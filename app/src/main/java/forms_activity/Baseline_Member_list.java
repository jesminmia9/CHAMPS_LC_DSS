package forms_activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.icddrb.champs_lc_dss.R;
import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Common.Connection;
import Common.Global;
import Common.Global_CodeList;
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import forms_datamodel.MemberMove_DataModel;
import forms_datamodel.Member_DataModel;
import forms_datamodel.Visits_DataModel;

public class Baseline_Member_list extends AppCompatActivity {
    boolean networkAvailable=false;
    Location currentLocation; 
    double currentLatitude,currentLongitude; 
    //Disabled Back/Home key
    //--------------------------------------------------------------------------------------------------
    @Override 
    public boolean onKeyDown(int iKeyCode, KeyEvent event)
    {
        if(iKeyCode == KeyEvent.KEYCODE_BACK || iKeyCode == KeyEvent.KEYCODE_HOME) 
             { return false; }
        else { return true;  }
    }
    String VariableID;
    private int mDay;
    private int mMonth;
    private int mYear;
    static final int DATE_DIALOG = 1;
    static final int TIME_DIALOG = 2;

    Connection C;
    Global g;
    private List<Member_DataModel> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
    static String TableName;

    TextView lblHeading;
    TextView lblTotal;
    Button btnAdd;
    ImageButton btnSearch;
    EditText txtSearch;
    EditText dtpFDate;
    EditText dtpTDate;
    TextView txtRespValidat2;
    TextView lblCompound1;
    TextView lblMemHHID;
    TextView lblCompoundAddr;
    static String STARTTIME = "";
    static String DEVICEID  = "";

    static String ENTRYUSER = "";

    Button btnSES;
    Button btnDeath;
    Button btnStatus;

    ConstraintLayout ll_no_data;
    static String MEMID = "";
    static String HHID = "";
    static String VISITNO = "";
    static String HHNO = "";
    static String HHIDHEAD = "";
    static String MSlNo = "";

    static String COMPOUNDNAME = "";
    static String COMPOUNDADRS = "";
    static String HHHEAD = "";
    static String ROUND = "0";
    static String SurvType = "";
    static String VISIT_DATE = "";

    static String HAVE_DEATH = "";
    static String TOTAL_DEATH = "";
    String BaselineStatus = "1";

