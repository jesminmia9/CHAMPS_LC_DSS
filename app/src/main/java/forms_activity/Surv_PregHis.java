
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
 import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

 import forms_datamodel.Member_DataModel;
 import forms_datamodel.PregHis_DataModel;
 import Utility.*;
 import Common.*;
 import forms_datamodel.tmpMember_DataModel;
 import forms_datamodel.tmpPregHis_DataModel;

 public class Surv_PregHis extends AppCompatActivity {
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
    LinearLayout secObsMatID;
    View lineObsMatID;
    TextView VlblObsMatID;
    EditText txtObsMatID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMSlNo;
    View lineMSlNo;
    TextView VlblMSlNo;
    EditText txtMSlNo;
    Spinner spnMSlNo1;
    LinearLayout secObsVDate;
    View lineObsVDate;
    TextView VlblObsVDate;
    EditText dtpObsVDate;
    LinearLayout secObsVStatus;
    View lineObsVStatus;
    TextView VlblObsVStatus;
    Spinner spnObsVStatus;
    LinearLayout secObsVStatusOth;
    View lineObsVStatusOth;
    TextView VlblObsVStatusOth;
    AutoCompleteTextView txtObsVStatusOth;
    LinearLayout seclbl01;
    View linelbl01;
    LinearLayout seclbl01l;
    View linelbl01l;
    LinearLayout secMarMon;
    View lineMarMon;
    TextView VlblMarMon;
//    EditText txtMarMon;
    Spinner spnMarMon;
    LinearLayout secMarYear;
    View lineMarYear;
    TextView VlblMarYear;
//    EditText txtMarYear;
    Spinner spnMarYear;
    LinearLayout secMarDK;
    View lineMarDK;
    TextView VlblMarDK;
    CheckBox chkMarDK;
    LinearLayout secEverPreg;
    View lineEverPreg;
    TextView VlblEverPreg;
    RadioGroup rdogrpEverPreg;
    RadioButton rdoEverPreg1;
    RadioButton rdoEverPreg2;
    LinearLayout secTotPreg;
    View lineTotPreg;
    TextView VlblTotPreg;
    EditText txtTotPreg;
    LinearLayout secGaveBirth;
    View lineGaveBirth;
    TextView VlblGaveBirth;
    RadioGroup rdogrpGaveBirth;
    RadioButton rdoGaveBirth1;
    RadioButton rdoGaveBirth2;
     LinearLayout secChildLivWWo;
     View lineChildLivWWo;
     TextView VlblChildLivWWo;
     RadioGroup rdogrpChildLivWWo;
     RadioButton rdoChildLivWWo1;
     RadioButton rdoChildLivWWo2;
    LinearLayout secSonLivWWo;
    View lineSonLivWWo;
    TextView VlblSonLivWWo;
    EditText txtSonLivWWo;
    LinearLayout secDaugLivWWo;
    View lineDaugLivWWo;
    TextView VlblDaugLivWWo;
    EditText txtDaugLivWWo;
    LinearLayout secChldLivOut;
    View lineChldLivOut;
    TextView VlblChldLivOut;
    RadioGroup rdogrpChldLivOut;
    RadioButton rdoChldLivOut1;
    RadioButton rdoChldLivOut2;
    LinearLayout seclbl04;
    View linelbl04;
    LinearLayout secSonLivOut;
    View lineSonLivOut;
    TextView VlblSonLivOut;
    EditText txtSonLivOut;
    LinearLayout secDaugLivOut;
    View lineDaugLivOut;
    TextView VlblDaugLivOut;
    EditText txtDaugLivOut;
    LinearLayout secEarlyAlive;
    View lineEarlyAlive;
    TextView VlblEarlyAlive;
    RadioGroup rdogrpEarlyAlive;
    RadioButton rdoEarlyAlive1;
    RadioButton rdoEarlyAlive2;
    RadioButton rdoEarlyAlive3;
    LinearLayout secEarlyAliveNo;
    View lineEarlyAliveNo;
    TextView VlblEarlyAliveNo;
    EditText txtEarlyAliveNo;
    LinearLayout secChldDie;
    View lineChldDie;
    TextView VlblChldDie;
    RadioGroup rdogrpChldDie;
    RadioButton rdoChldDie1;
    RadioButton rdoChldDie2;
    LinearLayout seclbl05;
    View linelbl05;
    LinearLayout secBoyDied;
    View lineBoyDied;
    TextView VlblBoyDied;
    EditText txtBoyDied;
    LinearLayout secGirlDied;
    View lineGirlDied;
    TextView VlblGirlDied;
    EditText txtGirlDied;
    LinearLayout secChDiedFsMon;
    View lineChDiedFsMon;
    TextView VlblChDiedFsMon;
    EditText txtChDiedFsMon;
    LinearLayout secNotLivBrth;
    View lineNotLivBrth;
    TextView VlblNotLivBrth;
    RadioGroup rdogrpNotLivBrth;
    RadioButton rdoNotLivBrth1;
    RadioButton rdoNotLivBrth2;
    LinearLayout seclbl02;
    View linelbl02;
    LinearLayout secTotNotLB;
    View lineTotNotLB;
    TextView VlblTotNotLB;
    EditText txtTotNotLB;
     LinearLayout secTotNotLBDk;
     View lineTotNotLBDk;
     TextView VlblTotNotLBDk;
     CheckBox chkTotNotLBDk;
    LinearLayout secStillBirth;
    View lineStillBirth;
    TextView VlblStillBirth;
    EditText txtStillBirth;
    LinearLayout secStillBirthDk;
    View lineStillBirthDk;
    TextView VlblStillBirthDk;
    CheckBox chkStillBirthDk;
    LinearLayout secMiscAbor;
    View lineMiscAbor;
    TextView VlblMiscAbor;
    EditText txtMiscAbor;
    LinearLayout secMiscAborDk;
    View lineMiscAborDk;
    TextView VlblMiscAborDk;
    CheckBox chkMiscAborDk;
    LinearLayout seclbl06;
    View linelbl06;
     LinearLayout secLastPregRes;
     View lineLastPregRes;
     TextView VlblLastPregRes;
     RadioGroup rdogrpLastPregRes;
     RadioButton rdoLastPregRes1;
     RadioButton rdoLastPregRes2;
     RadioButton rdoLastPregRes3;
     RadioButton rdoLastPregRes4;
    LinearLayout secLastOutDate;
    View lineLastOutDate;
    TextView VlblLastOutDate;
    EditText dtpLastOutDate;
    LinearLayout secLastOutDateDK;
    View lineLastOutDateDK;
    TextView VlblLastOutDateDK;
    CheckBox chkLastOutDateDK;
    LinearLayout secCesarean;
    View lineCesarean;
    TextView VlblCesarean;
    RadioGroup rdogrpCesarean;
    RadioButton rdoCesarean1;
    RadioButton rdoCesarean2;
    RadioButton rdoCesarean3;
    LinearLayout secCesareanNo;
    View lineCesareanNo;
    TextView VlblCesareanNo;
    EditText txtCesareanNo;
    LinearLayout secTotPregOut;
    View lineTotPregOut;
    TextView VlblTotPregOut;
    EditText txtTotPregOut;
    LinearLayout secObsNote;
    View lineObsNote;
    TextView VlblObsNote;
    EditText txtObsNote;

     LinearLayout secObsNote1;
     View lineObsNote1;

     LinearLayout secMisc;
     EditText txtMisc;
     CheckBox chkMiscDk;



     int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
     String VISIT_DATE = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String OBSMATID = "";
     String MEM_ID = "";
     String HHID = "";
     String MSlNo = "";
     String EV_TYPE = "";
     String ROUND = "";
     String SurvType = "";
     String MS = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.surv_preghis);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         OBSMATID = IDbundle.getString("ObsMatID");
         MEM_ID = IDbundle.getString("MemID");
         MS = IDbundle.getString("ms");
         HHID = IDbundle.getString("HHID");
         MSlNo = IDbundle.getString("MSlNo");
         EV_TYPE = IDbundle.getString("evtype");
         ROUND = IDbundle.getString("round");
         SurvType = IDbundle.getString("SurvType");
         VISIT_DATE = IDbundle.getString("visitdate"); //dd/mm/yyyy
         List<String> listMS = new ArrayList<>(Global_CodeList.Get_MARITAL_STATUS());
         String _ms_name = "";
         try {
             _ms_name = getTextFromCode(MS, listMS);
         }catch (Exception ex){

         }
         ((TextView)findViewById(R.id.tv_member_name)).setText(IDbundle.getString("MemName","[Name]") + " ("+ _ms_name +")");

         TableName = "tmpPregHis";
         MODULEID = 38;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Surv_PregHis.this);
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
        Connection.LocalizeLanguage(Surv_PregHis.this, MODULEID, LANGUAGEID);

         dtpObsVDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnObsVDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });
         dtpLastOutDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnLastOutDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables
         secObsVStatusOth.setVisibility(View.GONE);
         lineObsVStatusOth.setVisibility(View.GONE);
         secObsVStatusOth.setVisibility(View.GONE);
         lineObsVStatusOth.setVisibility(View.GONE);
         seclbl01.setVisibility(View.GONE);
         linelbl01.setVisibility(View.GONE);
         seclbl01l.setVisibility(View.GONE);
         linelbl01l.setVisibility(View.GONE);
         secMarMon.setVisibility(View.GONE);
         lineMarMon.setVisibility(View.GONE);
         secMarYear.setVisibility(View.GONE);
         lineMarYear.setVisibility(View.GONE);
         secMarDK.setVisibility(View.GONE);
         lineMarDK.setVisibility(View.GONE);
         secEverPreg.setVisibility(View.GONE);
         lineEverPreg.setVisibility(View.GONE);
         secTotPreg.setVisibility(View.GONE);
         lineTotPreg.setVisibility(View.GONE);
         secGaveBirth.setVisibility(View.GONE);
         lineGaveBirth.setVisibility(View.GONE);
         secChildLivWWo.setVisibility(View.GONE);
         lineChildLivWWo.setVisibility(View.GONE);
         secSonLivWWo.setVisibility(View.GONE);
         lineSonLivWWo.setVisibility(View.GONE);
         secDaugLivWWo.setVisibility(View.GONE);
         lineDaugLivWWo.setVisibility(View.GONE);
         secChldLivOut.setVisibility(View.GONE);
         lineChldLivOut.setVisibility(View.GONE);
         seclbl04.setVisibility(View.GONE);
         linelbl04.setVisibility(View.GONE);
         secSonLivOut.setVisibility(View.GONE);
         lineSonLivOut.setVisibility(View.GONE);
         secDaugLivOut.setVisibility(View.GONE);
         lineDaugLivOut.setVisibility(View.GONE);
         secEarlyAlive.setVisibility(View.GONE);
         lineEarlyAlive.setVisibility(View.GONE);
         secEarlyAliveNo.setVisibility(View.GONE);
         lineEarlyAliveNo.setVisibility(View.GONE);
         secChldDie.setVisibility(View.GONE);
         lineChldDie.setVisibility(View.GONE);
         seclbl05.setVisibility(View.GONE);
         linelbl05.setVisibility(View.GONE);
         secBoyDied.setVisibility(View.GONE);
         lineBoyDied.setVisibility(View.GONE);
         secGirlDied.setVisibility(View.GONE);
         lineGirlDied.setVisibility(View.GONE);
         secChDiedFsMon.setVisibility(View.GONE);
         lineChDiedFsMon.setVisibility(View.GONE);
         secNotLivBrth.setVisibility(View.GONE);
         lineNotLivBrth.setVisibility(View.GONE);
         seclbl02.setVisibility(View.GONE);
         linelbl02.setVisibility(View.GONE);
         secTotNotLB.setVisibility(View.GONE);
         lineTotNotLB.setVisibility(View.GONE);
         secTotNotLBDk.setVisibility(View.GONE);
         lineTotNotLBDk.setVisibility(View.GONE);
         secStillBirth.setVisibility(View.GONE);
         lineStillBirth.setVisibility(View.GONE);
         secStillBirthDk.setVisibility(View.GONE);
         lineStillBirthDk.setVisibility(View.GONE);
         secMiscAbor.setVisibility(View.GONE);
         lineMiscAbor.setVisibility(View.GONE);
         secMiscAborDk.setVisibility(View.GONE);
         lineMiscAborDk.setVisibility(View.GONE);
         seclbl06.setVisibility(View.GONE);
         linelbl06.setVisibility(View.GONE);
         secLastPregRes.setVisibility(View.GONE);
         lineLastPregRes.setVisibility(View.GONE);
         secLastOutDate.setVisibility(View.GONE);
         lineLastOutDate.setVisibility(View.GONE);
         secLastOutDateDK.setVisibility(View.GONE);
         lineLastOutDateDK.setVisibility(View.GONE);
         secCesarean.setVisibility(View.GONE);
         lineCesarean.setVisibility(View.GONE);
         secCesareanNo.setVisibility(View.GONE);
         lineCesareanNo.setVisibility(View.GONE);
         secTotPregOut.setVisibility(View.GONE);
         lineTotPregOut.setVisibility(View.GONE);
         secObsVStatusOth.setVisibility(View.GONE);
         lineObsVStatusOth.setVisibility(View.GONE);
         /*seclbl01.setVisibility(View.GONE);
         linelbl01.setVisibility(View.GONE);
         seclbl01l.setVisibility(View.GONE);
         linelbl01l.setVisibility(View.GONE);
         secMarMon.setVisibility(View.GONE);
         lineMarMon.setVisibility(View.GONE);
         secMarYear.setVisibility(View.GONE);
         lineMarYear.setVisibility(View.GONE);
         secMarDK.setVisibility(View.GONE);
         lineMarDK.setVisibility(View.GONE);
         secEverPreg.setVisibility(View.GONE);
         lineEverPreg.setVisibility(View.GONE);
         secTotPreg.setVisibility(View.GONE);
         lineTotPreg.setVisibility(View.GONE);*/
         secGaveBirth.setVisibility(View.GONE);
         lineGaveBirth.setVisibility(View.GONE);
         secChildLivWWo.setVisibility(View.GONE);
         lineChildLivWWo.setVisibility(View.GONE);
         secSonLivWWo.setVisibility(View.GONE);
         lineSonLivWWo.setVisibility(View.GONE);
         secDaugLivWWo.setVisibility(View.GONE);
         lineDaugLivWWo.setVisibility(View.GONE);
         secChldLivOut.setVisibility(View.GONE);
         lineChldLivOut.setVisibility(View.GONE);
         seclbl04.setVisibility(View.GONE);
         linelbl04.setVisibility(View.GONE);
         secSonLivOut.setVisibility(View.GONE);
         lineSonLivOut.setVisibility(View.GONE);
         secDaugLivOut.setVisibility(View.GONE);
         lineDaugLivOut.setVisibility(View.GONE);
         secEarlyAlive.setVisibility(View.GONE);
         lineEarlyAlive.setVisibility(View.GONE);
         secEarlyAliveNo.setVisibility(View.GONE);
         lineEarlyAliveNo.setVisibility(View.GONE);
         secChldDie.setVisibility(View.GONE);
         lineChldDie.setVisibility(View.GONE);
         seclbl05.setVisibility(View.GONE);
         linelbl05.setVisibility(View.GONE);
         secBoyDied.setVisibility(View.GONE);
         lineBoyDied.setVisibility(View.GONE);
         secGirlDied.setVisibility(View.GONE);
         lineGirlDied.setVisibility(View.GONE);
         secChDiedFsMon.setVisibility(View.GONE);
         lineChDiedFsMon.setVisibility(View.GONE);
         secNotLivBrth.setVisibility(View.GONE);
         lineNotLivBrth.setVisibility(View.GONE);
         seclbl02.setVisibility(View.GONE);
         linelbl02.setVisibility(View.GONE);
         secTotNotLB.setVisibility(View.GONE);
         lineTotNotLB.setVisibility(View.GONE);
         secTotNotLBDk.setVisibility(View.GONE);
         lineTotNotLBDk.setVisibility(View.GONE);
         secStillBirth.setVisibility(View.GONE);
         lineStillBirth.setVisibility(View.GONE);
         secStillBirthDk.setVisibility(View.GONE);
         lineStillBirthDk.setVisibility(View.GONE);
         secMiscAbor.setVisibility(View.GONE);
         lineMiscAbor.setVisibility(View.GONE);
         secMiscAborDk.setVisibility(View.GONE);
         lineMiscAborDk.setVisibility(View.GONE);
         seclbl06.setVisibility(View.GONE);
         linelbl06.setVisibility(View.GONE);
         secLastPregRes.setVisibility(View.GONE);
         lineLastPregRes.setVisibility(View.GONE);
         secLastOutDate.setVisibility(View.GONE);
         lineLastOutDate.setVisibility(View.GONE);
         secLastOutDateDK.setVisibility(View.GONE);
         lineLastOutDateDK.setVisibility(View.GONE);
         secCesarean.setVisibility(View.GONE);
         lineCesarean.setVisibility(View.GONE);
         secCesareanNo.setVisibility(View.GONE);
         lineCesareanNo.setVisibility(View.GONE);
         secTotPregOut.setVisibility(View.GONE);
         lineTotPregOut.setVisibility(View.GONE);
         secTotPreg.setVisibility(View.GONE);
         lineTotPreg.setVisibility(View.GONE);
         secGaveBirth.setVisibility(View.GONE);
         lineGaveBirth.setVisibility(View.GONE);
         secChildLivWWo.setVisibility(View.GONE);
         lineChildLivWWo.setVisibility(View.GONE);
         secSonLivWWo.setVisibility(View.GONE);
         lineSonLivWWo.setVisibility(View.GONE);
         secDaugLivWWo.setVisibility(View.GONE);
         lineDaugLivWWo.setVisibility(View.GONE);
         secChldLivOut.setVisibility(View.GONE);
         lineChldLivOut.setVisibility(View.GONE);
         seclbl04.setVisibility(View.GONE);
         linelbl04.setVisibility(View.GONE);
         secSonLivOut.setVisibility(View.GONE);
         lineSonLivOut.setVisibility(View.GONE);
         secDaugLivOut.setVisibility(View.GONE);
         lineDaugLivOut.setVisibility(View.GONE);
         secEarlyAlive.setVisibility(View.GONE);
         lineEarlyAlive.setVisibility(View.GONE);
         secEarlyAliveNo.setVisibility(View.GONE);
         lineEarlyAliveNo.setVisibility(View.GONE);
         secChldDie.setVisibility(View.GONE);
         lineChldDie.setVisibility(View.GONE);
         seclbl05.setVisibility(View.GONE);
         linelbl05.setVisibility(View.GONE);
         secBoyDied.setVisibility(View.GONE);
         lineBoyDied.setVisibility(View.GONE);
         secGirlDied.setVisibility(View.GONE);
         lineGirlDied.setVisibility(View.GONE);
         secChDiedFsMon.setVisibility(View.GONE);
         lineChDiedFsMon.setVisibility(View.GONE);
         secNotLivBrth.setVisibility(View.GONE);
         lineNotLivBrth.setVisibility(View.GONE);
         seclbl02.setVisibility(View.GONE);
         linelbl02.setVisibility(View.GONE);
         secTotNotLB.setVisibility(View.GONE);
         lineTotNotLB.setVisibility(View.GONE);
         secTotNotLBDk.setVisibility(View.GONE);
         lineTotNotLBDk.setVisibility(View.GONE);
         secStillBirth.setVisibility(View.GONE);
         lineStillBirth.setVisibility(View.GONE);
         secStillBirthDk.setVisibility(View.GONE);
         lineStillBirthDk.setVisibility(View.GONE);
         secMiscAbor.setVisibility(View.GONE);
         lineMiscAbor.setVisibility(View.GONE);
         secMiscAborDk.setVisibility(View.GONE);
         lineMiscAborDk.setVisibility(View.GONE);
         seclbl06.setVisibility(View.GONE);
         linelbl06.setVisibility(View.GONE);
         secLastPregRes.setVisibility(View.GONE);
         lineLastPregRes.setVisibility(View.GONE);
         secLastOutDate.setVisibility(View.GONE);
         lineLastOutDate.setVisibility(View.GONE);
         secLastOutDateDK.setVisibility(View.GONE);
         lineLastOutDateDK.setVisibility(View.GONE);
         secCesarean.setVisibility(View.GONE);
         lineCesarean.setVisibility(View.GONE);
         secCesareanNo.setVisibility(View.GONE);
         lineCesareanNo.setVisibility(View.GONE);
         secTotPregOut.setVisibility(View.GONE);
         lineTotPregOut.setVisibility(View.GONE);
         secChildLivWWo.setVisibility(View.GONE);
         lineChildLivWWo.setVisibility(View.GONE);
         secSonLivWWo.setVisibility(View.GONE);
         lineSonLivWWo.setVisibility(View.GONE);
         secDaugLivWWo.setVisibility(View.GONE);
         lineDaugLivWWo.setVisibility(View.GONE);
         secChldLivOut.setVisibility(View.GONE);
         lineChldLivOut.setVisibility(View.GONE);
         seclbl04.setVisibility(View.GONE);
         linelbl04.setVisibility(View.GONE);
         secSonLivOut.setVisibility(View.GONE);
         lineSonLivOut.setVisibility(View.GONE);
         secDaugLivOut.setVisibility(View.GONE);
         lineDaugLivOut.setVisibility(View.GONE);
         secEarlyAlive.setVisibility(View.GONE);
         lineEarlyAlive.setVisibility(View.GONE);
         secEarlyAliveNo.setVisibility(View.GONE);
         lineEarlyAliveNo.setVisibility(View.GONE);
         secSonLivWWo.setVisibility(View.GONE);
         lineSonLivWWo.setVisibility(View.GONE);
         secDaugLivWWo.setVisibility(View.GONE);
         lineDaugLivWWo.setVisibility(View.GONE);
         seclbl04.setVisibility(View.GONE);
         linelbl04.setVisibility(View.GONE);
         secSonLivOut.setVisibility(View.GONE);
         lineSonLivOut.setVisibility(View.GONE);
         secDaugLivOut.setVisibility(View.GONE);
         lineDaugLivOut.setVisibility(View.GONE);
         seclbl05.setVisibility(View.GONE);
         linelbl05.setVisibility(View.GONE);
         secBoyDied.setVisibility(View.GONE);
         lineBoyDied.setVisibility(View.GONE);
         secGirlDied.setVisibility(View.GONE);
         lineGirlDied.setVisibility(View.GONE);
         secChDiedFsMon.setVisibility(View.GONE);
         lineChDiedFsMon.setVisibility(View.GONE);
         seclbl02.setVisibility(View.GONE);
         linelbl02.setVisibility(View.GONE);
         secTotNotLB.setVisibility(View.GONE);
         lineTotNotLB.setVisibility(View.GONE);
         secTotNotLBDk.setVisibility(View.GONE);
         lineTotNotLBDk.setVisibility(View.GONE);
         secStillBirth.setVisibility(View.GONE);
         lineStillBirth.setVisibility(View.GONE);
         secStillBirthDk.setVisibility(View.GONE);
         lineStillBirthDk.setVisibility(View.GONE);
         secMiscAbor.setVisibility(View.GONE);
         lineMiscAbor.setVisibility(View.GONE);
         secMiscAborDk.setVisibility(View.GONE);
         lineMiscAborDk.setVisibility(View.GONE);
         secCesareanNo.setVisibility(View.GONE);
         lineCesareanNo.setVisibility(View.GONE);
         secCesareanNo.setVisibility(View.GONE);
         lineCesareanNo.setVisibility(View.GONE);


        DataSearch(MEM_ID);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_PregHis.this, e.getMessage());
         return;
     }
 }

     public  String getTextFromCode(String code, List<String> listMS) {
        String _ms_name = "";
         if(code.length()==0){
             _ms_name = "Marital Status(missing)";
         }else {
             // Iterate over the listEDU array list
             for (String item : listMS) {
                 // Split the item into code and text using "-"
                 String[] parts = item.split("-");
                 // If the code matches the code portion of the item, return the text
                 if (parts[0].trim().equals(code)) {
                     // Return the text portion of the item
                     _ms_name = parts[1].trim();
                 }
             }
         }
         // If no match is found, return null or an appropriate default value
         return _ms_name;
     }

 private String GetTotalBirth()
 {
     int q9_1 = Integer.parseInt(txtSonLivWWo.getText().toString().length()==0?"0":txtSonLivWWo.getText().toString());
     int q9_2 = Integer.parseInt(txtDaugLivWWo.getText().toString().length()==0?"0":txtDaugLivWWo.getText().toString());
     int q11_1 = Integer.parseInt(txtSonLivOut.getText().toString().length()==0?"0":txtSonLivOut.getText().toString());
     int q11_2 = Integer.parseInt(txtDaugLivOut.getText().toString().length()==0?"0":txtDaugLivOut.getText().toString());
     int q14_1 = Integer.parseInt(txtBoyDied.getText().toString().length()==0?"0":txtBoyDied.getText().toString());
     int q14_2 = Integer.parseInt(txtGirlDied.getText().toString().length()==0?"0":txtGirlDied.getText().toString());
     int q17 = Integer.parseInt(txtTotNotLB.getText().toString().length()==0?"0":txtTotNotLB.getText().toString());
     String total = String.valueOf(q9_1 + q9_2 + q11_1 + q11_2 + q14_1 + q14_2 + q17);

     return total;
 }

 private void Initialization()
 {
   try
     {
         secMisc=findViewById(R.id.secMisc) ;
         txtMisc=findViewById(R.id.txtMisc) ;
         chkMiscDk=findViewById(R.id.chkMiscDk) ;
         txtMisc.addTextChangedListener(new TextWatcher() {
             public void afterTextChanged(Editable s) {}
             public void beforeTextChanged(CharSequence s, int start,
                                           int count, int after) {
             }

             public void onTextChanged(CharSequence s, int start,
                                       int before, int count) {
                 if(txtMisc.getText().length()>0) chkMiscDk.setChecked(false);
             }
         });

         chkMiscDk.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 if (((CheckBox) v).isChecked()) {
                     txtMisc.setEnabled(false);
                     txtMisc.setText("");
                 }
                 else {
                     txtMisc.setEnabled(true);
                 }
             }
         });


         secObsMatID=(LinearLayout)findViewById(R.id.secObsMatID);
         lineObsMatID=(View)findViewById(R.id.lineObsMatID);
         VlblObsMatID=(TextView) findViewById(R.id.VlblObsMatID);
         txtObsMatID=(EditText) findViewById(R.id.txtObsMatID);
