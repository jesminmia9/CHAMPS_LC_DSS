
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
 import android.widget.DatePicker;
 import android.widget.EditText;
 import android.widget.ImageButton;
 import android.widget.LinearLayout;
 import android.widget.RadioButton;
 import android.widget.SimpleAdapter;
 import android.widget.Spinner;
 import android.widget.TextView;
 import android.widget.TimePicker;
 import android.graphics.Color;
 import android.view.WindowManager;
 import android.widget.AutoCompleteTextView;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.R;
 import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

 import Utility.*;
 import Common.*;
 import forms_datamodel.tmpTemporaryMigrationIn_DataModel;

 public class Surv_TemporaryMigrationIn extends AppCompatActivity {
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
    LinearLayout secTmpMigrationID;
    View lineTmpMigrationID;
    TextView VlblTmpMigrationID;
    EditText txtTmpMigrationID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secTmpMigVDate;
    View lineTmpMigVDate;
    TextView VlblTmpMigVDate;
    EditText dtpTmpMigVDate;
    LinearLayout secTmpMigVisitorArrivalDate;
    View lineTmpMigVisitorArrivalDate;
    TextView VlblTmpMigVisitorArrivalDate;
    EditText dtpTmpMigVisitorArrivalDate;
    LinearLayout secTmpMigStayMonth;
    View lineTmpMigStayMonth;
    TextView VlblTmpMigStayMonth;
    EditText txtTmpMigStayMonth;
    LinearLayout secTmpMigReside;
    View lineTmpMigReside;
    TextView VlblTmpMigReside;
    Spinner spnTmpMigReside;

     LinearLayout secMigReason;
     View lineMigReason;
     TextView VlblMigReason;
     Spinner spnMigReason;
     LinearLayout secMigReasonOth;
     View lineMigReasonOth;
     TextView VlblMigReasonOth;
     AutoCompleteTextView txtMigReasonOth;

     int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String TMPMIGRATIONID = "";
      String MEM_ID = "";
      String HHID = "";
      String HHNO = "";
      String EV_TYPE = "";
      String ROUND = "";
      String VISIT_DATE = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.temporarymigrationin);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         TMPMIGRATIONID = IDbundle.getString("TmpMigrationID");
         MEM_ID = IDbundle.getString("MemID");
         HHID = IDbundle.getString("HHID");
         HHNO = IDbundle.getString("HHNO");
         EV_TYPE = IDbundle.getString("evtype");
         ROUND = IDbundle.getString("round");
         VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy

         TableName = "tmpTemporaryMigrationIn";
         MODULEID = 53;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Surv_TemporaryMigrationIn.this);
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
        Connection.LocalizeLanguage(Surv_TemporaryMigrationIn.this, MODULEID, LANGUAGEID);


         dtpTmpMigVDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnTmpMigVDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpTmpMigVisitorArrivalDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnTmpMigVisitorArrivalDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables


        DataSearch(TMPMIGRATIONID);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});

        Global.RoleManagement(cmdSave, MySharedPreferences.getValue(this, "userrole"));
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_TemporaryMigrationIn.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         secTmpMigrationID=(LinearLayout)findViewById(R.id.secTmpMigrationID);
         lineTmpMigrationID=(View)findViewById(R.id.lineTmpMigrationID);
         VlblTmpMigrationID=(TextView) findViewById(R.id.VlblTmpMigrationID);
         txtTmpMigrationID=(EditText) findViewById(R.id.txtTmpMigrationID);
         if(TMPMIGRATIONID.length()==0) txtTmpMigrationID.setText(Global.Get_UUID(DEVICEID));
         else txtTmpMigrationID.setText(TMPMIGRATIONID);
         txtTmpMigrationID.setEnabled(false);

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
         if(MEM_ID.length()==0) txtMemID.setText(Global.Get_UUID(DEVICEID));
         else txtMemID.setText(MEM_ID);
         txtMemID.setEnabled(false);

         secTmpMigVDate=(LinearLayout)findViewById(R.id.secTmpMigVDate);
         lineTmpMigVDate=(View)findViewById(R.id.lineTmpMigVDate);
         VlblTmpMigVDate=(TextView) findViewById(R.id.VlblTmpMigVDate);
         dtpTmpMigVDate=(EditText) findViewById(R.id.dtpTmpMigVDate);
         dtpTmpMigVDate.setText(VISIT_DATE);
         secTmpMigVDate.setVisibility(View.GONE);

         secTmpMigVisitorArrivalDate=(LinearLayout)findViewById(R.id.secTmpMigVisitorArrivalDate);
         lineTmpMigVisitorArrivalDate=(View)findViewById(R.id.lineTmpMigVisitorArrivalDate);
         VlblTmpMigVisitorArrivalDate=(TextView) findViewById(R.id.VlblTmpMigVisitorArrivalDate);
         dtpTmpMigVisitorArrivalDate=(EditText) findViewById(R.id.dtpTmpMigVisitorArrivalDate);
         secTmpMigStayMonth=(LinearLayout)findViewById(R.id.secTmpMigStayMonth);
         lineTmpMigStayMonth=(View)findViewById(R.id.lineTmpMigStayMonth);
         VlblTmpMigStayMonth=(TextView) findViewById(R.id.VlblTmpMigStayMonth);
         txtTmpMigStayMonth=(EditText) findViewById(R.id.txtTmpMigStayMonth);
         secTmpMigReside=(LinearLayout)findViewById(R.id.secTmpMigReside);
         lineTmpMigReside=(View)findViewById(R.id.lineTmpMigReside);
         VlblTmpMigReside=(TextView) findViewById(R.id.VlblTmpMigReside);
         spnTmpMigReside=(Spinner) findViewById(R.id.spnTmpMigReside);
         List<String> listTmpMigReside = new ArrayList<String>();
         
         listTmpMigReside.add("");
         listTmpMigReside.add("1-Less than one Month");
         listTmpMigReside.add("2-One Month");
         listTmpMigReside.add("3-Less than two months");
         listTmpMigReside.add("4-Two Months");
         listTmpMigReside.add("5-Less than three months");
         listTmpMigReside.add("6-Three months");
         listTmpMigReside.add("7-Less than four months");
         listTmpMigReside.add("8-More than 4 months");
         spnTmpMigReside.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listTmpMigReside)));

         secMigReason=(LinearLayout)findViewById(R.id.secMigReason);
         lineMigReason=(View)findViewById(R.id.lineMigReason);
         VlblMigReason=(TextView) findViewById(R.id.VlblMigReason);
         spnMigReason=(Spinner) findViewById(R.id.spnMigReason);
         List<String> listMigReason = new ArrayList<String>();

         listMigReason.add("");
         listMigReason.add("01-Work");
         listMigReason.add("02-Education");
         listMigReason.add("03-Health care");
         listMigReason.add("04-Marriage");
         listMigReason.add("05-Environmental/Natural disaster");
         listMigReason.add("06-Displacement");
         listMigReason.add("07-Farming");
         listMigReason.add("08-Moved to a new compound/house");
         listMigReason.add("09-Came/gone with relatives");
         listMigReason.add("10-Back in family");
         listMigReason.add("11-Waiting for marriage");
         listMigReason.add("97-Other");
         listMigReason.add("98-Don't know");
         listMigReason.add("99-Other");
         spnMigReason.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listMigReason)));

         spnMigReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 String spnData = "";
                 if (spnMigReason.getSelectedItem().toString().length() != 0)
                 {
                     spnData = Connection.SelectedSpinnerValue(spnMigReason.getSelectedItem().toString(), "-");
                 }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                     secMigReasonOth.setVisibility(View.VISIBLE);
                     lineMigReasonOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secMigReasonOth.setVisibility(View.GONE);
                     lineMigReasonOth.setVisibility(View.GONE);
                     txtMigReasonOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secMigReasonOth=(LinearLayout)findViewById(R.id.secMigReasonOth);
         lineMigReasonOth=(View)findViewById(R.id.lineMigReasonOth);
         VlblMigReasonOth=(TextView) findViewById(R.id.VlblMigReasonOth);
         txtMigReasonOth=(AutoCompleteTextView) findViewById(R.id.txtMigReasonOth);

     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_TemporaryMigrationIn.this, e.getMessage());
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
         	Connection.MessageBox(Surv_TemporaryMigrationIn.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         tmpTemporaryMigrationIn_DataModel objSave = new tmpTemporaryMigrationIn_DataModel();
         objSave.setTmpMigrationID(txtTmpMigrationID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setMemID(txtMemID.getText().toString());
         objSave.setTmpMigVDate(dtpTmpMigVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpTmpMigVDate.getText().toString()) : dtpTmpMigVDate.getText().toString());
         objSave.setTmpMigVisitorArrivalDate(dtpTmpMigVisitorArrivalDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpTmpMigVisitorArrivalDate.getText().toString()) : dtpTmpMigVisitorArrivalDate.getText().toString());
         objSave.setTmpMigStayMonth(txtTmpMigStayMonth.getText().toString());
         objSave.setTmpMigReside(spnTmpMigReside.getSelectedItemPosition() == 0 ? "" : spnTmpMigReside.getSelectedItem().toString().split("-")[0]);
         objSave.setMigReason(spnMigReason.getSelectedItemPosition() == 0 ? "" : spnMigReason.getSelectedItem().toString().split("-")[0]);
         objSave.setMigReasonOth(txtMigReasonOth.getText().toString());
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

             Bundle IDbundle = new Bundle();
             IDbundle.putString("TmpMigrationID", txtTmpMigrationID.getText().toString());
             IDbundle.putString("MemID", txtMemID.getText().toString());
             IDbundle.putString("HHID", HHID);
             IDbundle.putString("HHNO", HHNO);
             IDbundle.putString("evtype", EV_TYPE);
             IDbundle.putString("evdate", Global.DateConvertYMD(dtpTmpMigVisitorArrivalDate.getText().toString()));
             IDbundle.putString("vdate", Global.DateConvertYMD(dtpTmpMigVDate.getText().toString()));
             IDbundle.putString("round", ROUND);
             IDbundle.putString("dod", "");
             IDbundle.putString("MoID", "");
             IDbundle.putString("LiveBirthID", "");
             IDbundle.putString("MigrationID", "");
            // IDbundle.putSerializable("tmpMigration", objSave);

             Intent intent = new Intent(getApplicationContext(), Surv_Member.class);
             intent.putExtras(IDbundle);
             startActivityForResult(intent, 1);
             finish();
         }
         else{
             Connection.MessageBox(Surv_TemporaryMigrationIn.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_TemporaryMigrationIn.this, e.getMessage());
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
         if(txtTmpMigrationID.getText().toString().length()==0 & secTmpMigrationID.isShown())
           {
             ValidationMsg += "\nRequired field: Migration in/out internal ID.";
             secTmpMigrationID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
         DV = Global.DateValidate(dtpTmpMigVDate.getText().toString());
         if(DV.length()!=0 & secTmpMigVDate.isShown())
           {
             ValidationMsg += "\n1 Required field/Not a valid date format: Visit date.";
             secTmpMigVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpTmpMigVisitorArrivalDate.getText().toString());
         if(DV.length()!=0 & secTmpMigVisitorArrivalDate.isShown())
           {
             ValidationMsg += "\n2 Required field/Not a valid date format: Visitors arrival date.";
             secTmpMigVisitorArrivalDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtTmpMigStayMonth.getText().toString().length()==0 & secTmpMigStayMonth.isShown())
           {
             ValidationMsg += "\n3 Required field: Months of stay.";
             secTmpMigStayMonth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secTmpMigStayMonth.isShown() & (Integer.valueOf(txtTmpMigStayMonth.getText().toString().length()==0 ? "1" : txtTmpMigStayMonth.getText().toString()) < 1 || Integer.valueOf(txtTmpMigStayMonth.getText().toString().length()==0 ? "99" : txtTmpMigStayMonth.getText().toString()) > 99))
           {
             ValidationMsg += "\n3 Value should be between 1 and 99(Months of stay).";
             secTmpMigStayMonth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnTmpMigReside.getSelectedItemPosition()==0  & secTmpMigReside.isShown())
           {
             ValidationMsg += "\n4 Required field: How long will the visitor be residing in this household?.";
             secTmpMigReside.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }

         if(spnMigReason.getSelectedItemPosition()==0  & secMigReason.isShown())
         {
             ValidationMsg += "\nRequired field: What was the reason for the migration?";
             secMigReason.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtMigReasonOth.getText().toString().length()==0 & secMigReasonOth.isShown())
         {
             ValidationMsg += "\nRequired field: Specify Others.";
             secMigReasonOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secTmpMigrationID.setBackgroundColor(Color.WHITE);
             secHHID.setBackgroundColor(Color.WHITE);
             secMemID.setBackgroundColor(Color.WHITE);
             secTmpMigVDate.setBackgroundColor(Color.WHITE);
             secTmpMigVisitorArrivalDate.setBackgroundColor(Color.WHITE);
             secTmpMigStayMonth.setBackgroundColor(Color.WHITE);
             secTmpMigStayMonth.setBackgroundColor(Color.WHITE);
             secTmpMigReside.setBackgroundColor(Color.WHITE);
         secMigReason.setBackgroundColor(Color.WHITE);
         secMigReasonOth.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String TmpMigrationID)
     {
       try
        {     
           RadioButton rb;
            tmpTemporaryMigrationIn_DataModel d = new tmpTemporaryMigrationIn_DataModel();
           String SQL = "Select * from "+ TableName +"  Where TmpMigrationID='"+ TmpMigrationID +"'";
           List<tmpTemporaryMigrationIn_DataModel> data = d.SelectAll(this, SQL);
           for(tmpTemporaryMigrationIn_DataModel item : data){
             txtTmpMigrationID.setText(item.getTmpMigrationID());
             txtHHID.setText(item.getHHID());
             txtMemID.setText(item.getMemID());
             dtpTmpMigVDate.setText(item.getTmpMigVDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getTmpMigVDate()));
             dtpTmpMigVisitorArrivalDate.setText(item.getTmpMigVisitorArrivalDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getTmpMigVisitorArrivalDate()));
             txtTmpMigStayMonth.setText(String.valueOf(item.getTmpMigStayMonth()));
             spnTmpMigReside.setSelection(Global.SpinnerItemPositionAnyLength(spnTmpMigReside, String.valueOf(item.getTmpMigReside())));
               spnMigReason.setSelection(Global.SpinnerItemPositionAnyLength(spnMigReason, String.valueOf(item.getMigReason())));
               txtMigReasonOth.setText(item.getMigReasonOth());

           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_TemporaryMigrationIn.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpTmpMigVDate);
      if (VariableID.equals("btnTmpMigVDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpTmpMigVDate);
      }
      else if (VariableID.equals("btnTmpMigVisitorArrivalDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpTmpMigVisitorArrivalDate);
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