
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
import android.util.Log;
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
import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import Common.Connection;
import Common.Global;
import Common.Global_CodeList;
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import forms_datamodel.Education_DataModel;
import forms_datamodel.tmpEducation_DataModel;

public class Surv_Education extends AppCompatActivity {
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
    LinearLayout secEduID;
    View lineEduID;
    TextView VlblEduID;
    EditText txtEduID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secEduEvDate;
    View lineEduEvDate;
    TextView VlblEduEvDate;
    EditText dtpEduEvDate;
    LinearLayout secNewEdu;
    View lineNewEdu;
    TextView VlblNewEdu;
    Spinner spnNewEdu;
    LinearLayout secOldEdu;
    View lineOldEdu;
    TextView VlblOldEdu;
    Spinner spnOldEdu;
    LinearLayout secEduNote;
    View lineEduNote;
    TextView VlblEduNote;
    EditText txtEduNote;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secEduNote1;
    View lineEduNote1;
    TextView lblEvCode;
    LinearLayout secEduVDate;
    View lineEduVDate;
    TextView VlblEduVDate;
    EditText dtpEduVDate;

     int MODULEID = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String EDUID = "";
     String MEM_ID = "";
     String HHID = "";
     String EV_TYPE = "";
     String ROUND = "";
     String isEdit = "";
     String VISIT_DATE = "";
     String AGE_DAYS = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.surv_education);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            EDUID = IDbundle.getString("EduID");
            MEM_ID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            EV_TYPE = IDbundle.getString("evtype");
            ROUND = IDbundle.getString("round");
            isEdit = IDbundle.getString("isEdit");
            VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy
            AGE_DAYS = IDbundle.getString("age");

            TableName = "tmpEducation";
            MODULEID = 37;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Education.this);
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
            Connection.LocalizeLanguage(Surv_Education.this, MODULEID, LANGUAGEID);

            String PEdu = C.ReturnSingleValue("Select EduY from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            Log.d(PEdu,"previous education.");

            if(PEdu == null || PEdu.length()==0) spnOldEdu.setSelection(1);
            else spnOldEdu.setSelection(Global.SpinnerItemPositionAnyLength(spnOldEdu, PEdu));


            /*List<String> listEDU = Global_CodeList.Get_EDUCATION();
            spnNewEdu.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listEDU)));

            spnOldEdu.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listEDU)));
            String PEdu = C.ReturnSingleValue("Select EduY from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            spnOldEdu.setSelection(Global.SpinnerItemPositionAnyLength(spnOldEdu, PEdu));
            spnOldEdu.setEnabled(false);*/

            dtpEduEvDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnEduEvDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });
            dtpEduVDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnEduVDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            //Hide all skip variables


            DataSearch(EDUID);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(Surv_Education.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            lblEvCode = findViewById(R.id.lblEvCode);
            lblEvCode.setText(EV_TYPE);
            secEduID = (LinearLayout) findViewById(R.id.secEduID);
            lineEduID = (View) findViewById(R.id.lineEduID);
            VlblEduID = (TextView) findViewById(R.id.VlblEduID);
            txtEduID = (EditText) findViewById(R.id.txtEduID);


            if (EDUID.length() == 0) txtEduID.setText(Global.Get_UUID(DEVICEID));
            else txtEduID.setText(EDUID);
            txtEduID.setEnabled(false);

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
            secEduEvDate = (LinearLayout) findViewById(R.id.secEduEvDate);
            lineEduEvDate = (View) findViewById(R.id.lineEduEvDate);
            VlblEduEvDate = (TextView) findViewById(R.id.VlblEduEvDate);
            dtpEduEvDate = (EditText) findViewById(R.id.dtpEduEvDate);
            secNewEdu = (LinearLayout) findViewById(R.id.secNewEdu);
            lineNewEdu = (View) findViewById(R.id.lineNewEdu);
            VlblNewEdu = (TextView) findViewById(R.id.VlblNewEdu);
            spnNewEdu = (Spinner) findViewById(R.id.spnNewEdu);
            //spnNewEdu.setAdapter(C.getArrayAdapter("Select '' union Select Code||'-'||Name from EDU"));
            List<String> listEDU = Global_CodeList.Get_EDUCATION();
            spnNewEdu.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listEDU)));

            secOldEdu = (LinearLayout) findViewById(R.id.secOldEdu);
            lineOldEdu = (View) findViewById(R.id.lineOldEdu);
            VlblOldEdu = (TextView) findViewById(R.id.VlblOldEdu);
            spnOldEdu = (Spinner) findViewById(R.id.spnOldEdu);
            spnOldEdu.setEnabled(false);
            spnOldEdu.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listEDU)));
            /*String PEdu = C.ReturnSingleValue("Select EduY from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            Log.d(PEdu,"previous education.");

            if(PEdu == null || PEdu.length()==0) spnOldEdu.setSelection(1);
            else spnOldEdu.setSelection(Global.SpinnerItemPositionAnyLength(spnOldEdu, PEdu));*/


            secEduNote = (LinearLayout) findViewById(R.id.secEduNote);
            lineEduNote = (View) findViewById(R.id.lineEduNote);
            VlblEduNote = (TextView) findViewById(R.id.VlblEduNote);
            txtEduNote = (EditText) findViewById(R.id.txtEduNote);

            secEduNote1 = (LinearLayout) findViewById(R.id.secEduNote1);
            lineEduNote1 = (View) findViewById(R.id.lineEduNote1);
            secRnd = (LinearLayout) findViewById(R.id.secRnd);
            lineRnd = (View) findViewById(R.id.lineRnd);
            VlblRnd = (TextView) findViewById(R.id.VlblRnd);
            txtRnd = (EditText) findViewById(R.id.txtRnd);
            txtRnd.setText(ROUND);
            txtRnd.setEnabled(false);
            secEduVDate = (LinearLayout) findViewById(R.id.secEduVDate);
            lineEduVDate = (View) findViewById(R.id.lineEduVDate);
            VlblEduVDate = (TextView) findViewById(R.id.VlblEduVDate);
            dtpEduVDate = (EditText) findViewById(R.id.dtpEduVDate);
            dtpEduVDate.setText(VISIT_DATE);
            secEduVDate.setVisibility(View.GONE);

        } catch (Exception e) {
            Connection.MessageBox(Surv_Education.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(Surv_Education.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            tmpEducation_DataModel objSave = new tmpEducation_DataModel();
            objSave.setEduID(txtEduID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setEduEvDate(dtpEduEvDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpEduEvDate.getText().toString()) : dtpEduEvDate.getText().toString());
            objSave.setNewEdu(spnNewEdu.getSelectedItemPosition() == 0 ? "" : spnNewEdu.getSelectedItem().toString().split("-")[0]);
            objSave.setOldEdu(spnOldEdu.getSelectedItemPosition() == 0 ? "" : spnOldEdu.getSelectedItem().toString().split("-")[0]);
            objSave.setEduNote(txtEduNote.getText().toString());
            objSave.setEduVDate(dtpEduVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpEduVDate.getText().toString()) : dtpEduVDate.getText().toString());
            objSave.setRnd(txtRnd.getText().toString());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);
            if (status.length() == 0) {
                C.SaveData("Update tmpMember set Upload='2',EduY='" + (spnNewEdu.getSelectedItemPosition() == 0 ? "0" : spnNewEdu.getSelectedItem().toString().split("-")[0]) + "',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where MemID='" + MEM_ID + "' ");
                C.SaveData("Update tmpMemberMove set Upload='2',EduY='" + (spnNewEdu.getSelectedItemPosition() == 0 ? "0" : spnNewEdu.getSelectedItem().toString().split("-")[0]) + "',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where MemID='" + MEM_ID + "' AND HHID = '"+HHID+"'");

                Intent returnIntent = new Intent();
                returnIntent.putExtra("res", "");
                setResult(Activity.RESULT_OK, returnIntent);

                Toast.makeText(getApplicationContext(), "Save Successfully...", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(Surv_Education.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_Education.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        try {
            ResetSectionColor();
            if (txtEduID.getText().toString().length() == 0 & secEduID.isShown()) {
                ValidationMsg += "\nRequired field: Education Internal ID.";
                secEduID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtHHID.getText().toString().length() == 0 & secHHID.isShown()) {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtMemID.getText().toString().length() == 0 & secMemID.isShown()) {
                ValidationMsg += "\nRequired field: Member internal ID.";
                secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpEduEvDate.getText().toString());
            if (DV.length() != 0 & secEduEvDate.isShown()) {
                ValidationMsg += "\n1. Required field/Not a valid date format: When has this member’s level of education changed?.";
                secEduEvDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }

            if(AGE_DAYS != null){
                if(DV.length() == 0 & Integer.parseInt(AGE_DAYS) < Global.DateDifferenceDays(Global.DateNowDMY(), dtpEduEvDate.getText().toString())){
                    ValidationMsg += "\n1. Required field: Member's Education change date should be greater than birth date";
                    secEduEvDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }
            }


            if (spnNewEdu.getSelectedItemPosition() == 0 & secNewEdu.isShown()) {
                ValidationMsg += "\n2. Required field: What is your current education level?.";
                secNewEdu.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
//         if(spnOldEdu.getSelectedItemPosition()==0  & secOldEdu.isShown())
//         {
//             ValidationMsg += "\n3. Required field: What was the previous education level?.";
//             secOldEdu.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//         }
            if (txtRnd.getText().toString().length() == 0 & secRnd.isShown()) {
                ValidationMsg += "\nRequired field: Round.";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secRnd.isShown() & (Integer.valueOf(txtRnd.getText().toString().length() == 0 ? "1" : txtRnd.getText().toString()) < 1 || Integer.valueOf(txtRnd.getText().toString().length() == 0 ? "99" : txtRnd.getText().toString()) > 99)) {
                ValidationMsg += "\nValue should be between 1 and 99(Round).";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpEduVDate.getText().toString());
            if (DV.length() != 0 & secEduVDate.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: Visit Date.";
                secEduVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
//         if(txtEduNote.getText().toString().length()==0 & secEduNote.isShown())
//           {
//             ValidationMsg += "\nRequired field: Note.";
//             secEduNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }

            if(spnNewEdu.getSelectedItemPosition() != 0){
                Integer NewEduCode = Integer.parseInt(spnNewEdu.getSelectedItem().toString().split("-")[0]);
                Integer PrvEduCode = Integer.parseInt(spnOldEdu.getSelectedItem().toString().split("-")[0]);
                Integer AgeInYears = Integer.parseInt(C.ReturnSingleValue("SELECT CAST((julianday('now') - julianday(BDate)) / 365.25 AS INT) AS AgeInYears FROM tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'"));

                if (NewEduCode.equals(PrvEduCode)) {
                    ValidationMsg += "\n Current education level and Previous education level should not be the same";
                    secOldEdu.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    secNewEdu.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }

                if ( NewEduCode != 77 & NewEduCode != 97  & NewEduCode != 98 & NewEduCode != 99 & PrvEduCode != 77 & PrvEduCode != 97 & PrvEduCode != 98 & PrvEduCode != 99) {
                    if(NewEduCode < PrvEduCode && isEdit.equals("1")){
                        ValidationMsg += "\n New education level can't be lower than previous education";
                        secOldEdu.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secNewEdu.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    }
                }

                if (AgeInYears < (NewEduCode + 4) & NewEduCode != 77 & NewEduCode != 97  & NewEduCode != 98 & NewEduCode != 99 & PrvEduCode != 77 & PrvEduCode != 97 & PrvEduCode != 98 & PrvEduCode != 99){
                    ValidationMsg += "\n Education level is not compatible according to age";
                    secOldEdu.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    secNewEdu.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }
            }

        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor() {
        try {
            secEduID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secEduEvDate.setBackgroundColor(Color.WHITE);
            secNewEdu.setBackgroundColor(Color.WHITE);
            secOldEdu.setBackgroundColor(Color.WHITE);
            secOldEdu.setBackgroundColor(Color.WHITE);
            secEduNote.setBackgroundColor(Color.WHITE);
            secRnd.setBackgroundColor(Color.WHITE);
            secEduVDate.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String EduID) {
        try {
            RadioButton rb;
            Education_DataModel d = new Education_DataModel();
            String SQL = "Select * from " + TableName + "  Where EduID='" + EduID + "'";
            List<Education_DataModel> data = d.SelectAll(this, SQL);
            for (Education_DataModel item : data) {
                txtEduID.setText(item.getEduID());
                txtHHID.setText(item.getHHID());
                txtMemID.setText(item.getMemID());
                dtpEduEvDate.setText(item.getEduEvDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getEduEvDate()));
                spnNewEdu.setSelection(Global.SpinnerItemPositionAnyLength(spnNewEdu, String.valueOf(item.getNewEdu())));
                spnOldEdu.setSelection(Global.SpinnerItemPositionAnyLength(spnOldEdu, String.valueOf(item.getOldEdu())));
                //spnOldEdu.setAdapter(C.getArrayAdapter("SELECT Code||'-'||Name FROM EDU WHERE Code ='" + item.getOldEdu() + "'"));
                txtEduNote.setText(item.getEduNote());
                txtRnd.setText(String.valueOf(item.getRnd()));
                dtpEduVDate.setText(item.getEduVDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getEduVDate()));
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_Education.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpEduEvDate);
            if (VariableID.equals("btnEduEvDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpEduEvDate);
            } else if (VariableID.equals("btnEduVDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpEduVDate);
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