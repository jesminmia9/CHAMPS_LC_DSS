
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
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.icddrb.champs_lc_dss.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import Common.Connection;
import Common.Global;
import Utility.MySharedPreferences;
import forms_datamodel.NBC_Morbidity_DataModel;

public class NBC_Morbidity extends AppCompatActivity {
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
    LinearLayout seclbl01;
    View linelbl01;
    LinearLayout secMorbiID;
    View lineMorbiID;
    TextView VlblMorbiID;
    EditText txtMorbiID;
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
    LinearLayout secChDiarrhea;
    View lineChDiarrhea;
    TextView VlblChDiarrhea;
    RadioGroup rdogrpChDiarrhea;
    RadioButton rdoChDiarrhea1;
    RadioButton rdoChDiarrhea2;
    RadioButton rdoChDiarrhea3;
    RadioButton rdoChDiarrhea4;
    LinearLayout secDiarrheaTreat;
    View lineDiarrheaTreat;
    TextView VlblDiarrheaTreat;
    RadioGroup rdogrpDiarrheaTreat;
    RadioButton rdoDiarrheaTreat1;
    RadioButton rdoDiarrheaTreat2;
    RadioButton rdoDiarrheaTreat3;
    RadioButton rdoDiarrheaTreat4;
    LinearLayout secDiarrheaTreatPlace;
    View lineDiarrheaTreatPlace;
    TextView VlblDiarrheaTreatPlace;
    RadioGroup rdogrpDiarrheaTreatPlace;
    RadioButton rdoDiarrheaTreatPlace1;
    RadioButton rdoDiarrheaTreatPlace2;
    RadioButton rdoDiarrheaTreatPlace3;
    RadioButton rdoDiarrheaTreatPlace4;
    RadioButton rdoDiarrheaTreatPlace5;
    RadioButton rdoDiarrheaTreatPlace6;
    RadioButton rdoDiarrheaTreatPlace7;
    //RadioButton rdoDiarrheaTreatPlace8;
    RadioButton rdoDiarrheaTreatPlace9;
    RadioButton rdoDiarrheaTreatPlace10;
    RadioButton rdoDiarrheaTreatPlace11;
    RadioButton rdoDiarrheaTreatPlace12;
    RadioButton rdoDiarrheaTreatPlace13;
    LinearLayout secDiarrheaTreatPlaceOth;
    View lineDiarrheaTreatPlaceOth;
    TextView VlblDiarrheaTreatPlaceOth;
    EditText txtDiarrheaTreatPlaceOth;
    LinearLayout secCough2W;
    View lineCough2W;
    TextView VlblCough2W;
    RadioGroup rdogrpCough2W;
    RadioButton rdoCough2W1;
    RadioButton rdoCough2W2;
    RadioButton rdoCough2W3;
    RadioButton rdoCough2W4;
    LinearLayout secFastBrth;
    View lineFastBrth;
    TextView VlblFastBrth;
    RadioGroup rdogrpFastBrth;
    RadioButton rdoFastBrth1;
    RadioButton rdoFastBrth2;
    RadioButton rdoFastBrth3;
    RadioButton rdoFastBrth4;
    LinearLayout secFastBrthCause;
    View lineFastBrthCause;
    TextView VlblFastBrthCause;
    RadioGroup rdogrpFastBrthCause;
    RadioButton rdoFastBrthCause1;
    RadioButton rdoFastBrthCause2;
    RadioButton rdoFastBrthCause3;
    RadioButton rdoFastBrthCause4;
    RadioButton rdoFastBrthCause5;
    LinearLayout secSeekHCare;
    View lineSeekHCare;
    TextView VlblSeekHCare;
    RadioGroup rdogrpSeekHCare;
    RadioButton rdoSeekHCare1;
    RadioButton rdoSeekHCare2;
    RadioButton rdoSeekHCare3;
    RadioButton rdoSeekHCare4;
    LinearLayout secSeekHCarePlace;
    View lineSeekHCarePlace;
    TextView VlblSeekHCarePlace;
    RadioGroup rdogrpSeekHCarePlace;
    RadioButton rdoSeekHCarePlace1;
    RadioButton rdoSeekHCarePlace2;
    RadioButton rdoSeekHCarePlace3;
    RadioButton rdoSeekHCarePlace4;
    RadioButton rdoSeekHCarePlace5;
    RadioButton rdoSeekHCarePlace6;
    RadioButton rdoSeekHCarePlace7;
    //RadioButton rdoSeekHCarePlace8;
    RadioButton rdoSeekHCarePlace9;
    RadioButton rdoSeekHCarePlace10;
    RadioButton rdoSeekHCarePlace11;
    RadioButton rdoSeekHCarePlace12;
    RadioButton rdoSeekHCarePlace13;
    LinearLayout secSeekHCarePlaceOth;
    View lineSeekHCarePlaceOth;
    TextView VlblSeekHCarePlaceOth;
    EditText txtSeekHCarePlaceOth;
    LinearLayout secFever2W;
    View lineFever2W;
    TextView VlblFever2W;
    RadioGroup rdogrpFever2W;
    RadioButton rdoFever2W1;
    RadioButton rdoFever2W2;
    RadioButton rdoFever2W3;
    RadioButton rdoFever2W4;
    LinearLayout secFeverTreat;
    View lineFeverTreat;
    TextView VlblFeverTreat;
    RadioGroup rdogrpFeverTreat;
    RadioButton rdoFeverTreat1;
    RadioButton rdoFeverTreat2;
    RadioButton rdoFeverTreat3;
    RadioButton rdoFeverTreat4;
    LinearLayout secFeverTreatPlace;
    View lineFeverTreatPlace;
    TextView VlblFeverTreatPlace;
    RadioGroup rdogrpFeverTreatPlace;
    RadioButton rdoFeverTreatPlace1;
    RadioButton rdoFeverTreatPlace2;
    RadioButton rdoFeverTreatPlace3;
    RadioButton rdoFeverTreatPlace4;
    RadioButton rdoFeverTreatPlace5;
    RadioButton rdoFeverTreatPlace6;
    RadioButton rdoFeverTreatPlace7;
    //RadioButton rdoFeverTreatPlace8;
    RadioButton rdoFeverTreatPlace9;
    RadioButton rdoFeverTreatPlace10;
    RadioButton rdoFeverTreatPlace11;
    RadioButton rdoFeverTreatPlace12;
    RadioButton rdoFeverTreatPlace13;
    LinearLayout secFeverTreatPlaceOth;
    View lineFeverTreatPlaceOth;
    TextView VlblFeverTreatPlaceOth;
    EditText txtFeverTreatPlaceOth;
    LinearLayout secWeightLost;
    View lineWeightLost;
    TextView VlblWeightLost;
    RadioGroup rdogrpWeightLost;
    RadioButton rdoWeightLost1;
    RadioButton rdoWeightLost2;
    RadioButton rdoWeightLost3;
    RadioButton rdoWeightLost4;
    LinearLayout secPoorWeight;
    View linePoorWeight;
    TextView VlblPoorWeight;
    RadioGroup rdogrpPoorWeight;
    RadioButton rdoPoorWeight1;
    RadioButton rdoPoorWeight2;
    RadioButton rdoPoorWeight3;
    RadioButton rdoPoorWeight4;
    LinearLayout secFeedLP2Week;
    View lineFeedLP2Week;
    TextView VlblFeedLP2Week;
    RadioGroup rdogrpFeedLP2Week;
    RadioButton rdoFeedLP2Week1;
    RadioButton rdoFeedLP2Week2;
    RadioButton rdoFeedLP2Week3;
    RadioButton rdoFeedLP2Week4;
    LinearLayout secUnderweightAge;
    View lineUnderweightAge;
    TextView VlblUnderweightAge;
    RadioGroup rdogrpUnderweightAge;
    RadioButton rdoUnderweightAge1;
    RadioButton rdoUnderweightAge2;
    RadioButton rdoUnderweightAge3;
    RadioButton rdoUnderweightAge4;
    LinearLayout secOverweightAge;
    View lineOverweightAge;
    TextView VlblOverweightAge;
    RadioGroup rdogrpOverweightAge;
    RadioButton rdoOverweightAge1;
    RadioButton rdoOverweightAge2;
    RadioButton rdoOverweightAge3;
    RadioButton rdoOverweightAge4;
    LinearLayout secComMEBaby;
    View lineComMEBaby;
    TextView VlblComMEBaby;
    RadioGroup rdogrpComMEBaby;
    RadioButton rdoComMEBaby1;
    RadioButton rdoComMEBaby2;

