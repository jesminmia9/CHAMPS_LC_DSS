
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
import forms_datamodel.NBC_ANCDetail_DataModel;

public class NBC_ANCDetail extends AppCompatActivity {
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
    LinearLayout secANCSl;
    View lineANCSl;
    TextView VlblANCSl;
    EditText txtANCSl;
    LinearLayout secANCDate;
    View lineANCDate;
    TextView VlblANCDate;
    EditText dtpANCDate;
    LinearLayout secANCDateDk;
    View lineANCDateDk;
    TextView VlblANCDateDk;
    RadioGroup rdogrpANCDateDk;
    RadioButton rdoANCDateDk1;
    RadioButton rdoANCDateDk2;
    LinearLayout secANCGAge;
    View lineANCGAge;
    TextView VlblANCGAge;
    EditText txtANCGAge;
    LinearLayout secANCProv;
    View lineANCProv;
    TextView VlblANCProv;
    RadioGroup rdogrpANCProv;
    RadioButton rdoANCProv1;
    RadioButton rdoANCProv2;
    RadioButton rdoANCProv3;
    RadioButton rdoANCProv4;
    RadioButton rdoANCProv5;
    RadioButton rdoANCProv6;
    RadioButton rdoANCProv7;
    RadioButton rdoANCProv8;
    RadioButton rdoANCProv9;
    LinearLayout secANCProvOth;
    View lineANCProvOth;
    TextView VlblANCProvOth;
    AutoCompleteTextView txtANCProvOth;
    LinearLayout secANCPlace;
    View lineANCPlace;
    TextView VlblANCPlace;
    RadioGroup rdogrpANCPlace;
    RadioButton rdoANCPlace1;
    RadioButton rdoANCPlace2;
    RadioButton rdoANCPlace3;
    RadioButton rdoANCPlace4;
    RadioButton rdoANCPlace5;
    RadioButton rdoANCPlace6;
    RadioButton rdoANCPlace7;
    RadioButton rdoANCPlace8;
    RadioButton rdoANCPlace9;
    RadioButton rdoANCPlace10;
    RadioButton rdoANCPlace11;
    LinearLayout secANCPlaceOth;
    View lineANCPlaceOth;
    TextView VlblANCPlaceOth;
    AutoCompleteTextView txtANCPlaceOth;
    LinearLayout secANCRes;
    View lineANCRes;
    TextView VlblANCRes;
    RadioGroup rdogrpANCRes;
    RadioButton rdoANCRes1;
    RadioButton rdoANCRes2;
    RadioButton rdoANCRes3;

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
     String ANCSL = "";
     String isEDIT = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.nbc_ancdetail);
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
            ANCSL = IDbundle.getString("ANCSl");
            isEDIT = IDbundle.getString("isEDIT");

            TableName = "NBC_ANCDetail";
            MODULEID = 43;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(NBC_ANCDetail.this);
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
            Connection.LocalizeLanguage(NBC_ANCDetail.this, MODULEID, LANGUAGEID);

            dtpANCDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnANCDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            //Hide all skip variables
            secANCProvOth.setVisibility(View.GONE);
            lineANCProvOth.setVisibility(View.GONE);
            secANCPlaceOth.setVisibility(View.GONE);
            lineANCPlaceOth.setVisibility(View.GONE);


            DataSearch(MEMID, HHID, PGN, ANCSL);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(NBC_ANCDetail.this, e.getMessage());
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
            secANCSl = (LinearLayout) findViewById(R.id.secANCSl);
            lineANCSl = (View) findViewById(R.id.lineANCSl);
            VlblANCSl = (TextView) findViewById(R.id.VlblANCSl);
            txtANCSl = (EditText) findViewById(R.id.txtANCSl);
            if (ANCSL.length() == 0) txtANCSl.setText(NewANCSerial(MEMID, HHID, PGN));
            else txtANCSl.setText(ANCSL);
            txtANCSl.setEnabled(false);
            secANCDate = (LinearLayout) findViewById(R.id.secANCDate);
            lineANCDate = (View) findViewById(R.id.lineANCDate);
            VlblANCDate = (TextView) findViewById(R.id.VlblANCDate);
            dtpANCDate = (EditText) findViewById(R.id.dtpANCDate);
            secANCDateDk = (LinearLayout) findViewById(R.id.secANCDateDk);
            lineANCDateDk = (View) findViewById(R.id.lineANCDateDk);
            VlblANCDateDk = (TextView) findViewById(R.id.VlblANCDateDk);
            rdogrpANCDateDk = (RadioGroup) findViewById(R.id.rdogrpANCDateDk);
            rdoANCDateDk1 = (RadioButton) findViewById(R.id.rdoANCDateDk1);
            rdoANCDateDk2 = (RadioButton) findViewById(R.id.rdoANCDateDk2);

            dtpANCDate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (dtpANCDate.getText().toString().length() > 0) {
                        if (rdoANCDateDk1.isChecked() || rdoANCDateDk2.isChecked()) {
                            rdogrpANCDateDk.clearCheck();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            rdogrpANCDateDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpANCDateDk = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpANCDateDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpANCDateDk.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpANCDateDk[i];

                        if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2")) {
                            if (dtpANCDate.getText().toString().length() > 0) {
                                dtpANCDate.setText("");
                            }
                        }

                    }

                }
            });

            secANCGAge = (LinearLayout) findViewById(R.id.secANCGAge);
            lineANCGAge = (View) findViewById(R.id.lineANCGAge);
            VlblANCGAge = (TextView) findViewById(R.id.VlblANCGAge);
            txtANCGAge = (EditText) findViewById(R.id.txtANCGAge);
            secANCProv = (LinearLayout) findViewById(R.id.secANCProv);
            lineANCProv = (View) findViewById(R.id.lineANCProv);
            VlblANCProv = (TextView) findViewById(R.id.VlblANCProv);
            rdogrpANCProv = (RadioGroup) findViewById(R.id.rdogrpANCProv);
            rdoANCProv1 = (RadioButton) findViewById(R.id.rdoANCProv1);
            rdoANCProv2 = (RadioButton) findViewById(R.id.rdoANCProv2);
            rdoANCProv3 = (RadioButton) findViewById(R.id.rdoANCProv3);
            rdoANCProv4 = (RadioButton) findViewById(R.id.rdoANCProv4);
            rdoANCProv5 = (RadioButton) findViewById(R.id.rdoANCProv5);
            rdoANCProv6 = (RadioButton) findViewById(R.id.rdoANCProv6);
            rdoANCProv7 = (RadioButton) findViewById(R.id.rdoANCProv7);
            rdoANCProv8 = (RadioButton) findViewById(R.id.rdoANCProv8);
            rdogrpANCProv.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpANCProv = new String[]{"21", "22", "23", "31", "32", "97", "98", "99", ""};
                    for (int i = 0; i < rdogrpANCProv.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpANCProv.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpANCProv[i];
                    }

                    if (rbData.equalsIgnoreCase("97")) {
                        secANCProvOth.setVisibility(View.VISIBLE);
                        lineANCProvOth.setVisibility(View.VISIBLE);
                    } else {
                        secANCProvOth.setVisibility(View.GONE);
                        lineANCProvOth.setVisibility(View.GONE);
                        txtANCProvOth.setText("");
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secANCProvOth = (LinearLayout) findViewById(R.id.secANCProvOth);
            lineANCProvOth = (View) findViewById(R.id.lineANCProvOth);
            VlblANCProvOth = (TextView) findViewById(R.id.VlblANCProvOth);
            txtANCProvOth = (AutoCompleteTextView) findViewById(R.id.txtANCProvOth);
            C.setupAutoComplete(TableName,txtANCProvOth,"ANCProvOth"); //setup autocomplete view by event
            secANCPlace = (LinearLayout) findViewById(R.id.secANCPlace);
            lineANCPlace = (View) findViewById(R.id.lineANCPlace);
            VlblANCPlace = (TextView) findViewById(R.id.VlblANCPlace);
            rdogrpANCPlace = (RadioGroup) findViewById(R.id.rdogrpANCPlace);
            rdoANCPlace1 = (RadioButton) findViewById(R.id.rdoANCPlace1);
            rdoANCPlace2 = (RadioButton) findViewById(R.id.rdoANCPlace2);
            rdoANCPlace3 = (RadioButton) findViewById(R.id.rdoANCPlace3);
            rdoANCPlace4 = (RadioButton) findViewById(R.id.rdoANCPlace4);
            rdoANCPlace5 = (RadioButton) findViewById(R.id.rdoANCPlace5);
            rdoANCPlace6 = (RadioButton) findViewById(R.id.rdoANCPlace6);
            rdoANCPlace7 = (RadioButton) findViewById(R.id.rdoANCPlace7);
            rdoANCPlace8 = (RadioButton) findViewById(R.id.rdoANCPlace8);
            rdoANCPlace9 = (RadioButton) findViewById(R.id.rdoANCPlace9);
            rdoANCPlace10 = (RadioButton) findViewById(R.id.rdoANCPlace10);
            rdoANCPlace11 = (RadioButton) findViewById(R.id.rdoANCPlace11);
            rdogrpANCPlace.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpANCPlace = new String[]{"11", "15", "21", "22", "23", "31", "32", "41", "97", "98", "99"};
                    for (int i = 0; i < rdogrpANCPlace.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpANCPlace.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpANCPlace[i];
                    }

                    if (rbData.equalsIgnoreCase("97")) {
                        secANCPlaceOth.setVisibility(View.VISIBLE);
                        lineANCPlaceOth.setVisibility(View.VISIBLE);
                    } else {
                        secANCPlaceOth.setVisibility(View.GONE);
                        lineANCPlaceOth.setVisibility(View.GONE);
                        txtANCPlaceOth.setText("");
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secANCPlaceOth = (LinearLayout) findViewById(R.id.secANCPlaceOth);
            lineANCPlaceOth = (View) findViewById(R.id.lineANCPlaceOth);
            VlblANCPlaceOth = (TextView) findViewById(R.id.VlblANCPlaceOth);
            txtANCPlaceOth = (AutoCompleteTextView) findViewById(R.id.txtANCPlaceOth);
            C.setupAutoComplete(TableName,txtANCPlaceOth,"ANCPlaceOth"); //setup autocomplete view by event

            secANCRes = (LinearLayout) findViewById(R.id.secANCRes);
            lineANCRes = (View) findViewById(R.id.lineANCRes);
            VlblANCRes = (TextView) findViewById(R.id.VlblANCRes);
            rdogrpANCRes = (RadioGroup) findViewById(R.id.rdogrpANCRes);
            rdoANCRes1 = (RadioButton) findViewById(R.id.rdoANCRes1);
            rdoANCRes2 = (RadioButton) findViewById(R.id.rdoANCRes2);
            rdoANCRes3 = (RadioButton) findViewById(R.id.rdoANCRes3);
        } catch (Exception e) {
            Connection.MessageBox(NBC_ANCDetail.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(NBC_ANCDetail.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            NBC_ANCDetail_DataModel objSave = new NBC_ANCDetail_DataModel();
            objSave.setNBID(txtNBID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setPGN(txtPGN.getText().toString());
            objSave.setANCSl(txtANCSl.getText().toString());
            objSave.setANCDate(dtpANCDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpANCDate.getText().toString()) : dtpANCDate.getText().toString());
            String[] d_rdogrpANCDateDk = new String[]{"8", "9"};
            objSave.setANCDateDk("");
            for (int i = 0; i < rdogrpANCDateDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpANCDateDk.getChildAt(i);
                if (rb.isChecked()) objSave.setANCDateDk(d_rdogrpANCDateDk[i]);
            }

            objSave.setANCGAge(txtANCGAge.getText().toString());
            String[] d_rdogrpANCProv = new String[]{"21", "22", "23", "31", "32", "97", "98", "99"};
            objSave.setANCProv("");
            for (int i = 0; i < rdogrpANCProv.getChildCount(); i++) {
                rb = (RadioButton) rdogrpANCProv.getChildAt(i);
                if (rb.isChecked()) objSave.setANCProv(d_rdogrpANCProv[i]);
            }

            objSave.setANCProvOth(txtANCProvOth.getText().toString());
            String[] d_rdogrpANCPlace = new String[]{"11", "15", "21", "22", "23", "31", "32", "41", "97", "98", "99"};
            objSave.setANCPlace("");
            for (int i = 0; i < rdogrpANCPlace.getChildCount(); i++) {
                rb = (RadioButton) rdogrpANCPlace.getChildAt(i);
                if (rb.isChecked()) objSave.setANCPlace(d_rdogrpANCPlace[i]);
            }

            objSave.setANCPlaceOth(txtANCPlaceOth.getText().toString());
            String[] d_rdogrpANCRes = new String[]{"1", "2", "3"};
            objSave.setANCRes("");
            for (int i = 0; i < rdogrpANCRes.getChildCount(); i++) {
                rb = (RadioButton) rdogrpANCRes.getChildAt(i);
                if (rb.isChecked()) objSave.setANCRes(d_rdogrpANCRes[i]);
            }

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

                Toast.makeText(NBC_ANCDetail.this, "Save Successfully...", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(NBC_ANCDetail.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_ANCDetail.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        String lastANCDate = "";
        String pickedANCDate = "";
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
            if (txtANCSl.getText().toString().length() == 0 & secANCSl.isShown()) {
                ValidationMsg += "\nRequired field: ANC Serial.";
                secANCSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secANCSl.isShown() & (Integer.valueOf(txtANCSl.getText().toString().length() == 0 ? "0" : txtANCSl.getText().toString()) < 0 || Integer.valueOf(txtANCSl.getText().toString().length() == 0 ? "20" : txtANCSl.getText().toString()) > 20)) {
                ValidationMsg += "\nValue should be between 0 and 20(ANC Serial).";
                secANCSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpANCDate.getText().toString());

            int anc_serial = Integer.parseInt(txtANCSl.getText().toString().length()==0?"0":txtANCSl.getText().toString());
            lastANCDate = C.ReturnSingleValue("Select ifnull(ANCDate,'') from NBC_ANCDetail where MemID='"+ MEMID +"' and HHID='"+ HHID +"' and PGN='"+ PGN +"' and Cast(ANCSl as int)<"+ anc_serial +" and isdelete=2 order by Cast(ANCSl as int) desc limit 1");
            //lastANCDate = C.ReturnSingleValue("SELECT COALESCE(MAX(DATE(ANCDate)), '') AS max_date FROM NBC_ANCDetail Where MemID = '" + MEMID + "' and HHID = '" + HHID + "' and PGN = '" + PGN + "' and isdelete=2");
            pickedANCDate = Global.DateConvertYMD(dtpANCDate.getText().toString());
            int isPickedDateAfterPrevious = Global.DateDifferenceDays(dtpANCDate.getText().toString(),Global.DateConvertDMY(lastANCDate));
            if (!rdoANCDateDk1.isChecked() & !rdoANCDateDk2.isChecked() & DV.length() != 0 & secANCDate.isShown() & secANCDateDk.isShown()) {
                ValidationMsg += "\nB2.8 Required field/Not a valid date format: Date of ANC check-up.";
                secANCDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                ValidationMsg += "\nRequired field: ANC Date Don't know.";
                secANCDateDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (lastANCDate.length() != 0 && isPickedDateAfterPrevious<0 & !rdoANCDateDk1.isChecked() & !rdoANCDateDk2.isChecked()){
                ValidationMsg += "\nB2.8 New ANC Date should be bigger than last ANC Date["+ Global.DateConvertDMY(lastANCDate) +"]";
                secANCDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }


            if (txtANCGAge.getText().toString().length() == 0 & secANCGAge.isShown()) {
                ValidationMsg += "\nB2.9 Required field: Gestational age (Week).";
                secANCGAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secANCGAge.isShown() &
                    (Integer.valueOf(txtANCGAge.getText().toString().length() == 0 ? "0" : txtANCGAge.getText().toString()) < 0 || Integer.valueOf(txtANCGAge.getText().toString().length() == 0 ? "42" : txtANCGAge.getText().toString()) > 42)
                    && !txtANCGAge.getText().toString().equals("98") && !txtANCGAge.getText().toString().equals("99")) {
                ValidationMsg += "\nB2.9 Value should be between 0 and 42,98,99(Gestational age (Week)).";
                secANCGAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoANCProv1.isChecked() & !rdoANCProv2.isChecked() & !rdoANCProv3.isChecked() & !rdoANCProv4.isChecked() & !rdoANCProv5.isChecked() & !rdoANCProv6.isChecked() & !rdoANCProv7.isChecked() & !rdoANCProv8.isChecked() & secANCProv.isShown()) {
                ValidationMsg += "\nB2.10 Required field: ANC Provider.";
                secANCProv.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtANCProvOth.getText().toString().length() == 0 & secANCProvOth.isShown()) {
                ValidationMsg += "\nRequired field: Other ANC Provider.";
                secANCProvOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoANCPlace1.isChecked() & !rdoANCPlace2.isChecked() & !rdoANCPlace3.isChecked() & !rdoANCPlace4.isChecked() & !rdoANCPlace5.isChecked() & !rdoANCPlace6.isChecked() & !rdoANCPlace7.isChecked() & !rdoANCPlace8.isChecked() & !rdoANCPlace9.isChecked() & !rdoANCPlace10.isChecked() & !rdoANCPlace11.isChecked() & secANCPlace.isShown()) {
                ValidationMsg += "\nB2.11 Required field: Where did you see your antenatal care checkup? (Write facility name and record code).";
                secANCPlace.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtANCPlaceOth.getText().toString().length() == 0 & secANCPlaceOth.isShown()) {
                ValidationMsg += "\nRequired field: Other ANC Place.";
                secANCPlaceOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoANCRes1.isChecked() & !rdoANCRes2.isChecked() & !rdoANCRes3.isChecked() & secANCRes.isShown()) {
                ValidationMsg += "\nB2.12 Required field: Why did you visit?.";
                secANCRes.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
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
            secANCSl.setBackgroundColor(Color.WHITE);
            secANCSl.setBackgroundColor(Color.WHITE);
            secANCDate.setBackgroundColor(Color.WHITE);
            secANCDateDk.setBackgroundColor(Color.WHITE);
            secANCGAge.setBackgroundColor(Color.WHITE);
            secANCGAge.setBackgroundColor(Color.WHITE);
            secANCProv.setBackgroundColor(Color.WHITE);
            secANCProvOth.setBackgroundColor(Color.WHITE);
            secANCPlace.setBackgroundColor(Color.WHITE);
            secANCPlaceOth.setBackgroundColor(Color.WHITE);
            secANCRes.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String MEMID, String HHID, String PGN, String ANCSl) {
        try {
            RadioButton rb;
            NBC_ANCDetail_DataModel d = new NBC_ANCDetail_DataModel();
            String SQL = "Select * from " + TableName + "  Where MemID='" + MEMID + "' and HHID = '" + HHID + "' and PGN='" + PGN + "' and ANCSl='" + ANCSl + "'";
            List<NBC_ANCDetail_DataModel> data = d.SelectAll(this, SQL);
            for (NBC_ANCDetail_DataModel item : data) {
                txtNBID.setText(item.getNBID());
                txtMemID.setText(item.getMemID());
                txtHHID.setText(item.getHHID());
                txtPGN.setText(item.getPGN());
                txtANCSl.setText(String.valueOf(item.getANCSl()));
                dtpANCDate.setText(item.getANCDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getANCDate()));
                String[] d_rdogrpANCDateDk = new String[]{"8", "9"};
                for (int i = 0; i < d_rdogrpANCDateDk.length; i++) {
                    if (String.valueOf(item.getANCDateDk()).equals(String.valueOf(d_rdogrpANCDateDk[i]))) {
                        rb = (RadioButton) rdogrpANCDateDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtANCGAge.setText(String.valueOf(item.getANCGAge()));
                String[] d_rdogrpANCProv = new String[]{"21", "22", "23", "31", "32", "97", "98", "99"};
                for (int i = 0; i < d_rdogrpANCProv.length; i++) {
                    if (String.valueOf(item.getANCProv()).equals(String.valueOf(d_rdogrpANCProv[i]))) {
                        rb = (RadioButton) rdogrpANCProv.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtANCProvOth.setText(item.getANCProvOth());
                String[] d_rdogrpANCPlace = new String[]{"11", "15", "21", "22", "23", "31", "32", "41", "97", "98", "99"};
                for (int i = 0; i < d_rdogrpANCPlace.length; i++) {
                    if (String.valueOf(item.getANCPlace()).equals(String.valueOf(d_rdogrpANCPlace[i]))) {
                        rb = (RadioButton) rdogrpANCPlace.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtANCPlaceOth.setText(item.getANCPlaceOth());
                String[] d_rdogrpANCRes = new String[]{"1", "2", "3"};
                for (int i = 0; i < d_rdogrpANCRes.length; i++) {
                    if (String.valueOf(item.getANCRes()).equals(String.valueOf(d_rdogrpANCRes[i]))) {
                        rb = (RadioButton) rdogrpANCRes.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_ANCDetail.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpANCDate);
            if (VariableID.equals("btnANCDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpANCDate);
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

    //Generate New ANC Serial
    public String NewANCSerial(String MEMID, String HHID, String PGN) {
        String M = C.ReturnSingleValue("Select cast(ifnull(max(ANCSl),0)+1 as varchar(2))ANCSL from NBC_ANCDetail where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='" + PGN + "'");
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