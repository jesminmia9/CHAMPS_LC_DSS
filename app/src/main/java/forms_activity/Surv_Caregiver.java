
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
import forms_datamodel.Caregiver_DataModel;

public class Surv_Caregiver extends AppCompatActivity {
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
     final int DATE_DIALOG = 1;
     final int TIME_DIALOG = 2;

    Connection C;
    Global g;
    SimpleAdapter dataAdapter;
    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    TextView lblHeading;
    LinearLayout secCaregID;
    View lineCaregID;
    TextView VlblCaregID;
    EditText txtCaregID;
    LinearLayout seclbl01;
    View linelbl01;
    LinearLayout seclbl02;
    View linelbl02;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secRespName;
    View lineRespName;
    TextView VlblRespName;
    EditText txtRespName;
    LinearLayout secRespID;
    View lineRespID;
    TextView VlblRespID;
    EditText txtRespID;
    LinearLayout secIsMem;
    View lineIsMem;
    TextView VlblIsMem;
    RadioGroup rdogrpIsMem;
    RadioButton rdoIsMem1;
    RadioButton rdoIsMem2;
    LinearLayout secCMemID;
    View lineCMemID;
    TextView VlblCMemID;
    Spinner spnCMemID;
    LinearLayout secPriCaregiver;
    View linePriCaregiver;
    TextView VlblPriCaregiver;
    RadioGroup rdogrpPriCaregiver;
    RadioButton rdoPriCaregiver1;
    RadioButton rdoPriCaregiver2;
    RadioButton rdoPriCaregiver3;
    RadioButton rdoPriCaregiver4;
    LinearLayout secWhoPriCaregiver;
    View lineWhoPriCaregiver;
    TextView VlblWhoPriCaregiver;
    Spinner spnWhoPriCaregiver;
    LinearLayout secWhoPrCaregOth;
    View lineWhoPrCaregOth;
    TextView VlblWhoPrCaregOth;
    EditText txtWhoPrCaregOth;
    LinearLayout secPriCaregID;
    View linePriCaregID;
    TextView VlblPriCaregID;
    EditText txtPriCaregID;
    LinearLayout secRthChild;
    View lineRthChild;
    TextView VlblRthChild;
    Spinner spnRthChild;
    LinearLayout secRthChildOth;
    View lineRthChildOth;
    TextView VlblRthChildOth;
    EditText txtRthChildOth;
    LinearLayout secReligGroup;
    View lineReligGroup;
    TextView VlblReligGroup;
    Spinner spnReligGroup;
    LinearLayout secReligGroupOth;
    View lineReligGroupOth;
    TextView VlblReligGroupOth;
    EditText txtReligGroupOth;
    LinearLayout secEthnicGroup;
    View lineEthnicGroup;
    TextView VlblEthnicGroup;
    Spinner spnEthnicGroup;
    LinearLayout secEthnicGroupOth;
    View lineEthnicGroupOth;
    TextView VlblEthnicGroupOth;
    EditText txtEthnicGroupOth;
    LinearLayout secCrgvEduY;
    View lineCrgvEduY;
    TextView VlblCrgvEduY;
    Spinner spnCrgvEduY;
    LinearLayout secCrgvEduYDk;
    View lineCrgvEduYDk;
    TextView VlblCrgvEduYDk;
    RadioGroup rdogrpCrgvEduYDk;
    RadioButton rdoCrgvEduYDk1;
    RadioButton rdoCrgvEduYDk2;
    LinearLayout secEmpStatus;
    View lineEmpStatus;
    TextView VlblEmpStatus;
    Spinner spnEmpStatus;
    /*RadioGroup rdogrpEmpStatus;
    RadioButton rdoEmpStatus1;
    RadioButton rdoEmpStatus2;
    RadioButton rdoEmpStatus3;
    RadioButton rdoEmpStatus4;
    RadioButton rdoEmpStatus5;
    RadioButton rdoEmpStatus6;
    RadioButton rdoEmpStatus7;*/
    LinearLayout secEmployStsOth;
    View lineEmployStsOth;
    TextView VlblEmployStsOth;
    EditText txtEmployStsOth;
    LinearLayout secMainOccu;
    View lineMainOccu;
    TextView VlblMainOccu;
    Spinner spnMainOccu;
    LinearLayout secMainOccuDk;
    View lineMainOccuDk;
    TextView VlblMainOccuDk;
    RadioGroup rdogrpMainOccuDk;
    RadioButton rdoMainOccuDk1;
    RadioButton rdoMainOccuDk2;
    RadioButton rdoMainOccuDk3;
    LinearLayout secMainOccuOth;
    View lineMainOccuOth;
    TextView VlblMainOccuOth;
    EditText txtMainOccuOth;

     int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String MEMID = "";
     String HHID = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            setContentView(R.layout.caregiver);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            MEMID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            ((TextView)findViewById(R.id.tv_member_name)).setText(IDbundle.getString("MemName","[Name]"));

