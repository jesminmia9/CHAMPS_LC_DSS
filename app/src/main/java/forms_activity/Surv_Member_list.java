package forms_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.icddrb.champs_lc_dss.R;
import org.icddrb.champs_lc_dss.adapter.DeliveryAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import Common.Connection;
import Common.Global;
import Common.Global_CodeList;
import Common.ProjectSetting;
import Interfaces.DeliveryClick;
import Utility.MySharedPreferences;
import forms_datamodel.Abortion_DataModel;
import forms_datamodel.Climate_Change_DataModel;
import forms_datamodel.Death_DataModel;
import forms_datamodel.Delivery_DataModel;
import forms_datamodel.Education_DataModel;
import forms_datamodel.FatherSerial_DataModel;
import forms_datamodel.Household_DataModel;
import forms_datamodel.LiveBirth_DataModel;
import forms_datamodel.MaritalSts_DataModel;
import forms_datamodel.MemberMove_DataModel;
import forms_datamodel.Member_DataModel;
import forms_datamodel.Migration_DataModel;
import forms_datamodel.MotherSerial_DataModel;
import forms_datamodel.Movement_DataModel;
import forms_datamodel.NBC_NBCare_DataModel;
import forms_datamodel.NotPregnant_DataModel;
import forms_datamodel.Occupation_DataModel;
import forms_datamodel.PregHis_DataModel;
import forms_datamodel.Pregnancy_DataModel;
import forms_datamodel.Relation_DataModel;
import forms_datamodel.SES_CrossRiver_DataModel;
import forms_datamodel.SES_DataModel;
import forms_datamodel.SES_Mali_DataModel;
import forms_datamodel.SpouseSerial_DataModel;
import forms_datamodel.StillBirth_DataModel;
import forms_datamodel.TemporaryMigrationIn_DataModel;
import forms_datamodel.TemporaryMigrationOut_DataModel;
import forms_datamodel.Visits_DataModel;
import forms_datamodel.migMember_DataModel;
import forms_datamodel.tmpAbortion_DataModel;
import forms_datamodel.tmpClimate_Change_DataModel;
import forms_datamodel.tmpDeath_DataModel;
import forms_datamodel.tmpDelivery_DataModel;
import forms_datamodel.tmpEducation_DataModel;
import forms_datamodel.tmpFatherSerial_DataModel;
import forms_datamodel.tmpHousehold_DataModel;
import forms_datamodel.tmpLiveBirth_DataModel;
import forms_datamodel.tmpMaritalSts_DataModel;
import forms_datamodel.tmpMemberMove_DataModel;
import forms_datamodel.tmpMember_DataModel;
import forms_datamodel.tmpMigration_DataModel;
import forms_datamodel.tmpMotherSerial_DataModel;
import forms_datamodel.tmpMovement_DataModel;
import forms_datamodel.tmpNotPregnant_DataModel;
import forms_datamodel.tmpOccupation_DataModel;
import forms_datamodel.tmpPregHis_DataModel;
import forms_datamodel.tmpPregnancy_DataModel;
import forms_datamodel.tmpRelation_DataModel;
import forms_datamodel.tmpSES_CrossRiver_DataModel;
import forms_datamodel.tmpSES_DataModel;
import forms_datamodel.tmpSES_Mali_DataModel;
import forms_datamodel.tmpSpouseSerial_DataModel;
import forms_datamodel.tmpStillBirth_DataModel;
import forms_datamodel.tmpTemporaryMigrationIn_DataModel;
import forms_datamodel.tmpTemporaryMigrationOut_DataModel;
import forms_datamodel.tmpVisits_DataModel;

public class Surv_Member_list extends AppCompatActivity implements DeliveryClick {
    boolean networkAvailable = false;
    Location currentLocation;
    double currentLatitude, currentLongitude;

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

    String VariableID;
    private int mDay;
    private int mMonth;
    private int mYear;
    final int DATE_DIALOG = 1;
    final int TIME_DIALOG = 2;

    Connection C;
    Global g;
    private List<tmpMember_DataModel> dataList = new ArrayList<>();
    private List<tmpDelivery_DataModel> tmpDeliveryDataModels = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
    String TableName;

    TextView lblHeading;
    TextView lblTotal;
    Button btnAdd;
    ImageButton btnSearch;
    EditText txtSearch;
    Button btnprocess;
    EditText dtpFDate;
    EditText dtpTDate;
    TextView lastVdate;
    TextView lastVdateText;

    TextView lblCompound1;
    TextView lblMemHHID;
    TextView lblCompoundAddr;
    String STARTTIME = "";
    String DEVICEID = "";

    String ENTRYUSER = "";

    Button btnSES;
    Button btnClimateChange;
    Button btnDthNotification;
    Button btnInformation;
    Button btnVisits;
    FloatingActionButton fb_datacleaning;
    ConstraintLayout ll_no_data;

    String MEMID = "";
    String HHID = "";
    String HHNO = "";
    String HHIDHEAD = "";
    String MSlNo = "";

    String COMPOUNDNAME = "";
    String COMPOUNDADRS = "";
    String HHHEAD = "";
    String ROUND = "";
    String VISITNO = "";
    String VISIT_DATE = "";
    String VISIT_STATUS = "";
    String response = "";

    Bundle IDbundle;
    MySharedPreferences sp;
    int LANGUAGEID = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.surv_member_list);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            C = new Connection(this);
            g = Global.getInstance();
            STARTTIME = g.CurrentTime24();
            sp = new MySharedPreferences();

            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));

            IDbundle = getIntent().getExtras();
            HHID = IDbundle.getString("HHID");
            HHNO = IDbundle.getString("HHNO");
            COMPOUNDNAME = IDbundle.getString("CompoundName");
            COMPOUNDADRS = IDbundle.getString("CompoundAdrs");
            HHHEAD = IDbundle.getString("HHHead");
            ROUND = IDbundle.getString("round");
            MSlNo = IDbundle.getString("MSlNo");
            VISITNO = IDbundle.getString("VisitNo");
            VISIT_DATE = IDbundle.getString("VisitDate");
            VISIT_STATUS = IDbundle.getString("VisitStatus");

            TableName = "tmpMember";
            lblHeading = (TextView) findViewById(R.id.lblHeading);
            lblTotal = (TextView) findViewById(R.id.lblTotal);

            btnInformation = findViewById(R.id.btnInformation);
            btnVisits = findViewById(R.id.btnVisits);

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Member_list.this);
                    adb.setTitle("Cancel Transaction");
                    adb.setIcon(R.drawable.favicon);
                    if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                        adb.setMessage("Voulez-vous annuler la transaction en cours [Annuler/Non] ?\n\n" +
                                "Si vous annulez la transaction, toutes les données de la transaction en cours seront supprimées.");
                    }else {
                        adb.setMessage("Do you want to cancel the current transaction [Cancel/No]?\n\nIf you cancel the transaction, all data of the current transaction will be deleted.");
                    }
                    adb.setNegativeButton("No", null);
                    adb.setPositiveButton("Cancel", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //tpmDataDelete();
                            finish();
                        }

                    });
                    adb.show();
                }
            });

            btnAdd = (Button) findViewById(R.id.btnAdd);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    NewMemberEntry(VISIT_DATE);
                }
            });

            btnSES = (Button) findViewById(R.id.btnSES);

            btnSES.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    /*Bundle IDbundle = new Bundle();
                    IDbundle.putString("HHID", HHID);
                    IDbundle.putString("SESNo", "");
                    IDbundle.putString("SurvType", "");
                    IDbundle.putString("round", ROUND);
                    IDbundle.putString("visit_date", VISIT_DATE);

                    Intent intent = new Intent(getApplicationContext(), Surv_SES_list.class);
                    intent.putExtras(IDbundle);
                    startActivityForResult(intent, 1);*/

                    if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                        Bundle IDbundle = new Bundle();
                        IDbundle.putString("HHID", HHID);
                        IDbundle.putString("SESNo", "");
                        IDbundle.putString("SurvType", "");
                        IDbundle.putString("round", ROUND);
                        IDbundle.putString("visit_date", VISIT_DATE);

                        Intent intent = new Intent(getApplicationContext(), Surv_SES_MALI_list.class);
                        intent.putExtras(IDbundle);
                        startActivityForResult(intent, 1);
                    }
                    else{
                        Bundle IDbundle = new Bundle();
                        IDbundle.putString("HHID", HHID);
                        IDbundle.putString("SESNo", "");
                        IDbundle.putString("SurvType", "");
                        IDbundle.putString("visit_date", VISIT_DATE);
                        IDbundle.putString("round", ROUND);

                        Intent intent = new Intent(getApplicationContext(), Surv_SES_list.class);
                        intent.putExtras(IDbundle);
                        startActivityForResult(intent, 1);
                    }

                }
            });


            btnClimateChange = (Button) findViewById(R.id.btnClimateChange);

            btnClimateChange.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("HHID", HHID);
                    IDbundle.putString("round", ROUND);

                    Intent intent = new Intent(getApplicationContext(), Climate_Change_list.class);
                    intent.putExtras(IDbundle);
                    startActivityForResult(intent, 1);
                }
            });

            btnDthNotification = (Button) findViewById(R.id.btnDthNotification);
            btnDthNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("HHID", HHID);
                    IDbundle.putString("HHNO", HHNO);
                    Intent intent = new Intent(getApplicationContext(), Surv_DeathNotifi_list.class);
                    intent.putExtras(IDbundle);
                    //startActivityForResult(intent, 1);
                    startActivity(intent);
                }
            });


            if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                if(C.Existence("select * from tmpSES_Mali where HHID='"+ HHID +"'")){
                    double decimalValue = Double.parseDouble(C.ReturnSingleValue("Select Cast((cast((ifnull(cast((julianday(date('now'))-julianday(date(SESVDate))) as int),0)) as int)/30.4375) as int)ses_dur from tmpSES_Mali where HHID='"+ HHID +"' order by date(SESVDate) desc limit 1"));
                    String sesvstat = (C.ReturnSingleValue("Select SESVStatus from tmpSES_Mali where HHID='"+ HHID +"' order by date(SESVDate) desc limit 1"));
                    if((int)decimalValue < ProjectSetting.SES_DURATION_MONTHS*30){
                        if(sesvstat.equals("1")){
                            btnSES.setBackgroundResource(R.drawable.style_completed_square_shape);
                        }
                        else if (sesvstat.equals("2")) {
                            btnSES.setBackgroundResource(R.drawable.style_square_shape_yellow);
                        }
                        else if (sesvstat.equals("3")) {
                            btnSES.setBackgroundResource(R.drawable.button_style_red);
                        }
                    }else{
                        btnSES.setBackgroundResource(R.drawable.style_circle_line_incomplete);
                    }
                }else{
                    btnSES.setBackgroundResource(R.drawable.style_circle_line_incomplete);
                }

                if(C.Existence("select * from tmpClimate_Change where HHID='"+ HHID +"'")){
                    btnClimateChange.setBackgroundResource(R.drawable.style_completed_square_shape);
                }else{
                    btnClimateChange.setBackgroundResource(R.drawable.style_circle_line_incomplete);
                }
                btnDthNotification.setVisibility(View.GONE);
                btnClimateChange.setVisibility(View.VISIBLE);
            }
            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
                if(C.Existence("select * from tmpSES_CrossRiver where HHID='"+ HHID +"'")){
                    double decimalValue = Double.parseDouble(C.ReturnSingleValue("Select Cast((cast((ifnull(cast((julianday(date('now'))-julianday(date(SESVDate))) as int),0)) as int)/30.4375) as int)ses_dur from tmpSES_CrossRiver where HHID='"+ HHID +"' order by date(SESVDate) desc limit 1"));
                    String sesvstat = (C.ReturnSingleValue("Select SESVStatus from tmpSES_CrossRiver where HHID='"+ HHID +"' order by date(SESVDate) desc limit 1"));

                    if((int)decimalValue < ProjectSetting.SES_DURATION_MONTHS*30){
                        if(sesvstat.equals("1")){
                            btnSES.setBackgroundResource(R.drawable.style_completed_square_shape);
                        }
                        else if (sesvstat.equals("2")) {
                            btnSES.setBackgroundResource(R.drawable.style_square_shape_yellow);
                        }
                        else if (sesvstat.equals("3")) {
                            btnSES.setBackgroundResource(R.drawable.button_style_red);
                        }
                    }else{
                        btnSES.setBackgroundResource(R.drawable.style_circle_line_incomplete);
                    }
                }else{
                    btnSES.setBackgroundResource(R.drawable.style_circle_line_incomplete);
                }
                btnDthNotification.setVisibility(View.VISIBLE);
                btnClimateChange.setVisibility(View.GONE);
            }
            else{
                if(C.Existence("select * from tmpSES where HHID='"+ HHID +"'")){
                    double decimalValue = Double.parseDouble(C.ReturnSingleValue("Select Cast((cast((ifnull(cast((julianday(date('now'))-julianday(date(SESVDate))) as int),0)) as int)/30.4375) as int)ses_dur from tmpSES where HHID='"+ HHID +"' order by date(SESVDate) desc limit 1"));
                    String sesvstat = (C.ReturnSingleValue("Select SESVStatus from tmpSES where HHID='"+ HHID +"' order by date(SESVDate) desc limit 1"));
                    if((int)decimalValue < ProjectSetting.SES_DURATION_MONTHS*30){
                        if(sesvstat.equals("1")){
                            btnSES.setBackgroundResource(R.drawable.style_completed_square_shape);
                        }
                        else if (sesvstat.equals("2")) {
                            btnSES.setBackgroundResource(R.drawable.style_square_shape_yellow);
                        }
                        else if (sesvstat.equals("3")) {
                            btnSES.setBackgroundResource(R.drawable.button_style_red);
                        }
                    }else{
                        btnSES.setBackgroundResource(R.drawable.style_circle_line_incomplete);
                    }
                }else{
                    btnSES.setBackgroundResource(R.drawable.style_circle_line_incomplete);
                }
                btnDthNotification.setVisibility(View.VISIBLE);
                btnClimateChange.setVisibility(View.GONE);
            }

            btnInformation.setOnClickListener(view -> {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("round", ROUND);
                Intent f1 = new Intent(getApplicationContext(), Surv_Event_list.class);
                f1.putExtras(IDbundle);
                startActivity(f1);
            });
            btnVisits.setOnClickListener(view -> {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("round", ROUND);
                Intent f1 = new Intent(getApplicationContext(), Surv_Visits_list.class);
                f1.putExtras(IDbundle);
                startActivityForResult(f1,1);
            });
            txtSearch = (EditText) findViewById(R.id.txtSearch);

            btnSearch = (ImageButton) findViewById(R.id.btnSearch);
            btnSearch.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
