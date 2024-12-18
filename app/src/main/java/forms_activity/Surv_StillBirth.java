
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
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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
import forms_datamodel.StillBirth_DataModel;
import forms_datamodel.tmpStillBirth_DataModel;

public class Surv_StillBirth extends AppCompatActivity {
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
    LinearLayout secStillBirthID;
    View lineStillBirthID;
    TextView VlblStillBirthID;
    EditText txtStillBirthID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secSBMomID;
    View lineSBMomID;
    TextView VlblSBMomID;
    EditText txtSBMomID;
    LinearLayout secSBDate;
    View lineSBDate;
    TextView VlblSBDate;
    EditText dtpSBDate;
    LinearLayout secSBDateType;
    View lineSBDateType;
    TextView VlblSBDateType;
    RadioGroup rdogrpSBDateType;
    RadioButton rdoSBDateType1;
    RadioButton rdoSBDateType2;
    RadioButton rdoSBDateType3;
    LinearLayout secSBTime;
    View lineSBTime;
    TextView VlblSBTime;
    EditText txtSBTime;
    LinearLayout secSBTimeType;
    View lineSBTimeType;
    TextView VlblSBTimeType;
    RadioGroup rdogrpSBTimeType;
    RadioButton rdoSBTimeType1;
    RadioButton rdoSBTimeType2;
    RadioButton rdoSBTimeType3;
    LinearLayout secSBType;
    View lineSBType;
    TextView VlblSBType;
    RadioGroup rdogrpSBType;
    RadioButton rdoSBType1;
    RadioButton rdoSBType2;
    RadioButton rdoSBType3;
    RadioButton rdoSBType4;
    LinearLayout secSBSex;
    View lineSBSex;
    TextView VlblSBSex;
    RadioGroup rdogrpSBSex;
    RadioButton rdoSBSex1;
    RadioButton rdoSBSex2;
    RadioButton rdoSBSex3;
    RadioButton rdoSBSex4;
    LinearLayout secSBPlace;
    View lineSBPlace;
    TextView VlblSBPlace;
    Spinner spnSBPlace;
    LinearLayout secSBPlaceOth;
    View lineSBPlaceOth;
    TextView VlblSBPlaceOth;
    AutoCompleteTextView txtSBPlaceOth;
    LinearLayout seclbl01;
    View linelbl01;
    LinearLayout secSBDoctor;
    View lineSBDoctor;
    TextView VlblSBDoctor;
    RadioGroup rdogrpSBDoctor;
    RadioButton rdoSBDoctor1;
    RadioButton rdoSBDoctor2;
    LinearLayout secSBNurse;
    View lineSBNurse;
    TextView VlblSBNurse;
    RadioGroup rdogrpSBNurse;
    RadioButton rdoSBNurse1;
    RadioButton rdoSBNurse2;
    LinearLayout secSBMidwife;
    View lineSBMidwife;
    TextView VlblSBMidwife;
    RadioGroup rdogrpSBMidwife;
    RadioButton rdoSBMidwife1;
    RadioButton rdoSBMidwife2;
    LinearLayout secSBTBA;
    View lineSBTBA;
    TextView VlblSBTBA;
    RadioGroup rdogrpSBTBA;
    RadioButton rdoSBTBA1;
    RadioButton rdoSBTBA2;
    LinearLayout secSBCHW;
    View lineSBCHW;
    TextView VlblSBCHW;
    RadioGroup rdogrpSBCHW;
    RadioButton rdoSBCHW1;
    RadioButton rdoSBCHW2;
    LinearLayout secSBRltv;
    LinearLayout secSBNAT;
    View lineSBRltv;
    View lineSBNAT;
    TextView VlblSBRltv;
    TextView VlblSBNAT;
    RadioGroup rdogrpSBRltv;
    RadioButton rdoSBRltv1;
    RadioButton rdoSBRltv2;
    RadioGroup rdogrpSBNAT;
    RadioButton rdoSBNAT1;
    RadioButton rdoSBNAT2;
    LinearLayout secSBOth;
    View lineSBOth;
    TextView VlblSBOth;
    RadioGroup rdogrpSBOth;
    RadioButton rdoSBOth1;
    RadioButton rdoSBOth2;
    LinearLayout secSBOthSp;
    View lineSBOthSp;
    TextView VlblSBOthSp;
    AutoCompleteTextView txtSBOthSp;
    LinearLayout secSBDk;
    View lineSBDk;
    TextView VlblSBDk;
    RadioGroup rdogrpSBDk;
    RadioButton rdoSBDk1;
    RadioButton rdoSBDk2;
    LinearLayout secSBRfs;
    View lineSBRfs;
    TextView VlblSBRfs;
    RadioGroup rdogrpSBRfs;
    RadioButton rdoSBRfs1;
    RadioButton rdoSBRfs2;
    LinearLayout secSBDelType;
    View lineSBDelType;
    TextView VlblSBDelType;
    RadioGroup rdogrpSBDelType;
    RadioButton rdoSBDelType1;
    RadioButton rdoSBDelType2;
    RadioButton rdoSBDelType3;
    RadioButton rdoSBDelType4;
    RadioButton rdoSBDelType5;
    RadioButton rdoSBDelType6;
    LinearLayout secSBVDate;
    View lineSBVDate;
    TextView VlblSBVDate;
    EditText dtpSBVDate;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secSBNote;
    View lineSBNote;
    TextView VlblSBNote;
    EditText txtSBNote;

