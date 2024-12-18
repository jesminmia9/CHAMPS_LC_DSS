
package forms_activity;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import android.app.*;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.view.KeyEvent;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
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
import android.widget.ArrayAdapter;
import android.graphics.Color;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.icddrb.champs_lc_dss.R;
import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

import forms_datamodel.Visits_DataModel;
import Utility.*;
import Common.*;

public class Baseline_Visits extends AppCompatActivity {
    //Disabled Back/Home key
    //--------------------------------------------------------------------------------------------------
    @Override
    public boolean onKeyDown(int iKeyCode, KeyEvent event)
    {
        if(iKeyCode == KeyEvent.KEYCODE_BACK || iKeyCode == KeyEvent.KEYCODE_HOME)
        { return false; }
        else { return true;  }
    }

    boolean networkAvailable=false;
    Location currentLocation;
    double currentLatitude,currentLongitude;
    String VariableID;
    private int hour;
    private int minute;
    private int mDay;
    private int mMonth;
    private int mYear;
    static final int DATE_DIALOG = 1;
    static final int TIME_DIALOG = 2;

    Connection C;
    Global g;
    SimpleAdapter dataAdapter;
    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    TextView lblHeading;
    LinearLayout seclbl1;
    View linelbl1;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secVisitNo;
    View lineVisitNo;
    TextView VlblVisitNo;
    EditText txtVisitNo;
    LinearLayout secVisitDate;
    View lineVisitDate;
    TextView VlblVisitDate;
    EditText dtpVisitDate;
    LinearLayout secVisitStatus;
    View lineVisitStatus;
    TextView VlblVisitStatus;
    Spinner spnVisitStatus;
    LinearLayout secVisitStatusOth;
    View lineVisitStatusOth;
    TextView VlblVisitStatusOth;
    AutoCompleteTextView txtVisitStatusOth;
    LinearLayout secRespID;
    View lineRespID;
    TextView VlblRespID;
    EditText txtRespID;
    LinearLayout secHaveDeath;
    View lineHaveDeath;
    TextView VlblHaveDeath;
    RadioGroup rdogrpHaveDeath;
    RadioButton rdoHaveDeath1;
    RadioButton rdoHaveDeath2;
    RadioButton rdoHaveDeath3;
    RadioButton rdoHaveDeath4;
    LinearLayout secTotalDeath;
    View lineTotalDeath;
    TextView VlblTotalDeath;
    EditText txtTotalDeath;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secVisitNote;
    View lineVisitNote;
    TextView VlblVisitNote;
    EditText txtVisitNote;

    static int MODULEID   = 0;
    static int LANGUAGEID = 0;
    static String TableName;

    static String STARTTIME = "";
    static String DEVICEID  = "";
    static String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
    static String HHID = "";
    static String HHNO = "";
    static String VILLID = "";
    static String CompoundID = "";
    static String VISITNO = "";
    static String HHHEAD = "";

    static String COMPOUNDNAME = "";
    static String COMPOUNDADRS = "";
    static String ROUND = "0";
    static String SurvType = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            setContentView(R.layout.baseline_visits);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            VILLID = IDbundle.getString("VillID");
            CompoundID = IDbundle.getString("CompoundID");
            HHID = IDbundle.getString("HHID");
            HHNO = IDbundle.getString("HHNO");
            VISITNO = IDbundle.getString("VisitNo");
            COMPOUNDNAME = IDbundle.getString("CompoundName");
            COMPOUNDADRS = IDbundle.getString("CompoundAdrs");
            HHHEAD = IDbundle.getString("HHHead");
            SurvType = IDbundle.getString("SurvType");

