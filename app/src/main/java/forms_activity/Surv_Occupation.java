
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
 import android.widget.Spinner;
 import android.widget.TextView;
 import android.widget.TimePicker;
 import android.graphics.Color;
 import android.view.WindowManager;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.R;
 import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

 import Utility.*;
 import Common.*;
 import forms_datamodel.tmpOccupation_DataModel;

 public class Surv_Occupation extends AppCompatActivity {
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
    LinearLayout secOcpID;
    View lineOcpID;
    TextView VlblOcpID;
    EditText txtOcpID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secOcpEvDate;
    View lineOcpEvDate;
    TextView VlblOcpEvDate;
    EditText dtpOcpEvDate;
    LinearLayout secNewOcp;
    View lineNewOcp;
    TextView VlblNewOcp;
    Spinner spnNewOcp;
     LinearLayout secNewEmpStat;
     View lineNewEmpStat;
     TextView VlblNewEmpStat;
     Spinner spnNewEmpStat;
    LinearLayout secOldOcp;
    View lineOldOcp;
    TextView VlblOldOcp;
     Spinner spnOldOcp;
     LinearLayout secOldEmpStat;
     View lineOldEmpStat;
     TextView VlblOldEmpStat;
     Spinner spnOldEmpStat;
    LinearLayout secOcpNote;
    View lineOcpNote;
    TextView VlblOcpNote;
    EditText txtOcpNote;
     LinearLayout secOcpVDate;
     View lineOcpVDate;
     TextView VlblOcpVDate;
     EditText dtpOcpVDate;

     LinearLayout secOcpNote1;
     View lineOcpNote1;
     TextView lblEvCode;
     LinearLayout secRnd;
     View lineRnd;
     TextView VlblRnd;
     EditText txtRnd;

      int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String OCPID = "";
      String MEM_ID = "";
      String HHID = "";
      String EV_TYPE = "";
      String ROUND = "";
      String VISIT_DATE = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.surv_occupation);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         OCPID = IDbundle.getString("OcpID");
         MEM_ID = IDbundle.getString("MemID");
         HHID = IDbundle.getString("HHID");
         EV_TYPE = IDbundle.getString("evtype");
         ROUND = IDbundle.getString("round");
         VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy

         TableName = "tmpOccupation";
         MODULEID = 36;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Occupation.this);
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
        Connection.LocalizeLanguage(Surv_Occupation.this, MODULEID, LANGUAGEID);

         String POcp = C.ReturnSingleValue("Select Ocp from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
         if(POcp == null || POcp.length()==0) spnOldOcp.setSelection(1);
         else spnOldOcp.setSelection(Global.SpinnerItemPositionAnyLength(spnOldOcp, POcp));


         String PEmp = C.ReturnSingleValue("Select Employ from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
         if(PEmp == null || PEmp.length()==0) spnOldEmpStat.setSelection(1);
         else spnOldEmpStat.setSelection(Global.SpinnerItemPositionAnyLength(spnOldEmpStat, PEmp));


        /*if(LANGUAGEID == 4){
            spnNewOcp.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_OCCUPATION())));
            spnOldOcp.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_OCCUPATION())));
            String POcp = C.ReturnSingleValue("Select Ocp from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            spnOldOcp.setSelection(Global.SpinnerItemPositionAnyLength(spnOldOcp, POcp));

            spnOldEmpStat.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_Employment())));
            String PEmp = C.ReturnSingleValue("Select Employ from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
            spnOldEmpStat.setSelection(Global.SpinnerItemPositionAnyLength(spnOldEmpStat, PEmp));
        }*/



         dtpOcpEvDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnOcpEvDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpOcpVDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     VariableID = "btnOcpVDate"; showDialog(DATE_DIALOG);
                     return true;
                 }
                 return false;
             }
         });


         //Hide all skip variables


        DataSearch(OCPID);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Occupation.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         lblEvCode = findViewById(R.id.lblEvCode);
         lblEvCode.setText(EV_TYPE);

         secOcpID=(LinearLayout)findViewById(R.id.secOcpID);
         lineOcpID=(View)findViewById(R.id.lineOcpID);
         VlblOcpID=(TextView) findViewById(R.id.VlblOcpID);
         txtOcpID=(EditText) findViewById(R.id.txtOcpID);
