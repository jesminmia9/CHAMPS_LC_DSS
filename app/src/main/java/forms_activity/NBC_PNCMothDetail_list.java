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
import forms_datamodel.NBC_PNCMothDetail_DataModel;

//This activity is not used any where
public class NBC_PNCMothDetail_list extends AppCompatActivity {
    boolean networkAvailable = false;
    Location currentLocation;
    double currentLatitude, currentLongitude;

    //Disabled Back/Home key
    //--------------------------------------------------------------------------------------------------
    @Override
    public boolean onKeyDown(int iKeyCode, KeyEvent event) {
        if (iKeyCode == KeyEvent.KEYCODE_BACK || iKeyCode == KeyEvent.KEYCODE_HOME) {
            return false;
        } else {
            return true;
        }
    }

    String VariableID;
    private int mDay;
    private int mMonth;
    private int mYear;
     final int DATE_DIALOG = 1;
     final int TIME_DIALOG = 2;

    Connection C;
    Global g;
    private List<NBC_PNCMothDetail_DataModel> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
     String TableName;

    Bundle IDbundle;
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
     String DEVICEID = "";

     String ENTRYUSER = "";

    ConstraintLayout ll_no_data;
     String NBID = "";
     String MEMID = "";
     String HHID = "";
     String PGN = "";
     String ROUND = "";
     String PNCSLCount = "";
     String PNCSL = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.nbc_pncmothdetail_list);
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
            PNCSLCount = IDbundle.getString("totalPNC");
            PGN = IDbundle.getString("PGN");

            TableName = "NBC_PNCMothDetail";
            lblHeading = (TextView) findViewById(R.id.lblHeading);
            lblTotal = (TextView) findViewById(R.id.lblTotal);

            LBLMEMID = (TextView) findViewById(R.id.labelMemId);
            LBLPGNNO = (TextView) findViewById(R.id.labelPGN);

            LBLMEMID.setText("Member ID: " + MEMID);
            LBLPGNNO.setText("PGN: " + PGN);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });

            btnAdd = (Button) findViewById(R.id.btnAdd);
            btnAdd.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    String TotalPncDetails = C.ReturnSingleValue("Select count(*)total from NBC_PNCMothDetail where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='" + PGN + "'");
                    if (!Global.isNullOrEmpty(TotalPncDetails)) {
                        if (Integer.parseInt(PNCSLCount) <= Integer.parseInt(TotalPncDetails)) {
                            Connection.MessageBox(NBC_PNCMothDetail_list.this, "You can't enter more record where as total number of pnc is " + PNCSLCount + ".");
                            return;
                        }
                    }
                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("NBID", "");
                    IDbundle.putString("MemID", MEMID);
                    IDbundle.putString("HHID", HHID);
                    IDbundle.putString("PGN", PGN);
                    IDbundle.putString("round", ROUND);
                    IDbundle.putString("isEDIT", "1");
                    IDbundle.putString("PNCSl", "");
                    Intent intent = new Intent(getApplicationContext(), NBC_PNCMothDetail.class);
                    intent.putExtras(IDbundle);
                    startActivityForResult(intent, 1);

                }
            });


            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
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


            DataSearch(MEMID, HHID, PGN);


        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCMothDetail_list.this, e.getMessage());
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            //Write your code if there's no result
        } else {
            DataSearch(MEMID, HHID, PGN);
        }
    }

    private void DataSearch(String MEMID, String HHID, String PGN) {
        try {

            NBC_PNCMothDetail_DataModel d = new NBC_PNCMothDetail_DataModel();
            String SQL = "Select * from " + TableName + "  Where MemID = '" + MEMID + "' and HHID = '" + HHID + "' and PGN = '" + PGN + "' and isdelete=2";
            List<NBC_PNCMothDetail_DataModel> data = d.SelectAll(this, SQL);
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
                Connection.MessageBox(NBC_PNCMothDetail_list.this, ex.getMessage());
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCMothDetail_list.this, e.getMessage());
            return;
        }
    }


    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
        private List<NBC_PNCMothDetail_DataModel> dataList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout secListRow;
            //         TextView NBID;
//         TextView MemID;
//         TextView HHID;
//         TextView PGN;
            TextView PNCSl;
            TextView PNCDate;

            //         TextView PNCDateDk;
//         TextView PNCProv;
//         TextView PNCProvOth;
//         TextView PNCPlace;
//         TextView PNCPlaceOth;
//         TextView PNCRes;
            public MyViewHolder(View convertView) {
                super(convertView);
                secListRow = (LinearLayout) convertView.findViewById(R.id.secListRow);
//             NBID = (TextView)convertView.findViewById(R.id.NBID);
//             MemID = (TextView)convertView.findViewById(R.id.MemID);
//             HHID = (TextView)convertView.findViewById(R.id.HHID);
//             PGN = (TextView)convertView.findViewById(R.id.PGN);
                PNCSl = (TextView) convertView.findViewById(R.id.PNCSl);
                PNCDate = (TextView) convertView.findViewById(R.id.PNCDate);
//             PNCDateDk = (TextView)convertView.findViewById(R.id.PNCDateDk);
//             PNCProv = (TextView)convertView.findViewById(R.id.PNCProv);
//             PNCProvOth = (TextView)convertView.findViewById(R.id.PNCProvOth);
//             PNCPlace = (TextView)convertView.findViewById(R.id.PNCPlace);
//             PNCPlaceOth = (TextView)convertView.findViewById(R.id.PNCPlaceOth);
//             PNCRes = (TextView)convertView.findViewById(R.id.PNCRes);
            }
        }

        public DataAdapter(List<NBC_PNCMothDetail_DataModel> datalist) {
            this.dataList = datalist;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.nbc_pncmothdetail_row, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final NBC_PNCMothDetail_DataModel data = dataList.get(position);
//             holder.NBID.setText(data.getNBID());
//             holder.MemID.setText(data.getMemID());
//             holder.HHID.setText(data.getHHID());
//             holder.PGN.setText(data.getPGN());
            holder.PNCSl.setText(String.valueOf(data.getPNCSl()));
            holder.PNCDate.setText(data.getPNCDate());
//             holder.PNCDateDk.setText(String.valueOf(data.getPNCDateDk()));
//             holder.PNCProv.setText(String.valueOf(data.getPNCProv()));
//             holder.PNCProvOth.setText(data.getPNCProvOth());
//             holder.PNCPlace.setText(String.valueOf(data.getPNCPlace()));
//             holder.PNCPlaceOth.setText(data.getPNCPlaceOth());
//             holder.PNCRes.setText(String.valueOf(data.getPNCRes()));


            holder.secListRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(NBC_PNCMothDetail_list.this, "", "Please Wait . . .", true);
                    new Thread() {
                        public void run() {
                            try {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("NBID", "");
                                IDbundle.putString("MemID", data.getMemID());
                                IDbundle.putString("HHID", data.getHHID());
                                IDbundle.putString("PGN", data.getPGN());
                                IDbundle.putString("round", ROUND);
                                IDbundle.putString("isEDIT", "2");
                                IDbundle.putString("PNCSl", data.getPNCSl());
                                Intent intent = new Intent(getApplicationContext(), NBC_PNCMothDetail.class);
                                intent.putExtras(IDbundle);
                                startActivityForResult(intent, 1);
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