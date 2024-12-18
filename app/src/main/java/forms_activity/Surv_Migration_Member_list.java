package forms_activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import forms_datamodel.Member_DataModel;

public class Surv_Migration_Member_list extends AppCompatActivity {
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
    private List<Member_DataModel> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
     String TableName;


    LinearLayout secLayer1;
    TextView lblLayer1;
    Spinner spnLayer1;
    LinearLayout secLayer2;
    TextView lblLayer2;
    Spinner spnLayer2;
    LinearLayout secLayer3;
    TextView lblLayer3;
    Spinner spnLayer3;
    LinearLayout secLayer4;
    TextView lblLayer4;
    Spinner spnLayer4;

    TextView lblHeading;
    TextView lblTotal;
    Button btnAdd;
    ImageButton btnSearch;
    EditText txtSearch;
    EditText dtpFDate;
    EditText dtpTDate;

    TextView lblCompound1;
    TextView lblMemHHID;
    TextView lblCompoundAddr;
     String STARTTIME = "";
     String DEVICEID = "";

     String ENTRYUSER = "";

    Button btnSES;
    ConstraintLayout ll_no_data;
     String MEMID = "";
     String HHID = "";
     String HHNO = "";
     String MSlNO = "";
     String HHIDHEAD = "";
     String COMPOUNDNAME = "";
     String COMPOUNDADRS = "";
     String HHHEAD = "";
     String ROUND = "";
     String EV_Type = "";
     String VISIT_DATE = "";
    String SQL = "";

