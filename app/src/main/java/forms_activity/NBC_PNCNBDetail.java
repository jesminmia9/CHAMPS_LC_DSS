
package forms_activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
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

import org.icddrb.champs_lc_dss.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Common.Connection;
import Common.Global;
import Utility.MySharedPreferences;
import forms_datamodel.NBC_PNCNBDetail_DataModel;

public class NBC_PNCNBDetail extends AppCompatActivity {
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
    SimpleAdapter dataAdapter;
    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
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
    LinearLayout secChPNCSl;
    View lineChPNCSl;
    TextView VlblChPNCSl;
    EditText txtChPNCSl;
    LinearLayout secChPNCDate;
    View lineChPNCDate;
    TextView VlblChPNCDate;
    EditText dtpChPNCDate;
    LinearLayout secChPNCDateDk;
    View lineChPNCDateDk;
    TextView VlblChPNCDateDk;
    RadioGroup rdogrpChPNCDateDk;
    RadioButton rdoChPNCDateDk1;
    RadioButton rdoChPNCDateDk2;
    LinearLayout secChPNCProv;
    View lineChPNCProv;
    TextView VlblChPNCProv;
    RadioGroup rdogrpChPNCProv;
    RadioButton rdoChPNCProv1;
    RadioButton rdoChPNCProv2;
    RadioButton rdoChPNCProv3;
    RadioButton rdoChPNCProv4;
    RadioButton rdoChPNCProv5;
    RadioButton rdoChPNCProv6;
    RadioButton rdoChPNCProv7;
    RadioButton rdoChPNCProv8;
    LinearLayout secChPNCProvOth;
    View lineChPNCProvOth;
    TextView VlblChPNCProvOth;
    AutoCompleteTextView txtChPNCProvOth;
    LinearLayout secChPNCPlace;
    View lineChPNCPlace;
    TextView VlblChPNCPlace;
    RadioGroup rdogrpChPNCPlace;
    RadioButton rdoChPNCPlace1;
    RadioButton rdoChPNCPlace2;
    RadioButton rdoChPNCPlace3;
    RadioButton rdoChPNCPlace4;
    RadioButton rdoChPNCPlace5;
    RadioButton rdoChPNCPlace6;
    RadioButton rdoChPNCPlace7;
    RadioButton rdoChPNCPlace8;
    RadioButton rdoChPNCPlace9;
    RadioButton rdoChPNCPlace10;
    RadioButton rdoChPNCPlace11;
    LinearLayout secChPNCPlaceOth;
    View lineChPNCPlaceOth;
    TextView VlblChPNCPlaceOth;
    AutoCompleteTextView txtChPNCPlaceOth;
    LinearLayout secChPNCRes;
    View lineChPNCRes;
    TextView VlblChPNCRes;
    RadioGroup rdogrpChPNCRes;
    RadioButton rdoChPNCRes1;
    RadioButton rdoChPNCRes2;
    RadioButton rdoChPNCRes3;
    LinearLayout seclbl02;
    View linelbl02;
    LinearLayout secChPNCCkWA;
    View lineChPNCCkWA;
    TextView VlblChPNCCkWA;
    CheckBox chkChPNCCkWA;
    LinearLayout secChPNCCkTM;
    View lineChPNCCkTM;
    TextView VlblChPNCCkTM;
    CheckBox chkChPNCCkTM;
    LinearLayout secChPNCCkRR;
    View lineChPNCCkRR;
    TextView VlblChPNCCkRR;
    CheckBox chkChPNCCkRR;
    LinearLayout secChPNCCkCE;
    View lineChPNCCkCE;
    TextView VlblChPNCCkCE;
    CheckBox chkChPNCCkCE;
    LinearLayout secChPNCCkCDS;
    View lineChPNCCkCDS;
    TextView VlblChPNCCkCDS;
    CheckBox chkChPNCCkCDS;
    LinearLayout secChPNCCkCB;
    View lineChPNCCkCB;
    TextView VlblChPNCCkCB;
    CheckBox chkChPNCCkCB;
    LinearLayout secChPNCCkOB;
    View lineChPNCCkOB;
    TextView VlblChPNCCkOB;
    CheckBox chkChPNCCkOB;
    LinearLayout secChPNCCkOth;
    View lineChPNCCkOth;
    TextView VlblChPNCCkOth;
    CheckBox chkChPNCCkOth;
    LinearLayout secChPNCCkOthSp;
    View lineChPNCCkOthSp;
    TextView VlblChPNCCkOthSp;
    AutoCompleteTextView txtChPNCCkOthSp;
    LinearLayout secChPNCCkDk;
    View lineChPNCCkDk;
    TextView VlblChPNCCkDk;
    CheckBox chkChPNCCkDk;
    LinearLayout secChPNCCkRef;
    View lineChPNCCkRef;
    TextView VlblChPNCCkRef;
    CheckBox chkChPNCCkRef;

