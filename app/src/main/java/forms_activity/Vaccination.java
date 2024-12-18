
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
 import android.text.Editable;
 import android.text.TextWatcher;
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

 import forms_datamodel.Member_DataModel;
 import forms_datamodel.Vaccination_DataModel;
 import Utility.*;
 import Common.*;

 public class Vaccination extends AppCompatActivity {
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
    LinearLayout secVaccID;
    View lineVaccID;
    TextView VlblVaccID;
    EditText txtVaccID;
    LinearLayout seclbl01;
    View linelbl01;
    LinearLayout seclbl02;
    View linelbl02;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMoSlNo;
    View lineMoSlNo;
    TextView VlblMoSlNo;
    EditText txtMoSlNo;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secVaccCode;
    View lineVaccCode;
    TextView VlblVaccCode;
    EditText txtVaccCode;
    LinearLayout secVaccCodeOth1;
    View lineVaccCodeOth1;
    TextView VlblVaccCodeOth1;
    EditText txtVaccCodeOth1;
    LinearLayout secVaccCodeOth2;
    View lineVaccCodeOth2;
    TextView VlblVaccCodeOth2;
    EditText txtVaccCodeOth2;
    LinearLayout secResponse;
    View lineResponse;
    TextView VlblResponse;
    RadioGroup rdogrpResponse;
    RadioButton rdoResponse1;
    RadioButton rdoResponse2;
    RadioButton rdoResponse3;
    RadioButton rdoResponse4;
    RadioButton rdoResponse5;
    RadioButton rdoResponse6;
    LinearLayout secResponseOth;
    View lineResponseOth;
    TextView VlblResponseOth;
    EditText txtResponseOth;
    LinearLayout secVaccDate;
    View lineVaccDate;
    TextView VlblVaccDate;
    EditText dtpVaccDate;
     LinearLayout secVaccDateDK;
     View lineVaccDateDK;
     TextView VlblVaccDateDK;
     CheckBox chkVaccDateDK;

     TextView lblHlbl13;

     int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String VACCID = "";
      String MEMID = "";
      String HHID = "";
      String VACCCODE = "";
      String VacciName = "";
      String VISIT_DATE = "";
      String BIRTH_DATE = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.vaccination);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         VACCID = IDbundle.getString("VaccID");
         HHID = IDbundle.getString("HHID");
         MEMID = IDbundle.getString("MemID");
         VACCCODE = IDbundle.getString("VaccCode");
         VacciName = IDbundle.getString("VaccName");
         VISIT_DATE = IDbundle.getString("visitdate"); //dd/mm/yyyy
         BIRTH_DATE = IDbundle.getString("bdate"); //yyyy-mm-dd

         TableName = "Vaccination";
         MODULEID = 17;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);
         lblHlbl13 = (TextView)findViewById(R.id.lblHlbl13);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Vaccination.this);
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

         lblHlbl13.setText(VacciName);

        Initialization();
        Connection.LocalizeLanguage(Vaccination.this, MODULEID, LANGUAGEID);


         dtpVaccDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnVaccDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables
         secResponseOth.setVisibility(View.GONE);
         lineResponseOth.setVisibility(View.GONE);

//         secVaccCodeOth1.setVisibility(View.GONE);
//         lineVaccCodeOth1.setVisibility(View.GONE);
         secVaccCodeOth2.setVisibility(View.GONE);
         lineVaccCodeOth2.setVisibility(View.GONE);


        DataSearch(VACCID,VACCCODE);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});

        //Global.RoleManagement(cmdSave, MySharedPreferences.getValue(this, "userrole"));
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Vaccination.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         secVaccID=(LinearLayout)findViewById(R.id.secVaccID);
         lineVaccID=(View)findViewById(R.id.lineVaccID);
         VlblVaccID=(TextView) findViewById(R.id.VlblVaccID);
         txtVaccID=(EditText) findViewById(R.id.txtVaccID);
