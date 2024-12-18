
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
 import android.util.Log;
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
 import android.widget.RadioGroup;
 import android.widget.SimpleAdapter;
 import android.widget.Spinner;
 import android.widget.TextView;
 import android.widget.TimePicker;
 import android.graphics.Color;
 import android.view.WindowManager;
 import android.widget.AutoCompleteTextView;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.R;

 import Utility.*;
 import Common.*;
 import forms_datamodel.tmpMovement_DataModel;

 public class Surv_Movement extends AppCompatActivity {
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
    LinearLayout secMovementID;
    View lineMovementID;
    TextView VlblMovementID;
    EditText txtMovementID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secMoveEvType;
    View lineMoveEvType;
    TextView VlblMoveEvType;
    RadioGroup rdogrpMoveEvType;
    RadioButton rdoMoveEvType1;
    RadioButton rdoMoveEvType2;
    RadioButton rdoMoveEvType3;
    RadioButton rdoMoveEvType4;
    LinearLayout secMoveDate;
    View lineMoveDate;
    TextView VlblMoveDate;
    EditText dtpMoveDate;
     LinearLayout secMovePhoneAvail;
     View lineMovePhoneAvail;
     TextView VlblMovePhoneAvail;
     RadioGroup rdogrpMovePhoneAvail;
     RadioButton rdoMovePhoneAvail1;
     RadioButton rdoMovePhoneAvail2;
     LinearLayout secMovePhone;
     View lineMovePhone;
     TextView VlblMovePhone;
     EditText txtMovePhone;
     LinearLayout secMoveAddressAvail;
     View lineMoveAddressAvail;
     TextView VlblMoveAddressAvail;
     RadioGroup rdogrpMoveAddressAvail;
     RadioButton rdoMoveAddressAvail1;
     RadioButton rdoMoveAddressAvail2;
   
     LinearLayout secMoveAddress;
     View lineMoveAddress;
     TextView VlblMoveAddress;
     EditText txtMoveAddress;
   
     LinearLayout seclblH1;
     View linelblH1;
   
    LinearLayout secMoveVill;
    View lineMoveVill;
    TextView VlblMoveVill;
//    AutoCompleteTextView txtMoveVill;
     Spinner spnLayer1;
     Spinner spnLayer2;
     Spinner spnLayer3;
    LinearLayout secMoveCompound;
    View lineMoveCompound;
    TextView VlblMoveCompound;
//    AutoCompleteTextView txtMoveCompound;
    LinearLayout secMoveHH;
    View lineMoveHH;
    TextView VlblMoveHH;
//    AutoCompleteTextView txtMoveHH;
    LinearLayout secMoveReason;
    View lineMoveReason;
    TextView VlblMoveReason;
    RadioGroup rdogrpMoveReason;
    RadioButton rdoMoveReason1;
    RadioButton rdoMoveReason2;
    RadioButton rdoMoveReason3;
    RadioButton rdoMoveReason4;
    RadioButton rdoMoveReason5;
    RadioButton rdoMoveReason6;
     RadioButton rdoMoveReason7;
     RadioButton rdoMoveReason8;
     RadioButton rdoMoveReason9;
     RadioButton rdoMoveReason10;
     RadioButton rdoMoveReason11;
     RadioButton rdoMoveReason12;
     RadioButton rdoMoveReason97;
     RadioButton rdoMoveReason98;
     RadioButton rdoMoveReason99;
    LinearLayout secMoveReasonOth;
    View lineMoveReasonOth;
    TextView VlblMoveReasonOth;
    AutoCompleteTextView txtMoveReasonOth;
    LinearLayout secMoveVDate;
    View lineMoveVDate;
    TextView VlblMoveVDate;
    EditText dtpMoveVDate;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secMoveNote;
    View lineMoveNote;
    TextView VlblMoveNote;
    EditText txtMoveNote;

     LinearLayout secMoveNote1;
     View lineMoveNote1;
     TextView lblEvCode;
     int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String MOVEMENTID = "";
      String MEM_ID = "";
      String HHID = "";
      String HHNO = "";
     // String MSLNO = "";
      String EV_TYPE = "";
      String ROUND = "";
      String edit = "";
      String exDate = "";
      String SQL = "";
      String VISIT_DATE = "";
      String L1 = "";
      String L2 = "";
      String L3 = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.surv_movement );
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         MOVEMENTID = IDbundle.getString("MovementID");
         MEM_ID = IDbundle.getString("MemID");
         HHID = IDbundle.getString("HHID");
         HHNO = IDbundle.getString("HHNO");
         EV_TYPE = IDbundle.getString("evtype");
         ROUND = IDbundle.getString("round");
         edit = IDbundle.getString("edit");
         VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy
         exDate = IDbundle.getString("exdate")==null?"":IDbundle.getString("exdate");

         L1 = IDbundle.getString("address_level1")==null?"":IDbundle.getString("address_level1");
         L2 = IDbundle.getString("address_level2")==null?"":IDbundle.getString("address_level2");
         L3 = IDbundle.getString("address_level3")==null?"":IDbundle.getString("address_level3");

         TableName = "tmpMovement";
         MODULEID = 24;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Movement.this);
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
        Connection.LocalizeLanguage(Surv_Movement.this, MODULEID, LANGUAGEID);


         dtpMoveDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnMoveDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpMoveVDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnMoveVDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });

         //=========================================================================================
         //Bangladesh: Baliakandi
         //=========================================================================================
         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.BANGLADESH_SITE1))
         {
             if(EV_TYPE.equals("52")){
                 VlblMoveVill.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER1 + " to");
                 VlblMoveCompound.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER2 + " to");
                 VlblMoveHH.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER3 + " to");
             }else{
                 VlblMoveVill.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER1 + " from");
                 VlblMoveCompound.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER2 + " from");
                 VlblMoveHH.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER3 + " from");
                 secMovePhone.setVisibility(View.GONE);
                 lineMovePhone.setVisibility(View.GONE);
                 secMovePhoneAvail.setVisibility(View.GONE);
                 lineMovePhoneAvail.setVisibility(View.GONE);
                 secMoveAddressAvail.setVisibility(View.GONE);
                 lineMoveAddressAvail.setVisibility(View.GONE);
                 secMoveAddress.setVisibility(View.GONE);
                 lineMoveAddress.setVisibility(View.GONE);

             }

              SQL = " select distinct GeoLevel7||'-'||GeoLevel7Name from Village v \n" +
                     " inner join Location l on v.LocID=l.LocID\n" +
                     " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and WorkGroup='3' and Active='1' " +
                     " order by GeoLevel7||'-'||GeoLevel7Name";

             spnLayer1.setAdapter(C.getArrayAdapter(SQL));
             spnLayer1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                     if(spnLayer1.getCount()==0) return;

                     SQL = " select distinct GeoLevel8||'-'||GeoLevel8Name from Village v \n" +
                             " inner join Location l on v.LocID=l.LocID\n" +
                             " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and WorkGroup='3' and Active='1' " +
                             " where GeoLevel7='"+ spnLayer1.getSelectedItem().toString().split("-")[0] +"'" +
                             " order by GeoLevel8||'-'||GeoLevel8Name";

                     spnLayer2.setAdapter(C.getArrayAdapter(SQL));
                 }

                 @Override
                 public void onNothingSelected(AdapterView<?> parentView) {
                     // your code here
                 }

             });


             spnLayer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                     if(spnLayer2.getCount()==0) return;
                     String LocationID = C.ReturnSingleValue("Select distinct LocID from Location where GeoLevel8='"+ spnLayer2.getSelectedItem().toString().split("-")[0] +"'");

                     spnLayer3.setAdapter(C.getArrayAdapter("Select cast(v.VillID as varchar(20))||'-'||v.VillName from Village v inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' where v.LocID='"+ LocationID +"' and isdelete=2"));
                 }

                 @Override
                 public void onNothingSelected(AdapterView<?> parentView) {
                     // your code here
                 }

             });

         }

         //=========================================================================================
         //Nigeria: Cross River
         //=========================================================================================
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1))
         {
             if(EV_TYPE.equals("52")){
                 VlblMoveVill.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER1 + " to");
                 VlblMoveCompound.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER2 + " to");
                 VlblMoveHH.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER3 + " to");
             }else{
                 VlblMoveVill.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER1 + " from");
                 VlblMoveCompound.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER2 + " from");
                 VlblMoveHH.setText(ProjectSetting.NIGERIA_SITE1_GEO_LAYER3 + " from");

                 secMovePhone.setVisibility(View.GONE);
                 lineMovePhone.setVisibility(View.GONE);
                 secMovePhoneAvail.setVisibility(View.GONE);
                 lineMovePhoneAvail.setVisibility(View.GONE);
                 secMoveAddressAvail.setVisibility(View.GONE);
                 lineMoveAddressAvail.setVisibility(View.GONE);
                 secMoveAddress.setVisibility(View.GONE);
                 lineMoveAddress.setVisibility(View.GONE);
             }

              SQL = "select '.Select from list' union  select distinct GeoLevel7||'-'||GeoLevel7Name from Village v \n" +
                     " inner join Location l on v.LocID=l.LocID\n" +
