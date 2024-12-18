
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
 import android.widget.SimpleAdapter;
 import android.widget.Spinner;
 import android.widget.TextView;
 import android.widget.TimePicker;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.R;
 import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.HashMap;
 import java.util.List;

 import Common.Connection;
 import Common.Global;
 import Utility.MySharedPreferences;
 import forms_datamodel.Visits_DataModel;

 public class Old_Baseline_Visits extends AppCompatActivity {
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
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secVisitNo;
    View lineVisitNo;
    TextView VlblVisitNo;
    EditText txtVisitNo;
    LinearLayout secVisitDate;
    View lineVisitDate;
    TextView VlblVisitDate;
    EditText dtpVisitDate;
    LinearLayout secVisitStatus;
    View lineVisitStatus;
    TextView VlblVisitStatus;
     Spinner spnVisitStatus;
//    RadioButton rdoVisitStatus1;
//    RadioButton rdoVisitStatus2;
//    RadioButton rdoVisitStatus3;
//    RadioButton rdoVisitStatus4;
//    RadioButton rdoVisitStatus5;
//    RadioButton rdoVisitStatus6;
//    RadioButton rdoVisitStatus7;
//    RadioButton rdoVisitStatus8;
//    RadioButton rdoVisitStatus9;
    LinearLayout secVisitStatusOth;
    View lineVisitStatusOth;
    TextView VlblVisitStatusOth;
    EditText txtVisitStatusOth;
    LinearLayout secRespID;
    View lineRespID;
    TextView VlblRespID;
    EditText txtRespID;
//    Spinner spnRespID;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secVisitNote;
    View lineVisitNote;
    TextView VlblVisitNote;
    EditText txtVisitNote;

     LinearLayout secVisitNote1;
     View lineVisitNote1;
     TextView VlblVisitNote1;

     int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String HHID = "";
     String HHNO = "";
     String VILLID = "";
     String CompoundID = "";
     String VISITNO = "";
     String HHHEAD = "";

      String COMPOUNDNAME = "";
      String COMPOUNDADRS = "";
      String ROUND = "";
      String SurvType = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.baseline_visits);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         VILLID = IDbundle.getString("VillID");
         CompoundID = IDbundle.getString("CompoundID");
         HHID = IDbundle.getString("HHID");
         HHNO = IDbundle.getString("HHNO");
         VISITNO = IDbundle.getString("VisitNo");
         COMPOUNDNAME = IDbundle.getString("CompoundName");
         COMPOUNDADRS = IDbundle.getString("CompoundAdrs");
         HHHEAD = IDbundle.getString("HHHead");
         ROUND = "0";
         SurvType = IDbundle.getString("SurvType");

         TableName = "Visits";
         MODULEID = 8;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Old_Baseline_Visits.this);
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
        txtRnd.setText(ROUND);
        Connection.LocalizeLanguage(Old_Baseline_Visits.this, MODULEID, LANGUAGEID);


         dtpVisitDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnVisitDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables
         secVisitStatusOth.setVisibility(View.GONE);

        DataSearch(HHID,VISITNO);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Old_Baseline_Visits.this, e.getMessage());
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
         secVisitNo=(LinearLayout)findViewById(R.id.secVisitNo);
         lineVisitNo=(View)findViewById(R.id.lineVisitNo);
         VlblVisitNo=(TextView) findViewById(R.id.VlblVisitNo);
         txtVisitNo=(EditText) findViewById(R.id.txtVisitNo);

         if(VISITNO.length()==0) {
             String VNO = C.ReturnSingleValue("Select VisitNo from Visits where HHID='"+ HHID +"' and VisitStatus='01' ");
             if(VNO.length()>0) {
                 txtVisitNo.setText(VNO);
                 VISITNO = VNO;
             }
             else
                 txtVisitNo.setText(VisitSerial(HHID));
         }
         else
             txtVisitNo.setText("");
         txtVisitNo.setEnabled(false);