//         txtObsMatID.setText(OBSMATID);
//         txtObsMatID.setEnabled(false);

         if(OBSMATID.length()==0) txtObsMatID.setText(Global.Get_UUID(DEVICEID));
         else txtObsMatID.setText(OBSMATID);
         txtObsMatID.setEnabled(false);

         secMemID=(LinearLayout)findViewById(R.id.secMemID);
         lineMemID=(View)findViewById(R.id.lineMemID);
         VlblMemID=(TextView) findViewById(R.id.VlblMemID);
         txtMemID=(EditText) findViewById(R.id.txtMemID);
         txtMemID.setText(MEM_ID);
         txtMemID.setEnabled(false);

         secHHID=(LinearLayout)findViewById(R.id.secHHID);
         lineHHID=(View)findViewById(R.id.lineHHID);
         VlblHHID=(TextView) findViewById(R.id.VlblHHID);
         txtHHID=(EditText) findViewById(R.id.txtHHID);
         txtHHID.setText(HHID);
         txtHHID.setEnabled(false);

         String MemberSl= C.ReturnSingleValue("Select MSlNo from tmpMember where HHID='"+ HHID +"' and MemID='"+ MEM_ID +"'");

         spnMSlNo1=(Spinner) findViewById(R.id.spnMSlNo1);
         secMSlNo=(LinearLayout)findViewById(R.id.secMSlNo);
         lineMSlNo=(View)findViewById(R.id.lineMSlNo);
         VlblMSlNo=(TextView) findViewById(R.id.VlblMSlNo);
         txtMSlNo=(EditText) findViewById(R.id.txtMSlNo);
         txtMSlNo.setText(MemberSl);

//         txtMSlNo.setEnabled(false);
//         if (SurvType.equals("1")){
//             spnMSlNo1.setVisibility(View.VISIBLE);
//         }else {
//             spnMSlNo1.setVisibility(View.GONE);
//             txtMSlNo.setText(MemberSl);
//         }