//                     " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                     " order by GeoLevel7||'-'||GeoLevel7Name";

             spnLayer1.setAdapter(C.getArrayAdapter(SQL));
             spnLayer1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                     if(spnLayer1.getCount()==0) return;

                     SQL = "select '.Select from list' union  select distinct GeoLevel8||'-'||GeoLevel8Name from Village v \n" +
                             " inner join Location l on v.LocID=l.LocID\n" +
//                             " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                             " where GeoLevel7='"+ spnLayer1.getSelectedItem().toString().split("-")[0] +"'" +
                             " order by GeoLevel8||'-'||GeoLevel8Name";

                     spnLayer2.setAdapter(C.getArrayAdapter(SQL));
                 }

                 @Override
                 public void onNothingSelected(AdapterView<?> parentView) {
                     // your code here
                 }

             });


             spnLayer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                     if(spnLayer2.getCount()==0) return;
                     String LocationID = C.ReturnSingleValue("Select distinct LocID from Location where GeoLevel8='"+ spnLayer2.getSelectedItem().toString().split("-")[0] +"'");

                     spnLayer3.setAdapter(C.getArrayAdapter("select '.Select from list' union  Select cast(v.VillID as varchar(20))||'-'||v.VillName from Village v " +
//                             "inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                             "where v.LocID='"+ LocationID +"' and isdelete=2"));
                 }

                 @Override
                 public void onNothingSelected(AdapterView<?> parentView) {
                     // your code here
                 }

             });
         }

         //=========================================================================================
         //Nigeria: Bauchi
         //=========================================================================================
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2))
         {
             if(EV_TYPE.equals("52")){
                 VlblMoveVill.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER1 + " to");
                 VlblMoveCompound.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER2 + " to");
                 VlblMoveHH.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER3 + " to");
             }else{
                 VlblMoveVill.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER1 + " from");
                 VlblMoveCompound.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER2 + " from");
                 VlblMoveHH.setText(ProjectSetting.NIGERIA_SITE2_GEO_LAYER3 + " from");

                 secMovePhone.setVisibility(View.GONE);
                 lineMovePhone.setVisibility(View.GONE);
                 secMovePhoneAvail.setVisibility(View.GONE);
                 lineMovePhoneAvail.setVisibility(View.GONE);
                 secMoveAddressAvail.setVisibility(View.GONE);
                 lineMoveAddressAvail.setVisibility(View.GONE);
                 secMoveAddress.setVisibility(View.GONE);
                 lineMoveAddress.setVisibility(View.GONE);
             }

              SQL = " select '.Select from list' union  select distinct GeoLevel7||'-'||GeoLevel7Name from Village v \n" +
                     " inner join Location l on v.LocID=l.LocID\n" +
                     " inner join DataCollector_AssignArea a on v.villid=a.villid and and a.WorkGroup='3' a.userid='"+ ENTRYUSER +"' and Active='1' " +
                     " order by GeoLevel7||'-'||GeoLevel7Name";

             spnLayer1.setAdapter(C.getArrayAdapter(SQL));
             spnLayer1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                     if(spnLayer1.getCount()==0) return;

                     SQL = "select '.Select from list' union   select distinct GeoLevel8||'-'||GeoLevel8Name from Village v \n" +
                             " inner join Location l on v.LocID=l.LocID\n" +
                             " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and a.Active='1' " +
                             " where GeoLevel7='"+ spnLayer1.getSelectedItem().toString().split("-")[0] +"'" +
                             " order by GeoLevel8||'-'||GeoLevel8Name";

                     spnLayer2.setAdapter(C.getArrayAdapter(SQL));
                 }

                 @Override
                 public void onNothingSelected(AdapterView<?> parentView) {
                     // your code here
                 }

             });


             spnLayer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                     if(spnLayer2.getCount()==0) return;
                     String LocationID = C.ReturnSingleValue("Select distinct LocID from Location where GeoLevel8='"+ spnLayer2.getSelectedItem().toString().split("-")[0] +"'");

                     spnLayer3.setAdapter(C.getArrayAdapter("select '.Select from list' union  Select cast(v.VillID as varchar(20))||'-'||v.VillName from Village v " +
//                             "inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                             "where v.LocID='"+ LocationID +"' and isdelete=2"));
                 }

                 @Override
                 public void onNothingSelected(AdapterView<?> parentView) {
                     // your code here
                 }

             });
         }

         //=========================================================================================
         //Sierra Leone
         //=========================================================================================
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1))
         {
             if(EV_TYPE.equals("52")){
                 VlblMoveVill.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER1 + " to");
                 VlblMoveCompound.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER2 + " to");
                 VlblMoveHH.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER3 + " to");
             }else{
                 VlblMoveVill.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER1 + " from");
                 VlblMoveCompound.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER2 + " from");
                 VlblMoveHH.setText(ProjectSetting.SIERRA_SITE1_GEO_LAYER3 + " from");

                 secMovePhone.setVisibility(View.GONE);
                 lineMovePhone.setVisibility(View.GONE);
                 secMovePhoneAvail.setVisibility(View.GONE);
                 lineMovePhoneAvail.setVisibility(View.GONE);
                 secMoveAddressAvail.setVisibility(View.GONE);
                 lineMoveAddressAvail.setVisibility(View.GONE);
                 secMoveAddress.setVisibility(View.GONE);
                 lineMoveAddress.setVisibility(View.GONE);
             }

              SQL = "  select '.Select from list' union  select distinct GeoLevel7||'-'||GeoLevel7Name from Village v \n" +
                 " inner join Location l on v.LocID=l.LocID   union select '98-Don`t Know'  \n" +
                 " order by GeoLevel7||'-'||GeoLevel7Name";

             spnLayer1.setAdapter(C.getArrayAdapter(SQL));
             spnLayer1.setSelection(Global.SpinnerItemPositionAnyLength(spnLayer1,L1));
             spnLayer1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                     if(spnLayer1.getCount()==0) return;

                     SQL = "  select '.Select from list' union  select distinct GeoLevel8||'-'||GeoLevel8Name from Village v \n" +
                             " inner join Location l on v.LocID=l.LocID\n" +