     int MODULEID = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String NBID = "";
     String PGN = "";
     String CHSL = "";
     String CHPNCSL = "";
     String MEMID = "";
     String HHID = "";
     String ROUND = "";
     String isEdit = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.nbc_pncnbdetail);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            NBID = IDbundle.getString("NBID");
            PGN = IDbundle.getString("PGN");
            CHSL = IDbundle.getString("ChSl");
            CHPNCSL = IDbundle.getString("ChPNCSl");
            MEMID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            isEdit = IDbundle.getString("isEdit");

            TableName = "NBC_PNCNBDetail";
            MODULEID = 47;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(NBC_PNCNBDetail.this);
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
            Connection.LocalizeLanguage(NBC_PNCNBDetail.this, MODULEID, LANGUAGEID);


            dtpChPNCDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnChPNCDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            //Hide all skip variables
            secChPNCProvOth.setVisibility(View.GONE);
            lineChPNCProvOth.setVisibility(View.GONE);
            secChPNCPlaceOth.setVisibility(View.GONE);
            lineChPNCPlaceOth.setVisibility(View.GONE);
            secChPNCCkOthSp.setVisibility(View.GONE);
            lineChPNCCkOthSp.setVisibility(View.GONE);


            DataSearch(NBID, PGN, CHSL, CHPNCSL);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCNBDetail.this, e.getMessage());
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
            secChPNCSl = (LinearLayout) findViewById(R.id.secChPNCSl);
            lineChPNCSl = (View) findViewById(R.id.lineChPNCSl);
            VlblChPNCSl = (TextView) findViewById(R.id.VlblChPNCSl);
            txtChPNCSl = (EditText) findViewById(R.id.txtChPNCSl);
            if (CHPNCSL.length() == 0) txtChPNCSl.setText(NewPNCSerial(MEMID, HHID, PGN, CHSL));
            else txtChPNCSl.setText(CHPNCSL);
            txtChPNCSl.setEnabled(false);
            secChPNCDate = (LinearLayout) findViewById(R.id.secChPNCDate);
            lineChPNCDate = (View) findViewById(R.id.lineChPNCDate);
            VlblChPNCDate = (TextView) findViewById(R.id.VlblChPNCDate);
            dtpChPNCDate = (EditText) findViewById(R.id.dtpChPNCDate);
            secChPNCDateDk = (LinearLayout) findViewById(R.id.secChPNCDateDk);
            lineChPNCDateDk = (View) findViewById(R.id.lineChPNCDateDk);
            VlblChPNCDateDk = (TextView) findViewById(R.id.VlblChPNCDateDk);
            rdogrpChPNCDateDk = (RadioGroup) findViewById(R.id.rdogrpChPNCDateDk);
            rdogrpChPNCDateDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpPNCDateDk = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpChPNCDateDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpChPNCDateDk.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpPNCDateDk[i];

                        if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2")) {
                            if (dtpChPNCDate.getText().toString().length() > 0) {
                                dtpChPNCDate.setText("");
                            }
                        }

                    }

                }
            });
            rdoChPNCDateDk1 = (RadioButton) findViewById(R.id.rdoChPNCDateDk1);
            rdoChPNCDateDk2 = (RadioButton) findViewById(R.id.rdoChPNCDateDk2);
            dtpChPNCDate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (dtpChPNCDate.getText().toString().length() > 0) {
                        if (rdoChPNCDateDk1.isChecked() || rdoChPNCDateDk2.isChecked()) {
                            rdogrpChPNCDateDk.clearCheck();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            secChPNCProv = (LinearLayout) findViewById(R.id.secChPNCProv);
            lineChPNCProv = (View) findViewById(R.id.lineChPNCProv);
            VlblChPNCProv = (TextView) findViewById(R.id.VlblChPNCProv);
            rdogrpChPNCProv = (RadioGroup) findViewById(R.id.rdogrpChPNCProv);
            rdoChPNCProv1 = (RadioButton) findViewById(R.id.rdoChPNCProv1);
            rdoChPNCProv2 = (RadioButton) findViewById(R.id.rdoChPNCProv2);
            rdoChPNCProv3 = (RadioButton) findViewById(R.id.rdoChPNCProv3);
            rdoChPNCProv4 = (RadioButton) findViewById(R.id.rdoChPNCProv4);
            rdoChPNCProv5 = (RadioButton) findViewById(R.id.rdoChPNCProv5);
            rdoChPNCProv6 = (RadioButton) findViewById(R.id.rdoChPNCProv6);
            rdoChPNCProv7 = (RadioButton) findViewById(R.id.rdoChPNCProv7);
            rdoChPNCProv8 = (RadioButton) findViewById(R.id.rdoChPNCProv8);
            rdogrpChPNCProv.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpChPNCProv = new String[]{"21", "22", "23", "31", "32", "97", "98", "99"};
                    for (int i = 0; i < rdogrpChPNCProv.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpChPNCProv.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpChPNCProv[i];
                    }

                    if (rbData.equalsIgnoreCase("97")) {
                        secChPNCProvOth.setVisibility(View.VISIBLE);
                        lineChPNCProvOth.setVisibility(View.VISIBLE);
                    } else {
                        secChPNCProvOth.setVisibility(View.GONE);
                        lineChPNCProvOth.setVisibility(View.GONE);
                        txtChPNCProvOth.setText("");
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secChPNCProvOth = (LinearLayout) findViewById(R.id.secChPNCProvOth);
            lineChPNCProvOth = (View) findViewById(R.id.lineChPNCProvOth);
            VlblChPNCProvOth = (TextView) findViewById(R.id.VlblChPNCProvOth);
            txtChPNCProvOth = (AutoCompleteTextView) findViewById(R.id.txtChPNCProvOth);
            C.setupAutoComplete(TableName,txtChPNCProvOth,"ChPNCProvOth"); //setup autocomplete view by event


            secChPNCPlace = (LinearLayout) findViewById(R.id.secChPNCPlace);
            lineChPNCPlace = (View) findViewById(R.id.lineChPNCPlace);
            VlblChPNCPlace = (TextView) findViewById(R.id.VlblChPNCPlace);
            rdogrpChPNCPlace = (RadioGroup) findViewById(R.id.rdogrpChPNCPlace);
            rdoChPNCPlace1 = (RadioButton) findViewById(R.id.rdoChPNCPlace1);
            rdoChPNCPlace2 = (RadioButton) findViewById(R.id.rdoChPNCPlace2);
            rdoChPNCPlace3 = (RadioButton) findViewById(R.id.rdoChPNCPlace3);
            rdoChPNCPlace4 = (RadioButton) findViewById(R.id.rdoChPNCPlace4);
            rdoChPNCPlace5 = (RadioButton) findViewById(R.id.rdoChPNCPlace5);
            rdoChPNCPlace6 = (RadioButton) findViewById(R.id.rdoChPNCPlace6);
            rdoChPNCPlace7 = (RadioButton) findViewById(R.id.rdoChPNCPlace7);
            rdoChPNCPlace8 = (RadioButton) findViewById(R.id.rdoChPNCPlace8);
            rdoChPNCPlace9 = (RadioButton) findViewById(R.id.rdoChPNCPlace9);
            rdoChPNCPlace10 = (RadioButton) findViewById(R.id.rdoChPNCPlace10);
            rdoChPNCPlace11 = (RadioButton) findViewById(R.id.rdoChPNCPlace11);
            rdogrpChPNCPlace.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpChPNCPlace = new String[]{"11", "15", "21", "22", "23", "31", "32", "41", "97", "98", "99"};
                    for (int i = 0; i < rdogrpChPNCPlace.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpChPNCPlace.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpChPNCPlace[i];
                    }

                    if (rbData.equalsIgnoreCase("97")) {
                        secChPNCPlaceOth.setVisibility(View.VISIBLE);
                        lineChPNCPlaceOth.setVisibility(View.VISIBLE);
                    } else {
                        secChPNCPlaceOth.setVisibility(View.GONE);
                        lineChPNCPlaceOth.setVisibility(View.GONE);
                        txtChPNCPlaceOth.setText("");
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secChPNCPlaceOth = (LinearLayout) findViewById(R.id.secChPNCPlaceOth);
            lineChPNCPlaceOth = (View) findViewById(R.id.lineChPNCPlaceOth);
            VlblChPNCPlaceOth = (TextView) findViewById(R.id.VlblChPNCPlaceOth);
            txtChPNCPlaceOth = (AutoCompleteTextView) findViewById(R.id.txtChPNCPlaceOth);
            C.setupAutoComplete(TableName,txtChPNCPlaceOth,"ChPNCPlaceOth"); //setup autocomplete view by event

            secChPNCRes = (LinearLayout) findViewById(R.id.secChPNCRes);
            lineChPNCRes = (View) findViewById(R.id.lineChPNCRes);
            VlblChPNCRes = (TextView) findViewById(R.id.VlblChPNCRes);
            rdogrpChPNCRes = (RadioGroup) findViewById(R.id.rdogrpChPNCRes);
            rdoChPNCRes1 = (RadioButton) findViewById(R.id.rdoChPNCRes1);
            rdoChPNCRes2 = (RadioButton) findViewById(R.id.rdoChPNCRes2);
            rdoChPNCRes3 = (RadioButton) findViewById(R.id.rdoChPNCRes3);
            seclbl02 = (LinearLayout) findViewById(R.id.seclbl02);
            linelbl02 = (View) findViewById(R.id.linelbl02);
            secChPNCCkWA = (LinearLayout) findViewById(R.id.secChPNCCkWA);
            lineChPNCCkWA = (View) findViewById(R.id.lineChPNCCkWA);
            VlblChPNCCkWA = (TextView) findViewById(R.id.VlblChPNCCkWA);
            chkChPNCCkWA = (CheckBox) findViewById(R.id.chkChPNCCkWA);
            chkChPNCCkWA.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    chkChPNCCkRef.setChecked(false);
                    chkChPNCCkDk.setChecked(false);
                }
            });
            secChPNCCkTM = (LinearLayout) findViewById(R.id.secChPNCCkTM);
            lineChPNCCkTM = (View) findViewById(R.id.lineChPNCCkTM);
            VlblChPNCCkTM = (TextView) findViewById(R.id.VlblChPNCCkTM);
            chkChPNCCkTM = (CheckBox) findViewById(R.id.chkChPNCCkTM);
            chkChPNCCkTM.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    chkChPNCCkRef.setChecked(false);
                    chkChPNCCkDk.setChecked(false);
                }
            });
            secChPNCCkRR = (LinearLayout) findViewById(R.id.secChPNCCkRR);
            lineChPNCCkRR = (View) findViewById(R.id.lineChPNCCkRR);
            VlblChPNCCkRR = (TextView) findViewById(R.id.VlblChPNCCkRR);
            chkChPNCCkRR = (CheckBox) findViewById(R.id.chkChPNCCkRR);
            chkChPNCCkRR.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    chkChPNCCkRef.setChecked(false);
                    chkChPNCCkDk.setChecked(false);
                }
            });
            secChPNCCkCE=(LinearLayout)findViewById(R.id.secChPNCCkCE);
            lineChPNCCkCE=(View)findViewById(R.id.lineChPNCCkCE);
            VlblChPNCCkCE=(TextView) findViewById(R.id.VlblChPNCCkCE);
            chkChPNCCkCE=(CheckBox) findViewById(R.id.chkChPNCCkCE);
            chkChPNCCkCE.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    chkChPNCCkRef.setChecked(false);
                    chkChPNCCkDk.setChecked(false);
                }
            });
            secChPNCCkCDS=(LinearLayout)findViewById(R.id.secChPNCCkCDS);
            lineChPNCCkCDS=(View)findViewById(R.id.lineChPNCCkCDS);
            VlblChPNCCkCDS=(TextView) findViewById(R.id.VlblChPNCCkCDS);
            chkChPNCCkCDS=(CheckBox) findViewById(R.id.chkChPNCCkCDS);
            chkChPNCCkCDS.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    chkChPNCCkRef.setChecked(false);
                    chkChPNCCkDk.setChecked(false);
                }
            });
            secChPNCCkCB=(LinearLayout)findViewById(R.id.secChPNCCkCB);
            lineChPNCCkCB=(View)findViewById(R.id.lineChPNCCkCB);
            VlblChPNCCkCB=(TextView) findViewById(R.id.VlblChPNCCkCB);
            chkChPNCCkCB=(CheckBox) findViewById(R.id.chkChPNCCkCB);
            chkChPNCCkCB.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    chkChPNCCkRef.setChecked(false);
                    chkChPNCCkDk.setChecked(false);
                }
            });
            secChPNCCkOB=(LinearLayout)findViewById(R.id.secChPNCCkOB);
            lineChPNCCkOB=(View)findViewById(R.id.lineChPNCCkOB);
            VlblChPNCCkOB=(TextView) findViewById(R.id.VlblChPNCCkOB);
            chkChPNCCkOB=(CheckBox) findViewById(R.id.chkChPNCCkOB);
            chkChPNCCkOB.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    chkChPNCCkRef.setChecked(false);
                    chkChPNCCkDk.setChecked(false);
                }
            });
            secChPNCCkOth = (LinearLayout) findViewById(R.id.secChPNCCkOth);
            lineChPNCCkOth = (View) findViewById(R.id.lineChPNCCkOth);
            VlblChPNCCkOth = (TextView) findViewById(R.id.VlblChPNCCkOth);
            chkChPNCCkOth = (CheckBox) findViewById(R.id.chkChPNCCkOth);
            chkChPNCCkOth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkChPNCCkRef.setChecked(false);
                        chkChPNCCkDk.setChecked(false);
                        secChPNCCkOthSp.setVisibility(View.VISIBLE);
                        lineChPNCCkOthSp.setVisibility(View.VISIBLE);
                    } else {
                        secChPNCCkOthSp.setVisibility(View.GONE);
                        lineChPNCCkOthSp.setVisibility(View.GONE);
                        txtChPNCCkOthSp.setText("");
                    }
                }
            });
            secChPNCCkOthSp = (LinearLayout) findViewById(R.id.secChPNCCkOthSp);
            lineChPNCCkOthSp = (View) findViewById(R.id.lineChPNCCkOthSp);
            VlblChPNCCkOthSp = (TextView) findViewById(R.id.VlblChPNCCkOthSp);
            txtChPNCCkOthSp = (AutoCompleteTextView) findViewById(R.id.txtChPNCCkOthSp);
            C.setupAutoComplete(TableName,txtChPNCCkOthSp,"ChPNCCkOthSp"); //setup autocomplete view by event

            secChPNCCkDk = (LinearLayout) findViewById(R.id.secChPNCCkDk);
            lineChPNCCkDk = (View) findViewById(R.id.lineChPNCCkDk);
            VlblChPNCCkDk = (TextView) findViewById(R.id.VlblChPNCCkDk);
            chkChPNCCkDk = (CheckBox) findViewById(R.id.chkChPNCCkDk);
            chkChPNCCkDk.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    txtChPNCCkOthSp.setText("");
                    chkChPNCCkRef.setChecked(false);
                    chkChPNCCkWA.setChecked(false);
                    chkChPNCCkTM.setChecked(false);
                    chkChPNCCkRR.setChecked(false);
                    chkChPNCCkCE.setChecked(false);
                    chkChPNCCkCDS.setChecked(false);
                    chkChPNCCkCB.setChecked(false);
                    chkChPNCCkOB.setChecked(false);
                    chkChPNCCkOth.setChecked(false);
                }
            });
            secChPNCCkRef = (LinearLayout) findViewById(R.id.secChPNCCkRef);
            lineChPNCCkRef = (View) findViewById(R.id.lineChPNCCkRef);
            VlblChPNCCkRef = (TextView) findViewById(R.id.VlblChPNCCkRef);
            chkChPNCCkRef = (CheckBox) findViewById(R.id.chkChPNCCkRef);
            chkChPNCCkRef.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    txtChPNCCkOthSp.setText("");
                    chkChPNCCkDk.setChecked(false);
                    chkChPNCCkWA.setChecked(false);
                    chkChPNCCkTM.setChecked(false);
                    chkChPNCCkRR.setChecked(false);
                    chkChPNCCkCE.setChecked(false);
                    chkChPNCCkCDS.setChecked(false);
                    chkChPNCCkCB.setChecked(false);
                    chkChPNCCkOB.setChecked(false);
                    chkChPNCCkOth.setChecked(false);
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCNBDetail.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(NBC_PNCNBDetail.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            NBC_PNCNBDetail_DataModel objSave = new NBC_PNCNBDetail_DataModel();
            objSave.setNBID(txtNBID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setPGN(txtPGN.getText().toString());
            objSave.setChSl(txtChSl.getText().toString());
            objSave.setChPNCSl(txtChPNCSl.getText().toString());
            objSave.setChPNCDate(dtpChPNCDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpChPNCDate.getText().toString()) : dtpChPNCDate.getText().toString());
            String[] d_rdogrpChPNCDateDk = new String[]{"8", "9"};
            objSave.setChPNCDateDk("");
            for (int i = 0; i < rdogrpChPNCDateDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpChPNCDateDk.getChildAt(i);
                if (rb.isChecked()) objSave.setChPNCDateDk(d_rdogrpChPNCDateDk[i]);
            }

            String[] d_rdogrpChPNCProv = new String[]{"21", "22", "23", "31", "32", "97", "98", "99"};
            objSave.setChPNCProv("");
            for (int i = 0; i < rdogrpChPNCProv.getChildCount(); i++) {
                rb = (RadioButton) rdogrpChPNCProv.getChildAt(i);
                if (rb.isChecked()) objSave.setChPNCProv(d_rdogrpChPNCProv[i]);
            }

            objSave.setChPNCProvOth(txtChPNCProvOth.getText().toString());
            String[] d_rdogrpChPNCPlace = new String[]{"11", "15", "21", "22", "23", "31", "32", "41", "97", "98", "99"};
            objSave.setChPNCPlace("");
            for (int i = 0; i < rdogrpChPNCPlace.getChildCount(); i++) {
                rb = (RadioButton) rdogrpChPNCPlace.getChildAt(i);
                if (rb.isChecked()) objSave.setChPNCPlace(d_rdogrpChPNCPlace[i]);
            }

            objSave.setChPNCPlaceOth(txtChPNCPlaceOth.getText().toString());
            String[] d_rdogrpChPNCRes = new String[]{"1", "2", "3"};
            objSave.setChPNCRes("");
            for (int i = 0; i < rdogrpChPNCRes.getChildCount(); i++) {
                rb = (RadioButton) rdogrpChPNCRes.getChildAt(i);
                if (rb.isChecked()) objSave.setChPNCRes(d_rdogrpChPNCRes[i]);
            }

            objSave.setChPNCCkWA((chkChPNCCkWA.isChecked() ? "1" : (secChPNCCkWA.isShown() ? "2" : "")));
            objSave.setChPNCCkTM((chkChPNCCkTM.isChecked() ? "1" : (secChPNCCkTM.isShown() ? "2" : "")));
            objSave.setChPNCCkRR((chkChPNCCkRR.isChecked() ? "1" : (secChPNCCkRR.isShown() ? "2" : "")));
            objSave.setChPNCCkCE((chkChPNCCkCE.isChecked() ? "1" : (secChPNCCkCE.isShown() ? "2" : "")));
            objSave.setChPNCCkCDS((chkChPNCCkCDS.isChecked() ? "1" : (secChPNCCkCDS.isShown() ? "2" : "")));
            objSave.setChPNCCkCB((chkChPNCCkCB.isChecked() ? "1" : (secChPNCCkCB.isShown() ? "2" : "")));
            objSave.setChPNCCkOB((chkChPNCCkOB.isChecked() ? "1" : (secChPNCCkOB.isShown() ? "2" : "")));
            objSave.setChPNCCkOth((chkChPNCCkOth.isChecked() ? "1" : (secChPNCCkOth.isShown() ? "2" : "")));
            objSave.setChPNCCkOthSp(txtChPNCCkOthSp.getText().toString());
            objSave.setChPNCCkDk((chkChPNCCkDk.isChecked() ? "1" : (secChPNCCkDk.isShown() ? "2" : "")));
            objSave.setChPNCCkRef((chkChPNCCkRef.isChecked() ? "1" : (secChPNCCkRef.isShown() ? "2" : "")));
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

                Toast.makeText(NBC_PNCNBDetail.this, "Save Successfully...", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(NBC_PNCNBDetail.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCNBDetail.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        String lastPNCDate = "";
        String pickedPNCDate = "";
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
            if (txtChPNCSl.getText().toString().length() == 0 & secChPNCSl.isShown()) {
                ValidationMsg += "\nRequired field: Child PNC serial.";
                secChPNCSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpChPNCDate.getText().toString());
            lastPNCDate = C.ReturnSingleValue("SELECT COALESCE(MAX(DATE(ChPNCDate)), '') AS max_date FROM NBC_PNCNBDetail where MemID = '" + MEMID + "' and HHID = '" + HHID + "' and PGN = '" + PGN + "'  and ChSl = '" + CHSL + "' and isdelete=2");
            pickedPNCDate = Global.DateConvertYMD(dtpChPNCDate.getText().toString());
            boolean isPickedDateAfterPrevious = dateChecker(pickedPNCDate, lastPNCDate);
            if (!rdoChPNCDateDk1.isChecked() & !rdoChPNCDateDk2.isChecked() & DV.length() != 0 & secChPNCDate.isShown() & secChPNCDateDk.isShown()) {
                ValidationMsg += "\nB4.8 Required field/Not a valid date format: Date of PNC check-up.";
                secChPNCDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                ValidationMsg += "\nRequired field: PNC Date Dont know.";
                secChPNCDateDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (lastPNCDate != null && lastPNCDate.length() != 0
                    && !isPickedDateAfterPrevious
                    && !rdoChPNCDateDk1.isChecked()
                    && !rdoChPNCDateDk2.isChecked()
                    && isEdit.equals("1")) {
                ValidationMsg += "\nNew PNC Date should be bigger than last PNC Date";
                secChPNCDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }


//            if (DV.length() != 0 & secChPNCDate.isShown()) {
//                ValidationMsg += "\nB4.8 Required field/Not a valid date format: Date of PNC check-up.";
//                secChPNCDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
//            }
//            if (!rdoChPNCDateDk1.isChecked() & !rdoChPNCDateDk2.isChecked() & secChPNCDateDk.isShown()) {
//                ValidationMsg += "\nRequired field: PNC Date Dont know.";
//                secChPNCDateDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
//            }
            if (!rdoChPNCProv1.isChecked() & !rdoChPNCProv2.isChecked() & !rdoChPNCProv3.isChecked() & !rdoChPNCProv4.isChecked() & !rdoChPNCProv5.isChecked() & !rdoChPNCProv6.isChecked() & !rdoChPNCProv7.isChecked() & !rdoChPNCProv8.isChecked() & secChPNCProv.isShown()) {
                ValidationMsg += "\nB4.9 Required field: Whom did you see for your postnatal care checkup for the child/name? (record code).";
                secChPNCProv.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtChPNCProvOth.getText().toString().length() == 0 & secChPNCProvOth.isShown()) {
                ValidationMsg += "\nRequired field: Other ANC Provider.";
                secChPNCProvOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoChPNCPlace1.isChecked() & !rdoChPNCPlace2.isChecked() & !rdoChPNCPlace3.isChecked() & !rdoChPNCPlace4.isChecked() & !rdoChPNCPlace5.isChecked() & !rdoChPNCPlace6.isChecked() & !rdoChPNCPlace7.isChecked() & !rdoChPNCPlace8.isChecked() & !rdoChPNCPlace9.isChecked() & !rdoChPNCPlace10.isChecked() & !rdoChPNCPlace11.isChecked() & secChPNCPlace.isShown()) {
                ValidationMsg += "\nB4.10 Required field: Where did you see your postnatal care checkup for the child/name? (Write facility name and record code).";
                secChPNCPlace.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtChPNCPlaceOth.getText().toString().length() == 0 & secChPNCPlaceOth.isShown()) {
                ValidationMsg += "\nRequired field: Other ANC Place.";
                secChPNCPlaceOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoChPNCRes1.isChecked() & !rdoChPNCRes2.isChecked() & !rdoChPNCRes3.isChecked() & secChPNCRes.isShown()) {
                ValidationMsg += "\nB4.11 Required field: Why did you visit for the child/name?.";
                secChPNCRes.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtChPNCCkOthSp.getText().toString().length() == 0 & secChPNCCkOthSp.isShown()) {
                ValidationMsg += "\nRequired field: Other specify.";
                secChPNCCkOthSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private boolean dateChecker(String pickedDateString, String previousDateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date pickedDate = sdf.parse(pickedDateString);
            Date previousDate = sdf.parse(previousDateString);

            return pickedDate.after(previousDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // Return false in case of an exception or invalid date format
            return false;
        }
    }

    private void ResetSectionColor() {
        try {
            secNBID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secPGN.setBackgroundColor(Color.WHITE);
            secChSl.setBackgroundColor(Color.WHITE);
            secChPNCSl.setBackgroundColor(Color.WHITE);
            secChPNCDate.setBackgroundColor(Color.WHITE);
            secChPNCDateDk.setBackgroundColor(Color.WHITE);
            secChPNCProv.setBackgroundColor(Color.WHITE);
            secChPNCProvOth.setBackgroundColor(Color.WHITE);
            secChPNCPlace.setBackgroundColor(Color.WHITE);
            secChPNCPlaceOth.setBackgroundColor(Color.WHITE);
            secChPNCRes.setBackgroundColor(Color.WHITE);
            secChPNCCkOthSp.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String NBID, String PGN, String ChSl, String ChPNCSl) {
        try {
            RadioButton rb;
            NBC_PNCNBDetail_DataModel d = new NBC_PNCNBDetail_DataModel();
            String SQL = "Select * from " + TableName + "  Where NBID='" + NBID + "' and PGN='" + PGN + "' and ChSl='" + ChSl + "' and ChPNCSl='" + ChPNCSl + "'";
            List<NBC_PNCNBDetail_DataModel> data = d.SelectAll(this, SQL);
            for (NBC_PNCNBDetail_DataModel item : data) {
                txtNBID.setText(item.getNBID());
                txtMemID.setText(item.getMemID());
                txtHHID.setText(item.getHHID());
                txtPGN.setText(item.getPGN());
                txtChSl.setText(item.getChSl());
                txtChPNCSl.setText(item.getChPNCSl());
                dtpChPNCDate.setText(item.getChPNCDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getChPNCDate()));
                String[] d_rdogrpChPNCDateDk = new String[]{"8", "9"};
                for (int i = 0; i < d_rdogrpChPNCDateDk.length; i++) {
                    if (String.valueOf(item.getChPNCDateDk()).equals(String.valueOf(d_rdogrpChPNCDateDk[i]))) {
                        rb = (RadioButton) rdogrpChPNCDateDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpChPNCProv = new String[]{"21", "22", "23", "31", "32", "97", "98", "99"};
                for (int i = 0; i < d_rdogrpChPNCProv.length; i++) {
                    if (String.valueOf(item.getChPNCProv()).equals(String.valueOf(d_rdogrpChPNCProv[i]))) {
                        rb = (RadioButton) rdogrpChPNCProv.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtChPNCProvOth.setText(item.getChPNCProvOth());
                String[] d_rdogrpChPNCPlace = new String[]{"11", "15", "21", "22", "23", "31", "32", "41", "97", "98", "99"};
                for (int i = 0; i < d_rdogrpChPNCPlace.length; i++) {
                    if (String.valueOf(item.getChPNCPlace()).equals(String.valueOf(d_rdogrpChPNCPlace[i]))) {
                        rb = (RadioButton) rdogrpChPNCPlace.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtChPNCPlaceOth.setText(item.getChPNCPlaceOth());
                String[] d_rdogrpChPNCRes = new String[]{"1", "2", "3"};
                for (int i = 0; i < d_rdogrpChPNCRes.length; i++) {
                    if (String.valueOf(item.getChPNCRes()).equals(String.valueOf(d_rdogrpChPNCRes[i]))) {
                        rb = (RadioButton) rdogrpChPNCRes.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                if (String.valueOf(item.getChPNCCkWA()).equals("1")) {
                    chkChPNCCkWA.setChecked(true);
                } else if (String.valueOf(item.getChPNCCkWA()).equals("2")) {
                    chkChPNCCkWA.setChecked(false);
                }
                if (String.valueOf(item.getChPNCCkTM()).equals("1")) {
                    chkChPNCCkTM.setChecked(true);
                } else if (String.valueOf(item.getChPNCCkTM()).equals("2")) {
                    chkChPNCCkTM.setChecked(false);
                }
                if (String.valueOf(item.getChPNCCkRR()).equals("1")) {
                    chkChPNCCkRR.setChecked(true);
                } else if (String.valueOf(item.getChPNCCkRR()).equals("2")) {
                    chkChPNCCkRR.setChecked(false);
                }
                if(String.valueOf(item.getChPNCCkCE()).equals("1"))
                {
                    chkChPNCCkCE.setChecked(true);
                }
                else if(String.valueOf(item.getChPNCCkCE()).equals("2"))
                {
                    chkChPNCCkCE.setChecked(false);
                }
                if(String.valueOf(item.getChPNCCkCDS()).equals("1"))
                {
                    chkChPNCCkCDS.setChecked(true);
                }
                else if(String.valueOf(item.getChPNCCkCDS()).equals("2"))
                {
                    chkChPNCCkCDS.setChecked(false);
                }
                if(String.valueOf(item.getChPNCCkCB()).equals("1"))
                {
                    chkChPNCCkCB.setChecked(true);
                }
                else if(String.valueOf(item.getChPNCCkCB()).equals("2"))
                {
                    chkChPNCCkCB.setChecked(false);
                }
                if(String.valueOf(item.getChPNCCkOB()).equals("1"))
                {
                    chkChPNCCkOB.setChecked(true);
                }
                else if(String.valueOf(item.getChPNCCkOB()).equals("2"))
                {
                    chkChPNCCkOB.setChecked(false);
                }
                if (String.valueOf(item.getChPNCCkOth()).equals("1")) {
                    chkChPNCCkOth.setChecked(true);
                } else if (String.valueOf(item.getChPNCCkOth()).equals("2")) {
                    chkChPNCCkOth.setChecked(false);
                }
                txtChPNCCkOthSp.setText(item.getChPNCCkOthSp());
                if (String.valueOf(item.getChPNCCkDk()).equals("1")) {
                    chkChPNCCkDk.setChecked(true);
                } else if (String.valueOf(item.getChPNCCkDk()).equals("2")) {
                    chkChPNCCkDk.setChecked(false);
                }
                if (String.valueOf(item.getChPNCCkRef()).equals("1")) {
                    chkChPNCCkRef.setChecked(true);
                } else if (String.valueOf(item.getChPNCCkRef()).equals("2")) {
                    chkChPNCCkRef.setChecked(false);
                }
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCNBDetail.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpChPNCDate);
            if (VariableID.equals("btnChPNCDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpChPNCDate);
            }
            dtpDate.setText(new StringBuilder()
                    .append(Global.Right("00" + mDay, 2)).append("/")
                    .append(Global.Right("00" + mMonth, 2)).append("/")
                    .append(mYear));
        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;
            EditText tpTime;

        }
    };

    public String NewPNCSerial(String MEMID, String HHID, String PGN, String ChSl) {
        String M = C.ReturnSingleValue("Select cast(ifnull(max(ChPNCSl),0)+1 as varchar(2))ChPNCSl from NBC_PNCNBDetail where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='" + PGN + "' and ChSl = '" + ChSl + "'");
        M = Global.Right("0" + M, 2);
        return M;
    }

    // turning off the GPS if its in on state. to avoid the battery drain.
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}