
package forms_activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
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
import forms_datamodel.Compound_DataModel;
import forms_datamodel.Member_DataModel;

public class Surv_Compound extends AppCompatActivity {
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
    LinearLayout secCompoundID;
    View lineCompoundID;
    TextView VlblCompoundID;
    EditText txtCompoundID;
    LinearLayout secCompoundCode;
    View lineCompoundCode;
    TextView VlblCompoundCode;
    EditText txtCompoundCode;
    LinearLayout secCompoundName;
    View lineCompoundName;
    TextView VlblCompoundName;
    EditText txtCompoundName;
    LinearLayout secCompoundAdrs;
    View lineCompoundAdrs;
    TextView VlblCompoundAdrs;
    EditText txtCompoundAdrs;
    LinearLayout secVillID;
    View lineVillID;
    TextView VlblVillID;
    EditText txtVillID;
    LinearLayout secTotalHH;
    View lineTotalHH;
    TextView VlblTotalHH;
    EditText txtTotalHH;
    LinearLayout secActive;
    View lineActive;
    TextView VlblActive;
    EditText txtActive;
    LinearLayout secComEnDate;
    View lineComEnDate;
    TextView VlblComEnDate;
    EditText dtpComEnDate;
    LinearLayout secComExDate;
    View lineComExDate;
    TextView VlblComExDate;
    EditText dtpComExDate;
    LinearLayout secComExReason;
    View lineComExReason;
    TextView VlblComExReason;
    EditText txtComExReason;
    LinearLayout secCluster;
    View lineCluster;
    TextView VlblCluster;
    EditText txtCluster;
    LinearLayout secBlock;
    View lineBlock;
    TextView VlblBlock;
    EditText txtBlock;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;

    LinearLayout secCompoundType;
    View lineCompoundType;
    TextView VlblCompoundType;
    Spinner spnCompoundType;
    LinearLayout secCompoundTypeOth;
    View lineCompoundTypeOth;
    TextView VlblCompoundTypeOth;
    AutoCompleteTextView txtCompoundTypeOth;
    LinearLayout secCompoundStatus;
    View lineCompoundStatus;
    TextView VlblCompoundStatus;
    RadioGroup rdogrpCompoundStatus;
    RadioButton rdoCompoundStatus1;
    RadioButton rdoCompoundStatus2;
    LinearLayout secCompoundPurpose;
    View lineCompoundPurpose;
    TextView VlblCompoundPurpose;
    Spinner spnCompoundPurpose;
    LinearLayout secCompoundPurposeOth;
    View lineCompoundPurposeOth;
    TextView VlblCompoundPurposeOth;
    TextView lblTotalHH;
    TextView lblCompoundStatus;
    TextView lblCompoundPurpose;

    AutoCompleteTextView txtCompoundPurposeOth;


    TextView lblVillage;
    int MODULEID   = 0;
    int LANGUAGEID = 0;
    String TableName;

    String STARTTIME = "";
    String DEVICEID  = "";
    String ENTRYUSER = "";
    String USER_ROLE = "";
    MySharedPreferences sp;

    Bundle IDbundle;
    String COMPOUNDID = "";
    String COMPOUNDCODE = "";
    String VILLID = "";
    String LOCATION_ID = "";

    String CLUSTER = "";
    String BLOCK = "";
    String ROUND = "";

    @SuppressLint("ClickableViewAccessibility")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            setContentView(R.layout.surv_compound);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            C = new Connection(this);
            g = Global.getInstance();

            STARTTIME = g.CurrentTime24();
            DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            COMPOUNDID = IDbundle.getString("CompoundID");
            COMPOUNDCODE = IDbundle.getString("CompoundCode");
            VILLID = IDbundle.getString("VillID");
            LOCATION_ID = IDbundle.getString("locationid");

            CLUSTER = IDbundle.getString("cluster");
            BLOCK = IDbundle.getString("block");
            ROUND = IDbundle.getString("round");

            lblVillage = findViewById(R.id.lblVillage);
            lblVillage.setText(IDbundle.getString("villname"));


            TableName = "Compound";
            MODULEID = 5;
            LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
            lblHeading = (TextView)findViewById(R.id.lblHeading);

