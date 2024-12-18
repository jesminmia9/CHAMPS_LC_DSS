
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
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.R;

 import forms_datamodel.GPSCompound_DataModel;
 import Utility.*;
 import Common.*;

 public class GPSCompound extends AppCompatActivity {
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
    LinearLayout secCompoundID;
    View lineCompoundID;
    TextView VlblCompoundID;
    EditText txtCompoundID;
    LinearLayout secLocalityName;
    View lineLocalityName;
    TextView VlblLocalityName;
    EditText txtLocalityName;
    LinearLayout secCompoundName;
    View lineCompoundName;
    TextView VlblCompoundName;
    EditText txtCompoundName;
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
    static String CompoundID = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.gpscompound);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         VILLID = IDbundle.getString("VillID");
         CompoundID = IDbundle.getString("CompoundID");

         TableName = "GPSCompound";
         MODULEID = 12;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(GPSCompound.this);
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
        Connection.LocalizeLanguage(GPSCompound.this, MODULEID, LANGUAGEID);





         //Hide all skip variables


        DataSearch(VILLID,CompoundID);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(GPSCompound.this, e.getMessage());
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
//         txtVillID.setEnabled(false);
         secCompoundID=(LinearLayout)findViewById(R.id.secCompoundID);
         lineCompoundID=(View)findViewById(R.id.lineCompoundID);
         VlblCompoundID=(TextView) findViewById(R.id.VlblCompoundID);
         txtCompoundID=(EditText) findViewById(R.id.txtCompoundID);
         txtCompoundID.setText(CompoundID);
//         txtCompoundID.setEnabled(false);
         secLocalityName=(LinearLayout)findViewById(R.id.secLocalityName);
         lineLocalityName=(View)findViewById(R.id.lineLocalityName);
         VlblLocalityName=(TextView) findViewById(R.id.VlblLocalityName);
         txtLocalityName=(EditText) findViewById(R.id.txtLocalityName);
         secCompoundName=(LinearLayout)findViewById(R.id.secCompoundName);
         lineCompoundName=(View)findViewById(R.id.lineCompoundName);
         VlblCompoundName=(TextView) findViewById(R.id.VlblCompoundName);
         txtCompoundName=(EditText) findViewById(R.id.txtCompoundName);
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
         Connection.MessageBox(GPSCompound.this, e.getMessage());
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
         	Connection.MessageBox(GPSCompound.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         GPSCompound_DataModel objSave = new GPSCompound_DataModel();
         objSave.setVillID(txtVillID.getText().toString());
         objSave.setCompoundID(txtCompoundID.getText().toString());
         objSave.setLocalityName(txtLocalityName.getText().toString());
         objSave.setCompoundName(txtCompoundName.getText().toString());
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

             Toast.makeText(GPSCompound.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
             finish();
         }
         else{
             Connection.MessageBox(GPSCompound.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(GPSCompound.this, e.getMessage());
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
         if(txtCompoundID.getText().toString().length()==0 & secCompoundID.isShown())
           {
             ValidationMsg += "\nRequired field: Compound Code.";
             secCompoundID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtLocalityName.getText().toString().length()==0 & secLocalityName.isShown())
           {
             ValidationMsg += "\nRequired field: Locality or Neighborhood Name.";
             secLocalityName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCompoundName.getText().toString().length()==0 & secCompoundName.isShown())
           {
             ValidationMsg += "\nRequired field: Compound Name.";
             secCompoundName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secCompoundID.setBackgroundColor(Color.WHITE);
             secLocalityName.setBackgroundColor(Color.WHITE);
             secCompoundName.setBackgroundColor(Color.WHITE);
             secLatitude.setBackgroundColor(Color.WHITE);
             secLongitude.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String VillID, String CompoundID)
     {
       try
        {     
           RadioButton rb;
           GPSCompound_DataModel d = new GPSCompound_DataModel();
           String SQL = "Select * from "+ TableName +"  Where VillID='"+ VillID +"' and CompoundID='"+ CompoundID +"'";
           List<GPSCompound_DataModel> data = d.SelectAll(this, SQL);
           for(GPSCompound_DataModel item : data){
             txtVillID.setText(item.getVillID());
             txtCompoundID.setText(item.getCompoundID());
             txtLocalityName.setText(item.getLocalityName());
             txtCompoundName.setText(item.getCompoundName());
             txtLatitude.setText(item.getLatitude());
             txtLongitude.setText(item.getLongitude());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(GPSCompound.this, e.getMessage());
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
}