
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
 import android.view.MotionEvent;
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
 import forms_datamodel.Member_DataModel;

 public class Surv_Member_Old extends AppCompatActivity {
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
    LinearLayout seclbl02;
    View linelbl02;
    LinearLayout seclbl01;
    View linelbl01;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secDSSID;
    View lineDSSID;
    TextView VlblDSSID;
    EditText txtDSSID;
    LinearLayout secMSlNo;
    View lineMSlNo;
    TextView VlblMSlNo;
    EditText txtMSlNo;
    LinearLayout secName;
    View lineName;
    TextView VlblName;
    EditText txtName;
    LinearLayout secRth;
    View lineRth;
    TextView VlblRth;
    Spinner spnRth;
    LinearLayout secRthOth;
    View lineRthOth;
    TextView VlblRthOth;
    EditText txtRthOth;
    LinearLayout secSex;
    View lineSex;
    TextView VlblSex;
    RadioGroup rdogrpSex;
    RadioButton rdoSex1;
    RadioButton rdoSex2;
    RadioButton rdoSex3;
    RadioButton rdoSex4;
    RadioButton rdoSex5;
    LinearLayout secBDate;
    View lineBDate;
    TextView VlblBDate;
    EditText dtpBDate;
    LinearLayout secBDateType;
    View lineBDateType;
    TextView VlblBDateType;
    RadioGroup rdogrpBDateType;
    RadioButton rdoBDateType1;
    RadioButton rdoBDateType2;
    LinearLayout secAgeY;
    View lineAgeY;
    TextView VlblAgeY;
    EditText txtAgeY;
    LinearLayout secMoNo;
    View lineMoNo;
    TextView VlblMoNo;
    Spinner spnMoNo;
    LinearLayout secMoName;
    View lineMoName;
    TextView VlblMoName;
    EditText txtMoName;
    LinearLayout secFaNo;
    View lineFaNo;
    TextView VlblFaNo;
    Spinner spnFaNo;
    LinearLayout secFaName;
    View lineFaName;
    TextView VlblFaName;
    EditText txtFaName;
    LinearLayout secEduY;
    View lineEduY;
    TextView VlblEduY;
    EditText txtEduY;
    LinearLayout secMS;
    View lineMS;
    TextView VlblMS;
    RadioGroup rdogrpMS;
    RadioButton rdoMS1;
    RadioButton rdoMS2;
    RadioButton rdoMS3;
    RadioButton rdoMS4;
    RadioButton rdoMS5;
    RadioButton rdoMS6;
    RadioButton rdoMS7;
    RadioButton rdoMS8;
    RadioButton rdoMS9;
    RadioButton rdoMS10;
    LinearLayout secMSOth;
    View lineMSOth;
    TextView VlblMSOth;
    EditText txtMSOth;
    LinearLayout secEmploy;
    View lineEmploy;
    TextView VlblEmploy;
     RadioGroup rdogrpEmploy;
     RadioButton rdoEmploy1;
     RadioButton rdoEmploy2;
     RadioButton rdoEmploy3;
     RadioButton rdoEmploy4;
     RadioButton rdoEmploy5;
     RadioButton rdoEmploy6;
     RadioButton rdoEmploy7;
     RadioButton rdoEmploy8;
     RadioButton rdoEmploy9;
    LinearLayout secEmployOth;
    View lineEmployOth;
    TextView VlblEmployOth;
    EditText txtEmployOth;
    LinearLayout secOcp;
    View lineOcp;
    TextView VlblOcp;
    Spinner spnOcp;
    LinearLayout secOcpOth;
    View lineOcpOth;
    TextView VlblOcpOth;
    EditText txtOcpOth;
    LinearLayout secReligion;
    View lineReligion;
    TextView VlblReligion;
    Spinner spnReligion;
    LinearLayout secReligionOth;
    View lineReligionOth;
    TextView VlblReligionOth;
    EditText txtReligionOth;
    LinearLayout secEthnicity;
    View lineEthnicity;
    TextView VlblEthnicity;
//    RadioGroup rdogrpEthnicity;
//    RadioButton rdoEthnicity1;
//    RadioButton rdoEthnicity2;
    Spinner spnEthnicity;
    LinearLayout secMobileNo;
    View lineMobileNo;
    TextView VlblMobileNo;
    EditText txtMobileNo;
    LinearLayout secSp1;
    View lineSp1;
    TextView VlblSp1;
    Spinner spnSp1;
    LinearLayout secSp1Name;
    View lineSp1Name;
    TextView VlblSp1Name;
    EditText txtSp1Name;
    LinearLayout secSp2;
    View lineSp2;
    TextView VlblSp2;
    Spinner spnSp2;
    LinearLayout secSp2Name;
    View lineSp2Name;
    TextView VlblSp2Name;
    EditText txtSp2Name;
    LinearLayout secSp3;
    View lineSp3;
    TextView VlblSp3;
    Spinner spnSp3;
    LinearLayout secSp3Name;
    View lineSp3Name;
    TextView VlblSp3Name;
    EditText txtSp3Name;
    LinearLayout secSp4;
    View lineSp4;
    TextView VlblSp4;
    Spinner spnSp4;
    LinearLayout secSp4Name;
    View lineSp4Name;
    TextView VlblSp4Name;
    EditText txtSp4Name;
    LinearLayout secPstat;
    View linePstat;
    TextView VlblPstat;
    RadioGroup rdogrpPstat;
    RadioButton rdoPstat1;
    RadioButton rdoPstat2;
    RadioButton rdoPstat3;
    LinearLayout secLmpDt;
    View lineLmpDt;
    TextView VlblLmpDt;
    EditText dtpLmpDt;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secActive;
    View lineActive;
    TextView VlblActive;
    EditText txtActive;

     LinearLayout seclblSpsl;
     View linelblSpsl;

    static int MODULEID   = 0;
    static int LANGUAGEID = 0;
    static String TableName;

    static String STARTTIME = "";
    static String DEVICEID  = "";
    static String ENTRYUSER = "";
    MySharedPreferences sp;
     static String MSLNO = "";
     static String MemSex = "";
    Bundle IDbundle;
    static String MEMID = "";
    static String VILLID = "";
    static String CompoundID = "";
    static String HHID = "";
    static String EV_TYPE = "";


 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.surv_member);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         HHID = IDbundle.getString("HHID");
         MEMID = IDbundle.getString("MemID");
         EV_TYPE = IDbundle.getString("evtype");
