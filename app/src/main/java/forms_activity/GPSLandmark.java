
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

 import forms_datamodel.GPSLandmark_DataModel;
 import Utility.*;
 import Common.*;

 public class GPSLandmark extends AppCompatActivity {
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
    LinearLayout secVillID;
    View lineVillID;
    TextView VlblVillID;
    EditText txtVillID;
    LinearLayout secLMNo;
    View lineLMNo;
    TextView VlblLMNo;
    EditText txtLMNo;
    LinearLayout secLocalityName;
    View lineLocalityName;
    TextView VlblLocalityName;
    EditText txtLocalityName;
    LinearLayout secLMName;
    View lineLMName;
    TextView VlblLMName;
    EditText txtLMName;
    LinearLayout secLatitude;
    View lineLatitude;
    TextView VlblLatitude;
    EditText txtLatitude;
    LinearLayout secLongitude;
    View lineLongitude;
    TextView VlblLongitude;
    EditText txtLongitude;

    static int MODULEID   = 0;
    static int LANGUAGEID = 0;
    static String TableName;

    static String STARTTIME = "";
    static String DEVICEID  = "";
    static String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
    static String VILLID = "";
    static String LMNO = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.gpslandmark);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         VILLID = IDbundle.getString("VillID");
         LMNO = IDbundle.getString("LMNO");

         TableName = "GPSLandmark";
         MODULEID = 13;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(GPSLandmark.this);
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
        Connection.LocalizeLanguage(GPSLandmark.this, MODULEID, LANGUAGEID);





         //Hide all skip variables


        DataSearch(VILLID,LMNO);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(GPSLandmark.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         secVillID=(LinearLayout)findViewById(R.id.secVillID);
         lineVillID=(View)findViewById(R.id.lineVillID);
         VlblVillID=(TextView) findViewById(R.id.VlblVillID);
         txtVillID=(EditText) findViewById(R.id.txtVillID);
         txtVillID.setText(VILLID);
         txtVillID.setEnabled(false);
         secLMNo=(LinearLayout)findViewById(R.id.secLMNo);
         lineLMNo=(View)findViewById(R.id.lineLMNo);
         VlblLMNo=(TextView) findViewById(R.id.VlblLMNo);
         txtLMNo=(EditText) findViewById(R.id.txtLMNo);

         if(LMNO.length()==0)
             txtLMNo.setText(LMSerial(VILLID));
         else
             txtLMNo.setText(LMNO);
         txtLMNo.setEnabled(false);

         secLocalityName=(LinearLayout)findViewById(R.id.secLocalityName);
         lineLocalityName=(View)findViewById(R.id.lineLocalityName);
         VlblLocalityName=(TextView) findViewById(R.id.VlblLocalityName);
         txtLocalityName=(EditText) findViewById(R.id.txtLocalityName);
         secLMName=(LinearLayout)findViewById(R.id.secLMName);
         lineLMName=(View)findViewById(R.id.lineLMName);
         VlblLMName=(TextView) findViewById(R.id.VlblLMName);
         txtLMName=(EditText) findViewById(R.id.txtLMName);
         secLatitude=(LinearLayout)findViewById(R.id.secLatitude);
         lineLatitude=(View)findViewById(R.id.lineLatitude);
         VlblLatitude=(TextView) findViewById(R.id.VlblLatitude);
         txtLatitude=(EditText) findViewById(R.id.txtLatitude);
         secLongitude=(LinearLayout)findViewById(R.id.secLongitude);
         lineLongitude=(View)findViewById(R.id.lineLongitude);
         VlblLongitude=(TextView) findViewById(R.id.VlblLongitude);
         txtLongitude=(EditText) findViewById(R.id.txtLongitude);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(GPSLandmark.this, e.getMessage());
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
         	Connection.MessageBox(GPSLandmark.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         GPSLandmark_DataModel objSave = new GPSLandmark_DataModel();
         objSave.setVillID(txtVillID.getText().toString());
         objSave.setLMNo(txtLMNo.getText().toString());
         objSave.setLocalityName(txtLocalityName.getText().toString());
         objSave.setLMName(txtLMName.getText().toString());
         objSave.setLatitude(txtLatitude.getText().toString());
         objSave.setLongitude(txtLongitude.getText().toString());
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

             Connection.MessageBox(GPSLandmark.this, "Saved Successfully");
             finish();
         }
         else{
             Connection.MessageBox(GPSLandmark.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(GPSLandmark.this, e.getMessage());
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
         if(txtVillID.getText().toString().length()==0 & secVillID.isShown())
           {
             ValidationMsg += "\nRequired field: Internal village ID.";
             secVillID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtLMNo.getText().toString().length()==0 & secLMNo.isShown())
           {
             ValidationMsg += "\nRequired field: Landmark No.";
             secLMNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtLocalityName.getText().toString().length()==0 & secLocalityName.isShown())
           {
             ValidationMsg += "\nRequired field: Locality or Neighborhood Name.";
             secLocalityName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtLMName.getText().toString().length()==0 & secLMName.isShown())
           {
             ValidationMsg += "\nRequired field: Landmark Name.";
             secLMName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtLatitude.getText().toString().length()==0 & secLatitude.isShown())
           {
             ValidationMsg += "\nRequired field: Latitude.";
             secLatitude.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtLongitude.getText().toString().length()==0 & secLongitude.isShown())
           {
             ValidationMsg += "\nRequired field: Longitude.";
             secLongitude.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secVillID.setBackgroundColor(Color.WHITE);
             secLMNo.setBackgroundColor(Color.WHITE);
             secLocalityName.setBackgroundColor(Color.WHITE);
             secLMName.setBackgroundColor(Color.WHITE);
             secLatitude.setBackgroundColor(Color.WHITE);
             secLongitude.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String VillID, String LMNo)
     {
       try
        {     
           RadioButton rb;
           GPSLandmark_DataModel d = new GPSLandmark_DataModel();
           String SQL = "Select * from "+ TableName +"  Where VillID='"+ VillID +"' and LMNo='"+ LMNo +"'";
           List<GPSLandmark_DataModel> data = d.SelectAll(this, SQL);
           for(GPSLandmark_DataModel item : data){
             txtVillID.setText(item.getVillID());
             txtLMNo.setText(item.getLMNo());
             txtLocalityName.setText(item.getLocalityName());
             txtLMName.setText(item.getLMName());
             txtLatitude.setText(item.getLatitude());
             txtLongitude.setText(item.getLongitude());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(GPSLandmark.this, e.getMessage());
            return;
        }
     }

     private String LMSerial(String VillID)
     {
         String SL = C.ReturnSingleValue("Select (ifnull(max(cast(LMNo as int)),0)+1)SL from GPSLandmark where VillID='"+ VillID +"'"); //where ParticipantID='"+ ParticipantID +"'");
         return Global.Right("00"+SL,2);
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