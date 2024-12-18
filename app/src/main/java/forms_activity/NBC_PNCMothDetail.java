
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
import forms_datamodel.NBC_PNCMothDetail_DataModel;

public class NBC_PNCMothDetail extends AppCompatActivity {
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
    LinearLayout secPNCSl;
    View linePNCSl;
    TextView VlblPNCSl;
    EditText txtPNCSl;
    LinearLayout seclbl01;
    View linelbl01;
    LinearLayout secPNCDate;
    View linePNCDate;
    TextView VlblPNCDate;
    EditText dtpPNCDate;
    LinearLayout secPNCDateDk;
    View linePNCDateDk;
    TextView VlblPNCDateDk;
    RadioGroup rdogrpPNCDateDk;
    RadioButton rdoPNCDateDk1;
    RadioButton rdoPNCDateDk2;
    LinearLayout secPNCProv;
    View linePNCProv;
    TextView VlblPNCProv;
    RadioGroup rdogrpPNCProv;
    RadioButton rdoPNCProv1;
    RadioButton rdoPNCProv2;
    RadioButton rdoPNCProv3;
    RadioButton rdoPNCProv4;
    RadioButton rdoPNCProv5;
    RadioButton rdoPNCProv6;
    RadioButton rdoPNCProv7;
    RadioButton rdoPNCProv8;
    LinearLayout secPNCProvOth;
    View linePNCProvOth;
    TextView VlblPNCProvOth;
    AutoCompleteTextView txtPNCProvOth;
    LinearLayout secPNCPlace;
    View linePNCPlace;
    TextView VlblPNCPlace;
    RadioGroup rdogrpPNCPlace;
    RadioButton rdoPNCPlace1;
    RadioButton rdoPNCPlace2;
    RadioButton rdoPNCPlace3;
    RadioButton rdoPNCPlace4;
    RadioButton rdoPNCPlace5;
    RadioButton rdoPNCPlace6;
    RadioButton rdoPNCPlace7;
    RadioButton rdoPNCPlace8;
    RadioButton rdoPNCPlace9;
    RadioButton rdoPNCPlace10;
    RadioButton rdoPNCPlace11;
    LinearLayout secPNCPlaceOth;
    View linePNCPlaceOth;
    TextView VlblPNCPlaceOth;
    AutoCompleteTextView txtPNCPlaceOth;
    LinearLayout secPNCRes;
    View linePNCRes;
    TextView VlblPNCRes;
    RadioGroup rdogrpPNCRes;
    RadioButton rdoPNCRes1;
    RadioButton rdoPNCRes2;
    RadioButton rdoPNCRes3;

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
     String PGN = "";
     String ROUND = "";
     String PNCSL = "";
     String isEDIT = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.nbc_pncmothdetail);
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
            PNCSL = IDbundle.getString("PNCSl");
            isEDIT = IDbundle.getString("isEDIT");


            TableName = "NBC_PNCMothDetail";
            MODULEID = 45;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(NBC_PNCMothDetail.this);
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
            Connection.LocalizeLanguage(NBC_PNCMothDetail.this, MODULEID, LANGUAGEID);


            dtpPNCDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnPNCDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            //Hide all skip variables
            secPNCProvOth.setVisibility(View.GONE);
            linePNCProvOth.setVisibility(View.GONE);
            secPNCPlaceOth.setVisibility(View.GONE);
            linePNCPlaceOth.setVisibility(View.GONE);


            DataSearch(MEMID, HHID, PGN, PNCSL);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCMothDetail.this, e.getMessage());
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
            secPNCSl = (LinearLayout) findViewById(R.id.secPNCSl);
            linePNCSl = (View) findViewById(R.id.linePNCSl);
            VlblPNCSl = (TextView) findViewById(R.id.VlblPNCSl);
            txtPNCSl = (EditText) findViewById(R.id.txtPNCSl);
            if (PNCSL.length() == 0) txtPNCSl.setText(NewPNCSerial(MEMID, HHID, PGN));
            else txtPNCSl.setText(PNCSL);
            txtPNCSl.setEnabled(false);
            seclbl01 = (LinearLayout) findViewById(R.id.seclbl01);
            linelbl01 = (View) findViewById(R.id.linelbl01);
            secPNCDate = (LinearLayout) findViewById(R.id.secPNCDate);
            linePNCDate = (View) findViewById(R.id.linePNCDate);
            VlblPNCDate = (TextView) findViewById(R.id.VlblPNCDate);
            dtpPNCDate = (EditText) findViewById(R.id.dtpPNCDate);
            secPNCDateDk = (LinearLayout) findViewById(R.id.secPNCDateDk);
            linePNCDateDk = (View) findViewById(R.id.linePNCDateDk);
            VlblPNCDateDk = (TextView) findViewById(R.id.VlblPNCDateDk);
            rdogrpPNCDateDk = (RadioGroup) findViewById(R.id.rdogrpPNCDateDk);
            rdoPNCDateDk1 = (RadioButton) findViewById(R.id.rdoPNCDateDk1);
            rdoPNCDateDk2 = (RadioButton) findViewById(R.id.rdoPNCDateDk2);

            dtpPNCDate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (dtpPNCDate.getText().toString().length() > 0) {
                        if (rdoPNCDateDk1.isChecked() || rdoPNCDateDk2.isChecked()) {
                            rdogrpPNCDateDk.clearCheck();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            rdogrpPNCDateDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpPNCDateDk = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpPNCDateDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpPNCDateDk.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpPNCDateDk[i];

                        if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2")) {
                            if (dtpPNCDate.getText().toString().length() > 0) {
                                dtpPNCDate.setText("");
                            }
                        }

                    }

                }
            });

            secPNCProv = (LinearLayout) findViewById(R.id.secPNCProv);
            linePNCProv = (View) findViewById(R.id.linePNCProv);
            VlblPNCProv = (TextView) findViewById(R.id.VlblPNCProv);
            rdogrpPNCProv = (RadioGroup) findViewById(R.id.rdogrpPNCProv);
            rdoPNCProv1 = (RadioButton) findViewById(R.id.rdoPNCProv1);
            rdoPNCProv2 = (RadioButton) findViewById(R.id.rdoPNCProv2);
            rdoPNCProv3 = (RadioButton) findViewById(R.id.rdoPNCProv3);
            rdoPNCProv4 = (RadioButton) findViewById(R.id.rdoPNCProv4);
            rdoPNCProv5 = (RadioButton) findViewById(R.id.rdoPNCProv5);
            rdoPNCProv6 = (RadioButton) findViewById(R.id.rdoPNCProv6);
            rdoPNCProv7 = (RadioButton) findViewById(R.id.rdoPNCProv7);
            rdoPNCProv8 = (RadioButton) findViewById(R.id.rdoPNCProv8);
            rdogrpPNCProv.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpPNCProv = new String[]{"21", "22", "23", "31", "32", "97", "98", "99"};
                    for (int i = 0; i < rdogrpPNCProv.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpPNCProv.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpPNCProv[i];
                    }

                    if (rbData.equalsIgnoreCase("97")) {
                        secPNCProvOth.setVisibility(View.VISIBLE);
                        linePNCProvOth.setVisibility(View.VISIBLE);
                    } else {
                        secPNCProvOth.setVisibility(View.GONE);
                        linePNCProvOth.setVisibility(View.GONE);
                        txtPNCProvOth.setText("");

                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secPNCProvOth = (LinearLayout) findViewById(R.id.secPNCProvOth);
            linePNCProvOth = (View) findViewById(R.id.linePNCProvOth);
            VlblPNCProvOth = (TextView) findViewById(R.id.VlblPNCProvOth);
            txtPNCProvOth = (AutoCompleteTextView) findViewById(R.id.txtPNCProvOth);
            C.setupAutoComplete(TableName,txtPNCProvOth,"PNCProvOth"); //setup autocomplete view by event

            secPNCPlace = (LinearLayout) findViewById(R.id.secPNCPlace);
            linePNCPlace = (View) findViewById(R.id.linePNCPlace);
            VlblPNCPlace = (TextView) findViewById(R.id.VlblPNCPlace);
            rdogrpPNCPlace = (RadioGroup) findViewById(R.id.rdogrpPNCPlace);
            rdoPNCPlace1 = (RadioButton) findViewById(R.id.rdoPNCPlace1);
            rdoPNCPlace2 = (RadioButton) findViewById(R.id.rdoPNCPlace2);
            rdoPNCPlace3 = (RadioButton) findViewById(R.id.rdoPNCPlace3);
            rdoPNCPlace4 = (RadioButton) findViewById(R.id.rdoPNCPlace4);
            rdoPNCPlace5 = (RadioButton) findViewById(R.id.rdoPNCPlace5);
            rdoPNCPlace6 = (RadioButton) findViewById(R.id.rdoPNCPlace6);
            rdoPNCPlace7 = (RadioButton) findViewById(R.id.rdoPNCPlace7);
            rdoPNCPlace8 = (RadioButton) findViewById(R.id.rdoPNCPlace8);
            rdoPNCPlace9 = (RadioButton) findViewById(R.id.rdoPNCPlace9);
            rdoPNCPlace10 = (RadioButton) findViewById(R.id.rdoPNCPlace10);
            rdoPNCPlace11 = (RadioButton) findViewById(R.id.rdoPNCPlace11);
            rdogrpPNCPlace.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpPNCPlace = new String[]{"11", "15", "21", "22", "23", "31", "32", "41", "97", "98", "99"};
                    for (int i = 0; i < rdogrpPNCPlace.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpPNCPlace.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpPNCPlace[i];
                    }

                    if (rbData.equalsIgnoreCase("97")) {
                        secPNCPlaceOth.setVisibility(View.VISIBLE);
                        linePNCPlaceOth.setVisibility(View.VISIBLE);
                    } else {
                        secPNCPlaceOth.setVisibility(View.GONE);
                        linePNCPlaceOth.setVisibility(View.GONE);
                        txtPNCPlaceOth.setText("");
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secPNCPlaceOth = (LinearLayout) findViewById(R.id.secPNCPlaceOth);
            linePNCPlaceOth = (View) findViewById(R.id.linePNCPlaceOth);
            VlblPNCPlaceOth = (TextView) findViewById(R.id.VlblPNCPlaceOth);
            txtPNCPlaceOth = (AutoCompleteTextView) findViewById(R.id.txtPNCPlaceOth);
            C.setupAutoComplete(TableName,txtPNCPlaceOth,"PNCPlaceOth"); //setup autocomplete view by event

            secPNCRes = (LinearLayout) findViewById(R.id.secPNCRes);
            linePNCRes = (View) findViewById(R.id.linePNCRes);
            VlblPNCRes = (TextView) findViewById(R.id.VlblPNCRes);
            rdogrpPNCRes = (RadioGroup) findViewById(R.id.rdogrpPNCRes);
            rdoPNCRes1 = (RadioButton) findViewById(R.id.rdoPNCRes1);
            rdoPNCRes2 = (RadioButton) findViewById(R.id.rdoPNCRes2);
            rdoPNCRes3 = (RadioButton) findViewById(R.id.rdoPNCRes3);
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCMothDetail.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(NBC_PNCMothDetail.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            NBC_PNCMothDetail_DataModel objSave = new NBC_PNCMothDetail_DataModel();
            objSave.setNBID(txtNBID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setPGN(txtPGN.getText().toString());
            objSave.setPNCSl(txtPNCSl.getText().toString());
            objSave.setPNCDate(dtpPNCDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpPNCDate.getText().toString()) : dtpPNCDate.getText().toString());
            String[] d_rdogrpPNCDateDk = new String[]{"8", "9"};
            objSave.setPNCDateDk("");
            for (int i = 0; i < rdogrpPNCDateDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpPNCDateDk.getChildAt(i);
                if (rb.isChecked()) objSave.setPNCDateDk(d_rdogrpPNCDateDk[i]);
            }

            String[] d_rdogrpPNCProv = new String[]{"21", "22", "23", "31", "32", "97", "98", "99"};
            objSave.setPNCProv("");
            for (int i = 0; i < rdogrpPNCProv.getChildCount(); i++) {
                rb = (RadioButton) rdogrpPNCProv.getChildAt(i);
                if (rb.isChecked()) objSave.setPNCProv(d_rdogrpPNCProv[i]);
            }

            objSave.setPNCProvOth(txtPNCProvOth.getText().toString());
            String[] d_rdogrpPNCPlace = new String[]{"11", "15", "21", "22", "23", "31", "32", "41", "97", "98", "99"};
            objSave.setPNCPlace("");
            for (int i = 0; i < rdogrpPNCPlace.getChildCount(); i++) {
                rb = (RadioButton) rdogrpPNCPlace.getChildAt(i);
                if (rb.isChecked()) objSave.setPNCPlace(d_rdogrpPNCPlace[i]);
            }

            objSave.setPNCPlaceOth(txtPNCPlaceOth.getText().toString());
            String[] d_rdogrpPNCRes = new String[]{"1", "2", "3"};
            objSave.setPNCRes("");
            for (int i = 0; i < rdogrpPNCRes.getChildCount(); i++) {
                rb = (RadioButton) rdogrpPNCRes.getChildAt(i);
                if (rb.isChecked()) objSave.setPNCRes(d_rdogrpPNCRes[i]);
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

                Toast.makeText(NBC_PNCMothDetail.this, "Save Successfully...", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(NBC_PNCMothDetail.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCMothDetail.this, e.getMessage());
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
            if (txtPNCSl.getText().toString().length() == 0 & secPNCSl.isShown()) {
                ValidationMsg += "\nRequired field: PNC Serial.";
                secPNCSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secPNCSl.isShown() & (Integer.valueOf(txtPNCSl.getText().toString().length() == 0 ? "0" : txtPNCSl.getText().toString()) < 0 || Integer.valueOf(txtPNCSl.getText().toString().length() == 0 ? "20" : txtPNCSl.getText().toString()) > 20)) {
                ValidationMsg += "\nValue should be between 0 and 20(PNC Serial).";
                secPNCSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpPNCDate.getText().toString());

            int pnc_serial = Integer.parseInt(txtPNCSl.getText().toString().length()==0?"0":txtPNCSl.getText().toString());
            lastPNCDate = C.ReturnSingleValue("Select ifnull(PNCDate,'') from NBC_PNCMothDetail where MemID='"+ MEMID +"' and HHID='"+ HHID +"' and PGN='"+ PGN +"' and Cast(PNCSl as int)<"+ pnc_serial +" and isdelete=2 order by Cast(PNCSl as int) desc limit 1");
            //lastPNCDate = C.ReturnSingleValue("SELECT COALESCE(MAX(DATE(PNCDate)), '') AS max_date FROM NBC_PNCMothDetail Where MemID = '"+MEMID+"' and HHID = '"+HHID+"' and PGN = '"+PGN+"' and isdelete=2");
            pickedPNCDate = Global.DateConvertYMD(dtpPNCDate.getText().toString());
            int isPickedDateAfterPrevious = Global.DateDifferenceDays(dtpPNCDate.getText().toString(), Global.DateConvertDMY(lastPNCDate));
            if (!rdoPNCDateDk1.isChecked() & !rdoPNCDateDk2.isChecked() & DV.length() != 0 & secPNCDate.isShown() & secPNCDateDk.isShown()) {
                ValidationMsg += "\nB4.8 Required field/Not a valid date format: Date of PNC check-up.";
                secPNCDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                ValidationMsg += "\nRequired field: PNC Date Dont know.";
                secPNCDateDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (lastPNCDate.length() != 0 & isPickedDateAfterPrevious<0 & !rdoPNCDateDk1.isChecked() & !rdoPNCDateDk2.isChecked()){
                ValidationMsg += "\nNew PNC Date should be bigger than last PNC Date["+ Global.DateConvertDMY(lastPNCDate) +"]";
                secPNCDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }


            if (!rdoPNCProv1.isChecked() & !rdoPNCProv2.isChecked() & !rdoPNCProv3.isChecked() & !rdoPNCProv4.isChecked() & !rdoPNCProv5.isChecked() & !rdoPNCProv6.isChecked() & !rdoPNCProv7.isChecked() & !rdoPNCProv8.isChecked() & secPNCProv.isShown()) {
                ValidationMsg += "\nB4.9 Required field: Whom did you see for your postnatal care checkup for the child/name? (record code).";
                secPNCProv.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtPNCProvOth.getText().toString().length() == 0 & secPNCProvOth.isShown()) {
                ValidationMsg += "\nRequired field: Other ANC Provider.";
                secPNCProvOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoPNCPlace1.isChecked() & !rdoPNCPlace2.isChecked() & !rdoPNCPlace3.isChecked() & !rdoPNCPlace4.isChecked() & !rdoPNCPlace5.isChecked() & !rdoPNCPlace6.isChecked() & !rdoPNCPlace7.isChecked() & !rdoPNCPlace8.isChecked() & !rdoPNCPlace9.isChecked() & !rdoPNCPlace10.isChecked() & !rdoPNCPlace11.isChecked() & secPNCPlace.isShown()) {
                ValidationMsg += "\nB4.10 Required field: Where did you see your postnatal care checkup for the child/name? (Write facility name and record code).";
                secPNCPlace.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtPNCPlaceOth.getText().toString().length() == 0 & secPNCPlaceOth.isShown()) {
                ValidationMsg += "\nRequired field: Other ANC Place.";
                secPNCPlaceOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoPNCRes1.isChecked() & !rdoPNCRes2.isChecked() & !rdoPNCRes3.isChecked() & secPNCRes.isShown()) {
                ValidationMsg += "\nB4.11 Required field: Why did you visit for the child/name?.";
                secPNCRes.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
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
            secPNCSl.setBackgroundColor(Color.WHITE);
            secPNCSl.setBackgroundColor(Color.WHITE);
            secPNCDate.setBackgroundColor(Color.WHITE);
            secPNCDateDk.setBackgroundColor(Color.WHITE);
            secPNCProv.setBackgroundColor(Color.WHITE);
            secPNCProvOth.setBackgroundColor(Color.WHITE);
            secPNCPlace.setBackgroundColor(Color.WHITE);
            secPNCPlaceOth.setBackgroundColor(Color.WHITE);
            secPNCRes.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String MEMID, String HHID, String PGN, String PNCSl) {
        try {
            RadioButton rb;
            NBC_PNCMothDetail_DataModel d = new NBC_PNCMothDetail_DataModel();
            String SQL = "Select * from " + TableName + "  Where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='" + PGN + "' and PNCSl='" + PNCSl + "'";
            List<NBC_PNCMothDetail_DataModel> data = d.SelectAll(this, SQL);
            for (NBC_PNCMothDetail_DataModel item : data) {
                txtNBID.setText(item.getNBID());
                txtMemID.setText(item.getMemID());
                txtHHID.setText(item.getHHID());
                txtPGN.setText(item.getPGN());
                txtPNCSl.setText(String.valueOf(item.getPNCSl()));
                dtpPNCDate.setText(item.getPNCDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getPNCDate()));
                String[] d_rdogrpPNCDateDk = new String[]{"8", "9"};
                for (int i = 0; i < d_rdogrpPNCDateDk.length; i++) {
                    if (String.valueOf(item.getPNCDateDk()).equals(String.valueOf(d_rdogrpPNCDateDk[i]))) {
                        rb = (RadioButton) rdogrpPNCDateDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpPNCProv = new String[]{"21", "22", "23", "31", "32", "97", "98", "99"};
                for (int i = 0; i < d_rdogrpPNCProv.length; i++) {
                    if (String.valueOf(item.getPNCProv()).equals(String.valueOf(d_rdogrpPNCProv[i]))) {
                        rb = (RadioButton) rdogrpPNCProv.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtPNCProvOth.setText(item.getPNCProvOth());
                String[] d_rdogrpPNCPlace = new String[]{"11", "15", "21", "22", "23", "31", "32", "41", "97", "98", "99"};
                for (int i = 0; i < d_rdogrpPNCPlace.length; i++) {
                    if (String.valueOf(item.getPNCPlace()).equals(String.valueOf(d_rdogrpPNCPlace[i]))) {
                        rb = (RadioButton) rdogrpPNCPlace.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtPNCPlaceOth.setText(item.getPNCPlaceOth());
                String[] d_rdogrpPNCRes = new String[]{"1", "2", "3"};
                for (int i = 0; i < d_rdogrpPNCRes.length; i++) {
                    if (String.valueOf(item.getPNCRes()).equals(String.valueOf(d_rdogrpPNCRes[i]))) {
                        rb = (RadioButton) rdogrpPNCRes.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_PNCMothDetail.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpPNCDate);
            if (VariableID.equals("btnPNCDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpPNCDate);
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

    //Generate New PNC Serial
    public String NewPNCSerial(String MEMID, String HHID, String PGN) {
        String M = C.ReturnSingleValue("Select cast(ifnull(max(PNCSl),0)+1 as varchar(2))PNCSL from NBC_PNCMothDetail where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='" + PGN + "'");
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