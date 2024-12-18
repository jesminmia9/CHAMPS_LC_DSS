
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

 import forms_datamodel.DeathNotifi_DataModel;
 import Utility.*;
 import Common.*;

 public class DeathNotifi extends AppCompatActivity {
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
    LinearLayout seclbl2;
    View linelbl2;
    LinearLayout secDthID;
    View lineDthID;
    TextView VlblDthID;
    EditText txtDthID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMortalityAgeG;
    View lineMortalityAgeG;
    TextView VlblMortalityAgeG;
    RadioGroup rdogrpMortalityAgeG;
    RadioButton rdoMortalityAgeG1;
    RadioButton rdoMortalityAgeG2;
    RadioButton rdoMortalityAgeG3;
    RadioButton rdoMortalityAgeG4;
    RadioButton rdoMortalityAgeG5;
    RadioButton rdoMortalityAgeG6;
    LinearLayout secDeceasedName;
    View lineDeceasedName;
    TextView VlblDeceasedName;
    EditText txtDeceasedName;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secDOD;
    View lineDOD;
    TextView VlblDOD;
    EditText dtpDOD;
    LinearLayout secDODDk;
    View lineDODDk;
    TextView VlblDODDk;
    RadioGroup rdogrpDODDk;
    RadioButton rdoDODDk1;
    RadioButton rdoDODDk2;
    LinearLayout secDOB;
    View lineDOB;
    TextView VlblDOB;
    EditText dtpDOB;
    LinearLayout secDOBDk;
    View lineDOBDk;
    TextView VlblDOBDk;
    RadioGroup rdogrpDOBDk;
    RadioButton rdoDOBDk1;
    RadioButton rdoDOBDk2;

    LinearLayout secDAge;
    View lineDAge;
    TextView VlblDAge;
    EditText txtDAge;

     LinearLayout secDAgeUnit;
     RadioGroup rdogrpDAgeUnit;
     RadioButton rdoDAgeUnit1;
     RadioButton rdoDAgeUnit2;
     RadioButton rdoDAgeUnit3;


    LinearLayout secDAgeDk;
    View lineDAgeDk;
    TextView VlblDAgeDk;
    RadioGroup rdogrpDAgeDk;
    RadioButton rdoDAgeDk1;
    RadioButton rdoDAgeDk2;
    LinearLayout secPOD;
    View linePOD;
    TextView VlblPOD;
    Spinner spnPOD;
    LinearLayout secPODOth;
    View linePODOth;
    TextView VlblPODOth;
    EditText txtPODOth;
    LinearLayout secPODNm;
    View linePODNm;
    TextView VlblPODNm;
    EditText txtPODNm;
    LinearLayout secPODNmDk;
    View linePODNmDk;
    TextView VlblPODNmDk;
    RadioGroup rdogrpPODNmDk;
    RadioButton rdoPODNmDk1;
    RadioButton rdoPODNmDk2;
    LinearLayout secCODToldHCP;
    View lineCODToldHCP;
    TextView VlblCODToldHCP;
    RadioGroup rdogrpCODToldHCP;
    RadioButton rdoCODToldHCP1;
    RadioButton rdoCODToldHCP2;
    RadioButton rdoCODToldHCP3;
    RadioButton rdoCODToldHCP4;
    LinearLayout secMainCOD;
    View lineMainCOD;
    TextView VlblMainCOD;
    EditText txtMainCOD;
    LinearLayout secMainCODDk;
    View lineMainCODDk;
    TextView VlblMainCODDk;
    RadioGroup rdogrpMainCODDk;
    RadioButton rdoMainCODDk1;
    RadioButton rdoMainCODDk2;
    LinearLayout secMainCODThink;
    View lineMainCODThink;
    TextView VlblMainCODThink;
    EditText txtMainCODThink;
    LinearLayout secMainCODThinkDk;
    View lineMainCODThinkDk;
    TextView VlblMainCODThinkDk;
    RadioGroup rdogrpMainCODThinkDk;
    RadioButton rdoMainCODThinkDk1;
    RadioButton rdoMainCODThinkDk2;
    TextView txtDSSID;

    static int MODULEID   = 0;
    static int LANGUAGEID = 0;
    static String TableName;

    static String STARTTIME = "";
    static String DEVICEID  = "";
    static String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
    static String DTHID = "";
    static String HHID = "";
    static String HHNO = "";
    static String DSSID = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.deathnotifi);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         DTHID = IDbundle.getString("DthID");
         HHID = IDbundle.getString("HHID");
         HHNO = IDbundle.getString("HHNO");
         DSSID = IDbundle.getString("DSSID");

         if (DTHID == null || DTHID.isEmpty()){
             DTHID = Global.Get_UUID(DEVICEID);
         }

         TableName = "DeathNotifi";
         MODULEID = 15;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(DeathNotifi.this);
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
        Connection.LocalizeLanguage(DeathNotifi.this, MODULEID, LANGUAGEID);


         dtpDOD.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnDOD"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpDOB.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnDOB"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables
         secMemID.setVisibility(View.GONE);
         secPODOth.setVisibility(View.GONE);
         linePODOth.setVisibility(View.GONE);
         secPODNm.setVisibility(View.GONE);
         linePODNm.setVisibility(View.GONE);
         secPODNmDk.setVisibility(View.GONE);
         linePODNmDk.setVisibility(View.GONE);
         secPODOth.setVisibility(View.GONE);
         linePODOth.setVisibility(View.GONE);
         secPODNm.setVisibility(View.GONE);
         linePODNm.setVisibility(View.GONE);
         secPODNmDk.setVisibility(View.GONE);
         linePODNmDk.setVisibility(View.GONE);
         secPODOth.setVisibility(View.GONE);
         linePODOth.setVisibility(View.GONE);
         secPODNm.setVisibility(View.GONE);
         linePODNm.setVisibility(View.GONE);
         secPODNmDk.setVisibility(View.GONE);
         linePODNmDk.setVisibility(View.GONE);
         secPODOth.setVisibility(View.GONE);
         linePODOth.setVisibility(View.GONE);
         secPODNm.setVisibility(View.GONE);
         linePODNm.setVisibility(View.GONE);
         secPODNmDk.setVisibility(View.GONE);
         linePODNmDk.setVisibility(View.GONE);
         secPODOth.setVisibility(View.GONE);
         linePODOth.setVisibility(View.GONE);
         secPODNm.setVisibility(View.GONE);
         linePODNm.setVisibility(View.GONE);
         secPODNmDk.setVisibility(View.GONE);
         linePODNmDk.setVisibility(View.GONE);
         secPODOth.setVisibility(View.GONE);
         linePODOth.setVisibility(View.GONE);
         secPODNm.setVisibility(View.GONE);
         linePODNm.setVisibility(View.GONE);
         secPODNmDk.setVisibility(View.GONE);
         linePODNmDk.setVisibility(View.GONE);
         secPODOth.setVisibility(View.GONE);
         linePODOth.setVisibility(View.GONE);
         secPODNm.setVisibility(View.GONE);
         linePODNm.setVisibility(View.GONE);
         secPODNmDk.setVisibility(View.GONE);
         linePODNmDk.setVisibility(View.GONE);
         secPODOth.setVisibility(View.GONE);
         linePODOth.setVisibility(View.GONE);
         secPODNm.setVisibility(View.GONE);
         linePODNm.setVisibility(View.GONE);
         secPODNmDk.setVisibility(View.GONE);
         linePODNmDk.setVisibility(View.GONE);
         secPODOth.setVisibility(View.GONE);
         linePODOth.setVisibility(View.GONE);
         secPODNm.setVisibility(View.GONE);
         linePODNm.setVisibility(View.GONE);
         secPODNmDk.setVisibility(View.GONE);
         linePODNmDk.setVisibility(View.GONE);
         /*sec.setVisibility(View.GONE);
         line.setVisibility(View.GONE);*/
         secMainCOD.setVisibility(View.GONE);
         lineMainCOD.setVisibility(View.GONE);
         secMainCODDk.setVisibility(View.GONE);
         lineMainCODDk.setVisibility(View.GONE);
         secMainCOD.setVisibility(View.GONE);
         lineMainCOD.setVisibility(View.GONE);
         secMainCODDk.setVisibility(View.GONE);
         lineMainCODDk.setVisibility(View.GONE);
         secMainCOD.setVisibility(View.GONE);
         lineMainCOD.setVisibility(View.GONE);
         secMainCODDk.setVisibility(View.GONE);
         lineMainCODDk.setVisibility(View.GONE);




        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
        Global.RoleManagement(cmdSave, MySharedPreferences.getValue(this, "userrole"));
     }
     catch(Exception  e)
     {
         Connection.MessageBox(DeathNotifi.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         txtDSSID = findViewById(R.id.txtDSSID);
//         if(DSSID.length()==0) txtDSSID.setText(GetDSS_ID(HHID,HHNO));
//         else txtDSSID.setText(DSSID);

         seclbl1=(LinearLayout)findViewById(R.id.seclbl1);
         linelbl1=(View)findViewById(R.id.linelbl1);
         seclbl2=(LinearLayout)findViewById(R.id.seclbl2);
         linelbl2=(View)findViewById(R.id.linelbl2);
         secDthID=(LinearLayout)findViewById(R.id.secDthID);
         lineDthID=(View)findViewById(R.id.lineDthID);
         VlblDthID=(TextView) findViewById(R.id.VlblDthID);
         txtDthID=(EditText) findViewById(R.id.txtDthID);
         txtDthID.setText(DTHID);
         txtDthID.setEnabled(false);
         secHHID=(LinearLayout)findViewById(R.id.secHHID);
         lineHHID=(View)findViewById(R.id.lineHHID);
         VlblHHID=(TextView) findViewById(R.id.VlblHHID);
         txtHHID=(EditText) findViewById(R.id.txtHHID);
         txtHHID.setText(HHID);
         secMortalityAgeG=(LinearLayout)findViewById(R.id.secMortalityAgeG);
         lineMortalityAgeG=(View)findViewById(R.id.lineMortalityAgeG);
         VlblMortalityAgeG = (TextView) findViewById(R.id.VlblMortalityAgeG);
         rdogrpMortalityAgeG = (RadioGroup) findViewById(R.id.rdogrpMortalityAgeG);
         rdoMortalityAgeG1 = (RadioButton) findViewById(R.id.rdoMortalityAgeG1);
         rdoMortalityAgeG2 = (RadioButton) findViewById(R.id.rdoMortalityAgeG2);
         rdoMortalityAgeG3 = (RadioButton) findViewById(R.id.rdoMortalityAgeG3);
         rdoMortalityAgeG4 = (RadioButton) findViewById(R.id.rdoMortalityAgeG4);
         rdoMortalityAgeG5 = (RadioButton) findViewById(R.id.rdoMortalityAgeG5);
         rdoMortalityAgeG6 = (RadioButton) findViewById(R.id.rdoMortalityAgeG6);
         secDeceasedName=(LinearLayout)findViewById(R.id.secDeceasedName);
         lineDeceasedName=(View)findViewById(R.id.lineDeceasedName);
         VlblDeceasedName=(TextView) findViewById(R.id.VlblDeceasedName);
         txtDeceasedName=(EditText) findViewById(R.id.txtDeceasedName);
         secMemID=(LinearLayout)findViewById(R.id.secMemID);
         lineMemID=(View)findViewById(R.id.lineMemID);
         VlblMemID=(TextView) findViewById(R.id.VlblMemID);
         txtMemID=(EditText) findViewById(R.id.txtMemID);
         secDOD=(LinearLayout)findViewById(R.id.secDOD);
         lineDOD=(View)findViewById(R.id.lineDOD);
         VlblDOD=(TextView) findViewById(R.id.VlblDOD);
         dtpDOD=(EditText) findViewById(R.id.dtpDOD);
         secDODDk=(LinearLayout)findViewById(R.id.secDODDk);
         lineDODDk=(View)findViewById(R.id.lineDODDk);
         VlblDODDk = (TextView) findViewById(R.id.VlblDODDk);
         rdogrpDODDk = (RadioGroup) findViewById(R.id.rdogrpDODDk);
         rdogrpDODDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup radioGroup, int i) {
                 dtpDOD.setText("");
             }
         });
         rdoDODDk1 = (RadioButton) findViewById(R.id.rdoDODDk1);
         rdoDODDk2 = (RadioButton) findViewById(R.id.rdoDODDk2);
         secDOB=(LinearLayout)findViewById(R.id.secDOB);
         lineDOB=(View)findViewById(R.id.lineDOB);
         VlblDOB=(TextView) findViewById(R.id.VlblDOB);
         dtpDOB=(EditText) findViewById(R.id.dtpDOB);
         secDOBDk=(LinearLayout)findViewById(R.id.secDOBDk);
         lineDOBDk=(View)findViewById(R.id.lineDOBDk);
         VlblDOBDk = (TextView) findViewById(R.id.VlblDOBDk);
         rdogrpDOBDk = (RadioGroup) findViewById(R.id.rdogrpDOBDk);
         rdogrpDOBDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup radioGroup, int i) {
                 dtpDOB.setText("");
             }
         });
         rdoDOBDk1 = (RadioButton) findViewById(R.id.rdoDOBDk1);
         rdoDOBDk2 = (RadioButton) findViewById(R.id.rdoDOBDk2);

         secDAge=(LinearLayout)findViewById(R.id.secDAge);
         lineDAge=(View)findViewById(R.id.lineDAge);
         VlblDAge=(TextView) findViewById(R.id.VlblDAge);
         txtDAge=(EditText) findViewById(R.id.txtDAge);
         txtDAge.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 if(txtDAge.getText().toString().equals("98")){
                     rdoDAgeDk1.setChecked(true);
                 }else if(txtDAge.getText().toString().equals("99")){
                     rdoDAgeDk2.setChecked(true);
                 }else{
                     rdogrpDAgeDk.clearCheck();
                 }

                 /*if (charSequence.length() != 0){
                     if (charSequence.toString().equalsIgnoreCase("8")){
                         rdoDAgeDk1.setChecked(true);
                     } else if (charSequence.toString().equalsIgnoreCase("9")) {
                         rdoDAgeDk2.setChecked(true);
                     }else{
                         rdogrpDAgeDk.clearCheck();
                     }

                 }*/
             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });

         secDAgeUnit=findViewById(R.id.secDAgeUnit);
         rdogrpDAgeUnit=findViewById(R.id.rdogrpDAgeUnit);
         rdogrpDAgeUnit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup radioGroup, int i) {
                 if (rdoDAgeUnit1.isChecked()){
                     rdogrpDAgeDk.clearCheck();
                 } else if (rdoDAgeUnit2.isChecked()) {
                     rdogrpDAgeDk.clearCheck();
                 }
                 else if (rdoDAgeUnit3.isChecked()) {
                     rdogrpDAgeDk.clearCheck();
                 }

             }
         });
         rdoDAgeUnit1=findViewById(R.id.rdoDAgeUnit1);
         rdoDAgeUnit2=findViewById(R.id.rdoDAgeUnit2);
         rdoDAgeUnit3=findViewById(R.id.rdoDAgeUnit3);

         secDAgeDk=(LinearLayout)findViewById(R.id.secDAgeDk);
         lineDAgeDk=(View)findViewById(R.id.lineDAgeDk);
         VlblDAgeDk = (TextView) findViewById(R.id.VlblDAgeDk);
         rdogrpDAgeDk = (RadioGroup) findViewById(R.id.rdogrpDAgeDk);
         rdogrpDAgeDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup radioGroup, int i) {
                 if (rdoDAgeDk1.isChecked()){
                     txtDAge.setText("98");
                     rdogrpDAgeUnit.clearCheck();
                 } else if (rdoDAgeDk2.isChecked()) {
                     txtDAge.setText("99");
                     rdogrpDAgeUnit.clearCheck();
                 }

             }
         });
         rdoDAgeDk1 = (RadioButton) findViewById(R.id.rdoDAgeDk1);
         rdoDAgeDk2 = (RadioButton) findViewById(R.id.rdoDAgeDk2);
         secPOD=(LinearLayout)findViewById(R.id.secPOD);
         linePOD=(View)findViewById(R.id.linePOD);
         VlblPOD=(TextView) findViewById(R.id.VlblPOD);
         spnPOD=(Spinner) findViewById(R.id.spnPOD);
         List<String> listPOD = new ArrayList<String>();
         
         listPOD.add("");
         listPOD.add("01-At resident’s home");
         listPOD.add("02-At a relative’s home");
         listPOD.add("03-At a healer’s home");
         listPOD.add("04-At other home");
         listPOD.add("05-At a public health facility");
         listPOD.add("06-At a private health facility");
         listPOD.add("11-TBA Home");
         listPOD.add("07-At an NGO health facility");
         listPOD.add("08-On route to a health facility");
         listPOD.add("09-At church/mosque");
         listPOD.add("10-Outdoors");
         listPOD.add("97-Other");
         listPOD.add("98-Don’t know");
         listPOD.add("99-Refused to respond");
         spnPOD.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listPOD)));

         spnPOD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnPOD.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnPOD.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("01"))
                 {
                    secPODOth.setVisibility(View.GONE);
                    linePODOth.setVisibility(View.GONE);
                    txtPODOth.setText("");
                    secPODNm.setVisibility(View.GONE);
                    linePODNm.setVisibility(View.GONE);
                    txtPODNm.setText("");
                    secPODNmDk.setVisibility(View.GONE);
                    linePODNmDk.setVisibility(View.GONE);
                    rdogrpPODNmDk.clearCheck();
                 }
                 else if(spnData.equalsIgnoreCase("02"))
                 {
                    secPODOth.setVisibility(View.GONE);
                    linePODOth.setVisibility(View.GONE);
                    txtPODOth.setText("");
                    secPODNm.setVisibility(View.GONE);
                    linePODNm.setVisibility(View.GONE);
                    txtPODNm.setText("");
                    secPODNmDk.setVisibility(View.GONE);
                    linePODNmDk.setVisibility(View.GONE);
                    rdogrpPODNmDk.clearCheck();
                 }
                 else if(spnData.equalsIgnoreCase("03"))
                 {
                    secPODOth.setVisibility(View.GONE);
                    linePODOth.setVisibility(View.GONE);
                    txtPODOth.setText("");
                    secPODNm.setVisibility(View.GONE);
                    linePODNm.setVisibility(View.GONE);
                    txtPODNm.setText("");
                    secPODNmDk.setVisibility(View.GONE);
                    linePODNmDk.setVisibility(View.GONE);
                    rdogrpPODNmDk.clearCheck();
                 }
                 else if(spnData.equalsIgnoreCase("04"))
                 {
                    secPODOth.setVisibility(View.GONE);
                    linePODOth.setVisibility(View.GONE);
                    txtPODOth.setText("");
                    secPODNm.setVisibility(View.GONE);
                    linePODNm.setVisibility(View.GONE);
                    txtPODNm.setText("");
                    secPODNmDk.setVisibility(View.GONE);
                    linePODNmDk.setVisibility(View.GONE);
                    rdogrpPODNmDk.clearCheck();
                 }else if(spnData.equalsIgnoreCase("05")
                         || spnData.equalsIgnoreCase("06")
                 ||spnData.equalsIgnoreCase("07"))
                 {
                    secPODOth.setVisibility(View.GONE);
                    linePODOth.setVisibility(View.GONE);
                    txtPODOth.setText("");
                    secPODNm.setVisibility(View.VISIBLE);
                    linePODNm.setVisibility(View.VISIBLE);
                    //txtPODNm.setText("");
                    secPODNmDk.setVisibility(View.VISIBLE);
                    linePODNmDk.setVisibility(View.VISIBLE);
                    //rdogrpPODNmDk.clearCheck();
                 }
                 else if(spnData.equalsIgnoreCase("08"))
                 {
                    secPODOth.setVisibility(View.GONE);
                    linePODOth.setVisibility(View.GONE);
                    txtPODOth.setText("");
                    secPODNm.setVisibility(View.GONE);
                    linePODNm.setVisibility(View.GONE);
                    txtPODNm.setText("");
                    secPODNmDk.setVisibility(View.GONE);
                    linePODNmDk.setVisibility(View.GONE);
                    rdogrpPODNmDk.clearCheck();
                 }
                 else if(spnData.equalsIgnoreCase("09"))
                 {
                    secPODOth.setVisibility(View.GONE);
                    linePODOth.setVisibility(View.GONE);
                    txtPODOth.setText("");
                    secPODNm.setVisibility(View.GONE);
                    linePODNm.setVisibility(View.GONE);
                    txtPODNm.setText("");
                    secPODNmDk.setVisibility(View.GONE);
                    linePODNmDk.setVisibility(View.GONE);
                    rdogrpPODNmDk.clearCheck();
                 }
                 else if(spnData.equalsIgnoreCase("10"))
                 {
                    secPODOth.setVisibility(View.GONE);
                    linePODOth.setVisibility(View.GONE);
                    txtPODOth.setText("");
                    secPODNm.setVisibility(View.GONE);
                    linePODNm.setVisibility(View.GONE);
                    txtPODNm.setText("");
                    secPODNmDk.setVisibility(View.GONE);
                    linePODNmDk.setVisibility(View.GONE);
                    rdogrpPODNmDk.clearCheck();
                 }
                 else if(spnData.equalsIgnoreCase("98"))
                 {
                    secPODOth.setVisibility(View.GONE);
                    linePODOth.setVisibility(View.GONE);
                    txtPODOth.setText("");
                    secPODNm.setVisibility(View.GONE);
                    linePODNm.setVisibility(View.GONE);
                    txtPODNm.setText("");
                    secPODNmDk.setVisibility(View.GONE);
                    linePODNmDk.setVisibility(View.GONE);
                    rdogrpPODNmDk.clearCheck();
                 }
                 else if(spnData.equalsIgnoreCase("99"))
                 {
                    secPODOth.setVisibility(View.GONE);
                    linePODOth.setVisibility(View.GONE);
                    txtPODOth.setText("");
                    secPODNm.setVisibility(View.GONE);
                    linePODNm.setVisibility(View.GONE);
                    txtPODNm.setText("");
                    secPODNmDk.setVisibility(View.GONE);
                    linePODNmDk.setVisibility(View.GONE);
                    rdogrpPODNmDk.clearCheck();
                 }
                 else if(spnData.equalsIgnoreCase("97"))
                 {

                     secPODOth.setVisibility(View.VISIBLE);
                     linePODOth.setVisibility(View.VISIBLE);
                   /* sec.setVisibility(View.GONE);
                    line.setVisibility(View.GONE);*/
                 }
                 else
                 {
                     secPODOth.setVisibility(View.GONE);
                     linePODOth.setVisibility(View.GONE);
                     txtPODOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secPODOth=(LinearLayout)findViewById(R.id.secPODOth);
         linePODOth=(View)findViewById(R.id.linePODOth);
         VlblPODOth=(TextView) findViewById(R.id.VlblPODOth);
         txtPODOth=(EditText) findViewById(R.id.txtPODOth);
         secPODNm=(LinearLayout)findViewById(R.id.secPODNm);
         linePODNm=(View)findViewById(R.id.linePODNm);
         VlblPODNm=(TextView) findViewById(R.id.VlblPODNm);
         txtPODNm=(EditText) findViewById(R.id.txtPODNm);
         txtPODNm.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 if (charSequence.length() != 0){
                     rdogrpPODNmDk.clearCheck();
                 }
             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });
         secPODNmDk=(LinearLayout)findViewById(R.id.secPODNmDk);
         linePODNmDk=(View)findViewById(R.id.linePODNmDk);
         VlblPODNmDk = (TextView) findViewById(R.id.VlblPODNmDk);
         rdogrpPODNmDk = (RadioGroup) findViewById(R.id.rdogrpPODNmDk);
         rdogrpPODNmDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup radioGroup, int i) {
                 if (rdoPODNmDk1.isChecked()){
                     txtPODNm.setText("");
                 } else if (rdoPODNmDk2.isChecked()) {
                     txtPODNm.setText("");
                 }
             }
         });
         rdoPODNmDk1 = (RadioButton) findViewById(R.id.rdoPODNmDk1);
         rdoPODNmDk2 = (RadioButton) findViewById(R.id.rdoPODNmDk2);
         secCODToldHCP=(LinearLayout)findViewById(R.id.secCODToldHCP);
         lineCODToldHCP=(View)findViewById(R.id.lineCODToldHCP);
         VlblCODToldHCP = (TextView) findViewById(R.id.VlblCODToldHCP);
         rdogrpCODToldHCP = (RadioGroup) findViewById(R.id.rdogrpCODToldHCP);
         rdoCODToldHCP1 = (RadioButton) findViewById(R.id.rdoCODToldHCP1);
         rdoCODToldHCP2 = (RadioButton) findViewById(R.id.rdoCODToldHCP2);
         rdoCODToldHCP3 = (RadioButton) findViewById(R.id.rdoCODToldHCP3);
         rdoCODToldHCP4 = (RadioButton) findViewById(R.id.rdoCODToldHCP4);
         rdogrpCODToldHCP.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpCODToldHCP = new String[] {"0","1","8","9"};
             for (int i = 0; i < rdogrpCODToldHCP.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpCODToldHCP.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpCODToldHCP[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    secMainCOD.setVisibility(View.GONE);
                    lineMainCOD.setVisibility(View.GONE);
                    txtMainCOD.setText("");
                    secMainCODDk.setVisibility(View.GONE);
                    lineMainCODDk.setVisibility(View.GONE);
                    rdogrpMainCODDk.clearCheck();
             }
             else if(rbData.equalsIgnoreCase("8"))
             {
                    secMainCOD.setVisibility(View.GONE);
                    lineMainCOD.setVisibility(View.GONE);
                    txtMainCOD.setText("");
                    secMainCODDk.setVisibility(View.GONE);
                    lineMainCODDk.setVisibility(View.GONE);
                    rdogrpMainCODDk.clearCheck();
             }
             else if(rbData.equalsIgnoreCase("9"))
             {
                    secMainCOD.setVisibility(View.GONE);
                    lineMainCOD.setVisibility(View.GONE);
                    txtMainCOD.setText("");
                    secMainCODDk.setVisibility(View.GONE);
                    lineMainCODDk.setVisibility(View.GONE);
                    rdogrpMainCODDk.clearCheck();
             }
             else
             {
                    secMainCOD.setVisibility(View.VISIBLE);
                    lineMainCOD.setVisibility(View.VISIBLE);
                    secMainCODDk.setVisibility(View.VISIBLE);
                    lineMainCODDk.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secMainCOD=(LinearLayout)findViewById(R.id.secMainCOD);
         lineMainCOD=(View)findViewById(R.id.lineMainCOD);
         VlblMainCOD=(TextView) findViewById(R.id.VlblMainCOD);
         txtMainCOD=(EditText) findViewById(R.id.txtMainCOD);
         txtMainCOD.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 if (charSequence.length() != 0){
                     rdogrpMainCODDk.clearCheck();
                     secMainCODThink.setVisibility(View.GONE);
                     lineMainCODThink.setVisibility(View.GONE);
                     txtMainCODThink.setText("");
                     secMainCODThinkDk.setVisibility(View.GONE);
                     lineMainCODThinkDk.setVisibility(View.GONE);
                 }
             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });
         secMainCODDk=(LinearLayout)findViewById(R.id.secMainCODDk);
         lineMainCODDk=(View)findViewById(R.id.lineMainCODDk);
         VlblMainCODDk = (TextView) findViewById(R.id.VlblMainCODDk);
         rdogrpMainCODDk = (RadioGroup) findViewById(R.id.rdogrpMainCODDk);
         rdogrpMainCODDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup radioGroup, int i) {
                 if (rdoMainCODDk1.isChecked()){
                     txtMainCOD.setText("");
//                     rdogrpMainCODDk.clearCheck();
                     secMainCODThink.setVisibility(View.VISIBLE);
                     lineMainCODThink.setVisibility(View.VISIBLE);
                     secMainCODThinkDk.setVisibility(View.VISIBLE);
                     lineMainCODThinkDk.setVisibility(View.VISIBLE);

                 } else if (rdoMainCODDk2.isChecked()) {
                     txtMainCOD.setText("");
                     secMainCODThink.setVisibility(View.VISIBLE);
                     lineMainCODThink.setVisibility(View.VISIBLE);
                     secMainCODThinkDk.setVisibility(View.VISIBLE);
                     lineMainCODThinkDk.setVisibility(View.VISIBLE);
                 }
             }
         });
         rdoMainCODDk1 = (RadioButton) findViewById(R.id.rdoMainCODDk1);
         rdoMainCODDk2 = (RadioButton) findViewById(R.id.rdoMainCODDk2);
         secMainCODThink=(LinearLayout)findViewById(R.id.secMainCODThink);
         lineMainCODThink=(View)findViewById(R.id.lineMainCODThink);
         VlblMainCODThink=(TextView) findViewById(R.id.VlblMainCODThink);
         txtMainCODThink=(EditText) findViewById(R.id.txtMainCODThink);
         txtMainCODThink.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 if (charSequence.length() != 0){
                     rdogrpMainCODThinkDk.clearCheck();
                 }
             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });
         secMainCODThinkDk=(LinearLayout)findViewById(R.id.secMainCODThinkDk);
         lineMainCODThinkDk=(View)findViewById(R.id.lineMainCODThinkDk);
         VlblMainCODThinkDk = (TextView) findViewById(R.id.VlblMainCODThinkDk);
         rdogrpMainCODThinkDk = (RadioGroup) findViewById(R.id.rdogrpMainCODThinkDk);
         rdogrpMainCODThinkDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup radioGroup, int i) {
                 if (rdoMainCODThinkDk1.isChecked()){
                     txtMainCODThink.setText("");
                 } else if (rdoMainCODThinkDk2.isChecked()) {
                     txtMainCODThink.setText("");
                 }
             }
         });
         rdoMainCODThinkDk1 = (RadioButton) findViewById(R.id.rdoMainCODThinkDk1);
         rdoMainCODThinkDk2 = (RadioButton) findViewById(R.id.rdoMainCODThinkDk2);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(DeathNotifi.this, e.getMessage());
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
         	Connection.MessageBox(DeathNotifi.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         DeathNotifi_DataModel objSave = new DeathNotifi_DataModel();
         objSave.setDthID(txtDthID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setDSSID(txtDSSID.getText().toString());
         String[] d_rdogrpMortalityAgeG = new String[] {"1","2","3","4","5","6"};
         objSave.setMortalityAgeG("");
         for (int i = 0; i < rdogrpMortalityAgeG.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMortalityAgeG.getChildAt(i);
             if (rb.isChecked()) objSave.setMortalityAgeG(d_rdogrpMortalityAgeG[i]);
         }

         objSave.setDeceasedName(txtDeceasedName.getText().toString());
         objSave.setMemID(txtMemID.getText().toString());
         objSave.setDOD(dtpDOD.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpDOD.getText().toString()) : dtpDOD.getText().toString());
         String[] d_rdogrpDODDk = new String[] {"8","9"};
         objSave.setDODDk("");
         for (int i = 0; i < rdogrpDODDk.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDODDk.getChildAt(i);
             if (rb.isChecked()) objSave.setDODDk(d_rdogrpDODDk[i]);
         }

         objSave.setDOB(dtpDOB.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpDOB.getText().toString()) : dtpDOB.getText().toString());
         String[] d_rdogrpDOBDk = new String[] {"8","9"};
         objSave.setDOBDk("");
         for (int i = 0; i < rdogrpDOBDk.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDOBDk.getChildAt(i);
             if (rb.isChecked()) objSave.setDOBDk(d_rdogrpDOBDk[i]);
         }

         objSave.setDAge(txtDAge.getText().toString());
         String[] d_rdogrpDAgeUnit = new String[] {"1","2","3"};
         objSave.setDAgeUnit("");
         for (int i = 0; i < rdogrpDAgeUnit.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDAgeUnit.getChildAt(i);
             if (rb.isChecked()) objSave.setDAgeUnit(d_rdogrpDAgeUnit[i]);
         }

         String[] d_rdogrpDAgeDk = new String[] {"8","9"};
         objSave.setDAgeDk("");
         for (int i = 0; i < rdogrpDAgeDk.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDAgeDk.getChildAt(i);
             if (rb.isChecked()) objSave.setDAgeDk(d_rdogrpDAgeDk[i]);
         }

         objSave.setPOD(spnPOD.getSelectedItemPosition() == 0 ? "" : spnPOD.getSelectedItem().toString().split("-")[0]);
         objSave.setPODOth(txtPODOth.getText().toString());
         objSave.setPODNm(txtPODNm.getText().toString());
         String[] d_rdogrpPODNmDk = new String[] {"8","9"};
         objSave.setPODNmDk("");
         for (int i = 0; i < rdogrpPODNmDk.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPODNmDk.getChildAt(i);
             if (rb.isChecked()) objSave.setPODNmDk(d_rdogrpPODNmDk[i]);
         }

         String[] d_rdogrpCODToldHCP = new String[] {"0","1","8","9"};
         objSave.setCODToldHCP("");
         for (int i = 0; i < rdogrpCODToldHCP.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCODToldHCP.getChildAt(i);
             if (rb.isChecked()) objSave.setCODToldHCP(d_rdogrpCODToldHCP[i]);
         }

         objSave.setMainCOD(txtMainCOD.getText().toString());
         String[] d_rdogrpMainCODDk = new String[] {"8","9"};
         objSave.setMainCODDk("");
         for (int i = 0; i < rdogrpMainCODDk.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMainCODDk.getChildAt(i);
             if (rb.isChecked()) objSave.setMainCODDk(d_rdogrpMainCODDk[i]);
         }

         objSave.setMainCODThink(txtMainCODThink.getText().toString());
         String[] d_rdogrpMainCODThinkDk = new String[] {"8","9"};
         objSave.setMainCODThinkDk("");
         for (int i = 0; i < rdogrpMainCODThinkDk.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMainCODThinkDk.getChildAt(i);
             if (rb.isChecked()) objSave.setMainCODThinkDk(d_rdogrpMainCODThinkDk[i]);
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

             Toast.makeText(DeathNotifi.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
             finish();
         }
         else{
             Connection.MessageBox(DeathNotifi.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(DeathNotifi.this, e.getMessage());
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
         if(txtDthID.getText().toString().length()==0 & secDthID.isShown())
           {
             ValidationMsg += "\nRequired field: Internal ID.";
             secDthID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
           {
             ValidationMsg += "\nRequired field: Household ID.";
             secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoMortalityAgeG1.isChecked() & !rdoMortalityAgeG2.isChecked() & !rdoMortalityAgeG3.isChecked() & !rdoMortalityAgeG4.isChecked() & !rdoMortalityAgeG5.isChecked() & !rdoMortalityAgeG6.isChecked() & secMortalityAgeG.isShown())
           {
             ValidationMsg += "\nD2.3 Required field: During the past 12 months, has there been a child/member born to a woman living in this house who died in the first day after birth/first seven days after birth/ between the 7th and 28th day of birth/between the 1st and 12th month of birth/aged 1, 2, 3, or 4 years old/5 years old or older?.";
             secMortalityAgeG.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDeceasedName.getText().toString().length()==0 & secDeceasedName.isShown())
           {
             ValidationMsg += "\nD4. Required field: Record deceased child's/member's name.";
             secDeceasedName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMemID.getText().toString().length()==0 & secMemID.isShown())
           {
             ValidationMsg += "\nD5. Required field: Record deceased child's/member's individual ID number.";
             secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         String DV1 = Global.DateValidate(dtpDOD.getText().toString());
         if (!rdoDODDk1.isChecked() & !rdoDODDk2.isChecked() & secDODDk.isShown()){
             //check date
             if(DV1.length()!=0 & secDOD.isShown()){ //invalid date
                 ValidationMsg += "\nD6. Required field/Not a valid date format: What was the date of death of the child/member/[name]? (WRITE DOWN RESPONSE ON THE BLANK).";
                 secDOD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }else if(DV1.length()==0 & secDOD.isShown()){
                 int deathDays = Global.DateDifferenceDays(Global.DateNowDMY(), dtpDOD.getText().toString());
                 if (deathDays < 0 || deathDays > 365){ // date out of condition
                     ValidationMsg += "\nD6. Value should be within last 1 year(What was the date of death of the child/member/[name]? (WRITE DOWN RESPONSE ON THE BLANK).)";
                     secDOD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                 }
             }
         } else if (DV1.length()!=0 & secDOD.isShown()) {
             // check radios
             if(!rdoDODDk1.isChecked() & !rdoDODDk2.isChecked() & secDODDk.isShown())
             {
                 ValidationMsg += "\nRequired field: What was the date of death of the child/member/[name]? - Don't know 98/Refused to respond 99.";
                 secDODDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));

             }
         }

         String DV2 = Global.DateValidate(dtpDOB.getText().toString());
         if (!rdoDOBDk1.isChecked() & !rdoDOBDk2.isChecked() & secDOBDk.isShown()){
             if(DV2.length()!=0 & secDOB.isShown())
             {
                 ValidationMsg += "\nD6.1 Required field/Not a valid date format: What was the date of birth of the child/member/[name]?(WRITE DOWN RESPONSE ON THE BLANK).";
                 secDOB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
         } else if (DV2.length()!=0 & secDOB.isShown()) {
             if(!rdoDOBDk1.isChecked() & !rdoDOBDk2.isChecked() & secDOBDk.isShown())
             {
                 ValidationMsg += "\nRequired field: What was the date of Birth of the child/member/[name]? - Don't know 98 /Refused to respond 99.";
                 secDOBDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
         }

         if (DV1.length()==0 && DV2.length()==0){
             int deathDays = Global.DateDifferenceDays(dtpDOD.getText().toString(),dtpDOB.getText().toString());
             if (deathDays < 0 ){ // date out of condition
                 ValidationMsg += "\nD6.1 Date of birth cannot exceed date of death";
                 secDOD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                 secDOB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
         }

         /*if(DV2.length()!=0 & secDOB.isShown())
           {
             ValidationMsg += "\nD6.1 Required field/Not a valid date format: What was the date of birth of the child/member/[name]?(WRITE DOWN RESPONSE ON THE BLANK).";
             secDOB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/
         /*if(!rdoDOBDk1.isChecked() & !rdoDOBDk2.isChecked() & secDOBDk.isShown())
           {
             ValidationMsg += "\nRequired field: What was the date of Birth of the child/member/[name]? - Don't know 98 /Refused to respond 99.";
             secDOBDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/

         if(txtDAge.getText().toString().length()==0 & secDAge.isShown())
           {
             ValidationMsg += "\nRequired field: What was the age in years of the child/member/[name] when they died? - Years.";
             secDAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if((!rdoDAgeDk1.isChecked() && !rdoDAgeDk2.isChecked()) & !rdoDAgeUnit1.isChecked() & !rdoDAgeUnit2.isChecked() & !rdoDAgeUnit3.isChecked() & secDAgeUnit.isShown())
         {
             ValidationMsg += "\nRequired field: Unit of Age at Death.";
             secDAgeUnit.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         /*if (secDAge.isShown() && !txtDAge.getText().toString().equalsIgnoreCase("98") &&  !txtDAge .getText().toString().equalsIgnoreCase("99")) {

         }*/
         if(rdoDAgeUnit1.isChecked()){
             if(secDAge.isShown() & (Integer.valueOf(txtDAge.getText().toString().length()==0 ? "0" : txtDAge.getText().toString()) < 0 || Integer.valueOf(txtDAge.getText().toString().length()==0 ? "59" : txtDAge.getText().toString()) > 59))
             {
                 ValidationMsg += "\nAge at death (Days):Value should be between 0 and 59 days.";
                 secDAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
         }else if(rdoDAgeUnit2.isChecked()){
             if(secDAge.isShown() & (Integer.valueOf(txtDAge.getText().toString().length()==0 ? "0" : txtDAge.getText().toString()) < 2 || Integer.valueOf(txtDAge.getText().toString().length()==0 ? "59" : txtDAge.getText().toString()) > 59))
             {
                 ValidationMsg += "\nAge at death (Months):Value should be between 2 and 59 months.";
                 secDAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
         }else if(rdoDAgeUnit3.isChecked()){
             if(secDAge.isShown() & (Integer.valueOf(txtDAge.getText().toString().length()==0 ? "0" : txtDAge.getText().toString()) < 5))
             {
                 ValidationMsg += "\nAge at death (Years):Value should be greater than or equal 5 years.";
                 secDAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
         }
         /*if(!rdoDAgeDk1.isChecked() & !rdoDAgeDk2.isChecked() & secDAgeDk.isShown())
           {
             ValidationMsg += "\nRequired field: What was the age in days, months, and years of the child/member/[name] when they died?  - Don't know 98/Refused to respond 99.";
             secDAgeDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/
         if(spnPOD.getSelectedItemPosition()==0  & secPOD.isShown())
           {
             ValidationMsg += "\nD8. Required field: Where did the child/member/[name] die?(GIVE ONE RESPONSE, OR WRITE RESPONSE FOR 88).";
             secPOD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtPODOth.getText().toString().length()==0 & secPODOth.isShown())
           {
             ValidationMsg += "\nRequired field: Where did the child/member/[name] die?(PROVIDE RESPONSE FOR 88) - Other specify.";
             secPODOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if (!rdoPODNmDk1.isChecked() & !rdoPODNmDk2.isChecked() & secPODNmDk.isShown()) {
             if(txtPODNm.getText().toString().length()==0 & secPODNm.isShown())
               {
                 ValidationMsg += "\nD9. Required field: What was the name of place where the child/member/[name] died? In case of public/privae/NGO health facility(PROVIDE DOWN RESPONSE ON THE BLANK).";
                 secPODNm.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
               }
         }
         if (txtPODNm.getText().toString().length()==0 & secPODNm.isShown()) {
             if(!rdoPODNmDk1.isChecked() & !rdoPODNmDk2.isChecked() & secPODNmDk.isShown())
               {
                 ValidationMsg += "\nRequired field: What was the name of place where the child/[name] died? - - In case of public/privae/NGO health facility    -Don't know/Refused to respond.";
                 secPODNmDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
               }
         }
         if(!rdoCODToldHCP1.isChecked() & !rdoCODToldHCP2.isChecked() & !rdoCODToldHCP3.isChecked() & !rdoCODToldHCP4.isChecked() & secCODToldHCP.isShown())
           {
             ValidationMsg += "\nD10. Required field: Were you told by a health care professional what the cause of death was for child/member/[name]?.";
             secCODToldHCP.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if (!rdoMainCODDk1.isChecked() & !rdoMainCODDk2.isChecked() & secMainCODDk.isShown()) {
             if(txtMainCOD.getText().toString().length()==0 & secMainCOD.isShown())
               {
                 ValidationMsg += "\nD11. Required field: What was the main cause of death that the health care professional reported for child/member/[name]?.";
                 secMainCOD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
               }
         }
         if (txtMainCOD.getText().toString().length()==0 & secMainCOD.isShown()) {
             if(!rdoMainCODDk1.isChecked() & !rdoMainCODDk2.isChecked() & secMainCODDk.isShown())
               {
                 ValidationMsg += "\nRequired field: What was the main cause of death that the health care professional reported for child/member/[name]? - Don't know/Refused to respond.";
                 secMainCODDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
               }
         }
         if (!rdoMainCODThinkDk1.isChecked() & !rdoMainCODThinkDk2.isChecked() & secMainCODThinkDk.isShown()) {
             if(txtMainCODThink.getText().toString().length()==0 & secMainCODThink.isShown())
               {
                 ValidationMsg += "\nD12. Required field: What do you think was the main cause of death of the child/member/[name]?.";
                 secMainCODThink.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
               }
         }
         if (txtMainCODThink.getText().toString().length()==0 & secMainCODThink.isShown()) {
             if(!rdoMainCODThinkDk1.isChecked() & !rdoMainCODThinkDk2.isChecked() & secMainCODThinkDk.isShown())
               {
                 ValidationMsg += "\nRequired field: What do you think was the main cause of death of the child/member/[name]? - Don't know/Refused to respond.";
                 secMainCODThinkDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
               }
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
             secDthID.setBackgroundColor(Color.WHITE);
             secHHID.setBackgroundColor(Color.WHITE);
             secMortalityAgeG.setBackgroundColor(Color.WHITE);
             secDeceasedName.setBackgroundColor(Color.WHITE);
             secMemID.setBackgroundColor(Color.WHITE);
             secDOD.setBackgroundColor(Color.WHITE);
             secDODDk.setBackgroundColor(Color.WHITE);
             secDOB.setBackgroundColor(Color.WHITE);
             secDOBDk.setBackgroundColor(Color.WHITE);
             secDAge.setBackgroundColor(Color.WHITE);
             secDAgeUnit.setBackgroundColor(Color.WHITE);
             secDAgeDk.setBackgroundColor(Color.WHITE);
             secPOD.setBackgroundColor(Color.WHITE);
             secPODOth.setBackgroundColor(Color.WHITE);
             secPODNm.setBackgroundColor(Color.WHITE);
             secPODNmDk.setBackgroundColor(Color.WHITE);
             secCODToldHCP.setBackgroundColor(Color.WHITE);
             secMainCOD.setBackgroundColor(Color.WHITE);
             secMainCODDk.setBackgroundColor(Color.WHITE);
             secMainCODThink.setBackgroundColor(Color.WHITE);
             secMainCODThinkDk.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String DthID)
     {
       try
        {     
           RadioButton rb;
           DeathNotifi_DataModel d = new DeathNotifi_DataModel();
           String SQL = "Select * from "+ TableName +"  Where DthID='"+ DthID +"'";
           List<DeathNotifi_DataModel> data = d.SelectAll(this, SQL);
           for(DeathNotifi_DataModel item : data){
             txtDthID.setText(item.getDthID());
             txtHHID.setText(item.getHHID());
             txtDSSID.setText(item.getDSSID());
             String[] d_rdogrpMortalityAgeG = new String[] {"1","2","3","4","5","6"};
             for (int i = 0; i < d_rdogrpMortalityAgeG.length; i++)
             {
                 if (String.valueOf(item.getMortalityAgeG()).equals(String.valueOf(d_rdogrpMortalityAgeG[i])))
                 {
                     rb = (RadioButton)rdogrpMortalityAgeG.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtDeceasedName.setText(item.getDeceasedName());
             txtMemID.setText(item.getMemID());
             dtpDOD.setText(item.getDOD().toString().length()==0 ? "" : Global.DateConvertDMY(item.getDOD()));
             String[] d_rdogrpDODDk = new String[] {"8","9"};
             for (int i = 0; i < d_rdogrpDODDk.length; i++)
             {
                 if (String.valueOf(item.getDODDk()).equals(String.valueOf(d_rdogrpDODDk[i])))
                 {
                     rb = (RadioButton)rdogrpDODDk.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             dtpDOB.setText(item.getDOB().toString().length()==0 ? "" : Global.DateConvertDMY(item.getDOB()));
             String[] d_rdogrpDOBDk = new String[] {"8","9"};
             for (int i = 0; i < d_rdogrpDOBDk.length; i++)
             {
                 if (String.valueOf(item.getDOBDk()).equals(String.valueOf(d_rdogrpDOBDk[i])))
                 {
                     rb = (RadioButton)rdogrpDOBDk.getChildAt(i);
                     rb.setChecked(true);
                 }
             }

             txtDAge.setText(String.valueOf(item.getDAge()));

               String[] d_rdogrpDAgeUnit = new String[] {"1","2","3"};
               for (int i = 0; i < d_rdogrpDAgeUnit.length; i++)
               {
                   if (String.valueOf(item.getDAgeUnit()).equals(String.valueOf(d_rdogrpDAgeUnit[i])))
                   {
                       rb = (RadioButton)rdogrpDAgeUnit.getChildAt(i);
                       rb.setChecked(true);
                   }
               }


             String[] d_rdogrpDAgeDk = new String[] {"8","9"};
             for (int i = 0; i < d_rdogrpDAgeDk.length; i++)
             {
                 if (String.valueOf(item.getDAgeDk()).equals(String.valueOf(d_rdogrpDAgeDk[i])))
                 {
                     rb = (RadioButton)rdogrpDAgeDk.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             spnPOD.setSelection(Global.SpinnerItemPositionAnyLength(spnPOD, String.valueOf(item.getPOD())));
             txtPODOth.setText(item.getPODOth());
             txtPODNm.setText(item.getPODNm());
             String[] d_rdogrpPODNmDk = new String[] {"8","9"};
             for (int i = 0; i < d_rdogrpPODNmDk.length; i++)
             {
                 if (String.valueOf(item.getPODNmDk()).equals(String.valueOf(d_rdogrpPODNmDk[i])))
                 {
                     rb = (RadioButton)rdogrpPODNmDk.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpCODToldHCP = new String[] {"0","1","8","9"};
             for (int i = 0; i < d_rdogrpCODToldHCP.length; i++)
             {
                 if (String.valueOf(item.getCODToldHCP()).equals(String.valueOf(d_rdogrpCODToldHCP[i])))
                 {
                     rb = (RadioButton)rdogrpCODToldHCP.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtMainCOD.setText(item.getMainCOD());
             String[] d_rdogrpMainCODDk = new String[] {"8","9"};
             for (int i = 0; i < d_rdogrpMainCODDk.length; i++)
             {
                 if (String.valueOf(item.getMainCODDk()).equals(String.valueOf(d_rdogrpMainCODDk[i])))
                 {
                     rb = (RadioButton)rdogrpMainCODDk.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtMainCODThink.setText(item.getMainCODThink());
             String[] d_rdogrpMainCODThinkDk = new String[] {"8","9"};
             for (int i = 0; i < d_rdogrpMainCODThinkDk.length; i++)
             {
                 if (String.valueOf(item.getMainCODThinkDk()).equals(String.valueOf(d_rdogrpMainCODThinkDk[i])))
                 {
                     rb = (RadioButton)rdogrpMainCODThinkDk.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(DeathNotifi.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpDOD);
      if (VariableID.equals("btnDOD"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpDOD);
          rdogrpDODDk.clearCheck();
      }
      else if (VariableID.equals("btnDOB"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpDOB);
          rdogrpDOBDk.clearCheck();
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

 private String GetDSS_ID(String HHID, String HHNO)
 {
     String M = C.ReturnSingleValue("Select count(*)total from DeathNotifi where HHID='"+ HHID +"'");
     int serial_number = Integer.parseInt(M) + 61;
     return HHNO + String.valueOf(serial_number);
 }
 
 // turning off the GPS if its in on state. to avoid the battery drain.
 @Override
 protected void onDestroy() {
     // TODO Auto-generated method stub
     super.onDestroy();
 }
}