//                             " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                             " where GeoLevel7='"+ spnLayer1.getSelectedItem().toString().split("-")[0] +"'   union select '98-Don`t Know'  " +
                             " order by GeoLevel8||'-'||GeoLevel8Name";

                     spnLayer2.setAdapter(C.getArrayAdapter(SQL));
                     spnLayer2.setSelection(Global.SpinnerItemPositionAnyLength(spnLayer2,L2));
                 }

                 @Override
                 public void onNothingSelected(AdapterView<?> parentView) {
                     // your code here
                 }

             });


             spnLayer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                     if(spnLayer2.getCount()==0) return;
                     String LocationID = C.ReturnSingleValue("Select distinct LocID from Location where GeoLevel8='"+ spnLayer2.getSelectedItem().toString().split("-")[0] +"'");

                     spnLayer3.setAdapter(C.getArrayAdapter("  select '.Select from list' union Select cast(v.VillID as varchar(20))||'-'||v.VillName from Village v " +
//                             "inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                             "where v.LocID='"+ LocationID +"' and isdelete=2 union select '998-Don`t Know'   "));

                     spnLayer3.setSelection(Global.SpinnerItemPositionAnyLength(spnLayer3,L3));
                 }

                 @Override
                 public void onNothingSelected(AdapterView<?> parentView) {
                     // your code here
                 }

             });
         }

         //=========================================================================================
         //Mali
         //=========================================================================================
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1))
         {
             if(EV_TYPE.equals("52")){
                 VlblMoveVill.setText(ProjectSetting.MALI_SITE1_GEO_LAYER1 + " to");
                 VlblMoveCompound.setText(ProjectSetting.MALI_SITE1_GEO_LAYER2 + " to");
                 VlblMoveHH.setText(ProjectSetting.MALI_SITE1_GEO_LAYER3 + " to");
             }else{
                 VlblMoveVill.setText(ProjectSetting.MALI_SITE1_GEO_LAYER1 + " from");
                 VlblMoveCompound.setText(ProjectSetting.MALI_SITE1_GEO_LAYER2 + " from");
                 VlblMoveHH.setText(ProjectSetting.MALI_SITE1_GEO_LAYER3 + " from");

                 secMovePhone.setVisibility(View.GONE);
                 lineMovePhone.setVisibility(View.GONE);
                 secMovePhoneAvail.setVisibility(View.GONE);
                 lineMovePhoneAvail.setVisibility(View.GONE);
                 secMoveAddressAvail.setVisibility(View.GONE);
                 lineMoveAddressAvail.setVisibility(View.GONE);
                 secMoveAddress.setVisibility(View.GONE);
                 lineMoveAddress.setVisibility(View.GONE);
             }

                  SQL = "  select '.Select from list' union select distinct GeoLevel7||'-'||GeoLevel7Name  from Village v \n" +
                     " inner join Location l on v.LocID=l.LocID  union select '98-Don`t Know'  \n" +
//                     " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                     " order by GeoLevel7||'-'||GeoLevel7Name";

             spnLayer1.setAdapter(C.getArrayAdapter(SQL));
             if(EV_TYPE.equals("22")){
                 String l1 = C.ReturnSingleValue("select l.GeoLevel7 from household h \n" +
                         "inner join Village v on v.VillID = h.VillID\n" +
                         "inner join Location l on l.LocID = v.LocID\n" +
                         "where h.HHID='"+HHID+"'");

                 spnLayer1.setSelection(Global.SpinnerItemPositionAnyLength(spnLayer1, l1));
             }

             spnLayer1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                     if(spnLayer1.getCount()==0) return;

                     SQL = " select '.Select from list' union select distinct GeoLevel8||'-'||GeoLevel8Name from Village v \n" +
                             " inner join Location l on v.LocID=l.LocID\n" +
//                             " inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                             " where GeoLevel7='"+ spnLayer1.getSelectedItem().toString().split("-")[0] +"'  union select '98-Don`t Know'  " +
                             " order by GeoLevel8||'-'||GeoLevel8Name";

                     spnLayer2.setAdapter(C.getArrayAdapter(SQL));
                     if(EV_TYPE.equals("22")){
                         String l2 = C.ReturnSingleValue("select l.GeoLevel8 from household h \n" +
                                 "inner join Village v on v.VillID = h.VillID\n" +
                                 "inner join Location l on l.LocID = v.LocID\n" +
                                 "where h.HHID='"+HHID+"'");
                         spnLayer2.setSelection(Global.SpinnerItemPositionAnyLength(spnLayer2, l2));
                     }
                 }

                 @Override
                 public void onNothingSelected(AdapterView<?> parentView) {
                     // your code here
                 }

             });


             spnLayer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                     if(spnLayer2.getCount()==0) return;
                     String LocationID = C.ReturnSingleValue("Select distinct LocID from Location where GeoLevel8='"+ spnLayer2.getSelectedItem().toString().split("-")[0] +"'");

                     spnLayer3.setAdapter(C.getArrayAdapter(" select '.Select from list' union Select cast(v.VillID as varchar(20)) from Village v " +
//                             "inner join DataCollector_AssignArea a on v.villid=a.villid and a.userid='"+ ENTRYUSER +"' and a.WorkGroup='3' and Active='1' " +
                             "where v.LocID='"+ LocationID +"' and isdelete=2 union select '998-Don`t Know'  "));
                     if(EV_TYPE.equals("22")){
                         String l3 = C.ReturnSingleValue("select h.VillID from household h where h.HHID='"+HHID+"'");
                         spnLayer3.setSelection(Global.SpinnerItemPositionAnyLength(spnLayer3, l3));
                     }

                 }

                 @Override
                 public void onNothingSelected(AdapterView<?> parentView) {
                     // your code here
                 }

             });
         }

         //Hide all skip variables
         secMoveReasonOth.setVisibility(View.GONE);
         lineMoveReasonOth.setVisibility(View.GONE);
         secMovePhone.setVisibility(View.GONE);
         lineMovePhone.setVisibility(View.GONE);