    Bundle IDbundle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.surv_migration_member_list);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            C = new Connection(this);
            g = Global.getInstance();
            STARTTIME = g.CurrentTime24();

            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            HHID = IDbundle.getString("HHID");
            HHNO = IDbundle.getString("HHNO");
            MSlNO = IDbundle.getString("mslno");
            ROUND = IDbundle.getString("round");
            EV_Type = IDbundle.getString("evtype");
            VISIT_DATE = IDbundle.getString("visit_date");

            TableName = "migMember";
            lblHeading = (TextView) findViewById(R.id.lblHeading);
            lblTotal = (TextView) findViewById(R.id.lblTotal);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });

            secLayer1 = findViewById(R.id.secLayer1);
            lblLayer1 = findViewById(R.id.lblLayer1);
            spnLayer1 = findViewById(R.id.spnLayer1);

            secLayer2 = findViewById(R.id.secLayer2);
            lblLayer2 = findViewById(R.id.lblLayer2);
            spnLayer2 = findViewById(R.id.spnLayer2);

            secLayer3 = findViewById(R.id.secLayer3);
            lblLayer3 = findViewById(R.id.lblLayer3);
            spnLayer3 = findViewById(R.id.spnLayer3);

            secLayer4 = findViewById(R.id.secLayer4);
            lblLayer4 = findViewById(R.id.lblLayer4);
            spnLayer4 = findViewById(R.id.spnLayer4);



            txtSearch = (EditText) findViewById(R.id.txtSearch);

            btnSearch = (ImageButton) findViewById(R.id.btnSearch);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DataSearch(HHID);
                }
            });
            //=========================================================================================
            //Development
            //=========================================================================================
            if(ProjectSetting.SITE_CODE.equals(ProjectSetting.BANGLADESH_SITE1))
            {
                secLayer1.setVisibility(View.GONE);
                secLayer2.setVisibility(View.VISIBLE);
                secLayer3.setVisibility(View.VISIBLE);
                secLayer4.setVisibility(View.VISIBLE);
                lblLayer2.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER1);
                lblLayer3.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER2);
                lblLayer4.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER3);
                txtSearch.setHint("Name, DSSID, House Head, Mobile");
            }

            //=========================================================================================
            //Nigeria: Cross River
            //=========================================================================================
            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1))
            {
                secLayer1.setVisibility(View.GONE);
                secLayer2.setVisibility(View.VISIBLE);
                secLayer3.setVisibility(View.VISIBLE);
                secLayer4.setVisibility(View.VISIBLE);
                lblLayer2.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER1);
                lblLayer3.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER2);
                lblLayer4.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER3);
                txtSearch.setHint("Name, DSSID, House Head, Mobile");
            }

            //=========================================================================================
            //Nigeria: Bauchi
            //=========================================================================================
            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2))
            {
                secLayer1.setVisibility(View.GONE);
                secLayer2.setVisibility(View.VISIBLE);
                secLayer3.setVisibility(View.VISIBLE);
                secLayer4.setVisibility(View.VISIBLE);
                lblLayer2.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER1);
                lblLayer3.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER2);
                lblLayer4.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER3);
                txtSearch.setHint("Name, DSSID, House Head, Mobile");

            }

            //=========================================================================================
            //Sierra Leone: Makeni
            //=========================================================================================
            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1))
            {
                secLayer1.setVisibility(View.GONE);
                secLayer2.setVisibility(View.VISIBLE);
                secLayer3.setVisibility(View.VISIBLE);
                secLayer4.setVisibility(View.VISIBLE);
                lblLayer2.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER1);
                lblLayer3.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER2);
                lblLayer4.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER3);
                txtSearch.setHint("Name, DSSID, House Head, Mobile");
            }

            //=========================================================================================
            //Mali: Bamako
            //=========================================================================================
            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1))
            {
                secLayer1.setVisibility(View.GONE);
                secLayer2.setVisibility(View.VISIBLE);
                secLayer3.setVisibility(View.VISIBLE);
                secLayer4.setVisibility(View.VISIBLE);
                lblLayer2.setText(ProjectSetting.MALI_SITE1_GEO_LAYER1);
                lblLayer3.setText(ProjectSetting.MALI_SITE1_GEO_LAYER2);
                lblLayer4.setText(ProjectSetting.MALI_SITE1_GEO_LAYER3);
                txtSearch.setHint("Name, DSSID, House Head, Mobile");
            }


            SQL = "select '.All' union select distinct GeoLevel7||'-'||GeoLevel7Name from Location" +
                    " order by GeoLevel7||'-'||GeoLevel7Name";

            spnLayer2.setAdapter(C.getArrayAdapter(SQL));
            spnLayer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if(spnLayer2.getCount()==0) return;

                    if(spnLayer2.getSelectedItemPosition()==0){
                        SQL = "select '.All' union select distinct GeoLevel8||'-'||GeoLevel8Name from Location " +
                                " order by GeoLevel8||'-'||GeoLevel8Name";
                    }else {
                        SQL = "select '.All' union select distinct GeoLevel8||'-'||GeoLevel8Name from Location " +
                                " where GeoLevel7='" + spnLayer2.getSelectedItem().toString().split("-")[0] + "'" +
                                " order by GeoLevel8||'-'||GeoLevel8Name";
                    }
                    spnLayer3.setAdapter(C.getArrayAdapter(SQL));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });


            spnLayer3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if(spnLayer3.getCount()==0) return;
                    if(spnLayer3.getSelectedItemPosition()==0){
                        SQL = "select '.All' union Select cast(v.VillID as varchar(20))||'-'||v.VillName from Village v where isdelete=2";
                    }else {
                        String LocationID = C.ReturnSingleValue("Select distinct LocID from Location where GeoLevel8='" + spnLayer3.getSelectedItem().toString().split("-")[0] + "'");
                        SQL = "select '.All' union Select cast(v.VillID as varchar(20))||'-'||v.VillName from Village v  where v.LocID='" + LocationID + "' and isdelete=2";
                    }
                    spnLayer4.setAdapter(C.getArrayAdapter(SQL));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
            spnLayer4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    //if(!first_time_load)
