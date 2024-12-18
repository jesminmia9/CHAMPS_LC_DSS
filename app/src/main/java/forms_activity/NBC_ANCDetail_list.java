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
 import forms_datamodel.NBC_ANCDetail_DataModel;
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

//This activity is not used any where
public class NBC_ANCDetail_list extends AppCompatActivity {
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
    private List<NBC_ANCDetail_DataModel> dataList = new ArrayList<>();
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
    TextView LBLMEMID;
    TextView LBLPGNNO;

     String STARTTIME = "";
     String DEVICEID  = "";

     String ENTRYUSER = "";

    ConstraintLayout ll_no_data;
    Bundle IDbundle;
     String NBID = "";
     String MEMID = "";
     String HHID = "";
     String ROUND = "";
     String TOTALANC = "";
     String PGN = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.nbc_ancdetail_list);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         C = new Connection(this);
         g = Global.getInstance();
         STARTTIME = g.CurrentTime24();

         IDbundle = getIntent().getExtras();
         NBID = IDbundle.getString("NBID");
         PGN = IDbundle.getString("PGN");
         MEMID = IDbundle.getString("MemID");
         HHID = IDbundle.getString("HHID");
         ROUND = IDbundle.getString("round");
         TOTALANC = IDbundle.getString("totalANC");

         DEVICEID = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");
         TableName = "NBC_ANCDetail";
         lblHeading = (TextView)findViewById(R.id.lblHeading);
         lblTotal = (TextView)findViewById(R.id.lblTotal);

         LBLMEMID = (TextView)findViewById(R.id.labelMemId);
         LBLPGNNO = (TextView)findViewById(R.id.labelPGN);

         LBLMEMID.setText("Member ID: "+MEMID);
         LBLPGNNO.setText("PGN: "+PGN);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 finish();
             }});

         btnAdd = (Button) findViewById(R.id.btnAdd);
         btnAdd.setOnClickListener(new View.OnClickListener() {

             public void onClick(View view) {
                 String TotalAncDetails = C.ReturnSingleValue("Select count(*)total from NBC_ANCDetail where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='"+PGN+"'");
                 if (!Global.isNullOrEmpty(TotalAncDetails)){
                     if(Integer.parseInt(TOTALANC) <= Integer.parseInt(TotalAncDetails)){
                         Connection.MessageBox(NBC_ANCDetail_list.this, "You can't enter more record where as total number of anc is " + TOTALANC + ".");
                         return;
                     }
                 }
                 Bundle IDbundle = new Bundle();
                 IDbundle.putString("NBID", "");
                 IDbundle.putString("PGN", PGN);
                 IDbundle.putString("MemID", MEMID);
                 IDbundle.putString("HHID", HHID);
                 IDbundle.putString("round", ROUND);
                 IDbundle.putString("isEDIT", "1");
                 IDbundle.putString("ANCSl", "");
                 Intent intent = new Intent(getApplicationContext(), NBC_ANCDetail.class);
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


        DataSearch(MEMID,HHID,PGN);


     }
     catch(Exception  e)
     {
         Connection.MessageBox(NBC_ANCDetail_list.this, e.getMessage());
         return;
     }
 }
 
 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if (resultCode == Activity.RESULT_CANCELED) {
         //Write your code if there's no result
     } else {
         DataSearch(MEMID,HHID,PGN);
     }
 }

 private void DataSearch(String MEMID, String HHID, String PGN)
     {
       try
        {
     
           NBC_ANCDetail_DataModel d = new NBC_ANCDetail_DataModel();
             String SQL = "Select * from "+ TableName +"  Where MemID = '"+MEMID+"' and HHID = '"+HHID+"' and PGN = '"+PGN+"' and isdelete=2";
             List<NBC_ANCDetail_DataModel> data = d.SelectAll(this, SQL);
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
                 Connection.MessageBox(NBC_ANCDetail_list.this,ex.getMessage());
             }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(NBC_ANCDetail_list.this, e.getMessage());
            return;
        }
     }



     public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
         private List<NBC_ANCDetail_DataModel> dataList;
         public class MyViewHolder extends RecyclerView.ViewHolder {
         LinearLayout secListRow;
         TextView ANCSl;
         TextView ANCDate;
//         TextView ANCDateDk;
//         TextView ANCGAge;
//         TextView ANCProv;
//         TextView ANCProvOth;
//         TextView ANCPlace;
//         TextView ANCPlaceOth;
//         TextView ANCRes;
         public MyViewHolder(View convertView) {
             super(convertView);
             secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
             ANCSl = (TextView)convertView.findViewById(R.id.ANCSl);
             ANCDate = (TextView)convertView.findViewById(R.id.ANCDate);
//             ANCDateDk = (TextView)convertView.findViewById(R.id.ANCDateDk);
//             ANCGAge = (TextView)convertView.findViewById(R.id.ANCGAge);
//             ANCProv = (TextView)convertView.findViewById(R.id.ANCProv);
//             ANCProvOth = (TextView)convertView.findViewById(R.id.ANCProvOth);
//             ANCPlace = (TextView)convertView.findViewById(R.id.ANCPlace);
//             ANCPlaceOth = (TextView)convertView.findViewById(R.id.ANCPlaceOth);
//             ANCRes = (TextView)convertView.findViewById(R.id.ANCRes);
             }
         }
         public DataAdapter(List<NBC_ANCDetail_DataModel> datalist) {
             this.dataList = datalist;
         }
         @Override
         public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.nbc_ancdetail_row, parent, false);
             return new MyViewHolder(itemView);
         }
         @Override
         public void onBindViewHolder(MyViewHolder holder, int position) {
             final NBC_ANCDetail_DataModel data = dataList.get(position);
             holder.ANCSl.setText(String.valueOf(data.getANCSl()));
             holder.ANCDate.setText(data.getANCDate());
//             holder.ANCDateDk.setText(String.valueOf(data.getANCDateDk()));
//             holder.ANCGAge.setText(String.valueOf(data.getANCGAge()));
//             holder.ANCProv.setText(String.valueOf(data.getANCProv()));
//             holder.ANCProvOth.setText(data.getANCProvOth());
//             holder.ANCPlace.setText(String.valueOf(data.getANCPlace()));
//             holder.ANCPlaceOth.setText(data.getANCPlaceOth());
//             holder.ANCRes.setText(String.valueOf(data.getANCRes()));


             holder.secListRow.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(NBC_ANCDetail_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("NBID", "");
                                 IDbundle.putString("PGN", data.getPGN());
                                 IDbundle.putString("MemID", data.getMemID());
                                 IDbundle.putString("HHID", data.getHHID());
                                 IDbundle.putString("round", ROUND);
                                 IDbundle.putString("isEDIT", "2");
                                 IDbundle.putString("ANCSl", data.getANCSl());
                                 Intent f1 = new Intent(getApplicationContext(), NBC_ANCDetail.class);
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