    LinearLayout secSBNote1;
    View lineSBNote1;
    TextView lblEvCode;

     int MODULEID = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String STILLBIRTHID = "";
     String DELIVERYID = "";
     String MEM_ID = "";
     String HHID = "";
     String EV_TYPE = "";
     String ROUND = "";
     String CHILD = "";
     String DOD = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.surv_stillbirth);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();
            sp = new MySharedPreferences();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            STILLBIRTHID = IDbundle.getString("StillBirthID");
            DELIVERYID = IDbundle.getString("DeliveryID");
            MEM_ID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            EV_TYPE = IDbundle.getString("evtype");
            ROUND = IDbundle.getString("round");
            CHILD = IDbundle.getString("child");
            DOD = IDbundle.getString("dod");


            TableName = "tmpStillBirth";
            MODULEID = 29;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_StillBirth.this);
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
            Connection.LocalizeLanguage(Surv_StillBirth.this, MODULEID, LANGUAGEID);


            dtpSBDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnSBDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });
            dtpSBVDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnSBVDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            txtSBTime.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnSBTime";
                        showDialog(TIME_DIALOG);
                        return true;
                    }
                    return false;
                }
            });

            //Hide all skip variables
            secSBPlaceOth.setVisibility(View.GONE);
            lineSBPlaceOth.setVisibility(View.GONE);
            secSBOthSp.setVisibility(View.GONE);
            lineSBOthSp.setVisibility(View.GONE);


            DataSearch(STILLBIRTHID);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(Surv_StillBirth.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            lblEvCode = findViewById(R.id.lblEvCode);
            lblEvCode.setText(EV_TYPE);

            secStillBirthID = (LinearLayout) findViewById(R.id.secStillBirthID);
            lineStillBirthID = (View) findViewById(R.id.lineStillBirthID);
            VlblStillBirthID = (TextView) findViewById(R.id.VlblStillBirthID);
            txtStillBirthID = (EditText) findViewById(R.id.txtStillBirthID);
            if (STILLBIRTHID.length() == 0) txtStillBirthID.setText(Global.Get_UUID(DEVICEID));
            else txtStillBirthID.setText(STILLBIRTHID);
            txtStillBirthID.setEnabled(false);

            secHHID = (LinearLayout) findViewById(R.id.secHHID);
            lineHHID = (View) findViewById(R.id.lineHHID);
            VlblHHID = (TextView) findViewById(R.id.VlblHHID);
            txtHHID = (EditText) findViewById(R.id.txtHHID);
            txtHHID.setText(HHID);
            txtHHID.setEnabled(false);
            secSBMomID = (LinearLayout) findViewById(R.id.secSBMomID);
            lineSBMomID = (View) findViewById(R.id.lineSBMomID);
            VlblSBMomID = (TextView) findViewById(R.id.VlblSBMomID);
            txtSBMomID = (EditText) findViewById(R.id.txtSBMomID);
            txtSBMomID.setText(MEM_ID);
            secSBDate = (LinearLayout) findViewById(R.id.secSBDate);
            lineSBDate = (View) findViewById(R.id.lineSBDate);
            VlblSBDate = (TextView) findViewById(R.id.VlblSBDate);
            dtpSBDate = (EditText) findViewById(R.id.dtpSBDate);
            dtpSBDate.setText(DOD);
            secSBDateType = (LinearLayout) findViewById(R.id.secSBDateType);
            lineSBDateType = (View) findViewById(R.id.lineSBDateType);
            VlblSBDateType = (TextView) findViewById(R.id.VlblSBDateType);
            rdogrpSBDateType = (RadioGroup) findViewById(R.id.rdogrpSBDateType);
            rdoSBDateType1 = (RadioButton) findViewById(R.id.rdoSBDateType1);
            rdoSBDateType2 = (RadioButton) findViewById(R.id.rdoSBDateType2);
            rdoSBDateType3 = (RadioButton) findViewById(R.id.rdoSBDateType3);
            secSBTime = (LinearLayout) findViewById(R.id.secSBTime);
            lineSBTime = (View) findViewById(R.id.lineSBTime);
            VlblSBTime = (TextView) findViewById(R.id.VlblSBTime);
            txtSBTime = (EditText) findViewById(R.id.txtSBTime);
            secSBTimeType = (LinearLayout) findViewById(R.id.secSBTimeType);
            lineSBTimeType = (View) findViewById(R.id.lineSBTimeType);
            VlblSBTimeType = (TextView) findViewById(R.id.VlblSBTimeType);
            rdogrpSBTimeType = (RadioGroup) findViewById(R.id.rdogrpSBTimeType);
            rdoSBTimeType1 = (RadioButton) findViewById(R.id.rdoSBTimeType1);
            rdoSBTimeType2 = (RadioButton) findViewById(R.id.rdoSBTimeType2);
            rdoSBTimeType3 = (RadioButton) findViewById(R.id.rdoSBTimeType3);
            secSBType = (LinearLayout) findViewById(R.id.secSBType);
            lineSBType = (View) findViewById(R.id.lineSBType);
            VlblSBType = (TextView) findViewById(R.id.VlblSBType);
            rdogrpSBType = (RadioGroup) findViewById(R.id.rdogrpSBType);
            rdoSBType1 = (RadioButton) findViewById(R.id.rdoSBType1);
            rdoSBType2 = (RadioButton) findViewById(R.id.rdoSBType2);
            rdoSBType3 = (RadioButton) findViewById(R.id.rdoSBType3);
            rdoSBType4 = (RadioButton) findViewById(R.id.rdoSBType4);
            secSBSex = (LinearLayout) findViewById(R.id.secSBSex);
            lineSBSex = (View) findViewById(R.id.lineSBSex);
            VlblSBSex = (TextView) findViewById(R.id.VlblSBSex);
            rdogrpSBSex = (RadioGroup) findViewById(R.id.rdogrpSBSex);
            rdoSBSex1 = (RadioButton) findViewById(R.id.rdoSBSex1);
            rdoSBSex2 = (RadioButton) findViewById(R.id.rdoSBSex2);
            rdoSBSex3 = (RadioButton) findViewById(R.id.rdoSBSex3);
            rdoSBSex4 = (RadioButton) findViewById(R.id.rdoSBSex4);
            secSBPlace = (LinearLayout) findViewById(R.id.secSBPlace);
            lineSBPlace = (View) findViewById(R.id.lineSBPlace);
            VlblSBPlace = (TextView) findViewById(R.id.VlblSBPlace);
            spnSBPlace = (Spinner) findViewById(R.id.spnSBPlace);
            List<String> listSBPlace = new ArrayList<String>();

            listSBPlace.add("");
            listSBPlace.add("11-Respondents home");
            listSBPlace.add("15-Other home");
            listSBPlace.add("21-Government hospital");
            listSBPlace.add("22-Government health center");
            listSBPlace.add("23-Government health post");
            listSBPlace.add("31-Private hospital/clinic");
            listSBPlace.add("32-TBA Home");
            listSBPlace.add("41-NGO hospital/clinic");
            listSBPlace.add("51-On the way to health facility");
            listSBPlace.add("52-On the way to home");
            listSBPlace.add("53-On the way to health facility to other health facility");
            listSBPlace.add("97-Other");
            listSBPlace.add("98-Dont know");
            listSBPlace.add("99-Refused to respond");
            spnSBPlace.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listSBPlace)));

            spnSBPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnSBPlace.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnSBPlace.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("97")) {
                        secSBPlaceOth.setVisibility(View.VISIBLE);
                        lineSBPlaceOth.setVisibility(View.VISIBLE);
                    } else {
                        secSBPlaceOth.setVisibility(View.GONE);
                        lineSBPlaceOth.setVisibility(View.GONE);
                        txtSBPlaceOth.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secSBPlaceOth = (LinearLayout) findViewById(R.id.secSBPlaceOth);
            lineSBPlaceOth = (View) findViewById(R.id.lineSBPlaceOth);
            VlblSBPlaceOth = (TextView) findViewById(R.id.VlblSBPlaceOth);
            txtSBPlaceOth = (AutoCompleteTextView) findViewById(R.id.txtSBPlaceOth);
            C.setupAutoComplete(TableName,txtSBPlaceOth,"SBPlaceOth"); //setup autocomplete view by event

            seclbl01 = (LinearLayout) findViewById(R.id.seclbl01);
            linelbl01 = (View) findViewById(R.id.linelbl01);
            secSBDoctor = (LinearLayout) findViewById(R.id.secSBDoctor);
            lineSBDoctor = (View) findViewById(R.id.lineSBDoctor);
            VlblSBDoctor = (TextView) findViewById(R.id.VlblSBDoctor);
            rdogrpSBDoctor = (RadioGroup) findViewById(R.id.rdogrpSBDoctor);
            rdoSBDoctor1 = (RadioButton) findViewById(R.id.rdoSBDoctor1);
            rdoSBDoctor2 = (RadioButton) findViewById(R.id.rdoSBDoctor2);
            secSBNurse = (LinearLayout) findViewById(R.id.secSBNurse);
            lineSBNurse = (View) findViewById(R.id.lineSBNurse);
            VlblSBNurse = (TextView) findViewById(R.id.VlblSBNurse);
            rdogrpSBNurse = (RadioGroup) findViewById(R.id.rdogrpSBNurse);
            rdoSBNurse1 = (RadioButton) findViewById(R.id.rdoSBNurse1);
            rdoSBNurse2 = (RadioButton) findViewById(R.id.rdoSBNurse2);
            secSBMidwife = (LinearLayout) findViewById(R.id.secSBMidwife);
            lineSBMidwife = (View) findViewById(R.id.lineSBMidwife);
            VlblSBMidwife = (TextView) findViewById(R.id.VlblSBMidwife);
            rdogrpSBMidwife = (RadioGroup) findViewById(R.id.rdogrpSBMidwife);
            rdoSBMidwife1 = (RadioButton) findViewById(R.id.rdoSBMidwife1);
            rdoSBMidwife2 = (RadioButton) findViewById(R.id.rdoSBMidwife2);
            secSBTBA = (LinearLayout) findViewById(R.id.secSBTBA);
            lineSBTBA = (View) findViewById(R.id.lineSBTBA);
            VlblSBTBA = (TextView) findViewById(R.id.VlblSBTBA);
            rdogrpSBTBA = (RadioGroup) findViewById(R.id.rdogrpSBTBA);
            rdoSBTBA1 = (RadioButton) findViewById(R.id.rdoSBTBA1);
            rdoSBTBA2 = (RadioButton) findViewById(R.id.rdoSBTBA2);
            secSBCHW = (LinearLayout) findViewById(R.id.secSBCHW);
            lineSBCHW = (View) findViewById(R.id.lineSBCHW);
            VlblSBCHW = (TextView) findViewById(R.id.VlblSBCHW);
            rdogrpSBCHW = (RadioGroup) findViewById(R.id.rdogrpSBCHW);
            rdoSBCHW1 = (RadioButton) findViewById(R.id.rdoSBCHW1);
            rdoSBCHW2 = (RadioButton) findViewById(R.id.rdoSBCHW2);
            secSBRltv = (LinearLayout) findViewById(R.id.secSBRltv);
            secSBNAT = (LinearLayout) findViewById(R.id.secSBNAT);
            lineSBRltv = (View) findViewById(R.id.lineSBRltv);
            lineSBNAT = (View) findViewById(R.id.lineSBNAT);
            VlblSBRltv = (TextView) findViewById(R.id.VlblSBRltv);
            VlblSBNAT = (TextView) findViewById(R.id.VlblSBNAT);
            rdogrpSBRltv = (RadioGroup) findViewById(R.id.rdogrpSBRltv);
            rdoSBRltv1 = (RadioButton) findViewById(R.id.rdoSBRltv1);
            rdoSBRltv2 = (RadioButton) findViewById(R.id.rdoSBRltv2);
            rdogrpSBNAT = (RadioGroup) findViewById(R.id.rdogrpSBNAT);
            rdoSBNAT1 = (RadioButton) findViewById(R.id.rdoSBNAT1);
            rdoSBNAT2 = (RadioButton) findViewById(R.id.rdoSBNAT2);
            secSBOth = (LinearLayout) findViewById(R.id.secSBOth);
            lineSBOth = (View) findViewById(R.id.lineSBOth);
            VlblSBOth = (TextView) findViewById(R.id.VlblSBOth);
            rdogrpSBOth = (RadioGroup) findViewById(R.id.rdogrpSBOth);
            rdoSBOth1 = (RadioButton) findViewById(R.id.rdoSBOth1);
            rdoSBOth2 = (RadioButton) findViewById(R.id.rdoSBOth2);

            rdogrpSBOth.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpSBOth = new String[]{"0", "1"};
                    for (int i = 0; i < rdogrpSBOth.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpSBOth.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpSBOth[i];
                    }

                    if (rbData.equalsIgnoreCase("0")) {
                        secSBOthSp.setVisibility(View.GONE);
                        lineSBOthSp.setVisibility(View.GONE);
                        txtSBOthSp.setText("");
                    } else if (rbData.equalsIgnoreCase("1")) {
                        secSBOthSp.setVisibility(View.VISIBLE);
                        lineSBOthSp.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secSBOthSp = (LinearLayout) findViewById(R.id.secSBOthSp);
            lineSBOthSp = (View) findViewById(R.id.lineSBOthSp);
            VlblSBOthSp = (TextView) findViewById(R.id.VlblSBOthSp);
            txtSBOthSp = (AutoCompleteTextView) findViewById(R.id.txtSBOthSp);
            C.setupAutoComplete(TableName,txtSBOthSp,"SBOthSp"); //setup autocomplete view by event

            secSBDk = (LinearLayout) findViewById(R.id.secSBDk);
            lineSBDk = (View) findViewById(R.id.lineSBDk);
            VlblSBDk = (TextView) findViewById(R.id.VlblSBDk);
            rdogrpSBDk = (RadioGroup) findViewById(R.id.rdogrpSBDk);
            rdoSBDk1 = (RadioButton) findViewById(R.id.rdoSBDk1);
            rdoSBDk2 = (RadioButton) findViewById(R.id.rdoSBDk2);
            secSBRfs = (LinearLayout) findViewById(R.id.secSBRfs);
            lineSBRfs = (View) findViewById(R.id.lineSBRfs);
            VlblSBRfs = (TextView) findViewById(R.id.VlblSBRfs);
            rdogrpSBRfs = (RadioGroup) findViewById(R.id.rdogrpSBRfs);
            rdoSBRfs1 = (RadioButton) findViewById(R.id.rdoSBRfs1);
            rdoSBRfs2 = (RadioButton) findViewById(R.id.rdoSBRfs2);
            secSBDelType = (LinearLayout) findViewById(R.id.secSBDelType);
            lineSBDelType = (View) findViewById(R.id.lineSBDelType);
            VlblSBDelType = (TextView) findViewById(R.id.VlblSBDelType);
            rdogrpSBDelType = (RadioGroup) findViewById(R.id.rdogrpSBDelType);
            rdoSBDelType1 = (RadioButton) findViewById(R.id.rdoSBDelType1);
            rdoSBDelType2 = (RadioButton) findViewById(R.id.rdoSBDelType2);
            rdoSBDelType3 = (RadioButton) findViewById(R.id.rdoSBDelType3);
            rdoSBDelType4 = (RadioButton) findViewById(R.id.rdoSBDelType4);
            rdoSBDelType5 = (RadioButton) findViewById(R.id.rdoSBDelType5);
            rdoSBDelType6 = (RadioButton) findViewById(R.id.rdoSBDelType6);
            secSBVDate = (LinearLayout) findViewById(R.id.secSBVDate);
            lineSBVDate = (View) findViewById(R.id.lineSBVDate);
            VlblSBVDate = (TextView) findViewById(R.id.VlblSBVDate);
            dtpSBVDate = (EditText) findViewById(R.id.dtpSBVDate);
            dtpSBVDate.setText(Global.DateNowDMY());
            secRnd = (LinearLayout) findViewById(R.id.secRnd);
            lineRnd = (View) findViewById(R.id.lineRnd);
            VlblRnd = (TextView) findViewById(R.id.VlblRnd);
            txtRnd = (EditText) findViewById(R.id.txtRnd);
            txtRnd.setText(ROUND);
            txtRnd.setEnabled(false);
            secSBNote = (LinearLayout) findViewById(R.id.secSBNote);
            lineSBNote = (View) findViewById(R.id.lineSBNote);
            secSBNote1 = (LinearLayout) findViewById(R.id.secSBNote1);
            lineSBNote1 = (View) findViewById(R.id.lineSBNote1);
            VlblSBNote = (TextView) findViewById(R.id.VlblSBNote);
            txtSBNote = (EditText) findViewById(R.id.txtSBNote);
        } catch (Exception e) {
            Connection.MessageBox(Surv_StillBirth.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(Surv_StillBirth.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            tmpStillBirth_DataModel objSave = new tmpStillBirth_DataModel();
            objSave.setStillBirthID(txtStillBirthID.getText().toString());
            objSave.setDeliveryID(DELIVERYID);
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setSBMomID(txtSBMomID.getText().toString());
            objSave.setSBDate(dtpSBDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpSBDate.getText().toString()) : dtpSBDate.getText().toString());
            String[] d_rdogrpSBDateType = new String[]{"1", "2", "8"};
            objSave.setSBDateType("");
            for (int i = 0; i < rdogrpSBDateType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBDateType.getChildAt(i);
                if (rb.isChecked()) objSave.setSBDateType(d_rdogrpSBDateType[i]);
            }

            objSave.setSBTime(txtSBTime.getText().toString());
            String[] d_rdogrpSBTimeType = new String[]{"1", "2", "8"};
            objSave.setSBTimeType("");
            for (int i = 0; i < rdogrpSBTimeType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBTimeType.getChildAt(i);
                if (rb.isChecked()) objSave.setSBTimeType(d_rdogrpSBTimeType[i]);
            }

            String[] d_rdogrpSBType = new String[]{"1", "2", "3", "8"};
            objSave.setSBType("");
            for (int i = 0; i < rdogrpSBType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBType.getChildAt(i);
                if (rb.isChecked()) objSave.setSBType(d_rdogrpSBType[i]);
            }

            String[] d_rdogrpSBSex = new String[]{"1", "2", "3", "8"};
            objSave.setSBSex("");
            for (int i = 0; i < rdogrpSBSex.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBSex.getChildAt(i);
                if (rb.isChecked()) objSave.setSBSex(d_rdogrpSBSex[i]);
            }

            objSave.setSBPlace(spnSBPlace.getSelectedItemPosition() == 0 ? "" : spnSBPlace.getSelectedItem().toString().split("-")[0]);
            objSave.setSBPlaceOth(txtSBPlaceOth.getText().toString());
            String[] d_rdogrpSBDoctor = new String[]{"0", "1"};
            objSave.setSBDoctor("");
            for (int i = 0; i < rdogrpSBDoctor.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBDoctor.getChildAt(i);
                if (rb.isChecked()) objSave.setSBDoctor(d_rdogrpSBDoctor[i]);
            }

            String[] d_rdogrpSBNurse = new String[]{"0", "1"};
            objSave.setSBNurse("");
            for (int i = 0; i < rdogrpSBNurse.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBNurse.getChildAt(i);
                if (rb.isChecked()) objSave.setSBNurse(d_rdogrpSBNurse[i]);
            }

            String[] d_rdogrpSBMidwife = new String[]{"0", "1"};
            objSave.setSBMidwife("");
            for (int i = 0; i < rdogrpSBMidwife.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBMidwife.getChildAt(i);
                if (rb.isChecked()) objSave.setSBMidwife(d_rdogrpSBMidwife[i]);
            }

            String[] d_rdogrpSBTBA = new String[]{"0", "1"};
            objSave.setSBTBA("");
            for (int i = 0; i < rdogrpSBTBA.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBTBA.getChildAt(i);
                if (rb.isChecked()) objSave.setSBTBA(d_rdogrpSBTBA[i]);
            }

            String[] d_rdogrpSBCHW = new String[]{"0", "1"};
            objSave.setSBCHW("");
            for (int i = 0; i < rdogrpSBCHW.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBCHW.getChildAt(i);
                if (rb.isChecked()) objSave.setSBCHW(d_rdogrpSBCHW[i]);
            }

            String[] d_rdogrpSBRltv = new String[]{"0", "1"};
            objSave.setSBRltv("");
            for (int i = 0; i < rdogrpSBRltv.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBRltv.getChildAt(i);
                if (rb.isChecked()) objSave.setSBRltv(d_rdogrpSBRltv[i]);
            }

            //Newly added
            String[] d_rdogrpSBNAT = new String[]{"0", "1"};
            objSave.setSBNAT("");
            for (int i = 0; i < rdogrpSBNAT.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBNAT.getChildAt(i);
                if (rb.isChecked()) objSave.setSBNAT(d_rdogrpSBNAT[i]);
            }

            String[] d_rdogrpSBOth = new String[]{"0", "1"};
            objSave.setSBOth("");
            for (int i = 0; i < rdogrpSBOth.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBOth.getChildAt(i);
                if (rb.isChecked()) objSave.setSBOth(d_rdogrpSBOth[i]);
            }

            objSave.setSBOthSp(txtSBOthSp.getText().toString());
            String[] d_rdogrpSBDk = new String[]{"0", "1"};
            objSave.setSBDk("");
            for (int i = 0; i < rdogrpSBDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBDk.getChildAt(i);
                if (rb.isChecked()) objSave.setSBDk(d_rdogrpSBDk[i]);
            }

            String[] d_rdogrpSBRfs = new String[]{"0", "1"};
            objSave.setSBRfs("");
            for (int i = 0; i < rdogrpSBRfs.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBRfs.getChildAt(i);
                if (rb.isChecked()) objSave.setSBRfs(d_rdogrpSBRfs[i]);
            }

            String[] d_rdogrpSBDelType = new String[]{"1", "2", "3", "4", "5", "8"};
            objSave.setSBDelType("");
            for (int i = 0; i < rdogrpSBDelType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSBDelType.getChildAt(i);
                if (rb.isChecked()) objSave.setSBDelType(d_rdogrpSBDelType[i]);
            }

            objSave.setSBVDate(dtpSBVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpSBVDate.getText().toString()) : dtpSBVDate.getText().toString());
            objSave.setRnd(txtRnd.getText().toString());
            objSave.setSBNote(txtSBNote.getText().toString());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);
            if (status.length() == 0) {
                if (CHILD.equals("1")) {
                    sp.save(this, "Stl1", "complete");
                } else if (CHILD.equals("2")) {
                    sp.save(this, "Stl2", "complete");
                } else if (CHILD.equals("3")) {
                    sp.save(this, "Stl3", "complete");
                } else if (CHILD.equals("4")) {
                    sp.save(this, "Stl4", "complete");
                } else if (CHILD.equals("5")) {
                    sp.save(this, "Stl5", "complete");
                } else if (CHILD.equals("6")) {
                    sp.save(this, "Stl6", "complete");
                } else if (CHILD.equals("7")) {
                    sp.save(this, "Stl7", "complete");
                }

                Intent returnIntent = new Intent();
                returnIntent.putExtra("res", "");
                setResult(Activity.RESULT_OK, returnIntent);

                Toast.makeText(Surv_StillBirth.this, "Save Successfully...", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(Surv_StillBirth.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_StillBirth.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        try {
            ResetSectionColor();
            if (txtStillBirthID.getText().toString().length() == 0 & secStillBirthID.isShown()) {
                ValidationMsg += "\nRequired field: Still Birth internal ID.";
                secStillBirthID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtHHID.getText().toString().length() == 0 & secHHID.isShown()) {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtSBMomID.getText().toString().length() == 0 & secSBMomID.isShown()) {
                ValidationMsg += "\nRequired field: Mother ID of the household.";
                secSBMomID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpSBDate.getText().toString());
            if (DV.length() != 0 & secSBDate.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: What was the date of still birth?.";
                secSBDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSBDateType1.isChecked() & !rdoSBDateType2.isChecked() & !rdoSBDateType3.isChecked() & secSBDateType.isShown()) {
                ValidationMsg += "\nRequired field: Still birth date Type.";
                secSBDateType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtSBTime.getText().length() == 0 & secSBTime.isShown()) {
                ValidationMsg += "\nRequired field: What was the time of delivery?.";
                secSBTime.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSBTimeType1.isChecked() & !rdoSBTimeType2.isChecked() & !rdoSBTimeType3.isChecked() & secSBTimeType.isShown()) {
                ValidationMsg += "\nRequired field: Still birth Time Type.";
                secSBTimeType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
//         if(!rdoSBType1.isChecked() & !rdoSBType2.isChecked() & !rdoSBType3.isChecked() & !rdoSBType4.isChecked() & secSBType.isShown())
//           {
//             ValidationMsg += "\nRequired field: What was the type of still birth?.";
//             secSBType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
            if (!rdoSBSex1.isChecked() & !rdoSBSex2.isChecked() & !rdoSBSex3.isChecked() & !rdoSBSex4.isChecked() & secSBSex.isShown()) {
                ValidationMsg += "\nRequired field: What was the sex of still birth?.";
                secSBSex.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnSBPlace.getSelectedItemPosition() == 0 & secSBPlace.isShown()) {
                ValidationMsg += "\nRequired field: What was the place of still birth?.";
                secSBPlace.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtSBPlaceOth.getText().toString().length() == 0 & secSBPlaceOth.isShown()) {
                ValidationMsg += "\nRequired field: Other place specify.";
                secSBPlaceOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSBDoctor1.isChecked() & !rdoSBDoctor2.isChecked() & secSBDoctor.isShown()) {
                ValidationMsg += "\nA. Required field: Doctor.";
                secSBDoctor.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSBNurse1.isChecked() & !rdoSBNurse2.isChecked() & secSBNurse.isShown()) {
                ValidationMsg += "\nB. Required field: Nurse.";
                secSBNurse.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSBMidwife1.isChecked() & !rdoSBMidwife2.isChecked() & secSBMidwife.isShown()) {
                ValidationMsg += "\nC. Required field: Midwife/Paramedic.";
                secSBMidwife.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSBNAT1.isChecked() & !rdoSBNAT2.isChecked() & secSBNAT.isShown()) {
                ValidationMsg += "\nC. Required field: No attendant.";
                secSBNAT.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSBTBA1.isChecked() & !rdoSBTBA2.isChecked() & secSBTBA.isShown()) {
                ValidationMsg += "\nD. Required field: Traditional birth attendant.";
                secSBTBA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSBCHW1.isChecked() & !rdoSBCHW2.isChecked() & secSBCHW.isShown()) {
                ValidationMsg += "\nE. Required field: Community health worker.";
                secSBCHW.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSBRltv1.isChecked() & !rdoSBRltv2.isChecked() & secSBRltv.isShown()) {
                ValidationMsg += "\nF. Required field: Relative/friend.";
                secSBRltv.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSBOth1.isChecked() & !rdoSBOth2.isChecked() & secSBOth.isShown()) {
                ValidationMsg += "\nG. Required field: Other.";
                secSBOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtSBOthSp.getText().toString().length() == 0 & secSBOthSp.isShown()) {
                ValidationMsg += "\nRequired field: Other specify.";
                secSBOthSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSBDk1.isChecked() & !rdoSBDk2.isChecked() & secSBDk.isShown()) {
                ValidationMsg += "\nH. Required field: Dont know.";
                secSBDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSBRfs1.isChecked() & !rdoSBRfs2.isChecked() & secSBRfs.isShown()) {
                ValidationMsg += "\nI. Required field: Refused to respond.";
                secSBRfs.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSBDelType1.isChecked() & !rdoSBDelType2.isChecked() & !rdoSBDelType3.isChecked() & !rdoSBDelType4.isChecked() & !rdoSBDelType5.isChecked() & !rdoSBDelType6.isChecked() & secSBDelType.isShown()) {
                ValidationMsg += "\nRequired field: What was the mode of delivery?.";
                secSBDelType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpSBVDate.getText().toString());
            if (DV.length() != 0 & secSBVDate.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: Visit date.";
                secSBVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtRnd.getText().toString().length() == 0 & secRnd.isShown()) {
                ValidationMsg += "\nRequired field: Round number.";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor() {
        try {
            secStillBirthID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secSBMomID.setBackgroundColor(Color.WHITE);
            secSBDate.setBackgroundColor(Color.WHITE);
            secSBDateType.setBackgroundColor(Color.WHITE);
            secSBTime.setBackgroundColor(Color.WHITE);
            secSBTimeType.setBackgroundColor(Color.WHITE);
            secSBType.setBackgroundColor(Color.WHITE);
            secSBSex.setBackgroundColor(Color.WHITE);
            secSBPlace.setBackgroundColor(Color.WHITE);
            secSBPlaceOth.setBackgroundColor(Color.WHITE);
            secSBDoctor.setBackgroundColor(Color.WHITE);
            secSBNurse.setBackgroundColor(Color.WHITE);
            secSBMidwife.setBackgroundColor(Color.WHITE);
            secSBTBA.setBackgroundColor(Color.WHITE);
            secSBCHW.setBackgroundColor(Color.WHITE);
            secSBRltv.setBackgroundColor(Color.WHITE);
            secSBOth.setBackgroundColor(Color.WHITE);
            secSBOthSp.setBackgroundColor(Color.WHITE);
            secSBDk.setBackgroundColor(Color.WHITE);
            secSBRfs.setBackgroundColor(Color.WHITE);
            secSBDelType.setBackgroundColor(Color.WHITE);
            secSBVDate.setBackgroundColor(Color.WHITE);
            secRnd.setBackgroundColor(Color.WHITE);
            secSBNote.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String StillBirthID) {
        try {
            RadioButton rb;
            StillBirth_DataModel d = new StillBirth_DataModel();
            String SQL = "Select * from " + TableName + "  Where StillBirthID='" + StillBirthID + "'";
            List<StillBirth_DataModel> data = d.SelectAll(this, SQL);
            for (StillBirth_DataModel item : data) {
                txtStillBirthID.setText(item.getStillBirthID());
                txtHHID.setText(item.getHHID());
                txtSBMomID.setText(item.getSBMomID());
                dtpSBDate.setText(item.getSBDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getSBDate()));
                String[] d_rdogrpSBDateType = new String[]{"1", "2", "8"};
                for (int i = 0; i < d_rdogrpSBDateType.length; i++) {
                    if (String.valueOf(item.getSBDateType()).equals(String.valueOf(d_rdogrpSBDateType[i]))) {
                        rb = (RadioButton) rdogrpSBDateType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtSBTime.setText(item.getSBTime());
                String[] d_rdogrpSBTimeType = new String[]{"1", "2", "8"};
                for (int i = 0; i < d_rdogrpSBTimeType.length; i++) {
                    if (String.valueOf(item.getSBTimeType()).equals(String.valueOf(d_rdogrpSBTimeType[i]))) {
                        rb = (RadioButton) rdogrpSBTimeType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSBType = new String[]{"1", "2", "3", "8"};
                for (int i = 0; i < d_rdogrpSBType.length; i++) {
                    if (String.valueOf(item.getSBType()).equals(String.valueOf(d_rdogrpSBType[i]))) {
                        rb = (RadioButton) rdogrpSBType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSBSex = new String[]{"1", "2", "3", "8"};
                for (int i = 0; i < d_rdogrpSBSex.length; i++) {
                    if (String.valueOf(item.getSBSex()).equals(String.valueOf(d_rdogrpSBSex[i]))) {
                        rb = (RadioButton) rdogrpSBSex.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnSBPlace.setSelection(Global.SpinnerItemPositionAnyLength(spnSBPlace, String.valueOf(item.getSBPlace())));
                txtSBPlaceOth.setText(item.getSBPlaceOth());
                String[] d_rdogrpSBDoctor = new String[]{"0", "1"};
                for (int i = 0; i < d_rdogrpSBDoctor.length; i++) {
                    if (String.valueOf(item.getSBDoctor()).equals(String.valueOf(d_rdogrpSBDoctor[i]))) {
                        rb = (RadioButton) rdogrpSBDoctor.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSBNurse = new String[]{"0", "1"};
                for (int i = 0; i < d_rdogrpSBNurse.length; i++) {
                    if (String.valueOf(item.getSBNurse()).equals(String.valueOf(d_rdogrpSBNurse[i]))) {
                        rb = (RadioButton) rdogrpSBNurse.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSBMidwife = new String[]{"0", "1"};
                for (int i = 0; i < d_rdogrpSBMidwife.length; i++) {
                    if (String.valueOf(item.getSBMidwife()).equals(String.valueOf(d_rdogrpSBMidwife[i]))) {
                        rb = (RadioButton) rdogrpSBMidwife.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSBTBA = new String[]{"0", "1"};
                for (int i = 0; i < d_rdogrpSBTBA.length; i++) {
                    if (String.valueOf(item.getSBTBA()).equals(String.valueOf(d_rdogrpSBTBA[i]))) {
                        rb = (RadioButton) rdogrpSBTBA.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSBCHW = new String[]{"0", "1"};
                for (int i = 0; i < d_rdogrpSBCHW.length; i++) {
                    if (String.valueOf(item.getSBCHW()).equals(String.valueOf(d_rdogrpSBCHW[i]))) {
                        rb = (RadioButton) rdogrpSBCHW.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSBRltv = new String[]{"0", "1"};
                for (int i = 0; i < d_rdogrpSBRltv.length; i++) {
                    if (String.valueOf(item.getSBRltv()).equals(String.valueOf(d_rdogrpSBRltv[i]))) {
                        rb = (RadioButton) rdogrpSBRltv.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSBNAT = new String[]{"0", "1"};
                for (int i = 0; i < d_rdogrpSBNAT.length; i++) {
                    if (String.valueOf(item.getSBNAT()).equals(String.valueOf(d_rdogrpSBNAT[i]))) {
                        rb = (RadioButton) rdogrpSBNAT.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSBOth = new String[]{"0", "1"};
                for (int i = 0; i < d_rdogrpSBOth.length; i++) {
                    if (String.valueOf(item.getSBOth()).equals(String.valueOf(d_rdogrpSBOth[i]))) {
                        rb = (RadioButton) rdogrpSBOth.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtSBOthSp.setText(item.getSBOthSp());
                String[] d_rdogrpSBDk = new String[]{"0", "1"};
                for (int i = 0; i < d_rdogrpSBDk.length; i++) {
                    if (String.valueOf(item.getSBDk()).equals(String.valueOf(d_rdogrpSBDk[i]))) {
                        rb = (RadioButton) rdogrpSBDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSBRfs = new String[]{"0", "1"};
                for (int i = 0; i < d_rdogrpSBRfs.length; i++) {
                    if (String.valueOf(item.getSBRfs()).equals(String.valueOf(d_rdogrpSBRfs[i]))) {
                        rb = (RadioButton) rdogrpSBRfs.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSBDelType = new String[]{"1", "2", "3", "4", "5", "8"};
                for (int i = 0; i < d_rdogrpSBDelType.length; i++) {
                    if (String.valueOf(item.getSBDelType()).equals(String.valueOf(d_rdogrpSBDelType[i]))) {
                        rb = (RadioButton) rdogrpSBDelType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                dtpSBVDate.setText(item.getSBVDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getSBVDate()));
                txtRnd.setText(item.getRnd());
                txtSBNote.setText(item.getSBNote());
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_StillBirth.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpSBDate);
            if (VariableID.equals("btnSBDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpSBDate);
            } else if (VariableID.equals("btnSBVDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpSBVDate);
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

            tpTime = (EditText) findViewById(R.id.txtSBTime);
            if (VariableID.equals("btnSBTime")) {
                tpTime = (EditText) findViewById(R.id.txtSBTime);
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