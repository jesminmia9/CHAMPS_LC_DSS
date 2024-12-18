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
 import forms_datamodel.PregHis_DataModel;
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

public class Surv_PregHis_list extends AppCompatActivity {
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
    private List<PregHis_DataModel> dataList = new ArrayList<>();
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
     String OBSMATID = "";
     String MEM_ID = "";
     String HHID = "";
     String EV_TYPE = "";
     String ROUND = "";
     String MSlNo = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.surv_preghis_list);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         C = new Connection(this);
         g = Global.getInstance();
         STARTTIME = g.CurrentTime24();

         DEVICEID = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         OBSMATID = IDbundle.getString("ObsMatID");
         MEM_ID = IDbundle.getString("MemID");
         HHID = IDbundle.getString("HHID");
         EV_TYPE = IDbundle.getString("evtype");
         ROUND = IDbundle.getString("round");
         MSlNo = IDbundle.getString("MSlNo");

         TableName = "tmpPregHis";
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
                         IDbundle.putString("ObsMatID", "");
                         IDbundle.putString("MemID", MEM_ID);
                         IDbundle.putString("HHID", HHID);
                         IDbundle.putString("MSlNo", MSlNo);
                         IDbundle.putString("evtype", "");
                         IDbundle.putString("round", ROUND);
                         Intent intent = new Intent(getApplicationContext(), Surv_PregHis.class);
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


        DataSearch(OBSMATID);


     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_PregHis_list.this, e.getMessage());
         return;
     }
 }
 
 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if (resultCode == Activity.RESULT_CANCELED) {
         //Write your code if there's no result
     } else {
         DataSearch(OBSMATID);
     }
 }

 private void DataSearch(String ObsMatID)
     {
       try
        {
     
           PregHis_DataModel d = new PregHis_DataModel();
             String SQL = "Select * from "+ TableName +"  Where HHID ='"+ HHID +"' and MemID='"+ MEM_ID +"' and isdelete=2";
             List<PregHis_DataModel> data = d.SelectAll(this, SQL);
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
                 Connection.MessageBox(Surv_PregHis_list.this,ex.getMessage());
             }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_PregHis_list.this, e.getMessage());
            return;
        }
     }



     public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
         private List<PregHis_DataModel> dataList;
         public class MyViewHolder extends RecyclerView.ViewHolder {
         LinearLayout secListRow;
//         TextView ObsMatID;
         TextView MemID;
//         TextView HHID;
         TextView MSlNo;
         TextView ObsVDate;
         TextView ObsVStatus;
//         TextView ObsVStatusOth;
//         TextView MarMon;
//         TextView MarYear;
//         TextView MarDK;
//         TextView EverPreg;
//         TextView TotPreg;
//         TextView GaveBirth;
//         TextView ChildLivWWo;
//         TextView SonLivWWo;
//         TextView DaugLivWWo;
//         TextView ChldLivOut;
//         TextView SonLivOut;
//         TextView DaugLivOut;
//         TextView EarlyAlive;
//         TextView EarlyAliveNo;
//         TextView ChldDie;
//         TextView BoyDied;
//         TextView GirlDied;
//         TextView ChDiedFsMon;
//         TextView NotLivBrth;
//         TextView TotNotLB;
//         TextView StillBirth;
//         TextView StillBirthDk;
//         TextView MiscAbor;
//         TextView MiscAborDk;
//         TextView LastPregRes;
//         TextView LastOutDate;
//         TextView LastOutDateDK;
//         TextView Cesarean;
//         TextView CesareanNo;
//         TextView TotPregOut;
//         TextView ObsNote;
         public MyViewHolder(View convertView) {
             super(convertView);
             secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
//             ObsMatID = (TextView)convertView.findViewById(R.id.ObsMatID);
             MemID = (TextView)convertView.findViewById(R.id.MemID);
//             HHID = (TextView)convertView.findViewById(R.id.HHID);
             MSlNo = (TextView)convertView.findViewById(R.id.MSlNo);
             ObsVDate = (TextView)convertView.findViewById(R.id.ObsVDate);
             ObsVStatus = (TextView)convertView.findViewById(R.id.ObsVStatus);
//             ObsVStatusOth = (TextView)convertView.findViewById(R.id.ObsVStatusOth);
//             MarMon = (TextView)convertView.findViewById(R.id.MarMon);
//             MarYear = (TextView)convertView.findViewById(R.id.MarYear);
//             MarDK = (TextView)convertView.findViewById(R.id.MarDK);
//             EverPreg = (TextView)convertView.findViewById(R.id.EverPreg);
//             TotPreg = (TextView)convertView.findViewById(R.id.TotPreg);
//             GaveBirth = (TextView)convertView.findViewById(R.id.GaveBirth);
//             ChildLivWWo = (TextView)convertView.findViewById(R.id.ChildLivWWo);
//             SonLivWWo = (TextView)convertView.findViewById(R.id.SonLivWWo);
//             DaugLivWWo = (TextView)convertView.findViewById(R.id.DaugLivWWo);
//             ChldLivOut = (TextView)convertView.findViewById(R.id.ChldLivOut);
//             SonLivOut = (TextView)convertView.findViewById(R.id.SonLivOut);
//             DaugLivOut = (TextView)convertView.findViewById(R.id.DaugLivOut);
//             EarlyAlive = (TextView)convertView.findViewById(R.id.EarlyAlive);
//             EarlyAliveNo = (TextView)convertView.findViewById(R.id.EarlyAliveNo);
//             ChldDie = (TextView)convertView.findViewById(R.id.ChldDie);
//             BoyDied = (TextView)convertView.findViewById(R.id.BoyDied);
//             GirlDied = (TextView)convertView.findViewById(R.id.GirlDied);
//             ChDiedFsMon = (TextView)convertView.findViewById(R.id.ChDiedFsMon);
//             NotLivBrth = (TextView)convertView.findViewById(R.id.NotLivBrth);
//             TotNotLB = (TextView)convertView.findViewById(R.id.TotNotLB);
//             StillBirth = (TextView)convertView.findViewById(R.id.StillBirth);
//             StillBirthDk = (TextView)convertView.findViewById(R.id.StillBirthDk);
//             MiscAbor = (TextView)convertView.findViewById(R.id.MiscAbor);
//             MiscAborDk = (TextView)convertView.findViewById(R.id.MiscAborDk);
//             LastPregRes = (TextView)convertView.findViewById(R.id.LastPregRes);
//             LastOutDate = (TextView)convertView.findViewById(R.id.LastOutDate);
//             LastOutDateDK = (TextView)convertView.findViewById(R.id.LastOutDateDK);
//             Cesarean = (TextView)convertView.findViewById(R.id.Cesarean);
//             CesareanNo = (TextView)convertView.findViewById(R.id.CesareanNo);
//             TotPregOut = (TextView)convertView.findViewById(R.id.TotPregOut);
//             ObsNote = (TextView)convertView.findViewById(R.id.ObsNote);
             }
         }
         public DataAdapter(List<PregHis_DataModel> datalist) {
             this.dataList = datalist;
         }
         @Override
         public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.surv_preghis_row, parent, false);
             return new MyViewHolder(itemView);
         }
         @Override
         public void onBindViewHolder(MyViewHolder holder, int position) {
             final PregHis_DataModel data = dataList.get(position);
//             holder.ObsMatID.setText(data.getObsMatID());
             holder.MemID.setText(data.getMemID());
//             holder.HHID.setText(data.getHHID());
             holder.MSlNo.setText(data.getMSlNo());
             holder.ObsVDate.setText(data.getObsVDate());
             holder.ObsVStatus.setText(data.getObsVStatus());
//             holder.ObsVStatusOth.setText(data.getObsVStatusOth());
//             holder.MarMon.setText(data.getMarMon());
//             holder.MarYear.setText(data.getMarYear());
//             holder.MarDK.setText(String.valueOf(data.getMarDK()));
//             holder.EverPreg.setText(String.valueOf(data.getEverPreg()));
//             holder.TotPreg.setText(data.getTotPreg());
//             holder.GaveBirth.setText(String.valueOf(data.getGaveBirth()));
//             holder.ChildLivWWo.setText(String.valueOf(data.getChildLivWWo()));
//             holder.SonLivWWo.setText(data.getSonLivWWo());
//             holder.DaugLivWWo.setText(data.getDaugLivWWo());
//             holder.ChldLivOut.setText(String.valueOf(data.getChldLivOut()));
//             holder.SonLivOut.setText(data.getSonLivOut());
//             holder.DaugLivOut.setText(data.getDaugLivOut());
//             holder.EarlyAlive.setText(String.valueOf(data.getEarlyAlive()));
//             holder.EarlyAliveNo.setText(data.getEarlyAliveNo());
//             holder.ChldDie.setText(String.valueOf(data.getChldDie()));
//             holder.BoyDied.setText(data.getBoyDied());
//             holder.GirlDied.setText(data.getGirlDied());
//             holder.ChDiedFsMon.setText(data.getChDiedFsMon());
//             holder.NotLivBrth.setText(String.valueOf(data.getNotLivBrth()));
//             holder.TotNotLB.setText(data.getTotNotLB());
//             holder.StillBirth.setText(data.getStillBirth());
//             holder.StillBirthDk.setText(String.valueOf(data.getStillBirthDk()));
//             holder.MiscAbor.setText(data.getMiscAbor());
//             holder.MiscAborDk.setText(String.valueOf(data.getMiscAborDk()));
//             holder.LastPregRes.setText(String.valueOf(data.getLastPregRes()));
//             holder.LastOutDate.setText(data.getLastOutDate());
//             holder.LastOutDateDK.setText(String.valueOf(data.getLastOutDateDK()));
//             holder.Cesarean.setText(String.valueOf(data.getCesarean()));
//             holder.CesareanNo.setText(data.getCesareanNo());
//             holder.TotPregOut.setText(data.getTotPregOut());
//             holder.ObsNote.setText(data.getObsNote());


             holder.secListRow.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(Surv_PregHis_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("ObsMatID", data.getObsMatID());
                                 Intent f1 = new Intent(getApplicationContext(), Surv_PregHis.class);
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