
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

 import forms_datamodel.Compound_DataModel;
 import Utility.*;
 import Common.*;

 public class Compound extends AppCompatActivity {
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
    LinearLayout secCompoundID;
    View lineCompoundID;
    TextView VlblCompoundID;
    EditText txtCompoundID;
    LinearLayout secCompoundCode;
    View lineCompoundCode;
    TextView VlblCompoundCode;
    EditText txtCompoundCode;
    LinearLayout secCompoundName;
    View lineCompoundName;
    TextView VlblCompoundName;
    EditText txtCompoundName;
    LinearLayout secCompoundAdrs;
    View lineCompoundAdrs;
    TextView VlblCompoundAdrs;
    EditText txtCompoundAdrs;
    LinearLayout secVillID;
    View lineVillID;
    TextView VlblVillID;
    EditText txtVillID;
    LinearLayout secTotalHH;
    View lineTotalHH;
    TextView VlblTotalHH;
    EditText txtTotalHH;
    LinearLayout secActive;
    View lineActive;
    TextView VlblActive;
    EditText txtActive;
    LinearLayout secComEnDate;
    View lineComEnDate;
    TextView VlblComEnDate;
    EditText dtpComEnDate;
    LinearLayout secComExDate;
    View lineComExDate;
    TextView VlblComExDate;
    EditText dtpComExDate;
    LinearLayout secComExReason;
    View lineComExReason;
    TextView VlblComExReason;
    EditText txtComExReason;
    LinearLayout secCluster;
    View lineCluster;
    TextView VlblCluster;
    EditText txtCluster;
    LinearLayout secBlock;
    View lineBlock;
    TextView VlblBlock;
    EditText txtBlock;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;

    static int MODULEID   = 0;
    static int LANGUAGEID = 0;
    static String TableName;

    static String STARTTIME = "";
    static String DEVICEID  = "";
    static String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
    static String COMPOUNDID = "";
    static String COMPOUNDCODE = "";
    static String VILLID = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.compound);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         COMPOUNDID = IDbundle.getString("CompoundID");
         COMPOUNDCODE = IDbundle.getString("CompoundCode");
         VILLID = IDbundle.getString("VillID");

         TableName = "Compound";
         MODULEID = 5;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         //Label
         lblHeading.setText(ProjectSetting.COMPOUND_LABEL );

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Compound.this);
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
        Connection.LocalizeLanguage(Compound.this, MODULEID, LANGUAGEID);


         dtpComEnDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnComEnDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpComExDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnComExDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables


        DataSearch(COMPOUNDID);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Compound.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         secCompoundID=(LinearLayout)findViewById(R.id.secCompoundID);
         lineCompoundID=(View)findViewById(R.id.lineCompoundID);
         VlblCompoundID=(TextView) findViewById(R.id.VlblCompoundID);
         txtCompoundID=(EditText) findViewById(R.id.txtCompoundID);
         txtCompoundID.setText(COMPOUNDID);
         txtCompoundID.setEnabled(false);

         secVillID=(LinearLayout)findViewById(R.id.secVillID);
         lineVillID=(View)findViewById(R.id.lineVillID);
         VlblVillID=(TextView) findViewById(R.id.VlblVillID);
         txtVillID=(EditText) findViewById(R.id.txtVillID);
         txtVillID.setText(VILLID);
         txtVillID.setEnabled(false);

         secCompoundCode=(LinearLayout)findViewById(R.id.secCompoundCode);
         lineCompoundCode=(View)findViewById(R.id.lineCompoundCode);
         VlblCompoundCode=(TextView) findViewById(R.id.VlblCompoundCode);
         txtCompoundCode=(EditText) findViewById(R.id.txtCompoundCode);

         if(COMPOUNDCODE.length()==0)
             txtCompoundCode.setText(CompSerial(VILLID));
         else
             txtCompoundCode.setText(COMPOUNDCODE);
         txtCompoundCode.setEnabled(false);

         secCompoundName=(LinearLayout)findViewById(R.id.secCompoundName);
         lineCompoundName=(View)findViewById(R.id.lineCompoundName);
         VlblCompoundName=(TextView) findViewById(R.id.VlblCompoundName);
         txtCompoundName=(EditText) findViewById(R.id.txtCompoundName);
         secCompoundAdrs=(LinearLayout)findViewById(R.id.secCompoundAdrs);
         lineCompoundAdrs=(View)findViewById(R.id.lineCompoundAdrs);
         VlblCompoundAdrs=(TextView) findViewById(R.id.VlblCompoundAdrs);
         txtCompoundAdrs=(EditText) findViewById(R.id.txtCompoundAdrs);

         secTotalHH=(LinearLayout)findViewById(R.id.secTotalHH);
         lineTotalHH=(View)findViewById(R.id.lineTotalHH);
         VlblTotalHH=(TextView) findViewById(R.id.VlblTotalHH);
         txtTotalHH=(EditText) findViewById(R.id.txtTotalHH);
         secActive=(LinearLayout)findViewById(R.id.secActive);
         lineActive=(View)findViewById(R.id.lineActive);
         VlblActive=(TextView) findViewById(R.id.VlblActive);
         txtActive=(EditText) findViewById(R.id.txtActive);
         secComEnDate=(LinearLayout)findViewById(R.id.secComEnDate);
         lineComEnDate=(View)findViewById(R.id.lineComEnDate);
         VlblComEnDate=(TextView) findViewById(R.id.VlblComEnDate);
         dtpComEnDate=(EditText) findViewById(R.id.dtpComEnDate);
         secComExDate=(LinearLayout)findViewById(R.id.secComExDate);
         lineComExDate=(View)findViewById(R.id.lineComExDate);
         VlblComExDate=(TextView) findViewById(R.id.VlblComExDate);
         dtpComExDate=(EditText) findViewById(R.id.dtpComExDate);
         secComExReason=(LinearLayout)findViewById(R.id.secComExReason);
         lineComExReason=(View)findViewById(R.id.lineComExReason);
         VlblComExReason=(TextView) findViewById(R.id.VlblComExReason);
         txtComExReason=(EditText) findViewById(R.id.txtComExReason);
         secCluster=(LinearLayout)findViewById(R.id.secCluster);
         lineCluster=(View)findViewById(R.id.lineCluster);
         VlblCluster=(TextView) findViewById(R.id.VlblCluster);
         txtCluster=(EditText) findViewById(R.id.txtCluster);
         secBlock=(LinearLayout)findViewById(R.id.secBlock);
         lineBlock=(View)findViewById(R.id.lineBlock);
         VlblBlock=(TextView) findViewById(R.id.VlblBlock);
         txtBlock=(EditText) findViewById(R.id.txtBlock);
         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Compound.this, e.getMessage());
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
         	Connection.MessageBox(Compound.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         Compound_DataModel objSave = new Compound_DataModel();
         objSave.setCompoundID(txtCompoundID.getText().toString());
         objSave.setCompoundCode(txtCompoundCode.getText().toString());
         objSave.setCompoundName(txtCompoundName.getText().toString());
         objSave.setCompoundAdrs(txtCompoundAdrs.getText().toString());
         objSave.setVillID(txtVillID.getText().toString());
         objSave.setTotalHH(txtTotalHH.getText().toString());
         objSave.setActive(txtActive.getText().toString());
         objSave.setComEnDate(dtpComEnDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpComEnDate.getText().toString()) : dtpComEnDate.getText().toString());
         objSave.setComExDate(dtpComExDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpComExDate.getText().toString()) : dtpComExDate.getText().toString());
         objSave.setComExReason(txtComExReason.getText().toString());
         objSave.setCluster(txtCluster.getText().toString());
         objSave.setBlock(txtBlock.getText().toString());
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

             Connection.MessageBox(Compound.this, "Saved Successfully");
             finish();
         }
         else{
             Connection.MessageBox(Compound.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Compound.this, e.getMessage());
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
         if(txtCompoundID.getText().toString().length()==0 & secCompoundID.isShown())
           {
             ValidationMsg += "\nRequired field: Internal Compound ID.";
             secCompoundID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCompoundCode.getText().toString().length()==0 & secCompoundCode.isShown())
           {
             ValidationMsg += "\nRequired field: Compound Code.";
             secCompoundCode.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCompoundName.getText().toString().length()==0 & secCompoundName.isShown())
           {
             ValidationMsg += "\nRequired field: Compound Name.";
             secCompoundName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCompoundAdrs.getText().toString().length()==0 & secCompoundAdrs.isShown())
           {
             ValidationMsg += "\nRequired field: Compound Address.";
             secCompoundAdrs.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtVillID.getText().toString().length()==0 & secVillID.isShown())
           {
             ValidationMsg += "\nRequired field: village ID.";
             secVillID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtTotalHH.getText().toString().length()==0 & secTotalHH.isShown())
           {
             ValidationMsg += "\nRequired field: Total Household.";
             secTotalHH.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtActive.getText().toString().length()==0 & secActive.isShown())
           {
             ValidationMsg += "\nRequired field: Active.";
             secActive.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpComEnDate.getText().toString());
         if(DV.length()!=0 & secComEnDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Compound Enroll Date.";
             secComEnDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpComExDate.getText().toString());
         if(DV.length()!=0 & secComExDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Compound Exit Date.";
             secComExDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtComExReason.getText().toString().length()==0 & secComExReason.isShown())
           {
             ValidationMsg += "\nRequired field: Compound Exit Reason.";
             secComExReason.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCluster.getText().toString().length()==0 & secCluster.isShown())
           {
             ValidationMsg += "\nRequired field: Cluster.";
             secCluster.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtBlock.getText().toString().length()==0 & secBlock.isShown())
           {
             ValidationMsg += "\nRequired field: Block.";
             secBlock.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             ValidationMsg += "\nRequired field: Round.";
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
             secCompoundID.setBackgroundColor(Color.WHITE);
             secCompoundCode.setBackgroundColor(Color.WHITE);
             secCompoundName.setBackgroundColor(Color.WHITE);
             secCompoundAdrs.setBackgroundColor(Color.WHITE);
             secVillID.setBackgroundColor(Color.WHITE);
             secTotalHH.setBackgroundColor(Color.WHITE);
             secActive.setBackgroundColor(Color.WHITE);
             secComEnDate.setBackgroundColor(Color.WHITE);
             secComExDate.setBackgroundColor(Color.WHITE);
             secComExReason.setBackgroundColor(Color.WHITE);
             secCluster.setBackgroundColor(Color.WHITE);
             secBlock.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String CompoundID)
     {
       try
        {     
           RadioButton rb;
           Compound_DataModel d = new Compound_DataModel();
           String SQL = "Select * from "+ TableName +"  Where CompoundID='"+ CompoundID +"'";
           List<Compound_DataModel> data = d.SelectAll(this, SQL);
           for(Compound_DataModel item : data){
             txtCompoundID.setText(item.getCompoundID());
             txtCompoundCode.setText(item.getCompoundCode());
             txtCompoundName.setText(item.getCompoundName());
             txtCompoundAdrs.setText(item.getCompoundAdrs());
             txtVillID.setText(item.getVillID());
             txtTotalHH.setText(item.getTotalHH());
             txtActive.setText(item.getActive());
             dtpComEnDate.setText(item.getComEnDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getComEnDate()));
             dtpComExDate.setText(item.getComExDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getComExDate()));
             txtComExReason.setText(item.getComExReason());
             txtCluster.setText(item.getCluster());
             txtBlock.setText(item.getBlock());
             txtRnd.setText(item.getRnd());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Compound.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpComEnDate);
      if (VariableID.equals("btnComEnDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpComEnDate);
      }
      else if (VariableID.equals("btnComExDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpComExDate);
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

     private String CompSerial(String VillID)
     {
         String SL = C.ReturnSingleValue("Select (ifnull(max(cast(CompoundCode as int)),0)+1)SL from Compound where VillID='"+ VillID +"'"); //where ParticipantID='"+ ParticipantID +"'");
         return Global.Right("0000"+SL,5);
     }

 
 // turning off the GPS if its in on state. to avoid the battery drain.
 @Override
 protected void onDestroy() {
     // TODO Auto-generated method stub
     super.onDestroy();
 }
}