//         secMoveVill.setVisibility(View.GONE);
//         lineMoveVill.setVisibility(View.GONE);
//         secMoveCompound.setVisibility(View.GONE);
//         lineMoveCompound.setVisibility(View.GONE);
//         secMoveHH.setVisibility(View.GONE);
//         lineMoveHH.setVisibility(View.GONE);

         seclblH1.setVisibility(View.GONE);
         linelblH1.setVisibility(View.GONE);
         secMoveVill.setVisibility(View.GONE);
         lineMoveVill.setVisibility(View.GONE);
         secMoveCompound.setVisibility(View.GONE);
         lineMoveCompound.setVisibility(View.GONE);
         secMoveHH.setVisibility(View.GONE);
         lineMoveHH.setVisibility(View.GONE);
         secMoveAddress.setVisibility(View.GONE);
         lineMoveAddress.setVisibility(View.GONE);

        DataSearch(MOVEMENTID);

        if (exDate.length() > 0){
            dtpMoveDate.setText(Global.DateConvertDMY(exDate));
            dtpMoveDate.setEnabled(false);
        }


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Movement.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         lblEvCode = findViewById(R.id.lblEvCode);
         lblEvCode.setText(EV_TYPE);

         if (EV_TYPE.equals("52"))
         {
             lblHeading.setText("Internal Migration out");
         }
         if (EV_TYPE.equals("53"))
         {
             lblHeading.setText("Split-out");
         }

         secMovementID=(LinearLayout)findViewById(R.id.secMovementID);
         lineMovementID=(View)findViewById(R.id.lineMovementID);
         VlblMovementID=(TextView) findViewById(R.id.VlblMovementID);
         txtMovementID=(EditText) findViewById(R.id.txtMovementID);
//         txtMovementID.setText(MOVEMENTID);
         if(MOVEMENTID.length()==0) txtMovementID.setText(Global.Get_UUID(DEVICEID));
         else txtMovementID.setText(MOVEMENTID);
         txtMovementID.setEnabled(false);
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

         secMoveEvType=(LinearLayout)findViewById(R.id.secMoveEvType);
         lineMoveEvType=(View)findViewById(R.id.lineMoveEvType);
         VlblMoveEvType = (TextView) findViewById(R.id.VlblMoveEvType);
         rdogrpMoveEvType = (RadioGroup) findViewById(R.id.rdogrpMoveEvType);
         rdoMoveEvType1 = (RadioButton) findViewById(R.id.rdoMoveEvType1);
         rdoMoveEvType2 = (RadioButton) findViewById(R.id.rdoMoveEvType2);
         rdoMoveEvType3 = (RadioButton) findViewById(R.id.rdoMoveEvType3);
         rdoMoveEvType4 = (RadioButton) findViewById(R.id.rdoMoveEvType4);

         if(EV_TYPE.equals("22")){
             rdoMoveEvType1.setChecked(true);
             rdoMoveEvType2.setEnabled(false);
             rdoMoveEvType4.setEnabled(false);
             rdoMoveEvType3.setEnabled(false);
         }else if(EV_TYPE.equals("23")){
             rdoMoveEvType2.setChecked(true);
             rdoMoveEvType1.setEnabled(false);
             rdoMoveEvType4.setEnabled(false);
             rdoMoveEvType3.setEnabled(false);
         }else if(EV_TYPE.equals("52")){
             rdoMoveEvType1.setEnabled(false);
             rdoMoveEvType2.setEnabled(false);
             rdoMoveEvType4.setEnabled(false);
             rdoMoveEvType3.setChecked(true);
         }else if(EV_TYPE.equals("53")){
             rdoMoveEvType1.setEnabled(false);
             rdoMoveEvType2.setEnabled(false);
             rdoMoveEvType3.setEnabled(false);
             rdoMoveEvType4.setChecked(true);
         }
         secMoveDate=(LinearLayout)findViewById(R.id.secMoveDate);
         lineMoveDate=(View)findViewById(R.id.lineMoveDate);
         VlblMoveDate=(TextView) findViewById(R.id.VlblMoveDate);
         dtpMoveDate=(EditText) findViewById(R.id.dtpMoveDate);


         secMovePhoneAvail=(LinearLayout)findViewById(R.id.secMovePhoneAvail);
         lineMovePhoneAvail=(View)findViewById(R.id.lineMovePhoneAvail);
         VlblMovePhoneAvail = (TextView) findViewById(R.id.VlblMovePhoneAvail);
         rdogrpMovePhoneAvail = (RadioGroup) findViewById(R.id.rdogrpMovePhoneAvail);
         rdoMovePhoneAvail1 = (RadioButton) findViewById(R.id.rdoMovePhoneAvail1);
         rdoMovePhoneAvail2 = (RadioButton) findViewById(R.id.rdoMovePhoneAvail2);
         rdogrpMovePhoneAvail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpMovePhoneAvail = new String[] {"1","2"};
                 for (int i = 0; i < rdogrpMovePhoneAvail.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpMovePhoneAvail.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpMovePhoneAvail[i];
                 }

                 if(rbData.equalsIgnoreCase("1"))
                 {
                     secMovePhone.setVisibility(View.VISIBLE);
                     lineMovePhone.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secMovePhone.setVisibility(View.GONE);
                     lineMovePhone.setVisibility(View.GONE);
                     txtMovePhone.setText("");
                 }
             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });
         secMovePhone=(LinearLayout)findViewById(R.id.secMovePhone);
         lineMovePhone=(View)findViewById(R.id.lineMovePhone);
         VlblMovePhone=(TextView) findViewById(R.id.VlblMovePhone);
         txtMovePhone=(EditText) findViewById(R.id.txtMovePhone);
         secMoveAddressAvail=(LinearLayout)findViewById(R.id.secMoveAddressAvail);
         lineMoveAddressAvail=(View)findViewById(R.id.lineMoveAddressAvail);
         VlblMoveAddressAvail = (TextView) findViewById(R.id.VlblMoveAddressAvail);
         rdogrpMoveAddressAvail = (RadioGroup) findViewById(R.id.rdogrpMoveAddressAvail);
         rdoMoveAddressAvail1 = (RadioButton) findViewById(R.id.rdoMoveAddressAvail1);
         rdoMoveAddressAvail2 = (RadioButton) findViewById(R.id.rdoMoveAddressAvail2);
         rdogrpMoveAddressAvail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpMoveAddressAvail = new String[] {"1","2"};
                 for (int i = 0; i < rdogrpMoveAddressAvail.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpMoveAddressAvail.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpMoveAddressAvail[i];
                 }

                 if(rbData.equalsIgnoreCase("2"))
                 {
                   
                     secMoveAddress.setVisibility(View.GONE);
                     lineMoveAddress.setVisibility(View.GONE);
                     txtMoveAddress.setText("");
                   
                     seclblH1.setVisibility(View.GONE);
                     linelblH1.setVisibility(View.GONE);
                   
                     secMoveVill.setVisibility(View.GONE);
                     lineMoveVill.setVisibility(View.GONE);
                     secMoveCompound.setVisibility(View.GONE);
                     lineMoveCompound.setVisibility(View.GONE);
                     secMoveHH.setVisibility(View.GONE);
                     lineMoveHH.setVisibility(View.GONE);

                 }
                 else
                 {
                   
                     secMoveAddress.setVisibility(View.VISIBLE);
                     lineMoveAddress.setVisibility(View.VISIBLE);
                   
                     seclblH1.setVisibility(View.VISIBLE);
                     linelblH1.setVisibility(View.VISIBLE);
                   
                     secMoveVill.setVisibility(View.VISIBLE);
                     lineMoveVill.setVisibility(View.VISIBLE);
                     secMoveCompound.setVisibility(View.VISIBLE);
                     lineMoveCompound.setVisibility(View.VISIBLE);
                     secMoveHH.setVisibility(View.VISIBLE);
                     lineMoveHH.setVisibility(View.VISIBLE);

                 }
             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });

     
         secMoveAddress=(LinearLayout)findViewById(R.id.secMoveAddress);
         lineMoveAddress=(View)findViewById(R.id.lineMoveAddress);
         VlblMoveAddress=(TextView) findViewById(R.id.VlblMoveAddress);
         txtMoveAddress=(EditText) findViewById(R.id.txtMoveAddress);
     
         seclblH1=(LinearLayout)findViewById(R.id.seclblH1);
         linelblH1=(View)findViewById(R.id.linelblH1);
     

         secMoveVill=(LinearLayout)findViewById(R.id.secMoveVill);
         lineMoveVill=(View)findViewById(R.id.lineMoveVill);
         VlblMoveVill=(TextView) findViewById(R.id.VlblMoveVill);

         spnLayer1 = (Spinner) findViewById(R.id.spnLayer1);
         spnLayer2 = (Spinner) findViewById(R.id.spnLayer2);
         spnLayer3 = (Spinner) findViewById(R.id.spnLayer3);