//                         DataSearch(txtSearch.getText().toString());
                }
            });


            fb_datacleaning = findViewById(R.id.fb_datacleaning);
            fb_datacleaning.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("HHID", HHID);
                    IDbundle.putString("round", ROUND);

                    Intent f1 = new Intent(getApplicationContext(), cleaning_household_list.class);
                    f1.putExtras(IDbundle);
                    startActivity(f1);
                }
            });

            btnprocess = (Button) findViewById(R.id.btnprocess);

            btnprocess.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog_0 = ProgressDialog.show(Surv_Member_list.this, "", "Data has been verifying, Please Wait . . .", true);

                    new Thread() {
                        public void run() {
                            try {
                                response = "";

                                //Entire household migrated-out
                                if(VISIT_STATUS.equals("77")) {
                                    //check if there is any active member available
                                    if(C.Existence("select m.HHID from tmpMember m inner join tmpMemberMove mv on m.MemID=mv.MemID where m.HHID='"+ HHID +"' and m.isdelete=2 and length(ifnull(mv.MExType,''))=0")) {
                                        response = "You have selected options that entire household migrated out but still there is an active household member.";
                                    }
                                }else{
                                    response = Process_Validation(HHID);
                                }

                                if(response.length()>0){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Connection.MessageBox(Surv_Member_list.this,response);
                                            if(progDailog_0.isShowing()) progDailog_0.dismiss();
                                        }
                                    });

                                    //finish();
                                }else {
                                    progDailog_0.dismiss();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Member_list.this);
                                            adb.setTitle("Process Transaction");
                                            adb.setIcon(R.drawable.favicon);
                                            adb.setMessage("Do you want to save all the changes and process current transaction[Process/Cancel]?");
                                            adb.setNegativeButton("Cancel", null);
                                            adb.setPositiveButton("Process", new AlertDialog.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {

                                                    final ProgressDialog progDailog_1 = ProgressDialog.show(Surv_Member_list.this, "", "New data has been processing. Please Wait . . .", true);

                                                    new Thread() {
                                                        public void run() {
                                                            try {
                                                                //Table Name: Visits
                                                                VisitsDataTransfer(HHID, VISITNO, ROUND);

                                                                //Table Name: Household
                                                                HouseholdDataTransfer(HHID);

                                                                //Table Name: Member
                                                                MemberDataTransfer(HHID);

                                                                //Table Name: Climate_Change
                                                                Climate_ChangeDataTransfer(HHID);

                                                                //Table Name: SES
                                                                SESDataTransfer(HHID);

                                                                //Table name: SES Mali
                                                                SESMaliDataTransfer(HHID);

                                                                //Table name: SES Cross River
                                                                SESCrossRiverDataTransfer(HHID);

                                                                //Table Name: PregHis
                                                                PreHisDataTransfer(HHID);

                                                                //Table Name: MemberMove
                                                                MemberMoveDataTransfer(HHID);

                                                                //Table Name: Migration
                                                                MigrationDataTransfer(HHID);
                                                                TemporaryMigrationOutDataTransfer(HHID);
                                                                TemporaryMigrationInDataTransfer(HHID);

                                                                //Table Name: Movement
                                                                MovementDataTransfer(HHID);

                                                                //Table Name: Death
                                                                DeathDataTransfer(HHID);

                                                                //Table Name: Delivery
                                                                DeliveryDataTransfer(HHID);

                                                                //Table Name: Education
                                                                EducationDataTransfer(HHID);

                                                                //Table Name: FatherSerial
                                                                FatherSerialDataTransfer(HHID);

                                                                //Table Name: LiveBirth
                                                                LiveBirthDataTransfer(HHID);

                                                                //Table Name: MaritalSts
                                                                MaritalStsDataTransfer(HHID);

                                                                //Table Name: MotherSerial
                                                                MotherSerialDataTransfer(HHID);

                                                                //Table Name: NotPregnant
                                                                NotPregnantDataTransfer(HHID);

                                                                //Table Name: Occupation
                                                                OccupationDataTransfer(HHID);

                                                                //Table Name: Pregnancy
                                                                PregnancyDataTransfer(HHID);

                                                                //Table Name: Relation
                                                                RelationDataTransfer(HHID);

                                                                //Table Name: SpouseSerial
                                                                SpouseSerialDataTransfer(HHID);

                                                                //Table Name: StillBirth
                                                                StillBirthDataTransfer(HHID);

                                                                //Table Name: Abortion
                                                                AbortionDataTransfer(HHID);

                                                                //Clean temp data
                                                                tpmDataDelete();

                                                                //Entire household migrated out
                                                                if(VISIT_STATUS.equals("77")){
                                                                    //update household information ExType and Date
                                                                    C.SaveData("Update Household set HHExType='77',HHExDate='"+ Global.DateConvertYMD(VISIT_DATE) +"',upload=2,Active=2 where HHID='"+ HHID +"'");
                                                                }

                                                            } catch (Exception e) {
                                                                runOnUiThread(new Runnable() {
                                                                    @Override
                                                                    public void run() {

                                                                        progDailog_1.dismiss();
                                                                        Intent returnIntent = new Intent();
                                                                        returnIntent.putExtra("res", "mem");
                                                                        setResult(Activity.RESULT_OK, returnIntent);
                                                                        finish();
                                                                    }
                                                                });
                                                            }
                                                            runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {

                                                                    progDailog_1.dismiss();
                                                                    Intent returnIntent = new Intent();
                                                                    returnIntent.putExtra("res", "mem");
                                                                    setResult(Activity.RESULT_OK, returnIntent);
                                                                    finish();
                                                                }
                                                            });
                                                        }
                                                    }.start();
                                                }

                                            });
                                            adb.show();
                                        }
                                    });
                                }
                            } catch (Exception exp) {
                                progDailog_0.dismiss();
                                Connection.MessageBox(Surv_Member_list.this, exp.getMessage());
                            }
                        }
                    }.start();
                }
            });


            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            ll_no_data = findViewById(R.id.ll_no_data);
            mAdapter = new DataAdapter(dataList);
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

            dtpFDate = (EditText) findViewById(R.id.dtpFDate);
            dtpTDate = (EditText) findViewById(R.id.dtpTDate);
            lastVdate =  (TextView) findViewById(R.id.lastVdate);
            lastVdateText =  (TextView) findViewById(R.id.lastVdateText);
            dtpFDate.setText(Global.DateNowDMY());
            dtpTDate.setText(Global.DateNowDMY());
            dtpFDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (event.getRawX() >= (dtpFDate.getRight() - dtpFDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            VariableID = "dtpFDate";
                            showDialog(DATE_DIALOG);
                            return true;
                        }
                    }
                    return false;
                }
            });

            dtpTDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (event.getRawX() >= (dtpTDate.getRight() - dtpTDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            VariableID = "dtpTDate";
                            showDialog(DATE_DIALOG);
                            return true;
                        }
                    }
                    return false;
                }
            });

            lblMemHHID = findViewById(R.id.lblMemHHID);
            lblCompoundAddr = findViewById(R.id.lblCompoundAddr);
            lblMemHHID.setText(HHNO + ", " + HHHEAD);
            lblCompoundAddr.setText(COMPOUNDNAME + ", " + COMPOUNDADRS);

            DataSearch(HHID);
            infoButtonCount(HHID);


        } catch (Exception e) {
            Connection.MessageBox(Surv_Member_list.this, e.getMessage());
            return;
        }
    }

    private void infoButtonCount(String HHID){
        String sql = "SELECT (a + b + c + d + e + f + g + h + i + j + k + l + m) as '' \n" +
                "FROM (\n" +
                "  SELECT \n" +
                "    (SELECT COUNT(*) FROM tmpMaritalSts WHERE HHID = '"+HHID+"' AND Rnd = '"+ROUND+ "') as a,\n" +
                "    (SELECT COUNT(*) FROM tmpEducation WHERE HHID = '"+HHID+"' AND Rnd = '"+ROUND+ "') as b,\n" +
                "    (SELECT COUNT(*) FROM tmpRelation WHERE HHID = '"+HHID+"' AND Rnd = '"+ROUND+ "') as c,\n" +
                "    (SELECT COUNT(*) FROM tmpFatherSerial WHERE HHID = '"+HHID+"' AND Rnd = '"+ROUND+ "') as d,\n" +
                "    (SELECT COUNT(*) FROM tmpMotherSerial WHERE HHID = '"+HHID+"' AND Rnd = '"+ROUND+ "') as e,\n" +
                "    (SELECT COUNT(*) FROM tmpSpouseSerial WHERE HHID = '"+HHID+"' AND Rnd = '"+ROUND+ "') as f,\n" +
                "    (SELECT COUNT(*) FROM tmpDeath WHERE HHID = '"+HHID+"' AND Rnd = '"+ROUND+ "') as g,\n" +
                "    (SELECT COUNT(*) FROM tmpMigration WHERE HHID = '"+HHID+"' AND Rnd = '"+ROUND+ "') as h,\n" +
                "    (SELECT COUNT(*) FROM tmpMovement WHERE HHID = '"+HHID+"' AND Rnd = '"+ROUND+ "') as i,\n" +
                "    (SELECT COUNT(*) FROM tmpNotPregnant WHERE HHID = '"+HHID+"' AND Rnd = '"+ROUND+ "') as j,\n" +
                "    (SELECT COUNT(*) FROM tmpPregnancy WHERE HHID = '"+HHID+"' AND Rnd = '"+ROUND+ "') as k,\n" +
                "    (SELECT COUNT(*) FROM tmpDelivery WHERE HHID = '"+HHID+"' AND Rnd = '"+ROUND+ "') as l,\n" +
                "    (SELECT COUNT(*) FROM tmpOccupation WHERE HHID = '"+HHID+"' AND Rnd = '"+ROUND+ "') as m\n" +
                ")";
        String total = C.ReturnSingleValue(sql);

        ((Button)findViewById(R.id.btnInformation)).setText("Events("+total+")");

        /*if (dataList.size() == 0){
            btnInformation.setText("Events");
        } else {
            btnInformation.setText("Events("+total+")");
        }*/


        String query = "SELECT VisitDate FROM Visits WHERE HHID ='"+HHID+"' order by date(VisitDate) desc limit 1";
        String visitDate = C.ReturnSingleValue(query);
        if (visitDate != null && !visitDate.isEmpty()) {
            ((TextView)findViewById(R.id.lastVdate)).setText(Global.DateConvertDMY(visitDate));
            ((TextView)findViewById(R.id.lastVdateText)).setText("Last Visit: "+ Global.DateConvertDMY(visitDate));
            //lastVdate.setText(Global.DateConvertDMY(visitDate));
            //lastVdateText.setText("Last Visit Date:");
        } else {
            //lastVdate.setVisibility(View.GONE);
            //lastVdateText.setVisibility(View.GONE);
            ((TextView)findViewById(R.id.lastVdate)).setVisibility(View.GONE);
            ((TextView)findViewById(R.id.lastVdateText)).setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        DataSearch(HHID);
        infoButtonCount(HHID);
        String _table_name="";
        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
            _table_name = "tmpSES_Mali";
        }else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
            _table_name = "tmpSES_CrossRiver";
        }else{
            _table_name = "tmpSES";
        }

        if(C.Existence("select * from "+ _table_name +" where HHID='"+ HHID +"'")){
            double decimalValue = Double.parseDouble(C.ReturnSingleValue("Select (julianday('now')-julianday(date(a.SESVDate)))duration from (select ifnull(max(SESVDate),date('now','-"+ ProjectSetting.SES_DURATION_MONTHS*30+1 +" day'))SESVDate from "+ _table_name +" where HHID='"+ HHID +"' order by SESVDate desc limit 1)a "));
            if((int)decimalValue < ProjectSetting.SES_DURATION_MONTHS*30){
                btnSES.setBackgroundResource(R.drawable.style_completed_square_shape);
            }
        }


        /*if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
            if(C.Existence("select * from tmpSES_Mali where HHID='"+ HHID +"'")){
                double decimalValue = Double.parseDouble(C.ReturnSingleValue("Select (julianday('now')-julianday(date(a.SESVDate)))duration from (select ifnull(max(SESVDate),date('now','-"+ ProjectSetting.SES_DURATION_MONTHS*30+1 +" day'))SESVDate from tmpSES_Mali where HHID='"+ HHID +"' order by SESVDate desc limit 1)a "));
                if((int)decimalValue < ProjectSetting.SES_DURATION_MONTHS*30){
                    btnSES.setBackgroundResource(R.drawable.style_completed_square_shape);
                }
            }
        }
        else{
            if(C.Existence("select * from tmpSES where HHID='"+ HHID +"'")){
                double decimalValue = Double.parseDouble(C.ReturnSingleValue("Select (julianday('now')-julianday(date(a.SESVDate)))duration from (select ifnull(max(SESVDate),date('now','-"+ ProjectSetting.SES_DURATION_MONTHS*30+1 +" day'))SESVDate from tmpSES where HHID='"+ HHID +"' order by SESVDate desc limit 1)a "));
                if((int)decimalValue < ProjectSetting.SES_DURATION_MONTHS*30){
                    btnSES.setBackgroundResource(R.drawable.style_completed_square_shape);
                }
            }
        }*/
        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
            if(C.Existence("select * from tmpSES_Mali where HHID='"+ HHID +"'")){
                double decimalValue = Double.parseDouble(C.ReturnSingleValue("Select Cast((cast((ifnull(cast((julianday(date('now'))-julianday(date(SESVDate))) as int),0)) as int)/30.4375) as int)ses_dur from tmpSES_Mali where HHID='"+ HHID +"' order by date(SESVDate) desc limit 1"));
                String sesvstat = (C.ReturnSingleValue("Select SESVStatus from tmpSES_Mali where HHID='"+ HHID +"' order by date(SESVDate) desc limit 1"));
                if((int)decimalValue < ProjectSetting.SES_DURATION_MONTHS*30){
                    if(sesvstat.equals("1")){
                        btnSES.setBackgroundResource(R.drawable.style_completed_square_shape);
                    }
                    else if (sesvstat.equals("2")) {
                        btnSES.setBackgroundResource(R.drawable.style_square_shape_yellow);
                    }
                    else if (sesvstat.equals("3")) {
                        btnSES.setBackgroundResource(R.drawable.button_style_red);
                    }
                }else{
                    btnSES.setBackgroundResource(R.drawable.style_circle_line_incomplete);
                }
            }else{
                btnSES.setBackgroundResource(R.drawable.style_circle_line_incomplete);
            }
            btnDthNotification.setVisibility(View.GONE);
        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
            if(C.Existence("select * from tmpSES_CrossRiver where HHID='"+ HHID +"'")){
                double decimalValue = Double.parseDouble(C.ReturnSingleValue("Select Cast((cast((ifnull(cast((julianday(date('now'))-julianday(date(SESVDate))) as int),0)) as int)/30.4375) as int)ses_dur from tmpSES_CrossRiver where HHID='"+ HHID +"' order by date(SESVDate) desc limit 1"));
                String sesvstat = (C.ReturnSingleValue("Select SESVStatus from tmpSES_CrossRiver where HHID='"+ HHID +"' order by date(SESVDate) desc limit 1"));
                Log.e("tag",sesvstat);
                Log.e("tag",sesvstat);
                Log.e("tag",sesvstat);
                Log.e("tag",sesvstat);
                Log.d("tag",sesvstat);
                Log.d("tag",sesvstat);
                Log.d("tag",sesvstat);
                Log.d("tag",sesvstat);
                if((int)decimalValue < ProjectSetting.SES_DURATION_MONTHS*30){
                    if(sesvstat.equals("1")){
                        btnSES.setBackgroundResource(R.drawable.style_completed_square_shape);
                    }
                    else if (sesvstat.equals("2")) {
                        btnSES.setBackgroundResource(R.drawable.style_square_shape_yellow);
                    }
                    else if (sesvstat.equals("3")) {
                        btnSES.setBackgroundResource(R.drawable.button_style_red);
                    }
                }else{
                    btnSES.setBackgroundResource(R.drawable.style_circle_line_incomplete);
                }
            }else{
                btnSES.setBackgroundResource(R.drawable.style_circle_line_incomplete);
            }
            btnDthNotification.setVisibility(View.VISIBLE);
        }
        else{
            if(C.Existence("select * from tmpSES where HHID='"+ HHID +"'")){
                double decimalValue = Double.parseDouble(C.ReturnSingleValue("Select Cast((cast((ifnull(cast((julianday(date('now'))-julianday(date(SESVDate))) as int),0)) as int)/30.4375) as int)ses_dur from tmpSES where HHID='"+ HHID +"' order by date(SESVDate) desc limit 1"));
                String sesvstat = (C.ReturnSingleValue("Select SESVStatus from tmpSES where HHID='"+ HHID +"' order by date(SESVDate) desc limit 1"));
                if((int)decimalValue < ProjectSetting.SES_DURATION_MONTHS*30){
                    if(sesvstat.equals("1")){
                        btnSES.setBackgroundResource(R.drawable.style_completed_square_shape);
                    }
                    else if (sesvstat.equals("2")) {
                        btnSES.setBackgroundResource(R.drawable.style_square_shape_yellow);
                    }
                    else if (sesvstat.equals("3")) {
                        btnSES.setBackgroundResource(R.drawable.button_style_red);
                    }
                }else{
                    btnSES.setBackgroundResource(R.drawable.style_circle_line_incomplete);
                }
            }else{
                btnSES.setBackgroundResource(R.drawable.style_circle_line_incomplete);
            }
            btnDthNotification.setVisibility(View.VISIBLE);
        }

        //data cleaning status
        if(C.Existence("Select * from cleaning_household where HHID='"+ HHID +"' and Rnd='"+ ROUND +"'")){
            if(C.Existence("Select sum(case when status='1' then 1 else 0 end)status_yes,count(status)total from cleaning_household where HHID='"+ HHID +"' and Rnd='"+ ROUND +"' group by HHID having sum(case when status='1' then 1 else 0 end)=count(status)"))
            {
                fb_datacleaning.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_completed)));
            }else {
                fb_datacleaning.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
            }
        }else{
            fb_datacleaning.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_not_done)));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            //Write your code if there's no result
        } else {
//         DataSearch(HHID);
        }
    }

    private void NewMemberEntry(String visit_date) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.surv_menu_newmember);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        ImageButton btnClose = (ImageButton) dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        MaterialCardView ev_20 = dialog.findViewById(R.id.ev_20);
        ev_20.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("MigrationID", "");
                IDbundle.putString("MemID", "");
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", "20");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("visit_date", visit_date);
                Intent intent = new Intent(getApplicationContext(), Surv_Migration.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_21 = dialog.findViewById(R.id.ev_21);
        ev_21.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_21.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("MigrationID", "");
                IDbundle.putString("MemID", "");
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", "21");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("visit_date", visit_date);
                Intent intent = new Intent(getApplicationContext(), Surv_Migration.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_22 = dialog.findViewById(R.id.ev_22);
        ev_22.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_22.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("MovementID", "");
                IDbundle.putString("MemID", "");
                IDbundle.putString("evtype", "22");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("mslno", MemNo(HHID));
                IDbundle.putString("visit_date", visit_date);
                Intent intent = new Intent(getApplicationContext(), Surv_Migration_Member_list.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_80 = dialog.findViewById(R.id.ev_80);
        ev_80.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_80.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("TmpMigrationID", "");
                IDbundle.putString("MemID", "");
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", "80");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("vdate", visit_date);
                Intent intent = new Intent(getApplicationContext(), Surv_TemporaryMigrationIn.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


    private void MemberEvent(String MemID, String Title, String AgeDays, String Sex, String MS, String Pstat, String visit_date) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.surv_menu_events);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        /*Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);*/

        ImageButton btnClose = (ImageButton) dialog.findViewById(R.id.btnClose);
        TextView lblTitle = (TextView) dialog.findViewById(R.id.lblTitle);
        lblTitle.setText(Title);
        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        MaterialCardView ev_12 = dialog.findViewById(R.id.ev_12);
        ev_12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
//
//                SurvMemberData data = sp.getValueEvent(Surv_Member_list.this, MemID);
//                if (data != null && "12".equals(data.getEvType()) && ROUND.equals(data.getRound()) && MemID.equals(data.getMemID())) {
//                    Connection.MessageBox(Surv_Member_list.this, "This event is already occurred, now you can change information from Events list.");
//                    return;
//                }

                Bundle IDbundle = new Bundle();
                IDbundle.putString("MigrationID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "12");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("vdate", Global.DateConvertYMD(visit_date));  //yyyy-mm-dd
                IDbundle.putString("dod", "");
                IDbundle.putString("MoID", "");
                IDbundle.putString("LiveBirthID", "");
                IDbundle.putString("TmpMigrationID", "");

                Intent intent = new Intent(getApplicationContext(), Surv_Member.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_31 = dialog.findViewById(R.id.ev_31);
        ev_31.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("MaritStsID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "31");
                IDbundle.putString("ms", MS);
                IDbundle.putString("round", ROUND);
                IDbundle.putString("vdate", visit_date); //dd/mm/yyyy
                Intent intent = new Intent(getApplicationContext(), Surv_MaritalSts.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_40 = dialog.findViewById(R.id.ev_40);
        ev_40.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
//                SurvMemberData data = sp.getValueEvent(Surv_Member_list.this, MemID+"NP");
//                if (data != null && "40".equals(data.getEvType()) && ROUND.equals(data.getRound()) && MemID.equals(data.getMemID())) {
//                    Connection.MessageBox(Surv_Member_list.this, "This event is already occurred, please edit from event list.");
//                    return;
//                }

                Bundle IDbundle = new Bundle();
                IDbundle.putString("NotPregID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "40");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("vdate", visit_date); //dd/mm/yyyy
                Intent intent = new Intent(getApplicationContext(), Surv_NotPregnant.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_41 = dialog.findViewById(R.id.ev_41);
        ev_41.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (C.Existence("Select Pstat from tmpMember Where HHID='" + HHID + "' and MemID='" + MemID + "' and Pstat='41'")) {
                    Connection.MessageBox(Surv_Member_list.this, "This member is not eligible for this Event, She is currently pregnant");
                    return;
                }

                Bundle IDbundle = new Bundle();
                IDbundle.putString("PregOccurID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "41");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("isEdit", "0");
                IDbundle.putString("vdate", visit_date); //dd/mm/yyyy
                Intent intent = new Intent(getApplicationContext(), Surv_Pregnancy.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_42 = dialog.findViewById(R.id.ev_42);
        ev_42.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (!C.Existence("Select Pstat from tmpMember Where HHID='" + HHID + "' and MemID='" + MemID + "' and Pstat='41'")) {
                    Connection.MessageBox(Surv_Member_list.this, "This member is not eligible for this Event, She is currently not Pregnant");
                    return;
                }

                String pregid = C.ReturnSingleValue("select PregOccurID from tmpPregnancy where HHID='" + HHID + "' and MemID='" + MemID + "' order by LMPDt desc limit 1");

                Bundle IDbundle = new Bundle();
                IDbundle.putString("DeliveryID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", "42");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("edit", "1");
                IDbundle.putString("pregid", pregid);
                IDbundle.putString("vdate", visit_date); //dd/mm/yyyy
                Intent intent = new Intent(getApplicationContext(), Surv_Delivery.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_51 = dialog.findViewById(R.id.ev_51);
        ev_51.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("MigrationID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "51");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("vdate", visit_date); //dd/mm/yyyy
                Intent intent = new Intent(getApplicationContext(), Surv_Migration.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_52 = dialog.findViewById(R.id.ev_52);
        ev_52.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("MovementID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "52");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("edit", "1");
                IDbundle.putString("vdate", visit_date); //dd/mm/yyyy
                Intent intent = new Intent(getApplicationContext(), Surv_Movement.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_55 = dialog.findViewById(R.id.ev_55);
        ev_55.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("DeathID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "55");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("age", AgeDays);
                IDbundle.putString("vdate", visit_date); //dd/mm/yyyy
                Intent intent = new Intent(getApplicationContext(), Surv_Death.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_61 = dialog.findViewById(R.id.ev_61);
        ev_61.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("MothSerialID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "61");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("age", AgeDays);
                IDbundle.putString("vdate", visit_date); //dd/mm/yyyy
                Intent intent = new Intent(getApplicationContext(), Surv_MotherSerial.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });
        MaterialCardView ev_62 = dialog.findViewById(R.id.ev_62);
        ev_62.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("FathSerialID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "62");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("age", AgeDays);
                IDbundle.putString("vdate", visit_date); //dd/mm/yyyy
                Intent intent = new Intent(getApplicationContext(), Surv_FatherSerial.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_63 = dialog.findViewById(R.id.ev_63);
        ev_63.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("SpSerialID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "63");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("vdate", visit_date); //dd/mm/yyyy
                Intent intent = new Intent(getApplicationContext(), Surv_SpouseSerial.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_64 = dialog.findViewById(R.id.ev_64);
        ev_64.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("RthID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "63");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("age", AgeDays);
                IDbundle.putString("vdate", visit_date); //dd/mm/yyyy
                Intent intent = new Intent(getApplicationContext(), Surv_Relation.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView ev_71 = dialog.findViewById(R.id.ev_71);
        ev_71.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("EduID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "71");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("isEdit", "1");
                IDbundle.putString("age", AgeDays);
                IDbundle.putString("vdate", visit_date); //dd/mm/yyyy
                Intent intent = new Intent(getApplicationContext(), Surv_Education.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });
        MaterialCardView ev_72 = dialog.findViewById(R.id.ev_72);
        ev_72.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("OcpID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "72");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("vdate", visit_date); //dd/mm/yyyy
                Intent intent = new Intent(getApplicationContext(), Surv_Occupation.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        ev_31.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_40.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_41.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_42.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_51.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_52.setBackgroundResource(R.drawable.style_not_completed_square_shape);

        ev_61.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_62.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_63.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_64.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_71.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_72.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_55.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_12.setBackgroundResource(R.drawable.style_not_completed_square_shape);

        //Male
        if (Sex.equals("1")) {
            ev_40.setBackgroundResource(R.drawable.style_not_done_square_shape);
            ev_40.setEnabled(false);
            ev_41.setBackgroundResource(R.drawable.style_not_done_square_shape);
            ev_41.setEnabled(false);
            ev_42.setBackgroundResource(R.drawable.style_not_done_square_shape);
            ev_42.setEnabled(false);
        } else if (Sex.equals("2")) {
//            if (!MS.trim().equals("01") && !MS.trim().equals("02") && !MS.trim().equals("03")) {
//                ev_40.setBackgroundResource(R.drawable.style_not_done_square_shape);
//                ev_40.setEnabled(false);
//                ev_41.setBackgroundResource(R.drawable.style_not_done_square_shape);
//                ev_41.setEnabled(false);
//                ev_42.setBackgroundResource(R.drawable.style_not_done_square_shape);
//                ev_42.setEnabled(false);
//            }

            if (Pstat.equals("41")) {
                ev_40.setBackgroundResource(R.drawable.style_not_done_square_shape);
                ev_40.setEnabled(false);
                ev_41.setBackgroundResource(R.drawable.style_not_done_square_shape);
                ev_41.setEnabled(false);
            }else{
                ev_42.setBackgroundResource(R.drawable.style_not_done_square_shape);
                ev_42.setEnabled(false);
            }
        }


        //MS:<10 years
        if (Integer.parseInt(AgeDays) <= 3652) {
            ev_31.setBackgroundResource(R.drawable.style_not_done_square_shape);
            ev_31.setEnabled(false);
        }
        //SpouseSl:<10 years
        if (Integer.parseInt(AgeDays) <= 3652) {
            ev_63.setBackgroundResource(R.drawable.style_not_done_square_shape);
            ev_63.setEnabled(false);
        }

        //Education:<3 years
        if (Integer.parseInt(AgeDays) <= ProjectSetting.ELIGIBILITY_AGE_EDU_YEAR*365.2) {
            ev_71.setBackgroundResource(R.drawable.style_not_done_square_shape);
            ev_71.setEnabled(false);
        }

        MaterialCardView ev_81 = dialog.findViewById(R.id.ev_81);
        ev_81.setBackgroundResource(R.drawable.style_not_completed_square_shape);

        ev_81.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("TmpMigrationID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", "81");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("vdate", VISIT_DATE);

                Intent intent = new Intent(getApplicationContext(), Surv_TemporaryMigrationOut.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
            ev_81.setBackgroundResource(R.drawable.style_not_done_square_shape);
            ev_81.setEnabled(false);
            ev_40.setBackgroundResource(R.drawable.style_not_done_square_shape);
            ev_40.setEnabled(false);
        }

        //dialog.setView(dialoglayout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


    /*private void DataCleaningForm(String HHID) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.surv_data_cleaning);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        ImageButton btnClose = (ImageButton) dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }*/

//    NBCMenu----------------------------------------------------------------------------------------

    private void tmpDeliveryList(String NBID, String PGN,String HHID,String MemID,String Title){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.surv_delivery_list_pop);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        ImageButton btnClose = (ImageButton) dialog.findViewById(R.id.btnClose);
        TextView lblTitle = (TextView) dialog.findViewById(R.id.lblTitle);
        lblTitle.setText(Title);
        btnClose.setOnClickListener(v -> dialog.dismiss());

        String TotPreg = C.ReturnSingleValue("select TotPreg from tmpPregHis where MemID='"+MemID+"'");
        String[] stringArray = new String[0];
        int totalPregnancies = Integer.parseInt(TotPreg);
        if (TotPreg != null && totalPregnancies > 0) {
            stringArray = new String[totalPregnancies];
            for (int i = 0; i < totalPregnancies; i++) {
                stringArray[i] = String.format("%02d", i + 1);
            }
        }
        for (String value : stringArray) {
            Log.d("Rafi", value);
        }

        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView);
        DeliveryAdapter adapter = new DeliveryAdapter(this, dialog, Arrays.asList(stringArray), NBID, PGN, HHID, MemID, Title, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onClickDelivery(Dialog dialog, String data, String NBID, String PGN, String HHID, String MemID, String Title) {
        NBCMenu("", data, HHID, MemID,"");
        dialog.dismiss();
    }

    private void NBCMenu(String NBID, String PGN, String HHID,String MemID,String Title) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.surv_menu_newborn);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        ImageButton btnClose = (ImageButton) dialog.findViewById(R.id.btnClose);
        TextView lblTitle = (TextView) dialog.findViewById(R.id.lblTitle);
        lblTitle.setText(Title);
        btnClose.setOnClickListener(v -> dialog.dismiss());

        MaterialCardView ANC = dialog.findViewById(R.id.ANC);
        ANC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("NBID", NBID);
                IDbundle.putString("PGN", PGN);
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("round", ROUND);
                IDbundle.putString("title", "");
                IDbundle.putString("vdate", Global.DateNowYMD());

                Intent intent = new Intent(getApplicationContext(), NBC_NBCare.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });



        MaterialCardView ANCDetail = dialog.findViewById(R.id.ANCDetail);
        ANCDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("NBID", "");
                IDbundle.putString("PGN", PGN);
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("round", ROUND);
                IDbundle.putString("isEDIT", "1");
                IDbundle.putString("vdate", Global.DateNowYMD());
                Intent intent = new Intent(getApplicationContext(), NBC_ANCDetail.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        /*MaterialCardView ev_42 = dialog.findViewById(R.id.ev_42);
        ev_42.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (!C.Existence("Select Pstat from tmpMember Where HHID='" + HHID + "' and MemID='" + MemID + "' and Pstat='41'")) {
                    Connection.MessageBox(Surv_Member_list.this, "This member is not eligible for this Event, She is currently not Pregnant");
                    return;
                }
                Bundle IDbundle = new Bundle();
                IDbundle.putString("DeliveryID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", "42");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("edit", "1");

                Intent intent = new Intent(getApplicationContext(), Surv_Delivery.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });*/

        MaterialCardView NBCare = dialog.findViewById(R.id.NBCare);
        NBCare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("NBID", "");
                IDbundle.putString("MemID", MEMID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("round", ROUND);

                Intent intent = new Intent(getApplicationContext(), NBC_NBCare.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView NBCareDetail = dialog.findViewById(R.id.NBCareDetail);
        NBCareDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("NBID", NBID);
                IDbundle.putString("PGN", PGN);
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("round", ROUND);
                IDbundle.putString("vdate", Global.DateNowYMD());
                Intent intent = new Intent(getApplicationContext(), NBC_NBCareDetail.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });

        MaterialCardView PNCMoth = dialog.findViewById(R.id.PNCMoth);
        PNCMoth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                IDbundle.putString("NBID", "");
                IDbundle.putString("MemID", MemID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("round", ROUND);
                IDbundle.putString("vdate", Global.DateNowYMD());

                Intent intent = new Intent(getApplicationContext(), NBC_PNCMoth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });





//        ev_31.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ANC.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ANCDetail.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        NBCare.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        NBCareDetail.setBackgroundResource(R.drawable.style_not_completed_square_shape);
//        ev_52.setBackgroundResource(R.drawable.style_not_completed_square_shape);

        /*ev_61.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_62.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_63.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_64.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_71.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_72.setBackgroundResource(R.drawable.style_not_completed_square_shape);
        ev_55.setBackgroundResource(R.drawable.style_not_completed_square_shape);*/




        //dialog.setView(dialoglayout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void DataSearch(String HHID) {
        try {
            tmpMember_DataModel d = new tmpMember_DataModel("");

            String SQL = "SELECT tm.MemID, tmm.MSlNo, tm.Name, tm.DSSID, tm.Sex, tm.Age, tm.AgeU," +
                    " IFNULL(CAST((julianday('now') - julianday(tm.BDate)) AS INT), 0) AS AgeD," +
                    " IFNULL(CAST((julianday('now') - julianday(tm.BDate)) / 365.25 AS INT), 0) AS AgeY," +
                    " IFNULL(tm.BDate, '') AS BDate," +
                    " IFNULL(tm.BDate_D, '') AS BDate_D,IFNULL(tm.BDate_M, '') AS BDate_M," +
                    " IFNULL(tm.BDate_Y, '') AS BDate_Y,IFNULL(tm.Pstat, '') AS Pstat," +
                    " IFNULL(tm.LmpDt, '') AS LmpDt," +

                    " tmm.Rth, tmm.MS, tmm.EduY, tmm.Ocp,tmm.OcpOth," +
                    " IFNULL(tmm.Sp1, '') AS Sp1," +
                    " IFNULL(tmm.Sp2, '') AS Sp2," +
                    " IFNULL(tmm.Sp3, '') AS Sp3," +
                    " IFNULL(tmm.Sp4, '') AS Sp4," +
                    " tmm.FaNo, tmm.MoNo," +
                    " (CASE WHEN tmm.MoNo = '00' THEN '' ELSE IFNULL(mo.Name, '') END) AS MName," +
                    " (CASE WHEN tmm.FaNo = '00' THEN '' ELSE IFNULL(fa.Name, '') END) AS FName," +
                    " IFNULL(tmm.Active, '') AS Active," +
                    " IFNULL(ph.ObsVStatus, '') AS phis,IFNULL(cc.MemID, '') AS cchar, IFNULL(anthro.MemID, '') AS anthro," +
                    " IFNULL(cg.MemID, '') AS care,IFNULL(va.MemID, '') AS vacc," +
                    " IFNULL(tmm.isdelete, '2') AS isdelete," +
                    " (Case when length(ifnull(tmm.MExType,''))=0 then 1 else 2 end)active_member," +

                    " IFNULL(tmm.MEnType, '') AS MEnType," +
                    " IFNULL(tmm.MEnDate, '') AS MEnDate," +
                    " IFNULL(tmm.MExType, '') AS MExType," +
                    " IFNULL(tmm.MExDate, '') AS MExDate" +

                    " FROM tmpMemberMove tmm " +
                    " INNER JOIN tmpMember tm ON tmm.MemID = tm.MemID " +
                    " LEFT JOIN tmpMember mo ON tmm.HHID = mo.HHID AND tmm.MoNo = mo.MSlNo " +
                    " LEFT JOIN tmpMember fa ON tmm.HHID = fa.HHID AND tmm.FaNo = fa.MSlNo " +
                    " LEFT JOIN tmpPregHis ph ON tmm.MemID = ph.MemID " +
                    " LEFT JOIN ChildChar cc ON tmm.MemID = cc.MemID " +
                    " LEFT JOIN Anthropometric anthro ON tmm.MemID = anthro.MemID"+
                    " LEFT JOIN Caregiver cg ON tmm.MemID = cg.MemID " +
                    " left outer join VaccinationListMaster va ON tmm.MemID = va.MemID" +
                    " WHERE tmm.HHID = '" + HHID + "'" +
                    " and tm.isDelete = '2'" +
                    " ORDER BY cast(tmm.MSlNo as int)";
            List<tmpMember_DataModel> data = d.SelectMember(this, SQL);
            dataList.clear();

            dataList.addAll(data);
            if (dataList != null && !dataList.isEmpty()) {
                recyclerView.setVisibility(View.VISIBLE);
                ll_no_data.setVisibility(View.GONE);
            } else {
                recyclerView.setVisibility(View.GONE);
                ll_no_data.setVisibility(View.VISIBLE);
            }
            int total_active_member = 0;
            try {
                mAdapter.notifyDataSetChanged();
                for (tmpMember_DataModel dmodel: dataList) {
                    if(dmodel.getactive_member().equals("1")) total_active_member +=1;
                }
                lblTotal.setText(" (Total: " + total_active_member + ")");
            } catch (Exception ex) {
                Connection.MessageBox(Surv_Member_list.this, ex.getMessage());
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_Member_list.this, e.getMessage());
            return;
        }
    }

    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
        private List<tmpMember_DataModel> dataList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout secListRow;
            CardView card_view;
            TextView MemID;
            TextView HHID;
            TextView MSlNo;
            TextView Name;
            TextView FaNo;
            TextView MoNo;
            TextView DSSID;
            TextView EduY;
            TextView MS;
            TextView Rth;
            TextView RthOth;
            TextView Sex;
            TextView Ocp;
            TextView BDate;
            TextView Pstat;
            TextView LmpDt;
            TextView Sp1;
            TextView Sp2;
            TextView Age;
            Button btnObsMat;
            Button btnBirth;
            Button btnChild;

            Button btnAnthro;
            Button btnVacci;
            Button btnCaregiver;
            ImageView pregnantImg;

            public MyViewHolder(View convertView) {
                super(convertView);
                secListRow = (LinearLayout) convertView.findViewById(R.id.secListRow);
                card_view = convertView.findViewById(R.id.card_view);
                MemID = (TextView) convertView.findViewById(R.id.MemID);
                MSlNo = (TextView) convertView.findViewById(R.id.MSlNo);
                Name = (TextView) convertView.findViewById(R.id.Name);
                pregnantImg = (ImageView) convertView.findViewById(R.id.pregnantImg);
                FaNo = (TextView) convertView.findViewById(R.id.FaNo);
                MoNo = (TextView) convertView.findViewById(R.id.MoNo);
                DSSID = (TextView) convertView.findViewById(R.id.DSSID);
                Sex = (TextView) convertView.findViewById(R.id.Sex);
                Rth = (TextView) convertView.findViewById(R.id.Rth);
                EduY = (TextView) convertView.findViewById(R.id.EduY);
                MS = (TextView) convertView.findViewById(R.id.MS);
                BDate = (TextView) convertView.findViewById(R.id.BDate);
                Pstat = (TextView) convertView.findViewById(R.id.Pstat);
                LmpDt = (TextView) convertView.findViewById(R.id.LmpDt);
                Sp1 = (TextView) convertView.findViewById(R.id.Sp1);
                Age = (TextView) convertView.findViewById(R.id.Age);

                btnObsMat = (Button)convertView.findViewById(R.id.btnObsMat);
                btnBirth = (Button)convertView.findViewById(R.id.btnBirth);
                btnChild = (Button)convertView.findViewById(R.id.btnChild);
                btnAnthro = (Button)convertView.findViewById(R.id.btnAnthro);
                btnVacci = (Button)convertView.findViewById(R.id.btnVacci);
                btnCaregiver = (Button)convertView.findViewById(R.id.btnCaregiver);
            }
        }

        public DataAdapter(List<tmpMember_DataModel> datalist) {
            this.dataList = datalist;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.surv_member_row, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final tmpMember_DataModel data = dataList.get(position);
            holder.MSlNo.setText(data.getMSlNo());
            holder.Name.setText(": " + data.getName());
            holder.FaNo.setText(": " + data.getFName());
            holder.MoNo.setText(": " + data.getMName());
            if(!data.getage().equals("")){
                holder.Age.setText(": "+ data.getage()+" "+(data.getageu().equals("3") ? "Year(s)" : data.getageu().equals("2") ? "Month(s)" : data.getageu().equals("1") ? "Day(s)" : "" ));
            }
            else{
                holder.Age.setText(": "+ data.getAgeY()+" Years");
            }

            holder.DSSID.setText("DSS ID: " + data.getDSSID());

            if (data.getSex().equals("1")) holder.Sex.setText(": M");
            else if (data.getSex().equals("2")) holder.Sex.setText(": F");
            else if (data.getSex().equals("3")) holder.Sex.setText(": I");
            else if (data.getSex().equals("4")) holder.Sex.setText(": T");
            else if (data.getSex().equals("5")) holder.Sex.setText(": U");

            holder.EduY.setText(": " + data.getEduY());
            holder.MS.setText(": " + data.getMS());
            holder.Rth.setText(": " + data.getRth());

            if(data.getBDate_D().length()>0)
                holder.BDate.setText(": "+ Global.Right("00"+data.getBDate_D(),2)+"/"+data.getBDate_M()+"/"+data.getBDate_Y());
            else
                holder.BDate.setText(": ");

            holder.LmpDt.setText(": " + Global.DateConvertDMY(data.getLmpDt()));
            holder.Sp1.setText(": " + data.getSp1());



            if(data.getSex().equals("1")){
                holder.Pstat.setText(": N/A");
                holder.LmpDt.setText(": N/A");
                holder.pregnantImg.setVisibility(View.GONE);
            }else {
                if (data.getPstat().equals("40")) {
                    holder.Pstat.setText(": N");
                    holder.pregnantImg.setVisibility(View.GONE);
                } else if (data.getPstat().equals("41")) {
                    holder.Pstat.setText(": Y");
                    holder.pregnantImg.setVisibility(View.VISIBLE);
                } else if (data.getPstat().equals("49")){
                    holder.Pstat.setText(": DK");
                    holder.pregnantImg.setVisibility(View.GONE);
                }else{
                    holder.Pstat.setText(":");
                    holder.pregnantImg.setVisibility(View.GONE);
                }

                if (data.getLmpDt() != null && !data.getLmpDt().isEmpty()) {
                    holder.LmpDt.setText(": " + Global.DateConvertDMY(data.getLmpDt()));
                }
            }

            holder.btnObsMat.setBackgroundResource(R.drawable.button_style_circle_line_disable);
            holder.btnBirth.setBackgroundResource(R.drawable.button_style_circle_line_disable);
            holder.btnChild.setBackgroundResource(R.drawable.button_style_circle_line_disable);
            holder.btnAnthro.setBackgroundResource(R.drawable.button_style_circle_line_disable);
            holder.btnVacci.setBackgroundResource(R.drawable.button_style_circle_line_disable);
            holder.btnCaregiver.setBackgroundResource(R.drawable.button_style_circle_line_disable);

            holder.btnObsMat.setTextColor(getResources().getColor(R.color.color_light_gray));
            holder.btnObsMat.setEnabled(false);
            holder.btnBirth.setTextColor(getResources().getColor(R.color.color_light_gray));
            holder.btnBirth.setEnabled(false);
            holder.btnChild.setTextColor(getResources().getColor(R.color.color_light_gray));
            holder.btnChild.setEnabled(false);
            holder.btnAnthro.setTextColor(getResources().getColor(R.color.color_light_gray));
            holder.btnAnthro.setEnabled(false);
            if(!ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2)) {
                holder.btnAnthro.setVisibility(View.GONE);
            }
            holder.btnVacci.setTextColor(getResources().getColor(R.color.color_light_gray));
            holder.btnVacci.setEnabled(false);
            holder.btnCaregiver.setTextColor(getResources().getColor(R.color.color_light_gray));
            holder.btnCaregiver.setEnabled(false);

            //--------------------------------------------------------------------------------------
            int age_days = Integer.parseInt(data.getaged());
            int age_years = Integer.parseInt(data.getAgeY());
            //--------------------------------------------------------------------------------------

            //inactive member
            if (data.getactive_member().equals("2")){
                holder.btnObsMat.setEnabled(false);
                holder.btnChild.setEnabled(false);
                holder.btnAnthro.setEnabled(false);
                holder.btnVacci.setEnabled(false);
                holder.btnCaregiver.setEnabled(false);
                holder.btnBirth.setEnabled(false);
            }
            //temporary in-migration
            else if (data.getMEnType().equals("80")){
                holder.btnObsMat.setEnabled(false);
                holder.btnChild.setEnabled(false);
                holder.btnAnthro.setEnabled(false);
                holder.btnVacci.setEnabled(false);
                holder.btnCaregiver.setEnabled(false);
                holder.btnBirth.setEnabled(false);
            } else {
                //for Mali
                if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                    //Female, and reproductive age
                    if(data.getSex().equals("2") && (age_years>= ProjectSetting.REPRODUCTIVE_AGE_START && age_years<=ProjectSetting.REPRODUCTIVE_AGE_END)){
                        if(ProjectSetting.PREGNANCY_HISTORY_MODULE) {
                            holder.btnObsMat.setTextColor(getResources().getColor(R.color.textColor));
                            holder.btnObsMat.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                            holder.btnObsMat.setEnabled(true);
                            tmpPregHis_DataModel objSave = new tmpPregHis_DataModel();
                            if (data.getphis().length() > 0) {
                                if (data.getphis().equals("1")) {
                                    holder.btnObsMat.setBackgroundResource(R.drawable.style_completed_square_shape);
                                    holder.btnObsMat.setTextColor(Color.WHITE);
                                } else {
                                    holder.btnObsMat.setBackgroundResource(R.drawable.style_completed_square_shape_blue);
                                    holder.btnObsMat.setTextColor(Color.BLACK);
                                }
                            }
                        }
                    }
                    else{
                        holder.btnObsMat.setEnabled(false);
                    }
                }
                //for Sierra Leone
                else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1)){
                    //Female, and reproductive age
                    if(data.getSex().equals("2") && (age_years>= ProjectSetting.REPRODUCTIVE_AGE_START && age_years<=ProjectSetting.REPRODUCTIVE_AGE_END)){
                        if(ProjectSetting.PREGNANCY_HISTORY_MODULE) {
                            holder.btnObsMat.setTextColor(getResources().getColor(R.color.textColor));
                            holder.btnObsMat.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                            holder.btnObsMat.setEnabled(true);
                            tmpPregHis_DataModel objSave = new tmpPregHis_DataModel();
                            if (data.getphis().length() > 0) {
                                if (data.getphis().length() > 0) {
                                    if (data.getphis().equals("1")) {
                                        holder.btnObsMat.setBackgroundResource(R.drawable.style_completed_square_shape);
                                        holder.btnObsMat.setTextColor(Color.WHITE);
                                    } else {
                                        holder.btnObsMat.setBackgroundResource(R.drawable.style_completed_square_shape_blue);
                                        holder.btnObsMat.setTextColor(Color.BLACK);
                                    }
                                }
                            }
                        }
                    }
                    else{
                        holder.btnObsMat.setEnabled(false);
                    }
                }
                //for Crossriver
                else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
                    //Female, and reproductive age
                    if(data.getSex().equals("2") && (age_years>= ProjectSetting.REPRODUCTIVE_AGE_START && age_years<=ProjectSetting.REPRODUCTIVE_AGE_END)){
                        if(ProjectSetting.PREGNANCY_HISTORY_MODULE) {
                            holder.btnObsMat.setTextColor(getResources().getColor(R.color.textColor));
                            holder.btnObsMat.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                            holder.btnObsMat.setEnabled(true);
                            tmpPregHis_DataModel objSave = new tmpPregHis_DataModel();
                            if (data.getphis().length() > 0) {
                                if (data.getphis().length() > 0) {
                                    if (data.getphis().equals("1")) {
                                        holder.btnObsMat.setBackgroundResource(R.drawable.style_completed_square_shape);
                                        holder.btnObsMat.setTextColor(Color.WHITE);
                                    } else {
                                        holder.btnObsMat.setBackgroundResource(R.drawable.style_completed_square_shape_blue);
                                        holder.btnObsMat.setTextColor(Color.BLACK);
                                    }
                                }
                            }
                        }
                    }
                    else{
                        holder.btnObsMat.setEnabled(false);
                    }
                }
                //For other sites
                else{
                    //Ever married, Female, and age between 14 and 49(Country specific)
                    if(!data.getMS().equals("00") && data.getSex().equals("2") && (age_years>= ProjectSetting.REPRODUCTIVE_AGE_START && age_years<=ProjectSetting.REPRODUCTIVE_AGE_END)){
                        if(ProjectSetting.PREGNANCY_HISTORY_MODULE) {
                            holder.btnObsMat.setTextColor(getResources().getColor(R.color.textColor));
                            holder.btnObsMat.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                            holder.btnObsMat.setEnabled(true);
                            tmpPregHis_DataModel objSave = new tmpPregHis_DataModel();
                            if (data.getphis().length() > 0) {
                                if (data.getphis().length() > 0) {
                                    if (data.getphis().equals("1")) {
                                        holder.btnObsMat.setBackgroundResource(R.drawable.style_completed_square_shape);
                                        holder.btnObsMat.setTextColor(Color.WHITE);
                                    } else {
                                        holder.btnObsMat.setBackgroundResource(R.drawable.style_completed_square_shape_blue);
                                        holder.btnObsMat.setTextColor(Color.BLACK);
                                    }
                                }
                            }
                        }
                    }
                    else{
                        holder.btnObsMat.setEnabled(false);
                    }
                }


                //NEW BIRTHS/NEWBORN CHARACTERISTICS -----------------------------------------------
                //for Mali
                NBC_NBCare_DataModel d = new NBC_NBCare_DataModel();
                String SQL = "select c1.*,c2.PregType from NBC_NBCare c1\n" +
                        " left outer join NBC_NBCareDetail c2 on c1.MemID=c2.MEMID  and c1.HHID=c2.HHID and c1.PGN=c2.PGN \n" +
                        " where c1.MemID='"+ data.getMemID() +"' and c1.HHID='"+ HHID +"' and c1.isdelete=2";
                List<NBC_NBCare_DataModel> careData = d.SelectAllwithPregType(Surv_Member_list.this, SQL);

                if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                    if(data.getSex().equals("2") && (age_years>= ProjectSetting.REPRODUCTIVE_AGE_START && age_years<=ProjectSetting.REPRODUCTIVE_AGE_END))
                    {
                        if(ProjectSetting.NEW_BIRTH_MODULE) {
                            if (careData.size() > 0){
                                holder.btnBirth.setTextColor(getResources().getColor(R.color.textColor));
                                holder.btnBirth.setBackgroundResource(R.drawable.style_completed_square_shape);
                                holder.btnBirth.setVisibility(View.VISIBLE);
                                holder.btnBirth.setTextColor(Color.WHITE);
                            } else {
                                holder.btnBirth.setTextColor(getResources().getColor(R.color.textColor));
                                holder.btnBirth.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                                holder.btnBirth.setVisibility(View.VISIBLE);
                                holder.btnBirth.setTextColor(Color.BLACK);
                            }
                        }else{
                            holder.btnBirth.setVisibility(View.GONE);
                        }

                    }else{
                        holder.btnBirth.setVisibility(View.GONE);
                    }
                }
                //for other sites
                else{
                    if(data.getSex().equals("2") && (age_years>= ProjectSetting.REPRODUCTIVE_AGE_START && age_years<=ProjectSetting.REPRODUCTIVE_AGE_END))
                    {
                        if(ProjectSetting.NEW_BIRTH_MODULE) {
                            if (careData.size() > 0){
                                holder.btnBirth.setTextColor(getResources().getColor(R.color.textColor));
                                holder.btnBirth.setBackgroundResource(R.drawable.style_completed_square_shape);
                                holder.btnBirth.setEnabled(true);
                                holder.btnBirth.setTextColor(Color.WHITE);
                            } else {
                                holder.btnBirth.setTextColor(getResources().getColor(R.color.textColor));
                                holder.btnBirth.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                                holder.btnBirth.setEnabled(true);
                                holder.btnBirth.setTextColor(Color.BLACK);
                            }

                        }

                    }else{
                        holder.btnBirth.setEnabled(false);
                    }
                }


                //Under 5 Age group
                if((age_days<=1826)){
                    if(ProjectSetting.CHILD_CHARACTERISTICS_MODULE) {
                        holder.btnChild.setTextColor(getResources().getColor(R.color.textColor));
                        holder.btnChild.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                        holder.btnChild.setEnabled(true);

                        if(data.getcchar().length()>0){
                            holder.btnChild.setBackgroundResource(R.drawable.style_completed_square_shape);
                            holder.btnChild.setTextColor(Color.BLACK);
                            holder.btnChild.setTextColor(Color.WHITE);
                        }
                    }

                    if(ProjectSetting.ANTHROPOMETRIC_MODULE) {
                        holder.btnAnthro.setTextColor(getResources().getColor(R.color.textColor));
                        holder.btnAnthro.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                        holder.btnAnthro.setEnabled(true);

                        if(data.getanthro().length()>0){
                            holder.btnAnthro.setBackgroundResource(R.drawable.style_completed_square_shape);
                            holder.btnAnthro.setTextColor(Color.BLACK);
                            holder.btnAnthro.setTextColor(Color.WHITE);
                        }
                    }

                    if(ProjectSetting.VACCINATION_MODULE) {
                        holder.btnVacci.setTextColor(getResources().getColor(R.color.textColor));
                        holder.btnVacci.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                        holder.btnVacci.setEnabled(true);

                        if (data.getvacc().length() > 0) {
                            holder.btnVacci.setBackgroundResource(R.drawable.style_completed_square_shape);
                            holder.btnVacci.setTextColor(Color.WHITE);
                        }
                    }
                }else{
                    holder.btnChild.setEnabled(false);
                    holder.btnAnthro.setEnabled(false);
                    holder.btnVacci.setEnabled(false);
                }

                //Age group 1 to 5 years
                if((age_days>=365 && age_days<=1826)){
                    if(ProjectSetting.CAREGIVER_MODULE) {
                        holder.btnCaregiver.setTextColor(getResources().getColor(R.color.textColor));
                        holder.btnCaregiver.setBackgroundResource(R.drawable.style_not_completed_square_shape);
                        holder.btnCaregiver.setEnabled(true);
                        if (data.getcare().length() > 0) {
                            holder.btnCaregiver.setBackgroundResource(R.drawable.style_completed_square_shape);
                            holder.btnCaregiver.setTextColor(Color.WHITE);
                        }
                    }
                }else{
                    holder.btnCaregiver.setEnabled(false);
                }
            }

            if (data.getactive_member().equalsIgnoreCase("1") && !data.getMEnType().equals("80")) {
                holder.Name.setTextColor(getResources().getColor(R.color.blue));
                holder.MSlNo.setTextColor(getResources().getColor(R.color.blue));
                holder.card_view.setBackgroundResource(R.drawable.style_circle_line_white);
            }
            else if (data.getMEnType().equals("80") || data.getMExType().equals("81")) {
                holder.Name.setTextColor(getResources().getColor(R.color.blue));
                holder.MSlNo.setTextColor(getResources().getColor(R.color.blue));
                holder.card_view.setBackgroundResource(R.drawable.style_circle_line_blue);
            }
            else {
                /*holder.Name.setTextColor(Color.RED);
                holder.MSlNo.setTextColor(Color.RED);
                holder.DSSID.setTextColor(Color.RED);*/
                holder.btnObsMat.setEnabled(false);
                holder.btnBirth.setEnabled(false);
                holder.btnChild.setEnabled(false);
                holder.btnAnthro.setEnabled(false);
                holder.btnVacci.setEnabled(false);
                holder.btnCaregiver.setEnabled(false);
                holder.card_view.setBackgroundResource(R.drawable.style_circle_line_incomplete_member);
            }

            //only for Mali
            if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                holder.btnBirth.setVisibility(View.GONE);
                holder.btnChild.setVisibility(View.GONE);
                holder.btnAnthro.setVisibility(View.GONE);
                holder.btnVacci.setVisibility(View.GONE);
                holder.btnCaregiver.setVisibility(View.GONE);
            }
            holder.secListRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.d("champs log:",data.getactive_member()+","+data.getExType());
                    //if died
                    //------------------------------------------------------------------------------
                    if (data.getactive_member().equalsIgnoreCase("2") && data.getMExType().equals("55")) {
                        Connection.MessageBox(Surv_Member_list.this, "Member is not active in this Household");
                        return;
                    }

                    //if migration out then options to reactivate
                    //------------------------------------------------------------------------------
                    else if (data.getactive_member().equalsIgnoreCase("2")
                            && (data.getMExType().equals("51"))) {
                        //execute event 21
                        AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Member_list.this);
                        adb.setTitle("Member Reactivate");
                        adb.setIcon(R.drawable.favicon);
                        adb.setMessage("Do you want to reactivate this member [Reactivate/No]?");
                        adb.setNegativeButton("No", null);
                        adb.setPositiveButton("Reactivate", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("MigrationID", "");
                                IDbundle.putString("MemID", data.getMemID());
                                IDbundle.putString("HHID", HHID);
                                IDbundle.putString("HHNO", HHNO);
                                IDbundle.putString("evtype", "21");
                                IDbundle.putString("round", ROUND);
                                IDbundle.putString("vdate", VISIT_DATE);
                                Intent intent = new Intent(getApplicationContext(), Surv_Migration.class);
                                intent.putExtras(IDbundle);
                                startActivityForResult(intent, 1);
                            }

                        });
                        adb.show();

                    }

                    //options to confirm temporary migration-in
                    //------------------------------------------------------------------------------
                    else if (data.getactive_member().equalsIgnoreCase("1")
                            && (data.getMEnType().equals("80"))) {
                        //execute event 21
                        AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Member_list.this);
                        adb.setTitle("Confirm temporary migration-in");
                        adb.setIcon(R.drawable.favicon);
                        adb.setMessage("Do you want to confirm the temporary migration-in [Confirm/No]?");
                        adb.setNegativeButton("No", null);
                        adb.setPositiveButton("Confirm", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("MigrationID", "");
                                IDbundle.putString("MemID", data.getMemID());
                                IDbundle.putString("HHID", HHID);
                                IDbundle.putString("HHNO", HHNO);
                                IDbundle.putString("evtype", "21");
                                IDbundle.putString("round", ROUND);
                                IDbundle.putString("vdate", VISIT_DATE); //dd/mm/yyyy
                                Intent intent = new Intent(getApplicationContext(), Surv_Migration.class);
                                intent.putExtras(IDbundle);
                                startActivityForResult(intent, 1);
                            }

                        });
                        adb.show();
                    }

                    //options to confirm temporary migration-out
                    //------------------------------------------------------------------------------
                    else if (data.getactive_member().equalsIgnoreCase("2")
                            && (data.getMExType().equals("81"))) {
                        //execute event 51
                        AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Member_list.this);
                        adb.setTitle("Confirm temporary migration-out");
                        adb.setIcon(R.drawable.favicon);
                        adb.setMessage("Do you want to confirm the temporary migration-out [Confirm/No]?");
                        adb.setNegativeButton("No", null);
                        adb.setPositiveButton("Confirm", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("MigrationID", "");
                                IDbundle.putString("MemID", data.getMemID());
                                IDbundle.putString("HHID", HHID);
                                IDbundle.putString("evtype", "51");
                                IDbundle.putString("round", ROUND);
                                IDbundle.putString("vdate", VISIT_DATE); //dd/mm/yyyy
                                Intent intent = new Intent(getApplicationContext(), Surv_Migration.class);
                                intent.putExtras(IDbundle);
                                startActivityForResult(intent, 1);
                            }
                        });
                        adb.show();

                    }

                    //member events
                    //------------------------------------------------------------------------------
                    else if (data.getactive_member().equalsIgnoreCase("2")) {
                        Connection.MessageBox(Surv_Member_list.this, "Member is not active in this Household");
                        return;
                    }
                    //Member Events form
                    //------------------------------------------------------------------------------
                    else {
                        MemberEvent(data.getMemID(), data.getName(), data.getaged(), data.getSex(), data.getMS(), data.getPstat(), VISIT_DATE);
                    }
                }
            });

            holder.btnBirth.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    //NBCMenu("NBID", "PGN", HHID,data.getMemID(),"");

                    final ProgressDialog progDailog = ProgressDialog.show(Surv_Member_list.this, "", "Please Wait . . .", true);
                    new Thread() {
                        public void run() {
                            try {
                               Bundle IDbundle = new Bundle();
                                IDbundle.putString("NBID", "");
                                IDbundle.putString("PGN", "");
                                IDbundle.putString("MemID", data.getMemID());
                                IDbundle.putString("MEMNAME", data.getName());
                                IDbundle.putString("MSlNo", data.getMSlNo());
                                IDbundle.putString("HHID", HHID);
                                IDbundle.putString("round", ROUND);

                                Intent intent = new Intent(Surv_Member_list.this, NBC_NBCare_list.class);
                                intent.putExtras(IDbundle);
                                startActivityForResult(intent, 1);
                            } catch (Exception e) {
                            }
                            progDailog.dismiss();
                        }
                    }.start();
                }
            });

            holder.btnChild.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(Surv_Member_list.this, "", "Please Wait . . .", true);
                    new Thread() {
                        public void run() {
                            try {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("MemID", data.getMemID());
                                IDbundle.putString("HHID", HHID);
                                IDbundle.putString("MemName", data.getName());
                                Intent intent = new Intent(getApplicationContext(), Surv_ChildChar.class);
                                intent.putExtras(IDbundle);
                                startActivityForResult(intent, 1);
                            } catch (Exception e) {
                            }
                            progDailog.dismiss();
                        }
                    }.start();
                }
            });

            holder.btnAnthro.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(Surv_Member_list.this, "", "Please Wait . . .", true);
                    new Thread() {
                        public void run() {
                            try {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("MemID", data.getMemID());
                                IDbundle.putString("HHID", HHID);
                                IDbundle.putString("MemName", data.getName());
                                Intent intent = new Intent(getApplicationContext(), Surv_Anthropometric.class);
                                intent.putExtras(IDbundle);
                                startActivityForResult(intent, 1);
                            } catch (Exception e) {
                            }
                            progDailog.dismiss();
                        }
                    }.start();
                }
            });

            holder.btnObsMat.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(Surv_Member_list.this, "", "Please Wait . . .", true);
                    new Thread() {
                        public void run() {
                            try {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("ObsMatID", "");
                                IDbundle.putString("MemID", data.getMemID());
                                IDbundle.putString("ms", data.getMS());
                                IDbundle.putString("HHID", HHID);
                                IDbundle.putString("MSlNo", data.getMSlNo());
                                IDbundle.putString("evtype", "");
                                IDbundle.putString("round", ROUND);
                                IDbundle.putString("SurvType", "1");
                                IDbundle.putString("MemName", data.getName());
                                IDbundle.putString("visitdate", VISIT_DATE);
                                Intent intent = new Intent(getApplicationContext(), Surv_PregHis.class);
                                intent.putExtras(IDbundle);
                                startActivityForResult(intent, 1);
                            } catch (Exception e) {
                            }
                            progDailog.dismiss();
                        }
                    }.start();
                }
            });

            holder.btnCaregiver.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(Surv_Member_list.this, "", "Please Wait . . .", true);
                    new Thread() {
                        public void run() {
                            try {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("MemID", data.getMemID());
                                IDbundle.putString("HHID", HHID);
                                IDbundle.putString("MemName", data.getName());
                                Intent intent = new Intent(getApplicationContext(), Surv_Caregiver.class);
                                intent.putExtras(IDbundle);
                                startActivityForResult(intent, 1);
                            } catch (Exception e) {
                            }
                            progDailog.dismiss();
                        }
                    }.start();
                }
            });

            holder.btnVacci.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progDailog = ProgressDialog.show(Surv_Member_list.this, "", "Please Wait . . .", true);
                    new Thread() {
                        public void run() {
                            try {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("VaccID", "");
                                IDbundle.putString("MemID", data.getMemID());
                                IDbundle.putString("HHID", HHID);
                                IDbundle.putString("MemName", data.getName());
                                IDbundle.putString("visitdate", VISIT_DATE);
                                IDbundle.putString("bdate", data.getBDate()); //yyyy-mm-dd
                                Intent intent = new Intent(getApplicationContext(), Vaccination_list.class);
                                intent.putExtras(IDbundle);
                                startActivityForResult(intent, 1);
                            } catch (Exception e) {
                            }
                            progDailog.dismiss();
                        }
                    }.start();
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }


    protected Dialog onCreateDialog(int id) {
        final Calendar c = Calendar.getInstance();
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mDateSetListener, g.mYear, g.mMonth - 1, g.mDay);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear + 1;
            mDay = dayOfMonth;
            EditText dtpDate;
            dtpDate = (EditText) findViewById(R.id.dtpFDate);
            if (VariableID.equals("dtpFDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpFDate);
            } else if (VariableID.equals("dtpTDate")) {
                dtpDate = (EditText) findViewById(R.id.dtpTDate);
            }
            dtpDate.setText(new StringBuilder()
                    .append(Global.Right("00" + mDay, 2)).append("/")
                    .append(Global.Right("00" + mMonth, 2)).append("/")
                    .append(mYear));
        }
    };


    //Data delete Function
    private void tpmDataDelete() {
        C.SaveData("Delete from tmpHousehold where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpVisits where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpMember where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpSES where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpPregHis where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpDeath where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpDelivery where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpEducation where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpFatherSerial where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpLiveBirth where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpMaritalSts where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpMemberMove where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpMigration where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpMotherSerial where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpMovement where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpNotPregnant where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpOccupation where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpPregDetail where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpPregnancy where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpRelation where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpSpouseSerial where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpStillBirth where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpAbortion where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpTemporaryMigrationIn where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpTemporaryMigrationOut where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpSES_Mali where HHID='" + HHID + "'");
        C.SaveData("Delete from tmpSES_CrossRiver where HHID='" + HHID + "'");
    }

    //Data Transfer to Main Table
    //************************************************
    //Table Name: Household
    private void HouseholdDataTransfer(String HHID) {
        tmpHousehold_DataModel d = new tmpHousehold_DataModel();
        String SQL = "Select * from tmpHousehold  Where HHID='" + HHID + "'";
        List<tmpHousehold_DataModel> data = d.SelectAll(this, SQL);
        for (tmpHousehold_DataModel item : data) {
            Household_DataModel objSave = new Household_DataModel();
            objSave.setHHID(HHID);
            objSave.setHHNO(HHNO);
            objSave.setVillID(item.getVillID());
            objSave.setCompoundID(item.getCompoundID());
            objSave.setHHHead(item.getHHHead());
            objSave.setMobileNo1(item.getMobileNo1());
            objSave.setMobileNo2(item.getMobileNo2());
            objSave.setTotMem(item.getTotMem());
            objSave.setHHEnType(item.getHHEnType());
            objSave.setHHEnDate(item.getHHEnDate());
            objSave.setHHExType(item.getHHExType());
            objSave.setHHExDate(item.getHHExDate());
            objSave.setRnd(item.getRnd());
            objSave.setActive(item.getActive());
            objSave.setHHNote(item.getHHNote());
            objSave.setStartTime(item.getStartTime());
            objSave.setEndTime(item.getEndTime());
            objSave.setDeviceID(item.getDeviceID());
            objSave.setEntryUser(item.getEntryUser());
            objSave.setLat(MySharedPreferences.getValue(Surv_Member_list.this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(Surv_Member_list.this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: Visits
    private void VisitsDataTransfer(String HHID, String VisitNo, String ROUND) {
        tmpVisits_DataModel d = new tmpVisits_DataModel();
        String SQL = "Select * from tmpVisits  Where HHID='" + HHID + "' and VisitNo='" + VisitNo + "' and Rnd='" + ROUND + "'";
        List<tmpVisits_DataModel> data = d.SelectAll(this, SQL);
        for (tmpVisits_DataModel item : data) {
            Visits_DataModel objSave = new Visits_DataModel();
            objSave.setHHID(HHID);
            objSave.setVisitNo(VisitNo);
            objSave.setVisitDate(item.getVisitDate());
            //objSave.setVisitStatus(item.getVisitStatus());
            if(VISIT_STATUS.equals("01") || VISIT_STATUS.equals("03")) {
                objSave.setVisitStatus("02");
            }else{
                objSave.setVisitStatus(item.getVisitStatus());
            }
            objSave.setVisitStatusOth(item.getVisitStatusOth());
            objSave.setRespID(item.getRespID());
            objSave.setHaveDeath(item.getHaveDeath()); //uchchash
            objSave.setTotalDeath(item.getTotalDeath());//uchchash
            objSave.setRnd(item.getRnd());
            objSave.setVisitNote(item.getVisitNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
//            objSave.setLat(MySharedPreferences.getValue(Surv_Member_list.this, "lat"));
//            objSave.setLon(MySharedPreferences.getValue(Surv_Member_list.this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: Member
    private void MemberDataTransfer(String HHID) {
        tmpMember_DataModel d = new tmpMember_DataModel("");
        String SQL = "Select * from tmpMember  Where HHID='" + HHID + "'";
        List<tmpMember_DataModel> data = d.SelectAll(this, SQL);
        for (tmpMember_DataModel item : data) {
            Member_DataModel objSave = new Member_DataModel();
            objSave.setMemID(item.getMemID());
            objSave.setHHID(item.getHHID());
            objSave.setDSSID(item.getDSSID());
            objSave.setMSlNo(item.getMSlNo());
            objSave.setRth(item.getRth());
            objSave.setRthOth(item.getRthOth());
            objSave.setName(item.getName());
            objSave.setSex(item.getSex());
            objSave.setBDate_D(item.getBDate_D());
            objSave.setBDate_M(item.getBDate_M());
            objSave.setBDate_Y(item.getBDate_Y());
            objSave.setBDate(item.getBDate());
            objSave.setBDateType(item.getBDateType());
            objSave.setAge(item.getAge());
            objSave.setAgeU(item.getAgeU());
            objSave.setMoNo(item.getMoNo());
            objSave.setMoName(item.getMoName());
            objSave.setFaNo(item.getFaNo());
            objSave.setFaName(item.getFaName());
            objSave.setEduY(item.getEduY());
            objSave.setEmploy(item.getEmploy());
            objSave.setEmployOth(item.getEmployOth()); //uchchash
            objSave.setOcp(item.getOcp());
            objSave.setOcpDk(item.getOcpDk());
            objSave.setOcpOth(item.getOcpOth());
            objSave.setReligion(item.getReligion());
            objSave.setReligionOth(item.getReligionOth());
            objSave.setEthnicity(item.getEthnicity());
            objSave.setEthnicityOth(item.getEthnicityOth());
            objSave.setMobileNo(item.getMobileNo());
            objSave.setMS(item.getMS());
            objSave.setMSOth(item.getMSOth());
            objSave.setSp1(item.getSp1());
            objSave.setSp1Name(item.getSp1Name());
            objSave.setSp2(item.getSp2());
            objSave.setSp2Name(item.getSp2Name());
            objSave.setSp3(item.getSp3());
            objSave.setSp3Name(item.getSp3Name());
            objSave.setSp4(item.getSp4());
            objSave.setSp4Name(item.getSp4Name());
            objSave.setPstat(item.getPstat());
            objSave.setLmpDt(item.getLmpDt());
            objSave.setgage(item.getgage());
            objSave.setgageUnit(item.getgageUnit());
            objSave.setanc_regis(item.getanc_regis());
            objSave.setanc_resp_home((item.getanc_resp_home()));
            objSave.setanc_other_home((item.getanc_other_home()));
            objSave.setanc_govt_hosp((item.getanc_govt_hosp()));
            objSave.setanc_govt_health((item.getanc_govt_health()));
            objSave.setanc_govt_health_post((item.getanc_govt_health_post()));
            objSave.setanc_priv_hosp((item.getanc_priv_hosp()));
            objSave.setanc_tba_home((item.getanc_tba_home()));
            objSave.setanc_ngo_hosp((item.getanc_ngo_hosp()));
            objSave.setanc_other((item.getanc_other()));
            objSave.setanc_dk((item.getanc_dk()));
            objSave.setanc_refuse((item.getanc_refuse()));
            objSave.setanc_other_specify(item.getanc_other_specify());
            objSave.setout_date(item.getout_date());
            objSave.setRnd(item.getRnd());
            objSave.setActive(item.getActive());
            objSave.setEnType(item.getEnType());
            objSave.setExType(item.getExType());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            String status = objSave.SaveUpdateData(this);

            //for internal movement
            if(item.getExType().equals("52")){
                migMemberDataTransfer(item.getMemID());
            }
        }
    }

    //Table Name: migMember
    private void migMemberDataTransfer(String MemID) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = sdf.format(calendar.getTime());

        tmpMember_DataModel d = new tmpMember_DataModel("");
        String SQL = "Select * from tmpMember  Where MemID='" + MemID + "'";
        List<tmpMember_DataModel> data = d.SelectAll(this, SQL);
        for (tmpMember_DataModel item : data) {
            migMember_DataModel objSave = new migMember_DataModel();
            objSave.setMemID(MEMID);
            objSave.setHHID(HHID);
            objSave.setDSSID(item.getDSSID());
            objSave.setMSlNo(item.getMSlNo());
            objSave.setRth(item.getRth());
            objSave.setRthOth(item.getRthOth());
            objSave.setName(item.getName());
            objSave.setSex(item.getSex());
            objSave.setBDate_D(item.getBDate_D());
            objSave.setBDate_M(item.getBDate_M());
            objSave.setBDate_Y(item.getBDate_Y());
            objSave.setBDate(item.getBDate());
            objSave.setBDateType(item.getBDateType());
            objSave.setAge(item.getAge());
            objSave.setAgeU(item.getAgeU());
            objSave.setMoNo(item.getMoNo());
            objSave.setMoName(item.getMoName());
            objSave.setFaNo(item.getFaNo());
            objSave.setFaName(item.getFaName());
            objSave.setEduY(item.getEduY());
            objSave.setEmploy(item.getEmploy());
            objSave.setEmployOth(item.getEmploy());
            objSave.setOcp(item.getOcp());
            objSave.setOcpOth(item.getOcpOth());
            objSave.setOcpDk(item.getOcpDk());
            objSave.setReligion(item.getReligion());
            objSave.setReligionOth(item.getReligionOth());
            objSave.setEthnicity(item.getEthnicity());
            objSave.setEthnicityOth(item.getEthnicityOth());
            objSave.setMobileNo(item.getMobileNo());
            objSave.setMS(item.getMS());
            objSave.setMSOth(item.getMSOth());
            objSave.setSp1(item.getSp1());
            objSave.setSp1Name(item.getSp1Name());
            objSave.setSp2(item.getSp2());
            objSave.setSp2Name(item.getSp2Name());
            objSave.setSp3(item.getSp3());
            objSave.setSp3Name(item.getSp3Name());
            objSave.setSp4(item.getSp4());
            objSave.setSp4Name(item.getSp4Name());
            objSave.setPstat(item.getPstat());
            objSave.setLmpDt(item.getLmpDt());
            objSave.setgage(item.getgage());
            objSave.setgageUnit(item.getgageUnit());
            objSave.setanc_regis(item.getanc_regis());
            objSave.setanc_resp_home((item.getanc_resp_home()));
            objSave.setanc_other_home((item.getanc_other_home()));
            objSave.setanc_govt_hosp((item.getanc_govt_hosp()));
            objSave.setanc_govt_health((item.getanc_govt_health()));
            objSave.setanc_govt_health_post((item.getanc_govt_health_post()));
            objSave.setanc_priv_hosp((item.getanc_priv_hosp()));
            objSave.setanc_tba_home((item.getanc_tba_home()));
            objSave.setanc_ngo_hosp((item.getanc_ngo_hosp()));
            objSave.setanc_other((item.getanc_other()));
            objSave.setanc_dk((item.getanc_dk()));
            objSave.setanc_refuse((item.getanc_refuse()));
            objSave.setanc_other_specify(item.getanc_other_specify());
            objSave.setout_date(item.getout_date());
            objSave.setRnd(item.getRnd());
            objSave.setActive(item.getActive());
            objSave.setEnType(item.getEnType());
            objSave.setExType(item.getExType());
            objSave.setexDate(formattedDate);
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: SES
    private void SESDataTransfer(String HHID) {
        tmpSES_DataModel d = new tmpSES_DataModel();
        String SQL = "Select * from tmpSES  Where HHID='" + HHID + "'";
        List<tmpSES_DataModel> data = d.SelectAll(this, SQL);
        for (tmpSES_DataModel item : data) {
            SES_DataModel objSave = new SES_DataModel();
            objSave.setHHID(item.getHHID());
            objSave.setSESNo(item.getSESNo());
            objSave.setSESVDate(item.getSESVDate());
            objSave.setSESVStatus(item.getSESVStatus());
            objSave.setSESVStatusOth(item.getSESVStatusOth());
            objSave.setRnd(item.getRnd());
            objSave.setWSDrink(item.getWSDrink());
            objSave.setWSDrinkOth(item.getWSDrinkOth());
            objSave.setToilet(item.getToilet());
            objSave.setToiletOth(item.getToiletOth());
            objSave.setToiletShrd(item.getToiletShrd());
            objSave.setToiletUseNo(item.getToiletUseNo());
            objSave.setToiletUseNoDk(item.getToiletUseNoDk());
            objSave.setToiletLoc(item.getToiletLoc());
            objSave.setCookDvc(item.getCookDvc());
            objSave.setCookDvcOth(item.getCookDvcOth());
            objSave.setCookFuel(item.getCookFuel());
            objSave.setCookFuelOth(item.getCookFuelOth());
            objSave.setCookPlc(item.getCookPlc());
            objSave.setCookPlcOth(item.getCookPlcOth());
            objSave.setFloor(item.getFloor());
            objSave.setFloorOth(item.getFloorOth());
            objSave.setRoof(item.getRoof());
            objSave.setRoofOth(item.getRoofOth());
            objSave.setWall(item.getWall());
            objSave.setWallOth(item.getWallOth());
            objSave.setRoomSleep(item.getRoomSleep());
            objSave.setRoomSleepDk(item.getRoomSleepDk());
            objSave.setHomesteadAny(item.getHomesteadAny());
            objSave.setOthLand(item.getOthLand());
            objSave.setElectricity(item.getElectricity());
            objSave.setHeater(item.getHeater());
            objSave.setAC(item.getAC());
            objSave.setElecFan(item.getElecFan());
            objSave.setLantern(item.getLantern());
            objSave.setLamp(item.getLamp());
            objSave.setMatt(item.getMatt());
            objSave.setBed(item.getBed());
            objSave.setChair(item.getChair());
            objSave.setSofa(item.getSofa());
            objSave.setTables(item.getTables());
            objSave.setWatch(item.getWatch());
            objSave.setWMachine(item.getWMachine());
            objSave.setIron(item.getIron());
            objSave.setBooth(item.getBooth());
            objSave.setSMachine(item.getSMachine());
            objSave.setGenerator(item.getGenerator());
            objSave.setInternet(item.getInternet());
            objSave.setSatellite(item.getSatellite());
            objSave.setLandline(item.getLandline());
            objSave.setCellphone(item.getCellphone());
            objSave.setTV(item.getTV());
            objSave.setRadio(item.getRadio());
            objSave.setDVD(item.getDVD());
            objSave.setVideo(item.getVideo());
            objSave.setComputer(item.getComputer());
            objSave.setCable(item.getCable());
            objSave.setMicrowave(item.getMicrowave());
            objSave.setGeyser(item.getGeyser());
            objSave.setGrill(item.getGrill());
            objSave.setFridge(item.getFridge());
            objSave.setDeepFreezer(item.getDeepFreezer());
            objSave.setStove(item.getStove());
            objSave.setBike(item.getBike());
            objSave.setMotorcycle(item.getMotorcycle());
            objSave.setCar(item.getCar());
            objSave.setRickshaw(item.getRickshaw());
            objSave.setCart(item.getCart());
            objSave.setCanoe(item.getCanoe());
            objSave.setBus(item.getBus());
            objSave.setTractor(item.getTractor());
            objSave.setPlow(item.getPlow());
            objSave.setDuck(item.getDuck());
            objSave.setCow(item.getCow());
            objSave.setSheep(item.getSheep());
            objSave.setGoat(item.getGoat());
            objSave.setChicken(item.getChicken());
            objSave.setDonkey(item.getDonkey());
            objSave.setHorse(item.getHorse());
            objSave.setPig(item.getPig());
            objSave.setBlender(item.getBlender());
            objSave.setMosquito(item.getMosquito());
            objSave.setOtherAsset(item.getOtherAsset());
            objSave.setOtherAsset1(item.getOtherAsset1());
            objSave.setOtherAsset2(item.getOtherAsset2());
            objSave.setOtherAsset3(item.getOtherAsset3());
            objSave.setSESNote(item.getSESNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            String status = objSave.SaveUpdateData(this);
        }
    }


    //Table Name: Climate_Change
    private void Climate_ChangeDataTransfer(String HHID) {
        tmpClimate_Change_DataModel d = new tmpClimate_Change_DataModel();
        String SQL = "Select * from tmpClimate_Change  Where HHID='" + HHID + "'";
        List<tmpClimate_Change_DataModel> data = d.SelectAll(this, SQL);
        for (tmpClimate_Change_DataModel item : data) {
            Climate_Change_DataModel objSave = new Climate_Change_DataModel();
            objSave.setHHID(item.getHHID());
            objSave.setClimateSL(item.getClimateSL());
            objSave.setClimateVDate(item.getClimateVDate());
            objSave.setClimateVStatus(item.getClimateVStatus());
            objSave.setClimateVStatusOth(item.getClimateVStatusOth());
            objSave.setRnd(item.getRnd());
            objSave.setClimateChangesNoticed(item.getClimateChangesNoticed());
            objSave.setClimateChangesTemp(item.getClimateChangesTemp());
            objSave.setClimateChangesSeason(item.getClimateChangesSeason());
            objSave.setClimateChangesWeather(item.getClimateChangesWeather());
            objSave.setClimateChangesFlora(item.getClimateChangesFlora());
            objSave.setClimateChangesOther(item.getClimateChangesOther());
            objSave.setClimateChangesOtherSp(item.getClimateChangesOtherSp());
            objSave.setClimateAffectDailyLife(item.getClimateAffectDailyLife());
            objSave.setClimateFlood(item.getClimateFlood());
            objSave.setClimateFloodHouse(item.getClimateFloodHouse());
            objSave.setClimateFloodWall(item.getClimateFloodWall());
            objSave.setClimateFloodMember(item.getClimateFloodMember());
            objSave.setClimateFloodLivestock(item.getClimateFloodLivestock());
            objSave.setClimateFloodJobLess(item.getClimateFloodJobLess());
            objSave.setClimateFloodEnergy(item.getClimateFloodEnergy());
            objSave.setClimateFloodToilets(item.getClimateFloodToilets());
            objSave.setClimateFloodHealth(item.getClimateFloodHealth());
            objSave.setClimateFloodMigration(item.getClimateFloodMigration());
            objSave.setClimateFloodOther(item.getClimateFloodOther());
            objSave.setClimateFloodOtherSp(item.getClimateFloodOtherSp());
            objSave.setClimateHighHeat(item.getClimateHighHeat());
            objSave.setClimateHighHeatMemberDth(item.getClimateHighHeatMemberDth());
            objSave.setClimateHighHeatHealth(item.getClimateHighHeatHealth());
            objSave.setClimateHighHeatMemberIll(item.getClimateHighHeatMemberIll());
            objSave.setClimateHighHeatPetDth(item.getClimateHighHeatPetDth());
            objSave.setClimateHighHeatOther(item.getClimateHighHeatOther());
            objSave.setClimateHighHeatOtherSp(item.getClimateHighHeatOtherSp());
            objSave.setClimateHealthProblemSleep(item.getClimateHealthProblemSleep());
            objSave.setClimateHealthProblemDizzy(item.getClimateHealthProblemDizzy());
            objSave.setClimateHealthProblemLowBlood(item.getClimateHealthProblemLowBlood());
            objSave.setClimateHealthProblemHighBlood(item.getClimateHealthProblemHighBlood());
            objSave.setClimateHealthProblemStroke(item.getClimateHealthProblemStroke());
            objSave.setClimateHealthProblemFever(item.getClimateHealthProblemFever());
            objSave.setClimateHealthProblemOther(item.getClimateHealthProblemOther());
            objSave.setClimateHealthProblemOtherSp(item.getClimateHealthProblemOtherSp());
            objSave.setClimateHealthProblemPeriod(item.getClimateHealthProblemPeriod());
            objSave.setClimateIllnessMalaria(item.getClimateIllnessMalaria());
            objSave.setClimateIllnessPneumonia(item.getClimateIllnessPneumonia());
            objSave.setClimateIllnessMeasles(item.getClimateIllnessMeasles());
            objSave.setClimateIllnessPertussis(item.getClimateIllnessPertussis());
            objSave.setClimateIllnessDiarrhea(item.getClimateIllnessDiarrhea());
            objSave.setClimateIllnessFood(item.getClimateIllnessFood());
            objSave.setClimateIllnessTyphoid(item.getClimateIllnessTyphoid());
            objSave.setClimateIllnessCough(item.getClimateIllnessCough());
            objSave.setClimateIllnessCholera(item.getClimateIllnessCholera());
            objSave.setClimateIllnessOther(item.getClimateIllnessOther());
            objSave.setClimateIllnessOtherSp(item.getClimateIllnessOtherSp());
            objSave.setClimateIllnessPeriod(item.getClimateIllnessPeriod());
            objSave.setClimateHeard(item.getClimateHeard());
            objSave.setClimateInfoMedia(item.getClimateInfoMedia());
            objSave.setClimateInfoInternet(item.getClimateInfoInternet());
            objSave.setClimateInfoEducation(item.getClimateInfoEducation());
            objSave.setClimateInfoFnF(item.getClimateInfoFnF());
            objSave.setClimateInfoOther(item.getClimateInfoOther());
            objSave.setClimateInfoOtherSp(item.getClimateInfoOtherSp());
            objSave.setClimateKnRate(item.getClimateKnRate());
            objSave.setClimatethreat(item.getClimatethreat());
            objSave.setClimateWorried(item.getClimateWorried());
            objSave.setClimateConcernTemp(item.getClimateConcernTemp());
            objSave.setClimateConcernEvent(item.getClimateConcernEvent());
            objSave.setClimateConcernPolarIce(item.getClimateConcernPolarIce());
            objSave.setClimateConcernSeaLevel(item.getClimateConcernSeaLevel());
            objSave.setClimateConcernBiodiversity(item.getClimateConcernBiodiversity());
            objSave.setClimateConcernDrying(item.getClimateConcernDrying());
            objSave.setClimateConcernRising(item.getClimateConcernRising());
            objSave.setClimateConcernDrought(item.getClimateConcernDrought());
            objSave.setClimateConcernDesert(item.getClimateConcernDesert());
            objSave.setClimateConcernErosin(item.getClimateConcernErosin());
            objSave.setClimateConcernOther(item.getClimateConcernOther());
            objSave.setClimateConcernOtherSp(item.getClimateConcernOtherSp());
            objSave.setClimateComDoing(item.getClimateComDoing());
            objSave.setClimateReduceImpact(item.getClimateReduceImpact());
            objSave.setClimateActionsPlastics(item.getClimateActionsPlastics());
            objSave.setClimateActionsTransport(item.getClimateActionsTransport());
            objSave.setClimateActionsFoods(item.getClimateActionsFoods());
            objSave.setClimateActionsHome(item.getClimateActionsHome());
            objSave.setClimateActionsOther(item.getClimateActionsOther());
            objSave.setClimateActionsOtherSp(item.getClimateActionsOtherSp());
            objSave.setClimateRenewableEnergy(item.getClimateRenewableEnergy());
            objSave.setClimateObstaclesCost(item.getClimateObstaclesCost());
            objSave.setClimateObstaclesLackInfo(item.getClimateObstaclesLackInfo());
            objSave.setClimateObstaclesAccess(item.getClimateObstaclesAccess());
            objSave.setClimateObstaclesHabit(item.getClimateObstaclesHabit());
            objSave.setClimateObstaclesOther(item.getClimateObstaclesOther());
            objSave.setClimateObstaclesOtherSp(item.getClimateObstaclesOtherSp());
            objSave.setClimateNote(item.getClimateNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: SES
    private void SESMaliDataTransfer(String HHID) {
        tmpSES_Mali_DataModel d = new tmpSES_Mali_DataModel();
        String SQL = "Select * from tmpSES_Mali  Where HHID='" + HHID + "'";
        List<tmpSES_Mali_DataModel> data = d.SelectAll(this, SQL);
        for (tmpSES_Mali_DataModel item : data) {
            SES_Mali_DataModel objSave = new SES_Mali_DataModel();
            objSave.setHHID(item.getHHID());
            objSave.setSESNo(item.getSESNo());
            objSave.setSESVDate(item.getSESVDate());
            objSave.setSESVStatus(item.getSESVStatus());
            objSave.setSESVStatusOth(item.getSESVStatusOth());
            objSave.setRnd(item.getRnd());
            objSave.setWSDrink(item.getWSDrink());
            objSave.setWSDrinkOth(item.getWSDrinkOth());
            objSave.setWaterSource(item.getWaterSource());
            objSave.setFetchWaterM(item.getFetchWaterM());
            objSave.setFetchWaterMDk(item.getFetchWaterMDk());
            objSave.setGetWater(item.getGetWater());
            objSave.setGetWaterOth(item.getGetWaterOth());
            objSave.setMemberID(item.getMemberID());
            objSave.setBringWater(item.getBringWater());
            objSave.setBringWaterDk(item.getBringWaterDk());
            objSave.setSomeone(item.getSomeone());
            objSave.setSecondPers(item.getSecondPers());
            objSave.setSecondPersOth(item.getSecondPersOth());
            objSave.setMemberID2nd(item.getMemberID2nd());
            objSave.setEnoughWater(item.getEnoughWater());
            objSave.setMainWater(item.getMainWater());
            objSave.setMainWaterOth(item.getMainWaterOth());
            objSave.setSmallTank(item.getSmallTank());
            objSave.setMediunTank(item.getMediunTank());
            objSave.setLargeTank(item.getLargeTank());
            objSave.setStoreDrink(item.getStoreDrink());
            objSave.setContainOpenCov(item.getContainOpenCov());
            objSave.setContainOpenNotCov(item.getContainOpenNotCov());
            objSave.setContainOpenDK(item.getContainOpenDK());
            objSave.setRecoverWater(item.getRecoverWater());
            objSave.setRecoverWaterOth(item.getRecoverWaterOth());
            objSave.setLessDanger(item.getLessDanger());
            objSave.setMakeSafer(item.getMakeSafer());
            objSave.setMakeSaferOth(item.getMakeSaferOth());
            objSave.setToilet(item.getToilet());
            objSave.setToiletOth(item.getToiletOth());
            objSave.setToiletShrd(item.getToiletShrd());
            objSave.setToiletUseNo(item.getToiletUseNo());
            objSave.setToiletUseNoDk(item.getToiletUseNoDk());
            objSave.setToiletLoc(item.getToiletLoc());
            objSave.setContentEmp(item.getContentEmp());
            objSave.setContentEmpOth(item.getContentEmpOth());
            objSave.setBowelMov(item.getBowelMov());
            objSave.setBowelMovOth(item.getBowelMovOth());
            objSave.setLiquidWaste(item.getLiquidWaste());
            objSave.setLiquidWasteOth(item.getLiquidWasteOth());
            objSave.setSolidWasteMethod(item.getSolidWasteMethod());
            objSave.setSolidWasteMethodOth(item.getSolidWasteMethodOth());
            objSave.setHandWash(item.getHandWash());
            objSave.setShowWash(item.getShowWash());
            objSave.setAvailableWat(item.getAvailableWat());
            objSave.setAvailableSoap(item.getAvailableSoap());
            objSave.setCookDvc(item.getCookDvc());
            objSave.setCookDvcOth(item.getCookDvcOth());
            objSave.setCookFuel(item.getCookFuel());
            objSave.setCookFuelOth(item.getCookFuelOth());
            objSave.setCookPlc(item.getCookPlc());
            objSave.setCookPlcOth(item.getCookPlcOth());
            objSave.setFloor(item.getFloor());
            objSave.setFloorOth(item.getFloorOth());
            objSave.setGroundMat(item.getGroundMat());
            objSave.setGroundMatOth(item.getGroundMatOth());
            objSave.setRoof(item.getRoof());
            objSave.setRoofOth(item.getRoofOth());
            objSave.setSmokeInside(item.getSmokeInside());
            objSave.setFreqSmoke(item.getFreqSmoke());
            objSave.setWall(item.getWall());
            objSave.setWallOth(item.getWallOth());
            objSave.setRoomSleep(item.getRoomSleep());
            objSave.setRoomSleepDk(item.getRoomSleepDk());
            objSave.setElecNight(item.getElecNight());
            objSave.setElecNightOth(item.getElecNightOth());
            objSave.setHomesteadAny(item.getHomesteadAny());
            objSave.setOthLand(item.getOthLand());
            objSave.setArea(item.getArea());
            objSave.setIncomeMo(item.getIncomeMo());
            objSave.setExpenses(item.getExpenses());
            objSave.setBankAcc(item.getBankAcc());
            objSave.setSprayInt(item.getSprayInt());
            objSave.setMosqNet(item.getMosqNet());
            objSave.setNetOwned(item.getNetOwned());
            objSave.setMedHome(item.getMedHome());
            objSave.setMedTypeAM(item.getMedTypeAM());
            objSave.setMedTypeAB(item.getMedTypeAB());
            objSave.setMedTypeDK(item.getMedTypeDK());
            objSave.setAntimalarAL(item.getAntimalarAL());
            objSave.setAntimalarASAQ(item.getAntimalarASAQ());
            objSave.setAntimalarSP(item.getAntimalarSP());
            objSave.setAntimalarOth(item.getAntimalarOth());
            objSave.setAntimalarSpecifyOth(item.getAntimalarSpecifyOth());
            objSave.setGetMedHosp(item.getGetMedHosp());
            objSave.setGetMedCSCom(item.getGetMedCSCom());
            objSave.setGetMedPrvCl(item.getGetMedPrvCl());
            objSave.setGetMedPhar(item.getGetMedPhar());
            objSave.setGetMedPD(item.getGetMedPD());
            objSave.setGetMedCHW(item.getGetMedCHW());
            objSave.setGetMedSS(item.getGetMedSS());
            objSave.setGetMedOth(item.getGetMedOth());
            objSave.setGetMedSpecifyOth(item.getGetMedSpecifyOth());
            objSave.setAComment(item.getAComment());
            objSave.setComment(item.getComment());
            objSave.setElectricity(item.getElectricity());
            objSave.setSolarPlate(item.getSolarPlate());
            objSave.setSolarPlateN(item.getSolarPlateN());
            objSave.setHeater(item.getHeater());
            objSave.setHeaterN(item.getHeaterN());
            objSave.setAC(item.getAC());
            objSave.setACN(item.getACN());
            objSave.setElecFan(item.getElecFan());
            objSave.setElecFanN(item.getElecFanN());
            objSave.setLantern(item.getLantern());
            objSave.setLanternN(item.getLanternN());
            objSave.setLamp(item.getLamp());
            objSave.setLampN(item.getLampN());
            objSave.setGasLamp(item.getGasLamp());
            objSave.setGasLampN(item.getGasLampN());
            objSave.setPetroleum(item.getPetroleum());
            objSave.setPetroleumN(item.getPetroleumN());
            objSave.setMatt(item.getMatt());
            objSave.setMattN(item.getMattN());
            objSave.setMats(item.getMats());
            objSave.setMatsN(item.getMatsN());
            objSave.setCarpets(item.getCarpets());
            objSave.setCarpetsN(item.getCarpetsN());
            objSave.setBed(item.getBed());
            objSave.setBedN(item.getBedN());
            objSave.setChair(item.getChair());
            objSave.setChairN(item.getChairN());
            objSave.setSofa(item.getSofa());
            objSave.setSofaN(item.getSofaN());
            objSave.setTables(item.getTables());
            objSave.setTablesN(item.getTablesN());
            objSave.setWatch(item.getWatch());
            objSave.setWatchN(item.getWatchN());
            objSave.setWMachine(item.getWMachine());
            objSave.setWMachineN(item.getWMachineN());
            objSave.setIron(item.getIron());
            objSave.setIronN(item.getIronN());
            objSave.setBooth(item.getBooth());
            objSave.setBoothN(item.getBoothN());
            objSave.setSMachine(item.getSMachine());
            objSave.setSMachineN(item.getSMachineN());
            objSave.setGenerator(item.getGenerator());
            objSave.setGeneratorN(item.getGeneratorN());
            objSave.setInternet(item.getInternet());
            objSave.setInternetN(item.getInternetN());
            objSave.setSatellite(item.getSatellite());
            objSave.setSatelliteN(item.getSatelliteN());
            objSave.setLandline(item.getLandline());
            objSave.setLandlineN(item.getLandlineN());
            objSave.setCellphone(item.getCellphone());
            objSave.setCellphoneN(item.getCellphoneN());
            objSave.setTV(item.getTV());
            objSave.setTVN(item.getTVN());
            objSave.setTV5(item.getTV5());
            objSave.setTV5N(item.getTV5N());
            objSave.setChannel(item.getChannel());
            objSave.setChannelN(item.getChannelN());
            objSave.setRadio(item.getRadio());
            objSave.setRadioN(item.getRadioN());
            objSave.setDVD(item.getDVD());
            objSave.setDVDN(item.getDVDN());
            objSave.setVideo(item.getVideo());
            objSave.setVideoN(item.getVideoN());
            objSave.setComputer(item.getComputer());
            objSave.setComputerN(item.getComputerN());
            objSave.setLaptop(item.getLaptop());
            objSave.setLaptopN(item.getLaptopN());
            objSave.setCable(item.getCable());
            objSave.setCableN(item.getCableN());
            objSave.setMicrowave(item.getMicrowave());
            objSave.setMicrowaveN(item.getMicrowaveN());
            objSave.setGeyser(item.getGeyser());
            objSave.setGeyserN(item.getGeyserN());
            objSave.setGrill(item.getGrill());
            objSave.setGrillN(item.getGrillN());
            objSave.setGrain(item.getGrain());
            objSave.setGrainN(item.getGrainN());
            objSave.setRefrigerator(item.getRefrigerator());
            objSave.setRefrigeratorN(item.getRefrigeratorN());
            objSave.setDeepFreezer(item.getDeepFreezer());
            objSave.setDeepFreezerN(item.getDeepFreezerN());
            objSave.setStove(item.getStove());
            objSave.setStoveN(item.getStoveN());
            objSave.setGasHob(item.getGasHob());
            objSave.setGasHobN(item.getGasHobN());
            objSave.setImpCooker(item.getImpCooker());
            objSave.setImpCookerN(item.getImpCookerN());
            objSave.setBike(item.getBike());
            objSave.setBikeN(item.getBikeN());
            objSave.setMotorcycle(item.getMotorcycle());
            objSave.setMotorcycleN(item.getMotorcycleN());
            objSave.setCar(item.getCar());
            objSave.setCarN(item.getCarN());
            objSave.setRickshaw(item.getRickshaw());
            objSave.setRickshawN(item.getRickshawN());
            objSave.setCart(item.getCart());
            objSave.setCartN(item.getCartN());
            objSave.setCanoe(item.getCanoe());
            objSave.setCanoeN(item.getCanoeN());
            objSave.setBus(item.getBus());
            objSave.setBusN(item.getBusN());
            objSave.setTractor(item.getTractor());
            objSave.setTractorN(item.getTractorN());
            objSave.setPlow(item.getPlow());
            objSave.setPlowN(item.getPlowN());
            objSave.setDuck(item.getDuck());
            objSave.setDuckN(item.getDuckN());
            objSave.setCow(item.getCow());
            objSave.setCowN(item.getCowN());
            objSave.setSheep(item.getSheep());
            objSave.setSheepN(item.getSheepN());
            objSave.setGoat(item.getGoat());
            objSave.setGoatN(item.getGoatN());
            objSave.setChicken(item.getChicken());
            objSave.setChickenN(item.getChickenN());
            objSave.setDonkey(item.getDonkey());
            objSave.setDunkeyN(item.getDunkeyN());
            objSave.setHorse(item.getHorse());
            objSave.setHorseN(item.getHorseN());
            objSave.setPig(item.getPig());
            objSave.setPigN(item.getPigN());
            objSave.setBirds(item.getBirds());
            objSave.setBirdsN(item.getBirdsN());
            objSave.setDogs(item.getDogs());
            objSave.setDogsN(item.getDogsN());
            objSave.setCats(item.getCats());
            objSave.setCatsN(item.getCatsN());
            objSave.setFishNet(item.getFishNet());
            objSave.setFishNetN(item.getFishNetN());
            objSave.setOtherAsset(item.getOtherAsset());
            objSave.setOtherAsset1(item.getOtherAsset1());
            objSave.setOtherAsset1N(item.getOtherAsset1N());
            objSave.setOtherAsset2(item.getOtherAsset2());
            objSave.setOtherAsset2N(item.getOtherAsset2N());
            objSave.setOtherAsset3(item.getOtherAsset3());
            objSave.setOtherAsset3N(item.getOtherAsset3N());
            objSave.setSESNote(item.getSESNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: SES
    private void SESCrossRiverDataTransfer(String HHID) {
        tmpSES_CrossRiver_DataModel d = new tmpSES_CrossRiver_DataModel();
        String SQL = "Select * from tmpSES_CrossRiver  Where HHID='" + HHID + "'";
        List<tmpSES_CrossRiver_DataModel> data = d.SelectAll(this, SQL);
        for (tmpSES_CrossRiver_DataModel item : data) {
            SES_CrossRiver_DataModel objSave = new SES_CrossRiver_DataModel();
            objSave.setHHID(item.getHHID());
            objSave.setSESNo(item.getSESNo());
            objSave.setSESVDate(item.getSESVDate());
            objSave.setSESVStatus(item.getSESVStatus());
            objSave.setSESVStatusOth(item.getSESVStatusOth());
            objSave.setRnd(item.getRnd());
            objSave.setWSDrink(item.getWSDrink());
            objSave.setWSDrinkOth(item.getWSDrinkOth());
            objSave.setMainWater(item.getMainWater());
            objSave.setMainWaterOth(item.getMainWaterOth());
            objSave.setToilet(item.getToilet());
            objSave.setToiletOth(item.getToiletOth());
            objSave.setToiletShrd(item.getToiletShrd());
            objSave.setToiletUseNo(item.getToiletUseNo());
            objSave.setToiletUseNoDk(item.getToiletUseNoDk());
            objSave.setToiletLoc(item.getToiletLoc());
            objSave.setHandWash(item.getHandWash());
            objSave.setShowWash(item.getShowWash());
            objSave.setAvailableWat(item.getAvailableWat());
            objSave.setAvailableSoap(item.getAvailableSoap());
            objSave.setHandWashOthMem(item.getHandWashOthMem());
            objSave.setHandWashOthMemO(item.getHandWashOthMemO());
            objSave.setSoapInHouse(item.getSoapInHouse());
            objSave.setSoapInHouseShow(item.getSoapInHouseShow());
            objSave.setSoapInHouseObjA(item.getSoapInHouseObjA());
            objSave.setSoapInHouseObjB(item.getSoapInHouseObjB());
            objSave.setSoapInHouseObjC(item.getSoapInHouseObjC());
            objSave.setCookDvc(item.getCookDvc());
            objSave.setCookDvcOth(item.getCookDvcOth());
            objSave.setCookFuel(item.getCookFuel());
            objSave.setCookFuelOth(item.getCookFuelOth());
            objSave.setCookPlc(item.getCookPlc());
            objSave.setCookPlcOth(item.getCookPlcOth());
            objSave.setFloor(item.getFloor());
            objSave.setFloorOth(item.getFloorOth());
            objSave.setRoof(item.getRoof());
            objSave.setRoofOth(item.getRoofOth());
            objSave.setWall(item.getWall());
            objSave.setWallOth(item.getWallOth());
            objSave.setRoomSleep(item.getRoomSleep());
            objSave.setRoomSleepDk(item.getRoomSleepDk());
            objSave.setHomesteadAny(item.getHomesteadAny());
            objSave.setOthLand(item.getOthLand());
            objSave.setElectricity(item.getElectricity());
            objSave.setHeater(item.getHeater());
            objSave.setAC(item.getAC());
            objSave.setElecFan(item.getElecFan());
            objSave.setLantern(item.getLantern());
            objSave.setLamp(item.getLamp());
            objSave.setGasLamp(item.getGasLamp());
            objSave.setPetroleum(item.getPetroleum());
            objSave.setMatt(item.getMatt());
            objSave.setMats(item.getMats());
            objSave.setCarpets(item.getCarpets());
            objSave.setBed(item.getBed());
            objSave.setChair(item.getChair());
            objSave.setSofa(item.getSofa());
            objSave.setTables(item.getTables());
            objSave.setWatch(item.getWatch());
            objSave.setWMachine(item.getWMachine());
            objSave.setIron(item.getIron());
            objSave.setBooth(item.getBooth());
            objSave.setSMachine(item.getSMachine());
            objSave.setGenerator(item.getGenerator());
            objSave.setInternet(item.getInternet());
            objSave.setSatellite(item.getSatellite());
            objSave.setLandline(item.getLandline());
            objSave.setCellphone(item.getCellphone());
            objSave.setTV(item.getTV());
            objSave.setTV5(item.getTV5());
            objSave.setChannel(item.getChannel());
            objSave.setRadio(item.getRadio());
            objSave.setDVD(item.getDVD());
            objSave.setVideo(item.getVideo());
            objSave.setComputer(item.getComputer());
            objSave.setLaptop(item.getLaptop());
            objSave.setCable(item.getCable());
            objSave.setMicrowave(item.getMicrowave());
            objSave.setGeyser(item.getGeyser());
            objSave.setGrill(item.getGrill());
            objSave.setGrain(item.getGrain());
            objSave.setRefrigerator(item.getRefrigerator());
            objSave.setDeepFreezer(item.getDeepFreezer());
            objSave.setStove(item.getStove());
            objSave.setGasHob(item.getGasHob());
            objSave.setImpCooker(item.getImpCooker());
            objSave.setBike(item.getBike());
            objSave.setMotorcycle(item.getMotorcycle());
            objSave.setCar(item.getCar());
            objSave.setRickshaw(item.getRickshaw());
            objSave.setCart(item.getCart());
            objSave.setCanoe(item.getCanoe());
            objSave.setBus(item.getBus());

            objSave.setTricycle(item.getTricycle());
            objSave.setBoatWithMot(item.getBoatWithMot());

            objSave.setTractor(item.getTractor());
            objSave.setPlow(item.getPlow());
            objSave.setDuck(item.getDuck());
            objSave.setCow(item.getCow());
            objSave.setSheep(item.getSheep());
            objSave.setGoat(item.getGoat());
            objSave.setChicken(item.getChicken());
            objSave.setDonkey(item.getDonkey());
            objSave.setHorse(item.getHorse());
            objSave.setPig(item.getPig());
            objSave.setBirds(item.getBirds());
            objSave.setDogs(item.getDogs());
            objSave.setCats(item.getCats());
            objSave.setFishNet(item.getFishNet());
            objSave.setOtherAsset(item.getOtherAsset());
            objSave.setOtherAsset1(item.getOtherAsset1());
            objSave.setOtherAsset2(item.getOtherAsset2());
            objSave.setOtherAsset3(item.getOtherAsset3());
            objSave.setSESNote(item.getSESNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list

            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: PregHis
    private void PreHisDataTransfer(String HHID) {
        tmpPregHis_DataModel d = new tmpPregHis_DataModel();
        String SQL = "Select * from tmpPregHis  Where HHID='" + HHID + "'";
        List<tmpPregHis_DataModel> data = d.SelectAll(this, SQL);
        for (tmpPregHis_DataModel item : data) {
            PregHis_DataModel objSave = new PregHis_DataModel();
            objSave.setObsMatID(item.getObsMatID());
            objSave.setMemID(item.getMemID());
            objSave.setHHID(item.getHHID());
            objSave.setMSlNo(item.getMSlNo());
            objSave.setObsVDate(item.getObsVDate());
            objSave.setObsVStatus(item.getObsVStatus());
            objSave.setObsVStatusOth(item.getObsVStatusOth());
            objSave.setMarMon(item.getMarMon());
            objSave.setMarYear(item.getMarYear());
            objSave.setMarDK((item.getMarDK()));
            objSave.setEverPreg(item.getEverPreg());
            objSave.setTotPreg(item.getTotPreg());
            objSave.setGaveBirth(item.getGaveBirth());
            objSave.setChildLivWWo(item.getChildLivWWo());
            objSave.setSonLivWWo(item.getSonLivWWo());
            objSave.setDaugLivWWo(item.getDaugLivWWo());
            objSave.setChldLivOut(item.getChldLivOut());
            objSave.setSonLivOut(item.getSonLivOut());
            objSave.setDaugLivOut(item.getDaugLivOut());
            objSave.setEarlyAlive(item.getEarlyAlive());
            objSave.setEarlyAliveNo(item.getEarlyAliveNo());
            objSave.setChldDie(item.getChldDie());
            objSave.setBoyDied(item.getBoyDied());
            objSave.setGirlDied(item.getGirlDied());
            objSave.setChDiedFsMon(item.getChDiedFsMon());
            objSave.setNotLivBrth(item.getNotLivBrth());
            objSave.setTotNotLB(item.getTotNotLB());
            objSave.setStillBirth(item.getStillBirth());
            objSave.setStillBirthDk((item.getStillBirthDk()));
            objSave.setMiscAbor(item.getMiscAbor());
            objSave.setMiscAborDk((item.getMiscAborDk()));

            objSave.setMisc(item.getMisc());
            objSave.setMiscDk((item.getMiscDk()));

            objSave.setLastPregRes(item.getLastPregRes());
            objSave.setLastOutDate(item.getLastOutDate());
            objSave.setLastOutDateDK((item.getLastOutDateDK()));
            objSave.setCesarean(item.getCesarean());
            objSave.setCesareanNo(item.getCesareanNo());
            objSave.setTotPregOut(item.getTotPregOut());
            objSave.setObsNote(item.getObsNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: MemberMove
    private void MemberMoveDataTransfer(String HHID) {
        tmpMemberMove_DataModel d = new tmpMemberMove_DataModel();
        String SQL = "Select * from tmpMemberMove  Where HHID='" + HHID + "'";
        List<tmpMemberMove_DataModel> data = d.SelectAll(this, SQL);
        for (tmpMemberMove_DataModel item : data) {
            MemberMove_DataModel objSave = new MemberMove_DataModel();
            objSave.setMemID(item.getMemID());
            objSave.setHHID(item.getHHID());
            objSave.setActive(item.getActive());
            objSave.setMSlNo(item.get_MSlNo());
            objSave.setDSSID(item.getDSSID());
            objSave.setMEnType(item.getMEnType());
            objSave.setMEnDate(item.getMEnDate());
            objSave.setMExType(item.getMExType());
            objSave.setMExDate(item.getMExDate());
            objSave.setRnd(item.getRnd());
            objSave.setMemNote(item.getMemNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            //Newly added
            objSave.setRth(item.getRth());
            objSave.setMoNo(item.getMoNo());
            objSave.setFaNo(item.getFaNo());
            objSave.setEduY(item.getEduY());
            objSave.setOcp(item.getOcp());
            objSave.setMS(item.getMS());
            objSave.setEmploy(item.getEmploy());
            objSave.setEmployOth(item.getEmploy());
            objSave.setOcpDk(item.getOcpDk());
            objSave.setOcpOth(item.getOcpOth());
            objSave.setMSOth(item.getMSOth());
            objSave.setSp1(item.getSp1());
            objSave.setSp1Name(item.getSp1Name());
            objSave.setSp2(item.getSp2());
            objSave.setSp2Name(item.getSp2Name());
            objSave.setSp3(item.getSp3());
            objSave.setSp3Name(item.getSp3Name());
            objSave.setSp4(item.getSp4());
            objSave.setSp4Name(item.getSp4Name());

            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: Migration
    private void MigrationDataTransfer(String HHID) {
        tmpMigration_DataModel d = new tmpMigration_DataModel();
        String SQL = "Select * from tmpMigration  Where HHID='" + HHID + "'";
        List<tmpMigration_DataModel> data = d.SelectAll(this, SQL);
        for (tmpMigration_DataModel item : data) {
            Migration_DataModel objSave = new Migration_DataModel();
            objSave.setMigrationID(item.getMigrationID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setMigEvType(item.getMigEvType());
            objSave.setMigDate(item.getMigDate());
            objSave.setMigLocType(item.getMigLocType());
            objSave.setMigAdminL1(item.getMigAdminL1());
            objSave.setMigAdminL2(item.getMigAdminL2());
            objSave.setMigAdminL3(item.getMigAdminL3());
            objSave.setMigAdminL4(item.getMigAdminL4());
            objSave.setMigAdminLArea(item.getMigAdminLArea());
            objSave.setMigCountry(item.getMigCountry());
            objSave.setMigBirthCountry(item.getMigBirthCountry());
            objSave.setMigBirthCountryOth(item.getMigBirthCountryOth());
            objSave.setMigOriginCountry(item.getMigOriginCountry());
            objSave.setMigOriginCountryOth(item.getMigOriginCountryOth());
            objSave.setMigWCRegionBurCity(item.getMigWCRegionBurCity());
            objSave.setMigBurCity(item.getMigBurCity());
            objSave.setMigAbleToRW(item.getMigAbleToRW());
            objSave.setchkMigLanguageA(item.getchkMigLanguageA());
            objSave.setchkMigLanguageB(item.getchkMigLanguageB());
            objSave.setchkMigLanguageC(item.getchkMigLanguageC());
            objSave.setchkMigLanguageD(item.getchkMigLanguageD());
            objSave.setMigLanguageOth(item.getMigLanguageOth());
            objSave.setMigOccupation(item.getMigOccupation());
            objSave.setMigTotalUnion(item.getMigTotalUnion());
            objSave.setMigFirstUnionDate(item.getMigFirstUnionDate());
            objSave.setMigUnionRupture(item.getMigUnionRupture());
            objSave.setMigRuptureDate(item.getMigRuptureDate());
            objSave.setMigNotDisUnionDate(item.getMigNotDisUnionDate());
            objSave.setMigMaritalChangeReason(item.getMigMaritalChangeReason()); //uchchash
            objSave.setMigMaritalChangeReasonOth(item.getMigMaritalChangeReasonOth()); //uchchash



            objSave.setMigReason(item.getMigReason());
            objSave.setMigReasonOth(item.getMigReasonOth());
            objSave.setMigVDate(item.getMigVDate());
            objSave.setRnd(item.getRnd());
            objSave.setMigNote(item.getMigNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    private void TemporaryMigrationOutDataTransfer(String HHID){
        tmpTemporaryMigrationOut_DataModel d = new tmpTemporaryMigrationOut_DataModel();
        String SQL = "Select * from tmpTemporaryMigrationOut  Where HHID='" + HHID + "'";
        List<tmpTemporaryMigrationOut_DataModel> data = d.SelectAll(this, SQL);
        for (tmpTemporaryMigrationOut_DataModel item : data) {
            TemporaryMigrationOut_DataModel objSave = new TemporaryMigrationOut_DataModel();
            objSave.setTmpMigrationID(item.getTmpMigrationID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setTmpMigVDate(item.getTmpMigVDate());
            objSave.setTmpMigLeave(item.getTmpMigLeave());
            objSave.setTmpMigMemberAway(item.getTmpMigMemberAway());
            objSave.setMigReason(item.getMigReason()); //uchchash
            objSave.setMigReasonOth(item.getMigReasonOth()); //uchchash
            objSave.setStartTime(STARTTIME); //uchchash
            objSave.setEndTime(g.CurrentTime24()); //uchchash
            objSave.setDeviceID(DEVICEID); //uchchash
            objSave.setEntryUser(ENTRYUSER);  //uchchash
            String status = objSave.SaveUpdateData(this);//uchchash
        }
    }
    private void TemporaryMigrationInDataTransfer(String HHID){
        tmpTemporaryMigrationIn_DataModel d = new tmpTemporaryMigrationIn_DataModel();
        String SQL = "Select * from tmpTemporaryMigrationIn  Where HHID='" + HHID + "'";
        List<tmpTemporaryMigrationIn_DataModel> data = d.SelectAll(this, SQL);
        for (tmpTemporaryMigrationIn_DataModel item : data) {
            TemporaryMigrationIn_DataModel objSave = new TemporaryMigrationIn_DataModel();
            objSave.setTmpMigrationID(item.getTmpMigrationID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setTmpMigVDate(item.getTmpMigVDate());
            objSave.setTmpMigReside(item.getTmpMigReside());
            objSave.setTmpMigStayMonth(item.getTmpMigStayMonth());
            objSave.setTmpMigVisitorArrivalDate(item.getTmpMigVisitorArrivalDate());
            objSave.setMigReason(item.getMigReason()); //uchchash
            objSave.setMigReasonOth(item.getMigReasonOth()); //uchchash
            objSave.setStartTime(STARTTIME); //uchchash
            objSave.setEndTime(g.CurrentTime24()); //uchchash
            objSave.setDeviceID(DEVICEID); //uchchash
            objSave.setEntryUser(ENTRYUSER);  //uchchash
            String status = objSave.SaveUpdateData(this);//uchchash
        }
    }

    //Table Name: Movement
    private void MovementDataTransfer(String HHID) {
        tmpMovement_DataModel d = new tmpMovement_DataModel();
        String SQL = "Select * from tmpMovement  Where HHID='" + HHID + "'";
        List<tmpMovement_DataModel> data = d.SelectAll(this, SQL);
        for (tmpMovement_DataModel item : data) {
            Movement_DataModel objSave = new Movement_DataModel();
            objSave.setMovementID(item.getMovementID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setMoveEvType(item.getMoveEvType());
            objSave.setMoveDate(item.getMoveDate());
            objSave.setMoveAddress(item.getMoveAddress());
            objSave.setMovePhoneAvail(item.getMovePhoneAvail());
            objSave.setMovePhone(item.getMovePhone());
            objSave.setMoveAddressAvail(item.getMoveAddressAvail());
            objSave.setMoveVill(item.getMoveVill());
            objSave.setMoveCompound(item.getMoveCompound());
            objSave.setMoveHH(item.getMoveHH());
            objSave.setMoveReason(item.getMoveReason());
            objSave.setMoveReasonOth(item.getMoveReasonOth());
            objSave.setMoveVDate(item.getMoveVDate());
            objSave.setRnd(item.getRnd());
            objSave.setMoveNote(item.getMoveNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: Death
    private void DeathDataTransfer(String HHID) {
        tmpDeath_DataModel d = new tmpDeath_DataModel();
        String SQL = "Select * from tmpDeath  Where HHID='" + HHID + "'";
        List<tmpDeath_DataModel> data = d.SelectAll(this, SQL);
        for (tmpDeath_DataModel item : data) {
            Death_DataModel objSave = new Death_DataModel();
            objSave.setDeathID(item.getDeathID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setDthDate(item.getDthDate());
            objSave.setDthDateType(item.getDthDateType());
            objSave.setDthTime(item.getDthTime());
            objSave.setDthTimeType(item.getDthTimeType());
            objSave.setDthPlace(item.getDthPlace());
            objSave.setDthPlaceOth(item.getDthPlaceOth());
            objSave.setDthAdrsState(item.getDthAdrsState());
            objSave.setDthAdrsAL1(item.getDthAdrsAL1());
            objSave.setDthAdrsAL2(item.getDthAdrsAL2());
            objSave.setDthAdrsAL3(item.getDthAdrsAL3());
            objSave.setDthAdrsAL4(item.getDthAdrsAL4());
            objSave.setDthAdrsAL5(item.getDthAdrsAL5());

            objSave.setDthPlaceFacility(item.getDthPlaceFacility());
            objSave.setDthSick(item.getDthSick());
            objSave.setDthCertificate(item.getDthCertificate());
            objSave.setchkDthCauseA(item.getchkDthCauseA());
            objSave.setchkDthCauseB(item.getchkDthCauseB());
            objSave.setchkDthCauseC(item.getchkDthCauseC());
            objSave.setchkDthCauseD(item.getchkDthCauseD());
            objSave.setchkDthCauseE(item.getchkDthCauseE());
            objSave.setchkDthCauseF(item.getchkDthCauseF());
            objSave.setchkDthCauseG(item.getchkDthCauseG());
            objSave.setchkDthCauseH(item.getchkDthCauseH());
            objSave.setchkDthCauseI(item.getchkDthCauseI());
            objSave.setchkDthCauseJ(item.getchkDthCauseJ());
            objSave.setchkDthCauseK(item.getchkDthCauseK());
            objSave.setchkDthCauseL(item.getchkDthCauseL());
            objSave.setchkDthCauseM(item.getchkDthCauseM());
            objSave.setchkDthCauseN(item.getchkDthCauseN());
            objSave.setchkDthCauseO(item.getchkDthCauseO());
            objSave.setchkDthCauseP(item.getchkDthCauseP());
            objSave.setchkDthCauseQ(item.getchkDthCauseQ());
            objSave.setchkDthCauseR(item.getchkDthCauseR());
            objSave.setchkDthCauseS(item.getchkDthCauseS());
            objSave.setchkDthCauseT(item.getchkDthCauseT());
            objSave.setchkDthCauseX(item.getchkDthCauseX());
            objSave.setchkDthCauseY(item.getchkDthCauseY());
            objSave.setchkDthCauseZ(item.getchkDthCauseZ());
            objSave.setDthCauseOth(item.getDthCauseOth());
            objSave.setDthHCProf(item.getDthHCProf());
            objSave.setchkDthCauseProfA(item.getchkDthCauseProfA());
            objSave.setchkDthCauseProfB(item.getchkDthCauseProfB());
            objSave.setchkDthCauseProfC(item.getchkDthCauseProfC());
            objSave.setchkDthCauseProfD(item.getchkDthCauseProfD());
            objSave.setchkDthCauseProfE(item.getchkDthCauseProfE());
            objSave.setchkDthCauseProfF(item.getchkDthCauseProfF());
            objSave.setchkDthCauseProfG(item.getchkDthCauseProfG());
            objSave.setchkDthCauseProfH(item.getchkDthCauseProfH());
            objSave.setchkDthCauseProfI(item.getchkDthCauseProfI());
            objSave.setchkDthCauseProfJ(item.getchkDthCauseProfJ());
            objSave.setchkDthCauseProfK(item.getchkDthCauseProfK());
            objSave.setchkDthCauseProfL(item.getchkDthCauseProfL());
            objSave.setchkDthCauseProfM(item.getchkDthCauseProfM());
            objSave.setchkDthCauseProfN(item.getchkDthCauseProfN());
            objSave.setchkDthCauseProfO(item.getchkDthCauseProfO());
            objSave.setchkDthCauseProfP(item.getchkDthCauseProfP());
            objSave.setchkDthCauseProfQ(item.getchkDthCauseProfQ());
            objSave.setchkDthCauseProfR(item.getchkDthCauseProfR());
            objSave.setchkDthCauseProfS(item.getchkDthCauseProfS());
            objSave.setchkDthCauseProfT(item.getchkDthCauseProfT());
            objSave.setchkDthCauseProfX(item.getchkDthCauseProfX());
            objSave.setchkDthCauseProfY(item.getchkDthCauseProfY());
            objSave.setchkDthCauseProfZ(item.getchkDthCauseProfZ());
            objSave.setDthCauseOthProf(item.getDthCauseOthProf());
            objSave.setDthCategory(item.getDthCategory());
            objSave.setDthEnrolChamps(item.getDthEnrolChamps());
            objSave.setDthChampsIdKnown(item.getDthChampsIdKnown());
            objSave.setDthChampsId(item.getDthChampsId());


            objSave.setDthVDate(item.getDthVDate());
            objSave.setRnd(item.getRnd());
            objSave.setDthNote(item.getDthNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: Delivery
    private void DeliveryDataTransfer(String HHID) {
        tmpDelivery_DataModel d = new tmpDelivery_DataModel();
        String SQL = "Select * from tmpDelivery  Where HHID='" + HHID + "'";
        List<tmpDelivery_DataModel> data = d.SelectAll(this, SQL);
        for (tmpDelivery_DataModel item : data) {
            Delivery_DataModel objSave = new Delivery_DataModel();
            objSave.setDeliveryID(item.getDeliveryID());
            objSave.setPregOccurID(item.getPregOccurID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setDelDate(item.getDelDate());
            objSave.setDelDateType(item.getDelDateType());
            objSave.setDelTime(item.getDelTime());
            objSave.setDelTimeType(item.getDelTimeType());
            objSave.setTotOut(item.getTotOut());
            objSave.setTotLB(item.getTotLB());
            objSave.setTotMis(item.getTotMis());
            objSave.setTotSB(item.getTotSB());
            objSave.setTotAB(item.getTotAB());
            objSave.setDelVDate(item.getDelVDate());
            objSave.setRnd(item.getRnd());
            objSave.setDelNote(item.getDelNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: Education
    private void EducationDataTransfer(String HHID) {
        tmpEducation_DataModel d = new tmpEducation_DataModel();
        String SQL = "Select * from tmpEducation  Where HHID='" + HHID + "'";
        List<tmpEducation_DataModel> data = d.SelectAll(this, SQL);
        for (tmpEducation_DataModel item : data) {
            Education_DataModel objSave = new Education_DataModel();
            objSave.setEduID(item.getEduID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setEduEvDate(item.getEduEvDate());
            objSave.setNewEdu(item.getNewEdu());
            objSave.setOldEdu(item.getOldEdu());
            objSave.setEduNote(item.getEduNote());
            objSave.setEduVDate(item.getEduVDate());
            objSave.setRnd(item.getRnd());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: FatherSerial
    private void FatherSerialDataTransfer(String HHID) {
        tmpFatherSerial_DataModel d = new tmpFatherSerial_DataModel();
        String SQL = "Select * from tmpFatherSerial  Where HHID='" + HHID + "'";
        List<tmpFatherSerial_DataModel> data = d.SelectAll(this, SQL);
        for (tmpFatherSerial_DataModel item : data) {
            FatherSerial_DataModel objSave = new FatherSerial_DataModel();
            objSave.setFathSerialID(item.getFathSerialID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setFathSlEvDate(item.getFathSlEvDate());
            objSave.setNewFathSl(item.getNewFathSl());
            objSave.setOldFathSl(item.getOldFathSl());
            objSave.setFathVDate(item.getFathVDate());
            objSave.setRnd(item.getRnd());
            objSave.setFathSlNote(item.getFathSlNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: LiveBirth
    private void LiveBirthDataTransfer(String HHID) {
        tmpLiveBirth_DataModel d = new tmpLiveBirth_DataModel();
        String SQL = "Select * from tmpLiveBirth  Where HHID='" + HHID + "'";
        List<tmpLiveBirth_DataModel> data = d.SelectAll(this, SQL);
        for (tmpLiveBirth_DataModel item : data) {
            LiveBirth_DataModel objSave = new LiveBirth_DataModel();
            objSave.setLiveBirthID(item.getLiveBirthID());
            objSave.setDeliveryID(item.getDeliveryID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setLBMomID(item.getLBMomID());
            objSave.setLBDOB(item.getLBDOB());
            objSave.setLBTime(item.getLBTime());
            objSave.setNAT(item.getNAT());
            objSave.setLBTimeType(item.getLBTimeType());
            objSave.setLBDPlace(item.getLBDPlace());
            objSave.setLBPlaceOth(item.getLBPlaceOth());
            objSave.setLBDoctor(item.getLBDoctor());
            objSave.setLBNurse(item.getLBNurse());
            objSave.setLBMidwife(item.getLBMidwife());
            objSave.setLBTBA(item.getLBTBA());
            objSave.setLBCHW(item.getLBCHW());
            objSave.setLBRF(item.getLBRF());
            objSave.setLBOth(item.getLBOth());
            objSave.setLBDk(item.getLBDk());
            objSave.setLBRfs(item.getLBRfs());
            objSave.setLBType(item.getLBType());
            objSave.setLBVDate(item.getLBVDate());
            objSave.setRnd(item.getRnd());
            objSave.setLBNote(item.getLBNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: MaritalSts
    private void MaritalStsDataTransfer(String HHID) {
        tmpMaritalSts_DataModel d = new tmpMaritalSts_DataModel();
        String SQL = "Select * from tmpMaritalSts  Where HHID='" + HHID + "'";
        List<tmpMaritalSts_DataModel> data = d.SelectAll(this, SQL);
        for (tmpMaritalSts_DataModel item : data) {
            MaritalSts_DataModel objSave = new MaritalSts_DataModel();
            objSave.setMaritStsID(item.getMaritStsID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setMSEvType(item.getMSEvType());
            objSave.setMSEvDate(item.getMSEvDate());
            objSave.setPrevMS(item.getPrevMS());
            objSave.setMSVDate(item.getMSEvDate());
            objSave.setRnd(item.getRnd());
            objSave.setMSNote(item.getMSNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: MotherSerial
    private void MotherSerialDataTransfer(String HHID) {
        tmpMotherSerial_DataModel d = new tmpMotherSerial_DataModel();
        String SQL = "Select * from tmpMotherSerial  Where HHID='" + HHID + "'";
        List<tmpMotherSerial_DataModel> data = d.SelectAll(this, SQL);
        for (tmpMotherSerial_DataModel item : data) {
            MotherSerial_DataModel objSave = new MotherSerial_DataModel();
            objSave.setMothSerialID(item.getMothSerialID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setMothSlEvDate(item.getMothSlEvDate());
            objSave.setNewMothSl(item.getNewMothSl());
            objSave.setOldMothSl(item.getOldMothSl());
            objSave.setMothVDate(item.getMothVDate());
            objSave.setRnd(item.getRnd());
            objSave.setMothSlNote(item.getMothSlNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: NotPregnant
    private void NotPregnantDataTransfer(String HHID) {
        tmpNotPregnant_DataModel d = new tmpNotPregnant_DataModel();
        String SQL = "Select * from tmpNotPregnant  Where HHID='" + HHID + "'";
        List<tmpNotPregnant_DataModel> data = d.SelectAll(this, SQL);
        for (tmpNotPregnant_DataModel item : data) {
            NotPregnant_DataModel objSave = new NotPregnant_DataModel();
            objSave.setNotPregID(item.getNotPregID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setNotPregVDate(item.getNotPregVDate());
            objSave.setPregStatus(item.getPregStatus());
            objSave.setNotPregNote(item.getNotPregNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: Occupation
    private void OccupationDataTransfer(String HHID) {
        tmpOccupation_DataModel d = new tmpOccupation_DataModel();
        String SQL = "Select * from tmpOccupation  Where HHID='" + HHID + "'";
        List<tmpOccupation_DataModel> data = d.SelectAll(this, SQL);
        for (tmpOccupation_DataModel item : data) {
            Occupation_DataModel objSave = new Occupation_DataModel();
            objSave.setOcpID(item.getOcpID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setOcpEvDate(item.getOcpEvDate());
            objSave.setNewOcp(item.getNewOcp());
            objSave.setOldOcp(item.getOldOcp());
            objSave.setNewEmpStat(item.getNewEmpStat());
            objSave.setOldEmpStat(item.getOldEmpStat());
            objSave.setOcpNote(item.getOcpNote());
            objSave.setOcpVDate(item.getOcpVDate());
            objSave.setRnd(item.getRnd());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: Pregnancy
    private void PregnancyDataTransfer(String HHID) {
        tmpPregnancy_DataModel d = new tmpPregnancy_DataModel();
        String SQL = "Select * from tmpPregnancy  Where HHID='" + HHID + "'";
        List<tmpPregnancy_DataModel> data = d.SelectAll(this, SQL);
        for (tmpPregnancy_DataModel item : data) {
            Pregnancy_DataModel objSave = new Pregnancy_DataModel();
            objSave.setPregOccurID(item.getPregOccurID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setLMPDt(item.getLMPDt());
            objSave.setLMPDtType(item.getLMPDtType());
            objSave.setLMPDtObt(item.getLMPDtObt());
            objSave.setPrConLMP(item.getPrConLMP());
            objSave.setPrConBelly(item.getPrConBelly());
            objSave.setPrConFeMove(item.getPrConFeMove());
            objSave.setPrConTestHome(item.getPrConTestHome());
            objSave.setPrConTestDoc(item.getPrConTestDoc());
            objSave.setPrConHeartSnd(item.getPrConHeartSnd());
            objSave.setPrConUtrasound(item.getPrConUtrasound());
            objSave.setPrConTestFaci(item.getPrConTestFaci());
            objSave.setPrConUrineTest(item.getPrConUrineTest());
            objSave.setPrConOth(item.getPrConOth());
            objSave.setPrConOthSpecify(item.getPrConOthSpecify());

            objSave.setPregPrenatalCons(item.getPregPrenatalCons());
            objSave.setchkPregPrenatalConsA(item.getchkPregPrenatalConsA());
            objSave.setchkPregPrenatalConsB(item.getchkPregPrenatalConsB());
            objSave.setchkPregPrenatalConsC(item.getchkPregPrenatalConsC());
            objSave.setchkPregPrenatalConsD(item.getchkPregPrenatalConsD());
            objSave.setchkPregPrenatalConsE(item.getchkPregPrenatalConsE());
            objSave.setchkPregPrenatalConsF(item.getchkPregPrenatalConsF());
            objSave.setchkPregPrenatalConsG(item.getchkPregPrenatalConsG());
            objSave.setchkPregPrenatalConsH(item.getchkPregPrenatalConsH());
            objSave.setchkPregPrenatalConsI(item.getchkPregPrenatalConsI());
            objSave.setchkPregPrenatalConsPlaceA(item.getchkPregPrenatalConsPlaceA());
            objSave.setchkPregPrenatalConsPlaceB(item.getchkPregPrenatalConsPlaceB());
            objSave.setchkPregPrenatalConsPlaceC(item.getchkPregPrenatalConsPlaceC());
            objSave.setchkPregPrenatalConsPlaceD(item.getchkPregPrenatalConsPlaceD());
            objSave.setchkPregPrenatalConsPlaceE(item.getchkPregPrenatalConsPlaceE());
            objSave.setchkPregPrenatalConsPlaceF(item.getchkPregPrenatalConsPlaceF());
            objSave.setchkPregPrenatalConsPlaceG(item.getchkPregPrenatalConsPlaceG());
            objSave.setchkPregPrenatalConsPlaceH(item.getchkPregPrenatalConsPlaceH());
            objSave.setchkPregPrenatalConsPlaceI(item.getchkPregPrenatalConsPlaceI());
            objSave.setchkPregPrenatalConsPlaceJ(item.getchkPregPrenatalConsPlaceJ());
            objSave.setchkPregPrenatalConsPlaceX(item.getchkPregPrenatalConsPlaceX());
            objSave.setchkPregPrenatalConsPlaceY(item.getchkPregPrenatalConsPlaceY());
            objSave.setchkPregPrenatalConsPlaceZ(item.getchkPregPrenatalConsPlaceZ());
            objSave.setPregPrenatalConsPlaceOth(item.getPregPrenatalConsPlaceOth());
            objSave.setPregRank(item.getPregRank());
            objSave.setPregEnrolled(item.getPregEnrolled());
            objSave.setPregPSIDKnown(item.getPregPSIDKnown());
            objSave.setPregPSID(item.getPregPSID());
            objSave.setPregWishEnroll(item.getPregWishEnroll());


            objSave.setPregVdate(item.getPregVdate());
            objSave.setRnd(item.getRnd());
            objSave.setPregNote(item.getPregNote());
            objSave.setRnd(item.getRnd());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: Relation
    private void RelationDataTransfer(String HHID) {
        tmpRelation_DataModel d = new tmpRelation_DataModel();
        String SQL = "Select * from tmpRelation  Where HHID='" + HHID + "'";
        List<tmpRelation_DataModel> data = d.SelectAll(this, SQL);
        for (tmpRelation_DataModel item : data) {
            Relation_DataModel objSave = new Relation_DataModel();
            objSave.setRthID(item.getRthID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setRthEvDate(item.getRthEvDate());
            objSave.setHeadShipRth(item.getHeadShipRth());
            objSave.setNewRth(item.getNewRth());
            objSave.setOldRth(item.getOldRth());
            objSave.setRthNote(item.getRthNote());
            objSave.setRthVDate(item.getRthVDate());
            objSave.setRnd(item.getRnd());
            objSave.setRnd(item.getRnd());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: SpouseSerial
    private void SpouseSerialDataTransfer(String HHID) {
        tmpSpouseSerial_DataModel d = new tmpSpouseSerial_DataModel();
        String SQL = "Select * from tmpSpouseSerial  Where HHID='" + HHID + "'";
        List<tmpSpouseSerial_DataModel> data = d.SelectAll(this, SQL);
        for (tmpSpouseSerial_DataModel item : data) {
            SpouseSerial_DataModel objSave = new SpouseSerial_DataModel();
            objSave.setSpSerialID(item.getSpSerialID());
            objSave.setHHID(item.getHHID());
            objSave.setMemID(item.getMemID());
            objSave.setSpSlEvDate(item.getSpSlEvDate());
            objSave.setNewSpSl(item.getNewSpSl());
            objSave.setOldSpSl(item.getOldSpSl());
            objSave.setSpSlNote(item.getSpSlNote());
            objSave.setRnd(item.getRnd());
            objSave.setSpVDate(item.getSpVDate());
            objSave.setRnd(item.getRnd());
            objSave.setRnd(item.getRnd());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    //Table Name: StillBirth
    private void StillBirthDataTransfer(String HHID) {
        tmpStillBirth_DataModel d = new tmpStillBirth_DataModel();
        String SQL = "Select * from tmpStillBirth  Where HHID='" + HHID + "'";
        List<tmpStillBirth_DataModel> data = d.SelectAll(this, SQL);
        for (tmpStillBirth_DataModel item : data) {
            StillBirth_DataModel objSave = new StillBirth_DataModel();
            objSave.setStillBirthID(item.getStillBirthID());
            objSave.setDeliveryID(item.getDeliveryID());
            objSave.setHHID(item.getHHID());
            objSave.setSBMomID(item.getSBMomID());
            objSave.setSBDate(item.getSBDate());
            objSave.setSBNAT(item.getSBNAT());
            objSave.setSBDateType(item.getSBDateType());
            objSave.setSBTime(item.getSBTime());
            objSave.setSBTimeType(item.getSBDateType());
            objSave.setSBType(item.getSBType());
            objSave.setSBSex(item.getSBSex());
            objSave.setSBPlace(item.getSBPlace());
            objSave.setSBPlaceOth(item.getSBPlaceOth());
            objSave.setSBDoctor(item.getSBDoctor());
            objSave.setSBNurse(item.getSBNurse());
            objSave.setSBMidwife(item.getSBMidwife());
            objSave.setSBTBA(item.getSBTBA());
            objSave.setSBCHW(item.getSBCHW());
            objSave.setSBRltv(item.getSBRltv());
            objSave.setSBOth(item.getSBOth());
            objSave.setSBOthSp(item.getSBOthSp());
            objSave.setSBDk(item.getSBDk());
            objSave.setSBRfs(item.getSBRfs());
            objSave.setSBDelType(item.getSBDelType());
            objSave.setSBVDate(item.getSBVDate());
            objSave.setRnd(item.getRnd());
            objSave.setSBNote(item.getSBNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }

    private void AbortionDataTransfer(String HHID) {
        tmpAbortion_DataModel d = new tmpAbortion_DataModel();
        String SQL = "Select * from tmpAbortion  Where HHID='" + HHID + "'";
        List<tmpAbortion_DataModel> data = d.SelectAll(this, SQL);
        for (tmpAbortion_DataModel item : data) {
            Abortion_DataModel objSave = new Abortion_DataModel();
            objSave.setAbortionID(item.getAbortionID());
            objSave.setDeliveryID(item.getDeliveryID());
            objSave.setHHID(item.getHHID());
            objSave.setAbMomID(item.getAbMomID());
            objSave.setAbDate(item.getAbDate());
            objSave.setNAT(item.getNAT());
            objSave.setAbDateType(item.getAbDateType());
            objSave.setAbTime(item.getAbTime());
            objSave.setAbTimeType(item.getAbTimeType());
            objSave.setAbPlace(item.getAbPlace());
            objSave.setAbPlaceOth(item.getAbPlaceOth());
            objSave.setAbType(item.getAbType());
            objSave.setAbDoctor(item.getAbDoctor());
            objSave.setAbNurse(item.getAbNurse());
            objSave.setAbMidwifeParamedic(item.getAbMidwifeParamedic());
            objSave.setAbTBA(item.getAbTBA());
            objSave.setAbCHW(item.getAbCHW());
            objSave.setAbRelativeFriend(item.getAbRelativeFriend());
            objSave.setAbOther(item.getAbOther());
            objSave.setAbOthSpec(item.getAbOthSpec());
            objSave.setAbDK(item.getAbDK());
            objSave.setAbRR(item.getAbRR());
            objSave.setAbNote(item.getAbNote());
            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));
            String status = objSave.SaveUpdateData(this);
        }
    }



    //Validation Check
    @SuppressLint("Range")
    private String Process_Validation(String HH_ID)
    {
        StringBuilder response = new StringBuilder();
        String temp_sql = "";

        //Household member information is yet to be updated
        //At least one member must enter
        if(!C.Existence("select m.HHID from tmpMember m inner join tmpMemberMove mv on m.MemID=mv.MemID where m.HHID='"+ HH_ID +"' and m.isdelete=2 and length(ifnull(mv.MExType,''))=0")) {
            response.append("\n-> ").append(Global_CodeList.Get_Process_Transaction_Message(2, LANGUAGEID ));
        }

        //There must be a household head in the household
        if(!C.Existence("select m.MemID from tmpMember m inner join tmpMemberMove mv on m.MemID=mv.MemID where m.HHID='"+ HH_ID +"' and m.isdelete=2 and length(ifnull(mv.MExType,''))=0 and cast(m.Rth as int)=1")){
            response.append("\n-> ").append(Global_CodeList.Get_Process_Transaction_Message(0, LANGUAGEID ));
        }
        //Household can,t have more than one household head.
        if(C.Existence("select m.HHID,Count(*)total from tmpMember m inner join tmpMemberMove mv on m.MemID=mv.MemID where m.HHID='"+ HH_ID +"' and m.isdelete=2 and length(ifnull(mv.MExType,''))=0 and cast(m.Rth as int)=1 group by m.HHID having count(*)>1")) {
            response.append("\n-> ").append(Global_CodeList.Get_Process_Transaction_Message(1, LANGUAGEID ));
        }

        //Socio-demographic information can't be left blank
        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
            temp_sql = "Select (julianday('now')-julianday(date(a.SESVDate)))duration from (select ifnull(max(SESVDate),date('now','-"+ ProjectSetting.SES_DURATION_MONTHS*30+1 +" day'))SESVDate from tmpSES_Mali where HHID='"+ HH_ID +"' order by SESVDate desc limit 1)a where (julianday('now')-julianday(a.SESVDate))>"+ ProjectSetting.SES_DURATION_MONTHS*30;
            if(C.Existence(temp_sql)){
                response.append("\n-> ").append(Global_CodeList.Get_Process_Transaction_Message(3, LANGUAGEID ));
            }
        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
            temp_sql = "Select (julianday('now')-julianday(date(a.SESVDate)))duration from (select ifnull(max(SESVDate),date('now','-"+ ProjectSetting.SES_DURATION_MONTHS*30+1 +" day'))SESVDate from tmpSES_CrossRiver where HHID='"+ HH_ID +"' order by SESVDate desc limit 1)a where (julianday('now')-julianday(a.SESVDate))>"+ ProjectSetting.SES_DURATION_MONTHS*30;
            if(C.Existence(temp_sql)){
                response.append("\n-> ").append(Global_CodeList.Get_Process_Transaction_Message(3, LANGUAGEID ));
            }
        }
        else{
            temp_sql = "Select (julianday('now')-julianday(date(a.SESVDate)))duration from (select ifnull(max(SESVDate),date('now','-"+ ProjectSetting.SES_DURATION_MONTHS*30+1 +" day'))SESVDate from tmpSES where HHID='"+ HH_ID +"' order by SESVDate desc limit 1)a where (julianday('now')-julianday(a.SESVDate))>"+ ProjectSetting.SES_DURATION_MONTHS*30;
            if(C.Existence(temp_sql)){
                response.append("\n-> ").append(Global_CodeList.Get_Process_Transaction_Message(3, LANGUAGEID ));
            }
        }


        //Event 40/49/99/98 did not occur
        String SQL_String = "";
        SQL_String= "select m.MSlNo slno, m.Name name, " +
                "ifnull(cast(((julianday('now')-julianday(m.BDate))/365.25)as int),0)age_year, " +
                "ifnull(mv.MS,'') ms,ifnull(m.sex,'') sex,ifnull(mv.rth,'') rth, " +
                "ifnull(mv.fano,'') fano,ifnull(mv.mono,'') mono, ifnull(mv.Sp1,'') sp1, ifnull(mv.Sp2,'') sp2, ifnull(mv.Sp3,'') sp3, ifnull(mv.Sp4,'') sp4, " +
                "ifnull(ph.MemID,'')preg_his, ifnull(mv.MEnType,'')entype" +
                " from tmpMember m" +
                " inner join tmpMemberMove mv on m.MemID=mv.MemID" +
                " left outer join tmpPregHis ph on m.MemID=ph.MemID" +
                " where m.HHID='"+ HH_ID +"' and m.isdelete=2 and length(ifnull(mv.MExType,''))=0";

        Cursor cur = C.ReadData(SQL_String);
        cur.moveToFirst();
            int _age_year = 0;
            String _ms = "";
            String _sex = "";
            String _rth = "";
            String _father = "";
            String _mother = "";
            String _sp1 = "";
            String _sp2 = "";
            String _sp3 = "";
            String _sp4 = "";
            String _entype = "";
            StringBuilder _serial_name;
            List<String> _member_info_father;
            List<String> _member_info_mother;
            List<String> _member_info_spouse_1;
            List<String> _member_info_spouse_2;
            List<String> _member_info_spouse_3;
            List<String> _member_info_spouse_4;

        while (!cur.isAfterLast()) {
            _age_year = Integer.parseInt(cur.getString(cur.getColumnIndex("age_year")));
            _ms = cur.getString(cur.getColumnIndex("ms"));
            _sex = cur.getString(cur.getColumnIndex("sex"));
            _rth = cur.getString(cur.getColumnIndex("rth"));
            _father = cur.getString(cur.getColumnIndex("fano"));
            _mother = cur.getString(cur.getColumnIndex("mono"));
            _sp1 = cur.getString(cur.getColumnIndex("sp1"));
            _sp2 = cur.getString(cur.getColumnIndex("sp2"));
            _sp3 = cur.getString(cur.getColumnIndex("sp3"));
            _sp4 = cur.getString(cur.getColumnIndex("sp4"));
            _entype = cur.getString(cur.getColumnIndex("entype"));

            _serial_name = new StringBuilder();
            _serial_name.append(cur.getString(cur.getColumnIndex("slno"))).append(" - ").append(cur.getString(cur.getColumnIndex("name")));

            //Pregnancy history was not collected except temporary migration in
            if(!_entype.equals("80") && _sex.equals("2") && (_age_year>=ProjectSetting.REPRODUCTIVE_AGE_START && _age_year<=ProjectSetting.REPRODUCTIVE_AGE_END) && cur.getString(cur.getColumnIndex("preg_his")).length()==0){
                response.append("\n-> ").append(_serial_name).append(" : ").append(Global_CodeList.Get_Process_Transaction_Message(4, LANGUAGEID ));
            }

            //Marital status will be not applicable if age is below 10(ELIGIBILITY_AGE_MS_YEAR) years
            Set<String> temp_ms = new HashSet<>(Arrays.asList("66", "97", "98", "99"));
            if(_age_year < ProjectSetting.ELIGIBILITY_AGE_MS_YEAR && !temp_ms.contains(_ms)){
                response.append("\n-> ").append(_serial_name).append(" : ").append(Global_CodeList.Get_Process_Transaction_Message(5, LANGUAGEID ));
            }

            //Serial No. of father/mother will not be 00 if the relationship with the household head is son/daughter
            if((_rth.equals("06")||_rth.equals("07")) && _father.equals("00") && _mother.equals("00")){
                response.append("\n-> ").append(_serial_name).append(" : ").append(Global_CodeList.Get_Process_Transaction_Message(6, LANGUAGEID ));
            }

            //Mother
            if(!_mother.equals("00")) {
                //Get_MemberInfo: 0-Age,1-Sex,2-MS
                _member_info_mother = Get_MemberInfo(HH_ID, _mother);
                if(_member_info_mother.size()==3) {
                    //sex of the mother should be female
                    if (!_member_info_mother.get(1).equals("2")) {
                        response.append("\n-> ").append(_serial_name).append(" : ").append(Global_CodeList.Get_Process_Transaction_Message(7, LANGUAGEID ));
                    }
                    //age difference between mother and son/daughter should be greater than 10
                    //marital status of mother should not be Single/never married
                }
            }

            //Father
            if(!_father.equals("00")) {
                //Get_MemberInfo: 0-Age,1-Sex,2-MS
                _member_info_father = Get_MemberInfo(HH_ID, _father);
                if(_member_info_father.size()==3) {
                    //sex of the father should be male
                    if (!_member_info_father.get(1).equals("1")) {
                        response.append("\n-> ").append(_serial_name).append(" : ").append(Global_CodeList.Get_Process_Transaction_Message(8, LANGUAGEID ));
                    }
                    //age difference between father and son/daughter should be greater than 10
                    //marital status of father should not be Single/never married
                }
            }

            //Spouses
            if(!_sp1.equals("00") && !_sp2.equals("00") && !_sp3.equals("00") && !_sp4.equals("00"))
            {
                //Get_MemberInfo: 0-Age,1-Sex,2-MS
                //--------------------------------------------------
                _member_info_spouse_1 = Get_MemberInfo(HH_ID, _sp1);
                if(_member_info_spouse_1.size()==3){

                }
                _member_info_spouse_2 = Get_MemberInfo(HH_ID, _sp2);
                if(_member_info_spouse_2.size()==3){

                }
                _member_info_spouse_3 = Get_MemberInfo(HH_ID, _sp3);
                if(_member_info_spouse_3.size()==3){

                }
                _member_info_spouse_4 = Get_MemberInfo(HH_ID, _sp4);
                if(_member_info_spouse_4.size()==3){

                }
            }

            //spouses of household head but spouses serial number is zero //husband/wife of household head but his (husband's serial1) number is 00
            //son/daughter of household head but no father's serial
            //father of household head is present in household but father's serial is not there
            //mother of household head is present in household but mother's serial is not there

            cur.moveToNext();
        }
        cur.close();

        //Mother's serial number is not correct
        //Father's serial number is not correct

        //Education cannot be 00 for profession meritorious.
        //A live birth occurred but event 25 did not occur
        //This member's spouse is of the same sex
        //(Spouse Serial1) Number 00 of the member's spouse
        //(Serial1 of Spouse) number of member whose spouse is blank
        //Member has spouse serial number but no spouse listed

        //Member is currently married but his (husband/wife serial) number is blank
        //Check the member's relationship code and marital status code
        //Check the relationship code with household head and the serial number of the member's father
        //Check the relationship code and serial number of the member's mother with the household head
        //brother/sister of household head but father has no serial
        //brother/sister of household head but mother has no serial


        //There is a mother but there is no serial of the mother, son/daughter of the head of the relationship
        //Stillbirth occurred but event 47 did not occur

        return response.toString();
    }

    @SuppressLint("Range")
    private List<String> Get_MemberInfo(String HH_ID, String MSLNo){
        Cursor cur = C.ReadData("Select cast(((julianday('now')-julianday(BDate))/365.25)as int)age_year,sex sex, ms ms from tmpMember where HHID='"+ HH_ID +"' and MSlNo='"+ MSLNo +"'");
        cur.moveToFirst();

        List<String> get_Information = new ArrayList<String>();
        while (!cur.isAfterLast()) {
            get_Information.add(0,cur.getString(cur.getColumnIndex("age_year")));
            get_Information.add(1,cur.getString(cur.getColumnIndex("sex")));
            get_Information.add(2,cur.getString(cur.getColumnIndex("ms")));

            cur.moveToNext();
        }
        cur.close();
        return  get_Information;
    }

    private String MemNo(String HHID) {
        String M = C.ReturnSingleValue("Select cast(ifnull(max(MSlNo),0)+1 as varchar(2))MemNo from tmpMemberMove where HHID='" + HHID + "'");
        M = Global.Right("0" + M, 2);
        return M;
    }
}