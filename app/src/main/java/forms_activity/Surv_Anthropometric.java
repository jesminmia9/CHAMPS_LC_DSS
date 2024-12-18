
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
 import android.text.Editable;
 import android.text.TextWatcher;
 import android.view.KeyEvent;
 import android.os.Bundle;
 import android.view.View;
 import android.view.MotionEvent;
 import android.widget.AdapterView;
 import android.widget.Button;
 import android.widget.CheckBox;
 import android.widget.CompoundButton;
 import android.widget.DatePicker;
 import android.widget.EditText;
 import android.widget.ImageButton;
 import android.widget.LinearLayout;
 import android.widget.RadioButton;
 import android.widget.RadioGroup;
 import android.widget.SimpleAdapter;
 import android.widget.TextView;
 import android.widget.TimePicker;
 import android.graphics.Color;
 import android.view.WindowManager;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.R;

 import forms_datamodel.Anthropometric_DataModel;
 import Utility.*;
 import Common.*;

 public class Surv_Anthropometric extends AppCompatActivity {
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
    LinearLayout secCID;
    View lineCID;
    TextView VlblCID;
    EditText txtCID;
    LinearLayout seclbl1;
    View linelbl1;
    LinearLayout secRespID;
    View lineRespID;
    TextView VlblRespID;
    EditText txtRespID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secAnthMeasurementDate;
    View lineAnthMeasurementDate;
    TextView VlblAnthMeasurementDate;
    EditText dtpAnthMeasurementDate;
    LinearLayout secAnthWeightCol;
    View lineAnthWeightCol;
    TextView VlblAnthWeightCol;
    RadioGroup rdogrpAnthWeightCol;
    RadioButton rdoAnthWeightCol1;
    RadioButton rdoAnthWeightCol2;
    RadioButton rdoAnthWeightCol3;
    LinearLayout secAnthWeight;
    View lineAnthWeight;
    TextView VlblAnthWeight;
    EditText txtAnthWeight;
    LinearLayout secAnthWeightDK;
    View lineAnthWeightDK;
    TextView VlblAnthWeightDK;
    CheckBox chkAnthWeightDK;
    LinearLayout secAnthHeightCol;
    View lineAnthHeightCol;
    TextView VlblAnthHeightCol;
    RadioGroup rdogrpAnthHeightCol;
    RadioButton rdoAnthHeightCol1;
    RadioButton rdoAnthHeightCol2;
    RadioButton rdoAnthHeightCol3;
    LinearLayout secAnthHeight;
    View lineAnthHeight;
    TextView VlblAnthHeight;
    EditText txtAnthHeight;
    LinearLayout secAnthHeightDK;
    View lineAnthHeightDK;
    TextView VlblAnthHeightDK;
    CheckBox chkAnthHeightDK;
    LinearLayout secAnthMUACCol;
    View lineAnthMUACCol;
    TextView VlblAnthMUACCol;
    RadioGroup rdogrpAnthMUACCol;
    RadioButton rdoAnthMUACCol1;
    RadioButton rdoAnthMUACCol2;
    RadioButton rdoAnthMUACCol3;
    LinearLayout secAnthMUAC;
    View lineAnthMUAC;
    TextView VlblAnthMUAC;
    EditText txtAnthMUAC;
    LinearLayout secAnthMUACDK;
    View lineAnthMUACDK;
    TextView VlblAnthMUACDK;
    CheckBox chkAnthMUACDK;
    LinearLayout secAnthHeadCircumCol;
    View lineAnthHeadCircumCol;
    TextView VlblAnthHeadCircumCol;
    RadioGroup rdogrpAnthHeadCircumCol;
    RadioButton rdoAnthHeadCircumCol1;
    RadioButton rdoAnthHeadCircumCol2;
    RadioButton rdoAnthHeadCircumCol3;
    LinearLayout secAnthHeadCircum;
    View lineAnthHeadCircum;
    TextView VlblAnthHeadCircum;
    EditText txtAnthHeadCircum;
    LinearLayout secAnthHeadCircumDK;
    View lineAnthHeadCircumDK;
    TextView VlblAnthHeadCircumDK;
    CheckBox chkAnthHeadCircumDK;
    LinearLayout secAnthNote;
    View lineAnthNote;
    TextView VlblAnthNote;
    EditText txtAnthNote;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;

     int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String CID = "";
      String MEMID = "";
      String HHID = "";
 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.anthropometric);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");