//         txtMoveVill=(AutoCompleteTextView) findViewById(R.id.txtMoveVill);
//         txtMoveVill.setAdapter(C.getArrayAdapter("Select distinct MoveVill from "+ TableName +" order by MoveVill"));
//
//         txtMoveVill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//             @Override
//             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {
//
//             }
//         });
//         txtMoveVill.setOnTouchListener(new View.OnTouchListener() {
//             @Override
//             public boolean onTouch(View v, MotionEvent event) {
//                 final int DRAWABLE_RIGHT = 2;
//
//                 if(event.getAction() == MotionEvent.ACTION_UP) {
//                     if(event.getRawX() >= (txtMoveVill.getRight() - txtMoveVill.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                         ((EditText)v).setText("");
//                         return true;
//                     }
//                 }
//                 return false;
//             }
//         });
         secMoveCompound=(LinearLayout)findViewById(R.id.secMoveCompound);
         lineMoveCompound=(View)findViewById(R.id.lineMoveCompound);
         VlblMoveCompound=(TextView) findViewById(R.id.VlblMoveCompound);
//         txtMoveCompound=(AutoCompleteTextView) findViewById(R.id.txtMoveCompound);
//         txtMoveCompound.setAdapter(C.getArrayAdapter("Select distinct MoveCompound from "+ TableName +" order by MoveCompound"));
//
//         txtMoveCompound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//             @Override
//             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {
//
//             }
//         });
//         txtMoveCompound.setOnTouchListener(new View.OnTouchListener() {
//             @Override
//             public boolean onTouch(View v, MotionEvent event) {
//                 final int DRAWABLE_RIGHT = 2;
//
//                 if(event.getAction() == MotionEvent.ACTION_UP) {
//                     if(event.getRawX() >= (txtMoveCompound.getRight() - txtMoveCompound.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                         ((EditText)v).setText("");
//                         return true;
//                     }
//                 }
//                 return false;
//             }
//         });
         secMoveHH=(LinearLayout)findViewById(R.id.secMoveHH);
         lineMoveHH=(View)findViewById(R.id.lineMoveHH);
         VlblMoveHH=(TextView) findViewById(R.id.VlblMoveHH);
