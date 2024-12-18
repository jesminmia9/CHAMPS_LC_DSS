package forms_activity;

import java.util.ArrayList;
 import java.util.List;
 import android.app.*;
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

import android.widget.EditText;
 import android.view.WindowManager;
 import Utility.*;
import forms_datamodel.tmpHousehold_DataModel;

import java.util.Calendar;
 import android.widget.DatePicker;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.DefaultItemAnimator;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;
 import androidx.constraintlayout.widget.ConstraintLayout;

import org.icddrb.champs_lc_dss.R;

public class Household_list extends AppCompatActivity {
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
    private List<tmpHousehold_DataModel> dataList = new ArrayList<>();
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
    Button btnGPSBari;

    Bundle IDbundle;
    static String STARTTIME = "";
    static String DEVICEID  = "";

    static String ENTRYUSER = "";

    ConstraintLayout ll_no_data;
    static String HHID = "";
    static String CompoundID = "";
    static String VillID = "";
    static String HHNO = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.household_list);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         C = new Connection(this);
         g = Global.getInstance();
         STARTTIME = g.CurrentTime24();

         IDbundle = getIntent().getExtras();
         CompoundID = IDbundle.getString("CompoundID");
         VillID = IDbundle.getString("VillID");

         DEVICEID = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");
         TableName = "Household";
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
                         Bundle IDbundle = new Bundle();
                         IDbundle.putString("VillID", VillID);
                         IDbundle.putString("CompoundID", CompoundID);
                         IDbundle.putString("HHID", HHID);
                         IDbundle.putString("HHNO", HHNO);
                         Intent intent = new Intent(getApplicationContext(), Household.class);
                         intent.putExtras(IDbundle);
                         startActivityForResult(intent, 1);
             }});


         btnGPSBari = (Button) findViewById(R.id.btnGPSBari);
         btnGPSBari.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
                 Bundle IDbundle = new Bundle();
//                 IDbundle.putString("CompoundID", "");
                 IDbundle.putString("VillID", VillID);
                 IDbundle.putString("CompoundID", CompoundID);
                 Intent intent = new Intent(getApplicationContext(), GPSCompound.class);
                 intent.putExtras(IDbundle);
                 startActivityForResult(intent, 1);

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


        DataSearch(CompoundID);


     }
     catch(Exception  e)
     {
         Connection.MessageBox(Household_list.this, e.getMessage());
         return;
     }
 }
 
 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if (resultCode == Activity.RESULT_CANCELED) {
         //Write your code if there's no result
     } else {
         DataSearch(CompoundID);
     }
 }

 private void DataSearch(String CompoundID)
     {
       try
        {
           tmpHousehold_DataModel d = new tmpHousehold_DataModel();
//             String SQL = "Select * from "+ TableName +"  Where CompoundID='"+ CompoundID +"' and isdelete=2";
            String SQL = "Select h.HHID ,h.VillID ,h.CompoundID ,h.HHNO, h.HHHead,h.MobileNo1,h.MobileNo2,h.HHNote,ifnull(ev.VisitStatus,'')VisitStatus," +
                    " (case when h.TotMem is null or length(ifnull(h.TotMem,''))=0 then 0 else h.TotMem end) TotMem " +
                    " from Household h\n" +
                    " left outer join (Select v.* from Visits v inner join (\n" +
                    " Select HHID,max(VisitNo)VisitNo from Visits group by HHID)v1 on v.HHID=v1.HHID and v.VisitNo=v1.VisitNo )ev \n" +
                    " on h.HHID=ev.HHID \n" +
                    " Where h.CompoundID='"+ CompoundID +"' and h.isdelete=2 " +
                    " order by h.CompoundID";
            List<tmpHousehold_DataModel> data = d.SelectHHList(this, SQL);
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
                 Connection.MessageBox(Household_list.this,ex.getMessage());
             }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Household_list.this, e.getMessage());
            return;
        }
     }

     public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
         private List<tmpHousehold_DataModel> dataList;
         public class MyViewHolder extends RecyclerView.ViewHolder {
         LinearLayout secListRow;
         TextView HHNO;
         TextView HHHead;
         TextView MobileNo1;
         TextView MobileNo2;
         TextView TotMem;

             Button btnVisit;
         public MyViewHolder(View convertView) {
             super(convertView);
             secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
//             HHID = (TextView)convertView.findViewById(R.id.HHID);
//             VillID = (TextView)convertView.findViewById(R.id.VillID);
//             CompoundID = (TextView)convertView.findViewById(R.id.CompoundID);
             HHNO = (TextView)convertView.findViewById(R.id.HHNO);
             HHHead = (TextView)convertView.findViewById(R.id.HHHead);
             MobileNo1 = (TextView)convertView.findViewById(R.id.MobileNo1);
             btnVisit = (Button)convertView.findViewById(R.id.btnVisit);
             }
         }
         public DataAdapter(List<tmpHousehold_DataModel> datalist) {
             this.dataList = datalist;
         }
         @Override
         public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.household_row, parent, false);
             return new MyViewHolder(itemView);
         }
         @Override
         public void onBindViewHolder(MyViewHolder holder, int position) {
             final tmpHousehold_DataModel data = dataList.get(position);
//             holder.HHID.setText(data.getHHID());
             holder.HHNO.setText(data.getHHNO());
             holder.HHHead.setText(data.getHHHead());
             holder.MobileNo1.setText(data.getMobileNo1() +" , " +data.getMobileNo2());

            if(data.getVisitStatus().length() >0 ){
                 holder.btnVisit.setBackgroundResource(R.drawable.button_style_circle_line_theme_back);
                 holder.btnVisit.setTextColor(Color.WHITE);
             }else if (data.getVisitStatus().length() ==0 ){
                holder.btnVisit.setBackgroundResource(R.drawable.button_style);
                holder.btnVisit.setTextColor(Color.BLACK);
             }

//             holder.btnVisit.setBackgroundResource(R.drawable.button_style_circle_line_theme_back);
             holder.btnVisit.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(Household_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("HHID", data.getHHID());
                                 IDbundle.putString("VillID", data.getVillID());
                                 IDbundle.putString("CompoundID", data.getCompoundID());
                                 IDbundle.putString("VisitNo", "");
                                 Intent f1 = new Intent(getApplicationContext(), Visits.class);
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
                     final ProgressDialog progDailog = ProgressDialog.show(Household_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("VillID", data.getVillID());
                                 IDbundle.putString("CompoundID", data.getCompoundID());
                                 IDbundle.putString("HHID", data.getHHID());
                                 IDbundle.putString("HHNO", data.getHHNO());
                                 Intent f1 = new Intent(getApplicationContext(), Household.class);
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