//         IDbundle = getIntent().getExtras();
//         CID = IDbundle.getString("CID");
         IDbundle = getIntent().getExtras();
         MEMID = IDbundle.getString("MemID");
         HHID = IDbundle.getString("HHID");
         ((TextView)findViewById(R.id.tv_member_name)).setText(IDbundle.getString("MemName","[Name]"));


         TableName = "Anthropometric";
         MODULEID = 56;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Anthropometric.this);
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
        Connection.LocalizeLanguage(Surv_Anthropometric.this, MODULEID, LANGUAGEID);


         dtpAnthMeasurementDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnAnthMeasurementDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables
         secAnthWeight.setVisibility(View.GONE);
         lineAnthWeight.setVisibility(View.GONE);
         secAnthWeightDK.setVisibility(View.GONE);
         lineAnthWeightDK.setVisibility(View.GONE);
         secAnthWeight.setVisibility(View.GONE);
         lineAnthWeight.setVisibility(View.GONE);
         secAnthWeightDK.setVisibility(View.GONE);
         lineAnthWeightDK.setVisibility(View.GONE);
         secAnthHeight.setVisibility(View.GONE);
         lineAnthHeight.setVisibility(View.GONE);
         secAnthHeightDK.setVisibility(View.GONE);
         lineAnthHeightDK.setVisibility(View.GONE);
         secAnthHeight.setVisibility(View.GONE);
         lineAnthHeight.setVisibility(View.GONE);
         secAnthHeightDK.setVisibility(View.GONE);
         lineAnthHeightDK.setVisibility(View.GONE);
         secAnthMUAC.setVisibility(View.GONE);
         lineAnthMUAC.setVisibility(View.GONE);
         secAnthMUACDK.setVisibility(View.GONE);
         lineAnthMUACDK.setVisibility(View.GONE);
         secAnthMUAC.setVisibility(View.GONE);
         lineAnthMUAC.setVisibility(View.GONE);
         secAnthMUACDK.setVisibility(View.GONE);
         lineAnthMUACDK.setVisibility(View.GONE);
         secAnthHeadCircum.setVisibility(View.GONE);
         lineAnthHeadCircum.setVisibility(View.GONE);
         secAnthHeadCircumDK.setVisibility(View.GONE);
         lineAnthHeadCircumDK.setVisibility(View.GONE);
         secAnthHeadCircum.setVisibility(View.GONE);
         lineAnthHeadCircum.setVisibility(View.GONE);
         secAnthHeadCircumDK.setVisibility(View.GONE);
         lineAnthHeadCircumDK.setVisibility(View.GONE);


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
         Connection.MessageBox(Surv_Anthropometric.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         secCID=(LinearLayout)findViewById(R.id.secCID);
         lineCID=(View)findViewById(R.id.lineCID);
         VlblCID=(TextView) findViewById(R.id.VlblCID);
         txtCID=(EditText) findViewById(R.id.txtCID);
         txtCID.setText(Global.Get_UUID(DEVICEID));
         txtCID.setEnabled(false);
         seclbl1=(LinearLayout)findViewById(R.id.seclbl1);
         linelbl1=(View)findViewById(R.id.linelbl1);
         secRespID=(LinearLayout)findViewById(R.id.secRespID);
         lineRespID=(View)findViewById(R.id.lineRespID);
         VlblRespID=(TextView) findViewById(R.id.VlblRespID);
         txtRespID=(EditText) findViewById(R.id.txtRespID);
         secHHID=(LinearLayout)findViewById(R.id.secHHID);
         lineHHID=(View)findViewById(R.id.lineHHID);
         VlblHHID=(TextView) findViewById(R.id.VlblHHID);
         txtHHID=(EditText) findViewById(R.id.txtHHID);
         txtHHID.setText(HHID);
         secMemID=(LinearLayout)findViewById(R.id.secMemID);
         lineMemID=(View)findViewById(R.id.lineMemID);
         VlblMemID=(TextView) findViewById(R.id.VlblMemID);
         txtMemID=(EditText) findViewById(R.id.txtMemID);
         txtMemID.setText(MEMID);
         secAnthMeasurementDate=(LinearLayout)findViewById(R.id.secAnthMeasurementDate);
         lineAnthMeasurementDate=(View)findViewById(R.id.lineAnthMeasurementDate);
         VlblAnthMeasurementDate=(TextView) findViewById(R.id.VlblAnthMeasurementDate);
         dtpAnthMeasurementDate=(EditText) findViewById(R.id.dtpAnthMeasurementDate);
         secAnthWeightCol=(LinearLayout)findViewById(R.id.secAnthWeightCol);
         lineAnthWeightCol=(View)findViewById(R.id.lineAnthWeightCol);
         VlblAnthWeightCol = (TextView) findViewById(R.id.VlblAnthWeightCol);
         rdogrpAnthWeightCol = (RadioGroup) findViewById(R.id.rdogrpAnthWeightCol);
         rdoAnthWeightCol1 = (RadioButton) findViewById(R.id.rdoAnthWeightCol1);
         rdoAnthWeightCol2 = (RadioButton) findViewById(R.id.rdoAnthWeightCol2);
         rdoAnthWeightCol3 = (RadioButton) findViewById(R.id.rdoAnthWeightCol3);
         rdogrpAnthWeightCol.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpAnthWeightCol = new String[] {"0","1","8"};
             for (int i = 0; i < rdogrpAnthWeightCol.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpAnthWeightCol.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpAnthWeightCol[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    secAnthWeight.setVisibility(View.GONE);
                    lineAnthWeight.setVisibility(View.GONE);
                    txtAnthWeight.setText("");
                    secAnthWeightDK.setVisibility(View.GONE);
                    lineAnthWeightDK.setVisibility(View.GONE);
                    chkAnthWeightDK.setChecked(false);
             }
             else if(rbData.equalsIgnoreCase("8"))
             {
                    secAnthWeight.setVisibility(View.GONE);
                    lineAnthWeight.setVisibility(View.GONE);
                    txtAnthWeight.setText("");
                    secAnthWeightDK.setVisibility(View.GONE);
                    lineAnthWeightDK.setVisibility(View.GONE);
                    chkAnthWeightDK.setChecked(false);
             }
             else
             {
                    secAnthWeight.setVisibility(View.VISIBLE);
                    lineAnthWeight.setVisibility(View.VISIBLE);
                    secAnthWeightDK.setVisibility(View.VISIBLE);
                    lineAnthWeightDK.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secAnthWeight=(LinearLayout)findViewById(R.id.secAnthWeight);
         lineAnthWeight=(View)findViewById(R.id.lineAnthWeight);
         VlblAnthWeight=(TextView) findViewById(R.id.VlblAnthWeight);
         txtAnthWeight=(EditText) findViewById(R.id.txtAnthWeight);
         txtAnthWeight.addTextChangedListener(new TextWatcher() {
         public void onTextChanged(CharSequence s, int start, int before, int count) {
             if(txtAnthWeight.getText().toString().length()>0){
                 if(chkAnthWeightDK.isChecked()){
                     chkAnthWeightDK.setChecked(false);
                 }
             }
             }
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }
             public void afterTextChanged(Editable s) {
             }
         });
         secAnthWeightDK=(LinearLayout)findViewById(R.id.secAnthWeightDK);
         lineAnthWeightDK=(View)findViewById(R.id.lineAnthWeightDK);
         VlblAnthWeightDK=(TextView) findViewById(R.id.VlblAnthWeightDK);
         chkAnthWeightDK=(CheckBox) findViewById(R.id.chkAnthWeightDK);
         chkAnthWeightDK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
               if ( isChecked )
               {
                   if(txtAnthWeight.getText().toString().length()>0){
                       txtAnthWeight.setText("");
                   }
               }
            }
         });
         secAnthHeightCol=(LinearLayout)findViewById(R.id.secAnthHeightCol);
         lineAnthHeightCol=(View)findViewById(R.id.lineAnthHeightCol);
         VlblAnthHeightCol = (TextView) findViewById(R.id.VlblAnthHeightCol);
         rdogrpAnthHeightCol = (RadioGroup) findViewById(R.id.rdogrpAnthHeightCol);
         rdoAnthHeightCol1 = (RadioButton) findViewById(R.id.rdoAnthHeightCol1);
         rdoAnthHeightCol2 = (RadioButton) findViewById(R.id.rdoAnthHeightCol2);
         rdoAnthHeightCol3 = (RadioButton) findViewById(R.id.rdoAnthHeightCol3);
         rdogrpAnthHeightCol.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpAnthHeightCol = new String[] {"0","1","8"};
             for (int i = 0; i < rdogrpAnthHeightCol.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpAnthHeightCol.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpAnthHeightCol[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    secAnthHeight.setVisibility(View.GONE);
                    lineAnthHeight.setVisibility(View.GONE);
                    txtAnthHeight.setText("");
                    secAnthHeightDK.setVisibility(View.GONE);
                    lineAnthHeightDK.setVisibility(View.GONE);
                    chkAnthHeightDK.setChecked(false);
             }
             else if(rbData.equalsIgnoreCase("8"))
             {
                    secAnthHeight.setVisibility(View.GONE);
                    lineAnthHeight.setVisibility(View.GONE);
                    txtAnthHeight.setText("");
                    secAnthHeightDK.setVisibility(View.GONE);
                    lineAnthHeightDK.setVisibility(View.GONE);
                    chkAnthHeightDK.setChecked(false);
             }
             else
             {
                    secAnthHeight.setVisibility(View.VISIBLE);
                    lineAnthHeight.setVisibility(View.VISIBLE);
                    secAnthHeightDK.setVisibility(View.VISIBLE);
                    lineAnthHeightDK.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secAnthHeight=(LinearLayout)findViewById(R.id.secAnthHeight);
         lineAnthHeight=(View)findViewById(R.id.lineAnthHeight);
         VlblAnthHeight=(TextView) findViewById(R.id.VlblAnthHeight);
         txtAnthHeight=(EditText) findViewById(R.id.txtAnthHeight);
         txtAnthHeight.addTextChangedListener(new TextWatcher() {
         public void onTextChanged(CharSequence s, int start, int before, int count) {
             if(txtAnthHeight.getText().toString().length()>0){
                 if(chkAnthHeightDK.isChecked()){
                     chkAnthHeightDK.setChecked(false);
                 }
             }
             }
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }
             public void afterTextChanged(Editable s) {
             }
         });
         secAnthHeightDK=(LinearLayout)findViewById(R.id.secAnthHeightDK);
         lineAnthHeightDK=(View)findViewById(R.id.lineAnthHeightDK);
         VlblAnthHeightDK=(TextView) findViewById(R.id.VlblAnthHeightDK);
         chkAnthHeightDK=(CheckBox) findViewById(R.id.chkAnthHeightDK);
         chkAnthHeightDK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    if(txtAnthHeight.getText().toString().length()>0){
                        txtAnthHeight.setText("");
                    }
                }
            }
         });
         secAnthMUACCol=(LinearLayout)findViewById(R.id.secAnthMUACCol);
         lineAnthMUACCol=(View)findViewById(R.id.lineAnthMUACCol);
         VlblAnthMUACCol = (TextView) findViewById(R.id.VlblAnthMUACCol);
         rdogrpAnthMUACCol = (RadioGroup) findViewById(R.id.rdogrpAnthMUACCol);
         rdoAnthMUACCol1 = (RadioButton) findViewById(R.id.rdoAnthMUACCol1);
         rdoAnthMUACCol2 = (RadioButton) findViewById(R.id.rdoAnthMUACCol2);
         rdoAnthMUACCol3 = (RadioButton) findViewById(R.id.rdoAnthMUACCol3);
         rdogrpAnthMUACCol.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpAnthMUACCol = new String[] {"0","1","8"};
             for (int i = 0; i < rdogrpAnthMUACCol.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpAnthMUACCol.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpAnthMUACCol[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    secAnthMUAC.setVisibility(View.GONE);
                    lineAnthMUAC.setVisibility(View.GONE);
                    txtAnthMUAC.setText("");
                    secAnthMUACDK.setVisibility(View.GONE);
                    lineAnthMUACDK.setVisibility(View.GONE);
                    chkAnthMUACDK.setChecked(false);
             }
             else if(rbData.equalsIgnoreCase("8"))
             {
                    secAnthMUAC.setVisibility(View.GONE);
                    lineAnthMUAC.setVisibility(View.GONE);
                    txtAnthMUAC.setText("");
                    secAnthMUACDK.setVisibility(View.GONE);
                    lineAnthMUACDK.setVisibility(View.GONE);
                    chkAnthMUACDK.setChecked(false);
             }
             else
             {
                    secAnthMUAC.setVisibility(View.VISIBLE);
                    lineAnthMUAC.setVisibility(View.VISIBLE);
                    secAnthMUACDK.setVisibility(View.VISIBLE);
                    lineAnthMUACDK.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secAnthMUAC=(LinearLayout)findViewById(R.id.secAnthMUAC);
         lineAnthMUAC=(View)findViewById(R.id.lineAnthMUAC);
         VlblAnthMUAC=(TextView) findViewById(R.id.VlblAnthMUAC);
         txtAnthMUAC=(EditText) findViewById(R.id.txtAnthMUAC);
         txtAnthMUAC.addTextChangedListener(new TextWatcher() {
         public void onTextChanged(CharSequence s, int start, int before, int count) {
             if(txtAnthMUAC.getText().toString().length()>0){
                 if(chkAnthMUACDK.isChecked()){
                     chkAnthMUACDK.setChecked(false);
                 }
             }
             }
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }
             public void afterTextChanged(Editable s) {
             }
         });
         secAnthMUACDK=(LinearLayout)findViewById(R.id.secAnthMUACDK);
         lineAnthMUACDK=(View)findViewById(R.id.lineAnthMUACDK);
         VlblAnthMUACDK=(TextView) findViewById(R.id.VlblAnthMUACDK);
         chkAnthMUACDK=(CheckBox) findViewById(R.id.chkAnthMUACDK);
         chkAnthMUACDK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
               if ( isChecked )
               {
                   if(txtAnthMUAC.getText().toString().length()>0){
                       txtAnthMUAC.setText("");
                   }
               }
            }
         });
         secAnthHeadCircumCol=(LinearLayout)findViewById(R.id.secAnthHeadCircumCol);
         lineAnthHeadCircumCol=(View)findViewById(R.id.lineAnthHeadCircumCol);
         VlblAnthHeadCircumCol = (TextView) findViewById(R.id.VlblAnthHeadCircumCol);
         rdogrpAnthHeadCircumCol = (RadioGroup) findViewById(R.id.rdogrpAnthHeadCircumCol);
         rdoAnthHeadCircumCol1 = (RadioButton) findViewById(R.id.rdoAnthHeadCircumCol1);
         rdoAnthHeadCircumCol2 = (RadioButton) findViewById(R.id.rdoAnthHeadCircumCol2);
         rdoAnthHeadCircumCol3 = (RadioButton) findViewById(R.id.rdoAnthHeadCircumCol3);
         rdogrpAnthHeadCircumCol.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpAnthHeadCircumCol = new String[] {"0","1","8"};
             for (int i = 0; i < rdogrpAnthHeadCircumCol.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpAnthHeadCircumCol.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpAnthHeadCircumCol[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    secAnthHeadCircum.setVisibility(View.GONE);
                    lineAnthHeadCircum.setVisibility(View.GONE);
                    txtAnthHeadCircum.setText("");
                    secAnthHeadCircumDK.setVisibility(View.GONE);
                    lineAnthHeadCircumDK.setVisibility(View.GONE);
                    chkAnthHeadCircumDK.setChecked(false);
             }
             else if(rbData.equalsIgnoreCase("8"))
             {
                    secAnthHeadCircum.setVisibility(View.GONE);
                    lineAnthHeadCircum.setVisibility(View.GONE);
                    txtAnthHeadCircum.setText("");
                    secAnthHeadCircumDK.setVisibility(View.GONE);
                    lineAnthHeadCircumDK.setVisibility(View.GONE);
                    chkAnthHeadCircumDK.setChecked(false);
             }
             else
             {
                    secAnthHeadCircum.setVisibility(View.VISIBLE);
                    lineAnthHeadCircum.setVisibility(View.VISIBLE);
                    secAnthHeadCircumDK.setVisibility(View.VISIBLE);
                    lineAnthHeadCircumDK.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secAnthHeadCircum=(LinearLayout)findViewById(R.id.secAnthHeadCircum);
         lineAnthHeadCircum=(View)findViewById(R.id.lineAnthHeadCircum);
         VlblAnthHeadCircum=(TextView) findViewById(R.id.VlblAnthHeadCircum);
         txtAnthHeadCircum=(EditText) findViewById(R.id.txtAnthHeadCircum);
         txtAnthHeadCircum.addTextChangedListener(new TextWatcher() {
         public void onTextChanged(CharSequence s, int start, int before, int count) {
             if(txtAnthHeadCircum.getText().toString().length()>0){
                 if(chkAnthHeadCircumDK.isChecked()){
                     chkAnthHeadCircumDK.setChecked(false);
                 }
             }
             }
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }
             public void afterTextChanged(Editable s) {
             }
         });
         secAnthHeadCircumDK=(LinearLayout)findViewById(R.id.secAnthHeadCircumDK);
         lineAnthHeadCircumDK=(View)findViewById(R.id.lineAnthHeadCircumDK);
         VlblAnthHeadCircumDK=(TextView) findViewById(R.id.VlblAnthHeadCircumDK);
         chkAnthHeadCircumDK=(CheckBox) findViewById(R.id.chkAnthHeadCircumDK);
         chkAnthHeadCircumDK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
               if ( isChecked )
               {
                   if(txtAnthHeadCircum.getText().toString().length()>0){
                       txtAnthHeadCircum.setText("");
                   }
               }
            }
         });
         secAnthNote=(LinearLayout)findViewById(R.id.secAnthNote);
         lineAnthNote=(View)findViewById(R.id.lineAnthNote);
         VlblAnthNote=(TextView) findViewById(R.id.VlblAnthNote);
         txtAnthNote=(EditText) findViewById(R.id.txtAnthNote);
         txtAnthNote.addTextChangedListener(new TextWatcher() {
         public void onTextChanged(CharSequence s, int start, int before, int count) {
             }
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }
             public void afterTextChanged(Editable s) {
             }
         });
         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         txtRnd.addTextChangedListener(new TextWatcher() {
         public void onTextChanged(CharSequence s, int start, int before, int count) {
             }
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }
             public void afterTextChanged(Editable s) {
             }
         });
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Anthropometric.this, e.getMessage());
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
         	Connection.MessageBox(Surv_Anthropometric.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         Anthropometric_DataModel objSave = new Anthropometric_DataModel();
         objSave.setCID(txtCID.getText().toString());
         objSave.setRespID(txtRespID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setMemID(txtMemID.getText().toString());
         objSave.setAnthMeasurementDate(dtpAnthMeasurementDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpAnthMeasurementDate.getText().toString()) : dtpAnthMeasurementDate.getText().toString());
         String[] d_rdogrpAnthWeightCol = new String[] {"0","1","8"};
         objSave.setAnthWeightCol("");
         for (int i = 0; i < rdogrpAnthWeightCol.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpAnthWeightCol.getChildAt(i);
             if (rb.isChecked()) objSave.setAnthWeightCol(d_rdogrpAnthWeightCol[i]);
         }

         objSave.setAnthWeight(txtAnthWeight.getText().toString());
         objSave.setAnthWeightDK((chkAnthWeightDK.isChecked() ? "1" : (secAnthWeightDK.isShown() ? "2" : "")));
         String[] d_rdogrpAnthHeightCol = new String[] {"0","1","8"};
         objSave.setAnthHeightCol("");
         for (int i = 0; i < rdogrpAnthHeightCol.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpAnthHeightCol.getChildAt(i);
             if (rb.isChecked()) objSave.setAnthHeightCol(d_rdogrpAnthHeightCol[i]);
         }

         objSave.setAnthHeight(txtAnthHeight.getText().toString());
         objSave.setAnthHeightDK((chkAnthHeightDK.isChecked() ? "1" : (secAnthHeightDK.isShown() ? "2" : "")));
         String[] d_rdogrpAnthMUACCol = new String[] {"0","1","8"};
         objSave.setAnthMUACCol("");
         for (int i = 0; i < rdogrpAnthMUACCol.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpAnthMUACCol.getChildAt(i);
             if (rb.isChecked()) objSave.setAnthMUACCol(d_rdogrpAnthMUACCol[i]);
         }

         objSave.setAnthMUAC(txtAnthMUAC.getText().toString());
         objSave.setAnthMUACDK((chkAnthMUACDK.isChecked() ? "1" : (secAnthMUACDK.isShown() ? "2" : "")));
         String[] d_rdogrpAnthHeadCircumCol = new String[] {"0","1","8"};
         objSave.setAnthHeadCircumCol("");
         for (int i = 0; i < rdogrpAnthHeadCircumCol.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpAnthHeadCircumCol.getChildAt(i);
             if (rb.isChecked()) objSave.setAnthHeadCircumCol(d_rdogrpAnthHeadCircumCol[i]);
         }

         objSave.setAnthHeadCircum(txtAnthHeadCircum.getText().toString());
         objSave.setAnthHeadCircumDK((chkAnthHeadCircumDK.isChecked() ? "1" : (secAnthHeadCircumDK.isShown() ? "2" : "")));
         objSave.setAnthNote(txtAnthNote.getText().toString());
         objSave.setRnd(txtRnd.getText().toString());
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

             Toast.makeText(Surv_Anthropometric.this,"Save Successfully...", Toast.LENGTH_SHORT).show();
             finish();
         }
         else{
             Connection.MessageBox(Surv_Anthropometric.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Anthropometric.this, e.getMessage());
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
         if(txtCID.getText().toString().length()==0 & secCID.isShown())
           {
             ValidationMsg += "\nRequired field: Clild Internal ID.";
             secCID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRespID.getText().toString().length()==0 & secRespID.isShown())
           {
             ValidationMsg += "\nRequired field: Record respondents individual ID number.";
             secRespID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
           {
             ValidationMsg += "\nRequired field: Household ID.";
             secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMemID.getText().toString().length()==0 & secMemID.isShown())
           {
             ValidationMsg += "\nRequired field: Record child  individual ID number.";
             secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpAnthMeasurementDate.getText().toString());
         if(DV.length()!=0 & secAnthMeasurementDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Measurement Date.";
             secAnthMeasurementDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoAnthWeightCol1.isChecked() & !rdoAnthWeightCol2.isChecked() & !rdoAnthWeightCol3.isChecked() & secAnthWeightCol.isShown())
           {
             ValidationMsg += "\nRequired field: Did you collect the weight of the Child?.";
             secAnthWeightCol.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtAnthWeight.getText().toString().length()==0 & secAnthWeight.isShown() & !chkAnthWeightDK.isChecked() )
           {
             ValidationMsg += "\nRequired field: Weight of the Child/[name] (in KG).";
             secAnthWeight.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoAnthHeightCol1.isChecked() & !rdoAnthHeightCol2.isChecked() & !rdoAnthHeightCol3.isChecked() & secAnthHeightCol.isShown())
           {
             ValidationMsg += "\nRequired field: Did you collect the height/ length of the Child?.";
             secAnthHeightCol.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtAnthHeight.getText().toString().length()==0 & secAnthHeight.isShown()  & !chkAnthHeightDK.isChecked())
           {
             ValidationMsg += "\nRequired field: Height/ length of the Child/[name] (in CM ).";
             secAnthHeight.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoAnthMUACCol1.isChecked() & !rdoAnthMUACCol2.isChecked() & !rdoAnthMUACCol3.isChecked() & secAnthMUACCol.isShown())
           {
             ValidationMsg += "\nRequired field: Did you collect the MUAC of the Child?.";
             secAnthMUACCol.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtAnthMUAC.getText().toString().length()==0 & secAnthMUAC.isShown()& !chkAnthMUACDK.isChecked())
           {
             ValidationMsg += "\nRequired field: MUAC of the Child/[name] (in CM).";
             secAnthMUAC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoAnthHeadCircumCol1.isChecked() & !rdoAnthHeadCircumCol2.isChecked() & !rdoAnthHeadCircumCol3.isChecked() & secAnthHeadCircumCol.isShown())
           {
             ValidationMsg += "\nRequired field: Did you collect the head circumference of the Child?.";
             secAnthHeadCircumCol.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtAnthHeadCircum.getText().toString().length()==0 & secAnthHeadCircum.isShown() & !chkAnthHeadCircumDK.isChecked())
           {
             ValidationMsg += "\nRequired field: Head circumference of the Child/[name] (in CM).";
             secAnthHeadCircum.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
//         if(txtAnthNote.getText().toString().length()==0 & secAnthNote.isShown())
//           {
//             ValidationMsg += "\nRequired field: Note.";
//             secAnthNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
//         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
//           {
//             ValidationMsg += "\nRequired field: Round.";
//             secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
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
             secCID.setBackgroundColor(Color.WHITE);
             secRespID.setBackgroundColor(Color.WHITE);
             secHHID.setBackgroundColor(Color.WHITE);
             secMemID.setBackgroundColor(Color.WHITE);
             secAnthMeasurementDate.setBackgroundColor(Color.WHITE);
             secAnthWeightCol.setBackgroundColor(Color.WHITE);
             secAnthWeight.setBackgroundColor(Color.WHITE);
             secAnthHeightCol.setBackgroundColor(Color.WHITE);
             secAnthHeight.setBackgroundColor(Color.WHITE);
             secAnthMUACCol.setBackgroundColor(Color.WHITE);
             secAnthMUAC.setBackgroundColor(Color.WHITE);
             secAnthHeadCircumCol.setBackgroundColor(Color.WHITE);
             secAnthHeadCircum.setBackgroundColor(Color.WHITE);
             secAnthNote.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String MEMID)
     {
       try
        {     
           RadioButton rb;
           Anthropometric_DataModel d = new Anthropometric_DataModel();
           String SQL = "Select * from "+ TableName +"  Where MemID='"+ MEMID +"'";
           List<Anthropometric_DataModel> data = d.SelectAll(this, SQL);
           for(Anthropometric_DataModel item : data){
             txtCID.setText(item.getCID());
             txtRespID.setText(item.getRespID());
             txtHHID.setText(item.getHHID());
             txtMemID.setText(item.getMemID());
             dtpAnthMeasurementDate.setText(item.getAnthMeasurementDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getAnthMeasurementDate()));
             String[] d_rdogrpAnthWeightCol = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpAnthWeightCol.length; i++)
             {
                 if (String.valueOf(item.getAnthWeightCol()).equals(String.valueOf(d_rdogrpAnthWeightCol[i])))
                 {
                     rb = (RadioButton)rdogrpAnthWeightCol.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtAnthWeight.setText(item.getAnthWeight());
             if(String.valueOf(item.getAnthWeightDK()).equals("1"))
             {
                chkAnthWeightDK.setChecked(true);
             }
             else if(String.valueOf(item.getAnthWeightDK()).equals("2"))
             {
                chkAnthWeightDK.setChecked(false);
             }
             String[] d_rdogrpAnthHeightCol = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpAnthHeightCol.length; i++)
             {
                 if (String.valueOf(item.getAnthHeightCol()).equals(String.valueOf(d_rdogrpAnthHeightCol[i])))
                 {
                     rb = (RadioButton)rdogrpAnthHeightCol.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtAnthHeight.setText(item.getAnthHeight());
             if(String.valueOf(item.getAnthHeightDK()).equals("1"))
             {
                chkAnthHeightDK.setChecked(true);
             }
             else if(String.valueOf(item.getAnthHeightDK()).equals("2"))
             {
                chkAnthHeightDK.setChecked(false);
             }
             String[] d_rdogrpAnthMUACCol = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpAnthMUACCol.length; i++)
             {
                 if (String.valueOf(item.getAnthMUACCol()).equals(String.valueOf(d_rdogrpAnthMUACCol[i])))
                 {
                     rb = (RadioButton)rdogrpAnthMUACCol.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtAnthMUAC.setText(item.getAnthMUAC());
             if(String.valueOf(item.getAnthMUACDK()).equals("1"))
             {
                chkAnthMUACDK.setChecked(true);
             }
             else if(String.valueOf(item.getAnthMUACDK()).equals("2"))
             {
                chkAnthMUACDK.setChecked(false);
             }
             String[] d_rdogrpAnthHeadCircumCol = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpAnthHeadCircumCol.length; i++)
             {
                 if (String.valueOf(item.getAnthHeadCircumCol()).equals(String.valueOf(d_rdogrpAnthHeadCircumCol[i])))
                 {
                     rb = (RadioButton)rdogrpAnthHeadCircumCol.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtAnthHeadCircum.setText(item.getAnthHeadCircum());
             if(String.valueOf(item.getAnthHeadCircumDK()).equals("1"))
             {
                chkAnthHeadCircumDK.setChecked(true);
             }
             else if(String.valueOf(item.getAnthHeadCircumDK()).equals("2"))
             {
                chkAnthHeadCircumDK.setChecked(false);
             }
             txtAnthNote.setText(item.getAnthNote());
             txtRnd.setText(item.getRnd());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Anthropometric.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpAnthMeasurementDate);
      if (VariableID.equals("btnAnthMeasurementDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpAnthMeasurementDate);
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