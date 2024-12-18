
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import forms_datamodel.NBC_NBCareDetail_DataModel;

public class NBC_NBCareDetail extends AppCompatActivity {
    //Disabled Back/Home key
    //--------------------------------------------------------------------------------------------------
    @Override
    public boolean onKeyDown(int iKeyCode, KeyEvent event) {
        if (iKeyCode == KeyEvent.KEYCODE_BACK || iKeyCode == KeyEvent.KEYCODE_HOME) {
            return false;
        } else {
            return true;
        }
    }

    boolean networkAvailable = false;
    Location currentLocation;
    double currentLatitude, currentLongitude;
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
    LinearLayout secNBID;
    View lineNBID;
    TextView VlblNBID;
    EditText txtNBID;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secPGN;
    View linePGN;
    TextView VlblPGN;
    EditText txtPGN;
    LinearLayout secChSl;
    View lineChSl;
    TextView VlblChSl;
    EditText txtChSl;
    LinearLayout secPregType;
    View linePregType;
    TextView VlblPregType;
    RadioGroup rdogrpPregType;
    RadioButton rdoPregType1;
    RadioButton rdoPregType2;
    RadioButton rdoPregType3;
    RadioButton rdoPregType4;
    RadioButton rdoPregType5;
    RadioButton rdoPregType6;
    RadioButton rdoPregType7;
    LinearLayout secOutcomeDate;
    View lineOutcomeDate;
    TextView VlblOutcomeDate;
    EditText dtpOutcomeDate;
    LinearLayout secOutcomeDateDk;
    View lineOutcomeDateDk;
    TextView VlblOutcomeDateDk;
    RadioGroup rdogrpOutcomeDateDk;
    RadioButton rdoOutcomeDateDk1;
    RadioButton rdoOutcomeDateDk2;
    LinearLayout secOutcomeTime;
    View lineOutcomeTime;
    TextView VlblOutcomeTime;
    EditText txtOutcomeTime;
    LinearLayout secOutcomeTimeDk;
    View lineOutcomeTimeDk;
    TextView VlblOutcomeTimeDk;
    RadioGroup rdogrpOutcomeTimeDk;
    RadioButton rdoOutcomeTimeDk1;
    RadioButton rdoOutcomeTimeDk2;
    LinearLayout secOutcomeType;
    View lineOutcomeType;
    TextView VlblOutcomeType;
    RadioGroup rdogrpOutcomeType;
    RadioButton rdoOutcomeType1;
    RadioButton rdoOutcomeType2;
    LinearLayout secCryMoveBreathe;
    View lineCryMoveBreathe;
    TextView VlblCryMoveBreathe;
    RadioGroup rdogrpCryMoveBreathe;
    RadioButton rdoCryMoveBreathe1;
    RadioButton rdoCryMoveBreathe2;
    RadioButton rdoCryMoveBreathe3;
    RadioButton rdoCryMoveBreathe4;
    LinearLayout secChildName;
    View lineChildName;
    TextView VlblChildName;
    EditText txtChildName;
    LinearLayout secChildNameDk;
    View lineChildNameDk;
    TextView VlblChildNameDk;
    RadioGroup rdogrpChildNameDk;
    RadioButton rdoChildNameDk1;
    RadioButton rdoChildNameDk2;
    LinearLayout secChildID;
    View lineChildID;
    TextView VlblChildID;
    EditText txtChildID;
    LinearLayout secChildSex;
    View lineChildSex;
    TextView VlblChildSex;
    RadioGroup rdogrpChildSex;
    RadioButton rdoChildSex1;
    RadioButton rdoChildSex2;
    RadioButton rdoChildSex3;
    RadioButton rdoChildSex4;
    RadioButton rdoChildSex5;
    LinearLayout secBirthTiming;
    View lineBirthTiming;
    TextView VlblBirthTiming;
    RadioGroup rdogrpBirthTiming;
    RadioButton rdoBirthTiming1;
    RadioButton rdoBirthTiming2;
    RadioButton rdoBirthTiming3;
    RadioButton rdoBirthTiming4;
    RadioButton rdoBirthTiming5;
    LinearLayout secPregWeek;
    View linePregWeek;
    TextView VlblPregWeek;
    EditText txtPregWeek;
    LinearLayout secPregWeekDk;
    View linePregWeekDk;
    TextView VlblPregWeekDk;
    RadioGroup rdogrpPregWeekDk;
    RadioButton rdoPregWeekDk1;
    RadioButton rdoPregWeekDk2;
    LinearLayout secExpDOB;
    View lineExpDOB;
    TextView VlblExpDOB;
    EditText dtpExpDOB;
    LinearLayout secExpDOBDk;
    View lineExpDOBDk;
    TextView VlblExpDOBDk;
    RadioGroup rdogrpExpDOBDk;
    RadioButton rdoExpDOBDk1;
    RadioButton rdoExpDOBDk2;
    LinearLayout secChildDOB;
    View lineChildDOB;
    TextView VlblChildDOB;
    EditText dtpChildDOB;
    LinearLayout secChildDOBDk;
    View lineChildDOBDk;
    TextView VlblChildDOBDk;
    RadioGroup rdogrpChildDOBDk;
    RadioButton rdoChildDOBDk1;
    RadioButton rdoChildDOBDk2;
    LinearLayout secBPlace;
    View lineBPlace;
    TextView VlblBPlace;
    Spinner spnBPlace;
    LinearLayout secBPlaceOth;
    View lineBPlaceOth;
    TextView VlblBPlaceOth;
    AutoCompleteTextView txtBPlaceOth;
    LinearLayout secFaciName;
    View lineFaciName;
    TextView VlblFaciName;
    EditText txtFaciName;
    LinearLayout secFaciNameDk;
    View lineFaciNameDk;
    TextView VlblFaciNameDk;
    RadioGroup rdogrpFaciNameDk;
    RadioButton rdoFaciNameDk1;
    RadioButton rdoFaciNameDk2;
    LinearLayout seclbl04;
    View linelbl04;
    LinearLayout secDelAtndDoc;
    View lineDelAtndDoc;
    TextView VlblDelAtndDoc;
    CheckBox chkDelAtndDoc;
    LinearLayout secDelAtndNurse;
    View lineDelAtndNurse;
    TextView VlblDelAtndNurse;
    CheckBox chkDelAtndNurse;
    LinearLayout secDelAtndMidwife;
    View lineDelAtndMidwife;
    TextView VlblDelAtndMidwife;
    CheckBox chkDelAtndMidwife;
    LinearLayout secDelAtndTBA;
    View lineDelAtndTBA;
    TextView VlblDelAtndTBA;
    CheckBox chkDelAtndTBA;
    LinearLayout secDelAtndCHW;
    View lineDelAtndCHW;
    TextView VlblDelAtndCHW;
    CheckBox chkDelAtndCHW;
    LinearLayout secDelAtndRel;
    View lineDelAtndRel;
    TextView VlblDelAtndRel;
    CheckBox chkDelAtndRel;
    LinearLayout secDelAtndNon;
    View lineDelAtndNon;
    TextView VlblDelAtndNon;
    CheckBox chkDelAtndNon;
    LinearLayout secDelAtndOth;
    View lineDelAtndOth;
    TextView VlblDelAtndOth;
    CheckBox chkDelAtndOth;
    LinearLayout secDelAtndOthSp;
    View lineDelAtndOthSp;
    TextView VlblDelAtndOthSp;
    AutoCompleteTextView txtDelAtndOthSp;
    LinearLayout secDelAtndDK;
    View lineDelAtndDK;
    TextView VlblDelAtndDK;
    CheckBox chkDelAtndDK;
    LinearLayout secDelAtndRef;
    View lineDelAtndRef;
    TextView VlblDelAtndRef;
    CheckBox chkDelAtndRef;
    LinearLayout secBabySize;
    View lineBabySize;
    TextView VlblBabySize;
    RadioGroup rdogrpBabySize;
    RadioButton rdoBabySize1;
    RadioButton rdoBabySize2;
    RadioButton rdoBabySize3;
    RadioButton rdoBabySize4;
    RadioButton rdoBabySize5;
    RadioButton rdoBabySize6;
    RadioButton rdoBabySize7;
    LinearLayout secWgtMsrd;
    View lineWgtMsrd;
    TextView VlblWgtMsrd;
    RadioGroup rdogrpWgtMsrd;
    RadioButton rdoWgtMsrd1;
    RadioButton rdoWgtMsrd2;
    RadioButton rdoWgtMsrd3;
    RadioButton rdoWgtMsrd4;
    LinearLayout secWgtMsrdUnt;
    View lineWgtMsrdUnt;
    TextView VlblWgtMsrdUnt;
    RadioGroup rdogrpWgtMsrdUnt;
    RadioButton rdoWgtMsrdUnt1;
    RadioButton rdoWgtMsrdUnt2;
    LinearLayout secWgtPound;
    View lineWgtPound;
    TextView VlblWgtPound;
    EditText txtWgtPound;
    LinearLayout secWgtGm;
    View lineWgtGm;
    TextView VlblWgtGm;
    EditText txtWgtGm;
    LinearLayout secWgtDk;
    View lineWgtDk;
    TextView VlblWgtDk;
    RadioGroup rdogrpWgtDk;
    RadioButton rdoWgtDk1;
    RadioButton rdoWgtDk2;
    LinearLayout secWgtDtSrc;
    View lineWgtDtSrc;
    TextView VlblWgtDtSrc;
    RadioGroup rdogrpWgtDtSrc;
    RadioButton rdoWgtDtSrc1;
    RadioButton rdoWgtDtSrc2;
    LinearLayout secBFStatus;
    View lineBFStatus;
    TextView VlblBFStatus;
    RadioGroup rdogrpBFStatus;
    RadioButton rdoBFStatus1;
    RadioButton rdoBFStatus2;
    RadioButton rdoBFStatus3;
    RadioButton rdoBFStatus4;
    LinearLayout secBFTime;
    View lineBFTime;
    TextView VlblBFTime;
    RadioGroup rdogrpBFTime;
    RadioButton rdoBFTime1;
    RadioButton rdoBFTime2;
    RadioButton rdoBFTime3;
    RadioButton rdoBFTime4;
    RadioButton rdoBFTime5;
    RadioButton rdoBFTime6;
    LinearLayout secBFTimeDur;
    View lineBFTimeDur;
    TextView VlblBFTimeDur;
    EditText txtBFTimeDur;
    LinearLayout secBFTimeOth;
    View lineBFTimeOth;
    TextView VlblBFTimeOth;
    AutoCompleteTextView txtBFTimeOth;
    LinearLayout secNBRthHead;
    View lineNBRthHead;
    TextView VlblNBRthHead;
    Spinner spnNBRthHead;
    LinearLayout secNBRthHeadOth;
    View lineNBRthHeadOth;
    TextView VlblNBRthHeadOth;
    AutoCompleteTextView txtNBRthHeadOth;