            TableName = "Visits";
            MODULEID = 8;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView)findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Baseline_Visits.this);
                    adb.setTitle("Close");
                    adb.setIcon(R.drawable.favicon);
                    adb.setMessage("Do you want to close this form[Yes/No]?");
                    adb.setNegativeButton("No", null);
                    adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }});
                    adb.show();
                }});

            Initialization();
            Connection.LocalizeLanguage(Baseline_Visits.this, MODULEID, LANGUAGEID);


            dtpVisitDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnVisitDate"; showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });



            //Hide all skip variables
            secVisitStatusOth.setVisibility(View.GONE);
            lineVisitStatusOth.setVisibility(View.GONE);
            secTotalDeath.setVisibility(View.GONE);
            lineTotalDeath.setVisibility(View.GONE);
            secRnd.setVisibility(View.GONE);
            lineRnd.setVisibility(View.GONE);
            secRespID.setVisibility(View.GONE);

            DataSearch(HHID,txtVisitNo.getText().toString());


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }});
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Baseline_Visits.this, e.getMessage());
            return;
        }
    }

    private String VisitSerial(String HHID)
    {
        String SL = C.ReturnSingleValue("Select (ifnull(max(cast(VisitNo as int)),0)+1)SL from Visits where HHID='"+ HHID +"'");
        return Global.Right("00"+SL,2);
    }

    private void Initialization()
    {
        try
        {
            seclbl1=(LinearLayout)findViewById(R.id.seclbl1);
            linelbl1=(View)findViewById(R.id.linelbl1);
            secHHID=(LinearLayout)findViewById(R.id.secHHID);
            lineHHID=(View)findViewById(R.id.lineHHID);
            VlblHHID=(TextView) findViewById(R.id.VlblHHID);
            txtHHID=(EditText) findViewById(R.id.txtHHID);
            txtHHID.setText(HHID);
            txtHHID.setEnabled(false);
            secVisitNo=(LinearLayout)findViewById(R.id.secVisitNo);
            lineVisitNo=(View)findViewById(R.id.lineVisitNo);
            VlblVisitNo=(TextView) findViewById(R.id.VlblVisitNo);
            txtVisitNo=(EditText) findViewById(R.id.txtVisitNo);
            if(VISITNO.length()==0) {
                String VNO = C.ReturnSingleValue("Select VisitNo from Visits where HHID='"+ HHID +"' and VisitStatus in('01','02','03')");
                if(VNO.length()>0) {
                    txtVisitNo.setText(VNO);
                }
                else
                    txtVisitNo.setText(VisitSerial(HHID));
            }
            else
                txtVisitNo.setText(VISITNO);



            txtVisitNo.setEnabled(false);
            secVisitDate=(LinearLayout)findViewById(R.id.secVisitDate);
            lineVisitDate=(View)findViewById(R.id.lineVisitDate);
            VlblVisitDate=(TextView) findViewById(R.id.VlblVisitDate);
            dtpVisitDate=(EditText) findViewById(R.id.dtpVisitDate);
            dtpVisitDate.setText(Global.DateNowDMY());
            secVisitStatus=(LinearLayout)findViewById(R.id.secVisitStatus);
            lineVisitStatus=(View)findViewById(R.id.lineVisitStatus);
            VlblVisitStatus=(TextView) findViewById(R.id.VlblVisitStatus);
            spnVisitStatus=(Spinner) findViewById(R.id.spnVisitStatus);
            List<String> listVisitStatus = new ArrayList<String>();

            listVisitStatus.add("");
            listVisitStatus.add("01-Interview Start");
            listVisitStatus.add("02-Interview completed");
            listVisitStatus.add("03-Interview Incomplete");
            listVisitStatus.add("04-No member or suitable person found during house visit");
            listVisitStatus.add("05-All members absent for many days");
            listVisitStatus.add("06-Interview canceled");
            listVisitStatus.add("07-Refused/Reluctance to interview");
            listVisitStatus.add("08-House vacant");
            listVisitStatus.add("09-Address not of any residence");
            listVisitStatus.add("10-Residential Destroyed");
            listVisitStatus.add("11-Dwelling not found");
            listVisitStatus.add("97-Others");
            spnVisitStatus.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listVisitStatus)));

            spnVisitStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnVisitStatus.getSelectedItem().toString().length() != 0)
                    {
                        spnData = Connection.SelectedSpinnerValue(spnVisitStatus.getSelectedItem().toString(), "-");
                    }

                    if(spnData.equalsIgnoreCase("01")
                        || spnData.equalsIgnoreCase("02")
                        || spnData.equalsIgnoreCase("03"))
                    {
                        secVisitStatusOth.setVisibility(View.GONE);
                        lineVisitStatusOth.setVisibility(View.GONE);
                        txtVisitStatusOth.setText("");
                        //secRespID.setVisibility(View.VISIBLE);
                        secHaveDeath.setVisibility(View.VISIBLE);
                    }
                    else if(spnData.equalsIgnoreCase("97"))
                    {
                        secVisitStatusOth.setVisibility(View.VISIBLE);
                        lineVisitStatusOth.setVisibility(View.VISIBLE);
                        secRespID.setVisibility(View.GONE);
                        txtRespID.setText("");
                        secHaveDeath.setVisibility(View.GONE);
                        rdogrpHaveDeath.clearCheck();
                        secTotalDeath.setVisibility(View.GONE);
                        txtTotalDeath.setText("");
                    }
                    else
                    {
                        secVisitStatusOth.setVisibility(View.GONE);
                        lineVisitStatusOth.setVisibility(View.GONE);
                        txtVisitStatusOth.setText("");
                        secRespID.setVisibility(View.GONE);
                        txtRespID.setText("");
                        secHaveDeath.setVisibility(View.GONE);
                        rdogrpHaveDeath.clearCheck();
                        secTotalDeath.setVisibility(View.GONE);
                        txtTotalDeath.setText("");
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secVisitStatusOth=(LinearLayout)findViewById(R.id.secVisitStatusOth);
            lineVisitStatusOth=(View)findViewById(R.id.lineVisitStatusOth);
            VlblVisitStatusOth=(TextView) findViewById(R.id.VlblVisitStatusOth);
            txtVisitStatusOth=(AutoCompleteTextView) findViewById(R.id.txtVisitStatusOth);
            txtVisitStatusOth.setAdapter(C.getArrayAdapter("Select distinct VisitStatusOth from "+ TableName +" order by VisitStatusOth"));

            txtVisitStatusOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

                }
            });
            txtVisitStatusOth.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (txtVisitStatusOth.getRight() - txtVisitStatusOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            ((EditText)v).setText("");
                            return true;
                        }
                    }
                    return false;
                }
            });
            secRespID=(LinearLayout)findViewById(R.id.secRespID);
            lineRespID=(View)findViewById(R.id.lineRespID);
            VlblRespID=(TextView) findViewById(R.id.VlblRespID);
            txtRespID=(EditText) findViewById(R.id.txtRespID);
            secHaveDeath=(LinearLayout)findViewById(R.id.secHaveDeath);
            lineHaveDeath=(View)findViewById(R.id.lineHaveDeath);
            VlblHaveDeath = (TextView) findViewById(R.id.VlblHaveDeath);
            rdogrpHaveDeath = (RadioGroup) findViewById(R.id.rdogrpHaveDeath);
            rdoHaveDeath1 = (RadioButton) findViewById(R.id.rdoHaveDeath1);
            rdoHaveDeath2 = (RadioButton) findViewById(R.id.rdoHaveDeath2);
            rdoHaveDeath3 = (RadioButton) findViewById(R.id.rdoHaveDeath3);
            rdoHaveDeath4 = (RadioButton) findViewById(R.id.rdoHaveDeath4);
            rdogrpHaveDeath.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpHaveDeath = new String[] {"0","1","8","9"};
                    for (int i = 0; i < rdogrpHaveDeath.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpHaveDeath.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpHaveDeath[i];
                    }

                    if(rbData.equalsIgnoreCase("1"))
                    {
                        secTotalDeath.setVisibility(View.VISIBLE);
                        lineTotalDeath.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        secTotalDeath.setVisibility(View.GONE);
                        lineTotalDeath.setVisibility(View.GONE);
                        txtTotalDeath.setText("");
                        secRnd.setVisibility(View.GONE);
                        lineRnd.setVisibility(View.GONE);
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

            secTotalDeath=(LinearLayout)findViewById(R.id.secTotalDeath);
            lineTotalDeath=(View)findViewById(R.id.lineTotalDeath);
            VlblTotalDeath=(TextView) findViewById(R.id.VlblTotalDeath);
            txtTotalDeath=(EditText) findViewById(R.id.txtTotalDeath);
            secRnd=(LinearLayout)findViewById(R.id.secRnd);
            lineRnd=(View)findViewById(R.id.lineRnd);
            VlblRnd=(TextView) findViewById(R.id.VlblRnd);
            txtRnd=(EditText) findViewById(R.id.txtRnd);
            txtRnd.setText(ROUND);
            secVisitNote=(LinearLayout)findViewById(R.id.secVisitNote);
            lineVisitNote=(View)findViewById(R.id.lineVisitNote);
            VlblVisitNote=(TextView) findViewById(R.id.VlblVisitNote);
            txtVisitNote=(EditText) findViewById(R.id.txtVisitNote);
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Baseline_Visits.this, e.getMessage());
            return;
        }
    }

    private void DataSave()
    {
        try
        {
            String ValidationMSG = ValidationCheck();
            if(ValidationMSG.length()>0)
            {
                Connection.MessageBox(Baseline_Visits.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            Visits_DataModel objSave = new Visits_DataModel();
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setVisitNo(txtVisitNo.getText().toString());
            objSave.setVisitDate(dtpVisitDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpVisitDate.getText().toString()) : dtpVisitDate.getText().toString());
            objSave.setVisitStatus(spnVisitStatus.getSelectedItemPosition() == 0 ? "" : spnVisitStatus.getSelectedItem().toString().split("-")[0]);
            objSave.setVisitStatusOth(txtVisitStatusOth.getText().toString());
            objSave.setRespID(txtRespID.getText().toString());
            String[] d_rdogrpHaveDeath = new String[] {"0","1","8","9"};
            objSave.setHaveDeath("");
            for (int i = 0; i < rdogrpHaveDeath.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpHaveDeath.getChildAt(i);
                if (rb.isChecked()) objSave.setHaveDeath(d_rdogrpHaveDeath[i]);
            }

            objSave.setTotalDeath(txtTotalDeath.getText().toString());
            objSave.setRnd(txtRnd.getText().toString());
            objSave.setVisitNote(txtVisitNote.getText().toString());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);
            if(status.length()==0) {
                String VS = spnVisitStatus.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnVisitStatus.getSelectedItem().toString(), "-");

                if (VS.equals("01") || VS.equals("02") || VS.equals("03")) {
                    String HaveDeath = rdoHaveDeath2.isChecked()?"1":"2";
                    String TotalDeath = txtTotalDeath.getText().toString().length()==0?"0":txtTotalDeath.getText().toString();

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("res", "");
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("HHID", HHID);
                    IDbundle.putString("VisitNo", txtVisitNo.getText().toString());
                    IDbundle.putString("HHNO", HHNO);
                    IDbundle.putString("VillID", VILLID);
                    IDbundle.putString("CompoundID", CompoundID);
                    IDbundle.putString("MemID", "");
                    IDbundle.putString("CompoundName", COMPOUNDNAME);
                    IDbundle.putString("CompoundAdrs", COMPOUNDADRS);
                    IDbundle.putString("HHHead", HHHEAD);
                    IDbundle.putString("havedeath", HaveDeath);
                    IDbundle.putString("totaldeath", TotalDeath);
                    IDbundle.putString("round", ROUND);
                    IDbundle.putString("SurvType", SurvType);
                    IDbundle.putString("visitdate", Global.DateConvertYMD(dtpVisitDate.getText().toString()));
                    Intent f1 = new Intent(getApplicationContext(), Baseline_Member_list.class);
                    f1.putExtras(IDbundle);
                    startActivityForResult(f1, 1);
                } else {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("res", "");
                    setResult(Activity.RESULT_OK, returnIntent);
                    Toast.makeText(Baseline_Visits.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            else{
                Connection.MessageBox(Baseline_Visits.this, status);
                return;
            }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Baseline_Visits.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck()
    {
        String ValidationMsg = "";
        String DV = "";
        try
        {
            ResetSectionColor();
            if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
            {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtVisitNo.getText().toString().length()==0 & secVisitNo.isShown())
            {
                ValidationMsg += "\n1. Required field: Visit No.";
                secVisitNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpVisitDate.getText().toString());
            if(DV.length()!=0 & secVisitDate.isShown())
            {
                ValidationMsg += "\n2. Required field/Not a valid date format: Visit date.";
                secVisitDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(spnVisitStatus.getSelectedItemPosition()==0  & secVisitStatus.isShown())
            {
                ValidationMsg += "\n3. Required field: Visit Status.";
                secVisitStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtVisitStatusOth.getText().toString().length()==0 & secVisitStatusOth.isShown())
            {
                ValidationMsg += "\nRequired field: Other Status.";
                secVisitStatusOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtRespID.getText().toString().length()==0 & secRespID.isShown())
            {
                ValidationMsg += "\n4. Required field: Respondents Name.";
                secRespID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoHaveDeath1.isChecked() & !rdoHaveDeath2.isChecked() & !rdoHaveDeath3.isChecked() & !rdoHaveDeath4.isChecked() & secHaveDeath.isShown())
            {
                ValidationMsg += "\n5. Required field: During the past 12 months, has any member living in this house died?.";
                secHaveDeath.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtTotalDeath.getText().toString().length()==0 & secTotalDeath.isShown())
            {
                ValidationMsg += "\n6. Required field: How many members living in this house have died?.";
                secTotalDeath.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(secTotalDeath.isShown() & (Integer.valueOf(txtTotalDeath.getText().toString().length()==0 ? "1" : txtTotalDeath.getText().toString()) < 1 || Integer.valueOf(txtTotalDeath.getText().toString().length()==0 ? "9" : txtTotalDeath.getText().toString()) > 9))
            {
                ValidationMsg += "\n6. Value should be between 1 and 9(How many members living in this house have died?).";
                secTotalDeath.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
            {
                ValidationMsg += "\nRequired field: Round No.";
                secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            /*if(txtVisitNote.getText().toString().length()==0 & secVisitNote.isShown())
            {
                ValidationMsg += "\n7. Required field: Household Visit Note.";
                secVisitNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }*/
        }
        catch(Exception  e)
        {
            ValidationMsg += "\n"+ e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor()
    {
        try
        {
            secHHID.setBackgroundColor(Color.WHITE);
            secVisitNo.setBackgroundColor(Color.WHITE);
            secVisitDate.setBackgroundColor(Color.WHITE);
            secVisitStatus.setBackgroundColor(Color.WHITE);
            secVisitStatusOth.setBackgroundColor(Color.WHITE);
            secRespID.setBackgroundColor(Color.WHITE);
            secHaveDeath.setBackgroundColor(Color.WHITE);
            secTotalDeath.setBackgroundColor(Color.WHITE);
            secTotalDeath.setBackgroundColor(Color.WHITE);
            secRnd.setBackgroundColor(Color.WHITE);
            secVisitNote.setBackgroundColor(Color.WHITE);
        }
        catch(Exception  e)
        {
        }
    }

    private void DataSearch(String HHID, String VisitNo)
    {
        try
        {
            RadioButton rb;
            Visits_DataModel d = new Visits_DataModel();
            String SQL = "Select * from "+ TableName +"  Where HHID='"+ HHID +"' and VisitNo='"+ VisitNo +"' and Rnd='"+ ROUND +"'";
            List<Visits_DataModel> data = d.SelectAll(this, SQL);
            for(Visits_DataModel item : data){
                txtHHID.setText(item.getHHID());
                txtVisitNo.setText(item.getVisitNo());
                dtpVisitDate.setText(item.getVisitDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getVisitDate()));
                spnVisitStatus.setSelection(Global.SpinnerItemPositionAnyLength(spnVisitStatus, String.valueOf(item.getVisitStatus())));
                txtVisitStatusOth.setText(item.getVisitStatusOth());
                txtRespID.setText(item.getRespID());
                String[] d_rdogrpHaveDeath = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpHaveDeath.length; i++)
                {
                    if (String.valueOf(item.getHaveDeath()).equals(String.valueOf(d_rdogrpHaveDeath[i])))
                    {
                        rb = (RadioButton)rdogrpHaveDeath.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtTotalDeath.setText(String.valueOf(item.getTotalDeath()));
                txtRnd.setText(item.getRnd());
                txtVisitNote.setText(item.getVisitNote());
            }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Baseline_Visits.this, e.getMessage());
            return;
        }
    }



    protected Dialog onCreateDialog(int id) {
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mDateSetListener,g.mYear,g.mMonth-1,g.mDay);
            case TIME_DIALOG:
                return new TimePickerDialog(this, timePickerListener, hour, minute,false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year; mMonth = monthOfYear+1; mDay = dayOfMonth;
            EditText dtpDate;


            dtpDate = (EditText)findViewById(R.id.dtpVisitDate);
            if (VariableID.equals("btnVisitDate"))
            {
                dtpDate = (EditText)findViewById(R.id.dtpVisitDate);
            }
            dtpDate.setText(new StringBuilder()
                    .append(Global.Right("00"+mDay,2)).append("/")
                    .append(Global.Right("00"+mMonth,2)).append("/")
                    .append(mYear));
        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour; minute = selectedMinute;
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