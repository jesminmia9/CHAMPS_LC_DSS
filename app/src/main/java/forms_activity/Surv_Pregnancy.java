
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
 import android.widget.AutoCompleteTextView;
 import android.widget.Button;
 import android.widget.CheckBox;
 import android.widget.CompoundButton;
 import android.widget.DatePicker;
 import android.widget.EditText;
 import android.widget.ImageButton;
 import android.widget.LinearLayout;
 import android.widget.RadioButton;
 import android.widget.RadioGroup;
 import android.widget.SimpleAdapter;
 import android.widget.TextView;
 import android.widget.TimePicker;
 import android.graphics.Color;
 import android.view.WindowManager;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.R;

 import Utility.*;
 import Common.*;
 import forms_datamodel.tmpPregnancy_DataModel;

 public class Surv_Pregnancy extends AppCompatActivity {
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
    LinearLayout secPregOccurID;
    View linePregOccurID;
    TextView VlblPregOccurID;
    EditText txtPregOccurID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
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
    LinearLayout secLMPDtObt;
    View lineLMPDtObt;
    TextView VlblLMPDtObt;
    RadioGroup rdogrpLMPDtObt;
    RadioButton rdoLMPDtObt1;
    RadioButton rdoLMPDtObt2;
    LinearLayout seclbl02;
    View linelbl02;
    LinearLayout secPrConLMP;
    View linePrConLMP;
    TextView VlblPrConLMP;
    RadioGroup rdogrpPrConLMP;
    RadioButton rdoPrConLMP1;
    RadioButton rdoPrConLMP2;
    LinearLayout secPrConBelly;
    View linePrConBelly;
    TextView VlblPrConBelly;
    RadioGroup rdogrpPrConBelly;
    RadioButton rdoPrConBelly1;
    RadioButton rdoPrConBelly2;
    LinearLayout secPrConFeMove;
    View linePrConFeMove;
    TextView VlblPrConFeMove;
    RadioGroup rdogrpPrConFeMove;
    RadioButton rdoPrConFeMove1;
    RadioButton rdoPrConFeMove2;
    LinearLayout secPrConTestHome;
    View linePrConTestHome;
    TextView VlblPrConTestHome;
    RadioGroup rdogrpPrConTestHome;
    RadioButton rdoPrConTestHome1;
    RadioButton rdoPrConTestHome2;
    LinearLayout secPrConTestDoc;
    View linePrConTestDoc;
    TextView VlblPrConTestDoc;
    RadioGroup rdogrpPrConTestDoc;
    RadioButton rdoPrConTestDoc1;
    RadioButton rdoPrConTestDoc2;
    LinearLayout secPrConHeartSnd;
    View linePrConHeartSnd;
    TextView VlblPrConHeartSnd;
    RadioGroup rdogrpPrConHeartSnd;
    RadioButton rdoPrConHeartSnd1;
    RadioButton rdoPrConHeartSnd2;
    LinearLayout secPrConUtrasound;
    View linePrConUtrasound;
    TextView VlblPrConUtrasound;
    RadioGroup rdogrpPrConUtrasound;
    RadioButton rdoPrConUtrasound1;
    RadioButton rdoPrConUtrasound2;
    LinearLayout secPrConTestFaci;
    View linePrConTestFaci;
    TextView VlblPrConTestFaci;
    RadioGroup rdogrpPrConTestFaci;
    RadioButton rdoPrConTestFaci1;
    RadioButton rdoPrConTestFaci2;
    LinearLayout secPrConUrineTest;
    View linePrConUrineTest;
    TextView VlblPrConUrineTest;
    RadioGroup rdogrpPrConUrineTest;
    RadioButton rdoPrConUrineTest1;
    RadioButton rdoPrConUrineTest2;
    LinearLayout secPrConOth;
    View linePrConOth;
    TextView VlblPrConOth;
    RadioGroup rdogrpPrConOth;
    RadioButton rdoPrConOth1;
    RadioButton rdoPrConOth2;
    LinearLayout secPrConOthSpecify;
    View linePrConOthSpecify;
    TextView VlblPrConOthSpecify;
    AutoCompleteTextView txtPrConOthSpecify;
     LinearLayout secPregPrenatalCons;
     View linePregPrenatalCons;
     TextView VlblPregPrenatalCons;
     RadioGroup rdogrpPregPrenatalCons;
     RadioButton rdoPregPrenatalCons1;
     RadioButton rdoPregPrenatalCons2;
     LinearLayout seclbl03;
     View linelbl03;
     LinearLayout secchkPregPrenatalConsA;
     View linechkPregPrenatalConsA;
     TextView VlblchkPregPrenatalConsA;
     CheckBox chkchkPregPrenatalConsA;
     LinearLayout secchkPregPrenatalConsB;
     View linechkPregPrenatalConsB;
     TextView VlblchkPregPrenatalConsB;
     CheckBox chkchkPregPrenatalConsB;
     LinearLayout secchkPregPrenatalConsC;
     View linechkPregPrenatalConsC;
     TextView VlblchkPregPrenatalConsC;
     CheckBox chkchkPregPrenatalConsC;
     LinearLayout secchkPregPrenatalConsD;
     View linechkPregPrenatalConsD;
     TextView VlblchkPregPrenatalConsD;
     CheckBox chkchkPregPrenatalConsD;
     LinearLayout secchkPregPrenatalConsE;
     View linechkPregPrenatalConsE;
     TextView VlblchkPregPrenatalConsE;
     CheckBox chkchkPregPrenatalConsE;
     LinearLayout secchkPregPrenatalConsF;
     View linechkPregPrenatalConsF;
     TextView VlblchkPregPrenatalConsF;
     CheckBox chkchkPregPrenatalConsF;
     LinearLayout secchkPregPrenatalConsG;
     View linechkPregPrenatalConsG;
     TextView VlblchkPregPrenatalConsG;
     CheckBox chkchkPregPrenatalConsG;
     LinearLayout secchkPregPrenatalConsH;
     View linechkPregPrenatalConsH;
     TextView VlblchkPregPrenatalConsH;
     CheckBox chkchkPregPrenatalConsH;
     LinearLayout secchkPregPrenatalConsI;
     View linechkPregPrenatalConsI;
     TextView VlblchkPregPrenatalConsI;
     CheckBox chkchkPregPrenatalConsI;
     LinearLayout seclbl04;
     View linelbl04;
     LinearLayout secchkPregPrenatalConsPlaceA;
     View linechkPregPrenatalConsPlaceA;
     TextView VlblchkPregPrenatalConsPlaceA;
     CheckBox chkchkPregPrenatalConsPlaceA;
     LinearLayout secchkPregPrenatalConsPlaceB;
     View linechkPregPrenatalConsPlaceB;
     TextView VlblchkPregPrenatalConsPlaceB;
     CheckBox chkchkPregPrenatalConsPlaceB;
     LinearLayout secchkPregPrenatalConsPlaceC;
     View linechkPregPrenatalConsPlaceC;
     TextView VlblchkPregPrenatalConsPlaceC;
     CheckBox chkchkPregPrenatalConsPlaceC;
     LinearLayout secchkPregPrenatalConsPlaceD;
     View linechkPregPrenatalConsPlaceD;
     TextView VlblchkPregPrenatalConsPlaceD;
     CheckBox chkchkPregPrenatalConsPlaceD;
     LinearLayout secchkPregPrenatalConsPlaceE;
     View linechkPregPrenatalConsPlaceE;
     TextView VlblchkPregPrenatalConsPlaceE;
     CheckBox chkchkPregPrenatalConsPlaceE;
     LinearLayout secchkPregPrenatalConsPlaceF;
     View linechkPregPrenatalConsPlaceF;
     TextView VlblchkPregPrenatalConsPlaceF;
     CheckBox chkchkPregPrenatalConsPlaceF;
     LinearLayout secchkPregPrenatalConsPlaceH;
     View linechkPregPrenatalConsPlaceH;
     TextView VlblchkPregPrenatalConsPlaceH;
     CheckBox chkchkPregPrenatalConsPlaceH;
     LinearLayout secchkPregPrenatalConsPlaceG;
     View linechkPregPrenatalConsPlaceG;
     TextView VlblchkPregPrenatalConsPlaceG;
     CheckBox chkchkPregPrenatalConsPlaceG;
     LinearLayout secchkPregPrenatalConsPlaceI;
     View linechkPregPrenatalConsPlaceI;
     TextView VlblchkPregPrenatalConsPlaceI;
     CheckBox chkchkPregPrenatalConsPlaceI;
     LinearLayout secchkPregPrenatalConsPlaceJ;
     View linechkPregPrenatalConsPlaceJ;
     TextView VlblchkPregPrenatalConsPlaceJ;
     CheckBox chkchkPregPrenatalConsPlaceJ;
     LinearLayout secchkPregPrenatalConsPlaceX;
     View linechkPregPrenatalConsPlaceX;
     TextView VlblchkPregPrenatalConsPlaceX;
     CheckBox chkchkPregPrenatalConsPlaceX;
     LinearLayout secchkPregPrenatalConsPlaceY;
     View linechkPregPrenatalConsPlaceY;
     TextView VlblchkPregPrenatalConsPlaceY;
     CheckBox chkchkPregPrenatalConsPlaceY;
     LinearLayout secchkPregPrenatalConsPlaceZ;
     View linechkPregPrenatalConsPlaceZ;
     TextView VlblchkPregPrenatalConsPlaceZ;
     CheckBox chkchkPregPrenatalConsPlaceZ;
     LinearLayout secPregPrenatalConsPlaceOth;
     View linePregPrenatalConsPlaceOth;
     TextView VlblPregPrenatalConsPlaceOth;
     AutoCompleteTextView txtPregPrenatalConsPlaceOth;
     LinearLayout secPregRank;
     View linePregRank;
     TextView VlblPregRank;
     EditText txtPregRank;
     LinearLayout secPregEnrolled;
     View linePregEnrolled;
     TextView VlblPregEnrolled;
     RadioGroup rdogrpPregEnrolled;
     RadioButton rdoPregEnrolled1;
     RadioButton rdoPregEnrolled2;
     RadioButton rdoPregEnrolled3;
     RadioButton rdoPregEnrolled4;
     LinearLayout secPregPSIDKnown;
     View linePregPSIDKnown;
     TextView VlblPregPSIDKnown;
     RadioGroup rdogrpPregPSIDKnown;
     RadioButton rdoPregPSIDKnown1;
     RadioButton rdoPregPSIDKnown2;
     RadioButton rdoPregPSIDKnown3;
     RadioButton rdoPregPSIDKnown4;
     LinearLayout secPregPSID;
     View linePregPSID;
     TextView VlblPregPSID;
     EditText txtPregPSID;
     LinearLayout secPregWishEnroll;
     View linePregWishEnroll;
     TextView VlblPregWishEnroll;
     RadioGroup rdogrpPregWishEnroll;
     RadioButton rdoPregWishEnroll1;
     RadioButton rdoPregWishEnroll2;
     RadioButton rdoPregWishEnroll3;
     RadioButton rdoPregWishEnroll4;
    LinearLayout secPregVdate;
    View linePregVdate;
    TextView VlblPregVdate;
    EditText dtpPregVdate;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secPregNote;
    View linePregNote;
    TextView VlblPregNote;
    EditText txtPregNote;

     LinearLayout secPregNote1;
     View linePregNote1;

     int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
      String PREGOCCURID = "";
      String MEM_ID = "";
      String HHID = "";
      String EV_TYPE = "";
      String ROUND = "";
      String VISIT_DATE = "";
      String prevPregDate = "";
      String isEdit = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.surv_pregnancy);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         PREGOCCURID = IDbundle.getString("PregOccurID");
         MEM_ID = IDbundle.getString("MemID");
         HHID = IDbundle.getString("HHID");
         EV_TYPE = IDbundle.getString("evtype");
         ROUND = IDbundle.getString("round");
         VISIT_DATE = IDbundle.getString("vdate"); //dd/mm/yyyy
         isEdit = IDbundle.getString("isEdit");

         prevPregDate = C.ReturnSingleValue("SELECT COALESCE(MAX(LMPDt), '') AS Last_LMPDt FROM tmpPregnancy WHERE MemID = '" + MEM_ID + "'");

         TableName = "tmpPregnancy";
         MODULEID = 26;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Pregnancy.this);
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
        Connection.LocalizeLanguage(Surv_Pregnancy.this, MODULEID, LANGUAGEID);

         if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
             //Show Mali Questions
             secPregPrenatalCons.setVisibility(View.VISIBLE);
             seclbl03.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsA.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsB.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsC.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsD.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsE.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsF.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsG.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsH.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsI.setVisibility(View.VISIBLE);
             seclbl04.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceA.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceB.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceC.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceD.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceE.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceF.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceG.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceH.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceI.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceJ.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceX.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceY.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceZ.setVisibility(View.VISIBLE);
             secPregPrenatalConsPlaceOth.setVisibility(View.GONE);
             secPregRank.setVisibility(View.VISIBLE);
             linePregPrenatalCons.setVisibility(View.VISIBLE);
             linelbl03.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsA.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsB.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsC.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsD.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsE.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsF.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsG.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsH.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsI.setVisibility(View.VISIBLE);
             linelbl04.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsPlaceA.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsPlaceB.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsPlaceC.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsPlaceD.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsPlaceE.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsPlaceF.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsPlaceG.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceH.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceJ.setVisibility(View.VISIBLE);
             secchkPregPrenatalConsPlaceI.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsPlaceX.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsPlaceY.setVisibility(View.VISIBLE);
             linechkPregPrenatalConsPlaceZ.setVisibility(View.VISIBLE);
             linePregPrenatalConsPlaceOth.setVisibility(View.GONE);
             linePregRank.setVisibility(View.VISIBLE);

             //Hide Sierra Leone Questions
             secPregEnrolled.setVisibility(View.GONE);
             linePregEnrolled.setVisibility(View.GONE);
             secPregPSIDKnown.setVisibility(View.GONE);
             linePregPSIDKnown.setVisibility(View.GONE);
             secPregPSID.setVisibility(View.GONE);
             linePregPSID.setVisibility(View.GONE);
             secPregWishEnroll.setVisibility(View.GONE);
             linePregWishEnroll.setVisibility(View.GONE);
         }
         else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1)){
             //Hide Mali Questions
             secPregPrenatalCons.setVisibility(View.GONE);
             seclbl03.setVisibility(View.GONE);
             secchkPregPrenatalConsA.setVisibility(View.GONE);
             secchkPregPrenatalConsB.setVisibility(View.GONE);
             secchkPregPrenatalConsC.setVisibility(View.GONE);
             secchkPregPrenatalConsD.setVisibility(View.GONE);
             secchkPregPrenatalConsE.setVisibility(View.GONE);
             secchkPregPrenatalConsF.setVisibility(View.GONE);
             secchkPregPrenatalConsG.setVisibility(View.GONE);
             secchkPregPrenatalConsH.setVisibility(View.GONE);
             secchkPregPrenatalConsI.setVisibility(View.GONE);
             seclbl04.setVisibility(View.GONE);
             secchkPregPrenatalConsPlaceA.setVisibility(View.GONE);
             secchkPregPrenatalConsPlaceB.setVisibility(View.GONE);
             secchkPregPrenatalConsPlaceC.setVisibility(View.GONE);
             secchkPregPrenatalConsPlaceD.setVisibility(View.GONE);
             secchkPregPrenatalConsPlaceE.setVisibility(View.GONE);
             secchkPregPrenatalConsPlaceF.setVisibility(View.GONE);
             secchkPregPrenatalConsPlaceG.setVisibility(View.GONE);
             secchkPregPrenatalConsPlaceH.setVisibility(View.GONE);
             secchkPregPrenatalConsPlaceI.setVisibility(View.GONE);
             secchkPregPrenatalConsPlaceJ.setVisibility(View.GONE);
             secchkPregPrenatalConsPlaceX.setVisibility(View.GONE);
             secchkPregPrenatalConsPlaceY.setVisibility(View.GONE);
             secchkPregPrenatalConsPlaceZ.setVisibility(View.GONE);
             secPregPrenatalConsPlaceOth.setVisibility(View.GONE);
             secPregRank.setVisibility(View.GONE);

             linePregPrenatalCons.setVisibility(View.GONE);
             linelbl03.setVisibility(View.GONE);
             linechkPregPrenatalConsA.setVisibility(View.GONE);
             linechkPregPrenatalConsB.setVisibility(View.GONE);
             linechkPregPrenatalConsC.setVisibility(View.GONE);
             linechkPregPrenatalConsD.setVisibility(View.GONE);
             linechkPregPrenatalConsE.setVisibility(View.GONE);
             linechkPregPrenatalConsF.setVisibility(View.GONE);
             linechkPregPrenatalConsG.setVisibility(View.GONE);
             linechkPregPrenatalConsH.setVisibility(View.GONE);
             linechkPregPrenatalConsI.setVisibility(View.GONE);
             linelbl04.setVisibility(View.GONE);
             linechkPregPrenatalConsPlaceA.setVisibility(View.GONE);
             linechkPregPrenatalConsPlaceB.setVisibility(View.GONE);
             linechkPregPrenatalConsPlaceC.setVisibility(View.GONE);
             linechkPregPrenatalConsPlaceD.setVisibility(View.GONE);
             linechkPregPrenatalConsPlaceE.setVisibility(View.GONE);
             linechkPregPrenatalConsPlaceF.setVisibility(View.GONE);
             linechkPregPrenatalConsPlaceG.setVisibility(View.GONE);
             linechkPregPrenatalConsPlaceH.setVisibility(View.GONE);
             linechkPregPrenatalConsPlaceI.setVisibility(View.GONE);
             linechkPregPrenatalConsPlaceJ.setVisibility(View.GONE);
             linechkPregPrenatalConsPlaceX.setVisibility(View.GONE);
             linechkPregPrenatalConsPlaceY.setVisibility(View.GONE);
             linechkPregPrenatalConsPlaceZ.setVisibility(View.GONE);
             linePregPrenatalConsPlaceOth.setVisibility(View.GONE);
             linePregRank.setVisibility(View.GONE);

            // hide Sierra Leone Questions
             secPregEnrolled.setVisibility(View.GONE);
             linePregEnrolled.setVisibility(View.GONE);
             secPregPSIDKnown.setVisibility(View.GONE);
             linePregPSIDKnown.setVisibility(View.GONE);
             secPregPSID.setVisibility(View.GONE);
             linePregPSID.setVisibility(View.GONE);
             secPregWishEnroll.setVisibility(View.GONE);
             linePregWishEnroll.setVisibility(View.GONE);
         }

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
         dtpPregVdate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnPregVdate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables
         secPrConOthSpecify.setVisibility(View.GONE);
         linePrConOthSpecify.setVisibility(View.GONE);

         secPregPrenatalConsPlaceOth.setVisibility(View.GONE);
         linePregPrenatalConsPlaceOth.setVisibility(View.GONE);


        DataSearch(PREGOCCURID);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Pregnancy.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         secPregOccurID=(LinearLayout)findViewById(R.id.secPregOccurID);
         linePregOccurID=(View)findViewById(R.id.linePregOccurID);
         VlblPregOccurID=(TextView) findViewById(R.id.VlblPregOccurID);
         txtPregOccurID=(EditText) findViewById(R.id.txtPregOccurID);

         if(PREGOCCURID.length()==0) txtPregOccurID.setText(Global.Get_UUID(DEVICEID));
         else txtPregOccurID.setText(PREGOCCURID);
         txtPregOccurID.setEnabled(false);

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
         txtMemID.setEnabled(false);
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
         secLMPDtObt=(LinearLayout)findViewById(R.id.secLMPDtObt);
         lineLMPDtObt=(View)findViewById(R.id.lineLMPDtObt);
         VlblLMPDtObt = (TextView) findViewById(R.id.VlblLMPDtObt);
         rdogrpLMPDtObt = (RadioGroup) findViewById(R.id.rdogrpLMPDtObt);
         rdoLMPDtObt1 = (RadioButton) findViewById(R.id.rdoLMPDtObt1);
         rdoLMPDtObt2 = (RadioButton) findViewById(R.id.rdoLMPDtObt2);
         seclbl02=(LinearLayout)findViewById(R.id.seclbl02);
         linelbl02=(View)findViewById(R.id.linelbl02);
         secPrConLMP=(LinearLayout)findViewById(R.id.secPrConLMP);
         linePrConLMP=(View)findViewById(R.id.linePrConLMP);
         VlblPrConLMP = (TextView) findViewById(R.id.VlblPrConLMP);
         rdogrpPrConLMP = (RadioGroup) findViewById(R.id.rdogrpPrConLMP);
         rdoPrConLMP1 = (RadioButton) findViewById(R.id.rdoPrConLMP1);
         rdoPrConLMP2 = (RadioButton) findViewById(R.id.rdoPrConLMP2);
         secPrConBelly=(LinearLayout)findViewById(R.id.secPrConBelly);
         linePrConBelly=(View)findViewById(R.id.linePrConBelly);
         VlblPrConBelly = (TextView) findViewById(R.id.VlblPrConBelly);
         rdogrpPrConBelly = (RadioGroup) findViewById(R.id.rdogrpPrConBelly);
         rdoPrConBelly1 = (RadioButton) findViewById(R.id.rdoPrConBelly1);
         rdoPrConBelly2 = (RadioButton) findViewById(R.id.rdoPrConBelly2);
         secPrConFeMove=(LinearLayout)findViewById(R.id.secPrConFeMove);
         linePrConFeMove=(View)findViewById(R.id.linePrConFeMove);
         VlblPrConFeMove = (TextView) findViewById(R.id.VlblPrConFeMove);
         rdogrpPrConFeMove = (RadioGroup) findViewById(R.id.rdogrpPrConFeMove);
         rdoPrConFeMove1 = (RadioButton) findViewById(R.id.rdoPrConFeMove1);
         rdoPrConFeMove2 = (RadioButton) findViewById(R.id.rdoPrConFeMove2);
         secPrConTestHome=(LinearLayout)findViewById(R.id.secPrConTestHome);
         linePrConTestHome=(View)findViewById(R.id.linePrConTestHome);
         VlblPrConTestHome = (TextView) findViewById(R.id.VlblPrConTestHome);
         rdogrpPrConTestHome = (RadioGroup) findViewById(R.id.rdogrpPrConTestHome);
         rdoPrConTestHome1 = (RadioButton) findViewById(R.id.rdoPrConTestHome1);
         rdoPrConTestHome2 = (RadioButton) findViewById(R.id.rdoPrConTestHome2);
         secPrConTestDoc=(LinearLayout)findViewById(R.id.secPrConTestDoc);
         linePrConTestDoc=(View)findViewById(R.id.linePrConTestDoc);
         VlblPrConTestDoc = (TextView) findViewById(R.id.VlblPrConTestDoc);
         rdogrpPrConTestDoc = (RadioGroup) findViewById(R.id.rdogrpPrConTestDoc);
         rdoPrConTestDoc1 = (RadioButton) findViewById(R.id.rdoPrConTestDoc1);
         rdoPrConTestDoc2 = (RadioButton) findViewById(R.id.rdoPrConTestDoc2);
         secPrConHeartSnd=(LinearLayout)findViewById(R.id.secPrConHeartSnd);
         linePrConHeartSnd=(View)findViewById(R.id.linePrConHeartSnd);
         VlblPrConHeartSnd = (TextView) findViewById(R.id.VlblPrConHeartSnd);
         rdogrpPrConHeartSnd = (RadioGroup) findViewById(R.id.rdogrpPrConHeartSnd);
         rdoPrConHeartSnd1 = (RadioButton) findViewById(R.id.rdoPrConHeartSnd1);
         rdoPrConHeartSnd2 = (RadioButton) findViewById(R.id.rdoPrConHeartSnd2);
         secPrConUtrasound=(LinearLayout)findViewById(R.id.secPrConUtrasound);
         linePrConUtrasound=(View)findViewById(R.id.linePrConUtrasound);
         VlblPrConUtrasound = (TextView) findViewById(R.id.VlblPrConUtrasound);
         rdogrpPrConUtrasound = (RadioGroup) findViewById(R.id.rdogrpPrConUtrasound);
         rdoPrConUtrasound1 = (RadioButton) findViewById(R.id.rdoPrConUtrasound1);
         rdoPrConUtrasound2 = (RadioButton) findViewById(R.id.rdoPrConUtrasound2);
         secPrConTestFaci=(LinearLayout)findViewById(R.id.secPrConTestFaci);
         linePrConTestFaci=(View)findViewById(R.id.linePrConTestFaci);
         VlblPrConTestFaci = (TextView) findViewById(R.id.VlblPrConTestFaci);
         rdogrpPrConTestFaci = (RadioGroup) findViewById(R.id.rdogrpPrConTestFaci);
         rdoPrConTestFaci1 = (RadioButton) findViewById(R.id.rdoPrConTestFaci1);
         rdoPrConTestFaci2 = (RadioButton) findViewById(R.id.rdoPrConTestFaci2);
         secPrConUrineTest=(LinearLayout)findViewById(R.id.secPrConUrineTest);
         linePrConUrineTest=(View)findViewById(R.id.linePrConUrineTest);
         VlblPrConUrineTest = (TextView) findViewById(R.id.VlblPrConUrineTest);
         rdogrpPrConUrineTest = (RadioGroup) findViewById(R.id.rdogrpPrConUrineTest);
         rdoPrConUrineTest1 = (RadioButton) findViewById(R.id.rdoPrConUrineTest1);
         rdoPrConUrineTest2 = (RadioButton) findViewById(R.id.rdoPrConUrineTest2);
         secPrConOth=(LinearLayout)findViewById(R.id.secPrConOth);
         linePrConOth=(View)findViewById(R.id.linePrConOth);
         VlblPrConOth = (TextView) findViewById(R.id.VlblPrConOth);
         rdogrpPrConOth = (RadioGroup) findViewById(R.id.rdogrpPrConOth);
         rdoPrConOth1 = (RadioButton) findViewById(R.id.rdoPrConOth1);
         rdoPrConOth2 = (RadioButton) findViewById(R.id.rdoPrConOth2);
         rdogrpPrConOth.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpPrConOth = new String[] {"0","1"};
             for (int i = 0; i < rdogrpPrConOth.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpPrConOth.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpPrConOth[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    secPrConOthSpecify.setVisibility(View.GONE);
                    linePrConOthSpecify.setVisibility(View.GONE);
                    txtPrConOthSpecify.setText("");
             }
             else
             {
                    secPrConOthSpecify.setVisibility(View.VISIBLE);
                    linePrConOthSpecify.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secPrConOthSpecify=(LinearLayout)findViewById(R.id.secPrConOthSpecify);
         linePrConOthSpecify=(View)findViewById(R.id.linePrConOthSpecify);
         VlblPrConOthSpecify=(TextView) findViewById(R.id.VlblPrConOthSpecify);
         txtPrConOthSpecify=(AutoCompleteTextView) findViewById(R.id.txtPrConOthSpecify);
         C.setupAutoComplete(TableName,txtPrConOthSpecify,"PrConOthSpecify"); //setup autocomplete view by event

         secPregPrenatalCons=(LinearLayout)findViewById(R.id.secPregPrenatalCons);
         linePregPrenatalCons=(View)findViewById(R.id.linePregPrenatalCons);
         VlblPregPrenatalCons = (TextView) findViewById(R.id.VlblPregPrenatalCons);
         rdogrpPregPrenatalCons = (RadioGroup) findViewById(R.id.rdogrpPregPrenatalCons);
         rdoPregPrenatalCons1 = (RadioButton) findViewById(R.id.rdoPregPrenatalCons1);
         rdoPregPrenatalCons2 = (RadioButton) findViewById(R.id.rdoPregPrenatalCons2);
         rdogrpPregPrenatalCons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpPregPrenatalCons = new String[] {"0","1"};
                 for (int i = 0; i < rdogrpPregPrenatalCons.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpPregPrenatalCons.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpPregPrenatalCons[i];
                 }

                 if(rbData.equalsIgnoreCase("1"))
                 {
                     seclbl03.setVisibility(View.VISIBLE);
                     linelbl03.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsA.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsA.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsB.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsB.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsC.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsC.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsD.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsD.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsE.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsE.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsF.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsF.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsG.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsG.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsH.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsH.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsI.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsI.setVisibility(View.VISIBLE);
                     seclbl04.setVisibility(View.VISIBLE);
                     linelbl04.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsPlaceA.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsPlaceA.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsPlaceB.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsPlaceB.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsPlaceC.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsPlaceC.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsPlaceD.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsPlaceD.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsPlaceE.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsPlaceE.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsPlaceF.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsPlaceF.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsPlaceG.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsPlaceG.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsPlaceH.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsPlaceH.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsPlaceI.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsPlaceI.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsPlaceJ.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsPlaceJ.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsPlaceX.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsPlaceX.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsPlaceY.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsPlaceY.setVisibility(View.VISIBLE);
                     secchkPregPrenatalConsPlaceZ.setVisibility(View.VISIBLE);
                     linechkPregPrenatalConsPlaceZ.setVisibility(View.VISIBLE);
                     secPregPrenatalConsPlaceOth.setVisibility(View.GONE);
                     linePregPrenatalConsPlaceOth.setVisibility(View.GONE);
                     txtPregPrenatalConsPlaceOth.setText("");
                 }
                 else
                 {
                     seclbl03.setVisibility(View.GONE);
                     linelbl03.setVisibility(View.GONE);
                     secchkPregPrenatalConsA.setVisibility(View.GONE);
                     linechkPregPrenatalConsA.setVisibility(View.GONE);
                     chkchkPregPrenatalConsA.setChecked(false);
                     secchkPregPrenatalConsB.setVisibility(View.GONE);
                     linechkPregPrenatalConsB.setVisibility(View.GONE);
                     chkchkPregPrenatalConsB.setChecked(false);
                     secchkPregPrenatalConsC.setVisibility(View.GONE);
                     linechkPregPrenatalConsC.setVisibility(View.GONE);
                     chkchkPregPrenatalConsC.setChecked(false);
                     secchkPregPrenatalConsD.setVisibility(View.GONE);
                     linechkPregPrenatalConsD.setVisibility(View.GONE);
                     chkchkPregPrenatalConsD.setChecked(false);
                     secchkPregPrenatalConsE.setVisibility(View.GONE);
                     linechkPregPrenatalConsE.setVisibility(View.GONE);
                     chkchkPregPrenatalConsE.setChecked(false);
                     secchkPregPrenatalConsF.setVisibility(View.GONE);
                     linechkPregPrenatalConsF.setVisibility(View.GONE);
                     chkchkPregPrenatalConsF.setChecked(false);
                     secchkPregPrenatalConsG.setVisibility(View.GONE);
                     linechkPregPrenatalConsG.setVisibility(View.GONE);
                     chkchkPregPrenatalConsG.setChecked(false);
                     secchkPregPrenatalConsH.setVisibility(View.GONE);
                     linechkPregPrenatalConsH.setVisibility(View.GONE);
                     chkchkPregPrenatalConsH.setChecked(false);
                     secchkPregPrenatalConsI.setVisibility(View.GONE);
                     linechkPregPrenatalConsI.setVisibility(View.GONE);
                     chkchkPregPrenatalConsI.setChecked(false);
                     seclbl04.setVisibility(View.GONE);
                     linelbl04.setVisibility(View.GONE);
                     secchkPregPrenatalConsPlaceA.setVisibility(View.GONE);
                     linechkPregPrenatalConsPlaceA.setVisibility(View.GONE);
                     chkchkPregPrenatalConsPlaceA.setChecked(false);
                     secchkPregPrenatalConsPlaceB.setVisibility(View.GONE);
                     linechkPregPrenatalConsPlaceB.setVisibility(View.GONE);
                     chkchkPregPrenatalConsPlaceB.setChecked(false);
                     secchkPregPrenatalConsPlaceC.setVisibility(View.GONE);
                     linechkPregPrenatalConsPlaceC.setVisibility(View.GONE);
                     chkchkPregPrenatalConsPlaceC.setChecked(false);
                     secchkPregPrenatalConsPlaceD.setVisibility(View.GONE);
                     linechkPregPrenatalConsPlaceD.setVisibility(View.GONE);
                     chkchkPregPrenatalConsPlaceD.setChecked(false);
                     secchkPregPrenatalConsPlaceE.setVisibility(View.GONE);
                     linechkPregPrenatalConsPlaceE.setVisibility(View.GONE);
                     chkchkPregPrenatalConsPlaceE.setChecked(false);
                     secchkPregPrenatalConsPlaceF.setVisibility(View.GONE);
                     linechkPregPrenatalConsPlaceF.setVisibility(View.GONE);
                     chkchkPregPrenatalConsPlaceF.setChecked(false);
                     secchkPregPrenatalConsPlaceG.setVisibility(View.GONE);
                     linechkPregPrenatalConsPlaceG.setVisibility(View.GONE);
                     chkchkPregPrenatalConsPlaceG.setChecked(false);
                     secchkPregPrenatalConsPlaceH.setVisibility(View.GONE);
                     linechkPregPrenatalConsPlaceH.setVisibility(View.GONE);
                     chkchkPregPrenatalConsPlaceH.setChecked(false);
                     secchkPregPrenatalConsPlaceI.setVisibility(View.GONE);
                     linechkPregPrenatalConsPlaceI.setVisibility(View.GONE);
                     chkchkPregPrenatalConsPlaceI.setChecked(false);
                     secchkPregPrenatalConsPlaceJ.setVisibility(View.GONE);
                     linechkPregPrenatalConsPlaceJ.setVisibility(View.GONE);
                     chkchkPregPrenatalConsPlaceJ.setChecked(false);
                     secchkPregPrenatalConsPlaceX.setVisibility(View.GONE);
                     linechkPregPrenatalConsPlaceX.setVisibility(View.GONE);
                     chkchkPregPrenatalConsPlaceX.setChecked(false);
                     secchkPregPrenatalConsPlaceY.setVisibility(View.GONE);
                     linechkPregPrenatalConsPlaceY.setVisibility(View.GONE);
                     chkchkPregPrenatalConsPlaceY.setChecked(false);
                     secchkPregPrenatalConsPlaceZ.setVisibility(View.GONE);
                     linechkPregPrenatalConsPlaceZ.setVisibility(View.GONE);
                     chkchkPregPrenatalConsPlaceZ.setChecked(false);
                     secPregPrenatalConsPlaceOth.setVisibility(View.GONE);
                     linePregPrenatalConsPlaceOth.setVisibility(View.GONE);
                     txtPregPrenatalConsPlaceOth.setText("");
                 }
             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });
         seclbl03=(LinearLayout)findViewById(R.id.seclbl03);
         linelbl03=(View)findViewById(R.id.linelbl03);
         secchkPregPrenatalConsA=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsA);
         linechkPregPrenatalConsA=(View)findViewById(R.id.linechkPregPrenatalConsA);
         VlblchkPregPrenatalConsA=(TextView) findViewById(R.id.VlblchkPregPrenatalConsA);
         chkchkPregPrenatalConsA=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsA);
         secchkPregPrenatalConsB=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsB);
         linechkPregPrenatalConsB=(View)findViewById(R.id.linechkPregPrenatalConsB);
         VlblchkPregPrenatalConsB=(TextView) findViewById(R.id.VlblchkPregPrenatalConsB);
         chkchkPregPrenatalConsB=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsB);
         secchkPregPrenatalConsC=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsC);
         linechkPregPrenatalConsC=(View)findViewById(R.id.linechkPregPrenatalConsC);
         VlblchkPregPrenatalConsC=(TextView) findViewById(R.id.VlblchkPregPrenatalConsC);
         chkchkPregPrenatalConsC=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsC);
         secchkPregPrenatalConsD=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsD);
         linechkPregPrenatalConsD=(View)findViewById(R.id.linechkPregPrenatalConsD);
         VlblchkPregPrenatalConsD=(TextView) findViewById(R.id.VlblchkPregPrenatalConsD);
         chkchkPregPrenatalConsD=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsD);
         secchkPregPrenatalConsE=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsE);
         linechkPregPrenatalConsE=(View)findViewById(R.id.linechkPregPrenatalConsE);
         VlblchkPregPrenatalConsE=(TextView) findViewById(R.id.VlblchkPregPrenatalConsE);
         chkchkPregPrenatalConsE=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsE);
         secchkPregPrenatalConsF=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsF);
         linechkPregPrenatalConsF=(View)findViewById(R.id.linechkPregPrenatalConsF);
         VlblchkPregPrenatalConsF=(TextView) findViewById(R.id.VlblchkPregPrenatalConsF);
         chkchkPregPrenatalConsF=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsF);
         secchkPregPrenatalConsG=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsG);
         linechkPregPrenatalConsG=(View)findViewById(R.id.linechkPregPrenatalConsG);
         VlblchkPregPrenatalConsG=(TextView) findViewById(R.id.VlblchkPregPrenatalConsG);
         chkchkPregPrenatalConsG=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsG);
         secchkPregPrenatalConsH=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsH);
         linechkPregPrenatalConsH=(View)findViewById(R.id.linechkPregPrenatalConsH);
         VlblchkPregPrenatalConsH=(TextView) findViewById(R.id.VlblchkPregPrenatalConsH);
         chkchkPregPrenatalConsH=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsH);
         secchkPregPrenatalConsI=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsI);
         linechkPregPrenatalConsI=(View)findViewById(R.id.linechkPregPrenatalConsI);
         VlblchkPregPrenatalConsI=(TextView) findViewById(R.id.VlblchkPregPrenatalConsI);
         chkchkPregPrenatalConsI=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsI);
         seclbl04=(LinearLayout)findViewById(R.id.seclbl04);
         linelbl04=(View)findViewById(R.id.linelbl04);
         secchkPregPrenatalConsPlaceA=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsPlaceA);
         linechkPregPrenatalConsPlaceA=(View)findViewById(R.id.linechkPregPrenatalConsPlaceA);
         VlblchkPregPrenatalConsPlaceA=(TextView) findViewById(R.id.VlblchkPregPrenatalConsPlaceA);
         chkchkPregPrenatalConsPlaceA=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsPlaceA);
         secchkPregPrenatalConsPlaceB=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsPlaceB);
         linechkPregPrenatalConsPlaceB=(View)findViewById(R.id.linechkPregPrenatalConsPlaceB);
         VlblchkPregPrenatalConsPlaceB=(TextView) findViewById(R.id.VlblchkPregPrenatalConsPlaceB);
         chkchkPregPrenatalConsPlaceB=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsPlaceB);
         secchkPregPrenatalConsPlaceC=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsPlaceC);
         linechkPregPrenatalConsPlaceC=(View)findViewById(R.id.linechkPregPrenatalConsPlaceC);
         VlblchkPregPrenatalConsPlaceC=(TextView) findViewById(R.id.VlblchkPregPrenatalConsPlaceC);
         chkchkPregPrenatalConsPlaceC=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsPlaceC);
         secchkPregPrenatalConsPlaceD=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsPlaceD);
         linechkPregPrenatalConsPlaceD=(View)findViewById(R.id.linechkPregPrenatalConsPlaceD);
         VlblchkPregPrenatalConsPlaceD=(TextView) findViewById(R.id.VlblchkPregPrenatalConsPlaceD);
         chkchkPregPrenatalConsPlaceD=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsPlaceD);
         secchkPregPrenatalConsPlaceE=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsPlaceE);
         linechkPregPrenatalConsPlaceE=(View)findViewById(R.id.linechkPregPrenatalConsPlaceE);
         VlblchkPregPrenatalConsPlaceE=(TextView) findViewById(R.id.VlblchkPregPrenatalConsPlaceE);
         chkchkPregPrenatalConsPlaceE=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsPlaceE);
         secchkPregPrenatalConsPlaceF=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsPlaceF);
         linechkPregPrenatalConsPlaceF=(View)findViewById(R.id.linechkPregPrenatalConsPlaceF);
         VlblchkPregPrenatalConsPlaceF=(TextView) findViewById(R.id.VlblchkPregPrenatalConsPlaceF);
         chkchkPregPrenatalConsPlaceF=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsPlaceF);
         secchkPregPrenatalConsPlaceG=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsPlaceG);
         linechkPregPrenatalConsPlaceG=(View)findViewById(R.id.linechkPregPrenatalConsPlaceG);
         VlblchkPregPrenatalConsPlaceG=(TextView) findViewById(R.id.VlblchkPregPrenatalConsPlaceG);
         chkchkPregPrenatalConsPlaceG=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsPlaceG);
         secchkPregPrenatalConsPlaceH=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsPlaceH);
         linechkPregPrenatalConsPlaceH=(View)findViewById(R.id.linechkPregPrenatalConsPlaceH);
         VlblchkPregPrenatalConsPlaceH=(TextView) findViewById(R.id.VlblchkPregPrenatalConsPlaceH);
         chkchkPregPrenatalConsPlaceH=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsPlaceH);
         secchkPregPrenatalConsPlaceI=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsPlaceI);
         linechkPregPrenatalConsPlaceI=(View)findViewById(R.id.linechkPregPrenatalConsPlaceI);
         VlblchkPregPrenatalConsPlaceI=(TextView) findViewById(R.id.VlblchkPregPrenatalConsPlaceI);
         chkchkPregPrenatalConsPlaceI=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsPlaceI);
         secchkPregPrenatalConsPlaceJ=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsPlaceJ);
         linechkPregPrenatalConsPlaceJ=(View)findViewById(R.id.linechkPregPrenatalConsPlaceJ);
         VlblchkPregPrenatalConsPlaceJ=(TextView) findViewById(R.id.VlblchkPregPrenatalConsPlaceJ);
         chkchkPregPrenatalConsPlaceJ=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsPlaceJ);
         secchkPregPrenatalConsPlaceX=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsPlaceX);
         linechkPregPrenatalConsPlaceX=(View)findViewById(R.id.linechkPregPrenatalConsPlaceX);
         VlblchkPregPrenatalConsPlaceX=(TextView) findViewById(R.id.VlblchkPregPrenatalConsPlaceX);
         chkchkPregPrenatalConsPlaceX=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsPlaceX);
         secchkPregPrenatalConsPlaceY=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsPlaceY);
         linechkPregPrenatalConsPlaceY=(View)findViewById(R.id.linechkPregPrenatalConsPlaceY);
         VlblchkPregPrenatalConsPlaceY=(TextView) findViewById(R.id.VlblchkPregPrenatalConsPlaceY);
         chkchkPregPrenatalConsPlaceY=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsPlaceY);
         secchkPregPrenatalConsPlaceZ=(LinearLayout)findViewById(R.id.secchkPregPrenatalConsPlaceZ);
         linechkPregPrenatalConsPlaceZ=(View)findViewById(R.id.linechkPregPrenatalConsPlaceZ);
         VlblchkPregPrenatalConsPlaceZ=(TextView) findViewById(R.id.VlblchkPregPrenatalConsPlaceZ);
         chkchkPregPrenatalConsPlaceZ=(CheckBox) findViewById(R.id.chkchkPregPrenatalConsPlaceZ);
         secPregPrenatalConsPlaceOth=(LinearLayout)findViewById(R.id.secPregPrenatalConsPlaceOth);
         linePregPrenatalConsPlaceOth=(View)findViewById(R.id.linePregPrenatalConsPlaceOth);
         VlblPregPrenatalConsPlaceOth=(TextView) findViewById(R.id.VlblPregPrenatalConsPlaceOth);
         txtPregPrenatalConsPlaceOth=(AutoCompleteTextView) findViewById(R.id.txtPregPrenatalConsPlaceOth);
         C.setupAutoComplete(TableName,txtPregPrenatalConsPlaceOth,"PregPrenatalConsPlaceOth"); //setup autocomplete view by event

         chkchkPregPrenatalConsPlaceX.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     secPregPrenatalConsPlaceOth.setVisibility(View.VISIBLE);
                     linePregPrenatalConsPlaceOth.setVisibility(View.VISIBLE);
                 }
                 else {
                     secPregPrenatalConsPlaceOth.setVisibility(View.GONE);
                     linePregPrenatalConsPlaceOth.setVisibility(View.GONE);
                     txtPregPrenatalConsPlaceOth.setText("");
                 }
             }
         });

         secPregRank=(LinearLayout)findViewById(R.id.secPregRank);
         linePregRank=(View)findViewById(R.id.linePregRank);
         VlblPregRank=(TextView) findViewById(R.id.VlblPregRank);
         txtPregRank=(EditText) findViewById(R.id.txtPregRank);
         secPregEnrolled=(LinearLayout)findViewById(R.id.secPregEnrolled);
         linePregEnrolled=(View)findViewById(R.id.linePregEnrolled);
         VlblPregEnrolled = (TextView) findViewById(R.id.VlblPregEnrolled);
         rdogrpPregEnrolled = (RadioGroup) findViewById(R.id.rdogrpPregEnrolled);
         rdoPregEnrolled1 = (RadioButton) findViewById(R.id.rdoPregEnrolled1);
         rdoPregEnrolled2 = (RadioButton) findViewById(R.id.rdoPregEnrolled2);
         rdoPregEnrolled3 = (RadioButton) findViewById(R.id.rdoPregEnrolled3);
         rdoPregEnrolled4 = (RadioButton) findViewById(R.id.rdoPregEnrolled4);
         rdogrpPregEnrolled.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpPregEnrolled = new String[] {"0","1","8","9"};
                 for (int i = 0; i < rdogrpPregEnrolled.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpPregEnrolled.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpPregEnrolled[i];
                 }

                 if(rbData.equalsIgnoreCase("1"))
                 {
                     secPregPSIDKnown.setVisibility(View.VISIBLE);
                     linePregPSIDKnown.setVisibility(View.VISIBLE);
                     secPregPSID.setVisibility(View.VISIBLE);
                     linePregPSID.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secPregPSIDKnown.setVisibility(View.GONE);
                     linePregPSIDKnown.setVisibility(View.GONE);
                     rdogrpPregPSIDKnown.clearCheck();
                     secPregPSID.setVisibility(View.GONE);
                     linePregPSID.setVisibility(View.GONE);
                     txtPregPSID.setText("");
                 }
             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });
         secPregPSIDKnown=(LinearLayout)findViewById(R.id.secPregPSIDKnown);
         linePregPSIDKnown=(View)findViewById(R.id.linePregPSIDKnown);
         VlblPregPSIDKnown = (TextView) findViewById(R.id.VlblPregPSIDKnown);
         rdogrpPregPSIDKnown = (RadioGroup) findViewById(R.id.rdogrpPregPSIDKnown);
         rdoPregPSIDKnown1 = (RadioButton) findViewById(R.id.rdoPregPSIDKnown1);
         rdoPregPSIDKnown2 = (RadioButton) findViewById(R.id.rdoPregPSIDKnown2);
         rdoPregPSIDKnown3 = (RadioButton) findViewById(R.id.rdoPregPSIDKnown3);
         rdoPregPSIDKnown4 = (RadioButton) findViewById(R.id.rdoPregPSIDKnown4);

         rdogrpPregPSIDKnown.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpPregPSIDKnown = new String[] {"0","1","8","9"};
                 for (int i = 0; i < rdogrpPregPSIDKnown.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpPregPSIDKnown.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpPregPSIDKnown[i];
                 }

                 if(rbData.equalsIgnoreCase("1"))
                 {
                     secPregPSID.setVisibility(View.VISIBLE);
                     linePregPSID.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secPregPSID.setVisibility(View.GONE);
                     linePregPSID.setVisibility(View.GONE);
                     txtPregPSID.setText("");
                 }
             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });

         secPregPSID=(LinearLayout)findViewById(R.id.secPregPSID);
         linePregPSID=(View)findViewById(R.id.linePregPSID);
         VlblPregPSID=(TextView) findViewById(R.id.VlblPregPSID);
         txtPregPSID=(EditText) findViewById(R.id.txtPregPSID);
         secPregWishEnroll=(LinearLayout)findViewById(R.id.secPregWishEnroll);
         linePregWishEnroll=(View)findViewById(R.id.linePregWishEnroll);
         VlblPregWishEnroll = (TextView) findViewById(R.id.VlblPregWishEnroll);
         rdogrpPregWishEnroll = (RadioGroup) findViewById(R.id.rdogrpPregWishEnroll);
         rdoPregWishEnroll1 = (RadioButton) findViewById(R.id.rdoPregWishEnroll1);
         rdoPregWishEnroll2 = (RadioButton) findViewById(R.id.rdoPregWishEnroll2);
         rdoPregWishEnroll3 = (RadioButton) findViewById(R.id.rdoPregWishEnroll3);
         rdoPregWishEnroll4 = (RadioButton) findViewById(R.id.rdoPregWishEnroll4);
         secPregVdate=(LinearLayout)findViewById(R.id.secPregVdate);
         linePregVdate=(View)findViewById(R.id.linePregVdate);
         VlblPregVdate=(TextView) findViewById(R.id.VlblPregVdate);
         dtpPregVdate=(EditText) findViewById(R.id.dtpPregVdate);
         dtpPregVdate.setText(VISIT_DATE);
         secPregVdate.setVisibility(View.GONE);

         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         txtRnd.setText(ROUND);
         txtRnd.setEnabled(false);
         secPregNote=(LinearLayout)findViewById(R.id.secPregNote);
         linePregNote=(View)findViewById(R.id.linePregNote);
         VlblPregNote=(TextView) findViewById(R.id.VlblPregNote);
         txtPregNote=(EditText) findViewById(R.id.txtPregNote);

         secPregNote1=(LinearLayout)findViewById(R.id.secPregNote1);
         linePregNote1=(View)findViewById(R.id.linePregNote1);


     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Pregnancy.this, e.getMessage());
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
         	Connection.MessageBox(Surv_Pregnancy.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         tmpPregnancy_DataModel objSave = new tmpPregnancy_DataModel();
         objSave.setPregOccurID(txtPregOccurID.getText().toString());
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setMemID(txtMemID.getText().toString());
         objSave.setLMPDt(dtpLMPDt.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpLMPDt.getText().toString()) : dtpLMPDt.getText().toString());


         String[] d_rdogrpLMPDtType = new String[] {"1","2"};
         objSave.setLMPDtType("");
         for (int i = 0; i < rdogrpLMPDtType.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLMPDtType.getChildAt(i);
             if (rb.isChecked()) objSave.setLMPDtType(d_rdogrpLMPDtType[i]);
         }

         String[] d_rdogrpLMPDtObt = new String[] {"1","2"};
         objSave.setLMPDtObt("");
         for (int i = 0; i < rdogrpLMPDtObt.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLMPDtObt.getChildAt(i);
             if (rb.isChecked()) objSave.setLMPDtObt(d_rdogrpLMPDtObt[i]);
         }

         String[] d_rdogrpPrConLMP = new String[] {"0","1"};
         objSave.setPrConLMP("");
         for (int i = 0; i < rdogrpPrConLMP.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPrConLMP.getChildAt(i);
             if (rb.isChecked()) objSave.setPrConLMP(d_rdogrpPrConLMP[i]);
         }

         String[] d_rdogrpPrConBelly = new String[] {"0","1"};
         objSave.setPrConBelly("");
         for (int i = 0; i < rdogrpPrConBelly.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPrConBelly.getChildAt(i);
             if (rb.isChecked()) objSave.setPrConBelly(d_rdogrpPrConBelly[i]);
         }

         String[] d_rdogrpPrConFeMove = new String[] {"0","1"};
         objSave.setPrConFeMove("");
         for (int i = 0; i < rdogrpPrConFeMove.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPrConFeMove.getChildAt(i);
             if (rb.isChecked()) objSave.setPrConFeMove(d_rdogrpPrConFeMove[i]);
         }

         String[] d_rdogrpPrConTestHome = new String[] {"0","1"};
         objSave.setPrConTestHome("");
         for (int i = 0; i < rdogrpPrConTestHome.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPrConTestHome.getChildAt(i);
             if (rb.isChecked()) objSave.setPrConTestHome(d_rdogrpPrConTestHome[i]);
         }

         String[] d_rdogrpPrConTestDoc = new String[] {"0","1"};
         objSave.setPrConTestDoc("");
         for (int i = 0; i < rdogrpPrConTestDoc.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPrConTestDoc.getChildAt(i);
             if (rb.isChecked()) objSave.setPrConTestDoc(d_rdogrpPrConTestDoc[i]);
         }

         String[] d_rdogrpPrConHeartSnd = new String[] {"0","1"};
         objSave.setPrConHeartSnd("");
         for (int i = 0; i < rdogrpPrConHeartSnd.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPrConHeartSnd.getChildAt(i);
             if (rb.isChecked()) objSave.setPrConHeartSnd(d_rdogrpPrConHeartSnd[i]);
         }

         String[] d_rdogrpPrConUtrasound = new String[] {"0","1"};
         objSave.setPrConUtrasound("");
         for (int i = 0; i < rdogrpPrConUtrasound.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPrConUtrasound.getChildAt(i);
             if (rb.isChecked()) objSave.setPrConUtrasound(d_rdogrpPrConUtrasound[i]);
         }

         String[] d_rdogrpPrConTestFaci = new String[] {"0","1"};
         objSave.setPrConTestFaci("");
         for (int i = 0; i < rdogrpPrConTestFaci.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPrConTestFaci.getChildAt(i);
             if (rb.isChecked()) objSave.setPrConTestFaci(d_rdogrpPrConTestFaci[i]);
         }

         String[] d_rdogrpPrConUrineTest = new String[] {"0","1"};
         objSave.setPrConUrineTest("");
         for (int i = 0; i < rdogrpPrConUrineTest.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPrConUrineTest.getChildAt(i);
             if (rb.isChecked()) objSave.setPrConUrineTest(d_rdogrpPrConUrineTest[i]);
         }

         String[] d_rdogrpPrConOth = new String[] {"0","1"};
         objSave.setPrConOth("");
         for (int i = 0; i < rdogrpPrConOth.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPrConOth.getChildAt(i);
             if (rb.isChecked()) objSave.setPrConOth(d_rdogrpPrConOth[i]);
         }

         objSave.setPrConOthSpecify(txtPrConOthSpecify.getText().toString());

         objSave.setPrConOthSpecify(txtPrConOthSpecify.getText().toString());
         String[] d_rdogrpPregPrenatalCons = new String[] {"0","1"};
         objSave.setPregPrenatalCons("");
         for (int i = 0; i < rdogrpPregPrenatalCons.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPregPrenatalCons.getChildAt(i);
             if (rb.isChecked()) objSave.setPregPrenatalCons(d_rdogrpPregPrenatalCons[i]);
         }

         objSave.setchkPregPrenatalConsA((chkchkPregPrenatalConsA.isChecked() ? "1" : (secchkPregPrenatalConsA.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsB((chkchkPregPrenatalConsB.isChecked() ? "1" : (secchkPregPrenatalConsB.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsC((chkchkPregPrenatalConsC.isChecked() ? "1" : (secchkPregPrenatalConsC.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsD((chkchkPregPrenatalConsD.isChecked() ? "1" : (secchkPregPrenatalConsD.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsE((chkchkPregPrenatalConsE.isChecked() ? "1" : (secchkPregPrenatalConsE.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsF((chkchkPregPrenatalConsF.isChecked() ? "1" : (secchkPregPrenatalConsF.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsG((chkchkPregPrenatalConsG.isChecked() ? "1" : (secchkPregPrenatalConsG.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsH((chkchkPregPrenatalConsH.isChecked() ? "1" : (secchkPregPrenatalConsH.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsI((chkchkPregPrenatalConsI.isChecked() ? "1" : (secchkPregPrenatalConsI.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsPlaceA((chkchkPregPrenatalConsPlaceA.isChecked() ? "1" : (secchkPregPrenatalConsPlaceA.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsPlaceB((chkchkPregPrenatalConsPlaceB.isChecked() ? "1" : (secchkPregPrenatalConsPlaceB.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsPlaceC((chkchkPregPrenatalConsPlaceC.isChecked() ? "1" : (secchkPregPrenatalConsPlaceC.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsPlaceD((chkchkPregPrenatalConsPlaceD.isChecked() ? "1" : (secchkPregPrenatalConsPlaceD.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsPlaceE((chkchkPregPrenatalConsPlaceE.isChecked() ? "1" : (secchkPregPrenatalConsPlaceE.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsPlaceF((chkchkPregPrenatalConsPlaceF.isChecked() ? "1" : (secchkPregPrenatalConsPlaceF.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsPlaceG((chkchkPregPrenatalConsPlaceG.isChecked() ? "1" : (secchkPregPrenatalConsPlaceG.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsPlaceH((chkchkPregPrenatalConsPlaceH.isChecked() ? "1" : (secchkPregPrenatalConsPlaceH.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsPlaceI((chkchkPregPrenatalConsPlaceI.isChecked() ? "1" : (secchkPregPrenatalConsPlaceI.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsPlaceJ((chkchkPregPrenatalConsPlaceJ.isChecked() ? "1" : (secchkPregPrenatalConsPlaceJ.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsPlaceX((chkchkPregPrenatalConsPlaceX.isChecked() ? "1" : (secchkPregPrenatalConsPlaceX.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsPlaceY((chkchkPregPrenatalConsPlaceY.isChecked() ? "1" : (secchkPregPrenatalConsPlaceY.isShown() ? "2" : "")));
         objSave.setchkPregPrenatalConsPlaceZ((chkchkPregPrenatalConsPlaceZ.isChecked() ? "1" : (secchkPregPrenatalConsPlaceZ.isShown() ? "2" : "")));
         objSave.setPregPrenatalConsPlaceOth(txtPregPrenatalConsPlaceOth.getText().toString());
         objSave.setPregRank(txtPregRank.getText().toString());
         String[] d_rdogrpPregEnrolled = new String[] {"0","1","8","9"};
         objSave.setPregEnrolled("");
         for (int i = 0; i < rdogrpPregEnrolled.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPregEnrolled.getChildAt(i);
             if (rb.isChecked()) objSave.setPregEnrolled(d_rdogrpPregEnrolled[i]);
         }

         String[] d_rdogrpPregPSIDKnown = new String[] {"0","1","8","9"};
         objSave.setPregPSIDKnown("");
         for (int i = 0; i < rdogrpPregPSIDKnown.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPregPSIDKnown.getChildAt(i);
             if (rb.isChecked()) objSave.setPregPSIDKnown(d_rdogrpPregPSIDKnown[i]);
         }

         objSave.setPregPSID(txtPregPSID.getText().toString());
         String[] d_rdogrpPregWishEnroll = new String[] {"0","1","8","9"};
         objSave.setPregWishEnroll("");
         for (int i = 0; i < rdogrpPregWishEnroll.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPregWishEnroll.getChildAt(i);
             if (rb.isChecked()) objSave.setPregWishEnroll(d_rdogrpPregWishEnroll[i]);
         }
         objSave.setPregVdate(dtpPregVdate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpPregVdate.getText().toString()) : dtpPregVdate.getText().toString());
         objSave.setRnd(txtRnd.getText().toString());
         objSave.setPregNote(txtPregNote.getText().toString());
         objSave.setStartTime(STARTTIME);
         objSave.setEndTime(g.CurrentTime24());
         objSave.setDeviceID(DEVICEID);
         objSave.setEntryUser(ENTRYUSER); //from data entry user list
         objSave.setLat(MySharedPreferences.getValue(this, "lat"));
         objSave.setLon(MySharedPreferences.getValue(this, "lon"));

         String status = objSave.SaveUpdateData(this);
         if(status.length()==0) {
             C.SaveData("Update tmpMember set Upload='2',modifydate='"+ Global.DateTimeNowYMDHMS() +"',Pstat='41',LmpDt='" + Global.DateConvertYMD(dtpLMPDt.getText().toString()) +"' where MemID='" + MEM_ID + "'");

             Intent returnIntent = new Intent();
             returnIntent.putExtra("res", "");
             setResult(Activity.RESULT_OK, returnIntent);

             Toast.makeText(Surv_Pregnancy.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
             finish();
         }
         else{
             Connection.MessageBox(Surv_Pregnancy.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_Pregnancy.this, e.getMessage());
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
         if(txtPregOccurID.getText().toString().length()==0 & secPregOccurID.isShown())
           {
             ValidationMsg += "\nRequired field: Pregnancy Occurrence Internal ID.";
             secPregOccurID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
         DV = Global.DateValidate(dtpLMPDt.getText().toString());
         if(DV.length()!=0 & secLMPDt.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: What is the start date of your last menstrual period (LMP)?   (Day/Month/Year).";
             secLMPDt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }

         if(Global.DateDifferenceDays(Global.DateNowDMY(), dtpLMPDt.getText().toString()) < 30){
             ValidationMsg += "\nLMP Date can not be within 30 days from now.";
             secLMPDt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if (!prevPregDate.equals("") & prevPregDate.length() > 0 & isEdit.equals("0")) {
             if (!Global.isLMPDtGreaterThanPrevious(Global.DateConvertYMD(dtpLMPDt.getText().toString()), prevPregDate)){
                 ValidationMsg += "\nLMP Date can not be smaller than previous LMP Date";
                 secLMPDt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
         }
         if(!rdoLMPDtType1.isChecked() & !rdoLMPDtType2.isChecked() & secLMPDtType.isShown())
           {
             ValidationMsg += "\nRequired field: What is the start date of your last menstrual period (LMP)?.";
             secLMPDtType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoLMPDtObt1.isChecked() & !rdoLMPDtObt2.isChecked() & secLMPDtObt.isShown())
           {
             ValidationMsg += "\nRequired field: How is the start date of last menstrual period obtained?.";
             secLMPDtObt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPrConLMP1.isChecked() & !rdoPrConLMP2.isChecked()  & secPrConLMP.isShown())
           {
             ValidationMsg += "\n4.1 Required field: Last menstrual period 6 weeks ago or Longer.";
             secPrConLMP.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPrConBelly1.isChecked() & !rdoPrConBelly2.isChecked() & secPrConBelly.isShown())
           {
             ValidationMsg += "\n4.2 Required field: Pregnant belly visually apparent.";
             secPrConBelly.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPrConFeMove1.isChecked() & !rdoPrConFeMove2.isChecked() & secPrConFeMove.isShown())
           {
             ValidationMsg += "\n4.3 Required field: Pregnant woman has felt fetal movement.";
             secPrConFeMove.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPrConTestHome1.isChecked() & !rdoPrConTestHome2.isChecked() & secPrConTestHome.isShown())
           {
             ValidationMsg += "\n4.4 Required field:  Positive pregnancy test at home.";
             secPrConTestHome.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPrConTestDoc1.isChecked() & !rdoPrConTestDoc2.isChecked() & secPrConTestDoc.isShown())
           {
             ValidationMsg += "\n4.5 Required field: Physical examination by Doctor or health care provider.";
             secPrConTestDoc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPrConHeartSnd1.isChecked() & !rdoPrConHeartSnd2.isChecked() & secPrConHeartSnd.isShown())
           {
             ValidationMsg += "\n4.6 Required field: Auscultation of fetal heart sound by Doctor or health care provider.";
             secPrConHeartSnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPrConUtrasound1.isChecked() & !rdoPrConUtrasound2.isChecked() & secPrConUtrasound.isShown())
           {
             ValidationMsg += "\n4.7 Required field: Ultrasound.";
             secPrConUtrasound.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPrConTestFaci1.isChecked() & !rdoPrConTestFaci2.isChecked() & secPrConTestFaci.isShown())
           {
             ValidationMsg += "\n4.8 Required field: Positive pregnancy test at a facility.";
             secPrConTestFaci.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPrConUrineTest1.isChecked() & !rdoPrConUrineTest2.isChecked() & secPrConUrineTest.isShown())
           {
             ValidationMsg += "\n4.9 Required field: Positive pregnancy test (urine) by HDSS staff.";
             secPrConUrineTest.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPrConOth1.isChecked() & !rdoPrConOth2.isChecked() & secPrConOth.isShown())
           {
             ValidationMsg += "\n4.10 Required field: Others.";
             secPrConOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtPrConOthSpecify.getText().toString().length()==0 & secPrConOthSpecify.isShown())
           {
             ValidationMsg += "\nRequired field: Criteria confirmed the pregnancy Others.";
             secPrConOthSpecify.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }

         if(!rdoPregPrenatalCons1.isChecked() & !rdoPregPrenatalCons2.isChecked() & secPregPrenatalCons.isShown())
         {
             ValidationMsg += "\nRequired field: Have you ever done a prenatal consultation (ANC)?.";
             secPregPrenatalCons.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtPregPrenatalConsPlaceOth.getText().toString().length()==0 & secPregPrenatalConsPlaceOth.isShown())
         {
             ValidationMsg += "\nRequired field: Specify Others.";
             secPregPrenatalConsPlaceOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtPregRank.getText().toString().length()==0 & secPregRank.isShown())
         {
             ValidationMsg += "\nRequired field: Pregnancy rank.";
             secPregRank.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!rdoPregEnrolled1.isChecked() & !rdoPregEnrolled2.isChecked() & !rdoPregEnrolled3.isChecked() & !rdoPregEnrolled4.isChecked() & secPregEnrolled.isShown())
         {
             ValidationMsg += "\nRequired field: Was this pregnancy enrolled in the Pregnancy Surveillance (PS) Study?.";
             secPregEnrolled.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!rdoPregPSIDKnown1.isChecked() & !rdoPregPSIDKnown2.isChecked() & !rdoPregPSIDKnown3.isChecked() & !rdoPregPSIDKnown4.isChecked() & secPregPSIDKnown.isShown())
         {
             ValidationMsg += "\nRequired field: Is the PS ID known?.";
             secPregPSIDKnown.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtPregPSID.getText().toString().length()==0 & secPregPSID.isShown())
         {
             ValidationMsg += "\nRequired field: PS ID.";
             secPregPSID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(secPregPSID.isShown() & (Integer.valueOf(txtPregPSID.getText().toString().length()==0 ? "1" : txtPregPSID.getText().toString()) < 1 || Integer.valueOf(txtPregPSID.getText().toString().length()==0 ? "99999" : txtPregPSID.getText().toString()) > 99999))
         {
             ValidationMsg += "\nValue should be between 1 and 99999(PS ID).";
             secPregPSID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!rdoPregWishEnroll1.isChecked() & !rdoPregWishEnroll2.isChecked() & !rdoPregWishEnroll3.isChecked() & !rdoPregWishEnroll4.isChecked() & secPregWishEnroll.isShown())
         {
             ValidationMsg += "\nRequired field: Would you wish to enroll in the PS study?.";
             secPregWishEnroll.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         DV = Global.DateValidate(dtpPregVdate.getText().toString());
         if(DV.length()!=0 & secPregVdate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Visit date.";
             secPregVdate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             ValidationMsg += "\nRequired field: Round number.";
             secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }

         if(rdoPrConLMP1.isChecked() & rdoPrConBelly1.isChecked() & rdoPrConFeMove1.isChecked() & rdoPrConTestHome1.isChecked() & rdoPrConTestDoc1.isChecked() & rdoPrConHeartSnd1.isChecked() & rdoPrConUtrasound1.isChecked() & rdoPrConTestFaci1.isChecked() & rdoPrConUrineTest1.isChecked() & rdoPrConOth1.isChecked()){
             ValidationMsg += "\nRequired field: Pregnancy Confirmation criteria required";
             seclbl02.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

//         if(txtPregNote.getText().toString().length()==0 & secPregNote.isShown())
//           {
//             ValidationMsg += "\nRequired field: Note.";
//             secPregNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//           }
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
             secPregOccurID.setBackgroundColor(Color.WHITE);
             secHHID.setBackgroundColor(Color.WHITE);
             secMemID.setBackgroundColor(Color.WHITE);
             secLMPDt.setBackgroundColor(Color.WHITE);
             secLMPDtType.setBackgroundColor(Color.WHITE);
             secLMPDtObt.setBackgroundColor(Color.WHITE);
             secPrConLMP.setBackgroundColor(Color.WHITE);
             secPrConBelly.setBackgroundColor(Color.WHITE);
             secPrConFeMove.setBackgroundColor(Color.WHITE);
             secPrConTestHome.setBackgroundColor(Color.WHITE);
             secPrConTestDoc.setBackgroundColor(Color.WHITE);
             secPrConHeartSnd.setBackgroundColor(Color.WHITE);
             secPrConUtrasound.setBackgroundColor(Color.WHITE);
             secPrConTestFaci.setBackgroundColor(Color.WHITE);
             secPrConUrineTest.setBackgroundColor(Color.WHITE);
             secPrConOth.setBackgroundColor(Color.WHITE);
             secPrConOthSpecify.setBackgroundColor(Color.WHITE);
         secPregPrenatalCons.setBackgroundColor(Color.WHITE);
         secPregPrenatalConsPlaceOth.setBackgroundColor(Color.WHITE);
         secPregRank.setBackgroundColor(Color.WHITE);
         secPregEnrolled.setBackgroundColor(Color.WHITE);
         secPregPSIDKnown.setBackgroundColor(Color.WHITE);
         secPregPSID.setBackgroundColor(Color.WHITE);
         secPregPSID.setBackgroundColor(Color.WHITE);
         secPregWishEnroll.setBackgroundColor(Color.WHITE);
             secPregVdate.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
             secPregNote.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String PregOccurID)
     {
       try
        {     
           RadioButton rb;
            tmpPregnancy_DataModel d = new tmpPregnancy_DataModel();
           String SQL = "Select * from "+ TableName +"  Where PregOccurID='"+ PregOccurID +"'";
           List<tmpPregnancy_DataModel> data = d.SelectAll(this, SQL);
           for(tmpPregnancy_DataModel item : data){
             txtPregOccurID.setText(item.getPregOccurID());
             txtHHID.setText(item.getHHID());
             txtMemID.setText(item.getMemID());
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
             String[] d_rdogrpLMPDtObt = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpLMPDtObt.length; i++)
             {
                 if (String.valueOf(item.getLMPDtObt()).equals(String.valueOf(d_rdogrpLMPDtObt[i])))
                 {
                     rb = (RadioButton)rdogrpLMPDtObt.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpPrConLMP = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPrConLMP.length; i++)
             {
                 if (String.valueOf(item.getPrConLMP()).equals(String.valueOf(d_rdogrpPrConLMP[i])))
                 {
                     rb = (RadioButton)rdogrpPrConLMP.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpPrConBelly = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPrConBelly.length; i++)
             {
                 if (String.valueOf(item.getPrConBelly()).equals(String.valueOf(d_rdogrpPrConBelly[i])))
                 {
                     rb = (RadioButton)rdogrpPrConBelly.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpPrConFeMove = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPrConFeMove.length; i++)
             {
                 if (String.valueOf(item.getPrConFeMove()).equals(String.valueOf(d_rdogrpPrConFeMove[i])))
                 {
                     rb = (RadioButton)rdogrpPrConFeMove.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpPrConTestHome = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPrConTestHome.length; i++)
             {
                 if (String.valueOf(item.getPrConTestHome()).equals(String.valueOf(d_rdogrpPrConTestHome[i])))
                 {
                     rb = (RadioButton)rdogrpPrConTestHome.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpPrConTestDoc = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPrConTestDoc.length; i++)
             {
                 if (String.valueOf(item.getPrConTestDoc()).equals(String.valueOf(d_rdogrpPrConTestDoc[i])))
                 {
                     rb = (RadioButton)rdogrpPrConTestDoc.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpPrConHeartSnd = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPrConHeartSnd.length; i++)
             {
                 if (String.valueOf(item.getPrConHeartSnd()).equals(String.valueOf(d_rdogrpPrConHeartSnd[i])))
                 {
                     rb = (RadioButton)rdogrpPrConHeartSnd.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpPrConUtrasound = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPrConUtrasound.length; i++)
             {
                 if (String.valueOf(item.getPrConUtrasound()).equals(String.valueOf(d_rdogrpPrConUtrasound[i])))
                 {
                     rb = (RadioButton)rdogrpPrConUtrasound.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpPrConTestFaci = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPrConTestFaci.length; i++)
             {
                 if (String.valueOf(item.getPrConTestFaci()).equals(String.valueOf(d_rdogrpPrConTestFaci[i])))
                 {
                     rb = (RadioButton)rdogrpPrConTestFaci.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpPrConUrineTest = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPrConUrineTest.length; i++)
             {
                 if (String.valueOf(item.getPrConUrineTest()).equals(String.valueOf(d_rdogrpPrConUrineTest[i])))
                 {
                     rb = (RadioButton)rdogrpPrConUrineTest.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpPrConOth = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPrConOth.length; i++)
             {
                 if (String.valueOf(item.getPrConOth()).equals(String.valueOf(d_rdogrpPrConOth[i])))
                 {
                     rb = (RadioButton)rdogrpPrConOth.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtPrConOthSpecify.setText(item.getPrConOthSpecify());
               String[] d_rdogrpPregPrenatalCons = new String[] {"0","1"};
               for (int i = 0; i < d_rdogrpPregPrenatalCons.length; i++)
               {
                   if (String.valueOf(item.getPregPrenatalCons()).equals(String.valueOf(d_rdogrpPregPrenatalCons[i])))
                   {
                       rb = (RadioButton)rdogrpPregPrenatalCons.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
               if(String.valueOf(item.getchkPregPrenatalConsA()).equals("1"))
               {
                   chkchkPregPrenatalConsA.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsA()).equals("2"))
               {
                   chkchkPregPrenatalConsA.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsB()).equals("1"))
               {
                   chkchkPregPrenatalConsB.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsB()).equals("2"))
               {
                   chkchkPregPrenatalConsB.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsC()).equals("1"))
               {
                   chkchkPregPrenatalConsC.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsC()).equals("2"))
               {
                   chkchkPregPrenatalConsC.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsD()).equals("1"))
               {
                   chkchkPregPrenatalConsD.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsD()).equals("2"))
               {
                   chkchkPregPrenatalConsD.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsE()).equals("1"))
               {
                   chkchkPregPrenatalConsE.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsE()).equals("2"))
               {
                   chkchkPregPrenatalConsE.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsF()).equals("1"))
               {
                   chkchkPregPrenatalConsF.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsF()).equals("2"))
               {
                   chkchkPregPrenatalConsF.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsG()).equals("1"))
               {
                   chkchkPregPrenatalConsG.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsG()).equals("2"))
               {
                   chkchkPregPrenatalConsG.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsH()).equals("1"))
               {
                   chkchkPregPrenatalConsH.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsH()).equals("2"))
               {
                   chkchkPregPrenatalConsH.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsI()).equals("1"))
               {
                   chkchkPregPrenatalConsI.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsI()).equals("2"))
               {
                   chkchkPregPrenatalConsI.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsPlaceA()).equals("1"))
               {
                   chkchkPregPrenatalConsPlaceA.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsPlaceA()).equals("2"))
               {
                   chkchkPregPrenatalConsPlaceA.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsPlaceB()).equals("1"))
               {
                   chkchkPregPrenatalConsPlaceB.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsPlaceB()).equals("2"))
               {
                   chkchkPregPrenatalConsPlaceB.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsPlaceC()).equals("1"))
               {
                   chkchkPregPrenatalConsPlaceC.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsPlaceC()).equals("2"))
               {
                   chkchkPregPrenatalConsPlaceC.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsPlaceD()).equals("1"))
               {
                   chkchkPregPrenatalConsPlaceD.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsPlaceD()).equals("2"))
               {
                   chkchkPregPrenatalConsPlaceD.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsPlaceE()).equals("1"))
               {
                   chkchkPregPrenatalConsPlaceE.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsPlaceE()).equals("2"))
               {
                   chkchkPregPrenatalConsPlaceE.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsPlaceF()).equals("1"))
               {
                   chkchkPregPrenatalConsPlaceF.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsPlaceF()).equals("2"))
               {
                   chkchkPregPrenatalConsPlaceF.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsPlaceG()).equals("1"))
               {
                   chkchkPregPrenatalConsPlaceG.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsPlaceG()).equals("2"))
               {
                   chkchkPregPrenatalConsPlaceG.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsPlaceH()).equals("1"))
               {
                   chkchkPregPrenatalConsPlaceH.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsPlaceH()).equals("2"))
               {
                   chkchkPregPrenatalConsPlaceH.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsPlaceI()).equals("1"))
               {
                   chkchkPregPrenatalConsPlaceI.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsPlaceI()).equals("2"))
               {
                   chkchkPregPrenatalConsPlaceI.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsPlaceJ()).equals("1"))
               {
                   chkchkPregPrenatalConsPlaceJ.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsPlaceJ()).equals("2"))
               {
                   chkchkPregPrenatalConsPlaceJ.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsPlaceX()).equals("1"))
               {
                   chkchkPregPrenatalConsPlaceX.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsPlaceX()).equals("2"))
               {
                   chkchkPregPrenatalConsPlaceX.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsPlaceY()).equals("1"))
               {
                   chkchkPregPrenatalConsPlaceY.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsPlaceY()).equals("2"))
               {
                   chkchkPregPrenatalConsPlaceY.setChecked(false);
               }
               if(String.valueOf(item.getchkPregPrenatalConsPlaceZ()).equals("1"))
               {
                   chkchkPregPrenatalConsPlaceZ.setChecked(true);
               }
               else if(String.valueOf(item.getchkPregPrenatalConsPlaceZ()).equals("2"))
               {
                   chkchkPregPrenatalConsPlaceZ.setChecked(false);
               }
               txtPregPrenatalConsPlaceOth.setText(item.getPregPrenatalConsPlaceOth());
               txtPregRank.setText(item.getPregRank());
               String[] d_rdogrpPregEnrolled = new String[] {"0","1","8","9"};
               for (int i = 0; i < d_rdogrpPregEnrolled.length; i++)
               {
                   if (String.valueOf(item.getPregEnrolled()).equals(String.valueOf(d_rdogrpPregEnrolled[i])))
                   {
                       rb = (RadioButton)rdogrpPregEnrolled.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
               String[] d_rdogrpPregPSIDKnown = new String[] {"0","1","8","9"};
               for (int i = 0; i < d_rdogrpPregPSIDKnown.length; i++)
               {
                   if (String.valueOf(item.getPregPSIDKnown()).equals(String.valueOf(d_rdogrpPregPSIDKnown[i])))
                   {
                       rb = (RadioButton)rdogrpPregPSIDKnown.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
               txtPregPSID.setText(String.valueOf(item.getPregPSID()));
               String[] d_rdogrpPregWishEnroll = new String[] {"0","1","8","9"};
               for (int i = 0; i < d_rdogrpPregWishEnroll.length; i++)
               {
                   if (String.valueOf(item.getPregWishEnroll()).equals(String.valueOf(d_rdogrpPregWishEnroll[i])))
                   {
                       rb = (RadioButton)rdogrpPregWishEnroll.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
             dtpPregVdate.setText(item.getPregVdate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getPregVdate()));
             txtRnd.setText(item.getRnd());
             txtPregNote.setText(item.getPregNote());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Pregnancy.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpLMPDt);
      if (VariableID.equals("btnLMPDt"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpLMPDt);
      }
      else if (VariableID.equals("btnPregVdate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpPregVdate);
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