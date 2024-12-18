
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

 import forms_datamodel.SES_Mali_DataModel;
 import Utility.*;
 import Common.*;
 import forms_datamodel.tmpSES_Mali_DataModel;

 public class Surv_SES_Mali extends AppCompatActivity {
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
    LinearLayout secWaterSource;
    View lineWaterSource;
    TextView VlblWaterSource;
    RadioGroup rdogrpWaterSource;
    RadioButton rdoWaterSource1;
    RadioButton rdoWaterSource2;
    RadioButton rdoWaterSource3;
    RadioButton rdoWaterSource4;
    RadioButton rdoWaterSource5;
    LinearLayout secFetchWaterM;
    View lineFetchWaterM;
    TextView VlblFetchWaterM;
    EditText txtFetchWaterM;
    LinearLayout secFetchWaterMDk;
    View lineFetchWaterMDk;
    TextView VlblFetchWaterMDk;
    RadioGroup rdogrpFetchWaterMDk;
    RadioButton rdoFetchWaterMDk1;
    RadioButton rdoFetchWaterMDk2;
    LinearLayout secGetWater;
    View lineGetWater;
    TextView VlblGetWater;
    RadioGroup rdogrpGetWater;
    RadioButton rdoGetWater1;
    RadioButton rdoGetWater2;
    RadioButton rdoGetWater3;
    LinearLayout secGetWaterOth;
    View lineGetWaterOth;
    TextView VlblGetWaterOth;
    AutoCompleteTextView txtGetWaterOth;
    LinearLayout secMemberID;
    View lineMemberID;
    TextView VlblMemberID;
    Spinner spnMemberID;
    LinearLayout secBringWater;
    View lineBringWater;
    TextView VlblBringWater;
    EditText txtBringWater;
    LinearLayout secBringWaterDk;
    View lineBringWaterDk;
    TextView VlblBringWaterDk;
    RadioGroup rdogrpBringWaterDk;
    RadioButton rdoBringWaterDk1;
    RadioButton rdoBringWaterDk2;
    LinearLayout secSomeone;
    View lineSomeone;
    TextView VlblSomeone;
    RadioGroup rdogrpSomeone;
    RadioButton rdoSomeone1;
    RadioButton rdoSomeone2;
    LinearLayout secSecondPers;
    View lineSecondPers;
    TextView VlblSecondPers;
    RadioGroup rdogrpSecondPers;
    RadioButton rdoSecondPers1;
    RadioButton rdoSecondPers2;
    RadioButton rdoSecondPers3;
    LinearLayout secSecondPersOth;
    View lineSecondPersOth;
    TextView VlblSecondPersOth;
    AutoCompleteTextView txtSecondPersOth;
    LinearLayout secMemberID2nd;
    View lineMemberID2nd;
    TextView VlblMemberID2nd;
    Spinner spnMemberID2nd;
    LinearLayout secEnoughWater;
    View lineEnoughWater;
    TextView VlblEnoughWater;
    RadioGroup rdogrpEnoughWater;
    RadioButton rdoEnoughWater1;
    RadioButton rdoEnoughWater2;
    RadioButton rdoEnoughWater3;
    LinearLayout secMainWater;
    View lineMainWater;
    TextView VlblMainWater;
    Spinner spnMainWater;
    LinearLayout secMainWaterOth;
    View lineMainWaterOth;
    TextView VlblMainWaterOth;
    AutoCompleteTextView txtMainWaterOth;
    LinearLayout seclbl111;
    View linelbl111;
    LinearLayout secSmallTank;
    View lineSmallTank;
    TextView VlblSmallTank;
    RadioGroup rdogrpSmallTank;
    RadioButton rdoSmallTank1;
    RadioButton rdoSmallTank2;
    RadioButton rdoSmallTank3;
    LinearLayout secMediunTank;
    View lineMediunTank;
    TextView VlblMediunTank;
    RadioGroup rdogrpMediunTank;
    RadioButton rdoMediunTank1;
    RadioButton rdoMediunTank2;
    RadioButton rdoMediunTank3;
    LinearLayout secLargeTank;
    View lineLargeTank;
    TextView VlblLargeTank;
    RadioGroup rdogrpLargeTank;
    RadioButton rdoLargeTank1;
    RadioButton rdoLargeTank2;
    RadioButton rdoLargeTank3;
    LinearLayout secStoreDrink;
    View lineStoreDrink;
    TextView VlblStoreDrink;
    RadioGroup rdogrpStoreDrink;
    RadioButton rdoStoreDrink1;
    RadioButton rdoStoreDrink2;
    LinearLayout seclbl113;
    View linelbl113;
    LinearLayout secContainOpenCov;
    View lineContainOpenCov;
    TextView VlblContainOpenCov;
    CheckBox chkContainOpenCov;
    LinearLayout secContainOpenNotCov;
    View lineContainOpenNotCov;
    TextView VlblContainOpenNotCov;
    CheckBox chkContainOpenNotCov;
    LinearLayout secContainOpenDK;
    View lineContainOpenDK;
    TextView VlblContainOpenDK;
    CheckBox chkContainOpenDK;
    LinearLayout secRecoverWater;
    View lineRecoverWater;
    TextView VlblRecoverWater;
    RadioGroup rdogrpRecoverWater;
    RadioButton rdoRecoverWater1;
    RadioButton rdoRecoverWater2;
    RadioButton rdoRecoverWater3;
    RadioButton rdoRecoverWater4;
    LinearLayout secRecoverWaterOth;
    View lineRecoverWaterOth;
    TextView VlblRecoverWaterOth;
    AutoCompleteTextView txtRecoverWaterOth;
    LinearLayout secLessDanger;
    View lineLessDanger;
    TextView VlblLessDanger;
    RadioGroup rdogrpLessDanger;
    RadioButton rdoLessDanger1;
    RadioButton rdoLessDanger2;
    RadioButton rdoLessDanger3;
    LinearLayout secMakeSafer;
    View lineMakeSafer;
    TextView VlblMakeSafer;
    Spinner spnMakeSafer;
    LinearLayout secMakeSaferOth;
    View lineMakeSaferOth;
    TextView VlblMakeSaferOth;
    AutoCompleteTextView txtMakeSaferOth;
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
    RadioButton rdoToiletShrd3;
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
    RadioButton rdoToiletUseNoDk3;
    LinearLayout secToiletLoc;
    View lineToiletLoc;
    TextView VlblToiletLoc;
    RadioGroup rdogrpToiletLoc;
    RadioButton rdoToiletLoc1;
    RadioButton rdoToiletLoc2;
    RadioButton rdoToiletLoc3;
    LinearLayout secContentEmp;
    View lineContentEmp;
    TextView VlblContentEmp;
    RadioGroup rdogrpContentEmp;
    RadioButton rdoContentEmp1;
    RadioButton rdoContentEmp2;
    RadioButton rdoContentEmp3;
    RadioButton rdoContentEmp4;
    RadioButton rdoContentEmp5;
    RadioButton rdoContentEmp6;
    RadioButton rdoContentEmp7;
    LinearLayout secContentEmpOth;
    View lineContentEmpOth;
    TextView VlblContentEmpOth;
    AutoCompleteTextView txtContentEmpOth;
    LinearLayout secBowelMov;
    View lineBowelMov;
    TextView VlblBowelMov;
    Spinner spnBowelMov;
    LinearLayout secBowelMovOth;
    View lineBowelMovOth;
    TextView VlblBowelMovOth;
    AutoCompleteTextView txtBowelMovOth;
    LinearLayout secLiquidWaste;
    View lineLiquidWaste;
    TextView VlblLiquidWaste;
    RadioGroup rdogrpLiquidWaste;
    RadioButton rdoLiquidWaste1;
    RadioButton rdoLiquidWaste2;
    RadioButton rdoLiquidWaste3;
    RadioButton rdoLiquidWaste4;
    RadioButton rdoLiquidWaste5;
    RadioButton rdoLiquidWaste6;
    LinearLayout secLiquidWasteOth;
    View lineLiquidWasteOth;
    TextView VlblLiquidWasteOth;
    AutoCompleteTextView txtLiquidWasteOth;
    LinearLayout secSolidWasteMethod;
    View lineSolidWasteMethod;
    TextView VlblSolidWasteMethod;
    Spinner spnSolidWasteMethod;
    LinearLayout secSolidWasteMethodOth;
    View lineSolidWasteMethodOth;
    TextView VlblSolidWasteMethodOth;
    AutoCompleteTextView txtSolidWasteMethodOth;
    LinearLayout secHandWash;
    View lineHandWash;
    TextView VlblHandWash;
    RadioGroup rdogrpHandWash;
    RadioButton rdoHandWash1;
    RadioButton rdoHandWash2;
    RadioButton rdoHandWash3;
    LinearLayout secShowWash;
    View lineShowWash;
    TextView VlblShowWash;
    RadioGroup rdogrpShowWash;
    RadioButton rdoShowWash1;
    RadioButton rdoShowWash2;
    RadioButton rdoShowWash3;
    RadioButton rdoShowWash4;
    RadioButton rdoShowWash5;
    RadioButton rdoShowWash6;
    LinearLayout secAvailableWat;
    View lineAvailableWat;
    TextView VlblAvailableWat;
    RadioGroup rdogrpAvailableWat;
    RadioButton rdoAvailableWat1;
    RadioButton rdoAvailableWat2;
    LinearLayout secAvailableSoap;
    View lineAvailableSoap;
    TextView VlblAvailableSoap;
    RadioGroup rdogrpAvailableSoap;
    RadioButton rdoAvailableSoap1;
    RadioButton rdoAvailableSoap2;
    RadioButton rdoAvailableSoap3;
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
    LinearLayout secGroundMat;
    View lineGroundMat;
    TextView VlblGroundMat;
    Spinner spnGroundMat;
    LinearLayout secGroundMatOth;
    View lineGroundMatOth;
    TextView VlblGroundMatOth;
    AutoCompleteTextView txtGroundMatOth;
    LinearLayout secRoof;
    View lineRoof;
    TextView VlblRoof;
    Spinner spnRoof;
    LinearLayout secRoofOth;
    View lineRoofOth;
    TextView VlblRoofOth;
    AutoCompleteTextView txtRoofOth;
    LinearLayout secSmokeInside;
    View lineSmokeInside;
    TextView VlblSmokeInside;
    RadioGroup rdogrpSmokeInside;
    RadioButton rdoSmokeInside1;
    RadioButton rdoSmokeInside2;
    RadioButton rdoSmokeInside3;
    LinearLayout secFreqSmoke;
    View lineFreqSmoke;
    TextView VlblFreqSmoke;
    RadioGroup rdogrpFreqSmoke;
    RadioButton rdoFreqSmoke1;
    RadioButton rdoFreqSmoke2;
    RadioButton rdoFreqSmoke3;
    RadioButton rdoFreqSmoke4;
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
    LinearLayout secElecNight;
    View lineElecNight;
    TextView VlblElecNight;
    Spinner spnElecNight;
    LinearLayout secElecNightOth;
    View lineElecNightOth;
    TextView VlblElecNightOth;
    AutoCompleteTextView txtElecNightOth;
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
    LinearLayout secArea;
    View lineArea;
    TextView VlblArea;
    EditText txtArea;
    LinearLayout secIncomeMo;
    View lineIncomeMo;
    TextView VlblIncomeMo;
    RadioGroup rdogrpIncomeMo;
    RadioButton rdoIncomeMo1;
    RadioButton rdoIncomeMo2;
    RadioButton rdoIncomeMo3;
    RadioButton rdoIncomeMo4;
    RadioButton rdoIncomeMo5;
    RadioButton rdoIncomeMo6;
    RadioButton rdoIncomeMo7;
    RadioButton rdoIncomeMo8;
    LinearLayout secExpenses;
    View lineExpenses;
    TextView VlblExpenses;
    RadioGroup rdogrpExpenses;
    RadioButton rdoExpenses1;
    RadioButton rdoExpenses2;
    RadioButton rdoExpenses3;
    RadioButton rdoExpenses4;
    RadioButton rdoExpenses5;
    RadioButton rdoExpenses6;
    RadioButton rdoExpenses7;
    RadioButton rdoExpenses8;
    LinearLayout secBankAcc;
    View lineBankAcc;
    TextView VlblBankAcc;
    RadioGroup rdogrpBankAcc;
    RadioButton rdoBankAcc1;
    RadioButton rdoBankAcc2;
    LinearLayout secSprayInt;
    View lineSprayInt;
    TextView VlblSprayInt;
    RadioGroup rdogrpSprayInt;
    RadioButton rdoSprayInt1;
    RadioButton rdoSprayInt2;
    RadioButton rdoSprayInt3;
    LinearLayout secMosqNet;
    View lineMosqNet;
    TextView VlblMosqNet;
    RadioGroup rdogrpMosqNet;
    RadioButton rdoMosqNet1;
    RadioButton rdoMosqNet2;
    RadioButton rdoMosqNet3;
    LinearLayout secNetOwned;
    View lineNetOwned;
    TextView VlblNetOwned;
    EditText txtNetOwned;
    LinearLayout secMedHome;
    View lineMedHome;
    TextView VlblMedHome;
    RadioGroup rdogrpMedHome;
    RadioButton rdoMedHome1;
    RadioButton rdoMedHome2;
    RadioButton rdoMedHome3;
    LinearLayout seclbl119;
    View linelbl119;
    LinearLayout secMedTypeAM;
    View lineMedTypeAM;
    TextView VlblMedTypeAM;
    CheckBox chkMedTypeAM;
    LinearLayout secMedTypeAB;
    View lineMedTypeAB;
    TextView VlblMedTypeAB;
    CheckBox chkMedTypeAB;
    LinearLayout secMedTypeDK;
    View lineMedTypeDK;
    TextView VlblMedTypeDK;
    CheckBox chkMedTypeDK;
    LinearLayout seclbl1110;
    View linelbl1110;
    LinearLayout secAntimalarAL;
    View lineAntimalarAL;
    TextView VlblAntimalarAL;
    CheckBox chkAntimalarAL;
    LinearLayout secAntimalarASAQ;
    View lineAntimalarASAQ;
    TextView VlblAntimalarASAQ;
    CheckBox chkAntimalarASAQ;
    LinearLayout secAntimalarSP;
    View lineAntimalarSP;
    TextView VlblAntimalarSP;
    CheckBox chkAntimalarSP;
    LinearLayout secAntimalarOth;
    View lineAntimalarOth;
    TextView VlblAntimalarOth;
    CheckBox chkAntimalarOth;
    LinearLayout secAntimalarSpecifyOth;
    View lineAntimalarSpecifyOth;
    TextView VlblAntimalarSpecifyOth;
    AutoCompleteTextView txtAntimalarSpecifyOth;
    LinearLayout seclbl1111;
    View linelbl1111;
    LinearLayout secGetMedHosp;
    View lineGetMedHosp;
    TextView VlblGetMedHosp;
    CheckBox chkGetMedHosp;
    LinearLayout secGetMedCSCom;
    View lineGetMedCSCom;
    TextView VlblGetMedCSCom;
    CheckBox chkGetMedCSCom;
    LinearLayout secGetMedPrvCl;
    View lineGetMedPrvCl;
    TextView VlblGetMedPrvCl;
    CheckBox chkGetMedPrvCl;
    LinearLayout secGetMedPhar;
    View lineGetMedPhar;
    TextView VlblGetMedPhar;
    CheckBox chkGetMedPhar;
    LinearLayout secGetMedPD;
    View lineGetMedPD;
    TextView VlblGetMedPD;
    CheckBox chkGetMedPD;
    LinearLayout secGetMedCHW;
    View lineGetMedCHW;
    TextView VlblGetMedCHW;
    CheckBox chkGetMedCHW;
    LinearLayout secGetMedSS;
    View lineGetMedSS;
    TextView VlblGetMedSS;
    CheckBox chkGetMedSS;
    LinearLayout secGetMedOth;
    View lineGetMedOth;
    TextView VlblGetMedOth;
    CheckBox chkGetMedOth;
    LinearLayout secGetMedSpecifyOth;
    View lineGetMedSpecifyOth;
    TextView VlblGetMedSpecifyOth;
    AutoCompleteTextView txtGetMedSpecifyOth;
    LinearLayout secAComment;
    View lineAComment;
    TextView VlblAComment;
    RadioGroup rdogrpAComment;
    RadioButton rdoAComment1;
    RadioButton rdoAComment2;
    LinearLayout secComment;
    View lineComment;
    TextView VlblComment;
    AutoCompleteTextView txtComment;
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
    LinearLayout secSolarPlate;
    View lineSolarPlate;
    TextView VlblSolarPlate;
    RadioGroup rdogrpSolarPlate;
    RadioButton rdoSolarPlate1;
    RadioButton rdoSolarPlate2;
    LinearLayout secSolarPlateN;
    View lineSolarPlateN;
    TextView VlblSolarPlateN;
    EditText txtSolarPlateN;
    LinearLayout secHeater;
    View lineHeater;
    TextView VlblHeater;
    RadioGroup rdogrpHeater;
    RadioButton rdoHeater1;
    RadioButton rdoHeater2;
    LinearLayout secHeaterN;
    View lineHeaterN;
    TextView VlblHeaterN;
    EditText txtHeaterN;
    LinearLayout secAC;
    View lineAC;
    TextView VlblAC;
    RadioGroup rdogrpAC;
    RadioButton rdoAC1;
    RadioButton rdoAC2;
    LinearLayout secACN;
    View lineACN;
    TextView VlblACN;
    EditText txtACN;
    LinearLayout secElecFan;
    View lineElecFan;
    TextView VlblElecFan;
    RadioGroup rdogrpElecFan;
    RadioButton rdoElecFan1;
    RadioButton rdoElecFan2;
    LinearLayout secElecFanN;
    View lineElecFanN;
    TextView VlblElecFanN;
    EditText txtElecFanN;
    LinearLayout secLantern;
    View lineLantern;
    TextView VlblLantern;
    RadioGroup rdogrpLantern;
    RadioButton rdoLantern1;
    RadioButton rdoLantern2;
    LinearLayout secLanternN;
    View lineLanternN;
    TextView VlblLanternN;
    EditText txtLanternN;
    LinearLayout secLamp;
    View lineLamp;
    TextView VlblLamp;
    RadioGroup rdogrpLamp;
    RadioButton rdoLamp1;
    RadioButton rdoLamp2;
    LinearLayout secLampN;
    View lineLampN;
    TextView VlblLampN;
    EditText txtLampN;
    LinearLayout secGasLamp;
    View lineGasLamp;
    TextView VlblGasLamp;
    RadioGroup rdogrpGasLamp;
    RadioButton rdoGasLamp1;
    RadioButton rdoGasLamp2;
    LinearLayout secGasLampN;
    View lineGasLampN;
    TextView VlblGasLampN;
    EditText txtGasLampN;
    LinearLayout secPetroleum;
    View linePetroleum;
    TextView VlblPetroleum;
    RadioGroup rdogrpPetroleum;
    RadioButton rdoPetroleum1;
    RadioButton rdoPetroleum2;
    LinearLayout secPetroleumN;
    View linePetroleumN;
    TextView VlblPetroleumN;
    EditText txtPetroleumN;
    LinearLayout secMatt;
    View lineMatt;
    TextView VlblMatt;
    RadioGroup rdogrpMatt;
    RadioButton rdoMatt1;
    RadioButton rdoMatt2;
    LinearLayout secMattN;
    View lineMattN;
    TextView VlblMattN;
    EditText txtMattN;
    LinearLayout secMats;
    View lineMats;
    TextView VlblMats;
    RadioGroup rdogrpMats;
    RadioButton rdoMats1;
    RadioButton rdoMats2;
    LinearLayout secMatsN;
    View lineMatsN;
    TextView VlblMatsN;
    EditText txtMatsN;
    LinearLayout secCarpets;
    View lineCarpets;
    TextView VlblCarpets;
    RadioGroup rdogrpCarpets;
    RadioButton rdoCarpets1;
    RadioButton rdoCarpets2;
    LinearLayout secCarpetsN;
    View lineCarpetsN;
    TextView VlblCarpetsN;
    EditText txtCarpetsN;
    LinearLayout secBed;
    View lineBed;
    TextView VlblBed;
    RadioGroup rdogrpBed;
    RadioButton rdoBed1;
    RadioButton rdoBed2;
    LinearLayout secBedN;
    View lineBedN;
    TextView VlblBedN;
    EditText txtBedN;
    LinearLayout secChair;
    View lineChair;
    TextView VlblChair;
    RadioGroup rdogrpChair;
    RadioButton rdoChair1;
    RadioButton rdoChair2;
    LinearLayout secChairN;
    View lineChairN;
    TextView VlblChairN;
    EditText txtChairN;
    LinearLayout secSofa;
    View lineSofa;
    TextView VlblSofa;
    RadioGroup rdogrpSofa;
    RadioButton rdoSofa1;
    RadioButton rdoSofa2;
    LinearLayout secSofaN;
    View lineSofaN;
    TextView VlblSofaN;
    EditText txtSofaN;
    LinearLayout secTables;
    View lineTables;
    TextView VlblTables;
    RadioGroup rdogrpTables;
    RadioButton rdoTables1;
    RadioButton rdoTables2;
    LinearLayout secTablesN;
    View lineTablesN;
    TextView VlblTablesN;
    EditText txtTablesN;
    LinearLayout secWatch;
    View lineWatch;
    TextView VlblWatch;
    RadioGroup rdogrpWatch;
    RadioButton rdoWatch1;
    RadioButton rdoWatch2;
    LinearLayout secWatchN;
    View lineWatchN;
    TextView VlblWatchN;
    EditText txtWatchN;
    LinearLayout secWMachine;
    View lineWMachine;
    TextView VlblWMachine;
    RadioGroup rdogrpWMachine;
    RadioButton rdoWMachine1;
    RadioButton rdoWMachine2;
    LinearLayout secWMachineN;
    View lineWMachineN;
    TextView VlblWMachineN;
    EditText txtWMachineN;
    LinearLayout secIron;
    View lineIron;
    TextView VlblIron;
    RadioGroup rdogrpIron;
    RadioButton rdoIron1;
    RadioButton rdoIron2;
    LinearLayout secIronN;
    View lineIronN;
    TextView VlblIronN;
    EditText txtIronN;
    LinearLayout secBooth;
    View lineBooth;
    TextView VlblBooth;
    RadioGroup rdogrpBooth;
    RadioButton rdoBooth1;
    RadioButton rdoBooth2;
    LinearLayout secBoothN;
    View lineBoothN;
    TextView VlblBoothN;
    EditText txtBoothN;
    LinearLayout secSMachine;
    View lineSMachine;
    TextView VlblSMachine;
    RadioGroup rdogrpSMachine;
    RadioButton rdoSMachine1;
    RadioButton rdoSMachine2;
    LinearLayout secSMachineN;
    View lineSMachineN;
    TextView VlblSMachineN;
    EditText txtSMachineN;
    LinearLayout secGenerator;
    View lineGenerator;
    TextView VlblGenerator;
    RadioGroup rdogrpGenerator;
    RadioButton rdoGenerator1;
    RadioButton rdoGenerator2;
    LinearLayout secGeneratorN;
    View lineGeneratorN;
    TextView VlblGeneratorN;
    EditText txtGeneratorN;
    LinearLayout seclblH12a;
    View linelblH12a;
    LinearLayout secInternet;
    View lineInternet;
    TextView VlblInternet;
    RadioGroup rdogrpInternet;
    RadioButton rdoInternet1;
    RadioButton rdoInternet2;
    LinearLayout secInternetN;
    View lineInternetN;
    TextView VlblInternetN;
    EditText txtInternetN;
    LinearLayout secSatellite;
    View lineSatellite;
    TextView VlblSatellite;
    RadioGroup rdogrpSatellite;
    RadioButton rdoSatellite1;
    RadioButton rdoSatellite2;
    LinearLayout secSatelliteN;
    View lineSatelliteN;
    TextView VlblSatelliteN;
    EditText txtSatelliteN;
    LinearLayout secLandline;
    View lineLandline;
    TextView VlblLandline;
    RadioGroup rdogrpLandline;
    RadioButton rdoLandline1;
    RadioButton rdoLandline2;
    LinearLayout secLandlineN;
    View lineLandlineN;
    TextView VlblLandlineN;
    EditText txtLandlineN;
    LinearLayout secCellphone;
    View lineCellphone;
    TextView VlblCellphone;
    RadioGroup rdogrpCellphone;
    RadioButton rdoCellphone1;
    RadioButton rdoCellphone2;
    LinearLayout secCellphoneN;
    View lineCellphoneN;
    TextView VlblCellphoneN;
    EditText txtCellphoneN;
    LinearLayout secTV;
    View lineTV;
    TextView VlblTV;
    RadioGroup rdogrpTV;
    RadioButton rdoTV1;
    RadioButton rdoTV2;
    LinearLayout secTVN;
    View lineTVN;
    TextView VlblTVN;
    EditText txtTVN;
    LinearLayout secTV5;
    View lineTV5;
    TextView VlblTV5;
    RadioGroup rdogrpTV5;
    RadioButton rdoTV51;
    RadioButton rdoTV52;
    LinearLayout secTV5N;
    View lineTV5N;
    TextView VlblTV5N;
    EditText txtTV5N;
    LinearLayout secChannel;
    View lineChannel;
    TextView VlblChannel;
    RadioGroup rdogrpChannel;
    RadioButton rdoChannel1;
    RadioButton rdoChannel2;
    LinearLayout secChannelN;
    View lineChannelN;
    TextView VlblChannelN;
    EditText txtChannelN;
    LinearLayout secRadio;
    View lineRadio;
    TextView VlblRadio;
    RadioGroup rdogrpRadio;
    RadioButton rdoRadio1;
    RadioButton rdoRadio2;
    LinearLayout secRadioN;
    View lineRadioN;
    TextView VlblRadioN;
    EditText txtRadioN;
    LinearLayout secDVD;
    View lineDVD;
    TextView VlblDVD;
    RadioGroup rdogrpDVD;
    RadioButton rdoDVD1;
    RadioButton rdoDVD2;
    LinearLayout secDVDN;
    View lineDVDN;
    TextView VlblDVDN;
    EditText txtDVDN;
    LinearLayout secVideo;
    View lineVideo;
    TextView VlblVideo;
    RadioGroup rdogrpVideo;
    RadioButton rdoVideo1;
    RadioButton rdoVideo2;
    LinearLayout secVideoN;
    View lineVideoN;
    TextView VlblVideoN;
    EditText txtVideoN;
    LinearLayout secComputer;
    View lineComputer;
    TextView VlblComputer;
    RadioGroup rdogrpComputer;
    RadioButton rdoComputer1;
    RadioButton rdoComputer2;
    LinearLayout secComputerN;
    View lineComputerN;
    TextView VlblComputerN;
    EditText txtComputerN;
    LinearLayout secLaptop;
    View lineLaptop;
    TextView VlblLaptop;
    RadioGroup rdogrpLaptop;
    RadioButton rdoLaptop1;
    RadioButton rdoLaptop2;
    LinearLayout secLaptopN;
    View lineLaptopN;
    TextView VlblLaptopN;
    EditText txtLaptopN;
    LinearLayout secCable;
    View lineCable;
    TextView VlblCable;
    RadioGroup rdogrpCable;
    RadioButton rdoCable1;
    RadioButton rdoCable2;
    LinearLayout secCableN;
    View lineCableN;
    TextView VlblCableN;
    EditText txtCableN;
    LinearLayout seclblH12b;
    View linelblH12b;
    LinearLayout secMicrowave;
    View lineMicrowave;
    TextView VlblMicrowave;
    RadioGroup rdogrpMicrowave;
    RadioButton rdoMicrowave1;
    RadioButton rdoMicrowave2;
    LinearLayout secMicrowaveN;
    View lineMicrowaveN;
    TextView VlblMicrowaveN;
    EditText txtMicrowaveN;
    LinearLayout secGeyser;
    View lineGeyser;
    TextView VlblGeyser;
    RadioGroup rdogrpGeyser;
    RadioButton rdoGeyser1;
    RadioButton rdoGeyser2;
    LinearLayout secGeyserN;
    View lineGeyserN;
    TextView VlblGeyserN;
    EditText txtGeyserN;
    LinearLayout secGrill;
    View lineGrill;
    TextView VlblGrill;
    RadioGroup rdogrpGrill;
    RadioButton rdoGrill1;
    RadioButton rdoGrill2;
    LinearLayout secGrillN;
    View lineGrillN;
    TextView VlblGrillN;
    EditText txtGrillN;
    LinearLayout secGrain;
    View lineGrain;
    TextView VlblGrain;
    RadioGroup rdogrpGrain;
    RadioButton rdoGrain1;
    RadioButton rdoGrain2;
    LinearLayout secGrainN;
    View lineGrainN;
    TextView VlblGrainN;
    EditText txtGrainN;
    LinearLayout secRefrigerator;
    View lineRefrigerator;
    TextView VlblRefrigerator;
    RadioGroup rdogrpRefrigerator;
    RadioButton rdoRefrigerator1;
    RadioButton rdoRefrigerator2;
    LinearLayout secRefrigeratorN;
    View lineRefrigeratorN;
    TextView VlblRefrigeratorN;
    EditText txtRefrigeratorN;
    LinearLayout secDeepFreezer;
    View lineDeepFreezer;
    TextView VlblDeepFreezer;
    RadioGroup rdogrpDeepFreezer;
    RadioButton rdoDeepFreezer1;
    RadioButton rdoDeepFreezer2;
    LinearLayout secDeepFreezerN;
    View lineDeepFreezerN;
    TextView VlblDeepFreezerN;
    EditText txtDeepFreezerN;
    LinearLayout secStove;
    View lineStove;
    TextView VlblStove;
    RadioGroup rdogrpStove;
    RadioButton rdoStove1;
    RadioButton rdoStove2;
    LinearLayout secStoveN;
    View lineStoveN;
    TextView VlblStoveN;
    EditText txtStoveN;
    LinearLayout secGasHob;
    View lineGasHob;
    TextView VlblGasHob;
    RadioGroup rdogrpGasHob;
    RadioButton rdoGasHob1;
    RadioButton rdoGasHob2;
    LinearLayout secGasHobN;
    View lineGasHobN;
    TextView VlblGasHobN;
    EditText txtGasHobN;
    LinearLayout secImpCooker;
    View lineImpCooker;
    TextView VlblImpCooker;
    RadioGroup rdogrpImpCooker;
    RadioButton rdoImpCooker1;
    RadioButton rdoImpCooker2;
    LinearLayout secImpCookerN;
    View lineImpCookerN;
    TextView VlblImpCookerN;
    EditText txtImpCookerN;
    LinearLayout seclblH12c;
    View linelblH12c;
    LinearLayout secBike;
    View lineBike;
    TextView VlblBike;
    RadioGroup rdogrpBike;
    RadioButton rdoBike1;
    RadioButton rdoBike2;
    LinearLayout secBikeN;
    View lineBikeN;
    TextView VlblBikeN;
    EditText txtBikeN;
    LinearLayout secMotorcycle;
    View lineMotorcycle;
    TextView VlblMotorcycle;
    RadioGroup rdogrpMotorcycle;
    RadioButton rdoMotorcycle1;
    RadioButton rdoMotorcycle2;
    LinearLayout secMotorcycleN;
    View lineMotorcycleN;
    TextView VlblMotorcycleN;
    EditText txtMotorcycleN;
    LinearLayout secCar;
    View lineCar;
    TextView VlblCar;
    RadioGroup rdogrpCar;
    RadioButton rdoCar1;
    RadioButton rdoCar2;
    LinearLayout secCarN;
    View lineCarN;
    TextView VlblCarN;
    EditText txtCarN;
    LinearLayout secRickshaw;
    View lineRickshaw;
    TextView VlblRickshaw;
    RadioGroup rdogrpRickshaw;
    RadioButton rdoRickshaw1;
    RadioButton rdoRickshaw2;
    LinearLayout secRickshawN;
    View lineRickshawN;
    TextView VlblRickshawN;
    EditText txtRickshawN;
    LinearLayout secCart;
    View lineCart;
    TextView VlblCart;
    RadioGroup rdogrpCart;
    RadioButton rdoCart1;
    RadioButton rdoCart2;
    LinearLayout secCartN;
    View lineCartN;
    TextView VlblCartN;
    EditText txtCartN;
    LinearLayout secCanoe;
    View lineCanoe;
    TextView VlblCanoe;
    RadioGroup rdogrpCanoe;
    RadioButton rdoCanoe1;
    RadioButton rdoCanoe2;
    LinearLayout secCanoeN;
    View lineCanoeN;
    TextView VlblCanoeN;
    EditText txtCanoeN;
    LinearLayout secBus;
    View lineBus;
    TextView VlblBus;
    RadioGroup rdogrpBus;
    RadioButton rdoBus1;
    RadioButton rdoBus2;
    LinearLayout secBusN;
    View lineBusN;
    TextView VlblBusN;
    EditText txtBusN;
    LinearLayout seclblH12d;
    View linelblH12d;
    LinearLayout secTractor;
    View lineTractor;
    TextView VlblTractor;
    RadioGroup rdogrpTractor;
    RadioButton rdoTractor1;
    RadioButton rdoTractor2;
    LinearLayout secTractorN;
    View lineTractorN;
    TextView VlblTractorN;
    EditText txtTractorN;
    LinearLayout secPlow;
    View linePlow;
    TextView VlblPlow;
    RadioGroup rdogrpPlow;
    RadioButton rdoPlow1;
    RadioButton rdoPlow2;
    LinearLayout secPlowN;
    View linePlowN;
    TextView VlblPlowN;
    EditText txtPlowN;
    LinearLayout secDuck;
    View lineDuck;
    TextView VlblDuck;
    RadioGroup rdogrpDuck;
    RadioButton rdoDuck1;
    RadioButton rdoDuck2;
    LinearLayout secDuckN;
    View lineDuckN;
    TextView VlblDuckN;
    EditText txtDuckN;
    LinearLayout secCow;
    View lineCow;
    TextView VlblCow;
    RadioGroup rdogrpCow;
    RadioButton rdoCow1;
    RadioButton rdoCow2;
    LinearLayout secCowN;
    View lineCowN;
    TextView VlblCowN;
    EditText txtCowN;
    LinearLayout secSheep;
    View lineSheep;
    TextView VlblSheep;
    RadioGroup rdogrpSheep;
    RadioButton rdoSheep1;
    RadioButton rdoSheep2;
    LinearLayout secSheepN;
    View lineSheepN;
    TextView VlblSheepN;
    EditText txtSheepN;
    LinearLayout secGoat;
    View lineGoat;
    TextView VlblGoat;
    RadioGroup rdogrpGoat;
    RadioButton rdoGoat1;
    RadioButton rdoGoat2;
    LinearLayout secGoatN;
    View lineGoatN;
    TextView VlblGoatN;
    EditText txtGoatN;
    LinearLayout secChicken;
    View lineChicken;
    TextView VlblChicken;
    RadioGroup rdogrpChicken;
    RadioButton rdoChicken1;
    RadioButton rdoChicken2;
    LinearLayout secChickenN;
    View lineChickenN;
    TextView VlblChickenN;
    EditText txtChickenN;
    LinearLayout secDonkey;
    View lineDonkey;
    TextView VlblDonkey;
    RadioGroup rdogrpDonkey;
    RadioButton rdoDonkey1;
    RadioButton rdoDonkey2;
    LinearLayout secDunkeyN;
    View lineDunkeyN;
    TextView VlblDunkeyN;
    EditText txtDunkeyN;
    LinearLayout secHorse;
    View lineHorse;
    TextView VlblHorse;
    RadioGroup rdogrpHorse;
    RadioButton rdoHorse1;
    RadioButton rdoHorse2;
    LinearLayout secHorseN;
    View lineHorseN;
    TextView VlblHorseN;
    EditText txtHorseN;
    LinearLayout secPig;
    View linePig;
    TextView VlblPig;
    RadioGroup rdogrpPig;
    RadioButton rdoPig1;
    RadioButton rdoPig2;
    LinearLayout secPigN;
    View linePigN;
    TextView VlblPigN;
    EditText txtPigN;
    LinearLayout secBirds;
    View lineBirds;
    TextView VlblBirds;
    RadioGroup rdogrpBirds;
    RadioButton rdoBirds1;
    RadioButton rdoBirds2;
    LinearLayout secBirdsN;
    View lineBirdsN;
    TextView VlblBirdsN;
    EditText txtBirdsN;
    LinearLayout secDogs;
    View lineDogs;
    TextView VlblDogs;
    RadioGroup rdogrpDogs;
    RadioButton rdoDogs1;
    RadioButton rdoDogs2;
    LinearLayout secDogsN;
    View lineDogsN;
    TextView VlblDogsN;
    EditText txtDogsN;
    LinearLayout secCats;
    View lineCats;
    TextView VlblCats;
    RadioGroup rdogrpCats;
    RadioButton rdoCats1;
    RadioButton rdoCats2;
    LinearLayout secCatsN;
    View lineCatsN;
    TextView VlblCatsN;
    EditText txtCatsN;
    LinearLayout secFishNet;
    View lineFishNet;
    TextView VlblFishNet;
    RadioGroup rdogrpFishNet;
    RadioButton rdoFishNet1;
    RadioButton rdoFishNet2;
    LinearLayout secFishNetN;
    View lineFishNetN;
    TextView VlblFishNetN;
    EditText txtFishNetN;
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
    LinearLayout secOtherAsset1N;
    View lineOtherAsset1N;
    TextView VlblOtherAsset1N;
    EditText txtOtherAsset1N;
    LinearLayout secOtherAsset2;
    View lineOtherAsset2;
    TextView VlblOtherAsset2;
    AutoCompleteTextView txtOtherAsset2;
    LinearLayout secOtherAsset2N;
    View lineOtherAsset2N;
    TextView VlblOtherAsset2N;
    EditText txtOtherAsset2N;
    LinearLayout secOtherAsset3;
    View lineOtherAsset3;
    TextView VlblOtherAsset3;
    AutoCompleteTextView txtOtherAsset3;
    LinearLayout secOtherAsset3N;
    View lineOtherAsset3N;
    TextView VlblOtherAsset3N;
    EditText txtOtherAsset3N;
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
     String ROUND = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.ses_mali);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         HHID = IDbundle.getString("HHID");
         SESNO = IDbundle.getString("SESNo");
         ROUND = IDbundle.getString("round");

         TableName = "tmpSES_Mali";
         MODULEID = 10;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Surv_SES_Mali.this);
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
        Connection.LocalizeLanguage(Surv_SES_Mali.this, MODULEID, LANGUAGEID);


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
         secWaterSource.setVisibility(View.GONE);
         lineWaterSource.setVisibility(View.GONE);
         secFetchWaterM.setVisibility(View.GONE);
         lineFetchWaterM.setVisibility(View.GONE);
         secFetchWaterMDk.setVisibility(View.GONE);
         lineFetchWaterMDk.setVisibility(View.GONE);
         secGetWater.setVisibility(View.GONE);
         lineGetWater.setVisibility(View.GONE);
         secGetWaterOth.setVisibility(View.GONE);
         lineGetWaterOth.setVisibility(View.GONE);
         secMemberID.setVisibility(View.GONE);
         lineMemberID.setVisibility(View.GONE);
         secBringWater.setVisibility(View.GONE);
         lineBringWater.setVisibility(View.GONE);
         secBringWaterDk.setVisibility(View.GONE);
         lineBringWaterDk.setVisibility(View.GONE);
         secSomeone.setVisibility(View.GONE);
         lineSomeone.setVisibility(View.GONE);
         secSecondPers.setVisibility(View.GONE);
         lineSecondPers.setVisibility(View.GONE);
         secSecondPersOth.setVisibility(View.GONE);
         lineSecondPersOth.setVisibility(View.GONE);
         secMemberID2nd.setVisibility(View.GONE);
         lineMemberID2nd.setVisibility(View.GONE);
         secEnoughWater.setVisibility(View.GONE);
         lineEnoughWater.setVisibility(View.GONE);
         secMainWater.setVisibility(View.GONE);
         lineMainWater.setVisibility(View.GONE);
         secMainWaterOth.setVisibility(View.GONE);
         lineMainWaterOth.setVisibility(View.GONE);
         seclbl111.setVisibility(View.GONE);
         linelbl111.setVisibility(View.GONE);
         secSmallTank.setVisibility(View.GONE);
         lineSmallTank.setVisibility(View.GONE);
         secMediunTank.setVisibility(View.GONE);
         lineMediunTank.setVisibility(View.GONE);
         secLargeTank.setVisibility(View.GONE);
         lineLargeTank.setVisibility(View.GONE);
         secStoreDrink.setVisibility(View.GONE);
         lineStoreDrink.setVisibility(View.GONE);
         seclbl113.setVisibility(View.GONE);
         linelbl113.setVisibility(View.GONE);
         secContainOpenCov.setVisibility(View.GONE);
         lineContainOpenCov.setVisibility(View.GONE);
         secContainOpenNotCov.setVisibility(View.GONE);
         lineContainOpenNotCov.setVisibility(View.GONE);
         secContainOpenDK.setVisibility(View.GONE);
         lineContainOpenDK.setVisibility(View.GONE);
         secRecoverWater.setVisibility(View.GONE);
         lineRecoverWater.setVisibility(View.GONE);
         secRecoverWaterOth.setVisibility(View.GONE);
         lineRecoverWaterOth.setVisibility(View.GONE);
         secLessDanger.setVisibility(View.GONE);
         lineLessDanger.setVisibility(View.GONE);
         secMakeSafer.setVisibility(View.GONE);
         lineMakeSafer.setVisibility(View.GONE);
         secMakeSaferOth.setVisibility(View.GONE);
         lineMakeSaferOth.setVisibility(View.GONE);
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
         secContentEmp.setVisibility(View.GONE);
         lineContentEmp.setVisibility(View.GONE);
         secContentEmpOth.setVisibility(View.GONE);
         lineContentEmpOth.setVisibility(View.GONE);
         secBowelMov.setVisibility(View.GONE);
         lineBowelMov.setVisibility(View.GONE);
         secBowelMovOth.setVisibility(View.GONE);
         lineBowelMovOth.setVisibility(View.GONE);
         secLiquidWaste.setVisibility(View.GONE);
         lineLiquidWaste.setVisibility(View.GONE);
         secLiquidWasteOth.setVisibility(View.GONE);
         lineLiquidWasteOth.setVisibility(View.GONE);
         secSolidWasteMethod.setVisibility(View.GONE);
         lineSolidWasteMethod.setVisibility(View.GONE);
         secSolidWasteMethodOth.setVisibility(View.GONE);
         lineSolidWasteMethodOth.setVisibility(View.GONE);
         secHandWash.setVisibility(View.GONE);
         lineHandWash.setVisibility(View.GONE);
         secShowWash.setVisibility(View.GONE);
         lineShowWash.setVisibility(View.GONE);
         secAvailableWat.setVisibility(View.GONE);
         lineAvailableWat.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
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
         secGroundMat.setVisibility(View.GONE);
         lineGroundMat.setVisibility(View.GONE);
         secGroundMatOth.setVisibility(View.GONE);
         lineGroundMatOth.setVisibility(View.GONE);
         secRoof.setVisibility(View.GONE);
         lineRoof.setVisibility(View.GONE);
         secRoofOth.setVisibility(View.GONE);
         lineRoofOth.setVisibility(View.GONE);
         secSmokeInside.setVisibility(View.GONE);
         lineSmokeInside.setVisibility(View.GONE);
         secFreqSmoke.setVisibility(View.GONE);
         lineFreqSmoke.setVisibility(View.GONE);
         secWall.setVisibility(View.GONE);
         lineWall.setVisibility(View.GONE);
         secWallOth.setVisibility(View.GONE);
         lineWallOth.setVisibility(View.GONE);
         secRoomSleep.setVisibility(View.GONE);
         lineRoomSleep.setVisibility(View.GONE);
         secRoomSleepDk.setVisibility(View.GONE);
         lineRoomSleepDk.setVisibility(View.GONE);
         secElecNight.setVisibility(View.GONE);
         lineElecNight.setVisibility(View.GONE);
         secElecNightOth.setVisibility(View.GONE);
         lineElecNightOth.setVisibility(View.GONE);
         secHomesteadAny.setVisibility(View.GONE);
         lineHomesteadAny.setVisibility(View.GONE);
         secOthLand.setVisibility(View.GONE);
         lineOthLand.setVisibility(View.GONE);
         secArea.setVisibility(View.GONE);
         lineArea.setVisibility(View.GONE);
         secIncomeMo.setVisibility(View.GONE);
         lineIncomeMo.setVisibility(View.GONE);
         secExpenses.setVisibility(View.GONE);
         lineExpenses.setVisibility(View.GONE);
         secBankAcc.setVisibility(View.GONE);
         lineBankAcc.setVisibility(View.GONE);
         secSprayInt.setVisibility(View.GONE);
         lineSprayInt.setVisibility(View.GONE);
         secMosqNet.setVisibility(View.GONE);
         lineMosqNet.setVisibility(View.GONE);
         secNetOwned.setVisibility(View.GONE);
         lineNetOwned.setVisibility(View.GONE);
         secMedHome.setVisibility(View.GONE);
         lineMedHome.setVisibility(View.GONE);
         seclbl119.setVisibility(View.GONE);
         linelbl119.setVisibility(View.GONE);
         secMedTypeAM.setVisibility(View.GONE);
         lineMedTypeAM.setVisibility(View.GONE);
         secMedTypeAB.setVisibility(View.GONE);
         lineMedTypeAB.setVisibility(View.GONE);
         secMedTypeDK.setVisibility(View.GONE);
         lineMedTypeDK.setVisibility(View.GONE);
         seclbl1110.setVisibility(View.GONE);
         linelbl1110.setVisibility(View.GONE);
         secAntimalarAL.setVisibility(View.GONE);
         lineAntimalarAL.setVisibility(View.GONE);
         secAntimalarASAQ.setVisibility(View.GONE);
         lineAntimalarASAQ.setVisibility(View.GONE);
         secAntimalarSP.setVisibility(View.GONE);
         lineAntimalarSP.setVisibility(View.GONE);
         secAntimalarOth.setVisibility(View.GONE);
         lineAntimalarOth.setVisibility(View.GONE);
         secAntimalarSpecifyOth.setVisibility(View.GONE);
         lineAntimalarSpecifyOth.setVisibility(View.GONE);
         seclbl1111.setVisibility(View.GONE);
         linelbl1111.setVisibility(View.GONE);
         secGetMedHosp.setVisibility(View.GONE);
         lineGetMedHosp.setVisibility(View.GONE);
         secGetMedCSCom.setVisibility(View.GONE);
         lineGetMedCSCom.setVisibility(View.GONE);
         secGetMedPrvCl.setVisibility(View.GONE);
         lineGetMedPrvCl.setVisibility(View.GONE);
         secGetMedPhar.setVisibility(View.GONE);
         lineGetMedPhar.setVisibility(View.GONE);
         secGetMedPD.setVisibility(View.GONE);
         lineGetMedPD.setVisibility(View.GONE);
         secGetMedCHW.setVisibility(View.GONE);
         lineGetMedCHW.setVisibility(View.GONE);
         secGetMedSS.setVisibility(View.GONE);
         lineGetMedSS.setVisibility(View.GONE);
         secGetMedOth.setVisibility(View.GONE);
         lineGetMedOth.setVisibility(View.GONE);
         secGetMedSpecifyOth.setVisibility(View.GONE);
         lineGetMedSpecifyOth.setVisibility(View.GONE);
         secAComment.setVisibility(View.GONE);
         lineAComment.setVisibility(View.GONE);
         secComment.setVisibility(View.GONE);
         lineComment.setVisibility(View.GONE);
         seclblH12.setVisibility(View.GONE);
         linelblH12.setVisibility(View.GONE);
         seclblH121.setVisibility(View.GONE);
         linelblH121.setVisibility(View.GONE);
         secElectricity.setVisibility(View.GONE);
         lineElectricity.setVisibility(View.GONE);
         secSolarPlate.setVisibility(View.GONE);
         lineSolarPlate.setVisibility(View.GONE);
         secSolarPlateN.setVisibility(View.GONE);
         lineSolarPlateN.setVisibility(View.GONE);
         secHeater.setVisibility(View.GONE);
         lineHeater.setVisibility(View.GONE);
         secHeaterN.setVisibility(View.GONE);
         lineHeaterN.setVisibility(View.GONE);
         secAC.setVisibility(View.GONE);
         lineAC.setVisibility(View.GONE);
         secACN.setVisibility(View.GONE);
         lineACN.setVisibility(View.GONE);
         secElecFan.setVisibility(View.GONE);
         lineElecFan.setVisibility(View.GONE);
         secElecFanN.setVisibility(View.GONE);
         lineElecFanN.setVisibility(View.GONE);
         secLantern.setVisibility(View.GONE);
         lineLantern.setVisibility(View.GONE);
         secLanternN.setVisibility(View.GONE);
         lineLanternN.setVisibility(View.GONE);
         secLamp.setVisibility(View.GONE);
         lineLamp.setVisibility(View.GONE);
         secLampN.setVisibility(View.GONE);
         lineLampN.setVisibility(View.GONE);
         secGasLamp.setVisibility(View.GONE);
         lineGasLamp.setVisibility(View.GONE);
         secGasLampN.setVisibility(View.GONE);
         lineGasLampN.setVisibility(View.GONE);
         secPetroleum.setVisibility(View.GONE);
         linePetroleum.setVisibility(View.GONE);
         secPetroleumN.setVisibility(View.GONE);
         linePetroleumN.setVisibility(View.GONE);
         secMatt.setVisibility(View.GONE);
         lineMatt.setVisibility(View.GONE);
         secMattN.setVisibility(View.GONE);
         lineMattN.setVisibility(View.GONE);
         secMats.setVisibility(View.GONE);
         lineMats.setVisibility(View.GONE);
         secMatsN.setVisibility(View.GONE);
         lineMatsN.setVisibility(View.GONE);
         secCarpets.setVisibility(View.GONE);
         lineCarpets.setVisibility(View.GONE);
         secCarpetsN.setVisibility(View.GONE);
         lineCarpetsN.setVisibility(View.GONE);
         secBed.setVisibility(View.GONE);
         lineBed.setVisibility(View.GONE);
         secBedN.setVisibility(View.GONE);
         lineBedN.setVisibility(View.GONE);
         secChair.setVisibility(View.GONE);
         lineChair.setVisibility(View.GONE);
         secChairN.setVisibility(View.GONE);
         lineChairN.setVisibility(View.GONE);
         secSofa.setVisibility(View.GONE);
         lineSofa.setVisibility(View.GONE);
         secSofaN.setVisibility(View.GONE);
         lineSofaN.setVisibility(View.GONE);
         secTables.setVisibility(View.GONE);
         lineTables.setVisibility(View.GONE);
         secTablesN.setVisibility(View.GONE);
         lineTablesN.setVisibility(View.GONE);
         secWatch.setVisibility(View.GONE);
         lineWatch.setVisibility(View.GONE);
         secWatchN.setVisibility(View.GONE);
         lineWatchN.setVisibility(View.GONE);
         secWMachine.setVisibility(View.GONE);
         lineWMachine.setVisibility(View.GONE);
         secWMachineN.setVisibility(View.GONE);
         lineWMachineN.setVisibility(View.GONE);
         secIron.setVisibility(View.GONE);
         lineIron.setVisibility(View.GONE);
         secIronN.setVisibility(View.GONE);
         lineIronN.setVisibility(View.GONE);
         secBooth.setVisibility(View.GONE);
         lineBooth.setVisibility(View.GONE);
         secBoothN.setVisibility(View.GONE);
         lineBoothN.setVisibility(View.GONE);
         secSMachine.setVisibility(View.GONE);
         lineSMachine.setVisibility(View.GONE);
         secSMachineN.setVisibility(View.GONE);
         lineSMachineN.setVisibility(View.GONE);
         secGenerator.setVisibility(View.GONE);
         lineGenerator.setVisibility(View.GONE);
         secGeneratorN.setVisibility(View.GONE);
         lineGeneratorN.setVisibility(View.GONE);
         seclblH12a.setVisibility(View.GONE);
         linelblH12a.setVisibility(View.GONE);
         secInternet.setVisibility(View.GONE);
         lineInternet.setVisibility(View.GONE);
         secInternetN.setVisibility(View.GONE);
         lineInternetN.setVisibility(View.GONE);
         secSatellite.setVisibility(View.GONE);
         lineSatellite.setVisibility(View.GONE);
         secSatelliteN.setVisibility(View.GONE);
         lineSatelliteN.setVisibility(View.GONE);
         secLandline.setVisibility(View.GONE);
         lineLandline.setVisibility(View.GONE);
         secLandlineN.setVisibility(View.GONE);
         lineLandlineN.setVisibility(View.GONE);
         secCellphone.setVisibility(View.GONE);
         lineCellphone.setVisibility(View.GONE);
         secCellphoneN.setVisibility(View.GONE);
         lineCellphoneN.setVisibility(View.GONE);
         secTV.setVisibility(View.GONE);
         lineTV.setVisibility(View.GONE);
         secTVN.setVisibility(View.GONE);
         lineTVN.setVisibility(View.GONE);
         secTV5.setVisibility(View.GONE);
         lineTV5.setVisibility(View.GONE);
         secTV5N.setVisibility(View.GONE);
         lineTV5N.setVisibility(View.GONE);
         secChannel.setVisibility(View.GONE);
         lineChannel.setVisibility(View.GONE);
         secChannelN.setVisibility(View.GONE);
         lineChannelN.setVisibility(View.GONE);
         secRadio.setVisibility(View.GONE);
         lineRadio.setVisibility(View.GONE);
         secRadioN.setVisibility(View.GONE);
         lineRadioN.setVisibility(View.GONE);
         secDVD.setVisibility(View.GONE);
         lineDVD.setVisibility(View.GONE);
         secDVDN.setVisibility(View.GONE);
         lineDVDN.setVisibility(View.GONE);
         secVideo.setVisibility(View.GONE);
         lineVideo.setVisibility(View.GONE);
         secVideoN.setVisibility(View.GONE);
         lineVideoN.setVisibility(View.GONE);
         secComputer.setVisibility(View.GONE);
         lineComputer.setVisibility(View.GONE);
         secComputerN.setVisibility(View.GONE);
         lineComputerN.setVisibility(View.GONE);
         secLaptop.setVisibility(View.GONE);
         lineLaptop.setVisibility(View.GONE);
         secLaptopN.setVisibility(View.GONE);
         lineLaptopN.setVisibility(View.GONE);
         secCable.setVisibility(View.GONE);
         lineCable.setVisibility(View.GONE);
         secCableN.setVisibility(View.GONE);
         lineCableN.setVisibility(View.GONE);
         seclblH12b.setVisibility(View.GONE);
         linelblH12b.setVisibility(View.GONE);
         secMicrowave.setVisibility(View.GONE);
         lineMicrowave.setVisibility(View.GONE);
         secMicrowaveN.setVisibility(View.GONE);
         lineMicrowaveN.setVisibility(View.GONE);
         secGeyser.setVisibility(View.GONE);
         lineGeyser.setVisibility(View.GONE);
         secGeyserN.setVisibility(View.GONE);
         lineGeyserN.setVisibility(View.GONE);
         secGrill.setVisibility(View.GONE);
         lineGrill.setVisibility(View.GONE);
         secGrillN.setVisibility(View.GONE);
         lineGrillN.setVisibility(View.GONE);
         secGrain.setVisibility(View.GONE);
         lineGrain.setVisibility(View.GONE);
         secGrainN.setVisibility(View.GONE);
         lineGrainN.setVisibility(View.GONE);
         secRefrigerator.setVisibility(View.GONE);
         lineRefrigerator.setVisibility(View.GONE);
         secRefrigeratorN.setVisibility(View.GONE);
         lineRefrigeratorN.setVisibility(View.GONE);
         secDeepFreezer.setVisibility(View.GONE);
         lineDeepFreezer.setVisibility(View.GONE);
         secDeepFreezerN.setVisibility(View.GONE);
         lineDeepFreezerN.setVisibility(View.GONE);
         secStove.setVisibility(View.GONE);
         lineStove.setVisibility(View.GONE);
         secStoveN.setVisibility(View.GONE);
         lineStoveN.setVisibility(View.GONE);
         secGasHob.setVisibility(View.GONE);
         lineGasHob.setVisibility(View.GONE);
         secGasHobN.setVisibility(View.GONE);
         lineGasHobN.setVisibility(View.GONE);
         secImpCooker.setVisibility(View.GONE);
         lineImpCooker.setVisibility(View.GONE);
         secImpCookerN.setVisibility(View.GONE);
         lineImpCookerN.setVisibility(View.GONE);
         seclblH12c.setVisibility(View.GONE);
         linelblH12c.setVisibility(View.GONE);
         secBike.setVisibility(View.GONE);
         lineBike.setVisibility(View.GONE);
         secBikeN.setVisibility(View.GONE);
         lineBikeN.setVisibility(View.GONE);
         secMotorcycle.setVisibility(View.GONE);
         lineMotorcycle.setVisibility(View.GONE);
         secMotorcycleN.setVisibility(View.GONE);
         lineMotorcycleN.setVisibility(View.GONE);
         secCar.setVisibility(View.GONE);
         lineCar.setVisibility(View.GONE);
         secCarN.setVisibility(View.GONE);
         lineCarN.setVisibility(View.GONE);
         secRickshaw.setVisibility(View.GONE);
         lineRickshaw.setVisibility(View.GONE);
         secRickshawN.setVisibility(View.GONE);
         lineRickshawN.setVisibility(View.GONE);
         secCart.setVisibility(View.GONE);
         lineCart.setVisibility(View.GONE);
         secCartN.setVisibility(View.GONE);
         lineCartN.setVisibility(View.GONE);
         secCanoe.setVisibility(View.GONE);
         lineCanoe.setVisibility(View.GONE);
         secCanoeN.setVisibility(View.GONE);
         lineCanoeN.setVisibility(View.GONE);
         secBus.setVisibility(View.GONE);
         lineBus.setVisibility(View.GONE);
         secBusN.setVisibility(View.GONE);
         lineBusN.setVisibility(View.GONE);
         seclblH12d.setVisibility(View.GONE);
         linelblH12d.setVisibility(View.GONE);
         secTractor.setVisibility(View.GONE);
         lineTractor.setVisibility(View.GONE);
         secTractorN.setVisibility(View.GONE);
         lineTractorN.setVisibility(View.GONE);
         secPlow.setVisibility(View.GONE);
         linePlow.setVisibility(View.GONE);
         secPlowN.setVisibility(View.GONE);
         linePlowN.setVisibility(View.GONE);
         secDuck.setVisibility(View.GONE);
         lineDuck.setVisibility(View.GONE);
         secDuckN.setVisibility(View.GONE);
         lineDuckN.setVisibility(View.GONE);
         secCow.setVisibility(View.GONE);
         lineCow.setVisibility(View.GONE);
         secCowN.setVisibility(View.GONE);
         lineCowN.setVisibility(View.GONE);
         secSheep.setVisibility(View.GONE);
         lineSheep.setVisibility(View.GONE);
         secSheepN.setVisibility(View.GONE);
         lineSheepN.setVisibility(View.GONE);
         secGoat.setVisibility(View.GONE);
         lineGoat.setVisibility(View.GONE);
         secGoatN.setVisibility(View.GONE);
         lineGoatN.setVisibility(View.GONE);
         secChicken.setVisibility(View.GONE);
         lineChicken.setVisibility(View.GONE);
         secChickenN.setVisibility(View.GONE);
         lineChickenN.setVisibility(View.GONE);
         secDonkey.setVisibility(View.GONE);
         lineDonkey.setVisibility(View.GONE);
         secDunkeyN.setVisibility(View.GONE);
         lineDunkeyN.setVisibility(View.GONE);
         secHorse.setVisibility(View.GONE);
         lineHorse.setVisibility(View.GONE);
         secHorseN.setVisibility(View.GONE);
         lineHorseN.setVisibility(View.GONE);
         secPig.setVisibility(View.GONE);
         linePig.setVisibility(View.GONE);
         secPigN.setVisibility(View.GONE);
         linePigN.setVisibility(View.GONE);
         secBirds.setVisibility(View.GONE);
         lineBirds.setVisibility(View.GONE);
         secBirdsN.setVisibility(View.GONE);
         lineBirdsN.setVisibility(View.GONE);
         secDogs.setVisibility(View.GONE);
         lineDogs.setVisibility(View.GONE);
         secDogsN.setVisibility(View.GONE);
         lineDogsN.setVisibility(View.GONE);
         secCats.setVisibility(View.GONE);
         lineCats.setVisibility(View.GONE);
         secCatsN.setVisibility(View.GONE);
         lineCatsN.setVisibility(View.GONE);
         secFishNet.setVisibility(View.GONE);
         lineFishNet.setVisibility(View.GONE);
         secFishNetN.setVisibility(View.GONE);
         lineFishNetN.setVisibility(View.GONE);
         secOtherAsset.setVisibility(View.GONE);
         lineOtherAsset.setVisibility(View.GONE);
         secOtherAsset1.setVisibility(View.GONE);
         lineOtherAsset1.setVisibility(View.GONE);
         secOtherAsset1N.setVisibility(View.GONE);
         lineOtherAsset1N.setVisibility(View.GONE);
         secOtherAsset2.setVisibility(View.GONE);
         lineOtherAsset2.setVisibility(View.GONE);
         secOtherAsset2N.setVisibility(View.GONE);
         lineOtherAsset2N.setVisibility(View.GONE);
         secOtherAsset3.setVisibility(View.GONE);
         lineOtherAsset3.setVisibility(View.GONE);
         secOtherAsset3N.setVisibility(View.GONE);
         lineOtherAsset3N.setVisibility(View.GONE);
         secSESNote.setVisibility(View.GONE);
         lineSESNote.setVisibility(View.GONE);
         secSESVStatusOth.setVisibility(View.GONE);
         lineSESVStatusOth.setVisibility(View.GONE);
         secRnd.setVisibility(View.GONE);
         lineRnd.setVisibility(View.GONE);
         secWSDrink.setVisibility(View.GONE);
         lineWSDrink.setVisibility(View.GONE);
         secWSDrinkOth.setVisibility(View.GONE);
         lineWSDrinkOth.setVisibility(View.GONE);
         secWaterSource.setVisibility(View.GONE);
         lineWaterSource.setVisibility(View.GONE);
         secFetchWaterM.setVisibility(View.GONE);
         lineFetchWaterM.setVisibility(View.GONE);
         secFetchWaterMDk.setVisibility(View.GONE);
         lineFetchWaterMDk.setVisibility(View.GONE);
         secGetWater.setVisibility(View.GONE);
         lineGetWater.setVisibility(View.GONE);
         secGetWaterOth.setVisibility(View.GONE);
         lineGetWaterOth.setVisibility(View.GONE);
         secMemberID.setVisibility(View.GONE);
         lineMemberID.setVisibility(View.GONE);
         secBringWater.setVisibility(View.GONE);
         lineBringWater.setVisibility(View.GONE);
         secBringWaterDk.setVisibility(View.GONE);
         lineBringWaterDk.setVisibility(View.GONE);
         secSomeone.setVisibility(View.GONE);
         lineSomeone.setVisibility(View.GONE);
         secSecondPers.setVisibility(View.GONE);
         lineSecondPers.setVisibility(View.GONE);
         secSecondPersOth.setVisibility(View.GONE);
         lineSecondPersOth.setVisibility(View.GONE);
         secMemberID2nd.setVisibility(View.GONE);
         lineMemberID2nd.setVisibility(View.GONE);
         secEnoughWater.setVisibility(View.GONE);
         lineEnoughWater.setVisibility(View.GONE);
         secMainWater.setVisibility(View.GONE);
         lineMainWater.setVisibility(View.GONE);
         secMainWaterOth.setVisibility(View.GONE);
         lineMainWaterOth.setVisibility(View.GONE);
         seclbl111.setVisibility(View.GONE);
         linelbl111.setVisibility(View.GONE);
         secSmallTank.setVisibility(View.GONE);
         lineSmallTank.setVisibility(View.GONE);
         secMediunTank.setVisibility(View.GONE);
         lineMediunTank.setVisibility(View.GONE);
         secLargeTank.setVisibility(View.GONE);
         lineLargeTank.setVisibility(View.GONE);
         secStoreDrink.setVisibility(View.GONE);
         lineStoreDrink.setVisibility(View.GONE);
         seclbl113.setVisibility(View.GONE);
         linelbl113.setVisibility(View.GONE);
         secContainOpenCov.setVisibility(View.GONE);
         lineContainOpenCov.setVisibility(View.GONE);
         secContainOpenNotCov.setVisibility(View.GONE);
         lineContainOpenNotCov.setVisibility(View.GONE);
         secContainOpenDK.setVisibility(View.GONE);
         lineContainOpenDK.setVisibility(View.GONE);
         secRecoverWater.setVisibility(View.GONE);
         lineRecoverWater.setVisibility(View.GONE);
         secRecoverWaterOth.setVisibility(View.GONE);
         lineRecoverWaterOth.setVisibility(View.GONE);
         secLessDanger.setVisibility(View.GONE);
         lineLessDanger.setVisibility(View.GONE);
         secMakeSafer.setVisibility(View.GONE);
         lineMakeSafer.setVisibility(View.GONE);
         secMakeSaferOth.setVisibility(View.GONE);
         lineMakeSaferOth.setVisibility(View.GONE);
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
         secContentEmp.setVisibility(View.GONE);
         lineContentEmp.setVisibility(View.GONE);
         secContentEmpOth.setVisibility(View.GONE);
         lineContentEmpOth.setVisibility(View.GONE);
         secBowelMov.setVisibility(View.GONE);
         lineBowelMov.setVisibility(View.GONE);
         secBowelMovOth.setVisibility(View.GONE);
         lineBowelMovOth.setVisibility(View.GONE);
         secLiquidWaste.setVisibility(View.GONE);
         lineLiquidWaste.setVisibility(View.GONE);
         secLiquidWasteOth.setVisibility(View.GONE);
         lineLiquidWasteOth.setVisibility(View.GONE);
         secSolidWasteMethod.setVisibility(View.GONE);
         lineSolidWasteMethod.setVisibility(View.GONE);
         secSolidWasteMethodOth.setVisibility(View.GONE);
         lineSolidWasteMethodOth.setVisibility(View.GONE);
         secHandWash.setVisibility(View.GONE);
         lineHandWash.setVisibility(View.GONE);
         secShowWash.setVisibility(View.GONE);
         lineShowWash.setVisibility(View.GONE);
         secAvailableWat.setVisibility(View.GONE);
         lineAvailableWat.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
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
         secGroundMat.setVisibility(View.GONE);
         lineGroundMat.setVisibility(View.GONE);
         secGroundMatOth.setVisibility(View.GONE);
         lineGroundMatOth.setVisibility(View.GONE);
         secRoof.setVisibility(View.GONE);
         lineRoof.setVisibility(View.GONE);
         secRoofOth.setVisibility(View.GONE);
         lineRoofOth.setVisibility(View.GONE);
         secSmokeInside.setVisibility(View.GONE);
         lineSmokeInside.setVisibility(View.GONE);
         secFreqSmoke.setVisibility(View.GONE);
         lineFreqSmoke.setVisibility(View.GONE);
         secWall.setVisibility(View.GONE);
         lineWall.setVisibility(View.GONE);
         secWallOth.setVisibility(View.GONE);
         lineWallOth.setVisibility(View.GONE);
         secRoomSleep.setVisibility(View.GONE);
         lineRoomSleep.setVisibility(View.GONE);
         secRoomSleepDk.setVisibility(View.GONE);
         lineRoomSleepDk.setVisibility(View.GONE);
         secElecNight.setVisibility(View.GONE);
         lineElecNight.setVisibility(View.GONE);
         secElecNightOth.setVisibility(View.GONE);
         lineElecNightOth.setVisibility(View.GONE);
         secHomesteadAny.setVisibility(View.GONE);
         lineHomesteadAny.setVisibility(View.GONE);
         secOthLand.setVisibility(View.GONE);
         lineOthLand.setVisibility(View.GONE);
         secArea.setVisibility(View.GONE);
         lineArea.setVisibility(View.GONE);
         secIncomeMo.setVisibility(View.GONE);
         lineIncomeMo.setVisibility(View.GONE);
         secExpenses.setVisibility(View.GONE);
         lineExpenses.setVisibility(View.GONE);
         secBankAcc.setVisibility(View.GONE);
         lineBankAcc.setVisibility(View.GONE);
         secSprayInt.setVisibility(View.GONE);
         lineSprayInt.setVisibility(View.GONE);
         secMosqNet.setVisibility(View.GONE);
         lineMosqNet.setVisibility(View.GONE);
         secNetOwned.setVisibility(View.GONE);
         lineNetOwned.setVisibility(View.GONE);
         secMedHome.setVisibility(View.GONE);
         lineMedHome.setVisibility(View.GONE);
         seclbl119.setVisibility(View.GONE);
         linelbl119.setVisibility(View.GONE);
         secMedTypeAM.setVisibility(View.GONE);
         lineMedTypeAM.setVisibility(View.GONE);
         secMedTypeAB.setVisibility(View.GONE);
         lineMedTypeAB.setVisibility(View.GONE);
         secMedTypeDK.setVisibility(View.GONE);
         lineMedTypeDK.setVisibility(View.GONE);
         seclbl1110.setVisibility(View.GONE);
         linelbl1110.setVisibility(View.GONE);
         secAntimalarAL.setVisibility(View.GONE);
         lineAntimalarAL.setVisibility(View.GONE);
         secAntimalarASAQ.setVisibility(View.GONE);
         lineAntimalarASAQ.setVisibility(View.GONE);
         secAntimalarSP.setVisibility(View.GONE);
         lineAntimalarSP.setVisibility(View.GONE);
         secAntimalarOth.setVisibility(View.GONE);
         lineAntimalarOth.setVisibility(View.GONE);
         secAntimalarSpecifyOth.setVisibility(View.GONE);
         lineAntimalarSpecifyOth.setVisibility(View.GONE);
         seclbl1111.setVisibility(View.GONE);
         linelbl1111.setVisibility(View.GONE);
         secGetMedHosp.setVisibility(View.GONE);
         lineGetMedHosp.setVisibility(View.GONE);
         secGetMedCSCom.setVisibility(View.GONE);
         lineGetMedCSCom.setVisibility(View.GONE);
         secGetMedPrvCl.setVisibility(View.GONE);
         lineGetMedPrvCl.setVisibility(View.GONE);
         secGetMedPhar.setVisibility(View.GONE);
         lineGetMedPhar.setVisibility(View.GONE);
         secGetMedPD.setVisibility(View.GONE);
         lineGetMedPD.setVisibility(View.GONE);
         secGetMedCHW.setVisibility(View.GONE);
         lineGetMedCHW.setVisibility(View.GONE);
         secGetMedSS.setVisibility(View.GONE);
         lineGetMedSS.setVisibility(View.GONE);
         secGetMedOth.setVisibility(View.GONE);
         lineGetMedOth.setVisibility(View.GONE);
         secGetMedSpecifyOth.setVisibility(View.GONE);
         lineGetMedSpecifyOth.setVisibility(View.GONE);
         secAComment.setVisibility(View.GONE);
         lineAComment.setVisibility(View.GONE);
         secComment.setVisibility(View.GONE);
         lineComment.setVisibility(View.GONE);
         seclblH12.setVisibility(View.GONE);
         linelblH12.setVisibility(View.GONE);
         seclblH121.setVisibility(View.GONE);
         linelblH121.setVisibility(View.GONE);
         secElectricity.setVisibility(View.GONE);
         lineElectricity.setVisibility(View.GONE);
         secSolarPlate.setVisibility(View.GONE);
         lineSolarPlate.setVisibility(View.GONE);
         secSolarPlateN.setVisibility(View.GONE);
         lineSolarPlateN.setVisibility(View.GONE);
         secHeater.setVisibility(View.GONE);
         lineHeater.setVisibility(View.GONE);
         secHeaterN.setVisibility(View.GONE);
         lineHeaterN.setVisibility(View.GONE);
         secAC.setVisibility(View.GONE);
         lineAC.setVisibility(View.GONE);
         secACN.setVisibility(View.GONE);
         lineACN.setVisibility(View.GONE);
         secElecFan.setVisibility(View.GONE);
         lineElecFan.setVisibility(View.GONE);
         secElecFanN.setVisibility(View.GONE);
         lineElecFanN.setVisibility(View.GONE);
         secLantern.setVisibility(View.GONE);
         lineLantern.setVisibility(View.GONE);
         secLanternN.setVisibility(View.GONE);
         lineLanternN.setVisibility(View.GONE);
         secLamp.setVisibility(View.GONE);
         lineLamp.setVisibility(View.GONE);
         secLampN.setVisibility(View.GONE);
         lineLampN.setVisibility(View.GONE);
         secGasLamp.setVisibility(View.GONE);
         lineGasLamp.setVisibility(View.GONE);
         secGasLampN.setVisibility(View.GONE);
         lineGasLampN.setVisibility(View.GONE);
         secPetroleum.setVisibility(View.GONE);
         linePetroleum.setVisibility(View.GONE);
         secPetroleumN.setVisibility(View.GONE);
         linePetroleumN.setVisibility(View.GONE);
         secMatt.setVisibility(View.GONE);
         lineMatt.setVisibility(View.GONE);
         secMattN.setVisibility(View.GONE);
         lineMattN.setVisibility(View.GONE);
         secMats.setVisibility(View.GONE);
         lineMats.setVisibility(View.GONE);
         secMatsN.setVisibility(View.GONE);
         lineMatsN.setVisibility(View.GONE);
         secCarpets.setVisibility(View.GONE);
         lineCarpets.setVisibility(View.GONE);
         secCarpetsN.setVisibility(View.GONE);
         lineCarpetsN.setVisibility(View.GONE);
         secBed.setVisibility(View.GONE);
         lineBed.setVisibility(View.GONE);
         secBedN.setVisibility(View.GONE);
         lineBedN.setVisibility(View.GONE);
         secChair.setVisibility(View.GONE);
         lineChair.setVisibility(View.GONE);
         secChairN.setVisibility(View.GONE);
         lineChairN.setVisibility(View.GONE);
         secSofa.setVisibility(View.GONE);
         lineSofa.setVisibility(View.GONE);
         secSofaN.setVisibility(View.GONE);
         lineSofaN.setVisibility(View.GONE);
         secTables.setVisibility(View.GONE);
         lineTables.setVisibility(View.GONE);
         secTablesN.setVisibility(View.GONE);
         lineTablesN.setVisibility(View.GONE);
         secWatch.setVisibility(View.GONE);
         lineWatch.setVisibility(View.GONE);
         secWatchN.setVisibility(View.GONE);
         lineWatchN.setVisibility(View.GONE);
         secWMachine.setVisibility(View.GONE);
         lineWMachine.setVisibility(View.GONE);
         secWMachineN.setVisibility(View.GONE);
         lineWMachineN.setVisibility(View.GONE);
         secIron.setVisibility(View.GONE);
         lineIron.setVisibility(View.GONE);
         secIronN.setVisibility(View.GONE);
         lineIronN.setVisibility(View.GONE);
         secBooth.setVisibility(View.GONE);
         lineBooth.setVisibility(View.GONE);
         secBoothN.setVisibility(View.GONE);
         lineBoothN.setVisibility(View.GONE);
         secSMachine.setVisibility(View.GONE);
         lineSMachine.setVisibility(View.GONE);
         secSMachineN.setVisibility(View.GONE);
         lineSMachineN.setVisibility(View.GONE);
         secGenerator.setVisibility(View.GONE);
         lineGenerator.setVisibility(View.GONE);
         secGeneratorN.setVisibility(View.GONE);
         lineGeneratorN.setVisibility(View.GONE);
         seclblH12a.setVisibility(View.GONE);
         linelblH12a.setVisibility(View.GONE);
         secInternet.setVisibility(View.GONE);
         lineInternet.setVisibility(View.GONE);
         secInternetN.setVisibility(View.GONE);
         lineInternetN.setVisibility(View.GONE);
         secSatellite.setVisibility(View.GONE);
         lineSatellite.setVisibility(View.GONE);
         secSatelliteN.setVisibility(View.GONE);
         lineSatelliteN.setVisibility(View.GONE);
         secLandline.setVisibility(View.GONE);
         lineLandline.setVisibility(View.GONE);
         secLandlineN.setVisibility(View.GONE);
         lineLandlineN.setVisibility(View.GONE);
         secCellphone.setVisibility(View.GONE);
         lineCellphone.setVisibility(View.GONE);
         secCellphoneN.setVisibility(View.GONE);
         lineCellphoneN.setVisibility(View.GONE);
         secTV.setVisibility(View.GONE);
         lineTV.setVisibility(View.GONE);
         secTVN.setVisibility(View.GONE);
         lineTVN.setVisibility(View.GONE);
         secTV5.setVisibility(View.GONE);
         lineTV5.setVisibility(View.GONE);
         secTV5N.setVisibility(View.GONE);
         lineTV5N.setVisibility(View.GONE);
         secChannel.setVisibility(View.GONE);
         lineChannel.setVisibility(View.GONE);
         secChannelN.setVisibility(View.GONE);
         lineChannelN.setVisibility(View.GONE);
         secRadio.setVisibility(View.GONE);
         lineRadio.setVisibility(View.GONE);
         secRadioN.setVisibility(View.GONE);
         lineRadioN.setVisibility(View.GONE);
         secDVD.setVisibility(View.GONE);
         lineDVD.setVisibility(View.GONE);
         secDVDN.setVisibility(View.GONE);
         lineDVDN.setVisibility(View.GONE);
         secVideo.setVisibility(View.GONE);
         lineVideo.setVisibility(View.GONE);
         secVideoN.setVisibility(View.GONE);
         lineVideoN.setVisibility(View.GONE);
         secComputer.setVisibility(View.GONE);
         lineComputer.setVisibility(View.GONE);
         secComputerN.setVisibility(View.GONE);
         lineComputerN.setVisibility(View.GONE);
         secLaptop.setVisibility(View.GONE);
         lineLaptop.setVisibility(View.GONE);
         secLaptopN.setVisibility(View.GONE);
         lineLaptopN.setVisibility(View.GONE);
         secCable.setVisibility(View.GONE);
         lineCable.setVisibility(View.GONE);
         secCableN.setVisibility(View.GONE);
         lineCableN.setVisibility(View.GONE);
         seclblH12b.setVisibility(View.GONE);
         linelblH12b.setVisibility(View.GONE);
         secMicrowave.setVisibility(View.GONE);
         lineMicrowave.setVisibility(View.GONE);
         secMicrowaveN.setVisibility(View.GONE);
         lineMicrowaveN.setVisibility(View.GONE);
         secGeyser.setVisibility(View.GONE);
         lineGeyser.setVisibility(View.GONE);
         secGeyserN.setVisibility(View.GONE);
         lineGeyserN.setVisibility(View.GONE);
         secGrill.setVisibility(View.GONE);
         lineGrill.setVisibility(View.GONE);
         secGrillN.setVisibility(View.GONE);
         lineGrillN.setVisibility(View.GONE);
         secGrain.setVisibility(View.GONE);
         lineGrain.setVisibility(View.GONE);
         secGrainN.setVisibility(View.GONE);
         lineGrainN.setVisibility(View.GONE);
         secRefrigerator.setVisibility(View.GONE);
         lineRefrigerator.setVisibility(View.GONE);
         secRefrigeratorN.setVisibility(View.GONE);
         lineRefrigeratorN.setVisibility(View.GONE);
         secDeepFreezer.setVisibility(View.GONE);
         lineDeepFreezer.setVisibility(View.GONE);
         secDeepFreezerN.setVisibility(View.GONE);
         lineDeepFreezerN.setVisibility(View.GONE);
         secStove.setVisibility(View.GONE);
         lineStove.setVisibility(View.GONE);
         secStoveN.setVisibility(View.GONE);
         lineStoveN.setVisibility(View.GONE);
         secGasHob.setVisibility(View.GONE);
         lineGasHob.setVisibility(View.GONE);
         secGasHobN.setVisibility(View.GONE);
         lineGasHobN.setVisibility(View.GONE);
         secImpCooker.setVisibility(View.GONE);
         lineImpCooker.setVisibility(View.GONE);
         secImpCookerN.setVisibility(View.GONE);
         lineImpCookerN.setVisibility(View.GONE);
         seclblH12c.setVisibility(View.GONE);
         linelblH12c.setVisibility(View.GONE);
         secBike.setVisibility(View.GONE);
         lineBike.setVisibility(View.GONE);
         secBikeN.setVisibility(View.GONE);
         lineBikeN.setVisibility(View.GONE);
         secMotorcycle.setVisibility(View.GONE);
         lineMotorcycle.setVisibility(View.GONE);
         secMotorcycleN.setVisibility(View.GONE);
         lineMotorcycleN.setVisibility(View.GONE);
         secCar.setVisibility(View.GONE);
         lineCar.setVisibility(View.GONE);
         secCarN.setVisibility(View.GONE);
         lineCarN.setVisibility(View.GONE);
         secRickshaw.setVisibility(View.GONE);
         lineRickshaw.setVisibility(View.GONE);
         secRickshawN.setVisibility(View.GONE);
         lineRickshawN.setVisibility(View.GONE);
         secCart.setVisibility(View.GONE);
         lineCart.setVisibility(View.GONE);
         secCartN.setVisibility(View.GONE);
         lineCartN.setVisibility(View.GONE);
         secCanoe.setVisibility(View.GONE);
         lineCanoe.setVisibility(View.GONE);
         secCanoeN.setVisibility(View.GONE);
         lineCanoeN.setVisibility(View.GONE);
         secBus.setVisibility(View.GONE);
         lineBus.setVisibility(View.GONE);
         secBusN.setVisibility(View.GONE);
         lineBusN.setVisibility(View.GONE);
         seclblH12d.setVisibility(View.GONE);
         linelblH12d.setVisibility(View.GONE);
         secTractor.setVisibility(View.GONE);
         lineTractor.setVisibility(View.GONE);
         secTractorN.setVisibility(View.GONE);
         lineTractorN.setVisibility(View.GONE);
         secPlow.setVisibility(View.GONE);
         linePlow.setVisibility(View.GONE);
         secPlowN.setVisibility(View.GONE);
         linePlowN.setVisibility(View.GONE);
         secDuck.setVisibility(View.GONE);
         lineDuck.setVisibility(View.GONE);
         secDuckN.setVisibility(View.GONE);
         lineDuckN.setVisibility(View.GONE);
         secCow.setVisibility(View.GONE);
         lineCow.setVisibility(View.GONE);
         secCowN.setVisibility(View.GONE);
         lineCowN.setVisibility(View.GONE);
         secSheep.setVisibility(View.GONE);
         lineSheep.setVisibility(View.GONE);
         secSheepN.setVisibility(View.GONE);
         lineSheepN.setVisibility(View.GONE);
         secGoat.setVisibility(View.GONE);
         lineGoat.setVisibility(View.GONE);
         secGoatN.setVisibility(View.GONE);
         lineGoatN.setVisibility(View.GONE);
         secChicken.setVisibility(View.GONE);
         lineChicken.setVisibility(View.GONE);
         secChickenN.setVisibility(View.GONE);
         lineChickenN.setVisibility(View.GONE);
         secDonkey.setVisibility(View.GONE);
         lineDonkey.setVisibility(View.GONE);
         secDunkeyN.setVisibility(View.GONE);
         lineDunkeyN.setVisibility(View.GONE);
         secHorse.setVisibility(View.GONE);
         lineHorse.setVisibility(View.GONE);
         secHorseN.setVisibility(View.GONE);
         lineHorseN.setVisibility(View.GONE);
         secPig.setVisibility(View.GONE);
         linePig.setVisibility(View.GONE);
         secPigN.setVisibility(View.GONE);
         linePigN.setVisibility(View.GONE);
         secBirds.setVisibility(View.GONE);
         lineBirds.setVisibility(View.GONE);
         secBirdsN.setVisibility(View.GONE);
         lineBirdsN.setVisibility(View.GONE);
         secDogs.setVisibility(View.GONE);
         lineDogs.setVisibility(View.GONE);
         secDogsN.setVisibility(View.GONE);
         lineDogsN.setVisibility(View.GONE);
         secCats.setVisibility(View.GONE);
         lineCats.setVisibility(View.GONE);
         secCatsN.setVisibility(View.GONE);
         lineCatsN.setVisibility(View.GONE);
         secFishNet.setVisibility(View.GONE);
         lineFishNet.setVisibility(View.GONE);
         secFishNetN.setVisibility(View.GONE);
         lineFishNetN.setVisibility(View.GONE);
         secOtherAsset.setVisibility(View.GONE);
         lineOtherAsset.setVisibility(View.GONE);
         secOtherAsset1.setVisibility(View.GONE);
         lineOtherAsset1.setVisibility(View.GONE);
         secOtherAsset1N.setVisibility(View.GONE);
         lineOtherAsset1N.setVisibility(View.GONE);
         secOtherAsset2.setVisibility(View.GONE);
         lineOtherAsset2.setVisibility(View.GONE);
         secOtherAsset2N.setVisibility(View.GONE);
         lineOtherAsset2N.setVisibility(View.GONE);
         secOtherAsset3.setVisibility(View.GONE);
         lineOtherAsset3.setVisibility(View.GONE);
         secOtherAsset3N.setVisibility(View.GONE);
         lineOtherAsset3N.setVisibility(View.GONE);
         secSESNote.setVisibility(View.GONE);
         lineSESNote.setVisibility(View.GONE);
         secWSDrinkOth.setVisibility(View.GONE);
         lineWSDrinkOth.setVisibility(View.GONE);
         secWaterSource.setVisibility(View.GONE);
         lineWaterSource.setVisibility(View.GONE);
         secFetchWaterM.setVisibility(View.GONE);
         lineFetchWaterM.setVisibility(View.GONE);
         secFetchWaterMDk.setVisibility(View.GONE);
         lineFetchWaterMDk.setVisibility(View.GONE);
         secGetWater.setVisibility(View.GONE);
         lineGetWater.setVisibility(View.GONE);
         secGetWaterOth.setVisibility(View.GONE);
         lineGetWaterOth.setVisibility(View.GONE);
         secMemberID.setVisibility(View.GONE);
         lineMemberID.setVisibility(View.GONE);
         secBringWater.setVisibility(View.GONE);
         lineBringWater.setVisibility(View.GONE);
         secBringWaterDk.setVisibility(View.GONE);
         lineBringWaterDk.setVisibility(View.GONE);
         secSomeone.setVisibility(View.GONE);
         lineSomeone.setVisibility(View.GONE);
         secSecondPers.setVisibility(View.GONE);
         lineSecondPers.setVisibility(View.GONE);
         secSecondPersOth.setVisibility(View.GONE);
         lineSecondPersOth.setVisibility(View.GONE);
         secMemberID2nd.setVisibility(View.GONE);
         lineMemberID2nd.setVisibility(View.GONE);
         secEnoughWater.setVisibility(View.GONE);
         lineEnoughWater.setVisibility(View.GONE);
         secMainWater.setVisibility(View.GONE);
         lineMainWater.setVisibility(View.GONE);
         secMainWaterOth.setVisibility(View.GONE);
         lineMainWaterOth.setVisibility(View.GONE);
         seclbl111.setVisibility(View.GONE);
         linelbl111.setVisibility(View.GONE);
         secSmallTank.setVisibility(View.GONE);
         lineSmallTank.setVisibility(View.GONE);
         secMediunTank.setVisibility(View.GONE);
         lineMediunTank.setVisibility(View.GONE);
         secLargeTank.setVisibility(View.GONE);
         lineLargeTank.setVisibility(View.GONE);
         secStoreDrink.setVisibility(View.GONE);
         lineStoreDrink.setVisibility(View.GONE);
         seclbl113.setVisibility(View.GONE);
         linelbl113.setVisibility(View.GONE);
         secContainOpenCov.setVisibility(View.GONE);
         lineContainOpenCov.setVisibility(View.GONE);
         secContainOpenNotCov.setVisibility(View.GONE);
         lineContainOpenNotCov.setVisibility(View.GONE);
         secContainOpenDK.setVisibility(View.GONE);
         lineContainOpenDK.setVisibility(View.GONE);
         secRecoverWater.setVisibility(View.GONE);
         lineRecoverWater.setVisibility(View.GONE);
         secRecoverWaterOth.setVisibility(View.GONE);
         lineRecoverWaterOth.setVisibility(View.GONE);
         secLessDanger.setVisibility(View.GONE);
         lineLessDanger.setVisibility(View.GONE);
         secMakeSafer.setVisibility(View.GONE);
         lineMakeSafer.setVisibility(View.GONE);
         secMakeSaferOth.setVisibility(View.GONE);
         lineMakeSaferOth.setVisibility(View.GONE);
         secGetWaterOth.setVisibility(View.GONE);
         lineGetWaterOth.setVisibility(View.GONE);
         secGetWaterOth.setVisibility(View.GONE);
         lineGetWaterOth.setVisibility(View.GONE);
         secSecondPers.setVisibility(View.GONE);
         lineSecondPers.setVisibility(View.GONE);
         secSecondPersOth.setVisibility(View.GONE);
         lineSecondPersOth.setVisibility(View.GONE);
         secMemberID2nd.setVisibility(View.GONE);
         lineMemberID2nd.setVisibility(View.GONE);
         secEnoughWater.setVisibility(View.GONE);
         lineEnoughWater.setVisibility(View.GONE);
         secMainWater.setVisibility(View.GONE);
         lineMainWater.setVisibility(View.GONE);
         secMainWaterOth.setVisibility(View.GONE);
         lineMainWaterOth.setVisibility(View.GONE);
         seclbl111.setVisibility(View.GONE);
         linelbl111.setVisibility(View.GONE);
         secSmallTank.setVisibility(View.GONE);
         lineSmallTank.setVisibility(View.GONE);
         secMediunTank.setVisibility(View.GONE);
         lineMediunTank.setVisibility(View.GONE);
         secLargeTank.setVisibility(View.GONE);
         lineLargeTank.setVisibility(View.GONE);
         secStoreDrink.setVisibility(View.GONE);
         lineStoreDrink.setVisibility(View.GONE);
         seclbl113.setVisibility(View.GONE);
         linelbl113.setVisibility(View.GONE);
         secContainOpenCov.setVisibility(View.GONE);
         lineContainOpenCov.setVisibility(View.GONE);
         secContainOpenNotCov.setVisibility(View.GONE);
         lineContainOpenNotCov.setVisibility(View.GONE);
         secContainOpenDK.setVisibility(View.GONE);
         lineContainOpenDK.setVisibility(View.GONE);
         secRecoverWater.setVisibility(View.GONE);
         lineRecoverWater.setVisibility(View.GONE);
         secRecoverWaterOth.setVisibility(View.GONE);
         lineRecoverWaterOth.setVisibility(View.GONE);
         secLessDanger.setVisibility(View.GONE);
         lineLessDanger.setVisibility(View.GONE);
         secMakeSafer.setVisibility(View.GONE);
         lineMakeSafer.setVisibility(View.GONE);
         secMakeSaferOth.setVisibility(View.GONE);
         lineMakeSaferOth.setVisibility(View.GONE);
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
         secContentEmp.setVisibility(View.GONE);
         lineContentEmp.setVisibility(View.GONE);
         secContentEmpOth.setVisibility(View.GONE);
         lineContentEmpOth.setVisibility(View.GONE);
         secBowelMov.setVisibility(View.GONE);
         lineBowelMov.setVisibility(View.GONE);
         secBowelMovOth.setVisibility(View.GONE);
         lineBowelMovOth.setVisibility(View.GONE);
         secLiquidWaste.setVisibility(View.GONE);
         lineLiquidWaste.setVisibility(View.GONE);
         secLiquidWasteOth.setVisibility(View.GONE);
         lineLiquidWasteOth.setVisibility(View.GONE);
         secSolidWasteMethod.setVisibility(View.GONE);
         lineSolidWasteMethod.setVisibility(View.GONE);
         secSolidWasteMethodOth.setVisibility(View.GONE);
         lineSolidWasteMethodOth.setVisibility(View.GONE);
         secHandWash.setVisibility(View.GONE);
         lineHandWash.setVisibility(View.GONE);
         secShowWash.setVisibility(View.GONE);
         lineShowWash.setVisibility(View.GONE);
         secAvailableWat.setVisibility(View.GONE);
         lineAvailableWat.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
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
         secGroundMat.setVisibility(View.GONE);
         lineGroundMat.setVisibility(View.GONE);
         secGroundMatOth.setVisibility(View.GONE);
         lineGroundMatOth.setVisibility(View.GONE);
         secRoof.setVisibility(View.GONE);
         lineRoof.setVisibility(View.GONE);
         secRoofOth.setVisibility(View.GONE);
         lineRoofOth.setVisibility(View.GONE);
         secSmokeInside.setVisibility(View.GONE);
         lineSmokeInside.setVisibility(View.GONE);
         secFreqSmoke.setVisibility(View.GONE);
         lineFreqSmoke.setVisibility(View.GONE);
         secWall.setVisibility(View.GONE);
         lineWall.setVisibility(View.GONE);
         secWallOth.setVisibility(View.GONE);
         lineWallOth.setVisibility(View.GONE);
         secRoomSleep.setVisibility(View.GONE);
         lineRoomSleep.setVisibility(View.GONE);
         secRoomSleepDk.setVisibility(View.GONE);
         lineRoomSleepDk.setVisibility(View.GONE);
         secElecNight.setVisibility(View.GONE);
         lineElecNight.setVisibility(View.GONE);
         secElecNightOth.setVisibility(View.GONE);
         lineElecNightOth.setVisibility(View.GONE);
         secHomesteadAny.setVisibility(View.GONE);
         lineHomesteadAny.setVisibility(View.GONE);
         secOthLand.setVisibility(View.GONE);
         lineOthLand.setVisibility(View.GONE);
         secArea.setVisibility(View.GONE);
         lineArea.setVisibility(View.GONE);
         secIncomeMo.setVisibility(View.GONE);
         lineIncomeMo.setVisibility(View.GONE);
         secExpenses.setVisibility(View.GONE);
         lineExpenses.setVisibility(View.GONE);
         secBankAcc.setVisibility(View.GONE);
         lineBankAcc.setVisibility(View.GONE);
         secSprayInt.setVisibility(View.GONE);
         lineSprayInt.setVisibility(View.GONE);
         secMosqNet.setVisibility(View.GONE);
         lineMosqNet.setVisibility(View.GONE);
         secNetOwned.setVisibility(View.GONE);
         lineNetOwned.setVisibility(View.GONE);
         secMedHome.setVisibility(View.GONE);
         lineMedHome.setVisibility(View.GONE);
         secSecondPersOth.setVisibility(View.GONE);
         lineSecondPersOth.setVisibility(View.GONE);
         secSecondPersOth.setVisibility(View.GONE);
         lineSecondPersOth.setVisibility(View.GONE);
         secMainWaterOth.setVisibility(View.GONE);
         lineMainWaterOth.setVisibility(View.GONE);
         seclbl113.setVisibility(View.GONE);
         linelbl113.setVisibility(View.GONE);
         secContainOpenCov.setVisibility(View.GONE);
         lineContainOpenCov.setVisibility(View.GONE);
         secContainOpenNotCov.setVisibility(View.GONE);
         lineContainOpenNotCov.setVisibility(View.GONE);
         secContainOpenDK.setVisibility(View.GONE);
         lineContainOpenDK.setVisibility(View.GONE);
         secRecoverWaterOth.setVisibility(View.GONE);
         lineRecoverWaterOth.setVisibility(View.GONE);
         secRecoverWaterOth.setVisibility(View.GONE);
         lineRecoverWaterOth.setVisibility(View.GONE);
         secRecoverWaterOth.setVisibility(View.GONE);
         lineRecoverWaterOth.setVisibility(View.GONE);
         secMakeSafer.setVisibility(View.GONE);
         lineMakeSafer.setVisibility(View.GONE);
         secMakeSaferOth.setVisibility(View.GONE);
         lineMakeSaferOth.setVisibility(View.GONE);
         secMakeSafer.setVisibility(View.GONE);
         lineMakeSafer.setVisibility(View.GONE);
         secMakeSaferOth.setVisibility(View.GONE);
         lineMakeSaferOth.setVisibility(View.GONE);
         secMakeSaferOth.setVisibility(View.GONE);
         lineMakeSaferOth.setVisibility(View.GONE);
         secToiletOth.setVisibility(View.GONE);
         lineToiletOth.setVisibility(View.GONE);
         secToiletUseNo.setVisibility(View.GONE);
         lineToiletUseNo.setVisibility(View.GONE);
         secToiletUseNoDk.setVisibility(View.GONE);
         lineToiletUseNoDk.setVisibility(View.GONE);
         secContentEmpOth.setVisibility(View.GONE);
         lineContentEmpOth.setVisibility(View.GONE);
         secSolidWasteMethodOth.setVisibility(View.GONE);
         lineSolidWasteMethodOth.setVisibility(View.GONE);
         secLiquidWasteOth.setVisibility(View.GONE);
         lineLiquidWasteOth.setVisibility(View.GONE);
         secLiquidWasteOth.setVisibility(View.GONE);
         lineLiquidWasteOth.setVisibility(View.GONE);
         secLiquidWasteOth.setVisibility(View.GONE);
         lineLiquidWasteOth.setVisibility(View.GONE);
         secLiquidWasteOth.setVisibility(View.GONE);
         lineLiquidWasteOth.setVisibility(View.GONE);
         secLiquidWasteOth.setVisibility(View.GONE);
         lineLiquidWasteOth.setVisibility(View.GONE);
         secShowWash.setVisibility(View.GONE);
         lineShowWash.setVisibility(View.GONE);
         secAvailableWat.setVisibility(View.GONE);
         lineAvailableWat.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
         secShowWash.setVisibility(View.GONE);
         lineShowWash.setVisibility(View.GONE);
         secAvailableWat.setVisibility(View.GONE);
         lineAvailableWat.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
         secCookDvcOth.setVisibility(View.GONE);
         lineCookDvcOth.setVisibility(View.GONE);
         secCookFuelOth.setVisibility(View.GONE);
         lineCookFuelOth.setVisibility(View.GONE);
         secCookPlcOth.setVisibility(View.GONE);
         lineCookPlcOth.setVisibility(View.GONE);
         secFloorOth.setVisibility(View.GONE);
         lineFloorOth.setVisibility(View.GONE);
         secGroundMatOth.setVisibility(View.GONE);
         lineGroundMatOth.setVisibility(View.GONE);
         secRoofOth.setVisibility(View.GONE);
         lineRoofOth.setVisibility(View.GONE);
         secFreqSmoke.setVisibility(View.GONE);
         lineFreqSmoke.setVisibility(View.GONE);
         secFreqSmoke.setVisibility(View.GONE);
         lineFreqSmoke.setVisibility(View.GONE);
         secWallOth.setVisibility(View.GONE);
         lineWallOth.setVisibility(View.GONE);
         secElecNightOth.setVisibility(View.GONE);
         lineElecNightOth.setVisibility(View.GONE);
         secNetOwned.setVisibility(View.GONE);
         lineNetOwned.setVisibility(View.GONE);
         secNetOwned.setVisibility(View.GONE);
         lineNetOwned.setVisibility(View.GONE);
         seclbl119.setVisibility(View.GONE);
         linelbl119.setVisibility(View.GONE);
         secMedTypeAM.setVisibility(View.GONE);
         lineMedTypeAM.setVisibility(View.GONE);
         secMedTypeAB.setVisibility(View.GONE);
         lineMedTypeAB.setVisibility(View.GONE);
         secMedTypeDK.setVisibility(View.GONE);
         lineMedTypeDK.setVisibility(View.GONE);
         seclbl1110.setVisibility(View.GONE);
         linelbl1110.setVisibility(View.GONE);
         secAntimalarAL.setVisibility(View.GONE);
         lineAntimalarAL.setVisibility(View.GONE);
         secAntimalarASAQ.setVisibility(View.GONE);
         lineAntimalarASAQ.setVisibility(View.GONE);
         secAntimalarSP.setVisibility(View.GONE);
         lineAntimalarSP.setVisibility(View.GONE);
         secAntimalarOth.setVisibility(View.GONE);
         lineAntimalarOth.setVisibility(View.GONE);
         secAntimalarSpecifyOth.setVisibility(View.GONE);
         lineAntimalarSpecifyOth.setVisibility(View.GONE);
         seclbl1111.setVisibility(View.GONE);
         linelbl1111.setVisibility(View.GONE);
         secGetMedHosp.setVisibility(View.GONE);
         lineGetMedHosp.setVisibility(View.GONE);
         secGetMedCSCom.setVisibility(View.GONE);
         lineGetMedCSCom.setVisibility(View.GONE);
         secGetMedPrvCl.setVisibility(View.GONE);
         lineGetMedPrvCl.setVisibility(View.GONE);
         secGetMedPhar.setVisibility(View.GONE);
         lineGetMedPhar.setVisibility(View.GONE);
         secGetMedPD.setVisibility(View.GONE);
         lineGetMedPD.setVisibility(View.GONE);
         secGetMedCHW.setVisibility(View.GONE);
         lineGetMedCHW.setVisibility(View.GONE);
         secGetMedSS.setVisibility(View.GONE);
         lineGetMedSS.setVisibility(View.GONE);
         secGetMedOth.setVisibility(View.GONE);
         lineGetMedOth.setVisibility(View.GONE);
         secGetMedSpecifyOth.setVisibility(View.GONE);
         lineGetMedSpecifyOth.setVisibility(View.GONE);
         seclbl119.setVisibility(View.GONE);
         linelbl119.setVisibility(View.GONE);
         secMedTypeAM.setVisibility(View.GONE);
         lineMedTypeAM.setVisibility(View.GONE);
         secMedTypeAB.setVisibility(View.GONE);
         lineMedTypeAB.setVisibility(View.GONE);
         secMedTypeDK.setVisibility(View.GONE);
         lineMedTypeDK.setVisibility(View.GONE);
         seclbl1110.setVisibility(View.GONE);
         linelbl1110.setVisibility(View.GONE);
         secAntimalarAL.setVisibility(View.GONE);
         lineAntimalarAL.setVisibility(View.GONE);
         secAntimalarASAQ.setVisibility(View.GONE);
         lineAntimalarASAQ.setVisibility(View.GONE);
         secAntimalarSP.setVisibility(View.GONE);
         lineAntimalarSP.setVisibility(View.GONE);
         secAntimalarOth.setVisibility(View.GONE);
         lineAntimalarOth.setVisibility(View.GONE);
         secAntimalarSpecifyOth.setVisibility(View.GONE);
         lineAntimalarSpecifyOth.setVisibility(View.GONE);
         seclbl1111.setVisibility(View.GONE);
         linelbl1111.setVisibility(View.GONE);
         secGetMedHosp.setVisibility(View.GONE);
         lineGetMedHosp.setVisibility(View.GONE);
         secGetMedCSCom.setVisibility(View.GONE);
         lineGetMedCSCom.setVisibility(View.GONE);
         secGetMedPrvCl.setVisibility(View.GONE);
         lineGetMedPrvCl.setVisibility(View.GONE);
         secGetMedPhar.setVisibility(View.GONE);
         lineGetMedPhar.setVisibility(View.GONE);
         secGetMedPD.setVisibility(View.GONE);
         lineGetMedPD.setVisibility(View.GONE);
         secGetMedCHW.setVisibility(View.GONE);
         lineGetMedCHW.setVisibility(View.GONE);
         secGetMedSS.setVisibility(View.GONE);
         lineGetMedSS.setVisibility(View.GONE);
         secGetMedOth.setVisibility(View.GONE);
         lineGetMedOth.setVisibility(View.GONE);
         secGetMedSpecifyOth.setVisibility(View.GONE);
         lineGetMedSpecifyOth.setVisibility(View.GONE);
         secAntimalarSpecifyOth.setVisibility(View.GONE);
         lineAntimalarSpecifyOth.setVisibility(View.GONE);
         secGetMedSpecifyOth.setVisibility(View.GONE);
         lineGetMedSpecifyOth.setVisibility(View.GONE);
         secComment.setVisibility(View.GONE);
         lineComment.setVisibility(View.GONE);
         secSolarPlateN.setVisibility(View.GONE);
         lineSolarPlateN.setVisibility(View.GONE);
         secHeaterN.setVisibility(View.GONE);
         lineHeaterN.setVisibility(View.GONE);
         secACN.setVisibility(View.GONE);
         lineACN.setVisibility(View.GONE);
         secElecFanN.setVisibility(View.GONE);
         lineElecFanN.setVisibility(View.GONE);
         secLanternN.setVisibility(View.GONE);
         lineLanternN.setVisibility(View.GONE);
         secLampN.setVisibility(View.GONE);
         lineLampN.setVisibility(View.GONE);
         secGasLampN.setVisibility(View.GONE);
         lineGasLampN.setVisibility(View.GONE);
         secPetroleumN.setVisibility(View.GONE);
         linePetroleumN.setVisibility(View.GONE);
         secMattN.setVisibility(View.GONE);
         lineMattN.setVisibility(View.GONE);
         secMatsN.setVisibility(View.GONE);
         lineMatsN.setVisibility(View.GONE);
         secCarpetsN.setVisibility(View.GONE);
         lineCarpetsN.setVisibility(View.GONE);
         secBedN.setVisibility(View.GONE);
         lineBedN.setVisibility(View.GONE);
         secChairN.setVisibility(View.GONE);
         lineChairN.setVisibility(View.GONE);
         secSofaN.setVisibility(View.GONE);
         lineSofaN.setVisibility(View.GONE);
         secTablesN.setVisibility(View.GONE);
         lineTablesN.setVisibility(View.GONE);
         secWatchN.setVisibility(View.GONE);
         lineWatchN.setVisibility(View.GONE);
         secWMachineN.setVisibility(View.GONE);
         lineWMachineN.setVisibility(View.GONE);
         secIronN.setVisibility(View.GONE);
         lineIronN.setVisibility(View.GONE);
         secBoothN.setVisibility(View.GONE);
         lineBoothN.setVisibility(View.GONE);
         secSMachineN.setVisibility(View.GONE);
         lineSMachineN.setVisibility(View.GONE);
         secGeneratorN.setVisibility(View.GONE);
         lineGeneratorN.setVisibility(View.GONE);
         secInternetN.setVisibility(View.GONE);
         lineInternetN.setVisibility(View.GONE);
         secSatelliteN.setVisibility(View.GONE);
         lineSatelliteN.setVisibility(View.GONE);
         secLandlineN.setVisibility(View.GONE);
         lineLandlineN.setVisibility(View.GONE);
         secTV.setVisibility(View.GONE);
         lineTV.setVisibility(View.GONE);
         secTVN.setVisibility(View.GONE);
         lineTVN.setVisibility(View.GONE);
         secTV5N.setVisibility(View.GONE);
         lineTV5N.setVisibility(View.GONE);
         secChannelN.setVisibility(View.GONE);
         lineChannelN.setVisibility(View.GONE);
         secRadioN.setVisibility(View.GONE);
         lineRadioN.setVisibility(View.GONE);
         secDVDN.setVisibility(View.GONE);
         lineDVDN.setVisibility(View.GONE);
         secVideoN.setVisibility(View.GONE);
         lineVideoN.setVisibility(View.GONE);
         secComputerN.setVisibility(View.GONE);
         lineComputerN.setVisibility(View.GONE);
         secLaptopN.setVisibility(View.GONE);
         lineLaptopN.setVisibility(View.GONE);
         secCableN.setVisibility(View.GONE);
         lineCableN.setVisibility(View.GONE);
         secMicrowaveN.setVisibility(View.GONE);
         lineMicrowaveN.setVisibility(View.GONE);
         secGeyserN.setVisibility(View.GONE);
         lineGeyserN.setVisibility(View.GONE);
         secGrillN.setVisibility(View.GONE);
         lineGrillN.setVisibility(View.GONE);
         secGrainN.setVisibility(View.GONE);
         lineGrainN.setVisibility(View.GONE);
         secRefrigeratorN.setVisibility(View.GONE);
         lineRefrigeratorN.setVisibility(View.GONE);
         secDeepFreezerN.setVisibility(View.GONE);
         lineDeepFreezerN.setVisibility(View.GONE);
         secStoveN.setVisibility(View.GONE);
         lineStoveN.setVisibility(View.GONE);
         secGasHobN.setVisibility(View.GONE);
         lineGasHobN.setVisibility(View.GONE);
         secImpCookerN.setVisibility(View.GONE);
         lineImpCookerN.setVisibility(View.GONE);
         secBikeN.setVisibility(View.GONE);
         lineBikeN.setVisibility(View.GONE);
         secMotorcycleN.setVisibility(View.GONE);
         lineMotorcycleN.setVisibility(View.GONE);
         secCarN.setVisibility(View.GONE);
         lineCarN.setVisibility(View.GONE);
         secRickshawN.setVisibility(View.GONE);
         lineRickshawN.setVisibility(View.GONE);
         secCartN.setVisibility(View.GONE);
         lineCartN.setVisibility(View.GONE);
         secCanoeN.setVisibility(View.GONE);
         lineCanoeN.setVisibility(View.GONE);
         secBusN.setVisibility(View.GONE);
         lineBusN.setVisibility(View.GONE);
         secTractorN.setVisibility(View.GONE);
         lineTractorN.setVisibility(View.GONE);
         secPlowN.setVisibility(View.GONE);
         linePlowN.setVisibility(View.GONE);
         secDuckN.setVisibility(View.GONE);
         lineDuckN.setVisibility(View.GONE);
         secCowN.setVisibility(View.GONE);
         lineCowN.setVisibility(View.GONE);
         secSheepN.setVisibility(View.GONE);
         lineSheepN.setVisibility(View.GONE);
         secGoatN.setVisibility(View.GONE);
         lineGoatN.setVisibility(View.GONE);
         secChickenN.setVisibility(View.GONE);
         lineChickenN.setVisibility(View.GONE);
         secDunkeyN.setVisibility(View.GONE);
         lineDunkeyN.setVisibility(View.GONE);
         secHorseN.setVisibility(View.GONE);
         lineHorseN.setVisibility(View.GONE);
         secPigN.setVisibility(View.GONE);
         linePigN.setVisibility(View.GONE);
         secBirdsN.setVisibility(View.GONE);
         lineBirdsN.setVisibility(View.GONE);
         secDogsN.setVisibility(View.GONE);
         lineDogsN.setVisibility(View.GONE);
         secCatsN.setVisibility(View.GONE);
         lineCatsN.setVisibility(View.GONE);
         secFishNetN.setVisibility(View.GONE);
         lineFishNetN.setVisibility(View.GONE);
         secOtherAsset1.setVisibility(View.GONE);
         lineOtherAsset1.setVisibility(View.GONE);
         secOtherAsset1N.setVisibility(View.GONE);
         lineOtherAsset1N.setVisibility(View.GONE);
         secOtherAsset2.setVisibility(View.GONE);
         lineOtherAsset2.setVisibility(View.GONE);
         secOtherAsset2N.setVisibility(View.GONE);
         lineOtherAsset2N.setVisibility(View.GONE);
         secOtherAsset3.setVisibility(View.GONE);
         lineOtherAsset3.setVisibility(View.GONE);
         secOtherAsset3N.setVisibility(View.GONE);
         lineOtherAsset3N.setVisibility(View.GONE);


        DataSearch(HHID,SESNO);


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});

        Global.RoleManagement(cmdSave, MySharedPreferences.getValue(this, "userrole"));
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_SES_Mali.this, e.getMessage());
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

        if(SESNO.length()==0) txtSESNo.setText(SESNo(HHID));
        else txtSESNo.setText(SESNO);
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
             String[] d_rdogrpSESVStatus = new String[] {"1","2","3","9"};
             for (int i = 0; i < rdogrpSESVStatus.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpSESVStatus.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpSESVStatus[i];
             }

             if(rbData.equalsIgnoreCase("3"))
             {
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
                    secWaterSource.setVisibility(View.GONE);
                    lineWaterSource.setVisibility(View.GONE);
                    rdogrpWaterSource.clearCheck();
                    secFetchWaterM.setVisibility(View.GONE);
                    lineFetchWaterM.setVisibility(View.GONE);
                    txtFetchWaterM.setText("");
                    secFetchWaterMDk.setVisibility(View.GONE);
                    lineFetchWaterMDk.setVisibility(View.GONE);
                    rdogrpFetchWaterMDk.clearCheck();
                    secGetWater.setVisibility(View.GONE);
                    lineGetWater.setVisibility(View.GONE);
                    rdogrpGetWater.clearCheck();
                    secGetWaterOth.setVisibility(View.GONE);
                    lineGetWaterOth.setVisibility(View.GONE);
                    txtGetWaterOth.setText("");
                    secMemberID.setVisibility(View.GONE);
                    lineMemberID.setVisibility(View.GONE);
                    spnMemberID.setSelection(0);
                    secBringWater.setVisibility(View.GONE);
                    lineBringWater.setVisibility(View.GONE);
                    txtBringWater.setText("");
                    secBringWaterDk.setVisibility(View.GONE);
                    lineBringWaterDk.setVisibility(View.GONE);
                    rdogrpBringWaterDk.clearCheck();
                    secSomeone.setVisibility(View.GONE);
                    lineSomeone.setVisibility(View.GONE);
                    rdogrpSomeone.clearCheck();
                    secSecondPers.setVisibility(View.GONE);
                    lineSecondPers.setVisibility(View.GONE);
                    rdogrpSecondPers.clearCheck();
                    secSecondPersOth.setVisibility(View.GONE);
                    lineSecondPersOth.setVisibility(View.GONE);
                    txtSecondPersOth.setText("");
                    secMemberID2nd.setVisibility(View.GONE);
                    lineMemberID2nd.setVisibility(View.GONE);
                    spnMemberID2nd.setSelection(0);
                    secEnoughWater.setVisibility(View.GONE);
                    lineEnoughWater.setVisibility(View.GONE);
                    rdogrpEnoughWater.clearCheck();
                    secMainWater.setVisibility(View.GONE);
                    lineMainWater.setVisibility(View.GONE);
                    spnMainWater.setSelection(0);
                    secMainWaterOth.setVisibility(View.GONE);
                    lineMainWaterOth.setVisibility(View.GONE);
                    txtMainWaterOth.setText("");
                    seclbl111.setVisibility(View.GONE);
                    linelbl111.setVisibility(View.GONE);
                    secSmallTank.setVisibility(View.GONE);
                    lineSmallTank.setVisibility(View.GONE);
                    rdogrpSmallTank.clearCheck();
                    secMediunTank.setVisibility(View.GONE);
                    lineMediunTank.setVisibility(View.GONE);
                    rdogrpMediunTank.clearCheck();
                    secLargeTank.setVisibility(View.GONE);
                    lineLargeTank.setVisibility(View.GONE);
                    rdogrpLargeTank.clearCheck();
                    secStoreDrink.setVisibility(View.GONE);
                    lineStoreDrink.setVisibility(View.GONE);
                    rdogrpStoreDrink.clearCheck();
                    seclbl113.setVisibility(View.GONE);
                    linelbl113.setVisibility(View.GONE);
                    secContainOpenCov.setVisibility(View.GONE);
                    lineContainOpenCov.setVisibility(View.GONE);
                    chkContainOpenCov.setChecked(false);
                    secContainOpenNotCov.setVisibility(View.GONE);
                    lineContainOpenNotCov.setVisibility(View.GONE);
                    chkContainOpenNotCov.setChecked(false);
                    secContainOpenDK.setVisibility(View.GONE);
                    lineContainOpenDK.setVisibility(View.GONE);
                    chkContainOpenDK.setChecked(false);
                    secRecoverWater.setVisibility(View.GONE);
                    lineRecoverWater.setVisibility(View.GONE);
                    rdogrpRecoverWater.clearCheck();
                    secRecoverWaterOth.setVisibility(View.GONE);
                    lineRecoverWaterOth.setVisibility(View.GONE);
                    txtRecoverWaterOth.setText("");
                    secLessDanger.setVisibility(View.GONE);
                    lineLessDanger.setVisibility(View.GONE);
                    rdogrpLessDanger.clearCheck();
                    secMakeSafer.setVisibility(View.GONE);
                    lineMakeSafer.setVisibility(View.GONE);
                    spnMakeSafer.setSelection(0);
                    secMakeSaferOth.setVisibility(View.GONE);
                    lineMakeSaferOth.setVisibility(View.GONE);
                    txtMakeSaferOth.setText("");
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
                    secContentEmp.setVisibility(View.GONE);
                    lineContentEmp.setVisibility(View.GONE);
                    rdogrpContentEmp.clearCheck();
                    secContentEmpOth.setVisibility(View.GONE);
                    lineContentEmpOth.setVisibility(View.GONE);
                    txtContentEmpOth.setText("");
                    secBowelMov.setVisibility(View.GONE);
                    lineBowelMov.setVisibility(View.GONE);
                    spnBowelMov.setSelection(0);
                    secBowelMovOth.setVisibility(View.GONE);
                    lineBowelMovOth.setVisibility(View.GONE);
                    txtBowelMovOth.setText("");
                    secLiquidWaste.setVisibility(View.GONE);
                    lineLiquidWaste.setVisibility(View.GONE);
                    rdogrpLiquidWaste.clearCheck();
                    secLiquidWasteOth.setVisibility(View.GONE);
                    lineLiquidWasteOth.setVisibility(View.GONE);
                    txtLiquidWasteOth.setText("");
                    secSolidWasteMethod.setVisibility(View.GONE);
                    lineSolidWasteMethod.setVisibility(View.GONE);
                    spnSolidWasteMethod.setSelection(0);
                    secSolidWasteMethodOth.setVisibility(View.GONE);
                    lineSolidWasteMethodOth.setVisibility(View.GONE);
                    txtSolidWasteMethodOth.setText("");
                    secHandWash.setVisibility(View.GONE);
                    lineHandWash.setVisibility(View.GONE);
                    rdogrpHandWash.clearCheck();
                    secShowWash.setVisibility(View.GONE);
                    lineShowWash.setVisibility(View.GONE);
                    rdogrpShowWash.clearCheck();
                    secAvailableWat.setVisibility(View.GONE);
                    lineAvailableWat.setVisibility(View.GONE);
                    rdogrpAvailableWat.clearCheck();
                    secAvailableSoap.setVisibility(View.GONE);
                    lineAvailableSoap.setVisibility(View.GONE);
                    rdogrpAvailableSoap.clearCheck();
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
                    secGroundMat.setVisibility(View.GONE);
                    lineGroundMat.setVisibility(View.GONE);
                    spnGroundMat.setSelection(0);
                    secGroundMatOth.setVisibility(View.GONE);
                    lineGroundMatOth.setVisibility(View.GONE);
                    txtGroundMatOth.setText("");
                    secRoof.setVisibility(View.GONE);
                    lineRoof.setVisibility(View.GONE);
                    spnRoof.setSelection(0);
                    secRoofOth.setVisibility(View.GONE);
                    lineRoofOth.setVisibility(View.GONE);
                    txtRoofOth.setText("");
                    secSmokeInside.setVisibility(View.GONE);
                    lineSmokeInside.setVisibility(View.GONE);
                    rdogrpSmokeInside.clearCheck();
                    secFreqSmoke.setVisibility(View.GONE);
                    lineFreqSmoke.setVisibility(View.GONE);
                    rdogrpFreqSmoke.clearCheck();
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
                    secElecNight.setVisibility(View.GONE);
                    lineElecNight.setVisibility(View.GONE);
                    spnElecNight.setSelection(0);
                    secElecNightOth.setVisibility(View.GONE);
                    lineElecNightOth.setVisibility(View.GONE);
                    txtElecNightOth.setText("");
                    secHomesteadAny.setVisibility(View.GONE);
                    lineHomesteadAny.setVisibility(View.GONE);
                    rdogrpHomesteadAny.clearCheck();
                    secOthLand.setVisibility(View.GONE);
                    lineOthLand.setVisibility(View.GONE);
                    rdogrpOthLand.clearCheck();
                    secArea.setVisibility(View.GONE);
                    lineArea.setVisibility(View.GONE);
                    txtArea.setText("");
                    secIncomeMo.setVisibility(View.GONE);
                    lineIncomeMo.setVisibility(View.GONE);
                    rdogrpIncomeMo.clearCheck();
                    secExpenses.setVisibility(View.GONE);
                    lineExpenses.setVisibility(View.GONE);
                    rdogrpExpenses.clearCheck();
                    secBankAcc.setVisibility(View.GONE);
                    lineBankAcc.setVisibility(View.GONE);
                    rdogrpBankAcc.clearCheck();
                    secSprayInt.setVisibility(View.GONE);
                    lineSprayInt.setVisibility(View.GONE);
                    rdogrpSprayInt.clearCheck();
                    secMosqNet.setVisibility(View.GONE);
                    lineMosqNet.setVisibility(View.GONE);
                    rdogrpMosqNet.clearCheck();
                    secNetOwned.setVisibility(View.GONE);
                    lineNetOwned.setVisibility(View.GONE);
                    txtNetOwned.setText("");
                    secMedHome.setVisibility(View.GONE);
                    lineMedHome.setVisibility(View.GONE);
                    rdogrpMedHome.clearCheck();
                    seclbl119.setVisibility(View.GONE);
                    linelbl119.setVisibility(View.GONE);
                    secMedTypeAM.setVisibility(View.GONE);
                    lineMedTypeAM.setVisibility(View.GONE);
                    chkMedTypeAM.setChecked(false);
                    secMedTypeAB.setVisibility(View.GONE);
                    lineMedTypeAB.setVisibility(View.GONE);
                    chkMedTypeAB.setChecked(false);
                    secMedTypeDK.setVisibility(View.GONE);
                    lineMedTypeDK.setVisibility(View.GONE);
                    chkMedTypeDK.setChecked(false);
                    seclbl1110.setVisibility(View.GONE);
                    linelbl1110.setVisibility(View.GONE);
                    secAntimalarAL.setVisibility(View.GONE);
                    lineAntimalarAL.setVisibility(View.GONE);
                    chkAntimalarAL.setChecked(false);
                    secAntimalarASAQ.setVisibility(View.GONE);
                    lineAntimalarASAQ.setVisibility(View.GONE);
                    chkAntimalarASAQ.setChecked(false);
                    secAntimalarSP.setVisibility(View.GONE);
                    lineAntimalarSP.setVisibility(View.GONE);
                    chkAntimalarSP.setChecked(false);
                    secAntimalarOth.setVisibility(View.GONE);
                    lineAntimalarOth.setVisibility(View.GONE);
                    chkAntimalarOth.setChecked(false);
                    secAntimalarSpecifyOth.setVisibility(View.GONE);
                    lineAntimalarSpecifyOth.setVisibility(View.GONE);
                    txtAntimalarSpecifyOth.setText("");
                    seclbl1111.setVisibility(View.GONE);
                    linelbl1111.setVisibility(View.GONE);
                    secGetMedHosp.setVisibility(View.GONE);
                    lineGetMedHosp.setVisibility(View.GONE);
                    chkGetMedHosp.setChecked(false);
                    secGetMedCSCom.setVisibility(View.GONE);
                    lineGetMedCSCom.setVisibility(View.GONE);
                    chkGetMedCSCom.setChecked(false);
                    secGetMedPrvCl.setVisibility(View.GONE);
                    lineGetMedPrvCl.setVisibility(View.GONE);
                    chkGetMedPrvCl.setChecked(false);
                    secGetMedPhar.setVisibility(View.GONE);
                    lineGetMedPhar.setVisibility(View.GONE);
                    chkGetMedPhar.setChecked(false);
                    secGetMedPD.setVisibility(View.GONE);
                    lineGetMedPD.setVisibility(View.GONE);
                    chkGetMedPD.setChecked(false);
                    secGetMedCHW.setVisibility(View.GONE);
                    lineGetMedCHW.setVisibility(View.GONE);
                    chkGetMedCHW.setChecked(false);
                    secGetMedSS.setVisibility(View.GONE);
                    lineGetMedSS.setVisibility(View.GONE);
                    chkGetMedSS.setChecked(false);
                    secGetMedOth.setVisibility(View.GONE);
                    lineGetMedOth.setVisibility(View.GONE);
                    chkGetMedOth.setChecked(false);
                    secGetMedSpecifyOth.setVisibility(View.GONE);
                    lineGetMedSpecifyOth.setVisibility(View.GONE);
                    txtGetMedSpecifyOth.setText("");
                    secAComment.setVisibility(View.GONE);
                    lineAComment.setVisibility(View.GONE);
                    rdogrpAComment.clearCheck();
                    secComment.setVisibility(View.GONE);
                    lineComment.setVisibility(View.GONE);
                    txtComment.setText("");
                    seclblH12.setVisibility(View.GONE);
                    linelblH12.setVisibility(View.GONE);
                    seclblH121.setVisibility(View.GONE);
                    linelblH121.setVisibility(View.GONE);
                    secElectricity.setVisibility(View.GONE);
                    lineElectricity.setVisibility(View.GONE);
                    rdogrpElectricity.clearCheck();
                    secSolarPlate.setVisibility(View.GONE);
                    lineSolarPlate.setVisibility(View.GONE);
                    rdogrpSolarPlate.clearCheck();
                    secSolarPlateN.setVisibility(View.GONE);
                    lineSolarPlateN.setVisibility(View.GONE);
                    txtSolarPlateN.setText("");
                    secHeater.setVisibility(View.GONE);
                    lineHeater.setVisibility(View.GONE);
                    rdogrpHeater.clearCheck();
                    secHeaterN.setVisibility(View.GONE);
                    lineHeaterN.setVisibility(View.GONE);
                    txtHeaterN.setText("");
                    secAC.setVisibility(View.GONE);
                    lineAC.setVisibility(View.GONE);
                    rdogrpAC.clearCheck();
                    secACN.setVisibility(View.GONE);
                    lineACN.setVisibility(View.GONE);
                    txtACN.setText("");
                    secElecFan.setVisibility(View.GONE);
                    lineElecFan.setVisibility(View.GONE);
                    rdogrpElecFan.clearCheck();
                    secElecFanN.setVisibility(View.GONE);
                    lineElecFanN.setVisibility(View.GONE);
                    txtElecFanN.setText("");
                    secLantern.setVisibility(View.GONE);
                    lineLantern.setVisibility(View.GONE);
                    rdogrpLantern.clearCheck();
                    secLanternN.setVisibility(View.GONE);
                    lineLanternN.setVisibility(View.GONE);
                    txtLanternN.setText("");
                    secLamp.setVisibility(View.GONE);
                    lineLamp.setVisibility(View.GONE);
                    rdogrpLamp.clearCheck();
                    secLampN.setVisibility(View.GONE);
                    lineLampN.setVisibility(View.GONE);
                    txtLampN.setText("");
                    secGasLamp.setVisibility(View.GONE);
                    lineGasLamp.setVisibility(View.GONE);
                    rdogrpGasLamp.clearCheck();
                    secGasLampN.setVisibility(View.GONE);
                    lineGasLampN.setVisibility(View.GONE);
                    txtGasLampN.setText("");
                    secPetroleum.setVisibility(View.GONE);
                    linePetroleum.setVisibility(View.GONE);
                    rdogrpPetroleum.clearCheck();
                    secPetroleumN.setVisibility(View.GONE);
                    linePetroleumN.setVisibility(View.GONE);
                    txtPetroleumN.setText("");
                    secMatt.setVisibility(View.GONE);
                    lineMatt.setVisibility(View.GONE);
                    rdogrpMatt.clearCheck();
                    secMattN.setVisibility(View.GONE);
                    lineMattN.setVisibility(View.GONE);
                    txtMattN.setText("");
                    secMats.setVisibility(View.GONE);
                    lineMats.setVisibility(View.GONE);
                    rdogrpMats.clearCheck();
                    secMatsN.setVisibility(View.GONE);
                    lineMatsN.setVisibility(View.GONE);
                    txtMatsN.setText("");
                    secCarpets.setVisibility(View.GONE);
                    lineCarpets.setVisibility(View.GONE);
                    rdogrpCarpets.clearCheck();
                    secCarpetsN.setVisibility(View.GONE);
                    lineCarpetsN.setVisibility(View.GONE);
                    txtCarpetsN.setText("");
                    secBed.setVisibility(View.GONE);
                    lineBed.setVisibility(View.GONE);
                    rdogrpBed.clearCheck();
                    secBedN.setVisibility(View.GONE);
                    lineBedN.setVisibility(View.GONE);
                    txtBedN.setText("");
                    secChair.setVisibility(View.GONE);
                    lineChair.setVisibility(View.GONE);
                    rdogrpChair.clearCheck();
                    secChairN.setVisibility(View.GONE);
                    lineChairN.setVisibility(View.GONE);
                    txtChairN.setText("");
                    secSofa.setVisibility(View.GONE);
                    lineSofa.setVisibility(View.GONE);
                    rdogrpSofa.clearCheck();
                    secSofaN.setVisibility(View.GONE);
                    lineSofaN.setVisibility(View.GONE);
                    txtSofaN.setText("");
                    secTables.setVisibility(View.GONE);
                    lineTables.setVisibility(View.GONE);
                    rdogrpTables.clearCheck();
                    secTablesN.setVisibility(View.GONE);
                    lineTablesN.setVisibility(View.GONE);
                    txtTablesN.setText("");
                    secWatch.setVisibility(View.GONE);
                    lineWatch.setVisibility(View.GONE);
                    rdogrpWatch.clearCheck();
                    secWatchN.setVisibility(View.GONE);
                    lineWatchN.setVisibility(View.GONE);
                    txtWatchN.setText("");
                    secWMachine.setVisibility(View.GONE);
                    lineWMachine.setVisibility(View.GONE);
                    rdogrpWMachine.clearCheck();
                    secWMachineN.setVisibility(View.GONE);
                    lineWMachineN.setVisibility(View.GONE);
                    txtWMachineN.setText("");
                    secIron.setVisibility(View.GONE);
                    lineIron.setVisibility(View.GONE);
                    rdogrpIron.clearCheck();
                    secIronN.setVisibility(View.GONE);
                    lineIronN.setVisibility(View.GONE);
                    txtIronN.setText("");
                    secBooth.setVisibility(View.GONE);
                    lineBooth.setVisibility(View.GONE);
                    rdogrpBooth.clearCheck();
                    secBoothN.setVisibility(View.GONE);
                    lineBoothN.setVisibility(View.GONE);
                    txtBoothN.setText("");
                    secSMachine.setVisibility(View.GONE);
                    lineSMachine.setVisibility(View.GONE);
                    rdogrpSMachine.clearCheck();
                    secSMachineN.setVisibility(View.GONE);
                    lineSMachineN.setVisibility(View.GONE);
                    txtSMachineN.setText("");
                    secGenerator.setVisibility(View.GONE);
                    lineGenerator.setVisibility(View.GONE);
                    rdogrpGenerator.clearCheck();
                    secGeneratorN.setVisibility(View.GONE);
                    lineGeneratorN.setVisibility(View.GONE);
                    txtGeneratorN.setText("");
                    seclblH12a.setVisibility(View.GONE);
                    linelblH12a.setVisibility(View.GONE);
                    secInternet.setVisibility(View.GONE);
                    lineInternet.setVisibility(View.GONE);
                    rdogrpInternet.clearCheck();
                    secInternetN.setVisibility(View.GONE);
                    lineInternetN.setVisibility(View.GONE);
                    txtInternetN.setText("");
                    secSatellite.setVisibility(View.GONE);
                    lineSatellite.setVisibility(View.GONE);
                    rdogrpSatellite.clearCheck();
                    secSatelliteN.setVisibility(View.GONE);
                    lineSatelliteN.setVisibility(View.GONE);
                    txtSatelliteN.setText("");
                    secLandline.setVisibility(View.GONE);
                    lineLandline.setVisibility(View.GONE);
                    rdogrpLandline.clearCheck();
                    secLandlineN.setVisibility(View.GONE);
                    lineLandlineN.setVisibility(View.GONE);
                    txtLandlineN.setText("");
                    secCellphone.setVisibility(View.GONE);
                    lineCellphone.setVisibility(View.GONE);
                    rdogrpCellphone.clearCheck();
                    secCellphoneN.setVisibility(View.GONE);
                    lineCellphoneN.setVisibility(View.GONE);
                    txtCellphoneN.setText("");
                    secTV.setVisibility(View.GONE);
                    lineTV.setVisibility(View.GONE);
                    rdogrpTV.clearCheck();
                    secTVN.setVisibility(View.GONE);
                    lineTVN.setVisibility(View.GONE);
                    txtTVN.setText("");
                    secTV5.setVisibility(View.GONE);
                    lineTV5.setVisibility(View.GONE);
                    rdogrpTV5.clearCheck();
                    secTV5N.setVisibility(View.GONE);
                    lineTV5N.setVisibility(View.GONE);
                    txtTV5N.setText("");
                    secChannel.setVisibility(View.GONE);
                    lineChannel.setVisibility(View.GONE);
                    rdogrpChannel.clearCheck();
                    secChannelN.setVisibility(View.GONE);
                    lineChannelN.setVisibility(View.GONE);
                    txtChannelN.setText("");
                    secRadio.setVisibility(View.GONE);
                    lineRadio.setVisibility(View.GONE);
                    rdogrpRadio.clearCheck();
                    secRadioN.setVisibility(View.GONE);
                    lineRadioN.setVisibility(View.GONE);
                    txtRadioN.setText("");
                    secDVD.setVisibility(View.GONE);
                    lineDVD.setVisibility(View.GONE);
                    rdogrpDVD.clearCheck();
                    secDVDN.setVisibility(View.GONE);
                    lineDVDN.setVisibility(View.GONE);
                    txtDVDN.setText("");
                    secVideo.setVisibility(View.GONE);
                    lineVideo.setVisibility(View.GONE);
                    rdogrpVideo.clearCheck();
                    secVideoN.setVisibility(View.GONE);
                    lineVideoN.setVisibility(View.GONE);
                    txtVideoN.setText("");
                    secComputer.setVisibility(View.GONE);
                    lineComputer.setVisibility(View.GONE);
                    rdogrpComputer.clearCheck();
                    secComputerN.setVisibility(View.GONE);
                    lineComputerN.setVisibility(View.GONE);
                    txtComputerN.setText("");
                    secLaptop.setVisibility(View.GONE);
                    lineLaptop.setVisibility(View.GONE);
                    rdogrpLaptop.clearCheck();
                    secLaptopN.setVisibility(View.GONE);
                    lineLaptopN.setVisibility(View.GONE);
                    txtLaptopN.setText("");
                    secCable.setVisibility(View.GONE);
                    lineCable.setVisibility(View.GONE);
                    rdogrpCable.clearCheck();
                    secCableN.setVisibility(View.GONE);
                    lineCableN.setVisibility(View.GONE);
                    txtCableN.setText("");
                    seclblH12b.setVisibility(View.GONE);
                    linelblH12b.setVisibility(View.GONE);
                    secMicrowave.setVisibility(View.GONE);
                    lineMicrowave.setVisibility(View.GONE);
                    rdogrpMicrowave.clearCheck();
                    secMicrowaveN.setVisibility(View.GONE);
                    lineMicrowaveN.setVisibility(View.GONE);
                    txtMicrowaveN.setText("");
                    secGeyser.setVisibility(View.GONE);
                    lineGeyser.setVisibility(View.GONE);
                    rdogrpGeyser.clearCheck();
                    secGeyserN.setVisibility(View.GONE);
                    lineGeyserN.setVisibility(View.GONE);
                    txtGeyserN.setText("");
                    secGrill.setVisibility(View.GONE);
                    lineGrill.setVisibility(View.GONE);
                    rdogrpGrill.clearCheck();
                    secGrillN.setVisibility(View.GONE);
                    lineGrillN.setVisibility(View.GONE);
                    txtGrillN.setText("");
                    secGrain.setVisibility(View.GONE);
                    lineGrain.setVisibility(View.GONE);
                    rdogrpGrain.clearCheck();
                    secGrainN.setVisibility(View.GONE);
                    lineGrainN.setVisibility(View.GONE);
                    txtGrainN.setText("");
                    secRefrigerator.setVisibility(View.GONE);
                    lineRefrigerator.setVisibility(View.GONE);
                    rdogrpRefrigerator.clearCheck();
                    secRefrigeratorN.setVisibility(View.GONE);
                    lineRefrigeratorN.setVisibility(View.GONE);
                    txtRefrigeratorN.setText("");
                    secDeepFreezer.setVisibility(View.GONE);
                    lineDeepFreezer.setVisibility(View.GONE);
                    rdogrpDeepFreezer.clearCheck();
                    secDeepFreezerN.setVisibility(View.GONE);
                    lineDeepFreezerN.setVisibility(View.GONE);
                    txtDeepFreezerN.setText("");
                    secStove.setVisibility(View.GONE);
                    lineStove.setVisibility(View.GONE);
                    rdogrpStove.clearCheck();
                    secStoveN.setVisibility(View.GONE);
                    lineStoveN.setVisibility(View.GONE);
                    txtStoveN.setText("");
                    secGasHob.setVisibility(View.GONE);
                    lineGasHob.setVisibility(View.GONE);
                    rdogrpGasHob.clearCheck();
                    secGasHobN.setVisibility(View.GONE);
                    lineGasHobN.setVisibility(View.GONE);
                    txtGasHobN.setText("");
                    secImpCooker.setVisibility(View.GONE);
                    lineImpCooker.setVisibility(View.GONE);
                    rdogrpImpCooker.clearCheck();
                    secImpCookerN.setVisibility(View.GONE);
                    lineImpCookerN.setVisibility(View.GONE);
                    txtImpCookerN.setText("");
                    seclblH12c.setVisibility(View.GONE);
                    linelblH12c.setVisibility(View.GONE);
                    secBike.setVisibility(View.GONE);
                    lineBike.setVisibility(View.GONE);
                    rdogrpBike.clearCheck();
                    secBikeN.setVisibility(View.GONE);
                    lineBikeN.setVisibility(View.GONE);
                    txtBikeN.setText("");
                    secMotorcycle.setVisibility(View.GONE);
                    lineMotorcycle.setVisibility(View.GONE);
                    rdogrpMotorcycle.clearCheck();
                    secMotorcycleN.setVisibility(View.GONE);
                    lineMotorcycleN.setVisibility(View.GONE);
                    txtMotorcycleN.setText("");
                    secCar.setVisibility(View.GONE);
                    lineCar.setVisibility(View.GONE);
                    rdogrpCar.clearCheck();
                    secCarN.setVisibility(View.GONE);
                    lineCarN.setVisibility(View.GONE);
                    txtCarN.setText("");
                    secRickshaw.setVisibility(View.GONE);
                    lineRickshaw.setVisibility(View.GONE);
                    rdogrpRickshaw.clearCheck();
                    secRickshawN.setVisibility(View.GONE);
                    lineRickshawN.setVisibility(View.GONE);
                    txtRickshawN.setText("");
                    secCart.setVisibility(View.GONE);
                    lineCart.setVisibility(View.GONE);
                    rdogrpCart.clearCheck();
                    secCartN.setVisibility(View.GONE);
                    lineCartN.setVisibility(View.GONE);
                    txtCartN.setText("");
                    secCanoe.setVisibility(View.GONE);
                    lineCanoe.setVisibility(View.GONE);
                    rdogrpCanoe.clearCheck();
                    secCanoeN.setVisibility(View.GONE);
                    lineCanoeN.setVisibility(View.GONE);
                    txtCanoeN.setText("");
                    secBus.setVisibility(View.GONE);
                    lineBus.setVisibility(View.GONE);
                    rdogrpBus.clearCheck();
                    secBusN.setVisibility(View.GONE);
                    lineBusN.setVisibility(View.GONE);
                    txtBusN.setText("");
                    seclblH12d.setVisibility(View.GONE);
                    linelblH12d.setVisibility(View.GONE);
                    secTractor.setVisibility(View.GONE);
                    lineTractor.setVisibility(View.GONE);
                    rdogrpTractor.clearCheck();
                    secTractorN.setVisibility(View.GONE);
                    lineTractorN.setVisibility(View.GONE);
                    txtTractorN.setText("");
                    secPlow.setVisibility(View.GONE);
                    linePlow.setVisibility(View.GONE);
                    rdogrpPlow.clearCheck();
                    secPlowN.setVisibility(View.GONE);
                    linePlowN.setVisibility(View.GONE);
                    txtPlowN.setText("");
                    secDuck.setVisibility(View.GONE);
                    lineDuck.setVisibility(View.GONE);
                    rdogrpDuck.clearCheck();
                    secDuckN.setVisibility(View.GONE);
                    lineDuckN.setVisibility(View.GONE);
                    txtDuckN.setText("");
                    secCow.setVisibility(View.GONE);
                    lineCow.setVisibility(View.GONE);
                    rdogrpCow.clearCheck();
                    secCowN.setVisibility(View.GONE);
                    lineCowN.setVisibility(View.GONE);
                    txtCowN.setText("");
                    secSheep.setVisibility(View.GONE);
                    lineSheep.setVisibility(View.GONE);
                    rdogrpSheep.clearCheck();
                    secSheepN.setVisibility(View.GONE);
                    lineSheepN.setVisibility(View.GONE);
                    txtSheepN.setText("");
                    secGoat.setVisibility(View.GONE);
                    lineGoat.setVisibility(View.GONE);
                    rdogrpGoat.clearCheck();
                    secGoatN.setVisibility(View.GONE);
                    lineGoatN.setVisibility(View.GONE);
                    txtGoatN.setText("");
                    secChicken.setVisibility(View.GONE);
                    lineChicken.setVisibility(View.GONE);
                    rdogrpChicken.clearCheck();
                    secChickenN.setVisibility(View.GONE);
                    lineChickenN.setVisibility(View.GONE);
                    txtChickenN.setText("");
                    secDonkey.setVisibility(View.GONE);
                    lineDonkey.setVisibility(View.GONE);
                    rdogrpDonkey.clearCheck();
                    secDunkeyN.setVisibility(View.GONE);
                    lineDunkeyN.setVisibility(View.GONE);
                    txtDunkeyN.setText("");
                    secHorse.setVisibility(View.GONE);
                    lineHorse.setVisibility(View.GONE);
                    rdogrpHorse.clearCheck();
                    secHorseN.setVisibility(View.GONE);
                    lineHorseN.setVisibility(View.GONE);
                    txtHorseN.setText("");
                    secPig.setVisibility(View.GONE);
                    linePig.setVisibility(View.GONE);
                    rdogrpPig.clearCheck();
                    secPigN.setVisibility(View.GONE);
                    linePigN.setVisibility(View.GONE);
                    txtPigN.setText("");
                    secBirds.setVisibility(View.GONE);
                    lineBirds.setVisibility(View.GONE);
                    rdogrpBirds.clearCheck();
                    secBirdsN.setVisibility(View.GONE);
                    lineBirdsN.setVisibility(View.GONE);
                    txtBirdsN.setText("");
                    secDogs.setVisibility(View.GONE);
                    lineDogs.setVisibility(View.GONE);
                    rdogrpDogs.clearCheck();
                    secDogsN.setVisibility(View.GONE);
                    lineDogsN.setVisibility(View.GONE);
                    txtDogsN.setText("");
                    secCats.setVisibility(View.GONE);
                    lineCats.setVisibility(View.GONE);
                    rdogrpCats.clearCheck();
                    secCatsN.setVisibility(View.GONE);
                    lineCatsN.setVisibility(View.GONE);
                    txtCatsN.setText("");
                    secFishNet.setVisibility(View.GONE);
                    lineFishNet.setVisibility(View.GONE);
                    rdogrpFishNet.clearCheck();
                    secFishNetN.setVisibility(View.GONE);
                    lineFishNetN.setVisibility(View.GONE);
                    txtFishNetN.setText("");
                    secOtherAsset.setVisibility(View.GONE);
                    lineOtherAsset.setVisibility(View.GONE);
                    rdogrpOtherAsset.clearCheck();
                    secOtherAsset1.setVisibility(View.GONE);
                    lineOtherAsset1.setVisibility(View.GONE);
                    txtOtherAsset1.setText("");
                    secOtherAsset1N.setVisibility(View.GONE);
                    lineOtherAsset1N.setVisibility(View.GONE);
                    txtOtherAsset1N.setText("");
                    secOtherAsset2.setVisibility(View.GONE);
                    lineOtherAsset2.setVisibility(View.GONE);
                    txtOtherAsset2.setText("");
                    secOtherAsset2N.setVisibility(View.GONE);
                    lineOtherAsset2N.setVisibility(View.GONE);
                    txtOtherAsset2N.setText("");
                    secOtherAsset3.setVisibility(View.GONE);
                    lineOtherAsset3.setVisibility(View.GONE);
                    txtOtherAsset3.setText("");
                    secOtherAsset3N.setVisibility(View.GONE);
                    lineOtherAsset3N.setVisibility(View.GONE);
                    txtOtherAsset3N.setText("");
                    secSESNote.setVisibility(View.GONE);
                    lineSESNote.setVisibility(View.GONE);
                    txtSESNote.setText("");
             }
             else if(rbData.equalsIgnoreCase("9"))
             {
                    secSESVStatusOth.setVisibility(View.VISIBLE);
                    lineSESVStatusOth.setVisibility(View.VISIBLE);
                    secRnd.setVisibility(View.GONE);
                    lineRnd.setVisibility(View.GONE);
                    txtRnd.setText("");
                    secWSDrink.setVisibility(View.GONE);
                    lineWSDrink.setVisibility(View.GONE);
                    spnWSDrink.setSelection(0);
                    secWSDrinkOth.setVisibility(View.GONE);
                    lineWSDrinkOth.setVisibility(View.GONE);
                    txtWSDrinkOth.setText("");
                    secWaterSource.setVisibility(View.GONE);
                    lineWaterSource.setVisibility(View.GONE);
                    rdogrpWaterSource.clearCheck();
                    secFetchWaterM.setVisibility(View.GONE);
                    lineFetchWaterM.setVisibility(View.GONE);
                    txtFetchWaterM.setText("");
                    secFetchWaterMDk.setVisibility(View.GONE);
                    lineFetchWaterMDk.setVisibility(View.GONE);
                    rdogrpFetchWaterMDk.clearCheck();
                    secGetWater.setVisibility(View.GONE);
                    lineGetWater.setVisibility(View.GONE);
                    rdogrpGetWater.clearCheck();
                    secGetWaterOth.setVisibility(View.GONE);
                    lineGetWaterOth.setVisibility(View.GONE);
                    txtGetWaterOth.setText("");
                    secMemberID.setVisibility(View.GONE);
                    lineMemberID.setVisibility(View.GONE);
                    spnMemberID.setSelection(0);
                    secBringWater.setVisibility(View.GONE);
                    lineBringWater.setVisibility(View.GONE);
                    txtBringWater.setText("");
                    secBringWaterDk.setVisibility(View.GONE);
                    lineBringWaterDk.setVisibility(View.GONE);
                    rdogrpBringWaterDk.clearCheck();
                    secSomeone.setVisibility(View.GONE);
                    lineSomeone.setVisibility(View.GONE);
                    rdogrpSomeone.clearCheck();
                    secSecondPers.setVisibility(View.GONE);
                    lineSecondPers.setVisibility(View.GONE);
                    rdogrpSecondPers.clearCheck();
                    secSecondPersOth.setVisibility(View.GONE);
                    lineSecondPersOth.setVisibility(View.GONE);
                    txtSecondPersOth.setText("");
                    secMemberID2nd.setVisibility(View.GONE);
                    lineMemberID2nd.setVisibility(View.GONE);
                    spnMemberID2nd.setSelection(0);
                    secEnoughWater.setVisibility(View.GONE);
                    lineEnoughWater.setVisibility(View.GONE);
                    rdogrpEnoughWater.clearCheck();
                    secMainWater.setVisibility(View.GONE);
                    lineMainWater.setVisibility(View.GONE);
                    spnMainWater.setSelection(0);
                    secMainWaterOth.setVisibility(View.GONE);
                    lineMainWaterOth.setVisibility(View.GONE);
                    txtMainWaterOth.setText("");
                    seclbl111.setVisibility(View.GONE);
                    linelbl111.setVisibility(View.GONE);
                    secSmallTank.setVisibility(View.GONE);
                    lineSmallTank.setVisibility(View.GONE);
                    rdogrpSmallTank.clearCheck();
                    secMediunTank.setVisibility(View.GONE);
                    lineMediunTank.setVisibility(View.GONE);
                    rdogrpMediunTank.clearCheck();
                    secLargeTank.setVisibility(View.GONE);
                    lineLargeTank.setVisibility(View.GONE);
                    rdogrpLargeTank.clearCheck();
                    secStoreDrink.setVisibility(View.GONE);
                    lineStoreDrink.setVisibility(View.GONE);
                    rdogrpStoreDrink.clearCheck();
                    seclbl113.setVisibility(View.GONE);
                    linelbl113.setVisibility(View.GONE);
                    secContainOpenCov.setVisibility(View.GONE);
                    lineContainOpenCov.setVisibility(View.GONE);
                    chkContainOpenCov.setChecked(false);
                    secContainOpenNotCov.setVisibility(View.GONE);
                    lineContainOpenNotCov.setVisibility(View.GONE);
                    chkContainOpenNotCov.setChecked(false);
                    secContainOpenDK.setVisibility(View.GONE);
                    lineContainOpenDK.setVisibility(View.GONE);
                    chkContainOpenDK.setChecked(false);
                    secRecoverWater.setVisibility(View.GONE);
                    lineRecoverWater.setVisibility(View.GONE);
                    rdogrpRecoverWater.clearCheck();
                    secRecoverWaterOth.setVisibility(View.GONE);
                    lineRecoverWaterOth.setVisibility(View.GONE);
                    txtRecoverWaterOth.setText("");
                    secLessDanger.setVisibility(View.GONE);
                    lineLessDanger.setVisibility(View.GONE);
                    rdogrpLessDanger.clearCheck();
                    secMakeSafer.setVisibility(View.GONE);
                    lineMakeSafer.setVisibility(View.GONE);
                    spnMakeSafer.setSelection(0);
                    secMakeSaferOth.setVisibility(View.GONE);
                    lineMakeSaferOth.setVisibility(View.GONE);
                    txtMakeSaferOth.setText("");
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
                    secContentEmp.setVisibility(View.GONE);
                    lineContentEmp.setVisibility(View.GONE);
                    rdogrpContentEmp.clearCheck();
                    secContentEmpOth.setVisibility(View.GONE);
                    lineContentEmpOth.setVisibility(View.GONE);
                    txtContentEmpOth.setText("");
                    secBowelMov.setVisibility(View.GONE);
                    lineBowelMov.setVisibility(View.GONE);
                    spnBowelMov.setSelection(0);
                    secBowelMovOth.setVisibility(View.GONE);
                    lineBowelMovOth.setVisibility(View.GONE);
                    txtBowelMovOth.setText("");
                    secLiquidWaste.setVisibility(View.GONE);
                    lineLiquidWaste.setVisibility(View.GONE);
                    rdogrpLiquidWaste.clearCheck();
                    secLiquidWasteOth.setVisibility(View.GONE);
                    lineLiquidWasteOth.setVisibility(View.GONE);
                    txtLiquidWasteOth.setText("");
                    secSolidWasteMethod.setVisibility(View.GONE);
                    lineSolidWasteMethod.setVisibility(View.GONE);
                    spnSolidWasteMethod.setSelection(0);
                    secSolidWasteMethodOth.setVisibility(View.GONE);
                    lineSolidWasteMethodOth.setVisibility(View.GONE);
                    txtSolidWasteMethodOth.setText("");
                    secHandWash.setVisibility(View.GONE);
                    lineHandWash.setVisibility(View.GONE);
                    rdogrpHandWash.clearCheck();
                    secShowWash.setVisibility(View.GONE);
                    lineShowWash.setVisibility(View.GONE);
                    rdogrpShowWash.clearCheck();
                    secAvailableWat.setVisibility(View.GONE);
                    lineAvailableWat.setVisibility(View.GONE);
                    rdogrpAvailableWat.clearCheck();
                    secAvailableSoap.setVisibility(View.GONE);
                    lineAvailableSoap.setVisibility(View.GONE);
                    rdogrpAvailableSoap.clearCheck();
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
                    secGroundMat.setVisibility(View.GONE);
                    lineGroundMat.setVisibility(View.GONE);
                    spnGroundMat.setSelection(0);
                    secGroundMatOth.setVisibility(View.GONE);
                    lineGroundMatOth.setVisibility(View.GONE);
                    txtGroundMatOth.setText("");
                    secRoof.setVisibility(View.GONE);
                    lineRoof.setVisibility(View.GONE);
                    spnRoof.setSelection(0);
                    secRoofOth.setVisibility(View.GONE);
                    lineRoofOth.setVisibility(View.GONE);
                    txtRoofOth.setText("");
                    secSmokeInside.setVisibility(View.GONE);
                    lineSmokeInside.setVisibility(View.GONE);
                    rdogrpSmokeInside.clearCheck();
                    secFreqSmoke.setVisibility(View.GONE);
                    lineFreqSmoke.setVisibility(View.GONE);
                    rdogrpFreqSmoke.clearCheck();
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
                    secElecNight.setVisibility(View.GONE);
                    lineElecNight.setVisibility(View.GONE);
                    spnElecNight.setSelection(0);
                    secElecNightOth.setVisibility(View.GONE);
                    lineElecNightOth.setVisibility(View.GONE);
                    txtElecNightOth.setText("");
                    secHomesteadAny.setVisibility(View.GONE);
                    lineHomesteadAny.setVisibility(View.GONE);
                    rdogrpHomesteadAny.clearCheck();
                    secOthLand.setVisibility(View.GONE);
                    lineOthLand.setVisibility(View.GONE);
                    rdogrpOthLand.clearCheck();
                    secArea.setVisibility(View.GONE);
                    lineArea.setVisibility(View.GONE);
                    txtArea.setText("");
                    secIncomeMo.setVisibility(View.GONE);
                    lineIncomeMo.setVisibility(View.GONE);
                    rdogrpIncomeMo.clearCheck();
                    secExpenses.setVisibility(View.GONE);
                    lineExpenses.setVisibility(View.GONE);
                    rdogrpExpenses.clearCheck();
                    secBankAcc.setVisibility(View.GONE);
                    lineBankAcc.setVisibility(View.GONE);
                    rdogrpBankAcc.clearCheck();
                    secSprayInt.setVisibility(View.GONE);
                    lineSprayInt.setVisibility(View.GONE);
                    rdogrpSprayInt.clearCheck();
                    secMosqNet.setVisibility(View.GONE);
                    lineMosqNet.setVisibility(View.GONE);
                    rdogrpMosqNet.clearCheck();
                    secNetOwned.setVisibility(View.GONE);
                    lineNetOwned.setVisibility(View.GONE);
                    txtNetOwned.setText("");
                    secMedHome.setVisibility(View.GONE);
                    lineMedHome.setVisibility(View.GONE);
                    rdogrpMedHome.clearCheck();
                    seclbl119.setVisibility(View.GONE);
                    linelbl119.setVisibility(View.GONE);
                    secMedTypeAM.setVisibility(View.GONE);
                    lineMedTypeAM.setVisibility(View.GONE);
                    chkMedTypeAM.setChecked(false);
                    secMedTypeAB.setVisibility(View.GONE);
                    lineMedTypeAB.setVisibility(View.GONE);
                    chkMedTypeAB.setChecked(false);
                    secMedTypeDK.setVisibility(View.GONE);
                    lineMedTypeDK.setVisibility(View.GONE);
                    chkMedTypeDK.setChecked(false);
                    seclbl1110.setVisibility(View.GONE);
                    linelbl1110.setVisibility(View.GONE);
                    secAntimalarAL.setVisibility(View.GONE);
                    lineAntimalarAL.setVisibility(View.GONE);
                    chkAntimalarAL.setChecked(false);
                    secAntimalarASAQ.setVisibility(View.GONE);
                    lineAntimalarASAQ.setVisibility(View.GONE);
                    chkAntimalarASAQ.setChecked(false);
                    secAntimalarSP.setVisibility(View.GONE);
                    lineAntimalarSP.setVisibility(View.GONE);
                    chkAntimalarSP.setChecked(false);
                    secAntimalarOth.setVisibility(View.GONE);
                    lineAntimalarOth.setVisibility(View.GONE);
                    chkAntimalarOth.setChecked(false);
                    secAntimalarSpecifyOth.setVisibility(View.GONE);
                    lineAntimalarSpecifyOth.setVisibility(View.GONE);
                    txtAntimalarSpecifyOth.setText("");
                    seclbl1111.setVisibility(View.GONE);
                    linelbl1111.setVisibility(View.GONE);
                    secGetMedHosp.setVisibility(View.GONE);
                    lineGetMedHosp.setVisibility(View.GONE);
                    chkGetMedHosp.setChecked(false);
                    secGetMedCSCom.setVisibility(View.GONE);
                    lineGetMedCSCom.setVisibility(View.GONE);
                    chkGetMedCSCom.setChecked(false);
                    secGetMedPrvCl.setVisibility(View.GONE);
                    lineGetMedPrvCl.setVisibility(View.GONE);
                    chkGetMedPrvCl.setChecked(false);
                    secGetMedPhar.setVisibility(View.GONE);
                    lineGetMedPhar.setVisibility(View.GONE);
                    chkGetMedPhar.setChecked(false);
                    secGetMedPD.setVisibility(View.GONE);
                    lineGetMedPD.setVisibility(View.GONE);
                    chkGetMedPD.setChecked(false);
                    secGetMedCHW.setVisibility(View.GONE);
                    lineGetMedCHW.setVisibility(View.GONE);
                    chkGetMedCHW.setChecked(false);
                    secGetMedSS.setVisibility(View.GONE);
                    lineGetMedSS.setVisibility(View.GONE);
                    chkGetMedSS.setChecked(false);
                    secGetMedOth.setVisibility(View.GONE);
                    lineGetMedOth.setVisibility(View.GONE);
                    chkGetMedOth.setChecked(false);
                    secGetMedSpecifyOth.setVisibility(View.GONE);
                    lineGetMedSpecifyOth.setVisibility(View.GONE);
                    txtGetMedSpecifyOth.setText("");
                    secAComment.setVisibility(View.GONE);
                    lineAComment.setVisibility(View.GONE);
                    rdogrpAComment.clearCheck();
                    secComment.setVisibility(View.GONE);
                    lineComment.setVisibility(View.GONE);
                    txtComment.setText("");
                    seclblH12.setVisibility(View.GONE);
                    linelblH12.setVisibility(View.GONE);
                    seclblH121.setVisibility(View.GONE);
                    linelblH121.setVisibility(View.GONE);
                    secElectricity.setVisibility(View.GONE);
                    lineElectricity.setVisibility(View.GONE);
                    rdogrpElectricity.clearCheck();
                    secSolarPlate.setVisibility(View.GONE);
                    lineSolarPlate.setVisibility(View.GONE);
                    rdogrpSolarPlate.clearCheck();
                    secSolarPlateN.setVisibility(View.GONE);
                    lineSolarPlateN.setVisibility(View.GONE);
                    txtSolarPlateN.setText("");
                    secHeater.setVisibility(View.GONE);
                    lineHeater.setVisibility(View.GONE);
                    rdogrpHeater.clearCheck();
                    secHeaterN.setVisibility(View.GONE);
                    lineHeaterN.setVisibility(View.GONE);
                    txtHeaterN.setText("");
                    secAC.setVisibility(View.GONE);
                    lineAC.setVisibility(View.GONE);
                    rdogrpAC.clearCheck();
                    secACN.setVisibility(View.GONE);
                    lineACN.setVisibility(View.GONE);
                    txtACN.setText("");
                    secElecFan.setVisibility(View.GONE);
                    lineElecFan.setVisibility(View.GONE);
                    rdogrpElecFan.clearCheck();
                    secElecFanN.setVisibility(View.GONE);
                    lineElecFanN.setVisibility(View.GONE);
                    txtElecFanN.setText("");
                    secLantern.setVisibility(View.GONE);
                    lineLantern.setVisibility(View.GONE);
                    rdogrpLantern.clearCheck();
                    secLanternN.setVisibility(View.GONE);
                    lineLanternN.setVisibility(View.GONE);
                    txtLanternN.setText("");
                    secLamp.setVisibility(View.GONE);
                    lineLamp.setVisibility(View.GONE);
                    rdogrpLamp.clearCheck();
                    secLampN.setVisibility(View.GONE);
                    lineLampN.setVisibility(View.GONE);
                    txtLampN.setText("");
                    secGasLamp.setVisibility(View.GONE);
                    lineGasLamp.setVisibility(View.GONE);
                    rdogrpGasLamp.clearCheck();
                    secGasLampN.setVisibility(View.GONE);
                    lineGasLampN.setVisibility(View.GONE);
                    txtGasLampN.setText("");
                    secPetroleum.setVisibility(View.GONE);
                    linePetroleum.setVisibility(View.GONE);
                    rdogrpPetroleum.clearCheck();
                    secPetroleumN.setVisibility(View.GONE);
                    linePetroleumN.setVisibility(View.GONE);
                    txtPetroleumN.setText("");
                    secMatt.setVisibility(View.GONE);
                    lineMatt.setVisibility(View.GONE);
                    rdogrpMatt.clearCheck();
                    secMattN.setVisibility(View.GONE);
                    lineMattN.setVisibility(View.GONE);
                    txtMattN.setText("");
                    secMats.setVisibility(View.GONE);
                    lineMats.setVisibility(View.GONE);
                    rdogrpMats.clearCheck();
                    secMatsN.setVisibility(View.GONE);
                    lineMatsN.setVisibility(View.GONE);
                    txtMatsN.setText("");
                    secCarpets.setVisibility(View.GONE);
                    lineCarpets.setVisibility(View.GONE);
                    rdogrpCarpets.clearCheck();
                    secCarpetsN.setVisibility(View.GONE);
                    lineCarpetsN.setVisibility(View.GONE);
                    txtCarpetsN.setText("");
                    secBed.setVisibility(View.GONE);
                    lineBed.setVisibility(View.GONE);
                    rdogrpBed.clearCheck();
                    secBedN.setVisibility(View.GONE);
                    lineBedN.setVisibility(View.GONE);
                    txtBedN.setText("");
                    secChair.setVisibility(View.GONE);
                    lineChair.setVisibility(View.GONE);
                    rdogrpChair.clearCheck();
                    secChairN.setVisibility(View.GONE);
                    lineChairN.setVisibility(View.GONE);
                    txtChairN.setText("");
                    secSofa.setVisibility(View.GONE);
                    lineSofa.setVisibility(View.GONE);
                    rdogrpSofa.clearCheck();
                    secSofaN.setVisibility(View.GONE);
                    lineSofaN.setVisibility(View.GONE);
                    txtSofaN.setText("");
                    secTables.setVisibility(View.GONE);
                    lineTables.setVisibility(View.GONE);
                    rdogrpTables.clearCheck();
                    secTablesN.setVisibility(View.GONE);
                    lineTablesN.setVisibility(View.GONE);
                    txtTablesN.setText("");
                    secWatch.setVisibility(View.GONE);
                    lineWatch.setVisibility(View.GONE);
                    rdogrpWatch.clearCheck();
                    secWatchN.setVisibility(View.GONE);
                    lineWatchN.setVisibility(View.GONE);
                    txtWatchN.setText("");
                    secWMachine.setVisibility(View.GONE);
                    lineWMachine.setVisibility(View.GONE);
                    rdogrpWMachine.clearCheck();
                    secWMachineN.setVisibility(View.GONE);
                    lineWMachineN.setVisibility(View.GONE);
                    txtWMachineN.setText("");
                    secIron.setVisibility(View.GONE);
                    lineIron.setVisibility(View.GONE);
                    rdogrpIron.clearCheck();
                    secIronN.setVisibility(View.GONE);
                    lineIronN.setVisibility(View.GONE);
                    txtIronN.setText("");
                    secBooth.setVisibility(View.GONE);
                    lineBooth.setVisibility(View.GONE);
                    rdogrpBooth.clearCheck();
                    secBoothN.setVisibility(View.GONE);
                    lineBoothN.setVisibility(View.GONE);
                    txtBoothN.setText("");
                    secSMachine.setVisibility(View.GONE);
                    lineSMachine.setVisibility(View.GONE);
                    rdogrpSMachine.clearCheck();
                    secSMachineN.setVisibility(View.GONE);
                    lineSMachineN.setVisibility(View.GONE);
                    txtSMachineN.setText("");
                    secGenerator.setVisibility(View.GONE);
                    lineGenerator.setVisibility(View.GONE);
                    rdogrpGenerator.clearCheck();
                    secGeneratorN.setVisibility(View.GONE);
                    lineGeneratorN.setVisibility(View.GONE);
                    txtGeneratorN.setText("");
                    seclblH12a.setVisibility(View.GONE);
                    linelblH12a.setVisibility(View.GONE);
                    secInternet.setVisibility(View.GONE);
                    lineInternet.setVisibility(View.GONE);
                    rdogrpInternet.clearCheck();
                    secInternetN.setVisibility(View.GONE);
                    lineInternetN.setVisibility(View.GONE);
                    txtInternetN.setText("");
                    secSatellite.setVisibility(View.GONE);
                    lineSatellite.setVisibility(View.GONE);
                    rdogrpSatellite.clearCheck();
                    secSatelliteN.setVisibility(View.GONE);
                    lineSatelliteN.setVisibility(View.GONE);
                    txtSatelliteN.setText("");
                    secLandline.setVisibility(View.GONE);
                    lineLandline.setVisibility(View.GONE);
                    rdogrpLandline.clearCheck();
                    secLandlineN.setVisibility(View.GONE);
                    lineLandlineN.setVisibility(View.GONE);
                    txtLandlineN.setText("");
                    secCellphone.setVisibility(View.GONE);
                    lineCellphone.setVisibility(View.GONE);
                    rdogrpCellphone.clearCheck();
                    secCellphoneN.setVisibility(View.GONE);
                    lineCellphoneN.setVisibility(View.GONE);
                    txtCellphoneN.setText("");
                    secTV.setVisibility(View.GONE);
                    lineTV.setVisibility(View.GONE);
                    rdogrpTV.clearCheck();
                    secTVN.setVisibility(View.GONE);
                    lineTVN.setVisibility(View.GONE);
                    txtTVN.setText("");
                    secTV5.setVisibility(View.GONE);
                    lineTV5.setVisibility(View.GONE);
                    rdogrpTV5.clearCheck();
                    secTV5N.setVisibility(View.GONE);
                    lineTV5N.setVisibility(View.GONE);
                    txtTV5N.setText("");
                    secChannel.setVisibility(View.GONE);
                    lineChannel.setVisibility(View.GONE);
                    rdogrpChannel.clearCheck();
                    secChannelN.setVisibility(View.GONE);
                    lineChannelN.setVisibility(View.GONE);
                    txtChannelN.setText("");
                    secRadio.setVisibility(View.GONE);
                    lineRadio.setVisibility(View.GONE);
                    rdogrpRadio.clearCheck();
                    secRadioN.setVisibility(View.GONE);
                    lineRadioN.setVisibility(View.GONE);
                    txtRadioN.setText("");
                    secDVD.setVisibility(View.GONE);
                    lineDVD.setVisibility(View.GONE);
                    rdogrpDVD.clearCheck();
                    secDVDN.setVisibility(View.GONE);
                    lineDVDN.setVisibility(View.GONE);
                    txtDVDN.setText("");
                    secVideo.setVisibility(View.GONE);
                    lineVideo.setVisibility(View.GONE);
                    rdogrpVideo.clearCheck();
                    secVideoN.setVisibility(View.GONE);
                    lineVideoN.setVisibility(View.GONE);
                    txtVideoN.setText("");
                    secComputer.setVisibility(View.GONE);
                    lineComputer.setVisibility(View.GONE);
                    rdogrpComputer.clearCheck();
                    secComputerN.setVisibility(View.GONE);
                    lineComputerN.setVisibility(View.GONE);
                    txtComputerN.setText("");
                    secLaptop.setVisibility(View.GONE);
                    lineLaptop.setVisibility(View.GONE);
                    rdogrpLaptop.clearCheck();
                    secLaptopN.setVisibility(View.GONE);
                    lineLaptopN.setVisibility(View.GONE);
                    txtLaptopN.setText("");
                    secCable.setVisibility(View.GONE);
                    lineCable.setVisibility(View.GONE);
                    rdogrpCable.clearCheck();
                    secCableN.setVisibility(View.GONE);
                    lineCableN.setVisibility(View.GONE);
                    txtCableN.setText("");
                    seclblH12b.setVisibility(View.GONE);
                    linelblH12b.setVisibility(View.GONE);
                    secMicrowave.setVisibility(View.GONE);
                    lineMicrowave.setVisibility(View.GONE);
                    rdogrpMicrowave.clearCheck();
                    secMicrowaveN.setVisibility(View.GONE);
                    lineMicrowaveN.setVisibility(View.GONE);
                    txtMicrowaveN.setText("");
                    secGeyser.setVisibility(View.GONE);
                    lineGeyser.setVisibility(View.GONE);
                    rdogrpGeyser.clearCheck();
                    secGeyserN.setVisibility(View.GONE);
                    lineGeyserN.setVisibility(View.GONE);
                    txtGeyserN.setText("");
                    secGrill.setVisibility(View.GONE);
                    lineGrill.setVisibility(View.GONE);
                    rdogrpGrill.clearCheck();
                    secGrillN.setVisibility(View.GONE);
                    lineGrillN.setVisibility(View.GONE);
                    txtGrillN.setText("");
                    secGrain.setVisibility(View.GONE);
                    lineGrain.setVisibility(View.GONE);
                    rdogrpGrain.clearCheck();
                    secGrainN.setVisibility(View.GONE);
                    lineGrainN.setVisibility(View.GONE);
                    txtGrainN.setText("");
                    secRefrigerator.setVisibility(View.GONE);
                    lineRefrigerator.setVisibility(View.GONE);
                    rdogrpRefrigerator.clearCheck();
                    secRefrigeratorN.setVisibility(View.GONE);
                    lineRefrigeratorN.setVisibility(View.GONE);
                    txtRefrigeratorN.setText("");
                    secDeepFreezer.setVisibility(View.GONE);
                    lineDeepFreezer.setVisibility(View.GONE);
                    rdogrpDeepFreezer.clearCheck();
                    secDeepFreezerN.setVisibility(View.GONE);
                    lineDeepFreezerN.setVisibility(View.GONE);
                    txtDeepFreezerN.setText("");
                    secStove.setVisibility(View.GONE);
                    lineStove.setVisibility(View.GONE);
                    rdogrpStove.clearCheck();
                    secStoveN.setVisibility(View.GONE);
                    lineStoveN.setVisibility(View.GONE);
                    txtStoveN.setText("");
                    secGasHob.setVisibility(View.GONE);
                    lineGasHob.setVisibility(View.GONE);
                    rdogrpGasHob.clearCheck();
                    secGasHobN.setVisibility(View.GONE);
                    lineGasHobN.setVisibility(View.GONE);
                    txtGasHobN.setText("");
                    secImpCooker.setVisibility(View.GONE);
                    lineImpCooker.setVisibility(View.GONE);
                    rdogrpImpCooker.clearCheck();
                    secImpCookerN.setVisibility(View.GONE);
                    lineImpCookerN.setVisibility(View.GONE);
                    txtImpCookerN.setText("");
                    seclblH12c.setVisibility(View.GONE);
                    linelblH12c.setVisibility(View.GONE);
                    secBike.setVisibility(View.GONE);
                    lineBike.setVisibility(View.GONE);
                    rdogrpBike.clearCheck();
                    secBikeN.setVisibility(View.GONE);
                    lineBikeN.setVisibility(View.GONE);
                    txtBikeN.setText("");
                    secMotorcycle.setVisibility(View.GONE);
                    lineMotorcycle.setVisibility(View.GONE);
                    rdogrpMotorcycle.clearCheck();
                    secMotorcycleN.setVisibility(View.GONE);
                    lineMotorcycleN.setVisibility(View.GONE);
                    txtMotorcycleN.setText("");
                    secCar.setVisibility(View.GONE);
                    lineCar.setVisibility(View.GONE);
                    rdogrpCar.clearCheck();
                    secCarN.setVisibility(View.GONE);
                    lineCarN.setVisibility(View.GONE);
                    txtCarN.setText("");
                    secRickshaw.setVisibility(View.GONE);
                    lineRickshaw.setVisibility(View.GONE);
                    rdogrpRickshaw.clearCheck();
                    secRickshawN.setVisibility(View.GONE);
                    lineRickshawN.setVisibility(View.GONE);
                    txtRickshawN.setText("");
                    secCart.setVisibility(View.GONE);
                    lineCart.setVisibility(View.GONE);
                    rdogrpCart.clearCheck();
                    secCartN.setVisibility(View.GONE);
                    lineCartN.setVisibility(View.GONE);
                    txtCartN.setText("");
                    secCanoe.setVisibility(View.GONE);
                    lineCanoe.setVisibility(View.GONE);
                    rdogrpCanoe.clearCheck();
                    secCanoeN.setVisibility(View.GONE);
                    lineCanoeN.setVisibility(View.GONE);
                    txtCanoeN.setText("");
                    secBus.setVisibility(View.GONE);
                    lineBus.setVisibility(View.GONE);
                    rdogrpBus.clearCheck();
                    secBusN.setVisibility(View.GONE);
                    lineBusN.setVisibility(View.GONE);
                    txtBusN.setText("");
                    seclblH12d.setVisibility(View.GONE);
                    linelblH12d.setVisibility(View.GONE);
                    secTractor.setVisibility(View.GONE);
                    lineTractor.setVisibility(View.GONE);
                    rdogrpTractor.clearCheck();
                    secTractorN.setVisibility(View.GONE);
                    lineTractorN.setVisibility(View.GONE);
                    txtTractorN.setText("");
                    secPlow.setVisibility(View.GONE);
                    linePlow.setVisibility(View.GONE);
                    rdogrpPlow.clearCheck();
                    secPlowN.setVisibility(View.GONE);
                    linePlowN.setVisibility(View.GONE);
                    txtPlowN.setText("");
                    secDuck.setVisibility(View.GONE);
                    lineDuck.setVisibility(View.GONE);
                    rdogrpDuck.clearCheck();
                    secDuckN.setVisibility(View.GONE);
                    lineDuckN.setVisibility(View.GONE);
                    txtDuckN.setText("");
                    secCow.setVisibility(View.GONE);
                    lineCow.setVisibility(View.GONE);
                    rdogrpCow.clearCheck();
                    secCowN.setVisibility(View.GONE);
                    lineCowN.setVisibility(View.GONE);
                    txtCowN.setText("");
                    secSheep.setVisibility(View.GONE);
                    lineSheep.setVisibility(View.GONE);
                    rdogrpSheep.clearCheck();
                    secSheepN.setVisibility(View.GONE);
                    lineSheepN.setVisibility(View.GONE);
                    txtSheepN.setText("");
                    secGoat.setVisibility(View.GONE);
                    lineGoat.setVisibility(View.GONE);
                    rdogrpGoat.clearCheck();
                    secGoatN.setVisibility(View.GONE);
                    lineGoatN.setVisibility(View.GONE);
                    txtGoatN.setText("");
                    secChicken.setVisibility(View.GONE);
                    lineChicken.setVisibility(View.GONE);
                    rdogrpChicken.clearCheck();
                    secChickenN.setVisibility(View.GONE);
                    lineChickenN.setVisibility(View.GONE);
                    txtChickenN.setText("");
                    secDonkey.setVisibility(View.GONE);
                    lineDonkey.setVisibility(View.GONE);
                    rdogrpDonkey.clearCheck();
                    secDunkeyN.setVisibility(View.GONE);
                    lineDunkeyN.setVisibility(View.GONE);
                    txtDunkeyN.setText("");
                    secHorse.setVisibility(View.GONE);
                    lineHorse.setVisibility(View.GONE);
                    rdogrpHorse.clearCheck();
                    secHorseN.setVisibility(View.GONE);
                    lineHorseN.setVisibility(View.GONE);
                    txtHorseN.setText("");
                    secPig.setVisibility(View.GONE);
                    linePig.setVisibility(View.GONE);
                    rdogrpPig.clearCheck();
                    secPigN.setVisibility(View.GONE);
                    linePigN.setVisibility(View.GONE);
                    txtPigN.setText("");
                    secBirds.setVisibility(View.GONE);
                    lineBirds.setVisibility(View.GONE);
                    rdogrpBirds.clearCheck();
                    secBirdsN.setVisibility(View.GONE);
                    lineBirdsN.setVisibility(View.GONE);
                    txtBirdsN.setText("");
                    secDogs.setVisibility(View.GONE);
                    lineDogs.setVisibility(View.GONE);
                    rdogrpDogs.clearCheck();
                    secDogsN.setVisibility(View.GONE);
                    lineDogsN.setVisibility(View.GONE);
                    txtDogsN.setText("");
                    secCats.setVisibility(View.GONE);
                    lineCats.setVisibility(View.GONE);
                    rdogrpCats.clearCheck();
                    secCatsN.setVisibility(View.GONE);
                    lineCatsN.setVisibility(View.GONE);
                    txtCatsN.setText("");
                    secFishNet.setVisibility(View.GONE);
                    lineFishNet.setVisibility(View.GONE);
                    rdogrpFishNet.clearCheck();
                    secFishNetN.setVisibility(View.GONE);
                    lineFishNetN.setVisibility(View.GONE);
                    txtFishNetN.setText("");
                    secOtherAsset.setVisibility(View.GONE);
                    lineOtherAsset.setVisibility(View.GONE);
                    rdogrpOtherAsset.clearCheck();
                    secOtherAsset1.setVisibility(View.GONE);
                    lineOtherAsset1.setVisibility(View.GONE);
                    txtOtherAsset1.setText("");
                    secOtherAsset1N.setVisibility(View.GONE);
                    lineOtherAsset1N.setVisibility(View.GONE);
                    txtOtherAsset1N.setText("");
                    secOtherAsset2.setVisibility(View.GONE);
                    lineOtherAsset2.setVisibility(View.GONE);
                    txtOtherAsset2.setText("");
                    secOtherAsset2N.setVisibility(View.GONE);
                    lineOtherAsset2N.setVisibility(View.GONE);
                    txtOtherAsset2N.setText("");
                    secOtherAsset3.setVisibility(View.GONE);
                    lineOtherAsset3.setVisibility(View.GONE);
                    txtOtherAsset3.setText("");
                    secOtherAsset3N.setVisibility(View.GONE);
                    lineOtherAsset3N.setVisibility(View.GONE);
                    txtOtherAsset3N.setText("");
                    secSESNote.setVisibility(View.GONE);
                    lineSESNote.setVisibility(View.GONE);
                    txtSESNote.setText("");
             }
             else
             {
                    secSESVStatusOth.setVisibility(View.GONE);
                    lineSESVStatusOth.setVisibility(View.GONE);
                    txtSESVStatusOth.setText("");
                    secWSDrink.setVisibility(View.VISIBLE);
                    lineWSDrink.setVisibility(View.VISIBLE);
                    secWaterSource.setVisibility(View.VISIBLE);
                    lineWaterSource.setVisibility(View.VISIBLE);
                    secFetchWaterM.setVisibility(View.VISIBLE);
                    lineFetchWaterM.setVisibility(View.VISIBLE);
                    secFetchWaterMDk.setVisibility(View.VISIBLE);
                    lineFetchWaterMDk.setVisibility(View.VISIBLE);
                    secGetWater.setVisibility(View.VISIBLE);
                    lineGetWater.setVisibility(View.VISIBLE);
                    secMemberID.setVisibility(View.VISIBLE);
                    lineMemberID.setVisibility(View.VISIBLE);
                    secBringWater.setVisibility(View.VISIBLE);
                    lineBringWater.setVisibility(View.VISIBLE);
                    secBringWaterDk.setVisibility(View.VISIBLE);
                    lineBringWaterDk.setVisibility(View.VISIBLE);
                    secSomeone.setVisibility(View.VISIBLE);
                    lineSomeone.setVisibility(View.VISIBLE);
                    secSecondPers.setVisibility(View.VISIBLE);
                    lineSecondPers.setVisibility(View.VISIBLE);
                    secMemberID2nd.setVisibility(View.VISIBLE);
                    lineMemberID2nd.setVisibility(View.VISIBLE);
                    secEnoughWater.setVisibility(View.VISIBLE);
                    lineEnoughWater.setVisibility(View.VISIBLE);
                    secMainWater.setVisibility(View.VISIBLE);
                    lineMainWater.setVisibility(View.VISIBLE);
                    seclbl111.setVisibility(View.VISIBLE);
                    linelbl111.setVisibility(View.VISIBLE);
                    secSmallTank.setVisibility(View.VISIBLE);
                    lineSmallTank.setVisibility(View.VISIBLE);
                    secMediunTank.setVisibility(View.VISIBLE);
                    lineMediunTank.setVisibility(View.VISIBLE);
                    secLargeTank.setVisibility(View.VISIBLE);
                    lineLargeTank.setVisibility(View.VISIBLE);
                    secStoreDrink.setVisibility(View.VISIBLE);
                    lineStoreDrink.setVisibility(View.VISIBLE);
                    seclbl113.setVisibility(View.VISIBLE);
                    linelbl113.setVisibility(View.VISIBLE);
                    secContainOpenCov.setVisibility(View.VISIBLE);
                    lineContainOpenCov.setVisibility(View.VISIBLE);
                    secContainOpenNotCov.setVisibility(View.VISIBLE);
                    lineContainOpenNotCov.setVisibility(View.VISIBLE);
                    secContainOpenDK.setVisibility(View.VISIBLE);
                    lineContainOpenDK.setVisibility(View.VISIBLE);
                    secRecoverWater.setVisibility(View.VISIBLE);
                    lineRecoverWater.setVisibility(View.VISIBLE);
                    secLessDanger.setVisibility(View.VISIBLE);
                    lineLessDanger.setVisibility(View.VISIBLE);
                    secMakeSafer.setVisibility(View.VISIBLE);
                    lineMakeSafer.setVisibility(View.VISIBLE);
                    secToilet.setVisibility(View.VISIBLE);
                    lineToilet.setVisibility(View.VISIBLE);
                    secToiletShrd.setVisibility(View.VISIBLE);
                    lineToiletShrd.setVisibility(View.VISIBLE);
                    secToiletUseNo.setVisibility(View.VISIBLE);
                    lineToiletUseNo.setVisibility(View.VISIBLE);
                    secToiletUseNoDk.setVisibility(View.VISIBLE);
                    lineToiletUseNoDk.setVisibility(View.VISIBLE);
                    secToiletLoc.setVisibility(View.VISIBLE);
                    lineToiletLoc.setVisibility(View.VISIBLE);
                    secContentEmp.setVisibility(View.VISIBLE);
                    lineContentEmp.setVisibility(View.VISIBLE);
                    secBowelMov.setVisibility(View.VISIBLE);
                    lineBowelMov.setVisibility(View.VISIBLE);
                    secLiquidWaste.setVisibility(View.VISIBLE);
                    lineLiquidWaste.setVisibility(View.VISIBLE);
                    secSolidWasteMethod.setVisibility(View.VISIBLE);
                    lineSolidWasteMethod.setVisibility(View.VISIBLE);
                    secHandWash.setVisibility(View.VISIBLE);
                    lineHandWash.setVisibility(View.VISIBLE);
                    secShowWash.setVisibility(View.VISIBLE);
                    lineShowWash.setVisibility(View.VISIBLE);
                    secAvailableWat.setVisibility(View.VISIBLE);
                    lineAvailableWat.setVisibility(View.VISIBLE);
                    secAvailableSoap.setVisibility(View.VISIBLE);
                    lineAvailableSoap.setVisibility(View.VISIBLE);
                    secCookDvc.setVisibility(View.VISIBLE);
                    lineCookDvc.setVisibility(View.VISIBLE);
                    secCookFuel.setVisibility(View.VISIBLE);
                    lineCookFuel.setVisibility(View.VISIBLE);
                    secCookPlc.setVisibility(View.VISIBLE);
                    lineCookPlc.setVisibility(View.VISIBLE);
                    secFloor.setVisibility(View.VISIBLE);
                    lineFloor.setVisibility(View.VISIBLE);
                    secGroundMat.setVisibility(View.VISIBLE);
                    lineGroundMat.setVisibility(View.VISIBLE);
                    secRoof.setVisibility(View.VISIBLE);
                    lineRoof.setVisibility(View.VISIBLE);
                    secSmokeInside.setVisibility(View.VISIBLE);
                    lineSmokeInside.setVisibility(View.VISIBLE);
                    secFreqSmoke.setVisibility(View.VISIBLE);
                    lineFreqSmoke.setVisibility(View.VISIBLE);
                    secWall.setVisibility(View.VISIBLE);
                    lineWall.setVisibility(View.VISIBLE);
                    secRoomSleep.setVisibility(View.VISIBLE);
                    lineRoomSleep.setVisibility(View.VISIBLE);
                    secRoomSleepDk.setVisibility(View.VISIBLE);
                    lineRoomSleepDk.setVisibility(View.VISIBLE);
                    secElecNight.setVisibility(View.VISIBLE);
                    lineElecNight.setVisibility(View.VISIBLE);
                    secHomesteadAny.setVisibility(View.VISIBLE);
                    lineHomesteadAny.setVisibility(View.VISIBLE);
                    secOthLand.setVisibility(View.VISIBLE);
                    lineOthLand.setVisibility(View.VISIBLE);
                    secArea.setVisibility(View.VISIBLE);
                    lineArea.setVisibility(View.VISIBLE);
                    secIncomeMo.setVisibility(View.VISIBLE);
                    lineIncomeMo.setVisibility(View.VISIBLE);
                    secExpenses.setVisibility(View.VISIBLE);
                    lineExpenses.setVisibility(View.VISIBLE);
                    secBankAcc.setVisibility(View.VISIBLE);
                    lineBankAcc.setVisibility(View.VISIBLE);
                    secSprayInt.setVisibility(View.VISIBLE);
                    lineSprayInt.setVisibility(View.VISIBLE);
                    secMosqNet.setVisibility(View.VISIBLE);
                    lineMosqNet.setVisibility(View.VISIBLE);
                    secNetOwned.setVisibility(View.VISIBLE);
                    lineNetOwned.setVisibility(View.VISIBLE);
                    secMedHome.setVisibility(View.VISIBLE);
                    lineMedHome.setVisibility(View.VISIBLE);
                    seclbl119.setVisibility(View.VISIBLE);
                    linelbl119.setVisibility(View.VISIBLE);
                    secMedTypeAM.setVisibility(View.VISIBLE);
                    lineMedTypeAM.setVisibility(View.VISIBLE);
                    secMedTypeAB.setVisibility(View.VISIBLE);
                    lineMedTypeAB.setVisibility(View.VISIBLE);
                    secMedTypeDK.setVisibility(View.VISIBLE);
                    lineMedTypeDK.setVisibility(View.VISIBLE);
//                    seclbl1110.setVisibility(View.VISIBLE);
//                    linelbl1110.setVisibility(View.VISIBLE);
//                    secAntimalarAL.setVisibility(View.VISIBLE);
//                    lineAntimalarAL.setVisibility(View.VISIBLE);
//                    secAntimalarASAQ.setVisibility(View.VISIBLE);
//                    lineAntimalarASAQ.setVisibility(View.VISIBLE);
//                    secAntimalarSP.setVisibility(View.VISIBLE);
//                    lineAntimalarSP.setVisibility(View.VISIBLE);
//                    secAntimalarOth.setVisibility(View.VISIBLE);
//                    lineAntimalarOth.setVisibility(View.VISIBLE);
                    seclbl1111.setVisibility(View.VISIBLE);
                    linelbl1111.setVisibility(View.VISIBLE);
                    secGetMedHosp.setVisibility(View.VISIBLE);
                    lineGetMedHosp.setVisibility(View.VISIBLE);
                    secGetMedCSCom.setVisibility(View.VISIBLE);
                    lineGetMedCSCom.setVisibility(View.VISIBLE);
                    secGetMedPrvCl.setVisibility(View.VISIBLE);
                    lineGetMedPrvCl.setVisibility(View.VISIBLE);
                    secGetMedPhar.setVisibility(View.VISIBLE);
                    lineGetMedPhar.setVisibility(View.VISIBLE);
                    secGetMedPD.setVisibility(View.VISIBLE);
                    lineGetMedPD.setVisibility(View.VISIBLE);
                    secGetMedCHW.setVisibility(View.VISIBLE);
                    lineGetMedCHW.setVisibility(View.VISIBLE);
                    secGetMedSS.setVisibility(View.VISIBLE);
                    lineGetMedSS.setVisibility(View.VISIBLE);
                    secGetMedOth.setVisibility(View.VISIBLE);
                    lineGetMedOth.setVisibility(View.VISIBLE);
                    secAComment.setVisibility(View.VISIBLE);
                    lineAComment.setVisibility(View.VISIBLE);
                    secComment.setVisibility(View.VISIBLE);
                    lineComment.setVisibility(View.VISIBLE);
                    seclblH12.setVisibility(View.VISIBLE);
                    linelblH12.setVisibility(View.VISIBLE);
                    seclblH121.setVisibility(View.VISIBLE);
                    linelblH121.setVisibility(View.VISIBLE);
                    secElectricity.setVisibility(View.VISIBLE);
                    lineElectricity.setVisibility(View.VISIBLE);
                    secSolarPlate.setVisibility(View.VISIBLE);
                    lineSolarPlate.setVisibility(View.VISIBLE);
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
                    secGasLamp.setVisibility(View.VISIBLE);
                    lineGasLamp.setVisibility(View.VISIBLE);
                    secPetroleum.setVisibility(View.VISIBLE);
                    linePetroleum.setVisibility(View.VISIBLE);
                    secMatt.setVisibility(View.VISIBLE);
                    lineMatt.setVisibility(View.VISIBLE);
                    secMats.setVisibility(View.VISIBLE);
                    lineMats.setVisibility(View.VISIBLE);
                    secCarpets.setVisibility(View.VISIBLE);
                    lineCarpets.setVisibility(View.VISIBLE);
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
                    secTV5.setVisibility(View.VISIBLE);
                    lineTV5.setVisibility(View.VISIBLE);
                    secChannel.setVisibility(View.VISIBLE);
                    lineChannel.setVisibility(View.VISIBLE);
                    secRadio.setVisibility(View.VISIBLE);
                    lineRadio.setVisibility(View.VISIBLE);
                    secDVD.setVisibility(View.VISIBLE);
                    lineDVD.setVisibility(View.VISIBLE);
                    secVideo.setVisibility(View.VISIBLE);
                    lineVideo.setVisibility(View.VISIBLE);
                    secComputer.setVisibility(View.VISIBLE);
                    lineComputer.setVisibility(View.VISIBLE);
                    secLaptop.setVisibility(View.VISIBLE);
                    lineLaptop.setVisibility(View.VISIBLE);
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
                    secGrain.setVisibility(View.VISIBLE);
                    lineGrain.setVisibility(View.VISIBLE);
                    secRefrigerator.setVisibility(View.VISIBLE);
                    lineRefrigerator.setVisibility(View.VISIBLE);
                    secDeepFreezer.setVisibility(View.VISIBLE);
                    lineDeepFreezer.setVisibility(View.VISIBLE);
                    secStove.setVisibility(View.VISIBLE);
                    lineStove.setVisibility(View.VISIBLE);
                    secGasHob.setVisibility(View.VISIBLE);
                    lineGasHob.setVisibility(View.VISIBLE);
                    secImpCooker.setVisibility(View.VISIBLE);
                    lineImpCooker.setVisibility(View.VISIBLE);
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
                    secBirds.setVisibility(View.VISIBLE);
                    lineBirds.setVisibility(View.VISIBLE);
                    secDogs.setVisibility(View.VISIBLE);
                    lineDogs.setVisibility(View.VISIBLE);
                    secCats.setVisibility(View.VISIBLE);
                    lineCats.setVisibility(View.VISIBLE);
                    secFishNet.setVisibility(View.VISIBLE);
                    lineFishNet.setVisibility(View.VISIBLE);
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
         txtRnd.setText(ROUND);
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
         listWSDrink.add("21-Tube well or borehole/Drilling");
         listWSDrink.add("31-Dug well protected well");
         listWSDrink.add("32-Dug well unprotected well");
         listWSDrink.add("41-Water from spring: protected spring");
         listWSDrink.add("42-Water from spring: unprotected spring");
         listWSDrink.add("51-Rainwater collection");
         listWSDrink.add("61-Delivered water: tanker-truck");
         listWSDrink.add("71-Delivered water: cart with small tank/drum");
         listWSDrink.add("81-Surface water (river, stream, dam, lake, pond, canal, etc.)");
         listWSDrink.add("91-Packaged water: bottled water");
         listWSDrink.add("92-Packaged water: sachet/bag water");
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
//                    secWaterSource.setVisibility(View.GONE);
//                    lineWaterSource.setVisibility(View.GONE);
//                    rdogrpWaterSource.clearCheck();
//                    secFetchWaterM.setVisibility(View.GONE);
//                    lineFetchWaterM.setVisibility(View.GONE);
//                    txtFetchWaterM.setText("");
//                    secFetchWaterMDk.setVisibility(View.GONE);
//                    lineFetchWaterMDk.setVisibility(View.GONE);
//                    rdogrpFetchWaterMDk.clearCheck();
//                    secGetWater.setVisibility(View.GONE);
//                    lineGetWater.setVisibility(View.GONE);
//                    rdogrpGetWater.clearCheck();
//                    secGetWaterOth.setVisibility(View.GONE);
//                    lineGetWaterOth.setVisibility(View.GONE);
//                    txtGetWaterOth.setText("");
//                    secMemberID.setVisibility(View.GONE);
//                    lineMemberID.setVisibility(View.GONE);
//                    spnMemberID.setSelection(0);
//                    secBringWater.setVisibility(View.GONE);
//                    lineBringWater.setVisibility(View.GONE);
//                    txtBringWater.setText("");
//                    secBringWaterDk.setVisibility(View.GONE);
//                    lineBringWaterDk.setVisibility(View.GONE);
//                    rdogrpBringWaterDk.clearCheck();
//                    secSomeone.setVisibility(View.GONE);
//                    lineSomeone.setVisibility(View.GONE);
//                    rdogrpSomeone.clearCheck();
//                    secSecondPers.setVisibility(View.GONE);
//                    lineSecondPers.setVisibility(View.GONE);
//                    rdogrpSecondPers.clearCheck();
//                    secSecondPersOth.setVisibility(View.GONE);
//                    lineSecondPersOth.setVisibility(View.GONE);
//                    txtSecondPersOth.setText("");
//                    secMemberID2nd.setVisibility(View.GONE);
//                    lineMemberID2nd.setVisibility(View.GONE);
//                    spnMemberID2nd.setSelection(0);
//                    secEnoughWater.setVisibility(View.GONE);
//                    lineEnoughWater.setVisibility(View.GONE);
//                    rdogrpEnoughWater.clearCheck();
//                    secMainWater.setVisibility(View.GONE);
//                    lineMainWater.setVisibility(View.GONE);
//                    spnMainWater.setSelection(0);
//                    secMainWaterOth.setVisibility(View.GONE);
//                    lineMainWaterOth.setVisibility(View.GONE);
//                    txtMainWaterOth.setText("");
//                    seclbl111.setVisibility(View.GONE);
//                    linelbl111.setVisibility(View.GONE);
//                    secSmallTank.setVisibility(View.GONE);
//                    lineSmallTank.setVisibility(View.GONE);
//                    rdogrpSmallTank.clearCheck();
//                    secMediunTank.setVisibility(View.GONE);
//                    lineMediunTank.setVisibility(View.GONE);
//                    rdogrpMediunTank.clearCheck();
//                    secLargeTank.setVisibility(View.GONE);
//                    lineLargeTank.setVisibility(View.GONE);
//                    rdogrpLargeTank.clearCheck();
//                    secStoreDrink.setVisibility(View.GONE);
//                    lineStoreDrink.setVisibility(View.GONE);
//                    rdogrpStoreDrink.clearCheck();
//                    seclbl113.setVisibility(View.GONE);
//                    linelbl113.setVisibility(View.GONE);
//                    secContainOpenCov.setVisibility(View.GONE);
//                    lineContainOpenCov.setVisibility(View.GONE);
//                    chkContainOpenCov.setChecked(false);
//                    secContainOpenNotCov.setVisibility(View.GONE);
//                    lineContainOpenNotCov.setVisibility(View.GONE);
//                    chkContainOpenNotCov.setChecked(false);
//                    secContainOpenDK.setVisibility(View.GONE);
//                    lineContainOpenDK.setVisibility(View.GONE);
//                    chkContainOpenDK.setChecked(false);
//                    secRecoverWater.setVisibility(View.GONE);
//                    lineRecoverWater.setVisibility(View.GONE);
//                    rdogrpRecoverWater.clearCheck();
//                    secRecoverWaterOth.setVisibility(View.GONE);
//                    lineRecoverWaterOth.setVisibility(View.GONE);
//                    txtRecoverWaterOth.setText("");
//                    secLessDanger.setVisibility(View.GONE);
//                    lineLessDanger.setVisibility(View.GONE);
//                    rdogrpLessDanger.clearCheck();
//                    secMakeSafer.setVisibility(View.GONE);
//                    lineMakeSafer.setVisibility(View.GONE);
//                    spnMakeSafer.setSelection(0);
//                    secMakeSaferOth.setVisibility(View.GONE);
//                    lineMakeSaferOth.setVisibility(View.GONE);
//                    txtMakeSaferOth.setText("");
                 }
                 else
                 {
                       secWSDrinkOth.setVisibility(View.GONE);
                       lineWSDrinkOth.setVisibility(View.GONE);
                       txtWSDrinkOth.setText("");
//                    secWaterSource.setVisibility(View.VISIBLE);
//                    lineWaterSource.setVisibility(View.VISIBLE);
//                    secFetchWaterM.setVisibility(View.VISIBLE);
//                    lineFetchWaterM.setVisibility(View.VISIBLE);
//                    secFetchWaterMDk.setVisibility(View.VISIBLE);
//                    lineFetchWaterMDk.setVisibility(View.VISIBLE);
//                    secGetWater.setVisibility(View.VISIBLE);
//                    lineGetWater.setVisibility(View.VISIBLE);
//                    secGetWaterOth.setVisibility(View.VISIBLE);
//                    lineGetWaterOth.setVisibility(View.VISIBLE);
//                    secMemberID.setVisibility(View.VISIBLE);
//                    lineMemberID.setVisibility(View.VISIBLE);
//                    secBringWater.setVisibility(View.VISIBLE);
//                    lineBringWater.setVisibility(View.VISIBLE);
//                    secBringWaterDk.setVisibility(View.VISIBLE);
//                    lineBringWaterDk.setVisibility(View.VISIBLE);
//                    secSomeone.setVisibility(View.VISIBLE);
//                    lineSomeone.setVisibility(View.VISIBLE);
//                    secSecondPers.setVisibility(View.VISIBLE);
//                    lineSecondPers.setVisibility(View.VISIBLE);
//                    secSecondPersOth.setVisibility(View.VISIBLE);
//                    lineSecondPersOth.setVisibility(View.VISIBLE);
//                    secMemberID2nd.setVisibility(View.VISIBLE);
//                    lineMemberID2nd.setVisibility(View.VISIBLE);
//                    secEnoughWater.setVisibility(View.VISIBLE);
//                    lineEnoughWater.setVisibility(View.VISIBLE);
//                    secMainWater.setVisibility(View.VISIBLE);
//                    lineMainWater.setVisibility(View.VISIBLE);
//                    secMainWaterOth.setVisibility(View.VISIBLE);
//                    lineMainWaterOth.setVisibility(View.VISIBLE);
//                    seclbl111.setVisibility(View.VISIBLE);
//                    linelbl111.setVisibility(View.VISIBLE);
//                    secSmallTank.setVisibility(View.VISIBLE);
//                    lineSmallTank.setVisibility(View.VISIBLE);
//                    secMediunTank.setVisibility(View.VISIBLE);
//                    lineMediunTank.setVisibility(View.VISIBLE);
//                    secLargeTank.setVisibility(View.VISIBLE);
//                    lineLargeTank.setVisibility(View.VISIBLE);
//                    secStoreDrink.setVisibility(View.VISIBLE);
//                    lineStoreDrink.setVisibility(View.VISIBLE);
//                    seclbl113.setVisibility(View.VISIBLE);
//                    linelbl113.setVisibility(View.VISIBLE);
//                    secContainOpenCov.setVisibility(View.VISIBLE);
//                    lineContainOpenCov.setVisibility(View.VISIBLE);
//                    secContainOpenNotCov.setVisibility(View.VISIBLE);
//                    lineContainOpenNotCov.setVisibility(View.VISIBLE);
//                    secContainOpenDK.setVisibility(View.VISIBLE);
//                    lineContainOpenDK.setVisibility(View.VISIBLE);
//                    secRecoverWater.setVisibility(View.VISIBLE);
//                    lineRecoverWater.setVisibility(View.VISIBLE);
//                    secRecoverWaterOth.setVisibility(View.VISIBLE);
//                    lineRecoverWaterOth.setVisibility(View.VISIBLE);
//                    secLessDanger.setVisibility(View.VISIBLE);
//                    lineLessDanger.setVisibility(View.VISIBLE);
//                    secMakeSafer.setVisibility(View.VISIBLE);
//                    lineMakeSafer.setVisibility(View.VISIBLE);
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
         secWaterSource=(LinearLayout)findViewById(R.id.secWaterSource);
         lineWaterSource=(View)findViewById(R.id.lineWaterSource);
         VlblWaterSource = (TextView) findViewById(R.id.VlblWaterSource);
         rdogrpWaterSource = (RadioGroup) findViewById(R.id.rdogrpWaterSource);
         rdoWaterSource1 = (RadioButton) findViewById(R.id.rdoWaterSource1);
         rdoWaterSource2 = (RadioButton) findViewById(R.id.rdoWaterSource2);
         rdoWaterSource3 = (RadioButton) findViewById(R.id.rdoWaterSource3);
         rdoWaterSource4 = (RadioButton) findViewById(R.id.rdoWaterSource4);
         rdoWaterSource5 = (RadioButton) findViewById(R.id.rdoWaterSource5);
         secFetchWaterM=(LinearLayout)findViewById(R.id.secFetchWaterM);
         lineFetchWaterM=(View)findViewById(R.id.lineFetchWaterM);
         VlblFetchWaterM=(TextView) findViewById(R.id.VlblFetchWaterM);
         txtFetchWaterM=(EditText) findViewById(R.id.txtFetchWaterM);

        txtFetchWaterM.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(txtFetchWaterM.getText().toString().length() > 0){
                  if(rdoFetchWaterMDk1.isChecked() || rdoFetchWaterMDk2.isChecked()){
                     rdogrpFetchWaterMDk.clearCheck();
                  }
               }
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
        });

         secFetchWaterMDk=(LinearLayout)findViewById(R.id.secFetchWaterMDk);
         lineFetchWaterMDk=(View)findViewById(R.id.lineFetchWaterMDk);
         VlblFetchWaterMDk = (TextView) findViewById(R.id.VlblFetchWaterMDk);
         rdogrpFetchWaterMDk = (RadioGroup) findViewById(R.id.rdogrpFetchWaterMDk);
         rdoFetchWaterMDk1 = (RadioButton) findViewById(R.id.rdoFetchWaterMDk1);
         rdoFetchWaterMDk2 = (RadioButton) findViewById(R.id.rdoFetchWaterMDk2);

        rdogrpFetchWaterMDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
           @Override
           public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
              String rbData = "";
              RadioButton rb;
              String[] d_rdogrpFetchWaterMDk = new String[] {"98","99"};
              for (int i = 0; i < rdogrpFetchWaterMDk.getChildCount(); i++)
              {
                 rb = (RadioButton)rdogrpFetchWaterMDk.getChildAt(i);
                 if (rb.isChecked()) rbData = d_rdogrpFetchWaterMDk[i];
              }
              if(rbData.equalsIgnoreCase("98") || rbData.equalsIgnoreCase("99")) {
                 if (txtFetchWaterM.getText().toString().length() > 0) {
                    txtFetchWaterM.setText("");
                 }
              }
           }
           public void onNothingSelected(AdapterView<?> adapterView) {
              return;
           }
        });

        secGetWater=(LinearLayout)findViewById(R.id.secGetWater);
         lineGetWater=(View)findViewById(R.id.lineGetWater);
         VlblGetWater = (TextView) findViewById(R.id.VlblGetWater);
         rdogrpGetWater = (RadioGroup) findViewById(R.id.rdogrpGetWater);
         rdoGetWater1 = (RadioButton) findViewById(R.id.rdoGetWater1);
         rdoGetWater2 = (RadioButton) findViewById(R.id.rdoGetWater2);
         rdoGetWater3 = (RadioButton) findViewById(R.id.rdoGetWater3);
         rdogrpGetWater.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpGetWater = new String[] {"1","2","7"};
             for (int i = 0; i < rdogrpGetWater.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpGetWater.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpGetWater[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secMemberID.setVisibility(View.VISIBLE);
                lineMemberID.setVisibility(View.VISIBLE);
                secGetWaterOth.setVisibility(View.GONE);
                lineGetWaterOth.setVisibility(View.GONE);
                txtGetWaterOth.setText("");
             }
            else if(rbData.equalsIgnoreCase("7"))
            {
               secMemberID.setVisibility(View.GONE);
               lineMemberID.setVisibility(View.GONE);
               spnMemberID.setSelection(0);
               secGetWaterOth.setVisibility(View.VISIBLE);
               lineGetWaterOth.setVisibility(View.VISIBLE);
            }
             else
             {
                secMemberID.setVisibility(View.GONE);
                lineMemberID.setVisibility(View.GONE);
                spnMemberID.setSelection(0);
                secGetWaterOth.setVisibility(View.GONE);
                lineGetWaterOth.setVisibility(View.GONE);
                txtGetWaterOth.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secGetWaterOth=(LinearLayout)findViewById(R.id.secGetWaterOth);
         lineGetWaterOth=(View)findViewById(R.id.lineGetWaterOth);
         VlblGetWaterOth=(TextView) findViewById(R.id.VlblGetWaterOth);
         txtGetWaterOth=(AutoCompleteTextView) findViewById(R.id.txtGetWaterOth);
         txtGetWaterOth.setAdapter(C.getArrayAdapter("Select distinct GetWaterOth from "+ TableName +" order by GetWaterOth"));

         txtGetWaterOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtGetWaterOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtGetWaterOth.getRight() - txtGetWaterOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secMemberID=(LinearLayout)findViewById(R.id.secMemberID);
         lineMemberID=(View)findViewById(R.id.lineMemberID);
         VlblMemberID=(TextView) findViewById(R.id.VlblMemberID);
         spnMemberID=(Spinner) findViewById(R.id.spnMemberID);
//         List<String> listMemberID = new ArrayList<String>();
//
//         listMemberID.add("");
//         listMemberID.add("1-");
//         listMemberID.add("2-");
//         spnMemberID.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listMemberID)));

        spnMemberID.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "'"));


        secBringWater=(LinearLayout)findViewById(R.id.secBringWater);
         lineBringWater=(View)findViewById(R.id.lineBringWater);
         VlblBringWater=(TextView) findViewById(R.id.VlblBringWater);
         txtBringWater=(EditText) findViewById(R.id.txtBringWater);

        txtBringWater.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
              if(txtBringWater.getText().toString().length() > 0){
                 if(rdoBringWaterDk1.isChecked() || rdoBringWaterDk2.isChecked()){
                    rdogrpBringWaterDk.clearCheck();
                 }
              }
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
        });

         secBringWaterDk=(LinearLayout)findViewById(R.id.secBringWaterDk);
         lineBringWaterDk=(View)findViewById(R.id.lineBringWaterDk);
         VlblBringWaterDk = (TextView) findViewById(R.id.VlblBringWaterDk);
         rdogrpBringWaterDk = (RadioGroup) findViewById(R.id.rdogrpBringWaterDk);
         rdoBringWaterDk1 = (RadioButton) findViewById(R.id.rdoBringWaterDk1);
         rdoBringWaterDk2 = (RadioButton) findViewById(R.id.rdoBringWaterDk2);

        rdogrpBringWaterDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
           @Override
           public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
              String rbData = "";
              RadioButton rb;
              String[] d_rdogrpBringWaterDk = new String[] {"98","99"};
              for (int i = 0; i < rdogrpBringWaterDk.getChildCount(); i++)
              {
                 rb = (RadioButton)rdogrpBringWaterDk.getChildAt(i);
                 if (rb.isChecked()) rbData = d_rdogrpBringWaterDk[i];
              }

              if(rbData.equalsIgnoreCase("98") || rbData.equalsIgnoreCase("99")){
                 if(txtBringWater.getText().toString().length() > 0){
                    txtBringWater.setText("");
                 }
              }


           }
           public void onNothingSelected(AdapterView<?> adapterView) {
              return;
           }
        });


        secSomeone=(LinearLayout)findViewById(R.id.secSomeone);
         lineSomeone=(View)findViewById(R.id.lineSomeone);
         VlblSomeone = (TextView) findViewById(R.id.VlblSomeone);
         rdogrpSomeone = (RadioGroup) findViewById(R.id.rdogrpSomeone);
         rdoSomeone1 = (RadioButton) findViewById(R.id.rdoSomeone1);
         rdoSomeone2 = (RadioButton) findViewById(R.id.rdoSomeone2);
         rdogrpSomeone.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpSomeone = new String[] {"0","1"};
             for (int i = 0; i < rdogrpSomeone.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpSomeone.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpSomeone[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    secSecondPers.setVisibility(View.GONE);
                    lineSecondPers.setVisibility(View.GONE);
                    rdogrpSecondPers.clearCheck();
                    secSecondPersOth.setVisibility(View.GONE);
                    lineSecondPersOth.setVisibility(View.GONE);
                    txtSecondPersOth.setText("");
                    secMemberID2nd.setVisibility(View.GONE);
                    lineMemberID2nd.setVisibility(View.GONE);
                    spnMemberID2nd.setSelection(0);
                    secEnoughWater.setVisibility(View.GONE);
                    lineEnoughWater.setVisibility(View.GONE);
                    rdogrpEnoughWater.clearCheck();
                    secMainWater.setVisibility(View.GONE);
                    lineMainWater.setVisibility(View.GONE);
                    spnMainWater.setSelection(0);
                    secMainWaterOth.setVisibility(View.GONE);
                    lineMainWaterOth.setVisibility(View.GONE);
                    txtMainWaterOth.setText("");
                    seclbl111.setVisibility(View.GONE);
                    linelbl111.setVisibility(View.GONE);
                    secSmallTank.setVisibility(View.GONE);
                    lineSmallTank.setVisibility(View.GONE);
                    rdogrpSmallTank.clearCheck();
                    secMediunTank.setVisibility(View.GONE);
                    lineMediunTank.setVisibility(View.GONE);
                    rdogrpMediunTank.clearCheck();
                    secLargeTank.setVisibility(View.GONE);
                    lineLargeTank.setVisibility(View.GONE);
                    rdogrpLargeTank.clearCheck();
                    secStoreDrink.setVisibility(View.GONE);
                    lineStoreDrink.setVisibility(View.GONE);
                    rdogrpStoreDrink.clearCheck();
                    seclbl113.setVisibility(View.GONE);
                    linelbl113.setVisibility(View.GONE);
                    secContainOpenCov.setVisibility(View.GONE);
                    lineContainOpenCov.setVisibility(View.GONE);
                    chkContainOpenCov.setChecked(false);
                    secContainOpenNotCov.setVisibility(View.GONE);
                    lineContainOpenNotCov.setVisibility(View.GONE);
                    chkContainOpenNotCov.setChecked(false);
                    secContainOpenDK.setVisibility(View.GONE);
                    lineContainOpenDK.setVisibility(View.GONE);
                    chkContainOpenDK.setChecked(false);
                    secRecoverWater.setVisibility(View.GONE);
                    lineRecoverWater.setVisibility(View.GONE);
                    rdogrpRecoverWater.clearCheck();
                    secRecoverWaterOth.setVisibility(View.GONE);
                    lineRecoverWaterOth.setVisibility(View.GONE);
                    txtRecoverWaterOth.setText("");
                    secLessDanger.setVisibility(View.GONE);
                    lineLessDanger.setVisibility(View.GONE);
                    rdogrpLessDanger.clearCheck();
                    secMakeSafer.setVisibility(View.GONE);
                    lineMakeSafer.setVisibility(View.GONE);
                    spnMakeSafer.setSelection(0);
                    secMakeSaferOth.setVisibility(View.GONE);
                    lineMakeSaferOth.setVisibility(View.GONE);
                    txtMakeSaferOth.setText("");
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
                    secContentEmp.setVisibility(View.GONE);
                    lineContentEmp.setVisibility(View.GONE);
                    rdogrpContentEmp.clearCheck();
                    secContentEmpOth.setVisibility(View.GONE);
                    lineContentEmpOth.setVisibility(View.GONE);
                    txtContentEmpOth.setText("");
                    secBowelMov.setVisibility(View.GONE);
                    lineBowelMov.setVisibility(View.GONE);
                    spnBowelMov.setSelection(0);
                    secBowelMovOth.setVisibility(View.GONE);
                    lineBowelMovOth.setVisibility(View.GONE);
                    txtBowelMovOth.setText("");
                    secLiquidWaste.setVisibility(View.GONE);
                    lineLiquidWaste.setVisibility(View.GONE);
                    rdogrpLiquidWaste.clearCheck();
                    secLiquidWasteOth.setVisibility(View.GONE);
                    lineLiquidWasteOth.setVisibility(View.GONE);
                    txtLiquidWasteOth.setText("");
                    secSolidWasteMethod.setVisibility(View.GONE);
                    lineSolidWasteMethod.setVisibility(View.GONE);
                    spnSolidWasteMethod.setSelection(0);
                    secSolidWasteMethodOth.setVisibility(View.GONE);
                    lineSolidWasteMethodOth.setVisibility(View.GONE);
                    txtSolidWasteMethodOth.setText("");
                    secHandWash.setVisibility(View.GONE);
                    lineHandWash.setVisibility(View.GONE);
                    rdogrpHandWash.clearCheck();
                    secShowWash.setVisibility(View.GONE);
                    lineShowWash.setVisibility(View.GONE);
                    rdogrpShowWash.clearCheck();
                    secAvailableWat.setVisibility(View.GONE);
                    lineAvailableWat.setVisibility(View.GONE);
                    rdogrpAvailableWat.clearCheck();
                    secAvailableSoap.setVisibility(View.GONE);
                    lineAvailableSoap.setVisibility(View.GONE);
                    rdogrpAvailableSoap.clearCheck();
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
                    secGroundMat.setVisibility(View.GONE);
                    lineGroundMat.setVisibility(View.GONE);
                    spnGroundMat.setSelection(0);
                    secGroundMatOth.setVisibility(View.GONE);
                    lineGroundMatOth.setVisibility(View.GONE);
                    txtGroundMatOth.setText("");
                    secRoof.setVisibility(View.GONE);
                    lineRoof.setVisibility(View.GONE);
                    spnRoof.setSelection(0);
                    secRoofOth.setVisibility(View.GONE);
                    lineRoofOth.setVisibility(View.GONE);
                    txtRoofOth.setText("");
                    secSmokeInside.setVisibility(View.GONE);
                    lineSmokeInside.setVisibility(View.GONE);
                    rdogrpSmokeInside.clearCheck();
                    secFreqSmoke.setVisibility(View.GONE);
                    lineFreqSmoke.setVisibility(View.GONE);
                    rdogrpFreqSmoke.clearCheck();
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
                    secElecNight.setVisibility(View.GONE);
                    lineElecNight.setVisibility(View.GONE);
                    spnElecNight.setSelection(0);
                    secElecNightOth.setVisibility(View.GONE);
                    lineElecNightOth.setVisibility(View.GONE);
                    txtElecNightOth.setText("");
                    secHomesteadAny.setVisibility(View.GONE);
                    lineHomesteadAny.setVisibility(View.GONE);
                    rdogrpHomesteadAny.clearCheck();
                    secOthLand.setVisibility(View.GONE);
                    lineOthLand.setVisibility(View.GONE);
                    rdogrpOthLand.clearCheck();
                    secArea.setVisibility(View.GONE);
                    lineArea.setVisibility(View.GONE);
                    txtArea.setText("");
                    secIncomeMo.setVisibility(View.GONE);
                    lineIncomeMo.setVisibility(View.GONE);
                    rdogrpIncomeMo.clearCheck();
                    secExpenses.setVisibility(View.GONE);
                    lineExpenses.setVisibility(View.GONE);
                    rdogrpExpenses.clearCheck();
                    secBankAcc.setVisibility(View.GONE);
                    lineBankAcc.setVisibility(View.GONE);
                    rdogrpBankAcc.clearCheck();
                    secSprayInt.setVisibility(View.GONE);
                    lineSprayInt.setVisibility(View.GONE);
                    rdogrpSprayInt.clearCheck();
                    secMosqNet.setVisibility(View.GONE);
                    lineMosqNet.setVisibility(View.GONE);
                    rdogrpMosqNet.clearCheck();
                    secNetOwned.setVisibility(View.GONE);
                    lineNetOwned.setVisibility(View.GONE);
                    txtNetOwned.setText("");
                    secMedHome.setVisibility(View.GONE);
                    lineMedHome.setVisibility(View.GONE);
                    rdogrpMedHome.clearCheck();
             }
             else
             {
                    secSecondPers.setVisibility(View.VISIBLE);
                    lineSecondPers.setVisibility(View.VISIBLE);
                    secMemberID2nd.setVisibility(View.VISIBLE);
                    lineMemberID2nd.setVisibility(View.VISIBLE);
                    secEnoughWater.setVisibility(View.VISIBLE);
                    lineEnoughWater.setVisibility(View.VISIBLE);
                    secMainWater.setVisibility(View.VISIBLE);
                    lineMainWater.setVisibility(View.VISIBLE);
                    seclbl111.setVisibility(View.VISIBLE);
                    linelbl111.setVisibility(View.VISIBLE);
                    secSmallTank.setVisibility(View.VISIBLE);
                    lineSmallTank.setVisibility(View.VISIBLE);
                    secMediunTank.setVisibility(View.VISIBLE);
                    lineMediunTank.setVisibility(View.VISIBLE);
                    secLargeTank.setVisibility(View.VISIBLE);
                    lineLargeTank.setVisibility(View.VISIBLE);
                    secStoreDrink.setVisibility(View.VISIBLE);
                    lineStoreDrink.setVisibility(View.VISIBLE);
                    seclbl113.setVisibility(View.VISIBLE);
                    linelbl113.setVisibility(View.VISIBLE);
                    secContainOpenCov.setVisibility(View.VISIBLE);
                    lineContainOpenCov.setVisibility(View.VISIBLE);
                    secContainOpenNotCov.setVisibility(View.VISIBLE);
                    lineContainOpenNotCov.setVisibility(View.VISIBLE);
                    secContainOpenDK.setVisibility(View.VISIBLE);
                    lineContainOpenDK.setVisibility(View.VISIBLE);
                    secRecoverWater.setVisibility(View.VISIBLE);
                    lineRecoverWater.setVisibility(View.VISIBLE);
                    secLessDanger.setVisibility(View.VISIBLE);
                    lineLessDanger.setVisibility(View.VISIBLE);
                    secMakeSafer.setVisibility(View.VISIBLE);
                    lineMakeSafer.setVisibility(View.VISIBLE);
                    secToilet.setVisibility(View.VISIBLE);
                    lineToilet.setVisibility(View.VISIBLE);
                    secToiletShrd.setVisibility(View.VISIBLE);
                    lineToiletShrd.setVisibility(View.VISIBLE);
                    secToiletUseNo.setVisibility(View.VISIBLE);
                    lineToiletUseNo.setVisibility(View.VISIBLE);
                    secToiletUseNoDk.setVisibility(View.VISIBLE);
                    lineToiletUseNoDk.setVisibility(View.VISIBLE);
                    secToiletLoc.setVisibility(View.VISIBLE);
                    lineToiletLoc.setVisibility(View.VISIBLE);
                    secContentEmp.setVisibility(View.VISIBLE);
                    lineContentEmp.setVisibility(View.VISIBLE);
                    secBowelMov.setVisibility(View.VISIBLE);
                    lineBowelMov.setVisibility(View.VISIBLE);
                    secLiquidWaste.setVisibility(View.VISIBLE);
                    lineLiquidWaste.setVisibility(View.VISIBLE);
                    secSolidWasteMethod.setVisibility(View.VISIBLE);
                    lineSolidWasteMethod.setVisibility(View.VISIBLE);
                    secHandWash.setVisibility(View.VISIBLE);
                    lineHandWash.setVisibility(View.VISIBLE);
                    secShowWash.setVisibility(View.VISIBLE);
                    lineShowWash.setVisibility(View.VISIBLE);
                    secAvailableWat.setVisibility(View.VISIBLE);
                    lineAvailableWat.setVisibility(View.VISIBLE);
                    secAvailableSoap.setVisibility(View.VISIBLE);
                    lineAvailableSoap.setVisibility(View.VISIBLE);
                    secCookDvc.setVisibility(View.VISIBLE);
                    lineCookDvc.setVisibility(View.VISIBLE);
                    secCookFuel.setVisibility(View.VISIBLE);
                    lineCookFuel.setVisibility(View.VISIBLE);
                    secCookPlc.setVisibility(View.VISIBLE);
                    lineCookPlc.setVisibility(View.VISIBLE);
                    secFloor.setVisibility(View.VISIBLE);
                    lineFloor.setVisibility(View.VISIBLE);
                    secGroundMat.setVisibility(View.VISIBLE);
                    lineGroundMat.setVisibility(View.VISIBLE);
                    secRoof.setVisibility(View.VISIBLE);
                    lineRoof.setVisibility(View.VISIBLE);
                    secSmokeInside.setVisibility(View.VISIBLE);
                    lineSmokeInside.setVisibility(View.VISIBLE);
                    secFreqSmoke.setVisibility(View.VISIBLE);
                    lineFreqSmoke.setVisibility(View.VISIBLE);
                    secWall.setVisibility(View.VISIBLE);
                    lineWall.setVisibility(View.VISIBLE);
                    secRoomSleep.setVisibility(View.VISIBLE);
                    lineRoomSleep.setVisibility(View.VISIBLE);
                    secRoomSleepDk.setVisibility(View.VISIBLE);
                    lineRoomSleepDk.setVisibility(View.VISIBLE);
                    secElecNight.setVisibility(View.VISIBLE);
                    lineElecNight.setVisibility(View.VISIBLE);
                    secHomesteadAny.setVisibility(View.VISIBLE);
                    lineHomesteadAny.setVisibility(View.VISIBLE);
                    secOthLand.setVisibility(View.VISIBLE);
                    lineOthLand.setVisibility(View.VISIBLE);
                    secArea.setVisibility(View.VISIBLE);
                    lineArea.setVisibility(View.VISIBLE);
                    secIncomeMo.setVisibility(View.VISIBLE);
                    lineIncomeMo.setVisibility(View.VISIBLE);
                    secExpenses.setVisibility(View.VISIBLE);
                    lineExpenses.setVisibility(View.VISIBLE);
                    secBankAcc.setVisibility(View.VISIBLE);
                    lineBankAcc.setVisibility(View.VISIBLE);
                    secSprayInt.setVisibility(View.VISIBLE);
                    lineSprayInt.setVisibility(View.VISIBLE);
                    secMosqNet.setVisibility(View.VISIBLE);
                    lineMosqNet.setVisibility(View.VISIBLE);
                    secNetOwned.setVisibility(View.VISIBLE);
                    lineNetOwned.setVisibility(View.VISIBLE);
                    secMedHome.setVisibility(View.VISIBLE);
                    lineMedHome.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secSecondPers=(LinearLayout)findViewById(R.id.secSecondPers);
         lineSecondPers=(View)findViewById(R.id.lineSecondPers);
         VlblSecondPers = (TextView) findViewById(R.id.VlblSecondPers);
         rdogrpSecondPers = (RadioGroup) findViewById(R.id.rdogrpSecondPers);
         rdoSecondPers1 = (RadioButton) findViewById(R.id.rdoSecondPers1);
         rdoSecondPers2 = (RadioButton) findViewById(R.id.rdoSecondPers2);
         rdoSecondPers3 = (RadioButton) findViewById(R.id.rdoSecondPers3);
         rdogrpSecondPers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpSecondPers = new String[] {"1","2","7"};
             for (int i = 0; i < rdogrpSecondPers.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpSecondPers.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpSecondPers[i];
             }

            if(rbData.equalsIgnoreCase("1"))
            {
               secMemberID2nd.setVisibility(View.VISIBLE);
               lineMemberID2nd.setVisibility(View.VISIBLE);
               secSecondPersOth.setVisibility(View.GONE);
               lineSecondPersOth.setVisibility(View.GONE);
               txtSecondPersOth.setText("");
            }
            else if(rbData.equalsIgnoreCase("7"))
            {
               secMemberID2nd.setVisibility(View.GONE);
               lineMemberID2nd.setVisibility(View.GONE);
               spnMemberID2nd.setSelection(0);
               secSecondPersOth.setVisibility(View.VISIBLE);
               lineSecondPersOth.setVisibility(View.VISIBLE);
            }
            else
            {
               secMemberID2nd.setVisibility(View.GONE);
               lineMemberID2nd.setVisibility(View.GONE);
               spnMemberID2nd.setSelection(0);
               secSecondPersOth.setVisibility(View.GONE);
               lineSecondPersOth.setVisibility(View.GONE);
               txtSecondPersOth.setText("");
            }

            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secSecondPersOth=(LinearLayout)findViewById(R.id.secSecondPersOth);
         lineSecondPersOth=(View)findViewById(R.id.lineSecondPersOth);
         VlblSecondPersOth=(TextView) findViewById(R.id.VlblSecondPersOth);
         txtSecondPersOth=(AutoCompleteTextView) findViewById(R.id.txtSecondPersOth);
         txtSecondPersOth.setAdapter(C.getArrayAdapter("Select distinct SecondPersOth from "+ TableName +" order by SecondPersOth"));

         txtSecondPersOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtSecondPersOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtSecondPersOth.getRight() - txtSecondPersOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secMemberID2nd=(LinearLayout)findViewById(R.id.secMemberID2nd);
         lineMemberID2nd=(View)findViewById(R.id.lineMemberID2nd);
         VlblMemberID2nd=(TextView) findViewById(R.id.VlblMemberID2nd);
         spnMemberID2nd=(Spinner) findViewById(R.id.spnMemberID2nd);
//         List<String> listMemberID2nd = new ArrayList<String>();
//
//         listMemberID2nd.add("");
//         listMemberID2nd.add("1-");
//         listMemberID2nd.add("2-");
//         spnMemberID2nd.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listMemberID2nd)));

        spnMemberID2nd.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from tmpMember Where HHID='" + HHID + "'"));


        secEnoughWater=(LinearLayout)findViewById(R.id.secEnoughWater);
         lineEnoughWater=(View)findViewById(R.id.lineEnoughWater);
         VlblEnoughWater = (TextView) findViewById(R.id.VlblEnoughWater);
         rdogrpEnoughWater = (RadioGroup) findViewById(R.id.rdogrpEnoughWater);
         rdoEnoughWater1 = (RadioButton) findViewById(R.id.rdoEnoughWater1);
         rdoEnoughWater2 = (RadioButton) findViewById(R.id.rdoEnoughWater2);
         rdoEnoughWater3 = (RadioButton) findViewById(R.id.rdoEnoughWater3);
         secMainWater=(LinearLayout)findViewById(R.id.secMainWater);
         lineMainWater=(View)findViewById(R.id.lineMainWater);
         VlblMainWater=(TextView) findViewById(R.id.VlblMainWater);
         spnMainWater=(Spinner) findViewById(R.id.spnMainWater);
         List<String> listMainWater = new ArrayList<String>();
         
         listMainWater.add("");
         listMainWater.add("11-Piped water:into dwelling");
         listMainWater.add("12-Piped water:into compound or yard");
         listMainWater.add("13-Piped water:to neighbor");
         listMainWater.add("14-Piped water:to public tap/standpipe");
         listMainWater.add("21-Piped water:Tube well or borehole/Drilling");
         listMainWater.add("31-Dug well:protected well");
         listMainWater.add("32-Dug well:unprotected well");
         listMainWater.add("41-Water from spring:protected spring");
         listMainWater.add("42-Water from spring:unprotected spring");
         listMainWater.add("51-Rainwater collection");
         listMainWater.add("61-Delivered water:tanker-truck");
         listMainWater.add("71-Delivered water:cart with small tank/drum");
         listMainWater.add("81-Surface water (river, stream, dam, lake, pond, canal, etc.)");
         listMainWater.add("91-Packaged water:bottled water");
         listMainWater.add("92-Packaged water:sachet/bag water");
         listMainWater.add("93-Packaged water:Water kiosk");
         listMainWater.add("97-Other specify");
         listMainWater.add("98-Don't know");
         listMainWater.add("99-Refused to respond");
         spnMainWater.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listMainWater)));

         spnMainWater.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnMainWater.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnMainWater.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                    secMainWaterOth.setVisibility(View.VISIBLE);
                    lineMainWaterOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secMainWaterOth.setVisibility(View.GONE);
                    lineMainWaterOth.setVisibility(View.GONE);
                    txtMainWaterOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secMainWaterOth=(LinearLayout)findViewById(R.id.secMainWaterOth);
         lineMainWaterOth=(View)findViewById(R.id.lineMainWaterOth);
         VlblMainWaterOth=(TextView) findViewById(R.id.VlblMainWaterOth);
         txtMainWaterOth=(AutoCompleteTextView) findViewById(R.id.txtMainWaterOth);
         txtMainWaterOth.setAdapter(C.getArrayAdapter("Select distinct MainWaterOth from "+ TableName +" order by MainWaterOth"));

         txtMainWaterOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtMainWaterOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtMainWaterOth.getRight() - txtMainWaterOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         seclbl111=(LinearLayout)findViewById(R.id.seclbl111);
         linelbl111=(View)findViewById(R.id.linelbl111);
         secSmallTank=(LinearLayout)findViewById(R.id.secSmallTank);
         lineSmallTank=(View)findViewById(R.id.lineSmallTank);
         VlblSmallTank = (TextView) findViewById(R.id.VlblSmallTank);
         rdogrpSmallTank = (RadioGroup) findViewById(R.id.rdogrpSmallTank);
         rdoSmallTank1 = (RadioButton) findViewById(R.id.rdoSmallTank1);
         rdoSmallTank2 = (RadioButton) findViewById(R.id.rdoSmallTank2);
         rdoSmallTank3 = (RadioButton) findViewById(R.id.rdoSmallTank3);
         secMediunTank=(LinearLayout)findViewById(R.id.secMediunTank);
         lineMediunTank=(View)findViewById(R.id.lineMediunTank);
         VlblMediunTank = (TextView) findViewById(R.id.VlblMediunTank);
         rdogrpMediunTank = (RadioGroup) findViewById(R.id.rdogrpMediunTank);
         rdoMediunTank1 = (RadioButton) findViewById(R.id.rdoMediunTank1);
         rdoMediunTank2 = (RadioButton) findViewById(R.id.rdoMediunTank2);
         rdoMediunTank3 = (RadioButton) findViewById(R.id.rdoMediunTank3);
         secLargeTank=(LinearLayout)findViewById(R.id.secLargeTank);
         lineLargeTank=(View)findViewById(R.id.lineLargeTank);
         VlblLargeTank = (TextView) findViewById(R.id.VlblLargeTank);
         rdogrpLargeTank = (RadioGroup) findViewById(R.id.rdogrpLargeTank);
         rdoLargeTank1 = (RadioButton) findViewById(R.id.rdoLargeTank1);
         rdoLargeTank2 = (RadioButton) findViewById(R.id.rdoLargeTank2);
         rdoLargeTank3 = (RadioButton) findViewById(R.id.rdoLargeTank3);
         secStoreDrink=(LinearLayout)findViewById(R.id.secStoreDrink);
         lineStoreDrink=(View)findViewById(R.id.lineStoreDrink);
         VlblStoreDrink = (TextView) findViewById(R.id.VlblStoreDrink);
         rdogrpStoreDrink = (RadioGroup) findViewById(R.id.rdogrpStoreDrink);
         rdoStoreDrink1 = (RadioButton) findViewById(R.id.rdoStoreDrink1);
         rdoStoreDrink2 = (RadioButton) findViewById(R.id.rdoStoreDrink2);
         rdogrpStoreDrink.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpStoreDrink = new String[] {"0","1"};
             for (int i = 0; i < rdogrpStoreDrink.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpStoreDrink.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpStoreDrink[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    seclbl113.setVisibility(View.GONE);
                    linelbl113.setVisibility(View.GONE);
                    secContainOpenCov.setVisibility(View.GONE);
                    lineContainOpenCov.setVisibility(View.GONE);
                    chkContainOpenCov.setChecked(false);
                    secContainOpenNotCov.setVisibility(View.GONE);
                    lineContainOpenNotCov.setVisibility(View.GONE);
                    chkContainOpenNotCov.setChecked(false);
                    secContainOpenDK.setVisibility(View.GONE);
                    lineContainOpenDK.setVisibility(View.GONE);
                    chkContainOpenDK.setChecked(false);
             }
             else
             {
                    seclbl113.setVisibility(View.VISIBLE);
                    linelbl113.setVisibility(View.VISIBLE);
                    secContainOpenCov.setVisibility(View.VISIBLE);
                    lineContainOpenCov.setVisibility(View.VISIBLE);
                    secContainOpenNotCov.setVisibility(View.VISIBLE);
                    lineContainOpenNotCov.setVisibility(View.VISIBLE);
                    secContainOpenDK.setVisibility(View.VISIBLE);
                    lineContainOpenDK.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclbl113=(LinearLayout)findViewById(R.id.seclbl113);
         linelbl113=(View)findViewById(R.id.linelbl113);
         secContainOpenCov=(LinearLayout)findViewById(R.id.secContainOpenCov);
         lineContainOpenCov=(View)findViewById(R.id.lineContainOpenCov);
         VlblContainOpenCov=(TextView) findViewById(R.id.VlblContainOpenCov);
         chkContainOpenCov=(CheckBox) findViewById(R.id.chkContainOpenCov);
         secContainOpenNotCov=(LinearLayout)findViewById(R.id.secContainOpenNotCov);
         lineContainOpenNotCov=(View)findViewById(R.id.lineContainOpenNotCov);
         VlblContainOpenNotCov=(TextView) findViewById(R.id.VlblContainOpenNotCov);
         chkContainOpenNotCov=(CheckBox) findViewById(R.id.chkContainOpenNotCov);
         secContainOpenDK=(LinearLayout)findViewById(R.id.secContainOpenDK);
         lineContainOpenDK=(View)findViewById(R.id.lineContainOpenDK);
         VlblContainOpenDK=(TextView) findViewById(R.id.VlblContainOpenDK);
         chkContainOpenDK=(CheckBox) findViewById(R.id.chkContainOpenDK);
         secRecoverWater=(LinearLayout)findViewById(R.id.secRecoverWater);
         lineRecoverWater=(View)findViewById(R.id.lineRecoverWater);
         VlblRecoverWater = (TextView) findViewById(R.id.VlblRecoverWater);
         rdogrpRecoverWater = (RadioGroup) findViewById(R.id.rdogrpRecoverWater);
         rdoRecoverWater1 = (RadioButton) findViewById(R.id.rdoRecoverWater1);
         rdoRecoverWater2 = (RadioButton) findViewById(R.id.rdoRecoverWater2);
         rdoRecoverWater3 = (RadioButton) findViewById(R.id.rdoRecoverWater3);
         rdoRecoverWater4 = (RadioButton) findViewById(R.id.rdoRecoverWater4);
         rdogrpRecoverWater.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpRecoverWater = new String[] {"1","2","3","7"};
             for (int i = 0; i < rdogrpRecoverWater.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpRecoverWater.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpRecoverWater[i];
             }

             if(rbData.equalsIgnoreCase("7"))
             {
                secRecoverWaterOth.setVisibility(View.VISIBLE);
                lineRecoverWaterOth.setVisibility(View.VISIBLE);
             }
             else
             {
                secRecoverWaterOth.setVisibility(View.GONE);
                lineRecoverWaterOth.setVisibility(View.GONE);
                txtRecoverWaterOth.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secRecoverWaterOth=(LinearLayout)findViewById(R.id.secRecoverWaterOth);
         lineRecoverWaterOth=(View)findViewById(R.id.lineRecoverWaterOth);
         VlblRecoverWaterOth=(TextView) findViewById(R.id.VlblRecoverWaterOth);
         txtRecoverWaterOth=(AutoCompleteTextView) findViewById(R.id.txtRecoverWaterOth);
         txtRecoverWaterOth.setAdapter(C.getArrayAdapter("Select distinct RecoverWaterOth from "+ TableName +" order by RecoverWaterOth"));

         txtRecoverWaterOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtRecoverWaterOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtRecoverWaterOth.getRight() - txtRecoverWaterOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secLessDanger=(LinearLayout)findViewById(R.id.secLessDanger);
         lineLessDanger=(View)findViewById(R.id.lineLessDanger);
         VlblLessDanger = (TextView) findViewById(R.id.VlblLessDanger);
         rdogrpLessDanger = (RadioGroup) findViewById(R.id.rdogrpLessDanger);
         rdoLessDanger1 = (RadioButton) findViewById(R.id.rdoLessDanger1);
         rdoLessDanger2 = (RadioButton) findViewById(R.id.rdoLessDanger2);
         rdoLessDanger3 = (RadioButton) findViewById(R.id.rdoLessDanger3);
         rdogrpLessDanger.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpLessDanger = new String[] {"0","1","8"};
             for (int i = 0; i < rdogrpLessDanger.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpLessDanger.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpLessDanger[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    secMakeSafer.setVisibility(View.GONE);
                    lineMakeSafer.setVisibility(View.GONE);
                    spnMakeSafer.setSelection(0);
                    secMakeSaferOth.setVisibility(View.GONE);
                    lineMakeSaferOth.setVisibility(View.GONE);
                    txtMakeSaferOth.setText("");
             }
             else if(rbData.equalsIgnoreCase("8"))
             {
                    secMakeSafer.setVisibility(View.GONE);
                    lineMakeSafer.setVisibility(View.GONE);
                    spnMakeSafer.setSelection(0);
                    secMakeSaferOth.setVisibility(View.GONE);
                    lineMakeSaferOth.setVisibility(View.GONE);
                    txtMakeSaferOth.setText("");
             }
             else
             {
                    secMakeSafer.setVisibility(View.VISIBLE);
                    lineMakeSafer.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secMakeSafer=(LinearLayout)findViewById(R.id.secMakeSafer);
         lineMakeSafer=(View)findViewById(R.id.lineMakeSafer);
         VlblMakeSafer=(TextView) findViewById(R.id.VlblMakeSafer);
         spnMakeSafer=(Spinner) findViewById(R.id.spnMakeSafer);
         List<String> listMakeSafer = new ArrayList<String>();
         
         listMakeSafer.add("");
         listMakeSafer.add("1-Boil (bouillir)");
         listMakeSafer.add("2-Add bleach/Chlorine (a jouter du decolorant /chlore ) ");
         listMakeSafer.add("3-Filter with a cloth (filtrer avec un tissu )");
         listMakeSafer.add("4-Use a filter(ceramic/sand/composite/etc) (utiliser un filtre(ceramique/sable/composite/etc) )   ");
         listMakeSafer.add("5-Solar disinfection (disinfection solaire)");
         listMakeSafer.add("6-Leave it at rest (la laisser au repos ) ");
         listMakeSafer.add("7-Other (autre)");
         listMakeSafer.add("8-Don't know (ne sait pas )");
         spnMakeSafer.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listMakeSafer)));

         spnMakeSafer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnMakeSafer.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnMakeSafer.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("7"))
                 {
                    secMakeSaferOth.setVisibility(View.VISIBLE);
                    lineMakeSaferOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secMakeSaferOth.setVisibility(View.GONE);
                    lineMakeSaferOth.setVisibility(View.GONE);
                    txtMakeSaferOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secMakeSaferOth=(LinearLayout)findViewById(R.id.secMakeSaferOth);
         lineMakeSaferOth=(View)findViewById(R.id.lineMakeSaferOth);
         VlblMakeSaferOth=(TextView) findViewById(R.id.VlblMakeSaferOth);
         txtMakeSaferOth=(AutoCompleteTextView) findViewById(R.id.txtMakeSaferOth);
         txtMakeSaferOth.setAdapter(C.getArrayAdapter("Select distinct MakeSaferOth from "+ TableName +" order by MakeSaferOth"));

         txtMakeSaferOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtMakeSaferOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtMakeSaferOth.getRight() - txtMakeSaferOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
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
         listToilet.add("15-Flush (Don't know where to)");
         listToilet.add("21-Pit latrines: Ventilated");
         listToilet.add("22-Pit latrines: With slab");
         listToilet.add("23-Pit latrines: Without slab/open pit");
         listToilet.add("31-Composting toilet");
         listToilet.add("41-Bucket");
         listToilet.add("51-Hanging toilet/latrine");
         listToilet.add("61-No facility/bush/field");
         listToilet.add("97-Other specify");
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
         rdoToiletShrd3 = (RadioButton) findViewById(R.id.rdoToiletShrd3);
         rdogrpToiletShrd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpToiletShrd = new String[] {"0","1","2"};
             for (int i = 0; i < rdogrpToiletShrd.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpToiletShrd.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpToiletShrd[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    secToiletUseNo.setVisibility(View.GONE);
                    lineToiletUseNo.setVisibility(View.GONE);
                    txtToiletUseNo.setText("");
                    secToiletUseNoDk.setVisibility(View.GONE);
                    lineToiletUseNoDk.setVisibility(View.GONE);
                    rdogrpToiletUseNoDk.clearCheck();
             }
             else
             {
                    secToiletUseNo.setVisibility(View.VISIBLE);
                    lineToiletUseNo.setVisibility(View.VISIBLE);
                    secToiletUseNoDk.setVisibility(View.VISIBLE);
                    lineToiletUseNoDk.setVisibility(View.VISIBLE);
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
         rdoToiletUseNoDk3 = (RadioButton) findViewById(R.id.rdoToiletUseNoDk3);

         txtToiletUseNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(txtToiletUseNo.getText().toString().length() > 0){
                  if(rdoToiletUseNoDk2.isChecked() || rdoToiletUseNoDk3.isChecked()){
                     rdoToiletUseNoDk2.setChecked(false);
                     rdoToiletUseNoDk3.setChecked(false);
                  }
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
         });

        rdogrpToiletUseNoDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
           @Override
           public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
              String rbData = "";
              RadioButton rb;
              String[] d_rdogrpToiletUseNoDk = new String[] {"95","98","99"};
              for (int i = 0; i < rdogrpToiletUseNoDk.getChildCount(); i++)
              {
                 rb = (RadioButton)rdogrpToiletUseNoDk.getChildAt(i);
                 if (rb.isChecked()) rbData = d_rdogrpToiletUseNoDk[i];
              }

              if(rbData.equalsIgnoreCase("98") || rbData.equalsIgnoreCase("99"))
              {
                 if(txtToiletUseNo.getText().toString().length() > 0){
                    txtToiletUseNo.setText("");
                 }
              }
           }
           public void onNothingSelected(AdapterView<?> adapterView) {
              return;
           }
        });

        secToiletLoc=(LinearLayout)findViewById(R.id.secToiletLoc);
         lineToiletLoc=(View)findViewById(R.id.lineToiletLoc);
         VlblToiletLoc = (TextView) findViewById(R.id.VlblToiletLoc);
         rdogrpToiletLoc = (RadioGroup) findViewById(R.id.rdogrpToiletLoc);
         rdoToiletLoc1 = (RadioButton) findViewById(R.id.rdoToiletLoc1);
         rdoToiletLoc2 = (RadioButton) findViewById(R.id.rdoToiletLoc2);
         rdoToiletLoc3 = (RadioButton) findViewById(R.id.rdoToiletLoc3);
         secContentEmp=(LinearLayout)findViewById(R.id.secContentEmp);
         lineContentEmp=(View)findViewById(R.id.lineContentEmp);
         VlblContentEmp = (TextView) findViewById(R.id.VlblContentEmp);
         rdogrpContentEmp = (RadioGroup) findViewById(R.id.rdogrpContentEmp);
         rdoContentEmp1 = (RadioButton) findViewById(R.id.rdoContentEmp1);
         rdoContentEmp2 = (RadioButton) findViewById(R.id.rdoContentEmp2);
         rdoContentEmp3 = (RadioButton) findViewById(R.id.rdoContentEmp3);
         rdoContentEmp4 = (RadioButton) findViewById(R.id.rdoContentEmp4);
         rdoContentEmp5 = (RadioButton) findViewById(R.id.rdoContentEmp5);
         rdoContentEmp6 = (RadioButton) findViewById(R.id.rdoContentEmp6);
         rdoContentEmp7 = (RadioButton) findViewById(R.id.rdoContentEmp7);
         rdogrpContentEmp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpContentEmp = new String[] {"1","2","3","4","5","7","8"};
             for (int i = 0; i < rdogrpContentEmp.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpContentEmp.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpContentEmp[i];
             }

             if(rbData.equalsIgnoreCase("7"))
             {
                secContentEmpOth.setVisibility(View.VISIBLE);
                lineContentEmpOth.setVisibility(View.VISIBLE);
             }
             else
             {
                secContentEmpOth.setVisibility(View.GONE);
                lineContentEmpOth.setVisibility(View.GONE);
                txtContentEmpOth.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secContentEmpOth=(LinearLayout)findViewById(R.id.secContentEmpOth);
         lineContentEmpOth=(View)findViewById(R.id.lineContentEmpOth);
         VlblContentEmpOth=(TextView) findViewById(R.id.VlblContentEmpOth);
         txtContentEmpOth=(AutoCompleteTextView) findViewById(R.id.txtContentEmpOth);
         txtContentEmpOth.setAdapter(C.getArrayAdapter("Select distinct ContentEmpOth from "+ TableName +" order by ContentEmpOth"));

         txtContentEmpOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtContentEmpOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtContentEmpOth.getRight() - txtContentEmpOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secBowelMov=(LinearLayout)findViewById(R.id.secBowelMov);
         lineBowelMov=(View)findViewById(R.id.lineBowelMov);
         VlblBowelMov=(TextView) findViewById(R.id.VlblBowelMov);
         spnBowelMov=(Spinner) findViewById(R.id.spnBowelMov);
         List<String> listBowelMov = new ArrayList<String>();
         
         listBowelMov.add("");
         listBowelMov.add("01-The child used the toilet (Lenfant a utilisé la toilette)");
         listBowelMov.add("02-Put or rinsed in the toilet (Mises ou rincée dans la toilette)");
         listBowelMov.add("03-Put or rinsed in the hole or pit?(Mises ou rincée dans le trou ou fosse)");
         listBowelMov.add("04-Thrown the trash(solid waste) ( Jetées à la poubelle (déchets solides))");
         listBowelMov.add("05-Buried in the earth (Enfui dans la terre)");
         listBowelMov.add("06-Left in the open air? (Laissées à lair libre)");
         listBowelMov.add("07-Used as manure (Utilisées comme fumier)");
         listBowelMov.add("97-Other (Autre)");
         listBowelMov.add("98-Don't know (NE SAIT AS)");
         spnBowelMov.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listBowelMov)));

         spnBowelMov.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnBowelMov.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnBowelMov.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                    secBowelMovOth.setVisibility(View.VISIBLE);
                    lineBowelMovOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secBowelMovOth.setVisibility(View.GONE);
                    lineBowelMovOth.setVisibility(View.GONE);
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secBowelMovOth=(LinearLayout)findViewById(R.id.secBowelMovOth);
         lineBowelMovOth=(View)findViewById(R.id.lineBowelMovOth);
         VlblBowelMovOth=(TextView) findViewById(R.id.VlblBowelMovOth);
         txtBowelMovOth=(AutoCompleteTextView) findViewById(R.id.txtBowelMovOth);
         txtBowelMovOth.setAdapter(C.getArrayAdapter("Select distinct BowelMovOth from "+ TableName +" order by BowelMovOth"));

         txtBowelMovOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtBowelMovOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtBowelMovOth.getRight() - txtBowelMovOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secLiquidWaste=(LinearLayout)findViewById(R.id.secLiquidWaste);
         lineLiquidWaste=(View)findViewById(R.id.lineLiquidWaste);
         VlblLiquidWaste = (TextView) findViewById(R.id.VlblLiquidWaste);
         rdogrpLiquidWaste = (RadioGroup) findViewById(R.id.rdogrpLiquidWaste);
         rdoLiquidWaste1 = (RadioButton) findViewById(R.id.rdoLiquidWaste1);
         rdoLiquidWaste2 = (RadioButton) findViewById(R.id.rdoLiquidWaste2);
         rdoLiquidWaste3 = (RadioButton) findViewById(R.id.rdoLiquidWaste3);
         rdoLiquidWaste4 = (RadioButton) findViewById(R.id.rdoLiquidWaste4);
         rdoLiquidWaste5 = (RadioButton) findViewById(R.id.rdoLiquidWaste5);
         rdoLiquidWaste6 = (RadioButton) findViewById(R.id.rdoLiquidWaste6);
         rdogrpLiquidWaste.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpLiquidWaste = new String[] {"1","2","3","4","5","7"};
             for (int i = 0; i < rdogrpLiquidWaste.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpLiquidWaste.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpLiquidWaste[i];
             }

             if(rbData.equalsIgnoreCase("7"))
             {
                secLiquidWasteOth.setVisibility(View.VISIBLE);
                lineLiquidWasteOth.setVisibility(View.VISIBLE);
             }
             else
             {
                secLiquidWasteOth.setVisibility(View.GONE);
                lineLiquidWasteOth.setVisibility(View.GONE);
                txtLiquidWasteOth.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secLiquidWasteOth=(LinearLayout)findViewById(R.id.secLiquidWasteOth);
         lineLiquidWasteOth=(View)findViewById(R.id.lineLiquidWasteOth);
         VlblLiquidWasteOth=(TextView) findViewById(R.id.VlblLiquidWasteOth);
         txtLiquidWasteOth=(AutoCompleteTextView) findViewById(R.id.txtLiquidWasteOth);
         txtLiquidWasteOth.setAdapter(C.getArrayAdapter("Select distinct LiquidWasteOth from "+ TableName +" order by LiquidWasteOth"));

         txtLiquidWasteOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtLiquidWasteOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtLiquidWasteOth.getRight() - txtLiquidWasteOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secSolidWasteMethod=(LinearLayout)findViewById(R.id.secSolidWasteMethod);
         lineSolidWasteMethod=(View)findViewById(R.id.lineSolidWasteMethod);
         VlblSolidWasteMethod=(TextView) findViewById(R.id.VlblSolidWasteMethod);
         spnSolidWasteMethod=(Spinner) findViewById(R.id.spnSolidWasteMethod);
         List<String> listSolidWasteMethod = new ArrayList<String>();
         
         listSolidWasteMethod.add("");
         listSolidWasteMethod.add("01-Compound yard (Cour de la concession)");
         listSolidWasteMethod.add("02-Street (Rue)");
         listSolidWasteMethod.add("03-Wells (Puits)");
         listSolidWasteMethod.add("04-Septic tank (Fosse septique)");
         listSolidWasteMethod.add("05-Pit (Caniveau)");
         listSolidWasteMethod.add("06-Pile of rubbish (Tas dimmondices )");
         listSolidWasteMethod.add("07-Public trash can (Poubelle publique)");
         listSolidWasteMethod.add("08-Burned by the household (Brulés par le ménage)");
         listSolidWasteMethod.add("97-Other (Autre )");
         spnSolidWasteMethod.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listSolidWasteMethod)));

         spnSolidWasteMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnSolidWasteMethod.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnSolidWasteMethod.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                    secSolidWasteMethodOth.setVisibility(View.VISIBLE);
                    lineSolidWasteMethodOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secSolidWasteMethodOth.setVisibility(View.GONE);
                    lineSolidWasteMethodOth.setVisibility(View.GONE);
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secSolidWasteMethodOth=(LinearLayout)findViewById(R.id.secSolidWasteMethodOth);
         lineSolidWasteMethodOth=(View)findViewById(R.id.lineSolidWasteMethodOth);
         VlblSolidWasteMethodOth=(TextView) findViewById(R.id.VlblSolidWasteMethodOth);
         txtSolidWasteMethodOth=(AutoCompleteTextView) findViewById(R.id.txtSolidWasteMethodOth);
         txtSolidWasteMethodOth.setAdapter(C.getArrayAdapter("Select distinct SolidWasteMethodOth from "+ TableName +" order by SolidWasteMethodOth"));

         txtSolidWasteMethodOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtSolidWasteMethodOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtSolidWasteMethodOth.getRight() - txtSolidWasteMethodOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secHandWash=(LinearLayout)findViewById(R.id.secHandWash);
         lineHandWash=(View)findViewById(R.id.lineHandWash);
         VlblHandWash = (TextView) findViewById(R.id.VlblHandWash);
         rdogrpHandWash = (RadioGroup) findViewById(R.id.rdogrpHandWash);
         rdoHandWash1 = (RadioButton) findViewById(R.id.rdoHandWash1);
         rdoHandWash2 = (RadioButton) findViewById(R.id.rdoHandWash2);
         rdoHandWash3 = (RadioButton) findViewById(R.id.rdoHandWash3);
         rdogrpHandWash.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpHandWash = new String[] {"0","1","8"};
             for (int i = 0; i < rdogrpHandWash.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpHandWash.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpHandWash[i];
             }

             if(rbData.equalsIgnoreCase("0") || rbData.equalsIgnoreCase("8"))
             {
                    secShowWash.setVisibility(View.GONE);
                    lineShowWash.setVisibility(View.GONE);
                    rdogrpShowWash.clearCheck();
                    secAvailableWat.setVisibility(View.GONE);
                    lineAvailableWat.setVisibility(View.GONE);
                    rdogrpAvailableWat.clearCheck();
                    secAvailableSoap.setVisibility(View.GONE);
                    lineAvailableSoap.setVisibility(View.GONE);
                    rdogrpAvailableSoap.clearCheck();
             }
             else
             {
                    secShowWash.setVisibility(View.VISIBLE);
                    lineShowWash.setVisibility(View.VISIBLE);
                    secAvailableWat.setVisibility(View.VISIBLE);
                    lineAvailableWat.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secShowWash=(LinearLayout)findViewById(R.id.secShowWash);
         lineShowWash=(View)findViewById(R.id.lineShowWash);
         VlblShowWash = (TextView) findViewById(R.id.VlblShowWash);
         rdogrpShowWash = (RadioGroup) findViewById(R.id.rdogrpShowWash);
         rdoShowWash1 = (RadioButton) findViewById(R.id.rdoShowWash1);
         rdoShowWash2 = (RadioButton) findViewById(R.id.rdoShowWash2);
         rdoShowWash3 = (RadioButton) findViewById(R.id.rdoShowWash3);
         rdoShowWash4 = (RadioButton) findViewById(R.id.rdoShowWash4);
         rdoShowWash5 = (RadioButton) findViewById(R.id.rdoShowWash5);
         rdoShowWash6 = (RadioButton) findViewById(R.id.rdoShowWash6);
         secAvailableWat=(LinearLayout)findViewById(R.id.secAvailableWat);
         lineAvailableWat=(View)findViewById(R.id.lineAvailableWat);
         VlblAvailableWat = (TextView) findViewById(R.id.VlblAvailableWat);
         rdogrpAvailableWat = (RadioGroup) findViewById(R.id.rdogrpAvailableWat);
         rdoAvailableWat1 = (RadioButton) findViewById(R.id.rdoAvailableWat1);
         rdoAvailableWat2 = (RadioButton) findViewById(R.id.rdoAvailableWat2);
         rdogrpAvailableWat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpAvailableWat = new String[] {"1","2"};
             for (int i = 0; i < rdogrpAvailableWat.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpAvailableWat.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpAvailableWat[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secAvailableSoap.setVisibility(View.VISIBLE);
                lineAvailableSoap.setVisibility(View.VISIBLE);
             }
             else
             {
                secAvailableSoap.setVisibility(View.GONE);
                lineAvailableSoap.setVisibility(View.GONE);
                rdogrpAvailableSoap.clearCheck();
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secAvailableSoap=(LinearLayout)findViewById(R.id.secAvailableSoap);
         lineAvailableSoap=(View)findViewById(R.id.lineAvailableSoap);
         VlblAvailableSoap = (TextView) findViewById(R.id.VlblAvailableSoap);
         rdogrpAvailableSoap = (RadioGroup) findViewById(R.id.rdogrpAvailableSoap);
         rdoAvailableSoap1 = (RadioButton) findViewById(R.id.rdoAvailableSoap1);
         rdoAvailableSoap2 = (RadioButton) findViewById(R.id.rdoAvailableSoap2);
         rdoAvailableSoap3 = (RadioButton) findViewById(R.id.rdoAvailableSoap3);
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
         listCookDvc.add("05-Moveable firepan/bbq");
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
         listCookFuel.add("12-Woodchips");
         listCookFuel.add("13-Garbage/plastic");
         listCookFuel.add("14-Sawdust");
         listCookFuel.add("15-Electricity");
         listCookFuel.add("16-LPG (LPG)");
         listCookFuel.add("17-Biogas (biogaz)");
         listCookFuel.add("18-We do not prepare food in the household (on ne prepare Pas de nourriture dans le menage )");
         listCookFuel.add("97-Other specify");
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
         listCookPlc.add("7-Other specify");
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
         listFloor.add("97-Other specify");
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
         secGroundMat=(LinearLayout)findViewById(R.id.secGroundMat);
         lineGroundMat=(View)findViewById(R.id.lineGroundMat);
         VlblGroundMat=(TextView) findViewById(R.id.VlblGroundMat);
         spnGroundMat=(Spinner) findViewById(R.id.spnGroundMat);
         List<String> listGroundMat = new ArrayList<String>();
         
         listGroundMat.add("");
         listGroundMat.add("11-Natural floor:Earth/sand");
         listGroundMat.add("12-Natural floor:Dung");
         listGroundMat.add("21-Rudimentary floor:Wood planks");
         listGroundMat.add("22-Rudimentary floor:Palm/bamboo");
         listGroundMat.add("31-Finished floor:Parquet or polished wood");
         listGroundMat.add("32-Finished floor:Vinyl or asphalt strips");
         listGroundMat.add("33-Finished floor:Ceramic tiles");
         listGroundMat.add("34-Finished floor:Cement/Brick");
         listGroundMat.add("35-Finished floor:Carpet");
         listGroundMat.add("97-Other specify");
         listGroundMat.add("98-Don't know");
         listGroundMat.add("99-Refused to respond");
         spnGroundMat.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listGroundMat)));

         spnGroundMat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnGroundMat.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnGroundMat.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                    secGroundMatOth.setVisibility(View.VISIBLE);
                    lineGroundMatOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secGroundMatOth.setVisibility(View.GONE);
                    lineGroundMatOth.setVisibility(View.GONE);
                    txtGroundMatOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secGroundMatOth=(LinearLayout)findViewById(R.id.secGroundMatOth);
         lineGroundMatOth=(View)findViewById(R.id.lineGroundMatOth);
         VlblGroundMatOth=(TextView) findViewById(R.id.VlblGroundMatOth);
         txtGroundMatOth=(AutoCompleteTextView) findViewById(R.id.txtGroundMatOth);
         txtGroundMatOth.setAdapter(C.getArrayAdapter("Select distinct GroundMatOth from "+ TableName +" order by GroundMatOth"));

         txtGroundMatOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtGroundMatOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtGroundMatOth.getRight() - txtGroundMatOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
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
         listRoof.add("13-Natural roofing: Sod/GRASS (HERBES)");
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
         listRoof.add("37-SLABS (DALLE)");
         listRoof.add("97-Other specify");
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
         secSmokeInside=(LinearLayout)findViewById(R.id.secSmokeInside);
         lineSmokeInside=(View)findViewById(R.id.lineSmokeInside);
         VlblSmokeInside = (TextView) findViewById(R.id.VlblSmokeInside);
         rdogrpSmokeInside = (RadioGroup) findViewById(R.id.rdogrpSmokeInside);
         rdoSmokeInside1 = (RadioButton) findViewById(R.id.rdoSmokeInside1);
         rdoSmokeInside2 = (RadioButton) findViewById(R.id.rdoSmokeInside2);
         rdoSmokeInside3 = (RadioButton) findViewById(R.id.rdoSmokeInside3);
         rdogrpSmokeInside.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpSmokeInside = new String[] {"0","1","8"};
             for (int i = 0; i < rdogrpSmokeInside.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpSmokeInside.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpSmokeInside[i];
             }

             if(rbData.equalsIgnoreCase("0") || rbData.equalsIgnoreCase("8"))
             {
                    secFreqSmoke.setVisibility(View.GONE);
                    lineFreqSmoke.setVisibility(View.GONE);
                    rdogrpFreqSmoke.clearCheck();
             }
             else
             {
                    secFreqSmoke.setVisibility(View.VISIBLE);
                    lineFreqSmoke.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secFreqSmoke=(LinearLayout)findViewById(R.id.secFreqSmoke);
         lineFreqSmoke=(View)findViewById(R.id.lineFreqSmoke);
         VlblFreqSmoke = (TextView) findViewById(R.id.VlblFreqSmoke);
         rdogrpFreqSmoke = (RadioGroup) findViewById(R.id.rdogrpFreqSmoke);
         rdoFreqSmoke1 = (RadioButton) findViewById(R.id.rdoFreqSmoke1);
         rdoFreqSmoke2 = (RadioButton) findViewById(R.id.rdoFreqSmoke2);
         rdoFreqSmoke3 = (RadioButton) findViewById(R.id.rdoFreqSmoke3);
         rdoFreqSmoke4 = (RadioButton) findViewById(R.id.rdoFreqSmoke4);
         secWall=(LinearLayout)findViewById(R.id.secWall);
         lineWall=(View)findViewById(R.id.lineWall);
         VlblWall=(TextView) findViewById(R.id.VlblWall);
         spnWall=(Spinner) findViewById(R.id.spnWall);
         List<String> listWall = new ArrayList<String>();
         
         listWall.add("");
         listWall.add("11-Natural walls: No walls");
         listWall.add("12-Natural walls: Cane/palm/trunks");
         listWall.add("13-Natural walls: Earth/soil/dung/DIRT");
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
         listWall.add("35-Finished walls: Concrete/cement blocks");
         listWall.add("36-Finished walls: Wood planks/shingles");
         listWall.add("37-Finished walls: Plaster/cover adobe");
         listWall.add("38-Generator (groupe elctrogene)");
         listWall.add("97-Other specify");
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

         txtRoomSleep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(txtRoomSleep.getText().toString().length() > 0){
                  if(rdoRoomSleepDk1.isChecked() || rdoRoomSleepDk2.isChecked()){
                     rdogrpRoomSleepDk.clearCheck();
                  }
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
         });

         rdogrpRoomSleepDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               String rbData = "";
               RadioButton rb;
               String[] d_rdogrpRoomSleepDk = new String[]{"98","99"};
               for (int i = 0; i < rdogrpRoomSleepDk.getChildCount() ; i++){
                  rb = (RadioButton) rdogrpRoomSleepDk.getChildAt(i);
                  if(rb.isChecked()) rbData = d_rdogrpRoomSleepDk[i];
               }

               if(rbData.equalsIgnoreCase("98") || rbData.equalsIgnoreCase("99")){
                  if(txtRoomSleep.getText().toString().length() > 0){
                     txtRoomSleep.setText("");
                  }
               }
            }
         });

         rdoRoomSleepDk1 = (RadioButton) findViewById(R.id.rdoRoomSleepDk1);
         rdoRoomSleepDk2 = (RadioButton) findViewById(R.id.rdoRoomSleepDk2);
         secElecNight=(LinearLayout)findViewById(R.id.secElecNight);
         lineElecNight=(View)findViewById(R.id.lineElecNight);
         VlblElecNight=(TextView) findViewById(R.id.VlblElecNight);
         spnElecNight=(Spinner) findViewById(R.id.spnElecNight);
         List<String> listElecNight = new ArrayList<String>();
         
         listElecNight.add("");
         listElecNight.add("01-Electricity (EDM) (electricite(EDM))");
         listElecNight.add("02-Generator (Groupe électrogène)");
         listElecNight.add("03-Solar plate (Plaque solaire)");
         listElecNight.add("04-Battery (Batterie)");
         listElecNight.add("05-Gas lamp (Lampe à gaz)");
         listElecNight.add("06-Oil lamp (Lampe à pétrole)");
         listElecNight.add("07-Petroleum lamp (Lampe à huile)");
         listElecNight.add("08-Flash light (Lampe à torche)");
         listElecNight.add("09-Candle (Bougie)");
         listElecNight.add("10-Wood/Straw (Bois / Paille)");
         listElecNight.add("11-None (aucun)");
         listElecNight.add("97-Other (Autre)");
         spnElecNight.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listElecNight)));

         spnElecNight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             String spnData = "";
             if (spnElecNight.getSelectedItem().toString().length() != 0)
             {
                 spnData = Connection.SelectedSpinnerValue(spnElecNight.getSelectedItem().toString(), "-");
             }
                 if(spnData.equalsIgnoreCase("97"))
                 {
                    secElecNightOth.setVisibility(View.VISIBLE);
                    lineElecNightOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                    secElecNightOth.setVisibility(View.GONE);
                    lineElecNightOth.setVisibility(View.GONE);
                    txtElecNightOth.setText("");
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secElecNightOth=(LinearLayout)findViewById(R.id.secElecNightOth);
         lineElecNightOth=(View)findViewById(R.id.lineElecNightOth);
         VlblElecNightOth=(TextView) findViewById(R.id.VlblElecNightOth);
         txtElecNightOth=(AutoCompleteTextView) findViewById(R.id.txtElecNightOth);
         txtElecNightOth.setAdapter(C.getArrayAdapter("Select distinct ElecNightOth from "+ TableName +" order by ElecNightOth"));

         txtElecNightOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtElecNightOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtElecNightOth.getRight() - txtElecNightOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
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
         secArea=(LinearLayout)findViewById(R.id.secArea);
         lineArea=(View)findViewById(R.id.lineArea);
         VlblArea=(TextView) findViewById(R.id.VlblArea);
         txtArea=(EditText) findViewById(R.id.txtArea);
         secIncomeMo=(LinearLayout)findViewById(R.id.secIncomeMo);
         lineIncomeMo=(View)findViewById(R.id.lineIncomeMo);
         VlblIncomeMo = (TextView) findViewById(R.id.VlblIncomeMo);
         rdogrpIncomeMo = (RadioGroup) findViewById(R.id.rdogrpIncomeMo);
         rdoIncomeMo1 = (RadioButton) findViewById(R.id.rdoIncomeMo1);
         rdoIncomeMo2 = (RadioButton) findViewById(R.id.rdoIncomeMo2);
         rdoIncomeMo3 = (RadioButton) findViewById(R.id.rdoIncomeMo3);
         rdoIncomeMo4 = (RadioButton) findViewById(R.id.rdoIncomeMo4);
         rdoIncomeMo5 = (RadioButton) findViewById(R.id.rdoIncomeMo5);
         rdoIncomeMo6 = (RadioButton) findViewById(R.id.rdoIncomeMo6);
         rdoIncomeMo7 = (RadioButton) findViewById(R.id.rdoIncomeMo7);
         rdoIncomeMo8 = (RadioButton) findViewById(R.id.rdoIncomeMo8);
         secExpenses=(LinearLayout)findViewById(R.id.secExpenses);
         lineExpenses=(View)findViewById(R.id.lineExpenses);
         VlblExpenses = (TextView) findViewById(R.id.VlblExpenses);
         rdogrpExpenses = (RadioGroup) findViewById(R.id.rdogrpExpenses);
         rdoExpenses1 = (RadioButton) findViewById(R.id.rdoExpenses1);
         rdoExpenses2 = (RadioButton) findViewById(R.id.rdoExpenses2);
         rdoExpenses3 = (RadioButton) findViewById(R.id.rdoExpenses3);
         rdoExpenses4 = (RadioButton) findViewById(R.id.rdoExpenses4);
         rdoExpenses5 = (RadioButton) findViewById(R.id.rdoExpenses5);
         rdoExpenses6 = (RadioButton) findViewById(R.id.rdoExpenses6);
         rdoExpenses7 = (RadioButton) findViewById(R.id.rdoExpenses7);
         rdoExpenses8 = (RadioButton) findViewById(R.id.rdoExpenses8);
         secBankAcc=(LinearLayout)findViewById(R.id.secBankAcc);
         lineBankAcc=(View)findViewById(R.id.lineBankAcc);
         VlblBankAcc = (TextView) findViewById(R.id.VlblBankAcc);
         rdogrpBankAcc = (RadioGroup) findViewById(R.id.rdogrpBankAcc);
         rdoBankAcc1 = (RadioButton) findViewById(R.id.rdoBankAcc1);
         rdoBankAcc2 = (RadioButton) findViewById(R.id.rdoBankAcc2);
         secSprayInt=(LinearLayout)findViewById(R.id.secSprayInt);
         lineSprayInt=(View)findViewById(R.id.lineSprayInt);
         VlblSprayInt = (TextView) findViewById(R.id.VlblSprayInt);
         rdogrpSprayInt = (RadioGroup) findViewById(R.id.rdogrpSprayInt);
         rdoSprayInt1 = (RadioButton) findViewById(R.id.rdoSprayInt1);
         rdoSprayInt2 = (RadioButton) findViewById(R.id.rdoSprayInt2);
         rdoSprayInt3 = (RadioButton) findViewById(R.id.rdoSprayInt3);
         secMosqNet=(LinearLayout)findViewById(R.id.secMosqNet);
         lineMosqNet=(View)findViewById(R.id.lineMosqNet);
         VlblMosqNet = (TextView) findViewById(R.id.VlblMosqNet);
         rdogrpMosqNet = (RadioGroup) findViewById(R.id.rdogrpMosqNet);
         rdoMosqNet1 = (RadioButton) findViewById(R.id.rdoMosqNet1);
         rdoMosqNet2 = (RadioButton) findViewById(R.id.rdoMosqNet2);
         rdoMosqNet3 = (RadioButton) findViewById(R.id.rdoMosqNet3);
         rdogrpMosqNet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpMosqNet = new String[] {"0","1","8"};
             for (int i = 0; i < rdogrpMosqNet.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpMosqNet.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpMosqNet[i];
             }

             if(rbData.equalsIgnoreCase("0") || rbData.equalsIgnoreCase("8"))
             {
                    secNetOwned.setVisibility(View.GONE);
                    lineNetOwned.setVisibility(View.GONE);
                    txtNetOwned.setText("");
             }
             else
             {
                    secNetOwned.setVisibility(View.VISIBLE);
                    lineNetOwned.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secNetOwned=(LinearLayout)findViewById(R.id.secNetOwned);
         lineNetOwned=(View)findViewById(R.id.lineNetOwned);
         VlblNetOwned=(TextView) findViewById(R.id.VlblNetOwned);
         txtNetOwned=(EditText) findViewById(R.id.txtNetOwned);
         secMedHome=(LinearLayout)findViewById(R.id.secMedHome);
         lineMedHome=(View)findViewById(R.id.lineMedHome);
         VlblMedHome = (TextView) findViewById(R.id.VlblMedHome);
         rdogrpMedHome = (RadioGroup) findViewById(R.id.rdogrpMedHome);
         rdoMedHome1 = (RadioButton) findViewById(R.id.rdoMedHome1);
         rdoMedHome2 = (RadioButton) findViewById(R.id.rdoMedHome2);
         rdoMedHome3 = (RadioButton) findViewById(R.id.rdoMedHome3);
         rdogrpMedHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpMedHome = new String[] {"0","1","8"};
             for (int i = 0; i < rdogrpMedHome.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpMedHome.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpMedHome[i];
             }

             if(rbData.equalsIgnoreCase("0") || rbData.equalsIgnoreCase("8"))
             {
                    seclbl119.setVisibility(View.GONE);
                    linelbl119.setVisibility(View.GONE);
                    secMedTypeAM.setVisibility(View.GONE);
                    lineMedTypeAM.setVisibility(View.GONE);
                    chkMedTypeAM.setChecked(false);
                    secMedTypeAB.setVisibility(View.GONE);
                    lineMedTypeAB.setVisibility(View.GONE);
                    chkMedTypeAB.setChecked(false);
                    secMedTypeDK.setVisibility(View.GONE);
                    lineMedTypeDK.setVisibility(View.GONE);
                    chkMedTypeDK.setChecked(false);
                    seclbl1110.setVisibility(View.GONE);
                    linelbl1110.setVisibility(View.GONE);
                    secAntimalarAL.setVisibility(View.GONE);
                    lineAntimalarAL.setVisibility(View.GONE);
                    chkAntimalarAL.setChecked(false);
                    secAntimalarASAQ.setVisibility(View.GONE);
                    lineAntimalarASAQ.setVisibility(View.GONE);
                    chkAntimalarASAQ.setChecked(false);
                    secAntimalarSP.setVisibility(View.GONE);
                    lineAntimalarSP.setVisibility(View.GONE);
                    chkAntimalarSP.setChecked(false);
                    secAntimalarOth.setVisibility(View.GONE);
                    lineAntimalarOth.setVisibility(View.GONE);
                    chkAntimalarOth.setChecked(false);
                    secAntimalarSpecifyOth.setVisibility(View.GONE);
                    lineAntimalarSpecifyOth.setVisibility(View.GONE);
                    txtAntimalarSpecifyOth.setText("");
                    seclbl1111.setVisibility(View.GONE);
                    linelbl1111.setVisibility(View.GONE);
                    secGetMedHosp.setVisibility(View.GONE);
                    lineGetMedHosp.setVisibility(View.GONE);
                    chkGetMedHosp.setChecked(false);
                    secGetMedCSCom.setVisibility(View.GONE);
                    lineGetMedCSCom.setVisibility(View.GONE);
                    chkGetMedCSCom.setChecked(false);
                    secGetMedPrvCl.setVisibility(View.GONE);
                    lineGetMedPrvCl.setVisibility(View.GONE);
                    chkGetMedPrvCl.setChecked(false);
                    secGetMedPhar.setVisibility(View.GONE);
                    lineGetMedPhar.setVisibility(View.GONE);
                    chkGetMedPhar.setChecked(false);
                    secGetMedPD.setVisibility(View.GONE);
                    lineGetMedPD.setVisibility(View.GONE);
                    chkGetMedPD.setChecked(false);
                    secGetMedCHW.setVisibility(View.GONE);
                    lineGetMedCHW.setVisibility(View.GONE);
                    chkGetMedCHW.setChecked(false);
                    secGetMedSS.setVisibility(View.GONE);
                    lineGetMedSS.setVisibility(View.GONE);
                    chkGetMedSS.setChecked(false);
                    secGetMedOth.setVisibility(View.GONE);
                    lineGetMedOth.setVisibility(View.GONE);
                    chkGetMedOth.setChecked(false);
                    secGetMedSpecifyOth.setVisibility(View.GONE);
                    lineGetMedSpecifyOth.setVisibility(View.GONE);
                    txtGetMedSpecifyOth.setText("");
             }
             else
             {
                    seclbl119.setVisibility(View.VISIBLE);
                    linelbl119.setVisibility(View.VISIBLE);
                    secMedTypeAM.setVisibility(View.VISIBLE);
                    lineMedTypeAM.setVisibility(View.VISIBLE);
                    secMedTypeAB.setVisibility(View.VISIBLE);
                    lineMedTypeAB.setVisibility(View.VISIBLE);
                    secMedTypeDK.setVisibility(View.VISIBLE);
                    lineMedTypeDK.setVisibility(View.VISIBLE);
//                    seclbl1110.setVisibility(View.VISIBLE);
//                    linelbl1110.setVisibility(View.VISIBLE);
//                    secAntimalarAL.setVisibility(View.VISIBLE);
//                    lineAntimalarAL.setVisibility(View.VISIBLE);
//                    secAntimalarASAQ.setVisibility(View.VISIBLE);
//                    lineAntimalarASAQ.setVisibility(View.VISIBLE);
//                    secAntimalarSP.setVisibility(View.VISIBLE);
//                    lineAntimalarSP.setVisibility(View.VISIBLE);
//                    secAntimalarOth.setVisibility(View.VISIBLE);
//                    lineAntimalarOth.setVisibility(View.VISIBLE);
//                    seclbl1111.setVisibility(View.VISIBLE);
//                    linelbl1111.setVisibility(View.VISIBLE);
//                    secGetMedHosp.setVisibility(View.VISIBLE);
//                    lineGetMedHosp.setVisibility(View.VISIBLE);
//                    secGetMedCSCom.setVisibility(View.VISIBLE);
//                    lineGetMedCSCom.setVisibility(View.VISIBLE);
//                    secGetMedPrvCl.setVisibility(View.VISIBLE);
//                    lineGetMedPrvCl.setVisibility(View.VISIBLE);
//                    secGetMedPhar.setVisibility(View.VISIBLE);
//                    lineGetMedPhar.setVisibility(View.VISIBLE);
//                    secGetMedPD.setVisibility(View.VISIBLE);
//                    lineGetMedPD.setVisibility(View.VISIBLE);
//                    secGetMedCHW.setVisibility(View.VISIBLE);
//                    lineGetMedCHW.setVisibility(View.VISIBLE);
//                    secGetMedSS.setVisibility(View.VISIBLE);
//                    lineGetMedSS.setVisibility(View.VISIBLE);
//                    secGetMedOth.setVisibility(View.VISIBLE);
//                    lineGetMedOth.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclbl119=(LinearLayout)findViewById(R.id.seclbl119);
         linelbl119=(View)findViewById(R.id.linelbl119);
         secMedTypeAM=(LinearLayout)findViewById(R.id.secMedTypeAM);
         lineMedTypeAM=(View)findViewById(R.id.lineMedTypeAM);
         VlblMedTypeAM=(TextView) findViewById(R.id.VlblMedTypeAM);
         chkMedTypeAM=(CheckBox) findViewById(R.id.chkMedTypeAM);

         chkMedTypeAM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                    seclbl1110.setVisibility(View.VISIBLE);
                    linelbl1110.setVisibility(View.VISIBLE);
                    secAntimalarAL.setVisibility(View.VISIBLE);
                    lineAntimalarAL.setVisibility(View.VISIBLE);
                    secAntimalarASAQ.setVisibility(View.VISIBLE);
                    lineAntimalarASAQ.setVisibility(View.VISIBLE);
                    secAntimalarSP.setVisibility(View.VISIBLE);
                    lineAntimalarSP.setVisibility(View.VISIBLE);
                    secAntimalarOth.setVisibility(View.VISIBLE);
                    lineAntimalarOth.setVisibility(View.VISIBLE);
               }
               else {
                     seclbl1110.setVisibility(View.GONE);
                     linelbl1110.setVisibility(View.GONE);
                     secAntimalarAL.setVisibility(View.GONE);
                     lineAntimalarAL.setVisibility(View.GONE);
                     chkAntimalarAL.setChecked(false);
                     secAntimalarASAQ.setVisibility(View.GONE);
                     lineAntimalarASAQ.setVisibility(View.GONE);
                     chkAntimalarASAQ.setChecked(false);
                     secAntimalarSP.setVisibility(View.GONE);
                     lineAntimalarSP.setVisibility(View.GONE);
                     chkAntimalarSP.setChecked(false);
                     secAntimalarOth.setVisibility(View.GONE);
                     lineAntimalarOth.setVisibility(View.GONE);
                     chkAntimalarOth.setChecked(false);
                     secAntimalarSpecifyOth.setVisibility(View.GONE);
                     lineAntimalarSpecifyOth.setVisibility(View.GONE);
                     txtAntimalarSpecifyOth.setText("");
               }
            }
         });

         secMedTypeAB=(LinearLayout)findViewById(R.id.secMedTypeAB);
         lineMedTypeAB=(View)findViewById(R.id.lineMedTypeAB);
         VlblMedTypeAB=(TextView) findViewById(R.id.VlblMedTypeAB);
         chkMedTypeAB=(CheckBox) findViewById(R.id.chkMedTypeAB);
         secMedTypeDK=(LinearLayout)findViewById(R.id.secMedTypeDK);
         lineMedTypeDK=(View)findViewById(R.id.lineMedTypeDK);
         VlblMedTypeDK=(TextView) findViewById(R.id.VlblMedTypeDK);
         chkMedTypeDK=(CheckBox) findViewById(R.id.chkMedTypeDK);
         seclbl1110=(LinearLayout)findViewById(R.id.seclbl1110);
         linelbl1110=(View)findViewById(R.id.linelbl1110);
         secAntimalarAL=(LinearLayout)findViewById(R.id.secAntimalarAL);
         lineAntimalarAL=(View)findViewById(R.id.lineAntimalarAL);
         VlblAntimalarAL=(TextView) findViewById(R.id.VlblAntimalarAL);
         chkAntimalarAL=(CheckBox) findViewById(R.id.chkAntimalarAL);
         secAntimalarASAQ=(LinearLayout)findViewById(R.id.secAntimalarASAQ);
         lineAntimalarASAQ=(View)findViewById(R.id.lineAntimalarASAQ);
         VlblAntimalarASAQ=(TextView) findViewById(R.id.VlblAntimalarASAQ);
         chkAntimalarASAQ=(CheckBox) findViewById(R.id.chkAntimalarASAQ);
         secAntimalarSP=(LinearLayout)findViewById(R.id.secAntimalarSP);
         lineAntimalarSP=(View)findViewById(R.id.lineAntimalarSP);
         VlblAntimalarSP=(TextView) findViewById(R.id.VlblAntimalarSP);
         chkAntimalarSP=(CheckBox) findViewById(R.id.chkAntimalarSP);
         secAntimalarOth=(LinearLayout)findViewById(R.id.secAntimalarOth);
         lineAntimalarOth=(View)findViewById(R.id.lineAntimalarOth);
         VlblAntimalarOth=(TextView) findViewById(R.id.VlblAntimalarOth);
         chkAntimalarOth=(CheckBox) findViewById(R.id.chkAntimalarOth);
         chkAntimalarOth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secAntimalarSpecifyOth.setVisibility(View.VISIBLE);
                    lineAntimalarSpecifyOth.setVisibility(View.VISIBLE);
                 }
                 else {
                    secAntimalarSpecifyOth.setVisibility(View.GONE);
                    lineAntimalarSpecifyOth.setVisibility(View.GONE);
                    txtAntimalarSpecifyOth.setText("");
                 }
                 }
         });
         secAntimalarSpecifyOth=(LinearLayout)findViewById(R.id.secAntimalarSpecifyOth);
         lineAntimalarSpecifyOth=(View)findViewById(R.id.lineAntimalarSpecifyOth);
         VlblAntimalarSpecifyOth=(TextView) findViewById(R.id.VlblAntimalarSpecifyOth);
         txtAntimalarSpecifyOth=(AutoCompleteTextView) findViewById(R.id.txtAntimalarSpecifyOth);
         txtAntimalarSpecifyOth.setAdapter(C.getArrayAdapter("Select distinct AntimalarSpecifyOth from "+ TableName +" order by AntimalarSpecifyOth"));

         txtAntimalarSpecifyOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtAntimalarSpecifyOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtAntimalarSpecifyOth.getRight() - txtAntimalarSpecifyOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         seclbl1111=(LinearLayout)findViewById(R.id.seclbl1111);
         linelbl1111=(View)findViewById(R.id.linelbl1111);
         secGetMedHosp=(LinearLayout)findViewById(R.id.secGetMedHosp);
         lineGetMedHosp=(View)findViewById(R.id.lineGetMedHosp);
         VlblGetMedHosp=(TextView) findViewById(R.id.VlblGetMedHosp);
         chkGetMedHosp=(CheckBox) findViewById(R.id.chkGetMedHosp);
         secGetMedCSCom=(LinearLayout)findViewById(R.id.secGetMedCSCom);
         lineGetMedCSCom=(View)findViewById(R.id.lineGetMedCSCom);
         VlblGetMedCSCom=(TextView) findViewById(R.id.VlblGetMedCSCom);
         chkGetMedCSCom=(CheckBox) findViewById(R.id.chkGetMedCSCom);
         secGetMedPrvCl=(LinearLayout)findViewById(R.id.secGetMedPrvCl);
         lineGetMedPrvCl=(View)findViewById(R.id.lineGetMedPrvCl);
         VlblGetMedPrvCl=(TextView) findViewById(R.id.VlblGetMedPrvCl);
         chkGetMedPrvCl=(CheckBox) findViewById(R.id.chkGetMedPrvCl);
         secGetMedPhar=(LinearLayout)findViewById(R.id.secGetMedPhar);
         lineGetMedPhar=(View)findViewById(R.id.lineGetMedPhar);
         VlblGetMedPhar=(TextView) findViewById(R.id.VlblGetMedPhar);
         chkGetMedPhar=(CheckBox) findViewById(R.id.chkGetMedPhar);
         secGetMedPD=(LinearLayout)findViewById(R.id.secGetMedPD);
         lineGetMedPD=(View)findViewById(R.id.lineGetMedPD);
         VlblGetMedPD=(TextView) findViewById(R.id.VlblGetMedPD);
         chkGetMedPD=(CheckBox) findViewById(R.id.chkGetMedPD);
         secGetMedCHW=(LinearLayout)findViewById(R.id.secGetMedCHW);
         lineGetMedCHW=(View)findViewById(R.id.lineGetMedCHW);
         VlblGetMedCHW=(TextView) findViewById(R.id.VlblGetMedCHW);
         chkGetMedCHW=(CheckBox) findViewById(R.id.chkGetMedCHW);
         secGetMedSS=(LinearLayout)findViewById(R.id.secGetMedSS);
         lineGetMedSS=(View)findViewById(R.id.lineGetMedSS);
         VlblGetMedSS=(TextView) findViewById(R.id.VlblGetMedSS);
         chkGetMedSS=(CheckBox) findViewById(R.id.chkGetMedSS);
         secGetMedOth=(LinearLayout)findViewById(R.id.secGetMedOth);
         lineGetMedOth=(View)findViewById(R.id.lineGetMedOth);
         VlblGetMedOth=(TextView) findViewById(R.id.VlblGetMedOth);
         chkGetMedOth=(CheckBox) findViewById(R.id.chkGetMedOth);
         chkGetMedOth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                    secGetMedSpecifyOth.setVisibility(View.VISIBLE);
                    lineGetMedSpecifyOth.setVisibility(View.VISIBLE);
                 }
                 else {
                    secGetMedSpecifyOth.setVisibility(View.GONE);
                    lineGetMedSpecifyOth.setVisibility(View.GONE);
                    txtGetMedSpecifyOth.setText("");
                 }
                 }
         });
         secGetMedSpecifyOth=(LinearLayout)findViewById(R.id.secGetMedSpecifyOth);
         lineGetMedSpecifyOth=(View)findViewById(R.id.lineGetMedSpecifyOth);
         VlblGetMedSpecifyOth=(TextView) findViewById(R.id.VlblGetMedSpecifyOth);
         txtGetMedSpecifyOth=(AutoCompleteTextView) findViewById(R.id.txtGetMedSpecifyOth);
         txtGetMedSpecifyOth.setAdapter(C.getArrayAdapter("Select distinct GetMedSpecifyOth from "+ TableName +" order by GetMedSpecifyOth"));

         txtGetMedSpecifyOth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtGetMedSpecifyOth.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtGetMedSpecifyOth.getRight() - txtGetMedSpecifyOth.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
         secAComment=(LinearLayout)findViewById(R.id.secAComment);
         lineAComment=(View)findViewById(R.id.lineAComment);
         VlblAComment = (TextView) findViewById(R.id.VlblAComment);
         rdogrpAComment = (RadioGroup) findViewById(R.id.rdogrpAComment);
         rdoAComment1 = (RadioButton) findViewById(R.id.rdoAComment1);
         rdoAComment2 = (RadioButton) findViewById(R.id.rdoAComment2);
         rdogrpAComment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpAComment = new String[] {"0","1"};
             for (int i = 0; i < rdogrpAComment.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpAComment.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpAComment[i];
             }

             if(rbData.equalsIgnoreCase("0"))
             {
                    secComment.setVisibility(View.GONE);
                    lineComment.setVisibility(View.GONE);
                    txtComment.setText("");
             }
             else
             {
                    secComment.setVisibility(View.VISIBLE);
                    lineComment.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secComment=(LinearLayout)findViewById(R.id.secComment);
         lineComment=(View)findViewById(R.id.lineComment);
         VlblComment=(TextView) findViewById(R.id.VlblComment);
         txtComment=(AutoCompleteTextView) findViewById(R.id.txtComment);
         txtComment.setAdapter(C.getArrayAdapter("Select distinct Comment from "+ TableName +" order by Comment"));

         txtComment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

             }
         });
         txtComment.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT = 2;
         
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (txtComment.getRight() - txtComment.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         ((EditText)v).setText("");
                         return true;
                     }
                 }
                 return false;
             }
         });
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
         secSolarPlate=(LinearLayout)findViewById(R.id.secSolarPlate);
         lineSolarPlate=(View)findViewById(R.id.lineSolarPlate);
         VlblSolarPlate = (TextView) findViewById(R.id.VlblSolarPlate);
         rdogrpSolarPlate = (RadioGroup) findViewById(R.id.rdogrpSolarPlate);
         rdoSolarPlate1 = (RadioButton) findViewById(R.id.rdoSolarPlate1);
         rdoSolarPlate2 = (RadioButton) findViewById(R.id.rdoSolarPlate2);
         rdogrpSolarPlate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpSolarPlate = new String[] {"0","1"};
             for (int i = 0; i < rdogrpSolarPlate.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpSolarPlate.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpSolarPlate[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secSolarPlateN.setVisibility(View.VISIBLE);
                lineSolarPlateN.setVisibility(View.VISIBLE);
             }
             else
             {
                secSolarPlateN.setVisibility(View.GONE);
                lineSolarPlateN.setVisibility(View.GONE);
                txtSolarPlateN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secSolarPlateN=(LinearLayout)findViewById(R.id.secSolarPlateN);
         lineSolarPlateN=(View)findViewById(R.id.lineSolarPlateN);
         VlblSolarPlateN=(TextView) findViewById(R.id.VlblSolarPlateN);
         txtSolarPlateN=(EditText) findViewById(R.id.txtSolarPlateN);
         secHeater=(LinearLayout)findViewById(R.id.secHeater);
         lineHeater=(View)findViewById(R.id.lineHeater);
         VlblHeater = (TextView) findViewById(R.id.VlblHeater);
         rdogrpHeater = (RadioGroup) findViewById(R.id.rdogrpHeater);
         rdoHeater1 = (RadioButton) findViewById(R.id.rdoHeater1);
         rdoHeater2 = (RadioButton) findViewById(R.id.rdoHeater2);
         rdogrpHeater.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpHeater = new String[] {"0","1"};
             for (int i = 0; i < rdogrpHeater.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpHeater.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpHeater[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secHeaterN.setVisibility(View.VISIBLE);
                lineHeaterN.setVisibility(View.VISIBLE);
             }
             else
             {
                secHeaterN.setVisibility(View.GONE);
                lineHeaterN.setVisibility(View.GONE);
                txtHeaterN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secHeaterN=(LinearLayout)findViewById(R.id.secHeaterN);
         lineHeaterN=(View)findViewById(R.id.lineHeaterN);
         VlblHeaterN=(TextView) findViewById(R.id.VlblHeaterN);
         txtHeaterN=(EditText) findViewById(R.id.txtHeaterN);
         secAC=(LinearLayout)findViewById(R.id.secAC);
         lineAC=(View)findViewById(R.id.lineAC);
         VlblAC = (TextView) findViewById(R.id.VlblAC);
         rdogrpAC = (RadioGroup) findViewById(R.id.rdogrpAC);
         rdoAC1 = (RadioButton) findViewById(R.id.rdoAC1);
         rdoAC2 = (RadioButton) findViewById(R.id.rdoAC2);
         rdogrpAC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpAC = new String[] {"0","1"};
             for (int i = 0; i < rdogrpAC.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpAC.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpAC[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secACN.setVisibility(View.VISIBLE);
                lineACN.setVisibility(View.VISIBLE);
             }
             else
             {
                secACN.setVisibility(View.GONE);
                lineACN.setVisibility(View.GONE);
                txtACN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secACN=(LinearLayout)findViewById(R.id.secACN);
         lineACN=(View)findViewById(R.id.lineACN);
         VlblACN=(TextView) findViewById(R.id.VlblACN);
         txtACN=(EditText) findViewById(R.id.txtACN);
         secElecFan=(LinearLayout)findViewById(R.id.secElecFan);
         lineElecFan=(View)findViewById(R.id.lineElecFan);
         VlblElecFan = (TextView) findViewById(R.id.VlblElecFan);
         rdogrpElecFan = (RadioGroup) findViewById(R.id.rdogrpElecFan);
         rdoElecFan1 = (RadioButton) findViewById(R.id.rdoElecFan1);
         rdoElecFan2 = (RadioButton) findViewById(R.id.rdoElecFan2);
         rdogrpElecFan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpElecFan = new String[] {"0","1"};
             for (int i = 0; i < rdogrpElecFan.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpElecFan.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpElecFan[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secElecFanN.setVisibility(View.VISIBLE);
                lineElecFanN.setVisibility(View.VISIBLE);
             }
             else
             {
                secElecFanN.setVisibility(View.GONE);
                lineElecFanN.setVisibility(View.GONE);
                txtElecFanN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secElecFanN=(LinearLayout)findViewById(R.id.secElecFanN);
         lineElecFanN=(View)findViewById(R.id.lineElecFanN);
         VlblElecFanN=(TextView) findViewById(R.id.VlblElecFanN);
         txtElecFanN=(EditText) findViewById(R.id.txtElecFanN);
         secLantern=(LinearLayout)findViewById(R.id.secLantern);
         lineLantern=(View)findViewById(R.id.lineLantern);
         VlblLantern = (TextView) findViewById(R.id.VlblLantern);
         rdogrpLantern = (RadioGroup) findViewById(R.id.rdogrpLantern);
         rdoLantern1 = (RadioButton) findViewById(R.id.rdoLantern1);
         rdoLantern2 = (RadioButton) findViewById(R.id.rdoLantern2);
         rdogrpLantern.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpLantern = new String[] {"0","1"};
             for (int i = 0; i < rdogrpLantern.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpLantern.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpLantern[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secLanternN.setVisibility(View.VISIBLE);
                lineLanternN.setVisibility(View.VISIBLE);
             }
             else
             {
                secLanternN.setVisibility(View.GONE);
                lineLanternN.setVisibility(View.GONE);
                txtLanternN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secLanternN=(LinearLayout)findViewById(R.id.secLanternN);
         lineLanternN=(View)findViewById(R.id.lineLanternN);
         VlblLanternN=(TextView) findViewById(R.id.VlblLanternN);
         txtLanternN=(EditText) findViewById(R.id.txtLanternN);
         secLamp=(LinearLayout)findViewById(R.id.secLamp);
         lineLamp=(View)findViewById(R.id.lineLamp);
         VlblLamp = (TextView) findViewById(R.id.VlblLamp);
         rdogrpLamp = (RadioGroup) findViewById(R.id.rdogrpLamp);
         rdoLamp1 = (RadioButton) findViewById(R.id.rdoLamp1);
         rdoLamp2 = (RadioButton) findViewById(R.id.rdoLamp2);
         rdogrpLamp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpLamp = new String[] {"0","1"};
             for (int i = 0; i < rdogrpLamp.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpLamp.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpLamp[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secLampN.setVisibility(View.VISIBLE);
                lineLampN.setVisibility(View.VISIBLE);
             }
             else
             {
                secLampN.setVisibility(View.GONE);
                lineLampN.setVisibility(View.GONE);
                txtLampN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secLampN=(LinearLayout)findViewById(R.id.secLampN);
         lineLampN=(View)findViewById(R.id.lineLampN);
         VlblLampN=(TextView) findViewById(R.id.VlblLampN);
         txtLampN=(EditText) findViewById(R.id.txtLampN);
         secGasLamp=(LinearLayout)findViewById(R.id.secGasLamp);
         lineGasLamp=(View)findViewById(R.id.lineGasLamp);
         VlblGasLamp = (TextView) findViewById(R.id.VlblGasLamp);
         rdogrpGasLamp = (RadioGroup) findViewById(R.id.rdogrpGasLamp);
         rdoGasLamp1 = (RadioButton) findViewById(R.id.rdoGasLamp1);
         rdoGasLamp2 = (RadioButton) findViewById(R.id.rdoGasLamp2);
         rdogrpGasLamp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpGasLamp = new String[] {"0","1"};
             for (int i = 0; i < rdogrpGasLamp.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpGasLamp.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpGasLamp[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secGasLampN.setVisibility(View.VISIBLE);
                lineGasLampN.setVisibility(View.VISIBLE);
             }
             else
             {
                secGasLampN.setVisibility(View.GONE);
                lineGasLampN.setVisibility(View.GONE);
                txtGasLampN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secGasLampN=(LinearLayout)findViewById(R.id.secGasLampN);
         lineGasLampN=(View)findViewById(R.id.lineGasLampN);
         VlblGasLampN=(TextView) findViewById(R.id.VlblGasLampN);
         txtGasLampN=(EditText) findViewById(R.id.txtGasLampN);
         secPetroleum=(LinearLayout)findViewById(R.id.secPetroleum);
         linePetroleum=(View)findViewById(R.id.linePetroleum);
         VlblPetroleum = (TextView) findViewById(R.id.VlblPetroleum);
         rdogrpPetroleum = (RadioGroup) findViewById(R.id.rdogrpPetroleum);
         rdoPetroleum1 = (RadioButton) findViewById(R.id.rdoPetroleum1);
         rdoPetroleum2 = (RadioButton) findViewById(R.id.rdoPetroleum2);
         rdogrpPetroleum.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpPetroleum = new String[] {"0","1"};
             for (int i = 0; i < rdogrpPetroleum.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpPetroleum.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpPetroleum[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secPetroleumN.setVisibility(View.VISIBLE);
                linePetroleumN.setVisibility(View.VISIBLE);
             }
             else
             {
                secPetroleumN.setVisibility(View.GONE);
                linePetroleumN.setVisibility(View.GONE);
                txtPetroleumN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secPetroleumN=(LinearLayout)findViewById(R.id.secPetroleumN);
         linePetroleumN=(View)findViewById(R.id.linePetroleumN);
         VlblPetroleumN=(TextView) findViewById(R.id.VlblPetroleumN);
         txtPetroleumN=(EditText) findViewById(R.id.txtPetroleumN);
         secMatt=(LinearLayout)findViewById(R.id.secMatt);
         lineMatt=(View)findViewById(R.id.lineMatt);
         VlblMatt = (TextView) findViewById(R.id.VlblMatt);
         rdogrpMatt = (RadioGroup) findViewById(R.id.rdogrpMatt);
         rdoMatt1 = (RadioButton) findViewById(R.id.rdoMatt1);
         rdoMatt2 = (RadioButton) findViewById(R.id.rdoMatt2);
         rdogrpMatt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpMatt = new String[] {"0","1"};
             for (int i = 0; i < rdogrpMatt.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpMatt.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpMatt[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secMattN.setVisibility(View.VISIBLE);
                lineMattN.setVisibility(View.VISIBLE);
             }
             else
             {
                secMattN.setVisibility(View.GONE);
                lineMattN.setVisibility(View.GONE);
                txtMattN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secMattN=(LinearLayout)findViewById(R.id.secMattN);
         lineMattN=(View)findViewById(R.id.lineMattN);
         VlblMattN=(TextView) findViewById(R.id.VlblMattN);
         txtMattN=(EditText) findViewById(R.id.txtMattN);
         secMats=(LinearLayout)findViewById(R.id.secMats);
         lineMats=(View)findViewById(R.id.lineMats);
         VlblMats = (TextView) findViewById(R.id.VlblMats);
         rdogrpMats = (RadioGroup) findViewById(R.id.rdogrpMats);
         rdoMats1 = (RadioButton) findViewById(R.id.rdoMats1);
         rdoMats2 = (RadioButton) findViewById(R.id.rdoMats2);
         rdogrpMats.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpMats = new String[] {"0","1"};
             for (int i = 0; i < rdogrpMats.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpMats.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpMats[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secMatsN.setVisibility(View.VISIBLE);
                lineMatsN.setVisibility(View.VISIBLE);
             }
             else
             {
                secMatsN.setVisibility(View.GONE);
                lineMatsN.setVisibility(View.GONE);
                txtMatsN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secMatsN=(LinearLayout)findViewById(R.id.secMatsN);
         lineMatsN=(View)findViewById(R.id.lineMatsN);
         VlblMatsN=(TextView) findViewById(R.id.VlblMatsN);
         txtMatsN=(EditText) findViewById(R.id.txtMatsN);
         secCarpets=(LinearLayout)findViewById(R.id.secCarpets);
         lineCarpets=(View)findViewById(R.id.lineCarpets);
         VlblCarpets = (TextView) findViewById(R.id.VlblCarpets);
         rdogrpCarpets = (RadioGroup) findViewById(R.id.rdogrpCarpets);
         rdoCarpets1 = (RadioButton) findViewById(R.id.rdoCarpets1);
         rdoCarpets2 = (RadioButton) findViewById(R.id.rdoCarpets2);
         rdogrpCarpets.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpCarpets = new String[] {"0","1"};
             for (int i = 0; i < rdogrpCarpets.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpCarpets.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpCarpets[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secCarpetsN.setVisibility(View.VISIBLE);
                lineCarpetsN.setVisibility(View.VISIBLE);
             }
             else
             {
                secCarpetsN.setVisibility(View.GONE);
                lineCarpetsN.setVisibility(View.GONE);
                txtCarpetsN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secCarpetsN=(LinearLayout)findViewById(R.id.secCarpetsN);
         lineCarpetsN=(View)findViewById(R.id.lineCarpetsN);
         VlblCarpetsN=(TextView) findViewById(R.id.VlblCarpetsN);
         txtCarpetsN=(EditText) findViewById(R.id.txtCarpetsN);
         secBed=(LinearLayout)findViewById(R.id.secBed);
         lineBed=(View)findViewById(R.id.lineBed);
         VlblBed = (TextView) findViewById(R.id.VlblBed);
         rdogrpBed = (RadioGroup) findViewById(R.id.rdogrpBed);
         rdoBed1 = (RadioButton) findViewById(R.id.rdoBed1);
         rdoBed2 = (RadioButton) findViewById(R.id.rdoBed2);
         rdogrpBed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpBed = new String[] {"0","1"};
             for (int i = 0; i < rdogrpBed.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpBed.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpBed[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secBedN.setVisibility(View.VISIBLE);
                lineBedN.setVisibility(View.VISIBLE);
             }
             else
             {
                secBedN.setVisibility(View.GONE);
                lineBedN.setVisibility(View.GONE);
                txtBedN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secBedN=(LinearLayout)findViewById(R.id.secBedN);
         lineBedN=(View)findViewById(R.id.lineBedN);
         VlblBedN=(TextView) findViewById(R.id.VlblBedN);
         txtBedN=(EditText) findViewById(R.id.txtBedN);
         secChair=(LinearLayout)findViewById(R.id.secChair);
         lineChair=(View)findViewById(R.id.lineChair);
         VlblChair = (TextView) findViewById(R.id.VlblChair);
         rdogrpChair = (RadioGroup) findViewById(R.id.rdogrpChair);
         rdoChair1 = (RadioButton) findViewById(R.id.rdoChair1);
         rdoChair2 = (RadioButton) findViewById(R.id.rdoChair2);
         rdogrpChair.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpChair = new String[] {"0","1"};
             for (int i = 0; i < rdogrpChair.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpChair.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpChair[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secChairN.setVisibility(View.VISIBLE);
                lineChairN.setVisibility(View.VISIBLE);
             }
             else
             {
                secChairN.setVisibility(View.GONE);
                lineChairN.setVisibility(View.GONE);
                txtChairN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secChairN=(LinearLayout)findViewById(R.id.secChairN);
         lineChairN=(View)findViewById(R.id.lineChairN);
         VlblChairN=(TextView) findViewById(R.id.VlblChairN);
         txtChairN=(EditText) findViewById(R.id.txtChairN);
         secSofa=(LinearLayout)findViewById(R.id.secSofa);
         lineSofa=(View)findViewById(R.id.lineSofa);
         VlblSofa = (TextView) findViewById(R.id.VlblSofa);
         rdogrpSofa = (RadioGroup) findViewById(R.id.rdogrpSofa);
         rdoSofa1 = (RadioButton) findViewById(R.id.rdoSofa1);
         rdoSofa2 = (RadioButton) findViewById(R.id.rdoSofa2);
         rdogrpSofa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpSofa = new String[] {"0","1"};
             for (int i = 0; i < rdogrpSofa.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpSofa.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpSofa[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secSofaN.setVisibility(View.VISIBLE);
                lineSofaN.setVisibility(View.VISIBLE);
             }
             else
             {
                secSofaN.setVisibility(View.GONE);
                lineSofaN.setVisibility(View.GONE);
                txtSofaN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secSofaN=(LinearLayout)findViewById(R.id.secSofaN);
         lineSofaN=(View)findViewById(R.id.lineSofaN);
         VlblSofaN=(TextView) findViewById(R.id.VlblSofaN);
         txtSofaN=(EditText) findViewById(R.id.txtSofaN);
         secTables=(LinearLayout)findViewById(R.id.secTables);
         lineTables=(View)findViewById(R.id.lineTables);
         VlblTables = (TextView) findViewById(R.id.VlblTables);
         rdogrpTables = (RadioGroup) findViewById(R.id.rdogrpTables);
         rdoTables1 = (RadioButton) findViewById(R.id.rdoTables1);
         rdoTables2 = (RadioButton) findViewById(R.id.rdoTables2);
         rdogrpTables.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpTables = new String[] {"0","1"};
             for (int i = 0; i < rdogrpTables.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpTables.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpTables[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secTablesN.setVisibility(View.VISIBLE);
                lineTablesN.setVisibility(View.VISIBLE);
             }
             else
             {
                secTablesN.setVisibility(View.GONE);
                lineTablesN.setVisibility(View.GONE);
                txtTablesN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secTablesN=(LinearLayout)findViewById(R.id.secTablesN);
         lineTablesN=(View)findViewById(R.id.lineTablesN);
         VlblTablesN=(TextView) findViewById(R.id.VlblTablesN);
         txtTablesN=(EditText) findViewById(R.id.txtTablesN);
         secWatch=(LinearLayout)findViewById(R.id.secWatch);
         lineWatch=(View)findViewById(R.id.lineWatch);
         VlblWatch = (TextView) findViewById(R.id.VlblWatch);
         rdogrpWatch = (RadioGroup) findViewById(R.id.rdogrpWatch);
         rdoWatch1 = (RadioButton) findViewById(R.id.rdoWatch1);
         rdoWatch2 = (RadioButton) findViewById(R.id.rdoWatch2);
         rdogrpWatch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpWatch = new String[] {"0","1"};
             for (int i = 0; i < rdogrpWatch.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpWatch.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpWatch[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secWatchN.setVisibility(View.VISIBLE);
                lineWatchN.setVisibility(View.VISIBLE);
             }
             else
             {
                secWatchN.setVisibility(View.GONE);
                lineWatchN.setVisibility(View.GONE);
                txtWatchN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secWatchN=(LinearLayout)findViewById(R.id.secWatchN);
         lineWatchN=(View)findViewById(R.id.lineWatchN);
         VlblWatchN=(TextView) findViewById(R.id.VlblWatchN);
         txtWatchN=(EditText) findViewById(R.id.txtWatchN);
         secWMachine=(LinearLayout)findViewById(R.id.secWMachine);
         lineWMachine=(View)findViewById(R.id.lineWMachine);
         VlblWMachine = (TextView) findViewById(R.id.VlblWMachine);
         rdogrpWMachine = (RadioGroup) findViewById(R.id.rdogrpWMachine);
         rdoWMachine1 = (RadioButton) findViewById(R.id.rdoWMachine1);
         rdoWMachine2 = (RadioButton) findViewById(R.id.rdoWMachine2);
         rdogrpWMachine.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpWMachine = new String[] {"0","1"};
             for (int i = 0; i < rdogrpWMachine.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpWMachine.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpWMachine[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secWMachineN.setVisibility(View.VISIBLE);
                lineWMachineN.setVisibility(View.VISIBLE);
             }
             else
             {
                secWMachineN.setVisibility(View.GONE);
                lineWMachineN.setVisibility(View.GONE);
                txtWMachineN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secWMachineN=(LinearLayout)findViewById(R.id.secWMachineN);
         lineWMachineN=(View)findViewById(R.id.lineWMachineN);
         VlblWMachineN=(TextView) findViewById(R.id.VlblWMachineN);
         txtWMachineN=(EditText) findViewById(R.id.txtWMachineN);
         secIron=(LinearLayout)findViewById(R.id.secIron);
         lineIron=(View)findViewById(R.id.lineIron);
         VlblIron = (TextView) findViewById(R.id.VlblIron);
         rdogrpIron = (RadioGroup) findViewById(R.id.rdogrpIron);
         rdoIron1 = (RadioButton) findViewById(R.id.rdoIron1);
         rdoIron2 = (RadioButton) findViewById(R.id.rdoIron2);
         rdogrpIron.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpIron = new String[] {"0","1"};
             for (int i = 0; i < rdogrpIron.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpIron.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpIron[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secIronN.setVisibility(View.VISIBLE);
                lineIronN.setVisibility(View.VISIBLE);
             }
             else
             {
                secIronN.setVisibility(View.GONE);
                lineIronN.setVisibility(View.GONE);
                txtIronN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secIronN=(LinearLayout)findViewById(R.id.secIronN);
         lineIronN=(View)findViewById(R.id.lineIronN);
         VlblIronN=(TextView) findViewById(R.id.VlblIronN);
         txtIronN=(EditText) findViewById(R.id.txtIronN);
         secBooth=(LinearLayout)findViewById(R.id.secBooth);
         lineBooth=(View)findViewById(R.id.lineBooth);
         VlblBooth = (TextView) findViewById(R.id.VlblBooth);
         rdogrpBooth = (RadioGroup) findViewById(R.id.rdogrpBooth);
         rdoBooth1 = (RadioButton) findViewById(R.id.rdoBooth1);
         rdoBooth2 = (RadioButton) findViewById(R.id.rdoBooth2);
         rdogrpBooth.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpBooth = new String[] {"0","1"};
             for (int i = 0; i < rdogrpBooth.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpBooth.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpBooth[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secBoothN.setVisibility(View.VISIBLE);
                lineBoothN.setVisibility(View.VISIBLE);
             }
             else
             {
                secBoothN.setVisibility(View.GONE);
                lineBoothN.setVisibility(View.GONE);
                txtBoothN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secBoothN=(LinearLayout)findViewById(R.id.secBoothN);
         lineBoothN=(View)findViewById(R.id.lineBoothN);
         VlblBoothN=(TextView) findViewById(R.id.VlblBoothN);
         txtBoothN=(EditText) findViewById(R.id.txtBoothN);
         secSMachine=(LinearLayout)findViewById(R.id.secSMachine);
         lineSMachine=(View)findViewById(R.id.lineSMachine);
         VlblSMachine = (TextView) findViewById(R.id.VlblSMachine);
         rdogrpSMachine = (RadioGroup) findViewById(R.id.rdogrpSMachine);
         rdoSMachine1 = (RadioButton) findViewById(R.id.rdoSMachine1);
         rdoSMachine2 = (RadioButton) findViewById(R.id.rdoSMachine2);
         rdogrpSMachine.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpSMachine = new String[] {"0","1"};
             for (int i = 0; i < rdogrpSMachine.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpSMachine.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpSMachine[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secSMachineN.setVisibility(View.VISIBLE);
                lineSMachineN.setVisibility(View.VISIBLE);
             }
             else
             {
                secSMachineN.setVisibility(View.GONE);
                lineSMachineN.setVisibility(View.GONE);
                txtSMachineN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secSMachineN=(LinearLayout)findViewById(R.id.secSMachineN);
         lineSMachineN=(View)findViewById(R.id.lineSMachineN);
         VlblSMachineN=(TextView) findViewById(R.id.VlblSMachineN);
         txtSMachineN=(EditText) findViewById(R.id.txtSMachineN);
         secGenerator=(LinearLayout)findViewById(R.id.secGenerator);
         lineGenerator=(View)findViewById(R.id.lineGenerator);
         VlblGenerator = (TextView) findViewById(R.id.VlblGenerator);
         rdogrpGenerator = (RadioGroup) findViewById(R.id.rdogrpGenerator);
         rdoGenerator1 = (RadioButton) findViewById(R.id.rdoGenerator1);
         rdoGenerator2 = (RadioButton) findViewById(R.id.rdoGenerator2);
         rdogrpGenerator.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpGenerator = new String[] {"0","1"};
             for (int i = 0; i < rdogrpGenerator.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpGenerator.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpGenerator[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secGeneratorN.setVisibility(View.VISIBLE);
                lineGeneratorN.setVisibility(View.VISIBLE);
             }
             else
             {
                secGeneratorN.setVisibility(View.GONE);
                lineGeneratorN.setVisibility(View.GONE);
                txtGeneratorN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secGeneratorN=(LinearLayout)findViewById(R.id.secGeneratorN);
         lineGeneratorN=(View)findViewById(R.id.lineGeneratorN);
         VlblGeneratorN=(TextView) findViewById(R.id.VlblGeneratorN);
         txtGeneratorN=(EditText) findViewById(R.id.txtGeneratorN);
         seclblH12a=(LinearLayout)findViewById(R.id.seclblH12a);
         linelblH12a=(View)findViewById(R.id.linelblH12a);
         secInternet=(LinearLayout)findViewById(R.id.secInternet);
         lineInternet=(View)findViewById(R.id.lineInternet);
         VlblInternet = (TextView) findViewById(R.id.VlblInternet);
         rdogrpInternet = (RadioGroup) findViewById(R.id.rdogrpInternet);
         rdoInternet1 = (RadioButton) findViewById(R.id.rdoInternet1);
         rdoInternet2 = (RadioButton) findViewById(R.id.rdoInternet2);
         rdogrpInternet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpInternet = new String[] {"0","1"};
             for (int i = 0; i < rdogrpInternet.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpInternet.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpInternet[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secInternetN.setVisibility(View.VISIBLE);
                lineInternetN.setVisibility(View.VISIBLE);
             }
             else
             {
                secInternetN.setVisibility(View.GONE);
                lineInternetN.setVisibility(View.GONE);
                txtInternetN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secInternetN=(LinearLayout)findViewById(R.id.secInternetN);
         lineInternetN=(View)findViewById(R.id.lineInternetN);
         VlblInternetN=(TextView) findViewById(R.id.VlblInternetN);
         txtInternetN=(EditText) findViewById(R.id.txtInternetN);
         secSatellite=(LinearLayout)findViewById(R.id.secSatellite);
         lineSatellite=(View)findViewById(R.id.lineSatellite);
         VlblSatellite = (TextView) findViewById(R.id.VlblSatellite);
         rdogrpSatellite = (RadioGroup) findViewById(R.id.rdogrpSatellite);
         rdoSatellite1 = (RadioButton) findViewById(R.id.rdoSatellite1);
         rdoSatellite2 = (RadioButton) findViewById(R.id.rdoSatellite2);
         rdogrpSatellite.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpSatellite = new String[] {"0","1"};
             for (int i = 0; i < rdogrpSatellite.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpSatellite.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpSatellite[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secSatelliteN.setVisibility(View.VISIBLE);
                lineSatelliteN.setVisibility(View.VISIBLE);
             }
             else
             {
                secSatelliteN.setVisibility(View.GONE);
                lineSatelliteN.setVisibility(View.GONE);
                txtSatelliteN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secSatelliteN=(LinearLayout)findViewById(R.id.secSatelliteN);
         lineSatelliteN=(View)findViewById(R.id.lineSatelliteN);
         VlblSatelliteN=(TextView) findViewById(R.id.VlblSatelliteN);
         txtSatelliteN=(EditText) findViewById(R.id.txtSatelliteN);
         secLandline=(LinearLayout)findViewById(R.id.secLandline);
         lineLandline=(View)findViewById(R.id.lineLandline);
         VlblLandline = (TextView) findViewById(R.id.VlblLandline);
         rdogrpLandline = (RadioGroup) findViewById(R.id.rdogrpLandline);
         rdoLandline1 = (RadioButton) findViewById(R.id.rdoLandline1);
         rdoLandline2 = (RadioButton) findViewById(R.id.rdoLandline2);
         rdogrpLandline.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpLandline = new String[] {"0","1"};
             for (int i = 0; i < rdogrpLandline.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpLandline.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpLandline[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secLandlineN.setVisibility(View.VISIBLE);
                lineLandlineN.setVisibility(View.VISIBLE);
             }
             else
             {
                secLandlineN.setVisibility(View.GONE);
                lineLandlineN.setVisibility(View.GONE);
                txtLandlineN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secLandlineN=(LinearLayout)findViewById(R.id.secLandlineN);
         lineLandlineN=(View)findViewById(R.id.lineLandlineN);
         VlblLandlineN=(TextView) findViewById(R.id.VlblLandlineN);
         txtLandlineN=(EditText) findViewById(R.id.txtLandlineN);
         secCellphone=(LinearLayout)findViewById(R.id.secCellphone);
         lineCellphone=(View)findViewById(R.id.lineCellphone);
         VlblCellphone = (TextView) findViewById(R.id.VlblCellphone);
         rdogrpCellphone = (RadioGroup) findViewById(R.id.rdogrpCellphone);
         rdoCellphone1 = (RadioButton) findViewById(R.id.rdoCellphone1);
         rdoCellphone2 = (RadioButton) findViewById(R.id.rdoCellphone2);
         rdogrpCellphone.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpCellphone = new String[] {"0","1"};
             for (int i = 0; i < rdogrpCellphone.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpCellphone.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpCellphone[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secCellphoneN.setVisibility(View.VISIBLE);
                lineCellphoneN.setVisibility(View.VISIBLE);
             }
             else
             {
                secCellphoneN.setVisibility(View.GONE);
                lineCellphoneN.setVisibility(View.GONE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secCellphoneN=(LinearLayout)findViewById(R.id.secCellphoneN);
         lineCellphoneN=(View)findViewById(R.id.lineCellphoneN);
         VlblCellphoneN=(TextView) findViewById(R.id.VlblCellphoneN);
         txtCellphoneN=(EditText) findViewById(R.id.txtCellphoneN);
         secTV=(LinearLayout)findViewById(R.id.secTV);
         lineTV=(View)findViewById(R.id.lineTV);
         VlblTV = (TextView) findViewById(R.id.VlblTV);
         rdogrpTV = (RadioGroup) findViewById(R.id.rdogrpTV);
         rdoTV1 = (RadioButton) findViewById(R.id.rdoTV1);
         rdoTV2 = (RadioButton) findViewById(R.id.rdoTV2);
         rdogrpTV.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpTV = new String[] {"0","1"};
             for (int i = 0; i < rdogrpTV.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpTV.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpTV[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secTVN.setVisibility(View.VISIBLE);
                lineTVN.setVisibility(View.VISIBLE);
             }
             else
             {
                secTVN.setVisibility(View.GONE);
                lineTVN.setVisibility(View.GONE);
                txtTVN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secTVN=(LinearLayout)findViewById(R.id.secTVN);
         lineTVN=(View)findViewById(R.id.lineTVN);
         VlblTVN=(TextView) findViewById(R.id.VlblTVN);
         txtTVN=(EditText) findViewById(R.id.txtTVN);
         secTV5=(LinearLayout)findViewById(R.id.secTV5);
         lineTV5=(View)findViewById(R.id.lineTV5);
         VlblTV5 = (TextView) findViewById(R.id.VlblTV5);
         rdogrpTV5 = (RadioGroup) findViewById(R.id.rdogrpTV5);
         rdoTV51 = (RadioButton) findViewById(R.id.rdoTV51);
         rdoTV52 = (RadioButton) findViewById(R.id.rdoTV52);
         rdogrpTV5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpTV5 = new String[] {"0","1"};
             for (int i = 0; i < rdogrpTV5.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpTV5.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpTV5[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secTV5N.setVisibility(View.VISIBLE);
                lineTV5N.setVisibility(View.VISIBLE);
             }
             else
             {
                secTV5N.setVisibility(View.GONE);
                lineTV5N.setVisibility(View.GONE);
                txtTV5N.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secTV5N=(LinearLayout)findViewById(R.id.secTV5N);
         lineTV5N=(View)findViewById(R.id.lineTV5N);
         VlblTV5N=(TextView) findViewById(R.id.VlblTV5N);
         txtTV5N=(EditText) findViewById(R.id.txtTV5N);
         secChannel=(LinearLayout)findViewById(R.id.secChannel);
         lineChannel=(View)findViewById(R.id.lineChannel);
         VlblChannel = (TextView) findViewById(R.id.VlblChannel);
         rdogrpChannel = (RadioGroup) findViewById(R.id.rdogrpChannel);
         rdoChannel1 = (RadioButton) findViewById(R.id.rdoChannel1);
         rdoChannel2 = (RadioButton) findViewById(R.id.rdoChannel2);
         rdogrpChannel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpChannel = new String[] {"0","1"};
             for (int i = 0; i < rdogrpChannel.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpChannel.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpChannel[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secChannelN.setVisibility(View.VISIBLE);
                lineChannelN.setVisibility(View.VISIBLE);
             }
             else
             {
                secChannelN.setVisibility(View.GONE);
                lineChannelN.setVisibility(View.GONE);
                txtChannelN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secChannelN=(LinearLayout)findViewById(R.id.secChannelN);
         lineChannelN=(View)findViewById(R.id.lineChannelN);
         VlblChannelN=(TextView) findViewById(R.id.VlblChannelN);
         txtChannelN=(EditText) findViewById(R.id.txtChannelN);
         secRadio=(LinearLayout)findViewById(R.id.secRadio);
         lineRadio=(View)findViewById(R.id.lineRadio);
         VlblRadio = (TextView) findViewById(R.id.VlblRadio);
         rdogrpRadio = (RadioGroup) findViewById(R.id.rdogrpRadio);
         rdoRadio1 = (RadioButton) findViewById(R.id.rdoRadio1);
         rdoRadio2 = (RadioButton) findViewById(R.id.rdoRadio2);
         rdogrpRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpRadio = new String[] {"0","1"};
             for (int i = 0; i < rdogrpRadio.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpRadio.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpRadio[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secRadioN.setVisibility(View.VISIBLE);
                lineRadioN.setVisibility(View.VISIBLE);
             }
             else
             {
                secRadioN.setVisibility(View.GONE);
                lineRadioN.setVisibility(View.GONE);
                txtRadioN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secRadioN=(LinearLayout)findViewById(R.id.secRadioN);
         lineRadioN=(View)findViewById(R.id.lineRadioN);
         VlblRadioN=(TextView) findViewById(R.id.VlblRadioN);
         txtRadioN=(EditText) findViewById(R.id.txtRadioN);
         secDVD=(LinearLayout)findViewById(R.id.secDVD);
         lineDVD=(View)findViewById(R.id.lineDVD);
         VlblDVD = (TextView) findViewById(R.id.VlblDVD);
         rdogrpDVD = (RadioGroup) findViewById(R.id.rdogrpDVD);
         rdoDVD1 = (RadioButton) findViewById(R.id.rdoDVD1);
         rdoDVD2 = (RadioButton) findViewById(R.id.rdoDVD2);
         rdogrpDVD.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpDVD = new String[] {"0","1"};
             for (int i = 0; i < rdogrpDVD.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpDVD.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpDVD[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secDVDN.setVisibility(View.VISIBLE);
                lineDVDN.setVisibility(View.VISIBLE);
             }
             else
             {
                secDVDN.setVisibility(View.GONE);
                lineDVDN.setVisibility(View.GONE);
                txtDVDN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secDVDN=(LinearLayout)findViewById(R.id.secDVDN);
         lineDVDN=(View)findViewById(R.id.lineDVDN);
         VlblDVDN=(TextView) findViewById(R.id.VlblDVDN);
         txtDVDN=(EditText) findViewById(R.id.txtDVDN);
         secVideo=(LinearLayout)findViewById(R.id.secVideo);
         lineVideo=(View)findViewById(R.id.lineVideo);
         VlblVideo = (TextView) findViewById(R.id.VlblVideo);
         rdogrpVideo = (RadioGroup) findViewById(R.id.rdogrpVideo);
         rdoVideo1 = (RadioButton) findViewById(R.id.rdoVideo1);
         rdoVideo2 = (RadioButton) findViewById(R.id.rdoVideo2);
         rdogrpVideo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpVideo = new String[] {"0","1"};
             for (int i = 0; i < rdogrpVideo.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpVideo.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpVideo[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secVideoN.setVisibility(View.VISIBLE);
                lineVideoN.setVisibility(View.VISIBLE);
             }
             else
             {
                secVideoN.setVisibility(View.GONE);
                lineVideoN.setVisibility(View.GONE);
                txtVideoN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secVideoN=(LinearLayout)findViewById(R.id.secVideoN);
         lineVideoN=(View)findViewById(R.id.lineVideoN);
         VlblVideoN=(TextView) findViewById(R.id.VlblVideoN);
         txtVideoN=(EditText) findViewById(R.id.txtVideoN);
         secComputer=(LinearLayout)findViewById(R.id.secComputer);
         lineComputer=(View)findViewById(R.id.lineComputer);
         VlblComputer = (TextView) findViewById(R.id.VlblComputer);
         rdogrpComputer = (RadioGroup) findViewById(R.id.rdogrpComputer);
         rdoComputer1 = (RadioButton) findViewById(R.id.rdoComputer1);
         rdoComputer2 = (RadioButton) findViewById(R.id.rdoComputer2);
         rdogrpComputer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpComputer = new String[] {"0","1"};
             for (int i = 0; i < rdogrpComputer.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpComputer.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpComputer[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secComputerN.setVisibility(View.VISIBLE);
                lineComputerN.setVisibility(View.VISIBLE);
             }
             else
             {
                secComputerN.setVisibility(View.GONE);
                lineComputerN.setVisibility(View.GONE);
                txtComputerN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secComputerN=(LinearLayout)findViewById(R.id.secComputerN);
         lineComputerN=(View)findViewById(R.id.lineComputerN);
         VlblComputerN=(TextView) findViewById(R.id.VlblComputerN);
         txtComputerN=(EditText) findViewById(R.id.txtComputerN);
         secLaptop=(LinearLayout)findViewById(R.id.secLaptop);
         lineLaptop=(View)findViewById(R.id.lineLaptop);
         VlblLaptop = (TextView) findViewById(R.id.VlblLaptop);
         rdogrpLaptop = (RadioGroup) findViewById(R.id.rdogrpLaptop);
         rdoLaptop1 = (RadioButton) findViewById(R.id.rdoLaptop1);
         rdoLaptop2 = (RadioButton) findViewById(R.id.rdoLaptop2);
         rdogrpLaptop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpLaptop = new String[] {"0","1"};
             for (int i = 0; i < rdogrpLaptop.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpLaptop.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpLaptop[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secLaptopN.setVisibility(View.VISIBLE);
                lineLaptopN.setVisibility(View.VISIBLE);
             }
             else
             {
                secLaptopN.setVisibility(View.GONE);
                lineLaptopN.setVisibility(View.GONE);
                txtLaptopN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secLaptopN=(LinearLayout)findViewById(R.id.secLaptopN);
         lineLaptopN=(View)findViewById(R.id.lineLaptopN);
         VlblLaptopN=(TextView) findViewById(R.id.VlblLaptopN);
         txtLaptopN=(EditText) findViewById(R.id.txtLaptopN);
         secCable=(LinearLayout)findViewById(R.id.secCable);
         lineCable=(View)findViewById(R.id.lineCable);
         VlblCable = (TextView) findViewById(R.id.VlblCable);
         rdogrpCable = (RadioGroup) findViewById(R.id.rdogrpCable);
         rdoCable1 = (RadioButton) findViewById(R.id.rdoCable1);
         rdoCable2 = (RadioButton) findViewById(R.id.rdoCable2);
         rdogrpCable.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpCable = new String[] {"0","1"};
             for (int i = 0; i < rdogrpCable.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpCable.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpCable[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secCableN.setVisibility(View.VISIBLE);
                lineCableN.setVisibility(View.VISIBLE);
             }
             else
             {
                secCableN.setVisibility(View.GONE);
                lineCableN.setVisibility(View.GONE);
                txtCableN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secCableN=(LinearLayout)findViewById(R.id.secCableN);
         lineCableN=(View)findViewById(R.id.lineCableN);
         VlblCableN=(TextView) findViewById(R.id.VlblCableN);
         txtCableN=(EditText) findViewById(R.id.txtCableN);
         seclblH12b=(LinearLayout)findViewById(R.id.seclblH12b);
         linelblH12b=(View)findViewById(R.id.linelblH12b);
         secMicrowave=(LinearLayout)findViewById(R.id.secMicrowave);
         lineMicrowave=(View)findViewById(R.id.lineMicrowave);
         VlblMicrowave = (TextView) findViewById(R.id.VlblMicrowave);
         rdogrpMicrowave = (RadioGroup) findViewById(R.id.rdogrpMicrowave);
         rdoMicrowave1 = (RadioButton) findViewById(R.id.rdoMicrowave1);
         rdoMicrowave2 = (RadioButton) findViewById(R.id.rdoMicrowave2);
         rdogrpMicrowave.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpMicrowave = new String[] {"0","1"};
             for (int i = 0; i < rdogrpMicrowave.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpMicrowave.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpMicrowave[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secMicrowaveN.setVisibility(View.VISIBLE);
                lineMicrowaveN.setVisibility(View.VISIBLE);
             }
             else
             {
                secMicrowaveN.setVisibility(View.GONE);
                lineMicrowaveN.setVisibility(View.GONE);
                txtMicrowaveN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secMicrowaveN=(LinearLayout)findViewById(R.id.secMicrowaveN);
         lineMicrowaveN=(View)findViewById(R.id.lineMicrowaveN);
         VlblMicrowaveN=(TextView) findViewById(R.id.VlblMicrowaveN);
         txtMicrowaveN=(EditText) findViewById(R.id.txtMicrowaveN);
         secGeyser=(LinearLayout)findViewById(R.id.secGeyser);
         lineGeyser=(View)findViewById(R.id.lineGeyser);
         VlblGeyser = (TextView) findViewById(R.id.VlblGeyser);
         rdogrpGeyser = (RadioGroup) findViewById(R.id.rdogrpGeyser);
         rdoGeyser1 = (RadioButton) findViewById(R.id.rdoGeyser1);
         rdoGeyser2 = (RadioButton) findViewById(R.id.rdoGeyser2);
         rdogrpGeyser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpGeyser = new String[] {"0","1"};
             for (int i = 0; i < rdogrpGeyser.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpGeyser.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpGeyser[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secGeyserN.setVisibility(View.VISIBLE);
                lineGeyserN.setVisibility(View.VISIBLE);
             }
             else
             {
                secGeyserN.setVisibility(View.GONE);
                lineGeyserN.setVisibility(View.GONE);
                txtGeyserN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secGeyserN=(LinearLayout)findViewById(R.id.secGeyserN);
         lineGeyserN=(View)findViewById(R.id.lineGeyserN);
         VlblGeyserN=(TextView) findViewById(R.id.VlblGeyserN);
         txtGeyserN=(EditText) findViewById(R.id.txtGeyserN);
         secGrill=(LinearLayout)findViewById(R.id.secGrill);
         lineGrill=(View)findViewById(R.id.lineGrill);
         VlblGrill = (TextView) findViewById(R.id.VlblGrill);
         rdogrpGrill = (RadioGroup) findViewById(R.id.rdogrpGrill);
         rdoGrill1 = (RadioButton) findViewById(R.id.rdoGrill1);
         rdoGrill2 = (RadioButton) findViewById(R.id.rdoGrill2);
         rdogrpGrill.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpGrill = new String[] {"0","1"};
             for (int i = 0; i < rdogrpGrill.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpGrill.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpGrill[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secGrillN.setVisibility(View.VISIBLE);
                lineGrillN.setVisibility(View.VISIBLE);
             }
             else
             {
                secGrillN.setVisibility(View.GONE);
                lineGrillN.setVisibility(View.GONE);
                txtGrillN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secGrillN=(LinearLayout)findViewById(R.id.secGrillN);
         lineGrillN=(View)findViewById(R.id.lineGrillN);
         VlblGrillN=(TextView) findViewById(R.id.VlblGrillN);
         txtGrillN=(EditText) findViewById(R.id.txtGrillN);
         secGrain=(LinearLayout)findViewById(R.id.secGrain);
         lineGrain=(View)findViewById(R.id.lineGrain);
         VlblGrain = (TextView) findViewById(R.id.VlblGrain);
         rdogrpGrain = (RadioGroup) findViewById(R.id.rdogrpGrain);
         rdoGrain1 = (RadioButton) findViewById(R.id.rdoGrain1);
         rdoGrain2 = (RadioButton) findViewById(R.id.rdoGrain2);
         rdogrpGrain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpGrain = new String[] {"0","1"};
             for (int i = 0; i < rdogrpGrain.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpGrain.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpGrain[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secGrainN.setVisibility(View.VISIBLE);
                lineGrainN.setVisibility(View.VISIBLE);
             }
             else
             {
                secGrainN.setVisibility(View.GONE);
                lineGrainN.setVisibility(View.GONE);
                txtGrainN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secGrainN=(LinearLayout)findViewById(R.id.secGrainN);
         lineGrainN=(View)findViewById(R.id.lineGrainN);
         VlblGrainN=(TextView) findViewById(R.id.VlblGrainN);
         txtGrainN=(EditText) findViewById(R.id.txtGrainN);
         secRefrigerator=(LinearLayout)findViewById(R.id.secRefrigerator);
         lineRefrigerator=(View)findViewById(R.id.lineRefrigerator);
         VlblRefrigerator = (TextView) findViewById(R.id.VlblRefrigerator);
         rdogrpRefrigerator = (RadioGroup) findViewById(R.id.rdogrpRefrigerator);
         rdoRefrigerator1 = (RadioButton) findViewById(R.id.rdoRefrigerator1);
         rdoRefrigerator2 = (RadioButton) findViewById(R.id.rdoRefrigerator2);
         rdogrpRefrigerator.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpRefrigerator = new String[] {"0","1"};
             for (int i = 0; i < rdogrpRefrigerator.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpRefrigerator.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpRefrigerator[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secRefrigeratorN.setVisibility(View.VISIBLE);
                lineRefrigeratorN.setVisibility(View.VISIBLE);
             }
             else
             {
                secRefrigeratorN.setVisibility(View.GONE);
                lineRefrigeratorN.setVisibility(View.GONE);
                txtRefrigeratorN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secRefrigeratorN=(LinearLayout)findViewById(R.id.secRefrigeratorN);
         lineRefrigeratorN=(View)findViewById(R.id.lineRefrigeratorN);
         VlblRefrigeratorN=(TextView) findViewById(R.id.VlblRefrigeratorN);
         txtRefrigeratorN=(EditText) findViewById(R.id.txtRefrigeratorN);
         secDeepFreezer=(LinearLayout)findViewById(R.id.secDeepFreezer);
         lineDeepFreezer=(View)findViewById(R.id.lineDeepFreezer);
         VlblDeepFreezer = (TextView) findViewById(R.id.VlblDeepFreezer);
         rdogrpDeepFreezer = (RadioGroup) findViewById(R.id.rdogrpDeepFreezer);
         rdoDeepFreezer1 = (RadioButton) findViewById(R.id.rdoDeepFreezer1);
         rdoDeepFreezer2 = (RadioButton) findViewById(R.id.rdoDeepFreezer2);
         rdogrpDeepFreezer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpDeepFreezer = new String[] {"0","1"};
             for (int i = 0; i < rdogrpDeepFreezer.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpDeepFreezer.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpDeepFreezer[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secDeepFreezerN.setVisibility(View.VISIBLE);
                lineDeepFreezerN.setVisibility(View.VISIBLE);
             }
             else
             {
                secDeepFreezerN.setVisibility(View.GONE);
                lineDeepFreezerN.setVisibility(View.GONE);
                txtDeepFreezerN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secDeepFreezerN=(LinearLayout)findViewById(R.id.secDeepFreezerN);
         lineDeepFreezerN=(View)findViewById(R.id.lineDeepFreezerN);
         VlblDeepFreezerN=(TextView) findViewById(R.id.VlblDeepFreezerN);
         txtDeepFreezerN=(EditText) findViewById(R.id.txtDeepFreezerN);
         secStove=(LinearLayout)findViewById(R.id.secStove);
         lineStove=(View)findViewById(R.id.lineStove);
         VlblStove = (TextView) findViewById(R.id.VlblStove);
         rdogrpStove = (RadioGroup) findViewById(R.id.rdogrpStove);
         rdoStove1 = (RadioButton) findViewById(R.id.rdoStove1);
         rdoStove2 = (RadioButton) findViewById(R.id.rdoStove2);
         rdogrpStove.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpStove = new String[] {"0","1"};
             for (int i = 0; i < rdogrpStove.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpStove.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpStove[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secStoveN.setVisibility(View.VISIBLE);
                lineStoveN.setVisibility(View.VISIBLE);
             }
             else
             {
                secStoveN.setVisibility(View.GONE);
                lineStoveN.setVisibility(View.GONE);
                txtStoveN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secStoveN=(LinearLayout)findViewById(R.id.secStoveN);
         lineStoveN=(View)findViewById(R.id.lineStoveN);
         VlblStoveN=(TextView) findViewById(R.id.VlblStoveN);
         txtStoveN=(EditText) findViewById(R.id.txtStoveN);
         secGasHob=(LinearLayout)findViewById(R.id.secGasHob);
         lineGasHob=(View)findViewById(R.id.lineGasHob);
         VlblGasHob = (TextView) findViewById(R.id.VlblGasHob);
         rdogrpGasHob = (RadioGroup) findViewById(R.id.rdogrpGasHob);
         rdoGasHob1 = (RadioButton) findViewById(R.id.rdoGasHob1);
         rdoGasHob2 = (RadioButton) findViewById(R.id.rdoGasHob2);
         rdogrpGasHob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpGasHob = new String[] {"0","1"};
             for (int i = 0; i < rdogrpGasHob.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpGasHob.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpGasHob[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secGasHobN.setVisibility(View.VISIBLE);
                lineGasHobN.setVisibility(View.VISIBLE);
             }
             else
             {
                secGasHobN.setVisibility(View.GONE);
                lineGasHobN.setVisibility(View.GONE);
                txtGasHobN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secGasHobN=(LinearLayout)findViewById(R.id.secGasHobN);
         lineGasHobN=(View)findViewById(R.id.lineGasHobN);
         VlblGasHobN=(TextView) findViewById(R.id.VlblGasHobN);
         txtGasHobN=(EditText) findViewById(R.id.txtGasHobN);
         secImpCooker=(LinearLayout)findViewById(R.id.secImpCooker);
         lineImpCooker=(View)findViewById(R.id.lineImpCooker);
         VlblImpCooker = (TextView) findViewById(R.id.VlblImpCooker);
         rdogrpImpCooker = (RadioGroup) findViewById(R.id.rdogrpImpCooker);
         rdoImpCooker1 = (RadioButton) findViewById(R.id.rdoImpCooker1);
         rdoImpCooker2 = (RadioButton) findViewById(R.id.rdoImpCooker2);
         rdogrpImpCooker.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpImpCooker = new String[] {"0","1"};
             for (int i = 0; i < rdogrpImpCooker.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpImpCooker.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpImpCooker[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secImpCookerN.setVisibility(View.VISIBLE);
                lineImpCookerN.setVisibility(View.VISIBLE);
             }
             else
             {
                secImpCookerN.setVisibility(View.GONE);
                lineImpCookerN.setVisibility(View.GONE);
                txtImpCookerN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secImpCookerN=(LinearLayout)findViewById(R.id.secImpCookerN);
         lineImpCookerN=(View)findViewById(R.id.lineImpCookerN);
         VlblImpCookerN=(TextView) findViewById(R.id.VlblImpCookerN);
         txtImpCookerN=(EditText) findViewById(R.id.txtImpCookerN);
         seclblH12c=(LinearLayout)findViewById(R.id.seclblH12c);
         linelblH12c=(View)findViewById(R.id.linelblH12c);
         secBike=(LinearLayout)findViewById(R.id.secBike);
         lineBike=(View)findViewById(R.id.lineBike);
         VlblBike = (TextView) findViewById(R.id.VlblBike);
         rdogrpBike = (RadioGroup) findViewById(R.id.rdogrpBike);
         rdoBike1 = (RadioButton) findViewById(R.id.rdoBike1);
         rdoBike2 = (RadioButton) findViewById(R.id.rdoBike2);
         rdogrpBike.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpBike = new String[] {"0","1"};
             for (int i = 0; i < rdogrpBike.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpBike.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpBike[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secBikeN.setVisibility(View.VISIBLE);
                lineBikeN.setVisibility(View.VISIBLE);
             }
             else
             {
                secBikeN.setVisibility(View.GONE);
                lineBikeN.setVisibility(View.GONE);
                txtBikeN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secBikeN=(LinearLayout)findViewById(R.id.secBikeN);
         lineBikeN=(View)findViewById(R.id.lineBikeN);
         VlblBikeN=(TextView) findViewById(R.id.VlblBikeN);
         txtBikeN=(EditText) findViewById(R.id.txtBikeN);
         secMotorcycle=(LinearLayout)findViewById(R.id.secMotorcycle);
         lineMotorcycle=(View)findViewById(R.id.lineMotorcycle);
         VlblMotorcycle = (TextView) findViewById(R.id.VlblMotorcycle);
         rdogrpMotorcycle = (RadioGroup) findViewById(R.id.rdogrpMotorcycle);
         rdoMotorcycle1 = (RadioButton) findViewById(R.id.rdoMotorcycle1);
         rdoMotorcycle2 = (RadioButton) findViewById(R.id.rdoMotorcycle2);
         rdogrpMotorcycle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpMotorcycle = new String[] {"0","1"};
             for (int i = 0; i < rdogrpMotorcycle.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpMotorcycle.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpMotorcycle[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secMotorcycleN.setVisibility(View.VISIBLE);
                lineMotorcycleN.setVisibility(View.VISIBLE);
             }
             else
             {
                secMotorcycleN.setVisibility(View.GONE);
                lineMotorcycleN.setVisibility(View.GONE);
                txtMotorcycleN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secMotorcycleN=(LinearLayout)findViewById(R.id.secMotorcycleN);
         lineMotorcycleN=(View)findViewById(R.id.lineMotorcycleN);
         VlblMotorcycleN=(TextView) findViewById(R.id.VlblMotorcycleN);
         txtMotorcycleN=(EditText) findViewById(R.id.txtMotorcycleN);
         secCar=(LinearLayout)findViewById(R.id.secCar);
         lineCar=(View)findViewById(R.id.lineCar);
         VlblCar = (TextView) findViewById(R.id.VlblCar);
         rdogrpCar = (RadioGroup) findViewById(R.id.rdogrpCar);
         rdoCar1 = (RadioButton) findViewById(R.id.rdoCar1);
         rdoCar2 = (RadioButton) findViewById(R.id.rdoCar2);
         rdogrpCar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpCar = new String[] {"0","1"};
             for (int i = 0; i < rdogrpCar.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpCar.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpCar[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secCarN.setVisibility(View.VISIBLE);
                lineCarN.setVisibility(View.VISIBLE);
             }
             else
             {
                secCarN.setVisibility(View.GONE);
                lineCarN.setVisibility(View.GONE);
                txtCarN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secCarN=(LinearLayout)findViewById(R.id.secCarN);
         lineCarN=(View)findViewById(R.id.lineCarN);
         VlblCarN=(TextView) findViewById(R.id.VlblCarN);
         txtCarN=(EditText) findViewById(R.id.txtCarN);
         secRickshaw=(LinearLayout)findViewById(R.id.secRickshaw);
         lineRickshaw=(View)findViewById(R.id.lineRickshaw);
         VlblRickshaw = (TextView) findViewById(R.id.VlblRickshaw);
         rdogrpRickshaw = (RadioGroup) findViewById(R.id.rdogrpRickshaw);
         rdoRickshaw1 = (RadioButton) findViewById(R.id.rdoRickshaw1);
         rdoRickshaw2 = (RadioButton) findViewById(R.id.rdoRickshaw2);
         rdogrpRickshaw.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpRickshaw = new String[] {"0","1"};
             for (int i = 0; i < rdogrpRickshaw.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpRickshaw.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpRickshaw[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secRickshawN.setVisibility(View.VISIBLE);
                lineRickshawN.setVisibility(View.VISIBLE);
             }
             else
             {
                secRickshawN.setVisibility(View.GONE);
                lineRickshawN.setVisibility(View.GONE);
                txtRickshawN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secRickshawN=(LinearLayout)findViewById(R.id.secRickshawN);
         lineRickshawN=(View)findViewById(R.id.lineRickshawN);
         VlblRickshawN=(TextView) findViewById(R.id.VlblRickshawN);
         txtRickshawN=(EditText) findViewById(R.id.txtRickshawN);
         secCart=(LinearLayout)findViewById(R.id.secCart);
         lineCart=(View)findViewById(R.id.lineCart);
         VlblCart = (TextView) findViewById(R.id.VlblCart);
         rdogrpCart = (RadioGroup) findViewById(R.id.rdogrpCart);
         rdoCart1 = (RadioButton) findViewById(R.id.rdoCart1);
         rdoCart2 = (RadioButton) findViewById(R.id.rdoCart2);
         rdogrpCart.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpCart = new String[] {"0","1"};
             for (int i = 0; i < rdogrpCart.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpCart.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpCart[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secCartN.setVisibility(View.VISIBLE);
                lineCartN.setVisibility(View.VISIBLE);
             }
             else
             {
                secCartN.setVisibility(View.GONE);
                lineCartN.setVisibility(View.GONE);
                txtCartN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secCartN=(LinearLayout)findViewById(R.id.secCartN);
         lineCartN=(View)findViewById(R.id.lineCartN);
         VlblCartN=(TextView) findViewById(R.id.VlblCartN);
         txtCartN=(EditText) findViewById(R.id.txtCartN);
         secCanoe=(LinearLayout)findViewById(R.id.secCanoe);
         lineCanoe=(View)findViewById(R.id.lineCanoe);
         VlblCanoe = (TextView) findViewById(R.id.VlblCanoe);
         rdogrpCanoe = (RadioGroup) findViewById(R.id.rdogrpCanoe);
         rdoCanoe1 = (RadioButton) findViewById(R.id.rdoCanoe1);
         rdoCanoe2 = (RadioButton) findViewById(R.id.rdoCanoe2);
         rdogrpCanoe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpCanoe = new String[] {"0","1"};
             for (int i = 0; i < rdogrpCanoe.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpCanoe.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpCanoe[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secCanoeN.setVisibility(View.VISIBLE);
                lineCanoeN.setVisibility(View.VISIBLE);
             }
             else
             {
                secCanoeN.setVisibility(View.GONE);
                lineCanoeN.setVisibility(View.GONE);
                txtCanoeN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secCanoeN=(LinearLayout)findViewById(R.id.secCanoeN);
         lineCanoeN=(View)findViewById(R.id.lineCanoeN);
         VlblCanoeN=(TextView) findViewById(R.id.VlblCanoeN);
         txtCanoeN=(EditText) findViewById(R.id.txtCanoeN);
         secBus=(LinearLayout)findViewById(R.id.secBus);
         lineBus=(View)findViewById(R.id.lineBus);
         VlblBus = (TextView) findViewById(R.id.VlblBus);
         rdogrpBus = (RadioGroup) findViewById(R.id.rdogrpBus);
         rdoBus1 = (RadioButton) findViewById(R.id.rdoBus1);
         rdoBus2 = (RadioButton) findViewById(R.id.rdoBus2);
         rdogrpBus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpBus = new String[] {"0","1"};
             for (int i = 0; i < rdogrpBus.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpBus.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpBus[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secBusN.setVisibility(View.VISIBLE);
                lineBusN.setVisibility(View.VISIBLE);
             }
             else
             {
                secBusN.setVisibility(View.GONE);
                lineBusN.setVisibility(View.GONE);
                txtBusN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secBusN=(LinearLayout)findViewById(R.id.secBusN);
         lineBusN=(View)findViewById(R.id.lineBusN);
         VlblBusN=(TextView) findViewById(R.id.VlblBusN);
         txtBusN=(EditText) findViewById(R.id.txtBusN);
         seclblH12d=(LinearLayout)findViewById(R.id.seclblH12d);
         linelblH12d=(View)findViewById(R.id.linelblH12d);
         secTractor=(LinearLayout)findViewById(R.id.secTractor);
         lineTractor=(View)findViewById(R.id.lineTractor);
         VlblTractor = (TextView) findViewById(R.id.VlblTractor);
         rdogrpTractor = (RadioGroup) findViewById(R.id.rdogrpTractor);
         rdoTractor1 = (RadioButton) findViewById(R.id.rdoTractor1);
         rdoTractor2 = (RadioButton) findViewById(R.id.rdoTractor2);
         rdogrpTractor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpTractor = new String[] {"0","1"};
             for (int i = 0; i < rdogrpTractor.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpTractor.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpTractor[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secTractorN.setVisibility(View.VISIBLE);
                lineTractorN.setVisibility(View.VISIBLE);
             }
             else
             {
                secTractorN.setVisibility(View.GONE);
                lineTractorN.setVisibility(View.GONE);
                txtTractorN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secTractorN=(LinearLayout)findViewById(R.id.secTractorN);
         lineTractorN=(View)findViewById(R.id.lineTractorN);
         VlblTractorN=(TextView) findViewById(R.id.VlblTractorN);
         txtTractorN=(EditText) findViewById(R.id.txtTractorN);
         secPlow=(LinearLayout)findViewById(R.id.secPlow);
         linePlow=(View)findViewById(R.id.linePlow);
         VlblPlow = (TextView) findViewById(R.id.VlblPlow);
         rdogrpPlow = (RadioGroup) findViewById(R.id.rdogrpPlow);
         rdoPlow1 = (RadioButton) findViewById(R.id.rdoPlow1);
         rdoPlow2 = (RadioButton) findViewById(R.id.rdoPlow2);
         rdogrpPlow.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpPlow = new String[] {"0","1"};
             for (int i = 0; i < rdogrpPlow.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpPlow.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpPlow[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secPlowN.setVisibility(View.VISIBLE);
                linePlowN.setVisibility(View.VISIBLE);
             }
             else
             {
                secPlowN.setVisibility(View.GONE);
                linePlowN.setVisibility(View.GONE);
                txtPlowN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secPlowN=(LinearLayout)findViewById(R.id.secPlowN);
         linePlowN=(View)findViewById(R.id.linePlowN);
         VlblPlowN=(TextView) findViewById(R.id.VlblPlowN);
         txtPlowN=(EditText) findViewById(R.id.txtPlowN);
         secDuck=(LinearLayout)findViewById(R.id.secDuck);
         lineDuck=(View)findViewById(R.id.lineDuck);
         VlblDuck = (TextView) findViewById(R.id.VlblDuck);
         rdogrpDuck = (RadioGroup) findViewById(R.id.rdogrpDuck);
         rdoDuck1 = (RadioButton) findViewById(R.id.rdoDuck1);
         rdoDuck2 = (RadioButton) findViewById(R.id.rdoDuck2);
         rdogrpDuck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpDuck = new String[] {"0","1"};
             for (int i = 0; i < rdogrpDuck.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpDuck.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpDuck[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secDuckN.setVisibility(View.VISIBLE);
                lineDuckN.setVisibility(View.VISIBLE);
             }
             else
             {
                secDuckN.setVisibility(View.GONE);
                lineDuckN.setVisibility(View.GONE);
                txtDuckN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secDuckN=(LinearLayout)findViewById(R.id.secDuckN);
         lineDuckN=(View)findViewById(R.id.lineDuckN);
         VlblDuckN=(TextView) findViewById(R.id.VlblDuckN);
         txtDuckN=(EditText) findViewById(R.id.txtDuckN);
         secCow=(LinearLayout)findViewById(R.id.secCow);
         lineCow=(View)findViewById(R.id.lineCow);
         VlblCow = (TextView) findViewById(R.id.VlblCow);
         rdogrpCow = (RadioGroup) findViewById(R.id.rdogrpCow);
         rdoCow1 = (RadioButton) findViewById(R.id.rdoCow1);
         rdoCow2 = (RadioButton) findViewById(R.id.rdoCow2);
         rdogrpCow.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpCow = new String[] {"0","1"};
             for (int i = 0; i < rdogrpCow.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpCow.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpCow[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secCowN.setVisibility(View.VISIBLE);
                lineCowN.setVisibility(View.VISIBLE);
             }
             else
             {
                secCowN.setVisibility(View.GONE);
                lineCowN.setVisibility(View.GONE);
                txtCowN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secCowN=(LinearLayout)findViewById(R.id.secCowN);
         lineCowN=(View)findViewById(R.id.lineCowN);
         VlblCowN=(TextView) findViewById(R.id.VlblCowN);
         txtCowN=(EditText) findViewById(R.id.txtCowN);
         secSheep=(LinearLayout)findViewById(R.id.secSheep);
         lineSheep=(View)findViewById(R.id.lineSheep);
         VlblSheep = (TextView) findViewById(R.id.VlblSheep);
         rdogrpSheep = (RadioGroup) findViewById(R.id.rdogrpSheep);
         rdoSheep1 = (RadioButton) findViewById(R.id.rdoSheep1);
         rdoSheep2 = (RadioButton) findViewById(R.id.rdoSheep2);
         rdogrpSheep.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpSheep = new String[] {"0","1"};
             for (int i = 0; i < rdogrpSheep.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpSheep.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpSheep[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secSheepN.setVisibility(View.VISIBLE);
                lineSheepN.setVisibility(View.VISIBLE);
             }
             else
             {
                secSheepN.setVisibility(View.GONE);
                lineSheepN.setVisibility(View.GONE);
                txtSheepN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secSheepN=(LinearLayout)findViewById(R.id.secSheepN);
         lineSheepN=(View)findViewById(R.id.lineSheepN);
         VlblSheepN=(TextView) findViewById(R.id.VlblSheepN);
         txtSheepN=(EditText) findViewById(R.id.txtSheepN);
         secGoat=(LinearLayout)findViewById(R.id.secGoat);
         lineGoat=(View)findViewById(R.id.lineGoat);
         VlblGoat = (TextView) findViewById(R.id.VlblGoat);
         rdogrpGoat = (RadioGroup) findViewById(R.id.rdogrpGoat);
         rdoGoat1 = (RadioButton) findViewById(R.id.rdoGoat1);
         rdoGoat2 = (RadioButton) findViewById(R.id.rdoGoat2);
         rdogrpGoat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpGoat = new String[] {"0","1"};
             for (int i = 0; i < rdogrpGoat.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpGoat.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpGoat[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secGoatN.setVisibility(View.VISIBLE);
                lineGoatN.setVisibility(View.VISIBLE);
             }
             else
             {
                secGoatN.setVisibility(View.GONE);
                lineGoatN.setVisibility(View.GONE);
                txtGoatN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secGoatN=(LinearLayout)findViewById(R.id.secGoatN);
         lineGoatN=(View)findViewById(R.id.lineGoatN);
         VlblGoatN=(TextView) findViewById(R.id.VlblGoatN);
         txtGoatN=(EditText) findViewById(R.id.txtGoatN);
         secChicken=(LinearLayout)findViewById(R.id.secChicken);
         lineChicken=(View)findViewById(R.id.lineChicken);
         VlblChicken = (TextView) findViewById(R.id.VlblChicken);
         rdogrpChicken = (RadioGroup) findViewById(R.id.rdogrpChicken);
         rdoChicken1 = (RadioButton) findViewById(R.id.rdoChicken1);
         rdoChicken2 = (RadioButton) findViewById(R.id.rdoChicken2);
         rdogrpChicken.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpChicken = new String[] {"0","1"};
             for (int i = 0; i < rdogrpChicken.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpChicken.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpChicken[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secChickenN.setVisibility(View.VISIBLE);
                lineChickenN.setVisibility(View.VISIBLE);
             }
             else
             {
                secChickenN.setVisibility(View.GONE);
                lineChickenN.setVisibility(View.GONE);
                txtChickenN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secChickenN=(LinearLayout)findViewById(R.id.secChickenN);
         lineChickenN=(View)findViewById(R.id.lineChickenN);
         VlblChickenN=(TextView) findViewById(R.id.VlblChickenN);
         txtChickenN=(EditText) findViewById(R.id.txtChickenN);
         secDonkey=(LinearLayout)findViewById(R.id.secDonkey);
         lineDonkey=(View)findViewById(R.id.lineDonkey);
         VlblDonkey = (TextView) findViewById(R.id.VlblDonkey);
         rdogrpDonkey = (RadioGroup) findViewById(R.id.rdogrpDonkey);
         rdoDonkey1 = (RadioButton) findViewById(R.id.rdoDonkey1);
         rdoDonkey2 = (RadioButton) findViewById(R.id.rdoDonkey2);
         rdogrpDonkey.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpDonkey = new String[] {"0","1"};
             for (int i = 0; i < rdogrpDonkey.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpDonkey.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpDonkey[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secDunkeyN.setVisibility(View.VISIBLE);
                lineDunkeyN.setVisibility(View.VISIBLE);
             }
             else
             {
                secDunkeyN.setVisibility(View.GONE);
                lineDunkeyN.setVisibility(View.GONE);
                txtDunkeyN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secDunkeyN=(LinearLayout)findViewById(R.id.secDunkeyN);
         lineDunkeyN=(View)findViewById(R.id.lineDunkeyN);
         VlblDunkeyN=(TextView) findViewById(R.id.VlblDunkeyN);
         txtDunkeyN=(EditText) findViewById(R.id.txtDunkeyN);
         secHorse=(LinearLayout)findViewById(R.id.secHorse);
         lineHorse=(View)findViewById(R.id.lineHorse);
         VlblHorse = (TextView) findViewById(R.id.VlblHorse);
         rdogrpHorse = (RadioGroup) findViewById(R.id.rdogrpHorse);
         rdoHorse1 = (RadioButton) findViewById(R.id.rdoHorse1);
         rdoHorse2 = (RadioButton) findViewById(R.id.rdoHorse2);
         rdogrpHorse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpHorse = new String[] {"0","1"};
             for (int i = 0; i < rdogrpHorse.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpHorse.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpHorse[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secHorseN.setVisibility(View.VISIBLE);
                lineHorseN.setVisibility(View.VISIBLE);
             }
             else
             {
                secHorseN.setVisibility(View.GONE);
                lineHorseN.setVisibility(View.GONE);
                txtHorseN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secHorseN=(LinearLayout)findViewById(R.id.secHorseN);
         lineHorseN=(View)findViewById(R.id.lineHorseN);
         VlblHorseN=(TextView) findViewById(R.id.VlblHorseN);
         txtHorseN=(EditText) findViewById(R.id.txtHorseN);
         secPig=(LinearLayout)findViewById(R.id.secPig);
         linePig=(View)findViewById(R.id.linePig);
         VlblPig = (TextView) findViewById(R.id.VlblPig);
         rdogrpPig = (RadioGroup) findViewById(R.id.rdogrpPig);
         rdoPig1 = (RadioButton) findViewById(R.id.rdoPig1);
         rdoPig2 = (RadioButton) findViewById(R.id.rdoPig2);
         rdogrpPig.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpPig = new String[] {"0","1"};
             for (int i = 0; i < rdogrpPig.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpPig.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpPig[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secPigN.setVisibility(View.VISIBLE);
                linePigN.setVisibility(View.VISIBLE);
             }
             else
             {
                secPigN.setVisibility(View.GONE);
                linePigN.setVisibility(View.GONE);
                txtPigN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secPigN=(LinearLayout)findViewById(R.id.secPigN);
         linePigN=(View)findViewById(R.id.linePigN);
         VlblPigN=(TextView) findViewById(R.id.VlblPigN);
         txtPigN=(EditText) findViewById(R.id.txtPigN);
         secBirds=(LinearLayout)findViewById(R.id.secBirds);
         lineBirds=(View)findViewById(R.id.lineBirds);
         VlblBirds = (TextView) findViewById(R.id.VlblBirds);
         rdogrpBirds = (RadioGroup) findViewById(R.id.rdogrpBirds);
         rdoBirds1 = (RadioButton) findViewById(R.id.rdoBirds1);
         rdoBirds2 = (RadioButton) findViewById(R.id.rdoBirds2);
         rdogrpBirds.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpBirds = new String[] {"0","1"};
             for (int i = 0; i < rdogrpBirds.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpBirds.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpBirds[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secBirdsN.setVisibility(View.VISIBLE);
                lineBirdsN.setVisibility(View.VISIBLE);
             }
             else
             {
                secBirdsN.setVisibility(View.GONE);
                lineBirdsN.setVisibility(View.GONE);
                txtBirdsN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secBirdsN=(LinearLayout)findViewById(R.id.secBirdsN);
         lineBirdsN=(View)findViewById(R.id.lineBirdsN);
         VlblBirdsN=(TextView) findViewById(R.id.VlblBirdsN);
         txtBirdsN=(EditText) findViewById(R.id.txtBirdsN);
         secDogs=(LinearLayout)findViewById(R.id.secDogs);
         lineDogs=(View)findViewById(R.id.lineDogs);
         VlblDogs = (TextView) findViewById(R.id.VlblDogs);
         rdogrpDogs = (RadioGroup) findViewById(R.id.rdogrpDogs);
         rdoDogs1 = (RadioButton) findViewById(R.id.rdoDogs1);
         rdoDogs2 = (RadioButton) findViewById(R.id.rdoDogs2);
         rdogrpDogs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpDogs = new String[] {"0","1"};
             for (int i = 0; i < rdogrpDogs.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpDogs.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpDogs[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secDogsN.setVisibility(View.VISIBLE);
                lineDogsN.setVisibility(View.VISIBLE);
             }
             else
             {
                secDogsN.setVisibility(View.GONE);
                lineDogsN.setVisibility(View.GONE);
                txtDogsN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secDogsN=(LinearLayout)findViewById(R.id.secDogsN);
         lineDogsN=(View)findViewById(R.id.lineDogsN);
         VlblDogsN=(TextView) findViewById(R.id.VlblDogsN);
         txtDogsN=(EditText) findViewById(R.id.txtDogsN);
         secCats=(LinearLayout)findViewById(R.id.secCats);
         lineCats=(View)findViewById(R.id.lineCats);
         VlblCats = (TextView) findViewById(R.id.VlblCats);
         rdogrpCats = (RadioGroup) findViewById(R.id.rdogrpCats);
         rdoCats1 = (RadioButton) findViewById(R.id.rdoCats1);
         rdoCats2 = (RadioButton) findViewById(R.id.rdoCats2);
         rdogrpCats.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpCats = new String[] {"0","1"};
             for (int i = 0; i < rdogrpCats.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpCats.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpCats[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secCatsN.setVisibility(View.VISIBLE);
                lineCatsN.setVisibility(View.VISIBLE);
             }
             else
             {
                secCatsN.setVisibility(View.GONE);
                lineCatsN.setVisibility(View.GONE);
                txtCatsN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secCatsN=(LinearLayout)findViewById(R.id.secCatsN);
         lineCatsN=(View)findViewById(R.id.lineCatsN);
         VlblCatsN=(TextView) findViewById(R.id.VlblCatsN);
         txtCatsN=(EditText) findViewById(R.id.txtCatsN);
         secFishNet=(LinearLayout)findViewById(R.id.secFishNet);
         lineFishNet=(View)findViewById(R.id.lineFishNet);
         VlblFishNet = (TextView) findViewById(R.id.VlblFishNet);
         rdogrpFishNet = (RadioGroup) findViewById(R.id.rdogrpFishNet);
         rdoFishNet1 = (RadioButton) findViewById(R.id.rdoFishNet1);
         rdoFishNet2 = (RadioButton) findViewById(R.id.rdoFishNet2);
         rdogrpFishNet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpFishNet = new String[] {"0","1"};
             for (int i = 0; i < rdogrpFishNet.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpFishNet.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpFishNet[i];
             }

             if(rbData.equalsIgnoreCase("1"))
             {
                secFishNetN.setVisibility(View.VISIBLE);
                lineFishNetN.setVisibility(View.VISIBLE);
             }
             else
             {
                secFishNetN.setVisibility(View.GONE);
                lineFishNetN.setVisibility(View.GONE);
                txtFishNetN.setText("");
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         secFishNetN=(LinearLayout)findViewById(R.id.secFishNetN);
         lineFishNetN=(View)findViewById(R.id.lineFishNetN);
         VlblFishNetN=(TextView) findViewById(R.id.VlblFishNetN);
         txtFishNetN=(EditText) findViewById(R.id.txtFishNetN);
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

             if(rbData.equalsIgnoreCase("0"))
             {
                    secOtherAsset1.setVisibility(View.GONE);
                    lineOtherAsset1.setVisibility(View.GONE);
                    txtOtherAsset1.setText("");
                    secOtherAsset1N.setVisibility(View.GONE);
                    lineOtherAsset1N.setVisibility(View.GONE);
                    txtOtherAsset1N.setText("");
                    secOtherAsset2.setVisibility(View.GONE);
                    lineOtherAsset2.setVisibility(View.GONE);
                    txtOtherAsset2.setText("");
                    secOtherAsset2N.setVisibility(View.GONE);
                    lineOtherAsset2N.setVisibility(View.GONE);
                    txtOtherAsset2N.setText("");
                    secOtherAsset3.setVisibility(View.GONE);
                    lineOtherAsset3.setVisibility(View.GONE);
                    txtOtherAsset3.setText("");
                    secOtherAsset3N.setVisibility(View.GONE);
                    lineOtherAsset3N.setVisibility(View.GONE);
                    txtOtherAsset3N.setText("");
             }
             else
             {
                    secOtherAsset1.setVisibility(View.VISIBLE);
                    lineOtherAsset1.setVisibility(View.VISIBLE);
                    secOtherAsset1N.setVisibility(View.VISIBLE);
                    lineOtherAsset1N.setVisibility(View.VISIBLE);
                    secOtherAsset2.setVisibility(View.VISIBLE);
                    lineOtherAsset2.setVisibility(View.VISIBLE);
                    secOtherAsset2N.setVisibility(View.VISIBLE);
                    lineOtherAsset2N.setVisibility(View.VISIBLE);
                    secOtherAsset3.setVisibility(View.VISIBLE);
                    lineOtherAsset3.setVisibility(View.VISIBLE);
                    secOtherAsset3N.setVisibility(View.VISIBLE);
                    lineOtherAsset3N.setVisibility(View.VISIBLE);
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
         secOtherAsset1N=(LinearLayout)findViewById(R.id.secOtherAsset1N);
         lineOtherAsset1N=(View)findViewById(R.id.lineOtherAsset1N);
         VlblOtherAsset1N=(TextView) findViewById(R.id.VlblOtherAsset1N);
         txtOtherAsset1N=(EditText) findViewById(R.id.txtOtherAsset1N);
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
         secOtherAsset2N=(LinearLayout)findViewById(R.id.secOtherAsset2N);
         lineOtherAsset2N=(View)findViewById(R.id.lineOtherAsset2N);
         VlblOtherAsset2N=(TextView) findViewById(R.id.VlblOtherAsset2N);
         txtOtherAsset2N=(EditText) findViewById(R.id.txtOtherAsset2N);
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
         secOtherAsset3N=(LinearLayout)findViewById(R.id.secOtherAsset3N);
         lineOtherAsset3N=(View)findViewById(R.id.lineOtherAsset3N);
         VlblOtherAsset3N=(TextView) findViewById(R.id.VlblOtherAsset3N);
         txtOtherAsset3N=(EditText) findViewById(R.id.txtOtherAsset3N);
         secSESNote=(LinearLayout)findViewById(R.id.secSESNote);
         lineSESNote=(View)findViewById(R.id.lineSESNote);
         VlblSESNote=(TextView) findViewById(R.id.VlblSESNote);
         txtSESNote=(EditText) findViewById(R.id.txtSESNote);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_SES_Mali.this, e.getMessage());
         return;
     }
 }

 private void DataSave()
 {
   try
     {
        if(!rdoSESVStatus2.isChecked()) {
           String ValidationMSG = ValidationCheck();
           if(ValidationMSG.length()>0)
           {
              Connection.MessageBox(Surv_SES_Mali.this, ValidationMSG);
              return;
           }
        }

 
         String SQL = "";
         RadioButton rb;

         tmpSES_Mali_DataModel objSave = new tmpSES_Mali_DataModel();
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
         String[] d_rdogrpWaterSource = new String[] {"1","2","7","8","9"};
         objSave.setWaterSource("");
         for (int i = 0; i < rdogrpWaterSource.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpWaterSource.getChildAt(i);
             if (rb.isChecked()) objSave.setWaterSource(d_rdogrpWaterSource[i]);
         }

         objSave.setFetchWaterM(txtFetchWaterM.getText().toString());
         String[] d_rdogrpFetchWaterMDk = new String[] {"98","99"};
         objSave.setFetchWaterMDk("");
         for (int i = 0; i < rdogrpFetchWaterMDk.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpFetchWaterMDk.getChildAt(i);
             if (rb.isChecked()) objSave.setFetchWaterMDk(d_rdogrpFetchWaterMDk[i]);
         }

         String[] d_rdogrpGetWater = new String[] {"1","2","7"};
         objSave.setGetWater("");
         for (int i = 0; i < rdogrpGetWater.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGetWater.getChildAt(i);
             if (rb.isChecked()) objSave.setGetWater(d_rdogrpGetWater[i]);
         }

         objSave.setGetWaterOth(txtGetWaterOth.getText().toString());
         objSave.setMemberID(spnMemberID.getSelectedItemPosition() == 0 ? "" : spnMemberID.getSelectedItem().toString().split("-")[0]);
         objSave.setBringWater(txtBringWater.getText().toString());
         String[] d_rdogrpBringWaterDk = new String[] {"98","99"};
         objSave.setBringWaterDk("");
         for (int i = 0; i < rdogrpBringWaterDk.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpBringWaterDk.getChildAt(i);
             if (rb.isChecked()) objSave.setBringWaterDk(d_rdogrpBringWaterDk[i]);
         }

         String[] d_rdogrpSomeone = new String[] {"0","1"};
         objSave.setSomeone("");
         for (int i = 0; i < rdogrpSomeone.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSomeone.getChildAt(i);
             if (rb.isChecked()) objSave.setSomeone(d_rdogrpSomeone[i]);
         }

         String[] d_rdogrpSecondPers = new String[] {"1","2","7"};
         objSave.setSecondPers("");
         for (int i = 0; i < rdogrpSecondPers.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSecondPers.getChildAt(i);
             if (rb.isChecked()) objSave.setSecondPers(d_rdogrpSecondPers[i]);
         }

         objSave.setSecondPersOth(txtSecondPersOth.getText().toString());
         objSave.setMemberID2nd(spnMemberID2nd.getSelectedItemPosition() == 0 ? "" : spnMemberID2nd.getSelectedItem().toString().split("-")[0]);
         String[] d_rdogrpEnoughWater = new String[] {"0","1","8"};
         objSave.setEnoughWater("");
         for (int i = 0; i < rdogrpEnoughWater.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpEnoughWater.getChildAt(i);
             if (rb.isChecked()) objSave.setEnoughWater(d_rdogrpEnoughWater[i]);
         }

         objSave.setMainWater(spnMainWater.getSelectedItemPosition() == 0 ? "" : spnMainWater.getSelectedItem().toString().split("-")[0]);
         objSave.setMainWaterOth(txtMainWaterOth.getText().toString());
         String[] d_rdogrpSmallTank = new String[] {"0","1","8"};
         objSave.setSmallTank("");
         for (int i = 0; i < rdogrpSmallTank.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSmallTank.getChildAt(i);
             if (rb.isChecked()) objSave.setSmallTank(d_rdogrpSmallTank[i]);
         }

         String[] d_rdogrpMediunTank = new String[] {"0","1","8"};
         objSave.setMediunTank("");
         for (int i = 0; i < rdogrpMediunTank.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMediunTank.getChildAt(i);
             if (rb.isChecked()) objSave.setMediunTank(d_rdogrpMediunTank[i]);
         }

         String[] d_rdogrpLargeTank = new String[] {"0","1","8"};
         objSave.setLargeTank("");
         for (int i = 0; i < rdogrpLargeTank.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLargeTank.getChildAt(i);
             if (rb.isChecked()) objSave.setLargeTank(d_rdogrpLargeTank[i]);
         }

         String[] d_rdogrpStoreDrink = new String[] {"0","1"};
         objSave.setStoreDrink("");
         for (int i = 0; i < rdogrpStoreDrink.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpStoreDrink.getChildAt(i);
             if (rb.isChecked()) objSave.setStoreDrink(d_rdogrpStoreDrink[i]);
         }

         objSave.setContainOpenCov((chkContainOpenCov.isChecked() ? "1" : (secContainOpenCov.isShown() ? "2" : "")));
         objSave.setContainOpenNotCov((chkContainOpenNotCov.isChecked() ? "1" : (secContainOpenNotCov.isShown() ? "2" : "")));
         objSave.setContainOpenDK((chkContainOpenDK.isChecked() ? "1" : (secContainOpenDK.isShown() ? "2" : "")));
         String[] d_rdogrpRecoverWater = new String[] {"1","2","3","7"};
         objSave.setRecoverWater("");
         for (int i = 0; i < rdogrpRecoverWater.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpRecoverWater.getChildAt(i);
             if (rb.isChecked()) objSave.setRecoverWater(d_rdogrpRecoverWater[i]);
         }

         objSave.setRecoverWaterOth(txtRecoverWaterOth.getText().toString());
         String[] d_rdogrpLessDanger = new String[] {"0","1","8"};
         objSave.setLessDanger("");
         for (int i = 0; i < rdogrpLessDanger.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLessDanger.getChildAt(i);
             if (rb.isChecked()) objSave.setLessDanger(d_rdogrpLessDanger[i]);
         }

         objSave.setMakeSafer(spnMakeSafer.getSelectedItemPosition() == 0 ? "" : spnMakeSafer.getSelectedItem().toString().split("-")[0]);
         objSave.setMakeSaferOth(txtMakeSaferOth.getText().toString());
         objSave.setToilet(spnToilet.getSelectedItemPosition() == 0 ? "" : spnToilet.getSelectedItem().toString().split("-")[0]);
         objSave.setToiletOth(txtToiletOth.getText().toString());
         String[] d_rdogrpToiletShrd = new String[] {"0","1","2"};
         objSave.setToiletShrd("");
         for (int i = 0; i < rdogrpToiletShrd.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpToiletShrd.getChildAt(i);
             if (rb.isChecked()) objSave.setToiletShrd(d_rdogrpToiletShrd[i]);
         }

         objSave.setToiletUseNo(txtToiletUseNo.getText().toString());
         String[] d_rdogrpToiletUseNoDk = new String[] {"95","98","99"};
         objSave.setToiletUseNoDk("");
         for (int i = 0; i < rdogrpToiletUseNoDk.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpToiletUseNoDk.getChildAt(i);
             if (rb.isChecked()) objSave.setToiletUseNoDk(d_rdogrpToiletUseNoDk[i]);
         }

         String[] d_rdogrpToiletLoc = new String[] {"1","2","7"};
         objSave.setToiletLoc("");
         for (int i = 0; i < rdogrpToiletLoc.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpToiletLoc.getChildAt(i);
             if (rb.isChecked()) objSave.setToiletLoc(d_rdogrpToiletLoc[i]);
         }

         String[] d_rdogrpContentEmp = new String[] {"1","2","3","4","5","7","8"};
         objSave.setContentEmp("");
         for (int i = 0; i < rdogrpContentEmp.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpContentEmp.getChildAt(i);
             if (rb.isChecked()) objSave.setContentEmp(d_rdogrpContentEmp[i]);
         }

         objSave.setContentEmpOth(txtContentEmpOth.getText().toString());
         objSave.setBowelMov(spnBowelMov.getSelectedItemPosition() == 0 ? "" : spnBowelMov.getSelectedItem().toString().split("-")[0]);
         objSave.setBowelMovOth(txtBowelMovOth.getText().toString());
         String[] d_rdogrpLiquidWaste = new String[] {"1","2","3","4","5","7"};
         objSave.setLiquidWaste("");
         for (int i = 0; i < rdogrpLiquidWaste.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLiquidWaste.getChildAt(i);
             if (rb.isChecked()) objSave.setLiquidWaste(d_rdogrpLiquidWaste[i]);
         }

         objSave.setLiquidWasteOth(txtLiquidWasteOth.getText().toString());
         objSave.setSolidWasteMethod(spnSolidWasteMethod.getSelectedItemPosition() == 0 ? "" : spnSolidWasteMethod.getSelectedItem().toString().split("-")[0]);
         objSave.setSolidWasteMethodOth(txtSolidWasteMethodOth.getText().toString());
         String[] d_rdogrpHandWash = new String[] {"0","1","8"};
         objSave.setHandWash("");
         for (int i = 0; i < rdogrpHandWash.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpHandWash.getChildAt(i);
             if (rb.isChecked()) objSave.setHandWash(d_rdogrpHandWash[i]);
         }

         String[] d_rdogrpShowWash = new String[] {"1","2","3","4","5","6"};
         objSave.setShowWash("");
         for (int i = 0; i < rdogrpShowWash.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpShowWash.getChildAt(i);
             if (rb.isChecked()) objSave.setShowWash(d_rdogrpShowWash[i]);
         }

         String[] d_rdogrpAvailableWat = new String[] {"1","2"};
         objSave.setAvailableWat("");
         for (int i = 0; i < rdogrpAvailableWat.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpAvailableWat.getChildAt(i);
             if (rb.isChecked()) objSave.setAvailableWat(d_rdogrpAvailableWat[i]);
         }

         String[] d_rdogrpAvailableSoap = new String[] {"1","2","3"};
         objSave.setAvailableSoap("");
         for (int i = 0; i < rdogrpAvailableSoap.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpAvailableSoap.getChildAt(i);
             if (rb.isChecked()) objSave.setAvailableSoap(d_rdogrpAvailableSoap[i]);
         }

         objSave.setCookDvc(spnCookDvc.getSelectedItemPosition() == 0 ? "" : spnCookDvc.getSelectedItem().toString().split("-")[0]);
         objSave.setCookDvcOth(txtCookDvcOth.getText().toString());
         objSave.setCookFuel(spnCookFuel.getSelectedItemPosition() == 0 ? "" : spnCookFuel.getSelectedItem().toString().split("-")[0]);
         objSave.setCookFuelOth(txtCookFuelOth.getText().toString());
         objSave.setCookPlc(spnCookPlc.getSelectedItemPosition() == 0 ? "" : spnCookPlc.getSelectedItem().toString().split("-")[0]);
         objSave.setCookPlcOth(txtCookPlcOth.getText().toString());
         objSave.setFloor(spnFloor.getSelectedItemPosition() == 0 ? "" : spnFloor.getSelectedItem().toString().split("-")[0]);
         objSave.setFloorOth(txtFloorOth.getText().toString());
         objSave.setGroundMat(spnGroundMat.getSelectedItemPosition() == 0 ? "" : spnGroundMat.getSelectedItem().toString().split("-")[0]);
         objSave.setGroundMatOth(txtGroundMatOth.getText().toString());
         objSave.setRoof(spnRoof.getSelectedItemPosition() == 0 ? "" : spnRoof.getSelectedItem().toString().split("-")[0]);
         objSave.setRoofOth(txtRoofOth.getText().toString());
         String[] d_rdogrpSmokeInside = new String[] {"0","1","8"};
         objSave.setSmokeInside("");
         for (int i = 0; i < rdogrpSmokeInside.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSmokeInside.getChildAt(i);
             if (rb.isChecked()) objSave.setSmokeInside(d_rdogrpSmokeInside[i]);
         }

         String[] d_rdogrpFreqSmoke = new String[] {"1","2","3","4"};
         objSave.setFreqSmoke("");
         for (int i = 0; i < rdogrpFreqSmoke.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpFreqSmoke.getChildAt(i);
             if (rb.isChecked()) objSave.setFreqSmoke(d_rdogrpFreqSmoke[i]);
         }

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

         objSave.setElecNight(spnElecNight.getSelectedItemPosition() == 0 ? "" : spnElecNight.getSelectedItem().toString().split("-")[0]);
         objSave.setElecNightOth(txtElecNightOth.getText().toString());
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

         objSave.setArea(txtArea.getText().toString());
         String[] d_rdogrpIncomeMo = new String[] {"1","2","3","4","5","6","7","8"};
         objSave.setIncomeMo("");
         for (int i = 0; i < rdogrpIncomeMo.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpIncomeMo.getChildAt(i);
             if (rb.isChecked()) objSave.setIncomeMo(d_rdogrpIncomeMo[i]);
         }

         String[] d_rdogrpExpenses = new String[] {"1","2","3","4","5","6","7","8"};
         objSave.setExpenses("");
         for (int i = 0; i < rdogrpExpenses.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpExpenses.getChildAt(i);
             if (rb.isChecked()) objSave.setExpenses(d_rdogrpExpenses[i]);
         }

         String[] d_rdogrpBankAcc = new String[] {"0","1"};
         objSave.setBankAcc("");
         for (int i = 0; i < rdogrpBankAcc.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpBankAcc.getChildAt(i);
             if (rb.isChecked()) objSave.setBankAcc(d_rdogrpBankAcc[i]);
         }

         String[] d_rdogrpSprayInt = new String[] {"0","1","8"};
         objSave.setSprayInt("");
         for (int i = 0; i < rdogrpSprayInt.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSprayInt.getChildAt(i);
             if (rb.isChecked()) objSave.setSprayInt(d_rdogrpSprayInt[i]);
         }

         String[] d_rdogrpMosqNet = new String[] {"0","1","8"};
         objSave.setMosqNet("");
         for (int i = 0; i < rdogrpMosqNet.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMosqNet.getChildAt(i);
             if (rb.isChecked()) objSave.setMosqNet(d_rdogrpMosqNet[i]);
         }

         objSave.setNetOwned(txtNetOwned.getText().toString());
         String[] d_rdogrpMedHome = new String[] {"0","1","8"};
         objSave.setMedHome("");
         for (int i = 0; i < rdogrpMedHome.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMedHome.getChildAt(i);
             if (rb.isChecked()) objSave.setMedHome(d_rdogrpMedHome[i]);
         }

         objSave.setMedTypeAM((chkMedTypeAM.isChecked() ? "1" : (secMedTypeAM.isShown() ? "2" : "")));
         objSave.setMedTypeAB((chkMedTypeAB.isChecked() ? "1" : (secMedTypeAB.isShown() ? "2" : "")));
         objSave.setMedTypeDK((chkMedTypeDK.isChecked() ? "1" : (secMedTypeDK.isShown() ? "2" : "")));
         objSave.setAntimalarAL((chkAntimalarAL.isChecked() ? "1" : (secAntimalarAL.isShown() ? "2" : "")));
         objSave.setAntimalarASAQ((chkAntimalarASAQ.isChecked() ? "1" : (secAntimalarASAQ.isShown() ? "2" : "")));
         objSave.setAntimalarSP((chkAntimalarSP.isChecked() ? "1" : (secAntimalarSP.isShown() ? "2" : "")));
         objSave.setAntimalarOth((chkAntimalarOth.isChecked() ? "1" : (secAntimalarOth.isShown() ? "2" : "")));
         objSave.setAntimalarSpecifyOth(txtAntimalarSpecifyOth.getText().toString());
         objSave.setGetMedHosp((chkGetMedHosp.isChecked() ? "1" : (secGetMedHosp.isShown() ? "2" : "")));
         objSave.setGetMedCSCom((chkGetMedCSCom.isChecked() ? "1" : (secGetMedCSCom.isShown() ? "2" : "")));
         objSave.setGetMedPrvCl((chkGetMedPrvCl.isChecked() ? "1" : (secGetMedPrvCl.isShown() ? "2" : "")));
         objSave.setGetMedPhar((chkGetMedPhar.isChecked() ? "1" : (secGetMedPhar.isShown() ? "2" : "")));
         objSave.setGetMedPD((chkGetMedPD.isChecked() ? "1" : (secGetMedPD.isShown() ? "2" : "")));
         objSave.setGetMedCHW((chkGetMedCHW.isChecked() ? "1" : (secGetMedCHW.isShown() ? "2" : "")));
         objSave.setGetMedSS((chkGetMedSS.isChecked() ? "1" : (secGetMedSS.isShown() ? "2" : "")));
         objSave.setGetMedOth((chkGetMedOth.isChecked() ? "1" : (secGetMedOth.isShown() ? "2" : "")));
         objSave.setGetMedSpecifyOth(txtGetMedSpecifyOth.getText().toString());
         String[] d_rdogrpAComment = new String[] {"0","1"};
         objSave.setAComment("");
         for (int i = 0; i < rdogrpAComment.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpAComment.getChildAt(i);
             if (rb.isChecked()) objSave.setAComment(d_rdogrpAComment[i]);
         }

         objSave.setComment(txtComment.getText().toString());
         String[] d_rdogrpElectricity = new String[] {"0","1"};
         objSave.setElectricity("");
         for (int i = 0; i < rdogrpElectricity.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpElectricity.getChildAt(i);
             if (rb.isChecked()) objSave.setElectricity(d_rdogrpElectricity[i]);
         }

         String[] d_rdogrpSolarPlate = new String[] {"0","1"};
         objSave.setSolarPlate("");
         for (int i = 0; i < rdogrpSolarPlate.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSolarPlate.getChildAt(i);
             if (rb.isChecked()) objSave.setSolarPlate(d_rdogrpSolarPlate[i]);
         }

         objSave.setSolarPlateN(txtSolarPlateN.getText().toString());
         String[] d_rdogrpHeater = new String[] {"0","1"};
         objSave.setHeater("");
         for (int i = 0; i < rdogrpHeater.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpHeater.getChildAt(i);
             if (rb.isChecked()) objSave.setHeater(d_rdogrpHeater[i]);
         }

         objSave.setHeaterN(txtHeaterN.getText().toString());
         String[] d_rdogrpAC = new String[] {"0","1"};
         objSave.setAC("");
         for (int i = 0; i < rdogrpAC.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpAC.getChildAt(i);
             if (rb.isChecked()) objSave.setAC(d_rdogrpAC[i]);
         }

         objSave.setACN(txtACN.getText().toString());
         String[] d_rdogrpElecFan = new String[] {"0","1"};
         objSave.setElecFan("");
         for (int i = 0; i < rdogrpElecFan.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpElecFan.getChildAt(i);
             if (rb.isChecked()) objSave.setElecFan(d_rdogrpElecFan[i]);
         }

         objSave.setElecFanN(txtElecFanN.getText().toString());
         String[] d_rdogrpLantern = new String[] {"0","1"};
         objSave.setLantern("");
         for (int i = 0; i < rdogrpLantern.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLantern.getChildAt(i);
             if (rb.isChecked()) objSave.setLantern(d_rdogrpLantern[i]);
         }

         objSave.setLanternN(txtLanternN.getText().toString());
         String[] d_rdogrpLamp = new String[] {"0","1"};
         objSave.setLamp("");
         for (int i = 0; i < rdogrpLamp.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLamp.getChildAt(i);
             if (rb.isChecked()) objSave.setLamp(d_rdogrpLamp[i]);
         }

         objSave.setLampN(txtLampN.getText().toString());
         String[] d_rdogrpGasLamp = new String[] {"0","1"};
         objSave.setGasLamp("");
         for (int i = 0; i < rdogrpGasLamp.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGasLamp.getChildAt(i);
             if (rb.isChecked()) objSave.setGasLamp(d_rdogrpGasLamp[i]);
         }

         objSave.setGasLampN(txtGasLampN.getText().toString());
         String[] d_rdogrpPetroleum = new String[] {"0","1"};
         objSave.setPetroleum("");
         for (int i = 0; i < rdogrpPetroleum.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPetroleum.getChildAt(i);
             if (rb.isChecked()) objSave.setPetroleum(d_rdogrpPetroleum[i]);
         }

         objSave.setPetroleumN(txtPetroleumN.getText().toString());
         String[] d_rdogrpMatt = new String[] {"0","1"};
         objSave.setMatt("");
         for (int i = 0; i < rdogrpMatt.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMatt.getChildAt(i);
             if (rb.isChecked()) objSave.setMatt(d_rdogrpMatt[i]);
         }

         objSave.setMattN(txtMattN.getText().toString());
         String[] d_rdogrpMats = new String[] {"0","1"};
         objSave.setMats("");
         for (int i = 0; i < rdogrpMats.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMats.getChildAt(i);
             if (rb.isChecked()) objSave.setMats(d_rdogrpMats[i]);
         }

         objSave.setMatsN(txtMatsN.getText().toString());
         String[] d_rdogrpCarpets = new String[] {"0","1"};
         objSave.setCarpets("");
         for (int i = 0; i < rdogrpCarpets.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCarpets.getChildAt(i);
             if (rb.isChecked()) objSave.setCarpets(d_rdogrpCarpets[i]);
         }

         objSave.setCarpetsN(txtCarpetsN.getText().toString());
         String[] d_rdogrpBed = new String[] {"0","1"};
         objSave.setBed("");
         for (int i = 0; i < rdogrpBed.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpBed.getChildAt(i);
             if (rb.isChecked()) objSave.setBed(d_rdogrpBed[i]);
         }

         objSave.setBedN(txtBedN.getText().toString());
         String[] d_rdogrpChair = new String[] {"0","1"};
         objSave.setChair("");
         for (int i = 0; i < rdogrpChair.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpChair.getChildAt(i);
             if (rb.isChecked()) objSave.setChair(d_rdogrpChair[i]);
         }

         objSave.setChairN(txtChairN.getText().toString());
         String[] d_rdogrpSofa = new String[] {"0","1"};
         objSave.setSofa("");
         for (int i = 0; i < rdogrpSofa.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSofa.getChildAt(i);
             if (rb.isChecked()) objSave.setSofa(d_rdogrpSofa[i]);
         }

         objSave.setSofaN(txtSofaN.getText().toString());
         String[] d_rdogrpTables = new String[] {"0","1"};
         objSave.setTables("");
         for (int i = 0; i < rdogrpTables.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpTables.getChildAt(i);
             if (rb.isChecked()) objSave.setTables(d_rdogrpTables[i]);
         }

         objSave.setTablesN(txtTablesN.getText().toString());
         String[] d_rdogrpWatch = new String[] {"0","1"};
         objSave.setWatch("");
         for (int i = 0; i < rdogrpWatch.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpWatch.getChildAt(i);
             if (rb.isChecked()) objSave.setWatch(d_rdogrpWatch[i]);
         }

         objSave.setWatchN(txtWatchN.getText().toString());
         String[] d_rdogrpWMachine = new String[] {"0","1"};
         objSave.setWMachine("");
         for (int i = 0; i < rdogrpWMachine.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpWMachine.getChildAt(i);
             if (rb.isChecked()) objSave.setWMachine(d_rdogrpWMachine[i]);
         }

         objSave.setWMachineN(txtWMachineN.getText().toString());
         String[] d_rdogrpIron = new String[] {"0","1"};
         objSave.setIron("");
         for (int i = 0; i < rdogrpIron.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpIron.getChildAt(i);
             if (rb.isChecked()) objSave.setIron(d_rdogrpIron[i]);
         }

         objSave.setIronN(txtIronN.getText().toString());
         String[] d_rdogrpBooth = new String[] {"0","1"};
         objSave.setBooth("");
         for (int i = 0; i < rdogrpBooth.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpBooth.getChildAt(i);
             if (rb.isChecked()) objSave.setBooth(d_rdogrpBooth[i]);
         }

         objSave.setBoothN(txtBoothN.getText().toString());
         String[] d_rdogrpSMachine = new String[] {"0","1"};
         objSave.setSMachine("");
         for (int i = 0; i < rdogrpSMachine.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSMachine.getChildAt(i);
             if (rb.isChecked()) objSave.setSMachine(d_rdogrpSMachine[i]);
         }

         objSave.setSMachineN(txtSMachineN.getText().toString());
         String[] d_rdogrpGenerator = new String[] {"0","1"};
         objSave.setGenerator("");
         for (int i = 0; i < rdogrpGenerator.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGenerator.getChildAt(i);
             if (rb.isChecked()) objSave.setGenerator(d_rdogrpGenerator[i]);
         }

         objSave.setGeneratorN(txtGeneratorN.getText().toString());
         String[] d_rdogrpInternet = new String[] {"0","1"};
         objSave.setInternet("");
         for (int i = 0; i < rdogrpInternet.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpInternet.getChildAt(i);
             if (rb.isChecked()) objSave.setInternet(d_rdogrpInternet[i]);
         }

         objSave.setInternetN(txtInternetN.getText().toString());
         String[] d_rdogrpSatellite = new String[] {"0","1"};
         objSave.setSatellite("");
         for (int i = 0; i < rdogrpSatellite.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSatellite.getChildAt(i);
             if (rb.isChecked()) objSave.setSatellite(d_rdogrpSatellite[i]);
         }

         objSave.setSatelliteN(txtSatelliteN.getText().toString());
         String[] d_rdogrpLandline = new String[] {"0","1"};
         objSave.setLandline("");
         for (int i = 0; i < rdogrpLandline.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLandline.getChildAt(i);
             if (rb.isChecked()) objSave.setLandline(d_rdogrpLandline[i]);
         }

         objSave.setLandlineN(txtLandlineN.getText().toString());
         String[] d_rdogrpCellphone = new String[] {"0","1"};
         objSave.setCellphone("");
         for (int i = 0; i < rdogrpCellphone.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCellphone.getChildAt(i);
             if (rb.isChecked()) objSave.setCellphone(d_rdogrpCellphone[i]);
         }

         objSave.setCellphoneN(txtCellphoneN.getText().toString());
         String[] d_rdogrpTV = new String[] {"0","1"};
         objSave.setTV("");
         for (int i = 0; i < rdogrpTV.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpTV.getChildAt(i);
             if (rb.isChecked()) objSave.setTV(d_rdogrpTV[i]);
         }

         objSave.setTVN(txtTVN.getText().toString());
         String[] d_rdogrpTV5 = new String[] {"0","1"};
         objSave.setTV5("");
         for (int i = 0; i < rdogrpTV5.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpTV5.getChildAt(i);
             if (rb.isChecked()) objSave.setTV5(d_rdogrpTV5[i]);
         }

         objSave.setTV5N(txtTV5N.getText().toString());
         String[] d_rdogrpChannel = new String[] {"0","1"};
         objSave.setChannel("");
         for (int i = 0; i < rdogrpChannel.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpChannel.getChildAt(i);
             if (rb.isChecked()) objSave.setChannel(d_rdogrpChannel[i]);
         }

         objSave.setChannelN(txtChannelN.getText().toString());
         String[] d_rdogrpRadio = new String[] {"0","1"};
         objSave.setRadio("");
         for (int i = 0; i < rdogrpRadio.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpRadio.getChildAt(i);
             if (rb.isChecked()) objSave.setRadio(d_rdogrpRadio[i]);
         }

         objSave.setRadioN(txtRadioN.getText().toString());
         String[] d_rdogrpDVD = new String[] {"0","1"};
         objSave.setDVD("");
         for (int i = 0; i < rdogrpDVD.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDVD.getChildAt(i);
             if (rb.isChecked()) objSave.setDVD(d_rdogrpDVD[i]);
         }

         objSave.setDVDN(txtDVDN.getText().toString());
         String[] d_rdogrpVideo = new String[] {"0","1"};
         objSave.setVideo("");
         for (int i = 0; i < rdogrpVideo.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpVideo.getChildAt(i);
             if (rb.isChecked()) objSave.setVideo(d_rdogrpVideo[i]);
         }

         objSave.setVideoN(txtVideoN.getText().toString());
         String[] d_rdogrpComputer = new String[] {"0","1"};
         objSave.setComputer("");
         for (int i = 0; i < rdogrpComputer.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpComputer.getChildAt(i);
             if (rb.isChecked()) objSave.setComputer(d_rdogrpComputer[i]);
         }

         objSave.setComputerN(txtComputerN.getText().toString());
         String[] d_rdogrpLaptop = new String[] {"0","1"};
         objSave.setLaptop("");
         for (int i = 0; i < rdogrpLaptop.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpLaptop.getChildAt(i);
             if (rb.isChecked()) objSave.setLaptop(d_rdogrpLaptop[i]);
         }

         objSave.setLaptopN(txtLaptopN.getText().toString());
         String[] d_rdogrpCable = new String[] {"0","1"};
         objSave.setCable("");
         for (int i = 0; i < rdogrpCable.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCable.getChildAt(i);
             if (rb.isChecked()) objSave.setCable(d_rdogrpCable[i]);
         }

         objSave.setCableN(txtCableN.getText().toString());
         String[] d_rdogrpMicrowave = new String[] {"0","1"};
         objSave.setMicrowave("");
         for (int i = 0; i < rdogrpMicrowave.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMicrowave.getChildAt(i);
             if (rb.isChecked()) objSave.setMicrowave(d_rdogrpMicrowave[i]);
         }

         objSave.setMicrowaveN(txtMicrowaveN.getText().toString());
         String[] d_rdogrpGeyser = new String[] {"0","1"};
         objSave.setGeyser("");
         for (int i = 0; i < rdogrpGeyser.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGeyser.getChildAt(i);
             if (rb.isChecked()) objSave.setGeyser(d_rdogrpGeyser[i]);
         }

         objSave.setGeyserN(txtGeyserN.getText().toString());
         String[] d_rdogrpGrill = new String[] {"0","1"};
         objSave.setGrill("");
         for (int i = 0; i < rdogrpGrill.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGrill.getChildAt(i);
             if (rb.isChecked()) objSave.setGrill(d_rdogrpGrill[i]);
         }

         objSave.setGrillN(txtGrillN.getText().toString());
         String[] d_rdogrpGrain = new String[] {"0","1"};
         objSave.setGrain("");
         for (int i = 0; i < rdogrpGrain.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGrain.getChildAt(i);
             if (rb.isChecked()) objSave.setGrain(d_rdogrpGrain[i]);
         }

         objSave.setGrainN(txtGrainN.getText().toString());
         String[] d_rdogrpRefrigerator = new String[] {"0","1"};
         objSave.setRefrigerator("");
         for (int i = 0; i < rdogrpRefrigerator.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpRefrigerator.getChildAt(i);
             if (rb.isChecked()) objSave.setRefrigerator(d_rdogrpRefrigerator[i]);
         }

         objSave.setRefrigeratorN(txtRefrigeratorN.getText().toString());
         String[] d_rdogrpDeepFreezer = new String[] {"0","1"};
         objSave.setDeepFreezer("");
         for (int i = 0; i < rdogrpDeepFreezer.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDeepFreezer.getChildAt(i);
             if (rb.isChecked()) objSave.setDeepFreezer(d_rdogrpDeepFreezer[i]);
         }

         objSave.setDeepFreezerN(txtDeepFreezerN.getText().toString());
         String[] d_rdogrpStove = new String[] {"0","1"};
         objSave.setStove("");
         for (int i = 0; i < rdogrpStove.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpStove.getChildAt(i);
             if (rb.isChecked()) objSave.setStove(d_rdogrpStove[i]);
         }

         objSave.setStoveN(txtStoveN.getText().toString());
         String[] d_rdogrpGasHob = new String[] {"0","1"};
         objSave.setGasHob("");
         for (int i = 0; i < rdogrpGasHob.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGasHob.getChildAt(i);
             if (rb.isChecked()) objSave.setGasHob(d_rdogrpGasHob[i]);
         }

         objSave.setGasHobN(txtGasHobN.getText().toString());
         String[] d_rdogrpImpCooker = new String[] {"0","1"};
         objSave.setImpCooker("");
         for (int i = 0; i < rdogrpImpCooker.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpImpCooker.getChildAt(i);
             if (rb.isChecked()) objSave.setImpCooker(d_rdogrpImpCooker[i]);
         }

         objSave.setImpCookerN(txtImpCookerN.getText().toString());
         String[] d_rdogrpBike = new String[] {"0","1"};
         objSave.setBike("");
         for (int i = 0; i < rdogrpBike.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpBike.getChildAt(i);
             if (rb.isChecked()) objSave.setBike(d_rdogrpBike[i]);
         }

         objSave.setBikeN(txtBikeN.getText().toString());
         String[] d_rdogrpMotorcycle = new String[] {"0","1"};
         objSave.setMotorcycle("");
         for (int i = 0; i < rdogrpMotorcycle.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMotorcycle.getChildAt(i);
             if (rb.isChecked()) objSave.setMotorcycle(d_rdogrpMotorcycle[i]);
         }

         objSave.setMotorcycleN(txtMotorcycleN.getText().toString());
         String[] d_rdogrpCar = new String[] {"0","1"};
         objSave.setCar("");
         for (int i = 0; i < rdogrpCar.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCar.getChildAt(i);
             if (rb.isChecked()) objSave.setCar(d_rdogrpCar[i]);
         }

         objSave.setCarN(txtCarN.getText().toString());
         String[] d_rdogrpRickshaw = new String[] {"0","1"};
         objSave.setRickshaw("");
         for (int i = 0; i < rdogrpRickshaw.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpRickshaw.getChildAt(i);
             if (rb.isChecked()) objSave.setRickshaw(d_rdogrpRickshaw[i]);
         }

         objSave.setRickshawN(txtRickshawN.getText().toString());
         String[] d_rdogrpCart = new String[] {"0","1"};
         objSave.setCart("");
         for (int i = 0; i < rdogrpCart.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCart.getChildAt(i);
             if (rb.isChecked()) objSave.setCart(d_rdogrpCart[i]);
         }

         objSave.setCartN(txtCartN.getText().toString());
         String[] d_rdogrpCanoe = new String[] {"0","1"};
         objSave.setCanoe("");
         for (int i = 0; i < rdogrpCanoe.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCanoe.getChildAt(i);
             if (rb.isChecked()) objSave.setCanoe(d_rdogrpCanoe[i]);
         }

         objSave.setCanoeN(txtCanoeN.getText().toString());
         String[] d_rdogrpBus = new String[] {"0","1"};
         objSave.setBus("");
         for (int i = 0; i < rdogrpBus.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpBus.getChildAt(i);
             if (rb.isChecked()) objSave.setBus(d_rdogrpBus[i]);
         }

         objSave.setBusN(txtBusN.getText().toString());
         String[] d_rdogrpTractor = new String[] {"0","1"};
         objSave.setTractor("");
         for (int i = 0; i < rdogrpTractor.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpTractor.getChildAt(i);
             if (rb.isChecked()) objSave.setTractor(d_rdogrpTractor[i]);
         }

         objSave.setTractorN(txtTractorN.getText().toString());
         String[] d_rdogrpPlow = new String[] {"0","1"};
         objSave.setPlow("");
         for (int i = 0; i < rdogrpPlow.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPlow.getChildAt(i);
             if (rb.isChecked()) objSave.setPlow(d_rdogrpPlow[i]);
         }

         objSave.setPlowN(txtPlowN.getText().toString());
         String[] d_rdogrpDuck = new String[] {"0","1"};
         objSave.setDuck("");
         for (int i = 0; i < rdogrpDuck.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDuck.getChildAt(i);
             if (rb.isChecked()) objSave.setDuck(d_rdogrpDuck[i]);
         }

         objSave.setDuckN(txtDuckN.getText().toString());
         String[] d_rdogrpCow = new String[] {"0","1"};
         objSave.setCow("");
         for (int i = 0; i < rdogrpCow.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCow.getChildAt(i);
             if (rb.isChecked()) objSave.setCow(d_rdogrpCow[i]);
         }

         objSave.setCowN(txtCowN.getText().toString());
         String[] d_rdogrpSheep = new String[] {"0","1"};
         objSave.setSheep("");
         for (int i = 0; i < rdogrpSheep.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSheep.getChildAt(i);
             if (rb.isChecked()) objSave.setSheep(d_rdogrpSheep[i]);
         }

         objSave.setSheepN(txtSheepN.getText().toString());
         String[] d_rdogrpGoat = new String[] {"0","1"};
         objSave.setGoat("");
         for (int i = 0; i < rdogrpGoat.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGoat.getChildAt(i);
             if (rb.isChecked()) objSave.setGoat(d_rdogrpGoat[i]);
         }

         objSave.setGoatN(txtGoatN.getText().toString());
         String[] d_rdogrpChicken = new String[] {"0","1"};
         objSave.setChicken("");
         for (int i = 0; i < rdogrpChicken.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpChicken.getChildAt(i);
             if (rb.isChecked()) objSave.setChicken(d_rdogrpChicken[i]);
         }

         objSave.setChickenN(txtChickenN.getText().toString());
         String[] d_rdogrpDonkey = new String[] {"0","1"};
         objSave.setDonkey("");
         for (int i = 0; i < rdogrpDonkey.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDonkey.getChildAt(i);
             if (rb.isChecked()) objSave.setDonkey(d_rdogrpDonkey[i]);
         }

         objSave.setDunkeyN(txtDunkeyN.getText().toString());
         String[] d_rdogrpHorse = new String[] {"0","1"};
         objSave.setHorse("");
         for (int i = 0; i < rdogrpHorse.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpHorse.getChildAt(i);
             if (rb.isChecked()) objSave.setHorse(d_rdogrpHorse[i]);
         }

         objSave.setHorseN(txtHorseN.getText().toString());
         String[] d_rdogrpPig = new String[] {"0","1"};
         objSave.setPig("");
         for (int i = 0; i < rdogrpPig.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpPig.getChildAt(i);
             if (rb.isChecked()) objSave.setPig(d_rdogrpPig[i]);
         }

         objSave.setPigN(txtPigN.getText().toString());
         String[] d_rdogrpBirds = new String[] {"0","1"};
         objSave.setBirds("");
         for (int i = 0; i < rdogrpBirds.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpBirds.getChildAt(i);
             if (rb.isChecked()) objSave.setBirds(d_rdogrpBirds[i]);
         }

         objSave.setBirdsN(txtBirdsN.getText().toString());
         String[] d_rdogrpDogs = new String[] {"0","1"};
         objSave.setDogs("");
         for (int i = 0; i < rdogrpDogs.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpDogs.getChildAt(i);
             if (rb.isChecked()) objSave.setDogs(d_rdogrpDogs[i]);
         }

         objSave.setDogsN(txtDogsN.getText().toString());
         String[] d_rdogrpCats = new String[] {"0","1"};
         objSave.setCats("");
         for (int i = 0; i < rdogrpCats.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCats.getChildAt(i);
             if (rb.isChecked()) objSave.setCats(d_rdogrpCats[i]);
         }

         objSave.setCatsN(txtCatsN.getText().toString());
         String[] d_rdogrpFishNet = new String[] {"0","1"};
         objSave.setFishNet("");
         for (int i = 0; i < rdogrpFishNet.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpFishNet.getChildAt(i);
             if (rb.isChecked()) objSave.setFishNet(d_rdogrpFishNet[i]);
         }

         objSave.setFishNetN(txtFishNetN.getText().toString());
         String[] d_rdogrpOtherAsset = new String[] {"0","1"};
         objSave.setOtherAsset("");
         for (int i = 0; i < rdogrpOtherAsset.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpOtherAsset.getChildAt(i);
             if (rb.isChecked()) objSave.setOtherAsset(d_rdogrpOtherAsset[i]);
         }

         objSave.setOtherAsset1(txtOtherAsset1.getText().toString());
         objSave.setOtherAsset1N(txtOtherAsset1N.getText().toString());
         objSave.setOtherAsset2(txtOtherAsset2.getText().toString());
         objSave.setOtherAsset2N(txtOtherAsset2N.getText().toString());
         objSave.setOtherAsset3(txtOtherAsset3.getText().toString());
         objSave.setOtherAsset3N(txtOtherAsset3N.getText().toString());
         objSave.setSESNote(txtSESNote.getText().toString());
         objSave.setStartTime(STARTTIME);
         objSave.setEndTime(g.CurrentTime24());
         objSave.setDeviceID(DEVICEID);
         objSave.setEntryUser(ENTRYUSER); //from data entry user list
         objSave.setRnd(ROUND);
         objSave.setLat(MySharedPreferences.getValue(this, "lat"));
         objSave.setLon(MySharedPreferences.getValue(this, "lon"));

         String status = objSave.SaveUpdateData(this);
         if(status.length()==0) {
             Intent returnIntent = new Intent();
             returnIntent.putExtra("res", "");
             setResult(Activity.RESULT_OK, returnIntent);

             Toast.makeText(Surv_SES_Mali.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
             finish();
         }
         else{
             Connection.MessageBox(Surv_SES_Mali.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Surv_SES_Mali.this, e.getMessage());
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
             ValidationMsg += "\nH1. Required field: What is the main source of drinking water for members of your household ?\n(Circle One Response, Or Write Response For 97).";
             secWSDrink.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtWSDrinkOth.getText().toString().length()==0 & secWSDrinkOth.isShown())
           {
             ValidationMsg += "\nRequired field: Orher Specify.";
             secWSDrinkOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoWaterSource1.isChecked() & !rdoWaterSource2.isChecked() & !rdoWaterSource3.isChecked() & !rdoWaterSource4.isChecked() & !rdoWaterSource5.isChecked() & secWaterSource.isShown())
           {
             ValidationMsg += "\nH1.1. Required field: Where is this water source located?\n(Où est située cette source deau?).";
             secWaterSource.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtFetchWaterM.getText().toString().length()==0 & secFetchWaterM.isShown() & !rdoFetchWaterMDk1.isChecked() & !rdoFetchWaterMDk2.isChecked())
           {
             ValidationMsg += "\nH1.2. Required field: How many minutes does it take to fetch water and back? \n(Combien de MINUTES faut-il pour aller là-bas, trouver de leau potable, et revenir ? ).";
             secFetchWaterM.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secFetchWaterM.isShown() & (Integer.valueOf(txtFetchWaterM.getText().toString().length()==0 ? "01" : txtFetchWaterM.getText().toString()) < 01 || Integer.valueOf(txtFetchWaterM.getText().toString().length()==0 ? "999" : txtFetchWaterM.getText().toString()) > 999))
           {
             ValidationMsg += "\nH1.2. Value should be between 01 and 999(How many minutes does it take to fetch water and back? \n(Combien de MINUTES faut-il pour aller là-bas, trouver de leau potable, et revenir ? )).";
             secFetchWaterM.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoFetchWaterMDk1.isChecked() & !rdoFetchWaterMDk2.isChecked() & secFetchWaterMDk.isShown() & txtFetchWaterM.getText().toString().length()==0)
           {
             ValidationMsg += "\nRequired field: Fetch water and back minutes Don't know/Refused to respond.";
             secFetchWaterMDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGetWater1.isChecked() & !rdoGetWater2.isChecked() & !rdoGetWater3.isChecked() & secGetWater.isShown())
           {
             ValidationMsg += "\nH1.3. Required field: Usually who goes to this source to get water for your household.\n(Dhabitude qui va à cette source chercher de leau pour votre ménage ?).";
             secGetWater.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtGetWaterOth.getText().toString().length()==0 & secGetWaterOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other (Specify) (Autre).";
             secGetWaterOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnMemberID.getSelectedItemPosition()==0  & secMemberID.isShown())
           {
             ValidationMsg += "\nH1.4. Required field: Note the Member ID.";
             secMemberID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtBringWater.getText().toString().length()==0 & secBringWater.isShown() & !rdoBringWaterDk1.isChecked() & !rdoBringWaterDk2.isChecked())
           {
             ValidationMsg += "\nH1.5. Required field: How many times does a member of your family each day bring water into your household?\n(Combien de tours vous/un membre de la famille fait pour amener de leau potable dans votre ménage chaque jour?).";
             secBringWater.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secBringWater.isShown() & (Integer.valueOf(txtBringWater.getText().toString().length()==0 ? "01" : txtBringWater.getText().toString()) < 01 || Integer.valueOf(txtBringWater.getText().toString().length()==0 ? "99" : txtBringWater.getText().toString()) > 99))
           {
             ValidationMsg += "\nH1.5. Value should be between 01 and 99(How many times does a member of your family each day bring water into your household?\n(Combien de tours vous/un membre de la famille fait pour amener de leau potable dans votre ménage chaque jour?)).";
             secBringWater.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoBringWaterDk1.isChecked() & !rdoBringWaterDk2.isChecked() & secBringWaterDk.isShown() & txtBringWater.getText().toString().length()==0)
           {
             ValidationMsg += "\nRequired field: Times Don't know/Refused to respond.";
             secBringWaterDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSomeone1.isChecked() & !rdoSomeone2.isChecked() & secSomeone.isShown())
           {
             ValidationMsg += "\nH1.6. Required field: Is there someone else to get water if the person usually in charge is not available?\n(Quelquun dautre va-t-il chercher de leau lorsque la personne principale (nommée plus haut) nest pas disponible ?).";
             secSomeone.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSecondPers1.isChecked() & !rdoSecondPers2.isChecked() & !rdoSecondPers3.isChecked() & secSecondPers.isShown())
           {
             ValidationMsg += "\nH1.7 Required field: Who is the secondary person who gets water for your household?\n(Qui est la personne secondaire qui va chercher de leau ?).";
             secSecondPers.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSecondPersOth.getText().toString().length()==0 & secSecondPersOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other (Specify) (Autre).";
             secSecondPersOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnMemberID2nd.getSelectedItemPosition()==0  & secMemberID2nd.isShown())
           {
             ValidationMsg += "\nH1.8 Required field: Note the Member ID of secondary person.";
             secMemberID2nd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoEnoughWater1.isChecked() & !rdoEnoughWater2.isChecked() & !rdoEnoughWater3.isChecked() & secEnoughWater.isShown())
           {
             ValidationMsg += "\nH1.9 Required field: In the past month, was there a time when your household did not have enough water as needed?\n(Au cours du mois passé, y a-t-il eu un moment où votre ménage navait pas suffisamment deau quand vous en aviez besoin ?).";
             secEnoughWater.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnMainWater.getSelectedItemPosition()==0  & secMainWater.isShown())
           {
             ValidationMsg += "\nH1.10 Required field: What is the main source that your household uses for other needs; hand washing, bathing, laundry. \n(Quelle est la principale source deau que votre ménage utilise pour dautres besoins, le lavage des mains, le bain, la lessive ? ).";
             secMainWater.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMainWaterOth.getText().toString().length()==0 & secMainWaterOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other (Specify) (Autre).";
             secMainWaterOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSmallTank1.isChecked() & !rdoSmallTank2.isChecked() & !rdoSmallTank3.isChecked() & secSmallTank.isShown())
           {
             ValidationMsg += "\nRequired field: a) Small tank (GRAND RÉSERVOIR).";
             secSmallTank.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoMediunTank1.isChecked() & !rdoMediunTank2.isChecked() & !rdoMediunTank3.isChecked() & secMediunTank.isShown())
           {
             ValidationMsg += "\nRequired field: b) Mediun tank (GRAND RÉSERVOIR).";
             secMediunTank.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoLargeTank1.isChecked() & !rdoLargeTank2.isChecked() & !rdoLargeTank3.isChecked() & secLargeTank.isShown())
           {
             ValidationMsg += "\nRequired field: c) Large tank (GRAND RÉSERVOIR).";
             secLargeTank.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoStoreDrink1.isChecked() & !rdoStoreDrink2.isChecked() & secStoreDrink.isShown())
           {
             ValidationMsg += "\nH1.12 Required field: Does your household store drinking water in small containers?\n(Votre ménage conserve-t-il de leau potable dans de petits récipients?).";
             secStoreDrink.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoRecoverWater1.isChecked() & !rdoRecoverWater2.isChecked() & !rdoRecoverWater3.isChecked() & !rdoRecoverWater4.isChecked() & secRecoverWater.isShown())
           {
             ValidationMsg += "\nH1.14 Required field: How will you recover water stored for use?\n(Comment récupérez-vous leau conservée pour lutiliser ?).";
             secRecoverWater.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRecoverWaterOth.getText().toString().length()==0 & secRecoverWaterOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other (Specify).";
             secRecoverWaterOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoLessDanger1.isChecked() & !rdoLessDanger2.isChecked() & !rdoLessDanger3.isChecked() & secLessDanger.isShown())
           {
             ValidationMsg += "\nH1.15 Required field: Do you do anything or put something in the water to make it less dangerous to drink?\n(Faites-vous quelque chose à leau pour la rendre moins dangereuse à boire?).";
             secLessDanger.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnMakeSafer.getSelectedItemPosition()==0  & secMakeSafer.isShown())
           {
             ValidationMsg += "\nH1.16 Required field: What do you do with your water to make it safer to drink?\n(Que faites-vous dhabitude à leau pour la rendre plus sûre à la boisson ?).";
             secMakeSafer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMakeSaferOth.getText().toString().length()==0 & secMakeSaferOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other (Specify).";
             secMakeSaferOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnToilet.getSelectedItemPosition()==0  & secToilet.isShown())
           {
             ValidationMsg += "\nH2 Required field: What kind of main toilet facility do members of your household usually use ?\n(If ‘Flush’, Or ‘Pour Flush’, Probe: Where Does It Flush To ?)\n(Circle One Response, Or Write Response For 97).";
             secToilet.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtToiletOth.getText().toString().length()==0 & secToiletOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Specify.";
             secToiletOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoToiletShrd1.isChecked() & !rdoToiletShrd2.isChecked() & !rdoToiletShrd3.isChecked() & secToiletShrd.isShown())
           {
             ValidationMsg += "\nH2.1 Required field: Do you share this toilet facility with other households?.";
             secToiletShrd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtToiletUseNo.getText().toString().length()==0 & secToiletUseNo.isShown())
           {
             ValidationMsg += "\nH2.2 Required field: Including your own household, how many households use this toilet facility?      No. Of Households (If Less Than 10).";
             secToiletUseNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secToiletUseNo.isShown() & (Integer.valueOf(txtToiletUseNo.getText().toString().length()==0 ? "1" : txtToiletUseNo.getText().toString()) < 1 || Integer.valueOf(txtToiletUseNo.getText().toString().length()==0 ? "10" : txtToiletUseNo.getText().toString()) > 10))
           {
             ValidationMsg += "\nH2.2 Value should be between 1 and 10(Including your own household, how many households use this toilet facility?      No. Of Households (If Less Than 10)).";
             secToiletUseNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoToiletUseNoDk1.isChecked() & !rdoToiletUseNoDk2.isChecked() & !rdoToiletUseNoDk3.isChecked() & secToiletUseNoDk.isShown())
           {
             ValidationMsg += "\nRequired field: .";
             secToiletUseNoDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoToiletLoc1.isChecked() & !rdoToiletLoc2.isChecked() & !rdoToiletLoc3.isChecked() & secToiletLoc.isShown())
           {
             ValidationMsg += "\nH2.3 Required field: Where is this toilet facility located ?.";
             secToiletLoc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoContentEmp1.isChecked() & !rdoContentEmp2.isChecked() & !rdoContentEmp3.isChecked() & !rdoContentEmp4.isChecked() & !rdoContentEmp5.isChecked() & !rdoContentEmp6.isChecked() & !rdoContentEmp7.isChecked() & secContentEmp.isShown())
           {
             ValidationMsg += "\nH2.4 Required field: Where was the content emptied last time?\n(La dernière fois quil a été vidé, où le contenu a-t-il été vidé ? ).";
             secContentEmp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtContentEmpOth.getText().toString().length()==0 & secContentEmpOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other, specify.";
             secContentEmpOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnBowelMov.getSelectedItemPosition()==0  & secBowelMov.isShown())
           {
             ValidationMsg += "\nH2.5 Required field: What did you do the last time your young child had a bowel movement?\n(La dernière fois que votre plus jeune enfant a émis des selles, quest ce qui a été fait pour sen débarrasser ? ).";
             secBowelMov.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtBowelMovOth.getText().toString().length()==0 & secBowelMovOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other (Specify).";
             secBowelMovOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoLiquidWaste1.isChecked() & !rdoLiquidWaste2.isChecked() & !rdoLiquidWaste3.isChecked() & !rdoLiquidWaste4.isChecked() & !rdoLiquidWaste5.isChecked() & !rdoLiquidWaste6.isChecked() & secLiquidWaste.isShown())
           {
             ValidationMsg += "\nH2.6 Required field: What is the main method of liquid waste disposal?\n(Quel est le principal mode dévacuation des déchets liquides (eaux usées) ?).";
             secLiquidWaste.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtLiquidWasteOth.getText().toString().length()==0 & secLiquidWasteOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other (Specify).";
             secLiquidWasteOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnSolidWasteMethod.getSelectedItemPosition()==0  & secSolidWasteMethod.isShown())
           {
             ValidationMsg += "\nH2.7 Required field: What is the main method of disposal of solid waste(garbage)?\n(Quel est le principal mode dévacuation des déchets solides (ordures) ?).";
             secSolidWasteMethod.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSolidWasteMethodOth.getText().toString().length()==0 & secSolidWasteMethodOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Specify.";
             secSolidWasteMethodOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoHandWash1.isChecked() & !rdoHandWash2.isChecked() & !rdoHandWash3.isChecked() & secHandWash.isShown())
           {
             ValidationMsg += "\nH2.8 Required field: Do you have a place for hand washing in this household ?\n(Avez-vous un endroit dédié au lavage des mains dans le ménage ?).";
             secHandWash.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoShowWash1.isChecked() & !rdoShowWash2.isChecked() & !rdoShowWash3.isChecked() & !rdoShowWash4.isChecked() & !rdoShowWash5.isChecked() & !rdoShowWash6.isChecked() & secShowWash.isShown())
           {
             ValidationMsg += "\nH2.9 Required field: Please can you show me where the members of your household wash their hand?\n(Pouvez-vous me montrer sil vous plait là où les membres de votre ménage se lavent les mains le plus souvent ?).";
             secShowWash.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoAvailableWat1.isChecked() & !rdoAvailableWat2.isChecked() & secAvailableWat.isShown())
           {
             ValidationMsg += "\nH2.10 Required field: Do you have available water for hand washing? \n(De leau disponible pour le lavage des mains).";
             secAvailableWat.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoAvailableSoap1.isChecked() & !rdoAvailableSoap2.isChecked() & !rdoAvailableSoap3.isChecked() & secAvailableSoap.isShown())
           {
             ValidationMsg += "\nH2.11 Required field: Do you have available soap for the hand washing?\n(Du savon disponible pour le lavage des mains).";
             secAvailableSoap.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnCookDvc.getSelectedItemPosition()==0  & secCookDvc.isShown())
           {
             ValidationMsg += "\nH3. Required field: What device does this household use most of the time for cooking food, making tea/coffee, or boiling drinking water ? \n(Circle One Response, Or Write Response For 97).";
             secCookDvc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCookDvcOth.getText().toString().length()==0 & secCookDvcOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Specify.";
             secCookDvcOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnCookFuel.getSelectedItemPosition()==0  & secCookFuel.isShown())
           {
             ValidationMsg += "\nH4. Required field: When cooking, what type of fuel or energy source does this household use most of the time for cooking food, making tea/coffee, or boiling drinking water?\n(Circle One Response, Or Write Response For 97).";
             secCookFuel.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCookFuelOth.getText().toString().length()==0 & secCookFuelOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other fuel Specify.";
             secCookFuelOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnCookPlc.getSelectedItemPosition()==0  & secCookPlc.isShown())
           {
             ValidationMsg += "\nH5. Required field: Where is the cooking done most of the time?\n (Circle One Response, Or Write Response For 7).";
             secCookPlc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCookPlcOth.getText().toString().length()==0 & secCookPlcOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Specify.";
             secCookPlcOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnFloor.getSelectedItemPosition()==0  & secFloor.isShown())
           {
             ValidationMsg += "\nH6. Required field: What is the main material of the floor of your homestead/dwelling/residence?\n(Circle One Response, Or Write Response For 7) \n(“Main Material” Is The Material That Covers The Largest Amount Of Floor Space).";
             secFloor.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtFloorOth.getText().toString().length()==0 & secFloorOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Floor Specify.";
             secFloorOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnGroundMat.getSelectedItemPosition()==0  & secGroundMat.isShown())
           {
             ValidationMsg += "\nH6.1 Required field: What  are the main ground covering materials for the concessions and surrounding areas?\n(Quels sont les principaux matériaux de couverture du sol de la concession et des environs ?).";
             secGroundMat.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtGroundMatOth.getText().toString().length()==0 & secGroundMatOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other (specify).";
             secGroundMatOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnRoof.getSelectedItemPosition()==0  & secRoof.isShown())
           {
             ValidationMsg += "\nH7. Required field: What is the main material of the roof of your homestead/dwelling/residence?\n(CIRCLE ONE RESPONSE, OR WRITE RESPONSE FOR 97)\n(“MAIN MATERIAL” IS THE MATERIAL THAT COVERS THE LARGEST AMOUNT OF ROOF SPACE).";
             secRoof.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRoofOth.getText().toString().length()==0 & secRoofOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Roof Specify.";
             secRoofOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSmokeInside1.isChecked() & !rdoSmokeInside2.isChecked() & !rdoSmokeInside3.isChecked() & secSmokeInside.isShown())
           {
             ValidationMsg += "\nH7.1 Required field: Does anyone smoke inside the house?\n(Quelquun fume-t-il à lintérieur de la maison ?).";
             secSmokeInside.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoFreqSmoke1.isChecked() & !rdoFreqSmoke2.isChecked() & !rdoFreqSmoke3.isChecked() & !rdoFreqSmoke4.isChecked() & secFreqSmoke.isShown())
           {
             ValidationMsg += "\nH7.2 Required field: How often Is smoking Inside your home?\n(A quelle fréquence fume-t-on à lintérieur de votre maison ?).";
             secFreqSmoke.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnWall.getSelectedItemPosition()==0  & secWall.isShown())
           {
             ValidationMsg += "\nH8. Required field: What is the main material of the exterior walls of the house that is your main homestead/dwelling/residence?\n (CIRCLE ONE RESPONSE, OR WRITE RESPONSE FOR 97)\n(“MAIN MATERIAL” IS THE MATERIAL THAT COVERS THE LARGEST AMOUNT OF EXTERIOR WALL SPACE).";
             secWall.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtWallOth.getText().toString().length()==0 & secWallOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other Specify.";
             secWallOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRoomSleep.getText().toString().length()==0 & secRoomSleep.isShown() & !rdoRoomSleepDk1.isChecked() & !rdoRoomSleepDk2.isChecked())
           {
             ValidationMsg += "\nH9. Required field: How many rooms in your homestead/dwelling/residence are used for sleeping ?\n(Write The Number On The Blank) (A Room Divided By A Curtain Counts As One Room).";
             secRoomSleep.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secRoomSleep.isShown() & (Integer.valueOf(txtRoomSleep.getText().toString().length()==0 ? "01" : txtRoomSleep.getText().toString()) < 01 || Integer.valueOf(txtRoomSleep.getText().toString().length()==0 ? "20" : txtRoomSleep.getText().toString()) > 20))
           {
             ValidationMsg += "\nH9. Value should be between 01 and 20(How many rooms in your homestead/dwelling/residence are used for sleeping ?\n(Write The Number On The Blank) (A Room Divided By A Curtain Counts As One Room)).";
             secRoomSleep.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoRoomSleepDk1.isChecked() & !rdoRoomSleepDk2.isChecked() & secRoomSleepDk.isShown() & txtRoomSleep.getText().toString().length()==0 )
           {
             ValidationMsg += "\nRequired field: How many rooms in your homestead/dwelling/residence are used for sleeping?-Don’t know/Refused to respond.";
             secRoomSleepDk.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(spnElecNight.getSelectedItemPosition()==0  & secElecNight.isShown())
           {
             ValidationMsg += "\nH9.1 Required field: What is the main source of electricity (source of light at night) in the home?\n(Quel est le principal source delectricité du logement ?).";
             secElecNight.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtElecNightOth.getText().toString().length()==0 & secElecNightOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other (specify).";
             secElecNightOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
         if(txtArea.getText().toString().length()==0 & secArea.isShown())
           {
             ValidationMsg += "\nH11.1 Required field: How much do you estimate their surface area in hectares?\n(A combien estimez-vous leur superficie en hectares ?).";
             secArea.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secArea.isShown() & (Integer.valueOf(txtArea.getText().toString().length()==0 ? "01" : txtArea.getText().toString()) < 01 || Integer.valueOf(txtArea.getText().toString().length()==0 ? "99" : txtArea.getText().toString()) > 99))
           {
             ValidationMsg += "\nH11.1 Value should be between 01 and 99(How much do you estimate their surface area in hectares?\n(A combien estimez-vous leur superficie en hectares ?)).";
             secArea.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoIncomeMo1.isChecked() & !rdoIncomeMo2.isChecked() & !rdoIncomeMo3.isChecked() & !rdoIncomeMo4.isChecked() & !rdoIncomeMo5.isChecked() & !rdoIncomeMo6.isChecked() & !rdoIncomeMo7.isChecked() & !rdoIncomeMo8.isChecked() & secIncomeMo.isShown())
           {
             ValidationMsg += "\nH11.2 Required field: What is the range of monthly income( in FCFA) of this household?\n(Dans quelle fourchette se trouve le revenu mensuel (en FCFA) de ce ménage ?).";
             secIncomeMo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoExpenses1.isChecked() & !rdoExpenses2.isChecked() & !rdoExpenses3.isChecked() & !rdoExpenses4.isChecked() & !rdoExpenses5.isChecked() & !rdoExpenses6.isChecked() & !rdoExpenses7.isChecked() & !rdoExpenses8.isChecked() & secExpenses.isShown())
           {
             ValidationMsg += "\nH11.3 Required field: What Is the range of monthly expenses (In FCFA) of this household?\n(Dans quelle fourchette se trouve les dépenses mensuelles (en FCFA) de ce ménage ?).";
             secExpenses.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoBankAcc1.isChecked() & !rdoBankAcc2.isChecked() & secBankAcc.isShown())
           {
             ValidationMsg += "\nH11.4 Required field: Does any member of this household have a bank account?\n(Est-ce quun membre de ce ménage a un compte bancaire ?).";
             secBankAcc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSprayInt1.isChecked() & !rdoSprayInt2.isChecked() & !rdoSprayInt3.isChecked() & secSprayInt.isShown())
           {
             ValidationMsg += "\nH11.5 Required field: At any time during the past 12 months, has anyone entered your home to spray the interior of your home for mosquitoes?\n (A un moment quelconque au cours des 12 derniers mois, quelquun est-il entré dans votre maison pour pulvériser les murs intérieurs contre les moustiques ?).";
             secSprayInt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoMosqNet1.isChecked() & !rdoMosqNet2.isChecked() & !rdoMosqNet3.isChecked() & secMosqNet.isShown())
           {
             ValidationMsg += "\nH11.6 Required field: Do you have a mosquito net in your household?\n (Avez-vous au moins une moustiquaire dans votre ménage ?).";
             secMosqNet.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtNetOwned.getText().toString().length()==0 & secNetOwned.isShown())
           {
             ValidationMsg += "\nH11.7 Required field: Total number of mosquito nets owned by the household(hang or not)\n(Nombre total des moustiquaires que possède le ménage (accrochée ou pas):).";
             secNetOwned.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secNetOwned.isShown() & (Integer.valueOf(txtNetOwned.getText().toString().length()==0 ? "01" : txtNetOwned.getText().toString()) < 01 || Integer.valueOf(txtNetOwned.getText().toString().length()==0 ? "20" : txtNetOwned.getText().toString()) > 20))
           {
             ValidationMsg += "\nH11.7 Value should be between 01 and 20(Total number of mosquito nets owned by the household(hang or not)\n(Nombre total des moustiquaires que possède le ménage (accrochée ou pas):)).";
             secNetOwned.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoMedHome1.isChecked() & !rdoMedHome2.isChecked() & !rdoMedHome3.isChecked() & secMedHome.isShown())
           {
             ValidationMsg += "\nH11.8 Required field: Does the household have medicines at home?\n (Le ménage a-t-il des médicaments à la maison?).";
             secMedHome.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtAntimalarSpecifyOth.getText().toString().length()==0 & secAntimalarSpecifyOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other (Specify).";
             secAntimalarSpecifyOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtGetMedSpecifyOth.getText().toString().length()==0 & secGetMedSpecifyOth.isShown())
           {
             ValidationMsg += "\nRequired field: Other (Specify).";
             secGetMedSpecifyOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoAComment1.isChecked() & !rdoAComment2.isChecked() & secAComment.isShown())
           {
             ValidationMsg += "\nH11.12 Required field: A comment to make?\n(Un commentaire à faire ?).";
             secAComment.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtComment.getText().toString().length()==0 & secComment.isShown())
           {
             ValidationMsg += "\nH11.13 Required field: Comment (Commentaire).";
             secComment.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoElectricity1.isChecked() & !rdoElectricity2.isChecked() & secElectricity.isShown())
           {
             ValidationMsg += "\n1. Required field: Electricity.";
             secElectricity.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSolarPlate1.isChecked() & !rdoSolarPlate2.isChecked() & secSolarPlate.isShown())
           {
             ValidationMsg += "\n1.1 Required field: Solar plate.";
             secSolarPlate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSolarPlateN.getText().toString().length()==0 & secSolarPlateN.isShown())
           {
             ValidationMsg += "\nRequired field: Number:.";
             secSolarPlateN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secSolarPlateN.isShown() & (Integer.valueOf(txtSolarPlateN.getText().toString().length()==0 ? "01" : txtSolarPlateN.getText().toString()) < 01 || Integer.valueOf(txtSolarPlateN.getText().toString().length()==0 ? "90" : txtSolarPlateN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number:).";
             secSolarPlateN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoHeater1.isChecked() & !rdoHeater2.isChecked() & secHeater.isShown())
           {
             ValidationMsg += "\n2. Required field: Heater.";
             secHeater.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHeaterN.getText().toString().length()==0 & secHeaterN.isShown())
           {
             ValidationMsg += "\nRequired field: Number:.";
             secHeaterN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secHeaterN.isShown() & (Integer.valueOf(txtHeaterN.getText().toString().length()==0 ? "01" : txtHeaterN.getText().toString()) < 01 || Integer.valueOf(txtHeaterN.getText().toString().length()==0 ? "90" : txtHeaterN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number:).";
             secHeaterN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoAC1.isChecked() & !rdoAC2.isChecked() & secAC.isShown())
           {
             ValidationMsg += "\n3. Required field: Air conditioner.";
             secAC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtACN.getText().toString().length()==0 & secACN.isShown())
           {
             ValidationMsg += "\nRequired field: Number:.";
             secACN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secACN.isShown() & (Integer.valueOf(txtACN.getText().toString().length()==0 ? "01" : txtACN.getText().toString()) < 01 || Integer.valueOf(txtACN.getText().toString().length()==0 ? "99" : txtACN.getText().toString()) > 99))
           {
             ValidationMsg += "\nValue should be between 01 and 99(Number:).";
             secACN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoElecFan1.isChecked() & !rdoElecFan2.isChecked() & secElecFan.isShown())
           {
             ValidationMsg += "\n4. Required field: Electric fan.";
             secElecFan.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtElecFanN.getText().toString().length()==0 & secElecFanN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secElecFanN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secElecFanN.isShown() & (Integer.valueOf(txtElecFanN.getText().toString().length()==0 ? "01" : txtElecFanN.getText().toString()) < 01 || Integer.valueOf(txtElecFanN.getText().toString().length()==0 ? "90" : txtElecFanN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secElecFanN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoLantern1.isChecked() & !rdoLantern2.isChecked() & secLantern.isShown())
           {
             ValidationMsg += "\n5. Required field: Kerosene lantern.";
             secLantern.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtLanternN.getText().toString().length()==0 & secLanternN.isShown())
           {
             ValidationMsg += "\nRequired field: Number:.";
             secLanternN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secLanternN.isShown() & (Integer.valueOf(txtLanternN.getText().toString().length()==0 ? "01" : txtLanternN.getText().toString()) < 01 || Integer.valueOf(txtLanternN.getText().toString().length()==0 ? "90" : txtLanternN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number:).";
             secLanternN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoLamp1.isChecked() & !rdoLamp2.isChecked() & secLamp.isShown())
           {
             ValidationMsg += "\n6. Required field: Pressure lamp.";
             secLamp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtLampN.getText().toString().length()==0 & secLampN.isShown())
           {
             ValidationMsg += "\nRequired field: Number:.";
             secLampN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secLampN.isShown() & (Integer.valueOf(txtLampN.getText().toString().length()==0 ? "01" : txtLampN.getText().toString()) < 01 || Integer.valueOf(txtLampN.getText().toString().length()==0 ? "90" : txtLampN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number:).";
             secLampN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGasLamp1.isChecked() & !rdoGasLamp2.isChecked() & secGasLamp.isShown())
           {
             ValidationMsg += "\n6.1 Required field: Gas lamp.";
             secGasLamp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtGasLampN.getText().toString().length()==0 & secGasLampN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secGasLampN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secGasLampN.isShown() & (Integer.valueOf(txtGasLampN.getText().toString().length()==0 ? "01" : txtGasLampN.getText().toString()) < 01 || Integer.valueOf(txtGasLampN.getText().toString().length()==0 ? "90" : txtGasLampN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secGasLampN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPetroleum1.isChecked() & !rdoPetroleum2.isChecked() & secPetroleum.isShown())
           {
             ValidationMsg += "\n6.2 Required field: Petroleum lamp.";
             secPetroleum.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtPetroleumN.getText().toString().length()==0 & secPetroleumN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secPetroleumN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secPetroleumN.isShown() & (Integer.valueOf(txtPetroleumN.getText().toString().length()==0 ? "01" : txtPetroleumN.getText().toString()) < 01 || Integer.valueOf(txtPetroleumN.getText().toString().length()==0 ? "90" : txtPetroleumN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secPetroleumN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoMatt1.isChecked() & !rdoMatt2.isChecked() & secMatt.isShown())
           {
             ValidationMsg += "\n7. Required field: Mattress.";
             secMatt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMattN.getText().toString().length()==0 & secMattN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secMattN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secMattN.isShown() & (Integer.valueOf(txtMattN.getText().toString().length()==0 ? "01" : txtMattN.getText().toString()) < 01 || Integer.valueOf(txtMattN.getText().toString().length()==0 ? "90" : txtMattN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secMattN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoMats1.isChecked() & !rdoMats2.isChecked() & secMats.isShown())
           {
             ValidationMsg += "\n7.1 Required field: Mats.";
             secMats.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMatsN.getText().toString().length()==0 & secMatsN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secMatsN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secMatsN.isShown() & (Integer.valueOf(txtMatsN.getText().toString().length()==0 ? "01" : txtMatsN.getText().toString()) < 01 || Integer.valueOf(txtMatsN.getText().toString().length()==0 ? "90" : txtMatsN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secMatsN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCarpets1.isChecked() & !rdoCarpets2.isChecked() & secCarpets.isShown())
           {
             ValidationMsg += "\n7.2 Required field: Carpets.";
             secCarpets.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCarpetsN.getText().toString().length()==0 & secCarpetsN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secCarpetsN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secCarpetsN.isShown() & (Integer.valueOf(txtCarpetsN.getText().toString().length()==0 ? "01" : txtCarpetsN.getText().toString()) < 01 || Integer.valueOf(txtCarpetsN.getText().toString().length()==0 ? "90" : txtCarpetsN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secCarpetsN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoBed1.isChecked() & !rdoBed2.isChecked() & secBed.isShown())
           {
             ValidationMsg += "\n8. Required field: Bed.";
             secBed.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtBedN.getText().toString().length()==0 & secBedN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secBedN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secBedN.isShown() & (Integer.valueOf(txtBedN.getText().toString().length()==0 ? "01" : txtBedN.getText().toString()) < 01 || Integer.valueOf(txtBedN.getText().toString().length()==0 ? "90" : txtBedN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secBedN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoChair1.isChecked() & !rdoChair2.isChecked() & secChair.isShown())
           {
             ValidationMsg += "\n9. Required field: Chair.";
             secChair.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtChairN.getText().toString().length()==0 & secChairN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secChairN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secChairN.isShown() & (Integer.valueOf(txtChairN.getText().toString().length()==0 ? "01" : txtChairN.getText().toString()) < 01 || Integer.valueOf(txtChairN.getText().toString().length()==0 ? "90" : txtChairN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secChairN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSofa1.isChecked() & !rdoSofa2.isChecked() & secSofa.isShown())
           {
             ValidationMsg += "\n10. Required field: Sofa.";
             secSofa.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSofaN.getText().toString().length()==0 & secSofaN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secSofaN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secSofaN.isShown() & (Integer.valueOf(txtSofaN.getText().toString().length()==0 ? "01" : txtSofaN.getText().toString()) < 01 || Integer.valueOf(txtSofaN.getText().toString().length()==0 ? "90" : txtSofaN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secSofaN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoTables1.isChecked() & !rdoTables2.isChecked() & secTables.isShown())
           {
             ValidationMsg += "\n11. Required field: Tables.";
             secTables.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtTablesN.getText().toString().length()==0 & secTablesN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secTablesN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secTablesN.isShown() & (Integer.valueOf(txtTablesN.getText().toString().length()==0 ? "01" : txtTablesN.getText().toString()) < 01 || Integer.valueOf(txtTablesN.getText().toString().length()==0 ? "90" : txtTablesN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secTablesN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoWatch1.isChecked() & !rdoWatch2.isChecked() & secWatch.isShown())
           {
             ValidationMsg += "\n12. Required field: Watch or clock.";
             secWatch.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtWatchN.getText().toString().length()==0 & secWatchN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secWatchN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secWatchN.isShown() & (Integer.valueOf(txtWatchN.getText().toString().length()==0 ? "01" : txtWatchN.getText().toString()) < 01 || Integer.valueOf(txtWatchN.getText().toString().length()==0 ? "90" : txtWatchN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secWatchN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoWMachine1.isChecked() & !rdoWMachine2.isChecked() & secWMachine.isShown())
           {
             ValidationMsg += "\n13. Required field: Washing Machine.";
             secWMachine.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtWMachineN.getText().toString().length()==0 & secWMachineN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secWMachineN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secWMachineN.isShown() & (Integer.valueOf(txtWMachineN.getText().toString().length()==0 ? "01" : txtWMachineN.getText().toString()) < 01 || Integer.valueOf(txtWMachineN.getText().toString().length()==0 ? "90" : txtWMachineN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secWMachineN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoIron1.isChecked() & !rdoIron2.isChecked() & secIron.isShown())
           {
             ValidationMsg += "\n14. Required field: Electric iron.";
             secIron.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtIronN.getText().toString().length()==0 & secIronN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secIronN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secIronN.isShown() & (Integer.valueOf(txtIronN.getText().toString().length()==0 ? "01" : txtIronN.getText().toString()) < 01 || Integer.valueOf(txtIronN.getText().toString().length()==0 ? "90" : txtIronN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secIronN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoBooth1.isChecked() & !rdoBooth2.isChecked() & secBooth.isShown())
           {
             ValidationMsg += "\n15. Required field: Business booth.";
             secBooth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtBoothN.getText().toString().length()==0 & secBoothN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secBoothN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secBoothN.isShown() & (Integer.valueOf(txtBoothN.getText().toString().length()==0 ? "01" : txtBoothN.getText().toString()) < 01 || Integer.valueOf(txtBoothN.getText().toString().length()==0 ? "90" : txtBoothN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secBoothN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSMachine1.isChecked() & !rdoSMachine2.isChecked() & secSMachine.isShown())
           {
             ValidationMsg += "\n16. Required field: Sewing machine.";
             secSMachine.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSMachineN.getText().toString().length()==0 & secSMachineN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secSMachineN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secSMachineN.isShown() & (Integer.valueOf(txtSMachineN.getText().toString().length()==0 ? "01" : txtSMachineN.getText().toString()) < 01 || Integer.valueOf(txtSMachineN.getText().toString().length()==0 ? "90" : txtSMachineN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secSMachineN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGenerator1.isChecked() & !rdoGenerator2.isChecked() & secGenerator.isShown())
           {
             ValidationMsg += "\n17. Required field: Power generator.";
             secGenerator.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtGeneratorN.getText().toString().length()==0 & secGeneratorN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secGeneratorN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secGeneratorN.isShown() & (Integer.valueOf(txtGeneratorN.getText().toString().length()==0 ? "01" : txtGeneratorN.getText().toString()) < 01 || Integer.valueOf(txtGeneratorN.getText().toString().length()==0 ? "90" : txtGeneratorN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secGeneratorN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoInternet1.isChecked() & !rdoInternet2.isChecked() & secInternet.isShown())
           {
             ValidationMsg += "\n18. Required field: Internet/Wi-Fi at home.";
             secInternet.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtInternetN.getText().toString().length()==0 & secInternetN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secInternetN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secInternetN.isShown() & (Integer.valueOf(txtInternetN.getText().toString().length()==0 ? "01" : txtInternetN.getText().toString()) < 01 || Integer.valueOf(txtInternetN.getText().toString().length()==0 ? "90" : txtInternetN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secInternetN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSatellite1.isChecked() & !rdoSatellite2.isChecked() & secSatellite.isShown())
           {
             ValidationMsg += "\n19. Required field: Satellite decoder.";
             secSatellite.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSatelliteN.getText().toString().length()==0 & secSatelliteN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secSatelliteN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secSatelliteN.isShown() & (Integer.valueOf(txtSatelliteN.getText().toString().length()==0 ? "01" : txtSatelliteN.getText().toString()) < 01 || Integer.valueOf(txtSatelliteN.getText().toString().length()==0 ? "90" : txtSatelliteN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secSatelliteN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoLandline1.isChecked() & !rdoLandline2.isChecked() & secLandline.isShown())
           {
             ValidationMsg += "\n20. Required field: Landline telephone.";
             secLandline.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtLandlineN.getText().toString().length()==0 & secLandlineN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secLandlineN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secLandlineN.isShown() & (Integer.valueOf(txtLandlineN.getText().toString().length()==0 ? "01" : txtLandlineN.getText().toString()) < 01 || Integer.valueOf(txtLandlineN.getText().toString().length()==0 ? "90" : txtLandlineN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secLandlineN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCellphone1.isChecked() & !rdoCellphone2.isChecked() & secCellphone.isShown())
           {
             ValidationMsg += "\n21. Required field: Cellphone.";
             secCellphone.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCellphoneN.getText().toString().length()==0 & secCellphoneN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secCellphoneN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secCellphoneN.isShown() & (Integer.valueOf(txtCellphoneN.getText().toString().length()==0 ? "01" : txtCellphoneN.getText().toString()) < 01 || Integer.valueOf(txtCellphoneN.getText().toString().length()==0 ? "90" : txtCellphoneN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secCellphoneN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoTV1.isChecked() & !rdoTV2.isChecked() & secTV.isShown())
           {
             ValidationMsg += "\n22. Required field: TV.";
             secTV.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtTVN.getText().toString().length()==0 & secTVN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secTVN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secTVN.isShown() & (Integer.valueOf(txtTVN.getText().toString().length()==0 ? "01" : txtTVN.getText().toString()) < 01 || Integer.valueOf(txtTVN.getText().toString().length()==0 ? "90" : txtTVN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secTVN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoTV51.isChecked() & !rdoTV52.isChecked() & secTV5.isShown())
           {
             ValidationMsg += "\n22.1 Required field: TV5 Antenna.";
             secTV5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtTV5N.getText().toString().length()==0 & secTV5N.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secTV5N.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secTV5N.isShown() & (Integer.valueOf(txtTV5N.getText().toString().length()==0 ? "01" : txtTV5N.getText().toString()) < 01 || Integer.valueOf(txtTV5N.getText().toString().length()==0 ? "90" : txtTV5N.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secTV5N.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoChannel1.isChecked() & !rdoChannel2.isChecked() & secChannel.isShown())
           {
             ValidationMsg += "\n22.2 Required field: Channel subscription.";
             secChannel.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtChannelN.getText().toString().length()==0 & secChannelN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secChannelN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secChannelN.isShown() & (Integer.valueOf(txtChannelN.getText().toString().length()==0 ? "01" : txtChannelN.getText().toString()) < 01 || Integer.valueOf(txtChannelN.getText().toString().length()==0 ? "90" : txtChannelN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secChannelN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoRadio1.isChecked() & !rdoRadio2.isChecked() & secRadio.isShown())
           {
             ValidationMsg += "\n23. Required field: Radio.";
             secRadio.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRadioN.getText().toString().length()==0 & secRadioN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secRadioN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secRadioN.isShown() & (Integer.valueOf(txtRadioN.getText().toString().length()==0 ? "01" : txtRadioN.getText().toString()) < 01 || Integer.valueOf(txtRadioN.getText().toString().length()==0 ? "90" : txtRadioN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secRadioN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoDVD1.isChecked() & !rdoDVD2.isChecked() & secDVD.isShown())
           {
             ValidationMsg += "\n24. Required field: DVD/CD player.";
             secDVD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDVDN.getText().toString().length()==0 & secDVDN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secDVDN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secDVDN.isShown() & (Integer.valueOf(txtDVDN.getText().toString().length()==0 ? "01" : txtDVDN.getText().toString()) < 01 || Integer.valueOf(txtDVDN.getText().toString().length()==0 ? "90" : txtDVDN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secDVDN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoVideo1.isChecked() & !rdoVideo2.isChecked() & secVideo.isShown())
           {
             ValidationMsg += "\n25. Required field: Video player.";
             secVideo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtVideoN.getText().toString().length()==0 & secVideoN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secVideoN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secVideoN.isShown() & (Integer.valueOf(txtVideoN.getText().toString().length()==0 ? "01" : txtVideoN.getText().toString()) < 01 || Integer.valueOf(txtVideoN.getText().toString().length()==0 ? "90" : txtVideoN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secVideoN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoComputer1.isChecked() & !rdoComputer2.isChecked() & secComputer.isShown())
           {
             ValidationMsg += "\n26. Required field: Computer/Desktop.";
             secComputer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtComputerN.getText().toString().length()==0 & secComputerN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secComputerN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secComputerN.isShown() & (Integer.valueOf(txtComputerN.getText().toString().length()==0 ? "01" : txtComputerN.getText().toString()) < 01 || Integer.valueOf(txtComputerN.getText().toString().length()==0 ? "90" : txtComputerN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secComputerN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoLaptop1.isChecked() & !rdoLaptop2.isChecked() & secLaptop.isShown())
           {
             ValidationMsg += "\n26.1 Required field: Laptop.";
             secLaptop.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtLaptopN.getText().toString().length()==0 & secLaptopN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secLaptopN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secLaptopN.isShown() & (Integer.valueOf(txtLaptopN.getText().toString().length()==0 ? "01" : txtLaptopN.getText().toString()) < 01 || Integer.valueOf(txtLaptopN.getText().toString().length()==0 ? "90" : txtLaptopN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secLaptopN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCable1.isChecked() & !rdoCable2.isChecked() & secCable.isShown())
           {
             ValidationMsg += "\n27. Required field: Cable connection.";
             secCable.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCableN.getText().toString().length()==0 & secCableN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secCableN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secCableN.isShown() & (Integer.valueOf(txtCableN.getText().toString().length()==0 ? "01" : txtCableN.getText().toString()) < 01 || Integer.valueOf(txtCableN.getText().toString().length()==0 ? "90" : txtCableN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secCableN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoMicrowave1.isChecked() & !rdoMicrowave2.isChecked() & secMicrowave.isShown())
           {
             ValidationMsg += "\n28. Required field: Microwave oven.";
             secMicrowave.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMicrowaveN.getText().toString().length()==0 & secMicrowaveN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secMicrowaveN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secMicrowaveN.isShown() & (Integer.valueOf(txtMicrowaveN.getText().toString().length()==0 ? "01" : txtMicrowaveN.getText().toString()) < 01 || Integer.valueOf(txtMicrowaveN.getText().toString().length()==0 ? "90" : txtMicrowaveN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secMicrowaveN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGeyser1.isChecked() & !rdoGeyser2.isChecked() & secGeyser.isShown())
           {
             ValidationMsg += "\n29. Required field: Geyser for hot water.";
             secGeyser.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtGeyserN.getText().toString().length()==0 & secGeyserN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secGeyserN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secGeyserN.isShown() & (Integer.valueOf(txtGeyserN.getText().toString().length()==0 ? "01" : txtGeyserN.getText().toString()) < 01 || Integer.valueOf(txtGeyserN.getText().toString().length()==0 ? "90" : txtGeyserN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secGeyserN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGrill1.isChecked() & !rdoGrill2.isChecked() & secGrill.isShown())
           {
             ValidationMsg += "\n30. Required field: Electric grill/Mitad.";
             secGrill.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtGrillN.getText().toString().length()==0 & secGrillN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secGrillN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secGrillN.isShown() & (Integer.valueOf(txtGrillN.getText().toString().length()==0 ? "01" : txtGrillN.getText().toString()) < 01 || Integer.valueOf(txtGrillN.getText().toString().length()==0 ? "90" : txtGrillN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secGrillN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGrain1.isChecked() & !rdoGrain2.isChecked() & secGrain.isShown())
           {
             ValidationMsg += "\n30.1 Required field: Grain grinder.";
             secGrain.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtGrainN.getText().toString().length()==0 & secGrainN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secGrainN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secGrainN.isShown() & (Integer.valueOf(txtGrainN.getText().toString().length()==0 ? "01" : txtGrainN.getText().toString()) < 01 || Integer.valueOf(txtGrainN.getText().toString().length()==0 ? "90" : txtGrainN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secGrainN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoRefrigerator1.isChecked() & !rdoRefrigerator2.isChecked() & secRefrigerator.isShown())
           {
             ValidationMsg += "\n31. Required field: Refrigerator.";
             secRefrigerator.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRefrigeratorN.getText().toString().length()==0 & secRefrigeratorN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secRefrigeratorN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secRefrigeratorN.isShown() & (Integer.valueOf(txtRefrigeratorN.getText().toString().length()==0 ? "01" : txtRefrigeratorN.getText().toString()) < 01 || Integer.valueOf(txtRefrigeratorN.getText().toString().length()==0 ? "90" : txtRefrigeratorN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secRefrigeratorN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoDeepFreezer1.isChecked() & !rdoDeepFreezer2.isChecked() & secDeepFreezer.isShown())
           {
             ValidationMsg += "\n32. Required field: Freezer/deep freezer.";
             secDeepFreezer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDeepFreezerN.getText().toString().length()==0 & secDeepFreezerN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secDeepFreezerN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secDeepFreezerN.isShown() & (Integer.valueOf(txtDeepFreezerN.getText().toString().length()==0 ? "01" : txtDeepFreezerN.getText().toString()) < 01 || Integer.valueOf(txtDeepFreezerN.getText().toString().length()==0 ? "90" : txtDeepFreezerN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secDeepFreezerN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoStove1.isChecked() & !rdoStove2.isChecked() & secStove.isShown())
           {
             ValidationMsg += "\n33. Required field: Stove/Petroleum stove/Gas stove.";
             secStove.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtStoveN.getText().toString().length()==0 & secStoveN.isShown())
           {
             ValidationMsg += "\nRequired field: .";
             secStoveN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secStoveN.isShown() & (Integer.valueOf(txtStoveN.getText().toString().length()==0 ? "01" : txtStoveN.getText().toString()) < 01 || Integer.valueOf(txtStoveN.getText().toString().length()==0 ? "90" : txtStoveN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secStoveN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGasHob1.isChecked() & !rdoGasHob2.isChecked() & secGasHob.isShown())
           {
             ValidationMsg += "\n33.1 Required field: Gas hob.";
             secGasHob.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtGasHobN.getText().toString().length()==0 & secGasHobN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secGasHobN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secGasHobN.isShown() & (Integer.valueOf(txtGasHobN.getText().toString().length()==0 ? "01" : txtGasHobN.getText().toString()) < 01 || Integer.valueOf(txtGasHobN.getText().toString().length()==0 ? "90" : txtGasHobN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secGasHobN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoImpCooker1.isChecked() & !rdoImpCooker2.isChecked() & secImpCooker.isShown())
           {
             ValidationMsg += "\n33.2 Required field: Improved cooker.";
             secImpCooker.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtImpCookerN.getText().toString().length()==0 & secImpCookerN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secImpCookerN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secImpCookerN.isShown() & (Integer.valueOf(txtImpCookerN.getText().toString().length()==0 ? "01" : txtImpCookerN.getText().toString()) < 01 || Integer.valueOf(txtImpCookerN.getText().toString().length()==0 ? "90" : txtImpCookerN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secImpCookerN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoBike1.isChecked() & !rdoBike2.isChecked() & secBike.isShown())
           {
             ValidationMsg += "\n34. Required field: Bike/bicycle.";
             secBike.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtBikeN.getText().toString().length()==0 & secBikeN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secBikeN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secBikeN.isShown() & (Integer.valueOf(txtBikeN.getText().toString().length()==0 ? "01" : txtBikeN.getText().toString()) < 01 || Integer.valueOf(txtBikeN.getText().toString().length()==0 ? "90" : txtBikeN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secBikeN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoMotorcycle1.isChecked() & !rdoMotorcycle2.isChecked() & secMotorcycle.isShown())
           {
             ValidationMsg += "\n35. Required field: Motorcycle/moped/scooter.";
             secMotorcycle.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtMotorcycleN.getText().toString().length()==0 & secMotorcycleN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secMotorcycleN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secMotorcycleN.isShown() & (Integer.valueOf(txtMotorcycleN.getText().toString().length()==0 ? "01" : txtMotorcycleN.getText().toString()) < 01 || Integer.valueOf(txtMotorcycleN.getText().toString().length()==0 ? "90" : txtMotorcycleN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secMotorcycleN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCar1.isChecked() & !rdoCar2.isChecked() & secCar.isShown())
           {
             ValidationMsg += "\n36. Required field: Car.";
             secCar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCarN.getText().toString().length()==0 & secCarN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secCarN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secCarN.isShown() & (Integer.valueOf(txtCarN.getText().toString().length()==0 ? "01" : txtCarN.getText().toString()) < 01 || Integer.valueOf(txtCarN.getText().toString().length()==0 ? "90" : txtCarN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secCarN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoRickshaw1.isChecked() & !rdoRickshaw2.isChecked() & secRickshaw.isShown())
           {
             ValidationMsg += "\n37. Required field: Rickshaw/Van.";
             secRickshaw.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtRickshawN.getText().toString().length()==0 & secRickshawN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secRickshawN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secRickshawN.isShown() & (Integer.valueOf(txtRickshawN.getText().toString().length()==0 ? "01" : txtRickshawN.getText().toString()) < 01 || Integer.valueOf(txtRickshawN.getText().toString().length()==0 ? "90" : txtRickshawN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secRickshawN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCart1.isChecked() & !rdoCart2.isChecked() & secCart.isShown())
           {
             ValidationMsg += "\n38. Required field: Animal drawn cart.";
             secCart.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCartN.getText().toString().length()==0 & secCartN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secCartN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secCartN.isShown() & (Integer.valueOf(txtCartN.getText().toString().length()==0 ? "01" : txtCartN.getText().toString()) < 01 || Integer.valueOf(txtCartN.getText().toString().length()==0 ? "90" : txtCartN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secCartN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCanoe1.isChecked() & !rdoCanoe2.isChecked() & secCanoe.isShown())
           {
             ValidationMsg += "\n39. Required field: Canoe/fishing boat.";
             secCanoe.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCanoeN.getText().toString().length()==0 & secCanoeN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secCanoeN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secCanoeN.isShown() & (Integer.valueOf(txtCanoeN.getText().toString().length()==0 ? "01" : txtCanoeN.getText().toString()) < 01 || Integer.valueOf(txtCanoeN.getText().toString().length()==0 ? "90" : txtCanoeN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secCanoeN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoBus1.isChecked() & !rdoBus2.isChecked() & secBus.isShown())
           {
             ValidationMsg += "\n40. Required field: Bus/truck.";
             secBus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtBusN.getText().toString().length()==0 & secBusN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secBusN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secBusN.isShown() & (Integer.valueOf(txtBusN.getText().toString().length()==0 ? "01" : txtBusN.getText().toString()) < 01 || Integer.valueOf(txtBusN.getText().toString().length()==0 ? "90" : txtBusN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secBusN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoTractor1.isChecked() & !rdoTractor2.isChecked() & secTractor.isShown())
           {
             ValidationMsg += "\n41. Required field: Tractor.";
             secTractor.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtTractorN.getText().toString().length()==0 & secTractorN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secTractorN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secTractorN.isShown() & (Integer.valueOf(txtTractorN.getText().toString().length()==0 ? "01" : txtTractorN.getText().toString()) < 01 || Integer.valueOf(txtTractorN.getText().toString().length()==0 ? "90" : txtTractorN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secTractorN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPlow1.isChecked() & !rdoPlow2.isChecked() & secPlow.isShown())
           {
             ValidationMsg += "\n42. Required field: Plow/plough.";
             secPlow.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtPlowN.getText().toString().length()==0 & secPlowN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secPlowN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secPlowN.isShown() & (Integer.valueOf(txtPlowN.getText().toString().length()==0 ? "01" : txtPlowN.getText().toString()) < 01 || Integer.valueOf(txtPlowN.getText().toString().length()==0 ? "90" : txtPlowN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secPlowN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoDuck1.isChecked() & !rdoDuck2.isChecked() & secDuck.isShown())
           {
             ValidationMsg += "\n43. Required field: Duck.";
             secDuck.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDuckN.getText().toString().length()==0 & secDuckN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secDuckN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secDuckN.isShown() & (Integer.valueOf(txtDuckN.getText().toString().length()==0 ? "01" : txtDuckN.getText().toString()) < 01 || Integer.valueOf(txtDuckN.getText().toString().length()==0 ? "90" : txtDuckN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secDuckN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCow1.isChecked() & !rdoCow2.isChecked() & secCow.isShown())
           {
             ValidationMsg += "\n44. Required field: Cow.";
             secCow.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCowN.getText().toString().length()==0 & secCowN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secCowN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secCowN.isShown() & (Integer.valueOf(txtCowN.getText().toString().length()==0 ? "01" : txtCowN.getText().toString()) < 01 || Integer.valueOf(txtCowN.getText().toString().length()==0 ? "90" : txtCowN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secCowN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoSheep1.isChecked() & !rdoSheep2.isChecked() & secSheep.isShown())
           {
             ValidationMsg += "\n45. Required field: Sheep.";
             secSheep.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtSheepN.getText().toString().length()==0 & secSheepN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secSheepN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secSheepN.isShown() & (Integer.valueOf(txtSheepN.getText().toString().length()==0 ? "01" : txtSheepN.getText().toString()) < 01 || Integer.valueOf(txtSheepN.getText().toString().length()==0 ? "90" : txtSheepN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secSheepN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoGoat1.isChecked() & !rdoGoat2.isChecked() & secGoat.isShown())
           {
             ValidationMsg += "\n46. Required field: Goat.";
             secGoat.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtGoatN.getText().toString().length()==0 & secGoatN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secGoatN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secGoatN.isShown() & (Integer.valueOf(txtGoatN.getText().toString().length()==0 ? "01" : txtGoatN.getText().toString()) < 01 || Integer.valueOf(txtGoatN.getText().toString().length()==0 ? "90" : txtGoatN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secGoatN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoChicken1.isChecked() & !rdoChicken2.isChecked() & secChicken.isShown())
           {
             ValidationMsg += "\n47. Required field: Chicken.";
             secChicken.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtChickenN.getText().toString().length()==0 & secChickenN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secChickenN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secChickenN.isShown() & (Integer.valueOf(txtChickenN.getText().toString().length()==0 ? "01" : txtChickenN.getText().toString()) < 01 || Integer.valueOf(txtChickenN.getText().toString().length()==0 ? "90" : txtChickenN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secChickenN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoDonkey1.isChecked() & !rdoDonkey2.isChecked() & secDonkey.isShown())
           {
             ValidationMsg += "\n48. Required field: Donkey/mule/ass.";
             secDonkey.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDunkeyN.getText().toString().length()==0 & secDunkeyN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secDunkeyN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secDunkeyN.isShown() & (Integer.valueOf(txtDunkeyN.getText().toString().length()==0 ? "01" : txtDunkeyN.getText().toString()) < 01 || Integer.valueOf(txtDunkeyN.getText().toString().length()==0 ? "90" : txtDunkeyN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secDunkeyN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoHorse1.isChecked() & !rdoHorse2.isChecked() & secHorse.isShown())
           {
             ValidationMsg += "\n49. Required field: Horse.";
             secHorse.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtHorseN.getText().toString().length()==0 & secHorseN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secHorseN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secHorseN.isShown() & (Integer.valueOf(txtHorseN.getText().toString().length()==0 ? "01" : txtHorseN.getText().toString()) < 01 || Integer.valueOf(txtHorseN.getText().toString().length()==0 ? "90" : txtHorseN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secHorseN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoPig1.isChecked() & !rdoPig2.isChecked() & secPig.isShown())
           {
             ValidationMsg += "\n50. Required field: Pig.";
             secPig.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtPigN.getText().toString().length()==0 & secPigN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secPigN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secPigN.isShown() & (Integer.valueOf(txtPigN.getText().toString().length()==0 ? "01" : txtPigN.getText().toString()) < 01 || Integer.valueOf(txtPigN.getText().toString().length()==0 ? "90" : txtPigN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secPigN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoBirds1.isChecked() & !rdoBirds2.isChecked() & secBirds.isShown())
           {
             ValidationMsg += "\n50.1 Required field: Birds.";
             secBirds.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtBirdsN.getText().toString().length()==0 & secBirdsN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secBirdsN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secBirdsN.isShown() & (Integer.valueOf(txtBirdsN.getText().toString().length()==0 ? "01" : txtBirdsN.getText().toString()) < 01 || Integer.valueOf(txtBirdsN.getText().toString().length()==0 ? "90" : txtBirdsN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secBirdsN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoDogs1.isChecked() & !rdoDogs2.isChecked() & secDogs.isShown())
           {
             ValidationMsg += "\n50.2 Required field: Dogs.";
             secDogs.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtDogsN.getText().toString().length()==0 & secDogsN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secDogsN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secDogsN.isShown() & (Integer.valueOf(txtDogsN.getText().toString().length()==0 ? "01" : txtDogsN.getText().toString()) < 01 || Integer.valueOf(txtDogsN.getText().toString().length()==0 ? "90" : txtDogsN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secDogsN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoCats1.isChecked() & !rdoCats2.isChecked() & secCats.isShown())
           {
             ValidationMsg += "\n50.3 Required field: Cats.";
             secCats.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtCatsN.getText().toString().length()==0 & secCatsN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secCatsN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secCatsN.isShown() & (Integer.valueOf(txtCatsN.getText().toString().length()==0 ? "01" : txtCatsN.getText().toString()) < 01 || Integer.valueOf(txtCatsN.getText().toString().length()==0 ? "90" : txtCatsN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secCatsN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(!rdoFishNet1.isChecked() & !rdoFishNet2.isChecked() & secFishNet.isShown())
           {
             ValidationMsg += "\n50.4 Required field: Fishing net.";
             secFishNet.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtFishNetN.getText().toString().length()==0 & secFishNetN.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secFishNetN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secFishNetN.isShown() & (Integer.valueOf(txtFishNetN.getText().toString().length()==0 ? "01" : txtFishNetN.getText().toString()) < 01 || Integer.valueOf(txtFishNetN.getText().toString().length()==0 ? "90" : txtFishNetN.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secFishNetN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
         if(txtOtherAsset1N.getText().toString().length()==0 & secOtherAsset1N.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secOtherAsset1N.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secOtherAsset1N.isShown() & (Integer.valueOf(txtOtherAsset1N.getText().toString().length()==0 ? "01" : txtOtherAsset1N.getText().toString()) < 01 || Integer.valueOf(txtOtherAsset1N.getText().toString().length()==0 ? "90" : txtOtherAsset1N.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secOtherAsset1N.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtOtherAsset2.getText().toString().length()==0 & secOtherAsset2.isShown())
           {
             ValidationMsg += "\nRequired field: Other Asset-2.";
             secOtherAsset2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtOtherAsset2N.getText().toString().length()==0 & secOtherAsset2N.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secOtherAsset2N.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secOtherAsset2N.isShown() & (Integer.valueOf(txtOtherAsset2N.getText().toString().length()==0 ? "01" : txtOtherAsset2N.getText().toString()) < 01 || Integer.valueOf(txtOtherAsset2N.getText().toString().length()==0 ? "90" : txtOtherAsset2N.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secOtherAsset2N.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtOtherAsset3.getText().toString().length()==0 & secOtherAsset3.isShown())
           {
             ValidationMsg += "\nRequired field: Other Asset-3.";
             secOtherAsset3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(txtOtherAsset3N.getText().toString().length()==0 & secOtherAsset3N.isShown())
           {
             ValidationMsg += "\nRequired field: Number.";
             secOtherAsset3N.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
           }
         if(secOtherAsset3N.isShown() & (Integer.valueOf(txtOtherAsset3N.getText().toString().length()==0 ? "01" : txtOtherAsset3N.getText().toString()) < 01 || Integer.valueOf(txtOtherAsset3N.getText().toString().length()==0 ? "90" : txtOtherAsset3N.getText().toString()) > 90))
           {
             ValidationMsg += "\nValue should be between 01 and 90(Number).";
             secOtherAsset3N.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
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
             secWaterSource.setBackgroundColor(Color.WHITE);
             secFetchWaterM.setBackgroundColor(Color.WHITE);
             secFetchWaterM.setBackgroundColor(Color.WHITE);
             secFetchWaterMDk.setBackgroundColor(Color.WHITE);
             secGetWater.setBackgroundColor(Color.WHITE);
             secGetWaterOth.setBackgroundColor(Color.WHITE);
             secMemberID.setBackgroundColor(Color.WHITE);
             secBringWater.setBackgroundColor(Color.WHITE);
             secBringWater.setBackgroundColor(Color.WHITE);
             secBringWaterDk.setBackgroundColor(Color.WHITE);
             secSomeone.setBackgroundColor(Color.WHITE);
             secSecondPers.setBackgroundColor(Color.WHITE);
             secSecondPersOth.setBackgroundColor(Color.WHITE);
             secMemberID2nd.setBackgroundColor(Color.WHITE);
             secEnoughWater.setBackgroundColor(Color.WHITE);
             secMainWater.setBackgroundColor(Color.WHITE);
             secMainWaterOth.setBackgroundColor(Color.WHITE);
             secSmallTank.setBackgroundColor(Color.WHITE);
             secMediunTank.setBackgroundColor(Color.WHITE);
             secLargeTank.setBackgroundColor(Color.WHITE);
             secStoreDrink.setBackgroundColor(Color.WHITE);
             secRecoverWater.setBackgroundColor(Color.WHITE);
             secRecoverWaterOth.setBackgroundColor(Color.WHITE);
             secLessDanger.setBackgroundColor(Color.WHITE);
             secMakeSafer.setBackgroundColor(Color.WHITE);
             secMakeSaferOth.setBackgroundColor(Color.WHITE);
             secToilet.setBackgroundColor(Color.WHITE);
             secToiletOth.setBackgroundColor(Color.WHITE);
             secToiletShrd.setBackgroundColor(Color.WHITE);
             secToiletUseNo.setBackgroundColor(Color.WHITE);
             secToiletUseNo.setBackgroundColor(Color.WHITE);
             secToiletUseNoDk.setBackgroundColor(Color.WHITE);
             secToiletLoc.setBackgroundColor(Color.WHITE);
             secContentEmp.setBackgroundColor(Color.WHITE);
             secContentEmpOth.setBackgroundColor(Color.WHITE);
             secBowelMov.setBackgroundColor(Color.WHITE);
             secBowelMovOth.setBackgroundColor(Color.WHITE);
             secLiquidWaste.setBackgroundColor(Color.WHITE);
             secLiquidWasteOth.setBackgroundColor(Color.WHITE);
             secSolidWasteMethod.setBackgroundColor(Color.WHITE);
             secSolidWasteMethodOth.setBackgroundColor(Color.WHITE);
             secHandWash.setBackgroundColor(Color.WHITE);
             secShowWash.setBackgroundColor(Color.WHITE);
             secAvailableWat.setBackgroundColor(Color.WHITE);
             secAvailableSoap.setBackgroundColor(Color.WHITE);
             secCookDvc.setBackgroundColor(Color.WHITE);
             secCookDvcOth.setBackgroundColor(Color.WHITE);
             secCookFuel.setBackgroundColor(Color.WHITE);
             secCookFuelOth.setBackgroundColor(Color.WHITE);
             secCookPlc.setBackgroundColor(Color.WHITE);
             secCookPlcOth.setBackgroundColor(Color.WHITE);
             secFloor.setBackgroundColor(Color.WHITE);
             secFloorOth.setBackgroundColor(Color.WHITE);
             secGroundMat.setBackgroundColor(Color.WHITE);
             secGroundMatOth.setBackgroundColor(Color.WHITE);
             secRoof.setBackgroundColor(Color.WHITE);
             secRoofOth.setBackgroundColor(Color.WHITE);
             secSmokeInside.setBackgroundColor(Color.WHITE);
             secFreqSmoke.setBackgroundColor(Color.WHITE);
             secWall.setBackgroundColor(Color.WHITE);
             secWallOth.setBackgroundColor(Color.WHITE);
             secRoomSleep.setBackgroundColor(Color.WHITE);
             secRoomSleep.setBackgroundColor(Color.WHITE);
             secRoomSleepDk.setBackgroundColor(Color.WHITE);
             secElecNight.setBackgroundColor(Color.WHITE);
             secElecNightOth.setBackgroundColor(Color.WHITE);
             secHomesteadAny.setBackgroundColor(Color.WHITE);
             secOthLand.setBackgroundColor(Color.WHITE);
             secArea.setBackgroundColor(Color.WHITE);
             secArea.setBackgroundColor(Color.WHITE);
             secIncomeMo.setBackgroundColor(Color.WHITE);
             secExpenses.setBackgroundColor(Color.WHITE);
             secBankAcc.setBackgroundColor(Color.WHITE);
             secSprayInt.setBackgroundColor(Color.WHITE);
             secMosqNet.setBackgroundColor(Color.WHITE);
             secNetOwned.setBackgroundColor(Color.WHITE);
             secNetOwned.setBackgroundColor(Color.WHITE);
             secMedHome.setBackgroundColor(Color.WHITE);
             secAntimalarSpecifyOth.setBackgroundColor(Color.WHITE);
             secGetMedSpecifyOth.setBackgroundColor(Color.WHITE);
             secAComment.setBackgroundColor(Color.WHITE);
             secComment.setBackgroundColor(Color.WHITE);
             secElectricity.setBackgroundColor(Color.WHITE);
             secSolarPlate.setBackgroundColor(Color.WHITE);
             secSolarPlateN.setBackgroundColor(Color.WHITE);
             secSolarPlateN.setBackgroundColor(Color.WHITE);
             secHeater.setBackgroundColor(Color.WHITE);
             secHeaterN.setBackgroundColor(Color.WHITE);
             secHeaterN.setBackgroundColor(Color.WHITE);
             secAC.setBackgroundColor(Color.WHITE);
             secACN.setBackgroundColor(Color.WHITE);
             secACN.setBackgroundColor(Color.WHITE);
             secElecFan.setBackgroundColor(Color.WHITE);
             secElecFanN.setBackgroundColor(Color.WHITE);
             secElecFanN.setBackgroundColor(Color.WHITE);
             secLantern.setBackgroundColor(Color.WHITE);
             secLanternN.setBackgroundColor(Color.WHITE);
             secLanternN.setBackgroundColor(Color.WHITE);
             secLamp.setBackgroundColor(Color.WHITE);
             secLampN.setBackgroundColor(Color.WHITE);
             secLampN.setBackgroundColor(Color.WHITE);
             secGasLamp.setBackgroundColor(Color.WHITE);
             secGasLampN.setBackgroundColor(Color.WHITE);
             secGasLampN.setBackgroundColor(Color.WHITE);
             secPetroleum.setBackgroundColor(Color.WHITE);
             secPetroleumN.setBackgroundColor(Color.WHITE);
             secPetroleumN.setBackgroundColor(Color.WHITE);
             secMatt.setBackgroundColor(Color.WHITE);
             secMattN.setBackgroundColor(Color.WHITE);
             secMattN.setBackgroundColor(Color.WHITE);
             secMats.setBackgroundColor(Color.WHITE);
             secMatsN.setBackgroundColor(Color.WHITE);
             secMatsN.setBackgroundColor(Color.WHITE);
             secCarpets.setBackgroundColor(Color.WHITE);
             secCarpetsN.setBackgroundColor(Color.WHITE);
             secCarpetsN.setBackgroundColor(Color.WHITE);
             secBed.setBackgroundColor(Color.WHITE);
             secBedN.setBackgroundColor(Color.WHITE);
             secBedN.setBackgroundColor(Color.WHITE);
             secChair.setBackgroundColor(Color.WHITE);
             secChairN.setBackgroundColor(Color.WHITE);
             secChairN.setBackgroundColor(Color.WHITE);
             secSofa.setBackgroundColor(Color.WHITE);
             secSofaN.setBackgroundColor(Color.WHITE);
             secSofaN.setBackgroundColor(Color.WHITE);
             secTables.setBackgroundColor(Color.WHITE);
             secTablesN.setBackgroundColor(Color.WHITE);
             secTablesN.setBackgroundColor(Color.WHITE);
             secWatch.setBackgroundColor(Color.WHITE);
             secWatchN.setBackgroundColor(Color.WHITE);
             secWatchN.setBackgroundColor(Color.WHITE);
             secWMachine.setBackgroundColor(Color.WHITE);
             secWMachineN.setBackgroundColor(Color.WHITE);
             secWMachineN.setBackgroundColor(Color.WHITE);
             secIron.setBackgroundColor(Color.WHITE);
             secIronN.setBackgroundColor(Color.WHITE);
             secIronN.setBackgroundColor(Color.WHITE);
             secBooth.setBackgroundColor(Color.WHITE);
             secBoothN.setBackgroundColor(Color.WHITE);
             secBoothN.setBackgroundColor(Color.WHITE);
             secSMachine.setBackgroundColor(Color.WHITE);
             secSMachineN.setBackgroundColor(Color.WHITE);
             secSMachineN.setBackgroundColor(Color.WHITE);
             secGenerator.setBackgroundColor(Color.WHITE);
             secGeneratorN.setBackgroundColor(Color.WHITE);
             secGeneratorN.setBackgroundColor(Color.WHITE);
             secInternet.setBackgroundColor(Color.WHITE);
             secInternetN.setBackgroundColor(Color.WHITE);
             secInternetN.setBackgroundColor(Color.WHITE);
             secSatellite.setBackgroundColor(Color.WHITE);
             secSatelliteN.setBackgroundColor(Color.WHITE);
             secSatelliteN.setBackgroundColor(Color.WHITE);
             secLandline.setBackgroundColor(Color.WHITE);
             secLandlineN.setBackgroundColor(Color.WHITE);
             secLandlineN.setBackgroundColor(Color.WHITE);
             secCellphone.setBackgroundColor(Color.WHITE);
             secCellphoneN.setBackgroundColor(Color.WHITE);
             secCellphoneN.setBackgroundColor(Color.WHITE);
             secTV.setBackgroundColor(Color.WHITE);
             secTVN.setBackgroundColor(Color.WHITE);
             secTVN.setBackgroundColor(Color.WHITE);
             secTV5.setBackgroundColor(Color.WHITE);
             secTV5N.setBackgroundColor(Color.WHITE);
             secTV5N.setBackgroundColor(Color.WHITE);
             secChannel.setBackgroundColor(Color.WHITE);
             secChannelN.setBackgroundColor(Color.WHITE);
             secChannelN.setBackgroundColor(Color.WHITE);
             secRadio.setBackgroundColor(Color.WHITE);
             secRadioN.setBackgroundColor(Color.WHITE);
             secRadioN.setBackgroundColor(Color.WHITE);
             secDVD.setBackgroundColor(Color.WHITE);
             secDVDN.setBackgroundColor(Color.WHITE);
             secDVDN.setBackgroundColor(Color.WHITE);
             secVideo.setBackgroundColor(Color.WHITE);
             secVideoN.setBackgroundColor(Color.WHITE);
             secVideoN.setBackgroundColor(Color.WHITE);
             secComputer.setBackgroundColor(Color.WHITE);
             secComputerN.setBackgroundColor(Color.WHITE);
             secComputerN.setBackgroundColor(Color.WHITE);
             secLaptop.setBackgroundColor(Color.WHITE);
             secLaptopN.setBackgroundColor(Color.WHITE);
             secLaptopN.setBackgroundColor(Color.WHITE);
             secCable.setBackgroundColor(Color.WHITE);
             secCableN.setBackgroundColor(Color.WHITE);
             secCableN.setBackgroundColor(Color.WHITE);
             secMicrowave.setBackgroundColor(Color.WHITE);
             secMicrowaveN.setBackgroundColor(Color.WHITE);
             secMicrowaveN.setBackgroundColor(Color.WHITE);
             secGeyser.setBackgroundColor(Color.WHITE);
             secGeyserN.setBackgroundColor(Color.WHITE);
             secGeyserN.setBackgroundColor(Color.WHITE);
             secGrill.setBackgroundColor(Color.WHITE);
             secGrillN.setBackgroundColor(Color.WHITE);
             secGrillN.setBackgroundColor(Color.WHITE);
             secGrain.setBackgroundColor(Color.WHITE);
             secGrainN.setBackgroundColor(Color.WHITE);
             secGrainN.setBackgroundColor(Color.WHITE);
             secRefrigerator.setBackgroundColor(Color.WHITE);
             secRefrigeratorN.setBackgroundColor(Color.WHITE);
             secRefrigeratorN.setBackgroundColor(Color.WHITE);
             secDeepFreezer.setBackgroundColor(Color.WHITE);
             secDeepFreezerN.setBackgroundColor(Color.WHITE);
             secDeepFreezerN.setBackgroundColor(Color.WHITE);
             secStove.setBackgroundColor(Color.WHITE);
             secStoveN.setBackgroundColor(Color.WHITE);
             secStoveN.setBackgroundColor(Color.WHITE);
             secGasHob.setBackgroundColor(Color.WHITE);
             secGasHobN.setBackgroundColor(Color.WHITE);
             secGasHobN.setBackgroundColor(Color.WHITE);
             secImpCooker.setBackgroundColor(Color.WHITE);
             secImpCookerN.setBackgroundColor(Color.WHITE);
             secImpCookerN.setBackgroundColor(Color.WHITE);
             secBike.setBackgroundColor(Color.WHITE);
             secBikeN.setBackgroundColor(Color.WHITE);
             secBikeN.setBackgroundColor(Color.WHITE);
             secMotorcycle.setBackgroundColor(Color.WHITE);
             secMotorcycleN.setBackgroundColor(Color.WHITE);
             secMotorcycleN.setBackgroundColor(Color.WHITE);
             secCar.setBackgroundColor(Color.WHITE);
             secCarN.setBackgroundColor(Color.WHITE);
             secCarN.setBackgroundColor(Color.WHITE);
             secRickshaw.setBackgroundColor(Color.WHITE);
             secRickshawN.setBackgroundColor(Color.WHITE);
             secRickshawN.setBackgroundColor(Color.WHITE);
             secCart.setBackgroundColor(Color.WHITE);
             secCartN.setBackgroundColor(Color.WHITE);
             secCartN.setBackgroundColor(Color.WHITE);
             secCanoe.setBackgroundColor(Color.WHITE);
             secCanoeN.setBackgroundColor(Color.WHITE);
             secCanoeN.setBackgroundColor(Color.WHITE);
             secBus.setBackgroundColor(Color.WHITE);
             secBusN.setBackgroundColor(Color.WHITE);
             secBusN.setBackgroundColor(Color.WHITE);
             secTractor.setBackgroundColor(Color.WHITE);
             secTractorN.setBackgroundColor(Color.WHITE);
             secTractorN.setBackgroundColor(Color.WHITE);
             secPlow.setBackgroundColor(Color.WHITE);
             secPlowN.setBackgroundColor(Color.WHITE);
             secPlowN.setBackgroundColor(Color.WHITE);
             secDuck.setBackgroundColor(Color.WHITE);
             secDuckN.setBackgroundColor(Color.WHITE);
             secDuckN.setBackgroundColor(Color.WHITE);
             secCow.setBackgroundColor(Color.WHITE);
             secCowN.setBackgroundColor(Color.WHITE);
             secCowN.setBackgroundColor(Color.WHITE);
             secSheep.setBackgroundColor(Color.WHITE);
             secSheepN.setBackgroundColor(Color.WHITE);
             secSheepN.setBackgroundColor(Color.WHITE);
             secGoat.setBackgroundColor(Color.WHITE);
             secGoatN.setBackgroundColor(Color.WHITE);
             secGoatN.setBackgroundColor(Color.WHITE);
             secChicken.setBackgroundColor(Color.WHITE);
             secChickenN.setBackgroundColor(Color.WHITE);
             secChickenN.setBackgroundColor(Color.WHITE);
             secDonkey.setBackgroundColor(Color.WHITE);
             secDunkeyN.setBackgroundColor(Color.WHITE);
             secDunkeyN.setBackgroundColor(Color.WHITE);
             secHorse.setBackgroundColor(Color.WHITE);
             secHorseN.setBackgroundColor(Color.WHITE);
             secHorseN.setBackgroundColor(Color.WHITE);
             secPig.setBackgroundColor(Color.WHITE);
             secPigN.setBackgroundColor(Color.WHITE);
             secPigN.setBackgroundColor(Color.WHITE);
             secBirds.setBackgroundColor(Color.WHITE);
             secBirdsN.setBackgroundColor(Color.WHITE);
             secBirdsN.setBackgroundColor(Color.WHITE);
             secDogs.setBackgroundColor(Color.WHITE);
             secDogsN.setBackgroundColor(Color.WHITE);
             secDogsN.setBackgroundColor(Color.WHITE);
             secCats.setBackgroundColor(Color.WHITE);
             secCatsN.setBackgroundColor(Color.WHITE);
             secCatsN.setBackgroundColor(Color.WHITE);
             secFishNet.setBackgroundColor(Color.WHITE);
             secFishNetN.setBackgroundColor(Color.WHITE);
             secFishNetN.setBackgroundColor(Color.WHITE);
             secOtherAsset.setBackgroundColor(Color.WHITE);
             secOtherAsset1.setBackgroundColor(Color.WHITE);
             secOtherAsset1N.setBackgroundColor(Color.WHITE);
             secOtherAsset1N.setBackgroundColor(Color.WHITE);
             secOtherAsset2.setBackgroundColor(Color.WHITE);
             secOtherAsset2N.setBackgroundColor(Color.WHITE);
             secOtherAsset2N.setBackgroundColor(Color.WHITE);
             secOtherAsset3.setBackgroundColor(Color.WHITE);
             secOtherAsset3N.setBackgroundColor(Color.WHITE);
             secOtherAsset3N.setBackgroundColor(Color.WHITE);
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
           SES_Mali_DataModel d = new SES_Mali_DataModel();
           String SQL = "Select * from "+ TableName +"  Where HHID='"+ HHID +"' and SESNo='"+ SESNo +"'";
           List<SES_Mali_DataModel> data = d.SelectAll(this, SQL);
           for(SES_Mali_DataModel item : data){
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
             String[] d_rdogrpWaterSource = new String[] {"1","2","7","8","9"};
             for (int i = 0; i < d_rdogrpWaterSource.length; i++)
             {
                 if (String.valueOf(item.getWaterSource()).equals(String.valueOf(d_rdogrpWaterSource[i])))
                 {
                     rb = (RadioButton)rdogrpWaterSource.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtFetchWaterM.setText(String.valueOf(item.getFetchWaterM()));
             String[] d_rdogrpFetchWaterMDk = new String[] {"98","99"};
             for (int i = 0; i < d_rdogrpFetchWaterMDk.length; i++)
             {
                 if (String.valueOf(item.getFetchWaterMDk()).equals(String.valueOf(d_rdogrpFetchWaterMDk[i])))
                 {
                     rb = (RadioButton)rdogrpFetchWaterMDk.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpGetWater = new String[] {"1","2","7"};
             for (int i = 0; i < d_rdogrpGetWater.length; i++)
             {
                 if (String.valueOf(item.getGetWater()).equals(String.valueOf(d_rdogrpGetWater[i])))
                 {
                     rb = (RadioButton)rdogrpGetWater.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtGetWaterOth.setText(item.getGetWaterOth());
             spnMemberID.setSelection(Global.SpinnerItemPositionAnyLength(spnMemberID, String.valueOf(item.getMemberID())));
             txtBringWater.setText(String.valueOf(item.getBringWater()));
             String[] d_rdogrpBringWaterDk = new String[] {"98","99"};
             for (int i = 0; i < d_rdogrpBringWaterDk.length; i++)
             {
                 if (String.valueOf(item.getBringWaterDk()).equals(String.valueOf(d_rdogrpBringWaterDk[i])))
                 {
                     rb = (RadioButton)rdogrpBringWaterDk.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpSomeone = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpSomeone.length; i++)
             {
                 if (String.valueOf(item.getSomeone()).equals(String.valueOf(d_rdogrpSomeone[i])))
                 {
                     rb = (RadioButton)rdogrpSomeone.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpSecondPers = new String[] {"1","2","7"};
             for (int i = 0; i < d_rdogrpSecondPers.length; i++)
             {
                 if (String.valueOf(item.getSecondPers()).equals(String.valueOf(d_rdogrpSecondPers[i])))
                 {
                     rb = (RadioButton)rdogrpSecondPers.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtSecondPersOth.setText(item.getSecondPersOth());
             spnMemberID2nd.setSelection(Global.SpinnerItemPositionAnyLength(spnMemberID2nd, String.valueOf(item.getMemberID2nd())));
             String[] d_rdogrpEnoughWater = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpEnoughWater.length; i++)
             {
                 if (String.valueOf(item.getEnoughWater()).equals(String.valueOf(d_rdogrpEnoughWater[i])))
                 {
                     rb = (RadioButton)rdogrpEnoughWater.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             spnMainWater.setSelection(Global.SpinnerItemPositionAnyLength(spnMainWater, String.valueOf(item.getMainWater())));
             txtMainWaterOth.setText(item.getMainWaterOth());
             String[] d_rdogrpSmallTank = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpSmallTank.length; i++)
             {
                 if (String.valueOf(item.getSmallTank()).equals(String.valueOf(d_rdogrpSmallTank[i])))
                 {
                     rb = (RadioButton)rdogrpSmallTank.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpMediunTank = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpMediunTank.length; i++)
             {
                 if (String.valueOf(item.getMediunTank()).equals(String.valueOf(d_rdogrpMediunTank[i])))
                 {
                     rb = (RadioButton)rdogrpMediunTank.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpLargeTank = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpLargeTank.length; i++)
             {
                 if (String.valueOf(item.getLargeTank()).equals(String.valueOf(d_rdogrpLargeTank[i])))
                 {
                     rb = (RadioButton)rdogrpLargeTank.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpStoreDrink = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpStoreDrink.length; i++)
             {
                 if (String.valueOf(item.getStoreDrink()).equals(String.valueOf(d_rdogrpStoreDrink[i])))
                 {
                     rb = (RadioButton)rdogrpStoreDrink.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getContainOpenCov()).equals("1"))
             {
                chkContainOpenCov.setChecked(true);
             }
             else if(String.valueOf(item.getContainOpenCov()).equals("2"))
             {
                chkContainOpenCov.setChecked(false);
             }
             if(String.valueOf(item.getContainOpenNotCov()).equals("1"))
             {
                chkContainOpenNotCov.setChecked(true);
             }
             else if(String.valueOf(item.getContainOpenNotCov()).equals("2"))
             {
                chkContainOpenNotCov.setChecked(false);
             }
             if(String.valueOf(item.getContainOpenDK()).equals("1"))
             {
                chkContainOpenDK.setChecked(true);
             }
             else if(String.valueOf(item.getContainOpenDK()).equals("2"))
             {
                chkContainOpenDK.setChecked(false);
             }
             String[] d_rdogrpRecoverWater = new String[] {"1","2","3","7"};
             for (int i = 0; i < d_rdogrpRecoverWater.length; i++)
             {
                 if (String.valueOf(item.getRecoverWater()).equals(String.valueOf(d_rdogrpRecoverWater[i])))
                 {
                     rb = (RadioButton)rdogrpRecoverWater.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtRecoverWaterOth.setText(item.getRecoverWaterOth());
             String[] d_rdogrpLessDanger = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpLessDanger.length; i++)
             {
                 if (String.valueOf(item.getLessDanger()).equals(String.valueOf(d_rdogrpLessDanger[i])))
                 {
                     rb = (RadioButton)rdogrpLessDanger.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             spnMakeSafer.setSelection(Global.SpinnerItemPositionAnyLength(spnMakeSafer, String.valueOf(item.getMakeSafer())));
             txtMakeSaferOth.setText(item.getMakeSaferOth());
             spnToilet.setSelection(Global.SpinnerItemPositionAnyLength(spnToilet, String.valueOf(item.getToilet())));
             txtToiletOth.setText(item.getToiletOth());
             String[] d_rdogrpToiletShrd = new String[] {"0","1","2"};
             for (int i = 0; i < d_rdogrpToiletShrd.length; i++)
             {
                 if (String.valueOf(item.getToiletShrd()).equals(String.valueOf(d_rdogrpToiletShrd[i])))
                 {
                     rb = (RadioButton)rdogrpToiletShrd.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtToiletUseNo.setText(String.valueOf(item.getToiletUseNo()));
             String[] d_rdogrpToiletUseNoDk = new String[] {"95","98","99"};
             for (int i = 0; i < d_rdogrpToiletUseNoDk.length; i++)
             {
                 if (String.valueOf(item.getToiletUseNoDk()).equals(String.valueOf(d_rdogrpToiletUseNoDk[i])))
                 {
                     rb = (RadioButton)rdogrpToiletUseNoDk.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpToiletLoc = new String[] {"1","2","7"};
             for (int i = 0; i < d_rdogrpToiletLoc.length; i++)
             {
                 if (String.valueOf(item.getToiletLoc()).equals(String.valueOf(d_rdogrpToiletLoc[i])))
                 {
                     rb = (RadioButton)rdogrpToiletLoc.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpContentEmp = new String[] {"1","2","3","4","5","7","8"};
             for (int i = 0; i < d_rdogrpContentEmp.length; i++)
             {
                 if (String.valueOf(item.getContentEmp()).equals(String.valueOf(d_rdogrpContentEmp[i])))
                 {
                     rb = (RadioButton)rdogrpContentEmp.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtContentEmpOth.setText(item.getContentEmpOth());
             spnBowelMov.setSelection(Global.SpinnerItemPositionAnyLength(spnBowelMov, String.valueOf(item.getBowelMov())));
             txtBowelMovOth.setText(item.getBowelMovOth());
             String[] d_rdogrpLiquidWaste = new String[] {"1","2","3","4","5","7"};
             for (int i = 0; i < d_rdogrpLiquidWaste.length; i++)
             {
                 if (String.valueOf(item.getLiquidWaste()).equals(String.valueOf(d_rdogrpLiquidWaste[i])))
                 {
                     rb = (RadioButton)rdogrpLiquidWaste.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtLiquidWasteOth.setText(item.getLiquidWasteOth());
             spnSolidWasteMethod.setSelection(Global.SpinnerItemPositionAnyLength(spnSolidWasteMethod, String.valueOf(item.getSolidWasteMethod())));
             txtSolidWasteMethodOth.setText(item.getSolidWasteMethodOth());
             String[] d_rdogrpHandWash = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpHandWash.length; i++)
             {
                 if (String.valueOf(item.getHandWash()).equals(String.valueOf(d_rdogrpHandWash[i])))
                 {
                     rb = (RadioButton)rdogrpHandWash.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpShowWash = new String[] {"1","2","3","4","5","6"};
             for (int i = 0; i < d_rdogrpShowWash.length; i++)
             {
                 if (String.valueOf(item.getShowWash()).equals(String.valueOf(d_rdogrpShowWash[i])))
                 {
                     rb = (RadioButton)rdogrpShowWash.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpAvailableWat = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpAvailableWat.length; i++)
             {
                 if (String.valueOf(item.getAvailableWat()).equals(String.valueOf(d_rdogrpAvailableWat[i])))
                 {
                     rb = (RadioButton)rdogrpAvailableWat.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpAvailableSoap = new String[] {"1","2","3"};
             for (int i = 0; i < d_rdogrpAvailableSoap.length; i++)
             {
                 if (String.valueOf(item.getAvailableSoap()).equals(String.valueOf(d_rdogrpAvailableSoap[i])))
                 {
                     rb = (RadioButton)rdogrpAvailableSoap.getChildAt(i);
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
             spnGroundMat.setSelection(Global.SpinnerItemPositionAnyLength(spnGroundMat, String.valueOf(item.getGroundMat())));
             txtGroundMatOth.setText(item.getGroundMatOth());
             spnRoof.setSelection(Global.SpinnerItemPositionAnyLength(spnRoof, String.valueOf(item.getRoof())));
             txtRoofOth.setText(item.getRoofOth());
             String[] d_rdogrpSmokeInside = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpSmokeInside.length; i++)
             {
                 if (String.valueOf(item.getSmokeInside()).equals(String.valueOf(d_rdogrpSmokeInside[i])))
                 {
                     rb = (RadioButton)rdogrpSmokeInside.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpFreqSmoke = new String[] {"1","2","3","4"};
             for (int i = 0; i < d_rdogrpFreqSmoke.length; i++)
             {
                 if (String.valueOf(item.getFreqSmoke()).equals(String.valueOf(d_rdogrpFreqSmoke[i])))
                 {
                     rb = (RadioButton)rdogrpFreqSmoke.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
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
             spnElecNight.setSelection(Global.SpinnerItemPositionAnyLength(spnElecNight, String.valueOf(item.getElecNight())));
             txtElecNightOth.setText(item.getElecNightOth());
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
             txtArea.setText(String.valueOf(item.getArea()));
             String[] d_rdogrpIncomeMo = new String[] {"1","2","3","4","5","6","7","8"};
             for (int i = 0; i < d_rdogrpIncomeMo.length; i++)
             {
                 if (String.valueOf(item.getIncomeMo()).equals(String.valueOf(d_rdogrpIncomeMo[i])))
                 {
                     rb = (RadioButton)rdogrpIncomeMo.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpExpenses = new String[] {"1","2","3","4","5","6","7","8"};
             for (int i = 0; i < d_rdogrpExpenses.length; i++)
             {
                 if (String.valueOf(item.getExpenses()).equals(String.valueOf(d_rdogrpExpenses[i])))
                 {
                     rb = (RadioButton)rdogrpExpenses.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpBankAcc = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpBankAcc.length; i++)
             {
                 if (String.valueOf(item.getBankAcc()).equals(String.valueOf(d_rdogrpBankAcc[i])))
                 {
                     rb = (RadioButton)rdogrpBankAcc.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpSprayInt = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpSprayInt.length; i++)
             {
                 if (String.valueOf(item.getSprayInt()).equals(String.valueOf(d_rdogrpSprayInt[i])))
                 {
                     rb = (RadioButton)rdogrpSprayInt.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpMosqNet = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpMosqNet.length; i++)
             {
                 if (String.valueOf(item.getMosqNet()).equals(String.valueOf(d_rdogrpMosqNet[i])))
                 {
                     rb = (RadioButton)rdogrpMosqNet.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtNetOwned.setText(String.valueOf(item.getNetOwned()));
             String[] d_rdogrpMedHome = new String[] {"0","1","8"};
             for (int i = 0; i < d_rdogrpMedHome.length; i++)
             {
                 if (String.valueOf(item.getMedHome()).equals(String.valueOf(d_rdogrpMedHome[i])))
                 {
                     rb = (RadioButton)rdogrpMedHome.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             if(String.valueOf(item.getMedTypeAM()).equals("1"))
             {
                chkMedTypeAM.setChecked(true);
             }
             else if(String.valueOf(item.getMedTypeAM()).equals("2"))
             {
                chkMedTypeAM.setChecked(false);
             }
             if(String.valueOf(item.getMedTypeAB()).equals("1"))
             {
                chkMedTypeAB.setChecked(true);
             }
             else if(String.valueOf(item.getMedTypeAB()).equals("2"))
             {
                chkMedTypeAB.setChecked(false);
             }
             if(String.valueOf(item.getMedTypeDK()).equals("1"))
             {
                chkMedTypeDK.setChecked(true);
             }
             else if(String.valueOf(item.getMedTypeDK()).equals("2"))
             {
                chkMedTypeDK.setChecked(false);
             }
             if(String.valueOf(item.getAntimalarAL()).equals("1"))
             {
                chkAntimalarAL.setChecked(true);
             }
             else if(String.valueOf(item.getAntimalarAL()).equals("2"))
             {
                chkAntimalarAL.setChecked(false);
             }
             if(String.valueOf(item.getAntimalarASAQ()).equals("1"))
             {
                chkAntimalarASAQ.setChecked(true);
             }
             else if(String.valueOf(item.getAntimalarASAQ()).equals("2"))
             {
                chkAntimalarASAQ.setChecked(false);
             }
             if(String.valueOf(item.getAntimalarSP()).equals("1"))
             {
                chkAntimalarSP.setChecked(true);
             }
             else if(String.valueOf(item.getAntimalarSP()).equals("2"))
             {
                chkAntimalarSP.setChecked(false);
             }
             if(String.valueOf(item.getAntimalarOth()).equals("1"))
             {
                chkAntimalarOth.setChecked(true);
             }
             else if(String.valueOf(item.getAntimalarOth()).equals("2"))
             {
                chkAntimalarOth.setChecked(false);
             }
             txtAntimalarSpecifyOth.setText(item.getAntimalarSpecifyOth());
             if(String.valueOf(item.getGetMedHosp()).equals("1"))
             {
                chkGetMedHosp.setChecked(true);
             }
             else if(String.valueOf(item.getGetMedHosp()).equals("2"))
             {
                chkGetMedHosp.setChecked(false);
             }
             if(String.valueOf(item.getGetMedCSCom()).equals("1"))
             {
                chkGetMedCSCom.setChecked(true);
             }
             else if(String.valueOf(item.getGetMedCSCom()).equals("2"))
             {
                chkGetMedCSCom.setChecked(false);
             }
             if(String.valueOf(item.getGetMedPrvCl()).equals("1"))
             {
                chkGetMedPrvCl.setChecked(true);
             }
             else if(String.valueOf(item.getGetMedPrvCl()).equals("2"))
             {
                chkGetMedPrvCl.setChecked(false);
             }
             if(String.valueOf(item.getGetMedPhar()).equals("1"))
             {
                chkGetMedPhar.setChecked(true);
             }
             else if(String.valueOf(item.getGetMedPhar()).equals("2"))
             {
                chkGetMedPhar.setChecked(false);
             }
             if(String.valueOf(item.getGetMedPD()).equals("1"))
             {
                chkGetMedPD.setChecked(true);
             }
             else if(String.valueOf(item.getGetMedPD()).equals("2"))
             {
                chkGetMedPD.setChecked(false);
             }
             if(String.valueOf(item.getGetMedCHW()).equals("1"))
             {
                chkGetMedCHW.setChecked(true);
             }
             else if(String.valueOf(item.getGetMedCHW()).equals("2"))
             {
                chkGetMedCHW.setChecked(false);
             }
             if(String.valueOf(item.getGetMedSS()).equals("1"))
             {
                chkGetMedSS.setChecked(true);
             }
             else if(String.valueOf(item.getGetMedSS()).equals("2"))
             {
                chkGetMedSS.setChecked(false);
             }
             if(String.valueOf(item.getGetMedOth()).equals("1"))
             {
                chkGetMedOth.setChecked(true);
             }
             else if(String.valueOf(item.getGetMedOth()).equals("2"))
             {
                chkGetMedOth.setChecked(false);
             }
             txtGetMedSpecifyOth.setText(item.getGetMedSpecifyOth());
             String[] d_rdogrpAComment = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpAComment.length; i++)
             {
                 if (String.valueOf(item.getAComment()).equals(String.valueOf(d_rdogrpAComment[i])))
                 {
                     rb = (RadioButton)rdogrpAComment.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtComment.setText(item.getComment());
             String[] d_rdogrpElectricity = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpElectricity.length; i++)
             {
                 if (String.valueOf(item.getElectricity()).equals(String.valueOf(d_rdogrpElectricity[i])))
                 {
                     rb = (RadioButton)rdogrpElectricity.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpSolarPlate = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpSolarPlate.length; i++)
             {
                 if (String.valueOf(item.getSolarPlate()).equals(String.valueOf(d_rdogrpSolarPlate[i])))
                 {
                     rb = (RadioButton)rdogrpSolarPlate.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtSolarPlateN.setText(String.valueOf(item.getSolarPlateN()));
             String[] d_rdogrpHeater = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpHeater.length; i++)
             {
                 if (String.valueOf(item.getHeater()).equals(String.valueOf(d_rdogrpHeater[i])))
                 {
                     rb = (RadioButton)rdogrpHeater.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtHeaterN.setText(String.valueOf(item.getHeaterN()));
             String[] d_rdogrpAC = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpAC.length; i++)
             {
                 if (String.valueOf(item.getAC()).equals(String.valueOf(d_rdogrpAC[i])))
                 {
                     rb = (RadioButton)rdogrpAC.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtACN.setText(String.valueOf(item.getACN()));
             String[] d_rdogrpElecFan = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpElecFan.length; i++)
             {
                 if (String.valueOf(item.getElecFan()).equals(String.valueOf(d_rdogrpElecFan[i])))
                 {
                     rb = (RadioButton)rdogrpElecFan.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtElecFanN.setText(String.valueOf(item.getElecFanN()));
             String[] d_rdogrpLantern = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpLantern.length; i++)
             {
                 if (String.valueOf(item.getLantern()).equals(String.valueOf(d_rdogrpLantern[i])))
                 {
                     rb = (RadioButton)rdogrpLantern.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtLanternN.setText(String.valueOf(item.getLanternN()));
             String[] d_rdogrpLamp = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpLamp.length; i++)
             {
                 if (String.valueOf(item.getLamp()).equals(String.valueOf(d_rdogrpLamp[i])))
                 {
                     rb = (RadioButton)rdogrpLamp.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtLampN.setText(String.valueOf(item.getLampN()));
             String[] d_rdogrpGasLamp = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpGasLamp.length; i++)
             {
                 if (String.valueOf(item.getGasLamp()).equals(String.valueOf(d_rdogrpGasLamp[i])))
                 {
                     rb = (RadioButton)rdogrpGasLamp.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtGasLampN.setText(String.valueOf(item.getGasLampN()));
             String[] d_rdogrpPetroleum = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPetroleum.length; i++)
             {
                 if (String.valueOf(item.getPetroleum()).equals(String.valueOf(d_rdogrpPetroleum[i])))
                 {
                     rb = (RadioButton)rdogrpPetroleum.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtPetroleumN.setText(String.valueOf(item.getPetroleumN()));
             String[] d_rdogrpMatt = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpMatt.length; i++)
             {
                 if (String.valueOf(item.getMatt()).equals(String.valueOf(d_rdogrpMatt[i])))
                 {
                     rb = (RadioButton)rdogrpMatt.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtMattN.setText(String.valueOf(item.getMattN()));
             String[] d_rdogrpMats = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpMats.length; i++)
             {
                 if (String.valueOf(item.getMats()).equals(String.valueOf(d_rdogrpMats[i])))
                 {
                     rb = (RadioButton)rdogrpMats.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtMatsN.setText(String.valueOf(item.getMatsN()));
             String[] d_rdogrpCarpets = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCarpets.length; i++)
             {
                 if (String.valueOf(item.getCarpets()).equals(String.valueOf(d_rdogrpCarpets[i])))
                 {
                     rb = (RadioButton)rdogrpCarpets.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtCarpetsN.setText(String.valueOf(item.getCarpetsN()));
             String[] d_rdogrpBed = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpBed.length; i++)
             {
                 if (String.valueOf(item.getBed()).equals(String.valueOf(d_rdogrpBed[i])))
                 {
                     rb = (RadioButton)rdogrpBed.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtBedN.setText(String.valueOf(item.getBedN()));
             String[] d_rdogrpChair = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpChair.length; i++)
             {
                 if (String.valueOf(item.getChair()).equals(String.valueOf(d_rdogrpChair[i])))
                 {
                     rb = (RadioButton)rdogrpChair.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtChairN.setText(String.valueOf(item.getChairN()));
             String[] d_rdogrpSofa = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpSofa.length; i++)
             {
                 if (String.valueOf(item.getSofa()).equals(String.valueOf(d_rdogrpSofa[i])))
                 {
                     rb = (RadioButton)rdogrpSofa.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtSofaN.setText(String.valueOf(item.getSofaN()));
             String[] d_rdogrpTables = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpTables.length; i++)
             {
                 if (String.valueOf(item.getTables()).equals(String.valueOf(d_rdogrpTables[i])))
                 {
                     rb = (RadioButton)rdogrpTables.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtTablesN.setText(String.valueOf(item.getTablesN()));
             String[] d_rdogrpWatch = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpWatch.length; i++)
             {
                 if (String.valueOf(item.getWatch()).equals(String.valueOf(d_rdogrpWatch[i])))
                 {
                     rb = (RadioButton)rdogrpWatch.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtWatchN.setText(String.valueOf(item.getWatchN()));
             String[] d_rdogrpWMachine = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpWMachine.length; i++)
             {
                 if (String.valueOf(item.getWMachine()).equals(String.valueOf(d_rdogrpWMachine[i])))
                 {
                     rb = (RadioButton)rdogrpWMachine.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtWMachineN.setText(String.valueOf(item.getWMachineN()));
             String[] d_rdogrpIron = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpIron.length; i++)
             {
                 if (String.valueOf(item.getIron()).equals(String.valueOf(d_rdogrpIron[i])))
                 {
                     rb = (RadioButton)rdogrpIron.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtIronN.setText(String.valueOf(item.getIronN()));
             String[] d_rdogrpBooth = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpBooth.length; i++)
             {
                 if (String.valueOf(item.getBooth()).equals(String.valueOf(d_rdogrpBooth[i])))
                 {
                     rb = (RadioButton)rdogrpBooth.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtBoothN.setText(String.valueOf(item.getBoothN()));
             String[] d_rdogrpSMachine = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpSMachine.length; i++)
             {
                 if (String.valueOf(item.getSMachine()).equals(String.valueOf(d_rdogrpSMachine[i])))
                 {
                     rb = (RadioButton)rdogrpSMachine.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtSMachineN.setText(String.valueOf(item.getSMachineN()));
             String[] d_rdogrpGenerator = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpGenerator.length; i++)
             {
                 if (String.valueOf(item.getGenerator()).equals(String.valueOf(d_rdogrpGenerator[i])))
                 {
                     rb = (RadioButton)rdogrpGenerator.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtGeneratorN.setText(String.valueOf(item.getGeneratorN()));
             String[] d_rdogrpInternet = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpInternet.length; i++)
             {
                 if (String.valueOf(item.getInternet()).equals(String.valueOf(d_rdogrpInternet[i])))
                 {
                     rb = (RadioButton)rdogrpInternet.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtInternetN.setText(String.valueOf(item.getInternetN()));
             String[] d_rdogrpSatellite = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpSatellite.length; i++)
             {
                 if (String.valueOf(item.getSatellite()).equals(String.valueOf(d_rdogrpSatellite[i])))
                 {
                     rb = (RadioButton)rdogrpSatellite.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtSatelliteN.setText(String.valueOf(item.getSatelliteN()));
             String[] d_rdogrpLandline = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpLandline.length; i++)
             {
                 if (String.valueOf(item.getLandline()).equals(String.valueOf(d_rdogrpLandline[i])))
                 {
                     rb = (RadioButton)rdogrpLandline.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtLandlineN.setText(String.valueOf(item.getLandlineN()));
             String[] d_rdogrpCellphone = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCellphone.length; i++)
             {
                 if (String.valueOf(item.getCellphone()).equals(String.valueOf(d_rdogrpCellphone[i])))
                 {
                     rb = (RadioButton)rdogrpCellphone.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtCellphoneN.setText(String.valueOf(item.getCellphoneN()));
             String[] d_rdogrpTV = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpTV.length; i++)
             {
                 if (String.valueOf(item.getTV()).equals(String.valueOf(d_rdogrpTV[i])))
                 {
                     rb = (RadioButton)rdogrpTV.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtTVN.setText(String.valueOf(item.getTVN()));
             String[] d_rdogrpTV5 = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpTV5.length; i++)
             {
                 if (String.valueOf(item.getTV5()).equals(String.valueOf(d_rdogrpTV5[i])))
                 {
                     rb = (RadioButton)rdogrpTV5.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtTV5N.setText(String.valueOf(item.getTV5N()));
             String[] d_rdogrpChannel = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpChannel.length; i++)
             {
                 if (String.valueOf(item.getChannel()).equals(String.valueOf(d_rdogrpChannel[i])))
                 {
                     rb = (RadioButton)rdogrpChannel.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtChannelN.setText(String.valueOf(item.getChannelN()));
             String[] d_rdogrpRadio = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpRadio.length; i++)
             {
                 if (String.valueOf(item.getRadio()).equals(String.valueOf(d_rdogrpRadio[i])))
                 {
                     rb = (RadioButton)rdogrpRadio.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtRadioN.setText(String.valueOf(item.getRadioN()));
             String[] d_rdogrpDVD = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpDVD.length; i++)
             {
                 if (String.valueOf(item.getDVD()).equals(String.valueOf(d_rdogrpDVD[i])))
                 {
                     rb = (RadioButton)rdogrpDVD.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtDVDN.setText(String.valueOf(item.getDVDN()));
             String[] d_rdogrpVideo = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpVideo.length; i++)
             {
                 if (String.valueOf(item.getVideo()).equals(String.valueOf(d_rdogrpVideo[i])))
                 {
                     rb = (RadioButton)rdogrpVideo.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtVideoN.setText(String.valueOf(item.getVideoN()));
             String[] d_rdogrpComputer = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpComputer.length; i++)
             {
                 if (String.valueOf(item.getComputer()).equals(String.valueOf(d_rdogrpComputer[i])))
                 {
                     rb = (RadioButton)rdogrpComputer.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtComputerN.setText(String.valueOf(item.getComputerN()));
             String[] d_rdogrpLaptop = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpLaptop.length; i++)
             {
                 if (String.valueOf(item.getLaptop()).equals(String.valueOf(d_rdogrpLaptop[i])))
                 {
                     rb = (RadioButton)rdogrpLaptop.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtLaptopN.setText(String.valueOf(item.getLaptopN()));
             String[] d_rdogrpCable = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCable.length; i++)
             {
                 if (String.valueOf(item.getCable()).equals(String.valueOf(d_rdogrpCable[i])))
                 {
                     rb = (RadioButton)rdogrpCable.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtCableN.setText(String.valueOf(item.getCableN()));
             String[] d_rdogrpMicrowave = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpMicrowave.length; i++)
             {
                 if (String.valueOf(item.getMicrowave()).equals(String.valueOf(d_rdogrpMicrowave[i])))
                 {
                     rb = (RadioButton)rdogrpMicrowave.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtMicrowaveN.setText(String.valueOf(item.getMicrowaveN()));
             String[] d_rdogrpGeyser = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpGeyser.length; i++)
             {
                 if (String.valueOf(item.getGeyser()).equals(String.valueOf(d_rdogrpGeyser[i])))
                 {
                     rb = (RadioButton)rdogrpGeyser.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtGeyserN.setText(String.valueOf(item.getGeyserN()));
             String[] d_rdogrpGrill = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpGrill.length; i++)
             {
                 if (String.valueOf(item.getGrill()).equals(String.valueOf(d_rdogrpGrill[i])))
                 {
                     rb = (RadioButton)rdogrpGrill.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtGrillN.setText(String.valueOf(item.getGrillN()));
             String[] d_rdogrpGrain = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpGrain.length; i++)
             {
                 if (String.valueOf(item.getGrain()).equals(String.valueOf(d_rdogrpGrain[i])))
                 {
                     rb = (RadioButton)rdogrpGrain.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtGrainN.setText(String.valueOf(item.getGrainN()));
             String[] d_rdogrpRefrigerator = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpRefrigerator.length; i++)
             {
                 if (String.valueOf(item.getRefrigerator()).equals(String.valueOf(d_rdogrpRefrigerator[i])))
                 {
                     rb = (RadioButton)rdogrpRefrigerator.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtRefrigeratorN.setText(String.valueOf(item.getRefrigeratorN()));
             String[] d_rdogrpDeepFreezer = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpDeepFreezer.length; i++)
             {
                 if (String.valueOf(item.getDeepFreezer()).equals(String.valueOf(d_rdogrpDeepFreezer[i])))
                 {
                     rb = (RadioButton)rdogrpDeepFreezer.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtDeepFreezerN.setText(String.valueOf(item.getDeepFreezerN()));
             String[] d_rdogrpStove = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpStove.length; i++)
             {
                 if (String.valueOf(item.getStove()).equals(String.valueOf(d_rdogrpStove[i])))
                 {
                     rb = (RadioButton)rdogrpStove.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtStoveN.setText(String.valueOf(item.getStoveN()));
             String[] d_rdogrpGasHob = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpGasHob.length; i++)
             {
                 if (String.valueOf(item.getGasHob()).equals(String.valueOf(d_rdogrpGasHob[i])))
                 {
                     rb = (RadioButton)rdogrpGasHob.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtGasHobN.setText(String.valueOf(item.getGasHobN()));
             String[] d_rdogrpImpCooker = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpImpCooker.length; i++)
             {
                 if (String.valueOf(item.getImpCooker()).equals(String.valueOf(d_rdogrpImpCooker[i])))
                 {
                     rb = (RadioButton)rdogrpImpCooker.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtImpCookerN.setText(String.valueOf(item.getImpCookerN()));
             String[] d_rdogrpBike = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpBike.length; i++)
             {
                 if (String.valueOf(item.getBike()).equals(String.valueOf(d_rdogrpBike[i])))
                 {
                     rb = (RadioButton)rdogrpBike.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtBikeN.setText(String.valueOf(item.getBikeN()));
             String[] d_rdogrpMotorcycle = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpMotorcycle.length; i++)
             {
                 if (String.valueOf(item.getMotorcycle()).equals(String.valueOf(d_rdogrpMotorcycle[i])))
                 {
                     rb = (RadioButton)rdogrpMotorcycle.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtMotorcycleN.setText(String.valueOf(item.getMotorcycleN()));
             String[] d_rdogrpCar = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCar.length; i++)
             {
                 if (String.valueOf(item.getCar()).equals(String.valueOf(d_rdogrpCar[i])))
                 {
                     rb = (RadioButton)rdogrpCar.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtCarN.setText(String.valueOf(item.getCarN()));
             String[] d_rdogrpRickshaw = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpRickshaw.length; i++)
             {
                 if (String.valueOf(item.getRickshaw()).equals(String.valueOf(d_rdogrpRickshaw[i])))
                 {
                     rb = (RadioButton)rdogrpRickshaw.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtRickshawN.setText(String.valueOf(item.getRickshawN()));
             String[] d_rdogrpCart = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCart.length; i++)
             {
                 if (String.valueOf(item.getCart()).equals(String.valueOf(d_rdogrpCart[i])))
                 {
                     rb = (RadioButton)rdogrpCart.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtCartN.setText(String.valueOf(item.getCartN()));
             String[] d_rdogrpCanoe = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCanoe.length; i++)
             {
                 if (String.valueOf(item.getCanoe()).equals(String.valueOf(d_rdogrpCanoe[i])))
                 {
                     rb = (RadioButton)rdogrpCanoe.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtCanoeN.setText(String.valueOf(item.getCanoeN()));
             String[] d_rdogrpBus = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpBus.length; i++)
             {
                 if (String.valueOf(item.getBus()).equals(String.valueOf(d_rdogrpBus[i])))
                 {
                     rb = (RadioButton)rdogrpBus.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtBusN.setText(String.valueOf(item.getBusN()));
             String[] d_rdogrpTractor = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpTractor.length; i++)
             {
                 if (String.valueOf(item.getTractor()).equals(String.valueOf(d_rdogrpTractor[i])))
                 {
                     rb = (RadioButton)rdogrpTractor.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtTractorN.setText(String.valueOf(item.getTractorN()));
             String[] d_rdogrpPlow = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPlow.length; i++)
             {
                 if (String.valueOf(item.getPlow()).equals(String.valueOf(d_rdogrpPlow[i])))
                 {
                     rb = (RadioButton)rdogrpPlow.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtPlowN.setText(String.valueOf(item.getPlowN()));
             String[] d_rdogrpDuck = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpDuck.length; i++)
             {
                 if (String.valueOf(item.getDuck()).equals(String.valueOf(d_rdogrpDuck[i])))
                 {
                     rb = (RadioButton)rdogrpDuck.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtDuckN.setText(String.valueOf(item.getDuckN()));
             String[] d_rdogrpCow = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCow.length; i++)
             {
                 if (String.valueOf(item.getCow()).equals(String.valueOf(d_rdogrpCow[i])))
                 {
                     rb = (RadioButton)rdogrpCow.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtCowN.setText(String.valueOf(item.getCowN()));
             String[] d_rdogrpSheep = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpSheep.length; i++)
             {
                 if (String.valueOf(item.getSheep()).equals(String.valueOf(d_rdogrpSheep[i])))
                 {
                     rb = (RadioButton)rdogrpSheep.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtSheepN.setText(String.valueOf(item.getSheepN()));
             String[] d_rdogrpGoat = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpGoat.length; i++)
             {
                 if (String.valueOf(item.getGoat()).equals(String.valueOf(d_rdogrpGoat[i])))
                 {
                     rb = (RadioButton)rdogrpGoat.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtGoatN.setText(String.valueOf(item.getGoatN()));
             String[] d_rdogrpChicken = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpChicken.length; i++)
             {
                 if (String.valueOf(item.getChicken()).equals(String.valueOf(d_rdogrpChicken[i])))
                 {
                     rb = (RadioButton)rdogrpChicken.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtChickenN.setText(String.valueOf(item.getChickenN()));
             String[] d_rdogrpDonkey = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpDonkey.length; i++)
             {
                 if (String.valueOf(item.getDonkey()).equals(String.valueOf(d_rdogrpDonkey[i])))
                 {
                     rb = (RadioButton)rdogrpDonkey.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtDunkeyN.setText(String.valueOf(item.getDunkeyN()));
             String[] d_rdogrpHorse = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpHorse.length; i++)
             {
                 if (String.valueOf(item.getHorse()).equals(String.valueOf(d_rdogrpHorse[i])))
                 {
                     rb = (RadioButton)rdogrpHorse.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtHorseN.setText(String.valueOf(item.getHorseN()));
             String[] d_rdogrpPig = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpPig.length; i++)
             {
                 if (String.valueOf(item.getPig()).equals(String.valueOf(d_rdogrpPig[i])))
                 {
                     rb = (RadioButton)rdogrpPig.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtPigN.setText(String.valueOf(item.getPigN()));
             String[] d_rdogrpBirds = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpBirds.length; i++)
             {
                 if (String.valueOf(item.getBirds()).equals(String.valueOf(d_rdogrpBirds[i])))
                 {
                     rb = (RadioButton)rdogrpBirds.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtBirdsN.setText(String.valueOf(item.getBirdsN()));
             String[] d_rdogrpDogs = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpDogs.length; i++)
             {
                 if (String.valueOf(item.getDogs()).equals(String.valueOf(d_rdogrpDogs[i])))
                 {
                     rb = (RadioButton)rdogrpDogs.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtDogsN.setText(String.valueOf(item.getDogsN()));
             String[] d_rdogrpCats = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpCats.length; i++)
             {
                 if (String.valueOf(item.getCats()).equals(String.valueOf(d_rdogrpCats[i])))
                 {
                     rb = (RadioButton)rdogrpCats.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtCatsN.setText(String.valueOf(item.getCatsN()));
             String[] d_rdogrpFishNet = new String[] {"0","1"};
             for (int i = 0; i < d_rdogrpFishNet.length; i++)
             {
                 if (String.valueOf(item.getFishNet()).equals(String.valueOf(d_rdogrpFishNet[i])))
                 {
                     rb = (RadioButton)rdogrpFishNet.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtFishNetN.setText(String.valueOf(item.getFishNetN()));
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
             txtOtherAsset1N.setText(String.valueOf(item.getOtherAsset1N()));
             txtOtherAsset2.setText(item.getOtherAsset2());
             txtOtherAsset2N.setText(String.valueOf(item.getOtherAsset2N()));
             txtOtherAsset3.setText(item.getOtherAsset3());
             txtOtherAsset3N.setText(String.valueOf(item.getOtherAsset3N()));
             txtSESNote.setText(item.getSESNote());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Surv_SES_Mali.this, e.getMessage());
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

    private String SESNo(String HHID)
    {
       String M = C.ReturnSingleValue("Select cast(ifnull(max(SESNo),0)+1 as varchar(2))SESNo from tmpSES_Mali where HHID='"+ HHID +"'");
       return M;
    }
 
 // turning off the GPS if its in on state. to avoid the battery drain.
 @Override
 protected void onDestroy() {
     // TODO Auto-generated method stub
     super.onDestroy();
 }
}