
package forms_activity;


import static Common.Security_Permission.TAG;

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
import android.util.Log;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Common.Connection;
import Common.Global;
import Common.Global_CodeList;
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import Utility.SurvMemberData;
import forms_datamodel.Member_DataModel;
import forms_datamodel.tmpMemberMove_DataModel;
import forms_datamodel.tmpMember_DataModel;
import forms_datamodel.tmpMigration_DataModel;

public class Surv_Member extends AppCompatActivity {
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
    LinearLayout seclbl02;
    View linelbl02;
    LinearLayout seclbl01;
    View linelbl01;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secDSSID;
    View lineDSSID;
    TextView VlblDSSID;
    EditText txtDSSID;
    LinearLayout secMSlNo;
    View lineMSlNo;
    TextView VlblMSlNo;
    EditText txtMSlNo;
    LinearLayout secRth;
    View lineRth;
    TextView VlblRth;
    Spinner spnRth;
    LinearLayout secRthOth;
    View lineRthOth;
    TextView VlblRthOth;
    AutoCompleteTextView txtRthOth;
    LinearLayout secName;
    View lineName;
    TextView VlblName;
    EditText txtName;
    LinearLayout secSex;
    View lineSex;
    TextView VlblSex;
    RadioGroup rdogrpSex;
    RadioButton rdoSex1;
    RadioButton rdoSex2;
    RadioButton rdoSex3;
    RadioButton rdoSex4;
    RadioButton rdoSex5;
    LinearLayout seclblbdate;
    View linelblbdate;
    LinearLayout secBDate_D;
    View lineBDate_D;
    TextView VlblBDate_D;
    Spinner spnBDate_D;
    LinearLayout secBDate_M;
    View lineBDate_M;
    TextView VlblBDate_M;
    Spinner spnBDate_M;
    LinearLayout secBDate_Y;
    View lineBDate_Y;
    TextView VlblBDate_Y;
    Spinner spnBDate_Y;
    LinearLayout secBDate;
    View lineBDate;
    TextView VlblBDate;
    EditText dtpBDate;
    LinearLayout secBDateType;
    View lineBDateType;
    TextView VlblBDateType;
    RadioGroup rdogrpBDateType;
    RadioButton rdoBDateType1;
    RadioButton rdoBDateType2;
    LinearLayout secAge;
    View lineAge;
    TextView VlblAge;
    EditText txtAge;
    LinearLayout secAgeU;
    View lineAgeU;
    TextView VlblAgeU;
    RadioGroup rdogrpAgeU;
    RadioButton rdoAgeU1;
    RadioButton rdoAgeU2;
    RadioButton rdoAgeU3;
    LinearLayout secMoNo;
    View lineMoNo;
    TextView VlblMoNo;
    Spinner spnMoNo;
    LinearLayout secMoName;
    View lineMoName;
    TextView VlblMoName;
    EditText txtMoName;
    LinearLayout secFaNo;
    View lineFaNo;
    TextView VlblFaNo;
    Spinner spnFaNo;
    LinearLayout secFaName;
    View lineFaName;
    TextView VlblFaName;
    EditText txtFaName;
    LinearLayout secEduY;
    View lineEduY;
    TextView VlblEduY;
    //EditText txtEduY;
    Spinner spnEduY;
    LinearLayout secEmploy;
    View lineEmploy;
    TextView VlblEmploy;
    Spinner spnEmploy;
    LinearLayout secEmployOth;
    View lineEmployOth;
    TextView VlblEmployOth;
    AutoCompleteTextView txtEmployOth;
    LinearLayout secOcp;
    View lineOcp;
    TextView VlblOcp;
    Spinner spnOcp;

    LinearLayout secOcpOth;
    AutoCompleteTextView txtOcpOth;

    LinearLayout secOcpDk;
    View lineOcpDk;
    TextView VlblOcpDk;
    RadioGroup rdogrpOcpDk;
    RadioButton rdoOcpDk1;
    RadioButton rdoOcpDk2;
    LinearLayout secReligion;
    View lineReligion;
    TextView VlblReligion;
    Spinner spnReligion;
    LinearLayout secReligionOth;
    View lineReligionOth;
    TextView VlblReligionOth;
    AutoCompleteTextView txtReligionOth;
    LinearLayout secEthnicity;
    View lineEthnicity;
    TextView VlblEthnicity;
    Spinner spnEthnicity;

    LinearLayout secEthnicityOth;
    AutoCompleteTextView txtEthnicityOth;
    View lineEthnicityOth;

    LinearLayout secMobileNo;
    View lineMobileNo;
    TextView VlblMobileNo;
    EditText txtMobileNo;
    LinearLayout secMS;
    View lineMS;
    TextView VlblMS;
    Spinner spnMS;
    LinearLayout secMSOth;
    View lineMSOth;
    TextView VlblMSOth;
    AutoCompleteTextView txtMSOth;
    LinearLayout secSp1;
    View lineSp1;
    TextView VlblSp1;
    Spinner spnSp1;
    LinearLayout secSp1Name;
    View lineSp1Name;
    TextView VlblSp1Name;
    EditText txtSp1Name;
    LinearLayout secSp2;
    View lineSp2;
    TextView VlblSp2;
    Spinner spnSp2;
    LinearLayout secSp2Name;
    View lineSp2Name;
    TextView VlblSp2Name;
    EditText txtSp2Name;
    LinearLayout secSp3;
    View lineSp3;
    TextView VlblSp3;
    Spinner spnSp3;
    LinearLayout secSp3Name;
    View lineSp3Name;
    TextView VlblSp3Name;
    EditText txtSp3Name;
    LinearLayout secSp4;
    View lineSp4;
    TextView VlblSp4;
    Spinner spnSp4;
    LinearLayout secSp4Name;
    View lineSp4Name;
    TextView VlblSp4Name;
    EditText txtSp4Name;

    LinearLayout seclbl1;
    View linelbl1;
    LinearLayout secPstat;
    View linePstat;
    TextView VlblPstat;
    RadioGroup rdogrpPstat;
    RadioButton rdoPstat1;
    RadioButton rdoPstat2;
    RadioButton rdoPstat3;
    RadioButton rdoPstat4;
    RadioButton rdoPstat5;
    RadioButton rdoPstat6;
    LinearLayout secLmpDt;
    View lineLmpDt;
    TextView VlblLmpDt;
    EditText dtpLmpDt;
    LinearLayout secgage;
    View linegage;
    TextView Vlblgage;
    EditText txtgage;
    LinearLayout secgageUnit;
    View linegageUnit;
    TextView VlblgageUnit;
    RadioGroup rdogrpgageUnit;
    RadioButton rdogageUnit1;
    RadioButton rdogageUnit2;
    LinearLayout secanc_regis;
    View lineanc_regis;
    TextView Vlblanc_regis;
    RadioGroup rdogrpanc_regis;
    RadioButton rdoanc_regis1;
    RadioButton rdoanc_regis2;
    LinearLayout seclbl2;
    View linelbl2;
    LinearLayout secanc_resp_home;
    View lineanc_resp_home;
    TextView Vlblanc_resp_home;
    CheckBox chkanc_resp_home;
    LinearLayout secanc_other_home;
    View lineanc_other_home;
    TextView Vlblanc_other_home;
    CheckBox chkanc_other_home;
    LinearLayout secanc_govt_hosp;
    View lineanc_govt_hosp;
    TextView Vlblanc_govt_hosp;
    CheckBox chkanc_govt_hosp;
    LinearLayout secanc_govt_health;
    View lineanc_govt_health;
    TextView Vlblanc_govt_health;
    CheckBox chkanc_govt_health;
    LinearLayout secanc_govt_health_post;
    View lineanc_govt_health_post;
    TextView Vlblanc_govt_health_post;
    CheckBox chkanc_govt_health_post;
    LinearLayout secanc_priv_hosp;
    View lineanc_priv_hosp;
    TextView Vlblanc_priv_hosp;
    CheckBox chkanc_priv_hosp;

    LinearLayout secanc_tba_home;
    CheckBox chkanc_tba_home;
    LinearLayout secanc_ngo_hosp;
    View lineanc_ngo_hosp;
    TextView Vlblanc_ngo_hosp;
    CheckBox chkanc_ngo_hosp;
    LinearLayout secanc_other;
    View lineanc_other;
    TextView Vlblanc_other;
    CheckBox chkanc_other;
    LinearLayout secanc_dk;
    View lineanc_dk;
    TextView Vlblanc_dk;
    CheckBox chkanc_dk;
    LinearLayout secanc_refuse;
    View lineanc_refuse;
    TextView Vlblanc_refuse;
    CheckBox chkanc_refuse;
    LinearLayout secanc_other_specify;
    View lineanc_other_specify;
    TextView Vlblanc_other_specify;
    AutoCompleteTextView txtanc_other_specify;
    LinearLayout secout_date;
    View lineout_date;
    TextView Vlblout_date;
    EditText dtpout_date;

    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secActive;
    View lineActive;
    TextView VlblActive;
    EditText txtActive;
    LinearLayout secEnType;
    View lineEnType;
    TextView VlblEnType;
    Spinner spnEnType;
    LinearLayout secExType;
    View lineExType;
    TextView VlblExType;
    Spinner spnExType;

    int MODULEID = 0;
    int LANGUAGEID = 0;
    String TableName;