//         txtMoveHH=(AutoCompleteTextView) findViewById(R.id.txtMoveHH);
//         txtMoveHH.setAdapter(C.getArrayAdapter("Select distinct MoveHH from "+ TableName +" order by MoveHH"));
//
//         txtMoveHH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//             @Override
//             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {
//
//             }
//         });
//         txtMoveHH.setOnTouchListener(new View.OnTouchListener() {
//             @Override
//             public boolean onTouch(View v, MotionEvent event) {
//                 final int DRAWABLE_RIGHT = 2;
//
//                 if(event.getAction() == MotionEvent.ACTION_UP) {
//                     if(event.getRawX() >= (txtMoveHH.getRight() - txtMoveHH.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                         ((EditText)v).setText("");
//                         return true;
//                     }
//                 }
//                 return false;
//             }
//         });
         secMoveReason=(LinearLayout)findViewById(R.id.secMoveReason);
         lineMoveReason=(View)findViewById(R.id.lineMoveReason);
         VlblMoveReason = (TextView) findViewById(R.id.VlblMoveReason);
         rdogrpMoveReason = (RadioGroup) findViewById(R.id.rdogrpMoveReason);
         rdoMoveReason1 = (RadioButton) findViewById(R.id.rdoMoveReason1);
         rdoMoveReason2 = (RadioButton) findViewById(R.id.rdoMoveReason2);
         rdoMoveReason3 = (RadioButton) findViewById(R.id.rdoMoveReason3);
         rdoMoveReason4 = (RadioButton) findViewById(R.id.rdoMoveReason4);
         rdoMoveReason5 = (RadioButton) findViewById(R.id.rdoMoveReason5);
         rdoMoveReason6 = (RadioButton) findViewById(R.id.rdoMoveReason6);
         rdoMoveReason7 = (RadioButton) findViewById(R.id.rdoMoveReason7);
         rdoMoveReason8 = (RadioButton) findViewById(R.id.rdoMoveReason8);
         rdoMoveReason9 = (RadioButton) findViewById(R.id.rdoMoveReason9);
         rdoMoveReason10 = (RadioButton) findViewById(R.id.rdoMoveReason10);
         rdoMoveReason11 = (RadioButton) findViewById(R.id.rdoMoveReason11);
         rdoMoveReason12 = (RadioButton) findViewById(R.id.rdoMoveReason12);
         rdoMoveReason97 = (RadioButton) findViewById(R.id.rdoMoveReason97);
         rdoMoveReason98 = (RadioButton) findViewById(R.id.rdoMoveReason98);
         rdoMoveReason99 = (RadioButton) findViewById(R.id.rdoMoveReason99);

         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
             rdoMoveReason7.setVisibility(View.VISIBLE);
             rdoMoveReason8.setVisibility(View.VISIBLE);
             rdoMoveReason9.setVisibility(View.VISIBLE);
             rdoMoveReason10.setVisibility(View.VISIBLE);
             rdoMoveReason11.setVisibility(View.VISIBLE);
         }
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
         rdoMoveReason7.setVisibility(View.GONE);
         rdoMoveReason8.setVisibility(View.VISIBLE);
         rdoMoveReason9.setVisibility(View.VISIBLE);
         rdoMoveReason10.setVisibility(View.GONE);
         rdoMoveReason11.setVisibility(View.GONE);
        }
         else{
             rdoMoveReason7.setVisibility(View.GONE);
             rdoMoveReason8.setVisibility(View.GONE);
             rdoMoveReason9.setVisibility(View.GONE);
             rdoMoveReason10.setVisibility(View.GONE);
             rdoMoveReason11.setVisibility(View.GONE);
         }
         rdogrpMoveReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpMoveReason = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","97","98","99"};
             for (int i = 0; i < rdogrpMoveReason.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpMoveReason.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpMoveReason[i];
             }

             if(rbData.equalsIgnoreCase("97"))
             {
                 secMoveReasonOth.setVisibility(View.VISIBLE);
                 lineMoveReasonOth.setVisibility(View.VISIBLE);
             }
             else
             {
                 secMoveReasonOth.setVisibility(View.GONE);
                 lineMoveReasonOth.setVisibility(View.GONE);
                 txtMoveReasonOth.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            }
         });
         secMoveReasonOth=(LinearLayout)findViewById(R.id.secMoveReasonOth);
         lineMoveReasonOth=(View)findViewById(R.id.lineMoveReasonOth);
         VlblMoveReasonOth=(TextView) findViewById(R.id.VlblMoveReasonOth);
         txtMoveReasonOth=(AutoCompleteTextView) findViewById(R.id.txtMoveReasonOth);
         C.setupAutoComplete(TableName,txtMoveReasonOth,"MoveReasonOth"); //setup autocomplete view by event

         secMoveVDate=(LinearLayout)findViewById(R.id.secMoveVDate);
         lineMoveVDate=(View)findViewById(R.id.lineMoveVDate);
         VlblMoveVDate=(TextView) findViewById(R.id.VlblMoveVDate);
         dtpMoveVDate=(EditText) findViewById(R.id.dtpMoveVDate);
         dtpMoveVDate.setText(VISIT_DATE);
         secMoveVDate.setVisibility(View.GONE);

         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         txtRnd.setText(ROUND);
         txtRnd.setEnabled(false);
         secMoveNote=(LinearLayout)findViewById(R.id.secMoveNote);
         lineMoveNote=(View)findViewById(R.id.lineMoveNote);
         VlblMoveNote=(TextView) findViewById(R.id.VlblMoveNote);
         txtMoveNote=(EditText) findViewById(R.id.txtMoveNote);

         secMoveNote1=(LinearLayout)findViewById(R.id.secMoveNote1);
         lineMoveNote1=(View)findViewById(R.id.lineMoveNote1);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Movement.this, e.getMessage());
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
         	Connection.MessageBox(Surv_Movement.this, ValidationMSG);
         	return;
         }

         String SQL = "";
         String SQL3 = "";
         RadioButton rb;

         tmpMovement_DataModel objSave = new tmpMovement_DataModel();
         objSave.setMovementID(txtMovementID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setMemID(txtMemID.getText().toString());
         String[] d_rdogrpMoveEvType = new String[] {"22","23","52","53"};
         objSave.setMoveEvType("");
         for (int i = 0; i < rdogrpMoveEvType.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMoveEvType.getChildAt(i);
             if (rb.isChecked()) objSave.setMoveEvType(d_rdogrpMoveEvType[i]);
         }

         objSave.setMoveDate(dtpMoveDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpMoveDate.getText().toString()) : dtpMoveDate.getText().toString());
         String[] d_rdogrpMovePhoneAvail = new String[] {"1","2"};
         objSave.setMovePhoneAvail("");
         for (int i = 0; i < rdogrpMovePhoneAvail.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMovePhoneAvail.getChildAt(i);
             if (rb.isChecked()) objSave.setMovePhoneAvail(d_rdogrpMovePhoneAvail[i]);
         }

         objSave.setMovePhone(txtMovePhone.getText().toString());
         String[] d_rdogrpMoveAddressAvail = new String[] {"1","2"};
         objSave.setMoveAddressAvail("");
         for (int i = 0; i < rdogrpMoveAddressAvail.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMoveAddressAvail.getChildAt(i);
             if (rb.isChecked()) objSave.setMoveAddressAvail(d_rdogrpMoveAddressAvail[i]);
         }

         objSave.setMoveAddress(txtMoveAddress.getText().toString());
