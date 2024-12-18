
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
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.R;

 import forms_datamodel.PregDetail_DataModel;
 import Utility.*;
 import Common.*;

 public class PregDetail extends AppCompatActivity {
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
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secOutSl;
    View lineOutSl;
    TextView VlblOutSl;
    EditText txtOutSl;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMSlNo;
    View lineMSlNo;
    TextView VlblMSlNo;
    EditText txtMSlNo;
    LinearLayout secPregNo;
    View linePregNo;
    TextView VlblPregNo;
    RadioGroup rdogrpPregNo;
    RadioButton rdoPregNo1;
    RadioButton rdoPregNo2;
    RadioButton rdoPregNo3;
    LinearLayout secOutDate;
    View lineOutDate;
    TextView VlblOutDate;
    EditText dtpOutDate;
    LinearLayout secLMP;
    View lineLMP;
    TextView VlblLMP;
    EditText dtpLMP;
    LinearLayout secLMPDk;
    View lineLMPDk;
    TextView VlblLMPDk;
    CheckBox chkLMPDk;
    LinearLayout secResult;
    View lineResult;
    TextView VlblResult;
    RadioGroup rdogrpResult;
    RadioButton rdoResult1;
    RadioButton rdoResult2;
    RadioButton rdoResult3;
    LinearLayout secCry;
    View lineCry;
    TextView VlblCry;
    RadioGroup rdogrpCry;
    RadioButton rdoCry1;
    RadioButton rdoCry2;
    LinearLayout secDelMode;
    View lineDelMode;
    TextView VlblDelMode;
    RadioGroup rdogrpDelMode;
    RadioButton rdoDelMode1;
    RadioButton rdoDelMode2;
    RadioButton rdoDelMode3;
    RadioButton rdoDelMode4;
    LinearLayout secDelModeOth;
    View lineDelModeOth;
    TextView VlblDelModeOth;
    EditText txtDelModeOth;
    LinearLayout secName;
    View lineName;
    TextView VlblName;
    EditText txtName;
    LinearLayout secSex;
    View lineSex;
    TextView VlblSex;
    RadioGroup rdogrpSex;
    RadioButton rdoSex1;
    RadioButton rdoSex2;
    RadioButton rdoSex3;
//    RadioButton rdoSex4;
    LinearLayout secAlive;
    View lineAlive;
    TextView VlblAlive;
    RadioGroup rdogrpAlive;
    RadioButton rdoAlive1;
    RadioButton rdoAlive2;
    LinearLayout secAgeY;
    View lineAgeY;
    TextView VlblAgeY;
    EditText txtAgeY;
    LinearLayout secDAgeU;
    View lineDAgeU;
    TextView VlblDAgeU;
    RadioGroup rdogrpDAgeU;
    RadioButton rdoDAgeU1;
    RadioButton rdoDAgeU2;
    RadioButton rdoDAgeU3;
    LinearLayout secDAge;
    View lineDAge;
    TextView VlblDAge;
    EditText txtDAge;
    LinearLayout secDAgeD;
    View lineDAgeD;
    TextView VlblDAgeD;
    EditText txtDAgeD;
    LinearLayout secOthPreg;
    View lineOthPreg;
    TextView VlblOthPreg;
    RadioGroup rdogrpOthPreg;
    RadioButton rdoOthPreg1;
    RadioButton rdoOthPreg2;

     int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String MEMID = "";
     String OUTSL = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.pregdetail);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         MEMID = IDbundle.getString("MemID");
         OUTSL = IDbundle.getString("OutSl");

         TableName = "PregDetail";
         MODULEID = 14;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(PregDetail.this);
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
        Connection.LocalizeLanguage(PregDetail.this, MODULEID, LANGUAGEID);


         dtpOutDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnOutDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpLMP.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnLMP"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables
         secCry.setVisibility(View.GONE);
         lineCry.setVisibility(View.GONE);
         secCry.setVisibility(View.GONE);
         lineCry.setVisibility(View.GONE);
         secDelMode.setVisibility(View.GONE);
         lineDelMode.setVisibility(View.GONE);
         secDelModeOth.setVisibility(View.GONE);
         lineDelModeOth.setVisibility(View.GONE);
         secName.setVisibility(View.GONE);
         lineName.setVisibility(View.GONE);
         secSex.setVisibility(View.GONE);
         lineSex.setVisibility(View.GONE);
         secAlive.setVisibility(View.GONE);
         lineAlive.setVisibility(View.GONE);
         secAgeY.setVisibility(View.GONE);
         lineAgeY.setVisibility(View.GONE);
         secDAgeU.setVisibility(View.GONE);
         lineDAgeU.setVisibility(View.GONE);
         secDAge.setVisibility(View.GONE);
         lineDAge.setVisibility(View.GONE);
         secDAgeD.setVisibility(View.GONE);
         lineDAgeD.setVisibility(View.GONE);
         secOthPreg.setVisibility(View.GONE);
         lineOthPreg.setVisibility(View.GONE);
         secDelModeOth.setVisibility(View.GONE);
         lineDelModeOth.setVisibility(View.GONE);
         secDelModeOth.setVisibility(View.GONE);
         lineDelModeOth.setVisibility(View.GONE);
         secDelModeOth.setVisibility(View.GONE);
         lineDelModeOth.setVisibility(View.GONE);


        DataSearch(MEMID,OUTSL);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(PregDetail.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         secMemID=(LinearLayout)findViewById(R.id.secMemID);
         lineMemID=(View)findViewById(R.id.lineMemID);
         VlblMemID=(TextView) findViewById(R.id.VlblMemID);
         txtMemID=(EditText) findViewById(R.id.txtMemID);
         txtMemID.setText(MEMID);
         txtMemID.setEnabled(false);
         secOutSl=(LinearLayout)findViewById(R.id.secOutSl);
         lineOutSl=(View)findViewById(R.id.lineOutSl);
         VlblOutSl=(TextView) findViewById(R.id.VlblOutSl);
         txtOutSl=(EditText) findViewById(R.id.txtOutSl);
         txtOutSl.setText(OUTSL);
         txtOutSl.setEnabled(false);
         secHHID=(LinearLayout)findViewById(R.id.secHHID);
         lineHHID=(View)findViewById(R.id.lineHHID);
         VlblHHID=(TextView) findViewById(R.id.VlblHHID);
         txtHHID=(EditText) findViewById(R.id.txtHHID);
         secMSlNo=(LinearLayout)findViewById(R.id.secMSlNo);
         lineMSlNo=(View)findViewById(R.id.lineMSlNo);
         VlblMSlNo=(TextView) findViewById(R.id.VlblMSlNo);
         txtMSlNo=(EditText) findViewById(R.id.txtMSlNo);
         secPregNo=(LinearLayout)findViewById(R.id.secPregNo);
         linePregNo=(View)findViewById(R.id.linePregNo);
         VlblPregNo = (TextView) findViewById(R.id.VlblPregNo);
         rdogrpPregNo = (RadioGroup) findViewById(R.id.rdogrpPregNo);
         rdoPregNo1 = (RadioButton) findViewById(R.id.rdoPregNo1);
         rdoPregNo2 = (RadioButton) findViewById(R.id.rdoPregNo2);
         rdoPregNo3 = (RadioButton) findViewById(R.id.rdoPregNo3);
         secOutDate=(LinearLayout)findViewById(R.id.secOutDate);
         lineOutDate=(View)findViewById(R.id.lineOutDate);
         VlblOutDate=(TextView) findViewById(R.id.VlblOutDate);
         dtpOutDate=(EditText) findViewById(R.id.dtpOutDate);
         secLMP=(LinearLayout)findViewById(R.id.secLMP);
         lineLMP=(View)findViewById(R.id.lineLMP);
         VlblLMP=(TextView) findViewById(R.id.VlblLMP);
         dtpLMP=(EditText) findViewById(R.id.dtpLMP);
         secLMPDk=(LinearLayout)findViewById(R.id.secLMPDk);
         lineLMPDk=(View)findViewById(R.id.lineLMPDk);
         VlblLMPDk=(TextView) findViewById(R.id.VlblLMPDk);
         chkLMPDk=(CheckBox) findViewById(R.id.chkLMPDk);
         secResult=(LinearLayout)findViewById(R.id.secResult);
         lineResult=(View)findViewById(R.id.lineResult);
         VlblResult = (TextView) findViewById(R.id.VlblResult);
         rdogrpResult = (RadioGroup) findViewById(R.id.rdogrpResult);
         rdoResult1 = (RadioButton) findViewById(R.id.rdoResult1);
         rdoResult2 = (RadioButton) findViewById(R.id.rdoResult2);
         rdoResult3 = (RadioButton) findViewById(R.id.rdoResult3);
         rdogrpResult.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpResult = new String[] {"1","2","3"};
             for (int i = 0; i < rdogrpResult.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpResult.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpResult[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                    secCry.setVisibility(View.GONE);
                    lineCry.setVisibility(View.GONE);
                    rdogrpCry.clearCheck();
             }
             else if(rbData.equalsIgnoreCase("3"))
             {
                    secCry.setVisibility(View.GONE);
                    lineCry.setVisibility(View.GONE);
                    rdogrpCry.clearCheck();
                    secDelMode.setVisibility(View.GONE);
                    lineDelMode.setVisibility(View.GONE);
                    rdogrpDelMode.clearCheck();
                    secDelModeOth.setVisibility(View.GONE);
                    lineDelModeOth.setVisibility(View.GONE);
                    txtDelModeOth.setText("");
                    secName.setVisibility(View.GONE);
                    lineName.setVisibility(View.GONE);
                    txtName.setText("");
                    secSex.setVisibility(View.GONE);
                    lineSex.setVisibility(View.GONE);
                    rdogrpSex.clearCheck();
                    secAlive.setVisibility(View.GONE);
                    lineAlive.setVisibility(View.GONE);
                    rdogrpAlive.clearCheck();
                    secAgeY.setVisibility(View.GONE);
                    lineAgeY.setVisibility(View.GONE);
                    txtAgeY.setText("");
                    secDAgeU.setVisibility(View.GONE);
                    lineDAgeU.setVisibility(View.GONE);
                    rdogrpDAgeU.clearCheck();
                    secDAge.setVisibility(View.GONE);
                    lineDAge.setVisibility(View.GONE);
                    txtDAge.setText("");
                    secDAgeD.setVisibility(View.GONE);
                    lineDAgeD.setVisibility(View.GONE);
                    txtDAgeD.setText("");
                    secOthPreg.setVisibility(View.GONE);
                    lineOthPreg.setVisibility(View.GONE);
                    rdogrpOthPreg.clearCheck();
             }
             else
             {
                    secCry.setVisibility(View.VISIBLE);
                    lineCry.setVisibility(View.VISIBLE);
                    secDelMode.setVisibility(View.VISIBLE);
                    lineDelMode.setVisibility(View.VISIBLE);
                    secName.setVisibility(View.VISIBLE);
                    lineName.setVisibility(View.VISIBLE);
                    secSex.setVisibility(View.VISIBLE);
                    lineSex.setVisibility(View.VISIBLE);
                    secAlive.setVisibility(View.VISIBLE);
                    lineAlive.setVisibility(View.VISIBLE);
                    secAgeY.setVisibility(View.VISIBLE);
                    lineAgeY.setVisibility(View.VISIBLE);
                    secDAgeU.setVisibility(View.VISIBLE);
                    lineDAgeU.setVisibility(View.VISIBLE);
                    secDAge.setVisibility(View.VISIBLE);
                    lineDAge.setVisibility(View.VISIBLE);
                    secDAgeD.setVisibility(View.VISIBLE);
                    lineDAgeD.setVisibility(View.VISIBLE);
                    secOthPreg.setVisibility(View.VISIBLE);
                    lineOthPreg.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secCry=(LinearLayout)findViewById(R.id.secCry);
         lineCry=(View)findViewById(R.id.lineCry);
         VlblCry = (TextView) findViewById(R.id.VlblCry);
         rdogrpCry = (RadioGroup) findViewById(R.id.rdogrpCry);
         rdoCry1 = (RadioButton) findViewById(R.id.rdoCry1);
         rdoCry2 = (RadioButton) findViewById(R.id.rdoCry2);
         secDelMode=(LinearLayout)findViewById(R.id.secDelMode);
         lineDelMode=(View)findViewById(R.id.lineDelMode);
         VlblDelMode = (TextView) findViewById(R.id.VlblDelMode);
         rdogrpDelMode = (RadioGroup) findViewById(R.id.rdogrpDelMode);
         rdoDelMode1 = (RadioButton) findViewById(R.id.rdoDelMode1);
         rdoDelMode2 = (RadioButton) findViewById(R.id.rdoDelMode2);
         rdoDelMode3 = (RadioButton) findViewById(R.id.rdoDelMode3);
         rdoDelMode4 = (RadioButton) findViewById(R.id.rdoDelMode4);
         rdogrpDelMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpDelMode = new String[] {"1","2","3","9"};
             for (int i = 0; i < rdogrpDelMode.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpDelMode.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpDelMode[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                    secDelModeOth.setVisibility(View.GONE);
                    lineDelModeOth.setVisibility(View.GONE);
                    txtDelModeOth.setText("");
             }
             else if(rbData.equalsIgnoreCase("2"))
             {
                    secDelModeOth.setVisibility(View.GONE);
                    lineDelModeOth.setVisibility(View.GONE);
                    txtDelModeOth.setText("");
             }
             else if(rbData.equalsIgnoreCase("3"))
             {
                    secDelModeOth.setVisibility(View.GONE);
                    lineDelModeOth.setVisibility(View.GONE);
                    txtDelModeOth.setText("");
             }
             else
             {
                    secDelModeOth.setVisibility(View.VISIBLE);
                    lineDelModeOth.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secDelModeOth=(LinearLayout)findViewById(R.id.secDelModeOth);
         lineDelModeOth=(View)findViewById(R.id.lineDelModeOth);
         VlblDelModeOth=(TextView) findViewById(R.id.VlblDelModeOth);
         txtDelModeOth=(EditText) findViewById(R.id.txtDelModeOth);
         secName=(LinearLayout)findViewById(R.id.secName);
         lineName=(View)findViewById(R.id.lineName);
         VlblName=(TextView) findViewById(R.id.VlblName);
         txtName=(EditText) findViewById(R.id.txtName);
         secSex=(LinearLayout)findViewById(R.id.secSex);
         lineSex=(View)findViewById(R.id.lineSex);
         VlblSex = (TextView) findViewById(R.id.VlblSex);
         rdogrpSex = (RadioGroup) findViewById(R.id.rdogrpSex);
         rdoSex1 = (RadioButton) findViewById(R.id.rdoSex1);
         rdoSex2 = (RadioButton) findViewById(R.id.rdoSex2);
         rdoSex3 = (RadioButton) findViewById(R.id.rdoSex3);
//         rdoSex4 = (RadioButton) findViewById(R.id.rdoSex4);
         secAlive=(LinearLayout)findViewById(R.id.secAlive);
         lineAlive=(View)findViewById(R.id.lineAlive);
         VlblAlive = (TextView) findViewById(R.id.VlblAlive);
         rdogrpAlive = (RadioGroup) findViewById(R.id.rdogrpAlive);
         rdoAlive1 = (RadioButton) findViewById(R.id.rdoAlive1);
         rdoAlive2 = (RadioButton) findViewById(R.id.rdoAlive2);
         secAgeY=(LinearLayout)findViewById(R.id.secAgeY);
         lineAgeY=(View)findViewById(R.id.lineAgeY);
         VlblAgeY=(TextView) findViewById(R.id.VlblAgeY);
         txtAgeY=(EditText) findViewById(R.id.txtAgeY);
         secDAgeU=(LinearLayout)findViewById(R.id.secDAgeU);
         lineDAgeU=(View)findViewById(R.id.lineDAgeU);
         VlblDAgeU = (TextView) findViewById(R.id.VlblDAgeU);
         rdogrpDAgeU = (RadioGroup) findViewById(R.id.rdogrpDAgeU);
         rdoDAgeU1 = (RadioButton) findViewById(R.id.rdoDAgeU1);
         rdoDAgeU2 = (RadioButton) findViewById(R.id.rdoDAgeU2);
         rdoDAgeU3 = (RadioButton) findViewById(R.id.rdoDAgeU3);
         secDAge=(LinearLayout)findViewById(R.id.secDAge);
         lineDAge=(View)findViewById(R.id.lineDAge);
         VlblDAge=(TextView) findViewById(R.id.VlblDAge);
         txtDAge=(EditText) findViewById(R.id.txtDAge);
         secDAgeD=(LinearLayout)findViewById(R.id.secDAgeD);
         lineDAgeD=(View)findViewById(R.id.lineDAgeD);
         VlblDAgeD=(TextView) findViewById(R.id.VlblDAgeD);
         txtDAgeD=(EditText) findViewById(R.id.txtDAgeD);
         secOthPreg=(LinearLayout)findViewById(R.id.secOthPreg);
         lineOthPreg=(View)findViewById(R.id.lineOthPreg);
         VlblOthPreg = (TextView) findViewById(R.id.VlblOthPreg);
         rdogrpOthPreg = (RadioGroup) findViewById(R.id.rdogrpOthPreg);
         rdoOthPreg1 = (RadioButton) findViewById(R.id.rdoOthPreg1);
         rdoOthPreg2 = (RadioButton) findViewById(R.id.rdoOthPreg2);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(PregDetail.this, e.getMessage());
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
         	Connection.MessageBox(PregDetail.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         PregDetail_DataModel objSave = new PregDetail_DataModel();
         objSave.setMemID(txtMemID.getText().toString());
         objSave.setOutSl(txtOutSl.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setMSlNo(txtMSlNo.getText().toString());
         String[] d_rdogrpPregNo = new String[] {"1","2","8"};
         objSave.setPregNo("");
         for (int i = 0; i < rdogrpPregNo.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPregNo.getChildAt(i);
             if (rb.isChecked()) objSave.setPregNo(d_rdogrpPregNo[i]);
         }

         objSave.setOutDate(dtpOutDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpOutDate.getText().toString()) : dtpOutDate.getText().toString());
         objSave.setLMP(dtpLMP.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpLMP.getText().toString()) : dtpLMP.getText().toString());
         objSave.setLMPDk((chkLMPDk.isChecked() ? "1" : (secLMPDk.isShown() ? "2" : "")));
         String[] d_rdogrpResult = new String[] {"1","2","3"};
         objSave.setResult("");
         for (int i = 0; i < rdogrpResult.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpResult.getChildAt(i);
             if (rb.isChecked()) objSave.setResult(d_rdogrpResult[i]);
         }

         String[] d_rdogrpCry = new String[] {"0","1"};
         objSave.setCry("");
         for (int i = 0; i < rdogrpCry.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCry.getChildAt(i);
             if (rb.isChecked()) objSave.setCry(d_rdogrpCry[i]);
         }

         String[] d_rdogrpDelMode = new String[] {"1","2","3","9"};
         objSave.setDelMode("");
         for (int i = 0; i < rdogrpDelMode.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDelMode.getChildAt(i);
             if (rb.isChecked()) objSave.setDelMode(d_rdogrpDelMode[i]);
         }

         objSave.setDelModeOth(txtDelModeOth.getText().toString());
         objSave.setName(txtName.getText().toString());
         String[] d_rdogrpSex = new String[] {"1","2","3"};
         objSave.setSex("");
         for (int i = 0; i < rdogrpSex.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSex.getChildAt(i);
             if (rb.isChecked()) objSave.setSex(d_rdogrpSex[i]);
         }

         String[] d_rdogrpAlive = new String[] {"0","1"};
         objSave.setAlive("");
         for (int i = 0; i < rdogrpAlive.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpAlive.getChildAt(i);
             if (rb.isChecked()) objSave.setAlive(d_rdogrpAlive[i]);
         }

         objSave.setAgeY(txtAgeY.getText().toString());
         String[] d_rdogrpDAgeU = new String[] {"1","2","3"};
         objSave.setDAgeU("");
         for (int i = 0; i < rdogrpDAgeU.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDAgeU.getChildAt(i);
             if (rb.isChecked()) objSave.setDAgeU(d_rdogrpDAgeU[i]);
         }

         objSave.setDAge(txtDAge.getText().toString());
         objSave.setDAgeD(txtDAgeD.getText().toString());
         String[] d_rdogrpOthPreg = new String[] {"0","1"};
         objSave.setOthPreg("");
         for (int i = 0; i < rdogrpOthPreg.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpOthPreg.getChildAt(i);
             if (rb.isChecked()) objSave.setOthPreg(d_rdogrpOthPreg[i]);
         }

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

             Connection.MessageBox(PregDetail.this, "Saved Successfully");
         }
         else{
             Connection.MessageBox(PregDetail.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(PregDetail.this, e.getMessage());
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
         if(txtMemID.getText().toString().length()==0 & secMemID.isShown())
           {
             ValidationMsg += "\nRequired field: Mother Permanet ID.";
             secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtOutSl.getText().toString().length()==0 & secOutSl.isShown())
           {
             ValidationMsg += "\nRequired field: Outcome Serial no.";
             secOutSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
           {
             ValidationMsg += "\nRequired field: Household ID.";
             secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMSlNo.getText().toString().length()==0 & secMSlNo.isShown())
           {
             ValidationMsg += "\nRequired field: Mothers serial no.";
             secMSlNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPregNo1.isChecked() & !rdoPregNo2.isChecked() & !rdoPregNo3.isChecked() & secPregNo.isShown())
           {
             ValidationMsg += "\nB. Required field: Single/multiple Pregnancy.";
             secPregNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpOutDate.getText().toString());
         if(DV.length()!=0 & secOutDate.isShown())
           {
             ValidationMsg += "\nC. Required field/Not a valid date format: On which date did this pregnancy end in a particular month and year?.";
             secOutDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpLMP.getText().toString());
         if(DV.length()!=0 & secLMP.isShown())
           {
             ValidationMsg += "\nD. Required field/Not a valid date format: What was the date of your last menstrual period of your last pregnancy end (count from the interview date)?.";
             secLMP.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoResult1.isChecked() & !rdoResult2.isChecked() & !rdoResult3.isChecked() & secResult.isShown())
           {
             ValidationMsg += "\nE. Required field: Status of pregnancy Outcome.";
             secResult.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCry1.isChecked() & !rdoCry2.isChecked() & secCry.isShown())
           {
             ValidationMsg += "\nF. Required field: Cry/move/breathe after birth.";
             secCry.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoDelMode1.isChecked() & !rdoDelMode2.isChecked() & !rdoDelMode3.isChecked() & !rdoDelMode4.isChecked() & secDelMode.isShown())
           {
             ValidationMsg += "\nM. Required field: Mode of delivery.";
             secDelMode.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDelModeOth.getText().toString().length()==0 & secDelModeOth.isShown())
           {
             ValidationMsg += "\nRequired field: Mode of delivery - Other.";
             secDelModeOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtName.getText().toString().length()==0 & secName.isShown())
           {
             ValidationMsg += "\nG. Required field: What name was given to the child? [If no name was given, write XX.].";
             secName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSex1.isChecked() & !rdoSex2.isChecked() & !rdoSex3.isChecked() & secSex.isShown())
           {
             ValidationMsg += "\nH. Required field: Sex (Was the child a girl or a boy?).";
             secSex.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoAlive1.isChecked() & !rdoAlive2.isChecked() & secAlive.isShown())
           {
             ValidationMsg += "\nI. Required field: Status of the child (alive or dead).";
             secAlive.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtAgeY.getText().toString().length()==0 & secAgeY.isShown())
           {
             ValidationMsg += "\nJ. Required field: If alive, how old was (names) at his/her last birthday? [Write age in complete years. Write 00 if less than one year old.].";
             secAgeY.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secAgeY.isShown() & (Integer.valueOf(txtAgeY.getText().toString().length()==0 ? "00" : txtAgeY.getText().toString()) < 00 || Integer.valueOf(txtAgeY.getText().toString().length()==0 ? "99" : txtAgeY.getText().toString()) > 99))
           {
             ValidationMsg += "\nJ. Value should be between 00 and 99(If alive, how old was (names) at his/her last birthday? [Write age in complete years. Write 00 if less than one year old.]).";
             secAgeY.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoDAgeU1.isChecked() & !rdoDAgeU2.isChecked() & !rdoDAgeU3.isChecked() & secDAgeU.isShown())
           {
             ValidationMsg += "\nK. Required field: Death Age Unit.";
             secDAgeU.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDAge.getText().toString().length()==0 & secDAge.isShown())
           {
             ValidationMsg += "\nRequired field: If dead, how old was when he/she died?.";
             secDAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secDAge.isShown() & (Integer.valueOf(txtDAge.getText().toString().length()==0 ? "00" : txtDAge.getText().toString()) < 00 || Integer.valueOf(txtDAge.getText().toString().length()==0 ? "99" : txtDAge.getText().toString()) > 99))
           {
             ValidationMsg += "\nValue should be between 00 and 99(If dead, how old was when he/she died?).";
             secDAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDAgeD.getText().toString().length()==0 & secDAgeD.isShown())
           {
             ValidationMsg += "\nRequired field: Days.";
             secDAgeD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secDAgeD.isShown() & (Integer.valueOf(txtDAgeD.getText().toString().length()==0 ? "00" : txtDAgeD.getText().toString()) < 00 || Integer.valueOf(txtDAgeD.getText().toString().length()==0 ? "29" : txtDAgeD.getText().toString()) > 29))
           {
             ValidationMsg += "\nValue should be between 00 and 29(Days).";
             secDAgeD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoOthPreg1.isChecked() & !rdoOthPreg2.isChecked() & secOthPreg.isShown())
           {
             ValidationMsg += "\nL. Required field: Were there any pregnancy outcome between this pregnancy and previous pregnancy?.";
             secOthPreg.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secMemID.setBackgroundColor(Color.WHITE);
             secOutSl.setBackgroundColor(Color.WHITE);
             secHHID.setBackgroundColor(Color.WHITE);
             secMSlNo.setBackgroundColor(Color.WHITE);
             secPregNo.setBackgroundColor(Color.WHITE);
             secOutDate.setBackgroundColor(Color.WHITE);
             secLMP.setBackgroundColor(Color.WHITE);
             secResult.setBackgroundColor(Color.WHITE);
             secCry.setBackgroundColor(Color.WHITE);
             secDelMode.setBackgroundColor(Color.WHITE);
             secDelModeOth.setBackgroundColor(Color.WHITE);
             secName.setBackgroundColor(Color.WHITE);
             secSex.setBackgroundColor(Color.WHITE);
             secAlive.setBackgroundColor(Color.WHITE);
             secAgeY.setBackgroundColor(Color.WHITE);
             secAgeY.setBackgroundColor(Color.WHITE);
             secDAgeU.setBackgroundColor(Color.WHITE);
             secDAge.setBackgroundColor(Color.WHITE);
             secDAge.setBackgroundColor(Color.WHITE);
             secDAgeD.setBackgroundColor(Color.WHITE);
             secDAgeD.setBackgroundColor(Color.WHITE);
             secOthPreg.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String MemID, String OutSl)
     {
       try
        {     
           RadioButton rb;
           PregDetail_DataModel d = new PregDetail_DataModel();
           String SQL = "Select * from "+ TableName +"  Where MemID='"+ MemID +"' and OutSl='"+ OutSl +"'";
           List<PregDetail_DataModel> data = d.SelectAll(this, SQL);
           for(PregDetail_DataModel item : data){
             txtMemID.setText(item.getMemID());
             txtOutSl.setText(item.getOutSl());
             txtHHID.setText(item.getHHID());
             txtMSlNo.setText(item.getMSlNo());
             String[] d_rdogrpPregNo = new String[] {"1","2","8"};
             for (int i = 0; i < d_rdogrpPregNo.length; i++)
             {
                 if (String.valueOf(item.getPregNo()).equals(String.valueOf(d_rdogrpPregNo[i])))
                 {
                     rb = (RadioButton)rdogrpPregNo.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             dtpOutDate.setText(item.getOutDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getOutDate()));
             dtpLMP.setText(item.getLMP().toString().length()==0 ? "" : Global.DateConvertDMY(item.getLMP()));
             if(String.valueOf(item.getLMPDk()).equals("1"))
             {
                chkLMPDk.setChecked(true);
             }
             else if(String.valueOf(item.getLMPDk()).equals("2"))
             {
                chkLMPDk.setChecked(false);
             }
             String[] d_rdogrpResult = new String[] {"1","2","3"};
             for (int i = 0; i < d_rdogrpResult.length; i++)
             {
                 if (String.valueOf(item.getResult()).equals(String.valueOf(d_rdogrpResult[i])))
                 {
                     rb = (RadioButton)rdogrpResult.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpCry = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCry.length; i++)
             {
                 if (String.valueOf(item.getCry()).equals(String.valueOf(d_rdogrpCry[i])))
                 {
                     rb = (RadioButton)rdogrpCry.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpDelMode = new String[] {"1","2","3","9"};
             for (int i = 0; i < d_rdogrpDelMode.length; i++)
             {
                 if (String.valueOf(item.getDelMode()).equals(String.valueOf(d_rdogrpDelMode[i])))
                 {
                     rb = (RadioButton)rdogrpDelMode.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtDelModeOth.setText(item.getDelModeOth());
             txtName.setText(item.getName());
             String[] d_rdogrpSex = new String[] {"1","2","3"};
             for (int i = 0; i < d_rdogrpSex.length; i++)
             {
                 if (String.valueOf(item.getSex()).equals(String.valueOf(d_rdogrpSex[i])))
                 {
                     rb = (RadioButton)rdogrpSex.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpAlive = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpAlive.length; i++)
             {
                 if (String.valueOf(item.getAlive()).equals(String.valueOf(d_rdogrpAlive[i])))
                 {
                     rb = (RadioButton)rdogrpAlive.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtAgeY.setText(String.valueOf(item.getAgeY()));
             String[] d_rdogrpDAgeU = new String[] {"1","2","3"};
             for (int i = 0; i < d_rdogrpDAgeU.length; i++)
             {
                 if (String.valueOf(item.getDAgeU()).equals(String.valueOf(d_rdogrpDAgeU[i])))
                 {
                     rb = (RadioButton)rdogrpDAgeU.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtDAge.setText(String.valueOf(item.getDAge()));
             txtDAgeD.setText(String.valueOf(item.getDAgeD()));
             String[] d_rdogrpOthPreg = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpOthPreg.length; i++)
             {
                 if (String.valueOf(item.getOthPreg()).equals(String.valueOf(d_rdogrpOthPreg[i])))
                 {
                     rb = (RadioButton)rdogrpOthPreg.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(PregDetail.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpOutDate);
      if (VariableID.equals("btnOutDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpOutDate);
      }
      else if (VariableID.equals("btnLMP"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpLMP);
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