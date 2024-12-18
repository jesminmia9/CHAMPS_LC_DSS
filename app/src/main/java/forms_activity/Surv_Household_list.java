package forms_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.icddrb.champs_lc_dss.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Common.Connection;
import Common.Global;
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import forms_datamodel.Household_DataModel;

public class Surv_Household_list extends AppCompatActivity {
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
     final int DATE_DIALOG = 1;
     final int TIME_DIALOG = 2;

    Connection C;
    Global g;
    private List<Household_DataModel> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
     String TableName;

    TextView lblHeading;
    TextView lblTotal;
    Button btnAdd;
    ImageButton btnSearch;
    EditText txtSearch;
    EditText dtpFDate;
    EditText dtpTDate;
    Button btnGPSBari;

    TextView lblCompound;
    TextView lblCompoundAddr;
    Button btnBariUpdate;

    Bundle IDbundle;
     String STARTTIME = "";
     String DEVICEID  = "";

     String ENTRYUSER = "";

    ConstraintLayout ll_no_data;
     String HHID = "";
     String COMPOUNDID = "";
     String VillID = "";
     String HHNO = "";

     String COMPOUNDCODE = "";
     String COMPOUNDNAME = "";
     String COMPOUNDADRS = "";
     String TOTALHH = "";
     String SurvType = "";

     String CLUSTER = "";
     String BLOCK = "";
     String ROUND = "";
     String VILLAGE_NAME = "";
    String ListingStatus = "1";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            setContentView(R.layout.surv_household_list);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            C = new Connection(this);
            g = Global.getInstance();
            STARTTIME = g.CurrentTime24();

            IDbundle = getIntent().getExtras();
            COMPOUNDID = IDbundle.getString("CompoundID");
            VillID = IDbundle.getString("VillID");
            VILLAGE_NAME = IDbundle.getString("villname");
            COMPOUNDCODE = IDbundle.getString("CompoundCode");
            COMPOUNDNAME = IDbundle.getString("CompoundName");
            COMPOUNDADRS = IDbundle.getString("CompoundAdrs");
            TOTALHH = IDbundle.getString("TotalHH");
            SurvType = IDbundle.getString("SurvType");

