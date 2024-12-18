
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
import forms_datamodel.tmpDelivery_DataModel;

public class Surv_Delivery extends AppCompatActivity {
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
    LinearLayout secPregOccurID;
    View linePregOccurID;
    TextView VlblPregOccurID;
    EditText txtPregOccurID;
    LinearLayout secDeliveryID;
    View lineDeliveryID;
    TextView VlblDeliveryID;
    EditText txtDeliveryID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secDelDate;
    View lineDelDate;
    TextView VlblDelDate;
    EditText dtpDelDate;
    LinearLayout secDelDateType;
    View lineDelDateType;
    TextView VlblDelDateType;
    RadioGroup rdogrpDelDateType;
    RadioButton rdoDelDateType1;
    RadioButton rdoDelDateType2;
    RadioButton rdoDelDateType3;
    LinearLayout secDelTime;
    View lineDelTime;
    TextView VlblDelTime;
    EditText txtDelTime;
    LinearLayout secDelTimeType;
    View lineDelTimeType;
    TextView VlblDelTimeType;
    RadioGroup rdogrpDelTimeType;
    RadioButton rdoDelTimeType1;
    RadioButton rdoDelTimeType2;
    RadioButton rdoDelTimeType3;
    LinearLayout secTotOut;
    View lineTotOut;
    TextView VlblTotOut;
    EditText txtTotOut;
    LinearLayout secTotLB;
    View lineTotLB;
    TextView VlblTotLB;
    EditText txtTotLB;
    LinearLayout secTotMis;
    View lineTotMis;
    TextView VlblTotMis;
    EditText txtTotMis;
    LinearLayout secTotSB;
    View lineTotSB;
    TextView VlblTotSB;
    EditText txtTotSB;
    LinearLayout secTotAB;
    View lineTotAB;
    TextView VlblTotAB;
    EditText txtTotAB;
    LinearLayout secDelVDate;
    View lineDelVDate;
    TextView VlblDelVDate;
    EditText dtpDelVDate;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secDelNote;
    View lineDelNote;
    TextView VlblDelNote;
    EditText txtDelNote;

    LinearLayout secDelNote1;
    View lineDelNote1;

