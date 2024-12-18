
package forms_activity;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.icddrb.champs_lc_dss.R;
import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import Common.Connection;
import Common.Global;
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import forms_datamodel.LiveBirth_DataModel;
import forms_datamodel.tmpLiveBirth_DataModel;

public class Surv_LiveBirth extends AppCompatActivity {
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
    LinearLayout secLiveBirthID;
    View lineLiveBirthID;
    TextView VlblLiveBirthID;
    EditText txtLiveBirthID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secLBMomID;
    View lineLBMomID;
    TextView VlblLBMomID;
    EditText txtLBMomID;
    LinearLayout secLBDOB;
    View lineLBDOB;
    TextView VlblLBDOB;
    EditText dtpLBDOB;
    LinearLayout secLBTime;
    View lineLBTime;
    TextView VlblLBTime;
    EditText txtLBTime;
    LinearLayout secLBTimeType;
    View lineLBTimeType;
    TextView VlblLBTimeType;
    RadioGroup rdogrpLBTimeType;
    RadioButton rdoLBTimeType1;
    RadioButton rdoLBTimeType2;
    RadioButton rdoLBTimeType3;
    LinearLayout secLBDPlace;
    View lineLBDPlace;
    TextView VlblLBDPlace;
    Spinner spnLBDPlace;
    LinearLayout secLBPlaceOth;
    View lineLBPlaceOth;
    TextView VlblLBPlaceOth;
    AutoCompleteTextView txtLBPlaceOth;
    LinearLayout seclbl01;
    View linelbl01;
    LinearLayout secLBDoctor;
    View lineLBDoctor;
    TextView VlblLBDoctor;
    CheckBox chkLBDoctor;
    LinearLayout secLBNurse;
    View lineLBNurse;
    TextView VlblLBNurse;
    CheckBox chkLBNurse;
    LinearLayout secLBMidwife;
    View lineLBMidwife;
    TextView VlblLBMidwife;
    CheckBox chkLBMidwife;
    LinearLayout secLBTBA;
    View lineLBTBA;
    TextView VlblLBTBA;
    CheckBox chkLBTBA;
    LinearLayout secLBCHW;
    View lineLBCHW;
    TextView VlblLBCHW;
    CheckBox chkLBCHW;
    LinearLayout secLBRF;
    View lineLBRF;
    View lineSBNAT;
    LinearLayout secSBNAT;
    TextView VlblSBNAT;
    TextView VlblLBRF;
    CheckBox chkLBRF;
    CheckBox chkNAT;
    LinearLayout secLBOth;
    View lineLBOth;
    TextView VlblLBOth;
    CheckBox chkLBOth;
    LinearLayout secLBOthSpecify;
    View lineLBOthSpecify;
    TextView VlblLBOthSpecify;
    AutoCompleteTextView txtLBOthSpecify;
    LinearLayout secLBDk;
    View lineLBDk;
    TextView VlblLBDk;
    CheckBox chkLBDk;
    LinearLayout secLBRfs;
    View lineLBRfs;
    TextView VlblLBRfs;
    CheckBox chkLBRfs;
    LinearLayout secLBType;
    View lineLBType;
    TextView VlblLBType;
    RadioGroup rdogrpLBType;
    RadioButton rdoLBType1;
    RadioButton rdoLBType2;
    RadioButton rdoLBType3;
    RadioButton rdoLBType4;
    RadioButton rdoLBType5;
    RadioButton rdoLBType6;
    LinearLayout secLBVDate;
    View lineLBVDate;
    TextView VlblLBVDate;
    EditText dtpLBVDate;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secLBNote;
    View lineLBNote;
    TextView VlblLBNote;
    EditText txtLBNote;

    LinearLayout secLBNote1;
    View lineLBNote1;
    TextView lblEvCode;

