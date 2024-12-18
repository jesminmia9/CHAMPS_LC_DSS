
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

 import forms_datamodel.NotPregnant_DataModel;
 import Utility.*;
 import Common.*;
 import forms_datamodel.tmpNotPregnant_DataModel;

 public class Surv_NotPregnant extends AppCompatActivity {
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
    LinearLayout secNotPregID;
    View lineNotPregID;
    TextView VlblNotPregID;
    EditText txtNotPregID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secNotPregVDate;
    View lineNotPregVDate;
    TextView VlblNotPregVDate;
    EditText dtpNotPregVDate;
     LinearLayout secLMPDt;
     View lineLMPDt;
     TextView VlblLMPDt;
     EditText dtpLMPDt;
     LinearLayout secLMPDtType;
     View lineLMPDtType;
     TextView VlblLMPDtType;
     RadioGroup rdogrpLMPDtType;
     RadioButton rdoLMPDtType1;
     RadioButton rdoLMPDtType2;
    LinearLayout secPregStatus;
    View linePregStatus;
    TextView VlblPregStatus;
    RadioGroup rdogrpPregStatus;
    RadioButton rdoPregStatus1;
    RadioButton rdoPregStatus2;
    RadioButton rdoPregStatus3;
    LinearLayout secNotPregNote;
    View lineNotPregNote;
    TextView VlblNotPregNote;
    EditText txtNotPregNote;
     TextView lblEvCode;
     LinearLayout secNotPregNote1;
     View lineNotPregNote1;

     int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String NOTPREGID = "";
      String MEM_ID = "";
      String HHID = "";
      String EV_TYPE = "";
      String ROUND = "";
      String VISIT_DATE = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.surv_notpregnant);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();
         sp = new MySharedPreferences();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         NOTPREGID = IDbundle.getString("NotPregID");
         MEM_ID = IDbundle.getString("MemID");
         HHID = IDbundle.getString("HHID");
         EV_TYPE = IDbundle.getString("evtype");
         ROUND = IDbundle.getString("round");
         VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy

         TableName = "tmpNotPregnant";
         MODULEID = 31;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Surv_NotPregnant.this);
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
        Connection.LocalizeLanguage(Surv_NotPregnant.this, MODULEID, LANGUAGEID);


         dtpNotPregVDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnNotPregVDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpLMPDt.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     VariableID = "btnLMPDt"; showDialog(DATE_DIALOG);
                     return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables
         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1)){
             secLMPDt.setVisibility(View.GONE);
             lineLMPDt.setVisibility(View.GONE);
             dtpLMPDt.setText("");
             secLMPDtType.setVisibility(View.GONE);
             lineLMPDtType.setVisibility(View.GONE);
             rdogrpLMPDtType.clearCheck();
         }

        DataSearch(NOTPREGID);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_NotPregnant.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         lblEvCode = findViewById(R.id.lblEvCode);
         lblEvCode.setText(EV_TYPE);

         secNotPregID=(LinearLayout)findViewById(R.id.secNotPregID);
         lineNotPregID=(View)findViewById(R.id.lineNotPregID);
         VlblNotPregID=(TextView) findViewById(R.id.VlblNotPregID);
         txtNotPregID=(EditText) findViewById(R.id.txtNotPregID);
