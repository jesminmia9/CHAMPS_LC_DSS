package forms_activity;

import java.util.ArrayList;
 import java.util.List;
 import android.app.*;
 import android.app.AlertDialog;
 import android.content.Context;
 import android.content.DialogInterface;
 import android.content.Intent;
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
 import forms_datamodel.cleaning_household_DataModel;
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
 import android.widget.Toast;

import org.icddrb.champs_lc_dss.R;

public class cleaning_household_list extends AppCompatActivity {
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
    private List<cleaning_household_DataModel> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
    static String TableName;

    TextView lblHeading;
    TextView lblTotal;

    static String STARTTIME = "";
    static String DEVICEID  = "";

    static String ENTRYUSER = "";

    ConstraintLayout ll_no_data;
    static String UUID = "";
    static String HHID = "";
    static String ROUND = "";

    Bundle IDbundle;

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.cleaning_household_list);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         C = new Connection(this);
         g = Global.getInstance();
         STARTTIME = g.CurrentTime24();

         DEVICEID = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         HHID = IDbundle.getString("HHID");
         ROUND = IDbundle.getString("round");

         TableName = "cleaning_household";


         ImageButton cmdBack = (ImageButton) findViewById(R.id.btnClose);
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
         Connection.MessageBox(cleaning_household_list.this, e.getMessage());
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

 private void DataSearch(String hhid, String rnd)
     {
       try
        {
     
             cleaning_household_DataModel d = new cleaning_household_DataModel();
             String SQL = "Select * from "+ TableName +" Where HHID='"+ hhid +"' and Rnd='"+ rnd +"'";
             List<cleaning_household_DataModel> data = d.SelectAll(this, SQL);
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
             }catch ( Exception ex){
                 Connection.MessageBox(cleaning_household_list.this,ex.getMessage());
             }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(cleaning_household_list.this, e.getMessage());
            return;
        }
     }


     public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
         private List<cleaning_household_DataModel> dataList;
         public class MyViewHolder extends RecyclerView.ViewHolder {
         LinearLayout secListRow;
         TextView errors;
         CardView card_view;
         public MyViewHolder(View convertView) {
             super(convertView);
             secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
             errors = (TextView)convertView.findViewById(R.id.errors);
             card_view = convertView.findViewById(R.id.card_view);
             }
         }
         public DataAdapter(List<cleaning_household_DataModel> datalist) {
             this.dataList = datalist;
         }
         @Override
         public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.cleaning_household_row, parent, false);
             return new MyViewHolder(itemView);
         }
         @Override
         public void onBindViewHolder(MyViewHolder holder, int position) {
             final cleaning_household_DataModel data = dataList.get(position);
             holder.errors.setText(data.geterrors());

             if (data.getstatus().equalsIgnoreCase("1")) {
                 holder.card_view.setBackgroundResource(R.drawable.style_completed_square_shape);
             }
             else {
                 holder.card_view.setBackgroundResource(R.drawable.style_circle_line_incomplete);
             }

             holder.secListRow.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     cleaning_household_DataModel objSave = new cleaning_household_DataModel();
                     objSave.setuuid(data.getuuid());
                     objSave.setHHID(data.getHHID());
                     if (data.getstatus().equals("1")) objSave.setstatus("2");
                     else objSave.setstatus("1");
                     objSave.setcleandt(Global.DateTimeNowYMDHMS());
                     objSave.setDeviceID(DEVICEID);
                     objSave.setEntryUser(ENTRYUSER); //from data entry user list

                     String status = objSave.SaveUpdateData(cleaning_household_list.this);
                     if(status.length()>0)
                     {
                         Connection.MessageBox(cleaning_household_list.this,status);
                         return;
                     }else {
                         if (data.getstatus().equals("1")) data.setstatus("2");
                         else data.setstatus("1");

                         dataList.set(position, data);

                         mAdapter.notifyItemChanged(position);
                     }
                 }
             });
         }
         @Override
         public int getItemCount() {
             return dataList.size();
         }
     }

}