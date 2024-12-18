
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Common.Connection;
import Common.Global;
import Common.Global_CodeList;
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import forms_datamodel.tmpRelation_DataModel;

public class Surv_Relation extends AppCompatActivity {
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
    LinearLayout secRthID;
    View lineRthID;
    TextView VlblRthID;
    EditText txtRthID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secRthEvDate;
    View lineRthEvDate;
    TextView VlblRthEvDate;
    EditText dtpRthEvDate;
    LinearLayout secNewRth;
    LinearLayout secNewRthA;
    View lineNewRth;
    View lineNewRthA;
    TextView VlblNewRth;
    TextView VlblNewRthA;
    Spinner spnNewRth;
    RadioGroup rdogrpNewRthA;
    RadioButton rdoNewRthA1;
    RadioButton rdoNewRthA2;
    LinearLayout secOldRth;
    View lineOldRth;
    TextView VlblOldRth;
    //    EditText txtOldRth;
    Spinner spnOldRth;
    LinearLayout secRthNote;
    View lineRthNote;
    TextView VlblRthNote;
    EditText txtRthNote;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secRthVDate;
    View lineRthVDate;
    TextView VlblRthVDate;
    EditText dtpRthVDate;

    LinearLayout secRthNote1;
    View lineRthNote1;
    TextView lblEvCode;

