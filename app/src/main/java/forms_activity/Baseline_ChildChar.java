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
import android.widget.CompoundButton;
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
import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

import forms_datamodel.ChildChar_DataModel;
import Utility.*;
import Common.*;

public class Baseline_ChildChar extends AppCompatActivity {
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
    LinearLayout secCID;
    View lineCID;
    TextView VlblCID;
    EditText txtCID;
    LinearLayout seclbl1;
    View linelbl1;
    LinearLayout secRespID;
    View lineRespID;
    TextView VlblRespID;
    EditText txtRespID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secChRthHead;
    View lineChRthHead;
    TextView VlblChRthHead;
    Spinner spnChRthHead;
    LinearLayout secRthHeadOth;
    View lineRthHeadOth;
    TextView VlblRthHeadOth;
    AutoCompleteTextView txtRthHeadOth;
    LinearLayout secRthCaregiver;
    View lineRthCaregiver;
    TextView VlblRthCaregiver;
    Spinner spnRthCaregiver;
    LinearLayout secRthCaregiverOth;
    View lineRthCaregiverOth;
    TextView VlblRthCaregiverOth;
    AutoCompleteTextView txtRthCaregiverOth;
    LinearLayout secChSize;
    View lineChSize;
    TextView VlblChSize;
    RadioGroup rdogrpChSize;
    RadioButton rdoChSize1;
    RadioButton rdoChSize2;
    RadioButton rdoChSize3;
    RadioButton rdoChSize4;
    RadioButton rdoChSize5;
    RadioButton rdoChSize6;
    RadioButton rdoChSize7;
    LinearLayout secChStatus;
    View lineChStatus;
    TextView VlblChStatus;
    RadioGroup rdogrpChStatus;
    RadioButton rdoChStatus1;
    RadioButton rdoChStatus2;
    RadioButton rdoChStatus3;
    RadioButton rdoChStatus4;
    LinearLayout secChDiarrhea;
    View lineChDiarrhea;
    TextView VlblChDiarrhea;
    RadioGroup rdogrpChDiarrhea;
    RadioButton rdoChDiarrhea1;
    RadioButton rdoChDiarrhea2;
    RadioButton rdoChDiarrhea3;
    RadioButton rdoChDiarrhea4;
    LinearLayout secSeekCareDiarrhea;
    View lineSeekCareDiarrhea;
    TextView VlblSeekCareDiarrhea;
    RadioGroup rdogrpSeekCareDiarrhea;
    RadioButton rdoSeekCareDiarrhea1;
    RadioButton rdoSeekCareDiarrhea2;
    RadioButton rdoSeekCareDiarrhea3;
    RadioButton rdoSeekCareDiarrhea4;
//    LinearLayout secDiarrheaCareLoc;
//    View lineDiarrheaCareLoc;
//    TextView VlblDiarrheaCareLoc;
//    Spinner spnDiarrheaCareLoc;
//    LinearLayout secDiarrheaCareLocOth;
//    View lineDiarrheaCareLocOth;
//    TextView VlblDiarrheaCareLocOth;
//    AutoCompleteTextView txtDiarrheaCareLocOth;

    LinearLayout seclbl01;
    View linelbl01;
    LinearLayout secGovHos;
    View lineGovHos;
    TextView VlblGovHos;
    CheckBox chkGovHos;
    LinearLayout secGovHelCenter;
    View lineGovHelCenter;
    TextView VlblGovHelCenter;
    CheckBox chkGovHelCenter;
    LinearLayout secGovHelPost;
    View lineGovHelPost;
    TextView VlblGovHelPost;
    CheckBox chkGovHelPost;
    LinearLayout secGovMobClinic;
    View lineGovMobClinic;
    TextView VlblGovMobClinic;
    CheckBox chkGovMobClinic;
    LinearLayout secPrvtHos;
    View linePrvtHos;
    TextView VlblPrvtHos;
    CheckBox chkPrvtHos;
    LinearLayout secPharma;
    View linePharma;
    TextView VlblPharma;
    CheckBox chkPharma;
    LinearLayout secPrvtDoctor;
    View linePrvtDoctor;
    TextView VlblPrvtDoctor;
    CheckBox chkPrvtDoctor;
    LinearLayout secPrvtMobClinic;
    View linePrvtMobClinic;
    TextView VlblPrvtMobClinic;
    CheckBox chkPrvtMobClinic;
    LinearLayout secTrdiPract;
    View lineTrdiPract;
    TextView VlblTrdiPract;
    CheckBox chkTrdiPract;
    LinearLayout secDrugSeller;
    View lineDrugSeller;
    TextView VlblDrugSeller;
    CheckBox chkDrugSeller;
    LinearLayout secOther;
    View lineOther;
    TextView VlblOther;
    CheckBox chkOther;
    LinearLayout secOtherSp;
    View lineOtherSp;
    TextView VlblOtherSp;
    EditText txtOtherSp;
    LinearLayout secFaciDk;
    View lineFaciDk;
    TextView VlblFaciDk;
    CheckBox chkFaciDk;
    LinearLayout secRefused;
    View lineRefused;
    TextView VlblRefused;
    CheckBox chkRefused;
    LinearLayout secCough;
    View lineCough;
    TextView VlblCough;
    RadioGroup rdogrpCough;
    RadioButton rdoCough1;
    RadioButton rdoCough2;
    RadioButton rdoCough3;
    RadioButton rdoCough4;
    LinearLayout secBreath;
    View lineBreath;
    TextView VlblBreath;
    RadioGroup rdogrpBreath;
    RadioButton rdoBreath1;
    RadioButton rdoBreath2;
    RadioButton rdoBreath3;
    RadioButton rdoBreath4;
    LinearLayout secBreathDiffC;
    View lineBreathDiffC;
    TextView VlblBreathDiffC;
    RadioGroup rdogrpBreathDiffC;
    RadioButton rdoBreathDiffC1;
    RadioButton rdoBreathDiffC2;
    RadioButton rdoBreathDiffC3;
    RadioButton rdoBreathDiffC4;
    RadioButton rdoBreathDiffC5;
    LinearLayout secSeekCareCough;
    View lineSeekCareCough;
    TextView VlblSeekCareCough;
    RadioGroup rdogrpSeekCareCough;
    RadioButton rdoSeekCareCough1;
    RadioButton rdoSeekCareCough2;
    RadioButton rdoSeekCareCough3;
    RadioButton rdoSeekCareCough4;
    LinearLayout secCoughCareLoc;
    View lineCoughCareLoc;
    TextView VlblCoughCareLoc;
    Spinner spnCoughCareLoc;
    LinearLayout secCoughCareLocOth;
    View lineCoughCareLocOth;
    TextView VlblCoughCareLocOth;
    AutoCompleteTextView txtCoughCareLocOth;
    LinearLayout secFeverPst2W;
    View lineFeverPst2W;
    TextView VlblFeverPst2W;
    RadioGroup rdogrpFeverPst2W;
    RadioButton rdoFeverPst2W1;
    RadioButton rdoFeverPst2W2;
    RadioButton rdoFeverPst2W3;
    RadioButton rdoFeverPst2W4;
    LinearLayout secSeekCareFever;
    View lineSeekCareFever;
    TextView VlblSeekCareFever;
    RadioGroup rdogrpSeekCareFever;
    RadioButton rdoSeekCareFever1;
    RadioButton rdoSeekCareFever2;
    RadioButton rdoSeekCareFever3;
    RadioButton rdoSeekCareFever4;
    LinearLayout secFeverCareLoc;
    View lineFeverCareLoc;
    TextView VlblFeverCareLoc;
    Spinner spnFeverCareLoc;
    LinearLayout secFeverCareLocOth;
    View lineFeverCareLocOth;
    TextView VlblFeverCareLocOth;
    AutoCompleteTextView txtFeverCareLocOth;
    LinearLayout secWgtLost;
    View lineWgtLost;
    TextView VlblWgtLost;
    RadioGroup rdogrpWgtLost;
    RadioButton rdoWgtLost1;
    RadioButton rdoWgtLost2;
    RadioButton rdoWgtLost3;
    RadioButton rdoWgtLost4;
    LinearLayout secWgtGain;
    View lineWgtGain;
    TextView VlblWgtGain;
    RadioGroup rdogrpWgtGain;
    RadioButton rdoWgtGain1;
    RadioButton rdoWgtGain2;
    RadioButton rdoWgtGain3;
    RadioButton rdoWgtGain4;
    LinearLayout secChLessFeed;
    View lineChLessFeed;
    TextView VlblChLessFeed;
    RadioGroup rdogrpChLessFeed;
    RadioButton rdoChLessFeed1;
    RadioButton rdoChLessFeed2;
    RadioButton rdoChLessFeed3;
    RadioButton rdoChLessFeed4;
    LinearLayout secUnderWeight;
    View lineUnderWeight;
    TextView VlblUnderWeight;
    RadioGroup rdogrpUnderWeight;
    RadioButton rdoUnderWeight1;
    RadioButton rdoUnderWeight2;
    RadioButton rdoUnderWeight3;
    RadioButton rdoUnderWeight4;
    LinearLayout secOverWeight;
    View lineOverWeight;
    TextView VlblOverWeight;
    RadioGroup rdogrpOverWeight;
    RadioButton rdoOverWeight1;
    RadioButton rdoOverWeight2;
    RadioButton rdoOverWeight3;
    RadioButton rdoOverWeight4;
    LinearLayout secEvrVacc;
    View lineEvrVacc;
    TextView VlblEvrVacc;
    RadioGroup rdogrpEvrVacc;
    RadioButton rdoEvrVacc1;
    RadioButton rdoEvrVacc2;
    RadioButton rdoEvrVacc3;
    RadioButton rdoEvrVacc4;
    LinearLayout secVaccCard;
    View lineVaccCard;
    TextView VlblVaccCard;
    RadioGroup rdogrpVaccCard;
    RadioButton rdoVaccCard1;
    RadioButton rdoVaccCard2;
    RadioButton rdoVaccCard3;
    RadioButton rdoVaccCard4;
    LinearLayout secVaccCardPhoto;
    View lineVaccCardPhoto;
    TextView VlblVaccCardPhoto;
    RadioGroup rdogrpVaccCardPhoto;
    RadioButton rdoVaccCardPhoto1;
    RadioButton rdoVaccCardPhoto2;
    RadioButton rdoVaccCardPhoto3;
    RadioButton rdoVaccCardPhoto4;
    LinearLayout secEvrVaccCard;
    View lineEvrVaccCard;
    TextView VlblEvrVaccCard;
    RadioGroup rdogrpEvrVaccCard;
    RadioButton rdoEvrVaccCard1;
    RadioButton rdoEvrVaccCard2;
    RadioButton rdoEvrVaccCard3;
    RadioButton rdoEvrVaccCard4;

    static int MODULEID   = 0;
    static int LANGUAGEID = 0;
    static String TableName;

    static String STARTTIME = "";
    static String DEVICEID  = "";
    static String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
    static String MEMID = "";
    static String HHID = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            setContentView(R.layout.childchar);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            MEMID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            ((TextView)findViewById(R.id.tv_member_name)).setText(IDbundle.getString("MemName","[Name]"));

            TableName = "ChildChar";
            MODULEID = 19;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView)findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Baseline_ChildChar.this);
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
            Connection.LocalizeLanguage(Baseline_ChildChar.this, MODULEID, LANGUAGEID);





            //Hide all skip variables
            secCID.setVisibility(View.GONE);
            secRespID.setVisibility(View.GONE);
            secMemID.setVisibility(View.GONE);
            secRthHeadOth.setVisibility(View.GONE);
            lineRthHeadOth.setVisibility(View.GONE);
            secRthCaregiverOth.setVisibility(View.GONE);
            lineRthCaregiverOth.setVisibility(View.GONE);
            secChDiarrhea.setVisibility(View.GONE);
            lineChDiarrhea.setVisibility(View.GONE);
            secSeekCareDiarrhea.setVisibility(View.GONE);
            lineSeekCareDiarrhea.setVisibility(View.GONE);
//            secDiarrheaCareLoc.setVisibility(View.GONE);
//            lineDiarrheaCareLoc.setVisibility(View.GONE);
//            secDiarrheaCareLocOth.setVisibility(View.GONE);
//            lineDiarrheaCareLocOth.setVisibility(View.GONE);
            seclbl01.setVisibility(View.GONE);
            linelbl01.setVisibility(View.GONE);
            secGovHos.setVisibility(View.GONE);
            lineGovHos.setVisibility(View.GONE);
            secGovHelCenter.setVisibility(View.GONE);
            lineGovHelCenter.setVisibility(View.GONE);
            secGovHelPost.setVisibility(View.GONE);
            lineGovHelPost.setVisibility(View.GONE);
            secGovMobClinic.setVisibility(View.GONE);
            lineGovMobClinic.setVisibility(View.GONE);
            secPrvtHos.setVisibility(View.GONE);
            linePrvtHos.setVisibility(View.GONE);
            secPharma.setVisibility(View.GONE);
            linePharma.setVisibility(View.GONE);
            secPrvtDoctor.setVisibility(View.GONE);
            linePrvtDoctor.setVisibility(View.GONE);
            secPrvtMobClinic.setVisibility(View.GONE);
            linePrvtMobClinic.setVisibility(View.GONE);
            secTrdiPract.setVisibility(View.GONE);
            lineTrdiPract.setVisibility(View.GONE);
            secDrugSeller.setVisibility(View.GONE);
            lineDrugSeller.setVisibility(View.GONE);
            secOther.setVisibility(View.GONE);
            lineOther.setVisibility(View.GONE);
            secOtherSp.setVisibility(View.GONE);
            lineOtherSp.setVisibility(View.GONE);
            secFaciDk.setVisibility(View.GONE);
            lineFaciDk.setVisibility(View.GONE);
            secRefused.setVisibility(View.GONE);
            lineRefused.setVisibility(View.GONE);
            secCough.setVisibility(View.GONE);
            lineCough.setVisibility(View.GONE);
            secBreath.setVisibility(View.GONE);
            lineBreath.setVisibility(View.GONE);
            secBreathDiffC.setVisibility(View.GONE);
            lineBreathDiffC.setVisibility(View.GONE);
            secSeekCareCough.setVisibility(View.GONE);
            lineSeekCareCough.setVisibility(View.GONE);
            secCoughCareLoc.setVisibility(View.GONE);
            lineCoughCareLoc.setVisibility(View.GONE);
            secCoughCareLocOth.setVisibility(View.GONE);
            lineCoughCareLocOth.setVisibility(View.GONE);
            secFeverPst2W.setVisibility(View.GONE);
            lineFeverPst2W.setVisibility(View.GONE);
            secSeekCareFever.setVisibility(View.GONE);
            lineSeekCareFever.setVisibility(View.GONE);
            secFeverCareLoc.setVisibility(View.GONE);
            lineFeverCareLoc.setVisibility(View.GONE);
            secFeverCareLocOth.setVisibility(View.GONE);
            lineFeverCareLocOth.setVisibility(View.GONE);
            secChDiarrhea.setVisibility(View.GONE);
            lineChDiarrhea.setVisibility(View.GONE);
            secSeekCareDiarrhea.setVisibility(View.GONE);
            lineSeekCareDiarrhea.setVisibility(View.GONE);
//            secDiarrheaCareLoc.setVisibility(View.GONE);
//            lineDiarrheaCareLoc.setVisibility(View.GONE);
//            secDiarrheaCareLocOth.setVisibility(View.GONE);
//            lineDiarrheaCareLocOth.setVisibility(View.GONE);
            secCough.setVisibility(View.GONE);
            lineCough.setVisibility(View.GONE);
            secBreath.setVisibility(View.GONE);
            lineBreath.setVisibility(View.GONE);
            secBreathDiffC.setVisibility(View.GONE);
            lineBreathDiffC.setVisibility(View.GONE);
            secSeekCareCough.setVisibility(View.GONE);
            lineSeekCareCough.setVisibility(View.GONE);
            secCoughCareLoc.setVisibility(View.GONE);
            lineCoughCareLoc.setVisibility(View.GONE);
            secCoughCareLocOth.setVisibility(View.GONE);
            lineCoughCareLocOth.setVisibility(View.GONE);
            secFeverPst2W.setVisibility(View.GONE);
            lineFeverPst2W.setVisibility(View.GONE);
            secSeekCareFever.setVisibility(View.GONE);
            lineSeekCareFever.setVisibility(View.GONE);
            secFeverCareLoc.setVisibility(View.GONE);
            lineFeverCareLoc.setVisibility(View.GONE);
            secFeverCareLocOth.setVisibility(View.GONE);
            lineFeverCareLocOth.setVisibility(View.GONE);
            secChDiarrhea.setVisibility(View.GONE);
            lineChDiarrhea.setVisibility(View.GONE);
            secSeekCareDiarrhea.setVisibility(View.GONE);
            lineSeekCareDiarrhea.setVisibility(View.GONE);