     int MODULEID = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String NBID = "";
     String MEMID = "";
     String HHID = "";
     String ROUND = "";
     String TOTALOUTCOME = "";
     String CHSL = "";
     String PGN = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.nbc_nbcaredetail);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            NBID = IDbundle.getString("NBID");
            PGN = IDbundle.getString("PGN");
            MEMID = IDbundle.getString("MEMID");
            HHID = IDbundle.getString("HHID");
            ROUND = IDbundle.getString("round");
            TOTALOUTCOME = IDbundle.getString("totalOutcome");
            CHSL = IDbundle.getString("childSl");

            TableName = "NBC_NBCareDetail";
            MODULEID = 41;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(NBC_NBCareDetail.this);
                    adb.setTitle("Close");
                    adb.setIcon(R.drawable.favicon);
                    adb.setMessage("Do you want to close this form[Yes/No]?");
                    adb.setNegativeButton("No", null);
                    adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    adb.show();
                }
            });

            Initialization();
            Connection.LocalizeLanguage(NBC_NBCareDetail.this, MODULEID, LANGUAGEID);


            dtpOutcomeDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnOutcomeDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });
            dtpExpDOB.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnExpDOB";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });
            dtpChildDOB.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnChildDOB";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            txtOutcomeTime.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnOutcomeTime";
                        showDialog(TIME_DIALOG);
                        return true;
                    }
                    return false;
                }
            });

            //Hide all skip variables
            secOutcomeDate.setVisibility(View.GONE);
            lineOutcomeDate.setVisibility(View.GONE);
            secOutcomeDateDk.setVisibility(View.GONE);
            lineOutcomeDateDk.setVisibility(View.GONE);
            secOutcomeTime.setVisibility(View.GONE);
            lineOutcomeTime.setVisibility(View.GONE);
            secOutcomeTimeDk.setVisibility(View.GONE);
            lineOutcomeTimeDk.setVisibility(View.GONE);
            secOutcomeType.setVisibility(View.GONE);
            lineOutcomeType.setVisibility(View.GONE);
            secCryMoveBreathe.setVisibility(View.GONE);
            lineCryMoveBreathe.setVisibility(View.GONE);
            secChildName.setVisibility(View.GONE);
            lineChildName.setVisibility(View.GONE);
            secChildNameDk.setVisibility(View.GONE);
            lineChildNameDk.setVisibility(View.GONE);
            secChildID.setVisibility(View.GONE);
            lineChildID.setVisibility(View.GONE);
            secChildSex.setVisibility(View.GONE);
            lineChildSex.setVisibility(View.GONE);
            secBirthTiming.setVisibility(View.GONE);
            lineBirthTiming.setVisibility(View.GONE);
            secPregWeek.setVisibility(View.GONE);
            linePregWeek.setVisibility(View.GONE);
            secPregWeekDk.setVisibility(View.GONE);
            linePregWeekDk.setVisibility(View.GONE);
            secExpDOB.setVisibility(View.GONE);
            lineExpDOB.setVisibility(View.GONE);
            secExpDOBDk.setVisibility(View.GONE);
            lineExpDOBDk.setVisibility(View.GONE);
            secChildDOB.setVisibility(View.GONE);
            lineChildDOB.setVisibility(View.GONE);
            secChildDOBDk.setVisibility(View.GONE);
            lineChildDOBDk.setVisibility(View.GONE);
            secBPlace.setVisibility(View.GONE);
            lineBPlace.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secFaciName.setVisibility(View.GONE);
            lineFaciName.setVisibility(View.GONE);
            secFaciNameDk.setVisibility(View.GONE);
            lineFaciNameDk.setVisibility(View.GONE);
            seclbl04.setVisibility(View.GONE);
            linelbl04.setVisibility(View.GONE);
            secDelAtndDoc.setVisibility(View.GONE);
            lineDelAtndDoc.setVisibility(View.GONE);
            secDelAtndNurse.setVisibility(View.GONE);
            lineDelAtndNurse.setVisibility(View.GONE);
            secDelAtndMidwife.setVisibility(View.GONE);
            lineDelAtndMidwife.setVisibility(View.GONE);
            secDelAtndTBA.setVisibility(View.GONE);
            lineDelAtndTBA.setVisibility(View.GONE);
            secDelAtndCHW.setVisibility(View.GONE);
            lineDelAtndCHW.setVisibility(View.GONE);
            secDelAtndRel.setVisibility(View.GONE);
            lineDelAtndRel.setVisibility(View.GONE);
            secDelAtndNon.setVisibility(View.GONE);
            lineDelAtndNon.setVisibility(View.GONE);
            secDelAtndOth.setVisibility(View.GONE);
            lineDelAtndOth.setVisibility(View.GONE);
            secDelAtndOthSp.setVisibility(View.GONE);
            lineDelAtndOthSp.setVisibility(View.GONE);
            secDelAtndDK.setVisibility(View.GONE);
            lineDelAtndDK.setVisibility(View.GONE);
            secDelAtndRef.setVisibility(View.GONE);
            lineDelAtndRef.setVisibility(View.GONE);
            secBabySize.setVisibility(View.GONE);
            lineBabySize.setVisibility(View.GONE);
            secWgtMsrd.setVisibility(View.GONE);
            lineWgtMsrd.setVisibility(View.GONE);
            secWgtMsrdUnt.setVisibility(View.GONE);
            lineWgtMsrdUnt.setVisibility(View.GONE);
            secWgtPound.setVisibility(View.GONE);
            lineWgtPound.setVisibility(View.GONE);
            secWgtGm.setVisibility(View.GONE);
            lineWgtGm.setVisibility(View.GONE);
            secWgtDk.setVisibility(View.GONE);
            lineWgtDk.setVisibility(View.GONE);
            secWgtDtSrc.setVisibility(View.GONE);
            lineWgtDtSrc.setVisibility(View.GONE);
            secBFStatus.setVisibility(View.GONE);
            lineBFStatus.setVisibility(View.GONE);
            secBFTime.setVisibility(View.GONE);
            lineBFTime.setVisibility(View.GONE);
            secBFTimeDur.setVisibility(View.GONE);
            lineBFTimeDur.setVisibility(View.GONE);
            secBFTimeOth.setVisibility(View.GONE);
            lineBFTimeOth.setVisibility(View.GONE);
            secNBRthHead.setVisibility(View.GONE);
            lineNBRthHead.setVisibility(View.GONE);
            secNBRthHeadOth.setVisibility(View.GONE);
            lineNBRthHeadOth.setVisibility(View.GONE);
            secOutcomeDate.setVisibility(View.GONE);
            lineOutcomeDate.setVisibility(View.GONE);
            secOutcomeDateDk.setVisibility(View.GONE);
            lineOutcomeDateDk.setVisibility(View.GONE);
            secOutcomeTime.setVisibility(View.GONE);
            lineOutcomeTime.setVisibility(View.GONE);
            secOutcomeTimeDk.setVisibility(View.GONE);
            lineOutcomeTimeDk.setVisibility(View.GONE);
            secOutcomeType.setVisibility(View.GONE);
            lineOutcomeType.setVisibility(View.GONE);
            secCryMoveBreathe.setVisibility(View.GONE);
            lineCryMoveBreathe.setVisibility(View.GONE);
            secChildName.setVisibility(View.GONE);
            lineChildName.setVisibility(View.GONE);
            secChildNameDk.setVisibility(View.GONE);
            lineChildNameDk.setVisibility(View.GONE);
            secChildID.setVisibility(View.GONE);
            lineChildID.setVisibility(View.GONE);
            secChildSex.setVisibility(View.GONE);
            lineChildSex.setVisibility(View.GONE);
            secBirthTiming.setVisibility(View.GONE);
            lineBirthTiming.setVisibility(View.GONE);
            secPregWeek.setVisibility(View.GONE);
            linePregWeek.setVisibility(View.GONE);
            secPregWeekDk.setVisibility(View.GONE);
            linePregWeekDk.setVisibility(View.GONE);
            secExpDOB.setVisibility(View.GONE);
            lineExpDOB.setVisibility(View.GONE);
            secExpDOBDk.setVisibility(View.GONE);
            lineExpDOBDk.setVisibility(View.GONE);
            secChildDOB.setVisibility(View.GONE);
            lineChildDOB.setVisibility(View.GONE);
            secChildDOBDk.setVisibility(View.GONE);
            lineChildDOBDk.setVisibility(View.GONE);
            secBPlace.setVisibility(View.GONE);
            lineBPlace.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secFaciName.setVisibility(View.GONE);
            lineFaciName.setVisibility(View.GONE);
            secFaciNameDk.setVisibility(View.GONE);
            lineFaciNameDk.setVisibility(View.GONE);
            seclbl04.setVisibility(View.GONE);
            linelbl04.setVisibility(View.GONE);
            secDelAtndDoc.setVisibility(View.GONE);
            lineDelAtndDoc.setVisibility(View.GONE);
            secDelAtndNurse.setVisibility(View.GONE);
            lineDelAtndNurse.setVisibility(View.GONE);
            secDelAtndMidwife.setVisibility(View.GONE);
            lineDelAtndMidwife.setVisibility(View.GONE);
            secDelAtndTBA.setVisibility(View.GONE);
            lineDelAtndTBA.setVisibility(View.GONE);
            secDelAtndCHW.setVisibility(View.GONE);
            lineDelAtndCHW.setVisibility(View.GONE);
            secDelAtndRel.setVisibility(View.GONE);
            lineDelAtndRel.setVisibility(View.GONE);
            secDelAtndNon.setVisibility(View.GONE);
            lineDelAtndNon.setVisibility(View.GONE);
            secDelAtndOth.setVisibility(View.GONE);
            lineDelAtndOth.setVisibility(View.GONE);
            secDelAtndOthSp.setVisibility(View.GONE);
            lineDelAtndOthSp.setVisibility(View.GONE);
            secDelAtndDK.setVisibility(View.GONE);
            lineDelAtndDK.setVisibility(View.GONE);
            secDelAtndRef.setVisibility(View.GONE);
            lineDelAtndRef.setVisibility(View.GONE);
            secBabySize.setVisibility(View.GONE);
            lineBabySize.setVisibility(View.GONE);
            secWgtMsrd.setVisibility(View.GONE);
            lineWgtMsrd.setVisibility(View.GONE);
            secWgtMsrdUnt.setVisibility(View.GONE);
            lineWgtMsrdUnt.setVisibility(View.GONE);
            secWgtPound.setVisibility(View.GONE);
            lineWgtPound.setVisibility(View.GONE);
            secWgtGm.setVisibility(View.GONE);
            lineWgtGm.setVisibility(View.GONE);
            secWgtDk.setVisibility(View.GONE);
            lineWgtDk.setVisibility(View.GONE);
            secWgtDtSrc.setVisibility(View.GONE);
            lineWgtDtSrc.setVisibility(View.GONE);
            secBFStatus.setVisibility(View.GONE);
            lineBFStatus.setVisibility(View.GONE);
            secBFTime.setVisibility(View.GONE);
            lineBFTime.setVisibility(View.GONE);
            secBFTimeDur.setVisibility(View.GONE);
            lineBFTimeDur.setVisibility(View.GONE);
            secBFTimeOth.setVisibility(View.GONE);
            lineBFTimeOth.setVisibility(View.GONE);
            secNBRthHead.setVisibility(View.GONE);
            lineNBRthHead.setVisibility(View.GONE);
            secNBRthHeadOth.setVisibility(View.GONE);
            lineNBRthHeadOth.setVisibility(View.GONE);
            secOutcomeDate.setVisibility(View.GONE);
            lineOutcomeDate.setVisibility(View.GONE);
            secOutcomeDateDk.setVisibility(View.GONE);
            lineOutcomeDateDk.setVisibility(View.GONE);
            secOutcomeTime.setVisibility(View.GONE);
            lineOutcomeTime.setVisibility(View.GONE);
            secOutcomeTimeDk.setVisibility(View.GONE);
            lineOutcomeTimeDk.setVisibility(View.GONE);
            secOutcomeType.setVisibility(View.GONE);
            lineOutcomeType.setVisibility(View.GONE);
            secCryMoveBreathe.setVisibility(View.GONE);
            lineCryMoveBreathe.setVisibility(View.GONE);
            secChildName.setVisibility(View.GONE);
            lineChildName.setVisibility(View.GONE);
            secChildNameDk.setVisibility(View.GONE);
            lineChildNameDk.setVisibility(View.GONE);
            secChildID.setVisibility(View.GONE);
            lineChildID.setVisibility(View.GONE);
            secChildSex.setVisibility(View.GONE);
            lineChildSex.setVisibility(View.GONE);
            secBirthTiming.setVisibility(View.GONE);
            lineBirthTiming.setVisibility(View.GONE);
            secPregWeek.setVisibility(View.GONE);
            linePregWeek.setVisibility(View.GONE);
            secPregWeekDk.setVisibility(View.GONE);
            linePregWeekDk.setVisibility(View.GONE);
            secExpDOB.setVisibility(View.GONE);
            lineExpDOB.setVisibility(View.GONE);
            secExpDOBDk.setVisibility(View.GONE);
            lineExpDOBDk.setVisibility(View.GONE);
            secChildDOB.setVisibility(View.GONE);
            lineChildDOB.setVisibility(View.GONE);
            secChildDOBDk.setVisibility(View.GONE);
            lineChildDOBDk.setVisibility(View.GONE);
            secBPlace.setVisibility(View.GONE);
            lineBPlace.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secFaciName.setVisibility(View.GONE);
            lineFaciName.setVisibility(View.GONE);
            secFaciNameDk.setVisibility(View.GONE);
            lineFaciNameDk.setVisibility(View.GONE);
            seclbl04.setVisibility(View.GONE);
            linelbl04.setVisibility(View.GONE);
            secDelAtndDoc.setVisibility(View.GONE);
            lineDelAtndDoc.setVisibility(View.GONE);
            secDelAtndNurse.setVisibility(View.GONE);
            lineDelAtndNurse.setVisibility(View.GONE);
            secDelAtndMidwife.setVisibility(View.GONE);
            lineDelAtndMidwife.setVisibility(View.GONE);
            secDelAtndTBA.setVisibility(View.GONE);
            lineDelAtndTBA.setVisibility(View.GONE);
            secDelAtndCHW.setVisibility(View.GONE);
            lineDelAtndCHW.setVisibility(View.GONE);
            secDelAtndRel.setVisibility(View.GONE);
            lineDelAtndRel.setVisibility(View.GONE);
            secDelAtndNon.setVisibility(View.GONE);
            lineDelAtndNon.setVisibility(View.GONE);
            secDelAtndOth.setVisibility(View.GONE);
            lineDelAtndOth.setVisibility(View.GONE);
            secDelAtndOthSp.setVisibility(View.GONE);
            lineDelAtndOthSp.setVisibility(View.GONE);
            secDelAtndDK.setVisibility(View.GONE);
            lineDelAtndDK.setVisibility(View.GONE);
            secDelAtndRef.setVisibility(View.GONE);
            lineDelAtndRef.setVisibility(View.GONE);
            secBabySize.setVisibility(View.GONE);
            lineBabySize.setVisibility(View.GONE);
            secWgtMsrd.setVisibility(View.GONE);
            lineWgtMsrd.setVisibility(View.GONE);
            secWgtMsrdUnt.setVisibility(View.GONE);
            lineWgtMsrdUnt.setVisibility(View.GONE);
            secWgtPound.setVisibility(View.GONE);
            lineWgtPound.setVisibility(View.GONE);
            secWgtGm.setVisibility(View.GONE);
            lineWgtGm.setVisibility(View.GONE);
            secWgtDk.setVisibility(View.GONE);
            lineWgtDk.setVisibility(View.GONE);
            secWgtDtSrc.setVisibility(View.GONE);
            lineWgtDtSrc.setVisibility(View.GONE);
            secBFStatus.setVisibility(View.GONE);
            lineBFStatus.setVisibility(View.GONE);
            secBFTime.setVisibility(View.GONE);
            lineBFTime.setVisibility(View.GONE);
            secBFTimeDur.setVisibility(View.GONE);
            lineBFTimeDur.setVisibility(View.GONE);
            secBFTimeOth.setVisibility(View.GONE);
            lineBFTimeOth.setVisibility(View.GONE);
            secNBRthHead.setVisibility(View.GONE);
            lineNBRthHead.setVisibility(View.GONE);
            secNBRthHeadOth.setVisibility(View.GONE);
            lineNBRthHeadOth.setVisibility(View.GONE);
            secOutcomeDate.setVisibility(View.GONE);
            lineOutcomeDate.setVisibility(View.GONE);
            secOutcomeDateDk.setVisibility(View.GONE);
            lineOutcomeDateDk.setVisibility(View.GONE);
            secOutcomeTime.setVisibility(View.GONE);
            lineOutcomeTime.setVisibility(View.GONE);
            secOutcomeTimeDk.setVisibility(View.GONE);
            lineOutcomeTimeDk.setVisibility(View.GONE);
            secOutcomeType.setVisibility(View.GONE);
            lineOutcomeType.setVisibility(View.GONE);
            secCryMoveBreathe.setVisibility(View.GONE);
            lineCryMoveBreathe.setVisibility(View.GONE);
            secChildName.setVisibility(View.GONE);
            lineChildName.setVisibility(View.GONE);
            secChildNameDk.setVisibility(View.GONE);
            lineChildNameDk.setVisibility(View.GONE);
            secChildID.setVisibility(View.GONE);
            lineChildID.setVisibility(View.GONE);
            secChildSex.setVisibility(View.GONE);
            lineChildSex.setVisibility(View.GONE);
            secBirthTiming.setVisibility(View.GONE);
            lineBirthTiming.setVisibility(View.GONE);
            secPregWeek.setVisibility(View.GONE);
            linePregWeek.setVisibility(View.GONE);
            secPregWeekDk.setVisibility(View.GONE);
            linePregWeekDk.setVisibility(View.GONE);
            secExpDOB.setVisibility(View.GONE);
            lineExpDOB.setVisibility(View.GONE);
            secExpDOBDk.setVisibility(View.GONE);
            lineExpDOBDk.setVisibility(View.GONE);
            secChildDOB.setVisibility(View.GONE);
            lineChildDOB.setVisibility(View.GONE);
            secChildDOBDk.setVisibility(View.GONE);
            lineChildDOBDk.setVisibility(View.GONE);
            secBPlace.setVisibility(View.GONE);
            lineBPlace.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secFaciName.setVisibility(View.GONE);
            lineFaciName.setVisibility(View.GONE);
            secFaciNameDk.setVisibility(View.GONE);
            lineFaciNameDk.setVisibility(View.GONE);
            seclbl04.setVisibility(View.GONE);
            linelbl04.setVisibility(View.GONE);
            secDelAtndDoc.setVisibility(View.GONE);
            lineDelAtndDoc.setVisibility(View.GONE);
            secDelAtndNurse.setVisibility(View.GONE);
            lineDelAtndNurse.setVisibility(View.GONE);
            secDelAtndMidwife.setVisibility(View.GONE);
            lineDelAtndMidwife.setVisibility(View.GONE);
            secDelAtndTBA.setVisibility(View.GONE);
            lineDelAtndTBA.setVisibility(View.GONE);
            secDelAtndCHW.setVisibility(View.GONE);
            lineDelAtndCHW.setVisibility(View.GONE);
            secDelAtndRel.setVisibility(View.GONE);
            lineDelAtndRel.setVisibility(View.GONE);
            secDelAtndNon.setVisibility(View.GONE);
            lineDelAtndNon.setVisibility(View.GONE);
            secDelAtndOth.setVisibility(View.GONE);
            lineDelAtndOth.setVisibility(View.GONE);
            secDelAtndOthSp.setVisibility(View.GONE);
            lineDelAtndOthSp.setVisibility(View.GONE);
            secDelAtndDK.setVisibility(View.GONE);
            lineDelAtndDK.setVisibility(View.GONE);
            secDelAtndRef.setVisibility(View.GONE);
            lineDelAtndRef.setVisibility(View.GONE);
            secBabySize.setVisibility(View.GONE);
            lineBabySize.setVisibility(View.GONE);
            secWgtMsrd.setVisibility(View.GONE);
            lineWgtMsrd.setVisibility(View.GONE);
            secWgtMsrdUnt.setVisibility(View.GONE);
            lineWgtMsrdUnt.setVisibility(View.GONE);
            secWgtPound.setVisibility(View.GONE);
            lineWgtPound.setVisibility(View.GONE);
            secWgtGm.setVisibility(View.GONE);
            lineWgtGm.setVisibility(View.GONE);
            secWgtDk.setVisibility(View.GONE);
            lineWgtDk.setVisibility(View.GONE);
            secWgtDtSrc.setVisibility(View.GONE);
            lineWgtDtSrc.setVisibility(View.GONE);
            secBFStatus.setVisibility(View.GONE);
            lineBFStatus.setVisibility(View.GONE);
            secBFTime.setVisibility(View.GONE);
            lineBFTime.setVisibility(View.GONE);
            secBFTimeDur.setVisibility(View.GONE);
            lineBFTimeDur.setVisibility(View.GONE);
            secBFTimeOth.setVisibility(View.GONE);
            lineBFTimeOth.setVisibility(View.GONE);
            secNBRthHead.setVisibility(View.GONE);
            lineNBRthHead.setVisibility(View.GONE);
            secNBRthHeadOth.setVisibility(View.GONE);
            lineNBRthHeadOth.setVisibility(View.GONE);
            secOutcomeDate.setVisibility(View.GONE);
            lineOutcomeDate.setVisibility(View.GONE);
            secOutcomeDateDk.setVisibility(View.GONE);
            lineOutcomeDateDk.setVisibility(View.GONE);
            secOutcomeTime.setVisibility(View.GONE);
            lineOutcomeTime.setVisibility(View.GONE);
            secOutcomeTimeDk.setVisibility(View.GONE);
            lineOutcomeTimeDk.setVisibility(View.GONE);
            secOutcomeType.setVisibility(View.GONE);
            lineOutcomeType.setVisibility(View.GONE);
            secCryMoveBreathe.setVisibility(View.GONE);
            lineCryMoveBreathe.setVisibility(View.GONE);
            secChildName.setVisibility(View.GONE);
            lineChildName.setVisibility(View.GONE);
            secChildNameDk.setVisibility(View.GONE);
            lineChildNameDk.setVisibility(View.GONE);
            secChildID.setVisibility(View.GONE);
            lineChildID.setVisibility(View.GONE);
            secChildSex.setVisibility(View.GONE);
            lineChildSex.setVisibility(View.GONE);
            secBirthTiming.setVisibility(View.GONE);
            lineBirthTiming.setVisibility(View.GONE);
            secPregWeek.setVisibility(View.GONE);
            linePregWeek.setVisibility(View.GONE);
            secPregWeekDk.setVisibility(View.GONE);
            linePregWeekDk.setVisibility(View.GONE);
            secExpDOB.setVisibility(View.GONE);
            lineExpDOB.setVisibility(View.GONE);
            secExpDOBDk.setVisibility(View.GONE);
            lineExpDOBDk.setVisibility(View.GONE);
            secChildDOB.setVisibility(View.GONE);
            lineChildDOB.setVisibility(View.GONE);
            secChildDOBDk.setVisibility(View.GONE);
            lineChildDOBDk.setVisibility(View.GONE);
            secBPlace.setVisibility(View.GONE);
            lineBPlace.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secFaciName.setVisibility(View.GONE);
            lineFaciName.setVisibility(View.GONE);
            secFaciNameDk.setVisibility(View.GONE);
            lineFaciNameDk.setVisibility(View.GONE);
            seclbl04.setVisibility(View.GONE);
            linelbl04.setVisibility(View.GONE);
            secDelAtndDoc.setVisibility(View.GONE);
            lineDelAtndDoc.setVisibility(View.GONE);
            secDelAtndNurse.setVisibility(View.GONE);
            lineDelAtndNurse.setVisibility(View.GONE);
            secDelAtndMidwife.setVisibility(View.GONE);
            lineDelAtndMidwife.setVisibility(View.GONE);
            secDelAtndTBA.setVisibility(View.GONE);
            lineDelAtndTBA.setVisibility(View.GONE);
            secDelAtndCHW.setVisibility(View.GONE);
            lineDelAtndCHW.setVisibility(View.GONE);
            secDelAtndRel.setVisibility(View.GONE);
            lineDelAtndRel.setVisibility(View.GONE);
            secDelAtndNon.setVisibility(View.GONE);
            lineDelAtndNon.setVisibility(View.GONE);
            secDelAtndOth.setVisibility(View.GONE);
            lineDelAtndOth.setVisibility(View.GONE);
            secDelAtndOthSp.setVisibility(View.GONE);
            lineDelAtndOthSp.setVisibility(View.GONE);
            secDelAtndDK.setVisibility(View.GONE);
            lineDelAtndDK.setVisibility(View.GONE);
            secDelAtndRef.setVisibility(View.GONE);
            lineDelAtndRef.setVisibility(View.GONE);
            secBabySize.setVisibility(View.GONE);
            lineBabySize.setVisibility(View.GONE);
            secWgtMsrd.setVisibility(View.GONE);
            lineWgtMsrd.setVisibility(View.GONE);
            secWgtMsrdUnt.setVisibility(View.GONE);
            lineWgtMsrdUnt.setVisibility(View.GONE);
            secWgtPound.setVisibility(View.GONE);
            lineWgtPound.setVisibility(View.GONE);
            secWgtGm.setVisibility(View.GONE);
            lineWgtGm.setVisibility(View.GONE);
            secWgtDk.setVisibility(View.GONE);
            lineWgtDk.setVisibility(View.GONE);
            secWgtDtSrc.setVisibility(View.GONE);
            lineWgtDtSrc.setVisibility(View.GONE);
            secBFStatus.setVisibility(View.GONE);
            lineBFStatus.setVisibility(View.GONE);
            secBFTime.setVisibility(View.GONE);
            lineBFTime.setVisibility(View.GONE);
            secBFTimeDur.setVisibility(View.GONE);
            lineBFTimeDur.setVisibility(View.GONE);
            secBFTimeOth.setVisibility(View.GONE);
            lineBFTimeOth.setVisibility(View.GONE);
            secNBRthHead.setVisibility(View.GONE);
            lineNBRthHead.setVisibility(View.GONE);
            secNBRthHeadOth.setVisibility(View.GONE);
            lineNBRthHeadOth.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secFaciName.setVisibility(View.GONE);
            lineFaciName.setVisibility(View.GONE);
            secFaciNameDk.setVisibility(View.GONE);
            lineFaciNameDk.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secFaciName.setVisibility(View.GONE);
            lineFaciName.setVisibility(View.GONE);
            secFaciNameDk.setVisibility(View.GONE);
            lineFaciNameDk.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secFaciName.setVisibility(View.GONE);
            lineFaciName.setVisibility(View.GONE);
            secFaciNameDk.setVisibility(View.GONE);
            lineFaciNameDk.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secFaciName.setVisibility(View.GONE);
            lineFaciName.setVisibility(View.GONE);
            secFaciNameDk.setVisibility(View.GONE);
            lineFaciNameDk.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secFaciName.setVisibility(View.GONE);
            lineFaciName.setVisibility(View.GONE);
            secFaciNameDk.setVisibility(View.GONE);
            lineFaciNameDk.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secFaciName.setVisibility(View.GONE);
            lineFaciName.setVisibility(View.GONE);
            secFaciNameDk.setVisibility(View.GONE);
            lineFaciNameDk.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secFaciName.setVisibility(View.GONE);
            lineFaciName.setVisibility(View.GONE);
            secFaciNameDk.setVisibility(View.GONE);
            lineFaciNameDk.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secFaciName.setVisibility(View.GONE);
            lineFaciName.setVisibility(View.GONE);
            secFaciNameDk.setVisibility(View.GONE);
            lineFaciNameDk.setVisibility(View.GONE);
            secBPlaceOth.setVisibility(View.GONE);
            lineBPlaceOth.setVisibility(View.GONE);
            secDelAtndOthSp.setVisibility(View.GONE);
            lineDelAtndOthSp.setVisibility(View.GONE);
            secWgtMsrdUnt.setVisibility(View.GONE);
            lineWgtMsrdUnt.setVisibility(View.GONE);
            secWgtPound.setVisibility(View.GONE);
            lineWgtPound.setVisibility(View.GONE);
            secWgtGm.setVisibility(View.GONE);
            lineWgtGm.setVisibility(View.GONE);
            secWgtDk.setVisibility(View.GONE);
            lineWgtDk.setVisibility(View.GONE);
            secWgtDtSrc.setVisibility(View.GONE);
            lineWgtDtSrc.setVisibility(View.GONE);
            secWgtMsrdUnt.setVisibility(View.GONE);
            lineWgtMsrdUnt.setVisibility(View.GONE);
            secWgtPound.setVisibility(View.GONE);
            lineWgtPound.setVisibility(View.GONE);
            secWgtGm.setVisibility(View.GONE);
            lineWgtGm.setVisibility(View.GONE);
            secWgtDk.setVisibility(View.GONE);
            lineWgtDk.setVisibility(View.GONE);
            secWgtDtSrc.setVisibility(View.GONE);
            lineWgtDtSrc.setVisibility(View.GONE);
            secWgtMsrdUnt.setVisibility(View.GONE);
            lineWgtMsrdUnt.setVisibility(View.GONE);
            secWgtPound.setVisibility(View.GONE);
            lineWgtPound.setVisibility(View.GONE);
            secWgtGm.setVisibility(View.GONE);
            lineWgtGm.setVisibility(View.GONE);
            secWgtDk.setVisibility(View.GONE);
            lineWgtDk.setVisibility(View.GONE);
            secWgtDtSrc.setVisibility(View.GONE);
            lineWgtDtSrc.setVisibility(View.GONE);
            secBFTime.setVisibility(View.GONE);
            lineBFTime.setVisibility(View.GONE);
            secBFTimeDur.setVisibility(View.GONE);
            lineBFTimeDur.setVisibility(View.GONE);
            secBFTimeOth.setVisibility(View.GONE);
            lineBFTimeOth.setVisibility(View.GONE);
            secBFTime.setVisibility(View.GONE);
            lineBFTime.setVisibility(View.GONE);
            secBFTimeDur.setVisibility(View.GONE);
            lineBFTimeDur.setVisibility(View.GONE);
            secBFTimeOth.setVisibility(View.GONE);
            lineBFTimeOth.setVisibility(View.GONE);
            secBFTime.setVisibility(View.GONE);
            lineBFTime.setVisibility(View.GONE);
            secBFTimeDur.setVisibility(View.GONE);
            lineBFTimeDur.setVisibility(View.GONE);
            secBFTimeOth.setVisibility(View.GONE);
            lineBFTimeOth.setVisibility(View.GONE);
            secBFTimeDur.setVisibility(View.GONE);
            lineBFTimeDur.setVisibility(View.GONE);
            secBFTimeOth.setVisibility(View.GONE);
            lineBFTimeOth.setVisibility(View.GONE);
            secBFTimeDur.setVisibility(View.GONE);
            lineBFTimeDur.setVisibility(View.GONE);
            secBFTimeOth.setVisibility(View.GONE);
            lineBFTimeOth.setVisibility(View.GONE);
            secBFTimeDur.setVisibility(View.GONE);
            lineBFTimeDur.setVisibility(View.GONE);
            secBFTimeOth.setVisibility(View.GONE);
            lineBFTimeOth.setVisibility(View.GONE);
            secBFTimeDur.setVisibility(View.GONE);
            lineBFTimeDur.setVisibility(View.GONE);
            secBFTimeOth.setVisibility(View.GONE);
            lineBFTimeOth.setVisibility(View.GONE);
            secNBRthHeadOth.setVisibility(View.GONE);
            lineNBRthHeadOth.setVisibility(View.GONE);


            DataSearch(NBID, PGN, CHSL);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(NBC_NBCareDetail.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            secNBID = (LinearLayout) findViewById(R.id.secNBID);
            lineNBID = (View) findViewById(R.id.lineNBID);
            VlblNBID = (TextView) findViewById(R.id.VlblNBID);
            txtNBID = (EditText) findViewById(R.id.txtNBID);
            if (NBID.length() == 0) txtNBID.setText(Global.Get_UUID(DEVICEID));
            else txtNBID.setText(NBID);
            txtNBID.setEnabled(false);
            secMemID = (LinearLayout) findViewById(R.id.secMemID);
            lineMemID = (View) findViewById(R.id.lineMemID);
            VlblMemID = (TextView) findViewById(R.id.VlblMemID);
            txtMemID = (EditText) findViewById(R.id.txtMemID);
            txtMemID.setText(MEMID);
            txtMemID.setEnabled(false);
            secHHID = (LinearLayout) findViewById(R.id.secHHID);
            lineHHID = (View) findViewById(R.id.lineHHID);
            VlblHHID = (TextView) findViewById(R.id.VlblHHID);
            txtHHID = (EditText) findViewById(R.id.txtHHID);
            txtHHID.setText(HHID);
            txtHHID.setEnabled(false);
            secPGN = (LinearLayout) findViewById(R.id.secPGN);
            linePGN = (View) findViewById(R.id.linePGN);
            VlblPGN = (TextView) findViewById(R.id.VlblPGN);
            txtPGN = (EditText) findViewById(R.id.txtPGN);
            txtPGN.setText(PGN);
            txtPGN.setEnabled(false);
            secChSl = (LinearLayout) findViewById(R.id.secChSl);
            lineChSl = (View) findViewById(R.id.lineChSl);
            VlblChSl = (TextView) findViewById(R.id.VlblChSl);
            txtChSl = (EditText) findViewById(R.id.txtChSl);
            if (CHSL.length() == 0)
                txtChSl.setText(NewChildSerial(MEMID, PGN));
            else
                txtChSl.setText(CHSL);
            txtChSl.setEnabled(false);
            secPregType = (LinearLayout) findViewById(R.id.secPregType);
            linePregType = (View) findViewById(R.id.linePregType);
            VlblPregType = (TextView) findViewById(R.id.VlblPregType);
            rdogrpPregType = (RadioGroup) findViewById(R.id.rdogrpPregType);
            rdoPregType1 = (RadioButton) findViewById(R.id.rdoPregType1);
            rdoPregType2 = (RadioButton) findViewById(R.id.rdoPregType2);
            rdoPregType3 = (RadioButton) findViewById(R.id.rdoPregType3);
            rdoPregType4 = (RadioButton) findViewById(R.id.rdoPregType4);
            rdoPregType5 = (RadioButton) findViewById(R.id.rdoPregType5);
            rdoPregType6 = (RadioButton) findViewById(R.id.rdoPregType6);
            rdoPregType7 = (RadioButton) findViewById(R.id.rdoPregType7);
            rdogrpPregType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpPregType = new String[]{"1", "2", "3", "4", "5", "8", "9"};
                    for (int i = 0; i < rdogrpPregType.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpPregType.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpPregType[i];
                    }

                    if (rbData.equalsIgnoreCase("2") || rbData.equalsIgnoreCase("4") || rbData.equalsIgnoreCase("5") || rbData.equalsIgnoreCase("8") || rbData.equalsIgnoreCase("9")) {
                        secOutcomeDate.setVisibility(View.GONE);
                        lineOutcomeDate.setVisibility(View.GONE);
                        dtpOutcomeDate.setText("");
                        secOutcomeDateDk.setVisibility(View.GONE);
                        lineOutcomeDateDk.setVisibility(View.GONE);
                        rdogrpOutcomeDateDk.clearCheck();
                        secOutcomeTime.setVisibility(View.GONE);
                        lineOutcomeTime.setVisibility(View.GONE);
                        txtOutcomeTime.setText("");
                        secOutcomeTimeDk.setVisibility(View.GONE);
                        lineOutcomeTimeDk.setVisibility(View.GONE);
                        rdogrpOutcomeTimeDk.clearCheck();
                        secOutcomeType.setVisibility(View.GONE);
                        lineOutcomeType.setVisibility(View.GONE);
                        rdogrpOutcomeType.clearCheck();
                        secCryMoveBreathe.setVisibility(View.GONE);
                        lineCryMoveBreathe.setVisibility(View.GONE);
                        rdogrpCryMoveBreathe.clearCheck();
                        secChildName.setVisibility(View.GONE);
                        lineChildName.setVisibility(View.GONE);
                        txtChildName.setText("");
                        secChildNameDk.setVisibility(View.GONE);
                        lineChildNameDk.setVisibility(View.GONE);
                        rdogrpChildNameDk.clearCheck();
                        secChildID.setVisibility(View.GONE);
                        lineChildID.setVisibility(View.GONE);
                        txtChildID.setText("");
                        secChildSex.setVisibility(View.GONE);
                        lineChildSex.setVisibility(View.GONE);
                        rdogrpChildSex.clearCheck();
                        secBirthTiming.setVisibility(View.GONE);
                        lineBirthTiming.setVisibility(View.GONE);
                        rdogrpBirthTiming.clearCheck();
                        secPregWeek.setVisibility(View.GONE);
                        linePregWeek.setVisibility(View.GONE);
                        txtPregWeek.setText("");
                        secPregWeekDk.setVisibility(View.GONE);
                        linePregWeekDk.setVisibility(View.GONE);
                        rdogrpPregWeekDk.clearCheck();
                        secExpDOB.setVisibility(View.GONE);
                        lineExpDOB.setVisibility(View.GONE);
                        dtpExpDOB.setText("");
                        secExpDOBDk.setVisibility(View.GONE);
                        lineExpDOBDk.setVisibility(View.GONE);
                        rdogrpExpDOBDk.clearCheck();
                        secChildDOB.setVisibility(View.GONE);
                        lineChildDOB.setVisibility(View.GONE);
                        dtpChildDOB.setText("");
                        secChildDOBDk.setVisibility(View.GONE);
                        lineChildDOBDk.setVisibility(View.GONE);
                        rdogrpChildDOBDk.clearCheck();
                        secBPlace.setVisibility(View.GONE);
                        lineBPlace.setVisibility(View.GONE);
                        spnBPlace.setSelection(0);
                        secBPlaceOth.setVisibility(View.GONE);
                        lineBPlaceOth.setVisibility(View.GONE);
                        txtBPlaceOth.setText("");
                        secFaciName.setVisibility(View.GONE);
                        lineFaciName.setVisibility(View.GONE);
                        txtFaciName.setText("");
                        secFaciNameDk.setVisibility(View.GONE);
                        lineFaciNameDk.setVisibility(View.GONE);
                        rdogrpFaciNameDk.clearCheck();
                        seclbl04.setVisibility(View.GONE);
                        linelbl04.setVisibility(View.GONE);
                        secDelAtndDoc.setVisibility(View.GONE);
                        lineDelAtndDoc.setVisibility(View.GONE);
                        chkDelAtndDoc.setChecked(false);
                        secDelAtndNurse.setVisibility(View.GONE);
                        lineDelAtndNurse.setVisibility(View.GONE);
                        chkDelAtndNurse.setChecked(false);
                        secDelAtndMidwife.setVisibility(View.GONE);
                        lineDelAtndMidwife.setVisibility(View.GONE);
                        chkDelAtndMidwife.setChecked(false);
                        secDelAtndTBA.setVisibility(View.GONE);
                        lineDelAtndTBA.setVisibility(View.GONE);
                        chkDelAtndTBA.setChecked(false);
                        secDelAtndCHW.setVisibility(View.GONE);
                        lineDelAtndCHW.setVisibility(View.GONE);
                        chkDelAtndCHW.setChecked(false);
                        secDelAtndRel.setVisibility(View.GONE);
                        lineDelAtndRel.setVisibility(View.GONE);
                        chkDelAtndRel.setChecked(false);
                        secDelAtndNon.setVisibility(View.GONE);
                        lineDelAtndNon.setVisibility(View.GONE);
                        chkDelAtndNon.setChecked(false);
                        secDelAtndOth.setVisibility(View.GONE);
                        lineDelAtndOth.setVisibility(View.GONE);
                        chkDelAtndOth.setChecked(false);
                        secDelAtndOthSp.setVisibility(View.GONE);
                        lineDelAtndOthSp.setVisibility(View.GONE);
                        txtDelAtndOthSp.setText("");
                        secDelAtndDK.setVisibility(View.GONE);
                        lineDelAtndDK.setVisibility(View.GONE);
                        chkDelAtndDK.setChecked(false);
                        secDelAtndRef.setVisibility(View.GONE);
                        lineDelAtndRef.setVisibility(View.GONE);
                        chkDelAtndRef.setChecked(false);
                        secBabySize.setVisibility(View.GONE);
                        lineBabySize.setVisibility(View.GONE);
                        rdogrpBabySize.clearCheck();
                        secWgtMsrd.setVisibility(View.GONE);
                        lineWgtMsrd.setVisibility(View.GONE);
                        rdogrpWgtMsrd.clearCheck();
                        secWgtMsrdUnt.setVisibility(View.GONE);
                        lineWgtMsrdUnt.setVisibility(View.GONE);
                        rdogrpWgtMsrdUnt.clearCheck();
                        secWgtPound.setVisibility(View.GONE);
                        lineWgtPound.setVisibility(View.GONE);
                        secWgtGm.setVisibility(View.GONE);
                        lineWgtGm.setVisibility(View.GONE);
                        txtWgtGm.setText("");
                        secWgtDk.setVisibility(View.GONE);
                        lineWgtDk.setVisibility(View.GONE);
                        rdogrpWgtDk.clearCheck();
                        secWgtDtSrc.setVisibility(View.GONE);
                        lineWgtDtSrc.setVisibility(View.GONE);
                        rdogrpWgtDtSrc.clearCheck();
                        secBFStatus.setVisibility(View.GONE);
                        lineBFStatus.setVisibility(View.GONE);
                        rdogrpBFStatus.clearCheck();
                        secBFTime.setVisibility(View.GONE);
                        lineBFTime.setVisibility(View.GONE);
                        rdogrpBFTime.clearCheck();
                        secBFTimeDur.setVisibility(View.GONE);
                        lineBFTimeDur.setVisibility(View.GONE);
                        txtBFTimeDur.setText("");
                        secBFTimeOth.setVisibility(View.GONE);
                        lineBFTimeOth.setVisibility(View.GONE);
                        txtBFTimeOth.setText("");
                        secNBRthHead.setVisibility(View.GONE);
                        lineNBRthHead.setVisibility(View.GONE);
                        spnNBRthHead.setSelection(0);
                        secNBRthHeadOth.setVisibility(View.GONE);
                        lineNBRthHeadOth.setVisibility(View.GONE);
                        txtNBRthHeadOth.setText("");
                    } else if (rbData.equalsIgnoreCase("3")) {
                        secOutcomeDate.setVisibility(View.VISIBLE);
                        lineOutcomeDate.setVisibility(View.VISIBLE);
                        secOutcomeDateDk.setVisibility(View.VISIBLE);
                        lineOutcomeDateDk.setVisibility(View.VISIBLE);
                        secOutcomeTime.setVisibility(View.VISIBLE);
                        lineOutcomeTime.setVisibility(View.VISIBLE);
                        secOutcomeTimeDk.setVisibility(View.VISIBLE);
                        lineOutcomeTimeDk.setVisibility(View.VISIBLE);
                        secOutcomeType.setVisibility(View.VISIBLE);
                        lineOutcomeType.setVisibility(View.VISIBLE);
                        secCryMoveBreathe.setVisibility(View.VISIBLE);
                        lineCryMoveBreathe.setVisibility(View.VISIBLE);
                        secChildName.setVisibility(View.GONE);
                        lineChildName.setVisibility(View.GONE);
                        txtChildName.setText("");
                        secChildNameDk.setVisibility(View.GONE);
                        lineChildNameDk.setVisibility(View.GONE);
                        rdogrpChildNameDk.clearCheck();
                        secChildSex.setVisibility(View.GONE);
                        lineChildSex.setVisibility(View.GONE);
                        rdogrpChildSex.clearCheck();
                        secBirthTiming.setVisibility(View.VISIBLE);
                        lineBirthTiming.setVisibility(View.VISIBLE);
                        secPregWeek.setVisibility(View.VISIBLE);
                        linePregWeek.setVisibility(View.VISIBLE);
                        secPregWeekDk.setVisibility(View.VISIBLE);
                        linePregWeekDk.setVisibility(View.VISIBLE);
                        secExpDOB.setVisibility(View.VISIBLE);
                        lineExpDOB.setVisibility(View.VISIBLE);
                        secExpDOBDk.setVisibility(View.VISIBLE);
                        lineExpDOBDk.setVisibility(View.VISIBLE);
                        secChildDOB.setVisibility(View.VISIBLE);
                        lineChildDOB.setVisibility(View.VISIBLE);
                        secChildDOBDk.setVisibility(View.VISIBLE);
                        lineChildDOBDk.setVisibility(View.VISIBLE);
                        secBPlace.setVisibility(View.VISIBLE);
                        lineBPlace.setVisibility(View.VISIBLE);
                        secFaciName.setVisibility(View.VISIBLE);
                        lineFaciName.setVisibility(View.VISIBLE);
                        secFaciNameDk.setVisibility(View.VISIBLE);
                        lineFaciNameDk.setVisibility(View.VISIBLE);
                        seclbl04.setVisibility(View.VISIBLE);
                        linelbl04.setVisibility(View.VISIBLE);
                        secDelAtndDoc.setVisibility(View.VISIBLE);
                        lineDelAtndDoc.setVisibility(View.VISIBLE);
                        secDelAtndNurse.setVisibility(View.VISIBLE);
                        lineDelAtndNurse.setVisibility(View.VISIBLE);
                        secDelAtndMidwife.setVisibility(View.VISIBLE);
                        lineDelAtndMidwife.setVisibility(View.VISIBLE);
                        secDelAtndTBA.setVisibility(View.VISIBLE);
                        lineDelAtndTBA.setVisibility(View.VISIBLE);
                        secDelAtndCHW.setVisibility(View.VISIBLE);
                        lineDelAtndCHW.setVisibility(View.VISIBLE);
                        secDelAtndRel.setVisibility(View.VISIBLE);
                        lineDelAtndRel.setVisibility(View.VISIBLE);
                        secDelAtndNon.setVisibility(View.VISIBLE);
                        lineDelAtndNon.setVisibility(View.VISIBLE);
                        secDelAtndOth.setVisibility(View.VISIBLE);
                        lineDelAtndOth.setVisibility(View.VISIBLE);
                        secDelAtndDK.setVisibility(View.VISIBLE);
                        lineDelAtndDK.setVisibility(View.VISIBLE);
                        secDelAtndRef.setVisibility(View.VISIBLE);
                        lineDelAtndRef.setVisibility(View.VISIBLE);
                        secBabySize.setVisibility(View.VISIBLE);
                        lineBabySize.setVisibility(View.VISIBLE);
                        secWgtMsrd.setVisibility(View.VISIBLE);
                        lineWgtMsrd.setVisibility(View.VISIBLE);
                        secWgtMsrdUnt.setVisibility(View.VISIBLE);
                        lineWgtMsrdUnt.setVisibility(View.VISIBLE);
                        secWgtPound.setVisibility(View.VISIBLE);
                        lineWgtPound.setVisibility(View.VISIBLE);
                        secWgtGm.setVisibility(View.VISIBLE);
                        lineWgtGm.setVisibility(View.VISIBLE);
                        secWgtDk.setVisibility(View.VISIBLE);
                        lineWgtDk.setVisibility(View.VISIBLE);
                        secWgtDtSrc.setVisibility(View.VISIBLE);
                        lineWgtDtSrc.setVisibility(View.VISIBLE);
                        secBFStatus.setVisibility(View.VISIBLE);
                        lineBFStatus.setVisibility(View.VISIBLE);
                        secBFTime.setVisibility(View.VISIBLE);
                        lineBFTime.setVisibility(View.VISIBLE);
                        secBFTimeDur.setVisibility(View.VISIBLE);
                        lineBFTimeDur.setVisibility(View.VISIBLE);
                        secNBRthHead.setVisibility(View.VISIBLE);
                        lineNBRthHead.setVisibility(View.VISIBLE);
                    } else {
                        secOutcomeDate.setVisibility(View.VISIBLE);
                        lineOutcomeDate.setVisibility(View.VISIBLE);
                        secOutcomeDateDk.setVisibility(View.VISIBLE);
                        lineOutcomeDateDk.setVisibility(View.VISIBLE);
                        secOutcomeTime.setVisibility(View.VISIBLE);
                        lineOutcomeTime.setVisibility(View.VISIBLE);
                        secOutcomeTimeDk.setVisibility(View.VISIBLE);
                        lineOutcomeTimeDk.setVisibility(View.VISIBLE);
                        secOutcomeType.setVisibility(View.VISIBLE);
                        lineOutcomeType.setVisibility(View.VISIBLE);
                        secCryMoveBreathe.setVisibility(View.VISIBLE);
                        lineCryMoveBreathe.setVisibility(View.VISIBLE);
                        secChildName.setVisibility(View.VISIBLE);
                        lineChildName.setVisibility(View.VISIBLE);
                        secChildNameDk.setVisibility(View.VISIBLE);
                        lineChildNameDk.setVisibility(View.VISIBLE);
                        secChildSex.setVisibility(View.VISIBLE);
                        lineChildSex.setVisibility(View.VISIBLE);
                        secBirthTiming.setVisibility(View.VISIBLE);
                        lineBirthTiming.setVisibility(View.VISIBLE);
                        secPregWeek.setVisibility(View.VISIBLE);
                        linePregWeek.setVisibility(View.VISIBLE);
                        secPregWeekDk.setVisibility(View.VISIBLE);
                        linePregWeekDk.setVisibility(View.VISIBLE);
                        secExpDOB.setVisibility(View.VISIBLE);
                        lineExpDOB.setVisibility(View.VISIBLE);
                        secExpDOBDk.setVisibility(View.VISIBLE);
                        lineExpDOBDk.setVisibility(View.VISIBLE);
                        secChildDOB.setVisibility(View.VISIBLE);
                        lineChildDOB.setVisibility(View.VISIBLE);
                        secChildDOBDk.setVisibility(View.VISIBLE);
                        lineChildDOBDk.setVisibility(View.VISIBLE);
                        secBPlace.setVisibility(View.VISIBLE);
                        lineBPlace.setVisibility(View.VISIBLE);
                        secFaciName.setVisibility(View.VISIBLE);
                        lineFaciName.setVisibility(View.VISIBLE);
                        secFaciNameDk.setVisibility(View.VISIBLE);
                        lineFaciNameDk.setVisibility(View.VISIBLE);
                        seclbl04.setVisibility(View.VISIBLE);
                        linelbl04.setVisibility(View.VISIBLE);
                        secDelAtndDoc.setVisibility(View.VISIBLE);
                        lineDelAtndDoc.setVisibility(View.VISIBLE);
                        secDelAtndNurse.setVisibility(View.VISIBLE);
                        lineDelAtndNurse.setVisibility(View.VISIBLE);
                        secDelAtndMidwife.setVisibility(View.VISIBLE);
                        lineDelAtndMidwife.setVisibility(View.VISIBLE);
                        secDelAtndTBA.setVisibility(View.VISIBLE);
                        lineDelAtndTBA.setVisibility(View.VISIBLE);
                        secDelAtndCHW.setVisibility(View.VISIBLE);
                        lineDelAtndCHW.setVisibility(View.VISIBLE);
                        secDelAtndRel.setVisibility(View.VISIBLE);
                        lineDelAtndRel.setVisibility(View.VISIBLE);
                        secDelAtndNon.setVisibility(View.VISIBLE);
                        lineDelAtndNon.setVisibility(View.VISIBLE);
                        secDelAtndOth.setVisibility(View.VISIBLE);
                        lineDelAtndOth.setVisibility(View.VISIBLE);
                        secDelAtndDK.setVisibility(View.VISIBLE);
                        lineDelAtndDK.setVisibility(View.VISIBLE);
                        secDelAtndRef.setVisibility(View.VISIBLE);
                        lineDelAtndRef.setVisibility(View.VISIBLE);
                        secBabySize.setVisibility(View.VISIBLE);
                        lineBabySize.setVisibility(View.VISIBLE);
                        secWgtMsrd.setVisibility(View.VISIBLE);
                        lineWgtMsrd.setVisibility(View.VISIBLE);
                        secWgtMsrdUnt.setVisibility(View.VISIBLE);
                        lineWgtMsrdUnt.setVisibility(View.VISIBLE);
                        secWgtPound.setVisibility(View.VISIBLE);
                        lineWgtPound.setVisibility(View.VISIBLE);
                        secWgtGm.setVisibility(View.VISIBLE);
                        lineWgtGm.setVisibility(View.VISIBLE);
                        secWgtDk.setVisibility(View.VISIBLE);
                        lineWgtDk.setVisibility(View.VISIBLE);
                        secWgtDtSrc.setVisibility(View.VISIBLE);
                        lineWgtDtSrc.setVisibility(View.VISIBLE);
                        secBFStatus.setVisibility(View.VISIBLE);
                        lineBFStatus.setVisibility(View.VISIBLE);
                        secBFTime.setVisibility(View.VISIBLE);
                        lineBFTime.setVisibility(View.VISIBLE);
                        secBFTimeDur.setVisibility(View.VISIBLE);
                        lineBFTimeDur.setVisibility(View.VISIBLE);
                        secNBRthHead.setVisibility(View.VISIBLE);
                        lineNBRthHead.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secOutcomeDate = (LinearLayout) findViewById(R.id.secOutcomeDate);
            lineOutcomeDate = (View) findViewById(R.id.lineOutcomeDate);
            VlblOutcomeDate = (TextView) findViewById(R.id.VlblOutcomeDate);
            dtpOutcomeDate = (EditText) findViewById(R.id.dtpOutcomeDate);
            dtpOutcomeDate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 0) {
                        if (rdoOutcomeDateDk1.isChecked() || rdoOutcomeDateDk2.isChecked()) {
                            rdogrpOutcomeDateDk.clearCheck();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            secOutcomeDateDk = (LinearLayout) findViewById(R.id.secOutcomeDateDk);
            lineOutcomeDateDk = (View) findViewById(R.id.lineOutcomeDateDk);
            VlblOutcomeDateDk = (TextView) findViewById(R.id.VlblOutcomeDateDk);
            rdogrpOutcomeDateDk = (RadioGroup) findViewById(R.id.rdogrpOutcomeDateDk);
            rdogrpOutcomeDateDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpOutcomeDateDk = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpOutcomeDateDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpOutcomeDateDk.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpOutcomeDateDk[i];

                        if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2")) {
                            if (dtpOutcomeDate.getText().toString().length() > 0) {
                                dtpOutcomeDate.setText("");
                            }
                        }

                    }
                }
            });
            rdoOutcomeDateDk1 = (RadioButton) findViewById(R.id.rdoOutcomeDateDk1);
            rdoOutcomeDateDk2 = (RadioButton) findViewById(R.id.rdoOutcomeDateDk2);
            secOutcomeTime = (LinearLayout) findViewById(R.id.secOutcomeTime);
            lineOutcomeTime = (View) findViewById(R.id.lineOutcomeTime);
            VlblOutcomeTime = (TextView) findViewById(R.id.VlblOutcomeTime);
            txtOutcomeTime = (EditText) findViewById(R.id.txtOutcomeTime);
            txtOutcomeTime.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 0) {
                        if (rdoOutcomeTimeDk1.isChecked() || rdoOutcomeTimeDk2.isChecked()) {
                            rdogrpOutcomeTimeDk.clearCheck();
                        }
                        secOutcomeType.setVisibility(View.VISIBLE);
                        lineOutcomeType.setVisibility(View.VISIBLE);
                    } else {
                        secOutcomeType.setVisibility(View.GONE);
                        lineOutcomeType.setVisibility(View.GONE);
                        rdogrpOutcomeType.clearCheck();
                        //rdogrpOutcomeTimeDk.clearCheck();

                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            secOutcomeTimeDk = (LinearLayout) findViewById(R.id.secOutcomeTimeDk);
            lineOutcomeTimeDk = (View) findViewById(R.id.lineOutcomeTimeDk);
            VlblOutcomeTimeDk = (TextView) findViewById(R.id.VlblOutcomeTimeDk);
            rdogrpOutcomeTimeDk = (RadioGroup) findViewById(R.id.rdogrpOutcomeTimeDk);
            rdogrpOutcomeTimeDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpOutcomeTimeDk = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpOutcomeTimeDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpOutcomeTimeDk.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpOutcomeTimeDk[i];

                        if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2")) {
                            if (txtOutcomeTime.getText().toString().length() > 0) {
                                txtOutcomeTime.setText("");
                            }
                            secOutcomeType.setVisibility(View.GONE);
                            lineOutcomeType.setVisibility(View.GONE);
                            secOutcomeType.setVisibility(View.GONE);
                            rdogrpOutcomeType.clearCheck();
                        } else {
                            secOutcomeType.setVisibility(View.VISIBLE);
                            lineOutcomeType.setVisibility(View.VISIBLE);
                            secOutcomeType.setVisibility(View.VISIBLE);
                        }

                    }
                }
            });
            rdoOutcomeTimeDk1 = (RadioButton) findViewById(R.id.rdoOutcomeTimeDk1);
            rdoOutcomeTimeDk2 = (RadioButton) findViewById(R.id.rdoOutcomeTimeDk2);
            secOutcomeType = (LinearLayout) findViewById(R.id.secOutcomeType);
            lineOutcomeType = (View) findViewById(R.id.lineOutcomeType);
            VlblOutcomeType = (TextView) findViewById(R.id.VlblOutcomeType);
            rdogrpOutcomeType = (RadioGroup) findViewById(R.id.rdogrpOutcomeType);
            rdoOutcomeType1 = (RadioButton) findViewById(R.id.rdoOutcomeType1);
            rdoOutcomeType2 = (RadioButton) findViewById(R.id.rdoOutcomeType2);
            secCryMoveBreathe = (LinearLayout) findViewById(R.id.secCryMoveBreathe);
            lineCryMoveBreathe = (View) findViewById(R.id.lineCryMoveBreathe);
            VlblCryMoveBreathe = (TextView) findViewById(R.id.VlblCryMoveBreathe);
            rdogrpCryMoveBreathe = (RadioGroup) findViewById(R.id.rdogrpCryMoveBreathe);
            rdoCryMoveBreathe1 = (RadioButton) findViewById(R.id.rdoCryMoveBreathe1);
            rdoCryMoveBreathe2 = (RadioButton) findViewById(R.id.rdoCryMoveBreathe2);
            rdoCryMoveBreathe3 = (RadioButton) findViewById(R.id.rdoCryMoveBreathe3);
            rdoCryMoveBreathe4 = (RadioButton) findViewById(R.id.rdoCryMoveBreathe4);
            secChildName = (LinearLayout) findViewById(R.id.secChildName);
            lineChildName = (View) findViewById(R.id.lineChildName);
            VlblChildName = (TextView) findViewById(R.id.VlblChildName);
            txtChildName = (EditText) findViewById(R.id.txtChildName);
            txtChildName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 0) {
                        if (rdoChildNameDk1.isChecked() || rdoChildNameDk2.isChecked()) {
                            rdogrpChildNameDk.clearCheck();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            secChildNameDk = (LinearLayout) findViewById(R.id.secChildNameDk);
            lineChildNameDk = (View) findViewById(R.id.lineChildNameDk);
            VlblChildNameDk = (TextView) findViewById(R.id.VlblChildNameDk);
            rdogrpChildNameDk = (RadioGroup) findViewById(R.id.rdogrpChildNameDk);
            rdogrpChildNameDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpChildNameDk = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpChildNameDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpChildNameDk.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpChildNameDk[i];

                        if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2")) {
                            if (txtChildName.getText().toString().length() > 0) {
                                txtChildName.setText("");
                            }
                        }

                    }
                }
            });
            rdoChildNameDk1 = (RadioButton) findViewById(R.id.rdoChildNameDk1);
            rdoChildNameDk2 = (RadioButton) findViewById(R.id.rdoChildNameDk2);
            secChildID = (LinearLayout) findViewById(R.id.secChildID);
            lineChildID = (View) findViewById(R.id.lineChildID);
            VlblChildID = (TextView) findViewById(R.id.VlblChildID);
            txtChildID = (EditText) findViewById(R.id.txtChildID);
            secChildSex = (LinearLayout) findViewById(R.id.secChildSex);
            lineChildSex = (View) findViewById(R.id.lineChildSex);
            VlblChildSex = (TextView) findViewById(R.id.VlblChildSex);
            rdogrpChildSex = (RadioGroup) findViewById(R.id.rdogrpChildSex);
            rdoChildSex1 = (RadioButton) findViewById(R.id.rdoChildSex1);
            rdoChildSex2 = (RadioButton) findViewById(R.id.rdoChildSex2);
            rdoChildSex3 = (RadioButton) findViewById(R.id.rdoChildSex3);
            rdoChildSex4 = (RadioButton) findViewById(R.id.rdoChildSex4);
            rdoChildSex5 = (RadioButton) findViewById(R.id.rdoChildSex5);

            if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2)){
                rdoChildSex4.setVisibility(View.GONE);
            }

            secBirthTiming = (LinearLayout) findViewById(R.id.secBirthTiming);
            lineBirthTiming = (View) findViewById(R.id.lineBirthTiming);
            VlblBirthTiming = (TextView) findViewById(R.id.VlblBirthTiming);
            rdogrpBirthTiming = (RadioGroup) findViewById(R.id.rdogrpBirthTiming);
            rdoBirthTiming1 = (RadioButton) findViewById(R.id.rdoBirthTiming1);
            rdoBirthTiming2 = (RadioButton) findViewById(R.id.rdoBirthTiming2);
            rdoBirthTiming3 = (RadioButton) findViewById(R.id.rdoBirthTiming3);
            rdoBirthTiming4 = (RadioButton) findViewById(R.id.rdoBirthTiming4);
            rdoBirthTiming5 = (RadioButton) findViewById(R.id.rdoBirthTiming5);
            secPregWeek = (LinearLayout) findViewById(R.id.secPregWeek);
            linePregWeek = (View) findViewById(R.id.linePregWeek);
            VlblPregWeek = (TextView) findViewById(R.id.VlblPregWeek);
            txtPregWeek = (EditText) findViewById(R.id.txtPregWeek);
            secPregWeekDk = (LinearLayout) findViewById(R.id.secPregWeekDk);
            linePregWeekDk = (View) findViewById(R.id.linePregWeekDk);
            VlblPregWeekDk = (TextView) findViewById(R.id.VlblPregWeekDk);
            rdogrpPregWeekDk = (RadioGroup) findViewById(R.id.rdogrpPregWeekDk);
            rdoPregWeekDk1 = (RadioButton) findViewById(R.id.rdoPregWeekDk1);
            rdoPregWeekDk2 = (RadioButton) findViewById(R.id.rdoPregWeekDk2);

            txtPregWeek.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 0) {
                        if (rdoPregWeekDk1.isChecked() || rdoPregWeekDk2.isChecked()) {
                            rdogrpPregWeekDk.clearCheck();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            rdogrpPregWeekDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpPregWeekDk = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpPregWeekDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpPregWeekDk.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpPregWeekDk[i];

                        if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2")) {
                            if (txtPregWeek.getText().toString().length() > 0) {
                                txtPregWeek.setText("");
                            }
                        }

                    }
                }
            });

            secExpDOB = (LinearLayout) findViewById(R.id.secExpDOB);
            lineExpDOB = (View) findViewById(R.id.lineExpDOB);
            VlblExpDOB = (TextView) findViewById(R.id.VlblExpDOB);
            dtpExpDOB = (EditText) findViewById(R.id.dtpExpDOB);
            secExpDOBDk = (LinearLayout) findViewById(R.id.secExpDOBDk);
            lineExpDOBDk = (View) findViewById(R.id.lineExpDOBDk);
            VlblExpDOBDk = (TextView) findViewById(R.id.VlblExpDOBDk);
            rdogrpExpDOBDk = (RadioGroup) findViewById(R.id.rdogrpExpDOBDk);
            rdoExpDOBDk1 = (RadioButton) findViewById(R.id.rdoExpDOBDk1);
            rdoExpDOBDk2 = (RadioButton) findViewById(R.id.rdoExpDOBDk2);

            dtpExpDOB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 0) {
                        if (rdoExpDOBDk1.isChecked() || rdoExpDOBDk2.isChecked()) {
                            rdogrpExpDOBDk.clearCheck();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            rdogrpExpDOBDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpExpDOBDk = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpExpDOBDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpExpDOBDk.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpExpDOBDk[i];

                        if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2")) {
                            if (dtpExpDOB.getText().toString().length() > 0) {
                                dtpExpDOB.setText("");
                            }
                        }

                    }
                }
            });

            secChildDOB = (LinearLayout) findViewById(R.id.secChildDOB);
            lineChildDOB = (View) findViewById(R.id.lineChildDOB);
            VlblChildDOB = (TextView) findViewById(R.id.VlblChildDOB);
            dtpChildDOB = (EditText) findViewById(R.id.dtpChildDOB);
            secChildDOBDk = (LinearLayout) findViewById(R.id.secChildDOBDk);
            lineChildDOBDk = (View) findViewById(R.id.lineChildDOBDk);
            VlblChildDOBDk = (TextView) findViewById(R.id.VlblChildDOBDk);
            rdogrpChildDOBDk = (RadioGroup) findViewById(R.id.rdogrpChildDOBDk);
            rdoChildDOBDk1 = (RadioButton) findViewById(R.id.rdoChildDOBDk1);
            rdoChildDOBDk2 = (RadioButton) findViewById(R.id.rdoChildDOBDk2);

            dtpChildDOB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 0) {
                        if (rdoChildDOBDk1.isChecked() || rdoChildDOBDk2.isChecked()) {
                            rdogrpChildDOBDk.clearCheck();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            rdogrpChildDOBDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpChildDOBDk = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpChildDOBDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpChildDOBDk.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpChildDOBDk[i];

                        if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2")) {
                            if (dtpChildDOB.getText().toString().length() > 0) {
                                dtpChildDOB.setText("");
                            }
                        }

                    }
                }
            });

            secBPlace = (LinearLayout) findViewById(R.id.secBPlace);
            lineBPlace = (View) findViewById(R.id.lineBPlace);
            VlblBPlace = (TextView) findViewById(R.id.VlblBPlace);
            spnBPlace = (Spinner) findViewById(R.id.spnBPlace);
            List<String> listBPlace = new ArrayList<String>();

            listBPlace.add("");
            listBPlace.add("11-Respondents home");
            listBPlace.add("15-Other home");
            listBPlace.add("21-Government hospital");
            listBPlace.add("22-Government health center");
            listBPlace.add("23-Government health post");
            listBPlace.add("31-Private hospital/clinic");
            listBPlace.add("32-TBA Home");
            listBPlace.add("41-NGO hospital/clinic");
            listBPlace.add("51-On the way to health facility");
            listBPlace.add("52-On the Way to Home");
            listBPlace.add("53-On the way to health facility to other health facility");
            listBPlace.add("97-Other");
            listBPlace.add("98-Dont know");
            listBPlace.add("99-Refused to respond");
            spnBPlace.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listBPlace)));

            spnBPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnBPlace.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnBPlace.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("11") || spnData.equalsIgnoreCase("15") || spnData.equalsIgnoreCase("51") || spnData.equalsIgnoreCase("52") || spnData.equalsIgnoreCase("53") || spnData.equalsIgnoreCase("98") || spnData.equalsIgnoreCase("99")) {
                        secBPlaceOth.setVisibility(View.GONE);
                        lineBPlaceOth.setVisibility(View.GONE);
                        txtBPlaceOth.setText("");
                        secFaciName.setVisibility(View.GONE);
                        lineFaciName.setVisibility(View.GONE);
                        txtFaciName.setText("");
                        secFaciNameDk.setVisibility(View.GONE);
                        lineFaciNameDk.setVisibility(View.GONE);
                        rdogrpFaciNameDk.clearCheck();
                    } else if (spnData.equalsIgnoreCase("97")) {
                        secBPlaceOth.setVisibility(View.VISIBLE);
                        lineBPlaceOth.setVisibility(View.VISIBLE);
                        secFaciName.setVisibility(View.GONE);
                        lineFaciName.setVisibility(View.GONE);
                        txtFaciName.setText("");
                        secFaciNameDk.setVisibility(View.GONE);
                        lineFaciNameDk.setVisibility(View.GONE);
                        rdogrpFaciNameDk.clearCheck();
                    } else {
                        secBPlaceOth.setVisibility(View.GONE);
                        lineBPlaceOth.setVisibility(View.GONE);
                        txtBPlaceOth.setText("");
                        secFaciName.setVisibility(View.VISIBLE);
                        lineFaciName.setVisibility(View.VISIBLE);
                        secFaciNameDk.setVisibility(View.VISIBLE);
                        lineFaciNameDk.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secBPlaceOth = (LinearLayout) findViewById(R.id.secBPlaceOth);
            lineBPlaceOth = (View) findViewById(R.id.lineBPlaceOth);
            VlblBPlaceOth = (TextView) findViewById(R.id.VlblBPlaceOth);
            txtBPlaceOth = (AutoCompleteTextView) findViewById(R.id.txtBPlaceOth);
            C.setupAutoComplete(TableName,txtBPlaceOth,"BPlaceOth"); //setup autocomplete view by event

            secFaciName = (LinearLayout) findViewById(R.id.secFaciName);
            lineFaciName = (View) findViewById(R.id.lineFaciName);
            VlblFaciName = (TextView) findViewById(R.id.VlblFaciName);
            txtFaciName = (EditText) findViewById(R.id.txtFaciName);
            secFaciNameDk = (LinearLayout) findViewById(R.id.secFaciNameDk);
            lineFaciNameDk = (View) findViewById(R.id.lineFaciNameDk);
            VlblFaciNameDk = (TextView) findViewById(R.id.VlblFaciNameDk);
            rdogrpFaciNameDk = (RadioGroup) findViewById(R.id.rdogrpFaciNameDk);
            rdoFaciNameDk1 = (RadioButton) findViewById(R.id.rdoFaciNameDk1);
            rdoFaciNameDk2 = (RadioButton) findViewById(R.id.rdoFaciNameDk2);

            txtFaciName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 0) {
                        if (rdoFaciNameDk1.isChecked() || rdoFaciNameDk2.isChecked()) {
                            rdogrpFaciNameDk.clearCheck();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            rdogrpFaciNameDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpFaciNameDk = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpFaciNameDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpFaciNameDk.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpFaciNameDk[i];

                        if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2")) {
                            if (txtFaciName.getText().toString().length() > 0) {
                                txtFaciName.setText("");
                            }
                        }

                    }
                }
            });

            seclbl04 = (LinearLayout) findViewById(R.id.seclbl04);
            linelbl04 = (View) findViewById(R.id.linelbl04);
            secDelAtndDoc = (LinearLayout) findViewById(R.id.secDelAtndDoc);
            lineDelAtndDoc = (View) findViewById(R.id.lineDelAtndDoc);
            VlblDelAtndDoc = (TextView) findViewById(R.id.VlblDelAtndDoc);
            chkDelAtndDoc = (CheckBox) findViewById(R.id.chkDelAtndDoc);
            chkDelAtndDoc.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkDelAtndRef.setChecked(false);
                    chkDelAtndDK.setChecked(false);
                }
            });
            secDelAtndNurse = (LinearLayout) findViewById(R.id.secDelAtndNurse);
            lineDelAtndNurse = (View) findViewById(R.id.lineDelAtndNurse);
            VlblDelAtndNurse = (TextView) findViewById(R.id.VlblDelAtndNurse);
            chkDelAtndNurse = (CheckBox) findViewById(R.id.chkDelAtndNurse);
            chkDelAtndNurse.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkDelAtndRef.setChecked(false);
                    chkDelAtndDK.setChecked(false);
                }
            });
            secDelAtndMidwife = (LinearLayout) findViewById(R.id.secDelAtndMidwife);
            lineDelAtndMidwife = (View) findViewById(R.id.lineDelAtndMidwife);
            VlblDelAtndMidwife = (TextView) findViewById(R.id.VlblDelAtndMidwife);
            chkDelAtndMidwife = (CheckBox) findViewById(R.id.chkDelAtndMidwife);
            chkDelAtndMidwife.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkDelAtndRef.setChecked(false);
                    chkDelAtndDK.setChecked(false);
                }
            });
            secDelAtndTBA = (LinearLayout) findViewById(R.id.secDelAtndTBA);
            lineDelAtndTBA = (View) findViewById(R.id.lineDelAtndTBA);
            VlblDelAtndTBA = (TextView) findViewById(R.id.VlblDelAtndTBA);
            chkDelAtndTBA = (CheckBox) findViewById(R.id.chkDelAtndTBA);
            chkDelAtndTBA.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkDelAtndRef.setChecked(false);
                    chkDelAtndDK.setChecked(false);
                }
            });
            secDelAtndCHW = (LinearLayout) findViewById(R.id.secDelAtndCHW);
            lineDelAtndCHW = (View) findViewById(R.id.lineDelAtndCHW);
            VlblDelAtndCHW = (TextView) findViewById(R.id.VlblDelAtndCHW);
            chkDelAtndCHW = (CheckBox) findViewById(R.id.chkDelAtndCHW);
            chkDelAtndCHW.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkDelAtndRef.setChecked(false);
                    chkDelAtndDK.setChecked(false);
                }
            });
            secDelAtndRel = (LinearLayout) findViewById(R.id.secDelAtndRel);
            lineDelAtndRel = (View) findViewById(R.id.lineDelAtndRel);
            VlblDelAtndRel = (TextView) findViewById(R.id.VlblDelAtndRel);
            chkDelAtndRel = (CheckBox) findViewById(R.id.chkDelAtndRel);
            chkDelAtndRel.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkDelAtndRef.setChecked(false);
                    chkDelAtndDK.setChecked(false);
                }
            });
            secDelAtndNon = (LinearLayout) findViewById(R.id.secDelAtndNon);
            lineDelAtndNon = (View) findViewById(R.id.lineDelAtndNon);
            VlblDelAtndNon = (TextView) findViewById(R.id.VlblDelAtndNon);
            chkDelAtndNon = (CheckBox) findViewById(R.id.chkDelAtndNon);
            chkDelAtndNon.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    chkDelAtndRef.setChecked(false);
                    chkDelAtndDK.setChecked(false);
                }
            });
            secDelAtndOth = (LinearLayout) findViewById(R.id.secDelAtndOth);
            lineDelAtndOth = (View) findViewById(R.id.lineDelAtndOth);
            VlblDelAtndOth = (TextView) findViewById(R.id.VlblDelAtndOth);
            chkDelAtndOth = (CheckBox) findViewById(R.id.chkDelAtndOth);
            chkDelAtndOth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkDelAtndRef.setChecked(false);
                        chkDelAtndDK.setChecked(false);
                        secDelAtndOthSp.setVisibility(View.VISIBLE);
                        lineDelAtndOthSp.setVisibility(View.VISIBLE);
                    } else {
                        secDelAtndOthSp.setVisibility(View.GONE);
                        lineDelAtndOthSp.setVisibility(View.GONE);
                        txtDelAtndOthSp.setText("");
                    }
                }
            });
            secDelAtndOthSp = (LinearLayout) findViewById(R.id.secDelAtndOthSp);
            lineDelAtndOthSp = (View) findViewById(R.id.lineDelAtndOthSp);
            VlblDelAtndOthSp = (TextView) findViewById(R.id.VlblDelAtndOthSp);
            txtDelAtndOthSp = (AutoCompleteTextView) findViewById(R.id.txtDelAtndOthSp);
            C.setupAutoComplete(TableName,txtDelAtndOthSp,"DelAtndOthSp"); //setup autocomplete view by event


            secDelAtndDK = (LinearLayout) findViewById(R.id.secDelAtndDK);
            lineDelAtndDK = (View) findViewById(R.id.lineDelAtndDK);
            VlblDelAtndDK = (TextView) findViewById(R.id.VlblDelAtndDK);
            chkDelAtndDK = (CheckBox) findViewById(R.id.chkDelAtndDK);
            chkDelAtndDK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        chkDelAtndDoc.setChecked(false);
                        chkDelAtndNurse.setChecked(false);
                        chkDelAtndMidwife.setChecked(false);
                        chkDelAtndTBA.setChecked(false);
                        chkDelAtndCHW.setChecked(false);
                        chkDelAtndRel.setChecked(false);
                        chkDelAtndNon.setChecked(false);
                        chkDelAtndOth.setChecked(false);
                        txtDelAtndOthSp.setText("");
                        chkDelAtndRef.setChecked(false);
                    }
                }
            });
            secDelAtndRef = (LinearLayout) findViewById(R.id.secDelAtndRef);
            lineDelAtndRef = (View) findViewById(R.id.lineDelAtndRef);
            VlblDelAtndRef = (TextView) findViewById(R.id.VlblDelAtndRef);
            chkDelAtndRef = (CheckBox) findViewById(R.id.chkDelAtndRef);
            chkDelAtndRef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        chkDelAtndDoc.setChecked(false);
                        chkDelAtndNurse.setChecked(false);
                        chkDelAtndMidwife.setChecked(false);
                        chkDelAtndTBA.setChecked(false);
                        chkDelAtndCHW.setChecked(false);
                        chkDelAtndRel.setChecked(false);
                        chkDelAtndNon.setChecked(false);
                        chkDelAtndOth.setChecked(false);
                        txtDelAtndOthSp.setText("");
                        chkDelAtndDK.setChecked(false);
                    }
                }
            });
            secBabySize = (LinearLayout) findViewById(R.id.secBabySize);
            lineBabySize = (View) findViewById(R.id.lineBabySize);
            VlblBabySize = (TextView) findViewById(R.id.VlblBabySize);
            rdogrpBabySize = (RadioGroup) findViewById(R.id.rdogrpBabySize);
            rdoBabySize1 = (RadioButton) findViewById(R.id.rdoBabySize1);
            rdoBabySize2 = (RadioButton) findViewById(R.id.rdoBabySize2);
            rdoBabySize3 = (RadioButton) findViewById(R.id.rdoBabySize3);
            rdoBabySize4 = (RadioButton) findViewById(R.id.rdoBabySize4);
            rdoBabySize5 = (RadioButton) findViewById(R.id.rdoBabySize5);
            rdoBabySize6 = (RadioButton) findViewById(R.id.rdoBabySize6);
            rdoBabySize7 = (RadioButton) findViewById(R.id.rdoBabySize7);
            secWgtMsrd = (LinearLayout) findViewById(R.id.secWgtMsrd);
            lineWgtMsrd = (View) findViewById(R.id.lineWgtMsrd);
            VlblWgtMsrd = (TextView) findViewById(R.id.VlblWgtMsrd);
            rdogrpWgtMsrd = (RadioGroup) findViewById(R.id.rdogrpWgtMsrd);
            rdoWgtMsrd1 = (RadioButton) findViewById(R.id.rdoWgtMsrd1);
            rdoWgtMsrd2 = (RadioButton) findViewById(R.id.rdoWgtMsrd2);
            rdoWgtMsrd3 = (RadioButton) findViewById(R.id.rdoWgtMsrd3);
            rdoWgtMsrd4 = (RadioButton) findViewById(R.id.rdoWgtMsrd4);
            rdogrpWgtMsrd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpWgtMsrd = new String[]{"0", "1", "8", "9"};
                    for (int i = 0; i < rdogrpWgtMsrd.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpWgtMsrd.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpWgtMsrd[i];
                    }

                    if (rbData.equalsIgnoreCase("0")) {
                        secWgtMsrdUnt.setVisibility(View.GONE);
                        lineWgtMsrdUnt.setVisibility(View.GONE);
                        rdogrpWgtMsrdUnt.clearCheck();
                        secWgtPound.setVisibility(View.GONE);
                        lineWgtPound.setVisibility(View.GONE);
                        secWgtGm.setVisibility(View.GONE);
                        lineWgtGm.setVisibility(View.GONE);
                        txtWgtGm.setText("");
                        secWgtDk.setVisibility(View.GONE);
                        lineWgtDk.setVisibility(View.GONE);
                        rdogrpWgtDk.clearCheck();
                        secWgtDtSrc.setVisibility(View.GONE);
                        lineWgtDtSrc.setVisibility(View.GONE);
                        rdogrpWgtDtSrc.clearCheck();
                    } else if (rbData.equalsIgnoreCase("8")) {
                        secWgtMsrdUnt.setVisibility(View.GONE);
                        lineWgtMsrdUnt.setVisibility(View.GONE);
                        rdogrpWgtMsrdUnt.clearCheck();
                        secWgtPound.setVisibility(View.GONE);
                        lineWgtPound.setVisibility(View.GONE);
                        secWgtGm.setVisibility(View.GONE);
                        lineWgtGm.setVisibility(View.GONE);
                        txtWgtGm.setText("");
                        secWgtDk.setVisibility(View.GONE);
                        lineWgtDk.setVisibility(View.GONE);
                        rdogrpWgtDk.clearCheck();
                        secWgtDtSrc.setVisibility(View.GONE);
                        lineWgtDtSrc.setVisibility(View.GONE);
                        rdogrpWgtDtSrc.clearCheck();
                    } else if (rbData.equalsIgnoreCase("9")) {
                        secWgtMsrdUnt.setVisibility(View.GONE);
                        lineWgtMsrdUnt.setVisibility(View.GONE);
                        rdogrpWgtMsrdUnt.clearCheck();
                        secWgtPound.setVisibility(View.GONE);
                        lineWgtPound.setVisibility(View.GONE);
                        secWgtGm.setVisibility(View.GONE);
                        lineWgtGm.setVisibility(View.GONE);
                        txtWgtGm.setText("");
                        secWgtDk.setVisibility(View.GONE);
                        lineWgtDk.setVisibility(View.GONE);
                        rdogrpWgtDk.clearCheck();
                        secWgtDtSrc.setVisibility(View.GONE);
                        lineWgtDtSrc.setVisibility(View.GONE);
                        rdogrpWgtDtSrc.clearCheck();
                    } else {
                        secWgtMsrdUnt.setVisibility(View.VISIBLE);
                        lineWgtMsrdUnt.setVisibility(View.VISIBLE);
                        secWgtPound.setVisibility(View.VISIBLE);
                        lineWgtPound.setVisibility(View.VISIBLE);
                        secWgtGm.setVisibility(View.VISIBLE);
                        lineWgtGm.setVisibility(View.VISIBLE);
                        secWgtDk.setVisibility(View.VISIBLE);
                        lineWgtDk.setVisibility(View.VISIBLE);
                        secWgtDtSrc.setVisibility(View.VISIBLE);
                        lineWgtDtSrc.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secWgtMsrdUnt = (LinearLayout) findViewById(R.id.secWgtMsrdUnt);
            lineWgtMsrdUnt = (View) findViewById(R.id.lineWgtMsrdUnt);
            VlblWgtMsrdUnt = (TextView) findViewById(R.id.VlblWgtMsrdUnt);
            rdogrpWgtMsrdUnt = (RadioGroup) findViewById(R.id.rdogrpWgtMsrdUnt);
            rdoWgtMsrdUnt1 = (RadioButton) findViewById(R.id.rdoWgtMsrdUnt1);
            rdoWgtMsrdUnt2 = (RadioButton) findViewById(R.id.rdoWgtMsrdUnt2);
            secWgtPound = (LinearLayout) findViewById(R.id.secWgtPound);
            lineWgtPound = (View) findViewById(R.id.lineWgtPound);
            secWgtPound.setVisibility(View.GONE);
            lineWgtPound.setVisibility(View.GONE);
            VlblWgtPound = (TextView) findViewById(R.id.VlblWgtPound);
            txtWgtPound = (EditText) findViewById(R.id.txtWgtPound);
            secWgtGm = (LinearLayout) findViewById(R.id.secWgtGm);
            lineWgtGm = (View) findViewById(R.id.lineWgtGm);

            VlblWgtGm = (TextView) findViewById(R.id.VlblWgtGm);
            txtWgtGm = (EditText) findViewById(R.id.txtWgtGm);
            secWgtDk = (LinearLayout) findViewById(R.id.secWgtDk);
            lineWgtDk = (View) findViewById(R.id.lineWgtDk);
            VlblWgtDk = (TextView) findViewById(R.id.VlblWgtDk);
            rdogrpWgtDk = (RadioGroup) findViewById(R.id.rdogrpWgtDk);
            rdoWgtDk1 = (RadioButton) findViewById(R.id.rdoWgtDk1);
            rdoWgtDk2 = (RadioButton) findViewById(R.id.rdoWgtDk2);

            rdogrpWgtMsrdUnt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpChildDOBDk = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpWgtMsrdUnt.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpWgtMsrdUnt.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpChildDOBDk[i];

                        if (rbData.equalsIgnoreCase("1")) {
                            secWgtPound.setVisibility(View.VISIBLE);
                            lineWgtPound.setVisibility(View.VISIBLE);
                            secWgtGm.setVisibility(View.GONE);
                            lineWgtGm.setVisibility(View.GONE);
                        } else if (rbData.equalsIgnoreCase("2")) {
                            secWgtPound.setVisibility(View.GONE);
                            lineWgtPound.setVisibility(View.GONE);
                            secWgtGm.setVisibility(View.VISIBLE);
                            lineWgtGm.setVisibility(View.VISIBLE);
                        }

                    }
                }
            });

            rdogrpWgtDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpWgtDk = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpWgtDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpWgtDk.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpWgtDk[i];

                        if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2")) {
                            if (rdoWgtMsrdUnt1.isChecked()) {
                                if (txtWgtPound.getText().toString().length() > 0) {
                                    txtWgtPound.setText("");
                                }
                            } else if (rdoWgtMsrdUnt2.isChecked()) {
                                if (txtWgtGm.getText().toString().length() > 0) {
                                    txtWgtGm.setText("");
                                }
                            }
                        }
                    }
                }
            });

            txtWgtPound.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 0) {
                        if (rdoWgtDk1.isChecked() || rdoWgtDk2.isChecked()) {
                            rdogrpWgtDk.clearCheck();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            txtWgtGm.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 0) {
                        if (rdoWgtDk1.isChecked() || rdoWgtDk2.isChecked()) {
                            rdogrpWgtDk.clearCheck();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            secWgtDtSrc = (LinearLayout) findViewById(R.id.secWgtDtSrc);
            lineWgtDtSrc = (View) findViewById(R.id.lineWgtDtSrc);
            VlblWgtDtSrc = (TextView) findViewById(R.id.VlblWgtDtSrc);
            rdogrpWgtDtSrc = (RadioGroup) findViewById(R.id.rdogrpWgtDtSrc);
            rdoWgtDtSrc1 = (RadioButton) findViewById(R.id.rdoWgtDtSrc1);
            rdoWgtDtSrc2 = (RadioButton) findViewById(R.id.rdoWgtDtSrc2);
            secBFStatus = (LinearLayout) findViewById(R.id.secBFStatus);
            lineBFStatus = (View) findViewById(R.id.lineBFStatus);
            VlblBFStatus = (TextView) findViewById(R.id.VlblBFStatus);
            rdogrpBFStatus = (RadioGroup) findViewById(R.id.rdogrpBFStatus);
            rdoBFStatus1 = (RadioButton) findViewById(R.id.rdoBFStatus1);
            rdoBFStatus2 = (RadioButton) findViewById(R.id.rdoBFStatus2);
            rdoBFStatus3 = (RadioButton) findViewById(R.id.rdoBFStatus3);
            rdoBFStatus4 = (RadioButton) findViewById(R.id.rdoBFStatus4);
            rdogrpBFStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpBFStatus = new String[]{"0", "1", "8", "9"};
                    for (int i = 0; i < rdogrpBFStatus.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpBFStatus.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpBFStatus[i];
                    }

                    if (rbData.equalsIgnoreCase("0")) {
                        secBFTime.setVisibility(View.GONE);
                        lineBFTime.setVisibility(View.GONE);
                        rdogrpBFTime.clearCheck();
                        secBFTimeDur.setVisibility(View.GONE);
                        lineBFTimeDur.setVisibility(View.GONE);
                        txtBFTimeDur.setText("");
                        secBFTimeOth.setVisibility(View.GONE);
                        lineBFTimeOth.setVisibility(View.GONE);
                        txtBFTimeOth.setText("");
                    } else if (rbData.equalsIgnoreCase("8")) {
                        secBFTime.setVisibility(View.GONE);
                        lineBFTime.setVisibility(View.GONE);
                        rdogrpBFTime.clearCheck();
                        secBFTimeDur.setVisibility(View.GONE);
                        lineBFTimeDur.setVisibility(View.GONE);
                        txtBFTimeDur.setText("");
                        secBFTimeOth.setVisibility(View.GONE);
                        lineBFTimeOth.setVisibility(View.GONE);
                        txtBFTimeOth.setText("");
                    } else if (rbData.equalsIgnoreCase("9")) {
                        secBFTime.setVisibility(View.GONE);
                        lineBFTime.setVisibility(View.GONE);
                        rdogrpBFTime.clearCheck();
                        secBFTimeDur.setVisibility(View.GONE);
                        lineBFTimeDur.setVisibility(View.GONE);
                        txtBFTimeDur.setText("");
                        secBFTimeOth.setVisibility(View.GONE);
                        lineBFTimeOth.setVisibility(View.GONE);
                        txtBFTimeOth.setText("");
                    } else {
                        secBFTime.setVisibility(View.VISIBLE);
                        lineBFTime.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secBFTime = (LinearLayout) findViewById(R.id.secBFTime);
            lineBFTime = (View) findViewById(R.id.lineBFTime);
            VlblBFTime = (TextView) findViewById(R.id.VlblBFTime);
            rdogrpBFTime = (RadioGroup) findViewById(R.id.rdogrpBFTime);
            rdoBFTime1 = (RadioButton) findViewById(R.id.rdoBFTime1);
            rdoBFTime2 = (RadioButton) findViewById(R.id.rdoBFTime2);
            rdoBFTime3 = (RadioButton) findViewById(R.id.rdoBFTime3);
            rdoBFTime4 = (RadioButton) findViewById(R.id.rdoBFTime4);
            rdoBFTime5 = (RadioButton) findViewById(R.id.rdoBFTime5);
            rdoBFTime6 = (RadioButton) findViewById(R.id.rdoBFTime6);
            rdogrpBFTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpBFTime = new String[]{"0", "1", "2", "7", "8", "9"};
                    for (int i = 0; i < rdogrpBFTime.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpBFTime.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpBFTime[i];
                    }

                    if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2")) {
                        secBFTimeDur.setVisibility(View.VISIBLE);
                        lineBFTimeDur.setVisibility(View.VISIBLE);
                        secBFTimeOth.setVisibility(View.GONE);
                        lineBFTimeOth.setVisibility(View.GONE);
                        txtBFTimeOth.setText("");
                    } else if (rbData.equalsIgnoreCase("7")) {
                        secBFTimeDur.setVisibility(View.GONE);
                        lineBFTimeDur.setVisibility(View.GONE);
                        txtBFTimeDur.setText("");
                        secBFTimeOth.setVisibility(View.VISIBLE);
                        lineBFTimeOth.setVisibility(View.VISIBLE);
                    } else {
                        secBFTimeDur.setVisibility(View.GONE);
                        lineBFTimeDur.setVisibility(View.GONE);
                        txtBFTimeDur.setText("");
                        secBFTimeOth.setVisibility(View.GONE);
                        lineBFTimeOth.setVisibility(View.GONE);
                        txtBFTimeOth.setText("");
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secBFTimeDur = (LinearLayout) findViewById(R.id.secBFTimeDur);
            lineBFTimeDur = (View) findViewById(R.id.lineBFTimeDur);
            VlblBFTimeDur = (TextView) findViewById(R.id.VlblBFTimeDur);
            txtBFTimeDur = (EditText) findViewById(R.id.txtBFTimeDur);
            secBFTimeOth = (LinearLayout) findViewById(R.id.secBFTimeOth);
            lineBFTimeOth = (View) findViewById(R.id.lineBFTimeOth);
            VlblBFTimeOth = (TextView) findViewById(R.id.VlblBFTimeOth);
            txtBFTimeOth = (AutoCompleteTextView) findViewById(R.id.txtBFTimeOth);
            C.setupAutoComplete(TableName,txtBFTimeOth,"BFTimeOth"); //setup autocomplete view by event

            secNBRthHead = (LinearLayout) findViewById(R.id.secNBRthHead);
            lineNBRthHead = (View) findViewById(R.id.lineNBRthHead);
            VlblNBRthHead = (TextView) findViewById(R.id.VlblNBRthHead);
            spnNBRthHead = (Spinner) findViewById(R.id.spnNBRthHead);
            List<String> listNBRthHead = new ArrayList<String>();

            listNBRthHead.add("");
            listNBRthHead.add("06-Son");
            listNBRthHead.add("07-Daughter");
            listNBRthHead.add("08-Adopted son");
            listNBRthHead.add("09-Adopted daughter");
            listNBRthHead.add("10-Stepson");
            listNBRthHead.add("11-Stepdaughter");
            listNBRthHead.add("12-Nephew");
            listNBRthHead.add("13-Niece");
            listNBRthHead.add("14-Grandson");
            listNBRthHead.add("15-Granddaughter");
            listNBRthHead.add("20-Brother");
            listNBRthHead.add("21-Sister");
            listNBRthHead.add("22-Stepbrother");
            listNBRthHead.add("23-Stepsister");
            listNBRthHead.add("24-Cousin");
            listNBRthHead.add("25-Unrelated");
            listNBRthHead.add("97-Other");
            listNBRthHead.add("98-Dont know");
            listNBRthHead.add("99-Refused to respond");
            spnNBRthHead.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listNBRthHead)));

            spnNBRthHead.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnNBRthHead.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnNBRthHead.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("97")) {
                        secNBRthHeadOth.setVisibility(View.VISIBLE);
                        lineNBRthHeadOth.setVisibility(View.VISIBLE);
                    } else {
                        secNBRthHeadOth.setVisibility(View.GONE);
                        lineNBRthHeadOth.setVisibility(View.GONE);
                        txtNBRthHeadOth.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secNBRthHeadOth = (LinearLayout) findViewById(R.id.secNBRthHeadOth);
            lineNBRthHeadOth = (View) findViewById(R.id.lineNBRthHeadOth);
            VlblNBRthHeadOth = (TextView) findViewById(R.id.VlblNBRthHeadOth);
            txtNBRthHeadOth = (AutoCompleteTextView) findViewById(R.id.txtNBRthHeadOth);
            C.setupAutoComplete(TableName,txtNBRthHeadOth,"NBRthHeadOth"); //setup autocomplete view by event

        } catch (Exception e) {
            Connection.MessageBox(NBC_NBCareDetail.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(NBC_NBCareDetail.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            NBC_NBCareDetail_DataModel objSave = new NBC_NBCareDetail_DataModel();
            objSave.setNBID(txtNBID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setPGN(txtPGN.getText().toString());
            objSave.setChSl(txtChSl.getText().toString());
            String[] d_rdogrpPregType = new String[]{"1", "2", "3", "4", "5", "8", "9"};
            objSave.setPregType("");
            for (int i = 0; i < rdogrpPregType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpPregType.getChildAt(i);
                if (rb.isChecked()) objSave.setPregType(d_rdogrpPregType[i]);
            }

            objSave.setOutcomeDate(dtpOutcomeDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpOutcomeDate.getText().toString()) : dtpOutcomeDate.getText().toString());
            String[] d_rdogrpOutcomeDateDk = new String[]{"8", "9"};
            objSave.setOutcomeDateDk("");
            for (int i = 0; i < rdogrpOutcomeDateDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpOutcomeDateDk.getChildAt(i);
                if (rb.isChecked()) objSave.setOutcomeDateDk(d_rdogrpOutcomeDateDk[i]);
            }

            objSave.setOutcomeTime(txtOutcomeTime.getText().toString());
            String[] d_rdogrpOutcomeTimeDk = new String[]{"8", "9"};
            objSave.setOutcomeTimeDk("");
            for (int i = 0; i < rdogrpOutcomeTimeDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpOutcomeTimeDk.getChildAt(i);
                if (rb.isChecked()) objSave.setOutcomeTimeDk(d_rdogrpOutcomeTimeDk[i]);
            }

            String[] d_rdogrpOutcomeType = new String[]{"1", "2"};
            objSave.setOutcomeType("");
            for (int i = 0; i < rdogrpOutcomeType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpOutcomeType.getChildAt(i);
                if (rb.isChecked()) objSave.setOutcomeType(d_rdogrpOutcomeType[i]);
            }

            String[] d_rdogrpCryMoveBreathe = new String[]{"0", "1", "8", "9"};
            objSave.setCryMoveBreathe("");
            for (int i = 0; i < rdogrpCryMoveBreathe.getChildCount(); i++) {
                rb = (RadioButton) rdogrpCryMoveBreathe.getChildAt(i);
                if (rb.isChecked()) objSave.setCryMoveBreathe(d_rdogrpCryMoveBreathe[i]);
            }

            objSave.setChildName(txtChildName.getText().toString());
            String[] d_rdogrpChildNameDk = new String[]{"8", "9"};
            objSave.setChildNameDk("");
            for (int i = 0; i < rdogrpChildNameDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpChildNameDk.getChildAt(i);
                if (rb.isChecked()) objSave.setChildNameDk(d_rdogrpChildNameDk[i]);
            }

            objSave.setChildID(txtChildID.getText().toString());
            String[] d_rdogrpChildSex = new String[]{"1", "2", "3", "4", "8"};
            objSave.setChildSex("");
            for (int i = 0; i < rdogrpChildSex.getChildCount(); i++) {
                rb = (RadioButton) rdogrpChildSex.getChildAt(i);
                if (rb.isChecked()) objSave.setChildSex(d_rdogrpChildSex[i]);
            }

            String[] d_rdogrpBirthTiming = new String[]{"1", "2", "3", "8", "9"};
            objSave.setBirthTiming("");
            for (int i = 0; i < rdogrpBirthTiming.getChildCount(); i++) {
                rb = (RadioButton) rdogrpBirthTiming.getChildAt(i);
                if (rb.isChecked()) objSave.setBirthTiming(d_rdogrpBirthTiming[i]);
            }

            objSave.setPregWeek(txtPregWeek.getText().toString());
            String[] d_rdogrpPregWeekDk = new String[]{"8", "9"};
            objSave.setPregWeekDk("");
            for (int i = 0; i < rdogrpPregWeekDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpPregWeekDk.getChildAt(i);
                if (rb.isChecked()) objSave.setPregWeekDk(d_rdogrpPregWeekDk[i]);
            }

            objSave.setExpDOB(dtpExpDOB.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpExpDOB.getText().toString()) : dtpExpDOB.getText().toString());
            String[] d_rdogrpExpDOBDk = new String[]{"8", "9"};
            objSave.setExpDOBDk("");
            for (int i = 0; i < rdogrpExpDOBDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpExpDOBDk.getChildAt(i);
                if (rb.isChecked()) objSave.setExpDOBDk(d_rdogrpExpDOBDk[i]);
            }

            objSave.setChildDOB(dtpChildDOB.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpChildDOB.getText().toString()) : dtpChildDOB.getText().toString());
            String[] d_rdogrpChildDOBDk = new String[]{"8", "9"};
            objSave.setChildDOBDk("");
            for (int i = 0; i < rdogrpChildDOBDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpChildDOBDk.getChildAt(i);
                if (rb.isChecked()) objSave.setChildDOBDk(d_rdogrpChildDOBDk[i]);
            }

            objSave.setBPlace(spnBPlace.getSelectedItemPosition() == 0 ? "" : spnBPlace.getSelectedItem().toString().split("-")[0]);
            objSave.setBPlaceOth(txtBPlaceOth.getText().toString());
            objSave.setFaciName(txtFaciName.getText().toString());
            String[] d_rdogrpFaciNameDk = new String[]{"8", "9"};
            objSave.setFaciNameDk("");
            for (int i = 0; i < rdogrpFaciNameDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpFaciNameDk.getChildAt(i);
                if (rb.isChecked()) objSave.setFaciNameDk(d_rdogrpFaciNameDk[i]);
            }

            objSave.setDelAtndDoc((chkDelAtndDoc.isChecked() ? "1" : (secDelAtndDoc.isShown() ? "2" : "")));
            objSave.setDelAtndNurse((chkDelAtndNurse.isChecked() ? "1" : (secDelAtndNurse.isShown() ? "2" : "")));
            objSave.setDelAtndMidwife((chkDelAtndMidwife.isChecked() ? "1" : (secDelAtndMidwife.isShown() ? "2" : "")));
            objSave.setDelAtndTBA((chkDelAtndTBA.isChecked() ? "1" : (secDelAtndTBA.isShown() ? "2" : "")));
            objSave.setDelAtndCHW((chkDelAtndCHW.isChecked() ? "1" : (secDelAtndCHW.isShown() ? "2" : "")));
            objSave.setDelAtndRel((chkDelAtndRel.isChecked() ? "1" : (secDelAtndRel.isShown() ? "2" : "")));
            objSave.setDelAtndNon((chkDelAtndNon.isChecked() ? "1" : (secDelAtndNon.isShown() ? "2" : "")));
            objSave.setDelAtndOth((chkDelAtndOth.isChecked() ? "1" : (secDelAtndOth.isShown() ? "2" : "")));
            objSave.setDelAtndOthSp(txtDelAtndOthSp.getText().toString());
            objSave.setDelAtndDK((chkDelAtndDK.isChecked() ? "1" : (secDelAtndDK.isShown() ? "2" : "")));
            objSave.setDelAtndRef((chkDelAtndRef.isChecked() ? "1" : (secDelAtndRef.isShown() ? "2" : "")));
            String[] d_rdogrpBabySize = new String[]{"1", "2", "3", "4", "5", "8", "9"};
            objSave.setBabySize("");
            for (int i = 0; i < rdogrpBabySize.getChildCount(); i++) {
                rb = (RadioButton) rdogrpBabySize.getChildAt(i);
                if (rb.isChecked()) objSave.setBabySize(d_rdogrpBabySize[i]);
            }

            String[] d_rdogrpWgtMsrd = new String[]{"0", "1", "8", "9"};
            objSave.setWgtMsrd("");
            for (int i = 0; i < rdogrpWgtMsrd.getChildCount(); i++) {
                rb = (RadioButton) rdogrpWgtMsrd.getChildAt(i);
                if (rb.isChecked()) objSave.setWgtMsrd(d_rdogrpWgtMsrd[i]);
            }

            String[] d_rdogrpWgtMsrdUnt = new String[]{"1", "2"};
            objSave.setWgtMsrdUnt("");
            for (int i = 0; i < rdogrpWgtMsrdUnt.getChildCount(); i++) {
                rb = (RadioButton) rdogrpWgtMsrdUnt.getChildAt(i);
                if (rb.isChecked()) objSave.setWgtMsrdUnt(d_rdogrpWgtMsrdUnt[i]);
            }

            objSave.setWgtPound(txtWgtPound.getText().toString());
            objSave.setWgtGm(txtWgtGm.getText().toString());
            String[] d_rdogrpWgtDk = new String[]{"8", "9"};
            objSave.setWgtDk("");
            for (int i = 0; i < rdogrpWgtDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpWgtDk.getChildAt(i);
                if (rb.isChecked()) objSave.setWgtDk(d_rdogrpWgtDk[i]);
            }

            String[] d_rdogrpWgtDtSrc = new String[]{"1", "2"};
            objSave.setWgtDtSrc("");
            for (int i = 0; i < rdogrpWgtDtSrc.getChildCount(); i++) {
                rb = (RadioButton) rdogrpWgtDtSrc.getChildAt(i);
                if (rb.isChecked()) objSave.setWgtDtSrc(d_rdogrpWgtDtSrc[i]);
            }

            String[] d_rdogrpBFStatus = new String[]{"0", "1", "8", "9"};
            objSave.setBFStatus("");
            for (int i = 0; i < rdogrpBFStatus.getChildCount(); i++) {
                rb = (RadioButton) rdogrpBFStatus.getChildAt(i);
                if (rb.isChecked()) objSave.setBFStatus(d_rdogrpBFStatus[i]);
            }

            String[] d_rdogrpBFTime = new String[]{"0", "1", "2", "7", "8", "9"};
            objSave.setBFTime("");
            for (int i = 0; i < rdogrpBFTime.getChildCount(); i++) {
                rb = (RadioButton) rdogrpBFTime.getChildAt(i);
                if (rb.isChecked()) objSave.setBFTime(d_rdogrpBFTime[i]);
            }

            objSave.setBFTimeDur(txtBFTimeDur.getText().toString());
            objSave.setBFTimeOth(txtBFTimeOth.getText().toString());
            objSave.setNBRthHead(spnNBRthHead.getSelectedItemPosition() == 0 ? "" : spnNBRthHead.getSelectedItem().toString().split("-")[0]);
            objSave.setNBRthHeadOth(txtNBRthHeadOth.getText().toString());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);
            if (status.length() == 0) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("res", "");
                setResult(Activity.RESULT_OK, returnIntent);

                Toast.makeText(NBC_NBCareDetail.this, "Save Successfully...", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(NBC_NBCareDetail.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_NBCareDetail.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        try {
            ResetSectionColor();
            if (txtNBID.getText().toString().length() == 0 & secNBID.isShown()) {
                ValidationMsg += "\nRequired field: Birth Internal ID.";
                secNBID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtMemID.getText().toString().length() == 0 & secMemID.isShown()) {
                ValidationMsg += "\nRequired field: Member ID.";
                secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtHHID.getText().toString().length() == 0 & secHHID.isShown()) {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtPGN.getText().toString().length() == 0 & secPGN.isShown()) {
                ValidationMsg += "\nRequired field: PGN.";
                secPGN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtChSl.getText().toString().length() == 0 & secChSl.isShown()) {
                ValidationMsg += "\nRequired field: Child Sl.";
                secChSl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoPregType1.isChecked() & !rdoPregType2.isChecked() & !rdoPregType3.isChecked() & !rdoPregType4.isChecked() & !rdoPregType5.isChecked() & !rdoPregType6.isChecked() & !rdoPregType7.isChecked() & secPregType.isShown()) {
                ValidationMsg += "\nB1.6 Required field: What was the outcome of the pregnancy..";
                secPregType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpOutcomeDate.getText().toString());
            if (DV.length() != 0 & secOutcomeDate.isShown() & !rdoOutcomeDateDk1.isChecked() & !rdoOutcomeDateDk2.isChecked() & secOutcomeDateDk.isShown()) {
                ValidationMsg += "\nB1.7 Required field/Not a valid date format: What was the date of the outcome? (WRITE DOWN RESPONSE ON THE BLANK; FILL IN AS COMPLETE AS POSSIBLE).";
                secOutcomeDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                ValidationMsg += "\nRequired field: What was the date of the outcome? - Dont know/refused.";
                secOutcomeDateDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtOutcomeTime.getText().length() == 0 & secOutcomeTime.isShown() & !rdoOutcomeTimeDk1.isChecked() & !rdoOutcomeTimeDk2.isChecked() & secOutcomeTimeDk.isShown()) {
                ValidationMsg += "\nB1.8 Required field: What was the time of the outcome? (WRITE DOWN RESPONSE ON THE BLANK; FILL IN AS COMPLETE AS POSSIBLE).";
                secOutcomeTime.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                ValidationMsg += "\nRequired field: Outcome Time dont know/refused.";
                secOutcomeTimeDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoOutcomeType1.isChecked() & !rdoOutcomeType2.isChecked() & secOutcomeType.isShown()) {
                ValidationMsg += "\nRequired field: Outcome Time Type.";
                secOutcomeType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoCryMoveBreathe1.isChecked() & !rdoCryMoveBreathe2.isChecked() & !rdoCryMoveBreathe3.isChecked() & !rdoCryMoveBreathe4.isChecked() & secCryMoveBreathe.isShown()) {
                ValidationMsg += "\nB1.9 Required field: Did the baby cry, move, or breathe immediately upon birth? (CIRCLE ONE RESPONSE).";
                secCryMoveBreathe.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }

            if (txtChildName.getText().toString().length() == 0 & secChildName.isShown() & !rdoChildNameDk1.isChecked() & !rdoChildNameDk2.isChecked() & secChildNameDk.isShown()) {
                ValidationMsg += "\nB1.10 Required field: Record babys name.";
                secChildName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                ValidationMsg += "\nRequired field: Dont know/Refused.";
                secChildNameDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtChildID.getText().toString().length() == 0 & secChildID.isShown()) {
                ValidationMsg += "\nB1.11 Required field: Record baby/[name]s individual ID number.";
                secChildID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoChildSex1.isChecked() & !rdoChildSex2.isChecked() & !rdoChildSex3.isChecked() & !rdoChildSex4.isChecked() & !rdoChildSex5.isChecked() & secChildSex.isShown()) {
                ValidationMsg += "\nB1.12 Required field: What is the sex of baby/[NAME]? (CIRCLE ONE RESPONSE).";
                secChildSex.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoBirthTiming1.isChecked() & !rdoBirthTiming2.isChecked() & !rdoBirthTiming3.isChecked() & !rdoBirthTiming4.isChecked() & !rdoBirthTiming5.isChecked() & secBirthTiming.isShown()) {
                ValidationMsg += "\nB1.13 Required field: Was baby/[name] born early, on time, or later than expected? (PRE-TERM IS DEFINED AS LESS THAN 37 WEEKS).";
                secBirthTiming.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
//            if (txtPregWeek.getText().toString().length() == 0 & secPregWeek.isShown()) {
//                ValidationMsg += "\nB1.14 Required field: How many weeks pregnant were you when you delivered baby/[name]? (WRITE DOWN THE NUMBER IN THE BLANK).";
//                secPregWeek.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
//            }
//            if (secPregWeek.isShown() & (Integer.valueOf(txtPregWeek.getText().toString().length() == 0 ? "03" : txtPregWeek.getText().toString()) < 03 || Integer.valueOf(txtPregWeek.getText().toString().length() == 0 ? "42" : txtPregWeek.getText().toString()) > 42)) {
//                ValidationMsg += "\nB1.14 Value should be between 03 and 42(How many weeks pregnant were you when you delivered baby/[name]? (WRITE DOWN THE NUMBER IN THE BLANK)).";
//                secPregWeek.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
//            }
            if (!rdoBirthTiming1.isChecked() & !rdoPregWeekDk1.isChecked() & !rdoPregWeekDk2.isChecked() & secPregWeekDk.isShown()) {
                if (secPregWeek.isShown() & (Integer.valueOf(txtPregWeek.getText().toString().length() == 0 ? "03" : txtPregWeek.getText().toString()) < 03 || Integer.valueOf(txtPregWeek.getText().toString().length() == 0 ? "42" : txtPregWeek.getText().toString()) > 42)) {
                    ValidationMsg += "\nB1.14 Value should be between 03 and 42(How many weeks pregnant were you when you delivered baby/[name]? (WRITE DOWN THE NUMBER IN THE BLANK)).";
                    secPregWeek.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    ValidationMsg += "\nB11. Required field: Dont know/Refused.";
                    secPregWeekDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }
            }
            if (rdoBirthTiming1.isChecked() & !rdoPregWeekDk1.isChecked() & !rdoPregWeekDk2.isChecked() & secPregWeekDk.isShown()) {
                if (secPregWeek.isShown() & (Integer.valueOf(txtPregWeek.getText().toString().length() == 0 ? "03" : txtPregWeek.getText().toString()) < 03 || Integer.valueOf(txtPregWeek.getText().toString().length() == 0 ? "37" : txtPregWeek.getText().toString()) > 37)) {
                    ValidationMsg += "\nB1.14 Value should be between 03 and 37(How many weeks pregnant were you when you delivered baby/[name]? (WRITE DOWN THE NUMBER IN THE BLANK)).";
                    secPregWeek.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    ValidationMsg += "\nB11. Required field: Dont know/Refused.";
                    secPregWeekDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }
            }
            DV = Global.DateValidate(dtpExpDOB.getText().toString());
            if (DV.length() != 0 & secExpDOB.isShown() & !rdoExpDOBDk1.isChecked() & !rdoExpDOBDk2.isChecked() & secExpDOBDk.isShown()) {
                ValidationMsg += "\nB1.15 Required field/Not a valid date format: What was the expected date of birth of the baby/[name]?.";
                secExpDOB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                ValidationMsg += "\nRequired field: Dont know/Refused.";
                secExpDOBDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpChildDOB.getText().toString());
            if (DV.length() != 0 & secChildDOB.isShown() & !rdoChildDOBDk1.isChecked() & !rdoChildDOBDk2.isChecked() & secChildDOBDk.isShown()) {
                ValidationMsg += "\nB1.16 Required field/Not a valid date format: What was the date of birth of the baby/[name]?.";
                secChildDOB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                ValidationMsg += "\nRequired field: Dont know/refused.";
                secChildDOBDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnBPlace.getSelectedItemPosition() == 0 & secBPlace.isShown()) {
                ValidationMsg += "\nB1.17 Required field: Where was baby/[name] born?.";
                secBPlace.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtBPlaceOth.getText().toString().length() == 0 & secBPlaceOth.isShown()) {
                ValidationMsg += "\nRequired field: Other specify.";
                secBPlaceOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtFaciName.getText().toString().length() == 0 & secFaciName.isShown() & !rdoFaciNameDk1.isChecked() & !rdoFaciNameDk2.isChecked() & secFaciNameDk.isShown()) {
                ValidationMsg += "\nB1.18 Required field: What was the name of health facility where baby/[name] was born?.";
                secFaciName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                ValidationMsg += "\nRequired field: Dont know/refused.";
                secFaciNameDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtDelAtndOthSp.getText().toString().length() == 0 & secDelAtndOthSp.isShown()) {
                ValidationMsg += "\nRequired field: Other specify.";
                secDelAtndOthSp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoBabySize1.isChecked() & !rdoBabySize2.isChecked() & !rdoBabySize3.isChecked() & !rdoBabySize4.isChecked() & !rdoBabySize5.isChecked() & !rdoBabySize6.isChecked() & !rdoBabySize7.isChecked() & secBabySize.isShown()) {
                ValidationMsg += "\nB1.20 Required field: When baby/[name] was born, was baby/[name] very small, small, average, large, or very large? (CIRCLE ONE RESPONSE).";
                secBabySize.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoWgtMsrd1.isChecked() & !rdoWgtMsrd2.isChecked() & !rdoWgtMsrd3.isChecked() & !rdoWgtMsrd4.isChecked() & secWgtMsrd.isShown()) {
                ValidationMsg += "\nB1.21 Required field: Was baby/[name] weighed at birth?.";
                secWgtMsrd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoWgtMsrdUnt1.isChecked() & !rdoWgtMsrdUnt2.isChecked() & secWgtMsrdUnt.isShown()) {
                ValidationMsg += "\nB1.22 Required field: Which measurement system was followed to take the weight?.";
                secWgtMsrdUnt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtWgtPound.getText().toString().length() == 0 & secWgtPound.isShown() & !rdoWgtDk1.isChecked() & !rdoWgtDk2.isChecked() & secWgtDk.isShown() & rdoWgtMsrdUnt1.isChecked()) {
                ValidationMsg += "\nB1.23 Required field: Pound.";
                secWgtPound.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                ValidationMsg += "\nRequired field: Weight Dont know/Refused.";
                secWgtDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtWgtGm.getText().toString().length() == 0 & secWgtGm.isShown() & !rdoWgtDk1.isChecked() & !rdoWgtDk2.isChecked() & secWgtDk.isShown() & rdoWgtMsrdUnt2.isChecked()) {
                ValidationMsg += "\nRequired field: Gm.";
                secWgtGm.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                ValidationMsg += "\nRequired field: Weight Dont know/Refused.";
                secWgtDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
//            if (secWgtGm.isShown() & (Integer.valueOf(txtWgtGm.getText().toString().length() == 0 ? "1" : txtWgtGm.getText().toString()) < 1 || Integer.valueOf(txtWgtGm.getText().toString().length() == 0 ? "9999" : txtWgtGm.getText().toString()) > 9999)) {
//                ValidationMsg += "\nValue should be between 1 and 9999(Gm).";
//                secWgtGm.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
//            }
            if (!rdoWgtDtSrc1.isChecked() & !rdoWgtDtSrc2.isChecked() & secWgtDtSrc.isShown()) {
                ValidationMsg += "\nB1.24 Required field: From where the weight data was collected in earlier question?.";
                secWgtDtSrc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoBFStatus1.isChecked() & !rdoBFStatus2.isChecked() & !rdoBFStatus3.isChecked() & !rdoBFStatus4.isChecked() & secBFStatus.isShown()) {
                ValidationMsg += "\nB1.25 Required field: Did you ever breastfeed baby/[name]?\n(CIRCLE ONE RESPONSE).";
                secBFStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoBFTime1.isChecked() & !rdoBFTime2.isChecked() & !rdoBFTime3.isChecked() & !rdoBFTime4.isChecked() & !rdoBFTime5.isChecked() & !rdoBFTime6.isChecked() & secBFTime.isShown()) {
                ValidationMsg += "\nB1.26 Required field: When was baby/[name] first breastfed?\n(CIRCLE ONE RESPONSE, OR WRITE RESPONSE FOR 7)(IF LESS THAN 1 HOUR, RECORD “00”;IF LESS THAN 24 HOURS,RECORD NUMBER OF HOURS;IF MORE THAN 24 HOURS,RECORD NUMBER OF DAYS).";
                secBFTime.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtBFTimeDur.getText().toString().length() == 0 & secBFTimeDur.isShown()) {
                ValidationMsg += "\nRequired field: Breastfed Duration.";
                secBFTimeDur.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (secBFTimeDur.isShown() & (Integer.valueOf(txtBFTimeDur.getText().toString().length() == 0 ? "00" : txtBFTimeDur.getText().toString()) < 00 || Integer.valueOf(txtBFTimeDur.getText().toString().length() == 0 ? "99" : txtBFTimeDur.getText().toString()) > 99)) {
                ValidationMsg += "\nValue should be between 00 and 99(Breastfed Duration).";
                secBFTimeDur.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtBFTimeOth.getText().toString().length() == 0 & secBFTimeOth.isShown()) {
                ValidationMsg += "\nRequired field: Other specify.";
                secBFTimeOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnNBRthHead.getSelectedItemPosition() == 0 & secNBRthHead.isShown()) {
                ValidationMsg += "\nB1.27 Required field: What is the relationship of baby/[name] to the household head?\n(CIRCLE ONE RESPONSE, OR WRITE RESPONSE FOR 7)\n*Only include this question if relationship with the household head is not asked as part of the household roster.For example: Still birth *.";
                secNBRthHead.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtNBRthHeadOth.getText().toString().length() == 0 & secNBRthHeadOth.isShown()) {
                ValidationMsg += "\nRequired field: Other specify.";
                secNBRthHeadOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor() {
        try {
            secNBID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secPGN.setBackgroundColor(Color.WHITE);
            secChSl.setBackgroundColor(Color.WHITE);
            secPregType.setBackgroundColor(Color.WHITE);
            secOutcomeDate.setBackgroundColor(Color.WHITE);
            secOutcomeDateDk.setBackgroundColor(Color.WHITE);
            secOutcomeTime.setBackgroundColor(Color.WHITE);
            secOutcomeTimeDk.setBackgroundColor(Color.WHITE);
            secOutcomeType.setBackgroundColor(Color.WHITE);
            secCryMoveBreathe.setBackgroundColor(Color.WHITE);
            secChildName.setBackgroundColor(Color.WHITE);
            secChildNameDk.setBackgroundColor(Color.WHITE);
            secChildID.setBackgroundColor(Color.WHITE);
            secChildSex.setBackgroundColor(Color.WHITE);
            secBirthTiming.setBackgroundColor(Color.WHITE);
            secPregWeek.setBackgroundColor(Color.WHITE);
            secPregWeek.setBackgroundColor(Color.WHITE);
            secPregWeekDk.setBackgroundColor(Color.WHITE);
            secExpDOB.setBackgroundColor(Color.WHITE);
            secExpDOBDk.setBackgroundColor(Color.WHITE);
            secChildDOB.setBackgroundColor(Color.WHITE);
            secChildDOBDk.setBackgroundColor(Color.WHITE);
            secBPlace.setBackgroundColor(Color.WHITE);
            secBPlaceOth.setBackgroundColor(Color.WHITE);
            secFaciName.setBackgroundColor(Color.WHITE);
            secFaciNameDk.setBackgroundColor(Color.WHITE);
            secDelAtndOthSp.setBackgroundColor(Color.WHITE);
            secBabySize.setBackgroundColor(Color.WHITE);
            secWgtMsrd.setBackgroundColor(Color.WHITE);
            secWgtMsrdUnt.setBackgroundColor(Color.WHITE);
            secWgtPound.setBackgroundColor(Color.WHITE);
            secWgtGm.setBackgroundColor(Color.WHITE);
            secWgtGm.setBackgroundColor(Color.WHITE);
            secWgtDk.setBackgroundColor(Color.WHITE);
            secWgtDtSrc.setBackgroundColor(Color.WHITE);
            secBFStatus.setBackgroundColor(Color.WHITE);
            secBFTime.setBackgroundColor(Color.WHITE);
            secBFTimeDur.setBackgroundColor(Color.WHITE);
            secBFTimeDur.setBackgroundColor(Color.WHITE);
            secBFTimeOth.setBackgroundColor(Color.WHITE);
            secNBRthHead.setBackgroundColor(Color.WHITE);
            secNBRthHeadOth.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String NBID, String PGN, String ChSl) {
        try {
            RadioButton rb;
            NBC_NBCareDetail_DataModel d = new NBC_NBCareDetail_DataModel();
            String SQL = "Select * from " + TableName + "  Where NBID='" + NBID + "' and PGN='" + PGN + "' and ChSl='" + ChSl + "'";
            List<NBC_NBCareDetail_DataModel> data = d.SelectAll(this, SQL);
            for (NBC_NBCareDetail_DataModel item : data) {
                txtNBID.setText(item.getNBID());
                txtMemID.setText(item.getMemID());
                txtHHID.setText(item.getHHID());
                txtPGN.setText(item.getPGN());
                txtChSl.setText(item.getChSl());
                String[] d_rdogrpPregType = new String[]{"1", "2", "3", "4", "5", "8", "9"};
                for (int i = 0; i < d_rdogrpPregType.length; i++) {
                    if (String.valueOf(item.getPregType()).equals(String.valueOf(d_rdogrpPregType[i]))) {
                        rb = (RadioButton) rdogrpPregType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                dtpOutcomeDate.setText(item.getOutcomeDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getOutcomeDate()));
                String[] d_rdogrpOutcomeDateDk = new String[]{"8", "9"};
                for (int i = 0; i < d_rdogrpOutcomeDateDk.length; i++) {
                    if (String.valueOf(item.getOutcomeDateDk()).equals(String.valueOf(d_rdogrpOutcomeDateDk[i]))) {
                        rb = (RadioButton) rdogrpOutcomeDateDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtOutcomeTime.setText(item.getOutcomeTime());
                String[] d_rdogrpOutcomeTimeDk = new String[]{"8", "9"};
                for (int i = 0; i < d_rdogrpOutcomeTimeDk.length; i++) {
                    if (String.valueOf(item.getOutcomeTimeDk()).equals(String.valueOf(d_rdogrpOutcomeTimeDk[i]))) {
                        rb = (RadioButton) rdogrpOutcomeTimeDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpOutcomeType = new String[]{"1", "2"};
                for (int i = 0; i < d_rdogrpOutcomeType.length; i++) {
                    if (String.valueOf(item.getOutcomeType()).equals(String.valueOf(d_rdogrpOutcomeType[i]))) {
                        rb = (RadioButton) rdogrpOutcomeType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpCryMoveBreathe = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpCryMoveBreathe.length; i++) {
                    if (String.valueOf(item.getCryMoveBreathe()).equals(String.valueOf(d_rdogrpCryMoveBreathe[i]))) {
                        rb = (RadioButton) rdogrpCryMoveBreathe.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtChildName.setText(item.getChildName());
                String[] d_rdogrpChildNameDk = new String[]{"8", "9"};
                for (int i = 0; i < d_rdogrpChildNameDk.length; i++) {
                    if (String.valueOf(item.getChildNameDk()).equals(String.valueOf(d_rdogrpChildNameDk[i]))) {
                        rb = (RadioButton) rdogrpChildNameDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtChildID.setText(item.getChildID());
                String[] d_rdogrpChildSex = new String[]{"1", "2", "3", "4", "8"};
                for (int i = 0; i < d_rdogrpChildSex.length; i++) {
                    if (String.valueOf(item.getChildSex()).equals(String.valueOf(d_rdogrpChildSex[i]))) {
                        rb = (RadioButton) rdogrpChildSex.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpBirthTiming = new String[]{"1", "2", "3", "8", "9"};
                for (int i = 0; i < d_rdogrpBirthTiming.length; i++) {
                    if (String.valueOf(item.getBirthTiming()).equals(String.valueOf(d_rdogrpBirthTiming[i]))) {
                        rb = (RadioButton) rdogrpBirthTiming.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtPregWeek.setText(String.valueOf(item.getPregWeek()));
                String[] d_rdogrpPregWeekDk = new String[]{"8", "9"};
                for (int i = 0; i < d_rdogrpPregWeekDk.length; i++) {
                    if (String.valueOf(item.getPregWeekDk()).equals(String.valueOf(d_rdogrpPregWeekDk[i]))) {
                        rb = (RadioButton) rdogrpPregWeekDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                dtpExpDOB.setText(item.getExpDOB().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getExpDOB()));
                String[] d_rdogrpExpDOBDk = new String[]{"8", "9"};
                for (int i = 0; i < d_rdogrpExpDOBDk.length; i++) {
                    if (String.valueOf(item.getExpDOBDk()).equals(String.valueOf(d_rdogrpExpDOBDk[i]))) {
                        rb = (RadioButton) rdogrpExpDOBDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                dtpChildDOB.setText(item.getChildDOB().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getChildDOB()));
                String[] d_rdogrpChildDOBDk = new String[]{"8", "9"};
                for (int i = 0; i < d_rdogrpChildDOBDk.length; i++) {
                    if (String.valueOf(item.getChildDOBDk()).equals(String.valueOf(d_rdogrpChildDOBDk[i]))) {
                        rb = (RadioButton) rdogrpChildDOBDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnBPlace.setSelection(Global.SpinnerItemPositionAnyLength(spnBPlace, String.valueOf(item.getBPlace())));
                txtBPlaceOth.setText(item.getBPlaceOth());
                txtFaciName.setText(item.getFaciName());
                String[] d_rdogrpFaciNameDk = new String[]{"8", "9"};
                for (int i = 0; i < d_rdogrpFaciNameDk.length; i++) {
                    if (String.valueOf(item.getFaciNameDk()).equals(String.valueOf(d_rdogrpFaciNameDk[i]))) {
                        rb = (RadioButton) rdogrpFaciNameDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                if (String.valueOf(item.getDelAtndDoc()).equals("1")) {
                    chkDelAtndDoc.setChecked(true);
                } else if (String.valueOf(item.getDelAtndDoc()).equals("2")) {
                    chkDelAtndDoc.setChecked(false);
                }
                if (String.valueOf(item.getDelAtndNurse()).equals("1")) {
                    chkDelAtndNurse.setChecked(true);
                } else if (String.valueOf(item.getDelAtndNurse()).equals("2")) {
                    chkDelAtndNurse.setChecked(false);
                }
                if (String.valueOf(item.getDelAtndMidwife()).equals("1")) {
                    chkDelAtndMidwife.setChecked(true);
                } else if (String.valueOf(item.getDelAtndMidwife()).equals("2")) {
                    chkDelAtndMidwife.setChecked(false);
                }
                if (String.valueOf(item.getDelAtndTBA()).equals("1")) {
                    chkDelAtndTBA.setChecked(true);
                } else if (String.valueOf(item.getDelAtndTBA()).equals("2")) {
                    chkDelAtndTBA.setChecked(false);
                }
                if (String.valueOf(item.getDelAtndCHW()).equals("1")) {
                    chkDelAtndCHW.setChecked(true);
                } else if (String.valueOf(item.getDelAtndCHW()).equals("2")) {
                    chkDelAtndCHW.setChecked(false);
                }
                if (String.valueOf(item.getDelAtndRel()).equals("1")) {
                    chkDelAtndRel.setChecked(true);
                } else if (String.valueOf(item.getDelAtndRel()).equals("2")) {
                    chkDelAtndRel.setChecked(false);
                }
                if (String.valueOf(item.getDelAtndNon()).equals("1")) {
                    chkDelAtndNon.setChecked(true);
                } else if (String.valueOf(item.getDelAtndNon()).equals("2")) {
                    chkDelAtndNon.setChecked(false);
                }
                if (String.valueOf(item.getDelAtndOth()).equals("1")) {
                    chkDelAtndOth.setChecked(true);
                } else if (String.valueOf(item.getDelAtndOth()).equals("2")) {
                    chkDelAtndOth.setChecked(false);
                }
                txtDelAtndOthSp.setText(item.getDelAtndOthSp());
                if (String.valueOf(item.getDelAtndDK()).equals("1")) {
                    chkDelAtndDK.setChecked(true);
                } else if (String.valueOf(item.getDelAtndDK()).equals("2")) {
                    chkDelAtndDK.setChecked(false);
                }
                if (String.valueOf(item.getDelAtndRef()).equals("1")) {
                    chkDelAtndRef.setChecked(true);
                } else if (String.valueOf(item.getDelAtndRef()).equals("2")) {
                    chkDelAtndRef.setChecked(false);
                }
                String[] d_rdogrpBabySize = new String[]{"1", "2", "3", "4", "5", "8", "9"};
                for (int i = 0; i < d_rdogrpBabySize.length; i++) {
                    if (String.valueOf(item.getBabySize()).equals(String.valueOf(d_rdogrpBabySize[i]))) {
                        rb = (RadioButton) rdogrpBabySize.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpWgtMsrd = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpWgtMsrd.length; i++) {
                    if (String.valueOf(item.getWgtMsrd()).equals(String.valueOf(d_rdogrpWgtMsrd[i]))) {
                        rb = (RadioButton) rdogrpWgtMsrd.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpWgtMsrdUnt = new String[]{"1", "2"};
                for (int i = 0; i < d_rdogrpWgtMsrdUnt.length; i++) {
                    if (String.valueOf(item.getWgtMsrdUnt()).equals(String.valueOf(d_rdogrpWgtMsrdUnt[i]))) {
                        rb = (RadioButton) rdogrpWgtMsrdUnt.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtWgtPound.setText(String.valueOf(item.getWgtPound()));
                txtWgtGm.setText(String.valueOf(item.getWgtGm()));
                String[] d_rdogrpWgtDk = new String[]{"8", "9"};
                for (int i = 0; i < d_rdogrpWgtDk.length; i++) {
                    if (String.valueOf(item.getWgtDk()).equals(String.valueOf(d_rdogrpWgtDk[i]))) {
                        rb = (RadioButton) rdogrpWgtDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpWgtDtSrc = new String[]{"1", "2"};
                for (int i = 0; i < d_rdogrpWgtDtSrc.length; i++) {
                    if (String.valueOf(item.getWgtDtSrc()).equals(String.valueOf(d_rdogrpWgtDtSrc[i]))) {
                        rb = (RadioButton) rdogrpWgtDtSrc.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpBFStatus = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpBFStatus.length; i++) {
                    if (String.valueOf(item.getBFStatus()).equals(String.valueOf(d_rdogrpBFStatus[i]))) {
                        rb = (RadioButton) rdogrpBFStatus.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpBFTime = new String[]{"0", "1", "2", "7", "8", "9"};
                for (int i = 0; i < d_rdogrpBFTime.length; i++) {
                    if (String.valueOf(item.getBFTime()).equals(String.valueOf(d_rdogrpBFTime[i]))) {
                        rb = (RadioButton) rdogrpBFTime.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtBFTimeDur.setText(String.valueOf(item.getBFTimeDur()));
                txtBFTimeOth.setText(item.getBFTimeOth());
                spnNBRthHead.setSelection(Global.SpinnerItemPositionAnyLength(spnNBRthHead, String.valueOf(item.getNBRthHead())));
                txtNBRthHeadOth.setText(item.getNBRthHeadOth());
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_NBCareDetail.this, e.getMessage());
            return;
        }
    }


    protected Dialog onCreateDialog(int id) {
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mDateSetListener, g.mYear, g.mMonth - 1, g.mDay);
            case TIME_DIALOG:
                return new TimePickerDialog(this, timePickerListener, hour, minute, false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear + 1;
            mDay = dayOfMonth;
            EditText dtpDate;


            dtpDate = (EditText) findViewById(R.id.dtpOutcomeDate);
            if (VariableID.equals("btnOutcomeDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpOutcomeDate);
            } else if (VariableID.equals("btnExpDOB")) {
                dtpDate = (EditText) findViewById(R.id.dtpExpDOB);
            } else if (VariableID.equals("btnChildDOB")) {
                dtpDate = (EditText) findViewById(R.id.dtpChildDOB);
            }
            dtpDate.setText(new StringBuilder()
                    .append(Global.Right("00" + mDay, 2)).append("/")
                    .append(Global.Right("00" + mMonth, 2)).append("/")
                    .append(mYear));
        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;
            EditText tpTime;

            tpTime = (EditText) findViewById(R.id.txtOutcomeTime);
            if (VariableID.equals("btnOutcomeTime")) {
                tpTime = (EditText) findViewById(R.id.txtOutcomeTime);
            }
            tpTime.setText(new StringBuilder().append(Global.Right("00" + hour, 2)).append(":").append(Global.Right("00" + minute, 2)));
        }
    };

    //Generate New Child Serial
    public String NewChildSerial(String MEMID, String PGN) {
        String M = C.ReturnSingleValue("Select cast(ifnull(max(ChSl),0)+1 as varchar(2))CS from NBC_NBCareDetail where MemID='" + MEMID + "' and PGN='" + PGN + "'");
        M = Global.Right("0" + M, 2);
        return M;
    }

    // turning off the GPS if its in on state. to avoid the battery drain.
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}