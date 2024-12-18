
package forms_activity;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.icddrb.champs_lc_dss.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Common.Connection;
import Common.Global;
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import forms_datamodel.NBC_ANCDetail_DataModel;
import forms_datamodel.NBC_ANC_DataModel;

public class NBC_ANC extends AppCompatActivity {
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

    boolean networkAvailable = false;
    Location currentLocation;
    double currentLatitude, currentLongitude;
    String VariableID;
    private int hour;
    private int minute;
    private int mDay;
    private int mMonth;
    private int mYear;
     final int DATE_DIALOG = 1;
     final int TIME_DIALOG = 2;

    private List<NBC_ANCDetail_DataModel> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
    Button btnAdd;

    Connection C;
    Global g;
    SimpleAdapter dataAdapter;
    //    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    TextView lblHeading;
    LinearLayout secAncList;
    View lineanclist;
    LinearLayout secNBID;
    View lineNBID;
    TextView VlblNBID;
    EditText txtNBID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secPGN;
    View linePGN;
    TextView VlblPGN;
    EditText txtPGN;
    LinearLayout secAnc;
    View lineAnc;
    TextView VlblAnc;
    RadioGroup rdogrpAnc;
    RadioButton rdoAnc1;
    RadioButton rdoAnc2;
    RadioButton rdoAnc3;
    RadioButton rdoAnc4;
    LinearLayout secAncNo;
    View lineAncNo;
    TextView VlblAncNo;
    EditText txtAncNo;
    LinearLayout secAncNoDk;
    View lineAncNoDk;
    TextView VlblAncNoDk;
    RadioGroup rdogrpAncNoDk;
    RadioButton rdoAncNoDk1;
    RadioButton rdoAncNoDk2;
    LinearLayout secAncCard;
    View lineAncCard;
    TextView VlblAncCard;
    RadioGroup rdogrpAncCard;
    RadioButton rdoAncCard1;
    RadioButton rdoAncCard2;
    RadioButton rdoAncCard3;
    RadioButton rdoAncCard4;
    LinearLayout seclbl04;
    View linelbl04;
    LinearLayout secAncLackMoney;
    View lineAncLackMoney;
    TextView VlblAncLackMoney;
    CheckBox chkAncLackMoney;
    LinearLayout secAncLackTransport;
    View lineAncLackTransport;
    TextView VlblAncLackTransport;
    CheckBox chkAncLackTransport;
    LinearLayout secAncDistance;
    View lineAncDistance;
    TextView VlblAncDistance;
    CheckBox chkAncDistance;
    LinearLayout secAncAttitude;
    View lineAncAttitude;
    TextView VlblAncAttitude;
    CheckBox chkAncAttitude;
    LinearLayout secAncPreference;
    View lineAncPreference;
    TextView VlblAncPreference;
    CheckBox chkAncPreference;
    LinearLayout secAncRsnOth;
    View lineAncRsnOth;
    TextView VlblAncRsnOth;
    CheckBox chkAncRsnOth;
    LinearLayout secAncRsnOthSp;
    View lineAncRsnOthSp;
    TextView VlblAncRsnOthSp;
    EditText txtAncRsnOthSp;
    LinearLayout secAncRsnDk;
    View lineAncRsnDk;
    TextView VlblAncRsnDk;
    CheckBox chkAncRsnDk;
    LinearLayout secAncRsnRefu;
    View lineAncRsnRefu;
    TextView VlblAncRsnRefu;
    CheckBox chkAncRsnRefu;
    LinearLayout secANCPres;
    View lineANCPres;
    TextView VlblANCPres;
    RadioGroup rdogrpANCPres;
    RadioButton rdoANCPres1;
    RadioButton rdoANCPres2;
    RadioButton rdoANCPres3;
    RadioButton rdoANCPres4;
    LinearLayout seclbl03;
    View linelbl03;
    LinearLayout secAncWeight;
    View lineAncWeight;
    TextView VlblAncWeight;
    CheckBox chkAncWeight;
    LinearLayout secAncBp;
    View lineAncBp;
    TextView VlblAncBp;
    CheckBox chkAncBp;
    LinearLayout secAncUrine;
    View lineAncUrine;
    TextView VlblAncUrine;
    CheckBox chkAncUrine;
    LinearLayout secAncBlood;
    View lineAncBlood;
    TextView VlblAncBlood;
    CheckBox chkAncBlood;
    LinearLayout secAncHeartbeat;
    View lineAncHeartbeat;
    TextView VlblAncHeartbeat;
    CheckBox chkAncHeartbeat;
    LinearLayout secAncUSG;
    View lineAncUSG;
    TextView VlblAncUSG;
    CheckBox chkAncUSG;
    LinearLayout secAncFood;
    View lineAncFood;
    TextView VlblAncFood;
    CheckBox chkAncFood;
    LinearLayout secANCBF;
    View lineANCBF;
    TextView VlblANCBF;
    CheckBox chkANCBF;
    LinearLayout secANCVBleed;
    View lineANCVBleed;
    TextView VlblANCVBleed;
    CheckBox chkANCVBleed;
    LinearLayout secANCDSign;
    View lineANCDSign;
    TextView VlblANCDSign;
    CheckBox chkANCDSign;
    LinearLayout secANCFPMtd;
    View lineANCFPMtd;
    TextView VlblANCFPMtd;
    CheckBox chkANCFPMtd;
    LinearLayout secANCHCOth;
    View lineANCHCOth;
    TextView VlblANCHCOth;
    CheckBox chkANCHCOth;
    LinearLayout secANCHCOthSp;
    View lineANCHCOthSp;
    TextView VlblANCHCOthSp;
    AutoCompleteTextView txtANCHCOthSp;
    LinearLayout secAncHCDk;
    View lineAncHCDk;
    TextView VlblAncHCDk;
    CheckBox chkAncHCDk;
    LinearLayout secANCRefu;
    View lineANCRefu;
    TextView VlblANCRefu;
    CheckBox chkANCRefu;