            CLUSTER = IDbundle.getString("cluster");
            BLOCK = IDbundle.getString("block");
            ROUND = IDbundle.getString("round");


            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");
            TableName = "Household";
            lblHeading = (TextView)findViewById(R.id.lblHeading);
            lblTotal = (TextView)findViewById(R.id.lblTotal);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String total_hh_entry = C.ReturnSingleValue("Select count(*)total from Household where CompoundID='" + COMPOUNDID + "' and isdelete='2' and active='1'");
                    C.SaveData("Update Compound set TotalHH='"+ total_hh_entry +"',upload='2',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where CompoundID='" + COMPOUNDID + "'");
                    finish();
                }});

            btnAdd = (Button) findViewById(R.id.btnAdd);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String total_hh_in_compound = C.ReturnSingleValue("Select TotalHH from Compound where CompoundID='" + COMPOUNDID + "'");
                    if (Global.isNullOrEmpty(total_hh_in_compound)) {
                        Connection.MessageBox(Surv_Household_list.this, "Number of household not found from this Compound !!.");
                        return;
                    }
                    if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
                        String total_hh_entry = C.ReturnSingleValue("Select count(*)total from Household where CompoundID='" + COMPOUNDID + "' and isdelete='2' and active='1'");
                        if (!Global.isNullOrEmpty(total_hh_entry)){
                            if(Integer.parseInt(total_hh_in_compound) <= Integer.parseInt(total_hh_entry)){
                                Connection.MessageBox(Surv_Household_list.this, "You can't enter more household where as total number of household is " + total_hh_in_compound + " in compound. \n\nPlease update data in compound form before entering more households.");
                                btnBariUpdate.requestFocus();
                                return;
                            }
                        }
                    }


                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("VillID", VillID);
                    IDbundle.putString("CompoundID", COMPOUNDID);
                    IDbundle.putString("compound_name", COMPOUNDNAME);
                    IDbundle.putString("CompoundCode", COMPOUNDCODE);
                    IDbundle.putString("HHID", "");
                    IDbundle.putString("HHNO", "");
                    IDbundle.putString("round", ROUND);
                    Intent intent = new Intent(getApplicationContext(), Surv_Household.class);
                    intent.putExtras(IDbundle);
                    startActivityForResult(intent, 1);
                }});

            btnBariUpdate = (Button) findViewById(R.id.btnBariUpdate);
            btnBariUpdate.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("VillID", VillID);
                    IDbundle.putString("CompoundID", COMPOUNDID);
                    IDbundle.putString("villname", VILLAGE_NAME);
                    IDbundle.putString("CompoundCode", COMPOUNDCODE);
                    IDbundle.putString("HHID", HHID);
                    IDbundle.putString("HHNO", HHNO);
                    IDbundle.putString("cluster", CLUSTER);
                    IDbundle.putString("block", BLOCK);
                    IDbundle.putString("round", ROUND);
                    Intent intent = new Intent(getApplicationContext(), Surv_Compound.class);
                    intent.putExtras(IDbundle);
                    startActivityForResult(intent, 1);
                }});

            btnGPSBari = (Button) findViewById(R.id.btnGPSBari);
            btnGPSBari.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("IDNO", "");
                    IDbundle.putString("VillID", VillID);
                    IDbundle.putString("COMPOUNDID", COMPOUNDID);
                    IDbundle.putString("COMPOUNDCODE", COMPOUNDCODE);
                    IDbundle.putString("GPSTYPE", "2");
                    IDbundle.putString("LMSL", "");
                    IDbundle.putString("round", ROUND);
                    Intent intent = new Intent(getApplicationContext(), GPS.class);
                    intent.putExtras(IDbundle);
                    startActivityForResult(intent, 1);

                }});


            txtSearch = (EditText)findViewById(R.id.txtSearch);

            btnSearch = (ImageButton) findViewById(R.id.btnSearch);
            btnSearch.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    DataSearch(COMPOUNDID);

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

            lblCompound=findViewById(R.id.lblCompound);
            lblCompoundAddr=findViewById(R.id.lblCompoundAddr);
            lblCompound.setText(COMPOUNDCODE+", "+COMPOUNDNAME);
            lblCompoundAddr.setText(COMPOUNDADRS);

            DataSearch(COMPOUNDID);


        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Household_list.this, e.getMessage());
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        DataSearch(COMPOUNDID);
        //ListingStatus = "1";
        if(C.Existence("Select * from GPS where CompoundID = '"+COMPOUNDID+"' and GPSType=2 and GPSStatus=1")){
            btnGPSBari.setBackgroundResource(R.drawable.style_completed_square_shape);
            btnGPSBari.setTextColor(Color.WHITE);
        }else{
            btnGPSBari.setBackgroundResource(R.drawable.style_not_completed_square_shape);
            btnGPSBari.setTextColor(Color.BLACK);
            ListingStatus = "9";
        }
    }

    @Override
    protected void onPause() {
        //Update data collection status
        try {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        String status = C.SaveData("Update Compound set ListingStatus='" + ListingStatus + "',upload='2' where CompoundID='" + COMPOUNDID + "'");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        } catch (Exception ex) {
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

        }
    }

    private void DataSearch(String CompoundID)
    {
        try
        {
            Household_DataModel d = new Household_DataModel();
            String SQL = "Select h.HHID ,h.VillID ,h.CompoundID ,h.HHNO, h.HHHead,h.MobileNo1,h.MobileNo2,ifnull(ev.VisitNote,'')HHNote,ifnull(ev.VisitStatus,'')VisitStatus," +
                    " (case when h.TotMem is null or length(ifnull(h.TotMem,''))=0 then 0 else h.TotMem end) TotMem," +
                    " ifnull(m.regis_mem,0)regis_mem, '' BaselineStatus, ifnull(h.HHExType,'')ExType  " +
                    " from "+ TableName +" h\n" +
                    " left outer join (Select v.* from Visits v inner join (\n" +
                    "   Select HHID,Max(cast(VisitNo as int))VisitNo from Visits where Rnd='"+ ROUND +"' group by HHID)v1 on v.HHID=v1.HHID and cast(v.VisitNo as int)=cast(v1.VisitNo as int))ev \n" +
                    "   on h.HHID=ev.HHID and ev.Rnd='"+ ROUND +"'\n" +
                    " left outer join (select HHID,count(*)regis_mem from Member where isdelete=2 and Active=1 group by HHID) m on h.HHID=m.HHID" +
                    " Where h.CompoundID='"+ CompoundID +"' and h.isdelete=2 " +
                    " and (" +
                    "   h.HHNO like('"+ txtSearch.getText().toString() +"%') " +
                    "   or h.HHHead like('%"+ txtSearch.getText().toString() +"%')" +
                    "   or h.MobileNo1 like('%"+ txtSearch.getText().toString() +"%')" +
                    "   or h.MobileNo2 like('%"+ txtSearch.getText().toString() +"%')" +
                    "   )" +
                    " order by h.HHNO asc";
            List<Household_DataModel> data = d.SelectHHList(this, SQL);

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
                Connection.MessageBox(Surv_Household_list.this,ex.getMessage());
            }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Household_list.this, e.getMessage());
            return;
        }
    }

    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
        private List<Household_DataModel> dataList;
        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout secListRow;
            TextView HHNO;
            TextView HHHead;
            TextView MobileNo1;
            TextView MobileNo2;
            TextView TotMem;
            TextView VisitStatus;
            TextView HHNote;
            ImageView iv_next;
            CardView card_view;

            Button btnVisit;
            public MyViewHolder(View convertView) {
                super(convertView);
                secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
                HHNO = (TextView)convertView.findViewById(R.id.HHNO);
                HHHead = (TextView)convertView.findViewById(R.id.HHHead);
                MobileNo1 = (TextView)convertView.findViewById(R.id.MobileNo1);
                VisitStatus = (TextView)convertView.findViewById(R.id.VisitStatus);
                HHNote = (TextView)convertView.findViewById(R.id.HHNote);
                btnVisit = (Button)convertView.findViewById(R.id.btnVisit);
                iv_next = (ImageView) convertView.findViewById(R.id.iv_next);
                card_view = convertView.findViewById(R.id.card_view);
            }
        }
        public DataAdapter(List<Household_DataModel> datalist) {
            this.dataList = datalist;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.surv_household_row, parent, false);
            return new MyViewHolder(itemView);
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final Household_DataModel data = dataList.get(position);
            holder.HHNO.setText(data.getHHNO());
            holder.HHHead.setText(data.getHHHead());
            String mob = "";
            if(data.getMobileNo1().length()>0) mob=data.getMobileNo1();
            if(data.getMobileNo2().length()>0){
                mob = mob.length()>0? mob+", "+data.getMobileNo2():data.getMobileNo2();
            }
            holder.MobileNo1.setText("Mobile: "+ mob);
            if(mob.length()>0) holder.MobileNo1.setVisibility(View.VISIBLE); else holder.MobileNo1.setVisibility(View.GONE);
            if(data.getHHNote().length()>0) holder.HHNote.setVisibility(View.VISIBLE); else holder.HHNote.setVisibility(View.GONE);

            holder.HHNote.setText("Notes: "+ data.getHHNote());
            holder.btnVisit.setText("Visit\nM: "+ data.getregis_mem());

            //holder.btnVisit.setText("Visit\nM: "+data.getregis_mem()+"/"+data.getTotMem());


            switch (data.getVisitStatus()) {
                case "01":
                case "02":
                case "03":
                    holder.btnVisit.setBackgroundResource(R.drawable.style_completed_square_shape);
                    holder.btnVisit.setTextColor(Color.WHITE);
                    ListingStatus = "1";
                    break;
                case "04":
                case "05":
                case "07":
                case "08":
                case "09":
                case "10":
                case "11":
                    holder.btnVisit.setBackgroundResource(R.drawable.style_circle_line_incomplete);
                    holder.btnVisit.setTextColor(Color.BLACK);
                    ListingStatus = "2";
                    break;
                case "97":
                    holder.btnVisit.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                    holder.btnVisit.setTextColor(Color.BLACK);
                    ListingStatus = "2";
                    break;
                default:
                    holder.btnVisit.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                    holder.btnVisit.setTextColor(Color.BLACK);
                    ListingStatus = "9";
                    break;
            }


            switch (data.getVisitStatus()) {
                case "01":
                    holder.VisitStatus.setText("Interview start");
                    break;
                case "02":
                    holder.VisitStatus.setText("Interview completed");
                    break;
                case "03":
                    holder.VisitStatus.setText("Interview Incomplete");
                    break;
                case "04":
                    holder.VisitStatus.setText("No member or suitable person found during house visit");
                    break;
                case "05":
                    holder.VisitStatus.setText("All members absent for many days");
                    break;
                case "07":
                    holder.VisitStatus.setText("Refused/Reluctance to interview");
                    break;
                case "08":
                    holder.VisitStatus.setText("House vacant or address not of any household");
                    break;
                case "09":
                    holder.VisitStatus.setText("Address not of any residence");
                    break;
                case "10":
                    holder.VisitStatus.setText("Residential Destroyed");
                    break;
                case "11":
                    holder.VisitStatus.setText("Dwelling not found");
                    break;
                case "77":
                    holder.VisitStatus.setText("Entire household migrated-out");
                    break;
                case "97":
                    holder.VisitStatus.setText("Others");
                    break;
            }

            if(data.getExType().length()>0){
                holder.card_view.setBackgroundResource(R.drawable.style_circle_line_incomplete_member);
                holder.btnVisit.setEnabled(false);
                holder.secListRow.setEnabled(false);
            }else{
                holder.card_view.setBackgroundResource(R.drawable.style_circle_line_white);
                holder.btnVisit.setEnabled(true);
                holder.secListRow.setEnabled(true);
            }


            holder.btnVisit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(Surv_Household_list.this, "", "Please Wait . . .", true);
                    new Thread() {
                        public void run() {
                            try {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("HHID", data.getHHID());
                                IDbundle.putString("HHNO", data.getHHNO());
                                IDbundle.putString("VillID", data.getVillID());
                                IDbundle.putString("CompoundID", data.getCompoundID());
                                IDbundle.putString("CompoundName", COMPOUNDNAME);
                                IDbundle.putString("CompoundAdrs", COMPOUNDADRS);
                                IDbundle.putString("HHHead", data.getHHHead());
                                IDbundle.putString("VisitNo", "");
                                IDbundle.putString("round", ROUND);
                                IDbundle.putString("SurvType", SurvType);
                                Intent f1 = new Intent(getApplicationContext(), Surv_Visits.class);
                                f1.putExtras(IDbundle);
                                startActivityForResult(f1,1);
                            } catch (Exception e) {
                            }
                            progDailog.dismiss();

                        }
                    }.start();
                }
            });

            holder.secListRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(Surv_Household_list.this, "", "Please Wait . . .", true);
                    new Thread() {
                        public void run() {
                            try {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("VillID", data.getVillID());
                                IDbundle.putString("CompoundID", data.getCompoundID());
                                IDbundle.putString("compound_name", COMPOUNDNAME);
                                IDbundle.putString("HHID", data.getHHID());
                                IDbundle.putString("HHNO", data.getHHNO());
                                Intent f1 = new Intent(getApplicationContext(), Surv_Household.class);
                                f1.putExtras(IDbundle);
                                startActivityForResult(f1,1);
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