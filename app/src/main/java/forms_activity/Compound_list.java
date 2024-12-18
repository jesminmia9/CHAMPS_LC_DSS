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
 import forms_datamodel.Compound_DataModel;
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
 import androidx.recyclerview.widget.DefaultItemAnimator;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;
 import androidx.constraintlayout.widget.ConstraintLayout;

import org.icddrb.champs_lc_dss.R;

public class Compound_list extends AppCompatActivity {
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
    private List<Compound_DataModel> dataList = new ArrayList<>();
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
    Button btnLandmark;
    static String STARTTIME = "";
    static String DEVICEID  = "";

    static String ENTRYUSER = "";

    Bundle IDbundle;
    ConstraintLayout ll_no_data;
    static String COMPOUNDID = "";
    static String COMPOUNDCODE = "";
    static String VillID = "";
    static String LMNO = "";
    static String TotalHH = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.compound_list);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         C = new Connection(this);
         g = Global.getInstance();
         STARTTIME = g.CurrentTime24();

         IDbundle = getIntent().getExtras();
         VillID = IDbundle.getString("VillID");
         LMNO = IDbundle.getString("LMNO");

         DEVICEID = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");
         TableName = "Compound";
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
                         IDbundle.putString("VillID", "888");
                         IDbundle.putString("CompoundCode", "");
                         Intent intent = new Intent(getApplicationContext(), Compound.class);
                         intent.putExtras(IDbundle);
                         startActivityForResult(intent, 1);
             }});
         txtSearch = (EditText)findViewById(R.id.txtSearch);
         btnSearch = (ImageButton) findViewById(R.id.btnSearch);
         btnSearch.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
                         DataSearch(txtSearch.getText().toString());

             }});

         btnLandmark = (Button) findViewById(R.id.btnLandmark);
         btnLandmark.setOnClickListener(new View.OnClickListener() {             public void onClick(View view) {
                 Bundle IDbundle = new Bundle();
                 IDbundle.putString("VillID", "888");
                 IDbundle.putString("LMNO", "");
                 Intent intent = new Intent(getApplicationContext(), GPSLandmark.class);
                 intent.putExtras(IDbundle);
                 startActivityForResult(intent, 1);
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
         Connection.MessageBox(Compound_list.this, e.getMessage());
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
           Compound_DataModel d = new Compound_DataModel();
             String SQL = "Select c.CompoundID,c.VillID,c.CompoundCode,c.CompoundName,c.CompoundAdrs,\n" +
                     "Count(h.HHID) TotalHH,\n" +
                     "sum(case when v1.HHID IS Not null then 1 else 0 end)Visited\n" +
                     "from Compound c \n" +
                     "left join  Household h on c.CompoundID=h.CompoundID \n" +
                     "left join (Select HHID,max(Visitno)visitno from Visits group by HHID)v1 \n" +
                     "on h.HHID=v1.HHID\n" +
                     " where c.isdelete='2'\n" +
                     " Group by c.CompoundCode,c.CompoundName,c.CompoundAdrs";

             List<Compound_DataModel> data = d.SelectAll_Baseline(this, SQL);
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
                 Connection.MessageBox(Compound_list.this,ex.getMessage());
             }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Compound_list.this, e.getMessage());
            return;
        }
     }



     public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
         private List<Compound_DataModel> dataList;
         public class MyViewHolder extends RecyclerView.ViewHolder {
         LinearLayout secListRow;
//         TextView CompoundID;
         TextView CompoundCode;
         TextView CompoundName;
         TextView CompoundAdrs;
         TextView VillID;
         TextView TotalHH;
//         TextView Active;
         /*TextView ComEnDate;
         TextView ComExDate;
         TextView ComExReason;
         TextView Cluster;
         TextView Block;
         TextView Rnd;*/
         public MyViewHolder(View convertView) {
             super(convertView);
             secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
//             CompoundID = (TextView)convertView.findViewById(R.id.CompoundID);
             CompoundCode = (TextView)convertView.findViewById(R.id.CompoundCode);
             CompoundName = (TextView)convertView.findViewById(R.id.CompoundName);
             CompoundAdrs = (TextView)convertView.findViewById(R.id.CompoundAdrs);
             TotalHH = (TextView)convertView.findViewById(R.id.TotalHH);
//             VillID = (TextView)convertView.findViewById(R.id.VillID);

             /*Active = (TextView)convertView.findViewById(R.id.Active);
             ComEnDate = (TextView)convertView.findViewById(R.id.ComEnDate);
             ComExDate = (TextView)convertView.findViewById(R.id.ComExDate);
             ComExReason = (TextView)convertView.findViewById(R.id.ComExReason);
             Cluster = (TextView)convertView.findViewById(R.id.Cluster);
             Block = (TextView)convertView.findViewById(R.id.Block);
             Rnd = (TextView)convertView.findViewById(R.id.Rnd);*/
             }
         }
         public DataAdapter(List<Compound_DataModel> datalist) {
             this.dataList = datalist;
         }
         @Override
         public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.compound_row, parent, false);
             return new MyViewHolder(itemView);
         }
         @Override
         public void onBindViewHolder(MyViewHolder holder, int position) {
             final Compound_DataModel data = dataList.get(position);
//             holder.CompoundID.setText(data.getCompoundID());
             holder.CompoundCode.setText(data.getCompoundCode());
             holder.CompoundName.setText(data.getCompoundName());
             holder.CompoundAdrs.setText(data.getCompoundAdrs());
             holder.TotalHH.setText(data.getVisited() + " / " + data.getTotalHH());

             if(data.getVisited().equals(data.getTotalHH())) {
                 holder.TotalHH.setBackgroundResource(R.drawable.button_style_circle_green);
                 holder.TotalHH.setVisibility(View.VISIBLE);
             }else {
                 holder.TotalHH.setBackgroundResource(R.drawable.button_style_circle);
                 holder.TotalHH.setVisibility(View.VISIBLE);
             }

             if(data.getTotalHH().equalsIgnoreCase("0")) {
                 holder.TotalHH.setBackgroundResource(R.drawable.button_style_not_scheduled);
                 holder.TotalHH.setTextColor(Color.BLACK);
//                 holder.TotalHH.setVisibility(View.GONE);
             }


             holder.secListRow.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(Compound_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("VillID", data.getVillID());
                                 IDbundle.putString("CompoundID", data.getCompoundID());
                                 Intent f1 = new Intent(getApplicationContext(), Household_list.class);
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