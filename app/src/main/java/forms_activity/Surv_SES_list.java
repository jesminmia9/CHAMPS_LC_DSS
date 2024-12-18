package forms_activity;

import java.util.ArrayList;
 import java.util.List;
 import android.app.*;
 import android.app.AlertDialog;
 import android.content.Context;
 import android.content.DialogInterface;
 import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.util.Log;
import android.view.KeyEvent;
 import android.os.Bundle;
 import android.view.View;
 import android.view.MotionEvent;
 import android.view.ViewGroup;
 import android.view.LayoutInflater;
 import android.widget.LinearLayout;
 import android.widget.TextView;
 import android.widget.Button;
 import android.widget.ImageButton;
 import Common.*;
 import forms_datamodel.SES_DataModel;
 import android.content.res.TypedArray;
 import android.graphics.Canvas;
 import android.graphics.Rect;
 import android.graphics.drawable.Drawable;
 import android.view.GestureDetector;
 import android.widget.EditText;
 import android.view.WindowManager;
 import Utility.*;
 import java.util.Calendar;
 import android.widget.DatePicker;
 import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;
 import androidx.constraintlayout.widget.ConstraintLayout;

import org.icddrb.champs_lc_dss.R;

public class Surv_SES_list extends AppCompatActivity {
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
    private List<SES_DataModel> dataList = new ArrayList<>();
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

     String STARTTIME = "";
     String DEVICEID  = "";

     String ENTRYUSER = "";