//         txtVaccID.setText(VACCID);
//         txtVaccID.setEnabled(false);
         if(VACCID.length()==0) txtVaccID.setText(Global.Get_UUID(DEVICEID));
         else txtVaccID.setText(VACCID);
         txtVaccID.setEnabled(false);

         seclbl01=(LinearLayout)findViewById(R.id.seclbl01);
         linelbl01=(View)findViewById(R.id.linelbl01);
         seclbl02=(LinearLayout)findViewById(R.id.seclbl02);
         linelbl02=(View)findViewById(R.id.linelbl02);
         secHHID=(LinearLayout)findViewById(R.id.secHHID);
         lineHHID=(View)findViewById(R.id.lineHHID);
         VlblHHID=(TextView) findViewById(R.id.VlblHHID);
         txtHHID=(EditText) findViewById(R.id.txtHHID);
         txtHHID.setText(HHID);
         txtHHID.setEnabled(false);

         String MotherSL = C.ReturnSingleValue("select MoNo from Member where MemID='" + MEMID + "'");
         secMoSlNo=(LinearLayout)findViewById(R.id.secMoSlNo);
         lineMoSlNo=(View)findViewById(R.id.lineMoSlNo);
         VlblMoSlNo=(TextView) findViewById(R.id.VlblMoSlNo);
         txtMoSlNo=(EditText) findViewById(R.id.txtMoSlNo);
         txtMoSlNo.setText(MotherSL);
         txtMoSlNo.setEnabled(false);

         secMemID=(LinearLayout)findViewById(R.id.secMemID);
         lineMemID=(View)findViewById(R.id.lineMemID);
         VlblMemID=(TextView) findViewById(R.id.VlblMemID);
         txtMemID=(EditText) findViewById(R.id.txtMemID);
         txtMemID.setText(MEMID);

         secVaccCode=(LinearLayout)findViewById(R.id.secVaccCode);
         lineVaccCode=(View)findViewById(R.id.lineVaccCode);
         VlblVaccCode=(TextView) findViewById(R.id.VlblVaccCode);
         txtVaccCode=(EditText) findViewById(R.id.txtVaccCode);
         txtVaccCode.setText(VACCCODE);
         txtVaccCode.setEnabled(false);

         secVaccCodeOth1=(LinearLayout)findViewById(R.id.secVaccCodeOth1);
         lineVaccCodeOth1=(View)findViewById(R.id.lineVaccCodeOth1);
         VlblVaccCodeOth1=(TextView) findViewById(R.id.VlblVaccCodeOth1);
         txtVaccCodeOth1=(EditText) findViewById(R.id.txtVaccCodeOth1);
         secVaccCodeOth2=(LinearLayout)findViewById(R.id.secVaccCodeOth2);
         lineVaccCodeOth2=(View)findViewById(R.id.lineVaccCodeOth2);
         VlblVaccCodeOth2=(TextView) findViewById(R.id.VlblVaccCodeOth2);
         txtVaccCodeOth2=(EditText) findViewById(R.id.txtVaccCodeOth2);
         secResponse=(LinearLayout)findViewById(R.id.secResponse);
         lineResponse=(View)findViewById(R.id.lineResponse);
         VlblResponse = (TextView) findViewById(R.id.VlblResponse);
         rdogrpResponse = (RadioGroup) findViewById(R.id.rdogrpResponse);
         rdoResponse1 = (RadioButton) findViewById(R.id.rdoResponse1);
         rdoResponse2 = (RadioButton) findViewById(R.id.rdoResponse2);
         rdoResponse3 = (RadioButton) findViewById(R.id.rdoResponse3);
         rdoResponse4 = (RadioButton) findViewById(R.id.rdoResponse4);
         rdoResponse5 = (RadioButton) findViewById(R.id.rdoResponse5);
         rdoResponse6 = (RadioButton) findViewById(R.id.rdoResponse6);
         rdogrpResponse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpResponse = new String[] {"0","1","2","8","9","7"};
             for (int i = 0; i < rdogrpResponse.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpResponse.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpResponse[i];
             }
             if(rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("7"))
             {
                 secVaccDate.setVisibility(View.VISIBLE);
                 lineVaccDate.setVisibility(View.VISIBLE);
                 secVaccDateDK.setVisibility(View.VISIBLE);
                 lineVaccDateDK.setVisibility(View.VISIBLE);
             }
             else {
                 secVaccDate.setVisibility(View.GONE);
                 lineVaccDate.setVisibility(View.GONE);
                 dtpVaccDate.setText("");
                 secVaccDateDK.setVisibility(View.GONE);
                 lineVaccDateDK.setVisibility(View.GONE);
             }
             if(rbData.equalsIgnoreCase("7"))
             {
                 secResponseOth.setVisibility(View.VISIBLE);
                 lineResponseOth.setVisibility(View.VISIBLE);

             }
             else
             {
                 secResponseOth.setVisibility(View.GONE);
                 lineResponseOth.setVisibility(View.GONE);
                 txtResponseOth.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secResponseOth=(LinearLayout)findViewById(R.id.secResponseOth);
         lineResponseOth=(View)findViewById(R.id.lineResponseOth);
         VlblResponseOth=(TextView) findViewById(R.id.VlblResponseOth);
         txtResponseOth=(EditText) findViewById(R.id.txtResponseOth);
         secVaccDate=(LinearLayout)findViewById(R.id.secVaccDate);
         lineVaccDate=(View)findViewById(R.id.lineVaccDate);
         VlblVaccDate=(TextView) findViewById(R.id.VlblVaccDate);
         dtpVaccDate=(EditText) findViewById(R.id.dtpVaccDate);
         dtpVaccDate.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 if(dtpVaccDate.getText().toString().length()>0){
                     chkVaccDateDK.setChecked(false);
                 }
             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });
         secVaccDateDK=(LinearLayout)findViewById(R.id.secVaccDateDK);
         lineVaccDateDK=(View)findViewById(R.id.lineVaccDateDK);
         VlblVaccDateDK=(TextView) findViewById(R.id.VlblVaccDateDK);
         chkVaccDateDK=(CheckBox) findViewById(R.id.chkVaccDateDK);

         if (VACCCODE.equals("33") || VACCCODE.equals("34")){
             secVaccCodeOth1.setVisibility(View.VISIBLE);
             lineVaccCodeOth1.setVisibility(View.VISIBLE);
         }else {
             secVaccCodeOth1.setVisibility(View.GONE);
             lineVaccCodeOth1.setVisibility(View.GONE);
             txtVaccCodeOth1.setText("");
         }

         chkVaccDateDK.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 if (((CheckBox) v).isChecked()) {
                     //dtpVaccDate.setEnabled(false);
                     dtpVaccDate.setText("");
                 }
                 else {
                     //dtpVaccDate.setEnabled(true);

                 }
             }
         });

     }
     catch(Exception  e)
     {
         Connection.MessageBox(Vaccination.this, e.getMessage());
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
         	Connection.MessageBox(Vaccination.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         Vaccination_DataModel objSave = new Vaccination_DataModel();
         objSave.setVaccID(txtVaccID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setMoSlNo(txtMoSlNo.getText().toString());
         objSave.setMemID(txtMemID.getText().toString());
         objSave.setVaccCode(txtVaccCode.getText().toString());
         objSave.setVaccCodeOth1(txtVaccCodeOth1.getText().toString());
         objSave.setVaccCodeOth2(txtVaccCodeOth2.getText().toString());
         String[] d_rdogrpResponse = new String[] {"0","1","2","8","9","7"};
         objSave.setResponse("");
         for (int i = 0; i < rdogrpResponse.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpResponse.getChildAt(i);
             if (rb.isChecked()) objSave.setResponse(d_rdogrpResponse[i]);
         }

         objSave.setResponseOth(txtResponseOth.getText().toString());
         objSave.setVaccDate(dtpVaccDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpVaccDate.getText().toString()) : dtpVaccDate.getText().toString());

         objSave.setVaccDateDK((chkVaccDateDK.isChecked() ? "1" : (secVaccDateDK.isShown() ? "2" : "")));

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

             Toast.makeText(Vaccination.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
             finish();
         }
         else{
             Connection.MessageBox(Vaccination.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Vaccination.this, e.getMessage());
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
         if(txtVaccID.getText().toString().length()==0 & secVaccID.isShown())
           {
             ValidationMsg += "\nRequired field: Internal Vaccination Id.";
             secVaccID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
           {
             ValidationMsg += "\nRequired field: Household ID.";
             secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMoSlNo.getText().toString().length()==0 & secMoSlNo.isShown())
           {
             ValidationMsg += "\nRequired field: Mothers serial No.";
             secMoSlNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMemID.getText().toString().length()==0 & secMemID.isShown())
           {
             ValidationMsg += "\nRequired field: Member ID.";
             secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtVaccCode.getText().toString().length()==0 & secVaccCode.isShown())
           {
             ValidationMsg += "\nRequired field: Vaccine Code.";
             secVaccCode.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtVaccCodeOth1.getText().toString().length()==0 & secVaccCodeOth1.isShown())
           {
             ValidationMsg += "\nRequired field: Other, specify.";
             secVaccCodeOth1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtVaccCodeOth2.getText().toString().length()==0 & secVaccCodeOth2.isShown())
           {
             ValidationMsg += "\nRequired field: Other, specify.";
             secVaccCodeOth2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoResponse1.isChecked() & !rdoResponse2.isChecked() & !rdoResponse3.isChecked() & !rdoResponse4.isChecked() & !rdoResponse5.isChecked() & !rdoResponse6.isChecked() & secResponse.isShown())
           {
             ValidationMsg += "\nRequired field: Primary Schedule Vaccine Response. GIVE APPLICABLE RESPONSES AND COPY THE DATE.";
             secResponse.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtResponseOth.getText().toString().length()==0 & secResponseOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other specify.";
             secResponseOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpVaccDate.getText().toString());
         if(!chkVaccDateDK.isChecked() & DV.length()!=0 & secVaccDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Date vaccine was administered.";
             secVaccDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
//         if(!rdoVaccDateDK1.isChecked() & secVaccDateDK.isShown())
//           {
//             ValidationMsg += "\nRequired field: Primary Schedule Vaccine date- Dont know 88.";
//             secVaccDateDK.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
         if (!chkVaccDateDK.isChecked() && DV.length()==0 && secVaccDate.isShown()) {
             int datediff = Global.DateDifferenceDays(dtpVaccDate.getText().toString(), Global.DateConvertDMY(BIRTH_DATE));
             if(datediff<0){
                 ValidationMsg += "\nDate vaccine was administered date must be equal or greater than date of birth.";
                 secVaccDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
             // check vaccine date and member date of birth
             /*Member_DataModel d = new Member_DataModel();
             String SQL = "Select * from "+ Member_DataModel.TableName +"  Where HHID='"+ HHID +"' and MemID='"+MEMID+"'  and isdelete='2'";
             List<Member_DataModel> data = d.SelectAll(this, SQL);
             if(data != null && !data.isEmpty()) {
                 Member_DataModel memberDataModel = data.get(0);
                 int memberAge = -1;
                 if (memberDataModel.getBDate() != null && !memberDataModel.getBDate().isEmpty()){
                     //memberAge = Global.DateDifferenceDays(Global.DateConvertDMY(VISIT_DATE),Global.DateConvertDMY(memberDataModel.getBDate()));
                     memberAge = Global.DateDifferenceDays(dtpVaccDate.getText().toString(),Global.DateConvertDMY(memberDataModel.getBDate()));
                 }
                 if (memberAge < 0){
                 ValidationMsg += "\nPrimary Schedule Vaccine date must be greater than date of birth.";
                 secVaccDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
                 }
             }*/

             /*int dateDiff = -1;
             dateDiff = Global.DateDifferenceDays(Global.DateNowDMY(),dtpVaccDate.getText().toString());
             if (dateDiff < 0){
                 ValidationMsg += "\nPrimary Schedule Vaccine date must be less than or equal to current date.";
                 secVaccDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }*/

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
             secVaccID.setBackgroundColor(Color.WHITE);
             secHHID.setBackgroundColor(Color.WHITE);
             secMoSlNo.setBackgroundColor(Color.WHITE);
             secMemID.setBackgroundColor(Color.WHITE);
             secVaccCode.setBackgroundColor(Color.WHITE);
             secVaccCodeOth1.setBackgroundColor(Color.WHITE);
             secVaccCodeOth2.setBackgroundColor(Color.WHITE);
             secResponse.setBackgroundColor(Color.WHITE);
             secResponseOth.setBackgroundColor(Color.WHITE);
             secVaccDate.setBackgroundColor(Color.WHITE);
             secVaccDateDK.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {

     }
 }

 private void DataSearch(String MemID,String VaccCode)
     {
       try
        {     
           RadioButton rb;
           Vaccination_DataModel d = new Vaccination_DataModel();
           String SQL = "Select * from "+ TableName +"  Where MemID='"+ MEMID +"' and VaccCode='"+ VACCCODE +"'";
           List<Vaccination_DataModel> data = d.SelectAll(this, SQL);
           for(Vaccination_DataModel item : data){
             txtVaccID.setText(item.getVaccID());
             txtHHID.setText(item.getHHID());
             txtMoSlNo.setText(item.getMoSlNo());
             txtMemID.setText(item.getMemID());
             txtVaccCode.setText(item.getVaccCode());
             txtVaccCodeOth1.setText(item.getVaccCodeOth1());
             txtVaccCodeOth2.setText(item.getVaccCodeOth2());
             String[] d_rdogrpResponse = new String[] {"0","1","2","8","9","7"};
             for (int i = 0; i < d_rdogrpResponse.length; i++)
             {
                 if (String.valueOf(item.getResponse()).equals(String.valueOf(d_rdogrpResponse[i])))
                 {
                     rb = (RadioButton)rdogrpResponse.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtResponseOth.setText(item.getResponseOth());
             dtpVaccDate.setText(item.getVaccDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getVaccDate()));
               if(String.valueOf(item.getVaccDateDK()).equals("1"))
               {
                   chkVaccDateDK.setChecked(true);
               }
               else if(String.valueOf(item.getVaccDateDK()).equals("2"))
               {
                   chkVaccDateDK.setChecked(false);
               }
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Vaccination.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpVaccDate);
      if (VariableID.equals("btnVaccDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpVaccDate);
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