//         txtOcpID.setText(OCPID);
//         txtOcpID.setEnabled(false);

         if(OCPID.length()==0) txtOcpID.setText(Global.Get_UUID(DEVICEID));
         else txtOcpID.setText(OCPID);
         txtOcpID.setEnabled(false);

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

         secOcpEvDate=(LinearLayout)findViewById(R.id.secOcpEvDate);
         lineOcpEvDate=(View)findViewById(R.id.lineOcpEvDate);
         VlblOcpEvDate=(TextView) findViewById(R.id.VlblOcpEvDate);
         dtpOcpEvDate=(EditText) findViewById(R.id.dtpOcpEvDate);
         secNewOcp=(LinearLayout)findViewById(R.id.secNewOcp);
         lineNewOcp=(View)findViewById(R.id.lineNewOcp);
         VlblNewOcp=(TextView) findViewById(R.id.VlblNewOcp);
         spnNewOcp=(Spinner) findViewById(R.id.spnNewOcp);
         secNewEmpStat=(LinearLayout)findViewById(R.id.secNewEmpStat);
         lineNewEmpStat=(View)findViewById(R.id.lineNewEmpStat);
         VlblNewEmpStat=(TextView) findViewById(R.id.VlblNewEmpStat);
         spnNewEmpStat=(Spinner) findViewById(R.id.spnNewEmpStat);


         secOldOcp=(LinearLayout)findViewById(R.id.secOldOcp);
         lineOldOcp=(View)findViewById(R.id.lineOldOcp);
         VlblOldOcp=(TextView) findViewById(R.id.VlblOldOcp);
         spnOldOcp=(Spinner) findViewById(R.id.spnOldOcp);
//         spnOldOcp.setEnabled(false);
         secOldEmpStat=(LinearLayout)findViewById(R.id.secOldEmpStat);
         lineOldEmpStat=(View)findViewById(R.id.lineOldEmpStat);
         VlblOldEmpStat=(TextView) findViewById(R.id.VlblOldEmpStat);
         spnOldEmpStat=(Spinner) findViewById(R.id.spnOldEmpStat);
