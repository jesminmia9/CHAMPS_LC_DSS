
 package forms_activity;


 import java.net.IDN;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.HashMap;
 import java.util.List;

 import android.Manifest;
 import android.app.*;
 import android.app.AlertDialog;
 import android.app.DatePickerDialog;
 import android.app.Dialog;
 import android.app.TimePickerDialog;
 import android.content.Context;
 import android.content.DialogInterface;
 import android.content.Intent;
 import android.content.pm.PackageManager;
 import android.location.Location;
 import android.location.LocationManager;
 import android.util.Log;
 import android.view.KeyEvent;
 import android.os.Bundle;
 import android.view.View;
 import android.location.LocationListener;
 import android.location.LocationManager;
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

 import androidx.appcompat.widget.AppCompatButton;
 import androidx.constraintlayout.widget.ConstraintLayout;
 import android.widget.AutoCompleteTextView;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.app.ActivityCompat;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.GPSService;
 import org.icddrb.champs_lc_dss.R;
 import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

 import forms_datamodel.GPS_DataModel;
 import Utility.*;
 import Common.*;

 public class GPS extends AppCompatActivity {
    //Disabled Back/Home key
    //--------------------------------------------------------------------------------------------------
    @Override 
    public boolean onKeyDown(int iKeyCode, KeyEvent event)
    {
        if(iKeyCode == KeyEvent.KEYCODE_BACK || iKeyCode == KeyEvent.KEYCODE_HOME) 
             { return false; }
        else { return true;  }
    }

     boolean GPS_SCAN_START = false;
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
    LinearLayout secIDNo;
    View lineIDNo;
    TextView VlblIDNo;
    EditText txtIDNo;
    LinearLayout secVillID;
    View lineVillID;
    TextView VlblVillID;
    EditText txtVillID;
    LinearLayout secGPSType;
    View lineGPSType;
    TextView VlblGPSType;
    RadioGroup rdogrpGPSType;
    RadioButton rdoGPSType1;
    RadioButton rdoGPSType2;
    LinearLayout secCompoundID;
    LinearLayout secCompoundCode;
    View lineCompoundID;
    TextView VlblCompoundID;
    EditText txtCompoundID;
    EditText txtCompoundCode;
    LinearLayout secLMName;
    View lineLMName;
    TextView VlblLMName;
    EditText txtLMName;
    LinearLayout secLMType;
    View lineLMType;
    TextView VlblLMType;
    Spinner spnLMType;

     LinearLayout secLMTypeOth;
     View lineLMTypeOth;
     TextView VlblLMTypeOth;
     EditText txtLMTypeOth;

    LinearLayout secLMSl;
    View lineLMSl;
    TextView VlblLMSl;
    EditText txtLMSl;
    LinearLayout secLatitude;
    View lineLatitude;
    TextView VlblLatitude;
    EditText txtLatitude;
    LinearLayout secLongitude;
    View lineLongitude;
    TextView VlblLongitude;
    EditText txtLongitude;
    LinearLayout secAltitude;
    View lineAltitude;
    TextView VlblAltitude;
    EditText txtAltitude;
    LinearLayout secAccuracy;
    View lineAccuracy;
    TextView VlblAccuracy;
    EditText txtAccuracy;
    LinearLayout secSatelites;
    View lineSatelites;
    TextView VlblSatelites;
    EditText txtSatelites;
    LinearLayout secGPSStatus;
    View lineGPSStatus;
    TextView VlblGPSStatus;
    RadioGroup rdogrpGPSStatus;
    RadioButton rdoGPSStatus1;
    RadioButton rdoGPSStatus2;
     LinearLayout secGPSStatusReason;
     View lineGPSStatusReason;
     TextView VlblGPSStatusReason;
     Spinner spnGPSStatusReason;
    LinearLayout secGPSNote;
    View lineGPSNote;
    TextView VlblGPSNote;
    EditText txtGPSNote;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;

     LinearLayout secGPSNote1;
     View lineGPSNote1;

    static int MODULEID   = 0;
    static int LANGUAGEID = 0;
    static String TableName;

    static String STARTTIME = "";
    static String DEVICEID  = "";
    static String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
    static String IDNO = "";
    static String VILLID = "";
    static String GPSTYPE = "";
    static String GPSTypes = "";
    static String LMSL = "";
    static String COMPOUNDID = "";
    static String COMPOUNDCODE = "";
    static String ROUND = "";

     public static final String BROADCAST_ACTION = "gps_data";
     private static final int TWO_MINUTES = 1000 * 60 * 1;
     public LocationManager locationManager;
     public GPS.GPSLocationListener listener;
     public Location previousBestLocation = null;

     Intent intent;
 public void onCreate(Bundle savedInstanceState) {

     intent = new Intent(BROADCAST_ACTION);
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.gps);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         IDNO = IDbundle.getString("IDNO");
         VILLID = IDbundle.getString("VillID");
         COMPOUNDID = IDbundle.getString("COMPOUNDID");
         COMPOUNDCODE = IDbundle.getString("COMPOUNDCODE");
         GPSTYPE = IDbundle.getString("GPSTYPE");
         LMSL = IDbundle.getString("LMSL");
         ROUND = IDbundle.getString("round");

         TableName = "GPS";
         MODULEID = 22;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(GPS.this);
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

         AppCompatButton cmdScanAgain = findViewById(R.id.cmdScanAgain);
         cmdScanAgain.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(GPS.this);
                 adb.setTitle("GPS");
                 adb.setIcon(R.drawable.favicon);
                 adb.setMessage("Do you want to collect the GPS again[Yes/No]?");
                 adb.setNegativeButton("No", null);
                 adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         txtLatitude.setText("");
                         txtLongitude.setText("");
                         txtAccuracy.setText("");
                         txtSatelites.setText("0");
                         GPS_SCAN_START = true;
                         GPS_Start();
                     }});
                 adb.show();
             }});

        Initialization();
        Connection.LocalizeLanguage(GPS.this, MODULEID, LANGUAGEID);


        //Hide all skip variables
        secLMTypeOth.setVisibility(View.GONE);
        lineLMTypeOth.setVisibility(View.GONE);
         secGPSStatusReason.setVisibility(View.GONE);
         lineGPSStatusReason.setVisibility(View.GONE);

         String GPS_SQL = "";
         String ID = "";
         //Landmark
         if(GPSTYPE.equals("1")){
             GPS_SQL = "Select * from GPS where GPSType='1' and VillID='"+ VILLID +"' and IDNO='"+ txtIDNo.getText().toString() +"'";
         }
         //Bari/Compound
         else{
             txtLMSl.setText("0");
             IDNO = C.ReturnSingleValue("Select IDNO from GPS where GPSType='2' and VillID='"+ VILLID +"' and CompoundID='"+ COMPOUNDID +"'");
             if(IDNO.length()>0) txtIDNo.setText(IDNO);
             GPS_SQL = "Select * from GPS where GPSType='2' and VillID='"+ VILLID +"' and IDNO='"+ txtIDNo.getText().toString() +"'";
         }

         if(!C.Existence(GPS_SQL)){
             GPS_SCAN_START = true;
         }


        DataSearch(VILLID,txtIDNo.getText().toString());


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(GPS.this, e.getMessage());
         return;
     }




     GPS_Start();
 }

 private void GPS_Start(){
     try {
         locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         listener = new GPSLocationListener();
         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
             return;
         }
         locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 4000, 0, listener);
         locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 4000, 0, listener);
     }catch (Exception ex){

     }
 }

 private void Initialization()
 {
   try
     {
         secIDNo=(LinearLayout)findViewById(R.id.secIDNo);
         lineIDNo=(View)findViewById(R.id.lineIDNo);
         VlblIDNo=(TextView) findViewById(R.id.VlblIDNo);
         txtIDNo=(EditText) findViewById(R.id.txtIDNo);
         if(IDNO.length()==0) txtIDNo.setText(Global.Get_UUID(DEVICEID));
         else txtIDNo.setText(IDNO);
         txtIDNo.setEnabled(false);

         secVillID=(LinearLayout)findViewById(R.id.secVillID);
         lineVillID=(View)findViewById(R.id.lineVillID);
         VlblVillID=(TextView) findViewById(R.id.VlblVillID);
         txtVillID=(EditText) findViewById(R.id.txtVillID);
         txtVillID.setText(VILLID);
         txtVillID.setEnabled(false);
         secGPSType=(LinearLayout)findViewById(R.id.secGPSType);
         lineGPSType=(View)findViewById(R.id.lineGPSType);
         VlblGPSType = (TextView) findViewById(R.id.VlblGPSType);
         rdogrpGPSType = (RadioGroup) findViewById(R.id.rdogrpGPSType);
         rdoGPSType1 = (RadioButton) findViewById(R.id.rdoGPSType1);
         rdoGPSType2 = (RadioButton) findViewById(R.id.rdoGPSType2);

         secCompoundID=(LinearLayout)findViewById(R.id.secCompoundID);
         secCompoundCode=(LinearLayout)findViewById(R.id.secCompoundCode);
         lineCompoundID=(View)findViewById(R.id.lineCompoundID);
         VlblCompoundID=(TextView) findViewById(R.id.VlblCompoundID);
         txtCompoundID=(EditText) findViewById(R.id.txtCompoundID);
         txtCompoundCode=(EditText) findViewById(R.id.txtCompoundCode);
         txtCompoundID.setText(COMPOUNDID);
         txtCompoundID.setEnabled(false);
         txtCompoundCode.setText(COMPOUNDCODE);
         txtCompoundCode.setEnabled(false);
         secLMName=(LinearLayout)findViewById(R.id.secLMName);
         lineLMName=(View)findViewById(R.id.lineLMName);
         VlblLMName=(TextView) findViewById(R.id.VlblLMName);
         txtLMName=(EditText) findViewById(R.id.txtLMName);
         secLMType=(LinearLayout)findViewById(R.id.secLMType);
         lineLMType=(View)findViewById(R.id.lineLMType);
         VlblLMType=(TextView) findViewById(R.id.VlblLMType);
         spnLMType=(Spinner) findViewById(R.id.spnLMType);
         secLMTypeOth=(LinearLayout)findViewById(R.id.secLMTypeOth);
         lineLMTypeOth=(View)findViewById(R.id.lineLMTypeOth);
         VlblLMTypeOth=(TextView) findViewById(R.id.VlblLMTypeOth);
         txtLMTypeOth=(EditText) findViewById(R.id.txtLMTypeOth);

         List<String> listLandmarkType = new ArrayList<String>();
         //spnLMType.setAdapter(C.getArrayAdapter("Select '' union Select LandmarkType||'-'||TypeName TypeName from GPS_LandmarkType order by TypeName"));
         spnLMType.setAdapter(C.getArrayAdapter("Select '' union Select TypeName||'-'||LandmarkType TypeName from GPS_LandmarkType order by TypeName"));
         spnLMType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 String spnData = "";
                 if (spnLMType.getSelectedItem().toString().length() != 0)
                 {
                     spnData = spnLMType.getSelectedItem().toString().split("-")[1];
                 }
                 if (spnData.equalsIgnoreCase("77")) {

                     secLMTypeOth.setVisibility(View.VISIBLE);
                     lineLMTypeOth.setVisibility(View.VISIBLE);
                 } else {
                     secLMTypeOth.setVisibility(View.GONE);
                     lineLMTypeOth.setVisibility(View.GONE);
                     txtLMTypeOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });

         secLMSl=(LinearLayout)findViewById(R.id.secLMSl);
         lineLMSl=(View)findViewById(R.id.lineLMSl);
         VlblLMSl=(TextView) findViewById(R.id.VlblLMSl);
         txtLMSl=(EditText) findViewById(R.id.txtLMSl);
         txtLMSl=(EditText) findViewById(R.id.txtLMSl);

         if(LMSL.length()==0)
             txtLMSl.setText(C.NewLandmarkNumber(ENTRYUSER, VILLID));
         else
             txtLMSl.setText(LMSL);
         txtLMSl.setEnabled(false);

         secLatitude=(LinearLayout)findViewById(R.id.secLatitude);
         lineLatitude=(View)findViewById(R.id.lineLatitude);
         VlblLatitude=(TextView) findViewById(R.id.VlblLatitude);
         txtLatitude=(EditText) findViewById(R.id.txtLatitude);
         secLongitude=(LinearLayout)findViewById(R.id.secLongitude);
         lineLongitude=(View)findViewById(R.id.lineLongitude);
         VlblLongitude=(TextView) findViewById(R.id.VlblLongitude);
         txtLongitude=(EditText) findViewById(R.id.txtLongitude);
         secAltitude=(LinearLayout)findViewById(R.id.secAltitude);
         lineAltitude=(View)findViewById(R.id.lineAltitude);
         VlblAltitude=(TextView) findViewById(R.id.VlblAltitude);
         txtAltitude=(EditText) findViewById(R.id.txtAltitude);
         secAccuracy=(LinearLayout)findViewById(R.id.secAccuracy);
         lineAccuracy=(View)findViewById(R.id.lineAccuracy);
         VlblAccuracy=(TextView) findViewById(R.id.VlblAccuracy);
         txtAccuracy=(EditText) findViewById(R.id.txtAccuracy);
         secSatelites=(LinearLayout)findViewById(R.id.secSatelites);
         lineSatelites=(View)findViewById(R.id.lineSatelites);
         VlblSatelites=(TextView) findViewById(R.id.VlblSatelites);
         txtSatelites=(EditText) findViewById(R.id.txtSatelites);
         secGPSStatus=(LinearLayout)findViewById(R.id.secGPSStatus);
         lineGPSStatus=(View)findViewById(R.id.lineGPSStatus);
         VlblGPSStatus = (TextView) findViewById(R.id.VlblGPSStatus);
         rdogrpGPSStatus = (RadioGroup) findViewById(R.id.rdogrpGPSStatus);
         rdoGPSStatus1 = (RadioButton) findViewById(R.id.rdoGPSStatus1);
         rdoGPSStatus2 = (RadioButton) findViewById(R.id.rdoGPSStatus2);


         secGPSStatusReason=(LinearLayout)findViewById(R.id.secGPSStatusReason);
         lineGPSStatusReason=(View)findViewById(R.id.lineGPSStatusReason);
         VlblGPSStatusReason=(TextView) findViewById(R.id.VlblGPSStatusReason);
         spnGPSStatusReason=(Spinner) findViewById(R.id.spnGPSStatusReason);
         List<String> listGPSStatusReason = new ArrayList<String>();

         listGPSStatusReason.add("");
         listGPSStatusReason.add("1-Weather cloudy");
         listGPSStatusReason.add("2-Device problem");
         listGPSStatusReason.add("3-Minimum 3 satellite not connected");
         spnGPSStatusReason.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listGPSStatusReason)));

         rdogrpGPSStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpGPSStatus = new String[] {"1","2"};
             for (int i = 0; i < rdogrpGPSStatus.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpGPSStatus.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpGPSStatus[i];
             }

             if(rbData.equalsIgnoreCase("2"))
             {
                 secGPSStatusReason.setVisibility(View.VISIBLE);
                 lineGPSStatusReason.setVisibility(View.VISIBLE);
             }
             else
             {
                 secGPSStatusReason.setVisibility(View.GONE);
                 lineGPSStatusReason.setVisibility(View.GONE);
                 spnGPSStatusReason.setSelection(0);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         });
         secGPSNote=(LinearLayout)findViewById(R.id.secGPSNote);
         lineGPSNote=(View)findViewById(R.id.lineGPSNote);
         VlblGPSNote=(TextView) findViewById(R.id.VlblGPSNote);
         txtGPSNote=(EditText) findViewById(R.id.txtGPSNote);
         secGPSNote1=(LinearLayout)findViewById(R.id.secGPSNote1);
         lineGPSNote1=(View)findViewById(R.id.lineGPSNote1);

         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         txtRnd.setText(ROUND);


         if (GPSTYPE.equals("1"))
         {
             rdoGPSType1.setChecked(true);
             rdoGPSType2.setEnabled(false);
             GPSTypeSkipRule(GPSTYPE);
         }
         if (GPSTYPE.equals("2"))
         {
             rdoGPSType2.setChecked(true);
             rdoGPSType1.setEnabled(false);
             GPSTypeSkipRule(GPSTYPE);
         }
         rdogrpGPSType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpGPSType = new String[] {"1","2"};
                 for (int i = 0; i < rdogrpGPSType.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpGPSType.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpGPSType[i];
                 }
                 GPSTypes=rbData;

                 GPSTypeSkipRule(rbData);

             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });
     }
     catch(Exception  e)
     {
         Connection.MessageBox(GPS.this, e.getMessage());
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
         	Connection.MessageBox(GPS.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         GPS_DataModel objSave = new GPS_DataModel();
         objSave.setIDNo(txtIDNo.getText().toString());
         objSave.setVillID(txtVillID.getText().toString());
         String[] d_rdogrpGPSType = new String[] {"1","2"};
         objSave.setGPSType("");
         for (int i = 0; i < rdogrpGPSType.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGPSType.getChildAt(i);
             if (rb.isChecked()) objSave.setGPSType(d_rdogrpGPSType[i]);
         }

         objSave.setCompoundID(txtCompoundID.getText().toString());
         objSave.setLMName(txtLMName.getText().toString());
         objSave.setLMType(spnLMType.getSelectedItemPosition() == 0 ? "" : spnLMType.getSelectedItem().toString().split("-")[1]);
         objSave.setLMTypeOth(txtLMTypeOth.getText().toString());
         objSave.setLMSl(txtLMSl.getText().toString());
         objSave.setLatitude(txtLatitude.getText().toString());
         objSave.setLongitude(txtLongitude.getText().toString());
         objSave.setAltitude(txtAltitude.getText().toString());
         objSave.setAccuracy(txtAccuracy.getText().toString());
         objSave.setSatelites(txtSatelites.getText().toString());
         String[] d_rdogrpGPSStatus = new String[] {"1","2"};
         objSave.setGPSStatus("");
         for (int i = 0; i < rdogrpGPSStatus.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGPSStatus.getChildAt(i);
             if (rb.isChecked()) objSave.setGPSStatus(d_rdogrpGPSStatus[i]);
         }


         objSave.setGPSStatusReason(spnGPSStatusReason.getSelectedItemPosition() == 0 ? "" : spnGPSStatusReason.getSelectedItem().toString().split("-")[0]);
         objSave.setGPSNote(txtGPSNote.getText().toString());
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

             Toast.makeText(GPS.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
             finish();
         }
         else{
             Connection.MessageBox(GPS.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(GPS.this, e.getMessage());
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
         if(txtIDNo.getText().toString().length()==0 & secIDNo.isShown())
           {
             ValidationMsg += "\nRequired field: IDNo.";
             secIDNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtVillID.getText().toString().length()==0 & secVillID.isShown())
           {
             ValidationMsg += "\nRequired field: Internal village ID.";
             secVillID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGPSType1.isChecked() & !rdoGPSType2.isChecked() & secGPSType.isShown())
           {
             ValidationMsg += "\nRequired field: GPS Type.";
             secGPSType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCompoundID.getText().toString().length()==0 & secCompoundID.isShown())
           {
             ValidationMsg += "\nRequired field: Compound ID.";
             secCompoundID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtLMName.getText().toString().length()==0 & secLMName.isShown())
           {
             ValidationMsg += "\nRequired field: Landmark Name.";
             secLMName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnLMType.getSelectedItemPosition()==0  & secLMType.isShown())
           {
             ValidationMsg += "\nRequired field: Landmark Type.";
             secLMType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtLMTypeOth.getText().toString().length()==0 & secLMTypeOth.isShown())
         {
             ValidationMsg += "\nRequired field: Other Landmark Type.";
             secLMTypeOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtLMSl.getText().toString().length()==0 & secLMSl.isShown())
           {
             ValidationMsg += "\nRequired field: Landmark No.";
             secLMSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if (rdoGPSStatus1.isChecked()) {
             if (txtLatitude.getText().toString().length() == 0 & secLatitude.isShown()) {
                 ValidationMsg += "\nRequired field: Latitude.";
                 secLatitude.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
             }
             if (txtLongitude.getText().toString().length() == 0 & secLongitude.isShown()) {
                 ValidationMsg += "\nRequired field: Longitude.";
                 secLongitude.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
             }
             if (txtAltitude.getText().toString().length() == 0 & secAltitude.isShown()) {
                 ValidationMsg += "\nRequired field: Altitude.";
                 secAltitude.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
             }
             if (txtAccuracy.getText().toString().length() == 0 & secAccuracy.isShown()) {
                 ValidationMsg += "\nRequired field: Accuracy.";
                 secAccuracy.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
             }

             if (txtSatelites.getText().toString().length() == 0 & secSatelites.isShown()) {
                 ValidationMsg += "\nRequired field: Satellites.";
                 secSatelites.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
             }
             int total_satellite_connected = Integer.parseInt(txtSatelites.getText().toString().length()==0?"0":txtSatelites.getText().toString());
             if(total_satellite_connected<3){
                 ValidationMsg += "\nFor completed GPS Collection: At least three (3) satellites need to be connected for more accuracy.";
                 secSatelites.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
             }
         }

             if (!rdoGPSStatus1.isChecked() & !rdoGPSStatus2.isChecked() & secGPSStatus.isShown()) {
                 ValidationMsg += "\nRequired field: Status.";
                 secGPSStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
             }
         if(spnGPSStatusReason.getSelectedItemPosition()==0  & secGPSStatusReason.isShown())
         {
             ValidationMsg += "\nRequired field: Reason for Not Completed GPS.";
             secGPSStatusReason.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

         /*if(txtGPSNote.getText().toString().length()==0 & secGPSNote1.isShown())
           {
             ValidationMsg += "\nRequired field: Note.";
             secGPSNote1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             ValidationMsg += "\nRequired field: Round.";
             secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secRnd.isShown() & (Integer.valueOf(txtRnd.getText().toString().length()==0 ? "1" : txtRnd.getText().toString()) < 1 || Integer.valueOf(txtRnd.getText().toString().length()==0 ? "99" : txtRnd.getText().toString()) > 99))
           {
             ValidationMsg += "\nValue should be between 1 and 99(Round).";
             secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secIDNo.setBackgroundColor(Color.WHITE);
             secVillID.setBackgroundColor(Color.WHITE);
             secGPSType.setBackgroundColor(Color.WHITE);
             secCompoundID.setBackgroundColor(Color.WHITE);
             secLMName.setBackgroundColor(Color.WHITE);
             secLMType.setBackgroundColor(Color.WHITE);
             secLMTypeOth.setBackgroundColor(Color.WHITE);
             secLMSl.setBackgroundColor(Color.WHITE);
             secLatitude.setBackgroundColor(Color.WHITE);
             secLongitude.setBackgroundColor(Color.WHITE);
             secAltitude.setBackgroundColor(Color.WHITE);
             secAccuracy.setBackgroundColor(Color.WHITE);
             secSatelites.setBackgroundColor(Color.WHITE);
             secGPSStatus.setBackgroundColor(Color.WHITE);
            secGPSStatusReason.setBackgroundColor(Color.WHITE);
             secGPSNote1.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {

     }
 }

 private void DataSearch(String VillID, String IDNO)
     {
       try
        {     
           RadioButton rb;
           GPS_DataModel d = new GPS_DataModel();
            String SQL = "";

           //Bari/Compound
           if(GPSTYPE.equals("2"))
               SQL = "Select * from "+ TableName +"  Where GPSType='2' and VillID='"+ VillID +"' and IDNO='"+ IDNO +"'";
           else
               SQL = "Select * from "+ TableName +"  Where GPSType='1' and VillID='"+ VillID +"' and IDNO='"+ IDNO +"'";

           List<GPS_DataModel> data = d.SelectAll(this, SQL);
           for(GPS_DataModel item : data){
             txtIDNo.setText(item.getIDNo());
             txtVillID.setText(item.getVillID());
             String[] d_rdogrpGPSType = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpGPSType.length; i++)
             {
                 if (String.valueOf(item.getGPSType()).equals(String.valueOf(d_rdogrpGPSType[i])))
                 {
                     rb = (RadioButton)rdogrpGPSType.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtCompoundID.setText(item.getCompoundID());
             txtLMName.setText(item.getLMName());
             spnLMType.setSelection(Global.SpinnerItemPositionAnyLength_Index1(spnLMType, String.valueOf(item.getLMType())));
             txtLMTypeOth.setText(item.getLMTypeOth());
             txtLMSl.setText(item.getLMSl());
             txtLatitude.setText(item.getLatitude());
             txtLongitude.setText(item.getLongitude());
             txtAltitude.setText(item.getAltitude());
             txtAccuracy.setText(item.getAccuracy());
             txtSatelites.setText(item.getSatelites());
             String[] d_rdogrpGPSStatus = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpGPSStatus.length; i++)
             {
                 if (String.valueOf(item.getGPSStatus()).equals(String.valueOf(d_rdogrpGPSStatus[i])))
                 {
                     rb = (RadioButton)rdogrpGPSStatus.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
               spnGPSStatusReason.setSelection(Global.SpinnerItemPositionAnyLength(spnGPSStatusReason, String.valueOf(item.getGPSStatusReason())));
             txtGPSNote.setText(item.getGPSNote());
             txtRnd.setText(String.valueOf(item.getRnd()));
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(GPS.this, e.getMessage());
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

 public void GPSTypeSkipRule(String gpstype){

     if(gpstype.equalsIgnoreCase("1"))
     {
         secCompoundID.setVisibility(View.GONE);
         lineCompoundID.setVisibility(View.GONE);
         txtCompoundID.setText("");
         secLMName.setVisibility(View.VISIBLE);
         lineLMName.setVisibility(View.VISIBLE);
         secLMType.setVisibility(View.VISIBLE);
         lineLMType.setVisibility(View.VISIBLE);
//         secLMSl.setVisibility(View.VISIBLE);
//         lineLMSl.setVisibility(View.VISIBLE);
     }
     else
     {
         secCompoundID.setVisibility(View.GONE);
         secCompoundCode.setVisibility(View.VISIBLE);
         lineCompoundID.setVisibility(View.GONE);
         secLMName.setVisibility(View.GONE);
         lineLMName.setVisibility(View.GONE);
         txtLMName.setText("");
         secLMType.setVisibility(View.GONE);
         lineLMType.setVisibility(View.GONE);
         spnLMType.setSelection(0);
         secLMTypeOth.setVisibility(View.GONE);
         lineLMTypeOth.setVisibility(View.GONE);
         txtLMTypeOth.setText("");
//         secLMSl.setVisibility(View.GONE);
//         lineLMSl.setVisibility(View.GONE);
//         txtLMSl.setText("");
     }
 }

     protected boolean isBetterLocation(Location location, Location currentBestLocation) {
         if (currentBestLocation == null) {
             // A new location is always better than no location
             return true;
         }

         // Check whether the new location fix is newer or older
         long timeDelta = location.getTime() - currentBestLocation.getTime();
         boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
         boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
         boolean isNewer = timeDelta > 0;

         // If it's been more than two minutes since the current location, use the new location
         // because the user has likely moved
         if (isSignificantlyNewer) {
             return true;
             // If the new location is more than two minutes older, it must be worse
         } else if (isSignificantlyOlder) {
             return false;
         }

         // Check whether the new location fix is more or less accurate
         int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
         boolean isLessAccurate = accuracyDelta > 0;
         boolean isMoreAccurate = accuracyDelta < 0;
         boolean isSignificantlyLessAccurate = accuracyDelta > 200;

         // Check if the old and new location are from the same provider
         boolean isFromSameProvider = isSameProvider(location.getProvider(),
                 currentBestLocation.getProvider());

         // Determine location quality using a combination of timeliness and accuracy
         if (isMoreAccurate) {
             return true;
         } else if (isNewer && !isLessAccurate) {
             return true;
         } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
             return true;
         }
         return false;
     }

     /** Checks whether two providers are the same */
     private boolean isSameProvider(String provider1, String provider2) {
         if (provider1 == null) {
             return provider2 == null;
         }
         return provider1.equals(provider2);
     }

     public class GPSLocationListener implements LocationListener {
         public void onLocationChanged(final Location loc) {
             Log.i("**********", "Location changed");
             if (isBetterLocation(loc, previousBestLocation)) {
                 if(GPS_SCAN_START) {
                     txtLatitude.setText(String.valueOf(loc.getLatitude()));
                     txtLongitude.setText(String.valueOf(loc.getLongitude()));
                     txtAltitude.setText(String.valueOf(loc.getAltitude()));
                     txtAccuracy.setText(String.valueOf(loc.getAccuracy()));
                     txtSatelites.setText(String.valueOf(loc.getExtras().getInt("satellites", 0)));
                 }
                 //Toast.makeText(getApplicationContext(), "Latitude" + loc.getLatitude() + "\nLongitude" + loc.getLongitude(), Toast.LENGTH_SHORT).show();
                 intent.putExtra("Latitude", loc.getLatitude());
                 intent.putExtra("Longitude", loc.getLongitude());
                 intent.putExtra("Provider", loc.getProvider());
                 sendBroadcast(intent);
             }

         }

         public void onProviderDisabled(String provider) {
             Toast.makeText(getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT).show();
         }

         public void onProviderEnabled(String provider) {
             Toast.makeText(getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
         }

         public void onStatusChanged(String provider, int status, Bundle extras) {
             Toast.makeText(getApplicationContext(), "Status Changed", Toast.LENGTH_SHORT).show();
         }
     }
}