    ConstraintLayout ll_no_data;
    Bundle IDbundle;
     String HHID = "";
     String SESNO = "";
     String SurvType = "";
     Integer TOTAL = 0;
     String ROUND = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.surv_ses_list);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         C = new Connection(this);
         g = Global.getInstance();
         STARTTIME = g.CurrentTime24();

         DEVICEID = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         HHID = IDbundle.getString("HHID");
         SESNO = IDbundle.getString("SESNo");
         SurvType = IDbundle.getString("SurvType");
         ROUND = IDbundle.getString("round");

         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
             TableName = "tmpSES_Mali";
         }else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
             TableName = "tmpSES_CrossRiver";
         }else{
             TableName = "tmpSES";
         }

         lblHeading = (TextView)findViewById(R.id.lblHeading);
         lblTotal = (TextView)findViewById(R.id.lblTotal);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 finish();
             }});

         btnAdd = (Button) findViewById(R.id.btnAdd);
         btnAdd.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
                 String lastSesDuration = C.ReturnSingleValue("Select Cast((cast((ifnull(cast((julianday(date('now'))-julianday(date(SESVDate))) as int),0)) as int)/30.4375) as int)ses_dur from "+ TableName +"  Where HHID='"+ HHID +"' and isdelete=2 order by SESVDate desc limit 1");
                 if(lastSesDuration.length() == 0){
                     Bundle IDbundle = new Bundle();
                     IDbundle.putString("HHID", HHID);
                     IDbundle.putString("SESNo", "");
                     IDbundle.putString("SurvType", "2");
                     IDbundle.putString("TotalSES", ""+TOTAL);
                     IDbundle.putString("round", ROUND);
                     Intent intent = null;
                     if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                         intent = new Intent(getApplicationContext(), Surv_SES_Mali.class);
                     }else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
                         intent = new Intent(getApplicationContext(), Surv_SES_CrossRiver.class);
                     }else{
                         intent = new Intent(getApplicationContext(), Surv_SES.class);
                     }

                     intent.putExtras(IDbundle);
                     startActivityForResult(intent, 1);
                 }
                 else{
                     if(Integer.parseInt(lastSesDuration) < ProjectSetting.SES_DURATION_MONTHS){
                         Connection.MessageBox(Surv_SES_list.this, "SES can not be collected before 36 months.");
                         return;
                     }
                     else{
                         Bundle IDbundle = new Bundle();
                         IDbundle.putString("HHID", HHID);
                         IDbundle.putString("SESNo", "");
                         IDbundle.putString("SurvType", "2");
                         IDbundle.putString("TotalSES", ""+TOTAL);
                         Intent intent = null;
                         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                             intent = new Intent(getApplicationContext(), Surv_SES_Mali.class);
                         }else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
                             intent = new Intent(getApplicationContext(), Surv_SES_CrossRiver.class);
                         }else{
                             intent = new Intent(getApplicationContext(), Surv_SES.class);
                         }
                         intent.putExtras(IDbundle);
                         startActivityForResult(intent, 1);
                     }
                 }


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


        DataSearch(HHID);


     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_SES_list.this, e.getMessage());
         return;
     }
 }
 
 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if (resultCode == Activity.RESULT_CANCELED) {
         //Write your code if there's no result
     } else {
         DataSearch(HHID);
     }
 }

 private void DataSearch(String HHID)
     {
       try
        {
     
           SES_DataModel d = new SES_DataModel();
//             String SQL = "Select * from "+ TableName +"  Where HHID like('"+ SearchText +"%') or SESNo like('"+ SearchText +"%') and isdelete=2";
             String SQL = "Select *,Cast((cast((ifnull(cast((julianday(date('now'))-julianday(date(SESVDate))) as int),0)) as int)/30.4375) as int)ses_dur from "+ TableName +"  Where HHID='"+ HHID +"' and isdelete=2";
             List<SES_DataModel> data = d.SelectAll_List(this, SQL);
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
                 TOTAL = dataList.size();
             }catch ( Exception ex){
                 Connection.MessageBox(Surv_SES_list.this,ex.getMessage());
             }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_SES_list.this, e.getMessage());
            return;
        }
     }



     public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
         private List<SES_DataModel> dataList;
         public class MyViewHolder extends RecyclerView.ViewHolder {
         LinearLayout secListRow;
         TextView HHID;
         TextView SESNo;
         TextView SESVDate;
         TextView SESVStatus;
         CardView card_view;

         TextView SESDur;
         public MyViewHolder(View convertView) {
             super(convertView);
             secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
             card_view = (CardView)convertView.findViewById(R.id.card_view);
             HHID = (TextView)convertView.findViewById(R.id.HHID);
             SESNo = (TextView)convertView.findViewById(R.id.SESNo);
             SESVDate = (TextView)convertView.findViewById(R.id.SESVDate);
             SESVStatus = (TextView)convertView.findViewById(R.id.SESVStatus);

             SESDur = (TextView)convertView.findViewById(R.id.SESDur);
             }
         }
         public DataAdapter(List<SES_DataModel> datalist) {
             this.dataList = datalist;
         }
         @Override
         public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.surv_ses_row, parent, false);
             return new MyViewHolder(itemView);
         }
         @Override
         public void onBindViewHolder(MyViewHolder holder, int position) {
             final SES_DataModel data = dataList.get(position);
             holder.HHID.setText(data.getHHID());
             holder.SESNo.setText(data.getSESNo());
             holder.SESVDate.setText(Global.DateConvertDMY(data.getSESVDate()));
             holder.SESVStatus.setText(data.getSESVStatus());

             holder.SESDur.setText(data.getses_dur()+" mon");

             if(Integer.parseInt(data.getses_dur()) <= ProjectSetting.SES_DURATION_MONTHS){
                 if(data.getSESVStatus().equals("Completed")){
                     holder.card_view.setBackgroundResource(R.drawable.style_completed_square_shape);
                 }
                 else if (data.getSESVStatus().equals("Partially Completed")) {
                     holder.card_view.setBackgroundResource(R.drawable.style_square_shape_yellow);
                 }
                 else if (data.getSESVStatus().equals("Refused")) {
                     holder.card_view.setBackgroundResource(R.drawable.button_style_red);
                 }
                 ;

             }else{
                 holder.card_view.setBackgroundResource(R.drawable.style_circle_line_incomplete);
             }

             holder.secListRow.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(Surv_SES_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("HHID", data.getHHID());
                                 IDbundle.putString("SESNo", data.getSESNo());
                                 IDbundle.putString("SurvType", "2");
                                 IDbundle.putString("TotalSES", "");
                                 IDbundle.putString("round", data.getRnd());
                                 Intent intent = null;
                                 if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                                     intent = new Intent(getApplicationContext(), Surv_SES_Mali.class);
                                 }else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
                                     intent = new Intent(getApplicationContext(), Surv_SES_CrossRiver.class);
                                 }else{
                                     intent = new Intent(getApplicationContext(), Surv_SES.class);
                                 }
                                 intent.putExtras(IDbundle);
                                 startActivityForResult(intent,1);
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