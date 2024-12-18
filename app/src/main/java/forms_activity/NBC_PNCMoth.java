
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
import java.util.HashMap;
import java.util.List;

import Common.Connection;
import Common.Global;
import Utility.MySharedPreferences;
import forms_datamodel.NBC_ANCDetail_DataModel;
import forms_datamodel.NBC_PNCMothDetail_DataModel;
import forms_datamodel.NBC_PNCMoth_DataModel;

public class NBC_PNCMoth extends AppCompatActivity {
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

    Connection C;
    Global g;
    private List<NBC_PNCMothDetail_DataModel> dataList = new ArrayList<>();
    TextView lblHeading;
    LinearLayout seclbl02;
    View linelbl02;
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
    LinearLayout secHealthCheck;
    View lineHealthCheck;
    TextView VlblHealthCheck;
    RadioGroup rdogrpHealthCheck;
    RadioButton rdoHealthCheck1;
    RadioButton rdoHealthCheck2;
    RadioButton rdoHealthCheck3;
    RadioButton rdoHealthCheck4;
    LinearLayout seclbl01;
    View linelbl01;
    LinearLayout secDeliCheckUnit;
    View lineDeliCheckUnit;
    TextView VlblDeliCheckUnit;
    RadioGroup rdogrpDeliCheckUnit;
    RadioButton rdoDeliCheckUnit1;
    RadioButton rdoDeliCheckUnit2;
    RadioButton rdoDeliCheckUnit3;
    RadioButton rdoDeliCheckUnit4;
    RadioButton rdoDeliCheckUnit5;
    LinearLayout secDeliCheckDur;
    View lineDeliCheckDur;
    TextView VlblDeliCheckDur;
    EditText txtDeliCheckDur;
    LinearLayout secPNCTotal;
    View linePNCTotal;
    TextView VlblPNCTotal;
    EditText txtPNCTotal;
    LinearLayout secPNCCard;
    View linePNCCard;
    TextView VlblPNCCard;
    RadioGroup rdogrpPNCCard;
    RadioButton rdoPNCCard1;
    RadioButton rdoPNCCard2;
    RadioButton rdoPNCCard3;
    RadioButton rdoPNCCard4;
    LinearLayout secPNCPres;
    View linePNCPres;
    TextView VlblPNCPres;
    RadioGroup rdogrpPNCPres;
    RadioButton rdoPNCPres1;
    RadioButton rdoPNCPres2;
    RadioButton rdoPNCPres3;
    RadioButton rdoPNCPres4;
    LinearLayout seclbl07;
    View linelbl07;
    LinearLayout secPNC42dBPM;
    View linePNC42dBPM;
    TextView VlblPNC42dBPM;
    CheckBox chkPNC42dBPM;
    LinearLayout secPNC42dTM;
    View linePNC42dTM;
    TextView VlblPNC42dTM;
    CheckBox chkPNC42dTM;
    LinearLayout secPNC42dAnm;
    View linePNC42dAnm;
    TextView VlblPNC42dAnm;
    CheckBox chkPNC42dAnm;
    LinearLayout secPNC42dBrst;
    View linePNC42dBrst;
    TextView VlblPNC42dBrst;
    CheckBox chkPNC42dBrst;
    LinearLayout secPNC42dPeri;
    View linePNC42dPeri;
    TextView VlblPNC42dPeri;
    CheckBox chkPNC42dPeri;
    LinearLayout secPNC42dOth;
    View linePNC42dOth;
    TextView VlblPNC42dOth;
    CheckBox chkPNC42dOth;
    LinearLayout secPNC42dOthSp;
    View linePNC42dOthSp;
    TextView VlblPNC42dOthSp;
    AutoCompleteTextView txtPNC42dOthSp;
    LinearLayout secPNC42dDk;
    View linePNC42dDk;
    TextView VlblPNC42dDk;
    CheckBox chkPNC42dDk;
    LinearLayout secPNC42dRR;
    View linePNC42dRR;
    TextView VlblPNC42dRR;
    CheckBox chkPNC42dRR;
    LinearLayout secPncList;
    Button btnAdd;
    RecyclerView recycler_view;
    View linePncNoDk;
    private DataAdapter mAdapter;

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
            setContentView(R.layout.nbc_pncmoth);
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
            PGN = IDbundle.getString("PGN");

            TableName = "NBC_PNCMoth";
            MODULEID = 44;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String TotalPncDetails = C.ReturnSingleValue("Select count(*)total from NBC_PNCMothDetail where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='"+PGN+"'");
                    if (!Global.isNullOrEmpty(TotalPncDetails)){
                        if(Integer.parseInt(txtPNCTotal.getText().toString().length() == 0 ? "0" : txtPNCTotal.getText().toString()) != Integer.parseInt(TotalPncDetails)){
                            Connection.MessageBox(NBC_PNCMoth.this, "Your total pnc visit number and total pnc visit record are not same.");
                            return;
                        }
                    }
                    AlertDialog.Builder adb = new AlertDialog.Builder(NBC_PNCMoth.this);
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
            Connection.LocalizeLanguage(NBC_PNCMoth.this, MODULEID, LANGUAGEID);