//         CompoundID = IDbundle.getString("CompoundID");


         TableName = "Member";
         MODULEID = 9;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Member_Old.this);
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
        Connection.LocalizeLanguage(Surv_Member_Old.this, MODULEID, LANGUAGEID);


         dtpBDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnBDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpLmpDt.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnLmpDt"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables
         secRthOth.setVisibility(View.GONE);
         lineRthOth.setVisibility(View.GONE);
         secMoName.setVisibility(View.GONE);
         lineMoName.setVisibility(View.GONE);
         secFaName.setVisibility(View.GONE);
         lineFaName.setVisibility(View.GONE);
         secMSOth.setVisibility(View.GONE);
         lineMSOth.setVisibility(View.GONE);
         secEmployOth.setVisibility(View.GONE);
         lineEmployOth.setVisibility(View.GONE);
         secEmployOth.setVisibility(View.GONE);
         lineEmployOth.setVisibility(View.GONE);
         secEmployOth.setVisibility(View.GONE);
         lineEmployOth.setVisibility(View.GONE);
         secOcpOth.setVisibility(View.GONE);
         lineOcpOth.setVisibility(View.GONE);
         secReligionOth.setVisibility(View.GONE);
         lineReligionOth.setVisibility(View.GONE);
         secReligionOth.setVisibility(View.GONE);
         lineReligionOth.setVisibility(View.GONE);
         secReligionOth.setVisibility(View.GONE);
         lineReligionOth.setVisibility(View.GONE);
         seclblSpsl.setVisibility(View.GONE);
         spnSp1.setVisibility(View.GONE);
         lineSp1.setVisibility(View.GONE);
         secSp1.setVisibility(View.GONE);
         lineSp1.setVisibility(View.GONE);
         spnSp2.setVisibility(View.GONE);
         lineSp2.setVisibility(View.GONE);
         secSp2.setVisibility(View.GONE);
         lineSp2.setVisibility(View.GONE);
         spnSp3.setVisibility(View.GONE);
         lineSp3.setVisibility(View.GONE);
         secSp3.setVisibility(View.GONE);
         lineSp3.setVisibility(View.GONE);
         spnSp4.setVisibility(View.GONE);
         lineSp4.setVisibility(View.GONE);
         secSp4.setVisibility(View.GONE);
         lineSp4.setVisibility(View.GONE);
         secMobileNo.setVisibility(View.GONE);
         lineMobileNo.setVisibility(View.GONE);

         secMobileNo.setVisibility(View.GONE);
         lineMobileNo.setVisibility(View.GONE);
         secSp1Name.setVisibility(View.GONE);
         lineSp1Name.setVisibility(View.GONE);
         secSp2Name.setVisibility(View.GONE);
         lineSp2Name.setVisibility(View.GONE);
         secSp3Name.setVisibility(View.GONE);
         lineSp3Name.setVisibility(View.GONE);
         secSp4Name.setVisibility(View.GONE);
         lineSp4Name.setVisibility(View.GONE);
         secLmpDt.setVisibility(View.GONE);
         lineLmpDt.setVisibility(View.GONE);
         secPstat.setVisibility(View.GONE);
         linePstat.setVisibility(View.GONE);

        if (EV_TYPE.equals("22")|| EV_TYPE.equals("23")) {
            DataSearchMember(MEMID);
        }else if (EV_TYPE.equals("20")|| EV_TYPE.equals("21") || EV_TYPE.equals("25")){
            DataSearch(MEMID);
        }else if (EV_TYPE.equals("12")){
            DataSearch(MEMID);
        }

        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Member_Old.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         seclbl02=(LinearLayout)findViewById(R.id.seclbl02);
         linelbl02=(View)findViewById(R.id.linelbl02);
         seclbl01=(LinearLayout)findViewById(R.id.seclbl01);
         linelbl01=(View)findViewById(R.id.linelbl01);
         secMemID=(LinearLayout)findViewById(R.id.secMemID);
         lineMemID=(View)findViewById(R.id.lineMemID);
         VlblMemID=(TextView) findViewById(R.id.VlblMemID);
         txtMemID=(EditText) findViewById(R.id.txtMemID);

         if(MEMID.length()==0) txtMemID.setText(Global.Get_UUID(DEVICEID));
         else txtMemID.setText(MEMID);
         txtMemID.setEnabled(false);

         secHHID=(LinearLayout)findViewById(R.id.secHHID);
         lineHHID=(View)findViewById(R.id.lineHHID);
         VlblHHID=(TextView) findViewById(R.id.VlblHHID);
         txtHHID=(EditText) findViewById(R.id.txtHHID);
         txtHHID.setText(HHID);
         txtHHID.setEnabled(false);

         secDSSID=(LinearLayout)findViewById(R.id.secDSSID);
         lineDSSID=(View)findViewById(R.id.lineDSSID);
         VlblDSSID=(TextView) findViewById(R.id.VlblDSSID);
         txtDSSID=(EditText) findViewById(R.id.txtDSSID);

         secMSlNo=(LinearLayout)findViewById(R.id.secMSlNo);
         lineMSlNo=(View)findViewById(R.id.lineMSlNo);
         VlblMSlNo=(TextView) findViewById(R.id.VlblMSlNo);
         txtMSlNo=(EditText) findViewById(R.id.txtMSlNo);

         if(MSLNO.length()==0)
             txtMSlNo.setText(MemNo(HHID));
         else
             txtMSlNo.setText(MSLNO);
         txtMSlNo.setEnabled(false);
         txtDSSID.setText( VILLID.toString()+CompoundID.toString()+HHID.toString()+txtMSlNo.getText().toString());

         secName=(LinearLayout)findViewById(R.id.secName);
         lineName=(View)findViewById(R.id.lineName);
         VlblName=(TextView) findViewById(R.id.VlblName);
         txtName=(EditText) findViewById(R.id.txtName);
         secRth=(LinearLayout)findViewById(R.id.secRth);
         lineRth=(View)findViewById(R.id.lineRth);
         VlblRth=(TextView) findViewById(R.id.VlblRth);
         spnRth=(Spinner) findViewById(R.id.spnRth);
//         List<String> listRth = new ArrayList<String>();
//         listRth.add("");
//         listRth.add("01-Household head");
//         listRth.add("02-Spouse of household head");
//         listRth.add("03-Son/Daughter");
//         listRth.add("04-Adopted son/daughter");
//         listRth.add("05-Stepson/Stepdaughter");
//         listRth.add("06-Nephew/Niece");
//         listRth.add("07-Grandson/Granddaughter");
//         listRth.add("08-Father/Mother");
//         listRth.add("09-Stepfather/Stepmother");
//         listRth.add("10-Brother/Sister");
//         listRth.add("11-Stepbrother/Stepsister");
//         listRth.add("12-Cousin");
//         listRth.add("13-Uncle/Aunt");
//         listRth.add("14-Grandfather/Grandmother");
//         listRth.add("15-Father/mother-in-law");
//         listRth.add("16-Son/daughter-in-law");
//         listRth.add("17-Brother/sister-in-law");
//         listRth.add("18-Unrelated");
//         listRth.add("96-Other");
//         listRth.add("98-Dont know");
//         listRth.add("99-Refused to respond");
//         spnRth.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listRth)));

         spnRth.setAdapter(C.getArrayAdapter("Select '' union Select Code||'-'||Name from RTH "));

         spnRth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnRth.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnRth.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("96"))
                 {
                     secRthOth.setVisibility(View.VISIBLE);
                     lineRthOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secRthOth.setVisibility(View.GONE);
                     lineRthOth.setVisibility(View.GONE);
                     txtRthOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secRthOth=(LinearLayout)findViewById(R.id.secRthOth);
         lineRthOth=(View)findViewById(R.id.lineRthOth);
         VlblRthOth=(TextView) findViewById(R.id.VlblRthOth);
         txtRthOth=(EditText) findViewById(R.id.txtRthOth);
         secSex=(LinearLayout)findViewById(R.id.secSex);
         lineSex=(View)findViewById(R.id.lineSex);
         VlblSex = (TextView) findViewById(R.id.VlblSex);
         rdogrpSex = (RadioGroup) findViewById(R.id.rdogrpSex);
         rdoSex1 = (RadioButton) findViewById(R.id.rdoSex1);
         rdoSex2 = (RadioButton) findViewById(R.id.rdoSex2);
         rdoSex3 = (RadioButton) findViewById(R.id.rdoSex3);
         rdoSex4 = (RadioButton) findViewById(R.id.rdoSex4);
         rdoSex5 = (RadioButton) findViewById(R.id.rdoSex5);
         rdogrpSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpSex = new String[] {"1","2","3","4","8"};
                 for (int i = 0; i < rdogrpSex.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpSex.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpSex[i];
                 }
                 MemSex=rbData;

             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });


         secBDate=(LinearLayout)findViewById(R.id.secBDate);
         lineBDate=(View)findViewById(R.id.lineBDate);
         VlblBDate=(TextView) findViewById(R.id.VlblBDate);
         dtpBDate=(EditText) findViewById(R.id.dtpBDate);
         secBDateType=(LinearLayout)findViewById(R.id.secBDateType);
         lineBDateType=(View)findViewById(R.id.lineBDateType);
         VlblBDateType = (TextView) findViewById(R.id.VlblBDateType);
         rdogrpBDateType = (RadioGroup) findViewById(R.id.rdogrpBDateType);
         rdoBDateType1 = (RadioButton) findViewById(R.id.rdoBDateType1);
         rdoBDateType2 = (RadioButton) findViewById(R.id.rdoBDateType2);
         secAgeY=(LinearLayout)findViewById(R.id.secAgeY);
         lineAgeY=(View)findViewById(R.id.lineAgeY);
         VlblAgeY=(TextView) findViewById(R.id.VlblAgeY);
         txtAgeY=(EditText) findViewById(R.id.txtAgeY);
         secMoNo=(LinearLayout)findViewById(R.id.secMoNo);
         lineMoNo=(View)findViewById(R.id.lineMoNo);
         VlblMoNo=(TextView) findViewById(R.id.VlblMoNo);
         spnMoNo=(Spinner) findViewById(R.id.spnMoNo);
