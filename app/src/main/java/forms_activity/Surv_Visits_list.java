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
 import forms_datamodel.Visits_DataModel;
import androidx.recyclerview.widget.RecyclerView;
 import androidx.appcompat.app.AppCompatActivity;
 import android.content.res.TypedArray;
 import android.graphics.Canvas;
 import android.graphics.Rect;
 import android.graphics.drawable.Drawable;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import android.view.GestureDetector;
 import androidx.recyclerview.widget.DefaultItemAnimator;
 import android.widget.EditText;
 import android.view.WindowManager;
 import Utility.*;
 import java.util.Calendar;
 import android.widget.DatePicker;
 import androidx.constraintlayout.widget.ConstraintLayout;

import org.icddrb.champs_lc_dss.R;

public class Surv_Visits_list extends AppCompatActivity {
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
    private List<Visits_DataModel> dataList = new ArrayList<>();
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
     String ROUND = "";
     String VISITNO = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.surv_visits_list);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         C = new Connection(this);
         g = Global.getInstance();
         STARTTIME = g.CurrentTime24();

         IDbundle = getIntent().getExtras();
         DEVICEID = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");
         HHID = IDbundle.getString("HHID");
         ROUND = IDbundle.getString("round");
         TableName = "Visits";
         lblHeading = (TextView)findViewById(R.id.lblHeading);
         lblTotal = (TextView)findViewById(R.id.lblTotal);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 finish();
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

        DataSearch(HHID,ROUND);


     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Visits_list.this, e.getMessage());
         return;
     }
 }
 
 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if (resultCode == Activity.RESULT_CANCELED) {
         //Write your code if there's no result
     } else {
         DataSearch(HHID,ROUND);
     }
 }

 private void DataSearch(String hhid, String round)
     {
       try
        {
     
           Visits_DataModel d = new Visits_DataModel();
             String SQL = "Select * from "+ TableName +"  Where HHID ='"+hhid+"' and Rnd="+round+" and isdelete=2";
             List<Visits_DataModel> data = d.SelectAll(this, SQL);
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
                 Connection.MessageBox(Surv_Visits_list.this,ex.getMessage());
             }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Visits_list.this, e.getMessage());
            return;
        }
     }



     public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
         private List<Visits_DataModel> dataList;
         public class MyViewHolder extends RecyclerView.ViewHolder {
         LinearLayout secListRow;
         TextView VisitNo;
         TextView VisitDate;
         TextView VisitStatus;
         public MyViewHolder(View convertView) {
             super(convertView);
             secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
             VisitNo = (TextView)convertView.findViewById(R.id.VisitNo);
             VisitDate = (TextView)convertView.findViewById(R.id.VisitDate);
             VisitStatus = (TextView)convertView.findViewById(R.id.VisitStatus);
             }
         }
         public DataAdapter(List<Visits_DataModel> datalist) {
             this.dataList = datalist;
         }
         @Override
         public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.surv_visits_row, parent, false);
             return new MyViewHolder(itemView);
         }
         @Override
         public void onBindViewHolder(MyViewHolder holder, int position) {
             final Visits_DataModel data = dataList.get(position);
             holder.VisitNo.setText(data.getVisitNo());
             holder.VisitDate.setText(Global.DateConvertDMY(data.getVisitDate()));
             if(data.getVisitStatus().equals("01") || data.getVisitStatus().equals("1")) {
                 holder.VisitStatus.setText("Interview Complete");
             }
             else if(data.getVisitStatus().equals("02") || data.getVisitStatus().equals("2")) {
                 holder.VisitStatus.setText("No member or suitable person found during house visit");
             }
             else if(data.getVisitStatus().equals("03") || data.getVisitStatus().equals("3")) {
                 holder.VisitStatus.setText("All members absent for many days");
             }
             else if(data.getVisitStatus().equals("04") || data.getVisitStatus().equals("4")) {
                 holder.VisitStatus.setText("Reluctance to Canceled");
             }
             else if(data.getVisitStatus().equals("05") || data.getVisitStatus().equals("5")) {
                 holder.VisitStatus.setText("Reluctance to interview");
             }
             else if(data.getVisitStatus().equals("06") || data.getVisitStatus().equals("6")) {
                 holder.VisitStatus.setText("House vacant or address not of any residence");
             }
             else if(data.getVisitStatus().equals("07") || data.getVisitStatus().equals("7")) {
                 holder.VisitStatus.setText("Residential Destroyed");
             }
             else if(data.getVisitStatus().equals("08") || data.getVisitStatus().equals("8")) {
                 holder.VisitStatus.setText("Dwelling not found");
             }
             else if(data.getVisitStatus().equals("09") || data.getVisitStatus().equals("9")) {
                 holder.VisitStatus.setText("Other");
             }


//             holder.secListRow.setOnClickListener(new View.OnClickListener() {
//                 public void onClick(View v) {
//                     final ProgressDialog progDailog = ProgressDialog.show(Surv_Visits_list.this, "", "Please Wait . . .", true);
//                     new Thread() {
//                         public void run() {
//                             try {
//                                 Bundle IDbundle = new Bundle();
//                                 IDbundle.putString("HHID", data.getHHID());
//                                 IDbundle.putString("VisitNo", data.getVisitNo());
//                                 Intent f1 = new Intent(getApplicationContext(), Visits.class);
//                                 f1.putExtras(IDbundle);
//                                 startActivityForResult(f1,1);
//                             } catch (Exception e) {
//                             }
//                             progDailog.dismiss();
//                         }
//                     }.start();
//                 }
//             });
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