/*         txtVisitNo.setText(VISITNO);
         if(VISITNO.length()==0)
             txtVisitNo.setText(VisitSerial(HHID));
         else
             txtVisitNo.setText(VISITNO);
         txtVisitNo.setEnabled(false);*/

         secVisitDate=(LinearLayout)findViewById(R.id.secVisitDate);
         lineVisitDate=(View)findViewById(R.id.lineVisitDate);
         VlblVisitDate=(TextView) findViewById(R.id.VlblVisitDate);
         dtpVisitDate=(EditText) findViewById(R.id.dtpVisitDate);
         dtpVisitDate.setText(Global.DateNowDMY());
         secVisitStatus=(LinearLayout)findViewById(R.id.secVisitStatus);
         lineVisitStatus=(View)findViewById(R.id.lineVisitStatus);
         VlblVisitStatus = (TextView) findViewById(R.id.VlblVisitStatus);
         spnVisitStatus=(Spinner) findViewById(R.id.spnVisitStatus);
         List<String> listVStatus = new ArrayList<String>();
         listVStatus.add("");
         listVStatus.add("01-Interview Complete");
         listVStatus.add("02-No member or suitable person found during house visit");
         listVStatus.add("03-All members absent for many days");
         listVStatus.add("04-Interview Canceled");
         listVStatus.add("05-Reluctance to interview");
         listVStatus.add("06-House vacant or address not of any residence");
         listVStatus.add("07-Residential Destroyed");
         listVStatus.add("08-Dwelling not found");
         listVStatus.add("09-Other");
         spnVisitStatus.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listVStatus)));

         spnVisitStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 String spnData = "";
                 if (spnVisitStatus.getSelectedItem().toString().length() != 0)
                 {
                     spnData = Connection.SelectedSpinnerValue(spnVisitStatus.getSelectedItem().toString(), "-");
                 }
                 if(spnData.equalsIgnoreCase("01"))
                 {
                     secRespID.setVisibility(View.VISIBLE);
                     secRespID.setVisibility(View.VISIBLE);
                     secVisitStatusOth.setVisibility(View.GONE);
                     lineVisitStatusOth.setVisibility(View.GONE);
                     txtVisitStatusOth.setText("");
                 }
                 if(spnData.equalsIgnoreCase("09"))
                 {
                     secVisitStatusOth.setVisibility(View.VISIBLE);
                     lineVisitStatusOth.setVisibility(View.VISIBLE);
                     txtVisitStatusOth.setText("");
                     secRespID.setVisibility(View.GONE);
                     lineRespID.setVisibility(View.GONE);
                     txtRespID.setText("");
                 }
                 if(spnData.equalsIgnoreCase("02") || spnData.equalsIgnoreCase("03") || spnData.equalsIgnoreCase("04")  || spnData.equalsIgnoreCase("05") || spnData.equalsIgnoreCase("06") || spnData.equalsIgnoreCase("07") || spnData.equalsIgnoreCase("08"))
                 {
                     secRespID.setVisibility(View.GONE);
                     lineRespID.setVisibility(View.GONE);
                     txtRespID.setText("");
                     secVisitStatusOth.setVisibility(View.GONE);
                     lineVisitStatusOth.setVisibility(View.GONE);
                     txtVisitStatusOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });

         secVisitStatusOth=(LinearLayout)findViewById(R.id.secVisitStatusOth);
         lineVisitStatusOth=(View)findViewById(R.id.lineVisitStatusOth);
         VlblVisitStatusOth=(TextView) findViewById(R.id.VlblVisitStatusOth);
         txtVisitStatusOth=(EditText) findViewById(R.id.txtVisitStatusOth);
         secRespID=(LinearLayout)findViewById(R.id.secRespID);
         lineRespID=(View)findViewById(R.id.lineRespID);
         VlblRespID=(TextView) findViewById(R.id.VlblRespID);
         txtRespID=(EditText) findViewById(R.id.txtRespID);

         /*spnRespID=(Spinner) findViewById(R.id.spnRespID);
         List<String> listRespID = new ArrayList<String>();
         listRespID.add("");
         listRespID.add("01-Member 1");
         listRespID.add("02-Member 2");

         spnRespID.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listRespID)));
         spnRespID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 String spnData = "";
                 if (spnRespID.getSelectedItem().toString().length() != 0)
                 {
                     spnData = Connection.SelectedSpinnerValue(spnRespID.getSelectedItem().toString(), "-");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });*/

         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         secVisitNote=(LinearLayout)findViewById(R.id.secVisitNote);
         lineVisitNote=(View)findViewById(R.id.lineVisitNote);
         VlblVisitNote=(TextView) findViewById(R.id.VlblVisitNote);
         txtVisitNote=(EditText) findViewById(R.id.txtVisitNote);

         secVisitNote1=(LinearLayout)findViewById(R.id.secVisitNote1);
         lineVisitNote1=(View)findViewById(R.id.lineVisitNote1);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Old_Baseline_Visits.this, e.getMessage());
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
         	Connection.MessageBox(Old_Baseline_Visits.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         Visits_DataModel objSave = new Visits_DataModel();
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setVisitNo(txtVisitNo.getText().toString());
         objSave.setVisitDate(dtpVisitDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpVisitDate.getText().toString()) : dtpVisitDate.getText().toString());