     int MODULEID = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String LIVEBIRTHID = "";
     String DELIVERYID = "";
     String MEM_ID = "";
     String LiveBirthMemID = "";
     String HHID = "";
     String HHNO = "";
     String EV_TYPE = "";
     String ROUND = "";
     String CHILD = "";
     String DOD = "";
     String TOD = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.surv_livebirth);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();
            sp = new MySharedPreferences();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            LIVEBIRTHID = IDbundle.getString("LiveBirthID");
            DELIVERYID = IDbundle.getString("DeliveryID");
            MEM_ID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            HHNO = IDbundle.getString("HHNO");
            EV_TYPE = IDbundle.getString("evtype");
            ROUND = IDbundle.getString("round");
            CHILD = IDbundle.getString("child");
            DOD = IDbundle.getString("dod");
            TOD = IDbundle.getString("tod");
            LiveBirthMemID = IDbundle.getString("LiveBirthMemID");

            TableName = "tmpLiveBirth";
            MODULEID = 28;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_LiveBirth.this);
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
            Connection.LocalizeLanguage(Surv_LiveBirth.this, MODULEID, LANGUAGEID);


            dtpLBDOB.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnLBDOB";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });
            dtpLBVDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnLBVDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            txtLBTime.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnLBTime";
                        showDialog(TIME_DIALOG);
                        return true;
                    }
                    return false;
                }
            });

            //Hide all skip variables
            secLBPlaceOth.setVisibility(View.GONE);
            lineLBPlaceOth.setVisibility(View.GONE);
            secLBOthSpecify.setVisibility(View.GONE);
            lineLBOthSpecify.setVisibility(View.GONE);


            DataSearch(LIVEBIRTHID);

            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(Surv_LiveBirth.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            lblEvCode = findViewById(R.id.lblEvCode);
            lblEvCode.setText(EV_TYPE);

            secLiveBirthID = (LinearLayout) findViewById(R.id.secLiveBirthID);
            lineLiveBirthID = (View) findViewById(R.id.lineLiveBirthID);
            VlblLiveBirthID = (TextView) findViewById(R.id.VlblLiveBirthID);
            txtLiveBirthID = (EditText) findViewById(R.id.txtLiveBirthID);
//         txtLiveBirthID.setText(LIVEBIRTHID);
//         txtLiveBirthID.setEnabled(false);


            if (LIVEBIRTHID.length() == 0) txtLiveBirthID.setText(Global.Get_UUID(DEVICEID));
            else txtLiveBirthID.setText(LIVEBIRTHID);
            txtLiveBirthID.setEnabled(false);

            secHHID = (LinearLayout) findViewById(R.id.secHHID);
            lineHHID = (View) findViewById(R.id.lineHHID);
            VlblHHID = (TextView) findViewById(R.id.VlblHHID);
            txtHHID = (EditText) findViewById(R.id.txtHHID);
            txtHHID.setText(HHID);
            txtHHID.setEnabled(false);
            secMemID = (LinearLayout) findViewById(R.id.secMemID);
            lineMemID = (View) findViewById(R.id.lineMemID);
            VlblMemID = (TextView) findViewById(R.id.VlblMemID);
            txtMemID = (EditText) findViewById(R.id.txtMemID);
            secLBMomID = (LinearLayout) findViewById(R.id.secLBMomID);
            lineLBMomID = (View) findViewById(R.id.lineLBMomID);
            VlblLBMomID = (TextView) findViewById(R.id.VlblLBMomID);
            txtLBMomID = (EditText) findViewById(R.id.txtLBMomID);
            txtLBMomID.setText(MEM_ID);
            secLBDOB = (LinearLayout) findViewById(R.id.secLBDOB);
            lineLBDOB = (View) findViewById(R.id.lineLBDOB);
            VlblLBDOB = (TextView) findViewById(R.id.VlblLBDOB);
            dtpLBDOB = (EditText) findViewById(R.id.dtpLBDOB);
            dtpLBDOB.setText(DOD);
//         dtpLBDOB.setEnabled(false);
            secLBTime = (LinearLayout) findViewById(R.id.secLBTime);
            lineLBTime = (View) findViewById(R.id.lineLBTime);
            VlblLBTime = (TextView) findViewById(R.id.VlblLBTime);
            txtLBTime = (EditText) findViewById(R.id.txtLBTime);
            txtLBTime.setText(TOD);
//         txtLBTime.setEnabled(false);
            secLBTimeType = (LinearLayout) findViewById(R.id.secLBTimeType);
            lineLBTimeType = (View) findViewById(R.id.lineLBTimeType);
            VlblLBTimeType = (TextView) findViewById(R.id.VlblLBTimeType);
            rdogrpLBTimeType = (RadioGroup) findViewById(R.id.rdogrpLBTimeType);
            rdoLBTimeType1 = (RadioButton) findViewById(R.id.rdoLBTimeType1);
            rdoLBTimeType2 = (RadioButton) findViewById(R.id.rdoLBTimeType2);
            rdoLBTimeType3 = (RadioButton) findViewById(R.id.rdoLBTimeType3);
            secLBDPlace = (LinearLayout) findViewById(R.id.secLBDPlace);
            lineLBDPlace = (View) findViewById(R.id.lineLBDPlace);
            VlblLBDPlace = (TextView) findViewById(R.id.VlblLBDPlace);
            spnLBDPlace = (Spinner) findViewById(R.id.spnLBDPlace);
            List<String> listLBDPlace = new ArrayList<String>();

            listLBDPlace.add("");
            listLBDPlace.add("11-Own home");
            listLBDPlace.add("15-Other home");
            listLBDPlace.add("21-Government hospital");
            listLBDPlace.add("22-Government health center");
            listLBDPlace.add("23-Government health post");
            listLBDPlace.add("31-Private hospital/clinic");
            listLBDPlace.add("32-TBA Home");
            listLBDPlace.add("41-NGO hospital/clinic");
            listLBDPlace.add("51-On the way to health facility");
            listLBDPlace.add("52-On the way to home");
            listLBDPlace.add("53-On the way to health facility to other health facility");
            listLBDPlace.add("97-Other");
            listLBDPlace.add("98-Dont know");
            listLBDPlace.add("99-Refused to respond");
            spnLBDPlace.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listLBDPlace)));

            spnLBDPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnLBDPlace.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnLBDPlace.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("97")) {
                        secLBPlaceOth.setVisibility(View.VISIBLE);
                        lineLBPlaceOth.setVisibility(View.VISIBLE);
                    } else {
                        secLBPlaceOth.setVisibility(View.GONE);
                        lineLBPlaceOth.setVisibility(View.GONE);
                        txtLBPlaceOth.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secLBPlaceOth = (LinearLayout) findViewById(R.id.secLBPlaceOth);
            lineLBPlaceOth = (View) findViewById(R.id.lineLBPlaceOth);
            VlblLBPlaceOth = (TextView) findViewById(R.id.VlblLBPlaceOth);
            txtLBPlaceOth = (AutoCompleteTextView) findViewById(R.id.txtLBPlaceOth);
            C.setupAutoComplete(TableName,txtLBPlaceOth,"LBPlaceOth"); //setup autocomplete view by event

            seclbl01 = (LinearLayout) findViewById(R.id.seclbl01);
            linelbl01 = (View) findViewById(R.id.linelbl01);
            secLBDoctor = (LinearLayout) findViewById(R.id.secLBDoctor);
            lineLBDoctor = (View) findViewById(R.id.lineLBDoctor);
            VlblLBDoctor = (TextView) findViewById(R.id.VlblLBDoctor);
            chkLBDoctor = (CheckBox) findViewById(R.id.chkLBDoctor);
            secLBNurse = (LinearLayout) findViewById(R.id.secLBNurse);
            lineLBNurse = (View) findViewById(R.id.lineLBNurse);
            VlblLBNurse = (TextView) findViewById(R.id.VlblLBNurse);
            chkLBNurse = (CheckBox) findViewById(R.id.chkLBNurse);
            secLBMidwife = (LinearLayout) findViewById(R.id.secLBMidwife);
            lineLBMidwife = (View) findViewById(R.id.lineLBMidwife);
            VlblLBMidwife = (TextView) findViewById(R.id.VlblLBMidwife);
            chkLBMidwife = (CheckBox) findViewById(R.id.chkLBMidwife);
            secLBTBA = (LinearLayout) findViewById(R.id.secLBTBA);
            lineLBTBA = (View) findViewById(R.id.lineLBTBA);
            VlblLBTBA = (TextView) findViewById(R.id.VlblLBTBA);
            chkLBTBA = (CheckBox) findViewById(R.id.chkLBTBA);
            secLBCHW = (LinearLayout) findViewById(R.id.secLBCHW);
            lineLBCHW = (View) findViewById(R.id.lineLBCHW);
            VlblLBCHW = (TextView) findViewById(R.id.VlblLBCHW);
            chkLBCHW = (CheckBox) findViewById(R.id.chkLBCHW);
            secLBRF = (LinearLayout) findViewById(R.id.secLBRF);
            lineLBRF = (View) findViewById(R.id.lineLBRF);
            VlblLBRF = (TextView) findViewById(R.id.VlblLBRF);
            chkLBRF = (CheckBox) findViewById(R.id.chkLBRF);
            secSBNAT = (LinearLayout) findViewById(R.id.secSBNAT);
            VlblSBNAT = (TextView) findViewById(R.id.VlblSBNAT);
            lineSBNAT = (View) findViewById(R.id.lineSBNAT);
            chkNAT = (CheckBox) findViewById(R.id.chkNAT);
            secLBOth = (LinearLayout) findViewById(R.id.secLBOth);
            lineLBOth = (View) findViewById(R.id.lineLBOth);
            VlblLBOth = (TextView) findViewById(R.id.VlblLBOth);
            chkLBOth = (CheckBox) findViewById(R.id.chkLBOth);
            secLBOthSpecify = (LinearLayout) findViewById(R.id.secLBOthSpecify);
            lineLBOthSpecify = (View) findViewById(R.id.lineLBOthSpecify);
            VlblLBOthSpecify = (TextView) findViewById(R.id.VlblLBOthSpecify);
            txtLBOthSpecify = (AutoCompleteTextView) findViewById(R.id.txtLBOthSpecify);
            C.setupAutoComplete(TableName,txtLBOthSpecify,"LBOthSpecify"); //setup autocomplete view by event


            chkLBOth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (chkLBOth.isChecked()) {
                        secLBOthSpecify.setVisibility(View.VISIBLE);
                        lineLBOthSpecify.setVisibility(View.VISIBLE);
                    } else {
                        secLBOthSpecify.setVisibility(View.GONE);
                        lineLBOthSpecify.setVisibility(View.GONE);
                    }
                }
            });

            secLBDk = (LinearLayout) findViewById(R.id.secLBDk);
            lineLBDk = (View) findViewById(R.id.lineLBDk);
            VlblLBDk = (TextView) findViewById(R.id.VlblLBDk);
            chkLBDk = (CheckBox) findViewById(R.id.chkLBDk);
            secLBRfs = (LinearLayout) findViewById(R.id.secLBRfs);
            lineLBRfs = (View) findViewById(R.id.lineLBRfs);
            VlblLBRfs = (TextView) findViewById(R.id.VlblLBRfs);
            chkLBRfs = (CheckBox) findViewById(R.id.chkLBRfs);
            secLBType = (LinearLayout) findViewById(R.id.secLBType);
            lineLBType = (View) findViewById(R.id.lineLBType);
            VlblLBType = (TextView) findViewById(R.id.VlblLBType);
            rdogrpLBType = (RadioGroup) findViewById(R.id.rdogrpLBType);
            rdoLBType1 = (RadioButton) findViewById(R.id.rdoLBType1);
            rdoLBType2 = (RadioButton) findViewById(R.id.rdoLBType2);
            rdoLBType3 = (RadioButton) findViewById(R.id.rdoLBType3);
            rdoLBType4 = (RadioButton) findViewById(R.id.rdoLBType4);
            rdoLBType5 = (RadioButton) findViewById(R.id.rdoLBType5);
            rdoLBType6 = (RadioButton) findViewById(R.id.rdoLBType6);
            secLBVDate = (LinearLayout) findViewById(R.id.secLBVDate);
            lineLBVDate = (View) findViewById(R.id.lineLBVDate);
            VlblLBVDate = (TextView) findViewById(R.id.VlblLBVDate);
            dtpLBVDate = (EditText) findViewById(R.id.dtpLBVDate);
            dtpLBVDate.setText(Global.DateNowDMY());
            secRnd = (LinearLayout) findViewById(R.id.secRnd);
            lineRnd = (View) findViewById(R.id.lineRnd);
            VlblRnd = (TextView) findViewById(R.id.VlblRnd);
            txtRnd = (EditText) findViewById(R.id.txtRnd);
            txtRnd.setText(ROUND);
            txtRnd.setEnabled(false);
            secLBNote = (LinearLayout) findViewById(R.id.secLBNote);
            lineLBNote = (View) findViewById(R.id.lineLBNote);
            VlblLBNote = (TextView) findViewById(R.id.VlblLBNote);
            txtLBNote = (EditText) findViewById(R.id.txtLBNote);

            secLBNote1 = (LinearLayout) findViewById(R.id.secLBNote1);
            lineLBNote1 = (View) findViewById(R.id.lineLBNote1);
        } catch (Exception e) {
            Connection.MessageBox(Surv_LiveBirth.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(Surv_LiveBirth.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            tmpLiveBirth_DataModel objSave = new tmpLiveBirth_DataModel();
            objSave.setLiveBirthID(txtLiveBirthID.getText().toString());
            objSave.setDeliveryID(DELIVERYID);
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setLBMomID(txtLBMomID.getText().toString());
            objSave.setLBDOB(dtpLBDOB.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpLBDOB.getText().toString()) : dtpLBDOB.getText().toString());
            objSave.setLBTime(txtLBTime.getText().toString());
            String[] d_rdogrpLBTimeType = new String[]{"1", "2", "8"};
            objSave.setLBTimeType("");
            for (int i = 0; i < rdogrpLBTimeType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpLBTimeType.getChildAt(i);
                if (rb.isChecked()) objSave.setLBTimeType(d_rdogrpLBTimeType[i]);
            }

            objSave.setLBDPlace(spnLBDPlace.getSelectedItemPosition() == 0 ? "" : spnLBDPlace.getSelectedItem().toString().split("-")[0]);
            objSave.setLBPlaceOth(txtLBPlaceOth.getText().toString());
            objSave.setLBDoctor((chkLBDoctor.isChecked() ? "1" : (secLBDoctor.isShown() ? "2" : "")));
            objSave.setLBNurse((chkLBNurse.isChecked() ? "1" : (secLBNurse.isShown() ? "2" : "")));
            objSave.setLBMidwife((chkLBMidwife.isChecked() ? "1" : (secLBMidwife.isShown() ? "2" : "")));
            objSave.setLBTBA((chkLBTBA.isChecked() ? "1" : (secLBTBA.isShown() ? "2" : "")));
            objSave.setLBCHW((chkLBCHW.isChecked() ? "1" : (secLBCHW.isShown() ? "2" : "")));
            objSave.setLBRF((chkLBRF.isChecked() ? "1" : (secLBRF.isShown() ? "2" : "")));
            objSave.setLBOth((chkLBOth.isChecked() ? "1" : (secLBOth.isShown() ? "2" : "")));
            objSave.setLBOthSpecify(txtLBOthSpecify.getText().toString());
            objSave.setLBDk((chkLBDk.isChecked() ? "1" : (secLBDk.isShown() ? "2" : "")));
            objSave.setLBRfs((chkLBRfs.isChecked() ? "1" : (secLBRfs.isShown() ? "2" : "")));


            //Newly added,
            objSave.setNAT((chkNAT.isChecked() ? "1" : (secSBNAT.isShown() ? "2" : "")));

            String[] d_rdogrpLBType = new String[]{"1", "2", "3", "4", "5", "8"};
            objSave.setLBType("");
            for (int i = 0; i < rdogrpLBType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpLBType.getChildAt(i);
                if (rb.isChecked()) objSave.setLBType(d_rdogrpLBType[i]);
            }

            objSave.setLBVDate(dtpLBVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpLBVDate.getText().toString()) : dtpLBVDate.getText().toString());
            objSave.setRnd(txtRnd.getText().toString());
            objSave.setLBNote(txtLBNote.getText().toString());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);
            if (status.length() == 0) {
                Toast.makeText(Surv_LiveBirth.this, "Save Successfully...", Toast.LENGTH_SHORT).show();

                Bundle IDbundle = new Bundle();
                IDbundle.putString("MigrationID", "");
                IDbundle.putString("TmpMigrationID", "");
                IDbundle.putString("LiveBirthID", txtLiveBirthID.getText().toString());
                IDbundle.putString("MemID", LiveBirthMemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", EV_TYPE);
                IDbundle.putString("evdate", Global.DateConvertYMD(dtpLBDOB.getText().toString()));
                IDbundle.putString("vdate", Global.DateConvertYMD(dtpLBVDate.getText().toString()));
                IDbundle.putString("round", ROUND);
                IDbundle.putString("dod", dtpLBDOB.getText().toString());
                IDbundle.putString("MoID", txtLBMomID.getText().toString());
                if (CHILD.equals("1")) {
                    sp.save(this, "child1", CHILD);
                } else if (CHILD.equals("2")) {
                    sp.save(this, "child2", CHILD);
                } else if (CHILD.equals("3")) {
                    sp.save(this, "child3", CHILD);
                } else if (CHILD.equals("4")) {
                    sp.save(this, "child4", CHILD);
                } else if (CHILD.equals("5")) {
                    sp.save(this, "child5", CHILD);
                } else if (CHILD.equals("6")) {
                    sp.save(this, "child6", CHILD);
                } else if (CHILD.equals("7")) {
                    sp.save(this, "child7", CHILD);
                }

                Intent intent = new Intent(getApplicationContext(), Surv_Member.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                finish();
            } else {
                Connection.MessageBox(Surv_LiveBirth.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_LiveBirth.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        try {
            ResetSectionColor();
            if (txtLiveBirthID.getText().toString().length() == 0 & secLiveBirthID.isShown()) {
                ValidationMsg += "\nRequired field: Live Birth internal ID.";
                secLiveBirthID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtHHID.getText().toString().length() == 0 & secHHID.isShown()) {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtMemID.getText().toString().length() == 0 & secMemID.isShown()) {
                ValidationMsg += "\nRequired field: Member internal ID.";
                secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtLBMomID.getText().toString().length() == 0 & secLBMomID.isShown()) {
                ValidationMsg += "\nRequired field: Mother member internal ID.";
                secLBMomID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpLBDOB.getText().toString());
            if (DV.length() != 0 & secLBDOB.isShown()) {
                ValidationMsg += "\n1. Required field/Not a valid date format: What was the date of birth?.";
                secLBDOB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtLBTime.getText().length() == 0 & secLBTime.isShown()) {
                ValidationMsg += "\n2. Required field: What was the time of delivery?.";
                secLBTime.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoLBTimeType1.isChecked() & !rdoLBTimeType2.isChecked() & !rdoLBTimeType3.isChecked() & secLBTimeType.isShown()) {
                ValidationMsg += "\n3. Required field: Delivery Time Type.";
                secLBTimeType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnLBDPlace.getSelectedItemPosition() == 0 & secLBDPlace.isShown()) {
                ValidationMsg += "\n4. Required field: What was the place of birth?.";
                secLBDPlace.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtLBPlaceOth.getText().toString().length() == 0 & secLBPlaceOth.isShown()) {
                ValidationMsg += "\nRequired field: Other specify.";
                secLBPlaceOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!chkLBDoctor.isChecked() & !chkLBNurse.isChecked() & !chkLBMidwife.isChecked() & !chkLBTBA.isChecked() & !chkLBCHW.isChecked() & !chkLBRF.isChecked() & !chkLBOth.isChecked() & !chkLBDk.isChecked() & !chkNAT.isChecked() & !chkLBRfs.isChecked() & secLBDoctor.isShown()) {
                ValidationMsg += "\nRequired field: Who assisted during child birth?";
                secLBDoctor.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoLBType1.isChecked() & !rdoLBType2.isChecked() & !rdoLBType3.isChecked() & !rdoLBType4.isChecked() & !rdoLBType5.isChecked() & !rdoLBType6.isChecked() & secLBType.isShown()) {
                ValidationMsg += "\n6. Required field: How was the baby delivered?.";
                secLBType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpLBVDate.getText().toString());
            if (DV.length() != 0 & secLBVDate.isShown()) {
                ValidationMsg += "\n7. Required field/Not a valid date format: Visit date.";
                secLBVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtRnd.getText().toString().length() == 0 & secRnd.isShown()) {
                ValidationMsg += "\nRequired field: Round number.";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtLBOthSpecify.getText().toString().length() == 0 & secLBOthSpecify.isShown()) {
                ValidationMsg += "\nRequired field: Other specify.";
                secLBOthSpecify.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
/*         if(txtLBNote.getText().toString().length()==0 & secLBNote.isShown())
           {
             ValidationMsg += "\nRequired field: Note.";
             secLBNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/
        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor() {
        try {
            secLiveBirthID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secLBMomID.setBackgroundColor(Color.WHITE);
            secLBDOB.setBackgroundColor(Color.WHITE);
            secLBTime.setBackgroundColor(Color.WHITE);
            secLBTimeType.setBackgroundColor(Color.WHITE);
            secLBDPlace.setBackgroundColor(Color.WHITE);
            secLBPlaceOth.setBackgroundColor(Color.WHITE);
            secLBDoctor.setBackgroundColor(Color.WHITE);
            secLBNurse.setBackgroundColor(Color.WHITE);
            secLBMidwife.setBackgroundColor(Color.WHITE);
            secLBTBA.setBackgroundColor(Color.WHITE);
            secLBCHW.setBackgroundColor(Color.WHITE);
            secLBRF.setBackgroundColor(Color.WHITE);
            secLBOth.setBackgroundColor(Color.WHITE);
            secLBOthSpecify.setBackgroundColor(Color.WHITE);
            secLBDk.setBackgroundColor(Color.WHITE);
            secLBRfs.setBackgroundColor(Color.WHITE);
            secLBType.setBackgroundColor(Color.WHITE);
            secLBVDate.setBackgroundColor(Color.WHITE);
            secRnd.setBackgroundColor(Color.WHITE);
            secLBNote.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String LiveBirthID) {
        try {
            RadioButton rb;
            LiveBirth_DataModel d = new LiveBirth_DataModel();
            String SQL = "Select * from " + TableName + "  Where LiveBirthID='" + LiveBirthID + "'";
            List<LiveBirth_DataModel> data = d.SelectAll(this, SQL);
            for (LiveBirth_DataModel item : data) {
                txtLiveBirthID.setText(item.getLiveBirthID());
                txtHHID.setText(item.getHHID());
                txtMemID.setText(item.getMemID());
                txtLBMomID.setText(item.getLBMomID());
                dtpLBDOB.setText(item.getLBDOB().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getLBDOB()));
                txtLBTime.setText(item.getLBTime());
                String[] d_rdogrpLBTimeType = new String[]{"1", "2", "8"};
                for (int i = 0; i < d_rdogrpLBTimeType.length; i++) {
                    if (String.valueOf(item.getLBTimeType()).equals(String.valueOf(d_rdogrpLBTimeType[i]))) {
                        rb = (RadioButton) rdogrpLBTimeType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnLBDPlace.setSelection(Global.SpinnerItemPositionAnyLength(spnLBDPlace, String.valueOf(item.getLBDPlace())));
                txtLBPlaceOth.setText(item.getLBPlaceOth());
                if (String.valueOf(item.getLBDoctor()).equals("1")) {
                    chkLBDoctor.setChecked(true);
                } else if (String.valueOf(item.getLBDoctor()).equals("2")) {
                    chkLBDoctor.setChecked(false);
                }
                if (String.valueOf(item.getLBNurse()).equals("1")) {
                    chkLBNurse.setChecked(true);
                } else if (String.valueOf(item.getLBNurse()).equals("2")) {
                    chkLBNurse.setChecked(false);
                }
                if (String.valueOf(item.getLBMidwife()).equals("1")) {
                    chkLBMidwife.setChecked(true);
                } else if (String.valueOf(item.getLBMidwife()).equals("2")) {
                    chkLBMidwife.setChecked(false);
                }
                if (String.valueOf(item.getLBTBA()).equals("1")) {
                    chkLBTBA.setChecked(true);
                } else if (String.valueOf(item.getLBTBA()).equals("2")) {
                    chkLBTBA.setChecked(false);
                }
                if (String.valueOf(item.getLBCHW()).equals("1")) {
                    chkLBCHW.setChecked(true);
                } else if (String.valueOf(item.getLBCHW()).equals("2")) {
                    chkLBCHW.setChecked(false);
                }
                if (String.valueOf(item.getLBRF()).equals("1")) {
                    chkLBRF.setChecked(true);
                } else if (String.valueOf(item.getLBRF()).equals("2")) {
                    chkLBRF.setChecked(false);
                }
                if (String.valueOf(item.getLBOth()).equals("1")) {
                    chkLBOth.setChecked(true);
                } else if (String.valueOf(item.getLBOth()).equals("2")) {
                    chkLBOth.setChecked(false);
                }
                txtLBOthSpecify.setText(item.getLBOthSpecify());
                if (String.valueOf(item.getLBDk()).equals("1")) {
                    chkLBDk.setChecked(true);
                } else if (String.valueOf(item.getLBDk()).equals("2")) {
                    chkLBDk.setChecked(false);
                }
                if (String.valueOf(item.getLBRfs()).equals("1")) {
                    chkLBRfs.setChecked(true);
                } else if (String.valueOf(item.getLBRfs()).equals("2")) {
                    chkLBRfs.setChecked(false);
                }
                if (String.valueOf(item.getNAT()).equals("1")) {
                    chkNAT.setChecked(true);
                } else if (String.valueOf(item.getNAT()).equals("2")) {
                    chkNAT.setChecked(false);
                }
                String[] d_rdogrpLBType = new String[]{"1", "2", "3", "4", "5", "8"};
                for (int i = 0; i < d_rdogrpLBType.length; i++) {
                    if (String.valueOf(item.getLBType()).equals(String.valueOf(d_rdogrpLBType[i]))) {
                        rb = (RadioButton) rdogrpLBType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                dtpLBVDate.setText(item.getLBVDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getLBVDate()));
                txtRnd.setText(item.getRnd());
                txtLBNote.setText(item.getLBNote());
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_LiveBirth.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpLBDOB);
            if (VariableID.equals("btnLBDOB")) {
                dtpDate = (EditText) findViewById(R.id.dtpLBDOB);
            } else if (VariableID.equals("btnLBVDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpLBVDate);
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

            tpTime = (EditText) findViewById(R.id.txtLBTime);
            if (VariableID.equals("btnLBTime")) {
                tpTime = (EditText) findViewById(R.id.txtLBTime);
            }
            tpTime.setText(new StringBuilder().append(Global.Right("00" + hour, 2)).append(":").append(Global.Right("00" + minute, 2)));
        }
    };


    // turning off the GPS if its in on state. to avoid the battery drain.
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}