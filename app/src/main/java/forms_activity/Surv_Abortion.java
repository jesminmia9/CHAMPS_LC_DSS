
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
import android.widget.ArrayAdapter;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.icddrb.champs_lc_dss.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import Common.Connection;
import Common.Global;
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import forms_datamodel.tmpAbortion_DataModel;

public class Surv_Abortion extends AppCompatActivity {
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
    LinearLayout secAbortionID;
    View lineAbortionID;
    TextView VlblAbortionID;
    EditText txtAbortionID;
    LinearLayout secDeliveryID;
    View lineDeliveryID;
    TextView VlblDeliveryID;
    EditText txtDeliveryID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secAbMomID;
    View lineAbMomID;
    TextView VlblAbMomID;
    EditText txtAbMomID;
    LinearLayout secAbDate;
    View lineAbDate;
    TextView VlblAbDate;
    EditText dtpAbDate;
    LinearLayout secAbDateType;
    View lineAbDateType;
    TextView VlblAbDateType;
    RadioGroup rdogrpAbDateType;
    RadioButton rdoAbDateType1;
    RadioButton rdoAbDateType2;
    RadioButton rdoAbDateType3;
    LinearLayout secAbTime;
    View lineAbTime;
    TextView VlblAbTime;
    EditText txtAbTime;
    LinearLayout secAbTimeType;
    View lineAbTimeType;
    TextView VlblAbTimeType;
    RadioGroup rdogrpAbTimeType;
    RadioButton rdoAbTimeType1;
    RadioButton rdoAbTimeType2;
    RadioButton rdoAbTimeType3;
    LinearLayout secAbPlace;
    View lineAbPlace;
    TextView VlblAbPlace;
    Spinner spnAbPlace;
    LinearLayout secAbPlaceOth;
    View lineAbPlaceOth;
    TextView VlblAbPlaceOth;
    AutoCompleteTextView txtAbPlaceOth;
    LinearLayout secAbType;
    View lineAbType;
    TextView VlblAbType;
    RadioGroup rdogrpAbType;
    RadioButton rdoAbType1;
    RadioButton rdoAbType2;
    RadioButton rdoAbType3;
    LinearLayout seclbl01;
    View linelbl01;
    LinearLayout secAbDoctor;
    View lineAbDoctor;
    TextView VlblAbDoctor;
    CheckBox chkAbDoctor;
    LinearLayout secAbNurse;
    View lineAbNurse;
    TextView VlblAbNurse;
    CheckBox chkAbNurse;
    LinearLayout secAbMidwifeParamedic;
    View lineAbMidwifeParamedic;
    TextView VlblAbMidwifeParamedic;
    CheckBox chkAbMidwifeParamedic;
    LinearLayout secAbTBA;
    View lineAbTBA;
    TextView VlblAbTBA;
    CheckBox chkAbTBA;
    LinearLayout secAbCHW;
    View lineAbCHW;
    TextView VlblAbCHW;
    CheckBox chkAbCHW;
    LinearLayout secAbRelativeFriend;
    View lineAbRelativeFriend;
    TextView VlblAbRelativeFriend;
    CheckBox chkAbRelativeFriend;
    LinearLayout secAbOther;
    View lineAbOther;
    TextView VlblAbOther;
    CheckBox chkAbOther;
    LinearLayout secAbOthSpec;
    View lineAbOthSpec;
    TextView VlblAbOthSpec;
    AutoCompleteTextView txtAbOthSpec;
    LinearLayout secAbDK;
    View lineAbDK;
    TextView VlblAbDK;
    CheckBox chkAbDK;
    View lineSBNAT;
    LinearLayout secSBNAT;
    TextView VlblSBNAT;
    CheckBox chkNAT;
    LinearLayout secAbRR;
    View lineAbRR;
    TextView VlblAbRR;
    CheckBox chkAbRR;
    LinearLayout secAbNote;
    View lineAbNote;
    TextView VlblAbNote;
    EditText txtAbNote;

