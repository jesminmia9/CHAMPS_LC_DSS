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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.icddrb.champs_lc_dss.Fragment_Main;
import org.icddrb.champs_lc_dss.LoginActivity;
import org.icddrb.champs_lc_dss.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Common.Connection;
import Common.Global;
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import forms_datamodel.Compound_DataModel;

public class Surv_Compound_list extends AppCompatActivity {
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
    Button btnLandmark;

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

    TextView lblTotalComp;
    TextView lblTotalHH;
    TextView lblTotalMem;
    TextView lblTotalLandmark;
    TextView lblNameCompound;
    TextView lblNameHousehold;

    Spinner spnRound;

    static String STARTTIME = "";
    static String DEVICEID  = "";

    static String ENTRYUSER = "";

    Bundle IDbundle;
    ConstraintLayout ll_no_data;
    static String COMPOUNDID = "";
    static String COMPOUNDCODE = "";
    static String VIllID = "";
    static String LMNO = "";
    static String TotalHH = "";
    static String SurvType = "";

    static String LOCATION_ID = "";
    static String LOCATION_NAME = "";
    static String ROUND = "0";
    String SQL = "";
    boolean isFromActivityResult;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            setContentView(R.layout.surv_compound_list);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            C = new Connection(this);
            g = Global.getInstance();
            STARTTIME = g.CurrentTime24();

            IDbundle = getIntent().getExtras();

            LMNO = IDbundle.getString("LMNO");
            LOCATION_ID = IDbundle.getString("locationid");
            LOCATION_NAME = IDbundle.getString("locationname");
            SurvType = IDbundle.getString("SurvType");

            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");
            TableName = "Compound";
            lblHeading = (TextView)findViewById(R.id.lblHeading);
            lblTotal = (TextView)findViewById(R.id.lblTotal);

            spnRound = findViewById(R.id.spnRound);
            spnRound.setAdapter(C.getArrayAdapter("Select RoundNo from DSSRound order by RoundNo desc limit 1"));
            spnRound.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    //PageRefresh();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
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


            //Label
            lblHeading.setText(ProjectSetting.COMPOUND_LABEL + " List");
            lblNameCompound = findViewById(R.id.lblNameCompound);
            lblNameHousehold = findViewById(R.id.lblNameHousehold);
            lblNameCompound.setText(ProjectSetting.COMPOUND_LABEL);
            lblNameHousehold.setText(ProjectSetting.HOUSEHOLD_LABEL);
            txtSearch = (EditText)findViewById(R.id.txtSearch);

