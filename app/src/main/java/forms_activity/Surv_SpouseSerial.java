
package forms_activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import forms_datamodel.tmpSpouseSerial_DataModel;

public class Surv_SpouseSerial extends AppCompatActivity {
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
    LinearLayout secSpSerialID;
    View lineSpSerialID;
    TextView VlblSpSerialID;
    EditText txtSpSerialID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secSpSlEvDate;
    View lineSpSlEvDate;
    TextView VlblSpSlEvDate;
    EditText dtpSpSlEvDate;
    LinearLayout secNewSpSl;
    View lineNewSpSl;
    TextView VlblNewSpSl;
    Spinner spnNewSpSl;
    LinearLayout secOldSpSl;
    View lineOldSpSl;
    TextView VlblOldSpSl;
    //    EditText txtOldSpSl;
    Spinner spnOldSpSl;
    LinearLayout secSpSlNote;
    View lineSpSlNote;
    TextView VlblSpSlNote;
    EditText txtSpSlNote;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secSpVDate;
    View lineSpVDate;
    TextView VlblSpVDate;
    EditText dtpSpVDate;

    LinearLayout secSpSlNote1;
    View lineSpSlNote1;
    TextView lblEvCode;
     int MODULEID = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String SPSERIALID = "";
     String MEM_ID = "";
     String HHID = "";
     String EV_TYPE = "";
     String ROUND = "";
     String VISIT_DATE = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.surv_spouseserial);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            SPSERIALID = IDbundle.getString("SpSerialID");
            MEM_ID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            EV_TYPE = IDbundle.getString("evtype");
            ROUND = IDbundle.getString("round");
            VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy

            TableName = "tmpSpouseSerial";
            MODULEID = 34;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_SpouseSerial.this);
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
            Connection.LocalizeLanguage(Surv_SpouseSerial.this, MODULEID, LANGUAGEID);

            String MoNo = C.ReturnSingleValue("SELECT MoNo from tmpMember where MemID='"+MEM_ID+"'");
            String FaNo = C.ReturnSingleValue("SELECT FaNo from tmpMember where MemID='"+MEM_ID+"'");

            String sex = C.ReturnSingleValue("SELECT Sex FROM tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            if (sex.equals("1")){
                spnNewSpSl.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and Sex='2' and MemID <> '"+MEM_ID+"' and julianday('now') - julianday(BDate) > 3652 and (MSlNo <> '"+MoNo+"' and MSlNo <> '"+FaNo+"') union Select '0-Not a Member of this House'"));
            } else {
                spnNewSpSl.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and Sex='1' and MemID <> '"+MEM_ID+"' and julianday('now') - julianday(BDate) > 3652 union Select '0-Not a Member of this House'"));
            }

            String PSpNo = C.ReturnSingleValue("Select Sp1 from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            if (!PSpNo.equals("0")) {
                String PMoNoValue = C.ReturnSingleValue("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + PSpNo.toString() + "'");
                if (PMoNoValue.equals("") || PMoNoValue.isEmpty()) {
                    spnOldSpSl.setAdapter(C.getArrayAdapter("Select '0-Not a member of this House'"));
                } else {
                    spnOldSpSl.setAdapter(C.getArrayAdapter("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + PSpNo.toString() + "'"));
                }
            }
            if (PSpNo.equals("0") || PSpNo.equals("")) {
                spnOldSpSl.setAdapter(C.getArrayAdapter("Select '0-Not a member of this House'"));
            }

            dtpSpSlEvDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnSpSlEvDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });
            dtpSpVDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnSpVDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            //Hide all skip variables


            DataSearch(SPSERIALID);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(Surv_SpouseSerial.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            lblEvCode = findViewById(R.id.lblEvCode);
            lblEvCode.setText(EV_TYPE);

            secSpSerialID = (LinearLayout) findViewById(R.id.secSpSerialID);
            lineSpSerialID = (View) findViewById(R.id.lineSpSerialID);
            VlblSpSerialID = (TextView) findViewById(R.id.VlblSpSerialID);
            txtSpSerialID = (EditText) findViewById(R.id.txtSpSerialID);

            if (SPSERIALID.length() == 0) txtSpSerialID.setText(Global.Get_UUID(DEVICEID));
            else txtSpSerialID.setText(SPSERIALID);
            txtSpSerialID.setEnabled(false);

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
            secSpSlEvDate = (LinearLayout) findViewById(R.id.secSpSlEvDate);
            lineSpSlEvDate = (View) findViewById(R.id.lineSpSlEvDate);
            VlblSpSlEvDate = (TextView) findViewById(R.id.VlblSpSlEvDate);
            dtpSpSlEvDate = (EditText) findViewById(R.id.dtpSpSlEvDate);

            secNewSpSl = (LinearLayout) findViewById(R.id.secNewSpSl);
            lineNewSpSl = (View) findViewById(R.id.lineNewSpSl);
            VlblNewSpSl = (TextView) findViewById(R.id.VlblNewSpSl);
            spnNewSpSl = (Spinner) findViewById(R.id.spnNewSpSl);
            String MoNo = C.ReturnSingleValue("SELECT MoNo from tmpMember where MemID='"+MEM_ID+"'");
            String FaNo = C.ReturnSingleValue("SELECT FaNo from tmpMember where MemID='"+MEM_ID+"'");

            String sex = C.ReturnSingleValue("SELECT Sex FROM tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            if (sex.equals("1")){
                spnNewSpSl.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and Sex='2' and MemID <> '"+MEM_ID+"' and julianday('now') - julianday(BDate) > 3652 and (MSlNo <> '"+MoNo+"' and MSlNo <> '"+FaNo+"') union Select '0-Not a Member of this House'"));
            } else {
                spnNewSpSl.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and Sex='1' and MemID <> '"+MEM_ID+"' and julianday('now') - julianday(BDate) > 3652 union Select '0-Not a Member of this House'"));
            }

            secOldSpSl = (LinearLayout) findViewById(R.id.secOldSpSl);
            lineOldSpSl = (View) findViewById(R.id.lineOldSpSl);
            VlblOldSpSl = (TextView) findViewById(R.id.VlblOldSpSl);
            spnOldSpSl = (Spinner) findViewById(R.id.spnOldSpSl);

            String PSpNo = C.ReturnSingleValue("Select Sp1 from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            if (!PSpNo.equals("0")) {
                String PMoNoValue = C.ReturnSingleValue("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + PSpNo.toString() + "'");
                if (PMoNoValue.equals("") || PMoNoValue.isEmpty()) {
                    spnOldSpSl.setAdapter(C.getArrayAdapter("Select '0-Not a member of this House'"));
                } else {
                    spnOldSpSl.setAdapter(C.getArrayAdapter("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + PSpNo.toString() + "'"));
                }
            }
            if (PSpNo.equals("0") || PSpNo.equals("")) {
                spnOldSpSl.setAdapter(C.getArrayAdapter("Select '0-Not a member of this House'"));
            }

            spnOldSpSl.setEnabled(false);
            secSpSlNote = (LinearLayout) findViewById(R.id.secSpSlNote);
            lineSpSlNote = (View) findViewById(R.id.lineSpSlNote);
            VlblSpSlNote = (TextView) findViewById(R.id.VlblSpSlNote);
            txtSpSlNote = (EditText) findViewById(R.id.txtSpSlNote);

            secSpSlNote1 = (LinearLayout) findViewById(R.id.secSpSlNote1);
            lineSpSlNote1 = (View) findViewById(R.id.lineSpSlNote1);
            secRnd = (LinearLayout) findViewById(R.id.secRnd);
            lineRnd = (View) findViewById(R.id.lineRnd);
            VlblRnd = (TextView) findViewById(R.id.VlblRnd);
            txtRnd = (EditText) findViewById(R.id.txtRnd);
            txtRnd.setText(ROUND);
            txtRnd.setEnabled(false);
            secSpVDate = (LinearLayout) findViewById(R.id.secSpVDate);
            lineSpVDate = (View) findViewById(R.id.lineSpVDate);
            VlblSpVDate = (TextView) findViewById(R.id.VlblSpVDate);
            dtpSpVDate = (EditText) findViewById(R.id.dtpSpVDate);
            dtpSpVDate.setText(VISIT_DATE);
            secSpVDate.setVisibility(View.GONE);

        } catch (Exception e) {
            Connection.MessageBox(Surv_SpouseSerial.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(Surv_SpouseSerial.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            tmpSpouseSerial_DataModel objSave = new tmpSpouseSerial_DataModel();
            objSave.setSpSerialID(txtSpSerialID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setSpSlEvDate(dtpSpSlEvDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpSpSlEvDate.getText().toString()) : dtpSpSlEvDate.getText().toString());
            objSave.setNewSpSl(spnNewSpSl.getSelectedItemPosition() == 0 ? "" : spnNewSpSl.getSelectedItem().toString().split("-")[0]);
            //objSave.setOldSpSl(spnOldSpSl.getSelectedItemPosition() == 0 ? "" : spnOldSpSl.getSelectedItem().toString().split("-")[0]);
            objSave.setOldSpSl(spnOldSpSl.getSelectedItem().toString().split("-")[0]);
            objSave.setSpSlNote(txtSpSlNote.getText().toString());
            objSave.setRnd(txtRnd.getText().toString());
            objSave.setSpVDate(dtpSpVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpSpVDate.getText().toString()) : dtpSpVDate.getText().toString());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);
            if (status.length() == 0) {

                List<String> lstSp = Get_MemberInfo(HHID, MEM_ID);
                String SP_NUMBER = "";
                if(Integer.parseInt(lstSp.get(0))==0) SP_NUMBER = "sp1";
                else if(Integer.parseInt(lstSp.get(1))==0) SP_NUMBER = "sp2";
                else if(Integer.parseInt(lstSp.get(2))==0) SP_NUMBER = "sp3";
                else if(Integer.parseInt(lstSp.get(3))==0) SP_NUMBER = "sp4";

                C.SaveData("Update tmpMember set Upload='2',"+ SP_NUMBER +" = '" + (spnNewSpSl.getSelectedItemPosition() == 0 ? "0" : spnNewSpSl.getSelectedItem().toString().split("-")[0]) + "',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where MemID='" + MEM_ID + "' ");
                C.SaveData("Update tmpMemberMove set Upload='2',"+ SP_NUMBER +" = '" + (spnNewSpSl.getSelectedItemPosition() == 0 ? "0" : spnNewSpSl.getSelectedItem().toString().split("-")[0]) + "',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where HHID='"+ HHID +"' and MemID='" + MEM_ID + "' ");

                Intent returnIntent = new Intent();
                returnIntent.putExtra("res", "");
                setResult(Activity.RESULT_OK, returnIntent);

                Toast.makeText(getApplicationContext(), "Save Successfully...", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(Surv_SpouseSerial.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_SpouseSerial.this, e.getMessage());
            return;
        }
    }

    @SuppressLint("Range")
    private List<String> Get_MemberInfo(String _HH_ID, String _Mem_ID){
        Cursor cur = C.ReadData("Select " +
                "(Case when length(ifnull(Sp1,''))=0 then '0' else Sp1 end)sp1," +
                "(Case when length(ifnull(Sp2,''))=0 then '0' else Sp1 end)sp2," +
                "(Case when length(ifnull(Sp3,''))=0 then '0' else Sp1 end)sp3," +
                "(Case when length(ifnull(Sp4,''))=0 then '0' else Sp1 end)sp4" +
                " from tmpMemberMove where HHID='"+ _HH_ID +"' and MemID='"+ _Mem_ID +"'");
        cur.moveToFirst();

        List<String> get_Information = new ArrayList<String>();
        while (!cur.isAfterLast()) {
            get_Information.add(0,cur.getString(cur.getColumnIndex("sp1")));
            get_Information.add(1,cur.getString(cur.getColumnIndex("sp2")));
            get_Information.add(2,cur.getString(cur.getColumnIndex("sp3")));
            get_Information.add(3,cur.getString(cur.getColumnIndex("sp4")));

            cur.moveToNext();
        }
        cur.close();
        return  get_Information;
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        try {
            ResetSectionColor();
            if (txtSpSerialID.getText().toString().length() == 0 & secSpSerialID.isShown()) {
                ValidationMsg += "\nRequired field: spouse serial number Internal ID.";
                secSpSerialID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtHHID.getText().toString().length() == 0 & secHHID.isShown()) {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtMemID.getText().toString().length() == 0 & secMemID.isShown()) {
                ValidationMsg += "\nRequired field: Member internal ID.";
                secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpSpSlEvDate.getText().toString());
            if (DV.length() != 0 & secSpSlEvDate.isShown()) {
                ValidationMsg += "\n1. Required field/Not a valid date format: When has this member’s spouse’s serial changed?.";
                secSpSlEvDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnNewSpSl.getSelectedItemPosition() == 0 & secNewSpSl.isShown()) {
                ValidationMsg += "\n2. Required field: What is the new serial number?.";
                secNewSpSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
//         if(spnOldSpSl.getSelectedItemPosition()==0  & secOldSpSl.isShown())
//         {
//             ValidationMsg += "\n3. Required field: What was the previous serial number?.";
//             secOldSpSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//         }
            if (txtRnd.getText().toString().length() == 0 & secRnd.isShown()) {
                ValidationMsg += "\nRequired field: Round.";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secRnd.isShown() & (Integer.valueOf(txtRnd.getText().toString().length() == 0 ? "1" : txtRnd.getText().toString()) < 1 || Integer.valueOf(txtRnd.getText().toString().length() == 0 ? "99" : txtRnd.getText().toString()) > 99)) {
                ValidationMsg += "\nValue should be between 1 and 99(Round).";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpSpVDate.getText().toString());
            if (DV.length() != 0 & secSpVDate.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: Visit Date.";
                secSpVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
//         if(txtSpSlNote.getText().toString().length()==0 & secSpSlNote.isShown())
//           {
//             ValidationMsg += "\nRequired field: Note.";
//             secSpSlNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
            Integer NewSp = Integer.parseInt(spnNewSpSl.getSelectedItem().toString().split("-")[0]);
            Integer PrvSp = Integer.parseInt(spnOldSpSl.getSelectedItem().toString().split("-")[0]);

            if (NewSp.equals(PrvSp)) {
                ValidationMsg += "\n New Spouse serial number and Old Spouse serial number should not same";
                secNewSpSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                secOldSpSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor() {
        try {
            secSpSerialID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secSpSlEvDate.setBackgroundColor(Color.WHITE);
            secNewSpSl.setBackgroundColor(Color.WHITE);
            secOldSpSl.setBackgroundColor(Color.WHITE);
            secOldSpSl.setBackgroundColor(Color.WHITE);
            secSpSlNote.setBackgroundColor(Color.WHITE);
            secRnd.setBackgroundColor(Color.WHITE);
            secSpVDate.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String SpSerialID) {
        try {
            RadioButton rb;
            tmpSpouseSerial_DataModel d = new tmpSpouseSerial_DataModel();
            String SQL = "Select * from " + TableName + "  Where SpSerialID='" + SpSerialID + "'";
            List<tmpSpouseSerial_DataModel> data = d.SelectAll(this, SQL);
            for (tmpSpouseSerial_DataModel item : data) {
                txtSpSerialID.setText(item.getSpSerialID());
                txtHHID.setText(item.getHHID());
                txtMemID.setText(item.getMemID());
                dtpSpSlEvDate.setText(item.getSpSlEvDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getSpSlEvDate()));
                spnNewSpSl.setSelection(Global.SpinnerItemPositionAnyLength(spnNewSpSl, String.valueOf(item.getNewSpSl())));
                //spnOldSpSl.setSelection(Global.SpinnerItemPositionAnyLength(spnOldSpSl, String.valueOf(item.getOldSpSl())));
                String PMoNoValue = C.ReturnSingleValue("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + item.getOldSpSl() + "'");
                if (PMoNoValue.equals("") || PMoNoValue.isEmpty()){
                    spnOldSpSl.setAdapter(C.getArrayAdapter("Select '0-Not a member of this House'"));
                } else {
                    spnOldSpSl.setAdapter(C.getArrayAdapter("Select MSlNo||'-'||Name from tmpMember where HHID='" + HHID + "' and MSlNo='" + item.getOldSpSl() + "'"));
                }
                txtRnd.setText(String.valueOf(item.getRnd()));
                txtSpSlNote.setText(item.getSpSlNote());
                dtpSpVDate.setText(item.getSpVDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getSpVDate()));
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_SpouseSerial.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpSpSlEvDate);
            if (VariableID.equals("btnSpSlEvDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpSpSlEvDate);
            } else if (VariableID.equals("btnSpVDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpSpVDate);
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