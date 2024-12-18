
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
 import android.graphics.Color;
 import android.view.WindowManager;
 import android.widget.AutoCompleteTextView;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.R;
 import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

 import forms_datamodel.Migration_DataModel;
 import Utility.*;
 import Common.*;
 import forms_datamodel.tmpMigration_DataModel;

 public class Surv_Migration extends AppCompatActivity {
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
    LinearLayout secMigrationID;
    View lineMigrationID;
    TextView VlblMigrationID;
    EditText txtMigrationID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secMigEvType;
    View lineMigEvType;

     LinearLayout secMigEvType1;
     View lineMigEvType1;
    TextView VlblMigEvType;
    TextView VlblMigEvType1;
    RadioGroup rdogrpMigEvType;
    RadioButton rdoMigEvType1;
    RadioButton rdoMigEvType2;
    RadioButton rdoMigEvType3;
    LinearLayout secMigDate;
    View lineMigDate;
    TextView VlblMigDate;
    EditText dtpMigDate;
    LinearLayout secMigLocType;
    View lineMigLocType;
    TextView VlblMigLocType;
    RadioGroup rdogrpMigLocType;
    RadioButton rdoMigLocType1;
    RadioButton rdoMigLocType2;
    RadioButton rdoMigLocType3;
    RadioButton rdoMigLocType4;
    LinearLayout secMigAdminL1;
    View lineMigAdminL1;
    TextView VlblMigAdminL1;
    AutoCompleteTextView txtMigAdminL1;

//     Spinner spnLayer1;
    LinearLayout secMigAdminL2;
    View lineMigAdminL2;
    TextView VlblMigAdminL2;
    AutoCompleteTextView txtMigAdminL2;

//     Spinner spnLayer2;
    LinearLayout secMigAdminL3;
    View lineMigAdminL3;
    TextView VlblMigAdminL3;
    AutoCompleteTextView txtMigAdminL3;
//     Spinner spnLayer3;
    LinearLayout secMigAdminL4;
    View lineMigAdminL4;
    TextView VlblMigAdminL4;
    AutoCompleteTextView txtMigAdminL4;
    LinearLayout secMigAdminLArea;
    View lineMigAdminLArea;
    TextView VlblMigAdminLArea;
    EditText txtMigAdminLArea;
    LinearLayout secMigCountry;
    View lineMigCountry;
    TextView VlblMigCountry;
    AutoCompleteTextView txtMigCountry;
     LinearLayout secMigBirthCountry;
     View lineMigBirthCountry;
     TextView VlblMigBirthCountry;
     Spinner spnMigBirthCountry;
     LinearLayout secMigBirthCountryOth;
     View lineMigBirthCountryOth;
     TextView VlblMigBirthCountryOth;
     AutoCompleteTextView txtMigBirthCountryOth;
     LinearLayout secMigOriginCountry;
     View lineMigOriginCountry;
     TextView VlblMigOriginCountry;
     Spinner spnMigOriginCountry;
     LinearLayout secMigOriginCountryOth;
     View lineMigOriginCountryOth;
     TextView VlblMigOriginCountryOth;
     AutoCompleteTextView txtMigOriginCountryOth;
     LinearLayout secMigWCRegionBurCity;
     View lineMigWCRegionBurCity;
     TextView VlblMigWCRegionBurCity;
     Spinner spnMigWCRegionBurCity;
     LinearLayout secMigBurCity;
     View lineMigBurCity;
     TextView VlblMigBurCity;
     Spinner spnMigBurCity;
    LinearLayout secMigReason;
    View lineMigReason;
    TextView VlblMigReason;
    RadioGroup rdogrpMigReason;
    RadioButton rdoMigReason1;
    RadioButton rdoMigReason2;
    RadioButton rdoMigReason3;
    RadioButton rdoMigReason4;
    RadioButton rdoMigReason5;
    RadioButton rdoMigReason6;
    RadioButton rdoMigReason7;
    RadioButton rdoMigReason8;
    RadioButton rdoMigReason9;
    RadioButton rdoMigReason10;
    RadioButton rdoMigReason11;
    RadioButton rdoMigReason12;
    RadioButton rdoMigReason13;
    RadioButton rdoMigReason97;
    RadioButton rdoMigReason98;
    RadioButton rdoMigReason99;
    LinearLayout secMigReasonOth;
    View lineMigReasonOth;
    TextView VlblMigReasonOth;
    AutoCompleteTextView txtMigReasonOth;
     LinearLayout secMigMaritalChangeReason;
     View lineMigMaritalChangeReason;
     TextView VlblMigMaritalChangeReason;
     Spinner spnMigMaritalChangeReason;
     LinearLayout secMigMaritalChangeReasonOth;
     View lineMigMaritalChangeReasonOth;
     TextView VlblMigMaritalChangeReasonOth;
     AutoCompleteTextView txtMigMaritalChangeReasonOth;
     LinearLayout secMigAbleToRW;
     View lineMigAbleToRW;
     TextView VlblMigAbleToRW;
     RadioGroup rdogrpMigAbleToRW;
     RadioButton rdoMigAbleToRW1;
     RadioButton rdoMigAbleToRW2;
     LinearLayout seclbl01;
     View linelbl01;
     LinearLayout secchkMigLanguageA;
     View linechkMigLanguageA;
     TextView VlblchkMigLanguageA;
     CheckBox chkchkMigLanguageA;
     LinearLayout secchkMigLanguageB;
     View linechkMigLanguageB;
     TextView VlblchkMigLanguageB;
     CheckBox chkchkMigLanguageB;
     LinearLayout secchkMigLanguageC;
     View linechkMigLanguageC;
     TextView VlblchkMigLanguageC;
     CheckBox chkchkMigLanguageC;
     LinearLayout secchkMigLanguageD;
     View linechkMigLanguageD;
     TextView VlblchkMigLanguageD;
     CheckBox chkchkMigLanguageD;
     LinearLayout secMigLanguageOth;
     View lineMigLanguageOth;
     TextView VlblMigLanguageOth;
     AutoCompleteTextView txtMigLanguageOth;
     LinearLayout secMigOccupation;
     View lineMigOccupation;
     TextView VlblMigOccupation;
     Spinner spnMigOccupation;
     LinearLayout secMigTotalUnion;
     View lineMigTotalUnion;
     TextView VlblMigTotalUnion;
     EditText txtMigTotalUnion;
     LinearLayout secMigFirstUnionDate;
     View lineMigFirstUnionDate;
     TextView VlblMigFirstUnionDate;
     EditText dtpMigFirstUnionDate;
     LinearLayout secMigUnionRupture;
     View lineMigUnionRupture;
     TextView VlblMigUnionRupture;
     RadioGroup rdogrpMigUnionRupture;
     RadioButton rdoMigUnionRupture1;
     RadioButton rdoMigUnionRupture2;
     LinearLayout secMigRuptureDate;
     View lineMigRuptureDate;
     TextView VlblMigRuptureDate;
     EditText dtpMigRuptureDate;
     LinearLayout secMigNotDisUnionDate;
     View lineMigNotDisUnionDate;
     TextView VlblMigNotDisUnionDate;
     EditText dtpMigNotDisUnionDate;
     LinearLayout secMigVDate;
    View lineMigVDate;
    TextView VlblMigVDate;
    EditText dtpMigVDate;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secMigNote;
    View lineMigNote;
    TextView VlblMigNote;
    TextView lblEvCode;
    EditText txtMigNote;

     LinearLayout secMigNote1;
     View lineMigNote1;

    int MODULEID   = 0;
    int LANGUAGEID = 0;
    String TableName;

    String STARTTIME = "";
    String DEVICEID  = "";
    String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
    String MIGRATIONID = "";
    String MEM_ID = "";
    String HHID = "";
    String HHNO = "";
    String EV_TYPE = "";
    String ROUND = "";
    String VISIT_DATE = "";
     String SQL = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.migration);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         MIGRATIONID = IDbundle.getString("MigrationID");
         MEM_ID = IDbundle.getString("MemID");
         HHID = IDbundle.getString("HHID");
         HHNO = IDbundle.getString("HHNO");
         EV_TYPE = IDbundle.getString("evtype");
         ROUND = IDbundle.getString("round");
         VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy

         TableName = "tmpMigration";
         MODULEID = 23;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Migration.this);
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
        Connection.LocalizeLanguage(Surv_Migration.this, MODULEID, LANGUAGEID);


         secMigAdminL1.setVisibility(View.GONE);
         secMigAdminL2.setVisibility(View.GONE);
         secMigAdminL3.setVisibility(View.GONE);
         secMigAdminL4.setVisibility(View.GONE);
         secMigAdminLArea.setVisibility(View.GONE);

         secMigBirthCountry.setVisibility(View.GONE);
         lineMigBirthCountry.setVisibility(View.GONE);
         secMigOriginCountry.setVisibility(View.GONE);
         lineMigOriginCountry.setVisibility(View.GONE);
         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.BANGLADESH_SITE1))
         {
             secMigAdminL1.setVisibility(View.VISIBLE);
             secMigAdminL2.setVisibility(View.VISIBLE);
             secMigAdminL3.setVisibility(View.VISIBLE);
             VlblMigAdminL1.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER1);
             VlblMigAdminL2.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER2);
             VlblMigAdminL3.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER3);

         }

         //=========================================================================================
         //Nigeria: Cross River
         //=========================================================================================
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1))
         {
             secMigAdminL1.setVisibility(View.VISIBLE);
             secMigAdminL2.setVisibility(View.VISIBLE);
             secMigAdminL3.setVisibility(View.VISIBLE);
             secMigAdminL4.setVisibility(View.VISIBLE);
             VlblMigAdminL1.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER1);
             VlblMigAdminL2.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER2);
             VlblMigAdminL3.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER3);
             VlblMigAdminL4.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER4);
             rdoMigLocType1.setVisibility(View.GONE);
             rdoMigLocType3.setVisibility(View.VISIBLE);
             rdoMigLocType4.setVisibility(View.VISIBLE);

         }
         //=========================================================================================
         //Nigeria: Bauchi
         //=========================================================================================
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2))
         {
             secMigAdminL1.setVisibility(View.VISIBLE);
             secMigAdminL2.setVisibility(View.VISIBLE);
             secMigAdminL3.setVisibility(View.VISIBLE);
             VlblMigAdminL1.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER1);
             VlblMigAdminL2.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER2);
             VlblMigAdminL3.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER3);

         }
         //=========================================================================================
         //Sierra Leone
         //=========================================================================================
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1))
         {
             secMigLocType.setVisibility(View.VISIBLE);
             lineMigLocType.setVisibility(View.VISIBLE);
             secMigAdminL1.setVisibility(View.GONE);
             lineMigAdminL1.setVisibility(View.GONE);
             txtMigAdminL1.setText("");
             secMigAdminL2.setVisibility(View.GONE);
             lineMigAdminL2.setVisibility(View.GONE);
             txtMigAdminL2.setText("");
             secMigAdminL3.setVisibility(View.GONE);
             lineMigAdminL3.setVisibility(View.GONE);
             txtMigAdminL3.setText("");
             VlblMigAdminL1.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER1);
             VlblMigAdminL2.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER2);
             VlblMigAdminL3.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER3);

         }
         //=========================================================================================
         //Mali
         //=========================================================================================
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1))
         {
             secMigLocType.setVisibility(View.VISIBLE);
             lineMigLocType.setVisibility(View.VISIBLE);
             secMigAdminL1.setVisibility(View.GONE);
             secMigAdminL2.setVisibility(View.GONE);
             secMigAdminL3.setVisibility(View.GONE);
             VlblMigAdminL1.setText(ProjectSetting.MALI_SITE1_GEO_LAYER1);
             VlblMigAdminL2.setText(ProjectSetting.MALI_SITE1_GEO_LAYER2);
             VlblMigAdminL3.setText(ProjectSetting.MALI_SITE1_GEO_LAYER3);


         }



         if (EV_TYPE.equals("51"))
         {
             lblHeading.setText("Migration out");

             secMigAbleToRW.setVisibility(View.GONE);
             lineMigAbleToRW.setVisibility(View.GONE);
             seclbl01.setVisibility(View.GONE);
             linelbl01.setVisibility(View.GONE);
             secchkMigLanguageA.setVisibility(View.GONE);
             linechkMigLanguageA.setVisibility(View.GONE);
             secchkMigLanguageB.setVisibility(View.GONE);
             linechkMigLanguageB.setVisibility(View.GONE);
             secchkMigLanguageC.setVisibility(View.GONE);
             linechkMigLanguageC.setVisibility(View.GONE);
             secchkMigLanguageD.setVisibility(View.GONE);
             linechkMigLanguageD.setVisibility(View.GONE);
             secMigLanguageOth.setVisibility(View.GONE);
             lineMigLanguageOth.setVisibility(View.GONE);
             secMigOccupation.setVisibility(View.GONE);
             lineMigOccupation.setVisibility(View.GONE);
             secMigTotalUnion.setVisibility(View.GONE);
             lineMigTotalUnion.setVisibility(View.GONE);
             secMigFirstUnionDate.setVisibility(View.GONE);
             lineMigFirstUnionDate.setVisibility(View.GONE);
             secMigUnionRupture.setVisibility(View.GONE);
             lineMigUnionRupture.setVisibility(View.GONE);
             secMigRuptureDate.setVisibility(View.GONE);
             lineMigRuptureDate.setVisibility(View.GONE);
             secMigNotDisUnionDate.setVisibility(View.GONE);
             lineMigNotDisUnionDate.setVisibility(View.GONE);
             seclbl01.setVisibility(View.GONE);
             linelbl01.setVisibility(View.GONE);
             secchkMigLanguageA.setVisibility(View.GONE);
             linechkMigLanguageA.setVisibility(View.GONE);
             secchkMigLanguageB.setVisibility(View.GONE);
             linechkMigLanguageB.setVisibility(View.GONE);
             secchkMigLanguageC.setVisibility(View.GONE);
             linechkMigLanguageC.setVisibility(View.GONE);
             secchkMigLanguageD.setVisibility(View.GONE);
             linechkMigLanguageD.setVisibility(View.GONE);
             secMigLanguageOth.setVisibility(View.GONE);
             lineMigLanguageOth.setVisibility(View.GONE);
             secMigLanguageOth.setVisibility(View.GONE);
             lineMigLanguageOth.setVisibility(View.GONE);
             secMigOccupation.setVisibility(View.GONE);
             lineMigOccupation.setVisibility(View.GONE);
             secMigTotalUnion.setVisibility(View.GONE);
             lineMigTotalUnion.setVisibility(View.GONE);
             secMigFirstUnionDate.setVisibility(View.GONE);
             lineMigFirstUnionDate.setVisibility(View.GONE);
             secMigUnionRupture.setVisibility(View.GONE);
             lineMigUnionRupture.setVisibility(View.GONE);
             secMigRuptureDate.setVisibility(View.GONE);
             lineMigRuptureDate.setVisibility(View.GONE);
             secMigNotDisUnionDate.setVisibility(View.GONE);
             lineMigNotDisUnionDate.setVisibility(View.GONE);
         }else if (EV_TYPE.equals("20"))
         {
             lblHeading.setText("Registration");
             if(LANGUAGEID == 4){
                 VlblMigDate.setText("Depuis quand le membre commence-t-il actuellement à vivre dans ce ménage ? (ENTREZ LES JOURS D'AUJOURD'HUI SI LE MEMBRE A ÉTÉ MANQUÉ AU DÉPART)");
             }else{
                 VlblMigDate.setText("Since when does the member currently start living in this household? (ENTER TODAY'S DAYS IF THE MEMBER WAS MISSED AT BASELINE)");
             }

             secMigLocType.setVisibility(View.GONE);
             lineMigLocType.setVisibility(View.GONE);
             secMigAdminL1.setVisibility(View.GONE);
             lineMigAdminL1.setVisibility(View.GONE);
             secMigAdminL2.setVisibility(View.GONE);
             lineMigAdminL2.setVisibility(View.GONE);
             secMigAdminL3.setVisibility(View.GONE);
             lineMigAdminL3.setVisibility(View.GONE);
             secMigAdminL4.setVisibility(View.GONE);
             lineMigAdminL4.setVisibility(View.GONE);
             secMigAdminLArea.setVisibility(View.GONE);
             lineMigAdminLArea.setVisibility(View.GONE);
             secMigCountry.setVisibility(View.GONE);
             secMigReason.setVisibility(View.GONE);
             secMigBirthCountry.setVisibility(View.GONE);
             lineMigBirthCountry.setVisibility(View.GONE);
             secMigOriginCountry.setVisibility(View.GONE);
             lineMigOriginCountry.setVisibility(View.GONE);
             secMigAbleToRW.setVisibility(View.GONE);
             lineMigAbleToRW.setVisibility(View.GONE);
             seclbl01.setVisibility(View.GONE);
             linelbl01.setVisibility(View.GONE);
             secchkMigLanguageA.setVisibility(View.GONE);
             linechkMigLanguageA.setVisibility(View.GONE);
             secchkMigLanguageB.setVisibility(View.GONE);
             linechkMigLanguageB.setVisibility(View.GONE);
             secchkMigLanguageC.setVisibility(View.GONE);
             linechkMigLanguageC.setVisibility(View.GONE);
             secchkMigLanguageD.setVisibility(View.GONE);
             linechkMigLanguageD.setVisibility(View.GONE);
             secMigLanguageOth.setVisibility(View.GONE);
             lineMigLanguageOth.setVisibility(View.GONE);
             secMigOccupation.setVisibility(View.GONE);
             lineMigOccupation.setVisibility(View.GONE);
             secMigTotalUnion.setVisibility(View.GONE);
             lineMigTotalUnion.setVisibility(View.GONE);
             secMigFirstUnionDate.setVisibility(View.GONE);
             lineMigFirstUnionDate.setVisibility(View.GONE);
             secMigUnionRupture.setVisibility(View.GONE);
             lineMigUnionRupture.setVisibility(View.GONE);
             secMigRuptureDate.setVisibility(View.GONE);
             lineMigRuptureDate.setVisibility(View.GONE);
             secMigNotDisUnionDate.setVisibility(View.GONE);
             lineMigNotDisUnionDate.setVisibility(View.GONE);
             secMigMaritalChangeReasonOth.setVisibility(View.GONE);
             lineMigMaritalChangeReasonOth.setVisibility(View.GONE);
             secMigAbleToRW.setVisibility(View.GONE);
             lineMigAbleToRW.setVisibility(View.GONE);
             seclbl01.setVisibility(View.GONE);
             linelbl01.setVisibility(View.GONE);
             secchkMigLanguageA.setVisibility(View.GONE);
             linechkMigLanguageA.setVisibility(View.GONE);
             secchkMigLanguageB.setVisibility(View.GONE);
             linechkMigLanguageB.setVisibility(View.GONE);
             secchkMigLanguageC.setVisibility(View.GONE);
             linechkMigLanguageC.setVisibility(View.GONE);
             secchkMigLanguageD.setVisibility(View.GONE);
             linechkMigLanguageD.setVisibility(View.GONE);
             secMigLanguageOth.setVisibility(View.GONE);
             lineMigLanguageOth.setVisibility(View.GONE);
             secMigOccupation.setVisibility(View.GONE);
             lineMigOccupation.setVisibility(View.GONE);
             secMigTotalUnion.setVisibility(View.GONE);
             lineMigTotalUnion.setVisibility(View.GONE);
             secMigFirstUnionDate.setVisibility(View.GONE);
             lineMigFirstUnionDate.setVisibility(View.GONE);
             secMigUnionRupture.setVisibility(View.GONE);
             lineMigUnionRupture.setVisibility(View.GONE);
             secMigRuptureDate.setVisibility(View.GONE);
             lineMigRuptureDate.setVisibility(View.GONE);
             secMigNotDisUnionDate.setVisibility(View.GONE);
             lineMigNotDisUnionDate.setVisibility(View.GONE);
             seclbl01.setVisibility(View.GONE);
             linelbl01.setVisibility(View.GONE);
             secchkMigLanguageA.setVisibility(View.GONE);
             linechkMigLanguageA.setVisibility(View.GONE);
             secchkMigLanguageB.setVisibility(View.GONE);
             linechkMigLanguageB.setVisibility(View.GONE);
             secchkMigLanguageC.setVisibility(View.GONE);
             linechkMigLanguageC.setVisibility(View.GONE);
             secchkMigLanguageD.setVisibility(View.GONE);
             linechkMigLanguageD.setVisibility(View.GONE);
             secMigLanguageOth.setVisibility(View.GONE);
             lineMigLanguageOth.setVisibility(View.GONE);
             secMigLanguageOth.setVisibility(View.GONE);
             lineMigLanguageOth.setVisibility(View.GONE);
             secMigOccupation.setVisibility(View.GONE);
             lineMigOccupation.setVisibility(View.GONE);
             secMigTotalUnion.setVisibility(View.GONE);
             lineMigTotalUnion.setVisibility(View.GONE);
             secMigFirstUnionDate.setVisibility(View.GONE);
             lineMigFirstUnionDate.setVisibility(View.GONE);
             secMigUnionRupture.setVisibility(View.GONE);
             lineMigUnionRupture.setVisibility(View.GONE);
             secMigRuptureDate.setVisibility(View.GONE);
             lineMigRuptureDate.setVisibility(View.GONE);
             secMigNotDisUnionDate.setVisibility(View.GONE);
             lineMigNotDisUnionDate.setVisibility(View.GONE);

         }else if (EV_TYPE.equals("21"))
         {
             lblHeading.setText("Migration-In");
             if(LANGUAGEID == 4){
                 VlblMigDate.setText("Quand avez-vous emménagé dans ce foyer?");
             }else {
                 VlblMigDate.setText("When did you move into this household?");
             }

         }else if (EV_TYPE.equals("25"))
         {
             lblHeading.setText("Live Birth");
         }else if (EV_TYPE.equals("47"))
         {
             lblHeading.setText("Still Birth");
         }

         dtpMigDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnMigDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });

         dtpMigFirstUnionDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "dtpMigFirstUnionDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpMigRuptureDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "dtpMigRuptureDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpMigNotDisUnionDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "dtpMigNotDisUnionDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });

         dtpMigVDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnMigVDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables
//         secMigAdminL1.setVisibility(View.GONE);
//         lineMigAdminL1.setVisibility(View.GONE);
//         secMigAdminL2.setVisibility(View.GONE);
//         lineMigAdminL2.setVisibility(View.GONE);
//         secMigAdminL3.setVisibility(View.GONE);
//         lineMigAdminL3.setVisibility(View.GONE);
//         secMigAdminL4.setVisibility(View.GONE);
//         lineMigAdminL4.setVisibility(View.GONE);
         secMigAdminLArea.setVisibility(View.GONE);
         lineMigAdminLArea.setVisibility(View.GONE);
         secMigReasonOth.setVisibility(View.GONE);
         lineMigReasonOth.setVisibility(View.GONE);
         secMigCountry.setVisibility(View.GONE);
         lineMigCountry.setVisibility(View.GONE);
         secMigBirthCountryOth.setVisibility(View.GONE);
         lineMigBirthCountryOth.setVisibility(View.GONE);
         secMigOriginCountryOth.setVisibility(View.GONE);
         lineMigOriginCountryOth.setVisibility(View.GONE);
         secMigWCRegionBurCity.setVisibility(View.GONE);
         lineMigWCRegionBurCity.setVisibility(View.GONE);
         secMigBurCity.setVisibility(View.GONE);
         lineMigBurCity.setVisibility(View.GONE);
         secMigReasonOth.setVisibility(View.GONE);
         lineMigReasonOth.setVisibility(View.GONE);
         secMigMaritalChangeReason.setVisibility(View.GONE);
         lineMigMaritalChangeReason.setVisibility(View.GONE);
         secMigMaritalChangeReasonOth.setVisibility(View.GONE);
         lineMigMaritalChangeReasonOth.setVisibility(View.GONE);

        DataSearch(MIGRATIONID);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Migration.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         lblEvCode = findViewById(R.id.lblEvCode);
         lblEvCode.setText(EV_TYPE);



         secMigrationID=(LinearLayout)findViewById(R.id.secMigrationID);
         lineMigrationID=(View)findViewById(R.id.lineMigrationID);
         VlblMigrationID=(TextView) findViewById(R.id.VlblMigrationID);
         txtMigrationID=(EditText) findViewById(R.id.txtMigrationID);
         if(MIGRATIONID.length()==0) txtMigrationID.setText(Global.Get_UUID(DEVICEID));
         else txtMigrationID.setText(MIGRATIONID);
         txtMigrationID.setEnabled(false);

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

         if(MEM_ID.length()==0) txtMemID.setText(Global.Get_UUID(DEVICEID));
         else txtMemID.setText(MEM_ID);
         txtMemID.setEnabled(false);

         secMigEvType=(LinearLayout)findViewById(R.id.secMigEvType);
         lineMigEvType=(View)findViewById(R.id.lineMigEvType);
         VlblMigEvType = (TextView) findViewById(R.id.VlblMigEvType);
         rdogrpMigEvType = (RadioGroup) findViewById(R.id.rdogrpMigEvType);
         rdoMigEvType1 = (RadioButton) findViewById(R.id.rdoMigEvType1);
         rdoMigEvType2 = (RadioButton) findViewById(R.id.rdoMigEvType2);
         rdoMigEvType3 = (RadioButton) findViewById(R.id.rdoMigEvType3);
         if(EV_TYPE.equals("20")){
             rdoMigEvType1.setChecked(true);
         }else if(EV_TYPE.equals("21")){
             rdoMigEvType2.setChecked(true);
         }else if(EV_TYPE.equals("51")){
             rdoMigEvType1.setEnabled(false);
             rdoMigEvType2.setEnabled(false);
             rdoMigEvType3.setChecked(true);
         }

         secMigEvType1=(LinearLayout)findViewById(R.id.secMigEvType1);
         lineMigEvType1=(View)findViewById(R.id.lineMigEvType1);

         secMigDate=(LinearLayout)findViewById(R.id.secMigDate);
         lineMigDate=(View)findViewById(R.id.lineMigDate);
         VlblMigDate=(TextView) findViewById(R.id.VlblMigDate);
         dtpMigDate=(EditText) findViewById(R.id.dtpMigDate);
         secMigLocType=(LinearLayout)findViewById(R.id.secMigLocType);
         lineMigLocType=(View)findViewById(R.id.lineMigLocType);
         VlblMigLocType = (TextView) findViewById(R.id.VlblMigLocType);
         rdogrpMigLocType = (RadioGroup) findViewById(R.id.rdogrpMigLocType);
         rdoMigLocType1 = (RadioButton) findViewById(R.id.rdoMigLocType1);
         rdoMigLocType2 = (RadioButton) findViewById(R.id.rdoMigLocType2);
         rdoMigLocType3 = (RadioButton) findViewById(R.id.rdoMigLocType3);
         rdoMigLocType4 = (RadioButton) findViewById(R.id.rdoMigLocType4);
         rdogrpMigLocType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpMigLocType = new String[] {"1","3","4","2"};
             for (int i = 0; i < rdogrpMigLocType.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpMigLocType.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpMigLocType[i];
             }

             if(rbData.equalsIgnoreCase("2"))
             {
                secMigAdminL1.setVisibility(View.GONE);
                lineMigAdminL1.setVisibility(View.GONE);
                txtMigAdminL1.setText("");
                secMigAdminL2.setVisibility(View.GONE);
                lineMigAdminL2.setVisibility(View.GONE);
                 txtMigAdminL2.setText("");
                secMigAdminL3.setVisibility(View.GONE);
                lineMigAdminL3.setVisibility(View.GONE);
                 txtMigAdminL3.setText("");
                secMigAdminL4.setVisibility(View.GONE);
                lineMigAdminL4.setVisibility(View.GONE);
                txtMigAdminL4.setText("");
                secMigAdminLArea.setVisibility(View.GONE);
                lineMigAdminLArea.setVisibility(View.GONE);
                txtMigAdminLArea.setText("");
                secMigCountry.setVisibility(View.VISIBLE);
                lineMigCountry.setVisibility(View.VISIBLE);
                 if(EV_TYPE.equals("21") & ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                     secMigBirthCountry.setVisibility(View.VISIBLE);
                     lineMigBirthCountry.setVisibility(View.VISIBLE);
                     secMigOriginCountry.setVisibility(View.VISIBLE);
                     lineMigOriginCountry.setVisibility(View.VISIBLE);
                 }
             }
             else if (rbData.equalsIgnoreCase("3")) {
                 secMigAdminL1.setVisibility(View.VISIBLE);
                 lineMigAdminL1.setVisibility(View.VISIBLE);
                 secMigAdminL2.setVisibility(View.VISIBLE);
                 lineMigAdminL2.setVisibility(View.VISIBLE);
                 secMigAdminL3.setVisibility(View.GONE);
                 lineMigAdminL3.setVisibility(View.GONE);
                 txtMigAdminL3.setText("");
                secMigAdminL4.setVisibility(View.GONE);
                lineMigAdminL4.setVisibility(View.GONE);
                txtMigAdminL4.setText("");
                /*secMigAdminLArea.setVisibility(View.VISIBLE);
                lineMigAdminLArea.setVisibility(View.VISIBLE);*/
                 secMigCountry.setVisibility(View.GONE);
                 txtMigCountry.setText("");
                 lineMigCountry.setVisibility(View.GONE);
                 secMigBirthCountry.setVisibility(View.GONE);
                 lineMigBirthCountry.setVisibility(View.GONE);
                 spnMigBirthCountry.setSelection(0);
                 secMigOriginCountry.setVisibility(View.GONE);
                 lineMigOriginCountry.setVisibility(View.GONE);
                 spnMigOriginCountry.setSelection(0);
                 secMigWCRegionBurCity.setVisibility(View.GONE);
                 lineMigWCRegionBurCity.setVisibility(View.GONE);
                 spnMigWCRegionBurCity.setSelection(0);
                 secMigBurCity.setVisibility(View.GONE);
                 lineMigBurCity.setVisibility(View.GONE);
                 spnMigBurCity.setSelection(0);
             }else if (rbData.equalsIgnoreCase("4")) {
                 secMigAdminL1.setVisibility(View.VISIBLE);
                 lineMigAdminL1.setVisibility(View.VISIBLE);
                 secMigAdminL2.setVisibility(View.GONE);
                 lineMigAdminL2.setVisibility(View.GONE);
                 txtMigAdminL2.setText("");
                 secMigAdminL3.setVisibility(View.GONE);
                 lineMigAdminL3.setVisibility(View.GONE);
                 txtMigAdminL3.setText("");
                 secMigAdminL4.setVisibility(View.VISIBLE);
                 lineMigAdminL4.setVisibility(View.VISIBLE);
                /*secMigAdminLArea.setVisibility(View.VISIBLE);
                lineMigAdminLArea.setVisibility(View.VISIBLE);*/
                 secMigCountry.setVisibility(View.GONE);
                 txtMigCountry.setText("");
                 lineMigCountry.setVisibility(View.GONE);
                 secMigBirthCountry.setVisibility(View.GONE);
                 lineMigBirthCountry.setVisibility(View.GONE);
                 spnMigBirthCountry.setSelection(0);
                 secMigOriginCountry.setVisibility(View.GONE);
                 lineMigOriginCountry.setVisibility(View.GONE);
                 spnMigOriginCountry.setSelection(0);
                 secMigWCRegionBurCity.setVisibility(View.GONE);
                 lineMigWCRegionBurCity.setVisibility(View.GONE);
                 spnMigWCRegionBurCity.setSelection(0);
                 secMigBurCity.setVisibility(View.GONE);
                 lineMigBurCity.setVisibility(View.GONE);
                 spnMigBurCity.setSelection(0);
             } else
             {
                secMigAdminL1.setVisibility(View.VISIBLE);
                lineMigAdminL1.setVisibility(View.VISIBLE);
                secMigAdminL2.setVisibility(View.VISIBLE);
                lineMigAdminL2.setVisibility(View.VISIBLE);
                secMigAdminL3.setVisibility(View.VISIBLE);
                lineMigAdminL3.setVisibility(View.VISIBLE);
                secMigAdminL4.setVisibility(View.GONE);
                lineMigAdminL4.setVisibility(View.GONE);
                txtMigAdminL4.setText("");
                /*secMigAdminLArea.setVisibility(View.VISIBLE);
                lineMigAdminLArea.setVisibility(View.VISIBLE);*/
                secMigCountry.setVisibility(View.GONE);
                txtMigCountry.setText("");
                lineMigCountry.setVisibility(View.GONE);
                 secMigBirthCountry.setVisibility(View.GONE);
                 lineMigBirthCountry.setVisibility(View.GONE);
                 spnMigBirthCountry.setSelection(0);
                 secMigOriginCountry.setVisibility(View.GONE);
                 lineMigOriginCountry.setVisibility(View.GONE);
                 spnMigOriginCountry.setSelection(0);
                 secMigWCRegionBurCity.setVisibility(View.GONE);
                 lineMigWCRegionBurCity.setVisibility(View.GONE);
                 spnMigWCRegionBurCity.setSelection(0);
                 secMigBurCity.setVisibility(View.GONE);
                 lineMigBurCity.setVisibility(View.GONE);
                 spnMigBurCity.setSelection(0);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            }
         });
         secMigAdminL1=(LinearLayout)findViewById(R.id.secMigAdminL1);
         lineMigAdminL1=(View)findViewById(R.id.lineMigAdminL1);
         VlblMigAdminL1=(TextView) findViewById(R.id.VlblMigAdminL1);

