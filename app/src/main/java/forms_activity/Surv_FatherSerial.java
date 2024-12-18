
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
import forms_datamodel.tmpFatherSerial_DataModel;

public class Surv_FatherSerial extends AppCompatActivity {
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
    LinearLayout secFathSerialID;
    View lineFathSerialID;
    TextView VlblFathSerialID;
    EditText txtFathSerialID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secFathSlEvDate;
    View lineFathSlEvDate;
    TextView VlblFathSlEvDate;
    EditText dtpFathSlEvDate;
    LinearLayout secNewFathSl;
    View lineNewFathSl;
    TextView VlblNewFathSl;
    Spinner spnNewFathSl;
    LinearLayout secOldFathSl;
    View lineOldFathSl;
    TextView VlblOldFathSl;
    //    EditText txtOldFathSl;
    Spinner spnOldFathSl;
    LinearLayout secFathSlNote;
    View lineFathSlNote;
    TextView VlblFathSlNote;
    EditText txtFathSlNote;

    LinearLayout secFathSlNote1;
    View lineFathSlNote1;
    LinearLayout secFathVDate;
    View lineFathVDate;
    TextView VlblFathVDate;
    EditText dtpFathVDate;

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
     String FATHSERIALID = "";
     String MEM_ID = "";
     String HHID = "";
     String EV_TYPE = "";
     String ROUND = "";
     String VISIT_DATE = "";
     String AGE_DAYS = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.surv_fatherserial);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            FATHSERIALID = IDbundle.getString("FathSerialID");
            MEM_ID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            EV_TYPE = IDbundle.getString("evtype");
            ROUND = IDbundle.getString("round");
            VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy
            AGE_DAYS = IDbundle.getString("age");

            TableName = "tmpFatherSerial";
            MODULEID = 33;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_FatherSerial.this);
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
            Connection.LocalizeLanguage(Surv_FatherSerial.this, MODULEID, LANGUAGEID);

            String Sp1 = C.ReturnSingleValue("SELECT Sp1 from tmpMemberMove where MemID='"+MEM_ID+"'");
            String Sp2 = C.ReturnSingleValue("SELECT Sp2 from tmpMemberMove where MemID='"+MEM_ID+"'");
            String Sp3 = C.ReturnSingleValue("SELECT Sp3 from tmpMemberMove where MemID='"+MEM_ID+"'");
            String Sp4 = C.ReturnSingleValue("SELECT Sp4 from tmpMemberMove where MemID='"+MEM_ID+"'");
            spnNewFathSl.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and Sex='1' and MemID <> '"+MEM_ID+"' and julianday('now') - julianday(BDate) > 3652 and MS<>'30' and (MSlNo <> '"+Sp1+"' and MSlNo <> '"+Sp2+"' and MSlNo <> '"+Sp3+"' and MSlNo <> '"+Sp4+"') union Select '00-Not a Member of this House'"));

            String PFaNo = C.ReturnSingleValue("Select FaNo from tmpMemberMove where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            if (!PFaNo.equals("00")) {
                String PMoNoValue = C.ReturnSingleValue("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + PFaNo.toString() + "'");
                if (PMoNoValue.equals("") || PMoNoValue.isEmpty()) {
                    spnOldFathSl.setAdapter(C.getArrayAdapter("Select '00-Not a member of this House'"));
                } else {
                    spnOldFathSl.setAdapter(C.getArrayAdapter("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + PFaNo.toString() + "'"));
                }
            }
            if (PFaNo.equals("00") || PFaNo.equals("")) {
                spnOldFathSl.setAdapter(C.getArrayAdapter("Select '00-Not a member of this House'"));
            }

            spnOldFathSl.setEnabled(false);

            dtpFathSlEvDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnFathSlEvDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });
            dtpFathVDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnFathVDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });

            //Hide all skip variables


            DataSearch(FATHSERIALID);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(Surv_FatherSerial.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            lblEvCode = findViewById(R.id.lblEvCode);
            lblEvCode.setText(EV_TYPE);

            secFathSerialID = (LinearLayout) findViewById(R.id.secFathSerialID);
            lineFathSerialID = (View) findViewById(R.id.lineFathSerialID);
            VlblFathSerialID = (TextView) findViewById(R.id.VlblFathSerialID);
            txtFathSerialID = (EditText) findViewById(R.id.txtFathSerialID);
            if (FATHSERIALID.length() == 0) txtFathSerialID.setText(Global.Get_UUID(DEVICEID));
            else txtFathSerialID.setText(FATHSERIALID);
            txtFathSerialID.setEnabled(false);

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
            secFathSlEvDate = (LinearLayout) findViewById(R.id.secFathSlEvDate);
            lineFathSlEvDate = (View) findViewById(R.id.lineFathSlEvDate);
            VlblFathSlEvDate = (TextView) findViewById(R.id.VlblFathSlEvDate);
            dtpFathSlEvDate = (EditText) findViewById(R.id.dtpFathSlEvDate);
//         dtpFathSlEvDate.setText(Global.DateNowDMY());

            secNewFathSl = (LinearLayout) findViewById(R.id.secNewFathSl);
            lineNewFathSl = (View) findViewById(R.id.lineNewFathSl);
            VlblNewFathSl = (TextView) findViewById(R.id.VlblNewFathSl);
            spnNewFathSl = (Spinner) findViewById(R.id.spnNewFathSl);
            String Sp1 = C.ReturnSingleValue("SELECT Sp1 from tmpMemberMove where MemID='"+MEM_ID+"'");
            String Sp2 = C.ReturnSingleValue("SELECT Sp2 from tmpMemberMove where MemID='"+MEM_ID+"'");
            String Sp3 = C.ReturnSingleValue("SELECT Sp3 from tmpMemberMove where MemID='"+MEM_ID+"'");
            String Sp4 = C.ReturnSingleValue("SELECT Sp4 from tmpMemberMove where MemID='"+MEM_ID+"'");
            spnNewFathSl.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and Sex='1' and MemID <> '"+MEM_ID+"' and julianday('now') - julianday(BDate) > 3652 and MS<>'30' and (MSlNo <> '"+Sp1+"' and MSlNo <> '"+Sp2+"' and MSlNo <> '"+Sp3+"' and MSlNo <> '"+Sp4+"') union Select '00-Not a Member of this House'"));

            secOldFathSl = (LinearLayout) findViewById(R.id.secOldFathSl);
            lineOldFathSl = (View) findViewById(R.id.lineOldFathSl);
            VlblOldFathSl = (TextView) findViewById(R.id.VlblOldFathSl);
            spnOldFathSl = (Spinner) findViewById(R.id.spnOldFathSl);
//         spnOldFathSl.setEnabled(false);

            String PFaNo = C.ReturnSingleValue("Select FaNo from tmpMemberMove where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            if (!PFaNo.equals("00")) {
                String PMoNoValue = C.ReturnSingleValue("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + PFaNo.toString() + "'");
                if (PMoNoValue.equals("") || PMoNoValue.isEmpty()) {
                    spnOldFathSl.setAdapter(C.getArrayAdapter("Select '00-Not a member of this House'"));
                } else {
                    spnOldFathSl.setAdapter(C.getArrayAdapter("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + PFaNo.toString() + "'"));
                }
            }
            if (PFaNo.equals("00") || PFaNo.equals("")) {
                spnOldFathSl.setAdapter(C.getArrayAdapter("Select '00-Not a member of this House'"));
            }

            secFathVDate = (LinearLayout) findViewById(R.id.secFathVDate);
            lineFathVDate = (View) findViewById(R.id.lineFathVDate);
            VlblFathVDate = (TextView) findViewById(R.id.VlblFathVDate);
            dtpFathVDate = (EditText) findViewById(R.id.dtpFathVDate);
            dtpFathVDate.setText(VISIT_DATE);
            secFathVDate.setVisibility(View.GONE);

            secFathSlNote = (LinearLayout) findViewById(R.id.secFathSlNote);
            lineFathSlNote = (View) findViewById(R.id.lineFathSlNote);
            VlblFathSlNote = (TextView) findViewById(R.id.VlblFathSlNote);
            txtFathSlNote = (EditText) findViewById(R.id.txtFathSlNote);
            secFathSlNote1 = (LinearLayout) findViewById(R.id.secFathSlNote1);
            lineFathSlNote1 = (View) findViewById(R.id.lineFathSlNote1);
            secRnd = (LinearLayout) findViewById(R.id.secRnd);
            lineRnd = (View) findViewById(R.id.lineRnd);
            VlblRnd = (TextView) findViewById(R.id.VlblRnd);
            txtRnd = (EditText) findViewById(R.id.txtRnd);
            txtRnd.setText(ROUND);
            txtRnd.setEnabled(false);
        } catch (Exception e) {
            Connection.MessageBox(Surv_FatherSerial.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(Surv_FatherSerial.this, ValidationMSG);
                return;
            }
            String SQL = "";
            RadioButton rb;

            tmpFatherSerial_DataModel objSave = new tmpFatherSerial_DataModel();
            objSave.setFathSerialID(txtFathSerialID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setFathSlEvDate(dtpFathSlEvDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpFathSlEvDate.getText().toString()) : dtpFathSlEvDate.getText().toString());
            objSave.setNewFathSl(spnNewFathSl.getSelectedItemPosition() == 0 ? "" : spnNewFathSl.getSelectedItem().toString().split("-")[0]);
            //objSave.setOldFathSl(spnOldFathSl.getSelectedItemPosition() == 0 ? "" : spnOldFathSl.getSelectedItem().toString().split("-")[0]);
            objSave.setOldFathSl(spnOldFathSl.getSelectedItem().toString().split("-")[0]);
            objSave.setFathVDate(dtpFathVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpFathVDate.getText().toString()) : dtpFathVDate.getText().toString());
            objSave.setRnd(txtRnd.getText().toString());
            objSave.setFathSlNote(txtFathSlNote.getText().toString());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);
            if (status.length() == 0) {

                C.SaveData("Update tmpMember set Upload='2',FaNo='" + (spnNewFathSl.getSelectedItemPosition() == 0 ? "0" : spnNewFathSl.getSelectedItem().toString().split("-")[0]) + "',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where MemID='" + MEM_ID + "' ");
                C.SaveData("Update tmpMemberMove set Upload='2',FaNo='" + (spnNewFathSl.getSelectedItemPosition() == 0 ? "0" : spnNewFathSl.getSelectedItem().toString().split("-")[0]) + "',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where MemID='" + MEM_ID + "' AND HHID = '"+HHID+"'");

                Intent returnIntent = new Intent();
                returnIntent.putExtra("res", "");
                setResult(Activity.RESULT_OK, returnIntent);

                Toast.makeText(getApplicationContext(), "Save Successfully...", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(Surv_FatherSerial.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_FatherSerial.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        try {
            ResetSectionColor();
            if (txtFathSerialID.getText().toString().length() == 0 & secFathSerialID.isShown()) {
                ValidationMsg += "\nRequired field: Father serial number Internal ID.";
                secFathSerialID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtHHID.getText().toString().length() == 0 & secHHID.isShown()) {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtMemID.getText().toString().length() == 0 & secMemID.isShown()) {
                ValidationMsg += "\nRequired field: Member internal ID.";
                secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpFathSlEvDate.getText().toString());
            if (DV.length() != 0 & secFathSlEvDate.isShown()) {
                ValidationMsg += "\n1. Required field/Not a valid date format: When has this member’s father’s serial changed?.";
                secFathSlEvDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }

            if(AGE_DAYS != null){
                if(DV.length() == 0 & Integer.parseInt(AGE_DAYS) < Global.DateDifferenceDays(Global.DateNowDMY(), dtpFathSlEvDate.getText().toString())){
                    ValidationMsg += "\n1. Required field: Member's father's serial change date should be greater than birth date";
                    secFathSlEvDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }
            }


            if (spnNewFathSl.getSelectedItemPosition() == 0 & secNewFathSl.isShown()) {
                ValidationMsg += "\n2. Required field: What is the new serial number?.";
                secNewFathSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
//         if(spnOldFathSl.getSelectedItemPosition()==0  & secOldFathSl.isShown())
//         {
//             ValidationMsg += "\n3. Required field: What was the previous serial number?.";
//             secOldFathSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//         }
            if (txtRnd.getText().toString().length() == 0 & secRnd.isShown()) {
                ValidationMsg += "\nRequired field: Round.";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secRnd.isShown() & (Integer.valueOf(txtRnd.getText().toString().length() == 0 ? "1" : txtRnd.getText().toString()) < 1 || Integer.valueOf(txtRnd.getText().toString().length() == 0 ? "99" : txtRnd.getText().toString()) > 99)) {
                ValidationMsg += "\nValue should be between 1 and 99(Round).";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpFathVDate.getText().toString());
            if (DV.length() != 0 & secFathVDate.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: Visit Date.";
                secFathVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
//         if(txtOldFathSl.getText().toString().length()==0 & secOldFathSl.isShown())
//           {
//             ValidationMsg += "\n3. Required field: What was the previous serial number?.";
//             secOldFathSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
//         if(secOldFathSl.isShown() & (Integer.valueOf(txtOldFathSl.getText().toString().length()==0 ? "1" : txtOldFathSl.getText().toString()) < 1 || Integer.valueOf(txtOldFathSl.getText().toString().length()==0 ? "99" : txtOldFathSl.getText().toString()) > 99))
//           {
//             ValidationMsg += "\n3. Value should be between 1 and 99(What was the previous serial number?).";
//             secOldFathSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
/*         if(txtFathSlNote.getText().toString().length()==0 & secFathSlNote.isShown())
           {
             ValidationMsg += "\nRequired field: Note.";
             secFathSlNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/

            if(spnNewFathSl.getSelectedItemPosition() != 0){
                Integer NewFatSl = Integer.parseInt(spnNewFathSl.getSelectedItem().toString().split("-")[0]);
                Integer PrvFatSl = Integer.parseInt(spnOldFathSl.getSelectedItem().toString().split("-")[0]);

                if (NewFatSl.equals(PrvFatSl)) {
                    ValidationMsg += "\n New Father serial number and Old Father serial number should not same";
                    secNewFathSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    secOldFathSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }
            }

        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor() {
        try {
            secFathSerialID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secFathSlEvDate.setBackgroundColor(Color.WHITE);
            secNewFathSl.setBackgroundColor(Color.WHITE);
            secOldFathSl.setBackgroundColor(Color.WHITE);
            secOldFathSl.setBackgroundColor(Color.WHITE);
            secFathSlNote.setBackgroundColor(Color.WHITE);
            secRnd.setBackgroundColor(Color.WHITE);
            secFathVDate.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String FathSerialID) {
        try {
            RadioButton rb;
            tmpFatherSerial_DataModel d = new tmpFatherSerial_DataModel();
            String SQL = "Select * from " + TableName + "  Where FathSerialID='" + FathSerialID + "'";
            List<tmpFatherSerial_DataModel> data = d.SelectAll(this, SQL);
            for (tmpFatherSerial_DataModel item : data) {
                txtFathSerialID.setText(item.getFathSerialID());
                txtHHID.setText(item.getHHID());
                txtMemID.setText(item.getMemID());
                dtpFathSlEvDate.setText(item.getFathSlEvDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getFathSlEvDate()));
                spnNewFathSl.setSelection(Global.SpinnerItemPositionAnyLength(spnNewFathSl, String.valueOf(item.getNewFathSl())));
                //spnOldFathSl.setSelection(Global.SpinnerItemPositionAnyLength(spnOldFathSl, String.valueOf(item.getOldFathSl())));
                String PMoNoValue = C.ReturnSingleValue("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + item.getOldFathSl() + "'");
                if (PMoNoValue.equals("") || PMoNoValue.isEmpty()){
                    spnOldFathSl.setAdapter(C.getArrayAdapter("Select '00-Not a member of this House'"));
                } else {
                    spnOldFathSl.setAdapter(C.getArrayAdapter("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + item.getOldFathSl() + "'"));
                }
                dtpFathVDate.setText(item.getFathVDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getFathVDate()));
                txtRnd.setText(String.valueOf(item.getRnd()));
                txtFathSlNote.setText(item.getFathSlNote());
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_FatherSerial.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpFathSlEvDate);
            if (VariableID.equals("btnFathSlEvDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpFathSlEvDate);
            } else if (VariableID.equals("btnFathVDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpFathVDate);
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