//            secDiarrheaCareLoc.setVisibility(View.GONE);
//            lineDiarrheaCareLoc.setVisibility(View.GONE);
//            secDiarrheaCareLocOth.setVisibility(View.GONE);
//            lineDiarrheaCareLocOth.setVisibility(View.GONE);
            secCough.setVisibility(View.GONE);
            lineCough.setVisibility(View.GONE);
            secBreath.setVisibility(View.GONE);
            lineBreath.setVisibility(View.GONE);
            secBreathDiffC.setVisibility(View.GONE);
            lineBreathDiffC.setVisibility(View.GONE);
            secSeekCareCough.setVisibility(View.GONE);
            lineSeekCareCough.setVisibility(View.GONE);
            secCoughCareLoc.setVisibility(View.GONE);
            lineCoughCareLoc.setVisibility(View.GONE);
            secCoughCareLocOth.setVisibility(View.GONE);
            lineCoughCareLocOth.setVisibility(View.GONE);
            secFeverPst2W.setVisibility(View.GONE);
            lineFeverPst2W.setVisibility(View.GONE);
            secSeekCareFever.setVisibility(View.GONE);
            lineSeekCareFever.setVisibility(View.GONE);
            secFeverCareLoc.setVisibility(View.GONE);
            lineFeverCareLoc.setVisibility(View.GONE);
            secFeverCareLocOth.setVisibility(View.GONE);
            lineFeverCareLocOth.setVisibility(View.GONE);
            secSeekCareDiarrhea.setVisibility(View.GONE);
            lineSeekCareDiarrhea.setVisibility(View.GONE);
            secSeekCareDiarrhea.setVisibility(View.GONE);
            lineSeekCareDiarrhea.setVisibility(View.GONE);
            secSeekCareDiarrhea.setVisibility(View.GONE);
            lineSeekCareDiarrhea.setVisibility(View.GONE);
            secBreath.setVisibility(View.GONE);
            lineBreath.setVisibility(View.GONE);
            secBreathDiffC.setVisibility(View.GONE);
            lineBreathDiffC.setVisibility(View.GONE);
            secSeekCareCough.setVisibility(View.GONE);
            lineSeekCareCough.setVisibility(View.GONE);
            secCoughCareLoc.setVisibility(View.GONE);
            lineCoughCareLoc.setVisibility(View.GONE);
            secCoughCareLocOth.setVisibility(View.GONE);
            lineCoughCareLocOth.setVisibility(View.GONE);
            secBreath.setVisibility(View.GONE);
            lineBreath.setVisibility(View.GONE);
            secBreathDiffC.setVisibility(View.GONE);
            lineBreathDiffC.setVisibility(View.GONE);
            secSeekCareCough.setVisibility(View.GONE);
            lineSeekCareCough.setVisibility(View.GONE);
            secCoughCareLoc.setVisibility(View.GONE);
            lineCoughCareLoc.setVisibility(View.GONE);
            secCoughCareLocOth.setVisibility(View.GONE);
            lineCoughCareLocOth.setVisibility(View.GONE);
            secBreath.setVisibility(View.GONE);
            lineBreath.setVisibility(View.GONE);
            secBreathDiffC.setVisibility(View.GONE);
            lineBreathDiffC.setVisibility(View.GONE);
            secSeekCareCough.setVisibility(View.GONE);
            lineSeekCareCough.setVisibility(View.GONE);
            secCoughCareLoc.setVisibility(View.GONE);
            lineCoughCareLoc.setVisibility(View.GONE);
            secCoughCareLocOth.setVisibility(View.GONE);
            lineCoughCareLocOth.setVisibility(View.GONE);
            secCoughCareLoc.setVisibility(View.GONE);
            lineCoughCareLoc.setVisibility(View.GONE);
            secCoughCareLocOth.setVisibility(View.GONE);
            lineCoughCareLocOth.setVisibility(View.GONE);
            secCoughCareLoc.setVisibility(View.GONE);
            lineCoughCareLoc.setVisibility(View.GONE);
            secCoughCareLocOth.setVisibility(View.GONE);
            lineCoughCareLocOth.setVisibility(View.GONE);
            secCoughCareLoc.setVisibility(View.GONE);
            lineCoughCareLoc.setVisibility(View.GONE);
            secCoughCareLocOth.setVisibility(View.GONE);
            lineCoughCareLocOth.setVisibility(View.GONE);
            secCoughCareLocOth.setVisibility(View.GONE);
            lineCoughCareLocOth.setVisibility(View.GONE);
            secSeekCareFever.setVisibility(View.GONE);
            lineSeekCareFever.setVisibility(View.GONE);
            secFeverCareLoc.setVisibility(View.GONE);
            lineFeverCareLoc.setVisibility(View.GONE);
            secFeverCareLocOth.setVisibility(View.GONE);
            lineFeverCareLocOth.setVisibility(View.GONE);
            secSeekCareFever.setVisibility(View.GONE);
            lineSeekCareFever.setVisibility(View.GONE);
            secFeverCareLoc.setVisibility(View.GONE);
            lineFeverCareLoc.setVisibility(View.GONE);
            secFeverCareLocOth.setVisibility(View.GONE);
            lineFeverCareLocOth.setVisibility(View.GONE);
            secSeekCareFever.setVisibility(View.GONE);
            lineSeekCareFever.setVisibility(View.GONE);
            secFeverCareLoc.setVisibility(View.GONE);
            lineFeverCareLoc.setVisibility(View.GONE);
            secFeverCareLocOth.setVisibility(View.GONE);
            lineFeverCareLocOth.setVisibility(View.GONE);
            secFeverCareLoc.setVisibility(View.GONE);
            lineFeverCareLoc.setVisibility(View.GONE);
            secFeverCareLocOth.setVisibility(View.GONE);
            lineFeverCareLocOth.setVisibility(View.GONE);
            secFeverCareLoc.setVisibility(View.GONE);
            lineFeverCareLoc.setVisibility(View.GONE);
            secFeverCareLocOth.setVisibility(View.GONE);
            lineFeverCareLocOth.setVisibility(View.GONE);
            secFeverCareLoc.setVisibility(View.GONE);
            lineFeverCareLoc.setVisibility(View.GONE);
            secFeverCareLocOth.setVisibility(View.GONE);
            lineFeverCareLocOth.setVisibility(View.GONE);
            secFeverCareLocOth.setVisibility(View.GONE);
            lineFeverCareLocOth.setVisibility(View.GONE);
            secOverWeight.setVisibility(View.GONE);
            lineOverWeight.setVisibility(View.GONE);
            secEvrVacc.setVisibility(View.GONE);
            lineEvrVacc.setVisibility(View.GONE);
            secVaccCard.setVisibility(View.GONE);
            lineVaccCard.setVisibility(View.GONE);
            secVaccCardPhoto.setVisibility(View.GONE);
            lineVaccCardPhoto.setVisibility(View.GONE);
            secEvrVaccCard.setVisibility(View.GONE);
            lineEvrVaccCard.setVisibility(View.GONE);
            secVaccCard.setVisibility(View.GONE);
            lineVaccCard.setVisibility(View.GONE);
            secVaccCardPhoto.setVisibility(View.GONE);
            lineVaccCardPhoto.setVisibility(View.GONE);
            secEvrVaccCard.setVisibility(View.GONE);
            lineEvrVaccCard.setVisibility(View.GONE);
            secVaccCard.setVisibility(View.GONE);
            lineVaccCard.setVisibility(View.GONE);
            secVaccCardPhoto.setVisibility(View.GONE);
            lineVaccCardPhoto.setVisibility(View.GONE);
            secEvrVaccCard.setVisibility(View.GONE);
            lineEvrVaccCard.setVisibility(View.GONE);


            DataSearch(MEMID);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }});

            Global.RoleManagement(cmdSave, MySharedPreferences.getValue(this, "userrole"));
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Baseline_ChildChar.this, e.getMessage());
            return;
        }
    }

    private void Initialization()
    {
        try
        {
            secCID=(LinearLayout)findViewById(R.id.secCID);
            lineCID=(View)findViewById(R.id.lineCID);
            VlblCID=(TextView) findViewById(R.id.VlblCID);
            txtCID=(EditText) findViewById(R.id.txtCID);
            txtCID.setText(Global.Get_UUID(DEVICEID));
            txtCID.setEnabled(false);
            seclbl1=(LinearLayout)findViewById(R.id.seclbl1);
            linelbl1=(View)findViewById(R.id.linelbl1);
            secRespID=(LinearLayout)findViewById(R.id.secRespID);
            lineRespID=(View)findViewById(R.id.lineRespID);
            VlblRespID=(TextView) findViewById(R.id.VlblRespID);
            txtRespID=(EditText) findViewById(R.id.txtRespID);
            secMemID=(LinearLayout)findViewById(R.id.secMemID);
            lineMemID=(View)findViewById(R.id.lineMemID);
            VlblMemID=(TextView) findViewById(R.id.VlblMemID);
            txtMemID=(EditText) findViewById(R.id.txtMemID);
            txtMemID.setText(MEMID);
            secChRthHead=(LinearLayout)findViewById(R.id.secChRthHead);
            lineChRthHead=(View)findViewById(R.id.lineChRthHead);
            VlblChRthHead=(TextView) findViewById(R.id.VlblChRthHead);
            spnChRthHead=(Spinner) findViewById(R.id.spnChRthHead);
            List<String> listChRthHead = new ArrayList<String>();

            listChRthHead.add("");
            listChRthHead.add("06-Son");
            listChRthHead.add("07-Daughter");
            listChRthHead.add("08-Adopted son");
            listChRthHead.add("09-Adopted daughter");
            listChRthHead.add("10-Stepson");
            listChRthHead.add("11-Stepdaughter");
            listChRthHead.add("12-Nephew");
            listChRthHead.add("13-Niece");
            listChRthHead.add("14-Grandson");
            listChRthHead.add("15-Granddaughter");
            listChRthHead.add("20-Brother");
            listChRthHead.add("21-Sister");
            listChRthHead.add("22-Stepbrother");
            listChRthHead.add("23-Stepsister");
            listChRthHead.add("24-Cousin");
            listChRthHead.add("32-Unrelated");
            listChRthHead.add("97-Other");
            listChRthHead.add("98-Don't know");
            listChRthHead.add("99-Refused to respond");
            spnChRthHead.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listChRthHead)));

            spnChRthHead.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnChRthHead.getSelectedItem().toString().length() != 0)
                    {
                        spnData = Connection.SelectedSpinnerValue(spnChRthHead.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("97")) {

                        secRthHeadOth.setVisibility(View.VISIBLE);
                        lineRthHeadOth.setVisibility(View.VISIBLE);
                    } else {
                        secRthHeadOth.setVisibility(View.GONE);
                        lineRthHeadOth.setVisibility(View.GONE);
                        txtRthHeadOth.setText("");
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secRthHeadOth=(LinearLayout)findViewById(R.id.secRthHeadOth);
            lineRthHeadOth=(View)findViewById(R.id.lineRthHeadOth);
            VlblRthHeadOth=(TextView) findViewById(R.id.VlblRthHeadOth);
            txtRthHeadOth=(AutoCompleteTextView) findViewById(R.id.txtRthHeadOth);
            txtRthHeadOth.setAdapter(C.getArrayAdapter("Select distinct RthHeadOth from "+ TableName +" order by RthHeadOth"));

            txtRthHeadOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

                }
            });
            txtRthHeadOth.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (txtRthHeadOth.getRight() - txtRthHeadOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            ((EditText)v).setText("");
                            return true;
                        }
                    }
                    return false;
                }
            });
            secRthCaregiver=(LinearLayout)findViewById(R.id.secRthCaregiver);
            lineRthCaregiver=(View)findViewById(R.id.lineRthCaregiver);
            VlblRthCaregiver=(TextView) findViewById(R.id.VlblRthCaregiver);
            spnRthCaregiver=(Spinner) findViewById(R.id.spnRthCaregiver);
            List<String> listRthCaregiver = new ArrayList<String>();

            listRthCaregiver.add("");
            listRthCaregiver.add("16-Father");
            listRthCaregiver.add("17-Mother");
            listRthCaregiver.add("18-Stepfather");
            listRthCaregiver.add("19-Stepmother");
            listRthCaregiver.add("20-Brother");
            listRthCaregiver.add("21-Sister");
            listRthCaregiver.add("22-Stepbrother");
            listRthCaregiver.add("23-Stepsister");
            listRthCaregiver.add("24-Cousin");
            listRthCaregiver.add("25-Uncle");
            listRthCaregiver.add("26-Aunt");
            listRthCaregiver.add("27-Grandfather");
            listRthCaregiver.add("28-Grandmother");
            listRthCaregiver.add("32-Unrelated");
            listRthCaregiver.add("97-Other");
            listRthCaregiver.add("98-Don't know");
            listRthCaregiver.add("99-Refused to respond");
            spnRthCaregiver.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listRthCaregiver)));

            spnRthCaregiver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnRthCaregiver.getSelectedItem().toString().length() != 0)
                    {
                        spnData = Connection.SelectedSpinnerValue(spnRthCaregiver.getSelectedItem().toString(), "-");
                    }
                    if(spnData.equalsIgnoreCase("97"))
                    {
                        secRthCaregiverOth.setVisibility(View.VISIBLE);
                        lineRthCaregiverOth.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        secRthCaregiverOth.setVisibility(View.GONE);
                        lineRthCaregiverOth.setVisibility(View.GONE);
                        txtRthCaregiverOth.setText("");
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secRthCaregiverOth=(LinearLayout)findViewById(R.id.secRthCaregiverOth);
            lineRthCaregiverOth=(View)findViewById(R.id.lineRthCaregiverOth);
            VlblRthCaregiverOth=(TextView) findViewById(R.id.VlblRthCaregiverOth);
            txtRthCaregiverOth=(AutoCompleteTextView) findViewById(R.id.txtRthCaregiverOth);
            txtRthCaregiverOth.setAdapter(C.getArrayAdapter("Select distinct RthCaregiverOth from "+ TableName +" order by RthCaregiverOth"));

            txtRthCaregiverOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

                }
            });
            txtRthCaregiverOth.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (txtRthCaregiverOth.getRight() - txtRthCaregiverOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            ((EditText)v).setText("");
                            return true;
                        }
                    }
                    return false;
                }
            });
            secChSize=(LinearLayout)findViewById(R.id.secChSize);
            lineChSize=(View)findViewById(R.id.lineChSize);
            VlblChSize = (TextView) findViewById(R.id.VlblChSize);
            rdogrpChSize = (RadioGroup) findViewById(R.id.rdogrpChSize);
            rdoChSize1 = (RadioButton) findViewById(R.id.rdoChSize1);
            rdoChSize2 = (RadioButton) findViewById(R.id.rdoChSize2);
            rdoChSize3 = (RadioButton) findViewById(R.id.rdoChSize3);
            rdoChSize4 = (RadioButton) findViewById(R.id.rdoChSize4);
            rdoChSize5 = (RadioButton) findViewById(R.id.rdoChSize5);
            rdoChSize6 = (RadioButton) findViewById(R.id.rdoChSize6);
            rdoChSize7 = (RadioButton) findViewById(R.id.rdoChSize7);
            secChStatus=(LinearLayout)findViewById(R.id.secChStatus);
            lineChStatus=(View)findViewById(R.id.lineChStatus);
            VlblChStatus = (TextView) findViewById(R.id.VlblChStatus);
            rdogrpChStatus = (RadioGroup) findViewById(R.id.rdogrpChStatus);
            rdoChStatus1 = (RadioButton) findViewById(R.id.rdoChStatus1);
            rdoChStatus2 = (RadioButton) findViewById(R.id.rdoChStatus2);
            rdoChStatus3 = (RadioButton) findViewById(R.id.rdoChStatus3);
            rdoChStatus4 = (RadioButton) findViewById(R.id.rdoChStatus4);
            rdogrpChStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpChStatus = new String[] {"0","1","8","9"};
                    for (int i = 0; i < rdogrpChStatus.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpChStatus.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpChStatus[i];
                    }

                    if(rbData.equalsIgnoreCase("0") || rbData.equalsIgnoreCase("8")|| rbData.equalsIgnoreCase("9"))
                    {
                        secChDiarrhea.setVisibility(View.GONE);
                        lineChDiarrhea.setVisibility(View.GONE);
                        rdogrpChDiarrhea.clearCheck();
                        secSeekCareDiarrhea.setVisibility(View.GONE);
                        lineSeekCareDiarrhea.setVisibility(View.GONE);
                        rdogrpSeekCareDiarrhea.clearCheck();
                        seclbl01.setVisibility(View.GONE);
                        linelbl01.setVisibility(View.GONE);
                        secGovHos.setVisibility(View.GONE);
                        lineGovHos.setVisibility(View.GONE);
                        chkGovHos.setChecked(false);
                        secGovHelCenter.setVisibility(View.GONE);
                        lineGovHelCenter.setVisibility(View.GONE);
                        chkGovHelCenter.setChecked(false);
                        secGovHelPost.setVisibility(View.GONE);
                        lineGovHelPost.setVisibility(View.GONE);
                        chkGovHelPost.setChecked(false);
                        secGovMobClinic.setVisibility(View.GONE);
                        lineGovMobClinic.setVisibility(View.GONE);
                        chkGovMobClinic.setChecked(false);
                        secPrvtHos.setVisibility(View.GONE);
                        linePrvtHos.setVisibility(View.GONE);
                        chkPrvtHos.setChecked(false);
                        secPharma.setVisibility(View.GONE);
                        linePharma.setVisibility(View.GONE);
                        chkPharma.setChecked(false);
                        secPrvtDoctor.setVisibility(View.GONE);
                        linePrvtDoctor.setVisibility(View.GONE);
                        chkPrvtDoctor.setChecked(false);
                        secPrvtMobClinic.setVisibility(View.GONE);
                        linePrvtMobClinic.setVisibility(View.GONE);
                        chkPrvtMobClinic.setChecked(false);
                        secTrdiPract.setVisibility(View.GONE);
                        lineTrdiPract.setVisibility(View.GONE);
                        chkTrdiPract.setChecked(false);
                        secDrugSeller.setVisibility(View.GONE);
                        lineDrugSeller.setVisibility(View.GONE);
                        chkDrugSeller.setChecked(false);
                        secOther.setVisibility(View.GONE);
                        lineOther.setVisibility(View.GONE);
                        chkOther.setChecked(false);
                        secOtherSp.setVisibility(View.GONE);
                        lineOtherSp.setVisibility(View.GONE);
                        txtOtherSp.setText("");
                        secFaciDk.setVisibility(View.GONE);
                        lineFaciDk.setVisibility(View.GONE);
                        chkFaciDk.setChecked(false);
                        secRefused.setVisibility(View.GONE);
                        lineRefused.setVisibility(View.GONE);
                        chkRefused.setChecked(false);
                        secCough.setVisibility(View.GONE);
                        lineCough.setVisibility(View.GONE);
                        rdogrpCough.clearCheck();
                        secBreath.setVisibility(View.GONE);
                        lineBreath.setVisibility(View.GONE);
                        rdogrpBreath.clearCheck();
                        secBreathDiffC.setVisibility(View.GONE);
                        lineBreathDiffC.setVisibility(View.GONE);
                        rdogrpBreathDiffC.clearCheck();
                        secSeekCareCough.setVisibility(View.GONE);
                        lineSeekCareCough.setVisibility(View.GONE);
                        rdogrpSeekCareCough.clearCheck();
                        secCoughCareLoc.setVisibility(View.GONE);
                        lineCoughCareLoc.setVisibility(View.GONE);
                        spnCoughCareLoc.setSelection(0);
                        secCoughCareLocOth.setVisibility(View.GONE);
                        lineCoughCareLocOth.setVisibility(View.GONE);
                        txtCoughCareLocOth.setText("");
                        secFeverPst2W.setVisibility(View.GONE);
                        lineFeverPst2W.setVisibility(View.GONE);
                        rdogrpFeverPst2W.clearCheck();
                        secSeekCareFever.setVisibility(View.GONE);
                        lineSeekCareFever.setVisibility(View.GONE);
                        rdogrpSeekCareFever.clearCheck();
                        secFeverCareLoc.setVisibility(View.GONE);
                        lineFeverCareLoc.setVisibility(View.GONE);
                        spnFeverCareLoc.setSelection(0);
                        secFeverCareLocOth.setVisibility(View.GONE);
                        lineFeverCareLocOth.setVisibility(View.GONE);
                        txtFeverCareLocOth.setText("");
                    }
                    /*else if(rbData.equalsIgnoreCase("98"))
                    {
                        secRefused.setVisibility(View.GONE);
                        lineRefused.setVisibility(View.GONE);
                        chkRefused.setChecked(false);
                        secChDiarrhea.setVisibility(View.GONE);
                        lineChDiarrhea.setVisibility(View.GONE);
                        rdogrpChDiarrhea.clearCheck();
                        secSeekCareDiarrhea.setVisibility(View.GONE);
                        lineSeekCareDiarrhea.setVisibility(View.GONE);
                        rdogrpSeekCareDiarrhea.clearCheck();
                        seclbl01.setVisibility(View.GONE);
                        linelbl01.setVisibility(View.GONE);
                        secGovHos.setVisibility(View.GONE);
                        lineGovHos.setVisibility(View.GONE);
                        chkGovHos.setChecked(false);
                        secGovHelCenter.setVisibility(View.GONE);
                        lineGovHelCenter.setVisibility(View.GONE);
                        chkGovHelCenter.setChecked(false);
                        secGovHelPost.setVisibility(View.GONE);
                        lineGovHelPost.setVisibility(View.GONE);
                        chkGovHelPost.setChecked(false);
                        secGovMobClinic.setVisibility(View.GONE);
                        lineGovMobClinic.setVisibility(View.GONE);
                        chkGovMobClinic.setChecked(false);
                        secPrvtHos.setVisibility(View.GONE);
                        linePrvtHos.setVisibility(View.GONE);
                        chkPrvtHos.setChecked(false);
                        secPharma.setVisibility(View.GONE);
                        linePharma.setVisibility(View.GONE);
                        chkPharma.setChecked(false);
                        secPrvtDoctor.setVisibility(View.GONE);
                        linePrvtDoctor.setVisibility(View.GONE);
                        chkPrvtDoctor.setChecked(false);
                        secPrvtMobClinic.setVisibility(View.GONE);
                        linePrvtMobClinic.setVisibility(View.GONE);
                        chkPrvtMobClinic.setChecked(false);
                        secTrdiPract.setVisibility(View.GONE);
                        lineTrdiPract.setVisibility(View.GONE);
                        chkTrdiPract.setChecked(false);
                        secDrugSeller.setVisibility(View.GONE);
                        lineDrugSeller.setVisibility(View.GONE);
                        chkDrugSeller.setChecked(false);
                        secOther.setVisibility(View.GONE);
                        lineOther.setVisibility(View.GONE);
                        chkOther.setChecked(false);
                        secOtherSp.setVisibility(View.GONE);
                        lineOtherSp.setVisibility(View.GONE);
                        txtOtherSp.setText("");
                        secFaciDk.setVisibility(View.GONE);
                        lineFaciDk.setVisibility(View.GONE);
                        chkFaciDk.setChecked(false);
                        secCough.setVisibility(View.GONE);
                        lineCough.setVisibility(View.GONE);
                        rdogrpCough.clearCheck();
                        secBreath.setVisibility(View.GONE);
                        lineBreath.setVisibility(View.GONE);
                        rdogrpBreath.clearCheck();
                        secBreathDiffC.setVisibility(View.GONE);
                        lineBreathDiffC.setVisibility(View.GONE);
                        rdogrpBreathDiffC.clearCheck();
                        secSeekCareCough.setVisibility(View.GONE);
                        lineSeekCareCough.setVisibility(View.GONE);
                        rdogrpSeekCareCough.clearCheck();
                        secCoughCareLoc.setVisibility(View.GONE);
                        lineCoughCareLoc.setVisibility(View.GONE);
                        spnCoughCareLoc.setSelection(0);
                        secCoughCareLocOth.setVisibility(View.GONE);
                        lineCoughCareLocOth.setVisibility(View.GONE);
                        txtCoughCareLocOth.setText("");
                        secFeverPst2W.setVisibility(View.GONE);
                        lineFeverPst2W.setVisibility(View.GONE);
                        rdogrpFeverPst2W.clearCheck();
                        secSeekCareFever.setVisibility(View.GONE);
                        lineSeekCareFever.setVisibility(View.GONE);
                        rdogrpSeekCareFever.clearCheck();
                        secFeverCareLoc.setVisibility(View.GONE);
                        lineFeverCareLoc.setVisibility(View.GONE);
                        spnFeverCareLoc.setSelection(0);
                        secFeverCareLocOth.setVisibility(View.GONE);
                        lineFeverCareLocOth.setVisibility(View.GONE);
                        txtFeverCareLocOth.setText("");
                    }
                    else if(rbData.equalsIgnoreCase("99"))
                    {
                        secRefused.setVisibility(View.GONE);
                        lineRefused.setVisibility(View.GONE);
                        chkRefused.setChecked(false);
                        secChDiarrhea.setVisibility(View.GONE);
                        lineChDiarrhea.setVisibility(View.GONE);
                        rdogrpChDiarrhea.clearCheck();
                        secSeekCareDiarrhea.setVisibility(View.GONE);
                        lineSeekCareDiarrhea.setVisibility(View.GONE);
                        rdogrpSeekCareDiarrhea.clearCheck();
                        seclbl01.setVisibility(View.GONE);
                        linelbl01.setVisibility(View.GONE);
                        secGovHos.setVisibility(View.GONE);
                        lineGovHos.setVisibility(View.GONE);
                        chkGovHos.setChecked(false);
                        secGovHelCenter.setVisibility(View.GONE);
                        lineGovHelCenter.setVisibility(View.GONE);
                        chkGovHelCenter.setChecked(false);
                        secGovHelPost.setVisibility(View.GONE);
                        lineGovHelPost.setVisibility(View.GONE);
                        chkGovHelPost.setChecked(false);
                        secGovMobClinic.setVisibility(View.GONE);
                        lineGovMobClinic.setVisibility(View.GONE);
                        chkGovMobClinic.setChecked(false);
                        secPrvtHos.setVisibility(View.GONE);
                        linePrvtHos.setVisibility(View.GONE);
                        chkPrvtHos.setChecked(false);
                        secPharma.setVisibility(View.GONE);
                        linePharma.setVisibility(View.GONE);
                        chkPharma.setChecked(false);
                        secPrvtDoctor.setVisibility(View.GONE);
                        linePrvtDoctor.setVisibility(View.GONE);
                        chkPrvtDoctor.setChecked(false);
                        secPrvtMobClinic.setVisibility(View.GONE);
                        linePrvtMobClinic.setVisibility(View.GONE);
                        chkPrvtMobClinic.setChecked(false);
                        secTrdiPract.setVisibility(View.GONE);
                        lineTrdiPract.setVisibility(View.GONE);
                        chkTrdiPract.setChecked(false);
                        secDrugSeller.setVisibility(View.GONE);
                        lineDrugSeller.setVisibility(View.GONE);
                        chkDrugSeller.setChecked(false);
                        secOther.setVisibility(View.GONE);
                        lineOther.setVisibility(View.GONE);
                        chkOther.setChecked(false);
                        secOtherSp.setVisibility(View.GONE);
                        lineOtherSp.setVisibility(View.GONE);
                        txtOtherSp.setText("");
                        secFaciDk.setVisibility(View.GONE);
                        lineFaciDk.setVisibility(View.GONE);
                        chkFaciDk.setChecked(false);
                        secCough.setVisibility(View.GONE);
                        lineCough.setVisibility(View.GONE);
                        rdogrpCough.clearCheck();
                        secBreath.setVisibility(View.GONE);
                        lineBreath.setVisibility(View.GONE);
                        rdogrpBreath.clearCheck();
                        secBreathDiffC.setVisibility(View.GONE);
                        lineBreathDiffC.setVisibility(View.GONE);
                        rdogrpBreathDiffC.clearCheck();
                        secSeekCareCough.setVisibility(View.GONE);
                        lineSeekCareCough.setVisibility(View.GONE);
                        rdogrpSeekCareCough.clearCheck();
                        secCoughCareLoc.setVisibility(View.GONE);
                        lineCoughCareLoc.setVisibility(View.GONE);
                        spnCoughCareLoc.setSelection(0);
                        secCoughCareLocOth.setVisibility(View.GONE);
                        lineCoughCareLocOth.setVisibility(View.GONE);
                        txtCoughCareLocOth.setText("");
                        secFeverPst2W.setVisibility(View.GONE);
                        lineFeverPst2W.setVisibility(View.GONE);
                        rdogrpFeverPst2W.clearCheck();
                        secSeekCareFever.setVisibility(View.GONE);
                        lineSeekCareFever.setVisibility(View.GONE);
                        rdogrpSeekCareFever.clearCheck();
                        secFeverCareLoc.setVisibility(View.GONE);
                        lineFeverCareLoc.setVisibility(View.GONE);
                        spnFeverCareLoc.setSelection(0);
                        secFeverCareLocOth.setVisibility(View.GONE);
                        lineFeverCareLocOth.setVisibility(View.GONE);
                        txtFeverCareLocOth.setText("");
                    }*/
                    else
                    {
                        secChDiarrhea.setVisibility(View.VISIBLE);
                        lineChDiarrhea.setVisibility(View.VISIBLE);
                        secSeekCareDiarrhea.setVisibility(View.VISIBLE);
                        lineSeekCareDiarrhea.setVisibility(View.VISIBLE);
                        seclbl01.setVisibility(View.VISIBLE);
                        linelbl01.setVisibility(View.VISIBLE);
                        secGovHos.setVisibility(View.VISIBLE);
                        lineGovHos.setVisibility(View.VISIBLE);
                        secGovHelCenter.setVisibility(View.VISIBLE);
                        lineGovHelCenter.setVisibility(View.VISIBLE);
                        secGovHelPost.setVisibility(View.VISIBLE);
                        lineGovHelPost.setVisibility(View.VISIBLE);
                        secGovMobClinic.setVisibility(View.VISIBLE);
                        lineGovMobClinic.setVisibility(View.VISIBLE);
                        secPrvtHos.setVisibility(View.VISIBLE);
                        linePrvtHos.setVisibility(View.VISIBLE);
                        secPharma.setVisibility(View.VISIBLE);
                        linePharma.setVisibility(View.VISIBLE);
                        secPrvtDoctor.setVisibility(View.VISIBLE);
                        linePrvtDoctor.setVisibility(View.VISIBLE);
                        secPrvtMobClinic.setVisibility(View.VISIBLE);
                        linePrvtMobClinic.setVisibility(View.VISIBLE);
                        secTrdiPract.setVisibility(View.VISIBLE);
                        lineTrdiPract.setVisibility(View.VISIBLE);
                        secDrugSeller.setVisibility(View.VISIBLE);
                        lineDrugSeller.setVisibility(View.VISIBLE);
                        secOther.setVisibility(View.VISIBLE);
                        lineOther.setVisibility(View.VISIBLE);
                        //secOtherSp.setVisibility(View.VISIBLE);
                        //lineOtherSp.setVisibility(View.VISIBLE);
                        secFaciDk.setVisibility(View.VISIBLE);
                        lineFaciDk.setVisibility(View.VISIBLE);
                        secRefused.setVisibility(View.VISIBLE);
                        lineRefused.setVisibility(View.VISIBLE);
                        secCough.setVisibility(View.VISIBLE);
                        lineCough.setVisibility(View.VISIBLE);
                        secBreath.setVisibility(View.VISIBLE);
                        lineBreath.setVisibility(View.VISIBLE);
                        secBreathDiffC.setVisibility(View.VISIBLE);
                        lineBreathDiffC.setVisibility(View.VISIBLE);
                        secSeekCareCough.setVisibility(View.VISIBLE);
                        lineSeekCareCough.setVisibility(View.VISIBLE);
                        secCoughCareLoc.setVisibility(View.VISIBLE);
                        lineCoughCareLoc.setVisibility(View.VISIBLE);
                        //secCoughCareLocOth.setVisibility(View.VISIBLE);
                        //lineCoughCareLocOth.setVisibility(View.VISIBLE);
                        secFeverPst2W.setVisibility(View.VISIBLE);
                        lineFeverPst2W.setVisibility(View.VISIBLE);
                        secSeekCareFever.setVisibility(View.VISIBLE);
                        lineSeekCareFever.setVisibility(View.VISIBLE);
                        secFeverCareLoc.setVisibility(View.VISIBLE);
                        lineFeverCareLoc.setVisibility(View.VISIBLE);
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secChDiarrhea=(LinearLayout)findViewById(R.id.secChDiarrhea);
            lineChDiarrhea=(View)findViewById(R.id.lineChDiarrhea);
            VlblChDiarrhea = (TextView) findViewById(R.id.VlblChDiarrhea);
            rdogrpChDiarrhea = (RadioGroup) findViewById(R.id.rdogrpChDiarrhea);
            rdoChDiarrhea1 = (RadioButton) findViewById(R.id.rdoChDiarrhea1);
            rdoChDiarrhea2 = (RadioButton) findViewById(R.id.rdoChDiarrhea2);
            rdoChDiarrhea3 = (RadioButton) findViewById(R.id.rdoChDiarrhea3);
            rdoChDiarrhea4 = (RadioButton) findViewById(R.id.rdoChDiarrhea4);
            rdogrpChDiarrhea.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpChDiarrhea = new String[] {"0","1","8","9"};
                    for (int i = 0; i < rdogrpChDiarrhea.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpChDiarrhea.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpChDiarrhea[i];
                    }

                    if(rbData.equalsIgnoreCase("0"))
                    {
                        secSeekCareDiarrhea.setVisibility(View.GONE);
                        lineSeekCareDiarrhea.setVisibility(View.GONE);
                        rdogrpSeekCareDiarrhea.clearCheck();
                        seclbl01.setVisibility(View.GONE);
                        linelbl01.setVisibility(View.GONE);
                        secGovHos.setVisibility(View.GONE);
                        lineGovHos.setVisibility(View.GONE);
                        chkGovHos.setChecked(false);
                        secGovHelCenter.setVisibility(View.GONE);
                        lineGovHelCenter.setVisibility(View.GONE);
                        chkGovHelCenter.setChecked(false);
                        secGovHelPost.setVisibility(View.GONE);
                        lineGovHelPost.setVisibility(View.GONE);
                        chkGovHelPost.setChecked(false);
                        secGovMobClinic.setVisibility(View.GONE);
                        lineGovMobClinic.setVisibility(View.GONE);
                        chkGovMobClinic.setChecked(false);
                        secPrvtHos.setVisibility(View.GONE);
                        linePrvtHos.setVisibility(View.GONE);
                        chkPrvtHos.setChecked(false);
                        secPharma.setVisibility(View.GONE);
                        linePharma.setVisibility(View.GONE);
                        chkPharma.setChecked(false);
                        secPrvtDoctor.setVisibility(View.GONE);
                        linePrvtDoctor.setVisibility(View.GONE);
                        chkPrvtDoctor.setChecked(false);
                        secPrvtMobClinic.setVisibility(View.GONE);
                        linePrvtMobClinic.setVisibility(View.GONE);
                        chkPrvtMobClinic.setChecked(false);
                        secTrdiPract.setVisibility(View.GONE);
                        lineTrdiPract.setVisibility(View.GONE);
                        chkTrdiPract.setChecked(false);
                        secDrugSeller.setVisibility(View.GONE);
                        lineDrugSeller.setVisibility(View.GONE);
                        chkDrugSeller.setChecked(false);
                        secOther.setVisibility(View.GONE);
                        lineOther.setVisibility(View.GONE);
                        chkOther.setChecked(false);
                        secOtherSp.setVisibility(View.GONE);
                        lineOtherSp.setVisibility(View.GONE);
                        txtOtherSp.setText("");
                        secFaciDk.setVisibility(View.GONE);
                        lineFaciDk.setVisibility(View.GONE);
                        chkFaciDk.setChecked(false);
                        secRefused.setVisibility(View.GONE);
                        lineRefused.setVisibility(View.GONE);
                        chkRefused.setChecked(false);
                    }
                    else if(rbData.equalsIgnoreCase("8"))
                    {
                        secSeekCareDiarrhea.setVisibility(View.GONE);
                        lineSeekCareDiarrhea.setVisibility(View.GONE);
                        rdogrpSeekCareDiarrhea.clearCheck();
                        seclbl01.setVisibility(View.GONE);
                        linelbl01.setVisibility(View.GONE);
                        secGovHos.setVisibility(View.GONE);
                        lineGovHos.setVisibility(View.GONE);
                        chkGovHos.setChecked(false);
                        secGovHelCenter.setVisibility(View.GONE);
                        lineGovHelCenter.setVisibility(View.GONE);
                        chkGovHelCenter.setChecked(false);
                        secGovHelPost.setVisibility(View.GONE);
                        lineGovHelPost.setVisibility(View.GONE);
                        chkGovHelPost.setChecked(false);
                        secGovMobClinic.setVisibility(View.GONE);
                        lineGovMobClinic.setVisibility(View.GONE);
                        chkGovMobClinic.setChecked(false);
                        secPrvtHos.setVisibility(View.GONE);
                        linePrvtHos.setVisibility(View.GONE);
                        chkPrvtHos.setChecked(false);
                        secPharma.setVisibility(View.GONE);
                        linePharma.setVisibility(View.GONE);
                        chkPharma.setChecked(false);
                        secPrvtDoctor.setVisibility(View.GONE);
                        linePrvtDoctor.setVisibility(View.GONE);
                        chkPrvtDoctor.setChecked(false);
                        secPrvtMobClinic.setVisibility(View.GONE);
                        linePrvtMobClinic.setVisibility(View.GONE);
                        chkPrvtMobClinic.setChecked(false);
                        secTrdiPract.setVisibility(View.GONE);
                        lineTrdiPract.setVisibility(View.GONE);
                        chkTrdiPract.setChecked(false);
                        secDrugSeller.setVisibility(View.GONE);
                        lineDrugSeller.setVisibility(View.GONE);
                        chkDrugSeller.setChecked(false);
                        secOther.setVisibility(View.GONE);
                        lineOther.setVisibility(View.GONE);
                        chkOther.setChecked(false);
                        secOtherSp.setVisibility(View.GONE);
                        lineOtherSp.setVisibility(View.GONE);
                        txtOtherSp.setText("");
                        secFaciDk.setVisibility(View.GONE);
                        lineFaciDk.setVisibility(View.GONE);
                        chkFaciDk.setChecked(false);
                        secRefused.setVisibility(View.GONE);
                        lineRefused.setVisibility(View.GONE);
                        chkRefused.setChecked(false);
                    }
                    else if(rbData.equalsIgnoreCase("9"))
                    {
                        secSeekCareDiarrhea.setVisibility(View.GONE);
                        lineSeekCareDiarrhea.setVisibility(View.GONE);
                        rdogrpSeekCareDiarrhea.clearCheck();
                        seclbl01.setVisibility(View.GONE);
                        linelbl01.setVisibility(View.GONE);
                        secGovHos.setVisibility(View.GONE);
                        lineGovHos.setVisibility(View.GONE);
                        chkGovHos.setChecked(false);
                        secGovHelCenter.setVisibility(View.GONE);
                        lineGovHelCenter.setVisibility(View.GONE);
                        chkGovHelCenter.setChecked(false);
                        secGovHelPost.setVisibility(View.GONE);
                        lineGovHelPost.setVisibility(View.GONE);
                        chkGovHelPost.setChecked(false);
                        secGovMobClinic.setVisibility(View.GONE);
                        lineGovMobClinic.setVisibility(View.GONE);
                        chkGovMobClinic.setChecked(false);
                        secPrvtHos.setVisibility(View.GONE);
                        linePrvtHos.setVisibility(View.GONE);
                        chkPrvtHos.setChecked(false);
                        secPharma.setVisibility(View.GONE);
                        linePharma.setVisibility(View.GONE);
                        chkPharma.setChecked(false);
                        secPrvtDoctor.setVisibility(View.GONE);
                        linePrvtDoctor.setVisibility(View.GONE);
                        chkPrvtDoctor.setChecked(false);
                        secPrvtMobClinic.setVisibility(View.GONE);
                        linePrvtMobClinic.setVisibility(View.GONE);
                        chkPrvtMobClinic.setChecked(false);
                        secTrdiPract.setVisibility(View.GONE);
                        lineTrdiPract.setVisibility(View.GONE);
                        chkTrdiPract.setChecked(false);
                        secDrugSeller.setVisibility(View.GONE);
                        lineDrugSeller.setVisibility(View.GONE);
                        chkDrugSeller.setChecked(false);
                        secOther.setVisibility(View.GONE);
                        lineOther.setVisibility(View.GONE);
                        chkOther.setChecked(false);
                        secOtherSp.setVisibility(View.GONE);
                        lineOtherSp.setVisibility(View.GONE);
                        txtOtherSp.setText("");
                        secFaciDk.setVisibility(View.GONE);
                        lineFaciDk.setVisibility(View.GONE);
                        chkFaciDk.setChecked(false);
                        secRefused.setVisibility(View.GONE);
                        lineRefused.setVisibility(View.GONE);
                        chkRefused.setChecked(false);
                    }
                    else
                    {
                        secSeekCareDiarrhea.setVisibility(View.VISIBLE);
                        lineSeekCareDiarrhea.setVisibility(View.VISIBLE);
                        seclbl01.setVisibility(View.VISIBLE);
                        linelbl01.setVisibility(View.VISIBLE);
                        secGovHos.setVisibility(View.VISIBLE);
                        lineGovHos.setVisibility(View.VISIBLE);
                        secGovHelCenter.setVisibility(View.VISIBLE);
                        lineGovHelCenter.setVisibility(View.VISIBLE);
                        secGovHelPost.setVisibility(View.VISIBLE);
                        lineGovHelPost.setVisibility(View.VISIBLE);
                        secGovMobClinic.setVisibility(View.VISIBLE);
                        lineGovMobClinic.setVisibility(View.VISIBLE);
                        secPrvtHos.setVisibility(View.VISIBLE);
                        linePrvtHos.setVisibility(View.VISIBLE);
                        secPharma.setVisibility(View.VISIBLE);
                        linePharma.setVisibility(View.VISIBLE);
                        secPrvtDoctor.setVisibility(View.VISIBLE);
                        linePrvtDoctor.setVisibility(View.VISIBLE);
                        secPrvtMobClinic.setVisibility(View.VISIBLE);
                        linePrvtMobClinic.setVisibility(View.VISIBLE);
                        secTrdiPract.setVisibility(View.VISIBLE);
                        lineTrdiPract.setVisibility(View.VISIBLE);
                        secDrugSeller.setVisibility(View.VISIBLE);
                        lineDrugSeller.setVisibility(View.VISIBLE);
                        secOther.setVisibility(View.VISIBLE);
                        lineOther.setVisibility(View.VISIBLE);
                        secFaciDk.setVisibility(View.VISIBLE);
                        lineFaciDk.setVisibility(View.VISIBLE);
                        secRefused.setVisibility(View.VISIBLE);
                        lineRefused.setVisibility(View.VISIBLE);
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secSeekCareDiarrhea=(LinearLayout)findViewById(R.id.secSeekCareDiarrhea);
            lineSeekCareDiarrhea=(View)findViewById(R.id.lineSeekCareDiarrhea);
            VlblSeekCareDiarrhea = (TextView) findViewById(R.id.VlblSeekCareDiarrhea);
            rdogrpSeekCareDiarrhea = (RadioGroup) findViewById(R.id.rdogrpSeekCareDiarrhea);
            rdoSeekCareDiarrhea1 = (RadioButton) findViewById(R.id.rdoSeekCareDiarrhea1);
            rdoSeekCareDiarrhea2 = (RadioButton) findViewById(R.id.rdoSeekCareDiarrhea2);
            rdoSeekCareDiarrhea3 = (RadioButton) findViewById(R.id.rdoSeekCareDiarrhea3);
            rdoSeekCareDiarrhea4 = (RadioButton) findViewById(R.id.rdoSeekCareDiarrhea4);
            rdogrpSeekCareDiarrhea.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpSeekCareDiarrhea = new String[] {"0","1","8","9"};
                    for (int i = 0; i < rdogrpSeekCareDiarrhea.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpSeekCareDiarrhea.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpSeekCareDiarrhea[i];
                    }

                    if(rbData.equalsIgnoreCase("0"))
                    {
                        seclbl01.setVisibility(View.GONE);
                        linelbl01.setVisibility(View.GONE);
                        secGovHos.setVisibility(View.GONE);
                        lineGovHos.setVisibility(View.GONE);
                        chkGovHos.setChecked(false);
                        secGovHelCenter.setVisibility(View.GONE);
                        lineGovHelCenter.setVisibility(View.GONE);
                        chkGovHelCenter.setChecked(false);
                        secGovHelPost.setVisibility(View.GONE);
                        lineGovHelPost.setVisibility(View.GONE);
                        chkGovHelPost.setChecked(false);
                        secGovMobClinic.setVisibility(View.GONE);
                        lineGovMobClinic.setVisibility(View.GONE);
                        chkGovMobClinic.setChecked(false);
                        secPrvtHos.setVisibility(View.GONE);
                        linePrvtHos.setVisibility(View.GONE);
                        chkPrvtHos.setChecked(false);
                        secPharma.setVisibility(View.GONE);
                        linePharma.setVisibility(View.GONE);
                        chkPharma.setChecked(false);
                        secPrvtDoctor.setVisibility(View.GONE);
                        linePrvtDoctor.setVisibility(View.GONE);
                        chkPrvtDoctor.setChecked(false);
                        secPrvtMobClinic.setVisibility(View.GONE);
                        linePrvtMobClinic.setVisibility(View.GONE);
                        chkPrvtMobClinic.setChecked(false);
                        secTrdiPract.setVisibility(View.GONE);
                        lineTrdiPract.setVisibility(View.GONE);
                        chkTrdiPract.setChecked(false);
                        secDrugSeller.setVisibility(View.GONE);
                        lineDrugSeller.setVisibility(View.GONE);
                        chkDrugSeller.setChecked(false);
                        secOther.setVisibility(View.GONE);
                        lineOther.setVisibility(View.GONE);
                        chkOther.setChecked(false);
                        secOtherSp.setVisibility(View.GONE);
                        lineOtherSp.setVisibility(View.GONE);
                        txtOtherSp.setText("");
                        secFaciDk.setVisibility(View.GONE);
                        lineFaciDk.setVisibility(View.GONE);
                        chkFaciDk.setChecked(false);
                        secRefused.setVisibility(View.GONE);
                        lineRefused.setVisibility(View.GONE);
                        chkRefused.setChecked(false);
                    }
                    else if(rbData.equalsIgnoreCase("8"))
                    {
                        seclbl01.setVisibility(View.GONE);
                        linelbl01.setVisibility(View.GONE);
                        secGovHos.setVisibility(View.GONE);
                        lineGovHos.setVisibility(View.GONE);
                        chkGovHos.setChecked(false);
                        secGovHelCenter.setVisibility(View.GONE);
                        lineGovHelCenter.setVisibility(View.GONE);
                        chkGovHelCenter.setChecked(false);
                        secGovHelPost.setVisibility(View.GONE);
                        lineGovHelPost.setVisibility(View.GONE);
                        chkGovHelPost.setChecked(false);
                        secGovMobClinic.setVisibility(View.GONE);
                        lineGovMobClinic.setVisibility(View.GONE);
                        chkGovMobClinic.setChecked(false);
                        secPrvtHos.setVisibility(View.GONE);
                        linePrvtHos.setVisibility(View.GONE);
                        chkPrvtHos.setChecked(false);
                        secPharma.setVisibility(View.GONE);
                        linePharma.setVisibility(View.GONE);
                        chkPharma.setChecked(false);
                        secPrvtDoctor.setVisibility(View.GONE);
                        linePrvtDoctor.setVisibility(View.GONE);
                        chkPrvtDoctor.setChecked(false);
                        secPrvtMobClinic.setVisibility(View.GONE);
                        linePrvtMobClinic.setVisibility(View.GONE);
                        chkPrvtMobClinic.setChecked(false);
                        secTrdiPract.setVisibility(View.GONE);
                        lineTrdiPract.setVisibility(View.GONE);
                        chkTrdiPract.setChecked(false);
                        secDrugSeller.setVisibility(View.GONE);
                        lineDrugSeller.setVisibility(View.GONE);
                        chkDrugSeller.setChecked(false);
                        secOther.setVisibility(View.GONE);
                        lineOther.setVisibility(View.GONE);
                        chkOther.setChecked(false);
                        secOtherSp.setVisibility(View.GONE);
                        lineOtherSp.setVisibility(View.GONE);
                        txtOtherSp.setText("");
                        secFaciDk.setVisibility(View.GONE);
                        lineFaciDk.setVisibility(View.GONE);
                        chkFaciDk.setChecked(false);
                        secRefused.setVisibility(View.GONE);
                        lineRefused.setVisibility(View.GONE);
                        chkRefused.setChecked(false);
                    }
                    else if(rbData.equalsIgnoreCase("9"))
                    {
                        seclbl01.setVisibility(View.GONE);
                        linelbl01.setVisibility(View.GONE);
                        secGovHos.setVisibility(View.GONE);
                        lineGovHos.setVisibility(View.GONE);
                        chkGovHos.setChecked(false);
                        secGovHelCenter.setVisibility(View.GONE);
                        lineGovHelCenter.setVisibility(View.GONE);
                        chkGovHelCenter.setChecked(false);
                        secGovHelPost.setVisibility(View.GONE);
                        lineGovHelPost.setVisibility(View.GONE);
                        chkGovHelPost.setChecked(false);
                        secGovMobClinic.setVisibility(View.GONE);
                        lineGovMobClinic.setVisibility(View.GONE);
                        chkGovMobClinic.setChecked(false);
                        secPrvtHos.setVisibility(View.GONE);
                        linePrvtHos.setVisibility(View.GONE);
                        chkPrvtHos.setChecked(false);
                        secPharma.setVisibility(View.GONE);
                        linePharma.setVisibility(View.GONE);
                        chkPharma.setChecked(false);
                        secPrvtDoctor.setVisibility(View.GONE);
                        linePrvtDoctor.setVisibility(View.GONE);
                        chkPrvtDoctor.setChecked(false);
                        secPrvtMobClinic.setVisibility(View.GONE);
                        linePrvtMobClinic.setVisibility(View.GONE);
                        chkPrvtMobClinic.setChecked(false);
                        secTrdiPract.setVisibility(View.GONE);
                        lineTrdiPract.setVisibility(View.GONE);
                        chkTrdiPract.setChecked(false);
                        secDrugSeller.setVisibility(View.GONE);
                        lineDrugSeller.setVisibility(View.GONE);
                        chkDrugSeller.setChecked(false);
                        secOther.setVisibility(View.GONE);
                        lineOther.setVisibility(View.GONE);
                        chkOther.setChecked(false);
                        secOtherSp.setVisibility(View.GONE);
                        lineOtherSp.setVisibility(View.GONE);
                        txtOtherSp.setText("");
                        secFaciDk.setVisibility(View.GONE);
                        lineFaciDk.setVisibility(View.GONE);
                        chkFaciDk.setChecked(false);
                        secRefused.setVisibility(View.GONE);
                        lineRefused.setVisibility(View.GONE);
                        chkRefused.setChecked(false);
                    }
                    else
                    {
                        seclbl01.setVisibility(View.VISIBLE);
                        linelbl01.setVisibility(View.VISIBLE);
                        secGovHos.setVisibility(View.VISIBLE);
                        lineGovHos.setVisibility(View.VISIBLE);
                        secGovHelCenter.setVisibility(View.VISIBLE);
                        lineGovHelCenter.setVisibility(View.VISIBLE);
                        secGovHelPost.setVisibility(View.VISIBLE);
                        lineGovHelPost.setVisibility(View.VISIBLE);
                        secGovMobClinic.setVisibility(View.VISIBLE);
                        lineGovMobClinic.setVisibility(View.VISIBLE);
                        secPrvtHos.setVisibility(View.VISIBLE);
                        linePrvtHos.setVisibility(View.VISIBLE);
                        secPharma.setVisibility(View.VISIBLE);
                        linePharma.setVisibility(View.VISIBLE);
                        secPrvtDoctor.setVisibility(View.VISIBLE);
                        linePrvtDoctor.setVisibility(View.VISIBLE);
                        secPrvtMobClinic.setVisibility(View.VISIBLE);
                        linePrvtMobClinic.setVisibility(View.VISIBLE);
                        secTrdiPract.setVisibility(View.VISIBLE);
                        lineTrdiPract.setVisibility(View.VISIBLE);
                        secDrugSeller.setVisibility(View.VISIBLE);
                        lineDrugSeller.setVisibility(View.VISIBLE);
                        secOther.setVisibility(View.VISIBLE);
                        lineOther.setVisibility(View.VISIBLE);
                        secFaciDk.setVisibility(View.VISIBLE);
                        lineFaciDk.setVisibility(View.VISIBLE);
                        secRefused.setVisibility(View.VISIBLE);
                        lineRefused.setVisibility(View.VISIBLE);
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            seclbl01=(LinearLayout)findViewById(R.id.seclbl01);
            linelbl01=(View)findViewById(R.id.linelbl01);
            secGovHos=(LinearLayout)findViewById(R.id.secGovHos);
            lineGovHos=(View)findViewById(R.id.lineGovHos);
            VlblGovHos=(TextView) findViewById(R.id.VlblGovHos);
            chkGovHos=(CheckBox) findViewById(R.id.chkGovHos);
            chkGovHos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkFaciDk.setChecked(false);
                        chkRefused.setChecked(false);
                    }
                }
            });
            secGovHelCenter=(LinearLayout)findViewById(R.id.secGovHelCenter);
            lineGovHelCenter=(View)findViewById(R.id.lineGovHelCenter);
            VlblGovHelCenter=(TextView) findViewById(R.id.VlblGovHelCenter);
            chkGovHelCenter=(CheckBox) findViewById(R.id.chkGovHelCenter);
            chkGovHelCenter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkFaciDk.setChecked(false);
                        chkRefused.setChecked(false);
                    }
                }
            });
            secGovHelPost=(LinearLayout)findViewById(R.id.secGovHelPost);
            lineGovHelPost=(View)findViewById(R.id.lineGovHelPost);
            VlblGovHelPost=(TextView) findViewById(R.id.VlblGovHelPost);
            chkGovHelPost=(CheckBox) findViewById(R.id.chkGovHelPost);
            chkGovHelPost.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkFaciDk.setChecked(false);
                        chkRefused.setChecked(false);
                    }
                }
            });
            secGovMobClinic=(LinearLayout)findViewById(R.id.secGovMobClinic);
            lineGovMobClinic=(View)findViewById(R.id.lineGovMobClinic);
            VlblGovMobClinic=(TextView) findViewById(R.id.VlblGovMobClinic);
            chkGovMobClinic=(CheckBox) findViewById(R.id.chkGovMobClinic);
            chkGovMobClinic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkFaciDk.setChecked(false);
                        chkRefused.setChecked(false);
                    }
                }
            });
            secPrvtHos=(LinearLayout)findViewById(R.id.secPrvtHos);
            linePrvtHos=(View)findViewById(R.id.linePrvtHos);
            VlblPrvtHos=(TextView) findViewById(R.id.VlblPrvtHos);
            chkPrvtHos=(CheckBox) findViewById(R.id.chkPrvtHos);
            chkPrvtHos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkFaciDk.setChecked(false);
                        chkRefused.setChecked(false);
                    }
                }
            });
            secPharma=(LinearLayout)findViewById(R.id.secPharma);
            linePharma=(View)findViewById(R.id.linePharma);
            VlblPharma=(TextView) findViewById(R.id.VlblPharma);
            chkPharma=(CheckBox) findViewById(R.id.chkPharma);
            chkPharma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkFaciDk.setChecked(false);
                        chkRefused.setChecked(false);
                    }
                }
            });
            secPrvtDoctor=(LinearLayout)findViewById(R.id.secPrvtDoctor);
            linePrvtDoctor=(View)findViewById(R.id.linePrvtDoctor);
            VlblPrvtDoctor=(TextView) findViewById(R.id.VlblPrvtDoctor);
            chkPrvtDoctor=(CheckBox) findViewById(R.id.chkPrvtDoctor);
            chkPrvtDoctor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkFaciDk.setChecked(false);
                        chkRefused.setChecked(false);
                    }
                }
            });
            secPrvtMobClinic=(LinearLayout)findViewById(R.id.secPrvtMobClinic);
            linePrvtMobClinic=(View)findViewById(R.id.linePrvtMobClinic);
            VlblPrvtMobClinic=(TextView) findViewById(R.id.VlblPrvtMobClinic);
            chkPrvtMobClinic=(CheckBox) findViewById(R.id.chkPrvtMobClinic);
            chkPrvtMobClinic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkFaciDk.setChecked(false);
                        chkRefused.setChecked(false);
                    }
                }
            });
            secTrdiPract=(LinearLayout)findViewById(R.id.secTrdiPract);
            lineTrdiPract=(View)findViewById(R.id.lineTrdiPract);
            VlblTrdiPract=(TextView) findViewById(R.id.VlblTrdiPract);
            chkTrdiPract=(CheckBox) findViewById(R.id.chkTrdiPract);
            chkTrdiPract.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkFaciDk.setChecked(false);
                        chkRefused.setChecked(false);
                    }
                }
            });
            secDrugSeller=(LinearLayout)findViewById(R.id.secDrugSeller);
            lineDrugSeller=(View)findViewById(R.id.lineDrugSeller);
            VlblDrugSeller=(TextView) findViewById(R.id.VlblDrugSeller);
            chkDrugSeller=(CheckBox) findViewById(R.id.chkDrugSeller);
            chkDrugSeller.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkFaciDk.setChecked(false);
                        chkRefused.setChecked(false);
                    }
                }
            });
            secOther=(LinearLayout)findViewById(R.id.secOther);
            lineOther=(View)findViewById(R.id.lineOther);
            VlblOther=(TextView) findViewById(R.id.VlblOther);
            chkOther=(CheckBox) findViewById(R.id.chkOther);
            chkOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        secOtherSp.setVisibility(View.VISIBLE);
                        lineOtherSp.setVisibility(View.VISIBLE);
                        chkFaciDk.setChecked(false);
                        chkRefused.setChecked(false);
                    }
                    else {
                        secOtherSp.setVisibility(View.GONE);
                        lineOtherSp.setVisibility(View.GONE);
                        txtOtherSp.setText("");
                    }
                }
            });
            secOtherSp=(LinearLayout)findViewById(R.id.secOtherSp);
            lineOtherSp=(View)findViewById(R.id.lineOtherSp);
            VlblOtherSp=(TextView) findViewById(R.id.VlblOtherSp);
            txtOtherSp=(EditText) findViewById(R.id.txtOtherSp);
            secFaciDk=(LinearLayout)findViewById(R.id.secFaciDk);
            lineFaciDk=(View)findViewById(R.id.lineFaciDk);
            VlblFaciDk=(TextView) findViewById(R.id.VlblFaciDk);
            chkFaciDk=(CheckBox) findViewById(R.id.chkFaciDk);
            chkFaciDk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        chkGovHos.setChecked(false);
                        chkGovHelCenter.setChecked(false);
                        chkGovHelPost.setChecked(false);
                        chkGovMobClinic.setChecked(false);
                        chkPrvtHos.setChecked(false);
                        chkPharma.setChecked(false);
                        chkPrvtDoctor.setChecked(false);
                        chkPrvtMobClinic.setChecked(false);
                        chkTrdiPract.setChecked(false);
                        chkDrugSeller.setChecked(false);
                        chkOther.setChecked(false);
                        //chkFaciDk.setChecked(false);
                        chkRefused.setChecked(false);
                    }
                }
            });
            secRefused=(LinearLayout)findViewById(R.id.secRefused);
            lineRefused=(View)findViewById(R.id.lineRefused);
            VlblRefused=(TextView) findViewById(R.id.VlblRefused);
            chkRefused=(CheckBox) findViewById(R.id.chkRefused);
            chkRefused.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        chkGovHos.setChecked(false);
                        chkGovHelCenter.setChecked(false);
                        chkGovHelPost.setChecked(false);
                        chkGovMobClinic.setChecked(false);
                        chkPrvtHos.setChecked(false);
                        chkPharma.setChecked(false);
                        chkPrvtDoctor.setChecked(false);
                        chkPrvtMobClinic.setChecked(false);
                        chkTrdiPract.setChecked(false);
                        chkDrugSeller.setChecked(false);
                        chkOther.setChecked(false);
                        chkFaciDk.setChecked(false);
                        //chkRefused.setChecked(false);
                    }
                }
            });
            secCough=(LinearLayout)findViewById(R.id.secCough);
            lineCough=(View)findViewById(R.id.lineCough);
            VlblCough = (TextView) findViewById(R.id.VlblCough);
            rdogrpCough = (RadioGroup) findViewById(R.id.rdogrpCough);
            rdoCough1 = (RadioButton) findViewById(R.id.rdoCough1);
            rdoCough2 = (RadioButton) findViewById(R.id.rdoCough2);
            rdoCough3 = (RadioButton) findViewById(R.id.rdoCough3);
            rdoCough4 = (RadioButton) findViewById(R.id.rdoCough4);
            rdogrpCough.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpCough = new String[] {"0","1","8","9"};
                    for (int i = 0; i < rdogrpCough.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpCough.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpCough[i];
                    }

                    if(rbData.equalsIgnoreCase("0"))
                    {
                        secBreath.setVisibility(View.GONE);
                        lineBreath.setVisibility(View.GONE);
                        rdogrpBreath.clearCheck();
                        secBreathDiffC.setVisibility(View.GONE);
                        lineBreathDiffC.setVisibility(View.GONE);
                        rdogrpBreathDiffC.clearCheck();
                        secSeekCareCough.setVisibility(View.GONE);
                        lineSeekCareCough.setVisibility(View.GONE);
                        rdogrpSeekCareCough.clearCheck();
                        secCoughCareLoc.setVisibility(View.GONE);
                        lineCoughCareLoc.setVisibility(View.GONE);
                        spnCoughCareLoc.setSelection(0);
                        secCoughCareLocOth.setVisibility(View.GONE);
                        lineCoughCareLocOth.setVisibility(View.GONE);
                        txtCoughCareLocOth.setText("");
                    }
                    else if(rbData.equalsIgnoreCase("8"))
                    {
                        secBreath.setVisibility(View.GONE);
                        lineBreath.setVisibility(View.GONE);
                        rdogrpBreath.clearCheck();
                        secBreathDiffC.setVisibility(View.GONE);
                        lineBreathDiffC.setVisibility(View.GONE);
                        rdogrpBreathDiffC.clearCheck();
                        secSeekCareCough.setVisibility(View.GONE);
                        lineSeekCareCough.setVisibility(View.GONE);
                        rdogrpSeekCareCough.clearCheck();
                        secCoughCareLoc.setVisibility(View.GONE);
                        lineCoughCareLoc.setVisibility(View.GONE);
                        spnCoughCareLoc.setSelection(0);
                        secCoughCareLocOth.setVisibility(View.GONE);
                        lineCoughCareLocOth.setVisibility(View.GONE);
                        txtCoughCareLocOth.setText("");
                    }
                    else if(rbData.equalsIgnoreCase("9"))
                    {
                        secBreath.setVisibility(View.GONE);
                        lineBreath.setVisibility(View.GONE);
                        rdogrpBreath.clearCheck();
                        secBreathDiffC.setVisibility(View.GONE);
                        lineBreathDiffC.setVisibility(View.GONE);
                        rdogrpBreathDiffC.clearCheck();
                        secSeekCareCough.setVisibility(View.GONE);
                        lineSeekCareCough.setVisibility(View.GONE);
                        rdogrpSeekCareCough.clearCheck();
                        secCoughCareLoc.setVisibility(View.GONE);
                        lineCoughCareLoc.setVisibility(View.GONE);
                        spnCoughCareLoc.setSelection(0);
                        secCoughCareLocOth.setVisibility(View.GONE);
                        lineCoughCareLocOth.setVisibility(View.GONE);
                        txtCoughCareLocOth.setText("");
                    }
                    else
                    {
                        secBreath.setVisibility(View.VISIBLE);
                        lineBreath.setVisibility(View.VISIBLE);
                        secBreathDiffC.setVisibility(View.VISIBLE);
                        lineBreathDiffC.setVisibility(View.VISIBLE);
                        secSeekCareCough.setVisibility(View.VISIBLE);
                        lineSeekCareCough.setVisibility(View.VISIBLE);
                        secCoughCareLoc.setVisibility(View.VISIBLE);
                        lineCoughCareLoc.setVisibility(View.VISIBLE);
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secBreath=(LinearLayout)findViewById(R.id.secBreath);
            lineBreath=(View)findViewById(R.id.lineBreath);
            VlblBreath = (TextView) findViewById(R.id.VlblBreath);
            rdogrpBreath = (RadioGroup) findViewById(R.id.rdogrpBreath);
            rdoBreath1 = (RadioButton) findViewById(R.id.rdoBreath1);
            rdoBreath2 = (RadioButton) findViewById(R.id.rdoBreath2);
            rdoBreath3 = (RadioButton) findViewById(R.id.rdoBreath3);
            rdoBreath4 = (RadioButton) findViewById(R.id.rdoBreath4);
            secBreathDiffC=(LinearLayout)findViewById(R.id.secBreathDiffC);
            lineBreathDiffC=(View)findViewById(R.id.lineBreathDiffC);
            VlblBreathDiffC = (TextView) findViewById(R.id.VlblBreathDiffC);
            rdogrpBreathDiffC = (RadioGroup) findViewById(R.id.rdogrpBreathDiffC);
            rdoBreathDiffC1 = (RadioButton) findViewById(R.id.rdoBreathDiffC1);
            rdoBreathDiffC2 = (RadioButton) findViewById(R.id.rdoBreathDiffC2);
            rdoBreathDiffC3 = (RadioButton) findViewById(R.id.rdoBreathDiffC3);
            rdoBreathDiffC4 = (RadioButton) findViewById(R.id.rdoBreathDiffC4);
            rdoBreathDiffC5 = (RadioButton) findViewById(R.id.rdoBreathDiffC5);
            secSeekCareCough=(LinearLayout)findViewById(R.id.secSeekCareCough);
            lineSeekCareCough=(View)findViewById(R.id.lineSeekCareCough);
            VlblSeekCareCough = (TextView) findViewById(R.id.VlblSeekCareCough);
            rdogrpSeekCareCough = (RadioGroup) findViewById(R.id.rdogrpSeekCareCough);
            rdoSeekCareCough1 = (RadioButton) findViewById(R.id.rdoSeekCareCough1);
            rdoSeekCareCough2 = (RadioButton) findViewById(R.id.rdoSeekCareCough2);
            rdoSeekCareCough3 = (RadioButton) findViewById(R.id.rdoSeekCareCough3);
            rdoSeekCareCough4 = (RadioButton) findViewById(R.id.rdoSeekCareCough4);
            rdogrpSeekCareCough.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpSeekCareCough = new String[] {"0","1","8","9"};
                    for (int i = 0; i < rdogrpSeekCareCough.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpSeekCareCough.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpSeekCareCough[i];
                    }

                    if(rbData.equalsIgnoreCase("0"))
                    {
                        secCoughCareLoc.setVisibility(View.GONE);
                        lineCoughCareLoc.setVisibility(View.GONE);
                        spnCoughCareLoc.setSelection(0);
                        secCoughCareLocOth.setVisibility(View.GONE);
                        lineCoughCareLocOth.setVisibility(View.GONE);
                        txtCoughCareLocOth.setText("");
                    }
                    else if(rbData.equalsIgnoreCase("8"))
                    {
                        secCoughCareLoc.setVisibility(View.GONE);
                        lineCoughCareLoc.setVisibility(View.GONE);
                        spnCoughCareLoc.setSelection(0);
                        secCoughCareLocOth.setVisibility(View.GONE);
                        lineCoughCareLocOth.setVisibility(View.GONE);
                        txtCoughCareLocOth.setText("");
                    }
                    else if(rbData.equalsIgnoreCase("9"))
                    {
                        secCoughCareLoc.setVisibility(View.GONE);
                        lineCoughCareLoc.setVisibility(View.GONE);
                        spnCoughCareLoc.setSelection(0);
                        secCoughCareLocOth.setVisibility(View.GONE);
                        lineCoughCareLocOth.setVisibility(View.GONE);
                        txtCoughCareLocOth.setText("");
                    }
                    else
                    {
                        secCoughCareLoc.setVisibility(View.VISIBLE);
                        lineCoughCareLoc.setVisibility(View.VISIBLE);
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secCoughCareLoc=(LinearLayout)findViewById(R.id.secCoughCareLoc);
            lineCoughCareLoc=(View)findViewById(R.id.lineCoughCareLoc);
            VlblCoughCareLoc=(TextView) findViewById(R.id.VlblCoughCareLoc);
            spnCoughCareLoc=(Spinner) findViewById(R.id.spnCoughCareLoc);
            List<String> listCoughCareLoc = new ArrayList<String>();

            listCoughCareLoc.add("");
            listCoughCareLoc.add("01-Government hospital");
            listCoughCareLoc.add("02-Government health center");
            listCoughCareLoc.add("03-Government health post");
            listCoughCareLoc.add("04-Government Mobile clinic");
            listCoughCareLoc.add("05-Private hospital/clinic");
            listCoughCareLoc.add("06-Private Pharmacy");
            listCoughCareLoc.add("07-Private doctor");
            listCoughCareLoc.add("08-Private Mobile clinic");
            listCoughCareLoc.add("09-Traditional practitioner");
            listCoughCareLoc.add("10-Itinerant drug seller");
            listCoughCareLoc.add("97-Other");
            listCoughCareLoc.add("98-Don't know");
            listCoughCareLoc.add("99-Refused to respond");
            spnCoughCareLoc.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listCoughCareLoc)));

            spnCoughCareLoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnCoughCareLoc.getSelectedItem().toString().length() != 0)
                    {
                        spnData = Connection.SelectedSpinnerValue(spnCoughCareLoc.getSelectedItem().toString(), "-");
                    }
                    if(spnData.equalsIgnoreCase("97"))
                    {
                        secCoughCareLocOth.setVisibility(View.VISIBLE);
                        lineCoughCareLocOth.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        secCoughCareLocOth.setVisibility(View.GONE);
                        lineCoughCareLocOth.setVisibility(View.GONE);
                        txtCoughCareLocOth.setText("");
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secCoughCareLocOth=(LinearLayout)findViewById(R.id.secCoughCareLocOth);
            lineCoughCareLocOth=(View)findViewById(R.id.lineCoughCareLocOth);
            VlblCoughCareLocOth=(TextView) findViewById(R.id.VlblCoughCareLocOth);
            txtCoughCareLocOth=(AutoCompleteTextView) findViewById(R.id.txtCoughCareLocOth);
            txtCoughCareLocOth.setAdapter(C.getArrayAdapter("Select distinct CoughCareLocOth from "+ TableName +" order by CoughCareLocOth"));

            txtCoughCareLocOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

                }
            });
            txtCoughCareLocOth.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (txtCoughCareLocOth.getRight() - txtCoughCareLocOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            ((EditText)v).setText("");
                            return true;
                        }
                    }
                    return false;
                }
            });
            secFeverPst2W=(LinearLayout)findViewById(R.id.secFeverPst2W);
            lineFeverPst2W=(View)findViewById(R.id.lineFeverPst2W);
            VlblFeverPst2W = (TextView) findViewById(R.id.VlblFeverPst2W);
            rdogrpFeverPst2W = (RadioGroup) findViewById(R.id.rdogrpFeverPst2W);
            rdoFeverPst2W1 = (RadioButton) findViewById(R.id.rdoFeverPst2W1);
            rdoFeverPst2W2 = (RadioButton) findViewById(R.id.rdoFeverPst2W2);
            rdoFeverPst2W3 = (RadioButton) findViewById(R.id.rdoFeverPst2W3);
            rdoFeverPst2W4 = (RadioButton) findViewById(R.id.rdoFeverPst2W4);
            rdogrpFeverPst2W.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpFeverPst2W = new String[] {"0","1","8","9"};
                    for (int i = 0; i < rdogrpFeverPst2W.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpFeverPst2W.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpFeverPst2W[i];
                    }

                    if(rbData.equalsIgnoreCase("0"))
                    {
                        secSeekCareFever.setVisibility(View.GONE);
                        lineSeekCareFever.setVisibility(View.GONE);
                        rdogrpSeekCareFever.clearCheck();
                        secFeverCareLoc.setVisibility(View.GONE);
                        lineFeverCareLoc.setVisibility(View.GONE);
                        spnFeverCareLoc.setSelection(0);
                        secFeverCareLocOth.setVisibility(View.GONE);
                        lineFeverCareLocOth.setVisibility(View.GONE);
                        txtFeverCareLocOth.setText("");
                    }
                    else if(rbData.equalsIgnoreCase("8"))
                    {
                        secSeekCareFever.setVisibility(View.GONE);
                        lineSeekCareFever.setVisibility(View.GONE);
                        rdogrpSeekCareFever.clearCheck();
                        secFeverCareLoc.setVisibility(View.GONE);
                        lineFeverCareLoc.setVisibility(View.GONE);
                        spnFeverCareLoc.setSelection(0);
                        secFeverCareLocOth.setVisibility(View.GONE);
                        lineFeverCareLocOth.setVisibility(View.GONE);
                        txtFeverCareLocOth.setText("");
                    }
                    else if(rbData.equalsIgnoreCase("9"))
                    {
                        secSeekCareFever.setVisibility(View.GONE);
                        lineSeekCareFever.setVisibility(View.GONE);
                        rdogrpSeekCareFever.clearCheck();
                        secFeverCareLoc.setVisibility(View.GONE);
                        lineFeverCareLoc.setVisibility(View.GONE);
                        spnFeverCareLoc.setSelection(0);
                        secFeverCareLocOth.setVisibility(View.GONE);
                        lineFeverCareLocOth.setVisibility(View.GONE);
                        txtFeverCareLocOth.setText("");
                    }
                    else
                    {
                        secSeekCareFever.setVisibility(View.VISIBLE);
                        lineSeekCareFever.setVisibility(View.VISIBLE);
                        secFeverCareLoc.setVisibility(View.VISIBLE);
                        lineFeverCareLoc.setVisibility(View.VISIBLE);
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secSeekCareFever=(LinearLayout)findViewById(R.id.secSeekCareFever);
            lineSeekCareFever=(View)findViewById(R.id.lineSeekCareFever);
            VlblSeekCareFever = (TextView) findViewById(R.id.VlblSeekCareFever);
            rdogrpSeekCareFever = (RadioGroup) findViewById(R.id.rdogrpSeekCareFever);
            rdoSeekCareFever1 = (RadioButton) findViewById(R.id.rdoSeekCareFever1);
            rdoSeekCareFever2 = (RadioButton) findViewById(R.id.rdoSeekCareFever2);
            rdoSeekCareFever3 = (RadioButton) findViewById(R.id.rdoSeekCareFever3);
            rdoSeekCareFever4 = (RadioButton) findViewById(R.id.rdoSeekCareFever4);
            rdogrpSeekCareFever.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpSeekCareFever = new String[] {"0","1","8","9"};
                    for (int i = 0; i < rdogrpSeekCareFever.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpSeekCareFever.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpSeekCareFever[i];
                    }

                    if(rbData.equalsIgnoreCase("0"))
                    {
                        secFeverCareLoc.setVisibility(View.GONE);
                        lineFeverCareLoc.setVisibility(View.GONE);
                        spnFeverCareLoc.setSelection(0);
                        secFeverCareLocOth.setVisibility(View.GONE);
                        lineFeverCareLocOth.setVisibility(View.GONE);
                        txtFeverCareLocOth.setText("");
                    }
                    else if(rbData.equalsIgnoreCase("8"))
                    {
                        secFeverCareLoc.setVisibility(View.GONE);
                        lineFeverCareLoc.setVisibility(View.GONE);
                        spnFeverCareLoc.setSelection(0);
                        secFeverCareLocOth.setVisibility(View.GONE);
                        lineFeverCareLocOth.setVisibility(View.GONE);
                        txtFeverCareLocOth.setText("");
                    }
                    else if(rbData.equalsIgnoreCase("9"))
                    {
                        secFeverCareLoc.setVisibility(View.GONE);
                        lineFeverCareLoc.setVisibility(View.GONE);
                        spnFeverCareLoc.setSelection(0);
                        secFeverCareLocOth.setVisibility(View.GONE);
                        lineFeverCareLocOth.setVisibility(View.GONE);
                        txtFeverCareLocOth.setText("");
                    }
                    else
                    {
                        secFeverCareLoc.setVisibility(View.VISIBLE);
                        lineFeverCareLoc.setVisibility(View.VISIBLE);
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secFeverCareLoc=(LinearLayout)findViewById(R.id.secFeverCareLoc);
            lineFeverCareLoc=(View)findViewById(R.id.lineFeverCareLoc);
            VlblFeverCareLoc=(TextView) findViewById(R.id.VlblFeverCareLoc);
            spnFeverCareLoc=(Spinner) findViewById(R.id.spnFeverCareLoc);
            List<String> listFeverCareLoc = new ArrayList<String>();

            listFeverCareLoc.add("");
            listFeverCareLoc.add("01-Government hospital");
            listFeverCareLoc.add("02-Government health center");
            listFeverCareLoc.add("03-Government health post");
            listFeverCareLoc.add("04-Government Mobile clinic");
            listFeverCareLoc.add("05-Private hospital/clinic");
            listFeverCareLoc.add("06-Private Pharmacy");
            listFeverCareLoc.add("07-Private doctor");
            listFeverCareLoc.add("08-Private Mobile clinic");
            listFeverCareLoc.add("09-Traditional practitioner");
            listFeverCareLoc.add("10-Itinerant drug seller");
            listFeverCareLoc.add("97-Other");
            listFeverCareLoc.add("98-Don't know");
            listFeverCareLoc.add("99-Refused to respond");
            spnFeverCareLoc.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listFeverCareLoc)));

            spnFeverCareLoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnFeverCareLoc.getSelectedItem().toString().length() != 0)
                    {
                        spnData = Connection.SelectedSpinnerValue(spnFeverCareLoc.getSelectedItem().toString(), "-");
                    }
                    if(spnData.equalsIgnoreCase("97"))
                    {
                        secFeverCareLocOth.setVisibility(View.VISIBLE);
                        lineFeverCareLocOth.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        secFeverCareLocOth.setVisibility(View.GONE);
                        lineFeverCareLocOth.setVisibility(View.GONE);
                        txtFeverCareLocOth.setText("");
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secFeverCareLocOth=(LinearLayout)findViewById(R.id.secFeverCareLocOth);
            lineFeverCareLocOth=(View)findViewById(R.id.lineFeverCareLocOth);
            VlblFeverCareLocOth=(TextView) findViewById(R.id.VlblFeverCareLocOth);
            txtFeverCareLocOth=(AutoCompleteTextView) findViewById(R.id.txtFeverCareLocOth);
            txtFeverCareLocOth.setAdapter(C.getArrayAdapter("Select distinct FeverCareLocOth from "+ TableName +" order by FeverCareLocOth"));

            txtFeverCareLocOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

                }
            });
            txtFeverCareLocOth.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (txtFeverCareLocOth.getRight() - txtFeverCareLocOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            ((EditText)v).setText("");
                            return true;
                        }
                    }
                    return false;
                }
            });
            secWgtLost=(LinearLayout)findViewById(R.id.secWgtLost);
            lineWgtLost=(View)findViewById(R.id.lineWgtLost);
            VlblWgtLost = (TextView) findViewById(R.id.VlblWgtLost);
            rdogrpWgtLost = (RadioGroup) findViewById(R.id.rdogrpWgtLost);
            rdoWgtLost1 = (RadioButton) findViewById(R.id.rdoWgtLost1);
            rdoWgtLost2 = (RadioButton) findViewById(R.id.rdoWgtLost2);
            rdoWgtLost3 = (RadioButton) findViewById(R.id.rdoWgtLost3);
            rdoWgtLost4 = (RadioButton) findViewById(R.id.rdoWgtLost4);
            secWgtGain=(LinearLayout)findViewById(R.id.secWgtGain);
            lineWgtGain=(View)findViewById(R.id.lineWgtGain);
            VlblWgtGain = (TextView) findViewById(R.id.VlblWgtGain);
            rdogrpWgtGain = (RadioGroup) findViewById(R.id.rdogrpWgtGain);
            rdoWgtGain1 = (RadioButton) findViewById(R.id.rdoWgtGain1);
            rdoWgtGain2 = (RadioButton) findViewById(R.id.rdoWgtGain2);
            rdoWgtGain3 = (RadioButton) findViewById(R.id.rdoWgtGain3);
            rdoWgtGain4 = (RadioButton) findViewById(R.id.rdoWgtGain4);
            secChLessFeed=(LinearLayout)findViewById(R.id.secChLessFeed);
            lineChLessFeed=(View)findViewById(R.id.lineChLessFeed);
            VlblChLessFeed = (TextView) findViewById(R.id.VlblChLessFeed);
            rdogrpChLessFeed = (RadioGroup) findViewById(R.id.rdogrpChLessFeed);
            rdoChLessFeed1 = (RadioButton) findViewById(R.id.rdoChLessFeed1);
            rdoChLessFeed2 = (RadioButton) findViewById(R.id.rdoChLessFeed2);
            rdoChLessFeed3 = (RadioButton) findViewById(R.id.rdoChLessFeed3);
            rdoChLessFeed4 = (RadioButton) findViewById(R.id.rdoChLessFeed4);
            secUnderWeight=(LinearLayout)findViewById(R.id.secUnderWeight);
            lineUnderWeight=(View)findViewById(R.id.lineUnderWeight);
            VlblUnderWeight = (TextView) findViewById(R.id.VlblUnderWeight);
            rdogrpUnderWeight = (RadioGroup) findViewById(R.id.rdogrpUnderWeight);
            rdoUnderWeight1 = (RadioButton) findViewById(R.id.rdoUnderWeight1);
            rdoUnderWeight2 = (RadioButton) findViewById(R.id.rdoUnderWeight2);
            rdoUnderWeight3 = (RadioButton) findViewById(R.id.rdoUnderWeight3);
            rdoUnderWeight4 = (RadioButton) findViewById(R.id.rdoUnderWeight4);
            rdogrpUnderWeight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpUnderWeight = new String[] {"0","1","8","9"};
                    for (int i = 0; i < rdogrpUnderWeight.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpUnderWeight.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpUnderWeight[i];
                    }

                    if(rbData.equalsIgnoreCase("1"))
                    {
                        secOverWeight.setVisibility(View.GONE);
                        lineOverWeight.setVisibility(View.GONE);
                        rdogrpOverWeight.clearCheck();
                        secEvrVacc.setVisibility(View.GONE);
                        lineEvrVacc.setVisibility(View.GONE);
                        rdogrpEvrVacc.clearCheck();
                    }
                    else
                    {
                        secOverWeight.setVisibility(View.VISIBLE);
                        lineOverWeight.setVisibility(View.VISIBLE);
                        secEvrVacc.setVisibility(View.VISIBLE);
                        lineEvrVacc.setVisibility(View.VISIBLE);
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secOverWeight=(LinearLayout)findViewById(R.id.secOverWeight);
            lineOverWeight=(View)findViewById(R.id.lineOverWeight);
            VlblOverWeight = (TextView) findViewById(R.id.VlblOverWeight);
            rdogrpOverWeight = (RadioGroup) findViewById(R.id.rdogrpOverWeight);
            rdoOverWeight1 = (RadioButton) findViewById(R.id.rdoOverWeight1);
            rdoOverWeight2 = (RadioButton) findViewById(R.id.rdoOverWeight2);
            rdoOverWeight3 = (RadioButton) findViewById(R.id.rdoOverWeight3);
            rdoOverWeight4 = (RadioButton) findViewById(R.id.rdoOverWeight4);
            secEvrVacc=(LinearLayout)findViewById(R.id.secEvrVacc);
            lineEvrVacc=(View)findViewById(R.id.lineEvrVacc);
            VlblEvrVacc = (TextView) findViewById(R.id.VlblEvrVacc);
            rdogrpEvrVacc = (RadioGroup) findViewById(R.id.rdogrpEvrVacc);
            rdoEvrVacc1 = (RadioButton) findViewById(R.id.rdoEvrVacc1);
            rdoEvrVacc2 = (RadioButton) findViewById(R.id.rdoEvrVacc2);
            rdoEvrVacc3 = (RadioButton) findViewById(R.id.rdoEvrVacc3);
            rdoEvrVacc4 = (RadioButton) findViewById(R.id.rdoEvrVacc4);
            rdogrpEvrVacc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpEvrVacc = new String[] {"0","1","8","9"};
                    for (int i = 0; i < rdogrpEvrVacc.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpEvrVacc.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpEvrVacc[i];
                    }

                    if(rbData.equalsIgnoreCase("0"))
                    {
                        secVaccCard.setVisibility(View.GONE);
                        lineVaccCard.setVisibility(View.GONE);
                        rdogrpVaccCard.clearCheck();
                        secVaccCardPhoto.setVisibility(View.GONE);
                        lineVaccCardPhoto.setVisibility(View.GONE);
                        rdogrpVaccCardPhoto.clearCheck();
                        secEvrVaccCard.setVisibility(View.GONE);
                        lineEvrVaccCard.setVisibility(View.GONE);
                        rdogrpEvrVaccCard.clearCheck();
                    }
                    else if(rbData.equalsIgnoreCase("8"))
                    {
                        secVaccCard.setVisibility(View.GONE);
                        lineVaccCard.setVisibility(View.GONE);
                        rdogrpVaccCard.clearCheck();
                        secVaccCardPhoto.setVisibility(View.GONE);
                        lineVaccCardPhoto.setVisibility(View.GONE);
                        rdogrpVaccCardPhoto.clearCheck();
                        secEvrVaccCard.setVisibility(View.GONE);
                        lineEvrVaccCard.setVisibility(View.GONE);
                        rdogrpEvrVaccCard.clearCheck();
                    }
                    else if(rbData.equalsIgnoreCase("9"))
                    {
                        secVaccCard.setVisibility(View.GONE);
                        lineVaccCard.setVisibility(View.GONE);
                        rdogrpVaccCard.clearCheck();
                        secVaccCardPhoto.setVisibility(View.GONE);
                        lineVaccCardPhoto.setVisibility(View.GONE);
                        rdogrpVaccCardPhoto.clearCheck();
                        secEvrVaccCard.setVisibility(View.GONE);
                        lineEvrVaccCard.setVisibility(View.GONE);
                        rdogrpEvrVaccCard.clearCheck();
                    }
                    else
                    {
                        secVaccCard.setVisibility(View.VISIBLE);
                        lineVaccCard.setVisibility(View.VISIBLE);
                        secVaccCardPhoto.setVisibility(View.GONE);
                        lineVaccCardPhoto.setVisibility(View.VISIBLE);
                        secEvrVaccCard.setVisibility(View.VISIBLE);
                        lineEvrVaccCard.setVisibility(View.VISIBLE);
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secVaccCard=(LinearLayout)findViewById(R.id.secVaccCard);
            lineVaccCard=(View)findViewById(R.id.lineVaccCard);
            VlblVaccCard = (TextView) findViewById(R.id.VlblVaccCard);
            rdogrpVaccCard = (RadioGroup) findViewById(R.id.rdogrpVaccCard);
            rdoVaccCard1 = (RadioButton) findViewById(R.id.rdoVaccCard1);
            rdoVaccCard2 = (RadioButton) findViewById(R.id.rdoVaccCard2);
            rdoVaccCard3 = (RadioButton) findViewById(R.id.rdoVaccCard3);
            rdoVaccCard4 = (RadioButton) findViewById(R.id.rdoVaccCard4);
            rdogrpVaccCard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpVaccCard = new String[] {"0","1","8","9"};
                    for (int i = 0; i < rdogrpVaccCard.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpVaccCard.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpVaccCard[i];
                    }

                    if(rbData.equalsIgnoreCase("0"))
                    {
                        secVaccCardPhoto.setVisibility(View.GONE);
                        lineVaccCardPhoto.setVisibility(View.GONE);
                        rdogrpVaccCardPhoto.clearCheck();
                    }
                    else if(rbData.equalsIgnoreCase("8"))
                    {
                        secVaccCardPhoto.setVisibility(View.GONE);
                        lineVaccCardPhoto.setVisibility(View.GONE);
                        rdogrpVaccCardPhoto.clearCheck();
                    }
                    else if(rbData.equalsIgnoreCase("9"))
                    {
                        secVaccCardPhoto.setVisibility(View.GONE);
                        lineVaccCardPhoto.setVisibility(View.GONE);
                        rdogrpVaccCardPhoto.clearCheck();
                    }
                    else
                    {
                        secVaccCardPhoto.setVisibility(View.GONE);
                        lineVaccCardPhoto.setVisibility(View.VISIBLE);
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

            secVaccCardPhoto=(LinearLayout)findViewById(R.id.secVaccCardPhoto);
            lineVaccCardPhoto=(View)findViewById(R.id.lineVaccCardPhoto);
            VlblVaccCardPhoto = (TextView) findViewById(R.id.VlblVaccCardPhoto);
            rdogrpVaccCardPhoto = (RadioGroup) findViewById(R.id.rdogrpVaccCardPhoto);
            rdoVaccCardPhoto1 = (RadioButton) findViewById(R.id.rdoVaccCardPhoto1);
            rdoVaccCardPhoto2 = (RadioButton) findViewById(R.id.rdoVaccCardPhoto2);
            rdoVaccCardPhoto3 = (RadioButton) findViewById(R.id.rdoVaccCardPhoto3);
            rdoVaccCardPhoto4 = (RadioButton) findViewById(R.id.rdoVaccCardPhoto4);
            secEvrVaccCard=(LinearLayout)findViewById(R.id.secEvrVaccCard);
            lineEvrVaccCard=(View)findViewById(R.id.lineEvrVaccCard);
            VlblEvrVaccCard = (TextView) findViewById(R.id.VlblEvrVaccCard);
            rdogrpEvrVaccCard = (RadioGroup) findViewById(R.id.rdogrpEvrVaccCard);
            rdoEvrVaccCard1 = (RadioButton) findViewById(R.id.rdoEvrVaccCard1);
            rdoEvrVaccCard2 = (RadioButton) findViewById(R.id.rdoEvrVaccCard2);
            rdoEvrVaccCard3 = (RadioButton) findViewById(R.id.rdoEvrVaccCard3);
            rdoEvrVaccCard4 = (RadioButton) findViewById(R.id.rdoEvrVaccCard4);
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Baseline_ChildChar.this, e.getMessage());
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
                Connection.MessageBox(Baseline_ChildChar.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            ChildChar_DataModel objSave = new ChildChar_DataModel();
            objSave.setCID(txtCID.getText().toString());
            objSave.setRespID(txtRespID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setHHID(HHID);
            objSave.setChRthHead(spnChRthHead.getSelectedItemPosition() == 0 ? "" : spnChRthHead.getSelectedItem().toString().split("-")[0]);
            objSave.setRthHeadOth(txtRthHeadOth.getText().toString());
            objSave.setRthCaregiver(spnRthCaregiver.getSelectedItemPosition() == 0 ? "" : spnRthCaregiver.getSelectedItem().toString().split("-")[0]);
            objSave.setRthCaregiverOth(txtRthCaregiverOth.getText().toString());
            String[] d_rdogrpChSize = new String[] {"1","2","3","4","5","8","9"};
            objSave.setChSize("");
            for (int i = 0; i < rdogrpChSize.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpChSize.getChildAt(i);
                if (rb.isChecked()) objSave.setChSize(d_rdogrpChSize[i]);
            }

            String[] d_rdogrpChStatus = new String[] {"0","1","8","9"};
            objSave.setChStatus("");
            for (int i = 0; i < rdogrpChStatus.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpChStatus.getChildAt(i);
                if (rb.isChecked()) objSave.setChStatus(d_rdogrpChStatus[i]);
            }

            String[] d_rdogrpChDiarrhea = new String[] {"0","1","8","9"};
            objSave.setChDiarrhea("");
            for (int i = 0; i < rdogrpChDiarrhea.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpChDiarrhea.getChildAt(i);
                if (rb.isChecked()) objSave.setChDiarrhea(d_rdogrpChDiarrhea[i]);
            }

            String[] d_rdogrpSeekCareDiarrhea = new String[] {"0","1","8","9"};
            objSave.setSeekCareDiarrhea("");
            for (int i = 0; i < rdogrpSeekCareDiarrhea.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpSeekCareDiarrhea.getChildAt(i);
                if (rb.isChecked()) objSave.setSeekCareDiarrhea(d_rdogrpSeekCareDiarrhea[i]);
            }

            objSave.setGovHos((chkGovHos.isChecked() ? "1" : (secGovHos.isShown() ? "2" : "")));
            objSave.setGovHelCenter((chkGovHelCenter.isChecked() ? "1" : (secGovHelCenter.isShown() ? "2" : "")));
            objSave.setGovHelPost((chkGovHelPost.isChecked() ? "1" : (secGovHelPost.isShown() ? "2" : "")));
            objSave.setGovMobClinic((chkGovMobClinic.isChecked() ? "1" : (secGovMobClinic.isShown() ? "2" : "")));
            objSave.setPrvtHos((chkPrvtHos.isChecked() ? "1" : (secPrvtHos.isShown() ? "2" : "")));
            objSave.setPharma((chkPharma.isChecked() ? "1" : (secPharma.isShown() ? "2" : "")));
            objSave.setPrvtDoctor((chkPrvtDoctor.isChecked() ? "1" : (secPrvtDoctor.isShown() ? "2" : "")));
            objSave.setPrvtMobClinic((chkPrvtMobClinic.isChecked() ? "1" : (secPrvtMobClinic.isShown() ? "2" : "")));
            objSave.setTrdiPract((chkTrdiPract.isChecked() ? "1" : (secTrdiPract.isShown() ? "2" : "")));
            objSave.setDrugSeller((chkDrugSeller.isChecked() ? "1" : (secDrugSeller.isShown() ? "2" : "")));
            objSave.setOther((chkOther.isChecked() ? "1" : (secOther.isShown() ? "2" : "")));
            objSave.setOtherSp(txtOtherSp.getText().toString());
            objSave.setFaciDk((chkFaciDk.isChecked() ? "1" : (secFaciDk.isShown() ? "2" : "")));
            objSave.setRefused((chkRefused.isChecked() ? "1" : (secRefused.isShown() ? "2" : "")));
            String[] d_rdogrpCough = new String[] {"0","1","8","9"};
            objSave.setCough("");
            for (int i = 0; i < rdogrpCough.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpCough.getChildAt(i);
                if (rb.isChecked()) objSave.setCough(d_rdogrpCough[i]);
            }

            String[] d_rdogrpBreath = new String[] {"0","1","8","9"};
            objSave.setBreath("");
            for (int i = 0; i < rdogrpBreath.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpBreath.getChildAt(i);
                if (rb.isChecked()) objSave.setBreath(d_rdogrpBreath[i]);
            }

            String[] d_rdogrpBreathDiffC = new String[] {"1","2","3","7","8"};
            objSave.setBreathDiffC("");
            for (int i = 0; i < rdogrpBreathDiffC.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpBreathDiffC.getChildAt(i);
                if (rb.isChecked()) objSave.setBreathDiffC(d_rdogrpBreathDiffC[i]);
            }

            String[] d_rdogrpSeekCareCough = new String[] {"0","1","8","9"};
            objSave.setSeekCareCough("");
            for (int i = 0; i < rdogrpSeekCareCough.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpSeekCareCough.getChildAt(i);
                if (rb.isChecked()) objSave.setSeekCareCough(d_rdogrpSeekCareCough[i]);
            }

            objSave.setCoughCareLoc(spnCoughCareLoc.getSelectedItemPosition() == 0 ? "" : spnCoughCareLoc.getSelectedItem().toString().split("-")[0]);
            objSave.setCoughCareLocOth(txtCoughCareLocOth.getText().toString());
            String[] d_rdogrpFeverPst2W = new String[] {"0","1","8","9"};
            objSave.setFeverPst2W("");
            for (int i = 0; i < rdogrpFeverPst2W.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpFeverPst2W.getChildAt(i);
                if (rb.isChecked()) objSave.setFeverPst2W(d_rdogrpFeverPst2W[i]);
            }

            String[] d_rdogrpSeekCareFever = new String[] {"0","1","8","9"};
            objSave.setSeekCareFever("");
            for (int i = 0; i < rdogrpSeekCareFever.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpSeekCareFever.getChildAt(i);
                if (rb.isChecked()) objSave.setSeekCareFever(d_rdogrpSeekCareFever[i]);
            }

            objSave.setFeverCareLoc(spnFeverCareLoc.getSelectedItemPosition() == 0 ? "" : spnFeverCareLoc.getSelectedItem().toString().split("-")[0]);
            objSave.setFeverCareLocOth(txtFeverCareLocOth.getText().toString());
            String[] d_rdogrpWgtLost = new String[] {"0","1","8","9"};
            objSave.setWgtLost("");
            for (int i = 0; i < rdogrpWgtLost.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpWgtLost.getChildAt(i);
                if (rb.isChecked()) objSave.setWgtLost(d_rdogrpWgtLost[i]);
            }

            String[] d_rdogrpWgtGain = new String[] {"0","1","8","9"};
            objSave.setWgtGain("");
            for (int i = 0; i < rdogrpWgtGain.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpWgtGain.getChildAt(i);
                if (rb.isChecked()) objSave.setWgtGain(d_rdogrpWgtGain[i]);
            }

            String[] d_rdogrpChLessFeed = new String[] {"0","1","8","9"};
            objSave.setChLessFeed("");
            for (int i = 0; i < rdogrpChLessFeed.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpChLessFeed.getChildAt(i);
                if (rb.isChecked()) objSave.setChLessFeed(d_rdogrpChLessFeed[i]);
            }

            String[] d_rdogrpUnderWeight = new String[] {"0","1","8","9"};
            objSave.setUnderWeight("");
            for (int i = 0; i < rdogrpUnderWeight.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpUnderWeight.getChildAt(i);
                if (rb.isChecked()) objSave.setUnderWeight(d_rdogrpUnderWeight[i]);
            }

            String[] d_rdogrpOverWeight = new String[] {"0","1","8","9"};
            objSave.setOverWeight("");
            for (int i = 0; i < rdogrpOverWeight.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpOverWeight.getChildAt(i);
                if (rb.isChecked()) objSave.setOverWeight(d_rdogrpOverWeight[i]);
            }

            String[] d_rdogrpEvrVacc = new String[] {"0","1","8","9"};
            objSave.setEvrVacc("");
            for (int i = 0; i < rdogrpEvrVacc.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpEvrVacc.getChildAt(i);
                if (rb.isChecked()) objSave.setEvrVacc(d_rdogrpEvrVacc[i]);
            }

            String[] d_rdogrpVaccCard = new String[] {"0","1","8","9"};
            objSave.setVaccCard("");
            for (int i = 0; i < rdogrpVaccCard.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpVaccCard.getChildAt(i);
                if (rb.isChecked()) objSave.setVaccCard(d_rdogrpVaccCard[i]);
            }

            String[] d_rdogrpVaccCardPhoto = new String[] {"0","1","8","9"};
            objSave.setVaccCardPhoto("");
            for (int i = 0; i < rdogrpVaccCardPhoto.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpVaccCardPhoto.getChildAt(i);
                if (rb.isChecked()) objSave.setVaccCardPhoto(d_rdogrpVaccCardPhoto[i]);
            }

            String[] d_rdogrpEvrVaccCard = new String[] {"0","1","8","9"};
            objSave.setEvrVaccCard("");
            for (int i = 0; i < rdogrpEvrVaccCard.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpEvrVaccCard.getChildAt(i);
                if (rb.isChecked()) objSave.setEvrVaccCard(d_rdogrpEvrVaccCard[i]);
            }

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

                Toast.makeText(Baseline_ChildChar.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                Connection.MessageBox(Baseline_ChildChar.this, status);
                return;
            }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Baseline_ChildChar.this, e.getMessage());
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
            if(txtCID.getText().toString().length()==0 & secCID.isShown())
            {
                ValidationMsg += "\nRequired field: Clild Internal ID.";
                secCID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtRespID.getText().toString().length()==0 & secRespID.isShown())
            {
                ValidationMsg += "\nC2. Required field: Record respondents individual ID number.";
                secRespID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtMemID.getText().toString().length()==0 & secMemID.isShown())
            {
                ValidationMsg += "\nC4. Required field: Record child  individual ID number.";
                secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(spnChRthHead.getSelectedItemPosition()==0  & secChRthHead.isShown())
            {
                ValidationMsg += "\nC5. Required field: What is the relationship of the child/[name] to the household head?\n(CIRCLE ONE RESPONSE, OR WRITE RESPONSE FOR 97) *Only include this question if relationship with the household head is not asked as part of the household roster*.";
                secChRthHead.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtRthHeadOth.getText().toString().length()==0 & secRthHeadOth.isShown())
            {
                ValidationMsg += "\nRequired field: Others specify.";
                secRthHeadOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(spnRthCaregiver.getSelectedItemPosition()==0  & secRthCaregiver.isShown())
            {
                ValidationMsg += "\nC6. Required field: What is the primary caregivers relationship with the child/[name]?.";
                secRthCaregiver.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtRthCaregiverOth.getText().toString().length()==0 & secRthCaregiverOth.isShown())
            {
                ValidationMsg += "\nRequired field: Others specify.";
                secRthCaregiverOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoChSize1.isChecked() & !rdoChSize2.isChecked() & !rdoChSize3.isChecked() & !rdoChSize4.isChecked() & !rdoChSize5.isChecked() & !rdoChSize6.isChecked() & !rdoChSize7.isChecked() & secChSize.isShown())
            {
                ValidationMsg += "\nC7. Required field: When child/[name] was born, was child/[name] very small, small, average, large, or very large?.";
                secChSize.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoChStatus1.isChecked() & !rdoChStatus2.isChecked() & !rdoChStatus3.isChecked() & !rdoChStatus4.isChecked() & secChStatus.isShown())
            {
                ValidationMsg += "\nC8. Required field: Was child/[name] ill/unwell in the past two weeks?.";
                secChStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoChDiarrhea1.isChecked() & !rdoChDiarrhea2.isChecked() & !rdoChDiarrhea3.isChecked() & !rdoChDiarrhea4.isChecked() & secChDiarrhea.isShown())
            {
                ValidationMsg += "\nC9. Required field: Did the child/[name] have diarrhea (3 or more loose stools per day) in the past two weeks?.";
                secChDiarrhea.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoSeekCareDiarrhea1.isChecked() & !rdoSeekCareDiarrhea2.isChecked() & !rdoSeekCareDiarrhea3.isChecked() & !rdoSeekCareDiarrhea4.isChecked() & secSeekCareDiarrhea.isShown())
            {
                ValidationMsg += "\nC10. Required field: Did you/caregiver seek health care/treatment for the child/[name]s diarrhea?.";
                secSeekCareDiarrhea.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
//            if(spnDiarrheaCareLoc.getSelectedItemPosition()==0  & secDiarrheaCareLoc.isShown())
//            {
//                ValidationMsg += "\nC11. Required field: Where did you/caregiver seek health care/treatment for the child/[name]s diarrhea?.";
//                secDiarrheaCareLoc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//            }
//            if(txtDiarrheaCareLocOth.getText().toString().length()==0 & secDiarrheaCareLocOth.isShown())
//            {
//                ValidationMsg += "\nRequired field: Other specify.";
//                secDiarrheaCareLocOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
//            }
            if(!rdoCough1.isChecked() & !rdoCough2.isChecked() & !rdoCough3.isChecked() & !rdoCough4.isChecked() & secCough.isShown())
            {
                ValidationMsg += "\nC12. Required field: Did the child/[name] have an illness with a cough in the past two weeks?.";
                secCough.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoBreath1.isChecked() & !rdoBreath2.isChecked() & !rdoBreath3.isChecked() & !rdoBreath4.isChecked() & secBreath.isShown())
            {
                ValidationMsg += "\nC13. Required field: Did the child/[name] have fast, short, rapid breaths or difficulty breathing in the past two weeks?.";
                secBreath.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoBreathDiffC1.isChecked() & !rdoBreathDiffC2.isChecked() & !rdoBreathDiffC3.isChecked() & !rdoBreathDiffC4.isChecked() & !rdoBreathDiffC5.isChecked() & secBreathDiffC.isShown())
            {
                ValidationMsg += "\nC13.1. Required field: Was the fast or difficult breathing due to a problem in the chest or to a blocked or runny nose?.";
                secBreathDiffC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoSeekCareCough1.isChecked() & !rdoSeekCareCough2.isChecked() & !rdoSeekCareCough3.isChecked() & !rdoSeekCareCough4.isChecked() & secSeekCareCough.isShown())
            {
                ValidationMsg += "\nC14. Required field: Did you/caregiver seek health care/treatment for the child/[name]s cough?.";
                secSeekCareCough.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(spnCoughCareLoc.getSelectedItemPosition()==0  & secCoughCareLoc.isShown())
            {
                ValidationMsg += "\nC15. Required field: Where did you/caregiver seek health care/treatment for the child/[name]s cough?.";
                secCoughCareLoc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtCoughCareLocOth.getText().toString().length()==0 & secCoughCareLocOth.isShown())
            {
                ValidationMsg += "\nRequired field: Others specify.";
                secCoughCareLocOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoFeverPst2W1.isChecked() & !rdoFeverPst2W2.isChecked() & !rdoFeverPst2W3.isChecked() & !rdoFeverPst2W4.isChecked() & secFeverPst2W.isShown())
            {
                ValidationMsg += "\nC16. Required field: Did the child/[name] have a fever (was the babe unusually hot to the touch) in the past two weeks?.";
                secFeverPst2W.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoSeekCareFever1.isChecked() & !rdoSeekCareFever2.isChecked() & !rdoSeekCareFever3.isChecked() & !rdoSeekCareFever4.isChecked() & secSeekCareFever.isShown())
            {
                ValidationMsg += "\nC17. Required field: Did you/caregiver seek health care/treatment for the child/[name]s fever?.";
                secSeekCareFever.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(spnFeverCareLoc.getSelectedItemPosition()==0  & secFeverCareLoc.isShown())
            {
                ValidationMsg += "\nC18. Required field: Where did you/caregiver seek health care/treatment for the child/[name]s fever?.";
                secFeverCareLoc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtFeverCareLocOth.getText().toString().length()==0 & secFeverCareLocOth.isShown())
            {
                ValidationMsg += "\nRequired field: Other Specify.";
                secFeverCareLocOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoWgtLost1.isChecked() & !rdoWgtLost2.isChecked() & !rdoWgtLost3.isChecked() & !rdoWgtLost4.isChecked() & secWgtLost.isShown())
            {
                ValidationMsg += "\nC19. Required field: Has the child/[name] unintentionally lost weight lately?.";
                secWgtLost.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoWgtGain1.isChecked() & !rdoWgtGain2.isChecked() & !rdoWgtGain3.isChecked() & !rdoWgtGain4.isChecked() & secWgtGain.isShown())
            {
                ValidationMsg += "\nC20. Required field: Has the child/[name] had poor weight gain over the last few months?.";
                secWgtGain.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoChLessFeed1.isChecked() & !rdoChLessFeed2.isChecked() & !rdoChLessFeed3.isChecked() & !rdoChLessFeed4.isChecked() & secChLessFeed.isShown())
            {
                ValidationMsg += "\nC21. Required field: Has the child/[name] been eating/feeding less in the past two weeks?.";
                secChLessFeed.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoUnderWeight1.isChecked() & !rdoUnderWeight2.isChecked() & !rdoUnderWeight3.isChecked() & !rdoUnderWeight4.isChecked() & secUnderWeight.isShown())
            {
                ValidationMsg += "\nC22. Required field: Is the child/[name] underweight?.";
                secUnderWeight.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoOverWeight1.isChecked() & !rdoOverWeight2.isChecked() & !rdoOverWeight3.isChecked() & !rdoOverWeight4.isChecked() & secOverWeight.isShown())
            {
                ValidationMsg += "\nC23. Required field: Is the child/[name] overweight?.";
                secOverWeight.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoEvrVacc1.isChecked() & !rdoEvrVacc2.isChecked() & !rdoEvrVacc3.isChecked() & !rdoEvrVacc4.isChecked() & secEvrVacc.isShown())
            {
                ValidationMsg += "\nC23.1. Required field: Have you ever vaccinated your child/[name]?.";
                secEvrVacc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoVaccCard1.isChecked() & !rdoVaccCard2.isChecked() & !rdoVaccCard3.isChecked() & !rdoVaccCard4.isChecked() & secVaccCard.isShown())
            {
                ValidationMsg += "\nC24. Required field: Do you have a card where child/[name]s vaccinations are written down?.";
                secVaccCard.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoVaccCardPhoto1.isChecked() & !rdoVaccCardPhoto2.isChecked() & !rdoVaccCardPhoto3.isChecked() & !rdoVaccCardPhoto4.isChecked() & secVaccCardPhoto.isShown())
            {
                ValidationMsg += "\nC25. Required field: May I take a photo of the vaccination card for child/[name]?.";
                secVaccCardPhoto.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoEvrVaccCard1.isChecked() & !rdoEvrVaccCard2.isChecked() & !rdoEvrVaccCard3.isChecked() & !rdoEvrVaccCard4.isChecked() & secEvrVaccCard.isShown())
            {
                ValidationMsg += "\nC26 Required field: Did you ever have a vaccination card for child/[name]?.";
                secEvrVaccCard.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
            secCID.setBackgroundColor(Color.WHITE);
            secRespID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secChRthHead.setBackgroundColor(Color.WHITE);
            secRthHeadOth.setBackgroundColor(Color.WHITE);
            secRthCaregiver.setBackgroundColor(Color.WHITE);
            secRthCaregiverOth.setBackgroundColor(Color.WHITE);
            secChSize.setBackgroundColor(Color.WHITE);
            secChStatus.setBackgroundColor(Color.WHITE);
            secChDiarrhea.setBackgroundColor(Color.WHITE);
            secSeekCareDiarrhea.setBackgroundColor(Color.WHITE);
//            secDiarrheaCareLoc.setBackgroundColor(Color.WHITE);
//            secDiarrheaCareLocOth.setBackgroundColor(Color.WHITE);
            secCough.setBackgroundColor(Color.WHITE);
            secBreath.setBackgroundColor(Color.WHITE);
            secBreathDiffC.setBackgroundColor(Color.WHITE);
            secSeekCareCough.setBackgroundColor(Color.WHITE);
            secCoughCareLoc.setBackgroundColor(Color.WHITE);
            secCoughCareLocOth.setBackgroundColor(Color.WHITE);
            secFeverPst2W.setBackgroundColor(Color.WHITE);
            secSeekCareFever.setBackgroundColor(Color.WHITE);
            secFeverCareLoc.setBackgroundColor(Color.WHITE);
            secFeverCareLocOth.setBackgroundColor(Color.WHITE);
            secWgtLost.setBackgroundColor(Color.WHITE);
            secWgtGain.setBackgroundColor(Color.WHITE);
            secChLessFeed.setBackgroundColor(Color.WHITE);
            secUnderWeight.setBackgroundColor(Color.WHITE);
            secOverWeight.setBackgroundColor(Color.WHITE);
            secEvrVacc.setBackgroundColor(Color.WHITE);
            secVaccCard.setBackgroundColor(Color.WHITE);
            secVaccCardPhoto.setBackgroundColor(Color.WHITE);
            secEvrVaccCard.setBackgroundColor(Color.WHITE);
        }
        catch(Exception  e)
        {
        }
    }

    private void DataSearch(String MemId)
    {
        try
        {
            RadioButton rb;
            ChildChar_DataModel d = new ChildChar_DataModel();
            String SQL = "Select * from "+ TableName +"  Where MemID='"+ MemId +"'";
            List<ChildChar_DataModel> data = d.SelectAll(this, SQL);
            for(ChildChar_DataModel item : data){
                txtCID.setText(item.getCID());
                txtRespID.setText(item.getRespID());
                txtMemID.setText(item.getMemID());
                spnChRthHead.setSelection(Global.SpinnerItemPositionAnyLength(spnChRthHead, String.valueOf(item.getChRthHead())));
                txtRthHeadOth.setText(item.getRthHeadOth());
                spnRthCaregiver.setSelection(Global.SpinnerItemPositionAnyLength(spnRthCaregiver, String.valueOf(item.getRthCaregiver())));
                txtRthCaregiverOth.setText(item.getRthCaregiverOth());
                String[] d_rdogrpChSize = new String[] {"1","2","3","4","5","8","9"};
                for (int i = 0; i < d_rdogrpChSize.length; i++)
                {
                    if (String.valueOf(item.getChSize()).equals(String.valueOf(d_rdogrpChSize[i])))
                    {
                        rb = (RadioButton)rdogrpChSize.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpChStatus = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpChStatus.length; i++)
                {
                    if (String.valueOf(item.getChStatus()).equals(String.valueOf(d_rdogrpChStatus[i])))
                    {
                        rb = (RadioButton)rdogrpChStatus.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpChDiarrhea = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpChDiarrhea.length; i++)
                {
                    if (String.valueOf(item.getChDiarrhea()).equals(String.valueOf(d_rdogrpChDiarrhea[i])))
                    {
                        rb = (RadioButton)rdogrpChDiarrhea.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSeekCareDiarrhea = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpSeekCareDiarrhea.length; i++)
                {
                    if (String.valueOf(item.getSeekCareDiarrhea()).equals(String.valueOf(d_rdogrpSeekCareDiarrhea[i])))
                    {
                        rb = (RadioButton)rdogrpSeekCareDiarrhea.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
//                spnDiarrheaCareLoc.setSelection(Global.SpinnerItemPositionAnyLength(spnDiarrheaCareLoc, String.valueOf(item.getDiarrheaCareLoc())));
//                txtDiarrheaCareLocOth.setText(item.getDiarrheaCareLocOth());

                if(String.valueOf(item.getGovHos()).equals("1"))
                {
                    chkGovHos.setChecked(true);
                }
                else if(String.valueOf(item.getGovHos()).equals("2"))
                {
                    chkGovHos.setChecked(false);
                }
                if(String.valueOf(item.getGovHelCenter()).equals("1"))
                {
                    chkGovHelCenter.setChecked(true);
                }
                else if(String.valueOf(item.getGovHelCenter()).equals("2"))
                {
                    chkGovHelCenter.setChecked(false);
                }
                if(String.valueOf(item.getGovHelPost()).equals("1"))
                {
                    chkGovHelPost.setChecked(true);
                }
                else if(String.valueOf(item.getGovHelPost()).equals("2"))
                {
                    chkGovHelPost.setChecked(false);
                }
                if(String.valueOf(item.getGovMobClinic()).equals("1"))
                {
                    chkGovMobClinic.setChecked(true);
                }
                else if(String.valueOf(item.getGovMobClinic()).equals("2"))
                {
                    chkGovMobClinic.setChecked(false);
                }
                if(String.valueOf(item.getPrvtHos()).equals("1"))
                {
                    chkPrvtHos.setChecked(true);
                }
                else if(String.valueOf(item.getPrvtHos()).equals("2"))
                {
                    chkPrvtHos.setChecked(false);
                }
                if(String.valueOf(item.getPharma()).equals("1"))
                {
                    chkPharma.setChecked(true);
                }
                else if(String.valueOf(item.getPharma()).equals("2"))
                {
                    chkPharma.setChecked(false);
                }
                if(String.valueOf(item.getPrvtDoctor()).equals("1"))
                {
                    chkPrvtDoctor.setChecked(true);
                }
                else if(String.valueOf(item.getPrvtDoctor()).equals("2"))
                {
                    chkPrvtDoctor.setChecked(false);
                }
                if(String.valueOf(item.getPrvtMobClinic()).equals("1"))
                {
                    chkPrvtMobClinic.setChecked(true);
                }
                else if(String.valueOf(item.getPrvtMobClinic()).equals("2"))
                {
                    chkPrvtMobClinic.setChecked(false);
                }
                if(String.valueOf(item.getTrdiPract()).equals("1"))
                {
                    chkTrdiPract.setChecked(true);
                }
                else if(String.valueOf(item.getTrdiPract()).equals("2"))
                {
                    chkTrdiPract.setChecked(false);
                }
                if(String.valueOf(item.getDrugSeller()).equals("1"))
                {
                    chkDrugSeller.setChecked(true);
                }
                else if(String.valueOf(item.getDrugSeller()).equals("2"))
                {
                    chkDrugSeller.setChecked(false);
                }
                if(String.valueOf(item.getOther()).equals("1"))
                {
                    chkOther.setChecked(true);
                }
                else if(String.valueOf(item.getOther()).equals("2"))
                {
                    chkOther.setChecked(false);
                }
                txtOtherSp.setText(item.getOtherSp());
                if(String.valueOf(item.getFaciDk()).equals("1"))
                {
                    chkFaciDk.setChecked(true);
                }
                else if(String.valueOf(item.getFaciDk()).equals("2"))
                {
                    chkFaciDk.setChecked(false);
                }
                if(String.valueOf(item.getRefused()).equals("1"))
                {
                    chkRefused.setChecked(true);
                }
                else if(String.valueOf(item.getRefused()).equals("2"))
                {
                    chkRefused.setChecked(false);
                }

                String[] d_rdogrpCough = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpCough.length; i++)
                {
                    if (String.valueOf(item.getCough()).equals(String.valueOf(d_rdogrpCough[i])))
                    {
                        rb = (RadioButton)rdogrpCough.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpBreath = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpBreath.length; i++)
                {
                    if (String.valueOf(item.getBreath()).equals(String.valueOf(d_rdogrpBreath[i])))
                    {
                        rb = (RadioButton)rdogrpBreath.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpBreathDiffC = new String[] {"1","2","3","7","8"};
                for (int i = 0; i < d_rdogrpBreathDiffC.length; i++)
                {
                    if (String.valueOf(item.getBreathDiffC()).equals(String.valueOf(d_rdogrpBreathDiffC[i])))
                    {
                        rb = (RadioButton)rdogrpBreathDiffC.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSeekCareCough = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpSeekCareCough.length; i++)
                {
                    if (String.valueOf(item.getSeekCareCough()).equals(String.valueOf(d_rdogrpSeekCareCough[i])))
                    {
                        rb = (RadioButton)rdogrpSeekCareCough.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnCoughCareLoc.setSelection(Global.SpinnerItemPositionAnyLength(spnCoughCareLoc, String.valueOf(item.getCoughCareLoc())));
                txtCoughCareLocOth.setText(item.getCoughCareLocOth());
                String[] d_rdogrpFeverPst2W = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpFeverPst2W.length; i++)
                {
                    if (String.valueOf(item.getFeverPst2W()).equals(String.valueOf(d_rdogrpFeverPst2W[i])))
                    {
                        rb = (RadioButton)rdogrpFeverPst2W.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSeekCareFever = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpSeekCareFever.length; i++)
                {
                    if (String.valueOf(item.getSeekCareFever()).equals(String.valueOf(d_rdogrpSeekCareFever[i])))
                    {
                        rb = (RadioButton)rdogrpSeekCareFever.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnFeverCareLoc.setSelection(Global.SpinnerItemPositionAnyLength(spnFeverCareLoc, String.valueOf(item.getFeverCareLoc())));
                txtFeverCareLocOth.setText(item.getFeverCareLocOth());
                String[] d_rdogrpWgtLost = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpWgtLost.length; i++)
                {
                    if (String.valueOf(item.getWgtLost()).equals(String.valueOf(d_rdogrpWgtLost[i])))
                    {
                        rb = (RadioButton)rdogrpWgtLost.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpWgtGain = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpWgtGain.length; i++)
                {
                    if (String.valueOf(item.getWgtGain()).equals(String.valueOf(d_rdogrpWgtGain[i])))
                    {
                        rb = (RadioButton)rdogrpWgtGain.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpChLessFeed = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpChLessFeed.length; i++)
                {
                    if (String.valueOf(item.getChLessFeed()).equals(String.valueOf(d_rdogrpChLessFeed[i])))
                    {
                        rb = (RadioButton)rdogrpChLessFeed.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpUnderWeight = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpUnderWeight.length; i++)
                {
                    if (String.valueOf(item.getUnderWeight()).equals(String.valueOf(d_rdogrpUnderWeight[i])))
                    {
                        rb = (RadioButton)rdogrpUnderWeight.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpOverWeight = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpOverWeight.length; i++)
                {
                    if (String.valueOf(item.getOverWeight()).equals(String.valueOf(d_rdogrpOverWeight[i])))
                    {
                        rb = (RadioButton)rdogrpOverWeight.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpEvrVacc = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpEvrVacc.length; i++)
                {
                    if (String.valueOf(item.getEvrVacc()).equals(String.valueOf(d_rdogrpEvrVacc[i])))
                    {
                        rb = (RadioButton)rdogrpEvrVacc.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpVaccCard = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpVaccCard.length; i++)
                {
                    if (String.valueOf(item.getVaccCard()).equals(String.valueOf(d_rdogrpVaccCard[i])))
                    {
                        rb = (RadioButton)rdogrpVaccCard.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpVaccCardPhoto = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpVaccCardPhoto.length; i++)
                {
                    if (String.valueOf(item.getVaccCardPhoto()).equals(String.valueOf(d_rdogrpVaccCardPhoto[i])))
                    {
                        rb = (RadioButton)rdogrpVaccCardPhoto.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpEvrVaccCard = new String[] {"0","1","8","9"};
                for (int i = 0; i < d_rdogrpEvrVaccCard.length; i++)
                {
                    if (String.valueOf(item.getEvrVaccCard()).equals(String.valueOf(d_rdogrpEvrVaccCard[i])))
                    {
                        rb = (RadioButton)rdogrpEvrVaccCard.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
            }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Baseline_ChildChar.this, e.getMessage());
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