//         List<String> listMoNo = new ArrayList<String>();
//         listMoNo.add("");
//         listMoNo.add("00-Not a member of this HH");
//         listMoNo.add("01-a");
//         listMoNo.add("02-b");
//         spnMoNo.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listMoNo)));
         spnMoNo.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where HHID='"+ HHID + "' and isdelete='2' and Sex='2' and MS<>'30' union Select '00-Not a member of this Household'"));

         spnMoNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnMoNo.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnMoNo.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("00"))
                 {
                     secMoName.setVisibility(View.VISIBLE);
                     lineMoName.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secMoName.setVisibility(View.GONE);
                     lineMoName.setVisibility(View.GONE);
                     txtMoName.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secMoName=(LinearLayout)findViewById(R.id.secMoName);
         lineMoName=(View)findViewById(R.id.lineMoName);
         VlblMoName=(TextView) findViewById(R.id.VlblMoName);
         txtMoName=(EditText) findViewById(R.id.txtMoName);
         secFaNo=(LinearLayout)findViewById(R.id.secFaNo);
         lineFaNo=(View)findViewById(R.id.lineFaNo);
         VlblFaNo=(TextView) findViewById(R.id.VlblFaNo);
         spnFaNo=(Spinner) findViewById(R.id.spnFaNo);
//         List<String> listFaNo = new ArrayList<String>();
//         listFaNo.add("");
//         listFaNo.add("00-Not a member of this HH");
//         listFaNo.add("01-a");
//         listFaNo.add("02-b");
//         spnFaNo.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listFaNo)));

         spnFaNo.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where HHID='"+ HHID +"' and isdelete='2' and Sex='1' and MS<>'30' union Select '00-Not a member of this Household'"));

         spnFaNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnFaNo.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnFaNo.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("00"))
                 {
                     secFaName.setVisibility(View.VISIBLE);
                     lineFaName.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secFaName.setVisibility(View.GONE);
                     lineFaName.setVisibility(View.GONE);
                     txtFaName.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secFaName=(LinearLayout)findViewById(R.id.secFaName);
         lineFaName=(View)findViewById(R.id.lineFaName);
         VlblFaName=(TextView) findViewById(R.id.VlblFaName);
         txtFaName=(EditText) findViewById(R.id.txtFaName);
         secEduY=(LinearLayout)findViewById(R.id.secEduY);
         lineEduY=(View)findViewById(R.id.lineEduY);
         VlblEduY=(TextView) findViewById(R.id.VlblEduY);
         txtEduY=(EditText) findViewById(R.id.txtEduY);

         secMS=(LinearLayout)findViewById(R.id.secMS);
         lineMS=(View)findViewById(R.id.lineMS);
         VlblMS = (TextView) findViewById(R.id.VlblMS);
         rdogrpMS = (RadioGroup) findViewById(R.id.rdogrpMS);
         rdoMS1 = (RadioButton) findViewById(R.id.rdoMS1);
         rdoMS2 = (RadioButton) findViewById(R.id.rdoMS2);
         rdoMS3 = (RadioButton) findViewById(R.id.rdoMS3);
         rdoMS4 = (RadioButton) findViewById(R.id.rdoMS4);
         rdoMS5 = (RadioButton) findViewById(R.id.rdoMS5);
         rdoMS6 = (RadioButton) findViewById(R.id.rdoMS6);
         rdoMS7 = (RadioButton) findViewById(R.id.rdoMS7);
         rdoMS8 = (RadioButton) findViewById(R.id.rdoMS8);
         rdoMS9 = (RadioButton) findViewById(R.id.rdoMS9);
         rdoMS10 = (RadioButton) findViewById(R.id.rdoMS10);
         rdogrpMS.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpMS = new String[] {"00","01","02","03","04","05","06","96","98","99"};
             for (int i = 0; i < rdogrpMS.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpMS.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpMS[i];
             }
             if ((MemSex.equals("2")) & (rbData.equalsIgnoreCase("01") || rbData.equalsIgnoreCase("02")))
             {
                 seclblSpsl.setVisibility(View.VISIBLE);
                 spnSp1.setVisibility(View.VISIBLE);
                 lineSp1.setVisibility(View.VISIBLE);
                 secSp1.setVisibility(View.VISIBLE);
                 lineSp1.setVisibility(View.VISIBLE);
                 spnSp2.setVisibility(View.VISIBLE);
                 lineSp2.setVisibility(View.VISIBLE);
                 secSp2.setVisibility(View.VISIBLE);
                 lineSp2.setVisibility(View.VISIBLE);
                 spnSp3.setVisibility(View.VISIBLE);
                 lineSp3.setVisibility(View.VISIBLE);
                 secSp3.setVisibility(View.VISIBLE);
                 lineSp3.setVisibility(View.VISIBLE);
                 spnSp4.setVisibility(View.VISIBLE);
                 lineSp4.setVisibility(View.VISIBLE);
                 secSp4.setVisibility(View.VISIBLE);
                 lineSp4.setVisibility(View.VISIBLE);
                 secMobileNo.setVisibility(View.VISIBLE);
                 lineMobileNo.setVisibility(View.VISIBLE);
                 secPstat.setVisibility(View.VISIBLE);
                 linePstat.setVisibility(View.VISIBLE);
                 secLmpDt.setVisibility(View.VISIBLE);
                 lineLmpDt.setVisibility(View.VISIBLE);

             }else {
                 seclblSpsl.setVisibility(View.GONE);
                 spnSp1.setSelection(0);
                 spnSp1.setVisibility(View.GONE);
                 lineSp1.setVisibility(View.GONE);
                 secSp1.setVisibility(View.GONE);
                 lineSp1.setVisibility(View.GONE);
                 spnSp2.setSelection(0);
                 spnSp2.setVisibility(View.GONE);
                 lineSp2.setVisibility(View.GONE);
                 secSp2.setVisibility(View.GONE);
                 lineSp2.setVisibility(View.GONE);
                 spnSp3.setSelection(0);
                 spnSp3.setVisibility(View.GONE);
                 lineSp3.setVisibility(View.GONE);
                 secSp3.setVisibility(View.GONE);
                 lineSp3.setVisibility(View.GONE);
                 spnSp4.setSelection(0);
                 spnSp4.setVisibility(View.GONE);
                 lineSp4.setVisibility(View.GONE);
                 secSp4.setVisibility(View.GONE);
                 lineSp4.setVisibility(View.GONE);
                 secMobileNo.setVisibility(View.GONE);
                 lineMobileNo.setVisibility(View.GONE);
                 secPstat.setVisibility(View.GONE);
                 linePstat.setVisibility(View.GONE);
                 rdogrpPstat.clearCheck();
                 secLmpDt.setVisibility(View.GONE);
                 lineLmpDt.setVisibility(View.GONE);
                 dtpLmpDt.setText("");
             }

             if(rbData.equalsIgnoreCase("96"))
             {
                 secMSOth.setVisibility(View.VISIBLE);
                 lineMSOth.setVisibility(View.VISIBLE);
             }
             else
             {
                 secMSOth.setVisibility(View.GONE);
                 lineMSOth.setVisibility(View.GONE);
                 txtMSOth.setText("");
             }

         }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secMSOth=(LinearLayout)findViewById(R.id.secMSOth);
         lineMSOth=(View)findViewById(R.id.lineMSOth);
         VlblMSOth=(TextView) findViewById(R.id.VlblMSOth);
         txtMSOth=(EditText) findViewById(R.id.txtMSOth);
         secEmploy=(LinearLayout)findViewById(R.id.secEmploy);
         lineEmploy=(View)findViewById(R.id.lineEmploy);
         VlblEmploy = (TextView) findViewById(R.id.VlblEmploy);
         rdogrpEmploy = (RadioGroup) findViewById(R.id.rdogrpEmploy);
         rdoEmploy1 = (RadioButton) findViewById(R.id.rdoEmploy1);
         rdoEmploy2 = (RadioButton) findViewById(R.id.rdoEmploy2);
         rdoEmploy3 = (RadioButton) findViewById(R.id.rdoEmploy3);
         rdoEmploy4 = (RadioButton) findViewById(R.id.rdoEmploy4);
         rdoEmploy5 = (RadioButton) findViewById(R.id.rdoEmploy5);
         rdoEmploy6 = (RadioButton) findViewById(R.id.rdoEmploy6);
         rdoEmploy7 = (RadioButton) findViewById(R.id.rdoEmploy7);
         rdoEmploy8 = (RadioButton) findViewById(R.id.rdoEmploy8);
         rdoEmploy9 = (RadioButton) findViewById(R.id.rdoEmploy9);
         rdogrpEmploy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpEmploy = new String[] {"01","02","03","04","05","06","96","98","99"};
                 for (int i = 0; i < rdogrpEmploy.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpEmploy.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpEmploy[i];
                 }

                 if(rbData.equalsIgnoreCase("96"))
                 {
                     secEmployOth.setVisibility(View.VISIBLE);
                     lineEmployOth.setVisibility(View.VISIBLE);
                 }
                 else if(rbData.equalsIgnoreCase("98"))
                 {
                     secEmployOth.setVisibility(View.GONE);
                     lineEmployOth.setVisibility(View.GONE);
                     txtEmployOth.setText("");
                 }
                 else if(rbData.equalsIgnoreCase("99"))
                 {
                     secEmployOth.setVisibility(View.GONE);
                     lineEmployOth.setVisibility(View.GONE);
                     txtEmployOth.setText("");
                 }
                 else
                 {
                     secEmployOth.setVisibility(View.GONE);
                     lineEmployOth.setVisibility(View.GONE);
                     txtEmployOth.setText("");
                 }
             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });
         secEmployOth=(LinearLayout)findViewById(R.id.secEmployOth);
         lineEmployOth=(View)findViewById(R.id.lineEmployOth);
         VlblEmployOth=(TextView) findViewById(R.id.VlblEmployOth);
         txtEmployOth=(EditText) findViewById(R.id.txtEmployOth);
         secOcp=(LinearLayout)findViewById(R.id.secOcp);
         lineOcp=(View)findViewById(R.id.lineOcp);
         VlblOcp=(TextView) findViewById(R.id.VlblOcp);
         spnOcp=(Spinner) findViewById(R.id.spnOcp);