/*         String[] d_rdogrpVisitStatus = new String[] {"1","2","3","4","5","6","7","8","9"};
         objSave.setVisitStatus("");
         for (int i = 0; i < rdogrpVisitStatus.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpVisitStatus.getChildAt(i);
             if (rb.isChecked()) objSave.setVisitStatus(d_rdogrpVisitStatus[i]);
         }*/

         objSave.setVisitStatus(spnVisitStatus.getSelectedItemPosition() == 0 ? "" : spnVisitStatus.getSelectedItem().toString().split("-")[0]);

         objSave.setVisitStatusOth(txtVisitStatusOth.getText().toString());
         objSave.setRespID(txtRespID.getText().toString());
//         objSave.setRespID(spnRespID.getSelectedItemPosition() == 0 ? "" : spnRespID.getSelectedItem().toString().split("-")[0]);

         objSave.setRnd(txtRnd.getText().toString());
         objSave.setVisitNote(txtVisitNote.getText().toString());
         objSave.setStartTime(STARTTIME);
         objSave.setEndTime(g.CurrentTime24());
         objSave.setDeviceID(DEVICEID);
         objSave.setEntryUser(ENTRYUSER); //from data entry user list
         objSave.setLat(MySharedPreferences.getValue(this, "lat"));
         objSave.setLon(MySharedPreferences.getValue(this, "lon"));

         String status = objSave.SaveUpdateData(this);
         if(status.length()==0) {
             String VS = spnVisitStatus.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnVisitStatus.getSelectedItem().toString(), "-");

             if (VS.equals("01")) {
                 Intent returnIntent = new Intent();
                 returnIntent.putExtra("res", "");
                 setResult(Activity.RESULT_OK, returnIntent);
                 finish();
                 Bundle IDbundle = new Bundle();
                 IDbundle.putString("HHID", HHID);
                 IDbundle.putString("HHNO", HHNO);
                 IDbundle.putString("VillID", VILLID);
                 IDbundle.putString("CompoundID", CompoundID);
                 IDbundle.putString("MemID", "");
                 IDbundle.putString("CompoundName", COMPOUNDNAME);
                 IDbundle.putString("CompoundAdrs", COMPOUNDADRS);
                 IDbundle.putString("HHHead", HHHEAD);
                 IDbundle.putString("round", ROUND);
                 IDbundle.putString("SurvType", SurvType);
                 Intent f1 = new Intent(getApplicationContext(), Baseline_Member_list.class);
                 f1.putExtras(IDbundle);
                 startActivityForResult(f1, 1);
             } else {
                 Intent returnIntent = new Intent();
                 returnIntent.putExtra("res", "");
                 setResult(Activity.RESULT_OK, returnIntent);
                 Connection.MessageBox(Old_Baseline_Visits.this, "Saved Successfully");
                 finish();
             }

         }
         else{
             Connection.MessageBox(Old_Baseline_Visits.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Old_Baseline_Visits.this, e.getMessage());
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
         if(txtVisitNo.getText().toString().length()==0 & secVisitNo.isShown())
           {
             ValidationMsg += "\nRequired field: Household Visit No.";
             secVisitNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpVisitDate.getText().toString());
         if(DV.length()!=0 & secVisitDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Household Visit date.";
             secVisitDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
/*         if(!rdoVisitStatus1.isChecked() & !rdoVisitStatus2.isChecked() & !rdoVisitStatus3.isChecked() & !rdoVisitStatus4.isChecked() & !rdoVisitStatus5.isChecked() & !rdoVisitStatus6.isChecked() & !rdoVisitStatus7.isChecked() & !rdoVisitStatus8.isChecked() & !rdoVisitStatus9.isChecked() & secVisitStatus.isShown())
           {
             ValidationMsg += "\nRequired field: Household Visit Status.";
             secVisitStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/

         if(spnVisitStatus.getSelectedItemPosition()==0  & secVisitStatus.isShown())
         {
             ValidationMsg += "\nRequired field: Household Visit Status";
             secVisitStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

         if(txtVisitStatusOth.getText().toString().length()==0 & secVisitStatusOth.isShown())
           {
             ValidationMsg += "\nRequired field: Household Visit Status others.";
             secVisitStatusOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRespID.getText().toString().length()==0 & secRespID.isShown())
           {
             ValidationMsg += "\nRequired field: Respondent’s ID.";
             secRespID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             ValidationMsg += "\nRequired field: Round No.";
             secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         /*if(txtVisitNote.getText().toString().length()==0 & secVisitNote.isShown())
           {
             ValidationMsg += "\nRequired field: Household Visit Note.";
             secVisitNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secVisitNo.setBackgroundColor(Color.WHITE);
             secVisitDate.setBackgroundColor(Color.WHITE);
             secVisitStatus.setBackgroundColor(Color.WHITE);
             secVisitStatusOth.setBackgroundColor(Color.WHITE);
             secRespID.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
             secVisitNote.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String HHID, String VisitNo)
     {
       try
        {     
           RadioButton rb;
           Visits_DataModel d = new Visits_DataModel();
           String SQL = "Select * from "+ TableName +"  Where HHID='"+ HHID +"' and VisitNo='"+ VisitNo +"' and Rnd='"+ ROUND +"'";
           List<Visits_DataModel> data = d.SelectAll(this, SQL);
           for(Visits_DataModel item : data){
             txtHHID.setText(item.getHHID());
             txtVisitNo.setText(item.getVisitNo());
             dtpVisitDate.setText(item.getVisitDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getVisitDate()));
             spnVisitStatus.setSelection(Global.SpinnerItemPositionAnyLength(spnVisitStatus, String.valueOf(item.getVisitStatus())));

            /* String[] d_rdogrpVisitStatus = new String[] {"1","2","3","4","5","6","7","8","9"};
             for (int i = 0; i < d_rdogrpVisitStatus.length; i++)
             {
                 if (String.valueOf(item.getVisitStatus()).equals(String.valueOf(d_rdogrpVisitStatus[i])))
                 {
                     rb = (RadioButton)rdogrpVisitStatus.getChildAt(i);
                     rb.setChecked(true);
                 }
             }*/

             txtVisitStatusOth.setText(item.getVisitStatusOth());
             txtRespID.setText(item.getRespID());
//               spnRespID.setSelection(Global.SpinnerItemPositionAnyLength(spnRespID, String.valueOf(item.getRespID())));

               txtRnd.setText(item.getRnd());
             txtVisitNote.setText(item.getVisitNote());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Old_Baseline_Visits.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpVisitDate);
      if (VariableID.equals("btnVisitDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpVisitDate);
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

     private String VisitSerial(String HHID)
     {
         String SL = C.ReturnSingleValue("Select (ifnull(max(cast(VisitNo as int)),0)+1)SL from Visits where HHID='"+ HHID +"'"); //where ParticipantID='"+ ParticipantID +"'");
         return Global.Right("00"+SL,2);
     }
 
 // turning off the GPS if its in on state. to avoid the battery drain.
 @Override
 protected void onDestroy() {
     // TODO Auto-generated method stub
     super.onDestroy();
 }

     private void TransferDataToTemp()
     {
         C.SaveData("Delete from tmpHousehold");
         C.SaveData("Delete from tmpVisits");
         C.SaveData("Delete from tmpMember");
         C.SaveData("Delete from tmpSES");
         C.SaveData("Delete from tmpPregHis");
         C.SaveData("Delete from tmpDeath");
         C.SaveData("Delete from tmpDelivery");
         C.SaveData("Delete from tmpEducation");
         C.SaveData("Delete from tmpFatherSerial");
         C.SaveData("Delete from tmpMotherSerial");
         C.SaveData("Delete from tmpSpouseSerial");
         C.SaveData("Delete from tmpOccupation");
         C.SaveData("Delete from tmpRelation");
         C.SaveData("Delete from tmpMaritalSts");
         C.SaveData("Delete from tmpMemberMove");
         C.SaveData("Delete from tmpMigration");
         C.SaveData("Delete from tmpMovement");
         C.SaveData("Delete from tmpNotPregnant");
         C.SaveData("Delete from tmpLiveBirth");
         C.SaveData("Delete from tmpStillBirth");


         //-- -tmpHousehold Information-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         String SQL = "";
         SQL = "Insert into tmpHousehold(HHID,HHNO,VillID,CompoundID,HHHead,MobileNo1,MobileNo2,TotMem,HHEnType,HHEnDate,HHExType,HHExDate,Rnd,Active,HHNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select HHID,HHNO,VillID,CompoundID,HHHead,MobileNo1,MobileNo2,TotMem,HHEnType,HHEnDate,HHExType,HHExDate,Rnd,Active,HHNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from Household";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpVisitsHH Information-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpVisits";
         SQL += " (HHID,VisitNo,VisitDate,VisitStatus,VisitStatusOth,RespID,Rnd,VisitNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select HHID,VisitNo,VisitDate,VisitStatus,VisitStatusOth,RespID,Rnd,VisitNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from Visits";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpMember Information-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpMember";
         SQL += " (MemID,HHID,DSSID,MSlNo,Name,Rth,RthOth,Sex,BDate,BDateType,AgeY,MoNo,MoName,FaNo,FaName,EduY,MS,MSOth,Employ,EmployOth,Ocp,OcpOth,Religion,ReligionOth,Ethnicity,MobileNo,Sp1,Sp1Name,Sp2,Sp2Name,Sp3,Sp3Name,Sp4,Sp4Name,Pstat,LmpDt,Rnd,Active,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select MemID,HHID,DSSID,MSlNo,Name,Rth,RthOth,Sex,BDate,BDateType,AgeY,MoNo,MoName,FaNo,FaName,EduY,MS,MSOth,Employ,EmployOth,Ocp,OcpOth,Religion,ReligionOth,Ethnicity,MobileNo,Sp1,Sp1Name,Sp2,Sp2Name,Sp3,Sp3Name,Sp4,Sp4Name,Pstat,LmpDt,Rnd,Active,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from Member";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpSES Information-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpSES";
         SQL += " (HHID,SESNo,SESVDate,SESVStatus,SESVStatusOth,Rnd,WSDrink,WSDrinkOth,Toilet,ToiletOth,ToiletShrd,ToiletUseNo,ToiletUseNoDk,ToiletLoc,CookDvc,CookDvcOth,";
         SQL += " CookFuel,CookFuelOth,CookPlc,CookPlcOth,Floor,FloorOth,Roof,RoofOth,Wall,WallOth,RoomSleep,RoomSleepDk,HomesteadAny,OthLand,Electricity,Solar,Heater,";
         SQL += " AC,ElecFan,Lantern,Lamp,Matt,Bed,Chair,Sofa,Tables,Watch,WMachine,Iron,Booth,SMachine,Generator,Internet,Satellite,Landline,Cellphone,TV,Radio,DVD,";
         SQL += " Video,Computer,Cable,Microwave,Geyser,Grill,Fridge,DeepFreezer,Stove,Bike,Motorcycle,Car,Rickshaw,Cart,Canoe,Bus,Tractor,Plow,Duck,Cow,Buffalo,Bull,";
         SQL += " Sheep,Goat,Chicken,Donkey,Horse,Pigeon,OtherAsset,OtherAssetOth,SESNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";

         SQL += " Select HHID,SESNo,SESVDate,SESVStatus,SESVStatusOth,Rnd,WSDrink,WSDrinkOth,Toilet,ToiletOth,ToiletShrd,ToiletUseNo,ToiletUseNoDk,ToiletLoc,CookDvc,CookDvcOth,";
         SQL += " CookFuel,CookFuelOth,CookPlc,CookPlcOth,Floor,FloorOth,Roof,RoofOth,Wall,WallOth,RoomSleep,RoomSleepDk,HomesteadAny,OthLand,Electricity,Solar,Heater,";
         SQL += " AC,ElecFan,Lantern,Lamp,Matt,Bed,Chair,Sofa,Tables,Watch,WMachine,Iron,Booth,SMachine,Generator,Internet,Satellite,Landline,Cellphone,TV,Radio,DVD,";
         SQL += " Video,Computer,Cable,Microwave,Geyser,Grill,Fridge,DeepFreezer,Stove,Bike,Motorcycle,Car,Rickshaw,Cart,Canoe,Bus,Tractor,Plow,Duck,Cow,Buffalo,Bull,";
         SQL += " Sheep,Goat,Chicken,Donkey,Horse,Pigeon,OtherAsset,OtherAssetOth,SESNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from SES";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpPregHis. History-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpPregHis";
         SQL += "(ObsMatID,MemID,HHID,MSlNo,ObsVDate,ObsVStatus,ObsVStatusOth,MarMon,MarYear,MarDK,EverPreg,TotPreg,GaveBirth,ChildLivWWo,SonLivWWo,DaugLivWWo,ChldLivOut,SonLivOut,DaugLivOut,EarlyAlive,EarlyAliveNo,ChldDie,BoyDied,GirlDied,ChDiedFsMon,NotLivBrth,TotNotLB,StillBirth,StillBirthDk,MiscAbor,MiscAborDk,LastPregRes,LastOutDate,LastOutDateDK,Cesarean,CesareanNo,TotPregOut,ObsNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select ObsMatID,MemID,HHID,MSlNo,ObsVDate,ObsVStatus,ObsVStatusOth,MarMon,MarYear,MarDK,EverPreg,TotPreg,GaveBirth,ChildLivWWo,SonLivWWo,DaugLivWWo,ChldLivOut,SonLivOut,DaugLivOut,EarlyAlive,EarlyAliveNo,ChldDie,BoyDied,GirlDied,ChDiedFsMon,NotLivBrth,TotNotLB,StillBirth,StillBirthDk,MiscAbor,MiscAborDk,LastPregRes,LastOutDate,LastOutDateDK,Cesarean,CesareanNo,TotPregOut,ObsNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from PregHis";
         SQL += " where HHID='" + HHID + "'";

         C.SaveData(SQL);

         //-- -tmpDeath-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpDeath";
         SQL += "(DeathID,HHID,MemID,DthDate,DthDateType,DthTime,DthTimeType,DthPlace,DthPlaceOth,DthAdrsAL1,DthAdrsAL2,DthAdrsAL3,DthAdrsAL4,DthAdrsAL5,DthVDate,Rnd,DthNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select DeathID,HHID,MemID,DthDate,DthDateType,DthTime,DthTimeType,DthPlace,DthPlaceOth,DthAdrsAL1,DthAdrsAL2,DthAdrsAL3,DthAdrsAL4,DthAdrsAL5,DthVDate,Rnd,DthNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from Death";
         SQL += " where HHID='" + HHID + "'";

         C.SaveData(SQL);

         //-- -tmpDelivery-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpDelivery";
         SQL += "(DeliveryID,HHID,MemID,DelDate,DelDateType,DelTime,DelTimeType,TotOut,TotLB,TotMis,TotSB,TotAB,DelVDate,Rnd,DelNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select DeliveryID,HHID,MemID,DelDate,DelDateType,DelTime,DelTimeType,TotOut,TotLB,TotMis,TotSB,TotAB,DelVDate,Rnd,DelNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from Delivery";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpEducation-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpEducation";
         SQL += "(EduID,HHID,MemID,EduEvDate,NewEdu,OldEdu,EduNote,EduVDate,Rnd,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select EduID,HHID,MemID,EduEvDate,NewEdu,OldEdu,EduNote,EduVDate,Rnd,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from Education";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpFatherSerial-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpFatherSerial";
         SQL += "(FathSerialID,HHID,MemID,FathSlEvDate,NewFathSl,OldFathSl,FathSlNote,FathVDate,Rnd,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select FathSerialID,HHID,MemID,FathSlEvDate,NewFathSl,OldFathSl,FathSlNote,FathVDate,Rnd,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from FatherSerial";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpMotherSerial-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpMotherSerial";
         SQL += "(MothSerialID,HHID,MemID,MothSlEvDate,NewMothSl,OldMothSl,MothSlNote,MothVDate,Rnd,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select MothSerialID,HHID,MemID,MothSlEvDate,NewMothSl,OldMothSl,MothSlNote,MothVDate,Rnd,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from MotherSerial";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpSpouseSerial-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpSpouseSerial";
         SQL += "(SpSerialID,HHID,MemID,SpSlEvDate,NewSpSl,OldSpSl,SpSlNote,SpVDate,Rnd,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select SpSerialID,HHID,MemID,SpSlEvDate,NewSpSl,OldSpSl,SpSlNote,SpVDate,Rnd,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from SpouseSerial";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpOccupation-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpOccupation";
         SQL += "(OcpID,HHID,MemID,OcpEvDate,NewOcp,OldOcp,OcpNote,OcpVDate,Rnd,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select OcpID,HHID,MemID,OcpEvDate,NewOcp,OldOcp,OcpNote,OcpVDate,Rnd,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from Occupation";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpRelation-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpRelation";
         SQL += "(RthID,HHID,MemID,RthEvDate,NewRth,OldRth,RthNote,RthVDate,Rnd,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select RthID,HHID,MemID,RthEvDate,NewRth,OldRth,RthNote,RthVDate,Rnd,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from Relation";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpMaritalSts-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpMaritalSts";
         SQL += "(MaritStsID,HHID,MemID,MSEvType,MSEvDate,PrevMS,MSVDate,Rnd,MSNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select MaritStsID,HHID,MemID,MSEvType,MSEvDate,PrevMS,MSVDate,Rnd,MSNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from MaritalSts";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpMemberMove-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpMemberMove";
         SQL += "(MemID,HHID,Active,DSSID,MEnType,MEnDate,MExType,MExDate,Rnd,MemNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select MemID,HHID,Active,DSSID,MEnType,MEnDate,MExType,MExDate,Rnd,MemNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from MemberMove";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpMigration-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpMigration";
         SQL += "(MigrationID,HHID,MemID,MigEvType,MigDate,MigLocType,MigAdminL1,MigAdminL2,MigAdminL3,MigAdminL4,MigAdminLArea,MigCountry,MigReason,MigReasonOth,MigVDate,Rnd,MigNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select MigrationID,HHID,MemID,MigEvType,MigDate,MigLocType,MigAdminL1,MigAdminL2,MigAdminL3,MigAdminL4,MigAdminLArea,MigCountry,MigReason,MigReasonOth,MigVDate,Rnd,MigNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from Migration";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpMovement-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpMovement";
         SQL += "(MovementID,HHID,MemID,MoveEvType,MoveDate,MoveVill,MoveCompound,MoveHH,MoveReason,MoveReasonOth,MoveVDate,Rnd,MoveNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select MovementID,HHID,MemID,MoveEvType,MoveDate,MoveVill,MoveCompound,MoveHH,MoveReason,MoveReasonOth,MoveVDate,Rnd,MoveNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from Movement";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpNotPregnant-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpNotPregnant";
         SQL += "(NotPregID,HHID,MemID,NotPregVDate,PregStatus,NotPregNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select NotPregID,HHID,MemID,NotPregVDate,PregStatus,NotPregNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from NotPregnant";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpLiveBirth-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpLiveBirth";
         SQL += "(LiveBirthID,DeliveryID,HHID,MemID,LBMomID,LBDOB,LBTime,LBTimeType,LBDPlace,LBPlaceOth,LBDoctor,LBNurse,LBMidwife,LBTBA,LBCHW,LBRF,LBOth,LBDk,LBRfs,LBType,LBVDate,Rnd,LBNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select LiveBirthID,DeliveryID,HHID,MemID,LBMomID,LBDOB,LBTime,LBTimeType,LBDPlace,LBPlaceOth,LBDoctor,LBNurse,LBMidwife,LBTBA,LBCHW,LBRF,LBOth,LBDk,LBRfs,LBType,LBVDate,Rnd,LBNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from LiveBirth";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

         //-- -tmpStillBirth-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         SQL = " Insert into tmpStillBirth";
         SQL += "(StillBirthID,DeliveryID,HHID,SBMomID,SBDate,SBDateType,SBTime,SBTimeType,SBType,SBSex,SBPlace,SBPlaceOth,SBDoctor,SBNurse,SBMidwife,SBTBA,SBCHW,SBRltv,SBOth,SBOthSp,SBDk,SBRfs,SBDelType,SBVDate,Rnd,SBNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload)";
         SQL += " Select StillBirthID,DeliveryID,HHID,SBMomID,SBDate,SBDateType,SBTime,SBTimeType,SBType,SBSex,SBPlace,SBPlaceOth,SBDoctor,SBNurse,SBMidwife,SBTBA,SBCHW,SBRltv,SBOth,SBOthSp,SBDk,SBRfs,SBDelType,SBVDate,Rnd,SBNote,isdelete,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload from StillBirth";
         SQL += " where HHID='" + HHID + "'";
         C.SaveData(SQL);

     }

}