     int MODULEID = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String RTHID = "";
     String MEM_ID = "";
     String HHID = "";
     String EV_TYPE = "";
     String ROUND = "";
     String VISIT_DATE = "";
     String AGE_DAYS = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.surv_relation);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            RTHID = IDbundle.getString("RthID");
            MEM_ID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            EV_TYPE = IDbundle.getString("evtype");
            ROUND = IDbundle.getString("round");
            AGE_DAYS = IDbundle.getString("age");
            VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy

            TableName = "tmpRelation";
            MODULEID = 35;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Relation.this);
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
            Connection.LocalizeLanguage(Surv_Relation.this, MODULEID, LANGUAGEID);


            spnOldRth.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_RTH())));

            /*String PRth = C.ReturnSingleValue("Select Rth from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");

            if (!PRth.equals("0")) {
                spnOldRth.setAdapter(C.getArrayAdapter("Select Rth||'-'||r.Name from tmpMember m left join RTH r on m.Rth=r.Code where HHID='" + HHID + "' and MemID='" + MEM_ID + "'"));
            }
            if (PRth.equals("0") || PRth.equals("")) {
                spnOldRth.setAdapter(C.getArrayAdapter("Select '0-No Relation'"));
            }*/


            String PRth = C.ReturnSingleValue("Select Rth from tmpMemberMove where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            if(PRth == null || PRth.length()==0) spnOldRth.setSelection(1);
            else spnOldRth.setSelection(Global.SpinnerItemPositionAnyLength(spnOldRth, PRth));


            spnOldRth.setEnabled(false);

            dtpRthEvDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnRthEvDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });
            dtpRthVDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnRthVDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            //Hide all skip variables


            DataSearch(RTHID);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(Surv_Relation.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            lblEvCode = findViewById(R.id.lblEvCode);
            lblEvCode.setText(EV_TYPE);

            secRthID = (LinearLayout) findViewById(R.id.secRthID);
            lineRthID = (View) findViewById(R.id.lineRthID);
            VlblRthID = (TextView) findViewById(R.id.VlblRthID);
            txtRthID = (EditText) findViewById(R.id.txtRthID);

            if (RTHID.length() == 0) txtRthID.setText(Global.Get_UUID(DEVICEID));
            else txtRthID.setText(RTHID);
            txtRthID.setEnabled(false);

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
            secRthEvDate = (LinearLayout) findViewById(R.id.secRthEvDate);
            lineRthEvDate = (View) findViewById(R.id.lineRthEvDate);
            VlblRthEvDate = (TextView) findViewById(R.id.VlblRthEvDate);
            dtpRthEvDate = (EditText) findViewById(R.id.dtpRthEvDate);
            secNewRth = (LinearLayout) findViewById(R.id.secNewRth);
            secNewRthA = (LinearLayout) findViewById(R.id.secNewRthA);
            lineNewRth = (View) findViewById(R.id.lineNewRth);
            lineNewRthA = (View) findViewById(R.id.lineNewRthA);
            VlblNewRth = (TextView) findViewById(R.id.VlblNewRth);
            VlblNewRthA = (TextView) findViewById(R.id.VlblNewRthA);
            spnNewRth = (Spinner) findViewById(R.id.spnNewRth);
            rdogrpNewRthA = (RadioGroup) findViewById(R.id.rdogrpNewRthA);
            rdoNewRthA1 = (RadioButton) findViewById(R.id.rdoNewRthA1);
            rdoNewRthA2 = (RadioButton) findViewById(R.id.rdoNewRthA2);
            if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                secNewRthA.setVisibility(View.VISIBLE);
                lineNewRthA.setVisibility(View.VISIBLE);
            } else {
                secNewRthA.setVisibility(View.GONE);
                lineNewRthA.setVisibility(View.GONE);
                rdogrpNewRthA.clearCheck();

            }
            rdogrpNewRthA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpMigReason = new String[] {"1","2"};
                    for (int i = 0; i < rdogrpNewRthA.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpNewRthA.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpMigReason[i];
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

            //spnNewRth.setAdapter(C.getArrayAdapter( "Select '' union Select Code||'-'||Name from RTH "));
            spnNewRth.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_RTH())));
            secOldRth = (LinearLayout) findViewById(R.id.secOldRth);
            lineOldRth = (View) findViewById(R.id.lineOldRth);
            VlblOldRth = (TextView) findViewById(R.id.VlblOldRth);
            spnOldRth = (Spinner) findViewById(R.id.spnOldRth);

            /*String PRth = C.ReturnSingleValue("Select Rth from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            if (!PRth.equals("0")) {
                spnOldRth.setAdapter(C.getArrayAdapter("Select Rth||'-'||r.Name from tmpMember m left join RTH r on m.Rth=r.Code where HHID='" + HHID + "' and MemID='" + MEM_ID + "'"));
            }
            if (PRth.equals("0") || PRth.equals("")) {
                spnOldRth.setAdapter(C.getArrayAdapter("Select '0-No Relation'"));
            }*/

            secRthNote = (LinearLayout) findViewById(R.id.secRthNote);
            lineRthNote = (View) findViewById(R.id.lineRthNote);
            VlblRthNote = (TextView) findViewById(R.id.VlblRthNote);
            txtRthNote = (EditText) findViewById(R.id.txtRthNote);

            secRthNote1 = (LinearLayout) findViewById(R.id.secRthNote1);
            lineRthNote1 = (View) findViewById(R.id.lineRthNote1);
            secRnd = (LinearLayout) findViewById(R.id.secRnd);
            lineRnd = (View) findViewById(R.id.lineRnd);
            VlblRnd = (TextView) findViewById(R.id.VlblRnd);
            txtRnd = (EditText) findViewById(R.id.txtRnd);
            txtRnd.setText(ROUND);
            txtRnd.setEnabled(false);
            secRthVDate = (LinearLayout) findViewById(R.id.secRthVDate);
            lineRthVDate = (View) findViewById(R.id.lineRthVDate);
            VlblRthVDate = (TextView) findViewById(R.id.VlblRthVDate);
            dtpRthVDate = (EditText) findViewById(R.id.dtpRthVDate);
            dtpRthVDate.setText(VISIT_DATE);
            secRthVDate.setVisibility(View.GONE);

        } catch (Exception e) {
            Connection.MessageBox(Surv_Relation.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(Surv_Relation.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            tmpRelation_DataModel objSave = new tmpRelation_DataModel();
            objSave.setRthID(txtRthID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setRthEvDate(dtpRthEvDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpRthEvDate.getText().toString()) : dtpRthEvDate.getText().toString());
            objSave.setNewRth(spnNewRth.getSelectedItemPosition() == 0 ? "" : spnNewRth.getSelectedItem().toString().split("-")[0]);
            //objSave.setOldRth(spnOldRth.getSelectedItemPosition() == 0 ? "" : spnOldRth.getSelectedItem().toString().split("-")[0]);
            objSave.setOldRth(spnOldRth.getSelectedItem().toString().split("-")[0]);
            objSave.setRthNote(txtRthNote.getText().toString());
            objSave.setRthVDate(dtpRthVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpRthVDate.getText().toString()) : dtpRthVDate.getText().toString());
            objSave.setRnd(txtRnd.getText().toString());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            //Newly added,
            // also need to add validation and add a new field on model call and database,
            // also add on edit mode

            String[] d_rdogrpHeadShipRth = new String[] {"1","2"};
            objSave.setHeadShipRth("");
            for (int i = 0; i < rdogrpNewRthA.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpNewRthA.getChildAt(i);
                if (rb.isChecked()) objSave.setHeadShipRth(d_rdogrpHeadShipRth[i]);
            }

            String status = objSave.SaveUpdateData(this);
            if (status.length() == 0) {

                C.SaveData("Update tmpMember set Upload='2',Rth='" + (spnNewRth.getSelectedItemPosition() == 0 ? "0" : spnNewRth.getSelectedItem().toString().split("-")[0]) + "',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where MemID='" + MEM_ID + "' ");
                C.SaveData("Update tmpMemberMove set Upload='2',Rth='" + (spnNewRth.getSelectedItemPosition() == 0 ? "0" : spnNewRth.getSelectedItem().toString().split("-")[0]) + "',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where MemID='" + MEM_ID + "' AND HHID = '"+ HHID +"'");

                Intent returnIntent = new Intent();
                returnIntent.putExtra("res", "");
                setResult(Activity.RESULT_OK, returnIntent);

                Toast.makeText(getApplicationContext(), "Save Successfully...", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(Surv_Relation.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_Relation.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        try {
            ResetSectionColor();
            if (txtRthID.getText().toString().length() == 0 & secRthID.isShown()) {
                ValidationMsg += "\nRequired field: spouse serial number Internal ID.";
                secRthID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtHHID.getText().toString().length() == 0 & secHHID.isShown()) {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtMemID.getText().toString().length() == 0 & secMemID.isShown()) {
                ValidationMsg += "\nRequired field: Member internal ID.";
                secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpRthEvDate.getText().toString());
            if (DV.length() != 0 & secRthEvDate.isShown()) {
                ValidationMsg += "\n1. Required field/Not a valid date format: When has this member’s relation to household head changed?.";
                secRthEvDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnNewRth.getSelectedItemPosition() == 0 & secNewRth.isShown()) {
                ValidationMsg += "\n2. Required field: What is the new relation code?.";
                secNewRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
//         if(spnOldRth.getSelectedItemPosition()==0  & secOldRth.isShown())
//         {
//             ValidationMsg += "\n3. Required field: What was the previous relation code?.";
//             secOldRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//         }
            if (txtRnd.getText().toString().length() == 0 & secRnd.isShown()) {
                ValidationMsg += "\nRequired field: Round.";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secRnd.isShown() & (Integer.valueOf(txtRnd.getText().toString().length() == 0 ? "1" : txtRnd.getText().toString()) < 1 || Integer.valueOf(txtRnd.getText().toString().length() == 0 ? "99" : txtRnd.getText().toString()) > 99)) {
                ValidationMsg += "\nValue should be between 1 and 99(Round).";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpRthVDate.getText().toString());
            if (DV.length() != 0 & secRthVDate.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: Visit Date.";
                secRthVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
/*         if(txtRthNote.getText().toString().length()==0 & secRthNote.isShown())
           {
             ValidationMsg += "\nRequired field: Note.";
             secRthNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/
            Integer NewRth = Integer.parseInt(spnNewRth.getSelectedItem().toString().split("-")[0]);
            Integer PrvRth = Integer.parseInt(spnOldRth.getSelectedItem().toString().split("-")[0]);
            String sex = C.ReturnSingleValue("SELECT Sex FROM tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");

            if (NewRth.equals(PrvRth)) {
                ValidationMsg += "\n Current relation code and Previous relation code should not same";
                secNewRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                secOldRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }

            //Newly added
            if (spnNewRth.getSelectedItemPosition() != 0) {
                String itemCode = spnNewRth.getSelectedItemPosition() == 0 ? "" : spnNewRth.getSelectedItem().toString().split("-")[0];
                String itemValue = spnNewRth.getSelectedItemPosition() == 0 ? "" : spnNewRth.getSelectedItem().toString().split("-")[1];
                Set<String> checkSetF = new HashSet<>(Arrays.asList("02", "03", "04", "05", "07", "09", "11", "13", "15", "17", "19", "21", "23", "26", "28"));
                Set<String> checkSetM = new HashSet<>(Arrays.asList("06", "08", "10", "12", "14", "16", "18", "20", "22", "27"));

                if (checkSetF.contains(itemCode) && sex.equals("1")) {
                    ValidationMsg += "\nHR4 Relationship " + itemValue + " can not be Male.";
                    secNewRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                } else if (checkSetM.contains(itemCode) && sex.equals("2")) {
                    ValidationMsg += "\nHR4 Relationship " + itemValue + " can not be Female.";
                    secNewRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }
            }
        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor() {
        try {
            secRthID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secRthEvDate.setBackgroundColor(Color.WHITE);
            secNewRth.setBackgroundColor(Color.WHITE);
            secOldRth.setBackgroundColor(Color.WHITE);
            secOldRth.setBackgroundColor(Color.WHITE);
            secRthNote.setBackgroundColor(Color.WHITE);
            secRnd.setBackgroundColor(Color.WHITE);
            secRthVDate.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String RthID) {
        try {
            RadioButton rb;
            tmpRelation_DataModel d = new tmpRelation_DataModel();
            String SQL = "Select * from " + TableName + "  Where RthID='" + RthID + "'";
            List<tmpRelation_DataModel> data = d.SelectAll(this, SQL);
            for (tmpRelation_DataModel item : data) {
                txtRthID.setText(item.getRthID());
                txtHHID.setText(item.getHHID());
                txtMemID.setText(item.getMemID());
                dtpRthEvDate.setText(item.getRthEvDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getRthEvDate()));
                String[] d_rdogrpHeadShipRth = new String[] {"1","2"};
                for (int i = 0; i < d_rdogrpHeadShipRth.length; i++) {
                    if (String.valueOf(item.getHeadShipRth()).equals(String.valueOf(d_rdogrpHeadShipRth[i]))) {
                        rb = (RadioButton) rdogrpNewRthA.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnNewRth.setSelection(Global.SpinnerItemPositionAnyLength(spnNewRth, String.valueOf(item.getNewRth())));
                //spnOldRth.setSelection(Global.SpinnerItemPositionAnyLength(spnOldRth, String.valueOf(item.getOldRth())));
                spnOldRth.setAdapter(C.getArrayAdapter("SELECT Code||'-'||Name FROM RTH WHERE Code = '"+ item.getOldRth() +"'"));
                txtRthNote.setText(item.getRthNote());
                txtRnd.setText(String.valueOf(item.getRnd()));
                dtpRthVDate.setText(item.getRthVDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getRthVDate()));
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_Relation.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpRthEvDate);
            if (VariableID.equals("btnRthEvDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpRthEvDate);
            } else if (VariableID.equals("btnRthVDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpRthVDate);
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