//         List<String> listOcp = new ArrayList<String>();
//         listOcp.add("");
//         listOcp.add("01-Unemployed");
//         listOcp.add("02-Student (not employed)");
//         listOcp.add("03-Housewife/Housekeeper (not employed)");
//         listOcp.add("96-Other");
//         listOcp.add("98-Dont know");
//         listOcp.add("99-Refused to respond");
//         spnOcp.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listOcp)));
         spnOcp.setAdapter(C.getArrayAdapter("Select '' union Select Code||'-'||Name from OCP "));

         spnOcp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnOcp.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnOcp.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("96"))
                 {
                     secOcpOth.setVisibility(View.VISIBLE);
                     lineOcpOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secOcpOth.setVisibility(View.GONE);
                     lineOcpOth.setVisibility(View.GONE);
                     txtOcpOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secOcpOth=(LinearLayout)findViewById(R.id.secOcpOth);
         lineOcpOth=(View)findViewById(R.id.lineOcpOth);
         VlblOcpOth=(TextView) findViewById(R.id.VlblOcpOth);
         txtOcpOth=(EditText) findViewById(R.id.txtOcpOth);
         secReligion=(LinearLayout)findViewById(R.id.secReligion);
         lineReligion=(View)findViewById(R.id.lineReligion);
         VlblReligion=(TextView) findViewById(R.id.VlblReligion);
         spnReligion=(Spinner) findViewById(R.id.spnReligion);
         /*List<String> listReligion = new ArrayList<String>();
         listReligion.add("");
         listReligion.add("01-African independent church");
         listReligion.add("02-African tribal tradition");
         listReligion.add("03-Anglican");
         listReligion.add("04-Animist");
         listReligion.add("05-Agnostic");
         listReligion.add("06-Atheist");
         listReligion.add("07-Baptist");
         listReligion.add("08-Buddhist");
         listReligion.add("09-Catholic");
         listReligion.add("10-Christian unspecified");
         listReligion.add("11-Evangelical");
         listReligion.add("12-Hindu");
         listReligion.add("13-Legio Maria");
         listReligion.add("14-Methodist");
         listReligion.add("15-Islam/Muslim");
         listReligion.add("16-Nomia");
         listReligion.add("17-No Religion");
         listReligion.add("18-Orthodox Christian");
         listReligion.add("19-Presbyterian");
         listReligion.add("20-Protestant Christian");
         listReligion.add("21-Roho");
         listReligion.add("22-SDA");
         listReligion.add("23-Traditional/Traditionalist");
         listReligion.add("24-Zion");
         listReligion.add("96-Other");
         listReligion.add("98-Dont know");
         listReligion.add("99-Refused to respond");
         spnReligion.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listReligion)));*/

         spnReligion.setAdapter(C.getArrayAdapter("Select '' union Select Code||'-'||Name from Religion "));

         spnReligion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnReligion.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnReligion.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("96"))
                 {
                     secReligionOth.setVisibility(View.VISIBLE);
                     lineReligionOth.setVisibility(View.VISIBLE);
                 }
                 else if(spnData.equalsIgnoreCase("98"))
                 {
                    secReligionOth.setVisibility(View.GONE);
                    lineReligionOth.setVisibility(View.GONE);
                    txtReligionOth.setText("");
                 }
                 else if(spnData.equalsIgnoreCase("99"))
                 {
                    secReligionOth.setVisibility(View.GONE);
                    lineReligionOth.setVisibility(View.GONE);
                    txtReligionOth.setText("");
                 }
                 else
                 {
                     secReligionOth.setVisibility(View.GONE);
                     lineReligionOth.setVisibility(View.GONE);
                     txtReligionOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secReligionOth=(LinearLayout)findViewById(R.id.secReligionOth);
         lineReligionOth=(View)findViewById(R.id.lineReligionOth);
         VlblReligionOth=(TextView) findViewById(R.id.VlblReligionOth);
         txtReligionOth=(EditText) findViewById(R.id.txtReligionOth);
         secEthnicity=(LinearLayout)findViewById(R.id.secEthnicity);
         lineEthnicity=(View)findViewById(R.id.lineEthnicity);
         VlblEthnicity = (TextView) findViewById(R.id.VlblEthnicity);
         spnEthnicity=(Spinner) findViewById(R.id.spnEthnicity);
         spnEthnicity.setAdapter(C.getArrayAdapter("Select '' union Select Code||'-'||Name from Ethnicity "));

         secMobileNo=(LinearLayout)findViewById(R.id.secMobileNo);
         lineMobileNo=(View)findViewById(R.id.lineMobileNo);
         VlblMobileNo=(TextView) findViewById(R.id.VlblMobileNo);
         txtMobileNo=(EditText) findViewById(R.id.txtMobileNo);

         seclblSpsl=(LinearLayout)findViewById(R.id.seclblSpsl);
         linelblSpsl=(View)findViewById(R.id.linelblSpsl);
         secSp1=(LinearLayout)findViewById(R.id.secSp1);
         lineSp1=(View)findViewById(R.id.lineSp1);
         VlblSp1=(TextView) findViewById(R.id.VlblSp1);
         spnSp1=(Spinner) findViewById(R.id.spnSp1);
         spnSp1.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where HHID='"+ HHID +"' and isdelete='2' union Select '00-Not a member of this Household'"));

         spnSp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnSp1.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnSp1.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("00"))
                 {
                     secSp1Name.setVisibility(View.VISIBLE);
                     lineSp1Name.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secSp1Name.setVisibility(View.GONE);
                     lineSp1Name.setVisibility(View.GONE);
                     txtSp1Name.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secSp1Name=(LinearLayout)findViewById(R.id.secSp1Name);
         lineSp1Name=(View)findViewById(R.id.lineSp1Name);
         VlblSp1Name=(TextView) findViewById(R.id.VlblSp1Name);
         txtSp1Name=(EditText) findViewById(R.id.txtSp1Name);
         secSp2=(LinearLayout)findViewById(R.id.secSp2);
         lineSp2=(View)findViewById(R.id.lineSp2);
         VlblSp2=(TextView) findViewById(R.id.VlblSp2);
         spnSp2=(Spinner) findViewById(R.id.spnSp2);
         spnSp2.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where HHID='"+ HHID +"' and isdelete='2' union Select '00-Not a member of this Household'"));

         spnSp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnSp2.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnSp2.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("00"))
                 {
                     secSp2Name.setVisibility(View.VISIBLE);
                     lineSp2Name.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secSp2Name.setVisibility(View.GONE);
                     lineSp2Name.setVisibility(View.GONE);
                     txtSp2Name.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secSp2Name=(LinearLayout)findViewById(R.id.secSp2Name);
         lineSp2Name=(View)findViewById(R.id.lineSp2Name);
         VlblSp2Name=(TextView) findViewById(R.id.VlblSp2Name);
         txtSp2Name=(EditText) findViewById(R.id.txtSp2Name);
         secSp3=(LinearLayout)findViewById(R.id.secSp3);
         lineSp3=(View)findViewById(R.id.lineSp3);
         VlblSp3=(TextView) findViewById(R.id.VlblSp3);
         spnSp3=(Spinner) findViewById(R.id.spnSp3);
         spnSp3.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where HHID='"+ HHID +"' and isdelete='2' union Select '00-Not a member of this Household'"));

         spnSp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnSp3.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnSp3.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("00"))
                 {
                     secSp3Name.setVisibility(View.VISIBLE);
                     lineSp3Name.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secSp3Name.setVisibility(View.GONE);
                     lineSp3Name.setVisibility(View.GONE);
                     txtSp3Name.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secSp3Name=(LinearLayout)findViewById(R.id.secSp3Name);
         lineSp3Name=(View)findViewById(R.id.lineSp3Name);
         VlblSp3Name=(TextView) findViewById(R.id.VlblSp3Name);
         txtSp3Name=(EditText) findViewById(R.id.txtSp3Name);
         secSp4=(LinearLayout)findViewById(R.id.secSp4);
         lineSp4=(View)findViewById(R.id.lineSp4);
         VlblSp4=(TextView) findViewById(R.id.VlblSp4);
         spnSp4=(Spinner) findViewById(R.id.spnSp4);
         spnSp4.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where HHID='"+ HHID +"' and isdelete='2' union Select '00-Not a member of this Household'"));


         spnSp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnSp4.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnSp4.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("00"))
                 {
                     secSp4Name.setVisibility(View.VISIBLE);
                     lineSp4Name.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secSp4Name.setVisibility(View.GONE);
                     lineSp4Name.setVisibility(View.GONE);
                     txtSp4Name.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secSp4Name=(LinearLayout)findViewById(R.id.secSp4Name);
         lineSp4Name=(View)findViewById(R.id.lineSp4Name);
         VlblSp4Name=(TextView) findViewById(R.id.VlblSp4Name);
         txtSp4Name=(EditText) findViewById(R.id.txtSp4Name);
         secPstat=(LinearLayout)findViewById(R.id.secPstat);
         linePstat=(View)findViewById(R.id.linePstat);
         VlblPstat = (TextView) findViewById(R.id.VlblPstat);
         rdogrpPstat = (RadioGroup) findViewById(R.id.rdogrpPstat);
         rdoPstat1 = (RadioButton) findViewById(R.id.rdoPstat1);
         rdoPstat2 = (RadioButton) findViewById(R.id.rdoPstat2);
         rdoPstat3 = (RadioButton) findViewById(R.id.rdoPstat3);
         rdogrpPstat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpPstat = new String[] {"40","41","49"};
             for (int i = 0; i < rdogrpPstat.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpPstat.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpPstat[i];
             }

             if(rbData.equalsIgnoreCase("40"))
             {
                    secLmpDt.setVisibility(View.GONE);
                    lineLmpDt.setVisibility(View.GONE);
                    dtpLmpDt.setText("");
             }
             else if(rbData.equalsIgnoreCase("49"))
             {
                    secLmpDt.setVisibility(View.GONE);
                    lineLmpDt.setVisibility(View.GONE);
                    dtpLmpDt.setText("");
             }
             else
             {
                    secLmpDt.setVisibility(View.VISIBLE);
                    lineLmpDt.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secLmpDt=(LinearLayout)findViewById(R.id.secLmpDt);
         lineLmpDt=(View)findViewById(R.id.lineLmpDt);
         VlblLmpDt=(TextView) findViewById(R.id.VlblLmpDt);
         dtpLmpDt=(EditText) findViewById(R.id.dtpLmpDt);
         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         secActive=(LinearLayout)findViewById(R.id.secActive);
         lineActive=(View)findViewById(R.id.lineActive);
         VlblActive=(TextView) findViewById(R.id.VlblActive);
         txtActive=(EditText) findViewById(R.id.txtActive);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Member_Old.this, e.getMessage());
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
         	Connection.MessageBox(Surv_Member_Old.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         Member_DataModel objSave = new Member_DataModel();
         objSave.setMemID(txtMemID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setDSSID(txtDSSID.getText().toString());
         objSave.setMSlNo(txtMSlNo.getText().toString());
         objSave.setName(txtName.getText().toString());
         objSave.setRth(spnRth.getSelectedItemPosition() == 0 ? "" : spnRth.getSelectedItem().toString().split("-")[0]);
         objSave.setRthOth(txtRthOth.getText().toString());
         String[] d_rdogrpSex = new String[] {"1","2","3","4","8"};
         objSave.setSex("");
         for (int i = 0; i < rdogrpSex.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSex.getChildAt(i);
             if (rb.isChecked()) objSave.setSex(d_rdogrpSex[i]);
         }

         objSave.setBDate(dtpBDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpBDate.getText().toString()) : dtpBDate.getText().toString());
         String[] d_rdogrpBDateType = new String[] {"1","2"};
         objSave.setBDateType("");
         for (int i = 0; i < rdogrpBDateType.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpBDateType.getChildAt(i);
             if (rb.isChecked()) objSave.setBDateType(d_rdogrpBDateType[i]);
         }

         //objSave.setAgeY(txtAgeY.getText().toString());
         objSave.setMoNo(spnMoNo.getSelectedItemPosition() == 0 ? "" : spnMoNo.getSelectedItem().toString().split("-")[0]);
         objSave.setMoName(txtMoName.getText().toString());
         objSave.setFaNo(spnFaNo.getSelectedItemPosition() == 0 ? "" : spnFaNo.getSelectedItem().toString().split("-")[0]);
         objSave.setFaName(txtFaName.getText().toString());
         objSave.setEduY(txtEduY.getText().toString());
         String[] d_rdogrpMS = new String[] {"00","01","02","03","04","05","06","96","98","99"};
         objSave.setMS("");
         for (int i = 0; i < rdogrpMS.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMS.getChildAt(i);
             if (rb.isChecked()) objSave.setMS(d_rdogrpMS[i]);
         }

         objSave.setMSOth(txtMSOth.getText().toString());
         String[] d_rdogrpEmploy = new String[] {"01","02","03","04","05","06","96","98","99"};
         objSave.setEmploy("");
         for (int i = 0; i < rdogrpEmploy.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpEmploy.getChildAt(i);
             if (rb.isChecked()) objSave.setEmploy(d_rdogrpEmploy[i]);
         }

         objSave.setEmployOth(txtEmployOth.getText().toString());
         objSave.setOcp(spnOcp.getSelectedItemPosition() == 0 ? "" : spnOcp.getSelectedItem().toString().split("-")[0]);
         //objSave.setOcpOth(txtOcpOth.getText().toString());
         objSave.setReligion(spnReligion.getSelectedItemPosition() == 0 ? "" : spnReligion.getSelectedItem().toString().split("-")[0]);
         objSave.setReligionOth(txtReligionOth.getText().toString());
/*         String[] d_rdogrpEthnicity = new String[] {"1","2"};
         objSave.setEthnicity("");
         for (int i = 0; i < rdogrpEthnicity.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpEthnicity.getChildAt(i);
             if (rb.isChecked()) objSave.setEthnicity(d_rdogrpEthnicity[i]);
         }*/

         objSave.setEthnicity(spnEthnicity.getSelectedItemPosition() == 0 ? "" : spnEthnicity.getSelectedItem().toString().split("-")[0]);

         objSave.setMobileNo(txtMobileNo.getText().toString());
         objSave.setSp1(spnSp1.getSelectedItemPosition() == 0 ? "" : spnSp1.getSelectedItem().toString().split("-")[0]);
         objSave.setSp1Name(txtSp1Name.getText().toString());
         objSave.setSp2(spnSp2.getSelectedItemPosition() == 0 ? "" : spnSp2.getSelectedItem().toString().split("-")[0]);
         objSave.setSp2Name(txtSp2Name.getText().toString());
         objSave.setSp3(spnSp3.getSelectedItemPosition() == 0 ? "" : spnSp3.getSelectedItem().toString().split("-")[0]);
         objSave.setSp3Name(txtSp3Name.getText().toString());
         objSave.setSp4(spnSp4.getSelectedItemPosition() == 0 ? "" : spnSp4.getSelectedItem().toString().split("-")[0]);
         objSave.setSp4Name(txtSp4Name.getText().toString());
         String[] d_rdogrpPstat = new String[] {"40","41","49"};
         objSave.setPstat("");
         for (int i = 0; i < rdogrpPstat.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPstat.getChildAt(i);
             if (rb.isChecked()) objSave.setPstat(d_rdogrpPstat[i]);
         }

         objSave.setLmpDt(dtpLmpDt.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpLmpDt.getText().toString()) : dtpLmpDt.getText().toString());
         objSave.setRnd(txtRnd.getText().toString());
         objSave.setActive(txtActive.getText().toString());
         objSave.setStartTime(STARTTIME);
         objSave.setEndTime(g.CurrentTime24());
         objSave.setDeviceID(DEVICEID);
         objSave.setEntryUser(ENTRYUSER); //from data entry user list
         objSave.setLat(MySharedPreferences.getValue(this, "lat"));
         objSave.setLon(MySharedPreferences.getValue(this, "lon"));

         String status = objSave.SaveUpdateData(this);
         if(status.length()==0) {

             String resp = "";
             String SQL1 = "";
             if (EV_TYPE.equals("20") || EV_TYPE.equals("21")  || EV_TYPE.equals("22") || EV_TYPE.equals("23") || EV_TYPE.equals("25")) {
                 //Insert Member Move
                 SQL1 = " Insert into MemberMove(MemID, HHID, Active, DSSID, MEnType, MEnDate, MExType, MExDate, Rnd, MemNote, isdelete, StartTime, EndTime, DeviceID, EntryUser, Lat, Lon, EnDt, Upload, modifyDate)" +
                         " Select MemID, HHID, '1', DSSID, '" + EV_TYPE + "', EnDt, '', '', '" + txtRnd.getText().toString() +"', '', isdelete, StartTime, EndTime, DeviceID, EntryUser, Lat, Lon, EnDt, Upload, modifyDate\n" +
                         " from Member where " +
                         " MemID='" + txtMemID.getText().toString() + "'";
                 resp = C.SaveData(SQL1);

                 Intent returnIntent = new Intent();
                 returnIntent.putExtra("res", "");
                 setResult(Activity.RESULT_OK, returnIntent);
                 Connection.MessageBox(Surv_Member_Old.this, "Saved Successfully");
                 finish();
             }
         }
         else{
             Connection.MessageBox(Surv_Member_Old.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Member_Old.this, e.getMessage());
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
             ValidationMsg += "\nRequired field: Internal Member ID.";
             secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
           {
             ValidationMsg += "\nRequired field: Household ID.";
             secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDSSID.getText().toString().length()==0 & secDSSID.isShown())
           {
             ValidationMsg += "\nRequired field: Member Permanent ID.";
             secDSSID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMSlNo.getText().toString().length()==0 & secMSlNo.isShown())
           {
             ValidationMsg += "\nRequired field: Member Serial Number.";
             secMSlNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtName.getText().toString().length()==0 & secName.isShown())
           {
             ValidationMsg += "\nHR1 Required field: Member Name.";
             secName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnRth.getSelectedItemPosition()==0  & secRth.isShown())
           {
             ValidationMsg += "\nHR3 Required field: What is relationship with HH head.";
             secRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRthOth.getText().toString().length()==0 & secRthOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other relation Specify.";
             secRthOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSex1.isChecked() & !rdoSex2.isChecked() & !rdoSex3.isChecked() & !rdoSex4.isChecked() & !rdoSex5.isChecked() & secSex.isShown())
           {
             ValidationMsg += "\nHR4 Required field: Sex.";
             secSex.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpBDate.getText().toString());
         if(DV.length()!=0 & secBDate.isShown())
           {
             ValidationMsg += "\nHR4.1 Required field/Not a valid date format: Date of Birth.";
             secBDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoBDateType1.isChecked() & !rdoBDateType2.isChecked() & secBDateType.isShown())
           {
             ValidationMsg += "\nRequired field: Date of Birth Type.";
             secBDateType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtAgeY.getText().toString().length()==0 & secAgeY.isShown())
           {
             ValidationMsg += "\nHR5 Required field: What is age in completed years.";
             secAgeY.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }

         int ageday = Global.DateDifferenceDays(Global.DateNowDMY(),dtpBDate.getText().toString());
         int ageyear = Integer.parseInt(txtAgeY.getText().toString().length()==0?"0":txtAgeY.getText().toString());

         Double  D=ageday/365.25;
         int j = Integer.valueOf(D.intValue());

         if(j!=ageyear)
         {
             ValidationMsg += "\nAge does not match with date of birth, Age should be "+ j +" ";
             secAgeY.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

         if(spnMoNo.getSelectedItemPosition()==0  & secMoNo.isShown())
           {
             ValidationMsg += "\nHR6 Required field: Mothers ID (IF MOTHER IS A HOUSEHOLD MEMBER).";
             secMoNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         /*if(txtMoName.getText().toString().length()==0 & secMoName.isShown())
           {
             ValidationMsg += "\nRequired field: Mother Name.";
             secMoName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/
         if(spnFaNo.getSelectedItemPosition()==0  & secFaNo.isShown())
           {
             ValidationMsg += "\nHR7 Required field: Fathers ID (IF FATHER IS A HOUSEHOLD MEMBER).";
             secFaNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         /*if(txtFaName.getText().toString().length()==0 & secFaName.isShown())
           {
             ValidationMsg += "\nRequired field: Father Name.";
             secFaName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/
         if(txtEduY.getText().toString().length()==0 & secEduY.isShown())
           {
             ValidationMsg += "\nHR8 Required field: How many years of education has completed?.";
             secEduY.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoMS1.isChecked() & !rdoMS2.isChecked() & !rdoMS3.isChecked() & !rdoMS4.isChecked() & !rdoMS5.isChecked() & !rdoMS6.isChecked() & !rdoMS7.isChecked() & !rdoMS8.isChecked() & !rdoMS9.isChecked() & !rdoMS10.isChecked() & secMS.isShown())
           {
             ValidationMsg += "\nHR9 Required field: What is marital status.";
             secMS.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMSOth.getText().toString().length()==0 & secMSOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Specify.";
             secMSOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoEmploy1.isChecked() & !rdoEmploy2.isChecked() & !rdoEmploy3.isChecked() & !rdoEmploy4.isChecked() & !rdoEmploy5.isChecked() & !rdoEmploy6.isChecked() & !rdoEmploy7.isChecked() & !rdoEmploy8.isChecked() & !rdoEmploy9.isChecked() & secEmploy.isShown())
         {
             ValidationMsg += "\nHR11 Required field: What is employment status.";
             secEmploy.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtEmployOth.getText().toString().length()==0 & secEmployOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Employment status specify.";
             secEmployOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnOcp.getSelectedItemPosition()==0  & secOcp.isShown())
           {
             ValidationMsg += "\nHR12 Required field: What is primary income generating activity (main occupation) at this time?.";
             secOcp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtOcpOth.getText().toString().length()==0 & secOcpOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Occupation Specify.";
             secOcpOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnReligion.getSelectedItemPosition()==0  & secReligion.isShown())
           {
             ValidationMsg += "\nHR13 Required field: Religion.";
             secReligion.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtReligionOth.getText().toString().length()==0 & secReligionOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Religion Specify.";
             secReligionOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
/*         if(!rdoEthnicity1.isChecked() & !rdoEthnicity2.isChecked() & secEthnicity.isShown())
           {
             ValidationMsg += "\nhr14 Required field: Ethnicity.";
             secEthnicity.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/

         if(spnEthnicity.getSelectedItemPosition()==0  & secEthnicity.isShown())
         {
             ValidationMsg += "\nhr14 Required field: Ethnicity.";
             secEthnicity.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtMobileNo.getText().toString().length()==0 & secMobileNo.isShown())
           {
             ValidationMsg += "\nRequired field: Mobile No.";
             secMobileNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnSp1.getSelectedItemPosition()==0  & secSp1.isShown())
           {
             ValidationMsg += "\nRequired field: Spouse Serial No 1.";
             secSp1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSp1Name.getText().toString().length()==0 & secSp1Name.isShown())
           {
             ValidationMsg += "\nRequired field: Spouse Name.";
             secSp1Name.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         /*if(spnSp2.getSelectedItemPosition()==0  & secSp2.isShown())
           {
             ValidationMsg += "\nRequired field: Spouse Serial No 2.";
             secSp2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSp2Name.getText().toString().length()==0 & secSp2Name.isShown())
           {
             ValidationMsg += "\nRequired field: Spouse Name.";
             secSp2Name.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnSp3.getSelectedItemPosition()==0  & secSp3.isShown())
           {
             ValidationMsg += "\nRequired field: Spouse Serial No 3.";
             secSp3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSp3Name.getText().toString().length()==0 & secSp3Name.isShown())
           {
             ValidationMsg += "\nRequired field: Spouse Name.";
             secSp3Name.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnSp4.getSelectedItemPosition()==0  & secSp4.isShown())
           {
             ValidationMsg += "\nRequired field: Spouse Serial No 4.";
             secSp4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSp4Name.getText().toString().length()==0 & secSp4Name.isShown())
           {
             ValidationMsg += "\nRequired field: Spouse Name.";
             secSp4Name.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/
         if(!rdoPstat1.isChecked() & !rdoPstat2.isChecked() & !rdoPstat3.isChecked() & secPstat.isShown())
           {
             ValidationMsg += "\nRequired field: Pregnancy Status.";
             secPstat.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpLmpDt.getText().toString());
         if(DV.length()!=0 & secLmpDt.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: LMP Date.";
             secLmpDt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             ValidationMsg += "\nRequired field: Round.";
             secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtActive.getText().toString().length()==0 & secActive.isShown())
           {
             ValidationMsg += "\nRequired field: Member active.";
             secActive.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secHHID.setBackgroundColor(Color.WHITE);
             secDSSID.setBackgroundColor(Color.WHITE);
             secMSlNo.setBackgroundColor(Color.WHITE);
             secName.setBackgroundColor(Color.WHITE);
             secRth.setBackgroundColor(Color.WHITE);
             secRthOth.setBackgroundColor(Color.WHITE);
             secSex.setBackgroundColor(Color.WHITE);
             secBDate.setBackgroundColor(Color.WHITE);
             secBDateType.setBackgroundColor(Color.WHITE);
             secAgeY.setBackgroundColor(Color.WHITE);
             secMoNo.setBackgroundColor(Color.WHITE);
             secMoName.setBackgroundColor(Color.WHITE);
             secFaNo.setBackgroundColor(Color.WHITE);
             secFaName.setBackgroundColor(Color.WHITE);
             secEduY.setBackgroundColor(Color.WHITE);
             secMS.setBackgroundColor(Color.WHITE);
             secMSOth.setBackgroundColor(Color.WHITE);
             secEmploy.setBackgroundColor(Color.WHITE);
             secEmployOth.setBackgroundColor(Color.WHITE);
             secOcp.setBackgroundColor(Color.WHITE);
             secOcpOth.setBackgroundColor(Color.WHITE);
             secReligion.setBackgroundColor(Color.WHITE);
             secReligionOth.setBackgroundColor(Color.WHITE);
             secEthnicity.setBackgroundColor(Color.WHITE);
             secMobileNo.setBackgroundColor(Color.WHITE);
             secSp1.setBackgroundColor(Color.WHITE);
             secSp1Name.setBackgroundColor(Color.WHITE);
             secSp2.setBackgroundColor(Color.WHITE);
             secSp2Name.setBackgroundColor(Color.WHITE);
             secSp3.setBackgroundColor(Color.WHITE);
             secSp3Name.setBackgroundColor(Color.WHITE);
             secSp4.setBackgroundColor(Color.WHITE);
             secSp4Name.setBackgroundColor(Color.WHITE);
             secPstat.setBackgroundColor(Color.WHITE);
             secLmpDt.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
             secActive.setBackgroundColor(Color.WHITE);
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
           Member_DataModel d = new Member_DataModel();
           String SQL = "Select * from "+ TableName +"  Where MemID='"+ MemID +"'";
           List<Member_DataModel> data = d.SelectAll(this, SQL);
           for(Member_DataModel item : data){
             txtMemID.setText(item.getMemID());

//             txtHHID.setText(item.getHHID());

               if(HHID.length()==0) txtHHID.setText(item.getHHID());
               else txtHHID.setText(HHID);

             txtDSSID.setText(item.getDSSID());
             txtMSlNo.setText(item.getMSlNo());
             txtName.setText(item.getName());
             spnRth.setSelection(Global.SpinnerItemPositionAnyLength(spnRth, String.valueOf(item.getRth())));
             txtRthOth.setText(item.getRthOth());
             String[] d_rdogrpSex = new String[] {"1","2","3","4","8"};
             for (int i = 0; i < d_rdogrpSex.length; i++)
             {
                 if (String.valueOf(item.getSex()).equals(String.valueOf(d_rdogrpSex[i])))
                 {
                     rb = (RadioButton)rdogrpSex.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             dtpBDate.setText(item.getBDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getBDate()));
             String[] d_rdogrpBDateType = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpBDateType.length; i++)
             {
                 if (String.valueOf(item.getBDateType()).equals(String.valueOf(d_rdogrpBDateType[i])))
                 {
                     rb = (RadioButton)rdogrpBDateType.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             //txtAgeY.setText(item.getAgeY());
             spnMoNo.setSelection(Global.SpinnerItemPositionAnyLength(spnMoNo, String.valueOf(item.getMoNo())));
             txtMoName.setText(item.getMoName());
             spnFaNo.setSelection(Global.SpinnerItemPositionAnyLength(spnFaNo, String.valueOf(item.getFaNo())));
             txtFaName.setText(item.getFaName());
             txtEduY.setText(item.getEduY());
             String[] d_rdogrpMS = new String[] {"00","01","02","03","04","05","06","96","98","99"};
             for (int i = 0; i < d_rdogrpMS.length; i++)
             {
                 if (String.valueOf(item.getMS()).equals(String.valueOf(d_rdogrpMS[i])))
                 {
                     rb = (RadioButton)rdogrpMS.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtMSOth.setText(item.getMSOth());
               String[] d_rdogrpEmploy = new String[] {"01","02","03","04","05","06","96","98","99"};
             for (int i = 0; i < d_rdogrpEmploy.length; i++)
             {
                 if (String.valueOf(item.getEmploy()).equals(String.valueOf(d_rdogrpEmploy[i])))
                 {
                     rb = (RadioButton)rdogrpEmploy.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtEmployOth.setText(item.getEmployOth());
             spnOcp.setSelection(Global.SpinnerItemPositionAnyLength(spnOcp, String.valueOf(item.getOcp())));
             //txtOcpOth.setText(item.getOcpOth());
             spnReligion.setSelection(Global.SpinnerItemPositionAnyLength(spnReligion, String.valueOf(item.getReligion())));
             txtReligionOth.setText(item.getReligionOth());
             /*String[] d_rdogrpEthnicity = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpEthnicity.length; i++)
             {
                 if (String.valueOf(item.getEthnicity()).equals(String.valueOf(d_rdogrpEthnicity[i])))
                 {
                     rb = (RadioButton)rdogrpEthnicity.getChildAt(i);
                     rb.setChecked(true);
                 }
             }*/

             spnEthnicity.setSelection(Global.SpinnerItemPositionAnyLength(spnEthnicity, String.valueOf(item.getEthnicity())));
             txtMobileNo.setText(item.getMobileNo());
             spnSp1.setSelection(Global.SpinnerItemPositionAnyLength(spnSp1, String.valueOf(item.getSp1())));
             txtSp1Name.setText(item.getSp1Name());
             spnSp2.setSelection(Global.SpinnerItemPositionAnyLength(spnSp2, String.valueOf(item.getSp2())));
             txtSp2Name.setText(item.getSp2Name());
             spnSp3.setSelection(Global.SpinnerItemPositionAnyLength(spnSp3, String.valueOf(item.getSp3())));
             txtSp3Name.setText(item.getSp3Name());
             spnSp4.setSelection(Global.SpinnerItemPositionAnyLength(spnSp4, String.valueOf(item.getSp4())));
             txtSp4Name.setText(item.getSp4Name());
             String[] d_rdogrpPstat = new String[] {"40","41","49"};
             for (int i = 0; i < d_rdogrpPstat.length; i++)
             {
                 if (String.valueOf(item.getPstat()).equals(String.valueOf(d_rdogrpPstat[i])))
                 {
                     rb = (RadioButton)rdogrpPstat.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             dtpLmpDt.setText(item.getLmpDt().toString().length()==0 ? "" : Global.DateConvertDMY(item.getLmpDt()));
             txtRnd.setText(item.getRnd());
             txtActive.setText(item.getActive());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Member_Old.this, e.getMessage());
            return;
        }
     }


     public void DataSearchMember(String MEMID)
     {
         try
         {
             RadioButton rb;
             Member_DataModel d = new Member_DataModel();
             String SQL = "Select MemID,DSSID ,Name, Sex, BDate,BDateType,Cast(((julianday(date('now'))-julianday(BDate))/365.25) as int) as AgeY,Religion,Ethnicity ,MoNo,MoName, FaNo,FaName ,EduY, MS,Ocp, Pstat, LmpDt from "+ TableName +"  Where MemID='"+ MEMID +"'";
             List<Member_DataModel> data = d.SelectAll_MemberEvent(this, SQL);
             for(Member_DataModel item : data){

                 txtMemID.setText(item.getMemID());
                 txtName.setText(item.getName());
                 txtDSSID.setText(item.getDSSID());
                 String[] d_rdogrpSex = new String[] {"1","2","3","4"};
                 for (int i = 0; i < d_rdogrpSex.length; i++)
                 {
                     if (item.getSex().equals(String.valueOf(d_rdogrpSex[i])))
                     {
                         rb = (RadioButton)rdogrpSex.getChildAt(i);
                         rb.setChecked(true);
                     }
                 }
                 dtpBDate.setText(item.getBDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getBDate()));

                 String[] d_rdogrpBDateType = new String[] {"1","2"};
                 for (int i = 0; i < d_rdogrpBDateType.length; i++)
                 {
                     if (String.valueOf(item.getBDateType()).equals(String.valueOf(d_rdogrpBDateType[i])))
                     {
                         rb = (RadioButton)rdogrpBDateType.getChildAt(i);
                         rb.setChecked(true);
                     }
                 }

                 //txtAgeY.setText(item.getAgeY());
                 spnReligion.setSelection(Global.SpinnerItemPositionAnyLength(spnReligion, item.getReligion()));
                 spnEthnicity.setSelection(Global.SpinnerItemPositionAnyLength(spnEthnicity, item.getEthnicity()));
                 spnMoNo.setSelection(Global.SpinnerItemPositionAnyLength(spnMoNo, item.getMoNo()));
                 txtMoName.setText(item.getMoName());
                 spnFaNo.setSelection(Global.SpinnerItemPositionAnyLength(spnFaNo, item.getFaNo()));
                 txtFaName.setText(item.getFaName());
                 txtEduY.setText(item.getEduY());

                 String[] d_rdogrpMS = new String[] {"00","01","02","03","04","05","06","96","98","99"};
                 for (int i = 0; i < d_rdogrpMS.length; i++)
                 {
                     if (String.valueOf(item.getMS()).equals(String.valueOf(d_rdogrpMS[i])))
                     {
                         rb = (RadioButton)rdogrpMS.getChildAt(i);
                         rb.setChecked(true);
                     }
                 }
                 spnOcp.setSelection(Global.SpinnerItemPositionAnyLength(spnOcp, item.getOcp()));
                 String[] d_rdogrpPstat = new String[] {"40","41","49"};
                 for (int i = 0; i < d_rdogrpPstat.length; i++)
                 {
                     if (String.valueOf(item.getPstat()).equals(String.valueOf(d_rdogrpPstat[i])))
                     {
                         rb = (RadioButton)rdogrpPstat.getChildAt(i);
                         rb.setChecked(true);
                     }
                 }
                 dtpLmpDt.setText(item.getLmpDt().toString().length()==0 ? "" : Global.DateConvertDMY(item.getLmpDt()));
             }

         }
         catch(Exception  e)
         {
             Connection.MessageBox(Surv_Member_Old.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpBDate);
      if (VariableID.equals("btnBDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpBDate);
      }
      else if (VariableID.equals("btnLmpDt"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpLmpDt);
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

     private String MemNo(String HHID)
     {
         String M = C.ReturnSingleValue("Select cast(ifnull(max(MSlNo),0)+1 as varchar(2))MemNo from Member where HHID='"+ HHID +"'");
         M = Global.Right("0"+M,2);
         return M;
     }
 
 // turning off the GPS if its in on state. to avoid the battery drain.
 @Override
 protected void onDestroy() {
     // TODO Auto-generated method stub
     super.onDestroy();
 }
}