//         objSave.setMoveVill(txtMoveVill.getText().toString());
//         objSave.setMoveCompound(txtMoveCompound.getText().toString());
//         objSave.setMoveHH(txtMoveHH.getText().toString());
         Log.d("tag",String.valueOf(spnLayer1.getSelectedItemPosition()));
         Log.d("tag2",String.valueOf(spnLayer2.getSelectedItemPosition()));
         Log.d("tag3",String.valueOf(spnLayer3.getSelectedItemPosition()));
          objSave.setMoveVill(spnLayer1.getSelectedItemPosition() == 0 ? "" : spnLayer1.getSelectedItem().toString().split("-")[0]);
          objSave.setMoveCompound((spnLayer2.getSelectedItemPosition() == 0 || spnLayer2.getSelectedItemPosition() == -1) ? "" : spnLayer2.getSelectedItem().toString().split("-")[0]);
          objSave.setMoveHH((spnLayer3.getSelectedItemPosition() == 0 || spnLayer3.getSelectedItemPosition() == -1) ? "" : spnLayer3.getSelectedItem().toString().split("-")[0]);


         String[] d_rdogrpMoveReason = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","97","98","99"};
         objSave.setMoveReason("");
         for (int i = 0; i < rdogrpMoveReason.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMoveReason.getChildAt(i);
             if (rb.isChecked()) objSave.setMoveReason(d_rdogrpMoveReason[i]);
         }

         objSave.setMoveReasonOth(txtMoveReasonOth.getText().toString());
         objSave.setMoveVDate(dtpMoveVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpMoveVDate.getText().toString()) : dtpMoveVDate.getText().toString());
         objSave.setRnd(txtRnd.getText().toString());
         objSave.setMoveNote(txtMoveNote.getText().toString());
         objSave.setStartTime(STARTTIME);
         objSave.setEndTime(g.CurrentTime24());
         objSave.setDeviceID(DEVICEID);
         objSave.setEntryUser(ENTRYUSER); //from data entry user list
         objSave.setLat(MySharedPreferences.getValue(this, "lat"));
         objSave.setLon(MySharedPreferences.getValue(this, "lon"));

         String status = objSave.SaveUpdateData(this);

         if(status.length()==0) {
             String resp = "";
             String SQL1 = "";

             if (EV_TYPE.equals("52") || EV_TYPE.equals("53")) {
                 C.SaveData("Update tmpMember set Upload='2',Active='2',ExType='"+ EV_TYPE +"',modifydate='"+ Global.DateTimeNowYMDHMS() +"' Where MemID='" + MEM_ID + "'");
                 C.SaveData("Update tmpMemberMove set Upload='2',Active='2', MExType='" + EV_TYPE + "', MExDate='" + Global.DateConvertYMD(dtpMoveDate.getText().toString()) +"',modifydate='"+ Global.DateTimeNowYMDHMS() +"' Where  HHID='" + HHID + "' and MemID='" + MEM_ID + "'");

                 C.SaveData("INSERT INTO migMember (VillID,MemID, HHID, DSSID, MSlNo, Rth, RthOth, Name, Sex, BDate_D, BDate_M, BDate_Y, BDate, BDateType, Age, AgeU, MoNo, MoName, FaNo, FaName, EduY, Employ, EmployOth, Ocp, OcpOth, OcpDk, Religion, ReligionOth, Ethnicity, EthnicityOth, MobileNo, MS, MSOth, Sp1, Sp1Name, Sp2, Sp2Name, Sp3, Sp3Name, Sp4, Sp4Name, Pstat, LmpDt, gage, gageUnit, anc_regis, anc_resp_home, anc_other_home, anc_govt_hosp, anc_govt_health, anc_govt_health_post, anc_priv_hosp, anc_tba_home, anc_ngo_hosp, anc_other, anc_other_specify, anc_dk, anc_refuse, out_date, HHHead, Rnd, Active, EnType, ExType, isdelete, StartTime, EndTime, DeviceID, EntryUser, Lat, Lon, EnDt, Upload, modifyDate, ExDate)\n" +
                         " SELECT VillID,MemID, HHID, DSSID, MSlNo, Rth, RthOth, Name, Sex, BDate_D, BDate_M, BDate_Y, BDate, BDateType, Age, AgeU, MoNo, MoName, FaNo, FaName, EduY, Employ, EmployOth, Ocp, OcpOth, OcpDk, Religion, ReligionOth, Ethnicity, EthnicityOth, MobileNo, MS, MSOth, Sp1, Sp1Name, Sp2, Sp2Name, Sp3, Sp3Name, Sp4, Sp4Name, Pstat, LmpDt, gage, gageUnit, anc_regis, anc_resp_home, anc_other_home, anc_govt_hosp, anc_govt_health, anc_govt_health_post, anc_priv_hosp, anc_tba_home, anc_ngo_hosp, anc_other, anc_other_specify, anc_dk, anc_refuse, out_date, HHHead, Rnd, Active, EnType, ExType, isdelete, StartTime, EndTime, DeviceID, EntryUser, Lat, Lon, EnDt, Upload, modifyDate, '"+ Global.DateConvertYMD(dtpMoveDate.getText().toString()) +"' AS ExDate\n" +
                         " FROM tmpMember\n" +
                         " WHERE MemID='" + MEM_ID + "'");

                 Intent returnIntent = new Intent();
                 returnIntent.putExtra("res", "");
                 setResult(Activity.RESULT_OK, returnIntent);
                 Toast.makeText(getApplicationContext(), "Save Successfully...", Toast.LENGTH_SHORT).show();
                 finish();
             }

             if (EV_TYPE.equals("22") || EV_TYPE.equals("23")) {
                 //C.SaveData("Delete from migMember Where MemID='" + MEM_ID + "'");

                 if (edit.equals("2")){
                     Intent returnIntent = new Intent();
                     returnIntent.putExtra("res", "");
                     setResult(Activity.RESULT_OK, returnIntent);
                     Toast.makeText(getApplicationContext(), "Save Successfully...", Toast.LENGTH_SHORT).show();
                     finish();
                 } else {
                     Bundle IDbundle = new Bundle();
                     IDbundle.putString("MigrationID", "");
                     IDbundle.putString("MemID", txtMemID.getText().toString());
                     IDbundle.putString("HHID", txtHHID.getText().toString());
                     IDbundle.putString("evtype", EV_TYPE);
                     IDbundle.putString("evdate", Global.DateConvertYMD(dtpMoveDate.getText().toString()));
                     IDbundle.putString("vdate", Global.DateConvertYMD(VISIT_DATE));
                     IDbundle.putString("round", ROUND);
                     IDbundle.putString("dod", "");
                     IDbundle.putString("MoID", "");
                     IDbundle.putString("LiveBirthID", "");
                     IDbundle.putString("TmpMigrationID", "");
                     finish();
                     Intent intent = new Intent(getApplicationContext(), Surv_Member.class);
                     intent.putExtras(IDbundle);
                     startActivityForResult(intent, 1);
                     finish();
                 }
             }
         }
         else{
             Connection.MessageBox(Surv_Movement.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Movement.this, e.getMessage());
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
         if(txtMovementID.getText().toString().length()==0 & secMovementID.isShown())
           {
             ValidationMsg += "\nRequired field: Internal Movement ID.";
             secMovementID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
         if(!rdoMoveEvType1.isChecked() & !rdoMoveEvType2.isChecked() & !rdoMoveEvType3.isChecked() & !rdoMoveEvType4.isChecked() & secMoveEvType.isShown())
           {
             ValidationMsg += "\nRequired field: Movement Event Type.";
             secMoveEvType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpMoveDate.getText().toString());
         if(DV.length()!=0 & secMoveDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: When did he/she move?";
             secMoveDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoMovePhoneAvail1.isChecked() & !rdoMovePhoneAvail2.isChecked() & secMovePhoneAvail.isShown())
         {
             ValidationMsg += "\nRequired field: Is the phone number of the member available?";
             secMovePhoneAvail.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
//         if(txtMovePhone.getText().toString().length()==0 & secMovePhone.isShown())
//         {
//             ValidationMsg += "\nRequired field: What is the Phone number of the member.";
//             secMovePhone.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//         }
         if(!rdoMoveAddressAvail1.isChecked() & !rdoMoveAddressAvail2.isChecked() & secMoveAddressAvail.isShown())
         {
             ValidationMsg += "\nRequired field: Is the new address of the participant known?";
             secMoveAddressAvail.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         /*if(txtMoveAddress.getText().toString().length()==0 & secMoveAddress.isShown())
         {
             ValidationMsg += "\nRequired field: Address.";
             secMoveAddress.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }*/
//          if(spnLayer1.getSelectedItemPosition() == 0 & secMoveVill.isShown())
//           {
//             ValidationMsg += "\nRequired field: Movement Area/Village.";
//             secMoveVill.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
//         if(txtMoveVill.getText().toString().length()==0 & secMoveVill.isShown())
//           {
//             ValidationMsg += "\nRequired field: Movement Area/Village.";
//             secMoveVill.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
//         if(txtMoveCompound.getText().toString().length()==0 & secMoveCompound.isShown())
//           {
//             ValidationMsg += "\nRequired field: Movement Compound.";
//             secMoveCompound.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
//         if(txtMoveHH.getText().toString().length()==0 & secMoveHH.isShown())
//           {
//             ValidationMsg += "\nRequired field: Movement Household.";
//             secMoveHH.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
         if(!rdoMoveReason1.isChecked() & !rdoMoveReason2.isChecked() & !rdoMoveReason3.isChecked() & !rdoMoveReason4.isChecked() & !rdoMoveReason5.isChecked() & !rdoMoveReason6.isChecked()
                 & !rdoMoveReason7.isChecked() & !rdoMoveReason8.isChecked() & !rdoMoveReason9.isChecked() & !rdoMoveReason10.isChecked() & !rdoMoveReason11.isChecked() & !rdoMoveReason12.isChecked()
                 & !rdoMoveReason97.isChecked() & !rdoMoveReason98.isChecked() & !rdoMoveReason99.isChecked() & secMoveReason.isShown())
           {
             ValidationMsg += "\nRequired field: What was the reason for the movement?";
             secMoveReason.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMoveReasonOth.getText().toString().length()==0 & secMoveReasonOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Specify.";
             secMoveReasonOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpMoveVDate.getText().toString());
         if(DV.length()!=0 & secMoveVDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Visit date.";
             secMoveVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             ValidationMsg += "\nRequired field: Round number.";
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
             secMovementID.setBackgroundColor(Color.WHITE);
             secHHID.setBackgroundColor(Color.WHITE);
             secMemID.setBackgroundColor(Color.WHITE);
             secMoveEvType.setBackgroundColor(Color.WHITE);
             secMoveDate.setBackgroundColor(Color.WHITE);
         secMovePhoneAvail.setBackgroundColor(Color.WHITE);
         secMovePhone.setBackgroundColor(Color.WHITE);
         secMoveAddressAvail.setBackgroundColor(Color.WHITE);
         secMoveAddress.setBackgroundColor(Color.WHITE);
             secMoveVill.setBackgroundColor(Color.WHITE);
             secMoveCompound.setBackgroundColor(Color.WHITE);
             secMoveHH.setBackgroundColor(Color.WHITE);
             secMoveReason.setBackgroundColor(Color.WHITE);
             secMoveReasonOth.setBackgroundColor(Color.WHITE);
             secMoveVDate.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
             secMoveNote.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String MovementID)
     {
       try
        {
           RadioButton rb;
            tmpMovement_DataModel d = new tmpMovement_DataModel();
           String SQL = "Select * from "+ TableName +"  Where MovementID='"+ MovementID +"'";
           List<tmpMovement_DataModel> data = d.SelectAll(this, SQL);
           for(tmpMovement_DataModel item : data){
             txtMovementID.setText(item.getMovementID());
             txtHHID.setText(item.getHHID());
             txtMemID.setText(item.getMemID());
             String[] d_rdogrpMoveEvType = new String[] {"22","23","52","53"};
             for (int i = 0; i < d_rdogrpMoveEvType.length; i++)
             {
                 if (String.valueOf(item.getMoveEvType()).equals(String.valueOf(d_rdogrpMoveEvType[i])))
                 {
                     rb = (RadioButton)rdogrpMoveEvType.getChildAt(i);
                     rb.setChecked(true);
                 }
             }

             dtpMoveDate.setText(item.getMoveDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getMoveDate()));
//             txtMoveVill.setText(item.getMoveVill());
//             txtMoveCompound.setText(item.getMoveCompound());
//             txtMoveHH.setText(item.getMoveHH());
               String[] d_rdogrpMovePhoneAvail = new String[] {"1","2"};
               for (int i = 0; i < d_rdogrpMovePhoneAvail.length; i++)
               {
                   if (String.valueOf(item.getMovePhoneAvail()).equals(String.valueOf(d_rdogrpMovePhoneAvail[i])))
                   {
                       rb = (RadioButton)rdogrpMovePhoneAvail.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
               txtMovePhone.setText(item.getMovePhone());
               String[] d_rdogrpMoveAddressAvail = new String[] {"1","2"};
               for (int i = 0; i < d_rdogrpMoveAddressAvail.length; i++)
               {
                   if (String.valueOf(item.getMoveAddressAvail()).equals(String.valueOf(d_rdogrpMoveAddressAvail[i])))
                   {
                       rb = (RadioButton)rdogrpMoveAddressAvail.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
               txtMoveAddress.setText(item.getMoveAddress());
               spnLayer1.setSelection(Global.SpinnerItemPositionAnyLength(spnLayer1, String.valueOf(item.getMoveVill())));
               spnLayer2.setSelection(Global.SpinnerItemPositionAnyLength(spnLayer2, String.valueOf(item.getMoveCompound())));
               spnLayer3.setSelection(Global.SpinnerItemPositionAnyLength(spnLayer3, String.valueOf(item.getMoveHH())));



               String[] d_rdogrpMoveReason = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","97","98","99"};
                 for (int i = 0; i < d_rdogrpMoveReason.length; i++)
                 {
                     if (String.valueOf(item.getMoveReason()).equals(String.valueOf(d_rdogrpMoveReason[i])))
                     {
                         rb = (RadioButton)rdogrpMoveReason.getChildAt(i);
                         rb.setChecked(true);
                     }
                 }
                 txtMoveReasonOth.setText(item.getMoveReasonOth());
                 dtpMoveVDate.setText(item.getMoveVDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getMoveVDate()));
                 txtRnd.setText(item.getRnd());
                 txtMoveNote.setText(item.getMoveNote());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Movement.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpMoveDate);
      if (VariableID.equals("btnMoveDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpMoveDate);
      }
      else if (VariableID.equals("btnMoveVDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpMoveVDate);
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