//                        PageRefresh();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
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

            DataSearch(HHID);

        } catch (Exception e) {
            Connection.MessageBox(Surv_Migration_Member_list.this, e.getMessage());
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            //Write your code if there's no result
        } else {
            DataSearch(HHID);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        DataSearch(HHID);
    }



    private void DataSearch(String HHID) {
        try {
            Member_DataModel d = new Member_DataModel();

            String SQL = "Select m.MemID,'"+ HHID +"' HHID,m.DSSID,'"+ MSlNO +"' MSlNo,m.Name,m.Rth,m.Sex,m.Age,m.AgeU,m.FaNo,m.MoNo,m.EduY,m.MS," +
                    " ifnull(m.BDate,'')BDate,ifnull(m.Pstat,'')Pstat,ifnull(m.LmpDt,'')LmpDt,ifnull(m.Sp1,'')Sp1,ifnull(m.Sp2,'')Sp2," +
                    " ifnull(m.MoName,'')MName,ifnull(m.FaName,'')FName ,ifnull(m.Active,'')Active, m.isdelete,m.ExDate exdate " +
                    " from migMember m" +
                    " where " +
                    " ((m.Name like('%"+ txtSearch.getText().toString() +"%')" +
                    " or m.DSSID like('%"+ txtSearch.getText().toString() +"%')" +
                    " or m.MobileNo like('%"+ txtSearch.getText().toString() +"%')" +
                    " or m.HHHead like('%"+ txtSearch.getText().toString() +"%')))";

            if(spnLayer4.getCount()>0){
                SQL += " and m.VillID like '"+ (spnLayer4.getSelectedItem().toString().length()==0 ? "" : spnLayer4.getSelectedItem().toString().equals(".All") ? "" : spnLayer4.getSelectedItem().toString().split("-")[0]) +"%'";
            }

            /*String SQL = "Select m.MemID,m.HHID,m.DSSID,m.MSlNo,m.Name,m.Rth,m.Sex,m.Age,m.AgeU,m.FaNo,m.MoNo,m.EduY,m.MS," +
                    " ifnull(m.BDate,'')BDate,ifnull(m.Pstat,'')Pstat,ifnull(m.LmpDt,'')LmpDt,ifnull(m.Sp1,'')Sp1,ifnull(m.Sp2,'')Sp2," +
                    " ifnull(mo.Name,'')MName,ifnull(fa.Name,'')FName ,ifnull(m.Active,'')Active, m.isdelete " +
                    " from migMember m\n" +
                    " inner join Household h on h.HHID=m.HHID\n" +
                    " left join tmpMember mo on m.HHID=mo.HHID and  m.MoNo=mo.MSlNo\n" +
                    " left join tmpMember fa on m.HHID=fa.HHID and m.FaNo=fa.MSlNo" +
                    " where " +
                    " ((m.Name like('%"+ txtSearch.getText().toString() +"%')" +
                    " or m.DSSID like('%"+ txtSearch.getText().toString() +"%')" +
                    " or m.MobileNo like('%"+ txtSearch.getText().toString() +"%')))" +
                    " or m.HHead like('%"+ txtSearch.getText().toString() +"%')))" +
                    " and h.VillID like '"+ (spnLayer4.getSelectedItem().toString().length()==0 ? "" : spnLayer4.getSelectedItem().toString().equals(".All") ? "" : spnLayer4.getSelectedItem().toString().split("-")[0]) +"%'";*/

            List<Member_DataModel> data = d.SelectMemberMigration(this, SQL);
            dataList.clear();

            dataList.addAll(data);
//             if (dataList != null && !dataList.isEmpty())
//             {
//                 recyclerView.setVisibility(View.VISIBLE);
//                 ll_no_data.setVisibility(View.GONE);
//             }
//             else
//             {
//                 recyclerView.setVisibility(View.GONE);
//                 ll_no_data.setVisibility(View.VISIBLE);
//             }
            try {
                mAdapter.notifyDataSetChanged();
                lblTotal.setText(" (Total: " + dataList.size() + ")");
            } catch (Exception ex) {
                Connection.MessageBox(Surv_Migration_Member_list.this, ex.getMessage());
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_Migration_Member_list.this, e.getMessage());
            return;
        }
    }

    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
        private List<Member_DataModel> dataList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout secListRow;
            TextView MemID;
            TextView HHID;
            TextView MSlNo;
            TextView Name;
            TextView FaNo;
            TextView MoNo;
            TextView DSSID;
            TextView EduY;
            TextView MS;
            TextView Rth;
            TextView RthOth;
            TextView Sex;
            TextView Ocp;
            TextView BDate;
            TextView Pstat;
            TextView LmpDt;
            TextView Sp1;
            TextView Sp2;
            TextView Age;

            public MyViewHolder(View convertView) {
                super(convertView);
                secListRow = (LinearLayout) convertView.findViewById(R.id.secListRow);
                MemID = (TextView) convertView.findViewById(R.id.MemID);
                MSlNo = (TextView) convertView.findViewById(R.id.MSlNo);
                Name = (TextView) convertView.findViewById(R.id.Name);
                FaNo = (TextView) convertView.findViewById(R.id.FaNo);
                MoNo = (TextView) convertView.findViewById(R.id.MoNo);
                DSSID = (TextView) convertView.findViewById(R.id.DSSID);
                Sex = (TextView) convertView.findViewById(R.id.Sex);
                Rth = (TextView) convertView.findViewById(R.id.Rth);
                //EduY = (TextView) convertView.findViewById(R.id.EduY);
                MS = (TextView) convertView.findViewById(R.id.MS);
                BDate = (TextView) convertView.findViewById(R.id.BDate);
                Pstat = (TextView) convertView.findViewById(R.id.Pstat);
                LmpDt = (TextView) convertView.findViewById(R.id.LmpDt);
                //Sp1 = (TextView) convertView.findViewById(R.id.Sp1);
                Age = (TextView) convertView.findViewById(R.id.Age);
            }
        }

        public DataAdapter(List<Member_DataModel> datalist) {
            this.dataList = datalist;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.surv_mig_member_row, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final Member_DataModel data = dataList.get(position);
//             holder.MemID.setText(data.getMemID());
            holder.MSlNo.setText(data.getMSlNo());
            holder.Name.setText(data.getName());
            holder.FaNo.setText(": " + data.getFName());
            holder.MoNo.setText(": " + data.getMoName());
            holder.DSSID.setText("DSS ID: " + data.getDSSID());
            //holder.Sex.setText(String.valueOf(": " + data.getSex()));
            if (data.getSex().equals("1")) holder.Sex.setText(": M");
            else if (data.getSex().equals("2")) holder.Sex.setText(": F");
            else if (data.getSex().equals("3")) holder.Sex.setText(": I");
            else if (data.getSex().equals("4")) holder.Sex.setText(": T");
            else if (data.getSex().equals("5")) holder.Sex.setText(": U");

            //holder.EduY.setText(": " + data.getEduY());
            holder.MS.setText(": " + String.valueOf(data.getMS()));
            holder.Rth.setText(": " + data.getRth());
            holder.BDate.setText(": " + Global.DateConvertDMY(data.getBDate()));
            holder.LmpDt.setText(": " + Global.DateConvertDMY(data.getLmpDt()));
            //holder.Sp1.setText(": " + Global.DateConvertDMY(data.getSp1()));
            holder.Pstat.setText(": " + Global.DateConvertDMY(data.getPstat()));
            holder.BDate.setText(": " + Global.DateConvertDMY(data.getBDate()));
            holder.Age.setText(": " + data.getAge());

            holder.secListRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("MovementID", "");
                    IDbundle.putString("MemID", data.getMemID());
                    IDbundle.putString("HHID", data.getHHID());
                    IDbundle.putString("HHNO", HHNO);
                    IDbundle.putString("evtype", EV_Type);
                    IDbundle.putString("exdate", data.getExDate());
                    IDbundle.putString("vdate", VISIT_DATE);
                    IDbundle.putString("round", ROUND);
                    IDbundle.putString("edit", "1");

                    String level1 = spnLayer2.getCount()==0 || spnLayer2.getSelectedItemPosition()==0?"":spnLayer2.getSelectedItem().toString().split("-")[0];
                    String level2 = spnLayer3.getCount()==0 || spnLayer3.getSelectedItemPosition()==0?"":spnLayer3.getSelectedItem().toString().split("-")[0];
                    String level3 = spnLayer4.getCount()==0 || spnLayer4.getSelectedItemPosition()==0?"":spnLayer4.getSelectedItem().toString().split("-")[0];

                    IDbundle.putString("address_level1", level1);
                    IDbundle.putString("address_level2", level2);
                    IDbundle.putString("address_level3", level3);
                    finish();
                    Intent intent = new Intent(getApplicationContext(), Surv_Movement.class);
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