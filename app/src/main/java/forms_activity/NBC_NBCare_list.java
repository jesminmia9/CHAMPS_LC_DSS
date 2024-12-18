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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import org.icddrb.champs_lc_dss.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Common.Connection;
import Common.Global;
import Utility.MySharedPreferences;
import forms_datamodel.NBC_NBCare_DataModel;

public class NBC_NBCare_list extends AppCompatActivity {
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
    private List<NBC_NBCare_DataModel> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
     String TableName;

    TextView LBLMEMID;
    TextView LBLHHID;
    TextView lblHeading;
    TextView lblTotal;
    Button btnAdd;
    ImageButton btnSearch;
    EditText txtSearch;
    EditText dtpFDate;
    EditText dtpTDate;

     String STARTTIME = "";
     String DEVICEID = "";

     String ENTRYUSER = "";

    ConstraintLayout ll_no_data;
    Bundle IDbundle;
     String NBID = "";
     String PGN = "";
     String MEMID = "";
     String HHID = "";
     String ROUND = "";
     String MEMNAME = "";
     String MSLNO = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.nbc_nbcare_list);
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
            MEMNAME = IDbundle.getString("MEMNAME");
            MSLNO = IDbundle.getString("MSlNo");

            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");
            TableName = "NBC_NBCare";
            lblHeading = (TextView) findViewById(R.id.lblHeading);
            lblHeading.setText("NEW BIRTHS/NEWBORN CHARACTERISTICS");
            lblTotal = (TextView) findViewById(R.id.lblTotal);

            LBLMEMID = (TextView)findViewById(R.id.labelMemId);
            LBLHHID = (TextView)findViewById(R.id.labelhhid);

            LBLMEMID.setText(MSLNO+". "+MEMNAME);
            LBLHHID.setText("HHID: "+HHID);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });

            btnAdd = (Button) findViewById(R.id.btnAdd);
            btnAdd.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    String PGNCount = C.ReturnSingleValue("SELECT COUNT(*) FROM NBC_NBCare where MemID='"+MEMID+"' and HHID='"+HHID+"'");
                    int totalPregnancies = Integer.parseInt(PGNCount);
                    String newPGN = String.format("%02d", totalPregnancies + 1);

                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("NBID", "");
                    IDbundle.putString("PGN", newPGN);
                    IDbundle.putString("MemID", MEMID);
                    IDbundle.putString("HHID", HHID);
                    IDbundle.putString("round", ROUND);
                    IDbundle.putString("title", lblHeading.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), NBC_NBCare.class);
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



            DataSearch(HHID,MEMID);


        } catch (Exception e) {
            Connection.MessageBox(NBC_NBCare_list.this, e.getMessage());
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            //Write your code if there's no result
        } else {
            DataSearch(HHID,MEMID);
        }
    }

    private void DataSearch(String HHID, String MEMID) {
        try {

            NBC_NBCare_DataModel d = new NBC_NBCare_DataModel();
            //String SQL = "Select * from " + TableName + "  Where MemID='"+MEMID+"' and HHID='"+HHID+"' and isdelete=2";
            String SQL = "select c1.*,c2.PregType from NBC_NBCare c1\n" +
                    " left outer join NBC_NBCareDetail c2 on c1.MemID=c2.MEMID  and c1.HHID=c2.HHID and c1.PGN=c2.PGN \n" +
                    " where c1.MemID='"+ MEMID +"' and c1.HHID='"+ HHID +"' and c1.isdelete=2";

            List<NBC_NBCare_DataModel> data = d.SelectAllwithPregType(this, SQL);
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
                Connection.MessageBox(NBC_NBCare_list.this, ex.getMessage());
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_NBCare_list.this, e.getMessage());
            return;
        }
    }


    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
        private List<NBC_NBCare_DataModel> dataList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout secListRow;
            LinearLayout secModules;
            TextView NBID;
            AppCompatButton NBCareDetail;
            AppCompatButton ANC;
            AppCompatButton PNC;
            TextView PGN;
            TextView OutcomeType;

            public MyViewHolder(View convertView) {
                super(convertView);
                secListRow = (LinearLayout) convertView.findViewById(R.id.secListRow);
                secModules = (LinearLayout) convertView.findViewById(R.id.secModules);
                NBID = (TextView) convertView.findViewById(R.id.NBID);
                PGN = (TextView) convertView.findViewById(R.id.PGN);
                OutcomeType = (TextView) convertView.findViewById(R.id.OutcomeType);
                NBCareDetail = convertView.findViewById(R.id.NBCareDetail);
                ANC = convertView.findViewById(R.id.ANC);
                PNC = convertView.findViewById(R.id.PNC);
            }
        }

        public DataAdapter(List<NBC_NBCare_DataModel> datalist) {
            this.dataList = datalist;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.nbc_nbcare_row, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final NBC_NBCare_DataModel data = dataList.get(position);
            holder.NBID.setVisibility(View.GONE);
            holder.NBID.setText("NBID: "+data.getNBID());
            holder.PGN.setText(data.getPGN());
            if(data.getPregLVisit().equals("1")) {
                if (data.getOutcomeType().equals("1")) {
                    holder.OutcomeType.setVisibility(View.VISIBLE);
                    holder.OutcomeType.setText("Pregnancy type: Single");
                } else if (data.getOutcomeType().equals("2")) {
                    holder.OutcomeType.setVisibility(View.VISIBLE);
                    holder.OutcomeType.setText("Pregnancy type: Multiple");
                } else {
                    holder.OutcomeType.setVisibility(View.GONE);
                }
                holder.secModules.setVisibility(View.VISIBLE);
            }else{
                holder.OutcomeType.setVisibility(View.VISIBLE);
                holder.secModules.setVisibility(View.GONE);
                holder.OutcomeType.setText("No Pregnancy");

            }

            holder.NBCareDetail.setBackgroundResource(R.drawable.style_not_completed_square_shape);
            holder.ANC.setBackgroundResource(R.drawable.button_style_circle_line_disable);
            holder.PNC.setBackgroundResource(R.drawable.button_style_circle_line_disable);
            holder.ANC.setEnabled(false);
            holder.ANC.setVisibility(View.INVISIBLE);
            holder.PNC.setEnabled(false);
            holder.PNC.setVisibility(View.INVISIBLE);
            if(C.Existence("Select * from NBC_NBCareDetail  Where MemID='"+ data.getMemID() +"' and HHID='"+ data.getHHID() +"'")){
                holder.NBCareDetail.setBackgroundResource(R.drawable.style_completed_square_shape);
                holder.NBCareDetail.setTextColor(Color.WHITE);
            }
            if(data.getPregType() != null){
                if(data.getPregType().equals("1") || data.getPregType().equals("3")){
                    holder.ANC.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                    holder.ANC.setEnabled(true);
                    holder.ANC.setVisibility(View.VISIBLE);
                    holder.PNC.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                    holder.PNC.setEnabled(true);
                    holder.PNC.setVisibility(View.VISIBLE);

                    if(C.Existence("Select * from NBC_ANC  Where MemID='"+ data.getMemID() +"' and HHID='"+ data.getHHID() +"' and PGN='"+ data.getPGN() +"'")){
                        holder.ANC.setBackgroundResource(R.drawable.style_completed_square_shape);
                        holder.ANC.setTextColor(Color.WHITE);
                    }
                    if(C.Existence("Select * from NBC_PNCMoth  Where MemID='"+ data.getMemID() +"' and HHID='"+ data.getHHID() +"' and PGN='"+ data.getPGN() +"'")){
                        holder.PNC.setBackgroundResource(R.drawable.style_completed_square_shape);
                        holder.PNC.setTextColor(Color.WHITE);
                    }
                }
            }




            holder.secListRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(NBC_NBCare_list.this, "", "Please Wait . . .", true);
                    new Thread() {
                        public void run() {
                            try {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("NBID", data.getNBID());
                                IDbundle.putString("PGN", data.getPGN());
                                IDbundle.putString("MemID", data.getMemID());
                                IDbundle.putString("HHID", HHID);
                                IDbundle.putString("round", ROUND);
                                IDbundle.putString("title", lblHeading.getText().toString());

                                Intent f1 = new Intent(getApplicationContext(), NBC_NBCare.class);
                                f1.putExtras(IDbundle);
                                startActivityForResult(f1, 1);
                            } catch (Exception e) {
                            }
                            progDailog.dismiss();
                        }
                    }.start();
                }
            });
            holder.NBCareDetail.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("NBID", "");
                    IDbundle.putString("PGN", data.getPGN());
                    IDbundle.putString("MemID", MEMID);
                    IDbundle.putString("HHID", HHID);
                    IDbundle.putString("round", ROUND);
                    IDbundle.putString("totalOutcome", data.getOutcomeType().toString().equals("2") ? data.getBirthNo() : "1" );
                    IDbundle.putString("vdate", Global.DateNowYMD());
                    IDbundle.putString("MEMNAME", MEMNAME);
                    IDbundle.putString("MSlNo", MSLNO);
                    IDbundle.putString("title", "DELIVERY CARE DETAIL");
                    Intent intent = new Intent(getApplicationContext(), NBC_NBCareDetail_list.class);
                    intent.putExtras(IDbundle);
                    startActivityForResult(intent, 1);
                }
            });

            holder.ANC.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("NBID", "");
                    IDbundle.putString("PGN", data.getPGN());
                    IDbundle.putString("MemID", MEMID);
                    IDbundle.putString("HHID", HHID);
                    IDbundle.putString("round", ROUND);
                    IDbundle.putString("title", "ANTENATAL CARE (B2)");
                    Intent intent = new Intent(getApplicationContext(), NBC_ANC.class);
                    intent.putExtras(IDbundle);
                    startActivityForResult(intent, 1);
                }
            });

            holder.PNC.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("NBID", "");
                    IDbundle.putString("PGN", data.getPGN());
                    IDbundle.putString("MemID", MEMID);
                    IDbundle.putString("HHID", HHID);
                    IDbundle.putString("round", ROUND);
                    IDbundle.putString("title", "POSTNATAL CARE (B2)");
                    Intent intent = new Intent(getApplicationContext(), NBC_PNCMoth.class);
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

    @Override
    protected void onResume() {
        super.onResume();
        DataSearch(HHID,MEMID);
    }
}