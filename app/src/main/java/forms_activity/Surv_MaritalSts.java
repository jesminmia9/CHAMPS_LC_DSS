
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
import Common.Global_CodeList;
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import forms_datamodel.tmpMaritalSts_DataModel;

public class Surv_MaritalSts extends AppCompatActivity {
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
    LinearLayout secMaritStsID;
    View lineMaritStsID;
    TextView VlblMaritStsID;
    EditText txtMaritStsID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secMSEvType;
    View lineMSEvType;
    TextView VlblMSEvType;
    Spinner spnMSEvType;
    /*RadioGroup rdogrpMSEvType;
    RadioButton rdoMSEvType1;
    RadioButton rdoMSEvType2;
    RadioButton rdoMSEvType3;
    RadioButton rdoMSEvType4;
    RadioButton rdoMSEvType5;
    RadioButton rdoMSEvType6;*/
    LinearLayout secMSEvDate;
    View lineMSEvDate;
    TextView VlblMSEvDate;
    EditText dtpMSEvDate;
    LinearLayout secPrevMS;
    View linePrevMS;
    TextView VlblPrevMS;
    Spinner spnPrevMS;
    LinearLayout secMSVDate;
    View lineMSVDate;
    TextView VlblMSVDate;
    EditText dtpMSVDate;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secMSNote;
    View lineMSNote;
    TextView VlblMSNote;
    EditText txtMSNote;
    TextView lblEvCode;
     int MODULEID = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String MARITSTSID = "";
     String MEM_ID = "";
     String HHID = "";
     String EV_TYPE = "";
     String ROUND = "";
     String VISIT_DATE = "";
     String MS = "";