//         spnLayer1 = (Spinner) findViewById(R.id.spnLayer1);
//         spnLayer2 = (Spinner) findViewById(R.id.spnLayer2);
//         spnLayer3 = (Spinner) findViewById(R.id.spnLayer3);

         txtMigAdminL1=(AutoCompleteTextView) findViewById(R.id.txtMigAdminL1);

         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
             txtMigAdminL1.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_LGA())));
         }
         else{
             txtMigAdminL1.setAdapter(C.getArrayAdapter("Select distinct MigAdminL1 from "+ TableName +" order by MigAdminL1"));
         }


         txtMigAdminL1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {
             }
         });
         txtMigAdminL1.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;

                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtMigAdminL1.getRight() - txtMigAdminL1.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secMigAdminL2=(LinearLayout)findViewById(R.id.secMigAdminL2);
         lineMigAdminL2=(View)findViewById(R.id.lineMigAdminL2);
         VlblMigAdminL2=(TextView) findViewById(R.id.VlblMigAdminL2);
         txtMigAdminL2=(AutoCompleteTextView) findViewById(R.id.txtMigAdminL2);
         txtMigAdminL2.setAdapter(C.getArrayAdapter("Select distinct MigAdminL2 from "+ TableName +" order by MigAdminL2"));

         txtMigAdminL2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtMigAdminL2.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;

                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtMigAdminL2.getRight() - txtMigAdminL2.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secMigAdminL3=(LinearLayout)findViewById(R.id.secMigAdminL3);
         lineMigAdminL3=(View)findViewById(R.id.lineMigAdminL3);
         VlblMigAdminL3=(TextView) findViewById(R.id.VlblMigAdminL3);
         txtMigAdminL3=(AutoCompleteTextView) findViewById(R.id.txtMigAdminL3);
         txtMigAdminL3.setAdapter(C.getArrayAdapter("Select distinct MigAdminL3 from "+ TableName +" order by MigAdminL3"));

         txtMigAdminL3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtMigAdminL3.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;

                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtMigAdminL3.getRight() - txtMigAdminL3.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });

//         SQL = " select distinct GeoLevel7||'-'||GeoLevel7Name from Village v \n" +
//                 " inner join Location l on v.LocID=l.LocID\n" +
//                 " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and WorkGroup='3' and Active='1' " +
//                 " order by GeoLevel7||'-'||GeoLevel7Name";
//
//         spnLayer1.setAdapter(C.getArrayAdapter(SQL));
//         spnLayer1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//             @Override
//             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                 if(spnLayer1.getCount()==0) return;
//
//                 SQL = " select distinct GeoLevel8||'-'||GeoLevel8Name from Village v \n" +
//                         " inner join Location l on v.LocID=l.LocID\n" +
//                         " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and WorkGroup='3' and Active='1' " +
//                         " where GeoLevel7='"+ spnLayer1.getSelectedItem().toString().split("-")[0] +"'" +
//                         " order by GeoLevel8||'-'||GeoLevel8Name";
//
//                 spnLayer2.setAdapter(C.getArrayAdapter(SQL));
//             }
//
//             @Override
//             public void onNothingSelected(AdapterView<?> parentView) {
//                 // your code here
//             }
//
//         });
//
//
//         spnLayer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//             @Override
//             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                 if(spnLayer2.getCount()==0) return;
//                 String LocationID = C.ReturnSingleValue("Select distinct LocID from Location where GeoLevel8='"+ spnLayer2.getSelectedItem().toString().split("-")[0] +"'");
//
//                 spnLayer3.setAdapter(C.getArrayAdapter("Select cast(v.VillID as varchar(20))||'-'||v.VillName from Village v inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' where v.LocID='"+ LocationID +"' and isdelete=2"));
//             }
//
//             @Override
//             public void onNothingSelected(AdapterView<?> parentView) {
//                 // your code here
//             }
//
//         });

         secMigAdminL4=(LinearLayout)findViewById(R.id.secMigAdminL4);
         lineMigAdminL4=(View)findViewById(R.id.lineMigAdminL4);
         VlblMigAdminL4=(TextView) findViewById(R.id.VlblMigAdminL4);
         txtMigAdminL4=(AutoCompleteTextView) findViewById(R.id.txtMigAdminL4);

         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
             txtMigAdminL4.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_State())));
         }
         else{
             txtMigAdminL4.setAdapter(C.getArrayAdapter("Select distinct MigAdminL4 from "+ TableName +" order by MigAdminL4"));
         }


         txtMigAdminL4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtMigAdminL4.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;

                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtMigAdminL4.getRight() - txtMigAdminL4.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secMigAdminLArea=(LinearLayout)findViewById(R.id.secMigAdminLArea);
         lineMigAdminLArea=(View)findViewById(R.id.lineMigAdminLArea);
         VlblMigAdminLArea=(TextView) findViewById(R.id.VlblMigAdminLArea);
         txtMigAdminLArea=(EditText) findViewById(R.id.txtMigAdminLArea);
         secMigCountry=(LinearLayout)findViewById(R.id.secMigCountry);
         lineMigCountry=(View)findViewById(R.id.lineMigCountry);
         VlblMigCountry=(TextView) findViewById(R.id.VlblMigCountry);
         txtMigCountry=(AutoCompleteTextView) findViewById(R.id.txtMigCountry);
//         txtMigCountry.setAdapter(C.getArrayAdapter("Select distinct MigCountry from "+ TableName +" order by MigCountry"));
         txtMigCountry.setAdapter(C.getArrayAdapter("Select distinct CountryName from AllCountry order by CountryName"));

         txtMigCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtMigCountry.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;

                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtMigCountry.getRight() - txtMigCountry.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });

         secMigBirthCountry=(LinearLayout)findViewById(R.id.secMigBirthCountry);
         lineMigBirthCountry=(View)findViewById(R.id.lineMigBirthCountry);
         VlblMigBirthCountry=(TextView) findViewById(R.id.VlblMigBirthCountry);
         spnMigBirthCountry=(Spinner) findViewById(R.id.spnMigBirthCountry);
         List<String> listMigBirthCountry = new ArrayList<String>();

         listMigBirthCountry.add("");
         listMigBirthCountry.add("1-BURKINA FASO");
         listMigBirthCountry.add("2-COTE DIVOIRE");
         listMigBirthCountry.add("3-MALI");
         listMigBirthCountry.add("4-GHANA");
         listMigBirthCountry.add("5-OTHER COUNTRY (SPECIFY)   ");
         listMigBirthCountry.add("6-UNKNOWN");
         spnMigBirthCountry.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listMigBirthCountry)));

         spnMigBirthCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 String spnData = "";
                 if (spnMigBirthCountry.getSelectedItem().toString().length() != 0)
                 {
                     spnData = Connection.SelectedSpinnerValue(spnMigBirthCountry.getSelectedItem().toString(), "-");
                 }
                 if(spnData.equalsIgnoreCase("5"))
                 {
                     secMigBirthCountryOth.setVisibility(View.VISIBLE);
                     lineMigBirthCountryOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secMigBirthCountryOth.setVisibility(View.GONE);
                     lineMigBirthCountryOth.setVisibility(View.GONE);
                     txtMigBirthCountryOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secMigBirthCountryOth=(LinearLayout)findViewById(R.id.secMigBirthCountryOth);
         lineMigBirthCountryOth=(View)findViewById(R.id.lineMigBirthCountryOth);
         VlblMigBirthCountryOth=(TextView) findViewById(R.id.VlblMigBirthCountryOth);
         txtMigBirthCountryOth=(AutoCompleteTextView) findViewById(R.id.txtMigBirthCountryOth);
         C.setupAutoComplete(TableName,txtMigBirthCountryOth,"MigBirthCountryOth"); //setup autocomplete view by event
         secMigOriginCountry=(LinearLayout)findViewById(R.id.secMigOriginCountry);
         lineMigOriginCountry=(View)findViewById(R.id.lineMigOriginCountry);
         VlblMigOriginCountry=(TextView) findViewById(R.id.VlblMigOriginCountry);
         spnMigOriginCountry=(Spinner) findViewById(R.id.spnMigOriginCountry);
         List<String> listMigOriginCountry = new ArrayList<String>();

         listMigOriginCountry.add("");
         listMigOriginCountry.add("1-BURKINA FASO");
         listMigOriginCountry.add("2-COTE DIVOIRE");
         listMigOriginCountry.add("3-MALI");
         listMigOriginCountry.add("4-GHANA");
         listMigOriginCountry.add("5-OTHER COUNTRY (SPECIFY)   ");
         listMigOriginCountry.add("6-UNKNOWN");
         spnMigOriginCountry.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listMigOriginCountry)));

         spnMigOriginCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 String spnData = "";
                 if (spnMigOriginCountry.getSelectedItem().toString().length() != 0)
                 {
                     spnData = Connection.SelectedSpinnerValue(spnMigOriginCountry.getSelectedItem().toString(), "-");
                 }
                 if(spnData.equalsIgnoreCase("1"))
                 {
                     secMigOriginCountryOth.setVisibility(View.GONE);
                     lineMigOriginCountryOth.setVisibility(View.GONE);
                     txtMigOriginCountryOth.setText("");
                     secMigWCRegionBurCity.setVisibility(View.VISIBLE);
                     lineMigWCRegionBurCity.setVisibility(View.VISIBLE);
                     secMigBurCity.setVisibility(View.VISIBLE);
                     lineMigBurCity.setVisibility(View.VISIBLE);
                 }
                 else if(spnData.equalsIgnoreCase("5"))
                 {
                     secMigOriginCountryOth.setVisibility(View.VISIBLE);
                     lineMigOriginCountryOth.setVisibility(View.VISIBLE);
                     secMigWCRegionBurCity.setVisibility(View.GONE);
                     lineMigWCRegionBurCity.setVisibility(View.GONE);
                     spnMigWCRegionBurCity.setSelection(0);
                     secMigBurCity.setVisibility(View.GONE);
                     lineMigBurCity.setVisibility(View.GONE);
                     spnMigBurCity.setSelection(0);
                 }
                 else
                 {
                     secMigOriginCountryOth.setVisibility(View.GONE);
                     lineMigOriginCountryOth.setVisibility(View.GONE);
                     txtMigOriginCountryOth.setText("");
                     secMigWCRegionBurCity.setVisibility(View.GONE);
                     lineMigWCRegionBurCity.setVisibility(View.GONE);
                     spnMigWCRegionBurCity.setSelection(0);
                     secMigBurCity.setVisibility(View.GONE);
                     lineMigBurCity.setVisibility(View.GONE);
                     spnMigBurCity.setSelection(0);
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secMigOriginCountryOth=(LinearLayout)findViewById(R.id.secMigOriginCountryOth);
         lineMigOriginCountryOth=(View)findViewById(R.id.lineMigOriginCountryOth);
         VlblMigOriginCountryOth=(TextView) findViewById(R.id.VlblMigOriginCountryOth);
         txtMigOriginCountryOth=(AutoCompleteTextView) findViewById(R.id.txtMigOriginCountryOth);
         C.setupAutoComplete(TableName,txtMigOriginCountryOth,"MigOriginCountryOth"); //setup autocomplete view by event
         secMigWCRegionBurCity=(LinearLayout)findViewById(R.id.secMigWCRegionBurCity);
         lineMigWCRegionBurCity=(View)findViewById(R.id.lineMigWCRegionBurCity);
         VlblMigWCRegionBurCity=(TextView) findViewById(R.id.VlblMigWCRegionBurCity);
         spnMigWCRegionBurCity=(Spinner) findViewById(R.id.spnMigWCRegionBurCity);
         List<String> listMigWCRegionBurCity = new ArrayList<String>();

         listMigWCRegionBurCity.add("");
         listMigWCRegionBurCity.add("1-Koudougou");
         listMigWCRegionBurCity.add("2-Léo ");
         listMigWCRegionBurCity.add("3-Réo");
         listMigWCRegionBurCity.add("4-Sapouy");
         listMigWCRegionBurCity.add("5-No applicable");
         spnMigWCRegionBurCity.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listMigWCRegionBurCity)));

         secMigBurCity=(LinearLayout)findViewById(R.id.secMigBurCity);
         lineMigBurCity=(View)findViewById(R.id.lineMigBurCity);
         VlblMigBurCity=(TextView) findViewById(R.id.VlblMigBurCity);
         spnMigBurCity=(Spinner) findViewById(R.id.spnMigBurCity);
         List<String> listMigBurCity = new ArrayList<String>();

         listMigBurCity.add("");
         listMigBurCity.add("1-OUAGADOUGOU");
         listMigBurCity.add("2-Bobo Dioulasso");
         listMigBurCity.add("3-Yako");
         listMigBurCity.add("4-Bousse");
         listMigBurCity.add("5-Banfora");
         listMigBurCity.add("6-Ouahigouya");
         listMigBurCity.add("7-Other country in Burkina ");
         listMigBurCity.add("8-Unknown");
         listMigBurCity.add("9-Not applicable");
         spnMigBurCity.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listMigBurCity)));


         secMigReason=(LinearLayout)findViewById(R.id.secMigReason);
         lineMigReason=(View)findViewById(R.id.lineMigReason);
         VlblMigReason = (TextView) findViewById(R.id.VlblMigReason);
         rdogrpMigReason = (RadioGroup) findViewById(R.id.rdogrpMigReason);
         rdoMigReason1 = (RadioButton) findViewById(R.id.rdoMigReason1);
         rdoMigReason2 = (RadioButton) findViewById(R.id.rdoMigReason2);
         rdoMigReason3 = (RadioButton) findViewById(R.id.rdoMigReason3);
         rdoMigReason4 = (RadioButton) findViewById(R.id.rdoMigReason4);
         rdoMigReason5 = (RadioButton) findViewById(R.id.rdoMigReason5);
         rdoMigReason6 = (RadioButton) findViewById(R.id.rdoMigReason6);
         rdoMigReason7 = (RadioButton) findViewById(R.id.rdoMigReason7);
         rdoMigReason8 = (RadioButton) findViewById(R.id.rdoMigReason8);
         rdoMigReason9 = (RadioButton) findViewById(R.id.rdoMigReason9);
         rdoMigReason10 = (RadioButton) findViewById(R.id.rdoMigReason10);
         rdoMigReason11 = (RadioButton) findViewById(R.id.rdoMigReason11);
         rdoMigReason12 = (RadioButton) findViewById(R.id.rdoMigReason12);
         rdoMigReason13 = (RadioButton) findViewById(R.id.rdoMigReason13);
         rdoMigReason97 = (RadioButton) findViewById(R.id.rdoMigReason97);
         rdoMigReason98 = (RadioButton) findViewById(R.id.rdoMigReason98);
         rdoMigReason99 = (RadioButton) findViewById(R.id.rdoMigReason99);

         if(!ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
             rdoMigReason7.setVisibility(View.GONE);
             rdoMigReason8.setVisibility(View.GONE);
             rdoMigReason9.setVisibility(View.GONE);
             rdoMigReason10.setVisibility(View.GONE);
             rdoMigReason11.setVisibility(View.GONE);
             rdoMigReason12.setVisibility(View.GONE);
             rdoMigReason13.setVisibility(View.GONE);
         }
         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
             rdoMigReason7.setVisibility(View.GONE);
             rdoMigReason8.setVisibility(View.VISIBLE);
             rdoMigReason9.setVisibility(View.GONE);
             rdoMigReason10.setVisibility(View.GONE);
             rdoMigReason11.setVisibility(View.GONE);
             rdoMigReason12.setVisibility(View.GONE);
             rdoMigReason13.setVisibility(View.VISIBLE);
         }