     int MODULEID = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String MORBIID = "";
     String MEMID = "";
     String HHID = "";
     String ROUND = "";
     String PGN = "";
     String CHSL = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.nbc_morbidity);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            MORBIID = IDbundle.getString("MorbiID");
            PGN = IDbundle.getString("PGN");
            MEMID = IDbundle.getString("MemID");
            HHID = IDbundle.getString("HHID");
            CHSL = IDbundle.getString("childsl");

            TableName = "NBC_Morbidity";
            MODULEID = 48;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(NBC_Morbidity.this);
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
            Connection.LocalizeLanguage(NBC_Morbidity.this, MODULEID, LANGUAGEID);


            //Hide all skip variables
            secDiarrheaTreat.setVisibility(View.GONE);
            lineDiarrheaTreat.setVisibility(View.GONE);
            secDiarrheaTreatPlace.setVisibility(View.GONE);
            lineDiarrheaTreatPlace.setVisibility(View.GONE);
            secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            secDiarrheaTreat.setVisibility(View.GONE);
            lineDiarrheaTreat.setVisibility(View.GONE);
            secDiarrheaTreatPlace.setVisibility(View.GONE);
            lineDiarrheaTreatPlace.setVisibility(View.GONE);
            secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            secDiarrheaTreat.setVisibility(View.GONE);
            lineDiarrheaTreat.setVisibility(View.GONE);
            secDiarrheaTreatPlace.setVisibility(View.GONE);
            lineDiarrheaTreatPlace.setVisibility(View.GONE);
            secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            secDiarrheaTreatPlace.setVisibility(View.GONE);
            lineDiarrheaTreatPlace.setVisibility(View.GONE);
            secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            secDiarrheaTreatPlace.setVisibility(View.GONE);
            lineDiarrheaTreatPlace.setVisibility(View.GONE);
            secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            secDiarrheaTreatPlace.setVisibility(View.GONE);
            lineDiarrheaTreatPlace.setVisibility(View.GONE);
            secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
            secFastBrth.setVisibility(View.GONE);
            lineFastBrth.setVisibility(View.GONE);
            secFastBrthCause.setVisibility(View.GONE);
            lineFastBrthCause.setVisibility(View.GONE);
            secSeekHCare.setVisibility(View.GONE);
            lineSeekHCare.setVisibility(View.GONE);
            secSeekHCarePlace.setVisibility(View.GONE);
            lineSeekHCarePlace.setVisibility(View.GONE);
            secSeekHCarePlaceOth.setVisibility(View.GONE);
            lineSeekHCarePlaceOth.setVisibility(View.GONE);
            secFastBrth.setVisibility(View.GONE);
            lineFastBrth.setVisibility(View.GONE);
            secFastBrthCause.setVisibility(View.GONE);
            lineFastBrthCause.setVisibility(View.GONE);
            secSeekHCare.setVisibility(View.GONE);
            lineSeekHCare.setVisibility(View.GONE);
            secSeekHCarePlace.setVisibility(View.GONE);
            lineSeekHCarePlace.setVisibility(View.GONE);
            secSeekHCarePlaceOth.setVisibility(View.GONE);
            lineSeekHCarePlaceOth.setVisibility(View.GONE);
            secFastBrth.setVisibility(View.GONE);
            lineFastBrth.setVisibility(View.GONE);
            secFastBrthCause.setVisibility(View.GONE);
            lineFastBrthCause.setVisibility(View.GONE);
            secSeekHCare.setVisibility(View.GONE);
            lineSeekHCare.setVisibility(View.GONE);
            secSeekHCarePlace.setVisibility(View.GONE);
            lineSeekHCarePlace.setVisibility(View.GONE);
            secSeekHCarePlaceOth.setVisibility(View.GONE);
            lineSeekHCarePlaceOth.setVisibility(View.GONE);
            secSeekHCarePlace.setVisibility(View.GONE);
            lineSeekHCarePlace.setVisibility(View.GONE);
            secSeekHCarePlaceOth.setVisibility(View.GONE);
            lineSeekHCarePlaceOth.setVisibility(View.GONE);
            secSeekHCarePlace.setVisibility(View.GONE);
            lineSeekHCarePlace.setVisibility(View.GONE);
            secSeekHCarePlaceOth.setVisibility(View.GONE);
            lineSeekHCarePlaceOth.setVisibility(View.GONE);
            secSeekHCarePlace.setVisibility(View.GONE);
            lineSeekHCarePlace.setVisibility(View.GONE);
            secSeekHCarePlaceOth.setVisibility(View.GONE);
            lineSeekHCarePlaceOth.setVisibility(View.GONE);
            secSeekHCarePlaceOth.setVisibility(View.GONE);
            lineSeekHCarePlaceOth.setVisibility(View.GONE);
            secFeverTreat.setVisibility(View.GONE);
            lineFeverTreat.setVisibility(View.GONE);
            secFeverTreatPlace.setVisibility(View.GONE);
            lineFeverTreatPlace.setVisibility(View.GONE);
            secFeverTreatPlaceOth.setVisibility(View.GONE);
            lineFeverTreatPlaceOth.setVisibility(View.GONE);
            secFeverTreat.setVisibility(View.GONE);
            lineFeverTreat.setVisibility(View.GONE);
            secFeverTreatPlace.setVisibility(View.GONE);
            lineFeverTreatPlace.setVisibility(View.GONE);
            secFeverTreatPlaceOth.setVisibility(View.GONE);
            lineFeverTreatPlaceOth.setVisibility(View.GONE);
            secFeverTreat.setVisibility(View.GONE);
            lineFeverTreat.setVisibility(View.GONE);
            secFeverTreatPlace.setVisibility(View.GONE);
            lineFeverTreatPlace.setVisibility(View.GONE);
            secFeverTreatPlaceOth.setVisibility(View.GONE);
            lineFeverTreatPlaceOth.setVisibility(View.GONE);
            secFeverTreatPlace.setVisibility(View.GONE);
            lineFeverTreatPlace.setVisibility(View.GONE);
            secFeverTreatPlaceOth.setVisibility(View.GONE);
            lineFeverTreatPlaceOth.setVisibility(View.GONE);
            secFeverTreatPlace.setVisibility(View.GONE);
            lineFeverTreatPlace.setVisibility(View.GONE);
            secFeverTreatPlaceOth.setVisibility(View.GONE);
            lineFeverTreatPlaceOth.setVisibility(View.GONE);
            secFeverTreatPlace.setVisibility(View.GONE);
            lineFeverTreatPlace.setVisibility(View.GONE);
            secFeverTreatPlaceOth.setVisibility(View.GONE);
            lineFeverTreatPlaceOth.setVisibility(View.GONE);
            secFeverTreatPlaceOth.setVisibility(View.GONE);
            lineFeverTreatPlaceOth.setVisibility(View.GONE);


            DataSearch(MEMID, HHID, PGN, CHSL);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(NBC_Morbidity.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            seclbl01 = (LinearLayout) findViewById(R.id.seclbl01);
            linelbl01 = (View) findViewById(R.id.linelbl01);
            secMorbiID = (LinearLayout) findViewById(R.id.secMorbiID);
            lineMorbiID = (View) findViewById(R.id.lineMorbiID);
            VlblMorbiID = (TextView) findViewById(R.id.VlblMorbiID);
            txtMorbiID = (EditText) findViewById(R.id.txtMorbiID);
            if (MORBIID.length() == 0) txtMorbiID.setText(Global.Get_UUID(DEVICEID));
            else txtMorbiID.setText(MORBIID);
            txtMorbiID.setEnabled(false);
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
            txtChSl.setText(CHSL);
            txtChSl.setEnabled(false);
            secChDiarrhea = (LinearLayout) findViewById(R.id.secChDiarrhea);
            lineChDiarrhea = (View) findViewById(R.id.lineChDiarrhea);
            VlblChDiarrhea = (TextView) findViewById(R.id.VlblChDiarrhea);
            rdogrpChDiarrhea = (RadioGroup) findViewById(R.id.rdogrpChDiarrhea);
            rdoChDiarrhea1 = (RadioButton) findViewById(R.id.rdoChDiarrhea1);
            rdoChDiarrhea2 = (RadioButton) findViewById(R.id.rdoChDiarrhea2);
            rdoChDiarrhea3 = (RadioButton) findViewById(R.id.rdoChDiarrhea3);
            rdoChDiarrhea4 = (RadioButton) findViewById(R.id.rdoChDiarrhea4);
            rdogrpChDiarrhea.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpChDiarrhea = new String[]{"0", "1", "8", "9"};
                    for (int i = 0; i < rdogrpChDiarrhea.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpChDiarrhea.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpChDiarrhea[i];
                    }

                    if (rbData.equalsIgnoreCase("0")) {
                        secDiarrheaTreat.setVisibility(View.GONE);
                        lineDiarrheaTreat.setVisibility(View.GONE);
                        rdogrpDiarrheaTreat.clearCheck();
                        secDiarrheaTreatPlace.setVisibility(View.GONE);
                        lineDiarrheaTreatPlace.setVisibility(View.GONE);
                        rdogrpDiarrheaTreatPlace.clearCheck();
                        secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        txtDiarrheaTreatPlaceOth.setText("");
                    } else if (rbData.equalsIgnoreCase("8")) {
                        secDiarrheaTreat.setVisibility(View.GONE);
                        lineDiarrheaTreat.setVisibility(View.GONE);
                        rdogrpDiarrheaTreat.clearCheck();
                        secDiarrheaTreatPlace.setVisibility(View.GONE);
                        lineDiarrheaTreatPlace.setVisibility(View.GONE);
                        rdogrpDiarrheaTreatPlace.clearCheck();
                        secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        txtDiarrheaTreatPlaceOth.setText("");
                    } else if (rbData.equalsIgnoreCase("9")) {
                        secDiarrheaTreat.setVisibility(View.GONE);
                        lineDiarrheaTreat.setVisibility(View.GONE);
                        rdogrpDiarrheaTreat.clearCheck();
                        secDiarrheaTreatPlace.setVisibility(View.GONE);
                        lineDiarrheaTreatPlace.setVisibility(View.GONE);
                        rdogrpDiarrheaTreatPlace.clearCheck();
                        secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        txtDiarrheaTreatPlaceOth.setText("");
                    } else {
                        secDiarrheaTreat.setVisibility(View.VISIBLE);
                        lineDiarrheaTreat.setVisibility(View.VISIBLE);
                        secDiarrheaTreatPlace.setVisibility(View.VISIBLE);
                        lineDiarrheaTreatPlace.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secDiarrheaTreat = (LinearLayout) findViewById(R.id.secDiarrheaTreat);
            lineDiarrheaTreat = (View) findViewById(R.id.lineDiarrheaTreat);
            VlblDiarrheaTreat = (TextView) findViewById(R.id.VlblDiarrheaTreat);
            rdogrpDiarrheaTreat = (RadioGroup) findViewById(R.id.rdogrpDiarrheaTreat);
            rdoDiarrheaTreat1 = (RadioButton) findViewById(R.id.rdoDiarrheaTreat1);
            rdoDiarrheaTreat2 = (RadioButton) findViewById(R.id.rdoDiarrheaTreat2);
            rdoDiarrheaTreat3 = (RadioButton) findViewById(R.id.rdoDiarrheaTreat3);
            rdoDiarrheaTreat4 = (RadioButton) findViewById(R.id.rdoDiarrheaTreat4);
            rdogrpDiarrheaTreat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpDiarrheaTreat = new String[]{"0", "1", "8", "9"};
                    for (int i = 0; i < rdogrpDiarrheaTreat.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpDiarrheaTreat.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpDiarrheaTreat[i];
                    }

                    if (rbData.equalsIgnoreCase("0")) {
                        secDiarrheaTreatPlace.setVisibility(View.GONE);
                        lineDiarrheaTreatPlace.setVisibility(View.GONE);
                        rdogrpDiarrheaTreatPlace.clearCheck();
                        secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        txtDiarrheaTreatPlaceOth.setText("");
                    } else if (rbData.equalsIgnoreCase("8")) {
                        secDiarrheaTreatPlace.setVisibility(View.GONE);
                        lineDiarrheaTreatPlace.setVisibility(View.GONE);
                        rdogrpDiarrheaTreatPlace.clearCheck();
                        secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        txtDiarrheaTreatPlaceOth.setText("");
                    } else if (rbData.equalsIgnoreCase("9")) {
                        secDiarrheaTreatPlace.setVisibility(View.GONE);
                        lineDiarrheaTreatPlace.setVisibility(View.GONE);
                        rdogrpDiarrheaTreatPlace.clearCheck();
                        secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        txtDiarrheaTreatPlaceOth.setText("");
                    } else {
                        secDiarrheaTreatPlace.setVisibility(View.VISIBLE);
                        lineDiarrheaTreatPlace.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secDiarrheaTreatPlace = (LinearLayout) findViewById(R.id.secDiarrheaTreatPlace);
            lineDiarrheaTreatPlace = (View) findViewById(R.id.lineDiarrheaTreatPlace);
            VlblDiarrheaTreatPlace = (TextView) findViewById(R.id.VlblDiarrheaTreatPlace);
            rdogrpDiarrheaTreatPlace = (RadioGroup) findViewById(R.id.rdogrpDiarrheaTreatPlace);
            rdoDiarrheaTreatPlace1 = (RadioButton) findViewById(R.id.rdoDiarrheaTreatPlace1);
            rdoDiarrheaTreatPlace2 = (RadioButton) findViewById(R.id.rdoDiarrheaTreatPlace2);
            rdoDiarrheaTreatPlace3 = (RadioButton) findViewById(R.id.rdoDiarrheaTreatPlace3);
            rdoDiarrheaTreatPlace4 = (RadioButton) findViewById(R.id.rdoDiarrheaTreatPlace4);
            rdoDiarrheaTreatPlace5 = (RadioButton) findViewById(R.id.rdoDiarrheaTreatPlace5);
            rdoDiarrheaTreatPlace6 = (RadioButton) findViewById(R.id.rdoDiarrheaTreatPlace6);
            rdoDiarrheaTreatPlace7 = (RadioButton) findViewById(R.id.rdoDiarrheaTreatPlace7);
            //rdoDiarrheaTreatPlace8 = (RadioButton) findViewById(R.id.rdoDiarrheaTreatPlace8);
            rdoDiarrheaTreatPlace9 = (RadioButton) findViewById(R.id.rdoDiarrheaTreatPlace9);
            rdoDiarrheaTreatPlace10 = (RadioButton) findViewById(R.id.rdoDiarrheaTreatPlace10);
            rdoDiarrheaTreatPlace11 = (RadioButton) findViewById(R.id.rdoDiarrheaTreatPlace11);
            rdoDiarrheaTreatPlace12 = (RadioButton) findViewById(R.id.rdoDiarrheaTreatPlace12);
            rdoDiarrheaTreatPlace13 = (RadioButton) findViewById(R.id.rdoDiarrheaTreatPlace13);
            rdogrpDiarrheaTreatPlace.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpDiarrheaTreatPlace = new String[]{"21", "22", "23", "24", "31", "32", "33",  "61", "62", "97", "98", "99"};
                    for (int i = 0; i < rdogrpDiarrheaTreatPlace.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpDiarrheaTreatPlace.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpDiarrheaTreatPlace[i];
                    }

                    if (rbData.equalsIgnoreCase("97")) {
                        secDiarrheaTreatPlaceOth.setVisibility(View.VISIBLE);
                        lineDiarrheaTreatPlaceOth.setVisibility(View.VISIBLE);
                    } else {
                        secDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        lineDiarrheaTreatPlaceOth.setVisibility(View.GONE);
                        txtDiarrheaTreatPlaceOth.setText("");
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secDiarrheaTreatPlaceOth = (LinearLayout) findViewById(R.id.secDiarrheaTreatPlaceOth);
            lineDiarrheaTreatPlaceOth = (View) findViewById(R.id.lineDiarrheaTreatPlaceOth);
            VlblDiarrheaTreatPlaceOth = (TextView) findViewById(R.id.VlblDiarrheaTreatPlaceOth);
            txtDiarrheaTreatPlaceOth = (EditText) findViewById(R.id.txtDiarrheaTreatPlaceOth);
            secCough2W = (LinearLayout) findViewById(R.id.secCough2W);
            lineCough2W = (View) findViewById(R.id.lineCough2W);
            VlblCough2W = (TextView) findViewById(R.id.VlblCough2W);
            rdogrpCough2W = (RadioGroup) findViewById(R.id.rdogrpCough2W);
            rdoCough2W1 = (RadioButton) findViewById(R.id.rdoCough2W1);
            rdoCough2W2 = (RadioButton) findViewById(R.id.rdoCough2W2);
            rdoCough2W3 = (RadioButton) findViewById(R.id.rdoCough2W3);
            rdoCough2W4 = (RadioButton) findViewById(R.id.rdoCough2W4);
            rdogrpCough2W.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpCough2W = new String[]{"0", "1", "8", "9"};
                    for (int i = 0; i < rdogrpCough2W.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpCough2W.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpCough2W[i];
                    }

                    if (rbData.equalsIgnoreCase("0")) {
                        secFastBrth.setVisibility(View.GONE);
                        lineFastBrth.setVisibility(View.GONE);
                        rdogrpFastBrth.clearCheck();
                        secFastBrthCause.setVisibility(View.GONE);
                        lineFastBrthCause.setVisibility(View.GONE);
                        rdogrpFastBrthCause.clearCheck();
                        secSeekHCare.setVisibility(View.GONE);
                        lineSeekHCare.setVisibility(View.GONE);
                        rdogrpSeekHCare.clearCheck();
                        secSeekHCarePlace.setVisibility(View.GONE);
                        lineSeekHCarePlace.setVisibility(View.GONE);
                        rdogrpSeekHCarePlace.clearCheck();
                        secSeekHCarePlaceOth.setVisibility(View.GONE);
                        lineSeekHCarePlaceOth.setVisibility(View.GONE);
                        txtSeekHCarePlaceOth.setText("");
                    } else if (rbData.equalsIgnoreCase("8")) {
                        secFastBrth.setVisibility(View.GONE);
                        lineFastBrth.setVisibility(View.GONE);
                        rdogrpFastBrth.clearCheck();
                        secFastBrthCause.setVisibility(View.GONE);
                        lineFastBrthCause.setVisibility(View.GONE);
                        rdogrpFastBrthCause.clearCheck();
                        secSeekHCare.setVisibility(View.GONE);
                        lineSeekHCare.setVisibility(View.GONE);
                        rdogrpSeekHCare.clearCheck();
                        secSeekHCarePlace.setVisibility(View.GONE);
                        lineSeekHCarePlace.setVisibility(View.GONE);
                        rdogrpSeekHCarePlace.clearCheck();
                        secSeekHCarePlaceOth.setVisibility(View.GONE);
                        lineSeekHCarePlaceOth.setVisibility(View.GONE);
                        txtSeekHCarePlaceOth.setText("");
                    } else if (rbData.equalsIgnoreCase("9")) {
                        secFastBrth.setVisibility(View.GONE);
                        lineFastBrth.setVisibility(View.GONE);
                        rdogrpFastBrth.clearCheck();
                        secFastBrthCause.setVisibility(View.GONE);
                        lineFastBrthCause.setVisibility(View.GONE);
                        rdogrpFastBrthCause.clearCheck();
                        secSeekHCare.setVisibility(View.GONE);
                        lineSeekHCare.setVisibility(View.GONE);
                        rdogrpSeekHCare.clearCheck();
                        secSeekHCarePlace.setVisibility(View.GONE);
                        lineSeekHCarePlace.setVisibility(View.GONE);
                        rdogrpSeekHCarePlace.clearCheck();
                        secSeekHCarePlaceOth.setVisibility(View.GONE);
                        lineSeekHCarePlaceOth.setVisibility(View.GONE);
                        txtSeekHCarePlaceOth.setText("");
                    } else {
                        secFastBrth.setVisibility(View.VISIBLE);
                        lineFastBrth.setVisibility(View.VISIBLE);
                        secFastBrthCause.setVisibility(View.VISIBLE);
                        lineFastBrthCause.setVisibility(View.VISIBLE);
                        secSeekHCare.setVisibility(View.VISIBLE);
                        lineSeekHCare.setVisibility(View.VISIBLE);
                        secSeekHCarePlace.setVisibility(View.VISIBLE);
                        lineSeekHCarePlace.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secFastBrth = (LinearLayout) findViewById(R.id.secFastBrth);
            lineFastBrth = (View) findViewById(R.id.lineFastBrth);
            VlblFastBrth = (TextView) findViewById(R.id.VlblFastBrth);
            rdogrpFastBrth = (RadioGroup) findViewById(R.id.rdogrpFastBrth);
            rdoFastBrth1 = (RadioButton) findViewById(R.id.rdoFastBrth1);
            rdoFastBrth2 = (RadioButton) findViewById(R.id.rdoFastBrth2);
            rdoFastBrth3 = (RadioButton) findViewById(R.id.rdoFastBrth3);
            rdoFastBrth4 = (RadioButton) findViewById(R.id.rdoFastBrth4);
            secFastBrthCause = (LinearLayout) findViewById(R.id.secFastBrthCause);
            lineFastBrthCause = (View) findViewById(R.id.lineFastBrthCause);
            VlblFastBrthCause = (TextView) findViewById(R.id.VlblFastBrthCause);
            rdogrpFastBrthCause = (RadioGroup) findViewById(R.id.rdogrpFastBrthCause);
            rdoFastBrthCause1 = (RadioButton) findViewById(R.id.rdoFastBrthCause1);
            rdoFastBrthCause2 = (RadioButton) findViewById(R.id.rdoFastBrthCause2);
            rdoFastBrthCause3 = (RadioButton) findViewById(R.id.rdoFastBrthCause3);
            rdoFastBrthCause4 = (RadioButton) findViewById(R.id.rdoFastBrthCause4);
            rdoFastBrthCause5 = (RadioButton) findViewById(R.id.rdoFastBrthCause5);

            rdogrpFastBrth.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpFastBrth = new String[]{"0", "1", "8", "9"};
                    for (int i = 0; i < rdogrpFastBrth.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpFastBrth.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpFastBrth[i];
                    }

                    if (rbData.equalsIgnoreCase("1")) {
                        secFastBrthCause.setVisibility(View.VISIBLE);
                        lineFastBrthCause.setVisibility(View.VISIBLE);
                    }
                    else {
                        secFastBrthCause.setVisibility(View.GONE);
                        lineFastBrthCause.setVisibility(View.GONE);
                        rdogrpFastBrthCause.clearCheck();
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

            secSeekHCare = (LinearLayout) findViewById(R.id.secSeekHCare);
            lineSeekHCare = (View) findViewById(R.id.lineSeekHCare);
            VlblSeekHCare = (TextView) findViewById(R.id.VlblSeekHCare);
            rdogrpSeekHCare = (RadioGroup) findViewById(R.id.rdogrpSeekHCare);
            rdoSeekHCare1 = (RadioButton) findViewById(R.id.rdoSeekHCare1);
            rdoSeekHCare2 = (RadioButton) findViewById(R.id.rdoSeekHCare2);
            rdoSeekHCare3 = (RadioButton) findViewById(R.id.rdoSeekHCare3);
            rdoSeekHCare4 = (RadioButton) findViewById(R.id.rdoSeekHCare4);
            rdogrpSeekHCare.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpSeekHCare = new String[]{"0", "1", "8", "9"};
                    for (int i = 0; i < rdogrpSeekHCare.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpSeekHCare.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpSeekHCare[i];
                    }

                    if (rbData.equalsIgnoreCase("0")) {
                        secSeekHCarePlace.setVisibility(View.GONE);
                        lineSeekHCarePlace.setVisibility(View.GONE);
                        rdogrpSeekHCarePlace.clearCheck();
                        secSeekHCarePlaceOth.setVisibility(View.GONE);
                        lineSeekHCarePlaceOth.setVisibility(View.GONE);
                        txtSeekHCarePlaceOth.setText("");
                    } else if (rbData.equalsIgnoreCase("8")) {
                        secSeekHCarePlace.setVisibility(View.GONE);
                        lineSeekHCarePlace.setVisibility(View.GONE);
                        rdogrpSeekHCarePlace.clearCheck();
                        secSeekHCarePlaceOth.setVisibility(View.GONE);
                        lineSeekHCarePlaceOth.setVisibility(View.GONE);
                        txtSeekHCarePlaceOth.setText("");
                    } else if (rbData.equalsIgnoreCase("9")) {
                        secSeekHCarePlace.setVisibility(View.GONE);
                        lineSeekHCarePlace.setVisibility(View.GONE);
                        rdogrpSeekHCarePlace.clearCheck();
                        secSeekHCarePlaceOth.setVisibility(View.GONE);
                        lineSeekHCarePlaceOth.setVisibility(View.GONE);
                        txtSeekHCarePlaceOth.setText("");
                    } else {
                        secSeekHCarePlace.setVisibility(View.VISIBLE);
                        lineSeekHCarePlace.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secSeekHCarePlace = (LinearLayout) findViewById(R.id.secSeekHCarePlace);
            lineSeekHCarePlace = (View) findViewById(R.id.lineSeekHCarePlace);
            VlblSeekHCarePlace = (TextView) findViewById(R.id.VlblSeekHCarePlace);
            rdogrpSeekHCarePlace = (RadioGroup) findViewById(R.id.rdogrpSeekHCarePlace);
            rdoSeekHCarePlace1 = (RadioButton) findViewById(R.id.rdoSeekHCarePlace1);
            rdoSeekHCarePlace2 = (RadioButton) findViewById(R.id.rdoSeekHCarePlace2);
            rdoSeekHCarePlace3 = (RadioButton) findViewById(R.id.rdoSeekHCarePlace3);
            rdoSeekHCarePlace4 = (RadioButton) findViewById(R.id.rdoSeekHCarePlace4);
            rdoSeekHCarePlace5 = (RadioButton) findViewById(R.id.rdoSeekHCarePlace5);
            rdoSeekHCarePlace6 = (RadioButton) findViewById(R.id.rdoSeekHCarePlace6);
            rdoSeekHCarePlace7 = (RadioButton) findViewById(R.id.rdoSeekHCarePlace7);
            //rdoSeekHCarePlace8 = (RadioButton) findViewById(R.id.rdoSeekHCarePlace8);
            rdoSeekHCarePlace9 = (RadioButton) findViewById(R.id.rdoSeekHCarePlace9);
            rdoSeekHCarePlace10 = (RadioButton) findViewById(R.id.rdoSeekHCarePlace10);
            rdoSeekHCarePlace11 = (RadioButton) findViewById(R.id.rdoSeekHCarePlace11);
            rdoSeekHCarePlace12 = (RadioButton) findViewById(R.id.rdoSeekHCarePlace12);
            rdoSeekHCarePlace13 = (RadioButton) findViewById(R.id.rdoSeekHCarePlace13);
            rdogrpSeekHCarePlace.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpSeekHCarePlace = new String[]{"21", "22", "23", "24", "31", "32", "33",  "61", "62", "97", "98", "99"};
                    for (int i = 0; i < rdogrpSeekHCarePlace.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpSeekHCarePlace.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpSeekHCarePlace[i];
                    }

                    if (rbData.equalsIgnoreCase("97")) {
                        secSeekHCarePlaceOth.setVisibility(View.VISIBLE);
                        lineSeekHCarePlaceOth.setVisibility(View.VISIBLE);
                    } else {
                        secSeekHCarePlaceOth.setVisibility(View.GONE);
                        lineSeekHCarePlaceOth.setVisibility(View.GONE);
                        txtSeekHCarePlaceOth.setText("");
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secSeekHCarePlaceOth = (LinearLayout) findViewById(R.id.secSeekHCarePlaceOth);
            lineSeekHCarePlaceOth = (View) findViewById(R.id.lineSeekHCarePlaceOth);
            VlblSeekHCarePlaceOth = (TextView) findViewById(R.id.VlblSeekHCarePlaceOth);
            txtSeekHCarePlaceOth = (EditText) findViewById(R.id.txtSeekHCarePlaceOth);
            secFever2W = (LinearLayout) findViewById(R.id.secFever2W);
            lineFever2W = (View) findViewById(R.id.lineFever2W);
            VlblFever2W = (TextView) findViewById(R.id.VlblFever2W);
            rdogrpFever2W = (RadioGroup) findViewById(R.id.rdogrpFever2W);
            rdoFever2W1 = (RadioButton) findViewById(R.id.rdoFever2W1);
            rdoFever2W2 = (RadioButton) findViewById(R.id.rdoFever2W2);
            rdoFever2W3 = (RadioButton) findViewById(R.id.rdoFever2W3);
            rdoFever2W4 = (RadioButton) findViewById(R.id.rdoFever2W4);
            rdogrpFever2W.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpFever2W = new String[]{"0", "1", "8", "9"};
                    for (int i = 0; i < rdogrpFever2W.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpFever2W.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpFever2W[i];
                    }

                    if (rbData.equalsIgnoreCase("0")) {
                        secFeverTreat.setVisibility(View.GONE);
                        lineFeverTreat.setVisibility(View.GONE);
                        rdogrpFeverTreat.clearCheck();
                        secFeverTreatPlace.setVisibility(View.GONE);
                        lineFeverTreatPlace.setVisibility(View.GONE);
                        rdogrpFeverTreatPlace.clearCheck();
                        secFeverTreatPlaceOth.setVisibility(View.GONE);
                        lineFeverTreatPlaceOth.setVisibility(View.GONE);
                        txtFeverTreatPlaceOth.setText("");
                    } else if (rbData.equalsIgnoreCase("8")) {
                        secFeverTreat.setVisibility(View.GONE);
                        lineFeverTreat.setVisibility(View.GONE);
                        rdogrpFeverTreat.clearCheck();
                        secFeverTreatPlace.setVisibility(View.GONE);
                        lineFeverTreatPlace.setVisibility(View.GONE);
                        rdogrpFeverTreatPlace.clearCheck();
                        secFeverTreatPlaceOth.setVisibility(View.GONE);
                        lineFeverTreatPlaceOth.setVisibility(View.GONE);
                        txtFeverTreatPlaceOth.setText("");
                    } else if (rbData.equalsIgnoreCase("9")) {
                        secFeverTreat.setVisibility(View.GONE);
                        lineFeverTreat.setVisibility(View.GONE);
                        rdogrpFeverTreat.clearCheck();
                        secFeverTreatPlace.setVisibility(View.GONE);
                        lineFeverTreatPlace.setVisibility(View.GONE);
                        rdogrpFeverTreatPlace.clearCheck();
                        secFeverTreatPlaceOth.setVisibility(View.GONE);
                        lineFeverTreatPlaceOth.setVisibility(View.GONE);
                        txtFeverTreatPlaceOth.setText("");
                    } else {
                        secFeverTreat.setVisibility(View.VISIBLE);
                        lineFeverTreat.setVisibility(View.VISIBLE);
                        secFeverTreatPlace.setVisibility(View.VISIBLE);
                        lineFeverTreatPlace.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secFeverTreat = (LinearLayout) findViewById(R.id.secFeverTreat);
            lineFeverTreat = (View) findViewById(R.id.lineFeverTreat);
            VlblFeverTreat = (TextView) findViewById(R.id.VlblFeverTreat);
            rdogrpFeverTreat = (RadioGroup) findViewById(R.id.rdogrpFeverTreat);
            rdoFeverTreat1 = (RadioButton) findViewById(R.id.rdoFeverTreat1);
            rdoFeverTreat2 = (RadioButton) findViewById(R.id.rdoFeverTreat2);
            rdoFeverTreat3 = (RadioButton) findViewById(R.id.rdoFeverTreat3);
            rdoFeverTreat4 = (RadioButton) findViewById(R.id.rdoFeverTreat4);
            rdogrpFeverTreat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpFeverTreat = new String[]{"0", "1", "8", "9"};
                    for (int i = 0; i < rdogrpFeverTreat.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpFeverTreat.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpFeverTreat[i];
                    }

                    if (rbData.equalsIgnoreCase("0")) {
                        secFeverTreatPlace.setVisibility(View.GONE);
                        lineFeverTreatPlace.setVisibility(View.GONE);
                        rdogrpFeverTreatPlace.clearCheck();
                        secFeverTreatPlaceOth.setVisibility(View.GONE);
                        lineFeverTreatPlaceOth.setVisibility(View.GONE);
                        txtFeverTreatPlaceOth.setText("");
                    } else if (rbData.equalsIgnoreCase("8")) {
                        secFeverTreatPlace.setVisibility(View.GONE);
                        lineFeverTreatPlace.setVisibility(View.GONE);
                        rdogrpFeverTreatPlace.clearCheck();
                        secFeverTreatPlaceOth.setVisibility(View.GONE);
                        lineFeverTreatPlaceOth.setVisibility(View.GONE);
                        txtFeverTreatPlaceOth.setText("");
                    } else if (rbData.equalsIgnoreCase("9")) {
                        secFeverTreatPlace.setVisibility(View.GONE);
                        lineFeverTreatPlace.setVisibility(View.GONE);
                        rdogrpFeverTreatPlace.clearCheck();
                        secFeverTreatPlaceOth.setVisibility(View.GONE);
                        lineFeverTreatPlaceOth.setVisibility(View.GONE);
                        txtFeverTreatPlaceOth.setText("");
                    } else {
                        secFeverTreatPlace.setVisibility(View.VISIBLE);
                        lineFeverTreatPlace.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secFeverTreatPlace = (LinearLayout) findViewById(R.id.secFeverTreatPlace);
            lineFeverTreatPlace = (View) findViewById(R.id.lineFeverTreatPlace);
            VlblFeverTreatPlace = (TextView) findViewById(R.id.VlblFeverTreatPlace);
            rdogrpFeverTreatPlace = (RadioGroup) findViewById(R.id.rdogrpFeverTreatPlace);
            rdoFeverTreatPlace1 = (RadioButton) findViewById(R.id.rdoFeverTreatPlace1);
            rdoFeverTreatPlace2 = (RadioButton) findViewById(R.id.rdoFeverTreatPlace2);
            rdoFeverTreatPlace3 = (RadioButton) findViewById(R.id.rdoFeverTreatPlace3);
            rdoFeverTreatPlace4 = (RadioButton) findViewById(R.id.rdoFeverTreatPlace4);
            rdoFeverTreatPlace5 = (RadioButton) findViewById(R.id.rdoFeverTreatPlace5);
            rdoFeverTreatPlace6 = (RadioButton) findViewById(R.id.rdoFeverTreatPlace6);
            rdoFeverTreatPlace7 = (RadioButton) findViewById(R.id.rdoFeverTreatPlace7);
            //rdoFeverTreatPlace8 = (RadioButton) findViewById(R.id.rdoFeverTreatPlace8);
            rdoFeverTreatPlace9 = (RadioButton) findViewById(R.id.rdoFeverTreatPlace9);
            rdoFeverTreatPlace10 = (RadioButton) findViewById(R.id.rdoFeverTreatPlace10);
            rdoFeverTreatPlace11 = (RadioButton) findViewById(R.id.rdoFeverTreatPlace11);
            rdoFeverTreatPlace12 = (RadioButton) findViewById(R.id.rdoFeverTreatPlace12);
            rdoFeverTreatPlace13 = (RadioButton) findViewById(R.id.rdoFeverTreatPlace13);
            rdogrpFeverTreatPlace.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpFeverTreatPlace = new String[]{"21", "22", "23", "24", "31", "32", "33",  "61", "62", "97", "98", "99"};
                    for (int i = 0; i < rdogrpFeverTreatPlace.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpFeverTreatPlace.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpFeverTreatPlace[i];
                    }

                    if (rbData.equalsIgnoreCase("97")) {
                        secFeverTreatPlaceOth.setVisibility(View.VISIBLE);
                        lineFeverTreatPlaceOth.setVisibility(View.VISIBLE);
                    } else {
                        secFeverTreatPlaceOth.setVisibility(View.GONE);
                        lineFeverTreatPlaceOth.setVisibility(View.GONE);
                        txtFeverTreatPlaceOth.setText("");
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secFeverTreatPlaceOth = (LinearLayout) findViewById(R.id.secFeverTreatPlaceOth);
            lineFeverTreatPlaceOth = (View) findViewById(R.id.lineFeverTreatPlaceOth);
            VlblFeverTreatPlaceOth = (TextView) findViewById(R.id.VlblFeverTreatPlaceOth);
            txtFeverTreatPlaceOth = (EditText) findViewById(R.id.txtFeverTreatPlaceOth);
            secWeightLost = (LinearLayout) findViewById(R.id.secWeightLost);
            lineWeightLost = (View) findViewById(R.id.lineWeightLost);
            VlblWeightLost = (TextView) findViewById(R.id.VlblWeightLost);
            rdogrpWeightLost = (RadioGroup) findViewById(R.id.rdogrpWeightLost);
            rdoWeightLost1 = (RadioButton) findViewById(R.id.rdoWeightLost1);
            rdoWeightLost2 = (RadioButton) findViewById(R.id.rdoWeightLost2);
            rdoWeightLost3 = (RadioButton) findViewById(R.id.rdoWeightLost3);
            rdoWeightLost4 = (RadioButton) findViewById(R.id.rdoWeightLost4);
            secPoorWeight = (LinearLayout) findViewById(R.id.secPoorWeight);
            linePoorWeight = (View) findViewById(R.id.linePoorWeight);
            VlblPoorWeight = (TextView) findViewById(R.id.VlblPoorWeight);
            rdogrpPoorWeight = (RadioGroup) findViewById(R.id.rdogrpPoorWeight);
            rdoPoorWeight1 = (RadioButton) findViewById(R.id.rdoPoorWeight1);
            rdoPoorWeight2 = (RadioButton) findViewById(R.id.rdoPoorWeight2);
            rdoPoorWeight3 = (RadioButton) findViewById(R.id.rdoPoorWeight3);
            rdoPoorWeight4 = (RadioButton) findViewById(R.id.rdoPoorWeight4);
            secFeedLP2Week = (LinearLayout) findViewById(R.id.secFeedLP2Week);
            lineFeedLP2Week = (View) findViewById(R.id.lineFeedLP2Week);
            VlblFeedLP2Week = (TextView) findViewById(R.id.VlblFeedLP2Week);
            rdogrpFeedLP2Week = (RadioGroup) findViewById(R.id.rdogrpFeedLP2Week);
            rdoFeedLP2Week1 = (RadioButton) findViewById(R.id.rdoFeedLP2Week1);
            rdoFeedLP2Week2 = (RadioButton) findViewById(R.id.rdoFeedLP2Week2);
            rdoFeedLP2Week3 = (RadioButton) findViewById(R.id.rdoFeedLP2Week3);
            rdoFeedLP2Week4 = (RadioButton) findViewById(R.id.rdoFeedLP2Week4);
            secUnderweightAge = (LinearLayout) findViewById(R.id.secUnderweightAge);
            lineUnderweightAge = (View) findViewById(R.id.lineUnderweightAge);
            VlblUnderweightAge = (TextView) findViewById(R.id.VlblUnderweightAge);
            rdogrpUnderweightAge = (RadioGroup) findViewById(R.id.rdogrpUnderweightAge);
            rdoUnderweightAge1 = (RadioButton) findViewById(R.id.rdoUnderweightAge1);
            rdoUnderweightAge2 = (RadioButton) findViewById(R.id.rdoUnderweightAge2);
            rdoUnderweightAge3 = (RadioButton) findViewById(R.id.rdoUnderweightAge3);
            rdoUnderweightAge4 = (RadioButton) findViewById(R.id.rdoUnderweightAge4);
            secOverweightAge = (LinearLayout) findViewById(R.id.secOverweightAge);
            lineOverweightAge = (View) findViewById(R.id.lineOverweightAge);
            VlblOverweightAge = (TextView) findViewById(R.id.VlblOverweightAge);
            rdogrpOverweightAge = (RadioGroup) findViewById(R.id.rdogrpOverweightAge);
            rdoOverweightAge1 = (RadioButton) findViewById(R.id.rdoOverweightAge1);
            rdoOverweightAge2 = (RadioButton) findViewById(R.id.rdoOverweightAge2);
            rdoOverweightAge3 = (RadioButton) findViewById(R.id.rdoOverweightAge3);
            rdoOverweightAge4 = (RadioButton) findViewById(R.id.rdoOverweightAge4);
            secComMEBaby = (LinearLayout) findViewById(R.id.secComMEBaby);
            lineComMEBaby = (View) findViewById(R.id.lineComMEBaby);
            VlblComMEBaby = (TextView) findViewById(R.id.VlblComMEBaby);
            rdogrpComMEBaby = (RadioGroup) findViewById(R.id.rdogrpComMEBaby);
            rdoComMEBaby1 = (RadioButton) findViewById(R.id.rdoComMEBaby1);
            rdoComMEBaby2 = (RadioButton) findViewById(R.id.rdoComMEBaby2);
        } catch (Exception e) {
            Connection.MessageBox(NBC_Morbidity.this, e.getMessage());
            return;
        }
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(NBC_Morbidity.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            NBC_Morbidity_DataModel objSave = new NBC_Morbidity_DataModel();
            objSave.setMorbiID(txtMorbiID.getText().toString());
            objSave.setMemID(txtMemID.getText().toString());
            objSave.setHHID(txtHHID.getText().toString());
            objSave.setPGN(txtPGN.getText().toString());
            objSave.setChSl(txtChSl.getText().toString());
            String[] d_rdogrpChDiarrhea = new String[]{"0", "1", "8", "9"};
            objSave.setChDiarrhea("");
            for (int i = 0; i < rdogrpChDiarrhea.getChildCount(); i++) {
                rb = (RadioButton) rdogrpChDiarrhea.getChildAt(i);
                if (rb.isChecked()) objSave.setChDiarrhea(d_rdogrpChDiarrhea[i]);
            }

            String[] d_rdogrpDiarrheaTreat = new String[]{"0", "1", "8", "9"};
            objSave.setDiarrheaTreat("");
            for (int i = 0; i < rdogrpDiarrheaTreat.getChildCount(); i++) {
                rb = (RadioButton) rdogrpDiarrheaTreat.getChildAt(i);
                if (rb.isChecked()) objSave.setDiarrheaTreat(d_rdogrpDiarrheaTreat[i]);
            }

            String[] d_rdogrpDiarrheaTreatPlace = new String[]{"21", "22", "23", "24", "31", "32", "33",  "61", "62", "97", "98", "99"};
            objSave.setDiarrheaTreatPlace("");
            for (int i = 0; i < rdogrpDiarrheaTreatPlace.getChildCount(); i++) {
                rb = (RadioButton) rdogrpDiarrheaTreatPlace.getChildAt(i);
                if (rb.isChecked()) objSave.setDiarrheaTreatPlace(d_rdogrpDiarrheaTreatPlace[i]);
            }

            objSave.setDiarrheaTreatPlaceOth(txtDiarrheaTreatPlaceOth.getText().toString());
            String[] d_rdogrpCough2W = new String[]{"0", "1", "8", "9"};
            objSave.setCough2W("");
            for (int i = 0; i < rdogrpCough2W.getChildCount(); i++) {
                rb = (RadioButton) rdogrpCough2W.getChildAt(i);
                if (rb.isChecked()) objSave.setCough2W(d_rdogrpCough2W[i]);
            }

            String[] d_rdogrpFastBrth = new String[]{"0", "1", "8", "9"};
            objSave.setFastBrth("");
            for (int i = 0; i < rdogrpFastBrth.getChildCount(); i++) {
                rb = (RadioButton) rdogrpFastBrth.getChildAt(i);
                if (rb.isChecked()) objSave.setFastBrth(d_rdogrpFastBrth[i]);
            }

            String[] d_rdogrpFastBrthCause = new String[]{"1", "2", "3", "7", "8"};
            objSave.setFastBrthCause("");
            for (int i = 0; i < rdogrpFastBrthCause.getChildCount(); i++) {
                rb = (RadioButton) rdogrpFastBrthCause.getChildAt(i);
                if (rb.isChecked()) objSave.setFastBrthCause(d_rdogrpFastBrthCause[i]);
            }

            String[] d_rdogrpSeekHCare = new String[]{"0", "1", "8", "9"};
            objSave.setSeekHCare("");
            for (int i = 0; i < rdogrpSeekHCare.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSeekHCare.getChildAt(i);
                if (rb.isChecked()) objSave.setSeekHCare(d_rdogrpSeekHCare[i]);
            }

            String[] d_rdogrpSeekHCarePlace = new String[]{"21", "22", "23", "24", "31", "32", "33",  "61", "62", "97", "98", "99"};
            objSave.setSeekHCarePlace("");
            for (int i = 0; i < rdogrpSeekHCarePlace.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSeekHCarePlace.getChildAt(i);
                if (rb.isChecked()) objSave.setSeekHCarePlace(d_rdogrpSeekHCarePlace[i]);
            }

            objSave.setSeekHCarePlaceOth(txtSeekHCarePlaceOth.getText().toString());
            String[] d_rdogrpFever2W = new String[]{"0", "1", "8", "9"};
            objSave.setFever2W("");
            for (int i = 0; i < rdogrpFever2W.getChildCount(); i++) {
                rb = (RadioButton) rdogrpFever2W.getChildAt(i);
                if (rb.isChecked()) objSave.setFever2W(d_rdogrpFever2W[i]);
            }

            String[] d_rdogrpFeverTreat = new String[]{"0", "1", "8", "9"};
            objSave.setFeverTreat("");
            for (int i = 0; i < rdogrpFeverTreat.getChildCount(); i++) {
                rb = (RadioButton) rdogrpFeverTreat.getChildAt(i);
                if (rb.isChecked()) objSave.setFeverTreat(d_rdogrpFeverTreat[i]);
            }

            String[] d_rdogrpFeverTreatPlace = new String[]{"21", "22", "23", "24", "31", "32", "33",  "61", "62", "97", "98", "99"};
            objSave.setFeverTreatPlace("");
            for (int i = 0; i < rdogrpFeverTreatPlace.getChildCount(); i++) {
                rb = (RadioButton) rdogrpFeverTreatPlace.getChildAt(i);
                if (rb.isChecked()) objSave.setFeverTreatPlace(d_rdogrpFeverTreatPlace[i]);
            }

            objSave.setFeverTreatPlaceOth(txtFeverTreatPlaceOth.getText().toString());
            String[] d_rdogrpWeightLost = new String[]{"0", "1", "8", "9"};
            objSave.setWeightLost("");
            for (int i = 0; i < rdogrpWeightLost.getChildCount(); i++) {
                rb = (RadioButton) rdogrpWeightLost.getChildAt(i);
                if (rb.isChecked()) objSave.setWeightLost(d_rdogrpWeightLost[i]);
            }

            String[] d_rdogrpPoorWeight = new String[]{"0", "1", "8", "9"};
            objSave.setPoorWeight("");
            for (int i = 0; i < rdogrpPoorWeight.getChildCount(); i++) {
                rb = (RadioButton) rdogrpPoorWeight.getChildAt(i);
                if (rb.isChecked()) objSave.setPoorWeight(d_rdogrpPoorWeight[i]);
            }

            String[] d_rdogrpFeedLP2Week = new String[]{"0", "1", "8", "9"};
            objSave.setFeedLP2Week("");
            for (int i = 0; i < rdogrpFeedLP2Week.getChildCount(); i++) {
                rb = (RadioButton) rdogrpFeedLP2Week.getChildAt(i);
                if (rb.isChecked()) objSave.setFeedLP2Week(d_rdogrpFeedLP2Week[i]);
            }

            String[] d_rdogrpUnderweightAge = new String[]{"0", "1", "8", "9"};
            objSave.setUnderweightAge("");
            for (int i = 0; i < rdogrpUnderweightAge.getChildCount(); i++) {
                rb = (RadioButton) rdogrpUnderweightAge.getChildAt(i);
                if (rb.isChecked()) objSave.setUnderweightAge(d_rdogrpUnderweightAge[i]);
            }

            String[] d_rdogrpOverweightAge = new String[]{"0", "1", "8", "9"};
            objSave.setOverweightAge("");
            for (int i = 0; i < rdogrpOverweightAge.getChildCount(); i++) {
                rb = (RadioButton) rdogrpOverweightAge.getChildAt(i);
                if (rb.isChecked()) objSave.setOverweightAge(d_rdogrpOverweightAge[i]);
            }

            String[] d_rdogrpComMEBaby = new String[]{"0", "1"};
            objSave.setComMEBaby("");
            for (int i = 0; i < rdogrpComMEBaby.getChildCount(); i++) {
                rb = (RadioButton) rdogrpComMEBaby.getChildAt(i);
                if (rb.isChecked()) objSave.setComMEBaby(d_rdogrpComMEBaby[i]);
            }

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

                Toast.makeText(NBC_Morbidity.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Connection.MessageBox(NBC_Morbidity.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_Morbidity.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        String ValidationMsg = "";
        String DV = "";
        try {
            ResetSectionColor();
            if (txtMorbiID.getText().toString().length() == 0 & secMorbiID.isShown()) {
                ValidationMsg += "\nRequired field: Birth Internal ID.";
                secMorbiID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
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
            if (!rdoChDiarrhea1.isChecked() & !rdoChDiarrhea2.isChecked() & !rdoChDiarrhea3.isChecked() & !rdoChDiarrhea4.isChecked() & secChDiarrhea.isShown()) {
                ValidationMsg += "\nB5.3 Required field: Did baby/[name] have diarrhea (3 or more loose stools per day) in the past two weeks?  (CIRCLE ONE RESPONSE).";
                secChDiarrhea.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoDiarrheaTreat1.isChecked() & !rdoDiarrheaTreat2.isChecked() & !rdoDiarrheaTreat3.isChecked() & !rdoDiarrheaTreat4.isChecked() & secDiarrheaTreat.isShown()) {
                ValidationMsg += "\nB5.4 Required field: Did you/caregiver seek health care/treatment for baby/[name]s diarrhea? (CIRCLE ONE RESPONSE).";
                secDiarrheaTreat.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            //if (!rdoDiarrheaTreatPlace1.isChecked() & !rdoDiarrheaTreatPlace2.isChecked() & !rdoDiarrheaTreatPlace3.isChecked() & !rdoDiarrheaTreatPlace4.isChecked() & !rdoDiarrheaTreatPlace5.isChecked() & !rdoDiarrheaTreatPlace6.isChecked() & !rdoDiarrheaTreatPlace7.isChecked() & !rdoDiarrheaTreatPlace8.isChecked() & !rdoDiarrheaTreatPlace9.isChecked() & !rdoDiarrheaTreatPlace10.isChecked() & !rdoDiarrheaTreatPlace11.isChecked() & !rdoDiarrheaTreatPlace12.isChecked() & !rdoDiarrheaTreatPlace13.isChecked() & secDiarrheaTreatPlace.isShown()) {
            if (!rdoDiarrheaTreatPlace1.isChecked() & !rdoDiarrheaTreatPlace2.isChecked() & !rdoDiarrheaTreatPlace3.isChecked() & !rdoDiarrheaTreatPlace4.isChecked() & !rdoDiarrheaTreatPlace5.isChecked() & !rdoDiarrheaTreatPlace6.isChecked() & !rdoDiarrheaTreatPlace7.isChecked() & !rdoDiarrheaTreatPlace9.isChecked() & !rdoDiarrheaTreatPlace10.isChecked() & !rdoDiarrheaTreatPlace11.isChecked() & !rdoDiarrheaTreatPlace12.isChecked() & !rdoDiarrheaTreatPlace13.isChecked() & secDiarrheaTreatPlace.isShown()) {
                ValidationMsg += "\nB5.5 Required field: Where did you/caregiver seek health care/treatment for baby/[name]s diarrhea? PROBE TO IDENTIFY THE LAST SOURCE (CIRCLE ONE RESPONSE).";
                secDiarrheaTreatPlace.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtDiarrheaTreatPlaceOth.getText().toString().length() == 0 & secDiarrheaTreatPlaceOth.isShown()) {
                ValidationMsg += "\nRequired field: Other, specify.";
                secDiarrheaTreatPlaceOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoCough2W1.isChecked() & !rdoCough2W2.isChecked() & !rdoCough2W3.isChecked() & !rdoCough2W4.isChecked() & secCough2W.isShown()) {
                ValidationMsg += "\nB5.6 Required field: Did baby/[name] have an illness with a cough in the past two weeks? (CIRCLE ONE RESPONSE).";
                secCough2W.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoFastBrth1.isChecked() & !rdoFastBrth2.isChecked() & !rdoFastBrth3.isChecked() & !rdoFastBrth4.isChecked() & secFastBrth.isShown()) {
                ValidationMsg += "\nB5.7 Required field: Did baby  have fast, short, rapid breaths or difficulty breathing in the past two weeks? (CIRCLE ONE RESPONSE).";
                secFastBrth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoFastBrthCause1.isChecked() & !rdoFastBrthCause2.isChecked() & !rdoFastBrthCause3.isChecked() & !rdoFastBrthCause4.isChecked() & !rdoFastBrthCause5.isChecked() & secFastBrthCause.isShown()) {
                ValidationMsg += "\nB5.8 Required field: Was the fast or difficult breathing due to a problem in the chest or to a blocked or runny nose?.";
                secFastBrthCause.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSeekHCare1.isChecked() & !rdoSeekHCare2.isChecked() & !rdoSeekHCare3.isChecked() & !rdoSeekHCare4.isChecked() & secSeekHCare.isShown()) {
                ValidationMsg += "\nB5.9 Required field: Did you/caregiver seek health care/treatment for babys illness?.";
                secSeekHCare.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            //if (!rdoSeekHCarePlace1.isChecked() & !rdoSeekHCarePlace2.isChecked() & !rdoSeekHCarePlace3.isChecked() & !rdoSeekHCarePlace4.isChecked() & !rdoSeekHCarePlace5.isChecked() & !rdoSeekHCarePlace6.isChecked() & !rdoSeekHCarePlace7.isChecked() & !rdoSeekHCarePlace8.isChecked() & !rdoSeekHCarePlace9.isChecked() & !rdoSeekHCarePlace10.isChecked() & !rdoSeekHCarePlace11.isChecked() & !rdoSeekHCarePlace12.isChecked() & !rdoSeekHCarePlace13.isChecked() & secSeekHCarePlace.isShown()) {
            if (!rdoSeekHCarePlace1.isChecked() & !rdoSeekHCarePlace2.isChecked() & !rdoSeekHCarePlace3.isChecked() & !rdoSeekHCarePlace4.isChecked() & !rdoSeekHCarePlace5.isChecked() & !rdoSeekHCarePlace6.isChecked() & !rdoSeekHCarePlace7.isChecked() & !rdoSeekHCarePlace9.isChecked() & !rdoSeekHCarePlace10.isChecked() & !rdoSeekHCarePlace11.isChecked() & !rdoSeekHCarePlace12.isChecked() & !rdoSeekHCarePlace13.isChecked() & secSeekHCarePlace.isShown()) {
                ValidationMsg += "\nB5.10 Required field: Where did you/caregiver seek health care/treatment for baby/[name]’s illness PROBE TO IDENTIFY THE LAST SOURCE (CIRCLE ONE RESPONSE).";
                secSeekHCarePlace.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtSeekHCarePlaceOth.getText().toString().length() == 0 & secSeekHCarePlaceOth.isShown()) {
                ValidationMsg += "\nRequired field: Other, specify.";
                secSeekHCarePlaceOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoFever2W1.isChecked() & !rdoFever2W2.isChecked() & !rdoFever2W3.isChecked() & !rdoFever2W4.isChecked() & secFever2W.isShown()) {
                ValidationMsg += "\nB5.11 Required field: Did baby/[name] have a fever (was the babe unusually hot to touch) in the past two weeks?  (CIRCLE ONE RESPONSE).";
                secFever2W.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoFeverTreat1.isChecked() & !rdoFeverTreat2.isChecked() & !rdoFeverTreat3.isChecked() & !rdoFeverTreat4.isChecked() & secFeverTreat.isShown()) {
                ValidationMsg += "\nB5.12 Required field: Did you/caregiver seek health care/treatment for baby/[name]s fever?.";
                secFeverTreat.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            //if (!rdoFeverTreatPlace1.isChecked() & !rdoFeverTreatPlace2.isChecked() & !rdoFeverTreatPlace3.isChecked() & !rdoFeverTreatPlace4.isChecked() & !rdoFeverTreatPlace5.isChecked() & !rdoFeverTreatPlace6.isChecked() & !rdoFeverTreatPlace7.isChecked() & !rdoFeverTreatPlace8.isChecked() & !rdoFeverTreatPlace9.isChecked() & !rdoFeverTreatPlace10.isChecked() & !rdoFeverTreatPlace11.isChecked() & !rdoFeverTreatPlace12.isChecked() & !rdoFeverTreatPlace13.isChecked() & secFeverTreatPlace.isShown()) {
            if (!rdoFeverTreatPlace1.isChecked() & !rdoFeverTreatPlace2.isChecked() & !rdoFeverTreatPlace3.isChecked() & !rdoFeverTreatPlace4.isChecked() & !rdoFeverTreatPlace5.isChecked() & !rdoFeverTreatPlace6.isChecked() & !rdoFeverTreatPlace7.isChecked() & !rdoFeverTreatPlace9.isChecked() & !rdoFeverTreatPlace10.isChecked() & !rdoFeverTreatPlace11.isChecked() & !rdoFeverTreatPlace12.isChecked() & !rdoFeverTreatPlace13.isChecked() & secFeverTreatPlace.isShown()) {
                ValidationMsg += "\nB5.13 Required field: Where did you/caregiver seek health care/treatment for baby/[name]s fever? (CIRCLE ONE RESPONSE).";
                secFeverTreatPlace.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtFeverTreatPlaceOth.getText().toString().length() == 0 & secFeverTreatPlaceOth.isShown()) {
                ValidationMsg += "\nRequired field: Other, specify.";
                secFeverTreatPlaceOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoWeightLost1.isChecked() & !rdoWeightLost2.isChecked() & !rdoWeightLost3.isChecked() & !rdoWeightLost4.isChecked() & secWeightLost.isShown()) {
                ValidationMsg += "\nB5.14 Required field: Has baby/[name] lost weight over the last three months?.";
                secWeightLost.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoPoorWeight1.isChecked() & !rdoPoorWeight2.isChecked() & !rdoPoorWeight3.isChecked() & !rdoPoorWeight4.isChecked() & secPoorWeight.isShown()) {
                ValidationMsg += "\nB5.15 Required field: Has baby/[name] had poor/slow weight gain over the last three months?.";
                secPoorWeight.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoFeedLP2Week1.isChecked() & !rdoFeedLP2Week2.isChecked() & !rdoFeedLP2Week3.isChecked() & !rdoFeedLP2Week4.isChecked() & secFeedLP2Week.isShown()) {
                ValidationMsg += "\nB5.16 Required field: Has baby/[name] been eating/feeding less in the past two weeks?.";
                secFeedLP2Week.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoUnderweightAge1.isChecked() & !rdoUnderweightAge2.isChecked() & !rdoUnderweightAge3.isChecked() & !rdoUnderweightAge4.isChecked() & secUnderweightAge.isShown()) {
                ValidationMsg += "\nB5.17 Required field: Is baby/[name] underweight or thin for their age?.";
                secUnderweightAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoOverweightAge1.isChecked() & !rdoOverweightAge2.isChecked() & !rdoOverweightAge3.isChecked() & !rdoOverweightAge4.isChecked() & secOverweightAge.isShown()) {
                ValidationMsg += "\nB5.18 Required field: Is baby/[name] overweight or large for their age?.";
                secOverweightAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoComMEBaby1.isChecked() & !rdoComMEBaby2.isChecked() & secComMEBaby.isShown()) {
                ValidationMsg += "\nB5.19 Required field: *DO NOT ASK RESPONDENT*   FOR A MULTIPLE PREGNANCY, HAS THE RESPONDENT COMPLETED THE MODULE FOR EACH BABY?.";
                secComMEBaby.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor() {
        try {
            secMorbiID.setBackgroundColor(Color.WHITE);
            secMemID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secPGN.setBackgroundColor(Color.WHITE);
            secChSl.setBackgroundColor(Color.WHITE);
            secChDiarrhea.setBackgroundColor(Color.WHITE);
            secDiarrheaTreat.setBackgroundColor(Color.WHITE);
            secDiarrheaTreatPlace.setBackgroundColor(Color.WHITE);
            secDiarrheaTreatPlaceOth.setBackgroundColor(Color.WHITE);
            secCough2W.setBackgroundColor(Color.WHITE);
            secFastBrth.setBackgroundColor(Color.WHITE);
            secFastBrthCause.setBackgroundColor(Color.WHITE);
            secSeekHCare.setBackgroundColor(Color.WHITE);
            secSeekHCarePlace.setBackgroundColor(Color.WHITE);
            secSeekHCarePlaceOth.setBackgroundColor(Color.WHITE);
            secFever2W.setBackgroundColor(Color.WHITE);
            secFeverTreat.setBackgroundColor(Color.WHITE);
            secFeverTreatPlace.setBackgroundColor(Color.WHITE);
            secFeverTreatPlaceOth.setBackgroundColor(Color.WHITE);
            secWeightLost.setBackgroundColor(Color.WHITE);
            secPoorWeight.setBackgroundColor(Color.WHITE);
            secFeedLP2Week.setBackgroundColor(Color.WHITE);
            secUnderweightAge.setBackgroundColor(Color.WHITE);
            secOverweightAge.setBackgroundColor(Color.WHITE);
            secComMEBaby.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String MEMID, String HHID, String PGN, String ChSl) {
        try {
            RadioButton rb;
            NBC_Morbidity_DataModel d = new NBC_Morbidity_DataModel();
            String SQL = "Select * from " + TableName + "  Where MEMID='" + MEMID + "' and HHID='" + HHID + "' and PGN='" + PGN + "' and ChSl='" + ChSl + "'";
            List<NBC_Morbidity_DataModel> data = d.SelectAll(this, SQL);
            for (NBC_Morbidity_DataModel item : data) {
                txtMorbiID.setText(item.getMorbiID());
                txtMemID.setText(item.getMemID());
                txtHHID.setText(item.getHHID());
                txtPGN.setText(item.getPGN());
                txtChSl.setText(item.getChSl());
                String[] d_rdogrpChDiarrhea = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpChDiarrhea.length; i++) {
                    if (String.valueOf(item.getChDiarrhea()).equals(String.valueOf(d_rdogrpChDiarrhea[i]))) {
                        rb = (RadioButton) rdogrpChDiarrhea.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpDiarrheaTreat = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpDiarrheaTreat.length; i++) {
                    if (String.valueOf(item.getDiarrheaTreat()).equals(String.valueOf(d_rdogrpDiarrheaTreat[i]))) {
                        rb = (RadioButton) rdogrpDiarrheaTreat.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpDiarrheaTreatPlace = new String[]{"21", "22", "23", "24", "31", "32", "33",  "61", "62", "97", "98", "99"};
                for (int i = 0; i < d_rdogrpDiarrheaTreatPlace.length; i++) {
                    if (String.valueOf(item.getDiarrheaTreatPlace()).equals(String.valueOf(d_rdogrpDiarrheaTreatPlace[i]))) {
                        rb = (RadioButton) rdogrpDiarrheaTreatPlace.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtDiarrheaTreatPlaceOth.setText(item.getDiarrheaTreatPlaceOth());
                String[] d_rdogrpCough2W = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpCough2W.length; i++) {
                    if (String.valueOf(item.getCough2W()).equals(String.valueOf(d_rdogrpCough2W[i]))) {
                        rb = (RadioButton) rdogrpCough2W.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpFastBrth = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpFastBrth.length; i++) {
                    if (String.valueOf(item.getFastBrth()).equals(String.valueOf(d_rdogrpFastBrth[i]))) {
                        rb = (RadioButton) rdogrpFastBrth.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpFastBrthCause = new String[]{"1", "2", "3", "7", "8"};
                for (int i = 0; i < d_rdogrpFastBrthCause.length; i++) {
                    if (String.valueOf(item.getFastBrthCause()).equals(String.valueOf(d_rdogrpFastBrthCause[i]))) {
                        rb = (RadioButton) rdogrpFastBrthCause.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSeekHCare = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpSeekHCare.length; i++) {
                    if (String.valueOf(item.getSeekHCare()).equals(String.valueOf(d_rdogrpSeekHCare[i]))) {
                        rb = (RadioButton) rdogrpSeekHCare.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpSeekHCarePlace = new String[]{"21", "22", "23", "24", "31", "32", "33",  "61", "62", "97", "98", "99"};
                for (int i = 0; i < d_rdogrpSeekHCarePlace.length; i++) {
                    if (String.valueOf(item.getSeekHCarePlace()).equals(String.valueOf(d_rdogrpSeekHCarePlace[i]))) {
                        rb = (RadioButton) rdogrpSeekHCarePlace.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtSeekHCarePlaceOth.setText(item.getSeekHCarePlaceOth());
                String[] d_rdogrpFever2W = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpFever2W.length; i++) {
                    if (String.valueOf(item.getFever2W()).equals(String.valueOf(d_rdogrpFever2W[i]))) {
                        rb = (RadioButton) rdogrpFever2W.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpFeverTreat = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpFeverTreat.length; i++) {
                    if (String.valueOf(item.getFeverTreat()).equals(String.valueOf(d_rdogrpFeverTreat[i]))) {
                        rb = (RadioButton) rdogrpFeverTreat.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpFeverTreatPlace = new String[]{"21", "22", "23", "24", "31", "32", "33",  "61", "62", "97", "98", "99"};
                for (int i = 0; i < d_rdogrpFeverTreatPlace.length; i++) {
                    if (String.valueOf(item.getFeverTreatPlace()).equals(String.valueOf(d_rdogrpFeverTreatPlace[i]))) {
                        rb = (RadioButton) rdogrpFeverTreatPlace.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtFeverTreatPlaceOth.setText(item.getFeverTreatPlaceOth());
                String[] d_rdogrpWeightLost = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpWeightLost.length; i++) {
                    if (String.valueOf(item.getWeightLost()).equals(String.valueOf(d_rdogrpWeightLost[i]))) {
                        rb = (RadioButton) rdogrpWeightLost.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpPoorWeight = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpPoorWeight.length; i++) {
                    if (String.valueOf(item.getPoorWeight()).equals(String.valueOf(d_rdogrpPoorWeight[i]))) {
                        rb = (RadioButton) rdogrpPoorWeight.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpFeedLP2Week = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpFeedLP2Week.length; i++) {
                    if (String.valueOf(item.getFeedLP2Week()).equals(String.valueOf(d_rdogrpFeedLP2Week[i]))) {
                        rb = (RadioButton) rdogrpFeedLP2Week.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpUnderweightAge = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpUnderweightAge.length; i++) {
                    if (String.valueOf(item.getUnderweightAge()).equals(String.valueOf(d_rdogrpUnderweightAge[i]))) {
                        rb = (RadioButton) rdogrpUnderweightAge.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpOverweightAge = new String[]{"0", "1", "8", "9"};
                for (int i = 0; i < d_rdogrpOverweightAge.length; i++) {
                    if (String.valueOf(item.getOverweightAge()).equals(String.valueOf(d_rdogrpOverweightAge[i]))) {
                        rb = (RadioButton) rdogrpOverweightAge.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpComMEBaby = new String[]{"0", "1"};
                for (int i = 0; i < d_rdogrpComMEBaby.length; i++) {
                    if (String.valueOf(item.getComMEBaby()).equals(String.valueOf(d_rdogrpComMEBaby[i]))) {
                        rb = (RadioButton) rdogrpComMEBaby.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
            }
        } catch (Exception e) {
            Connection.MessageBox(NBC_Morbidity.this, e.getMessage());
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


        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;
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