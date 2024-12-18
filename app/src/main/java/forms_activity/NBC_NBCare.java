
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
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import Common.Connection;
import Common.Global;
import Utility.MySharedPreferences;
import forms_datamodel.NBC_NBCare_DataModel;

public class NBC_NBCare extends AppCompatActivity {
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
    LinearLayout secPregLVisit;
    View linePregLVisit;
    TextView VlblPregLVisit;
    RadioGroup rdogrpPregLVisit;
    RadioButton rdoPregLVisit1;
    RadioButton rdoPregLVisit2;
    RadioButton rdoPregLVisit3;
    RadioButton rdoPregLVisit4;
    LinearLayout seclblB14;
    View linelblB14;
    LinearLayout secOutcomeType;
    View lineOutcomeType;
    TextView VlblOutcomeType;
    RadioGroup rdogrpOutcomeType;
    RadioButton rdoOutcomeType1;
    RadioButton rdoOutcomeType2;
    LinearLayout secBirthNo;
    View lineBirthNo;
    TextView VlblBirthNo;
    EditText txtBirthNo;

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
     String MEMID = "";
     String HHID = "";
     String ROUND = "";
     String title = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.nbc_nbcare);
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
            ROUND = IDbundle.getString("ROUND");
            title = IDbundle.getString("title");

            TableName = "NBC_NBCare";
            MODULEID = 40;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);
            lblHeading.setText(title);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(NBC_NBCare.this);
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
            Connection.LocalizeLanguage(NBC_NBCare.this, MODULEID, LANGUAGEID);


            //Hide all skip variables
            seclblB14.setVisibility(View.GONE);
            linelblB14.setVisibility(View.GONE);
            secOutcomeType.setVisibility(View.GONE);
            lineOutcomeType.setVisibility(View.GONE);
            secBirthNo.setVisibility(View.GONE);
            lineBirthNo.setVisibility(View.GONE);
            seclblB14.setVisibility(View.GONE);
            linelblB14.setVisibility(View.GONE);
            secOutcomeType.setVisibility(View.GONE);
            lineOutcomeType.setVisibility(View.GONE);
            secBirthNo.setVisibility(View.GONE);
            lineBirthNo.setVisibility(View.GONE);
            seclblB14.setVisibility(View.GONE);
            linelblB14.setVisibility(View.GONE);
            secOutcomeType.setVisibility(View.GONE);
            lineOutcomeType.setVisibility(View.GONE);
            secBirthNo.setVisibility(View.GONE);
            lineBirthNo.setVisibility(View.GONE);
            secBirthNo.setVisibility(View.GONE);
            lineBirthNo.setVisibility(View.GONE);


            DataSearch(MEMID, HHID, PGN);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(NBC_NBCare.this, e.getMessage());
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
            secPregLVisit = (LinearLayout) findViewById(R.id.secPregLVisit);
            linePregLVisit = (View) findViewById(R.id.linePregLVisit);
            VlblPregLVisit = (TextView) findViewById(R.id.VlblPregLVisit);
            rdogrpPregLVisit = (RadioGroup) findViewById(R.id.rdogrpPregLVisit);
            rdoPregLVisit1 = (RadioButton) findViewById(R.id.rdoPregLVisit1);
            rdoPregLVisit2 = (RadioButton) findViewById(R.id.rdoPregLVisit2);
            rdoPregLVisit3 = (RadioButton) findViewById(R.id.rdoPregLVisit3);
            rdoPregLVisit4 = (RadioButton) findViewById(R.id.rdoPregLVisit4);
            seclblB14 = (LinearLayout) findViewById(R.id.seclblB14);
            linelblB14 = (View) findViewById(R.id.linelblB14);
            secOutcomeType = (LinearLayout) findViewById(R.id.secOutcomeType);
            lineOutcomeType = (View) findViewById(R.id.lineOutcomeType);
            VlblOutcomeType = (TextView) findViewById(R.id.VlblOutcomeType);
            rdogrpOutcomeType = (RadioGroup) findViewById(R.id.rdogrpOutcomeType);
            rdoOutcomeType1 = (RadioButton) findViewById(R.id.rdoOutcomeType1);
            rdoOutcomeType2 = (RadioButton) findViewById(R.id.rdoOutcomeType2);
            secBirthNo = (LinearLayout) findViewById(R.id.secBirthNo);
            lineBirthNo = (View) findViewById(R.id.lineBirthNo);
            VlblBirthNo = (TextView) findViewById(R.id.VlblBirthNo);
            txtBirthNo = (EditText) findViewById(R.id.txtBirthNo);
            rdogrpPregLVisit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpPregLVisit = new String[]{"0", "1", "8", "9"};
                    for (int i = 0; i < rdogrpPregLVisit.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpPregLVisit.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpPregLVisit[i];
                    }

                    if (rbData.equalsIgnoreCase("0")) {
                        seclblB14.setVisibility(View.GONE);
                        linelblB14.setVisibility(View.GONE);
                        secOutcomeType.setVisibility(View.GONE);
                        lineOutcomeType.setVisibility(View.GONE);
                        rdogrpOutcomeType.clearCheck();
                        secBirthNo.setVisibility(View.GONE);
                        lineBirthNo.setVisibility(View.GONE);
                        txtBirthNo.setText("");
                    } else if (rbData.equalsIgnoreCase("8")) {
                        seclblB14.setVisibility(View.GONE);
                        linelblB14.setVisibility(View.GONE);
                        secOutcomeType.setVisibility(View.GONE);
                        lineOutcomeType.setVisibility(View.GONE);
                        rdogrpOutcomeType.clearCheck();
                        secBirthNo.setVisibility(View.GONE);
                        lineBirthNo.setVisibility(View.GONE);
                        txtBirthNo.setText("");
                    } else if (rbData.equalsIgnoreCase("9")) {
                        seclblB14.setVisibility(View.GONE);
                        linelblB14.setVisibility(View.GONE);
                        secOutcomeType.setVisibility(View.GONE);
                        lineOutcomeType.setVisibility(View.GONE);
                        rdogrpOutcomeType.clearCheck();
                        secBirthNo.setVisibility(View.GONE);
                        lineBirthNo.setVisibility(View.GONE);
                        txtBirthNo.setText("");
                    } else {
                        seclblB14.setVisibility(View.VISIBLE);
                        linelblB14.setVisibility(View.VISIBLE);
                        secOutcomeType.setVisibility(View.VISIBLE);
                        lineOutcomeType.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

            rdogrpOutcomeType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpOutcomeType = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpOutcomeType.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpOutcomeType.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpOutcomeType[i];
                    }

                    if (rbData.equalsIgnoreCase("1")) {
                        secBirthNo.setVisibility(View.GONE);
                        lineBirthNo.setVisibility(View.GONE);
                        txtBirthNo.setText("");
                    } else {
                        secBirthNo.setVisibility(View.VISIBLE);
                        lineBirthNo.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

        } catch (Exception e) {
            Connection.MessageBox(NBC_NBCare.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(NBC_NBCare.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            NBC_NBCare_DataModel objSave = new NBC_NBCare_DataModel();
            objSave.setNBID(txtNBID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setPGN(txtPGN.getText().toString());
            String[] d_rdogrpPregLVisit = new String[]{"0", "1", "8", "9"};
            objSave.setPregLVisit("");
            for (int i = 0; i < rdogrpPregLVisit.getChildCount(); i++) {
                rb = (RadioButton) rdogrpPregLVisit.getChildAt(i);
                if (rb.isChecked()) objSave.setPregLVisit(d_rdogrpPregLVisit[i]);
            }

            String[] d_rdogrpOutcomeType = new String[]{"1", "2"};
            objSave.setOutcomeType("");
            for (int i = 0; i < rdogrpOutcomeType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpOutcomeType.getChildAt(i);
                if (rb.isChecked()) objSave.setOutcomeType(d_rdogrpOutcomeType[i]);
            }

            objSave.setBirthNo(rdoOutcomeType1.isChecked() ? "1" : txtBirthNo.getText().toString());
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

                Toast.makeText(NBC_NBCare.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(NBC_NBCare.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_NBCare.this, e.getMessage());
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
            if (!rdoPregLVisit1.isChecked() & !rdoPregLVisit2.isChecked() & !rdoPregLVisit3.isChecked() & !rdoPregLVisit4.isChecked() & secPregLVisit.isShown()) {
                ValidationMsg += "\nB1.3 Required field: Have you been pregnant in last 12 months?.";
                secPregLVisit.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoOutcomeType1.isChecked() & !rdoOutcomeType2.isChecked() & secOutcomeType.isShown()) {
                ValidationMsg += "\nB1.4 Required field: Was your pregnancy a single pregnancy, twins, or triplets.";
                secOutcomeType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtBirthNo.getText().toString().length() == 0 & secBirthNo.isShown()) {
                ValidationMsg += "\nB1.5 Required field: What is the number of births?.";
                secBirthNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
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
            secPregLVisit.setBackgroundColor(Color.WHITE);
            secOutcomeType.setBackgroundColor(Color.WHITE);
            secBirthNo.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String MEMID, String HHID, String PGN) {
        try {
            RadioButton rb;
            NBC_NBCare_DataModel d = new NBC_NBCare_DataModel();
            String SQL = "Select * from " + TableName + "  Where MemID='" + MEMID + "' and HHID='" + HHID + "' and PGN='" + PGN + "'";
            List<NBC_NBCare_DataModel> data = d.SelectAll(this, SQL);
            for (NBC_NBCare_DataModel item : data) {
                txtNBID.setText(item.getNBID());
                txtMemID.setText(item.getMemID());
                txtHHID.setText(item.getHHID());
                txtPGN.setText(item.getPGN());
                String[] d_rdogrpPregLVisit = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpPregLVisit.length; i++) {
                    if (String.valueOf(item.getPregLVisit()).equals(String.valueOf(d_rdogrpPregLVisit[i]))) {
                        rb = (RadioButton) rdogrpPregLVisit.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpOutcomeType = new String[]{"1", "2"};
                for (int i = 0; i < d_rdogrpOutcomeType.length; i++) {
                    if (String.valueOf(item.getOutcomeType()).equals(String.valueOf(d_rdogrpOutcomeType[i]))) {
                        rb = (RadioButton) rdogrpOutcomeType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtBirthNo.setText(item.getBirthNo());
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_NBCare.this, e.getMessage());
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
}