     int MODULEID = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String PREGOCCURID = "";
     String DELIVERYID = "";
     String MEM_ID = "";
     String HHID = "";
     String HHNO = "";
     String EV_TYPE = "";
     String ROUND = "";
     String EDIT = "";
     String VISIT_DATE = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.surv_delivery);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();
            sp = new MySharedPreferences();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            PREGOCCURID = IDbundle.getString("pregid");
            DELIVERYID = IDbundle.getString("DeliveryID");
            MEM_ID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            HHID = IDbundle.getString("HHID");
            HHNO = IDbundle.getString("HHNO");
            EV_TYPE = IDbundle.getString("evtype");
            ROUND = IDbundle.getString("round");
            EDIT = IDbundle.getString("edit");
            VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy

            TableName = "tmpDelivery";
            MODULEID = 27;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Delivery.this);
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
            Connection.LocalizeLanguage(Surv_Delivery.this, MODULEID, LANGUAGEID);


            dtpDelDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnDelDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });
            dtpDelVDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnDelVDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            txtDelTime.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnDelTime";
                        showDialog(TIME_DIALOG);
                        return true;
                    }
                    return false;
                }
            });

            //Hide all skip variables


            DataSearch(DELIVERYID);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(Surv_Delivery.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            secPregOccurID=(LinearLayout)findViewById(R.id.secPregOccurID);
            linePregOccurID=(View)findViewById(R.id.linePregOccurID);
            VlblPregOccurID=(TextView) findViewById(R.id.VlblPregOccurID);
            txtPregOccurID=(EditText) findViewById(R.id.txtPregOccurID);
             txtPregOccurID.setText(PREGOCCURID);
             txtPregOccurID.setEnabled(false);
            secDeliveryID = (LinearLayout) findViewById(R.id.secDeliveryID);
            lineDeliveryID = (View) findViewById(R.id.lineDeliveryID);
            VlblDeliveryID = (TextView) findViewById(R.id.VlblDeliveryID);
            txtDeliveryID = (EditText) findViewById(R.id.txtDeliveryID);

            if (DELIVERYID.length() == 0) txtDeliveryID.setText(Global.Get_UUID(DEVICEID));
            else txtDeliveryID.setText(DELIVERYID);
            txtDeliveryID.setEnabled(false);

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
            secDelDate = (LinearLayout) findViewById(R.id.secDelDate);
            lineDelDate = (View) findViewById(R.id.lineDelDate);
            VlblDelDate = (TextView) findViewById(R.id.VlblDelDate);
            dtpDelDate = (EditText) findViewById(R.id.dtpDelDate);
            secDelDateType = (LinearLayout) findViewById(R.id.secDelDateType);
            lineDelDateType = (View) findViewById(R.id.lineDelDateType);
            VlblDelDateType = (TextView) findViewById(R.id.VlblDelDateType);
            rdogrpDelDateType = (RadioGroup) findViewById(R.id.rdogrpDelDateType);
            rdoDelDateType1 = (RadioButton) findViewById(R.id.rdoDelDateType1);
            rdoDelDateType2 = (RadioButton) findViewById(R.id.rdoDelDateType2);
            rdoDelDateType3 = (RadioButton) findViewById(R.id.rdoDelDateType3);
            secDelTime = (LinearLayout) findViewById(R.id.secDelTime);
            lineDelTime = (View) findViewById(R.id.lineDelTime);
            VlblDelTime = (TextView) findViewById(R.id.VlblDelTime);
            txtDelTime = (EditText) findViewById(R.id.txtDelTime);
            secDelTimeType = (LinearLayout) findViewById(R.id.secDelTimeType);
            lineDelTimeType = (View) findViewById(R.id.lineDelTimeType);
            VlblDelTimeType = (TextView) findViewById(R.id.VlblDelTimeType);
            rdogrpDelTimeType = (RadioGroup) findViewById(R.id.rdogrpDelTimeType);
            rdoDelTimeType1 = (RadioButton) findViewById(R.id.rdoDelTimeType1);
            rdoDelTimeType2 = (RadioButton) findViewById(R.id.rdoDelTimeType2);
            rdoDelTimeType3 = (RadioButton) findViewById(R.id.rdoDelTimeType3);
            secTotOut = (LinearLayout) findViewById(R.id.secTotOut);
            lineTotOut = (View) findViewById(R.id.lineTotOut);
            VlblTotOut = (TextView) findViewById(R.id.VlblTotOut);
            txtTotOut = (EditText) findViewById(R.id.txtTotOut);
            secTotLB = (LinearLayout) findViewById(R.id.secTotLB);
            lineTotLB = (View) findViewById(R.id.lineTotLB);
            VlblTotLB = (TextView) findViewById(R.id.VlblTotLB);
            txtTotLB = (EditText) findViewById(R.id.txtTotLB);
            txtTotLB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (txtTotLB.getText().toString().length() > 0 && !txtTotLB.getText().toString().trim().equals("0")){
                        txtTotAB.setText("0");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


            secTotMis = (LinearLayout) findViewById(R.id.secTotMis);
            lineTotMis = (View) findViewById(R.id.lineTotMis);
            VlblTotMis = (TextView) findViewById(R.id.VlblTotMis);
            txtTotMis = (EditText) findViewById(R.id.txtTotMis);
            txtTotMis.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (txtTotMis.getText().toString().length() > 0 && !txtTotMis.getText().toString().trim().equals("0")){
                        txtTotAB.setText("0");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            secTotSB = (LinearLayout) findViewById(R.id.secTotSB);
            lineTotSB = (View) findViewById(R.id.lineTotSB);
            VlblTotSB = (TextView) findViewById(R.id.VlblTotSB);
            txtTotSB = (EditText) findViewById(R.id.txtTotSB);
            txtTotSB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (txtTotSB.getText().toString().length() > 0 && !txtTotSB.getText().toString().trim().equals("0")){
                        txtTotAB.setText("0");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            secTotAB = (LinearLayout) findViewById(R.id.secTotAB);
            lineTotAB = (View) findViewById(R.id.lineTotAB);
            VlblTotAB = (TextView) findViewById(R.id.VlblTotAB);
            txtTotAB = (EditText) findViewById(R.id.txtTotAB);
            txtTotAB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (txtTotAB.getText().toString().length() > 0 && txtTotAB.getText().toString().trim().equals("1")){
                        txtTotOut.setText("1");
                        txtTotLB.setText("0");
                        txtTotSB.setText("0");
                        txtTotMis.setText("0");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            txtTotLB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (txtTotLB.getText().toString().length() > 0 ){
                        if(Integer.parseInt(txtTotLB.getText().toString().trim()) > 0){
                            txtTotAB.setText("0");
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            txtTotSB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (txtTotSB.getText().toString().length() > 0 ){
                        if(Integer.parseInt(txtTotSB.getText().toString().trim()) > 0){
                            txtTotAB.setText("0");
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            txtTotMis.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (txtTotMis.getText().toString().length() > 0 ){
                        if(Integer.parseInt(txtTotMis.getText().toString().trim()) > 0){
                            txtTotAB.setText("0");
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            secDelVDate = (LinearLayout) findViewById(R.id.secDelVDate);
            lineDelVDate = (View) findViewById(R.id.lineDelVDate);
            VlblDelVDate = (TextView) findViewById(R.id.VlblDelVDate);
            dtpDelVDate = (EditText) findViewById(R.id.dtpDelVDate);
            dtpDelVDate.setText(VISIT_DATE);
            secDelVDate.setVisibility(View.GONE);

            secRnd = (LinearLayout) findViewById(R.id.secRnd);
            lineRnd = (View) findViewById(R.id.lineRnd);
            VlblRnd = (TextView) findViewById(R.id.VlblRnd);
            txtRnd = (EditText) findViewById(R.id.txtRnd);
            txtRnd.setText(ROUND);
            txtRnd.setEnabled(false);
            secDelNote = (LinearLayout) findViewById(R.id.secDelNote);
            lineDelNote = (View) findViewById(R.id.lineDelNote);
            VlblDelNote = (TextView) findViewById(R.id.VlblDelNote);
            txtDelNote = (EditText) findViewById(R.id.txtDelNote);

            secDelNote1 = (LinearLayout) findViewById(R.id.secDelNote1);
            lineDelNote1 = (View) findViewById(R.id.lineDelNote1);
        } catch (Exception e) {
            Connection.MessageBox(Surv_Delivery.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        sp.save(this, "ab1", "");
        sp.save(Surv_Delivery.this,"child1","");
        sp.save(Surv_Delivery.this,"child2","");
        sp.save(Surv_Delivery.this,"child3","");
        sp.save(Surv_Delivery.this,"child4","");
        sp.save(Surv_Delivery.this,"child5","");
        sp.save(Surv_Delivery.this,"child6","");
        sp.save(Surv_Delivery.this,"child7","");

        //tmpStillBirth
        sp.save(Surv_Delivery.this,"stl1","");
        sp.save(Surv_Delivery.this,"stl2","");
        sp.save(Surv_Delivery.this,"stl3","");
        sp.save(Surv_Delivery.this,"stl4","");
        sp.save(Surv_Delivery.this,"stl5","");
        sp.save(Surv_Delivery.this,"stl6","");
        sp.save(Surv_Delivery.this,"stl7","");

        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(Surv_Delivery.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            tmpDelivery_DataModel objSave = new tmpDelivery_DataModel();
            objSave.setPregOccurID(txtPregOccurID.getText().toString());
            objSave.setDeliveryID(txtDeliveryID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setDelDate(dtpDelDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpDelDate.getText().toString()) : dtpDelDate.getText().toString());
            String[] d_rdogrpDelDateType = new String[]{"1", "2", "8"};
            objSave.setDelDateType("");
            for (int i = 0; i < rdogrpDelDateType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpDelDateType.getChildAt(i);
                if (rb.isChecked()) objSave.setDelDateType(d_rdogrpDelDateType[i]);
            }

            objSave.setDelTime(txtDelTime.getText().toString());
            String[] d_rdogrpDelTimeType = new String[]{"1", "2", "8"};
            objSave.setDelTimeType("");
            for (int i = 0; i < rdogrpDelTimeType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpDelTimeType.getChildAt(i);
                if (rb.isChecked()) objSave.setDelTimeType(d_rdogrpDelTimeType[i]);
            }

            objSave.setTotOut(txtTotOut.getText().toString());
            objSave.setTotLB(txtTotLB.getText().toString());
            objSave.setTotMis(txtTotMis.getText().toString());
            objSave.setTotSB(txtTotSB.getText().toString());
            objSave.setTotAB(txtTotAB.getText().toString());
            objSave.setDelVDate(dtpDelVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpDelVDate.getText().toString()) : dtpDelVDate.getText().toString());
            objSave.setRnd(txtRnd.getText().toString());
            objSave.setDelNote(txtDelNote.getText().toString());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);
            if (status.length() == 0) {
                C.SaveData("Update tmpMember set Upload='2',modifydate='"+ Global.DateTimeNowYMDHMS() +"',Pstat='40',LmpDt='' where MemID='" + MEM_ID + "'");

                Intent returnIntent = new Intent();
                returnIntent.putExtra("res", "");
                setResult(Activity.RESULT_OK, returnIntent);

                Toast.makeText(Surv_Delivery.this, "Save Successfully...", Toast.LENGTH_SHORT).show();

                Bundle IDbundle = new Bundle();
                IDbundle.putString("DeliveryID", txtDeliveryID.getText().toString());
                IDbundle.putString("MemID", txtMemID.getText().toString());
                IDbundle.putString("HHID", txtHHID.getText().toString());
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("dod", dtpDelDate.getText().toString());
                IDbundle.putString("tod", txtDelTime.getText().toString());
                IDbundle.putString("lb", txtTotLB.getText().toString());
                IDbundle.putString("sb", txtTotSB.getText().toString());
                IDbundle.putString("misc", txtTotMis.getText().toString());
                IDbundle.putString("ab", txtTotAB.getText().toString());
                IDbundle.putString("total", txtTotOut.getText().toString());
                IDbundle.putString("round", ROUND);
                IDbundle.putString("edit", EDIT);
                Intent intent = new Intent(getApplicationContext(), Surv_delivery_module.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                finish();
            } else {
                Connection.MessageBox(Surv_Delivery.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_Delivery.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        try {
            ResetSectionColor();
            if(txtPregOccurID.getText().toString().length()==0 & secPregOccurID.isShown())
            {
                ValidationMsg += "\nRequired field: Pregnancy Occurrence Internal ID.";
                secPregOccurID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if (txtDeliveryID.getText().toString().length() == 0 & secDeliveryID.isShown()) {
                ValidationMsg += "\nRequired field: Delivery Internal ID.";
                secDeliveryID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtHHID.getText().toString().length() == 0 & secHHID.isShown()) {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtMemID.getText().toString().length() == 0 & secMemID.isShown()) {
                ValidationMsg += "\nRequired field: Member internal ID.";
                secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpDelDate.getText().toString());
            if (DV.length() != 0 & secDelDate.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: What was the date of pregnancy outcome?  (Day/Month/Year).";
                secDelDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoDelDateType1.isChecked() & !rdoDelDateType2.isChecked() & !rdoDelDateType3.isChecked() & secDelDateType.isShown()) {
                ValidationMsg += "\nRequired field: Pregnancy Outcome date Type.";
                secDelDateType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtDelTime.getText().length() == 0 & secDelTime.isShown()) {
                ValidationMsg += "\nRequired field: What is the time of pregnancy outcome?.";
                secDelTime.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoDelTimeType1.isChecked() & !rdoDelTimeType2.isChecked() & !rdoDelTimeType3.isChecked() & secDelTimeType.isShown()) {
                ValidationMsg += "\nRequired field: Pregnancy outcome time type..";
                secDelTimeType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtTotOut.getText().toString().length() == 0 & secTotOut.isShown()) {
                ValidationMsg += "\nRequired field: What is/are the number of pregnancy outcome?.";
                secTotOut.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secTotOut.isShown() & (Integer.valueOf(txtTotOut.getText().toString().length() == 0 ? "00" : txtTotOut.getText().toString()) < 00 || Integer.valueOf(txtTotOut.getText().toString().length() == 0 ? "50" : txtTotOut.getText().toString()) > 50)) {
                ValidationMsg += "\nValue should be between 00 and 50(What is/are the number of pregnancy outcome?).";
                secTotOut.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtTotLB.getText().toString().length() == 0 & secTotLB.isShown()) {
                ValidationMsg += "\nRequired field: What is/are the number of live births?.";
                secTotLB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secTotLB.isShown() & (Integer.valueOf(txtTotLB.getText().toString().length() == 0 ? "00" : txtTotLB.getText().toString()) < 00 || Integer.valueOf(txtTotLB.getText().toString().length() == 0 ? "30" : txtTotLB.getText().toString()) > 30)) {
                ValidationMsg += "\nValue should be between 00 and 30(What is/are the number of live births?).";
                secTotLB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtTotMis.getText().toString().length() == 0 & secTotMis.isShown()) {
                ValidationMsg += "\nRequired field: What is/are the number of miscarriages?.";
                secTotMis.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secTotMis.isShown() & (Integer.valueOf(txtTotMis.getText().toString().length() == 0 ? "00" : txtTotMis.getText().toString()) < 00 || Integer.valueOf(txtTotMis.getText().toString().length() == 0 ? "30" : txtTotMis.getText().toString()) > 30)) {
                ValidationMsg += "\nValue should be between 00 and 30(What is/are the number of miscarriages?).";
                secTotMis.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtTotSB.getText().toString().length() == 0 & secTotSB.isShown()) {
                ValidationMsg += "\nRequired field: What is/are the number of stillbirths?.";
                secTotSB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secTotSB.isShown() & (Integer.valueOf(txtTotSB.getText().toString().length() == 0 ? "00" : txtTotSB.getText().toString()) < 00 || Integer.valueOf(txtTotSB.getText().toString().length() == 0 ? "30" : txtTotSB.getText().toString()) > 30)) {
                ValidationMsg += "\nValue should be between 00 and 30(What is/are the number of stillbirths?).";
                secTotSB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtTotAB.getText().toString().length() == 0 & secTotAB.isShown()) {
                ValidationMsg += "\nRequired field: What is/are the number of abortions?.";
                secTotAB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (Integer.valueOf(txtTotAB.getText().toString().trim()) > 1 & secTotAB.isShown()) {
                ValidationMsg += "\nRequired field: the number is not bigger than 1.";
                secTotAB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secTotAB.isShown() & (Integer.valueOf(txtTotAB.getText().toString().length() == 0 ? "00" : txtTotAB.getText().toString()) < 00 || Integer.valueOf(txtTotAB.getText().toString().length() == 0 ? "30" : txtTotAB.getText().toString()) > 30)) {
                ValidationMsg += "\nValue should be between 00 and 30(What is/are the number of abortions?).";
                secTotAB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpDelVDate.getText().toString());
            if (DV.length() != 0 & secDelVDate.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: Visit date.";
                secDelVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtRnd.getText().toString().length() == 0 & secRnd.isShown()) {
                ValidationMsg += "\nRequired field: Round number.";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtTotOut.getText().toString().length() != 0 & txtTotLB.getText().toString().length() != 0 & txtTotAB.getText().toString().length() != 0 & txtTotSB.getText().toString().length() != 0 & txtTotMis.getText().toString().length() != 0) {
                int TotLB = Integer.parseInt(txtTotLB.getText().toString());
                int TotSB = Integer.parseInt(txtTotSB.getText().toString());
                int TotAB = Integer.parseInt(txtTotAB.getText().toString());
                int TotMis = Integer.parseInt(txtTotMis.getText().toString());
                int Total = TotLB + TotSB + TotAB + TotMis;
                if (Integer.parseInt(txtTotOut.getText().toString()) != Total) {
                    ValidationMsg += "\nTotal Outcome and sum of other outcomes do not match";
                    secTotOut.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }
            }
//         if(txtDelNote.getText().toString().length()==0 & secDelNote.isShown())
//           {
//             ValidationMsg += "\nRequired field: Note.";
//             secDelNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor() {
        try {
            secDeliveryID.setBackgroundColor(Color.WHITE);
            secPregOccurID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secDelDate.setBackgroundColor(Color.WHITE);
            secDelDateType.setBackgroundColor(Color.WHITE);
            secDelTime.setBackgroundColor(Color.WHITE);
            secDelTimeType.setBackgroundColor(Color.WHITE);
            secTotOut.setBackgroundColor(Color.WHITE);
            secTotOut.setBackgroundColor(Color.WHITE);
            secTotLB.setBackgroundColor(Color.WHITE);
            secTotLB.setBackgroundColor(Color.WHITE);
            secTotMis.setBackgroundColor(Color.WHITE);
            secTotMis.setBackgroundColor(Color.WHITE);
            secTotSB.setBackgroundColor(Color.WHITE);
            secTotSB.setBackgroundColor(Color.WHITE);
            secTotAB.setBackgroundColor(Color.WHITE);
            secTotAB.setBackgroundColor(Color.WHITE);
            secDelVDate.setBackgroundColor(Color.WHITE);
            secRnd.setBackgroundColor(Color.WHITE);
            secDelNote.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String DeliveryID) {
        try {
            RadioButton rb;
            tmpDelivery_DataModel d = new tmpDelivery_DataModel();
            String SQL = "Select * from " + TableName + "  Where DeliveryID='" + DeliveryID + "'";
            List<tmpDelivery_DataModel> data = d.SelectAll(this, SQL);
            for (tmpDelivery_DataModel item : data) {
                txtPregOccurID.setText(item.getPregOccurID());
                txtDeliveryID.setText(item.getDeliveryID());
                txtHHID.setText(item.getHHID());
                txtMemID.setText(item.getMemID());
                dtpDelDate.setText(item.getDelDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getDelDate()));
                String[] d_rdogrpDelDateType = new String[]{"1", "2", "8"};
                for (int i = 0; i < d_rdogrpDelDateType.length; i++) {
                    if (String.valueOf(item.getDelDateType()).equals(String.valueOf(d_rdogrpDelDateType[i]))) {
                        rb = (RadioButton) rdogrpDelDateType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtDelTime.setText(item.getDelTime());
                String[] d_rdogrpDelTimeType = new String[]{"1", "2", "8"};
                for (int i = 0; i < d_rdogrpDelTimeType.length; i++) {
                    if (String.valueOf(item.getDelTimeType()).equals(String.valueOf(d_rdogrpDelTimeType[i]))) {
                        rb = (RadioButton) rdogrpDelTimeType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtTotOut.setText(String.valueOf(item.getTotOut()));
                txtTotLB.setText(String.valueOf(item.getTotLB()));
                txtTotMis.setText(String.valueOf(item.getTotMis()));
                txtTotSB.setText(String.valueOf(item.getTotSB()));
                txtTotAB.setText(String.valueOf(item.getTotAB()));
                dtpDelVDate.setText(item.getDelVDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getDelVDate()));
                txtRnd.setText(item.getRnd());
                txtDelNote.setText(item.getDelNote());
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_Delivery.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpDelDate);
            if (VariableID.equals("btnDelDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpDelDate);
            } else if (VariableID.equals("btnDelVDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpDelVDate);
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

            tpTime = (EditText) findViewById(R.id.txtDelTime);
            if (VariableID.equals("btnDelTime")) {
                tpTime = (EditText) findViewById(R.id.txtDelTime);
            }
            tpTime.setText(new StringBuilder().append(Global.Right("00" + hour, 2)).append(":").append(Global.Right("00" + minute, 2)));
        }
    };

// public void checkTotalOutcomeCompleteOrNot(String outtype){
//
//     int TotLB = Integer.parseInt(txtTotLB.getText().toString().length() > 0 ? txtTotLB.getText().toString() : "0");
//     int TotSB = Integer.parseInt(txtTotSB.getText().toString().length() > 0 ? txtTotSB.getText().toString() : "0");
//     int TotAB = Integer.parseInt(txtTotAB.getText().toString().length() > 0 ? txtTotAB.getText().toString() : "0");
//     int TotMis = Integer.parseInt(txtTotMis.getText().toString().length() > 0 ? txtTotMis.getText().toString() : "0");
//     if((TotLB + TotAB + TotSB + TotMis) == Integer.parseInt(txtTotOut.getText().toString())){
//         txtTotAB.setText("0");
//         txtTotMis.setText("0");
//         txtTotSB.setText("0");
//     }
// }


    // turning off the GPS if its in on state. to avoid the battery drain.
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}