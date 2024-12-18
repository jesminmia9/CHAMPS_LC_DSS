
 package forms_activity;


 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
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
 import android.util.Log;
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
 import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

 import forms_datamodel.tmpDeath_DataModel;
 import Utility.*;
 import Common.*;

 public class Surv_Death extends AppCompatActivity {
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
    LinearLayout secDeathID;
    View lineDeathID;
    TextView VlblDeathID;
    EditText txtDeathID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secDthDate;
    View lineDthDate;
    TextView VlblDthDate;
    EditText dtpDthDate;
    LinearLayout secDthDateType;
    View lineDthDateType;
    TextView VlblDthDateType;
    RadioGroup rdogrpDthDateType;
    RadioButton rdoDthDateType1;
    RadioButton rdoDthDateType2;
    RadioButton rdoDthDateType3;
    LinearLayout secDthTime;
    View lineDthTime;
    TextView VlblDthTime;
    EditText txtDthTime;
    LinearLayout secDthTimeType;
    View lineDthTimeType;
    TextView VlblDthTimeType;
    RadioGroup rdogrpDthTimeType;
    RadioButton rdoDthTimeType1;
    RadioButton rdoDthTimeType2;
    RadioButton rdoDthTimeType3;
    LinearLayout secDthPlace;
    View lineDthPlace;
    TextView VlblDthPlace;
    Spinner spnDthPlace;
    LinearLayout secDthPlaceOth;
    View lineDthPlaceOth;
    TextView VlblDthPlaceOth;
    AutoCompleteTextView txtDthPlaceOth;
     LinearLayout secDthPlaceFacility;
     View lineDthPlaceFacility;
     TextView VlblDthPlaceFacility;
     EditText txtDthPlaceFacility;

     LinearLayout secDthAdrsState;
     View lineDthAdrsState;
     TextView VlblDthAdrsState;
     RadioGroup rdogrpDthAdrsState;
     RadioButton rdoDthAdrsState1;
     RadioButton rdoDthAdrsState2;
     LinearLayout seclbl01;
     View linelbl01;
     LinearLayout secDthAdrsAL1;
     View lineDthAdrsAL1;
     TextView VlblDthAdrsAL1;
     AutoCompleteTextView txtDthAdrsAL1;
     LinearLayout secDthAdrsAL2;
     View lineDthAdrsAL2;
     TextView VlblDthAdrsAL2;
     AutoCompleteTextView txtDthAdrsAL2;
     LinearLayout secDthAdrsAL3;
     View lineDthAdrsAL3;
     TextView VlblDthAdrsAL3;
     AutoCompleteTextView txtDthAdrsAL3;
     LinearLayout secDthAdrsAL4;
     View lineDthAdrsAL4;
     TextView VlblDthAdrsAL4;
     AutoCompleteTextView txtDthAdrsAL4;
     LinearLayout secDthAdrsAL5;
     View lineDthAdrsAL5;
     TextView VlblDthAdrsAL5;
     AutoCompleteTextView txtDthAdrsAL5;
     LinearLayout secDthSick;
     View lineDthSick;
     TextView VlblDthSick;
     RadioGroup rdogrpDthSick;
     RadioButton rdoDthSick1;
     RadioButton rdoDthSick2;
     LinearLayout secDthCertificate;
     View lineDthCertificate;
     TextView VlblDthCertificate;
     RadioGroup rdogrpDthCertificate;
     RadioButton rdoDthCertificate1;
     RadioButton rdoDthCertificate2;
     LinearLayout seclbl02;
     View linelbl02;
     LinearLayout secchkDthCauseA;
     View linechkDthCauseA;
     TextView VlblchkDthCauseA;
     CheckBox chkchkDthCauseA;
     LinearLayout secchkDthCauseB;
     View linechkDthCauseB;
     TextView VlblchkDthCauseB;
     CheckBox chkchkDthCauseB;
     LinearLayout secchkDthCauseC;
     View linechkDthCauseC;
     TextView VlblchkDthCauseC;
     CheckBox chkchkDthCauseC;
     LinearLayout secchkDthCauseD;
     View linechkDthCauseD;
     TextView VlblchkDthCauseD;
     CheckBox chkchkDthCauseD;
     LinearLayout secchkDthCauseE;
     View linechkDthCauseE;
     TextView VlblchkDthCauseE;
     CheckBox chkchkDthCauseE;
     LinearLayout secchkDthCauseF;
     View linechkDthCauseF;
     TextView VlblchkDthCauseF;
     CheckBox chkchkDthCauseF;
     LinearLayout secchkDthCauseG;
     View linechkDthCauseG;
     TextView VlblchkDthCauseG;
     CheckBox chkchkDthCauseG;
     LinearLayout secchkDthCauseH;
     View linechkDthCauseH;
     TextView VlblchkDthCauseH;
     CheckBox chkchkDthCauseH;
     LinearLayout secchkDthCauseI;
     View linechkDthCauseI;
     TextView VlblchkDthCauseI;
     CheckBox chkchkDthCauseI;
     LinearLayout secchkDthCauseJ;
     View linechkDthCauseJ;
     TextView VlblchkDthCauseJ;
     CheckBox chkchkDthCauseJ;
     LinearLayout secchkDthCauseK;
     View linechkDthCauseK;
     TextView VlblchkDthCauseK;
     CheckBox chkchkDthCauseK;
     LinearLayout secchkDthCauseL;
     View linechkDthCauseL;
     TextView VlblchkDthCauseL;
     CheckBox chkchkDthCauseL;
     LinearLayout secchkDthCauseM;
     View linechkDthCauseM;
     TextView VlblchkDthCauseM;
     CheckBox chkchkDthCauseM;
     LinearLayout secchkDthCauseN;
     View linechkDthCauseN;
     TextView VlblchkDthCauseN;
     CheckBox chkchkDthCauseN;
     LinearLayout secchkDthCauseO;
     View linechkDthCauseO;
     TextView VlblchkDthCauseO;
     CheckBox chkchkDthCauseO;
     LinearLayout secchkDthCauseP;
     View linechkDthCauseP;
     TextView VlblchkDthCauseP;
     CheckBox chkchkDthCauseP;
     LinearLayout secchkDthCauseQ;
     View linechkDthCauseQ;
     TextView VlblchkDthCauseQ;
     CheckBox chkchkDthCauseQ;
     LinearLayout secchkDthCauseR;
     View linechkDthCauseR;
     TextView VlblchkDthCauseR;
     CheckBox chkchkDthCauseR;
     LinearLayout secchkDthCauseS;
     View linechkDthCauseS;
     TextView VlblchkDthCauseS;
     CheckBox chkchkDthCauseS;
     LinearLayout secchkDthCauseT;
     View linechkDthCauseT;
     TextView VlblchkDthCauseT;
     CheckBox chkchkDthCauseT;
     LinearLayout secchkDthCauseX;
     View linechkDthCauseX;
     TextView VlblchkDthCauseX;
     CheckBox chkchkDthCauseX;
     LinearLayout secchkDthCauseY;
     View linechkDthCauseY;
     TextView VlblchkDthCauseY;
     CheckBox chkchkDthCauseY;
     LinearLayout secchkDthCauseZ;
     View linechkDthCauseZ;
     TextView VlblchkDthCauseZ;
     CheckBox chkchkDthCauseZ;
     LinearLayout secDthCauseOth;
     View lineDthCauseOth;
     TextView VlblDthCauseOth;
     AutoCompleteTextView txtDthCauseOth;
     LinearLayout secDthHCProf;
     View lineDthHCProf;
     TextView VlblDthHCProf;
     RadioGroup rdogrpDthHCProf;
     RadioButton rdoDthHCProf1;
     RadioButton rdoDthHCProf2;
     RadioButton rdoDthHCProf3;
     RadioButton rdoDthHCProf4;
     LinearLayout seclbl03;
     View linelbl03;
     LinearLayout secchkDthCauseProfA;
     View linechkDthCauseProfA;
     TextView VlblchkDthCauseProfA;
     CheckBox chkchkDthCauseProfA;
     LinearLayout secchkDthCauseProfB;
     View linechkDthCauseProfB;
     TextView VlblchkDthCauseProfB;
     CheckBox chkchkDthCauseProfB;
     LinearLayout secchkDthCauseProfC;
     View linechkDthCauseProfC;
     TextView VlblchkDthCauseProfC;
     CheckBox chkchkDthCauseProfC;
     LinearLayout secchkDthCauseProfD;
     View linechkDthCauseProfD;
     TextView VlblchkDthCauseProfD;
     CheckBox chkchkDthCauseProfD;
     LinearLayout secchkDthCauseProfE;
     View linechkDthCauseProfE;
     TextView VlblchkDthCauseProfE;
     CheckBox chkchkDthCauseProfE;
     LinearLayout secchkDthCauseProfF;
     View linechkDthCauseProfF;
     TextView VlblchkDthCauseProfF;
     CheckBox chkchkDthCauseProfF;
     LinearLayout secchkDthCauseProfG;
     View linechkDthCauseProfG;
     TextView VlblchkDthCauseProfG;
     CheckBox chkchkDthCauseProfG;
     LinearLayout secchkDthCauseProfH;
     View linechkDthCauseProfH;
     TextView VlblchkDthCauseProfH;
     CheckBox chkchkDthCauseProfH;
     LinearLayout secchkDthCauseProfI;
     View linechkDthCauseProfI;
     TextView VlblchkDthCauseProfI;
     CheckBox chkchkDthCauseProfI;
     LinearLayout secchkDthCauseProfJ;
     View linechkDthCauseProfJ;
     TextView VlblchkDthCauseProfJ;
     CheckBox chkchkDthCauseProfJ;
     LinearLayout secchkDthCauseProfK;
     View linechkDthCauseProfK;
     TextView VlblchkDthCauseProfK;
     CheckBox chkchkDthCauseProfK;
     LinearLayout secchkDthCauseProfL;
     View linechkDthCauseProfL;
     TextView VlblchkDthCauseProfL;
     CheckBox chkchkDthCauseProfL;
     LinearLayout secchkDthCauseProfM;
     View linechkDthCauseProfM;
     TextView VlblchkDthCauseProfM;
     CheckBox chkchkDthCauseProfM;
     LinearLayout secchkDthCauseProfN;
     View linechkDthCauseProfN;
     TextView VlblchkDthCauseProfN;
     CheckBox chkchkDthCauseProfN;
     LinearLayout secchkDthCauseProfO;
     View linechkDthCauseProfO;
     TextView VlblchkDthCauseProfO;
     CheckBox chkchkDthCauseProfO;
     LinearLayout secchkDthCauseProfP;
     View linechkDthCauseProfP;
     TextView VlblchkDthCauseProfP;
     CheckBox chkchkDthCauseProfP;
     LinearLayout secchkDthCauseProfQ;
     View linechkDthCauseProfQ;
     TextView VlblchkDthCauseProfQ;
     CheckBox chkchkDthCauseProfQ;
     LinearLayout secchkDthCauseProfR;
     View linechkDthCauseProfR;
     TextView VlblchkDthCauseProfR;
     CheckBox chkchkDthCauseProfR;
     LinearLayout secchkDthCauseProfS;
     View linechkDthCauseProfS;
     TextView VlblchkDthCauseProfS;
     CheckBox chkchkDthCauseProfS;
     LinearLayout secchkDthCauseProfT;
     View linechkDthCauseProfT;
     TextView VlblchkDthCauseProfT;
     CheckBox chkchkDthCauseProfT;
     LinearLayout secchkDthCauseProfX;
     View linechkDthCauseProfX;
     TextView VlblchkDthCauseProfX;
     CheckBox chkchkDthCauseProfX;
     LinearLayout secchkDthCauseProfY;
     View linechkDthCauseProfY;
     TextView VlblchkDthCauseProfY;
     CheckBox chkchkDthCauseProfY;
     LinearLayout secchkDthCauseProfZ;
     View linechkDthCauseProfZ;
     TextView VlblchkDthCauseProfZ;
     CheckBox chkchkDthCauseProfZ;
     LinearLayout secDthCauseOthProf;
     View lineDthCauseOthProf;
     TextView VlblDthCauseOthProf;
     AutoCompleteTextView txtDthCauseOthProf;
     LinearLayout secDthCategory;
     View lineDthCategory;
     TextView VlblDthCategory;
     Spinner spnDthCategory;
     LinearLayout secDthEnrolChamps;
     View lineDthEnrolChamps;
     TextView VlblDthEnrolChamps;
     RadioGroup rdogrpDthEnrolChamps;
     RadioButton rdoDthEnrolChamps1;
     RadioButton rdoDthEnrolChamps2;
     RadioButton rdoDthEnrolChamps3;
     RadioButton rdoDthEnrolChamps4;

     LinearLayout secDthChampsIdKnown;
     View lineDthChampsIdKnown;
     TextView VlblDthChampsIdKnown;
     RadioGroup rdogrpDthChampsIdKnown;

     RadioButton rdoDthChampsIdKnown1;
     RadioButton rdoDthChampsIdKnown2;
     RadioButton rdoDthChampsIdKnown3;
     RadioButton rdoDthChampsIdKnown4;
     LinearLayout secDthChampsId;
     View lineDthChampsId;
     TextView VlblDthChampsId;
     EditText txtDthChampsId;
    LinearLayout secDthVDate;
    View lineDthVDate;
    TextView VlblDthVDate;
    EditText dtpDthVDate;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secDthNote;
    View lineDthNote;
    TextView VlblDthNote;
    EditText txtDthNote;

     LinearLayout secDthNote1;
     View lineDthNote1;

     int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String DEATHID = "";
      String MEM_ID = "";
      String HHID = "";
      String EV_TYPE = "";
      String ROUND = "";
      String VISIT_DATE = "";
      String AGE_DAYS = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.surv_death);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         DEATHID = IDbundle.getString("DeathID");
         MEM_ID = IDbundle.getString("MemID");
         HHID = IDbundle.getString("HHID");
         EV_TYPE = IDbundle.getString("evtype");
         ROUND = IDbundle.getString("round");
         AGE_DAYS = IDbundle.getString("age");
         VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy

         TableName = "tmpDeath";
         MODULEID = 30;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Death.this);
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
        Connection.LocalizeLanguage(Surv_Death.this, MODULEID, LANGUAGEID);

         /*secDthAdrsAL1.setVisibility(View.GONE);
         secDthAdrsAL2.setVisibility(View.GONE);
         secDthAdrsAL3.setVisibility(View.GONE);*/
         secDthAdrsAL4.setVisibility(View.GONE);
         secDthAdrsAL5.setVisibility(View.GONE);
         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.BANGLADESH_SITE1))
         {
             secDthAdrsState.setVisibility(View.GONE);
             lineDthAdrsState.setVisibility(View.GONE);
             VlblDthAdrsAL1.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER1);
             VlblDthAdrsAL2.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER2);
             VlblDthAdrsAL3.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER3);
         }

         //=========================================================================================
         //Nigeria: Cross River
         //=========================================================================================
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1))
         {
             VlblDthAdrsAL1.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER1);
             VlblDthAdrsAL2.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER2);
             VlblDthAdrsAL3.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER3);
             VlblDthAdrsAL4.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER4);
         }
         //=========================================================================================
         //Nigeria: Bauchi
         //=========================================================================================
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2))
         {
             secDthAdrsState.setVisibility(View.GONE);
             lineDthAdrsState.setVisibility(View.GONE);
             VlblDthAdrsAL1.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER1);
             VlblDthAdrsAL2.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER2);
             VlblDthAdrsAL3.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER3);
         }
         //=========================================================================================
         //Sierra Leone
         //=========================================================================================
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1))
         {
             secDthAdrsState.setVisibility(View.GONE);
             lineDthAdrsState.setVisibility(View.GONE);
             secDthHCProf.setVisibility(View.VISIBLE);
             lineDthHCProf.setVisibility(View.VISIBLE);
             secDthCategory.setVisibility(View.VISIBLE);
             lineDthCategory.setVisibility(View.VISIBLE);
             secDthEnrolChamps.setVisibility(View.VISIBLE);
             lineDthEnrolChamps.setVisibility(View.VISIBLE);
             secDthChampsIdKnown.setVisibility(View.VISIBLE);
             lineDthChampsIdKnown.setVisibility(View.VISIBLE);
             secDthChampsId.setVisibility(View.VISIBLE);
             lineDthChampsId.setVisibility(View.VISIBLE);
             VlblDthAdrsAL1.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER1);
             VlblDthAdrsAL2.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER2);
             VlblDthAdrsAL3.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER3);
         }
         //=========================================================================================
         //Mali
         //=========================================================================================
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1))
         {
             secDthAdrsState.setVisibility(View.GONE);
             lineDthAdrsState.setVisibility(View.GONE);
             secDthSick.setVisibility(View.VISIBLE);
             lineDthSick.setVisibility(View.VISIBLE);
             secDthHCProf.setVisibility(View.VISIBLE);
             lineDthHCProf.setVisibility(View.VISIBLE);
             VlblDthAdrsAL1.setText(ProjectSetting.MALI_SITE1_GEO_LAYER1);
             VlblDthAdrsAL2.setText(ProjectSetting.MALI_SITE1_GEO_LAYER2);
             VlblDthAdrsAL3.setText(ProjectSetting.MALI_SITE1_GEO_LAYER3);
         }

         dtpDthDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnDthDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpDthVDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnDthVDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });


         txtDthTime.setOnTouchListener(new View.OnTouchListener() {
         @Override
         public boolean onTouch(View v, MotionEvent event) {
             if(event.getAction() == MotionEvent.ACTION_UP) {
                  VariableID = "btnDthTime"; showDialog(TIME_DIALOG);
                  return true;
             }
             return false;
           }
         });

         //Hide all skip variables
         secDthPlaceOth.setVisibility(View.GONE);
         lineDthPlaceOth.setVisibility(View.GONE);
         secDthCauseOth.setVisibility(View.GONE);
         lineDthCauseOth.setVisibility(View.GONE);

        DataSearch(DEATHID);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Death.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         secDeathID=(LinearLayout)findViewById(R.id.secDeathID);
         lineDeathID=(View)findViewById(R.id.lineDeathID);
         VlblDeathID=(TextView) findViewById(R.id.VlblDeathID);
         txtDeathID=(EditText) findViewById(R.id.txtDeathID);