     int MODULEID = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String ABORTIONID = "";
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
            setContentView(R.layout.abortion);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            ABORTIONID = IDbundle.getString("AbortionID");
            DELIVERYID = IDbundle.getString("DeliveryID");
            MEM_ID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            EV_TYPE = IDbundle.getString("evtype");
            ROUND = IDbundle.getString("round");

            TableName = "tmpAbortion";
            MODULEID = 0;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Abortion.this);
                    adb.setTitle("Close");
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
            Connection.LocalizeLanguage(Surv_Abortion.this, MODULEID, LANGUAGEID);


            dtpAbDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnAbDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            txtAbTime.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnAbTime";
                        showDialog(TIME_DIALOG);
                        return true;
                    }
                    return false;
                }
            });

            //Hide all skip variables
            secAbOthSpec.setVisibility(View.GONE);
            lineAbOthSpec.setVisibility(View.GONE);


            DataSearch(ABORTIONID);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(Surv_Abortion.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            secAbortionID = (LinearLayout) findViewById(R.id.secAbortionID);
            lineAbortionID = (View) findViewById(R.id.lineAbortionID);
            VlblAbortionID = (TextView) findViewById(R.id.VlblAbortionID);
            txtAbortionID = (EditText) findViewById(R.id.txtAbortionID);
//         txtAbortionID.setText(ABORTIONID);
//         txtAbortionID.setEnabled(false);
            if (ABORTIONID.length() == 0) txtAbortionID.setText(Global.Get_UUID(DEVICEID));
            else txtAbortionID.setText(ABORTIONID);
            txtAbortionID.setEnabled(false);
            secDeliveryID = (LinearLayout) findViewById(R.id.secDeliveryID);
            lineDeliveryID = (View) findViewById(R.id.lineDeliveryID);
            VlblDeliveryID = (TextView) findViewById(R.id.VlblDeliveryID);
            txtDeliveryID = (EditText) findViewById(R.id.txtDeliveryID);
            txtDeliveryID.setText(DELIVERYID);
            txtDeliveryID.setEnabled(false);
            secHHID = (LinearLayout) findViewById(R.id.secHHID);
            lineHHID = (View) findViewById(R.id.lineHHID);
            VlblHHID = (TextView) findViewById(R.id.VlblHHID);
            txtHHID = (EditText) findViewById(R.id.txtHHID);
            txtHHID.setText(HHID);
            txtHHID.setEnabled(false);
            secAbMomID = (LinearLayout) findViewById(R.id.secAbMomID);
            lineAbMomID = (View) findViewById(R.id.lineAbMomID);
            VlblAbMomID = (TextView) findViewById(R.id.VlblAbMomID);
            txtAbMomID = (EditText) findViewById(R.id.txtAbMomID);
            txtAbMomID.setText(MEM_ID);
            txtAbMomID.setEnabled(false);
            secAbDate = (LinearLayout) findViewById(R.id.secAbDate);
            lineAbDate = (View) findViewById(R.id.lineAbDate);
            VlblAbDate = (TextView) findViewById(R.id.VlblAbDate);
            dtpAbDate = (EditText) findViewById(R.id.dtpAbDate);
            secAbDateType = (LinearLayout) findViewById(R.id.secAbDateType);
            lineAbDateType = (View) findViewById(R.id.lineAbDateType);
            VlblAbDateType = (TextView) findViewById(R.id.VlblAbDateType);
            rdogrpAbDateType = (RadioGroup) findViewById(R.id.rdogrpAbDateType);
            rdoAbDateType1 = (RadioButton) findViewById(R.id.rdoAbDateType1);
            rdoAbDateType2 = (RadioButton) findViewById(R.id.rdoAbDateType2);
            rdoAbDateType3 = (RadioButton) findViewById(R.id.rdoAbDateType3);
            secAbTime = (LinearLayout) findViewById(R.id.secAbTime);
            lineAbTime = (View) findViewById(R.id.lineAbTime);
            VlblAbTime = (TextView) findViewById(R.id.VlblAbTime);
            txtAbTime = (EditText) findViewById(R.id.txtAbTime);
            secAbTimeType = (LinearLayout) findViewById(R.id.secAbTimeType);
            lineAbTimeType = (View) findViewById(R.id.lineAbTimeType);
            VlblAbTimeType = (TextView) findViewById(R.id.VlblAbTimeType);
            rdogrpAbTimeType = (RadioGroup) findViewById(R.id.rdogrpAbTimeType);
            rdoAbTimeType1 = (RadioButton) findViewById(R.id.rdoAbTimeType1);
            rdoAbTimeType2 = (RadioButton) findViewById(R.id.rdoAbTimeType2);
            rdoAbTimeType3 = (RadioButton) findViewById(R.id.rdoAbTimeType3);
            secAbPlace = (LinearLayout) findViewById(R.id.secAbPlace);
            lineAbPlace = (View) findViewById(R.id.lineAbPlace);
            VlblAbPlace = (TextView) findViewById(R.id.VlblAbPlace);
            spnAbPlace = (Spinner) findViewById(R.id.spnAbPlace);
            List<String> listAbPlace = new ArrayList<String>();

            listAbPlace.add("");
            listAbPlace.add("11-Own home");
            listAbPlace.add("15-Other home");
            listAbPlace.add("21-Government hospital");
            listAbPlace.add("22-Government health center");
            listAbPlace.add("23-Government health post");
            listAbPlace.add("31-Private hospital/clinic");
            listAbPlace.add("32-TBA Home");
            listAbPlace.add("41-NGO hospital/clinic");
            listAbPlace.add("51-On the way to health facility");
            listAbPlace.add("52-On the way to home");
            listAbPlace.add("53-On the way to health facility to other health facility");
            listAbPlace.add("97-Other");
            listAbPlace.add("98-Dont know");
            listAbPlace.add("99-Refused to respond");
            ArrayAdapter<String> adptrAbPlace = new ArrayAdapter<String>(this, R.layout.multiline_spinner_dropdown_item, listAbPlace);
            spnAbPlace.setAdapter(adptrAbPlace);

            spnAbPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnAbPlace.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnAbPlace.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("97")) {
                        secAbPlaceOth.setVisibility(View.VISIBLE);
                        lineAbPlaceOth.setVisibility(View.VISIBLE);
                    } else {
                        secAbPlaceOth.setVisibility(View.GONE);
                        lineAbPlaceOth.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secAbPlaceOth = (LinearLayout) findViewById(R.id.secAbPlaceOth);
            lineAbPlaceOth = (View) findViewById(R.id.lineAbPlaceOth);
            VlblAbPlaceOth = (TextView) findViewById(R.id.VlblAbPlaceOth);
            txtAbPlaceOth = (AutoCompleteTextView) findViewById(R.id.txtAbPlaceOth);
            C.setupAutoComplete(TableName,txtAbPlaceOth,"AbPlaceOth"); //setup autocomplete view by event

            secAbType = (LinearLayout) findViewById(R.id.secAbType);
            lineAbType = (View) findViewById(R.id.lineAbType);
            VlblAbType = (TextView) findViewById(R.id.VlblAbType);
            rdogrpAbType = (RadioGroup) findViewById(R.id.rdogrpAbType);
            rdoAbType1 = (RadioButton) findViewById(R.id.rdoAbType1);
            rdoAbType2 = (RadioButton) findViewById(R.id.rdoAbType2);
            rdoAbType3 = (RadioButton) findViewById(R.id.rdoAbType3);
            seclbl01 = (LinearLayout) findViewById(R.id.seclbl01);
            linelbl01 = (View) findViewById(R.id.linelbl01);
            secAbDoctor = (LinearLayout) findViewById(R.id.secAbDoctor);
            lineAbDoctor = (View) findViewById(R.id.lineAbDoctor);
            VlblAbDoctor = (TextView) findViewById(R.id.VlblAbDoctor);
            chkAbDoctor = (CheckBox) findViewById(R.id.chkAbDoctor);
            secAbNurse = (LinearLayout) findViewById(R.id.secAbNurse);
            lineAbNurse = (View) findViewById(R.id.lineAbNurse);
            VlblAbNurse = (TextView) findViewById(R.id.VlblAbNurse);
            chkAbNurse = (CheckBox) findViewById(R.id.chkAbNurse);
            secAbMidwifeParamedic = (LinearLayout) findViewById(R.id.secAbMidwifeParamedic);
            lineAbMidwifeParamedic = (View) findViewById(R.id.lineAbMidwifeParamedic);
            VlblAbMidwifeParamedic = (TextView) findViewById(R.id.VlblAbMidwifeParamedic);
            chkAbMidwifeParamedic = (CheckBox) findViewById(R.id.chkAbMidwifeParamedic);
            secAbTBA = (LinearLayout) findViewById(R.id.secAbTBA);
            lineAbTBA = (View) findViewById(R.id.lineAbTBA);
            VlblAbTBA = (TextView) findViewById(R.id.VlblAbTBA);
            chkAbTBA = (CheckBox) findViewById(R.id.chkAbTBA);
            secAbCHW = (LinearLayout) findViewById(R.id.secAbCHW);
            lineAbCHW = (View) findViewById(R.id.lineAbCHW);
            VlblAbCHW = (TextView) findViewById(R.id.VlblAbCHW);
            chkAbCHW = (CheckBox) findViewById(R.id.chkAbCHW);
            secAbRelativeFriend = (LinearLayout) findViewById(R.id.secAbRelativeFriend);
            lineAbRelativeFriend = (View) findViewById(R.id.lineAbRelativeFriend);
            VlblAbRelativeFriend = (TextView) findViewById(R.id.VlblAbRelativeFriend);
            chkAbRelativeFriend = (CheckBox) findViewById(R.id.chkAbRelativeFriend);
            secAbOther = (LinearLayout) findViewById(R.id.secAbOther);
            lineAbOther = (View) findViewById(R.id.lineAbOther);
            VlblAbOther = (TextView) findViewById(R.id.VlblAbOther);
            chkAbOther = (CheckBox) findViewById(R.id.chkAbOther);
            chkAbOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        secAbOthSpec.setVisibility(View.VISIBLE);
                        lineAbOthSpec.setVisibility(View.VISIBLE);
                    } else {
                        secAbOthSpec.setVisibility(View.GONE);
                        lineAbOthSpec.setVisibility(View.GONE);
                        txtAbOthSpec.setText("");
                    }
                }
            });
            secAbOthSpec = (LinearLayout) findViewById(R.id.secAbOthSpec);
            lineAbOthSpec = (View) findViewById(R.id.lineAbOthSpec);
            VlblAbOthSpec = (TextView) findViewById(R.id.VlblAbOthSpec);
            txtAbOthSpec = (AutoCompleteTextView) findViewById(R.id.txtAbOthSpec);
            C.setupAutoComplete(TableName,txtAbOthSpec,"AbOthSpec"); //setup autocomplete view by event


            secAbDK = (LinearLayout) findViewById(R.id.secAbDK);
            lineAbDK = (View) findViewById(R.id.lineAbDK);
            VlblAbDK = (TextView) findViewById(R.id.VlblAbDK);
            chkAbDK = (CheckBox) findViewById(R.id.chkAbDK);
            secSBNAT = (LinearLayout) findViewById(R.id.secSBNAT);
            VlblSBNAT = (TextView) findViewById(R.id.VlblSBNAT);
            lineSBNAT = (View) findViewById(R.id.lineSBNAT);
            chkNAT = (CheckBox) findViewById(R.id.chkNAT);
            secAbRR = (LinearLayout) findViewById(R.id.secAbRR);
            lineAbRR = (View) findViewById(R.id.lineAbRR);
            VlblAbRR = (TextView) findViewById(R.id.VlblAbRR);
            chkAbRR = (CheckBox) findViewById(R.id.chkAbRR);
            secAbNote = (LinearLayout) findViewById(R.id.secAbNote);
            lineAbNote = (View) findViewById(R.id.lineAbNote);
            VlblAbNote = (TextView) findViewById(R.id.VlblAbNote);
            txtAbNote = (EditText) findViewById(R.id.txtAbNote);
        } catch (Exception e) {
            Connection.MessageBox(Surv_Abortion.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(Surv_Abortion.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            tmpAbortion_DataModel objSave = new tmpAbortion_DataModel();
            objSave.setAbortionID(txtAbortionID.getText().toString());
            objSave.setDeliveryID(txtDeliveryID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setAbMomID(txtAbMomID.getText().toString());
            objSave.setAbDate(dtpAbDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpAbDate.getText().toString()) : dtpAbDate.getText().toString());
            String[] d_rdogrpAbDateType = new String[]{"01", "02", "98"};
            objSave.setAbDateType("");
            for (int i = 0; i < rdogrpAbDateType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpAbDateType.getChildAt(i);
                if (rb.isChecked()) objSave.setAbDateType(d_rdogrpAbDateType[i]);
            }

            objSave.setAbTime(txtAbTime.getText().toString());
            String[] d_rdogrpAbTimeType = new String[]{"01", "02", "98"};
            objSave.setAbTimeType("");
            for (int i = 0; i < rdogrpAbTimeType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpAbTimeType.getChildAt(i);
                if (rb.isChecked()) objSave.setAbTimeType(d_rdogrpAbTimeType[i]);
            }

            objSave.setAbPlace(spnAbPlace.getSelectedItemPosition() == 0 ? "" : spnAbPlace.getSelectedItem().toString().split("-")[0]);
            objSave.setAbPlaceOth(txtAbPlaceOth.getText().toString());
            String[] d_rdogrpAbType = new String[]{"01", "02", "03"};
            objSave.setAbType("");
            for (int i = 0; i < rdogrpAbType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpAbType.getChildAt(i);
                if (rb.isChecked()) objSave.setAbType(d_rdogrpAbType[i]);
            }

            objSave.setAbDoctor((chkAbDoctor.isChecked() ? "1" : "2"));
            objSave.setAbNurse((chkAbNurse.isChecked() ? "1" : "2"));
            objSave.setAbMidwifeParamedic((chkAbMidwifeParamedic.isChecked() ? "1" : "2"));
            objSave.setAbTBA((chkAbTBA.isChecked() ? "1" : "2"));
            objSave.setAbCHW((chkAbCHW.isChecked() ? "1" : "2"));
            objSave.setAbRelativeFriend((chkAbRelativeFriend.isChecked() ? "1" : "2"));
            objSave.setAbOther((chkAbOther.isChecked() ? "1" : "2"));
            objSave.setAbOthSpec(txtAbOthSpec.getText().toString());
            objSave.setAbDK((chkAbDK.isChecked() ? "1" : "2"));
            objSave.setAbRR((chkAbRR.isChecked() ? "1" : "2"));

            //Newly added,
            objSave.setNAT((chkNAT.isChecked() ? "1" : (secSBNAT.isShown() ? "2" : "")));

            objSave.setAbNote(txtAbNote.getText().toString());
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

                Connection.MessageBox(Surv_Abortion.this, "Saved Successfully");
            } else {
                Connection.MessageBox(Surv_Abortion.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_Abortion.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        try {
            ResetSectionColor();
            if (txtAbortionID.getText().toString().length() == 0 & secAbortionID.isShown()) {
                ValidationMsg += "\nRequired field: Abortion internal ID.";
                secAbortionID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtDeliveryID.getText().toString().length() == 0 & secDeliveryID.isShown()) {
                ValidationMsg += "\nRequired field: DeliveryID.";
                secDeliveryID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtHHID.getText().toString().length() == 0 & secHHID.isShown()) {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtAbMomID.getText().toString().length() == 0 & secAbMomID.isShown()) {
                ValidationMsg += "\nRequired field: Mother ID of the household.";
                secAbMomID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpAbDate.getText().toString());
            if (DV.length() != 0 & secAbDate.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: What was the date of  Abortion?.";
                secAbDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoAbDateType1.isChecked() & !rdoAbDateType2.isChecked() & !rdoAbDateType3.isChecked() & secAbDateType.isShown()) {
                ValidationMsg += "\nRequired field: Abortion date Type.";
                secAbDateType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtAbTime.getText().length() == 0 & secAbTime.isShown()) {
                ValidationMsg += "\nRequired field: What was the time of Abortion?.";
                secAbTime.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoAbTimeType1.isChecked() & !rdoAbTimeType2.isChecked() & !rdoAbTimeType3.isChecked() & secAbTimeType.isShown()) {
                ValidationMsg += "\nRequired field: Abortion Time Type.";
                secAbTimeType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnAbPlace.getSelectedItemPosition() == 0 & secAbPlace.isShown()) {
                ValidationMsg += "\nRequired field: What was the place of abortion ?.";
                secAbPlace.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtAbPlaceOth.getText().toString().length() == 0 & secAbPlaceOth.isShown()) {
                ValidationMsg += "\nRequired field: Specify other  place of abortion.";
                secAbPlaceOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoAbType1.isChecked() & !rdoAbType2.isChecked() & !rdoAbType3.isChecked() & secAbType.isShown()) {
                ValidationMsg += "\nRequired field: What was the type of abortion ?.";
                secAbType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtAbOthSpec.getText().toString().length() == 0 & secAbOthSpec.isShown()) {
                ValidationMsg += "\nRequired field: Specify other health personnel.";
                secAbOthSpec.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtAbNote.getText().toString().length() == 0 & secAbNote.isShown()) {
                ValidationMsg += "\nRequired field: Note.";
                secAbNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor() {
        try {
            secAbortionID.setBackgroundColor(Color.WHITE);
            secDeliveryID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secAbMomID.setBackgroundColor(Color.WHITE);
            secAbDate.setBackgroundColor(Color.WHITE);
            secAbDateType.setBackgroundColor(Color.WHITE);
            secAbTime.setBackgroundColor(Color.WHITE);
            secAbTimeType.setBackgroundColor(Color.WHITE);
            secAbPlace.setBackgroundColor(Color.WHITE);
            secAbPlaceOth.setBackgroundColor(Color.WHITE);
            secAbType.setBackgroundColor(Color.WHITE);
            secAbOthSpec.setBackgroundColor(Color.WHITE);
            secAbNote.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String AbortionID) {
        try {
            RadioButton rb;
            tmpAbortion_DataModel d = new tmpAbortion_DataModel();
            String SQL = "Select * from " + TableName + "  Where AbortionID='" + AbortionID + "'";
            List<tmpAbortion_DataModel> data = d.SelectAll(this, SQL);
            for (tmpAbortion_DataModel item : data) {
                txtAbortionID.setText(item.getAbortionID());
                txtDeliveryID.setText(item.getDeliveryID());
                txtHHID.setText(item.getHHID());
                txtAbMomID.setText(item.getAbMomID());
                dtpAbDate.setText(item.getAbDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getAbDate()));
                String[] d_rdogrpAbDateType = new String[]{"01", "02", "98"};
                for (int i = 0; i < d_rdogrpAbDateType.length; i++) {
                    if (String.valueOf(item.getAbDateType()).equals(String.valueOf(d_rdogrpAbDateType[i]))) {
                        rb = (RadioButton) rdogrpAbDateType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtAbTime.setText(item.getAbTime());
                String[] d_rdogrpAbTimeType = new String[]{"01", "02", "98"};
                for (int i = 0; i < d_rdogrpAbTimeType.length; i++) {
                    if (String.valueOf(item.getAbTimeType()).equals(String.valueOf(d_rdogrpAbTimeType[i]))) {
                        rb = (RadioButton) rdogrpAbTimeType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnAbPlace.setSelection(Global.SpinnerItemPositionAnyLength(spnAbPlace, String.valueOf(item.getAbPlace())));
                txtAbPlaceOth.setText(item.getAbPlaceOth());
                String[] d_rdogrpAbType = new String[]{"01", "02", "03"};
                for (int i = 0; i < d_rdogrpAbType.length; i++) {
                    if (String.valueOf(item.getAbType()).equals(String.valueOf(d_rdogrpAbType[i]))) {
                        rb = (RadioButton) rdogrpAbType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                if (String.valueOf(item.getAbDoctor()).equals("1")) {
                    chkAbDoctor.setChecked(true);
                } else if (String.valueOf(item.getAbDoctor()).equals("2")) {
                    chkAbDoctor.setChecked(false);
                }
                if (String.valueOf(item.getAbNurse()).equals("1")) {
                    chkAbNurse.setChecked(true);
                } else if (String.valueOf(item.getAbNurse()).equals("2")) {
                    chkAbNurse.setChecked(false);
                }
                if (String.valueOf(item.getAbMidwifeParamedic()).equals("1")) {
                    chkAbMidwifeParamedic.setChecked(true);
                } else if (String.valueOf(item.getAbMidwifeParamedic()).equals("2")) {
                    chkAbMidwifeParamedic.setChecked(false);
                }
                if (String.valueOf(item.getAbTBA()).equals("1")) {
                    chkAbTBA.setChecked(true);
                } else if (String.valueOf(item.getAbTBA()).equals("2")) {
                    chkAbTBA.setChecked(false);
                }
                if (String.valueOf(item.getAbCHW()).equals("1")) {
                    chkAbCHW.setChecked(true);
                } else if (String.valueOf(item.getAbCHW()).equals("2")) {
                    chkAbCHW.setChecked(false);
                }
                if (String.valueOf(item.getAbRelativeFriend()).equals("1")) {
                    chkAbRelativeFriend.setChecked(true);
                } else if (String.valueOf(item.getAbRelativeFriend()).equals("2")) {
                    chkAbRelativeFriend.setChecked(false);
                }
                if (String.valueOf(item.getAbOther()).equals("1")) {
                    chkAbOther.setChecked(true);
                } else if (String.valueOf(item.getAbOther()).equals("2")) {
                    chkAbOther.setChecked(false);
                }
                txtAbOthSpec.setText(item.getAbOthSpec());
                if (String.valueOf(item.getAbDK()).equals("1")) {
                    chkAbDK.setChecked(true);
                } else if (String.valueOf(item.getAbDK()).equals("2")) {
                    chkAbDK.setChecked(false);
                }
                if (String.valueOf(item.getAbRR()).equals("1")) {
                    chkAbRR.setChecked(true);
                } else if (String.valueOf(item.getAbRR()).equals("2")) {
                    chkAbRR.setChecked(false);
                }
                if (String.valueOf(item.getNAT()).equals("1")) {
                    chkNAT.setChecked(true);
                } else if (String.valueOf(item.getNAT()).equals("2")) {
                    chkNAT.setChecked(false);
                }
                txtAbNote.setText(item.getAbNote());
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_Abortion.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpAbDate);
            if (VariableID.equals("btnAbDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpAbDate);
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

            tpTime = (EditText) findViewById(R.id.txtAbTime);
            if (VariableID.equals("btnAbTime")) {
                tpTime = (EditText) findViewById(R.id.txtAbTime);
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