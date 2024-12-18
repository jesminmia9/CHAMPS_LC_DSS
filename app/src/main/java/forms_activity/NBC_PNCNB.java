
package forms_activity;


import android.app.Activity;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.icddrb.champs_lc_dss.R;
import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Common.Connection;
import Common.Global;
import Utility.MySharedPreferences;
import forms_datamodel.NBC_PNCNBDetail_DataModel;
import forms_datamodel.NBC_PNCNB_DataModel;

public class NBC_PNCNB extends AppCompatActivity {
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

    private List<NBC_PNCNBDetail_DataModel> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
    Button btnAdd;

    Connection C;
    Global g;
    SimpleAdapter dataAdapter;
    //    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    TextView lblHeading;
    LinearLayout seclbl01;
    View linelbl01;
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
    LinearLayout secChSl;
    View lineChSl;
    TextView VlblChSl;
    EditText txtChSl;
    LinearLayout secChPNCCkup;
    View lineChPNCCkup;
    TextView VlblChPNCCkup;
    RadioGroup rdogrpChPNCCkup;
    RadioButton rdoChPNCCkup1;
    RadioButton rdoChPNCCkup2;
    RadioButton rdoChPNCCkup3;
    RadioButton rdoChPNCCkup4;
    LinearLayout seclbl02;
    View linelbl02;
    LinearLayout secChPNCCheckUnit;
    View lineChPNCCheckUnit;
    TextView VlblChPNCCheckUnit;
    Spinner spnChPNCCheckUnit;
    LinearLayout secChPNCCheckDur;
    View lineChPNCCheckDur;
    TextView VlblChPNCCheckDur;
    EditText txtChPNCCheckDur;
    LinearLayout secChPNCTotal;
    View lineChPNCTotal;
    View lineanclist;
    TextView VlblChPNCTotal;
    EditText txtChPNCTotal;
    LinearLayout secChPNCCard;
    View lineChPNCCard;
    TextView VlblChPNCCard;
    RadioGroup rdogrpChPNCCard;
    RadioButton rdoChPNCCard1;
    RadioButton rdoChPNCCard2;
    RadioButton rdoChPNCCard3;
    RadioButton rdoChPNCCard4;
    LinearLayout secChPNCPres;
    View lineChPNCPres;
    TextView VlblChPNCPres;
    RadioGroup rdogrpChPNCPres;
    RadioButton rdoChPNCPres1;
    RadioButton rdoChPNCPres2;
    RadioButton rdoChPNCPres3;
    RadioButton rdoChPNCPres4;
    LinearLayout seclbl03;
    View linelbl03;
    LinearLayout secChPNC42dWA;
    View lineChPNC42dWA;
    TextView VlblChPNC42dWA;
    CheckBox chkChPNC42dWA;
    LinearLayout secChPNC42dTM;
    View lineChPNC42dTM;
    TextView VlblChPNC42dTM;
    CheckBox chkChPNC42dTM;
    LinearLayout secChPNC42dRRA;
    View lineChPNC42dRRA;
    TextView VlblChPNC42dRRA;
    CheckBox chkChPNC42dRRA;
    LinearLayout secChPNC42IM;
    View lineChPNC42IM;
    TextView VlblChPNC42IM;
    CheckBox chkChPNC42IM;
    LinearLayout secChPNC42CE;
    View lineChPNC42CE;
    TextView VlblChPNC42CE;
    CheckBox chkChPNC42CE;
    LinearLayout secChPNC42CDS;
    View lineChPNC42CDS;
    TextView VlblChPNC42CDS;
    CheckBox chkChPNC42CDS;
    LinearLayout secChPNC42CB;
    View lineChPNC42CB;
    TextView VlblChPNC42CB;
    CheckBox chkChPNC42CB;
    LinearLayout secChPNC42OB;
    View lineChPNC42OB;
    TextView VlblChPNC42OB;
    CheckBox chkChPNC42OB;
    LinearLayout secChPNC42dOth;
    View lineChPNC42dOth;
    TextView VlblChPNC42dOth;
    CheckBox chkChPNC42dOth;
    LinearLayout secChPNC42dOthSp;
    View lineChPNC42dOthSp;
    TextView VlblChPNC42dOthSp;
    AutoCompleteTextView txtChPNC42dOthSp;
    LinearLayout secChPNC42dDk;
    View lineChPNC42dDk;
    TextView VlblChPNC42dDk;
    CheckBox chkChPNC42dDk;
    LinearLayout secChPNC42dRR;
    View lineChPNC42dRR;
    TextView VlblChPNC42dRR;
    CheckBox chkChPNC42dRR;
    LinearLayout secChPNCList;

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
     String CHSL = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.nbc_pncnb);
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
            CHSL = IDbundle.getString("childsl");

            TableName = "NBC_PNCNB";
            MODULEID = 46;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            btnAdd = (Button) findViewById(R.id.btnAdd);
            btnAdd.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    String TotalChAncDetails = C.ReturnSingleValue("Select count(*)total from NBC_PNCNBDetail where MemID='" + MEMID + "' and ChSl='" + CHSL + "' and HHID='" + HHID + "' and PGN='" + PGN + "'");
                    if (!Global.isNullOrEmpty(TotalChAncDetails)) {
                        if (Integer.parseInt(txtChPNCTotal.getText().toString().length() == 0 ? "0" : txtChPNCTotal.getText().toString()) <= Integer.parseInt(TotalChAncDetails)) {
                            Connection.MessageBox(NBC_PNCNB.this, "You can't enter more record where as total number of pnc is " + txtChPNCTotal.getText().toString() + ".");
                            return;
                        }
                    }
                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("NBID", "");
                    IDbundle.putString("PGN", PGN);
                    IDbundle.putString("MemID", MEMID);
                    IDbundle.putString("HHID", HHID);
                    IDbundle.putString("round", ROUND);
                    IDbundle.putString("ChSl", CHSL);
                    IDbundle.putString("isEdit", "1");
                    IDbundle.putString("ChPNCSl", "");
                    Intent intent = new Intent(getApplicationContext(), NBC_PNCNBDetail.class);
                    intent.putExtras(IDbundle);
                    startActivityForResult(intent, 1);

                }
            });

            secChPNCList = findViewById(R.id.secChPNCList);
            lineanclist = findViewById(R.id.lineanclist);
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

                    String TotalPncDetails = C.ReturnSingleValue("Select count(*)total from NBC_PNCNBDetail Where MemID = '" + MEMID + "' and HHID = '" + HHID + "' and PGN = '" + PGN + "'  and ChSl = '" + CHSL + "'");
                    if (!Global.isNullOrEmpty(TotalPncDetails)){
                        if(Integer.parseInt(txtChPNCTotal.getText().toString().length() == 0 ? "0" : txtChPNCTotal.getText().toString()) != Integer.parseInt(TotalPncDetails)){
                            Connection.MessageBox(NBC_PNCNB.this, "Your total pnc visit number and total pnc visit record are not same.");
                            return;
                        }
                    }
                    AlertDialog.Builder adb = new AlertDialog.Builder(NBC_PNCNB.this);
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
            Connection.LocalizeLanguage(NBC_PNCNB.this, MODULEID, LANGUAGEID);


            //Hide all skip variables
            seclbl02.setVisibility(View.GONE);
            linelbl02.setVisibility(View.GONE);
            secChPNCCheckUnit.setVisibility(View.GONE);
            lineChPNCCheckUnit.setVisibility(View.GONE);
            secChPNCCheckDur.setVisibility(View.GONE);
            lineChPNCCheckDur.setVisibility(View.GONE);
            secChPNCTotal.setVisibility(View.GONE);
            lineChPNCTotal.setVisibility(View.GONE);
            secChPNCCard.setVisibility(View.GONE);
            lineChPNCCard.setVisibility(View.GONE);
            secChPNCPres.setVisibility(View.GONE);
            lineChPNCPres.setVisibility(View.GONE);
            secChPNCCheckDur.setVisibility(View.GONE);
            lineChPNCCheckDur.setVisibility(View.GONE);
            secChPNCList.setVisibility(View.GONE);
            lineanclist.setVisibility(View.GONE);
            seclbl03.setVisibility(View.GONE);
            linelbl03.setVisibility(View.GONE);
            secChPNC42dWA.setVisibility(View.GONE);
            lineChPNC42dWA.setVisibility(View.GONE);
            secChPNC42dTM.setVisibility(View.GONE);
            lineChPNC42dTM.setVisibility(View.GONE);
            secChPNC42dRRA.setVisibility(View.GONE);
            lineChPNC42dRRA.setVisibility(View.GONE);
            secChPNC42IM.setVisibility(View.GONE);
            lineChPNC42IM.setVisibility(View.GONE);
            secChPNC42CE.setVisibility(View.GONE);
            lineChPNC42CE.setVisibility(View.GONE);
            secChPNC42CDS.setVisibility(View.GONE);
            lineChPNC42CDS.setVisibility(View.GONE);
            secChPNC42CB.setVisibility(View.GONE);
            lineChPNC42CB.setVisibility(View.GONE);
            secChPNC42OB.setVisibility(View.GONE);
            lineChPNC42OB.setVisibility(View.GONE);
            secChPNC42dOth.setVisibility(View.GONE);
            lineChPNC42dOth.setVisibility(View.GONE);
            secChPNC42dOthSp.setVisibility(View.GONE);
            lineChPNC42dOthSp.setVisibility(View.GONE);
            secChPNC42dDk.setVisibility(View.GONE);
            lineChPNC42dDk.setVisibility(View.GONE);
            secChPNC42dRR.setVisibility(View.GONE);
            lineChPNC42dRR.setVisibility(View.GONE);
            secChPNCCheckDur.setVisibility(View.GONE);
            lineChPNCCheckDur.setVisibility(View.GONE);
            secChPNC42dOthSp.setVisibility(View.GONE);
            lineChPNC42dOthSp.setVisibility(View.GONE);


            DataSearch(MEMID, HHID, PGN, CHSL);
            DataSearchPNCChList(MEMID, HHID, PGN, CHSL);

            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });

            //Global.RoleManagement(cmdSave, MySharedPreferences.getValue(this, "userrole"));
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCNB.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            seclbl01 = (LinearLayout) findViewById(R.id.seclbl01);
            linelbl01 = (View) findViewById(R.id.linelbl01);
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
            secChSl = (LinearLayout) findViewById(R.id.secChSl);
            lineChSl = (View) findViewById(R.id.lineChSl);
            VlblChSl = (TextView) findViewById(R.id.VlblChSl);
            txtChSl = (EditText) findViewById(R.id.txtChSl);
            txtChSl.setText(CHSL);
            txtChSl.setEnabled(false);
            secChPNCCkup = (LinearLayout) findViewById(R.id.secChPNCCkup);
            lineChPNCCkup = (View) findViewById(R.id.lineChPNCCkup);
            VlblChPNCCkup = (TextView) findViewById(R.id.VlblChPNCCkup);
            rdogrpChPNCCkup = (RadioGroup) findViewById(R.id.rdogrpChPNCCkup);
            rdoChPNCCkup1 = (RadioButton) findViewById(R.id.rdoChPNCCkup1);
            rdoChPNCCkup2 = (RadioButton) findViewById(R.id.rdoChPNCCkup2);
            rdoChPNCCkup3 = (RadioButton) findViewById(R.id.rdoChPNCCkup3);
            rdoChPNCCkup4 = (RadioButton) findViewById(R.id.rdoChPNCCkup4);
            rdogrpChPNCCkup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpChPNCCkup = new String[]{"0", "1", "8", "9"};
                    for (int i = 0; i < rdogrpChPNCCkup.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpChPNCCkup.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpChPNCCkup[i];
                    }

                    if (rbData.equalsIgnoreCase("0") || rbData.equalsIgnoreCase("8") || rbData.equalsIgnoreCase("9")) {
                        seclbl02.setVisibility(View.GONE);
                        linelbl02.setVisibility(View.GONE);
                        secChPNCCheckUnit.setVisibility(View.GONE);
                        lineChPNCCheckUnit.setVisibility(View.GONE);
                        spnChPNCCheckUnit.setSelection(0);
                        secChPNCCheckDur.setVisibility(View.GONE);
                        lineChPNCCheckDur.setVisibility(View.GONE);
                        txtChPNCCheckDur.setText("");
                        secChPNCTotal.setVisibility(View.GONE);
                        lineChPNCTotal.setVisibility(View.GONE);
                        txtChPNCTotal.setText("");
                        secChPNCCard.setVisibility(View.GONE);
                        lineChPNCCard.setVisibility(View.GONE);
                        rdogrpChPNCCard.clearCheck();
                        secChPNCPres.setVisibility(View.GONE);
                        lineChPNCPres.setVisibility(View.GONE);
                        rdogrpChPNCPres.clearCheck();
                        seclbl03.setVisibility(View.GONE);
                        linelbl03.setVisibility(View.GONE);
                        secChPNC42dWA.setVisibility(View.GONE);
                        lineChPNC42dWA.setVisibility(View.GONE);
                        chkChPNC42dWA.setChecked(false);
                        secChPNC42dTM.setVisibility(View.GONE);
                        lineChPNC42dTM.setVisibility(View.GONE);
                        chkChPNC42dTM.setChecked(false);
                        secChPNC42dRRA.setVisibility(View.GONE);
                        lineChPNC42dRRA.setVisibility(View.GONE);
                        chkChPNC42dRRA.setChecked(false);
                        secChPNC42IM.setVisibility(View.GONE);
                        lineChPNC42IM.setVisibility(View.GONE);
                        chkChPNC42IM.setChecked(false);
                        secChPNC42CE.setVisibility(View.GONE);
                        lineChPNC42CE.setVisibility(View.GONE);
                        chkChPNC42CE.setChecked(false);
                        secChPNC42CDS.setVisibility(View.GONE);
                        lineChPNC42CDS.setVisibility(View.GONE);
                        chkChPNC42CDS.setChecked(false);
                        secChPNC42CB.setVisibility(View.GONE);
                        lineChPNC42CB.setVisibility(View.GONE);
                        chkChPNC42CB.setChecked(false);
                        secChPNC42OB.setVisibility(View.GONE);
                        lineChPNC42OB.setVisibility(View.GONE);
                        chkChPNC42OB.setChecked(false);
                        secChPNC42dOth.setVisibility(View.GONE);
                        lineChPNC42dOth.setVisibility(View.GONE);
                        chkChPNC42dOth.setChecked(false);
                        secChPNC42dOthSp.setVisibility(View.GONE);
                        lineChPNC42dOthSp.setVisibility(View.GONE);
                        txtChPNC42dOthSp.setText("");
                        secChPNC42dDk.setVisibility(View.GONE);
                        lineChPNC42dDk.setVisibility(View.GONE);
                        chkChPNC42dDk.setChecked(false);
                        secChPNC42dRR.setVisibility(View.GONE);
                        lineChPNC42dRR.setVisibility(View.GONE);
                        chkChPNC42dRR.setChecked(false);
                    } else {
                        seclbl02.setVisibility(View.VISIBLE);
                        linelbl02.setVisibility(View.VISIBLE);
                        secChPNCCheckUnit.setVisibility(View.VISIBLE);
                        lineChPNCCheckUnit.setVisibility(View.VISIBLE);
                        secChPNCTotal.setVisibility(View.VISIBLE);
                        lineChPNCTotal.setVisibility(View.VISIBLE);
                        secChPNCCard.setVisibility(View.VISIBLE);
                        lineChPNCCard.setVisibility(View.VISIBLE);
                        secChPNCPres.setVisibility(View.VISIBLE);
                        lineChPNCPres.setVisibility(View.VISIBLE);
                        seclbl03.setVisibility(View.VISIBLE);
                        linelbl03.setVisibility(View.VISIBLE);
                        secChPNC42dWA.setVisibility(View.VISIBLE);
                        lineChPNC42dWA.setVisibility(View.VISIBLE);
                        secChPNC42dTM.setVisibility(View.VISIBLE);
                        lineChPNC42dTM.setVisibility(View.VISIBLE);
                        secChPNC42dRRA.setVisibility(View.VISIBLE);
                        lineChPNC42dRRA.setVisibility(View.VISIBLE);
                        secChPNC42IM.setVisibility(View.VISIBLE);
                        lineChPNC42IM.setVisibility(View.VISIBLE);
                        secChPNC42CE.setVisibility(View.VISIBLE);
                        lineChPNC42CE.setVisibility(View.VISIBLE);
                        secChPNC42CDS.setVisibility(View.VISIBLE);
                        lineChPNC42CDS.setVisibility(View.VISIBLE);
                        secChPNC42CB.setVisibility(View.VISIBLE);
                        lineChPNC42CB.setVisibility(View.VISIBLE);
                        secChPNC42OB.setVisibility(View.VISIBLE);
                        lineChPNC42OB.setVisibility(View.VISIBLE);
                        secChPNC42dOth.setVisibility(View.VISIBLE);
                        lineChPNC42dOth.setVisibility(View.VISIBLE);
                        secChPNC42dDk.setVisibility(View.VISIBLE);
                        lineChPNC42dDk.setVisibility(View.VISIBLE);
                        secChPNC42dRR.setVisibility(View.VISIBLE);
                        lineChPNC42dRR.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            seclbl02 = (LinearLayout) findViewById(R.id.seclbl02);
            linelbl02 = (View) findViewById(R.id.linelbl02);
            secChPNCCheckUnit = (LinearLayout) findViewById(R.id.secChPNCCheckUnit);
            lineChPNCCheckUnit = (View) findViewById(R.id.lineChPNCCheckUnit);
            VlblChPNCCheckUnit = (TextView) findViewById(R.id.VlblChPNCCheckUnit);
            spnChPNCCheckUnit = (Spinner) findViewById(R.id.spnChPNCCheckUnit);
            List<String> listChPNCCheckUnit = new ArrayList<String>();

            listChPNCCheckUnit.add("");
            listChPNCCheckUnit.add("Minutes");
            listChPNCCheckUnit.add("Hours");
            listChPNCCheckUnit.add("Days");
            listChPNCCheckUnit.add("Weeks");
            listChPNCCheckUnit.add("Don’t know");
            spnChPNCCheckUnit.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listChPNCCheckUnit)));

            spnChPNCCheckUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnChPNCCheckUnit.getSelectedItem().toString().length() != 0) {
//                        spnData = Connection.SelectedSpinnerValue(spnChPNCCheckUnit.getSelectedItem().toString(), "-");
                        spnData = String.valueOf(spnChPNCCheckUnit.getSelectedItemPosition());
                    }
                    if (spnData.equalsIgnoreCase("5")) {
                        secChPNCCheckDur.setVisibility(View.GONE);
                        lineChPNCCheckDur.setVisibility(View.GONE);
                        txtChPNCCheckDur.setText("");
                    } else {
                        secChPNCCheckDur.setVisibility(View.VISIBLE);
                        lineChPNCCheckDur.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secChPNCCheckDur = (LinearLayout) findViewById(R.id.secChPNCCheckDur);
            lineChPNCCheckDur = (View) findViewById(R.id.lineChPNCCheckDur);
            VlblChPNCCheckDur = (TextView) findViewById(R.id.VlblChPNCCheckDur);
            txtChPNCCheckDur = (EditText) findViewById(R.id.txtChPNCCheckDur);
            secChPNCTotal = (LinearLayout) findViewById(R.id.secChPNCTotal);
            lineChPNCTotal = (View) findViewById(R.id.lineChPNCTotal);
            VlblChPNCTotal = (TextView) findViewById(R.id.VlblChPNCTotal);
            txtChPNCTotal = (EditText) findViewById(R.id.txtChPNCTotal);
            txtChPNCTotal.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (txtChPNCTotal.getText().toString().length() > 0 && !txtChPNCTotal.getText().toString().equals("0")) {
                        secChPNCList.setVisibility(View.VISIBLE);
                        lineanclist.setVisibility(View.VISIBLE);
                    } else {
                        secChPNCList.setVisibility(View.GONE);
                        lineanclist.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            secChPNCCard = (LinearLayout) findViewById(R.id.secChPNCCard);
            lineChPNCCard = (View) findViewById(R.id.lineChPNCCard);
            VlblChPNCCard = (TextView) findViewById(R.id.VlblChPNCCard);
            rdogrpChPNCCard = (RadioGroup) findViewById(R.id.rdogrpChPNCCard);
            rdoChPNCCard1 = (RadioButton) findViewById(R.id.rdoChPNCCard1);
            rdoChPNCCard2 = (RadioButton) findViewById(R.id.rdoChPNCCard2);
            rdoChPNCCard3 = (RadioButton) findViewById(R.id.rdoChPNCCard3);
            rdoChPNCCard4 = (RadioButton) findViewById(R.id.rdoChPNCCard4);
            secChPNCPres = (LinearLayout) findViewById(R.id.secChPNCPres);
            lineChPNCPres = (View) findViewById(R.id.lineChPNCPres);
            VlblChPNCPres = (TextView) findViewById(R.id.VlblChPNCPres);
            rdogrpChPNCPres = (RadioGroup) findViewById(R.id.rdogrpChPNCPres);
            rdoChPNCPres1 = (RadioButton) findViewById(R.id.rdoChPNCPres1);
            rdoChPNCPres2 = (RadioButton) findViewById(R.id.rdoChPNCPres2);
            rdoChPNCPres3 = (RadioButton) findViewById(R.id.rdoChPNCPres3);
            rdoChPNCPres4 = (RadioButton) findViewById(R.id.rdoChPNCPres4);
            seclbl03 = (LinearLayout) findViewById(R.id.seclbl03);
            linelbl03 = (View) findViewById(R.id.linelbl03);
            secChPNC42dWA = (LinearLayout) findViewById(R.id.secChPNC42dWA);
            lineChPNC42dWA = (View) findViewById(R.id.lineChPNC42dWA);
            VlblChPNC42dWA = (TextView) findViewById(R.id.VlblChPNC42dWA);
            chkChPNC42dWA = (CheckBox) findViewById(R.id.chkChPNC42dWA);
            chkChPNC42dWA.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkChPNC42dRR.setChecked(false);
                    chkChPNC42dDk.setChecked(false);
                }
            });
            secChPNC42dTM = (LinearLayout) findViewById(R.id.secChPNC42dTM);
            lineChPNC42dTM = (View) findViewById(R.id.lineChPNC42dTM);
            VlblChPNC42dTM = (TextView) findViewById(R.id.VlblChPNC42dTM);
            chkChPNC42dTM = (CheckBox) findViewById(R.id.chkChPNC42dTM);
            chkChPNC42dTM.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkChPNC42dRR.setChecked(false);
                    chkChPNC42dDk.setChecked(false);
                }
            });
            secChPNC42IM=(LinearLayout)findViewById(R.id.secChPNC42IM);
            lineChPNC42IM=(View)findViewById(R.id.lineChPNC42IM);
            VlblChPNC42IM=(TextView) findViewById(R.id.VlblChPNC42IM);
            chkChPNC42IM=(CheckBox) findViewById(R.id.chkChPNC42IM);
            chkChPNC42IM.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkChPNC42dRR.setChecked(false);
                    chkChPNC42dDk.setChecked(false);
                }
            });
            secChPNC42CE=(LinearLayout)findViewById(R.id.secChPNC42CE);
            lineChPNC42CE=(View)findViewById(R.id.lineChPNC42CE);
            VlblChPNC42CE=(TextView) findViewById(R.id.VlblChPNC42CE);
            chkChPNC42CE=(CheckBox) findViewById(R.id.chkChPNC42CE);
            chkChPNC42CE.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    chkChPNC42dRR.setChecked(false);
                    chkChPNC42dDk.setChecked(false);
                }
            });
            secChPNC42CDS=(LinearLayout)findViewById(R.id.secChPNC42CDS);
            lineChPNC42CDS=(View)findViewById(R.id.lineChPNC42CDS);
            VlblChPNC42CDS=(TextView) findViewById(R.id.VlblChPNC42CDS);
            chkChPNC42CDS=(CheckBox) findViewById(R.id.chkChPNC42CDS);
            chkChPNC42CDS.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    chkChPNC42dRR.setChecked(false);
                    chkChPNC42dDk.setChecked(false);
                }
            });
            secChPNC42CB=(LinearLayout)findViewById(R.id.secChPNC42CB);
            lineChPNC42CB=(View)findViewById(R.id.lineChPNC42CB);
            VlblChPNC42CB=(TextView) findViewById(R.id.VlblChPNC42CB);
            chkChPNC42CB=(CheckBox) findViewById(R.id.chkChPNC42CB);
            chkChPNC42CB.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    chkChPNC42dRR.setChecked(false);
                    chkChPNC42dDk.setChecked(false);
                }
            });
            secChPNC42OB=(LinearLayout)findViewById(R.id.secChPNC42OB);
            lineChPNC42OB=(View)findViewById(R.id.lineChPNC42OB);
            VlblChPNC42OB=(TextView) findViewById(R.id.VlblChPNC42OB);
            chkChPNC42OB=(CheckBox) findViewById(R.id.chkChPNC42OB);
            chkChPNC42OB.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    chkChPNC42dRR.setChecked(false);
                    chkChPNC42dDk.setChecked(false);
                }
            });

            secChPNC42dRRA = (LinearLayout) findViewById(R.id.secChPNC42dRRA);
            lineChPNC42dRRA = (View) findViewById(R.id.lineChPNC42dRRA);
            VlblChPNC42dRRA = (TextView) findViewById(R.id.VlblChPNC42dRRA);
            chkChPNC42dRRA = (CheckBox) findViewById(R.id.chkChPNC42dRRA);
            chkChPNC42dRRA.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkChPNC42dRR.setChecked(false);
                    chkChPNC42dDk.setChecked(false);
                }
            });
            secChPNC42dOth = (LinearLayout) findViewById(R.id.secChPNC42dOth);
            lineChPNC42dOth = (View) findViewById(R.id.lineChPNC42dOth);
            VlblChPNC42dOth = (TextView) findViewById(R.id.VlblChPNC42dOth);
            chkChPNC42dOth = (CheckBox) findViewById(R.id.chkChPNC42dOth);
            chkChPNC42dOth.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkChPNC42dRR.setChecked(false);
                    chkChPNC42dDk.setChecked(false);
                    secChPNC42dOthSp.setVisibility(View.VISIBLE);
                    lineChPNC42dOthSp.setVisibility(View.VISIBLE);
                } else {
                    secChPNC42dOthSp.setVisibility(View.GONE);
                    lineChPNC42dOthSp.setVisibility(View.GONE);
                    txtChPNC42dOthSp.setText("");
                }
            });
            secChPNC42dOthSp = (LinearLayout) findViewById(R.id.secChPNC42dOthSp);
            lineChPNC42dOthSp = (View) findViewById(R.id.lineChPNC42dOthSp);
            VlblChPNC42dOthSp = (TextView) findViewById(R.id.VlblChPNC42dOthSp);
            txtChPNC42dOthSp = (AutoCompleteTextView) findViewById(R.id.txtChPNC42dOthSp);
            C.setupAutoComplete(TableName,txtChPNC42dOthSp,"ChPNC42dOthSp"); //setup autocomplete view by event

            secChPNC42dDk = (LinearLayout) findViewById(R.id.secChPNC42dDk);
            lineChPNC42dDk = (View) findViewById(R.id.lineChPNC42dDk);
            VlblChPNC42dDk = (TextView) findViewById(R.id.VlblChPNC42dDk);
            chkChPNC42dDk = (CheckBox) findViewById(R.id.chkChPNC42dDk);
            chkChPNC42dDk.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    txtChPNC42dOthSp.setText("");
                    chkChPNC42dRR.setChecked(false);
                    chkChPNC42dWA.setChecked(false);
                    chkChPNC42dTM.setChecked(false);
                    chkChPNC42dRRA.setChecked(false);
                    chkChPNC42IM.setChecked(false);
                    chkChPNC42CE.setChecked(false);
                    chkChPNC42CDS.setChecked(false);
                    chkChPNC42CB.setChecked(false);
                    chkChPNC42OB.setChecked(false);
                    chkChPNC42dOth.setChecked(false);
                }
            });
            secChPNC42dRR = (LinearLayout) findViewById(R.id.secChPNC42dRR);
            lineChPNC42dRR = (View) findViewById(R.id.lineChPNC42dRR);
            VlblChPNC42dRR = (TextView) findViewById(R.id.VlblChPNC42dRR);
            chkChPNC42dRR = (CheckBox) findViewById(R.id.chkChPNC42dRR);
            chkChPNC42dRR.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    txtChPNC42dOthSp.setText("");
                    chkChPNC42dDk.setChecked(false);
                    chkChPNC42dWA.setChecked(false);
                    chkChPNC42dTM.setChecked(false);
                    chkChPNC42dRRA.setChecked(false);
                    chkChPNC42IM.setChecked(false);
                    chkChPNC42CE.setChecked(false);
                    chkChPNC42CDS.setChecked(false);
                    chkChPNC42CB.setChecked(false);
                    chkChPNC42OB.setChecked(false);
                    chkChPNC42dOth.setChecked(false);
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCNB.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(NBC_PNCNB.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            NBC_PNCNB_DataModel objSave = new NBC_PNCNB_DataModel();
            objSave.setNBID(txtNBID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setPGN(txtPGN.getText().toString());
            objSave.setChSl(txtChSl.getText().toString());
            String[] d_rdogrpChPNCCkup = new String[]{"0", "1", "8", "9"};
            objSave.setChPNCCkup("");
            for (int i = 0; i < rdogrpChPNCCkup.getChildCount(); i++) {
                rb = (RadioButton) rdogrpChPNCCkup.getChildAt(i);
                if (rb.isChecked()) objSave.setChPNCCkup(d_rdogrpChPNCCkup[i]);
            }

//            objSave.setChPNCCheckUnit(spnChPNCCheckUnit.getSelectedItemPosition() == 0 ? "" : spnChPNCCheckUnit.getSelectedItem().toString().split("-")[0]);
            objSave.setChPNCCheckUnit(spnChPNCCheckUnit.getSelectedItemPosition() == 0 ? "" : spnChPNCCheckUnit.getSelectedItemPosition() == 5 ? "8" : String.valueOf(spnChPNCCheckUnit.getSelectedItemPosition()));
            objSave.setChPNCCheckDur(txtChPNCCheckDur.getText().toString());
            objSave.setChPNCTotal(txtChPNCTotal.getText().toString());
            String[] d_rdogrpChPNCCard = new String[]{"0", "1", "8", "9"};
            objSave.setChPNCCard("");
            for (int i = 0; i < rdogrpChPNCCard.getChildCount(); i++) {
                rb = (RadioButton) rdogrpChPNCCard.getChildAt(i);
                if (rb.isChecked()) objSave.setChPNCCard(d_rdogrpChPNCCard[i]);
            }

            String[] d_rdogrpChPNCPres = new String[]{"0", "1", "8", "9"};
            objSave.setChPNCPres("");
            for (int i = 0; i < rdogrpChPNCPres.getChildCount(); i++) {
                rb = (RadioButton) rdogrpChPNCPres.getChildAt(i);
                if (rb.isChecked()) objSave.setChPNCPres(d_rdogrpChPNCPres[i]);
            }

            objSave.setChPNC42dWA((chkChPNC42dWA.isChecked() ? "1" : (secChPNC42dWA.isShown() ? "2" : "")));
            objSave.setChPNC42dTM((chkChPNC42dTM.isChecked() ? "1" : (secChPNC42dTM.isShown() ? "2" : "")));
            objSave.setChPNC42dRRA((chkChPNC42dRRA.isChecked() ? "1" : (secChPNC42dRRA.isShown() ? "2" : "")));
            objSave.setChPNC42IM((chkChPNC42IM.isChecked() ? "1" : (secChPNC42IM.isShown() ? "2" : "")));
            objSave.setChPNC42CE((chkChPNC42CE.isChecked() ? "1" : (secChPNC42CE.isShown() ? "2" : "")));
            objSave.setChPNC42CDS((chkChPNC42CDS.isChecked() ? "1" : (secChPNC42CDS.isShown() ? "2" : "")));
            objSave.setChPNC42CB((chkChPNC42CB.isChecked() ? "1" : (secChPNC42CB.isShown() ? "2" : "")));
            objSave.setChPNC42OB((chkChPNC42OB.isChecked() ? "1" : (secChPNC42OB.isShown() ? "2" : "")));
            objSave.setChPNC42dOth((chkChPNC42dOth.isChecked() ? "1" : (secChPNC42dOth.isShown() ? "2" : "")));
            objSave.setChPNC42dOthSp(txtChPNC42dOthSp.getText().toString());
            objSave.setChPNC42dDk((chkChPNC42dDk.isChecked() ? "1" : (secChPNC42dDk.isShown() ? "2" : "")));
            objSave.setChPNC42dRR((chkChPNC42dRR.isChecked() ? "1" : (secChPNC42dRR.isShown() ? "2" : "")));

            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);
            if (status.length() == 0) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("res", "");
                setResult(Activity.RESULT_OK, returnIntent);

                Toast.makeText(NBC_PNCNB.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(NBC_PNCNB.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCNB.this, e.getMessage());
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
            if (txtChSl.getText().toString().length() == 0 & secChSl.isShown()) {
                ValidationMsg += "\nRequired field: Child Sl.";
                secChSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoChPNCCkup1.isChecked() & !rdoChPNCCkup2.isChecked() & !rdoChPNCCkup3.isChecked() & !rdoChPNCCkup4.isChecked() & secChPNCCkup.isShown()) {
                ValidationMsg += "\nB4.3 Required field: I want to talk to you about postnatal checkup on your child/name health within 42 days after delivery..";
                secChPNCCkup.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnChPNCCheckUnit.getSelectedItemPosition() == 0 & secChPNCCheckUnit.isShown()) {
                ValidationMsg += "\nRequired field: First check Unit.";
                secChPNCCheckUnit.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtChPNCCheckDur.getText().toString().length() == 0 & secChPNCCheckDur.isShown()) {
                ValidationMsg += "\nRequired field: First check Duration.";
                secChPNCCheckDur.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtChPNCTotal.getText().toString().length() == 0 & secChPNCTotal.isShown()) {
                ValidationMsg += "\nB4.5 Required field: How many times did you receive postnatal care for your child/name after the delivery?   Number of postnatal visits.";
                secChPNCTotal.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secChPNCTotal.isShown() & (Integer.valueOf(txtChPNCTotal.getText().toString().length() == 0 ? "00" : txtChPNCTotal.getText().toString()) < 00 || Integer.valueOf(txtChPNCTotal.getText().toString().length() == 0 ? "30" : txtChPNCTotal.getText().toString()) > 30)) {
                ValidationMsg += "\nB4.5 Value should be between 00 and 30(How many times did you receive postnatal care for your child/name after the delivery?   Number of postnatal visits).";
                secChPNCTotal.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoChPNCCard1.isChecked() & !rdoChPNCCard2.isChecked() & !rdoChPNCCard3.isChecked() & !rdoChPNCCard4.isChecked() & secChPNCCard.isShown()) {
                ValidationMsg += "\nB4.6 Required field: Do you have a postnatal care (PNC) card for the child/name?.";
                secChPNCCard.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoChPNCPres1.isChecked() & !rdoChPNCPres2.isChecked() & !rdoChPNCPres3.isChecked() & !rdoChPNCPres4.isChecked() & secChPNCPres.isShown()) {
                ValidationMsg += "\nB4.7 Required field: Do you have any prescriptions for your postnatal care checkup during this postnatal period for the child/name?.";
                secChPNCPres.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }

            String TotalPncDetails = C.ReturnSingleValue("Select count(*)total from NBC_PNCNBDetail Where MemID = '" + MEMID + "' and HHID = '" + HHID + "' and PGN = '" + PGN + "'  and ChSl = '" + CHSL + "'");
            if (!Global.isNullOrEmpty(TotalPncDetails)){
                if(Integer.parseInt(txtChPNCTotal.getText().toString().length() == 0 ? "0" : txtChPNCTotal.getText().toString()) != Integer.parseInt(TotalPncDetails)){
                    ValidationMsg += "\nB4.5 Your total pnc visit number and total pnc visit record are not same.";
                    secChPNCTotal.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
            secChSl.setBackgroundColor(Color.WHITE);
            secChPNCCkup.setBackgroundColor(Color.WHITE);
            secChPNCCheckUnit.setBackgroundColor(Color.WHITE);
            secChPNCCheckDur.setBackgroundColor(Color.WHITE);
            secChPNCTotal.setBackgroundColor(Color.WHITE);
            secChPNCTotal.setBackgroundColor(Color.WHITE);
            secChPNCCard.setBackgroundColor(Color.WHITE);
            secChPNCPres.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String MEMID, String HHID, String PGN, String ChSl) {
        try {
            RadioButton rb;
            NBC_PNCNB_DataModel d = new NBC_PNCNB_DataModel();
            String SQL = "Select * from " + TableName + "  Where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='" + PGN + "' and ChSl='" + ChSl + "'";
            List<NBC_PNCNB_DataModel> data = d.SelectAll(this, SQL);
            for (NBC_PNCNB_DataModel item : data) {
                txtNBID.setText(item.getNBID());
                txtMemID.setText(item.getMemID());
                txtHHID.setText(item.getHHID());
                txtPGN.setText(item.getPGN());
                txtChSl.setText(item.getChSl());
                String[] d_rdogrpChPNCCkup = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpChPNCCkup.length; i++) {
                    if (String.valueOf(item.getChPNCCkup()).equals(String.valueOf(d_rdogrpChPNCCkup[i]))) {
                        rb = (RadioButton) rdogrpChPNCCkup.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
//                spnChPNCCheckUnit.setSelection(Global.SpinnerItemPositionAnyLength(spnChPNCCheckUnit, String.valueOf(item.getChPNCCheckUnit())));
                spnChPNCCheckUnit.setSelection(Integer.parseInt(item.getChPNCCheckUnit().equals("8") ? "5" : item.getChPNCCheckUnit()));

                txtChPNCCheckDur.setText(item.getChPNCCheckDur());
                txtChPNCTotal.setText(String.valueOf(item.getChPNCTotal()));
                String[] d_rdogrpChPNCCard = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpChPNCCard.length; i++) {
                    if (String.valueOf(item.getChPNCCard()).equals(String.valueOf(d_rdogrpChPNCCard[i]))) {
                        rb = (RadioButton) rdogrpChPNCCard.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpChPNCPres = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpChPNCPres.length; i++) {
                    if (String.valueOf(item.getChPNCPres()).equals(String.valueOf(d_rdogrpChPNCPres[i]))) {
                        rb = (RadioButton) rdogrpChPNCPres.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                if (String.valueOf(item.getChPNC42dWA()).equals("1")) {
                    chkChPNC42dWA.setChecked(true);
                } else if (String.valueOf(item.getChPNC42dWA()).equals("2")) {
                    chkChPNC42dWA.setChecked(false);
                }
                if (String.valueOf(item.getChPNC42dTM()).equals("1")) {
                    chkChPNC42dTM.setChecked(true);
                } else if (String.valueOf(item.getChPNC42dTM()).equals("2")) {
                    chkChPNC42dTM.setChecked(false);
                }
                if (String.valueOf(item.getChPNC42dRRA()).equals("1")) {
                    chkChPNC42dRRA.setChecked(true);
                } else if (String.valueOf(item.getChPNC42dRRA()).equals("2")) {
                    chkChPNC42dRRA.setChecked(false);
                }
                if(String.valueOf(item.getChPNC42IM()).equals("1"))
                {
                    chkChPNC42IM.setChecked(true);
                }
                else if(String.valueOf(item.getChPNC42IM()).equals("2"))
                {
                    chkChPNC42IM.setChecked(false);
                }
                if(String.valueOf(item.getChPNC42CE()).equals("1"))
                {
                    chkChPNC42CE.setChecked(true);
                }
                else if(String.valueOf(item.getChPNC42CE()).equals("2"))
                {
                    chkChPNC42CE.setChecked(false);
                }
                if(String.valueOf(item.getChPNC42CDS()).equals("1"))
                {
                    chkChPNC42CDS.setChecked(true);
                }
                else if(String.valueOf(item.getChPNC42CDS()).equals("2"))
                {
                    chkChPNC42CDS.setChecked(false);
                }
                if(String.valueOf(item.getChPNC42CB()).equals("1"))
                {
                    chkChPNC42CB.setChecked(true);
                }
                else if(String.valueOf(item.getChPNC42CB()).equals("2"))
                {
                    chkChPNC42CB.setChecked(false);
                }
                if(String.valueOf(item.getChPNC42OB()).equals("1"))
                {
                    chkChPNC42OB.setChecked(true);
                }
                else if(String.valueOf(item.getChPNC42OB()).equals("2"))
                {
                    chkChPNC42OB.setChecked(false);
                }
                if (String.valueOf(item.getChPNC42dOth()).equals("1")) {
                    chkChPNC42dOth.setChecked(true);
                } else if (String.valueOf(item.getChPNC42dOth()).equals("2")) {
                    chkChPNC42dOth.setChecked(false);
                }
                txtChPNC42dOthSp.setText(item.getChPNC42dOthSp());
                if (String.valueOf(item.getChPNC42dDk()).equals("1")) {
                    chkChPNC42dDk.setChecked(true);
                } else if (String.valueOf(item.getChPNC42dDk()).equals("2")) {
                    chkChPNC42dDk.setChecked(false);
                }
                if (String.valueOf(item.getChPNC42dRR()).equals("1")) {
                    chkChPNC42dRR.setChecked(true);
                } else if (String.valueOf(item.getChPNC42dRR()).equals("2")) {
                    chkChPNC42dRR.setChecked(false);
                }
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCNB.this, e.getMessage());
            return;
        }
    }

    private void DataSearchPNCChList(String MEMID, String HHID, String PGN, String CHSL) {
        try {

            NBC_PNCNBDetail_DataModel d = new NBC_PNCNBDetail_DataModel();
            String SQL = "Select * from NBC_PNCNBDetail  Where MemID = '" + MEMID + "' and HHID = '" + HHID + "' and PGN = '" + PGN + "'  and ChSl = '" + CHSL + "' and isdelete=2";
            List<NBC_PNCNBDetail_DataModel> data = d.SelectAll(this, SQL);
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
                Connection.MessageBox(NBC_PNCNB.this, ex.getMessage());
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCNB.this, e.getMessage());
            return;
        }
    }


    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
        private List<NBC_PNCNBDetail_DataModel> dataList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout secListRow;
            TextView ChPNCSl;
            TextView ChPNCDate;

            public MyViewHolder(View convertView) {
                super(convertView);
                secListRow = (LinearLayout) convertView.findViewById(R.id.secListRow);
                ChPNCSl = (TextView) convertView.findViewById(R.id.ChPNCSl);
                ChPNCDate = (TextView) convertView.findViewById(R.id.ChPNCDate);
            }
        }

        public DataAdapter(List<NBC_PNCNBDetail_DataModel> datalist) {
            this.dataList = datalist;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.nbc_pncnbdetail_row, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final NBC_PNCNBDetail_DataModel data = dataList.get(position);
            holder.ChPNCSl.setText(String.valueOf(data.getChPNCSl()));
            holder.ChPNCDate.setText(Global.DateConvertDMY(data.getChPNCDate()));

            holder.secListRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(NBC_PNCNB.this, "", "Please Wait . . .", true);
                    new Thread() {
                        public void run() {
                            try {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("NBID", data.getNBID());
                                IDbundle.putString("PGN", data.getPGN());
                                IDbundle.putString("MemID", data.getMemID());
                                IDbundle.putString("HHID", data.getHHID());
                                IDbundle.putString("round", ROUND);
                                IDbundle.putString("ChSl", data.getChSl());
                                IDbundle.putString("isEdit", "2");
                                IDbundle.putString("ChPNCSl", data.getChPNCSl());
                                Intent f1 = new Intent(getApplicationContext(), NBC_PNCNBDetail.class);
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
        DataSearchPNCChList(MEMID, HHID, PGN, CHSL);
    }
}