//         txtDeathID.setText(DEATHID);
//         txtDeathID.setEnabled(false);
         if(DEATHID.length()==0) txtDeathID.setText(Global.Get_UUID(DEVICEID));
         else txtDeathID.setText(DEATHID);
         txtDeathID.setEnabled(false);

         secHHID=(LinearLayout)findViewById(R.id.secHHID);
         lineHHID=(View)findViewById(R.id.lineHHID);
         VlblHHID=(TextView) findViewById(R.id.VlblHHID);
         txtHHID=(EditText) findViewById(R.id.txtHHID);
         txtHHID.setText(HHID);
         txtHHID.setEnabled(false);

         secMemID=(LinearLayout)findViewById(R.id.secMemID);
         lineMemID=(View)findViewById(R.id.lineMemID);
         VlblMemID=(TextView) findViewById(R.id.VlblMemID);
         txtMemID=(EditText) findViewById(R.id.txtMemID);
         txtMemID.setText(MEM_ID);

         secDthDate=(LinearLayout)findViewById(R.id.secDthDate);
         lineDthDate=(View)findViewById(R.id.lineDthDate);
         VlblDthDate=(TextView) findViewById(R.id.VlblDthDate);
         dtpDthDate=(EditText) findViewById(R.id.dtpDthDate);

         dtpDthDate.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 if(dtpDthDate.getText().toString().length() > 0){
                     if(rdoDthDateType3.isChecked()){
                         rdogrpDthDateType.clearCheck();
                     }
                 }
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });

         secDthDateType=(LinearLayout)findViewById(R.id.secDthDateType);
         lineDthDateType=(View)findViewById(R.id.lineDthDateType);
         VlblDthDateType = (TextView) findViewById(R.id.VlblDthDateType);
         rdogrpDthDateType = (RadioGroup) findViewById(R.id.rdogrpDthDateType);
         rdoDthDateType1 = (RadioButton) findViewById(R.id.rdoDthDateType1);
         rdoDthDateType2 = (RadioButton) findViewById(R.id.rdoDthDateType2);
         rdoDthDateType3 = (RadioButton) findViewById(R.id.rdoDthDateType3);

         rdogrpDthDateType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                  String rbData = "";
                  RadioButton rb;
                  String[] d_rdogrpDthDateType = new String[]{"1", "2", "8"};
                  for (int i = 0; i < rdogrpDthDateType.getChildCount(); i++) {
                      rb = (RadioButton) rdogrpDthDateType.getChildAt(i);
                      if (rb.isChecked()) rbData = d_rdogrpDthDateType[i];
                  }

                  if(rbData.equalsIgnoreCase("8")){
                      if(dtpDthDate.getText().toString().length() > 0){
                          dtpDthDate.setText("");
                      }
                  }
              }
          });


         secDthTime=(LinearLayout)findViewById(R.id.secDthTime);
         lineDthTime=(View)findViewById(R.id.lineDthTime);
         VlblDthTime=(TextView) findViewById(R.id.VlblDthTime);
         txtDthTime=(EditText) findViewById(R.id.txtDthTime);

         txtDthTime.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 if(txtDthTime.getText().toString().length() > 0){
                     if(rdoDthTimeType3.isChecked()){
                         rdogrpDthTimeType.clearCheck();
                     }
                 }
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });

         secDthTimeType=(LinearLayout)findViewById(R.id.secDthTimeType);
         lineDthTimeType=(View)findViewById(R.id.lineDthTimeType);
         VlblDthTimeType = (TextView) findViewById(R.id.VlblDthTimeType);
         rdogrpDthTimeType = (RadioGroup) findViewById(R.id.rdogrpDthTimeType);
         rdoDthTimeType1 = (RadioButton) findViewById(R.id.rdoDthTimeType1);
         rdoDthTimeType2 = (RadioButton) findViewById(R.id.rdoDthTimeType2);
         rdoDthTimeType3 = (RadioButton) findViewById(R.id.rdoDthTimeType3);

         rdogrpDthTimeType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpDthTimeType = new String[]{"1", "2", "8"};
                 for (int i = 0; i < rdogrpDthTimeType.getChildCount(); i++) {
                     rb = (RadioButton) rdogrpDthTimeType.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpDthTimeType[i];
                 }

                 if(rbData.equalsIgnoreCase("8")){
                     if(txtDthTime.getText().toString().length() > 0){
                         txtDthTime.setText("");
                     }
                 }
             }
         });

         secDthPlace=(LinearLayout)findViewById(R.id.secDthPlace);
         lineDthPlace=(View)findViewById(R.id.lineDthPlace);
         VlblDthPlace=(TextView) findViewById(R.id.VlblDthPlace);
         spnDthPlace=(Spinner) findViewById(R.id.spnDthPlace);
         List<String> listDthPlace = new ArrayList<String>();
