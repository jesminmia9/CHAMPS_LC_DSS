
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
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.R;
 import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

 import forms_datamodel.tmpClimate_Change_DataModel;
 import Utility.*;
 import Common.*;

 public class Climate_Change extends AppCompatActivity {
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
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secClimateSL;
    View lineClimateSL;
    TextView VlblClimateSL;
    EditText txtClimateSL;
    LinearLayout secClimateVDate;
    View lineClimateVDate;
    TextView VlblClimateVDate;
    EditText dtpClimateVDate;
    LinearLayout secClimateVStatus;
    View lineClimateVStatus;
    TextView VlblClimateVStatus;
    RadioGroup rdogrpClimateVStatus;
    RadioButton rdoClimateVStatus1;
    RadioButton rdoClimateVStatus2;
    RadioButton rdoClimateVStatus3;
    RadioButton rdoClimateVStatus4;
    LinearLayout secClimateVStatusOth;
    View lineClimateVStatusOth;
    TextView VlblClimateVStatusOth;
    EditText txtClimateVStatusOth;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout seclblP1;
    View linelblP1;
    LinearLayout secClimateChangesNoticed;
    View lineClimateChangesNoticed;
    TextView VlblClimateChangesNoticed;
    RadioGroup rdogrpClimateChangesNoticed;
    RadioButton rdoClimateChangesNoticed1;
    RadioButton rdoClimateChangesNoticed2;
    LinearLayout seclblQ2;
    View linelblQ2;
    LinearLayout secClimateChangesTemp;
    View lineClimateChangesTemp;
    TextView VlblClimateChangesTemp;
    CheckBox chkClimateChangesTemp;
    LinearLayout secClimateChangesSeason;
    View lineClimateChangesSeason;
    TextView VlblClimateChangesSeason;
    CheckBox chkClimateChangesSeason;
    LinearLayout secClimateChangesWeather;
    View lineClimateChangesWeather;
    TextView VlblClimateChangesWeather;
    CheckBox chkClimateChangesWeather;
    LinearLayout secClimateChangesFlora;
    View lineClimateChangesFlora;
    TextView VlblClimateChangesFlora;
    CheckBox chkClimateChangesFlora;
    LinearLayout secClimateChangesOther;
    View lineClimateChangesOther;
    TextView VlblClimateChangesOther;
    CheckBox chkClimateChangesOther;
    LinearLayout secClimateChangesOtherSp;
    View lineClimateChangesOtherSp;
    TextView VlblClimateChangesOtherSp;
    EditText txtClimateChangesOtherSp;
    LinearLayout secClimateAffectDailyLife;
    View lineClimateAffectDailyLife;
    TextView VlblClimateAffectDailyLife;
    Spinner spnClimateAffectDailyLife;
    LinearLayout secClimateFlood;
    View lineClimateFlood;
    TextView VlblClimateFlood;
    RadioGroup rdogrpClimateFlood;
    RadioButton rdoClimateFlood1;
    RadioButton rdoClimateFlood2;
    LinearLayout seclblQ5;
    View linelblQ5;
    LinearLayout secClimateFloodHouse;
    View lineClimateFloodHouse;
    TextView VlblClimateFloodHouse;
    CheckBox chkClimateFloodHouse;
    LinearLayout secClimateFloodWall;
    View lineClimateFloodWall;
    TextView VlblClimateFloodWall;
    CheckBox chkClimateFloodWall;
    LinearLayout secClimateFloodMember;
    View lineClimateFloodMember;
    TextView VlblClimateFloodMember;
    CheckBox chkClimateFloodMember;
    LinearLayout secClimateFloodLivestock;
    View lineClimateFloodLivestock;
    TextView VlblClimateFloodLivestock;
    CheckBox chkClimateFloodLivestock;
    LinearLayout secClimateFloodJobLess;
    View lineClimateFloodJobLess;
    TextView VlblClimateFloodJobLess;
    CheckBox chkClimateFloodJobLess;
    LinearLayout secClimateFloodEnergy;
    View lineClimateFloodEnergy;
    TextView VlblClimateFloodEnergy;
    CheckBox chkClimateFloodEnergy;
    LinearLayout secClimateFloodToilets;
    View lineClimateFloodToilets;
    TextView VlblClimateFloodToilets;
    CheckBox chkClimateFloodToilets;
    LinearLayout secClimateFloodHealth;
    View lineClimateFloodHealth;
    TextView VlblClimateFloodHealth;
    CheckBox chkClimateFloodHealth;
    LinearLayout secClimateFloodMigration;
    View lineClimateFloodMigration;
    TextView VlblClimateFloodMigration;
    CheckBox chkClimateFloodMigration;
    LinearLayout secClimateFloodOther;
    View lineClimateFloodOther;
    TextView VlblClimateFloodOther;
    CheckBox chkClimateFloodOther;
    LinearLayout secClimateFloodOtherSp;
    View lineClimateFloodOtherSp;
    TextView VlblClimateFloodOtherSp;
    EditText txtClimateFloodOtherSp;
    LinearLayout secClimateHighHeat;
    View lineClimateHighHeat;
    TextView VlblClimateHighHeat;
    RadioGroup rdogrpClimateHighHeat;
    RadioButton rdoClimateHighHeat1;
    RadioButton rdoClimateHighHeat2;
    LinearLayout seclblQ7;
    View linelblQ7;
    LinearLayout secClimateHighHeatMemberDth;
    View lineClimateHighHeatMemberDth;
    TextView VlblClimateHighHeatMemberDth;
    CheckBox chkClimateHighHeatMemberDth;
    LinearLayout secClimateHighHeatHealth;
    View lineClimateHighHeatHealth;
    TextView VlblClimateHighHeatHealth;
    CheckBox chkClimateHighHeatHealth;
    LinearLayout secClimateHighHeatMemberIll;
    View lineClimateHighHeatMemberIll;
    TextView VlblClimateHighHeatMemberIll;
    CheckBox chkClimateHighHeatMemberIll;
    LinearLayout secClimateHighHeatPetDth;
    View lineClimateHighHeatPetDth;
    TextView VlblClimateHighHeatPetDth;
    CheckBox chkClimateHighHeatPetDth;
    LinearLayout secClimateHighHeatOther;
    View lineClimateHighHeatOther;
    TextView VlblClimateHighHeatOther;
    CheckBox chkClimateHighHeatOther;
    LinearLayout secClimateHighHeatOtherSp;
    View lineClimateHighHeatOtherSp;
    TextView VlblClimateHighHeatOtherSp;
    EditText txtClimateHighHeatOtherSp;
    LinearLayout seclblQ7a;
    View linelblQ7a;
    LinearLayout secClimateHealthProblemSleep;
    View lineClimateHealthProblemSleep;
    TextView VlblClimateHealthProblemSleep;
    CheckBox chkClimateHealthProblemSleep;
    LinearLayout secClimateHealthProblemDizzy;
    View lineClimateHealthProblemDizzy;
    TextView VlblClimateHealthProblemDizzy;
    CheckBox chkClimateHealthProblemDizzy;
    LinearLayout secClimateHealthProblemLowBlood;
    View lineClimateHealthProblemLowBlood;
    TextView VlblClimateHealthProblemLowBlood;
    CheckBox chkClimateHealthProblemLowBlood;
    LinearLayout secClimateHealthProblemHighBlood;
    View lineClimateHealthProblemHighBlood;
    TextView VlblClimateHealthProblemHighBlood;
    CheckBox chkClimateHealthProblemHighBlood;
    LinearLayout secClimateHealthProblemStroke;
    View lineClimateHealthProblemStroke;
    TextView VlblClimateHealthProblemStroke;
    CheckBox chkClimateHealthProblemStroke;
    LinearLayout secClimateHealthProblemFever;
    View lineClimateHealthProblemFever;
    TextView VlblClimateHealthProblemFever;
    CheckBox chkClimateHealthProblemFever;
    LinearLayout secClimateHealthProblemOther;
    View lineClimateHealthProblemOther;
    TextView VlblClimateHealthProblemOther;
    CheckBox chkClimateHealthProblemOther;
    LinearLayout secClimateHealthProblemOtherSp;
    View lineClimateHealthProblemOtherSp;
    TextView VlblClimateHealthProblemOtherSp;
    EditText txtClimateHealthProblemOtherSp;
    LinearLayout secClimateHealthProblemPeriod;
    View lineClimateHealthProblemPeriod;
    TextView VlblClimateHealthProblemPeriod;
    Spinner spnClimateHealthProblemPeriod;
    LinearLayout seclblQ7b;
    View linelblQ7b;
    LinearLayout secClimateIllnessMalaria;
    View lineClimateIllnessMalaria;
    TextView VlblClimateIllnessMalaria;
    CheckBox chkClimateIllnessMalaria;
    LinearLayout secClimateIllnessPneumonia;
    View lineClimateIllnessPneumonia;
    TextView VlblClimateIllnessPneumonia;
    CheckBox chkClimateIllnessPneumonia;
    LinearLayout secClimateIllnessMeasles;
    View lineClimateIllnessMeasles;
    TextView VlblClimateIllnessMeasles;
    CheckBox chkClimateIllnessMeasles;
    LinearLayout secClimateIllnessPertussis;
    View lineClimateIllnessPertussis;
    TextView VlblClimateIllnessPertussis;
    CheckBox chkClimateIllnessPertussis;
    LinearLayout secClimateIllnessDiarrhea;
    View lineClimateIllnessDiarrhea;
    TextView VlblClimateIllnessDiarrhea;
    CheckBox chkClimateIllnessDiarrhea;
    LinearLayout secClimateIllnessFood;
    View lineClimateIllnessFood;
    TextView VlblClimateIllnessFood;
    CheckBox chkClimateIllnessFood;
    LinearLayout secClimateIllnessTyphoid;
    View lineClimateIllnessTyphoid;
    TextView VlblClimateIllnessTyphoid;
    CheckBox chkClimateIllnessTyphoid;
    LinearLayout secClimateIllnessCough;
    View lineClimateIllnessCough;
    TextView VlblClimateIllnessCough;
    CheckBox chkClimateIllnessCough;
    LinearLayout secClimateIllnessCholera;
    View lineClimateIllnessCholera;
    TextView VlblClimateIllnessCholera;
    CheckBox chkClimateIllnessCholera;
    LinearLayout secClimateIllnessOther;
    View lineClimateIllnessOther;
    TextView VlblClimateIllnessOther;
    CheckBox chkClimateIllnessOther;
    LinearLayout secClimateIllnessOtherSp;
    View lineClimateIllnessOtherSp;
    TextView VlblClimateIllnessOtherSp;
    EditText txtClimateIllnessOtherSp;
    LinearLayout secClimateIllnessPeriod;
    View lineClimateIllnessPeriod;
    TextView VlblClimateIllnessPeriod;
    Spinner spnClimateIllnessPeriod;
    LinearLayout seclblP2;
    View linelblP2;
    LinearLayout secClimateHeard;
    View lineClimateHeard;
    TextView VlblClimateHeard;
    RadioGroup rdogrpClimateHeard;
    RadioButton rdoClimateHeard1;
    RadioButton rdoClimateHeard2;
    LinearLayout seclblQ9;
    View linelblQ9;
    LinearLayout secClimateInfoMedia;
    View lineClimateInfoMedia;
    TextView VlblClimateInfoMedia;
    CheckBox chkClimateInfoMedia;
    LinearLayout secClimateInfoInternet;
    View lineClimateInfoInternet;
    TextView VlblClimateInfoInternet;
    CheckBox chkClimateInfoInternet;
    LinearLayout secClimateInfoEducation;
    View lineClimateInfoEducation;
    TextView VlblClimateInfoEducation;
    CheckBox chkClimateInfoEducation;
    LinearLayout secClimateInfoFnF;
    View lineClimateInfoFnF;
    TextView VlblClimateInfoFnF;
    CheckBox chkClimateInfoFnF;
    LinearLayout secClimateInfoOther;
    View lineClimateInfoOther;
    TextView VlblClimateInfoOther;
    CheckBox chkClimateInfoOther;
    LinearLayout secClimateInfoOtherSp;
    View lineClimateInfoOtherSp;
    TextView VlblClimateInfoOtherSp;
    EditText txtClimateInfoOtherSp;
    LinearLayout secClimateKnRate;
    View lineClimateKnRate;
    TextView VlblClimateKnRate;
    Spinner spnClimateKnRate;
    LinearLayout seclblP3;
    View linelblP3;
    LinearLayout secClimatethreat;
    View lineClimatethreat;
    TextView VlblClimatethreat;
    RadioGroup rdogrpClimatethreat;
    RadioButton rdoClimatethreat1;
    RadioButton rdoClimatethreat2;
    RadioButton rdoClimatethreat3;
    LinearLayout secClimateWorried;
    View lineClimateWorried;
    TextView VlblClimateWorried;
    Spinner spnClimateWorried;
    LinearLayout seclblQ13;
    View linelblQ13;
    LinearLayout secClimateConcernTemp;
    View lineClimateConcernTemp;
    TextView VlblClimateConcernTemp;
    CheckBox chkClimateConcernTemp;
    LinearLayout secClimateConcernEvent;
    View lineClimateConcernEvent;
    TextView VlblClimateConcernEvent;
    CheckBox chkClimateConcernEvent;
    LinearLayout secClimateConcernPolarIce;
    View lineClimateConcernPolarIce;
    TextView VlblClimateConcernPolarIce;
    CheckBox chkClimateConcernPolarIce;
    LinearLayout secClimateConcernSeaLevel;
    View lineClimateConcernSeaLevel;
    TextView VlblClimateConcernSeaLevel;
    CheckBox chkClimateConcernSeaLevel;
    LinearLayout secClimateConcernBiodiversity;
    View lineClimateConcernBiodiversity;
    TextView VlblClimateConcernBiodiversity;
    CheckBox chkClimateConcernBiodiversity;
    LinearLayout secClimateConcernDrying;
    View lineClimateConcernDrying;
    TextView VlblClimateConcernDrying;
    CheckBox chkClimateConcernDrying;
    LinearLayout secClimateConcernRising;
    View lineClimateConcernRising;
    TextView VlblClimateConcernRising;
    CheckBox chkClimateConcernRising;
    LinearLayout secClimateConcernDrought;
    View lineClimateConcernDrought;
    TextView VlblClimateConcernDrought;
    CheckBox chkClimateConcernDrought;
    LinearLayout secClimateConcernDesert;
    View lineClimateConcernDesert;
    TextView VlblClimateConcernDesert;
    CheckBox chkClimateConcernDesert;
    LinearLayout secClimateConcernErosin;
    View lineClimateConcernErosin;
    TextView VlblClimateConcernErosin;
    CheckBox chkClimateConcernErosin;
    LinearLayout secClimateConcernOther;
    View lineClimateConcernOther;
    TextView VlblClimateConcernOther;
    CheckBox chkClimateConcernOther;
    LinearLayout secClimateConcernOtherSp;
    View lineClimateConcernOtherSp;
    TextView VlblClimateConcernOtherSp;
    EditText txtClimateConcernOtherSp;
    LinearLayout secClimateComDoing;
    View lineClimateComDoing;
    TextView VlblClimateComDoing;
    RadioGroup rdogrpClimateComDoing;
    RadioButton rdoClimateComDoing1;
    RadioButton rdoClimateComDoing2;
    RadioButton rdoClimateComDoing3;
    LinearLayout seclblP4;
    View linelblP4;
    LinearLayout secClimateReduceImpact;
    View lineClimateReduceImpact;
    TextView VlblClimateReduceImpact;
    RadioGroup rdogrpClimateReduceImpact;
    RadioButton rdoClimateReduceImpact1;
    RadioButton rdoClimateReduceImpact2;
    LinearLayout seclblQ16;
    View linelblQ16;
    LinearLayout secClimateActionsPlastics;
    View lineClimateActionsPlastics;
    TextView VlblClimateActionsPlastics;
    CheckBox chkClimateActionsPlastics;
    LinearLayout secClimateActionsTransport;
    View lineClimateActionsTransport;
    TextView VlblClimateActionsTransport;
    CheckBox chkClimateActionsTransport;
    LinearLayout secClimateActionsFoods;
    View lineClimateActionsFoods;
    TextView VlblClimateActionsFoods;
    CheckBox chkClimateActionsFoods;
    LinearLayout secClimateActionsHome;
    View lineClimateActionsHome;
    TextView VlblClimateActionsHome;
    CheckBox chkClimateActionsHome;
    LinearLayout secClimateActionsOther;
    View lineClimateActionsOther;
    TextView VlblClimateActionsOther;
    CheckBox chkClimateActionsOther;
    LinearLayout secClimateActionsOtherSp;
    View lineClimateActionsOtherSp;
    TextView VlblClimateActionsOtherSp;
    EditText txtClimateActionsOtherSp;
    LinearLayout secClimateRenewableEnergy;
    View lineClimateRenewableEnergy;
    TextView VlblClimateRenewableEnergy;
    RadioGroup rdogrpClimateRenewableEnergy;
    RadioButton rdoClimateRenewableEnergy1;
    RadioButton rdoClimateRenewableEnergy2;
    RadioButton rdoClimateRenewableEnergy3;
    LinearLayout seclblQ18;
    View linelblQ18;
    LinearLayout secClimateObstaclesCost;
    View lineClimateObstaclesCost;
    TextView VlblClimateObstaclesCost;
    CheckBox chkClimateObstaclesCost;
    LinearLayout secClimateObstaclesLackInfo;
    View lineClimateObstaclesLackInfo;
    TextView VlblClimateObstaclesLackInfo;
    CheckBox chkClimateObstaclesLackInfo;
    LinearLayout secClimateObstaclesAccess;
    View lineClimateObstaclesAccess;
    TextView VlblClimateObstaclesAccess;
    CheckBox chkClimateObstaclesAccess;
    LinearLayout secClimateObstaclesHabit;
    View lineClimateObstaclesHabit;
    TextView VlblClimateObstaclesHabit;
    CheckBox chkClimateObstaclesHabit;
    LinearLayout secClimateObstaclesOther;
    View lineClimateObstaclesOther;
    TextView VlblClimateObstaclesOther;
    CheckBox chkClimateObstaclesOther;
    LinearLayout secClimateObstaclesOtherSp;
    View lineClimateObstaclesOtherSp;
    TextView VlblClimateObstaclesOtherSp;
    EditText txtClimateObstaclesOtherSp;
    LinearLayout secClimateNote;
    View lineClimateNote;
    TextView VlblClimateNote;
    EditText txtClimateNote;

    static int MODULEID   = 0;
    static int LANGUAGEID = 0;
    static String TableName;

    static String STARTTIME = "";
    static String DEVICEID  = "";
    static String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
    static String HHID = "";
    static String CLIMATESL = "";
    static String ROUND = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.climate_change);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         HHID = IDbundle.getString("HHID");
         CLIMATESL = IDbundle.getString("ClimateSL");
         ROUND = IDbundle.getString("round");

         TableName = "tmpClimate_Change";
         MODULEID = 58;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Climate_Change.this);
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
        Connection.LocalizeLanguage(Climate_Change.this, MODULEID, LANGUAGEID);


         dtpClimateVDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnClimateVDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables
         secClimateVStatusOth.setVisibility(View.GONE);
         lineClimateVStatusOth.setVisibility(View.GONE);
         secRnd.setVisibility(View.GONE);
         lineRnd.setVisibility(View.GONE);
         seclblP1.setVisibility(View.GONE);
         linelblP1.setVisibility(View.GONE);
         secClimateChangesNoticed.setVisibility(View.GONE);
         lineClimateChangesNoticed.setVisibility(View.GONE);
         seclblQ2.setVisibility(View.GONE);
         linelblQ2.setVisibility(View.GONE);
         secClimateChangesTemp.setVisibility(View.GONE);
         lineClimateChangesTemp.setVisibility(View.GONE);
         secClimateChangesSeason.setVisibility(View.GONE);
         lineClimateChangesSeason.setVisibility(View.GONE);
         secClimateChangesWeather.setVisibility(View.GONE);
         lineClimateChangesWeather.setVisibility(View.GONE);
         secClimateChangesFlora.setVisibility(View.GONE);
         lineClimateChangesFlora.setVisibility(View.GONE);
         secClimateChangesOther.setVisibility(View.GONE);
         lineClimateChangesOther.setVisibility(View.GONE);
         secClimateChangesOtherSp.setVisibility(View.GONE);
         lineClimateChangesOtherSp.setVisibility(View.GONE);
         secClimateAffectDailyLife.setVisibility(View.GONE);
         lineClimateAffectDailyLife.setVisibility(View.GONE);
         secClimateFlood.setVisibility(View.GONE);
         lineClimateFlood.setVisibility(View.GONE);
         seclblQ5.setVisibility(View.GONE);
         linelblQ5.setVisibility(View.GONE);
         secClimateFloodHouse.setVisibility(View.GONE);
         lineClimateFloodHouse.setVisibility(View.GONE);
         secClimateFloodWall.setVisibility(View.GONE);
         lineClimateFloodWall.setVisibility(View.GONE);
         secClimateFloodMember.setVisibility(View.GONE);
         lineClimateFloodMember.setVisibility(View.GONE);
         secClimateFloodLivestock.setVisibility(View.GONE);
         lineClimateFloodLivestock.setVisibility(View.GONE);
         secClimateFloodJobLess.setVisibility(View.GONE);
         lineClimateFloodJobLess.setVisibility(View.GONE);
         secClimateFloodEnergy.setVisibility(View.GONE);
         lineClimateFloodEnergy.setVisibility(View.GONE);
         secClimateFloodToilets.setVisibility(View.GONE);
         lineClimateFloodToilets.setVisibility(View.GONE);
         secClimateFloodHealth.setVisibility(View.GONE);
         lineClimateFloodHealth.setVisibility(View.GONE);
         secClimateFloodMigration.setVisibility(View.GONE);
         lineClimateFloodMigration.setVisibility(View.GONE);
         secClimateFloodOther.setVisibility(View.GONE);
         lineClimateFloodOther.setVisibility(View.GONE);
         secClimateFloodOtherSp.setVisibility(View.GONE);
         lineClimateFloodOtherSp.setVisibility(View.GONE);
         secClimateHighHeat.setVisibility(View.GONE);
         lineClimateHighHeat.setVisibility(View.GONE);
         seclblQ7.setVisibility(View.GONE);
         linelblQ7.setVisibility(View.GONE);
         secClimateHighHeatMemberDth.setVisibility(View.GONE);
         lineClimateHighHeatMemberDth.setVisibility(View.GONE);
         secClimateHighHeatHealth.setVisibility(View.GONE);
         lineClimateHighHeatHealth.setVisibility(View.GONE);
         secClimateHighHeatMemberIll.setVisibility(View.GONE);
         lineClimateHighHeatMemberIll.setVisibility(View.GONE);
         secClimateHighHeatPetDth.setVisibility(View.GONE);
         lineClimateHighHeatPetDth.setVisibility(View.GONE);
         secClimateHighHeatOther.setVisibility(View.GONE);
         lineClimateHighHeatOther.setVisibility(View.GONE);
         secClimateHighHeatOtherSp.setVisibility(View.GONE);
         lineClimateHighHeatOtherSp.setVisibility(View.GONE);
         seclblQ7a.setVisibility(View.GONE);
         linelblQ7a.setVisibility(View.GONE);
         secClimateHealthProblemSleep.setVisibility(View.GONE);
         lineClimateHealthProblemSleep.setVisibility(View.GONE);
         secClimateHealthProblemDizzy.setVisibility(View.GONE);
         lineClimateHealthProblemDizzy.setVisibility(View.GONE);
         secClimateHealthProblemLowBlood.setVisibility(View.GONE);
         lineClimateHealthProblemLowBlood.setVisibility(View.GONE);
         secClimateHealthProblemHighBlood.setVisibility(View.GONE);
         lineClimateHealthProblemHighBlood.setVisibility(View.GONE);
         secClimateHealthProblemStroke.setVisibility(View.GONE);
         lineClimateHealthProblemStroke.setVisibility(View.GONE);
         secClimateHealthProblemFever.setVisibility(View.GONE);
         lineClimateHealthProblemFever.setVisibility(View.GONE);
         secClimateHealthProblemOther.setVisibility(View.GONE);
         lineClimateHealthProblemOther.setVisibility(View.GONE);
         secClimateHealthProblemOtherSp.setVisibility(View.GONE);
         lineClimateHealthProblemOtherSp.setVisibility(View.GONE);
         secClimateHealthProblemPeriod.setVisibility(View.GONE);
         lineClimateHealthProblemPeriod.setVisibility(View.GONE);
         seclblQ7b.setVisibility(View.GONE);
         linelblQ7b.setVisibility(View.GONE);
         secClimateIllnessMalaria.setVisibility(View.GONE);
         lineClimateIllnessMalaria.setVisibility(View.GONE);
         secClimateIllnessPneumonia.setVisibility(View.GONE);
         lineClimateIllnessPneumonia.setVisibility(View.GONE);
         secClimateIllnessMeasles.setVisibility(View.GONE);
         lineClimateIllnessMeasles.setVisibility(View.GONE);
         secClimateIllnessPertussis.setVisibility(View.GONE);
         lineClimateIllnessPertussis.setVisibility(View.GONE);
         secClimateIllnessDiarrhea.setVisibility(View.GONE);
         lineClimateIllnessDiarrhea.setVisibility(View.GONE);
         secClimateIllnessFood.setVisibility(View.GONE);
         lineClimateIllnessFood.setVisibility(View.GONE);
         secClimateIllnessTyphoid.setVisibility(View.GONE);
         lineClimateIllnessTyphoid.setVisibility(View.GONE);
         secClimateIllnessCough.setVisibility(View.GONE);
         lineClimateIllnessCough.setVisibility(View.GONE);
         secClimateIllnessCholera.setVisibility(View.GONE);
         lineClimateIllnessCholera.setVisibility(View.GONE);
         secClimateIllnessOther.setVisibility(View.GONE);
         lineClimateIllnessOther.setVisibility(View.GONE);
         secClimateIllnessOtherSp.setVisibility(View.GONE);
         lineClimateIllnessOtherSp.setVisibility(View.GONE);
         secClimateIllnessPeriod.setVisibility(View.GONE);
         lineClimateIllnessPeriod.setVisibility(View.GONE);
         seclblP2.setVisibility(View.GONE);
         linelblP2.setVisibility(View.GONE);
         secClimateHeard.setVisibility(View.GONE);
         lineClimateHeard.setVisibility(View.GONE);
         seclblQ9.setVisibility(View.GONE);
         linelblQ9.setVisibility(View.GONE);
         secClimateInfoMedia.setVisibility(View.GONE);
         lineClimateInfoMedia.setVisibility(View.GONE);
         secClimateInfoInternet.setVisibility(View.GONE);
         lineClimateInfoInternet.setVisibility(View.GONE);
         secClimateInfoEducation.setVisibility(View.GONE);
         lineClimateInfoEducation.setVisibility(View.GONE);
         secClimateInfoFnF.setVisibility(View.GONE);
         lineClimateInfoFnF.setVisibility(View.GONE);
         secClimateInfoOther.setVisibility(View.GONE);
         lineClimateInfoOther.setVisibility(View.GONE);
         secClimateInfoOtherSp.setVisibility(View.GONE);
         lineClimateInfoOtherSp.setVisibility(View.GONE);
         secClimateKnRate.setVisibility(View.GONE);
         lineClimateKnRate.setVisibility(View.GONE);
         seclblP3.setVisibility(View.GONE);
         linelblP3.setVisibility(View.GONE);
         secClimatethreat.setVisibility(View.GONE);
         lineClimatethreat.setVisibility(View.GONE);
         secClimateWorried.setVisibility(View.GONE);
         lineClimateWorried.setVisibility(View.GONE);
         seclblQ13.setVisibility(View.GONE);
         linelblQ13.setVisibility(View.GONE);
         secClimateConcernTemp.setVisibility(View.GONE);
         lineClimateConcernTemp.setVisibility(View.GONE);
         secClimateConcernEvent.setVisibility(View.GONE);
         lineClimateConcernEvent.setVisibility(View.GONE);
         secClimateConcernPolarIce.setVisibility(View.GONE);
         lineClimateConcernPolarIce.setVisibility(View.GONE);
         secClimateConcernSeaLevel.setVisibility(View.GONE);
         lineClimateConcernSeaLevel.setVisibility(View.GONE);
         secClimateConcernBiodiversity.setVisibility(View.GONE);
         lineClimateConcernBiodiversity.setVisibility(View.GONE);
         secClimateConcernDrying.setVisibility(View.GONE);
         lineClimateConcernDrying.setVisibility(View.GONE);
         secClimateConcernRising.setVisibility(View.GONE);
         lineClimateConcernRising.setVisibility(View.GONE);
         secClimateConcernDrought.setVisibility(View.GONE);
         lineClimateConcernDrought.setVisibility(View.GONE);
         secClimateConcernDesert.setVisibility(View.GONE);
         lineClimateConcernDesert.setVisibility(View.GONE);
         secClimateConcernErosin.setVisibility(View.GONE);
         lineClimateConcernErosin.setVisibility(View.GONE);
         secClimateConcernOther.setVisibility(View.GONE);
         lineClimateConcernOther.setVisibility(View.GONE);
         secClimateConcernOtherSp.setVisibility(View.GONE);
         lineClimateConcernOtherSp.setVisibility(View.GONE);
         secClimateComDoing.setVisibility(View.GONE);
         lineClimateComDoing.setVisibility(View.GONE);
         seclblP4.setVisibility(View.GONE);
         linelblP4.setVisibility(View.GONE);
         secClimateReduceImpact.setVisibility(View.GONE);
         lineClimateReduceImpact.setVisibility(View.GONE);
         seclblQ16.setVisibility(View.GONE);
         linelblQ16.setVisibility(View.GONE);
         secClimateActionsPlastics.setVisibility(View.GONE);
         lineClimateActionsPlastics.setVisibility(View.GONE);
         secClimateActionsTransport.setVisibility(View.GONE);
         lineClimateActionsTransport.setVisibility(View.GONE);
         secClimateActionsFoods.setVisibility(View.GONE);
         lineClimateActionsFoods.setVisibility(View.GONE);
         secClimateActionsHome.setVisibility(View.GONE);
         lineClimateActionsHome.setVisibility(View.GONE);
         secClimateActionsOther.setVisibility(View.GONE);
         lineClimateActionsOther.setVisibility(View.GONE);
         secClimateActionsOtherSp.setVisibility(View.GONE);
         lineClimateActionsOtherSp.setVisibility(View.GONE);
         secClimateRenewableEnergy.setVisibility(View.GONE);
         lineClimateRenewableEnergy.setVisibility(View.GONE);
         seclblQ18.setVisibility(View.GONE);
         linelblQ18.setVisibility(View.GONE);
         secClimateObstaclesCost.setVisibility(View.GONE);
         lineClimateObstaclesCost.setVisibility(View.GONE);
        secClimateObstaclesLackInfo.setVisibility(View.GONE);
        lineClimateObstaclesLackInfo.setVisibility(View.GONE);
         secClimateObstaclesAccess.setVisibility(View.GONE);
         lineClimateObstaclesAccess.setVisibility(View.GONE);
         secClimateObstaclesHabit.setVisibility(View.GONE);
         lineClimateObstaclesHabit.setVisibility(View.GONE);
         secClimateObstaclesOther.setVisibility(View.GONE);
         lineClimateObstaclesOther.setVisibility(View.GONE);
         secClimateObstaclesOtherSp.setVisibility(View.GONE);
         lineClimateObstaclesOtherSp.setVisibility(View.GONE);
         secClimateVStatusOth.setVisibility(View.GONE);
         lineClimateVStatusOth.setVisibility(View.GONE);
         secRnd.setVisibility(View.GONE);
         lineRnd.setVisibility(View.GONE);
         seclblP1.setVisibility(View.GONE);
         linelblP1.setVisibility(View.GONE);
         secClimateChangesNoticed.setVisibility(View.GONE);
         lineClimateChangesNoticed.setVisibility(View.GONE);
         seclblQ2.setVisibility(View.GONE);
         linelblQ2.setVisibility(View.GONE);
         secClimateChangesTemp.setVisibility(View.GONE);
         lineClimateChangesTemp.setVisibility(View.GONE);
         secClimateChangesSeason.setVisibility(View.GONE);
         lineClimateChangesSeason.setVisibility(View.GONE);
         secClimateChangesWeather.setVisibility(View.GONE);
         lineClimateChangesWeather.setVisibility(View.GONE);
         secClimateChangesFlora.setVisibility(View.GONE);
         lineClimateChangesFlora.setVisibility(View.GONE);
         secClimateChangesOther.setVisibility(View.GONE);
         lineClimateChangesOther.setVisibility(View.GONE);
         secClimateChangesOtherSp.setVisibility(View.GONE);
         lineClimateChangesOtherSp.setVisibility(View.GONE);
         secClimateAffectDailyLife.setVisibility(View.GONE);
         lineClimateAffectDailyLife.setVisibility(View.GONE);
         secClimateFlood.setVisibility(View.GONE);
         lineClimateFlood.setVisibility(View.GONE);
         seclblQ5.setVisibility(View.GONE);
         linelblQ5.setVisibility(View.GONE);
         secClimateFloodHouse.setVisibility(View.GONE);
         lineClimateFloodHouse.setVisibility(View.GONE);
         secClimateFloodWall.setVisibility(View.GONE);
         lineClimateFloodWall.setVisibility(View.GONE);
         secClimateFloodMember.setVisibility(View.GONE);
         lineClimateFloodMember.setVisibility(View.GONE);
         secClimateFloodLivestock.setVisibility(View.GONE);
         lineClimateFloodLivestock.setVisibility(View.GONE);
         secClimateFloodJobLess.setVisibility(View.GONE);
         lineClimateFloodJobLess.setVisibility(View.GONE);
         secClimateFloodEnergy.setVisibility(View.GONE);
         lineClimateFloodEnergy.setVisibility(View.GONE);
         secClimateFloodToilets.setVisibility(View.GONE);
         lineClimateFloodToilets.setVisibility(View.GONE);
         secClimateFloodHealth.setVisibility(View.GONE);
         lineClimateFloodHealth.setVisibility(View.GONE);
         secClimateFloodMigration.setVisibility(View.GONE);
         lineClimateFloodMigration.setVisibility(View.GONE);
         secClimateFloodOther.setVisibility(View.GONE);
         lineClimateFloodOther.setVisibility(View.GONE);
         secClimateFloodOtherSp.setVisibility(View.GONE);
         lineClimateFloodOtherSp.setVisibility(View.GONE);
         secClimateHighHeat.setVisibility(View.GONE);
         lineClimateHighHeat.setVisibility(View.GONE);
         seclblQ7.setVisibility(View.GONE);
         linelblQ7.setVisibility(View.GONE);
         secClimateHighHeatMemberDth.setVisibility(View.GONE);
         lineClimateHighHeatMemberDth.setVisibility(View.GONE);
         secClimateHighHeatHealth.setVisibility(View.GONE);
         lineClimateHighHeatHealth.setVisibility(View.GONE);
         secClimateHighHeatMemberIll.setVisibility(View.GONE);
         lineClimateHighHeatMemberIll.setVisibility(View.GONE);
         secClimateHighHeatPetDth.setVisibility(View.GONE);
         lineClimateHighHeatPetDth.setVisibility(View.GONE);
         secClimateHighHeatOther.setVisibility(View.GONE);
         lineClimateHighHeatOther.setVisibility(View.GONE);
         secClimateHighHeatOtherSp.setVisibility(View.GONE);
         lineClimateHighHeatOtherSp.setVisibility(View.GONE);
         seclblQ7a.setVisibility(View.GONE);
         linelblQ7a.setVisibility(View.GONE);
         secClimateHealthProblemSleep.setVisibility(View.GONE);
         lineClimateHealthProblemSleep.setVisibility(View.GONE);
         secClimateHealthProblemDizzy.setVisibility(View.GONE);
         lineClimateHealthProblemDizzy.setVisibility(View.GONE);
         secClimateHealthProblemLowBlood.setVisibility(View.GONE);
         lineClimateHealthProblemLowBlood.setVisibility(View.GONE);
         secClimateHealthProblemHighBlood.setVisibility(View.GONE);
         lineClimateHealthProblemHighBlood.setVisibility(View.GONE);
         secClimateHealthProblemStroke.setVisibility(View.GONE);
         lineClimateHealthProblemStroke.setVisibility(View.GONE);
         secClimateHealthProblemFever.setVisibility(View.GONE);
         lineClimateHealthProblemFever.setVisibility(View.GONE);
         secClimateHealthProblemOther.setVisibility(View.GONE);
         lineClimateHealthProblemOther.setVisibility(View.GONE);
         secClimateHealthProblemOtherSp.setVisibility(View.GONE);
         lineClimateHealthProblemOtherSp.setVisibility(View.GONE);
         secClimateHealthProblemPeriod.setVisibility(View.GONE);
         lineClimateHealthProblemPeriod.setVisibility(View.GONE);
         seclblQ7b.setVisibility(View.GONE);
         linelblQ7b.setVisibility(View.GONE);
         secClimateIllnessMalaria.setVisibility(View.GONE);
         lineClimateIllnessMalaria.setVisibility(View.GONE);
         secClimateIllnessPneumonia.setVisibility(View.GONE);
         lineClimateIllnessPneumonia.setVisibility(View.GONE);
         secClimateIllnessMeasles.setVisibility(View.GONE);
         lineClimateIllnessMeasles.setVisibility(View.GONE);
         secClimateIllnessPertussis.setVisibility(View.GONE);
         lineClimateIllnessPertussis.setVisibility(View.GONE);
         secClimateIllnessDiarrhea.setVisibility(View.GONE);
         lineClimateIllnessDiarrhea.setVisibility(View.GONE);
         secClimateIllnessFood.setVisibility(View.GONE);
         lineClimateIllnessFood.setVisibility(View.GONE);
         secClimateIllnessTyphoid.setVisibility(View.GONE);
         lineClimateIllnessTyphoid.setVisibility(View.GONE);
         secClimateIllnessCough.setVisibility(View.GONE);
         lineClimateIllnessCough.setVisibility(View.GONE);
         secClimateIllnessCholera.setVisibility(View.GONE);
         lineClimateIllnessCholera.setVisibility(View.GONE);
         secClimateIllnessOther.setVisibility(View.GONE);
         lineClimateIllnessOther.setVisibility(View.GONE);
         secClimateIllnessOtherSp.setVisibility(View.GONE);
         lineClimateIllnessOtherSp.setVisibility(View.GONE);
         secClimateIllnessPeriod.setVisibility(View.GONE);
         lineClimateIllnessPeriod.setVisibility(View.GONE);
         seclblP2.setVisibility(View.GONE);
         linelblP2.setVisibility(View.GONE);
         secClimateHeard.setVisibility(View.GONE);
         lineClimateHeard.setVisibility(View.GONE);
         seclblQ9.setVisibility(View.GONE);
         linelblQ9.setVisibility(View.GONE);
         secClimateInfoMedia.setVisibility(View.GONE);
         lineClimateInfoMedia.setVisibility(View.GONE);
         secClimateInfoInternet.setVisibility(View.GONE);
         lineClimateInfoInternet.setVisibility(View.GONE);
         secClimateInfoEducation.setVisibility(View.GONE);
         lineClimateInfoEducation.setVisibility(View.GONE);
         secClimateInfoFnF.setVisibility(View.GONE);
         lineClimateInfoFnF.setVisibility(View.GONE);
         secClimateInfoOther.setVisibility(View.GONE);
         lineClimateInfoOther.setVisibility(View.GONE);
         secClimateInfoOtherSp.setVisibility(View.GONE);
         lineClimateInfoOtherSp.setVisibility(View.GONE);
         secClimateKnRate.setVisibility(View.GONE);
         lineClimateKnRate.setVisibility(View.GONE);
         seclblP3.setVisibility(View.GONE);
         linelblP3.setVisibility(View.GONE);
         secClimatethreat.setVisibility(View.GONE);
         lineClimatethreat.setVisibility(View.GONE);
         secClimateWorried.setVisibility(View.GONE);
         lineClimateWorried.setVisibility(View.GONE);
         seclblQ13.setVisibility(View.GONE);
         linelblQ13.setVisibility(View.GONE);
         secClimateConcernTemp.setVisibility(View.GONE);
         lineClimateConcernTemp.setVisibility(View.GONE);
         secClimateConcernEvent.setVisibility(View.GONE);
         lineClimateConcernEvent.setVisibility(View.GONE);
         secClimateConcernPolarIce.setVisibility(View.GONE);
         lineClimateConcernPolarIce.setVisibility(View.GONE);
         secClimateConcernSeaLevel.setVisibility(View.GONE);
         lineClimateConcernSeaLevel.setVisibility(View.GONE);
         secClimateConcernBiodiversity.setVisibility(View.GONE);
         lineClimateConcernBiodiversity.setVisibility(View.GONE);
         secClimateConcernDrying.setVisibility(View.GONE);
         lineClimateConcernDrying.setVisibility(View.GONE);
         secClimateConcernRising.setVisibility(View.GONE);
         lineClimateConcernRising.setVisibility(View.GONE);
         secClimateConcernDrought.setVisibility(View.GONE);
         lineClimateConcernDrought.setVisibility(View.GONE);
         secClimateConcernDesert.setVisibility(View.GONE);
         lineClimateConcernDesert.setVisibility(View.GONE);
         secClimateConcernErosin.setVisibility(View.GONE);
         lineClimateConcernErosin.setVisibility(View.GONE);
         secClimateConcernOther.setVisibility(View.GONE);
         lineClimateConcernOther.setVisibility(View.GONE);
         secClimateConcernOtherSp.setVisibility(View.GONE);
         lineClimateConcernOtherSp.setVisibility(View.GONE);
         secClimateComDoing.setVisibility(View.GONE);
         lineClimateComDoing.setVisibility(View.GONE);
         seclblP4.setVisibility(View.GONE);
         linelblP4.setVisibility(View.GONE);
         secClimateReduceImpact.setVisibility(View.GONE);
         lineClimateReduceImpact.setVisibility(View.GONE);
         seclblQ16.setVisibility(View.GONE);
         linelblQ16.setVisibility(View.GONE);
         secClimateActionsPlastics.setVisibility(View.GONE);
         lineClimateActionsPlastics.setVisibility(View.GONE);
         secClimateActionsTransport.setVisibility(View.GONE);
         lineClimateActionsTransport.setVisibility(View.GONE);
         secClimateActionsFoods.setVisibility(View.GONE);
         lineClimateActionsFoods.setVisibility(View.GONE);
         secClimateActionsHome.setVisibility(View.GONE);
         lineClimateActionsHome.setVisibility(View.GONE);
         secClimateActionsOther.setVisibility(View.GONE);
         lineClimateActionsOther.setVisibility(View.GONE);
         secClimateActionsOtherSp.setVisibility(View.GONE);
         lineClimateActionsOtherSp.setVisibility(View.GONE);
         secClimateRenewableEnergy.setVisibility(View.GONE);
         lineClimateRenewableEnergy.setVisibility(View.GONE);
         seclblQ18.setVisibility(View.GONE);
         linelblQ18.setVisibility(View.GONE);
         secClimateObstaclesCost.setVisibility(View.GONE);
         lineClimateObstaclesCost.setVisibility(View.GONE);
        secClimateObstaclesLackInfo.setVisibility(View.GONE);
        lineClimateObstaclesLackInfo.setVisibility(View.GONE);
         secClimateObstaclesAccess.setVisibility(View.GONE);
         lineClimateObstaclesAccess.setVisibility(View.GONE);
         secClimateObstaclesHabit.setVisibility(View.GONE);
         lineClimateObstaclesHabit.setVisibility(View.GONE);
         secClimateObstaclesOther.setVisibility(View.GONE);
         lineClimateObstaclesOther.setVisibility(View.GONE);
         secClimateObstaclesOtherSp.setVisibility(View.GONE);
         lineClimateObstaclesOtherSp.setVisibility(View.GONE);
         seclblQ2.setVisibility(View.GONE);
         linelblQ2.setVisibility(View.GONE);
         secClimateChangesTemp.setVisibility(View.GONE);
         lineClimateChangesTemp.setVisibility(View.GONE);
         secClimateChangesSeason.setVisibility(View.GONE);
         lineClimateChangesSeason.setVisibility(View.GONE);
         secClimateChangesWeather.setVisibility(View.GONE);
         lineClimateChangesWeather.setVisibility(View.GONE);
         secClimateChangesFlora.setVisibility(View.GONE);
         lineClimateChangesFlora.setVisibility(View.GONE);
         secClimateChangesOther.setVisibility(View.GONE);
         lineClimateChangesOther.setVisibility(View.GONE);
         secClimateChangesOtherSp.setVisibility(View.GONE);
         lineClimateChangesOtherSp.setVisibility(View.GONE);
         secClimateAffectDailyLife.setVisibility(View.GONE);
         lineClimateAffectDailyLife.setVisibility(View.GONE);
         secClimateChangesOtherSp.setVisibility(View.GONE);
         lineClimateChangesOtherSp.setVisibility(View.GONE);
         seclblQ5.setVisibility(View.GONE);
         linelblQ5.setVisibility(View.GONE);
         secClimateFloodHouse.setVisibility(View.GONE);
         lineClimateFloodHouse.setVisibility(View.GONE);
         secClimateFloodWall.setVisibility(View.GONE);
         lineClimateFloodWall.setVisibility(View.GONE);
         secClimateFloodMember.setVisibility(View.GONE);
         lineClimateFloodMember.setVisibility(View.GONE);
         secClimateFloodLivestock.setVisibility(View.GONE);
         lineClimateFloodLivestock.setVisibility(View.GONE);
         secClimateFloodJobLess.setVisibility(View.GONE);
         lineClimateFloodJobLess.setVisibility(View.GONE);
         secClimateFloodEnergy.setVisibility(View.GONE);
         lineClimateFloodEnergy.setVisibility(View.GONE);
         secClimateFloodToilets.setVisibility(View.GONE);
         lineClimateFloodToilets.setVisibility(View.GONE);
         secClimateFloodHealth.setVisibility(View.GONE);
         lineClimateFloodHealth.setVisibility(View.GONE);
         secClimateFloodMigration.setVisibility(View.GONE);
         lineClimateFloodMigration.setVisibility(View.GONE);
         secClimateFloodOther.setVisibility(View.GONE);
         lineClimateFloodOther.setVisibility(View.GONE);
         secClimateFloodOtherSp.setVisibility(View.GONE);
         lineClimateFloodOtherSp.setVisibility(View.GONE);
         secClimateFloodOtherSp.setVisibility(View.GONE);
         lineClimateFloodOtherSp.setVisibility(View.GONE);
         seclblQ7.setVisibility(View.GONE);
         linelblQ7.setVisibility(View.GONE);
         secClimateHighHeatMemberDth.setVisibility(View.GONE);
         lineClimateHighHeatMemberDth.setVisibility(View.GONE);
         secClimateHighHeatHealth.setVisibility(View.GONE);
         lineClimateHighHeatHealth.setVisibility(View.GONE);
         secClimateHighHeatMemberIll.setVisibility(View.GONE);
         lineClimateHighHeatMemberIll.setVisibility(View.GONE);
         secClimateHighHeatPetDth.setVisibility(View.GONE);
         lineClimateHighHeatPetDth.setVisibility(View.GONE);
         secClimateHighHeatOther.setVisibility(View.GONE);
         lineClimateHighHeatOther.setVisibility(View.GONE);
         secClimateHighHeatOtherSp.setVisibility(View.GONE);
         lineClimateHighHeatOtherSp.setVisibility(View.GONE);
         secClimateHighHeatMemberIll.setVisibility(View.GONE);
         lineClimateHighHeatMemberIll.setVisibility(View.GONE);
         secClimateHighHeatPetDth.setVisibility(View.GONE);
         lineClimateHighHeatPetDth.setVisibility(View.GONE);
         secClimateHighHeatOther.setVisibility(View.GONE);
         lineClimateHighHeatOther.setVisibility(View.GONE);
         secClimateHighHeatOtherSp.setVisibility(View.GONE);
         lineClimateHighHeatOtherSp.setVisibility(View.GONE);
         seclblQ7a.setVisibility(View.GONE);
         linelblQ7a.setVisibility(View.GONE);
         secClimateHealthProblemSleep.setVisibility(View.GONE);
         lineClimateHealthProblemSleep.setVisibility(View.GONE);
         secClimateHealthProblemDizzy.setVisibility(View.GONE);
         lineClimateHealthProblemDizzy.setVisibility(View.GONE);
         secClimateHealthProblemLowBlood.setVisibility(View.GONE);
         lineClimateHealthProblemLowBlood.setVisibility(View.GONE);
         secClimateHealthProblemHighBlood.setVisibility(View.GONE);
         lineClimateHealthProblemHighBlood.setVisibility(View.GONE);
         secClimateHealthProblemStroke.setVisibility(View.GONE);
         lineClimateHealthProblemStroke.setVisibility(View.GONE);
         secClimateHealthProblemFever.setVisibility(View.GONE);
         lineClimateHealthProblemFever.setVisibility(View.GONE);
         secClimateHealthProblemOther.setVisibility(View.GONE);
         lineClimateHealthProblemOther.setVisibility(View.GONE);
         secClimateHealthProblemOtherSp.setVisibility(View.GONE);
         lineClimateHealthProblemOtherSp.setVisibility(View.GONE);
         secClimateHealthProblemPeriod.setVisibility(View.GONE);
         lineClimateHealthProblemPeriod.setVisibility(View.GONE);
         secClimateHighHeatOtherSp.setVisibility(View.GONE);
         lineClimateHighHeatOtherSp.setVisibility(View.GONE);
         secClimateHealthProblemOtherSp.setVisibility(View.GONE);
         lineClimateHealthProblemOtherSp.setVisibility(View.GONE);
         secClimateIllnessOtherSp.setVisibility(View.GONE);
         lineClimateIllnessOtherSp.setVisibility(View.GONE);
         seclblQ9.setVisibility(View.GONE);
         linelblQ9.setVisibility(View.GONE);
         secClimateInfoMedia.setVisibility(View.GONE);
         lineClimateInfoMedia.setVisibility(View.GONE);
         secClimateInfoInternet.setVisibility(View.GONE);
         lineClimateInfoInternet.setVisibility(View.GONE);
         secClimateInfoEducation.setVisibility(View.GONE);
         lineClimateInfoEducation.setVisibility(View.GONE);
         secClimateInfoFnF.setVisibility(View.GONE);
         lineClimateInfoFnF.setVisibility(View.GONE);
         secClimateInfoOther.setVisibility(View.GONE);
         lineClimateInfoOther.setVisibility(View.GONE);
         secClimateInfoOtherSp.setVisibility(View.GONE);
         lineClimateInfoOtherSp.setVisibility(View.GONE);
         secClimateKnRate.setVisibility(View.GONE);
         lineClimateKnRate.setVisibility(View.GONE);
         seclblP3.setVisibility(View.GONE);
         linelblP3.setVisibility(View.GONE);
         secClimatethreat.setVisibility(View.GONE);
         lineClimatethreat.setVisibility(View.GONE);
         secClimateWorried.setVisibility(View.GONE);
         lineClimateWorried.setVisibility(View.GONE);
         seclblQ13.setVisibility(View.GONE);
         linelblQ13.setVisibility(View.GONE);
         secClimateConcernTemp.setVisibility(View.GONE);
         lineClimateConcernTemp.setVisibility(View.GONE);
         secClimateConcernEvent.setVisibility(View.GONE);
         lineClimateConcernEvent.setVisibility(View.GONE);
         secClimateConcernPolarIce.setVisibility(View.GONE);
         lineClimateConcernPolarIce.setVisibility(View.GONE);
         secClimateConcernSeaLevel.setVisibility(View.GONE);
         lineClimateConcernSeaLevel.setVisibility(View.GONE);
         secClimateConcernBiodiversity.setVisibility(View.GONE);
         lineClimateConcernBiodiversity.setVisibility(View.GONE);
         secClimateConcernDrying.setVisibility(View.GONE);
         lineClimateConcernDrying.setVisibility(View.GONE);
         secClimateConcernRising.setVisibility(View.GONE);
         lineClimateConcernRising.setVisibility(View.GONE);
         secClimateConcernDrought.setVisibility(View.GONE);
         lineClimateConcernDrought.setVisibility(View.GONE);
         secClimateConcernDesert.setVisibility(View.GONE);
         lineClimateConcernDesert.setVisibility(View.GONE);
         secClimateConcernErosin.setVisibility(View.GONE);
         lineClimateConcernErosin.setVisibility(View.GONE);
         secClimateConcernOther.setVisibility(View.GONE);
         lineClimateConcernOther.setVisibility(View.GONE);
         secClimateConcernOtherSp.setVisibility(View.GONE);
         lineClimateConcernOtherSp.setVisibility(View.GONE);
         secClimateComDoing.setVisibility(View.GONE);
         lineClimateComDoing.setVisibility(View.GONE);
         secClimateInfoOtherSp.setVisibility(View.GONE);
         lineClimateInfoOtherSp.setVisibility(View.GONE);
         secClimateConcernOtherSp.setVisibility(View.GONE);
         lineClimateConcernOtherSp.setVisibility(View.GONE);
         seclblQ16.setVisibility(View.GONE);
         linelblQ16.setVisibility(View.GONE);
         secClimateActionsPlastics.setVisibility(View.GONE);
         lineClimateActionsPlastics.setVisibility(View.GONE);
         secClimateActionsTransport.setVisibility(View.GONE);
         lineClimateActionsTransport.setVisibility(View.GONE);
         secClimateActionsFoods.setVisibility(View.GONE);
         lineClimateActionsFoods.setVisibility(View.GONE);
         secClimateActionsHome.setVisibility(View.GONE);
         lineClimateActionsHome.setVisibility(View.GONE);
         secClimateActionsOther.setVisibility(View.GONE);
         lineClimateActionsOther.setVisibility(View.GONE);
         secClimateActionsOtherSp.setVisibility(View.GONE);
         lineClimateActionsOtherSp.setVisibility(View.GONE);
         secClimateActionsOtherSp.setVisibility(View.GONE);
         lineClimateActionsOtherSp.setVisibility(View.GONE);
         secClimateObstaclesOtherSp.setVisibility(View.GONE);
         lineClimateObstaclesOtherSp.setVisibility(View.GONE);


        DataSearch(HHID,CLIMATESL);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});

        Global.RoleManagement(cmdSave, MySharedPreferences.getValue(this, "userrole"));
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Climate_Change.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         secHHID=(LinearLayout)findViewById(R.id.secHHID);
         lineHHID=(View)findViewById(R.id.lineHHID);
         VlblHHID=(TextView) findViewById(R.id.VlblHHID);
         txtHHID=(EditText) findViewById(R.id.txtHHID);
         txtHHID.setText(HHID);
         txtHHID.setEnabled(false);
         secClimateSL=(LinearLayout)findViewById(R.id.secClimateSL);
         lineClimateSL=(View)findViewById(R.id.lineClimateSL);
         VlblClimateSL=(TextView) findViewById(R.id.VlblClimateSL);
         txtClimateSL=(EditText) findViewById(R.id.txtClimateSL);
        if(CLIMATESL.length()==0) txtClimateSL.setText(NewClimateSl(HHID));
        else txtClimateSL.setText(CLIMATESL);
         txtClimateSL.setEnabled(false);
         secClimateVDate=(LinearLayout)findViewById(R.id.secClimateVDate);
         lineClimateVDate=(View)findViewById(R.id.lineClimateVDate);
         VlblClimateVDate=(TextView) findViewById(R.id.VlblClimateVDate);
         dtpClimateVDate=(EditText) findViewById(R.id.dtpClimateVDate);
         secClimateVStatus=(LinearLayout)findViewById(R.id.secClimateVStatus);
         lineClimateVStatus=(View)findViewById(R.id.lineClimateVStatus);
         VlblClimateVStatus = (TextView) findViewById(R.id.VlblClimateVStatus);
         rdogrpClimateVStatus = (RadioGroup) findViewById(R.id.rdogrpClimateVStatus);
         rdoClimateVStatus1 = (RadioButton) findViewById(R.id.rdoClimateVStatus1);
         rdoClimateVStatus2 = (RadioButton) findViewById(R.id.rdoClimateVStatus2);
         rdoClimateVStatus3 = (RadioButton) findViewById(R.id.rdoClimateVStatus3);
         rdoClimateVStatus4 = (RadioButton) findViewById(R.id.rdoClimateVStatus4);
         rdogrpClimateVStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpClimateVStatus = new String[] {"1","2","3","9"};
             for (int i = 0; i < rdogrpClimateVStatus.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpClimateVStatus.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpClimateVStatus[i];
             }

             if(rbData.equalsIgnoreCase("3"))
             {
                    secClimateVStatusOth.setVisibility(View.GONE);
                    lineClimateVStatusOth.setVisibility(View.GONE);
                    txtClimateVStatusOth.setText("");
                    seclblP1.setVisibility(View.GONE);
                    linelblP1.setVisibility(View.GONE);
                    secClimateChangesNoticed.setVisibility(View.GONE);
                    lineClimateChangesNoticed.setVisibility(View.GONE);
                    rdogrpClimateChangesNoticed.clearCheck();
                    seclblQ2.setVisibility(View.GONE);
                    linelblQ2.setVisibility(View.GONE);
                    secClimateChangesTemp.setVisibility(View.GONE);
                    lineClimateChangesTemp.setVisibility(View.GONE);
                    chkClimateChangesTemp.setChecked(false);
                    secClimateChangesSeason.setVisibility(View.GONE);
                    lineClimateChangesSeason.setVisibility(View.GONE);
                    chkClimateChangesSeason.setChecked(false);
                    secClimateChangesWeather.setVisibility(View.GONE);
                    lineClimateChangesWeather.setVisibility(View.GONE);
                    chkClimateChangesWeather.setChecked(false);
                    secClimateChangesFlora.setVisibility(View.GONE);
                    lineClimateChangesFlora.setVisibility(View.GONE);
                    chkClimateChangesFlora.setChecked(false);
                    secClimateChangesOther.setVisibility(View.GONE);
                    lineClimateChangesOther.setVisibility(View.GONE);
                    chkClimateChangesOther.setChecked(false);
                    secClimateChangesOtherSp.setVisibility(View.GONE);
                    lineClimateChangesOtherSp.setVisibility(View.GONE);
                    txtClimateChangesOtherSp.setText("");
                    secClimateAffectDailyLife.setVisibility(View.GONE);
                    lineClimateAffectDailyLife.setVisibility(View.GONE);
                    spnClimateAffectDailyLife.setSelection(0);
                    secClimateFlood.setVisibility(View.GONE);
                    lineClimateFlood.setVisibility(View.GONE);
                    rdogrpClimateFlood.clearCheck();
                    seclblQ5.setVisibility(View.GONE);
                    linelblQ5.setVisibility(View.GONE);
                    secClimateFloodHouse.setVisibility(View.GONE);
                    lineClimateFloodHouse.setVisibility(View.GONE);
                    chkClimateFloodHouse.setChecked(false);
                    secClimateFloodWall.setVisibility(View.GONE);
                    lineClimateFloodWall.setVisibility(View.GONE);
                    chkClimateFloodWall.setChecked(false);
                    secClimateFloodMember.setVisibility(View.GONE);
                    lineClimateFloodMember.setVisibility(View.GONE);
                    chkClimateFloodMember.setChecked(false);
                    secClimateFloodLivestock.setVisibility(View.GONE);
                    lineClimateFloodLivestock.setVisibility(View.GONE);
                    chkClimateFloodLivestock.setChecked(false);
                    secClimateFloodJobLess.setVisibility(View.GONE);
                    lineClimateFloodJobLess.setVisibility(View.GONE);
                    chkClimateFloodJobLess.setChecked(false);
                    secClimateFloodEnergy.setVisibility(View.GONE);
                    lineClimateFloodEnergy.setVisibility(View.GONE);
                    chkClimateFloodEnergy.setChecked(false);
                    secClimateFloodToilets.setVisibility(View.GONE);
                    lineClimateFloodToilets.setVisibility(View.GONE);
                    chkClimateFloodToilets.setChecked(false);
                    secClimateFloodHealth.setVisibility(View.GONE);
                    lineClimateFloodHealth.setVisibility(View.GONE);
                    chkClimateFloodHealth.setChecked(false);
                    secClimateFloodMigration.setVisibility(View.GONE);
                    lineClimateFloodMigration.setVisibility(View.GONE);
                    chkClimateFloodMigration.setChecked(false);
                    secClimateFloodOther.setVisibility(View.GONE);
                    lineClimateFloodOther.setVisibility(View.GONE);
                    chkClimateFloodOther.setChecked(false);
                    secClimateFloodOtherSp.setVisibility(View.GONE);
                    lineClimateFloodOtherSp.setVisibility(View.GONE);
                    txtClimateFloodOtherSp.setText("");
                    secClimateHighHeat.setVisibility(View.GONE);
                    lineClimateHighHeat.setVisibility(View.GONE);
                    rdogrpClimateHighHeat.clearCheck();
                    seclblQ7.setVisibility(View.GONE);
                    linelblQ7.setVisibility(View.GONE);
                    secClimateHighHeatMemberDth.setVisibility(View.GONE);
                    lineClimateHighHeatMemberDth.setVisibility(View.GONE);
                    chkClimateHighHeatMemberDth.setChecked(false);
                    secClimateHighHeatHealth.setVisibility(View.GONE);
                    lineClimateHighHeatHealth.setVisibility(View.GONE);
                    chkClimateHighHeatHealth.setChecked(false);
                    secClimateHighHeatMemberIll.setVisibility(View.GONE);
                    lineClimateHighHeatMemberIll.setVisibility(View.GONE);
                    chkClimateHighHeatMemberIll.setChecked(false);
                    secClimateHighHeatPetDth.setVisibility(View.GONE);
                    lineClimateHighHeatPetDth.setVisibility(View.GONE);
                    chkClimateHighHeatPetDth.setChecked(false);
                    secClimateHighHeatOther.setVisibility(View.GONE);
                    lineClimateHighHeatOther.setVisibility(View.GONE);
                    chkClimateHighHeatOther.setChecked(false);
                    secClimateHighHeatOtherSp.setVisibility(View.GONE);
                    lineClimateHighHeatOtherSp.setVisibility(View.GONE);
                    txtClimateHighHeatOtherSp.setText("");
                    seclblQ7a.setVisibility(View.GONE);
                    linelblQ7a.setVisibility(View.GONE);
                    secClimateHealthProblemSleep.setVisibility(View.GONE);
                    lineClimateHealthProblemSleep.setVisibility(View.GONE);
                    chkClimateHealthProblemSleep.setChecked(false);
                    secClimateHealthProblemDizzy.setVisibility(View.GONE);
                    lineClimateHealthProblemDizzy.setVisibility(View.GONE);
                    chkClimateHealthProblemDizzy.setChecked(false);
                    secClimateHealthProblemLowBlood.setVisibility(View.GONE);
                    lineClimateHealthProblemLowBlood.setVisibility(View.GONE);
                    chkClimateHealthProblemLowBlood.setChecked(false);
                    secClimateHealthProblemHighBlood.setVisibility(View.GONE);
                    lineClimateHealthProblemHighBlood.setVisibility(View.GONE);
                    chkClimateHealthProblemHighBlood.setChecked(false);
                    secClimateHealthProblemStroke.setVisibility(View.GONE);
                    lineClimateHealthProblemStroke.setVisibility(View.GONE);
                    chkClimateHealthProblemStroke.setChecked(false);
                    secClimateHealthProblemFever.setVisibility(View.GONE);
                    lineClimateHealthProblemFever.setVisibility(View.GONE);
                    chkClimateHealthProblemFever.setChecked(false);
                    secClimateHealthProblemOther.setVisibility(View.GONE);
                    lineClimateHealthProblemOther.setVisibility(View.GONE);
                    chkClimateHealthProblemOther.setChecked(false);
                    secClimateHealthProblemOtherSp.setVisibility(View.GONE);
                    lineClimateHealthProblemOtherSp.setVisibility(View.GONE);
                    txtClimateHealthProblemOtherSp.setText("");
                    secClimateHealthProblemPeriod.setVisibility(View.GONE);
                    lineClimateHealthProblemPeriod.setVisibility(View.GONE);
                    spnClimateHealthProblemPeriod.setSelection(0);
                    seclblQ7b.setVisibility(View.GONE);
                    linelblQ7b.setVisibility(View.GONE);
                    secClimateIllnessMalaria.setVisibility(View.GONE);
                    lineClimateIllnessMalaria.setVisibility(View.GONE);
                    chkClimateIllnessMalaria.setChecked(false);
                    secClimateIllnessPneumonia.setVisibility(View.GONE);
                    lineClimateIllnessPneumonia.setVisibility(View.GONE);
                    chkClimateIllnessPneumonia.setChecked(false);
                    secClimateIllnessMeasles.setVisibility(View.GONE);
                    lineClimateIllnessMeasles.setVisibility(View.GONE);
                    chkClimateIllnessMeasles.setChecked(false);
                    secClimateIllnessPertussis.setVisibility(View.GONE);
                    lineClimateIllnessPertussis.setVisibility(View.GONE);
                    chkClimateIllnessPertussis.setChecked(false);
                    secClimateIllnessDiarrhea.setVisibility(View.GONE);
                    lineClimateIllnessDiarrhea.setVisibility(View.GONE);
                    chkClimateIllnessDiarrhea.setChecked(false);
                    secClimateIllnessFood.setVisibility(View.GONE);
                    lineClimateIllnessFood.setVisibility(View.GONE);
                    chkClimateIllnessFood.setChecked(false);
                    secClimateIllnessTyphoid.setVisibility(View.GONE);
                    lineClimateIllnessTyphoid.setVisibility(View.GONE);
                    chkClimateIllnessTyphoid.setChecked(false);
                    secClimateIllnessCough.setVisibility(View.GONE);
                    lineClimateIllnessCough.setVisibility(View.GONE);
                    chkClimateIllnessCough.setChecked(false);
                    secClimateIllnessCholera.setVisibility(View.GONE);
                    lineClimateIllnessCholera.setVisibility(View.GONE);
                    chkClimateIllnessCholera.setChecked(false);
                    secClimateIllnessOther.setVisibility(View.GONE);
                    lineClimateIllnessOther.setVisibility(View.GONE);
                    chkClimateIllnessOther.setChecked(false);
                    secClimateIllnessOtherSp.setVisibility(View.GONE);
                    lineClimateIllnessOtherSp.setVisibility(View.GONE);
                    txtClimateIllnessOtherSp.setText("");
                    secClimateIllnessPeriod.setVisibility(View.GONE);
                    lineClimateIllnessPeriod.setVisibility(View.GONE);
                    spnClimateIllnessPeriod.setSelection(0);
                    seclblP2.setVisibility(View.GONE);
                    linelblP2.setVisibility(View.GONE);
                    secClimateHeard.setVisibility(View.GONE);
                    lineClimateHeard.setVisibility(View.GONE);
                    rdogrpClimateHeard.clearCheck();
                    seclblQ9.setVisibility(View.GONE);
                    linelblQ9.setVisibility(View.GONE);
                    secClimateInfoMedia.setVisibility(View.GONE);
                    lineClimateInfoMedia.setVisibility(View.GONE);
                    chkClimateInfoMedia.setChecked(false);
                    secClimateInfoInternet.setVisibility(View.GONE);
                    lineClimateInfoInternet.setVisibility(View.GONE);
                    chkClimateInfoInternet.setChecked(false);
                    secClimateInfoEducation.setVisibility(View.GONE);
                    lineClimateInfoEducation.setVisibility(View.GONE);
                    chkClimateInfoEducation.setChecked(false);
                    secClimateInfoFnF.setVisibility(View.GONE);
                    lineClimateInfoFnF.setVisibility(View.GONE);
                    chkClimateInfoFnF.setChecked(false);
                    secClimateInfoOther.setVisibility(View.GONE);
                    lineClimateInfoOther.setVisibility(View.GONE);
                    chkClimateInfoOther.setChecked(false);
                    secClimateInfoOtherSp.setVisibility(View.GONE);
                    lineClimateInfoOtherSp.setVisibility(View.GONE);
                    txtClimateInfoOtherSp.setText("");
                    secClimateKnRate.setVisibility(View.GONE);
                    lineClimateKnRate.setVisibility(View.GONE);
                    spnClimateKnRate.setSelection(0);
                    seclblP3.setVisibility(View.GONE);
                    linelblP3.setVisibility(View.GONE);
                    secClimatethreat.setVisibility(View.GONE);
                    lineClimatethreat.setVisibility(View.GONE);
                    rdogrpClimatethreat.clearCheck();
                    secClimateWorried.setVisibility(View.GONE);
                    lineClimateWorried.setVisibility(View.GONE);
                    spnClimateWorried.setSelection(0);
                    seclblQ13.setVisibility(View.GONE);
                    linelblQ13.setVisibility(View.GONE);
                    secClimateConcernTemp.setVisibility(View.GONE);
                    lineClimateConcernTemp.setVisibility(View.GONE);
                    chkClimateConcernTemp.setChecked(false);
                    secClimateConcernEvent.setVisibility(View.GONE);
                    lineClimateConcernEvent.setVisibility(View.GONE);
                    chkClimateConcernEvent.setChecked(false);
                    secClimateConcernPolarIce.setVisibility(View.GONE);
                    lineClimateConcernPolarIce.setVisibility(View.GONE);
                    chkClimateConcernPolarIce.setChecked(false);
                    secClimateConcernSeaLevel.setVisibility(View.GONE);
                    lineClimateConcernSeaLevel.setVisibility(View.GONE);
                    chkClimateConcernSeaLevel.setChecked(false);
                    secClimateConcernBiodiversity.setVisibility(View.GONE);
                    lineClimateConcernBiodiversity.setVisibility(View.GONE);
                    chkClimateConcernBiodiversity.setChecked(false);
                    secClimateConcernDrying.setVisibility(View.GONE);
                    lineClimateConcernDrying.setVisibility(View.GONE);
                    chkClimateConcernDrying.setChecked(false);
                    secClimateConcernRising.setVisibility(View.GONE);
                    lineClimateConcernRising.setVisibility(View.GONE);
                    chkClimateConcernRising.setChecked(false);
                    secClimateConcernDrought.setVisibility(View.GONE);
                    lineClimateConcernDrought.setVisibility(View.GONE);
                    chkClimateConcernDrought.setChecked(false);
                    secClimateConcernDesert.setVisibility(View.GONE);
                    lineClimateConcernDesert.setVisibility(View.GONE);
                    chkClimateConcernDesert.setChecked(false);
                    secClimateConcernErosin.setVisibility(View.GONE);
                    lineClimateConcernErosin.setVisibility(View.GONE);
                    chkClimateConcernErosin.setChecked(false);
                    secClimateConcernOther.setVisibility(View.GONE);
                    lineClimateConcernOther.setVisibility(View.GONE);
                    chkClimateConcernOther.setChecked(false);
                    secClimateConcernOtherSp.setVisibility(View.GONE);
                    lineClimateConcernOtherSp.setVisibility(View.GONE);
                    txtClimateConcernOtherSp.setText("");
                    secClimateComDoing.setVisibility(View.GONE);
                    lineClimateComDoing.setVisibility(View.GONE);
                    rdogrpClimateComDoing.clearCheck();
                    seclblP4.setVisibility(View.GONE);
                    linelblP4.setVisibility(View.GONE);
                    secClimateReduceImpact.setVisibility(View.GONE);
                    lineClimateReduceImpact.setVisibility(View.GONE);
                    rdogrpClimateReduceImpact.clearCheck();
                    seclblQ16.setVisibility(View.GONE);
                    linelblQ16.setVisibility(View.GONE);
                    secClimateActionsPlastics.setVisibility(View.GONE);
                    lineClimateActionsPlastics.setVisibility(View.GONE);
                    chkClimateActionsPlastics.setChecked(false);
                    secClimateActionsTransport.setVisibility(View.GONE);
                    lineClimateActionsTransport.setVisibility(View.GONE);
                    chkClimateActionsTransport.setChecked(false);
                    secClimateActionsFoods.setVisibility(View.GONE);
                    lineClimateActionsFoods.setVisibility(View.GONE);
                    chkClimateActionsFoods.setChecked(false);
                    secClimateActionsHome.setVisibility(View.GONE);
                    lineClimateActionsHome.setVisibility(View.GONE);
                    chkClimateActionsHome.setChecked(false);
                    secClimateActionsOther.setVisibility(View.GONE);
                    lineClimateActionsOther.setVisibility(View.GONE);
                    chkClimateActionsOther.setChecked(false);
                    secClimateActionsOtherSp.setVisibility(View.GONE);
                    lineClimateActionsOtherSp.setVisibility(View.GONE);
                    txtClimateActionsOtherSp.setText("");
                    secClimateRenewableEnergy.setVisibility(View.GONE);
                    lineClimateRenewableEnergy.setVisibility(View.GONE);
                    rdogrpClimateRenewableEnergy.clearCheck();
                    seclblQ18.setVisibility(View.GONE);
                    linelblQ18.setVisibility(View.GONE);
                    secClimateObstaclesCost.setVisibility(View.GONE);
                    lineClimateObstaclesCost.setVisibility(View.GONE);
                    chkClimateObstaclesCost.setChecked(false);
                secClimateObstaclesLackInfo.setVisibility(View.GONE);
                lineClimateObstaclesLackInfo.setVisibility(View.GONE);
                chkClimateObstaclesLackInfo.setChecked(false);
                    secClimateObstaclesAccess.setVisibility(View.GONE);
                    lineClimateObstaclesAccess.setVisibility(View.GONE);
                    chkClimateObstaclesAccess.setChecked(false);
                    secClimateObstaclesHabit.setVisibility(View.GONE);
                    lineClimateObstaclesHabit.setVisibility(View.GONE);
                    chkClimateObstaclesHabit.setChecked(false);
                    secClimateObstaclesOther.setVisibility(View.GONE);
                    lineClimateObstaclesOther.setVisibility(View.GONE);
                    chkClimateObstaclesOther.setChecked(false);
                    secClimateObstaclesOtherSp.setVisibility(View.GONE);
                    lineClimateObstaclesOtherSp.setVisibility(View.GONE);
                    txtClimateObstaclesOtherSp.setText("");
             }
             else if(rbData.equalsIgnoreCase("9"))
             {
                    secClimateVStatusOth.setVisibility(View.VISIBLE);
                    lineClimateVStatusOth.setVisibility(View.VISIBLE);
                    seclblP1.setVisibility(View.GONE);
                    linelblP1.setVisibility(View.GONE);
                    secClimateChangesNoticed.setVisibility(View.GONE);
                    lineClimateChangesNoticed.setVisibility(View.GONE);
                    rdogrpClimateChangesNoticed.clearCheck();
                    seclblQ2.setVisibility(View.GONE);
                    linelblQ2.setVisibility(View.GONE);
                    secClimateChangesTemp.setVisibility(View.GONE);
                    lineClimateChangesTemp.setVisibility(View.GONE);
                    chkClimateChangesTemp.setChecked(false);
                    secClimateChangesSeason.setVisibility(View.GONE);
                    lineClimateChangesSeason.setVisibility(View.GONE);
                    chkClimateChangesSeason.setChecked(false);
                    secClimateChangesWeather.setVisibility(View.GONE);
                    lineClimateChangesWeather.setVisibility(View.GONE);
                    chkClimateChangesWeather.setChecked(false);
                    secClimateChangesFlora.setVisibility(View.GONE);
                    lineClimateChangesFlora.setVisibility(View.GONE);
                    chkClimateChangesFlora.setChecked(false);
                    secClimateChangesOther.setVisibility(View.GONE);
                    lineClimateChangesOther.setVisibility(View.GONE);
                    chkClimateChangesOther.setChecked(false);
                    secClimateChangesOtherSp.setVisibility(View.GONE);
                    lineClimateChangesOtherSp.setVisibility(View.GONE);
                    txtClimateChangesOtherSp.setText("");
                    secClimateAffectDailyLife.setVisibility(View.GONE);
                    lineClimateAffectDailyLife.setVisibility(View.GONE);
                    spnClimateAffectDailyLife.setSelection(0);
                    secClimateFlood.setVisibility(View.GONE);
                    lineClimateFlood.setVisibility(View.GONE);
                    rdogrpClimateFlood.clearCheck();
                    seclblQ5.setVisibility(View.GONE);
                    linelblQ5.setVisibility(View.GONE);
                    secClimateFloodHouse.setVisibility(View.GONE);
                    lineClimateFloodHouse.setVisibility(View.GONE);
                    chkClimateFloodHouse.setChecked(false);
                    secClimateFloodWall.setVisibility(View.GONE);
                    lineClimateFloodWall.setVisibility(View.GONE);
                    chkClimateFloodWall.setChecked(false);
                    secClimateFloodMember.setVisibility(View.GONE);
                    lineClimateFloodMember.setVisibility(View.GONE);
                    chkClimateFloodMember.setChecked(false);
                    secClimateFloodLivestock.setVisibility(View.GONE);
                    lineClimateFloodLivestock.setVisibility(View.GONE);
                    chkClimateFloodLivestock.setChecked(false);
                    secClimateFloodJobLess.setVisibility(View.GONE);
                    lineClimateFloodJobLess.setVisibility(View.GONE);
                    chkClimateFloodJobLess.setChecked(false);
                    secClimateFloodEnergy.setVisibility(View.GONE);
                    lineClimateFloodEnergy.setVisibility(View.GONE);
                    chkClimateFloodEnergy.setChecked(false);
                    secClimateFloodToilets.setVisibility(View.GONE);
                    lineClimateFloodToilets.setVisibility(View.GONE);
                    chkClimateFloodToilets.setChecked(false);
                    secClimateFloodHealth.setVisibility(View.GONE);
                    lineClimateFloodHealth.setVisibility(View.GONE);
                    chkClimateFloodHealth.setChecked(false);
                    secClimateFloodMigration.setVisibility(View.GONE);
                    lineClimateFloodMigration.setVisibility(View.GONE);
                    chkClimateFloodMigration.setChecked(false);
                    secClimateFloodOther.setVisibility(View.GONE);
                    lineClimateFloodOther.setVisibility(View.GONE);
                    chkClimateFloodOther.setChecked(false);
                    secClimateFloodOtherSp.setVisibility(View.GONE);
                    lineClimateFloodOtherSp.setVisibility(View.GONE);
                    txtClimateFloodOtherSp.setText("");
                    secClimateHighHeat.setVisibility(View.GONE);
                    lineClimateHighHeat.setVisibility(View.GONE);
                    rdogrpClimateHighHeat.clearCheck();
                    seclblQ7.setVisibility(View.GONE);
                    linelblQ7.setVisibility(View.GONE);
                    secClimateHighHeatMemberDth.setVisibility(View.GONE);
                    lineClimateHighHeatMemberDth.setVisibility(View.GONE);
                    chkClimateHighHeatMemberDth.setChecked(false);
                    secClimateHighHeatHealth.setVisibility(View.GONE);
                    lineClimateHighHeatHealth.setVisibility(View.GONE);
                    chkClimateHighHeatHealth.setChecked(false);
                    secClimateHighHeatMemberIll.setVisibility(View.GONE);
                    lineClimateHighHeatMemberIll.setVisibility(View.GONE);
                    chkClimateHighHeatMemberIll.setChecked(false);
                    secClimateHighHeatPetDth.setVisibility(View.GONE);
                    lineClimateHighHeatPetDth.setVisibility(View.GONE);
                    chkClimateHighHeatPetDth.setChecked(false);
                    secClimateHighHeatOther.setVisibility(View.GONE);
                    lineClimateHighHeatOther.setVisibility(View.GONE);
                    chkClimateHighHeatOther.setChecked(false);
                    secClimateHighHeatOtherSp.setVisibility(View.GONE);
                    lineClimateHighHeatOtherSp.setVisibility(View.GONE);
                    txtClimateHighHeatOtherSp.setText("");
                    seclblQ7a.setVisibility(View.GONE);
                    linelblQ7a.setVisibility(View.GONE);
                    secClimateHealthProblemSleep.setVisibility(View.GONE);
                    lineClimateHealthProblemSleep.setVisibility(View.GONE);
                    chkClimateHealthProblemSleep.setChecked(false);
                    secClimateHealthProblemDizzy.setVisibility(View.GONE);
                    lineClimateHealthProblemDizzy.setVisibility(View.GONE);
                    chkClimateHealthProblemDizzy.setChecked(false);
                    secClimateHealthProblemLowBlood.setVisibility(View.GONE);
                    lineClimateHealthProblemLowBlood.setVisibility(View.GONE);
                    chkClimateHealthProblemLowBlood.setChecked(false);
                    secClimateHealthProblemHighBlood.setVisibility(View.GONE);
                    lineClimateHealthProblemHighBlood.setVisibility(View.GONE);
                    chkClimateHealthProblemHighBlood.setChecked(false);
                    secClimateHealthProblemStroke.setVisibility(View.GONE);
                    lineClimateHealthProblemStroke.setVisibility(View.GONE);
                    chkClimateHealthProblemStroke.setChecked(false);
                    secClimateHealthProblemFever.setVisibility(View.GONE);
                    lineClimateHealthProblemFever.setVisibility(View.GONE);
                    chkClimateHealthProblemFever.setChecked(false);
                    secClimateHealthProblemOther.setVisibility(View.GONE);
                    lineClimateHealthProblemOther.setVisibility(View.GONE);
                    chkClimateHealthProblemOther.setChecked(false);
                    secClimateHealthProblemOtherSp.setVisibility(View.GONE);
                    lineClimateHealthProblemOtherSp.setVisibility(View.GONE);
                    txtClimateHealthProblemOtherSp.setText("");
                    secClimateHealthProblemPeriod.setVisibility(View.GONE);
                    lineClimateHealthProblemPeriod.setVisibility(View.GONE);
                    spnClimateHealthProblemPeriod.setSelection(0);
                    seclblQ7b.setVisibility(View.GONE);
                    linelblQ7b.setVisibility(View.GONE);
                    secClimateIllnessMalaria.setVisibility(View.GONE);
                    lineClimateIllnessMalaria.setVisibility(View.GONE);
                    chkClimateIllnessMalaria.setChecked(false);
                    secClimateIllnessPneumonia.setVisibility(View.GONE);
                    lineClimateIllnessPneumonia.setVisibility(View.GONE);
                    chkClimateIllnessPneumonia.setChecked(false);
                    secClimateIllnessMeasles.setVisibility(View.GONE);
                    lineClimateIllnessMeasles.setVisibility(View.GONE);
                    chkClimateIllnessMeasles.setChecked(false);
                    secClimateIllnessPertussis.setVisibility(View.GONE);
                    lineClimateIllnessPertussis.setVisibility(View.GONE);
                    chkClimateIllnessPertussis.setChecked(false);
                    secClimateIllnessDiarrhea.setVisibility(View.GONE);
                    lineClimateIllnessDiarrhea.setVisibility(View.GONE);
                    chkClimateIllnessDiarrhea.setChecked(false);
                    secClimateIllnessFood.setVisibility(View.GONE);
                    lineClimateIllnessFood.setVisibility(View.GONE);
                    chkClimateIllnessFood.setChecked(false);
                    secClimateIllnessTyphoid.setVisibility(View.GONE);
                    lineClimateIllnessTyphoid.setVisibility(View.GONE);
                    chkClimateIllnessTyphoid.setChecked(false);
                    secClimateIllnessCough.setVisibility(View.GONE);
                    lineClimateIllnessCough.setVisibility(View.GONE);
                    chkClimateIllnessCough.setChecked(false);
                    secClimateIllnessCholera.setVisibility(View.GONE);
                    lineClimateIllnessCholera.setVisibility(View.GONE);
                    chkClimateIllnessCholera.setChecked(false);
                    secClimateIllnessOther.setVisibility(View.GONE);
                    lineClimateIllnessOther.setVisibility(View.GONE);
                    chkClimateIllnessOther.setChecked(false);
                    secClimateIllnessOtherSp.setVisibility(View.GONE);
                    lineClimateIllnessOtherSp.setVisibility(View.GONE);
                    txtClimateIllnessOtherSp.setText("");
                    secClimateIllnessPeriod.setVisibility(View.GONE);
                    lineClimateIllnessPeriod.setVisibility(View.GONE);
                    spnClimateIllnessPeriod.setSelection(0);
                    seclblP2.setVisibility(View.GONE);
                    linelblP2.setVisibility(View.GONE);
                    secClimateHeard.setVisibility(View.GONE);
                    lineClimateHeard.setVisibility(View.GONE);
                    rdogrpClimateHeard.clearCheck();
                    seclblQ9.setVisibility(View.GONE);
                    linelblQ9.setVisibility(View.GONE);
                    secClimateInfoMedia.setVisibility(View.GONE);
                    lineClimateInfoMedia.setVisibility(View.GONE);
                    chkClimateInfoMedia.setChecked(false);
                    secClimateInfoInternet.setVisibility(View.GONE);
                    lineClimateInfoInternet.setVisibility(View.GONE);
                    chkClimateInfoInternet.setChecked(false);
                    secClimateInfoEducation.setVisibility(View.GONE);
                    lineClimateInfoEducation.setVisibility(View.GONE);
                    chkClimateInfoEducation.setChecked(false);
                    secClimateInfoFnF.setVisibility(View.GONE);
                    lineClimateInfoFnF.setVisibility(View.GONE);
                    chkClimateInfoFnF.setChecked(false);
                    secClimateInfoOther.setVisibility(View.GONE);
                    lineClimateInfoOther.setVisibility(View.GONE);
                    chkClimateInfoOther.setChecked(false);
                    secClimateInfoOtherSp.setVisibility(View.GONE);
                    lineClimateInfoOtherSp.setVisibility(View.GONE);
                    txtClimateInfoOtherSp.setText("");
                    secClimateKnRate.setVisibility(View.GONE);
                    lineClimateKnRate.setVisibility(View.GONE);
                    spnClimateKnRate.setSelection(0);
                    seclblP3.setVisibility(View.GONE);
                    linelblP3.setVisibility(View.GONE);
                    secClimatethreat.setVisibility(View.GONE);
                    lineClimatethreat.setVisibility(View.GONE);
                    rdogrpClimatethreat.clearCheck();
                    secClimateWorried.setVisibility(View.GONE);
                    lineClimateWorried.setVisibility(View.GONE);
                    spnClimateWorried.setSelection(0);
                    seclblQ13.setVisibility(View.GONE);
                    linelblQ13.setVisibility(View.GONE);
                    secClimateConcernTemp.setVisibility(View.GONE);
                    lineClimateConcernTemp.setVisibility(View.GONE);
                    chkClimateConcernTemp.setChecked(false);
                    secClimateConcernEvent.setVisibility(View.GONE);
                    lineClimateConcernEvent.setVisibility(View.GONE);
                    chkClimateConcernEvent.setChecked(false);
                    secClimateConcernPolarIce.setVisibility(View.GONE);
                    lineClimateConcernPolarIce.setVisibility(View.GONE);
                    chkClimateConcernPolarIce.setChecked(false);
                    secClimateConcernSeaLevel.setVisibility(View.GONE);
                    lineClimateConcernSeaLevel.setVisibility(View.GONE);
                    chkClimateConcernSeaLevel.setChecked(false);
                    secClimateConcernBiodiversity.setVisibility(View.GONE);
                    lineClimateConcernBiodiversity.setVisibility(View.GONE);
                    chkClimateConcernBiodiversity.setChecked(false);
                    secClimateConcernDrying.setVisibility(View.GONE);
                    lineClimateConcernDrying.setVisibility(View.GONE);
                    chkClimateConcernDrying.setChecked(false);
                    secClimateConcernRising.setVisibility(View.GONE);
                    lineClimateConcernRising.setVisibility(View.GONE);
                    chkClimateConcernRising.setChecked(false);
                    secClimateConcernDrought.setVisibility(View.GONE);
                    lineClimateConcernDrought.setVisibility(View.GONE);
                    chkClimateConcernDrought.setChecked(false);
                    secClimateConcernDesert.setVisibility(View.GONE);
                    lineClimateConcernDesert.setVisibility(View.GONE);
                    chkClimateConcernDesert.setChecked(false);
                    secClimateConcernErosin.setVisibility(View.GONE);
                    lineClimateConcernErosin.setVisibility(View.GONE);
                    chkClimateConcernErosin.setChecked(false);
                    secClimateConcernOther.setVisibility(View.GONE);
                    lineClimateConcernOther.setVisibility(View.GONE);
                    chkClimateConcernOther.setChecked(false);
                    secClimateConcernOtherSp.setVisibility(View.GONE);
                    lineClimateConcernOtherSp.setVisibility(View.GONE);
                    txtClimateConcernOtherSp.setText("");
                    secClimateComDoing.setVisibility(View.GONE);
                    lineClimateComDoing.setVisibility(View.GONE);
                    rdogrpClimateComDoing.clearCheck();
                    seclblP4.setVisibility(View.GONE);
                    linelblP4.setVisibility(View.GONE);
                    secClimateReduceImpact.setVisibility(View.GONE);
                    lineClimateReduceImpact.setVisibility(View.GONE);
                    rdogrpClimateReduceImpact.clearCheck();
                    seclblQ16.setVisibility(View.GONE);
                    linelblQ16.setVisibility(View.GONE);
                    secClimateActionsPlastics.setVisibility(View.GONE);
                    lineClimateActionsPlastics.setVisibility(View.GONE);
                    chkClimateActionsPlastics.setChecked(false);
                    secClimateActionsTransport.setVisibility(View.GONE);
                    lineClimateActionsTransport.setVisibility(View.GONE);
                    chkClimateActionsTransport.setChecked(false);
                    secClimateActionsFoods.setVisibility(View.GONE);
                    lineClimateActionsFoods.setVisibility(View.GONE);
                    chkClimateActionsFoods.setChecked(false);
                    secClimateActionsHome.setVisibility(View.GONE);
                    lineClimateActionsHome.setVisibility(View.GONE);
                    chkClimateActionsHome.setChecked(false);
                    secClimateActionsOther.setVisibility(View.GONE);
                    lineClimateActionsOther.setVisibility(View.GONE);
                    chkClimateActionsOther.setChecked(false);
                    secClimateActionsOtherSp.setVisibility(View.GONE);
                    lineClimateActionsOtherSp.setVisibility(View.GONE);
                    txtClimateActionsOtherSp.setText("");
                    secClimateRenewableEnergy.setVisibility(View.GONE);
                    lineClimateRenewableEnergy.setVisibility(View.GONE);
                    rdogrpClimateRenewableEnergy.clearCheck();
                    seclblQ18.setVisibility(View.GONE);
                    linelblQ18.setVisibility(View.GONE);
                    secClimateObstaclesCost.setVisibility(View.GONE);
                    lineClimateObstaclesCost.setVisibility(View.GONE);
                    chkClimateObstaclesCost.setChecked(false);
                secClimateObstaclesLackInfo.setVisibility(View.GONE);
                lineClimateObstaclesLackInfo.setVisibility(View.GONE);
                chkClimateObstaclesLackInfo.setChecked(false);
                    secClimateObstaclesAccess.setVisibility(View.GONE);
                    lineClimateObstaclesAccess.setVisibility(View.GONE);
                    chkClimateObstaclesAccess.setChecked(false);
                    secClimateObstaclesHabit.setVisibility(View.GONE);
                    lineClimateObstaclesHabit.setVisibility(View.GONE);
                    chkClimateObstaclesHabit.setChecked(false);
                    secClimateObstaclesOther.setVisibility(View.GONE);
                    lineClimateObstaclesOther.setVisibility(View.GONE);
                    chkClimateObstaclesOther.setChecked(false);
                    secClimateObstaclesOtherSp.setVisibility(View.GONE);
                    lineClimateObstaclesOtherSp.setVisibility(View.GONE);
                    txtClimateObstaclesOtherSp.setText("");
             }
             else
             {
                    seclblP1.setVisibility(View.VISIBLE);
                    linelblP1.setVisibility(View.VISIBLE);
                    secClimateChangesNoticed.setVisibility(View.VISIBLE);
                    lineClimateChangesNoticed.setVisibility(View.VISIBLE);
                    seclblQ2.setVisibility(View.VISIBLE);
                    linelblQ2.setVisibility(View.VISIBLE);
                    secClimateChangesTemp.setVisibility(View.VISIBLE);
                    lineClimateChangesTemp.setVisibility(View.VISIBLE);
                    secClimateChangesSeason.setVisibility(View.VISIBLE);
                    lineClimateChangesSeason.setVisibility(View.VISIBLE);
                    secClimateChangesWeather.setVisibility(View.VISIBLE);
                    lineClimateChangesWeather.setVisibility(View.VISIBLE);
                    secClimateChangesFlora.setVisibility(View.VISIBLE);
                    lineClimateChangesFlora.setVisibility(View.VISIBLE);
                    secClimateChangesOther.setVisibility(View.VISIBLE);
                    lineClimateChangesOther.setVisibility(View.VISIBLE);
                    secClimateAffectDailyLife.setVisibility(View.VISIBLE);
                    lineClimateAffectDailyLife.setVisibility(View.VISIBLE);
                    secClimateFlood.setVisibility(View.VISIBLE);
                    lineClimateFlood.setVisibility(View.VISIBLE);
                    seclblQ5.setVisibility(View.VISIBLE);
                    linelblQ5.setVisibility(View.VISIBLE);
                    secClimateFloodHouse.setVisibility(View.VISIBLE);
                    lineClimateFloodHouse.setVisibility(View.VISIBLE);
                    secClimateFloodWall.setVisibility(View.VISIBLE);
                    lineClimateFloodWall.setVisibility(View.VISIBLE);
                    secClimateFloodMember.setVisibility(View.VISIBLE);
                    lineClimateFloodMember.setVisibility(View.VISIBLE);
                    secClimateFloodLivestock.setVisibility(View.VISIBLE);
                    lineClimateFloodLivestock.setVisibility(View.VISIBLE);
                    secClimateFloodJobLess.setVisibility(View.VISIBLE);
                    lineClimateFloodJobLess.setVisibility(View.VISIBLE);
                    secClimateFloodEnergy.setVisibility(View.VISIBLE);
                    lineClimateFloodEnergy.setVisibility(View.VISIBLE);
                    secClimateFloodToilets.setVisibility(View.VISIBLE);
                    lineClimateFloodToilets.setVisibility(View.VISIBLE);
                    secClimateFloodHealth.setVisibility(View.VISIBLE);
                    lineClimateFloodHealth.setVisibility(View.VISIBLE);
                    secClimateFloodMigration.setVisibility(View.VISIBLE);
                    lineClimateFloodMigration.setVisibility(View.VISIBLE);
                    secClimateFloodOther.setVisibility(View.VISIBLE);
                    lineClimateFloodOther.setVisibility(View.VISIBLE);
                    secClimateHighHeat.setVisibility(View.VISIBLE);
                    lineClimateHighHeat.setVisibility(View.VISIBLE);
                    seclblQ7.setVisibility(View.VISIBLE);
                    linelblQ7.setVisibility(View.VISIBLE);
                    secClimateHighHeatMemberDth.setVisibility(View.VISIBLE);
                    lineClimateHighHeatMemberDth.setVisibility(View.VISIBLE);
                    secClimateHighHeatHealth.setVisibility(View.VISIBLE);
                    lineClimateHighHeatHealth.setVisibility(View.VISIBLE);
                    secClimateHighHeatMemberIll.setVisibility(View.VISIBLE);
                    lineClimateHighHeatMemberIll.setVisibility(View.VISIBLE);
                    secClimateHighHeatPetDth.setVisibility(View.VISIBLE);
                    lineClimateHighHeatPetDth.setVisibility(View.VISIBLE);
                    secClimateHighHeatOther.setVisibility(View.VISIBLE);
                    lineClimateHighHeatOther.setVisibility(View.VISIBLE);
//                    seclblQ7a.setVisibility(View.VISIBLE);
//                    linelblQ7a.setVisibility(View.VISIBLE);
//                    secClimateHealthProblemSleep.setVisibility(View.VISIBLE);
//                    lineClimateHealthProblemSleep.setVisibility(View.VISIBLE);
//                    secClimateHealthProblemDizzy.setVisibility(View.VISIBLE);
//                    lineClimateHealthProblemDizzy.setVisibility(View.VISIBLE);
//                    secClimateHealthProblemLowBlood.setVisibility(View.VISIBLE);
//                    lineClimateHealthProblemLowBlood.setVisibility(View.VISIBLE);
//                    secClimateHealthProblemHighBlood.setVisibility(View.VISIBLE);
//                    lineClimateHealthProblemHighBlood.setVisibility(View.VISIBLE);
//                    secClimateHealthProblemStroke.setVisibility(View.VISIBLE);
//                    lineClimateHealthProblemStroke.setVisibility(View.VISIBLE);
//                    secClimateHealthProblemFever.setVisibility(View.VISIBLE);
//                    lineClimateHealthProblemFever.setVisibility(View.VISIBLE);
//                    secClimateHealthProblemOther.setVisibility(View.VISIBLE);
//                    lineClimateHealthProblemOther.setVisibility(View.VISIBLE);
//                    secClimateHealthProblemPeriod.setVisibility(View.VISIBLE);
//                    lineClimateHealthProblemPeriod.setVisibility(View.VISIBLE);
//                    seclblQ7b.setVisibility(View.VISIBLE);
//                    linelblQ7b.setVisibility(View.VISIBLE);
//                    secClimateIllnessMalaria.setVisibility(View.VISIBLE);
//                    lineClimateIllnessMalaria.setVisibility(View.VISIBLE);
//                    secClimateIllnessPneumonia.setVisibility(View.VISIBLE);
//                    lineClimateIllnessPneumonia.setVisibility(View.VISIBLE);
//                    secClimateIllnessMeasles.setVisibility(View.VISIBLE);
//                    lineClimateIllnessMeasles.setVisibility(View.VISIBLE);
//                    secClimateIllnessPertussis.setVisibility(View.VISIBLE);
//                    lineClimateIllnessPertussis.setVisibility(View.VISIBLE);
//                    secClimateIllnessDiarrhea.setVisibility(View.VISIBLE);
//                    lineClimateIllnessDiarrhea.setVisibility(View.VISIBLE);
//                    secClimateIllnessFood.setVisibility(View.VISIBLE);
//                    lineClimateIllnessFood.setVisibility(View.VISIBLE);
//                    secClimateIllnessTyphoid.setVisibility(View.VISIBLE);
//                    lineClimateIllnessTyphoid.setVisibility(View.VISIBLE);
//                    secClimateIllnessCough.setVisibility(View.VISIBLE);
//                    lineClimateIllnessCough.setVisibility(View.VISIBLE);
//                    secClimateIllnessCholera.setVisibility(View.VISIBLE);
//                    lineClimateIllnessCholera.setVisibility(View.VISIBLE);
//                    secClimateIllnessOther.setVisibility(View.VISIBLE);
//                    lineClimateIllnessOther.setVisibility(View.VISIBLE);
//                    secClimateIllnessPeriod.setVisibility(View.VISIBLE);
//                    lineClimateIllnessPeriod.setVisibility(View.VISIBLE);
                    seclblP2.setVisibility(View.VISIBLE);
                    linelblP2.setVisibility(View.VISIBLE);
                    secClimateHeard.setVisibility(View.VISIBLE);
                    lineClimateHeard.setVisibility(View.VISIBLE);
                    seclblQ9.setVisibility(View.VISIBLE);
                    linelblQ9.setVisibility(View.VISIBLE);
                    secClimateInfoMedia.setVisibility(View.VISIBLE);
                    lineClimateInfoMedia.setVisibility(View.VISIBLE);
                    secClimateInfoInternet.setVisibility(View.VISIBLE);
                    lineClimateInfoInternet.setVisibility(View.VISIBLE);
                    secClimateInfoEducation.setVisibility(View.VISIBLE);
                    lineClimateInfoEducation.setVisibility(View.VISIBLE);
                    secClimateInfoFnF.setVisibility(View.VISIBLE);
                    lineClimateInfoFnF.setVisibility(View.VISIBLE);
                    secClimateInfoOther.setVisibility(View.VISIBLE);
                    lineClimateInfoOther.setVisibility(View.VISIBLE);
                    secClimateKnRate.setVisibility(View.VISIBLE);
                    lineClimateKnRate.setVisibility(View.VISIBLE);
                    seclblP3.setVisibility(View.VISIBLE);
                    linelblP3.setVisibility(View.VISIBLE);
                    secClimatethreat.setVisibility(View.VISIBLE);
                    lineClimatethreat.setVisibility(View.VISIBLE);
                    secClimateWorried.setVisibility(View.VISIBLE);
                    lineClimateWorried.setVisibility(View.VISIBLE);
                    seclblQ13.setVisibility(View.VISIBLE);
                    linelblQ13.setVisibility(View.VISIBLE);
                    secClimateConcernTemp.setVisibility(View.VISIBLE);
                    lineClimateConcernTemp.setVisibility(View.VISIBLE);
                    secClimateConcernEvent.setVisibility(View.VISIBLE);
                    lineClimateConcernEvent.setVisibility(View.VISIBLE);
                    secClimateConcernPolarIce.setVisibility(View.VISIBLE);
                    lineClimateConcernPolarIce.setVisibility(View.VISIBLE);
                    secClimateConcernSeaLevel.setVisibility(View.VISIBLE);
                    lineClimateConcernSeaLevel.setVisibility(View.VISIBLE);
                    secClimateConcernBiodiversity.setVisibility(View.VISIBLE);
                    lineClimateConcernBiodiversity.setVisibility(View.VISIBLE);
                    secClimateConcernDrying.setVisibility(View.VISIBLE);
                    lineClimateConcernDrying.setVisibility(View.VISIBLE);
                    secClimateConcernRising.setVisibility(View.VISIBLE);
                    lineClimateConcernRising.setVisibility(View.VISIBLE);
                    secClimateConcernDrought.setVisibility(View.VISIBLE);
                    lineClimateConcernDrought.setVisibility(View.VISIBLE);
                    secClimateConcernDesert.setVisibility(View.VISIBLE);
                    lineClimateConcernDesert.setVisibility(View.VISIBLE);
                    secClimateConcernErosin.setVisibility(View.VISIBLE);
                    lineClimateConcernErosin.setVisibility(View.VISIBLE);
                    secClimateConcernOther.setVisibility(View.VISIBLE);
                    lineClimateConcernOther.setVisibility(View.VISIBLE);
                    secClimateComDoing.setVisibility(View.VISIBLE);
                    lineClimateComDoing.setVisibility(View.VISIBLE);
                    seclblP4.setVisibility(View.VISIBLE);
                    linelblP4.setVisibility(View.VISIBLE);
                    secClimateReduceImpact.setVisibility(View.VISIBLE);
                    lineClimateReduceImpact.setVisibility(View.VISIBLE);
                    seclblQ16.setVisibility(View.VISIBLE);
                    linelblQ16.setVisibility(View.VISIBLE);
                    secClimateActionsPlastics.setVisibility(View.VISIBLE);
                    lineClimateActionsPlastics.setVisibility(View.VISIBLE);
                    secClimateActionsTransport.setVisibility(View.VISIBLE);
                    lineClimateActionsTransport.setVisibility(View.VISIBLE);
                    secClimateActionsFoods.setVisibility(View.VISIBLE);
                    lineClimateActionsFoods.setVisibility(View.VISIBLE);
                    secClimateActionsHome.setVisibility(View.VISIBLE);
                    lineClimateActionsHome.setVisibility(View.VISIBLE);
                    secClimateActionsOther.setVisibility(View.VISIBLE);
                    lineClimateActionsOther.setVisibility(View.VISIBLE);
                    secClimateRenewableEnergy.setVisibility(View.VISIBLE);
                    lineClimateRenewableEnergy.setVisibility(View.VISIBLE);
                    seclblQ18.setVisibility(View.VISIBLE);
                    linelblQ18.setVisibility(View.VISIBLE);
                    secClimateObstaclesCost.setVisibility(View.VISIBLE);
                    lineClimateObstaclesCost.setVisibility(View.VISIBLE);
                secClimateObstaclesLackInfo.setVisibility(View.VISIBLE);
                lineClimateObstaclesLackInfo.setVisibility(View.VISIBLE);
                    secClimateObstaclesAccess.setVisibility(View.VISIBLE);
                    lineClimateObstaclesAccess.setVisibility(View.VISIBLE);
                    secClimateObstaclesHabit.setVisibility(View.VISIBLE);
                    lineClimateObstaclesHabit.setVisibility(View.VISIBLE);
                    secClimateObstaclesOther.setVisibility(View.VISIBLE);
                    lineClimateObstaclesOther.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secClimateVStatusOth=(LinearLayout)findViewById(R.id.secClimateVStatusOth);
         lineClimateVStatusOth=(View)findViewById(R.id.lineClimateVStatusOth);
         VlblClimateVStatusOth=(TextView) findViewById(R.id.VlblClimateVStatusOth);
         txtClimateVStatusOth=(EditText) findViewById(R.id.txtClimateVStatusOth);
         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         txtRnd.setText(ROUND);
         txtRnd.setEnabled(false);
         seclblP1=(LinearLayout)findViewById(R.id.seclblP1);
         linelblP1=(View)findViewById(R.id.linelblP1);
         secClimateChangesNoticed=(LinearLayout)findViewById(R.id.secClimateChangesNoticed);
         lineClimateChangesNoticed=(View)findViewById(R.id.lineClimateChangesNoticed);
         VlblClimateChangesNoticed = (TextView) findViewById(R.id.VlblClimateChangesNoticed);
         rdogrpClimateChangesNoticed = (RadioGroup) findViewById(R.id.rdogrpClimateChangesNoticed);
         rdoClimateChangesNoticed1 = (RadioButton) findViewById(R.id.rdoClimateChangesNoticed1);
         rdoClimateChangesNoticed2 = (RadioButton) findViewById(R.id.rdoClimateChangesNoticed2);
         rdogrpClimateChangesNoticed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpClimateChangesNoticed = new String[] {"0","1"};
             for (int i = 0; i < rdogrpClimateChangesNoticed.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpClimateChangesNoticed.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpClimateChangesNoticed[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                seclblQ2.setVisibility(View.VISIBLE);
                linelblQ2.setVisibility(View.VISIBLE);
                secClimateChangesTemp.setVisibility(View.VISIBLE);
                lineClimateChangesTemp.setVisibility(View.VISIBLE);
                secClimateChangesSeason.setVisibility(View.VISIBLE);
                lineClimateChangesSeason.setVisibility(View.VISIBLE);
                secClimateChangesWeather.setVisibility(View.VISIBLE);
                lineClimateChangesWeather.setVisibility(View.VISIBLE);
                secClimateChangesFlora.setVisibility(View.VISIBLE);
                lineClimateChangesFlora.setVisibility(View.VISIBLE);
                secClimateChangesOther.setVisibility(View.VISIBLE);
                lineClimateChangesOther.setVisibility(View.VISIBLE);
                secClimateAffectDailyLife.setVisibility(View.VISIBLE);
                lineClimateAffectDailyLife.setVisibility(View.VISIBLE);
             }
             else
             {
                seclblQ2.setVisibility(View.GONE);
                linelblQ2.setVisibility(View.GONE);
                secClimateChangesTemp.setVisibility(View.GONE);
                lineClimateChangesTemp.setVisibility(View.GONE);
                chkClimateChangesTemp.setChecked(false);
                secClimateChangesSeason.setVisibility(View.GONE);
                lineClimateChangesSeason.setVisibility(View.GONE);
                chkClimateChangesSeason.setChecked(false);
                secClimateChangesWeather.setVisibility(View.GONE);
                lineClimateChangesWeather.setVisibility(View.GONE);
                chkClimateChangesWeather.setChecked(false);
                secClimateChangesFlora.setVisibility(View.GONE);
                lineClimateChangesFlora.setVisibility(View.GONE);
                chkClimateChangesFlora.setChecked(false);
                secClimateChangesOther.setVisibility(View.GONE);
                lineClimateChangesOther.setVisibility(View.GONE);
                chkClimateChangesOther.setChecked(false);
                secClimateChangesOtherSp.setVisibility(View.GONE);
                lineClimateChangesOtherSp.setVisibility(View.GONE);
                txtClimateChangesOtherSp.setText("");
                secClimateAffectDailyLife.setVisibility(View.GONE);
                lineClimateAffectDailyLife.setVisibility(View.GONE);
                spnClimateAffectDailyLife.setSelection(0);

             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclblQ2=(LinearLayout)findViewById(R.id.seclblQ2);
         linelblQ2=(View)findViewById(R.id.linelblQ2);
         secClimateChangesTemp=(LinearLayout)findViewById(R.id.secClimateChangesTemp);
         lineClimateChangesTemp=(View)findViewById(R.id.lineClimateChangesTemp);
         VlblClimateChangesTemp=(TextView) findViewById(R.id.VlblClimateChangesTemp);
         chkClimateChangesTemp=(CheckBox) findViewById(R.id.chkClimateChangesTemp);
         secClimateChangesSeason=(LinearLayout)findViewById(R.id.secClimateChangesSeason);
         lineClimateChangesSeason=(View)findViewById(R.id.lineClimateChangesSeason);
         VlblClimateChangesSeason=(TextView) findViewById(R.id.VlblClimateChangesSeason);
         chkClimateChangesSeason=(CheckBox) findViewById(R.id.chkClimateChangesSeason);
         secClimateChangesWeather=(LinearLayout)findViewById(R.id.secClimateChangesWeather);
         lineClimateChangesWeather=(View)findViewById(R.id.lineClimateChangesWeather);
         VlblClimateChangesWeather=(TextView) findViewById(R.id.VlblClimateChangesWeather);
         chkClimateChangesWeather=(CheckBox) findViewById(R.id.chkClimateChangesWeather);
         secClimateChangesFlora=(LinearLayout)findViewById(R.id.secClimateChangesFlora);
         lineClimateChangesFlora=(View)findViewById(R.id.lineClimateChangesFlora);
         VlblClimateChangesFlora=(TextView) findViewById(R.id.VlblClimateChangesFlora);
         chkClimateChangesFlora=(CheckBox) findViewById(R.id.chkClimateChangesFlora);
         secClimateChangesOther=(LinearLayout)findViewById(R.id.secClimateChangesOther);
         lineClimateChangesOther=(View)findViewById(R.id.lineClimateChangesOther);
         VlblClimateChangesOther=(TextView) findViewById(R.id.VlblClimateChangesOther);
         chkClimateChangesOther=(CheckBox) findViewById(R.id.chkClimateChangesOther);
         chkClimateChangesOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secClimateChangesOtherSp.setVisibility(View.VISIBLE);
                    lineClimateChangesOtherSp.setVisibility(View.VISIBLE);
                 }
                 else {
                    secClimateChangesOtherSp.setVisibility(View.GONE);
                    lineClimateChangesOtherSp.setVisibility(View.GONE);
                    txtClimateChangesOtherSp.setText("");
                 }
                 }
         });
         secClimateChangesOtherSp=(LinearLayout)findViewById(R.id.secClimateChangesOtherSp);
         lineClimateChangesOtherSp=(View)findViewById(R.id.lineClimateChangesOtherSp);
         VlblClimateChangesOtherSp=(TextView) findViewById(R.id.VlblClimateChangesOtherSp);
         txtClimateChangesOtherSp=(EditText) findViewById(R.id.txtClimateChangesOtherSp);
         secClimateAffectDailyLife=(LinearLayout)findViewById(R.id.secClimateAffectDailyLife);
         lineClimateAffectDailyLife=(View)findViewById(R.id.lineClimateAffectDailyLife);
         VlblClimateAffectDailyLife=(TextView) findViewById(R.id.VlblClimateAffectDailyLife);
         spnClimateAffectDailyLife=(Spinner) findViewById(R.id.spnClimateAffectDailyLife);
         List<String> listClimateAffectDailyLife = new ArrayList<String>();
         
         listClimateAffectDailyLife.add("");
         listClimateAffectDailyLife.add("1- Not affected at all");
         listClimateAffectDailyLife.add("2- Slightly affected");
         listClimateAffectDailyLife.add("3- Moderately affected");
         listClimateAffectDailyLife.add("4- Heavily affected");
         listClimateAffectDailyLife.add("5- Extremely affected");
         spnClimateAffectDailyLife.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listClimateAffectDailyLife)));

         secClimateFlood=(LinearLayout)findViewById(R.id.secClimateFlood);
         lineClimateFlood=(View)findViewById(R.id.lineClimateFlood);
         VlblClimateFlood = (TextView) findViewById(R.id.VlblClimateFlood);
         rdogrpClimateFlood = (RadioGroup) findViewById(R.id.rdogrpClimateFlood);
         rdoClimateFlood1 = (RadioButton) findViewById(R.id.rdoClimateFlood1);
         rdoClimateFlood2 = (RadioButton) findViewById(R.id.rdoClimateFlood2);
         rdogrpClimateFlood.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpClimateFlood = new String[] {"0","1"};
             for (int i = 0; i < rdogrpClimateFlood.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpClimateFlood.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpClimateFlood[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                seclblQ5.setVisibility(View.VISIBLE);
                linelblQ5.setVisibility(View.VISIBLE);
                secClimateFloodHouse.setVisibility(View.VISIBLE);
                lineClimateFloodHouse.setVisibility(View.VISIBLE);
                secClimateFloodWall.setVisibility(View.VISIBLE);
                lineClimateFloodWall.setVisibility(View.VISIBLE);
                secClimateFloodMember.setVisibility(View.VISIBLE);
                lineClimateFloodMember.setVisibility(View.VISIBLE);
                secClimateFloodLivestock.setVisibility(View.VISIBLE);
                lineClimateFloodLivestock.setVisibility(View.VISIBLE);
                secClimateFloodJobLess.setVisibility(View.VISIBLE);
                lineClimateFloodJobLess.setVisibility(View.VISIBLE);
                secClimateFloodEnergy.setVisibility(View.VISIBLE);
                lineClimateFloodEnergy.setVisibility(View.VISIBLE);
                secClimateFloodToilets.setVisibility(View.VISIBLE);
                lineClimateFloodToilets.setVisibility(View.VISIBLE);
                secClimateFloodHealth.setVisibility(View.VISIBLE);
                lineClimateFloodHealth.setVisibility(View.VISIBLE);
                secClimateFloodMigration.setVisibility(View.VISIBLE);
                lineClimateFloodMigration.setVisibility(View.VISIBLE);
                secClimateFloodOther.setVisibility(View.VISIBLE);
                lineClimateFloodOther.setVisibility(View.VISIBLE);
             }
             else
             {
                seclblQ5.setVisibility(View.GONE);
                linelblQ5.setVisibility(View.GONE);
                secClimateFloodHouse.setVisibility(View.GONE);
                lineClimateFloodHouse.setVisibility(View.GONE);
                chkClimateFloodHouse.setChecked(false);
                secClimateFloodWall.setVisibility(View.GONE);
                lineClimateFloodWall.setVisibility(View.GONE);
                chkClimateFloodWall.setChecked(false);
                secClimateFloodMember.setVisibility(View.GONE);
                lineClimateFloodMember.setVisibility(View.GONE);
                chkClimateFloodMember.setChecked(false);
                secClimateFloodLivestock.setVisibility(View.GONE);
                lineClimateFloodLivestock.setVisibility(View.GONE);
                chkClimateFloodLivestock.setChecked(false);
                secClimateFloodJobLess.setVisibility(View.GONE);
                lineClimateFloodJobLess.setVisibility(View.GONE);
                chkClimateFloodJobLess.setChecked(false);
                secClimateFloodEnergy.setVisibility(View.GONE);
                lineClimateFloodEnergy.setVisibility(View.GONE);
                chkClimateFloodEnergy.setChecked(false);
                secClimateFloodToilets.setVisibility(View.GONE);
                lineClimateFloodToilets.setVisibility(View.GONE);
                chkClimateFloodToilets.setChecked(false);
                secClimateFloodHealth.setVisibility(View.GONE);
                lineClimateFloodHealth.setVisibility(View.GONE);
                chkClimateFloodHealth.setChecked(false);
                secClimateFloodMigration.setVisibility(View.GONE);
                lineClimateFloodMigration.setVisibility(View.GONE);
                chkClimateFloodMigration.setChecked(false);
                secClimateFloodOther.setVisibility(View.GONE);
                lineClimateFloodOther.setVisibility(View.GONE);
                chkClimateFloodOther.setChecked(false);
                secClimateFloodOtherSp.setVisibility(View.GONE);
                lineClimateFloodOtherSp.setVisibility(View.GONE);
                txtClimateFloodOtherSp.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclblQ5=(LinearLayout)findViewById(R.id.seclblQ5);
         linelblQ5=(View)findViewById(R.id.linelblQ5);
         secClimateFloodHouse=(LinearLayout)findViewById(R.id.secClimateFloodHouse);
         lineClimateFloodHouse=(View)findViewById(R.id.lineClimateFloodHouse);
         VlblClimateFloodHouse=(TextView) findViewById(R.id.VlblClimateFloodHouse);
         chkClimateFloodHouse=(CheckBox) findViewById(R.id.chkClimateFloodHouse);
         secClimateFloodWall=(LinearLayout)findViewById(R.id.secClimateFloodWall);
         lineClimateFloodWall=(View)findViewById(R.id.lineClimateFloodWall);
         VlblClimateFloodWall=(TextView) findViewById(R.id.VlblClimateFloodWall);
         chkClimateFloodWall=(CheckBox) findViewById(R.id.chkClimateFloodWall);
         secClimateFloodMember=(LinearLayout)findViewById(R.id.secClimateFloodMember);
         lineClimateFloodMember=(View)findViewById(R.id.lineClimateFloodMember);
         VlblClimateFloodMember=(TextView) findViewById(R.id.VlblClimateFloodMember);
         chkClimateFloodMember=(CheckBox) findViewById(R.id.chkClimateFloodMember);
         secClimateFloodLivestock=(LinearLayout)findViewById(R.id.secClimateFloodLivestock);
         lineClimateFloodLivestock=(View)findViewById(R.id.lineClimateFloodLivestock);
         VlblClimateFloodLivestock=(TextView) findViewById(R.id.VlblClimateFloodLivestock);
         chkClimateFloodLivestock=(CheckBox) findViewById(R.id.chkClimateFloodLivestock);
         secClimateFloodJobLess=(LinearLayout)findViewById(R.id.secClimateFloodJobLess);
         lineClimateFloodJobLess=(View)findViewById(R.id.lineClimateFloodJobLess);
         VlblClimateFloodJobLess=(TextView) findViewById(R.id.VlblClimateFloodJobLess);
         chkClimateFloodJobLess=(CheckBox) findViewById(R.id.chkClimateFloodJobLess);
         secClimateFloodEnergy=(LinearLayout)findViewById(R.id.secClimateFloodEnergy);
         lineClimateFloodEnergy=(View)findViewById(R.id.lineClimateFloodEnergy);
         VlblClimateFloodEnergy=(TextView) findViewById(R.id.VlblClimateFloodEnergy);
         chkClimateFloodEnergy=(CheckBox) findViewById(R.id.chkClimateFloodEnergy);
         secClimateFloodToilets=(LinearLayout)findViewById(R.id.secClimateFloodToilets);
         lineClimateFloodToilets=(View)findViewById(R.id.lineClimateFloodToilets);
         VlblClimateFloodToilets=(TextView) findViewById(R.id.VlblClimateFloodToilets);
         chkClimateFloodToilets=(CheckBox) findViewById(R.id.chkClimateFloodToilets);
         secClimateFloodHealth=(LinearLayout)findViewById(R.id.secClimateFloodHealth);
         lineClimateFloodHealth=(View)findViewById(R.id.lineClimateFloodHealth);
         VlblClimateFloodHealth=(TextView) findViewById(R.id.VlblClimateFloodHealth);
         chkClimateFloodHealth=(CheckBox) findViewById(R.id.chkClimateFloodHealth);
         secClimateFloodMigration=(LinearLayout)findViewById(R.id.secClimateFloodMigration);
         lineClimateFloodMigration=(View)findViewById(R.id.lineClimateFloodMigration);
         VlblClimateFloodMigration=(TextView) findViewById(R.id.VlblClimateFloodMigration);
         chkClimateFloodMigration=(CheckBox) findViewById(R.id.chkClimateFloodMigration);
         secClimateFloodOther=(LinearLayout)findViewById(R.id.secClimateFloodOther);
         lineClimateFloodOther=(View)findViewById(R.id.lineClimateFloodOther);
         VlblClimateFloodOther=(TextView) findViewById(R.id.VlblClimateFloodOther);
         chkClimateFloodOther=(CheckBox) findViewById(R.id.chkClimateFloodOther);
         chkClimateFloodOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secClimateFloodOtherSp.setVisibility(View.VISIBLE);
                    lineClimateFloodOtherSp.setVisibility(View.VISIBLE);
                 }
                 else {
                    secClimateFloodOtherSp.setVisibility(View.GONE);
                    lineClimateFloodOtherSp.setVisibility(View.GONE);
                    txtClimateFloodOtherSp.setText("");
                 }
                 }
         });
         secClimateFloodOtherSp=(LinearLayout)findViewById(R.id.secClimateFloodOtherSp);
         lineClimateFloodOtherSp=(View)findViewById(R.id.lineClimateFloodOtherSp);
         VlblClimateFloodOtherSp=(TextView) findViewById(R.id.VlblClimateFloodOtherSp);
         txtClimateFloodOtherSp=(EditText) findViewById(R.id.txtClimateFloodOtherSp);
         secClimateHighHeat=(LinearLayout)findViewById(R.id.secClimateHighHeat);
         lineClimateHighHeat=(View)findViewById(R.id.lineClimateHighHeat);
         VlblClimateHighHeat = (TextView) findViewById(R.id.VlblClimateHighHeat);
         rdogrpClimateHighHeat = (RadioGroup) findViewById(R.id.rdogrpClimateHighHeat);
         rdoClimateHighHeat1 = (RadioButton) findViewById(R.id.rdoClimateHighHeat1);
         rdoClimateHighHeat2 = (RadioButton) findViewById(R.id.rdoClimateHighHeat2);
         rdogrpClimateHighHeat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpClimateHighHeat = new String[] {"0","1"};
             for (int i = 0; i < rdogrpClimateHighHeat.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpClimateHighHeat.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpClimateHighHeat[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                seclblQ7.setVisibility(View.VISIBLE);
                linelblQ7.setVisibility(View.VISIBLE);
                secClimateHighHeatMemberDth.setVisibility(View.VISIBLE);
                lineClimateHighHeatMemberDth.setVisibility(View.VISIBLE);
                secClimateHighHeatHealth.setVisibility(View.VISIBLE);
                lineClimateHighHeatHealth.setVisibility(View.VISIBLE);
                secClimateHighHeatMemberIll.setVisibility(View.VISIBLE);
                lineClimateHighHeatMemberIll.setVisibility(View.VISIBLE);
                secClimateHighHeatPetDth.setVisibility(View.VISIBLE);
                lineClimateHighHeatPetDth.setVisibility(View.VISIBLE);
                secClimateHighHeatOther.setVisibility(View.VISIBLE);
                lineClimateHighHeatOther.setVisibility(View.VISIBLE);
             }
             else
             {
                seclblQ7.setVisibility(View.GONE);
                linelblQ7.setVisibility(View.GONE);
                secClimateHighHeatMemberDth.setVisibility(View.GONE);
                lineClimateHighHeatMemberDth.setVisibility(View.GONE);
                chkClimateHighHeatMemberDth.setChecked(false);
                secClimateHighHeatHealth.setVisibility(View.GONE);
                lineClimateHighHeatHealth.setVisibility(View.GONE);
                chkClimateHighHeatHealth.setChecked(false);
                secClimateHighHeatMemberIll.setVisibility(View.GONE);
                lineClimateHighHeatMemberIll.setVisibility(View.GONE);
                chkClimateHighHeatMemberIll.setChecked(false);
                secClimateHighHeatPetDth.setVisibility(View.GONE);
                lineClimateHighHeatPetDth.setVisibility(View.GONE);
                chkClimateHighHeatPetDth.setChecked(false);
                secClimateHighHeatOther.setVisibility(View.GONE);
                lineClimateHighHeatOther.setVisibility(View.GONE);
                chkClimateHighHeatOther.setChecked(false);
                secClimateHighHeatOtherSp.setVisibility(View.GONE);
                lineClimateHighHeatOtherSp.setVisibility(View.GONE);
                txtClimateHighHeatOtherSp.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclblQ7=(LinearLayout)findViewById(R.id.seclblQ7);
         linelblQ7=(View)findViewById(R.id.linelblQ7);
         secClimateHighHeatMemberDth=(LinearLayout)findViewById(R.id.secClimateHighHeatMemberDth);
         lineClimateHighHeatMemberDth=(View)findViewById(R.id.lineClimateHighHeatMemberDth);
         VlblClimateHighHeatMemberDth=(TextView) findViewById(R.id.VlblClimateHighHeatMemberDth);
         chkClimateHighHeatMemberDth=(CheckBox) findViewById(R.id.chkClimateHighHeatMemberDth);
         secClimateHighHeatHealth=(LinearLayout)findViewById(R.id.secClimateHighHeatHealth);
         lineClimateHighHeatHealth=(View)findViewById(R.id.lineClimateHighHeatHealth);
         VlblClimateHighHeatHealth=(TextView) findViewById(R.id.VlblClimateHighHeatHealth);
         chkClimateHighHeatHealth=(CheckBox) findViewById(R.id.chkClimateHighHeatHealth);
         chkClimateHighHeatHealth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    seclblQ7a.setVisibility(View.VISIBLE);
                    linelblQ7a.setVisibility(View.VISIBLE);
                    secClimateHealthProblemSleep.setVisibility(View.VISIBLE);
                    lineClimateHealthProblemSleep.setVisibility(View.VISIBLE);
                    secClimateHealthProblemDizzy.setVisibility(View.VISIBLE);
                    lineClimateHealthProblemDizzy.setVisibility(View.VISIBLE);
                    secClimateHealthProblemLowBlood.setVisibility(View.VISIBLE);
                    lineClimateHealthProblemLowBlood.setVisibility(View.VISIBLE);
                    secClimateHealthProblemHighBlood.setVisibility(View.VISIBLE);
                    lineClimateHealthProblemHighBlood.setVisibility(View.VISIBLE);
                    secClimateHealthProblemStroke.setVisibility(View.VISIBLE);
                    lineClimateHealthProblemStroke.setVisibility(View.VISIBLE);
                    secClimateHealthProblemFever.setVisibility(View.VISIBLE);
                    lineClimateHealthProblemFever.setVisibility(View.VISIBLE);
                    secClimateHealthProblemOther.setVisibility(View.VISIBLE);
                    lineClimateHealthProblemOther.setVisibility(View.VISIBLE);
                    secClimateHealthProblemPeriod.setVisibility(View.VISIBLE);
                    lineClimateHealthProblemPeriod.setVisibility(View.VISIBLE);
                 }
                 else {
                    seclblQ7a.setVisibility(View.GONE);
                    linelblQ7a.setVisibility(View.GONE);
                    secClimateHealthProblemSleep.setVisibility(View.GONE);
                    lineClimateHealthProblemSleep.setVisibility(View.GONE);
                    chkClimateHealthProblemSleep.setChecked(false);
                    secClimateHealthProblemDizzy.setVisibility(View.GONE);
                    lineClimateHealthProblemDizzy.setVisibility(View.GONE);
                    chkClimateHealthProblemDizzy.setChecked(false);
                    secClimateHealthProblemLowBlood.setVisibility(View.GONE);
                    lineClimateHealthProblemLowBlood.setVisibility(View.GONE);
                    chkClimateHealthProblemLowBlood.setChecked(false);
                    secClimateHealthProblemHighBlood.setVisibility(View.GONE);
                    lineClimateHealthProblemHighBlood.setVisibility(View.GONE);
                    chkClimateHealthProblemHighBlood.setChecked(false);
                    secClimateHealthProblemStroke.setVisibility(View.GONE);
                    lineClimateHealthProblemStroke.setVisibility(View.GONE);
                    chkClimateHealthProblemStroke.setChecked(false);
                    secClimateHealthProblemFever.setVisibility(View.GONE);
                    lineClimateHealthProblemFever.setVisibility(View.GONE);
                    chkClimateHealthProblemFever.setChecked(false);
                    secClimateHealthProblemOther.setVisibility(View.GONE);
                    lineClimateHealthProblemOther.setVisibility(View.GONE);
                    chkClimateHealthProblemOther.setChecked(false);
                    secClimateHealthProblemOtherSp.setVisibility(View.GONE);
                    lineClimateHealthProblemOtherSp.setVisibility(View.GONE);
                    txtClimateHealthProblemOtherSp.setText("");
                    secClimateHealthProblemPeriod.setVisibility(View.GONE);
                    lineClimateHealthProblemPeriod.setVisibility(View.GONE);
                    spnClimateHealthProblemPeriod.setSelection(0);
                 }
              }
         });
         secClimateHighHeatMemberIll=(LinearLayout)findViewById(R.id.secClimateHighHeatMemberIll);
         lineClimateHighHeatMemberIll=(View)findViewById(R.id.lineClimateHighHeatMemberIll);
         VlblClimateHighHeatMemberIll=(TextView) findViewById(R.id.VlblClimateHighHeatMemberIll);
         chkClimateHighHeatMemberIll=(CheckBox) findViewById(R.id.chkClimateHighHeatMemberIll);

        chkClimateHighHeatMemberIll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if (isChecked) {
                 seclblQ7b.setVisibility(View.VISIBLE);
                 linelblQ7b.setVisibility(View.VISIBLE);
                 secClimateIllnessMalaria.setVisibility(View.VISIBLE);
                 lineClimateIllnessMalaria.setVisibility(View.VISIBLE);
                 secClimateIllnessPneumonia.setVisibility(View.VISIBLE);
                 lineClimateIllnessPneumonia.setVisibility(View.VISIBLE);
                 secClimateIllnessMeasles.setVisibility(View.VISIBLE);
                 lineClimateIllnessMeasles.setVisibility(View.VISIBLE);
                 secClimateIllnessPertussis.setVisibility(View.VISIBLE);
                 lineClimateIllnessPertussis.setVisibility(View.VISIBLE);
                 secClimateIllnessDiarrhea.setVisibility(View.VISIBLE);
                 lineClimateIllnessDiarrhea.setVisibility(View.VISIBLE);
                 secClimateIllnessFood.setVisibility(View.VISIBLE);
                 lineClimateIllnessFood.setVisibility(View.VISIBLE);
                 secClimateIllnessTyphoid.setVisibility(View.VISIBLE);
                 lineClimateIllnessTyphoid.setVisibility(View.VISIBLE);
                 secClimateIllnessCough.setVisibility(View.VISIBLE);
                 lineClimateIllnessCough.setVisibility(View.VISIBLE);
                 secClimateIllnessCholera.setVisibility(View.VISIBLE);
                 lineClimateIllnessCholera.setVisibility(View.VISIBLE);
                 secClimateIllnessOther.setVisibility(View.VISIBLE);
                 lineClimateIllnessOther.setVisibility(View.VISIBLE);
                 secClimateIllnessPeriod.setVisibility(View.VISIBLE);
                 lineClimateIllnessPeriod.setVisibility(View.VISIBLE);
              }
              else {
                 seclblQ7b.setVisibility(View.GONE);
                 linelblQ7b.setVisibility(View.GONE);
                 secClimateIllnessMalaria.setVisibility(View.GONE);
                 lineClimateIllnessMalaria.setVisibility(View.GONE);
                 chkClimateIllnessMalaria.setChecked(false);
                 secClimateIllnessPneumonia.setVisibility(View.GONE);
                 lineClimateIllnessPneumonia.setVisibility(View.GONE);
                 chkClimateIllnessPneumonia.setChecked(false);
                 secClimateIllnessMeasles.setVisibility(View.GONE);
                 lineClimateIllnessMeasles.setVisibility(View.GONE);
                 chkClimateIllnessMeasles.setChecked(false);
                 secClimateIllnessPertussis.setVisibility(View.GONE);
                 lineClimateIllnessPertussis.setVisibility(View.GONE);
                 chkClimateIllnessPertussis.setChecked(false);
                 secClimateIllnessDiarrhea.setVisibility(View.GONE);
                 lineClimateIllnessDiarrhea.setVisibility(View.GONE);
                 chkClimateIllnessDiarrhea.setChecked(false);
                 secClimateIllnessFood.setVisibility(View.GONE);
                 lineClimateIllnessFood.setVisibility(View.GONE);
                 chkClimateIllnessFood.setChecked(false);
                 secClimateIllnessTyphoid.setVisibility(View.GONE);
                 lineClimateIllnessTyphoid.setVisibility(View.GONE);
                 chkClimateIllnessTyphoid.setChecked(false);
                 secClimateIllnessCough.setVisibility(View.GONE);
                 lineClimateIllnessCough.setVisibility(View.GONE);
                 chkClimateIllnessCough.setChecked(false);
                 secClimateIllnessCholera.setVisibility(View.GONE);
                 lineClimateIllnessCholera.setVisibility(View.GONE);
                 chkClimateIllnessCholera.setChecked(false);
                 secClimateIllnessOther.setVisibility(View.GONE);
                 lineClimateIllnessOther.setVisibility(View.GONE);
                 chkClimateIllnessOther.setChecked(false);
                 secClimateIllnessOtherSp.setVisibility(View.GONE);
                 lineClimateIllnessOtherSp.setVisibility(View.GONE);
                 txtClimateIllnessOtherSp.setText("");
                 secClimateIllnessPeriod.setVisibility(View.GONE);
                 lineClimateIllnessPeriod.setVisibility(View.GONE);
                 spnClimateIllnessPeriod.setSelection(0);
              }
           }
        });

         secClimateHighHeatPetDth=(LinearLayout)findViewById(R.id.secClimateHighHeatPetDth);
         lineClimateHighHeatPetDth=(View)findViewById(R.id.lineClimateHighHeatPetDth);
         VlblClimateHighHeatPetDth=(TextView) findViewById(R.id.VlblClimateHighHeatPetDth);
         chkClimateHighHeatPetDth=(CheckBox) findViewById(R.id.chkClimateHighHeatPetDth);
         secClimateHighHeatOther=(LinearLayout)findViewById(R.id.secClimateHighHeatOther);
         lineClimateHighHeatOther=(View)findViewById(R.id.lineClimateHighHeatOther);
         VlblClimateHighHeatOther=(TextView) findViewById(R.id.VlblClimateHighHeatOther);
         chkClimateHighHeatOther=(CheckBox) findViewById(R.id.chkClimateHighHeatOther);
         chkClimateHighHeatOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secClimateHighHeatOtherSp.setVisibility(View.VISIBLE);
                    lineClimateHighHeatOtherSp.setVisibility(View.VISIBLE);
                 }
                 else {
                    secClimateHighHeatOtherSp.setVisibility(View.GONE);
                    lineClimateHighHeatOtherSp.setVisibility(View.GONE);
                    txtClimateHighHeatOtherSp.setText("");
                 }
                 }
         });
         secClimateHighHeatOtherSp=(LinearLayout)findViewById(R.id.secClimateHighHeatOtherSp);
         lineClimateHighHeatOtherSp=(View)findViewById(R.id.lineClimateHighHeatOtherSp);
         VlblClimateHighHeatOtherSp=(TextView) findViewById(R.id.VlblClimateHighHeatOtherSp);
         txtClimateHighHeatOtherSp=(EditText) findViewById(R.id.txtClimateHighHeatOtherSp);
         seclblQ7a=(LinearLayout)findViewById(R.id.seclblQ7a);
         linelblQ7a=(View)findViewById(R.id.linelblQ7a);
         secClimateHealthProblemSleep=(LinearLayout)findViewById(R.id.secClimateHealthProblemSleep);
         lineClimateHealthProblemSleep=(View)findViewById(R.id.lineClimateHealthProblemSleep);
         VlblClimateHealthProblemSleep=(TextView) findViewById(R.id.VlblClimateHealthProblemSleep);
         chkClimateHealthProblemSleep=(CheckBox) findViewById(R.id.chkClimateHealthProblemSleep);
         secClimateHealthProblemDizzy=(LinearLayout)findViewById(R.id.secClimateHealthProblemDizzy);
         lineClimateHealthProblemDizzy=(View)findViewById(R.id.lineClimateHealthProblemDizzy);
         VlblClimateHealthProblemDizzy=(TextView) findViewById(R.id.VlblClimateHealthProblemDizzy);
         chkClimateHealthProblemDizzy=(CheckBox) findViewById(R.id.chkClimateHealthProblemDizzy);
         secClimateHealthProblemLowBlood=(LinearLayout)findViewById(R.id.secClimateHealthProblemLowBlood);
         lineClimateHealthProblemLowBlood=(View)findViewById(R.id.lineClimateHealthProblemLowBlood);
         VlblClimateHealthProblemLowBlood=(TextView) findViewById(R.id.VlblClimateHealthProblemLowBlood);
         chkClimateHealthProblemLowBlood=(CheckBox) findViewById(R.id.chkClimateHealthProblemLowBlood);
         secClimateHealthProblemHighBlood=(LinearLayout)findViewById(R.id.secClimateHealthProblemHighBlood);
         lineClimateHealthProblemHighBlood=(View)findViewById(R.id.lineClimateHealthProblemHighBlood);
         VlblClimateHealthProblemHighBlood=(TextView) findViewById(R.id.VlblClimateHealthProblemHighBlood);
         chkClimateHealthProblemHighBlood=(CheckBox) findViewById(R.id.chkClimateHealthProblemHighBlood);
         secClimateHealthProblemStroke=(LinearLayout)findViewById(R.id.secClimateHealthProblemStroke);
         lineClimateHealthProblemStroke=(View)findViewById(R.id.lineClimateHealthProblemStroke);
         VlblClimateHealthProblemStroke=(TextView) findViewById(R.id.VlblClimateHealthProblemStroke);
         chkClimateHealthProblemStroke=(CheckBox) findViewById(R.id.chkClimateHealthProblemStroke);
         secClimateHealthProblemFever=(LinearLayout)findViewById(R.id.secClimateHealthProblemFever);
         lineClimateHealthProblemFever=(View)findViewById(R.id.lineClimateHealthProblemFever);
         VlblClimateHealthProblemFever=(TextView) findViewById(R.id.VlblClimateHealthProblemFever);
         chkClimateHealthProblemFever=(CheckBox) findViewById(R.id.chkClimateHealthProblemFever);
         secClimateHealthProblemOther=(LinearLayout)findViewById(R.id.secClimateHealthProblemOther);
         lineClimateHealthProblemOther=(View)findViewById(R.id.lineClimateHealthProblemOther);
         VlblClimateHealthProblemOther=(TextView) findViewById(R.id.VlblClimateHealthProblemOther);
         chkClimateHealthProblemOther=(CheckBox) findViewById(R.id.chkClimateHealthProblemOther);
         chkClimateHealthProblemOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secClimateHealthProblemOtherSp.setVisibility(View.VISIBLE);
                    lineClimateHealthProblemOtherSp.setVisibility(View.VISIBLE);
                 }
                 else {
                    secClimateHealthProblemOtherSp.setVisibility(View.GONE);
                    lineClimateHealthProblemOtherSp.setVisibility(View.GONE);
                    txtClimateHealthProblemOtherSp.setText("");
                 }
                 }
         });
         secClimateHealthProblemOtherSp=(LinearLayout)findViewById(R.id.secClimateHealthProblemOtherSp);
         lineClimateHealthProblemOtherSp=(View)findViewById(R.id.lineClimateHealthProblemOtherSp);
         VlblClimateHealthProblemOtherSp=(TextView) findViewById(R.id.VlblClimateHealthProblemOtherSp);
         txtClimateHealthProblemOtherSp=(EditText) findViewById(R.id.txtClimateHealthProblemOtherSp);
         secClimateHealthProblemPeriod=(LinearLayout)findViewById(R.id.secClimateHealthProblemPeriod);
         lineClimateHealthProblemPeriod=(View)findViewById(R.id.lineClimateHealthProblemPeriod);
         VlblClimateHealthProblemPeriod=(TextView) findViewById(R.id.VlblClimateHealthProblemPeriod);
         spnClimateHealthProblemPeriod=(Spinner) findViewById(R.id.spnClimateHealthProblemPeriod);
         List<String> listClimateHealthProblemPeriod = new ArrayList<String>();
         
         listClimateHealthProblemPeriod.add("");
         listClimateHealthProblemPeriod.add("1- From January to June ");
         listClimateHealthProblemPeriod.add("2- From July to September");
         listClimateHealthProblemPeriod.add("3- From October to December");
         spnClimateHealthProblemPeriod.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listClimateHealthProblemPeriod)));

         seclblQ7b=(LinearLayout)findViewById(R.id.seclblQ7b);
         linelblQ7b=(View)findViewById(R.id.linelblQ7b);
         secClimateIllnessMalaria=(LinearLayout)findViewById(R.id.secClimateIllnessMalaria);
         lineClimateIllnessMalaria=(View)findViewById(R.id.lineClimateIllnessMalaria);
         VlblClimateIllnessMalaria=(TextView) findViewById(R.id.VlblClimateIllnessMalaria);
         chkClimateIllnessMalaria=(CheckBox) findViewById(R.id.chkClimateIllnessMalaria);
         secClimateIllnessPneumonia=(LinearLayout)findViewById(R.id.secClimateIllnessPneumonia);
         lineClimateIllnessPneumonia=(View)findViewById(R.id.lineClimateIllnessPneumonia);
         VlblClimateIllnessPneumonia=(TextView) findViewById(R.id.VlblClimateIllnessPneumonia);
         chkClimateIllnessPneumonia=(CheckBox) findViewById(R.id.chkClimateIllnessPneumonia);
         secClimateIllnessMeasles=(LinearLayout)findViewById(R.id.secClimateIllnessMeasles);
         lineClimateIllnessMeasles=(View)findViewById(R.id.lineClimateIllnessMeasles);
         VlblClimateIllnessMeasles=(TextView) findViewById(R.id.VlblClimateIllnessMeasles);
         chkClimateIllnessMeasles=(CheckBox) findViewById(R.id.chkClimateIllnessMeasles);
         secClimateIllnessPertussis=(LinearLayout)findViewById(R.id.secClimateIllnessPertussis);
         lineClimateIllnessPertussis=(View)findViewById(R.id.lineClimateIllnessPertussis);
         VlblClimateIllnessPertussis=(TextView) findViewById(R.id.VlblClimateIllnessPertussis);
         chkClimateIllnessPertussis=(CheckBox) findViewById(R.id.chkClimateIllnessPertussis);
         secClimateIllnessDiarrhea=(LinearLayout)findViewById(R.id.secClimateIllnessDiarrhea);
         lineClimateIllnessDiarrhea=(View)findViewById(R.id.lineClimateIllnessDiarrhea);
         VlblClimateIllnessDiarrhea=(TextView) findViewById(R.id.VlblClimateIllnessDiarrhea);
         chkClimateIllnessDiarrhea=(CheckBox) findViewById(R.id.chkClimateIllnessDiarrhea);
         secClimateIllnessFood=(LinearLayout)findViewById(R.id.secClimateIllnessFood);
         lineClimateIllnessFood=(View)findViewById(R.id.lineClimateIllnessFood);
         VlblClimateIllnessFood=(TextView) findViewById(R.id.VlblClimateIllnessFood);
         chkClimateIllnessFood=(CheckBox) findViewById(R.id.chkClimateIllnessFood);
         secClimateIllnessTyphoid=(LinearLayout)findViewById(R.id.secClimateIllnessTyphoid);
         lineClimateIllnessTyphoid=(View)findViewById(R.id.lineClimateIllnessTyphoid);
         VlblClimateIllnessTyphoid=(TextView) findViewById(R.id.VlblClimateIllnessTyphoid);
         chkClimateIllnessTyphoid=(CheckBox) findViewById(R.id.chkClimateIllnessTyphoid);
         secClimateIllnessCough=(LinearLayout)findViewById(R.id.secClimateIllnessCough);
         lineClimateIllnessCough=(View)findViewById(R.id.lineClimateIllnessCough);
         VlblClimateIllnessCough=(TextView) findViewById(R.id.VlblClimateIllnessCough);
         chkClimateIllnessCough=(CheckBox) findViewById(R.id.chkClimateIllnessCough);
         secClimateIllnessCholera=(LinearLayout)findViewById(R.id.secClimateIllnessCholera);
         lineClimateIllnessCholera=(View)findViewById(R.id.lineClimateIllnessCholera);
         VlblClimateIllnessCholera=(TextView) findViewById(R.id.VlblClimateIllnessCholera);
         chkClimateIllnessCholera=(CheckBox) findViewById(R.id.chkClimateIllnessCholera);
         secClimateIllnessOther=(LinearLayout)findViewById(R.id.secClimateIllnessOther);
         lineClimateIllnessOther=(View)findViewById(R.id.lineClimateIllnessOther);
         VlblClimateIllnessOther=(TextView) findViewById(R.id.VlblClimateIllnessOther);
         chkClimateIllnessOther=(CheckBox) findViewById(R.id.chkClimateIllnessOther);
         chkClimateIllnessOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secClimateIllnessOtherSp.setVisibility(View.VISIBLE);
                    lineClimateIllnessOtherSp.setVisibility(View.VISIBLE);
                 }
                 else {
                    secClimateIllnessOtherSp.setVisibility(View.GONE);
                    lineClimateIllnessOtherSp.setVisibility(View.GONE);
                    txtClimateIllnessOtherSp.setText("");
                 }
                 }
         });
         secClimateIllnessOtherSp=(LinearLayout)findViewById(R.id.secClimateIllnessOtherSp);
         lineClimateIllnessOtherSp=(View)findViewById(R.id.lineClimateIllnessOtherSp);
         VlblClimateIllnessOtherSp=(TextView) findViewById(R.id.VlblClimateIllnessOtherSp);
         txtClimateIllnessOtherSp=(EditText) findViewById(R.id.txtClimateIllnessOtherSp);
         secClimateIllnessPeriod=(LinearLayout)findViewById(R.id.secClimateIllnessPeriod);
         lineClimateIllnessPeriod=(View)findViewById(R.id.lineClimateIllnessPeriod);
         VlblClimateIllnessPeriod=(TextView) findViewById(R.id.VlblClimateIllnessPeriod);
         spnClimateIllnessPeriod=(Spinner) findViewById(R.id.spnClimateIllnessPeriod);
         List<String> listClimateIllnessPeriod = new ArrayList<String>();
         
         listClimateIllnessPeriod.add("");
         listClimateIllnessPeriod.add("1- From January to June ");
         listClimateIllnessPeriod.add("2- From July to September");
         listClimateIllnessPeriod.add("3- From October to December");
         spnClimateIllnessPeriod.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listClimateIllnessPeriod)));

         seclblP2=(LinearLayout)findViewById(R.id.seclblP2);
         linelblP2=(View)findViewById(R.id.linelblP2);
         secClimateHeard=(LinearLayout)findViewById(R.id.secClimateHeard);
         lineClimateHeard=(View)findViewById(R.id.lineClimateHeard);
         VlblClimateHeard = (TextView) findViewById(R.id.VlblClimateHeard);
         rdogrpClimateHeard = (RadioGroup) findViewById(R.id.rdogrpClimateHeard);
         rdoClimateHeard1 = (RadioButton) findViewById(R.id.rdoClimateHeard1);
         rdoClimateHeard2 = (RadioButton) findViewById(R.id.rdoClimateHeard2);
         rdogrpClimateHeard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpClimateHeard = new String[] {"0","1"};
             for (int i = 0; i < rdogrpClimateHeard.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpClimateHeard.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpClimateHeard[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                seclblQ9.setVisibility(View.VISIBLE);
                linelblQ9.setVisibility(View.VISIBLE);
                secClimateInfoMedia.setVisibility(View.VISIBLE);
                lineClimateInfoMedia.setVisibility(View.VISIBLE);
                secClimateInfoInternet.setVisibility(View.VISIBLE);
                lineClimateInfoInternet.setVisibility(View.VISIBLE);
                secClimateInfoEducation.setVisibility(View.VISIBLE);
                lineClimateInfoEducation.setVisibility(View.VISIBLE);
                secClimateInfoFnF.setVisibility(View.VISIBLE);
                lineClimateInfoFnF.setVisibility(View.VISIBLE);
                secClimateInfoOther.setVisibility(View.VISIBLE);
                lineClimateInfoOther.setVisibility(View.VISIBLE);
                secClimateKnRate.setVisibility(View.VISIBLE);
                lineClimateKnRate.setVisibility(View.VISIBLE);
                seclblP3.setVisibility(View.VISIBLE);
                linelblP3.setVisibility(View.VISIBLE);
                secClimatethreat.setVisibility(View.VISIBLE);
                lineClimatethreat.setVisibility(View.VISIBLE);
                secClimateWorried.setVisibility(View.VISIBLE);
                lineClimateWorried.setVisibility(View.VISIBLE);
                seclblQ13.setVisibility(View.VISIBLE);
                linelblQ13.setVisibility(View.VISIBLE);
                secClimateConcernTemp.setVisibility(View.VISIBLE);
                lineClimateConcernTemp.setVisibility(View.VISIBLE);
                secClimateConcernEvent.setVisibility(View.VISIBLE);
                lineClimateConcernEvent.setVisibility(View.VISIBLE);
                secClimateConcernPolarIce.setVisibility(View.VISIBLE);
                lineClimateConcernPolarIce.setVisibility(View.VISIBLE);
                secClimateConcernSeaLevel.setVisibility(View.VISIBLE);
                lineClimateConcernSeaLevel.setVisibility(View.VISIBLE);
                secClimateConcernBiodiversity.setVisibility(View.VISIBLE);
                lineClimateConcernBiodiversity.setVisibility(View.VISIBLE);
                secClimateConcernDrying.setVisibility(View.VISIBLE);
                lineClimateConcernDrying.setVisibility(View.VISIBLE);
                secClimateConcernRising.setVisibility(View.VISIBLE);
                lineClimateConcernRising.setVisibility(View.VISIBLE);
                secClimateConcernDrought.setVisibility(View.VISIBLE);
                lineClimateConcernDrought.setVisibility(View.VISIBLE);
                secClimateConcernDesert.setVisibility(View.VISIBLE);
                lineClimateConcernDesert.setVisibility(View.VISIBLE);
                secClimateConcernErosin.setVisibility(View.VISIBLE);
                lineClimateConcernErosin.setVisibility(View.VISIBLE);
                secClimateConcernOther.setVisibility(View.VISIBLE);
                lineClimateConcernOther.setVisibility(View.VISIBLE);
                secClimateComDoing.setVisibility(View.VISIBLE);
                lineClimateComDoing.setVisibility(View.VISIBLE);
             }
             else
             {
                seclblQ9.setVisibility(View.GONE);
                linelblQ9.setVisibility(View.GONE);
                secClimateInfoMedia.setVisibility(View.GONE);
                lineClimateInfoMedia.setVisibility(View.GONE);
                chkClimateInfoMedia.setChecked(false);
                secClimateInfoInternet.setVisibility(View.GONE);
                lineClimateInfoInternet.setVisibility(View.GONE);
                chkClimateInfoInternet.setChecked(false);
                secClimateInfoEducation.setVisibility(View.GONE);
                lineClimateInfoEducation.setVisibility(View.GONE);
                chkClimateInfoEducation.setChecked(false);
                secClimateInfoFnF.setVisibility(View.GONE);
                lineClimateInfoFnF.setVisibility(View.GONE);
                chkClimateInfoFnF.setChecked(false);
                secClimateInfoOther.setVisibility(View.GONE);
                lineClimateInfoOther.setVisibility(View.GONE);
                chkClimateInfoOther.setChecked(false);
                secClimateInfoOtherSp.setVisibility(View.GONE);
                lineClimateInfoOtherSp.setVisibility(View.GONE);
                txtClimateInfoOtherSp.setText("");
                secClimateKnRate.setVisibility(View.GONE);
                lineClimateKnRate.setVisibility(View.GONE);
                spnClimateKnRate.setSelection(0);
                seclblP3.setVisibility(View.GONE);
                linelblP3.setVisibility(View.GONE);
                secClimatethreat.setVisibility(View.GONE);
                lineClimatethreat.setVisibility(View.GONE);
                rdogrpClimatethreat.clearCheck();
                secClimateWorried.setVisibility(View.GONE);
                lineClimateWorried.setVisibility(View.GONE);
                spnClimateWorried.setSelection(0);
                seclblQ13.setVisibility(View.GONE);
                linelblQ13.setVisibility(View.GONE);
                secClimateConcernTemp.setVisibility(View.GONE);
                lineClimateConcernTemp.setVisibility(View.GONE);
                chkClimateConcernTemp.setChecked(false);
                secClimateConcernEvent.setVisibility(View.GONE);
                lineClimateConcernEvent.setVisibility(View.GONE);
                chkClimateConcernEvent.setChecked(false);
                secClimateConcernPolarIce.setVisibility(View.GONE);
                lineClimateConcernPolarIce.setVisibility(View.GONE);
                chkClimateConcernPolarIce.setChecked(false);
                secClimateConcernSeaLevel.setVisibility(View.GONE);
                lineClimateConcernSeaLevel.setVisibility(View.GONE);
                chkClimateConcernSeaLevel.setChecked(false);
                secClimateConcernBiodiversity.setVisibility(View.GONE);
                lineClimateConcernBiodiversity.setVisibility(View.GONE);
                chkClimateConcernBiodiversity.setChecked(false);
                secClimateConcernDrying.setVisibility(View.GONE);
                lineClimateConcernDrying.setVisibility(View.GONE);
                chkClimateConcernDrying.setChecked(false);
                secClimateConcernRising.setVisibility(View.GONE);
                lineClimateConcernRising.setVisibility(View.GONE);
                chkClimateConcernRising.setChecked(false);
                secClimateConcernDrought.setVisibility(View.GONE);
                lineClimateConcernDrought.setVisibility(View.GONE);
                chkClimateConcernDrought.setChecked(false);
                secClimateConcernDesert.setVisibility(View.GONE);
                lineClimateConcernDesert.setVisibility(View.GONE);
                chkClimateConcernDesert.setChecked(false);
                secClimateConcernErosin.setVisibility(View.GONE);
                lineClimateConcernErosin.setVisibility(View.GONE);
                chkClimateConcernErosin.setChecked(false);
                secClimateConcernOther.setVisibility(View.GONE);
                lineClimateConcernOther.setVisibility(View.GONE);
                chkClimateConcernOther.setChecked(false);
                secClimateConcernOtherSp.setVisibility(View.GONE);
                lineClimateConcernOtherSp.setVisibility(View.GONE);
                txtClimateConcernOtherSp.setText("");
                secClimateComDoing.setVisibility(View.GONE);
                lineClimateComDoing.setVisibility(View.GONE);
                rdogrpClimateComDoing.clearCheck();
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclblQ9=(LinearLayout)findViewById(R.id.seclblQ9);
         linelblQ9=(View)findViewById(R.id.linelblQ9);
         secClimateInfoMedia=(LinearLayout)findViewById(R.id.secClimateInfoMedia);
         lineClimateInfoMedia=(View)findViewById(R.id.lineClimateInfoMedia);
         VlblClimateInfoMedia=(TextView) findViewById(R.id.VlblClimateInfoMedia);
         chkClimateInfoMedia=(CheckBox) findViewById(R.id.chkClimateInfoMedia);
         secClimateInfoInternet=(LinearLayout)findViewById(R.id.secClimateInfoInternet);
         lineClimateInfoInternet=(View)findViewById(R.id.lineClimateInfoInternet);
         VlblClimateInfoInternet=(TextView) findViewById(R.id.VlblClimateInfoInternet);
         chkClimateInfoInternet=(CheckBox) findViewById(R.id.chkClimateInfoInternet);
         secClimateInfoEducation=(LinearLayout)findViewById(R.id.secClimateInfoEducation);
         lineClimateInfoEducation=(View)findViewById(R.id.lineClimateInfoEducation);
         VlblClimateInfoEducation=(TextView) findViewById(R.id.VlblClimateInfoEducation);
         chkClimateInfoEducation=(CheckBox) findViewById(R.id.chkClimateInfoEducation);
         secClimateInfoFnF=(LinearLayout)findViewById(R.id.secClimateInfoFnF);
         lineClimateInfoFnF=(View)findViewById(R.id.lineClimateInfoFnF);
         VlblClimateInfoFnF=(TextView) findViewById(R.id.VlblClimateInfoFnF);
         chkClimateInfoFnF=(CheckBox) findViewById(R.id.chkClimateInfoFnF);
         secClimateInfoOther=(LinearLayout)findViewById(R.id.secClimateInfoOther);
         lineClimateInfoOther=(View)findViewById(R.id.lineClimateInfoOther);
         VlblClimateInfoOther=(TextView) findViewById(R.id.VlblClimateInfoOther);
         chkClimateInfoOther=(CheckBox) findViewById(R.id.chkClimateInfoOther);
         chkClimateInfoOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secClimateInfoOtherSp.setVisibility(View.VISIBLE);
                    lineClimateInfoOtherSp.setVisibility(View.VISIBLE);
                 }
                 else {
                    secClimateInfoOtherSp.setVisibility(View.GONE);
                    lineClimateInfoOtherSp.setVisibility(View.GONE);
                    txtClimateInfoOtherSp.setText("");
                 }
                 }
         });
         secClimateInfoOtherSp=(LinearLayout)findViewById(R.id.secClimateInfoOtherSp);
         lineClimateInfoOtherSp=(View)findViewById(R.id.lineClimateInfoOtherSp);
         VlblClimateInfoOtherSp=(TextView) findViewById(R.id.VlblClimateInfoOtherSp);
         txtClimateInfoOtherSp=(EditText) findViewById(R.id.txtClimateInfoOtherSp);
         secClimateKnRate=(LinearLayout)findViewById(R.id.secClimateKnRate);
         lineClimateKnRate=(View)findViewById(R.id.lineClimateKnRate);
         VlblClimateKnRate=(TextView) findViewById(R.id.VlblClimateKnRate);
         spnClimateKnRate=(Spinner) findViewById(R.id.spnClimateKnRate);
         List<String> listClimateKnRate = new ArrayList<String>();
         
         listClimateKnRate.add("");
         listClimateKnRate.add("1-Very Low");
         listClimateKnRate.add("2-Low");
         listClimateKnRate.add("3-Average");
         listClimateKnRate.add("4-Good");
         listClimateKnRate.add("5-Very Good");
         spnClimateKnRate.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listClimateKnRate)));

         seclblP3=(LinearLayout)findViewById(R.id.seclblP3);
         linelblP3=(View)findViewById(R.id.linelblP3);
         secClimatethreat=(LinearLayout)findViewById(R.id.secClimatethreat);
         lineClimatethreat=(View)findViewById(R.id.lineClimatethreat);
         VlblClimatethreat = (TextView) findViewById(R.id.VlblClimatethreat);
         rdogrpClimatethreat = (RadioGroup) findViewById(R.id.rdogrpClimatethreat);
         rdoClimatethreat1 = (RadioButton) findViewById(R.id.rdoClimatethreat1);
         rdoClimatethreat2 = (RadioButton) findViewById(R.id.rdoClimatethreat2);
         rdoClimatethreat3 = (RadioButton) findViewById(R.id.rdoClimatethreat3);
         secClimateWorried=(LinearLayout)findViewById(R.id.secClimateWorried);
         lineClimateWorried=(View)findViewById(R.id.lineClimateWorried);
         VlblClimateWorried=(TextView) findViewById(R.id.VlblClimateWorried);
         spnClimateWorried=(Spinner) findViewById(R.id.spnClimateWorried);
         List<String> listClimateWorried = new ArrayList<String>();
         
         listClimateWorried.add("");
         listClimateWorried.add("1-Not at all worried");
         listClimateWorried.add("2-Not worried");
         listClimateWorried.add("3-Moderately worried");
         listClimateWorried.add("4-Very worried");
         listClimateWorried.add("5-Extremely worried");
         spnClimateWorried.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listClimateWorried)));

         seclblQ13=(LinearLayout)findViewById(R.id.seclblQ13);
         linelblQ13=(View)findViewById(R.id.linelblQ13);
         secClimateConcernTemp=(LinearLayout)findViewById(R.id.secClimateConcernTemp);
         lineClimateConcernTemp=(View)findViewById(R.id.lineClimateConcernTemp);
         VlblClimateConcernTemp=(TextView) findViewById(R.id.VlblClimateConcernTemp);
         chkClimateConcernTemp=(CheckBox) findViewById(R.id.chkClimateConcernTemp);
         secClimateConcernEvent=(LinearLayout)findViewById(R.id.secClimateConcernEvent);
         lineClimateConcernEvent=(View)findViewById(R.id.lineClimateConcernEvent);
         VlblClimateConcernEvent=(TextView) findViewById(R.id.VlblClimateConcernEvent);
         chkClimateConcernEvent=(CheckBox) findViewById(R.id.chkClimateConcernEvent);
         secClimateConcernPolarIce=(LinearLayout)findViewById(R.id.secClimateConcernPolarIce);
         lineClimateConcernPolarIce=(View)findViewById(R.id.lineClimateConcernPolarIce);
         VlblClimateConcernPolarIce=(TextView) findViewById(R.id.VlblClimateConcernPolarIce);
         chkClimateConcernPolarIce=(CheckBox) findViewById(R.id.chkClimateConcernPolarIce);
         secClimateConcernSeaLevel=(LinearLayout)findViewById(R.id.secClimateConcernSeaLevel);
         lineClimateConcernSeaLevel=(View)findViewById(R.id.lineClimateConcernSeaLevel);
         VlblClimateConcernSeaLevel=(TextView) findViewById(R.id.VlblClimateConcernSeaLevel);
         chkClimateConcernSeaLevel=(CheckBox) findViewById(R.id.chkClimateConcernSeaLevel);
         secClimateConcernBiodiversity=(LinearLayout)findViewById(R.id.secClimateConcernBiodiversity);
         lineClimateConcernBiodiversity=(View)findViewById(R.id.lineClimateConcernBiodiversity);
         VlblClimateConcernBiodiversity=(TextView) findViewById(R.id.VlblClimateConcernBiodiversity);
         chkClimateConcernBiodiversity=(CheckBox) findViewById(R.id.chkClimateConcernBiodiversity);
         secClimateConcernDrying=(LinearLayout)findViewById(R.id.secClimateConcernDrying);
         lineClimateConcernDrying=(View)findViewById(R.id.lineClimateConcernDrying);
         VlblClimateConcernDrying=(TextView) findViewById(R.id.VlblClimateConcernDrying);
         chkClimateConcernDrying=(CheckBox) findViewById(R.id.chkClimateConcernDrying);
         secClimateConcernRising=(LinearLayout)findViewById(R.id.secClimateConcernRising);
         lineClimateConcernRising=(View)findViewById(R.id.lineClimateConcernRising);
         VlblClimateConcernRising=(TextView) findViewById(R.id.VlblClimateConcernRising);
         chkClimateConcernRising=(CheckBox) findViewById(R.id.chkClimateConcernRising);
         secClimateConcernDrought=(LinearLayout)findViewById(R.id.secClimateConcernDrought);
         lineClimateConcernDrought=(View)findViewById(R.id.lineClimateConcernDrought);
         VlblClimateConcernDrought=(TextView) findViewById(R.id.VlblClimateConcernDrought);
         chkClimateConcernDrought=(CheckBox) findViewById(R.id.chkClimateConcernDrought);
         secClimateConcernDesert=(LinearLayout)findViewById(R.id.secClimateConcernDesert);
         lineClimateConcernDesert=(View)findViewById(R.id.lineClimateConcernDesert);
         VlblClimateConcernDesert=(TextView) findViewById(R.id.VlblClimateConcernDesert);
         chkClimateConcernDesert=(CheckBox) findViewById(R.id.chkClimateConcernDesert);
         secClimateConcernErosin=(LinearLayout)findViewById(R.id.secClimateConcernErosin);
         lineClimateConcernErosin=(View)findViewById(R.id.lineClimateConcernErosin);
         VlblClimateConcernErosin=(TextView) findViewById(R.id.VlblClimateConcernErosin);
         chkClimateConcernErosin=(CheckBox) findViewById(R.id.chkClimateConcernErosin);
         secClimateConcernOther=(LinearLayout)findViewById(R.id.secClimateConcernOther);
         lineClimateConcernOther=(View)findViewById(R.id.lineClimateConcernOther);
         VlblClimateConcernOther=(TextView) findViewById(R.id.VlblClimateConcernOther);
         chkClimateConcernOther=(CheckBox) findViewById(R.id.chkClimateConcernOther);
         chkClimateConcernOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secClimateConcernOtherSp.setVisibility(View.VISIBLE);
                    lineClimateConcernOtherSp.setVisibility(View.VISIBLE);
                 }
                 else {
                    secClimateConcernOtherSp.setVisibility(View.GONE);
                    lineClimateConcernOtherSp.setVisibility(View.GONE);
                    txtClimateConcernOtherSp.setText("");
                 }
                 }
         });
         secClimateConcernOtherSp=(LinearLayout)findViewById(R.id.secClimateConcernOtherSp);
         lineClimateConcernOtherSp=(View)findViewById(R.id.lineClimateConcernOtherSp);
         VlblClimateConcernOtherSp=(TextView) findViewById(R.id.VlblClimateConcernOtherSp);
         txtClimateConcernOtherSp=(EditText) findViewById(R.id.txtClimateConcernOtherSp);
         secClimateComDoing=(LinearLayout)findViewById(R.id.secClimateComDoing);
         lineClimateComDoing=(View)findViewById(R.id.lineClimateComDoing);
         VlblClimateComDoing = (TextView) findViewById(R.id.VlblClimateComDoing);
         rdogrpClimateComDoing = (RadioGroup) findViewById(R.id.rdogrpClimateComDoing);
         rdoClimateComDoing1 = (RadioButton) findViewById(R.id.rdoClimateComDoing1);
         rdoClimateComDoing2 = (RadioButton) findViewById(R.id.rdoClimateComDoing2);
         rdoClimateComDoing3 = (RadioButton) findViewById(R.id.rdoClimateComDoing3);
         seclblP4=(LinearLayout)findViewById(R.id.seclblP4);
         linelblP4=(View)findViewById(R.id.linelblP4);
         secClimateReduceImpact=(LinearLayout)findViewById(R.id.secClimateReduceImpact);
         lineClimateReduceImpact=(View)findViewById(R.id.lineClimateReduceImpact);
         VlblClimateReduceImpact = (TextView) findViewById(R.id.VlblClimateReduceImpact);
         rdogrpClimateReduceImpact = (RadioGroup) findViewById(R.id.rdogrpClimateReduceImpact);
         rdoClimateReduceImpact1 = (RadioButton) findViewById(R.id.rdoClimateReduceImpact1);
         rdoClimateReduceImpact2 = (RadioButton) findViewById(R.id.rdoClimateReduceImpact2);
         rdogrpClimateReduceImpact.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpClimateReduceImpact = new String[] {"0","1"};
             for (int i = 0; i < rdogrpClimateReduceImpact.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpClimateReduceImpact.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpClimateReduceImpact[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                seclblQ16.setVisibility(View.VISIBLE);
                linelblQ16.setVisibility(View.VISIBLE);
                secClimateActionsPlastics.setVisibility(View.VISIBLE);
                lineClimateActionsPlastics.setVisibility(View.VISIBLE);
                secClimateActionsTransport.setVisibility(View.VISIBLE);
                lineClimateActionsTransport.setVisibility(View.VISIBLE);
                secClimateActionsFoods.setVisibility(View.VISIBLE);
                lineClimateActionsFoods.setVisibility(View.VISIBLE);
                secClimateActionsHome.setVisibility(View.VISIBLE);
                lineClimateActionsHome.setVisibility(View.VISIBLE);
                secClimateActionsOther.setVisibility(View.VISIBLE);
                lineClimateActionsOther.setVisibility(View.VISIBLE);
             }
             else
             {
                seclblQ16.setVisibility(View.GONE);
                linelblQ16.setVisibility(View.GONE);
                secClimateActionsPlastics.setVisibility(View.GONE);
                lineClimateActionsPlastics.setVisibility(View.GONE);
                chkClimateActionsPlastics.setChecked(false);
                secClimateActionsTransport.setVisibility(View.GONE);
                lineClimateActionsTransport.setVisibility(View.GONE);
                chkClimateActionsTransport.setChecked(false);
                secClimateActionsFoods.setVisibility(View.GONE);
                lineClimateActionsFoods.setVisibility(View.GONE);
                chkClimateActionsFoods.setChecked(false);
                secClimateActionsHome.setVisibility(View.GONE);
                lineClimateActionsHome.setVisibility(View.GONE);
                chkClimateActionsHome.setChecked(false);
                secClimateActionsOther.setVisibility(View.GONE);
                lineClimateActionsOther.setVisibility(View.GONE);
                chkClimateActionsOther.setChecked(false);
                secClimateActionsOtherSp.setVisibility(View.GONE);
                lineClimateActionsOtherSp.setVisibility(View.GONE);
                txtClimateActionsOtherSp.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclblQ16=(LinearLayout)findViewById(R.id.seclblQ16);
         linelblQ16=(View)findViewById(R.id.linelblQ16);
         secClimateActionsPlastics=(LinearLayout)findViewById(R.id.secClimateActionsPlastics);
         lineClimateActionsPlastics=(View)findViewById(R.id.lineClimateActionsPlastics);
         VlblClimateActionsPlastics=(TextView) findViewById(R.id.VlblClimateActionsPlastics);
         chkClimateActionsPlastics=(CheckBox) findViewById(R.id.chkClimateActionsPlastics);
         secClimateActionsTransport=(LinearLayout)findViewById(R.id.secClimateActionsTransport);
         lineClimateActionsTransport=(View)findViewById(R.id.lineClimateActionsTransport);
         VlblClimateActionsTransport=(TextView) findViewById(R.id.VlblClimateActionsTransport);
         chkClimateActionsTransport=(CheckBox) findViewById(R.id.chkClimateActionsTransport);
         secClimateActionsFoods=(LinearLayout)findViewById(R.id.secClimateActionsFoods);
         lineClimateActionsFoods=(View)findViewById(R.id.lineClimateActionsFoods);
         VlblClimateActionsFoods=(TextView) findViewById(R.id.VlblClimateActionsFoods);
         chkClimateActionsFoods=(CheckBox) findViewById(R.id.chkClimateActionsFoods);
         secClimateActionsHome=(LinearLayout)findViewById(R.id.secClimateActionsHome);
         lineClimateActionsHome=(View)findViewById(R.id.lineClimateActionsHome);
         VlblClimateActionsHome=(TextView) findViewById(R.id.VlblClimateActionsHome);
         chkClimateActionsHome=(CheckBox) findViewById(R.id.chkClimateActionsHome);
         secClimateActionsOther=(LinearLayout)findViewById(R.id.secClimateActionsOther);
         lineClimateActionsOther=(View)findViewById(R.id.lineClimateActionsOther);
         VlblClimateActionsOther=(TextView) findViewById(R.id.VlblClimateActionsOther);
         chkClimateActionsOther=(CheckBox) findViewById(R.id.chkClimateActionsOther);
         chkClimateActionsOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secClimateActionsOtherSp.setVisibility(View.VISIBLE);
                    lineClimateActionsOtherSp.setVisibility(View.VISIBLE);
                 }
                 else {
                    secClimateActionsOtherSp.setVisibility(View.GONE);
                    lineClimateActionsOtherSp.setVisibility(View.GONE);
                    txtClimateActionsOtherSp.setText("");
                 }
                 }
         });
         secClimateActionsOtherSp=(LinearLayout)findViewById(R.id.secClimateActionsOtherSp);
         lineClimateActionsOtherSp=(View)findViewById(R.id.lineClimateActionsOtherSp);
         VlblClimateActionsOtherSp=(TextView) findViewById(R.id.VlblClimateActionsOtherSp);
         txtClimateActionsOtherSp=(EditText) findViewById(R.id.txtClimateActionsOtherSp);
         secClimateRenewableEnergy=(LinearLayout)findViewById(R.id.secClimateRenewableEnergy);
         lineClimateRenewableEnergy=(View)findViewById(R.id.lineClimateRenewableEnergy);
         VlblClimateRenewableEnergy = (TextView) findViewById(R.id.VlblClimateRenewableEnergy);
         rdogrpClimateRenewableEnergy = (RadioGroup) findViewById(R.id.rdogrpClimateRenewableEnergy);
         rdoClimateRenewableEnergy1 = (RadioButton) findViewById(R.id.rdoClimateRenewableEnergy1);
         rdoClimateRenewableEnergy2 = (RadioButton) findViewById(R.id.rdoClimateRenewableEnergy2);
         rdoClimateRenewableEnergy3 = (RadioButton) findViewById(R.id.rdoClimateRenewableEnergy3);
         seclblQ18=(LinearLayout)findViewById(R.id.seclblQ18);
         linelblQ18=(View)findViewById(R.id.linelblQ18);
         secClimateObstaclesCost=(LinearLayout)findViewById(R.id.secClimateObstaclesCost);
         lineClimateObstaclesCost=(View)findViewById(R.id.lineClimateObstaclesCost);
         VlblClimateObstaclesCost=(TextView) findViewById(R.id.VlblClimateObstaclesCost);
         chkClimateObstaclesCost=(CheckBox) findViewById(R.id.chkClimateObstaclesCost);
        secClimateObstaclesLackInfo=(LinearLayout)findViewById(R.id.secClimateObstaclesLackInfo);
        lineClimateObstaclesLackInfo=(View)findViewById(R.id.lineClimateObstaclesLackInfo);
        VlblClimateObstaclesLackInfo=(TextView) findViewById(R.id.VlblClimateObstaclesLackInfo);
        chkClimateObstaclesLackInfo=(CheckBox) findViewById(R.id.chkClimateObstaclesLackInfo);
         secClimateObstaclesAccess=(LinearLayout)findViewById(R.id.secClimateObstaclesAccess);
         lineClimateObstaclesAccess=(View)findViewById(R.id.lineClimateObstaclesAccess);
         VlblClimateObstaclesAccess=(TextView) findViewById(R.id.VlblClimateObstaclesAccess);
         chkClimateObstaclesAccess=(CheckBox) findViewById(R.id.chkClimateObstaclesAccess);
         secClimateObstaclesHabit=(LinearLayout)findViewById(R.id.secClimateObstaclesHabit);
         lineClimateObstaclesHabit=(View)findViewById(R.id.lineClimateObstaclesHabit);
         VlblClimateObstaclesHabit=(TextView) findViewById(R.id.VlblClimateObstaclesHabit);
         chkClimateObstaclesHabit=(CheckBox) findViewById(R.id.chkClimateObstaclesHabit);
         secClimateObstaclesOther=(LinearLayout)findViewById(R.id.secClimateObstaclesOther);
         lineClimateObstaclesOther=(View)findViewById(R.id.lineClimateObstaclesOther);
         VlblClimateObstaclesOther=(TextView) findViewById(R.id.VlblClimateObstaclesOther);
         chkClimateObstaclesOther=(CheckBox) findViewById(R.id.chkClimateObstaclesOther);
         chkClimateObstaclesOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secClimateObstaclesOtherSp.setVisibility(View.VISIBLE);
                    lineClimateObstaclesOtherSp.setVisibility(View.VISIBLE);
                 }
                 else {
                    secClimateObstaclesOtherSp.setVisibility(View.GONE);
                    lineClimateObstaclesOtherSp.setVisibility(View.GONE);
                    txtClimateObstaclesOtherSp.setText("");
                 }
                 }
         });
         secClimateObstaclesOtherSp=(LinearLayout)findViewById(R.id.secClimateObstaclesOtherSp);
         lineClimateObstaclesOtherSp=(View)findViewById(R.id.lineClimateObstaclesOtherSp);
         VlblClimateObstaclesOtherSp=(TextView) findViewById(R.id.VlblClimateObstaclesOtherSp);
         txtClimateObstaclesOtherSp=(EditText) findViewById(R.id.txtClimateObstaclesOtherSp);
         secClimateNote=(LinearLayout)findViewById(R.id.secClimateNote);
         lineClimateNote=(View)findViewById(R.id.lineClimateNote);
         VlblClimateNote=(TextView) findViewById(R.id.VlblClimateNote);
         txtClimateNote=(EditText) findViewById(R.id.txtClimateNote);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Climate_Change.this, e.getMessage());
         return;
     }
 }

 private void DataSave()
 {
   try
     {
        if(!rdoClimateVStatus2.isChecked()) {
           String ValidationMSG = ValidationCheck();
           if (ValidationMSG.length() > 0) {
              Connection.MessageBox(Climate_Change.this, ValidationMSG);
              return;
           }
        }
 
         String SQL = "";
         RadioButton rb;

         tmpClimate_Change_DataModel objSave = new tmpClimate_Change_DataModel();
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setClimateSL(txtClimateSL.getText().toString());
         objSave.setClimateVDate(dtpClimateVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpClimateVDate.getText().toString()) : dtpClimateVDate.getText().toString());
         String[] d_rdogrpClimateVStatus = new String[] {"1","2","3","9"};
         objSave.setClimateVStatus("");
         for (int i = 0; i < rdogrpClimateVStatus.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpClimateVStatus.getChildAt(i);
             if (rb.isChecked()) objSave.setClimateVStatus(d_rdogrpClimateVStatus[i]);
         }

         objSave.setClimateVStatusOth(txtClimateVStatusOth.getText().toString());
         objSave.setRnd(txtRnd.getText().toString());
         String[] d_rdogrpClimateChangesNoticed = new String[] {"0","1"};
         objSave.setClimateChangesNoticed("");
         for (int i = 0; i < rdogrpClimateChangesNoticed.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpClimateChangesNoticed.getChildAt(i);
             if (rb.isChecked()) objSave.setClimateChangesNoticed(d_rdogrpClimateChangesNoticed[i]);
         }

         objSave.setClimateChangesTemp((chkClimateChangesTemp.isChecked() ? "1" : (secClimateChangesTemp.isShown() ? "2" : "")));
         objSave.setClimateChangesSeason((chkClimateChangesSeason.isChecked() ? "1" : (secClimateChangesSeason.isShown() ? "2" : "")));
         objSave.setClimateChangesWeather((chkClimateChangesWeather.isChecked() ? "1" : (secClimateChangesWeather.isShown() ? "2" : "")));
         objSave.setClimateChangesFlora((chkClimateChangesFlora.isChecked() ? "1" : (secClimateChangesFlora.isShown() ? "2" : "")));
         objSave.setClimateChangesOther((chkClimateChangesOther.isChecked() ? "1" : (secClimateChangesOther.isShown() ? "2" : "")));
         objSave.setClimateChangesOtherSp(txtClimateChangesOtherSp.getText().toString());
         objSave.setClimateAffectDailyLife(spnClimateAffectDailyLife.getSelectedItemPosition() == 0 ? "" : spnClimateAffectDailyLife.getSelectedItem().toString().split("-")[0]);
         String[] d_rdogrpClimateFlood = new String[] {"0","1"};
         objSave.setClimateFlood("");
         for (int i = 0; i < rdogrpClimateFlood.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpClimateFlood.getChildAt(i);
             if (rb.isChecked()) objSave.setClimateFlood(d_rdogrpClimateFlood[i]);
         }

         objSave.setClimateFloodHouse((chkClimateFloodHouse.isChecked() ? "1" : (secClimateFloodHouse.isShown() ? "2" : "")));
         objSave.setClimateFloodWall((chkClimateFloodWall.isChecked() ? "1" : (secClimateFloodWall.isShown() ? "2" : "")));
         objSave.setClimateFloodMember((chkClimateFloodMember.isChecked() ? "1" : (secClimateFloodMember.isShown() ? "2" : "")));
         objSave.setClimateFloodLivestock((chkClimateFloodLivestock.isChecked() ? "1" : (secClimateFloodLivestock.isShown() ? "2" : "")));
         objSave.setClimateFloodJobLess((chkClimateFloodJobLess.isChecked() ? "1" : (secClimateFloodJobLess.isShown() ? "2" : "")));
         objSave.setClimateFloodEnergy((chkClimateFloodEnergy.isChecked() ? "1" : (secClimateFloodEnergy.isShown() ? "2" : "")));
         objSave.setClimateFloodToilets((chkClimateFloodToilets.isChecked() ? "1" : (secClimateFloodToilets.isShown() ? "2" : "")));
         objSave.setClimateFloodHealth((chkClimateFloodHealth.isChecked() ? "1" : (secClimateFloodHealth.isShown() ? "2" : "")));
         objSave.setClimateFloodMigration((chkClimateFloodMigration.isChecked() ? "1" : (secClimateFloodMigration.isShown() ? "2" : "")));
         objSave.setClimateFloodOther((chkClimateFloodOther.isChecked() ? "1" : (secClimateFloodOther.isShown() ? "2" : "")));
         objSave.setClimateFloodOtherSp(txtClimateFloodOtherSp.getText().toString());
         String[] d_rdogrpClimateHighHeat = new String[] {"0","1"};
         objSave.setClimateHighHeat("");
         for (int i = 0; i < rdogrpClimateHighHeat.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpClimateHighHeat.getChildAt(i);
             if (rb.isChecked()) objSave.setClimateHighHeat(d_rdogrpClimateHighHeat[i]);
         }

         objSave.setClimateHighHeatMemberDth((chkClimateHighHeatMemberDth.isChecked() ? "1" : (secClimateHighHeatMemberDth.isShown() ? "2" : "")));
         objSave.setClimateHighHeatHealth((chkClimateHighHeatHealth.isChecked() ? "1" : (secClimateHighHeatHealth.isShown() ? "2" : "")));
         objSave.setClimateHighHeatMemberIll((chkClimateHighHeatMemberIll.isChecked() ? "1" : (secClimateHighHeatMemberIll.isShown() ? "2" : "")));
         objSave.setClimateHighHeatPetDth((chkClimateHighHeatPetDth.isChecked() ? "1" : (secClimateHighHeatPetDth.isShown() ? "2" : "")));
         objSave.setClimateHighHeatOther((chkClimateHighHeatOther.isChecked() ? "1" : (secClimateHighHeatOther.isShown() ? "2" : "")));
         objSave.setClimateHighHeatOtherSp(txtClimateHighHeatOtherSp.getText().toString());
         objSave.setClimateHealthProblemSleep((chkClimateHealthProblemSleep.isChecked() ? "1" : (secClimateHealthProblemSleep.isShown() ? "2" : "")));
         objSave.setClimateHealthProblemDizzy((chkClimateHealthProblemDizzy.isChecked() ? "1" : (secClimateHealthProblemDizzy.isShown() ? "2" : "")));
         objSave.setClimateHealthProblemLowBlood((chkClimateHealthProblemLowBlood.isChecked() ? "1" : (secClimateHealthProblemLowBlood.isShown() ? "2" : "")));
         objSave.setClimateHealthProblemHighBlood((chkClimateHealthProblemHighBlood.isChecked() ? "1" : (secClimateHealthProblemHighBlood.isShown() ? "2" : "")));
         objSave.setClimateHealthProblemStroke((chkClimateHealthProblemStroke.isChecked() ? "1" : (secClimateHealthProblemStroke.isShown() ? "2" : "")));
         objSave.setClimateHealthProblemFever((chkClimateHealthProblemFever.isChecked() ? "1" : (secClimateHealthProblemFever.isShown() ? "2" : "")));
         objSave.setClimateHealthProblemOther((chkClimateHealthProblemOther.isChecked() ? "1" : (secClimateHealthProblemOther.isShown() ? "2" : "")));
         objSave.setClimateHealthProblemOtherSp(txtClimateHealthProblemOtherSp.getText().toString());
         objSave.setClimateHealthProblemPeriod(spnClimateHealthProblemPeriod.getSelectedItemPosition() == 0 ? "" : spnClimateHealthProblemPeriod.getSelectedItem().toString().split("-")[0]);
         objSave.setClimateIllnessMalaria((chkClimateIllnessMalaria.isChecked() ? "1" : (secClimateIllnessMalaria.isShown() ? "2" : "")));
         objSave.setClimateIllnessPneumonia((chkClimateIllnessPneumonia.isChecked() ? "1" : (secClimateIllnessPneumonia.isShown() ? "2" : "")));
         objSave.setClimateIllnessMeasles((chkClimateIllnessMeasles.isChecked() ? "1" : (secClimateIllnessMeasles.isShown() ? "2" : "")));
         objSave.setClimateIllnessPertussis((chkClimateIllnessPertussis.isChecked() ? "1" : (secClimateIllnessPertussis.isShown() ? "2" : "")));
         objSave.setClimateIllnessDiarrhea((chkClimateIllnessDiarrhea.isChecked() ? "1" : (secClimateIllnessDiarrhea.isShown() ? "2" : "")));
         objSave.setClimateIllnessFood((chkClimateIllnessFood.isChecked() ? "1" : (secClimateIllnessFood.isShown() ? "2" : "")));
         objSave.setClimateIllnessTyphoid((chkClimateIllnessTyphoid.isChecked() ? "1" : (secClimateIllnessTyphoid.isShown() ? "2" : "")));
         objSave.setClimateIllnessCough((chkClimateIllnessCough.isChecked() ? "1" : (secClimateIllnessCough.isShown() ? "2" : "")));
         objSave.setClimateIllnessCholera((chkClimateIllnessCholera.isChecked() ? "1" : (secClimateIllnessCholera.isShown() ? "2" : "")));
         objSave.setClimateIllnessOther((chkClimateIllnessOther.isChecked() ? "1" : (secClimateIllnessOther.isShown() ? "2" : "")));
         objSave.setClimateIllnessOtherSp(txtClimateIllnessOtherSp.getText().toString());
         objSave.setClimateIllnessPeriod(spnClimateIllnessPeriod.getSelectedItemPosition() == 0 ? "" : spnClimateIllnessPeriod.getSelectedItem().toString().split("-")[0]);
         String[] d_rdogrpClimateHeard = new String[] {"0","1"};
         objSave.setClimateHeard("");
         for (int i = 0; i < rdogrpClimateHeard.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpClimateHeard.getChildAt(i);
             if (rb.isChecked()) objSave.setClimateHeard(d_rdogrpClimateHeard[i]);
         }

         objSave.setClimateInfoMedia((chkClimateInfoMedia.isChecked() ? "1" : (secClimateInfoMedia.isShown() ? "2" : "")));
         objSave.setClimateInfoInternet((chkClimateInfoInternet.isChecked() ? "1" : (secClimateInfoInternet.isShown() ? "2" : "")));
         objSave.setClimateInfoEducation((chkClimateInfoEducation.isChecked() ? "1" : (secClimateInfoEducation.isShown() ? "2" : "")));
         objSave.setClimateInfoFnF((chkClimateInfoFnF.isChecked() ? "1" : (secClimateInfoFnF.isShown() ? "2" : "")));
         objSave.setClimateInfoOther((chkClimateInfoOther.isChecked() ? "1" : (secClimateInfoOther.isShown() ? "2" : "")));
         objSave.setClimateInfoOtherSp(txtClimateInfoOtherSp.getText().toString());
         objSave.setClimateKnRate(spnClimateKnRate.getSelectedItemPosition() == 0 ? "" : spnClimateKnRate.getSelectedItem().toString().split("-")[0]);
         String[] d_rdogrpClimatethreat = new String[] {"0","1","8"};
         objSave.setClimatethreat("");
         for (int i = 0; i < rdogrpClimatethreat.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpClimatethreat.getChildAt(i);
             if (rb.isChecked()) objSave.setClimatethreat(d_rdogrpClimatethreat[i]);
         }

         objSave.setClimateWorried(spnClimateWorried.getSelectedItemPosition() == 0 ? "" : spnClimateWorried.getSelectedItem().toString().split("-")[0]);
         objSave.setClimateConcernTemp((chkClimateConcernTemp.isChecked() ? "1" : (secClimateConcernTemp.isShown() ? "2" : "")));
         objSave.setClimateConcernEvent((chkClimateConcernEvent.isChecked() ? "1" : (secClimateConcernEvent.isShown() ? "2" : "")));
         objSave.setClimateConcernPolarIce((chkClimateConcernPolarIce.isChecked() ? "1" : (secClimateConcernPolarIce.isShown() ? "2" : "")));
         objSave.setClimateConcernSeaLevel((chkClimateConcernSeaLevel.isChecked() ? "1" : (secClimateConcernSeaLevel.isShown() ? "2" : "")));
         objSave.setClimateConcernBiodiversity((chkClimateConcernBiodiversity.isChecked() ? "1" : (secClimateConcernBiodiversity.isShown() ? "2" : "")));
         objSave.setClimateConcernDrying((chkClimateConcernDrying.isChecked() ? "1" : (secClimateConcernDrying.isShown() ? "2" : "")));
         objSave.setClimateConcernRising((chkClimateConcernRising.isChecked() ? "1" : (secClimateConcernRising.isShown() ? "2" : "")));
         objSave.setClimateConcernDrought((chkClimateConcernDrought.isChecked() ? "1" : (secClimateConcernDrought.isShown() ? "2" : "")));
         objSave.setClimateConcernDesert((chkClimateConcernDesert.isChecked() ? "1" : (secClimateConcernDesert.isShown() ? "2" : "")));
         objSave.setClimateConcernErosin((chkClimateConcernErosin.isChecked() ? "1" : (secClimateConcernErosin.isShown() ? "2" : "")));
         objSave.setClimateConcernOther((chkClimateConcernOther.isChecked() ? "1" : (secClimateConcernOther.isShown() ? "2" : "")));
         objSave.setClimateConcernOtherSp(txtClimateConcernOtherSp.getText().toString());
         String[] d_rdogrpClimateComDoing = new String[] {"0","1","8"};
         objSave.setClimateComDoing("");
         for (int i = 0; i < rdogrpClimateComDoing.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpClimateComDoing.getChildAt(i);
             if (rb.isChecked()) objSave.setClimateComDoing(d_rdogrpClimateComDoing[i]);
         }

         String[] d_rdogrpClimateReduceImpact = new String[] {"0","1"};
         objSave.setClimateReduceImpact("");
         for (int i = 0; i < rdogrpClimateReduceImpact.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpClimateReduceImpact.getChildAt(i);
             if (rb.isChecked()) objSave.setClimateReduceImpact(d_rdogrpClimateReduceImpact[i]);
         }

         objSave.setClimateActionsPlastics((chkClimateActionsPlastics.isChecked() ? "1" : (secClimateActionsPlastics.isShown() ? "2" : "")));
         objSave.setClimateActionsTransport((chkClimateActionsTransport.isChecked() ? "1" : (secClimateActionsTransport.isShown() ? "2" : "")));
         objSave.setClimateActionsFoods((chkClimateActionsFoods.isChecked() ? "1" : (secClimateActionsFoods.isShown() ? "2" : "")));
         objSave.setClimateActionsHome((chkClimateActionsHome.isChecked() ? "1" : (secClimateActionsHome.isShown() ? "2" : "")));
         objSave.setClimateActionsOther((chkClimateActionsOther.isChecked() ? "1" : (secClimateActionsOther.isShown() ? "2" : "")));
         objSave.setClimateActionsOtherSp(txtClimateActionsOtherSp.getText().toString());
         String[] d_rdogrpClimateRenewableEnergy = new String[] {"1","2","3"};
         objSave.setClimateRenewableEnergy("");
         for (int i = 0; i < rdogrpClimateRenewableEnergy.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpClimateRenewableEnergy.getChildAt(i);
             if (rb.isChecked()) objSave.setClimateRenewableEnergy(d_rdogrpClimateRenewableEnergy[i]);
         }

         objSave.setClimateObstaclesCost((chkClimateObstaclesCost.isChecked() ? "1" : (secClimateObstaclesCost.isShown() ? "2" : "")));
        objSave.setClimateObstaclesLackInfo((chkClimateObstaclesLackInfo.isChecked() ? "1" : (secClimateObstaclesLackInfo.isShown() ? "2" : "")));
         objSave.setClimateObstaclesAccess((chkClimateObstaclesAccess.isChecked() ? "1" : (secClimateObstaclesAccess.isShown() ? "2" : "")));
         objSave.setClimateObstaclesHabit((chkClimateObstaclesHabit.isChecked() ? "1" : (secClimateObstaclesHabit.isShown() ? "2" : "")));
         objSave.setClimateObstaclesOther((chkClimateObstaclesOther.isChecked() ? "1" : (secClimateObstaclesOther.isShown() ? "2" : "")));
         objSave.setClimateObstaclesOtherSp(txtClimateObstaclesOtherSp.getText().toString());
         objSave.setClimateNote(txtClimateNote.getText().toString());
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

             Toast.makeText(Climate_Change.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
             finish();
         }
         else{
             Connection.MessageBox(Climate_Change.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Climate_Change.this, e.getMessage());
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
         if(txtClimateSL.getText().toString().length()==0 & secClimateSL.isShown())
           {
             ValidationMsg += "\nRequired field: Climate Change Serial No.";
             secClimateSL.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpClimateVDate.getText().toString());
         if(DV.length()!=0 & secClimateVDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Date of Interview.";
             secClimateVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoClimateVStatus1.isChecked() & !rdoClimateVStatus2.isChecked() & !rdoClimateVStatus3.isChecked() & !rdoClimateVStatus4.isChecked() & secClimateVStatus.isShown())
           {
             ValidationMsg += "\nRequired field: Visit Status.";
             secClimateVStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtClimateVStatusOth.getText().toString().length()==0 & secClimateVStatusOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Specify.";
             secClimateVStatusOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             ValidationMsg += "\nRequired field: Round No.";
             secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoClimateChangesNoticed1.isChecked() & !rdoClimateChangesNoticed2.isChecked() & secClimateChangesNoticed.isShown())
           {
             ValidationMsg += "\nQ1. Required field: Have you noticed any changes in your local environment in the last few years?.";
             secClimateChangesNoticed.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtClimateChangesOtherSp.getText().toString().length()==0 & secClimateChangesOtherSp.isShown())
           {
             ValidationMsg += "\nRequired field: Other (specify):.";
             secClimateChangesOtherSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnClimateAffectDailyLife.getSelectedItemPosition()==0  & secClimateAffectDailyLife.isShown())
           {
             ValidationMsg += "\nQ3. Required field: How have these changes affected your daily life?.";
             secClimateAffectDailyLife.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoClimateFlood1.isChecked() & !rdoClimateFlood2.isChecked() & secClimateFlood.isShown())
           {
             ValidationMsg += "\nQ4. Required field: Have you been affected by the floods within the last year?.";
             secClimateFlood.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtClimateFloodOtherSp.getText().toString().length()==0 & secClimateFloodOtherSp.isShown())
           {
             ValidationMsg += "\nRequired field: Other (specify):.";
             secClimateFloodOtherSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoClimateHighHeat1.isChecked() & !rdoClimateHighHeat2.isChecked() & secClimateHighHeat.isShown())
           {
             ValidationMsg += "\nQ6. Required field: Were you affected by an unusually high heat in the last year?.";
             secClimateHighHeat.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtClimateHighHeatOtherSp.getText().toString().length()==0 & secClimateHighHeatOtherSp.isShown())
           {
             ValidationMsg += "\nRequired field: Other (specify):.";
             secClimateHighHeatOtherSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtClimateHealthProblemOtherSp.getText().toString().length()==0 & secClimateHealthProblemOtherSp.isShown())
           {
             ValidationMsg += "\nRequired field: Other (specify):.";
             secClimateHealthProblemOtherSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnClimateHealthProblemPeriod.getSelectedItemPosition()==0  & secClimateHealthProblemPeriod.isShown())
           {
             ValidationMsg += "\nQ7c. Required field: What is the period of this disease?.";
             secClimateHealthProblemPeriod.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtClimateIllnessOtherSp.getText().toString().length()==0 & secClimateIllnessOtherSp.isShown())
           {
             ValidationMsg += "\nRequired field: Other (specify):.";
             secClimateIllnessOtherSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnClimateIllnessPeriod.getSelectedItemPosition()==0  & secClimateIllnessPeriod.isShown())
           {
             ValidationMsg += "\nQ7d. Required field: What is the period of this disease?.";
             secClimateIllnessPeriod.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoClimateHeard1.isChecked() & !rdoClimateHeard2.isChecked() & secClimateHeard.isShown())
           {
             ValidationMsg += "\nQ8. Required field: Have you heard of climate change?.";
             secClimateHeard.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtClimateInfoOtherSp.getText().toString().length()==0 & secClimateInfoOtherSp.isShown())
           {
             ValidationMsg += "\nRequired field: Other (specify):.";
             secClimateInfoOtherSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnClimateKnRate.getSelectedItemPosition()==0  & secClimateKnRate.isShown())
           {
             ValidationMsg += "\nQ10. Required field: On a scale of 1 to 5, how would you rate your knowledge of climate change?.";
             secClimateKnRate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoClimatethreat1.isChecked() & !rdoClimatethreat2.isChecked() & !rdoClimatethreat3.isChecked() & secClimatethreat.isShown())
           {
             ValidationMsg += "\nQ11. Required field: Do you think climate change poses a serious threat to the planet?.";
             secClimatethreat.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnClimateWorried.getSelectedItemPosition()==0  & secClimateWorried.isShown())
           {
             ValidationMsg += "\nQ12. Required field: How worried are you about climate change?.";
             secClimateWorried.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtClimateConcernOtherSp.getText().toString().length()==0 & secClimateConcernOtherSp.isShown())
           {
             ValidationMsg += "\nRequired field: Other (specify):.";
             secClimateConcernOtherSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoClimateComDoing1.isChecked() & !rdoClimateComDoing2.isChecked() & !rdoClimateComDoing3.isChecked() & secClimateComDoing.isShown())
           {
             ValidationMsg += "\nQ14. Required field: Do you think your community is doing enough to address climate change?.";
             secClimateComDoing.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoClimateReduceImpact1.isChecked() & !rdoClimateReduceImpact2.isChecked() & secClimateReduceImpact.isShown())
           {
             ValidationMsg += "\nQ15. Required field: Have you changed your consumption habits to reduce your impact on the environment?.";
             secClimateReduceImpact.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtClimateActionsOtherSp.getText().toString().length()==0 & secClimateActionsOtherSp.isShown())
           {
             ValidationMsg += "\nRequired field: Other (specify):.";
             secClimateActionsOtherSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoClimateRenewableEnergy1.isChecked() & !rdoClimateRenewableEnergy2.isChecked() & !rdoClimateRenewableEnergy3.isChecked() & secClimateRenewableEnergy.isShown())
           {
             ValidationMsg += "\nQ17. Required field: What is your perception of renewable energy source (e.g. Solar and wind)?.";
             secClimateRenewableEnergy.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtClimateObstaclesOtherSp.getText().toString().length()==0 & secClimateObstaclesOtherSp.isShown())
           {
             ValidationMsg += "\nE. Required field: Other (specify):.";
             secClimateObstaclesOtherSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
//         if(txtClimateNote.getText().toString().length()==0 & secClimateNote.isShown())
//           {
//             ValidationMsg += "\nRequired field: Note.";
//             secClimateNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secHHID.setBackgroundColor(Color.WHITE);
             secClimateSL.setBackgroundColor(Color.WHITE);
             secClimateVDate.setBackgroundColor(Color.WHITE);
             secClimateVStatus.setBackgroundColor(Color.WHITE);
             secClimateVStatusOth.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
             secClimateChangesNoticed.setBackgroundColor(Color.WHITE);
             secClimateChangesOtherSp.setBackgroundColor(Color.WHITE);
             secClimateAffectDailyLife.setBackgroundColor(Color.WHITE);
             secClimateFlood.setBackgroundColor(Color.WHITE);
             secClimateFloodOtherSp.setBackgroundColor(Color.WHITE);
             secClimateHighHeat.setBackgroundColor(Color.WHITE);
             secClimateHighHeatOtherSp.setBackgroundColor(Color.WHITE);
             secClimateHealthProblemOtherSp.setBackgroundColor(Color.WHITE);
             secClimateHealthProblemPeriod.setBackgroundColor(Color.WHITE);
             secClimateIllnessOtherSp.setBackgroundColor(Color.WHITE);
             secClimateIllnessPeriod.setBackgroundColor(Color.WHITE);
             secClimateHeard.setBackgroundColor(Color.WHITE);
             secClimateInfoOtherSp.setBackgroundColor(Color.WHITE);
             secClimateKnRate.setBackgroundColor(Color.WHITE);
             secClimatethreat.setBackgroundColor(Color.WHITE);
             secClimateWorried.setBackgroundColor(Color.WHITE);
             secClimateConcernOtherSp.setBackgroundColor(Color.WHITE);
             secClimateComDoing.setBackgroundColor(Color.WHITE);
             secClimateReduceImpact.setBackgroundColor(Color.WHITE);
             secClimateActionsOtherSp.setBackgroundColor(Color.WHITE);
             secClimateRenewableEnergy.setBackgroundColor(Color.WHITE);
             secClimateObstaclesOtherSp.setBackgroundColor(Color.WHITE);
             secClimateNote.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String HHID, String ClimateSL)
     {
       try
        {     
           RadioButton rb;
           tmpClimate_Change_DataModel d = new tmpClimate_Change_DataModel();
           String SQL = "Select * from "+ TableName +"  Where HHID='"+ HHID +"' and ClimateSL='"+ ClimateSL +"'";
           List<tmpClimate_Change_DataModel> data = d.SelectAll(this, SQL);
           for(tmpClimate_Change_DataModel item : data){
             txtHHID.setText(item.getHHID());
             txtClimateSL.setText(item.getClimateSL());
             dtpClimateVDate.setText(item.getClimateVDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getClimateVDate()));
             String[] d_rdogrpClimateVStatus = new String[] {"1","2","3","9"};
             for (int i = 0; i < d_rdogrpClimateVStatus.length; i++)
             {
                 if (String.valueOf(item.getClimateVStatus()).equals(String.valueOf(d_rdogrpClimateVStatus[i])))
                 {
                     rb = (RadioButton)rdogrpClimateVStatus.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtClimateVStatusOth.setText(item.getClimateVStatusOth());
             txtRnd.setText(item.getRnd());
             String[] d_rdogrpClimateChangesNoticed = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpClimateChangesNoticed.length; i++)
             {
                 if (String.valueOf(item.getClimateChangesNoticed()).equals(String.valueOf(d_rdogrpClimateChangesNoticed[i])))
                 {
                     rb = (RadioButton)rdogrpClimateChangesNoticed.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getClimateChangesTemp()).equals("1"))
             {
                chkClimateChangesTemp.setChecked(true);
             }
             else if(String.valueOf(item.getClimateChangesTemp()).equals("2"))
             {
                chkClimateChangesTemp.setChecked(false);
             }
             if(String.valueOf(item.getClimateChangesSeason()).equals("1"))
             {
                chkClimateChangesSeason.setChecked(true);
             }
             else if(String.valueOf(item.getClimateChangesSeason()).equals("2"))
             {
                chkClimateChangesSeason.setChecked(false);
             }
             if(String.valueOf(item.getClimateChangesWeather()).equals("1"))
             {
                chkClimateChangesWeather.setChecked(true);
             }
             else if(String.valueOf(item.getClimateChangesWeather()).equals("2"))
             {
                chkClimateChangesWeather.setChecked(false);
             }
             if(String.valueOf(item.getClimateChangesFlora()).equals("1"))
             {
                chkClimateChangesFlora.setChecked(true);
             }
             else if(String.valueOf(item.getClimateChangesFlora()).equals("2"))
             {
                chkClimateChangesFlora.setChecked(false);
             }
             if(String.valueOf(item.getClimateChangesOther()).equals("1"))
             {
                chkClimateChangesOther.setChecked(true);
             }
             else if(String.valueOf(item.getClimateChangesOther()).equals("2"))
             {
                chkClimateChangesOther.setChecked(false);
             }
             txtClimateChangesOtherSp.setText(item.getClimateChangesOtherSp());
             spnClimateAffectDailyLife.setSelection(Global.SpinnerItemPositionAnyLength(spnClimateAffectDailyLife, String.valueOf(item.getClimateAffectDailyLife())));
             String[] d_rdogrpClimateFlood = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpClimateFlood.length; i++)
             {
                 if (String.valueOf(item.getClimateFlood()).equals(String.valueOf(d_rdogrpClimateFlood[i])))
                 {
                     rb = (RadioButton)rdogrpClimateFlood.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getClimateFloodHouse()).equals("1"))
             {
                chkClimateFloodHouse.setChecked(true);
             }
             else if(String.valueOf(item.getClimateFloodHouse()).equals("2"))
             {
                chkClimateFloodHouse.setChecked(false);
             }
             if(String.valueOf(item.getClimateFloodWall()).equals("1"))
             {
                chkClimateFloodWall.setChecked(true);
             }
             else if(String.valueOf(item.getClimateFloodWall()).equals("2"))
             {
                chkClimateFloodWall.setChecked(false);
             }
             if(String.valueOf(item.getClimateFloodMember()).equals("1"))
             {
                chkClimateFloodMember.setChecked(true);
             }
             else if(String.valueOf(item.getClimateFloodMember()).equals("2"))
             {
                chkClimateFloodMember.setChecked(false);
             }
             if(String.valueOf(item.getClimateFloodLivestock()).equals("1"))
             {
                chkClimateFloodLivestock.setChecked(true);
             }
             else if(String.valueOf(item.getClimateFloodLivestock()).equals("2"))
             {
                chkClimateFloodLivestock.setChecked(false);
             }
             if(String.valueOf(item.getClimateFloodJobLess()).equals("1"))
             {
                chkClimateFloodJobLess.setChecked(true);
             }
             else if(String.valueOf(item.getClimateFloodJobLess()).equals("2"))
             {
                chkClimateFloodJobLess.setChecked(false);
             }
             if(String.valueOf(item.getClimateFloodEnergy()).equals("1"))
             {
                chkClimateFloodEnergy.setChecked(true);
             }
             else if(String.valueOf(item.getClimateFloodEnergy()).equals("2"))
             {
                chkClimateFloodEnergy.setChecked(false);
             }
             if(String.valueOf(item.getClimateFloodToilets()).equals("1"))
             {
                chkClimateFloodToilets.setChecked(true);
             }
             else if(String.valueOf(item.getClimateFloodToilets()).equals("2"))
             {
                chkClimateFloodToilets.setChecked(false);
             }
             if(String.valueOf(item.getClimateFloodHealth()).equals("1"))
             {
                chkClimateFloodHealth.setChecked(true);
             }
             else if(String.valueOf(item.getClimateFloodHealth()).equals("2"))
             {
                chkClimateFloodHealth.setChecked(false);
             }
             if(String.valueOf(item.getClimateFloodMigration()).equals("1"))
             {
                chkClimateFloodMigration.setChecked(true);
             }
             else if(String.valueOf(item.getClimateFloodMigration()).equals("2"))
             {
                chkClimateFloodMigration.setChecked(false);
             }
             if(String.valueOf(item.getClimateFloodOther()).equals("1"))
             {
                chkClimateFloodOther.setChecked(true);
             }
             else if(String.valueOf(item.getClimateFloodOther()).equals("2"))
             {
                chkClimateFloodOther.setChecked(false);
             }
             txtClimateFloodOtherSp.setText(item.getClimateFloodOtherSp());
             String[] d_rdogrpClimateHighHeat = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpClimateHighHeat.length; i++)
             {
                 if (String.valueOf(item.getClimateHighHeat()).equals(String.valueOf(d_rdogrpClimateHighHeat[i])))
                 {
                     rb = (RadioButton)rdogrpClimateHighHeat.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getClimateHighHeatMemberDth()).equals("1"))
             {
                chkClimateHighHeatMemberDth.setChecked(true);
             }
             else if(String.valueOf(item.getClimateHighHeatMemberDth()).equals("2"))
             {
                chkClimateHighHeatMemberDth.setChecked(false);
             }
             if(String.valueOf(item.getClimateHighHeatHealth()).equals("1"))
             {
                chkClimateHighHeatHealth.setChecked(true);
             }
             else if(String.valueOf(item.getClimateHighHeatHealth()).equals("2"))
             {
                chkClimateHighHeatHealth.setChecked(false);
             }
             if(String.valueOf(item.getClimateHighHeatMemberIll()).equals("1"))
             {
                chkClimateHighHeatMemberIll.setChecked(true);
             }
             else if(String.valueOf(item.getClimateHighHeatMemberIll()).equals("2"))
             {
                chkClimateHighHeatMemberIll.setChecked(false);
             }
             if(String.valueOf(item.getClimateHighHeatPetDth()).equals("1"))
             {
                chkClimateHighHeatPetDth.setChecked(true);
             }
             else if(String.valueOf(item.getClimateHighHeatPetDth()).equals("2"))
             {
                chkClimateHighHeatPetDth.setChecked(false);
             }
             if(String.valueOf(item.getClimateHighHeatOther()).equals("1"))
             {
                chkClimateHighHeatOther.setChecked(true);
             }
             else if(String.valueOf(item.getClimateHighHeatOther()).equals("2"))
             {
                chkClimateHighHeatOther.setChecked(false);
             }
             txtClimateHighHeatOtherSp.setText(item.getClimateHighHeatOtherSp());
             if(String.valueOf(item.getClimateHealthProblemSleep()).equals("1"))
             {
                chkClimateHealthProblemSleep.setChecked(true);
             }
             else if(String.valueOf(item.getClimateHealthProblemSleep()).equals("2"))
             {
                chkClimateHealthProblemSleep.setChecked(false);
             }
             if(String.valueOf(item.getClimateHealthProblemDizzy()).equals("1"))
             {
                chkClimateHealthProblemDizzy.setChecked(true);
             }
             else if(String.valueOf(item.getClimateHealthProblemDizzy()).equals("2"))
             {
                chkClimateHealthProblemDizzy.setChecked(false);
             }
             if(String.valueOf(item.getClimateHealthProblemLowBlood()).equals("1"))
             {
                chkClimateHealthProblemLowBlood.setChecked(true);
             }
             else if(String.valueOf(item.getClimateHealthProblemLowBlood()).equals("2"))
             {
                chkClimateHealthProblemLowBlood.setChecked(false);
             }
             if(String.valueOf(item.getClimateHealthProblemHighBlood()).equals("1"))
             {
                chkClimateHealthProblemHighBlood.setChecked(true);
             }
             else if(String.valueOf(item.getClimateHealthProblemHighBlood()).equals("2"))
             {
                chkClimateHealthProblemHighBlood.setChecked(false);
             }
             if(String.valueOf(item.getClimateHealthProblemStroke()).equals("1"))
             {
                chkClimateHealthProblemStroke.setChecked(true);
             }
             else if(String.valueOf(item.getClimateHealthProblemStroke()).equals("2"))
             {
                chkClimateHealthProblemStroke.setChecked(false);
             }
             if(String.valueOf(item.getClimateHealthProblemFever()).equals("1"))
             {
                chkClimateHealthProblemFever.setChecked(true);
             }
             else if(String.valueOf(item.getClimateHealthProblemFever()).equals("2"))
             {
                chkClimateHealthProblemFever.setChecked(false);
             }
             if(String.valueOf(item.getClimateHealthProblemOther()).equals("1"))
             {
                chkClimateHealthProblemOther.setChecked(true);
             }
             else if(String.valueOf(item.getClimateHealthProblemOther()).equals("2"))
             {
                chkClimateHealthProblemOther.setChecked(false);
             }
             txtClimateHealthProblemOtherSp.setText(item.getClimateHealthProblemOtherSp());
             spnClimateHealthProblemPeriod.setSelection(Global.SpinnerItemPositionAnyLength(spnClimateHealthProblemPeriod, String.valueOf(item.getClimateHealthProblemPeriod())));
             if(String.valueOf(item.getClimateIllnessMalaria()).equals("1"))
             {
                chkClimateIllnessMalaria.setChecked(true);
             }
             else if(String.valueOf(item.getClimateIllnessMalaria()).equals("2"))
             {
                chkClimateIllnessMalaria.setChecked(false);
             }
             if(String.valueOf(item.getClimateIllnessPneumonia()).equals("1"))
             {
                chkClimateIllnessPneumonia.setChecked(true);
             }
             else if(String.valueOf(item.getClimateIllnessPneumonia()).equals("2"))
             {
                chkClimateIllnessPneumonia.setChecked(false);
             }
             if(String.valueOf(item.getClimateIllnessMeasles()).equals("1"))
             {
                chkClimateIllnessMeasles.setChecked(true);
             }
             else if(String.valueOf(item.getClimateIllnessMeasles()).equals("2"))
             {
                chkClimateIllnessMeasles.setChecked(false);
             }
             if(String.valueOf(item.getClimateIllnessPertussis()).equals("1"))
             {
                chkClimateIllnessPertussis.setChecked(true);
             }
             else if(String.valueOf(item.getClimateIllnessPertussis()).equals("2"))
             {
                chkClimateIllnessPertussis.setChecked(false);
             }
             if(String.valueOf(item.getClimateIllnessDiarrhea()).equals("1"))
             {
                chkClimateIllnessDiarrhea.setChecked(true);
             }
             else if(String.valueOf(item.getClimateIllnessDiarrhea()).equals("2"))
             {
                chkClimateIllnessDiarrhea.setChecked(false);
             }
             if(String.valueOf(item.getClimateIllnessFood()).equals("1"))
             {
                chkClimateIllnessFood.setChecked(true);
             }
             else if(String.valueOf(item.getClimateIllnessFood()).equals("2"))
             {
                chkClimateIllnessFood.setChecked(false);
             }
             if(String.valueOf(item.getClimateIllnessTyphoid()).equals("1"))
             {
                chkClimateIllnessTyphoid.setChecked(true);
             }
             else if(String.valueOf(item.getClimateIllnessTyphoid()).equals("2"))
             {
                chkClimateIllnessTyphoid.setChecked(false);
             }
             if(String.valueOf(item.getClimateIllnessCough()).equals("1"))
             {
                chkClimateIllnessCough.setChecked(true);
             }
             else if(String.valueOf(item.getClimateIllnessCough()).equals("2"))
             {
                chkClimateIllnessCough.setChecked(false);
             }
             if(String.valueOf(item.getClimateIllnessCholera()).equals("1"))
             {
                chkClimateIllnessCholera.setChecked(true);
             }
             else if(String.valueOf(item.getClimateIllnessCholera()).equals("2"))
             {
                chkClimateIllnessCholera.setChecked(false);
             }
             if(String.valueOf(item.getClimateIllnessOther()).equals("1"))
             {
                chkClimateIllnessOther.setChecked(true);
             }
             else if(String.valueOf(item.getClimateIllnessOther()).equals("2"))
             {
                chkClimateIllnessOther.setChecked(false);
             }
             txtClimateIllnessOtherSp.setText(item.getClimateIllnessOtherSp());
             spnClimateIllnessPeriod.setSelection(Global.SpinnerItemPositionAnyLength(spnClimateIllnessPeriod, String.valueOf(item.getClimateIllnessPeriod())));
             String[] d_rdogrpClimateHeard = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpClimateHeard.length; i++)
             {
                 if (String.valueOf(item.getClimateHeard()).equals(String.valueOf(d_rdogrpClimateHeard[i])))
                 {
                     rb = (RadioButton)rdogrpClimateHeard.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getClimateInfoMedia()).equals("1"))
             {
                chkClimateInfoMedia.setChecked(true);
             }
             else if(String.valueOf(item.getClimateInfoMedia()).equals("2"))
             {
                chkClimateInfoMedia.setChecked(false);
             }
             if(String.valueOf(item.getClimateInfoInternet()).equals("1"))
             {
                chkClimateInfoInternet.setChecked(true);
             }
             else if(String.valueOf(item.getClimateInfoInternet()).equals("2"))
             {
                chkClimateInfoInternet.setChecked(false);
             }
             if(String.valueOf(item.getClimateInfoEducation()).equals("1"))
             {
                chkClimateInfoEducation.setChecked(true);
             }
             else if(String.valueOf(item.getClimateInfoEducation()).equals("2"))
             {
                chkClimateInfoEducation.setChecked(false);
             }
             if(String.valueOf(item.getClimateInfoFnF()).equals("1"))
             {
                chkClimateInfoFnF.setChecked(true);
             }
             else if(String.valueOf(item.getClimateInfoFnF()).equals("2"))
             {
                chkClimateInfoFnF.setChecked(false);
             }
             if(String.valueOf(item.getClimateInfoOther()).equals("1"))
             {
                chkClimateInfoOther.setChecked(true);
             }
             else if(String.valueOf(item.getClimateInfoOther()).equals("2"))
             {
                chkClimateInfoOther.setChecked(false);
             }
             txtClimateInfoOtherSp.setText(item.getClimateInfoOtherSp());
             spnClimateKnRate.setSelection(Global.SpinnerItemPositionAnyLength(spnClimateKnRate, String.valueOf(item.getClimateKnRate())));
             String[] d_rdogrpClimatethreat = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpClimatethreat.length; i++)
             {
                 if (String.valueOf(item.getClimatethreat()).equals(String.valueOf(d_rdogrpClimatethreat[i])))
                 {
                     rb = (RadioButton)rdogrpClimatethreat.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             spnClimateWorried.setSelection(Global.SpinnerItemPositionAnyLength(spnClimateWorried, String.valueOf(item.getClimateWorried())));
             if(String.valueOf(item.getClimateConcernTemp()).equals("1"))
             {
                chkClimateConcernTemp.setChecked(true);
             }
             else if(String.valueOf(item.getClimateConcernTemp()).equals("2"))
             {
                chkClimateConcernTemp.setChecked(false);
             }
             if(String.valueOf(item.getClimateConcernEvent()).equals("1"))
             {
                chkClimateConcernEvent.setChecked(true);
             }
             else if(String.valueOf(item.getClimateConcernEvent()).equals("2"))
             {
                chkClimateConcernEvent.setChecked(false);
             }
             if(String.valueOf(item.getClimateConcernPolarIce()).equals("1"))
             {
                chkClimateConcernPolarIce.setChecked(true);
             }
             else if(String.valueOf(item.getClimateConcernPolarIce()).equals("2"))
             {
                chkClimateConcernPolarIce.setChecked(false);
             }
             if(String.valueOf(item.getClimateConcernSeaLevel()).equals("1"))
             {
                chkClimateConcernSeaLevel.setChecked(true);
             }
             else if(String.valueOf(item.getClimateConcernSeaLevel()).equals("2"))
             {
                chkClimateConcernSeaLevel.setChecked(false);
             }
             if(String.valueOf(item.getClimateConcernBiodiversity()).equals("1"))
             {
                chkClimateConcernBiodiversity.setChecked(true);
             }
             else if(String.valueOf(item.getClimateConcernBiodiversity()).equals("2"))
             {
                chkClimateConcernBiodiversity.setChecked(false);
             }
             if(String.valueOf(item.getClimateConcernDrying()).equals("1"))
             {
                chkClimateConcernDrying.setChecked(true);
             }
             else if(String.valueOf(item.getClimateConcernDrying()).equals("2"))
             {
                chkClimateConcernDrying.setChecked(false);
             }
             if(String.valueOf(item.getClimateConcernRising()).equals("1"))
             {
                chkClimateConcernRising.setChecked(true);
             }
             else if(String.valueOf(item.getClimateConcernRising()).equals("2"))
             {
                chkClimateConcernRising.setChecked(false);
             }
             if(String.valueOf(item.getClimateConcernDrought()).equals("1"))
             {
                chkClimateConcernDrought.setChecked(true);
             }
             else if(String.valueOf(item.getClimateConcernDrought()).equals("2"))
             {
                chkClimateConcernDrought.setChecked(false);
             }
             if(String.valueOf(item.getClimateConcernDesert()).equals("1"))
             {
                chkClimateConcernDesert.setChecked(true);
             }
             else if(String.valueOf(item.getClimateConcernDesert()).equals("2"))
             {
                chkClimateConcernDesert.setChecked(false);
             }
             if(String.valueOf(item.getClimateConcernErosin()).equals("1"))
             {
                chkClimateConcernErosin.setChecked(true);
             }
             else if(String.valueOf(item.getClimateConcernErosin()).equals("2"))
             {
                chkClimateConcernErosin.setChecked(false);
             }
             if(String.valueOf(item.getClimateConcernOther()).equals("1"))
             {
                chkClimateConcernOther.setChecked(true);
             }
             else if(String.valueOf(item.getClimateConcernOther()).equals("2"))
             {
                chkClimateConcernOther.setChecked(false);
             }
             txtClimateConcernOtherSp.setText(item.getClimateConcernOtherSp());
             String[] d_rdogrpClimateComDoing = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpClimateComDoing.length; i++)
             {
                 if (String.valueOf(item.getClimateComDoing()).equals(String.valueOf(d_rdogrpClimateComDoing[i])))
                 {
                     rb = (RadioButton)rdogrpClimateComDoing.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpClimateReduceImpact = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpClimateReduceImpact.length; i++)
             {
                 if (String.valueOf(item.getClimateReduceImpact()).equals(String.valueOf(d_rdogrpClimateReduceImpact[i])))
                 {
                     rb = (RadioButton)rdogrpClimateReduceImpact.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getClimateActionsPlastics()).equals("1"))
             {
                chkClimateActionsPlastics.setChecked(true);
             }
             else if(String.valueOf(item.getClimateActionsPlastics()).equals("2"))
             {
                chkClimateActionsPlastics.setChecked(false);
             }
             if(String.valueOf(item.getClimateActionsTransport()).equals("1"))
             {
                chkClimateActionsTransport.setChecked(true);
             }
             else if(String.valueOf(item.getClimateActionsTransport()).equals("2"))
             {
                chkClimateActionsTransport.setChecked(false);
             }
             if(String.valueOf(item.getClimateActionsFoods()).equals("1"))
             {
                chkClimateActionsFoods.setChecked(true);
             }
             else if(String.valueOf(item.getClimateActionsFoods()).equals("2"))
             {
                chkClimateActionsFoods.setChecked(false);
             }
             if(String.valueOf(item.getClimateActionsHome()).equals("1"))
             {
                chkClimateActionsHome.setChecked(true);
             }
             else if(String.valueOf(item.getClimateActionsHome()).equals("2"))
             {
                chkClimateActionsHome.setChecked(false);
             }
             if(String.valueOf(item.getClimateActionsOther()).equals("1"))
             {
                chkClimateActionsOther.setChecked(true);
             }
             else if(String.valueOf(item.getClimateActionsOther()).equals("2"))
             {
                chkClimateActionsOther.setChecked(false);
             }
             txtClimateActionsOtherSp.setText(item.getClimateActionsOtherSp());
             String[] d_rdogrpClimateRenewableEnergy = new String[] {"1","2","3"};
             for (int i = 0; i < d_rdogrpClimateRenewableEnergy.length; i++)
             {
                 if (String.valueOf(item.getClimateRenewableEnergy()).equals(String.valueOf(d_rdogrpClimateRenewableEnergy[i])))
                 {
                     rb = (RadioButton)rdogrpClimateRenewableEnergy.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getClimateObstaclesCost()).equals("1"))
             {
                chkClimateObstaclesCost.setChecked(true);
             }
             else if(String.valueOf(item.getClimateObstaclesCost()).equals("2"))
             {
                chkClimateObstaclesCost.setChecked(false);
             }
              if(String.valueOf(item.getClimateObstaclesLackInfo()).equals("1"))
              {
                 chkClimateObstaclesLackInfo.setChecked(true);
              }
              else if(String.valueOf(item.getClimateObstaclesLackInfo()).equals("2"))
              {
                 chkClimateObstaclesLackInfo.setChecked(false);
              }
             if(String.valueOf(item.getClimateObstaclesAccess()).equals("1"))
             {
                chkClimateObstaclesAccess.setChecked(true);
             }
             else if(String.valueOf(item.getClimateObstaclesAccess()).equals("2"))
             {
                chkClimateObstaclesAccess.setChecked(false);
             }
             if(String.valueOf(item.getClimateObstaclesHabit()).equals("1"))
             {
                chkClimateObstaclesHabit.setChecked(true);
             }
             else if(String.valueOf(item.getClimateObstaclesHabit()).equals("2"))
             {
                chkClimateObstaclesHabit.setChecked(false);
             }
             if(String.valueOf(item.getClimateObstaclesOther()).equals("1"))
             {
                chkClimateObstaclesOther.setChecked(true);
             }
             else if(String.valueOf(item.getClimateObstaclesOther()).equals("2"))
             {
                chkClimateObstaclesOther.setChecked(false);
             }
             txtClimateObstaclesOtherSp.setText(item.getClimateObstaclesOtherSp());
             txtClimateNote.setText(item.getClimateNote());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Climate_Change.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpClimateVDate);
      if (VariableID.equals("btnClimateVDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpClimateVDate);
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

    private String NewClimateSl(String HHID)
    {
       String M = C.ReturnSingleValue("Select cast(ifnull(max(ClimateSL),0)+1 as varchar(2))ClimateSL from tmpClimate_Change where HHID='"+ HHID +"'");
       return M;
    }
 
 // turning off the GPS if its in on state. to avoid the battery drain.
 @Override
 protected void onDestroy() {
     // TODO Auto-generated method stub
     super.onDestroy();
 }
}