    Bundle IDbundle;

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.baseline_member_list);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         C = new Connection(this);
         g = Global.getInstance();
         STARTTIME = g.CurrentTime24();

         DEVICEID = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         HHID = IDbundle.getString("HHID");
         VISITNO = IDbundle.getString("VisitNo");
         HHNO = IDbundle.getString("HHNO");
         COMPOUNDNAME = IDbundle.getString("CompoundName");
         COMPOUNDADRS = IDbundle.getString("CompoundAdrs");
         HHHEAD = IDbundle.getString("HHHead");
         MSlNo = IDbundle.getString("MSlNo");
         SurvType = IDbundle.getString("SurvType");
         VISIT_DATE = IDbundle.getString("visitdate");

         HAVE_DEATH = IDbundle.getString("havedeath");
         TOTAL_DEATH = IDbundle.getString("totaldeath");

         TableName = "Member";
         lblHeading = (TextView)findViewById(R.id.lblHeading);
         lblTotal = (TextView)findViewById(R.id.lblTotal);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 Intent intent = getIntent();
                 setResult(RESULT_OK,intent);
                 finish();
             }});

         btnStatus = (Button) findViewById(R.id.btnStatus);
         btnStatus.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
                 Interview_Status_Form(HHID, VISITNO);
             }});

         btnAdd = (Button) findViewById(R.id.btnAdd);
         btnAdd.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
                 String total_member_in_household = C.ReturnSingleValue("Select TotMem from Household where HHID='"+ HHID +"'");
                 String total_member_entry = C.ReturnSingleValue("Select count(*)total from Member where HHID='"+ HHID +"' and isdelete='2' and active='1'");
                 if (!Global.isNullOrEmpty(total_member_entry)) {
                     if (Integer.parseInt(total_member_in_household)<= Integer.parseInt(total_member_entry)) {
                         Connection.MessageBox(Baseline_Member_list.this, "You can't enter more members where as total number of members is " + total_member_in_household + " in household. \n\nPlease update data in household form before entering more members.");
                     } else {

                         NameEntry_Form("");
                     }
                 }
             }});

         btnSES = (Button) findViewById(R.id.btnSES);
         btnSES.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
                 Bundle IDbundle = new Bundle();
                 IDbundle.putString("HHID", HHID);
                 IDbundle.putString("SESNo", "1");
                 IDbundle.putString("SurvType", SurvType);
                 IDbundle.putString("round", ROUND);
                 Intent intent = new Intent(getApplicationContext(), Baseline_SES.class);
                 intent.putExtras(IDbundle);
                 startActivityForResult(intent, 1);
             }});

         btnDeath = (Button) findViewById(R.id.btnDeath);
         if(HAVE_DEATH.equals("2")) btnDeath.setEnabled(false);
         else if(HAVE_DEATH.equals("1"))
         {
             btnDeath.setVisibility(View.VISIBLE);
             btnDeath.setText(" Death ("+ TOTAL_DEATH +") ");
         }

         btnDeath.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
                 Bundle IDbundle = new Bundle();
                 IDbundle.putString("HHID", HHID);
                 IDbundle.putString("HHNO", HHNO);
                 Intent intent = new Intent(getApplicationContext(), DeathNotifi_list.class);
                 intent.putExtras(IDbundle);
                 //startActivityForResult(intent, 1);
                 startActivity(intent);
             }});

         txtSearch = (EditText)findViewById(R.id.txtSearch);

         btnSearch = (ImageButton) findViewById(R.id.btnSearch);
         btnSearch.setOnClickListener(new View.OnClickListener() {

             public void onClick(View view) {
//                         DataSearch(txtSearch.getText().toString());

             }});


        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        ll_no_data = findViewById(R.id.ll_no_data);
        mAdapter = new DataAdapter(dataList);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        dtpFDate = (EditText) findViewById(R.id.dtpFDate);
        dtpTDate = (EditText) findViewById(R.id.dtpTDate);
        dtpFDate.setText(Global.DateNowDMY());
        dtpTDate.setText(Global.DateNowDMY());
        dtpFDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (dtpFDate.getRight() - dtpFDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        VariableID = "dtpFDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                }
                return false;
            }
        });
        
        dtpTDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (dtpTDate.getRight() - dtpTDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        VariableID = "dtpTDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                }
                return false;
            }
        });

         lblMemHHID=findViewById(R.id.lblMemHHID);
         lblCompoundAddr=findViewById(R.id.lblCompoundAddr);
         lblMemHHID.setText("HH NO: "+ HHNO+", "+HHHEAD);
         lblCompoundAddr.setText(COMPOUNDNAME+", "+COMPOUNDADRS);

        DataSearch(HHID);

     }
     catch(Exception  e)
     {
         Connection.MessageBox(Baseline_Member_list.this, e.getMessage());
         return;
     }
 }

    @Override
    protected void onResume() {
        super.onResume();
        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2)
                && ProjectSetting.LISTING_OR_BASELINE_OR_SURVEILLANCE.equals(ProjectSetting.LISTING)){
            btnSES.setVisibility(View.GONE);
            btnDeath.setVisibility(View.GONE);
        }

        BaselineStatus = "1";
        if(C.Existence("Select * from SES where HHID = '"+HHID+"'")){
            btnSES.setBackgroundResource(R.drawable.style_completed_square_shape);
            btnSES.setTextColor(Color.BLACK);
        }else{
            btnSES.setBackgroundResource(R.drawable.style_not_completed_square_shape);
            btnSES.setTextColor(Color.BLACK);
            BaselineStatus = "2";
        }

        if(C.Existence("Select * from Visits where HHID='"+HHID+"' and VisitNo='"+ VISITNO +"' and VisitStatus in('02','03','06','07')")){
            btnStatus.setBackgroundResource(R.drawable.style_completed_square_shape);
            btnStatus.setTextColor(Color.BLACK);
        }else{
            btnStatus.setBackgroundResource(R.drawable.style_not_completed_square_shape);
            btnStatus.setTextColor(Color.BLACK);
            BaselineStatus = "2";
        }

        int death_entry = Integer.parseInt(C.ReturnSingleValue("select count(*)total from DeathNotifi where HHID='"+ HHID +"'"));
        int total_death = Integer.parseInt(TOTAL_DEATH);
        btnDeath.setText(" Death ("+ death_entry +"/"+ total_death +") ");

        if(death_entry == total_death && total_death !=0 ){
            btnDeath.setBackgroundResource(R.drawable.style_completed_square_shape);
            btnDeath.setTextColor(Color.BLACK);
        }else{
            if(total_death==0){
                btnDeath.setBackgroundResource(R.drawable.button_style_circle_line_disable);
                btnDeath.setTextColor(getResources().getColor(R.color.color_light_gray));
            }else {
                btnDeath.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                btnDeath.setTextColor(Color.BLACK);
                BaselineStatus = "2";
            }
        }

        /*if(C.Existence("Select * from PregHis where HHID like ('"+HHID+"')")){
            btnDeath.setBackgroundResource(R.drawable.style_completed_square_shape);
            btnDeath.setTextColor(Color.BLACK);
        }else{
            btnDeath.setBackgroundResource(R.drawable.style_not_completed_square_shape);
            btnDeath.setTextColor(Color.BLACK);
        }*/

        DataSearch(HHID);


    }

    @Override
    protected void onPause() {
        //Update data collection status
        try {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2)
                            && ProjectSetting.LISTING_OR_BASELINE_OR_SURVEILLANCE.equals(ProjectSetting.LISTING)){
                            btnSES.setVisibility(View.GONE);
                            btnDeath.setVisibility(View.GONE);
                            String status = C.SaveData("Update Household set BaselineStatus='1',upload='2' where HHID='" + HHID + "'");
                        }else {
                            String status = C.SaveData("Update Household set BaselineStatus='" + BaselineStatus + "',upload='2' where HHID='" + HHID + "'");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        super.onPause();
    }

    @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if (resultCode == Activity.RESULT_CANCELED) {
         //Write your code if there's no result
     } else {
//         DataSearch(HHID);
     }
 }

 private void DataSearch(String HHID)
     {
       try
        {
           Member_DataModel d = new Member_DataModel();

            String SQL = "Select m.MemID,m.MSlNo,m.Name,m.FaNo,m.MoNo,m.DSSID,m.Sex," +
                    " ifnull(cast(((julianday('"+VISIT_DATE+"')-julianday(m.BDate))) as int),0)AgeD," +
                    " ifnull(cast(((julianday(date('now'))-julianday(m.BDate))/365.25) as int),0)AgeY," +
                    " m.Rth,m.MS,m.EduY,m.Ocp,m.OcpOth," +
                    " ifnull(m.BDate,'')BDate,ifnull(m.BDate_D,'')BDate_D, ifnull(m.BDate_M,'')BDate_M, ifnull(m.BDate_Y,'')BDate_Y," +
                    " ifnull(m.Pstat,'')Pstat,ifnull(m.LmpDt,'')LmpDt," +
                    " ifnull(m.Sp1,'')Sp1,ifnull(m.Sp2,'')Sp2," +
                    " (Case when m.MoNo='00' then m.MoName else ifnull(mo.Name,'') end)MName," +
                    " (Case when m.FaNo='00' then m.FaName else ifnull(fa.Name,'') end)FName ," +
                    " ifnull(m.Active,'')Active,ifnull(m.Active,'')Active ,ifnull(mv.MEnDate,'')MEnDate," +
                    " ifnull(ph.MemID,'')phis," +
                    " ifnull(cc.MemID,'')cchar, IFNULL(anthro.MemID, '') AS anthro," +
                    " ifnull(cg.MemID,'')care, " +
                    " ifnull(va.MemID,'')vacc, " +
                    " ifnull(cb.uuid,'')con_beh, " +
                    " ifnull(m.isdelete,'2')isdelete " +
                    " from Member m\n" +
                    " left outer join (Select v.* from MemberMove v inner join (\n" +
                    " Select HHID,MemID,max(MEnDate)MEnDate from MemberMove group by HHID,MemID)v1 on v.HHID=v1.HHID and v.MemID=v1.MemID )mv \n" +
                    " on m.HHID=mv.HHID and m.MemID=mv.MemID \n" +
                    " left join Member mo on m.HHID=mo.HHID and  m.MoNo=mo.MSlNo\n" +
                    " left join Member fa on m.HHID=fa.HHID and m.FaNo=fa.MSlNo\n" +

                    " left join PregHis ph on m.MemID=ph.MemID\n" +
                    " left join ChildChar cc on m.MemID=cc.MemID\n" +
                    " LEFT JOIN Anthropometric anthro ON m.MemID = anthro.MemID\n" +
                    " left join Caregiver cg on m.MemID=cg.MemID\n" +
                    " left outer join VaccinationListMaster va ON m.MemID = va.MemID" +
                    " left outer join CONTRACEPTIVE_BEHAVIOUR cb ON m.MemID = cb.MemID" +

                    " Where m.HHID='"+ HHID +"' and m.isdelete='2'" +
                    " Group by m.MemID,m.MSlNo,m.Name,m.FaNo,m.MoNo,m.DSSID,m.Sex,m.Age,m.Rth,m.MS,m.EduY,m.Ocp, m.Pstat,m.LmpDt,m.Sp1,m.Sp2, MName, FName ,m.Active"+
                    " order by m.MSlNo";

             List<Member_DataModel> data = d.SelectMember(this, SQL);
             dataList.clear();

             dataList.addAll(data);
             if (dataList != null && !dataList.isEmpty())
             {
                 recyclerView.setVisibility(View.VISIBLE);
                 ll_no_data.setVisibility(View.GONE);
             }
             else
             {
                 recyclerView.setVisibility(View.GONE);
                 ll_no_data.setVisibility(View.VISIBLE);
             }
             try {
                 mAdapter.notifyDataSetChanged();
                 lblTotal.setText(" (Total: "+ dataList.size() +")");
             }catch ( Exception ex){
                 Connection.MessageBox(Baseline_Member_list.this,ex.getMessage());
             }



        }
        catch(Exception  e)
        {
            Connection.MessageBox(Baseline_Member_list.this, e.getMessage());
            return;
        }
     }

     public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
         private List<Member_DataModel> dataList;
         public class MyViewHolder extends RecyclerView.ViewHolder {
         LinearLayout secListRow;
         LinearLayout secModules;
         RelativeLayout rv_body;
         TextView MemID;
         TextView HHID;
         TextView MSlNo;
         TextView Name;
         TextView FaNo;
         TextView MoNo;
         TextView Age;
         TextView EduY;
         TextView MS;
         TextView Rth;
         TextView RthOth;
         TextView Sex;
         TextView BDate;
         TextView Pstat;
         TextView LmpDt;
         TextView Sp1;
         Button btnObsMat;
         Button btnBirth;
         Button btnChild;
         Button btnAnthro;
         Button btnVacci;
         Button btnCaregiver;
         Button btnCont;
         ImageView iv_next;
         ImageView iv_delete;

         public MyViewHolder(View convertView) {
             super(convertView);
             secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
             secModules = (LinearLayout)convertView.findViewById(R.id.secModules);
             MemID = (TextView)convertView.findViewById(R.id.MemID);
             MSlNo = (TextView)convertView.findViewById(R.id.MSlNo);
             Name = (TextView)convertView.findViewById(R.id.Name);
             FaNo = (TextView)convertView.findViewById(R.id.FaNo);
             MoNo = (TextView)convertView.findViewById(R.id.MoNo);
             Age = (TextView)convertView.findViewById(R.id.Age);
             Sex = (TextView)convertView.findViewById(R.id.Sex);
             Rth = (TextView)convertView.findViewById(R.id.Rth);
             EduY = (TextView)convertView.findViewById(R.id.EduY);
             MS = (TextView)convertView.findViewById(R.id.MS);
             BDate = (TextView)convertView.findViewById(R.id.BDate);
             Pstat = (TextView)convertView.findViewById(R.id.Pstat);
             LmpDt = (TextView)convertView.findViewById(R.id.LmpDt);
             Sp1 = (TextView)convertView.findViewById(R.id.Sp1);
             MS = (TextView)convertView.findViewById(R.id.MS);
             rv_body = convertView.findViewById(R.id.rv_body);
             iv_next = convertView.findViewById(R.id.iv_next);
             iv_delete = convertView.findViewById(R.id.iv_delete);

             btnObsMat = (Button)convertView.findViewById(R.id.btnObsMat);
             btnBirth = (Button)convertView.findViewById(R.id.btnBirth);
             btnChild = (Button)convertView.findViewById(R.id.btnChild);
             btnAnthro = (Button)convertView.findViewById(R.id.btnAnthro);
             btnVacci = (Button)convertView.findViewById(R.id.btnVacci);
             btnCaregiver = (Button)convertView.findViewById(R.id.btnCaregiver);
             btnCont = (Button)convertView.findViewById(R.id.btnCont);

             }
         }
         public DataAdapter(List<Member_DataModel> datalist) {
             this.dataList = datalist;
         }
         @Override
         public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.baseline_member_row, parent, false);
             return new MyViewHolder(itemView);
         }
         @Override
         public void onBindViewHolder(MyViewHolder holder, int position) {
             final Member_DataModel data = dataList.get(position);

             holder.MSlNo.setText(data.getMSlNo());
             holder.Name.setText(": "+ data.getName());
             holder.FaNo.setText(": "+ data.getFName());
             holder.MoNo.setText(": "+ data.getMName());
             holder.Age.setText(": "+ data.getAgeY());

             if(data.getSex().equals("1")) holder.Sex.setText(": M");
             else if(data.getSex().equals("2")) holder.Sex.setText(": F");
             else if(data.getSex().equals("3")) holder.Sex.setText(": I");
             else if(data.getSex().equals("4")) holder.Sex.setText(": T");
             else if(data.getSex().equals("5")) holder.Sex.setText(": U");

             holder.EduY.setText(": "+ data.getEduY());
             holder.MS.setText(": "+ String.valueOf(data.getMS()));
             holder.Rth.setText(": "+ data.getRth());

             //holder.BDate.setText(": "+ Global.DateConvertDMY(data.getBDate()));
             if(data.getBDate_D().length()>0)
                 holder.BDate.setText(": "+ Global.Right("00"+data.getBDate_D(),2)+"/"+data.getBDate_M()+"/"+data.getBDate_Y());
             else
                 holder.BDate.setText(": ");

             if(data.getSex().equals("1")){
                 holder.Pstat.setText(": N/A");
                 holder.LmpDt.setText(": N/A");
             }else {
                 if (data.getPstat().equals("40"))
                     holder.Pstat.setText(": N");
                 else if (data.getPstat().equals("41")) holder.Pstat.setText(": Y");
                 else if (data.getPstat().equals("49")) holder.Pstat.setText(": DK");
                 else holder.Pstat.setText(":");

                 if (data.getLmpDt() != null && !data.getLmpDt().isEmpty()) {
                     holder.LmpDt.setText(": " + Global.DateConvertDMY(data.getLmpDt()));
                 }
             }

             holder.Sp1.setText(": "+ data.getSp1());


             if (data.getActive().equalsIgnoreCase("2")){
                 holder.Name.setTextColor(Color.RED);
                 holder.MSlNo.setTextColor(Color.RED);
             }
             else {
                 holder.Name.setTextColor(Color.BLACK);
                 holder.MSlNo.setTextColor(Color.BLACK);
             }

             if (data.getSex().equalsIgnoreCase("2") & !data.getMS().equalsIgnoreCase("00")){
                 //holder.btnPregHis.setVisibility(View.VISIBLE);
             }else {
                 //holder.btnPregHis.setVisibility(View.GONE);
             }

             holder.btnObsMat.setBackgroundResource(R.drawable.button_style_circle_line_disable);
             holder.btnBirth.setBackgroundResource(R.drawable.button_style_circle_line_disable);
             holder.btnChild.setBackgroundResource(R.drawable.button_style_circle_line_disable);
             holder.btnAnthro.setBackgroundResource(R.drawable.button_style_circle_line_disable);
             holder.btnVacci.setBackgroundResource(R.drawable.button_style_circle_line_disable);
             holder.btnCaregiver.setBackgroundResource(R.drawable.button_style_circle_line_disable);
             holder.btnCont.setBackgroundResource(R.drawable.button_style_circle_line_disable);

             holder.btnObsMat.setTextColor(getResources().getColor(R.color.color_light_gray));
             holder.btnBirth.setTextColor(getResources().getColor(R.color.color_light_gray));
             holder.btnChild.setTextColor(getResources().getColor(R.color.color_light_gray));
             holder.btnAnthro.setTextColor(getResources().getColor(R.color.color_light_gray));
             holder.btnVacci.setTextColor(getResources().getColor(R.color.color_light_gray));
             holder.btnCaregiver.setTextColor(getResources().getColor(R.color.color_light_gray));
             holder.btnCont.setTextColor(getResources().getColor(R.color.color_light_gray));

             holder.iv_next.setVisibility(View.GONE);

             //For Member Name Entry Only
             if (data.getSex() != null && !data.getSex().trim().isEmpty()){
                 //holder.iv_next.setVisibility(View.VISIBLE);
                 holder.iv_delete.setVisibility(View.GONE);
                 holder.secModules.setVisibility(View.VISIBLE);
                 holder.rv_body.setBackgroundResource(R.drawable.style_circle_line_normal);
             }else{
                 //holder.iv_next.setVisibility(View.GONE);
                 holder.iv_delete.setVisibility(View.VISIBLE);
                 holder.secModules.setVisibility(View.GONE);
                 holder.rv_body.setBackgroundResource(R.drawable.style_circle_line_incomplete);
             }

             //Listing for Bauchi
             if(ProjectSetting.LISTING_OR_BASELINE_OR_SURVEILLANCE.equals(ProjectSetting.LISTING)
                && ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2)){
                 holder.iv_delete.setVisibility(View.GONE);
                 holder.secModules.setVisibility(View.GONE);
                 holder.rv_body.setBackgroundResource(R.drawable.style_circle_line_normal);
             }

             holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     AlertDialog.Builder adb = new AlertDialog.Builder(Baseline_Member_list.this);
                     adb.setTitle("Remove");
                     adb.setIcon(R.drawable.favicon);
                     adb.setMessage("Do you want to remove this member information[Yes/No]?");
                     adb.setNegativeButton("No", null);
                     adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {
                             C.SaveData("Update Member set isdelete='1' where MemID='"+ data.getMemID() +"'");
                             DataSearch(HHID);
                         }});
                     adb.show();


                 }
             });

             int age_days = Integer.parseInt(data.getaged());

             //Ever married, Female, and age between 12 and 49
             if(!data.getMS().equals("00") && data.getSex().equals("2") && (age_days>= ProjectSetting.REPRODUCTIVE_AGE_START*365.25 && age_days<=ProjectSetting.REPRODUCTIVE_AGE_END*365.25)){
                 holder.btnObsMat.setTextColor(getResources().getColor(R.color.textColor));
                 holder.btnObsMat.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                 holder.btnObsMat.setEnabled(true);

                 if(data.getphis().length()>0){
                     holder.btnObsMat.setBackgroundResource(R.drawable.style_completed_square_shape);
                     holder.btnObsMat.setTextColor(Color.BLACK);
                 }else{
                     BaselineStatus = "2";
                 }
             }else{
                 holder.btnObsMat.setEnabled(false);
             }

            //Under 5 Age group
             if((age_days<=1826)){
                 holder.btnChild.setTextColor(getResources().getColor(R.color.textColor));
                 holder.btnChild.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                 holder.btnChild.setEnabled(true);
                 holder.btnAnthro.setTextColor(getResources().getColor(R.color.textColor));
                 holder.btnAnthro.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                 holder.btnAnthro.setEnabled(true);
                 holder.btnVacci.setTextColor(getResources().getColor(R.color.textColor));
                 holder.btnVacci.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                 holder.btnVacci.setEnabled(true);
                 if(data.getcchar().length()>0){
                     holder.btnChild.setBackgroundResource(R.drawable.style_completed_square_shape);
                     holder.btnChild.setTextColor(Color.BLACK);
                 }

                 else{
                     BaselineStatus = "2";
                 }
                 if(data.getanthro().length()>0){
                     holder.btnAnthro.setBackgroundResource(R.drawable.style_completed_square_shape);
                     holder.btnAnthro.setTextColor(Color.BLACK);
                     //holder.btnAnthro.setTextColor(Color.WHITE);
                 }else{
                     BaselineStatus = "2";
                 }
                 if(data.getvacc().length()>0){
                     holder.btnVacci.setBackgroundResource(R.drawable.style_completed_square_shape);
                     holder.btnVacci.setTextColor(Color.BLACK);
                 }else{
                     BaselineStatus = "2";
                 }
             }else{
                 holder.btnChild.setEnabled(false);
                 holder.btnAnthro.setEnabled(false);
                 holder.btnVacci.setEnabled(false);
             }

             //Age group 1 to 5 years
             if((age_days>=365 && age_days<=1826)){
                 holder.btnCaregiver.setTextColor(getResources().getColor(R.color.textColor));
                 holder.btnCaregiver.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                 holder.btnCaregiver.setEnabled(true);
                 if(data.getcare().length()>0){
                     holder.btnCaregiver.setBackgroundResource(R.drawable.style_completed_square_shape);
                     holder.btnCaregiver.setTextColor(Color.BLACK);
                 }else{
                     BaselineStatus = "2";
                 }
             }else{
                 holder.btnCaregiver.setEnabled(false);
             }

             if(!data.getMS().equals("0") && data.getSex().equals("2") && (age_days>= ProjectSetting.REPRODUCTIVE_AGE_START*365.25 && age_days<=ProjectSetting.REPRODUCTIVE_AGE_END*365.25))
             {
                 holder.btnBirth.setTextColor(getResources().getColor(R.color.textColor));
                 holder.btnBirth.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                 holder.btnBirth.setEnabled(true);

                 //Color the button green if birth char data is present
                 if(C.Existence("Select * from NBC_NBCare where MemID = '" + data.getMemID() + "' and HHID = '" + HHID + "'")){
                     holder.btnBirth.setBackgroundResource(R.drawable.style_completed_square_shape);
                 }

             }else{
                 holder.btnBirth.setEnabled(false);
             }


             //married, Female, and age between 12 and 49
             if((data.getMS().equals("1") || data.getMS().equals("2")) && data.getSex().equals("2")){
                 holder.btnCont.setTextColor(getResources().getColor(R.color.textColor));
                 holder.btnCont.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                 holder.btnCont.setEnabled(true);

                 if(data.getcon_beh().length()>0){
                     holder.btnCont.setBackgroundResource(R.drawable.style_completed_square_shape);
                     holder.btnCont.setTextColor(Color.BLACK);
                 }
             }else{
                 holder.btnCont.setEnabled(false);
             }

             if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2)){
                 holder.btnCont.setVisibility(View.VISIBLE);
             }

             holder.secListRow.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     //Listing for Bauchi
                     if (ProjectSetting.LISTING_OR_BASELINE_OR_SURVEILLANCE.equals(ProjectSetting.LISTING)
                             && ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2)) {
                         NameEntry_Form(data.getMemID());
                     } else {
                         final ProgressDialog progDailog = ProgressDialog.show(Baseline_Member_list.this, "", "Please Wait . . .", true);
                         new Thread() {
                             public void run() {
                                 try {

                                     Bundle IDbundle = new Bundle();
                                     IDbundle.putString("MemID", data.getMemID());
                                     IDbundle.putString("HHID", HHID);
                                     IDbundle.putString("HHNO", HHNO);
                                     IDbundle.putString("round", ROUND);
                                     IDbundle.putString("visitdate", VISIT_DATE);
                                     Intent f1 = new Intent(getApplicationContext(), Baseline_Member.class);
                                     f1.putExtras(IDbundle);
                                     startActivityForResult(f1, 1);
                             } catch(
                             Exception e)

                             {
                             }
                             progDailog.dismiss();
                         }
                     }.start();
                 }
                 }
             });

             holder.btnChild.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(Baseline_Member_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("MemID", data.getMemID());
                                 IDbundle.putString("HHID", HHID);
                                 IDbundle.putString("MemName", data.getName());
                                 Intent intent = new Intent(getApplicationContext(), Baseline_ChildChar.class);
                                 intent.putExtras(IDbundle);
                                 startActivityForResult(intent, 1);
                             } catch (Exception e) {
                             }
                             progDailog.dismiss();
                         }
                     }.start();
                 }
             });

             holder.btnAnthro.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(Baseline_Member_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("MemID", data.getMemID());
                                 IDbundle.putString("HHID", HHID);
                                 IDbundle.putString("MemName", data.getName());
                                 Intent intent = new Intent(getApplicationContext(), Surv_Anthropometric.class);
                                 intent.putExtras(IDbundle);
                                 startActivityForResult(intent, 1);
                             } catch (Exception e) {
                             }
                             progDailog.dismiss();
                         }
                     }.start();
                 }
             });

             holder.btnObsMat.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(Baseline_Member_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("ObsMatID", "");
                                 IDbundle.putString("MemID", data.getMemID());
                                 IDbundle.putString("HHID", HHID);
                                 IDbundle.putString("MSlNo", data.getMSlNo());
                                 IDbundle.putString("evtype", "");
                                 IDbundle.putString("round", ROUND);
                                 IDbundle.putString("SurvType", SurvType);
                                 IDbundle.putString("MemName", data.getName());
                                 Intent intent = new Intent(getApplicationContext(), PregHis.class);
                                 intent.putExtras(IDbundle);
                                 startActivityForResult(intent, 1);
                             } catch (Exception e) {
                             }
                             progDailog.dismiss();
                         }
                     }.start();
                 }
             });

             holder.btnBirth.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(Baseline_Member_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("NBID", "");
                                 IDbundle.putString("PGN", "");
                                 IDbundle.putString("MemID", data.getMemID());
                                 IDbundle.putString("MEMNAME", data.getName());
                                 IDbundle.putString("MSlNo", data.getMSlNo());
                                 IDbundle.putString("HHID", HHID);
                                 IDbundle.putString("round", ROUND);

                                 Intent intent = new Intent(Baseline_Member_list.this, NBC_NBCare_list.class);
                                 intent.putExtras(IDbundle);
                                 startActivityForResult(intent, 1);
                             } catch (Exception e) {
                             }
                             progDailog.dismiss();
                         }
                     }.start();
                 }
             });

             holder.btnCaregiver.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(Baseline_Member_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("MemID", data.getMemID());
                                 IDbundle.putString("HHID", HHID);
                                 IDbundle.putString("MemName", data.getName());
                                 Intent intent = new Intent(getApplicationContext(), Baseline_Caregiver.class);
                                 intent.putExtras(IDbundle);
                                 startActivityForResult(intent, 1);
                             } catch (Exception e) {
                             }
                             progDailog.dismiss();
                         }
                     }.start();
                 }
             });

             holder.btnVacci.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(Baseline_Member_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("VaccID", "");
                                 IDbundle.putString("MemID", data.getMemID());
                                 IDbundle.putString("HHID", HHID);
                                 IDbundle.putString("MemName", data.getName());
                                 IDbundle.putString("visitdate", VISIT_DATE);
                                 Intent intent = new Intent(getApplicationContext(), Vaccination_list.class);
                                 intent.putExtras(IDbundle);
                                 startActivityForResult(intent, 1);
                             } catch (Exception e) {
                             }
                             progDailog.dismiss();
                         }
                     }.start();
                 }
             });

             holder.btnCont.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(Baseline_Member_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("uuid", data.getcon_beh());
                                 IDbundle.putString("MemID", data.getMemID());
                                 IDbundle.putString("HHID", HHID);
                                 IDbundle.putString("MemName", data.getName());
                                 IDbundle.putString("visitdate", VISIT_DATE);
                                 Intent intent = new Intent(getApplicationContext(), CONTRACEPTIVE_BEHAVIOUR.class);
                                 intent.putExtras(IDbundle);
                                 startActivityForResult(intent, 1);
                             } catch (Exception e) {
                             }
                             progDailog.dismiss();
                         }
                     }.start();
                 }
             });
         }
         @Override
         public int getItemCount() {
             return dataList.size();
         }
     }

    private String MemNo(String HHID)
    {
        String M = C.ReturnSingleValue("Select cast(ifnull(max(MSlNo),0)+1 as varchar(2))MemNo from Member where HHID='"+ HHID +"' and isdelete='2'");
        M = Global.Right("0"+M,2);
        return M;
    }

    private void Interview_Status_Form(String HouseholdID, String VISIT_NO)
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.baseline_interview_status);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        final Spinner spnVisitStatus;
        final Spinner spnResp;
        final LinearLayout secRespValidat;

        spnResp = dialog.findViewById(R.id.spnResp);
        spnVisitStatus = dialog.findViewById(R.id.spnVisitStatus);
        secRespValidat = dialog.findViewById(R.id.secRespValidat);

        List<String> listVisitStatus = new ArrayList<String>();

        listVisitStatus.add("");
        //listVisitStatus.add("01-Interview Start");
        listVisitStatus.add("02-Interview completed");
        listVisitStatus.add("03-Interview Incomplete");
        //listVisitStatus.add("04-No member or suitable person found during house visit");
        //listVisitStatus.add("05-All members absent for many days");
        listVisitStatus.add("06-Interview canceled");
        listVisitStatus.add("07-Refused/Reluctance to interview");
        //listVisitStatus.add("08-House vacant");
        //listVisitStatus.add("09-Address not of any residence");
        //listVisitStatus.add("10-Residential Destroyed");
        //listVisitStatus.add("11-Dwelling not found");
        //listVisitStatus.add("97-Others");
        spnVisitStatus.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listVisitStatus)));
        spnResp.setAdapter(C.getArrayAdapter("Select '' union select MSlNo||'-'||Name from Member where HHID='"+ HHID +"' and ifnull(cast(((julianday(date('now'))-julianday(BDate))/365.25) as int),0)>=10"));
        if(spnResp.getAdapter().getCount()>1){
            secRespValidat.setVisibility(View.GONE);
        }

        ImageButton cmdBack = (ImageButton) dialog.findViewById(R.id.cmdBack);
        cmdBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }});

        Button cmdSave = (Button)dialog.findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (spnVisitStatus.getSelectedItemPosition() == 0) {
                    Connection.MessageBox(Baseline_Member_list.this, "Required field: Status of Interview.");
                    spnVisitStatus.requestFocus();
                    return;
                } else if (spnResp.getSelectedItemPosition() == 0) {
                    Connection.MessageBox(Baseline_Member_list.this, "Required field: Name of the respondent.");
                    spnResp.requestFocus();
                    return;
                } else {

                    C.SaveData("Update Visits set upload='2', VisitStatus='"+ spnVisitStatus.getSelectedItem().toString().split("-")[0] +"',RespID='"+ spnResp.getSelectedItem().toString().split("-")[0] +"' where HHID='"+ HHID +"' and VisitNo='"+ VISIT_NO +"'");
                    if(C.Existence("Select * from Visits where HHID='"+HHID+"' and VisitNo='"+ VISITNO +"' and VisitStatus in('02','03','06','07')")){
                        btnStatus.setBackgroundResource(R.drawable.style_completed_square_shape);
                        btnStatus.setTextColor(Color.BLACK);
                    }else{
                        btnStatus.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                        btnStatus.setTextColor(Color.BLACK);
                        BaselineStatus = "2";
                    }
                    dialog.dismiss();
                }

            }
        });
        Global.RoleManagement(cmdSave, MySharedPreferences.getValue(this, "userrole"));

        try
        {
            RadioButton rb;
            Visits_DataModel d = new Visits_DataModel();
            String SQL = "Select * from Visits where HHID='"+ HHID +"' and VisitNo='"+ VISITNO +"'";
            List<Visits_DataModel> data = d.SelectAll(this, SQL);
            for(Visits_DataModel item : data){
                spnVisitStatus.setSelection(Global.SpinnerItemPositionAnyLength(spnVisitStatus, String.valueOf(item.getVisitStatus())));
                spnResp.setSelection(Global.SpinnerItemPositionAnyLength(spnResp, String.valueOf(item.getRespID())));
            }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Baseline_Member_list.this, e.getMessage());
            return;
        }

        dialog.show();
    }

    private void NameEntry_Form(String MemID_)
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.baseline_member_name);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        /*dialog.getWindow().setDimAmount(0.0f);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);*/

        final EditText txtMSlNo;
        final EditText txtName;
        final LinearLayout secName;
        final LinearLayout secFirstMemberMessage;

        final LinearLayout secRth;
        final Spinner spnRth;
        final View lineRth;
        final LinearLayout secRthOth;
        final AutoCompleteTextView txtRthOth;
        final View lineRthOth;
        final LinearLayout secSex;
        final RadioGroup rdogrpSex;
        final RadioButton rdoSex1;
        final RadioButton rdoSex2;
        final RadioButton rdoSex3;
        final RadioButton rdoSex4;
        final RadioButton rdoSex5;
        final View lineSex;
        final LinearLayout seclblbdate;
        final View linelblbdate;
        final Spinner spnBDate_D;
        final View lineBDate_D;
        final LinearLayout secBDate_D;
        final LinearLayout secBDate_M;
        final Spinner spnBDate_M;
        final View lineBDate_M;
        final LinearLayout secBDate_Y;
        final Spinner spnBDate_Y;
        final View lineBDate_Y;
        final LinearLayout secBDate;
        final EditText dtpBDate;
        final View lineBDate;
        final LinearLayout secBDateType;
        final RadioGroup rdogrpBDateType;
        final RadioButton rdoBDateType1;
        final RadioButton rdoBDateType2;
        final View lineBDateType;
        final LinearLayout secAge;
        final EditText txtAge;
        final View lineAge;
        final LinearLayout secAgeU;
        final RadioGroup rdogrpAgeU;
        final RadioButton rdoAgeU1;
        final RadioButton rdoAgeU2;
        final RadioButton rdoAgeU3;
        final View lineAgeU;

        txtMSlNo=(EditText)dialog.findViewById(R.id.txtMSlNo);
        txtMSlNo.setText(MemNo(HHID));
        txtName=(EditText)dialog.findViewById(R.id.txtName) ;
        secName = dialog.findViewById(R.id.secName);
        secFirstMemberMessage = dialog.findViewById(R.id.secFirstMemberMessage);
        secRth = dialog.findViewById(R.id.secRth);
        spnRth = dialog.findViewById(R.id.spnRth);
        lineRth = dialog.findViewById(R.id.lineRth);
        secRthOth = dialog.findViewById(R.id.secRthOth);
        txtRthOth = dialog.findViewById(R.id.txtRthOth);
        lineRthOth = dialog.findViewById(R.id.lineRthOth);

        /*List<String> listRth = new ArrayList<String>();

        listRth.add("");
        listRth.add("01-Household head");
        listRth.add("02-1st Wife");
        listRth.add("03-2nd Wife");
        listRth.add("04-3rd Wife");
        listRth.add("05-4th Wife");
        listRth.add("06-Son");
        listRth.add("07-Daughter");
        listRth.add("08-Adopted son");
        listRth.add("09-Adopted daughter");
        listRth.add("10-Stepson");
        listRth.add("11-Stepdaughter");
        listRth.add("12-Nephew");
        listRth.add("13-Niece");
        listRth.add("14-Grandson");
        listRth.add("15-Granddaughter");
        listRth.add("16-Father");
        listRth.add("17-Mother");
        listRth.add("18-Stepfather");
        listRth.add("19-Stepmother");
        listRth.add("20-Brother");
        listRth.add("21-Sister");
        listRth.add("22-Stepbrother");
        listRth.add("23-Stepsister");
        listRth.add("24-Cousin");
        listRth.add("25-Uncle");
        listRth.add("26-Aunt");
        listRth.add("27-Grandfather");
        listRth.add("28-Grandmother");
        listRth.add("29-Father/mother-in-law");
        listRth.add("30-Son/daughter-in-law");
        listRth.add("31-Brother/sister-in-law");
        listRth.add("32-Unrelated");
        listRth.add("97-Other");
        listRth.add("98-Don't know");
        listRth.add("99-Refused to respond");*/
        spnRth.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(Global_CodeList.Get_RTH())));
        spnRth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String spnData = "";
                if (spnRth.getSelectedItem().toString().length() != 0)
                {
                    spnData = Connection.SelectedSpinnerValue(spnRth.getSelectedItem().toString(), "-");
                }
                if(spnData.equalsIgnoreCase("97"))
                {
                    secRthOth.setVisibility(View.VISIBLE);
                    lineRthOth.setVisibility(View.VISIBLE);
                }
                else
                {
                    secRthOth.setVisibility(View.GONE);
                    lineRthOth.setVisibility(View.GONE);
                    txtRthOth.setText("");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        txtRthOth.setAdapter(C.getArrayAdapter("Select distinct RthOth from "+ TableName +" order by RthOth"));

        txtRthOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

            }
        });
        txtRthOth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (txtRthOth.getRight() - txtRthOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        ((EditText)v).setText("");
                        return true;
                    }
                }
                return false;
            }
        });

        secSex = dialog.findViewById(R.id.secSex);
        rdogrpSex = dialog.findViewById(R.id.rdogrpSex);
        rdoSex1 = dialog.findViewById(R.id.rdoSex1);
        rdoSex2 = dialog.findViewById(R.id.rdoSex2);
        rdoSex3 = dialog.findViewById(R.id.rdoSex3);
        rdoSex4 = dialog.findViewById(R.id.rdoSex4);
        rdoSex5 = dialog.findViewById(R.id.rdoSex5);
        lineSex = dialog.findViewById(R.id.lineSex);
        seclblbdate = dialog.findViewById(R.id.seclblbdate);
        linelblbdate = dialog.findViewById(R.id.linelblbdate);
        spnBDate_D = dialog.findViewById(R.id.spnBDate_D);
        lineBDate_D = dialog.findViewById(R.id.lineBDate_D);
        secBDate_D = dialog.findViewById(R.id.secBDate_D);
        secBDate_M = dialog.findViewById(R.id.secBDate_M);
        spnBDate_M = dialog.findViewById(R.id.spnBDate_M);

        lineBDate_M = dialog.findViewById(R.id.lineBDate_M);
        secBDate_Y = dialog.findViewById(R.id.secBDate_Y);
        spnBDate_Y = dialog.findViewById(R.id.spnBDate_Y);
        lineBDate_Y = dialog.findViewById(R.id.lineBDate_Y);
        secBDate = dialog.findViewById(R.id.secBDate);
        dtpBDate = dialog.findViewById(R.id.dtpBDate);
        lineBDate = dialog.findViewById(R.id.lineBDate);
        secBDateType = dialog.findViewById(R.id.secBDateType);
        rdogrpBDateType = dialog.findViewById(R.id.rdogrpBDateType);
        rdoBDateType1 = dialog.findViewById(R.id.rdoBDateType1);
        rdoBDateType2 = dialog.findViewById(R.id.rdoBDateType2);
        lineBDateType = dialog.findViewById(R.id.lineBDateType);
        secAge = dialog.findViewById(R.id.secAge);
        txtAge = dialog.findViewById(R.id.txtAge);
        lineAge = dialog.findViewById(R.id.lineAge);
        secAgeU = dialog.findViewById(R.id.secAgeU);
        rdogrpAgeU = dialog.findViewById(R.id.rdogrpAgeU);
        rdoAgeU1 = dialog.findViewById(R.id.rdoAgeU1);
        rdoAgeU2 = dialog.findViewById(R.id.rdoAgeU2);
        rdoAgeU3 = dialog.findViewById(R.id.rdoAgeU3);
        lineAgeU = dialog.findViewById(R.id.lineAgeU);

        if(txtMSlNo.getText().toString().equals("01")){
            secFirstMemberMessage.setVisibility(View.VISIBLE);
        }else{
            secFirstMemberMessage.setVisibility(View.GONE);
        }
        txtMSlNo.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if(txtMSlNo.getText().toString().equals("01")){
                    secFirstMemberMessage.setVisibility(View.VISIBLE);
                }else{
                    secFirstMemberMessage.setVisibility(View.GONE);
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });

        List<String> listBDate_D = new ArrayList<String>();
        listBDate_D.add("");
        for(int i=1;i<=31;i++){
            listBDate_D.add(String.valueOf(i));
        }
        listBDate_D.add("98");
        spnBDate_D.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listBDate_D)));
        spnBDate_D.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String DD = spnBDate_D.getSelectedItemPosition()>0?spnBDate_D.getSelectedItem().toString():"";
                String MM = spnBDate_M.getSelectedItemPosition()>0?spnBDate_M.getSelectedItem().toString().split("-")[0]:"";
                String YY = spnBDate_Y.getSelectedItemPosition()>0?spnBDate_Y.getSelectedItem().toString():"";
                if(DD.equals("98") || MM.equals("98") || YY.equals("9998")){
                    secAge.setVisibility(View.VISIBLE);
                    lineAge.setVisibility(View.VISIBLE);
                    secAgeU.setVisibility(View.VISIBLE);
                    lineAgeU.setVisibility(View.VISIBLE);
                }else{
                    secAge.setVisibility(View.GONE);
                    lineAge.setVisibility(View.GONE);
                    txtAge.setText("");
                    secAgeU.setVisibility(View.GONE);
                    lineAgeU.setVisibility(View.GONE);
                    rdogrpAgeU.clearCheck();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        List<String> listBDate_M = new ArrayList<String>();

        listBDate_M.add("");
        listBDate_M.add("01-Jan");
        listBDate_M.add("02-Feb");
        listBDate_M.add("03-Mar");
        listBDate_M.add("04-Apr");
        listBDate_M.add("05-May");
        listBDate_M.add("06-Jun");
        listBDate_M.add("07-Jul");
        listBDate_M.add("08-Aug");
        listBDate_M.add("09-Sep");
        listBDate_M.add("10-Oct");
        listBDate_M.add("11-Nov");
        listBDate_M.add("12-Dec");
        listBDate_M.add("98-Don't know");

        spnBDate_M.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listBDate_M)));
        spnBDate_M.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String DD = spnBDate_D.getSelectedItemPosition()>0?spnBDate_D.getSelectedItem().toString():"";
                String MM = spnBDate_M.getSelectedItemPosition()>0?spnBDate_M.getSelectedItem().toString().split("-")[0]:"";
                String YY = spnBDate_Y.getSelectedItemPosition()>0?spnBDate_Y.getSelectedItem().toString():"";
                if(DD.equals("98") || MM.equals("98") || YY.equals("9998")){
                    secAge.setVisibility(View.VISIBLE);
                    lineAge.setVisibility(View.VISIBLE);
                    secAgeU.setVisibility(View.VISIBLE);
                    lineAgeU.setVisibility(View.VISIBLE);
                }else{
                    secAge.setVisibility(View.GONE);
                    lineAge.setVisibility(View.GONE);
                    txtAge.setText("");
                    secAgeU.setVisibility(View.GONE);
                    lineAgeU.setVisibility(View.GONE);
                    rdogrpAgeU.clearCheck();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        List<String> listBDate_Y = new ArrayList<String>();

        listBDate_Y.add("");
        for(int i= Integer.parseInt(Global.CurrentYear());i>=Integer.parseInt(Global.CurrentYear())-100;i--){
            listBDate_Y.add(String.valueOf(i));
        }
        listBDate_Y.add("9998");
        spnBDate_Y.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listBDate_Y)));
        spnBDate_Y.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String DD = spnBDate_D.getSelectedItemPosition()>0?spnBDate_D.getSelectedItem().toString():"";
                String MM = spnBDate_M.getSelectedItemPosition()>0?spnBDate_M.getSelectedItem().toString().split("-")[0]:"";
                String YY = spnBDate_Y.getSelectedItemPosition()>0?spnBDate_Y.getSelectedItem().toString():"";
                if(DD.equals("98") || MM.equals("98") || YY.equals("9998")){
                    secAge.setVisibility(View.VISIBLE);
                    lineAge.setVisibility(View.VISIBLE);
                    secAgeU.setVisibility(View.VISIBLE);
                    lineAgeU.setVisibility(View.VISIBLE);
                }else{
                    secAge.setVisibility(View.GONE);
                    lineAge.setVisibility(View.GONE);
                    txtAge.setText("");
                    secAgeU.setVisibility(View.GONE);
                    lineAgeU.setVisibility(View.GONE);
                    rdogrpAgeU.clearCheck();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        //For Bauchi Need additional Variables
        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2)){
            secRth.setVisibility(View.VISIBLE);
            lineRth.setVisibility(View.VISIBLE);
            secRthOth.setVisibility(View.GONE);
            lineRthOth.setVisibility(View.GONE);
            secSex.setVisibility(View.VISIBLE);
            lineSex.setVisibility(View.VISIBLE);
            seclblbdate.setVisibility(View.VISIBLE);
            linelblbdate.setVisibility(View.VISIBLE);
            lineBDate_D.setVisibility(View.VISIBLE);
            secBDate_D.setVisibility(View.VISIBLE);
            secBDate_M.setVisibility(View.VISIBLE);
            lineBDate_M.setVisibility(View.VISIBLE);
            secBDate_Y.setVisibility(View.VISIBLE);
            lineBDate_Y.setVisibility(View.VISIBLE);
            secBDate.setVisibility(View.GONE);
            lineBDate.setVisibility(View.GONE);
            secBDateType.setVisibility(View.GONE);
            lineBDateType.setVisibility(View.GONE);
            secAge.setVisibility(View.GONE);
            lineAge.setVisibility(View.GONE);
            secAgeU.setVisibility(View.GONE);
            lineAgeU.setVisibility(View.GONE);

            rdoSex3.setVisibility(View.GONE);
            rdoSex4.setVisibility(View.GONE);
            rdoSex5.setVisibility(View.GONE);
        }else{
            secRth.setVisibility(View.GONE);
            lineRth.setVisibility(View.GONE);
            secRthOth.setVisibility(View.GONE);
            lineRthOth.setVisibility(View.GONE);
            secSex.setVisibility(View.GONE);
            lineSex.setVisibility(View.GONE);
            seclblbdate.setVisibility(View.GONE);
            linelblbdate.setVisibility(View.GONE);
            lineBDate_D.setVisibility(View.GONE);
            secBDate_D.setVisibility(View.GONE);
            secBDate_M.setVisibility(View.GONE);
            lineBDate_M.setVisibility(View.GONE);
            secBDate_Y.setVisibility(View.GONE);
            lineBDate_Y.setVisibility(View.GONE);
            secBDate.setVisibility(View.GONE);
            lineBDate.setVisibility(View.GONE);
            secBDateType.setVisibility(View.GONE);
            lineBDateType.setVisibility(View.GONE);
            secAge.setVisibility(View.GONE);
            lineAge.setVisibility(View.GONE);
            secAgeU.setVisibility(View.GONE);
            lineAgeU.setVisibility(View.GONE);
        }

        spnRth.requestFocus();

        ImageButton cmdBack = (ImageButton) dialog.findViewById(R.id.cmdBack);
        cmdBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }});

        Button cmdSave = (Button)dialog.findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(txtName.getText().toString().length()==0){
                    Connection.MessageBox(Baseline_Member_list.this,"Required field: Name of household member.");
                    txtName.requestFocus();
                    secName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                }
                else if(spnRth.getSelectedItemPosition()==0 & secRth.isShown()){
                    Connection.MessageBox(Baseline_Member_list.this,"Required field: 3. What is relationship with household head.");
                    spnRth.requestFocus();
                    secRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                }
                else if(txtRthOth.getText().toString().length()==0 & secRthOth.isShown())
                {
                    Connection.MessageBox(Baseline_Member_list.this,"Required field: Other relation Specify.");
                    txtRthOth.requestFocus();
                    secRthOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                }
                else if(!rdoSex1.isChecked() & !rdoSex2.isChecked() & !rdoSex3.isChecked() & !rdoSex4.isChecked() & !rdoSex5.isChecked() & secSex.isShown())
                {
                    Connection.MessageBox(Baseline_Member_list.this,"HR4 Required field: Sex.");
                    secSex.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                }
                else if(spnBDate_D.getSelectedItemPosition()==0  & secBDate_D.isShown())
                {
                    Connection.MessageBox(Baseline_Member_list.this,"Required field: Day.");
                    secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                }
                else if(spnBDate_M.getSelectedItemPosition()==0  & secBDate_M.isShown())
                {
                    Connection.MessageBox(Baseline_Member_list.this,"Required field: Month.");
                    secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                }
                else if(spnBDate_Y.getSelectedItemPosition()==0  & secBDate_Y.isShown())
                {
                    Connection.MessageBox(Baseline_Member_list.this,"Required field: Year.");
                    secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                }
                else if(txtAge.getText().toString().length()==0 && secAge.isShown()){
                    Connection.MessageBox(Baseline_Member_list.this,"Required field: Age.");
                    secAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                }
                else if(rdoAgeU1.isChecked() && (Integer.parseInt(txtAge.getText().toString())<1 && Integer.parseInt(txtAge.getText().toString())>59)){
                    Connection.MessageBox(Baseline_Member_list.this,"Age should be in month for over 59 days");
                    secAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                    secAgeU.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                }
                else if(rdoAgeU2.isChecked() && (Integer.parseInt(txtAge.getText().toString())<2 && Integer.parseInt(txtAge.getText().toString())>59)){
                    Connection.MessageBox(Baseline_Member_list.this,"Age should be in days for below 2 months or in years for over 59 months");
                    secAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                    secAgeU.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                }
                else if(rdoAgeU3.isChecked() && (Integer.parseInt(txtAge.getText().toString())<5)){
                    Connection.MessageBox(Baseline_Member_list.this,"Age should be in month or years for under 5 years ");
                    secAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                    secAgeU.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                }
                else{
                    if(spnBDate_Y.getSelectedItem().toString().equals("9998")){
                        int age = 0;
                        if(rdoAgeU1.isChecked()) age = Integer.parseInt(txtAge.getText().toString().length()==0?"0":txtAge.getText().toString());
                        else if(rdoAgeU2.isChecked()) age = (int) (Integer.parseInt(txtAge.getText().toString().length()==0?"0":txtAge.getText().toString())*30.4375);
                        else if(rdoAgeU3.isChecked()) age = (int) (Integer.parseInt(txtAge.getText().toString().length()==0?"0":txtAge.getText().toString())*365.25);

                        String DOB = Global.addDays(VISIT_DATE,-age);
                        dtpBDate.setText(DOB);
                    }else {
                        String DD = "";
                        String MM = "";
                        String YY = "";
                        if (secBDate_D.isShown() && secBDate_M.isShown() && secBDate_Y.isShown()) {
                            DD = Global.Right("0" + (spnBDate_D.getSelectedItem().toString().equals("98") ? "15" : spnBDate_D.getSelectedItem().toString()), 2);
                            MM = Global.Right("0" + (spnBDate_M.getSelectedItem().toString().split("-")[0].equals("98") ? "6" : spnBDate_M.getSelectedItem().toString().split("-")[0]), 2);
                            YY = spnBDate_Y.getSelectedItem().toString();
                            String DV = Global.DateValidate(DD + "/" + MM + "/" + YY);
                            if (DV.length() != 0) {
                                Connection.MessageBox(Baseline_Member_list.this, "Not a valid date format: Date of Birth.");
                                secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            } else {
                                dtpBDate.setText(DD + "/" + MM + "/" + YY);
                            }
                        } else {
                            spnBDate_D.setSelection(0);
                            spnBDate_M.setSelection(0);
                            spnBDate_Y.setSelection(0);
                            dtpBDate.setText("");
                        }
                    }

                    RadioButton rb;

                    //--------------------------------------------------------------------------
                    String DSSID = HHNO + txtMSlNo.getText().toString();
                    String MEM_ID = "";
                    if(MemID_.length()>0)
                    {
                        MEM_ID = MemID_;
                    }else{
                        MEM_ID = Global.Get_UUID(DEVICEID);
                    }

                    Member_DataModel objSave = new Member_DataModel();
                    /*if(MemID_.length()>0)
                        objSave.setMemID(MemID_);
                    else
                        objSave.setMemID(Global.Get_UUID(DEVICEID));
                    */
                    objSave.setMemID(MEM_ID);
                    objSave.setHHID(HHID);
                    objSave.setDSSID(DSSID);
                    objSave.setMSlNo(txtMSlNo.getText().toString());
                    objSave.setName(txtName.getText().toString());
                    objSave.setRth(spnRth.getSelectedItemPosition() == 0 ? "" : spnRth.getSelectedItem().toString().split("-")[0]);
                    objSave.setRthOth(txtRthOth.getText().toString());
                    objSave.setName(txtName.getText().toString());
                    String[] d_rdogrpSex = new String[]{"1", "2", "3", "4", "8"};
                    objSave.setSex("");
                    for (int i = 0; i < rdogrpSex.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpSex.getChildAt(i);
                        if (rb.isChecked()) objSave.setSex(d_rdogrpSex[i]);
                    }

                    objSave.setBDate_D(spnBDate_D.getSelectedItemPosition() == 0 ? "" : spnBDate_D.getSelectedItem().toString().split("-")[0]);
                    objSave.setBDate_M(spnBDate_M.getSelectedItemPosition() == 0 ? "" : spnBDate_M.getSelectedItem().toString().split("-")[0]);
                    objSave.setBDate_Y(spnBDate_Y.getSelectedItemPosition() == 0 ? "" : spnBDate_Y.getSelectedItem().toString().split("-")[0]);
                    objSave.setBDate(dtpBDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpBDate.getText().toString()) : dtpBDate.getText().toString());
                    String[] d_rdogrpBDateType = new String[]{"1", "2"};
                    objSave.setBDateType("");
                    for (int i = 0; i < rdogrpBDateType.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpBDateType.getChildAt(i);
                        if (rb.isChecked()) objSave.setBDateType(d_rdogrpBDateType[i]);
                    }

                    objSave.setAge(txtAge.getText().toString());
                    String[] d_rdogrpAgeU = new String[]{"1", "2", "3"};
                    objSave.setAgeU("");
                    for (int i = 0; i < rdogrpAgeU.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpAgeU.getChildAt(i);
                        if (rb.isChecked()) objSave.setAgeU(d_rdogrpAgeU[i]);
                    }

                    objSave.setMoNo("");
                    objSave.setMoName("");
                    objSave.setFaNo("");
                    objSave.setFaName("");
                    objSave.setEduY("");
                    objSave.setEmploy("");
                    objSave.setEmployOth("");
                    objSave.setOcp("");
                    objSave.setOcpDk("");

                    objSave.setReligion("");
                    objSave.setReligionOth("");
                    objSave.setEthnicity("");
                    objSave.setEthnicityOth("");
                    objSave.setMobileNo("");
                    objSave.setMS("");
                    objSave.setMSOth("");
                    objSave.setSp1("");
                    objSave.setSp1Name("");
                    objSave.setSp2("");
                    objSave.setSp2Name("");
                    objSave.setSp3("");
                    objSave.setSp3Name("");
                    objSave.setSp4("");
                    objSave.setSp4Name("");
                    objSave.setPstat("");

                    objSave.setLmpDt("");
                    objSave.setgage("");

                    objSave.setgageUnit("");

                    objSave.setanc_regis("");

                    objSave.setanc_resp_home("");
                    objSave.setanc_other_home("");
                    objSave.setanc_govt_hosp("");
                    objSave.setanc_govt_health("");
                    objSave.setanc_govt_health_post("");
                    objSave.setanc_priv_hosp("");

                    objSave.setanc_tba_home("");

                    objSave.setanc_ngo_hosp("");
                    objSave.setanc_other("");
                    objSave.setanc_dk("");
                    objSave.setanc_refuse("");
                    objSave.setanc_other_specify("");
                    objSave.setout_date("");

                    objSave.setRnd("0");
                    objSave.setActive("1");
                    objSave.setEnType("20");
                    objSave.setExType("");
                    objSave.setStartTime(STARTTIME);
                    objSave.setEndTime(g.CurrentTime24());
                    objSave.setDeviceID(DEVICEID);
                    objSave.setEntryUser(ENTRYUSER); //from data entry user list
                    objSave.setLat(MySharedPreferences.getValue(Baseline_Member_list.this, "lat"));
                    objSave.setLon(MySharedPreferences.getValue(Baseline_Member_list.this, "lon"));

                    String status = objSave.SaveUpdateData(Baseline_Member_list.this);
                    if(status.length()>0){
                        Connection.MessageBox(Baseline_Member_list.this,status);
                        return;
                    }else {
                        //--------------------------------------------------------------------------
                        MemberMove_DataModel objSave1 = new MemberMove_DataModel();

                        objSave1.setMemID(MEM_ID);
                        objSave1.setHHID(HHID);
                        objSave1.setMSlNo(txtMSlNo.getText().toString());
                        objSave1.setActive("1");
                        objSave1.setDSSID(DSSID);
                        objSave1.setMEnType("20");
                        objSave1.setMEnDate(VISIT_DATE);
                        objSave1.setMExType("");
                        objSave1.setMExDate("");
                        objSave1.setRnd(ROUND);
                        objSave1.setMemNote("");
                        objSave1.setStartTime(STARTTIME);
                        objSave1.setEndTime(g.CurrentTime24());
                        objSave1.setDeviceID(DEVICEID);
                        objSave1.setEntryUser(ENTRYUSER); //from data entry user list
                        objSave1.setLat(MySharedPreferences.getValue(Baseline_Member_list.this, "lat"));
                        objSave1.setLon(MySharedPreferences.getValue(Baseline_Member_list.this, "lon"));

                        String status1 = objSave1.SaveUpdateData(Baseline_Member_list.this);
                        if(status1.length()>0){
                            Connection.MessageBox(Baseline_Member_list.this,status1);
                            return;
                        }else {
                            txtMSlNo.setText(MemNo(HHID));
                            spnRth.setSelection(0);
                            txtName.setText("");
                            rdogrpSex.clearCheck();
                            spnBDate_D.setSelection(0);
                            spnBDate_M.setSelection(0);
                            spnBDate_Y.setSelection(0);
                            dtpBDate.setText("");
                            txtAge.setText("");
                            rdogrpAgeU.clearCheck();
                            txtName.requestFocus();

                            secRth.setBackgroundColor(Color.WHITE);
                            secRthOth.setBackgroundColor(Color.WHITE);
                            secName.setBackgroundColor(Color.WHITE);
                            secSex.setBackgroundColor(Color.WHITE);
                            secBDate_D.setBackgroundColor(Color.WHITE);
                            secBDate_M.setBackgroundColor(Color.WHITE);
                            secBDate_Y.setBackgroundColor(Color.WHITE);
                            secBDate.setBackgroundColor(Color.WHITE);
                            secBDateType.setBackgroundColor(Color.WHITE);
                            secAge.setBackgroundColor(Color.WHITE);

                            DataSearch(HHID);

                            if(MemID_.length()>0) dialog.dismiss();

                            spnRth.setEnabled(true);
                            Toast.makeText(Baseline_Member_list.this, "Save Successfully...", Toast.LENGTH_SHORT).show();

                            String total_member_in_household = C.ReturnSingleValue("Select TotMem from Household where HHID='"+ HHID +"'");
                            String total_member_entry = C.ReturnSingleValue("Select count(*)total from Member where HHID='"+ HHID +"' and isdelete='2' and active='1'");
                            if(total_member_in_household.equals(total_member_entry))
                            {
                                dialog.dismiss();
                            }
                        }
                        //--------------------------------------------------------------------------
                    }

                    /*txtMSlNo.setText(MemNo(HHID));
                    spnRth.setSelection(0);
                    txtName.setText("");
                    rdogrpSex.clearCheck();
                    spnBDate_D.setSelection(0);
                    spnBDate_M.setSelection(0);
                    spnBDate_Y.setSelection(0);
                    dtpBDate.setText("");
                    txtAge.setText("");
                    rdogrpAgeU.clearCheck();
                    txtName.requestFocus();

                    secRth.setBackgroundColor(Color.WHITE);
                    secRthOth.setBackgroundColor(Color.WHITE);
                    secName.setBackgroundColor(Color.WHITE);
                    secSex.setBackgroundColor(Color.WHITE);
                    secBDate_D.setBackgroundColor(Color.WHITE);
                    secBDate_M.setBackgroundColor(Color.WHITE);
                    secBDate_Y.setBackgroundColor(Color.WHITE);
                    secBDate.setBackgroundColor(Color.WHITE);
                    secBDateType.setBackgroundColor(Color.WHITE);
                    secAge.setBackgroundColor(Color.WHITE);

                    DataSearch(HHID);

                    if(MemID_.length()>0) dialog.dismiss();

                    spnRth.setEnabled(true);
                    Toast.makeText(Baseline_Member_list.this, "Save Successfully...", Toast.LENGTH_SHORT).show();

                    String total_member_in_household = C.ReturnSingleValue("Select TotMem from Household where HHID='"+ HHID +"'");
                    String total_member_entry = C.ReturnSingleValue("Select count(*)total from Member where HHID='"+ HHID +"' and isdelete='2' and active='1'");
                    if(total_member_in_household.equals(total_member_entry))
                    {
                        dialog.dismiss();
                    }*/
                }
            }
        });

        Global.RoleManagement(cmdSave, MySharedPreferences.getValue(this, "userrole"));

        //Member Data Search for Bauchi
        try
        {
            RadioButton rb;
            Member_DataModel d = new Member_DataModel();
            String SQL = "Select * from "+ TableName +"  Where MemID='"+ MemID_ +"'";
            List<Member_DataModel> data = d.SelectAll(this, SQL);
            for(Member_DataModel item : data){
                txtMSlNo.setText(item.getMSlNo());
                spnRth.setSelection(Global.SpinnerItemPositionAnyLength(spnRth, String.valueOf(item.getRth())));
                txtRthOth.setText(item.getRthOth());
                txtName.setText(item.getName());
                String[] d_rdogrpSex = new String[] {"1","2","3","4","8"};
                for (int i = 0; i < d_rdogrpSex.length; i++)
                {
                    if (String.valueOf(item.getSex()).equals(String.valueOf(d_rdogrpSex[i])))
                    {
                        rb = (RadioButton)rdogrpSex.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnBDate_D.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_D, String.valueOf(item.getBDate_D())));
                spnBDate_M.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_M, String.valueOf(item.getBDate_M())));
                spnBDate_Y.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_Y, String.valueOf(item.getBDate_Y())));
                dtpBDate.setText(item.getBDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getBDate()));
                String[] d_rdogrpBDateType = new String[] {"1","2"};
                for (int i = 0; i < d_rdogrpBDateType.length; i++)
                {
                    if (String.valueOf(item.getBDateType()).equals(String.valueOf(d_rdogrpBDateType[i])))
                    {
                        rb = (RadioButton)rdogrpBDateType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtAge.setText(item.getAge());
                String[] d_rdogrpAgeU = new String[] {"1","2","3"};
                for (int i = 0; i < d_rdogrpAgeU.length; i++)
                {
                    if (String.valueOf(item.getAgeU()).equals(String.valueOf(d_rdogrpAgeU[i])))
                    {
                        rb = (RadioButton)rdogrpAgeU.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
            }

            String slNo= txtMSlNo.getText().toString();
            if(!slNo.isEmpty() && slNo.equalsIgnoreCase("01")){
                spnRth.setSelection(1);
                spnRth.setEnabled(false);

            }else{
                spnRth.setEnabled(true);
            }


        }
        catch(Exception  e)
        {
            Connection.MessageBox(Baseline_Member_list.this, e.getMessage());
            return;
        }

        dialog.show();
    }

     protected Dialog onCreateDialog(int id) {
        final Calendar c = Calendar.getInstance();
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mDateSetListener,g.mYear,g.mMonth-1,g.mDay);
        }
        return null;
     }


     private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year; mMonth = monthOfYear+1; mDay = dayOfMonth;
            EditText dtpDate;
            dtpDate = (EditText)findViewById(R.id.dtpFDate);
            if (VariableID.equals("dtpFDate"))
            {
                dtpDate = (EditText)findViewById(R.id.dtpFDate);
            }
            else if (VariableID.equals("dtpTDate"))
            {
                dtpDate = (EditText)findViewById(R.id.dtpTDate);
            }
            dtpDate.setText(new StringBuilder()
                .append(Global.Right("00"+mDay,2)).append("/")
                .append(Global.Right("00"+mMonth,2)).append("/")
                .append(mYear));
        }
     };


}