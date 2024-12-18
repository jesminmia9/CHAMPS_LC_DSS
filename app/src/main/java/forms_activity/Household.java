
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

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.R;

 import Utility.*;
 import Common.*;
 import forms_datamodel.tmpHousehold_DataModel;

 public class Household extends AppCompatActivity {
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
    LinearLayout secHHNO;
    View lineHHNO;
    TextView VlblHHNO;
    EditText txtHHNO;
    LinearLayout secVillID;
    View lineVillID;
    TextView VlblVillID;
    EditText txtVillID;
    LinearLayout secCompoundID;
    View lineCompoundID;
    TextView VlblCompoundID;
    EditText txtCompoundID;
    LinearLayout secHHHead;
    View lineHHHead;
    TextView VlblHHHead;
    EditText txtHHHead;
    LinearLayout secMobileNo1;
    View lineMobileNo1;
    TextView VlblMobileNo1;
    EditText txtMobileNo1;
    LinearLayout secMobileNo2;
    View lineMobileNo2;
    TextView VlblMobileNo2;
    EditText txtMobileNo2;
    LinearLayout secTotMem;
    View lineTotMem;
    TextView VlblTotMem;
    EditText txtTotMem;
    LinearLayout secHHEnType;
    View lineHHEnType;
    TextView VlblHHEnType;
    EditText txtHHEnType;
    LinearLayout secHHEnDate;
    View lineHHEnDate;
    TextView VlblHHEnDate;
    EditText dtpHHEnDate;
    LinearLayout secHHExType;
    View lineHHExType;
    TextView VlblHHExType;
    EditText txtHHExType;
    LinearLayout secHHExDate;
    View lineHHExDate;
    TextView VlblHHExDate;
    EditText dtpHHExDate;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secActive;
    View lineActive;
    TextView VlblActive;
    EditText txtActive;
    LinearLayout secHHNote;
    View lineHHNote;
    TextView VlblHHNote;
    EditText txtHHNote;

    LinearLayout secHHNote1;
    View lineHHNote1;
    TextView VlblHHNote1;


    static int MODULEID   = 0;
    static int LANGUAGEID = 0;
    static String TableName;

    static String STARTTIME = "";
    static String DEVICEID  = "";
    static String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     static String HHID = "";
     static String CompoundID = "";
     static String VillID = "";
     static String HHNO = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.household);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         HHID = IDbundle.getString("HHID");
         CompoundID = IDbundle.getString("CompoundID");
         VillID = IDbundle.getString("VillID");
         HHNO = IDbundle.getString("HHNO");

         TableName = "Household";
         MODULEID = 7;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Household.this);
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
        Connection.LocalizeLanguage(Household.this, MODULEID, LANGUAGEID);


         dtpHHEnDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnHHEnDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpHHExDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnHHExDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables


        DataSearch(VillID,CompoundID,HHNO);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Household.this, e.getMessage());
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
         secHHNO=(LinearLayout)findViewById(R.id.secHHNO);
         lineHHNO=(View)findViewById(R.id.lineHHNO);
         VlblHHNO=(TextView) findViewById(R.id.VlblHHNO);
         txtHHNO=(EditText) findViewById(R.id.txtHHNO);

         if(HHNO.length()==0)
             txtHHNO.setText(HHSerial(VillID,CompoundID));
         else
             txtHHNO.setText(HHNO);
         txtHHNO.setEnabled(false);

         secVillID=(LinearLayout)findViewById(R.id.secVillID);
         lineVillID=(View)findViewById(R.id.lineVillID);
         VlblVillID=(TextView) findViewById(R.id.VlblVillID);
         txtVillID=(EditText) findViewById(R.id.txtVillID);
         txtVillID.setText(VillID);
         txtVillID.setEnabled(false);
         secCompoundID=(LinearLayout)findViewById(R.id.secCompoundID);
         lineCompoundID=(View)findViewById(R.id.lineCompoundID);
         VlblCompoundID=(TextView) findViewById(R.id.VlblCompoundID);
         txtCompoundID=(EditText) findViewById(R.id.txtCompoundID);
         txtCompoundID.setText(CompoundID);
         txtCompoundID.setEnabled(false);
         secHHHead=(LinearLayout)findViewById(R.id.secHHHead);
         lineHHHead=(View)findViewById(R.id.lineHHHead);
         VlblHHHead=(TextView) findViewById(R.id.VlblHHHead);
         txtHHHead=(EditText) findViewById(R.id.txtHHHead);
         secMobileNo1=(LinearLayout)findViewById(R.id.secMobileNo1);
         lineMobileNo1=(View)findViewById(R.id.lineMobileNo1);
         VlblMobileNo1=(TextView) findViewById(R.id.VlblMobileNo1);
         txtMobileNo1=(EditText) findViewById(R.id.txtMobileNo1);
         secMobileNo2=(LinearLayout)findViewById(R.id.secMobileNo2);
         lineMobileNo2=(View)findViewById(R.id.lineMobileNo2);
         VlblMobileNo2=(TextView) findViewById(R.id.VlblMobileNo2);
         txtMobileNo2=(EditText) findViewById(R.id.txtMobileNo2);
         secTotMem=(LinearLayout)findViewById(R.id.secTotMem);
         lineTotMem=(View)findViewById(R.id.lineTotMem);
         VlblTotMem=(TextView) findViewById(R.id.VlblTotMem);
         txtTotMem=(EditText) findViewById(R.id.txtTotMem);
         secHHEnType=(LinearLayout)findViewById(R.id.secHHEnType);
         lineHHEnType=(View)findViewById(R.id.lineHHEnType);
         VlblHHEnType=(TextView) findViewById(R.id.VlblHHEnType);
         txtHHEnType=(EditText) findViewById(R.id.txtHHEnType);
         secHHEnDate=(LinearLayout)findViewById(R.id.secHHEnDate);
         lineHHEnDate=(View)findViewById(R.id.lineHHEnDate);
         VlblHHEnDate=(TextView) findViewById(R.id.VlblHHEnDate);
         dtpHHEnDate=(EditText) findViewById(R.id.dtpHHEnDate);
         secHHExType=(LinearLayout)findViewById(R.id.secHHExType);
         lineHHExType=(View)findViewById(R.id.lineHHExType);
         VlblHHExType=(TextView) findViewById(R.id.VlblHHExType);
         txtHHExType=(EditText) findViewById(R.id.txtHHExType);
         secHHExDate=(LinearLayout)findViewById(R.id.secHHExDate);
         lineHHExDate=(View)findViewById(R.id.lineHHExDate);
         VlblHHExDate=(TextView) findViewById(R.id.VlblHHExDate);
         dtpHHExDate=(EditText) findViewById(R.id.dtpHHExDate);
         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         secActive=(LinearLayout)findViewById(R.id.secActive);
         lineActive=(View)findViewById(R.id.lineActive);
         VlblActive=(TextView) findViewById(R.id.VlblActive);
         txtActive=(EditText) findViewById(R.id.txtActive);
         secHHNote=(LinearLayout)findViewById(R.id.secHHNote);
         lineHHNote=(View)findViewById(R.id.lineHHNote);
         VlblHHNote=(TextView) findViewById(R.id.VlblHHNote);
         txtHHNote=(EditText) findViewById(R.id.txtHHNote);

         secHHNote1=(LinearLayout)findViewById(R.id.secHHNote1);
         lineHHNote1=(View)findViewById(R.id.lineHHNote1);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Household.this, e.getMessage());
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
         	Connection.MessageBox(Household.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         tmpHousehold_DataModel objSave = new tmpHousehold_DataModel();
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setHHNO(txtHHNO.getText().toString());
         objSave.setVillID(txtVillID.getText().toString());
         objSave.setCompoundID(txtCompoundID.getText().toString());
         objSave.setHHHead(txtHHHead.getText().toString());
         objSave.setMobileNo1(txtMobileNo1.getText().toString());
         objSave.setMobileNo2(txtMobileNo2.getText().toString());
         objSave.setTotMem(txtTotMem.getText().toString());
         objSave.setHHEnType(txtHHEnType.getText().toString());
         objSave.setHHEnDate(dtpHHEnDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpHHEnDate.getText().toString()) : dtpHHEnDate.getText().toString());
         objSave.setHHExType(txtHHExType.getText().toString());
         objSave.setHHExDate(dtpHHExDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpHHExDate.getText().toString()) : dtpHHExDate.getText().toString());
         objSave.setRnd(txtRnd.getText().toString());
         objSave.setActive(txtActive.getText().toString());
         objSave.setHHNote(txtHHNote.getText().toString());
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

             Connection.MessageBox(Household.this, "Saved Successfully");
             finish();
         }
         else{
             Connection.MessageBox(Household.this, status);
             return;
         }

     }
     catch(Exception  e)
     {
         Connection.MessageBox(Household.this, e.getMessage());
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
         if(txtHHNO.getText().toString().length()==0 & secHHNO.isShown())
           {
             ValidationMsg += "\nRequired field: Household Serial Number.";
             secHHNO.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtVillID.getText().toString().length()==0 & secVillID.isShown())
           {
             ValidationMsg += "\nRequired field: Village ID.";
             secVillID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCompoundID.getText().toString().length()==0 & secCompoundID.isShown())
           {
             ValidationMsg += "\nRequired field: Compound/bounded structure ID.";
             secCompoundID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHHead.getText().toString().length()==0 & secHHHead.isShown())
           {
             ValidationMsg += "\nRequired field: Name of household head (HH).";
             secHHHead.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         /*if(txtMobileNo1.getText().toString().length()==0 & secMobileNo1.isShown())
           {
             ValidationMsg += "\nRequired field: 1st Mobile No.";
             secMobileNo1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/
         /*if(txtMobileNo2.getText().toString().length()==0 & secMobileNo2.isShown())
           {
             ValidationMsg += "\nRequired field: 2nd Mobile No.";
             secMobileNo2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/
         if(txtMobileNo1.getText().toString().equals(txtMobileNo2.getText().toString()))
         {
             ValidationMsg += "\nRequired field: 2nd Mobile No. can't be the same as 1st Mobile No.";
             secMobileNo2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtTotMem.getText().toString().length()==0 & secTotMem.isShown())
           {
             ValidationMsg += "\nRequired field: Total Member.";
             secTotMem.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHEnType.getText().toString().length()==0 & secHHEnType.isShown())
           {
             ValidationMsg += "\nRequired field: Household Entry Type.";
             secHHEnType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpHHEnDate.getText().toString());
         if(DV.length()!=0 & secHHEnDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Household Entry Date.";
             secHHEnDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHExType.getText().toString().length()==0 & secHHExType.isShown())
           {
             ValidationMsg += "\nRequired field: Household Exit Type.";
             secHHExType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpHHExDate.getText().toString());
         if(DV.length()!=0 & secHHExDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Household Exit Date.";
             secHHExDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             ValidationMsg += "\nRequired field: Roud No.";
             secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtActive.getText().toString().length()==0 & secActive.isShown())
           {
             ValidationMsg += "\nRequired field: Active.";
             secActive.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secActive.isShown() & (Integer.valueOf(txtActive.getText().toString().length()==0 ? "1" : txtActive.getText().toString()) < 1 || Integer.valueOf(txtActive.getText().toString().length()==0 ? "2" : txtActive.getText().toString()) > 2))
           {
             ValidationMsg += "\nValue should be between 1 and 2(Active).";
             secActive.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
/*         if(txtHHNote.getText().toString().length()==0 & secHHNote.isShown())
           {
             ValidationMsg += "\nRequired field: Household Note.";
             secHHNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secHHID.setBackgroundColor(Color.WHITE);
             secHHNO.setBackgroundColor(Color.WHITE);
             secVillID.setBackgroundColor(Color.WHITE);
             secCompoundID.setBackgroundColor(Color.WHITE);
             secHHHead.setBackgroundColor(Color.WHITE);
             secMobileNo1.setBackgroundColor(Color.WHITE);
             secMobileNo2.setBackgroundColor(Color.WHITE);
             secTotMem.setBackgroundColor(Color.WHITE);
             secHHEnType.setBackgroundColor(Color.WHITE);
             secHHEnDate.setBackgroundColor(Color.WHITE);
             secHHExType.setBackgroundColor(Color.WHITE);
             secHHExDate.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
             secActive.setBackgroundColor(Color.WHITE);
             secActive.setBackgroundColor(Color.WHITE);
             secHHNote.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String VillID,String CompoundID,String HHNO)
     {
       try
        {     
           RadioButton rb;
           tmpHousehold_DataModel d = new tmpHousehold_DataModel();
           String SQL = "Select * from "+ TableName +"  Where VillID='"+ VillID +"' and CompoundID='"+CompoundID+"' and HHNO='"+HHNO+"'";
           List<tmpHousehold_DataModel> data = d.SelectAll(this, SQL);
           for(tmpHousehold_DataModel item : data){
             txtHHID.setText(item.getHHID());
             txtHHNO.setText(item.getHHNO());
             txtVillID.setText(item.getVillID());
             txtCompoundID.setText(item.getCompoundID());
             txtHHHead.setText(item.getHHHead());
             txtMobileNo1.setText(item.getMobileNo1());
             txtMobileNo2.setText(item.getMobileNo2());
             txtTotMem.setText(item.getTotMem());
             txtHHEnType.setText(item.getHHEnType());
             dtpHHEnDate.setText(item.getHHEnDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getHHEnDate()));
             txtHHExType.setText(item.getHHExType());
             dtpHHExDate.setText(item.getHHExDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getHHExDate()));
             txtRnd.setText(item.getRnd());
             txtActive.setText(String.valueOf(item.getActive()));
             txtHHNote.setText(item.getHHNote());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Household.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpHHEnDate);
      if (VariableID.equals("btnHHEnDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpHHEnDate);
      }
      else if (VariableID.equals("btnHHExDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpHHExDate);
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

     private String HHSerial(String VillID,String CompoundID)
     {
         String SL = C.ReturnSingleValue("Select (ifnull(max(cast(HHNO as int)),0)+1)SL from Household where VillID='"+ VillID +"' and CompoundID='"+CompoundID+"'"); //where ParticipantID='"+ ParticipantID +"'");
         return Global.Right("00"+SL,2);
     }
 
 // turning off the GPS if its in on state. to avoid the battery drain.
 @Override
 protected void onDestroy() {
     // TODO Auto-generated method stub
     super.onDestroy();
 }
}