     int MODULEID = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String NBID = "";
     String MEMID = "";
     String HHID = "";
     String ROUND = "";
     String PGN = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.nbc_anc);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");


            IDbundle = getIntent().getExtras();
            NBID = IDbundle.getString("NBID");
            PGN = IDbundle.getString("PGN");
            MEMID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            ROUND = IDbundle.getString("round");

            TableName = "NBC_ANC";
            MODULEID = 42;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            btnAdd = (Button) findViewById(R.id.btnAdd);
            btnAdd.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    String TotalAncDetails = C.ReturnSingleValue("Select count(*)total from NBC_ANCDetail where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='" + PGN + "'");
                    if (!Global.isNullOrEmpty(TotalAncDetails)) {
                        if (Integer.parseInt(txtAncNo.getText().toString().length() == 0 ? "0" : txtAncNo.getText().toString()) <= Integer.parseInt(TotalAncDetails)) {
                            Connection.MessageBox(NBC_ANC.this, "You can't enter more record where as total number of anc is " + txtAncNo.getText().toString() + ".");
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

                }
            });

            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mAdapter = new DataAdapter(dataList);
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String TotalAncDetails = C.ReturnSingleValue("Select count(*)total from NBC_ANCDetail where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='" + PGN + "'");
                    if (!Global.isNullOrEmpty(TotalAncDetails)) {
                        if (Integer.parseInt(txtAncNo.getText().toString().length() == 0 ? "0" : txtAncNo.getText().toString()) != Integer.parseInt(TotalAncDetails)) {
                            Connection.MessageBox(NBC_ANC.this, "Your total anc visit number and total anc visit record are not same.");
                            return;
                        }
                    }
                    AlertDialog.Builder adb = new AlertDialog.Builder(NBC_ANC.this);
                    adb.setTitle("Close");
                    adb.setIcon(R.drawable.favicon);
                    adb.setMessage("Do you want to close this form[Yes/No]?");
                    adb.setNegativeButton("No", null);
                    adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    adb.show();
                }
            });

            Initialization();
            Connection.LocalizeLanguage(NBC_ANC.this, MODULEID, LANGUAGEID);


            //Hide all skip variables
            secAncNo.setVisibility(View.GONE);
            lineAncNo.setVisibility(View.GONE);
            secAncNoDk.setVisibility(View.GONE);
            lineAncNoDk.setVisibility(View.GONE);
            secAncCard.setVisibility(View.GONE);
            lineAncCard.setVisibility(View.GONE);
            seclbl04.setVisibility(View.GONE);
            linelbl04.setVisibility(View.GONE);
            secAncLackMoney.setVisibility(View.GONE);
            lineAncLackMoney.setVisibility(View.GONE);
            secAncLackTransport.setVisibility(View.GONE);
            lineAncLackTransport.setVisibility(View.GONE);
            secAncDistance.setVisibility(View.GONE);
            lineAncDistance.setVisibility(View.GONE);
            secAncAttitude.setVisibility(View.GONE);
            lineAncAttitude.setVisibility(View.GONE);
            secAncPreference.setVisibility(View.GONE);
            lineAncPreference.setVisibility(View.GONE);
            secAncRsnOth.setVisibility(View.GONE);
            lineAncRsnOth.setVisibility(View.GONE);
            secAncRsnOthSp.setVisibility(View.GONE);
            lineAncRsnOthSp.setVisibility(View.GONE);
            secAncRsnDk.setVisibility(View.GONE);
            lineAncRsnDk.setVisibility(View.GONE);
            secAncRsnRefu.setVisibility(View.GONE);
            lineAncRsnRefu.setVisibility(View.GONE);
            secANCPres.setVisibility(View.GONE);
            lineANCPres.setVisibility(View.GONE);
            seclbl03.setVisibility(View.GONE);
            linelbl03.setVisibility(View.GONE);
            secAncWeight.setVisibility(View.GONE);
            lineAncWeight.setVisibility(View.GONE);
            secAncBp.setVisibility(View.GONE);
            lineAncBp.setVisibility(View.GONE);
            secAncUrine.setVisibility(View.GONE);
            lineAncUrine.setVisibility(View.GONE);
            secAncBlood.setVisibility(View.GONE);
            lineAncBlood.setVisibility(View.GONE);
            secAncHeartbeat.setVisibility(View.GONE);
            lineAncHeartbeat.setVisibility(View.GONE);
            secAncUSG.setVisibility(View.GONE);
            lineAncUSG.setVisibility(View.GONE);
            secAncFood.setVisibility(View.GONE);
            lineAncFood.setVisibility(View.GONE);
            secANCBF.setVisibility(View.GONE);
            lineANCBF.setVisibility(View.GONE);
            secANCVBleed.setVisibility(View.GONE);
            lineANCVBleed.setVisibility(View.GONE);
            secANCDSign.setVisibility(View.GONE);
            lineANCDSign.setVisibility(View.GONE);
            secANCFPMtd.setVisibility(View.GONE);
            lineANCFPMtd.setVisibility(View.GONE);
            secANCHCOth.setVisibility(View.GONE);
            lineANCHCOth.setVisibility(View.GONE);
            secANCHCOthSp.setVisibility(View.GONE);
            lineANCHCOthSp.setVisibility(View.GONE);
            secAncHCDk.setVisibility(View.GONE);
            lineAncHCDk.setVisibility(View.GONE);
            secANCRefu.setVisibility(View.GONE);
            lineANCRefu.setVisibility(View.GONE);
            secAncNo.setVisibility(View.GONE);
            lineAncNo.setVisibility(View.GONE);
            secAncNoDk.setVisibility(View.GONE);
            lineAncNoDk.setVisibility(View.GONE);
            secAncCard.setVisibility(View.GONE);
            lineAncCard.setVisibility(View.GONE);
            secANCPres.setVisibility(View.GONE);
            lineANCPres.setVisibility(View.GONE);
            seclbl03.setVisibility(View.GONE);
            linelbl03.setVisibility(View.GONE);
            secAncWeight.setVisibility(View.GONE);
            lineAncWeight.setVisibility(View.GONE);
            secAncBp.setVisibility(View.GONE);
            lineAncBp.setVisibility(View.GONE);
            secAncUrine.setVisibility(View.GONE);
            lineAncUrine.setVisibility(View.GONE);
            secAncBlood.setVisibility(View.GONE);
            lineAncBlood.setVisibility(View.GONE);
            secAncHeartbeat.setVisibility(View.GONE);
            lineAncHeartbeat.setVisibility(View.GONE);
            secAncUSG.setVisibility(View.GONE);
            lineAncUSG.setVisibility(View.GONE);
            secAncFood.setVisibility(View.GONE);
            lineAncFood.setVisibility(View.GONE);
            secANCBF.setVisibility(View.GONE);
            lineANCBF.setVisibility(View.GONE);
            secANCVBleed.setVisibility(View.GONE);
            lineANCVBleed.setVisibility(View.GONE);
            secANCDSign.setVisibility(View.GONE);
            lineANCDSign.setVisibility(View.GONE);
            secANCFPMtd.setVisibility(View.GONE);
            lineANCFPMtd.setVisibility(View.GONE);
            secANCHCOth.setVisibility(View.GONE);
            lineANCHCOth.setVisibility(View.GONE);
            secANCHCOthSp.setVisibility(View.GONE);
            lineANCHCOthSp.setVisibility(View.GONE);
            secAncHCDk.setVisibility(View.GONE);
            lineAncHCDk.setVisibility(View.GONE);
            secANCRefu.setVisibility(View.GONE);
            lineANCRefu.setVisibility(View.GONE);
            secAncNo.setVisibility(View.GONE);
            lineAncNo.setVisibility(View.GONE);
            secAncNoDk.setVisibility(View.GONE);
            lineAncNoDk.setVisibility(View.GONE);
            secAncCard.setVisibility(View.GONE);
            lineAncCard.setVisibility(View.GONE);
            secANCPres.setVisibility(View.GONE);
            lineANCPres.setVisibility(View.GONE);
            seclbl03.setVisibility(View.GONE);
            linelbl03.setVisibility(View.GONE);
            secAncWeight.setVisibility(View.GONE);
            lineAncWeight.setVisibility(View.GONE);
            secAncBp.setVisibility(View.GONE);
            lineAncBp.setVisibility(View.GONE);
            secAncUrine.setVisibility(View.GONE);
            lineAncUrine.setVisibility(View.GONE);
            secAncBlood.setVisibility(View.GONE);
            lineAncBlood.setVisibility(View.GONE);
            secAncHeartbeat.setVisibility(View.GONE);
            lineAncHeartbeat.setVisibility(View.GONE);
            secAncUSG.setVisibility(View.GONE);
            lineAncUSG.setVisibility(View.GONE);
            secAncFood.setVisibility(View.GONE);
            lineAncFood.setVisibility(View.GONE);
            secANCBF.setVisibility(View.GONE);
            lineANCBF.setVisibility(View.GONE);
            secANCVBleed.setVisibility(View.GONE);
            lineANCVBleed.setVisibility(View.GONE);
            secANCDSign.setVisibility(View.GONE);
            lineANCDSign.setVisibility(View.GONE);
            secANCFPMtd.setVisibility(View.GONE);
            lineANCFPMtd.setVisibility(View.GONE);
            secANCHCOth.setVisibility(View.GONE);
            lineANCHCOth.setVisibility(View.GONE);
            secANCHCOthSp.setVisibility(View.GONE);
            lineANCHCOthSp.setVisibility(View.GONE);
            secAncHCDk.setVisibility(View.GONE);
            lineAncHCDk.setVisibility(View.GONE);
            secANCRefu.setVisibility(View.GONE);
            lineANCRefu.setVisibility(View.GONE);
            secANCHCOthSp.setVisibility(View.GONE);
            lineANCHCOthSp.setVisibility(View.GONE);
            secAncList.setVisibility(View.GONE);
            lineanclist.setVisibility(View.GONE);


            DataSearch(MEMID, HHID, PGN);


            DataSearchANCList(MEMID, HHID, PGN);

            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(NBC_ANC.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            secNBID = (LinearLayout) findViewById(R.id.secNBID);
            lineNBID = (View) findViewById(R.id.lineNBID);
            VlblNBID = (TextView) findViewById(R.id.VlblNBID);
            txtNBID = (EditText) findViewById(R.id.txtNBID);
            if (NBID.length() == 0) txtNBID.setText(Global.Get_UUID(DEVICEID));
            else txtNBID.setText(NBID);
            txtNBID.setEnabled(false);
            secMemID = (LinearLayout) findViewById(R.id.secMemID);
            lineMemID = (View) findViewById(R.id.lineMemID);
            VlblMemID = (TextView) findViewById(R.id.VlblMemID);
            txtMemID = (EditText) findViewById(R.id.txtMemID);
            txtMemID.setText(MEMID);
            txtMemID.setEnabled(false);
            secHHID = (LinearLayout) findViewById(R.id.secHHID);
            lineHHID = (View) findViewById(R.id.lineHHID);
            VlblHHID = (TextView) findViewById(R.id.VlblHHID);
            txtHHID = (EditText) findViewById(R.id.txtHHID);
            txtHHID.setText(HHID);
            txtHHID.setEnabled(false);
            secPGN = (LinearLayout) findViewById(R.id.secPGN);
            linePGN = (View) findViewById(R.id.linePGN);
            VlblPGN = (TextView) findViewById(R.id.VlblPGN);
            txtPGN = (EditText) findViewById(R.id.txtPGN);
            txtPGN.setText(PGN);
            txtPGN.setEnabled(false);
            secAnc = (LinearLayout) findViewById(R.id.secAnc);
            lineAnc = (View) findViewById(R.id.lineAnc);
            VlblAnc = (TextView) findViewById(R.id.VlblAnc);
            rdogrpAnc = (RadioGroup) findViewById(R.id.rdogrpAnc);
            rdoAnc1 = (RadioButton) findViewById(R.id.rdoAnc1);
            rdoAnc2 = (RadioButton) findViewById(R.id.rdoAnc2);
            rdoAnc3 = (RadioButton) findViewById(R.id.rdoAnc3);
            rdoAnc4 = (RadioButton) findViewById(R.id.rdoAnc4);
            rdogrpAnc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpAnc = new String[]{"0", "1", "8", "9"};
                    for (int i = 0; i < rdogrpAnc.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpAnc.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpAnc[i];
                    }

                    if (rbData.equalsIgnoreCase("0")) {
                        secAncNo.setVisibility(View.GONE);
                        lineAncNo.setVisibility(View.GONE);
                        txtAncNo.setText("");
                        secAncNoDk.setVisibility(View.GONE);
                        lineAncNoDk.setVisibility(View.GONE);
                        rdogrpAncNoDk.clearCheck();
                        secAncCard.setVisibility(View.GONE);
                        lineAncCard.setVisibility(View.GONE);
                        rdogrpAncCard.clearCheck();
                        seclbl04.setVisibility(View.GONE);
                        linelbl04.setVisibility(View.GONE);
                        secAncLackMoney.setVisibility(View.GONE);
                        lineAncLackMoney.setVisibility(View.GONE);
                        chkAncLackMoney.setChecked(false);
                        secAncLackTransport.setVisibility(View.GONE);
                        lineAncLackTransport.setVisibility(View.GONE);
                        chkAncLackTransport.setChecked(false);
                        secAncDistance.setVisibility(View.GONE);
                        lineAncDistance.setVisibility(View.GONE);
                        chkAncDistance.setChecked(false);
                        secAncAttitude.setVisibility(View.GONE);
                        lineAncAttitude.setVisibility(View.GONE);
                        chkAncAttitude.setChecked(false);
                        secAncPreference.setVisibility(View.GONE);
                        lineAncPreference.setVisibility(View.GONE);
                        chkAncPreference.setChecked(false);
                        secAncRsnOth.setVisibility(View.GONE);
                        lineAncRsnOth.setVisibility(View.GONE);
                        chkAncRsnOth.setChecked(false);
                        secAncRsnOthSp.setVisibility(View.GONE);
                        lineAncRsnOthSp.setVisibility(View.GONE);
                        txtAncRsnOthSp.setText("");
                        secAncRsnDk.setVisibility(View.GONE);
                        lineAncRsnDk.setVisibility(View.GONE);
                        chkAncRsnDk.setChecked(false);
                        secAncRsnRefu.setVisibility(View.GONE);
                        lineAncRsnRefu.setVisibility(View.GONE);
                        chkAncRsnRefu.setChecked(false);
                        secANCPres.setVisibility(View.GONE);
                        lineANCPres.setVisibility(View.GONE);
                        rdogrpANCPres.clearCheck();
                        seclbl03.setVisibility(View.GONE);
                        linelbl03.setVisibility(View.GONE);
                        secAncWeight.setVisibility(View.GONE);
                        lineAncWeight.setVisibility(View.GONE);
                        chkAncWeight.setChecked(false);
                        secAncBp.setVisibility(View.GONE);
                        lineAncBp.setVisibility(View.GONE);
                        chkAncBp.setChecked(false);
                        secAncUrine.setVisibility(View.GONE);
                        lineAncUrine.setVisibility(View.GONE);
                        chkAncUrine.setChecked(false);
                        secAncBlood.setVisibility(View.GONE);
                        lineAncBlood.setVisibility(View.GONE);
                        chkAncBlood.setChecked(false);
                        secAncHeartbeat.setVisibility(View.GONE);
                        lineAncHeartbeat.setVisibility(View.GONE);
                        chkAncHeartbeat.setChecked(false);
                        secAncUSG.setVisibility(View.GONE);
                        lineAncUSG.setVisibility(View.GONE);
                        chkAncUSG.setChecked(false);
                        secAncFood.setVisibility(View.GONE);
                        lineAncFood.setVisibility(View.GONE);
                        chkAncFood.setChecked(false);
                        secANCBF.setVisibility(View.GONE);
                        lineANCBF.setVisibility(View.GONE);
                        chkANCBF.setChecked(false);
                        secANCVBleed.setVisibility(View.GONE);
                        lineANCVBleed.setVisibility(View.GONE);
                        chkANCVBleed.setChecked(false);
                        secANCDSign.setVisibility(View.GONE);
                        lineANCDSign.setVisibility(View.GONE);
                        chkANCDSign.setChecked(false);
                        secANCFPMtd.setVisibility(View.GONE);
                        lineANCFPMtd.setVisibility(View.GONE);
                        chkANCFPMtd.setChecked(false);
                        secANCHCOth.setVisibility(View.GONE);
                        lineANCHCOth.setVisibility(View.GONE);
                        chkANCHCOth.setChecked(false);
                        secANCHCOthSp.setVisibility(View.GONE);
                        lineANCHCOthSp.setVisibility(View.GONE);
                        txtANCHCOthSp.setText("");
                        secAncHCDk.setVisibility(View.GONE);
                        lineAncHCDk.setVisibility(View.GONE);
                        chkAncHCDk.setChecked(false);
                        secANCRefu.setVisibility(View.GONE);
                        lineANCRefu.setVisibility(View.GONE);
                        chkANCRefu.setChecked(false);
                    } else if (rbData.equalsIgnoreCase("8")) {
                        secAncNo.setVisibility(View.GONE);
                        lineAncNo.setVisibility(View.GONE);
                        txtAncNo.setText("");
                        secAncNoDk.setVisibility(View.GONE);
                        lineAncNoDk.setVisibility(View.GONE);
                        rdogrpAncNoDk.clearCheck();
                        secAncCard.setVisibility(View.GONE);
                        lineAncCard.setVisibility(View.GONE);
                        rdogrpAncCard.clearCheck();
                        seclbl04.setVisibility(View.GONE);
                        linelbl04.setVisibility(View.GONE);
                        secAncLackMoney.setVisibility(View.GONE);
                        lineAncLackMoney.setVisibility(View.GONE);
                        chkAncLackMoney.setChecked(false);
                        secAncLackTransport.setVisibility(View.GONE);
                        lineAncLackTransport.setVisibility(View.GONE);
                        chkAncLackTransport.setChecked(false);
                        secAncDistance.setVisibility(View.GONE);
                        lineAncDistance.setVisibility(View.GONE);
                        chkAncDistance.setChecked(false);
                        secAncAttitude.setVisibility(View.GONE);
                        lineAncAttitude.setVisibility(View.GONE);
                        chkAncAttitude.setChecked(false);
                        secAncPreference.setVisibility(View.GONE);
                        lineAncPreference.setVisibility(View.GONE);
                        chkAncPreference.setChecked(false);
                        secAncRsnOth.setVisibility(View.GONE);
                        lineAncRsnOth.setVisibility(View.GONE);
                        chkAncRsnOth.setChecked(false);
                        secAncRsnOthSp.setVisibility(View.GONE);
                        lineAncRsnOthSp.setVisibility(View.GONE);
                        txtAncRsnOthSp.setText("");
                        secAncRsnDk.setVisibility(View.GONE);
                        lineAncRsnDk.setVisibility(View.GONE);
                        chkAncRsnDk.setChecked(false);
                        secAncRsnRefu.setVisibility(View.GONE);
                        lineAncRsnRefu.setVisibility(View.GONE);
                        chkAncRsnRefu.setChecked(false);
                        secANCPres.setVisibility(View.GONE);
                        lineANCPres.setVisibility(View.GONE);
                        rdogrpANCPres.clearCheck();
                        seclbl03.setVisibility(View.GONE);
                        linelbl03.setVisibility(View.GONE);
                        secAncWeight.setVisibility(View.GONE);
                        lineAncWeight.setVisibility(View.GONE);
                        chkAncWeight.setChecked(false);
                        secAncBp.setVisibility(View.GONE);
                        lineAncBp.setVisibility(View.GONE);
                        chkAncBp.setChecked(false);
                        secAncUrine.setVisibility(View.GONE);
                        lineAncUrine.setVisibility(View.GONE);
                        chkAncUrine.setChecked(false);
                        secAncBlood.setVisibility(View.GONE);
                        lineAncBlood.setVisibility(View.GONE);
                        chkAncBlood.setChecked(false);
                        secAncHeartbeat.setVisibility(View.GONE);
                        lineAncHeartbeat.setVisibility(View.GONE);
                        chkAncHeartbeat.setChecked(false);
                        secAncUSG.setVisibility(View.GONE);
                        lineAncUSG.setVisibility(View.GONE);
                        chkAncUSG.setChecked(false);
                        secAncFood.setVisibility(View.GONE);
                        lineAncFood.setVisibility(View.GONE);
                        chkAncFood.setChecked(false);
                        secANCBF.setVisibility(View.GONE);
                        lineANCBF.setVisibility(View.GONE);
                        chkANCBF.setChecked(false);
                        secANCVBleed.setVisibility(View.GONE);
                        lineANCVBleed.setVisibility(View.GONE);
                        chkANCVBleed.setChecked(false);
                        secANCDSign.setVisibility(View.GONE);
                        lineANCDSign.setVisibility(View.GONE);
                        chkANCDSign.setChecked(false);
                        secANCFPMtd.setVisibility(View.GONE);
                        lineANCFPMtd.setVisibility(View.GONE);
                        chkANCFPMtd.setChecked(false);
                        secANCHCOth.setVisibility(View.GONE);
                        lineANCHCOth.setVisibility(View.GONE);
                        chkANCHCOth.setChecked(false);
                        secANCHCOthSp.setVisibility(View.GONE);
                        lineANCHCOthSp.setVisibility(View.GONE);
                        txtANCHCOthSp.setText("");
                        secAncHCDk.setVisibility(View.GONE);
                        lineAncHCDk.setVisibility(View.GONE);
                        chkAncHCDk.setChecked(false);
                        secANCRefu.setVisibility(View.GONE);
                        lineANCRefu.setVisibility(View.GONE);
                        chkANCRefu.setChecked(false);
                    } else if (rbData.equalsIgnoreCase("9")) {
                        secAncNo.setVisibility(View.GONE);
                        lineAncNo.setVisibility(View.GONE);
                        txtAncNo.setText("");
                        secAncNoDk.setVisibility(View.GONE);
                        lineAncNoDk.setVisibility(View.GONE);
                        rdogrpAncNoDk.clearCheck();
                        secAncCard.setVisibility(View.GONE);
                        lineAncCard.setVisibility(View.GONE);
                        rdogrpAncCard.clearCheck();
                        seclbl04.setVisibility(View.GONE);
                        linelbl04.setVisibility(View.GONE);
                        secAncLackMoney.setVisibility(View.GONE);
                        lineAncLackMoney.setVisibility(View.GONE);
                        chkAncLackMoney.setChecked(false);
                        secAncLackTransport.setVisibility(View.GONE);
                        lineAncLackTransport.setVisibility(View.GONE);
                        chkAncLackTransport.setChecked(false);
                        secAncDistance.setVisibility(View.GONE);
                        lineAncDistance.setVisibility(View.GONE);
                        chkAncDistance.setChecked(false);
                        secAncAttitude.setVisibility(View.GONE);
                        lineAncAttitude.setVisibility(View.GONE);
                        chkAncAttitude.setChecked(false);
                        secAncPreference.setVisibility(View.GONE);
                        lineAncPreference.setVisibility(View.GONE);
                        chkAncPreference.setChecked(false);
                        secAncRsnOth.setVisibility(View.GONE);
                        lineAncRsnOth.setVisibility(View.GONE);
                        chkAncRsnOth.setChecked(false);
                        secAncRsnOthSp.setVisibility(View.GONE);
                        lineAncRsnOthSp.setVisibility(View.GONE);
                        txtAncRsnOthSp.setText("");
                        secAncRsnDk.setVisibility(View.GONE);
                        lineAncRsnDk.setVisibility(View.GONE);
                        chkAncRsnDk.setChecked(false);
                        secAncRsnRefu.setVisibility(View.GONE);
                        lineAncRsnRefu.setVisibility(View.GONE);
                        chkAncRsnRefu.setChecked(false);
                        secANCPres.setVisibility(View.GONE);
                        lineANCPres.setVisibility(View.GONE);
                        rdogrpANCPres.clearCheck();
                        seclbl03.setVisibility(View.GONE);
                        linelbl03.setVisibility(View.GONE);
                        secAncWeight.setVisibility(View.GONE);
                        lineAncWeight.setVisibility(View.GONE);
                        chkAncWeight.setChecked(false);
                        secAncBp.setVisibility(View.GONE);
                        lineAncBp.setVisibility(View.GONE);
                        chkAncBp.setChecked(false);
                        secAncUrine.setVisibility(View.GONE);
                        lineAncUrine.setVisibility(View.GONE);
                        chkAncUrine.setChecked(false);
                        secAncBlood.setVisibility(View.GONE);
                        lineAncBlood.setVisibility(View.GONE);
                        chkAncBlood.setChecked(false);
                        secAncHeartbeat.setVisibility(View.GONE);
                        lineAncHeartbeat.setVisibility(View.GONE);
                        chkAncHeartbeat.setChecked(false);
                        secAncUSG.setVisibility(View.GONE);
                        lineAncUSG.setVisibility(View.GONE);
                        chkAncUSG.setChecked(false);
                        secAncFood.setVisibility(View.GONE);
                        lineAncFood.setVisibility(View.GONE);
                        chkAncFood.setChecked(false);
                        secANCBF.setVisibility(View.GONE);
                        lineANCBF.setVisibility(View.GONE);
                        chkANCBF.setChecked(false);
                        secANCVBleed.setVisibility(View.GONE);
                        lineANCVBleed.setVisibility(View.GONE);
                        chkANCVBleed.setChecked(false);
                        secANCDSign.setVisibility(View.GONE);
                        lineANCDSign.setVisibility(View.GONE);
                        chkANCDSign.setChecked(false);
                        secANCFPMtd.setVisibility(View.GONE);
                        lineANCFPMtd.setVisibility(View.GONE);
                        chkANCFPMtd.setChecked(false);
                        secANCHCOth.setVisibility(View.GONE);
                        lineANCHCOth.setVisibility(View.GONE);
                        chkANCHCOth.setChecked(false);
                        secANCHCOthSp.setVisibility(View.GONE);
                        lineANCHCOthSp.setVisibility(View.GONE);
                        txtANCHCOthSp.setText("");
                        secAncHCDk.setVisibility(View.GONE);
                        lineAncHCDk.setVisibility(View.GONE);
                        chkAncHCDk.setChecked(false);
                        secANCRefu.setVisibility(View.GONE);
                        lineANCRefu.setVisibility(View.GONE);
                        chkANCRefu.setChecked(false);
                    } else {
                        secAncNo.setVisibility(View.VISIBLE);
                        lineAncNo.setVisibility(View.VISIBLE);
                        secAncNoDk.setVisibility(View.VISIBLE);
                        lineAncNoDk.setVisibility(View.VISIBLE);
                        secAncCard.setVisibility(View.VISIBLE);
                        lineAncCard.setVisibility(View.VISIBLE);
                        if(ProjectSetting.SITE_CODE.equalsIgnoreCase(ProjectSetting.SIERRA_LEONE_SITE1)){
                            seclbl04.setVisibility(View.VISIBLE);
                            linelbl04.setVisibility(View.VISIBLE);
                            secAncLackMoney.setVisibility(View.VISIBLE);
                            lineAncLackMoney.setVisibility(View.VISIBLE);
                            secAncLackTransport.setVisibility(View.VISIBLE);
                            lineAncLackTransport.setVisibility(View.VISIBLE);
                            secAncDistance.setVisibility(View.VISIBLE);
                            lineAncDistance.setVisibility(View.VISIBLE);
                            secAncAttitude.setVisibility(View.VISIBLE);
                            lineAncAttitude.setVisibility(View.VISIBLE);
                            secAncPreference.setVisibility(View.VISIBLE);
                            lineAncPreference.setVisibility(View.VISIBLE);
                            secAncRsnOth.setVisibility(View.VISIBLE);
                            lineAncRsnOth.setVisibility(View.VISIBLE);
                            secAncRsnDk.setVisibility(View.VISIBLE);
                            lineAncRsnDk.setVisibility(View.VISIBLE);
                            secAncRsnRefu.setVisibility(View.VISIBLE);
                            lineAncRsnRefu.setVisibility(View.VISIBLE);
                        }

                        secANCPres.setVisibility(View.VISIBLE);
                        lineANCPres.setVisibility(View.VISIBLE);
                        seclbl03.setVisibility(View.VISIBLE);
                        linelbl03.setVisibility(View.VISIBLE);
                        secAncWeight.setVisibility(View.VISIBLE);
                        lineAncWeight.setVisibility(View.VISIBLE);
                        secAncBp.setVisibility(View.VISIBLE);
                        lineAncBp.setVisibility(View.VISIBLE);
                        secAncUrine.setVisibility(View.VISIBLE);
                        lineAncUrine.setVisibility(View.VISIBLE);
                        secAncBlood.setVisibility(View.VISIBLE);
                        lineAncBlood.setVisibility(View.VISIBLE);
                        secAncHeartbeat.setVisibility(View.VISIBLE);
                        lineAncHeartbeat.setVisibility(View.VISIBLE);
                        secAncUSG.setVisibility(View.VISIBLE);
                        lineAncUSG.setVisibility(View.VISIBLE);
                        secAncFood.setVisibility(View.VISIBLE);
                        lineAncFood.setVisibility(View.VISIBLE);
                        secANCBF.setVisibility(View.VISIBLE);
                        lineANCBF.setVisibility(View.VISIBLE);
                        secANCVBleed.setVisibility(View.VISIBLE);
                        lineANCVBleed.setVisibility(View.VISIBLE);
                        secANCDSign.setVisibility(View.VISIBLE);
                        lineANCDSign.setVisibility(View.VISIBLE);
                        secANCFPMtd.setVisibility(View.VISIBLE);
                        lineANCFPMtd.setVisibility(View.VISIBLE);
                        secANCHCOth.setVisibility(View.VISIBLE);
                        lineANCHCOth.setVisibility(View.VISIBLE);
                        secAncHCDk.setVisibility(View.VISIBLE);
                        lineAncHCDk.setVisibility(View.VISIBLE);
                        secANCRefu.setVisibility(View.VISIBLE);
                        lineANCRefu.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secAncList = (LinearLayout) findViewById(R.id.secAncList);
            lineanclist = (View) findViewById(R.id.lineanclist);
            secAncNo = (LinearLayout) findViewById(R.id.secAncNo);
            lineAncNo = (View) findViewById(R.id.lineAncNo);
            VlblAncNo = (TextView) findViewById(R.id.VlblAncNo);
            txtAncNo = (EditText) findViewById(R.id.txtAncNo);
            secAncNoDk = (LinearLayout) findViewById(R.id.secAncNoDk);
            lineAncNoDk = (View) findViewById(R.id.lineAncNoDk);
            VlblAncNoDk = (TextView) findViewById(R.id.VlblAncNoDk);
            rdogrpAncNoDk = (RadioGroup) findViewById(R.id.rdogrpAncNoDk);
            rdoAncNoDk1 = (RadioButton) findViewById(R.id.rdoAncNoDk1);
            rdoAncNoDk2 = (RadioButton) findViewById(R.id.rdoAncNoDk2);

            txtAncNo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (txtAncNo.getText().toString().length() > 0 && !txtAncNo.getText().toString().equals("0")) {
                        if (rdoAncNoDk1.isChecked() || rdoAncNoDk2.isChecked()) {
                            rdogrpAncNoDk.clearCheck();
                        }
                        secAncList.setVisibility(View.VISIBLE);
                        lineanclist.setVisibility(View.VISIBLE);
                    } else {
                        secAncList.setVisibility(View.GONE);
                        lineanclist.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            rdogrpAncNoDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpAncNoDk = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpAncNoDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpAncNoDk.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpAncNoDk[i];

                        if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2")) {
                            if (txtAncNo.getText().toString().length() > 0) {
                                txtAncNo.setText("");
                            }
                            secAncList.setVisibility(View.GONE);
                            lineanclist.setVisibility(View.GONE);
                        }

                    }

                }
            });

            secAncCard = (LinearLayout) findViewById(R.id.secAncCard);
            lineAncCard = (View) findViewById(R.id.lineAncCard);
            VlblAncCard = (TextView) findViewById(R.id.VlblAncCard);
            rdogrpAncCard = (RadioGroup) findViewById(R.id.rdogrpAncCard);
            rdoAncCard1 = (RadioButton) findViewById(R.id.rdoAncCard1);
            rdoAncCard2 = (RadioButton) findViewById(R.id.rdoAncCard2);
            rdoAncCard3 = (RadioButton) findViewById(R.id.rdoAncCard3);
            rdoAncCard4 = (RadioButton) findViewById(R.id.rdoAncCard4);
            seclbl04=(LinearLayout)findViewById(R.id.seclbl04);
            linelbl04=(View)findViewById(R.id.linelbl04);
            secAncLackMoney=(LinearLayout)findViewById(R.id.secAncLackMoney);
            lineAncLackMoney=(View)findViewById(R.id.lineAncLackMoney);
            VlblAncLackMoney=(TextView) findViewById(R.id.VlblAncLackMoney);
            chkAncLackMoney=(CheckBox) findViewById(R.id.chkAncLackMoney);
            chkAncLackMoney.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if ( isChecked )
                    {
                        if (chkAncRsnDk.isChecked()) chkAncRsnDk.setChecked(false);
                        if (chkAncRsnRefu.isChecked()) chkAncRsnRefu.setChecked(false);
                    }
                }
            });
            secAncLackTransport=(LinearLayout)findViewById(R.id.secAncLackTransport);
            lineAncLackTransport=(View)findViewById(R.id.lineAncLackTransport);
            VlblAncLackTransport=(TextView) findViewById(R.id.VlblAncLackTransport);
            chkAncLackTransport=(CheckBox) findViewById(R.id.chkAncLackTransport);
            chkAncLackTransport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if ( isChecked )
                    {
                        if (chkAncRsnDk.isChecked()) chkAncRsnDk.setChecked(false);
                        if (chkAncRsnRefu.isChecked()) chkAncRsnRefu.setChecked(false);
                    }
                }
            });
            secAncDistance=(LinearLayout)findViewById(R.id.secAncDistance);
            lineAncDistance=(View)findViewById(R.id.lineAncDistance);
            VlblAncDistance=(TextView) findViewById(R.id.VlblAncDistance);
            chkAncDistance=(CheckBox) findViewById(R.id.chkAncDistance);
            chkAncDistance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if ( isChecked )
                    {
                        if (chkAncRsnDk.isChecked()) chkAncRsnDk.setChecked(false);
                        if (chkAncRsnRefu.isChecked()) chkAncRsnRefu.setChecked(false);
                    }
                }
            });
            secAncAttitude=(LinearLayout)findViewById(R.id.secAncAttitude);
            lineAncAttitude=(View)findViewById(R.id.lineAncAttitude);
            VlblAncAttitude=(TextView) findViewById(R.id.VlblAncAttitude);
            chkAncAttitude=(CheckBox) findViewById(R.id.chkAncAttitude);
            chkAncAttitude.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if ( isChecked )
                    {
                        if (chkAncRsnDk.isChecked()) chkAncRsnDk.setChecked(false);
                        if (chkAncRsnRefu.isChecked()) chkAncRsnRefu.setChecked(false);
                    }
                }
            });
            secAncPreference=(LinearLayout)findViewById(R.id.secAncPreference);
            lineAncPreference=(View)findViewById(R.id.lineAncPreference);
            VlblAncPreference=(TextView) findViewById(R.id.VlblAncPreference);
            chkAncPreference=(CheckBox) findViewById(R.id.chkAncPreference);
            chkAncPreference.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if ( isChecked )
                    {
                        if (chkAncRsnDk.isChecked()) chkAncRsnDk.setChecked(false);
                        if (chkAncRsnRefu.isChecked()) chkAncRsnRefu.setChecked(false);
                    }
                }
            });
            secAncRsnOth=(LinearLayout)findViewById(R.id.secAncRsnOth);
            lineAncRsnOth=(View)findViewById(R.id.lineAncRsnOth);
            VlblAncRsnOth=(TextView) findViewById(R.id.VlblAncRsnOth);
            chkAncRsnOth=(CheckBox) findViewById(R.id.chkAncRsnOth);
            chkAncRsnOth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if ( isChecked )
                    {
                        if (chkAncRsnDk.isChecked()) chkAncRsnDk.setChecked(false);
                        if (chkAncRsnRefu.isChecked()) chkAncRsnRefu.setChecked(false);
                        secAncRsnOthSp.setVisibility(View.VISIBLE);
                        lineAncRsnOthSp.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        secAncRsnOthSp.setVisibility(View.GONE);
                        lineAncRsnOthSp.setVisibility(View.GONE);
                        txtAncRsnOthSp.setText("");
                    }
                }
            });
            secAncRsnOthSp=(LinearLayout)findViewById(R.id.secAncRsnOthSp);
            lineAncRsnOthSp=(View)findViewById(R.id.lineAncRsnOthSp);
            VlblAncRsnOthSp=(TextView) findViewById(R.id.VlblAncRsnOthSp);
            txtAncRsnOthSp=(EditText) findViewById(R.id.txtAncRsnOthSp);
            secAncRsnDk=(LinearLayout)findViewById(R.id.secAncRsnDk);
            lineAncRsnDk=(View)findViewById(R.id.lineAncRsnDk);
            VlblAncRsnDk=(TextView) findViewById(R.id.VlblAncRsnDk);
            chkAncRsnDk=(CheckBox) findViewById(R.id.chkAncRsnDk);
            chkAncRsnDk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if ( isChecked )
                    {
                        if (chkAncLackMoney.isChecked()) chkAncLackMoney.setChecked(false);
                        if (chkAncLackTransport.isChecked()) chkAncLackTransport.setChecked(false);
                        if (chkAncDistance.isChecked()) chkAncDistance.setChecked(false);
                        if (chkAncAttitude.isChecked()) chkAncAttitude.setChecked(false);
                        if (chkAncPreference.isChecked()) chkAncPreference.setChecked(false);
                        if (chkAncRsnOth.isChecked()) chkAncRsnOth.setChecked(false);
                        if (chkAncRsnRefu.isChecked()) chkAncRsnRefu.setChecked(false);
                    }
                }
            });
            secAncRsnRefu=(LinearLayout)findViewById(R.id.secAncRsnRefu);
            lineAncRsnRefu=(View)findViewById(R.id.lineAncRsnRefu);
            VlblAncRsnRefu=(TextView) findViewById(R.id.VlblAncRsnRefu);
            chkAncRsnRefu=(CheckBox) findViewById(R.id.chkAncRsnRefu);
            chkAncRsnRefu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if ( isChecked )
                    {
                        if (chkAncLackMoney.isChecked()) chkAncLackMoney.setChecked(false);
                        if (chkAncLackTransport.isChecked()) chkAncLackTransport.setChecked(false);
                        if (chkAncDistance.isChecked()) chkAncDistance.setChecked(false);
                        if (chkAncAttitude.isChecked()) chkAncAttitude.setChecked(false);
                        if (chkAncPreference.isChecked()) chkAncPreference.setChecked(false);
                        if (chkAncRsnOth.isChecked()) chkAncRsnOth.setChecked(false);
                        if (chkAncRsnDk.isChecked()) chkAncRsnDk.setChecked(false);
                    }
                }
            });
            secANCPres = (LinearLayout) findViewById(R.id.secANCPres);
            lineANCPres = (View) findViewById(R.id.lineANCPres);
            VlblANCPres = (TextView) findViewById(R.id.VlblANCPres);
            rdogrpANCPres = (RadioGroup) findViewById(R.id.rdogrpANCPres);
            rdoANCPres1 = (RadioButton) findViewById(R.id.rdoANCPres1);
            rdoANCPres2 = (RadioButton) findViewById(R.id.rdoANCPres2);
            rdoANCPres3 = (RadioButton) findViewById(R.id.rdoANCPres3);
            rdoANCPres4 = (RadioButton) findViewById(R.id.rdoANCPres4);
            seclbl03 = (LinearLayout) findViewById(R.id.seclbl03);
            linelbl03 = (View) findViewById(R.id.linelbl03);
            secAncWeight = (LinearLayout) findViewById(R.id.secAncWeight);
            lineAncWeight = (View) findViewById(R.id.lineAncWeight);
            VlblAncWeight = (TextView) findViewById(R.id.VlblAncWeight);
            chkAncWeight = (CheckBox) findViewById(R.id.chkAncWeight);
            chkAncWeight.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    chkANCRefu.setChecked(false);
                    chkAncHCDk.setChecked(false);
                }
            });
            secAncBp = (LinearLayout) findViewById(R.id.secAncBp);
            lineAncBp = (View) findViewById(R.id.lineAncBp);
            VlblAncBp = (TextView) findViewById(R.id.VlblAncBp);
            chkAncBp = (CheckBox) findViewById(R.id.chkAncBp);
            chkAncBp.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    chkANCRefu.setChecked(false);
                    chkAncHCDk.setChecked(false);
                }
            });
            secAncUrine = (LinearLayout) findViewById(R.id.secAncUrine);
            lineAncUrine = (View) findViewById(R.id.lineAncUrine);
            VlblAncUrine = (TextView) findViewById(R.id.VlblAncUrine);
            chkAncUrine = (CheckBox) findViewById(R.id.chkAncUrine);
            chkAncUrine.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    chkANCRefu.setChecked(false);
                    chkAncHCDk.setChecked(false);
                }
            });
            secAncBlood = (LinearLayout) findViewById(R.id.secAncBlood);
            lineAncBlood = (View) findViewById(R.id.lineAncBlood);
            VlblAncBlood = (TextView) findViewById(R.id.VlblAncBlood);
            chkAncBlood = (CheckBox) findViewById(R.id.chkAncBlood);
            chkAncBlood.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    chkANCRefu.setChecked(false);
                    chkAncHCDk.setChecked(false);
                }
            });
            secAncHeartbeat = (LinearLayout) findViewById(R.id.secAncHeartbeat);
            lineAncHeartbeat = (View) findViewById(R.id.lineAncHeartbeat);
            VlblAncHeartbeat = (TextView) findViewById(R.id.VlblAncHeartbeat);
            chkAncHeartbeat = (CheckBox) findViewById(R.id.chkAncHeartbeat);
            chkAncHeartbeat.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    chkANCRefu.setChecked(false);
                    chkAncHCDk.setChecked(false);
                }
            });
            secAncUSG = (LinearLayout) findViewById(R.id.secAncUSG);
            lineAncUSG = (View) findViewById(R.id.lineAncUSG);
            VlblAncUSG = (TextView) findViewById(R.id.VlblAncUSG);
            chkAncUSG = (CheckBox) findViewById(R.id.chkAncUSG);
            chkAncUSG.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    chkANCRefu.setChecked(false);
                    chkAncHCDk.setChecked(false);
                }
            });
            secAncFood = (LinearLayout) findViewById(R.id.secAncFood);
            lineAncFood = (View) findViewById(R.id.lineAncFood);
            VlblAncFood = (TextView) findViewById(R.id.VlblAncFood);
            chkAncFood = (CheckBox) findViewById(R.id.chkAncFood);
            chkAncFood.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    chkANCRefu.setChecked(false);
                    chkAncHCDk.setChecked(false);
                }
            });
            secANCBF = (LinearLayout) findViewById(R.id.secANCBF);
            lineANCBF = (View) findViewById(R.id.lineANCBF);
            VlblANCBF = (TextView) findViewById(R.id.VlblANCBF);
            chkANCBF = (CheckBox) findViewById(R.id.chkANCBF);
            chkANCBF.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    chkANCRefu.setChecked(false);
                    chkAncHCDk.setChecked(false);
                }
            });
            secANCVBleed = (LinearLayout) findViewById(R.id.secANCVBleed);
            lineANCVBleed = (View) findViewById(R.id.lineANCVBleed);
            VlblANCVBleed = (TextView) findViewById(R.id.VlblANCVBleed);
            chkANCVBleed = (CheckBox) findViewById(R.id.chkANCVBleed);
            chkANCVBleed.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    chkANCRefu.setChecked(false);
                    chkAncHCDk.setChecked(false);
                }
            });
            secANCDSign = (LinearLayout) findViewById(R.id.secANCDSign);
            lineANCDSign = (View) findViewById(R.id.lineANCDSign);
            VlblANCDSign = (TextView) findViewById(R.id.VlblANCDSign);
            chkANCDSign = (CheckBox) findViewById(R.id.chkANCDSign);
            chkANCDSign.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    chkANCRefu.setChecked(false);
                    chkAncHCDk.setChecked(false);
                }
            });
            secANCFPMtd = (LinearLayout) findViewById(R.id.secANCFPMtd);
            lineANCFPMtd = (View) findViewById(R.id.lineANCFPMtd);
            VlblANCFPMtd = (TextView) findViewById(R.id.VlblANCFPMtd);
            chkANCFPMtd = (CheckBox) findViewById(R.id.chkANCFPMtd);
            chkANCFPMtd.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    chkANCRefu.setChecked(false);
                    chkAncHCDk.setChecked(false);
                }
            });
            secANCHCOth = (LinearLayout) findViewById(R.id.secANCHCOth);
            lineANCHCOth = (View) findViewById(R.id.lineANCHCOth);
            VlblANCHCOth = (TextView) findViewById(R.id.VlblANCHCOth);
            chkANCHCOth = (CheckBox) findViewById(R.id.chkANCHCOth);
            chkANCHCOth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkANCRefu.setChecked(false);
                        chkAncHCDk.setChecked(false);
                        secANCHCOthSp.setVisibility(View.VISIBLE);
                        lineANCHCOthSp.setVisibility(View.VISIBLE);
                    } else {
                        secANCHCOthSp.setVisibility(View.GONE);
                        lineANCHCOthSp.setVisibility(View.GONE);
                        txtANCHCOthSp.setText("");
                    }
                }
            });
            secANCHCOthSp = (LinearLayout) findViewById(R.id.secANCHCOthSp);
            lineANCHCOthSp = (View) findViewById(R.id.lineANCHCOthSp);
            VlblANCHCOthSp = (TextView) findViewById(R.id.VlblANCHCOthSp);
            txtANCHCOthSp = (AutoCompleteTextView) findViewById(R.id.txtANCHCOthSp);
            C.setupAutoComplete(TableName,txtANCHCOthSp,"ANCHCOthSp"); //setup autocomplete view by event

            secAncHCDk = (LinearLayout) findViewById(R.id.secAncHCDk);
            lineAncHCDk = (View) findViewById(R.id.lineAncHCDk);
            VlblAncHCDk = (TextView) findViewById(R.id.VlblAncHCDk);
            chkAncHCDk = (CheckBox) findViewById(R.id.chkAncHCDk);
            chkAncHCDk.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    chkAncWeight.setChecked(false);
                    chkAncBp.setChecked(false);
                    chkAncUrine.setChecked(false);
                    chkAncBlood.setChecked(false);
                    chkAncHeartbeat.setChecked(false);
                    chkAncUSG.setChecked(false);
                    chkAncFood.setChecked(false);
                    chkANCBF.setChecked(false);
                    chkANCVBleed.setChecked(false);
                    chkANCDSign.setChecked(false);
                    chkANCFPMtd.setChecked(false);
                    chkANCHCOth.setChecked(false);
                    chkANCRefu.setChecked(false);
                    txtANCHCOthSp.setText("");
                }
            });
            secANCRefu = (LinearLayout) findViewById(R.id.secANCRefu);
            lineANCRefu = (View) findViewById(R.id.lineANCRefu);
            VlblANCRefu = (TextView) findViewById(R.id.VlblANCRefu);
            chkANCRefu = (CheckBox) findViewById(R.id.chkANCRefu);
            chkANCRefu.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    chkAncWeight.setChecked(false);
                    chkAncBp.setChecked(false);
                    chkAncUrine.setChecked(false);
                    chkAncBlood.setChecked(false);
                    chkAncHeartbeat.setChecked(false);
                    chkAncUSG.setChecked(false);
                    chkAncFood.setChecked(false);
                    chkANCBF.setChecked(false);
                    chkANCVBleed.setChecked(false);
                    chkANCDSign.setChecked(false);
                    chkANCFPMtd.setChecked(false);
                    chkANCHCOth.setChecked(false);
                    chkAncHCDk.setChecked(false);
                    txtANCHCOthSp.setText("");
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(NBC_ANC.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(NBC_ANC.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            NBC_ANC_DataModel objSave = new NBC_ANC_DataModel();
            objSave.setNBID(txtNBID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setPGN(txtPGN.getText().toString());
            String[] d_rdogrpAnc = new String[]{"0", "1", "8", "9"};
            objSave.setAnc("");
            for (int i = 0; i < rdogrpAnc.getChildCount(); i++) {
                rb = (RadioButton) rdogrpAnc.getChildAt(i);
                if (rb.isChecked()) objSave.setAnc(d_rdogrpAnc[i]);
            }

            objSave.setAncNo(txtAncNo.getText().toString());
            String[] d_rdogrpAncNoDk = new String[]{"8", "9"};
            objSave.setAncNoDk("");
            for (int i = 0; i < rdogrpAncNoDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpAncNoDk.getChildAt(i);
                if (rb.isChecked()) objSave.setAncNoDk(d_rdogrpAncNoDk[i]);
            }

            String[] d_rdogrpAncCard = new String[]{"0", "1", "8", "9"};
            objSave.setAncCard("");
            for (int i = 0; i < rdogrpAncCard.getChildCount(); i++) {
                rb = (RadioButton) rdogrpAncCard.getChildAt(i);
                if (rb.isChecked()) objSave.setAncCard(d_rdogrpAncCard[i]);
            }

            objSave.setAncLackMoney((chkAncLackMoney.isChecked() ? "1" : (secAncLackMoney.isShown() ? "2" : "")));
            objSave.setAncLackTransport((chkAncLackTransport.isChecked() ? "1" : (secAncLackTransport.isShown() ? "2" : "")));
            objSave.setAncDistance((chkAncDistance.isChecked() ? "1" : (secAncDistance.isShown() ? "2" : "")));
            objSave.setAncAttitude((chkAncAttitude.isChecked() ? "1" : (secAncAttitude.isShown() ? "2" : "")));
            objSave.setAncPreference((chkAncPreference.isChecked() ? "1" : (secAncPreference.isShown() ? "2" : "")));
            objSave.setAncRsnOth((chkAncRsnOth.isChecked() ? "1" : (secAncRsnOth.isShown() ? "2" : "")));
            objSave.setAncRsnOthSp(txtAncRsnOthSp.getText().toString());
            objSave.setAncRsnDk((chkAncRsnDk.isChecked() ? "1" : (secAncRsnDk.isShown() ? "2" : "")));
            objSave.setAncRsnRefu((chkAncRsnRefu.isChecked() ? "1" : (secAncRsnRefu.isShown() ? "2" : "")));

            String[] d_rdogrpANCPres = new String[]{"0", "1", "8", "9"};
            objSave.setANCPres("");
            for (int i = 0; i < rdogrpANCPres.getChildCount(); i++) {
                rb = (RadioButton) rdogrpANCPres.getChildAt(i);
                if (rb.isChecked()) objSave.setANCPres(d_rdogrpANCPres[i]);
            }

            objSave.setAncWeight((chkAncWeight.isChecked() ? "1" : (secAncWeight.isShown() ? "2" : "")));
            objSave.setAncBp((chkAncBp.isChecked() ? "1" : (secAncBp.isShown() ? "2" : "")));
            objSave.setAncUrine((chkAncUrine.isChecked() ? "1" : (secAncUrine.isShown() ? "2" : "")));
            objSave.setAncBlood((chkAncBlood.isChecked() ? "1" : (secAncBlood.isShown() ? "2" : "")));
            objSave.setAncHeartbeat((chkAncHeartbeat.isChecked() ? "1" : (secAncHeartbeat.isShown() ? "2" : "")));
            objSave.setAncUSG((chkAncUSG.isChecked() ? "1" : (secAncUSG.isShown() ? "2" : "")));
            objSave.setAncFood((chkAncFood.isChecked() ? "1" : (secAncFood.isShown() ? "2" : "")));
            objSave.setANCBF((chkANCBF.isChecked() ? "1" : (secANCBF.isShown() ? "2" : "")));
            objSave.setANCVBleed((chkANCVBleed.isChecked() ? "1" : (secANCVBleed.isShown() ? "2" : "")));
            objSave.setANCDSign((chkANCDSign.isChecked() ? "1" : (secANCDSign.isShown() ? "2" : "")));
            objSave.setANCFPMtd((chkANCFPMtd.isChecked() ? "1" : (secANCFPMtd.isShown() ? "2" : "")));
            objSave.setANCHCOth((chkANCHCOth.isChecked() ? "1" : (secANCHCOth.isShown() ? "2" : "")));
            objSave.setANCHCOthSp(txtANCHCOthSp.getText().toString());
            objSave.setAncHCDk((chkAncHCDk.isChecked() ? "1" : (secAncHCDk.isShown() ? "2" : "")));
            objSave.setANCRefu((chkANCRefu.isChecked() ? "1" : (secANCRefu.isShown() ? "2" : "")));
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);
            if (status.length() == 0) {

                Toast.makeText(NBC_ANC.this, "Save Successfully...", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(NBC_ANC.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_ANC.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        try {
            ResetSectionColor();
            if (txtNBID.getText().toString().length() == 0 & secNBID.isShown()) {
                ValidationMsg += "\nRequired field: Birth Internal ID.";
                secNBID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtMemID.getText().toString().length() == 0 & secMemID.isShown()) {
                ValidationMsg += "\nRequired field: Member ID.";
                secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtHHID.getText().toString().length() == 0 & secHHID.isShown()) {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtPGN.getText().toString().length() == 0 & secPGN.isShown()) {
                ValidationMsg += "\nRequired field: PGN.";
                secPGN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoAnc1.isChecked() & !rdoAnc2.isChecked() & !rdoAnc3.isChecked() & !rdoAnc4.isChecked() & secAnc.isShown()) {
                ValidationMsg += "\nB2.3 Required field: Did you see anyone for antenatal care during your pregnancy with baby/[name]? (HERE ANTENATAL CARE REFERS TO HEALTHCARE SERVICES RATHER THAN SPIRITUAL SERVICES FROM A COMMUNITY SHAMAN).";
                secAnc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoAncNoDk1.isChecked() & !rdoAncNoDk2.isChecked() & txtAncNo.getText().toString().length() == 0 & secAncNoDk.isShown() & secAncNo.isShown()) {
                ValidationMsg += "\nRequired field: Don't know.";
                secAncNoDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));

                ValidationMsg += "\nB2.5 Required field: How many times did you receive antenatal care during your pregnancy with baby/[name]?.";
                secAncNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoAncCard1.isChecked() & !rdoAncCard2.isChecked() & !rdoAncCard3.isChecked() & !rdoAncCard4.isChecked() & secAncCard.isShown()) {
                ValidationMsg += "\nB2.6 Required field: Do you have an antenatal care (ANC) card?.";
                secAncCard.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoANCPres1.isChecked() & !rdoANCPres2.isChecked() & !rdoANCPres3.isChecked() & !rdoANCPres4.isChecked() & secANCPres.isShown()) {
                ValidationMsg += "\nB2.7 Required field: Do you have any prescriptions for your antenatal care checkup during this pregnancy period?.";
                secANCPres.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtANCHCOthSp.getText().toString().length() == 0 & secANCHCOthSp.isShown()) {
                ValidationMsg += "\nRequired field: Other specify.";
                secANCHCOthSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }


            //ANC Visit Check
            String TotalAncDetails = C.ReturnSingleValue("Select count(*)total from NBC_ANCDetail where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='" + PGN + "'");
            if (!Global.isNullOrEmpty(TotalAncDetails) & !rdoAncNoDk1.isChecked() & !rdoAncNoDk2.isChecked()) {
                if (Integer.parseInt(txtAncNo.getText().toString().length() == 0 ? "0" : txtAncNo.getText().toString()) != Integer.parseInt(TotalAncDetails)) {
                    ValidationMsg += "\nB2.5 Your total anc visit number and total anc visit record are not same.";
                    secAncNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));

                }
            }
        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor() {
        try {
            secNBID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secPGN.setBackgroundColor(Color.WHITE);
            secAnc.setBackgroundColor(Color.WHITE);
            secAncNo.setBackgroundColor(Color.WHITE);
            secAncNoDk.setBackgroundColor(Color.WHITE);
            secAncCard.setBackgroundColor(Color.WHITE);
            secANCPres.setBackgroundColor(Color.WHITE);
            secANCHCOthSp.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String MEMID, String HHID, String PGN) {
        try {
            RadioButton rb;
            NBC_ANC_DataModel d = new NBC_ANC_DataModel();
            String SQL = "Select * from " + TableName + "  Where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='" + PGN + "'";
            List<NBC_ANC_DataModel> data = d.SelectAll(this, SQL);
            for (NBC_ANC_DataModel item : data) {
                if (item.getAncNo().toString().length() > 0) {

                }
                txtNBID.setText(item.getNBID());
                txtMemID.setText(item.getMemID());
                txtHHID.setText(item.getHHID());
                txtPGN.setText(item.getPGN());
                String[] d_rdogrpAnc = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpAnc.length; i++) {
                    if (String.valueOf(item.getAnc()).equals(String.valueOf(d_rdogrpAnc[i]))) {
                        rb = (RadioButton) rdogrpAnc.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtAncNo.setText(item.getAncNo());
                String[] d_rdogrpAncNoDk = new String[]{"8", "9"};
                for (int i = 0; i < d_rdogrpAncNoDk.length; i++) {
                    if (String.valueOf(item.getAncNoDk()).equals(String.valueOf(d_rdogrpAncNoDk[i]))) {
                        rb = (RadioButton) rdogrpAncNoDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpAncCard = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpAncCard.length; i++) {
                    if (String.valueOf(item.getAncCard()).equals(String.valueOf(d_rdogrpAncCard[i]))) {
                        rb = (RadioButton) rdogrpAncCard.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                if(String.valueOf(item.getAncLackMoney()).equals("1"))
                {
                    chkAncLackMoney.setChecked(true);
                }
                else if(String.valueOf(item.getAncLackMoney()).equals("2"))
                {
                    chkAncLackMoney.setChecked(false);
                }
                if(String.valueOf(item.getAncLackTransport()).equals("1"))
                {
                    chkAncLackTransport.setChecked(true);
                }
                else if(String.valueOf(item.getAncLackTransport()).equals("2"))
                {
                    chkAncLackTransport.setChecked(false);
                }
                if(String.valueOf(item.getAncDistance()).equals("1"))
                {
                    chkAncDistance.setChecked(true);
                }
                else if(String.valueOf(item.getAncDistance()).equals("2"))
                {
                    chkAncDistance.setChecked(false);
                }
                if(String.valueOf(item.getAncAttitude()).equals("1"))
                {
                    chkAncAttitude.setChecked(true);
                }
                else if(String.valueOf(item.getAncAttitude()).equals("2"))
                {
                    chkAncAttitude.setChecked(false);
                }
                if(String.valueOf(item.getAncPreference()).equals("1"))
                {
                    chkAncPreference.setChecked(true);
                }
                else if(String.valueOf(item.getAncPreference()).equals("2"))
                {
                    chkAncPreference.setChecked(false);
                }
                if(String.valueOf(item.getAncRsnOth()).equals("1"))
                {
                    chkAncRsnOth.setChecked(true);
                }
                else if(String.valueOf(item.getAncRsnOth()).equals("2"))
                {
                    chkAncRsnOth.setChecked(false);
                }
                txtAncRsnOthSp.setText(item.getAncRsnOthSp());
                if(String.valueOf(item.getAncRsnDk()).equals("1"))
                {
                    chkAncRsnDk.setChecked(true);
                }
                else if(String.valueOf(item.getAncRsnDk()).equals("2"))
                {
                    chkAncRsnDk.setChecked(false);
                }
                if(String.valueOf(item.getAncRsnRefu()).equals("1"))
                {
                    chkAncRsnRefu.setChecked(true);
                }
                else if(String.valueOf(item.getAncRsnRefu()).equals("2"))
                {
                    chkAncRsnRefu.setChecked(false);
                }
                String[] d_rdogrpANCPres = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpANCPres.length; i++) {
                    if (String.valueOf(item.getANCPres()).equals(String.valueOf(d_rdogrpANCPres[i]))) {
                        rb = (RadioButton) rdogrpANCPres.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                if (String.valueOf(item.getAncWeight()).equals("1")) {
                    chkAncWeight.setChecked(true);
                } else if (String.valueOf(item.getAncWeight()).equals("2")) {
                    chkAncWeight.setChecked(false);
                }
                if (String.valueOf(item.getAncBp()).equals("1")) {
                    chkAncBp.setChecked(true);
                } else if (String.valueOf(item.getAncBp()).equals("2")) {
                    chkAncBp.setChecked(false);
                }
                if (String.valueOf(item.getAncUrine()).equals("1")) {
                    chkAncUrine.setChecked(true);
                } else if (String.valueOf(item.getAncUrine()).equals("2")) {
                    chkAncUrine.setChecked(false);
                }
                if (String.valueOf(item.getAncBlood()).equals("1")) {
                    chkAncBlood.setChecked(true);
                } else if (String.valueOf(item.getAncBlood()).equals("2")) {
                    chkAncBlood.setChecked(false);
                }
                if (String.valueOf(item.getAncHeartbeat()).equals("1")) {
                    chkAncHeartbeat.setChecked(true);
                } else if (String.valueOf(item.getAncHeartbeat()).equals("2")) {
                    chkAncHeartbeat.setChecked(false);
                }
                if (String.valueOf(item.getAncUSG()).equals("1")) {
                    chkAncUSG.setChecked(true);
                } else if (String.valueOf(item.getAncUSG()).equals("2")) {
                    chkAncUSG.setChecked(false);
                }
                if (String.valueOf(item.getAncFood()).equals("1")) {
                    chkAncFood.setChecked(true);
                } else if (String.valueOf(item.getAncFood()).equals("2")) {
                    chkAncFood.setChecked(false);
                }
                if (String.valueOf(item.getANCBF()).equals("1")) {
                    chkANCBF.setChecked(true);
                } else if (String.valueOf(item.getANCBF()).equals("2")) {
                    chkANCBF.setChecked(false);
                }
                if (String.valueOf(item.getANCVBleed()).equals("1")) {
                    chkANCVBleed.setChecked(true);
                } else if (String.valueOf(item.getANCVBleed()).equals("2")) {
                    chkANCVBleed.setChecked(false);
                }
                if (String.valueOf(item.getANCDSign()).equals("1")) {
                    chkANCDSign.setChecked(true);
                } else if (String.valueOf(item.getANCDSign()).equals("2")) {
                    chkANCDSign.setChecked(false);
                }
                if (String.valueOf(item.getANCFPMtd()).equals("1")) {
                    chkANCFPMtd.setChecked(true);
                } else if (String.valueOf(item.getANCFPMtd()).equals("2")) {
                    chkANCFPMtd.setChecked(false);
                }
                if (String.valueOf(item.getANCHCOth()).equals("1")) {
                    chkANCHCOth.setChecked(true);
                } else if (String.valueOf(item.getANCHCOth()).equals("2")) {
                    chkANCHCOth.setChecked(false);
                }
                txtANCHCOthSp.setText(item.getANCHCOthSp());
                if (String.valueOf(item.getAncHCDk()).equals("1")) {
                    chkAncHCDk.setChecked(true);
                } else if (String.valueOf(item.getAncHCDk()).equals("2")) {
                    chkAncHCDk.setChecked(false);
                }
                if (String.valueOf(item.getANCRefu()).equals("1")) {
                    chkANCRefu.setChecked(true);
                } else if (String.valueOf(item.getANCRefu()).equals("2")) {
                    chkANCRefu.setChecked(false);
                }
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_ANC.this, e.getMessage());
            return;
        }
    }

    private void DataSearchANCList(String MEMID, String HHID, String PGN) {
        try {

            NBC_ANCDetail_DataModel d = new NBC_ANCDetail_DataModel();
            String SQL = "Select * from NBC_ANCDetail  Where MemID = '" + MEMID + "' and HHID = '" + HHID + "' and PGN = '" + PGN + "' and isdelete=2";
            List<NBC_ANCDetail_DataModel> data = d.SelectAll(this, SQL);
            dataList.clear();

            dataList.addAll(data);
            if (dataList != null && !dataList.isEmpty()) {
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.GONE);
            }
            try {
                mAdapter.notifyDataSetChanged();
            } catch (Exception ex) {
                Connection.MessageBox(NBC_ANC.this, ex.getMessage());
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_ANC.this, e.getMessage());
            return;
        }
    }


    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
        private List<NBC_ANCDetail_DataModel> dataList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout secListRow;
            TextView ANCSl;
            TextView ANCDate;

            public MyViewHolder(View convertView) {
                super(convertView);
                secListRow = (LinearLayout) convertView.findViewById(R.id.secListRow);
                ANCSl = (TextView) convertView.findViewById(R.id.ANCSl);
                ANCDate = (TextView) convertView.findViewById(R.id.ANCDate);
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
            holder.ANCDate.setText(Global.DateConvertDMY(data.getANCDate()));

            holder.secListRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(NBC_ANC.this, "", "Please Wait . . .", true);
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
                                startActivityForResult(f1, 1);
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
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mDateSetListener, g.mYear, g.mMonth - 1, g.mDay);
            case TIME_DIALOG:
                return new TimePickerDialog(this, timePickerListener, hour, minute, false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear + 1;
            mDay = dayOfMonth;
            EditText dtpDate;


        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;
            EditText tpTime;

        }
    };


    // turning off the GPS if its in on state. to avoid the battery drain.
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DataSearchANCList(MEMID, HHID, PGN);
    }
}