//         spnMSlNo1.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where HHID='"+ HHID + "' and (julianday('now')-julianday(BDate))<=18261 and Sex='2' and MS<>'00'"));
//         spnMSlNo1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                     String MSL = spnMSlNo1.getSelectedItemPosition()==0?"":Global.Left(spnMSlNo1.getSelectedItem().toString(),2);
//                     String Sl= C.ReturnSingleValue("Select '' union Select MSlNo||'-'||Name from Member Where HHID='"+ HHID + "' and (julianday('now')-julianday(BDate))<=18261 and Sex='2' and MS<>'00'");
//                     txtMSlNo.setText(Sl);
//                     DataSearch(MEM_ID);
//             }
//             public void onNothingSelected(AdapterView<?> parentView) {
//
//             }
//         });

         secObsVDate=(LinearLayout)findViewById(R.id.secObsVDate);
         lineObsVDate=(View)findViewById(R.id.lineObsVDate);
         VlblObsVDate=(TextView) findViewById(R.id.VlblObsVDate);
         dtpObsVDate=(EditText) findViewById(R.id.dtpObsVDate);
         dtpObsVDate.setText(Global.DateNowDMY());

         secObsVStatus=(LinearLayout)findViewById(R.id.secObsVStatus);
         lineObsVStatus=(View)findViewById(R.id.lineObsVStatus);
         VlblObsVStatus=(TextView) findViewById(R.id.VlblObsVStatus);
         spnObsVStatus=(Spinner) findViewById(R.id.spnObsVStatus);
         List<String> listObsVStatus = new ArrayList<String>();
         listObsVStatus.add("");
         listObsVStatus.add("1-Interview complete");
         listObsVStatus.add("2-Interview partially complete");
         listObsVStatus.add("3-Refused to give interview");
         listObsVStatus.add("4-Absent");
         listObsVStatus.add("7-Other");
         spnObsVStatus.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listObsVStatus)));

         spnObsVStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 if (spnObsVStatus.getSelectedItem().toString().length() == 0) {
                     /*seclbl01.setVisibility(View.GONE);
                     linelbl01.setVisibility(View.GONE);
                     seclbl01l.setVisibility(View.GONE);
                     linelbl01l.setVisibility(View.GONE);
                     secMarMon.setVisibility(View.GONE);
                     lineMarMon.setVisibility(View.GONE);
                     spnMarMon.setSelection(0);
                     secMarYear.setVisibility(View.GONE);
                     lineMarYear.setVisibility(View.GONE);
                     spnMarYear.setSelection(0);
                     secMarDK.setVisibility(View.GONE);
                     lineMarDK.setVisibility(View.GONE);
                     chkMarDK.setChecked(false);
                     secEverPreg.setVisibility(View.GONE);
                     lineEverPreg.setVisibility(View.GONE);
                     rdogrpEverPreg.clearCheck();*/
                     secTotPreg.setVisibility(View.GONE);
                     lineTotPreg.setVisibility(View.GONE);
                     txtTotPreg.setText("");
                     secGaveBirth.setVisibility(View.GONE);
                     lineGaveBirth.setVisibility(View.GONE);
                     rdogrpGaveBirth.clearCheck();
                     secChildLivWWo.setVisibility(View.GONE);
                     lineChildLivWWo.setVisibility(View.GONE);
                     rdogrpChildLivWWo.clearCheck();
                     secSonLivWWo.setVisibility(View.GONE);
                     lineSonLivWWo.setVisibility(View.GONE);
                     txtSonLivWWo.setText("");
                     secDaugLivWWo.setVisibility(View.GONE);
                     lineDaugLivWWo.setVisibility(View.GONE);
                     txtDaugLivWWo.setText("");
                     secChldLivOut.setVisibility(View.GONE);
                     lineChldLivOut.setVisibility(View.GONE);
                     rdogrpChldLivOut.clearCheck();
                     seclbl04.setVisibility(View.GONE);
                     linelbl04.setVisibility(View.GONE);
                     secSonLivOut.setVisibility(View.GONE);
                     lineSonLivOut.setVisibility(View.GONE);
                     txtSonLivOut.setText("");
                     secDaugLivOut.setVisibility(View.GONE);
                     lineDaugLivOut.setVisibility(View.GONE);
                     txtDaugLivOut.setText("");
                     secEarlyAlive.setVisibility(View.GONE);
                     lineEarlyAlive.setVisibility(View.GONE);
                     rdogrpEarlyAlive.clearCheck();
                     secEarlyAliveNo.setVisibility(View.GONE);
                     lineEarlyAliveNo.setVisibility(View.GONE);
                     txtEarlyAliveNo.setText("");
                     secChldDie.setVisibility(View.GONE);
                     lineChldDie.setVisibility(View.GONE);
                     rdogrpChldDie.clearCheck();
                     seclbl05.setVisibility(View.GONE);
                     linelbl05.setVisibility(View.GONE);
                     secBoyDied.setVisibility(View.GONE);
                     lineBoyDied.setVisibility(View.GONE);
                     txtBoyDied.setText("");
                     secGirlDied.setVisibility(View.GONE);
                     lineGirlDied.setVisibility(View.GONE);
                     txtGirlDied.setText("");
                     secChDiedFsMon.setVisibility(View.GONE);
                     lineChDiedFsMon.setVisibility(View.GONE);
                     txtChDiedFsMon.setText("");
                     secNotLivBrth.setVisibility(View.GONE);
                     lineNotLivBrth.setVisibility(View.GONE);
                     rdogrpNotLivBrth.clearCheck();
                     seclbl02.setVisibility(View.GONE);
                     linelbl02.setVisibility(View.GONE);
                     secTotNotLB.setVisibility(View.GONE);
                     lineTotNotLB.setVisibility(View.GONE);
                     txtTotNotLB.setText("");
                     secTotNotLBDk.setVisibility(View.GONE);
                     lineTotNotLBDk.setVisibility(View.GONE);
                     chkTotNotLBDk.setChecked(false);
                     secStillBirth.setVisibility(View.GONE);
                     lineStillBirth.setVisibility(View.GONE);
                     txtStillBirth.setText("");
                     secStillBirthDk.setVisibility(View.GONE);
                     lineStillBirthDk.setVisibility(View.GONE);
                     chkStillBirthDk.setChecked(false);
                     secMiscAbor.setVisibility(View.GONE);
                     secMisc.setVisibility(View.GONE);
                     txtMisc.setText("");
                     lineMiscAbor.setVisibility(View.GONE);
                     txtMiscAbor.setText("");
                     secMiscAborDk.setVisibility(View.GONE);
                     lineMiscAborDk.setVisibility(View.GONE);
                     chkMiscAborDk.setChecked(false);
                     seclbl06.setVisibility(View.GONE);
                     linelbl06.setVisibility(View.GONE);
                     secLastPregRes.setVisibility(View.GONE);
                     lineLastPregRes.setVisibility(View.GONE);
                     rdogrpLastPregRes.clearCheck();
                     secLastOutDate.setVisibility(View.GONE);
                     lineLastOutDate.setVisibility(View.GONE);
                     dtpLastOutDate.setText("");
                     secLastOutDateDK.setVisibility(View.GONE);
                     lineLastOutDateDK.setVisibility(View.GONE);
                     chkLastOutDateDK.setChecked(false);
                     secCesarean.setVisibility(View.GONE);
                     lineCesarean.setVisibility(View.GONE);
                     rdogrpCesarean.clearCheck();
                     secCesareanNo.setVisibility(View.GONE);
                     lineCesareanNo.setVisibility(View.GONE);
                     txtCesareanNo.setText("");
                     secTotPregOut.setVisibility(View.GONE);
                     lineTotPregOut.setVisibility(View.GONE);
                     txtTotPregOut.setText("");
                 }

                 String spnData = "";
                 spnData = Connection.SelectedSpinnerValue(spnObsVStatus.getSelectedItem().toString(), "-");
                 if(spnData.equalsIgnoreCase("7"))
                 {
                    secObsVStatusOth.setVisibility(View.VISIBLE);
                    lineObsVStatusOth.setVisibility(View.VISIBLE);
                     seclbl01.setVisibility(View.GONE);
                     linelbl01.setVisibility(View.GONE);
                     seclbl01l.setVisibility(View.GONE);
                     linelbl01l.setVisibility(View.GONE);
                     secMarMon.setVisibility(View.GONE);
                     lineMarMon.setVisibility(View.GONE);
                     spnMarMon.setSelection(0);
                     secMarYear.setVisibility(View.GONE);
                     lineMarYear.setVisibility(View.GONE);
                     spnMarYear.setSelection(0);
                     secMarDK.setVisibility(View.GONE);
                     lineMarDK.setVisibility(View.GONE);
                     chkMarDK.setChecked(false);
                     secEverPreg.setVisibility(View.GONE);
                     lineEverPreg.setVisibility(View.GONE);
                     rdogrpEverPreg.clearCheck();
                     secTotPreg.setVisibility(View.GONE);
                     lineTotPreg.setVisibility(View.GONE);
                     txtTotPreg.setText("");
                     secGaveBirth.setVisibility(View.GONE);
                     lineGaveBirth.setVisibility(View.GONE);
                     rdogrpGaveBirth.clearCheck();
                     secChildLivWWo.setVisibility(View.GONE);
                     lineChildLivWWo.setVisibility(View.GONE);
                     rdogrpChildLivWWo.clearCheck();
                     secSonLivWWo.setVisibility(View.GONE);
                     lineSonLivWWo.setVisibility(View.GONE);
                     txtSonLivWWo.setText("");
                     secDaugLivWWo.setVisibility(View.GONE);
                     lineDaugLivWWo.setVisibility(View.GONE);
                     txtDaugLivWWo.setText("");
                     secChldLivOut.setVisibility(View.GONE);
                     lineChldLivOut.setVisibility(View.GONE);
                     rdogrpChldLivOut.clearCheck();
                     seclbl04.setVisibility(View.GONE);
                     linelbl04.setVisibility(View.GONE);
                     secSonLivOut.setVisibility(View.GONE);
                     lineSonLivOut.setVisibility(View.GONE);
                     txtSonLivOut.setText("");
                     secDaugLivOut.setVisibility(View.GONE);
                     lineDaugLivOut.setVisibility(View.GONE);
                     txtDaugLivOut.setText("");
                     secEarlyAlive.setVisibility(View.GONE);
                     lineEarlyAlive.setVisibility(View.GONE);
                     rdogrpEarlyAlive.clearCheck();
                     secEarlyAliveNo.setVisibility(View.GONE);
                     lineEarlyAliveNo.setVisibility(View.GONE);
                     txtEarlyAliveNo.setText("");
                     secChldDie.setVisibility(View.GONE);
                     lineChldDie.setVisibility(View.GONE);
                     rdogrpChldDie.clearCheck();
                     seclbl05.setVisibility(View.GONE);
                     linelbl05.setVisibility(View.GONE);
                     secBoyDied.setVisibility(View.GONE);
                     lineBoyDied.setVisibility(View.GONE);
                     txtBoyDied.setText("");
                     secGirlDied.setVisibility(View.GONE);
                     lineGirlDied.setVisibility(View.GONE);
                     txtGirlDied.setText("");
                     secChDiedFsMon.setVisibility(View.GONE);
                     lineChDiedFsMon.setVisibility(View.GONE);
                     txtChDiedFsMon.setText("");
                     secNotLivBrth.setVisibility(View.GONE);
                     lineNotLivBrth.setVisibility(View.GONE);
                     rdogrpNotLivBrth.clearCheck();
                     seclbl02.setVisibility(View.GONE);
                     linelbl02.setVisibility(View.GONE);
                     secTotNotLB.setVisibility(View.GONE);
                     lineTotNotLB.setVisibility(View.GONE);
                     txtTotNotLB.setText("");
                     secTotNotLBDk.setVisibility(View.GONE);
                     lineTotNotLBDk.setVisibility(View.GONE);
                     chkTotNotLBDk.setChecked(false);
                     secStillBirth.setVisibility(View.GONE);
                     lineStillBirth.setVisibility(View.GONE);
                     txtStillBirth.setText("");
                     secStillBirthDk.setVisibility(View.GONE);
                     lineStillBirthDk.setVisibility(View.GONE);
                     chkStillBirthDk.setChecked(false);
                     secMisc.setVisibility(View.GONE);
                     txtMisc.setText("");
                     secMiscAbor.setVisibility(View.GONE);
                     lineMiscAbor.setVisibility(View.GONE);
                     txtMiscAbor.setText("");
                     secMiscAborDk.setVisibility(View.GONE);
                     lineMiscAborDk.setVisibility(View.GONE);
                     chkMiscAborDk.setChecked(false);
                     seclbl06.setVisibility(View.GONE);
                     linelbl06.setVisibility(View.GONE);
                     secLastPregRes.setVisibility(View.GONE);
                     lineLastPregRes.setVisibility(View.GONE);
                     rdogrpLastPregRes.clearCheck();
                     secLastOutDate.setVisibility(View.GONE);
                     lineLastOutDate.setVisibility(View.GONE);
                     dtpLastOutDate.setText("");
                     secLastOutDateDK.setVisibility(View.GONE);
                     lineLastOutDateDK.setVisibility(View.GONE);
                     chkLastOutDateDK.setChecked(false);
                     secCesarean.setVisibility(View.GONE);
                     lineCesarean.setVisibility(View.GONE);
                     rdogrpCesarean.clearCheck();
                     secCesareanNo.setVisibility(View.GONE);
                     lineCesareanNo.setVisibility(View.GONE);
                     txtCesareanNo.setText("");
                     secTotPregOut.setVisibility(View.GONE);
                     lineTotPregOut.setVisibility(View.GONE);
                     txtTotPregOut.setText("");
                 }
                 else if(spnData.equalsIgnoreCase("3")||spnData.equalsIgnoreCase("4"))
                 {
                    secObsVStatusOth.setVisibility(View.GONE);
                    lineObsVStatusOth.setVisibility(View.GONE);
                    txtObsVStatusOth.setText("");
                    seclbl01.setVisibility(View.GONE);
                    linelbl01.setVisibility(View.GONE);
                     seclbl01l.setVisibility(View.GONE);
                     linelbl01l.setVisibility(View.GONE);
                    secMarMon.setVisibility(View.GONE);
                    lineMarMon.setVisibility(View.GONE);
                     spnMarMon.setSelection(0);
                    secMarYear.setVisibility(View.GONE);
                    lineMarYear.setVisibility(View.GONE);
                    spnMarYear.setSelection(0);
                    secMarDK.setVisibility(View.GONE);
                    lineMarDK.setVisibility(View.GONE);
                    chkMarDK.setChecked(false);
                    secEverPreg.setVisibility(View.GONE);
                    lineEverPreg.setVisibility(View.GONE);
                    rdogrpEverPreg.clearCheck();
                    secTotPreg.setVisibility(View.GONE);
                    lineTotPreg.setVisibility(View.GONE);
                    txtTotPreg.setText("");
                    secGaveBirth.setVisibility(View.GONE);
                    lineGaveBirth.setVisibility(View.GONE);
                    rdogrpGaveBirth.clearCheck();
                     secChildLivWWo.setVisibility(View.GONE);
                     lineChildLivWWo.setVisibility(View.GONE);
                     rdogrpChildLivWWo.clearCheck();
                    secSonLivWWo.setVisibility(View.GONE);
                    lineSonLivWWo.setVisibility(View.GONE);
                    txtSonLivWWo.setText("");
                    secDaugLivWWo.setVisibility(View.GONE);
                    lineDaugLivWWo.setVisibility(View.GONE);
                    txtDaugLivWWo.setText("");
                    secChldLivOut.setVisibility(View.GONE);
                    lineChldLivOut.setVisibility(View.GONE);
                    rdogrpChldLivOut.clearCheck();
                    seclbl04.setVisibility(View.GONE);
                    linelbl04.setVisibility(View.GONE);
                    secSonLivOut.setVisibility(View.GONE);
                    lineSonLivOut.setVisibility(View.GONE);
                    txtSonLivOut.setText("");
                    secDaugLivOut.setVisibility(View.GONE);
                    lineDaugLivOut.setVisibility(View.GONE);
                    txtDaugLivOut.setText("");
                    secEarlyAlive.setVisibility(View.GONE);
                    lineEarlyAlive.setVisibility(View.GONE);
                    rdogrpEarlyAlive.clearCheck();
                    secEarlyAliveNo.setVisibility(View.GONE);
                    lineEarlyAliveNo.setVisibility(View.GONE);
                    txtEarlyAliveNo.setText("");
                    secChldDie.setVisibility(View.GONE);
                    lineChldDie.setVisibility(View.GONE);
                    rdogrpChldDie.clearCheck();
                    seclbl05.setVisibility(View.GONE);
                    linelbl05.setVisibility(View.GONE);
                    secBoyDied.setVisibility(View.GONE);
                    lineBoyDied.setVisibility(View.GONE);
                    txtBoyDied.setText("");
                    secGirlDied.setVisibility(View.GONE);
                    lineGirlDied.setVisibility(View.GONE);
                    txtGirlDied.setText("");
                    secChDiedFsMon.setVisibility(View.GONE);
                    lineChDiedFsMon.setVisibility(View.GONE);
                    txtChDiedFsMon.setText("");
                    secNotLivBrth.setVisibility(View.GONE);
                    lineNotLivBrth.setVisibility(View.GONE);
                    rdogrpNotLivBrth.clearCheck();
                    seclbl02.setVisibility(View.GONE);
                    linelbl02.setVisibility(View.GONE);
                    secTotNotLB.setVisibility(View.GONE);
                    lineTotNotLB.setVisibility(View.GONE);
                    txtTotNotLB.setText("");
                    secTotNotLBDk.setVisibility(View.GONE);
                    lineTotNotLBDk.setVisibility(View.GONE);
                     chkTotNotLBDk.setChecked(false);
                    secStillBirth.setVisibility(View.GONE);
                    lineStillBirth.setVisibility(View.GONE);
                    txtStillBirth.setText("");
                    secStillBirthDk.setVisibility(View.GONE);
                    lineStillBirthDk.setVisibility(View.GONE);
                    chkStillBirthDk.setChecked(false);
                    secMisc.setVisibility(View.GONE);
                    txtMisc.setText("");
                    secMiscAbor.setVisibility(View.GONE);
                    lineMiscAbor.setVisibility(View.GONE);
                    txtMiscAbor.setText("");
                    secMiscAborDk.setVisibility(View.GONE);
                    lineMiscAborDk.setVisibility(View.GONE);
                    chkMiscAborDk.setChecked(false);
                    seclbl06.setVisibility(View.GONE);
                    linelbl06.setVisibility(View.GONE);
                     secLastPregRes.setVisibility(View.GONE);
                     lineLastPregRes.setVisibility(View.GONE);
                     rdogrpLastPregRes.clearCheck();
                    secLastOutDate.setVisibility(View.GONE);
                    lineLastOutDate.setVisibility(View.GONE);
                    dtpLastOutDate.setText("");
                    secLastOutDateDK.setVisibility(View.GONE);
                    lineLastOutDateDK.setVisibility(View.GONE);
                    chkLastOutDateDK.setChecked(false);
                    secCesarean.setVisibility(View.GONE);
                    lineCesarean.setVisibility(View.GONE);
                    rdogrpCesarean.clearCheck();
                    secCesareanNo.setVisibility(View.GONE);
                    lineCesareanNo.setVisibility(View.GONE);
                    txtCesareanNo.setText("");
                    secTotPregOut.setVisibility(View.GONE);
                    lineTotPregOut.setVisibility(View.GONE);
                    txtTotPregOut.setText("");
                 }
                 /*else if(spnData.equalsIgnoreCase("2"))
                 {
                    secObsVStatusOth.setVisibility(View.GONE);
                    lineObsVStatusOth.setVisibility(View.GONE);
                    txtObsVStatusOth.setText("");
                    seclbl01.setVisibility(View.GONE);
                    linelbl01.setVisibility(View.GONE);
                     seclbl01l.setVisibility(View.GONE);
                     linelbl01l.setVisibility(View.GONE);
                    secMarMon.setVisibility(View.GONE);
                    lineMarMon.setVisibility(View.GONE);
                     spnMarMon.setSelection(0);
                    secMarYear.setVisibility(View.GONE);
                    lineMarYear.setVisibility(View.GONE);
                    spnMarYear.setSelection(0);
                    secMarDK.setVisibility(View.GONE);
                    lineMarDK.setVisibility(View.GONE);
                    chkMarDK.setChecked(false);
                    secEverPreg.setVisibility(View.GONE);
                    lineEverPreg.setVisibility(View.GONE);
                    rdogrpEverPreg.clearCheck();
                    secTotPreg.setVisibility(View.GONE);
                    lineTotPreg.setVisibility(View.GONE);
                    txtTotPreg.setText("");
                    secGaveBirth.setVisibility(View.GONE);
                    lineGaveBirth.setVisibility(View.GONE);
                    rdogrpGaveBirth.clearCheck();
                     secChildLivWWo.setVisibility(View.GONE);
                     lineChildLivWWo.setVisibility(View.GONE);
                     rdogrpChildLivWWo.clearCheck();
                    secSonLivWWo.setVisibility(View.GONE);
                    lineSonLivWWo.setVisibility(View.GONE);
                    txtSonLivWWo.setText("");
                    secDaugLivWWo.setVisibility(View.GONE);
                    lineDaugLivWWo.setVisibility(View.GONE);
                    txtDaugLivWWo.setText("");
                    secChldLivOut.setVisibility(View.GONE);
                    lineChldLivOut.setVisibility(View.GONE);
                    rdogrpChldLivOut.clearCheck();
                    seclbl04.setVisibility(View.GONE);
                    linelbl04.setVisibility(View.GONE);
                    secSonLivOut.setVisibility(View.GONE);
                    lineSonLivOut.setVisibility(View.GONE);
                    txtSonLivOut.setText("");
                    secDaugLivOut.setVisibility(View.GONE);
                    lineDaugLivOut.setVisibility(View.GONE);
                    txtDaugLivOut.setText("");
                    secEarlyAlive.setVisibility(View.GONE);
                    lineEarlyAlive.setVisibility(View.GONE);
                    rdogrpEarlyAlive.clearCheck();
                    secEarlyAliveNo.setVisibility(View.GONE);
                    lineEarlyAliveNo.setVisibility(View.GONE);
                    txtEarlyAliveNo.setText("");
                    secChldDie.setVisibility(View.GONE);
                    lineChldDie.setVisibility(View.GONE);
                    rdogrpChldDie.clearCheck();
                    seclbl05.setVisibility(View.GONE);
                    linelbl05.setVisibility(View.GONE);
                    secBoyDied.setVisibility(View.GONE);
                    lineBoyDied.setVisibility(View.GONE);
                    txtBoyDied.setText("");
                    secGirlDied.setVisibility(View.GONE);
                    lineGirlDied.setVisibility(View.GONE);
                    txtGirlDied.setText("");
                    secChDiedFsMon.setVisibility(View.GONE);
                    lineChDiedFsMon.setVisibility(View.GONE);
                    txtChDiedFsMon.setText("");
                    secNotLivBrth.setVisibility(View.GONE);
                    lineNotLivBrth.setVisibility(View.GONE);
                    rdogrpNotLivBrth.clearCheck();
                    seclbl02.setVisibility(View.GONE);
                    linelbl02.setVisibility(View.GONE);
                    secTotNotLB.setVisibility(View.GONE);
                    lineTotNotLB.setVisibility(View.GONE);
                    txtTotNotLB.setText("");
                    secTotNotLBDk.setVisibility(View.GONE);
                    lineTotNotLBDk.setVisibility(View.GONE);
                     chkTotNotLBDk.setChecked(false);
                    secStillBirth.setVisibility(View.GONE);
                    lineStillBirth.setVisibility(View.GONE);
                    txtStillBirth.setText("");
                    secStillBirthDk.setVisibility(View.GONE);
                    lineStillBirthDk.setVisibility(View.GONE);
                    chkStillBirthDk.setChecked(false);
                    secMiscAbor.setVisibility(View.GONE);
                    lineMiscAbor.setVisibility(View.GONE);
                    txtMiscAbor.setText("");
                    secMiscAborDk.setVisibility(View.GONE);
                    lineMiscAborDk.setVisibility(View.GONE);
                    chkMiscAborDk.setChecked(false);
                    seclbl06.setVisibility(View.GONE);
                    linelbl06.setVisibility(View.GONE);
                     secLastPregRes.setVisibility(View.GONE);
                     lineLastPregRes.setVisibility(View.GONE);
                     rdogrpLastPregRes.clearCheck();
                    secLastOutDate.setVisibility(View.GONE);
                    lineLastOutDate.setVisibility(View.GONE);
                    dtpLastOutDate.setText("");
                    secLastOutDateDK.setVisibility(View.GONE);
                    lineLastOutDateDK.setVisibility(View.GONE);
                    chkLastOutDateDK.setChecked(false);
                    secCesarean.setVisibility(View.GONE);
                    lineCesarean.setVisibility(View.GONE);
                    rdogrpCesarean.clearCheck();
                    secCesareanNo.setVisibility(View.GONE);
                    lineCesareanNo.setVisibility(View.GONE);
                    txtCesareanNo.setText("");
                    secTotPregOut.setVisibility(View.GONE);
                    lineTotPregOut.setVisibility(View.GONE);
                    txtTotPregOut.setText("");
                 }*/
                 else if(spnData.equalsIgnoreCase("1") || spnData.equalsIgnoreCase("2"))
                 {
                     secObsVStatusOth.setVisibility(View.GONE);
                     lineObsVStatusOth.setVisibility(View.GONE);
                     seclbl01l.setVisibility(View.VISIBLE);
                     linelbl01l.setVisibility(View.VISIBLE);


                    //if ever married
                    if(MS.equals("01")||
                            MS.equals("02")||
                            MS.equals("04")||
                            MS.equals("05")||
                            MS.equals("06")) {
                        seclbl01.setVisibility(View.VISIBLE);
                        linelbl01.setVisibility(View.VISIBLE);
                        secMarMon.setVisibility(View.VISIBLE);
                        lineMarMon.setVisibility(View.VISIBLE);
                        secMarYear.setVisibility(View.VISIBLE);
                        lineMarYear.setVisibility(View.VISIBLE);
                        secMarDK.setVisibility(View.VISIBLE);
                        lineMarDK.setVisibility(View.VISIBLE);
                    }else{
                        seclbl01.setVisibility(View.GONE);
                        linelbl01.setVisibility(View.GONE);
                        secMarMon.setVisibility(View.GONE);
                        lineMarMon.setVisibility(View.GONE);
                        spnMarMon.setSelection(0);
                        secMarYear.setVisibility(View.GONE);
                        lineMarYear.setVisibility(View.GONE);
                        spnMarYear.setSelection(0);
                        secMarDK.setVisibility(View.GONE);
                        lineMarDK.setVisibility(View.GONE);
                        chkMarDK.setChecked(false);
                    }

                    secEverPreg.setVisibility(View.VISIBLE);
                    lineEverPreg.setVisibility(View.VISIBLE);
                    /*secTotPreg.setVisibility(View.VISIBLE);
                    lineTotPreg.setVisibility(View.VISIBLE);
                    secGaveBirth.setVisibility(View.VISIBLE);
                    lineGaveBirth.setVisibility(View.VISIBLE);
                    secChildLivWWo.setVisibility(View.VISIBLE);
                    lineChildLivWWo.setVisibility(View.VISIBLE);
                    secSonLivWWo.setVisibility(View.VISIBLE);
                    lineSonLivWWo.setVisibility(View.VISIBLE);
                    secDaugLivWWo.setVisibility(View.VISIBLE);
                    lineDaugLivWWo.setVisibility(View.VISIBLE);
                    secChldLivOut.setVisibility(View.VISIBLE);
                    lineChldLivOut.setVisibility(View.VISIBLE);
                    seclbl04.setVisibility(View.VISIBLE);
                    linelbl04.setVisibility(View.VISIBLE);
                    secSonLivOut.setVisibility(View.VISIBLE);
                    lineSonLivOut.setVisibility(View.VISIBLE);
                    secDaugLivOut.setVisibility(View.VISIBLE);
                    lineDaugLivOut.setVisibility(View.VISIBLE);
                    secEarlyAlive.setVisibility(View.VISIBLE);
                    lineEarlyAlive.setVisibility(View.VISIBLE);
                    secEarlyAliveNo.setVisibility(View.VISIBLE);
                    lineEarlyAliveNo.setVisibility(View.VISIBLE);
                    secChldDie.setVisibility(View.VISIBLE);
                    lineChldDie.setVisibility(View.VISIBLE);
                    seclbl05.setVisibility(View.VISIBLE);
                    linelbl05.setVisibility(View.VISIBLE);
                    secBoyDied.setVisibility(View.VISIBLE);
                    lineBoyDied.setVisibility(View.VISIBLE);
                    secGirlDied.setVisibility(View.VISIBLE);
                    lineGirlDied.setVisibility(View.VISIBLE);
                    secChDiedFsMon.setVisibility(View.VISIBLE);
                    lineChDiedFsMon.setVisibility(View.VISIBLE);
                    secNotLivBrth.setVisibility(View.VISIBLE);
                    lineNotLivBrth.setVisibility(View.VISIBLE);
                    seclbl02.setVisibility(View.VISIBLE);
                    linelbl02.setVisibility(View.VISIBLE);
                    secTotNotLB.setVisibility(View.VISIBLE);
                    lineTotNotLB.setVisibility(View.VISIBLE);
//                    secTotNotLBDk.setVisibility(View.VISIBLE);
//                    lineTotNotLBDk.setVisibility(View.VISIBLE);
                    secStillBirth.setVisibility(View.VISIBLE);
                    lineStillBirth.setVisibility(View.VISIBLE);
//                    secStillBirthDk.setVisibility(View.VISIBLE);
//                    lineStillBirthDk.setVisibility(View.VISIBLE);
                    secMiscAbor.setVisibility(View.VISIBLE);
                    lineMiscAbor.setVisibility(View.VISIBLE);
//                    secMiscAborDk.setVisibility(View.VISIBLE);
//                    lineMiscAborDk.setVisibility(View.VISIBLE);
                    seclbl06.setVisibility(View.VISIBLE);
                    linelbl06.setVisibility(View.VISIBLE);
                    secLastPregRes.setVisibility(View.VISIBLE);
                    lineLastPregRes.setVisibility(View.VISIBLE);
                    secLastOutDate.setVisibility(View.VISIBLE);
                    lineLastOutDate.setVisibility(View.VISIBLE);
                    secLastOutDateDK.setVisibility(View.VISIBLE);
                    lineLastOutDateDK.setVisibility(View.VISIBLE);
                    secCesarean.setVisibility(View.VISIBLE);
                    lineCesarean.setVisibility(View.VISIBLE);
                    secTotPregOut.setVisibility(View.VISIBLE);
                    lineTotPregOut.setVisibility(View.VISIBLE);*/
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secObsVStatusOth=(LinearLayout)findViewById(R.id.secObsVStatusOth);
         lineObsVStatusOth=(View)findViewById(R.id.lineObsVStatusOth);
         VlblObsVStatusOth=(TextView) findViewById(R.id.VlblObsVStatusOth);
         txtObsVStatusOth=(AutoCompleteTextView) findViewById(R.id.txtObsVStatusOth);
         C.setupAutoComplete(TableName,txtObsVStatusOth,"ObsVStatusOth"); //setup autocomplete view by event


         seclbl01=(LinearLayout)findViewById(R.id.seclbl01);
         linelbl01l=(View)findViewById(R.id.linelbl01l);
         seclbl01l=(LinearLayout)findViewById(R.id.seclbl01l);
         linelbl01=(View)findViewById(R.id.linelbl01);
         secMarMon=(LinearLayout)findViewById(R.id.secMarMon);
         lineMarMon=(View)findViewById(R.id.lineMarMon);
         VlblMarMon=(TextView) findViewById(R.id.VlblMarMon);
         secMarMon=(LinearLayout)findViewById(R.id.secMarMon);
         lineMarMon=(View)findViewById(R.id.lineMarMon);
         VlblMarMon=(TextView) findViewById(R.id.VlblMarMon);
         spnMarMon=(Spinner) findViewById(R.id.spnMarMon);
         List<String> listMarMon = new ArrayList<String>();

         listMarMon.add("");
         listMarMon.add("01-Jan");
         listMarMon.add("02-Feb");
         listMarMon.add("03-Mar");
         listMarMon.add("04-Apr");
         listMarMon.add("05-May");
         listMarMon.add("06-Jun");
         listMarMon.add("07-Jul");
         listMarMon.add("08-Aug");
         listMarMon.add("09-Sep");
         listMarMon.add("10-Oct");
         listMarMon.add("11-Nov");
         listMarMon.add("12-Dec");
         listMarMon.add("98-Don't know");
         listMarMon.add("99-Never Married");
         spnMarMon.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listMarMon)));
         spnMarMon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 if(spnMarMon.getSelectedItemPosition()==0) return;

                 if(spnMarMon.getSelectedItem().toString().split("-")[0].equals("99")){
                     //spnMarYear.setSelection(Global.SpinnerItemPositionAnyLength(spnMarYear,"9999"));
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
                 // your code here
             }
         });

         secMarYear=(LinearLayout)findViewById(R.id.secMarYear);
         lineMarYear=(View)findViewById(R.id.lineMarYear);
         VlblMarYear=(TextView) findViewById(R.id.VlblMarYear);
         spnMarYear=(Spinner) findViewById(R.id.spnMarYear);
         List<String> listMarYear = new ArrayList<String>();

         listMarYear.add("");
         for(int i= Integer.parseInt(Global.CurrentYear());i>=Integer.parseInt(Global.CurrentYear())-40;i--){
             listMarYear.add(String.valueOf(i));
         }
         listMarYear.add("9998-Don't know");
         listMarYear.add("9999-Never Married");
         spnMarYear.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listMarYear)));