     String EvTypeMS = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.surv_maritalsts);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            MARITSTSID = IDbundle.getString("MaritStsID");
            MEM_ID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            EV_TYPE = IDbundle.getString("evtype");
            MS = IDbundle.getString("ms");
            ROUND = IDbundle.getString("round");
            VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy

            TableName = "tmpMaritalSts";
            MODULEID = 25;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_MaritalSts.this);
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
            Connection.LocalizeLanguage(Surv_MaritalSts.this, MODULEID, LANGUAGEID);
            spnPrevMS.setSelection(Global.SpinnerItemPositionAnyLength(spnPrevMS,MS));
            spnPrevMS.setEnabled(false);
            //Hide Spinner item


            dtpMSEvDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnMSEvDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });
            dtpMSVDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnMSVDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            DataSearch(MARITSTSID);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(Surv_MaritalSts.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            lblEvCode = findViewById(R.id.lblEvCode);
            lblEvCode.setText(EV_TYPE);

            secMaritStsID = (LinearLayout) findViewById(R.id.secMaritStsID);
            lineMaritStsID = (View) findViewById(R.id.lineMaritStsID);
            VlblMaritStsID = (TextView) findViewById(R.id.VlblMaritStsID);
            txtMaritStsID = (EditText) findViewById(R.id.txtMaritStsID);
//         txtMaritStsID.setText(MARITSTSID);
//         txtMaritStsID.setEnabled(false);
            if (MARITSTSID.length() == 0) txtMaritStsID.setText(Global.Get_UUID(DEVICEID));
            else txtMaritStsID.setText(MARITSTSID);
            txtMaritStsID.setEnabled(false);

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
            txtMemID.setEnabled(false);

            secMSEvType = (LinearLayout) findViewById(R.id.secMSEvType);
            lineMSEvType = (View) findViewById(R.id.lineMSEvType);
            VlblMSEvType = (TextView) findViewById(R.id.VlblMSEvType);
            spnMSEvType = (Spinner) findViewById(R.id.spnMSEvType);
            //spnMSEvType.setAdapter(C.getArrayAdapter("Select '' union Select Code||'-'||Name from MStatus where Code not in('"+ MS +"','7','8','9')"));
            spnMSEvType.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_MARITAL_STATUS())));

            secMSEvDate = (LinearLayout) findViewById(R.id.secMSEvDate);
            lineMSEvDate = (View) findViewById(R.id.lineMSEvDate);
            VlblMSEvDate = (TextView) findViewById(R.id.VlblMSEvDate);
            dtpMSEvDate = (EditText) findViewById(R.id.dtpMSEvDate);
            secPrevMS = (LinearLayout) findViewById(R.id.secPrevMS);
            linePrevMS = (View) findViewById(R.id.linePrevMS);
            VlblPrevMS = (TextView) findViewById(R.id.VlblPrevMS);
            spnPrevMS = (Spinner) findViewById(R.id.spnPrevMS);

            //spnPrevMS.setAdapter(C.getArrayAdapter("Select Code||'-'||Name from MStatus"));
            spnPrevMS.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_MARITAL_STATUS())));
            spnPrevMS.setSelection(Global.SpinnerItemPositionAnyLength(spnPrevMS,MS));
            spnPrevMS.setEnabled(false);


            secMSVDate = (LinearLayout) findViewById(R.id.secMSVDate);
            lineMSVDate = (View) findViewById(R.id.lineMSVDate);
            VlblMSVDate = (TextView) findViewById(R.id.VlblMSVDate);
            dtpMSVDate = (EditText) findViewById(R.id.dtpMSVDate);
            dtpMSVDate.setText(VISIT_DATE);
            secMSVDate.setVisibility(View.GONE);

            secRnd = (LinearLayout) findViewById(R.id.secRnd);
            lineRnd = (View) findViewById(R.id.lineRnd);
            VlblRnd = (TextView) findViewById(R.id.VlblRnd);
            txtRnd = (EditText) findViewById(R.id.txtRnd);
            txtRnd.setText(ROUND);
            txtRnd.setEnabled(false);
            secMSNote = (LinearLayout) findViewById(R.id.secMSNote);
            lineMSNote = (View) findViewById(R.id.lineMSNote);
            VlblMSNote = (TextView) findViewById(R.id.VlblMSNote);
            txtMSNote = (EditText) findViewById(R.id.txtMSNote);
        } catch (Exception e) {
            Connection.MessageBox(Surv_MaritalSts.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(Surv_MaritalSts.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            tmpMaritalSts_DataModel objSave = new tmpMaritalSts_DataModel();
            objSave.setMaritStsID(txtMaritStsID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());


            objSave.setMSEvType(spnMSEvType.getSelectedItem().toString().split("-")[0]);

            objSave.setMSEvDate(dtpMSEvDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpMSEvDate.getText().toString()) : dtpMSEvDate.getText().toString());
            objSave.setPrevMS(spnPrevMS.getSelectedItem().toString().split("-")[0]);
            objSave.setMSVDate(dtpMSVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpMSVDate.getText().toString()) : dtpMSVDate.getText().toString());
            objSave.setRnd(txtRnd.getText().toString());
            objSave.setMSNote(txtMSNote.getText().toString());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);
            if (status.length() == 0) {

                C.SaveData("Update tmpMember set Upload='2',modifydate='"+ Global.DateTimeNowYMDHMS() +"',MS='" + spnMSEvType.getSelectedItem().toString().split("-")[0] + "' where MemID='" + MEM_ID + "' ");
                C.SaveData("Update tmpMemberMove set Upload='2',modifydate='"+ Global.DateTimeNowYMDHMS() +"',MS='" + spnMSEvType.getSelectedItem().toString().split("-")[0] + "' where MemID='" + MEM_ID + "' AND HHID = '"+ HHID +"'");

                Intent returnIntent = new Intent();
                returnIntent.putExtra("res", "");
                setResult(Activity.RESULT_OK, returnIntent);

                Toast.makeText(getApplicationContext(), "Save Successfully...", Toast.LENGTH_SHORT).show();

                finish();
            } else {
                Connection.MessageBox(Surv_MaritalSts.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_MaritalSts.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        try {
            ResetSectionColor();
            if (txtMaritStsID.getText().toString().length() == 0 & secMaritStsID.isShown()) {
                ValidationMsg += "\nRequired field: Internal Marital Status ID.";
                secMaritStsID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtHHID.getText().toString().length() == 0 & secHHID.isShown()) {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtMemID.getText().toString().length() == 0 & secMemID.isShown()) {
                ValidationMsg += "\nRequired field: Member ID.";
                secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnMSEvType.getSelectedItemPosition()==0 & secMSEvType.isShown()) {
                ValidationMsg += "\nRequired field: Event Type of marital status change.";
                secMSEvType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpMSEvDate.getText().toString());
            if (DV.length() != 0 & secMSEvDate.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: Event Date.";
                secMSEvDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
//         if(spnPrevMS.getSelectedItemPosition()==0  & secPrevMS.isShown())
//           {
//             ValidationMsg += "\nRequired field: What was the previous marital status?.";
//             secPrevMS.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
            if (spnPrevMS.getSelectedItem().toString().split("-")[0].equals("01")
                    & spnMSEvType.getSelectedItem().toString().split("-")[0].equals("00")
                    & secMSEvType.isShown() & secPrevMS.isShown()) {
                ValidationMsg += "\nRequired field: If the previous marital status is married, the current status can't be single/never married.";
                secMSEvType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnPrevMS.getSelectedItem().toString().split("-")[0].equals("00")
                    & spnMSEvType.getSelectedItem().toString().split("-")[0].equals("04")
                    & secMSEvType.isShown() & secPrevMS.isShown()) {
                ValidationMsg += "\nRequired field: If the previous marital status is single/never married, the current status can't be divorced.";
                secMSEvType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnPrevMS.getSelectedItem().toString().split("-")[0].equals("04")
                    & spnMSEvType.getSelectedItem().toString().split("-")[0].equals("05")
                    & secMSEvType.isShown() & secPrevMS.isShown()) {
                ValidationMsg += "\nRequired field: If the previous marital status is divorced, the current status can't be separated.";
                secMSEvType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpMSVDate.getText().toString());
            if (DV.length() != 0 & secMSVDate.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: Visit date.";
                secMSVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if(spnMSEvType.getSelectedItem().toString().split("-")[0].equals(spnPrevMS.getSelectedItem().toString().split("-")[0])){
                ValidationMsg += "\nPrevious and current marital status should not be same.";
                spnMSEvType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                spnPrevMS.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
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
            secMaritStsID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secMSEvType.setBackgroundColor(Color.WHITE);
            secMSEvDate.setBackgroundColor(Color.WHITE);
            secPrevMS.setBackgroundColor(Color.WHITE);
            secMSVDate.setBackgroundColor(Color.WHITE);
            secRnd.setBackgroundColor(Color.WHITE);
            secMSNote.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String MaritStsID) {
        try {
            RadioButton rb;
            tmpMaritalSts_DataModel d = new tmpMaritalSts_DataModel();
            String SQL = "Select * from " + TableName + "  Where MaritStsID='" + MaritStsID + "'";
            List<tmpMaritalSts_DataModel> data = d.SelectAll(this, SQL);
            for (tmpMaritalSts_DataModel item : data) {
                Log.d("JM ===>>", item.getPrevMS());
                txtMaritStsID.setText(item.getMaritStsID());
                txtHHID.setText(item.getHHID());
                txtMemID.setText(item.getMemID());
                /*String[] d_rdogrpMSEvType = new String[]{"1", "2", "3", "4", "5", "6"};
                for (int i = 0; i < d_rdogrpMSEvType.length; i++) {
                    if (String.valueOf(item.getMSEvType()).equals(String.valueOf(d_rdogrpMSEvType[i]))) {
                        rb = (RadioButton) rdogrpMSEvType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }*/
                spnMSEvType.setSelection(Global.SpinnerItemPositionAnyLength(spnMSEvType,item.getMSEvType()));
                dtpMSEvDate.setText(item.getMSEvDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getMSEvDate()));
                //spnPrevMS.setSelection(Global.SpinnerItemPositionAnyLength(spnPrevMS, String.valueOf(item.getPrevMS())));
                //spnPrevMS.setAdapter(C.getArrayAdapter("Select Code||'-'||Name from MStatus WHERE Code='" + item.getPrevMS() + "'"));
                spnPrevMS.setSelection(Global.SpinnerItemPositionAnyLength(spnPrevMS,item.getPrevMS()));
                dtpMSVDate.setText(item.getMSVDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getMSVDate()));
                txtRnd.setText(item.getRnd());
                txtMSNote.setText(item.getMSNote());
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_MaritalSts.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpMSEvDate);
            if (VariableID.equals("btnMSEvDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpMSEvDate);
            } else if (VariableID.equals("btnMSVDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpMSVDate);
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