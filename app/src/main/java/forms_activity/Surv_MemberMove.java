
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
 import android.widget.Button;
 import android.widget.DatePicker;
 import android.widget.EditText;
 import android.widget.ImageButton;
 import android.widget.LinearLayout;
 import android.widget.RadioButton;
 import android.widget.SimpleAdapter;
 import android.widget.TextView;
 import android.widget.TimePicker;
 import android.graphics.Color;
 import android.view.WindowManager;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.R;

 import forms_datamodel.MemberMove_DataModel;
 import Utility.*;
 import Common.*;

 public class Surv_MemberMove extends AppCompatActivity {
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
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secActive;
    View lineActive;
    TextView VlblActive;
    EditText txtActive;
    LinearLayout secDSSID;
    View lineDSSID;
    TextView VlblDSSID;
    EditText txtDSSID;
    LinearLayout secMEnType;
    View lineMEnType;
    TextView VlblMEnType;
    EditText txtMEnType;
    LinearLayout secMEnDate;
    View lineMEnDate;
    TextView VlblMEnDate;
    EditText dtpMEnDate;
    LinearLayout secMExType;
    View lineMExType;
    TextView VlblMExType;
    EditText txtMExType;
    LinearLayout secMExDate;
    View lineMExDate;
    TextView VlblMExDate;
    EditText dtpMExDate;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secMemNote;
    View lineMemNote;
    TextView VlblMemNote;
    EditText txtMemNote;

     int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String MEMID = "";
     String HHID = "";
     String ACTIVE = "";

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
         MEMID = IDbundle.getString("MemID");
         HHID = IDbundle.getString("HHID");
         ACTIVE = IDbundle.getString("Active");

         TableName = "tmpMemberMove";
         MODULEID = 11;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Surv_MemberMove.this);
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
        Connection.LocalizeLanguage(Surv_MemberMove.this, MODULEID, LANGUAGEID);


         dtpMEnDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnMEnDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpMExDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnMExDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables


        DataSearch(MEMID,HHID,ACTIVE);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_MemberMove.this, e.getMessage());
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
         secHHID=(LinearLayout)findViewById(R.id.secHHID);
         lineHHID=(View)findViewById(R.id.lineHHID);
         VlblHHID=(TextView) findViewById(R.id.VlblHHID);
         txtHHID=(EditText) findViewById(R.id.txtHHID);
         txtHHID.setText(HHID);
         txtHHID.setEnabled(false);
         secActive=(LinearLayout)findViewById(R.id.secActive);
         lineActive=(View)findViewById(R.id.lineActive);
         VlblActive=(TextView) findViewById(R.id.VlblActive);
         txtActive=(EditText) findViewById(R.id.txtActive);
         txtActive.setText(ACTIVE);
         txtActive.setEnabled(false);
         secDSSID=(LinearLayout)findViewById(R.id.secDSSID);
         lineDSSID=(View)findViewById(R.id.lineDSSID);
         VlblDSSID=(TextView) findViewById(R.id.VlblDSSID);
         txtDSSID=(EditText) findViewById(R.id.txtDSSID);
         secMEnType=(LinearLayout)findViewById(R.id.secMEnType);
         lineMEnType=(View)findViewById(R.id.lineMEnType);
         VlblMEnType=(TextView) findViewById(R.id.VlblMEnType);
         txtMEnType=(EditText) findViewById(R.id.txtMEnType);
         secMEnDate=(LinearLayout)findViewById(R.id.secMEnDate);
         lineMEnDate=(View)findViewById(R.id.lineMEnDate);
         VlblMEnDate=(TextView) findViewById(R.id.VlblMEnDate);
         dtpMEnDate=(EditText) findViewById(R.id.dtpMEnDate);
         secMExType=(LinearLayout)findViewById(R.id.secMExType);
         lineMExType=(View)findViewById(R.id.lineMExType);
         VlblMExType=(TextView) findViewById(R.id.VlblMExType);
         txtMExType=(EditText) findViewById(R.id.txtMExType);
         secMExDate=(LinearLayout)findViewById(R.id.secMExDate);
         lineMExDate=(View)findViewById(R.id.lineMExDate);
         VlblMExDate=(TextView) findViewById(R.id.VlblMExDate);
         dtpMExDate=(EditText) findViewById(R.id.dtpMExDate);
         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         secMemNote=(LinearLayout)findViewById(R.id.secMemNote);
         lineMemNote=(View)findViewById(R.id.lineMemNote);
         VlblMemNote=(TextView) findViewById(R.id.VlblMemNote);
         txtMemNote=(EditText) findViewById(R.id.txtMemNote);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_MemberMove.this, e.getMessage());
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
         	Connection.MessageBox(Surv_MemberMove.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         MemberMove_DataModel objSave = new MemberMove_DataModel();
         objSave.setMemID(txtMemID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setActive(txtActive.getText().toString());
         objSave.setDSSID(txtDSSID.getText().toString());
         objSave.setMEnType(txtMEnType.getText().toString());
         objSave.setMEnDate(dtpMEnDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpMEnDate.getText().toString()) : dtpMEnDate.getText().toString());
         objSave.setMExType(txtMExType.getText().toString());
         objSave.setMExDate(dtpMExDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpMExDate.getText().toString()) : dtpMExDate.getText().toString());
         objSave.setRnd(txtRnd.getText().toString());
         objSave.setMemNote(txtMemNote.getText().toString());
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

             Toast.makeText(getApplicationContext(), "Save Successfully...", Toast.LENGTH_SHORT).show();
         }
         else{
             Connection.MessageBox(Surv_MemberMove.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_MemberMove.this, e.getMessage());
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
             ValidationMsg += "\nRequired field: Member ID.";
             secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
           {
             ValidationMsg += "\nRequired field: Household ID.";
             secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtActive.getText().toString().length()==0 & secActive.isShown())
           {
             ValidationMsg += "\nRequired field: Is the member extension active.";
             secActive.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDSSID.getText().toString().length()==0 & secDSSID.isShown())
           {
             ValidationMsg += "\nRequired field: Permanent Member DSS ID.";
             secDSSID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMEnType.getText().toString().length()==0 & secMEnType.isShown())
           {
             ValidationMsg += "\nRequired field: Member Entry Type.";
             secMEnType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpMEnDate.getText().toString());
         if(DV.length()!=0 & secMEnDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Member Entry Date.";
             secMEnDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMExType.getText().toString().length()==0 & secMExType.isShown())
           {
             ValidationMsg += "\nRequired field: Member Exit Type.";
             secMExType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpMExDate.getText().toString());
         if(DV.length()!=0 & secMExDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Member Exit Date.";
             secMExDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             ValidationMsg += "\nRequired field: Round.";
             secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMemNote.getText().toString().length()==0 & secMemNote.isShown())
           {
             ValidationMsg += "\nRequired field: Member Note.";
             secMemNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secActive.setBackgroundColor(Color.WHITE);
             secDSSID.setBackgroundColor(Color.WHITE);
             secMEnType.setBackgroundColor(Color.WHITE);
             secMEnDate.setBackgroundColor(Color.WHITE);
             secMExType.setBackgroundColor(Color.WHITE);
             secMExDate.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
             secMemNote.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String MemID, String HHID, String Active)
     {
       try
        {     
           RadioButton rb;
           MemberMove_DataModel d = new MemberMove_DataModel();
           String SQL = "Select * from "+ TableName +"  Where MemID='"+ MemID +"' and HHID='"+ HHID +"' and Active='"+ Active +"'";
           List<MemberMove_DataModel> data = d.SelectAll(this, SQL);
           for(MemberMove_DataModel item : data){
             txtMemID.setText(item.getMemID());
             txtHHID.setText(item.getHHID());
             txtActive.setText(item.getActive());
             txtDSSID.setText(item.getDSSID());
             txtMEnType.setText(item.getMEnType());
             dtpMEnDate.setText(item.getMEnDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getMEnDate()));
             txtMExType.setText(item.getMExType());
             dtpMExDate.setText(item.getMExDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getMExDate()));
             txtRnd.setText(item.getRnd());
             txtMemNote.setText(item.getMemNote());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_MemberMove.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpMEnDate);
      if (VariableID.equals("btnMEnDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpMEnDate);
      }
      else if (VariableID.equals("btnMExDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpMExDate);
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