    String STARTTIME = "";
    String DEVICEID = "";
    String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
    String MEMID = "";
    String MigrationID = "";
    String TmpMigrationID = "";
    String LIVEBIRTHID = "";
    String VILLID = "";
    String CompoundID = "";
    String HHID = "";
    String HHNO = "";
    String EV_TYPE = "";
    String MSLNO = "";
    String VISIT_DATE = "";
    String EV_DATE = "";
    String ROUND = "";
    String DOD = "";
    String MoID = "";
    String MemSex = "";
    String NOT_A_MEMBER_LABEL = "";
    tmpMigration_DataModel tmpMigrationDataModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.surv_member);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();
            sp = new MySharedPreferences();

            STARTTIME = g.CurrentTime24();
            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            HHID = IDbundle.getString("HHID");
            HHNO = IDbundle.getString("HHNO");
            MEMID = IDbundle.getString("MemID");
            EV_TYPE = IDbundle.getString("evtype");
            EV_DATE = IDbundle.getString("evdate");   //yyyy-mm-dd
            VISIT_DATE = IDbundle.getString("vdate"); //yyyy-mm-dd
            ROUND = IDbundle.getString("round");
            MigrationID = IDbundle.getString("MigrationID");
            LIVEBIRTHID = IDbundle.getString("LiveBirthID");
            TmpMigrationID = IDbundle.getString("TmpMigrationID");
            DOD = IDbundle.getString("dod");
            MoID = IDbundle.getString("MoID");


            tmpMigrationDataModel = (tmpMigration_DataModel)IDbundle.getSerializable("tmpMigration");

            TableName = "tmpMember";
            MODULEID = 9;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView) findViewById(R.id.lblHeading);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Member.this);
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
            Connection.LocalizeLanguage(Surv_Member.this, MODULEID, LANGUAGEID);

            if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1) & LANGUAGEID == 4){
                NOT_A_MEMBER_LABEL = "00-Ne pas faire partie de ce ménage";
            }else{
                NOT_A_MEMBER_LABEL = "00-Not a Member of this Household";
            }

            List<String> listBDate_Y = new ArrayList<String>();
            listBDate_Y.add("");
            for (int i = Integer.parseInt(Global.CurrentYear()); i >= Integer.parseInt(Global.CurrentYear()) - 100; i--) {
                listBDate_Y.add(String.valueOf(i));
            }
            listBDate_Y.add("9998");
            spnBDate_Y.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listBDate_Y)));
            spnMoNo.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2' and Sex='2' and MemID <> '" + MEMID + "' union Select '"+ NOT_A_MEMBER_LABEL +"'"));
            spnFaNo.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2' and Sex='1' and MemID <> '" + MEMID + "' union Select '"+ NOT_A_MEMBER_LABEL +"'"));
            //spnReligion.setAdapter(C.getArrayAdapter("Select '' union Select Code||'-'||Name from Religion"));
            //spnReligion.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_RELIGION())));
            spnEthnicity.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_ETHNICITY(ProjectSetting.SITE_CODE))));
            spnSp1.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2'  and MemID <> '" + MEMID + "'  union Select '"+ NOT_A_MEMBER_LABEL +"'"));
            spnSp2.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2'  and MemID <> '" + MEMID + "'  union Select '"+ NOT_A_MEMBER_LABEL +"'"));
            spnSp3.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2'  and MemID <> '" + MEMID + "'  union Select '"+ NOT_A_MEMBER_LABEL +"'"));
            spnSp4.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2'  and MemID <> '" + MEMID + "'  union Select '"+ NOT_A_MEMBER_LABEL +"'"));




            dtpBDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnBDate";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });
            dtpLmpDt.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnLmpDt";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });

            dtpout_date.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnout_date";
                        showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });


            //Hide all skip variables

            secMoName.setVisibility(View.GONE);
            lineMoName.setVisibility(View.GONE);
            secFaName.setVisibility(View.GONE);
            lineFaName.setVisibility(View.GONE);

            secSp1Name.setVisibility(View.GONE);
            lineSp1Name.setVisibility(View.GONE);
            secSp2Name.setVisibility(View.GONE);
            lineSp2Name.setVisibility(View.GONE);
            secSp3Name.setVisibility(View.GONE);
            lineSp3Name.setVisibility(View.GONE);
            secSp4Name.setVisibility(View.GONE);
            lineSp4Name.setVisibility(View.GONE);
            seclbl1.setVisibility(View.GONE);
            linelbl1.setVisibility(View.GONE);
            secLmpDt.setVisibility(View.GONE);
            lineLmpDt.setVisibility(View.GONE);
            secgage.setVisibility(View.GONE);
            linegage.setVisibility(View.GONE);
            secgageUnit.setVisibility(View.GONE);
            linegageUnit.setVisibility(View.GONE);
            secanc_regis.setVisibility(View.GONE);
            lineanc_regis.setVisibility(View.GONE);
            seclbl2.setVisibility(View.GONE);
            linelbl2.setVisibility(View.GONE);
            secanc_resp_home.setVisibility(View.GONE);
            lineanc_resp_home.setVisibility(View.GONE);
            secanc_other_home.setVisibility(View.GONE);
            lineanc_other_home.setVisibility(View.GONE);
            secanc_govt_hosp.setVisibility(View.GONE);
            lineanc_govt_hosp.setVisibility(View.GONE);
            secanc_govt_health.setVisibility(View.GONE);
            lineanc_govt_health.setVisibility(View.GONE);
            secanc_govt_health_post.setVisibility(View.GONE);
            lineanc_govt_health_post.setVisibility(View.GONE);
            secanc_priv_hosp.setVisibility(View.GONE);
            lineanc_priv_hosp.setVisibility(View.GONE);
            secanc_ngo_hosp.setVisibility(View.GONE);
            lineanc_ngo_hosp.setVisibility(View.GONE);
            secanc_other.setVisibility(View.GONE);
            lineanc_other.setVisibility(View.GONE);
            secanc_dk.setVisibility(View.GONE);
            lineanc_dk.setVisibility(View.GONE);
            secanc_refuse.setVisibility(View.GONE);
            lineanc_refuse.setVisibility(View.GONE);
            secanc_other_specify.setVisibility(View.GONE);
            lineanc_other_specify.setVisibility(View.GONE);
            secout_date.setVisibility(View.GONE);
            lineout_date.setVisibility(View.GONE);
            secLmpDt.setVisibility(View.GONE);
            lineLmpDt.setVisibility(View.GONE);
            secgage.setVisibility(View.GONE);
            linegage.setVisibility(View.GONE);
            secgageUnit.setVisibility(View.GONE);
            linegageUnit.setVisibility(View.GONE);
            secanc_regis.setVisibility(View.GONE);
            lineanc_regis.setVisibility(View.GONE);
            seclbl2.setVisibility(View.GONE);
            linelbl2.setVisibility(View.GONE);
            secanc_resp_home.setVisibility(View.GONE);
            lineanc_resp_home.setVisibility(View.GONE);
            secanc_other_home.setVisibility(View.GONE);
            lineanc_other_home.setVisibility(View.GONE);
            secanc_govt_hosp.setVisibility(View.GONE);
            lineanc_govt_hosp.setVisibility(View.GONE);
            secanc_govt_health.setVisibility(View.GONE);
            lineanc_govt_health.setVisibility(View.GONE);
            secanc_govt_health_post.setVisibility(View.GONE);
            lineanc_govt_health_post.setVisibility(View.GONE);
            secanc_priv_hosp.setVisibility(View.GONE);
            lineanc_priv_hosp.setVisibility(View.GONE);
            secanc_ngo_hosp.setVisibility(View.GONE);
            lineanc_ngo_hosp.setVisibility(View.GONE);
            secanc_other.setVisibility(View.GONE);
            lineanc_other.setVisibility(View.GONE);
            secanc_dk.setVisibility(View.GONE);
            lineanc_dk.setVisibility(View.GONE);
            secanc_refuse.setVisibility(View.GONE);
            lineanc_refuse.setVisibility(View.GONE);
            secanc_other_specify.setVisibility(View.GONE);
            lineanc_other_specify.setVisibility(View.GONE);
            secout_date.setVisibility(View.GONE);
            lineout_date.setVisibility(View.GONE);
            secAge.setVisibility(View.GONE);
            lineAge.setVisibility(View.GONE);
            secAgeU.setVisibility(View.GONE);
            lineAgeU.setVisibility(View.GONE);
            secEthnicityOth.setVisibility(View.GONE);
            lineEthnicityOth.setVisibility(View.GONE);
            secOcpOth.setVisibility(View.GONE);
            secOcpDk.setVisibility(View.GONE);

            DataSearch(MEMID);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }
            });
        } catch (Exception e) {
            Connection.MessageBox(Surv_Member.this, e.getMessage());
            return;
        }
    }

    private void Initialization() {
        try {
            seclbl02 = (LinearLayout) findViewById(R.id.seclbl02);
            linelbl02 = (View) findViewById(R.id.linelbl02);
            seclbl01 = (LinearLayout) findViewById(R.id.seclbl01);
            linelbl01 = (View) findViewById(R.id.linelbl01);
            secMemID = (LinearLayout) findViewById(R.id.secMemID);
            lineMemID = (View) findViewById(R.id.lineMemID);
            VlblMemID = (TextView) findViewById(R.id.VlblMemID);
            txtMemID = (EditText) findViewById(R.id.txtMemID);
            if (MEMID==null || MEMID.length() == 0) txtMemID.setText(Global.Get_UUID(DEVICEID));
            else txtMemID.setText(MEMID);
            txtMemID.setEnabled(false);

            secHHID = (LinearLayout) findViewById(R.id.secHHID);
            lineHHID = (View) findViewById(R.id.lineHHID);
            VlblHHID = (TextView) findViewById(R.id.VlblHHID);
            txtHHID = (EditText) findViewById(R.id.txtHHID);
            txtHHID.setText(HHID);
            txtHHID.setEnabled(false);

            secDSSID = (LinearLayout) findViewById(R.id.secDSSID);
            lineDSSID = (View) findViewById(R.id.lineDSSID);
            VlblDSSID = (TextView) findViewById(R.id.VlblDSSID);
            txtDSSID = (EditText) findViewById(R.id.txtDSSID);

            secMSlNo = (LinearLayout) findViewById(R.id.secMSlNo);
            lineMSlNo = (View) findViewById(R.id.lineMSlNo);
            VlblMSlNo = (TextView) findViewById(R.id.VlblMSlNo);
            txtMSlNo = (EditText) findViewById(R.id.txtMSlNo);
            if (MSLNO.length() == 0)
                txtMSlNo.setText(MemNo(HHID));
            else
                txtMSlNo.setText(MSLNO);
            txtMSlNo.setEnabled(false);

            if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                txtDSSID.setText(Get_DSSID_Mali(HHID));
            }else {
                txtDSSID.setText(HHNO + txtMSlNo.getText().toString());
            }

            secRth = (LinearLayout) findViewById(R.id.secRth);
            lineRth = (View) findViewById(R.id.lineRth);
            VlblRth = (TextView) findViewById(R.id.VlblRth);
            spnRth = (Spinner) findViewById(R.id.spnRth);
            List<String> listRth = new ArrayList<String>();

            spnRth.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_RTH())));
            spnRth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnRth.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnRth.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("97")) {
                        secRthOth.setVisibility(View.VISIBLE);
                        lineRthOth.setVisibility(View.VISIBLE);
                    } else {
                        secRthOth.setVisibility(View.GONE);
                        lineRthOth.setVisibility(View.GONE);
                        txtRthOth.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secRthOth = (LinearLayout) findViewById(R.id.secRthOth);
            lineRthOth = (View) findViewById(R.id.lineRthOth);
            VlblRthOth = (TextView) findViewById(R.id.VlblRthOth);
            txtRthOth = (AutoCompleteTextView) findViewById(R.id.txtRthOth);
            txtRthOth.setAdapter(C.getArrayAdapter("Select distinct RthOth from " + TableName + " order by RthOth"));

            txtRthOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {

                }
            });
            txtRthOth.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;

                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (event.getRawX() >= (txtRthOth.getRight() - txtRthOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            ((EditText) v).setText("");
                            return true;
                        }
                    }
                    return false;
                }
            });
            secName = (LinearLayout) findViewById(R.id.secName);
            lineName = (View) findViewById(R.id.lineName);
            VlblName = (TextView) findViewById(R.id.VlblName);
            txtName = (EditText) findViewById(R.id.txtName);
            secSex = (LinearLayout) findViewById(R.id.secSex);
            lineSex = (View) findViewById(R.id.lineSex);
            VlblSex = (TextView) findViewById(R.id.VlblSex);
            rdogrpSex = (RadioGroup) findViewById(R.id.rdogrpSex);
            rdoSex1 = (RadioButton) findViewById(R.id.rdoSex1);
            rdoSex2 = (RadioButton) findViewById(R.id.rdoSex2);
            rdoSex3 = (RadioButton) findViewById(R.id.rdoSex3);
            rdoSex4 = (RadioButton) findViewById(R.id.rdoSex4);
            rdoSex5 = (RadioButton) findViewById(R.id.rdoSex5);
            rdogrpSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpSex = new String[]{"1", "2", "3", "4", "8"};
                    for (int i = 0; i < rdogrpSex.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpSex.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpSex[i];
                    }
                    MemSex = rbData;

                    String spnData = spnMS.getSelectedItemPosition() == 0 ? "" : spnMS.getSelectedItem().toString().split("-")[0];

                    if ((spnData.equalsIgnoreCase("01") || spnData.equalsIgnoreCase("02"))) {
                        String SPOUSE_SEX = "";
                        if (MemSex.equalsIgnoreCase("1")) SPOUSE_SEX = "2";
                        else if (MemSex.equalsIgnoreCase("2")) SPOUSE_SEX = "1";

                        spnSp1.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2' and Sex='" + SPOUSE_SEX + "' union Select '"+ NOT_A_MEMBER_LABEL +"'"));
                        spnSp2.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2' and Sex='" + SPOUSE_SEX + "' union Select '"+ NOT_A_MEMBER_LABEL +"'"));
                        spnSp3.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2' and Sex='" + SPOUSE_SEX + "' union Select '"+ NOT_A_MEMBER_LABEL +"'"));
                        spnSp4.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2' and Sex='" + SPOUSE_SEX + "' union Select '"+ NOT_A_MEMBER_LABEL +"'"));

                        //seclblSpsl.setVisibility(View.VISIBLE);
                        spnSp1.setVisibility(View.VISIBLE);
                        lineSp1.setVisibility(View.VISIBLE);
                        secSp1.setVisibility(View.VISIBLE);
                        lineSp1.setVisibility(View.VISIBLE);
                        spnSp2.setVisibility(View.VISIBLE);
                        lineSp2.setVisibility(View.VISIBLE);
                        secSp2.setVisibility(View.VISIBLE);
                        lineSp2.setVisibility(View.VISIBLE);
                        spnSp3.setVisibility(View.VISIBLE);
                        lineSp3.setVisibility(View.VISIBLE);
                        secSp3.setVisibility(View.VISIBLE);
                        lineSp3.setVisibility(View.VISIBLE);
                        spnSp4.setVisibility(View.VISIBLE);
                        lineSp4.setVisibility(View.VISIBLE);
                        secSp4.setVisibility(View.VISIBLE);
                        lineSp4.setVisibility(View.VISIBLE);

                        secMobileNo.setVisibility(View.VISIBLE);
                        lineMobileNo.setVisibility(View.VISIBLE);

                        if (MemSex.equals("2")) {
                            //seclbl1.setVisibility(View.VISIBLE);
                            //linelbl1.setVisibility(View.VISIBLE);
                            //secPstat.setVisibility(View.VISIBLE);
                            //linePstat.setVisibility(View.VISIBLE);
                            //secLmpDt.setVisibility(View.VISIBLE);
                            //lineLmpDt.setVisibility(View.VISIBLE);

                            spnSp2.setSelection(0);
                            spnSp2.setVisibility(View.GONE);
                            lineSp2.setVisibility(View.GONE);
                            secSp2.setVisibility(View.GONE);
                            lineSp2.setVisibility(View.GONE);
                            secSp2Name.setVisibility(View.GONE);
                            txtSp2Name.setText("");

                            spnSp3.setSelection(0);
                            spnSp3.setVisibility(View.GONE);
                            lineSp3.setVisibility(View.GONE);
                            secSp3.setVisibility(View.GONE);
                            lineSp3.setVisibility(View.GONE);
                            secSp3Name.setVisibility(View.GONE);
                            txtSp3Name.setText("");

                            spnSp4.setSelection(0);
                            spnSp4.setVisibility(View.GONE);
                            lineSp4.setVisibility(View.GONE);
                            secSp4.setVisibility(View.GONE);
                            lineSp4.setVisibility(View.GONE);
                            secSp4Name.setVisibility(View.GONE);
                            txtSp4Name.setText("");
                        } else {
                            seclbl1.setVisibility(View.GONE);
                            linelbl1.setVisibility(View.GONE);
                            secPstat.setVisibility(View.GONE);
                            linePstat.setVisibility(View.GONE);
                            secLmpDt.setVisibility(View.GONE);
                            lineLmpDt.setVisibility(View.GONE);
                            dtpLmpDt.setText("");
                            secgage.setVisibility(View.GONE);
                            linegage.setVisibility(View.GONE);
                            txtgage.setText("");
                            secgageUnit.setVisibility(View.GONE);
                            linegageUnit.setVisibility(View.GONE);
                            rdogrpgageUnit.clearCheck();
                            secanc_regis.setVisibility(View.GONE);
                            lineanc_regis.setVisibility(View.GONE);
                            rdogrpanc_regis.clearCheck();
                            seclbl2.setVisibility(View.GONE);
                            linelbl2.setVisibility(View.GONE);
                            secanc_resp_home.setVisibility(View.GONE);
                            lineanc_resp_home.setVisibility(View.GONE);
                            chkanc_resp_home.setChecked(false);
                            secanc_other_home.setVisibility(View.GONE);
                            lineanc_other_home.setVisibility(View.GONE);
                            chkanc_other_home.setChecked(false);
                            secanc_govt_hosp.setVisibility(View.GONE);
                            lineanc_govt_hosp.setVisibility(View.GONE);
                            chkanc_govt_hosp.setChecked(false);
                            secanc_govt_health.setVisibility(View.GONE);
                            lineanc_govt_health.setVisibility(View.GONE);
                            chkanc_govt_health.setChecked(false);
                            secanc_govt_health_post.setVisibility(View.GONE);
                            lineanc_govt_health_post.setVisibility(View.GONE);
                            chkanc_govt_health_post.setChecked(false);
                            secanc_priv_hosp.setVisibility(View.GONE);
                            lineanc_priv_hosp.setVisibility(View.GONE);
                            chkanc_priv_hosp.setChecked(false);

                            secanc_tba_home.setVisibility(View.GONE);
                            secanc_tba_home.setVisibility(View.GONE);
                            chkanc_tba_home.setChecked(false);

                            secanc_ngo_hosp.setVisibility(View.GONE);
                            lineanc_ngo_hosp.setVisibility(View.GONE);
                            chkanc_ngo_hosp.setChecked(false);
                            secanc_other.setVisibility(View.GONE);
                            lineanc_other.setVisibility(View.GONE);
                            chkanc_other.setChecked(false);
                            secanc_dk.setVisibility(View.GONE);
                            lineanc_dk.setVisibility(View.GONE);
                            chkanc_dk.setChecked(false);
                            secanc_refuse.setVisibility(View.GONE);
                            lineanc_refuse.setVisibility(View.GONE);
                            chkanc_refuse.setChecked(false);
                            secanc_other_specify.setVisibility(View.GONE);
                            lineanc_other_specify.setVisibility(View.GONE);
                            txtanc_other_specify.setText("");
                            secout_date.setVisibility(View.GONE);
                            lineout_date.setVisibility(View.GONE);
                            dtpout_date.setText("");
                        }
                    } else {
                        spnSp1.setSelection(0);
                        spnSp1.setVisibility(View.GONE);
                        lineSp1.setVisibility(View.GONE);
                        secSp1.setVisibility(View.GONE);
                        lineSp1.setVisibility(View.GONE);
                        secSp1Name.setVisibility(View.GONE);
                        txtSp1Name.setText("");

                        spnSp2.setSelection(0);
                        spnSp2.setVisibility(View.GONE);
                        lineSp2.setVisibility(View.GONE);
                        secSp2.setVisibility(View.GONE);
                        lineSp2.setVisibility(View.GONE);
                        secSp2Name.setVisibility(View.GONE);
                        txtSp2Name.setText("");

                        spnSp3.setSelection(0);
                        spnSp3.setVisibility(View.GONE);
                        lineSp3.setVisibility(View.GONE);
                        secSp3.setVisibility(View.GONE);
                        lineSp3.setVisibility(View.GONE);
                        secSp3Name.setVisibility(View.GONE);
                        txtSp3Name.setText("");

                        spnSp4.setSelection(0);
                        spnSp4.setVisibility(View.GONE);
                        lineSp4.setVisibility(View.GONE);
                        secSp4.setVisibility(View.GONE);
                        lineSp4.setVisibility(View.GONE);
                        secSp4Name.setVisibility(View.GONE);
                        txtSp4Name.setText("");

                        secMobileNo.setVisibility(View.GONE);
                        lineMobileNo.setVisibility(View.GONE);

                        seclbl1.setVisibility(View.GONE);
                        linelbl1.setVisibility(View.GONE);
                        secPstat.setVisibility(View.GONE);
                        linePstat.setVisibility(View.GONE);
                        secLmpDt.setVisibility(View.GONE);
                        lineLmpDt.setVisibility(View.GONE);
                        dtpLmpDt.setText("");
                        secgage.setVisibility(View.GONE);
                        linegage.setVisibility(View.GONE);
                        txtgage.setText("");
                        secgageUnit.setVisibility(View.GONE);
                        linegageUnit.setVisibility(View.GONE);
                        rdogrpgageUnit.clearCheck();
                        secanc_regis.setVisibility(View.GONE);
                        lineanc_regis.setVisibility(View.GONE);
                        rdogrpanc_regis.clearCheck();
                        seclbl2.setVisibility(View.GONE);
                        linelbl2.setVisibility(View.GONE);
                        secanc_resp_home.setVisibility(View.GONE);
                        lineanc_resp_home.setVisibility(View.GONE);
                        chkanc_resp_home.setChecked(false);
                        secanc_other_home.setVisibility(View.GONE);
                        lineanc_other_home.setVisibility(View.GONE);
                        chkanc_other_home.setChecked(false);
                        secanc_govt_hosp.setVisibility(View.GONE);
                        lineanc_govt_hosp.setVisibility(View.GONE);
                        chkanc_govt_hosp.setChecked(false);
                        secanc_govt_health.setVisibility(View.GONE);
                        lineanc_govt_health.setVisibility(View.GONE);
                        chkanc_govt_health.setChecked(false);
                        secanc_govt_health_post.setVisibility(View.GONE);
                        lineanc_govt_health_post.setVisibility(View.GONE);
                        chkanc_govt_health_post.setChecked(false);
                        secanc_priv_hosp.setVisibility(View.GONE);
                        lineanc_priv_hosp.setVisibility(View.GONE);
                        chkanc_priv_hosp.setChecked(false);

                        secanc_tba_home.setVisibility(View.GONE);
                        secanc_tba_home.setVisibility(View.GONE);
                        chkanc_tba_home.setChecked(false);

                        secanc_ngo_hosp.setVisibility(View.GONE);
                        lineanc_ngo_hosp.setVisibility(View.GONE);
                        chkanc_ngo_hosp.setChecked(false);
                        secanc_other.setVisibility(View.GONE);
                        lineanc_other.setVisibility(View.GONE);
                        chkanc_other.setChecked(false);
                        secanc_dk.setVisibility(View.GONE);
                        lineanc_dk.setVisibility(View.GONE);
                        chkanc_dk.setChecked(false);
                        secanc_refuse.setVisibility(View.GONE);
                        lineanc_refuse.setVisibility(View.GONE);
                        chkanc_refuse.setChecked(false);
                        secanc_other_specify.setVisibility(View.GONE);
                        lineanc_other_specify.setVisibility(View.GONE);
                        txtanc_other_specify.setText("");
                        secout_date.setVisibility(View.GONE);
                        lineout_date.setVisibility(View.GONE);
                        dtpout_date.setText("");
                    }

                    if (spnData.equalsIgnoreCase("97")) {
                        secMSOth.setVisibility(View.VISIBLE);
                        lineMSOth.setVisibility(View.VISIBLE);
                    } else {
                        secMSOth.setVisibility(View.GONE);
                        lineMSOth.setVisibility(View.GONE);
                        txtMSOth.setText("");
                    }

                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

            seclblbdate = (LinearLayout) findViewById(R.id.seclblbdate);
            linelblbdate = (View) findViewById(R.id.linelblbdate);


            secBDate_D = (LinearLayout) findViewById(R.id.secBDate_D);
            lineBDate_D = (View) findViewById(R.id.lineBDate_D);
            VlblBDate_D = (TextView) findViewById(R.id.VlblBDate_D);
            spnBDate_D = (Spinner) findViewById(R.id.spnBDate_D);
            List<String> listBDate_D = new ArrayList<String>();
            listBDate_D.add("");
            for (int i = 1; i <= 31; i++) {
                listBDate_D.add(String.valueOf(i));
            }
            listBDate_D.add("98");
            spnBDate_D.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listBDate_D)));
            spnBDate_D.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String DD = spnBDate_D.getSelectedItemPosition()>0?spnBDate_D.getSelectedItem().toString():"";
                    String MM = spnBDate_M.getSelectedItemPosition()>0?spnBDate_M.getSelectedItem().toString():"";
                    String YY = spnBDate_Y.getSelectedItemPosition()>0?spnBDate_Y.getSelectedItem().toString():"";
                    if(DD.equals("98") || MM.split("-")[0].equals("98") || YY.equals("9998")){
                        secAge.setVisibility(View.VISIBLE);
                        lineAge.setVisibility(View.VISIBLE);
                        secAgeU.setVisibility(View.VISIBLE);
                        lineAgeU.setVisibility(View.VISIBLE);

                    } else {
                        secAge.setVisibility(View.GONE);
                        lineAge.setVisibility(View.GONE);
                        txtAge.setText("");
                        secAgeU.setVisibility(View.GONE);
                        lineAgeU.setVisibility(View.GONE);
                        rdogrpAgeU.clearCheck();

                        /*if(calclateAgeFromDMY(DD,MM.split("-")[0],YY) <= ProjectSetting.ELIGIBILITY_AGE_EDU_YEAR ){
                            spnEduY.setSelection(Global.SpinnerItemPositionAnyLength(spnEduY, "66"));
                        }

                        if(calclateAgeFromDMY(DD,MM.split("-")[0],YY) < ProjectSetting.ELIGIBILITY_AGE_MS_YEAR ){
                            spnMS.setSelection(Global.SpinnerItemPositionAnyLength(spnMS, "66"));
                        }*/
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });


            secBDate_M = (LinearLayout) findViewById(R.id.secBDate_M);
            lineBDate_M = (View) findViewById(R.id.lineBDate_M);
            VlblBDate_M = (TextView) findViewById(R.id.VlblBDate_M);
            spnBDate_M = (Spinner) findViewById(R.id.spnBDate_M);
            List<String> listBDate_M = new ArrayList<String>();

            listBDate_M.add("");
            listBDate_M.add("01-Jan");
            listBDate_M.add("02-Feb");
            listBDate_M.add("03-Mar");
            listBDate_M.add("04-Apr");
            listBDate_M.add("05-May");
            listBDate_M.add("06-Jun");
            listBDate_M.add("07-Jul");
            listBDate_M.add("08-Aug");
            listBDate_M.add("09-Sep");
            listBDate_M.add("10-Oct");
            listBDate_M.add("11-Nov");
            listBDate_M.add("12-Dec");
            listBDate_M.add("98-Don't know");
            spnBDate_M.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listBDate_M)));
            spnBDate_M.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String DD = spnBDate_D.getSelectedItemPosition()>0?spnBDate_D.getSelectedItem().toString():"";
                    String MM = spnBDate_M.getSelectedItemPosition()>0?spnBDate_M.getSelectedItem().toString():"";
                    String YY = spnBDate_Y.getSelectedItemPosition()>0?spnBDate_Y.getSelectedItem().toString():"";
                    if(DD.equals("98") || MM.split("-")[0].equals("98") || YY.equals("9998")){
                        secAge.setVisibility(View.VISIBLE);
                        lineAge.setVisibility(View.VISIBLE);
                        secAgeU.setVisibility(View.VISIBLE);
                        lineAgeU.setVisibility(View.VISIBLE);
                    } else {
                        secAge.setVisibility(View.GONE);
                        lineAge.setVisibility(View.GONE);
                        txtAge.setText("");
                        secAgeU.setVisibility(View.GONE);
                        lineAgeU.setVisibility(View.GONE);
                        rdogrpAgeU.clearCheck();

                        /*if(calclateAgeFromDMY(DD,MM.split("-")[0],YY) <= ProjectSetting.ELIGIBILITY_AGE_EDU_YEAR ){
                            spnEduY.setSelection(Global.SpinnerItemPositionAnyLength(spnEduY, "66"));
                        }

                        if(calclateAgeFromDMY(DD,MM.split("-")[0],YY) < ProjectSetting.ELIGIBILITY_AGE_MS_YEAR ){
                            spnMS.setSelection(Global.SpinnerItemPositionAnyLength(spnMS, "66"));
                        }*/
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

            secBDate_Y = (LinearLayout) findViewById(R.id.secBDate_Y);
            lineBDate_Y = (View) findViewById(R.id.lineBDate_Y);
            VlblBDate_Y = (TextView) findViewById(R.id.VlblBDate_Y);
            spnBDate_Y = (Spinner) findViewById(R.id.spnBDate_Y);
            List<String> listBDate_Y = new ArrayList<String>();

            listBDate_Y.add("");
            for (int i = Integer.parseInt(Global.CurrentYear()); i >= Integer.parseInt(Global.CurrentYear()) - 100; i--) {
                listBDate_Y.add(String.valueOf(i));
            }
            listBDate_Y.add("9998");

            spnBDate_Y.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listBDate_Y)));
            spnBDate_Y.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String DD = spnBDate_D.getSelectedItemPosition()>0?spnBDate_D.getSelectedItem().toString():"";
                    String MM = spnBDate_M.getSelectedItemPosition()>0?spnBDate_M.getSelectedItem().toString():"";
                    String YY = spnBDate_Y.getSelectedItemPosition()>0?spnBDate_Y.getSelectedItem().toString():"";
                    if(DD.equals("98") || MM.split("-")[0].equals("98") || YY.equals("9998")){
                        secAge.setVisibility(View.VISIBLE);
                        lineAge.setVisibility(View.VISIBLE);
                        secAgeU.setVisibility(View.VISIBLE);
                        lineAgeU.setVisibility(View.VISIBLE);
                    }else{
                        secAge.setVisibility(View.GONE);
                        lineAge.setVisibility(View.GONE);
                        txtAge.setText("");
                        secAgeU.setVisibility(View.GONE);
                        lineAgeU.setVisibility(View.GONE);
                        rdogrpAgeU.clearCheck();

                        /*if(calclateAgeFromDMY(DD,MM.split("-")[0],YY) <= ProjectSetting.ELIGIBILITY_AGE_EDU_YEAR ){
                            spnEduY.setSelection(Global.SpinnerItemPositionAnyLength(spnEduY, "66"));
                        }

                        if(calclateAgeFromDMY(DD,MM.split("-")[0],YY) < ProjectSetting.ELIGIBILITY_AGE_MS_YEAR ){
                            spnMS.setSelection(Global.SpinnerItemPositionAnyLength(spnMS, "66"));
                        }*/
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });


            /*if(!DOD.equals("")){
                String str[] = DOD.split("/");
                int day = Integer.parseInt(str[0]);
                String month = str[1];
                int year = Integer.parseInt(str[2]);

                spnBDate_Y.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_Y, String.valueOf(year)));
                spnBDate_M.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_M, String.valueOf(month)));
                spnBDate_D.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_D, String.valueOf(day)));
            }*/


            secBDate = (LinearLayout) findViewById(R.id.secBDate);
            lineBDate = (View) findViewById(R.id.lineBDate);
            VlblBDate = (TextView) findViewById(R.id.VlblBDate);
            dtpBDate = (EditText) findViewById(R.id.dtpBDate);
            rdogrpBDateType = (RadioGroup) findViewById(R.id.rdogrpBDateType);
            rdoBDateType1 = (RadioButton) findViewById(R.id.rdoBDateType1);
            rdoBDateType2 = (RadioButton) findViewById(R.id.rdoBDateType2);
            if (DOD.length() > 0){
                rdoBDateType1.setChecked(true);
                rdoBDateType2.setEnabled(false);

                dtpBDate.setText(DOD);
                dtpBDate.setEnabled(false);

                String[] DT = dtpBDate.getText().toString().split("/");
                String D = String.valueOf(Integer.parseInt(DT[0]));
                String M = DT[1];
                String Y = DT[2];
                spnBDate_D.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_D,D));
                spnBDate_M.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_M,M));
                spnBDate_Y.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_Y,Y));

                secBDate_D.setVisibility(View.GONE);
                lineBDate_D.setVisibility(View.GONE);
                secBDate_M.setVisibility(View.GONE);
                lineBDate_M.setVisibility(View.GONE);
                secBDate_Y.setVisibility(View.GONE);
                lineBDate_Y.setVisibility(View.GONE);
            }
            dtpBDate.addTextChangedListener(new TextWatcher() {
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                public void beforeTextChanged(CharSequence s, int start, int count,int after) {
                }

                public void afterTextChanged(Editable s) {
                    if(dtpBDate.getText().toString().length()==10){
                        if(rdoBDateType1.isChecked()){
                            String[] DT = dtpBDate.getText().toString().split("/");
                            String D = String.valueOf(Integer.parseInt(DT[0]));
                            String M = DT[1];
                            String Y = DT[2];
                            spnBDate_D.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_D,D));
                            spnBDate_M.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_M,M));
                            spnBDate_Y.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_Y,Y));
                        }

                        //Date difference check
                        /*if(Global.DateDifferenceYears(Global.DateNowDMY(),dtpBDate.getText().toString()) <= ProjectSetting.ELIGIBILITY_AGE_EDU_YEAR ){
                            spnEduY.setSelection(Global.SpinnerItemPositionAnyLength(spnEduY, "66"));
                        }

                        if(Global.DateDifferenceYears(Global.DateNowDMY(),dtpBDate.getText().toString()) < ProjectSetting.ELIGIBILITY_AGE_MS_YEAR ){
                            spnMS.setSelection(Global.SpinnerItemPositionAnyLength(spnMS, "66"));
                        }*/
                    }
                }
            });
            secBDateType = (LinearLayout) findViewById(R.id.secBDateType);
            lineBDateType = (View) findViewById(R.id.lineBDateType);
            VlblBDateType = (TextView) findViewById(R.id.VlblBDateType);
            rdogrpBDateType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                public void onCheckedChanged(RadioGroup group, int checkedId)
                {
                    if(checkedId==rdoBDateType1.getId()){
                        secBDate.setVisibility(View.VISIBLE);
                        lineBDate.setVisibility(View.VISIBLE);
                        secBDate_D.setVisibility(View.GONE);
                        lineBDate_D.setVisibility(View.GONE);
                        secBDate_M.setVisibility(View.GONE);
                        lineBDate_M.setVisibility(View.GONE);
                        secBDate_Y.setVisibility(View.GONE);
                        lineBDate_Y.setVisibility(View.GONE);
                        secAge.setVisibility(View.GONE);
                        lineAge.setVisibility(View.GONE);
                        secAgeU.setVisibility(View.GONE);
                        lineAgeU.setVisibility(View.GONE);
                    }else{
                        secBDate.setVisibility(View.GONE);
                        lineBDate.setVisibility(View.GONE);
                        dtpBDate.setText("");
                        secBDate_D.setVisibility(View.VISIBLE);
                        lineBDate_D.setVisibility(View.VISIBLE);
                        secBDate_M.setVisibility(View.VISIBLE);
                        lineBDate_M.setVisibility(View.VISIBLE);
                        secBDate_Y.setVisibility(View.VISIBLE);
                        lineBDate_Y.setVisibility(View.VISIBLE);
                    }

                }
            });
            secAge = (LinearLayout) findViewById(R.id.secAge);
            lineAge = (View) findViewById(R.id.lineAge);
            VlblAge = (TextView) findViewById(R.id.VlblAge);
            txtAge = (EditText) findViewById(R.id.txtAge);

            txtAge.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    /*if(txtAge.getText().toString().length() > 0){
                        if(calclateAgeFromAgeText(txtAge.getText().toString()) <= ProjectSetting.ELIGIBILITY_AGE_EDU_YEAR ){
                            spnEduY.setSelection(Global.SpinnerItemPositionAnyLength(spnEduY, "66"));
                        }

                        if(calclateAgeFromAgeText(txtAge.getText().toString()) < ProjectSetting.ELIGIBILITY_AGE_MS_YEAR ){
                            spnMS.setSelection(Global.SpinnerItemPositionAnyLength(spnMS, "66"));
                        }
                    }*/
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            secAgeU = (LinearLayout) findViewById(R.id.secAgeU);
            lineAgeU = (View) findViewById(R.id.lineAgeU);
            VlblAgeU = (TextView) findViewById(R.id.VlblAgeU);
            rdogrpAgeU = (RadioGroup) findViewById(R.id.rdogrpAgeU);
            rdoAgeU1 = (RadioButton) findViewById(R.id.rdoAgeU1);
            rdoAgeU2 = (RadioButton) findViewById(R.id.rdoAgeU2);
            rdoAgeU3 = (RadioButton) findViewById(R.id.rdoAgeU3);
            secMoNo = (LinearLayout) findViewById(R.id.secMoNo);
            lineMoNo = (View) findViewById(R.id.lineMoNo);
            VlblMoNo = (TextView) findViewById(R.id.VlblMoNo);
            spnMoNo = (Spinner) findViewById(R.id.spnMoNo);
            spnMoNo.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and Sex='2' and isdelete='2'  and MemID <> '" + MEMID + "' union Select '"+ NOT_A_MEMBER_LABEL +"'"));

            spnMoNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnMoNo.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnMoNo.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("00")) {
                        secMoName.setVisibility(View.GONE);
                        lineMoName.setVisibility(View.GONE);
                    } else {
                        secMoName.setVisibility(View.GONE);
                        lineMoName.setVisibility(View.GONE);
                        txtMoName.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secMoName = (LinearLayout) findViewById(R.id.secMoName);
            lineMoName = (View) findViewById(R.id.lineMoName);
            VlblMoName = (TextView) findViewById(R.id.VlblMoName);
            txtMoName = (EditText) findViewById(R.id.txtMoName);
            secFaNo = (LinearLayout) findViewById(R.id.secFaNo);
            lineFaNo = (View) findViewById(R.id.lineFaNo);
            VlblFaNo = (TextView) findViewById(R.id.VlblFaNo);
            spnFaNo = (Spinner) findViewById(R.id.spnFaNo);

            spnFaNo.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember " +
                    "Where HHID='" + HHID + "' and Sex='1' and isdelete='2' and MemID <> '" + MEMID + "' union Select '"+ NOT_A_MEMBER_LABEL +"'"));

            spnFaNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnFaNo.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnFaNo.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("00")) {
                        secFaName.setVisibility(View.GONE);
                        lineFaName.setVisibility(View.GONE);
                    } else {
                        secFaName.setVisibility(View.GONE);
                        lineFaName.setVisibility(View.GONE);
                        txtFaName.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });




            secFaName = (LinearLayout) findViewById(R.id.secFaName);
            lineFaName = (View) findViewById(R.id.lineFaName);
            VlblFaName = (TextView) findViewById(R.id.VlblFaName);
            txtFaName = (EditText) findViewById(R.id.txtFaName);
            secEduY = (LinearLayout) findViewById(R.id.secEduY);
            lineEduY = (View) findViewById(R.id.lineEduY);
            VlblEduY = (TextView) findViewById(R.id.VlblEduY);
            spnEduY = (Spinner) findViewById(R.id.spnEduY);

            List<String> listEDU = Global_CodeList.Get_EDUCATION();
            spnEduY.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listEDU)));

            secEmploy = (LinearLayout) findViewById(R.id.secEmploy);
            lineEmploy = (View) findViewById(R.id.lineEmploy);
            VlblEmploy = (TextView) findViewById(R.id.VlblEmploy);
            spnEmploy = (Spinner) findViewById(R.id.spnEmploy);
            spnEmploy.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_Employment())));

            spnEmploy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnEmploy.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnEmploy.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("7")) {
                        secEmployOth.setVisibility(View.VISIBLE);
                        lineEmployOth.setVisibility(View.VISIBLE);
                    } else if (spnData.equalsIgnoreCase("8")) {
                        secEmployOth.setVisibility(View.GONE);
                        lineEmployOth.setVisibility(View.GONE);
                        txtEmployOth.setText("");
                    } else if (spnData.equalsIgnoreCase("9")) {
                        secEmployOth.setVisibility(View.GONE);
                        lineEmployOth.setVisibility(View.GONE);
                        txtEmployOth.setText("");
                    } else {
                        secEmployOth.setVisibility(View.GONE);
                        lineEmployOth.setVisibility(View.GONE);
                        txtEmployOth.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secEmployOth = (LinearLayout) findViewById(R.id.secEmployOth);
            lineEmployOth = (View) findViewById(R.id.lineEmployOth);
            VlblEmployOth = (TextView) findViewById(R.id.VlblEmployOth);
            txtEmployOth = (AutoCompleteTextView) findViewById(R.id.txtEmployOth);
            txtEmployOth.setAdapter(C.getArrayAdapter("Select distinct EmployOth from " + TableName + " order by EmployOth"));

            txtEmployOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {

                }
            });
            txtEmployOth.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;

                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (event.getRawX() >= (txtEmployOth.getRight() - txtEmployOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            ((EditText) v).setText("");
                            return true;
                        }
                    }
                    return false;
                }
            });
            secOcp = (LinearLayout) findViewById(R.id.secOcp);
            lineOcp = (View) findViewById(R.id.lineOcp);
            VlblOcp = (TextView) findViewById(R.id.VlblOcp);
            spnOcp = (Spinner) findViewById(R.id.spnOcp);
            //spnOcp.setAdapter(C.getArrayAdapter("select Select distinct Ocp from " + TableName + " order by Ocp"));

            spnOcp.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_OCCUPATION())));

            spnOcp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String code = spnOcp.getSelectedItemPosition()==0?"":spnOcp.getSelectedItem().toString().split("-")[0];
                    if(code.equals("97")){
                        secOcpOth.setVisibility(View.VISIBLE);
                    }else{
                        secOcpOth.setVisibility(View.GONE);
                        txtOcpOth.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
            secOcpOth=findViewById(R.id.secOcpOth);
            txtOcpOth=findViewById(R.id.txtOcpOth);
            txtOcpOth.setAdapter(C.getArrayAdapter("Select distinct OcpOth from "+ TableName +" order by OcpOth"));

            txtOcpOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

                }
            });

            txtOcpOth.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (txtOcpOth.getRight() - txtOcpOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            ((EditText)v).setText("");
                            return true;
                        }
                    }
                    return false;
                }
            });

            txtOcpOth.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {}
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    if(txtOcpOth.getText().toString().length()>0){
                        //rdogrpOcpDk.clearCheck();
                    }
                }
            });

            secOcpDk = (LinearLayout) findViewById(R.id.secOcpDk);
            lineOcpDk = (View) findViewById(R.id.lineOcpDk);
            VlblOcpDk = (TextView) findViewById(R.id.VlblOcpDk);
            rdogrpOcpDk = (RadioGroup) findViewById(R.id.rdogrpOcpDk);
            rdoOcpDk1 = (RadioButton) findViewById(R.id.rdoOcpDk1);
            rdoOcpDk2 = (RadioButton) findViewById(R.id.rdoOcpDk2);

            /*rdogrpOcpDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (rdoOcpDk1.isChecked() || rdoOcpDk2.isChecked())
                        txtOcp.setText("");
                }

            });*/

            secReligion = (LinearLayout) findViewById(R.id.secReligion);
            lineReligion = (View) findViewById(R.id.lineReligion);
            VlblReligion = (TextView) findViewById(R.id.VlblReligion);
            spnReligion = (Spinner) findViewById(R.id.spnReligion);

            spnReligion.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_RELIGION())));

            spnReligion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnReligion.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnReligion.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("97")) {
                        secReligionOth.setVisibility(View.VISIBLE);
                        lineReligionOth.setVisibility(View.VISIBLE);
                    } else if (spnData.equalsIgnoreCase("98")) {
                        secReligionOth.setVisibility(View.GONE);
                        lineReligionOth.setVisibility(View.GONE);
                        txtReligionOth.setText("");
                    } else if (spnData.equalsIgnoreCase("99")) {
                        secReligionOth.setVisibility(View.GONE);
                        lineReligionOth.setVisibility(View.GONE);
                        txtReligionOth.setText("");
                    } else {
                        secReligionOth.setVisibility(View.GONE);
                        lineReligionOth.setVisibility(View.GONE);
                        txtReligionOth.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secReligionOth = (LinearLayout) findViewById(R.id.secReligionOth);
            lineReligionOth = (View) findViewById(R.id.lineReligionOth);
            VlblReligionOth = (TextView) findViewById(R.id.VlblReligionOth);

            txtReligionOth = (AutoCompleteTextView) findViewById(R.id.txtReligionOth);
            C.setupAutoComplete(TableName,txtReligionOth,"ReligionOth"); //setup autocomplete view by event
            secEthnicity = (LinearLayout) findViewById(R.id.secEthnicity);
            lineEthnicity = (View) findViewById(R.id.lineEthnicity);
            VlblEthnicity = (TextView) findViewById(R.id.VlblEthnicity);
            spnEthnicity = (Spinner) findViewById(R.id.spnEthnicity);
            //spnEthnicity.setAdapter(C.getArrayAdapter("Select '' union Select Code||'-'||Name from Ethnicity"));

            spnEthnicity.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_ETHNICITY(ProjectSetting.SITE_CODE))));
            secEthnicityOth = findViewById(R.id.secEthnicityOth);
            txtEthnicityOth = findViewById(R.id.txtEthnicityOth);
            C.setupAutoComplete(TableName,txtEthnicityOth,"EthnicityOth"); //setup autocomplete view by event
            lineEthnicityOth = findViewById(R.id.lineEthnicityOth);
            spnEthnicity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnEthnicity.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnEthnicity.getSelectedItem().toString(), "-").trim();
                    }

                    if (spnData.equalsIgnoreCase("97")) {
                        secEthnicityOth.setVisibility(View.VISIBLE);
                        lineEthnicityOth.setVisibility(View.VISIBLE);
                    } else {
                        secEthnicityOth.setVisibility(View.GONE);
                        lineEthnicityOth.setVisibility(View.GONE);
                        txtEthnicityOth.setText("");
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    return;
                }
            });

            secMobileNo = (LinearLayout) findViewById(R.id.secMobileNo);
            lineMobileNo = (View) findViewById(R.id.lineMobileNo);
            VlblMobileNo = (TextView) findViewById(R.id.VlblMobileNo);
            txtMobileNo = (EditText) findViewById(R.id.txtMobileNo);
            secMS = (LinearLayout) findViewById(R.id.secMS);
            lineMS = (View) findViewById(R.id.lineMS);
            VlblMS = (TextView) findViewById(R.id.VlblMS);
            spnMS = (Spinner) findViewById(R.id.spnMS);

            spnMS.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(Global_CodeList.Get_MARITAL_STATUS())));

            spnMS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnMS.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnMS.getSelectedItem().toString(), "-").trim();
                    }

                    //mobile show/hide
                    if (
                            (spnData.equalsIgnoreCase("01")
                                        || spnData.equalsIgnoreCase("02")
                                        || spnData.equalsIgnoreCase("03")
                                        || spnData.equalsIgnoreCase("04")
                                        || spnData.equalsIgnoreCase("05")
                                        || spnData.equalsIgnoreCase("06")
                                        || spnData.equalsIgnoreCase("97"))
                                    && MemSex.equals("2")
                    ) {
                        secMobileNo.setVisibility(View.VISIBLE);
                        lineMobileNo.setVisibility(View.VISIBLE);
                    } else {
                        secMobileNo.setVisibility(View.GONE);
                        lineMobileNo.setVisibility(View.GONE);
                        txtMobileNo.setText("");
                    }
                    //-----

                    if ((spnData.equalsIgnoreCase("01") || spnData.equalsIgnoreCase("02"))) {

                        spnSp1.setVisibility(View.VISIBLE);
                        lineSp1.setVisibility(View.VISIBLE);
                        secSp1.setVisibility(View.VISIBLE);
                        lineSp1.setVisibility(View.VISIBLE);
                        spnSp2.setVisibility(View.VISIBLE);
                        lineSp2.setVisibility(View.VISIBLE);
                        secSp2.setVisibility(View.VISIBLE);
                        lineSp2.setVisibility(View.VISIBLE);
                        spnSp3.setVisibility(View.VISIBLE);
                        lineSp3.setVisibility(View.VISIBLE);
                        secSp3.setVisibility(View.VISIBLE);
                        lineSp3.setVisibility(View.VISIBLE);
                        spnSp4.setVisibility(View.VISIBLE);
                        lineSp4.setVisibility(View.VISIBLE);
                        secSp4.setVisibility(View.VISIBLE);
                        lineSp4.setVisibility(View.VISIBLE);

                        if (MemSex.equals("2")) {
                            //seclbl1.setVisibility(View.VISIBLE);
                            //linelbl1.setVisibility(View.VISIBLE);
                            //secPstat.setVisibility(View.VISIBLE);
                            //linePstat.setVisibility(View.VISIBLE);

                            spnSp2.setSelection(0);
                            spnSp2.setVisibility(View.GONE);
                            lineSp2.setVisibility(View.GONE);
                            secSp2.setVisibility(View.GONE);
                            lineSp2.setVisibility(View.GONE);
                            secSp2Name.setVisibility(View.GONE);
                            txtSp2Name.setText("");

                            spnSp3.setSelection(0);
                            spnSp3.setVisibility(View.GONE);
                            lineSp3.setVisibility(View.GONE);
                            secSp3.setVisibility(View.GONE);
                            lineSp3.setVisibility(View.GONE);
                            secSp3Name.setVisibility(View.GONE);
                            txtSp3Name.setText("");

                            spnSp4.setSelection(0);
                            spnSp4.setVisibility(View.GONE);
                            lineSp4.setVisibility(View.GONE);
                            secSp4.setVisibility(View.GONE);
                            lineSp4.setVisibility(View.GONE);
                            secSp4Name.setVisibility(View.GONE);
                            txtSp4Name.setText("");
                        } else {
                            seclbl1.setVisibility(View.GONE);
                            linelbl1.setVisibility(View.GONE);
                            secPstat.setVisibility(View.GONE);
                            rdogrpPstat.clearCheck();
                            linePstat.setVisibility(View.GONE);
                            secLmpDt.setVisibility(View.GONE);
                            lineLmpDt.setVisibility(View.GONE);
                            dtpLmpDt.setText("");
                            secgage.setVisibility(View.GONE);
                            linegage.setVisibility(View.GONE);
                            txtgage.setText("");
                            secgageUnit.setVisibility(View.GONE);
                            linegageUnit.setVisibility(View.GONE);
                            rdogrpgageUnit.clearCheck();
                            secanc_regis.setVisibility(View.GONE);
                            lineanc_regis.setVisibility(View.GONE);
                            rdogrpanc_regis.clearCheck();
                            seclbl2.setVisibility(View.GONE);
                            linelbl2.setVisibility(View.GONE);
                            secanc_resp_home.setVisibility(View.GONE);
                            lineanc_resp_home.setVisibility(View.GONE);
                            chkanc_resp_home.setChecked(false);
                            secanc_other_home.setVisibility(View.GONE);
                            lineanc_other_home.setVisibility(View.GONE);
                            chkanc_other_home.setChecked(false);
                            secanc_govt_hosp.setVisibility(View.GONE);
                            lineanc_govt_hosp.setVisibility(View.GONE);
                            chkanc_govt_hosp.setChecked(false);
                            secanc_govt_health.setVisibility(View.GONE);
                            lineanc_govt_health.setVisibility(View.GONE);
                            chkanc_govt_health.setChecked(false);
                            secanc_govt_health_post.setVisibility(View.GONE);
                            lineanc_govt_health_post.setVisibility(View.GONE);
                            chkanc_govt_health_post.setChecked(false);
                            secanc_priv_hosp.setVisibility(View.GONE);
                            lineanc_priv_hosp.setVisibility(View.GONE);
                            chkanc_priv_hosp.setChecked(false);

                            secanc_tba_home.setVisibility(View.GONE);
                            secanc_tba_home.setVisibility(View.GONE);
                            chkanc_tba_home.setChecked(false);

                            secanc_ngo_hosp.setVisibility(View.GONE);
                            lineanc_ngo_hosp.setVisibility(View.GONE);
                            chkanc_ngo_hosp.setChecked(false);
                            secanc_other.setVisibility(View.GONE);
                            lineanc_other.setVisibility(View.GONE);
                            chkanc_other.setChecked(false);
                            secanc_dk.setVisibility(View.GONE);
                            lineanc_dk.setVisibility(View.GONE);
                            chkanc_dk.setChecked(false);
                            secanc_refuse.setVisibility(View.GONE);
                            lineanc_refuse.setVisibility(View.GONE);
                            chkanc_refuse.setChecked(false);
                            secanc_other_specify.setVisibility(View.GONE);
                            lineanc_other_specify.setVisibility(View.GONE);
                            txtanc_other_specify.setText("");
                            secout_date.setVisibility(View.GONE);
                            lineout_date.setVisibility(View.GONE);
                            dtpout_date.setText("");
                        }
                    } else {
                        spnSp1.setSelection(0);
                        spnSp1.setVisibility(View.GONE);
                        lineSp1.setVisibility(View.GONE);
                        secSp1.setVisibility(View.GONE);
                        lineSp1.setVisibility(View.GONE);
                        secSp1Name.setVisibility(View.GONE);
                        txtSp1Name.setText("");

                        spnSp2.setSelection(0);
                        spnSp2.setVisibility(View.GONE);
                        lineSp2.setVisibility(View.GONE);
                        secSp2.setVisibility(View.GONE);
                        lineSp2.setVisibility(View.GONE);
                        secSp2Name.setVisibility(View.GONE);
                        txtSp2Name.setText("");

                        spnSp3.setSelection(0);
                        spnSp3.setVisibility(View.GONE);
                        lineSp3.setVisibility(View.GONE);
                        secSp3.setVisibility(View.GONE);
                        lineSp3.setVisibility(View.GONE);
                        secSp3Name.setVisibility(View.GONE);
                        txtSp3Name.setText("");

                        spnSp4.setSelection(0);
                        spnSp4.setVisibility(View.GONE);
                        lineSp4.setVisibility(View.GONE);
                        secSp4.setVisibility(View.GONE);
                        lineSp4.setVisibility(View.GONE);
                        secSp4Name.setVisibility(View.GONE);
                        txtSp4Name.setText("");


                        seclbl1.setVisibility(View.GONE);
                        linelbl1.setVisibility(View.GONE);
                        secPstat.setVisibility(View.GONE);
                        rdogrpPstat.clearCheck();
                        linePstat.setVisibility(View.GONE);
                        secLmpDt.setVisibility(View.GONE);
                        lineLmpDt.setVisibility(View.GONE);
                        dtpLmpDt.setText("");
                        secgage.setVisibility(View.GONE);
                        linegage.setVisibility(View.GONE);
                        txtgage.setText("");
                        secgageUnit.setVisibility(View.GONE);
                        linegageUnit.setVisibility(View.GONE);
                        rdogrpgageUnit.clearCheck();
                        secanc_regis.setVisibility(View.GONE);
                        lineanc_regis.setVisibility(View.GONE);
                        rdogrpanc_regis.clearCheck();
                        seclbl2.setVisibility(View.GONE);
                        linelbl2.setVisibility(View.GONE);
                        secanc_resp_home.setVisibility(View.GONE);
                        lineanc_resp_home.setVisibility(View.GONE);
                        chkanc_resp_home.setChecked(false);
                        secanc_other_home.setVisibility(View.GONE);
                        lineanc_other_home.setVisibility(View.GONE);
                        chkanc_other_home.setChecked(false);
                        secanc_govt_hosp.setVisibility(View.GONE);
                        lineanc_govt_hosp.setVisibility(View.GONE);
                        chkanc_govt_hosp.setChecked(false);
                        secanc_govt_health.setVisibility(View.GONE);
                        lineanc_govt_health.setVisibility(View.GONE);
                        chkanc_govt_health.setChecked(false);
                        secanc_govt_health_post.setVisibility(View.GONE);
                        lineanc_govt_health_post.setVisibility(View.GONE);
                        chkanc_govt_health_post.setChecked(false);
                        secanc_priv_hosp.setVisibility(View.GONE);
                        lineanc_priv_hosp.setVisibility(View.GONE);
                        chkanc_priv_hosp.setChecked(false);

                        secanc_tba_home.setVisibility(View.GONE);
                        secanc_tba_home.setVisibility(View.GONE);
                        chkanc_tba_home.setChecked(false);

                        secanc_ngo_hosp.setVisibility(View.GONE);
                        lineanc_ngo_hosp.setVisibility(View.GONE);
                        chkanc_ngo_hosp.setChecked(false);
                        secanc_other.setVisibility(View.GONE);
                        lineanc_other.setVisibility(View.GONE);
                        chkanc_other.setChecked(false);
                        secanc_dk.setVisibility(View.GONE);
                        lineanc_dk.setVisibility(View.GONE);
                        chkanc_dk.setChecked(false);
                        secanc_refuse.setVisibility(View.GONE);
                        lineanc_refuse.setVisibility(View.GONE);
                        chkanc_refuse.setChecked(false);
                        secanc_other_specify.setVisibility(View.GONE);
                        lineanc_other_specify.setVisibility(View.GONE);
                        txtanc_other_specify.setText("");
                        secout_date.setVisibility(View.GONE);
                        lineout_date.setVisibility(View.GONE);
                        dtpout_date.setText("");
                    }

                    if (spnData.equalsIgnoreCase("97")) {
                        secMSOth.setVisibility(View.VISIBLE);
                        lineMSOth.setVisibility(View.VISIBLE);
                    } else {
                        secMSOth.setVisibility(View.GONE);
                        lineMSOth.setVisibility(View.GONE);
                        txtMSOth.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    return;
                }
            });

            secMSOth = (LinearLayout) findViewById(R.id.secMSOth);
            lineMSOth = (View) findViewById(R.id.lineMSOth);
            VlblMSOth = (TextView) findViewById(R.id.VlblMSOth);
            txtMSOth = (AutoCompleteTextView) findViewById(R.id.txtMSOth);
            txtMSOth.setAdapter(C.getArrayAdapter("Select distinct MSOth from " + TableName + " order by MSOth"));

            txtMSOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {

                }
            });
            txtMSOth.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;

                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (event.getRawX() >= (txtMSOth.getRight() - txtMSOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            ((EditText) v).setText("");
                            return true;
                        }
                    }
                    return false;
                }
            });
            secSp1 = (LinearLayout) findViewById(R.id.secSp1);
            lineSp1 = (View) findViewById(R.id.lineSp1);
            VlblSp1 = (TextView) findViewById(R.id.VlblSp1);
            spnSp1 = (Spinner) findViewById(R.id.spnSp1);
            spnSp1.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2'  and MemID <> '" + MEMID + "'  union Select '"+ NOT_A_MEMBER_LABEL +"'"));

            spnSp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnSp1.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnSp1.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("00")) {
                        secSp1Name.setVisibility(View.VISIBLE);
                        lineSp1Name.setVisibility(View.VISIBLE);
                    } else {
                        secSp1Name.setVisibility(View.GONE);
                        lineSp1Name.setVisibility(View.GONE);
                        txtSp1Name.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });


            secSp1Name = (LinearLayout) findViewById(R.id.secSp1Name);
            lineSp1Name = (View) findViewById(R.id.lineSp1Name);
            VlblSp1Name = (TextView) findViewById(R.id.VlblSp1Name);
            txtSp1Name = (EditText) findViewById(R.id.txtSp1Name);
            secSp2 = (LinearLayout) findViewById(R.id.secSp2);
            lineSp2 = (View) findViewById(R.id.lineSp2);
            VlblSp2 = (TextView) findViewById(R.id.VlblSp2);
            spnSp2 = (Spinner) findViewById(R.id.spnSp2);
            spnSp2.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2'  and MemID <> '" + MEMID + "'  union Select '"+ NOT_A_MEMBER_LABEL +"'"));

            spnSp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnSp2.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnSp2.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("00")) {
                        secSp2Name.setVisibility(View.VISIBLE);
                        lineSp2Name.setVisibility(View.VISIBLE);
                    } else {
                        secSp2Name.setVisibility(View.GONE);
                        lineSp2Name.setVisibility(View.GONE);
                        txtSp2Name.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secSp2Name = (LinearLayout) findViewById(R.id.secSp2Name);
            lineSp2Name = (View) findViewById(R.id.lineSp2Name);
            VlblSp2Name = (TextView) findViewById(R.id.VlblSp2Name);
            txtSp2Name = (EditText) findViewById(R.id.txtSp2Name);
            secSp3 = (LinearLayout) findViewById(R.id.secSp3);
            lineSp3 = (View) findViewById(R.id.lineSp3);
            VlblSp3 = (TextView) findViewById(R.id.VlblSp3);
            spnSp3 = (Spinner) findViewById(R.id.spnSp3);
            spnSp3.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2'  and MemID <> '" + MEMID + "'  union Select '"+ NOT_A_MEMBER_LABEL +"'"));

            spnSp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnSp3.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnSp3.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("00")) {
                        secSp3Name.setVisibility(View.VISIBLE);
                        lineSp3Name.setVisibility(View.VISIBLE);
                    } else {
                        secSp3Name.setVisibility(View.GONE);
                        lineSp3Name.setVisibility(View.GONE);
                        txtSp3Name.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secSp3Name = (LinearLayout) findViewById(R.id.secSp3Name);
            lineSp3Name = (View) findViewById(R.id.lineSp3Name);
            VlblSp3Name = (TextView) findViewById(R.id.VlblSp3Name);
            txtSp3Name = (EditText) findViewById(R.id.txtSp3Name);
            secSp4 = (LinearLayout) findViewById(R.id.secSp4);
            lineSp4 = (View) findViewById(R.id.lineSp4);
            VlblSp4 = (TextView) findViewById(R.id.VlblSp4);
            spnSp4 = (Spinner) findViewById(R.id.spnSp4);
            spnSp4.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "' and isdelete='2'  and MemID <> '" + MEMID + "'  union Select '"+ NOT_A_MEMBER_LABEL +"'"));


            spnSp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnSp4.getSelectedItem().toString().length() != 0) {
                        spnData = Connection.SelectedSpinnerValue(spnSp4.getSelectedItem().toString(), "-");
                    }
                    if (spnData.equalsIgnoreCase("00")) {
                        secSp4Name.setVisibility(View.VISIBLE);
                        lineSp4Name.setVisibility(View.VISIBLE);
                    } else {
                        secSp4Name.setVisibility(View.GONE);
                        lineSp4Name.setVisibility(View.GONE);
                        txtSp4Name.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secSp4Name = (LinearLayout) findViewById(R.id.secSp4Name);
            lineSp4Name = (View) findViewById(R.id.lineSp4Name);
            VlblSp4Name = (TextView) findViewById(R.id.VlblSp4Name);
            txtSp4Name = (EditText) findViewById(R.id.txtSp4Name);

            seclbl1 = (LinearLayout) findViewById(R.id.seclbl1);
            linelbl1 = (View) findViewById(R.id.linelbl1);
            secPstat = (LinearLayout) findViewById(R.id.secPstat);
            linePstat = (View) findViewById(R.id.linePstat);
            VlblPstat = (TextView) findViewById(R.id.VlblPstat);
            rdogrpPstat = (RadioGroup) findViewById(R.id.rdogrpPstat);
            rdoPstat1 = (RadioButton) findViewById(R.id.rdoPstat1);
            rdoPstat2 = (RadioButton) findViewById(R.id.rdoPstat2);
            rdoPstat3 = (RadioButton) findViewById(R.id.rdoPstat3);
            rdoPstat4 = (RadioButton) findViewById(R.id.rdoPstat4);
            rdoPstat5 = (RadioButton) findViewById(R.id.rdoPstat5);
            rdoPstat6 = (RadioButton) findViewById(R.id.rdoPstat6);
            rdogrpPstat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpPstat = new String[]{"41", "1", "2", "3", "40", "49"};
                    for (int i = 0; i < rdogrpPstat.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpPstat.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpPstat[i];
                    }

                    if (rbData.equalsIgnoreCase("41")) {
                        secLmpDt.setVisibility(View.VISIBLE);
                        lineLmpDt.setVisibility(View.VISIBLE);
                        secgage.setVisibility(View.VISIBLE);
                        linegage.setVisibility(View.VISIBLE);
                        secgageUnit.setVisibility(View.VISIBLE);
                        linegageUnit.setVisibility(View.VISIBLE);
                        secanc_regis.setVisibility(View.VISIBLE);
                        lineanc_regis.setVisibility(View.VISIBLE);
                     /*seclbl2.setVisibility(View.VISIBLE);
                     linelbl2.setVisibility(View.VISIBLE);
                     secanc_resp_home.setVisibility(View.VISIBLE);
                     lineanc_resp_home.setVisibility(View.VISIBLE);
                     secanc_other_home.setVisibility(View.VISIBLE);
                     lineanc_other_home.setVisibility(View.VISIBLE);
                     secanc_govt_hosp.setVisibility(View.VISIBLE);
                     lineanc_govt_hosp.setVisibility(View.VISIBLE);
                     secanc_govt_health.setVisibility(View.VISIBLE);
                     lineanc_govt_health.setVisibility(View.VISIBLE);
                     secanc_govt_health_post.setVisibility(View.VISIBLE);
                     lineanc_govt_health_post.setVisibility(View.VISIBLE);
                     secanc_priv_hosp.setVisibility(View.VISIBLE);
                     lineanc_priv_hosp.setVisibility(View.VISIBLE);
                     secanc_ngo_hosp.setVisibility(View.VISIBLE);
                     lineanc_ngo_hosp.setVisibility(View.VISIBLE);
                     secanc_other.setVisibility(View.VISIBLE);
                     lineanc_other.setVisibility(View.VISIBLE);
                     secanc_dk.setVisibility(View.VISIBLE);
                     lineanc_dk.setVisibility(View.VISIBLE);
                     secanc_refuse.setVisibility(View.VISIBLE);
                     lineanc_refuse.setVisibility(View.VISIBLE);
                     secanc_other_specify.setVisibility(View.VISIBLE);
                     lineanc_other_specify.setVisibility(View.VISIBLE);

                      */
                        secout_date.setVisibility(View.GONE);
                        lineout_date.setVisibility(View.GONE);
                        dtpout_date.setText("");
                    } else if (rbData.equalsIgnoreCase("1") || rbData.equalsIgnoreCase("2") || rbData.equalsIgnoreCase("3")) {
                        secLmpDt.setVisibility(View.GONE);
                        lineLmpDt.setVisibility(View.GONE);
                        dtpLmpDt.setText("");
                        secgage.setVisibility(View.GONE);
                        linegage.setVisibility(View.GONE);
                        txtgage.setText("");
                        secgageUnit.setVisibility(View.GONE);
                        linegageUnit.setVisibility(View.GONE);
                        rdogrpgageUnit.clearCheck();
                        secanc_regis.setVisibility(View.GONE);
                        lineanc_regis.setVisibility(View.GONE);
                        rdogrpanc_regis.clearCheck();
                        seclbl2.setVisibility(View.GONE);
                        linelbl2.setVisibility(View.GONE);
                        secanc_resp_home.setVisibility(View.GONE);
                        lineanc_resp_home.setVisibility(View.GONE);
                        chkanc_resp_home.setChecked(false);
                        secanc_other_home.setVisibility(View.GONE);
                        lineanc_other_home.setVisibility(View.GONE);
                        chkanc_other_home.setChecked(false);
                        secanc_govt_hosp.setVisibility(View.GONE);
                        lineanc_govt_hosp.setVisibility(View.GONE);
                        chkanc_govt_hosp.setChecked(false);
                        secanc_govt_health.setVisibility(View.GONE);
                        lineanc_govt_health.setVisibility(View.GONE);
                        chkanc_govt_health.setChecked(false);
                        secanc_govt_health_post.setVisibility(View.GONE);
                        lineanc_govt_health_post.setVisibility(View.GONE);
                        chkanc_govt_health_post.setChecked(false);
                        secanc_priv_hosp.setVisibility(View.GONE);
                        lineanc_priv_hosp.setVisibility(View.GONE);
                        chkanc_priv_hosp.setChecked(false);

                        secanc_tba_home.setVisibility(View.GONE);
                        secanc_tba_home.setVisibility(View.GONE);
                        chkanc_tba_home.setChecked(false);

                        secanc_ngo_hosp.setVisibility(View.GONE);
                        lineanc_ngo_hosp.setVisibility(View.GONE);
                        chkanc_ngo_hosp.setChecked(false);
                        secanc_other.setVisibility(View.GONE);
                        lineanc_other.setVisibility(View.GONE);
                        chkanc_other.setChecked(false);
                        secanc_dk.setVisibility(View.GONE);
                        lineanc_dk.setVisibility(View.GONE);
                        chkanc_dk.setChecked(false);
                        secanc_refuse.setVisibility(View.GONE);
                        lineanc_refuse.setVisibility(View.GONE);
                        chkanc_refuse.setChecked(false);
                        secanc_other_specify.setVisibility(View.GONE);
                        lineanc_other_specify.setVisibility(View.GONE);
                        txtanc_other_specify.setText("");
                        secout_date.setVisibility(View.VISIBLE);
                        lineout_date.setVisibility(View.VISIBLE);
                        dtpout_date.setText("");
                    } else {
                        secLmpDt.setVisibility(View.GONE);
                        lineLmpDt.setVisibility(View.GONE);
                        dtpLmpDt.setText("");
                        secgage.setVisibility(View.GONE);
                        linegage.setVisibility(View.GONE);
                        txtgage.setText("");
                        secgageUnit.setVisibility(View.GONE);
                        linegageUnit.setVisibility(View.GONE);
                        rdogrpgageUnit.clearCheck();
                        secanc_regis.setVisibility(View.GONE);
                        lineanc_regis.setVisibility(View.GONE);
                        rdogrpanc_regis.clearCheck();
                        seclbl2.setVisibility(View.GONE);
                        linelbl2.setVisibility(View.GONE);
                        secanc_resp_home.setVisibility(View.GONE);
                        lineanc_resp_home.setVisibility(View.GONE);
                        chkanc_resp_home.setChecked(false);
                        secanc_other_home.setVisibility(View.GONE);
                        lineanc_other_home.setVisibility(View.GONE);
                        chkanc_other_home.setChecked(false);
                        secanc_govt_hosp.setVisibility(View.GONE);
                        lineanc_govt_hosp.setVisibility(View.GONE);
                        chkanc_govt_hosp.setChecked(false);
                        secanc_govt_health.setVisibility(View.GONE);
                        lineanc_govt_health.setVisibility(View.GONE);
                        chkanc_govt_health.setChecked(false);
                        secanc_govt_health_post.setVisibility(View.GONE);
                        lineanc_govt_health_post.setVisibility(View.GONE);
                        chkanc_govt_health_post.setChecked(false);
                        secanc_priv_hosp.setVisibility(View.GONE);
                        lineanc_priv_hosp.setVisibility(View.GONE);
                        chkanc_priv_hosp.setChecked(false);

                        secanc_tba_home.setVisibility(View.GONE);
                        secanc_tba_home.setVisibility(View.GONE);
                        chkanc_tba_home.setChecked(false);

                        secanc_ngo_hosp.setVisibility(View.GONE);
                        lineanc_ngo_hosp.setVisibility(View.GONE);
                        chkanc_ngo_hosp.setChecked(false);
                        secanc_other.setVisibility(View.GONE);
                        lineanc_other.setVisibility(View.GONE);
                        chkanc_other.setChecked(false);
                        secanc_dk.setVisibility(View.GONE);
                        lineanc_dk.setVisibility(View.GONE);
                        chkanc_dk.setChecked(false);
                        secanc_refuse.setVisibility(View.GONE);
                        lineanc_refuse.setVisibility(View.GONE);
                        chkanc_refuse.setChecked(false);
                        secanc_other_specify.setVisibility(View.GONE);
                        lineanc_other_specify.setVisibility(View.GONE);
                        txtanc_other_specify.setText("");
                        secout_date.setVisibility(View.GONE);
                        lineout_date.setVisibility(View.GONE);
                        dtpout_date.setText("");
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secLmpDt = (LinearLayout) findViewById(R.id.secLmpDt);
            lineLmpDt = (View) findViewById(R.id.lineLmpDt);
            VlblLmpDt = (TextView) findViewById(R.id.VlblLmpDt);
            dtpLmpDt = (EditText) findViewById(R.id.dtpLmpDt);
            secgage = (LinearLayout) findViewById(R.id.secgage);
            linegage = (View) findViewById(R.id.linegage);
            Vlblgage = (TextView) findViewById(R.id.Vlblgage);
            txtgage = (EditText) findViewById(R.id.txtgage);
            secgageUnit = (LinearLayout) findViewById(R.id.secgageUnit);
            linegageUnit = (View) findViewById(R.id.linegageUnit);
            VlblgageUnit = (TextView) findViewById(R.id.VlblgageUnit);
            rdogrpgageUnit = (RadioGroup) findViewById(R.id.rdogrpgageUnit);
            rdogageUnit1 = (RadioButton) findViewById(R.id.rdogageUnit1);
            rdogageUnit2 = (RadioButton) findViewById(R.id.rdogageUnit2);
            secanc_regis = (LinearLayout) findViewById(R.id.secanc_regis);
            lineanc_regis = (View) findViewById(R.id.lineanc_regis);
            Vlblanc_regis = (TextView) findViewById(R.id.Vlblanc_regis);
            rdogrpanc_regis = (RadioGroup) findViewById(R.id.rdogrpanc_regis);
            rdoanc_regis1 = (RadioButton) findViewById(R.id.rdoanc_regis1);
            rdoanc_regis2 = (RadioButton) findViewById(R.id.rdoanc_regis2);

            rdogrpanc_regis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpanc_regis = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpanc_regis.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpanc_regis.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpanc_regis[i];
                    }

                    if (rbData.equalsIgnoreCase("2")) {
                        seclbl2.setVisibility(View.GONE);
                        linelbl2.setVisibility(View.GONE);
                        secanc_resp_home.setVisibility(View.GONE);
                        lineanc_resp_home.setVisibility(View.GONE);
                        chkanc_resp_home.setChecked(false);
                        secanc_other_home.setVisibility(View.GONE);
                        lineanc_other_home.setVisibility(View.GONE);
                        chkanc_other_home.setChecked(false);
                        secanc_govt_hosp.setVisibility(View.GONE);
                        lineanc_govt_hosp.setVisibility(View.GONE);
                        chkanc_govt_hosp.setChecked(false);
                        secanc_govt_health.setVisibility(View.GONE);
                        lineanc_govt_health.setVisibility(View.GONE);
                        chkanc_govt_health.setChecked(false);
                        secanc_govt_health_post.setVisibility(View.GONE);
                        lineanc_govt_health_post.setVisibility(View.GONE);
                        chkanc_govt_health_post.setChecked(false);
                        secanc_priv_hosp.setVisibility(View.GONE);
                        lineanc_priv_hosp.setVisibility(View.GONE);
                        chkanc_priv_hosp.setChecked(false);

                        secanc_tba_home.setVisibility(View.GONE);
                        secanc_tba_home.setVisibility(View.GONE);
                        chkanc_tba_home.setChecked(false);

                        secanc_ngo_hosp.setVisibility(View.GONE);
                        lineanc_ngo_hosp.setVisibility(View.GONE);
                        chkanc_ngo_hosp.setChecked(false);
                        secanc_other.setVisibility(View.GONE);
                        lineanc_other.setVisibility(View.GONE);
                        chkanc_other.setChecked(false);
                        secanc_other_specify.setVisibility(View.GONE);
                        lineanc_other_specify.setVisibility(View.GONE);
                        txtanc_other_specify.setText("");
                        secanc_dk.setVisibility(View.GONE);
                        lineanc_dk.setVisibility(View.GONE);
                        chkanc_dk.setChecked(false);
                        secanc_refuse.setVisibility(View.GONE);
                        lineanc_refuse.setVisibility(View.GONE);
                        chkanc_refuse.setChecked(false);
                        secout_date.setVisibility(View.GONE);
                        lineout_date.setVisibility(View.GONE);
                        dtpout_date.setText("");
                        secRnd.setVisibility(View.GONE);
                        lineRnd.setVisibility(View.GONE);
                        txtRnd.setText("");
                        secActive.setVisibility(View.GONE);
                        lineActive.setVisibility(View.GONE);
                        txtActive.setText("");
                        secEnType.setVisibility(View.GONE);
                        lineEnType.setVisibility(View.GONE);
                        spnEnType.setSelection(0);
                        secExType.setVisibility(View.GONE);
                        lineExType.setVisibility(View.GONE);
                        spnExType.setSelection(0);
                    } else {
                        seclbl2.setVisibility(View.VISIBLE);
                        linelbl2.setVisibility(View.VISIBLE);
                        secanc_resp_home.setVisibility(View.VISIBLE);
                        lineanc_resp_home.setVisibility(View.VISIBLE);
                        secanc_other_home.setVisibility(View.VISIBLE);
                        lineanc_other_home.setVisibility(View.VISIBLE);
                        secanc_govt_hosp.setVisibility(View.VISIBLE);
                        lineanc_govt_hosp.setVisibility(View.VISIBLE);
                        secanc_govt_health.setVisibility(View.VISIBLE);
                        lineanc_govt_health.setVisibility(View.VISIBLE);
                        secanc_govt_health_post.setVisibility(View.VISIBLE);
                        lineanc_govt_health_post.setVisibility(View.VISIBLE);
                        secanc_priv_hosp.setVisibility(View.VISIBLE);
                        lineanc_priv_hosp.setVisibility(View.VISIBLE);

                        secanc_tba_home.setVisibility(View.VISIBLE);

                        secanc_ngo_hosp.setVisibility(View.VISIBLE);
                        lineanc_ngo_hosp.setVisibility(View.VISIBLE);
                        secanc_other.setVisibility(View.VISIBLE);
                        lineanc_other.setVisibility(View.VISIBLE);
                        secanc_dk.setVisibility(View.VISIBLE);
                        lineanc_dk.setVisibility(View.VISIBLE);
                        secanc_refuse.setVisibility(View.VISIBLE);
                        lineanc_refuse.setVisibility(View.VISIBLE);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

            seclbl2 = (LinearLayout) findViewById(R.id.seclbl2);
            linelbl2 = (View) findViewById(R.id.linelbl2);
            secanc_resp_home = (LinearLayout) findViewById(R.id.secanc_resp_home);
            lineanc_resp_home = (View) findViewById(R.id.lineanc_resp_home);
            Vlblanc_resp_home = (TextView) findViewById(R.id.Vlblanc_resp_home);
            chkanc_resp_home = (CheckBox) findViewById(R.id.chkanc_resp_home);
            chkanc_resp_home.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkanc_dk.setChecked(false);
                        chkanc_refuse.setChecked(false);
                    }
                }
            });

            secanc_other_home = (LinearLayout) findViewById(R.id.secanc_other_home);
            lineanc_other_home = (View) findViewById(R.id.lineanc_other_home);
            Vlblanc_other_home = (TextView) findViewById(R.id.Vlblanc_other_home);
            chkanc_other_home = (CheckBox) findViewById(R.id.chkanc_other_home);
            chkanc_resp_home.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkanc_dk.setChecked(false);
                        chkanc_refuse.setChecked(false);
                    }
                }
            });

            secanc_govt_hosp = (LinearLayout) findViewById(R.id.secanc_govt_hosp);
            lineanc_govt_hosp = (View) findViewById(R.id.lineanc_govt_hosp);
            Vlblanc_govt_hosp = (TextView) findViewById(R.id.Vlblanc_govt_hosp);
            chkanc_govt_hosp = (CheckBox) findViewById(R.id.chkanc_govt_hosp);
            chkanc_govt_hosp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkanc_dk.setChecked(false);
                        chkanc_refuse.setChecked(false);
                    }
                }
            });

            secanc_govt_health = (LinearLayout) findViewById(R.id.secanc_govt_health);
            lineanc_govt_health = (View) findViewById(R.id.lineanc_govt_health);
            Vlblanc_govt_health = (TextView) findViewById(R.id.Vlblanc_govt_health);
            chkanc_govt_health = (CheckBox) findViewById(R.id.chkanc_govt_health);
            chkanc_govt_health.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkanc_dk.setChecked(false);
                        chkanc_refuse.setChecked(false);
                    }
                }
            });

            secanc_govt_health_post = (LinearLayout) findViewById(R.id.secanc_govt_health_post);
            lineanc_govt_health_post = (View) findViewById(R.id.lineanc_govt_health_post);
            Vlblanc_govt_health_post = (TextView) findViewById(R.id.Vlblanc_govt_health_post);
            chkanc_govt_health_post = (CheckBox) findViewById(R.id.chkanc_govt_health_post);
            chkanc_govt_health_post.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkanc_dk.setChecked(false);
                        chkanc_refuse.setChecked(false);
                    }
                }
            });

            secanc_priv_hosp = (LinearLayout) findViewById(R.id.secanc_priv_hosp);
            lineanc_priv_hosp = (View) findViewById(R.id.lineanc_priv_hosp);
            Vlblanc_priv_hosp = (TextView) findViewById(R.id.Vlblanc_priv_hosp);
            chkanc_priv_hosp = (CheckBox) findViewById(R.id.chkanc_priv_hosp);
            chkanc_priv_hosp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkanc_dk.setChecked(false);
                        chkanc_refuse.setChecked(false);
                    }
                }
            });

            secanc_tba_home = findViewById(R.id.secanc_tba_home);
            chkanc_tba_home = findViewById(R.id.chkanc_tba_home);
            chkanc_tba_home.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkanc_dk.setChecked(false);
                        chkanc_refuse.setChecked(false);
                    }
                }
            });

            secanc_ngo_hosp = (LinearLayout) findViewById(R.id.secanc_ngo_hosp);
            lineanc_ngo_hosp = (View) findViewById(R.id.lineanc_ngo_hosp);
            Vlblanc_ngo_hosp = (TextView) findViewById(R.id.Vlblanc_ngo_hosp);
            chkanc_ngo_hosp = (CheckBox) findViewById(R.id.chkanc_ngo_hosp);
            chkanc_ngo_hosp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkanc_dk.setChecked(false);
                        chkanc_refuse.setChecked(false);
                    }
                }
            });

            secanc_other = (LinearLayout) findViewById(R.id.secanc_other);
            lineanc_other = (View) findViewById(R.id.lineanc_other);
            Vlblanc_other = (TextView) findViewById(R.id.Vlblanc_other);
            chkanc_other = (CheckBox) findViewById(R.id.chkanc_other);
            chkanc_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        secanc_other_specify.setVisibility(View.VISIBLE);
                        lineanc_other_specify.setVisibility(View.VISIBLE);
                    } else {
                        secanc_other_specify.setVisibility(View.GONE);
                        lineanc_other_specify.setVisibility(View.GONE);
                        txtanc_other_specify.setText("");
                    }
                }
            });

            secanc_dk = (LinearLayout) findViewById(R.id.secanc_dk);
            lineanc_dk = (View) findViewById(R.id.lineanc_dk);
            Vlblanc_dk = (TextView) findViewById(R.id.Vlblanc_dk);
            chkanc_dk = (CheckBox) findViewById(R.id.chkanc_dk);
            chkanc_dk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkanc_resp_home.setChecked(false);
                        chkanc_other_home.setChecked(false);
                        chkanc_govt_hosp.setChecked(false);
                        chkanc_govt_health.setChecked(false);
                        chkanc_govt_health_post.setChecked(false);
                        chkanc_priv_hosp.setChecked(false);
                        chkanc_tba_home.setChecked(false);
                        chkanc_ngo_hosp.setChecked(false);
                        chkanc_other.setChecked(false);
                        chkanc_refuse.setChecked(false);
                    }
                }
            });


            secanc_refuse = (LinearLayout) findViewById(R.id.secanc_refuse);
            lineanc_refuse = (View) findViewById(R.id.lineanc_refuse);
            Vlblanc_refuse = (TextView) findViewById(R.id.Vlblanc_refuse);
            chkanc_refuse = (CheckBox) findViewById(R.id.chkanc_refuse);
            chkanc_refuse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chkanc_resp_home.setChecked(false);
                        chkanc_other_home.setChecked(false);
                        chkanc_govt_hosp.setChecked(false);
                        chkanc_govt_health.setChecked(false);
                        chkanc_govt_health_post.setChecked(false);
                        chkanc_priv_hosp.setChecked(false);
                        chkanc_tba_home.setChecked(false);
                        chkanc_ngo_hosp.setChecked(false);
                        chkanc_other.setChecked(false);
                        chkanc_dk.setChecked(false);
                    }
                }
            });

            secanc_other_specify = (LinearLayout) findViewById(R.id.secanc_other_specify);
            lineanc_other_specify = (View) findViewById(R.id.lineanc_other_specify);
            Vlblanc_other_specify = (TextView) findViewById(R.id.Vlblanc_other_specify);
            txtanc_other_specify = (AutoCompleteTextView) findViewById(R.id.txtanc_other_specify);
            txtanc_other_specify.setAdapter(C.getArrayAdapter("Select distinct anc_other_specify from " + TableName + " order by anc_other_specify"));

            txtanc_other_specify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {

                }
            });
            txtanc_other_specify.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;

                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (event.getRawX() >= (txtanc_other_specify.getRight() - txtanc_other_specify.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            ((EditText) v).setText("");
                            return true;
                        }
                    }
                    return false;
                }
            });
            secout_date = (LinearLayout) findViewById(R.id.secout_date);
            lineout_date = (View) findViewById(R.id.lineout_date);
            Vlblout_date = (TextView) findViewById(R.id.Vlblout_date);
            dtpout_date = (EditText) findViewById(R.id.dtpout_date);

            secRnd = (LinearLayout) findViewById(R.id.secRnd);
            lineRnd = (View) findViewById(R.id.lineRnd);
            VlblRnd = (TextView) findViewById(R.id.VlblRnd);
            txtRnd = (EditText) findViewById(R.id.txtRnd);
            txtRnd.setText("0");
            secActive = (LinearLayout) findViewById(R.id.secActive);
            lineActive = (View) findViewById(R.id.lineActive);
            VlblActive = (TextView) findViewById(R.id.VlblActive);
            txtActive = (EditText) findViewById(R.id.txtActive);
            txtActive.setText("1");
            secEnType = (LinearLayout) findViewById(R.id.secEnType);
            lineEnType = (View) findViewById(R.id.lineEnType);
            VlblEnType = (TextView) findViewById(R.id.VlblEnType);
            spnEnType = (Spinner) findViewById(R.id.spnEnType);
            List<String> listEnType = new ArrayList<String>();

            listEnType.add("20-By registration");
            listEnType.add("21-Migration-in");
            listEnType.add("22-Internal migration-in");
            listEnType.add("23-Split-in");
            listEnType.add("25-New birth");
            spnEnType.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listEnType)));

            secExType = (LinearLayout) findViewById(R.id.secExType);
            lineExType = (View) findViewById(R.id.lineExType);
            VlblExType = (TextView) findViewById(R.id.VlblExType);
            spnExType = (Spinner) findViewById(R.id.spnExType);
            List<String> listExType = new ArrayList<String>();

            listExType.add("");
            spnExType.setAdapter(new CustomSpinnerAdapter(this, new ArrayList<>(listExType)));




            if(!MoID.equals("")){
                String MotherSerial = C.ReturnSingleValue("select MSLNo from tmpMember where MemID='"+MoID+"'");
                String FatherSerial = C.ReturnSingleValue("select Sp1 from tmpMember where MemID='"+MoID+"'");
                spnMoNo.setSelection(Global.SpinnerItemPositionAnyLength(spnMoNo, String.valueOf(MotherSerial)));
                spnFaNo.setSelection(Global.SpinnerItemPositionAnyLength(spnFaNo, String.valueOf(FatherSerial)));
                spnEduY.setSelection(Global.SpinnerItemPositionAnyLength(spnEduY, "00"));
                spnEmploy.setSelection(Global.SpinnerItemPositionAnyLength(spnEmploy, "0"));
                spnOcp.setSelection(Global.SpinnerItemPositionAnyLength(spnOcp, "01"));
                spnMS.setSelection(Global.SpinnerItemPositionAnyLength(spnMS, "0"));
            }

        } catch (Exception e) {
            Connection.MessageBox(Surv_Member.this, e.getMessage());
            return;
        }
    }
    private String MigrationDataSave(tmpMigration_DataModel objSave) {
        String status = objSave.SaveUpdateData(this);
        return status;
    }

    private void DataSave() {
        try {
            String ValidationMSG = ValidationCheck();
            if (ValidationMSG.length() > 0) {
                Connection.MessageBox(Surv_Member.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;
            String migrationSaveStatus = "";
            if (tmpMigrationDataModel != null) {
                migrationSaveStatus = MigrationDataSave(tmpMigrationDataModel);
            }

            tmpMember_DataModel objSave = new tmpMember_DataModel(EV_TYPE);
            objSave.setMemID(txtMemID.getText().toString());
            if (EV_TYPE.equals("22") || EV_TYPE.equals("23")) {
                objSave.setHHID(HHID);
            } else {
                objSave.setHHID(txtHHID.getText().toString());
            }
            objSave.setDSSID(txtDSSID.getText().toString());
            objSave.setMSlNo(txtMSlNo.getText().toString());
            objSave.setRth(spnRth.getSelectedItemPosition() == 0 ? "" : spnRth.getSelectedItem().toString().split("-")[0]);
            objSave.setRthOth(txtRthOth.getText().toString());
            objSave.setName(txtName.getText().toString());
            String[] d_rdogrpSex = new String[]{"1", "2", "3", "4", "8"};
            objSave.setSex("");
            for (int i = 0; i < rdogrpSex.getChildCount(); i++) {
                rb = (RadioButton) rdogrpSex.getChildAt(i);
                if (rb.isChecked()) objSave.setSex(d_rdogrpSex[i]);
            }


            objSave.setBDate_D(spnBDate_D.getSelectedItemPosition() == 0 ? "" : spnBDate_D.getSelectedItem().toString().split("-")[0]);
            objSave.setBDate_M(spnBDate_M.getSelectedItemPosition() == 0 ? "" : spnBDate_M.getSelectedItem().toString().split("-")[0]);
            objSave.setBDate_Y(spnBDate_Y.getSelectedItemPosition() == 0 ? "" : spnBDate_Y.getSelectedItem().toString().split("-")[0]);
            objSave.setBDate(dtpBDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpBDate.getText().toString()) : dtpBDate.getText().toString());
            String[] d_rdogrpBDateType = new String[]{"1", "2"};
            objSave.setBDateType("");
            for (int i = 0; i < rdogrpBDateType.getChildCount(); i++) {
                rb = (RadioButton) rdogrpBDateType.getChildAt(i);
                if (rb.isChecked()) objSave.setBDateType(d_rdogrpBDateType[i]);
            }

            objSave.setAge(!secAge.isShown() ? "" : txtAge.getText().toString());
            String[] d_rdogrpAgeU = new String[]{"1", "2", "3"};
            objSave.setAgeU("");
            for (int i = 0; i < rdogrpAgeU.getChildCount(); i++) {
                rb = (RadioButton) rdogrpAgeU.getChildAt(i);
                if (rb.isChecked()) objSave.setAgeU(!secAge.isShown() ? "" : d_rdogrpAgeU[i]);
            }

            objSave.setMoNo(spnMoNo.getSelectedItemPosition() == 0 ? "" : spnMoNo.getSelectedItem().toString().split("-")[0]);
            objSave.setMoName(txtMoName.getText().toString());
            objSave.setFaNo(spnFaNo.getSelectedItemPosition() == 0 ? "" : spnFaNo.getSelectedItem().toString().split("-")[0]);
            objSave.setFaName(txtFaName.getText().toString());
            objSave.setEduY(spnEduY.getSelectedItemPosition() == 0 ? "" : spnEduY.getSelectedItem().toString().split("-")[0]);
            objSave.setEmploy(spnEmploy.getSelectedItemPosition() == 0 ? "" : spnEmploy.getSelectedItem().toString().split("-")[0]);
            objSave.setEmployOth(txtEmployOth.getText().toString());
            objSave.setOcp(spnOcp.getSelectedItemPosition() == 0 ? "" : spnOcp.getSelectedItem().toString().split("-")[0]);
            objSave.setOcpOth(txtOcpOth.getText().toString());
            String[] d_rdogrpOcpDk = new String[]{"97", "98"};
            objSave.setOcpDk("");
            for (int i = 0; i < rdogrpOcpDk.getChildCount(); i++) {
                rb = (RadioButton) rdogrpOcpDk.getChildAt(i);
                if (rb.isChecked()) objSave.setOcpDk(d_rdogrpOcpDk[i]);
            }

            objSave.setReligion(spnReligion.getSelectedItemPosition() == 0 ? "" : spnReligion.getSelectedItem().toString().split("-")[0]);
            objSave.setReligionOth(txtReligionOth.getText().toString());
            objSave.setEthnicity(spnEthnicity.getSelectedItemPosition() == 0 ? "" : spnEthnicity.getSelectedItem().toString().split("-")[0]);
            objSave.setEthnicityOth(txtEthnicityOth.getText().toString());
            objSave.setMobileNo(txtMobileNo.getText().toString());
            objSave.setMS(spnMS.getSelectedItemPosition() == 0 ? "" : spnMS.getSelectedItem().toString().split("-")[0]);
            objSave.setMSOth(txtMSOth.getText().toString());

            objSave.setSp1(spnSp1.getSelectedItemPosition() == 0 ? "" : spnSp1.getSelectedItem().toString().split("-")[0]);
            objSave.setSp1Name(txtSp1Name.getText().toString());
            objSave.setSp2(spnSp2.getSelectedItemPosition() == 0 ? "" : spnSp2.getSelectedItem().toString().split("-")[0]);
            objSave.setSp2Name(txtSp2Name.getText().toString());
            objSave.setSp3(spnSp3.getSelectedItemPosition() == 0 ? "" : spnSp3.getSelectedItem().toString().split("-")[0]);
            objSave.setSp3Name(txtSp3Name.getText().toString());
            objSave.setSp4(spnSp4.getSelectedItemPosition() == 0 ? "" : spnSp4.getSelectedItem().toString().split("-")[0]);
            objSave.setSp4Name(txtSp4Name.getText().toString());

            String[] d_rdogrpPstat = new String[]{"41", "1", "2", "3", "40", "49"};
            objSave.setPstat("");
            for (int i = 0; i < rdogrpPstat.getChildCount(); i++) {
                rb = (RadioButton) rdogrpPstat.getChildAt(i);
                if (rb.isChecked()) objSave.setPstat(d_rdogrpPstat[i]);
            }

            objSave.setLmpDt(dtpLmpDt.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpLmpDt.getText().toString()) : dtpLmpDt.getText().toString());
            objSave.setgage(txtgage.getText().toString());
            String[] d_rdogrpgageUnit = new String[]{"1", "2"};
            objSave.setgageUnit("");
            for (int i = 0; i < rdogrpgageUnit.getChildCount(); i++) {
                rb = (RadioButton) rdogrpgageUnit.getChildAt(i);
                if (rb.isChecked()) objSave.setgageUnit(d_rdogrpgageUnit[i]);
            }

            String[] d_rdogrpanc_regis = new String[]{"1", "2"};
            objSave.setanc_regis("");
            for (int i = 0; i < rdogrpanc_regis.getChildCount(); i++) {
                rb = (RadioButton) rdogrpanc_regis.getChildAt(i);
                if (rb.isChecked()) objSave.setanc_regis(d_rdogrpanc_regis[i]);
            }

            objSave.setanc_resp_home((chkanc_resp_home.isChecked() ? "1" : (secanc_resp_home.isShown() ? "2" : "")));
            objSave.setanc_other_home((chkanc_other_home.isChecked() ? "1" : (secanc_other_home.isShown() ? "2" : "")));
            objSave.setanc_govt_hosp((chkanc_govt_hosp.isChecked() ? "1" : (secanc_govt_hosp.isShown() ? "2" : "")));
            objSave.setanc_govt_health((chkanc_govt_health.isChecked() ? "1" : (secanc_govt_health.isShown() ? "2" : "")));
            objSave.setanc_govt_health_post((chkanc_govt_health_post.isChecked() ? "1" : (secanc_govt_health_post.isShown() ? "2" : "")));
            objSave.setanc_priv_hosp((chkanc_priv_hosp.isChecked() ? "1" : (secanc_priv_hosp.isShown() ? "2" : "")));

            objSave.setanc_tba_home((chkanc_tba_home.isChecked() ? "1" : (secanc_tba_home.isShown() ? "2" : "")));

            objSave.setanc_ngo_hosp((chkanc_ngo_hosp.isChecked() ? "1" : (secanc_ngo_hosp.isShown() ? "2" : "")));
            objSave.setanc_other((chkanc_other.isChecked() ? "1" : (secanc_other.isShown() ? "2" : "")));
            objSave.setanc_dk((chkanc_dk.isChecked() ? "1" : (secanc_dk.isShown() ? "2" : "")));
            objSave.setanc_refuse((chkanc_refuse.isChecked() ? "1" : (secanc_refuse.isShown() ? "2" : "")));
            objSave.setanc_other_specify(txtanc_other_specify.getText().toString());
            objSave.setout_date(dtpout_date.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpout_date.getText().toString()) : dtpout_date.getText().toString());

            objSave.setRnd(ROUND);
            objSave.setActive(txtActive.getText().toString());
            //objSave.setEnType(spnEnType.getSelectedItemPosition() == 0 ? "" : spnEnType.getSelectedItem().toString().split("-")[0]);
            objSave.setEnType(EV_TYPE);
            objSave.setExType(spnExType.getSelectedItemPosition() == 0 ? "" : spnExType.getSelectedItem().toString().split("-")[0]);
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));


            String status = objSave.SaveUpdateData(this);
            if (status.length() == 0) {
                if (EV_TYPE.equals("80")) {
                    C.SaveData("UPDATE tmpTemporaryMigrationIn set MemID='" + txtMemID.getText().toString() + "' Where TmpMigrationID='" + TmpMigrationID + "'");
                }
                if (EV_TYPE.equals("12")) {
                    sp.saveEvent(Surv_Member.this, MEMID, new SurvMemberData("12", ROUND, MEMID));
                }
                if (EV_TYPE.equals("25")) {
                    C.SaveData("Update tmpLiveBirth set MemID ='" + txtMemID.getText().toString() + "' where LiveBirthID='" + LIVEBIRTHID + "' AND HHID = '"+HHID+"'");
                }
                if(!DOD.equals("")){
                    if(MySharedPreferences.getValue(this, "child1").equals("1")){
                        sp.save(this, "child1", "complete");
                    }
                    else if(MySharedPreferences.getValue(this, "child2").equals("2")){
                        sp.save(this, "child2", "complete");
                    }
                    else if(MySharedPreferences.getValue(this, "child3").equals("3")){
                        sp.save(this, "child3", "complete");
                    }
                    else if(MySharedPreferences.getValue(this, "child4").equals("4")){
                        sp.save(this, "child4", "complete");
                    }
                    else if(MySharedPreferences.getValue(this, "child5").equals("5")){
                        sp.save(this, "child5", "complete");
                    }
                    else if(MySharedPreferences.getValue(this, "child6").equals("6")){
                        sp.save(this, "child6", "complete");
                    }
                    else if(MySharedPreferences.getValue(this, "child7").equals("7")){
                        sp.save(this, "child7", "complete");
                    }

                }

                //Member Correction
                if (EV_TYPE.equals("12")) {
                    tmpMemberMove_DataModel objSave1 = new tmpMemberMove_DataModel();
                    objSave1.setMemID(txtMemID.getText().toString());
                    objSave1.setHHID(txtHHID.getText().toString());
                    objSave1.set_MSlNo(txtMSlNo.getText().toString());

                    //Newly added
                    objSave1.setRth(spnRth.getSelectedItemPosition() == 0 ? "" : spnRth.getSelectedItem().toString().split("-")[0]);
                    objSave1.setMoNo(spnMoNo.getSelectedItemPosition() == 0 ? "" : spnMoNo.getSelectedItem().toString().split("-")[0]);
                    objSave1.setFaNo(spnFaNo.getSelectedItemPosition() == 0 ? "" : spnFaNo.getSelectedItem().toString().split("-")[0]);
                    objSave1.setEduY(spnEduY.getSelectedItemPosition() == 0 ? "" : spnEduY.getSelectedItem().toString().split("-")[0]);
                    objSave1.setOcp(spnOcp.getSelectedItemPosition() == 0 ? "" : spnOcp.getSelectedItem().toString().split("-")[0]);
                    objSave1.setOcpOth(txtOcpOth.getText().toString());
                    String[] d_rdogrpOcpDk1 = new String[]{"97", "98"};
                    objSave1.setOcpDk("");
                    for (int i = 0; i < rdogrpOcpDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpOcpDk.getChildAt(i);
                        if (rb.isChecked()) objSave1.setOcpDk(d_rdogrpOcpDk1[i]);
                    }
                    objSave1.setEmploy(spnEmploy.getSelectedItemPosition() == 0 ? "" : spnEmploy.getSelectedItem().toString().split("-")[0]);
                    objSave1.setEmployOth(txtEmployOth.getText().toString());
                    objSave1.setMS(spnMS.getSelectedItemPosition() == 0 ? "" : spnMS.getSelectedItem().toString().split("-")[0]);
                    objSave1.setMSOth(txtMSOth.getText().toString());
                    objSave1.setSp1(spnSp1.getSelectedItemPosition() == 0 ? "" : spnSp1.getSelectedItem().toString().split("-")[0]);
                    objSave1.setSp1Name(txtSp1Name.getText().toString());
                    objSave1.setSp2(spnSp2.getSelectedItemPosition() == 0 ? "" : spnSp2.getSelectedItem().toString().split("-")[0]);
                    objSave1.setSp2Name(txtSp2Name.getText().toString());
                    objSave1.setSp3(spnSp3.getSelectedItemPosition() == 0 ? "" : spnSp3.getSelectedItem().toString().split("-")[0]);
                    objSave1.setSp3Name(txtSp3Name.getText().toString());
                    objSave1.setSp4(spnSp4.getSelectedItemPosition() == 0 ? "" : spnSp4.getSelectedItem().toString().split("-")[0]);
                    objSave1.setSp4Name(txtSp4Name.getText().toString());

                    String status1 = objSave1.DataCorrection(this);

                    Toast.makeText(Surv_Member.this, "Save Successfully...", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    tmpMemberMove_DataModel objSave1 = new tmpMemberMove_DataModel();
                    objSave1.setMemID(txtMemID.getText().toString());
                    if (EV_TYPE.equals("22") || EV_TYPE.equals("23")) {
                        objSave1.setHHID(HHID);
                    } else {
                        objSave1.setHHID(txtHHID.getText().toString());
                    }

                    objSave1.setActive("1");
                    objSave1.set_MSlNo(txtMSlNo.getText().toString());
                    objSave1.setDSSID(txtDSSID.getText().toString());
                    objSave1.setMEnType(EV_TYPE);
                    objSave1.setMEnDate(EV_DATE);
                    objSave1.setMExType("");
                    objSave1.setMExDate("");
                    objSave1.setRnd(ROUND);
                    objSave1.setMemNote("");
                    objSave1.setStartTime(STARTTIME);
                    objSave1.setEndTime(g.CurrentTime24());
                    objSave1.setDeviceID(DEVICEID);
                    objSave1.setEntryUser(ENTRYUSER); //from data entry user list
                    objSave1.setLat(MySharedPreferences.getValue(this, "lat"));
                    objSave1.setLon(MySharedPreferences.getValue(this, "lon"));

                    //Newly added
                    objSave1.setRth(spnRth.getSelectedItemPosition() == 0 ? "" : spnRth.getSelectedItem().toString().split("-")[0]);
                    objSave1.setMoNo(spnMoNo.getSelectedItemPosition() == 0 ? "" : spnMoNo.getSelectedItem().toString().split("-")[0]);
                    objSave1.setFaNo(spnFaNo.getSelectedItemPosition() == 0 ? "" : spnFaNo.getSelectedItem().toString().split("-")[0]);
                    objSave1.setEduY(spnEduY.getSelectedItemPosition() == 0 ? "" : spnEduY.getSelectedItem().toString().split("-")[0]);
                    objSave1.setOcp(spnOcp.getSelectedItemPosition() == 0 ? "" : spnOcp.getSelectedItem().toString().split("-")[0]);
                    objSave1.setOcpOth(txtOcpOth.getText().toString());
                    String[] d_rdogrpOcpDk1 = new String[]{"97", "98"};
                    objSave1.setOcpDk("");
                    for (int i = 0; i < rdogrpOcpDk.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpOcpDk.getChildAt(i);
                        if (rb.isChecked()) objSave1.setOcpDk(d_rdogrpOcpDk1[i]);
                    }
                    objSave1.setEmploy(spnEmploy.getSelectedItemPosition() == 0 ? "" : spnEmploy.getSelectedItem().toString().split("-")[0]);
                    objSave1.setEmployOth(txtEmployOth.getText().toString());
                    objSave1.setMS(spnMS.getSelectedItemPosition() == 0 ? "" : spnMS.getSelectedItem().toString().split("-")[0]);
                    objSave1.setMSOth(txtMSOth.getText().toString());
                    objSave1.setSp1(spnSp1.getSelectedItemPosition() == 0 ? "" : spnSp1.getSelectedItem().toString().split("-")[0]);
                    objSave1.setSp1Name(txtSp1Name.getText().toString());
                    objSave1.setSp2(spnSp2.getSelectedItemPosition() == 0 ? "" : spnSp2.getSelectedItem().toString().split("-")[0]);
                    objSave1.setSp2Name(txtSp2Name.getText().toString());
                    objSave1.setSp3(spnSp3.getSelectedItemPosition() == 0 ? "" : spnSp3.getSelectedItem().toString().split("-")[0]);
                    objSave1.setSp3Name(txtSp3Name.getText().toString());
                    objSave1.setSp4(spnSp4.getSelectedItemPosition() == 0 ? "" : spnSp4.getSelectedItem().toString().split("-")[0]);
                    objSave1.setSp4Name(txtSp4Name.getText().toString());

                    String status1 = objSave1.SaveUpdateData(this);

                    if(status1.length()==0) {
                        //MigrationID
                        C.SaveData("UPDATE tmpMigration set MemID='" + txtMemID.getText().toString() + "' Where MigrationID='" + MigrationID + "'");

                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("res", "");
                        setResult(Activity.RESULT_OK, returnIntent);

                        Toast.makeText(Surv_Member.this, "Save Successfully...", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Connection.MessageBox(Surv_Member.this, status1);
                        return;
                    }
                }

            } else {
                Connection.MessageBox(Surv_Member.this, status);
                return;
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_Member.this, e.getMessage());
            return;
        }
    }

    private String ValidationCheck() {
        int validHHeadAge = ProjectSetting.ELIGIBILITY_HOUSEHOLD_HEAD_AGE;
        int validMarrigeAge = ProjectSetting.ELIGIBILITY_AGE_MS_YEAR;
        int validParentChildAge = 10;
        int validEmploymentAndAgeDifference = 5;
        String ValidationMsg = "";
        String DV = "";
        boolean isValidBirthDate;
        try {
            Set<String> relationshipMaritalCheckSet = new HashSet<>(Arrays.asList("02", "03", "04", "05", "16", "17", "18", "19", "27", "28", "29", "30"));
            ResetSectionColor();
            if (txtMemID.getText().toString().length() == 0 & secMemID.isShown()) {
                ValidationMsg += "\nRequired field: Internal Member ID.";
                secMemID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtHHID.getText().toString().length() == 0 & secHHID.isShown()) {
                ValidationMsg += "\nRequired field: Household ID.";
                secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtDSSID.getText().toString().length() == 0 & secDSSID.isShown()) {
                ValidationMsg += "\nRequired field: Member Permanent ID.";
                secDSSID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtMSlNo.getText().toString().length() == 0 & secMSlNo.isShown()) {
                ValidationMsg += "\nRequired field: Member Serial Number.";
                secMSlNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }

            if (spnRth.getSelectedItemPosition() == 0 & secRth.isShown()) {
                ValidationMsg += "\nHR3 Required field: What is relationship with HH head.";
                secRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnRth.getSelectedItemPosition() != 0 & secRth.isShown()) {
                String itemCode = spnRth.getSelectedItemPosition() == 0 ? "" : spnRth.getSelectedItem().toString().split("-")[0];
                String itemValue = spnRth.getSelectedItemPosition() == 0 ? "" : spnRth.getSelectedItem().toString().split("-")[1];
                Set<String> checkSetF = new HashSet<>(Arrays.asList("07", "09", "11", "13", "15", "17", "19", "21", "23", "26", "28"));
                Set<String> checkSetM = new HashSet<>(Arrays.asList("06", "08", "10", "12", "14", "16", "18", "20", "22", "27"));

                String slNo = txtMSlNo.getText().toString();
                /*if (!slNo.equalsIgnoreCase("01") && itemCode.equalsIgnoreCase("01")) {
                    ValidationMsg += "\nHR3 Member Serial Number " + slNo + " can not be Household head";
                    secRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }*/

                if (checkSetF.contains(itemCode) && rdoSex1.isChecked() && secSex.isShown()) {
                    ValidationMsg += "\nHR4 Relationship " + itemValue + " can not be Male.";
                    secSex.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    secRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                } else if (checkSetM.contains(itemCode) && rdoSex2.isChecked() && secSex.isShown()) {
                    ValidationMsg += "\nHR4 Relationship " + itemValue + " can not be Female.";
                    secSex.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    secRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }


            }

            if (txtRthOth.getText().toString().length() == 0 & secRthOth.isShown()) {
                ValidationMsg += "\nRequired field: Other relation Specify.";
                secRthOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtName.getText().toString().length() == 0 & secName.isShown()) {
                ValidationMsg += "\nHR1 Required field: Member Name.";
                secName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoSex1.isChecked() & !rdoSex2.isChecked() & !rdoSex3.isChecked() & !rdoSex4.isChecked() & !rdoSex5.isChecked() & secSex.isShown()) {
                ValidationMsg += "\nHR4 Required field: Sex.";
                secSex.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnBDate_D.getSelectedItemPosition() == 0 & secBDate_D.isShown()) {
                ValidationMsg += "\nRequired field: Day.";
                secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnBDate_M.getSelectedItemPosition() == 0 & secBDate_M.isShown()) {
                ValidationMsg += "\nRequired field: Month.";
                secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnBDate_Y.getSelectedItemPosition() == 0 & secBDate_Y.isShown()) {
                ValidationMsg += "\nRequired field: Year.";
                secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }

            if (DOD.length() > 0){
                String[] DT = dtpBDate.getText().toString().split("/");
                spnBDate_D.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_D,DT[0]));
                spnBDate_M.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_M,DT[1]));
                spnBDate_Y.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_Y,DT[2]));
            }



            if (DOD.length() != 0){
                DV = Global.DateValidate(DOD);
            } else{
                String DD = Global.Right("0"+ (spnBDate_D.getSelectedItem().toString().equals("98")?"15":spnBDate_D.getSelectedItem().toString()),2);
                String MM = Global.Right("0"+ (spnBDate_M.getSelectedItem().toString().split("-")[0].equals("98")?"6":spnBDate_M.getSelectedItem().toString().split("-")[0]),2);
                String YY = spnBDate_Y.getSelectedItem().toString();
                DV = Global.DateValidate(DD + "/" + MM + "/" + YY);
            }

            isValidBirthDate = DV.length() == 0;

            if(spnBDate_Y.getSelectedItem().toString().equals("9998")){
                int age = 0;
                if(rdoAgeU1.isChecked()) age = Integer.parseInt(txtAge.getText().toString().length()==0?"0":txtAge.getText().toString());
                else if(rdoAgeU2.isChecked()) age = (int) (Integer.parseInt(txtAge.getText().toString().length()==0?"0":txtAge.getText().toString())*30.4375);
                else if(rdoAgeU3.isChecked()) age = (int) (Integer.parseInt(txtAge.getText().toString().length()==0?"0":txtAge.getText().toString())*365.25);

                String DOB = Global.addDays(Global.DateConvertDMY(VISIT_DATE),-age);
                dtpBDate.setText(DOB);
            }
            else {
                if (DV.length() != 0 & (secBDate_D.isShown() || secBDate_M.isShown() || secBDate_Y.isShown())) {
                    ValidationMsg += "\nNot a valid date format: Date of Birth.";
                    secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                } else {
                    if (DOD.length() != 0){
                        dtpBDate.setText(DOD);
                    } else {
                        String DD = Global.Right("0"+ (spnBDate_D.getSelectedItem().toString().equals("98")?"15":spnBDate_D.getSelectedItem().toString()),2);
                        String MM = Global.Right("0"+ (spnBDate_M.getSelectedItem().toString().split("-")[0].equals("98")?"6":spnBDate_M.getSelectedItem().toString().split("-")[0]),2);
                        String YY = spnBDate_Y.getSelectedItem().toString();
                        dtpBDate.setText(DD + "/" + MM + "/" + YY);
                    }

                    String itemCode = spnRth.getSelectedItemPosition() == 0 ? "" : spnRth.getSelectedItem().toString().split("-")[0];
                    String itemValue = spnRth.getSelectedItemPosition() == 0 ? "" : spnRth.getSelectedItem().toString().split("-")[1];

                    try {
                        int age = Global.getAgeYears(Global.DateConvertYMD(dtpBDate.getText().toString()));
                        if (spnRth.getSelectedItemPosition() == 1 & secRth.isShown()) {// house hold head age >=12
                            if (age < validHHeadAge && isValidBirthDate) {
                                ValidationMsg += "\n" + itemValue + " age must be " + validMarrigeAge + " years or above. ";
                                secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                secRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            }

                        } else if (relationshipMaritalCheckSet.contains(itemCode) && age < validMarrigeAge && isValidBirthDate && secRth.isShown()) {
                            ValidationMsg += "\n" + itemValue + " age must be " + validMarrigeAge + " years or above. ";
                            secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            secRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            if (DOD.length() != 0){
                DV = Global.DateValidate(DOD);
            } else DV = Global.DateValidate(dtpBDate.getText().toString());


            if (DV.length() != 0 & secBDate.isShown()) {
                ValidationMsg += "\nHR4.1 Required field/Not a valid date format: Date of Birth.";
                secBDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoBDateType1.isChecked() & !rdoBDateType2.isChecked() & secBDateType.isShown()) {
                ValidationMsg += "\nHR4.1 Required field: Date of Birth Type.";
                secBDateType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtAge.getText().toString().length() == 0 & secAge.isShown()) {
                ValidationMsg += "\nHR5. Required field: What is age in days(1 to 59 days)/months(2 to 59 month)/years(greater or equal 5 years)?";
                secAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (!rdoAgeU1.isChecked() & !rdoAgeU2.isChecked() & !rdoAgeU3.isChecked() & secAgeU.isShown()) {
                ValidationMsg += "\nHR5. Required field: Age unit.";
                secAgeU.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtAge.getText().toString().trim().length() > 0 && secAge.isShown() && secAgeU.isShown()) {
                int ageValue = Integer.parseInt(txtAge.getText().toString().trim());


                if (rdoAgeU1.isChecked()) {
                    if (ageValue < 1 || ageValue > 59) {//1-59 days
                        ValidationMsg += "\nHR5. age in days must be 1 to 59 days";
                        secAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    }
                } else if (rdoAgeU2.isChecked()) {
                    if (ageValue < 2 || ageValue > 59) {//2-59 month
                        ValidationMsg += "\nHR5. age in months must be 2 to 59 month";
                        secAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    }
                } else if (rdoAgeU3.isChecked()) {
                    if (ageValue < 5) {//5years +
                        ValidationMsg += "\nHR5. age in years must be greater or equal 5 years";
                        secAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    }
                }
            }

            if (txtAge.getText().toString().length() > 0 & secAge.isShown()) {
             if(Integer.parseInt(txtAge.getText().toString())>130 && secAge.isShown()){
                 ValidationMsg += "\nHR5. Age should not be greater than 130.";
                 secAge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
             }
            }

            int gage = Integer.parseInt(txtgage.getText().toString().length() == 0 ? "0" : txtgage.getText().toString());
            if (rdogageUnit1.isChecked() & (gage < 4 || gage > 52) & secgage.isShown()) {
                ValidationMsg += "\nInconsistent  data: Number of Weeks of pregnancy(should be between 4 and 52 weeks).";
                secgage.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                secAgeU.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            } else if (rdogageUnit2.isChecked() & (gage < 1 || gage > 10) & secgage.isShown()) {
                ValidationMsg += "\nInconsistent  data: Number of Months of pregnancy(should be between 1 and 10 months).";
                secgage.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                secAgeU.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }



            if (spnMoNo.getSelectedItemPosition() == 0 & secMoNo.isShown()) {
                ValidationMsg += "\nHR6. Required field: Mothers ID (IF MOTHER IS A HOUSEHOLD MEMBER).";
                secMoNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }

            if (spnMoNo.getSelectedItemPosition() != 0 && spnMoNo.getSelectedItemPosition() != 1 && secMoNo.isShown()) {
                String spnData = Connection.SelectedSpinnerValue(spnMoNo.getSelectedItem().toString(), "-");
                Member_DataModel d = new Member_DataModel();
                String SQL = "Select * from " + TableName + "  Where HHID='" + HHID + "' and MSlNo='" + spnData + "'  and isdelete='2'";
                List<Member_DataModel> data = d.SelectAll(this, SQL);
                if (data != null && !data.isEmpty()) {
                    Member_DataModel memberDataModel = data.get(0);
                    try {
                        int parentAge = -1;
                        if (memberDataModel.getBDate() != null && !memberDataModel.getBDate().isEmpty()) {
                            parentAge = Global.getAgeYears(memberDataModel.getBDate());
                        }

                        int memberAge = -1;
                        if (dtpBDate.getText().toString().trim().length() > 0) {
                            memberAge = Global.getAgeYears(Global.DateConvertYMD(dtpBDate.getText().toString()));
                        }

                        if (parentAge != -1) {
                            if (parentAge < validMarrigeAge) { // check parents marrige age
                                ValidationMsg += "\nHR6." + " Mothers age must be " + validMarrigeAge + " years or above. ";
                                secMoNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            } else if (memberAge != -1) {  // check parents and child age difference
                                int ageDifference = parentAge - memberAge;
                                if (ageDifference < validParentChildAge) {
                                    ValidationMsg += "\nHR6." + " Mothers age must be " + validParentChildAge + " years or more than childs age. ";
                                    secMoNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                    secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                    secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                    secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));

                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if (txtMoName.getText().toString().length() == 0 & secMoName.isShown()) {
                ValidationMsg += "\nHR6.1. Required field: Mother Name.";
                secMoName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnFaNo.getSelectedItemPosition() == 0 & secFaNo.isShown()) {
                ValidationMsg += "\nHR7 Required field: Fathers ID (IF FATHER IS A HOUSEHOLD MEMBER).";
                secFaNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnFaNo.getSelectedItemPosition() != 0 && spnFaNo.getSelectedItemPosition() != 1 && secFaNo.isShown()) {
                String spnData = Connection.SelectedSpinnerValue(spnFaNo.getSelectedItem().toString(), "-");
                Member_DataModel d = new Member_DataModel();
                String SQL = "Select * from " + TableName + "  Where HHID='" + HHID + "' and MSlNo='" + spnData + "'  and isdelete='2'";
                List<Member_DataModel> data = d.SelectAll(this, SQL);
                if (data != null && !data.isEmpty()) {
                    Member_DataModel memberDataModel = data.get(0);
                    try {
                        int parentAge = -1;
                        if (memberDataModel.getBDate() != null && !memberDataModel.getBDate().isEmpty()) {
                            parentAge = Global.getAgeYears(memberDataModel.getBDate());
                        }
                        int memberAge = -1;
                        if (dtpBDate.getText().toString().trim().length() > 0) {
                            memberAge = Global.getAgeYears(Global.DateConvertYMD(dtpBDate.getText().toString()));
                        }
                        if (parentAge != -1) {
                            if (parentAge < validMarrigeAge) {
                                ValidationMsg += "\nHR7." + " Fathers age must be " + validMarrigeAge + " years or above. ";
                                secFaNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            } else if (memberAge != -1) {  // check parents and child age difference
                                int ageDifference = parentAge - memberAge;
                                if (ageDifference < validParentChildAge) {
                                    ValidationMsg += "\nHR7." + " Fathers age must be " + validParentChildAge + " years or more than the child's age. ";
                                    secFaNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                    secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                    secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                    secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));

                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (txtFaName.getText().toString().length() == 0 & secFaName.isShown()) {
                ValidationMsg += "\nHR7.1. Required field: Father Name.";
                secFaName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }

            int eduYearEntry = Integer.parseInt(spnEduY.getSelectedItemPosition()==0?"0":spnEduY.getSelectedItem().toString().split("-")[0]);
            if (eduYearEntry != 66 & eduYearEntry != 97 & eduYearEntry != 98 & eduYearEntry != 99 ){
                if (spnEduY.getSelectedItemPosition() == 0 & secEduY.isShown()) {
                    ValidationMsg += "\nHR8 Required field: What level of education have you completed?.";
                    secEduY.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }
                if (spnEduY.getSelectedItemPosition()> 0 & secEduY.isShown()) {
                    try {
                        int age = Global.getAgeYears(Global.DateConvertYMD(dtpBDate.getText().toString()));
                        if((eduYearEntry>=1 && eduYearEntry<=10) && age<ProjectSetting.ELIGIBILITY_AGE_EDU_YEAR){
                            ValidationMsg += "\nHR8 Check Date of Birth and What level of education have you completed.\n" +
                                    "Age should be greater than or equal "+ ProjectSetting.ELIGIBILITY_AGE_EDU_YEAR +" years for any education level.";
                            secEduY.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            secBDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        }
                        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1)){
                            if(eduYearEntry>=2 && age<6)
                            {
                                ValidationMsg += "\nHR8 Check Date of Birth and What level of education have you completed.\n" +
                                        "Age should be greater than or equal 6 years for Primary level.";
                                secEduY.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                                secBDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            }
                        }
                        /*int eduYearExpected = age - 4;
                        if (eduYearEntry != 0 && eduYearEntry > eduYearExpected) {
                            //ValidationMsg += "\nHR8 Years of education can not be grater than "+eduYearExpected;
                            ValidationMsg += "\nHR8 Check Date of Birth and What level of education have you completed.";
                            secEduY.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                            secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));

                        }*/
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (spnEmploy.getSelectedItemPosition() == 0 & secEmploy.isShown()) {
                ValidationMsg += "\nHR11 Required field: What is employment status.";
                secEmploy.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnEmploy.getSelectedItemPosition() != 0 & secEmploy.isShown()) {
                String itemCode = spnEmploy.getSelectedItemPosition() == 0 ? "" : spnEmploy.getSelectedItem().toString().split("-")[0];
                String itemValue = spnEmploy.getSelectedItemPosition() == 0 ? "" : spnEmploy.getSelectedItem().toString().split("-")[1];
                Set<String> checkSet = new HashSet<>(Arrays.asList("01", "02", "03", "97"));

                if (checkSet.contains(itemCode) && isValidBirthDate) {
                    int memberAge = -1;
                    if (dtpBDate.getText().toString().trim().length() > 0) {
                        try {
                            memberAge = Global.getAgeYears(Global.DateConvertYMD(dtpBDate.getText().toString()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    int expectedEmploymentYears = memberAge - validEmploymentAndAgeDifference;

                    if (expectedEmploymentYears < 1) {
                        ValidationMsg += "\nHR11 employment status " + itemValue + ", person must be above 5 years of age.";
                        secEmploy.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secBDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    }
                }
            }
            if (txtEmployOth.getText().toString().length() == 0 & secEmployOth.isShown()) {
                ValidationMsg += "\nHR11. Required field: Other Specify.";
                secEmployOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnOcp.getSelectedItemPosition() == 0 & secOcp.isShown()) {
                ValidationMsg += "\nHR12 Required field: What is primary income generating activity (main occupation) at this time?.";
                secOcp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtOcpOth.getText().toString().length() == 0 & secOcpOth.isShown()) {
                ValidationMsg += "\nHR12 Required field: Others occupation?.";
                secOcpOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }

            /*if (!rdoOcpDk1.isChecked() & !rdoOcpDk2.isChecked() & txtOcp.getText().toString().length() == 0 & secOcpDk.isShown()) {
                ValidationMsg += "\nHR12 Required field: Occupation (Don't know/Refuse).";
                secOcpDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }*/
            if (spnReligion.getSelectedItemPosition() == 0 & secReligion.isShown()) {
                ValidationMsg += "\nHR13 Required field: Religion.";
                secReligion.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtReligionOth.getText().toString().length() == 0 & secReligionOth.isShown()) {
                ValidationMsg += "\nRequired field: Other Religion Specify.";
                secReligionOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnEthnicity.getSelectedItemPosition() == 0 & secEthnicity.isShown()) {
                ValidationMsg += "\nHR14. Required field: Ethnicity.";
                secEthnicity.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtEthnicityOth.getText().toString().length() == 0 & secEthnicityOth.isShown()) {
                ValidationMsg += "\nHR14. Required field: Specify Other Ethnicity.";
                secEthnicityOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
           /*if(txtMobileNo.getText().toString().length()==0 & secMobileNo.isShown())
           {
             ValidationMsg += "\nHR15. Required field: Mobile No.";
             secMobileNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/
            if (spnMS.getSelectedItemPosition() == 0 & secMS.isShown()) {
                ValidationMsg += "\nHR9. Required field: What is marital status?.";
                secMS.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnRth.getSelectedItemPosition() != 0 & secRth.isShown()) {
                String itemCode = spnRth.getSelectedItemPosition() == 0 ? "" : spnRth.getSelectedItem().toString().split("-")[0];
                String itemValue = spnRth.getSelectedItemPosition() == 0 ? "" : spnRth.getSelectedItem().toString().split("-")[1];
                String itemMS = spnMS.getSelectedItemPosition() == 0 ? "" : spnMS.getSelectedItem().toString().split("-")[0];

                int memberAge_1 = -1;
                if (dtpBDate.getText().toString().trim().length() > 0) {
                    try {
                        memberAge_1 = Global.getAgeYears(Global.DateConvertYMD(dtpBDate.getText().toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (relationshipMaritalCheckSet.contains(itemCode) && itemMS.equalsIgnoreCase("00") && secMS.isShown()) {
                    ValidationMsg += "\nHR9. Relationship " + itemValue + " can not be Single/never married.";
                    secMS.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    secRth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }else if(memberAge_1 < ProjectSetting.ELIGIBILITY_AGE_MS_YEAR &&
                        (!itemMS.equalsIgnoreCase("66")
                        && !itemMS.equalsIgnoreCase("97")
                        && !itemMS.equalsIgnoreCase("98")
                        && !itemMS.equalsIgnoreCase("99"))
                        && secMS.isShown()){
                    ValidationMsg += "\nMarital status will be not applicable if age is below "+ ProjectSetting.ELIGIBILITY_AGE_MS_YEAR +" years.";
                    secMS.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                }
            }

            if (isValidBirthDate) {
                if (spnMS.getSelectedItemPosition() != 0 && secMS.isShown()) {
                    Set<String> validEverMarrigeCheckSet = new HashSet<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7"));
                    String itemMS = spnMS.getSelectedItemPosition() == 0 ? "" : spnMS.getSelectedItem().toString().split("-")[0];
                    String itemValue = spnMS.getSelectedItemPosition() == 0 ? "" : spnMS.getSelectedItem().toString().split("-")[1];
                    int memberAge = -1;
                    if (dtpBDate.getText().toString().trim().length() > 0) {
                        try {
                            memberAge = Global.getAgeYears(Global.DateConvertYMD(dtpBDate.getText().toString()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (validEverMarrigeCheckSet.contains(itemMS) && memberAge < validMarrigeAge && secMS.isShown()) {
                        ValidationMsg += "\nHR9 Marital status for " + itemValue + " can not be below " + validMarrigeAge + " years old";
                        secMS.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));

                    }
                }
            }


            if (txtMSOth.getText().toString().length() == 0 & secMSOth.isShown()) {
                ValidationMsg += "\nRequired field: Other Specify.";
                secMSOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnSp1.getSelectedItemPosition() == 0 & secSp1.isShown()) {
                ValidationMsg += "\nHR10. Required field: Spouse Serial No 1.";
                secSp1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtSp1Name.getText().toString().length() == 0 & secSp1Name.isShown()) {
                ValidationMsg += "\nHR10.1. Required field: Spouse Name.";
                secSp1Name.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (spnSp1.getSelectedItemPosition() != 0 && spnSp1.getSelectedItemPosition() != 1 && secSp1.isShown()) {
                String spnData = Connection.SelectedSpinnerValue(spnSp1.getSelectedItem().toString(), "-");
                Member_DataModel d = new Member_DataModel();
                String SQL = "Select * from " + TableName + "  Where HHID='" + HHID + "' and MSlNo='" + spnData + "'  and isdelete='2'";
                List<Member_DataModel> data = d.SelectAll(this, SQL);
                if (data != null && !data.isEmpty()) {
                    Member_DataModel memberDataModel = data.get(0);
                    int spouseAge = -1;
                    if (memberDataModel.getBDate() != null && !memberDataModel.getBDate().isEmpty()) {
                        spouseAge = Global.getAgeYears(memberDataModel.getBDate());
                    }
                    if (spouseAge != -1 && spouseAge < validMarrigeAge) {
                        ValidationMsg += "\nHR10." + " Spouse age must be " + validMarrigeAge + " years or above. ";
                        secSp1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    }
                }
            }
            if (spnSp1.getSelectedItemPosition() != 0 & secSp1.isShown()) {
                List<String> spnSpPositionList = new ArrayList<>();

                spnSpPositionList.add(spnSp4.getSelectedItem().toString()); // pos 0
                spnSpPositionList.add(spnSp3.getSelectedItem().toString()); // pos 1
                spnSpPositionList.add(spnSp2.getSelectedItem().toString()); // pos 2
                spnSpPositionList.add(spnSp1.getSelectedItem().toString()); // pos 3
                int lastSelection = 0;
                for (String selectedItem : spnSpPositionList
                ) {
                    if (!selectedItem.trim().isEmpty()) {
                        lastSelection = spnSpPositionList.indexOf(selectedItem);
                        break;
                    }
                }

                for (int i = (spnSpPositionList.size() - 1); i >= lastSelection; i--) {
                    if (i == 1 && spnSpPositionList.get(i).isEmpty()) {
                        ValidationMsg += "\nRequired field: Spouse Serial No 3.";
                        secSp3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    } else if (i == 2 && spnSpPositionList.get(i).isEmpty()) {
                        ValidationMsg += "\nRequired field: Spouse Serial No 2.";
                        secSp2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    } /*else if (i == 3 && spnSpPositionList.get(i).isEmpty()) {
                     ValidationMsg += "\nRequired field: ২য়  Visit.";
                     secSp2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                 }*/

                }
            }
            if (spnSp2.getSelectedItemPosition() != 0 && spnSp2.getSelectedItemPosition() != 1 && secSp2.isShown()) {
                String spnData = Connection.SelectedSpinnerValue(spnSp2.getSelectedItem().toString(), "-");
                Member_DataModel d = new Member_DataModel();
                String SQL = "Select * from " + TableName + "  Where HHID='" + HHID + "' and MSlNo='" + spnData + "'  and isdelete='2'";
                List<Member_DataModel> data = d.SelectAll(this, SQL);
                if (data != null && !data.isEmpty()) {
                    Member_DataModel memberDataModel = data.get(0);
                    int spouseAge = -1;
                    if (memberDataModel.getBDate() != null && !memberDataModel.getBDate().isEmpty()) {
                        spouseAge = Global.getAgeYears(memberDataModel.getBDate());
                    }
                    if (spouseAge != -1 && spouseAge < validMarrigeAge) {
                        ValidationMsg += "\nHR10.2" + " Spouse age must be " + validMarrigeAge + " years or above. ";
                        secSp2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    }
                }
            }
            if (spnSp3.getSelectedItemPosition() != 0 && spnSp3.getSelectedItemPosition() != 1 && secSp3.isShown()) {
                String spnData = Connection.SelectedSpinnerValue(spnSp3.getSelectedItem().toString(), "-");
                Member_DataModel d = new Member_DataModel();
                String SQL = "Select * from " + TableName + "  Where HHID='" + HHID + "' and MSlNo='" + spnData + "'  and isdelete='2'";
                List<Member_DataModel> data = d.SelectAll(this, SQL);
                if (data != null && !data.isEmpty()) {
                    Member_DataModel memberDataModel = data.get(0);
                    int spouseAge = -1;
                    if (memberDataModel.getBDate() != null && !memberDataModel.getBDate().isEmpty()) {
                        spouseAge = Global.getAgeYears(memberDataModel.getBDate());
                    }
                    if (spouseAge != -1 && spouseAge < validMarrigeAge) {
                        ValidationMsg += "\nHR10.3" + " Spouse age must be " + validMarrigeAge + " years or above. ";
                        secSp3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    }
                }
            }
            if (spnSp4.getSelectedItemPosition() != 0 && spnSp4.getSelectedItemPosition() != 1 && secSp4.isShown()) {
                String spnData = Connection.SelectedSpinnerValue(spnSp4.getSelectedItem().toString(), "-");
                Member_DataModel d = new Member_DataModel();
                String SQL = "Select * from " + TableName + "  Where HHID='" + HHID + "' and MSlNo='" + spnData + "'  and isdelete='2'";
                List<Member_DataModel> data = d.SelectAll(this, SQL);
                if (data != null && !data.isEmpty()) {
                    Member_DataModel memberDataModel = data.get(0);
                    int spouseAge = -1;
                    if (memberDataModel.getBDate() != null && !memberDataModel.getBDate().isEmpty()) {
                        spouseAge = Global.getAgeYears(memberDataModel.getBDate());
                    }
                    if (spouseAge != -1 && spouseAge < validMarrigeAge) {
                        ValidationMsg += "\nHR10.4" + " Spouse age must be " + validMarrigeAge + " years or above. ";
                        secSp4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                    }
                }
            }
         /*if(spnSp2.getSelectedItemPosition()==0  & secSp2.isShown())
           {
             ValidationMsg += "\nHR10.2. Required field: Spouse Serial No 2.";
             secSp2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSp2Name.getText().toString().length()==0 & secSp2Name.isShown())
           {
             ValidationMsg += "\nHR10.3. Required field: Spouse Name.";
             secSp2Name.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnSp3.getSelectedItemPosition()==0  & secSp3.isShown())
           {
             ValidationMsg += "\nHR10.4. Required field: Spouse Serial No 3.";
             secSp3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSp3Name.getText().toString().length()==0 & secSp3Name.isShown())
           {
             ValidationMsg += "\nHR10.5. Required field: Spouse Name.";
             secSp3Name.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnSp4.getSelectedItemPosition()==0  & secSp4.isShown())
           {
             ValidationMsg += "\nHR10.6. Required field: Spouse Serial No 4.";
             secSp4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSp4Name.getText().toString().length()==0 & secSp4Name.isShown())
           {
             ValidationMsg += "\nHR10.7. Required field: Spouse Name.";
             secSp4Name.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/
            if (!rdoPstat1.isChecked() & !rdoPstat2.isChecked() & !rdoPstat3.isChecked() & !rdoPstat4.isChecked() & !rdoPstat5.isChecked() & !rdoPstat6.isChecked() & secPstat.isShown()) {
                ValidationMsg += "\nRequired field: State of the Pregnancy.";
                secPstat.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpLmpDt.getText().toString());
            if (DV.length() != 0 & secLmpDt.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: LMP Date.";
                secLmpDt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (DV.length() == 0 && secLmpDt.isShown()) {

                if (dtpBDate.getText().toString().trim().length() > 0) {
                    int difference = Global.DateDifferenceDays(dtpLmpDt.getText().toString(), dtpBDate.getText().toString().trim());
                    if (difference < 1) {
                        ValidationMsg += "\nLMP Date can not be grater than date of birth";
                        secLmpDt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));

                    }
                }
            }
            if (txtgage.getText().toString().length() == 0 & secgage.isShown()) {
                ValidationMsg += "\nRequired field: Number of Weeks/Months of pregnancy.";
                secgage.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
         /*if(secgage.isShown() & (Integer.valueOf(txtgage.getText().toString().length()==0 ? "1" : txtgage.getText().toString()) < 1 || Integer.valueOf(txtgage.getText().toString().length()==0 ? "52" : txtgage.getText().toString()) > 52))
         {
             ValidationMsg += "\nValue should be between 1 and 52(Number of Weeks/Months of pregnancy).";
             secgage.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!rdogageUnit1.isChecked() & !rdogageUnit2.isChecked() & secgageUnit.isShown())
         {
             ValidationMsg += "\nRequired field: Unit.";
             secgageUnit.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }*/
            if (!rdoanc_regis1.isChecked() & !rdoanc_regis2.isChecked() & secanc_regis.isShown()) {
                ValidationMsg += "\nRequired field: Has pregnant woman registered for antenatal care(ANC)?.";
                secanc_regis.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (txtanc_other_specify.getText().toString().length() == 0 & secanc_other_specify.isShown()) {
                ValidationMsg += "\nRequired field: Others specify.";
                secanc_other_specify.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpout_date.getText().toString());
            if (DV.length() != 0 & secout_date.isShown()) {
                ValidationMsg += "\nRequired field/Not a valid date format: Date pregnancy ended.";
                secout_date.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
            }
            if (DV.length() == 0 && secout_date.isShown()) {
                if (dtpBDate.getText().toString().trim().length() > 0) {
                    int difference = Global.DateDifferenceDays(dtpout_date.getText().toString(), dtpBDate.getText().toString().trim());
                    if (difference < 1) {
                        ValidationMsg += "\nDate pregnancy ended can not be grater than date of birth";
                        secout_date.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secBDate_D.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secBDate_M.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));
                        secBDate_Y.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_Section_Highlight));

                    }
                }
            }
         /*
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             ValidationMsg += "\nRequired field: Round.";
             secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtActive.getText().toString().length()==0 & secActive.isShown())
           {
             ValidationMsg += "\nRequired field: Member active.";
             secActive.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnEnType.getSelectedItemPosition()==0  & secEnType.isShown())
           {
             ValidationMsg += "\nHR10.8. Required field: Member Entry Type.";
             secEnType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnExType.getSelectedItemPosition()==0  & secExType.isShown())
           {
             ValidationMsg += "\nHR10.9 Required field: Member Exit Type.";
             secExType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }*/


        } catch (Exception e) {
            ValidationMsg += "\n" + e.getMessage();
        }

        return ValidationMsg;
    }

    private void ResetSectionColor() {
        try {
            secMemID.setBackgroundColor(Color.WHITE);
            secHHID.setBackgroundColor(Color.WHITE);
            secDSSID.setBackgroundColor(Color.WHITE);
            secMSlNo.setBackgroundColor(Color.WHITE);
            secRth.setBackgroundColor(Color.WHITE);
            secRthOth.setBackgroundColor(Color.WHITE);
            secName.setBackgroundColor(Color.WHITE);
            secSex.setBackgroundColor(Color.WHITE);
            secBDate_D.setBackgroundColor(Color.WHITE);
            secBDate_M.setBackgroundColor(Color.WHITE);
            secBDate_Y.setBackgroundColor(Color.WHITE);
            secBDate.setBackgroundColor(Color.WHITE);
            secBDateType.setBackgroundColor(Color.WHITE);
            secAge.setBackgroundColor(Color.WHITE);
            secAgeU.setBackgroundColor(Color.WHITE);
            secMoNo.setBackgroundColor(Color.WHITE);
            secMoName.setBackgroundColor(Color.WHITE);
            secFaNo.setBackgroundColor(Color.WHITE);
            secFaName.setBackgroundColor(Color.WHITE);
            secEduY.setBackgroundColor(Color.WHITE);
            secEmploy.setBackgroundColor(Color.WHITE);
            secEmployOth.setBackgroundColor(Color.WHITE);
            secOcp.setBackgroundColor(Color.WHITE);
            secOcpOth.setBackgroundColor(Color.WHITE);
            secOcpDk.setBackgroundColor(Color.WHITE);
            secReligion.setBackgroundColor(Color.WHITE);
            secReligionOth.setBackgroundColor(Color.WHITE);
            secEthnicity.setBackgroundColor(Color.WHITE);
            secMobileNo.setBackgroundColor(Color.WHITE);
            secMS.setBackgroundColor(Color.WHITE);
            secMSOth.setBackgroundColor(Color.WHITE);
            secSp1.setBackgroundColor(Color.WHITE);
            secSp1Name.setBackgroundColor(Color.WHITE);
            secSp2.setBackgroundColor(Color.WHITE);
            secSp2Name.setBackgroundColor(Color.WHITE);
            secSp3.setBackgroundColor(Color.WHITE);
            secSp3Name.setBackgroundColor(Color.WHITE);
            secSp4.setBackgroundColor(Color.WHITE);
            secSp4Name.setBackgroundColor(Color.WHITE);
            secPstat.setBackgroundColor(Color.WHITE);
            secLmpDt.setBackgroundColor(Color.WHITE);
            secgage.setBackgroundColor(Color.WHITE);
            secgage.setBackgroundColor(Color.WHITE);
            secgageUnit.setBackgroundColor(Color.WHITE);
            secanc_regis.setBackgroundColor(Color.WHITE);
            secanc_other_specify.setBackgroundColor(Color.WHITE);
            secout_date.setBackgroundColor(Color.WHITE);
            secRnd.setBackgroundColor(Color.WHITE);
            secActive.setBackgroundColor(Color.WHITE);
            secEnType.setBackgroundColor(Color.WHITE);
            secExType.setBackgroundColor(Color.WHITE);
        } catch (Exception e) {
        }
    }

    private void DataSearch(String MemID) {
        try {
            RadioButton rb;
            tmpMember_DataModel d = new tmpMember_DataModel("");
            String SQL = "";
            if (EV_TYPE.equals("22") || EV_TYPE.equals("23")) {
                SQL = "Select * from migMember Where MemID='" + MemID + "'";
            } else {
                SQL = "Select * from tmpMember Where MemID='" + MemID + "'";
            }
            List<tmpMember_DataModel> data = d.SelectAll(this, SQL);
            for (tmpMember_DataModel item : data) {
                txtMemID.setText(item.getMemID());
                txtHHID.setText(item.getHHID());
                txtDSSID.setText(item.getDSSID());
                if (EV_TYPE.equals("22") || EV_TYPE.equals("23")) {
                }
                else {
                    txtMSlNo.setText(item.getMSlNo());
                }

                spnRth.setSelection(Global.SpinnerItemPositionAnyLength(spnRth, String.valueOf(item.getRth())));
                txtRthOth.setText(item.getRthOth());
                txtName.setText(item.getName());
                String[] d_rdogrpSex = new String[]{"1", "2", "3", "4", "8"};
                for (int i = 0; i < d_rdogrpSex.length; i++) {
                    if (String.valueOf(item.getSex()).equals(String.valueOf(d_rdogrpSex[i]))) {
                        rb = (RadioButton) rdogrpSex.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnBDate_D.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_D, String.valueOf(item.getBDate_D())));
                spnBDate_M.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_M, String.valueOf(Global.Right("00"+item.getBDate_M(),2))));
                spnBDate_Y.setSelection(Global.SpinnerItemPositionAnyLength(spnBDate_Y, String.valueOf(item.getBDate_Y())));
                dtpBDate.setText(item.getBDate().length() == 0 ? "" : Global.DateConvertDMY(item.getBDate()));

                String[] d_rdogrpBDateType = new String[]{"1", "2"};
                for (int i = 0; i < d_rdogrpBDateType.length; i++) {
                    if (String.valueOf(item.getBDateType()).equals(String.valueOf(d_rdogrpBDateType[i]))) {
                        rb = (RadioButton) rdogrpBDateType.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                txtAge.setText(item.getAge());
                String[] d_rdogrpAgeU = new String[]{"1", "2", "3"};
                for (int i = 0; i < d_rdogrpAgeU.length; i++) {
                    if (String.valueOf(item.getAgeU()).equals(String.valueOf(d_rdogrpAgeU[i]))) {
                        rb = (RadioButton) rdogrpAgeU.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnMoNo.setSelection(Global.SpinnerItemPositionAnyLength(spnMoNo, String.valueOf(item.getMoNo())));
                txtMoName.setText(item.getMoName());
                spnFaNo.setSelection(Global.SpinnerItemPositionAnyLength(spnFaNo, String.valueOf(item.getFaNo())));
                txtFaName.setText(item.getFaName());
                spnEduY.setSelection(Global.SpinnerItemPositionAnyLength(spnEduY, String.valueOf(item.getEduY())));
                spnEmploy.setSelection(Global.SpinnerItemPositionAnyLength(spnEmploy, String.valueOf(item.getEmploy())));
                txtEmployOth.setText(item.getEmployOth());
                spnOcp.setSelection(Global.SpinnerItemPositionAnyLength(spnOcp, String.valueOf(item.getOcp())));
                txtOcpOth.setText(item.getOcpOth());
                String[] d_rdogrpOcpDk = new String[]{"97", "98"};
                for (int i = 0; i < d_rdogrpOcpDk.length; i++) {
                    if (String.valueOf(item.getOcpDk()).equals(String.valueOf(d_rdogrpOcpDk[i]))) {
                        rb = (RadioButton) rdogrpOcpDk.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnReligion.setSelection(Global.SpinnerItemPositionAnyLength(spnReligion, String.valueOf(item.getReligion())));
                txtReligionOth.setText(item.getReligionOth());
                spnEthnicity.setSelection(Global.SpinnerItemPositionAnyLength(spnEthnicity, String.valueOf(item.getEthnicity())));
                txtEthnicityOth.setText(item.getEthnicityOth());
                txtMobileNo.setText(item.getMobileNo());
                spnMS.setSelection(Global.SpinnerItemPositionAnyLength(spnMS, String.valueOf(item.getMS())));
                txtMSOth.setText(item.getMSOth());
                spnSp1.setSelection(Global.SpinnerItemPositionAnyLength(spnSp1, String.valueOf(item.getSp1())));
                txtSp1Name.setText(item.getSp1Name());
                spnSp2.setSelection(Global.SpinnerItemPositionAnyLength(spnSp2, String.valueOf(item.getSp2())));
                txtSp2Name.setText(item.getSp2Name());
                spnSp3.setSelection(Global.SpinnerItemPositionAnyLength(spnSp3, String.valueOf(item.getSp3())));
                txtSp3Name.setText(item.getSp3Name());
                spnSp4.setSelection(Global.SpinnerItemPositionAnyLength(spnSp4, String.valueOf(item.getSp4())));
                txtSp4Name.setText(item.getSp4Name());
                String[] d_rdogrpPstat = new String[]{"41", "1", "2", "3", "40", "49"};
                for (int i = 0; i < d_rdogrpPstat.length; i++) {
                    if (String.valueOf(item.getPstat()).equals(String.valueOf(d_rdogrpPstat[i]))) {
                        rb = (RadioButton) rdogrpPstat.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                dtpLmpDt.setText(item.getLmpDt().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getLmpDt()));
                txtgage.setText(String.valueOf(item.getgage()));
                String[] d_rdogrpgageUnit = new String[]{"1", "2"};
                for (int i = 0; i < d_rdogrpgageUnit.length; i++) {
                    if (String.valueOf(item.getgageUnit()).equals(String.valueOf(d_rdogrpgageUnit[i]))) {
                        rb = (RadioButton) rdogrpgageUnit.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                String[] d_rdogrpanc_regis = new String[]{"1", "2"};
                for (int i = 0; i < d_rdogrpanc_regis.length; i++) {
                    if (String.valueOf(item.getanc_regis()).equals(String.valueOf(d_rdogrpanc_regis[i]))) {
                        rb = (RadioButton) rdogrpanc_regis.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                if (String.valueOf(item.getanc_resp_home()).equals("1")) {
                    chkanc_resp_home.setChecked(true);
                } else if (String.valueOf(item.getanc_resp_home()).equals("2")) {
                    chkanc_resp_home.setChecked(false);
                }
                if (String.valueOf(item.getanc_other_home()).equals("1")) {
                    chkanc_other_home.setChecked(true);
                } else if (String.valueOf(item.getanc_other_home()).equals("2")) {
                    chkanc_other_home.setChecked(false);
                }
                if (String.valueOf(item.getanc_govt_hosp()).equals("1")) {
                    chkanc_govt_hosp.setChecked(true);
                } else if (String.valueOf(item.getanc_govt_hosp()).equals("2")) {
                    chkanc_govt_hosp.setChecked(false);
                }
                if (String.valueOf(item.getanc_govt_health()).equals("1")) {
                    chkanc_govt_health.setChecked(true);
                } else if (String.valueOf(item.getanc_govt_health()).equals("2")) {
                    chkanc_govt_health.setChecked(false);
                }
                if (String.valueOf(item.getanc_govt_health_post()).equals("1")) {
                    chkanc_govt_health_post.setChecked(true);
                } else if (String.valueOf(item.getanc_govt_health_post()).equals("2")) {
                    chkanc_govt_health_post.setChecked(false);
                }
                if (String.valueOf(item.getanc_priv_hosp()).equals("1")) {
                    chkanc_priv_hosp.setChecked(true);
                } else if (String.valueOf(item.getanc_priv_hosp()).equals("2")) {
                    chkanc_priv_hosp.setChecked(false);
                }

                if (String.valueOf(item.getanc_tba_home()).equals("1")) {
                    chkanc_tba_home.setChecked(true);
                } else if (String.valueOf(item.getanc_tba_home()).equals("2")) {
                    chkanc_tba_home.setChecked(false);
                }

                if (String.valueOf(item.getanc_ngo_hosp()).equals("1")) {
                    chkanc_ngo_hosp.setChecked(true);
                } else if (String.valueOf(item.getanc_ngo_hosp()).equals("2")) {
                    chkanc_ngo_hosp.setChecked(false);
                }
                if (String.valueOf(item.getanc_other()).equals("1")) {
                    chkanc_other.setChecked(true);
                } else if (String.valueOf(item.getanc_other()).equals("2")) {
                    chkanc_other.setChecked(false);
                }
                if (String.valueOf(item.getanc_dk()).equals("1")) {
                    chkanc_dk.setChecked(true);
                } else if (String.valueOf(item.getanc_dk()).equals("2")) {
                    chkanc_dk.setChecked(false);
                }
                if (String.valueOf(item.getanc_refuse()).equals("1")) {
                    chkanc_refuse.setChecked(true);
                } else if (String.valueOf(item.getanc_refuse()).equals("2")) {
                    chkanc_refuse.setChecked(false);
                }
                txtanc_other_specify.setText(item.getanc_other_specify());
                dtpout_date.setText(item.getout_date().toString().length() == 0 ? "" : Global.DateConvertDMY(item.getout_date()));

                //txtRnd.setText(item.getRnd());
                //txtActive.setText(item.getActive());
                spnEnType.setSelection(Global.SpinnerItemPositionAnyLength(spnEnType, String.valueOf(item.getEnType())));
                spnExType.setSelection(Global.SpinnerItemPositionAnyLength(spnExType, String.valueOf(item.getExType())));
            }

            if (EV_TYPE.equals("22") || EV_TYPE.equals("23")) {
                spnRth.setSelection(0);
                spnMoNo.setSelection(0);
                spnFaNo.setSelection(0);
            }
            /*String slNo = txtMSlNo.getText().toString();
            if (!slNo.isEmpty() && slNo.equalsIgnoreCase("01")) {
                spnRth.setSelection(1);
                spnRth.setEnabled(false);
                //spnRth.setClickable(false);

            } else {
                spnRth.setEnabled(true);
                //spnRth.setClickable(true);
            }*/

        } catch (Exception e) {
            Connection.MessageBox(Surv_Member.this, e.getMessage());
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


            dtpDate = (EditText) findViewById(R.id.dtpBDate);
            if (VariableID.equals("btnBDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpBDate);
            } else if (VariableID.equals("btnLmpDt")) {
                dtpDate = (EditText) findViewById(R.id.dtpLmpDt);
            } else if (VariableID.equals("btnout_date")) {
                dtpDate = (EditText) findViewById(R.id.dtpout_date);
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

        }
    };

    private int calclateAgeFromDMY(String DD, String MM, String YY){

        int Age = 0;
//        if(secAge.isShown()){
//            if( txtAge.getText().toString().length() > 0){
//                Age = Integer.parseInt(txtAge.getText().toString());
//            }
//        }
//        else{
//            String DD = Global.Right("0"+ (spnBDate_D.getSelectedItem().toString().equals("98")?"15":spnBDate_D.getSelectedItem().toString()),2);
//            String MM = Global.Right("0"+ (spnBDate_M.getSelectedItem().toString().split("-")[0].equals("98")?"6":spnBDate_M.getSelectedItem().toString().split("-")[0]),2);
//            String YY = spnBDate_Y.getSelectedItem().toString();
            if(Global.DateValidate(DD + "/" + MM + "/" + YY).length() == 0){
                Age = Global.DateDifferenceYears(Global.DateNowDMY(), DD + "/" + MM + "/" + YY);
            }
//        }
        return Age;
    }
    private int calclateAgeFromAgeText(String AGE){

        int Age = 0;
//        if(secAge.isShown()){
            if( AGE.length() > 0){
                Age = Integer.parseInt(AGE);
            }
//        }
//        else{
//            String DD = Global.Right("0"+ (spnBDate_D.getSelectedItem().toString().equals("98")?"15":spnBDate_D.getSelectedItem().toString()),2);
//            String MM = Global.Right("0"+ (spnBDate_M.getSelectedItem().toString().split("-")[0].equals("98")?"6":spnBDate_M.getSelectedItem().toString().split("-")[0]),2);
//            String YY = spnBDate_Y.getSelectedItem().toString();
//            if(Global.DateValidate(DD + "/" + MM + "/" + YY).length() == 0){
//        Age = Global.DateDifferenceYears(Global.DateNowDMY(), DD + "/" + MM + "/" + YY);
//            }
//        }
        return Age;
    }


    public String MemNo(String HHID) {
        String M = C.ReturnSingleValue("Select cast(ifnull(max(MSlNo),0)+1 as varchar(2))MemNo from tmpMemberMove where HHID='" + HHID + "'");
        M = Global.Right("0" + M, 2);
        return M;
    }

    private String Get_DSSID_Mali(String _HHID)
    {
        String SL = C.ReturnSingleValue("Select substr('00'||cast((ifnull(max(cast(substr(DSSID,10,2) as int)),0)+1) as varchar(5)),-2) from tmpMemberMove where HHID='"+ _HHID +"'");
        Log.d("DSSID", "Get_DSSID: "+ SL);
        return HHNO + SL;
    }

    // turning off the GPS if its in on state. to avoid the battery drain.
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}