//         txtNotPregID.setText(NOTPREGID);
//         txtNotPregID.setEnabled(false);

         if(NOTPREGID.length()==0) txtNotPregID.setText(Global.Get_UUID(DEVICEID));
         else txtNotPregID.setText(NOTPREGID);
         txtNotPregID.setEnabled(false);

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
         txtMemID.setText(MEM_ID);
         secNotPregVDate=(LinearLayout)findViewById(R.id.secNotPregVDate);
         lineNotPregVDate=(View)findViewById(R.id.lineNotPregVDate);
         VlblNotPregVDate=(TextView) findViewById(R.id.VlblNotPregVDate);
         dtpNotPregVDate=(EditText) findViewById(R.id.dtpNotPregVDate);
         dtpNotPregVDate.setText(VISIT_DATE);
         secNotPregVDate.setVisibility(View.GONE);
         secPregStatus=(LinearLayout)findViewById(R.id.secPregStatus);
         linePregStatus=(View)findViewById(R.id.linePregStatus);
         VlblPregStatus = (TextView) findViewById(R.id.VlblPregStatus);
         rdogrpPregStatus = (RadioGroup) findViewById(R.id.rdogrpPregStatus);
         rdoPregStatus1 = (RadioButton) findViewById(R.id.rdoPregStatus1);
         rdoPregStatus2 = (RadioButton) findViewById(R.id.rdoPregStatus2);
         rdoPregStatus3 = (RadioButton) findViewById(R.id.rdoPregStatus3);

         if(EV_TYPE.equals("40")){
             rdoPregStatus1.setChecked(true);
         }

         secLMPDt=(LinearLayout)findViewById(R.id.secLMPDt);
         lineLMPDt=(View)findViewById(R.id.lineLMPDt);
         VlblLMPDt=(TextView) findViewById(R.id.VlblLMPDt);
         dtpLMPDt=(EditText) findViewById(R.id.dtpLMPDt);
         secLMPDtType=(LinearLayout)findViewById(R.id.secLMPDtType);
         lineLMPDtType=(View)findViewById(R.id.lineLMPDtType);
         VlblLMPDtType = (TextView) findViewById(R.id.VlblLMPDtType);
         rdogrpLMPDtType = (RadioGroup) findViewById(R.id.rdogrpLMPDtType);
         rdoLMPDtType1 = (RadioButton) findViewById(R.id.rdoLMPDtType1);
         rdoLMPDtType2 = (RadioButton) findViewById(R.id.rdoLMPDtType2);
         secNotPregNote=(LinearLayout)findViewById(R.id.secNotPregNote);
         lineNotPregNote=(View)findViewById(R.id.lineNotPregNote);
         VlblNotPregNote=(TextView) findViewById(R.id.VlblNotPregNote);
         txtNotPregNote=(EditText) findViewById(R.id.txtNotPregNote);

         secNotPregNote1=(LinearLayout)findViewById(R.id.secNotPregNote1);
         lineNotPregNote1=(View)findViewById(R.id.lineNotPregNote1);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_NotPregnant.this, e.getMessage());
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
         	Connection.MessageBox(Surv_NotPregnant.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         tmpNotPregnant_DataModel objSave = new tmpNotPregnant_DataModel();
         objSave.setNotPregID(txtNotPregID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setMemID(txtMemID.getText().toString());
         objSave.setNotPregVDate(dtpNotPregVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpNotPregVDate.getText().toString()) : dtpNotPregVDate.getText().toString());

         objSave.setLMPDt(dtpLMPDt.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpLMPDt.getText().toString()) : dtpLMPDt.getText().toString());
         String[] d_rdogrpLMPDtType = new String[] {"1","2"};
         objSave.setLMPDtType("");
         for (int i = 0; i < rdogrpLMPDtType.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLMPDtType.getChildAt(i);
             if (rb.isChecked()) objSave.setLMPDtType(d_rdogrpLMPDtType[i]);
         }

         String[] d_rdogrpPregStatus = new String[] {"40","49","3"};
         objSave.setPregStatus("");
         for (int i = 0; i < rdogrpPregStatus.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPregStatus.getChildAt(i);
             if (rb.isChecked()) objSave.setPregStatus(d_rdogrpPregStatus[i]);
         }

         objSave.setNotPregNote(txtNotPregNote.getText().toString());
         objSave.setRnd(ROUND);
         objSave.setStartTime(STARTTIME);
         objSave.setEndTime(g.CurrentTime24());
         objSave.setDeviceID(DEVICEID);
         objSave.setEntryUser(ENTRYUSER); //from data entry user list
         objSave.setLat(MySharedPreferences.getValue(this, "lat"));
         objSave.setLon(MySharedPreferences.getValue(this, "lon"));

         String status = objSave.SaveUpdateData(this);
         if(status.length()==0) {
             sp.saveEvent(Surv_NotPregnant.this, MEM_ID+"NP", new SurvMemberData("40", ROUND, MEM_ID));

             if(rdoPregStatus1.isChecked()){
                 C.SaveData("Update tmpMember set Upload='2',modifydate='"+ Global.DateTimeNowYMDHMS() +"',Pstat='40',LmpDt='' Where MemID='" + MEM_ID + "'");
                 C.SaveData("Update tmpMemberMove set Upload='2',modifydate='"+ Global.DateTimeNowYMDHMS() +"',Pstat='40',LmpDt='' Where HHID='"+ HHID +"' and MemID='" + MEM_ID + "'");
             }
             else if(rdoPregStatus2.isChecked()){
                 C.SaveData("Update tmpMember set Upload='2',Pstat='49',LmpDt='' Where MemID='" + MEM_ID + "'");
                 C.SaveData("Update tmpMemberMove set Upload='2',modifydate='"+ Global.DateTimeNowYMDHMS() +"',Pstat='49',LmpDt='' Where HHID='"+ HHID +"' and MemID='" + MEM_ID + "'");
             }


             Intent returnIntent = new Intent();
             returnIntent.putExtra("res", "");
             setResult(Activity.RESULT_OK, returnIntent);

             Connection.MessageBox(Surv_NotPregnant.this, "Saved Successfully");
             finish();
         }
         else{
             Connection.MessageBox(Surv_NotPregnant.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_NotPregnant.this, e.getMessage());
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
         if(txtNotPregID.getText().toString().length()==0 & secNotPregID.isShown())
           {
             ValidationMsg += "\nRequired field: Not Pregnancy Internal ID.";
             secNotPregID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
           {
             ValidationMsg += "\nRequired field: Household ID.";
             secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMemID.getText().toString().length()==0 & secMemID.isShown())
           {
             ValidationMsg += "\nRequired field: Member internal ID.";
             secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpNotPregVDate.getText().toString());
         if(DV.length()!=0 & secNotPregVDate.isShown())
           {
             ValidationMsg += "\n1. Required field/Not a valid date format: Visit Date.";
             secNotPregVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpLMPDt.getText().toString());
         if(DV.length()!=0 & secLMPDt.isShown())
         {
             ValidationMsg += "\nRequired field/Not a valid date format: What was your LMP Date?.";
             secLMPDt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!rdoLMPDtType1.isChecked() & !rdoLMPDtType2.isChecked() & secLMPDtType.isShown())
         {
             ValidationMsg += "\nRequired field: LMP Date type.";
             secLMPDtType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!rdoPregStatus1.isChecked() & !rdoPregStatus2.isChecked() & !rdoPregStatus3.isChecked() & secPregStatus.isShown())
           {
             ValidationMsg += "\n2. Required field: What is the status of the women?.";
             secPregStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secNotPregID.setBackgroundColor(Color.WHITE);
             secHHID.setBackgroundColor(Color.WHITE);
             secMemID.setBackgroundColor(Color.WHITE);
             secNotPregVDate.setBackgroundColor(Color.WHITE);
            secLMPDt.setBackgroundColor(Color.WHITE);
            secLMPDtType.setBackgroundColor(Color.WHITE);
             secPregStatus.setBackgroundColor(Color.WHITE);
             secNotPregNote.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String NotPregID)
     {
       try
        {     
           RadioButton rb;
            tmpNotPregnant_DataModel d = new tmpNotPregnant_DataModel();
           String SQL = "Select * from "+ TableName +"  Where NotPregID='"+ NotPregID +"'";
           List<tmpNotPregnant_DataModel> data = d.SelectAll(this, SQL);
           for(tmpNotPregnant_DataModel item : data){
             txtNotPregID.setText(item.getNotPregID());
             txtHHID.setText(item.getHHID());
             txtMemID.setText(item.getMemID());
             dtpNotPregVDate.setText(item.getNotPregVDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getNotPregVDate()));
               dtpLMPDt.setText(item.getLMPDt().toString().length()==0 ? "" : Global.DateConvertDMY(item.getLMPDt()));
               String[] d_rdogrpLMPDtType = new String[] {"1","2"};
               for (int i = 0; i < d_rdogrpLMPDtType.length; i++)
               {
                   if (String.valueOf(item.getLMPDtType()).equals(String.valueOf(d_rdogrpLMPDtType[i])))
                   {
                       rb = (RadioButton)rdogrpLMPDtType.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
             String[] d_rdogrpPregStatus = new String[] {"40","49","3"};
             for (int i = 0; i < d_rdogrpPregStatus.length; i++)
             {
                 if (String.valueOf(item.getPregStatus()).equals(String.valueOf(d_rdogrpPregStatus[i])))
                 {
                     rb = (RadioButton)rdogrpPregStatus.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtNotPregNote.setText(item.getNotPregNote());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_NotPregnant.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpNotPregVDate);
      if (VariableID.equals("btnNotPregVDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpNotPregVDate);
      }
      else if (VariableID.equals("btnLMPDt"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpLMPDt);
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