
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
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.content.ContextCompat;

 import org.icddrb.champs_lc_dss.R;
 import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

 import forms_datamodel.SES_DataModel;
 import Utility.*;
 import Common.*;

 public class SES extends AppCompatActivity {
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
    LinearLayout seclblH1;
    View linelblH1;
    LinearLayout secHHID;
    View lineHHID;
    TextView VlblHHID;
    EditText txtHHID;
    LinearLayout secSESNo;
    View lineSESNo;
    TextView VlblSESNo;
    EditText txtSESNo;
    LinearLayout secSESVDate;
    View lineSESVDate;
    TextView VlblSESVDate;
    EditText dtpSESVDate;
    LinearLayout secSESVStatus;
    View lineSESVStatus;
    TextView VlblSESVStatus;
    RadioGroup rdogrpSESVStatus;
    RadioButton rdoSESVStatus1;
    RadioButton rdoSESVStatus2;
    RadioButton rdoSESVStatus3;
    RadioButton rdoSESVStatus4;
    LinearLayout secSESVStatusOth;
    View lineSESVStatusOth;
    TextView VlblSESVStatusOth;
    EditText txtSESVStatusOth;
    LinearLayout secRnd;
    View lineRnd;
    TextView VlblRnd;
    EditText txtRnd;
    LinearLayout secWSDrink;
    View lineWSDrink;
    TextView VlblWSDrink;
    Spinner spnWSDrink;
    LinearLayout secWSDrinkOth;
    View lineWSDrinkOth;
    TextView VlblWSDrinkOth;
    AutoCompleteTextView txtWSDrinkOth;
    LinearLayout secToilet;
    View lineToilet;
    TextView VlblToilet;
    Spinner spnToilet;
    LinearLayout secToiletOth;
    View lineToiletOth;
    TextView VlblToiletOth;
    AutoCompleteTextView txtToiletOth;
    LinearLayout secToiletShrd;
    View lineToiletShrd;
    TextView VlblToiletShrd;
    RadioGroup rdogrpToiletShrd;
    RadioButton rdoToiletShrd1;
    RadioButton rdoToiletShrd2;
    LinearLayout secToiletUseNo;
    View lineToiletUseNo;
    TextView VlblToiletUseNo;
    EditText txtToiletUseNo;
    LinearLayout secToiletUseNoDk;
    View lineToiletUseNoDk;
    TextView VlblToiletUseNoDk;
    RadioGroup rdogrpToiletUseNoDk;
    RadioButton rdoToiletUseNoDk1;
    RadioButton rdoToiletUseNoDk2;
    LinearLayout secToiletLoc;
    View lineToiletLoc;
    TextView VlblToiletLoc;
    RadioGroup rdogrpToiletLoc;
    RadioButton rdoToiletLoc1;
    RadioButton rdoToiletLoc2;
    RadioButton rdoToiletLoc3;
    LinearLayout secCookDvc;
    View lineCookDvc;
    TextView VlblCookDvc;
    Spinner spnCookDvc;
    LinearLayout secCookDvcOth;
    View lineCookDvcOth;
    TextView VlblCookDvcOth;
    AutoCompleteTextView txtCookDvcOth;
    LinearLayout secCookFuel;
    View lineCookFuel;
    TextView VlblCookFuel;
    Spinner spnCookFuel;
    LinearLayout secCookFuelOth;
    View lineCookFuelOth;
    TextView VlblCookFuelOth;
    AutoCompleteTextView txtCookFuelOth;
    LinearLayout secCookPlc;
    View lineCookPlc;
    TextView VlblCookPlc;
    Spinner spnCookPlc;
    LinearLayout secCookPlcOth;
    View lineCookPlcOth;
    TextView VlblCookPlcOth;
    AutoCompleteTextView txtCookPlcOth;
    LinearLayout secFloor;
    View lineFloor;
    TextView VlblFloor;
    Spinner spnFloor;
    LinearLayout secFloorOth;
    View lineFloorOth;
    TextView VlblFloorOth;
    AutoCompleteTextView txtFloorOth;
    LinearLayout secRoof;
    View lineRoof;
    TextView VlblRoof;
    Spinner spnRoof;
    LinearLayout secRoofOth;
    View lineRoofOth;
    TextView VlblRoofOth;
    AutoCompleteTextView txtRoofOth;
    LinearLayout secWall;
    View lineWall;
    TextView VlblWall;
    Spinner spnWall;
    LinearLayout secWallOth;
    View lineWallOth;
    TextView VlblWallOth;
    AutoCompleteTextView txtWallOth;
    LinearLayout secRoomSleep;
    View lineRoomSleep;
    TextView VlblRoomSleep;
    EditText txtRoomSleep;
    LinearLayout secRoomSleepDk;
    View lineRoomSleepDk;
    TextView VlblRoomSleepDk;
    RadioGroup rdogrpRoomSleepDk;
    RadioButton rdoRoomSleepDk1;
    RadioButton rdoRoomSleepDk2;
    LinearLayout secHomesteadAny;
    View lineHomesteadAny;
    TextView VlblHomesteadAny;
    RadioGroup rdogrpHomesteadAny;
    RadioButton rdoHomesteadAny1;
    RadioButton rdoHomesteadAny2;
    RadioButton rdoHomesteadAny3;
    RadioButton rdoHomesteadAny4;
    LinearLayout secOthLand;
    View lineOthLand;
    TextView VlblOthLand;
    RadioGroup rdogrpOthLand;
    RadioButton rdoOthLand1;
    RadioButton rdoOthLand2;
    RadioButton rdoOthLand3;
    RadioButton rdoOthLand4;
    LinearLayout seclblH12;
    View linelblH12;
    LinearLayout seclblH121;
    View linelblH121;
    LinearLayout secElectricity;
    View lineElectricity;
    TextView VlblElectricity;
    RadioGroup rdogrpElectricity;
    RadioButton rdoElectricity1;
    RadioButton rdoElectricity2;
    LinearLayout secHeater;
    View lineHeater;
    TextView VlblHeater;
    RadioGroup rdogrpHeater;
    RadioButton rdoHeater1;
    RadioButton rdoHeater2;
    LinearLayout secAC;
    View lineAC;
    TextView VlblAC;
    RadioGroup rdogrpAC;
    RadioButton rdoAC1;
    RadioButton rdoAC2;
    LinearLayout secElecFan;
    View lineElecFan;
    TextView VlblElecFan;
    RadioGroup rdogrpElecFan;
    RadioButton rdoElecFan1;
    RadioButton rdoElecFan2;
    LinearLayout secLantern;
    View lineLantern;
    TextView VlblLantern;
    RadioGroup rdogrpLantern;
    RadioButton rdoLantern1;
    RadioButton rdoLantern2;
    LinearLayout secLamp;
    View lineLamp;
    TextView VlblLamp;
    RadioGroup rdogrpLamp;
    RadioButton rdoLamp1;
    RadioButton rdoLamp2;
    LinearLayout secMatt;
    View lineMatt;
    TextView VlblMatt;
    RadioGroup rdogrpMatt;
    RadioButton rdoMatt1;
    RadioButton rdoMatt2;
    LinearLayout secBed;
    View lineBed;
    TextView VlblBed;
    RadioGroup rdogrpBed;
    RadioButton rdoBed1;
    RadioButton rdoBed2;
    LinearLayout secChair;
    View lineChair;
    TextView VlblChair;
    RadioGroup rdogrpChair;
    RadioButton rdoChair1;
    RadioButton rdoChair2;
    LinearLayout secSofa;
    View lineSofa;
    TextView VlblSofa;
    RadioGroup rdogrpSofa;
    RadioButton rdoSofa1;
    RadioButton rdoSofa2;
    LinearLayout secTables;
    View lineTables;
    TextView VlblTables;
    RadioGroup rdogrpTables;
    RadioButton rdoTables1;
    RadioButton rdoTables2;
    LinearLayout secWatch;
    View lineWatch;
    TextView VlblWatch;
    RadioGroup rdogrpWatch;
    RadioButton rdoWatch1;
    RadioButton rdoWatch2;
    LinearLayout secWMachine;
    View lineWMachine;
    TextView VlblWMachine;
    RadioGroup rdogrpWMachine;
    RadioButton rdoWMachine1;
    RadioButton rdoWMachine2;
    LinearLayout secIron;
    View lineIron;
    TextView VlblIron;
    RadioGroup rdogrpIron;
    RadioButton rdoIron1;
    RadioButton rdoIron2;
    LinearLayout secBooth;
    View lineBooth;
    TextView VlblBooth;
    RadioGroup rdogrpBooth;
    RadioButton rdoBooth1;
    RadioButton rdoBooth2;
    LinearLayout secSMachine;
    View lineSMachine;
    TextView VlblSMachine;
    RadioGroup rdogrpSMachine;
    RadioButton rdoSMachine1;
    RadioButton rdoSMachine2;
    LinearLayout secGenerator;
    View lineGenerator;
    TextView VlblGenerator;
    RadioGroup rdogrpGenerator;
    RadioButton rdoGenerator1;
    RadioButton rdoGenerator2;
    LinearLayout secMosquito;
    View lineMosquito;
    TextView VlblMosquito;
    RadioGroup rdogrpMosquito;
    RadioButton rdoMosquito1;
    RadioButton rdoMosquito2;
    LinearLayout seclblH12a;
    View linelblH12a;
    LinearLayout secInternet;
    View lineInternet;
    TextView VlblInternet;
    RadioGroup rdogrpInternet;
    RadioButton rdoInternet1;
    RadioButton rdoInternet2;
    LinearLayout secSatellite;
    View lineSatellite;
    TextView VlblSatellite;
    RadioGroup rdogrpSatellite;
    RadioButton rdoSatellite1;
    RadioButton rdoSatellite2;
    LinearLayout secLandline;
    View lineLandline;
    TextView VlblLandline;
    RadioGroup rdogrpLandline;
    RadioButton rdoLandline1;
    RadioButton rdoLandline2;
    LinearLayout secCellphone;
    View lineCellphone;
    TextView VlblCellphone;
    RadioGroup rdogrpCellphone;
    RadioButton rdoCellphone1;
    RadioButton rdoCellphone2;
    LinearLayout secTV;
    View lineTV;
    TextView VlblTV;
    RadioGroup rdogrpTV;
    RadioButton rdoTV1;
    RadioButton rdoTV2;
    LinearLayout secRadio;
    View lineRadio;
    TextView VlblRadio;
    RadioGroup rdogrpRadio;
    RadioButton rdoRadio1;
    RadioButton rdoRadio2;
    LinearLayout secDVD;
    View lineDVD;
    TextView VlblDVD;
    RadioGroup rdogrpDVD;
    RadioButton rdoDVD1;
    RadioButton rdoDVD2;
    LinearLayout secVideo;
    View lineVideo;
    TextView VlblVideo;
    RadioGroup rdogrpVideo;
    RadioButton rdoVideo1;
    RadioButton rdoVideo2;
    LinearLayout secComputer;
    View lineComputer;
    TextView VlblComputer;
    RadioGroup rdogrpComputer;
    RadioButton rdoComputer1;
    RadioButton rdoComputer2;
    LinearLayout secCable;
    View lineCable;
    TextView VlblCable;
    RadioGroup rdogrpCable;
    RadioButton rdoCable1;
    RadioButton rdoCable2;
    LinearLayout seclblH12b;
    View linelblH12b;
    LinearLayout secMicrowave;
    View lineMicrowave;
    TextView VlblMicrowave;
    RadioGroup rdogrpMicrowave;
    RadioButton rdoMicrowave1;
    RadioButton rdoMicrowave2;
    LinearLayout secGeyser;
    View lineGeyser;
    TextView VlblGeyser;
    RadioGroup rdogrpGeyser;
    RadioButton rdoGeyser1;
    RadioButton rdoGeyser2;
    LinearLayout secGrill;
    View lineGrill;
    TextView VlblGrill;
    RadioGroup rdogrpGrill;
    RadioButton rdoGrill1;
    RadioButton rdoGrill2;
    LinearLayout secFridge;
    View lineFridge;
    TextView VlblFridge;
    RadioGroup rdogrpFridge;
    RadioButton rdoFridge1;
    RadioButton rdoFridge2;
    LinearLayout secDeepFreezer;
    View lineDeepFreezer;
    TextView VlblDeepFreezer;
    RadioGroup rdogrpDeepFreezer;
    RadioButton rdoDeepFreezer1;
    RadioButton rdoDeepFreezer2;
    LinearLayout secStove;
    View lineStove;
    TextView VlblStove;
    RadioGroup rdogrpStove;
    RadioButton rdoStove1;
    RadioButton rdoStove2;
    LinearLayout secBlender;
    View lineBlender;
    TextView VlblBlender;
    RadioGroup rdogrpBlender;
    RadioButton rdoBlender1;
    RadioButton rdoBlender2;
    LinearLayout seclblH12c;
    View linelblH12c;
    LinearLayout secBike;
    View lineBike;
    TextView VlblBike;
    RadioGroup rdogrpBike;
    RadioButton rdoBike1;
    RadioButton rdoBike2;
    LinearLayout secMotorcycle;
    View lineMotorcycle;
    TextView VlblMotorcycle;
    RadioGroup rdogrpMotorcycle;
    RadioButton rdoMotorcycle1;
    RadioButton rdoMotorcycle2;
    LinearLayout secCar;
    View lineCar;
    TextView VlblCar;
    RadioGroup rdogrpCar;
    RadioButton rdoCar1;
    RadioButton rdoCar2;
    LinearLayout secRickshaw;
    View lineRickshaw;
    TextView VlblRickshaw;
    RadioGroup rdogrpRickshaw;
    RadioButton rdoRickshaw1;
    RadioButton rdoRickshaw2;
    LinearLayout secCart;
    View lineCart;
    TextView VlblCart;
    RadioGroup rdogrpCart;
    RadioButton rdoCart1;
    RadioButton rdoCart2;
    LinearLayout secCanoe;
    View lineCanoe;
    TextView VlblCanoe;
    RadioGroup rdogrpCanoe;
    RadioButton rdoCanoe1;
    RadioButton rdoCanoe2;
    LinearLayout secBus;
    View lineBus;
    TextView VlblBus;
    RadioGroup rdogrpBus;
    RadioButton rdoBus1;
    RadioButton rdoBus2;
    LinearLayout seclblH12d;
    View linelblH12d;
    LinearLayout secTractor;
    View lineTractor;
    TextView VlblTractor;
    RadioGroup rdogrpTractor;
    RadioButton rdoTractor1;
    RadioButton rdoTractor2;
    LinearLayout secPlow;
    View linePlow;
    TextView VlblPlow;
    RadioGroup rdogrpPlow;
    RadioButton rdoPlow1;
    RadioButton rdoPlow2;
    LinearLayout secDuck;
    View lineDuck;
    TextView VlblDuck;
    RadioGroup rdogrpDuck;
    RadioButton rdoDuck1;
    RadioButton rdoDuck2;
    LinearLayout secCow;
    View lineCow;
    TextView VlblCow;
    RadioGroup rdogrpCow;
    RadioButton rdoCow1;
    RadioButton rdoCow2;
    LinearLayout secSheep;
    View lineSheep;
    TextView VlblSheep;
    RadioGroup rdogrpSheep;
    RadioButton rdoSheep1;
    RadioButton rdoSheep2;
    LinearLayout secGoat;
    View lineGoat;
    TextView VlblGoat;
    RadioGroup rdogrpGoat;
    RadioButton rdoGoat1;
    RadioButton rdoGoat2;
    LinearLayout secChicken;
    View lineChicken;
    TextView VlblChicken;
    RadioGroup rdogrpChicken;
    RadioButton rdoChicken1;
    RadioButton rdoChicken2;
    LinearLayout secDonkey;
    View lineDonkey;
    TextView VlblDonkey;
    RadioGroup rdogrpDonkey;
    RadioButton rdoDonkey1;
    RadioButton rdoDonkey2;
    LinearLayout secHorse;
    View lineHorse;
    TextView VlblHorse;
    RadioGroup rdogrpHorse;
    RadioButton rdoHorse1;
    RadioButton rdoHorse2;
    LinearLayout secPig;
    View linePig;
    TextView VlblPig;
    RadioGroup rdogrpPig;
    RadioButton rdoPig1;
    RadioButton rdoPig2;
    LinearLayout secOtherAsset;
    View lineOtherAsset;
    TextView VlblOtherAsset;
    RadioGroup rdogrpOtherAsset;
    RadioButton rdoOtherAsset1;
    RadioButton rdoOtherAsset2;
    LinearLayout secOtherAsset1;
    View lineOtherAsset1;
    TextView VlblOtherAsset1;
    AutoCompleteTextView txtOtherAsset1;
    LinearLayout secOtherAsset2;
    View lineOtherAsset2;
    TextView VlblOtherAsset2;
    AutoCompleteTextView txtOtherAsset2;
    LinearLayout secOtherAsset3;
    View lineOtherAsset3;
    TextView VlblOtherAsset3;
    AutoCompleteTextView txtOtherAsset3;
    LinearLayout secSESNote;
    View lineSESNote;
    TextView VlblSESNote;
    EditText txtSESNote;

     int MODULEID   = 0;
     int LANGUAGEID = 0;
     String TableName;

     String STARTTIME = "";
     String DEVICEID  = "";
     String ENTRYUSER = "";
    MySharedPreferences sp;

    Bundle IDbundle;
     String HHID = "";
     String SESNO = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.ses);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         HHID = IDbundle.getString("HHID");
         SESNO = IDbundle.getString("SESNo");

         TableName = "SES";
         MODULEID = 10;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(SES.this);
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
        Connection.LocalizeLanguage(SES.this, MODULEID, LANGUAGEID);


         dtpSESVDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                      VariableID = "btnSESVDate"; showDialog(DATE_DIALOG);
                      return true;
                 }
                 return false;
             }
         });



         //Hide all skip variables
         secSESVStatusOth.setVisibility(View.GONE);
         lineSESVStatusOth.setVisibility(View.GONE);
         secRnd.setVisibility(View.GONE);
         lineRnd.setVisibility(View.GONE);
         secWSDrink.setVisibility(View.GONE);
         lineWSDrink.setVisibility(View.GONE);
         secWSDrinkOth.setVisibility(View.GONE);
         lineWSDrinkOth.setVisibility(View.GONE);
         secToilet.setVisibility(View.GONE);
         lineToilet.setVisibility(View.GONE);
         secToiletOth.setVisibility(View.GONE);
         lineToiletOth.setVisibility(View.GONE);
         secToiletShrd.setVisibility(View.GONE);
         lineToiletShrd.setVisibility(View.GONE);
         secToiletUseNo.setVisibility(View.GONE);
         lineToiletUseNo.setVisibility(View.GONE);
         secToiletUseNoDk.setVisibility(View.GONE);
         lineToiletUseNoDk.setVisibility(View.GONE);
         secToiletLoc.setVisibility(View.GONE);
         lineToiletLoc.setVisibility(View.GONE);
         secCookDvc.setVisibility(View.GONE);
         lineCookDvc.setVisibility(View.GONE);
         secCookDvcOth.setVisibility(View.GONE);
         lineCookDvcOth.setVisibility(View.GONE);
         secCookFuel.setVisibility(View.GONE);
         lineCookFuel.setVisibility(View.GONE);
         secCookFuelOth.setVisibility(View.GONE);
         lineCookFuelOth.setVisibility(View.GONE);
         secCookPlc.setVisibility(View.GONE);
         lineCookPlc.setVisibility(View.GONE);
         secCookPlcOth.setVisibility(View.GONE);
         lineCookPlcOth.setVisibility(View.GONE);
         secFloor.setVisibility(View.GONE);
         lineFloor.setVisibility(View.GONE);
         secFloorOth.setVisibility(View.GONE);
         lineFloorOth.setVisibility(View.GONE);
         secRoof.setVisibility(View.GONE);
         lineRoof.setVisibility(View.GONE);
         secRoofOth.setVisibility(View.GONE);
         lineRoofOth.setVisibility(View.GONE);
         secWall.setVisibility(View.GONE);
         lineWall.setVisibility(View.GONE);
         secWallOth.setVisibility(View.GONE);
         lineWallOth.setVisibility(View.GONE);
         secRoomSleep.setVisibility(View.GONE);
         lineRoomSleep.setVisibility(View.GONE);
         secRoomSleepDk.setVisibility(View.GONE);
         lineRoomSleepDk.setVisibility(View.GONE);
         secHomesteadAny.setVisibility(View.GONE);
         lineHomesteadAny.setVisibility(View.GONE);
         secOthLand.setVisibility(View.GONE);
         lineOthLand.setVisibility(View.GONE);
         seclblH12.setVisibility(View.GONE);
         linelblH12.setVisibility(View.GONE);
         seclblH121.setVisibility(View.GONE);
         linelblH121.setVisibility(View.GONE);
         secElectricity.setVisibility(View.GONE);
         lineElectricity.setVisibility(View.GONE);
         secHeater.setVisibility(View.GONE);
         lineHeater.setVisibility(View.GONE);
         secAC.setVisibility(View.GONE);
         lineAC.setVisibility(View.GONE);
         secElecFan.setVisibility(View.GONE);
         lineElecFan.setVisibility(View.GONE);
         secLantern.setVisibility(View.GONE);
         lineLantern.setVisibility(View.GONE);
         secLamp.setVisibility(View.GONE);
         lineLamp.setVisibility(View.GONE);
         secMatt.setVisibility(View.GONE);
         lineMatt.setVisibility(View.GONE);
         secBed.setVisibility(View.GONE);
         lineBed.setVisibility(View.GONE);
         secChair.setVisibility(View.GONE);
         lineChair.setVisibility(View.GONE);
         secSofa.setVisibility(View.GONE);
         lineSofa.setVisibility(View.GONE);
         secTables.setVisibility(View.GONE);
         lineTables.setVisibility(View.GONE);
         secWatch.setVisibility(View.GONE);
         lineWatch.setVisibility(View.GONE);
         secWMachine.setVisibility(View.GONE);
         lineWMachine.setVisibility(View.GONE);
         secIron.setVisibility(View.GONE);
         lineIron.setVisibility(View.GONE);
         secBooth.setVisibility(View.GONE);
         lineBooth.setVisibility(View.GONE);
         secSMachine.setVisibility(View.GONE);
         lineSMachine.setVisibility(View.GONE);
         secGenerator.setVisibility(View.GONE);
         lineGenerator.setVisibility(View.GONE);
        secMosquito.setVisibility(View.GONE);
        lineMosquito.setVisibility(View.GONE);
         seclblH12a.setVisibility(View.GONE);
         linelblH12a.setVisibility(View.GONE);
         secInternet.setVisibility(View.GONE);
         lineInternet.setVisibility(View.GONE);
         secSatellite.setVisibility(View.GONE);
         lineSatellite.setVisibility(View.GONE);
         secLandline.setVisibility(View.GONE);
         lineLandline.setVisibility(View.GONE);
         secCellphone.setVisibility(View.GONE);
         lineCellphone.setVisibility(View.GONE);
         secTV.setVisibility(View.GONE);
         lineTV.setVisibility(View.GONE);
         secRadio.setVisibility(View.GONE);
         lineRadio.setVisibility(View.GONE);
         secDVD.setVisibility(View.GONE);
         lineDVD.setVisibility(View.GONE);
         secVideo.setVisibility(View.GONE);
         lineVideo.setVisibility(View.GONE);
         secComputer.setVisibility(View.GONE);
         lineComputer.setVisibility(View.GONE);
         secCable.setVisibility(View.GONE);
         lineCable.setVisibility(View.GONE);
         seclblH12b.setVisibility(View.GONE);
         linelblH12b.setVisibility(View.GONE);
         secMicrowave.setVisibility(View.GONE);
         lineMicrowave.setVisibility(View.GONE);
         secGeyser.setVisibility(View.GONE);
         lineGeyser.setVisibility(View.GONE);
         secGrill.setVisibility(View.GONE);
         lineGrill.setVisibility(View.GONE);
         secFridge.setVisibility(View.GONE);
         lineFridge.setVisibility(View.GONE);
         secDeepFreezer.setVisibility(View.GONE);
         lineDeepFreezer.setVisibility(View.GONE);
         secStove.setVisibility(View.GONE);
         lineStove.setVisibility(View.GONE);
        secBlender.setVisibility(View.GONE);
        lineBlender.setVisibility(View.GONE);
         seclblH12c.setVisibility(View.GONE);
         linelblH12c.setVisibility(View.GONE);
         secBike.setVisibility(View.GONE);
         lineBike.setVisibility(View.GONE);
         secMotorcycle.setVisibility(View.GONE);
         lineMotorcycle.setVisibility(View.GONE);
         secCar.setVisibility(View.GONE);
         lineCar.setVisibility(View.GONE);
         secRickshaw.setVisibility(View.GONE);
         lineRickshaw.setVisibility(View.GONE);
         secCart.setVisibility(View.GONE);
         lineCart.setVisibility(View.GONE);
         secCanoe.setVisibility(View.GONE);
         lineCanoe.setVisibility(View.GONE);
         secBus.setVisibility(View.GONE);
         lineBus.setVisibility(View.GONE);
         seclblH12d.setVisibility(View.GONE);
         linelblH12d.setVisibility(View.GONE);
         secTractor.setVisibility(View.GONE);
         lineTractor.setVisibility(View.GONE);
         secPlow.setVisibility(View.GONE);
         linePlow.setVisibility(View.GONE);
         secDuck.setVisibility(View.GONE);
         lineDuck.setVisibility(View.GONE);
         secCow.setVisibility(View.GONE);
         lineCow.setVisibility(View.GONE);
         secSheep.setVisibility(View.GONE);
         lineSheep.setVisibility(View.GONE);
         secGoat.setVisibility(View.GONE);
         lineGoat.setVisibility(View.GONE);
         secChicken.setVisibility(View.GONE);
         lineChicken.setVisibility(View.GONE);
         secDonkey.setVisibility(View.GONE);
         lineDonkey.setVisibility(View.GONE);
         secHorse.setVisibility(View.GONE);
         lineHorse.setVisibility(View.GONE);
         secPig.setVisibility(View.GONE);
         linePig.setVisibility(View.GONE);
         secOtherAsset.setVisibility(View.GONE);
         lineOtherAsset.setVisibility(View.GONE);
         secOtherAsset1.setVisibility(View.GONE);
         lineOtherAsset1.setVisibility(View.GONE);
         secOtherAsset2.setVisibility(View.GONE);
         lineOtherAsset2.setVisibility(View.GONE);
         secOtherAsset3.setVisibility(View.GONE);
         lineOtherAsset3.setVisibility(View.GONE);
         secSESNote.setVisibility(View.GONE);
         lineSESNote.setVisibility(View.GONE);
         secSESVStatusOth.setVisibility(View.GONE);
         lineSESVStatusOth.setVisibility(View.GONE);
         secRnd.setVisibility(View.GONE);
         lineRnd.setVisibility(View.GONE);
         secWSDrinkOth.setVisibility(View.GONE);
         lineWSDrinkOth.setVisibility(View.GONE);
         secToiletOth.setVisibility(View.GONE);
         lineToiletOth.setVisibility(View.GONE);
         secToiletUseNo.setVisibility(View.GONE);
         lineToiletUseNo.setVisibility(View.GONE);
         secToiletUseNoDk.setVisibility(View.GONE);
         lineToiletUseNoDk.setVisibility(View.GONE);
         secCookDvcOth.setVisibility(View.GONE);
         lineCookDvcOth.setVisibility(View.GONE);
         secCookFuelOth.setVisibility(View.GONE);
         lineCookFuelOth.setVisibility(View.GONE);
         secCookPlcOth.setVisibility(View.GONE);
         lineCookPlcOth.setVisibility(View.GONE);
         secFloorOth.setVisibility(View.GONE);
         lineFloorOth.setVisibility(View.GONE);
         secRoofOth.setVisibility(View.GONE);
         lineRoofOth.setVisibility(View.GONE);
         secWallOth.setVisibility(View.GONE);
         lineWallOth.setVisibility(View.GONE);
         secOtherAsset1.setVisibility(View.GONE);
         lineOtherAsset1.setVisibility(View.GONE);
         secOtherAsset2.setVisibility(View.GONE);
         lineOtherAsset2.setVisibility(View.GONE);
         secOtherAsset3.setVisibility(View.GONE);
         lineOtherAsset3.setVisibility(View.GONE);


        DataSearch(HHID,SESNO);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(SES.this, e.getMessage());
         return;
     }
 }

 private void Initialization()
 {
   try
     {
         seclblH1=(LinearLayout)findViewById(R.id.seclblH1);
         linelblH1=(View)findViewById(R.id.linelblH1);
         secHHID=(LinearLayout)findViewById(R.id.secHHID);
         lineHHID=(View)findViewById(R.id.lineHHID);
         VlblHHID=(TextView) findViewById(R.id.VlblHHID);
         txtHHID=(EditText) findViewById(R.id.txtHHID);
         txtHHID.setText(HHID);
         txtHHID.setEnabled(false);
         secSESNo=(LinearLayout)findViewById(R.id.secSESNo);
         lineSESNo=(View)findViewById(R.id.lineSESNo);
         VlblSESNo=(TextView) findViewById(R.id.VlblSESNo);
         txtSESNo=(EditText) findViewById(R.id.txtSESNo);
         txtSESNo.setText(SESNO);
         txtSESNo.setEnabled(false);
         secSESVDate=(LinearLayout)findViewById(R.id.secSESVDate);
         lineSESVDate=(View)findViewById(R.id.lineSESVDate);
         VlblSESVDate=(TextView) findViewById(R.id.VlblSESVDate);
         dtpSESVDate=(EditText) findViewById(R.id.dtpSESVDate);
         dtpSESVDate.setText(Global.DateNowDMY());
         secSESVStatus=(LinearLayout)findViewById(R.id.secSESVStatus);
         lineSESVStatus=(View)findViewById(R.id.lineSESVStatus);
         VlblSESVStatus = (TextView) findViewById(R.id.VlblSESVStatus);
         rdogrpSESVStatus = (RadioGroup) findViewById(R.id.rdogrpSESVStatus);
         rdoSESVStatus1 = (RadioButton) findViewById(R.id.rdoSESVStatus1);
         rdoSESVStatus2 = (RadioButton) findViewById(R.id.rdoSESVStatus2);
         rdoSESVStatus3 = (RadioButton) findViewById(R.id.rdoSESVStatus3);
         rdoSESVStatus4 = (RadioButton) findViewById(R.id.rdoSESVStatus4);
         rdogrpSESVStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
            String rbData = "";
            RadioButton rb;
            String[] d_rdogrpSESVStatus = new String[]{"1", "2", "3", "9"};
            for (int i = 0; i < rdogrpSESVStatus.getChildCount(); i++) {
               rb = (RadioButton) rdogrpSESVStatus.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpSESVStatus[i];
            }

            if (rbData.equalsIgnoreCase("3")) {
               secSESVStatusOth.setVisibility(View.GONE);
               lineSESVStatusOth.setVisibility(View.GONE);
               txtSESVStatusOth.setText("");
               secRnd.setVisibility(View.GONE);
               lineRnd.setVisibility(View.GONE);
               txtRnd.setText("");
               secWSDrink.setVisibility(View.GONE);
               lineWSDrink.setVisibility(View.GONE);
               spnWSDrink.setSelection(0);
               secWSDrinkOth.setVisibility(View.GONE);
               lineWSDrinkOth.setVisibility(View.GONE);
               txtWSDrinkOth.setText("");
               secToilet.setVisibility(View.GONE);
               lineToilet.setVisibility(View.GONE);
               spnToilet.setSelection(0);
               secToiletOth.setVisibility(View.GONE);
               lineToiletOth.setVisibility(View.GONE);
               txtToiletOth.setText("");
               secToiletShrd.setVisibility(View.GONE);
               lineToiletShrd.setVisibility(View.GONE);
               rdogrpToiletShrd.clearCheck();
               secToiletUseNo.setVisibility(View.GONE);
               lineToiletUseNo.setVisibility(View.GONE);
               txtToiletUseNo.setText("");
               secToiletUseNoDk.setVisibility(View.GONE);
               lineToiletUseNoDk.setVisibility(View.GONE);
               rdogrpToiletUseNoDk.clearCheck();
               secToiletLoc.setVisibility(View.GONE);
               lineToiletLoc.setVisibility(View.GONE);
               rdogrpToiletLoc.clearCheck();
               secCookDvc.setVisibility(View.GONE);
               lineCookDvc.setVisibility(View.GONE);
               spnCookDvc.setSelection(0);
               secCookDvcOth.setVisibility(View.GONE);
               lineCookDvcOth.setVisibility(View.GONE);
               txtCookDvcOth.setText("");
               secCookFuel.setVisibility(View.GONE);
               lineCookFuel.setVisibility(View.GONE);
               spnCookFuel.setSelection(0);
               secCookFuelOth.setVisibility(View.GONE);
               lineCookFuelOth.setVisibility(View.GONE);
               txtCookFuelOth.setText("");
               secCookPlc.setVisibility(View.GONE);
               lineCookPlc.setVisibility(View.GONE);
               spnCookPlc.setSelection(0);
               secCookPlcOth.setVisibility(View.GONE);
               lineCookPlcOth.setVisibility(View.GONE);
               txtCookPlcOth.setText("");
               secFloor.setVisibility(View.GONE);
               lineFloor.setVisibility(View.GONE);
               spnFloor.setSelection(0);
               secFloorOth.setVisibility(View.GONE);
               lineFloorOth.setVisibility(View.GONE);
               txtFloorOth.setText("");
               secRoof.setVisibility(View.GONE);
               lineRoof.setVisibility(View.GONE);
               spnRoof.setSelection(0);
               secRoofOth.setVisibility(View.GONE);
               lineRoofOth.setVisibility(View.GONE);
               txtRoofOth.setText("");
               secWall.setVisibility(View.GONE);
               lineWall.setVisibility(View.GONE);
               spnWall.setSelection(0);
               secWallOth.setVisibility(View.GONE);
               lineWallOth.setVisibility(View.GONE);
               txtWallOth.setText("");
               secRoomSleep.setVisibility(View.GONE);
               lineRoomSleep.setVisibility(View.GONE);
               txtRoomSleep.setText("");
               secRoomSleepDk.setVisibility(View.GONE);
               lineRoomSleepDk.setVisibility(View.GONE);
               rdogrpRoomSleepDk.clearCheck();
               secHomesteadAny.setVisibility(View.GONE);
               lineHomesteadAny.setVisibility(View.GONE);
               rdogrpHomesteadAny.clearCheck();
               secOthLand.setVisibility(View.GONE);
               lineOthLand.setVisibility(View.GONE);
               rdogrpOthLand.clearCheck();
               seclblH12.setVisibility(View.GONE);
               linelblH12.setVisibility(View.GONE);
               seclblH121.setVisibility(View.GONE);
               linelblH121.setVisibility(View.GONE);
               secElectricity.setVisibility(View.GONE);
               lineElectricity.setVisibility(View.GONE);
               rdogrpElectricity.clearCheck();
               secHeater.setVisibility(View.GONE);
               lineHeater.setVisibility(View.GONE);
               rdogrpHeater.clearCheck();
               secAC.setVisibility(View.GONE);
               lineAC.setVisibility(View.GONE);
               rdogrpAC.clearCheck();
               secElecFan.setVisibility(View.GONE);
               lineElecFan.setVisibility(View.GONE);
               rdogrpElecFan.clearCheck();
               secLantern.setVisibility(View.GONE);
               lineLantern.setVisibility(View.GONE);
               rdogrpLantern.clearCheck();
               secLamp.setVisibility(View.GONE);
               lineLamp.setVisibility(View.GONE);
               rdogrpLamp.clearCheck();
               secMatt.setVisibility(View.GONE);
               lineMatt.setVisibility(View.GONE);
               rdogrpMatt.clearCheck();
               secBed.setVisibility(View.GONE);
               lineBed.setVisibility(View.GONE);
               rdogrpBed.clearCheck();
               secChair.setVisibility(View.GONE);
               lineChair.setVisibility(View.GONE);
               rdogrpChair.clearCheck();
               secSofa.setVisibility(View.GONE);
               lineSofa.setVisibility(View.GONE);
               rdogrpSofa.clearCheck();
               secTables.setVisibility(View.GONE);
               lineTables.setVisibility(View.GONE);
               rdogrpTables.clearCheck();
               secWatch.setVisibility(View.GONE);
               lineWatch.setVisibility(View.GONE);
               rdogrpWatch.clearCheck();
               secWMachine.setVisibility(View.GONE);
               lineWMachine.setVisibility(View.GONE);
               rdogrpWMachine.clearCheck();
               secIron.setVisibility(View.GONE);
               lineIron.setVisibility(View.GONE);
               rdogrpIron.clearCheck();
               secBooth.setVisibility(View.GONE);
               lineBooth.setVisibility(View.GONE);
               rdogrpBooth.clearCheck();
               secSMachine.setVisibility(View.GONE);
               lineSMachine.setVisibility(View.GONE);
               rdogrpSMachine.clearCheck();
               secGenerator.setVisibility(View.GONE);
               lineGenerator.setVisibility(View.GONE);
               rdogrpGenerator.clearCheck();
               secMosquito.setVisibility(View.GONE);
               lineMosquito.setVisibility(View.GONE);
               rdogrpMosquito.clearCheck();
               seclblH12a.setVisibility(View.GONE);
               linelblH12a.setVisibility(View.GONE);
               secInternet.setVisibility(View.GONE);
               lineInternet.setVisibility(View.GONE);
               rdogrpInternet.clearCheck();
               secSatellite.setVisibility(View.GONE);
               lineSatellite.setVisibility(View.GONE);
               rdogrpSatellite.clearCheck();
               secLandline.setVisibility(View.GONE);
               lineLandline.setVisibility(View.GONE);
               rdogrpLandline.clearCheck();
               secCellphone.setVisibility(View.GONE);
               lineCellphone.setVisibility(View.GONE);
               rdogrpCellphone.clearCheck();
               secTV.setVisibility(View.GONE);
               lineTV.setVisibility(View.GONE);
               rdogrpTV.clearCheck();
               secRadio.setVisibility(View.GONE);
               lineRadio.setVisibility(View.GONE);
               rdogrpRadio.clearCheck();
               secDVD.setVisibility(View.GONE);
               lineDVD.setVisibility(View.GONE);
               rdogrpDVD.clearCheck();
               secVideo.setVisibility(View.GONE);
               lineVideo.setVisibility(View.GONE);
               rdogrpVideo.clearCheck();
               secComputer.setVisibility(View.GONE);
               lineComputer.setVisibility(View.GONE);
               rdogrpComputer.clearCheck();
               secCable.setVisibility(View.GONE);
               lineCable.setVisibility(View.GONE);
               rdogrpCable.clearCheck();
               seclblH12b.setVisibility(View.GONE);
               linelblH12b.setVisibility(View.GONE);
               secMicrowave.setVisibility(View.GONE);
               lineMicrowave.setVisibility(View.GONE);
               rdogrpMicrowave.clearCheck();
               secGeyser.setVisibility(View.GONE);
               lineGeyser.setVisibility(View.GONE);
               rdogrpGeyser.clearCheck();
               secGrill.setVisibility(View.GONE);
               lineGrill.setVisibility(View.GONE);
               rdogrpGrill.clearCheck();
               secFridge.setVisibility(View.GONE);
               lineFridge.setVisibility(View.GONE);
               rdogrpFridge.clearCheck();
               secDeepFreezer.setVisibility(View.GONE);
               lineDeepFreezer.setVisibility(View.GONE);
               rdogrpDeepFreezer.clearCheck();
               secStove.setVisibility(View.GONE);
               lineStove.setVisibility(View.GONE);
               rdogrpStove.clearCheck();
               secBlender.setVisibility(View.GONE);
               lineBlender.setVisibility(View.GONE);
               rdogrpBlender.clearCheck();
               seclblH12c.setVisibility(View.GONE);
               linelblH12c.setVisibility(View.GONE);
               secBike.setVisibility(View.GONE);
               lineBike.setVisibility(View.GONE);
               rdogrpBike.clearCheck();
               secMotorcycle.setVisibility(View.GONE);
               lineMotorcycle.setVisibility(View.GONE);
               rdogrpMotorcycle.clearCheck();
               secCar.setVisibility(View.GONE);
               lineCar.setVisibility(View.GONE);
               rdogrpCar.clearCheck();
               secRickshaw.setVisibility(View.GONE);
               lineRickshaw.setVisibility(View.GONE);
               rdogrpRickshaw.clearCheck();
               secCart.setVisibility(View.GONE);
               lineCart.setVisibility(View.GONE);
               rdogrpCart.clearCheck();
               secCanoe.setVisibility(View.GONE);
               lineCanoe.setVisibility(View.GONE);
               rdogrpCanoe.clearCheck();
               secBus.setVisibility(View.GONE);
               lineBus.setVisibility(View.GONE);
               rdogrpBus.clearCheck();
               seclblH12d.setVisibility(View.GONE);
               linelblH12d.setVisibility(View.GONE);
               secTractor.setVisibility(View.GONE);
               lineTractor.setVisibility(View.GONE);
               rdogrpTractor.clearCheck();
               secPlow.setVisibility(View.GONE);
               linePlow.setVisibility(View.GONE);
               rdogrpPlow.clearCheck();
               secDuck.setVisibility(View.GONE);
               lineDuck.setVisibility(View.GONE);
               rdogrpDuck.clearCheck();
               secCow.setVisibility(View.GONE);
               lineCow.setVisibility(View.GONE);
               rdogrpCow.clearCheck();
               secSheep.setVisibility(View.GONE);
               lineSheep.setVisibility(View.GONE);
               rdogrpSheep.clearCheck();
               secGoat.setVisibility(View.GONE);
               lineGoat.setVisibility(View.GONE);
               rdogrpGoat.clearCheck();
               secChicken.setVisibility(View.GONE);
               lineChicken.setVisibility(View.GONE);
               rdogrpChicken.clearCheck();
               secDonkey.setVisibility(View.GONE);
               lineDonkey.setVisibility(View.GONE);
               rdogrpDonkey.clearCheck();
               secHorse.setVisibility(View.GONE);
               lineHorse.setVisibility(View.GONE);
               rdogrpHorse.clearCheck();
               secPig.setVisibility(View.GONE);
               linePig.setVisibility(View.GONE);
               rdogrpPig.clearCheck();
               secOtherAsset.setVisibility(View.GONE);
               lineOtherAsset.setVisibility(View.GONE);
               rdogrpOtherAsset.clearCheck();
               secOtherAsset1.setVisibility(View.GONE);
               lineOtherAsset1.setVisibility(View.GONE);
               txtOtherAsset1.setText("");
               secOtherAsset2.setVisibility(View.GONE);
               lineOtherAsset2.setVisibility(View.GONE);
               txtOtherAsset2.setText("");
               secOtherAsset3.setVisibility(View.GONE);
               lineOtherAsset3.setVisibility(View.GONE);
               txtOtherAsset3.setText("");
               secSESNote.setVisibility(View.GONE);
               lineSESNote.setVisibility(View.GONE);
               txtSESNote.setText("");
            } else if (rbData.equalsIgnoreCase("9")) {
               secSESVStatusOth.setVisibility(View.VISIBLE);
               lineSESVStatusOth.setVisibility(View.VISIBLE);
               txtSESVStatusOth.setText("");
               secRnd.setVisibility(View.GONE);
               lineRnd.setVisibility(View.GONE);
               txtRnd.setText("");
               secWSDrink.setVisibility(View.GONE);
               lineWSDrink.setVisibility(View.GONE);
               spnWSDrink.setSelection(0);
               secWSDrinkOth.setVisibility(View.GONE);
               lineWSDrinkOth.setVisibility(View.GONE);
               txtWSDrinkOth.setText("");
               secToilet.setVisibility(View.GONE);
               lineToilet.setVisibility(View.GONE);
               spnToilet.setSelection(0);
               secToiletOth.setVisibility(View.GONE);
               lineToiletOth.setVisibility(View.GONE);
               txtToiletOth.setText("");
               secToiletShrd.setVisibility(View.GONE);
               lineToiletShrd.setVisibility(View.GONE);
               rdogrpToiletShrd.clearCheck();
               secToiletUseNo.setVisibility(View.GONE);
               lineToiletUseNo.setVisibility(View.GONE);
               txtToiletUseNo.setText("");
               secToiletUseNoDk.setVisibility(View.GONE);
               lineToiletUseNoDk.setVisibility(View.GONE);
               rdogrpToiletUseNoDk.clearCheck();
               secToiletLoc.setVisibility(View.GONE);
               lineToiletLoc.setVisibility(View.GONE);
               rdogrpToiletLoc.clearCheck();
               secCookDvc.setVisibility(View.GONE);
               lineCookDvc.setVisibility(View.GONE);
               spnCookDvc.setSelection(0);
               secCookDvcOth.setVisibility(View.GONE);
               lineCookDvcOth.setVisibility(View.GONE);
               txtCookDvcOth.setText("");
               secCookFuel.setVisibility(View.GONE);
               lineCookFuel.setVisibility(View.GONE);
               spnCookFuel.setSelection(0);
               secCookFuelOth.setVisibility(View.GONE);
               lineCookFuelOth.setVisibility(View.GONE);
               txtCookFuelOth.setText("");
               secCookPlc.setVisibility(View.GONE);
               lineCookPlc.setVisibility(View.GONE);
               spnCookPlc.setSelection(0);
               secCookPlcOth.setVisibility(View.GONE);
               lineCookPlcOth.setVisibility(View.GONE);
               txtCookPlcOth.setText("");
               secFloor.setVisibility(View.GONE);
               lineFloor.setVisibility(View.GONE);
               spnFloor.setSelection(0);
               secFloorOth.setVisibility(View.GONE);
               lineFloorOth.setVisibility(View.GONE);
               txtFloorOth.setText("");
               secRoof.setVisibility(View.GONE);
               lineRoof.setVisibility(View.GONE);
               spnRoof.setSelection(0);
               secRoofOth.setVisibility(View.GONE);
               lineRoofOth.setVisibility(View.GONE);
               txtRoofOth.setText("");
               secWall.setVisibility(View.GONE);
               lineWall.setVisibility(View.GONE);
               spnWall.setSelection(0);
               secWallOth.setVisibility(View.GONE);
               lineWallOth.setVisibility(View.GONE);
               txtWallOth.setText("");
               secRoomSleep.setVisibility(View.GONE);
               lineRoomSleep.setVisibility(View.GONE);
               txtRoomSleep.setText("");
               secRoomSleepDk.setVisibility(View.GONE);
               lineRoomSleepDk.setVisibility(View.GONE);
               rdogrpRoomSleepDk.clearCheck();
               secHomesteadAny.setVisibility(View.GONE);
               lineHomesteadAny.setVisibility(View.GONE);
               rdogrpHomesteadAny.clearCheck();
               secOthLand.setVisibility(View.GONE);
               lineOthLand.setVisibility(View.GONE);
               rdogrpOthLand.clearCheck();
               seclblH12.setVisibility(View.GONE);
               linelblH12.setVisibility(View.GONE);
               seclblH121.setVisibility(View.GONE);
               linelblH121.setVisibility(View.GONE);
               secElectricity.setVisibility(View.GONE);
               lineElectricity.setVisibility(View.GONE);
               rdogrpElectricity.clearCheck();
               secHeater.setVisibility(View.GONE);
               lineHeater.setVisibility(View.GONE);
               rdogrpHeater.clearCheck();
               secAC.setVisibility(View.GONE);
               lineAC.setVisibility(View.GONE);
               rdogrpAC.clearCheck();
               secElecFan.setVisibility(View.GONE);
               lineElecFan.setVisibility(View.GONE);
               rdogrpElecFan.clearCheck();
               secLantern.setVisibility(View.GONE);
               lineLantern.setVisibility(View.GONE);
               rdogrpLantern.clearCheck();
               secLamp.setVisibility(View.GONE);
               lineLamp.setVisibility(View.GONE);
               rdogrpLamp.clearCheck();
               secMatt.setVisibility(View.GONE);
               lineMatt.setVisibility(View.GONE);
               rdogrpMatt.clearCheck();
               secBed.setVisibility(View.GONE);
               lineBed.setVisibility(View.GONE);
               rdogrpBed.clearCheck();
               secChair.setVisibility(View.GONE);
               lineChair.setVisibility(View.GONE);
               rdogrpChair.clearCheck();
               secSofa.setVisibility(View.GONE);
               lineSofa.setVisibility(View.GONE);
               rdogrpSofa.clearCheck();
               secTables.setVisibility(View.GONE);
               lineTables.setVisibility(View.GONE);
               rdogrpTables.clearCheck();
               secWatch.setVisibility(View.GONE);
               lineWatch.setVisibility(View.GONE);
               rdogrpWatch.clearCheck();
               secWMachine.setVisibility(View.GONE);
               lineWMachine.setVisibility(View.GONE);
               rdogrpWMachine.clearCheck();
               secIron.setVisibility(View.GONE);
               lineIron.setVisibility(View.GONE);
               rdogrpIron.clearCheck();
               secBooth.setVisibility(View.GONE);
               lineBooth.setVisibility(View.GONE);
               rdogrpBooth.clearCheck();
               secSMachine.setVisibility(View.GONE);
               lineSMachine.setVisibility(View.GONE);
               rdogrpSMachine.clearCheck();
               secGenerator.setVisibility(View.GONE);
               lineGenerator.setVisibility(View.GONE);
               rdogrpGenerator.clearCheck();
               secMosquito.setVisibility(View.GONE);
               lineMosquito.setVisibility(View.GONE);
               rdogrpMosquito.clearCheck();
               seclblH12a.setVisibility(View.GONE);
               linelblH12a.setVisibility(View.GONE);
               secInternet.setVisibility(View.GONE);
               lineInternet.setVisibility(View.GONE);
               rdogrpInternet.clearCheck();
               secSatellite.setVisibility(View.GONE);
               lineSatellite.setVisibility(View.GONE);
               rdogrpSatellite.clearCheck();
               secLandline.setVisibility(View.GONE);
               lineLandline.setVisibility(View.GONE);
               rdogrpLandline.clearCheck();
               secCellphone.setVisibility(View.GONE);
               lineCellphone.setVisibility(View.GONE);
               rdogrpCellphone.clearCheck();
               secTV.setVisibility(View.GONE);
               lineTV.setVisibility(View.GONE);
               rdogrpTV.clearCheck();
               secRadio.setVisibility(View.GONE);
               lineRadio.setVisibility(View.GONE);
               rdogrpRadio.clearCheck();
               secDVD.setVisibility(View.GONE);
               lineDVD.setVisibility(View.GONE);
               rdogrpDVD.clearCheck();
               secVideo.setVisibility(View.GONE);
               lineVideo.setVisibility(View.GONE);
               rdogrpVideo.clearCheck();
               secComputer.setVisibility(View.GONE);
               lineComputer.setVisibility(View.GONE);
               rdogrpComputer.clearCheck();
               secCable.setVisibility(View.GONE);
               lineCable.setVisibility(View.GONE);
               rdogrpCable.clearCheck();
               seclblH12b.setVisibility(View.GONE);
               linelblH12b.setVisibility(View.GONE);
               secMicrowave.setVisibility(View.GONE);
               lineMicrowave.setVisibility(View.GONE);
               rdogrpMicrowave.clearCheck();
               secGeyser.setVisibility(View.GONE);
               lineGeyser.setVisibility(View.GONE);
               rdogrpGeyser.clearCheck();
               secGrill.setVisibility(View.GONE);
               lineGrill.setVisibility(View.GONE);
               rdogrpGrill.clearCheck();
               secFridge.setVisibility(View.GONE);
               lineFridge.setVisibility(View.GONE);
               rdogrpFridge.clearCheck();
               secDeepFreezer.setVisibility(View.GONE);
               lineDeepFreezer.setVisibility(View.GONE);
               rdogrpDeepFreezer.clearCheck();
               secStove.setVisibility(View.GONE);
               lineStove.setVisibility(View.GONE);
               rdogrpStove.clearCheck();
               secBlender.setVisibility(View.GONE);
               lineBlender.setVisibility(View.GONE);
               rdogrpBlender.clearCheck();
               seclblH12c.setVisibility(View.GONE);
               linelblH12c.setVisibility(View.GONE);
               secBike.setVisibility(View.GONE);
               lineBike.setVisibility(View.GONE);
               rdogrpBike.clearCheck();
               secMotorcycle.setVisibility(View.GONE);
               lineMotorcycle.setVisibility(View.GONE);
               rdogrpMotorcycle.clearCheck();
               secCar.setVisibility(View.GONE);
               lineCar.setVisibility(View.GONE);
               rdogrpCar.clearCheck();
               secRickshaw.setVisibility(View.GONE);
               lineRickshaw.setVisibility(View.GONE);
               rdogrpRickshaw.clearCheck();
               secCart.setVisibility(View.GONE);
               lineCart.setVisibility(View.GONE);
               rdogrpCart.clearCheck();
               secCanoe.setVisibility(View.GONE);
               lineCanoe.setVisibility(View.GONE);
               rdogrpCanoe.clearCheck();
               secBus.setVisibility(View.GONE);
               lineBus.setVisibility(View.GONE);
               rdogrpBus.clearCheck();
               seclblH12d.setVisibility(View.GONE);
               linelblH12d.setVisibility(View.GONE);
               secTractor.setVisibility(View.GONE);
               lineTractor.setVisibility(View.GONE);
               rdogrpTractor.clearCheck();
               secPlow.setVisibility(View.GONE);
               linePlow.setVisibility(View.GONE);
               rdogrpPlow.clearCheck();
               secDuck.setVisibility(View.GONE);
               lineDuck.setVisibility(View.GONE);
               rdogrpDuck.clearCheck();
               secCow.setVisibility(View.GONE);
               lineCow.setVisibility(View.GONE);
               rdogrpCow.clearCheck();
               secSheep.setVisibility(View.GONE);
               lineSheep.setVisibility(View.GONE);
               rdogrpSheep.clearCheck();
               secGoat.setVisibility(View.GONE);
               lineGoat.setVisibility(View.GONE);
               rdogrpGoat.clearCheck();
               secChicken.setVisibility(View.GONE);
               lineChicken.setVisibility(View.GONE);
               rdogrpChicken.clearCheck();
               secDonkey.setVisibility(View.GONE);
               lineDonkey.setVisibility(View.GONE);
               rdogrpDonkey.clearCheck();
               secHorse.setVisibility(View.GONE);
               lineHorse.setVisibility(View.GONE);
               rdogrpHorse.clearCheck();
               secPig.setVisibility(View.GONE);
               linePig.setVisibility(View.GONE);
               rdogrpPig.clearCheck();
               secOtherAsset.setVisibility(View.GONE);
               lineOtherAsset.setVisibility(View.GONE);
               rdogrpOtherAsset.clearCheck();
               secOtherAsset1.setVisibility(View.GONE);
               lineOtherAsset1.setVisibility(View.GONE);
               txtOtherAsset1.setText("");
               secOtherAsset2.setVisibility(View.GONE);
               lineOtherAsset2.setVisibility(View.GONE);
               txtOtherAsset2.setText("");
               secOtherAsset3.setVisibility(View.GONE);
               lineOtherAsset3.setVisibility(View.GONE);
               txtOtherAsset3.setText("");
               secSESNote.setVisibility(View.GONE);
               lineSESNote.setVisibility(View.GONE);
               txtSESNote.setText("");
            } else {
               //secSESVStatusOth.setVisibility(View.VISIBLE);
               //lineSESVStatusOth.setVisibility(View.VISIBLE);
               secRnd.setVisibility(View.VISIBLE);
               lineRnd.setVisibility(View.VISIBLE);
               secWSDrink.setVisibility(View.VISIBLE);
               lineWSDrink.setVisibility(View.VISIBLE);
               //secWSDrinkOth.setVisibility(View.VISIBLE);
               //lineWSDrinkOth.setVisibility(View.VISIBLE);
               secToilet.setVisibility(View.VISIBLE);
               lineToilet.setVisibility(View.VISIBLE);
               //secToiletOth.setVisibility(View.VISIBLE);
               //lineToiletOth.setVisibility(View.VISIBLE);
               secToiletShrd.setVisibility(View.VISIBLE);
               lineToiletShrd.setVisibility(View.VISIBLE);
               secToiletUseNo.setVisibility(View.VISIBLE);
               lineToiletUseNo.setVisibility(View.VISIBLE);
               secToiletUseNoDk.setVisibility(View.VISIBLE);
               lineToiletUseNoDk.setVisibility(View.VISIBLE);
               secToiletLoc.setVisibility(View.VISIBLE);
               lineToiletLoc.setVisibility(View.VISIBLE);
               secCookDvc.setVisibility(View.VISIBLE);
               lineCookDvc.setVisibility(View.VISIBLE);
               //secCookDvcOth.setVisibility(View.VISIBLE);
               //lineCookDvcOth.setVisibility(View.VISIBLE);
               secCookFuel.setVisibility(View.VISIBLE);
               lineCookFuel.setVisibility(View.VISIBLE);
               //secCookFuelOth.setVisibility(View.VISIBLE);
               //lineCookFuelOth.setVisibility(View.VISIBLE);
               secCookPlc.setVisibility(View.VISIBLE);
               lineCookPlc.setVisibility(View.VISIBLE);
               //secCookPlcOth.setVisibility(View.VISIBLE);
               //lineCookPlcOth.setVisibility(View.VISIBLE);
               secFloor.setVisibility(View.VISIBLE);
               lineFloor.setVisibility(View.VISIBLE);
               //secFloorOth.setVisibility(View.VISIBLE);
               //lineFloorOth.setVisibility(View.VISIBLE);
               secRoof.setVisibility(View.VISIBLE);
               lineRoof.setVisibility(View.VISIBLE);
               //secRoofOth.setVisibility(View.VISIBLE);
               //lineRoofOth.setVisibility(View.VISIBLE);
               secWall.setVisibility(View.VISIBLE);
               lineWall.setVisibility(View.VISIBLE);
               //secWallOth.setVisibility(View.VISIBLE);
               //lineWallOth.setVisibility(View.VISIBLE);
               secRoomSleep.setVisibility(View.VISIBLE);
               lineRoomSleep.setVisibility(View.VISIBLE);
               secRoomSleepDk.setVisibility(View.VISIBLE);
               lineRoomSleepDk.setVisibility(View.VISIBLE);
               secHomesteadAny.setVisibility(View.VISIBLE);
               lineHomesteadAny.setVisibility(View.VISIBLE);
               secOthLand.setVisibility(View.VISIBLE);
               lineOthLand.setVisibility(View.VISIBLE);
               seclblH12.setVisibility(View.VISIBLE);
               linelblH12.setVisibility(View.VISIBLE);
               seclblH121.setVisibility(View.VISIBLE);
               linelblH121.setVisibility(View.VISIBLE);
               secElectricity.setVisibility(View.VISIBLE);
               lineElectricity.setVisibility(View.VISIBLE);
               secHeater.setVisibility(View.VISIBLE);
               lineHeater.setVisibility(View.VISIBLE);
               secAC.setVisibility(View.VISIBLE);
               lineAC.setVisibility(View.VISIBLE);
               secElecFan.setVisibility(View.VISIBLE);
               lineElecFan.setVisibility(View.VISIBLE);
               secLantern.setVisibility(View.VISIBLE);
               lineLantern.setVisibility(View.VISIBLE);
               secLamp.setVisibility(View.VISIBLE);
               lineLamp.setVisibility(View.VISIBLE);
               secMatt.setVisibility(View.VISIBLE);
               lineMatt.setVisibility(View.VISIBLE);
               secBed.setVisibility(View.VISIBLE);
               lineBed.setVisibility(View.VISIBLE);
               secChair.setVisibility(View.VISIBLE);
               lineChair.setVisibility(View.VISIBLE);
               secSofa.setVisibility(View.VISIBLE);
               lineSofa.setVisibility(View.VISIBLE);
               secTables.setVisibility(View.VISIBLE);
               lineTables.setVisibility(View.VISIBLE);
               secWatch.setVisibility(View.VISIBLE);
               lineWatch.setVisibility(View.VISIBLE);
               secWMachine.setVisibility(View.VISIBLE);
               lineWMachine.setVisibility(View.VISIBLE);
               secIron.setVisibility(View.VISIBLE);
               lineIron.setVisibility(View.VISIBLE);
               secBooth.setVisibility(View.VISIBLE);
               lineBooth.setVisibility(View.VISIBLE);
               secSMachine.setVisibility(View.VISIBLE);
               lineSMachine.setVisibility(View.VISIBLE);
               secGenerator.setVisibility(View.VISIBLE);
               lineGenerator.setVisibility(View.VISIBLE);
               secMosquito.setVisibility(View.VISIBLE);
               lineMosquito.setVisibility(View.VISIBLE);
               seclblH12a.setVisibility(View.VISIBLE);
               linelblH12a.setVisibility(View.VISIBLE);
               secInternet.setVisibility(View.VISIBLE);
               lineInternet.setVisibility(View.VISIBLE);
               secSatellite.setVisibility(View.VISIBLE);
               lineSatellite.setVisibility(View.VISIBLE);
               secLandline.setVisibility(View.VISIBLE);
               lineLandline.setVisibility(View.VISIBLE);
               secCellphone.setVisibility(View.VISIBLE);
               lineCellphone.setVisibility(View.VISIBLE);
               secTV.setVisibility(View.VISIBLE);
               lineTV.setVisibility(View.VISIBLE);
               secRadio.setVisibility(View.VISIBLE);
               lineRadio.setVisibility(View.VISIBLE);
               secDVD.setVisibility(View.VISIBLE);
               lineDVD.setVisibility(View.VISIBLE);
               secVideo.setVisibility(View.VISIBLE);
               lineVideo.setVisibility(View.VISIBLE);
               secComputer.setVisibility(View.VISIBLE);
               lineComputer.setVisibility(View.VISIBLE);
               secCable.setVisibility(View.VISIBLE);
               lineCable.setVisibility(View.VISIBLE);
               seclblH12b.setVisibility(View.VISIBLE);
               linelblH12b.setVisibility(View.VISIBLE);
               secMicrowave.setVisibility(View.VISIBLE);
               lineMicrowave.setVisibility(View.VISIBLE);
               secGeyser.setVisibility(View.VISIBLE);
               lineGeyser.setVisibility(View.VISIBLE);
               secGrill.setVisibility(View.VISIBLE);
               lineGrill.setVisibility(View.VISIBLE);
               secFridge.setVisibility(View.VISIBLE);
               lineFridge.setVisibility(View.VISIBLE);
               secDeepFreezer.setVisibility(View.VISIBLE);
               lineDeepFreezer.setVisibility(View.VISIBLE);
               secStove.setVisibility(View.VISIBLE);
               lineStove.setVisibility(View.VISIBLE);
               secBlender.setVisibility(View.VISIBLE);
               lineBlender.setVisibility(View.VISIBLE);
               seclblH12c.setVisibility(View.VISIBLE);
               linelblH12c.setVisibility(View.VISIBLE);
               secBike.setVisibility(View.VISIBLE);
               lineBike.setVisibility(View.VISIBLE);
               secMotorcycle.setVisibility(View.VISIBLE);
               lineMotorcycle.setVisibility(View.VISIBLE);
               secCar.setVisibility(View.VISIBLE);
               lineCar.setVisibility(View.VISIBLE);
               secRickshaw.setVisibility(View.VISIBLE);
               lineRickshaw.setVisibility(View.VISIBLE);
               secCart.setVisibility(View.VISIBLE);
               lineCart.setVisibility(View.VISIBLE);
               secCanoe.setVisibility(View.VISIBLE);
               lineCanoe.setVisibility(View.VISIBLE);
               secBus.setVisibility(View.VISIBLE);
               lineBus.setVisibility(View.VISIBLE);
               seclblH12d.setVisibility(View.VISIBLE);
               linelblH12d.setVisibility(View.VISIBLE);
               secTractor.setVisibility(View.VISIBLE);
               lineTractor.setVisibility(View.VISIBLE);
               secPlow.setVisibility(View.VISIBLE);
               linePlow.setVisibility(View.VISIBLE);
               secDuck.setVisibility(View.VISIBLE);
               lineDuck.setVisibility(View.VISIBLE);
               secCow.setVisibility(View.VISIBLE);
               lineCow.setVisibility(View.VISIBLE);
               secSheep.setVisibility(View.VISIBLE);
               lineSheep.setVisibility(View.VISIBLE);
               secGoat.setVisibility(View.VISIBLE);
               lineGoat.setVisibility(View.VISIBLE);
               secChicken.setVisibility(View.VISIBLE);
               lineChicken.setVisibility(View.VISIBLE);
               secDonkey.setVisibility(View.VISIBLE);
               lineDonkey.setVisibility(View.VISIBLE);
               secHorse.setVisibility(View.VISIBLE);
               lineHorse.setVisibility(View.VISIBLE);
               secPig.setVisibility(View.VISIBLE);
               linePig.setVisibility(View.VISIBLE);
               secOtherAsset.setVisibility(View.VISIBLE);
               lineOtherAsset.setVisibility(View.VISIBLE);
               secSESNote.setVisibility(View.VISIBLE);
               lineSESNote.setVisibility(View.VISIBLE);
            }
         }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secSESVStatusOth=(LinearLayout)findViewById(R.id.secSESVStatusOth);
         lineSESVStatusOth=(View)findViewById(R.id.lineSESVStatusOth);
         VlblSESVStatusOth=(TextView) findViewById(R.id.VlblSESVStatusOth);
         txtSESVStatusOth=(EditText) findViewById(R.id.txtSESVStatusOth);
         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         secWSDrink=(LinearLayout)findViewById(R.id.secWSDrink);
         lineWSDrink=(View)findViewById(R.id.lineWSDrink);
         VlblWSDrink=(TextView) findViewById(R.id.VlblWSDrink);
         spnWSDrink=(Spinner) findViewById(R.id.spnWSDrink);
         List<String> listWSDrink = new ArrayList<String>();
         
         listWSDrink.add("");
         listWSDrink.add("11-Piped water into dwelling");
         listWSDrink.add("12-Piped water into compound or yard");
         listWSDrink.add("13-Piped water to neighbor");
         listWSDrink.add("14-Piped water to public tap/standpipe");
         listWSDrink.add("21-Tube well or borehole");
         listWSDrink.add("31-Dug well protected well");
         listWSDrink.add("32-Dug well unprotected well");
         listWSDrink.add("41-Water from spring: protected spring");
         listWSDrink.add("42-Water from spring: unprotected spring");
         listWSDrink.add("51-Rainwater collection");
         listWSDrink.add("61-Delivered water: tanker-truck");
         listWSDrink.add("71-Delivered water: cart with small tank/drum");
         listWSDrink.add("81-Surface water (river, stream, dam, lake, pond, canal, etc.)");
         listWSDrink.add("91-Packaged water: bottled water");
         listWSDrink.add("92-Packaged water: sachet water");
         listWSDrink.add("93-Packaged water: Water kiosk");
         listWSDrink.add("97-Other");
         listWSDrink.add("98-Don't know");
         listWSDrink.add("99-Refused to respond");
         spnWSDrink.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listWSDrink)));

         spnWSDrink.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnWSDrink.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnWSDrink.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                    secWSDrinkOth.setVisibility(View.VISIBLE);
                    lineWSDrinkOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secWSDrinkOth.setVisibility(View.GONE);
                    lineWSDrinkOth.setVisibility(View.GONE);
                    txtWSDrinkOth.setText("");
                 }

             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secWSDrinkOth=(LinearLayout)findViewById(R.id.secWSDrinkOth);
         lineWSDrinkOth=(View)findViewById(R.id.lineWSDrinkOth);
         VlblWSDrinkOth=(TextView) findViewById(R.id.VlblWSDrinkOth);
         txtWSDrinkOth=(AutoCompleteTextView) findViewById(R.id.txtWSDrinkOth);
         txtWSDrinkOth.setAdapter(C.getArrayAdapter("Select distinct WSDrinkOth from "+ TableName +" order by WSDrinkOth"));

         txtWSDrinkOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtWSDrinkOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtWSDrinkOth.getRight() - txtWSDrinkOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secToilet=(LinearLayout)findViewById(R.id.secToilet);
         lineToilet=(View)findViewById(R.id.lineToilet);
         VlblToilet=(TextView) findViewById(R.id.VlblToilet);
         spnToilet=(Spinner) findViewById(R.id.spnToilet);
         List<String> listToilet = new ArrayList<String>();
         
         listToilet.add("");
         listToilet.add("11-Flush to piped sewer system");
         listToilet.add("12-Flush to septic tank");
         listToilet.add("13-Flush to pit latrine");
         listToilet.add("14-Flush to open drain /somewhere else");
         listToilet.add("15-Flush (don’t know where to)");
         listToilet.add("21-Pit latrines: Ventilated");
         listToilet.add("22-Pit latrines: With slab");
         listToilet.add("23-Pit latrines: Without slab/open pit");
         listToilet.add("31-Composting toilet");
         listToilet.add("41-Bucket");
         listToilet.add("51-Hanging toilet/latrine");
         listToilet.add("61-No facility/bush/field");
         listToilet.add("97-Other");
         listToilet.add("98-Don't know");
         listToilet.add("99-Refused to respond");
         spnToilet.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listToilet)));

         spnToilet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnToilet.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnToilet.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                    secToiletOth.setVisibility(View.VISIBLE);
                    lineToiletOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secToiletOth.setVisibility(View.GONE);
                    lineToiletOth.setVisibility(View.GONE);
                    txtToiletOth.setText("");
                 }

             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secToiletOth=(LinearLayout)findViewById(R.id.secToiletOth);
         lineToiletOth=(View)findViewById(R.id.lineToiletOth);
         VlblToiletOth=(TextView) findViewById(R.id.VlblToiletOth);
         txtToiletOth=(AutoCompleteTextView) findViewById(R.id.txtToiletOth);
         txtToiletOth.setAdapter(C.getArrayAdapter("Select distinct ToiletOth from "+ TableName +" order by ToiletOth"));

         txtToiletOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtToiletOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtToiletOth.getRight() - txtToiletOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secToiletShrd=(LinearLayout)findViewById(R.id.secToiletShrd);
         lineToiletShrd=(View)findViewById(R.id.lineToiletShrd);
         VlblToiletShrd = (TextView) findViewById(R.id.VlblToiletShrd);
         rdogrpToiletShrd = (RadioGroup) findViewById(R.id.rdogrpToiletShrd);
         rdoToiletShrd1 = (RadioButton) findViewById(R.id.rdoToiletShrd1);
         rdoToiletShrd2 = (RadioButton) findViewById(R.id.rdoToiletShrd2);
         rdogrpToiletShrd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpToiletShrd = new String[] {"0","1"};
             for (int i = 0; i < rdogrpToiletShrd.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpToiletShrd.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpToiletShrd[i];
             }

             if(rbData.equalsIgnoreCase("1")){
                secToiletUseNo.setVisibility(View.VISIBLE);
                lineToiletUseNo.setVisibility(View.VISIBLE);
                secToiletUseNoDk.setVisibility(View.VISIBLE);
                lineToiletUseNoDk.setVisibility(View.VISIBLE);
             }
             else
             {
                    secToiletUseNo.setVisibility(View.GONE);
                    lineToiletUseNo.setVisibility(View.GONE);
                    txtToiletUseNo.setText("");
                    secToiletUseNoDk.setVisibility(View.GONE);
                    lineToiletUseNoDk.setVisibility(View.GONE);
                    rdogrpToiletUseNoDk.clearCheck();
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secToiletUseNo=(LinearLayout)findViewById(R.id.secToiletUseNo);
         lineToiletUseNo=(View)findViewById(R.id.lineToiletUseNo);
         VlblToiletUseNo=(TextView) findViewById(R.id.VlblToiletUseNo);
         txtToiletUseNo=(EditText) findViewById(R.id.txtToiletUseNo);
         secToiletUseNoDk=(LinearLayout)findViewById(R.id.secToiletUseNoDk);
         lineToiletUseNoDk=(View)findViewById(R.id.lineToiletUseNoDk);
         VlblToiletUseNoDk = (TextView) findViewById(R.id.VlblToiletUseNoDk);
         rdogrpToiletUseNoDk = (RadioGroup) findViewById(R.id.rdogrpToiletUseNoDk);
         rdoToiletUseNoDk1 = (RadioButton) findViewById(R.id.rdoToiletUseNoDk1);
         rdoToiletUseNoDk2 = (RadioButton) findViewById(R.id.rdoToiletUseNoDk2);
         secToiletLoc=(LinearLayout)findViewById(R.id.secToiletLoc);
         lineToiletLoc=(View)findViewById(R.id.lineToiletLoc);
         VlblToiletLoc = (TextView) findViewById(R.id.VlblToiletLoc);
         rdogrpToiletLoc = (RadioGroup) findViewById(R.id.rdogrpToiletLoc);
         rdoToiletLoc1 = (RadioButton) findViewById(R.id.rdoToiletLoc1);
         rdoToiletLoc2 = (RadioButton) findViewById(R.id.rdoToiletLoc2);
         rdoToiletLoc3 = (RadioButton) findViewById(R.id.rdoToiletLoc3);
         secCookDvc=(LinearLayout)findViewById(R.id.secCookDvc);
         lineCookDvc=(View)findViewById(R.id.lineCookDvc);
         VlblCookDvc=(TextView) findViewById(R.id.VlblCookDvc);
         spnCookDvc=(Spinner) findViewById(R.id.spnCookDvc);
         List<String> listCookDvc = new ArrayList<String>();
         
         listCookDvc.add("");
         listCookDvc.add("01-Gas stove");
         listCookDvc.add("02-Electric stove");
         listCookDvc.add("03-Liquid fuel stove");
         listCookDvc.add("04-Manufactured solid fuel stove");
         listCookDvc.add("05-Movable firepan/bbq");
         listCookDvc.add("06-Solar cooker");
         listCookDvc.add("07-Three stone stove/open fire");
         listCookDvc.add("08-No cooking done by household");
         listCookDvc.add("97-Other");
         listCookDvc.add("98-Don't know");
         listCookDvc.add("99-Refused to respond");
         spnCookDvc.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listCookDvc)));

         spnCookDvc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnCookDvc.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnCookDvc.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                    secCookDvcOth.setVisibility(View.VISIBLE);
                    lineCookDvcOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secCookDvcOth.setVisibility(View.GONE);
                    lineCookDvcOth.setVisibility(View.GONE);
                    txtCookDvcOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secCookDvcOth=(LinearLayout)findViewById(R.id.secCookDvcOth);
         lineCookDvcOth=(View)findViewById(R.id.lineCookDvcOth);
         VlblCookDvcOth=(TextView) findViewById(R.id.VlblCookDvcOth);
         txtCookDvcOth=(AutoCompleteTextView) findViewById(R.id.txtCookDvcOth);
         txtCookDvcOth.setAdapter(C.getArrayAdapter("Select distinct CookDvcOth from "+ TableName +" order by CookDvcOth"));

         txtCookDvcOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtCookDvcOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtCookDvcOth.getRight() - txtCookDvcOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secCookFuel=(LinearLayout)findViewById(R.id.secCookFuel);
         lineCookFuel=(View)findViewById(R.id.lineCookFuel);
         VlblCookFuel=(TextView) findViewById(R.id.VlblCookFuel);
         spnCookFuel=(Spinner) findViewById(R.id.spnCookFuel);
         List<String> listCookFuel = new ArrayList<String>();
         
         listCookFuel.add("");
         listCookFuel.add("01-Alcohol/ethanol");
         listCookFuel.add("02-Gasoline/diesel");
         listCookFuel.add("03-Kerosene/paraffin");
         listCookFuel.add("04-Natural gas");
         listCookFuel.add("05-Coal/lignite");
         listCookFuel.add("06-Charcoal");
         listCookFuel.add("07-Wood");
         listCookFuel.add("08-Grass/straw/shrubs");
         listCookFuel.add("09-Agricultural crop residue");
         listCookFuel.add("10-Animal waste/dung");
         listCookFuel.add("11-Processed biomass");
         listCookFuel.add("12-Wood chips");
         listCookFuel.add("13-Garbage/plastic");
         listCookFuel.add("14-Sawdust");
         listCookFuel.add("15-Electricity");
         listCookFuel.add("97-Other");
         listCookFuel.add("98-Don't know");
         listCookFuel.add("99-Refused to respond");
         spnCookFuel.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listCookFuel)));

         spnCookFuel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnCookFuel.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnCookFuel.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                    secCookFuelOth.setVisibility(View.VISIBLE);
                    lineCookFuelOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secCookFuelOth.setVisibility(View.GONE);
                    lineCookFuelOth.setVisibility(View.GONE);
                    txtCookFuelOth.setText("");
                 }

             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secCookFuelOth=(LinearLayout)findViewById(R.id.secCookFuelOth);
         lineCookFuelOth=(View)findViewById(R.id.lineCookFuelOth);
         VlblCookFuelOth=(TextView) findViewById(R.id.VlblCookFuelOth);
         txtCookFuelOth=(AutoCompleteTextView) findViewById(R.id.txtCookFuelOth);
         txtCookFuelOth.setAdapter(C.getArrayAdapter("Select distinct CookFuelOth from "+ TableName +" order by CookFuelOth"));

         txtCookFuelOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtCookFuelOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtCookFuelOth.getRight() - txtCookFuelOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secCookPlc=(LinearLayout)findViewById(R.id.secCookPlc);
         lineCookPlc=(View)findViewById(R.id.lineCookPlc);
         VlblCookPlc=(TextView) findViewById(R.id.VlblCookPlc);
         spnCookPlc=(Spinner) findViewById(R.id.spnCookPlc);
         List<String> listCookPlc = new ArrayList<String>();
         
         listCookPlc.add("");
         listCookPlc.add("1-In main house: no separate room");
         listCookPlc.add("2-In main house: separate room dedicated to cooking");
         listCookPlc.add("3-Outside of main house: separate room dedicated to cooking");
         listCookPlc.add("4-Outside of main house: open air");
         listCookPlc.add("5-Outside of main house: On veranda or covered porch");
         listCookPlc.add("7-Other");
         listCookPlc.add("8-Don't know");
         listCookPlc.add("9-Refused to respond");
         spnCookPlc.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listCookPlc)));

         spnCookPlc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnCookPlc.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnCookPlc.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("7"))
                 {
                    secCookPlcOth.setVisibility(View.VISIBLE);
                    lineCookPlcOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secCookPlcOth.setVisibility(View.GONE);
                    lineCookPlcOth.setVisibility(View.GONE);
                    txtCookPlcOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secCookPlcOth=(LinearLayout)findViewById(R.id.secCookPlcOth);
         lineCookPlcOth=(View)findViewById(R.id.lineCookPlcOth);
         VlblCookPlcOth=(TextView) findViewById(R.id.VlblCookPlcOth);
         txtCookPlcOth=(AutoCompleteTextView) findViewById(R.id.txtCookPlcOth);
         txtCookPlcOth.setAdapter(C.getArrayAdapter("Select distinct CookPlcOth from "+ TableName +" order by CookPlcOth"));

         txtCookPlcOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtCookPlcOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtCookPlcOth.getRight() - txtCookPlcOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secFloor=(LinearLayout)findViewById(R.id.secFloor);
         lineFloor=(View)findViewById(R.id.lineFloor);
         VlblFloor=(TextView) findViewById(R.id.VlblFloor);
         spnFloor=(Spinner) findViewById(R.id.spnFloor);
         List<String> listFloor = new ArrayList<String>();
         
         listFloor.add("");
         listFloor.add("11-Natural floor: Earth/sand");
         listFloor.add("12-Natural floor: Dung");
         listFloor.add("21-Rudimentary floor: Wood planks");
         listFloor.add("22-Rudimentary floor: Palm/bamboo");
         listFloor.add("31-Finished floor: Parquet or polished wood");
         listFloor.add("32-Finished floor: Vinyl or asphalt strips");
         listFloor.add("33-Finished floor: Ceramic tiles");
         listFloor.add("34-Finished floor: Cement/Brick");
         listFloor.add("35-Finished floor: Carpet");
         listFloor.add("97-Other");
         listFloor.add("98-Don't know");
         listFloor.add("99-Refused to respond");
         spnFloor.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listFloor)));

         spnFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnFloor.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnFloor.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                    secFloorOth.setVisibility(View.VISIBLE);
                    lineFloorOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secFloorOth.setVisibility(View.GONE);
                    lineFloorOth.setVisibility(View.GONE);
                    txtFloorOth.setText("");
                 }

             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secFloorOth=(LinearLayout)findViewById(R.id.secFloorOth);
         lineFloorOth=(View)findViewById(R.id.lineFloorOth);
         VlblFloorOth=(TextView) findViewById(R.id.VlblFloorOth);
         txtFloorOth=(AutoCompleteTextView) findViewById(R.id.txtFloorOth);
         txtFloorOth.setAdapter(C.getArrayAdapter("Select distinct FloorOth from "+ TableName +" order by FloorOth"));

         txtFloorOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtFloorOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtFloorOth.getRight() - txtFloorOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secRoof=(LinearLayout)findViewById(R.id.secRoof);
         lineRoof=(View)findViewById(R.id.lineRoof);
         VlblRoof=(TextView) findViewById(R.id.VlblRoof);
         spnRoof=(Spinner) findViewById(R.id.spnRoof);
         List<String> listRoof = new ArrayList<String>();
         
         listRoof.add("");
         listRoof.add("11-Natural roofing: No roof");
         listRoof.add("12-Natural roofing: Thatch/palm leaf");
         listRoof.add("13-Natural roofing: Sod");
         listRoof.add("21-Rudimentary roofing: Rustic mat");
         listRoof.add("22-Rudimentary roofing: Palm/bamboo");
         listRoof.add("23-Rudimentary roofing: Wood planks");
         listRoof.add("24-Rudimentary roofing: Cardboard");
         listRoof.add("31-Finished roofing: Metal/Tin/Zinc");
         listRoof.add("32-Finished roofing: Wood");
         listRoof.add("33-Finished roofing: Calamine/cement fiber");
         listRoof.add("34-Finished roofing: Ceramic tiles");
         listRoof.add("35-Finished roofing: Cement/Brick");
         listRoof.add("36-Finished roofing: Roofing shingles");
         listRoof.add("97-Other");
         listRoof.add("98-Don't know");
         listRoof.add("99-Refused to respond");
         spnRoof.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listRoof)));

         spnRoof.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnRoof.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnRoof.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                    secRoofOth.setVisibility(View.VISIBLE);
                    lineRoofOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secRoofOth.setVisibility(View.GONE);
                    lineRoofOth.setVisibility(View.GONE);
                    txtRoofOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secRoofOth=(LinearLayout)findViewById(R.id.secRoofOth);
         lineRoofOth=(View)findViewById(R.id.lineRoofOth);
         VlblRoofOth=(TextView) findViewById(R.id.VlblRoofOth);
         txtRoofOth=(AutoCompleteTextView) findViewById(R.id.txtRoofOth);
         txtRoofOth.setAdapter(C.getArrayAdapter("Select distinct RoofOth from "+ TableName +" order by RoofOth"));

         txtRoofOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtRoofOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtRoofOth.getRight() - txtRoofOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secWall=(LinearLayout)findViewById(R.id.secWall);
         lineWall=(View)findViewById(R.id.lineWall);
         VlblWall=(TextView) findViewById(R.id.VlblWall);
         spnWall=(Spinner) findViewById(R.id.spnWall);
         List<String> listWall = new ArrayList<String>();
         
         listWall.add("");
         listWall.add("11-Natural walls: No walls");
         listWall.add("12-Natural walls: Cane/palm/trunks");
         listWall.add("13-Natural walls: Earth/soil/dung/DIRT ");
         listWall.add("21-Rudimentary walls: Bamboo with mud");
         listWall.add("22-Rudimentary walls: Stone with mud");
         listWall.add("23-Rudimentary walls: Uncovered adobe");
         listWall.add("24-Rudimentary walls: Plywood");
         listWall.add("25-Rudimentary walls: Cardboard");
         listWall.add("26-Rudimentary walls: Reused wood");
         listWall.add("31-Finished walls: Tin");
         listWall.add("32-Finished walls: Cement");
         listWall.add("33-Finished walls: Stone with lime/cement");
         listWall.add("34-Finished walls: Bricks");
         listWall.add("35-Finished walls: Concrete blocks");
         listWall.add("36-Finished walls: Wood planks/shingles");
         listWall.add("37-Finished walls: Plaster adobe");
         listWall.add("97-Other");
         listWall.add("98-Don't know");
         listWall.add("99-Refused to respond");
         spnWall.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listWall)));

         spnWall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnWall.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnWall.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                    secWallOth.setVisibility(View.VISIBLE);
                    lineWallOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secWallOth.setVisibility(View.GONE);
                    lineWallOth.setVisibility(View.GONE);
                    txtWallOth.setText("");
                 }

             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secWallOth=(LinearLayout)findViewById(R.id.secWallOth);
         lineWallOth=(View)findViewById(R.id.lineWallOth);
         VlblWallOth=(TextView) findViewById(R.id.VlblWallOth);
         txtWallOth=(AutoCompleteTextView) findViewById(R.id.txtWallOth);
         txtWallOth.setAdapter(C.getArrayAdapter("Select distinct WallOth from "+ TableName +" order by WallOth"));

         txtWallOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtWallOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtWallOth.getRight() - txtWallOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secRoomSleep=(LinearLayout)findViewById(R.id.secRoomSleep);
         lineRoomSleep=(View)findViewById(R.id.lineRoomSleep);
         VlblRoomSleep=(TextView) findViewById(R.id.VlblRoomSleep);
         txtRoomSleep=(EditText) findViewById(R.id.txtRoomSleep);
         secRoomSleepDk=(LinearLayout)findViewById(R.id.secRoomSleepDk);
         lineRoomSleepDk=(View)findViewById(R.id.lineRoomSleepDk);
         VlblRoomSleepDk = (TextView) findViewById(R.id.VlblRoomSleepDk);
         rdogrpRoomSleepDk = (RadioGroup) findViewById(R.id.rdogrpRoomSleepDk);
         rdoRoomSleepDk1 = (RadioButton) findViewById(R.id.rdoRoomSleepDk1);
         rdoRoomSleepDk2 = (RadioButton) findViewById(R.id.rdoRoomSleepDk2);
         secHomesteadAny=(LinearLayout)findViewById(R.id.secHomesteadAny);
         lineHomesteadAny=(View)findViewById(R.id.lineHomesteadAny);
         VlblHomesteadAny = (TextView) findViewById(R.id.VlblHomesteadAny);
         rdogrpHomesteadAny = (RadioGroup) findViewById(R.id.rdogrpHomesteadAny);
         rdoHomesteadAny1 = (RadioButton) findViewById(R.id.rdoHomesteadAny1);
         rdoHomesteadAny2 = (RadioButton) findViewById(R.id.rdoHomesteadAny2);
         rdoHomesteadAny3 = (RadioButton) findViewById(R.id.rdoHomesteadAny3);
         rdoHomesteadAny4 = (RadioButton) findViewById(R.id.rdoHomesteadAny4);
         secOthLand=(LinearLayout)findViewById(R.id.secOthLand);
         lineOthLand=(View)findViewById(R.id.lineOthLand);
         VlblOthLand = (TextView) findViewById(R.id.VlblOthLand);
         rdogrpOthLand = (RadioGroup) findViewById(R.id.rdogrpOthLand);
         rdoOthLand1 = (RadioButton) findViewById(R.id.rdoOthLand1);
         rdoOthLand2 = (RadioButton) findViewById(R.id.rdoOthLand2);
         rdoOthLand3 = (RadioButton) findViewById(R.id.rdoOthLand3);
         rdoOthLand4 = (RadioButton) findViewById(R.id.rdoOthLand4);
         seclblH12=(LinearLayout)findViewById(R.id.seclblH12);
         linelblH12=(View)findViewById(R.id.linelblH12);
         seclblH121=(LinearLayout)findViewById(R.id.seclblH121);
         linelblH121=(View)findViewById(R.id.linelblH121);
         secElectricity=(LinearLayout)findViewById(R.id.secElectricity);
         lineElectricity=(View)findViewById(R.id.lineElectricity);
         VlblElectricity = (TextView) findViewById(R.id.VlblElectricity);
         rdogrpElectricity = (RadioGroup) findViewById(R.id.rdogrpElectricity);
         rdoElectricity1 = (RadioButton) findViewById(R.id.rdoElectricity1);
         rdoElectricity2 = (RadioButton) findViewById(R.id.rdoElectricity2);
         secHeater=(LinearLayout)findViewById(R.id.secHeater);
         lineHeater=(View)findViewById(R.id.lineHeater);
         VlblHeater = (TextView) findViewById(R.id.VlblHeater);
         rdogrpHeater = (RadioGroup) findViewById(R.id.rdogrpHeater);
         rdoHeater1 = (RadioButton) findViewById(R.id.rdoHeater1);
         rdoHeater2 = (RadioButton) findViewById(R.id.rdoHeater2);
         secAC=(LinearLayout)findViewById(R.id.secAC);
         lineAC=(View)findViewById(R.id.lineAC);
         VlblAC = (TextView) findViewById(R.id.VlblAC);
         rdogrpAC = (RadioGroup) findViewById(R.id.rdogrpAC);
         rdoAC1 = (RadioButton) findViewById(R.id.rdoAC1);
         rdoAC2 = (RadioButton) findViewById(R.id.rdoAC2);
         secElecFan=(LinearLayout)findViewById(R.id.secElecFan);
         lineElecFan=(View)findViewById(R.id.lineElecFan);
         VlblElecFan = (TextView) findViewById(R.id.VlblElecFan);
         rdogrpElecFan = (RadioGroup) findViewById(R.id.rdogrpElecFan);
         rdoElecFan1 = (RadioButton) findViewById(R.id.rdoElecFan1);
         rdoElecFan2 = (RadioButton) findViewById(R.id.rdoElecFan2);
         secLantern=(LinearLayout)findViewById(R.id.secLantern);
         lineLantern=(View)findViewById(R.id.lineLantern);
         VlblLantern = (TextView) findViewById(R.id.VlblLantern);
         rdogrpLantern = (RadioGroup) findViewById(R.id.rdogrpLantern);
         rdoLantern1 = (RadioButton) findViewById(R.id.rdoLantern1);
         rdoLantern2 = (RadioButton) findViewById(R.id.rdoLantern2);
         secLamp=(LinearLayout)findViewById(R.id.secLamp);
         lineLamp=(View)findViewById(R.id.lineLamp);
         VlblLamp = (TextView) findViewById(R.id.VlblLamp);
         rdogrpLamp = (RadioGroup) findViewById(R.id.rdogrpLamp);
         rdoLamp1 = (RadioButton) findViewById(R.id.rdoLamp1);
         rdoLamp2 = (RadioButton) findViewById(R.id.rdoLamp2);
         secMatt=(LinearLayout)findViewById(R.id.secMatt);
         lineMatt=(View)findViewById(R.id.lineMatt);
         VlblMatt = (TextView) findViewById(R.id.VlblMatt);
         rdogrpMatt = (RadioGroup) findViewById(R.id.rdogrpMatt);
         rdoMatt1 = (RadioButton) findViewById(R.id.rdoMatt1);
         rdoMatt2 = (RadioButton) findViewById(R.id.rdoMatt2);
         secBed=(LinearLayout)findViewById(R.id.secBed);
         lineBed=(View)findViewById(R.id.lineBed);
         VlblBed = (TextView) findViewById(R.id.VlblBed);
         rdogrpBed = (RadioGroup) findViewById(R.id.rdogrpBed);
         rdoBed1 = (RadioButton) findViewById(R.id.rdoBed1);
         rdoBed2 = (RadioButton) findViewById(R.id.rdoBed2);
         secChair=(LinearLayout)findViewById(R.id.secChair);
         lineChair=(View)findViewById(R.id.lineChair);
         VlblChair = (TextView) findViewById(R.id.VlblChair);
         rdogrpChair = (RadioGroup) findViewById(R.id.rdogrpChair);
         rdoChair1 = (RadioButton) findViewById(R.id.rdoChair1);
         rdoChair2 = (RadioButton) findViewById(R.id.rdoChair2);
         secSofa=(LinearLayout)findViewById(R.id.secSofa);
         lineSofa=(View)findViewById(R.id.lineSofa);
         VlblSofa = (TextView) findViewById(R.id.VlblSofa);
         rdogrpSofa = (RadioGroup) findViewById(R.id.rdogrpSofa);
         rdoSofa1 = (RadioButton) findViewById(R.id.rdoSofa1);
         rdoSofa2 = (RadioButton) findViewById(R.id.rdoSofa2);
         secTables=(LinearLayout)findViewById(R.id.secTables);
         lineTables=(View)findViewById(R.id.lineTables);
         VlblTables = (TextView) findViewById(R.id.VlblTables);
         rdogrpTables = (RadioGroup) findViewById(R.id.rdogrpTables);
         rdoTables1 = (RadioButton) findViewById(R.id.rdoTables1);
         rdoTables2 = (RadioButton) findViewById(R.id.rdoTables2);
         secWatch=(LinearLayout)findViewById(R.id.secWatch);
         lineWatch=(View)findViewById(R.id.lineWatch);
         VlblWatch = (TextView) findViewById(R.id.VlblWatch);
         rdogrpWatch = (RadioGroup) findViewById(R.id.rdogrpWatch);
         rdoWatch1 = (RadioButton) findViewById(R.id.rdoWatch1);
         rdoWatch2 = (RadioButton) findViewById(R.id.rdoWatch2);
         secWMachine=(LinearLayout)findViewById(R.id.secWMachine);
         lineWMachine=(View)findViewById(R.id.lineWMachine);
         VlblWMachine = (TextView) findViewById(R.id.VlblWMachine);
         rdogrpWMachine = (RadioGroup) findViewById(R.id.rdogrpWMachine);
         rdoWMachine1 = (RadioButton) findViewById(R.id.rdoWMachine1);
         rdoWMachine2 = (RadioButton) findViewById(R.id.rdoWMachine2);
         secIron=(LinearLayout)findViewById(R.id.secIron);
         lineIron=(View)findViewById(R.id.lineIron);
         VlblIron = (TextView) findViewById(R.id.VlblIron);
         rdogrpIron = (RadioGroup) findViewById(R.id.rdogrpIron);
         rdoIron1 = (RadioButton) findViewById(R.id.rdoIron1);
         rdoIron2 = (RadioButton) findViewById(R.id.rdoIron2);
         secBooth=(LinearLayout)findViewById(R.id.secBooth);
         lineBooth=(View)findViewById(R.id.lineBooth);
         VlblBooth = (TextView) findViewById(R.id.VlblBooth);
         rdogrpBooth = (RadioGroup) findViewById(R.id.rdogrpBooth);
         rdoBooth1 = (RadioButton) findViewById(R.id.rdoBooth1);
         rdoBooth2 = (RadioButton) findViewById(R.id.rdoBooth2);
         secSMachine=(LinearLayout)findViewById(R.id.secSMachine);
         lineSMachine=(View)findViewById(R.id.lineSMachine);
         VlblSMachine = (TextView) findViewById(R.id.VlblSMachine);
         rdogrpSMachine = (RadioGroup) findViewById(R.id.rdogrpSMachine);
         rdoSMachine1 = (RadioButton) findViewById(R.id.rdoSMachine1);
         rdoSMachine2 = (RadioButton) findViewById(R.id.rdoSMachine2);
         secGenerator=(LinearLayout)findViewById(R.id.secGenerator);
         lineGenerator=(View)findViewById(R.id.lineGenerator);
         VlblGenerator = (TextView) findViewById(R.id.VlblGenerator);
         rdogrpGenerator = (RadioGroup) findViewById(R.id.rdogrpGenerator);
         rdoGenerator1 = (RadioButton) findViewById(R.id.rdoGenerator1);
         rdoGenerator2 = (RadioButton) findViewById(R.id.rdoGenerator2);
        secMosquito=(LinearLayout)findViewById(R.id.secMosquito);
        lineMosquito=(View)findViewById(R.id.lineMosquito);
        VlblMosquito = (TextView) findViewById(R.id.VlblMosquito);
        rdogrpMosquito = (RadioGroup) findViewById(R.id.rdogrpMosquito);
        rdoMosquito1 = (RadioButton) findViewById(R.id.rdoMosquito1);
        rdoMosquito2 = (RadioButton) findViewById(R.id.rdoMosquito2);
         seclblH12a=(LinearLayout)findViewById(R.id.seclblH12a);
         linelblH12a=(View)findViewById(R.id.linelblH12a);
         secInternet=(LinearLayout)findViewById(R.id.secInternet);
         lineInternet=(View)findViewById(R.id.lineInternet);
         VlblInternet = (TextView) findViewById(R.id.VlblInternet);
         rdogrpInternet = (RadioGroup) findViewById(R.id.rdogrpInternet);
         rdoInternet1 = (RadioButton) findViewById(R.id.rdoInternet1);
         rdoInternet2 = (RadioButton) findViewById(R.id.rdoInternet2);
         secSatellite=(LinearLayout)findViewById(R.id.secSatellite);
         lineSatellite=(View)findViewById(R.id.lineSatellite);
         VlblSatellite = (TextView) findViewById(R.id.VlblSatellite);
         rdogrpSatellite = (RadioGroup) findViewById(R.id.rdogrpSatellite);
         rdoSatellite1 = (RadioButton) findViewById(R.id.rdoSatellite1);
         rdoSatellite2 = (RadioButton) findViewById(R.id.rdoSatellite2);
         secLandline=(LinearLayout)findViewById(R.id.secLandline);
         lineLandline=(View)findViewById(R.id.lineLandline);
         VlblLandline = (TextView) findViewById(R.id.VlblLandline);
         rdogrpLandline = (RadioGroup) findViewById(R.id.rdogrpLandline);
         rdoLandline1 = (RadioButton) findViewById(R.id.rdoLandline1);
         rdoLandline2 = (RadioButton) findViewById(R.id.rdoLandline2);
         secCellphone=(LinearLayout)findViewById(R.id.secCellphone);
         lineCellphone=(View)findViewById(R.id.lineCellphone);
         VlblCellphone = (TextView) findViewById(R.id.VlblCellphone);
         rdogrpCellphone = (RadioGroup) findViewById(R.id.rdogrpCellphone);
         rdoCellphone1 = (RadioButton) findViewById(R.id.rdoCellphone1);
         rdoCellphone2 = (RadioButton) findViewById(R.id.rdoCellphone2);
         secTV=(LinearLayout)findViewById(R.id.secTV);
         lineTV=(View)findViewById(R.id.lineTV);
         VlblTV = (TextView) findViewById(R.id.VlblTV);
         rdogrpTV = (RadioGroup) findViewById(R.id.rdogrpTV);
         rdoTV1 = (RadioButton) findViewById(R.id.rdoTV1);
         rdoTV2 = (RadioButton) findViewById(R.id.rdoTV2);
         secRadio=(LinearLayout)findViewById(R.id.secRadio);
         lineRadio=(View)findViewById(R.id.lineRadio);
         VlblRadio = (TextView) findViewById(R.id.VlblRadio);
         rdogrpRadio = (RadioGroup) findViewById(R.id.rdogrpRadio);
         rdoRadio1 = (RadioButton) findViewById(R.id.rdoRadio1);
         rdoRadio2 = (RadioButton) findViewById(R.id.rdoRadio2);
         secDVD=(LinearLayout)findViewById(R.id.secDVD);
         lineDVD=(View)findViewById(R.id.lineDVD);
         VlblDVD = (TextView) findViewById(R.id.VlblDVD);
         rdogrpDVD = (RadioGroup) findViewById(R.id.rdogrpDVD);
         rdoDVD1 = (RadioButton) findViewById(R.id.rdoDVD1);
         rdoDVD2 = (RadioButton) findViewById(R.id.rdoDVD2);
         secVideo=(LinearLayout)findViewById(R.id.secVideo);
         lineVideo=(View)findViewById(R.id.lineVideo);
         VlblVideo = (TextView) findViewById(R.id.VlblVideo);
         rdogrpVideo = (RadioGroup) findViewById(R.id.rdogrpVideo);
         rdoVideo1 = (RadioButton) findViewById(R.id.rdoVideo1);
         rdoVideo2 = (RadioButton) findViewById(R.id.rdoVideo2);
         secComputer=(LinearLayout)findViewById(R.id.secComputer);
         lineComputer=(View)findViewById(R.id.lineComputer);
         VlblComputer = (TextView) findViewById(R.id.VlblComputer);
         rdogrpComputer = (RadioGroup) findViewById(R.id.rdogrpComputer);
         rdoComputer1 = (RadioButton) findViewById(R.id.rdoComputer1);
         rdoComputer2 = (RadioButton) findViewById(R.id.rdoComputer2);
         secCable=(LinearLayout)findViewById(R.id.secCable);
         lineCable=(View)findViewById(R.id.lineCable);
         VlblCable = (TextView) findViewById(R.id.VlblCable);
         rdogrpCable = (RadioGroup) findViewById(R.id.rdogrpCable);
         rdoCable1 = (RadioButton) findViewById(R.id.rdoCable1);
         rdoCable2 = (RadioButton) findViewById(R.id.rdoCable2);
         seclblH12b=(LinearLayout)findViewById(R.id.seclblH12b);
         linelblH12b=(View)findViewById(R.id.linelblH12b);
         secMicrowave=(LinearLayout)findViewById(R.id.secMicrowave);
         lineMicrowave=(View)findViewById(R.id.lineMicrowave);
         VlblMicrowave = (TextView) findViewById(R.id.VlblMicrowave);
         rdogrpMicrowave = (RadioGroup) findViewById(R.id.rdogrpMicrowave);
         rdoMicrowave1 = (RadioButton) findViewById(R.id.rdoMicrowave1);
         rdoMicrowave2 = (RadioButton) findViewById(R.id.rdoMicrowave2);
         secGeyser=(LinearLayout)findViewById(R.id.secGeyser);
         lineGeyser=(View)findViewById(R.id.lineGeyser);
         VlblGeyser = (TextView) findViewById(R.id.VlblGeyser);
         rdogrpGeyser = (RadioGroup) findViewById(R.id.rdogrpGeyser);
         rdoGeyser1 = (RadioButton) findViewById(R.id.rdoGeyser1);
         rdoGeyser2 = (RadioButton) findViewById(R.id.rdoGeyser2);
         secGrill=(LinearLayout)findViewById(R.id.secGrill);
         lineGrill=(View)findViewById(R.id.lineGrill);
         VlblGrill = (TextView) findViewById(R.id.VlblGrill);
         rdogrpGrill = (RadioGroup) findViewById(R.id.rdogrpGrill);
         rdoGrill1 = (RadioButton) findViewById(R.id.rdoGrill1);
         rdoGrill2 = (RadioButton) findViewById(R.id.rdoGrill2);
         secFridge=(LinearLayout)findViewById(R.id.secFridge);
         lineFridge=(View)findViewById(R.id.lineFridge);
         VlblFridge = (TextView) findViewById(R.id.VlblFridge);
         rdogrpFridge = (RadioGroup) findViewById(R.id.rdogrpFridge);
         rdoFridge1 = (RadioButton) findViewById(R.id.rdoFridge1);
         rdoFridge2 = (RadioButton) findViewById(R.id.rdoFridge2);
         secDeepFreezer=(LinearLayout)findViewById(R.id.secDeepFreezer);
         lineDeepFreezer=(View)findViewById(R.id.lineDeepFreezer);
         VlblDeepFreezer = (TextView) findViewById(R.id.VlblDeepFreezer);
         rdogrpDeepFreezer = (RadioGroup) findViewById(R.id.rdogrpDeepFreezer);
         rdoDeepFreezer1 = (RadioButton) findViewById(R.id.rdoDeepFreezer1);
         rdoDeepFreezer2 = (RadioButton) findViewById(R.id.rdoDeepFreezer2);
         secStove=(LinearLayout)findViewById(R.id.secStove);
         lineStove=(View)findViewById(R.id.lineStove);
         VlblStove = (TextView) findViewById(R.id.VlblStove);
         rdogrpStove = (RadioGroup) findViewById(R.id.rdogrpStove);
         rdoStove1 = (RadioButton) findViewById(R.id.rdoStove1);
         rdoStove2 = (RadioButton) findViewById(R.id.rdoStove2);
        secBlender=(LinearLayout)findViewById(R.id.secBlender);
        lineBlender=(View)findViewById(R.id.lineBlender);
        VlblBlender = (TextView) findViewById(R.id.VlblBlender);
        rdogrpBlender = (RadioGroup) findViewById(R.id.rdogrpBlender);
        rdoBlender1 = (RadioButton) findViewById(R.id.rdoBlender1);
        rdoBlender2 = (RadioButton) findViewById(R.id.rdoBlender2);
         seclblH12c=(LinearLayout)findViewById(R.id.seclblH12c);
         linelblH12c=(View)findViewById(R.id.linelblH12c);
         secBike=(LinearLayout)findViewById(R.id.secBike);
         lineBike=(View)findViewById(R.id.lineBike);
         VlblBike = (TextView) findViewById(R.id.VlblBike);
         rdogrpBike = (RadioGroup) findViewById(R.id.rdogrpBike);
         rdoBike1 = (RadioButton) findViewById(R.id.rdoBike1);
         rdoBike2 = (RadioButton) findViewById(R.id.rdoBike2);
         secMotorcycle=(LinearLayout)findViewById(R.id.secMotorcycle);
         lineMotorcycle=(View)findViewById(R.id.lineMotorcycle);
         VlblMotorcycle = (TextView) findViewById(R.id.VlblMotorcycle);
         rdogrpMotorcycle = (RadioGroup) findViewById(R.id.rdogrpMotorcycle);
         rdoMotorcycle1 = (RadioButton) findViewById(R.id.rdoMotorcycle1);
         rdoMotorcycle2 = (RadioButton) findViewById(R.id.rdoMotorcycle2);
         secCar=(LinearLayout)findViewById(R.id.secCar);
         lineCar=(View)findViewById(R.id.lineCar);
         VlblCar = (TextView) findViewById(R.id.VlblCar);
         rdogrpCar = (RadioGroup) findViewById(R.id.rdogrpCar);
         rdoCar1 = (RadioButton) findViewById(R.id.rdoCar1);
         rdoCar2 = (RadioButton) findViewById(R.id.rdoCar2);
         secRickshaw=(LinearLayout)findViewById(R.id.secRickshaw);
         lineRickshaw=(View)findViewById(R.id.lineRickshaw);
         VlblRickshaw = (TextView) findViewById(R.id.VlblRickshaw);
         rdogrpRickshaw = (RadioGroup) findViewById(R.id.rdogrpRickshaw);
         rdoRickshaw1 = (RadioButton) findViewById(R.id.rdoRickshaw1);
         rdoRickshaw2 = (RadioButton) findViewById(R.id.rdoRickshaw2);
         secCart=(LinearLayout)findViewById(R.id.secCart);
         lineCart=(View)findViewById(R.id.lineCart);
         VlblCart = (TextView) findViewById(R.id.VlblCart);
         rdogrpCart = (RadioGroup) findViewById(R.id.rdogrpCart);
         rdoCart1 = (RadioButton) findViewById(R.id.rdoCart1);
         rdoCart2 = (RadioButton) findViewById(R.id.rdoCart2);
         secCanoe=(LinearLayout)findViewById(R.id.secCanoe);
         lineCanoe=(View)findViewById(R.id.lineCanoe);
         VlblCanoe = (TextView) findViewById(R.id.VlblCanoe);
         rdogrpCanoe = (RadioGroup) findViewById(R.id.rdogrpCanoe);
         rdoCanoe1 = (RadioButton) findViewById(R.id.rdoCanoe1);
         rdoCanoe2 = (RadioButton) findViewById(R.id.rdoCanoe2);
         secBus=(LinearLayout)findViewById(R.id.secBus);
         lineBus=(View)findViewById(R.id.lineBus);
         VlblBus = (TextView) findViewById(R.id.VlblBus);
         rdogrpBus = (RadioGroup) findViewById(R.id.rdogrpBus);
         rdoBus1 = (RadioButton) findViewById(R.id.rdoBus1);
         rdoBus2 = (RadioButton) findViewById(R.id.rdoBus2);
         seclblH12d=(LinearLayout)findViewById(R.id.seclblH12d);
         linelblH12d=(View)findViewById(R.id.linelblH12d);
         secTractor=(LinearLayout)findViewById(R.id.secTractor);
         lineTractor=(View)findViewById(R.id.lineTractor);
         VlblTractor = (TextView) findViewById(R.id.VlblTractor);
         rdogrpTractor = (RadioGroup) findViewById(R.id.rdogrpTractor);
         rdoTractor1 = (RadioButton) findViewById(R.id.rdoTractor1);
         rdoTractor2 = (RadioButton) findViewById(R.id.rdoTractor2);
         secPlow=(LinearLayout)findViewById(R.id.secPlow);
         linePlow=(View)findViewById(R.id.linePlow);
         VlblPlow = (TextView) findViewById(R.id.VlblPlow);
         rdogrpPlow = (RadioGroup) findViewById(R.id.rdogrpPlow);
         rdoPlow1 = (RadioButton) findViewById(R.id.rdoPlow1);
         rdoPlow2 = (RadioButton) findViewById(R.id.rdoPlow2);
         secDuck=(LinearLayout)findViewById(R.id.secDuck);
         lineDuck=(View)findViewById(R.id.lineDuck);
         VlblDuck = (TextView) findViewById(R.id.VlblDuck);
         rdogrpDuck = (RadioGroup) findViewById(R.id.rdogrpDuck);
         rdoDuck1 = (RadioButton) findViewById(R.id.rdoDuck1);
         rdoDuck2 = (RadioButton) findViewById(R.id.rdoDuck2);
         secCow=(LinearLayout)findViewById(R.id.secCow);
         lineCow=(View)findViewById(R.id.lineCow);
         VlblCow = (TextView) findViewById(R.id.VlblCow);
         rdogrpCow = (RadioGroup) findViewById(R.id.rdogrpCow);
         rdoCow1 = (RadioButton) findViewById(R.id.rdoCow1);
         rdoCow2 = (RadioButton) findViewById(R.id.rdoCow2);
         secSheep=(LinearLayout)findViewById(R.id.secSheep);
         lineSheep=(View)findViewById(R.id.lineSheep);
         VlblSheep = (TextView) findViewById(R.id.VlblSheep);
         rdogrpSheep = (RadioGroup) findViewById(R.id.rdogrpSheep);
         rdoSheep1 = (RadioButton) findViewById(R.id.rdoSheep1);
         rdoSheep2 = (RadioButton) findViewById(R.id.rdoSheep2);
         secGoat=(LinearLayout)findViewById(R.id.secGoat);
         lineGoat=(View)findViewById(R.id.lineGoat);
         VlblGoat = (TextView) findViewById(R.id.VlblGoat);
         rdogrpGoat = (RadioGroup) findViewById(R.id.rdogrpGoat);
         rdoGoat1 = (RadioButton) findViewById(R.id.rdoGoat1);
         rdoGoat2 = (RadioButton) findViewById(R.id.rdoGoat2);
         secChicken=(LinearLayout)findViewById(R.id.secChicken);
         lineChicken=(View)findViewById(R.id.lineChicken);
         VlblChicken = (TextView) findViewById(R.id.VlblChicken);
         rdogrpChicken = (RadioGroup) findViewById(R.id.rdogrpChicken);
         rdoChicken1 = (RadioButton) findViewById(R.id.rdoChicken1);
         rdoChicken2 = (RadioButton) findViewById(R.id.rdoChicken2);
         secDonkey=(LinearLayout)findViewById(R.id.secDonkey);
         lineDonkey=(View)findViewById(R.id.lineDonkey);
         VlblDonkey = (TextView) findViewById(R.id.VlblDonkey);
         rdogrpDonkey = (RadioGroup) findViewById(R.id.rdogrpDonkey);
         rdoDonkey1 = (RadioButton) findViewById(R.id.rdoDonkey1);
         rdoDonkey2 = (RadioButton) findViewById(R.id.rdoDonkey2);
         secHorse=(LinearLayout)findViewById(R.id.secHorse);
         lineHorse=(View)findViewById(R.id.lineHorse);
         VlblHorse = (TextView) findViewById(R.id.VlblHorse);
         rdogrpHorse = (RadioGroup) findViewById(R.id.rdogrpHorse);
         rdoHorse1 = (RadioButton) findViewById(R.id.rdoHorse1);
         rdoHorse2 = (RadioButton) findViewById(R.id.rdoHorse2);
         secPig=(LinearLayout)findViewById(R.id.secPig);
         linePig=(View)findViewById(R.id.linePig);
         VlblPig = (TextView) findViewById(R.id.VlblPig);
         rdogrpPig = (RadioGroup) findViewById(R.id.rdogrpPig);
         rdoPig1 = (RadioButton) findViewById(R.id.rdoPig1);
         rdoPig2 = (RadioButton) findViewById(R.id.rdoPig2);
         secOtherAsset=(LinearLayout)findViewById(R.id.secOtherAsset);
         lineOtherAsset=(View)findViewById(R.id.lineOtherAsset);
         VlblOtherAsset = (TextView) findViewById(R.id.VlblOtherAsset);
         rdogrpOtherAsset = (RadioGroup) findViewById(R.id.rdogrpOtherAsset);
         rdoOtherAsset1 = (RadioButton) findViewById(R.id.rdoOtherAsset1);
         rdoOtherAsset2 = (RadioButton) findViewById(R.id.rdoOtherAsset2);
         rdogrpOtherAsset.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpOtherAsset = new String[] {"0","1"};
             for (int i = 0; i < rdogrpOtherAsset.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpOtherAsset.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpOtherAsset[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secOtherAsset1.setVisibility(View.VISIBLE);
                lineOtherAsset1.setVisibility(View.VISIBLE);
                secOtherAsset2.setVisibility(View.VISIBLE);
                lineOtherAsset2.setVisibility(View.VISIBLE);
                secOtherAsset3.setVisibility(View.VISIBLE);
                lineOtherAsset3.setVisibility(View.VISIBLE);
             }
             else
             {
                    secOtherAsset1.setVisibility(View.GONE);
                    lineOtherAsset1.setVisibility(View.GONE);
                    txtOtherAsset1.setText("");
                    secOtherAsset2.setVisibility(View.GONE);
                    lineOtherAsset2.setVisibility(View.GONE);
                    txtOtherAsset2.setText("");
                    secOtherAsset3.setVisibility(View.GONE);
                    lineOtherAsset3.setVisibility(View.GONE);
                    txtOtherAsset3.setText("");
             }

            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secOtherAsset1=(LinearLayout)findViewById(R.id.secOtherAsset1);
         lineOtherAsset1=(View)findViewById(R.id.lineOtherAsset1);
         VlblOtherAsset1=(TextView) findViewById(R.id.VlblOtherAsset1);
         txtOtherAsset1=(AutoCompleteTextView) findViewById(R.id.txtOtherAsset1);
         txtOtherAsset1.setAdapter(C.getArrayAdapter("Select distinct OtherAsset1 from "+ TableName +" order by OtherAsset1"));

         txtOtherAsset1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtOtherAsset1.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtOtherAsset1.getRight() - txtOtherAsset1.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secOtherAsset2=(LinearLayout)findViewById(R.id.secOtherAsset2);
         lineOtherAsset2=(View)findViewById(R.id.lineOtherAsset2);
         VlblOtherAsset2=(TextView) findViewById(R.id.VlblOtherAsset2);
         txtOtherAsset2=(AutoCompleteTextView) findViewById(R.id.txtOtherAsset2);
         txtOtherAsset2.setAdapter(C.getArrayAdapter("Select distinct OtherAsset2 from "+ TableName +" order by OtherAsset2"));

         txtOtherAsset2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtOtherAsset2.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtOtherAsset2.getRight() - txtOtherAsset2.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secOtherAsset3=(LinearLayout)findViewById(R.id.secOtherAsset3);
         lineOtherAsset3=(View)findViewById(R.id.lineOtherAsset3);
         VlblOtherAsset3=(TextView) findViewById(R.id.VlblOtherAsset3);
         txtOtherAsset3=(AutoCompleteTextView) findViewById(R.id.txtOtherAsset3);
         txtOtherAsset3.setAdapter(C.getArrayAdapter("Select distinct OtherAsset3 from "+ TableName +" order by OtherAsset3"));

         txtOtherAsset3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtOtherAsset3.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtOtherAsset3.getRight() - txtOtherAsset3.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secSESNote=(LinearLayout)findViewById(R.id.secSESNote);
         lineSESNote=(View)findViewById(R.id.lineSESNote);
         VlblSESNote=(TextView) findViewById(R.id.VlblSESNote);
         txtSESNote=(EditText) findViewById(R.id.txtSESNote);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(SES.this, e.getMessage());
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
         	Connection.MessageBox(SES.this, ValidationMSG);
         	return;
         }
 
         String SQL = "";
         RadioButton rb;

         SES_DataModel objSave = new SES_DataModel();
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setSESNo(txtSESNo.getText().toString());
         objSave.setSESVDate(dtpSESVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpSESVDate.getText().toString()) : dtpSESVDate.getText().toString());
         String[] d_rdogrpSESVStatus = new String[] {"1","2","3","9"};
         objSave.setSESVStatus("");
         for (int i = 0; i < rdogrpSESVStatus.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSESVStatus.getChildAt(i);
             if (rb.isChecked()) objSave.setSESVStatus(d_rdogrpSESVStatus[i]);
         }

         objSave.setSESVStatusOth(txtSESVStatusOth.getText().toString());
         objSave.setRnd(txtRnd.getText().toString());
         objSave.setWSDrink(spnWSDrink.getSelectedItemPosition() == 0 ? "" : spnWSDrink.getSelectedItem().toString().split("-")[0]);
         objSave.setWSDrinkOth(txtWSDrinkOth.getText().toString());
         objSave.setToilet(spnToilet.getSelectedItemPosition() == 0 ? "" : spnToilet.getSelectedItem().toString().split("-")[0]);
         objSave.setToiletOth(txtToiletOth.getText().toString());
         String[] d_rdogrpToiletShrd = new String[] {"0","1"};
         objSave.setToiletShrd("");
         for (int i = 0; i < rdogrpToiletShrd.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpToiletShrd.getChildAt(i);
             if (rb.isChecked()) objSave.setToiletShrd(d_rdogrpToiletShrd[i]);
         }

         objSave.setToiletUseNo(txtToiletUseNo.getText().toString());
         String[] d_rdogrpToiletUseNoDk = new String[] {"95","98"};
         objSave.setToiletUseNoDk("");
         for (int i = 0; i < rdogrpToiletUseNoDk.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpToiletUseNoDk.getChildAt(i);
             if (rb.isChecked()) objSave.setToiletUseNoDk(d_rdogrpToiletUseNoDk[i]);
         }

         String[] d_rdogrpToiletLoc = new String[] {"1","2","3"};
         objSave.setToiletLoc("");
         for (int i = 0; i < rdogrpToiletLoc.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpToiletLoc.getChildAt(i);
             if (rb.isChecked()) objSave.setToiletLoc(d_rdogrpToiletLoc[i]);
         }

         objSave.setCookDvc(spnCookDvc.getSelectedItemPosition() == 0 ? "" : spnCookDvc.getSelectedItem().toString().split("-")[0]);
         objSave.setCookDvcOth(txtCookDvcOth.getText().toString());
         objSave.setCookFuel(spnCookFuel.getSelectedItemPosition() == 0 ? "" : spnCookFuel.getSelectedItem().toString().split("-")[0]);
         objSave.setCookFuelOth(txtCookFuelOth.getText().toString());
         objSave.setCookPlc(spnCookPlc.getSelectedItemPosition() == 0 ? "" : spnCookPlc.getSelectedItem().toString().split("-")[0]);
         objSave.setCookPlcOth(txtCookPlcOth.getText().toString());
         objSave.setFloor(spnFloor.getSelectedItemPosition() == 0 ? "" : spnFloor.getSelectedItem().toString().split("-")[0]);
         objSave.setFloorOth(txtFloorOth.getText().toString());
         objSave.setRoof(spnRoof.getSelectedItemPosition() == 0 ? "" : spnRoof.getSelectedItem().toString().split("-")[0]);
         objSave.setRoofOth(txtRoofOth.getText().toString());
         objSave.setWall(spnWall.getSelectedItemPosition() == 0 ? "" : spnWall.getSelectedItem().toString().split("-")[0]);
         objSave.setWallOth(txtWallOth.getText().toString());
         objSave.setRoomSleep(txtRoomSleep.getText().toString());
         String[] d_rdogrpRoomSleepDk = new String[] {"98","99"};
         objSave.setRoomSleepDk("");
         for (int i = 0; i < rdogrpRoomSleepDk.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpRoomSleepDk.getChildAt(i);
             if (rb.isChecked()) objSave.setRoomSleepDk(d_rdogrpRoomSleepDk[i]);
         }

         String[] d_rdogrpHomesteadAny = new String[] {"0","1","8","9"};
         objSave.setHomesteadAny("");
         for (int i = 0; i < rdogrpHomesteadAny.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpHomesteadAny.getChildAt(i);
             if (rb.isChecked()) objSave.setHomesteadAny(d_rdogrpHomesteadAny[i]);
         }

         String[] d_rdogrpOthLand = new String[] {"0","1","8","9"};
         objSave.setOthLand("");
         for (int i = 0; i < rdogrpOthLand.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpOthLand.getChildAt(i);
             if (rb.isChecked()) objSave.setOthLand(d_rdogrpOthLand[i]);
         }

         String[] d_rdogrpElectricity = new String[] {"0","1"};
         objSave.setElectricity("");
         for (int i = 0; i < rdogrpElectricity.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpElectricity.getChildAt(i);
             if (rb.isChecked()) objSave.setElectricity(d_rdogrpElectricity[i]);
         }

         String[] d_rdogrpHeater = new String[] {"0","1"};
         objSave.setHeater("");
         for (int i = 0; i < rdogrpHeater.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpHeater.getChildAt(i);
             if (rb.isChecked()) objSave.setHeater(d_rdogrpHeater[i]);
         }

         String[] d_rdogrpAC = new String[] {"0","1"};
         objSave.setAC("");
         for (int i = 0; i < rdogrpAC.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpAC.getChildAt(i);
             if (rb.isChecked()) objSave.setAC(d_rdogrpAC[i]);
         }

         String[] d_rdogrpElecFan = new String[] {"0","1"};
         objSave.setElecFan("");
         for (int i = 0; i < rdogrpElecFan.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpElecFan.getChildAt(i);
             if (rb.isChecked()) objSave.setElecFan(d_rdogrpElecFan[i]);
         }

         String[] d_rdogrpLantern = new String[] {"0","1"};
         objSave.setLantern("");
         for (int i = 0; i < rdogrpLantern.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLantern.getChildAt(i);
             if (rb.isChecked()) objSave.setLantern(d_rdogrpLantern[i]);
         }

         String[] d_rdogrpLamp = new String[] {"0","1"};
         objSave.setLamp("");
         for (int i = 0; i < rdogrpLamp.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLamp.getChildAt(i);
             if (rb.isChecked()) objSave.setLamp(d_rdogrpLamp[i]);
         }

         String[] d_rdogrpMatt = new String[] {"0","1"};
         objSave.setMatt("");
         for (int i = 0; i < rdogrpMatt.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMatt.getChildAt(i);
             if (rb.isChecked()) objSave.setMatt(d_rdogrpMatt[i]);
         }

         String[] d_rdogrpBed = new String[] {"0","1"};
         objSave.setBed("");
         for (int i = 0; i < rdogrpBed.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpBed.getChildAt(i);
             if (rb.isChecked()) objSave.setBed(d_rdogrpBed[i]);
         }

         String[] d_rdogrpChair = new String[] {"0","1"};
         objSave.setChair("");
         for (int i = 0; i < rdogrpChair.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpChair.getChildAt(i);
             if (rb.isChecked()) objSave.setChair(d_rdogrpChair[i]);
         }

         String[] d_rdogrpSofa = new String[] {"0","1"};
         objSave.setSofa("");
         for (int i = 0; i < rdogrpSofa.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSofa.getChildAt(i);
             if (rb.isChecked()) objSave.setSofa(d_rdogrpSofa[i]);
         }

         String[] d_rdogrpTables = new String[] {"0","1"};
         objSave.setTables("");
         for (int i = 0; i < rdogrpTables.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpTables.getChildAt(i);
             if (rb.isChecked()) objSave.setTables(d_rdogrpTables[i]);
         }

         String[] d_rdogrpWatch = new String[] {"0","1"};
         objSave.setWatch("");
         for (int i = 0; i < rdogrpWatch.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpWatch.getChildAt(i);
             if (rb.isChecked()) objSave.setWatch(d_rdogrpWatch[i]);
         }

         String[] d_rdogrpWMachine = new String[] {"0","1"};
         objSave.setWMachine("");
         for (int i = 0; i < rdogrpWMachine.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpWMachine.getChildAt(i);
             if (rb.isChecked()) objSave.setWMachine(d_rdogrpWMachine[i]);
         }

         String[] d_rdogrpIron = new String[] {"0","1"};
         objSave.setIron("");
         for (int i = 0; i < rdogrpIron.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpIron.getChildAt(i);
             if (rb.isChecked()) objSave.setIron(d_rdogrpIron[i]);
         }

         String[] d_rdogrpBooth = new String[] {"0","1"};
         objSave.setBooth("");
         for (int i = 0; i < rdogrpBooth.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpBooth.getChildAt(i);
             if (rb.isChecked()) objSave.setBooth(d_rdogrpBooth[i]);
         }

         String[] d_rdogrpSMachine = new String[] {"0","1"};
         objSave.setSMachine("");
         for (int i = 0; i < rdogrpSMachine.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSMachine.getChildAt(i);
             if (rb.isChecked()) objSave.setSMachine(d_rdogrpSMachine[i]);
         }

         String[] d_rdogrpGenerator = new String[] {"0","1"};
         objSave.setGenerator("");
         for (int i = 0; i < rdogrpGenerator.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGenerator.getChildAt(i);
             if (rb.isChecked()) objSave.setGenerator(d_rdogrpGenerator[i]);
         }

        String[] d_rdogrpMosquito = new String[] {"0","1"};
        objSave.setMosquito("");
        for (int i = 0; i < rdogrpMosquito.getChildCount(); i++)
        {
           rb = (RadioButton)rdogrpMosquito.getChildAt(i);
           if (rb.isChecked()) objSave.setMosquito(d_rdogrpMosquito[i]);
        }

         String[] d_rdogrpInternet = new String[] {"0","1"};
         objSave.setInternet("");
         for (int i = 0; i < rdogrpInternet.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpInternet.getChildAt(i);
             if (rb.isChecked()) objSave.setInternet(d_rdogrpInternet[i]);
         }

         String[] d_rdogrpSatellite = new String[] {"0","1"};
         objSave.setSatellite("");
         for (int i = 0; i < rdogrpSatellite.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSatellite.getChildAt(i);
             if (rb.isChecked()) objSave.setSatellite(d_rdogrpSatellite[i]);
         }

         String[] d_rdogrpLandline = new String[] {"0","1"};
         objSave.setLandline("");
         for (int i = 0; i < rdogrpLandline.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLandline.getChildAt(i);
             if (rb.isChecked()) objSave.setLandline(d_rdogrpLandline[i]);
         }

         String[] d_rdogrpCellphone = new String[] {"0","1"};
         objSave.setCellphone("");
         for (int i = 0; i < rdogrpCellphone.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCellphone.getChildAt(i);
             if (rb.isChecked()) objSave.setCellphone(d_rdogrpCellphone[i]);
         }

         String[] d_rdogrpTV = new String[] {"0","1"};
         objSave.setTV("");
         for (int i = 0; i < rdogrpTV.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpTV.getChildAt(i);
             if (rb.isChecked()) objSave.setTV(d_rdogrpTV[i]);
         }

         String[] d_rdogrpRadio = new String[] {"0","1"};
         objSave.setRadio("");
         for (int i = 0; i < rdogrpRadio.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpRadio.getChildAt(i);
             if (rb.isChecked()) objSave.setRadio(d_rdogrpRadio[i]);
         }

         String[] d_rdogrpDVD = new String[] {"0","1"};
         objSave.setDVD("");
         for (int i = 0; i < rdogrpDVD.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDVD.getChildAt(i);
             if (rb.isChecked()) objSave.setDVD(d_rdogrpDVD[i]);
         }

         String[] d_rdogrpVideo = new String[] {"0","1"};
         objSave.setVideo("");
         for (int i = 0; i < rdogrpVideo.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpVideo.getChildAt(i);
             if (rb.isChecked()) objSave.setVideo(d_rdogrpVideo[i]);
         }

         String[] d_rdogrpComputer = new String[] {"0","1"};
         objSave.setComputer("");
         for (int i = 0; i < rdogrpComputer.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpComputer.getChildAt(i);
             if (rb.isChecked()) objSave.setComputer(d_rdogrpComputer[i]);
         }

         String[] d_rdogrpCable = new String[] {"0","1"};
         objSave.setCable("");
         for (int i = 0; i < rdogrpCable.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCable.getChildAt(i);
             if (rb.isChecked()) objSave.setCable(d_rdogrpCable[i]);
         }

         String[] d_rdogrpMicrowave = new String[] {"0","1"};
         objSave.setMicrowave("");
         for (int i = 0; i < rdogrpMicrowave.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMicrowave.getChildAt(i);
             if (rb.isChecked()) objSave.setMicrowave(d_rdogrpMicrowave[i]);
         }

         String[] d_rdogrpGeyser = new String[] {"0","1"};
         objSave.setGeyser("");
         for (int i = 0; i < rdogrpGeyser.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGeyser.getChildAt(i);
             if (rb.isChecked()) objSave.setGeyser(d_rdogrpGeyser[i]);
         }

         String[] d_rdogrpGrill = new String[] {"0","1"};
         objSave.setGrill("");
         for (int i = 0; i < rdogrpGrill.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGrill.getChildAt(i);
             if (rb.isChecked()) objSave.setGrill(d_rdogrpGrill[i]);
         }

         String[] d_rdogrpFridge = new String[] {"0","1"};
         objSave.setFridge("");
         for (int i = 0; i < rdogrpFridge.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpFridge.getChildAt(i);
             if (rb.isChecked()) objSave.setFridge(d_rdogrpFridge[i]);
         }

         String[] d_rdogrpDeepFreezer = new String[] {"0","1"};
         objSave.setDeepFreezer("");
         for (int i = 0; i < rdogrpDeepFreezer.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDeepFreezer.getChildAt(i);
             if (rb.isChecked()) objSave.setDeepFreezer(d_rdogrpDeepFreezer[i]);
         }

         String[] d_rdogrpStove = new String[] {"0","1"};
         objSave.setStove("");
         for (int i = 0; i < rdogrpStove.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpStove.getChildAt(i);
             if (rb.isChecked()) objSave.setStove(d_rdogrpStove[i]);
         }

        String[] d_rdogrpBlender = new String[] {"0","1"};
        objSave.setBlender("");
        for (int i = 0; i < rdogrpBlender.getChildCount(); i++)
        {
           rb = (RadioButton)rdogrpBlender.getChildAt(i);
           if (rb.isChecked()) objSave.setBlender(d_rdogrpBlender[i]);
        }

         String[] d_rdogrpBike = new String[] {"0","1"};
         objSave.setBike("");
         for (int i = 0; i < rdogrpBike.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpBike.getChildAt(i);
             if (rb.isChecked()) objSave.setBike(d_rdogrpBike[i]);
         }

         String[] d_rdogrpMotorcycle = new String[] {"0","1"};
         objSave.setMotorcycle("");
         for (int i = 0; i < rdogrpMotorcycle.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMotorcycle.getChildAt(i);
             if (rb.isChecked()) objSave.setMotorcycle(d_rdogrpMotorcycle[i]);
         }

         String[] d_rdogrpCar = new String[] {"0","1"};
         objSave.setCar("");
         for (int i = 0; i < rdogrpCar.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCar.getChildAt(i);
             if (rb.isChecked()) objSave.setCar(d_rdogrpCar[i]);
         }

         String[] d_rdogrpRickshaw = new String[] {"0","1"};
         objSave.setRickshaw("");
         for (int i = 0; i < rdogrpRickshaw.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpRickshaw.getChildAt(i);
             if (rb.isChecked()) objSave.setRickshaw(d_rdogrpRickshaw[i]);
         }

         String[] d_rdogrpCart = new String[] {"0","1"};
         objSave.setCart("");
         for (int i = 0; i < rdogrpCart.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCart.getChildAt(i);
             if (rb.isChecked()) objSave.setCart(d_rdogrpCart[i]);
         }

         String[] d_rdogrpCanoe = new String[] {"0","1"};
         objSave.setCanoe("");
         for (int i = 0; i < rdogrpCanoe.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCanoe.getChildAt(i);
             if (rb.isChecked()) objSave.setCanoe(d_rdogrpCanoe[i]);
         }

         String[] d_rdogrpBus = new String[] {"0","1"};
         objSave.setBus("");
         for (int i = 0; i < rdogrpBus.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpBus.getChildAt(i);
             if (rb.isChecked()) objSave.setBus(d_rdogrpBus[i]);
         }

         String[] d_rdogrpTractor = new String[] {"0","1"};
         objSave.setTractor("");
         for (int i = 0; i < rdogrpTractor.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpTractor.getChildAt(i);
             if (rb.isChecked()) objSave.setTractor(d_rdogrpTractor[i]);
         }

         String[] d_rdogrpPlow = new String[] {"0","1"};
         objSave.setPlow("");
         for (int i = 0; i < rdogrpPlow.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPlow.getChildAt(i);
             if (rb.isChecked()) objSave.setPlow(d_rdogrpPlow[i]);
         }

         String[] d_rdogrpDuck = new String[] {"0","1"};
         objSave.setDuck("");
         for (int i = 0; i < rdogrpDuck.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDuck.getChildAt(i);
             if (rb.isChecked()) objSave.setDuck(d_rdogrpDuck[i]);
         }

         String[] d_rdogrpCow = new String[] {"0","1"};
         objSave.setCow("");
         for (int i = 0; i < rdogrpCow.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCow.getChildAt(i);
             if (rb.isChecked()) objSave.setCow(d_rdogrpCow[i]);
         }

         String[] d_rdogrpSheep = new String[] {"0","1"};
         objSave.setSheep("");
         for (int i = 0; i < rdogrpSheep.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSheep.getChildAt(i);
             if (rb.isChecked()) objSave.setSheep(d_rdogrpSheep[i]);
         }

         String[] d_rdogrpGoat = new String[] {"0","1"};
         objSave.setGoat("");
         for (int i = 0; i < rdogrpGoat.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGoat.getChildAt(i);
             if (rb.isChecked()) objSave.setGoat(d_rdogrpGoat[i]);
         }

         String[] d_rdogrpChicken = new String[] {"0","1"};
         objSave.setChicken("");
         for (int i = 0; i < rdogrpChicken.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpChicken.getChildAt(i);
             if (rb.isChecked()) objSave.setChicken(d_rdogrpChicken[i]);
         }

         String[] d_rdogrpDonkey = new String[] {"0","1"};
         objSave.setDonkey("");
         for (int i = 0; i < rdogrpDonkey.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDonkey.getChildAt(i);
             if (rb.isChecked()) objSave.setDonkey(d_rdogrpDonkey[i]);
         }

         String[] d_rdogrpHorse = new String[] {"0","1"};
         objSave.setHorse("");
         for (int i = 0; i < rdogrpHorse.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpHorse.getChildAt(i);
             if (rb.isChecked()) objSave.setHorse(d_rdogrpHorse[i]);
         }

         String[] d_rdogrpPig = new String[] {"0","1"};
         objSave.setPig("");
         for (int i = 0; i < rdogrpPig.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPig.getChildAt(i);
             if (rb.isChecked()) objSave.setPig(d_rdogrpPig[i]);
         }

         String[] d_rdogrpOtherAsset = new String[] {"0","1"};
         objSave.setOtherAsset("");
         for (int i = 0; i < rdogrpOtherAsset.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpOtherAsset.getChildAt(i);
             if (rb.isChecked()) objSave.setOtherAsset(d_rdogrpOtherAsset[i]);
         }

         objSave.setOtherAsset1(txtOtherAsset1.getText().toString());
         objSave.setOtherAsset2(txtOtherAsset2.getText().toString());
         objSave.setOtherAsset3(txtOtherAsset3.getText().toString());
         objSave.setSESNote(txtSESNote.getText().toString());
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

             Connection.MessageBox(SES.this, "Saved Successfully");
         }
         else{
             Connection.MessageBox(SES.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(SES.this, e.getMessage());
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
         if(txtHHID.getText().toString().length()==0 & secHHID.isShown())
           {
             ValidationMsg += "\nRequired field: Household ID.";
             secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSESNo.getText().toString().length()==0 & secSESNo.isShown())
           {
             ValidationMsg += "\nRequired field: SES No.";
             secSESNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         DV = Global.DateValidate(dtpSESVDate.getText().toString());
         if(DV.length()!=0 & secSESVDate.isShown())
           {
             ValidationMsg += "\nRequired field/Not a valid date format: Date of Interview.";
             secSESVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSESVStatus1.isChecked() & !rdoSESVStatus2.isChecked() & !rdoSESVStatus3.isChecked() & !rdoSESVStatus4.isChecked() & secSESVStatus.isShown())
           {
             ValidationMsg += "\nRequired field: Visit Status.";
             secSESVStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSESVStatusOth.getText().toString().length()==0 & secSESVStatusOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Specify.";
             secSESVStatusOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             ValidationMsg += "\nRequired field: Round No.";
             secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnWSDrink.getSelectedItemPosition()==0  & secWSDrink.isShown())
           {
             ValidationMsg += "\nH1. Required field: What is the main source of drinking water for members of your household ? (Circle One Response, Or Write Response For 97).";
             secWSDrink.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtWSDrinkOth.getText().toString().length()==0 & secWSDrinkOth.isShown())
           {
             ValidationMsg += "\nRequired field: Orher Specify.";
             secWSDrinkOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnToilet.getSelectedItemPosition()==0  & secToilet.isShown())
           {
             ValidationMsg += "\nH2 Required field: What kind of main toilet facility do members of your household usually use ? (If ‘Flush’, Or ‘Pour Flush’, Probe: Where Does It Flush To ?)  (Circle One Response, Or Write Response For 97).";
             secToilet.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtToiletOth.getText().toString().length()==0 & secToiletOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Specify.";
             secToiletOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoToiletShrd1.isChecked() & !rdoToiletShrd2.isChecked() & secToiletShrd.isShown())
           {
             ValidationMsg += "\nH2.1 Required field: Do you share this toilet facility with other households?.";
             secToiletShrd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtToiletUseNo.getText().toString().length()==0 & secToiletUseNo.isShown())
           {
             ValidationMsg += "\nH2.2 Required field: Including your own household, how many households use this toilet facility? No. Of Households (If Less Than 10).";
             secToiletUseNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secToiletUseNo.isShown() & (Integer.valueOf(txtToiletUseNo.getText().toString().length()==0 ? "1" : txtToiletUseNo.getText().toString()) < 1 || Integer.valueOf(txtToiletUseNo.getText().toString().length()==0 ? "10" : txtToiletUseNo.getText().toString()) > 10))
           {
             ValidationMsg += "\nH2.2 Value should be between 1 and 10(Including your own household, how many households use this toilet facility? No. Of Households (If Less Than 10)).";
             secToiletUseNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoToiletUseNoDk1.isChecked() & !rdoToiletUseNoDk2.isChecked() & secToiletUseNoDk.isShown())
           {
             ValidationMsg += "\nRequired field: .";
             secToiletUseNoDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoToiletLoc1.isChecked() & !rdoToiletLoc2.isChecked() & !rdoToiletLoc3.isChecked() & secToiletLoc.isShown())
           {
             ValidationMsg += "\nH2.3 Required field: Where is this toilet facility located ?.";
             secToiletLoc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnCookDvc.getSelectedItemPosition()==0  & secCookDvc.isShown())
           {
             ValidationMsg += "\nH3. Required field: What device does this household use most of the time for cooking food, making tea/coffee, or boiling drinking water ?  (Circle One Response, Or Write Response For 97).";
             secCookDvc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCookDvcOth.getText().toString().length()==0 & secCookDvcOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Specify.";
             secCookDvcOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnCookFuel.getSelectedItemPosition()==0  & secCookFuel.isShown())
           {
             ValidationMsg += "\nH4. Required field: When cooking, what type of fuel or energy source does this household use most of the time for cooking food, making tea/coffee, or boiling drinking water? (Circle One Response, Or Write Response For 97).";
             secCookFuel.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCookFuelOth.getText().toString().length()==0 & secCookFuelOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other fuel Specify.";
             secCookFuelOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnCookPlc.getSelectedItemPosition()==0  & secCookPlc.isShown())
           {
             ValidationMsg += "\nH5. Required field: Where is the cooking done most of the time? (Circle One Response, Or Write Response For 97).";
             secCookPlc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCookPlcOth.getText().toString().length()==0 & secCookPlcOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Specify.";
             secCookPlcOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnFloor.getSelectedItemPosition()==0  & secFloor.isShown())
           {
             ValidationMsg += "\nH6. Required field: What is the main material of the floor of your homestead/dwelling/residence? (Circle One Response, Or Write Response For 97) (“Main Material” Is The Material That Covers The Largest Amount Of Floor Space).";
             secFloor.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtFloorOth.getText().toString().length()==0 & secFloorOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Floor Specify.";
             secFloorOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnRoof.getSelectedItemPosition()==0  & secRoof.isShown())
           {
             ValidationMsg += "\nH7. Required field: What is the main material of the roof of your homestead/dwelling/residence ? (Circle One Response, Or Write Response For 97)  (“Main Material” Is The Material That Covers The Largest Amount Of Roof Space).";
             secRoof.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRoofOth.getText().toString().length()==0 & secRoofOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Roof Specify.";
             secRoofOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnWall.getSelectedItemPosition()==0  & secWall.isShown())
           {
             ValidationMsg += "\nH8. Required field: What is the main material of the exterior walls of the house that is your main homestead/dwelling/residence? (Circle One Response, Or Write Response For 97) (“Main Material” Is The Material That Covers The Largest Amount Of Exterior Wall Space) .";
             secWall.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtWallOth.getText().toString().length()==0 & secWallOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Specify.";
             secWallOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRoomSleep.getText().toString().length()==0 & secRoomSleep.isShown())
           {
             ValidationMsg += "\nH9. Required field: How many rooms in your homestead/dwelling/residence are used for sleeping ?  (Write The Number On The Blank) (A Room Divided By A Curtain Counts As One Room).";
             secRoomSleep.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secRoomSleep.isShown() & (Integer.valueOf(txtRoomSleep.getText().toString().length()==0 ? "01" : txtRoomSleep.getText().toString()) < 01 || Integer.valueOf(txtRoomSleep.getText().toString().length()==0 ? "20" : txtRoomSleep.getText().toString()) > 20))
           {
             ValidationMsg += "\nH9. Value should be between 01 and 20(How many rooms in your homestead/dwelling/residence are used for sleeping ?  (Write The Number On The Blank) (A Room Divided By A Curtain Counts As One Room)).";
             secRoomSleep.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoRoomSleepDk1.isChecked() & !rdoRoomSleepDk2.isChecked() & secRoomSleepDk.isShown())
           {
             ValidationMsg += "\nRequired field: How many rooms in your homestead/dwelling/residence are used for sleeping?-Don’t know/Refused to respond.";
             secRoomSleepDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoHomesteadAny1.isChecked() & !rdoHomesteadAny2.isChecked() & !rdoHomesteadAny3.isChecked() & !rdoHomesteadAny4.isChecked() & secHomesteadAny.isShown())
           {
             ValidationMsg += "\nH10. Required field: Does your household own any homestead?.";
             secHomesteadAny.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoOthLand1.isChecked() & !rdoOthLand2.isChecked() & !rdoOthLand3.isChecked() & !rdoOthLand4.isChecked() & secOthLand.isShown())
           {
             ValidationMsg += "\nH11. Required field: Does your household own any agricultural land?.";
             secOthLand.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoElectricity1.isChecked() & !rdoElectricity2.isChecked() & secElectricity.isShown())
           {
             ValidationMsg += "\n1. Required field: Electricity.";
             secElectricity.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoHeater1.isChecked() & !rdoHeater2.isChecked() & secHeater.isShown())
           {
             ValidationMsg += "\n2. Required field: Heater.";
             secHeater.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoAC1.isChecked() & !rdoAC2.isChecked() & secAC.isShown())
           {
             ValidationMsg += "\n3. Required field: Air conditioner.";
             secAC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoElecFan1.isChecked() & !rdoElecFan2.isChecked() & secElecFan.isShown())
           {
             ValidationMsg += "\n4. Required field: Electric fan.";
             secElecFan.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoLantern1.isChecked() & !rdoLantern2.isChecked() & secLantern.isShown())
           {
             ValidationMsg += "\n5. Required field: Kerosene lantern.";
             secLantern.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoLamp1.isChecked() & !rdoLamp2.isChecked() & secLamp.isShown())
           {
             ValidationMsg += "\n6. Required field: Pressure lamp.";
             secLamp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoMatt1.isChecked() & !rdoMatt2.isChecked() & secMatt.isShown())
           {
             ValidationMsg += "\n7. Required field: Mattress.";
             secMatt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoBed1.isChecked() & !rdoBed2.isChecked() & secBed.isShown())
           {
             ValidationMsg += "\n8. Required field: Bed.";
             secBed.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoChair1.isChecked() & !rdoChair2.isChecked() & secChair.isShown())
           {
             ValidationMsg += "\n9. Required field: Chair.";
             secChair.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSofa1.isChecked() & !rdoSofa2.isChecked() & secSofa.isShown())
           {
             ValidationMsg += "\n10. Required field: Sofa.";
             secSofa.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoTables1.isChecked() & !rdoTables2.isChecked() & secTables.isShown())
           {
             ValidationMsg += "\n11. Required field: Tables.";
             secTables.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoWatch1.isChecked() & !rdoWatch2.isChecked() & secWatch.isShown())
           {
             ValidationMsg += "\n12. Required field: Watch or clock.";
             secWatch.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoWMachine1.isChecked() & !rdoWMachine2.isChecked() & secWMachine.isShown())
           {
             ValidationMsg += "\n13. Required field: Washing Machine.";
             secWMachine.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoIron1.isChecked() & !rdoIron2.isChecked() & secIron.isShown())
           {
             ValidationMsg += "\n14. Required field: Electric iron.";
             secIron.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoBooth1.isChecked() & !rdoBooth2.isChecked() & secBooth.isShown())
           {
             ValidationMsg += "\n15. Required field: Business booth.";
             secBooth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSMachine1.isChecked() & !rdoSMachine2.isChecked() & secSMachine.isShown())
           {
             ValidationMsg += "\n16. Required field: Sewing machine.";
             secSMachine.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGenerator1.isChecked() & !rdoGenerator2.isChecked() & secGenerator.isShown())
           {
             ValidationMsg += "\n17. Required field: Power generator.";
             secGenerator.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
        if(!rdoMosquito1.isChecked() & !rdoMosquito2.isChecked() & secMosquito.isShown())
        {
           ValidationMsg += "\n17.1. Required field: Mosquito bed net.";
           secMosquito.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
        }
         if(!rdoInternet1.isChecked() & !rdoInternet2.isChecked() & secInternet.isShown())
           {
             ValidationMsg += "\n18. Required field: Internet/Wi-Fi at home.";
             secInternet.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSatellite1.isChecked() & !rdoSatellite2.isChecked() & secSatellite.isShown())
           {
             ValidationMsg += "\n19. Required field: Satellite decoder.";
             secSatellite.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoLandline1.isChecked() & !rdoLandline2.isChecked() & secLandline.isShown())
           {
             ValidationMsg += "\n20. Required field: Landline telephone.";
             secLandline.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCellphone1.isChecked() & !rdoCellphone2.isChecked() & secCellphone.isShown())
           {
             ValidationMsg += "\n21. Required field: Cellphone.";
             secCellphone.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoTV1.isChecked() & !rdoTV2.isChecked() & secTV.isShown())
           {
             ValidationMsg += "\n22. Required field: TV.";
             secTV.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoRadio1.isChecked() & !rdoRadio2.isChecked() & secRadio.isShown())
           {
             ValidationMsg += "\n23. Required field: Radio.";
             secRadio.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoDVD1.isChecked() & !rdoDVD2.isChecked() & secDVD.isShown())
           {
             ValidationMsg += "\n24. Required field: DVD/CD player.";
             secDVD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoVideo1.isChecked() & !rdoVideo2.isChecked() & secVideo.isShown())
           {
             ValidationMsg += "\n25. Required field: Video player.";
             secVideo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoComputer1.isChecked() & !rdoComputer2.isChecked() & secComputer.isShown())
           {
             ValidationMsg += "\n26. Required field: Computer.";
             secComputer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCable1.isChecked() & !rdoCable2.isChecked() & secCable.isShown())
           {
             ValidationMsg += "\n27. Required field: Cable connection.";
             secCable.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoMicrowave1.isChecked() & !rdoMicrowave2.isChecked() & secMicrowave.isShown())
           {
             ValidationMsg += "\n28. Required field: Microwave oven.";
             secMicrowave.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGeyser1.isChecked() & !rdoGeyser2.isChecked() & secGeyser.isShown())
           {
             ValidationMsg += "\n29. Required field: Geyser for hot water.";
             secGeyser.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGrill1.isChecked() & !rdoGrill2.isChecked() & secGrill.isShown())
           {
             ValidationMsg += "\n30. Required field: Electric grill/Mitad.";
             secGrill.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoFridge1.isChecked() & !rdoFridge2.isChecked() & secFridge.isShown())
           {
             ValidationMsg += "\n31. Required field: Refrigerator.";
             secFridge.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoDeepFreezer1.isChecked() & !rdoDeepFreezer2.isChecked() & secDeepFreezer.isShown())
           {
             ValidationMsg += "\n32. Required field: Freezer/deep freezer.";
             secDeepFreezer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoStove1.isChecked() & !rdoStove2.isChecked() & secStove.isShown())
           {
             ValidationMsg += "\n33. Required field: Stove.";
             secStove.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
        if(!rdoBlender1.isChecked() & !rdoBlender2.isChecked() & secBlender.isShown())
        {
           ValidationMsg += "\n33.1. Required field: Blender.";
           secBlender.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
        }
         if(!rdoBike1.isChecked() & !rdoBike2.isChecked() & secBike.isShown())
           {
             ValidationMsg += "\n34. Required field: Bike.";
             secBike.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoMotorcycle1.isChecked() & !rdoMotorcycle2.isChecked() & secMotorcycle.isShown())
           {
             ValidationMsg += "\n35. Required field: Motorcycle/moped/scooter.";
             secMotorcycle.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCar1.isChecked() & !rdoCar2.isChecked() & secCar.isShown())
           {
             ValidationMsg += "\n36. Required field: Car.";
             secCar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoRickshaw1.isChecked() & !rdoRickshaw2.isChecked() & secRickshaw.isShown())
           {
             ValidationMsg += "\n37. Required field: Rickshaw/Van.";
             secRickshaw.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCart1.isChecked() & !rdoCart2.isChecked() & secCart.isShown())
           {
             ValidationMsg += "\n38. Required field: Animal drawn cart.";
             secCart.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCanoe1.isChecked() & !rdoCanoe2.isChecked() & secCanoe.isShown())
           {
             ValidationMsg += "\n39. Required field: Canoe/fishing boat.";
             secCanoe.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoBus1.isChecked() & !rdoBus2.isChecked() & secBus.isShown())
           {
             ValidationMsg += "\n40. Required field: Bus/truck.";
             secBus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoTractor1.isChecked() & !rdoTractor2.isChecked() & secTractor.isShown())
           {
             ValidationMsg += "\n41. Required field: Tractor.";
             secTractor.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPlow1.isChecked() & !rdoPlow2.isChecked() & secPlow.isShown())
           {
             ValidationMsg += "\n42. Required field: Plow/plough.";
             secPlow.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoDuck1.isChecked() & !rdoDuck2.isChecked() & secDuck.isShown())
           {
             ValidationMsg += "\n43. Required field: Duck.";
             secDuck.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCow1.isChecked() & !rdoCow2.isChecked() & secCow.isShown())
           {
             ValidationMsg += "\n44. Required field: Cow.";
             secCow.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSheep1.isChecked() & !rdoSheep2.isChecked() & secSheep.isShown())
           {
             ValidationMsg += "\n45. Required field: Sheep.";
             secSheep.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGoat1.isChecked() & !rdoGoat2.isChecked() & secGoat.isShown())
           {
             ValidationMsg += "\n46. Required field: Goat.";
             secGoat.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoChicken1.isChecked() & !rdoChicken2.isChecked() & secChicken.isShown())
           {
             ValidationMsg += "\n47. Required field: Chicken.";
             secChicken.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoDonkey1.isChecked() & !rdoDonkey2.isChecked() & secDonkey.isShown())
           {
             ValidationMsg += "\n48. Required field: Donkey/mule/ass.";
             secDonkey.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoHorse1.isChecked() & !rdoHorse2.isChecked() & secHorse.isShown())
           {
             ValidationMsg += "\n49. Required field: Horse.";
             secHorse.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPig1.isChecked() & !rdoPig2.isChecked() & secPig.isShown())
           {
             ValidationMsg += "\n50. Required field: Pig.";
             secPig.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoOtherAsset1.isChecked() & !rdoOtherAsset2.isChecked() & secOtherAsset.isShown())
           {
             ValidationMsg += "\n51. Required field: Other Asset.";
             secOtherAsset.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtOtherAsset1.getText().toString().length()==0 & secOtherAsset1.isShown())
           {
             ValidationMsg += "\nRequired field: Other Asset-1.";
             secOtherAsset1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtOtherAsset2.getText().toString().length()==0 & secOtherAsset2.isShown())
           {
             ValidationMsg += "\nRequired field: Other Asset-2.";
             secOtherAsset2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtOtherAsset3.getText().toString().length()==0 & secOtherAsset3.isShown())
           {
             ValidationMsg += "\nRequired field: Other Asset-3.";
             secOtherAsset3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSESNote.getText().toString().length()==0 & secSESNote.isShown())
           {
             ValidationMsg += "\nRequired field: Note.";
             secSESNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secHHID.setBackgroundColor(Color.WHITE);
             secSESNo.setBackgroundColor(Color.WHITE);
             secSESVDate.setBackgroundColor(Color.WHITE);
             secSESVStatus.setBackgroundColor(Color.WHITE);
             secSESVStatusOth.setBackgroundColor(Color.WHITE);
             secRnd.setBackgroundColor(Color.WHITE);
             secWSDrink.setBackgroundColor(Color.WHITE);
             secWSDrinkOth.setBackgroundColor(Color.WHITE);
             secToilet.setBackgroundColor(Color.WHITE);
             secToiletOth.setBackgroundColor(Color.WHITE);
             secToiletShrd.setBackgroundColor(Color.WHITE);
             secToiletUseNo.setBackgroundColor(Color.WHITE);
             secToiletUseNo.setBackgroundColor(Color.WHITE);
             secToiletUseNoDk.setBackgroundColor(Color.WHITE);
             secToiletLoc.setBackgroundColor(Color.WHITE);
             secCookDvc.setBackgroundColor(Color.WHITE);
             secCookDvcOth.setBackgroundColor(Color.WHITE);
             secCookFuel.setBackgroundColor(Color.WHITE);
             secCookFuelOth.setBackgroundColor(Color.WHITE);
             secCookPlc.setBackgroundColor(Color.WHITE);
             secCookPlcOth.setBackgroundColor(Color.WHITE);
             secFloor.setBackgroundColor(Color.WHITE);
             secFloorOth.setBackgroundColor(Color.WHITE);
             secRoof.setBackgroundColor(Color.WHITE);
             secRoofOth.setBackgroundColor(Color.WHITE);
             secWall.setBackgroundColor(Color.WHITE);
             secWallOth.setBackgroundColor(Color.WHITE);
             secRoomSleep.setBackgroundColor(Color.WHITE);
             secRoomSleep.setBackgroundColor(Color.WHITE);
             secRoomSleepDk.setBackgroundColor(Color.WHITE);
             secHomesteadAny.setBackgroundColor(Color.WHITE);
             secOthLand.setBackgroundColor(Color.WHITE);
             secElectricity.setBackgroundColor(Color.WHITE);
             secHeater.setBackgroundColor(Color.WHITE);
             secAC.setBackgroundColor(Color.WHITE);
             secElecFan.setBackgroundColor(Color.WHITE);
             secLantern.setBackgroundColor(Color.WHITE);
             secLamp.setBackgroundColor(Color.WHITE);
             secMatt.setBackgroundColor(Color.WHITE);
             secBed.setBackgroundColor(Color.WHITE);
             secChair.setBackgroundColor(Color.WHITE);
             secSofa.setBackgroundColor(Color.WHITE);
             secTables.setBackgroundColor(Color.WHITE);
             secWatch.setBackgroundColor(Color.WHITE);
             secWMachine.setBackgroundColor(Color.WHITE);
             secIron.setBackgroundColor(Color.WHITE);
             secBooth.setBackgroundColor(Color.WHITE);
             secSMachine.setBackgroundColor(Color.WHITE);
             secGenerator.setBackgroundColor(Color.WHITE);
             secMosquito.setBackgroundColor(Color.WHITE);
             secInternet.setBackgroundColor(Color.WHITE);
             secSatellite.setBackgroundColor(Color.WHITE);
             secLandline.setBackgroundColor(Color.WHITE);
             secCellphone.setBackgroundColor(Color.WHITE);
             secTV.setBackgroundColor(Color.WHITE);
             secRadio.setBackgroundColor(Color.WHITE);
             secDVD.setBackgroundColor(Color.WHITE);
             secVideo.setBackgroundColor(Color.WHITE);
             secComputer.setBackgroundColor(Color.WHITE);
             secCable.setBackgroundColor(Color.WHITE);
             secMicrowave.setBackgroundColor(Color.WHITE);
             secGeyser.setBackgroundColor(Color.WHITE);
             secGrill.setBackgroundColor(Color.WHITE);
             secFridge.setBackgroundColor(Color.WHITE);
             secDeepFreezer.setBackgroundColor(Color.WHITE);
             secStove.setBackgroundColor(Color.WHITE);
        secBlender.setBackgroundColor(Color.WHITE);
             secBike.setBackgroundColor(Color.WHITE);
             secMotorcycle.setBackgroundColor(Color.WHITE);
             secCar.setBackgroundColor(Color.WHITE);
             secRickshaw.setBackgroundColor(Color.WHITE);
             secCart.setBackgroundColor(Color.WHITE);
             secCanoe.setBackgroundColor(Color.WHITE);
             secBus.setBackgroundColor(Color.WHITE);
             secTractor.setBackgroundColor(Color.WHITE);
             secPlow.setBackgroundColor(Color.WHITE);
             secDuck.setBackgroundColor(Color.WHITE);
             secCow.setBackgroundColor(Color.WHITE);
             secSheep.setBackgroundColor(Color.WHITE);
             secGoat.setBackgroundColor(Color.WHITE);
             secChicken.setBackgroundColor(Color.WHITE);
             secDonkey.setBackgroundColor(Color.WHITE);
             secHorse.setBackgroundColor(Color.WHITE);
             secPig.setBackgroundColor(Color.WHITE);
             secOtherAsset.setBackgroundColor(Color.WHITE);
             secOtherAsset1.setBackgroundColor(Color.WHITE);
             secOtherAsset2.setBackgroundColor(Color.WHITE);
             secOtherAsset3.setBackgroundColor(Color.WHITE);
             secSESNote.setBackgroundColor(Color.WHITE);
     }
     catch(Exception  e)
     {
     }
 }

 private void DataSearch(String HHID, String SESNo)
     {
       try
        {     
           RadioButton rb;
           SES_DataModel d = new SES_DataModel();
           String SQL = "Select * from "+ TableName +"  Where HHID='"+ HHID +"' and SESNo='"+ SESNo +"'";
           List<SES_DataModel> data = d.SelectAll(this, SQL);
           for(SES_DataModel item : data){
             txtHHID.setText(item.getHHID());
             txtSESNo.setText(item.getSESNo());
             dtpSESVDate.setText(item.getSESVDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getSESVDate()));
             String[] d_rdogrpSESVStatus = new String[] {"1","2","3","9"};
             for (int i = 0; i < d_rdogrpSESVStatus.length; i++)
             {
                 if (String.valueOf(item.getSESVStatus()).equals(String.valueOf(d_rdogrpSESVStatus[i])))
                 {
                     rb = (RadioButton)rdogrpSESVStatus.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtSESVStatusOth.setText(item.getSESVStatusOth());
             txtRnd.setText(item.getRnd());
             spnWSDrink.setSelection(Global.SpinnerItemPositionAnyLength(spnWSDrink, String.valueOf(item.getWSDrink())));
             txtWSDrinkOth.setText(item.getWSDrinkOth());
             spnToilet.setSelection(Global.SpinnerItemPositionAnyLength(spnToilet, String.valueOf(item.getToilet())));
             txtToiletOth.setText(item.getToiletOth());
             String[] d_rdogrpToiletShrd = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpToiletShrd.length; i++)
             {
                 if (String.valueOf(item.getToiletShrd()).equals(String.valueOf(d_rdogrpToiletShrd[i])))
                 {
                     rb = (RadioButton)rdogrpToiletShrd.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtToiletUseNo.setText(String.valueOf(item.getToiletUseNo()));
             String[] d_rdogrpToiletUseNoDk = new String[] {"95","98"};
             for (int i = 0; i < d_rdogrpToiletUseNoDk.length; i++)
             {
                 if (String.valueOf(item.getToiletUseNoDk()).equals(String.valueOf(d_rdogrpToiletUseNoDk[i])))
                 {
                     rb = (RadioButton)rdogrpToiletUseNoDk.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpToiletLoc = new String[] {"1","2","3"};
             for (int i = 0; i < d_rdogrpToiletLoc.length; i++)
             {
                 if (String.valueOf(item.getToiletLoc()).equals(String.valueOf(d_rdogrpToiletLoc[i])))
                 {
                     rb = (RadioButton)rdogrpToiletLoc.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             spnCookDvc.setSelection(Global.SpinnerItemPositionAnyLength(spnCookDvc, String.valueOf(item.getCookDvc())));
             txtCookDvcOth.setText(item.getCookDvcOth());
             spnCookFuel.setSelection(Global.SpinnerItemPositionAnyLength(spnCookFuel, String.valueOf(item.getCookFuel())));
             txtCookFuelOth.setText(item.getCookFuelOth());
             spnCookPlc.setSelection(Global.SpinnerItemPositionAnyLength(spnCookPlc, String.valueOf(item.getCookPlc())));
             txtCookPlcOth.setText(item.getCookPlcOth());
             spnFloor.setSelection(Global.SpinnerItemPositionAnyLength(spnFloor, String.valueOf(item.getFloor())));
             txtFloorOth.setText(item.getFloorOth());
             spnRoof.setSelection(Global.SpinnerItemPositionAnyLength(spnRoof, String.valueOf(item.getRoof())));
             txtRoofOth.setText(item.getRoofOth());
             spnWall.setSelection(Global.SpinnerItemPositionAnyLength(spnWall, String.valueOf(item.getWall())));
             txtWallOth.setText(item.getWallOth());
             txtRoomSleep.setText(String.valueOf(item.getRoomSleep()));
             String[] d_rdogrpRoomSleepDk = new String[] {"98","99"};
             for (int i = 0; i < d_rdogrpRoomSleepDk.length; i++)
             {
                 if (String.valueOf(item.getRoomSleepDk()).equals(String.valueOf(d_rdogrpRoomSleepDk[i])))
                 {
                     rb = (RadioButton)rdogrpRoomSleepDk.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpHomesteadAny = new String[] {"0","1","8","9"};
             for (int i = 0; i < d_rdogrpHomesteadAny.length; i++)
             {
                 if (String.valueOf(item.getHomesteadAny()).equals(String.valueOf(d_rdogrpHomesteadAny[i])))
                 {
                     rb = (RadioButton)rdogrpHomesteadAny.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpOthLand = new String[] {"0","1","8","9"};
             for (int i = 0; i < d_rdogrpOthLand.length; i++)
             {
                 if (String.valueOf(item.getOthLand()).equals(String.valueOf(d_rdogrpOthLand[i])))
                 {
                     rb = (RadioButton)rdogrpOthLand.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpElectricity = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpElectricity.length; i++)
             {
                 if (String.valueOf(item.getElectricity()).equals(String.valueOf(d_rdogrpElectricity[i])))
                 {
                     rb = (RadioButton)rdogrpElectricity.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpHeater = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpHeater.length; i++)
             {
                 if (String.valueOf(item.getHeater()).equals(String.valueOf(d_rdogrpHeater[i])))
                 {
                     rb = (RadioButton)rdogrpHeater.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpAC = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpAC.length; i++)
             {
                 if (String.valueOf(item.getAC()).equals(String.valueOf(d_rdogrpAC[i])))
                 {
                     rb = (RadioButton)rdogrpAC.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpElecFan = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpElecFan.length; i++)
             {
                 if (String.valueOf(item.getElecFan()).equals(String.valueOf(d_rdogrpElecFan[i])))
                 {
                     rb = (RadioButton)rdogrpElecFan.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpLantern = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpLantern.length; i++)
             {
                 if (String.valueOf(item.getLantern()).equals(String.valueOf(d_rdogrpLantern[i])))
                 {
                     rb = (RadioButton)rdogrpLantern.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpLamp = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpLamp.length; i++)
             {
                 if (String.valueOf(item.getLamp()).equals(String.valueOf(d_rdogrpLamp[i])))
                 {
                     rb = (RadioButton)rdogrpLamp.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpMatt = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpMatt.length; i++)
             {
                 if (String.valueOf(item.getMatt()).equals(String.valueOf(d_rdogrpMatt[i])))
                 {
                     rb = (RadioButton)rdogrpMatt.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpBed = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpBed.length; i++)
             {
                 if (String.valueOf(item.getBed()).equals(String.valueOf(d_rdogrpBed[i])))
                 {
                     rb = (RadioButton)rdogrpBed.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpChair = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpChair.length; i++)
             {
                 if (String.valueOf(item.getChair()).equals(String.valueOf(d_rdogrpChair[i])))
                 {
                     rb = (RadioButton)rdogrpChair.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpSofa = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpSofa.length; i++)
             {
                 if (String.valueOf(item.getSofa()).equals(String.valueOf(d_rdogrpSofa[i])))
                 {
                     rb = (RadioButton)rdogrpSofa.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpTables = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpTables.length; i++)
             {
                 if (String.valueOf(item.getTables()).equals(String.valueOf(d_rdogrpTables[i])))
                 {
                     rb = (RadioButton)rdogrpTables.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpWatch = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpWatch.length; i++)
             {
                 if (String.valueOf(item.getWatch()).equals(String.valueOf(d_rdogrpWatch[i])))
                 {
                     rb = (RadioButton)rdogrpWatch.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpWMachine = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpWMachine.length; i++)
             {
                 if (String.valueOf(item.getWMachine()).equals(String.valueOf(d_rdogrpWMachine[i])))
                 {
                     rb = (RadioButton)rdogrpWMachine.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpIron = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpIron.length; i++)
             {
                 if (String.valueOf(item.getIron()).equals(String.valueOf(d_rdogrpIron[i])))
                 {
                     rb = (RadioButton)rdogrpIron.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpBooth = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpBooth.length; i++)
             {
                 if (String.valueOf(item.getBooth()).equals(String.valueOf(d_rdogrpBooth[i])))
                 {
                     rb = (RadioButton)rdogrpBooth.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpSMachine = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpSMachine.length; i++)
             {
                 if (String.valueOf(item.getSMachine()).equals(String.valueOf(d_rdogrpSMachine[i])))
                 {
                     rb = (RadioButton)rdogrpSMachine.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpGenerator = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpGenerator.length; i++)
             {
                 if (String.valueOf(item.getGenerator()).equals(String.valueOf(d_rdogrpGenerator[i])))
                 {
                     rb = (RadioButton)rdogrpGenerator.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
              String[] d_rdogrpMosquito = new String[] {"0","1"};
              for (int i = 0; i < d_rdogrpMosquito.length; i++)
              {
                 if (String.valueOf(item.getMosquito()).equals(String.valueOf(d_rdogrpMosquito[i])))
                 {
                    rb = (RadioButton)rdogrpMosquito.getChildAt(i);
                    rb.setChecked(true);
                 }
              }
             String[] d_rdogrpInternet = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpInternet.length; i++)
             {
                 if (String.valueOf(item.getInternet()).equals(String.valueOf(d_rdogrpInternet[i])))
                 {
                     rb = (RadioButton)rdogrpInternet.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpSatellite = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpSatellite.length; i++)
             {
                 if (String.valueOf(item.getSatellite()).equals(String.valueOf(d_rdogrpSatellite[i])))
                 {
                     rb = (RadioButton)rdogrpSatellite.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpLandline = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpLandline.length; i++)
             {
                 if (String.valueOf(item.getLandline()).equals(String.valueOf(d_rdogrpLandline[i])))
                 {
                     rb = (RadioButton)rdogrpLandline.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpCellphone = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCellphone.length; i++)
             {
                 if (String.valueOf(item.getCellphone()).equals(String.valueOf(d_rdogrpCellphone[i])))
                 {
                     rb = (RadioButton)rdogrpCellphone.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpTV = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpTV.length; i++)
             {
                 if (String.valueOf(item.getTV()).equals(String.valueOf(d_rdogrpTV[i])))
                 {
                     rb = (RadioButton)rdogrpTV.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpRadio = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpRadio.length; i++)
             {
                 if (String.valueOf(item.getRadio()).equals(String.valueOf(d_rdogrpRadio[i])))
                 {
                     rb = (RadioButton)rdogrpRadio.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpDVD = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpDVD.length; i++)
             {
                 if (String.valueOf(item.getDVD()).equals(String.valueOf(d_rdogrpDVD[i])))
                 {
                     rb = (RadioButton)rdogrpDVD.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpVideo = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpVideo.length; i++)
             {
                 if (String.valueOf(item.getVideo()).equals(String.valueOf(d_rdogrpVideo[i])))
                 {
                     rb = (RadioButton)rdogrpVideo.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpComputer = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpComputer.length; i++)
             {
                 if (String.valueOf(item.getComputer()).equals(String.valueOf(d_rdogrpComputer[i])))
                 {
                     rb = (RadioButton)rdogrpComputer.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpCable = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCable.length; i++)
             {
                 if (String.valueOf(item.getCable()).equals(String.valueOf(d_rdogrpCable[i])))
                 {
                     rb = (RadioButton)rdogrpCable.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpMicrowave = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpMicrowave.length; i++)
             {
                 if (String.valueOf(item.getMicrowave()).equals(String.valueOf(d_rdogrpMicrowave[i])))
                 {
                     rb = (RadioButton)rdogrpMicrowave.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpGeyser = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpGeyser.length; i++)
             {
                 if (String.valueOf(item.getGeyser()).equals(String.valueOf(d_rdogrpGeyser[i])))
                 {
                     rb = (RadioButton)rdogrpGeyser.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpGrill = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpGrill.length; i++)
             {
                 if (String.valueOf(item.getGrill()).equals(String.valueOf(d_rdogrpGrill[i])))
                 {
                     rb = (RadioButton)rdogrpGrill.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpFridge = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpFridge.length; i++)
             {
                 if (String.valueOf(item.getFridge()).equals(String.valueOf(d_rdogrpFridge[i])))
                 {
                     rb = (RadioButton)rdogrpFridge.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpDeepFreezer = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpDeepFreezer.length; i++)
             {
                 if (String.valueOf(item.getDeepFreezer()).equals(String.valueOf(d_rdogrpDeepFreezer[i])))
                 {
                     rb = (RadioButton)rdogrpDeepFreezer.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpStove = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpStove.length; i++)
             {
                 if (String.valueOf(item.getStove()).equals(String.valueOf(d_rdogrpStove[i])))
                 {
                     rb = (RadioButton)rdogrpStove.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
              String[] d_rdogrpBlender = new String[] {"0","1"};
              for (int i = 0; i < d_rdogrpBlender.length; i++)
              {
                 if (String.valueOf(item.getBlender()).equals(String.valueOf(d_rdogrpBlender[i])))
                 {
                    rb = (RadioButton)rdogrpBlender.getChildAt(i);
                    rb.setChecked(true);
                 }
              }
             String[] d_rdogrpBike = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpBike.length; i++)
             {
                 if (String.valueOf(item.getBike()).equals(String.valueOf(d_rdogrpBike[i])))
                 {
                     rb = (RadioButton)rdogrpBike.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpMotorcycle = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpMotorcycle.length; i++)
             {
                 if (String.valueOf(item.getMotorcycle()).equals(String.valueOf(d_rdogrpMotorcycle[i])))
                 {
                     rb = (RadioButton)rdogrpMotorcycle.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpCar = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCar.length; i++)
             {
                 if (String.valueOf(item.getCar()).equals(String.valueOf(d_rdogrpCar[i])))
                 {
                     rb = (RadioButton)rdogrpCar.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpRickshaw = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpRickshaw.length; i++)
             {
                 if (String.valueOf(item.getRickshaw()).equals(String.valueOf(d_rdogrpRickshaw[i])))
                 {
                     rb = (RadioButton)rdogrpRickshaw.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpCart = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCart.length; i++)
             {
                 if (String.valueOf(item.getCart()).equals(String.valueOf(d_rdogrpCart[i])))
                 {
                     rb = (RadioButton)rdogrpCart.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpCanoe = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCanoe.length; i++)
             {
                 if (String.valueOf(item.getCanoe()).equals(String.valueOf(d_rdogrpCanoe[i])))
                 {
                     rb = (RadioButton)rdogrpCanoe.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpBus = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpBus.length; i++)
             {
                 if (String.valueOf(item.getBus()).equals(String.valueOf(d_rdogrpBus[i])))
                 {
                     rb = (RadioButton)rdogrpBus.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpTractor = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpTractor.length; i++)
             {
                 if (String.valueOf(item.getTractor()).equals(String.valueOf(d_rdogrpTractor[i])))
                 {
                     rb = (RadioButton)rdogrpTractor.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpPlow = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPlow.length; i++)
             {
                 if (String.valueOf(item.getPlow()).equals(String.valueOf(d_rdogrpPlow[i])))
                 {
                     rb = (RadioButton)rdogrpPlow.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpDuck = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpDuck.length; i++)
             {
                 if (String.valueOf(item.getDuck()).equals(String.valueOf(d_rdogrpDuck[i])))
                 {
                     rb = (RadioButton)rdogrpDuck.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpCow = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCow.length; i++)
             {
                 if (String.valueOf(item.getCow()).equals(String.valueOf(d_rdogrpCow[i])))
                 {
                     rb = (RadioButton)rdogrpCow.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpSheep = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpSheep.length; i++)
             {
                 if (String.valueOf(item.getSheep()).equals(String.valueOf(d_rdogrpSheep[i])))
                 {
                     rb = (RadioButton)rdogrpSheep.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpGoat = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpGoat.length; i++)
             {
                 if (String.valueOf(item.getGoat()).equals(String.valueOf(d_rdogrpGoat[i])))
                 {
                     rb = (RadioButton)rdogrpGoat.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpChicken = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpChicken.length; i++)
             {
                 if (String.valueOf(item.getChicken()).equals(String.valueOf(d_rdogrpChicken[i])))
                 {
                     rb = (RadioButton)rdogrpChicken.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpDonkey = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpDonkey.length; i++)
             {
                 if (String.valueOf(item.getDonkey()).equals(String.valueOf(d_rdogrpDonkey[i])))
                 {
                     rb = (RadioButton)rdogrpDonkey.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpHorse = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpHorse.length; i++)
             {
                 if (String.valueOf(item.getHorse()).equals(String.valueOf(d_rdogrpHorse[i])))
                 {
                     rb = (RadioButton)rdogrpHorse.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpPig = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPig.length; i++)
             {
                 if (String.valueOf(item.getPig()).equals(String.valueOf(d_rdogrpPig[i])))
                 {
                     rb = (RadioButton)rdogrpPig.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpOtherAsset = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpOtherAsset.length; i++)
             {
                 if (String.valueOf(item.getOtherAsset()).equals(String.valueOf(d_rdogrpOtherAsset[i])))
                 {
                     rb = (RadioButton)rdogrpOtherAsset.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtOtherAsset1.setText(item.getOtherAsset1());
             txtOtherAsset2.setText(item.getOtherAsset2());
             txtOtherAsset3.setText(item.getOtherAsset3());
             txtSESNote.setText(item.getSESNote());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(SES.this, e.getMessage());
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


      dtpDate = (EditText)findViewById(R.id.dtpSESVDate);
      if (VariableID.equals("btnSESVDate"))
      {
          dtpDate = (EditText)findViewById(R.id.dtpSESVDate);
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