            TableName = "Caregiver";
            MODULEID = 16;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView)findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Caregiver.this);
                    adb.setTitle("Close");
                    adb.setMessage("Do you want to close this form[Yes/No]?");
                    adb.setNegativeButton("No", null);
                    adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }});
                    adb.show();
                }});

            Initialization();
            Connection.LocalizeLanguage(Surv_Caregiver.this, MODULEID, LANGUAGEID);

            spnCMemID.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='"+ HHID +"' and MemID <> '"+MEMID+"' and Active = '1'"));
            //spnPriCaregID.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='"+ HHID +"' and MemID <> '"+MEMID+"' and Active = '1'"));
            spnEthnicGroup.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(Global_CodeList.Get_ETHNICITY(ProjectSetting.SITE_CODE))));




            //Hide all skip variables
            secCaregID.setVisibility(View.GONE);
            lineCaregID.setVisibility(View.GONE);
            secMemID.setVisibility(View.GONE);
            lineMemID.setVisibility(View.GONE);
            secHHID.setVisibility(View.GONE);
            lineHHID.setVisibility(View.GONE);
            secRespName.setVisibility(View.GONE);
            lineRespName.setVisibility(View.GONE);
            secRespID.setVisibility(View.GONE);
            lineRespID.setVisibility(View.GONE);
            secCMemID.setVisibility(View.GONE);
            lineCMemID.setVisibility(View.GONE);
            secWhoPriCaregiver.setVisibility(View.GONE);
            lineWhoPriCaregiver.setVisibility(View.GONE);
            secWhoPrCaregOth.setVisibility(View.GONE);
            lineWhoPrCaregOth.setVisibility(View.GONE);
            //sec.setVisibility(View.GONE);
            //line.setVisibility(View.GONE);
            secRthChildOth.setVisibility(View.GONE);
            lineRthChildOth.setVisibility(View.GONE);
            secReligGroupOth.setVisibility(View.GONE);
            lineReligGroupOth.setVisibility(View.GONE);
            //sec.setVisibility(View.GONE);
            //line.setVisibility(View.GONE);
            secEthnicGroupOth.setVisibility(View.GONE);
            lineEthnicGroupOth.setVisibility(View.GONE);
            secEmployStsOth.setVisibility(View.GONE);
            lineEmployStsOth.setVisibility(View.GONE);
            secMainOccuOth.setVisibility(View.GONE);
            lineMainOccuOth.setVisibility(View.GONE);

            secCMemID.setVisibility(View.GONE);
            lineCMemID.setVisibility(View.GONE);
            secPriCaregiver.setVisibility(View.GONE);
            linePriCaregiver.setVisibility(View.GONE);
            rdogrpPriCaregiver.clearCheck();
            secWhoPriCaregiver.setVisibility(View.GONE);
            lineWhoPriCaregiver.setVisibility(View.GONE);
            spnWhoPriCaregiver.setSelection(0);
            secWhoPrCaregOth.setVisibility(View.GONE);
            lineWhoPrCaregOth.setVisibility(View.GONE);
            txtWhoPrCaregOth.setText("");
            secPriCaregID.setVisibility(View.GONE);
            linePriCaregID.setVisibility(View.GONE);
            txtPriCaregID.setText("");
            secRthChild.setVisibility(View.GONE);
            lineRthChild.setVisibility(View.GONE);
            spnRthChild.setSelection(0);
            secRthChildOth.setVisibility(View.GONE);
            lineRthChildOth.setVisibility(View.GONE);
            txtRthChildOth.setText("");
            secReligGroup.setVisibility(View.GONE);
            lineReligGroup.setVisibility(View.GONE);
            spnReligGroup.setSelection(0);
            secReligGroupOth.setVisibility(View.GONE);
            lineReligGroupOth.setVisibility(View.GONE);
            txtReligGroupOth.setText("");
            secEthnicGroup.setVisibility(View.GONE);
            lineEthnicGroup.setVisibility(View.GONE);
            spnEthnicGroup.setSelection(0);
            secEthnicGroupOth.setVisibility(View.GONE);
            lineEthnicGroupOth.setVisibility(View.GONE);
            txtEthnicGroupOth.setText("");
            secCrgvEduY.setVisibility(View.GONE);
            lineCrgvEduY.setVisibility(View.GONE);
            spnCrgvEduY.setSelection(0);
            secCrgvEduYDk.setVisibility(View.GONE);
            lineCrgvEduYDk.setVisibility(View.GONE);
            rdogrpCrgvEduYDk.clearCheck();
            secEmpStatus.setVisibility(View.GONE);
            lineEmpStatus.setVisibility(View.GONE);
            spnEmpStatus.setSelection(0);
            secEmployStsOth.setVisibility(View.GONE);
            lineEmployStsOth.setVisibility(View.GONE);
            txtEmployStsOth.setText("");
            secMainOccu.setVisibility(View.GONE);
            lineMainOccu.setVisibility(View.GONE);
            spnMainOccu.setSelection(0);
            secMainOccuDk.setVisibility(View.GONE);
            lineMainOccuDk.setVisibility(View.GONE);
            rdogrpMainOccuDk.clearCheck();
            secMainOccuOth.setVisibility(View.GONE);
            lineMainOccuOth.setVisibility(View.GONE);
            txtMainOccuOth.setText("");

            DataSearch(MEMID);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }});

            Global.RoleManagement(cmdSave, MySharedPreferences.getValue(this, "userrole"));
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Caregiver.this, e.getMessage());
            return;
        }
    }

    private void Initialization()
    {
        try
        {
            secCaregID=(LinearLayout)findViewById(R.id.secCaregID);
            lineCaregID=(View)findViewById(R.id.lineCaregID);
            VlblCaregID=(TextView) findViewById(R.id.VlblCaregID);
            txtCaregID=(EditText) findViewById(R.id.txtCaregID);
            txtCaregID.setText(Global.Get_UUID(DEVICEID));
            txtCaregID.setEnabled(false);
            seclbl01=(LinearLayout)findViewById(R.id.seclbl01);
            linelbl01=(View)findViewById(R.id.linelbl01);
            seclbl02=(LinearLayout)findViewById(R.id.seclbl02);
            linelbl02=(View)findViewById(R.id.linelbl02);
            secMemID=(LinearLayout)findViewById(R.id.secMemID);
            lineMemID=(View)findViewById(R.id.lineMemID);
            VlblMemID=(TextView) findViewById(R.id.VlblMemID);
            txtMemID=(EditText) findViewById(R.id.txtMemID);
            txtMemID.setText(MEMID);
            secHHID=(LinearLayout)findViewById(R.id.secHHID);
            lineHHID=(View)findViewById(R.id.lineHHID);
            VlblHHID=(TextView) findViewById(R.id.VlblHHID);
            txtHHID=(EditText) findViewById(R.id.txtHHID);
            txtHHID.setText(HHID);
            secRespName=(LinearLayout)findViewById(R.id.secRespName);
            lineRespName=(View)findViewById(R.id.lineRespName);
            VlblRespName=(TextView) findViewById(R.id.VlblRespName);
            txtRespName=(EditText) findViewById(R.id.txtRespName);
            secRespID=(LinearLayout)findViewById(R.id.secRespID);
            lineRespID=(View)findViewById(R.id.lineRespID);
            VlblRespID=(TextView) findViewById(R.id.VlblRespID);
            txtRespID=(EditText) findViewById(R.id.txtRespID);
            secIsMem=(LinearLayout)findViewById(R.id.secIsMem);
            lineIsMem=(View)findViewById(R.id.lineIsMem);
            VlblIsMem = (TextView) findViewById(R.id.VlblIsMem);
            rdogrpIsMem = (RadioGroup) findViewById(R.id.rdogrpIsMem);
            rdoIsMem1 = (RadioButton) findViewById(R.id.rdoIsMem1);
            rdoIsMem2 = (RadioButton) findViewById(R.id.rdoIsMem2);
            rdogrpIsMem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpIsMem = new String[] {"0","1"};
                    for (int i = 0; i < rdogrpIsMem.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpIsMem.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpIsMem[i];
                    }

                    if(rbData.equalsIgnoreCase("1"))
                    {
                        secCMemID.setVisibility(View.VISIBLE);
                        lineCMemID.setVisibility(View.VISIBLE);
                        secPriCaregiver.setVisibility(View.GONE);
                        linePriCaregiver.setVisibility(View.GONE);
                        rdogrpPriCaregiver.clearCheck();
                        secWhoPriCaregiver.setVisibility(View.GONE);
                        lineWhoPriCaregiver.setVisibility(View.GONE);
                        spnWhoPriCaregiver.setSelection(0);
                        secWhoPrCaregOth.setVisibility(View.GONE);
                        lineWhoPrCaregOth.setVisibility(View.GONE);
                        txtWhoPrCaregOth.setText("");
                        secPriCaregID.setVisibility(View.GONE);
                        linePriCaregID.setVisibility(View.GONE);
                        txtPriCaregID.setText("");
                        secRthChild.setVisibility(View.GONE);
                        lineRthChild.setVisibility(View.GONE);
                        spnRthChild.setSelection(0);
                        secRthChildOth.setVisibility(View.GONE);
                        lineRthChildOth.setVisibility(View.GONE);
                        txtRthChildOth.setText("");
                        secReligGroup.setVisibility(View.GONE);
                        lineReligGroup.setVisibility(View.GONE);
                        spnReligGroup.setSelection(0);
                        secReligGroupOth.setVisibility(View.GONE);
                        lineReligGroupOth.setVisibility(View.GONE);
                        txtReligGroupOth.setText("");
                        secEthnicGroup.setVisibility(View.GONE);
                        lineEthnicGroup.setVisibility(View.GONE);
                        spnEthnicGroup.setSelection(0);
                        secEthnicGroupOth.setVisibility(View.GONE);
                        lineEthnicGroupOth.setVisibility(View.GONE);
                        txtEthnicGroupOth.setText("");
                        secCrgvEduY.setVisibility(View.GONE);
                        lineCrgvEduY.setVisibility(View.GONE);
                        spnCrgvEduY.setSelection(0);
                        secCrgvEduYDk.setVisibility(View.GONE);
                        lineCrgvEduYDk.setVisibility(View.GONE);
                        rdogrpCrgvEduYDk.clearCheck();
                        secEmpStatus.setVisibility(View.GONE);
                        lineEmpStatus.setVisibility(View.GONE);
                        spnEmpStatus.setSelection(0);
                        secEmployStsOth.setVisibility(View.GONE);
                        lineEmployStsOth.setVisibility(View.GONE);
                        txtEmployStsOth.setText("");
                        secMainOccu.setVisibility(View.GONE);
                        lineMainOccu.setVisibility(View.GONE);
                        spnMainOccu.setSelection(0);
                        secMainOccuDk.setVisibility(View.GONE);
                        lineMainOccuDk.setVisibility(View.GONE);
                        rdogrpMainOccuDk.clearCheck();
                        secMainOccuOth.setVisibility(View.GONE);
                        lineMainOccuOth.setVisibility(View.GONE);
                        txtMainOccuOth.setText("");
                    }
                    else
                    {
                        secCMemID.setVisibility(View.GONE);
                        lineCMemID.setVisibility(View.GONE);
                        secPriCaregiver.setVisibility(View.VISIBLE);
                        linePriCaregiver.setVisibility(View.VISIBLE);
                        secWhoPriCaregiver.setVisibility(View.VISIBLE);
                        lineWhoPriCaregiver.setVisibility(View.VISIBLE);
                        //secWhoPrCaregOth.setVisibility(View.VISIBLE);
                        //lineWhoPrCaregOth.setVisibility(View.VISIBLE);
                        secPriCaregID.setVisibility(View.VISIBLE);
                        linePriCaregID.setVisibility(View.VISIBLE);
                        secRthChild.setVisibility(View.VISIBLE);
                        lineRthChild.setVisibility(View.VISIBLE);
                        //secRthChildOth.setVisibility(View.VISIBLE);
                        //lineRthChildOth.setVisibility(View.VISIBLE);
                        secReligGroup.setVisibility(View.VISIBLE);
                        lineReligGroup.setVisibility(View.VISIBLE);
                        //secReligGroupOth.setVisibility(View.VISIBLE);
                        //lineReligGroupOth.setVisibility(View.VISIBLE);
                        secEthnicGroup.setVisibility(View.VISIBLE);
                        lineEthnicGroup.setVisibility(View.VISIBLE);
                        //secEthnicGroupOth.setVisibility(View.VISIBLE);
                        //lineEthnicGroupOth.setVisibility(View.VISIBLE);
                        secCrgvEduY.setVisibility(View.VISIBLE);
                        lineCrgvEduY.setVisibility(View.VISIBLE);
                        secCrgvEduYDk.setVisibility(View.VISIBLE);
                        lineCrgvEduYDk.setVisibility(View.VISIBLE);
                        secEmpStatus.setVisibility(View.VISIBLE);
                        lineEmpStatus.setVisibility(View.VISIBLE);
                        //secEmployStsOth.setVisibility(View.VISIBLE);
                        //lineEmployStsOth.setVisibility(View.VISIBLE);
                        secMainOccu.setVisibility(View.VISIBLE);
                        lineMainOccu.setVisibility(View.VISIBLE);
                        secMainOccuDk.setVisibility(View.VISIBLE);
                        lineMainOccuDk.setVisibility(View.VISIBLE);
                    }

                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secCMemID=(LinearLayout)findViewById(R.id.secCMemID);
            lineCMemID=(View)findViewById(R.id.lineCMemID);
            VlblCMemID=(TextView) findViewById(R.id.VlblCMemID);
            spnCMemID=(Spinner) findViewById(R.id.spnCMemID);
            /*List<String> listCMemID = new ArrayList<String>();

            listCMemID.add("");
            listCMemID.add("01-Mem1");
            listCMemID.add("02-Mem2");

            spnCMemID.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listCMemID)));*/
            spnCMemID.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='"+ HHID +"' and MemID <> '"+MEMID+"' and Active = '1'"));

            secPriCaregiver=(LinearLayout)findViewById(R.id.secPriCaregiver);
            linePriCaregiver=(View)findViewById(R.id.linePriCaregiver);
            VlblPriCaregiver = (TextView) findViewById(R.id.VlblPriCaregiver);
            rdogrpPriCaregiver = (RadioGroup) findViewById(R.id.rdogrpPriCaregiver);
            rdoPriCaregiver1 = (RadioButton) findViewById(R.id.rdoPriCaregiver1);
            rdoPriCaregiver2 = (RadioButton) findViewById(R.id.rdoPriCaregiver2);
            rdoPriCaregiver3 = (RadioButton) findViewById(R.id.rdoPriCaregiver3);
            rdoPriCaregiver4 = (RadioButton) findViewById(R.id.rdoPriCaregiver4);
            rdogrpPriCaregiver.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpPriCaregiver = new String[] {"0","1","8","9"};
                    for (int i = 0; i < rdogrpPriCaregiver.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpPriCaregiver.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpPriCaregiver[i];
                    }

                    if(rbData.equalsIgnoreCase("1"))
                    {
                        secWhoPriCaregiver.setVisibility(View.GONE);
                        lineWhoPriCaregiver.setVisibility(View.GONE);
                        spnWhoPriCaregiver.setSelection(0);
                        secWhoPrCaregOth.setVisibility(View.GONE);
                        lineWhoPrCaregOth.setVisibility(View.GONE);
                        txtWhoPrCaregOth.setText("");
                    }
                    else
                    {
                        secWhoPriCaregiver.setVisibility(View.VISIBLE);
                        lineWhoPriCaregiver.setVisibility(View.VISIBLE);
                        //secWhoPrCaregOth.setVisibility(View.VISIBLE);
                        //lineWhoPrCaregOth.setVisibility(View.VISIBLE);
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secWhoPriCaregiver=(LinearLayout)findViewById(R.id.secWhoPriCaregiver);
            lineWhoPriCaregiver=(View)findViewById(R.id.lineWhoPriCaregiver);
            VlblWhoPriCaregiver=(TextView) findViewById(R.id.VlblWhoPriCaregiver);
            spnWhoPriCaregiver=(Spinner) findViewById(R.id.spnWhoPriCaregiver);


            spnWhoPriCaregiver.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(Global_CodeList.Get_CAREGIVER_RELATION())));
            spnWhoPriCaregiver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnWhoPriCaregiver.getSelectedItem().toString().length() != 0)
                    {
                        spnData = Connection.SelectedSpinnerValue(spnWhoPriCaregiver.getSelectedItem().toString(), "-");
                    }
                    if(spnData.equalsIgnoreCase("97"))
                    {

                        secWhoPrCaregOth.setVisibility(View.VISIBLE);
                        lineWhoPrCaregOth.setVisibility(View.VISIBLE);
                    }
                    else
                    {   secWhoPrCaregOth.setVisibility(View.GONE);
                        lineWhoPrCaregOth.setVisibility(View.GONE);
                        txtWhoPrCaregOth.setText("");
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secWhoPrCaregOth=(LinearLayout)findViewById(R.id.secWhoPrCaregOth);
            lineWhoPrCaregOth=(View)findViewById(R.id.lineWhoPrCaregOth);
            VlblWhoPrCaregOth=(TextView) findViewById(R.id.VlblWhoPrCaregOth);
            txtWhoPrCaregOth=(EditText) findViewById(R.id.txtWhoPrCaregOth);
            secPriCaregID=(LinearLayout)findViewById(R.id.secPriCaregID);
            linePriCaregID=(View)findViewById(R.id.linePriCaregID);
            VlblPriCaregID=(TextView) findViewById(R.id.VlblPriCaregID);
            txtPriCaregID=(EditText) findViewById(R.id.txtPriCaregID);
            //spnPriCaregID.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='"+ HHID +"' and MemID <> '"+MEMID+"'"));

            secRthChild=(LinearLayout)findViewById(R.id.secRthChild);
            lineRthChild=(View)findViewById(R.id.lineRthChild);
            VlblRthChild=(TextView) findViewById(R.id.VlblRthChild);
            spnRthChild=(Spinner) findViewById(R.id.spnRthChild);

            spnRthChild.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(Global_CodeList.Get_CAREGIVER_RELATION())));

            spnRthChild.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnRthChild.getSelectedItem().toString().length() != 0)
                    {
                        spnData = Connection.SelectedSpinnerValue(spnRthChild.getSelectedItem().toString(), "-");
                    }
                    if(spnData.equalsIgnoreCase("97"))
                    {
                        secRthChildOth.setVisibility(View.VISIBLE);
                        lineRthChildOth.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        secRthChildOth.setVisibility(View.GONE);
                        lineRthChildOth.setVisibility(View.GONE);
                        txtRthChildOth.setText("");
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secRthChildOth=(LinearLayout)findViewById(R.id.secRthChildOth);
            lineRthChildOth=(View)findViewById(R.id.lineRthChildOth);
            VlblRthChildOth=(TextView) findViewById(R.id.VlblRthChildOth);
            txtRthChildOth=(EditText) findViewById(R.id.txtRthChildOth);
            secReligGroup=(LinearLayout)findViewById(R.id.secReligGroup);
            lineReligGroup=(View)findViewById(R.id.lineReligGroup);
            VlblReligGroup=(TextView) findViewById(R.id.VlblReligGroup);
            spnReligGroup=(Spinner) findViewById(R.id.spnReligGroup);
            List<String> listReligGroup = new ArrayList<String>();

            /*listReligGroup.add("");
            listReligGroup.add("01-African independent church");
            listReligGroup.add("02-African tribal tradition");
            listReligGroup.add("03-Anglican");
            listReligGroup.add("04-Animist");
            listReligGroup.add("05-Agnostic");
            listReligGroup.add("06-Atheist");
            listReligGroup.add("07-Baptist");
            listReligGroup.add("08-Buddhist");
            listReligGroup.add("09-Catholic");
            listReligGroup.add("10-Christian unspecified");
            listReligGroup.add("11-Evangelical");
            listReligGroup.add("12-Hindu");
            listReligGroup.add("13-Legio Maria");
            listReligGroup.add("14-Methodist");
            listReligGroup.add("15-Islam/Muslim");
            listReligGroup.add("16-Nomia");
            listReligGroup.add("17-No Religion");
            listReligGroup.add("18-Orthodox Christian");
            listReligGroup.add("19-Presbyterian");
            listReligGroup.add("20-Protestant Christian");
            listReligGroup.add("21-Roho");
            listReligGroup.add("22-SDA");
            listReligGroup.add("23-Traditional/Traditionalist");
            listReligGroup.add("24-Zion");
            listReligGroup.add("97-Other");
            listReligGroup.add("98-Dont know");
            listReligGroup.add("99-Refused to respond");*/

            /*if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1) ||
                    ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2))
            {
                listReligGroup.add("");
                listReligGroup.add("15-Muslim");
                listReligGroup.add("23-Traditional");
                listReligGroup.add("09-Christian: Catholic");
                listReligGroup.add("20-Christian: protestant/Pentecostal");
                listReligGroup.add("25-Christian: Jehovah Witness");
                listReligGroup.add("10-Christian: unspecified");
                listReligGroup.add("00-No Religion");
                listReligGroup.add("97-Other specify");
                listReligGroup.add("98-Don’t know");
                listReligGroup.add("99-Refused to respond");

                spnReligGroup.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listReligGroup)));
            }
            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1))
            {
                listReligGroup.add("");
                listReligGroup.add("0-No Religion");
                listReligGroup.add("9-Christian: Catholic");
                listReligGroup.add("10-Christian unspecified");
                listReligGroup.add("15-Islam/Muslim");
                listReligGroup.add("20-Christian: protestant/Pentecostal");
                listReligGroup.add("23-Traditional/Traditionalist");
                listReligGroup.add("25-Christian: Jehovah Witness");
                listReligGroup.add("96-Other specify");
                listReligGroup.add("98-Don't know");
                listReligGroup.add("99-Refused to respond");

                spnReligGroup.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listReligGroup)));
            }
            else {
                spnReligGroup.setAdapter(C.getArrayAdapter("Select '' union Select Code||'-'||Name from Religion"));
            }*/

            spnReligGroup.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_RELIGION())));
            spnReligGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnReligGroup.getSelectedItem().toString().length() != 0)
                    {
                        spnData = Connection.SelectedSpinnerValue(spnReligGroup.getSelectedItem().toString(), "-");
                    }
                    if(spnData.equalsIgnoreCase("97"))
                    {
                        secReligGroupOth.setVisibility(View.VISIBLE);
                        lineReligGroupOth.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        secReligGroupOth.setVisibility(View.GONE);
                        lineReligGroupOth.setVisibility(View.GONE);
                        txtReligGroupOth.setText("");
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secReligGroupOth=(LinearLayout)findViewById(R.id.secReligGroupOth);
            lineReligGroupOth=(View)findViewById(R.id.lineReligGroupOth);
            VlblReligGroupOth=(TextView) findViewById(R.id.VlblReligGroupOth);
            txtReligGroupOth=(EditText) findViewById(R.id.txtReligGroupOth);
            secEthnicGroup=(LinearLayout)findViewById(R.id.secEthnicGroup);
            lineEthnicGroup=(View)findViewById(R.id.lineEthnicGroup);
            VlblEthnicGroup=(TextView) findViewById(R.id.VlblEthnicGroup);
            spnEthnicGroup=(Spinner) findViewById(R.id.spnEthnicGroup);
            /*List<String> listEthnicGroup = new ArrayList<String>();
            listEthnicGroup.addAll(C.getDataList("Select '' union Select Code||'-'||Name from Ethnicity"));
            //listEthnicGroup.add("");
            listEthnicGroup.add("97-Other");
            listEthnicGroup.add("98-Dont know");
            listEthnicGroup.add("99-Refused to respond");*/
            spnEthnicGroup.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(Global_CodeList.Get_ETHNICITY(ProjectSetting.SITE_CODE))));
            spnEthnicGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnEthnicGroup.getSelectedItem().toString().length() != 0)
                    {
                        spnData = Connection.SelectedSpinnerValue(spnEthnicGroup.getSelectedItem().toString(), "-");
                    }
                    if(spnData.equalsIgnoreCase("97"))
                    {
                        secEthnicGroupOth.setVisibility(View.VISIBLE);
                        lineEthnicGroupOth.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        secEthnicGroupOth.setVisibility(View.GONE);
                        lineEthnicGroupOth.setVisibility(View.GONE);
                        txtEthnicGroupOth.setText("");
                    }

                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    return;
                }
            });


            secEthnicGroupOth=(LinearLayout)findViewById(R.id.secEthnicGroupOth);
            lineEthnicGroupOth=(View)findViewById(R.id.lineEthnicGroupOth);
            VlblEthnicGroupOth=(TextView) findViewById(R.id.VlblEthnicGroupOth);
            txtEthnicGroupOth=(EditText) findViewById(R.id.txtEthnicGroupOth);
            secCrgvEduY=(LinearLayout)findViewById(R.id.secCrgvEduY);
            lineCrgvEduY=(View)findViewById(R.id.lineCrgvEduY);
            VlblCrgvEduY=(TextView) findViewById(R.id.VlblCrgvEduY);
            spnCrgvEduY=(Spinner) findViewById(R.id.spnCrgvEduY);
            spnCrgvEduY.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_EDUCATION())));
            /*txtCrgvEduY.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() != 0){
                        if (charSequence.toString().equalsIgnoreCase("98")){
                            rdoCrgvEduYDk1.setChecked(true);
                        } else if (charSequence.toString().equalsIgnoreCase("99")) {
                            rdoCrgvEduYDk2.setChecked(true);
                        }else{
                            rdogrpCrgvEduYDk.clearCheck();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });*/
            secCrgvEduYDk=(LinearLayout)findViewById(R.id.secCrgvEduYDk);
            lineCrgvEduYDk=(View)findViewById(R.id.lineCrgvEduYDk);
            VlblCrgvEduYDk = (TextView) findViewById(R.id.VlblCrgvEduYDk);
            rdogrpCrgvEduYDk = (RadioGroup) findViewById(R.id.rdogrpCrgvEduYDk);
            rdogrpCrgvEduYDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rdoCrgvEduYDk1.isChecked()){
                        //txtCrgvEduY.setText("98");
                    } else if (rdoCrgvEduYDk2.isChecked()) {
                        //txtCrgvEduY.setText("99");
                    }
                }
            });
            rdoCrgvEduYDk1 = (RadioButton) findViewById(R.id.rdoCrgvEduYDk1);
            rdoCrgvEduYDk2 = (RadioButton) findViewById(R.id.rdoCrgvEduYDk2);
            secEmpStatus=(LinearLayout)findViewById(R.id.secEmpStatus);
            lineEmpStatus=(View)findViewById(R.id.lineEmpStatus);
            VlblEmpStatus = (TextView) findViewById(R.id.VlblEmpStatus);
            spnEmpStatus = (Spinner) findViewById(R.id.spnEmpStatus);

            /*List<String> listEmploy = new ArrayList<String>();

            listEmploy.add("");
            listEmploy.add("0-Unemployed/Not working");
            listEmploy.add("1-Full-time employed");
            listEmploy.add("2-Part-time employed");
            listEmploy.add("3-Self-employed");
            listEmploy.add("7-Other");
            listEmploy.add("8-Don't know");
            listEmploy.add("9-Refused to respond");*/
            spnEmpStatus.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_Employment())));
            spnEmpStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnEmpStatus.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnEmpStatus.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("7")) {
                        secEmployStsOth.setVisibility(View.VISIBLE);
                        lineEmployStsOth.setVisibility(View.VISIBLE);
                    } else if (spnData.equalsIgnoreCase("8")) {
                        secEmployStsOth.setVisibility(View.GONE);
                        lineEmployStsOth.setVisibility(View.GONE);
                        txtEmployStsOth.setText("");
                    } else if (spnData.equalsIgnoreCase("9")) {
                        secEmployStsOth.setVisibility(View.GONE);
                        lineEmployStsOth.setVisibility(View.GONE);
                        txtEmployStsOth.setText("");
                    } else {
                        secEmployStsOth.setVisibility(View.GONE);
                        lineEmployStsOth.setVisibility(View.GONE);
                        txtEmployStsOth.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });

            /*rdogrpEmpStatus = (RadioGroup) findViewById(R.id.rdogrpEmpStatus);
            rdoEmpStatus1 = (RadioButton) findViewById(R.id.rdoEmpStatus1);
            rdoEmpStatus2 = (RadioButton) findViewById(R.id.rdoEmpStatus2);
            rdoEmpStatus3 = (RadioButton) findViewById(R.id.rdoEmpStatus3);
            rdoEmpStatus4 = (RadioButton) findViewById(R.id.rdoEmpStatus4);
            rdoEmpStatus5 = (RadioButton) findViewById(R.id.rdoEmpStatus5);
            rdoEmpStatus6 = (RadioButton) findViewById(R.id.rdoEmpStatus6);
            rdoEmpStatus7 = (RadioButton) findViewById(R.id.rdoEmpStatus7);*/
            /*rdogrpEmpStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpEmpStatus = new String[] {"0","1","2","3","7","8","9"};
                    for (int i = 0; i < rdogrpEmpStatus.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpEmpStatus.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpEmpStatus[i];
                    }

                    if(rbData.equalsIgnoreCase("7"))
                    {
                        secEmployStsOth.setVisibility(View.VISIBLE);
                        lineEmployStsOth.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        secEmployStsOth.setVisibility(View.GONE);
                        lineEmployStsOth.setVisibility(View.GONE);
                        txtEmployStsOth.setText("");
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });*/
            secEmployStsOth=(LinearLayout)findViewById(R.id.secEmployStsOth);
            lineEmployStsOth=(View)findViewById(R.id.lineEmployStsOth);
            VlblEmployStsOth=(TextView) findViewById(R.id.VlblEmployStsOth);
            txtEmployStsOth=(EditText) findViewById(R.id.txtEmployStsOth);
            secMainOccu=(LinearLayout)findViewById(R.id.secMainOccu);
            lineMainOccu=(View)findViewById(R.id.lineMainOccu);
            VlblMainOccu=(TextView) findViewById(R.id.VlblMainOccu);
            spnMainOccu=(Spinner) findViewById(R.id.spnMainOccu);
            spnMainOccu.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_OCCUPATION())));

            spnMainOccu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String code = spnMainOccu.getSelectedItemPosition()==0?"":spnMainOccu.getSelectedItem().toString().split("-")[0];
                    if(code.equals("97")){
                        secMainOccuOth.setVisibility(View.VISIBLE);
                    }else{
                        secMainOccuOth.setVisibility(View.GONE);
                        txtMainOccuOth.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
            secMainOccuDk=(LinearLayout)findViewById(R.id.secMainOccuDk);
            lineMainOccuDk=(View)findViewById(R.id.lineMainOccuDk);
            VlblMainOccuDk = (TextView) findViewById(R.id.VlblMainOccuDk);
            rdogrpMainOccuDk = (RadioGroup) findViewById(R.id.rdogrpMainOccuDk);
            rdoMainOccuDk1 = (RadioButton) findViewById(R.id.rdoMainOccuDk1);
            rdoMainOccuDk2 = (RadioButton) findViewById(R.id.rdoMainOccuDk2);
            rdoMainOccuDk3 = (RadioButton) findViewById(R.id.rdoMainOccuDk3);
            /*rdogrpMainOccuDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpMainOccuDk = new String[] {"7","8","9"};
                    for (int i = 0; i < rdogrpMainOccuDk.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpMainOccuDk.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpMainOccuDk[i];
                    }

                    if(rbData.equalsIgnoreCase("7"))
                    {
                        secMainOccuOth.setVisibility(View.VISIBLE);
                        lineMainOccuOth.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        secMainOccuOth.setVisibility(View.GONE);
                        lineMainOccuOth.setVisibility(View.GONE);
                        txtMainOccuOth.setText("");
                    }

                    if (rdoMainOccuDk1.isChecked()||rdoMainOccuDk2.isChecked()||rdoMainOccuDk3.isChecked()){
                        txtMainOccu.setText("");
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });*/
            secMainOccuOth=(LinearLayout)findViewById(R.id.secMainOccuOth);
            lineMainOccuOth=(View)findViewById(R.id.lineMainOccuOth);
            VlblMainOccuOth=(TextView) findViewById(R.id.VlblMainOccuOth);
            txtMainOccuOth=(EditText) findViewById(R.id.txtMainOccuOth);
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Caregiver.this, e.getMessage());
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
                Connection.MessageBox(Surv_Caregiver.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            Caregiver_DataModel objSave = new Caregiver_DataModel();
            objSave.setCaregID(txtCaregID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setRespName(txtRespName.getText().toString());
            objSave.setRespID(txtRespID.getText().toString());
            String[] d_rdogrpIsMem = new String[] {"0","1"};
            objSave.setIsMem("");
            for (int i = 0; i < rdogrpIsMem.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpIsMem.getChildAt(i);
                if (rb.isChecked()) objSave.setIsMem(d_rdogrpIsMem[i]);
            }

            objSave.setCMemID(spnCMemID.getSelectedItemPosition() == 0 ? "" : spnCMemID.getSelectedItem().toString().split("-")[0]);
            String[] d_rdogrpPriCaregiver = new String[] {"0","1","8","9"};
            objSave.setPriCaregiver("");
            for (int i = 0; i < rdogrpPriCaregiver.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpPriCaregiver.getChildAt(i);
                if (rb.isChecked()) objSave.setPriCaregiver(d_rdogrpPriCaregiver[i]);
            }

            objSave.setWhoPriCaregiver(spnWhoPriCaregiver.getSelectedItemPosition() == 0 ? "" : spnWhoPriCaregiver.getSelectedItem().toString().split("-")[0]);
            objSave.setWhoPrCaregOth(txtWhoPrCaregOth.getText().toString());
            //objSave.setPriCaregID(spnPriCaregID.getSelectedItemPosition() == 0 ? "" : spnPriCaregID.getSelectedItem().toString().split("-")[0]);
            objSave.setPriCaregID(txtPriCaregID.getText().toString());
            objSave.setRthChild(spnRthChild.getSelectedItemPosition() == 0 ? "" : spnRthChild.getSelectedItem().toString().split("-")[0]);
            objSave.setRthChildOth(txtRthChildOth.getText().toString());
            objSave.setReligGroup(spnReligGroup.getSelectedItemPosition() == 0 ? "" : spnReligGroup.getSelectedItem().toString().split("-")[0]);
            objSave.setReligGroupOth(txtReligGroupOth.getText().toString());
            objSave.setEthnicGroup(spnEthnicGroup.getSelectedItemPosition() == 0 ? "" : spnEthnicGroup.getSelectedItem().toString().split("-")[0]);
            objSave.setEthnicGroupOth(txtEthnicGroupOth.getText().toString());
            objSave.setCrgvEduY(spnCrgvEduY.getSelectedItemPosition() == 0 ? "" : spnCrgvEduY.getSelectedItem().toString().split("-")[0]);
            String[] d_rdogrpCrgvEduYDk = new String[] {"98","99"};
            objSave.setCrgvEduYDk("");
            for (int i = 0; i < rdogrpCrgvEduYDk.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpCrgvEduYDk.getChildAt(i);
                if (rb.isChecked()) objSave.setCrgvEduYDk(d_rdogrpCrgvEduYDk[i]);
            }

            objSave.setEmpStatus(spnEmpStatus.getSelectedItemPosition() == 0 ? "" : spnEmpStatus.getSelectedItem().toString().split("-")[0]);
            /*String[] d_rdogrpEmpStatus = new String[] {"0","1","2","3","7","8","9"};
            objSave.setEmpStatus("");
            for (int i = 0; i < rdogrpEmpStatus.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpEmpStatus.getChildAt(i);
                if (rb.isChecked()) objSave.setEmpStatus(d_rdogrpEmpStatus[i]);
            }*/

            objSave.setEmployStsOth(txtEmployStsOth.getText().toString());
            //objSave.setMainOccu(txtMainOccu.getText().toString());
            objSave.setMainOccu(spnMainOccu.getSelectedItemPosition() == 0 ? "" : spnMainOccu.getSelectedItem().toString().split("-")[0]);

            String[] d_rdogrpMainOccuDk = new String[] {"7","8","9"};
            objSave.setMainOccuDk("");
            for (int i = 0; i < rdogrpMainOccuDk.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpMainOccuDk.getChildAt(i);
                if (rb.isChecked()) objSave.setMainOccuDk(d_rdogrpMainOccuDk[i]);
            }

            objSave.setMainOccuOth(txtMainOccuOth.getText().toString());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);
            if(status.length()==0) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("res", "");
                setResult(Activity.RESULT_OK, returnIntent);

                Toast.makeText(Surv_Caregiver.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                Connection.MessageBox(Surv_Caregiver.this, status);
                return;
            }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Caregiver.this, e.getMessage());
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
            if(txtCaregID.getText().toString().length()==0 & secCaregID.isShown())
            {
                ValidationMsg += "\nRequired field: Caregiver Internal ID.";
                secCaregID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtMemID.getText().toString().length()==0 & secMemID.isShown())
            {
                ValidationMsg += "\nRequired field: ChildID.";
                secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
            {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtRespName.getText().toString().length()==0 & secRespName.isShown())
            {
                ValidationMsg += "\nRequired field: Record respondent’s name.";
                secRespName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtRespID.getText().toString().length()==0 & secRespID.isShown())
            {
                ValidationMsg += "\nM2. Required field: Record respondents individual ID number.";
                secRespID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoIsMem1.isChecked() & !rdoIsMem2.isChecked() & secIsMem.isShown())
            {
                ValidationMsg += "\nM2.1. Required field: Is caregiver member of this household?.";
                secIsMem.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(spnCMemID.getSelectedItemPosition()==0  & secCMemID.isShown())
            {
                ValidationMsg += "\nM2.2. Required field: Record caregivers individual ID number.";
                secCMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoPriCaregiver1.isChecked() & !rdoPriCaregiver2.isChecked() & !rdoPriCaregiver3.isChecked() & !rdoPriCaregiver4.isChecked() & secPriCaregiver.isShown())
            {
                ValidationMsg += "\nM3. Required field: Are you the primary caregiver of the child/[name]?.";
                secPriCaregiver.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(spnWhoPriCaregiver.getSelectedItemPosition()==0  & secWhoPriCaregiver.isShown())
            {
                ValidationMsg += "\nM4. Required field: Who is the primary caregiver of the child/[name]? (GIVE ONE RESPONSE, OR WRITE RESPONSE FOR 97).";
                secWhoPriCaregiver.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtWhoPrCaregOth.getText().toString().length()==0 & secWhoPrCaregOth.isShown())
            {
                ValidationMsg += "\nRequired field: Other specify.";
                secWhoPrCaregOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtPriCaregID.getText().toString().length()==0  & secPriCaregID.isShown())
            {
                ValidationMsg += "\nM5. Required field: Record primary caregivers name.";
                secPriCaregID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(spnRthChild.getSelectedItemPosition()==0  & secRthChild.isShown())
            {
                ValidationMsg += "\nM6. Required field: What is your relationship to the child/[name]? (GIVE ONE RESPONSE, OR WRITE RESPONSE FOR 88).";
                secRthChild.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtRthChildOth.getText().toString().length()==0 & secRthChildOth.isShown())
            {
                ValidationMsg += "\nRequired field: Other specify.";
                secRthChildOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(spnReligGroup.getSelectedItemPosition()==0  & secReligGroup.isShown())
            {
                ValidationMsg += "\nM7. Required field: Which religious group does the primary caregiver belong to? IF MORE THAN ONE, MARK OTHER AND SPECIFY .";
                secReligGroup.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtReligGroupOth.getText().toString().length()==0 & secReligGroupOth.isShown())
            {
                ValidationMsg += "\nRequired field: Other specify .";
                secReligGroupOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(spnEthnicGroup.getSelectedItemPosition()==0  & secEthnicGroup.isShown())
            {
                ValidationMsg += "\nM8. Required field: Which ethnic group does the primary caregiver belong to? IF MORE THAN ONE, MARK OTHER AND SPECIFY (GIVE RESPONSE ON THE BLANK).";
                secEthnicGroup.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }

            if(txtEthnicGroupOth.getText().toString().length()==0 & secEthnicGroupOth.isShown())
            {
                ValidationMsg += "\nRequired field: Other specify.";
                secEthnicGroupOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            /*if(txtCrgvEduY.getText().toString().length()==0 & secCrgvEduY.isShown())
            {
                ValidationMsg += "\nM9. Required field: How many years of education has the primary caregiver completed?\n(WRITE DOWN RESPONSE ON THE BLANK) ONLY INCLUDE THIS QUESTION IF EDUCATION IS NOT ASKED AS PART OF THE HOUSEHOLD ROSTER.";
                secCrgvEduY.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }*/
            if (spnCrgvEduY.getSelectedItemPosition() == 0 & secCrgvEduY.isShown()) {
                ValidationMsg += "\nHR8 Required field: How many years of education has completed?.";
                secCrgvEduY.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }

            /*if (secCrgvEduY.isShown() && !txtCrgvEduY.getText().toString().equalsIgnoreCase("98") &&  !txtCrgvEduY .getText().toString().equalsIgnoreCase("99")) {
                if(secCrgvEduY.isShown() & (Integer.valueOf(txtCrgvEduY.getText().toString().length()==0 ? "00" : txtCrgvEduY.getText().toString()) < 00 || Integer.valueOf(txtCrgvEduY.getText().toString().length()==0 ? "20" : txtCrgvEduY.getText().toString()) > 20))
                {
                    ValidationMsg += "\nM9. Value should be between 00 and 20(How many years of education has the primary caregiver completed?\n(WRITE DOWN RESPONSE ON THE BLANK) ONLY INCLUDE THIS QUESTION IF EDUCATION IS NOT ASKED AS PART OF THE HOUSEHOLD ROSTER).";
                    secCrgvEduY.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                }
            }*/
            /*if(!rdoCrgvEduYDk1.isChecked() & !rdoCrgvEduYDk2.isChecked() & secCrgvEduYDk.isShown())
            {
                ValidationMsg += "\nRequired field: How many years of education has the primary caregiver completed? - Dont know/Refused to respond.";
                secCrgvEduYDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }*/
            if(spnEmpStatus.getSelectedItemPosition() == 0 & secEmpStatus.isShown())
            {
                ValidationMsg += "\nM10. Required field: What is the employment status of the primary caregiver?\n(CIRCLE ONE RESPONSE, OR WRITE RESPONSE FOR 88) ONLY INCLUDE THIS QUESTION IF OCCUPATION IS NOT ASKED AS PART OF THE HOUSEHOLD ROSTER.";
                secEmpStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtEmployStsOth.getText().toString().length()==0 & secEmployStsOth.isShown())
            {
                ValidationMsg += "\nRequired field: Other specify.";
                secEmployStsOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(spnMainOccu.getSelectedItemPosition() == 0 & secMainOccu.isShown())
            {
                ValidationMsg += "\nM11. Required field: What is the main occupation of the primary caregiver?\nMain occupation being the most important income-generating activity (CIRCLE ONE RESPONSE, OR WRITE RESPONSE FOR 88).";
                secMainOccu.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            /*if (txtMainOccu.getText().toString().length()==0 & secMainOccu.isShown()) {
                if(!rdoMainOccuDk1.isChecked() & !rdoMainOccuDk2.isChecked() & !rdoMainOccuDk3.isChecked() & secMainOccuDk.isShown())
                {
                    ValidationMsg += "\nRequired field: Other/Dont know/Refused to respond.";
                    secMainOccuDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                }
            }*/
            if(txtMainOccuOth.getText().toString().length()==0 & secMainOccuOth.isShown())
            {
                ValidationMsg += "\n Other specify. Required field: What is the main occupation of the primary caregiver? Main occupation being the most important income-generating activity (GIVE RESPONSE FOR 88)  - Other specify.";
                secMainOccuOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
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
            secCaregID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secRespName.setBackgroundColor(Color.WHITE);
            secRespID.setBackgroundColor(Color.WHITE);
            secIsMem.setBackgroundColor(Color.WHITE);
            secCMemID.setBackgroundColor(Color.WHITE);
            secPriCaregiver.setBackgroundColor(Color.WHITE);
            secWhoPriCaregiver.setBackgroundColor(Color.WHITE);
            secWhoPrCaregOth.setBackgroundColor(Color.WHITE);
            secPriCaregID.setBackgroundColor(Color.WHITE);
            secRthChild.setBackgroundColor(Color.WHITE);
            secRthChildOth.setBackgroundColor(Color.WHITE);
            secReligGroup.setBackgroundColor(Color.WHITE);
            secReligGroupOth.setBackgroundColor(Color.WHITE);
            secEthnicGroup.setBackgroundColor(Color.WHITE);
            secEthnicGroupOth.setBackgroundColor(Color.WHITE);
            secCrgvEduY.setBackgroundColor(Color.WHITE);
            secCrgvEduY.setBackgroundColor(Color.WHITE);
            secCrgvEduYDk.setBackgroundColor(Color.WHITE);
            secEmpStatus.setBackgroundColor(Color.WHITE);
            secEmployStsOth.setBackgroundColor(Color.WHITE);
            secMainOccu.setBackgroundColor(Color.WHITE);
            secMainOccuDk.setBackgroundColor(Color.WHITE);
            secMainOccuOth.setBackgroundColor(Color.WHITE);
        }
        catch(Exception  e)
        {
        }
    }

    private void DataSearch(String MemID)
    {
        try
        {
            RadioButton rb;
            Caregiver_DataModel d = new Caregiver_DataModel();
            String SQL = "Select * from "+ TableName +"  Where MemID='"+ MemID +"'";
            List<Caregiver_DataModel> data = d.SelectAll(this, SQL);
            for(Caregiver_DataModel item : data){
                txtCaregID.setText(item.getCaregID());
                txtMemID.setText(item.getMemID());
                txtHHID.setText(item.getHHID());
                txtRespName.setText(item.getRespName());
                txtRespID.setText(item.getRespID());
                String[] d_rdogrpIsMem = new String[] {"0","1"};
                for (int i = 0; i < d_rdogrpIsMem.length; i++)
                {
                    if (String.valueOf(item.getIsMem()).equals(String.valueOf(d_rdogrpIsMem[i])))
                    {
                        rb = (RadioButton)rdogrpIsMem.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnCMemID.setSelection(Global.SpinnerItemPositionAnyLength(spnCMemID, String.valueOf(item.getCMemID())));
                String[] d_rdogrpPriCaregiver = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpPriCaregiver.length; i++)
                {
                    if (String.valueOf(item.getPriCaregiver()).equals(String.valueOf(d_rdogrpPriCaregiver[i])))
                    {
                        rb = (RadioButton)rdogrpPriCaregiver.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnWhoPriCaregiver.setSelection(Global.SpinnerItemPositionAnyLength(spnWhoPriCaregiver, String.valueOf(item.getWhoPriCaregiver())));
                txtWhoPrCaregOth.setText(item.getWhoPrCaregOth());
                txtPriCaregID.setText(item.getPriCaregID());
                spnRthChild.setSelection(Global.SpinnerItemPositionAnyLength(spnRthChild, String.valueOf(item.getRthChild())));
                txtRthChildOth.setText(item.getRthChildOth());
                spnReligGroup.setSelection(Global.SpinnerItemPositionAnyLength(spnReligGroup, String.valueOf(item.getReligGroup())));
                txtReligGroupOth.setText(item.getReligGroupOth());
                spnEthnicGroup.setSelection(Global.SpinnerItemPositionAnyLength(spnEthnicGroup, String.valueOf(item.getEthnicGroup())));

                txtEthnicGroupOth.setText(item.getEthnicGroupOth());

                spnCrgvEduY.setSelection(Global.SpinnerItemPositionAnyLength(spnCrgvEduY, String.valueOf(item.getCrgvEduY())));
                String[] d_rdogrpCrgvEduYDk = new String[] {"98","99"};
                for (int i = 0; i < d_rdogrpCrgvEduYDk.length; i++)
                {
                    if (String.valueOf(item.getCrgvEduYDk()).equals(String.valueOf(d_rdogrpCrgvEduYDk[i])))
                    {
                        rb = (RadioButton)rdogrpCrgvEduYDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }

                spnEmpStatus.setSelection(Global.SpinnerItemPositionAnyLength(spnEmpStatus, String.valueOf(item.getEmpStatus())));
                /*String[] d_rdogrpEmpStatus = new String[] {"0","1","2","3","7","8","9"};
                for (int i = 0; i < d_rdogrpEmpStatus.length; i++)
                {
                    if (String.valueOf(item.getEmpStatus()).equals(String.valueOf(d_rdogrpEmpStatus[i])))
                    {
                        rb = (RadioButton)rdogrpEmpStatus.getChildAt(i);
                        rb.setChecked(true);
                    }
                }*/
                txtEmployStsOth.setText(item.getEmployStsOth());
                //txtMainOccu.setText(item.getMainOccu());
                spnMainOccu.setSelection(Global.SpinnerItemPositionAnyLength(spnMainOccu, String.valueOf(item.getMainOccu())));
                String[] d_rdogrpMainOccuDk = new String[] {"7","8","9"};
                for (int i = 0; i < d_rdogrpMainOccuDk.length; i++)
                {
                    if (String.valueOf(item.getMainOccuDk()).equals(String.valueOf(d_rdogrpMainOccuDk[i])))
                    {
                        rb = (RadioButton)rdogrpMainOccuDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtMainOccuOth.setText(item.getMainOccuOth());
            }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Caregiver.this, e.getMessage());
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