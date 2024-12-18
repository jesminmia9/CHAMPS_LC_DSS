
 package forms_activity;


 import android.annotation.SuppressLint;
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
 import forms_datamodel.CONTRACEPTIVE_BEHAVIOUR_DataModel;
 import Utility.*;
 import Common.*;
 import android.widget.Toast;
 import android.text.TextWatcher;
 import android.widget.CompoundButton;
 import android.text.Editable;

 import org.icddrb.champs_lc_dss.R;
 import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

 public class CONTRACEPTIVE_BEHAVIOUR extends AppCompatActivity {
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
    LinearLayout secuuid;
    View lineuuid;
    TextView lbluuid;
    TextView Vlbluuid;
    EditText txtuuid;
    LinearLayout secMemID;
    View lineMemID;
    TextView lblMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secHHID;
    View lineHHID;
    TextView lblHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout seclbl1;
    View linelbl1;
    LinearLayout secS1Q1;
    View lineS1Q1;
    TextView lblS1Q1;
    TextView VlblS1Q1;
    RadioGroup rdogrpS1Q1;
    RadioButton rdoS1Q11;
    RadioButton rdoS1Q12;
    LinearLayout seclblq1;
    View linelblq1;
    LinearLayout secS1Q11;
    View lineS1Q11;
    TextView lblS1Q11;
    TextView VlblS1Q11;
    CheckBox chkS1Q11;
    LinearLayout secS1Q12;
    View lineS1Q12;
    TextView lblS1Q12;
    TextView VlblS1Q12;
    CheckBox chkS1Q12;
    LinearLayout secS1Q13;
    View lineS1Q13;
    TextView lblS1Q13;
    TextView VlblS1Q13;
    CheckBox chkS1Q13;
    LinearLayout secS1Q14;
    View lineS1Q14;
    TextView lblS1Q14;
    TextView VlblS1Q14;
    CheckBox chkS1Q14;
    LinearLayout secS1Q15;
    View lineS1Q15;
    TextView lblS1Q15;
    TextView VlblS1Q15;
    CheckBox chkS1Q15;
    LinearLayout secS1Q16;
    View lineS1Q16;
    TextView lblS1Q16;
    TextView VlblS1Q16;
    CheckBox chkS1Q16;
    LinearLayout secS1Q17;
    View lineS1Q17;
    TextView lblS1Q17;
    TextView VlblS1Q17;
    CheckBox chkS1Q17;
    LinearLayout secS1Q17_1;
    View lineS1Q17_1;
    TextView lblS1Q17_1;
    TextView VlblS1Q17_1;
    AutoCompleteTextView txtS1Q17_1;
    LinearLayout secS1Q2;
    View lineS1Q2;
    LinearLayout secS1Q21;
    View lineS1Q21;
    TextView lblS1Q21;
    TextView VlblS1Q21;
    CheckBox chkS1Q21;
    LinearLayout secS1Q22;
    View lineS1Q22;
    TextView lblS1Q22;
    TextView VlblS1Q22;
    CheckBox chkS1Q22;
    LinearLayout secS1Q23;
    View lineS1Q23;
    TextView lblS1Q23;
    TextView VlblS1Q23;
    CheckBox chkS1Q23;
    LinearLayout secS1Q24;
    View lineS1Q24;
    TextView lblS1Q24;
    TextView VlblS1Q24;
    CheckBox chkS1Q24;
    LinearLayout secS1Q25;
    View lineS1Q25;
    TextView lblS1Q25;
    TextView VlblS1Q25;
    CheckBox chkS1Q25;
    LinearLayout secS1Q26;
    View lineS1Q26;
    TextView lblS1Q26;
    TextView VlblS1Q26;
    CheckBox chkS1Q26;
    LinearLayout secS1Q27;
    View lineS1Q27;
    TextView lblS1Q27;
    TextView VlblS1Q27;
    CheckBox chkS1Q27;
    LinearLayout secS1Q28;
    View lineS1Q28;
    TextView lblS1Q28;
    TextView VlblS1Q28;
    CheckBox chkS1Q28;
    LinearLayout secS1Q28_1;
    View lineS1Q28_1;
    TextView lblS1Q28_1;
    TextView VlblS1Q28_1;
    AutoCompleteTextView txtS1Q28_1;
    LinearLayout secS1Q3;
    View lineS1Q3;
    TextView lblS1Q3;
    TextView VlblS1Q3;
    RadioGroup rdogrpS1Q3;
    RadioButton rdoS1Q31;
    RadioButton rdoS1Q32;
    LinearLayout seclblq31;
    View linelblq31;
    LinearLayout secS1Q31;
    View lineS1Q31;
    TextView lblS1Q31;
    TextView VlblS1Q31;
    CheckBox chkS1Q31;
    LinearLayout secS1Q32;
    View lineS1Q32;
    TextView lblS1Q32;
    TextView VlblS1Q32;
    CheckBox chkS1Q32;
    LinearLayout secS1Q33;
    View lineS1Q33;
    TextView lblS1Q33;
    TextView VlblS1Q33;
    CheckBox chkS1Q33;
    LinearLayout secS1Q34;
    View lineS1Q34;
    TextView lblS1Q34;
    TextView VlblS1Q34;
    CheckBox chkS1Q34;
    LinearLayout secS1Q35;
    View lineS1Q35;
    TextView lblS1Q35;
    TextView VlblS1Q35;
    CheckBox chkS1Q35;
    LinearLayout secS1Q36;
    View lineS1Q36;
    TextView lblS1Q36;
    TextView VlblS1Q36;
    CheckBox chkS1Q36;
    LinearLayout secS1Q37;
    View lineS1Q37;
    TextView lblS1Q37;
    TextView VlblS1Q37;
    CheckBox chkS1Q37;
    LinearLayout secS1Q38;
    View lineS1Q38;
    TextView lblS1Q38;
    TextView VlblS1Q38;
    CheckBox chkS1Q38;
    LinearLayout secS1Q39;
    View lineS1Q39;
    TextView lblS1Q39;
    TextView VlblS1Q39;
    CheckBox chkS1Q39;
    LinearLayout secS1Q310;
    View lineS1Q310;
    TextView lblS1Q310;
    TextView VlblS1Q310;
    CheckBox chkS1Q310;
    LinearLayout secS1Q311;
    View lineS1Q311;
    TextView lblS1Q311;
    TextView VlblS1Q311;
    CheckBox chkS1Q311;
    LinearLayout secS1Q311_1;
    View lineS1Q311_1;
    TextView lblS1Q311_1;
    TextView VlblS1Q311_1;
    AutoCompleteTextView txtS1Q311_1;
    LinearLayout seclbls2;
    View linelbls2;
    LinearLayout secS2Q4;
    View lineS2Q4;
    TextView lblS2Q4;
    TextView VlblS2Q4;
    RadioGroup rdogrpS2Q4;
    RadioButton rdoS2Q41;
    RadioButton rdoS2Q42;
    LinearLayout seclblQ5;
    View linelblQ5;
    LinearLayout secS2Q51;
    View lineS2Q51;
    TextView lblS2Q51;
    TextView VlblS2Q51;
    CheckBox chkS2Q51;
    LinearLayout secS2Q52;
    View lineS2Q52;
    TextView lblS2Q52;
    TextView VlblS2Q52;
    CheckBox chkS2Q52;
    LinearLayout secS2Q53;
    View lineS2Q53;
    TextView lblS2Q53;
    TextView VlblS2Q53;
    CheckBox chkS2Q53;
    LinearLayout secS2Q54;
    View lineS2Q54;
    TextView lblS2Q54;
    TextView VlblS2Q54;
    CheckBox chkS2Q54;
    LinearLayout secS2Q55;
    View lineS2Q55;
    TextView lblS2Q55;
    TextView VlblS2Q55;
    CheckBox chkS2Q55;
    LinearLayout secS2Q6;
    View lineS2Q6;
    TextView lblS2Q6;
    TextView VlblS2Q6;
    RadioGroup rdogrpS2Q6;
    RadioButton rdoS2Q61;
    RadioButton rdoS2Q62;
    RadioButton rdoS2Q63;
    RadioButton rdoS2Q64;
    LinearLayout secS2Q7;
    View lineS2Q7;
    TextView lblS2Q7;
    TextView VlblS2Q7;
    RadioGroup rdogrpS2Q7;
    RadioButton rdoS2Q71;
    RadioButton rdoS2Q72;
    RadioButton rdoS2Q73;
    RadioButton rdoS2Q74;
    LinearLayout seclblQ8;
    View linelblQ8;
    LinearLayout secS2Q81;
    View lineS2Q81;
    TextView lblS2Q81;
    TextView VlblS2Q81;
    CheckBox chkS2Q81;
    LinearLayout secS2Q82;
    View lineS2Q82;
    TextView lblS2Q82;
    TextView VlblS2Q82;
    CheckBox chkS2Q82;
    LinearLayout secS2Q83;
    View lineS2Q83;
    TextView lblS2Q83;
    TextView VlblS2Q83;
    CheckBox chkS2Q83;
    LinearLayout secS2Q84;
    View lineS2Q84;
    TextView lblS2Q84;
    TextView VlblS2Q84;
    CheckBox chkS2Q84;
    LinearLayout secS2Q84_1;
    View lineS2Q84_1;
    TextView lblS2Q84_1;
    TextView VlblS2Q84_1;
    AutoCompleteTextView txtS2Q84_1;
    LinearLayout seclbls3;
    View linelbls3;
    LinearLayout secS3Q9;
    View lineS3Q9;
    TextView lblS3Q9;
    TextView VlblS3Q9;
    RadioGroup rdogrpS3Q9;
    RadioButton rdoS3Q91;
    RadioButton rdoS3Q92;
    LinearLayout seclblQ9;
    View linelblQ9;
    LinearLayout secS3Q91;
    View lineS3Q91;
    TextView lblS3Q91;
    TextView VlblS3Q91;
    CheckBox chkS3Q91;
    LinearLayout secS3Q92;
    View lineS3Q92;
    TextView lblS3Q92;
    TextView VlblS3Q92;
    CheckBox chkS3Q92;
    LinearLayout secS3Q93;
    View lineS3Q93;
    TextView lblS3Q93;
    TextView VlblS3Q93;
    CheckBox chkS3Q93;
    LinearLayout secS3Q94;
    View lineS3Q94;
    TextView lblS3Q94;
    TextView VlblS3Q94;
    CheckBox chkS3Q94;
    LinearLayout secS3Q95;
    View lineS3Q95;
    TextView lblS3Q95;
    TextView VlblS3Q95;
    CheckBox chkS3Q95;
    LinearLayout secS3Q96;
    View lineS3Q96;
    TextView lblS3Q96;
    TextView VlblS3Q96;
    CheckBox chkS3Q96;
    LinearLayout seclbl97;
    View linelbl97;
    LinearLayout secS3Q97;
    View lineS3Q97;
    TextView lblS3Q97;
    TextView VlblS3Q97;
    CheckBox chkS3Q97;
    LinearLayout secS3Q98;
    View lineS3Q98;
    TextView lblS3Q98;
    TextView VlblS3Q98;
    CheckBox chkS3Q98;
    LinearLayout secS3Q99;
    View lineS3Q99;
    TextView lblS3Q99;
    TextView VlblS3Q99;
    CheckBox chkS3Q99;
    LinearLayout secS3Q910;
    View lineS3Q910;
    TextView lblS3Q910;
    TextView VlblS3Q910;
    CheckBox chkS3Q910;
    LinearLayout secS3Q911;
    View lineS3Q911;
    TextView lblS3Q911;
    TextView VlblS3Q911;
    CheckBox chkS3Q911;
    LinearLayout secS3Q912;
    View lineS3Q912;
    TextView lblS3Q912;
    TextView VlblS3Q912;
    CheckBox chkS3Q912;
    LinearLayout secS3Q913;
    View lineS3Q913;
    TextView lblS3Q913;
    TextView VlblS3Q913;
    CheckBox chkS3Q913;
    LinearLayout secS3Q914;
    View lineS3Q914;
    TextView lblS3Q914;
    TextView VlblS3Q914;
    CheckBox chkS3Q914;
    LinearLayout secS3Q914_1;
    View lineS3Q914_1;
    TextView lblS3Q914_1;
    TextView VlblS3Q914_1;
    AutoCompleteTextView txtS3Q914_1;
    LinearLayout secS3Q10;
    View lineS3Q10;
    TextView lblS3Q10;
    TextView VlblS3Q10;
    Spinner spnS3Q10;
    LinearLayout secS3Q10_1;
    View lineS3Q10_1;
    TextView lblS3Q10_1;
    TextView VlblS3Q10_1;
    AutoCompleteTextView txtS3Q10_1;
    LinearLayout secS3Q11;
    View lineS3Q11;
    TextView lblS3Q11;
    TextView VlblS3Q11;
    RadioGroup rdogrpS3Q11;
    RadioButton rdoS3Q111;
    RadioButton rdoS3Q112;
    RadioButton rdoS3Q113;
    LinearLayout seclblS3Q11;
    View linelblS3Q11;
    LinearLayout secS3Q111;
    View lineS3Q111;
    TextView lblS3Q111;
    TextView VlblS3Q111;
    CheckBox chkS3Q111;
    LinearLayout secS3Q112;
    View lineS3Q112;
    TextView lblS3Q112;
    TextView VlblS3Q112;
    CheckBox chkS3Q112;
    LinearLayout secS3Q113;
    View lineS3Q113;
    TextView lblS3Q113;
    TextView VlblS3Q113;
    CheckBox chkS3Q113;
    LinearLayout secS3Q114;
    View lineS3Q114;
    TextView lblS3Q114;
    TextView VlblS3Q114;
    CheckBox chkS3Q114;
    LinearLayout secS3Q115;
    View lineS3Q115;
    TextView lblS3Q115;
    TextView VlblS3Q115;
    CheckBox chkS3Q115;
    LinearLayout secS3Q116;
    View lineS3Q116;
    TextView lblS3Q116;
    TextView VlblS3Q116;
    CheckBox chkS3Q116;
    LinearLayout secS3Q117;
    View lineS3Q117;
    TextView lblS3Q117;
    TextView VlblS3Q117;
    CheckBox chkS3Q117;
    LinearLayout secS3Q117_1;
    View lineS3Q117_1;
    TextView lblS3Q117_1;
    TextView VlblS3Q117_1;
    AutoCompleteTextView txtS3Q117_1;

    static int MODULEID   = 0;
    static int LANGUAGEID = 0;
    static String TableName;

    static String STARTTIME = "";
    static String DEVICEID  = "";
    static String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
    static String UUID = "";
    static String MEMID = "";
    static String HHID = "";
    static String VISIT_DATE = "";

 @SuppressLint("ClickableViewAccessibility")
 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.contraceptive_behaviour);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         UUID = IDbundle.getString("uuid");
         HHID = IDbundle.getString("HHID");
         MEMID = IDbundle.getString("MemID");
         VISIT_DATE = IDbundle.getString("visitdate"); //dd/mm/yyyy


         TableName = "CONTRACEPTIVE_BEHAVIOUR";
         MODULEID = 55;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(CONTRACEPTIVE_BEHAVIOUR.this);
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
        Connection.LocalizeLanguage(CONTRACEPTIVE_BEHAVIOUR.this, MODULEID, LANGUAGEID);





         //Hide all skip variables
         seclblq1.setVisibility(View.GONE);
         linelblq1.setVisibility(View.GONE);
         secS1Q11.setVisibility(View.GONE);
         lineS1Q11.setVisibility(View.GONE);
         secS1Q12.setVisibility(View.GONE);
         lineS1Q12.setVisibility(View.GONE);
         secS1Q13.setVisibility(View.GONE);
         lineS1Q13.setVisibility(View.GONE);
         secS1Q14.setVisibility(View.GONE);
         lineS1Q14.setVisibility(View.GONE);
         secS1Q15.setVisibility(View.GONE);
         lineS1Q15.setVisibility(View.GONE);
         secS1Q16.setVisibility(View.GONE);
         lineS1Q16.setVisibility(View.GONE);
         secS1Q17.setVisibility(View.GONE);
         lineS1Q17.setVisibility(View.GONE);
         secS1Q17_1.setVisibility(View.GONE);
         lineS1Q17_1.setVisibility(View.GONE);
         secS1Q2.setVisibility(View.GONE);
         lineS1Q2.setVisibility(View.GONE);
         secS1Q21.setVisibility(View.GONE);
         lineS1Q21.setVisibility(View.GONE);
         secS1Q22.setVisibility(View.GONE);
         lineS1Q22.setVisibility(View.GONE);
         secS1Q23.setVisibility(View.GONE);
         lineS1Q23.setVisibility(View.GONE);
         secS1Q24.setVisibility(View.GONE);
         lineS1Q24.setVisibility(View.GONE);
         secS1Q25.setVisibility(View.GONE);
         lineS1Q25.setVisibility(View.GONE);
         secS1Q26.setVisibility(View.GONE);
         lineS1Q26.setVisibility(View.GONE);
         secS1Q27.setVisibility(View.GONE);
         lineS1Q27.setVisibility(View.GONE);
         secS1Q28.setVisibility(View.GONE);
         lineS1Q28.setVisibility(View.GONE);
         secS1Q28_1.setVisibility(View.GONE);
         lineS1Q28_1.setVisibility(View.GONE);
         secS1Q3.setVisibility(View.GONE);
         lineS1Q3.setVisibility(View.GONE);
         seclblq31.setVisibility(View.GONE);
         linelblq31.setVisibility(View.GONE);
         secS1Q31.setVisibility(View.GONE);
         lineS1Q31.setVisibility(View.GONE);
         secS1Q32.setVisibility(View.GONE);
         lineS1Q32.setVisibility(View.GONE);
         secS1Q33.setVisibility(View.GONE);
         lineS1Q33.setVisibility(View.GONE);
         secS1Q34.setVisibility(View.GONE);
         lineS1Q34.setVisibility(View.GONE);
         secS1Q35.setVisibility(View.GONE);
         lineS1Q35.setVisibility(View.GONE);
         secS1Q36.setVisibility(View.GONE);
         lineS1Q36.setVisibility(View.GONE);
         secS1Q37.setVisibility(View.GONE);
         lineS1Q37.setVisibility(View.GONE);
         secS1Q38.setVisibility(View.GONE);
         lineS1Q38.setVisibility(View.GONE);
         secS1Q39.setVisibility(View.GONE);
         lineS1Q39.setVisibility(View.GONE);
         secS1Q310.setVisibility(View.GONE);
         lineS1Q310.setVisibility(View.GONE);
         secS1Q311.setVisibility(View.GONE);
         lineS1Q311.setVisibility(View.GONE);
         secS1Q311_1.setVisibility(View.GONE);
         lineS1Q311_1.setVisibility(View.GONE);
         secS1Q17_1.setVisibility(View.GONE);
         lineS1Q17_1.setVisibility(View.GONE);
         secS1Q28_1.setVisibility(View.GONE);
         lineS1Q28_1.setVisibility(View.GONE);
         seclblq31.setVisibility(View.GONE);
         linelblq31.setVisibility(View.GONE);
         secS1Q31.setVisibility(View.GONE);
         lineS1Q31.setVisibility(View.GONE);
         secS1Q32.setVisibility(View.GONE);
         lineS1Q32.setVisibility(View.GONE);
         secS1Q33.setVisibility(View.GONE);
         lineS1Q33.setVisibility(View.GONE);
         secS1Q34.setVisibility(View.GONE);
         lineS1Q34.setVisibility(View.GONE);
         secS1Q35.setVisibility(View.GONE);
         lineS1Q35.setVisibility(View.GONE);
         secS1Q36.setVisibility(View.GONE);
         lineS1Q36.setVisibility(View.GONE);
         secS1Q37.setVisibility(View.GONE);
         lineS1Q37.setVisibility(View.GONE);
         secS1Q38.setVisibility(View.GONE);
         lineS1Q38.setVisibility(View.GONE);
         secS1Q39.setVisibility(View.GONE);
         lineS1Q39.setVisibility(View.GONE);
         secS1Q310.setVisibility(View.GONE);
         lineS1Q310.setVisibility(View.GONE);
         secS1Q311.setVisibility(View.GONE);
         lineS1Q311.setVisibility(View.GONE);
         secS1Q311_1.setVisibility(View.GONE);
         lineS1Q311_1.setVisibility(View.GONE);
         secS1Q311_1.setVisibility(View.GONE);
         lineS1Q311_1.setVisibility(View.GONE);
         seclblQ5.setVisibility(View.GONE);
         linelblQ5.setVisibility(View.GONE);
         secS2Q51.setVisibility(View.GONE);
         lineS2Q51.setVisibility(View.GONE);
         secS2Q52.setVisibility(View.GONE);
         lineS2Q52.setVisibility(View.GONE);
         secS2Q53.setVisibility(View.GONE);
         lineS2Q53.setVisibility(View.GONE);
         secS2Q54.setVisibility(View.GONE);
         lineS2Q54.setVisibility(View.GONE);
         secS2Q55.setVisibility(View.GONE);
         lineS2Q55.setVisibility(View.GONE);
         secS2Q84_1.setVisibility(View.GONE);
         lineS2Q84_1.setVisibility(View.GONE);
         seclblQ9.setVisibility(View.GONE);
         linelblQ9.setVisibility(View.GONE);
         secS3Q91.setVisibility(View.GONE);
         lineS3Q91.setVisibility(View.GONE);
         secS3Q92.setVisibility(View.GONE);
         lineS3Q92.setVisibility(View.GONE);
         secS3Q93.setVisibility(View.GONE);
         lineS3Q93.setVisibility(View.GONE);
         secS3Q94.setVisibility(View.GONE);
         lineS3Q94.setVisibility(View.GONE);
         secS3Q95.setVisibility(View.GONE);
         lineS3Q95.setVisibility(View.GONE);
         secS3Q96.setVisibility(View.GONE);
         lineS3Q96.setVisibility(View.GONE);
         seclbl97.setVisibility(View.GONE);
         linelbl97.setVisibility(View.GONE);
         secS3Q97.setVisibility(View.GONE);
         lineS3Q97.setVisibility(View.GONE);
         secS3Q98.setVisibility(View.GONE);
         lineS3Q98.setVisibility(View.GONE);
         secS3Q99.setVisibility(View.GONE);
         lineS3Q99.setVisibility(View.GONE);
         secS3Q910.setVisibility(View.GONE);
         lineS3Q910.setVisibility(View.GONE);
         secS3Q911.setVisibility(View.GONE);
         lineS3Q911.setVisibility(View.GONE);
         secS3Q912.setVisibility(View.GONE);
         lineS3Q912.setVisibility(View.GONE);
         secS3Q913.setVisibility(View.GONE);
         lineS3Q913.setVisibility(View.GONE);
         secS3Q914.setVisibility(View.GONE);
         lineS3Q914.setVisibility(View.GONE);
         secS3Q914_1.setVisibility(View.GONE);
         lineS3Q914_1.setVisibility(View.GONE);
         secS3Q10.setVisibility(View.GONE);
         lineS3Q10.setVisibility(View.GONE);
         secS3Q10_1.setVisibility(View.GONE);
         lineS3Q10_1.setVisibility(View.GONE);
         secS3Q914_1.setVisibility(View.GONE);
         lineS3Q914_1.setVisibility(View.GONE);
         secS3Q10_1.setVisibility(View.GONE);
         lineS3Q10_1.setVisibility(View.GONE);
         secS3Q10_1.setVisibility(View.GONE);
         lineS3Q10_1.setVisibility(View.GONE);
         secS3Q10_1.setVisibility(View.GONE);
         lineS3Q10_1.setVisibility(View.GONE);
         secS3Q117_1.setVisibility(View.GONE);
         lineS3Q117_1.setVisibility(View.GONE);

        seclblS3Q11.setVisibility(View.GONE);
        linelblS3Q11.setVisibility(View.GONE);
        secS3Q111.setVisibility(View.GONE);
        lineS3Q111.setVisibility(View.GONE);
        chkS3Q111.setChecked(false);
        secS3Q112.setVisibility(View.GONE);
        lineS3Q112.setVisibility(View.GONE);
        chkS3Q112.setChecked(false);
        secS3Q113.setVisibility(View.GONE);
        lineS3Q113.setVisibility(View.GONE);
        chkS3Q113.setChecked(false);
        secS3Q114.setVisibility(View.GONE);
        lineS3Q114.setVisibility(View.GONE);
        chkS3Q114.setChecked(false);
        secS3Q115.setVisibility(View.GONE);
        lineS3Q115.setVisibility(View.GONE);
        chkS3Q115.setChecked(false);
        secS3Q116.setVisibility(View.GONE);
        lineS3Q116.setVisibility(View.GONE);
        chkS3Q116.setChecked(false);
        secS3Q117.setVisibility(View.GONE);
        lineS3Q117.setVisibility(View.GONE);
        chkS3Q117.setChecked(false);
        secS3Q117_1.setVisibility(View.GONE);
        lineS3Q117_1.setVisibility(View.GONE);
        txtS3Q117_1.setText("");

        DataSearch(UUID);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});

        Global.RoleManagement(cmdSave, MySharedPreferences.getValue(this, "userrole"));
     }
     catch(Exception  e)
     {
         Connection.MessageBox(CONTRACEPTIVE_BEHAVIOUR.this, e.getMessage());
         return;
     }
 }

 @SuppressLint("ClickableViewAccessibility")
 private void Initialization()
 {
   try
     {
         secuuid = findViewById(R.id.secuuid);
         lineuuid = findViewById(R.id.lineuuid);
         lbluuid = findViewById(R.id.lbluuid);
         Vlbluuid = findViewById(R.id.Vlbluuid);
         txtuuid = findViewById(R.id.txtuuid);
         if (UUID.length() == 0) txtuuid.setText(Global.Get_UUID(DEVICEID));
         else txtuuid.setText(UUID); 
         txtuuid.setEnabled(false);
         secuuid.setVisibility(View.GONE);
         secMemID = findViewById(R.id.secMemID);
         lineMemID = findViewById(R.id.lineMemID);
         lblMemID = findViewById(R.id.lblMemID);
         VlblMemID = findViewById(R.id.VlblMemID);
         txtMemID = findViewById(R.id.txtMemID);
         txtMemID.setText(MEMID);
         secHHID = findViewById(R.id.secHHID);
         lineHHID = findViewById(R.id.lineHHID);
         lblHHID = findViewById(R.id.lblHHID);
         VlblHHID = findViewById(R.id.VlblHHID);
         txtHHID = findViewById(R.id.txtHHID);
         txtHHID.setText(HHID);
         seclbl1=findViewById(R.id.seclbl1);
         linelbl1=findViewById(R.id.linelbl1);
         secS1Q1 = findViewById(R.id.secS1Q1);
         lineS1Q1 = findViewById(R.id.lineS1Q1);
         lblS1Q1 =  findViewById(R.id.lblS1Q1);
         VlblS1Q1 =  findViewById(R.id.VlblS1Q1);
         rdogrpS1Q1 =  findViewById(R.id.rdogrpS1Q1);
         rdoS1Q11 =  findViewById(R.id.rdoS1Q11);
         rdoS1Q12 =  findViewById(R.id.rdoS1Q12);
         rdogrpS1Q1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpS1Q1 = new String[] {"1","2"};
             for (int i = 0; i < rdogrpS1Q1.getChildCount(); i++)
             {
               rb = (RadioButton) rdogrpS1Q1.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpS1Q1[i];
             }

             if(rbData.equalsIgnoreCase("2"))
             {
                    seclblq1.setVisibility(View.GONE);
                    linelblq1.setVisibility(View.GONE);
                    secS1Q11.setVisibility(View.GONE);
                    lineS1Q11.setVisibility(View.GONE);
                    chkS1Q11.setChecked(false);
                    secS1Q12.setVisibility(View.GONE);
                    lineS1Q12.setVisibility(View.GONE);
                    chkS1Q12.setChecked(false);
                    secS1Q13.setVisibility(View.GONE);
                    lineS1Q13.setVisibility(View.GONE);
                    chkS1Q13.setChecked(false);
                    secS1Q14.setVisibility(View.GONE);
                    lineS1Q14.setVisibility(View.GONE);
                    chkS1Q14.setChecked(false);
                    secS1Q15.setVisibility(View.GONE);
                    lineS1Q15.setVisibility(View.GONE);
                    chkS1Q15.setChecked(false);
                    secS1Q16.setVisibility(View.GONE);
                    lineS1Q16.setVisibility(View.GONE);
                    chkS1Q16.setChecked(false);
                    secS1Q17.setVisibility(View.GONE);
                    lineS1Q17.setVisibility(View.GONE);
                    chkS1Q17.setChecked(false);
                    secS1Q17_1.setVisibility(View.GONE);
                    lineS1Q17_1.setVisibility(View.GONE);
                    txtS1Q17_1.setText("");
                    secS1Q2.setVisibility(View.GONE);
                    lineS1Q2.setVisibility(View.GONE);
                    secS1Q21.setVisibility(View.GONE);
                    lineS1Q21.setVisibility(View.GONE);
                    chkS1Q21.setChecked(false);
                    secS1Q22.setVisibility(View.GONE);
                    lineS1Q22.setVisibility(View.GONE);
                    chkS1Q22.setChecked(false);
                    secS1Q23.setVisibility(View.GONE);
                    lineS1Q23.setVisibility(View.GONE);
                    chkS1Q23.setChecked(false);
                    secS1Q24.setVisibility(View.GONE);
                    lineS1Q24.setVisibility(View.GONE);
                    chkS1Q24.setChecked(false);
                    secS1Q25.setVisibility(View.GONE);
                    lineS1Q25.setVisibility(View.GONE);
                    chkS1Q25.setChecked(false);
                    secS1Q26.setVisibility(View.GONE);
                    lineS1Q26.setVisibility(View.GONE);
                    chkS1Q26.setChecked(false);
                    secS1Q27.setVisibility(View.GONE);
                    lineS1Q27.setVisibility(View.GONE);
                    chkS1Q27.setChecked(false);
                    secS1Q28.setVisibility(View.GONE);
                    lineS1Q28.setVisibility(View.GONE);
                    chkS1Q28.setChecked(false);
                    secS1Q28_1.setVisibility(View.GONE);
                    lineS1Q28_1.setVisibility(View.GONE);
                    txtS1Q28_1.setText("");
                    secS1Q3.setVisibility(View.GONE);
                    lineS1Q3.setVisibility(View.GONE);
                    rdogrpS1Q3.clearCheck();
                    seclblq31.setVisibility(View.GONE);
                    linelblq31.setVisibility(View.GONE);
                    secS1Q31.setVisibility(View.GONE);
                    lineS1Q31.setVisibility(View.GONE);
                    chkS1Q31.setChecked(false);
                    secS1Q32.setVisibility(View.GONE);
                    lineS1Q32.setVisibility(View.GONE);
                    chkS1Q32.setChecked(false);
                    secS1Q33.setVisibility(View.GONE);
                    lineS1Q33.setVisibility(View.GONE);
                    chkS1Q33.setChecked(false);
                    secS1Q34.setVisibility(View.GONE);
                    lineS1Q34.setVisibility(View.GONE);
                    chkS1Q34.setChecked(false);
                    secS1Q35.setVisibility(View.GONE);
                    lineS1Q35.setVisibility(View.GONE);
                    chkS1Q35.setChecked(false);
                    secS1Q36.setVisibility(View.GONE);
                    lineS1Q36.setVisibility(View.GONE);
                    chkS1Q36.setChecked(false);
                    secS1Q37.setVisibility(View.GONE);
                    lineS1Q37.setVisibility(View.GONE);
                    chkS1Q37.setChecked(false);
                    secS1Q38.setVisibility(View.GONE);
                    lineS1Q38.setVisibility(View.GONE);
                    chkS1Q38.setChecked(false);
                    secS1Q39.setVisibility(View.GONE);
                    lineS1Q39.setVisibility(View.GONE);
                    chkS1Q39.setChecked(false);
                    secS1Q310.setVisibility(View.GONE);
                    lineS1Q310.setVisibility(View.GONE);
                    chkS1Q310.setChecked(false);
                    secS1Q311.setVisibility(View.GONE);
                    lineS1Q311.setVisibility(View.GONE);
                    chkS1Q311.setChecked(false);
                    secS1Q311_1.setVisibility(View.GONE);
                    lineS1Q311_1.setVisibility(View.GONE);
                    txtS1Q311_1.setText("");
             }
             else
             {
                    seclblq1.setVisibility(View.VISIBLE);
                    linelblq1.setVisibility(View.VISIBLE);
                    secS1Q11.setVisibility(View.VISIBLE);
                    lineS1Q11.setVisibility(View.VISIBLE);
                    secS1Q12.setVisibility(View.VISIBLE);
                    lineS1Q12.setVisibility(View.VISIBLE);
                    secS1Q13.setVisibility(View.VISIBLE);
                    lineS1Q13.setVisibility(View.VISIBLE);
                    secS1Q14.setVisibility(View.VISIBLE);
                    lineS1Q14.setVisibility(View.VISIBLE);
                    secS1Q15.setVisibility(View.VISIBLE);
                    lineS1Q15.setVisibility(View.VISIBLE);
                    secS1Q16.setVisibility(View.VISIBLE);
                    lineS1Q16.setVisibility(View.VISIBLE);
                    secS1Q17.setVisibility(View.VISIBLE);
                    lineS1Q17.setVisibility(View.VISIBLE);
                    //secS1Q17_1.setVisibility(View.VISIBLE);
                    //lineS1Q17_1.setVisibility(View.VISIBLE);
                    secS1Q2.setVisibility(View.VISIBLE);
                    lineS1Q2.setVisibility(View.VISIBLE);
                    secS1Q21.setVisibility(View.VISIBLE);
                    lineS1Q21.setVisibility(View.VISIBLE);
                    secS1Q22.setVisibility(View.VISIBLE);
                    lineS1Q22.setVisibility(View.VISIBLE);
                    secS1Q23.setVisibility(View.VISIBLE);
                    lineS1Q23.setVisibility(View.VISIBLE);
                    secS1Q24.setVisibility(View.VISIBLE);
                    lineS1Q24.setVisibility(View.VISIBLE);
                    secS1Q25.setVisibility(View.VISIBLE);
                    lineS1Q25.setVisibility(View.VISIBLE);
                    secS1Q26.setVisibility(View.VISIBLE);
                    lineS1Q26.setVisibility(View.VISIBLE);
                    secS1Q27.setVisibility(View.VISIBLE);
                    lineS1Q27.setVisibility(View.VISIBLE);
                    secS1Q28.setVisibility(View.VISIBLE);
                    lineS1Q28.setVisibility(View.VISIBLE);
                    //secS1Q28_1.setVisibility(View.VISIBLE);
                    //lineS1Q28_1.setVisibility(View.VISIBLE);
                    secS1Q3.setVisibility(View.VISIBLE);
                    lineS1Q3.setVisibility(View.VISIBLE);
                    /*seclblq31.setVisibility(View.VISIBLE);
                    linelblq31.setVisibility(View.VISIBLE);
                    secS1Q31.setVisibility(View.VISIBLE);
                    lineS1Q31.setVisibility(View.VISIBLE);
                    secS1Q32.setVisibility(View.VISIBLE);
                    lineS1Q32.setVisibility(View.VISIBLE);
                    secS1Q33.setVisibility(View.VISIBLE);
                    lineS1Q33.setVisibility(View.VISIBLE);
                    secS1Q34.setVisibility(View.VISIBLE);
                    lineS1Q34.setVisibility(View.VISIBLE);
                    secS1Q35.setVisibility(View.VISIBLE);
                    lineS1Q35.setVisibility(View.VISIBLE);
                    secS1Q36.setVisibility(View.VISIBLE);
                    lineS1Q36.setVisibility(View.VISIBLE);
                    secS1Q37.setVisibility(View.VISIBLE);
                    lineS1Q37.setVisibility(View.VISIBLE);
                    secS1Q38.setVisibility(View.VISIBLE);
                    lineS1Q38.setVisibility(View.VISIBLE);
                    secS1Q39.setVisibility(View.VISIBLE);
                    lineS1Q39.setVisibility(View.VISIBLE);
                    secS1Q310.setVisibility(View.VISIBLE);
                    lineS1Q310.setVisibility(View.VISIBLE);
                    secS1Q311.setVisibility(View.VISIBLE);
                    lineS1Q311.setVisibility(View.VISIBLE);*/
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclblq1=findViewById(R.id.seclblq1);
         linelblq1=findViewById(R.id.linelblq1);
         secS1Q11 = findViewById(R.id.secS1Q11);
         lineS1Q11 = findViewById(R.id.lineS1Q11);
         lblS1Q11 = findViewById(R.id.lblS1Q11);
         VlblS1Q11 = findViewById(R.id.VlblS1Q11);
         chkS1Q11 = findViewById(R.id.chkS1Q11);
         secS1Q12 = findViewById(R.id.secS1Q12);
         lineS1Q12 = findViewById(R.id.lineS1Q12);
         lblS1Q12 = findViewById(R.id.lblS1Q12);
         VlblS1Q12 = findViewById(R.id.VlblS1Q12);
         chkS1Q12 = findViewById(R.id.chkS1Q12);
         secS1Q13 = findViewById(R.id.secS1Q13);
         lineS1Q13 = findViewById(R.id.lineS1Q13);
         lblS1Q13 = findViewById(R.id.lblS1Q13);
         VlblS1Q13 = findViewById(R.id.VlblS1Q13);
         chkS1Q13 = findViewById(R.id.chkS1Q13);
         secS1Q14 = findViewById(R.id.secS1Q14);
         lineS1Q14 = findViewById(R.id.lineS1Q14);
         lblS1Q14 = findViewById(R.id.lblS1Q14);
         VlblS1Q14 = findViewById(R.id.VlblS1Q14);
         chkS1Q14 = findViewById(R.id.chkS1Q14);
         secS1Q15 = findViewById(R.id.secS1Q15);
         lineS1Q15 = findViewById(R.id.lineS1Q15);
         lblS1Q15 = findViewById(R.id.lblS1Q15);
         VlblS1Q15 = findViewById(R.id.VlblS1Q15);
         chkS1Q15 = findViewById(R.id.chkS1Q15);
         secS1Q16 = findViewById(R.id.secS1Q16);
         lineS1Q16 = findViewById(R.id.lineS1Q16);
         lblS1Q16 = findViewById(R.id.lblS1Q16);
         VlblS1Q16 = findViewById(R.id.VlblS1Q16);
         chkS1Q16 = findViewById(R.id.chkS1Q16);
         secS1Q17 = findViewById(R.id.secS1Q17);
         lineS1Q17 = findViewById(R.id.lineS1Q17);
         lblS1Q17 = findViewById(R.id.lblS1Q17);
         VlblS1Q17 = findViewById(R.id.VlblS1Q17);
         chkS1Q17 = findViewById(R.id.chkS1Q17);
         chkS1Q17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secS1Q17_1.setVisibility(View.VISIBLE);
                    lineS1Q17_1.setVisibility(View.VISIBLE);
                 }
                 else {
                    secS1Q17_1.setVisibility(View.GONE);
                    lineS1Q17_1.setVisibility(View.GONE);
                    txtS1Q17_1.setText("");
                 }
                 }
         });
         secS1Q17_1 = findViewById(R.id.secS1Q17_1);
         lineS1Q17_1 = findViewById(R.id.lineS1Q17_1);
         lblS1Q17_1 = findViewById(R.id.lblS1Q17_1);
         VlblS1Q17_1 = findViewById(R.id.VlblS1Q17_1);
         txtS1Q17_1 = findViewById(R.id.txtS1Q17_1);
         txtS1Q17_1.setAdapter(C.getArrayAdapter("Select distinct S1Q17_1 from "+ TableName +" order by S1Q17_1"));

         txtS1Q17_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtS1Q17_1.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtS1Q17_1.getRight() - txtS1Q17_1.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secS1Q2=findViewById(R.id.secS1Q2);
         lineS1Q2=findViewById(R.id.lineS1Q2);
         secS1Q21 = findViewById(R.id.secS1Q21);
         lineS1Q21 = findViewById(R.id.lineS1Q21);
         lblS1Q21 = findViewById(R.id.lblS1Q21);
         VlblS1Q21 = findViewById(R.id.VlblS1Q21);
         chkS1Q21 = findViewById(R.id.chkS1Q21);
         secS1Q22 = findViewById(R.id.secS1Q22);
         lineS1Q22 = findViewById(R.id.lineS1Q22);
         lblS1Q22 = findViewById(R.id.lblS1Q22);
         VlblS1Q22 = findViewById(R.id.VlblS1Q22);
         chkS1Q22 = findViewById(R.id.chkS1Q22);
         secS1Q23 = findViewById(R.id.secS1Q23);
         lineS1Q23 = findViewById(R.id.lineS1Q23);
         lblS1Q23 = findViewById(R.id.lblS1Q23);
         VlblS1Q23 = findViewById(R.id.VlblS1Q23);
         chkS1Q23 = findViewById(R.id.chkS1Q23);
         secS1Q24 = findViewById(R.id.secS1Q24);
         lineS1Q24 = findViewById(R.id.lineS1Q24);
         lblS1Q24 = findViewById(R.id.lblS1Q24);
         VlblS1Q24 = findViewById(R.id.VlblS1Q24);
         chkS1Q24 = findViewById(R.id.chkS1Q24);
         secS1Q25 = findViewById(R.id.secS1Q25);
         lineS1Q25 = findViewById(R.id.lineS1Q25);
         lblS1Q25 = findViewById(R.id.lblS1Q25);
         VlblS1Q25 = findViewById(R.id.VlblS1Q25);
         chkS1Q25 = findViewById(R.id.chkS1Q25);
         secS1Q26 = findViewById(R.id.secS1Q26);
         lineS1Q26 = findViewById(R.id.lineS1Q26);
         lblS1Q26 = findViewById(R.id.lblS1Q26);
         VlblS1Q26 = findViewById(R.id.VlblS1Q26);
         chkS1Q26 = findViewById(R.id.chkS1Q26);
         secS1Q27 = findViewById(R.id.secS1Q27);
         lineS1Q27 = findViewById(R.id.lineS1Q27);
         lblS1Q27 = findViewById(R.id.lblS1Q27);
         VlblS1Q27 = findViewById(R.id.VlblS1Q27);
         chkS1Q27 = findViewById(R.id.chkS1Q27);
         secS1Q28 = findViewById(R.id.secS1Q28);
         lineS1Q28 = findViewById(R.id.lineS1Q28);
         lblS1Q28 = findViewById(R.id.lblS1Q28);
         VlblS1Q28 = findViewById(R.id.VlblS1Q28);
         chkS1Q28 = findViewById(R.id.chkS1Q28);
         chkS1Q28.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secS1Q28_1.setVisibility(View.VISIBLE);
                    lineS1Q28_1.setVisibility(View.VISIBLE);
                 }
                 else {
                    secS1Q28_1.setVisibility(View.GONE);
                    lineS1Q28_1.setVisibility(View.GONE);
                    txtS1Q28_1.setText("");
                 }
                 }
         });
         secS1Q28_1 = findViewById(R.id.secS1Q28_1);
         lineS1Q28_1 = findViewById(R.id.lineS1Q28_1);
         lblS1Q28_1 = findViewById(R.id.lblS1Q28_1);
         VlblS1Q28_1 = findViewById(R.id.VlblS1Q28_1);
         txtS1Q28_1 = findViewById(R.id.txtS1Q28_1);
         txtS1Q28_1.setAdapter(C.getArrayAdapter("Select distinct S1Q28_1 from "+ TableName +" order by S1Q28_1"));

         txtS1Q28_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtS1Q28_1.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtS1Q28_1.getRight() - txtS1Q28_1.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secS1Q3 = findViewById(R.id.secS1Q3);
         lineS1Q3 = findViewById(R.id.lineS1Q3);
         lblS1Q3 =  findViewById(R.id.lblS1Q3);
         VlblS1Q3 =  findViewById(R.id.VlblS1Q3);
         rdogrpS1Q3 =  findViewById(R.id.rdogrpS1Q3);
         rdoS1Q31 =  findViewById(R.id.rdoS1Q31);
         rdoS1Q32 =  findViewById(R.id.rdoS1Q32);
         rdogrpS1Q3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpS1Q3 = new String[] {"1","2"};
             for (int i = 0; i < rdogrpS1Q3.getChildCount(); i++)
             {
               rb = (RadioButton) rdogrpS1Q3.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpS1Q3[i];
             }

             if(rbData.equalsIgnoreCase("2"))
             {
                    seclblq31.setVisibility(View.GONE);
                    linelblq31.setVisibility(View.GONE);
                    secS1Q31.setVisibility(View.GONE);
                    lineS1Q31.setVisibility(View.GONE);
                    chkS1Q31.setChecked(false);
                    secS1Q32.setVisibility(View.GONE);
                    lineS1Q32.setVisibility(View.GONE);
                    chkS1Q32.setChecked(false);
                    secS1Q33.setVisibility(View.GONE);
                    lineS1Q33.setVisibility(View.GONE);
                    chkS1Q33.setChecked(false);
                    secS1Q34.setVisibility(View.GONE);
                    lineS1Q34.setVisibility(View.GONE);
                    chkS1Q34.setChecked(false);
                    secS1Q35.setVisibility(View.GONE);
                    lineS1Q35.setVisibility(View.GONE);
                    chkS1Q35.setChecked(false);
                    secS1Q36.setVisibility(View.GONE);
                    lineS1Q36.setVisibility(View.GONE);
                    chkS1Q36.setChecked(false);
                    secS1Q37.setVisibility(View.GONE);
                    lineS1Q37.setVisibility(View.GONE);
                    chkS1Q37.setChecked(false);
                    secS1Q38.setVisibility(View.GONE);
                    lineS1Q38.setVisibility(View.GONE);
                    chkS1Q38.setChecked(false);
                    secS1Q39.setVisibility(View.GONE);
                    lineS1Q39.setVisibility(View.GONE);
                    chkS1Q39.setChecked(false);
                    secS1Q310.setVisibility(View.GONE);
                    lineS1Q310.setVisibility(View.GONE);
                    chkS1Q310.setChecked(false);
                    secS1Q311.setVisibility(View.GONE);
                    lineS1Q311.setVisibility(View.GONE);
                    chkS1Q311.setChecked(false);
                    secS1Q311_1.setVisibility(View.GONE);
                    lineS1Q311_1.setVisibility(View.GONE);
                    txtS1Q311_1.setText("");
             }
             else
             {
                    seclblq31.setVisibility(View.VISIBLE);
                    linelblq31.setVisibility(View.VISIBLE);
                    secS1Q31.setVisibility(View.VISIBLE);
                    lineS1Q31.setVisibility(View.VISIBLE);
                    secS1Q32.setVisibility(View.VISIBLE);
                    lineS1Q32.setVisibility(View.VISIBLE);
                    secS1Q33.setVisibility(View.VISIBLE);
                    lineS1Q33.setVisibility(View.VISIBLE);
                    secS1Q34.setVisibility(View.VISIBLE);
                    lineS1Q34.setVisibility(View.VISIBLE);
                    secS1Q35.setVisibility(View.VISIBLE);
                    lineS1Q35.setVisibility(View.VISIBLE);
                    secS1Q36.setVisibility(View.VISIBLE);
                    lineS1Q36.setVisibility(View.VISIBLE);
                    secS1Q37.setVisibility(View.VISIBLE);
                    lineS1Q37.setVisibility(View.VISIBLE);
                    secS1Q38.setVisibility(View.VISIBLE);
                    lineS1Q38.setVisibility(View.VISIBLE);
                    secS1Q39.setVisibility(View.VISIBLE);
                    lineS1Q39.setVisibility(View.VISIBLE);
                    secS1Q310.setVisibility(View.VISIBLE);
                    lineS1Q310.setVisibility(View.VISIBLE);
                    secS1Q311.setVisibility(View.VISIBLE);
                    lineS1Q311.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclblq31=findViewById(R.id.seclblq31);
         linelblq31=findViewById(R.id.linelblq31);
         secS1Q31 = findViewById(R.id.secS1Q31);
         lineS1Q31 = findViewById(R.id.lineS1Q31);
         lblS1Q31 = findViewById(R.id.lblS1Q31);
         VlblS1Q31 = findViewById(R.id.VlblS1Q31);
         chkS1Q31 = findViewById(R.id.chkS1Q31);
         secS1Q32 = findViewById(R.id.secS1Q32);
         lineS1Q32 = findViewById(R.id.lineS1Q32);
         lblS1Q32 = findViewById(R.id.lblS1Q32);
         VlblS1Q32 = findViewById(R.id.VlblS1Q32);
         chkS1Q32 = findViewById(R.id.chkS1Q32);
         secS1Q33 = findViewById(R.id.secS1Q33);
         lineS1Q33 = findViewById(R.id.lineS1Q33);
         lblS1Q33 = findViewById(R.id.lblS1Q33);
         VlblS1Q33 = findViewById(R.id.VlblS1Q33);
         chkS1Q33 = findViewById(R.id.chkS1Q33);
         secS1Q34 = findViewById(R.id.secS1Q34);
         lineS1Q34 = findViewById(R.id.lineS1Q34);
         lblS1Q34 = findViewById(R.id.lblS1Q34);
         VlblS1Q34 = findViewById(R.id.VlblS1Q34);
         chkS1Q34 = findViewById(R.id.chkS1Q34);
         secS1Q35 = findViewById(R.id.secS1Q35);
         lineS1Q35 = findViewById(R.id.lineS1Q35);
         lblS1Q35 = findViewById(R.id.lblS1Q35);
         VlblS1Q35 = findViewById(R.id.VlblS1Q35);
         chkS1Q35 = findViewById(R.id.chkS1Q35);
         secS1Q36 = findViewById(R.id.secS1Q36);
         lineS1Q36 = findViewById(R.id.lineS1Q36);
         lblS1Q36 = findViewById(R.id.lblS1Q36);
         VlblS1Q36 = findViewById(R.id.VlblS1Q36);
         chkS1Q36 = findViewById(R.id.chkS1Q36);
         secS1Q37 = findViewById(R.id.secS1Q37);
         lineS1Q37 = findViewById(R.id.lineS1Q37);
         lblS1Q37 = findViewById(R.id.lblS1Q37);
         VlblS1Q37 = findViewById(R.id.VlblS1Q37);
         chkS1Q37 = findViewById(R.id.chkS1Q37);
         secS1Q38 = findViewById(R.id.secS1Q38);
         lineS1Q38 = findViewById(R.id.lineS1Q38);
         lblS1Q38 = findViewById(R.id.lblS1Q38);
         VlblS1Q38 = findViewById(R.id.VlblS1Q38);
         chkS1Q38 = findViewById(R.id.chkS1Q38);
         secS1Q39 = findViewById(R.id.secS1Q39);
         lineS1Q39 = findViewById(R.id.lineS1Q39);
         lblS1Q39 = findViewById(R.id.lblS1Q39);
         VlblS1Q39 = findViewById(R.id.VlblS1Q39);
         chkS1Q39 = findViewById(R.id.chkS1Q39);
         secS1Q310 = findViewById(R.id.secS1Q310);
         lineS1Q310 = findViewById(R.id.lineS1Q310);
         lblS1Q310 = findViewById(R.id.lblS1Q310);
         VlblS1Q310 = findViewById(R.id.VlblS1Q310);
         chkS1Q310 = findViewById(R.id.chkS1Q310);
         secS1Q311 = findViewById(R.id.secS1Q311);
         lineS1Q311 = findViewById(R.id.lineS1Q311);
         lblS1Q311 = findViewById(R.id.lblS1Q311);
         VlblS1Q311 = findViewById(R.id.VlblS1Q311);
         chkS1Q311 = findViewById(R.id.chkS1Q311);
         chkS1Q311.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secS1Q311_1.setVisibility(View.VISIBLE);
                    lineS1Q311_1.setVisibility(View.VISIBLE);
                 }
                 else {
                    secS1Q311_1.setVisibility(View.GONE);
                    lineS1Q311_1.setVisibility(View.GONE);
                    txtS1Q311_1.setText("");
                 }
                 }
         });
         secS1Q311_1 = findViewById(R.id.secS1Q311_1);
         lineS1Q311_1 = findViewById(R.id.lineS1Q311_1);
         lblS1Q311_1 = findViewById(R.id.lblS1Q311_1);
         VlblS1Q311_1 = findViewById(R.id.VlblS1Q311_1);
         txtS1Q311_1 = findViewById(R.id.txtS1Q311_1);
         txtS1Q311_1.setAdapter(C.getArrayAdapter("Select distinct S1Q311_1 from "+ TableName +" order by S1Q311_1"));

         txtS1Q311_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtS1Q311_1.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtS1Q311_1.getRight() - txtS1Q311_1.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         seclbls2=findViewById(R.id.seclbls2);
         linelbls2=findViewById(R.id.linelbls2);
         secS2Q4 = findViewById(R.id.secS2Q4);
         lineS2Q4 = findViewById(R.id.lineS2Q4);
         lblS2Q4 =  findViewById(R.id.lblS2Q4);
         VlblS2Q4 =  findViewById(R.id.VlblS2Q4);
         rdogrpS2Q4 =  findViewById(R.id.rdogrpS2Q4);
         rdoS2Q41 =  findViewById(R.id.rdoS2Q41);
         rdoS2Q42 =  findViewById(R.id.rdoS2Q42);
         rdogrpS2Q4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpS2Q4 = new String[] {"1","2"};
             for (int i = 0; i < rdogrpS2Q4.getChildCount(); i++)
             {
               rb = (RadioButton) rdogrpS2Q4.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpS2Q4[i];
             }

             if(rbData.equalsIgnoreCase("2"))
             {
                    seclblQ5.setVisibility(View.GONE);
                    linelblQ5.setVisibility(View.GONE);
                    secS2Q51.setVisibility(View.GONE);
                    lineS2Q51.setVisibility(View.GONE);
                    chkS2Q51.setChecked(false);
                    secS2Q52.setVisibility(View.GONE);
                    lineS2Q52.setVisibility(View.GONE);
                    chkS2Q52.setChecked(false);
                    secS2Q53.setVisibility(View.GONE);
                    lineS2Q53.setVisibility(View.GONE);
                    chkS2Q53.setChecked(false);
                    secS2Q54.setVisibility(View.GONE);
                    lineS2Q54.setVisibility(View.GONE);
                    chkS2Q54.setChecked(false);
                    secS2Q55.setVisibility(View.GONE);
                    lineS2Q55.setVisibility(View.GONE);
                    chkS2Q55.setChecked(false);
             }
             else
             {
                    seclblQ5.setVisibility(View.VISIBLE);
                    linelblQ5.setVisibility(View.VISIBLE);
                    secS2Q51.setVisibility(View.VISIBLE);
                    lineS2Q51.setVisibility(View.VISIBLE);
                    secS2Q52.setVisibility(View.VISIBLE);
                    lineS2Q52.setVisibility(View.VISIBLE);
                    secS2Q53.setVisibility(View.VISIBLE);
                    lineS2Q53.setVisibility(View.VISIBLE);
                    secS2Q54.setVisibility(View.VISIBLE);
                    lineS2Q54.setVisibility(View.VISIBLE);
                    secS2Q55.setVisibility(View.VISIBLE);
                    lineS2Q55.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclblQ5=findViewById(R.id.seclblQ5);
         linelblQ5=findViewById(R.id.linelblQ5);
         secS2Q51 = findViewById(R.id.secS2Q51);
         lineS2Q51 = findViewById(R.id.lineS2Q51);
         lblS2Q51 = findViewById(R.id.lblS2Q51);
         VlblS2Q51 = findViewById(R.id.VlblS2Q51);
         chkS2Q51 = findViewById(R.id.chkS2Q51);
         secS2Q52 = findViewById(R.id.secS2Q52);
         lineS2Q52 = findViewById(R.id.lineS2Q52);
         lblS2Q52 = findViewById(R.id.lblS2Q52);
         VlblS2Q52 = findViewById(R.id.VlblS2Q52);
         chkS2Q52 = findViewById(R.id.chkS2Q52);
         secS2Q53 = findViewById(R.id.secS2Q53);
         lineS2Q53 = findViewById(R.id.lineS2Q53);
         lblS2Q53 = findViewById(R.id.lblS2Q53);
         VlblS2Q53 = findViewById(R.id.VlblS2Q53);
         chkS2Q53 = findViewById(R.id.chkS2Q53);
         secS2Q54 = findViewById(R.id.secS2Q54);
         lineS2Q54 = findViewById(R.id.lineS2Q54);
         lblS2Q54 = findViewById(R.id.lblS2Q54);
         VlblS2Q54 = findViewById(R.id.VlblS2Q54);
         chkS2Q54 = findViewById(R.id.chkS2Q54);
         secS2Q55 = findViewById(R.id.secS2Q55);
         lineS2Q55 = findViewById(R.id.lineS2Q55);
         lblS2Q55 = findViewById(R.id.lblS2Q55);
         VlblS2Q55 = findViewById(R.id.VlblS2Q55);
         chkS2Q55 = findViewById(R.id.chkS2Q55);
         secS2Q6 = findViewById(R.id.secS2Q6);
         lineS2Q6 = findViewById(R.id.lineS2Q6);
         lblS2Q6 =  findViewById(R.id.lblS2Q6);
         VlblS2Q6 =  findViewById(R.id.VlblS2Q6);
         rdogrpS2Q6 =  findViewById(R.id.rdogrpS2Q6);
         rdoS2Q61 =  findViewById(R.id.rdoS2Q61);
         rdoS2Q62 =  findViewById(R.id.rdoS2Q62);
         rdoS2Q63 =  findViewById(R.id.rdoS2Q63);
         rdoS2Q64 =  findViewById(R.id.rdoS2Q64);
         secS2Q7 = findViewById(R.id.secS2Q7);
         lineS2Q7 = findViewById(R.id.lineS2Q7);
         lblS2Q7 =  findViewById(R.id.lblS2Q7);
         VlblS2Q7 =  findViewById(R.id.VlblS2Q7);
         rdogrpS2Q7 =  findViewById(R.id.rdogrpS2Q7);
         rdoS2Q71 =  findViewById(R.id.rdoS2Q71);
         rdoS2Q72 =  findViewById(R.id.rdoS2Q72);
         rdoS2Q73 =  findViewById(R.id.rdoS2Q73);
         rdoS2Q74 =  findViewById(R.id.rdoS2Q74);
         seclblQ8=findViewById(R.id.seclblQ8);
         linelblQ8=findViewById(R.id.linelblQ8);
         secS2Q81 = findViewById(R.id.secS2Q81);
         lineS2Q81 = findViewById(R.id.lineS2Q81);
         lblS2Q81 = findViewById(R.id.lblS2Q81);
         VlblS2Q81 = findViewById(R.id.VlblS2Q81);
         chkS2Q81 = findViewById(R.id.chkS2Q81);
         secS2Q82 = findViewById(R.id.secS2Q82);
         lineS2Q82 = findViewById(R.id.lineS2Q82);
         lblS2Q82 = findViewById(R.id.lblS2Q82);
         VlblS2Q82 = findViewById(R.id.VlblS2Q82);
         chkS2Q82 = findViewById(R.id.chkS2Q82);
         secS2Q83 = findViewById(R.id.secS2Q83);
         lineS2Q83 = findViewById(R.id.lineS2Q83);
         lblS2Q83 = findViewById(R.id.lblS2Q83);
         VlblS2Q83 = findViewById(R.id.VlblS2Q83);
         chkS2Q83 = findViewById(R.id.chkS2Q83);
         secS2Q84 = findViewById(R.id.secS2Q84);
         lineS2Q84 = findViewById(R.id.lineS2Q84);
         lblS2Q84 = findViewById(R.id.lblS2Q84);
         VlblS2Q84 = findViewById(R.id.VlblS2Q84);
         chkS2Q84 = findViewById(R.id.chkS2Q84);
         chkS2Q84.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secS2Q84_1.setVisibility(View.VISIBLE);
                    lineS2Q84_1.setVisibility(View.VISIBLE);
                 }
                 else {
                    secS2Q84_1.setVisibility(View.GONE);
                    lineS2Q84_1.setVisibility(View.GONE);
                    txtS2Q84_1.setText("");
                 }
                 }
         });
         secS2Q84_1 = findViewById(R.id.secS2Q84_1);
         lineS2Q84_1 = findViewById(R.id.lineS2Q84_1);
         lblS2Q84_1 = findViewById(R.id.lblS2Q84_1);
         VlblS2Q84_1 = findViewById(R.id.VlblS2Q84_1);
         txtS2Q84_1 = findViewById(R.id.txtS2Q84_1);
         txtS2Q84_1.setAdapter(C.getArrayAdapter("Select distinct S2Q84_1 from "+ TableName +" order by S2Q84_1"));

         txtS2Q84_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtS2Q84_1.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtS2Q84_1.getRight() - txtS2Q84_1.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         seclbls3=findViewById(R.id.seclbls3);
         linelbls3=findViewById(R.id.linelbls3);
         secS3Q9 = findViewById(R.id.secS3Q9);
         lineS3Q9 = findViewById(R.id.lineS3Q9);
         lblS3Q9 =  findViewById(R.id.lblS3Q9);
         VlblS3Q9 =  findViewById(R.id.VlblS3Q9);
         rdogrpS3Q9 =  findViewById(R.id.rdogrpS3Q9);
         rdoS3Q91 =  findViewById(R.id.rdoS3Q91);
         rdoS3Q92 =  findViewById(R.id.rdoS3Q92);
         rdogrpS3Q9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpS3Q9 = new String[] {"1","2"};
             for (int i = 0; i < rdogrpS3Q9.getChildCount(); i++)
             {
               rb = (RadioButton) rdogrpS3Q9.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpS3Q9[i];
             }

             if(rbData.equalsIgnoreCase("2"))
             {
                    seclblQ9.setVisibility(View.GONE);
                    linelblQ9.setVisibility(View.GONE);
                    secS3Q91.setVisibility(View.GONE);
                    lineS3Q91.setVisibility(View.GONE);
                    chkS3Q91.setChecked(false);
                    secS3Q92.setVisibility(View.GONE);
                    lineS3Q92.setVisibility(View.GONE);
                    chkS3Q92.setChecked(false);
                    secS3Q93.setVisibility(View.GONE);
                    lineS3Q93.setVisibility(View.GONE);
                    chkS3Q93.setChecked(false);
                    secS3Q94.setVisibility(View.GONE);
                    lineS3Q94.setVisibility(View.GONE);
                    chkS3Q94.setChecked(false);
                    secS3Q95.setVisibility(View.GONE);
                    lineS3Q95.setVisibility(View.GONE);
                    chkS3Q95.setChecked(false);
                    secS3Q96.setVisibility(View.GONE);
                    lineS3Q96.setVisibility(View.GONE);
                    chkS3Q96.setChecked(false);
                    seclbl97.setVisibility(View.GONE);
                    linelbl97.setVisibility(View.GONE);
                    secS3Q97.setVisibility(View.GONE);
                    lineS3Q97.setVisibility(View.GONE);
                    chkS3Q97.setChecked(false);
                    secS3Q98.setVisibility(View.GONE);
                    lineS3Q98.setVisibility(View.GONE);
                    chkS3Q98.setChecked(false);
                    secS3Q99.setVisibility(View.GONE);
                    lineS3Q99.setVisibility(View.GONE);
                    chkS3Q99.setChecked(false);
                    secS3Q910.setVisibility(View.GONE);
                    lineS3Q910.setVisibility(View.GONE);
                    chkS3Q910.setChecked(false);
                    secS3Q911.setVisibility(View.GONE);
                    lineS3Q911.setVisibility(View.GONE);
                    chkS3Q911.setChecked(false);
                    secS3Q912.setVisibility(View.GONE);
                    lineS3Q912.setVisibility(View.GONE);
                    chkS3Q912.setChecked(false);
                    secS3Q913.setVisibility(View.GONE);
                    lineS3Q913.setVisibility(View.GONE);
                    chkS3Q913.setChecked(false);
                    secS3Q914.setVisibility(View.GONE);
                    lineS3Q914.setVisibility(View.GONE);
                    chkS3Q914.setChecked(false);
                    secS3Q914_1.setVisibility(View.GONE);
                    lineS3Q914_1.setVisibility(View.GONE);
                    txtS3Q914_1.setText("");
                    secS3Q10.setVisibility(View.GONE);
                    lineS3Q10.setVisibility(View.GONE);
                    spnS3Q10.setSelection(0);
                    secS3Q10_1.setVisibility(View.GONE);
                    lineS3Q10_1.setVisibility(View.GONE);
                    txtS3Q10_1.setText("");
             }
             else
             {
                    seclblQ9.setVisibility(View.VISIBLE);
                    linelblQ9.setVisibility(View.VISIBLE);
                    secS3Q91.setVisibility(View.VISIBLE);
                    lineS3Q91.setVisibility(View.VISIBLE);
                    secS3Q92.setVisibility(View.VISIBLE);
                    lineS3Q92.setVisibility(View.VISIBLE);
                    secS3Q93.setVisibility(View.VISIBLE);
                    lineS3Q93.setVisibility(View.VISIBLE);
                    secS3Q94.setVisibility(View.VISIBLE);
                    lineS3Q94.setVisibility(View.VISIBLE);
                    secS3Q95.setVisibility(View.VISIBLE);
                    lineS3Q95.setVisibility(View.VISIBLE);
                    secS3Q96.setVisibility(View.VISIBLE);
                    lineS3Q96.setVisibility(View.VISIBLE);
                    seclbl97.setVisibility(View.VISIBLE);
                    linelbl97.setVisibility(View.VISIBLE);
                    secS3Q97.setVisibility(View.VISIBLE);
                    lineS3Q97.setVisibility(View.VISIBLE);
                    secS3Q98.setVisibility(View.VISIBLE);
                    lineS3Q98.setVisibility(View.VISIBLE);
                    secS3Q99.setVisibility(View.VISIBLE);
                    lineS3Q99.setVisibility(View.VISIBLE);
                    secS3Q910.setVisibility(View.VISIBLE);
                    lineS3Q910.setVisibility(View.VISIBLE);
                    secS3Q911.setVisibility(View.VISIBLE);
                    lineS3Q911.setVisibility(View.VISIBLE);
                    secS3Q912.setVisibility(View.VISIBLE);
                    lineS3Q912.setVisibility(View.VISIBLE);
                    secS3Q913.setVisibility(View.VISIBLE);
                    lineS3Q913.setVisibility(View.VISIBLE);
                    secS3Q914.setVisibility(View.VISIBLE);
                    lineS3Q914.setVisibility(View.VISIBLE);
                    secS3Q914_1.setVisibility(View.VISIBLE);
                    lineS3Q914_1.setVisibility(View.VISIBLE);
                    secS3Q10.setVisibility(View.VISIBLE);
                    lineS3Q10.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclblQ9=findViewById(R.id.seclblQ9);
         linelblQ9=findViewById(R.id.linelblQ9);
         secS3Q91 = findViewById(R.id.secS3Q91);
         lineS3Q91 = findViewById(R.id.lineS3Q91);
         lblS3Q91 = findViewById(R.id.lblS3Q91);
         VlblS3Q91 = findViewById(R.id.VlblS3Q91);
         chkS3Q91 = findViewById(R.id.chkS3Q91);
         secS3Q92 = findViewById(R.id.secS3Q92);
         lineS3Q92 = findViewById(R.id.lineS3Q92);
         lblS3Q92 = findViewById(R.id.lblS3Q92);
         VlblS3Q92 = findViewById(R.id.VlblS3Q92);
         chkS3Q92 = findViewById(R.id.chkS3Q92);
         secS3Q93 = findViewById(R.id.secS3Q93);
         lineS3Q93 = findViewById(R.id.lineS3Q93);
         lblS3Q93 = findViewById(R.id.lblS3Q93);
         VlblS3Q93 = findViewById(R.id.VlblS3Q93);
         chkS3Q93 = findViewById(R.id.chkS3Q93);
         secS3Q94 = findViewById(R.id.secS3Q94);
         lineS3Q94 = findViewById(R.id.lineS3Q94);
         lblS3Q94 = findViewById(R.id.lblS3Q94);
         VlblS3Q94 = findViewById(R.id.VlblS3Q94);
         chkS3Q94 = findViewById(R.id.chkS3Q94);
         secS3Q95 = findViewById(R.id.secS3Q95);
         lineS3Q95 = findViewById(R.id.lineS3Q95);
         lblS3Q95 = findViewById(R.id.lblS3Q95);
         VlblS3Q95 = findViewById(R.id.VlblS3Q95);
         chkS3Q95 = findViewById(R.id.chkS3Q95);
         secS3Q96 = findViewById(R.id.secS3Q96);
         lineS3Q96 = findViewById(R.id.lineS3Q96);
         lblS3Q96 = findViewById(R.id.lblS3Q96);
         VlblS3Q96 = findViewById(R.id.VlblS3Q96);
         chkS3Q96 = findViewById(R.id.chkS3Q96);
         seclbl97=findViewById(R.id.seclbl97);
         linelbl97=findViewById(R.id.linelbl97);
         secS3Q97 = findViewById(R.id.secS3Q97);
         lineS3Q97 = findViewById(R.id.lineS3Q97);
         lblS3Q97 = findViewById(R.id.lblS3Q97);
         VlblS3Q97 = findViewById(R.id.VlblS3Q97);
         chkS3Q97 = findViewById(R.id.chkS3Q97);
         secS3Q98 = findViewById(R.id.secS3Q98);
         lineS3Q98 = findViewById(R.id.lineS3Q98);
         lblS3Q98 = findViewById(R.id.lblS3Q98);
         VlblS3Q98 = findViewById(R.id.VlblS3Q98);
         chkS3Q98 = findViewById(R.id.chkS3Q98);
         secS3Q99 = findViewById(R.id.secS3Q99);
         lineS3Q99 = findViewById(R.id.lineS3Q99);
         lblS3Q99 = findViewById(R.id.lblS3Q99);
         VlblS3Q99 = findViewById(R.id.VlblS3Q99);
         chkS3Q99 = findViewById(R.id.chkS3Q99);
         secS3Q910 = findViewById(R.id.secS3Q910);
         lineS3Q910 = findViewById(R.id.lineS3Q910);
         lblS3Q910 = findViewById(R.id.lblS3Q910);
         VlblS3Q910 = findViewById(R.id.VlblS3Q910);
         chkS3Q910 = findViewById(R.id.chkS3Q910);
         secS3Q911 = findViewById(R.id.secS3Q911);
         lineS3Q911 = findViewById(R.id.lineS3Q911);
         lblS3Q911 = findViewById(R.id.lblS3Q911);
         VlblS3Q911 = findViewById(R.id.VlblS3Q911);
         chkS3Q911 = findViewById(R.id.chkS3Q911);
         secS3Q912 = findViewById(R.id.secS3Q912);
         lineS3Q912 = findViewById(R.id.lineS3Q912);
         lblS3Q912 = findViewById(R.id.lblS3Q912);
         VlblS3Q912 = findViewById(R.id.VlblS3Q912);
         chkS3Q912 = findViewById(R.id.chkS3Q912);
         secS3Q913 = findViewById(R.id.secS3Q913);
         lineS3Q913 = findViewById(R.id.lineS3Q913);
         lblS3Q913 = findViewById(R.id.lblS3Q913);
         VlblS3Q913 = findViewById(R.id.VlblS3Q913);
         chkS3Q913 = findViewById(R.id.chkS3Q913);
         secS3Q914 = findViewById(R.id.secS3Q914);
         lineS3Q914 = findViewById(R.id.lineS3Q914);
         lblS3Q914 = findViewById(R.id.lblS3Q914);
         VlblS3Q914 = findViewById(R.id.VlblS3Q914);
         chkS3Q914 = findViewById(R.id.chkS3Q914);
         chkS3Q914.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secS3Q914_1.setVisibility(View.VISIBLE);
                    lineS3Q914_1.setVisibility(View.VISIBLE);
                 }
                 else {
                    secS3Q914_1.setVisibility(View.GONE);
                    lineS3Q914_1.setVisibility(View.GONE);
                    txtS3Q914_1.setText("");
                 }
                 }
         });
         secS3Q914_1 = findViewById(R.id.secS3Q914_1);
         lineS3Q914_1 = findViewById(R.id.lineS3Q914_1);
         lblS3Q914_1 = findViewById(R.id.lblS3Q914_1);
         VlblS3Q914_1 = findViewById(R.id.VlblS3Q914_1);
         txtS3Q914_1 = findViewById(R.id.txtS3Q914_1);
         txtS3Q914_1.setAdapter(C.getArrayAdapter("Select distinct S3Q914_1 from "+ TableName +" order by S3Q914_1"));

         txtS3Q914_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtS3Q914_1.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtS3Q914_1.getRight() - txtS3Q914_1.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secS3Q10 = findViewById(R.id.secS3Q10);
         lineS3Q10 = findViewById(R.id.lineS3Q10);
         lblS3Q10 = findViewById(R.id.lblS3Q10);
         VlblS3Q10 = findViewById(R.id.VlblS3Q10);
         spnS3Q10 = findViewById(R.id.spnS3Q10);
         List<String> listS3Q10 = new ArrayList<String>();
         
         listS3Q10.add("");
         listS3Q10.add("1-Self");
         listS3Q10.add("2-Husband");
         listS3Q10.add("3-Joint decision");
         listS3Q10.add("4-Others (please specify)");
         spnS3Q10.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listS3Q10)));

         spnS3Q10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnS3Q10.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnS3Q10.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("1"))
                 {
                    secS3Q10_1.setVisibility(View.GONE);
                    lineS3Q10_1.setVisibility(View.GONE);
                    txtS3Q10_1.setText("");
                 }
                 else if(spnData.equalsIgnoreCase("2"))
                 {
                    secS3Q10_1.setVisibility(View.GONE);
                    lineS3Q10_1.setVisibility(View.GONE);
                    txtS3Q10_1.setText("");
                 }
                 else if(spnData.equalsIgnoreCase("3"))
                 {
                    secS3Q10_1.setVisibility(View.GONE);
                    lineS3Q10_1.setVisibility(View.GONE);
                    txtS3Q10_1.setText("");
                 }
                 else
                 {
                    secS3Q10_1.setVisibility(View.VISIBLE);
                    lineS3Q10_1.setVisibility(View.VISIBLE);
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secS3Q10_1 = findViewById(R.id.secS3Q10_1);
         lineS3Q10_1 = findViewById(R.id.lineS3Q10_1);
         lblS3Q10_1 = findViewById(R.id.lblS3Q10_1);
         VlblS3Q10_1 = findViewById(R.id.VlblS3Q10_1);
         txtS3Q10_1 = findViewById(R.id.txtS3Q10_1);
         txtS3Q10_1.setAdapter(C.getArrayAdapter("Select distinct S3Q10_1 from "+ TableName +" order by S3Q10_1"));

         txtS3Q10_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtS3Q10_1.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtS3Q10_1.getRight() - txtS3Q10_1.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secS3Q11 = findViewById(R.id.secS3Q11);
         lineS3Q11 = findViewById(R.id.lineS3Q11);
         lblS3Q11 =  findViewById(R.id.lblS3Q11);
         VlblS3Q11 =  findViewById(R.id.VlblS3Q11);
         rdogrpS3Q11 =  findViewById(R.id.rdogrpS3Q11);
         rdoS3Q111 =  findViewById(R.id.rdoS3Q111);
         rdoS3Q112 =  findViewById(R.id.rdoS3Q112);
         rdoS3Q113 =  findViewById(R.id.rdoS3Q113);
        rdogrpS3Q11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
           @Override
           public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
              String rbData = "";
              RadioButton rb;
              String[] d_rdogrpS3Q11 = new String[] {"1","2","3"};
              for (int i = 0; i < rdogrpS3Q11.getChildCount(); i++)
              {
                 rb = (RadioButton) rdogrpS3Q11.getChildAt(i);
                 if (rb.isChecked()) rbData = d_rdogrpS3Q11[i];
              }

              if(rbData.equalsIgnoreCase("2"))
              {
                 seclblS3Q11.setVisibility(View.GONE);
                 linelblS3Q11.setVisibility(View.GONE);
                 secS3Q111.setVisibility(View.GONE);
                 lineS3Q111.setVisibility(View.GONE);
                 chkS3Q111.setChecked(false);
                 secS3Q112.setVisibility(View.GONE);
                 lineS3Q112.setVisibility(View.GONE);
                 chkS3Q112.setChecked(false);
                 secS3Q113.setVisibility(View.GONE);
                 lineS3Q113.setVisibility(View.GONE);
                 chkS3Q113.setChecked(false);
                 secS3Q114.setVisibility(View.GONE);
                 lineS3Q114.setVisibility(View.GONE);
                 chkS3Q114.setChecked(false);
                 secS3Q115.setVisibility(View.GONE);
                 lineS3Q115.setVisibility(View.GONE);
                 chkS3Q115.setChecked(false);
                 secS3Q116.setVisibility(View.GONE);
                 lineS3Q116.setVisibility(View.GONE);
                 chkS3Q116.setChecked(false);
                 secS3Q117.setVisibility(View.GONE);
                 lineS3Q117.setVisibility(View.GONE);
                 chkS3Q117.setChecked(false);
                 secS3Q117_1.setVisibility(View.GONE);
                 lineS3Q117_1.setVisibility(View.GONE);
                 txtS3Q117_1.setText("");
              }
              else if(rbData.equalsIgnoreCase("3"))
              {
                 seclblS3Q11.setVisibility(View.GONE);
                 linelblS3Q11.setVisibility(View.GONE);
                 secS3Q111.setVisibility(View.GONE);
                 lineS3Q111.setVisibility(View.GONE);
                 chkS3Q111.setChecked(false);
                 secS3Q112.setVisibility(View.GONE);
                 lineS3Q112.setVisibility(View.GONE);
                 chkS3Q112.setChecked(false);
                 secS3Q113.setVisibility(View.GONE);
                 lineS3Q113.setVisibility(View.GONE);
                 chkS3Q113.setChecked(false);
                 secS3Q114.setVisibility(View.GONE);
                 lineS3Q114.setVisibility(View.GONE);
                 chkS3Q114.setChecked(false);
                 secS3Q115.setVisibility(View.GONE);
                 lineS3Q115.setVisibility(View.GONE);
                 chkS3Q115.setChecked(false);
                 secS3Q116.setVisibility(View.GONE);
                 lineS3Q116.setVisibility(View.GONE);
                 chkS3Q116.setChecked(false);
                 secS3Q117.setVisibility(View.GONE);
                 lineS3Q117.setVisibility(View.GONE);
                 chkS3Q117.setChecked(false);
                 secS3Q117_1.setVisibility(View.GONE);
                 lineS3Q117_1.setVisibility(View.GONE);
                 txtS3Q117_1.setText("");
              }
              else
              {
                 seclblS3Q11.setVisibility(View.VISIBLE);
                 linelblS3Q11.setVisibility(View.VISIBLE);
                 secS3Q111.setVisibility(View.VISIBLE);
                 lineS3Q111.setVisibility(View.VISIBLE);
                 secS3Q112.setVisibility(View.VISIBLE);
                 lineS3Q112.setVisibility(View.VISIBLE);
                 secS3Q113.setVisibility(View.VISIBLE);
                 lineS3Q113.setVisibility(View.VISIBLE);
                 secS3Q114.setVisibility(View.VISIBLE);
                 lineS3Q114.setVisibility(View.VISIBLE);
                 secS3Q115.setVisibility(View.VISIBLE);
                 lineS3Q115.setVisibility(View.VISIBLE);
                 secS3Q116.setVisibility(View.VISIBLE);
                 lineS3Q116.setVisibility(View.VISIBLE);
                 secS3Q117.setVisibility(View.VISIBLE);
                 lineS3Q117.setVisibility(View.VISIBLE);
              }
           }
           public void onNothingSelected(AdapterView<?> adapterView) {
              return;
           }
        });

        seclblS3Q11=findViewById(R.id.seclblS3Q11);
         linelblS3Q11=findViewById(R.id.linelblS3Q11);
         secS3Q111 = findViewById(R.id.secS3Q111);
         lineS3Q111 = findViewById(R.id.lineS3Q111);
         lblS3Q111 = findViewById(R.id.lblS3Q111);
         VlblS3Q111 = findViewById(R.id.VlblS3Q111);
         chkS3Q111 = findViewById(R.id.chkS3Q111);
         secS3Q112 = findViewById(R.id.secS3Q112);
         lineS3Q112 = findViewById(R.id.lineS3Q112);
         lblS3Q112 = findViewById(R.id.lblS3Q112);
         VlblS3Q112 = findViewById(R.id.VlblS3Q112);
         chkS3Q112 = findViewById(R.id.chkS3Q112);
         secS3Q113 = findViewById(R.id.secS3Q113);
         lineS3Q113 = findViewById(R.id.lineS3Q113);
         lblS3Q113 = findViewById(R.id.lblS3Q113);
         VlblS3Q113 = findViewById(R.id.VlblS3Q113);
         chkS3Q113 = findViewById(R.id.chkS3Q113);
         secS3Q114 = findViewById(R.id.secS3Q114);
         lineS3Q114 = findViewById(R.id.lineS3Q114);
         lblS3Q114 = findViewById(R.id.lblS3Q114);
         VlblS3Q114 = findViewById(R.id.VlblS3Q114);
         chkS3Q114 = findViewById(R.id.chkS3Q114);
         secS3Q115 = findViewById(R.id.secS3Q115);
         lineS3Q115 = findViewById(R.id.lineS3Q115);
         lblS3Q115 = findViewById(R.id.lblS3Q115);
         VlblS3Q115 = findViewById(R.id.VlblS3Q115);
         chkS3Q115 = findViewById(R.id.chkS3Q115);
         secS3Q116 = findViewById(R.id.secS3Q116);
         lineS3Q116 = findViewById(R.id.lineS3Q116);
         lblS3Q116 = findViewById(R.id.lblS3Q116);
         VlblS3Q116 = findViewById(R.id.VlblS3Q116);
         chkS3Q116 = findViewById(R.id.chkS3Q116);
         secS3Q117 = findViewById(R.id.secS3Q117);
         lineS3Q117 = findViewById(R.id.lineS3Q117);
         lblS3Q117 = findViewById(R.id.lblS3Q117);
         VlblS3Q117 = findViewById(R.id.VlblS3Q117);
         chkS3Q117 = findViewById(R.id.chkS3Q117);
         chkS3Q117.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secS3Q117_1.setVisibility(View.VISIBLE);
                    lineS3Q117_1.setVisibility(View.VISIBLE);
                 }
                 else {
                    secS3Q117_1.setVisibility(View.GONE);
                    lineS3Q117_1.setVisibility(View.GONE);
                    txtS3Q117_1.setText("");
                 }
                 }
         });
         secS3Q117_1 = findViewById(R.id.secS3Q117_1);
         lineS3Q117_1 = findViewById(R.id.lineS3Q117_1);
         lblS3Q117_1 = findViewById(R.id.lblS3Q117_1);
         VlblS3Q117_1 = findViewById(R.id.VlblS3Q117_1);
         txtS3Q117_1 = findViewById(R.id.txtS3Q117_1);
         txtS3Q117_1.setAdapter(C.getArrayAdapter("Select distinct S3Q117_1 from "+ TableName +" order by S3Q117_1"));

         txtS3Q117_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtS3Q117_1.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtS3Q117_1.getRight() - txtS3Q117_1.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
     }
     catch(Exception  e)
     {
         Connection.MessageBox(CONTRACEPTIVE_BEHAVIOUR.this, e.getMessage());
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
         	Connection.MessageBox(CONTRACEPTIVE_BEHAVIOUR.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         CONTRACEPTIVE_BEHAVIOUR_DataModel objSave = new CONTRACEPTIVE_BEHAVIOUR_DataModel();
         objSave.setuuid(txtuuid.getText().toString());
         objSave.setMemID(txtMemID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         String[] d_rdogrpS1Q1 = new String[] {"1","2"};
         objSave.setS1Q1("");
         for (int i = 0; i < rdogrpS1Q1.getChildCount(); i++)
         {
             rb = (RadioButton) rdogrpS1Q1.getChildAt(i);
             if (rb.isChecked()) objSave.setS1Q1(d_rdogrpS1Q1[i]);
         }

         objSave.setS1Q11((chkS1Q11.isChecked() ? "1" : (secS1Q11.isShown() ? "2" : "")));
         objSave.setS1Q12((chkS1Q12.isChecked() ? "1" : (secS1Q12.isShown() ? "2" : "")));
         objSave.setS1Q13((chkS1Q13.isChecked() ? "1" : (secS1Q13.isShown() ? "2" : "")));
         objSave.setS1Q14((chkS1Q14.isChecked() ? "1" : (secS1Q14.isShown() ? "2" : "")));
         objSave.setS1Q15((chkS1Q15.isChecked() ? "1" : (secS1Q15.isShown() ? "2" : "")));
         objSave.setS1Q16((chkS1Q16.isChecked() ? "1" : (secS1Q16.isShown() ? "2" : "")));
         objSave.setS1Q17((chkS1Q17.isChecked() ? "1" : (secS1Q17.isShown() ? "2" : "")));
         objSave.setS1Q17_1(txtS1Q17_1.getText().toString());
         objSave.setS1Q21((chkS1Q21.isChecked() ? "1" : (secS1Q21.isShown() ? "2" : "")));
         objSave.setS1Q22((chkS1Q22.isChecked() ? "1" : (secS1Q22.isShown() ? "2" : "")));
         objSave.setS1Q23((chkS1Q23.isChecked() ? "1" : (secS1Q23.isShown() ? "2" : "")));
         objSave.setS1Q24((chkS1Q24.isChecked() ? "1" : (secS1Q24.isShown() ? "2" : "")));
         objSave.setS1Q25((chkS1Q25.isChecked() ? "1" : (secS1Q25.isShown() ? "2" : "")));
         objSave.setS1Q26((chkS1Q26.isChecked() ? "1" : (secS1Q26.isShown() ? "2" : "")));
         objSave.setS1Q27((chkS1Q27.isChecked() ? "1" : (secS1Q27.isShown() ? "2" : "")));
         objSave.setS1Q28((chkS1Q28.isChecked() ? "1" : (secS1Q28.isShown() ? "2" : "")));
         objSave.setS1Q28_1(txtS1Q28_1.getText().toString());
         String[] d_rdogrpS1Q3 = new String[] {"1","2"};
         objSave.setS1Q3("");
         for (int i = 0; i < rdogrpS1Q3.getChildCount(); i++)
         {
             rb = (RadioButton) rdogrpS1Q3.getChildAt(i);
             if (rb.isChecked()) objSave.setS1Q3(d_rdogrpS1Q3[i]);
         }

         objSave.setS1Q31((chkS1Q31.isChecked() ? "1" : (secS1Q31.isShown() ? "2" : "")));
         objSave.setS1Q32((chkS1Q32.isChecked() ? "1" : (secS1Q32.isShown() ? "2" : "")));
         objSave.setS1Q33((chkS1Q33.isChecked() ? "1" : (secS1Q33.isShown() ? "2" : "")));
         objSave.setS1Q34((chkS1Q34.isChecked() ? "1" : (secS1Q34.isShown() ? "2" : "")));
         objSave.setS1Q35((chkS1Q35.isChecked() ? "1" : (secS1Q35.isShown() ? "2" : "")));
         objSave.setS1Q36((chkS1Q36.isChecked() ? "1" : (secS1Q36.isShown() ? "2" : "")));
         objSave.setS1Q37((chkS1Q37.isChecked() ? "1" : (secS1Q37.isShown() ? "2" : "")));
         objSave.setS1Q38((chkS1Q38.isChecked() ? "1" : (secS1Q38.isShown() ? "2" : "")));
         objSave.setS1Q39((chkS1Q39.isChecked() ? "1" : (secS1Q39.isShown() ? "2" : "")));
         objSave.setS1Q310((chkS1Q310.isChecked() ? "1" : (secS1Q310.isShown() ? "2" : "")));
         objSave.setS1Q311((chkS1Q311.isChecked() ? "1" : (secS1Q311.isShown() ? "2" : "")));
         objSave.setS1Q311_1(txtS1Q311_1.getText().toString());
         String[] d_rdogrpS2Q4 = new String[] {"1","2"};
         objSave.setS2Q4("");
         for (int i = 0; i < rdogrpS2Q4.getChildCount(); i++)
         {
             rb = (RadioButton) rdogrpS2Q4.getChildAt(i);
             if (rb.isChecked()) objSave.setS2Q4(d_rdogrpS2Q4[i]);
         }

         objSave.setS2Q51((chkS2Q51.isChecked() ? "1" : (secS2Q51.isShown() ? "2" : "")));
         objSave.setS2Q52((chkS2Q52.isChecked() ? "1" : (secS2Q52.isShown() ? "2" : "")));
         objSave.setS2Q53((chkS2Q53.isChecked() ? "1" : (secS2Q53.isShown() ? "2" : "")));
         objSave.setS2Q54((chkS2Q54.isChecked() ? "1" : (secS2Q54.isShown() ? "2" : "")));
         objSave.setS2Q55((chkS2Q55.isChecked() ? "1" : (secS2Q55.isShown() ? "2" : "")));
         String[] d_rdogrpS2Q6 = new String[] {"1","2","3","4"};
         objSave.setS2Q6("");
         for (int i = 0; i < rdogrpS2Q6.getChildCount(); i++)
         {
             rb = (RadioButton) rdogrpS2Q6.getChildAt(i);
             if (rb.isChecked()) objSave.setS2Q6(d_rdogrpS2Q6[i]);
         }

         String[] d_rdogrpS2Q7 = new String[] {"1","2","3","4"};
         objSave.setS2Q7("");
         for (int i = 0; i < rdogrpS2Q7.getChildCount(); i++)
         {
             rb = (RadioButton) rdogrpS2Q7.getChildAt(i);
             if (rb.isChecked()) objSave.setS2Q7(d_rdogrpS2Q7[i]);
         }

         objSave.setS2Q81((chkS2Q81.isChecked() ? "1" : (secS2Q81.isShown() ? "2" : "")));
         objSave.setS2Q82((chkS2Q82.isChecked() ? "1" : (secS2Q82.isShown() ? "2" : "")));
         objSave.setS2Q83((chkS2Q83.isChecked() ? "1" : (secS2Q83.isShown() ? "2" : "")));
         objSave.setS2Q84((chkS2Q84.isChecked() ? "1" : (secS2Q84.isShown() ? "2" : "")));
         objSave.setS2Q84_1(txtS2Q84_1.getText().toString());
         String[] d_rdogrpS3Q9 = new String[] {"1","2"};
         objSave.setS3Q9("");
         for (int i = 0; i < rdogrpS3Q9.getChildCount(); i++)
         {
             rb = (RadioButton) rdogrpS3Q9.getChildAt(i);
             if (rb.isChecked()) objSave.setS3Q9(d_rdogrpS3Q9[i]);
         }

         objSave.setS3Q91((chkS3Q91.isChecked() ? "1" : (secS3Q91.isShown() ? "2" : "")));
         objSave.setS3Q92((chkS3Q92.isChecked() ? "1" : (secS3Q92.isShown() ? "2" : "")));
         objSave.setS3Q93((chkS3Q93.isChecked() ? "1" : (secS3Q93.isShown() ? "2" : "")));
         objSave.setS3Q94((chkS3Q94.isChecked() ? "1" : (secS3Q94.isShown() ? "2" : "")));
         objSave.setS3Q95((chkS3Q95.isChecked() ? "1" : (secS3Q95.isShown() ? "2" : "")));
         objSave.setS3Q96((chkS3Q96.isChecked() ? "1" : (secS3Q96.isShown() ? "2" : "")));
         objSave.setS3Q97((chkS3Q97.isChecked() ? "1" : (secS3Q97.isShown() ? "2" : "")));
         objSave.setS3Q98((chkS3Q98.isChecked() ? "1" : (secS3Q98.isShown() ? "2" : "")));
         objSave.setS3Q99((chkS3Q99.isChecked() ? "1" : (secS3Q99.isShown() ? "2" : "")));
         objSave.setS3Q910((chkS3Q910.isChecked() ? "1" : (secS3Q910.isShown() ? "2" : "")));
         objSave.setS3Q911((chkS3Q911.isChecked() ? "1" : (secS3Q911.isShown() ? "2" : "")));
         objSave.setS3Q912((chkS3Q912.isChecked() ? "1" : (secS3Q912.isShown() ? "2" : "")));
         objSave.setS3Q913((chkS3Q913.isChecked() ? "1" : (secS3Q913.isShown() ? "2" : "")));
         objSave.setS3Q914((chkS3Q914.isChecked() ? "1" : (secS3Q914.isShown() ? "2" : "")));
         objSave.setS3Q914_1(txtS3Q914_1.getText().toString());
         objSave.setS3Q10(spnS3Q10.getSelectedItemPosition() == 0 ? "" : spnS3Q10.getSelectedItem().toString().split("-")[0]);
         objSave.setS3Q10_1(txtS3Q10_1.getText().toString());
         String[] d_rdogrpS3Q11 = new String[] {"1","2","3"};
         objSave.setS3Q11("");
         for (int i = 0; i < rdogrpS3Q11.getChildCount(); i++)
         {
             rb = (RadioButton) rdogrpS3Q11.getChildAt(i);
             if (rb.isChecked()) objSave.setS3Q11(d_rdogrpS3Q11[i]);
         }

         objSave.setS3Q111((chkS3Q111.isChecked() ? "1" : (secS3Q111.isShown() ? "2" : "")));
         objSave.setS3Q112((chkS3Q112.isChecked() ? "1" : (secS3Q112.isShown() ? "2" : "")));
         objSave.setS3Q113((chkS3Q113.isChecked() ? "1" : (secS3Q113.isShown() ? "2" : "")));
         objSave.setS3Q114((chkS3Q114.isChecked() ? "1" : (secS3Q114.isShown() ? "2" : "")));
         objSave.setS3Q115((chkS3Q115.isChecked() ? "1" : (secS3Q115.isShown() ? "2" : "")));
         objSave.setS3Q116((chkS3Q116.isChecked() ? "1" : (secS3Q116.isShown() ? "2" : "")));
         objSave.setS3Q117((chkS3Q117.isChecked() ? "1" : (secS3Q117.isShown() ? "2" : "")));
         objSave.setS3Q117_1(txtS3Q117_1.getText().toString());
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

             Toast.makeText(CONTRACEPTIVE_BEHAVIOUR.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
             finish();
         }
         else{
             Connection.MessageBox(CONTRACEPTIVE_BEHAVIOUR.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(CONTRACEPTIVE_BEHAVIOUR.this, e.getMessage());
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
         if(txtuuid.getText().toString().length()==0 & secuuid.isShown())
           {
             ValidationMsg += "\n"+ lbluuid.getText().toString() + " Required field: "+ Vlbluuid.getText().toString();
             secuuid.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMemID.getText().toString().length()==0 & secMemID.isShown())
           {
             ValidationMsg += "\n"+ lblMemID.getText().toString() + " Required field: "+ VlblMemID.getText().toString();
             secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
           {
             ValidationMsg += "\n"+ lblHHID.getText().toString() + " Required field: "+ VlblHHID.getText().toString();
             secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoS1Q11.isChecked() & !rdoS1Q12.isChecked() & secS1Q1.isShown())
           {
             ValidationMsg += "\n"+ lblS1Q1.getText().toString() + " Required field: "+ VlblS1Q1.getText().toString();
           }
         if(txtS1Q17_1.getText().toString().length()==0 & secS1Q17_1.isShown())
           {
             ValidationMsg += "\n"+ lblS1Q17_1.getText().toString() + " Required field: "+ VlblS1Q17_1.getText().toString();
             secS1Q17_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtS1Q28_1.getText().toString().length()==0 & secS1Q28_1.isShown())
           {
             ValidationMsg += "\n"+ lblS1Q28_1.getText().toString() + " Required field: "+ VlblS1Q28_1.getText().toString();
             secS1Q28_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoS1Q31.isChecked() & !rdoS1Q32.isChecked() & secS1Q3.isShown())
           {
             ValidationMsg += "\n"+ lblS1Q3.getText().toString() + " Required field: "+ VlblS1Q3.getText().toString();
           }
         if(txtS1Q311_1.getText().toString().length()==0 & secS1Q311_1.isShown())
           {
             ValidationMsg += "\n"+ lblS1Q311_1.getText().toString() + " Required field: "+ VlblS1Q311_1.getText().toString();
             secS1Q311_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoS2Q41.isChecked() & !rdoS2Q42.isChecked() & secS2Q4.isShown())
           {
             ValidationMsg += "\n"+ lblS2Q4.getText().toString() + " Required field: "+ VlblS2Q4.getText().toString();
           }
         if(!rdoS2Q61.isChecked() & !rdoS2Q62.isChecked() & !rdoS2Q63.isChecked() & !rdoS2Q64.isChecked() & secS2Q6.isShown())
           {
             ValidationMsg += "\n"+ lblS2Q6.getText().toString() + " Required field: "+ VlblS2Q6.getText().toString();
           }
         if(!rdoS2Q71.isChecked() & !rdoS2Q72.isChecked() & !rdoS2Q73.isChecked() & !rdoS2Q74.isChecked() & secS2Q7.isShown())
           {
             ValidationMsg += "\n"+ lblS2Q7.getText().toString() + " Required field: "+ VlblS2Q7.getText().toString();
           }
         if(txtS2Q84_1.getText().toString().length()==0 & secS2Q84_1.isShown())
           {
             ValidationMsg += "\n"+ lblS2Q84_1.getText().toString() + " Required field: "+ VlblS2Q84_1.getText().toString();
             secS2Q84_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoS3Q91.isChecked() & !rdoS3Q92.isChecked() & secS3Q9.isShown())
           {
             ValidationMsg += "\n"+ lblS3Q9.getText().toString() + " Required field: "+ VlblS3Q9.getText().toString();
           }
         if(txtS3Q914_1.getText().toString().length()==0 & secS3Q914_1.isShown())
           {
             ValidationMsg += "\n"+ lblS3Q914_1.getText().toString() + " Required field: "+ VlblS3Q914_1.getText().toString();
             secS3Q914_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnS3Q10.getSelectedItemPosition()==0  & secS3Q10.isShown())
           {
             ValidationMsg += "\n"+ lblS3Q10.getText().toString() + " Required field: "+ VlblS3Q10.getText().toString();
             secS3Q10.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtS3Q10_1.getText().toString().length()==0 & secS3Q10_1.isShown())
           {
             ValidationMsg += "\n"+ lblS3Q10_1.getText().toString() + " Required field: "+ VlblS3Q10_1.getText().toString();
             secS3Q10_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoS3Q111.isChecked() & !rdoS3Q112.isChecked() & !rdoS3Q113.isChecked() & secS3Q11.isShown())
           {
             ValidationMsg += "\n"+ lblS3Q11.getText().toString() + " Required field: "+ VlblS3Q11.getText().toString();
           }
         if(txtS3Q117_1.getText().toString().length()==0 & secS3Q117_1.isShown())
           {
             ValidationMsg += "\n"+ lblS3Q117_1.getText().toString() + " Required field: "+ VlblS3Q117_1.getText().toString();
             secS3Q117_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secuuid.setBackgroundColor(Color.WHITE);
             secMemID.setBackgroundColor(Color.WHITE);
             secHHID.setBackgroundColor(Color.WHITE);
             secS1Q17_1.setBackgroundColor(Color.WHITE);
             secS1Q28_1.setBackgroundColor(Color.WHITE);
             secS1Q311_1.setBackgroundColor(Color.WHITE);
             secS2Q84_1.setBackgroundColor(Color.WHITE);
             secS3Q914_1.setBackgroundColor(Color.WHITE);
             secS3Q10.setBackgroundColor(Color.WHITE);
             secS3Q10_1.setBackgroundColor(Color.WHITE);
             secS3Q117_1.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String uuid)
     {
       try
        {     
           RadioButton rb;
           CONTRACEPTIVE_BEHAVIOUR_DataModel d = new CONTRACEPTIVE_BEHAVIOUR_DataModel();
           String SQL = "Select * from "+ TableName +"  Where uuid='"+ uuid +"'";
           List<CONTRACEPTIVE_BEHAVIOUR_DataModel> data = d.SelectAll(this, SQL);
           for(CONTRACEPTIVE_BEHAVIOUR_DataModel item : data){
             txtuuid.setText(item.getuuid());
             txtMemID.setText(item.getMemID());
             txtHHID.setText(item.getHHID());
             String[] d_rdogrpS1Q1 = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpS1Q1.length; i++)
             {
                 if (String.valueOf(item.getS1Q1()).equals(String.valueOf(d_rdogrpS1Q1[i])))
                 {
                     rb = (RadioButton) rdogrpS1Q1.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getS1Q11()).equals("1"))
             {
                chkS1Q11.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q11()).equals("2"))
             {
                chkS1Q11.setChecked(false);
             }
             if(String.valueOf(item.getS1Q12()).equals("1"))
             {
                chkS1Q12.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q12()).equals("2"))
             {
                chkS1Q12.setChecked(false);
             }
             if(String.valueOf(item.getS1Q13()).equals("1"))
             {
                chkS1Q13.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q13()).equals("2"))
             {
                chkS1Q13.setChecked(false);
             }
             if(String.valueOf(item.getS1Q14()).equals("1"))
             {
                chkS1Q14.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q14()).equals("2"))
             {
                chkS1Q14.setChecked(false);
             }
             if(String.valueOf(item.getS1Q15()).equals("1"))
             {
                chkS1Q15.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q15()).equals("2"))
             {
                chkS1Q15.setChecked(false);
             }
             if(String.valueOf(item.getS1Q16()).equals("1"))
             {
                chkS1Q16.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q16()).equals("2"))
             {
                chkS1Q16.setChecked(false);
             }
             if(String.valueOf(item.getS1Q17()).equals("1"))
             {
                chkS1Q17.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q17()).equals("2"))
             {
                chkS1Q17.setChecked(false);
             }
             txtS1Q17_1.setText(item.getS1Q17_1());
             if(String.valueOf(item.getS1Q21()).equals("1"))
             {
                chkS1Q21.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q21()).equals("2"))
             {
                chkS1Q21.setChecked(false);
             }
             if(String.valueOf(item.getS1Q22()).equals("1"))
             {
                chkS1Q22.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q22()).equals("2"))
             {
                chkS1Q22.setChecked(false);
             }
             if(String.valueOf(item.getS1Q23()).equals("1"))
             {
                chkS1Q23.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q23()).equals("2"))
             {
                chkS1Q23.setChecked(false);
             }
             if(String.valueOf(item.getS1Q24()).equals("1"))
             {
                chkS1Q24.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q24()).equals("2"))
             {
                chkS1Q24.setChecked(false);
             }
             if(String.valueOf(item.getS1Q25()).equals("1"))
             {
                chkS1Q25.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q25()).equals("2"))
             {
                chkS1Q25.setChecked(false);
             }
             if(String.valueOf(item.getS1Q26()).equals("1"))
             {
                chkS1Q26.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q26()).equals("2"))
             {
                chkS1Q26.setChecked(false);
             }
             if(String.valueOf(item.getS1Q27()).equals("1"))
             {
                chkS1Q27.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q27()).equals("2"))
             {
                chkS1Q27.setChecked(false);
             }
             if(String.valueOf(item.getS1Q28()).equals("1"))
             {
                chkS1Q28.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q28()).equals("2"))
             {
                chkS1Q28.setChecked(false);
             }
             txtS1Q28_1.setText(item.getS1Q28_1());
             String[] d_rdogrpS1Q3 = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpS1Q3.length; i++)
             {
                 if (String.valueOf(item.getS1Q3()).equals(String.valueOf(d_rdogrpS1Q3[i])))
                 {
                     rb = (RadioButton) rdogrpS1Q3.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getS1Q31()).equals("1"))
             {
                chkS1Q31.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q31()).equals("2"))
             {
                chkS1Q31.setChecked(false);
             }
             if(String.valueOf(item.getS1Q32()).equals("1"))
             {
                chkS1Q32.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q32()).equals("2"))
             {
                chkS1Q32.setChecked(false);
             }
             if(String.valueOf(item.getS1Q33()).equals("1"))
             {
                chkS1Q33.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q33()).equals("2"))
             {
                chkS1Q33.setChecked(false);
             }
             if(String.valueOf(item.getS1Q34()).equals("1"))
             {
                chkS1Q34.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q34()).equals("2"))
             {
                chkS1Q34.setChecked(false);
             }
             if(String.valueOf(item.getS1Q35()).equals("1"))
             {
                chkS1Q35.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q35()).equals("2"))
             {
                chkS1Q35.setChecked(false);
             }
             if(String.valueOf(item.getS1Q36()).equals("1"))
             {
                chkS1Q36.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q36()).equals("2"))
             {
                chkS1Q36.setChecked(false);
             }
             if(String.valueOf(item.getS1Q37()).equals("1"))
             {
                chkS1Q37.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q37()).equals("2"))
             {
                chkS1Q37.setChecked(false);
             }
             if(String.valueOf(item.getS1Q38()).equals("1"))
             {
                chkS1Q38.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q38()).equals("2"))
             {
                chkS1Q38.setChecked(false);
             }
             if(String.valueOf(item.getS1Q39()).equals("1"))
             {
                chkS1Q39.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q39()).equals("2"))
             {
                chkS1Q39.setChecked(false);
             }
             if(String.valueOf(item.getS1Q310()).equals("1"))
             {
                chkS1Q310.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q310()).equals("2"))
             {
                chkS1Q310.setChecked(false);
             }
             if(String.valueOf(item.getS1Q311()).equals("1"))
             {
                chkS1Q311.setChecked(true);
             }
             else if(String.valueOf(item.getS1Q311()).equals("2"))
             {
                chkS1Q311.setChecked(false);
             }
             txtS1Q311_1.setText(item.getS1Q311_1());
             String[] d_rdogrpS2Q4 = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpS2Q4.length; i++)
             {
                 if (String.valueOf(item.getS2Q4()).equals(String.valueOf(d_rdogrpS2Q4[i])))
                 {
                     rb = (RadioButton) rdogrpS2Q4.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getS2Q51()).equals("1"))
             {
                chkS2Q51.setChecked(true);
             }
             else if(String.valueOf(item.getS2Q51()).equals("2"))
             {
                chkS2Q51.setChecked(false);
             }
             if(String.valueOf(item.getS2Q52()).equals("1"))
             {
                chkS2Q52.setChecked(true);
             }
             else if(String.valueOf(item.getS2Q52()).equals("2"))
             {
                chkS2Q52.setChecked(false);
             }
             if(String.valueOf(item.getS2Q53()).equals("1"))
             {
                chkS2Q53.setChecked(true);
             }
             else if(String.valueOf(item.getS2Q53()).equals("2"))
             {
                chkS2Q53.setChecked(false);
             }
             if(String.valueOf(item.getS2Q54()).equals("1"))
             {
                chkS2Q54.setChecked(true);
             }
             else if(String.valueOf(item.getS2Q54()).equals("2"))
             {
                chkS2Q54.setChecked(false);
             }
             if(String.valueOf(item.getS2Q55()).equals("1"))
             {
                chkS2Q55.setChecked(true);
             }
             else if(String.valueOf(item.getS2Q55()).equals("2"))
             {
                chkS2Q55.setChecked(false);
             }
             String[] d_rdogrpS2Q6 = new String[] {"1","2","3","4"};
             for (int i = 0; i < d_rdogrpS2Q6.length; i++)
             {
                 if (String.valueOf(item.getS2Q6()).equals(String.valueOf(d_rdogrpS2Q6[i])))
                 {
                     rb = (RadioButton) rdogrpS2Q6.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpS2Q7 = new String[] {"1","2","3","4"};
             for (int i = 0; i < d_rdogrpS2Q7.length; i++)
             {
                 if (String.valueOf(item.getS2Q7()).equals(String.valueOf(d_rdogrpS2Q7[i])))
                 {
                     rb = (RadioButton) rdogrpS2Q7.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getS2Q81()).equals("1"))
             {
                chkS2Q81.setChecked(true);
             }
             else if(String.valueOf(item.getS2Q81()).equals("2"))
             {
                chkS2Q81.setChecked(false);
             }
             if(String.valueOf(item.getS2Q82()).equals("1"))
             {
                chkS2Q82.setChecked(true);
             }
             else if(String.valueOf(item.getS2Q82()).equals("2"))
             {
                chkS2Q82.setChecked(false);
             }
             if(String.valueOf(item.getS2Q83()).equals("1"))
             {
                chkS2Q83.setChecked(true);
             }
             else if(String.valueOf(item.getS2Q83()).equals("2"))
             {
                chkS2Q83.setChecked(false);
             }
             if(String.valueOf(item.getS2Q84()).equals("1"))
             {
                chkS2Q84.setChecked(true);
             }
             else if(String.valueOf(item.getS2Q84()).equals("2"))
             {
                chkS2Q84.setChecked(false);
             }
             txtS2Q84_1.setText(item.getS2Q84_1());
             String[] d_rdogrpS3Q9 = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpS3Q9.length; i++)
             {
                 if (String.valueOf(item.getS3Q9()).equals(String.valueOf(d_rdogrpS3Q9[i])))
                 {
                     rb = (RadioButton) rdogrpS3Q9.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getS3Q91()).equals("1"))
             {
                chkS3Q91.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q91()).equals("2"))
             {
                chkS3Q91.setChecked(false);
             }
             if(String.valueOf(item.getS3Q92()).equals("1"))
             {
                chkS3Q92.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q92()).equals("2"))
             {
                chkS3Q92.setChecked(false);
             }
             if(String.valueOf(item.getS3Q93()).equals("1"))
             {
                chkS3Q93.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q93()).equals("2"))
             {
                chkS3Q93.setChecked(false);
             }
             if(String.valueOf(item.getS3Q94()).equals("1"))
             {
                chkS3Q94.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q94()).equals("2"))
             {
                chkS3Q94.setChecked(false);
             }
             if(String.valueOf(item.getS3Q95()).equals("1"))
             {
                chkS3Q95.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q95()).equals("2"))
             {
                chkS3Q95.setChecked(false);
             }
             if(String.valueOf(item.getS3Q96()).equals("1"))
             {
                chkS3Q96.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q96()).equals("2"))
             {
                chkS3Q96.setChecked(false);
             }
             if(String.valueOf(item.getS3Q97()).equals("1"))
             {
                chkS3Q97.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q97()).equals("2"))
             {
                chkS3Q97.setChecked(false);
             }
             if(String.valueOf(item.getS3Q98()).equals("1"))
             {
                chkS3Q98.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q98()).equals("2"))
             {
                chkS3Q98.setChecked(false);
             }
             if(String.valueOf(item.getS3Q99()).equals("1"))
             {
                chkS3Q99.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q99()).equals("2"))
             {
                chkS3Q99.setChecked(false);
             }
             if(String.valueOf(item.getS3Q910()).equals("1"))
             {
                chkS3Q910.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q910()).equals("2"))
             {
                chkS3Q910.setChecked(false);
             }
             if(String.valueOf(item.getS3Q911()).equals("1"))
             {
                chkS3Q911.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q911()).equals("2"))
             {
                chkS3Q911.setChecked(false);
             }
             if(String.valueOf(item.getS3Q912()).equals("1"))
             {
                chkS3Q912.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q912()).equals("2"))
             {
                chkS3Q912.setChecked(false);
             }
             if(String.valueOf(item.getS3Q913()).equals("1"))
             {
                chkS3Q913.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q913()).equals("2"))
             {
                chkS3Q913.setChecked(false);
             }
             if(String.valueOf(item.getS3Q914()).equals("1"))
             {
                chkS3Q914.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q914()).equals("2"))
             {
                chkS3Q914.setChecked(false);
             }
             txtS3Q914_1.setText(item.getS3Q914_1());
             spnS3Q10.setSelection(Global.SpinnerItemPositionAnyLength(spnS3Q10, String.valueOf(item.getS3Q10())));
             txtS3Q10_1.setText(item.getS3Q10_1());
             String[] d_rdogrpS3Q11 = new String[] {"1","2","3"};
             for (int i = 0; i < d_rdogrpS3Q11.length; i++)
             {
                 if (String.valueOf(item.getS3Q11()).equals(String.valueOf(d_rdogrpS3Q11[i])))
                 {
                     rb = (RadioButton) rdogrpS3Q11.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getS3Q111()).equals("1"))
             {
                chkS3Q111.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q111()).equals("2"))
             {
                chkS3Q111.setChecked(false);
             }
             if(String.valueOf(item.getS3Q112()).equals("1"))
             {
                chkS3Q112.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q112()).equals("2"))
             {
                chkS3Q112.setChecked(false);
             }
             if(String.valueOf(item.getS3Q113()).equals("1"))
             {
                chkS3Q113.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q113()).equals("2"))
             {
                chkS3Q113.setChecked(false);
             }
             if(String.valueOf(item.getS3Q114()).equals("1"))
             {
                chkS3Q114.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q114()).equals("2"))
             {
                chkS3Q114.setChecked(false);
             }
             if(String.valueOf(item.getS3Q115()).equals("1"))
             {
                chkS3Q115.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q115()).equals("2"))
             {
                chkS3Q115.setChecked(false);
             }
             if(String.valueOf(item.getS3Q116()).equals("1"))
             {
                chkS3Q116.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q116()).equals("2"))
             {
                chkS3Q116.setChecked(false);
             }
             if(String.valueOf(item.getS3Q117()).equals("1"))
             {
                chkS3Q117.setChecked(true);
             }
             else if(String.valueOf(item.getS3Q117()).equals("2"))
             {
                chkS3Q117.setChecked(false);
             }
             txtS3Q117_1.setText(item.getS3Q117_1());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(CONTRACEPTIVE_BEHAVIOUR.this, e.getMessage());
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
      EditText txtDate;


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