//         if(!ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1)){
//             secMigLocType.setVisibility(View.VISIBLE);
//             lineMigLocType.setVisibility(View.VISIBLE);
//             secMigAdminL1.setVisibility(View.GONE);
//             lineMigAdminL1.setVisibility(View.GONE);
//             spnLayer1.setSelection(0);
//             secMigAdminL2.setVisibility(View.VISIBLE);
//             lineMigAdminL2.setVisibility(View.VISIBLE);
//             txtMigAdminL2.setText("");
//             secMigAdminL3.setVisibility(View.VISIBLE);
//             lineMigAdminL3.setVisibility(View.VISIBLE);
//             txtMigAdminL3.setText("");
//         }

         rdogrpMigReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpMigReason = new String[] {"01","02","03","04","05","06","07","08","09","10","11","12","13","97","98","99"};
             for (int i = 0; i < rdogrpMigReason.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpMigReason.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpMigReason[i];
             }

             if(rbData.equalsIgnoreCase("97"))
             {
                 secMigReasonOth.setVisibility(View.VISIBLE);
                 lineMigReasonOth.setVisibility(View.VISIBLE);
                 secMigMaritalChangeReason.setVisibility(View.GONE);
                 lineMigMaritalChangeReason.setVisibility(View.GONE);
                 spnMigMaritalChangeReason.setSelection(0);
                 secMigMaritalChangeReasonOth.setVisibility(View.GONE);
                 lineMigMaritalChangeReasonOth.setVisibility(View.GONE);
                 txtMigMaritalChangeReasonOth.setText("");
             }
             else if(rbData.equalsIgnoreCase("04"))
             {
                 if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                     secMigMaritalChangeReason.setVisibility(View.VISIBLE);
                     lineMigMaritalChangeReason.setVisibility(View.VISIBLE);
                 }else{
                     secMigMaritalChangeReason.setVisibility(View.GONE);
                     lineMigMaritalChangeReason.setVisibility(View.GONE);
                     spnMigMaritalChangeReason.setSelection(0);
                     secMigMaritalChangeReasonOth.setVisibility(View.GONE);
                     lineMigMaritalChangeReasonOth.setVisibility(View.GONE);
                     txtMigMaritalChangeReasonOth.setText("");
                 }

                 secMigReasonOth.setVisibility(View.GONE);
                 lineMigReasonOth.setVisibility(View.GONE);
                 txtMigReasonOth.setText("");
             }
             else
             {
                 secMigReasonOth.setVisibility(View.GONE);
                 lineMigReasonOth.setVisibility(View.GONE);
                 txtMigReasonOth.setText("");
                 secMigMaritalChangeReason.setVisibility(View.GONE);
                 lineMigMaritalChangeReason.setVisibility(View.GONE);
                 spnMigMaritalChangeReason.setSelection(0);
                 secMigMaritalChangeReasonOth.setVisibility(View.GONE);
                 lineMigMaritalChangeReasonOth.setVisibility(View.GONE);
                 txtMigMaritalChangeReasonOth.setText("");
             }
        }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            }
         });
         secMigReasonOth=(LinearLayout)findViewById(R.id.secMigReasonOth);
         lineMigReasonOth=(View)findViewById(R.id.lineMigReasonOth);
         VlblMigReasonOth=(TextView) findViewById(R.id.VlblMigReasonOth);
         txtMigReasonOth=(AutoCompleteTextView) findViewById(R.id.txtMigReasonOth);
         C.setupAutoComplete(TableName,txtMigReasonOth,"MigReasonOth"); //setup autocomplete view by event
         secMigMaritalChangeReason=(LinearLayout)findViewById(R.id.secMigMaritalChangeReason);
         lineMigMaritalChangeReason=(View)findViewById(R.id.lineMigMaritalChangeReason);
         VlblMigMaritalChangeReason=(TextView) findViewById(R.id.VlblMigMaritalChangeReason);
         spnMigMaritalChangeReason=(Spinner) findViewById(R.id.spnMigMaritalChangeReason);
         /*List<String> listMigMaritalChangeReason = new ArrayList<String>();

         listMigMaritalChangeReason.add("");
         listMigMaritalChangeReason.add("1-Marriage");
         listMigMaritalChangeReason.add("2-Divorce");
         listMigMaritalChangeReason.add("3-Separation");
         listMigMaritalChangeReason.add("4-Widowed");
         listMigMaritalChangeReason.add("5-Reconciliation ");
         listMigMaritalChangeReason.add("6-None of the above");
         listMigMaritalChangeReason.add("7-Reason (other - Specify)");*/
         spnMigMaritalChangeReason.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(Global_CodeList.Get_MARITAL_STATUS())));

         spnMigMaritalChangeReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 String spnData = "";
                 if (spnMigMaritalChangeReason.getSelectedItem().toString().length() != 0)
                 {
                     spnData = Connection.SelectedSpinnerValue(spnMigMaritalChangeReason.getSelectedItem().toString(), "-");
                 }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                     secMigMaritalChangeReasonOth.setVisibility(View.VISIBLE);
                     lineMigMaritalChangeReasonOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secMigMaritalChangeReasonOth.setVisibility(View.GONE);
                     lineMigMaritalChangeReasonOth.setVisibility(View.GONE);
                     txtMigMaritalChangeReasonOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secMigMaritalChangeReasonOth=(LinearLayout)findViewById(R.id.secMigMaritalChangeReasonOth);
         lineMigMaritalChangeReasonOth=(View)findViewById(R.id.lineMigMaritalChangeReasonOth);
         VlblMigMaritalChangeReasonOth=(TextView) findViewById(R.id.VlblMigMaritalChangeReasonOth);
         txtMigMaritalChangeReasonOth=(AutoCompleteTextView) findViewById(R.id.txtMigMaritalChangeReasonOth);
         C.setupAutoComplete(TableName,txtMigMaritalChangeReasonOth,"MigMaritalChangeReasonOth"); //setup autocomplete view by event
         secMigAbleToRW=(LinearLayout)findViewById(R.id.secMigAbleToRW);
         lineMigAbleToRW=(View)findViewById(R.id.lineMigAbleToRW);
         VlblMigAbleToRW = (TextView) findViewById(R.id.VlblMigAbleToRW);
         rdogrpMigAbleToRW = (RadioGroup) findViewById(R.id.rdogrpMigAbleToRW);
         rdoMigAbleToRW1 = (RadioButton) findViewById(R.id.rdoMigAbleToRW1);
         rdoMigAbleToRW2 = (RadioButton) findViewById(R.id.rdoMigAbleToRW2);
         rdogrpMigAbleToRW.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpMigAbleToRW = new String[] {"0","1"};
                 for (int i = 0; i < rdogrpMigAbleToRW.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpMigAbleToRW.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpMigAbleToRW[i];
                 }

                 if(rbData.equalsIgnoreCase("1"))
                 {

                     seclbl01.setVisibility(View.VISIBLE);
                     linelbl01.setVisibility(View.VISIBLE);
                     secchkMigLanguageA.setVisibility(View.VISIBLE);
                     linechkMigLanguageA.setVisibility(View.VISIBLE);
                     secchkMigLanguageB.setVisibility(View.VISIBLE);
                     linechkMigLanguageB.setVisibility(View.VISIBLE);
                     secchkMigLanguageC.setVisibility(View.VISIBLE);
                     linechkMigLanguageC.setVisibility(View.VISIBLE);
                     secchkMigLanguageD.setVisibility(View.VISIBLE);
                     linechkMigLanguageD.setVisibility(View.VISIBLE);
                 }
                 else
                 {

                     seclbl01.setVisibility(View.GONE);
                     linelbl01.setVisibility(View.GONE);
                     secchkMigLanguageA.setVisibility(View.GONE);
                     linechkMigLanguageA.setVisibility(View.GONE);
                     chkchkMigLanguageA.setChecked(false);
                     secchkMigLanguageB.setVisibility(View.GONE);
                     linechkMigLanguageB.setVisibility(View.GONE);
                     chkchkMigLanguageB.setChecked(false);
                     secchkMigLanguageC.setVisibility(View.GONE);
                     linechkMigLanguageC.setVisibility(View.GONE);
                     chkchkMigLanguageC.setChecked(false);
                     secchkMigLanguageD.setVisibility(View.GONE);
                     linechkMigLanguageD.setVisibility(View.GONE);
                     chkchkMigLanguageD.setChecked(false);
                     secMigLanguageOth.setVisibility(View.GONE);
                     lineMigLanguageOth.setVisibility(View.GONE);
                     txtMigLanguageOth.setText("");
                 }
             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });
         seclbl01=(LinearLayout)findViewById(R.id.seclbl01);
         linelbl01=(View)findViewById(R.id.linelbl01);
         secchkMigLanguageA=(LinearLayout)findViewById(R.id.secchkMigLanguageA);
         linechkMigLanguageA=(View)findViewById(R.id.linechkMigLanguageA);
         VlblchkMigLanguageA=(TextView) findViewById(R.id.VlblchkMigLanguageA);
         chkchkMigLanguageA=(CheckBox) findViewById(R.id.chkchkMigLanguageA);
         secchkMigLanguageB=(LinearLayout)findViewById(R.id.secchkMigLanguageB);
         linechkMigLanguageB=(View)findViewById(R.id.linechkMigLanguageB);
         VlblchkMigLanguageB=(TextView) findViewById(R.id.VlblchkMigLanguageB);
         chkchkMigLanguageB=(CheckBox) findViewById(R.id.chkchkMigLanguageB);
         secchkMigLanguageC=(LinearLayout)findViewById(R.id.secchkMigLanguageC);
         linechkMigLanguageC=(View)findViewById(R.id.linechkMigLanguageC);
         VlblchkMigLanguageC=(TextView) findViewById(R.id.VlblchkMigLanguageC);
         chkchkMigLanguageC=(CheckBox) findViewById(R.id.chkchkMigLanguageC);
         secchkMigLanguageD=(LinearLayout)findViewById(R.id.secchkMigLanguageD);
         linechkMigLanguageD=(View)findViewById(R.id.linechkMigLanguageD);
         VlblchkMigLanguageD=(TextView) findViewById(R.id.VlblchkMigLanguageD);
         chkchkMigLanguageD=(CheckBox) findViewById(R.id.chkchkMigLanguageD);
         chkchkMigLanguageD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     secMigLanguageOth.setVisibility(View.VISIBLE);
                     lineMigLanguageOth.setVisibility(View.VISIBLE);
                 }
                 else {
                     secMigLanguageOth.setVisibility(View.GONE);
                     lineMigLanguageOth.setVisibility(View.GONE);
                     txtMigLanguageOth.setText("");
                 }
             }
         });
         secMigLanguageOth=(LinearLayout)findViewById(R.id.secMigLanguageOth);
         lineMigLanguageOth=(View)findViewById(R.id.lineMigLanguageOth);
         VlblMigLanguageOth=(TextView) findViewById(R.id.VlblMigLanguageOth);
         txtMigLanguageOth=(AutoCompleteTextView) findViewById(R.id.txtMigLanguageOth);
         C.setupAutoComplete(TableName,txtMigLanguageOth,"MigLanguageOth"); //setup autocomplete view by event
         secMigOccupation=(LinearLayout)findViewById(R.id.secMigOccupation);
         lineMigOccupation=(View)findViewById(R.id.lineMigOccupation);
         VlblMigOccupation=(TextView) findViewById(R.id.VlblMigOccupation);
         spnMigOccupation=(Spinner) findViewById(R.id.spnMigOccupation);

         spnMigOccupation.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(Global_CodeList.Get_OCCUPATION())));

         secMigTotalUnion=(LinearLayout)findViewById(R.id.secMigTotalUnion);
         lineMigTotalUnion=(View)findViewById(R.id.lineMigTotalUnion);
         VlblMigTotalUnion=(TextView) findViewById(R.id.VlblMigTotalUnion);
         txtMigTotalUnion=(EditText) findViewById(R.id.txtMigTotalUnion);
         secMigFirstUnionDate=(LinearLayout)findViewById(R.id.secMigFirstUnionDate);
         lineMigFirstUnionDate=(View)findViewById(R.id.lineMigFirstUnionDate);
         VlblMigFirstUnionDate=(TextView) findViewById(R.id.VlblMigFirstUnionDate);
         dtpMigFirstUnionDate=(EditText) findViewById(R.id.dtpMigFirstUnionDate);
         secMigUnionRupture=(LinearLayout)findViewById(R.id.secMigUnionRupture);
         lineMigUnionRupture=(View)findViewById(R.id.lineMigUnionRupture);
         VlblMigUnionRupture = (TextView) findViewById(R.id.VlblMigUnionRupture);
         rdogrpMigUnionRupture = (RadioGroup) findViewById(R.id.rdogrpMigUnionRupture);
         rdoMigUnionRupture1 = (RadioButton) findViewById(R.id.rdoMigUnionRupture1);
         rdoMigUnionRupture2 = (RadioButton) findViewById(R.id.rdoMigUnionRupture2);
         secMigRuptureDate=(LinearLayout)findViewById(R.id.secMigRuptureDate);
         lineMigRuptureDate=(View)findViewById(R.id.lineMigRuptureDate);
         VlblMigRuptureDate=(TextView) findViewById(R.id.VlblMigRuptureDate);
         dtpMigRuptureDate=(EditText) findViewById(R.id.dtpMigRuptureDate);
         secMigNotDisUnionDate=(LinearLayout)findViewById(R.id.secMigNotDisUnionDate);
         lineMigNotDisUnionDate=(View)findViewById(R.id.lineMigNotDisUnionDate);
         VlblMigNotDisUnionDate=(TextView) findViewById(R.id.VlblMigNotDisUnionDate);
         dtpMigNotDisUnionDate=(EditText) findViewById(R.id.dtpMigNotDisUnionDate);


         secMigVDate=(LinearLayout)findViewById(R.id.secMigVDate);
         lineMigVDate=(View)findViewById(R.id.lineMigVDate);
         VlblMigVDate=(TextView) findViewById(R.id.VlblMigVDate);
         dtpMigVDate=(EditText) findViewById(R.id.dtpMigVDate);
         dtpMigVDate.setText(VISIT_DATE);
         secMigVDate.setVisibility(View.GONE);

         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         txtRnd.setText(ROUND);
         txtRnd.setEnabled(false);
         secMigNote=(LinearLayout)findViewById(R.id.secMigNote);
         lineMigNote=(View)findViewById(R.id.lineMigNote);
         VlblMigNote=(TextView) findViewById(R.id.VlblMigNote);
         txtMigNote=(EditText) findViewById(R.id.txtMigNote);

         secMigNote1=(LinearLayout)findViewById(R.id.secMigNote1);
         lineMigNote1=(View)findViewById(R.id.lineMigNote1);

         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
             //Show Mali Questions
             secMigBirthCountry.setVisibility(View.VISIBLE);
             secMigOriginCountry.setVisibility(View.VISIBLE);
             secMigWCRegionBurCity.setVisibility(View.VISIBLE);
             secMigBurCity.setVisibility(View.VISIBLE);
             secMigAbleToRW.setVisibility(View.VISIBLE);
             seclbl01.setVisibility(View.VISIBLE);
             secchkMigLanguageA.setVisibility(View.VISIBLE);
             secchkMigLanguageB.setVisibility(View.VISIBLE);
             secchkMigLanguageC.setVisibility(View.VISIBLE);
             secchkMigLanguageD.setVisibility(View.VISIBLE);
             secMigLanguageOth.setVisibility(View.GONE);
             secMigOccupation.setVisibility(View.VISIBLE);
             secMigTotalUnion.setVisibility(View.VISIBLE);
             secMigFirstUnionDate.setVisibility(View.VISIBLE);
             secMigUnionRupture.setVisibility(View.VISIBLE);
             secMigRuptureDate.setVisibility(View.VISIBLE);
             secMigNotDisUnionDate.setVisibility(View.VISIBLE);
             lineMigBirthCountry.setVisibility(View.VISIBLE);
             lineMigOriginCountry.setVisibility(View.VISIBLE);
             lineMigWCRegionBurCity.setVisibility(View.VISIBLE);
             lineMigBurCity.setVisibility(View.VISIBLE);
             lineMigAbleToRW.setVisibility(View.VISIBLE);
             linelbl01.setVisibility(View.VISIBLE);
             linechkMigLanguageA.setVisibility(View.VISIBLE);
             linechkMigLanguageB.setVisibility(View.VISIBLE);
             linechkMigLanguageC.setVisibility(View.VISIBLE);
             linechkMigLanguageD.setVisibility(View.VISIBLE);
             lineMigLanguageOth.setVisibility(View.GONE);
             lineMigOccupation.setVisibility(View.VISIBLE);
             lineMigTotalUnion.setVisibility(View.VISIBLE);
             lineMigFirstUnionDate.setVisibility(View.VISIBLE);
             lineMigUnionRupture.setVisibility(View.VISIBLE);
             lineMigRuptureDate.setVisibility(View.VISIBLE);
             lineMigNotDisUnionDate.setVisibility(View.VISIBLE);
         }
         else{
             secMigBirthCountry.setVisibility(View.GONE);
             secMigOriginCountry.setVisibility(View.GONE);
             secMigWCRegionBurCity.setVisibility(View.GONE);
             secMigBurCity.setVisibility(View.GONE);
             secMigAbleToRW.setVisibility(View.GONE);
             seclbl01.setVisibility(View.GONE);
             secchkMigLanguageA.setVisibility(View.GONE);
             secchkMigLanguageB.setVisibility(View.GONE);
             secchkMigLanguageC.setVisibility(View.GONE);
             secchkMigLanguageD.setVisibility(View.GONE);
             secMigLanguageOth.setVisibility(View.GONE);
             secMigOccupation.setVisibility(View.GONE);
             secMigTotalUnion.setVisibility(View.GONE);
             secMigFirstUnionDate.setVisibility(View.GONE);
             secMigUnionRupture.setVisibility(View.GONE);
             secMigRuptureDate.setVisibility(View.GONE);
             secMigNotDisUnionDate.setVisibility(View.GONE);
             lineMigBirthCountry.setVisibility(View.GONE);
             lineMigOriginCountry.setVisibility(View.GONE);
             lineMigWCRegionBurCity.setVisibility(View.GONE);
             lineMigBurCity.setVisibility(View.GONE);
             lineMigAbleToRW.setVisibility(View.GONE);
             linelbl01.setVisibility(View.GONE);
             linechkMigLanguageA.setVisibility(View.GONE);
             linechkMigLanguageB.setVisibility(View.GONE);
             linechkMigLanguageC.setVisibility(View.GONE);
             linechkMigLanguageD.setVisibility(View.GONE);
             lineMigLanguageOth.setVisibility(View.GONE);
             lineMigOccupation.setVisibility(View.GONE);
             lineMigTotalUnion.setVisibility(View.GONE);
             lineMigFirstUnionDate.setVisibility(View.GONE);
             lineMigUnionRupture.setVisibility(View.GONE);
             lineMigRuptureDate.setVisibility(View.GONE);
             lineMigNotDisUnionDate.setVisibility(View.GONE);
         }

     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Migration.this, e.getMessage());
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
         	Connection.MessageBox(Surv_Migration.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         tmpMigration_DataModel objSave = new tmpMigration_DataModel();
         objSave.setMigrationID(txtMigrationID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setMemID(txtMemID.getText().toString());
         String[] d_rdogrpMigEvType = new String[] {"20","21","51"};
         objSave.setMigEvType("");
         for (int i = 0; i < rdogrpMigEvType.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMigEvType.getChildAt(i);
             if (rb.isChecked()) objSave.setMigEvType(d_rdogrpMigEvType[i]);
         }

         objSave.setMigDate(dtpMigDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpMigDate.getText().toString()) : dtpMigDate.getText().toString());
         String[] d_rdogrpMigLocType = new String[] {"1","3","4","2"};
         objSave.setMigLocType("");
         for (int i = 0; i < rdogrpMigLocType.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMigLocType.getChildAt(i);
             if (rb.isChecked()) objSave.setMigLocType(d_rdogrpMigLocType[i]);
         }


//         objSave.setMigAdminL1(spnLayer1.getSelectedItemPosition() == 0 ? "" : spnLayer1.getSelectedItem().toString().split("-")[0]);
//         if(secMigAdminL1.isShown()){
//             objSave.setMigAdminL2(spnLayer2.getSelectedItemPosition() == 0 ? "" : spnLayer2.getSelectedItem().toString().split("-")[0]);
//             objSave.setMigAdminL3(spnLayer3.getSelectedItemPosition() == 0 ? "" : spnLayer3.getSelectedItem().toString().split("-")[0]);
//         }
         objSave.setMigAdminL1(txtMigAdminL1.getText().toString());
         objSave.setMigAdminL2(txtMigAdminL2.getText().toString());
         objSave.setMigAdminL3(txtMigAdminL3.getText().toString());
         objSave.setMigAdminL4(txtMigAdminL4.getText().toString());
         objSave.setMigAdminLArea(txtMigAdminLArea.getText().toString());
         objSave.setMigCountry(txtMigCountry.getText().toString());
         objSave.setMigBirthCountry(spnMigBirthCountry.getSelectedItemPosition() == 0 ? "" : spnMigBirthCountry.getSelectedItem().toString().split("-")[0]);
         objSave.setMigBirthCountryOth(txtMigBirthCountryOth.getText().toString());
         objSave.setMigOriginCountry(spnMigOriginCountry.getSelectedItemPosition() == 0 ? "" : spnMigOriginCountry.getSelectedItem().toString().split("-")[0]);
         objSave.setMigOriginCountryOth(txtMigOriginCountryOth.getText().toString());
         objSave.setMigWCRegionBurCity(spnMigWCRegionBurCity.getSelectedItemPosition() == 0 ? "" : spnMigWCRegionBurCity.getSelectedItem().toString().split("-")[0]);
         objSave.setMigBurCity(spnMigBurCity.getSelectedItemPosition() == 0 ? "" : spnMigBurCity.getSelectedItem().toString().split("-")[0]);
         String[] d_rdogrpMigReason = new String[] {"01","02","03","04","05","06","07","08","09","10","11","12","13","97","98","99"};
         objSave.setMigReason("");
         for (int i = 0; i < rdogrpMigReason.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMigReason.getChildAt(i);
             if (rb.isChecked()) objSave.setMigReason(d_rdogrpMigReason[i]);
         }

         objSave.setMigReasonOth(txtMigReasonOth.getText().toString());
         objSave.setMigMaritalChangeReason(spnMigMaritalChangeReason.getSelectedItemPosition() == 0 ? "" : spnMigMaritalChangeReason.getSelectedItem().toString().split("-")[0]);
         objSave.setMigMaritalChangeReasonOth(txtMigMaritalChangeReasonOth.getText().toString());
         String[] d_rdogrpMigAbleToRW = new String[] {"0","1"};
         objSave.setMigAbleToRW("");
         for (int i = 0; i < rdogrpMigAbleToRW.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMigAbleToRW.getChildAt(i);
             if (rb.isChecked()) objSave.setMigAbleToRW(d_rdogrpMigAbleToRW[i]);
         }

         objSave.setchkMigLanguageA((chkchkMigLanguageA.isChecked() ? "1" : (secchkMigLanguageA.isShown() ? "2" : "")));
         objSave.setchkMigLanguageB((chkchkMigLanguageB.isChecked() ? "1" : (secchkMigLanguageB.isShown() ? "2" : "")));
         objSave.setchkMigLanguageC((chkchkMigLanguageC.isChecked() ? "1" : (secchkMigLanguageC.isShown() ? "2" : "")));
         objSave.setchkMigLanguageD((chkchkMigLanguageD.isChecked() ? "1" : (secchkMigLanguageD.isShown() ? "2" : "")));
         objSave.setMigLanguageOth(txtMigLanguageOth.getText().toString());
         objSave.setMigOccupation(spnMigOccupation.getSelectedItemPosition() == 0 ? "" : spnMigOccupation.getSelectedItem().toString().split("-")[0]);
         objSave.setMigTotalUnion(txtMigTotalUnion.getText().toString());
         objSave.setMigFirstUnionDate(dtpMigFirstUnionDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpMigFirstUnionDate.getText().toString()) : dtpMigFirstUnionDate.getText().toString());
         String[] d_rdogrpMigUnionRupture = new String[] {"0","1"};
         objSave.setMigUnionRupture("");
         for (int i = 0; i < rdogrpMigUnionRupture.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMigUnionRupture.getChildAt(i);
             if (rb.isChecked()) objSave.setMigUnionRupture(d_rdogrpMigUnionRupture[i]);
         }

         objSave.setMigRuptureDate(dtpMigRuptureDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpMigRuptureDate.getText().toString()) : dtpMigRuptureDate.getText().toString());
         objSave.setMigNotDisUnionDate(dtpMigNotDisUnionDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpMigNotDisUnionDate.getText().toString()) : dtpMigNotDisUnionDate.getText().toString());
         objSave.setMigVDate(dtpMigVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpMigVDate.getText().toString()) : dtpMigVDate.getText().toString());
         objSave.setRnd(txtRnd.getText().toString());
         objSave.setMigNote(txtMigNote.getText().toString());
         objSave.setStartTime(STARTTIME);
         objSave.setEndTime(g.CurrentTime24());
         objSave.setDeviceID(DEVICEID);
         objSave.setEntryUser(ENTRYUSER); //from data entry user list
         objSave.setLat(MySharedPreferences.getValue(this, "lat"));
         objSave.setLon(MySharedPreferences.getValue(this, "lon"));

         String status ="";
         if (!EV_TYPE.equals("20") && !EV_TYPE.equals("21")) {
             //if (!EV_TYPE.equals("20")){
             status = objSave.SaveUpdateData(this);
         } else {
             Intent returnIntent = new Intent();
             returnIntent.putExtra("res", "");
             setResult(Activity.RESULT_OK, returnIntent);

             Bundle IDbundle = new Bundle();
             IDbundle.putString("MigrationID", txtMigrationID.getText().toString());
             IDbundle.putString("MemID", txtMemID.getText().toString());
             IDbundle.putString("HHID", HHID);
             IDbundle.putString("HHNO", HHNO);
             IDbundle.putString("evtype", EV_TYPE);
             IDbundle.putString("evdate", Global.DateConvertYMD(dtpMigDate.getText().toString()));
             IDbundle.putString("vdate", Global.DateConvertYMD(dtpMigVDate.getText().toString()));
             IDbundle.putString("round", ROUND);
             IDbundle.putString("dod", "");
             IDbundle.putString("MoID", "");
             IDbundle.putString("LiveBirthID", "");
             IDbundle.putString("TmpMigrationID", "");
             IDbundle.putSerializable("tmpMigration", objSave);

             Intent intent = new Intent(getApplicationContext(), Surv_Member.class);
             intent.putExtras(IDbundle);
             startActivityForResult(intent, 1);
             finish();
         }

         if(status.length()==0) {

             if (EV_TYPE.equals("51")) {
                 C.SaveData("Update tmpMember set Active='2',Upload='2',modifydate='"+ Global.DateTimeNowYMDHMS() +"', ExType='"+ EV_TYPE +"' Where MemID='" + MEM_ID + "'");
                 C.SaveData("Update tmpMemberMove set Active='2',Upload='2',modifydate='"+ Global.DateTimeNowYMDHMS() +"', MExType='" + EV_TYPE + "', MExDate='" + Global.DateConvertYMD(dtpMigDate.getText().toString()) +"' Where  HHID='" + HHID + "' and MemID='" + MEM_ID + "'");

                 Intent returnIntent = new Intent();
                 returnIntent.putExtra("res", "");
                 setResult(Activity.RESULT_OK, returnIntent);
                 Toast.makeText(Surv_Migration.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
                 finish();
             }

         }
         else{
             Connection.MessageBox(Surv_Migration.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Migration.this, e.getMessage());
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
         if(txtMigrationID.getText().toString().length()==0 & secMigrationID.isShown())
           {
             ValidationMsg += "\nRequired field: Migration in/out internal ID.";
             secMigrationID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
           {
             ValidationMsg += "\nRequired field: Household ID.";
             secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         /*if(txtMemID.getText().toString().length()==0 & secMemID.isShown())
           {
             ValidationMsg += "\nRequired field: Member internal ID.";
             secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/
         if(!rdoMigEvType1.isChecked() & !rdoMigEvType2.isChecked() & !rdoMigEvType3.isChecked() & secMigEvType.isShown())
           {
             ValidationMsg += "\nRequired field: Event Type of migration.";
             secMigEvType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpMigDate.getText().toString());
         if(DV.length()!=0 & secMigDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: When did you move?.";
             secMigDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1) & !rdoMigLocType1.isChecked() & !rdoMigLocType2.isChecked() & secMigLocType.isShown())
           {
             ValidationMsg += "\nRequired field: Where did you move/come from?  - (internal, external).";
             secMigLocType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1) & !rdoMigLocType2.isChecked() & !rdoMigLocType3.isChecked() & !rdoMigLocType4.isChecked() & secMigLocType.isShown())
         {
             ValidationMsg += "\nRequired field: Where did you move/come from?  - (internal, external).";
             secMigLocType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtMigAdminL1.getText().toString().length() == 0 & secMigAdminL1.isShown() & rdoMigLocType3.isChecked())
           {
             ValidationMsg += "\nRequired field: Inside country: Admin level 1.";
             secMigAdminL1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
//         if(txtMigAdminL2.getText().toString().length()==0 & secMigAdminL2.isShown())
//           {
//             ValidationMsg += "\nRequired field: Inside country: Admin level 2.";
//             secMigAdminL2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
//         if(txtMigAdminL3.getText().toString().length()==0 & secMigAdminL3.isShown())
//           {
//             ValidationMsg += "\nRequired field: Inside country: Admin level 3.";
//             secMigAdminL3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
         if(txtMigAdminL4.getText().toString().length()==0 & secMigAdminL4.isShown() & rdoMigLocType4.isChecked())
           {
             ValidationMsg += "\nRequired field: Inside country: Admin level 4.";
             secMigAdminL4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
//         if(txtMigAdminLArea.getText().toString().length()==0 & secMigAdminLArea.isShown())
//           {
//             ValidationMsg += "\nRequired field: Area /Village.";
//             secMigAdminLArea.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
         if(txtMigCountry.getText().toString().length()==0 & secMigCountry.isShown())
           {
             ValidationMsg += "\nRequired field: Outside Country.";
             secMigCountry.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnMigBirthCountry.getSelectedItemPosition()==0  & secMigBirthCountry.isShown())
         {
             ValidationMsg += "\nRequired field: Country of birth.";
             secMigBirthCountry.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtMigBirthCountryOth.getText().toString().length()==0 & secMigBirthCountryOth.isShown())
         {
             ValidationMsg += "\nRequired field: Specify Others.";
             secMigBirthCountryOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnMigOriginCountry.getSelectedItemPosition()==0  & secMigOriginCountry.isShown())
         {
             ValidationMsg += "\nRequired field: Country of origin.";
             secMigOriginCountry.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtMigOriginCountryOth.getText().toString().length()==0 & secMigOriginCountryOth.isShown())
         {
             ValidationMsg += "\nRequired field: Specify Others.";
             secMigOriginCountryOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnMigWCRegionBurCity.getSelectedItemPosition()==0  & secMigWCRegionBurCity.isShown())
         {
             ValidationMsg += "\nRequired field: City in the west central region of Burkina.";
             secMigWCRegionBurCity.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnMigBurCity.getSelectedItemPosition()==0  & secMigBurCity.isShown())
         {
             ValidationMsg += "\nRequired field: Burkinas city.";
             secMigBurCity.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!rdoMigReason1.isChecked() & !rdoMigReason2.isChecked() & !rdoMigReason3.isChecked() & !rdoMigReason4.isChecked() & !rdoMigReason5.isChecked() & !rdoMigReason6.isChecked() & !rdoMigReason7.isChecked() & !rdoMigReason8.isChecked() & !rdoMigReason9.isChecked() & !rdoMigReason10.isChecked() & !rdoMigReason11.isChecked() & !rdoMigReason12.isChecked() & !rdoMigReason13.isChecked()  & !rdoMigReason97.isChecked() & !rdoMigReason98.isChecked() & !rdoMigReason99.isChecked() & secMigReason.isShown())
           {
             ValidationMsg += "\nRequired field: What was the reason for the migration?.";
             secMigReason.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMigReasonOth.getText().toString().length()==0 & secMigReasonOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other specify.";
             secMigReasonOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnMigMaritalChangeReason.getSelectedItemPosition()==0  & secMigMaritalChangeReason.isShown())
         {
             ValidationMsg += "\nRequired field: If reason is matrimonial status change.";
             secMigMaritalChangeReason.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtMigMaritalChangeReasonOth.getText().toString().length()==0 & secMigMaritalChangeReasonOth.isShown())
         {
             ValidationMsg += "\nRequired field: Others Specify.";
             secMigMaritalChangeReasonOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!rdoMigAbleToRW1.isChecked() & !rdoMigAbleToRW2.isChecked() & secMigAbleToRW.isShown())
         {
             ValidationMsg += "\nRequired field: Are you able to read or write?.";
             secMigAbleToRW.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtMigLanguageOth.getText().toString().length()==0 & secMigLanguageOth.isShown())
         {
             ValidationMsg += "\nRequired field: Specify Others.";
             secMigLanguageOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnMigOccupation.getSelectedItemPosition()==0  & secMigOccupation.isShown())
         {
             ValidationMsg += "\nRequired field: Your occupation during the last 12 months.";
             secMigOccupation.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtMigTotalUnion.getText().toString().length()==0 & secMigTotalUnion.isShown())
         {
             ValidationMsg += "\nRequired field: Total number of unions/marriage already contracted by the migrant.";
             secMigTotalUnion.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         DV = Global.DateValidate(dtpMigVDate.getText().toString());
         if(DV.length()!=0 & secMigVDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Visit date.";
             secMigVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             ValidationMsg += "\nRequired field: Round number.";
             secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         /*if(txtMigNote.getText().toString().length()==0 & secMigNote.isShown())
           {
             ValidationMsg += "\nRequired field: Note.";
             secMigNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secMigrationID.setBackgroundColor(Color.WHITE);
             secHHID.setBackgroundColor(Color.WHITE);
             secMemID.setBackgroundColor(Color.WHITE);
             secMigEvType.setBackgroundColor(Color.WHITE);
             secMigDate.setBackgroundColor(Color.WHITE);
             secMigLocType.setBackgroundColor(Color.WHITE);
             secMigAdminL1.setBackgroundColor(Color.WHITE);
             secMigAdminL2.setBackgroundColor(Color.WHITE);
             secMigAdminL3.setBackgroundColor(Color.WHITE);
             secMigAdminL4.setBackgroundColor(Color.WHITE);
             secMigAdminLArea.setBackgroundColor(Color.WHITE);
             secMigCountry.setBackgroundColor(Color.WHITE);
         secMigBirthCountry.setBackgroundColor(Color.WHITE);
         secMigBirthCountryOth.setBackgroundColor(Color.WHITE);
         secMigOriginCountry.setBackgroundColor(Color.WHITE);
         secMigOriginCountryOth.setBackgroundColor(Color.WHITE);
         secMigWCRegionBurCity.setBackgroundColor(Color.WHITE);
         secMigBurCity.setBackgroundColor(Color.WHITE);
             secMigReason.setBackgroundColor(Color.WHITE);
             secMigReasonOth.setBackgroundColor(Color.WHITE);
         secMigAbleToRW.setBackgroundColor(Color.WHITE);
         secMigLanguageOth.setBackgroundColor(Color.WHITE);
         secMigOccupation.setBackgroundColor(Color.WHITE);
         secMigTotalUnion.setBackgroundColor(Color.WHITE);
         secMigFirstUnionDate.setBackgroundColor(Color.WHITE);
         secMigUnionRupture.setBackgroundColor(Color.WHITE);
         secMigRuptureDate.setBackgroundColor(Color.WHITE);
         secMigNotDisUnionDate.setBackgroundColor(Color.WHITE);
             secMigVDate.setBackgroundColor(Color.WHITE);
             secMigMaritalChangeReason.setBackgroundColor(Color.WHITE);
         secMigMaritalChangeReasonOth.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
             secMigNote.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String MigrationID)
     {
       try
        {     
           RadioButton rb;
           Migration_DataModel d = new Migration_DataModel();
           String SQL = "Select * from "+ TableName +"  Where MigrationID='"+ MigrationID +"'";
           List<Migration_DataModel> data = d.SelectAll(this, SQL);
           for(Migration_DataModel item : data){
             txtMigrationID.setText(item.getMigrationID());
             txtHHID.setText(item.getHHID());
             txtMemID.setText(item.getMemID());
             String[] d_rdogrpMigEvType = new String[] {"20","21","51"};
             for (int i = 0; i < d_rdogrpMigEvType.length; i++)
             {
                 if (String.valueOf(item.getMigEvType()).equals(String.valueOf(d_rdogrpMigEvType[i])))
                 {
                     rb = (RadioButton)rdogrpMigEvType.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             dtpMigDate.setText(item.getMigDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getMigDate()));
             String[] d_rdogrpMigLocType = new String[] {"1","3","4","2"};
             for (int i = 0; i < d_rdogrpMigLocType.length; i++)
             {
                 if (String.valueOf(item.getMigLocType()).equals(String.valueOf(d_rdogrpMigLocType[i])))
                 {
                     rb = (RadioButton)rdogrpMigLocType.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
//               spnLayer1.setSelection(Global.SpinnerItemPositionAnyLength(spnLayer1, String.valueOf(item.getMigAdminL1())));
//               spnLayer2.setSelection(Global.SpinnerItemPositionAnyLength(spnLayer2, String.valueOf(item.getMigAdminL2())));
//               spnLayer3.setSelection(Global.SpinnerItemPositionAnyLength(spnLayer3, String.valueOf(item.getMigAdminL3())));
             txtMigAdminL1.setText(item.getMigAdminL1());
             txtMigAdminL2.setText(item.getMigAdminL2());
             txtMigAdminL3.setText(item.getMigAdminL3());
             txtMigAdminL4.setText(item.getMigAdminL4());
             txtMigAdminLArea.setText(item.getMigAdminLArea());
             txtMigCountry.setText(item.getMigCountry());
               spnMigBirthCountry.setSelection(Global.SpinnerItemPositionAnyLength(spnMigBirthCountry, String.valueOf(item.getMigBirthCountry())));
               txtMigBirthCountryOth.setText(item.getMigBirthCountryOth());
               spnMigOriginCountry.setSelection(Global.SpinnerItemPositionAnyLength(spnMigOriginCountry, String.valueOf(item.getMigOriginCountry())));
               txtMigOriginCountryOth.setText(item.getMigOriginCountryOth());
               spnMigWCRegionBurCity.setSelection(Global.SpinnerItemPositionAnyLength(spnMigWCRegionBurCity, String.valueOf(item.getMigWCRegionBurCity())));
               spnMigBurCity.setSelection(Global.SpinnerItemPositionAnyLength(spnMigBurCity, String.valueOf(item.getMigBurCity())));
               String[] d_rdogrpMigReason = new String[] {"01","02","03","04","05","06","07","08","09","10","11","12","13","97","98","99"};
             for (int i = 0; i < d_rdogrpMigReason.length; i++)
             {
                 if (String.valueOf(item.getMigReason()).equals(String.valueOf(d_rdogrpMigReason[i])))
                 {
                     rb = (RadioButton)rdogrpMigReason.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtMigReasonOth.setText(item.getMigReasonOth());
             spnMigMaritalChangeReason.setSelection(Global.SpinnerItemPositionAnyLength(spnMigMaritalChangeReason, String.valueOf(item.getMigMaritalChangeReason())));
            txtMigMaritalChangeReasonOth.setText(item.getMigMaritalChangeReasonOth());
               String[] d_rdogrpMigAbleToRW = new String[] {"0","1"};
               for (int i = 0; i < d_rdogrpMigAbleToRW.length; i++)
               {
                   if (String.valueOf(item.getMigAbleToRW()).equals(String.valueOf(d_rdogrpMigAbleToRW[i])))
                   {
                       rb = (RadioButton)rdogrpMigAbleToRW.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
               if(String.valueOf(item.getchkMigLanguageA()).equals("1"))
               {
                   chkchkMigLanguageA.setChecked(true);
               }
               else if(String.valueOf(item.getchkMigLanguageA()).equals("2"))
               {
                   chkchkMigLanguageA.setChecked(false);
               }
               if(String.valueOf(item.getchkMigLanguageB()).equals("1"))
               {
                   chkchkMigLanguageB.setChecked(true);
               }
               else if(String.valueOf(item.getchkMigLanguageB()).equals("2"))
               {
                   chkchkMigLanguageB.setChecked(false);
               }
               if(String.valueOf(item.getchkMigLanguageC()).equals("1"))
               {
                   chkchkMigLanguageC.setChecked(true);
               }
               else if(String.valueOf(item.getchkMigLanguageC()).equals("2"))
               {
                   chkchkMigLanguageC.setChecked(false);
               }
               if(String.valueOf(item.getchkMigLanguageD()).equals("1"))
               {
                   chkchkMigLanguageD.setChecked(true);
               }
               else if(String.valueOf(item.getchkMigLanguageD()).equals("2"))
               {
                   chkchkMigLanguageD.setChecked(false);
               }
               txtMigLanguageOth.setText(item.getMigLanguageOth());
               spnMigOccupation.setSelection(Global.SpinnerItemPositionAnyLength(spnMigOccupation, String.valueOf(item.getMigOccupation())));
               txtMigTotalUnion.setText(item.getMigTotalUnion());
               dtpMigFirstUnionDate.setText(item.getMigFirstUnionDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getMigFirstUnionDate()));
               String[] d_rdogrpMigUnionRupture = new String[] {"0","1"};
               for (int i = 0; i < d_rdogrpMigUnionRupture.length; i++)
               {
                   if (String.valueOf(item.getMigUnionRupture()).equals(String.valueOf(d_rdogrpMigUnionRupture[i])))
                   {
                       rb = (RadioButton)rdogrpMigUnionRupture.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
               dtpMigRuptureDate.setText(item.getMigRuptureDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getMigRuptureDate()));
               dtpMigNotDisUnionDate.setText(item.getMigNotDisUnionDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getMigNotDisUnionDate()));
               dtpMigVDate.setText(item.getMigVDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getMigVDate()));
             txtRnd.setText(item.getRnd());
             txtMigNote.setText(item.getMigNote());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Migration.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpMigDate);
      if (VariableID.equals("btnMigDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpMigDate);
      }
      else if (VariableID.equals("dtpMigFirstUnionDate")) {
          dtpDate = (EditText)findViewById(R.id.dtpMigFirstUnionDate);
      }
      else if (VariableID.equals("dtpMigRuptureDate")) {
          dtpDate = (EditText)findViewById(R.id.dtpMigRuptureDate);
      }
      else if (VariableID.equals("dtpMigNotDisUnionDate")) {
          dtpDate = (EditText)findViewById(R.id.dtpMigNotDisUnionDate);
      }
      else if (VariableID.equals("btnMigVDate")) {
          dtpDate = (EditText)findViewById(R.id.dtpMigVDate);
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