            //Hide all skip variables
            seclbl01.setVisibility(View.GONE);
            linelbl01.setVisibility(View.GONE);
            secDeliCheckUnit.setVisibility(View.GONE);
            lineDeliCheckUnit.setVisibility(View.GONE);
            secDeliCheckDur.setVisibility(View.GONE);
            lineDeliCheckDur.setVisibility(View.GONE);
            secPNCTotal.setVisibility(View.GONE);
            linePNCTotal.setVisibility(View.GONE);
            secPNCCard.setVisibility(View.GONE);
            linePNCCard.setVisibility(View.GONE);
            secPNCPres.setVisibility(View.GONE);
            linePNCPres.setVisibility(View.GONE);
            seclbl07.setVisibility(View.GONE);
            linelbl07.setVisibility(View.GONE);
            secPNC42dBPM.setVisibility(View.GONE);
            linePNC42dBPM.setVisibility(View.GONE);
            secPNC42dTM.setVisibility(View.GONE);
            linePNC42dTM.setVisibility(View.GONE);
            secPNC42dAnm.setVisibility(View.GONE);
            linePNC42dAnm.setVisibility(View.GONE);
            secPNC42dBrst.setVisibility(View.GONE);
            linePNC42dBrst.setVisibility(View.GONE);
            secPNC42dPeri.setVisibility(View.GONE);
            linePNC42dPeri.setVisibility(View.GONE);
            secPNC42dOth.setVisibility(View.GONE);
            linePNC42dOth.setVisibility(View.GONE);
            secPNC42dOthSp.setVisibility(View.GONE);
            linePNC42dOthSp.setVisibility(View.GONE);
            secPNC42dDk.setVisibility(View.GONE);
            linePNC42dDk.setVisibility(View.GONE);
            secPNC42dRR.setVisibility(View.GONE);
            linePNC42dRR.setVisibility(View.GONE);
            seclbl01.setVisibility(View.GONE);
            linelbl01.setVisibility(View.GONE);
            secDeliCheckUnit.setVisibility(View.GONE);
            lineDeliCheckUnit.setVisibility(View.GONE);
            secDeliCheckDur.setVisibility(View.GONE);
            lineDeliCheckDur.setVisibility(View.GONE);
            secPNCTotal.setVisibility(View.GONE);
            linePNCTotal.setVisibility(View.GONE);
            secPNCCard.setVisibility(View.GONE);
            linePNCCard.setVisibility(View.GONE);
            secPNCPres.setVisibility(View.GONE);
            linePNCPres.setVisibility(View.GONE);
            seclbl07.setVisibility(View.GONE);
            linelbl07.setVisibility(View.GONE);
            secPNC42dBPM.setVisibility(View.GONE);
            linePNC42dBPM.setVisibility(View.GONE);
            secPNC42dTM.setVisibility(View.GONE);
            linePNC42dTM.setVisibility(View.GONE);
            secPNC42dAnm.setVisibility(View.GONE);
            linePNC42dAnm.setVisibility(View.GONE);
            secPNC42dBrst.setVisibility(View.GONE);
            linePNC42dBrst.setVisibility(View.GONE);
            secPNC42dPeri.setVisibility(View.GONE);
            linePNC42dPeri.setVisibility(View.GONE);
            secPNC42dOth.setVisibility(View.GONE);
            linePNC42dOth.setVisibility(View.GONE);
            secPNC42dOthSp.setVisibility(View.GONE);
            linePNC42dOthSp.setVisibility(View.GONE);
            secPNC42dDk.setVisibility(View.GONE);
            linePNC42dDk.setVisibility(View.GONE);
            secPNC42dRR.setVisibility(View.GONE);
            linePNC42dRR.setVisibility(View.GONE);
            seclbl01.setVisibility(View.GONE);
            linelbl01.setVisibility(View.GONE);
            secDeliCheckUnit.setVisibility(View.GONE);
            lineDeliCheckUnit.setVisibility(View.GONE);
            secDeliCheckDur.setVisibility(View.GONE);
            lineDeliCheckDur.setVisibility(View.GONE);
            secPNCTotal.setVisibility(View.GONE);
            linePNCTotal.setVisibility(View.GONE);
            secPNCCard.setVisibility(View.GONE);
            linePNCCard.setVisibility(View.GONE);
            secPNCPres.setVisibility(View.GONE);
            linePNCPres.setVisibility(View.GONE);
            seclbl07.setVisibility(View.GONE);
            linelbl07.setVisibility(View.GONE);
            secPNC42dBPM.setVisibility(View.GONE);
            linePNC42dBPM.setVisibility(View.GONE);
            secPNC42dTM.setVisibility(View.GONE);
            linePNC42dTM.setVisibility(View.GONE);
            secPNC42dAnm.setVisibility(View.GONE);
            linePNC42dAnm.setVisibility(View.GONE);
            secPNC42dBrst.setVisibility(View.GONE);
            linePNC42dBrst.setVisibility(View.GONE);
            secPNC42dPeri.setVisibility(View.GONE);
            linePNC42dPeri.setVisibility(View.GONE);
            secPNC42dOth.setVisibility(View.GONE);
            linePNC42dOth.setVisibility(View.GONE);
            secPNC42dOthSp.setVisibility(View.GONE);
            linePNC42dOthSp.setVisibility(View.GONE);
            secPNC42dDk.setVisibility(View.GONE);
            linePNC42dDk.setVisibility(View.GONE);
            secPNC42dRR.setVisibility(View.GONE);
            linePNC42dRR.setVisibility(View.GONE);
            secDeliCheckDur.setVisibility(View.GONE);
            lineDeliCheckDur.setVisibility(View.GONE);
            secPNC42dOthSp.setVisibility(View.GONE);
            linePNC42dOthSp.setVisibility(View.GONE);
            secPncList.setVisibility(View.GONE);
            linePncNoDk.setVisibility(View.GONE);