//         txtMarYear=(EditText) findViewById(R.id.txtMarYear);
         secMarDK=(LinearLayout)findViewById(R.id.secMarDK);
         lineMarDK=(View)findViewById(R.id.lineMarDK);
         VlblMarDK=(TextView) findViewById(R.id.VlblMarDK);
         chkMarDK=(CheckBox) findViewById(R.id.chkMarDK);

         chkMarDK.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 if (((CheckBox) v).isChecked()) {
                     spnMarMon.setEnabled(false);
                     spnMarYear.setEnabled(false);
                     spnMarMon.setSelection(0);
                     spnMarYear.setSelection(0);
                 }
                 else {
                     spnMarMon.setEnabled(true);
                     spnMarYear.setEnabled(true);
                 }
             }
         });

         secEverPreg=(LinearLayout)findViewById(R.id.secEverPreg);
         lineEverPreg=(View)findViewById(R.id.lineEverPreg);
         VlblEverPreg = (TextView) findViewById(R.id.VlblEverPreg);
         rdogrpEverPreg = (RadioGroup) findViewById(R.id.rdogrpEverPreg);
         rdoEverPreg1 = (RadioButton) findViewById(R.id.rdoEverPreg1);
         rdoEverPreg2 = (RadioButton) findViewById(R.id.rdoEverPreg2);
         rdogrpEverPreg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpEverPreg = new String[] {"0","1"};
             for (int i = 0; i < rdogrpEverPreg.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpEverPreg.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpEverPreg[i];
             }

             if(rbData.equalsIgnoreCase("1")) {
                 secTotPreg.setVisibility(View.VISIBLE);
                 lineTotPreg.setVisibility(View.VISIBLE);
                 secGaveBirth.setVisibility(View.VISIBLE);
                 lineGaveBirth.setVisibility(View.VISIBLE);
                 //secChildLivWWo.setVisibility(View.VISIBLE);
                 //lineChildLivWWo.setVisibility(View.VISIBLE);
                 //secSonLivWWo.setVisibility(View.VISIBLE);
                 //lineSonLivWWo.setVisibility(View.VISIBLE);
                 //secDaugLivWWo.setVisibility(View.VISIBLE);
                 //lineDaugLivWWo.setVisibility(View.VISIBLE);
//                 secChldLivOut.setVisibility(View.VISIBLE);
//                 lineChldLivOut.setVisibility(View.VISIBLE);
//                 seclbl04.setVisibility(View.VISIBLE);
//                 linelbl04.setVisibility(View.VISIBLE);
                 //secSonLivOut.setVisibility(View.VISIBLE);
                 //lineSonLivOut.setVisibility(View.VISIBLE);
                 //secDaugLivOut.setVisibility(View.VISIBLE);
                 //lineDaugLivOut.setVisibility(View.VISIBLE);
                 //secEarlyAlive.setVisibility(View.VISIBLE);
                 //lineEarlyAlive.setVisibility(View.VISIBLE);
                 //secEarlyAliveNo.setVisibility(View.VISIBLE);
                 //lineEarlyAliveNo.setVisibility(View.VISIBLE);
                 //secChldDie.setVisibility(View.VISIBLE);
                 //lineChldDie.setVisibility(View.VISIBLE);
                 //seclbl05.setVisibility(View.VISIBLE);
                 //linelbl05.setVisibility(View.VISIBLE);
                 //secBoyDied.setVisibility(View.VISIBLE);
                 //lineBoyDied.setVisibility(View.VISIBLE);
                 //secGirlDied.setVisibility(View.VISIBLE);
                 //lineGirlDied.setVisibility(View.VISIBLE);
                 //secChDiedFsMon.setVisibility(View.VISIBLE);
                 //lineChDiedFsMon.setVisibility(View.VISIBLE);
                 //secNotLivBrth.setVisibility(View.VISIBLE);
                 //lineNotLivBrth.setVisibility(View.VISIBLE);
                 //seclbl02.setVisibility(View.VISIBLE);
                 //linelbl02.setVisibility(View.VISIBLE);
                 //secTotNotLB.setVisibility(View.VISIBLE);
                 //lineTotNotLB.setVisibility(View.VISIBLE);
//                    secTotNotLBDk.setVisibility(View.VISIBLE);
//                    lineTotNotLBDk.setVisibility(View.VISIBLE);
                 //secStillBirth.setVisibility(View.VISIBLE);
                 //lineStillBirth.setVisibility(View.VISIBLE);
//                    secStillBirthDk.setVisibility(View.VISIBLE);
//                    lineStillBirthDk.setVisibility(View.VISIBLE);
                 //secMiscAbor.setVisibility(View.VISIBLE);
                 //lineMiscAbor.setVisibility(View.VISIBLE);
//                    secMiscAborDk.setVisibility(View.VISIBLE);
//                    lineMiscAborDk.setVisibility(View.VISIBLE);
//                 seclbl06.setVisibility(View.VISIBLE);
//                 linelbl06.setVisibility(View.VISIBLE);
//                 secLastPregRes.setVisibility(View.VISIBLE);
//                 lineLastPregRes.setVisibility(View.VISIBLE);
//                 secLastOutDate.setVisibility(View.VISIBLE);
//                 lineLastOutDate.setVisibility(View.VISIBLE);
//                 secLastOutDateDK.setVisibility(View.VISIBLE);
//                 lineLastOutDateDK.setVisibility(View.VISIBLE);
//                 secCesarean.setVisibility(View.VISIBLE);
//                 lineCesarean.setVisibility(View.VISIBLE);
//                 secTotPregOut.setVisibility(View.VISIBLE);
//                 lineTotPregOut.setVisibility(View.VISIBLE);
             }
             else{
                    secTotPreg.setVisibility(View.GONE);
                    lineTotPreg.setVisibility(View.GONE);
                    txtTotPreg.setText("");
                    secGaveBirth.setVisibility(View.GONE);
                    lineGaveBirth.setVisibility(View.GONE);
                    rdogrpGaveBirth.clearCheck();
                 secChildLivWWo.setVisibility(View.GONE);
                 lineChildLivWWo.setVisibility(View.GONE);
                 rdogrpChildLivWWo.clearCheck();
                    secSonLivWWo.setVisibility(View.GONE);
                    lineSonLivWWo.setVisibility(View.GONE);
                    txtSonLivWWo.setText("");
                    secDaugLivWWo.setVisibility(View.GONE);
                    lineDaugLivWWo.setVisibility(View.GONE);
                    txtDaugLivWWo.setText("");
                    secChldLivOut.setVisibility(View.GONE);
                    lineChldLivOut.setVisibility(View.GONE);
                    rdogrpChldLivOut.clearCheck();
                    seclbl04.setVisibility(View.GONE);
                    linelbl04.setVisibility(View.GONE);
                    secSonLivOut.setVisibility(View.GONE);
                    lineSonLivOut.setVisibility(View.GONE);
                    txtSonLivOut.setText("");
                    secDaugLivOut.setVisibility(View.GONE);
                    lineDaugLivOut.setVisibility(View.GONE);
                    txtDaugLivOut.setText("");
                    secEarlyAlive.setVisibility(View.GONE);
                    lineEarlyAlive.setVisibility(View.GONE);
                    rdogrpEarlyAlive.clearCheck();
                    secEarlyAliveNo.setVisibility(View.GONE);
                    lineEarlyAliveNo.setVisibility(View.GONE);
                    txtEarlyAliveNo.setText("");
                    secChldDie.setVisibility(View.GONE);
                    lineChldDie.setVisibility(View.GONE);
                    rdogrpChldDie.clearCheck();
                    seclbl05.setVisibility(View.GONE);
                    linelbl05.setVisibility(View.GONE);
                    secBoyDied.setVisibility(View.GONE);
                    lineBoyDied.setVisibility(View.GONE);
                    txtBoyDied.setText("");
                    secGirlDied.setVisibility(View.GONE);
                    lineGirlDied.setVisibility(View.GONE);
                    txtGirlDied.setText("");
                    secChDiedFsMon.setVisibility(View.GONE);
                    lineChDiedFsMon.setVisibility(View.GONE);
                    txtChDiedFsMon.setText("");
                    secNotLivBrth.setVisibility(View.GONE);
                    lineNotLivBrth.setVisibility(View.GONE);
                    rdogrpNotLivBrth.clearCheck();
                    seclbl02.setVisibility(View.GONE);
                    linelbl02.setVisibility(View.GONE);
                    secTotNotLB.setVisibility(View.GONE);
                    lineTotNotLB.setVisibility(View.GONE);
                    txtTotNotLB.setText("");
                    secTotNotLBDk.setVisibility(View.GONE);
                    lineTotNotLBDk.setVisibility(View.GONE);
                    chkTotNotLBDk.setChecked(false);
                    secStillBirth.setVisibility(View.GONE);
                    lineStillBirth.setVisibility(View.GONE);
                    txtStillBirth.setText("");
                    secStillBirthDk.setVisibility(View.GONE);
                    lineStillBirthDk.setVisibility(View.GONE);
                    chkStillBirthDk.setChecked(false);
                    secMiscAbor.setVisibility(View.GONE);
                    secMisc.setVisibility(View.GONE);
                    txtMisc.setText("");
                    lineMiscAbor.setVisibility(View.GONE);
                    txtMiscAbor.setText("");
                    secMiscAborDk.setVisibility(View.GONE);
                    lineMiscAborDk.setVisibility(View.GONE);
                    chkMiscAborDk.setChecked(false);
                    seclbl06.setVisibility(View.GONE);
                    linelbl06.setVisibility(View.GONE);
                 secLastPregRes.setVisibility(View.GONE);
                 lineLastPregRes.setVisibility(View.GONE);
                 rdogrpLastPregRes.clearCheck();
                    secLastOutDate.setVisibility(View.GONE);
                    lineLastOutDate.setVisibility(View.GONE);
                    dtpLastOutDate.setText("");
                    secLastOutDateDK.setVisibility(View.GONE);
                    lineLastOutDateDK.setVisibility(View.GONE);
                    chkLastOutDateDK.setChecked(false);
                    secCesarean.setVisibility(View.GONE);
                    lineCesarean.setVisibility(View.GONE);
                    rdogrpCesarean.clearCheck();
                    secCesareanNo.setVisibility(View.GONE);
                    lineCesareanNo.setVisibility(View.GONE);
                    txtCesareanNo.setText("");
                    secTotPregOut.setVisibility(View.GONE);
                    lineTotPregOut.setVisibility(View.GONE);
                    txtTotPregOut.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secTotPreg=(LinearLayout)findViewById(R.id.secTotPreg);
         lineTotPreg=(View)findViewById(R.id.lineTotPreg);
         VlblTotPreg=(TextView) findViewById(R.id.VlblTotPreg);
         txtTotPreg=(EditText) findViewById(R.id.txtTotPreg);
         secGaveBirth=(LinearLayout)findViewById(R.id.secGaveBirth);
         lineGaveBirth=(View)findViewById(R.id.lineGaveBirth);
         VlblGaveBirth = (TextView) findViewById(R.id.VlblGaveBirth);
         rdogrpGaveBirth = (RadioGroup) findViewById(R.id.rdogrpGaveBirth);
         rdoGaveBirth1 = (RadioButton) findViewById(R.id.rdoGaveBirth1);
         rdoGaveBirth2 = (RadioButton) findViewById(R.id.rdoGaveBirth2);
         rdogrpGaveBirth.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpGaveBirth = new String[] {"0","1"};
             for (int i = 0; i < rdogrpGaveBirth.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpGaveBirth.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpGaveBirth[i];
             }

             if(rbData.equalsIgnoreCase("1")) {

                 secChildLivWWo.setVisibility(View.VISIBLE);
                 lineChildLivWWo.setVisibility(View.VISIBLE);
                 /*secSonLivWWo.setVisibility(View.VISIBLE);
                 lineSonLivWWo.setVisibility(View.VISIBLE);
                 secDaugLivWWo.setVisibility(View.VISIBLE);
                 lineDaugLivWWo.setVisibility(View.VISIBLE);*/
                 secChldLivOut.setVisibility(View.VISIBLE);
                 lineChldLivOut.setVisibility(View.VISIBLE);
                 /*seclbl04.setVisibility(View.VISIBLE);
                 linelbl04.setVisibility(View.VISIBLE);
                 secSonLivOut.setVisibility(View.VISIBLE);
                 lineSonLivOut.setVisibility(View.VISIBLE);
                 secDaugLivOut.setVisibility(View.VISIBLE);
                 lineDaugLivOut.setVisibility(View.VISIBLE);*/
                 secEarlyAlive.setVisibility(View.VISIBLE);
                 lineEarlyAlive.setVisibility(View.VISIBLE);
                 /*secEarlyAliveNo.setVisibility(View.VISIBLE);
                 lineEarlyAliveNo.setVisibility(View.VISIBLE);*/
                 secChldDie.setVisibility(View.VISIBLE);
                 lineChldDie.setVisibility(View.VISIBLE);
                 /*seclbl05.setVisibility(View.VISIBLE);
                 linelbl05.setVisibility(View.VISIBLE);
                 secBoyDied.setVisibility(View.VISIBLE);
                 lineBoyDied.setVisibility(View.VISIBLE);
                 secGirlDied.setVisibility(View.VISIBLE);
                 lineGirlDied.setVisibility(View.VISIBLE);
                 secChDiedFsMon.setVisibility(View.VISIBLE);
                 lineChDiedFsMon.setVisibility(View.VISIBLE);*/
                 secNotLivBrth.setVisibility(View.VISIBLE);
                 lineNotLivBrth.setVisibility(View.VISIBLE);
                 /*seclbl02.setVisibility(View.VISIBLE);
                 linelbl02.setVisibility(View.VISIBLE);
                 secTotNotLB.setVisibility(View.VISIBLE);
                 lineTotNotLB.setVisibility(View.VISIBLE);
//                    secTotNotLBDk.setVisibility(View.VISIBLE);
//                    lineTotNotLBDk.setVisibility(View.VISIBLE);
                 secStillBirth.setVisibility(View.VISIBLE);
                 lineStillBirth.setVisibility(View.VISIBLE);
//                    secStillBirthDk.setVisibility(View.VISIBLE);
//                    lineStillBirthDk.setVisibility(View.VISIBLE);
                 secMiscAbor.setVisibility(View.VISIBLE);
                 lineMiscAbor.setVisibility(View.VISIBLE);*/
//                    secMiscAborDk.setVisibility(View.VISIBLE);
//                    lineMiscAborDk.setVisibility(View.VISIBLE);
                 seclbl06.setVisibility(View.VISIBLE);
                 linelbl06.setVisibility(View.VISIBLE);
                 secLastPregRes.setVisibility(View.VISIBLE);
                 lineLastPregRes.setVisibility(View.VISIBLE);
                 secLastOutDate.setVisibility(View.VISIBLE);
                 lineLastOutDate.setVisibility(View.VISIBLE);
                 secLastOutDateDK.setVisibility(View.VISIBLE);
                 lineLastOutDateDK.setVisibility(View.VISIBLE);
                 secCesarean.setVisibility(View.VISIBLE);
                 lineCesarean.setVisibility(View.VISIBLE);
                 secTotPregOut.setVisibility(View.VISIBLE);
                 lineTotPregOut.setVisibility(View.VISIBLE);
             }
             else{
                 secChildLivWWo.setVisibility(View.GONE);
                 lineChildLivWWo.setVisibility(View.GONE);
                 rdogrpChildLivWWo.clearCheck();
                 secSonLivWWo.setVisibility(View.GONE);
                 lineSonLivWWo.setVisibility(View.GONE);
                 txtSonLivWWo.setText("");
                 secDaugLivWWo.setVisibility(View.GONE);
                 lineDaugLivWWo.setVisibility(View.GONE);
                 txtDaugLivWWo.setText("");
                 secChldLivOut.setVisibility(View.GONE);
                 lineChldLivOut.setVisibility(View.GONE);
                 rdogrpChldLivOut.clearCheck();
                 seclbl04.setVisibility(View.GONE);
                 linelbl04.setVisibility(View.GONE);
                 secSonLivOut.setVisibility(View.GONE);
                 lineSonLivOut.setVisibility(View.GONE);
                 txtSonLivOut.setText("");
                 secDaugLivOut.setVisibility(View.GONE);
                 lineDaugLivOut.setVisibility(View.GONE);
                 txtDaugLivOut.setText("");
                 secEarlyAlive.setVisibility(View.GONE);
                 lineEarlyAlive.setVisibility(View.GONE);
                 rdogrpEarlyAlive.clearCheck();
                 secEarlyAliveNo.setVisibility(View.GONE);
                 lineEarlyAliveNo.setVisibility(View.GONE);
                 txtEarlyAliveNo.setText("");
                 secChldDie.setVisibility(View.GONE);
                 lineChldDie.setVisibility(View.GONE);
                 rdogrpChldDie.clearCheck();
                 seclbl05.setVisibility(View.GONE);
                 linelbl05.setVisibility(View.GONE);
                 secBoyDied.setVisibility(View.GONE);
                 lineBoyDied.setVisibility(View.GONE);
                 txtBoyDied.setText("");
                 secGirlDied.setVisibility(View.GONE);
                 lineGirlDied.setVisibility(View.GONE);
                 txtGirlDied.setText("");
                 secChDiedFsMon.setVisibility(View.GONE);
                 lineChDiedFsMon.setVisibility(View.GONE);
                 txtChDiedFsMon.setText("");
                 secNotLivBrth.setVisibility(View.GONE);
                 lineNotLivBrth.setVisibility(View.GONE);
                 rdogrpNotLivBrth.clearCheck();
                 seclbl02.setVisibility(View.GONE);
                 linelbl02.setVisibility(View.GONE);
                 secTotNotLB.setVisibility(View.GONE);
                 lineTotNotLB.setVisibility(View.GONE);
                 txtTotNotLB.setText("");
                 secTotNotLBDk.setVisibility(View.GONE);
                 lineTotNotLBDk.setVisibility(View.GONE);
                 chkTotNotLBDk.setChecked(false);
                 secStillBirth.setVisibility(View.GONE);
                 lineStillBirth.setVisibility(View.GONE);
                 txtStillBirth.setText("");
                 secStillBirthDk.setVisibility(View.GONE);
                 lineStillBirthDk.setVisibility(View.GONE);
                 chkStillBirthDk.setChecked(false);
                 secMiscAbor.setVisibility(View.GONE);
                 secMisc.setVisibility(View.GONE);
                 txtMisc.setText("");
                 lineMiscAbor.setVisibility(View.GONE);
                 txtMiscAbor.setText("");
                 secMiscAborDk.setVisibility(View.GONE);
                 lineMiscAborDk.setVisibility(View.GONE);
                 chkMiscAborDk.setChecked(false);
                 seclbl06.setVisibility(View.GONE);
                 linelbl06.setVisibility(View.GONE);
                 secLastPregRes.setVisibility(View.GONE);
                 lineLastPregRes.setVisibility(View.GONE);
                 rdogrpLastPregRes.clearCheck();
                 secLastOutDate.setVisibility(View.GONE);
                 lineLastOutDate.setVisibility(View.GONE);
                 dtpLastOutDate.setText("");
                 secLastOutDateDK.setVisibility(View.GONE);
                 lineLastOutDateDK.setVisibility(View.GONE);
                 chkLastOutDateDK.setChecked(false);
                 secCesarean.setVisibility(View.GONE);
                 lineCesarean.setVisibility(View.GONE);
                 rdogrpCesarean.clearCheck();
                 secCesareanNo.setVisibility(View.GONE);
                 lineCesareanNo.setVisibility(View.GONE);
                 txtCesareanNo.setText("");
                 secTotPregOut.setVisibility(View.GONE);
                 lineTotPregOut.setVisibility(View.GONE);
                 txtTotPregOut.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         });
         secChildLivWWo=(LinearLayout)findViewById(R.id.secChildLivWWo);
         lineChildLivWWo=(View)findViewById(R.id.lineChildLivWWo);
         VlblChildLivWWo = (TextView) findViewById(R.id.VlblChildLivWWo);
         rdogrpChildLivWWo = (RadioGroup) findViewById(R.id.rdogrpChildLivWWo);
         rdoChildLivWWo1 = (RadioButton) findViewById(R.id.rdoChildLivWWo1);
         rdoChildLivWWo2 = (RadioButton) findViewById(R.id.rdoChildLivWWo2);
         rdogrpChildLivWWo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpChildLivWWo = new String[] {"0","1"};
                 for (int i = 0; i < rdogrpChildLivWWo.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpChildLivWWo.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpChildLivWWo[i];
                 }

                 if(rbData.equalsIgnoreCase("0"))
                 {
                     secSonLivWWo.setVisibility(View.GONE);
                     lineSonLivWWo.setVisibility(View.GONE);
                     txtSonLivWWo.setText("");
                     secDaugLivWWo.setVisibility(View.GONE);
                     lineDaugLivWWo.setVisibility(View.GONE);
                     txtDaugLivWWo.setText("");
                 } else {
                     secSonLivWWo.setVisibility(View.VISIBLE);
                     lineSonLivWWo.setVisibility(View.VISIBLE);
                     secDaugLivWWo.setVisibility(View.VISIBLE);
                     lineDaugLivWWo.setVisibility(View.VISIBLE);
                 }
             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });
         secSonLivWWo=(LinearLayout)findViewById(R.id.secSonLivWWo);
         lineSonLivWWo=(View)findViewById(R.id.lineSonLivWWo);
         VlblSonLivWWo=(TextView) findViewById(R.id.VlblSonLivWWo);
         txtSonLivWWo=(EditText) findViewById(R.id.txtSonLivWWo);
         txtSonLivWWo.addTextChangedListener(new TextWatcher() {
             public void afterTextChanged(Editable s) {
                 txtTotPregOut.setText(GetTotalBirth());
             }

             public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
             public void onTextChanged(CharSequence s, int start, int before, int count) { }
         });
         secDaugLivWWo=(LinearLayout)findViewById(R.id.secDaugLivWWo);
         lineDaugLivWWo=(View)findViewById(R.id.lineDaugLivWWo);
         VlblDaugLivWWo=(TextView) findViewById(R.id.VlblDaugLivWWo);
         txtDaugLivWWo=(EditText) findViewById(R.id.txtDaugLivWWo);
         txtDaugLivWWo.addTextChangedListener(new TextWatcher() {
             public void afterTextChanged(Editable s) {
                 txtTotPregOut.setText(GetTotalBirth());
             }

             public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
             public void onTextChanged(CharSequence s, int start, int before, int count) { }
         });
         secChldLivOut=(LinearLayout)findViewById(R.id.secChldLivOut);
         lineChldLivOut=(View)findViewById(R.id.lineChldLivOut);
         VlblChldLivOut = (TextView) findViewById(R.id.VlblChldLivOut);
         rdogrpChldLivOut = (RadioGroup) findViewById(R.id.rdogrpChldLivOut);
         rdoChldLivOut1 = (RadioButton) findViewById(R.id.rdoChldLivOut1);
         rdoChldLivOut2 = (RadioButton) findViewById(R.id.rdoChldLivOut2);
         rdogrpChldLivOut.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpChldLivOut = new String[] {"0","1"};
             for (int i = 0; i < rdogrpChldLivOut.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpChldLivOut.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpChldLivOut[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    seclbl04.setVisibility(View.GONE);
                    linelbl04.setVisibility(View.GONE);
                    secSonLivOut.setVisibility(View.GONE);
                    lineSonLivOut.setVisibility(View.GONE);
                    txtSonLivOut.setText("");
                    secDaugLivOut.setVisibility(View.GONE);
                    lineDaugLivOut.setVisibility(View.GONE);
                    txtDaugLivOut.setText("");
             }
             else
             {
                    seclbl04.setVisibility(View.VISIBLE);
                    linelbl04.setVisibility(View.VISIBLE);
                    secSonLivOut.setVisibility(View.VISIBLE);
                    lineSonLivOut.setVisibility(View.VISIBLE);
                    secDaugLivOut.setVisibility(View.VISIBLE);
                    lineDaugLivOut.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclbl04=(LinearLayout)findViewById(R.id.seclbl04);
         linelbl04=(View)findViewById(R.id.linelbl04);
         secSonLivOut=(LinearLayout)findViewById(R.id.secSonLivOut);
         lineSonLivOut=(View)findViewById(R.id.lineSonLivOut);
         VlblSonLivOut=(TextView) findViewById(R.id.VlblSonLivOut);
         txtSonLivOut=(EditText) findViewById(R.id.txtSonLivOut);
         txtSonLivOut.addTextChangedListener(new TextWatcher() {
             public void afterTextChanged(Editable s) {
                 txtTotPregOut.setText(GetTotalBirth());
             }

             public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
             public void onTextChanged(CharSequence s, int start, int before, int count) { }
         });
         secDaugLivOut=(LinearLayout)findViewById(R.id.secDaugLivOut);
         lineDaugLivOut=(View)findViewById(R.id.lineDaugLivOut);
         VlblDaugLivOut=(TextView) findViewById(R.id.VlblDaugLivOut);
         txtDaugLivOut=(EditText) findViewById(R.id.txtDaugLivOut);
         txtDaugLivOut.addTextChangedListener(new TextWatcher() {
             public void afterTextChanged(Editable s) {
                 txtTotPregOut.setText(GetTotalBirth());
             }

             public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
             public void onTextChanged(CharSequence s, int start, int before, int count) { }
         });
         secEarlyAlive=(LinearLayout)findViewById(R.id.secEarlyAlive);
         lineEarlyAlive=(View)findViewById(R.id.lineEarlyAlive);
         VlblEarlyAlive = (TextView) findViewById(R.id.VlblEarlyAlive);
         rdogrpEarlyAlive = (RadioGroup) findViewById(R.id.rdogrpEarlyAlive);
         rdoEarlyAlive1 = (RadioButton) findViewById(R.id.rdoEarlyAlive1);
         rdoEarlyAlive2 = (RadioButton) findViewById(R.id.rdoEarlyAlive2);
         rdoEarlyAlive3 = (RadioButton) findViewById(R.id.rdoEarlyAlive3);
         secEarlyAliveNo=(LinearLayout)findViewById(R.id.secEarlyAliveNo);
         lineEarlyAliveNo=(View)findViewById(R.id.lineEarlyAliveNo);
         VlblEarlyAliveNo=(TextView) findViewById(R.id.VlblEarlyAliveNo);
         txtEarlyAliveNo=(EditText) findViewById(R.id.txtEarlyAliveNo);

         rdogrpEarlyAlive.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpEarlyAlive = new String[] {"0","1","8"};
                 for (int i = 0; i < rdogrpEarlyAlive.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpEarlyAlive.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpEarlyAlive[i];
                 }

                 if(rbData.equalsIgnoreCase("0") || rbData.equalsIgnoreCase("8"))
                 {
                     secEarlyAliveNo.setVisibility(View.GONE);
                     lineEarlyAliveNo.setVisibility(View.GONE);
                     txtEarlyAliveNo.setText("");
                 }
                 else
                 {
                     secEarlyAliveNo.setVisibility(View.VISIBLE);
                     lineEarlyAliveNo.setVisibility(View.VISIBLE);
                 }
             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });

         secChldDie=(LinearLayout)findViewById(R.id.secChldDie);
         lineChldDie=(View)findViewById(R.id.lineChldDie);
         VlblChldDie = (TextView) findViewById(R.id.VlblChldDie);
         rdogrpChldDie = (RadioGroup) findViewById(R.id.rdogrpChldDie);
         rdoChldDie1 = (RadioButton) findViewById(R.id.rdoChldDie1);
         rdoChldDie2 = (RadioButton) findViewById(R.id.rdoChldDie2);
         rdogrpChldDie.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpChldDie = new String[] {"0","1"};
             for (int i = 0; i < rdogrpChldDie.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpChldDie.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpChldDie[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    seclbl05.setVisibility(View.GONE);
                    linelbl05.setVisibility(View.GONE);
                    secBoyDied.setVisibility(View.GONE);
                    lineBoyDied.setVisibility(View.GONE);
                    txtBoyDied.setText("");
                    secGirlDied.setVisibility(View.GONE);
                    lineGirlDied.setVisibility(View.GONE);
                    txtGirlDied.setText("");
                    secChDiedFsMon.setVisibility(View.GONE);
                    lineChDiedFsMon.setVisibility(View.GONE);
                    txtChDiedFsMon.setText("");
             }
             else
             {
                    seclbl05.setVisibility(View.VISIBLE);
                    linelbl05.setVisibility(View.VISIBLE);
                    secBoyDied.setVisibility(View.VISIBLE);
                    lineBoyDied.setVisibility(View.VISIBLE);
                    secGirlDied.setVisibility(View.VISIBLE);
                    lineGirlDied.setVisibility(View.VISIBLE);
                    secChDiedFsMon.setVisibility(View.VISIBLE);
                    lineChDiedFsMon.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclbl05=(LinearLayout)findViewById(R.id.seclbl05);
         linelbl05=(View)findViewById(R.id.linelbl05);
         secBoyDied=(LinearLayout)findViewById(R.id.secBoyDied);
         lineBoyDied=(View)findViewById(R.id.lineBoyDied);
         VlblBoyDied=(TextView) findViewById(R.id.VlblBoyDied);
         txtBoyDied=(EditText) findViewById(R.id.txtBoyDied);
         txtBoyDied.addTextChangedListener(new TextWatcher() {
             public void afterTextChanged(Editable s) {
                 txtTotPregOut.setText(GetTotalBirth());
             }

             public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
             public void onTextChanged(CharSequence s, int start, int before, int count) { }
         });
         secGirlDied=(LinearLayout)findViewById(R.id.secGirlDied);
         lineGirlDied=(View)findViewById(R.id.lineGirlDied);
         VlblGirlDied=(TextView) findViewById(R.id.VlblGirlDied);
         txtGirlDied=(EditText) findViewById(R.id.txtGirlDied);
         txtGirlDied.addTextChangedListener(new TextWatcher() {
             public void afterTextChanged(Editable s) {
                 txtTotPregOut.setText(GetTotalBirth());
             }

             public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
             public void onTextChanged(CharSequence s, int start, int before, int count) { }
         });
         secChDiedFsMon=(LinearLayout)findViewById(R.id.secChDiedFsMon);
         lineChDiedFsMon=(View)findViewById(R.id.lineChDiedFsMon);
         VlblChDiedFsMon=(TextView) findViewById(R.id.VlblChDiedFsMon);
         txtChDiedFsMon=(EditText) findViewById(R.id.txtChDiedFsMon);
         secNotLivBrth=(LinearLayout)findViewById(R.id.secNotLivBrth);
         lineNotLivBrth=(View)findViewById(R.id.lineNotLivBrth);
         VlblNotLivBrth = (TextView) findViewById(R.id.VlblNotLivBrth);
         rdogrpNotLivBrth = (RadioGroup) findViewById(R.id.rdogrpNotLivBrth);
         rdoNotLivBrth1 = (RadioButton) findViewById(R.id.rdoNotLivBrth1);
         rdoNotLivBrth2 = (RadioButton) findViewById(R.id.rdoNotLivBrth2);
         rdogrpNotLivBrth.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpNotLivBrth = new String[] {"0","1"};
             for (int i = 0; i < rdogrpNotLivBrth.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpNotLivBrth.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpNotLivBrth[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    seclbl02.setVisibility(View.GONE);
                    linelbl02.setVisibility(View.GONE);
                    secTotNotLB.setVisibility(View.GONE);
                    lineTotNotLB.setVisibility(View.GONE);
                    txtTotNotLB.setText("");
                    secTotNotLBDk.setVisibility(View.GONE);
                    lineTotNotLBDk.setVisibility(View.GONE);
                    chkTotNotLBDk.setChecked(false);
                    secStillBirth.setVisibility(View.GONE);
                    lineStillBirth.setVisibility(View.GONE);
                    txtStillBirth.setText("");
                    secStillBirthDk.setVisibility(View.GONE);
                    lineStillBirthDk.setVisibility(View.GONE);
                    chkStillBirthDk.setChecked(false);
                    secMiscAbor.setVisibility(View.GONE);
                    secMisc.setVisibility(View.GONE);
                    txtMisc.setText("");
                    lineMiscAbor.setVisibility(View.GONE);
                    txtMiscAbor.setText("");
                    secMiscAborDk.setVisibility(View.GONE);
                    lineMiscAborDk.setVisibility(View.GONE);
                    chkMiscAborDk.setChecked(false);
             }
             else
             {
                    seclbl02.setVisibility(View.VISIBLE);
                    linelbl02.setVisibility(View.VISIBLE);
                    secTotNotLB.setVisibility(View.VISIBLE);
                    lineTotNotLB.setVisibility(View.VISIBLE);
//                    secTotNotLBDk.setVisibility(View.VISIBLE);
//                    lineTotNotLBDk.setVisibility(View.VISIBLE);
                    secStillBirth.setVisibility(View.VISIBLE);
                    lineStillBirth.setVisibility(View.VISIBLE);
//                    secStillBirthDk.setVisibility(View.VISIBLE);
//                    lineStillBirthDk.setVisibility(View.VISIBLE);
                    secMiscAbor.setVisibility(View.VISIBLE);
                    secMisc.setVisibility(View.VISIBLE);
                    lineMiscAbor.setVisibility(View.VISIBLE);
//                    secMiscAborDk.setVisibility(View.VISIBLE);
//                    lineMiscAborDk.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclbl02=(LinearLayout)findViewById(R.id.seclbl02);
         linelbl02=(View)findViewById(R.id.linelbl02);
         secTotNotLB=(LinearLayout)findViewById(R.id.secTotNotLB);
         lineTotNotLB=(View)findViewById(R.id.lineTotNotLB);
         VlblTotNotLB=(TextView) findViewById(R.id.VlblTotNotLB);
         txtTotNotLB=(EditText) findViewById(R.id.txtTotNotLB);
         txtTotNotLB.addTextChangedListener(new TextWatcher() {
             public void afterTextChanged(Editable s) {
                 txtTotPregOut.setText(GetTotalBirth());
             }

             public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
             public void onTextChanged(CharSequence s, int start, int before, int count) { }
         });
         secTotNotLBDk=(LinearLayout)findViewById(R.id.secTotNotLBDk);
         lineTotNotLBDk=(View)findViewById(R.id.lineTotNotLBDk);
         VlblTotNotLBDk=(TextView) findViewById(R.id.VlblTotNotLBDk);
         chkTotNotLBDk=(CheckBox) findViewById(R.id.chkTotNotLBDk);
         secStillBirth=(LinearLayout)findViewById(R.id.secStillBirth);
         lineStillBirth=(View)findViewById(R.id.lineStillBirth);
         VlblStillBirth=(TextView) findViewById(R.id.VlblStillBirth);
         txtStillBirth=(EditText) findViewById(R.id.txtStillBirth);
         secStillBirthDk=(LinearLayout)findViewById(R.id.secStillBirthDk);
         lineStillBirthDk=(View)findViewById(R.id.lineStillBirthDk);
         VlblStillBirthDk=(TextView) findViewById(R.id.VlblStillBirthDk);
         chkStillBirthDk=(CheckBox) findViewById(R.id.chkStillBirthDk);

         txtStillBirth.addTextChangedListener(new TextWatcher() {
             public void afterTextChanged(Editable s) {}
             public void beforeTextChanged(CharSequence s, int start,
                                           int count, int after) {
             }

             public void onTextChanged(CharSequence s, int start,
                                       int before, int count) {
                 if(txtStillBirth.getText().length()>0) chkStillBirthDk.setChecked(false);
             }
         });

         chkStillBirthDk.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 if (((CheckBox) v).isChecked()) {
                     txtStillBirth.setEnabled(false);
                     txtStillBirth.setText("");
                 }
                 else {
                     txtStillBirth.setEnabled(true);
                 }
             }
         });

         secMiscAbor=(LinearLayout)findViewById(R.id.secMiscAbor);
         lineMiscAbor=(View)findViewById(R.id.lineMiscAbor);
         VlblMiscAbor=(TextView) findViewById(R.id.VlblMiscAbor);
         txtMiscAbor=(EditText) findViewById(R.id.txtMiscAbor);
         secMiscAborDk=(LinearLayout)findViewById(R.id.secMiscAborDk);
         lineMiscAborDk=(View)findViewById(R.id.lineMiscAborDk);
         VlblMiscAborDk=(TextView) findViewById(R.id.VlblMiscAborDk);
         chkMiscAborDk=(CheckBox) findViewById(R.id.chkMiscAborDk);

         txtMiscAbor.addTextChangedListener(new TextWatcher() {
             public void afterTextChanged(Editable s) {}
             public void beforeTextChanged(CharSequence s, int start,
                                           int count, int after) {
             }

             public void onTextChanged(CharSequence s, int start,
                                       int before, int count) {
                 if(txtMiscAbor.getText().length()>0) chkMiscAborDk.setChecked(false);
             }
         });

         chkMiscAborDk.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 if (((CheckBox) v).isChecked()) {
                     txtMiscAbor.setEnabled(false);
                     txtMiscAbor.setText("");
                 }
                 else {
                     txtMiscAbor.setEnabled(true);
                 }
             }
         });

         seclbl06=(LinearLayout)findViewById(R.id.seclbl06);
         linelbl06=(View)findViewById(R.id.linelbl06);
         secLastPregRes=(LinearLayout)findViewById(R.id.secLastPregRes);
         lineLastPregRes=(View)findViewById(R.id.lineLastPregRes);
         VlblLastPregRes = (TextView) findViewById(R.id.VlblLastPregRes);
         rdogrpLastPregRes = (RadioGroup) findViewById(R.id.rdogrpLastPregRes);
         rdoLastPregRes1 = (RadioButton) findViewById(R.id.rdoLastPregRes1);
         rdoLastPregRes2 = (RadioButton) findViewById(R.id.rdoLastPregRes2);
         rdoLastPregRes3 = (RadioButton) findViewById(R.id.rdoLastPregRes3);
         rdoLastPregRes4 = (RadioButton) findViewById(R.id.rdoLastPregRes4);
         secLastOutDate=(LinearLayout)findViewById(R.id.secLastOutDate);
         lineLastOutDate=(View)findViewById(R.id.lineLastOutDate);
         VlblLastOutDate=(TextView) findViewById(R.id.VlblLastOutDate);
         dtpLastOutDate=(EditText) findViewById(R.id.dtpLastOutDate);
         dtpLastOutDate.addTextChangedListener(new TextWatcher() {
             public void afterTextChanged(Editable s) {
                 if(dtpLastOutDate.getText().toString().length()==10) chkLastOutDateDK.setChecked(false);
             }

             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }

             public void onTextChanged(CharSequence s, int start, int before, int count) {
             }
         });

         secLastOutDateDK=(LinearLayout)findViewById(R.id.secLastOutDateDK);
         lineLastOutDateDK=(View)findViewById(R.id.lineLastOutDateDK);
         VlblLastOutDateDK=(TextView) findViewById(R.id.VlblLastOutDateDK);
         chkLastOutDateDK=(CheckBox) findViewById(R.id.chkLastOutDateDK);

         chkLastOutDateDK.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 if (((CheckBox) v).isChecked()) {
                     dtpLastOutDate.setText("");
                 }
             }
         });

         secCesarean=(LinearLayout)findViewById(R.id.secCesarean);
         lineCesarean=(View)findViewById(R.id.lineCesarean);
         VlblCesarean = (TextView) findViewById(R.id.VlblCesarean);
         rdogrpCesarean = (RadioGroup) findViewById(R.id.rdogrpCesarean);
         rdoCesarean1 = (RadioButton) findViewById(R.id.rdoCesarean1);
         rdoCesarean2 = (RadioButton) findViewById(R.id.rdoCesarean2);
         rdoCesarean3 = (RadioButton) findViewById(R.id.rdoCesarean3);
         rdogrpCesarean.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpCesarean = new String[] {"0","1","8"};
             for (int i = 0; i < rdogrpCesarean.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpCesarean.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpCesarean[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    secCesareanNo.setVisibility(View.GONE);
                    lineCesareanNo.setVisibility(View.GONE);
                    txtCesareanNo.setText("");
             }
             else if(rbData.equalsIgnoreCase("8"))
             {
                    secCesareanNo.setVisibility(View.GONE);
                    lineCesareanNo.setVisibility(View.GONE);
                    txtCesareanNo.setText("");
             }
             else
             {
                    secCesareanNo.setVisibility(View.VISIBLE);
                    lineCesareanNo.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secCesareanNo=(LinearLayout)findViewById(R.id.secCesareanNo);
         lineCesareanNo=(View)findViewById(R.id.lineCesareanNo);
         VlblCesareanNo=(TextView) findViewById(R.id.VlblCesareanNo);
         txtCesareanNo=(EditText) findViewById(R.id.txtCesareanNo);
         secTotPregOut=(LinearLayout)findViewById(R.id.secTotPregOut);
         lineTotPregOut=(View)findViewById(R.id.lineTotPregOut);
         VlblTotPregOut=(TextView) findViewById(R.id.VlblTotPregOut);
         txtTotPregOut=(EditText) findViewById(R.id.txtTotPregOut);
         secObsNote=(LinearLayout)findViewById(R.id.secObsNote);
         lineObsNote=(View)findViewById(R.id.lineObsNote);
         VlblObsNote=(TextView) findViewById(R.id.VlblObsNote);
         txtObsNote=(EditText) findViewById(R.id.txtObsNote);

         secObsNote1=(LinearLayout)findViewById(R.id.secObsNote1);
         lineObsNote1=(View)findViewById(R.id.lineObsNote1);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_PregHis.this, e.getMessage());
         return;
     }
 }

 private void DataSave()
 {
   try
     {
         String ValidationMSG = "";
         String int_status = spnObsVStatus.getSelectedItemPosition()==0?"":spnObsVStatus.getSelectedItem().toString().split("-")[0].toString();
         if(int_status.equals("1")){
             ValidationMSG = ValidationCheck();
             if(ValidationMSG.length()>0)
             {
                 Connection.MessageBox(Surv_PregHis.this, ValidationMSG);
                 return;
             }
         }
 
         String SQL = "";
         RadioButton rb;

         tmpPregHis_DataModel objSave = new tmpPregHis_DataModel();
         objSave.setObsMatID(txtObsMatID.getText().toString());
         objSave.setMemID(txtMemID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setMSlNo(txtMSlNo.getText().toString());
         objSave.setObsVDate(dtpObsVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpObsVDate.getText().toString()) : dtpObsVDate.getText().toString());
         objSave.setObsVStatus(spnObsVStatus.getSelectedItemPosition() == 0 ? "" : spnObsVStatus.getSelectedItem().toString().split("-")[0]);
         objSave.setObsVStatusOth(txtObsVStatusOth.getText().toString());
//         objSave.setMarMon(txtMarMon.getText().toString());
//         objSave.setMarYear(txtMarYear.getText().toString());
         objSave.setMarMon(spnMarMon.getSelectedItemPosition() == 0 ? "" : spnMarMon.getSelectedItem().toString().split("-")[0]);
         objSave.setMarYear(spnMarYear.getSelectedItemPosition() == 0 ? "" : spnMarYear.getSelectedItem().toString().split("-")[0]);
         objSave.setMarDK((chkMarDK.isChecked() ? "1" : (secMarDK.isShown() ? "2" : "")));
         String[] d_rdogrpEverPreg = new String[] {"0","1"};
         objSave.setEverPreg("");
         for (int i = 0; i < rdogrpEverPreg.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpEverPreg.getChildAt(i);
             if (rb.isChecked()) objSave.setEverPreg(d_rdogrpEverPreg[i]);
         }

         objSave.setTotPreg(txtTotPreg.getText().toString());
         String[] d_rdogrpGaveBirth = new String[] {"0","1"};
         objSave.setGaveBirth("");
         for (int i = 0; i < rdogrpGaveBirth.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGaveBirth.getChildAt(i);
             if (rb.isChecked()) objSave.setGaveBirth(d_rdogrpGaveBirth[i]);
         }

//         objSave.setChildLivWWo(spnChildLivWWo.getSelectedItemPosition() == 0 ? "" : spnChildLivWWo.getSelectedItem().toString().split("-")[0]);
         String[] d_rdogrpChildLivWWo = new String[] {"0","1"};
         objSave.setChildLivWWo("");
         for (int i = 0; i < rdogrpChildLivWWo.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpChildLivWWo.getChildAt(i);
             if (rb.isChecked()) objSave.setChildLivWWo(d_rdogrpChildLivWWo[i]);
         }
         objSave.setSonLivWWo(txtSonLivWWo.getText().toString());
         objSave.setDaugLivWWo(txtDaugLivWWo.getText().toString());
         String[] d_rdogrpChldLivOut = new String[] {"0","1"};
         objSave.setChldLivOut("");
         for (int i = 0; i < rdogrpChldLivOut.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpChldLivOut.getChildAt(i);
             if (rb.isChecked()) objSave.setChldLivOut(d_rdogrpChldLivOut[i]);
         }

         objSave.setSonLivOut(txtSonLivOut.getText().toString());
         objSave.setDaugLivOut(txtDaugLivOut.getText().toString());
         String[] d_rdogrpEarlyAlive = new String[] {"0","1","8"};
         objSave.setEarlyAlive("");
         for (int i = 0; i < rdogrpEarlyAlive.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpEarlyAlive.getChildAt(i);
             if (rb.isChecked()) objSave.setEarlyAlive(d_rdogrpEarlyAlive[i]);
         }

         objSave.setEarlyAliveNo(txtEarlyAliveNo.getText().toString());
         String[] d_rdogrpChldDie = new String[] {"0","1"};
         objSave.setChldDie("");
         for (int i = 0; i < rdogrpChldDie.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpChldDie.getChildAt(i);
             if (rb.isChecked()) objSave.setChldDie(d_rdogrpChldDie[i]);
         }

         objSave.setBoyDied(txtBoyDied.getText().toString());
         objSave.setGirlDied(txtGirlDied.getText().toString());
         objSave.setChDiedFsMon(txtChDiedFsMon.getText().toString());
         String[] d_rdogrpNotLivBrth = new String[] {"0","1"};
         objSave.setNotLivBrth("");
         for (int i = 0; i < rdogrpNotLivBrth.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpNotLivBrth.getChildAt(i);
             if (rb.isChecked()) objSave.setNotLivBrth(d_rdogrpNotLivBrth[i]);
         }

         objSave.setTotNotLB(txtTotNotLB.getText().toString());
         objSave.setStillBirth(txtStillBirth.getText().toString());
         objSave.setStillBirthDk((chkStillBirthDk.isChecked() ? "1" : (secStillBirthDk.isShown() ? "2" : "")));
         objSave.setMiscAbor(txtMiscAbor.getText().toString());
         objSave.setMiscAborDk((chkMiscAborDk.isChecked() ? "1" : (secMiscAborDk.isShown() ? "2" : "")));

         objSave.setMisc(txtMisc.getText().toString());
         objSave.setMiscDk((chkMiscDk.isChecked() ? "1" : (secMisc.isShown() ? "2" : "")));


//         objSave.setLastPregRes(txtLastPregRes.getText().toString());
         String[] d_rdogrpLastPregRes = new String[] {"1","2","3","4"};
         objSave.setLastPregRes("");
         for (int i = 0; i < rdogrpLastPregRes.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLastPregRes.getChildAt(i);
             if (rb.isChecked()) objSave.setLastPregRes(d_rdogrpLastPregRes[i]);
         }

         objSave.setLastOutDate(dtpLastOutDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpLastOutDate.getText().toString()) : dtpLastOutDate.getText().toString());
         objSave.setLastOutDateDK((chkLastOutDateDK.isChecked() ? "1" : (secLastOutDateDK.isShown() ? "2" : "")));
         String[] d_rdogrpCesarean = new String[] {"0","1","8"};
         objSave.setCesarean("");
         for (int i = 0; i < rdogrpCesarean.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCesarean.getChildAt(i);
             if (rb.isChecked()) objSave.setCesarean(d_rdogrpCesarean[i]);
         }

         objSave.setCesareanNo(txtCesareanNo.getText().toString());
         objSave.setTotPregOut(txtTotPregOut.getText().toString());
         objSave.setObsNote(txtObsNote.getText().toString());
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

             Toast.makeText(Surv_PregHis.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
             finish();
         }
         else{
             Connection.MessageBox(Surv_PregHis.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_PregHis.this, e.getMessage());
         return;
     }
 }

 private String ValidationCheck()
 {
     int validMarrigeAge = ProjectSetting.ELIGIBILITY_AGE_MS_YEAR;
   String ValidationMsg = "";
   String DV = "";
   try
     {
         ResetSectionColor();
         if(txtObsMatID.getText().toString().length()==0 & secObsMatID.isShown())
           {
             ValidationMsg += "\nRequired field: Maternal Info Internal ID.";
             secObsMatID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMemID.getText().toString().length()==0 & secMemID.isShown())
           {
             ValidationMsg += "\nRequired field: Member Internal ID.";
             secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
           {
             ValidationMsg += "\nRequired field: Household ID.";
             secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMSlNo.getText().toString().length()==0 & secMSlNo.isShown())
           {
             ValidationMsg += "\nRequired field: Member serial no.";
             secMSlNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpObsVDate.getText().toString());
         if(DV.length()!=0 & secObsVDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Visit Date.";
             secObsVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnObsVStatus.getSelectedItemPosition()==0  & secObsVStatus.isShown())
           {
             ValidationMsg += "\n1.2 Required field: Interview Status.";
             secObsVStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtObsVStatusOth.getText().toString().length()==0 & secObsVStatusOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other specify.";
             secObsVStatusOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
//         if(txtMarMon.getText().toString().length()==0 & secMarMon.isShown())
//           {
//             ValidationMsg += "\nRequired field: Month.";
//             secMarMon.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
//         if(txtMarYear.getText().toString().length()==0 & secMarYear.isShown())
//           {
//             ValidationMsg += "\nRequired field: Year.";
//             secMarYear.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }

         if(!chkMarDK.isChecked() & (spnMarMon.getSelectedItemPosition()==0 & secMarMon.isShown()))
         {
             ValidationMsg += "\nRequired field: Month.";
             secMarMon.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!chkMarDK.isChecked() & (spnMarYear.getSelectedItemPosition()==0 & secMarYear.isShown()))
         {
             ValidationMsg += "\nRequired field: Year.";
             secMarYear.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

         if (!chkMarDK.isChecked()
                 && spnMarMon.getSelectedItemPosition()!=0
                 && spnMarYear.getSelectedItemPosition()!=0
                 && secMarMon.isShown()
                 && secMarYear.isShown()
         ) {
             try {
                 tmpMember_DataModel d = new tmpMember_DataModel("");
                 String SQL = "Select * from "+ tmpMember_DataModel.TableName +"  Where HHID='"+ HHID +"' and MemID='"+MEM_ID+"' and isdelete='2'";
                 List<tmpMember_DataModel> data = d.SelectAll(this, SQL);
                 if(data != null && !data.isEmpty()) {
                     if(!spnMarYear.getSelectedItem().toString().split("-")[0].equals("9999") &&
                             !spnMarYear.getSelectedItem().toString().split("-")[0].equals("9998")) {
                         tmpMember_DataModel memberDataModel = data.get(0);
                         String marMonth = spnMarMon.getSelectedItemPosition() == 0 ? "" : (spnMarMon.getSelectedItem().toString().split("-")[0].equals("99")||spnMarMon.getSelectedItem().toString().split("-")[0].equals("98") ? "06" : spnMarMon.getSelectedItem().toString().split("-")[0]);
                         String marYear = spnMarYear.getSelectedItemPosition() == 0 ? "" : spnMarYear.getSelectedItem().toString().split("-")[0];
                         String marDate = "15" + "/" + marMonth + "/" + marYear;
                         int marAge = Global.DateDifferenceYears(VISIT_DATE, marDate); // Global.getAgeYears(Global.DateConvertYMD(marDate));
                         int age = Global.getAgeYears(memberDataModel.getBDate());
                         int difference = age - marAge;
                         if (difference < validMarrigeAge) {
                             ValidationMsg += "\n1.3 Marital age must be equal or more than "+ validMarrigeAge +" years after date of birth";
                             secMarMon.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                             secMarMon.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                         } else if (Global.DateDifferenceDays(VISIT_DATE, "01" + "/" + marMonth + "/" + marYear) < 0) {
                             ValidationMsg += "\n1.3 Marital date must be equal or less than today's date.";
                             secMarMon.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                             secMarMon.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                         }
                     }
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
//         if(spnMarMon.getSelectedItemPosition()==0  & secMarMon.isShown())
//         {
//             ValidationMsg += "\nRequired field: Month.";
//             secMarMon.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//         }
//         if(spnMarYear.getSelectedItemPosition()==0  & secMarYear.isShown())
//         {
//             ValidationMsg += "\nRequired field: Year.";
//             secMarYear.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//         }

         if(!rdoEverPreg1.isChecked() & !rdoEverPreg2.isChecked() & secEverPreg.isShown())
           {
             ValidationMsg += "\n1.5 Required field: Have you ever been pregnant?.";
             secEverPreg.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtTotPreg.getText().toString().length()==0 & secTotPreg.isShown())
           {
             ValidationMsg += "\n1.6 Required field: How many times have you been pregnant, including livebirth, stillbirth and miscarriages/abortions?.";
             secTotPreg.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGaveBirth1.isChecked() & !rdoGaveBirth2.isChecked() & secGaveBirth.isShown())
           {
             ValidationMsg += "\n1.7 Required field: While pregnant have you ever given birth?.";
             secGaveBirth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoChildLivWWo1.isChecked() & !rdoChildLivWWo2.isChecked() & secChildLivWWo.isShown())
         {
             ValidationMsg += "\n1.8 Required field: Do you have any sons or daughters to whom you have given birth and who are now living with you?.";
             secChildLivWWo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtSonLivWWo.getText().toString().length()==0 & secSonLivWWo.isShown())
           {
             ValidationMsg += "\n1.9 Required field: How many sons live with you? -IF NONE, RECORD ‘00’.";
             secSonLivWWo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDaugLivWWo.getText().toString().length()==0 & secDaugLivWWo.isShown())
           {
             ValidationMsg += "\nRequired field: How many daughters live with you? -IF NONE, RECORD ‘00’.";
             secDaugLivWWo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoChldLivOut1.isChecked() & !rdoChldLivOut2.isChecked() & secChldLivOut.isShown())
           {
             ValidationMsg += "\n1.10 Required field: Do you have any sons or daughters to whom you have given birth who are still alive but do not live with you?.";
             secChldLivOut.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSonLivOut.getText().toString().length()==0 & secSonLivOut.isShown())
           {
             ValidationMsg += "\nRequired field: Number of sons living elsewhere (IF NONE, RECORD ‘00’).";
             secSonLivOut.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDaugLivOut.getText().toString().length()==0 & secDaugLivOut.isShown())
           {
             ValidationMsg += "\nRequired field: Number of daughters living elsewhere (IF NONE, RECORD ‘00’).";
             secDaugLivOut.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoEarlyAlive1.isChecked() & !rdoEarlyAlive2.isChecked() & !rdoEarlyAlive3.isChecked() & secEarlyAlive.isShown())
           {
             ValidationMsg += "\n1.12 Required field: Have you ever had a baby born alive that was born early (before 37 weeks)?.";
             secEarlyAlive.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtEarlyAliveNo.getText().toString().length()==0 & secEarlyAliveNo.isShown())
           {
             ValidationMsg += "\nRequired field: Born early (before 37 weeks)?  Number.";
             secEarlyAliveNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoChldDie1.isChecked() & !rdoChldDie2.isChecked() & secChldDie.isShown())
           {
             ValidationMsg += "\n1.13 Required field: Have you ever given birth to a boy or a girl who was born alive but later died? IF NO, PROBE: Any baby who cried or showed any sign of life but only survived a few hours or days or years?.";
             secChldDie.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtBoyDied.getText().toString().length()==0 & secBoyDied.isShown())
           {
             ValidationMsg += "\nRequired field: In all, how many boys have died? IF NONE, RECORD ‘00’.";
             secBoyDied.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtGirlDied.getText().toString().length()==0 & secGirlDied.isShown())
           {
             ValidationMsg += "\nRequired field: In all, How many girls have died? (IF NONE, RECORD ‘00’).";
             secGirlDied.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         Integer Q16=0,Q19=0,Q19a=0,Q111=0,Q111a=0,Q114=0,Q114a=0;
         Q114 = Integer.valueOf(txtBoyDied.getText().toString().length() == 0 ? "0" : txtBoyDied.getText().toString());
         Q114a = Integer.valueOf(txtGirlDied.getText().toString().length() == 0 ? "0" : txtGirlDied.getText().toString());
         if(rdoChldDie2.isChecked() & Q114+Q114a==0)
         {
             if (!txtBoyDied.getText().toString().trim().equalsIgnoreCase("00")
                     || !txtGirlDied.getText().toString().trim().equalsIgnoreCase("00")) {
                 ValidationMsg += "\nRequired field: How many boys and girls have died ";
             }

             if (!txtBoyDied.getText().toString().trim().equalsIgnoreCase("00")) {
                 secBoyDied.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
             if (!txtGirlDied.getText().toString().trim().equalsIgnoreCase("00")) {
                 secGirlDied.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
         }
         if(txtChDiedFsMon.getText().toString().length()==0 & secChDiedFsMon.isShown())
           {
             ValidationMsg += "\n1.15 Required field: How many children died in the first month of life?.";
             secChDiedFsMon.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtChDiedFsMon.getText().toString().length()!=0 && !txtChDiedFsMon.getText().toString().equalsIgnoreCase("00") && secChDiedFsMon.isShown())
           {
               int totalDied = Q114+Q114a;
               int diedBeforeOneMonth = Integer.parseInt(txtChDiedFsMon.getText().toString().length() == 0 ? "0" : txtChDiedFsMon.getText().toString());;
               if (diedBeforeOneMonth > totalDied) {
                   ValidationMsg += "\n1.15 The total number of children who die in the first month of life cannot exceed the total number of deaths of boys and girls.";
                   secChDiedFsMon.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
               }
           }
         if(!rdoNotLivBrth1.isChecked() & !rdoNotLivBrth2.isChecked() & secNotLivBrth.isShown())
           {
             ValidationMsg += "\n1.16 Required field: Some pregnancies end before full term as miscarriage or an abortion, while others may result in a stillbirth. Have you had any pregnancies that did not result in live births?.";
             secNotLivBrth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtTotNotLB.getText().toString().length()==0 & secTotNotLB.isShown())
           {
             ValidationMsg += "\nRequired field: Total number.";
             secTotNotLB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
//         if(txtTotNotLBDk.getText().toString().length()==0 & secTotNotLBDk.isShown())
//           {
//             ValidationMsg += "\nRequired field: Total number Don’t know.";
//             secTotNotLBDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
         if (!chkStillBirthDk.isChecked() & (txtStillBirth.getText().toString().length()==0 & secStillBirth.isShown()))
           {
             ValidationMsg += "\nRequired field: Number of stillbirths.";
             secStillBirth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if (!chkMiscAborDk.isChecked() & (txtMiscAbor.getText().toString().length()==0 & secMiscAbor.isShown()))
           {
             ValidationMsg += "\nRequired field: Number of abortions.";
             secMiscAbor.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if (!chkMiscDk.isChecked() & (txtMisc.getText().toString().length()==0 & secMisc.isShown()))
         {
             ValidationMsg += "\nRequired field: Number of miscarriage.";
             secMisc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

         if(!rdoLastPregRes1.isChecked() & !rdoLastPregRes2.isChecked() & !rdoLastPregRes3.isChecked() & !rdoLastPregRes4.isChecked() & secLastPregRes.isShown())
         {
             ValidationMsg += "\nRequired field: Types of outcome.";
             secLastPregRes.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         DV = Global.DateValidate(dtpLastOutDate.getText().toString());
         if(DV.length()!=0 & secLastOutDate.isShown() & !chkLastOutDateDK.isChecked() )
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Delivery Outcome date.";
             secLastOutDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCesarean1.isChecked() & !rdoCesarean2.isChecked() & !rdoCesarean3.isChecked() & secCesarean.isShown())
           {
             ValidationMsg += "\n1.19 Required field: Have you ever had a cesarean operation for any of your delivery?.";
             secCesarean.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCesareanNo.getText().toString().length()==0 & secCesareanNo.isShown())
           {
             ValidationMsg += "\n1.20 Required field: How many have you ever had a cesarean operation?.";
             secCesareanNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtTotPregOut.getText().toString().length()==0 & secTotPregOut.isShown())
           {
             ValidationMsg += "\n1.21 Required field: Sum answers 1.9, 1.11, 1.14 and 1.17 and enter total   -Number of total pregnancy outcome.";
             secTotPregOut.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
//         if(txtObsNote.getText().toString().length()==0 & secObsNote.isShown())
//           {
//             ValidationMsg += "\nRequired field: Note.";
//             secObsNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }


         Q16 = Integer.valueOf(txtTotPreg.getText().toString().length() == 0 ? "0" : txtTotPreg.getText().toString());
         if(rdoEverPreg2.isChecked() & Q16==0)
         {
             ValidationMsg += "\nRequired field: How many times have you been pregnant.";
             secTotPreg.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         Q19 = Integer.valueOf(txtSonLivWWo.getText().toString().length() == 0 ? "0" : txtSonLivWWo.getText().toString());
         Q19a = Integer.valueOf(txtDaugLivWWo.getText().toString().length() == 0 ? "0" : txtDaugLivWWo.getText().toString());
         if(rdoChildLivWWo2.isChecked() & Q19+Q19a==0)
         {
             ValidationMsg += "\nRequired field: How many sons and daughters live with you?";
             secSonLivWWo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             secDaugLivWWo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

         Q111 = Integer.valueOf(txtSonLivOut.getText().toString().length() == 0 ? "0" : txtSonLivOut.getText().toString());
         Q111a = Integer.valueOf(txtDaugLivOut.getText().toString().length() == 0 ? "0" : txtDaugLivOut.getText().toString());
         if(rdoChldLivOut2.isChecked() & Q111+Q111a==0)
         {
             ValidationMsg += "\nRequired field: Number of sons and daughters living";
             secSonLivOut.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             secDaugLivOut.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

        /* Q114 = Integer.valueOf(txtBoyDied.getText().toString().length() == 0 ? "0" : txtBoyDied.getText().toString());
         Q114a = Integer.valueOf(txtGirlDied.getText().toString().length() == 0 ? "0" : txtGirlDied.getText().toString());
         if(rdoChldDie2.isChecked() & Q114+Q114a==0)
         {
             if (!txtBoyDied.getText().toString().trim().equalsIgnoreCase("00")
                     || !txtGirlDied.getText().toString().trim().equalsIgnoreCase("00")) {
                 ValidationMsg += "\nRequired field: How many boys and girls have died ";
             }

             if (!txtBoyDied.getText().toString().trim().equalsIgnoreCase("00")) {
                 secBoyDied.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
             if (!txtGirlDied.getText().toString().trim().equalsIgnoreCase("00")) {
                 secGirlDied.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
         }*/

         /*Q114 = Integer.valueOf(txtBoyDied.getText().toString().length() == 0 ? "0" : txtBoyDied.getText().toString());
         Q114a = Integer.valueOf(txtGirlDied.getText().toString().length() == 0 ? "0" : txtGirlDied.getText().toString());
         if(rdoChldDie2.isChecked() & Q114+Q114a==0)
         {
             ValidationMsg += "\nRequired field: How many boys and girls have died ";
             secBoyDied.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             secGirlDied.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }*/

         Integer Q117TotNotLB=0,Q117SB=0,Q117AB=0,Q117MS=0,Q117DK=0;

         Q117TotNotLB = Integer.valueOf(txtTotNotLB.getText().toString().length() == 0 ? "0" : txtTotNotLB.getText().toString());
         Q117SB = Integer.valueOf(txtStillBirth.getText().toString().length() == 0 ? "0" : txtStillBirth.getText().toString());
         Q117AB = Integer.valueOf(txtMiscAbor.getText().toString().length() == 0 ? "0" : txtMiscAbor.getText().toString());
         Q117MS = Integer.valueOf(txtMisc.getText().toString().length() == 0 ? "0" : txtMisc.getText().toString());

         if (!chkMiscAborDk.isChecked() & !chkStillBirthDk.isChecked() & !chkMiscDk.isChecked()) {
             if (Q117SB + Q117AB + Q117MS != Q117TotNotLB & txtTotNotLB.isShown() ) {
                 ValidationMsg += "\nQ1.17 - The number of stillbirths, miscarriages, abortions is not correct with the total number ))";
                 secMiscAbor.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
         }
         if (!chkStillBirthDk.isChecked() & Q117SB > Q117TotNotLB)
         {
             ValidationMsg += "\nQ1.17- Total number Should not be less than the number of still births))";
             secStillBirth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if (!chkMiscAborDk.isChecked() & Q117AB > Q117TotNotLB)
         {
             ValidationMsg += "\nQ1.17- Total number Should not be less than the number of abortions))";
             secMiscAbor.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

         if (!chkMiscDk.isChecked() & Q117MS > Q117TotNotLB)
         {
             ValidationMsg += "\nQ1.17- Total number Should not be less than the number of miscarriages))";
             secMisc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

         if(Q19+Q19a+Q111+Q111a+Q114+Q114a+Q117TotNotLB !=Integer.valueOf(txtTotPregOut.getText().toString().length()==0?"0":txtTotPregOut.getText().toString()))
         {
             ValidationMsg += "\nQ1.21 -The total number of pregnancy results is incorrect (check answers to questions 1.9, 1.11, 1.14, and 1.17.)";
             secTotPregOut.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secObsMatID.setBackgroundColor(Color.WHITE);
             secMemID.setBackgroundColor(Color.WHITE);
             secHHID.setBackgroundColor(Color.WHITE);
             secMSlNo.setBackgroundColor(Color.WHITE);
             secObsVDate.setBackgroundColor(Color.WHITE);
             secObsVStatus.setBackgroundColor(Color.WHITE);
             secObsVStatusOth.setBackgroundColor(Color.WHITE);
             secMarMon.setBackgroundColor(Color.WHITE);
             secMarYear.setBackgroundColor(Color.WHITE);
             secEverPreg.setBackgroundColor(Color.WHITE);
             secTotPreg.setBackgroundColor(Color.WHITE);
             secGaveBirth.setBackgroundColor(Color.WHITE);
             secChildLivWWo.setBackgroundColor(Color.WHITE);
             secSonLivWWo.setBackgroundColor(Color.WHITE);
             secDaugLivWWo.setBackgroundColor(Color.WHITE);
             secChldLivOut.setBackgroundColor(Color.WHITE);
             secSonLivOut.setBackgroundColor(Color.WHITE);
             secDaugLivOut.setBackgroundColor(Color.WHITE);
             secEarlyAlive.setBackgroundColor(Color.WHITE);
             secEarlyAliveNo.setBackgroundColor(Color.WHITE);
             secChldDie.setBackgroundColor(Color.WHITE);
             secBoyDied.setBackgroundColor(Color.WHITE);
             secGirlDied.setBackgroundColor(Color.WHITE);
             secChDiedFsMon.setBackgroundColor(Color.WHITE);
             secNotLivBrth.setBackgroundColor(Color.WHITE);
             secTotNotLB.setBackgroundColor(Color.WHITE);
             secTotNotLBDk.setBackgroundColor(Color.WHITE);
             secStillBirth.setBackgroundColor(Color.WHITE);
             secMiscAbor.setBackgroundColor(Color.WHITE);
             secMisc.setBackgroundColor(Color.WHITE);
             secLastPregRes.setBackgroundColor(Color.WHITE);
             secLastOutDate.setBackgroundColor(Color.WHITE);
             secCesarean.setBackgroundColor(Color.WHITE);
             secCesareanNo.setBackgroundColor(Color.WHITE);
             secTotPregOut.setBackgroundColor(Color.WHITE);
             secObsNote.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String MEM_ID)
     {
       try
        {     
           RadioButton rb;
           PregHis_DataModel d = new PregHis_DataModel();
           String SQL = "Select * from "+ TableName +"  Where MemID='"+ MEM_ID +"'";
           List<PregHis_DataModel> data = d.SelectAll(this, SQL);
           for(PregHis_DataModel item : data){
             txtObsMatID.setText(item.getObsMatID());
             txtMemID.setText(item.getMemID());
             txtHHID.setText(item.getHHID());
             txtMSlNo.setText(item.getMSlNo());
             dtpObsVDate.setText(item.getObsVDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getObsVDate()));
             spnObsVStatus.setSelection(Global.SpinnerItemPositionAnyLength(spnObsVStatus, String.valueOf(item.getObsVStatus())));
             txtObsVStatusOth.setText(item.getObsVStatusOth());
//             txtMarMon.setText(item.getMarMon());
//             txtMarYear.setText(item.getMarYear());
               spnMarMon.setSelection(Global.SpinnerItemPositionAnyLength(spnMarMon, String.valueOf(item.getMarMon())));
               spnMarYear.setSelection(Global.SpinnerItemPositionAnyLength(spnMarYear, String.valueOf(item.getMarYear())));

             if(String.valueOf(item.getMarDK()).equals("1"))
             {
                chkMarDK.setChecked(true);
             }
             else if(String.valueOf(item.getMarDK()).equals("2"))
             {
                chkMarDK.setChecked(false);
             }
             String[] d_rdogrpEverPreg = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpEverPreg.length; i++)
             {
                 if (String.valueOf(item.getEverPreg()).equals(String.valueOf(d_rdogrpEverPreg[i])))
                 {
                     rb = (RadioButton)rdogrpEverPreg.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtTotPreg.setText(item.getTotPreg());
             String[] d_rdogrpGaveBirth = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpGaveBirth.length; i++)
             {
                 if (String.valueOf(item.getGaveBirth()).equals(String.valueOf(d_rdogrpGaveBirth[i])))
                 {
                     rb = (RadioButton)rdogrpGaveBirth.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
//             spnChildLivWWo.setSelection(Global.SpinnerItemPositionAnyLength(spnChildLivWWo, String.valueOf(item.getChildLivWWo())));
               String[] d_rdogrpChildLivWWo = new String[] {"0","1"};
               for (int i = 0; i < d_rdogrpChildLivWWo.length; i++)
               {
                   if (String.valueOf(item.getChildLivWWo()).equals(String.valueOf(d_rdogrpChildLivWWo[i])))
                   {
                       rb = (RadioButton)rdogrpChildLivWWo.getChildAt(i);
                       rb.setChecked(true);
                   }
               }

             txtSonLivWWo.setText(item.getSonLivWWo());
             txtDaugLivWWo.setText(item.getDaugLivWWo());
             String[] d_rdogrpChldLivOut = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpChldLivOut.length; i++)
             {
                 if (String.valueOf(item.getChldLivOut()).equals(String.valueOf(d_rdogrpChldLivOut[i])))
                 {
                     rb = (RadioButton)rdogrpChldLivOut.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtSonLivOut.setText(item.getSonLivOut());
             txtDaugLivOut.setText(item.getDaugLivOut());
             String[] d_rdogrpEarlyAlive = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpEarlyAlive.length; i++)
             {
                 if (String.valueOf(item.getEarlyAlive()).equals(String.valueOf(d_rdogrpEarlyAlive[i])))
                 {
                     rb = (RadioButton)rdogrpEarlyAlive.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtEarlyAliveNo.setText(item.getEarlyAliveNo());
             String[] d_rdogrpChldDie = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpChldDie.length; i++)
             {
                 if (String.valueOf(item.getChldDie()).equals(String.valueOf(d_rdogrpChldDie[i])))
                 {
                     rb = (RadioButton)rdogrpChldDie.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtBoyDied.setText(item.getBoyDied());
             txtGirlDied.setText(item.getGirlDied());
             txtChDiedFsMon.setText(item.getChDiedFsMon());
             String[] d_rdogrpNotLivBrth = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpNotLivBrth.length; i++)
             {
                 if (String.valueOf(item.getNotLivBrth()).equals(String.valueOf(d_rdogrpNotLivBrth[i])))
                 {
                     rb = (RadioButton)rdogrpNotLivBrth.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtTotNotLB.setText(item.getTotNotLB());

             txtStillBirth.setText(item.getStillBirth());
             if(String.valueOf(item.getStillBirthDk()).equals("1"))
             {
                chkStillBirthDk.setChecked(true);
             }
             else if(String.valueOf(item.getStillBirthDk()).equals("2"))
             {
                chkStillBirthDk.setChecked(false);
             }
             txtMiscAbor.setText(item.getMiscAbor());
             if(String.valueOf(item.getMiscAborDk()).equals("1"))
             {
                chkMiscAborDk.setChecked(true);
             }
             else if(String.valueOf(item.getMiscAborDk()).equals("2"))
             {
                chkMiscAborDk.setChecked(false);
             }

               txtMisc.setText(item.getMisc());
               if(String.valueOf(item.getMiscDk()).equals("1"))
               {
                   chkMiscDk.setChecked(true);
               }
               else if(String.valueOf(item.getMiscDk()).equals("2"))
               {
                   chkMiscDk.setChecked(false);
               }


//             txtLastPregRes.setText(item.getLastPregRes());
               String[] d_rdogrpLastPregRes = new String[] {"1","2","3","4"};
               for (int i = 0; i < d_rdogrpLastPregRes.length; i++)
               {
                   if (String.valueOf(item.getLastPregRes()).equals(String.valueOf(d_rdogrpLastPregRes[i])))
                   {
                       rb = (RadioButton)rdogrpLastPregRes.getChildAt(i);
                       rb.setChecked(true);
                   }
               }

             dtpLastOutDate.setText(item.getLastOutDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getLastOutDate()));
             if(String.valueOf(item.getLastOutDateDK()).equals("1"))
             {
                chkLastOutDateDK.setChecked(true);
             }
             else if(String.valueOf(item.getLastOutDateDK()).equals("2"))
             {
                chkLastOutDateDK.setChecked(false);
             }
             String[] d_rdogrpCesarean = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpCesarean.length; i++)
             {
                 if (String.valueOf(item.getCesarean()).equals(String.valueOf(d_rdogrpCesarean[i])))
                 {
                     rb = (RadioButton)rdogrpCesarean.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtCesareanNo.setText(item.getCesareanNo());
             txtTotPregOut.setText(item.getTotPregOut());
             txtObsNote.setText(item.getObsNote());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_PregHis.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpObsVDate);
      if (VariableID.equals("btnObsVDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpObsVDate);
      }
      else if (VariableID.equals("btnLastOutDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpLastOutDate);
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