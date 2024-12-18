
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
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
import forms_datamodel.tmpMotherSerial_DataModel;

public class Surv_MotherSerial extends AppCompatActivity {
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
    LinearLayout secMothSerialID;
    View lineMothSerialID;
    TextView VlblMothSerialID;
    EditText txtMothSerialID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secMothSlEvDate;
    View lineMothSlEvDate;
    TextView VlblMothSlEvDate;
    EditText dtpMothSlEvDate;
    LinearLayout secNewMothSl;
    View lineNewMothSl;
    TextView VlblNewMothSl;
    Spinner spnNewMothSl;
    LinearLayout secOldMothSl;
    View lineOldMothSl;
    TextView VlblOldMothSl;
    Spinner spnOldMothSl;
    LinearLayout secMothSlNote;
    View lineMothSlNote;
    TextView VlblMothSlNote;
    EditText txtMothSlNote;
    LinearLayout secMothSlNote1;
    View lineMothSlNote1;

    LinearLayout secMothVDate;
    View lineMothVDate;
    TextView VlblMothVDate;
    EditText dtpMothVDate;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    TextView lblEvCode;
     int MODULEID = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String MOTHSERIALID = "";

     String MEM_ID = "";
     String HHID = "";
     String EV_TYPE = "";
     String ROUND = "";
     String VISIT_DATE = "";
     String AGE_DAYS = "";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.surv_motherserial);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            MOTHSERIALID = IDbundle.getString("MothSerialID");
            MEM_ID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            EV_TYPE = IDbundle.getString("evtype");
            ROUND = IDbundle.getString("round");
            AGE_DAYS = IDbundle.getString("age");
            VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy

            TableName = "tmpMotherSerial";
            MODULEID = 32;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_MotherSerial.this);
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
            Connection.LocalizeLanguage(Surv_MotherSerial.this, MODULEID, LANGUAGEID);

            String Sp1 = C.ReturnSingleValue("SELECT Sp1 from tmpMemberMove where MemID='"+MEM_ID+"'");
            String Sp2 = C.ReturnSingleValue("SELECT Sp2 from tmpMemberMove where MemID='"+MEM_ID+"'");
            String Sp3 = C.ReturnSingleValue("SELECT Sp3 from tmpMemberMove where MemID='"+MEM_ID+"'");
            String Sp4 = C.ReturnSingleValue("SELECT Sp4 from tmpMemberMove where MemID='"+MEM_ID+"'");
            spnNewMothSl.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and Sex='2' and MemID <> '"+MEM_ID+"' and julianday('now') - julianday(BDate) > 3652 and MS<>'0' and (MSlNo <> '"+Sp1+"' and MSlNo <> '"+Sp2+"' and MSlNo <> '"+Sp3+"' and MSlNo <> '"+Sp4+"') union Select '00-Not a Member of this Household'"));

            String PMoNo = C.ReturnSingleValue("Select MoNo from tmpMemberMove where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            if (!PMoNo.equals("00")) {
                String PMoNoValue = C.ReturnSingleValue("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + PMoNo.toString() + "'");
                if (PMoNoValue.equals("") || PMoNoValue.isEmpty()){
                    spnOldMothSl.setAdapter(C.getArrayAdapter("Select '00-Not a member of this House'"));
                } else {
                    spnOldMothSl.setAdapter(C.getArrayAdapter("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + PMoNo.toString() + "'"));
                }
            }
            if (PMoNo.equals("00") || PMoNo.equals("")) {
                spnOldMothSl.setAdapter(C.getArrayAdapter("Select '00-Not a member of this House'"));
            }
            spnOldMothSl.setEnabled(false);


            dtpMothSlEvDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnMothSlEvDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });
            dtpMothVDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnMothVDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            //Hide all skip variables


            DataSearch(MOTHSERIALID);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(Surv_MotherSerial.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            lblEvCode = findViewById(R.id.lblEvCode);
            lblEvCode.setText(EV_TYPE);

            secMothSerialID = (LinearLayout) findViewById(R.id.secMothSerialID);
            lineMothSerialID = (View) findViewById(R.id.lineMothSerialID);
            VlblMothSerialID = (TextView) findViewById(R.id.VlblMothSerialID);
            txtMothSerialID = (EditText) findViewById(R.id.txtMothSerialID);
//         txtMothSerialID.setText(MOTHSERIALID);
//         txtMothSerialID.setEnabled(false);
            if (MOTHSERIALID.length() == 0) txtMothSerialID.setText(Global.Get_UUID(DEVICEID));
            else txtMothSerialID.setText(MOTHSERIALID);
            txtMothSerialID.setEnabled(false);
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
            txtMemID.setText(MEM_ID);
            secMothSlEvDate = (LinearLayout) findViewById(R.id.secMothSlEvDate);
            lineMothSlEvDate = (View) findViewById(R.id.lineMothSlEvDate);
            VlblMothSlEvDate = (TextView) findViewById(R.id.VlblMothSlEvDate);
            dtpMothSlEvDate = (EditText) findViewById(R.id.dtpMothSlEvDate);

            secNewMothSl = (LinearLayout) findViewById(R.id.secNewMothSl);
            lineNewMothSl = (View) findViewById(R.id.lineNewMothSl);
            VlblNewMothSl = (TextView) findViewById(R.id.VlblNewMothSl);
            spnNewMothSl = (Spinner) findViewById(R.id.spnNewMothSl);

            String Sp1 = C.ReturnSingleValue("SELECT Sp1 from tmpMemberMove where MemID='"+MEM_ID+"'");
            String Sp2 = C.ReturnSingleValue("SELECT Sp2 from tmpMemberMove where MemID='"+MEM_ID+"'");
            String Sp3 = C.ReturnSingleValue("SELECT Sp3 from tmpMemberMove where MemID='"+MEM_ID+"'");
            String Sp4 = C.ReturnSingleValue("SELECT Sp4 from tmpMemberMove where MemID='"+MEM_ID+"'");
            spnNewMothSl.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and Sex='2' and MemID <> '"+MEM_ID+"' and julianday('now') - julianday(BDate) > 3652 and MS<>'0' and (MSlNo <> '"+Sp1+"' and MSlNo <> '"+Sp2+"' and MSlNo <> '"+Sp3+"' and MSlNo <> '"+Sp4+"') union Select '00-Not a Member of this House'"));

            secOldMothSl = (LinearLayout) findViewById(R.id.secOldMothSl);
            lineOldMothSl = (View) findViewById(R.id.lineOldMothSl);
            VlblOldMothSl = (TextView) findViewById(R.id.VlblOldMothSl);
            spnOldMothSl = (Spinner) findViewById(R.id.spnOldMothSl);
            spnOldMothSl.setEnabled(false);

            String PMoNo = C.ReturnSingleValue("Select MoNo from tmpMemberMove where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            if (!PMoNo.equals("00")) {
                String PMoNoValue = C.ReturnSingleValue("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + PMoNo.toString() + "'");
                if (PMoNoValue.equals("") || PMoNoValue.isEmpty()){
                    spnOldMothSl.setAdapter(C.getArrayAdapter("Select '00-Not a member of this House'"));
                } else {
                    spnOldMothSl.setAdapter(C.getArrayAdapter("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + PMoNo.toString() + "'"));
                }
            }
            if (PMoNo.equals("00") || PMoNo.equals("")) {
                spnOldMothSl.setAdapter(C.getArrayAdapter("Select '00-Not a member of this House'"));
            }

            secRnd = (LinearLayout) findViewById(R.id.secRnd);
            lineRnd = (View) findViewById(R.id.lineRnd);
            VlblRnd = (TextView) findViewById(R.id.VlblRnd);
            txtRnd = (EditText) findViewById(R.id.txtRnd);
            txtRnd.setText(ROUND);
            txtRnd.setEnabled(false);

            secMothVDate = (LinearLayout) findViewById(R.id.secMothVDate);
            lineMothVDate = (View) findViewById(R.id.lineMothVDate);
            VlblMothVDate = (TextView) findViewById(R.id.VlblMothVDate);
            dtpMothVDate = (EditText) findViewById(R.id.dtpMothVDate);
            dtpMothVDate.setText(VISIT_DATE);
            secMothVDate.setVisibility(View.GONE);

            secMothSlNote = (LinearLayout) findViewById(R.id.secMothSlNote);
            lineMothSlNote = (View) findViewById(R.id.lineMothSlNote);
            VlblMothSlNote = (TextView) findViewById(R.id.VlblMothSlNote);
            txtMothSlNote = (EditText) findViewById(R.id.txtMothSlNote);
            secMothSlNote1 = (LinearLayout) findViewById(R.id.secMothSlNote1);
            lineMothSlNote1 = (View) findViewById(R.id.lineMothSlNote1);
        } catch (Exception e) {
            Connection.MessageBox(Surv_MotherSerial.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(Surv_MotherSerial.this, ValidationMSG);
                return;
            }

            String SQL3 = "";
            String SQL = "";
            RadioButton rb;

            tmpMotherSerial_DataModel objSave = new tmpMotherSerial_DataModel();
            objSave.setMothSerialID(txtMothSerialID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setMothSlEvDate(dtpMothSlEvDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpMothSlEvDate.getText().toString()) : dtpMothSlEvDate.getText().toString());
            objSave.setNewMothSl(spnNewMothSl.getSelectedItemPosition() == 0 ? "" : spnNewMothSl.getSelectedItem().toString().split("-")[0]);
            //objSave.setOldMothSl(spnOldMothSl.getSelectedItemPosition() == 0 ? "" : spnOldMothSl.getSelectedItem().toString().split("-")[0]);
            objSave.setOldMothSl(spnOldMothSl.getSelectedItem().toString().split("-")[0]);
            objSave.setMothVDate(dtpMothVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpMothVDate.getText().toString()) : dtpMothVDate.getText().toString());
            objSave.setRnd(txtRnd.getText().toString());
            objSave.setMothSlNote(txtMothSlNote.getText().toString());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);

            if (status.length() == 0) {

                C.SaveData("Update tmpMember set Upload='2',MoNo='" + (spnNewMothSl.getSelectedItemPosition() == 0 ? "0" : spnNewMothSl.getSelectedItem().toString().split("-")[0]) + "',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where MemID='" + MEM_ID + "' ");
                C.SaveData("Update tmpMemberMove set Upload='2',MoNo='" + (spnNewMothSl.getSelectedItemPosition() == 0 ? "0" : spnNewMothSl.getSelectedItem().toString().split("-")[0]) + "',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where MemID='" + MEM_ID + "' AND HHID = '"+HHID+"'");

                Intent returnIntent = new Intent();
                returnIntent.putExtra("res", "");
                setResult(Activity.RESULT_OK, returnIntent);

                Toast.makeText(getApplicationContext(), "Save Successfully...", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(Surv_MotherSerial.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_MotherSerial.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        try {
            ResetSectionColor();
            if (txtMothSerialID.getText().toString().length() == 0 & secMothSerialID.isShown()) {
                ValidationMsg += "\nRequired field: Mother serial number Internal ID.";
                secMothSerialID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtHHID.getText().toString().length() == 0 & secHHID.isShown()) {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtMemID.getText().toString().length() == 0 & secMemID.isShown()) {
                ValidationMsg += "\nRequired field: Member internal ID.";
                secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpMothSlEvDate.getText().toString());
            if (DV.length() != 0 & secMothSlEvDate.isShown()) {
                ValidationMsg += "\n1. Required field/Not a valid date format: When has this member’s mother’s serial changed?.";
                secMothSlEvDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }

            if(AGE_DAYS != null){
                if(DV.length() == 0 & Integer.parseInt(AGE_DAYS) < Global.DateDifferenceDays(Global.DateNowDMY(), dtpMothSlEvDate.getText().toString())){
                    ValidationMsg += "\n1. Required field: Member's mother's serial change date should be greater than birth date";
                    secMothSlEvDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }
            }


            if (spnNewMothSl.getSelectedItemPosition() == 0 & secNewMothSl.isShown()) {
                ValidationMsg += "\n2. Required field: What is the new serial number?.";
                secNewMothSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
//         if(spnOldMothSl.getSelectedItemPosition()==0  & secOldMothSl.isShown())
//         {
//             ValidationMsg += "\n3. Required field: What was the previous serial number?.";
//             secOldMothSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//         }
            if (txtRnd.getText().toString().length() == 0 & secRnd.isShown()) {
                ValidationMsg += "\nRequired field: Round.";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secRnd.isShown() & (Integer.valueOf(txtRnd.getText().toString().length() == 0 ? "1" : txtRnd.getText().toString()) < 1 || Integer.valueOf(txtRnd.getText().toString().length() == 0 ? "99" : txtRnd.getText().toString()) > 99)) {
                ValidationMsg += "\nValue should be between 1 and 99(Round).";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpMothVDate.getText().toString());
            if (DV.length() != 0 & secMothVDate.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: Visit Date.";
                secMothVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
/*         if(txtMothSlNote.getText().toString().length()==0 & secMothSlNote.isShown())
           {
             ValidationMsg += "\nRequired field: Note.";
             secMothSlNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/

            if(spnNewMothSl.getSelectedItemPosition() != 0){
                Integer NewMotSl = Integer.parseInt(spnNewMothSl.getSelectedItem().toString().split("-")[0]);
                Integer PrvMotSl = Integer.parseInt(spnOldMothSl.getSelectedItem().toString().split("-")[0]);

                if (NewMotSl.equals(PrvMotSl)) {
                    ValidationMsg += "\n New Mother serial number and Old Mother serial number should not same";
                    secNewMothSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    secOldMothSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }
            }

        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor() {
        try {
            secMothSerialID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secMothSlEvDate.setBackgroundColor(Color.WHITE);
            secNewMothSl.setBackgroundColor(Color.WHITE);
            secOldMothSl.setBackgroundColor(Color.WHITE);
            secOldMothSl.setBackgroundColor(Color.WHITE);
            secMothSlNote.setBackgroundColor(Color.WHITE);
            secRnd.setBackgroundColor(Color.WHITE);
            secMothVDate.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String MothSerialID) {
        try {
            RadioButton rb;
            tmpMotherSerial_DataModel d = new tmpMotherSerial_DataModel();
            String SQL = "Select * from " + TableName + "  Where MothSerialID='" + MothSerialID + "'";
            List<tmpMotherSerial_DataModel> data = d.SelectAll(this, SQL);
            for (tmpMotherSerial_DataModel item : data) {
                txtMothSerialID.setText(item.getMothSerialID());
                txtHHID.setText(item.getHHID());
                txtMemID.setText(item.getMemID());
                dtpMothSlEvDate.setText(item.getMothSlEvDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getMothSlEvDate()));
                spnNewMothSl.setSelection(Global.SpinnerItemPositionAnyLength(spnNewMothSl, String.valueOf(item.getNewMothSl())));
                //spnOldMothSl.setSelection(Global.SpinnerItemPositionAnyLength(spnOldMothSl, String.valueOf(item.getOldMothSl())));

                String PMoNoValue = C.ReturnSingleValue("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + item.getOldMothSl() + "'");
                if (PMoNoValue.equals("") || PMoNoValue.isEmpty()){
                    spnOldMothSl.setAdapter(C.getArrayAdapter("Select '00-Not a member of this House'"));
                } else {
                    spnOldMothSl.setAdapter(C.getArrayAdapter("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + item.getOldMothSl() + "'"));
                }

                txtRnd.setText(String.valueOf(item.getRnd()));
                txtMothSlNote.setText(item.getMothSlNote());
                dtpMothVDate.setText(item.getMothVDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getMothVDate()));
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_MotherSerial.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpMothSlEvDate);
            if (VariableID.equals("btnMothSlEvDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpMothSlEvDate);
            } else if (VariableID.equals("btnMothVDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpMothVDate);
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


    // turning off the GPS if its in on state. to avoid the battery drain.
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}