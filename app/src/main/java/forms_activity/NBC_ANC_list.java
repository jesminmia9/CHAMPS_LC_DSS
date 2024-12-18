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
 import forms_datamodel.NBC_ANC_DataModel;
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
public class NBC_ANC_list extends AppCompatActivity {
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
    private List<NBC_ANC_DataModel> dataList = new ArrayList<>();
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
     String NBID = "";
     String PGN = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.nbc_anc_list);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         C = new Connection(this);
         g = Global.getInstance();
         STARTTIME = g.CurrentTime24();

         DEVICEID = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");
         TableName = "NBC_ANC";
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
                         IDbundle.putString("NBID", "");
                         IDbundle.putString("PGN", "");
                         Intent intent = new Intent(getApplicationContext(), NBC_ANC.class);
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


        DataSearch(txtSearch.getText().toString());


     }
     catch(Exception  e)
     {
         Connection.MessageBox(NBC_ANC_list.this, e.getMessage());
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
     
           NBC_ANC_DataModel d = new NBC_ANC_DataModel();
             String SQL = "Select * from "+ TableName +"  Where NBID like('"+ SearchText +"%') or PGN like('"+ SearchText +"%') and isdelete=2";
             List<NBC_ANC_DataModel> data = d.SelectAll(this, SQL);
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
                 Connection.MessageBox(NBC_ANC_list.this,ex.getMessage());
             }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(NBC_ANC_list.this, e.getMessage());
            return;
        }
     }



     public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
         private List<NBC_ANC_DataModel> dataList;
         public class MyViewHolder extends RecyclerView.ViewHolder {
         LinearLayout secListRow;
         TextView NBID;
         TextView MemID;
         TextView HHID;
         TextView PGN;
         TextView Anc;
         TextView AncNo;
         TextView AncNoDk;
         TextView AncCard;
         TextView ANCPres;
         TextView AncWeight;
         TextView AncBp;
         TextView AncUrine;
         TextView AncBlood;
         TextView AncHeartbeat;
         TextView AncUSG;
         TextView AncFood;
         TextView ANCBF;
         TextView ANCVBleed;
         TextView ANCDSign;
         TextView ANCFPMtd;
         TextView ANCHCOth;
         TextView ANCHCOthSp;
         TextView AncHCDk;
         TextView ANCRefu;
         public MyViewHolder(View convertView) {
             super(convertView);
             secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
             NBID = (TextView)convertView.findViewById(R.id.NBID);
             MemID = (TextView)convertView.findViewById(R.id.MemID);
             HHID = (TextView)convertView.findViewById(R.id.HHID);
             PGN = (TextView)convertView.findViewById(R.id.PGN);
             Anc = (TextView)convertView.findViewById(R.id.Anc);
             AncNo = (TextView)convertView.findViewById(R.id.AncNo);
             AncNoDk = (TextView)convertView.findViewById(R.id.AncNoDk);
             AncCard = (TextView)convertView.findViewById(R.id.AncCard);
             ANCPres = (TextView)convertView.findViewById(R.id.ANCPres);
             AncWeight = (TextView)convertView.findViewById(R.id.AncWeight);
             AncBp = (TextView)convertView.findViewById(R.id.AncBp);
             AncUrine = (TextView)convertView.findViewById(R.id.AncUrine);
             AncBlood = (TextView)convertView.findViewById(R.id.AncBlood);
             AncHeartbeat = (TextView)convertView.findViewById(R.id.AncHeartbeat);
             AncUSG = (TextView)convertView.findViewById(R.id.AncUSG);
             AncFood = (TextView)convertView.findViewById(R.id.AncFood);
             ANCBF = (TextView)convertView.findViewById(R.id.ANCBF);
             ANCVBleed = (TextView)convertView.findViewById(R.id.ANCVBleed);
             ANCDSign = (TextView)convertView.findViewById(R.id.ANCDSign);
             ANCFPMtd = (TextView)convertView.findViewById(R.id.ANCFPMtd);
             ANCHCOth = (TextView)convertView.findViewById(R.id.ANCHCOth);
             ANCHCOthSp = (TextView)convertView.findViewById(R.id.ANCHCOthSp);
             AncHCDk = (TextView)convertView.findViewById(R.id.AncHCDk);
             ANCRefu = (TextView)convertView.findViewById(R.id.ANCRefu);
             }
         }
         public DataAdapter(List<NBC_ANC_DataModel> datalist) {
             this.dataList = datalist;
         }
         @Override
         public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.nbc_anc_row, parent, false);
             return new MyViewHolder(itemView);
         }
         @Override
         public void onBindViewHolder(MyViewHolder holder, int position) {
             final NBC_ANC_DataModel data = dataList.get(position);
             holder.NBID.setText(data.getNBID());
             holder.MemID.setText(data.getMemID());
             holder.HHID.setText(data.getHHID());
             holder.PGN.setText(data.getPGN());
             holder.Anc.setText(String.valueOf(data.getAnc()));
             holder.AncNo.setText(data.getAncNo());
             holder.AncNoDk.setText(String.valueOf(data.getAncNoDk()));
             holder.AncCard.setText(String.valueOf(data.getAncCard()));
             holder.ANCPres.setText(String.valueOf(data.getANCPres()));
             holder.AncWeight.setText(String.valueOf(data.getAncWeight()));
             holder.AncBp.setText(String.valueOf(data.getAncBp()));
             holder.AncUrine.setText(String.valueOf(data.getAncUrine()));
             holder.AncBlood.setText(String.valueOf(data.getAncBlood()));
             holder.AncHeartbeat.setText(String.valueOf(data.getAncHeartbeat()));
             holder.AncUSG.setText(String.valueOf(data.getAncUSG()));
             holder.AncFood.setText(String.valueOf(data.getAncFood()));
             holder.ANCBF.setText(String.valueOf(data.getANCBF()));
             holder.ANCVBleed.setText(String.valueOf(data.getANCVBleed()));
             holder.ANCDSign.setText(String.valueOf(data.getANCDSign()));
             holder.ANCFPMtd.setText(String.valueOf(data.getANCFPMtd()));
             holder.ANCHCOth.setText(String.valueOf(data.getANCHCOth()));
             holder.ANCHCOthSp.setText(data.getANCHCOthSp());
             holder.AncHCDk.setText(String.valueOf(data.getAncHCDk()));
             holder.ANCRefu.setText(String.valueOf(data.getANCRefu()));


             holder.secListRow.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(NBC_ANC_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("NBID", data.getNBID());
                                 IDbundle.putString("PGN", data.getPGN());
                                 Intent f1 = new Intent(getApplicationContext(), NBC_ANC.class);
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