            //Label
            lblHeading.setText(ProjectSetting.COMPOUND_LABEL );

            ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Compound.this);
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

            txtCluster.setText(CLUSTER);
            txtBlock.setText(BLOCK);
            txtRnd.setText(ROUND);

            VlblCompoundCode.setText(ProjectSetting.COMPOUND_LABEL+ " Code" );
            VlblCompoundName.setText(ProjectSetting.COMPOUND_LABEL+ " Name" );
            VlblCompoundAdrs.setText(ProjectSetting.COMPOUND_LABEL+ " Address" );
            Connection.LocalizeLanguage(Surv_Compound.this, MODULEID, LANGUAGEID);


            dtpComEnDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnComEnDate"; showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });
            dtpComExDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        VariableID = "btnComExDate"; showDialog(DATE_DIALOG);
                        return true;
                    }
                    return false;
                }
            });



            //Hide all skip variables
            secCompoundTypeOth.setVisibility(View.GONE);
            lineCompoundTypeOth.setVisibility(View.GONE);
            secCompoundPurpose.setVisibility(View.GONE);
            lineCompoundPurpose.setVisibility(View.GONE);
            secCompoundPurposeOth.setVisibility(View.GONE);
            lineCompoundPurposeOth.setVisibility(View.GONE);
            secCompoundPurposeOth.setVisibility(View.GONE);
            lineCompoundPurposeOth.setVisibility(View.GONE);
            secTotalHH.setVisibility(View.GONE);
            lineTotalHH.setVisibility(View.GONE);

            if(ProjectSetting.SITE_CODE.equals(ProjectSetting.BANGLADESH_SITE1)){
                secCompoundType.setVisibility(View.GONE);
                lineCompoundType.setVisibility(View.GONE);
                secCompoundTypeOth.setVisibility(View.GONE);
                lineCompoundTypeOth.setVisibility(View.GONE);
                secCompoundStatus.setVisibility(View.GONE);
                lineCompoundStatus.setVisibility(View.GONE);
                secCompoundPurpose.setVisibility(View.GONE);
                lineCompoundPurpose.setVisibility(View.GONE);
                secCompoundPurposeOth.setVisibility(View.GONE);
                lineCompoundPurposeOth.setVisibility(View.GONE);
                secTotalHH.setVisibility(View.VISIBLE);
                lineTotalHH.setVisibility(View.VISIBLE);
                lblTotalHH.setText("4.");
            }
            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
                secCompoundType.setVisibility(View.GONE);
                lineCompoundType.setVisibility(View.GONE);
                secCompoundTypeOth.setVisibility(View.GONE);
                lineCompoundTypeOth.setVisibility(View.GONE);
                secCompoundStatus.setVisibility(View.GONE);
                lineCompoundStatus.setVisibility(View.GONE);
                secCompoundPurpose.setVisibility(View.GONE);
                lineCompoundPurpose.setVisibility(View.GONE);
                secCompoundPurposeOth.setVisibility(View.GONE);
                lineCompoundPurposeOth.setVisibility(View.GONE);
                secTotalHH.setVisibility(View.VISIBLE);
                lineTotalHH.setVisibility(View.VISIBLE);
                lblTotalHH.setText("4.");
            }
            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2)){
                secCompoundType.setVisibility(View.VISIBLE);
                lineCompoundType.setVisibility(View.VISIBLE);
                secCompoundTypeOth.setVisibility(View.GONE);
                lineCompoundTypeOth.setVisibility(View.GONE);
                secCompoundStatus.setVisibility(View.VISIBLE);
                lineCompoundStatus.setVisibility(View.VISIBLE);
                secCompoundPurpose.setVisibility(View.GONE);
                lineCompoundPurpose.setVisibility(View.GONE);
                secCompoundPurposeOth.setVisibility(View.GONE);
                lineCompoundPurposeOth.setVisibility(View.GONE);
                secTotalHH.setVisibility(View.GONE);
                VlblCompoundType.setText("Type of Structure");
                VlblCompoundStatus.setText("Structure Status");
                VlblCompoundPurpose.setText("Purpose");
            }
            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1)){
                secCompoundType.setVisibility(View.GONE);
                lineCompoundType.setVisibility(View.GONE);
                secCompoundTypeOth.setVisibility(View.GONE);
                lineCompoundTypeOth.setVisibility(View.GONE);
                secCompoundStatus.setVisibility(View.VISIBLE);
                lineCompoundStatus.setVisibility(View.VISIBLE);
                secCompoundPurpose.setVisibility(View.GONE);
                lineCompoundPurpose.setVisibility(View.GONE);
                secCompoundPurposeOth.setVisibility(View.GONE);
                lineCompoundPurposeOth.setVisibility(View.GONE);
                secTotalHH.setVisibility(View.GONE);
                VlblCompoundType.setText("Type of Structure");
                VlblCompoundStatus.setText("Structure Type");
                VlblCompoundPurpose.setText("Non-Residential Status");
                lblCompoundStatus.setText("4.");
                lblCompoundPurpose.setText("5.");
                lblTotalHH.setText("6.");

                secCluster.setVisibility(View.VISIBLE);
                lineCluster.setVisibility(View.VISIBLE);
            }

            DataSearch(COMPOUNDID);


            Button cmdSave = (Button) findViewById(R.id.cmdSave);
            cmdSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DataSave();
                }});

            Global.RoleManagement(cmdSave, MySharedPreferences.getValue(this, "userrole"));
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Compound.this, e.getMessage());
            return;
        }
    }

    private void Initialization()
    {
        try
        {
            lblCompoundStatus =findViewById(R.id.lblCompoundStatus);
            lblCompoundPurpose = findViewById(R.id.lblCompoundPurpose);
            lblTotalHH = findViewById(R.id.lblTotalHH);
            secCompoundID=(LinearLayout)findViewById(R.id.secCompoundID);
            lineCompoundID=(View)findViewById(R.id.lineCompoundID);
            VlblCompoundID=(TextView) findViewById(R.id.VlblCompoundID);
            txtCompoundID=(EditText) findViewById(R.id.txtCompoundID);
            if(COMPOUNDID.length()==0) txtCompoundID.setText(Global.Get_UUID(DEVICEID));
            else txtCompoundID.setText(COMPOUNDID);

            txtCompoundID.setEnabled(false);

            secVillID=(LinearLayout)findViewById(R.id.secVillID);
            lineVillID=(View)findViewById(R.id.lineVillID);
            VlblVillID=(TextView) findViewById(R.id.VlblVillID);
            txtVillID=(EditText) findViewById(R.id.txtVillID);
            txtVillID.setText(VILLID);
            txtVillID.setEnabled(false);

            secCompoundCode=(LinearLayout)findViewById(R.id.secCompoundCode);
            lineCompoundCode=(View)findViewById(R.id.lineCompoundCode);
            VlblCompoundCode=(TextView) findViewById(R.id.VlblCompoundCode);
            txtCompoundCode=(EditText) findViewById(R.id.txtCompoundCode);

            if(COMPOUNDCODE.length()==0) {
                if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)){
                    txtCompoundCode.setText(Get_COMPOUND_ID_Nigeria_Crossriver(VILLID));

                    //check compound/house available or not for this data collectors id's
                    /*if(!C.Existence("select * from compound where VillID='"+ VILLID +"' and EntryUser='"+  ENTRYUSER+"' limit 1"))
                    {
                        CompouneID_Form(IDbundle.getString("villname"));
                    }else {
                        txtCompoundCode.setText(Get_COMPOUND_ID_Nigeria_Crossriver(VILLID));
                    }*/
                }
                else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2)){
                    //check compound/house available or not for this data collectors id's
                    if(!C.Existence("select * from compound where VillID='"+ VILLID +"' and EntryUser='"+  ENTRYUSER+"' limit 1"))
                    {
                        CompouneID_Form(IDbundle.getString("villname"));
                    }else {
                        txtCompoundCode.setText(Get_COMPOUND_ID_Nigeria_Buochi(VILLID));
                    }
                }
                else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1)){
                    txtCompoundCode.setText(Get_COMPOUND_ID_SierraLeone(VILLID));

                    //check compound/house available or not for this data collectors id's
                    /*if(!C.Existence("select * from compound where VillID='"+ VILLID +"' and EntryUser='"+  ENTRYUSER+"' limit 1"))
                    {
                        CompouneID_Form(IDbundle.getString("villname"));
                    }else {
                        txtCompoundCode.setText(Get_COMPOUND_ID_SierraLeone(VILLID));
                    }*/
                }
                else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
                    txtCompoundCode.setText(Get_COMPOUND_ID_Mali(VILLID));
                }
                else {
                    txtCompoundCode.setText(Get_COMPOUND_ID(DEVICEID, VILLID));
                }
            }
            else {
                txtCompoundCode.setText(COMPOUNDCODE);
            }

            txtCompoundCode.setEnabled(false);

            secCompoundName=(LinearLayout)findViewById(R.id.secCompoundName);
            lineCompoundName=(View)findViewById(R.id.lineCompoundName);
            VlblCompoundName=(TextView) findViewById(R.id.VlblCompoundName);
            txtCompoundName=(EditText) findViewById(R.id.txtCompoundName);
            secCompoundAdrs=(LinearLayout)findViewById(R.id.secCompoundAdrs);
            lineCompoundAdrs=(View)findViewById(R.id.lineCompoundAdrs);
            VlblCompoundAdrs=(TextView) findViewById(R.id.VlblCompoundAdrs);
            txtCompoundAdrs=(EditText) findViewById(R.id.txtCompoundAdrs);


            //------------------------------------------------------------------------------------------
            secCompoundType=(LinearLayout)findViewById(R.id.secCompoundType);
            lineCompoundType=(View)findViewById(R.id.lineCompoundType);
            VlblCompoundType=(TextView) findViewById(R.id.VlblCompoundType);
            spnCompoundType=(Spinner) findViewById(R.id.spnCompoundType);
            List<String> listCompoundType = new ArrayList<String>();

            listCompoundType.add("");
            listCompoundType.add("1-Bungalow");
            listCompoundType.add("2-Traditional Hut House");
            listCompoundType.add("3-1 Story Building");
            listCompoundType.add("4-2 Story Building");
            listCompoundType.add("5-3 Story Building");
            listCompoundType.add("6-More than 3 Storey Building");
            listCompoundType.add("7-Make-Shift");
            listCompoundType.add("8-Shed/Container/Silos");
            listCompoundType.add("9-Bungalow & Multi-storey Building");
            listCompoundType.add("77-Others");
            spnCompoundType.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listCompoundType)));

            spnCompoundType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnCompoundType.getSelectedItem().toString().length() != 0)
                    {
                        spnData = Connection.SelectedSpinnerValue(spnCompoundType.getSelectedItem().toString(), "-");
                    }
                    if(spnData.equalsIgnoreCase("77"))
                    {
                        secCompoundTypeOth.setVisibility(View.VISIBLE);
                        lineCompoundTypeOth.setVisibility(View.VISIBLE);

                    }
                    else
                    {
                        secCompoundTypeOth.setVisibility(View.GONE);
                        lineCompoundTypeOth.setVisibility(View.GONE);
                        txtCompoundTypeOth.setText("");
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secCompoundTypeOth=(LinearLayout)findViewById(R.id.secCompoundTypeOth);
            lineCompoundTypeOth=(View)findViewById(R.id.lineCompoundTypeOth);
            VlblCompoundTypeOth=(TextView) findViewById(R.id.VlblCompoundTypeOth);
            txtCompoundTypeOth=(AutoCompleteTextView) findViewById(R.id.txtCompoundTypeOth);
            txtCompoundTypeOth.setAdapter(C.getArrayAdapter("Select distinct CompoundTypeOth from "+ TableName +" order by CompoundTypeOth"));

            txtCompoundTypeOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

                }
            });
            txtCompoundTypeOth.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (txtCompoundTypeOth.getRight() - txtCompoundTypeOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            ((EditText)v).setText("");
                            return true;
                        }
                    }
                    return false;
                }
            });
            secCompoundStatus=(LinearLayout)findViewById(R.id.secCompoundStatus);
            lineCompoundStatus=(View)findViewById(R.id.lineCompoundStatus);
            VlblCompoundStatus = (TextView) findViewById(R.id.VlblCompoundStatus);
            rdogrpCompoundStatus = (RadioGroup) findViewById(R.id.rdogrpCompoundStatus);
            rdoCompoundStatus1 = (RadioButton) findViewById(R.id.rdoCompoundStatus1);
            rdoCompoundStatus2 = (RadioButton) findViewById(R.id.rdoCompoundStatus2);
            rdogrpCompoundStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpCompoundStatus = new String[] {"1","2"};
                    for (int i = 0; i < rdogrpCompoundStatus.getChildCount(); i++)
                    {
                        rb = (RadioButton)rdogrpCompoundStatus.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpCompoundStatus[i];
                    }

                    if(rbData.equalsIgnoreCase("2"))
                    {
                        secCompoundPurpose.setVisibility(View.VISIBLE);
                        lineCompoundPurpose.setVisibility(View.VISIBLE);
                        secTotalHH.setVisibility(View.GONE);
                        lineTotalHH.setVisibility(View.GONE);
                        txtTotalHH.setText("");
                    }
                    else
                    {
                        secCompoundPurpose.setVisibility(View.GONE);
                        lineCompoundPurpose.setVisibility(View.GONE);
                        spnCompoundPurpose.setSelection(0);
                        secCompoundPurposeOth.setVisibility(View.GONE);
                        lineCompoundPurposeOth.setVisibility(View.GONE);
                        txtCompoundPurposeOth.setText("");
                        secTotalHH.setVisibility(View.VISIBLE);
                        lineTotalHH.setVisibility(View.VISIBLE);
                    }

                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
            secCompoundPurpose=(LinearLayout)findViewById(R.id.secCompoundPurpose);
            lineCompoundPurpose=(View)findViewById(R.id.lineCompoundPurpose);
            VlblCompoundPurpose=(TextView) findViewById(R.id.VlblCompoundPurpose);
            spnCompoundPurpose=(Spinner) findViewById(R.id.spnCompoundPurpose);
            List<String> listCompoundPurpose = new ArrayList<String>();

            if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2)) {
                listCompoundPurpose.add("");
                listCompoundPurpose.add("1-Uncompleted");
                listCompoundPurpose.add("2-Not Occupied");
                listCompoundPurpose.add("3-Dilapidated ");
                listCompoundPurpose.add("4-Religious House (E.g Mosque or Church)");
                listCompoundPurpose.add("5-School or Hospital");
                listCompoundPurpose.add("6-Office/Shop/Warehouse");
                listCompoundPurpose.add("77-Other Building or Structure");
            }
            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1)) {
                listCompoundPurpose.add("");
                listCompoundPurpose.add("1-Health Center");
                listCompoundPurpose.add("2-Church");
                listCompoundPurpose.add("3-Mosque");
                listCompoundPurpose.add("4-School");
                listCompoundPurpose.add("5-Court Barry/Local Court");
                listCompoundPurpose.add("6-Community Center ");
                listCompoundPurpose.add("7-Market");
                listCompoundPurpose.add("8-Store ");
                listCompoundPurpose.add("77-Other");
            }else{
                listCompoundPurpose.add("");
                listCompoundPurpose.add("1-Uncompleted");
                listCompoundPurpose.add("2-Not Occupied");
                listCompoundPurpose.add("3-Dilapidated ");
                listCompoundPurpose.add("4-Religious House (E.g Mosque or Church)");
                listCompoundPurpose.add("5-School or Hospital");
                listCompoundPurpose.add("6-Office/Shop/Warehouse");
                listCompoundPurpose.add("77-Other Building or Structure");
            }
            spnCompoundPurpose.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listCompoundPurpose)));

            spnCompoundPurpose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnCompoundPurpose.getSelectedItem().toString().length() != 0)
                    {
                        spnData = Connection.SelectedSpinnerValue(spnCompoundPurpose.getSelectedItem().toString(), "-");
                    }
                    if(spnData.equalsIgnoreCase("77"))
                    {
                        secCompoundPurposeOth.setVisibility(View.VISIBLE);
                        lineCompoundPurposeOth.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        secCompoundPurposeOth.setVisibility(View.GONE);
                        lineCompoundPurposeOth.setVisibility(View.GONE);
                        txtCompoundPurposeOth.setText("");
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
            secCompoundPurposeOth=(LinearLayout)findViewById(R.id.secCompoundPurposeOth);
            lineCompoundPurposeOth=(View)findViewById(R.id.lineCompoundPurposeOth);
            VlblCompoundPurposeOth=(TextView) findViewById(R.id.VlblCompoundPurposeOth);
            txtCompoundPurposeOth=(AutoCompleteTextView) findViewById(R.id.txtCompoundPurposeOth);
            txtCompoundPurposeOth.setAdapter(C.getArrayAdapter("Select distinct CompoundPurposeOth from "+ TableName +" order by CompoundPurposeOth"));

            txtCompoundPurposeOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

                }
            });
            txtCompoundPurposeOth.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;

                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (txtCompoundPurposeOth.getRight() - txtCompoundPurposeOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            ((EditText)v).setText("");
                            return true;
                        }
                    }
                    return false;
                }
            });



            secTotalHH=(LinearLayout)findViewById(R.id.secTotalHH);
            lineTotalHH=(View)findViewById(R.id.lineTotalHH);
            VlblTotalHH=(TextView) findViewById(R.id.VlblTotalHH);
            txtTotalHH=(EditText) findViewById(R.id.txtTotalHH);
            secActive=(LinearLayout)findViewById(R.id.secActive);
            lineActive=(View)findViewById(R.id.lineActive);
            VlblActive=(TextView) findViewById(R.id.VlblActive);
            txtActive=(EditText) findViewById(R.id.txtActive);
            secComEnDate=(LinearLayout)findViewById(R.id.secComEnDate);
            lineComEnDate=(View)findViewById(R.id.lineComEnDate);
            VlblComEnDate=(TextView) findViewById(R.id.VlblComEnDate);
            dtpComEnDate=(EditText) findViewById(R.id.dtpComEnDate);
            dtpComEnDate.setText(Global.DateNowDMY());
            secComExDate=(LinearLayout)findViewById(R.id.secComExDate);
            lineComExDate=(View)findViewById(R.id.lineComExDate);
            VlblComExDate=(TextView) findViewById(R.id.VlblComExDate);
            dtpComExDate=(EditText) findViewById(R.id.dtpComExDate);
            secComExReason=(LinearLayout)findViewById(R.id.secComExReason);
            lineComExReason=(View)findViewById(R.id.lineComExReason);
            VlblComExReason=(TextView) findViewById(R.id.VlblComExReason);
            txtComExReason=(EditText) findViewById(R.id.txtComExReason);
            secCluster=(LinearLayout)findViewById(R.id.secCluster);
            lineCluster=(View)findViewById(R.id.lineCluster);
            VlblCluster=(TextView) findViewById(R.id.VlblCluster);
            txtCluster=(EditText) findViewById(R.id.txtCluster);
            secBlock=(LinearLayout)findViewById(R.id.secBlock);
            lineBlock=(View)findViewById(R.id.lineBlock);
            VlblBlock=(TextView) findViewById(R.id.VlblBlock);
            txtBlock=(EditText) findViewById(R.id.txtBlock);
            secRnd=(LinearLayout)findViewById(R.id.secRnd);
            lineRnd=(View)findViewById(R.id.lineRnd);
            VlblRnd=(TextView) findViewById(R.id.VlblRnd);
            txtRnd=(EditText) findViewById(R.id.txtRnd);
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Compound.this, e.getMessage());
            return;
        }
    }

    private void CompouneID_Form(String VILL_NAME)
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.first_number_for_compound);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        final EditText txtCompound_Code;
        final TextView lblVillage;
        final TextView lblHeading;
        final TextView VlblCompoundCode;

        txtCompound_Code=(EditText)dialog.findViewById(R.id.txtCompound_Code);
        lblHeading=(TextView) dialog.findViewById(R.id.lblHeading);
        VlblCompoundCode=(TextView) dialog.findViewById(R.id.VlblCompoundCode);
        lblHeading.setText(ProjectSetting.COMPOUND_LABEL + " Form");
        VlblCompoundCode.setText("First Number of "+ ProjectSetting.COMPOUND_LABEL);

        lblVillage=(TextView) dialog.findViewById(R.id.lblVillage);
        lblVillage.setText(VILL_NAME);
        ImageButton cmdBack = (ImageButton) dialog.findViewById(R.id.cmdBack);
        cmdBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }});

        Button cmdSave = (Button)dialog.findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(txtCompound_Code.getText().toString().length()!=3){
                    Connection.MessageBox(Surv_Compound.this,"Length should be 3 digit.");
                    txtCompound_Code.requestFocus();
                }
                else if(txtCompound_Code.getText().toString().equals("000")){
                    Connection.MessageBox(Surv_Compound.this,"Code should not be zero(000).");
                    txtCompound_Code.requestFocus();
                }
                else if(C.Existence("Select * from Compound where CompoundCode='"+ VILLID + txtCompound_Code.getText().toString() +"'")){
                    Connection.MessageBox(Surv_Compound.this,"Generated code is already in the database.");
                    txtCompound_Code.requestFocus();
                }
                else {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_Compound.this);
                    adb.setTitle("Confirm");
                    adb.setIcon(R.drawable.favicon);
                    adb.setMessage("Please confirm if this is the correct number[Yes/No]?");
                    adb.setNegativeButton("No", null);
                    adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog1, int which) {
                            if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1)) {
                                txtCompoundCode.setText("CHS"+VILLID + txtCompound_Code.getText().toString());
                            }else{
                                txtCompoundCode.setText(VILLID + txtCompound_Code.getText().toString());
                            }
                            txtCompoundName.requestFocus();
                            dialog1.dismiss();
                            dialog.dismiss();
                        }
                    });
                    adb.show();
                }
            }
        });

        dialog.show();
    }

    private void DataSave()
    {
        try
        {
            String ValidationMSG = ValidationCheck();
            if(ValidationMSG.length()>0)
            {
                Connection.MessageBox(Surv_Compound.this, ValidationMSG);
                return;
            }

            String SQL = "";
            RadioButton rb;

            Compound_DataModel objSave = new Compound_DataModel();
            objSave.setCompoundID(txtCompoundID.getText().toString());
            objSave.setCompoundCode(txtCompoundCode.getText().toString());
            objSave.setCompoundName(txtCompoundName.getText().toString());
            objSave.setCompoundAdrs(txtCompoundAdrs.getText().toString());
            objSave.setVillID(txtVillID.getText().toString());

            objSave.setCompoundType(spnCompoundType.getSelectedItemPosition() == 0 ? "" : spnCompoundType.getSelectedItem().toString().split("-")[0]);
            objSave.setCompoundTypeOth(txtCompoundTypeOth.getText().toString());
            String[] d_rdogrpCompoundStatus = new String[] {"1","2"};
            objSave.setCompoundStatus("");
            for (int i = 0; i < rdogrpCompoundStatus.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpCompoundStatus.getChildAt(i);
                if (rb.isChecked()) objSave.setCompoundStatus(d_rdogrpCompoundStatus[i]);
            }

            objSave.setCompoundPurpose(spnCompoundPurpose.getSelectedItemPosition() == 0 ? "" : spnCompoundPurpose.getSelectedItem().toString().split("-")[0]);
            objSave.setCompoundPurposeOth(txtCompoundPurposeOth.getText().toString());

            objSave.setTotalHH(txtTotalHH.getText().toString().length()==0?"0": String.valueOf(Integer.parseInt(txtTotalHH.getText().toString())));

            objSave.setActive("1");
            objSave.setComEnDate(dtpComEnDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpComEnDate.getText().toString()) : dtpComEnDate.getText().toString());
            objSave.setComExDate("");
            objSave.setComExReason("");
            objSave.setCluster(txtCluster.getText().toString());
            objSave.setBlock(txtBlock.getText().toString());
            objSave.setRnd(txtRnd.getText().toString());
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
                Toast.makeText(Surv_Compound.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
                //Connection.MessageBox(Surv_Compound.this, "Saved Successfully");
                finish();
            }
            else{
                Connection.MessageBox(Surv_Compound.this, status);
                return;
            }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Compound.this, e.getMessage());
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
            if(txtCompoundID.getText().toString().length()==0 & secCompoundID.isShown())
            {
                ValidationMsg += "\n"+ VlblCompoundID.getText().toString() + " Required field: "+ VlblCompoundID.getText().toString();
                secCompoundID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }


            if(txtCompoundID.getText().toString().length()==0 & secCompoundID.isShown())
            {
                ValidationMsg += "\nRequired field: Internal Compound ID.";
                secCompoundID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtCompoundCode.getText().toString().length()==0 & secCompoundCode.isShown())
            {
                ValidationMsg += "\nRequired field: Compound Code.";
                secCompoundCode.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
         /*if(C.Existence("Select * from Compound where CompoundCode='"+ txtCompoundCode.getText().toString() +"'")){
             ValidationMsg += "\nGenerated compound/house code is already in the database.";
             secCompoundCode.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }*/
            /*if(txtCompoundName.getText().toString().length()==0 & secCompoundName.isShown())
            {
                ValidationMsg += "\nRequired field: Compound Name.";
                secCompoundName.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }*/
            /*if(txtCompoundAdrs.getText().toString().length()==0 & secCompoundAdrs.isShown())
            {
                ValidationMsg += "\nRequired field: Compound Address.";
                secCompoundAdrs.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }*/
            if(txtVillID.getText().toString().length()==0 & secVillID.isShown())
            {
                ValidationMsg += "\nRequired field: village ID.";
                secVillID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }


            if(spnCompoundType.getSelectedItemPosition()==0  & secCompoundType.isShown())
            {
                ValidationMsg += "\n4. Required field: Types of Structure.";
                secCompoundType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtCompoundTypeOth.getText().toString().length()==0 & secCompoundTypeOth.isShown())
            {
                ValidationMsg += "\nRequired field: Others Specify.";
                secCompoundTypeOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(!rdoCompoundStatus1.isChecked() & !rdoCompoundStatus2.isChecked() & secCompoundStatus.isShown())
            {
                ValidationMsg += "\nRequired field: Structure/ Building Status.";
                secCompoundStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(spnCompoundPurpose.getSelectedItemPosition()==0  & secCompoundPurpose.isShown())
            {
                ValidationMsg += "\nRequired field: Purpose of the Building.";
                secCompoundPurpose.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtCompoundPurposeOth.getText().toString().length()==0 & secCompoundPurposeOth.isShown())
            {
                ValidationMsg += "\nRequired field: Others Specify.";
                secCompoundPurposeOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }


            if(txtTotalHH.getText().toString().length()==0 & secTotalHH.isShown())
            {
                ValidationMsg += "\nRequired field: Total Household.";
                secTotalHH.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtActive.getText().toString().length()==0 & secActive.isShown())
            {
                ValidationMsg += "\nRequired field: Active.";
                secActive.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpComEnDate.getText().toString());
            if(DV.length()!=0 & secComEnDate.isShown())
            {
                ValidationMsg += "\nRequired field/Not a valid date format: Compound Enroll Date.";
                secComEnDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            DV = Global.DateValidate(dtpComExDate.getText().toString());
            if(DV.length()!=0 & secComExDate.isShown())
            {
                ValidationMsg += "\nRequired field/Not a valid date format: Compound Exit Date.";
                secComExDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtComExReason.getText().toString().length()==0 & secComExReason.isShown())
            {
                ValidationMsg += "\nRequired field: Compound Exit Reason.";
                secComExReason.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtCluster.getText().toString().length()==0 & secCluster.isShown())
            {
                ValidationMsg += "\nRequired field: Cluster.";
                secCluster.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtBlock.getText().toString().length()==0 & secBlock.isShown())
            {
                ValidationMsg += "\nRequired field: Block.";
                secBlock.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            }
            if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
            {
                ValidationMsg += "\nRequired field: Round.";
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
            secCompoundID.setBackgroundColor(Color.WHITE);
            secCompoundCode.setBackgroundColor(Color.WHITE);
            secCompoundName.setBackgroundColor(Color.WHITE);
            secCompoundAdrs.setBackgroundColor(Color.WHITE);
            secVillID.setBackgroundColor(Color.WHITE);

            secCompoundType.setBackgroundColor(Color.WHITE);
            secCompoundTypeOth.setBackgroundColor(Color.WHITE);
            secCompoundStatus.setBackgroundColor(Color.WHITE);
            secCompoundPurpose.setBackgroundColor(Color.WHITE);
            secCompoundPurposeOth.setBackgroundColor(Color.WHITE);

            secTotalHH.setBackgroundColor(Color.WHITE);
            secActive.setBackgroundColor(Color.WHITE);
            secComEnDate.setBackgroundColor(Color.WHITE);
            secComExDate.setBackgroundColor(Color.WHITE);
            secComExReason.setBackgroundColor(Color.WHITE);
            secCluster.setBackgroundColor(Color.WHITE);
            secBlock.setBackgroundColor(Color.WHITE);
            secRnd.setBackgroundColor(Color.WHITE);
        }
        catch(Exception  e)
        {
        }
    }

    private void DataSearch(String CompoundID)
    {
        try
        {
            RadioButton rb;
            Compound_DataModel d = new Compound_DataModel();
            String SQL = "Select * from "+ TableName +"  Where CompoundID='"+ CompoundID +"'";
            List<Compound_DataModel> data = d.SelectAll(this, SQL);
            for(Compound_DataModel item : data){
                txtCompoundID.setText(item.getCompoundID());
                txtCompoundCode.setText(item.getCompoundCode());
                txtCompoundName.setText(item.getCompoundName());
                txtCompoundAdrs.setText(item.getCompoundAdrs());
                txtVillID.setText(item.getVillID());

                spnCompoundType.setSelection(Global.SpinnerItemPositionAnyLength(spnCompoundType, String.valueOf(item.getCompoundType())));
                txtCompoundTypeOth.setText(item.getCompoundTypeOth());
                String[] d_rdogrpCompoundStatus = new String[] {"1","2"};
                for (int i = 0; i < d_rdogrpCompoundStatus.length; i++)
                {
                    if (String.valueOf(item.getCompoundStatus()).equals(String.valueOf(d_rdogrpCompoundStatus[i])))
                    {
                        rb = (RadioButton)rdogrpCompoundStatus.getChildAt(i);
                        rb.setChecked(true);
                    }
                }
                spnCompoundPurpose.setSelection(Global.SpinnerItemPositionAnyLength(spnCompoundPurpose, String.valueOf(item.getCompoundPurpose())));
                txtCompoundPurposeOth.setText(item.getCompoundPurposeOth());

                txtTotalHH.setText(item.getTotalHH());
                txtActive.setText(item.getActive());
                dtpComEnDate.setText(item.getComEnDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getComEnDate()));
                dtpComExDate.setText(item.getComExDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getComExDate()));
                txtComExReason.setText(item.getComExReason());
                txtCluster.setText(item.getCluster());
                txtBlock.setText(item.getBlock());
                txtRnd.setText(item.getRnd());
            }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_Compound.this, e.getMessage());
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


            dtpDate = (EditText)findViewById(R.id.dtpComEnDate);
            if (VariableID.equals("btnComEnDate"))
            {
                dtpDate = (EditText)findViewById(R.id.dtpComEnDate);
            }
            else if (VariableID.equals("btnComExDate"))
            {
                dtpDate = (EditText)findViewById(R.id.dtpComExDate);
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

    private String CompSerial(String VillID)
    {
        String SL = C.ReturnSingleValue("Select (ifnull(max(cast(CompoundCode as int)),0)+1)SL from Compound where VillID='"+ VillID +"'"); //where ParticipantID='"+ ParticipantID +"'");
        return Global.Right("0000"+SL,5);
    }


    // turning off the GPS if its in on state. to avoid the battery drain.
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    public String Get_COMPOUND_ID_Nigeria_Crossriver(String LocationID)
    {
        String MaxID = C.ReturnSingleValue("select substr('000'||cast((ifnull(max(cast(substr(CompoundCode,8,3) as int)),0)+1)as varchar(5)),-3) maxid from Compound where VillID='"+ LocationID +"'");
        return LocationID + MaxID;
    }

    public String Get_COMPOUND_ID_Nigeria_Buochi(String LocationID)
    {
        String MaxID = C.ReturnSingleValue("select substr('000'||cast((ifnull(max(cast(substr(CompoundCode,10,3) as int)),0)+1)as varchar(5)),-3) maxid from Compound where EntryUser='"+ ENTRYUSER +"' and VillID='"+ LocationID +"'");
        return LocationID + MaxID;
    }

    public String Get_COMPOUND_ID_SierraLeone(String LocationID)
    {
        String MaxID = C.ReturnSingleValue("select substr('000'||cast((ifnull(max(cast(substr(CompoundCode,7,3) as int)),0)+1)as varchar(5)),-3) maxid from Compound where VillID='"+ LocationID +"'");
        return "CHS"+LocationID + MaxID;
    }

    public String Get_COMPOUND_ID_Mali(String LocationID)
    {
        String MaxID = C.ReturnSingleValue("select substr('000'||cast((ifnull(max(cast(substr(CompoundCode,4,4) as int)),0)+1)as varchar(5)),-4) maxid from Compound where VillID='"+ LocationID +"'");
        return LocationID + MaxID;
    }

    public String Get_COMPOUND_ID(String DeviceID, String LocationID)
    {
        String MaxID = "0000000000"+C.ReturnSingleValue("select Cast((ifnull(max(cast(CompoundCode as numeric(15))),0)+1) as varchar(15)) maxid from Compound where EntryUser='"+ ENTRYUSER +"' and VillID='"+ LocationID +"'");
        return ENTRYUSER + Global.Right(MaxID,5);
    }
}