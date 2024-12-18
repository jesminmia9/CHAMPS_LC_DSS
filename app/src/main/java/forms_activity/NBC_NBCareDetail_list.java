package forms_activity;

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
import forms_datamodel.NBC_NBCareDetail_DataModel;

public class NBC_NBCareDetail_list extends AppCompatActivity {
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
    private List<NBC_NBCareDetail_DataModel> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
     String TableName;

    Bundle IDbundle;
    TextView lblHeading;
    TextView lblTotal;
    Button btnAdd;

    TextView LBLMEMID;
    TextView LBLPGNNO;
     String STARTTIME = "";
     String DEVICEID = "";

     String ENTRYUSER = "";

    ConstraintLayout ll_no_data;
     String NBID = "";
     String MEMID = "";
     String HHID = "";
     String ROUND = "";
     String TOTALOUTCOME = "";
     String PGN = "";
     String title = "";
     String MEMNAME = "";
     String MSlNo = "";
     String VDATE = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.nbc_nbcaredetail_list);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            C = new Connection(this);
            g = Global.getInstance();
            STARTTIME = g.CurrentTime24();

         DEVICEID = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         NBID = IDbundle.getString("NBID");
         MEMID = IDbundle.getString("MemID");
         HHID = IDbundle.getString("HHID");
         ROUND = IDbundle.getString("round");
         TOTALOUTCOME = IDbundle.getString("totalOutcome");
         PGN = IDbundle.getString("PGN");
         title = IDbundle.getString("title");
         MEMNAME = IDbundle.getString("MEMNAME");
         MSlNo = IDbundle.getString("MSlNo");
         VDATE = IDbundle.getString("vdate");

         TableName = "NBC_NBCareDetail";
         lblHeading = (TextView)findViewById(R.id.lblHeading);
         lblTotal = (TextView)findViewById(R.id.lblTotal);
         lblHeading.setText(title);

         LBLMEMID = (TextView)findViewById(R.id.labelMemId);
         LBLPGNNO = (TextView)findViewById(R.id.labelPGN);

         LBLMEMID.setText(MSlNo+": "+MEMNAME);
         LBLPGNNO.setText("Birth Serial: "+PGN);
         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 finish();
             }});

         btnAdd = (Button) findViewById(R.id.btnAdd);
         btnAdd.setOnClickListener(new View.OnClickListener() {

             public void onClick(View view) {
                 String TotalOutcomeDetails = C.ReturnSingleValue("Select count(*)total from NBC_NBCareDetail where MemID='" + MEMID + "' and PGN='"+PGN+"'");
                 if (!Global.isNullOrEmpty(TotalOutcomeDetails)){
                     if(Integer.parseInt(TOTALOUTCOME) <= Integer.parseInt(TotalOutcomeDetails)){
                         Connection.MessageBox(NBC_NBCareDetail_list.this, "You can't enter more record where as total number of outcome is " + TOTALOUTCOME + ".");
                         return;
                     }
                 }
                         Bundle IDbundle = new Bundle();
                         IDbundle.putString("NBID", "");
                         IDbundle.putString("MEMID", MEMID);
                         IDbundle.putString("HHID", HHID);
                         IDbundle.putString("PGN", PGN);
                         IDbundle.putString("round", ROUND);
                         IDbundle.putString("totalOutcome", TOTALOUTCOME);
                         IDbundle.putString("childSl", "");
                         IDbundle.putString("title", lblHeading.getText().toString());
                         Intent intent = new Intent(getApplicationContext(), NBC_NBCareDetail.class);
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

            DataSearch(HHID, MEMID, PGN);


        } catch (Exception e) {
            Connection.MessageBox(NBC_NBCareDetail_list.this, e.getMessage());
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            //Write your code if there's no result
        } else {
            DataSearch(HHID, MEMID, PGN);
        }
    }

    private void DataSearch(String HHID, String MEMID, String PGN) {

        try {

            NBC_NBCareDetail_DataModel d = new NBC_NBCareDetail_DataModel();
            String SQL = "Select * from " + TableName + "  Where PGN = '" + PGN + "' AND HHID = '" + HHID + "' AND isdelete= 2 AND MemID = '" + MEMID + "'";
            List<NBC_NBCareDetail_DataModel> data = d.SelectAll(this, SQL);
            dataList.clear();

            dataList.addAll(data);
            if (dataList != null && !dataList.isEmpty()) {
                recyclerView.setVisibility(View.VISIBLE);
                ll_no_data.setVisibility(View.GONE);
            } else {
                recyclerView.setVisibility(View.GONE);
                ll_no_data.setVisibility(View.VISIBLE);
            }
            try {
                mAdapter.notifyDataSetChanged();
                lblTotal.setText(" (Total: " + dataList.size() + ")");
            } catch (Exception ex) {
                Connection.MessageBox(NBC_NBCareDetail_list.this, ex.getMessage());
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_NBCareDetail_list.this, e.getMessage());
            return;
        }
    }



     public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
         private List<NBC_NBCareDetail_DataModel> dataList;
         public class MyViewHolder extends RecyclerView.ViewHolder {
         LinearLayout secListRow;
         TextView NBID;
         TextView HHID;
         TextView ChSl;
         TextView PregType;
         TextView ChildName;
         TextView ChildID;
         TextView ChildDOB;
         Button btnPNCChMaster;
         Button btnMorbidityCh;
         public MyViewHolder(View convertView) {
             super(convertView);
             secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
//             NBID = (TextView)convertView.findViewById(R.id.NBID);
//             HHID = (TextView)convertView.findViewById(R.id.HHID);
             ChSl = (TextView)convertView.findViewById(R.id.ChSl);
             PregType = (TextView)convertView.findViewById(R.id.PregType);
             ChildName = (TextView)convertView.findViewById(R.id.ChildName);
             ChildID = (TextView)convertView.findViewById(R.id.ChildID);
             ChildDOB = (TextView)convertView.findViewById(R.id.ChildDOB);
             btnPNCChMaster = (Button) convertView.findViewById(R.id.btnPNCChMaster);
             btnMorbidityCh = (Button) convertView.findViewById(R.id.btnMorbidityCh);
             }
         }
         public DataAdapter(List<NBC_NBCareDetail_DataModel> datalist) {
             this.dataList = datalist;
         }
         @Override
         public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.nbc_nbcaredetail_row, parent, false);
             return new MyViewHolder(itemView);
         }
         @Override
         public void onBindViewHolder(MyViewHolder holder, int position) {
             final NBC_NBCareDetail_DataModel data = dataList.get(position);
//             holder.NBID.setText(data.getNBID());
//             holder.HHID.setText(data.getHHID());
             holder.ChSl.setText(data.getChSl());
//             holder.LBLPGNNO.setText("PGN: 1");
             if (data.getPregType().equals("1")){
                 holder.PregType.setVisibility(View.VISIBLE);
                 holder.PregType.setText("Live birth");
             } else if(data.getPregType().equals("2")){
                 holder.PregType.setVisibility(View.VISIBLE);
                 holder.PregType.setText("Spontaneous miscarriage");
             } else if(data.getPregType().equals("3")){
                 holder.PregType.setVisibility(View.VISIBLE);
                 holder.PregType.setText("Stillbirth");
             } else if(data.getPregType().equals("4")){
                 holder.PregType.setVisibility(View.VISIBLE);
                 holder.PregType.setText("Induced abortion(No)");
             } else if(data.getPregType().equals("5")){
                 holder.PregType.setVisibility(View.VISIBLE);
                 holder.PregType.setText("Induced abortion(Medically terminated)");
             } else if(data.getPregType().equals("8")){
                 holder.PregType.setVisibility(View.VISIBLE);
                 holder.PregType.setText("Don't know");
             } else if(data.getPregType().equals("9")){
                 holder.PregType.setVisibility(View.VISIBLE);
                 holder.PregType.setText("Reused to respond");
             } else {
                 holder.PregType.setVisibility(View.GONE);
             }
             holder.ChildName.setText(data.getChildName());
             holder.ChildID.setText(data.getChildID());
             holder.ChildDOB.setText(Global.DateConvertDMY(data.getChildDOB()));


             holder.btnMorbidityCh.setBackgroundResource(R.drawable.button_style_circle_line_disable);
             holder.btnPNCChMaster.setBackgroundResource(R.drawable.button_style_circle_line_disable);
             holder.btnMorbidityCh.setVisibility(View.GONE);
             holder.btnPNCChMaster.setVisibility(View.GONE);
             if(data.getPregType().equals("1") || data.getPregType().equals("3")){
                 holder.btnMorbidityCh.setVisibility(View.VISIBLE);
                 holder.btnMorbidityCh.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                 holder.btnPNCChMaster.setVisibility(View.VISIBLE);
                 holder.btnPNCChMaster.setBackgroundResource(R.drawable.style_not_completed_square_shape);

                 if(C.Existence("Select * from NBC_Morbidity  Where MemID='"+ data.getMemID() +"' and HHID='"+ data.getHHID() +"' and ChSl='"+ data.getChSl() +"' and PGN='"+ data.getPGN() +"'")){

                     holder.btnMorbidityCh.setBackgroundResource(R.drawable.style_completed_square_shape);
                     holder.btnMorbidityCh.setTextColor(Color.WHITE);
                 }
                 if(C.Existence("Select * from NBC_PNCNB  Where MemID='"+ data.getMemID() +"' and HHID='"+ data.getHHID() +"' and ChSl='"+ data.getChSl() +"' and PGN='"+ data.getPGN() +"'")){
                     holder.btnPNCChMaster.setBackgroundResource(R.drawable.style_completed_square_shape);
                     holder.btnPNCChMaster.setTextColor(Color.WHITE);
                 }
             }



            holder.secListRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(NBC_NBCareDetail_list.this, "", "Please Wait . . .", true);
                    new Thread() {
                        public void run() {
                            try {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("NBID", data.getNBID());
                                IDbundle.putString("PGN", data.getPGN());
                                IDbundle.putString("MEMID", data.getMemID());
                                IDbundle.putString("HHID", data.getHHID());
                                IDbundle.putString("round", ROUND);
                                IDbundle.putString("totalOutcome", TOTALOUTCOME);
                                IDbundle.putString("childSl", data.getChSl());
                                IDbundle.putString("title", lblHeading.getText().toString());
                                Intent f1 = new Intent(getApplicationContext(), NBC_NBCareDetail.class);
                                f1.putExtras(IDbundle);
                                startActivityForResult(f1, 1);
                            } catch (Exception e) {
                            }
                            progDailog.dismiss();
                        }
                    }.start();
                }
            });

             holder.btnPNCChMaster.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View arg0) {
                     Bundle IDbundle = new Bundle();
                     IDbundle.putString("NBID", "");
                     IDbundle.putString("PGN", data.getPGN());
                     IDbundle.putString("MemID", data.getMemID());
                     IDbundle.putString("HHID", data.getHHID());
                     IDbundle.putString("round", ROUND);
                     IDbundle.putString("childsl", data.getChSl());
                     Intent intent = new Intent(getApplicationContext(), NBC_PNCNB.class);
                     intent.putExtras(IDbundle);
                     startActivityForResult(intent, 1);
                 }
             });

             holder.btnMorbidityCh.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View arg0) {
                     Bundle IDbundle = new Bundle();
                     IDbundle.putString("MorbiID", "");
                     IDbundle.putString("PGN", data.getPGN());
                     IDbundle.putString("MemID", data.getMemID());
                     IDbundle.putString("HHID", data.getHHID());
                     IDbundle.putString("round", ROUND);
                     IDbundle.putString("childsl", data.getChSl());
                     Intent intent = new Intent(getApplicationContext(), NBC_Morbidity.class);
                     intent.putExtras(IDbundle);
                     startActivityForResult(intent, 1);
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
                return new DatePickerDialog(this, mDateSetListener, g.mYear, g.mMonth - 1, g.mDay);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear + 1;
            mDay = dayOfMonth;
            EditText dtpDate;
            dtpDate = (EditText) findViewById(R.id.dtpFDate);
            if (VariableID.equals("dtpFDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpFDate);
            } else if (VariableID.equals("dtpTDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpTDate);
            }
            dtpDate.setText(new StringBuilder()
                    .append(Global.Right("00" + mDay, 2)).append("/")
                    .append(Global.Right("00" + mMonth, 2)).append("/")
                    .append(mYear));
        }
    };


}