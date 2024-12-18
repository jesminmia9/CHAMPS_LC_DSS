package forms_activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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
import Utility.MySharedPreferences;
import forms_datamodel.DeathNotifi_DataModel;

public class Surv_DeathNotifi_list extends AppCompatActivity {
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
    private List<DeathNotifi_DataModel> dataList = new ArrayList<>();
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

    static String STARTTIME = "";
    static String DEVICEID  = "";

    static String ENTRYUSER = "";

    ConstraintLayout ll_no_data;
    static String DTHID = "";
    Bundle IDbundle;
    static String HHID = "";
    static String HHNO = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.surv_deathnotifi_list);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         C = new Connection(this);
         g = Global.getInstance();
         STARTTIME = g.CurrentTime24();

         DEVICEID = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");
         TableName = "DeathNotifi";
         lblHeading = (TextView)findViewById(R.id.lblHeading);
         lblTotal = (TextView)findViewById(R.id.lblTotal);
         IDbundle = getIntent().getExtras();
         HHID = IDbundle.getString("HHID");
         HHNO = IDbundle.getString("HHNO");

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 finish();
             }});

         btnAdd = (Button) findViewById(R.id.btnAdd);
         btnAdd.setOnClickListener(new View.OnClickListener() {

             public void onClick(View view) {
                 Bundle IDbundle = new Bundle();
                 IDbundle.putString("DthID", "");
                 IDbundle.putString("HHID", HHID);
                 IDbundle.putString("HHNO", HHNO);
                 IDbundle.putString("DSSID", "");
                 Intent intent = new Intent(getApplicationContext(), Surv_DeathNotifi.class);
                 intent.putExtras(IDbundle);
                 startActivityForResult(intent, 1);

                 /*String total_death_in_household = C.ReturnSingleValue("select ifnull(TotalDeath,0)totaldeath from Visits where HHID='"+ HHID +"' and isdelete='2' order by cast(VisitNo as int) desc limit 1");
                 String total_death_entry = C.ReturnSingleValue("Select count(*)total from DeathNotifi where HHID='"+ HHID +"' and isdelete='2'");
                 if (!Global.isNullOrEmpty(total_death_entry)) {
                     if (Integer.parseInt(total_death_in_household)<= Integer.parseInt(total_death_entry)) {
                         Connection.MessageBox(Surv_DeathNotifi_list.this, "You can't enter more death information where as total number of death is " + total_death_in_household + " in household. \n\nPlease update data in household visit form before entering more deaths.");
                     } else {

                     }
                 }*/
             }});

         txtSearch = (EditText)findViewById(R.id.txtSearch);

         btnSearch = (ImageButton) findViewById(R.id.btnSearch);
         btnSearch.setOnClickListener(new View.OnClickListener() {

             public void onClick(View view) {
                         DataSearch(txtSearch.getText().toString());

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


        DataSearch(txtSearch.getText().toString());


     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_DeathNotifi_list.this, e.getMessage());
         return;
     }
 }
 
 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if (resultCode == Activity.RESULT_CANCELED) {
         //Write your code if there's no result
     } else {
         DataSearch(txtSearch.getText().toString());
     }
 }

 private void DataSearch(String SearchText)
     {
       try
        {
     
           DeathNotifi_DataModel d = new DeathNotifi_DataModel();
             String SQL = "Select * from "+ TableName +"  Where HHID='"+ HHID +"' and DthID like('"+ SearchText +"%') and isdelete=2";
             List<DeathNotifi_DataModel> data = d.SelectAll(this, SQL);
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
                 Connection.MessageBox(Surv_DeathNotifi_list.this,ex.getMessage());
             }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_DeathNotifi_list.this, e.getMessage());
            return;
        }
     }



     public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
         private List<DeathNotifi_DataModel> dataList;
         public class MyViewHolder extends RecyclerView.ViewHolder {
         LinearLayout secListRow;
         /*TextView DthID;
         TextView HHID;
         TextView MortalityAgeG;*/
         TextView DeceasedName;
         //TextView MemID;
         TextView DOD;
         //TextView DODDk;
         TextView DOB;
         /*TextView DOBDk;
         TextView DAgeDay;
         TextView DAgeM;
         TextView DAgeY;
         TextView DAgeYDk;
         TextView POD;
         TextView PODOth;
         TextView PODNm;
         TextView PODNmDk;
         TextView CODToldHCP;
         TextView MainCOD;
         TextView MainCODDk;
         TextView MainCODThink;
         TextView MainCODThinkDk;*/
         public MyViewHolder(View convertView) {
             super(convertView);
             secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
             //DthID = (TextView)convertView.findViewById(R.id.DthID);
             //HHID = (TextView)convertView.findViewById(R.id.HHID);
             //MortalityAgeG = (TextView)convertView.findViewById(R.id.MortalityAgeG);
             DeceasedName = (TextView)convertView.findViewById(R.id.DeceasedName);
             //MemID = (TextView)convertView.findViewById(R.id.MemID);
             DOD = (TextView)convertView.findViewById(R.id.DOD);
             //DODDk = (TextView)convertView.findViewById(R.id.DODDk);
             DOB = (TextView)convertView.findViewById(R.id.DOB);
             /*DOBDk = (TextView)convertView.findViewById(R.id.DOBDk);
             DAgeDay = (TextView)convertView.findViewById(R.id.DAgeDay);
             DAgeM = (TextView)convertView.findViewById(R.id.DAgeM);
             DAgeY = (TextView)convertView.findViewById(R.id.DAgeY);
             DAgeYDk = (TextView)convertView.findViewById(R.id.DAgeYDk);
             POD = (TextView)convertView.findViewById(R.id.POD);
             PODOth = (TextView)convertView.findViewById(R.id.PODOth);
             PODNm = (TextView)convertView.findViewById(R.id.PODNm);
             PODNmDk = (TextView)convertView.findViewById(R.id.PODNmDk);
             CODToldHCP = (TextView)convertView.findViewById(R.id.CODToldHCP);
             MainCOD = (TextView)convertView.findViewById(R.id.MainCOD);
             MainCODDk = (TextView)convertView.findViewById(R.id.MainCODDk);
             MainCODThink = (TextView)convertView.findViewById(R.id.MainCODThink);
             MainCODThinkDk = (TextView)convertView.findViewById(R.id.MainCODThinkDk);*/
             }
         }
         public DataAdapter(List<DeathNotifi_DataModel> datalist) {
             this.dataList = datalist;
         }
         @Override
         public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.deathnotifi_row, parent, false);
             return new MyViewHolder(itemView);
         }
         @Override
         public void onBindViewHolder(MyViewHolder holder, int position) {
             final DeathNotifi_DataModel data = dataList.get(position);
             /*holder.DthID.setText(data.getDthID());
             holder.HHID.setText(data.getHHID());
             holder.MortalityAgeG.setText(String.valueOf(data.getMortalityAgeG()));*/
             holder.DeceasedName.setText(data.getDeceasedName());
             //holder.MemID.setText(data.getMemID());
             holder.DOD.setText(Global.DateConvertDMY(data.getDOD()));
             //holder.DODDk.setText(String.valueOf(data.getDODDk()));
             holder.DOB.setText(Global.DateConvertDMY(data.getDOB()));
             /*holder.DOBDk.setText(String.valueOf(data.getDOBDk()));
             holder.DAgeDay.setText(String.valueOf(data.getDAgeDay()));
             holder.DAgeM.setText(String.valueOf(data.getDAgeM()));
             holder.DAgeY.setText(String.valueOf(data.getDAgeY()));
             holder.DAgeYDk.setText(String.valueOf(data.getDAgeYDk()));
             holder.POD.setText(data.getPOD());
             holder.PODOth.setText(data.getPODOth());
             holder.PODNm.setText(data.getPODNm());
             holder.PODNmDk.setText(String.valueOf(data.getPODNmDk()));
             holder.CODToldHCP.setText(String.valueOf(data.getCODToldHCP()));
             holder.MainCOD.setText(data.getMainCOD());
             holder.MainCODDk.setText(String.valueOf(data.getMainCODDk()));
             holder.MainCODThink.setText(data.getMainCODThink());
             holder.MainCODThinkDk.setText(String.valueOf(data.getMainCODThinkDk()));*/


             holder.secListRow.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(Surv_DeathNotifi_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("DthID", data.getDthID());
                                 IDbundle.putString("HHID", data.getHHID());
                                 IDbundle.putString("HHNO", HHNO);
                                 IDbundle.putString("DSSID", data.getDSSID());
                                 Intent f1 = new Intent(getApplicationContext(), DeathNotifi.class);
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