            //=========================================================================================
            //Development
            //=========================================================================================
            if(ProjectSetting.SITE_CODE.equals(ProjectSetting.BANGLADESH_SITE1))
            {
                secLayer1.setVisibility(View.GONE);
                secLayer2.setVisibility(View.VISIBLE);
                secLayer3.setVisibility(View.VISIBLE);
                secLayer4.setVisibility(View.VISIBLE);
                lblLayer2.setText(ProjectSetting.BANGLADESH_SITE1_GEO_LAYER1);
                lblLayer3.setText(ProjectSetting.BANGLADESH_SITE1_GEO_LAYER2);
                lblLayer4.setText(ProjectSetting.BANGLADESH_SITE1_GEO_LAYER3);
                txtSearch.setHint("Bari ID");

                SQL = " select distinct GeoLevel7||'-'||GeoLevel7Name from Village v \n" +
                        " inner join Location l on v.LocID=l.LocID\n" +
                        " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and WorkGroup='3' and Active='1' " +
                        " order by GeoLevel7||'-'||GeoLevel7Name";

                spnLayer2.setAdapter(C.getArrayAdapter(SQL));
                spnLayer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        if(spnLayer2.getCount()==0) return;

                        SQL = " select distinct GeoLevel8||'-'||GeoLevel8Name from Village v \n" +
                                " inner join Location l on v.LocID=l.LocID\n" +
                                " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and WorkGroup='3' and Active='1' " +
                                " where GeoLevel7='"+ spnLayer2.getSelectedItem().toString().split("-")[0] +"'" +
                                " order by GeoLevel8||'-'||GeoLevel8Name";

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
                        String LocationID = C.ReturnSingleValue("Select distinct LocID from Location where GeoLevel8='"+ spnLayer3.getSelectedItem().toString().split("-")[0] +"'");

                        spnLayer4.setAdapter(C.getArrayAdapter("Select cast(v.VillID as varchar(20))||'-'||v.VillName from Village v inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' where v.LocID='"+ LocationID +"' and isdelete=2"));
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
                        PageRefresh();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });


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
                txtSearch.setHint("House ID");

                SQL = " select distinct GeoLevel7||'-'||GeoLevel7Name from Village v \n" +
                        " inner join Location l on v.LocID=l.LocID\n" +
                        " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                        " order by GeoLevel7||'-'||GeoLevel7Name";

                spnLayer2.setAdapter(C.getArrayAdapter(SQL));
                spnLayer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        if(spnLayer2.getCount()==0) return;

                        SQL = " select distinct GeoLevel8||'-'||GeoLevel8Name from Village v \n" +
                                " inner join Location l on v.LocID=l.LocID\n" +
                                " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                                " where GeoLevel7='"+ spnLayer2.getSelectedItem().toString().split("-")[0] +"'" +
                                " order by GeoLevel8||'-'||GeoLevel8Name";

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
                        String LocationID = C.ReturnSingleValue("Select distinct LocID from Location where GeoLevel8='"+ spnLayer3.getSelectedItem().toString().split("-")[0] +"'");

                        spnLayer4.setAdapter(C.getArrayAdapter("Select cast(v.VillID as varchar(20))||'-'||v.VillName from Village v inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' where v.LocID='"+ LocationID +"' and isdelete=2"));
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
                        PageRefresh();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });


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
                txtSearch.setHint("Structure ID");

                SQL = " select distinct GeoLevel7||'-'||GeoLevel7Name from Village v \n" +
                        " inner join Location l on v.LocID=l.LocID\n" +
                        " inner join DataCollector_AssignArea a on v.villid=a.villid and and a.WorkGroup='3' a.userid='"+ ENTRYUSER +"' and Active='1' " +
                        " order by GeoLevel7||'-'||GeoLevel7Name";

                spnLayer2.setAdapter(C.getArrayAdapter(SQL));
                spnLayer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        if(spnLayer2.getCount()==0) return;

                        SQL = " select distinct GeoLevel8||'-'||GeoLevel8Name from Village v \n" +
                                " inner join Location l on v.LocID=l.LocID\n" +
                                " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and a.Active='1' " +
                                " where GeoLevel7='"+ spnLayer2.getSelectedItem().toString().split("-")[0] +"'" +
                                " order by GeoLevel8||'-'||GeoLevel8Name";

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
                        String LocationID = C.ReturnSingleValue("Select distinct LocID from Location where GeoLevel8='"+ spnLayer3.getSelectedItem().toString().split("-")[0] +"'");

                        spnLayer4.setAdapter(C.getArrayAdapter("Select cast(v.VillID as varchar(20))||'-'||v.VillName from Village v inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' where v.LocID='"+ LocationID +"' and isdelete=2"));
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
                        PageRefresh();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });
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
                txtSearch.setHint("Structure ID");

                SQL = " select distinct GeoLevel7||'-'||GeoLevel7Name from Village v \n" +
                        " inner join Location l on v.LocID=l.LocID\n" +
                        " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                        " order by GeoLevel7||'-'||GeoLevel7Name";

                spnLayer2.setAdapter(C.getArrayAdapter(SQL));
                spnLayer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        if(spnLayer2.getCount()==0) return;

                        SQL = " select distinct GeoLevel8||'-'||GeoLevel8Name from Village v \n" +
                                " inner join Location l on v.LocID=l.LocID\n" +
                                " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                                " where GeoLevel7='"+ spnLayer2.getSelectedItem().toString().split("-")[0] +"'" +
                                " order by GeoLevel8||'-'||GeoLevel8Name";

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
                        String LocationID = C.ReturnSingleValue("Select distinct LocID from Location where GeoLevel8='"+ spnLayer3.getSelectedItem().toString().split("-")[0] +"'");

                        spnLayer4.setAdapter(C.getArrayAdapter("Select cast(v.VillID as varchar(20))||'-'||v.VillName from Village v inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' where v.LocID='"+ LocationID +"' and isdelete=2"));
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
                        PageRefresh();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });


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
                txtSearch.setHint("Concession ID");

                SQL = " select distinct GeoLevel7||'-'||GeoLevel7Name from Village v \n" +
                        " inner join Location l on v.LocID=l.LocID\n" +
                        " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                        " order by GeoLevel7||'-'||GeoLevel7Name";

                spnLayer2.setAdapter(C.getArrayAdapter(SQL));
                spnLayer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        if(spnLayer2.getCount()==0) return;

                        SQL = " select distinct GeoLevel8||'-'||GeoLevel8Name from Village v \n" +
                                " inner join Location l on v.LocID=l.LocID\n" +
                                " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                                " where GeoLevel7='"+ spnLayer2.getSelectedItem().toString().split("-")[0] +"'" +
                                " order by GeoLevel8||'-'||GeoLevel8Name";

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
                        String LocationID = C.ReturnSingleValue("Select distinct LocID from Location where GeoLevel8='"+ spnLayer3.getSelectedItem().toString().split("-")[0] +"'");

                        spnLayer4.setAdapter(C.getArrayAdapter("Select cast(v.VillID as varchar(20)) from Village v inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' where v.LocID='"+ LocationID +"' and isdelete=2"));
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
                        PageRefresh();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });


            }


            btnLandmark = findViewById(R.id.btnLandmark);
            if(ProjectSetting.LANDMARK_COLLECT.equals((ProjectSetting.YES))){
                btnLandmark.setVisibility(View.VISIBLE);
            }else{
                btnLandmark.setVisibility(View.GONE);
            }

            lblTotalComp = findViewById(R.id.lblTotalComp);
            lblTotalHH = findViewById(R.id.lblTotalHH);
            lblTotalMem = findViewById(R.id.lblTotalMem);
            lblTotalLandmark = findViewById(R.id.lblTotalLandmark);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }});

            btnAdd = (Button) findViewById(R.id.btnAdd);
            btnAdd.setText("+ New "+ ProjectSetting.COMPOUND_LABEL);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    if(spnLayer4.getCount()==0 || spnLayer4.getSelectedItem().toString().length()==0){
                        Connection.MessageBox(Surv_Compound_list.this,"Select a valid "+ lblLayer4.getText().toString() +" from the list.");
                        spnLayer4.requestFocus();
                    }else {
                        Bundle IDbundle = new Bundle();
                        IDbundle.putString("VillID", spnLayer4.getSelectedItem().toString().split("-")[0]);

                        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                            IDbundle.putString("villname", spnLayer4.getSelectedItem().toString().split("-")[0].toString());
                        }else{
                            IDbundle.putString("villname", spnLayer4.getSelectedItem().toString().split("-")[1].toString());
                        }
                        IDbundle.putString("locationid", LOCATION_ID);
                        IDbundle.putString("CompoundCode", "");
                        IDbundle.putString("CompoundID", "");
                        IDbundle.putString("cluster", "");
                        IDbundle.putString("block", "");
                        IDbundle.putString("round", spnRound.getSelectedItem().toString());
                        Intent intent = new Intent(getApplicationContext(), Surv_Compound.class);
                        intent.putExtras(IDbundle);
                        startActivityForResult(intent, 1);
                    }
                }});
            txtSearch = (EditText)findViewById(R.id.txtSearch);
            btnSearch = (ImageButton) findViewById(R.id.btnSearch);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PageRefresh();

                }});

            btnLandmark = (Button) findViewById(R.id.btnLandmark);
            btnLandmark.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if(spnLayer4.getCount()==0 || spnLayer4.getSelectedItem().toString().length()==0){
                        Connection.MessageBox(Surv_Compound_list.this,"Select a valid "+ lblLayer4.getText().toString() +" from the list.");
                        spnLayer4.requestFocus();
                    }
                    else {
                        Bundle IDbundle = new Bundle();
                        IDbundle.putString("IDNO", "");
                        IDbundle.putString("VillID", spnLayer4.getSelectedItem().toString().split("-")[0]);
                        IDbundle.putString("COMPOUNDID", "");
                        IDbundle.putString("GPSTYPE", "1");
                        IDbundle.putString("LMSL", "");
                        IDbundle.putString("round", spnRound.getSelectedItem().toString());
                        Intent intent = new Intent(getApplicationContext(), GPS_list.class);
                        intent.putExtras(IDbundle);
                        startActivityForResult(intent, 1);
                    }
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


        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Compound_list.this, e.getMessage());
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if( isFromActivityResult) {
            PageRefresh();
            isFromActivityResult = false;
        }
    }

    private void PageRefresh()
    {
        final ProgressDialog progDailog = ProgressDialog.show(Surv_Compound_list.this, "", "Please Wait . . .", true);

        new Thread() {
            public void run() {
                try {
                    String ROUND_NUMBER = spnRound.getSelectedItem().toString();

                    if(spnLayer4.getCount()>0)
                        VIllID = spnLayer4.getSelectedItem().toString().length()==0 ? "" : spnLayer4.getSelectedItem().toString().split("-")[0].toString();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String totalhh = C.ReturnSingleValue("select count(*)totalhh from household where isdelete='2' and VillID='"+ VIllID +"'");
                            lblTotalHH.setText(totalhh);
                            String totalmem = C.ReturnSingleValue("select count(*)totalmem from household h inner join member m on h.HHID=m.HHID where m.isdelete='2' and h.VillID='"+ VIllID +"'");
                            lblTotalMem.setText(totalmem);
                            String totallandmark = C.ReturnSingleValue("select count(*)totalgps from GPS where GPSType='1' and isdelete='2' and VillID='"+ VIllID +"'");
                            lblTotalLandmark.setText(totallandmark);

                            DataSearch(VIllID, ROUND_NUMBER);

                            progDailog.dismiss();
                        }
                    });
                } catch (Exception e) {

                }
                progDailog.dismiss();
            }
        }.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            //Write your code if there's no result
        } else {
            //DataSearch(txtSearch.getText().toString());
        }
        isFromActivityResult = true;
    }

    private void DataSearch(String VillageID, String ROUND_NUMBER)
    {
        try
        {
            Compound_DataModel d = new Compound_DataModel();

            String SQL = "Select c.Cluster,c.Block,c.Rnd,c.CompoundID,c.VillID,c.CompoundCode,c.CompoundName,c.CompoundAdrs,c.TotalHH compound_totalhh,\n" +
                    " ifnull(g.compoundid,'')gps,ifnull(g.gpsstatus,'')gpsstatus," +
                    " Count(h.HHID) TotalHH,\n" +
                    " sum(case when v1.HHID IS Not null then 1 else 0 end)Visited," +
                    " '' ListingStatus\n" +
                    " from "+ TableName +" c \n" +
                    " left join Household h on c.CompoundID=h.CompoundID and (Case when h.Active is null or length(h.Active)=0 then '1' else h.Active end)='1'\n" +
                    " left join (Select HHID,max(Visitno)visitno from Visits where Rnd='"+ ROUND_NUMBER +"' group by HHID)v1 \n" +
                    "           on h.HHID=v1.HHID\n" +
                    " left outer join (select distinct CompoundID,GPSStatus from GPS where GPSType='2' and VillID='"+ VillageID +"')g" +
                    "           on c.CompoundID=g.CompoundID" +
                    " where c.isdelete='2'\n" +
                    " and c.VillID='"+ (VillageID.length()==0?"0":VillageID) +"'\n" +
                    " and (c.compoundcode like('"+ txtSearch.getText().toString() +"%') or c.CompoundName like('%"+ txtSearch.getText().toString() +"%'))\n" +
                    " Group by c.Cluster,c.Block, c.CompoundID,c.VIllID,c.CompoundCode,c.CompoundName,c.CompoundAdrs";

            //cross river
            if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1))
                SQL += " order by cast(substr(c.CompoundCode,8,3) as int) asc";

            //bauchi
            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2))
                SQL += " order by cast(substr(c.CompoundCode,10,3) as int) asc";

            //sierra leone
            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1))
                SQL += " order by cast(substr(c.CompoundCode,7,3) as int) asc";

            //mali
            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1))
                SQL += " order by cast(substr(c.CompoundCode,4,4) as int) asc";

            List<Compound_DataModel> data = d.SelectAll_ListingBaseline(this, SQL);
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
                lblTotalComp.setText(String.valueOf(dataList.size()));
            }catch ( Exception ex){
                Connection.MessageBox(Surv_Compound_list.this,ex.getMessage());
            }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Compound_list.this, e.getMessage());
            return;
        }
    }

    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
        private List<Compound_DataModel> dataList;
        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout secListRow;

            TextView CompoundCode;
            TextView CompoundName;
            TextView CompoundAdrs;
            TextView VillID;
            TextView TotalHH;
            TextView lblCompoundLabel;

            public MyViewHolder(View convertView) {
                super(convertView);
                secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
                CompoundCode = (TextView)convertView.findViewById(R.id.CompoundCode);
                CompoundName = (TextView)convertView.findViewById(R.id.CompoundName);
                CompoundAdrs = (TextView)convertView.findViewById(R.id.CompoundAdrs);
                lblCompoundLabel = (TextView)convertView.findViewById(R.id.lblCompoundLabel);
                TotalHH = (TextView)convertView.findViewById(R.id.TotalHH);
            }
        }
        public DataAdapter(List<Compound_DataModel> datalist) {
            this.dataList = datalist;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.surv_compound_row, parent, false);
            return new MyViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final Compound_DataModel data = dataList.get(position);
            holder.CompoundCode.setText(data.getCompoundCode());
            holder.CompoundName.setText(data.getCompoundName());
            holder.CompoundAdrs.setText(data.getCompoundAdrs());
            holder.lblCompoundLabel.setText(ProjectSetting.COMPOUND_LABEL+" ID");

            holder.TotalHH.setText(data.getVisited() + "/" + data.getTotalHH());
            if(data.getVisited().equalsIgnoreCase("0")) {
                holder.TotalHH.setBackgroundResource(R.drawable.style_not_done);
                holder.TotalHH.setTextColor(Color.BLACK);
            }else if(data.getVisited().equals(data.getTotalHH())) {
                holder.TotalHH.setBackgroundResource(R.drawable.style_completed);
                holder.TotalHH.setVisibility(View.VISIBLE);
                holder.TotalHH.setTextColor(Color.WHITE);
            }else {
                holder.TotalHH.setBackgroundResource(R.drawable.style_not_completed);
                holder.TotalHH.setVisibility(View.VISIBLE);
                holder.TotalHH.setTextColor(Color.WHITE);
            }

            holder.secListRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(Surv_Compound_list.this, "", "Please Wait . . .", true);
                    new Thread() {
                        public void run() {
                            try {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("VillID", data.getVillID());
                                if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                                    IDbundle.putString("villname", Connection.split(spnLayer4.getSelectedItem().toString(),'-')[0].toString());
                                }else{
                                    IDbundle.putString("villname", Connection.split(spnLayer4.getSelectedItem().toString(),'-')[1].toString());
                                }

                                IDbundle.putString("CompoundID", data.getCompoundID());
                                IDbundle.putString("CompoundCode", data.getCompoundCode());
                                IDbundle.putString("CompoundName", data.getCompoundName());
                                IDbundle.putString("CompoundAdrs", data.getCompoundAdrs());
                                IDbundle.putString("TotalHH", data.getTotalHH());
                                IDbundle.putString("cluster", data.getCluster());
                                IDbundle.putString("block", data.getBlock());
                                IDbundle.putString("round", spnRound.getSelectedItem().toString());
                                IDbundle.putString("SurvType", SurvType);
                                Intent f1 = new Intent(getApplicationContext(), Surv_Household_list.class);
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
            /*dtpDate = (EditText)findViewById(R.id.dtpFDate);
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
                .append(mYear));*/
        }
    };



}