//         spnOldEmpStat.setEnabled(false);


         //spnNewOcp.setAdapter(C.getArrayAdapter("Select distinct Ocp from tmpMember order by Ocp"));
         spnNewOcp.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_OCCUPATION())));
         spnNewEmpStat.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_Employment())));

         spnOldOcp.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_OCCUPATION())));
         spnOldEmpStat.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_Employment())));
         spnOldOcp.setEnabled(false);
         spnOldEmpStat.setEnabled(false);

         /*List<String> listOcp = new ArrayList<String>();

         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1) ||
                 ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2))
         {
             listOcp.add("");
             listOcp.add("01-None");
             listOcp.add("11-Farmer");
             listOcp.add("02-Student");
             listOcp.add("33-Business man/woman");
             listOcp.add("21-Artisan");
             listOcp.add("35-Private sector employee");
             listOcp.add("36-Civil/Public servant");
             listOcp.add("06-Retired");
             listOcp.add("97-Other specify");
             listOcp.add("98-Don’t know");
             listOcp.add("99-Refused to respond");

             spnNewOcp.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listOcp)));
             spnOldOcp.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listOcp)));
             spnOldEmpStat.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_Employment())));
             String POcp = C.ReturnSingleValue("Select Ocp from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
             spnOldOcp.setSelection(Global.SpinnerItemPositionAnyLength(spnOldOcp, POcp));
             String PEmp = C.ReturnSingleValue("Select Employ from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
             spnOldEmpStat.setSelection(Global.SpinnerItemPositionAnyLength(spnOldEmpStat, PEmp));
         }
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1))
         {
             listOcp.add("");
             listOcp.add("01-None");
             listOcp.add("11-Farmer");
             listOcp.add("02-Student");
             listOcp.add("33-Business man/woman");
             listOcp.add("21-Artisan");
             listOcp.add("35-Private sector employee");
             listOcp.add("36-Civil/Public servant");
             listOcp.add("06-Retired");
             listOcp.add("97-Other specify");
             listOcp.add("98-Don’t know");
             listOcp.add("99-Refused to respond");

             spnNewOcp.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listOcp)));
             spnOldOcp.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listOcp)));
             spnOldEmpStat.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_Employment())));
             String POcp = C.ReturnSingleValue("Select Ocp from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
             spnOldOcp.setSelection(Global.SpinnerItemPositionAnyLength(spnOldOcp, POcp));
             String PEmp = C.ReturnSingleValue("Select Employ from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
             spnOldEmpStat.setSelection(Global.SpinnerItemPositionAnyLength(spnOldEmpStat, PEmp));
         }
         else {
             listOcp.add("");
             listOcp.add("01-None");
             listOcp.add("11-Farmer");
             listOcp.add("02-Student");
             listOcp.add("33-Business man/woman");
             listOcp.add("21-Artisan");
             listOcp.add("35-Private sector employee");
             listOcp.add("36-Civil/Public servant");
             listOcp.add("06-Retired");
             listOcp.add("97-Other specify");
             listOcp.add("98-Don’t know");
             listOcp.add("99-Refused to respond");

             spnNewOcp.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listOcp)));
             spnOldOcp.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listOcp)));
             spnOldEmpStat.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_Employment())));
             String POcp = C.ReturnSingleValue("Select Ocp from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
             spnOldOcp.setSelection(Global.SpinnerItemPositionAnyLength(spnOldOcp, POcp));
             String PEmp = C.ReturnSingleValue("Select Employ from tmpMember where HHID='" + HHID + "' and MemID='" + MEM_ID + "'");
             spnOldEmpStat.setSelection(Global.SpinnerItemPositionAnyLength(spnOldEmpStat, PEmp));
         }*/

         secOcpNote=(LinearLayout)findViewById(R.id.secOcpNote);
         lineOcpNote=(View)findViewById(R.id.lineOcpNote);
         VlblOcpNote=(TextView) findViewById(R.id.VlblOcpNote);
         txtOcpNote=(EditText) findViewById(R.id.txtOcpNote);

         secOcpNote1=(LinearLayout)findViewById(R.id.secOcpNote1);
         lineOcpNote1=(View)findViewById(R.id.lineOcpNote1);
         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         txtRnd.setText(ROUND);
         txtRnd.setEnabled(false);
         secOcpVDate=(LinearLayout)findViewById(R.id.secOcpVDate);
         lineOcpVDate=(View)findViewById(R.id.lineOcpVDate);
         VlblOcpVDate=(TextView) findViewById(R.id.VlblOcpVDate);
         dtpOcpVDate=(EditText) findViewById(R.id.dtpOcpVDate);
         dtpOcpVDate.setText(VISIT_DATE);
         secOcpVDate.setVisibility(View.GONE);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Occupation.this, e.getMessage());
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
         	Connection.MessageBox(Surv_Occupation.this, ValidationMSG);
         	return;
         }

         String SQL = "";
         RadioButton rb;

         tmpOccupation_DataModel objSave = new tmpOccupation_DataModel();
         objSave.setOcpID(txtOcpID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setMemID(txtMemID.getText().toString());
         objSave.setOcpEvDate(dtpOcpEvDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpOcpEvDate.getText().toString()) : dtpOcpEvDate.getText().toString());
         objSave.setNewOcp(spnNewOcp.getSelectedItemPosition() == 0 ? "" : spnNewOcp.getSelectedItem().toString().split("-")[0]);
         objSave.setNewEmpStat(spnNewEmpStat.getSelectedItemPosition() == 0 ? "" : spnNewEmpStat.getSelectedItem().toString().split("-")[0]);
         objSave.setOldOcp(spnOldOcp.getSelectedItemPosition() == 0 ? "" : spnOldOcp.getSelectedItem().toString().split("-")[0]);
         objSave.setOldEmpStat(spnOldEmpStat.getSelectedItemPosition() == 0 ? "" : spnOldEmpStat.getSelectedItem().toString().split("-")[0]);
         objSave.setOcpNote(txtOcpNote.getText().toString());
         objSave.setOcpVDate(dtpOcpVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpOcpVDate.getText().toString()) : dtpOcpVDate.getText().toString());
         objSave.setRnd(txtRnd.getText().toString());
         objSave.setStartTime(STARTTIME);
         objSave.setEndTime(g.CurrentTime24());
         objSave.setDeviceID(DEVICEID);
         objSave.setEntryUser(ENTRYUSER); //from data entry user list
         objSave.setLat(MySharedPreferences.getValue(this, "lat"));
         objSave.setLon(MySharedPreferences.getValue(this, "lon"));

         String status = objSave.SaveUpdateData(this);
         if(status.length()==0) {
             C.SaveData("Update tmpMember set Upload='2',Ocp='"+ (spnNewOcp.getSelectedItemPosition() == 0 ? "0" : spnNewOcp.getSelectedItem().toString().split("-")[0]) +"', Employ='"+ (spnNewEmpStat.getSelectedItemPosition() == 0 ? "0" : spnNewEmpStat.getSelectedItem().toString().split("-")[0]) +"',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where MemID='"+ MEM_ID +"' " );
             C.SaveData("Update tmpMemberMove set Upload='2',Ocp='"+ (spnNewOcp.getSelectedItemPosition() == 0 ? "0" : spnNewOcp.getSelectedItem().toString().split("-")[0]) +"', Employ='"+ (spnNewEmpStat.getSelectedItemPosition() == 0 ? "0" : spnNewEmpStat.getSelectedItem().toString().split("-")[0]) +"',modifydate='"+ Global.DateTimeNowYMDHMS() +"' where HHID='"+ HHID +"' and MemID='"+ MEM_ID +"' " );

             Intent returnIntent = new Intent();
             returnIntent.putExtra("res", "");
             setResult(Activity.RESULT_OK, returnIntent);

             Toast.makeText(getApplicationContext(), "Save Successfully...", Toast.LENGTH_SHORT).show();
             finish();
         }
         else{
             Connection.MessageBox(Surv_Occupation.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Occupation.this, e.getMessage());
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
         if(txtOcpID.getText().toString().length()==0 & secOcpID.isShown())
           {
             ValidationMsg += "\nRequired field: Occupation  Internal ID.";
             secOcpID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
         DV = Global.DateValidate(dtpOcpEvDate.getText().toString());
         if(DV.length()!=0 & secOcpEvDate.isShown())
           {
             ValidationMsg += "\n1. Required field/Not a valid date format: When has this member’s occupation changed?.";
             secOcpEvDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnNewOcp.getSelectedItemPosition()==0  & secNewOcp.isShown())
           {
             ValidationMsg += "\n2. Required field: What is your current occupation?.";
             secNewOcp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnNewEmpStat.getSelectedItemPosition()==0  & secNewEmpStat.isShown())
           {
             ValidationMsg += "\n2. Required field: What is your current Employment Status?.";
             secNewEmpStat.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
        /* if(spnOldOcp.getSelectedItemPosition()==0  & secOldOcp.isShown())
         {
             ValidationMsg += "\n3. Required field: What was the previous occupation?.";
             secOldOcp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
         DV = Global.DateValidate(dtpOcpVDate.getText().toString());
         if(DV.length()!=0 & secOcpVDate.isShown())
         {
             ValidationMsg += "\nRequired field/Not a valid date format: Visit Date.";
             secOcpVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
//         if(txtOcpNote.getText().toString().length()==0 & secOcpNote.isShown())
//           {
//             ValidationMsg += "\nRequired field: Note.";
//             secOcpNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
         String NewOcp = spnNewOcp.getSelectedItem().toString().split("-")[0];
         String NewEmpStat = spnNewEmpStat.getSelectedItem().toString().split("-")[0];
         String PrvOcp = spnOldOcp.getSelectedItem().toString().split("-")[0];
         String PrvEmpStat = spnOldEmpStat.getSelectedItem().toString().split("-")[0];

         if (NewOcp.equals(PrvOcp)){
             ValidationMsg += "\n Current occupation and Previous occupation should not same";
             secOldOcp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             secNewOcp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
//         if (NewEmpStat.equals(PrvEmpStat)){
//             ValidationMsg += "\n Current Employment and Previous occupation should not same";
//             secOldOcp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//             secNewOcp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//         }
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
             secOcpID.setBackgroundColor(Color.WHITE);
             secHHID.setBackgroundColor(Color.WHITE);
             secMemID.setBackgroundColor(Color.WHITE);
             secOcpEvDate.setBackgroundColor(Color.WHITE);
             secNewOcp.setBackgroundColor(Color.WHITE);
             secNewEmpStat.setBackgroundColor(Color.WHITE);
             secOldOcp.setBackgroundColor(Color.WHITE);
             secOldEmpStat.setBackgroundColor(Color.WHITE);
             secOcpNote.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
             secOcpVDate.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String OcpID)
     {
       try
        {
           RadioButton rb;
           tmpOccupation_DataModel d = new tmpOccupation_DataModel();
           String SQL = "Select * from "+ TableName +"  Where OcpID='"+ OcpID +"'";
           List<tmpOccupation_DataModel> data = d.SelectAll(this, SQL);
           for(tmpOccupation_DataModel item : data){
             txtOcpID.setText(item.getOcpID());
             txtHHID.setText(item.getHHID());
             txtMemID.setText(item.getMemID());
             dtpOcpEvDate.setText(item.getOcpEvDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getOcpEvDate()));
             spnNewOcp.setSelection(Global.SpinnerItemPositionAnyLength(spnNewOcp, String.valueOf(item.getNewOcp())));
             spnNewEmpStat.setSelection(Global.SpinnerItemPositionAnyLength(spnNewEmpStat, String.valueOf(item.getNewEmpStat())));
             spnOldOcp.setSelection(Global.SpinnerItemPositionAnyLength(spnOldOcp, String.valueOf(item.getOldOcp())));
             spnOldEmpStat.setSelection(Global.SpinnerItemPositionAnyLength(spnOldEmpStat, String.valueOf(item.getOldEmpStat())));
             txtOcpNote.setText(item.getOcpNote());
             txtRnd.setText(String.valueOf(item.getRnd()));
             dtpOcpVDate.setText(item.getOcpVDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getOcpVDate()));
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Occupation.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpOcpEvDate);
      if (VariableID.equals("btnOcpEvDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpOcpEvDate);
      }
      else if (VariableID.equals("btnOcpVDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpOcpVDate);
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