            DataSearch(MEMID, HHID, PGN);

            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });

            mAdapter = new DataAdapter(dataList);
            DataSearchPNCList(MEMID, HHID, PGN);
            recycler_view.setItemViewCacheSize(20);
            recycler_view.setDrawingCacheEnabled(true);
            recycler_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            recycler_view.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter(mAdapter);

        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCMoth.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            seclbl02 = (LinearLayout) findViewById(R.id.seclbl02);
            linelbl02 = (View) findViewById(R.id.linelbl02);
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
            txtNBID.setEnabled(false);
            secHHID = (LinearLayout) findViewById(R.id.secHHID);
            lineHHID = (View) findViewById(R.id.lineHHID);
            VlblHHID = (TextView) findViewById(R.id.VlblHHID);
            txtHHID = (EditText) findViewById(R.id.txtHHID);
            txtHHID.setText(HHID);
            txtNBID.setEnabled(false);
            secPGN = (LinearLayout) findViewById(R.id.secPGN);
            linePGN = (View) findViewById(R.id.linePGN);
            VlblPGN = (TextView) findViewById(R.id.VlblPGN);
            txtPGN = (EditText) findViewById(R.id.txtPGN);
            txtPGN.setText(PGN);
            txtPGN.setEnabled(false);

            secPncList = findViewById(R.id.secPncList);
            btnAdd = findViewById(R.id.btnAdd);
            recycler_view = findViewById(R.id.recycler_view);
            linePncNoDk = findViewById(R.id.linePncNoDk);

            secHealthCheck = (LinearLayout) findViewById(R.id.secHealthCheck);
            lineHealthCheck = (View) findViewById(R.id.lineHealthCheck);
            VlblHealthCheck = (TextView) findViewById(R.id.VlblHealthCheck);
            rdogrpHealthCheck = (RadioGroup) findViewById(R.id.rdogrpHealthCheck);
            rdoHealthCheck1 = (RadioButton) findViewById(R.id.rdoHealthCheck1);
            rdoHealthCheck2 = (RadioButton) findViewById(R.id.rdoHealthCheck2);
            rdoHealthCheck3 = (RadioButton) findViewById(R.id.rdoHealthCheck3);
            rdoHealthCheck4 = (RadioButton) findViewById(R.id.rdoHealthCheck4);
            rdogrpHealthCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpHealthCheck = new String[]{"0", "1", "8", "9"};
                    for (int i = 0; i < rdogrpHealthCheck.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpHealthCheck.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpHealthCheck[i];
                    }

                    if (rbData.equalsIgnoreCase("1")) {
                        seclbl01.setVisibility(View.VISIBLE);
                        linelbl01.setVisibility(View.VISIBLE);
                        secDeliCheckUnit.setVisibility(View.VISIBLE);
                        lineDeliCheckUnit.setVisibility(View.VISIBLE);
                        secDeliCheckDur.setVisibility(View.VISIBLE);
                        lineDeliCheckDur.setVisibility(View.VISIBLE);
                        secPNCTotal.setVisibility(View.VISIBLE);
                        linePNCTotal.setVisibility(View.VISIBLE);
                        secPNCCard.setVisibility(View.VISIBLE);
                        linePNCCard.setVisibility(View.VISIBLE);
                        secPNCPres.setVisibility(View.VISIBLE);
                        linePNCPres.setVisibility(View.VISIBLE);
                        seclbl07.setVisibility(View.VISIBLE);
                        linelbl07.setVisibility(View.VISIBLE);
                        secPNC42dBPM.setVisibility(View.VISIBLE);
                        linePNC42dBPM.setVisibility(View.VISIBLE);
                        secPNC42dTM.setVisibility(View.VISIBLE);
                        linePNC42dTM.setVisibility(View.VISIBLE);
                        secPNC42dAnm.setVisibility(View.VISIBLE);
                        linePNC42dAnm.setVisibility(View.VISIBLE);
                        secPNC42dBrst.setVisibility(View.VISIBLE);
                        linePNC42dBrst.setVisibility(View.VISIBLE);
                        secPNC42dPeri.setVisibility(View.VISIBLE);
                        linePNC42dPeri.setVisibility(View.VISIBLE);
                        secPNC42dOth.setVisibility(View.VISIBLE);
                        linePNC42dOth.setVisibility(View.VISIBLE);
                        secPNC42dDk.setVisibility(View.VISIBLE);
                        linePNC42dDk.setVisibility(View.VISIBLE);
                        secPNC42dRR.setVisibility(View.VISIBLE);
                        linePNC42dRR.setVisibility(View.VISIBLE);
                    } else {
                        seclbl01.setVisibility(View.GONE);
                        linelbl01.setVisibility(View.GONE);
                        secDeliCheckUnit.setVisibility(View.GONE);
                        lineDeliCheckUnit.setVisibility(View.GONE);
                        rdogrpDeliCheckUnit.clearCheck();
                        secDeliCheckDur.setVisibility(View.GONE);
                        lineDeliCheckDur.setVisibility(View.GONE);
                        txtDeliCheckDur.setText("");
                        secPNCTotal.setVisibility(View.GONE);
                        linePNCTotal.setVisibility(View.GONE);
                        txtPNCTotal.setText("");
                        secPNCCard.setVisibility(View.GONE);
                        linePNCCard.setVisibility(View.GONE);
                        rdogrpPNCCard.clearCheck();
                        secPNCPres.setVisibility(View.GONE);
                        linePNCPres.setVisibility(View.GONE);
                        rdogrpPNCPres.clearCheck();
                        seclbl07.setVisibility(View.GONE);
                        linelbl07.setVisibility(View.GONE);
                        secPNC42dBPM.setVisibility(View.GONE);
                        linePNC42dBPM.setVisibility(View.GONE);
                        chkPNC42dBPM.setChecked(false);
                        secPNC42dTM.setVisibility(View.GONE);
                        linePNC42dTM.setVisibility(View.GONE);
                        chkPNC42dTM.setChecked(false);
                        secPNC42dAnm.setVisibility(View.GONE);
                        linePNC42dAnm.setVisibility(View.GONE);
                        chkPNC42dAnm.setChecked(false);
                        secPNC42dBrst.setVisibility(View.GONE);
                        linePNC42dBrst.setVisibility(View.GONE);
                        chkPNC42dBrst.setChecked(false);
                        secPNC42dPeri.setVisibility(View.GONE);
                        linePNC42dPeri.setVisibility(View.GONE);
                        chkPNC42dPeri.setChecked(false);
                        secPNC42dOth.setVisibility(View.GONE);
                        linePNC42dOth.setVisibility(View.GONE);
                        chkPNC42dOth.setChecked(false);
                        secPNC42dOthSp.setVisibility(View.GONE);
                        linePNC42dOthSp.setVisibility(View.GONE);
                        txtPNC42dOthSp.setText("");
                        secPNC42dDk.setVisibility(View.GONE);
                        linePNC42dDk.setVisibility(View.GONE);
                        chkPNC42dDk.setChecked(false);
                        secPNC42dRR.setVisibility(View.GONE);
                        linePNC42dRR.setVisibility(View.GONE);
                        chkPNC42dRR.setChecked(false);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            seclbl01 = (LinearLayout) findViewById(R.id.seclbl01);
            linelbl01 = (View) findViewById(R.id.linelbl01);
            secDeliCheckUnit = (LinearLayout) findViewById(R.id.secDeliCheckUnit);
            lineDeliCheckUnit = (View) findViewById(R.id.lineDeliCheckUnit);
            VlblDeliCheckUnit = (TextView) findViewById(R.id.VlblDeliCheckUnit);
            rdogrpDeliCheckUnit = (RadioGroup) findViewById(R.id.rdogrpDeliCheckUnit);
            rdoDeliCheckUnit1 = (RadioButton) findViewById(R.id.rdoDeliCheckUnit1);
            rdoDeliCheckUnit2 = (RadioButton) findViewById(R.id.rdoDeliCheckUnit2);
            rdoDeliCheckUnit3 = (RadioButton) findViewById(R.id.rdoDeliCheckUnit3);
            rdoDeliCheckUnit4 = (RadioButton) findViewById(R.id.rdoDeliCheckUnit4);
            rdoDeliCheckUnit5 = (RadioButton) findViewById(R.id.rdoDeliCheckUnit5);
            rdogrpDeliCheckUnit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpDeliCheckUnit = new String[]{"1", "2", "3", "4", "8"};
                    for (int i = 0; i < rdogrpDeliCheckUnit.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpDeliCheckUnit.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpDeliCheckUnit[i];
                    }

                    if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2") || rbData.equalsIgnoreCase("3") || rbData.equalsIgnoreCase("4")) {
                        secDeliCheckDur.setVisibility(View.VISIBLE);
                        lineDeliCheckDur.setVisibility(View.VISIBLE);
                    } else {
                        secDeliCheckDur.setVisibility(View.GONE);
                        lineDeliCheckDur.setVisibility(View.GONE);
                        txtDeliCheckDur.setText("");
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secDeliCheckDur = (LinearLayout) findViewById(R.id.secDeliCheckDur);
            lineDeliCheckDur = (View) findViewById(R.id.lineDeliCheckDur);
            VlblDeliCheckDur = (TextView) findViewById(R.id.VlblDeliCheckDur);
            txtDeliCheckDur = (EditText) findViewById(R.id.txtDeliCheckDur);
            secPNCTotal = (LinearLayout) findViewById(R.id.secPNCTotal);
            linePNCTotal = (View) findViewById(R.id.linePNCTotal);
            VlblPNCTotal = (TextView) findViewById(R.id.VlblPNCTotal);
            txtPNCTotal = (EditText) findViewById(R.id.txtPNCTotal);
            secPNCCard = (LinearLayout) findViewById(R.id.secPNCCard);
            linePNCCard = (View) findViewById(R.id.linePNCCard);
            VlblPNCCard = (TextView) findViewById(R.id.VlblPNCCard);
            rdogrpPNCCard = (RadioGroup) findViewById(R.id.rdogrpPNCCard);
            rdoPNCCard1 = (RadioButton) findViewById(R.id.rdoPNCCard1);
            rdoPNCCard2 = (RadioButton) findViewById(R.id.rdoPNCCard2);
            rdoPNCCard3 = (RadioButton) findViewById(R.id.rdoPNCCard3);
            rdoPNCCard4 = (RadioButton) findViewById(R.id.rdoPNCCard4);
            secPNCPres = (LinearLayout) findViewById(R.id.secPNCPres);
            linePNCPres = (View) findViewById(R.id.linePNCPres);
            VlblPNCPres = (TextView) findViewById(R.id.VlblPNCPres);
            rdogrpPNCPres = (RadioGroup) findViewById(R.id.rdogrpPNCPres);
            rdoPNCPres1 = (RadioButton) findViewById(R.id.rdoPNCPres1);
            rdoPNCPres2 = (RadioButton) findViewById(R.id.rdoPNCPres2);
            rdoPNCPres3 = (RadioButton) findViewById(R.id.rdoPNCPres3);
            rdoPNCPres4 = (RadioButton) findViewById(R.id.rdoPNCPres4);
            seclbl07 = (LinearLayout) findViewById(R.id.seclbl07);
            linelbl07 = (View) findViewById(R.id.linelbl07);
            secPNC42dBPM = (LinearLayout) findViewById(R.id.secPNC42dBPM);
            linePNC42dBPM = (View) findViewById(R.id.linePNC42dBPM);
            VlblPNC42dBPM = (TextView) findViewById(R.id.VlblPNC42dBPM);
            chkPNC42dBPM = (CheckBox) findViewById(R.id.chkPNC42dBPM);
            chkPNC42dBPM.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkPNC42dRR.setChecked(false);
                    chkPNC42dDk.setChecked(false);
                }
            });
            secPNC42dTM = (LinearLayout) findViewById(R.id.secPNC42dTM);
            linePNC42dTM = (View) findViewById(R.id.linePNC42dTM);
            VlblPNC42dTM = (TextView) findViewById(R.id.VlblPNC42dTM);
            chkPNC42dTM = (CheckBox) findViewById(R.id.chkPNC42dTM);
            chkPNC42dTM.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkPNC42dRR.setChecked(false);
                    chkPNC42dDk.setChecked(false);
                }
            });
            secPNC42dAnm = (LinearLayout) findViewById(R.id.secPNC42dAnm);
            linePNC42dAnm = (View) findViewById(R.id.linePNC42dAnm);
            VlblPNC42dAnm = (TextView) findViewById(R.id.VlblPNC42dAnm);
            chkPNC42dAnm = (CheckBox) findViewById(R.id.chkPNC42dAnm);
            chkPNC42dAnm.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkPNC42dRR.setChecked(false);
                    chkPNC42dDk.setChecked(false);
                }
            });
            secPNC42dBrst = (LinearLayout) findViewById(R.id.secPNC42dBrst);
            linePNC42dBrst = (View) findViewById(R.id.linePNC42dBrst);
            VlblPNC42dBrst = (TextView) findViewById(R.id.VlblPNC42dBrst);
            chkPNC42dBrst = (CheckBox) findViewById(R.id.chkPNC42dBrst);
            chkPNC42dBrst.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkPNC42dRR.setChecked(false);
                    chkPNC42dDk.setChecked(false);
                }
            });
            secPNC42dPeri = (LinearLayout) findViewById(R.id.secPNC42dPeri);
            linePNC42dPeri = (View) findViewById(R.id.linePNC42dPeri);
            VlblPNC42dPeri = (TextView) findViewById(R.id.VlblPNC42dPeri);
            chkPNC42dPeri = (CheckBox) findViewById(R.id.chkPNC42dPeri);
            chkPNC42dPeri.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkPNC42dRR.setChecked(false);
                    chkPNC42dDk.setChecked(false);
                }
            });
            secPNC42dOth = (LinearLayout) findViewById(R.id.secPNC42dOth);
            linePNC42dOth = (View) findViewById(R.id.linePNC42dOth);
            VlblPNC42dOth = (TextView) findViewById(R.id.VlblPNC42dOth);
            chkPNC42dOth = (CheckBox) findViewById(R.id.chkPNC42dOth);
            chkPNC42dOth.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkPNC42dRR.setChecked(false);
                    chkPNC42dDk.setChecked(false);
                }
            });
            chkPNC42dOth.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkPNC42dRR.setChecked(false);
                    chkPNC42dDk.setChecked(false);
                    secPNC42dOthSp.setVisibility(View.VISIBLE);
                    linePNC42dOthSp.setVisibility(View.VISIBLE);
                } else {
                    secPNC42dOthSp.setVisibility(View.GONE);
                    linePNC42dOthSp.setVisibility(View.GONE);
                    txtPNC42dOthSp.setText("");
                }
            });
            secPNC42dOthSp = (LinearLayout) findViewById(R.id.secPNC42dOthSp);
            linePNC42dOthSp = (View) findViewById(R.id.linePNC42dOthSp);
            VlblPNC42dOthSp = (TextView) findViewById(R.id.VlblPNC42dOthSp);
            txtPNC42dOthSp = (AutoCompleteTextView) findViewById(R.id.txtPNC42dOthSp);
            C.setupAutoComplete(TableName,txtPNC42dOthSp,"PNC42dOthSp"); //setup autocomplete view by event

            secPNC42dDk = (LinearLayout) findViewById(R.id.secPNC42dDk);
            linePNC42dDk = (View) findViewById(R.id.linePNC42dDk);
            VlblPNC42dDk = (TextView) findViewById(R.id.VlblPNC42dDk);
            chkPNC42dDk = (CheckBox) findViewById(R.id.chkPNC42dDk);
            chkPNC42dDk.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    chkPNC42dRR.setChecked(false);
                    chkPNC42dBPM.setChecked(false);
                    chkPNC42dTM.setChecked(false);
                    chkPNC42dAnm.setChecked(false);
                    chkPNC42dBrst.setChecked(false);
                    chkPNC42dPeri.setChecked(false);
                    chkPNC42dOth.setChecked(false);
                    txtPNC42dOthSp.setText("");
                }
            });
            secPNC42dRR = (LinearLayout) findViewById(R.id.secPNC42dRR);
            linePNC42dRR = (View) findViewById(R.id.linePNC42dRR);
            VlblPNC42dRR = (TextView) findViewById(R.id.VlblPNC42dRR);
            chkPNC42dRR = (CheckBox) findViewById(R.id.chkPNC42dRR);
            chkPNC42dRR.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    chkPNC42dDk.setChecked(false);
                    chkPNC42dBPM.setChecked(false);
                    chkPNC42dTM.setChecked(false);
                    chkPNC42dAnm.setChecked(false);
                    chkPNC42dBrst.setChecked(false);
                    chkPNC42dPeri.setChecked(false);
                    chkPNC42dOth.setChecked(false);
                    txtPNC42dOthSp.setText("");
                }
            });

            txtPNCTotal.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(txtPNCTotal.getText().toString().length() > 0 && !txtPNCTotal.getText().toString().equals("0")){
                        secPncList.setVisibility(View.VISIBLE);
                        linePncNoDk.setVisibility(View.VISIBLE);
                    }
                    else{
                        secPncList.setVisibility(View.GONE);
                        linePncNoDk.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            btnAdd.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    String TotalAncDetails = C.ReturnSingleValue("Select count(*)total from NBC_PNCMothDetail where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='"+PGN+"'");
                    if (!Global.isNullOrEmpty(TotalAncDetails)){
                        if(Integer.parseInt(txtPNCTotal.getText().toString().length() == 0 ? "0" : txtPNCTotal.getText().toString()) <= Integer.parseInt(TotalAncDetails)){
                            Connection.MessageBox(NBC_PNCMoth.this, "You can't enter more record where as total number of pnc is " + txtPNCTotal.getText().toString() + ".");
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

                }});

        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCMoth.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(NBC_PNCMoth.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            NBC_PNCMoth_DataModel objSave = new NBC_PNCMoth_DataModel();
            objSave.setNBID(txtNBID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setPGN(txtPGN.getText().toString());
            String[] d_rdogrpHealthCheck = new String[]{"0", "1", "8", "9"};
            objSave.setHealthCheck("");
            for (int i = 0; i < rdogrpHealthCheck.getChildCount(); i++) {
                rb = (RadioButton) rdogrpHealthCheck.getChildAt(i);
                if (rb.isChecked()) objSave.setHealthCheck(d_rdogrpHealthCheck[i]);
            }

            String[] d_rdogrpDeliCheckUnit = new String[]{"1", "2", "3", "4", "8"};
            objSave.setDeliCheckUnit("");
            for (int i = 0; i < rdogrpDeliCheckUnit.getChildCount(); i++) {
                rb = (RadioButton) rdogrpDeliCheckUnit.getChildAt(i);
                if (rb.isChecked()) objSave.setDeliCheckUnit(d_rdogrpDeliCheckUnit[i]);
            }

            objSave.setDeliCheckDur(txtDeliCheckDur.getText().toString());
            objSave.setPNCTotal(txtPNCTotal.getText().toString());
            String[] d_rdogrpPNCCard = new String[]{"0", "1", "8", "9"};
            objSave.setPNCCard("");
            for (int i = 0; i < rdogrpPNCCard.getChildCount(); i++) {
                rb = (RadioButton) rdogrpPNCCard.getChildAt(i);
                if (rb.isChecked()) objSave.setPNCCard(d_rdogrpPNCCard[i]);
            }

            String[] d_rdogrpPNCPres = new String[]{"0", "1", "8", "9"};
            objSave.setPNCPres("");
            for (int i = 0; i < rdogrpPNCPres.getChildCount(); i++) {
                rb = (RadioButton) rdogrpPNCPres.getChildAt(i);
                if (rb.isChecked()) objSave.setPNCPres(d_rdogrpPNCPres[i]);
            }

            objSave.setPNC42dBPM((chkPNC42dBPM.isChecked() ? "1" : (secPNC42dBPM.isShown() ? "2" : "")));
            objSave.setPNC42dTM((chkPNC42dTM.isChecked() ? "1" : (secPNC42dTM.isShown() ? "2" : "")));
            objSave.setPNC42dAnm((chkPNC42dAnm.isChecked() ? "1" : (secPNC42dAnm.isShown() ? "2" : "")));
            objSave.setPNC42dBrst((chkPNC42dBrst.isChecked() ? "1" : (secPNC42dBrst.isShown() ? "2" : "")));
            objSave.setPNC42dPeri((chkPNC42dPeri.isChecked() ? "1" : (secPNC42dPeri.isShown() ? "2" : "")));
            objSave.setPNC42dOth((chkPNC42dOth.isChecked() ? "1" : (secPNC42dOth.isShown() ? "2" : "")));
            objSave.setPNC42dOthSp(txtPNC42dOthSp.getText().toString());
            objSave.setPNC42dDk((chkPNC42dDk.isChecked() ? "1" : (secPNC42dDk.isShown() ? "2" : "")));
            objSave.setPNC42dRR((chkPNC42dRR.isChecked() ? "1" : (secPNC42dRR.isShown() ? "2" : "")));
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);
            if (status.length() == 0) {
                Toast.makeText(NBC_PNCMoth.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(NBC_PNCMoth.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCMoth.this, e.getMessage());
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
            if (!rdoHealthCheck1.isChecked() & !rdoHealthCheck2.isChecked() & !rdoHealthCheck3.isChecked() & !rdoHealthCheck4.isChecked() & secHealthCheck.isShown()) {
                ValidationMsg += "\nB3.3 Required field: I want to talk to you about postnatal checkup on your health within 42 days after delivery Did anyone check on your health within 42 days after delivery? Or did you go anywhere for checking your health within 42 days after delivery?.";
                secHealthCheck.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoDeliCheckUnit1.isChecked() & !rdoDeliCheckUnit2.isChecked() & !rdoDeliCheckUnit3.isChecked() & !rdoDeliCheckUnit4.isChecked() & !rdoDeliCheckUnit5.isChecked() & secDeliCheckUnit.isShown()) {
                ValidationMsg += "\nRequired field: First check Unit.";
                secDeliCheckUnit.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtDeliCheckDur.getText().toString().length() == 0 & secDeliCheckDur.isShown()) {
                ValidationMsg += "\nRequired field: First check Duration.";
                secDeliCheckDur.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtPNCTotal.getText().toString().length() == 0 & secPNCTotal.isShown()) {
                ValidationMsg += "\nB3.5 Required field: How many times did you receive postnatal care during this pregnancy?.";
                secPNCTotal.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secPNCTotal.isShown() & (Integer.valueOf(txtPNCTotal.getText().toString().length() == 0 ? "00" : txtPNCTotal.getText().toString()) < 00 || Integer.valueOf(txtPNCTotal.getText().toString().length() == 0 ? "30" : txtPNCTotal.getText().toString()) > 30)) {
                ValidationMsg += "\nB3.5 Value should be between 00 and 30(How many times did you receive postnatal care during this pregnancy?).";
                secPNCTotal.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoPNCCard1.isChecked() & !rdoPNCCard2.isChecked() & !rdoPNCCard3.isChecked() & !rdoPNCCard4.isChecked() & secPNCCard.isShown()) {
                ValidationMsg += "\nB3.6 Required field: Do you have a postnatal care (PNC) card?.";
                secPNCCard.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoPNCPres1.isChecked() & !rdoPNCPres2.isChecked() & !rdoPNCPres3.isChecked() & !rdoPNCPres4.isChecked() & secPNCPres.isShown()) {
                ValidationMsg += "\nB3.7 Required field: Do you have any prescriptions for your postnatal care checkup during this postnatal period?.";
                secPNCPres.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtPNC42dOthSp.getText().toString().length() == 0 & secPNC42dOthSp.isShown()) {
                ValidationMsg += "\nRequired field: Other, specify.";
                secPNC42dOthSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }

            //PNC Visit Check
            String TotalPncDetails = C.ReturnSingleValue("Select count(*)total from NBC_PNCMothDetail where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='"+PGN+"'");
            if (!Global.isNullOrEmpty(TotalPncDetails)){
                if(Integer.parseInt(txtPNCTotal.getText().toString().length() == 0 ? "0" : txtPNCTotal.getText().toString()) != Integer.parseInt(TotalPncDetails)){
                    ValidationMsg += "\nB3.5 Your total pnc visit number and total pnc visit record are not same.";
                    secPNCTotal.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));

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
            secHealthCheck.setBackgroundColor(Color.WHITE);
            secDeliCheckUnit.setBackgroundColor(Color.WHITE);
            secDeliCheckDur.setBackgroundColor(Color.WHITE);
            secPNCTotal.setBackgroundColor(Color.WHITE);
            secPNCTotal.setBackgroundColor(Color.WHITE);
            secPNCCard.setBackgroundColor(Color.WHITE);
            secPNCPres.setBackgroundColor(Color.WHITE);
            secPNC42dOthSp.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String MEMID, String HHID, String PGN) {
        try {
            RadioButton rb;
            NBC_PNCMoth_DataModel d = new NBC_PNCMoth_DataModel();
            String SQL = "Select * from " + TableName + "  Where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='" + PGN + "'";
            List<NBC_PNCMoth_DataModel> data = d.SelectAll(this, SQL);
            for (NBC_PNCMoth_DataModel item : data) {
                if (item.getPNCTotal().toString().length() > 0) {

                }
                txtNBID.setText(item.getNBID());
                txtMemID.setText(item.getMemID());
                txtHHID.setText(item.getHHID());
                txtPGN.setText(item.getPGN());
                String[] d_rdogrpHealthCheck = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpHealthCheck.length; i++) {
                    if (String.valueOf(item.getHealthCheck()).equals(String.valueOf(d_rdogrpHealthCheck[i]))) {
                        rb = (RadioButton) rdogrpHealthCheck.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpDeliCheckUnit = new String[]{"1", "2", "3", "4", "8"};
                for (int i = 0; i < d_rdogrpDeliCheckUnit.length; i++) {
                    if (String.valueOf(item.getDeliCheckUnit()).equals(String.valueOf(d_rdogrpDeliCheckUnit[i]))) {
                        rb = (RadioButton) rdogrpDeliCheckUnit.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtDeliCheckDur.setText(item.getDeliCheckDur());
                txtPNCTotal.setText(String.valueOf(item.getPNCTotal()));
                String[] d_rdogrpPNCCard = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpPNCCard.length; i++) {
                    if (String.valueOf(item.getPNCCard()).equals(String.valueOf(d_rdogrpPNCCard[i]))) {
                        rb = (RadioButton) rdogrpPNCCard.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpPNCPres = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpPNCPres.length; i++) {
                    if (String.valueOf(item.getPNCPres()).equals(String.valueOf(d_rdogrpPNCPres[i]))) {
                        rb = (RadioButton) rdogrpPNCPres.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                if (String.valueOf(item.getPNC42dBPM()).equals("1")) {
                    chkPNC42dBPM.setChecked(true);
                } else if (String.valueOf(item.getPNC42dBPM()).equals("2")) {
                    chkPNC42dBPM.setChecked(false);
                }
                if (String.valueOf(item.getPNC42dTM()).equals("1")) {
                    chkPNC42dTM.setChecked(true);
                } else if (String.valueOf(item.getPNC42dTM()).equals("2")) {
                    chkPNC42dTM.setChecked(false);
                }
                if (String.valueOf(item.getPNC42dAnm()).equals("1")) {
                    chkPNC42dAnm.setChecked(true);
                } else if (String.valueOf(item.getPNC42dAnm()).equals("2")) {
                    chkPNC42dAnm.setChecked(false);
                }
                if (String.valueOf(item.getPNC42dBrst()).equals("1")) {
                    chkPNC42dBrst.setChecked(true);
                } else if (String.valueOf(item.getPNC42dBrst()).equals("2")) {
                    chkPNC42dBrst.setChecked(false);
                }
                if (String.valueOf(item.getPNC42dPeri()).equals("1")) {
                    chkPNC42dPeri.setChecked(true);
                } else if (String.valueOf(item.getPNC42dPeri()).equals("2")) {
                    chkPNC42dPeri.setChecked(false);
                }
                if (String.valueOf(item.getPNC42dOth()).equals("1")) {
                    chkPNC42dOth.setChecked(true);
                } else if (String.valueOf(item.getPNC42dOth()).equals("2")) {
                    chkPNC42dOth.setChecked(false);
                }
                txtPNC42dOthSp.setText(item.getPNC42dOthSp());
                if (String.valueOf(item.getPNC42dDk()).equals("1")) {
                    chkPNC42dDk.setChecked(true);
                } else if (String.valueOf(item.getPNC42dDk()).equals("2")) {
                    chkPNC42dDk.setChecked(false);
                }
                if (String.valueOf(item.getPNC42dRR()).equals("1")) {
                    chkPNC42dRR.setChecked(true);
                } else if (String.valueOf(item.getPNC42dRR()).equals("2")) {
                    chkPNC42dRR.setChecked(false);
                }
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCMoth.this, e.getMessage());
            return;
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
        DataSearchPNCList(MEMID, HHID, PGN);
    }

    private void DataSearchPNCList(String MEMID, String HHID, String PGN)
    {
        try
        {

            NBC_PNCMothDetail_DataModel d = new NBC_PNCMothDetail_DataModel();
            String SQL = "Select * from NBC_PNCMothDetail Where MemID = '"+MEMID+"' and HHID = '"+HHID+"' and PGN = '"+PGN+"' and isdelete=2";
            List<NBC_PNCMothDetail_DataModel> data = d.SelectAll(this, SQL);
            dataList.clear();

            dataList.addAll(data);
            if (dataList != null && !dataList.isEmpty())
            {
                recycler_view.setVisibility(View.VISIBLE);
            }
            else
            {
                recycler_view.setVisibility(View.GONE);
            }
            try {
                mAdapter.notifyDataSetChanged();
            }catch ( Exception ex){
                Connection.MessageBox(NBC_PNCMoth.this,ex.getMessage());
            }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(NBC_PNCMoth.this, e.getMessage());
            return;
        }
    }

    public class DataAdapter extends RecyclerView.Adapter<NBC_PNCMoth.DataAdapter.MyViewHolder> {
        private List<NBC_PNCMothDetail_DataModel> dataList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout secListRow;
            TextView PNCSl;
            TextView PNCDate;
            public MyViewHolder(View convertView) {
                super(convertView);
                secListRow = (LinearLayout) convertView.findViewById(R.id.secListRow);
                PNCSl = (TextView) convertView.findViewById(R.id.PNCSl);
                PNCDate = (TextView) convertView.findViewById(R.id.PNCDate);
            }
        }

        public DataAdapter(List<NBC_PNCMothDetail_DataModel> datalist) {
            this.dataList = datalist;
        }

        @Override
        public NBC_PNCMoth.DataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.nbc_pncmothdetail_row, parent, false);
            return new NBC_PNCMoth.DataAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(NBC_PNCMoth.DataAdapter.MyViewHolder holder, int position) {
            final NBC_PNCMothDetail_DataModel data = dataList.get(position);
            holder.PNCSl.setText(String.valueOf(data.getPNCSl()));
            holder.PNCDate.setText(Global.DateConvertDMY(data.getPNCDate()));
            holder.secListRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(NBC_PNCMoth.this, "", "Please Wait . . .", true);
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
}