//         listDthPlace.add("");
//         listDthPlace.add("01-At deceaseds home");
//         listDthPlace.add("02-At a relatives home");
//         listDthPlace.add("03-At a healers home");
//         listDthPlace.add("04-At other home");
//         listDthPlace.add("05-At a public health facility");
//         listDthPlace.add("06-At a private health facility");
//         listDthPlace.add("07-At an NGO health facility");
//         listDthPlace.add("08-On route to a health facility");
//         listDthPlace.add("09-At church/mosque");
//         listDthPlace.add("97-Other");
//         listDthPlace.add("98-Dont know");
//         listDthPlace.add("99-Refused to respond");
         listDthPlace.add("");
         listDthPlace.add("11-At own home");
         listDthPlace.add("12-At a relative's home");
         listDthPlace.add("13-At a healers home");
         listDthPlace.add("15-At other home");
         listDthPlace.add("21-At a public health facility");
         listDthPlace.add("31-At a private health facility");
         listDthPlace.add("41-At an NGO health facility");
         listDthPlace.add("51-On route to a health facility");
         listDthPlace.add("52-On the way to home");
         listDthPlace.add("53-On the way to health facility to other health facility");
         listDthPlace.add("54-At church/mosque");
         listDthPlace.add("55-Outdoors");
         listDthPlace.add("97-Other");
         listDthPlace.add("98-Don't know");
         listDthPlace.add("99-Refused to respond");
         spnDthPlace.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listDthPlace)));

         spnDthPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnDthPlace.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnDthPlace.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                     secDthPlaceOth.setVisibility(View.VISIBLE);
                     lineDthPlaceOth.setVisibility(View.VISIBLE);
                     secDthPlaceFacility.setVisibility(View.GONE);
                     lineDthPlaceFacility.setVisibility(View.GONE);
                 }
                 else if(spnData.equalsIgnoreCase("21") || spnData.equalsIgnoreCase("31") || spnData.equalsIgnoreCase("41"))
                 {
                     if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                         secDthPlaceFacility.setVisibility(View.VISIBLE);
                         lineDthPlaceFacility.setVisibility(View.VISIBLE);
                     }
                     secDthPlaceOth.setVisibility(View.GONE);
                     lineDthPlaceOth.setVisibility(View.GONE);
                 }
                 else
                 {
                     secDthPlaceOth.setVisibility(View.GONE);
                     lineDthPlaceOth.setVisibility(View.GONE);
                     secDthPlaceFacility.setVisibility(View.GONE);
                     lineDthPlaceFacility.setVisibility(View.GONE);
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secDthPlaceOth=(LinearLayout)findViewById(R.id.secDthPlaceOth);
         lineDthPlaceOth=(View)findViewById(R.id.lineDthPlaceOth);
         VlblDthPlaceOth=(TextView) findViewById(R.id.VlblDthPlaceOth);
         txtDthPlaceOth=(AutoCompleteTextView) findViewById(R.id.txtDthPlaceOth);
         C.setupAutoComplete(TableName,txtDthPlaceOth,"DthPlaceOth"); //setup autocomplete view by event


         secDthPlaceFacility=(LinearLayout)findViewById(R.id.secDthPlaceFacility);
         lineDthPlaceFacility=(View)findViewById(R.id.lineDthPlaceFacility);
         VlblDthPlaceFacility=(TextView) findViewById(R.id.VlblDthPlaceFacility);
         txtDthPlaceFacility=(EditText) findViewById(R.id.txtDthPlaceFacility);

         secDthAdrsState=(LinearLayout)findViewById(R.id.secDthAdrsState);
         lineDthAdrsState=(View)findViewById(R.id.lineDthAdrsState);
         VlblDthAdrsState = (TextView) findViewById(R.id.VlblDthAdrsState);
         rdogrpDthAdrsState = (RadioGroup) findViewById(R.id.rdogrpDthAdrsState);
         rdoDthAdrsState1 = (RadioButton) findViewById(R.id.rdoDthAdrsState1);
         rdoDthAdrsState2 = (RadioButton) findViewById(R.id.rdoDthAdrsState2);

         rdogrpDthAdrsState.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpDthAdrsState = new String[]{"1", "2"};
                 for (int i = 0; i < rdogrpDthAdrsState.getChildCount(); i++) {
                     rb = (RadioButton) rdogrpDthAdrsState.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpDthAdrsState[i];
                 }

                 if(rbData.equalsIgnoreCase("2")){
                    secDthAdrsAL1.setVisibility(View.GONE);
                    lineDthAdrsAL1.setVisibility(View.GONE);
                    secDthAdrsAL2.setVisibility(View.GONE);
                    lineDthAdrsAL2.setVisibility(View.GONE);
                    secDthAdrsAL3.setVisibility(View.GONE);
                    lineDthAdrsAL3.setVisibility(View.GONE);
                    secDthAdrsAL4.setVisibility(View.VISIBLE);
                    lineDthAdrsAL4.setVisibility(View.VISIBLE);
                 }
                 else{
                     secDthAdrsAL1.setVisibility(View.VISIBLE);
                     lineDthAdrsAL1.setVisibility(View.VISIBLE);
                     secDthAdrsAL2.setVisibility(View.VISIBLE);
                     lineDthAdrsAL2.setVisibility(View.VISIBLE);
                     secDthAdrsAL3.setVisibility(View.VISIBLE);
                     lineDthAdrsAL3.setVisibility(View.VISIBLE);
                     secDthAdrsAL4.setVisibility(View.GONE);
                     lineDthAdrsAL4.setVisibility(View.GONE);
                 }
             }
         });

         seclbl01=(LinearLayout)findViewById(R.id.seclbl01);
         linelbl01=(View)findViewById(R.id.linelbl01);
         secDthAdrsAL1=(LinearLayout)findViewById(R.id.secDthAdrsAL1);
         lineDthAdrsAL1=(View)findViewById(R.id.lineDthAdrsAL1);
         VlblDthAdrsAL1=(TextView) findViewById(R.id.VlblDthAdrsAL1);
         txtDthAdrsAL1=(AutoCompleteTextView) findViewById(R.id.txtDthAdrsAL1);

         //txtDthAdrsAL1.setAdapter(C.getArrayAdapter("Select distinct DthAdrsAL1 from "+ TableName +" order by DthAdrsAL1"));

         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
             txtDthAdrsAL1.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_LGA())));
         }
         else{
             txtDthAdrsAL1.setAdapter(C.getArrayAdapter("Select distinct DthAdrsAL1 from "+ TableName +" order by DthAdrsAL1"));
         }
         txtDthAdrsAL1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtDthAdrsAL1.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;

                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtDthAdrsAL1.getRight() - txtDthAdrsAL1.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secDthAdrsAL2=(LinearLayout)findViewById(R.id.secDthAdrsAL2);
         lineDthAdrsAL2=(View)findViewById(R.id.lineDthAdrsAL2);
         VlblDthAdrsAL2=(TextView) findViewById(R.id.VlblDthAdrsAL2);
         txtDthAdrsAL2=(AutoCompleteTextView) findViewById(R.id.txtDthAdrsAL2);
         //txtDthAdrsAL2.setAdapter(C.getArrayAdapter("Select distinct DthAdrsAL2 from "+ TableName +" order by DthAdrsAL2"));
         txtDthAdrsAL2.setAdapter(C.getArrayAdapter("select distinct GeoLevel8Name from location order by GeoLevel8Name"));

         txtDthAdrsAL2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtDthAdrsAL2.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;

                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtDthAdrsAL2.getRight() - txtDthAdrsAL2.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secDthAdrsAL3=(LinearLayout)findViewById(R.id.secDthAdrsAL3);
         lineDthAdrsAL3=(View)findViewById(R.id.lineDthAdrsAL3);
         VlblDthAdrsAL3=(TextView) findViewById(R.id.VlblDthAdrsAL3);
         txtDthAdrsAL3=(AutoCompleteTextView) findViewById(R.id.txtDthAdrsAL3);
         //txtDthAdrsAL3.setAdapter(C.getArrayAdapter("Select distinct DthAdrsAL3 from "+ TableName +" order by DthAdrsAL3"));
         txtDthAdrsAL3.setAdapter(C.getArrayAdapter("select VillName from Village order by VillName"));

         txtDthAdrsAL3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtDthAdrsAL3.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;

                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtDthAdrsAL3.getRight() - txtDthAdrsAL3.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secDthAdrsAL4=(LinearLayout)findViewById(R.id.secDthAdrsAL4);
         lineDthAdrsAL4=(View)findViewById(R.id.lineDthAdrsAL4);
         VlblDthAdrsAL4=(TextView) findViewById(R.id.VlblDthAdrsAL4);
         txtDthAdrsAL4=(AutoCompleteTextView) findViewById(R.id.txtDthAdrsAL4);

         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
             txtDthAdrsAL4.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_State())));
         }
         else{
             txtDthAdrsAL4.setAdapter(C.getArrayAdapter("Select distinct DthAdrsAL4 from "+ TableName +" order by DthAdrsAL4"));
         }
         txtDthAdrsAL4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtDthAdrsAL4.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;

                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtDthAdrsAL4.getRight() - txtDthAdrsAL4.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secDthAdrsAL5=(LinearLayout)findViewById(R.id.secDthAdrsAL5);
         lineDthAdrsAL5=(View)findViewById(R.id.lineDthAdrsAL5);
         VlblDthAdrsAL5=(TextView) findViewById(R.id.VlblDthAdrsAL5);
         txtDthAdrsAL5=(AutoCompleteTextView) findViewById(R.id.txtDthAdrsAL5);
         txtDthAdrsAL5.setAdapter(C.getArrayAdapter("Select distinct DthAdrsAL5 from "+ TableName +" order by DthAdrsAL5"));

         txtDthAdrsAL5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtDthAdrsAL5.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;

                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtDthAdrsAL5.getRight() - txtDthAdrsAL5.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secDthSick=(LinearLayout)findViewById(R.id.secDthSick);
         lineDthSick=(View)findViewById(R.id.lineDthSick);
         VlblDthSick = (TextView) findViewById(R.id.VlblDthSick);
         rdogrpDthSick = (RadioGroup) findViewById(R.id.rdogrpDthSick);
         rdoDthSick1 = (RadioButton) findViewById(R.id.rdoDthSick1);
         rdoDthSick2 = (RadioButton) findViewById(R.id.rdoDthSick2);
         secDthCertificate=(LinearLayout)findViewById(R.id.secDthCertificate);
         lineDthCertificate=(View)findViewById(R.id.lineDthCertificate);
         VlblDthCertificate = (TextView) findViewById(R.id.VlblDthCertificate);
         rdogrpDthCertificate = (RadioGroup) findViewById(R.id.rdogrpDthCertificate);
         rdoDthCertificate1 = (RadioButton) findViewById(R.id.rdoDthCertificate1);
         rdoDthCertificate2 = (RadioButton) findViewById(R.id.rdoDthCertificate2);
         seclbl02=(LinearLayout)findViewById(R.id.seclbl02);
         linelbl02=(View)findViewById(R.id.linelbl02);
         secchkDthCauseA=(LinearLayout)findViewById(R.id.secchkDthCauseA);
         linechkDthCauseA=(View)findViewById(R.id.linechkDthCauseA);
         VlblchkDthCauseA=(TextView) findViewById(R.id.VlblchkDthCauseA);
         chkchkDthCauseA=(CheckBox) findViewById(R.id.chkchkDthCauseA);

         chkchkDthCauseA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseB=(LinearLayout)findViewById(R.id.secchkDthCauseB);
         linechkDthCauseB=(View)findViewById(R.id.linechkDthCauseB);
         VlblchkDthCauseB=(TextView) findViewById(R.id.VlblchkDthCauseB);
         chkchkDthCauseB=(CheckBox) findViewById(R.id.chkchkDthCauseB);

         chkchkDthCauseB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseC=(LinearLayout)findViewById(R.id.secchkDthCauseC);
         linechkDthCauseC=(View)findViewById(R.id.linechkDthCauseC);
         VlblchkDthCauseC=(TextView) findViewById(R.id.VlblchkDthCauseC);
         chkchkDthCauseC=(CheckBox) findViewById(R.id.chkchkDthCauseC);

         chkchkDthCauseC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseD=(LinearLayout)findViewById(R.id.secchkDthCauseD);
         linechkDthCauseD=(View)findViewById(R.id.linechkDthCauseD);
         VlblchkDthCauseD=(TextView) findViewById(R.id.VlblchkDthCauseD);
         chkchkDthCauseD=(CheckBox) findViewById(R.id.chkchkDthCauseD);

         chkchkDthCauseD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseE=(LinearLayout)findViewById(R.id.secchkDthCauseE);
         linechkDthCauseE=(View)findViewById(R.id.linechkDthCauseE);
         VlblchkDthCauseE=(TextView) findViewById(R.id.VlblchkDthCauseE);
         chkchkDthCauseE=(CheckBox) findViewById(R.id.chkchkDthCauseE);

         chkchkDthCauseE.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseF=(LinearLayout)findViewById(R.id.secchkDthCauseF);
         linechkDthCauseF=(View)findViewById(R.id.linechkDthCauseF);
         VlblchkDthCauseF=(TextView) findViewById(R.id.VlblchkDthCauseF);
         chkchkDthCauseF=(CheckBox) findViewById(R.id.chkchkDthCauseF);

         chkchkDthCauseF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseG=(LinearLayout)findViewById(R.id.secchkDthCauseG);
         linechkDthCauseG=(View)findViewById(R.id.linechkDthCauseG);
         VlblchkDthCauseG=(TextView) findViewById(R.id.VlblchkDthCauseG);
         chkchkDthCauseG=(CheckBox) findViewById(R.id.chkchkDthCauseG);

         chkchkDthCauseG.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseH=(LinearLayout)findViewById(R.id.secchkDthCauseH);
         linechkDthCauseH=(View)findViewById(R.id.linechkDthCauseH);
         VlblchkDthCauseH=(TextView) findViewById(R.id.VlblchkDthCauseH);
         chkchkDthCauseH=(CheckBox) findViewById(R.id.chkchkDthCauseH);

         chkchkDthCauseH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseI=(LinearLayout)findViewById(R.id.secchkDthCauseI);
         linechkDthCauseI=(View)findViewById(R.id.linechkDthCauseI);
         VlblchkDthCauseI=(TextView) findViewById(R.id.VlblchkDthCauseI);
         chkchkDthCauseI=(CheckBox) findViewById(R.id.chkchkDthCauseI);

         chkchkDthCauseI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseJ=(LinearLayout)findViewById(R.id.secchkDthCauseJ);
         linechkDthCauseJ=(View)findViewById(R.id.linechkDthCauseJ);
         VlblchkDthCauseJ=(TextView) findViewById(R.id.VlblchkDthCauseJ);
         chkchkDthCauseJ=(CheckBox) findViewById(R.id.chkchkDthCauseJ);

         chkchkDthCauseJ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseK=(LinearLayout)findViewById(R.id.secchkDthCauseK);
         linechkDthCauseK=(View)findViewById(R.id.linechkDthCauseK);
         VlblchkDthCauseK=(TextView) findViewById(R.id.VlblchkDthCauseK);
         chkchkDthCauseK=(CheckBox) findViewById(R.id.chkchkDthCauseK);

         chkchkDthCauseK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseL=(LinearLayout)findViewById(R.id.secchkDthCauseL);
         linechkDthCauseL=(View)findViewById(R.id.linechkDthCauseL);
         VlblchkDthCauseL=(TextView) findViewById(R.id.VlblchkDthCauseL);
         chkchkDthCauseL=(CheckBox) findViewById(R.id.chkchkDthCauseL);

         chkchkDthCauseL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseM=(LinearLayout)findViewById(R.id.secchkDthCauseM);
         linechkDthCauseM=(View)findViewById(R.id.linechkDthCauseM);
         VlblchkDthCauseM=(TextView) findViewById(R.id.VlblchkDthCauseM);
         chkchkDthCauseM=(CheckBox) findViewById(R.id.chkchkDthCauseM);

         chkchkDthCauseM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseN=(LinearLayout)findViewById(R.id.secchkDthCauseN);
         linechkDthCauseN=(View)findViewById(R.id.linechkDthCauseN);
         VlblchkDthCauseN=(TextView) findViewById(R.id.VlblchkDthCauseN);
         chkchkDthCauseN=(CheckBox) findViewById(R.id.chkchkDthCauseN);

         chkchkDthCauseN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseO=(LinearLayout)findViewById(R.id.secchkDthCauseO);
         linechkDthCauseO=(View)findViewById(R.id.linechkDthCauseO);
         VlblchkDthCauseO=(TextView) findViewById(R.id.VlblchkDthCauseO);
         chkchkDthCauseO=(CheckBox) findViewById(R.id.chkchkDthCauseO);

         chkchkDthCauseO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseP=(LinearLayout)findViewById(R.id.secchkDthCauseP);
         linechkDthCauseP=(View)findViewById(R.id.linechkDthCauseP);
         VlblchkDthCauseP=(TextView) findViewById(R.id.VlblchkDthCauseP);
         chkchkDthCauseP=(CheckBox) findViewById(R.id.chkchkDthCauseP);

         chkchkDthCauseP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseQ=(LinearLayout)findViewById(R.id.secchkDthCauseQ);
         linechkDthCauseQ=(View)findViewById(R.id.linechkDthCauseQ);
         VlblchkDthCauseQ=(TextView) findViewById(R.id.VlblchkDthCauseQ);
         chkchkDthCauseQ=(CheckBox) findViewById(R.id.chkchkDthCauseQ);

         chkchkDthCauseQ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseR=(LinearLayout)findViewById(R.id.secchkDthCauseR);
         linechkDthCauseR=(View)findViewById(R.id.linechkDthCauseR);
         VlblchkDthCauseR=(TextView) findViewById(R.id.VlblchkDthCauseR);
         chkchkDthCauseR=(CheckBox) findViewById(R.id.chkchkDthCauseR);

         chkchkDthCauseR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseS=(LinearLayout)findViewById(R.id.secchkDthCauseS);
         linechkDthCauseS=(View)findViewById(R.id.linechkDthCauseS);
         VlblchkDthCauseS=(TextView) findViewById(R.id.VlblchkDthCauseS);
         chkchkDthCauseS=(CheckBox) findViewById(R.id.chkchkDthCauseS);

         chkchkDthCauseS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseT=(LinearLayout)findViewById(R.id.secchkDthCauseT);
         linechkDthCauseT=(View)findViewById(R.id.linechkDthCauseT);
         VlblchkDthCauseT=(TextView) findViewById(R.id.VlblchkDthCauseT);
         chkchkDthCauseT=(CheckBox) findViewById(R.id.chkchkDthCauseT);

         chkchkDthCauseT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                 }
             }
         });

         secchkDthCauseX=(LinearLayout)findViewById(R.id.secchkDthCauseX);
         linechkDthCauseX=(View)findViewById(R.id.linechkDthCauseX);
         VlblchkDthCauseX=(TextView) findViewById(R.id.VlblchkDthCauseX);
         chkchkDthCauseX=(CheckBox) findViewById(R.id.chkchkDthCauseX);
         chkchkDthCauseX.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
             {
                 if ( isChecked )
                 {
                     if(chkchkDthCauseY.isChecked()){
                         chkchkDthCauseY.setChecked(false);
                     }
                     if(chkchkDthCauseZ.isChecked()){
                         chkchkDthCauseZ.setChecked(false);
                     }
                     secDthCauseOth.setVisibility(View.VISIBLE);
                     lineDthCauseOth.setVisibility(View.VISIBLE);
                 }
                 else {
                     secDthCauseOth.setVisibility(View.GONE);
                     lineDthCauseOth.setVisibility(View.GONE);
                     txtDthCauseOth.setText("");
                 }
             }
         });
         secchkDthCauseY=(LinearLayout)findViewById(R.id.secchkDthCauseY);
         linechkDthCauseY=(View)findViewById(R.id.linechkDthCauseY);
         VlblchkDthCauseY=(TextView) findViewById(R.id.VlblchkDthCauseY);
         chkchkDthCauseY=(CheckBox) findViewById(R.id.chkchkDthCauseY);
         chkchkDthCauseY.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
             {
                 if ( isChecked )
                 {
                     chkchkDthCauseA.setChecked(false);
                     chkchkDthCauseB.setChecked(false);
                     chkchkDthCauseC.setChecked(false);
                     chkchkDthCauseD.setChecked(false);
                     chkchkDthCauseE.setChecked(false);
                     chkchkDthCauseF.setChecked(false);
                     chkchkDthCauseG.setChecked(false);
                     chkchkDthCauseH.setChecked(false);
                     chkchkDthCauseI.setChecked(false);
                     chkchkDthCauseJ.setChecked(false);
                     chkchkDthCauseK.setChecked(false);
                     chkchkDthCauseL.setChecked(false);
                     chkchkDthCauseM.setChecked(false);
                     chkchkDthCauseN.setChecked(false);
                     chkchkDthCauseO.setChecked(false);
                     chkchkDthCauseP.setChecked(false);
                     chkchkDthCauseQ.setChecked(false);
                     chkchkDthCauseR.setChecked(false);
                     chkchkDthCauseS.setChecked(false);
                     chkchkDthCauseT.setChecked(false);
                     chkchkDthCauseX.setChecked(false);
                     chkchkDthCauseZ.setChecked(false);
                 }
             }
         });
         secchkDthCauseZ=(LinearLayout)findViewById(R.id.secchkDthCauseZ);
         linechkDthCauseZ=(View)findViewById(R.id.linechkDthCauseZ);
         VlblchkDthCauseZ=(TextView) findViewById(R.id.VlblchkDthCauseZ);
         chkchkDthCauseZ=(CheckBox) findViewById(R.id.chkchkDthCauseZ);
         chkchkDthCauseZ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
             {
                 if ( isChecked )
                 {
                     chkchkDthCauseA.setChecked(false);
                     chkchkDthCauseB.setChecked(false);
                     chkchkDthCauseC.setChecked(false);
                     chkchkDthCauseD.setChecked(false);
                     chkchkDthCauseE.setChecked(false);
                     chkchkDthCauseF.setChecked(false);
                     chkchkDthCauseG.setChecked(false);
                     chkchkDthCauseH.setChecked(false);
                     chkchkDthCauseI.setChecked(false);
                     chkchkDthCauseJ.setChecked(false);
                     chkchkDthCauseK.setChecked(false);
                     chkchkDthCauseL.setChecked(false);
                     chkchkDthCauseM.setChecked(false);
                     chkchkDthCauseN.setChecked(false);
                     chkchkDthCauseO.setChecked(false);
                     chkchkDthCauseP.setChecked(false);
                     chkchkDthCauseQ.setChecked(false);
                     chkchkDthCauseR.setChecked(false);
                     chkchkDthCauseS.setChecked(false);
                     chkchkDthCauseT.setChecked(false);
                     chkchkDthCauseX.setChecked(false);
                     chkchkDthCauseY.setChecked(false);
                 }
             }
         });
         secDthCauseOth=(LinearLayout)findViewById(R.id.secDthCauseOth);
         lineDthCauseOth=(View)findViewById(R.id.lineDthCauseOth);
         VlblDthCauseOth=(TextView) findViewById(R.id.VlblDthCauseOth);
         txtDthCauseOth=(AutoCompleteTextView) findViewById(R.id.txtDthCauseOth);
         C.setupAutoComplete(TableName,txtDthCauseOth,"DthCauseOth"); //setup autocomplete view by event


         rdogrpDthCertificate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpDthCertificate = new String[]{"0", "1"};
                 for (int i = 0; i < rdogrpDthCertificate.getChildCount(); i++) {
                     rb = (RadioButton) rdogrpDthCertificate.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpDthCertificate[i];
                 }

                 if (rbData.equalsIgnoreCase("1")){
                     secDthHCProf.setVisibility(View.VISIBLE);
                     lineDthHCProf.setVisibility(View.VISIBLE);
                     secchkDthCauseProfA.setVisibility(View.GONE);
                     linechkDthCauseProfA.setVisibility(View.GONE);
                     chkchkDthCauseProfA.setChecked(false);
                     secchkDthCauseProfB.setVisibility(View.GONE);
                     linechkDthCauseProfB.setVisibility(View.GONE);
                     chkchkDthCauseProfB.setChecked(false);
                     secchkDthCauseProfC.setVisibility(View.GONE);
                     linechkDthCauseProfC.setVisibility(View.GONE);
                     chkchkDthCauseProfC.setChecked(false);
                     secchkDthCauseProfD.setVisibility(View.GONE);
                     linechkDthCauseProfD.setVisibility(View.GONE);
                     chkchkDthCauseProfD.setChecked(false);
                     secchkDthCauseProfE.setVisibility(View.GONE);
                     linechkDthCauseProfE.setVisibility(View.GONE);
                     chkchkDthCauseProfE.setChecked(false);
                     secchkDthCauseProfF.setVisibility(View.GONE);
                     linechkDthCauseProfF.setVisibility(View.GONE);
                     chkchkDthCauseProfF.setChecked(false);
                     secchkDthCauseProfG.setVisibility(View.GONE);
                     linechkDthCauseProfG.setVisibility(View.GONE);
                     chkchkDthCauseProfG.setChecked(false);
                     secchkDthCauseProfH.setVisibility(View.GONE);
                     linechkDthCauseProfH.setVisibility(View.GONE);
                     chkchkDthCauseProfH.setChecked(false);
                     secchkDthCauseProfI.setVisibility(View.GONE);
                     linechkDthCauseProfI.setVisibility(View.GONE);
                     chkchkDthCauseProfI.setChecked(false);
                     secchkDthCauseProfJ.setVisibility(View.GONE);
                     linechkDthCauseProfJ.setVisibility(View.GONE);
                     chkchkDthCauseProfJ.setChecked(false);
                     secchkDthCauseProfK.setVisibility(View.GONE);
                     linechkDthCauseProfK.setVisibility(View.GONE);
                     chkchkDthCauseProfK.setChecked(false);
                     secchkDthCauseProfL.setVisibility(View.GONE);
                     linechkDthCauseProfL.setVisibility(View.GONE);
                     chkchkDthCauseProfL.setChecked(false);
                     secchkDthCauseProfM.setVisibility(View.GONE);
                     linechkDthCauseProfM.setVisibility(View.GONE);
                     chkchkDthCauseProfM.setChecked(false);
                     secchkDthCauseProfN.setVisibility(View.GONE);
                     linechkDthCauseProfN.setVisibility(View.GONE);
                     chkchkDthCauseProfN.setChecked(false);
                     secchkDthCauseProfO.setVisibility(View.GONE);
                     linechkDthCauseProfO.setVisibility(View.GONE);
                     chkchkDthCauseProfO.setChecked(false);
                     secchkDthCauseProfP.setVisibility(View.GONE);
                     linechkDthCauseProfP.setVisibility(View.GONE);
                     chkchkDthCauseProfP.setChecked(false);
                     secchkDthCauseProfQ.setVisibility(View.GONE);
                     linechkDthCauseProfQ.setVisibility(View.GONE);
                     chkchkDthCauseProfQ.setChecked(false);
                     secchkDthCauseProfR.setVisibility(View.GONE);
                     linechkDthCauseProfR.setVisibility(View.GONE);
                     chkchkDthCauseProfR.setChecked(false);
                     secchkDthCauseProfS.setVisibility(View.GONE);
                     linechkDthCauseProfS.setVisibility(View.GONE);
                     chkchkDthCauseProfS.setChecked(false);
                     secchkDthCauseProfT.setVisibility(View.GONE);
                     linechkDthCauseProfT.setVisibility(View.GONE);
                     chkchkDthCauseProfT.setChecked(false);
                     secchkDthCauseProfX.setVisibility(View.GONE);
                     linechkDthCauseProfX.setVisibility(View.GONE);
                     chkchkDthCauseProfX.setChecked(false);
                     secchkDthCauseProfY.setVisibility(View.GONE);
                     linechkDthCauseProfY.setVisibility(View.GONE);
                     chkchkDthCauseProfY.setChecked(false);
                     secchkDthCauseProfZ.setVisibility(View.GONE);
                     linechkDthCauseProfZ.setVisibility(View.GONE);
                     chkchkDthCauseProfZ.setChecked(false);
                     secDthCauseOthProf.setVisibility(View.GONE);
                     lineDthCauseOthProf.setVisibility(View.GONE);
                     txtDthCauseOthProf.setText("");
//                     seclbl02.setVisibility(View.VISIBLE);
//                     linelbl02.setVisibility(View.VISIBLE);
//                     secchkDthCauseA.setVisibility(View.VISIBLE);
//                     linechkDthCauseA.setVisibility(View.VISIBLE);
//                     secchkDthCauseB.setVisibility(View.VISIBLE);
//                     linechkDthCauseB.setVisibility(View.VISIBLE);
//                     secchkDthCauseC.setVisibility(View.VISIBLE);
//                     linechkDthCauseC.setVisibility(View.VISIBLE);
//                     secchkDthCauseD.setVisibility(View.VISIBLE);
//                     linechkDthCauseD.setVisibility(View.VISIBLE);
//                     secchkDthCauseE.setVisibility(View.VISIBLE);
//                     linechkDthCauseE.setVisibility(View.VISIBLE);
//                     secchkDthCauseF.setVisibility(View.VISIBLE);
//                     linechkDthCauseF.setVisibility(View.VISIBLE);
//                     secchkDthCauseG.setVisibility(View.VISIBLE);
//                     linechkDthCauseG.setVisibility(View.VISIBLE);
//                     secchkDthCauseH.setVisibility(View.VISIBLE);
//                     linechkDthCauseH.setVisibility(View.VISIBLE);
//                     secchkDthCauseI.setVisibility(View.VISIBLE);
//                     linechkDthCauseI.setVisibility(View.VISIBLE);
//                     secchkDthCauseJ.setVisibility(View.VISIBLE);
//                     linechkDthCauseJ.setVisibility(View.VISIBLE);
//                     secchkDthCauseK.setVisibility(View.VISIBLE);
//                     linechkDthCauseK.setVisibility(View.VISIBLE);
//                     secchkDthCauseL.setVisibility(View.VISIBLE);
//                     linechkDthCauseL.setVisibility(View.VISIBLE);
//                     secchkDthCauseM.setVisibility(View.VISIBLE);
//                     linechkDthCauseM.setVisibility(View.VISIBLE);
//                     secchkDthCauseN.setVisibility(View.VISIBLE);
//                     linechkDthCauseN.setVisibility(View.VISIBLE);
//                     secchkDthCauseO.setVisibility(View.VISIBLE);
//                     linechkDthCauseO.setVisibility(View.VISIBLE);
//                     secchkDthCauseP.setVisibility(View.VISIBLE);
//                     linechkDthCauseP.setVisibility(View.VISIBLE);
//                     secchkDthCauseQ.setVisibility(View.VISIBLE);
//                     linechkDthCauseQ.setVisibility(View.VISIBLE);
//                     secchkDthCauseR.setVisibility(View.VISIBLE);
//                     linechkDthCauseR.setVisibility(View.VISIBLE);
//                     secchkDthCauseS.setVisibility(View.VISIBLE);
//                     linechkDthCauseS.setVisibility(View.VISIBLE);
//                     secchkDthCauseT.setVisibility(View.VISIBLE);
//                     linechkDthCauseT.setVisibility(View.VISIBLE);
//                     secchkDthCauseX.setVisibility(View.VISIBLE);
//                     linechkDthCauseX.setVisibility(View.VISIBLE);
//                     secchkDthCauseY.setVisibility(View.VISIBLE);
//                     linechkDthCauseY.setVisibility(View.VISIBLE);
//                     secchkDthCauseZ.setVisibility(View.VISIBLE);
//                     linechkDthCauseZ.setVisibility(View.VISIBLE);
                 }
                 else{
                     secDthHCProf.setVisibility(View.GONE);
                     lineDthHCProf.setVisibility(View.GONE);
                     rdogrpDthHCProf.clearCheck();
                     secchkDthCauseProfA.setVisibility(View.GONE);
                     linechkDthCauseProfA.setVisibility(View.GONE);
                     chkchkDthCauseProfA.setChecked(false);
                     secchkDthCauseProfB.setVisibility(View.GONE);
                     linechkDthCauseProfB.setVisibility(View.GONE);
                     chkchkDthCauseProfB.setChecked(false);
                     secchkDthCauseProfC.setVisibility(View.GONE);
                     linechkDthCauseProfC.setVisibility(View.GONE);
                     chkchkDthCauseProfC.setChecked(false);
                     secchkDthCauseProfD.setVisibility(View.GONE);
                     linechkDthCauseProfD.setVisibility(View.GONE);
                     chkchkDthCauseProfD.setChecked(false);
                     secchkDthCauseProfE.setVisibility(View.GONE);
                     linechkDthCauseProfE.setVisibility(View.GONE);
                     chkchkDthCauseProfE.setChecked(false);
                     secchkDthCauseProfF.setVisibility(View.GONE);
                     linechkDthCauseProfF.setVisibility(View.GONE);
                     chkchkDthCauseProfF.setChecked(false);
                     secchkDthCauseProfG.setVisibility(View.GONE);
                     linechkDthCauseProfG.setVisibility(View.GONE);
                     chkchkDthCauseProfG.setChecked(false);
                     secchkDthCauseProfH.setVisibility(View.GONE);
                     linechkDthCauseProfH.setVisibility(View.GONE);
                     chkchkDthCauseProfH.setChecked(false);
                     secchkDthCauseProfI.setVisibility(View.GONE);
                     linechkDthCauseProfI.setVisibility(View.GONE);
                     chkchkDthCauseProfI.setChecked(false);
                     secchkDthCauseProfJ.setVisibility(View.GONE);
                     linechkDthCauseProfJ.setVisibility(View.GONE);
                     chkchkDthCauseProfJ.setChecked(false);
                     secchkDthCauseProfK.setVisibility(View.GONE);
                     linechkDthCauseProfK.setVisibility(View.GONE);
                     chkchkDthCauseProfK.setChecked(false);
                     secchkDthCauseProfL.setVisibility(View.GONE);
                     linechkDthCauseProfL.setVisibility(View.GONE);
                     chkchkDthCauseProfL.setChecked(false);
                     secchkDthCauseProfM.setVisibility(View.GONE);
                     linechkDthCauseProfM.setVisibility(View.GONE);
                     chkchkDthCauseProfM.setChecked(false);
                     secchkDthCauseProfN.setVisibility(View.GONE);
                     linechkDthCauseProfN.setVisibility(View.GONE);
                     chkchkDthCauseProfN.setChecked(false);
                     secchkDthCauseProfO.setVisibility(View.GONE);
                     linechkDthCauseProfO.setVisibility(View.GONE);
                     chkchkDthCauseProfO.setChecked(false);
                     secchkDthCauseProfP.setVisibility(View.GONE);
                     linechkDthCauseProfP.setVisibility(View.GONE);
                     chkchkDthCauseProfP.setChecked(false);
                     secchkDthCauseProfQ.setVisibility(View.GONE);
                     linechkDthCauseProfQ.setVisibility(View.GONE);
                     chkchkDthCauseProfQ.setChecked(false);
                     secchkDthCauseProfR.setVisibility(View.GONE);
                     linechkDthCauseProfR.setVisibility(View.GONE);
                     chkchkDthCauseProfR.setChecked(false);
                     secchkDthCauseProfS.setVisibility(View.GONE);
                     linechkDthCauseProfS.setVisibility(View.GONE);
                     chkchkDthCauseProfS.setChecked(false);
                     secchkDthCauseProfT.setVisibility(View.GONE);
                     linechkDthCauseProfT.setVisibility(View.GONE);
                     chkchkDthCauseProfT.setChecked(false);
                     secchkDthCauseProfX.setVisibility(View.GONE);
                     linechkDthCauseProfX.setVisibility(View.GONE);
                     chkchkDthCauseProfX.setChecked(false);
                     secchkDthCauseProfY.setVisibility(View.GONE);
                     linechkDthCauseProfY.setVisibility(View.GONE);
                     chkchkDthCauseProfY.setChecked(false);
                     secchkDthCauseProfZ.setVisibility(View.GONE);
                     linechkDthCauseProfZ.setVisibility(View.GONE);
                     chkchkDthCauseProfZ.setChecked(false);
                     secDthCauseOthProf.setVisibility(View.GONE);
                     lineDthCauseOthProf.setVisibility(View.GONE);
                     txtDthCauseOthProf.setText("");
//                     seclbl02.setVisibility(View.GONE);
//                     linelbl02.setVisibility(View.GONE);
//                     secchkDthCauseA.setVisibility(View.GONE);
//                     linechkDthCauseA.setVisibility(View.GONE);
//                     chkchkDthCauseA.setChecked(false);
//                     secchkDthCauseB.setVisibility(View.GONE);
//                     linechkDthCauseB.setVisibility(View.GONE);
//                     chkchkDthCauseB.setChecked(false);
//                     secchkDthCauseC.setVisibility(View.GONE);
//                     linechkDthCauseC.setVisibility(View.GONE);
//                     chkchkDthCauseC.setChecked(false);
//                     secchkDthCauseD.setVisibility(View.GONE);
//                     linechkDthCauseD.setVisibility(View.GONE);
//                     chkchkDthCauseD.setChecked(false);
//                     secchkDthCauseE.setVisibility(View.GONE);
//                     linechkDthCauseE.setVisibility(View.GONE);
//                     chkchkDthCauseE.setChecked(false);
//                     secchkDthCauseF.setVisibility(View.GONE);
//                     linechkDthCauseF.setVisibility(View.GONE);
//                     chkchkDthCauseF.setChecked(false);
//                     secchkDthCauseG.setVisibility(View.GONE);
//                     linechkDthCauseG.setVisibility(View.GONE);
//                     chkchkDthCauseG.setChecked(false);
//                     secchkDthCauseH.setVisibility(View.GONE);
//                     linechkDthCauseH.setVisibility(View.GONE);
//                     chkchkDthCauseH.setChecked(false);
//                     secchkDthCauseI.setVisibility(View.GONE);
//                     linechkDthCauseI.setVisibility(View.GONE);
//                     chkchkDthCauseI.setChecked(false);
//                     secchkDthCauseJ.setVisibility(View.GONE);
//                     linechkDthCauseJ.setVisibility(View.GONE);
//                     chkchkDthCauseJ.setChecked(false);
//                     secchkDthCauseK.setVisibility(View.GONE);
//                     linechkDthCauseK.setVisibility(View.GONE);
//                     chkchkDthCauseK.setChecked(false);
//                     secchkDthCauseL.setVisibility(View.GONE);
//                     linechkDthCauseL.setVisibility(View.GONE);
//                     chkchkDthCauseL.setChecked(false);
//                     secchkDthCauseM.setVisibility(View.GONE);
//                     linechkDthCauseM.setVisibility(View.GONE);
//                     chkchkDthCauseM.setChecked(false);
//                     secchkDthCauseN.setVisibility(View.GONE);
//                     linechkDthCauseN.setVisibility(View.GONE);
//                     chkchkDthCauseN.setChecked(false);
//                     secchkDthCauseO.setVisibility(View.GONE);
//                     linechkDthCauseO.setVisibility(View.GONE);
//                     chkchkDthCauseO.setChecked(false);
//                     secchkDthCauseP.setVisibility(View.GONE);
//                     linechkDthCauseP.setVisibility(View.GONE);
//                     chkchkDthCauseP.setChecked(false);
//                     secchkDthCauseQ.setVisibility(View.GONE);
//                     linechkDthCauseQ.setVisibility(View.GONE);
//                     chkchkDthCauseQ.setChecked(false);
//                     secchkDthCauseR.setVisibility(View.GONE);
//                     linechkDthCauseR.setVisibility(View.GONE);
//                     chkchkDthCauseR.setChecked(false);
//                     secchkDthCauseS.setVisibility(View.GONE);
//                     linechkDthCauseS.setVisibility(View.GONE);
//                     chkchkDthCauseS.setChecked(false);
//                     secchkDthCauseT.setVisibility(View.GONE);
//                     linechkDthCauseT.setVisibility(View.GONE);
//                     chkchkDthCauseT.setChecked(false);
//                     secchkDthCauseX.setVisibility(View.GONE);
//                     linechkDthCauseX.setVisibility(View.GONE);
//                     chkchkDthCauseX.setChecked(false);
//                     secchkDthCauseY.setVisibility(View.GONE);
//                     linechkDthCauseY.setVisibility(View.GONE);
//                     chkchkDthCauseY.setChecked(false);
//                     secchkDthCauseZ.setVisibility(View.GONE);
//                     linechkDthCauseZ.setVisibility(View.GONE);
//                     chkchkDthCauseZ.setChecked(false);
//                     secDthCauseOth.setVisibility(View.GONE);
//                     lineDthCauseOth.setVisibility(View.GONE);
//                     txtDthCauseOth.setText("");
                 }
             }
         });

         secDthHCProf=(LinearLayout)findViewById(R.id.secDthHCProf);
         lineDthHCProf=(View)findViewById(R.id.lineDthHCProf);
         VlblDthHCProf = (TextView) findViewById(R.id.VlblDthHCProf);
         rdogrpDthHCProf = (RadioGroup) findViewById(R.id.rdogrpDthHCProf);
         rdoDthHCProf1 = (RadioButton) findViewById(R.id.rdoDthHCProf1);
         rdoDthHCProf2 = (RadioButton) findViewById(R.id.rdoDthHCProf2);
         rdoDthHCProf3 = (RadioButton) findViewById(R.id.rdoDthHCProf3);
         rdoDthHCProf4 = (RadioButton) findViewById(R.id.rdoDthHCProf4);
         seclbl03=(LinearLayout)findViewById(R.id.seclbl03);
         linelbl03=(View)findViewById(R.id.linelbl03);
         secchkDthCauseProfA=(LinearLayout)findViewById(R.id.secchkDthCauseProfA);
         linechkDthCauseProfA=(View)findViewById(R.id.linechkDthCauseProfA);
         VlblchkDthCauseProfA=(TextView) findViewById(R.id.VlblchkDthCauseProfA);
         chkchkDthCauseProfA=(CheckBox) findViewById(R.id.chkchkDthCauseProfA);
         secchkDthCauseProfB=(LinearLayout)findViewById(R.id.secchkDthCauseProfB);
         linechkDthCauseProfB=(View)findViewById(R.id.linechkDthCauseProfB);
         VlblchkDthCauseProfB=(TextView) findViewById(R.id.VlblchkDthCauseProfB);
         chkchkDthCauseProfB=(CheckBox) findViewById(R.id.chkchkDthCauseProfB);
         secchkDthCauseProfC=(LinearLayout)findViewById(R.id.secchkDthCauseProfC);
         linechkDthCauseProfC=(View)findViewById(R.id.linechkDthCauseProfC);
         VlblchkDthCauseProfC=(TextView) findViewById(R.id.VlblchkDthCauseProfC);
         chkchkDthCauseProfC=(CheckBox) findViewById(R.id.chkchkDthCauseProfC);
         secchkDthCauseProfD=(LinearLayout)findViewById(R.id.secchkDthCauseProfD);
         linechkDthCauseProfD=(View)findViewById(R.id.linechkDthCauseProfD);
         VlblchkDthCauseProfD=(TextView) findViewById(R.id.VlblchkDthCauseProfD);
         chkchkDthCauseProfD=(CheckBox) findViewById(R.id.chkchkDthCauseProfD);
         secchkDthCauseProfE=(LinearLayout)findViewById(R.id.secchkDthCauseProfE);
         linechkDthCauseProfE=(View)findViewById(R.id.linechkDthCauseProfE);
         VlblchkDthCauseProfE=(TextView) findViewById(R.id.VlblchkDthCauseProfE);
         chkchkDthCauseProfE=(CheckBox) findViewById(R.id.chkchkDthCauseProfE);
         secchkDthCauseProfF=(LinearLayout)findViewById(R.id.secchkDthCauseProfF);
         linechkDthCauseProfF=(View)findViewById(R.id.linechkDthCauseProfF);
         VlblchkDthCauseProfF=(TextView) findViewById(R.id.VlblchkDthCauseProfF);
         chkchkDthCauseProfF=(CheckBox) findViewById(R.id.chkchkDthCauseProfF);
         secchkDthCauseProfG=(LinearLayout)findViewById(R.id.secchkDthCauseProfG);
         linechkDthCauseProfG=(View)findViewById(R.id.linechkDthCauseProfG);
         VlblchkDthCauseProfG=(TextView) findViewById(R.id.VlblchkDthCauseProfG);
         chkchkDthCauseProfG=(CheckBox) findViewById(R.id.chkchkDthCauseProfG);
         secchkDthCauseProfH=(LinearLayout)findViewById(R.id.secchkDthCauseProfH);
         linechkDthCauseProfH=(View)findViewById(R.id.linechkDthCauseProfH);
         VlblchkDthCauseProfH=(TextView) findViewById(R.id.VlblchkDthCauseProfH);
         chkchkDthCauseProfH=(CheckBox) findViewById(R.id.chkchkDthCauseProfH);
         secchkDthCauseProfI=(LinearLayout)findViewById(R.id.secchkDthCauseProfI);
         linechkDthCauseProfI=(View)findViewById(R.id.linechkDthCauseProfI);
         VlblchkDthCauseProfI=(TextView) findViewById(R.id.VlblchkDthCauseProfI);
         chkchkDthCauseProfI=(CheckBox) findViewById(R.id.chkchkDthCauseProfI);
         secchkDthCauseProfJ=(LinearLayout)findViewById(R.id.secchkDthCauseProfJ);
         linechkDthCauseProfJ=(View)findViewById(R.id.linechkDthCauseProfJ);
         VlblchkDthCauseProfJ=(TextView) findViewById(R.id.VlblchkDthCauseProfJ);
         chkchkDthCauseProfJ=(CheckBox) findViewById(R.id.chkchkDthCauseProfJ);
         secchkDthCauseProfK=(LinearLayout)findViewById(R.id.secchkDthCauseProfK);
         linechkDthCauseProfK=(View)findViewById(R.id.linechkDthCauseProfK);
         VlblchkDthCauseProfK=(TextView) findViewById(R.id.VlblchkDthCauseProfK);
         chkchkDthCauseProfK=(CheckBox) findViewById(R.id.chkchkDthCauseProfK);
         secchkDthCauseProfL=(LinearLayout)findViewById(R.id.secchkDthCauseProfL);
         linechkDthCauseProfL=(View)findViewById(R.id.linechkDthCauseProfL);
         VlblchkDthCauseProfL=(TextView) findViewById(R.id.VlblchkDthCauseProfL);
         chkchkDthCauseProfL=(CheckBox) findViewById(R.id.chkchkDthCauseProfL);
         secchkDthCauseProfM=(LinearLayout)findViewById(R.id.secchkDthCauseProfM);
         linechkDthCauseProfM=(View)findViewById(R.id.linechkDthCauseProfM);
         VlblchkDthCauseProfM=(TextView) findViewById(R.id.VlblchkDthCauseProfM);
         chkchkDthCauseProfM=(CheckBox) findViewById(R.id.chkchkDthCauseProfM);
         secchkDthCauseProfN=(LinearLayout)findViewById(R.id.secchkDthCauseProfN);
         linechkDthCauseProfN=(View)findViewById(R.id.linechkDthCauseProfN);
         VlblchkDthCauseProfN=(TextView) findViewById(R.id.VlblchkDthCauseProfN);
         chkchkDthCauseProfN=(CheckBox) findViewById(R.id.chkchkDthCauseProfN);
         secchkDthCauseProfO=(LinearLayout)findViewById(R.id.secchkDthCauseProfO);
         linechkDthCauseProfO=(View)findViewById(R.id.linechkDthCauseProfO);
         VlblchkDthCauseProfO=(TextView) findViewById(R.id.VlblchkDthCauseProfO);
         chkchkDthCauseProfO=(CheckBox) findViewById(R.id.chkchkDthCauseProfO);
         secchkDthCauseProfP=(LinearLayout)findViewById(R.id.secchkDthCauseProfP);
         linechkDthCauseProfP=(View)findViewById(R.id.linechkDthCauseProfP);
         VlblchkDthCauseProfP=(TextView) findViewById(R.id.VlblchkDthCauseProfP);
         chkchkDthCauseProfP=(CheckBox) findViewById(R.id.chkchkDthCauseProfP);
         secchkDthCauseProfQ=(LinearLayout)findViewById(R.id.secchkDthCauseProfQ);
         linechkDthCauseProfQ=(View)findViewById(R.id.linechkDthCauseProfQ);
         VlblchkDthCauseProfQ=(TextView) findViewById(R.id.VlblchkDthCauseProfQ);
         chkchkDthCauseProfQ=(CheckBox) findViewById(R.id.chkchkDthCauseProfQ);
         secchkDthCauseProfR=(LinearLayout)findViewById(R.id.secchkDthCauseProfR);
         linechkDthCauseProfR=(View)findViewById(R.id.linechkDthCauseProfR);
         VlblchkDthCauseProfR=(TextView) findViewById(R.id.VlblchkDthCauseProfR);
         chkchkDthCauseProfR=(CheckBox) findViewById(R.id.chkchkDthCauseProfR);
         secchkDthCauseProfS=(LinearLayout)findViewById(R.id.secchkDthCauseProfS);
         linechkDthCauseProfS=(View)findViewById(R.id.linechkDthCauseProfS);
         VlblchkDthCauseProfS=(TextView) findViewById(R.id.VlblchkDthCauseProfS);
         chkchkDthCauseProfS=(CheckBox) findViewById(R.id.chkchkDthCauseProfS);
         secchkDthCauseProfT=(LinearLayout)findViewById(R.id.secchkDthCauseProfT);
         linechkDthCauseProfT=(View)findViewById(R.id.linechkDthCauseProfT);
         VlblchkDthCauseProfT=(TextView) findViewById(R.id.VlblchkDthCauseProfT);
         chkchkDthCauseProfT=(CheckBox) findViewById(R.id.chkchkDthCauseProfT);
         secchkDthCauseProfX=(LinearLayout)findViewById(R.id.secchkDthCauseProfX);
         linechkDthCauseProfX=(View)findViewById(R.id.linechkDthCauseProfX);
         VlblchkDthCauseProfX=(TextView) findViewById(R.id.VlblchkDthCauseProfX);
         chkchkDthCauseProfX=(CheckBox) findViewById(R.id.chkchkDthCauseProfX);
         chkchkDthCauseProfX.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
             {
                 if ( isChecked )
                 {
                     secDthCauseOthProf.setVisibility(View.VISIBLE);
                     lineDthCauseOthProf.setVisibility(View.VISIBLE);
                 }
                 else {
                     secDthCauseOthProf.setVisibility(View.GONE);
                     lineDthCauseOthProf.setVisibility(View.GONE);
                     txtDthCauseOthProf.setText("");
                 }
             }
         });
         secchkDthCauseProfY=(LinearLayout)findViewById(R.id.secchkDthCauseProfY);
         linechkDthCauseProfY=(View)findViewById(R.id.linechkDthCauseProfY);
         VlblchkDthCauseProfY=(TextView) findViewById(R.id.VlblchkDthCauseProfY);
         chkchkDthCauseProfY=(CheckBox) findViewById(R.id.chkchkDthCauseProfY);
         chkchkDthCauseProfY.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
             {
                 if ( isChecked )
                 {
                     chkchkDthCauseProfA.setChecked(false);
                     chkchkDthCauseProfB.setChecked(false);
                     chkchkDthCauseProfC.setChecked(false);
                     chkchkDthCauseProfD.setChecked(false);
                     chkchkDthCauseProfE.setChecked(false);
                     chkchkDthCauseProfF.setChecked(false);
                     chkchkDthCauseProfG.setChecked(false);
                     chkchkDthCauseProfH.setChecked(false);
                     chkchkDthCauseProfI.setChecked(false);
                     chkchkDthCauseProfJ.setChecked(false);
                     chkchkDthCauseProfK.setChecked(false);
                     chkchkDthCauseProfL.setChecked(false);
                     chkchkDthCauseProfM.setChecked(false);
                     chkchkDthCauseProfN.setChecked(false);
                     chkchkDthCauseProfO.setChecked(false);
                     chkchkDthCauseProfP.setChecked(false);
                     chkchkDthCauseProfQ.setChecked(false);
                     chkchkDthCauseProfR.setChecked(false);
                     chkchkDthCauseProfS.setChecked(false);
                     chkchkDthCauseProfT.setChecked(false);
                     chkchkDthCauseProfX.setChecked(false);
                     chkchkDthCauseProfZ.setChecked(false);
                 }
             }
         });
         secchkDthCauseProfZ=(LinearLayout)findViewById(R.id.secchkDthCauseProfZ);
         linechkDthCauseProfZ=(View)findViewById(R.id.linechkDthCauseProfZ);
         VlblchkDthCauseProfZ=(TextView) findViewById(R.id.VlblchkDthCauseProfZ);
         chkchkDthCauseProfZ=(CheckBox) findViewById(R.id.chkchkDthCauseProfZ);
         chkchkDthCauseProfZ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
             {
                 if ( isChecked )
                 {
                     chkchkDthCauseProfA.setChecked(false);
                     chkchkDthCauseProfB.setChecked(false);
                     chkchkDthCauseProfC.setChecked(false);
                     chkchkDthCauseProfD.setChecked(false);
                     chkchkDthCauseProfE.setChecked(false);
                     chkchkDthCauseProfF.setChecked(false);
                     chkchkDthCauseProfG.setChecked(false);
                     chkchkDthCauseProfH.setChecked(false);
                     chkchkDthCauseProfI.setChecked(false);
                     chkchkDthCauseProfJ.setChecked(false);
                     chkchkDthCauseProfK.setChecked(false);
                     chkchkDthCauseProfL.setChecked(false);
                     chkchkDthCauseProfM.setChecked(false);
                     chkchkDthCauseProfN.setChecked(false);
                     chkchkDthCauseProfO.setChecked(false);
                     chkchkDthCauseProfP.setChecked(false);
                     chkchkDthCauseProfQ.setChecked(false);
                     chkchkDthCauseProfR.setChecked(false);
                     chkchkDthCauseProfS.setChecked(false);
                     chkchkDthCauseProfT.setChecked(false);
                     chkchkDthCauseProfX.setChecked(false);
                     chkchkDthCauseProfY.setChecked(false);
                 }
             }
         });
         secDthCauseOthProf=(LinearLayout)findViewById(R.id.secDthCauseOthProf);
         lineDthCauseOthProf=(View)findViewById(R.id.lineDthCauseOthProf);
         VlblDthCauseOthProf=(TextView) findViewById(R.id.VlblDthCauseOthProf);
         txtDthCauseOthProf=(AutoCompleteTextView) findViewById(R.id.txtDthCauseOthProf);
         C.setupAutoComplete(TableName,txtDthCauseOthProf,"DthCauseOthProf"); //setup autocomplete view by event


         rdogrpDthHCProf.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpDthHCProf = new String[]{"0", "1", "8", "9"};
                 for (int i = 0; i < rdogrpDthHCProf.getChildCount(); i++) {
                     rb = (RadioButton) rdogrpDthHCProf.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpDthHCProf[i];
                 }

                 if (rbData.equalsIgnoreCase("1")){
                     seclbl03.setVisibility(View.VISIBLE);
                     linelbl03.setVisibility(View.VISIBLE);
                     secchkDthCauseProfA.setVisibility(View.VISIBLE);
                     linechkDthCauseProfA.setVisibility(View.VISIBLE);
                     secchkDthCauseProfB.setVisibility(View.VISIBLE);
                     linechkDthCauseProfB.setVisibility(View.VISIBLE);
                     secchkDthCauseProfC.setVisibility(View.VISIBLE);
                     linechkDthCauseProfC.setVisibility(View.VISIBLE);
                     secchkDthCauseProfD.setVisibility(View.VISIBLE);
                     linechkDthCauseProfD.setVisibility(View.VISIBLE);
                     secchkDthCauseProfE.setVisibility(View.VISIBLE);
                     linechkDthCauseProfE.setVisibility(View.VISIBLE);
                     secchkDthCauseProfF.setVisibility(View.VISIBLE);
                     linechkDthCauseProfF.setVisibility(View.VISIBLE);
                     secchkDthCauseProfG.setVisibility(View.VISIBLE);
                     linechkDthCauseProfG.setVisibility(View.VISIBLE);
                     secchkDthCauseProfH.setVisibility(View.VISIBLE);
                     linechkDthCauseProfH.setVisibility(View.VISIBLE);
                     secchkDthCauseProfI.setVisibility(View.VISIBLE);
                     linechkDthCauseProfI.setVisibility(View.VISIBLE);
                     secchkDthCauseProfJ.setVisibility(View.VISIBLE);
                     linechkDthCauseProfJ.setVisibility(View.VISIBLE);
                     secchkDthCauseProfK.setVisibility(View.VISIBLE);
                     linechkDthCauseProfK.setVisibility(View.VISIBLE);
                     secchkDthCauseProfL.setVisibility(View.VISIBLE);
                     linechkDthCauseProfL.setVisibility(View.VISIBLE);
                     secchkDthCauseProfM.setVisibility(View.VISIBLE);
                     linechkDthCauseProfM.setVisibility(View.VISIBLE);
                     secchkDthCauseProfN.setVisibility(View.VISIBLE);
                     linechkDthCauseProfN.setVisibility(View.VISIBLE);
                     secchkDthCauseProfO.setVisibility(View.VISIBLE);
                     linechkDthCauseProfO.setVisibility(View.VISIBLE);
                     secchkDthCauseProfP.setVisibility(View.VISIBLE);
                     linechkDthCauseProfP.setVisibility(View.VISIBLE);
                     secchkDthCauseProfQ.setVisibility(View.VISIBLE);
                     linechkDthCauseProfQ.setVisibility(View.VISIBLE);
                     secchkDthCauseProfR.setVisibility(View.VISIBLE);
                     linechkDthCauseProfR.setVisibility(View.VISIBLE);
                     secchkDthCauseProfS.setVisibility(View.VISIBLE);
                     linechkDthCauseProfS.setVisibility(View.VISIBLE);
                     secchkDthCauseProfT.setVisibility(View.VISIBLE);
                     linechkDthCauseProfT.setVisibility(View.VISIBLE);
                     secchkDthCauseProfX.setVisibility(View.VISIBLE);
                     linechkDthCauseProfX.setVisibility(View.VISIBLE);
                     secchkDthCauseProfY.setVisibility(View.VISIBLE);
                     linechkDthCauseProfY.setVisibility(View.VISIBLE);
                     secchkDthCauseProfZ.setVisibility(View.VISIBLE);
                     linechkDthCauseProfZ.setVisibility(View.VISIBLE);
                 }
                 else{
                     seclbl03.setVisibility(View.GONE);
                     linelbl03.setVisibility(View.GONE);
                     secchkDthCauseProfA.setVisibility(View.GONE);
                     linechkDthCauseProfA.setVisibility(View.GONE);
                     chkchkDthCauseProfA.setChecked(false);
                     secchkDthCauseProfB.setVisibility(View.GONE);
                     linechkDthCauseProfB.setVisibility(View.GONE);
                     chkchkDthCauseProfB.setChecked(false);
                     secchkDthCauseProfC.setVisibility(View.GONE);
                     linechkDthCauseProfC.setVisibility(View.GONE);
                     chkchkDthCauseProfC.setChecked(false);
                     secchkDthCauseProfD.setVisibility(View.GONE);
                     linechkDthCauseProfD.setVisibility(View.GONE);
                     chkchkDthCauseProfD.setChecked(false);
                     secchkDthCauseProfE.setVisibility(View.GONE);
                     linechkDthCauseProfE.setVisibility(View.GONE);
                     chkchkDthCauseProfE.setChecked(false);
                     secchkDthCauseProfF.setVisibility(View.GONE);
                     linechkDthCauseProfF.setVisibility(View.GONE);
                     chkchkDthCauseProfF.setChecked(false);
                     secchkDthCauseProfG.setVisibility(View.GONE);
                     linechkDthCauseProfG.setVisibility(View.GONE);
                     chkchkDthCauseProfG.setChecked(false);
                     secchkDthCauseProfH.setVisibility(View.GONE);
                     linechkDthCauseProfH.setVisibility(View.GONE);
                     chkchkDthCauseProfH.setChecked(false);
                     secchkDthCauseProfI.setVisibility(View.GONE);
                     linechkDthCauseProfI.setVisibility(View.GONE);
                     chkchkDthCauseProfI.setChecked(false);
                     secchkDthCauseProfJ.setVisibility(View.GONE);
                     linechkDthCauseProfJ.setVisibility(View.GONE);
                     chkchkDthCauseProfJ.setChecked(false);
                     secchkDthCauseProfK.setVisibility(View.GONE);
                     linechkDthCauseProfK.setVisibility(View.GONE);
                     chkchkDthCauseProfK.setChecked(false);
                     secchkDthCauseProfL.setVisibility(View.GONE);
                     linechkDthCauseProfL.setVisibility(View.GONE);
                     chkchkDthCauseProfL.setChecked(false);
                     secchkDthCauseProfM.setVisibility(View.GONE);
                     linechkDthCauseProfM.setVisibility(View.GONE);
                     chkchkDthCauseProfM.setChecked(false);
                     secchkDthCauseProfN.setVisibility(View.GONE);
                     linechkDthCauseProfN.setVisibility(View.GONE);
                     chkchkDthCauseProfN.setChecked(false);
                     secchkDthCauseProfO.setVisibility(View.GONE);
                     linechkDthCauseProfO.setVisibility(View.GONE);
                     chkchkDthCauseProfO.setChecked(false);
                     secchkDthCauseProfP.setVisibility(View.GONE);
                     linechkDthCauseProfP.setVisibility(View.GONE);
                     chkchkDthCauseProfP.setChecked(false);
                     secchkDthCauseProfQ.setVisibility(View.GONE);
                     linechkDthCauseProfQ.setVisibility(View.GONE);
                     chkchkDthCauseProfQ.setChecked(false);
                     secchkDthCauseProfR.setVisibility(View.GONE);
                     linechkDthCauseProfR.setVisibility(View.GONE);
                     chkchkDthCauseProfR.setChecked(false);
                     secchkDthCauseProfS.setVisibility(View.GONE);
                     linechkDthCauseProfS.setVisibility(View.GONE);
                     chkchkDthCauseProfS.setChecked(false);
                     secchkDthCauseProfT.setVisibility(View.GONE);
                     linechkDthCauseProfT.setVisibility(View.GONE);
                     chkchkDthCauseProfT.setChecked(false);
                     secchkDthCauseProfX.setVisibility(View.GONE);
                     linechkDthCauseProfX.setVisibility(View.GONE);
                     chkchkDthCauseProfX.setChecked(false);
                     secchkDthCauseProfY.setVisibility(View.GONE);
                     linechkDthCauseProfY.setVisibility(View.GONE);
                     chkchkDthCauseProfY.setChecked(false);
                     secchkDthCauseProfZ.setVisibility(View.GONE);
                     linechkDthCauseProfZ.setVisibility(View.GONE);
                     chkchkDthCauseProfZ.setChecked(false);
                     secDthCauseOthProf.setVisibility(View.GONE);
                     lineDthCauseOthProf.setVisibility(View.GONE);
                     txtDthCauseOthProf.setText("");
                 }
             }
         });

         secDthCategory=(LinearLayout)findViewById(R.id.secDthCategory);
         lineDthCategory=(View)findViewById(R.id.lineDthCategory);
         VlblDthCategory=(TextView) findViewById(R.id.VlblDthCategory);
         spnDthCategory=(Spinner) findViewById(R.id.spnDthCategory);
         List<String> listDthCategory = new ArrayList<String>();

         listDthCategory.add("");
         listDthCategory.add("1-Child died first day after birth");
         listDthCategory.add("2-Child died first seven days after birth");
         listDthCategory.add("3-Child died 7th and 28th days of birth");
         listDthCategory.add("4-Child died 1st and 12th month of birth");
         listDthCategory.add("5-Child died aged 1 to 5 years");
         listDthCategory.add("6-Deaths other than under fives");
         spnDthCategory.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listDthCategory)));

         secDthEnrolChamps=(LinearLayout)findViewById(R.id.secDthEnrolChamps);
         lineDthEnrolChamps=(View)findViewById(R.id.lineDthEnrolChamps);
         VlblDthEnrolChamps = (TextView) findViewById(R.id.VlblDthEnrolChamps);
         rdogrpDthEnrolChamps = (RadioGroup) findViewById(R.id.rdogrpDthEnrolChamps);
         rdoDthEnrolChamps1 = (RadioButton) findViewById(R.id.rdoDthEnrolChamps1);
         rdoDthEnrolChamps2 = (RadioButton) findViewById(R.id.rdoDthEnrolChamps2);
         rdoDthEnrolChamps3 = (RadioButton) findViewById(R.id.rdoDthEnrolChamps3);
         rdoDthEnrolChamps4 = (RadioButton) findViewById(R.id.rdoDthEnrolChamps4);

         secDthChampsIdKnown=(LinearLayout)findViewById(R.id.secDthChampsIdKnown);
         lineDthChampsIdKnown=(View)findViewById(R.id.lineDthChampsIdKnown);
         VlblDthChampsIdKnown = (TextView) findViewById(R.id.VlblDthChampsIdKnown);
         rdogrpDthChampsIdKnown = (RadioGroup) findViewById(R.id.rdogrpDthChampsIdKnown);
         rdoDthChampsIdKnown1 = (RadioButton) findViewById(R.id.rdoDthChampsIdKnown1);
         rdoDthChampsIdKnown2 = (RadioButton) findViewById(R.id.rdoDthChampsIdKnown2);
         rdoDthChampsIdKnown3 = (RadioButton) findViewById(R.id.rdoDthChampsIdKnown3);
         rdoDthChampsIdKnown4 = (RadioButton) findViewById(R.id.rdoDthChampsIdKnown4);
         secDthChampsId=(LinearLayout)findViewById(R.id.secDthChampsId);
         lineDthChampsId=(View)findViewById(R.id.lineDthChampsId);
         VlblDthChampsId=(TextView) findViewById(R.id.VlblDthChampsId);
         txtDthChampsId=(EditText) findViewById(R.id.txtDthChampsId);

         rdogrpDthEnrolChamps.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                 @Override
                 public void onCheckedChanged(RadioGroup group, int checkedId) {
                     String rbData = "";
                     RadioButton rb;
                     String[] d_rdogrpDthEnrolChamps = new String[]{"0", "1", "8", "9"};
                     for (int i = 0; i < rdogrpDthEnrolChamps.getChildCount(); i++) {
                         rb = (RadioButton) rdogrpDthEnrolChamps.getChildAt(i);
                         if (rb.isChecked()) rbData = d_rdogrpDthEnrolChamps[i];
                     }

                     if (rbData.equalsIgnoreCase("1") & rdoDthChampsIdKnown2.isChecked()){
                         secDthChampsId.setVisibility(View.VISIBLE);
                         lineDthChampsId.setVisibility(View.VISIBLE);
                     }
                     else{
                         secDthChampsId.setVisibility(View.GONE);
                         lineDthChampsId.setVisibility(View.GONE);
                         txtDthChampsId.setText("");
                     }
                 }
             });

         rdogrpDthChampsIdKnown.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpDthChampsIdKnown = new String[]{"0", "1", "8", "9"};
                 for (int i = 0; i < rdogrpDthChampsIdKnown.getChildCount(); i++) {
                     rb = (RadioButton) rdogrpDthChampsIdKnown.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpDthChampsIdKnown[i];
                 }

                 if (rbData.equalsIgnoreCase("1") & rdoDthEnrolChamps2.isChecked()){
                     secDthChampsId.setVisibility(View.VISIBLE);
                     lineDthChampsId.setVisibility(View.VISIBLE);
                 }
                 else{
                     secDthChampsId.setVisibility(View.GONE);
                     lineDthChampsId.setVisibility(View.GONE);
                     txtDthChampsId.setText("");
                 }
             }
         });

         secDthVDate=(LinearLayout)findViewById(R.id.secDthVDate);
         lineDthVDate=(View)findViewById(R.id.lineDthVDate);
         VlblDthVDate=(TextView) findViewById(R.id.VlblDthVDate);
         dtpDthVDate=(EditText) findViewById(R.id.dtpDthVDate);
         dtpDthVDate.setText(VISIT_DATE);
         secDthVDate.setVisibility(View.GONE);

         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         txtRnd.setText(ROUND);
         txtRnd.setEnabled(false);

         secDthNote=(LinearLayout)findViewById(R.id.secDthNote);
         lineDthNote=(View)findViewById(R.id.lineDthNote);
         VlblDthNote=(TextView) findViewById(R.id.VlblDthNote);
         txtDthNote=(EditText) findViewById(R.id.txtDthNote);
         secDthNote1=(LinearLayout)findViewById(R.id.secDthNote1);
         lineDthNote1=(View)findViewById(R.id.lineDthNote1);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Death.this, e.getMessage());
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
         	Connection.MessageBox(Surv_Death.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         tmpDeath_DataModel objSave = new tmpDeath_DataModel();
         objSave.setDeathID(txtDeathID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setMemID(txtMemID.getText().toString());
         objSave.setDthDate(dtpDthDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpDthDate.getText().toString()) : dtpDthDate.getText().toString());
         String[] d_rdogrpDthDateType = new String[] {"1","2","8"};
         objSave.setDthDateType("");
         for (int i = 0; i < rdogrpDthDateType.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDthDateType.getChildAt(i);
             if (rb.isChecked()) objSave.setDthDateType(d_rdogrpDthDateType[i]);
         }

         objSave.setDthTime(txtDthTime.getText().toString());
         String[] d_rdogrpDthTimeType = new String[] {"1","2","8"};
         objSave.setDthTimeType("");
         for (int i = 0; i < rdogrpDthTimeType.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDthTimeType.getChildAt(i);
             if (rb.isChecked()) objSave.setDthTimeType(d_rdogrpDthTimeType[i]);
         }

         //objSave.setDthPlace(spnDthPlace.getSelectedItemPosition() == 0 ? "" : spnDthPlace.getSelectedItem().toString().split("-")[0]);
         objSave.setDthPlace(spnDthPlace.getSelectedItem().toString().split("-")[0]);
         objSave.setDthPlaceOth(txtDthPlaceOth.getText().toString());
         objSave.setDthPlaceFacility(txtDthPlaceFacility.getText().toString());
         String[] d_rdogrpDthAdrsState = new String[] {"1","2"};
         objSave.setDthAdrsState("");
         for (int i = 0; i < rdogrpDthAdrsState.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDthAdrsState.getChildAt(i);
             if (rb.isChecked()) objSave.setDthAdrsState(d_rdogrpDthAdrsState[i]);
         }
         objSave.setDthAdrsAL1(txtDthAdrsAL1.getText().toString());
         objSave.setDthAdrsAL2(txtDthAdrsAL2.getText().toString());
         objSave.setDthAdrsAL3(txtDthAdrsAL3.getText().toString());
         objSave.setDthAdrsAL4(txtDthAdrsAL4.getText().toString());
         objSave.setDthAdrsAL5(txtDthAdrsAL5.getText().toString());
         String[] d_rdogrpDthSick = new String[] {"0","1"};
         objSave.setDthSick("");
         for (int i = 0; i < rdogrpDthSick.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDthSick.getChildAt(i);
             if (rb.isChecked()) objSave.setDthSick(d_rdogrpDthSick[i]);
         }

         String[] d_rdogrpDthCertificate = new String[] {"0","1"};
         objSave.setDthCertificate("");
         for (int i = 0; i < rdogrpDthCertificate.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDthCertificate.getChildAt(i);
             if (rb.isChecked()) objSave.setDthCertificate(d_rdogrpDthCertificate[i]);
         }

         objSave.setchkDthCauseA((chkchkDthCauseA.isChecked() ? "1" : (secchkDthCauseA.isShown() ? "2" : "")));
         objSave.setchkDthCauseB((chkchkDthCauseB.isChecked() ? "1" : (secchkDthCauseB.isShown() ? "2" : "")));
         objSave.setchkDthCauseC((chkchkDthCauseC.isChecked() ? "1" : (secchkDthCauseC.isShown() ? "2" : "")));
         objSave.setchkDthCauseD((chkchkDthCauseD.isChecked() ? "1" : (secchkDthCauseD.isShown() ? "2" : "")));
         objSave.setchkDthCauseE((chkchkDthCauseE.isChecked() ? "1" : (secchkDthCauseE.isShown() ? "2" : "")));
         objSave.setchkDthCauseF((chkchkDthCauseF.isChecked() ? "1" : (secchkDthCauseF.isShown() ? "2" : "")));
         objSave.setchkDthCauseG((chkchkDthCauseG.isChecked() ? "1" : (secchkDthCauseG.isShown() ? "2" : "")));
         objSave.setchkDthCauseH((chkchkDthCauseH.isChecked() ? "1" : (secchkDthCauseH.isShown() ? "2" : "")));
         objSave.setchkDthCauseI((chkchkDthCauseI.isChecked() ? "1" : (secchkDthCauseI.isShown() ? "2" : "")));
         objSave.setchkDthCauseJ((chkchkDthCauseJ.isChecked() ? "1" : (secchkDthCauseJ.isShown() ? "2" : "")));
         objSave.setchkDthCauseK((chkchkDthCauseK.isChecked() ? "1" : (secchkDthCauseK.isShown() ? "2" : "")));
         objSave.setchkDthCauseL((chkchkDthCauseL.isChecked() ? "1" : (secchkDthCauseL.isShown() ? "2" : "")));
         objSave.setchkDthCauseM((chkchkDthCauseM.isChecked() ? "1" : (secchkDthCauseM.isShown() ? "2" : "")));
         objSave.setchkDthCauseN((chkchkDthCauseN.isChecked() ? "1" : (secchkDthCauseN.isShown() ? "2" : "")));
         objSave.setchkDthCauseO((chkchkDthCauseO.isChecked() ? "1" : (secchkDthCauseO.isShown() ? "2" : "")));
         objSave.setchkDthCauseP((chkchkDthCauseP.isChecked() ? "1" : (secchkDthCauseP.isShown() ? "2" : "")));
         objSave.setchkDthCauseQ((chkchkDthCauseQ.isChecked() ? "1" : (secchkDthCauseQ.isShown() ? "2" : "")));
         objSave.setchkDthCauseR((chkchkDthCauseR.isChecked() ? "1" : (secchkDthCauseR.isShown() ? "2" : "")));
         objSave.setchkDthCauseS((chkchkDthCauseS.isChecked() ? "1" : (secchkDthCauseS.isShown() ? "2" : "")));
         objSave.setchkDthCauseT((chkchkDthCauseT.isChecked() ? "1" : (secchkDthCauseT.isShown() ? "2" : "")));
         objSave.setchkDthCauseX((chkchkDthCauseX.isChecked() ? "1" : (secchkDthCauseX.isShown() ? "2" : "")));
         objSave.setchkDthCauseY((chkchkDthCauseY.isChecked() ? "1" : (secchkDthCauseY.isShown() ? "2" : "")));
         objSave.setchkDthCauseZ((chkchkDthCauseZ.isChecked() ? "1" : (secchkDthCauseZ.isShown() ? "2" : "")));
         objSave.setDthCauseOth(txtDthCauseOth.getText().toString());
         String[] d_rdogrpDthHCProf = new String[] {"0","1","8","9"};
         objSave.setDthHCProf("");
         for (int i = 0; i < rdogrpDthHCProf.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDthHCProf.getChildAt(i);
             if (rb.isChecked()) objSave.setDthHCProf(d_rdogrpDthHCProf[i]);
         }

         objSave.setchkDthCauseProfA((chkchkDthCauseProfA.isChecked() ? "1" : (secchkDthCauseProfA.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfB((chkchkDthCauseProfB.isChecked() ? "1" : (secchkDthCauseProfB.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfC((chkchkDthCauseProfC.isChecked() ? "1" : (secchkDthCauseProfC.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfD((chkchkDthCauseProfD.isChecked() ? "1" : (secchkDthCauseProfD.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfE((chkchkDthCauseProfE.isChecked() ? "1" : (secchkDthCauseProfE.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfF((chkchkDthCauseProfF.isChecked() ? "1" : (secchkDthCauseProfF.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfG((chkchkDthCauseProfG.isChecked() ? "1" : (secchkDthCauseProfG.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfH((chkchkDthCauseProfH.isChecked() ? "1" : (secchkDthCauseProfH.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfI((chkchkDthCauseProfI.isChecked() ? "1" : (secchkDthCauseProfI.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfJ((chkchkDthCauseProfJ.isChecked() ? "1" : (secchkDthCauseProfJ.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfK((chkchkDthCauseProfK.isChecked() ? "1" : (secchkDthCauseProfK.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfL((chkchkDthCauseProfL.isChecked() ? "1" : (secchkDthCauseProfL.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfM((chkchkDthCauseProfM.isChecked() ? "1" : (secchkDthCauseProfM.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfN((chkchkDthCauseProfN.isChecked() ? "1" : (secchkDthCauseProfN.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfO((chkchkDthCauseProfO.isChecked() ? "1" : (secchkDthCauseProfO.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfP((chkchkDthCauseProfP.isChecked() ? "1" : (secchkDthCauseProfP.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfQ((chkchkDthCauseProfQ.isChecked() ? "1" : (secchkDthCauseProfQ.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfR((chkchkDthCauseProfR.isChecked() ? "1" : (secchkDthCauseProfR.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfS((chkchkDthCauseProfS.isChecked() ? "1" : (secchkDthCauseProfS.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfT((chkchkDthCauseProfT.isChecked() ? "1" : (secchkDthCauseProfT.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfX((chkchkDthCauseProfX.isChecked() ? "1" : (secchkDthCauseProfX.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfY((chkchkDthCauseProfY.isChecked() ? "1" : (secchkDthCauseProfY.isShown() ? "2" : "")));
         objSave.setchkDthCauseProfZ((chkchkDthCauseProfZ.isChecked() ? "1" : (secchkDthCauseProfZ.isShown() ? "2" : "")));
         objSave.setDthCauseOthProf(txtDthCauseOthProf.getText().toString());
         objSave.setDthCategory(spnDthCategory.getSelectedItemPosition() == 0 ? "" : spnDthCategory.getSelectedItem().toString().split("-")[0]);
         String[] d_rdogrpDthEnrolChamps = new String[] {"0","1","8","9"};
         objSave.setDthEnrolChamps("");
         for (int i = 0; i < rdogrpDthEnrolChamps.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDthEnrolChamps.getChildAt(i);
             if (rb.isChecked()) objSave.setDthEnrolChamps(d_rdogrpDthEnrolChamps[i]);
         }

         String[] d_rdogrpDthChampsIdKnown = new String[] {"0","1","8","9"};
         objSave.setDthChampsIdKnown("");
         for (int i = 0; i < rdogrpDthChampsIdKnown.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDthChampsIdKnown.getChildAt(i);
             if (rb.isChecked()) objSave.setDthChampsIdKnown(d_rdogrpDthChampsIdKnown[i]);
         }


         objSave.setDthChampsId(txtDthChampsId.getText().toString());
         objSave.setDthVDate(dtpDthVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpDthVDate.getText().toString()) : dtpDthVDate.getText().toString());
         objSave.setRnd(txtRnd.getText().toString());
         objSave.setDthNote(txtDthNote.getText().toString());
         objSave.setStartTime(STARTTIME);
         objSave.setEndTime(g.CurrentTime24());
         objSave.setDeviceID(DEVICEID);
         objSave.setEntryUser(ENTRYUSER); //from data entry user list
         objSave.setLat(MySharedPreferences.getValue(this, "lat"));
         objSave.setLon(MySharedPreferences.getValue(this, "lon"));

         String status = objSave.SaveUpdateData(this);
         if(status.length()==0) {

             if (EV_TYPE.equals("55")) {
                 C.SaveData("Update tmpMember set Upload='2',Active='2',ExType='"+ EV_TYPE +"',modifydate='"+ Global.DateTimeNowYMDHMS() +"' Where MemID='" + MEM_ID + "'");
                 C.SaveData("Update tmpMemberMove set Upload='2',Active='2', MExType='" + EV_TYPE + "', MExDate='" + Global.DateConvertYMD(dtpDthDate.getText().toString()) +"',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where HHID='"+ HHID +"' and MemID='" + MEM_ID + "'");
             }

             Intent returnIntent = new Intent();
             returnIntent.putExtra("res", "");
             setResult(Activity.RESULT_OK, returnIntent);

             Connection.MessageBox(Surv_Death.this, "Saved Successfully");
             finish();
         }
         else{
             Connection.MessageBox(Surv_Death.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Death.this, e.getMessage());
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
         if(txtDeathID.getText().toString().length()==0 & secDeathID.isShown())
           {
             ValidationMsg += "\nRequired field: Internal death ID.";
             secDeathID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
           {
             ValidationMsg += "\nRequired field: Household ID.";
             secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMemID.getText().toString().length()==0 & secMemID.isShown())
           {
             ValidationMsg += "\nRequired field: Member internal ID.";
             secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpDthDate.getText().toString());
         if(DV.length()!=0 & secDthDate.isShown())
           {
             ValidationMsg += "\n1. Required field/Not a valid date format: When did he/she die? (Day /Month/Year).";
             secDthDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }

         if(DV.length() == 0 & Integer.parseInt(AGE_DAYS) < Global.DateDifferenceDays(Global.DateNowDMY(), dtpDthDate.getText().toString()) & !rdoDthDateType3.isChecked()){
             ValidationMsg += "\n1. Required field: Member's Death date should be greater than Birth date";
             secDthDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
         }

         if(!rdoDthDateType1.isChecked() & !rdoDthDateType2.isChecked() & !rdoDthDateType3.isChecked() & secDthDateType.isShown())
           {
             ValidationMsg += "\nRequired field: Death Date Type.";
             secDthDateType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDthTime.getText().length()==0 & secDthTime.isShown() & !rdoDthTimeType3.isChecked())
           {
             ValidationMsg += "\n2. Required field: What was the time of death?.";
             secDthTime.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoDthTimeType1.isChecked() & !rdoDthTimeType2.isChecked() & !rdoDthTimeType3.isChecked() & secDthTimeType.isShown())
           {
             ValidationMsg += "\nRequired field: Death Time Type.";
             secDthTimeType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnDthPlace.getSelectedItemPosition()==0  & secDthPlace.isShown())
           {
             ValidationMsg += "\n3. Required field: What was the place of death?.";
             secDthPlace.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDthPlaceOth.getText().toString().length()==0 & secDthPlaceOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other specify.";
             secDthPlaceOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDthPlaceFacility.getText().toString().length()==0 & secDthPlaceFacility.isShown())
         {
             ValidationMsg += "\nRequired field: If died in health facility, then what is the name of health facility?.";
             secDthPlaceFacility.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

         if(!rdoDthAdrsState1.isChecked() & !rdoDthAdrsState2.isChecked() & secDthAdrsState.isShown())
         {
             ValidationMsg += "\nRequired field: Where the person died?.";
             secDthAdrsState.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!(spnDthPlace.getSelectedItem().toString().split("-")[0].equals("98") || spnDthPlace.getSelectedItem().toString().split("-")[0].equals("99")) & txtDthAdrsAL1.getText().toString().length()==0 & secDthAdrsAL1.isShown())
         {
             ValidationMsg += "\na. Required field: Admin Level 1.";
             secDthAdrsAL1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
//         if(txtDthAdrsAL2.getText().toString().length()==0 & secDthAdrsAL2.isShown())
//         {
//             ValidationMsg += "\nb. Required field: Admin Level 2.";
//             secDthAdrsAL2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//         }
//         if(txtDthAdrsAL3.getText().toString().length()==0 & secDthAdrsAL3.isShown())
//         {
//             ValidationMsg += "\nc. Required field: Admin Level 3.";
//             secDthAdrsAL3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//         }
         if(txtDthAdrsAL4.getText().toString().length()==0 & secDthAdrsAL4.isShown())
         {
             ValidationMsg += "\nd. Required field: State.";
             secDthAdrsAL4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
//         if(txtDthAdrsAL5.getText().toString().length()==0 & secDthAdrsAL5.isShown())
//         {
//             ValidationMsg += "\ne. Required field: Admin Level 5 Area/village.";
//             secDthAdrsAL5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//         }
//         if(!rdoDthSick1.isChecked() & !rdoDthSick2.isChecked() & secDthSick.isShown())
//         {
//             ValidationMsg += "\nRequired field: Was s/he sick? (, était-il malade ?).";
//             secDthSick.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//         }
         if(!rdoDthCertificate1.isChecked() & !rdoDthCertificate2.isChecked()  & secDthCertificate.isShown())
         {
             ValidationMsg += "\nRequired field: Is there death certificate available?.";
             secDthCertificate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtDthCauseOth.getText().toString().length()==0 & secDthCauseOth.isShown())
         {
             ValidationMsg += "\nRequired field: Specify others.";
             secDthCauseOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!rdoDthHCProf1.isChecked() & !rdoDthHCProf2.isChecked() & !rdoDthHCProf3.isChecked() & !rdoDthHCProf4.isChecked() & secDthHCProf.isShown())
         {
             ValidationMsg += "\nRequired field: Were you told by a health care professional what the cause of death was for the deceased?.";
             secDthHCProf.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtDthCauseOthProf.getText().toString().length()==0 & secDthCauseOthProf.isShown())
         {
             ValidationMsg += "\nRequired field: Specify others.";
             secDthCauseOthProf.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnDthCategory.getSelectedItemPosition()==0  & secDthCategory.isShown())
         {
             ValidationMsg += "\nRequired field: Which category does the death apply to?.";
             secDthCategory.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

         if(!rdoDthEnrolChamps1.isChecked() & !rdoDthEnrolChamps2.isChecked() & !rdoDthEnrolChamps3.isChecked() & !rdoDthEnrolChamps4.isChecked() & secDthEnrolChamps.isShown())
         {
             ValidationMsg += "\nRequired field: Was this death enrolled in the CHAMPS MS study?.";
             secDthEnrolChamps.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

         if(!rdoDthChampsIdKnown1.isChecked() & !rdoDthChampsIdKnown2.isChecked() & !rdoDthChampsIdKnown3.isChecked() & !rdoDthChampsIdKnown4.isChecked() & secDthChampsIdKnown.isShown())
         {
             ValidationMsg += "\nRequired field: Is the MS CHAMPS ID known?.";
             secDthChampsIdKnown.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtDthChampsId.getText().toString().length()==0 & secDthChampsId.isShown())
         {
             ValidationMsg += "\nRequired field: If yes, what is the MS CHAMPS ID if the death occurred in a child under-five year of age?.";
             secDthChampsId.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         DV = Global.DateValidate(dtpDthVDate.getText().toString());

         //if (!isDiedDateSmaller(dtpDthVDate.getText().toString(), dtpDthDate.getText().toString())) {
         if(Global.DateDifferenceDays(dtpDthVDate.getText().toString(), dtpDthDate.getText().toString())<0){
             ValidationMsg += "\n Death date should be equal or less than date of visit.";
             secDthDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             secDthVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

         if(DV.length()!=0 & secDthVDate.isShown())
           {
             ValidationMsg += "\n4. Required field/Not a valid date format: Visit date.";
             secDthVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             ValidationMsg += "\nRequired field: Round.";
             secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
//         if(txtDthNote.getText().toString().length()==0 & secDthNote.isShown())
//           {
//             ValidationMsg += "\nRequired field: Note.";
//             secDthNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
     }
     catch(Exception  e)
     {
         ValidationMsg += "\n"+ e.getMessage();
     }

     return ValidationMsg;
 }

     public  boolean isDiedDateSmaller(String inDate, String diedDate) {
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
         try {
             Date inDateTime = sdf.parse(inDate);
             Date diedDateTime = sdf.parse(diedDate);

             // Compare the dates
             return diedDateTime.before(inDateTime);
         } catch (ParseException e) {
             e.printStackTrace();
             // Handle the parsing exception as needed
             return false;
         }
     }

 private void ResetSectionColor()
 {
   try
     {
             secDeathID.setBackgroundColor(Color.WHITE);
             secHHID.setBackgroundColor(Color.WHITE);
             secMemID.setBackgroundColor(Color.WHITE);
             secDthDate.setBackgroundColor(Color.WHITE);
             secDthDateType.setBackgroundColor(Color.WHITE);
             secDthTime.setBackgroundColor(Color.WHITE);
             secDthTimeType.setBackgroundColor(Color.WHITE);
             secDthPlace.setBackgroundColor(Color.WHITE);
             secDthPlaceOth.setBackgroundColor(Color.WHITE);
         secDthPlaceFacility.setBackgroundColor(Color.WHITE);
         secDthAdrsState.setBackgroundColor(Color.WHITE);
         secDthAdrsAL1.setBackgroundColor(Color.WHITE);
         secDthAdrsAL2.setBackgroundColor(Color.WHITE);
         secDthAdrsAL3.setBackgroundColor(Color.WHITE);
         secDthAdrsAL4.setBackgroundColor(Color.WHITE);
         secDthAdrsAL5.setBackgroundColor(Color.WHITE);
         secDthSick.setBackgroundColor(Color.WHITE);
         secDthCertificate.setBackgroundColor(Color.WHITE);
         secDthCauseOth.setBackgroundColor(Color.WHITE);
         secDthHCProf.setBackgroundColor(Color.WHITE);
         secDthCauseOthProf.setBackgroundColor(Color.WHITE);
         secDthCategory.setBackgroundColor(Color.WHITE);
         secDthEnrolChamps.setBackgroundColor(Color.WHITE);
         secDthChampsIdKnown.setBackgroundColor(Color.WHITE);
         secDthChampsId.setBackgroundColor(Color.WHITE);
             secDthVDate.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
             secDthNote.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String DeathID)
     {
       try
        {     
           RadioButton rb;
            tmpDeath_DataModel d = new tmpDeath_DataModel();
           String SQL = "Select * from "+ TableName +"  Where DeathID='"+ DeathID +"'";
           List<tmpDeath_DataModel> data = d.SelectAll(this, SQL);
           for(tmpDeath_DataModel item : data){
             txtDeathID.setText(item.getDeathID());
             txtHHID.setText(item.getHHID());
             txtMemID.setText(item.getMemID());
             dtpDthDate.setText(item.getDthDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getDthDate()));
             String[] d_rdogrpDthDateType = new String[] {"1","2","8"};
             for (int i = 0; i < d_rdogrpDthDateType.length; i++)
             {
                 if (String.valueOf(item.getDthDateType()).equals(String.valueOf(d_rdogrpDthDateType[i])))
                 {
                     rb = (RadioButton)rdogrpDthDateType.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtDthTime.setText(item.getDthTime());
             String[] d_rdogrpDthTimeType = new String[] {"1","2","8"};
             for (int i = 0; i < d_rdogrpDthTimeType.length; i++)
             {
                 if (String.valueOf(item.getDthTimeType()).equals(String.valueOf(d_rdogrpDthTimeType[i])))
                 {
                     rb = (RadioButton)rdogrpDthTimeType.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             spnDthPlace.setSelection(Global.SpinnerItemPositionAnyLength(spnDthPlace, String.valueOf(item.getDthPlace())));
             txtDthPlaceOth.setText(item.getDthPlaceOth());
               txtDthPlaceFacility.setText(item.getDthPlaceFacility());
               String[] d_rdogrpDthAdrsState = new String[] {"1","2"};
               for (int i = 0; i < d_rdogrpDthAdrsState.length; i++)
               {
                   if (String.valueOf(item.getDthAdrsState()).equals(String.valueOf(d_rdogrpDthAdrsState[i])))
                   {
                       rb = (RadioButton)rdogrpDthAdrsState.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
               txtDthAdrsAL1.setText(item.getDthAdrsAL1());
               txtDthAdrsAL2.setText(item.getDthAdrsAL2());
               txtDthAdrsAL3.setText(item.getDthAdrsAL3());
               txtDthAdrsAL4.setText(item.getDthAdrsAL4());
               txtDthAdrsAL5.setText(item.getDthAdrsAL5());
               String[] d_rdogrpDthSick = new String[] {"0","1"};
               for (int i = 0; i < d_rdogrpDthSick.length; i++)
               {
                   if (String.valueOf(item.getDthSick()).equals(String.valueOf(d_rdogrpDthSick[i])))
                   {
                       rb = (RadioButton)rdogrpDthSick.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
               String[] d_rdogrpDthCertificate = new String[] {"0","1"};
               for (int i = 0; i < d_rdogrpDthCertificate.length; i++)
               {
                   if (String.valueOf(item.getDthCertificate()).equals(String.valueOf(d_rdogrpDthCertificate[i])))
                   {
                       rb = (RadioButton)rdogrpDthCertificate.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
               if(String.valueOf(item.getchkDthCauseA()).equals("1"))
               {
                   chkchkDthCauseA.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseA()).equals("2"))
               {
                   chkchkDthCauseA.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseB()).equals("1"))
               {
                   chkchkDthCauseB.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseB()).equals("2"))
               {
                   chkchkDthCauseB.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseC()).equals("1"))
               {
                   chkchkDthCauseC.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseC()).equals("2"))
               {
                   chkchkDthCauseC.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseD()).equals("1"))
               {
                   chkchkDthCauseD.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseD()).equals("2"))
               {
                   chkchkDthCauseD.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseE()).equals("1"))
               {
                   chkchkDthCauseE.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseE()).equals("2"))
               {
                   chkchkDthCauseE.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseF()).equals("1"))
               {
                   chkchkDthCauseF.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseF()).equals("2"))
               {
                   chkchkDthCauseF.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseG()).equals("1"))
               {
                   chkchkDthCauseG.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseG()).equals("2"))
               {
                   chkchkDthCauseG.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseH()).equals("1"))
               {
                   chkchkDthCauseH.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseH()).equals("2"))
               {
                   chkchkDthCauseH.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseI()).equals("1"))
               {
                   chkchkDthCauseI.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseI()).equals("2"))
               {
                   chkchkDthCauseI.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseJ()).equals("1"))
               {
                   chkchkDthCauseJ.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseJ()).equals("2"))
               {
                   chkchkDthCauseJ.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseK()).equals("1"))
               {
                   chkchkDthCauseK.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseK()).equals("2"))
               {
                   chkchkDthCauseK.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseL()).equals("1"))
               {
                   chkchkDthCauseL.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseL()).equals("2"))
               {
                   chkchkDthCauseL.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseM()).equals("1"))
               {
                   chkchkDthCauseM.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseM()).equals("2"))
               {
                   chkchkDthCauseM.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseN()).equals("1"))
               {
                   chkchkDthCauseN.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseN()).equals("2"))
               {
                   chkchkDthCauseN.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseO()).equals("1"))
               {
                   chkchkDthCauseO.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseO()).equals("2"))
               {
                   chkchkDthCauseO.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseP()).equals("1"))
               {
                   chkchkDthCauseP.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseP()).equals("2"))
               {
                   chkchkDthCauseP.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseQ()).equals("1"))
               {
                   chkchkDthCauseQ.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseQ()).equals("2"))
               {
                   chkchkDthCauseQ.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseR()).equals("1"))
               {
                   chkchkDthCauseR.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseR()).equals("2"))
               {
                   chkchkDthCauseR.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseS()).equals("1"))
               {
                   chkchkDthCauseS.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseS()).equals("2"))
               {
                   chkchkDthCauseS.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseT()).equals("1"))
               {
                   chkchkDthCauseT.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseT()).equals("2"))
               {
                   chkchkDthCauseT.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseX()).equals("1"))
               {
                   chkchkDthCauseX.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseX()).equals("2"))
               {
                   chkchkDthCauseX.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseY()).equals("1"))
               {
                   chkchkDthCauseY.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseY()).equals("2"))
               {
                   chkchkDthCauseY.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseZ()).equals("1"))
               {
                   chkchkDthCauseZ.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseZ()).equals("2"))
               {
                   chkchkDthCauseZ.setChecked(false);
               }
               txtDthCauseOth.setText(item.getDthCauseOth());
               String[] d_rdogrpDthHCProf = new String[] {"0","1","8","9"};
               for (int i = 0; i < d_rdogrpDthHCProf.length; i++)
               {
                   if (String.valueOf(item.getDthHCProf()).equals(String.valueOf(d_rdogrpDthHCProf[i])))
                   {
                       rb = (RadioButton)rdogrpDthHCProf.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
               if(String.valueOf(item.getchkDthCauseProfA()).equals("1"))
               {
                   chkchkDthCauseProfA.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfA()).equals("2"))
               {
                   chkchkDthCauseProfA.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfB()).equals("1"))
               {
                   chkchkDthCauseProfB.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfB()).equals("2"))
               {
                   chkchkDthCauseProfB.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfC()).equals("1"))
               {
                   chkchkDthCauseProfC.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfC()).equals("2"))
               {
                   chkchkDthCauseProfC.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfD()).equals("1"))
               {
                   chkchkDthCauseProfD.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfD()).equals("2"))
               {
                   chkchkDthCauseProfD.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfE()).equals("1"))
               {
                   chkchkDthCauseProfE.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfE()).equals("2"))
               {
                   chkchkDthCauseProfE.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfF()).equals("1"))
               {
                   chkchkDthCauseProfF.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfF()).equals("2"))
               {
                   chkchkDthCauseProfF.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfG()).equals("1"))
               {
                   chkchkDthCauseProfG.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfG()).equals("2"))
               {
                   chkchkDthCauseProfG.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfH()).equals("1"))
               {
                   chkchkDthCauseProfH.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfH()).equals("2"))
               {
                   chkchkDthCauseProfH.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfI()).equals("1"))
               {
                   chkchkDthCauseProfI.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfI()).equals("2"))
               {
                   chkchkDthCauseProfI.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfJ()).equals("1"))
               {
                   chkchkDthCauseProfJ.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfJ()).equals("2"))
               {
                   chkchkDthCauseProfJ.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfK()).equals("1"))
               {
                   chkchkDthCauseProfK.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfK()).equals("2"))
               {
                   chkchkDthCauseProfK.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfL()).equals("1"))
               {
                   chkchkDthCauseProfL.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfL()).equals("2"))
               {
                   chkchkDthCauseProfL.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfM()).equals("1"))
               {
                   chkchkDthCauseProfM.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfM()).equals("2"))
               {
                   chkchkDthCauseProfM.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfN()).equals("1"))
               {
                   chkchkDthCauseProfN.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfN()).equals("2"))
               {
                   chkchkDthCauseProfN.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfO()).equals("1"))
               {
                   chkchkDthCauseProfO.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfO()).equals("2"))
               {
                   chkchkDthCauseProfO.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfP()).equals("1"))
               {
                   chkchkDthCauseProfP.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfP()).equals("2"))
               {
                   chkchkDthCauseProfP.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfQ()).equals("1"))
               {
                   chkchkDthCauseProfQ.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfQ()).equals("2"))
               {
                   chkchkDthCauseProfQ.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfR()).equals("1"))
               {
                   chkchkDthCauseProfR.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfR()).equals("2"))
               {
                   chkchkDthCauseProfR.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfS()).equals("1"))
               {
                   chkchkDthCauseProfS.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfS()).equals("2"))
               {
                   chkchkDthCauseProfS.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfT()).equals("1"))
               {
                   chkchkDthCauseProfT.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfT()).equals("2"))
               {
                   chkchkDthCauseProfT.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfX()).equals("1"))
               {
                   chkchkDthCauseProfX.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfX()).equals("2"))
               {
                   chkchkDthCauseProfX.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfY()).equals("1"))
               {
                   chkchkDthCauseProfY.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfY()).equals("2"))
               {
                   chkchkDthCauseProfY.setChecked(false);
               }
               if(String.valueOf(item.getchkDthCauseProfZ()).equals("1"))
               {
                   chkchkDthCauseProfZ.setChecked(true);
               }
               else if(String.valueOf(item.getchkDthCauseProfZ()).equals("2"))
               {
                   chkchkDthCauseProfZ.setChecked(false);
               }
               txtDthCauseOthProf.setText(item.getDthCauseOthProf());
               spnDthCategory.setSelection(Global.SpinnerItemPositionAnyLength(spnDthCategory, String.valueOf(item.getDthCategory())));
               String[] d_rdogrpDthEnrolChamps = new String[] {"0","1","8","9"};
               for (int i = 0; i < d_rdogrpDthEnrolChamps.length; i++)
               {
                   if (String.valueOf(item.getDthEnrolChamps()).equals(String.valueOf(d_rdogrpDthEnrolChamps[i])))
                   {
                       rb = (RadioButton)rdogrpDthEnrolChamps.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
             
               String[] d_rdogrpDthChampsIdKnown = new String[] {"0","1","8","9"};
               for (int i = 0; i < d_rdogrpDthChampsIdKnown.length; i++)
               {
                   if (String.valueOf(item.getDthChampsIdKnown()).equals(String.valueOf(d_rdogrpDthChampsIdKnown[i])))
                   {
                       rb = (RadioButton)rdogrpDthChampsIdKnown.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
             txtDthChampsId.setText(item.getDthChampsId());
             dtpDthVDate.setText(item.getDthVDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getDthVDate()));
             txtRnd.setText(item.getRnd());
             txtDthNote.setText(item.getDthNote());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Death.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpDthDate);
      if (VariableID.equals("btnDthDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpDthDate);
      }
      else if (VariableID.equals("btnDthVDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpDthVDate);
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

              tpTime = (EditText)findViewById(R.id.txtDthTime);
             if (VariableID.equals("btnDthTime"))
              {
                  tpTime = (EditText)findViewById(R.id.txtDthTime);
              }
       tpTime.setText(new StringBuilder().append(Global.Right("00"+hour,2)).append(":").append(Global.Right("00"+minute,2)));
    }
  };


 
 // turning off the GPS if its in on state. to avoid the battery drain.
 @Override
 protected void onDestroy() {
     // TODO Auto-generated method stub
     super.onDestroy();
 }
}