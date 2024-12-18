
package forms_activity;


import android.annotation.SuppressLint;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import forms_datamodel.SES_CrossRiver_DataModel;
import Utility.*;
import Common.*;
import forms_datamodel.tmpSES_CrossRiver_DataModel;

import android.widget.Toast;
import org.icddrb.champs_lc_dss.R;
import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

public class Surv_SES_CrossRiver extends AppCompatActivity {
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
   TextView lblHHID;
   TextView VlblHHID;
   EditText txtHHID;
   LinearLayout secSESNo;
   View lineSESNo;
   TextView lblSESNo;
   TextView VlblSESNo;
   EditText txtSESNo;
   LinearLayout secSESVDate;
   View lineSESVDate;
   TextView lblSESVDate;
   TextView VlblSESVDate;
   EditText txtSESVDate;
   LinearLayout secSESVStatus;
   View lineSESVStatus;
   TextView lblSESVStatus;
   TextView VlblSESVStatus;
   RadioGroup rdogrpSESVStatus;
   RadioButton rdoSESVStatus1;
   RadioButton rdoSESVStatus2;
   RadioButton rdoSESVStatus3;
   RadioButton rdoSESVStatus4;
   LinearLayout secSESVStatusOth;
   View lineSESVStatusOth;
   TextView lblSESVStatusOth;
   TextView VlblSESVStatusOth;
   EditText txtSESVStatusOth;
   LinearLayout secRnd;
   View lineRnd;
   TextView lblRnd;
   TextView VlblRnd;
   EditText txtRnd;
   LinearLayout secWSDrink;
   View lineWSDrink;
   TextView lblWSDrink;
   TextView VlblWSDrink;
   Spinner spnWSDrink;
   LinearLayout secWSDrinkOth;
   View lineWSDrinkOth;
   TextView lblWSDrinkOth;
   TextView VlblWSDrinkOth;
   AutoCompleteTextView txtWSDrinkOth;
   LinearLayout secMainWater;
   View lineMainWater;
   TextView lblMainWater;
   TextView VlblMainWater;
   Spinner spnMainWater;
   LinearLayout secMainWaterOth;
   View lineMainWaterOth;
   TextView lblMainWaterOth;
   TextView VlblMainWaterOth;
   AutoCompleteTextView txtMainWaterOth;
   LinearLayout secToilet;
   View lineToilet;
   TextView lblToilet;
   TextView VlblToilet;
   Spinner spnToilet;
   LinearLayout secToiletOth;
   View lineToiletOth;
   TextView lblToiletOth;
   TextView VlblToiletOth;
   AutoCompleteTextView txtToiletOth;
   LinearLayout secToiletShrd;
   View lineToiletShrd;
   TextView lblToiletShrd;
   TextView VlblToiletShrd;
   RadioGroup rdogrpToiletShrd;
   RadioButton rdoToiletShrd1;
   RadioButton rdoToiletShrd2;
   RadioButton rdoToiletShrd3;
   LinearLayout secToiletUseNo;
   View lineToiletUseNo;
   TextView lblToiletUseNo;
   TextView VlblToiletUseNo;
   EditText txtToiletUseNo;
   LinearLayout secToiletUseNoDk;
   View lineToiletUseNoDk;
   TextView lblToiletUseNoDk;
   TextView VlblToiletUseNoDk;
   RadioGroup rdogrpToiletUseNoDk;
   RadioButton rdoToiletUseNoDk1;
   RadioButton rdoToiletUseNoDk2;
   RadioButton rdoToiletUseNoDk3;
   LinearLayout secToiletLoc;
   View lineToiletLoc;
   TextView lblToiletLoc;
   TextView VlblToiletLoc;
   RadioGroup rdogrpToiletLoc;
   RadioButton rdoToiletLoc1;
   RadioButton rdoToiletLoc2;
   RadioButton rdoToiletLoc3;
   RadioButton rdoToiletLoc4;
   LinearLayout secHandWash;
   View lineHandWash;
   TextView lblHandWash;
   TextView VlblHandWash;
   RadioGroup rdogrpHandWash;
   RadioButton rdoHandWash1;
   RadioButton rdoHandWash2;
   RadioButton rdoHandWash3;
   LinearLayout secShowWash;
   View lineShowWash;
   TextView lblShowWash;
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
   TextView lblAvailableWat;
   TextView VlblAvailableWat;
   RadioGroup rdogrpAvailableWat;
   RadioButton rdoAvailableWat1;
   RadioButton rdoAvailableWat2;
   LinearLayout secAvailableSoap;
   View lineAvailableSoap;
   TextView lblAvailableSoap;
   TextView VlblAvailableSoap;
   RadioGroup rdogrpAvailableSoap;
   RadioButton rdoAvailableSoap1;
   RadioButton rdoAvailableSoap2;
   LinearLayout secHandWashOthMem;
   View lineHandWashOthMem;
   TextView lblHandWashOthMem;
   TextView VlblHandWashOthMem;
   Spinner spnHandWashOthMem;
   LinearLayout secHandWashOthMemO;
   View lineHandWashOthMemO;
   TextView lblHandWashOthMemO;
   TextView VlblHandWashOthMemO;
   AutoCompleteTextView txtHandWashOthMemO;
   LinearLayout secSoapInHouse;
   View lineSoapInHouse;
   TextView lblSoapInHouse;
   TextView VlblSoapInHouse;
   RadioGroup rdogrpSoapInHouse;
   RadioButton rdoSoapInHouse1;
   RadioButton rdoSoapInHouse2;
   LinearLayout secSoapInHouseShow;
   View lineSoapInHouseShow;
   TextView lblSoapInHouseShow;
   TextView VlblSoapInHouseShow;
   RadioGroup rdogrpSoapInHouseShow;
   RadioButton rdoSoapInHouseShow1;
   RadioButton rdoSoapInHouseShow2;
   LinearLayout seclblSoapInHouseObj;
   View linelblSoapInHouseObj;
   LinearLayout secSoapInHouseObjA;
   View lineSoapInHouseObjA;
   TextView lblSoapInHouseObjA;
   TextView VlblSoapInHouseObjA;
   CheckBox chkSoapInHouseObjA;
   LinearLayout secSoapInHouseObjB;
   View lineSoapInHouseObjB;
   TextView lblSoapInHouseObjB;
   TextView VlblSoapInHouseObjB;
   CheckBox chkSoapInHouseObjB;
   LinearLayout secSoapInHouseObjC;
   View lineSoapInHouseObjC;
   TextView lblSoapInHouseObjC;
   TextView VlblSoapInHouseObjC;
   CheckBox chkSoapInHouseObjC;
   LinearLayout secCookDvc;
   View lineCookDvc;
   TextView lblCookDvc;
   TextView VlblCookDvc;
   Spinner spnCookDvc;
   LinearLayout secCookDvcOth;
   View lineCookDvcOth;
   TextView lblCookDvcOth;
   TextView VlblCookDvcOth;
   AutoCompleteTextView txtCookDvcOth;
   LinearLayout secCookFuel;
   View lineCookFuel;
   TextView lblCookFuel;
   TextView VlblCookFuel;
   Spinner spnCookFuel;
   LinearLayout secCookFuelOth;
   View lineCookFuelOth;
   TextView lblCookFuelOth;
   TextView VlblCookFuelOth;
   AutoCompleteTextView txtCookFuelOth;
   LinearLayout secCookPlc;
   View lineCookPlc;
   TextView lblCookPlc;
   TextView VlblCookPlc;
   Spinner spnCookPlc;
   LinearLayout secCookPlcOth;
   View lineCookPlcOth;
   TextView lblCookPlcOth;
   TextView VlblCookPlcOth;
   AutoCompleteTextView txtCookPlcOth;
   LinearLayout secFloor;
   View lineFloor;
   TextView lblFloor;
   TextView VlblFloor;
   Spinner spnFloor;
   LinearLayout secFloorOth;
   View lineFloorOth;
   TextView lblFloorOth;
   TextView VlblFloorOth;
   AutoCompleteTextView txtFloorOth;
   LinearLayout secRoof;
   View lineRoof;
   TextView lblRoof;
   TextView VlblRoof;
   Spinner spnRoof;
   LinearLayout secRoofOth;
   View lineRoofOth;
   TextView lblRoofOth;
   TextView VlblRoofOth;
   AutoCompleteTextView txtRoofOth;
   LinearLayout secWall;
   View lineWall;
   TextView lblWall;
   TextView VlblWall;
   Spinner spnWall;
   LinearLayout secWallOth;
   View lineWallOth;
   TextView lblWallOth;
   TextView VlblWallOth;
   AutoCompleteTextView txtWallOth;
   LinearLayout secRoomSleep;
   View lineRoomSleep;
   TextView lblRoomSleep;
   TextView VlblRoomSleep;
   EditText txtRoomSleep;
   LinearLayout secRoomSleepDk;
   View lineRoomSleepDk;
   TextView lblRoomSleepDk;
   TextView VlblRoomSleepDk;
   RadioGroup rdogrpRoomSleepDk;
   RadioButton rdoRoomSleepDk1;
   RadioButton rdoRoomSleepDk2;
   LinearLayout secHomesteadAny;
   View lineHomesteadAny;
   TextView lblHomesteadAny;
   TextView VlblHomesteadAny;
   RadioGroup rdogrpHomesteadAny;
   RadioButton rdoHomesteadAny1;
   RadioButton rdoHomesteadAny2;
   RadioButton rdoHomesteadAny3;
   RadioButton rdoHomesteadAny4;
   LinearLayout secOthLand;
   View lineOthLand;
   TextView lblOthLand;
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
   TextView lblElectricity;
   TextView VlblElectricity;
   RadioGroup rdogrpElectricity;
   RadioButton rdoElectricity1;
   RadioButton rdoElectricity2;
   LinearLayout secHeater;
   View lineHeater;
   TextView lblHeater;
   TextView VlblHeater;
   RadioGroup rdogrpHeater;
   RadioButton rdoHeater1;
   RadioButton rdoHeater2;
   LinearLayout secAC;
   View lineAC;
   TextView lblAC;
   TextView VlblAC;
   RadioGroup rdogrpAC;
   RadioButton rdoAC1;
   RadioButton rdoAC2;
   LinearLayout secElecFan;
   View lineElecFan;
   TextView lblElecFan;
   TextView VlblElecFan;
   RadioGroup rdogrpElecFan;
   RadioButton rdoElecFan1;
   RadioButton rdoElecFan2;
   LinearLayout secLantern;
   View lineLantern;
   TextView lblLantern;
   TextView VlblLantern;
   RadioGroup rdogrpLantern;
   RadioButton rdoLantern1;
   RadioButton rdoLantern2;
   LinearLayout secLamp;
   View lineLamp;
   TextView lblLamp;
   TextView VlblLamp;
   RadioGroup rdogrpLamp;
   RadioButton rdoLamp1;
   RadioButton rdoLamp2;
   LinearLayout secGasLamp;
   View lineGasLamp;
   TextView lblGasLamp;
   TextView VlblGasLamp;
   RadioGroup rdogrpGasLamp;
   RadioButton rdoGasLamp1;
   RadioButton rdoGasLamp2;
   LinearLayout secPetroleum;
   View linePetroleum;
   TextView lblPetroleum;
   TextView VlblPetroleum;
   RadioGroup rdogrpPetroleum;
   RadioButton rdoPetroleum1;
   RadioButton rdoPetroleum2;
   LinearLayout secMatt;
   View lineMatt;
   TextView lblMatt;
   TextView VlblMatt;
   RadioGroup rdogrpMatt;
   RadioButton rdoMatt1;
   RadioButton rdoMatt2;
   LinearLayout secMats;
   View lineMats;
   TextView lblMats;
   TextView VlblMats;
   RadioGroup rdogrpMats;
   RadioButton rdoMats1;
   RadioButton rdoMats2;
   LinearLayout secCarpets;
   View lineCarpets;
   TextView lblCarpets;
   TextView VlblCarpets;
   RadioGroup rdogrpCarpets;
   RadioButton rdoCarpets1;
   RadioButton rdoCarpets2;
   LinearLayout secBed;
   View lineBed;
   TextView lblBed;
   TextView VlblBed;
   RadioGroup rdogrpBed;
   RadioButton rdoBed1;
   RadioButton rdoBed2;
   LinearLayout secChair;
   View lineChair;
   TextView lblChair;
   TextView VlblChair;
   RadioGroup rdogrpChair;
   RadioButton rdoChair1;
   RadioButton rdoChair2;
   LinearLayout secSofa;
   View lineSofa;
   TextView lblSofa;
   TextView VlblSofa;
   RadioGroup rdogrpSofa;
   RadioButton rdoSofa1;
   RadioButton rdoSofa2;
   LinearLayout secTables;
   View lineTables;
   TextView lblTables;
   TextView VlblTables;
   RadioGroup rdogrpTables;
   RadioButton rdoTables1;
   RadioButton rdoTables2;
   LinearLayout secWatch;
   View lineWatch;
   TextView lblWatch;
   TextView VlblWatch;
   RadioGroup rdogrpWatch;
   RadioButton rdoWatch1;
   RadioButton rdoWatch2;
   LinearLayout secWMachine;
   View lineWMachine;
   TextView lblWMachine;
   TextView VlblWMachine;
   RadioGroup rdogrpWMachine;
   RadioButton rdoWMachine1;
   RadioButton rdoWMachine2;
   LinearLayout secIron;
   View lineIron;
   TextView lblIron;
   TextView VlblIron;
   RadioGroup rdogrpIron;
   RadioButton rdoIron1;
   RadioButton rdoIron2;
   LinearLayout secBooth;
   View lineBooth;
   TextView lblBooth;
   TextView VlblBooth;
   RadioGroup rdogrpBooth;
   RadioButton rdoBooth1;
   RadioButton rdoBooth2;
   LinearLayout secSMachine;
   View lineSMachine;
   TextView lblSMachine;
   TextView VlblSMachine;
   RadioGroup rdogrpSMachine;
   RadioButton rdoSMachine1;
   RadioButton rdoSMachine2;
   LinearLayout secGenerator;
   View lineGenerator;
   TextView lblGenerator;
   TextView VlblGenerator;
   RadioGroup rdogrpGenerator;
   RadioButton rdoGenerator1;
   RadioButton rdoGenerator2;
   LinearLayout seclblH12a;
   View linelblH12a;
   LinearLayout secInternet;
   View lineInternet;
   TextView lblInternet;
   TextView VlblInternet;
   RadioGroup rdogrpInternet;
   RadioButton rdoInternet1;
   RadioButton rdoInternet2;
   LinearLayout secSatellite;
   View lineSatellite;
   TextView lblSatellite;
   TextView VlblSatellite;
   RadioGroup rdogrpSatellite;
   RadioButton rdoSatellite1;
   RadioButton rdoSatellite2;
   LinearLayout secLandline;
   View lineLandline;
   TextView lblLandline;
   TextView VlblLandline;
   RadioGroup rdogrpLandline;
   RadioButton rdoLandline1;
   RadioButton rdoLandline2;
   LinearLayout secCellphone;
   View lineCellphone;
   TextView lblCellphone;
   TextView VlblCellphone;
   RadioGroup rdogrpCellphone;
   RadioButton rdoCellphone1;
   RadioButton rdoCellphone2;
   LinearLayout secTV;
   View lineTV;
   TextView lblTV;
   TextView VlblTV;
   RadioGroup rdogrpTV;
   RadioButton rdoTV1;
   RadioButton rdoTV2;
   LinearLayout secTV5;
   View lineTV5;
   TextView lblTV5;
   TextView VlblTV5;
   RadioGroup rdogrpTV5;
   RadioButton rdoTV51;
   RadioButton rdoTV52;
   LinearLayout secChannel;
   View lineChannel;
   TextView lblChannel;
   TextView VlblChannel;
   RadioGroup rdogrpChannel;
   RadioButton rdoChannel1;
   RadioButton rdoChannel2;
   LinearLayout secRadio;
   View lineRadio;
   TextView lblRadio;
   TextView VlblRadio;
   RadioGroup rdogrpRadio;
   RadioButton rdoRadio1;
   RadioButton rdoRadio2;
   LinearLayout secDVD;
   View lineDVD;
   TextView lblDVD;
   TextView VlblDVD;
   RadioGroup rdogrpDVD;
   RadioButton rdoDVD1;
   RadioButton rdoDVD2;
   LinearLayout secVideo;
   View lineVideo;
   TextView lblVideo;
   TextView VlblVideo;
   RadioGroup rdogrpVideo;
   RadioButton rdoVideo1;
   RadioButton rdoVideo2;
   LinearLayout secComputer;
   View lineComputer;
   TextView lblComputer;
   TextView VlblComputer;
   RadioGroup rdogrpComputer;
   RadioButton rdoComputer1;
   RadioButton rdoComputer2;
   LinearLayout secLaptop;
   View lineLaptop;
   TextView lblLaptop;
   TextView VlblLaptop;
   RadioGroup rdogrpLaptop;
   RadioButton rdoLaptop1;
   RadioButton rdoLaptop2;
   LinearLayout secCable;
   View lineCable;
   TextView lblCable;
   TextView VlblCable;
   RadioGroup rdogrpCable;
   RadioButton rdoCable1;
   RadioButton rdoCable2;
   LinearLayout seclblH12b;
   View linelblH12b;
   LinearLayout secMicrowave;
   View lineMicrowave;
   TextView lblMicrowave;
   TextView VlblMicrowave;
   RadioGroup rdogrpMicrowave;
   RadioButton rdoMicrowave1;
   RadioButton rdoMicrowave2;
   LinearLayout secGeyser;
   View lineGeyser;
   TextView lblGeyser;
   TextView VlblGeyser;
   RadioGroup rdogrpGeyser;
   RadioButton rdoGeyser1;
   RadioButton rdoGeyser2;
   LinearLayout secGrill;
   View lineGrill;
   TextView lblGrill;
   TextView VlblGrill;
   RadioGroup rdogrpGrill;
   RadioButton rdoGrill1;
   RadioButton rdoGrill2;
   LinearLayout secGrain;
   View lineGrain;
   TextView lblGrain;
   TextView VlblGrain;
   RadioGroup rdogrpGrain;
   RadioButton rdoGrain1;
   RadioButton rdoGrain2;
   LinearLayout secRefrigerator;
   View lineRefrigerator;
   TextView lblRefrigerator;
   TextView VlblRefrigerator;
   RadioGroup rdogrpRefrigerator;
   RadioButton rdoRefrigerator1;
   RadioButton rdoRefrigerator2;
   LinearLayout secDeepFreezer;
   View lineDeepFreezer;
   TextView lblDeepFreezer;
   TextView VlblDeepFreezer;
   RadioGroup rdogrpDeepFreezer;
   RadioButton rdoDeepFreezer1;
   RadioButton rdoDeepFreezer2;
   LinearLayout secStove;
   View lineStove;
   TextView lblStove;
   TextView VlblStove;
   RadioGroup rdogrpStove;
   RadioButton rdoStove1;
   RadioButton rdoStove2;
   LinearLayout secGasHob;
   View lineGasHob;
   TextView lblGasHob;
   TextView VlblGasHob;
   RadioGroup rdogrpGasHob;
   RadioButton rdoGasHob1;
   RadioButton rdoGasHob2;
   LinearLayout secImpCooker;
   View lineImpCooker;
   TextView lblImpCooker;
   TextView VlblImpCooker;
   RadioGroup rdogrpImpCooker;
   RadioButton rdoImpCooker1;
   RadioButton rdoImpCooker2;
   LinearLayout seclblH12c;
   View linelblH12c;
   LinearLayout secBike;
   View lineBike;
   TextView lblBike;
   TextView VlblBike;
   RadioGroup rdogrpBike;
   RadioButton rdoBike1;
   RadioButton rdoBike2;
   LinearLayout secMotorcycle;
   View lineMotorcycle;
   TextView lblMotorcycle;
   TextView VlblMotorcycle;
   RadioGroup rdogrpMotorcycle;
   RadioButton rdoMotorcycle1;
   RadioButton rdoMotorcycle2;
   LinearLayout secCar;
   View lineCar;
   TextView lblCar;
   TextView VlblCar;
   RadioGroup rdogrpCar;
   RadioButton rdoCar1;
   RadioButton rdoCar2;
   LinearLayout secRickshaw;
   View lineRickshaw;
   TextView lblRickshaw;
   TextView VlblRickshaw;
   RadioGroup rdogrpRickshaw;
   RadioButton rdoRickshaw1;
   RadioButton rdoRickshaw2;
   LinearLayout secCart;
   View lineCart;
   TextView lblCart;
   TextView VlblCart;
   RadioGroup rdogrpCart;
   RadioButton rdoCart1;
   RadioButton rdoCart2;
   LinearLayout secCanoe;
   View lineCanoe;
   TextView lblCanoe;
   TextView VlblCanoe;
   RadioGroup rdogrpCanoe;
   RadioButton rdoCanoe1;
   RadioButton rdoCanoe2;
   LinearLayout secBus;
   View lineBus;
   TextView lblBus;
   TextView VlblBus;
   RadioGroup rdogrpBus;
   RadioButton rdoBus1;
   RadioButton rdoBus2;
   LinearLayout seclblH12d;
   View linelblH12d;
   LinearLayout secTractor;
   View lineTractor;
   TextView lblTractor;
   TextView VlblTractor;
   RadioGroup rdogrpTractor;
   RadioButton rdoTractor1;
   RadioButton rdoTractor2;
   LinearLayout secPlow;
   View linePlow;
   TextView lblPlow;
   TextView VlblPlow;
   RadioGroup rdogrpPlow;
   RadioButton rdoPlow1;
   RadioButton rdoPlow2;
   LinearLayout secDuck;
   View lineDuck;
   TextView lblDuck;
   TextView VlblDuck;
   RadioGroup rdogrpDuck;
   RadioButton rdoDuck1;
   RadioButton rdoDuck2;
   LinearLayout secCow;
   View lineCow;
   TextView lblCow;
   TextView VlblCow;
   RadioGroup rdogrpCow;
   RadioButton rdoCow1;
   RadioButton rdoCow2;
   LinearLayout secSheep;
   View lineSheep;
   TextView lblSheep;
   TextView VlblSheep;
   RadioGroup rdogrpSheep;
   RadioButton rdoSheep1;
   RadioButton rdoSheep2;
   LinearLayout secGoat;
   View lineGoat;
   TextView lblGoat;
   TextView VlblGoat;
   RadioGroup rdogrpGoat;
   RadioButton rdoGoat1;
   RadioButton rdoGoat2;
   LinearLayout secChicken;
   View lineChicken;
   TextView lblChicken;
   TextView VlblChicken;
   RadioGroup rdogrpChicken;
   RadioButton rdoChicken1;
   RadioButton rdoChicken2;
   LinearLayout secDonkey;
   View lineDonkey;
   TextView lblDonkey;
   TextView VlblDonkey;
   RadioGroup rdogrpDonkey;
   RadioButton rdoDonkey1;
   RadioButton rdoDonkey2;
   LinearLayout secHorse;
   View lineHorse;
   TextView lblHorse;
   TextView VlblHorse;
   RadioGroup rdogrpHorse;
   RadioButton rdoHorse1;
   RadioButton rdoHorse2;
   LinearLayout secPig;
   View linePig;
   TextView lblPig;
   TextView VlblPig;
   RadioGroup rdogrpPig;
   RadioButton rdoPig1;
   RadioButton rdoPig2;
   LinearLayout secBirds;
   View lineBirds;
   TextView lblBirds;
   TextView VlblBirds;
   RadioGroup rdogrpBirds;
   RadioButton rdoBirds1;
   RadioButton rdoBirds2;
   LinearLayout secDogs;
   View lineDogs;
   TextView lblDogs;
   TextView VlblDogs;
   RadioGroup rdogrpDogs;
   RadioButton rdoDogs1;
   RadioButton rdoDogs2;
   LinearLayout secCats;
   View lineCats;
   TextView lblCats;
   TextView VlblCats;
   RadioGroup rdogrpCats;
   RadioButton rdoCats1;
   RadioButton rdoCats2;
   LinearLayout secFishNet;
   View lineFishNet;
   TextView lblFishNet;
   TextView VlblFishNet;
   RadioGroup rdogrpFishNet;
   RadioButton rdoFishNet1;
   RadioButton rdoFishNet2;
   LinearLayout secOtherAsset;
   View lineOtherAsset;
   TextView lblOtherAsset;
   TextView VlblOtherAsset;
   RadioGroup rdogrpOtherAsset;
   RadioButton rdoOtherAsset1;
   RadioButton rdoOtherAsset2;
   LinearLayout secOtherAsset1;
   View lineOtherAsset1;
   TextView lblOtherAsset1;
   TextView VlblOtherAsset1;
   AutoCompleteTextView txtOtherAsset1;
   LinearLayout secOtherAsset2;
   View lineOtherAsset2;
   TextView lblOtherAsset2;
   TextView VlblOtherAsset2;
   AutoCompleteTextView txtOtherAsset2;
   LinearLayout secOtherAsset3;
   View lineOtherAsset3;
   TextView lblOtherAsset3;
   TextView VlblOtherAsset3;
   AutoCompleteTextView txtOtherAsset3;
   LinearLayout secSESNote;
   View lineSESNote;
   TextView lblSESNote;
   TextView VlblSESNote;
   EditText txtSESNote;

   LinearLayout secTricycle;
   TextView lblTricycle;
   TextView VlblTricycle;
   RadioGroup rdogrpTricycle;
   RadioButton rdoTricycle1;
   RadioButton rdoTricycle2;
   View lineTricycle;
   LinearLayout secBoatWithMot;
   TextView lblBoatWithMot;
   TextView VlblBoatWithMot;
   RadioGroup rdogrpBoatWithMot;
   RadioButton rdoBoatWithMot1;
   RadioButton rdoBoatWithMot2;
   View lineBoatWithMot;

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

   @SuppressLint("ClickableViewAccessibility")
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      try
      {
         setContentView(R.layout.ses_crossriver);
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

         TableName = "tmpSES_CrossRiver";
         MODULEID = 54;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               AlertDialog.Builder adb = new AlertDialog.Builder(Surv_SES_CrossRiver.this);
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
         Connection.LocalizeLanguage(Surv_SES_CrossRiver.this, MODULEID, LANGUAGEID);


         txtSESVDate.setOnTouchListener(new View.OnTouchListener() {
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
         secMainWater.setVisibility(View.GONE);
         lineMainWater.setVisibility(View.GONE);
         secMainWaterOth.setVisibility(View.GONE);
         lineMainWaterOth.setVisibility(View.GONE);
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
         secHandWash.setVisibility(View.GONE);
         lineHandWash.setVisibility(View.GONE);
         secShowWash.setVisibility(View.GONE);
         lineShowWash.setVisibility(View.GONE);
         secAvailableWat.setVisibility(View.GONE);
         lineAvailableWat.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
         secHandWashOthMem.setVisibility(View.GONE);
         lineHandWashOthMem.setVisibility(View.GONE);
         secHandWashOthMemO.setVisibility(View.GONE);
         lineHandWashOthMemO.setVisibility(View.GONE);
         secSoapInHouse.setVisibility(View.GONE);
         lineSoapInHouse.setVisibility(View.GONE);
         secSoapInHouseShow.setVisibility(View.GONE);
         lineSoapInHouseShow.setVisibility(View.GONE);
         seclblSoapInHouseObj.setVisibility(View.GONE);
         linelblSoapInHouseObj.setVisibility(View.GONE);
         secSoapInHouseObjA.setVisibility(View.GONE);
         lineSoapInHouseObjA.setVisibility(View.GONE);
         secSoapInHouseObjB.setVisibility(View.GONE);
         lineSoapInHouseObjB.setVisibility(View.GONE);
         secSoapInHouseObjC.setVisibility(View.GONE);
         lineSoapInHouseObjC.setVisibility(View.GONE);
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
         secGasLamp.setVisibility(View.GONE);
         lineGasLamp.setVisibility(View.GONE);
         secPetroleum.setVisibility(View.GONE);
         linePetroleum.setVisibility(View.GONE);
         secMatt.setVisibility(View.GONE);
         lineMatt.setVisibility(View.GONE);
         secMats.setVisibility(View.GONE);
         lineMats.setVisibility(View.GONE);
         secCarpets.setVisibility(View.GONE);
         lineCarpets.setVisibility(View.GONE);
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
         secTV5.setVisibility(View.GONE);
         lineTV5.setVisibility(View.GONE);
         secChannel.setVisibility(View.GONE);
         lineChannel.setVisibility(View.GONE);
         secRadio.setVisibility(View.GONE);
         lineRadio.setVisibility(View.GONE);
         secDVD.setVisibility(View.GONE);
         lineDVD.setVisibility(View.GONE);
         secVideo.setVisibility(View.GONE);
         lineVideo.setVisibility(View.GONE);
         secComputer.setVisibility(View.GONE);
         lineComputer.setVisibility(View.GONE);
         secLaptop.setVisibility(View.GONE);
         lineLaptop.setVisibility(View.GONE);
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
         secGrain.setVisibility(View.GONE);
         lineGrain.setVisibility(View.GONE);
         secRefrigerator.setVisibility(View.GONE);
         lineRefrigerator.setVisibility(View.GONE);
         secDeepFreezer.setVisibility(View.GONE);
         lineDeepFreezer.setVisibility(View.GONE);
         secStove.setVisibility(View.GONE);
         lineStove.setVisibility(View.GONE);
         secGasHob.setVisibility(View.GONE);
         lineGasHob.setVisibility(View.GONE);
         secImpCooker.setVisibility(View.GONE);
         lineImpCooker.setVisibility(View.GONE);
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
         secBirds.setVisibility(View.GONE);
         lineBirds.setVisibility(View.GONE);
         secDogs.setVisibility(View.GONE);
         lineDogs.setVisibility(View.GONE);
         secCats.setVisibility(View.GONE);
         lineCats.setVisibility(View.GONE);
         secFishNet.setVisibility(View.GONE);
         lineFishNet.setVisibility(View.GONE);
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
         secWSDrink.setVisibility(View.GONE);
         lineWSDrink.setVisibility(View.GONE);
         secWSDrinkOth.setVisibility(View.GONE);
         lineWSDrinkOth.setVisibility(View.GONE);
         secMainWater.setVisibility(View.GONE);
         lineMainWater.setVisibility(View.GONE);
         secMainWaterOth.setVisibility(View.GONE);
         lineMainWaterOth.setVisibility(View.GONE);
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
         secHandWash.setVisibility(View.GONE);
         lineHandWash.setVisibility(View.GONE);
         secShowWash.setVisibility(View.GONE);
         lineShowWash.setVisibility(View.GONE);
         secAvailableWat.setVisibility(View.GONE);
         lineAvailableWat.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
         secHandWashOthMem.setVisibility(View.GONE);
         lineHandWashOthMem.setVisibility(View.GONE);
         secHandWashOthMemO.setVisibility(View.GONE);
         lineHandWashOthMemO.setVisibility(View.GONE);
         secSoapInHouse.setVisibility(View.GONE);
         lineSoapInHouse.setVisibility(View.GONE);
         secSoapInHouseShow.setVisibility(View.GONE);
         lineSoapInHouseShow.setVisibility(View.GONE);
         seclblSoapInHouseObj.setVisibility(View.GONE);
         linelblSoapInHouseObj.setVisibility(View.GONE);
         secSoapInHouseObjA.setVisibility(View.GONE);
         lineSoapInHouseObjA.setVisibility(View.GONE);
         secSoapInHouseObjB.setVisibility(View.GONE);
         lineSoapInHouseObjB.setVisibility(View.GONE);
         secSoapInHouseObjC.setVisibility(View.GONE);
         lineSoapInHouseObjC.setVisibility(View.GONE);
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
         secGasLamp.setVisibility(View.GONE);
         lineGasLamp.setVisibility(View.GONE);
         secPetroleum.setVisibility(View.GONE);
         linePetroleum.setVisibility(View.GONE);
         secMatt.setVisibility(View.GONE);
         lineMatt.setVisibility(View.GONE);
         secMats.setVisibility(View.GONE);
         lineMats.setVisibility(View.GONE);
         secCarpets.setVisibility(View.GONE);
         lineCarpets.setVisibility(View.GONE);
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
         secTV5.setVisibility(View.GONE);
         lineTV5.setVisibility(View.GONE);
         secChannel.setVisibility(View.GONE);
         lineChannel.setVisibility(View.GONE);
         secRadio.setVisibility(View.GONE);
         lineRadio.setVisibility(View.GONE);
         secDVD.setVisibility(View.GONE);
         lineDVD.setVisibility(View.GONE);
         secVideo.setVisibility(View.GONE);
         lineVideo.setVisibility(View.GONE);
         secComputer.setVisibility(View.GONE);
         lineComputer.setVisibility(View.GONE);
         secLaptop.setVisibility(View.GONE);
         lineLaptop.setVisibility(View.GONE);
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
         secGrain.setVisibility(View.GONE);
         lineGrain.setVisibility(View.GONE);
         secRefrigerator.setVisibility(View.GONE);
         lineRefrigerator.setVisibility(View.GONE);
         secDeepFreezer.setVisibility(View.GONE);
         lineDeepFreezer.setVisibility(View.GONE);
         secStove.setVisibility(View.GONE);
         lineStove.setVisibility(View.GONE);
         secGasHob.setVisibility(View.GONE);
         lineGasHob.setVisibility(View.GONE);
         secImpCooker.setVisibility(View.GONE);
         lineImpCooker.setVisibility(View.GONE);
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
         secBirds.setVisibility(View.GONE);
         lineBirds.setVisibility(View.GONE);
         secDogs.setVisibility(View.GONE);
         lineDogs.setVisibility(View.GONE);
         secCats.setVisibility(View.GONE);
         lineCats.setVisibility(View.GONE);
         secFishNet.setVisibility(View.GONE);
         lineFishNet.setVisibility(View.GONE);
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
         secWSDrinkOth.setVisibility(View.GONE);
         lineWSDrinkOth.setVisibility(View.GONE);
         secMainWater.setVisibility(View.GONE);
         lineMainWater.setVisibility(View.GONE);
         secMainWaterOth.setVisibility(View.GONE);
         lineMainWaterOth.setVisibility(View.GONE);
         secMainWaterOth.setVisibility(View.GONE);
         lineMainWaterOth.setVisibility(View.GONE);
         secToiletOth.setVisibility(View.GONE);
         lineToiletOth.setVisibility(View.GONE);
         secToiletUseNo.setVisibility(View.GONE);
         lineToiletUseNo.setVisibility(View.GONE);
         secToiletUseNoDk.setVisibility(View.GONE);
         lineToiletUseNoDk.setVisibility(View.GONE);
         secShowWash.setVisibility(View.GONE);
         lineShowWash.setVisibility(View.GONE);
         secAvailableWat.setVisibility(View.GONE);
         lineAvailableWat.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
         secHandWashOthMem.setVisibility(View.GONE);
         lineHandWashOthMem.setVisibility(View.GONE);
         secHandWashOthMemO.setVisibility(View.GONE);
         lineHandWashOthMemO.setVisibility(View.GONE);
         secSoapInHouse.setVisibility(View.GONE);
         lineSoapInHouse.setVisibility(View.GONE);
         secSoapInHouseShow.setVisibility(View.GONE);
         lineSoapInHouseShow.setVisibility(View.GONE);
         seclblSoapInHouseObj.setVisibility(View.GONE);
         linelblSoapInHouseObj.setVisibility(View.GONE);
         secSoapInHouseObjA.setVisibility(View.GONE);
         lineSoapInHouseObjA.setVisibility(View.GONE);
         secSoapInHouseObjB.setVisibility(View.GONE);
         lineSoapInHouseObjB.setVisibility(View.GONE);
         secSoapInHouseObjC.setVisibility(View.GONE);
         lineSoapInHouseObjC.setVisibility(View.GONE);
         secShowWash.setVisibility(View.GONE);
         lineShowWash.setVisibility(View.GONE);
         secAvailableWat.setVisibility(View.GONE);
         lineAvailableWat.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
         secHandWashOthMem.setVisibility(View.GONE);
         lineHandWashOthMem.setVisibility(View.GONE);
         secHandWashOthMemO.setVisibility(View.GONE);
         lineHandWashOthMemO.setVisibility(View.GONE);
         secSoapInHouse.setVisibility(View.GONE);
         lineSoapInHouse.setVisibility(View.GONE);
         secSoapInHouseShow.setVisibility(View.GONE);
         lineSoapInHouseShow.setVisibility(View.GONE);
         seclblSoapInHouseObj.setVisibility(View.GONE);
         linelblSoapInHouseObj.setVisibility(View.GONE);
         secSoapInHouseObjA.setVisibility(View.GONE);
         lineSoapInHouseObjA.setVisibility(View.GONE);
         secSoapInHouseObjB.setVisibility(View.GONE);
         lineSoapInHouseObjB.setVisibility(View.GONE);
         secSoapInHouseObjC.setVisibility(View.GONE);
         lineSoapInHouseObjC.setVisibility(View.GONE);
         secAvailableWat.setVisibility(View.GONE);
         lineAvailableWat.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
         secHandWashOthMem.setVisibility(View.GONE);
         lineHandWashOthMem.setVisibility(View.GONE);
         secHandWashOthMemO.setVisibility(View.GONE);
         lineHandWashOthMemO.setVisibility(View.GONE);
         secAvailableWat.setVisibility(View.GONE);
         lineAvailableWat.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
         secAvailableWat.setVisibility(View.GONE);
         lineAvailableWat.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
         secHandWashOthMem.setVisibility(View.GONE);
         lineHandWashOthMem.setVisibility(View.GONE);
         secHandWashOthMemO.setVisibility(View.GONE);
         lineHandWashOthMemO.setVisibility(View.GONE);
         secAvailableSoap.setVisibility(View.GONE);
         lineAvailableSoap.setVisibility(View.GONE);
         secHandWashOthMem.setVisibility(View.GONE);
         lineHandWashOthMem.setVisibility(View.GONE);
         secHandWashOthMemO.setVisibility(View.GONE);
         lineHandWashOthMemO.setVisibility(View.GONE);
         secSoapInHouse.setVisibility(View.GONE);
         lineSoapInHouse.setVisibility(View.GONE);
         secSoapInHouseShow.setVisibility(View.GONE);
         lineSoapInHouseShow.setVisibility(View.GONE);
         seclblSoapInHouseObj.setVisibility(View.GONE);
         linelblSoapInHouseObj.setVisibility(View.GONE);
         secSoapInHouseObjA.setVisibility(View.GONE);
         lineSoapInHouseObjA.setVisibility(View.GONE);
         secSoapInHouseObjB.setVisibility(View.GONE);
         lineSoapInHouseObjB.setVisibility(View.GONE);
         secSoapInHouseObjC.setVisibility(View.GONE);
         lineSoapInHouseObjC.setVisibility(View.GONE);
         secHandWashOthMem.setVisibility(View.GONE);
         lineHandWashOthMem.setVisibility(View.GONE);
         secHandWashOthMemO.setVisibility(View.GONE);
         lineHandWashOthMemO.setVisibility(View.GONE);
         secSoapInHouse.setVisibility(View.GONE);
         lineSoapInHouse.setVisibility(View.GONE);
         secSoapInHouseShow.setVisibility(View.GONE);
         lineSoapInHouseShow.setVisibility(View.GONE);
         secHandWashOthMem.setVisibility(View.GONE);
         lineHandWashOthMem.setVisibility(View.GONE);
         secHandWashOthMemO.setVisibility(View.GONE);
         lineHandWashOthMemO.setVisibility(View.GONE);
         secHandWashOthMemO.setVisibility(View.GONE);
         lineHandWashOthMemO.setVisibility(View.GONE);
         secHandWashOthMemO.setVisibility(View.GONE);
         lineHandWashOthMemO.setVisibility(View.GONE);
         secHandWashOthMemO.setVisibility(View.GONE);
         lineHandWashOthMemO.setVisibility(View.GONE);
         secHandWashOthMemO.setVisibility(View.GONE);
         lineHandWashOthMemO.setVisibility(View.GONE);
         secSoapInHouseShow.setVisibility(View.GONE);
         lineSoapInHouseShow.setVisibility(View.GONE);
         seclblSoapInHouseObj.setVisibility(View.GONE);
         linelblSoapInHouseObj.setVisibility(View.GONE);
         secSoapInHouseObjA.setVisibility(View.GONE);
         lineSoapInHouseObjA.setVisibility(View.GONE);
         secSoapInHouseObjB.setVisibility(View.GONE);
         lineSoapInHouseObjB.setVisibility(View.GONE);
         secSoapInHouseObjC.setVisibility(View.GONE);
         lineSoapInHouseObjC.setVisibility(View.GONE);
         seclblSoapInHouseObj.setVisibility(View.GONE);
         linelblSoapInHouseObj.setVisibility(View.GONE);
         secSoapInHouseObjA.setVisibility(View.GONE);
         lineSoapInHouseObjA.setVisibility(View.GONE);
         secSoapInHouseObjB.setVisibility(View.GONE);
         lineSoapInHouseObjB.setVisibility(View.GONE);
         secSoapInHouseObjC.setVisibility(View.GONE);
         lineSoapInHouseObjC.setVisibility(View.GONE);
         secCookDvcOth.setVisibility(View.GONE);
         lineCookDvcOth.setVisibility(View.GONE);
         secCookFuelOth.setVisibility(View.GONE);
         lineCookFuelOth.setVisibility(View.GONE);
         secCookPlcOth.setVisibility(View.GONE);
         lineCookPlcOth.setVisibility(View.GONE);
         secFloorOth.setVisibility(View.GONE);
         lineFloorOth.setVisibility(View.GONE);
         secWallOth.setVisibility(View.GONE);
         lineWallOth.setVisibility(View.GONE);
         secOtherAsset1.setVisibility(View.GONE);
         lineOtherAsset1.setVisibility(View.GONE);
         secOtherAsset2.setVisibility(View.GONE);
         lineOtherAsset2.setVisibility(View.GONE);
         secOtherAsset3.setVisibility(View.GONE);
         lineOtherAsset3.setVisibility(View.GONE);

         secTricycle.setVisibility(View.GONE);
         lineTricycle.setVisibility(View.GONE);
         secBoatWithMot.setVisibility(View.GONE);
         lineBoatWithMot.setVisibility(View.GONE);

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
         Connection.MessageBox(Surv_SES_CrossRiver.this, e.getMessage());
         return;
      }
   }


   private String SESNo(String HHID)
   {
      String M = C.ReturnSingleValue("Select cast(ifnull(max(SESNo),0)+1 as varchar(2))SESNo from "+ TableName +" where HHID='"+ HHID +"'");
      return M;
   }

   @SuppressLint("ClickableViewAccessibility")
   private void Initialization()
   {
      try
      {
         secTricycle=findViewById(R.id.secTricycle) ;
         lblTricycle=findViewById(R.id.lblTricycle) ;
         VlblTricycle=findViewById(R.id.VlblTricycle) ;
         rdogrpTricycle=findViewById(R.id.rdogrpTricycle) ;
         rdoTricycle1=findViewById(R.id.rdoTricycle1) ;
         rdoTricycle2=findViewById(R.id.rdoTricycle2) ;
         lineTricycle=findViewById(R.id.lineTricycle) ;
         secBoatWithMot=findViewById(R.id.secBoatWithMot) ;
         lblBoatWithMot=findViewById(R.id.lblBoatWithMot) ;
         VlblBoatWithMot=findViewById(R.id.VlblBoatWithMot) ;
         rdogrpBoatWithMot=findViewById(R.id.rdogrpBoatWithMot) ;
         rdoBoatWithMot1=findViewById(R.id.rdoBoatWithMot1) ;
         rdoBoatWithMot2=findViewById(R.id.rdoBoatWithMot2) ;
         lineBoatWithMot=findViewById(R.id.lineBoatWithMot) ;

         seclblH1=findViewById(R.id.seclblH1);
         linelblH1=findViewById(R.id.linelblH1);
         secHHID = findViewById(R.id.secHHID);
         lineHHID = findViewById(R.id.lineHHID);
         lblHHID = findViewById(R.id.lblHHID);
         VlblHHID = findViewById(R.id.VlblHHID);
         txtHHID = findViewById(R.id.txtHHID);
         txtHHID.setText(HHID);
         txtHHID.setEnabled(false);
         secHHID.setVisibility(View.GONE);
         secSESNo = findViewById(R.id.secSESNo);
         lineSESNo = findViewById(R.id.lineSESNo);
         lblSESNo = findViewById(R.id.lblSESNo);
         VlblSESNo = findViewById(R.id.VlblSESNo);
         txtSESNo = findViewById(R.id.txtSESNo);

         if (SESNO.length() == 0) txtSESNo.setText(SESNo(HHID));
         else txtSESNo.setText(SESNO);

         txtSESNo.setEnabled(false);
         secSESNo.setVisibility(View.GONE);
         secSESVDate = findViewById(R.id.secSESVDate);
         lineSESVDate = findViewById(R.id.lineSESVDate);
         lblSESVDate = findViewById(R.id.lblSESVDate);
         VlblSESVDate = findViewById(R.id.VlblSESVDate);
         txtSESVDate = findViewById(R.id.txtSESVDate);
         secSESVStatus = findViewById(R.id.secSESVStatus);
         lineSESVStatus = findViewById(R.id.lineSESVStatus);
         lblSESVStatus =  findViewById(R.id.lblSESVStatus);
         VlblSESVStatus =  findViewById(R.id.VlblSESVStatus);
         rdogrpSESVStatus =  findViewById(R.id.rdogrpSESVStatus);
         rdoSESVStatus1 =  findViewById(R.id.rdoSESVStatus1);
         rdoSESVStatus2 =  findViewById(R.id.rdoSESVStatus2);
         rdoSESVStatus3 =  findViewById(R.id.rdoSESVStatus3);
         rdoSESVStatus4 =  findViewById(R.id.rdoSESVStatus4);
         rdogrpSESVStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
               String rbData = "";
               RadioButton rb;
               String[] d_rdogrpSESVStatus = new String[] {"1","2","3","9"};
               for (int i = 0; i < rdogrpSESVStatus.getChildCount(); i++)
               {
                  rb = (RadioButton) rdogrpSESVStatus.getChildAt(i);
                  if (rb.isChecked()) rbData = d_rdogrpSESVStatus[i];
               }

               if(rbData.equalsIgnoreCase("3"))
               {
                  secSESVStatusOth.setVisibility(View.GONE);
                  lineSESVStatusOth.setVisibility(View.GONE);
                  txtSESVStatusOth.setText("");
                  secRnd.setVisibility(View.GONE);
                  lineRnd.setVisibility(View.GONE);
                  //txtRnd.setText("");
                  secWSDrink.setVisibility(View.GONE);
                  lineWSDrink.setVisibility(View.GONE);
                  spnWSDrink.setSelection(0);
                  secWSDrinkOth.setVisibility(View.GONE);
                  lineWSDrinkOth.setVisibility(View.GONE);
                  txtWSDrinkOth.setText("");
                  secMainWater.setVisibility(View.GONE);
                  lineMainWater.setVisibility(View.GONE);
                  spnMainWater.setSelection(0);
                  secMainWaterOth.setVisibility(View.GONE);
                  lineMainWaterOth.setVisibility(View.GONE);
                  txtMainWaterOth.setText("");
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
                  secHandWashOthMem.setVisibility(View.GONE);
                  lineHandWashOthMem.setVisibility(View.GONE);
                  spnHandWashOthMem.setSelection(0);
                  secHandWashOthMemO.setVisibility(View.GONE);
                  lineHandWashOthMemO.setVisibility(View.GONE);
                  txtHandWashOthMemO.setText("");
                  secSoapInHouse.setVisibility(View.GONE);
                  lineSoapInHouse.setVisibility(View.GONE);
                  rdogrpSoapInHouse.clearCheck();
                  secSoapInHouseShow.setVisibility(View.GONE);
                  lineSoapInHouseShow.setVisibility(View.GONE);
                  rdogrpSoapInHouseShow.clearCheck();
                  seclblSoapInHouseObj.setVisibility(View.GONE);
                  linelblSoapInHouseObj.setVisibility(View.GONE);
                  secSoapInHouseObjA.setVisibility(View.GONE);
                  lineSoapInHouseObjA.setVisibility(View.GONE);
                  chkSoapInHouseObjA.setChecked(false);
                  secSoapInHouseObjB.setVisibility(View.GONE);
                  lineSoapInHouseObjB.setVisibility(View.GONE);
                  chkSoapInHouseObjB.setChecked(false);
                  secSoapInHouseObjC.setVisibility(View.GONE);
                  lineSoapInHouseObjC.setVisibility(View.GONE);
                  chkSoapInHouseObjC.setChecked(false);
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
                  secGasLamp.setVisibility(View.GONE);
                  lineGasLamp.setVisibility(View.GONE);
                  rdogrpGasLamp.clearCheck();
                  secPetroleum.setVisibility(View.GONE);
                  linePetroleum.setVisibility(View.GONE);
                  rdogrpPetroleum.clearCheck();
                  secMatt.setVisibility(View.GONE);
                  lineMatt.setVisibility(View.GONE);
                  rdogrpMatt.clearCheck();
                  secMats.setVisibility(View.GONE);
                  lineMats.setVisibility(View.GONE);
                  rdogrpMats.clearCheck();
                  secCarpets.setVisibility(View.GONE);
                  lineCarpets.setVisibility(View.GONE);
                  rdogrpCarpets.clearCheck();
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
                  secTV5.setVisibility(View.GONE);
                  lineTV5.setVisibility(View.GONE);
                  rdogrpTV5.clearCheck();
                  secChannel.setVisibility(View.GONE);
                  lineChannel.setVisibility(View.GONE);
                  rdogrpChannel.clearCheck();
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
                  secLaptop.setVisibility(View.GONE);
                  lineLaptop.setVisibility(View.GONE);
                  rdogrpLaptop.clearCheck();
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
                  secGrain.setVisibility(View.GONE);
                  lineGrain.setVisibility(View.GONE);
                  rdogrpGrain.clearCheck();
                  secRefrigerator.setVisibility(View.GONE);
                  lineRefrigerator.setVisibility(View.GONE);
                  rdogrpRefrigerator.clearCheck();
                  secDeepFreezer.setVisibility(View.GONE);
                  lineDeepFreezer.setVisibility(View.GONE);
                  rdogrpDeepFreezer.clearCheck();
                  secStove.setVisibility(View.GONE);
                  lineStove.setVisibility(View.GONE);
                  rdogrpStove.clearCheck();
                  secGasHob.setVisibility(View.GONE);
                  lineGasHob.setVisibility(View.GONE);
                  rdogrpGasHob.clearCheck();
                  secImpCooker.setVisibility(View.GONE);
                  lineImpCooker.setVisibility(View.GONE);
                  rdogrpImpCooker.clearCheck();
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

                  secTricycle.setVisibility(View.GONE);
                  lineTricycle.setVisibility(View.GONE);
                  rdogrpTricycle.clearCheck();

                  secBoatWithMot.setVisibility(View.GONE);
                  lineBoatWithMot.setVisibility(View.GONE);
                  rdogrpBoatWithMot.clearCheck();


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
                  secBirds.setVisibility(View.GONE);
                  lineBirds.setVisibility(View.GONE);
                  rdogrpBirds.clearCheck();
                  secDogs.setVisibility(View.GONE);
                  lineDogs.setVisibility(View.GONE);
                  rdogrpDogs.clearCheck();
                  secCats.setVisibility(View.GONE);
                  lineCats.setVisibility(View.GONE);
                  rdogrpCats.clearCheck();
                  secFishNet.setVisibility(View.GONE);
                  lineFishNet.setVisibility(View.GONE);
                  rdogrpFishNet.clearCheck();
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
               }
               else if(rbData.equalsIgnoreCase("9"))
               {
                  secSESVStatusOth.setVisibility(View.VISIBLE);
                  lineSESVStatusOth.setVisibility(View.VISIBLE);
                  txtSESVStatusOth.setText("");
                  secRnd.setVisibility(View.GONE);
                  lineRnd.setVisibility(View.GONE);
                  //txtRnd.setText("");
                  secWSDrink.setVisibility(View.GONE);
                  lineWSDrink.setVisibility(View.GONE);
                  spnWSDrink.setSelection(0);
                  secWSDrinkOth.setVisibility(View.GONE);
                  lineWSDrinkOth.setVisibility(View.GONE);
                  txtWSDrinkOth.setText("");
                  secMainWater.setVisibility(View.GONE);
                  lineMainWater.setVisibility(View.GONE);
                  spnMainWater.setSelection(0);
                  secMainWaterOth.setVisibility(View.GONE);
                  lineMainWaterOth.setVisibility(View.GONE);
                  txtMainWaterOth.setText("");
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
                  secHandWashOthMem.setVisibility(View.GONE);
                  lineHandWashOthMem.setVisibility(View.GONE);
                  spnHandWashOthMem.setSelection(0);
                  secHandWashOthMemO.setVisibility(View.GONE);
                  lineHandWashOthMemO.setVisibility(View.GONE);
                  txtHandWashOthMemO.setText("");
                  secSoapInHouse.setVisibility(View.GONE);
                  lineSoapInHouse.setVisibility(View.GONE);
                  rdogrpSoapInHouse.clearCheck();
                  secSoapInHouseShow.setVisibility(View.GONE);
                  lineSoapInHouseShow.setVisibility(View.GONE);
                  rdogrpSoapInHouseShow.clearCheck();
                  seclblSoapInHouseObj.setVisibility(View.GONE);
                  linelblSoapInHouseObj.setVisibility(View.GONE);
                  secSoapInHouseObjA.setVisibility(View.GONE);
                  lineSoapInHouseObjA.setVisibility(View.GONE);
                  chkSoapInHouseObjA.setChecked(false);
                  secSoapInHouseObjB.setVisibility(View.GONE);
                  lineSoapInHouseObjB.setVisibility(View.GONE);
                  chkSoapInHouseObjB.setChecked(false);
                  secSoapInHouseObjC.setVisibility(View.GONE);
                  lineSoapInHouseObjC.setVisibility(View.GONE);
                  chkSoapInHouseObjC.setChecked(false);
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
                  secGasLamp.setVisibility(View.GONE);
                  lineGasLamp.setVisibility(View.GONE);
                  rdogrpGasLamp.clearCheck();
                  secPetroleum.setVisibility(View.GONE);
                  linePetroleum.setVisibility(View.GONE);
                  rdogrpPetroleum.clearCheck();
                  secMatt.setVisibility(View.GONE);
                  lineMatt.setVisibility(View.GONE);
                  rdogrpMatt.clearCheck();
                  secMats.setVisibility(View.GONE);
                  lineMats.setVisibility(View.GONE);
                  rdogrpMats.clearCheck();
                  secCarpets.setVisibility(View.GONE);
                  lineCarpets.setVisibility(View.GONE);
                  rdogrpCarpets.clearCheck();
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
                  secTV5.setVisibility(View.GONE);
                  lineTV5.setVisibility(View.GONE);
                  rdogrpTV5.clearCheck();
                  secChannel.setVisibility(View.GONE);
                  lineChannel.setVisibility(View.GONE);
                  rdogrpChannel.clearCheck();
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
                  secLaptop.setVisibility(View.GONE);
                  lineLaptop.setVisibility(View.GONE);
                  rdogrpLaptop.clearCheck();
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
                  secGrain.setVisibility(View.GONE);
                  lineGrain.setVisibility(View.GONE);
                  rdogrpGrain.clearCheck();
                  secRefrigerator.setVisibility(View.GONE);
                  lineRefrigerator.setVisibility(View.GONE);
                  rdogrpRefrigerator.clearCheck();
                  secDeepFreezer.setVisibility(View.GONE);
                  lineDeepFreezer.setVisibility(View.GONE);
                  rdogrpDeepFreezer.clearCheck();
                  secStove.setVisibility(View.GONE);
                  lineStove.setVisibility(View.GONE);
                  rdogrpStove.clearCheck();
                  secGasHob.setVisibility(View.GONE);
                  lineGasHob.setVisibility(View.GONE);
                  rdogrpGasHob.clearCheck();
                  secImpCooker.setVisibility(View.GONE);
                  lineImpCooker.setVisibility(View.GONE);
                  rdogrpImpCooker.clearCheck();
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

                  secTricycle.setVisibility(View.GONE);
                  lineTricycle.setVisibility(View.GONE);
                  rdogrpTricycle.clearCheck();

                  secBoatWithMot.setVisibility(View.GONE);
                  lineBoatWithMot.setVisibility(View.GONE);
                  rdogrpBoatWithMot.clearCheck();

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
                  secBirds.setVisibility(View.GONE);
                  lineBirds.setVisibility(View.GONE);
                  rdogrpBirds.clearCheck();
                  secDogs.setVisibility(View.GONE);
                  lineDogs.setVisibility(View.GONE);
                  rdogrpDogs.clearCheck();
                  secCats.setVisibility(View.GONE);
                  lineCats.setVisibility(View.GONE);
                  rdogrpCats.clearCheck();
                  secFishNet.setVisibility(View.GONE);
                  lineFishNet.setVisibility(View.GONE);
                  rdogrpFishNet.clearCheck();
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
               }
               else
               {
                  secSESVStatusOth.setVisibility(View.GONE);
                  lineSESVStatusOth.setVisibility(View.GONE);
                  //secRnd.setVisibility(View.VISIBLE);
                  //lineRnd.setVisibility(View.VISIBLE);
                  secWSDrink.setVisibility(View.VISIBLE);
                  lineWSDrink.setVisibility(View.VISIBLE);
                  //secWSDrinkOth.setVisibility(View.VISIBLE);
                  //lineWSDrinkOth.setVisibility(View.VISIBLE);
                  secMainWater.setVisibility(View.VISIBLE);
                  lineMainWater.setVisibility(View.VISIBLE);
                  //secMainWaterOth.setVisibility(View.VISIBLE);
                  //lineMainWaterOth.setVisibility(View.VISIBLE);
                  secToilet.setVisibility(View.VISIBLE);
                  lineToilet.setVisibility(View.VISIBLE);
                  //secToiletOth.setVisibility(View.VISIBLE);
                  //lineToiletOth.setVisibility(View.VISIBLE);
                  secToiletShrd.setVisibility(View.VISIBLE);
                  lineToiletShrd.setVisibility(View.VISIBLE);
                  //secToiletUseNo.setVisibility(View.VISIBLE);
                  //lineToiletUseNo.setVisibility(View.VISIBLE);
                  //secToiletUseNoDk.setVisibility(View.VISIBLE);
                  //lineToiletUseNoDk.setVisibility(View.VISIBLE);
                  secToiletLoc.setVisibility(View.VISIBLE);
                  lineToiletLoc.setVisibility(View.VISIBLE);

                  //secContentEmpOth.setVisibility(View.VISIBLE);
                  //lineContentEmpOth.setVisibility(View.VISIBLE);

                  //secBowelMovOth.setVisibility(View.VISIBLE);
                  //lineBowelMovOth.setVisibility(View.VISIBLE);

                  //secLiquidWasteOth.setVisibility(View.VISIBLE);
                  //lineLiquidWasteOth.setVisibility(View.VISIBLE);

                  //secSolidWasteMethodOth.setVisibility(View.VISIBLE);
                  //lineSolidWasteMethodOth.setVisibility(View.VISIBLE);
                  secHandWash.setVisibility(View.VISIBLE);
                  lineHandWash.setVisibility(View.VISIBLE);
                  secShowWash.setVisibility(View.VISIBLE);
                  lineShowWash.setVisibility(View.VISIBLE);
                  secAvailableWat.setVisibility(View.VISIBLE);
                  lineAvailableWat.setVisibility(View.VISIBLE);
                  secAvailableSoap.setVisibility(View.VISIBLE);
                  lineAvailableSoap.setVisibility(View.VISIBLE);
                  secHandWashOthMem.setVisibility(View.VISIBLE);
                  lineHandWashOthMem.setVisibility(View.VISIBLE);
                  //secHandWashOthMemO.setVisibility(View.VISIBLE);
                  //lineHandWashOthMemO.setVisibility(View.VISIBLE);
                  secSoapInHouse.setVisibility(View.VISIBLE);
                  lineSoapInHouse.setVisibility(View.VISIBLE);
                  secSoapInHouseShow.setVisibility(View.VISIBLE);
                  lineSoapInHouseShow.setVisibility(View.VISIBLE);
                  seclblSoapInHouseObj.setVisibility(View.VISIBLE);
                  linelblSoapInHouseObj.setVisibility(View.VISIBLE);
                  secSoapInHouseObjA.setVisibility(View.VISIBLE);
                  lineSoapInHouseObjA.setVisibility(View.VISIBLE);
                  secSoapInHouseObjB.setVisibility(View.VISIBLE);
                  lineSoapInHouseObjB.setVisibility(View.VISIBLE);
                  secSoapInHouseObjC.setVisibility(View.VISIBLE);
                  lineSoapInHouseObjC.setVisibility(View.VISIBLE);
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

                  secTricycle.setVisibility(View.VISIBLE);
                  lineTricycle.setVisibility(View.VISIBLE);

                  secBoatWithMot.setVisibility(View.VISIBLE);
                  lineBoatWithMot.setVisibility(View.VISIBLE);


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
         secSESVStatusOth = findViewById(R.id.secSESVStatusOth);
         lineSESVStatusOth = findViewById(R.id.lineSESVStatusOth);
         lblSESVStatusOth = findViewById(R.id.lblSESVStatusOth);
         VlblSESVStatusOth = findViewById(R.id.VlblSESVStatusOth);
         txtSESVStatusOth = findViewById(R.id.txtSESVStatusOth);
         secRnd = findViewById(R.id.secRnd);
         lineRnd = findViewById(R.id.lineRnd);
         lblRnd = findViewById(R.id.lblRnd);
         VlblRnd = findViewById(R.id.VlblRnd);
         txtRnd = findViewById(R.id.txtRnd);
         txtRnd.setText(ROUND);
         secRnd.setVisibility(View.GONE);
         lineRnd.setVisibility(View.GONE);
         secWSDrink = findViewById(R.id.secWSDrink);
         lineWSDrink = findViewById(R.id.lineWSDrink);
         lblWSDrink = findViewById(R.id.lblWSDrink);
         VlblWSDrink = findViewById(R.id.VlblWSDrink);
         spnWSDrink = findViewById(R.id.spnWSDrink);
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
         secWSDrinkOth = findViewById(R.id.secWSDrinkOth);
         lineWSDrinkOth = findViewById(R.id.lineWSDrinkOth);
         lblWSDrinkOth = findViewById(R.id.lblWSDrinkOth);
         VlblWSDrinkOth = findViewById(R.id.VlblWSDrinkOth);
         txtWSDrinkOth = findViewById(R.id.txtWSDrinkOth);
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
         secMainWater = findViewById(R.id.secMainWater);
         lineMainWater = findViewById(R.id.lineMainWater);
         lblMainWater = findViewById(R.id.lblMainWater);
         VlblMainWater = findViewById(R.id.VlblMainWater);
         spnMainWater = findViewById(R.id.spnMainWater);
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
         secMainWaterOth = findViewById(R.id.secMainWaterOth);
         lineMainWaterOth = findViewById(R.id.lineMainWaterOth);
         lblMainWaterOth = findViewById(R.id.lblMainWaterOth);
         VlblMainWaterOth = findViewById(R.id.VlblMainWaterOth);
         txtMainWaterOth = findViewById(R.id.txtMainWaterOth);
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
         secToilet = findViewById(R.id.secToilet);
         lineToilet = findViewById(R.id.lineToilet);
         lblToilet = findViewById(R.id.lblToilet);
         VlblToilet = findViewById(R.id.VlblToilet);
         spnToilet = findViewById(R.id.spnToilet);
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
         secToiletOth = findViewById(R.id.secToiletOth);
         lineToiletOth = findViewById(R.id.lineToiletOth);
         lblToiletOth = findViewById(R.id.lblToiletOth);
         VlblToiletOth = findViewById(R.id.VlblToiletOth);
         txtToiletOth = findViewById(R.id.txtToiletOth);
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
         secToiletShrd = findViewById(R.id.secToiletShrd);
         lineToiletShrd = findViewById(R.id.lineToiletShrd);
         lblToiletShrd =  findViewById(R.id.lblToiletShrd);
         VlblToiletShrd =  findViewById(R.id.VlblToiletShrd);
         rdogrpToiletShrd =  findViewById(R.id.rdogrpToiletShrd);
         rdoToiletShrd1 =  findViewById(R.id.rdoToiletShrd1);
         rdoToiletShrd2 =  findViewById(R.id.rdoToiletShrd2);
         rdoToiletShrd3 =  findViewById(R.id.rdoToiletShrd3);
         rdogrpToiletShrd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
               String rbData = "";
               RadioButton rb;
               String[] d_rdogrpToiletShrd = new String[] {"0","1","2"};
               for (int i = 0; i < rdogrpToiletShrd.getChildCount(); i++)
               {
                  rb = (RadioButton) rdogrpToiletShrd.getChildAt(i);
                  if (rb.isChecked()) rbData = d_rdogrpToiletShrd[i];
               }

               if(rbData.equalsIgnoreCase("1"))
               {
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
         secToiletUseNo = findViewById(R.id.secToiletUseNo);
         lineToiletUseNo = findViewById(R.id.lineToiletUseNo);
         lblToiletUseNo = findViewById(R.id.lblToiletUseNo);
         VlblToiletUseNo = findViewById(R.id.VlblToiletUseNo);
         txtToiletUseNo = findViewById(R.id.txtToiletUseNo);
         txtToiletUseNo.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
               if(txtToiletUseNo.getText().length()>0) rdogrpToiletUseNoDk.clearCheck();
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
         });

         secToiletUseNoDk = findViewById(R.id.secToiletUseNoDk);
         lineToiletUseNoDk = findViewById(R.id.lineToiletUseNoDk);
         lblToiletUseNoDk =  findViewById(R.id.lblToiletUseNoDk);
         VlblToiletUseNoDk =  findViewById(R.id.VlblToiletUseNoDk);
         rdogrpToiletUseNoDk =  findViewById(R.id.rdogrpToiletUseNoDk);
         rdoToiletUseNoDk1 =  findViewById(R.id.rdoToiletUseNoDk1);
         rdoToiletUseNoDk2 =  findViewById(R.id.rdoToiletUseNoDk2);
         rdoToiletUseNoDk3 =  findViewById(R.id.rdoToiletUseNoDk3);
         rdogrpToiletUseNoDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
               String rbData = "";
               RadioButton rb;
               String[] d_rdogrpShowWash = new String[] {"95","98","99"};
               for (int i = 0; i < rdogrpToiletUseNoDk.getChildCount(); i++)
               {
                  rb = (RadioButton) rdogrpToiletUseNoDk.getChildAt(i);
                  if (rb.isChecked()) rbData = d_rdogrpShowWash[i];
               }

               if(rbData.equals("95")||rbData.equals("98")||rbData.equals("99")){
                  txtToiletUseNo.setText("");
               }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
               return;
            }
         });

         secToiletLoc = findViewById(R.id.secToiletLoc);
         lineToiletLoc = findViewById(R.id.lineToiletLoc);
         lblToiletLoc =  findViewById(R.id.lblToiletLoc);
         VlblToiletLoc =  findViewById(R.id.VlblToiletLoc);
         rdogrpToiletLoc =  findViewById(R.id.rdogrpToiletLoc);
         rdoToiletLoc1 =  findViewById(R.id.rdoToiletLoc1);
         rdoToiletLoc2 =  findViewById(R.id.rdoToiletLoc2);
         rdoToiletLoc3 =  findViewById(R.id.rdoToiletLoc3);
         rdoToiletLoc4 =  findViewById(R.id.rdoToiletLoc4);
         secHandWash = findViewById(R.id.secHandWash);
         lineHandWash = findViewById(R.id.lineHandWash);
         lblHandWash =  findViewById(R.id.lblHandWash);
         VlblHandWash =  findViewById(R.id.VlblHandWash);
         rdogrpHandWash =  findViewById(R.id.rdogrpHandWash);
         rdoHandWash1 =  findViewById(R.id.rdoHandWash1);
         rdoHandWash2 =  findViewById(R.id.rdoHandWash2);
         rdoHandWash3 =  findViewById(R.id.rdoHandWash3);
         rdogrpHandWash.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
               String rbData = "";
               RadioButton rb;
               String[] d_rdogrpHandWash = new String[] {"0","1","8"};
               for (int i = 0; i < rdogrpHandWash.getChildCount(); i++)
               {
                  rb = (RadioButton) rdogrpHandWash.getChildAt(i);
                  if (rb.isChecked()) rbData = d_rdogrpHandWash[i];
               }

               if(rbData.equalsIgnoreCase("0"))
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
                  secHandWashOthMem.setVisibility(View.GONE);
                  lineHandWashOthMem.setVisibility(View.GONE);
                  spnHandWashOthMem.setSelection(0);
                  secHandWashOthMemO.setVisibility(View.GONE);
                  lineHandWashOthMemO.setVisibility(View.GONE);
                  txtHandWashOthMemO.setText("");
                  secSoapInHouse.setVisibility(View.GONE);
                  lineSoapInHouse.setVisibility(View.GONE);
                  rdogrpSoapInHouse.clearCheck();
                  secSoapInHouseShow.setVisibility(View.GONE);
                  lineSoapInHouseShow.setVisibility(View.GONE);
                  rdogrpSoapInHouseShow.clearCheck();
                  seclblSoapInHouseObj.setVisibility(View.GONE);
                  linelblSoapInHouseObj.setVisibility(View.GONE);
                  secSoapInHouseObjA.setVisibility(View.GONE);
                  lineSoapInHouseObjA.setVisibility(View.GONE);
                  chkSoapInHouseObjA.setChecked(false);
                  secSoapInHouseObjB.setVisibility(View.GONE);
                  lineSoapInHouseObjB.setVisibility(View.GONE);
                  chkSoapInHouseObjB.setChecked(false);
                  secSoapInHouseObjC.setVisibility(View.GONE);
                  lineSoapInHouseObjC.setVisibility(View.GONE);
                  chkSoapInHouseObjC.setChecked(false);
               }
               else if(rbData.equalsIgnoreCase("8"))
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
                  secHandWashOthMem.setVisibility(View.GONE);
                  lineHandWashOthMem.setVisibility(View.GONE);
                  spnHandWashOthMem.setSelection(0);
                  secHandWashOthMemO.setVisibility(View.GONE);
                  lineHandWashOthMemO.setVisibility(View.GONE);
                  txtHandWashOthMemO.setText("");
                  secSoapInHouse.setVisibility(View.GONE);
                  lineSoapInHouse.setVisibility(View.GONE);
                  rdogrpSoapInHouse.clearCheck();
                  secSoapInHouseShow.setVisibility(View.GONE);
                  lineSoapInHouseShow.setVisibility(View.GONE);
                  rdogrpSoapInHouseShow.clearCheck();
                  seclblSoapInHouseObj.setVisibility(View.GONE);
                  linelblSoapInHouseObj.setVisibility(View.GONE);
                  secSoapInHouseObjA.setVisibility(View.GONE);
                  lineSoapInHouseObjA.setVisibility(View.GONE);
                  chkSoapInHouseObjA.setChecked(false);
                  secSoapInHouseObjB.setVisibility(View.GONE);
                  lineSoapInHouseObjB.setVisibility(View.GONE);
                  chkSoapInHouseObjB.setChecked(false);
                  secSoapInHouseObjC.setVisibility(View.GONE);
                  lineSoapInHouseObjC.setVisibility(View.GONE);
                  chkSoapInHouseObjC.setChecked(false);
               }
               else
               {
                  secShowWash.setVisibility(View.VISIBLE);
                  lineShowWash.setVisibility(View.VISIBLE);
                  secAvailableWat.setVisibility(View.VISIBLE);
                  lineAvailableWat.setVisibility(View.VISIBLE);
                  secAvailableSoap.setVisibility(View.VISIBLE);
                  lineAvailableSoap.setVisibility(View.VISIBLE);
                  secHandWashOthMem.setVisibility(View.VISIBLE);
                  lineHandWashOthMem.setVisibility(View.VISIBLE);
                  secHandWashOthMemO.setVisibility(View.VISIBLE);
                  lineHandWashOthMemO.setVisibility(View.VISIBLE);
                  secSoapInHouse.setVisibility(View.VISIBLE);
                  lineSoapInHouse.setVisibility(View.VISIBLE);
                  secSoapInHouseShow.setVisibility(View.VISIBLE);
                  lineSoapInHouseShow.setVisibility(View.VISIBLE);
               }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
               return;
            }
         });
         secShowWash = findViewById(R.id.secShowWash);
         lineShowWash = findViewById(R.id.lineShowWash);
         lblShowWash =  findViewById(R.id.lblShowWash);
         VlblShowWash =  findViewById(R.id.VlblShowWash);
         rdogrpShowWash =  findViewById(R.id.rdogrpShowWash);
         rdoShowWash1 =  findViewById(R.id.rdoShowWash1);
         rdoShowWash2 =  findViewById(R.id.rdoShowWash2);
         rdoShowWash3 =  findViewById(R.id.rdoShowWash3);
         rdoShowWash4 =  findViewById(R.id.rdoShowWash4);
         rdoShowWash5 =  findViewById(R.id.rdoShowWash5);
         rdoShowWash6 =  findViewById(R.id.rdoShowWash6);
         rdogrpShowWash.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
               String rbData = "";
               RadioButton rb;
               String[] d_rdogrpShowWash = new String[] {"1","2","3","4","5","6"};
               for (int i = 0; i < rdogrpShowWash.getChildCount(); i++)
               {
                  rb = (RadioButton) rdogrpShowWash.getChildAt(i);
                  if (rb.isChecked()) rbData = d_rdogrpShowWash[i];
               }

               if(rbData.equalsIgnoreCase("4"))
               {
                  secAvailableWat.setVisibility(View.GONE);
                  lineAvailableWat.setVisibility(View.GONE);
                  rdogrpAvailableWat.clearCheck();
                  secAvailableSoap.setVisibility(View.GONE);
                  lineAvailableSoap.setVisibility(View.GONE);
                  rdogrpAvailableSoap.clearCheck();
                  secHandWashOthMem.setVisibility(View.GONE);
                  lineHandWashOthMem.setVisibility(View.GONE);
                  spnHandWashOthMem.setSelection(0);
                  secHandWashOthMemO.setVisibility(View.GONE);
                  lineHandWashOthMemO.setVisibility(View.GONE);
                  txtHandWashOthMemO.setText("");
                  secSoapInHouse.setVisibility(View.VISIBLE);
                  lineSoapInHouse.setVisibility(View.VISIBLE);
               }
               else if(rbData.equalsIgnoreCase("5"))
               {
                  secAvailableWat.setVisibility(View.GONE);
                  lineAvailableWat.setVisibility(View.GONE);
                  rdogrpAvailableWat.clearCheck();
                  secAvailableSoap.setVisibility(View.GONE);
                  lineAvailableSoap.setVisibility(View.GONE);
                  rdogrpAvailableSoap.clearCheck();
                  secHandWashOthMem.setVisibility(View.GONE);
                  lineHandWashOthMem.setVisibility(View.GONE);
                  secHandWashOthMemO.setVisibility(View.GONE);
                  lineHandWashOthMemO.setVisibility(View.GONE);
                  txtHandWashOthMemO.setText("");
                  secSoapInHouse.setVisibility(View.VISIBLE);
                  lineSoapInHouse.setVisibility(View.VISIBLE);
               }
               else if(rbData.equalsIgnoreCase("6"))
               {
                  secAvailableWat.setVisibility(View.GONE);
                  lineAvailableWat.setVisibility(View.GONE);
                  rdogrpAvailableWat.clearCheck();
                  secAvailableSoap.setVisibility(View.GONE);
                  lineAvailableSoap.setVisibility(View.GONE);
                  rdogrpAvailableSoap.clearCheck();
                  secHandWashOthMem.setVisibility(View.VISIBLE);
                  lineHandWashOthMem.setVisibility(View.VISIBLE);
                  secHandWashOthMemO.setVisibility(View.GONE);
                  lineHandWashOthMemO.setVisibility(View.GONE);
                  txtHandWashOthMemO.setText("");
                  secSoapInHouse.setVisibility(View.VISIBLE);
                  lineSoapInHouse.setVisibility(View.VISIBLE);
               }
               else
               {
                  secAvailableWat.setVisibility(View.VISIBLE);
                  lineAvailableWat.setVisibility(View.VISIBLE);
                  secAvailableSoap.setVisibility(View.VISIBLE);
                  lineAvailableSoap.setVisibility(View.VISIBLE);
                  secHandWashOthMem.setVisibility(View.VISIBLE);
                  lineHandWashOthMem.setVisibility(View.VISIBLE);
               }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
               return;
            }
         });
         secAvailableWat = findViewById(R.id.secAvailableWat);
         lineAvailableWat = findViewById(R.id.lineAvailableWat);
         lblAvailableWat =  findViewById(R.id.lblAvailableWat);
         VlblAvailableWat =  findViewById(R.id.VlblAvailableWat);
         rdogrpAvailableWat =  findViewById(R.id.rdogrpAvailableWat);
         rdoAvailableWat1 =  findViewById(R.id.rdoAvailableWat1);
         rdoAvailableWat2 =  findViewById(R.id.rdoAvailableWat2);
         /*rdogrpAvailableWat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
               String rbData = "";
               RadioButton rb;
               String[] d_rdogrpAvailableWat = new String[] {"1","2"};
               for (int i = 0; i < rdogrpAvailableWat.getChildCount(); i++)
               {
                  rb = (RadioButton) rdogrpAvailableWat.getChildAt(i);
                  if (rb.isChecked()) rbData = d_rdogrpAvailableWat[i];
               }

               if(rbData.equalsIgnoreCase("1"))
               {
                  secAvailableSoap.setVisibility(View.VISIBLE);
                  lineAvailableSoap.setVisibility(View.VISIBLE);
                  secHandWashOthMem.setVisibility(View.VISIBLE);
                  lineHandWashOthMem.setVisibility(View.VISIBLE);
                  secHandWashOthMemO.setVisibility(View.VISIBLE);
                  lineHandWashOthMemO.setVisibility(View.VISIBLE);
                  secSoapInHouse.setVisibility(View.VISIBLE);
                  lineSoapInHouse.setVisibility(View.VISIBLE);
                  secSoapInHouseShow.setVisibility(View.VISIBLE);
                  lineSoapInHouseShow.setVisibility(View.VISIBLE);


               }
               else
               {
                  secAvailableSoap.setVisibility(View.GONE);
                  lineAvailableSoap.setVisibility(View.GONE);
                  rdogrpAvailableSoap.clearCheck();
                  secHandWashOthMem.setVisibility(View.GONE);
                  lineHandWashOthMem.setVisibility(View.GONE);
                  spnHandWashOthMem.setSelection(0);
                  secHandWashOthMemO.setVisibility(View.GONE);
                  lineHandWashOthMemO.setVisibility(View.GONE);
                  txtHandWashOthMemO.setText("");
                  secSoapInHouse.setVisibility(View.GONE);
                  lineSoapInHouse.setVisibility(View.GONE);
                  rdogrpSoapInHouse.clearCheck();
                  secSoapInHouseShow.setVisibility(View.GONE);
                  lineSoapInHouseShow.setVisibility(View.GONE);
                  rdogrpSoapInHouseShow.clearCheck();
                  seclblSoapInHouseObj.setVisibility(View.GONE);
                  linelblSoapInHouseObj.setVisibility(View.GONE);
                  secSoapInHouseObjA.setVisibility(View.GONE);
                  lineSoapInHouseObjA.setVisibility(View.GONE);
                  chkSoapInHouseObjA.setChecked(false);
                  secSoapInHouseObjB.setVisibility(View.GONE);
                  lineSoapInHouseObjB.setVisibility(View.GONE);
                  chkSoapInHouseObjB.setChecked(false);
                  secSoapInHouseObjC.setVisibility(View.GONE);
                  lineSoapInHouseObjC.setVisibility(View.GONE);
                  chkSoapInHouseObjC.setChecked(false);
               }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
               return;
            }
         });*/
         secAvailableSoap = findViewById(R.id.secAvailableSoap);
         lineAvailableSoap = findViewById(R.id.lineAvailableSoap);
         lblAvailableSoap =  findViewById(R.id.lblAvailableSoap);
         VlblAvailableSoap =  findViewById(R.id.VlblAvailableSoap);
         rdogrpAvailableSoap =  findViewById(R.id.rdogrpAvailableSoap);
         rdoAvailableSoap1 =  findViewById(R.id.rdoAvailableSoap1);
         rdoAvailableSoap2 =  findViewById(R.id.rdoAvailableSoap2);
         rdogrpAvailableSoap.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
               String rbData = "";
               RadioButton rb;
               String[] d_rdogrpAvailableSoap = new String[] {"1","2"};
               for (int i = 0; i < rdogrpAvailableSoap.getChildCount(); i++)
               {
                  rb = (RadioButton) rdogrpAvailableSoap.getChildAt(i);
                  if (rb.isChecked()) rbData = d_rdogrpAvailableSoap[i];
               }

               if(rbData.equalsIgnoreCase("1"))
               {
                  secHandWashOthMem.setVisibility(View.GONE);
                  lineHandWashOthMem.setVisibility(View.GONE);
                  spnHandWashOthMem.setSelection(0);
                  secHandWashOthMemO.setVisibility(View.GONE);
                  lineHandWashOthMemO.setVisibility(View.GONE);
                  txtHandWashOthMemO.setText("");
                  secSoapInHouse.setVisibility(View.GONE);
                  lineSoapInHouse.setVisibility(View.GONE);
                  rdogrpSoapInHouse.clearCheck();
                  secSoapInHouseShow.setVisibility(View.GONE);
                  lineSoapInHouseShow.setVisibility(View.GONE);
                  rdogrpSoapInHouseShow.clearCheck();
               }
               else if(rbData.equalsIgnoreCase("2"))
               {
                  secHandWashOthMem.setVisibility(View.GONE);
                  lineHandWashOthMem.setVisibility(View.GONE);
                  spnHandWashOthMem.setSelection(0);
                  secHandWashOthMemO.setVisibility(View.GONE);
                  lineHandWashOthMemO.setVisibility(View.GONE);
                  txtHandWashOthMemO.setText("");
               }

            }
            public void onNothingSelected(AdapterView<?> adapterView) {
               return;
            }
         });
         secHandWashOthMem = findViewById(R.id.secHandWashOthMem);
         lineHandWashOthMem = findViewById(R.id.lineHandWashOthMem);
         lblHandWashOthMem = findViewById(R.id.lblHandWashOthMem);
         VlblHandWashOthMem = findViewById(R.id.VlblHandWashOthMem);
         spnHandWashOthMem = findViewById(R.id.spnHandWashOthMem);
         List<String> listHandWashOthMem = new ArrayList<String>();

         listHandWashOthMem.add("");
         listHandWashOthMem.add("1-Fixed facility (Sink / Tap): In dwelling");
         listHandWashOthMem.add("2-Fixed facility (Sink / Tap): In yard / plot");
         listHandWashOthMem.add("3-Mobile object:(Bucket / Jug / Kettle)");
         listHandWashOthMem.add("4-No handwashing place in dwelling/yard/plot");
         listHandWashOthMem.add("7-Other (specify)");
         spnHandWashOthMem.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listHandWashOthMem)));

         spnHandWashOthMem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               String spnData = "";
               if (spnHandWashOthMem.getSelectedItem().toString().length() != 0)
               {
                  spnData = Connection.SelectedSpinnerValue(spnHandWashOthMem.getSelectedItem().toString(), "-");
               }
               if(spnData.equalsIgnoreCase("7"))
               {
                  secHandWashOthMemO.setVisibility(View.VISIBLE);
                  lineHandWashOthMemO.setVisibility(View.VISIBLE);
               }
               else
               {
                  secHandWashOthMemO.setVisibility(View.GONE);
                  lineHandWashOthMemO.setVisibility(View.GONE);
                  txtHandWashOthMemO.setText("");
               }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
         });
         secHandWashOthMemO = findViewById(R.id.secHandWashOthMemO);
         lineHandWashOthMemO = findViewById(R.id.lineHandWashOthMemO);
         lblHandWashOthMemO = findViewById(R.id.lblHandWashOthMemO);
         VlblHandWashOthMemO = findViewById(R.id.VlblHandWashOthMemO);
         txtHandWashOthMemO = findViewById(R.id.txtHandWashOthMemO);
         txtHandWashOthMemO.setAdapter(C.getArrayAdapter("Select distinct HandWashOthMemO from "+ TableName +" order by HandWashOthMemO"));

         txtHandWashOthMemO.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id) {

            }
         });
         txtHandWashOthMemO.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               final int DRAWABLE_RIGHT = 2;

               if(event.getAction() == MotionEvent.ACTION_UP) {
                  if(event.getRawX() >= (txtHandWashOthMemO.getRight() - txtHandWashOthMemO.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                     ((EditText)v).setText("");
                     return true;
                  }
               }
               return false;
            }
         });
         secSoapInHouse = findViewById(R.id.secSoapInHouse);
         lineSoapInHouse = findViewById(R.id.lineSoapInHouse);
         lblSoapInHouse =  findViewById(R.id.lblSoapInHouse);
         VlblSoapInHouse =  findViewById(R.id.VlblSoapInHouse);
         rdogrpSoapInHouse =  findViewById(R.id.rdogrpSoapInHouse);
         rdoSoapInHouse1 =  findViewById(R.id.rdoSoapInHouse1);
         rdoSoapInHouse2 =  findViewById(R.id.rdoSoapInHouse2);
         rdogrpSoapInHouse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
               String rbData = "";
               RadioButton rb;
               String[] d_rdogrpSoapInHouse = new String[] {"1","2"};
               for (int i = 0; i < rdogrpSoapInHouse.getChildCount(); i++)
               {
                  rb = (RadioButton) rdogrpSoapInHouse.getChildAt(i);
                  if (rb.isChecked()) rbData = d_rdogrpSoapInHouse[i];
               }

               if(rbData.equalsIgnoreCase("2"))
               {
                  secSoapInHouseShow.setVisibility(View.GONE);
                  lineSoapInHouseShow.setVisibility(View.GONE);
                  rdogrpSoapInHouseShow.clearCheck();
                  seclblSoapInHouseObj.setVisibility(View.GONE);
                  linelblSoapInHouseObj.setVisibility(View.GONE);
                  secSoapInHouseObjA.setVisibility(View.GONE);
                  lineSoapInHouseObjA.setVisibility(View.GONE);
                  chkSoapInHouseObjA.setChecked(false);
                  secSoapInHouseObjB.setVisibility(View.GONE);
                  lineSoapInHouseObjB.setVisibility(View.GONE);
                  chkSoapInHouseObjB.setChecked(false);
                  secSoapInHouseObjC.setVisibility(View.GONE);
                  lineSoapInHouseObjC.setVisibility(View.GONE);
                  chkSoapInHouseObjC.setChecked(false);
               }
               else
               {
                  secSoapInHouseShow.setVisibility(View.VISIBLE);
                  lineSoapInHouseShow.setVisibility(View.VISIBLE);
               }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
               return;
            }
         });
         secSoapInHouseShow = findViewById(R.id.secSoapInHouseShow);
         lineSoapInHouseShow = findViewById(R.id.lineSoapInHouseShow);
         lblSoapInHouseShow =  findViewById(R.id.lblSoapInHouseShow);
         VlblSoapInHouseShow =  findViewById(R.id.VlblSoapInHouseShow);
         rdogrpSoapInHouseShow =  findViewById(R.id.rdogrpSoapInHouseShow);
         rdoSoapInHouseShow1 =  findViewById(R.id.rdoSoapInHouseShow1);
         rdoSoapInHouseShow2 =  findViewById(R.id.rdoSoapInHouseShow2);
         rdogrpSoapInHouseShow.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
               String rbData = "";
               RadioButton rb;
               String[] d_rdogrpSoapInHouseShow = new String[] {"1","2"};
               for (int i = 0; i < rdogrpSoapInHouseShow.getChildCount(); i++)
               {
                  rb = (RadioButton) rdogrpSoapInHouseShow.getChildAt(i);
                  if (rb.isChecked()) rbData = d_rdogrpSoapInHouseShow[i];
               }

               if(rbData.equalsIgnoreCase("2"))
               {
                  seclblSoapInHouseObj.setVisibility(View.GONE);
                  linelblSoapInHouseObj.setVisibility(View.GONE);
                  secSoapInHouseObjA.setVisibility(View.GONE);
                  lineSoapInHouseObjA.setVisibility(View.GONE);
                  chkSoapInHouseObjA.setChecked(false);
                  secSoapInHouseObjB.setVisibility(View.GONE);
                  lineSoapInHouseObjB.setVisibility(View.GONE);
                  chkSoapInHouseObjB.setChecked(false);
                  secSoapInHouseObjC.setVisibility(View.GONE);
                  lineSoapInHouseObjC.setVisibility(View.GONE);
                  chkSoapInHouseObjC.setChecked(false);
               }
               else
               {
                  seclblSoapInHouseObj.setVisibility(View.VISIBLE);
                  linelblSoapInHouseObj.setVisibility(View.VISIBLE);
                  secSoapInHouseObjA.setVisibility(View.VISIBLE);
                  lineSoapInHouseObjA.setVisibility(View.VISIBLE);
                  secSoapInHouseObjB.setVisibility(View.VISIBLE);
                  lineSoapInHouseObjB.setVisibility(View.VISIBLE);
                  secSoapInHouseObjC.setVisibility(View.VISIBLE);
                  lineSoapInHouseObjC.setVisibility(View.VISIBLE);
               }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
               return;
            }
         });
         seclblSoapInHouseObj=findViewById(R.id.seclblSoapInHouseObj);
         linelblSoapInHouseObj=findViewById(R.id.linelblSoapInHouseObj);
         secSoapInHouseObjA = findViewById(R.id.secSoapInHouseObjA);
         lineSoapInHouseObjA = findViewById(R.id.lineSoapInHouseObjA);
         lblSoapInHouseObjA = findViewById(R.id.lblSoapInHouseObjA);
         VlblSoapInHouseObjA = findViewById(R.id.VlblSoapInHouseObjA);
         chkSoapInHouseObjA = findViewById(R.id.chkSoapInHouseObjA);
         secSoapInHouseObjB = findViewById(R.id.secSoapInHouseObjB);
         lineSoapInHouseObjB = findViewById(R.id.lineSoapInHouseObjB);
         lblSoapInHouseObjB = findViewById(R.id.lblSoapInHouseObjB);
         VlblSoapInHouseObjB = findViewById(R.id.VlblSoapInHouseObjB);
         chkSoapInHouseObjB = findViewById(R.id.chkSoapInHouseObjB);
         secSoapInHouseObjC = findViewById(R.id.secSoapInHouseObjC);
         lineSoapInHouseObjC = findViewById(R.id.lineSoapInHouseObjC);
         lblSoapInHouseObjC = findViewById(R.id.lblSoapInHouseObjC);
         VlblSoapInHouseObjC = findViewById(R.id.VlblSoapInHouseObjC);
         chkSoapInHouseObjC = findViewById(R.id.chkSoapInHouseObjC);
         secCookDvc = findViewById(R.id.secCookDvc);
         lineCookDvc = findViewById(R.id.lineCookDvc);
         lblCookDvc = findViewById(R.id.lblCookDvc);
         VlblCookDvc = findViewById(R.id.VlblCookDvc);
         spnCookDvc = findViewById(R.id.spnCookDvc);
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
                  if(spnData.equalsIgnoreCase("08") & (spnCookFuel.getSelectedItemPosition() == 0 ? "" : spnCookFuel.getSelectedItem().toString().split("-")[0]).equalsIgnoreCase("18"))
                  {
                     secCookPlc.setVisibility(View.GONE);
                     lineCookPlc.setVisibility(View.GONE);
                     spnCookPlc.setSelection(0);
                  }
                  else
                  {
                     secCookPlc.setVisibility(View.VISIBLE);
                     lineCookPlc.setVisibility(View.VISIBLE);
                  }
                  secCookDvcOth.setVisibility(View.GONE);
                  lineCookDvcOth.setVisibility(View.GONE);
                  txtCookDvcOth.setText("");
               }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
         });
         secCookDvcOth = findViewById(R.id.secCookDvcOth);
         lineCookDvcOth = findViewById(R.id.lineCookDvcOth);
         lblCookDvcOth = findViewById(R.id.lblCookDvcOth);
         VlblCookDvcOth = findViewById(R.id.VlblCookDvcOth);
         txtCookDvcOth = findViewById(R.id.txtCookDvcOth);
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
         secCookFuel = findViewById(R.id.secCookFuel);
         lineCookFuel = findViewById(R.id.lineCookFuel);
         lblCookFuel = findViewById(R.id.lblCookFuel);
         VlblCookFuel = findViewById(R.id.VlblCookFuel);
         spnCookFuel = findViewById(R.id.spnCookFuel);
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
         listCookFuel.add("16-Liquified Petroleum Gas (LPG)");
         listCookFuel.add("17-Biogas (biogaz)");
         listCookFuel.add("18-We do not prepare food in the household");
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
                  if(spnData.equalsIgnoreCase("18") & (spnCookDvc.getSelectedItemPosition() == 0 ? "" : spnCookDvc.getSelectedItem().toString().split("-")[0]).equalsIgnoreCase("08"))
                  {
                     secCookPlc.setVisibility(View.GONE);
                     lineCookPlc.setVisibility(View.GONE);
                     spnCookPlc.setSelection(0);
                  }
                  else
                  {
                     secCookPlc.setVisibility(View.VISIBLE);
                     lineCookPlc.setVisibility(View.VISIBLE);
                  }
                  secCookFuelOth.setVisibility(View.GONE);
                  lineCookFuelOth.setVisibility(View.GONE);
                  txtCookFuelOth.setText("");
               }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
         });
         secCookFuelOth = findViewById(R.id.secCookFuelOth);
         lineCookFuelOth = findViewById(R.id.lineCookFuelOth);
         lblCookFuelOth = findViewById(R.id.lblCookFuelOth);
         VlblCookFuelOth = findViewById(R.id.VlblCookFuelOth);
         txtCookFuelOth = findViewById(R.id.txtCookFuelOth);
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
         secCookPlc = findViewById(R.id.secCookPlc);
         lineCookPlc = findViewById(R.id.lineCookPlc);
         lblCookPlc = findViewById(R.id.lblCookPlc);
         VlblCookPlc = findViewById(R.id.VlblCookPlc);
         spnCookPlc = findViewById(R.id.spnCookPlc);
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
         secCookPlcOth = findViewById(R.id.secCookPlcOth);
         lineCookPlcOth = findViewById(R.id.lineCookPlcOth);
         lblCookPlcOth = findViewById(R.id.lblCookPlcOth);
         VlblCookPlcOth = findViewById(R.id.VlblCookPlcOth);
         txtCookPlcOth = findViewById(R.id.txtCookPlcOth);
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
         secFloor = findViewById(R.id.secFloor);
         lineFloor = findViewById(R.id.lineFloor);
         lblFloor = findViewById(R.id.lblFloor);
         VlblFloor = findViewById(R.id.VlblFloor);
         spnFloor = findViewById(R.id.spnFloor);
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
         listFloor.add("35-Finished floor: Rug carpet");
         listFloor.add("36-Finished floor: Rubber carpet");
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
         secFloorOth = findViewById(R.id.secFloorOth);
         lineFloorOth = findViewById(R.id.lineFloorOth);
         lblFloorOth = findViewById(R.id.lblFloorOth);
         VlblFloorOth = findViewById(R.id.VlblFloorOth);
         txtFloorOth = findViewById(R.id.txtFloorOth);
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
         secRoof = findViewById(R.id.secRoof);
         lineRoof = findViewById(R.id.lineRoof);
         lblRoof = findViewById(R.id.lblRoof);
         VlblRoof = findViewById(R.id.VlblRoof);
         spnRoof = findViewById(R.id.spnRoof);
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
         secRoofOth = findViewById(R.id.secRoofOth);
         lineRoofOth = findViewById(R.id.lineRoofOth);
         lblRoofOth = findViewById(R.id.lblRoofOth);
         VlblRoofOth = findViewById(R.id.VlblRoofOth);
         txtRoofOth = findViewById(R.id.txtRoofOth);
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
         secWall = findViewById(R.id.secWall);
         lineWall = findViewById(R.id.lineWall);
         lblWall = findViewById(R.id.lblWall);
         VlblWall = findViewById(R.id.VlblWall);
         spnWall = findViewById(R.id.spnWall);
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
         secWallOth = findViewById(R.id.secWallOth);
         lineWallOth = findViewById(R.id.lineWallOth);
         lblWallOth = findViewById(R.id.lblWallOth);
         VlblWallOth = findViewById(R.id.VlblWallOth);
         txtWallOth = findViewById(R.id.txtWallOth);
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
         secRoomSleep = findViewById(R.id.secRoomSleep);
         lineRoomSleep = findViewById(R.id.lineRoomSleep);
         lblRoomSleep = findViewById(R.id.lblRoomSleep);
         VlblRoomSleep = findViewById(R.id.VlblRoomSleep);
         txtRoomSleep = findViewById(R.id.txtRoomSleep);
         txtRoomSleep.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
               if(txtRoomSleep.getText().toString().length()>0) rdogrpRoomSleepDk.clearCheck();
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
         });

         secRoomSleepDk = findViewById(R.id.secRoomSleepDk);
         lineRoomSleepDk = findViewById(R.id.lineRoomSleepDk);
         lblRoomSleepDk =  findViewById(R.id.lblRoomSleepDk);
         VlblRoomSleepDk =  findViewById(R.id.VlblRoomSleepDk);
         rdogrpRoomSleepDk =  findViewById(R.id.rdogrpRoomSleepDk);
         rdoRoomSleepDk1 =  findViewById(R.id.rdoRoomSleepDk1);
         rdoRoomSleepDk2 =  findViewById(R.id.rdoRoomSleepDk2);
         rdogrpRoomSleepDk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
         {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
               if (rdoRoomSleepDk1.isChecked() || rdoRoomSleepDk2.isChecked())
               {
                  txtRoomSleep.setText("");
               }
            }
         });


         secHomesteadAny = findViewById(R.id.secHomesteadAny);
         lineHomesteadAny = findViewById(R.id.lineHomesteadAny);
         lblHomesteadAny =  findViewById(R.id.lblHomesteadAny);
         VlblHomesteadAny =  findViewById(R.id.VlblHomesteadAny);
         rdogrpHomesteadAny =  findViewById(R.id.rdogrpHomesteadAny);
         rdoHomesteadAny1 =  findViewById(R.id.rdoHomesteadAny1);
         rdoHomesteadAny2 =  findViewById(R.id.rdoHomesteadAny2);
         rdoHomesteadAny3 =  findViewById(R.id.rdoHomesteadAny3);
         rdoHomesteadAny4 =  findViewById(R.id.rdoHomesteadAny4);
         secOthLand = findViewById(R.id.secOthLand);
         lineOthLand = findViewById(R.id.lineOthLand);
         lblOthLand =  findViewById(R.id.lblOthLand);
         VlblOthLand =  findViewById(R.id.VlblOthLand);
         rdogrpOthLand =  findViewById(R.id.rdogrpOthLand);
         rdoOthLand1 =  findViewById(R.id.rdoOthLand1);
         rdoOthLand2 =  findViewById(R.id.rdoOthLand2);
         rdoOthLand3 =  findViewById(R.id.rdoOthLand3);
         rdoOthLand4 =  findViewById(R.id.rdoOthLand4);
         seclblH12=findViewById(R.id.seclblH12);
         linelblH12=findViewById(R.id.linelblH12);
         seclblH121=findViewById(R.id.seclblH121);
         linelblH121=findViewById(R.id.linelblH121);
         secElectricity = findViewById(R.id.secElectricity);
         lineElectricity = findViewById(R.id.lineElectricity);
         lblElectricity =  findViewById(R.id.lblElectricity);
         VlblElectricity =  findViewById(R.id.VlblElectricity);
         rdogrpElectricity =  findViewById(R.id.rdogrpElectricity);
         rdoElectricity1 =  findViewById(R.id.rdoElectricity1);
         rdoElectricity2 =  findViewById(R.id.rdoElectricity2);
         secHeater = findViewById(R.id.secHeater);
         lineHeater = findViewById(R.id.lineHeater);
         lblHeater =  findViewById(R.id.lblHeater);
         VlblHeater =  findViewById(R.id.VlblHeater);
         rdogrpHeater =  findViewById(R.id.rdogrpHeater);
         rdoHeater1 =  findViewById(R.id.rdoHeater1);
         rdoHeater2 =  findViewById(R.id.rdoHeater2);
         secAC = findViewById(R.id.secAC);
         lineAC = findViewById(R.id.lineAC);
         lblAC =  findViewById(R.id.lblAC);
         VlblAC =  findViewById(R.id.VlblAC);
         rdogrpAC =  findViewById(R.id.rdogrpAC);
         rdoAC1 =  findViewById(R.id.rdoAC1);
         rdoAC2 =  findViewById(R.id.rdoAC2);
         secElecFan = findViewById(R.id.secElecFan);
         lineElecFan = findViewById(R.id.lineElecFan);
         lblElecFan =  findViewById(R.id.lblElecFan);
         VlblElecFan =  findViewById(R.id.VlblElecFan);
         rdogrpElecFan =  findViewById(R.id.rdogrpElecFan);
         rdoElecFan1 =  findViewById(R.id.rdoElecFan1);
         rdoElecFan2 =  findViewById(R.id.rdoElecFan2);
         secLantern = findViewById(R.id.secLantern);
         lineLantern = findViewById(R.id.lineLantern);
         lblLantern =  findViewById(R.id.lblLantern);
         VlblLantern =  findViewById(R.id.VlblLantern);
         rdogrpLantern =  findViewById(R.id.rdogrpLantern);
         rdoLantern1 =  findViewById(R.id.rdoLantern1);
         rdoLantern2 =  findViewById(R.id.rdoLantern2);
         secLamp = findViewById(R.id.secLamp);
         lineLamp = findViewById(R.id.lineLamp);
         lblLamp =  findViewById(R.id.lblLamp);
         VlblLamp =  findViewById(R.id.VlblLamp);
         rdogrpLamp =  findViewById(R.id.rdogrpLamp);
         rdoLamp1 =  findViewById(R.id.rdoLamp1);
         rdoLamp2 =  findViewById(R.id.rdoLamp2);
         secGasLamp = findViewById(R.id.secGasLamp);
         lineGasLamp = findViewById(R.id.lineGasLamp);
         lblGasLamp =  findViewById(R.id.lblGasLamp);
         VlblGasLamp =  findViewById(R.id.VlblGasLamp);
         rdogrpGasLamp =  findViewById(R.id.rdogrpGasLamp);
         rdoGasLamp1 =  findViewById(R.id.rdoGasLamp1);
         rdoGasLamp2 =  findViewById(R.id.rdoGasLamp2);
         secPetroleum = findViewById(R.id.secPetroleum);
         linePetroleum = findViewById(R.id.linePetroleum);
         lblPetroleum =  findViewById(R.id.lblPetroleum);
         VlblPetroleum =  findViewById(R.id.VlblPetroleum);
         rdogrpPetroleum =  findViewById(R.id.rdogrpPetroleum);
         rdoPetroleum1 =  findViewById(R.id.rdoPetroleum1);
         rdoPetroleum2 =  findViewById(R.id.rdoPetroleum2);
         secMatt = findViewById(R.id.secMatt);
         lineMatt = findViewById(R.id.lineMatt);
         lblMatt =  findViewById(R.id.lblMatt);
         VlblMatt =  findViewById(R.id.VlblMatt);
         rdogrpMatt =  findViewById(R.id.rdogrpMatt);
         rdoMatt1 =  findViewById(R.id.rdoMatt1);
         rdoMatt2 =  findViewById(R.id.rdoMatt2);
         secMats = findViewById(R.id.secMats);
         lineMats = findViewById(R.id.lineMats);
         lblMats =  findViewById(R.id.lblMats);
         VlblMats =  findViewById(R.id.VlblMats);
         rdogrpMats =  findViewById(R.id.rdogrpMats);
         rdoMats1 =  findViewById(R.id.rdoMats1);
         rdoMats2 =  findViewById(R.id.rdoMats2);
         secCarpets = findViewById(R.id.secCarpets);
         lineCarpets = findViewById(R.id.lineCarpets);
         lblCarpets =  findViewById(R.id.lblCarpets);
         VlblCarpets =  findViewById(R.id.VlblCarpets);
         rdogrpCarpets =  findViewById(R.id.rdogrpCarpets);
         rdoCarpets1 =  findViewById(R.id.rdoCarpets1);
         rdoCarpets2 =  findViewById(R.id.rdoCarpets2);
         secBed = findViewById(R.id.secBed);
         lineBed = findViewById(R.id.lineBed);
         lblBed =  findViewById(R.id.lblBed);
         VlblBed =  findViewById(R.id.VlblBed);
         rdogrpBed =  findViewById(R.id.rdogrpBed);
         rdoBed1 =  findViewById(R.id.rdoBed1);
         rdoBed2 =  findViewById(R.id.rdoBed2);
         secChair = findViewById(R.id.secChair);
         lineChair = findViewById(R.id.lineChair);
         lblChair =  findViewById(R.id.lblChair);
         VlblChair =  findViewById(R.id.VlblChair);
         rdogrpChair =  findViewById(R.id.rdogrpChair);
         rdoChair1 =  findViewById(R.id.rdoChair1);
         rdoChair2 =  findViewById(R.id.rdoChair2);
         secSofa = findViewById(R.id.secSofa);
         lineSofa = findViewById(R.id.lineSofa);
         lblSofa =  findViewById(R.id.lblSofa);
         VlblSofa =  findViewById(R.id.VlblSofa);
         rdogrpSofa =  findViewById(R.id.rdogrpSofa);
         rdoSofa1 =  findViewById(R.id.rdoSofa1);
         rdoSofa2 =  findViewById(R.id.rdoSofa2);
         secTables = findViewById(R.id.secTables);
         lineTables = findViewById(R.id.lineTables);
         lblTables =  findViewById(R.id.lblTables);
         VlblTables =  findViewById(R.id.VlblTables);
         rdogrpTables =  findViewById(R.id.rdogrpTables);
         rdoTables1 =  findViewById(R.id.rdoTables1);
         rdoTables2 =  findViewById(R.id.rdoTables2);
         secWatch = findViewById(R.id.secWatch);
         lineWatch = findViewById(R.id.lineWatch);
         lblWatch =  findViewById(R.id.lblWatch);
         VlblWatch =  findViewById(R.id.VlblWatch);
         rdogrpWatch =  findViewById(R.id.rdogrpWatch);
         rdoWatch1 =  findViewById(R.id.rdoWatch1);
         rdoWatch2 =  findViewById(R.id.rdoWatch2);
         secWMachine = findViewById(R.id.secWMachine);
         lineWMachine = findViewById(R.id.lineWMachine);
         lblWMachine =  findViewById(R.id.lblWMachine);
         VlblWMachine =  findViewById(R.id.VlblWMachine);
         rdogrpWMachine =  findViewById(R.id.rdogrpWMachine);
         rdoWMachine1 =  findViewById(R.id.rdoWMachine1);
         rdoWMachine2 =  findViewById(R.id.rdoWMachine2);
         secIron = findViewById(R.id.secIron);
         lineIron = findViewById(R.id.lineIron);
         lblIron =  findViewById(R.id.lblIron);
         VlblIron =  findViewById(R.id.VlblIron);
         rdogrpIron =  findViewById(R.id.rdogrpIron);
         rdoIron1 =  findViewById(R.id.rdoIron1);
         rdoIron2 =  findViewById(R.id.rdoIron2);
         secBooth = findViewById(R.id.secBooth);
         lineBooth = findViewById(R.id.lineBooth);
         lblBooth =  findViewById(R.id.lblBooth);
         VlblBooth =  findViewById(R.id.VlblBooth);
         rdogrpBooth =  findViewById(R.id.rdogrpBooth);
         rdoBooth1 =  findViewById(R.id.rdoBooth1);
         rdoBooth2 =  findViewById(R.id.rdoBooth2);
         secSMachine = findViewById(R.id.secSMachine);
         lineSMachine = findViewById(R.id.lineSMachine);
         lblSMachine =  findViewById(R.id.lblSMachine);
         VlblSMachine =  findViewById(R.id.VlblSMachine);
         rdogrpSMachine =  findViewById(R.id.rdogrpSMachine);
         rdoSMachine1 =  findViewById(R.id.rdoSMachine1);
         rdoSMachine2 =  findViewById(R.id.rdoSMachine2);
         secGenerator = findViewById(R.id.secGenerator);
         lineGenerator = findViewById(R.id.lineGenerator);
         lblGenerator =  findViewById(R.id.lblGenerator);
         VlblGenerator =  findViewById(R.id.VlblGenerator);
         rdogrpGenerator =  findViewById(R.id.rdogrpGenerator);
         rdoGenerator1 =  findViewById(R.id.rdoGenerator1);
         rdoGenerator2 =  findViewById(R.id.rdoGenerator2);
         seclblH12a=findViewById(R.id.seclblH12a);
         linelblH12a=findViewById(R.id.linelblH12a);
         secInternet = findViewById(R.id.secInternet);
         lineInternet = findViewById(R.id.lineInternet);
         lblInternet =  findViewById(R.id.lblInternet);
         VlblInternet =  findViewById(R.id.VlblInternet);
         rdogrpInternet =  findViewById(R.id.rdogrpInternet);
         rdoInternet1 =  findViewById(R.id.rdoInternet1);
         rdoInternet2 =  findViewById(R.id.rdoInternet2);
         secSatellite = findViewById(R.id.secSatellite);
         lineSatellite = findViewById(R.id.lineSatellite);
         lblSatellite =  findViewById(R.id.lblSatellite);
         VlblSatellite =  findViewById(R.id.VlblSatellite);
         rdogrpSatellite =  findViewById(R.id.rdogrpSatellite);
         rdoSatellite1 =  findViewById(R.id.rdoSatellite1);
         rdoSatellite2 =  findViewById(R.id.rdoSatellite2);
         secLandline = findViewById(R.id.secLandline);
         lineLandline = findViewById(R.id.lineLandline);
         lblLandline =  findViewById(R.id.lblLandline);
         VlblLandline =  findViewById(R.id.VlblLandline);
         rdogrpLandline =  findViewById(R.id.rdogrpLandline);
         rdoLandline1 =  findViewById(R.id.rdoLandline1);
         rdoLandline2 =  findViewById(R.id.rdoLandline2);
         secCellphone = findViewById(R.id.secCellphone);
         lineCellphone = findViewById(R.id.lineCellphone);
         lblCellphone =  findViewById(R.id.lblCellphone);
         VlblCellphone =  findViewById(R.id.VlblCellphone);
         rdogrpCellphone =  findViewById(R.id.rdogrpCellphone);
         rdoCellphone1 =  findViewById(R.id.rdoCellphone1);
         rdoCellphone2 =  findViewById(R.id.rdoCellphone2);
         secTV = findViewById(R.id.secTV);
         lineTV = findViewById(R.id.lineTV);
         lblTV =  findViewById(R.id.lblTV);
         VlblTV =  findViewById(R.id.VlblTV);
         rdogrpTV =  findViewById(R.id.rdogrpTV);
         rdoTV1 =  findViewById(R.id.rdoTV1);
         rdoTV2 =  findViewById(R.id.rdoTV2);
         secTV5 = findViewById(R.id.secTV5);
         lineTV5 = findViewById(R.id.lineTV5);
         lblTV5 =  findViewById(R.id.lblTV5);
         VlblTV5 =  findViewById(R.id.VlblTV5);
         rdogrpTV5 =  findViewById(R.id.rdogrpTV5);
         rdoTV51 =  findViewById(R.id.rdoTV51);
         rdoTV52 =  findViewById(R.id.rdoTV52);
         secChannel = findViewById(R.id.secChannel);
         lineChannel = findViewById(R.id.lineChannel);
         lblChannel =  findViewById(R.id.lblChannel);
         VlblChannel =  findViewById(R.id.VlblChannel);
         rdogrpChannel =  findViewById(R.id.rdogrpChannel);
         rdoChannel1 =  findViewById(R.id.rdoChannel1);
         rdoChannel2 =  findViewById(R.id.rdoChannel2);
         secRadio = findViewById(R.id.secRadio);
         lineRadio = findViewById(R.id.lineRadio);
         lblRadio =  findViewById(R.id.lblRadio);
         VlblRadio =  findViewById(R.id.VlblRadio);
         rdogrpRadio =  findViewById(R.id.rdogrpRadio);
         rdoRadio1 =  findViewById(R.id.rdoRadio1);
         rdoRadio2 =  findViewById(R.id.rdoRadio2);
         secDVD = findViewById(R.id.secDVD);
         lineDVD = findViewById(R.id.lineDVD);
         lblDVD =  findViewById(R.id.lblDVD);
         VlblDVD =  findViewById(R.id.VlblDVD);
         rdogrpDVD =  findViewById(R.id.rdogrpDVD);
         rdoDVD1 =  findViewById(R.id.rdoDVD1);
         rdoDVD2 =  findViewById(R.id.rdoDVD2);
         secVideo = findViewById(R.id.secVideo);
         lineVideo = findViewById(R.id.lineVideo);
         lblVideo =  findViewById(R.id.lblVideo);
         VlblVideo =  findViewById(R.id.VlblVideo);
         rdogrpVideo =  findViewById(R.id.rdogrpVideo);
         rdoVideo1 =  findViewById(R.id.rdoVideo1);
         rdoVideo2 =  findViewById(R.id.rdoVideo2);
         secComputer = findViewById(R.id.secComputer);
         lineComputer = findViewById(R.id.lineComputer);
         lblComputer =  findViewById(R.id.lblComputer);
         VlblComputer =  findViewById(R.id.VlblComputer);
         rdogrpComputer =  findViewById(R.id.rdogrpComputer);
         rdoComputer1 =  findViewById(R.id.rdoComputer1);
         rdoComputer2 =  findViewById(R.id.rdoComputer2);
         secLaptop = findViewById(R.id.secLaptop);
         lineLaptop = findViewById(R.id.lineLaptop);
         lblLaptop =  findViewById(R.id.lblLaptop);
         VlblLaptop =  findViewById(R.id.VlblLaptop);
         rdogrpLaptop =  findViewById(R.id.rdogrpLaptop);
         rdoLaptop1 =  findViewById(R.id.rdoLaptop1);
         rdoLaptop2 =  findViewById(R.id.rdoLaptop2);
         secCable = findViewById(R.id.secCable);
         lineCable = findViewById(R.id.lineCable);
         lblCable =  findViewById(R.id.lblCable);
         VlblCable =  findViewById(R.id.VlblCable);
         rdogrpCable =  findViewById(R.id.rdogrpCable);
         rdoCable1 =  findViewById(R.id.rdoCable1);
         rdoCable2 =  findViewById(R.id.rdoCable2);
         seclblH12b=findViewById(R.id.seclblH12b);
         linelblH12b=findViewById(R.id.linelblH12b);
         secMicrowave = findViewById(R.id.secMicrowave);
         lineMicrowave = findViewById(R.id.lineMicrowave);
         lblMicrowave =  findViewById(R.id.lblMicrowave);
         VlblMicrowave =  findViewById(R.id.VlblMicrowave);
         rdogrpMicrowave =  findViewById(R.id.rdogrpMicrowave);
         rdoMicrowave1 =  findViewById(R.id.rdoMicrowave1);
         rdoMicrowave2 =  findViewById(R.id.rdoMicrowave2);
         secGeyser = findViewById(R.id.secGeyser);
         lineGeyser = findViewById(R.id.lineGeyser);
         lblGeyser =  findViewById(R.id.lblGeyser);
         VlblGeyser =  findViewById(R.id.VlblGeyser);
         rdogrpGeyser =  findViewById(R.id.rdogrpGeyser);
         rdoGeyser1 =  findViewById(R.id.rdoGeyser1);
         rdoGeyser2 =  findViewById(R.id.rdoGeyser2);
         secGrill = findViewById(R.id.secGrill);
         lineGrill = findViewById(R.id.lineGrill);
         lblGrill =  findViewById(R.id.lblGrill);
         VlblGrill =  findViewById(R.id.VlblGrill);
         rdogrpGrill =  findViewById(R.id.rdogrpGrill);
         rdoGrill1 =  findViewById(R.id.rdoGrill1);
         rdoGrill2 =  findViewById(R.id.rdoGrill2);
         secGrain = findViewById(R.id.secGrain);
         lineGrain = findViewById(R.id.lineGrain);
         lblGrain =  findViewById(R.id.lblGrain);
         VlblGrain =  findViewById(R.id.VlblGrain);
         rdogrpGrain =  findViewById(R.id.rdogrpGrain);
         rdoGrain1 =  findViewById(R.id.rdoGrain1);
         rdoGrain2 =  findViewById(R.id.rdoGrain2);
         secRefrigerator = findViewById(R.id.secRefrigerator);
         lineRefrigerator = findViewById(R.id.lineRefrigerator);
         lblRefrigerator =  findViewById(R.id.lblRefrigerator);
         VlblRefrigerator =  findViewById(R.id.VlblRefrigerator);
         rdogrpRefrigerator =  findViewById(R.id.rdogrpRefrigerator);
         rdoRefrigerator1 =  findViewById(R.id.rdoRefrigerator1);
         rdoRefrigerator2 =  findViewById(R.id.rdoRefrigerator2);
         secDeepFreezer = findViewById(R.id.secDeepFreezer);
         lineDeepFreezer = findViewById(R.id.lineDeepFreezer);
         lblDeepFreezer =  findViewById(R.id.lblDeepFreezer);
         VlblDeepFreezer =  findViewById(R.id.VlblDeepFreezer);
         rdogrpDeepFreezer =  findViewById(R.id.rdogrpDeepFreezer);
         rdoDeepFreezer1 =  findViewById(R.id.rdoDeepFreezer1);
         rdoDeepFreezer2 =  findViewById(R.id.rdoDeepFreezer2);
         secStove = findViewById(R.id.secStove);
         lineStove = findViewById(R.id.lineStove);
         lblStove =  findViewById(R.id.lblStove);
         VlblStove =  findViewById(R.id.VlblStove);
         rdogrpStove =  findViewById(R.id.rdogrpStove);
         rdoStove1 =  findViewById(R.id.rdoStove1);
         rdoStove2 =  findViewById(R.id.rdoStove2);
         secGasHob = findViewById(R.id.secGasHob);
         lineGasHob = findViewById(R.id.lineGasHob);
         lblGasHob =  findViewById(R.id.lblGasHob);
         VlblGasHob =  findViewById(R.id.VlblGasHob);
         rdogrpGasHob =  findViewById(R.id.rdogrpGasHob);
         rdoGasHob1 =  findViewById(R.id.rdoGasHob1);
         rdoGasHob2 =  findViewById(R.id.rdoGasHob2);
         secImpCooker = findViewById(R.id.secImpCooker);
         lineImpCooker = findViewById(R.id.lineImpCooker);
         lblImpCooker =  findViewById(R.id.lblImpCooker);
         VlblImpCooker =  findViewById(R.id.VlblImpCooker);
         rdogrpImpCooker =  findViewById(R.id.rdogrpImpCooker);
         rdoImpCooker1 =  findViewById(R.id.rdoImpCooker1);
         rdoImpCooker2 =  findViewById(R.id.rdoImpCooker2);
         seclblH12c=findViewById(R.id.seclblH12c);
         linelblH12c=findViewById(R.id.linelblH12c);
         secBike = findViewById(R.id.secBike);
         lineBike = findViewById(R.id.lineBike);
         lblBike =  findViewById(R.id.lblBike);
         VlblBike =  findViewById(R.id.VlblBike);
         rdogrpBike =  findViewById(R.id.rdogrpBike);
         rdoBike1 =  findViewById(R.id.rdoBike1);
         rdoBike2 =  findViewById(R.id.rdoBike2);
         secMotorcycle = findViewById(R.id.secMotorcycle);
         lineMotorcycle = findViewById(R.id.lineMotorcycle);
         lblMotorcycle =  findViewById(R.id.lblMotorcycle);
         VlblMotorcycle =  findViewById(R.id.VlblMotorcycle);
         rdogrpMotorcycle =  findViewById(R.id.rdogrpMotorcycle);
         rdoMotorcycle1 =  findViewById(R.id.rdoMotorcycle1);
         rdoMotorcycle2 =  findViewById(R.id.rdoMotorcycle2);
         secCar = findViewById(R.id.secCar);
         lineCar = findViewById(R.id.lineCar);
         lblCar =  findViewById(R.id.lblCar);
         VlblCar =  findViewById(R.id.VlblCar);
         rdogrpCar =  findViewById(R.id.rdogrpCar);
         rdoCar1 =  findViewById(R.id.rdoCar1);
         rdoCar2 =  findViewById(R.id.rdoCar2);
         secRickshaw = findViewById(R.id.secRickshaw);
         lineRickshaw = findViewById(R.id.lineRickshaw);
         lblRickshaw =  findViewById(R.id.lblRickshaw);
         VlblRickshaw =  findViewById(R.id.VlblRickshaw);
         rdogrpRickshaw =  findViewById(R.id.rdogrpRickshaw);
         rdoRickshaw1 =  findViewById(R.id.rdoRickshaw1);
         rdoRickshaw2 =  findViewById(R.id.rdoRickshaw2);
         secCart = findViewById(R.id.secCart);
         lineCart = findViewById(R.id.lineCart);
         lblCart =  findViewById(R.id.lblCart);
         VlblCart =  findViewById(R.id.VlblCart);
         rdogrpCart =  findViewById(R.id.rdogrpCart);
         rdoCart1 =  findViewById(R.id.rdoCart1);
         rdoCart2 =  findViewById(R.id.rdoCart2);
         secCanoe = findViewById(R.id.secCanoe);
         lineCanoe = findViewById(R.id.lineCanoe);
         lblCanoe =  findViewById(R.id.lblCanoe);
         VlblCanoe =  findViewById(R.id.VlblCanoe);
         rdogrpCanoe =  findViewById(R.id.rdogrpCanoe);
         rdoCanoe1 =  findViewById(R.id.rdoCanoe1);
         rdoCanoe2 =  findViewById(R.id.rdoCanoe2);
         secBus = findViewById(R.id.secBus);
         lineBus = findViewById(R.id.lineBus);
         lblBus =  findViewById(R.id.lblBus);
         VlblBus =  findViewById(R.id.VlblBus);
         rdogrpBus =  findViewById(R.id.rdogrpBus);
         rdoBus1 =  findViewById(R.id.rdoBus1);
         rdoBus2 =  findViewById(R.id.rdoBus2);
         seclblH12d=findViewById(R.id.seclblH12d);
         linelblH12d=findViewById(R.id.linelblH12d);
         secTractor = findViewById(R.id.secTractor);
         lineTractor = findViewById(R.id.lineTractor);
         lblTractor =  findViewById(R.id.lblTractor);
         VlblTractor =  findViewById(R.id.VlblTractor);
         rdogrpTractor =  findViewById(R.id.rdogrpTractor);
         rdoTractor1 =  findViewById(R.id.rdoTractor1);
         rdoTractor2 =  findViewById(R.id.rdoTractor2);
         secPlow = findViewById(R.id.secPlow);
         linePlow = findViewById(R.id.linePlow);
         lblPlow =  findViewById(R.id.lblPlow);
         VlblPlow =  findViewById(R.id.VlblPlow);
         rdogrpPlow =  findViewById(R.id.rdogrpPlow);
         rdoPlow1 =  findViewById(R.id.rdoPlow1);
         rdoPlow2 =  findViewById(R.id.rdoPlow2);
         secDuck = findViewById(R.id.secDuck);
         lineDuck = findViewById(R.id.lineDuck);
         lblDuck =  findViewById(R.id.lblDuck);
         VlblDuck =  findViewById(R.id.VlblDuck);
         rdogrpDuck =  findViewById(R.id.rdogrpDuck);
         rdoDuck1 =  findViewById(R.id.rdoDuck1);
         rdoDuck2 =  findViewById(R.id.rdoDuck2);
         secCow = findViewById(R.id.secCow);
         lineCow = findViewById(R.id.lineCow);
         lblCow =  findViewById(R.id.lblCow);
         VlblCow =  findViewById(R.id.VlblCow);
         rdogrpCow =  findViewById(R.id.rdogrpCow);
         rdoCow1 =  findViewById(R.id.rdoCow1);
         rdoCow2 =  findViewById(R.id.rdoCow2);
         secSheep = findViewById(R.id.secSheep);
         lineSheep = findViewById(R.id.lineSheep);
         lblSheep =  findViewById(R.id.lblSheep);
         VlblSheep =  findViewById(R.id.VlblSheep);
         rdogrpSheep =  findViewById(R.id.rdogrpSheep);
         rdoSheep1 =  findViewById(R.id.rdoSheep1);
         rdoSheep2 =  findViewById(R.id.rdoSheep2);
         secGoat = findViewById(R.id.secGoat);
         lineGoat = findViewById(R.id.lineGoat);
         lblGoat =  findViewById(R.id.lblGoat);
         VlblGoat =  findViewById(R.id.VlblGoat);
         rdogrpGoat =  findViewById(R.id.rdogrpGoat);
         rdoGoat1 =  findViewById(R.id.rdoGoat1);
         rdoGoat2 =  findViewById(R.id.rdoGoat2);
         secChicken = findViewById(R.id.secChicken);
         lineChicken = findViewById(R.id.lineChicken);
         lblChicken =  findViewById(R.id.lblChicken);
         VlblChicken =  findViewById(R.id.VlblChicken);
         rdogrpChicken =  findViewById(R.id.rdogrpChicken);
         rdoChicken1 =  findViewById(R.id.rdoChicken1);
         rdoChicken2 =  findViewById(R.id.rdoChicken2);
         secDonkey = findViewById(R.id.secDonkey);
         lineDonkey = findViewById(R.id.lineDonkey);
         lblDonkey =  findViewById(R.id.lblDonkey);
         VlblDonkey =  findViewById(R.id.VlblDonkey);
         rdogrpDonkey =  findViewById(R.id.rdogrpDonkey);
         rdoDonkey1 =  findViewById(R.id.rdoDonkey1);
         rdoDonkey2 =  findViewById(R.id.rdoDonkey2);
         secHorse = findViewById(R.id.secHorse);
         lineHorse = findViewById(R.id.lineHorse);
         lblHorse =  findViewById(R.id.lblHorse);
         VlblHorse =  findViewById(R.id.VlblHorse);
         rdogrpHorse =  findViewById(R.id.rdogrpHorse);
         rdoHorse1 =  findViewById(R.id.rdoHorse1);
         rdoHorse2 =  findViewById(R.id.rdoHorse2);
         secPig = findViewById(R.id.secPig);
         linePig = findViewById(R.id.linePig);
         lblPig =  findViewById(R.id.lblPig);
         VlblPig =  findViewById(R.id.VlblPig);
         rdogrpPig =  findViewById(R.id.rdogrpPig);
         rdoPig1 =  findViewById(R.id.rdoPig1);
         rdoPig2 =  findViewById(R.id.rdoPig2);
         secBirds = findViewById(R.id.secBirds);
         lineBirds = findViewById(R.id.lineBirds);
         lblBirds =  findViewById(R.id.lblBirds);
         VlblBirds =  findViewById(R.id.VlblBirds);
         rdogrpBirds =  findViewById(R.id.rdogrpBirds);
         rdoBirds1 =  findViewById(R.id.rdoBirds1);
         rdoBirds2 =  findViewById(R.id.rdoBirds2);
         secDogs = findViewById(R.id.secDogs);
         lineDogs = findViewById(R.id.lineDogs);
         lblDogs =  findViewById(R.id.lblDogs);
         VlblDogs =  findViewById(R.id.VlblDogs);
         rdogrpDogs =  findViewById(R.id.rdogrpDogs);
         rdoDogs1 =  findViewById(R.id.rdoDogs1);
         rdoDogs2 =  findViewById(R.id.rdoDogs2);
         secCats = findViewById(R.id.secCats);
         lineCats = findViewById(R.id.lineCats);
         lblCats =  findViewById(R.id.lblCats);
         VlblCats =  findViewById(R.id.VlblCats);
         rdogrpCats =  findViewById(R.id.rdogrpCats);
         rdoCats1 =  findViewById(R.id.rdoCats1);
         rdoCats2 =  findViewById(R.id.rdoCats2);
         secFishNet = findViewById(R.id.secFishNet);
         lineFishNet = findViewById(R.id.lineFishNet);
         lblFishNet =  findViewById(R.id.lblFishNet);
         VlblFishNet =  findViewById(R.id.VlblFishNet);
         rdogrpFishNet =  findViewById(R.id.rdogrpFishNet);
         rdoFishNet1 =  findViewById(R.id.rdoFishNet1);
         rdoFishNet2 =  findViewById(R.id.rdoFishNet2);
         secOtherAsset = findViewById(R.id.secOtherAsset);
         lineOtherAsset = findViewById(R.id.lineOtherAsset);
         lblOtherAsset =  findViewById(R.id.lblOtherAsset);
         VlblOtherAsset =  findViewById(R.id.VlblOtherAsset);
         rdogrpOtherAsset =  findViewById(R.id.rdogrpOtherAsset);
         rdoOtherAsset1 =  findViewById(R.id.rdoOtherAsset1);
         rdoOtherAsset2 =  findViewById(R.id.rdoOtherAsset2);
         rdogrpOtherAsset.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
               String rbData = "";
               RadioButton rb;
               String[] d_rdogrpOtherAsset = new String[] {"0","1"};
               for (int i = 0; i < rdogrpOtherAsset.getChildCount(); i++)
               {
                  rb = (RadioButton) rdogrpOtherAsset.getChildAt(i);
                  if (rb.isChecked()) rbData = d_rdogrpOtherAsset[i];
               }

               if(rbData.equalsIgnoreCase("0"))
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
               else
               {
                  secOtherAsset1.setVisibility(View.VISIBLE);
                  lineOtherAsset1.setVisibility(View.VISIBLE);
                  //secOtherAsset1N.setVisibility(View.VISIBLE);
                  //lineOtherAsset1N.setVisibility(View.VISIBLE);
                  secOtherAsset2.setVisibility(View.VISIBLE);
                  lineOtherAsset2.setVisibility(View.VISIBLE);
                  //secOtherAsset2N.setVisibility(View.VISIBLE);
                  //lineOtherAsset2N.setVisibility(View.VISIBLE);
                  secOtherAsset3.setVisibility(View.VISIBLE);
                  lineOtherAsset3.setVisibility(View.VISIBLE);
                  //secOtherAsset3N.setVisibility(View.VISIBLE);
                  //lineOtherAsset3N.setVisibility(View.VISIBLE);
               }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
               return;
            }
         });
         secOtherAsset1 = findViewById(R.id.secOtherAsset1);
         lineOtherAsset1 = findViewById(R.id.lineOtherAsset1);
         lblOtherAsset1 = findViewById(R.id.lblOtherAsset1);
         VlblOtherAsset1 = findViewById(R.id.VlblOtherAsset1);
         txtOtherAsset1 = findViewById(R.id.txtOtherAsset1);
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
         secOtherAsset2 = findViewById(R.id.secOtherAsset2);
         lineOtherAsset2 = findViewById(R.id.lineOtherAsset2);
         lblOtherAsset2 = findViewById(R.id.lblOtherAsset2);
         VlblOtherAsset2 = findViewById(R.id.VlblOtherAsset2);
         txtOtherAsset2 = findViewById(R.id.txtOtherAsset2);
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
         secOtherAsset3 = findViewById(R.id.secOtherAsset3);
         lineOtherAsset3 = findViewById(R.id.lineOtherAsset3);
         lblOtherAsset3 = findViewById(R.id.lblOtherAsset3);
         VlblOtherAsset3 = findViewById(R.id.VlblOtherAsset3);
         txtOtherAsset3 = findViewById(R.id.txtOtherAsset3);
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
         secSESNote = findViewById(R.id.secSESNote);
         lineSESNote = findViewById(R.id.lineSESNote);
         lblSESNote = findViewById(R.id.lblSESNote);
         VlblSESNote = findViewById(R.id.VlblSESNote);
         txtSESNote = findViewById(R.id.txtSESNote);
      }
      catch(Exception  e)
      {
         Connection.MessageBox(Surv_SES_CrossRiver.this, e.getMessage());
         return;
      }
   }

   private void DataSave()
   {
      try
      {
         String ValidationMSG = ValidationCheck();
         if(ValidationMSG.length()>0 & rdoSESVStatus1.isChecked())
         {
            Connection.MessageBox(Surv_SES_CrossRiver.this, ValidationMSG);
            return;
         }

         String DV="";
         DV = Global.DateValidate(txtSESVDate.getText().toString());
         if(DV.length()!=0 & secSESVDate.isShown())
         {
            secSESVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            Connection.MessageBox(Surv_SES_CrossRiver.this, "\n"+ lblSESVDate.getText().toString() + " Required field/Not a valid date format: "+ VlblSESVDate.getText().toString());
            return;
         }


         String SQL = "";
         RadioButton rb;

         tmpSES_CrossRiver_DataModel objSave = new tmpSES_CrossRiver_DataModel();
         objSave.setHHID(txtHHID.getText().toString());
         objSave.setSESNo(txtSESNo.getText().toString());
         objSave.setSESVDate(txtSESVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(txtSESVDate.getText().toString()) : txtSESVDate.getText().toString());
         String[] d_rdogrpSESVStatus = new String[] {"1","2","3","9"};
         objSave.setSESVStatus("");
         for (int i = 0; i < rdogrpSESVStatus.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpSESVStatus.getChildAt(i);
            if (rb.isChecked()) objSave.setSESVStatus(d_rdogrpSESVStatus[i]);
         }

         objSave.setSESVStatusOth(txtSESVStatusOth.getText().toString());
         objSave.setRnd(txtRnd.getText().toString());
         objSave.setWSDrink(spnWSDrink.getSelectedItemPosition() == 0 ? "" : spnWSDrink.getSelectedItem().toString().split("-")[0]);
         objSave.setWSDrinkOth(txtWSDrinkOth.getText().toString());
         objSave.setMainWater(spnMainWater.getSelectedItemPosition() == 0 ? "" : spnMainWater.getSelectedItem().toString().split("-")[0]);
         objSave.setMainWaterOth(txtMainWaterOth.getText().toString());
         objSave.setToilet(spnToilet.getSelectedItemPosition() == 0 ? "" : spnToilet.getSelectedItem().toString().split("-")[0]);
         objSave.setToiletOth(txtToiletOth.getText().toString());
         String[] d_rdogrpToiletShrd = new String[] {"0","1","2"};
         objSave.setToiletShrd("");
         for (int i = 0; i < rdogrpToiletShrd.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpToiletShrd.getChildAt(i);
            if (rb.isChecked()) objSave.setToiletShrd(d_rdogrpToiletShrd[i]);
         }

         objSave.setToiletUseNo(txtToiletUseNo.getText().toString());
         String[] d_rdogrpToiletUseNoDk = new String[] {"95","98","99"};
         objSave.setToiletUseNoDk("");
         for (int i = 0; i < rdogrpToiletUseNoDk.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpToiletUseNoDk.getChildAt(i);
            if (rb.isChecked()) objSave.setToiletUseNoDk(d_rdogrpToiletUseNoDk[i]);
         }

         String[] d_rdogrpToiletLoc = new String[] {"1","2","4","3"};
         objSave.setToiletLoc("");
         for (int i = 0; i < rdogrpToiletLoc.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpToiletLoc.getChildAt(i);
            if (rb.isChecked()) objSave.setToiletLoc(d_rdogrpToiletLoc[i]);
         }

         String[] d_rdogrpHandWash = new String[] {"0","1","8"};
         objSave.setHandWash("");
         for (int i = 0; i < rdogrpHandWash.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpHandWash.getChildAt(i);
            if (rb.isChecked()) objSave.setHandWash(d_rdogrpHandWash[i]);
         }

         String[] d_rdogrpShowWash = new String[] {"1","2","3","4","5","6"};
         objSave.setShowWash("");
         for (int i = 0; i < rdogrpShowWash.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpShowWash.getChildAt(i);
            if (rb.isChecked()) objSave.setShowWash(d_rdogrpShowWash[i]);
         }

         String[] d_rdogrpAvailableWat = new String[] {"1","2"};
         objSave.setAvailableWat("");
         for (int i = 0; i < rdogrpAvailableWat.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpAvailableWat.getChildAt(i);
            if (rb.isChecked()) objSave.setAvailableWat(d_rdogrpAvailableWat[i]);
         }

         String[] d_rdogrpAvailableSoap = new String[] {"1","2"};
         objSave.setAvailableSoap("");
         for (int i = 0; i < rdogrpAvailableSoap.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpAvailableSoap.getChildAt(i);
            if (rb.isChecked()) objSave.setAvailableSoap(d_rdogrpAvailableSoap[i]);
         }

         objSave.setHandWashOthMem(spnHandWashOthMem.getSelectedItemPosition() == 0 ? "" : spnHandWashOthMem.getSelectedItem().toString().split("-")[0]);
         objSave.setHandWashOthMemO(txtHandWashOthMemO.getText().toString());
         String[] d_rdogrpSoapInHouse = new String[] {"1","2"};
         objSave.setSoapInHouse("");
         for (int i = 0; i < rdogrpSoapInHouse.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpSoapInHouse.getChildAt(i);
            if (rb.isChecked()) objSave.setSoapInHouse(d_rdogrpSoapInHouse[i]);
         }

         String[] d_rdogrpSoapInHouseShow = new String[] {"1","2"};
         objSave.setSoapInHouseShow("");
         for (int i = 0; i < rdogrpSoapInHouseShow.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpSoapInHouseShow.getChildAt(i);
            if (rb.isChecked()) objSave.setSoapInHouseShow(d_rdogrpSoapInHouseShow[i]);
         }

         objSave.setSoapInHouseObjA((chkSoapInHouseObjA.isChecked() ? "1" : (secSoapInHouseObjA.isShown() ? "2" : "")));
         objSave.setSoapInHouseObjB((chkSoapInHouseObjB.isChecked() ? "1" : (secSoapInHouseObjB.isShown() ? "2" : "")));
         objSave.setSoapInHouseObjC((chkSoapInHouseObjC.isChecked() ? "1" : (secSoapInHouseObjC.isShown() ? "2" : "")));
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
         String[] d_rdogrpRoomSleepDk = new String[] {"8","9"};
         objSave.setRoomSleepDk("");
         for (int i = 0; i < rdogrpRoomSleepDk.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpRoomSleepDk.getChildAt(i);
            if (rb.isChecked()) objSave.setRoomSleepDk(d_rdogrpRoomSleepDk[i]);
         }

         String[] d_rdogrpHomesteadAny = new String[] {"0","1","8","9"};
         objSave.setHomesteadAny("");
         for (int i = 0; i < rdogrpHomesteadAny.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpHomesteadAny.getChildAt(i);
            if (rb.isChecked()) objSave.setHomesteadAny(d_rdogrpHomesteadAny[i]);
         }

         String[] d_rdogrpOthLand = new String[] {"0","1","8","9"};
         objSave.setOthLand("");
         for (int i = 0; i < rdogrpOthLand.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpOthLand.getChildAt(i);
            if (rb.isChecked()) objSave.setOthLand(d_rdogrpOthLand[i]);
         }

         String[] d_rdogrpElectricity = new String[] {"0","1"};
         objSave.setElectricity("");
         for (int i = 0; i < rdogrpElectricity.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpElectricity.getChildAt(i);
            if (rb.isChecked()) objSave.setElectricity(d_rdogrpElectricity[i]);
         }

         String[] d_rdogrpHeater = new String[] {"0","1"};
         objSave.setHeater("");
         for (int i = 0; i < rdogrpHeater.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpHeater.getChildAt(i);
            if (rb.isChecked()) objSave.setHeater(d_rdogrpHeater[i]);
         }

         String[] d_rdogrpAC = new String[] {"0","1"};
         objSave.setAC("");
         for (int i = 0; i < rdogrpAC.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpAC.getChildAt(i);
            if (rb.isChecked()) objSave.setAC(d_rdogrpAC[i]);
         }

         String[] d_rdogrpElecFan = new String[] {"0","1"};
         objSave.setElecFan("");
         for (int i = 0; i < rdogrpElecFan.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpElecFan.getChildAt(i);
            if (rb.isChecked()) objSave.setElecFan(d_rdogrpElecFan[i]);
         }

         String[] d_rdogrpLantern = new String[] {"0","1"};
         objSave.setLantern("");
         for (int i = 0; i < rdogrpLantern.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpLantern.getChildAt(i);
            if (rb.isChecked()) objSave.setLantern(d_rdogrpLantern[i]);
         }

         String[] d_rdogrpLamp = new String[] {"0","1"};
         objSave.setLamp("");
         for (int i = 0; i < rdogrpLamp.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpLamp.getChildAt(i);
            if (rb.isChecked()) objSave.setLamp(d_rdogrpLamp[i]);
         }

         String[] d_rdogrpGasLamp = new String[] {"0","1"};
         objSave.setGasLamp("");
         for (int i = 0; i < rdogrpGasLamp.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpGasLamp.getChildAt(i);
            if (rb.isChecked()) objSave.setGasLamp(d_rdogrpGasLamp[i]);
         }

         String[] d_rdogrpPetroleum = new String[] {"0","1"};
         objSave.setPetroleum("");
         for (int i = 0; i < rdogrpPetroleum.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpPetroleum.getChildAt(i);
            if (rb.isChecked()) objSave.setPetroleum(d_rdogrpPetroleum[i]);
         }

         String[] d_rdogrpMatt = new String[] {"0","1"};
         objSave.setMatt("");
         for (int i = 0; i < rdogrpMatt.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpMatt.getChildAt(i);
            if (rb.isChecked()) objSave.setMatt(d_rdogrpMatt[i]);
         }

         String[] d_rdogrpMats = new String[] {"0","1"};
         objSave.setMats("");
         for (int i = 0; i < rdogrpMats.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpMats.getChildAt(i);
            if (rb.isChecked()) objSave.setMats(d_rdogrpMats[i]);
         }

         String[] d_rdogrpCarpets = new String[] {"0","1"};
         objSave.setCarpets("");
         for (int i = 0; i < rdogrpCarpets.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpCarpets.getChildAt(i);
            if (rb.isChecked()) objSave.setCarpets(d_rdogrpCarpets[i]);
         }

         String[] d_rdogrpBed = new String[] {"0","1"};
         objSave.setBed("");
         for (int i = 0; i < rdogrpBed.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpBed.getChildAt(i);
            if (rb.isChecked()) objSave.setBed(d_rdogrpBed[i]);
         }

         String[] d_rdogrpChair = new String[] {"0","1"};
         objSave.setChair("");
         for (int i = 0; i < rdogrpChair.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpChair.getChildAt(i);
            if (rb.isChecked()) objSave.setChair(d_rdogrpChair[i]);
         }

         String[] d_rdogrpSofa = new String[] {"0","1"};
         objSave.setSofa("");
         for (int i = 0; i < rdogrpSofa.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpSofa.getChildAt(i);
            if (rb.isChecked()) objSave.setSofa(d_rdogrpSofa[i]);
         }

         String[] d_rdogrpTables = new String[] {"0","1"};
         objSave.setTables("");
         for (int i = 0; i < rdogrpTables.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpTables.getChildAt(i);
            if (rb.isChecked()) objSave.setTables(d_rdogrpTables[i]);
         }

         String[] d_rdogrpWatch = new String[] {"0","1"};
         objSave.setWatch("");
         for (int i = 0; i < rdogrpWatch.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpWatch.getChildAt(i);
            if (rb.isChecked()) objSave.setWatch(d_rdogrpWatch[i]);
         }

         String[] d_rdogrpWMachine = new String[] {"0","1"};
         objSave.setWMachine("");
         for (int i = 0; i < rdogrpWMachine.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpWMachine.getChildAt(i);
            if (rb.isChecked()) objSave.setWMachine(d_rdogrpWMachine[i]);
         }

         String[] d_rdogrpIron = new String[] {"0","1"};
         objSave.setIron("");
         for (int i = 0; i < rdogrpIron.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpIron.getChildAt(i);
            if (rb.isChecked()) objSave.setIron(d_rdogrpIron[i]);
         }

         String[] d_rdogrpBooth = new String[] {"0","1"};
         objSave.setBooth("");
         for (int i = 0; i < rdogrpBooth.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpBooth.getChildAt(i);
            if (rb.isChecked()) objSave.setBooth(d_rdogrpBooth[i]);
         }

         String[] d_rdogrpSMachine = new String[] {"0","1"};
         objSave.setSMachine("");
         for (int i = 0; i < rdogrpSMachine.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpSMachine.getChildAt(i);
            if (rb.isChecked()) objSave.setSMachine(d_rdogrpSMachine[i]);
         }

         String[] d_rdogrpGenerator = new String[] {"0","1"};
         objSave.setGenerator("");
         for (int i = 0; i < rdogrpGenerator.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpGenerator.getChildAt(i);
            if (rb.isChecked()) objSave.setGenerator(d_rdogrpGenerator[i]);
         }

         String[] d_rdogrpInternet = new String[] {"0","1"};
         objSave.setInternet("");
         for (int i = 0; i < rdogrpInternet.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpInternet.getChildAt(i);
            if (rb.isChecked()) objSave.setInternet(d_rdogrpInternet[i]);
         }

         String[] d_rdogrpSatellite = new String[] {"0","1"};
         objSave.setSatellite("");
         for (int i = 0; i < rdogrpSatellite.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpSatellite.getChildAt(i);
            if (rb.isChecked()) objSave.setSatellite(d_rdogrpSatellite[i]);
         }

         String[] d_rdogrpLandline = new String[] {"0","1"};
         objSave.setLandline("");
         for (int i = 0; i < rdogrpLandline.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpLandline.getChildAt(i);
            if (rb.isChecked()) objSave.setLandline(d_rdogrpLandline[i]);
         }

         String[] d_rdogrpCellphone = new String[] {"0","1"};
         objSave.setCellphone("");
         for (int i = 0; i < rdogrpCellphone.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpCellphone.getChildAt(i);
            if (rb.isChecked()) objSave.setCellphone(d_rdogrpCellphone[i]);
         }

         String[] d_rdogrpTV = new String[] {"0","1"};
         objSave.setTV("");
         for (int i = 0; i < rdogrpTV.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpTV.getChildAt(i);
            if (rb.isChecked()) objSave.setTV(d_rdogrpTV[i]);
         }

         String[] d_rdogrpTV5 = new String[] {"0","1"};
         objSave.setTV5("");
         for (int i = 0; i < rdogrpTV5.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpTV5.getChildAt(i);
            if (rb.isChecked()) objSave.setTV5(d_rdogrpTV5[i]);
         }

         String[] d_rdogrpChannel = new String[] {"0","1"};
         objSave.setChannel("");
         for (int i = 0; i < rdogrpChannel.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpChannel.getChildAt(i);
            if (rb.isChecked()) objSave.setChannel(d_rdogrpChannel[i]);
         }

         String[] d_rdogrpRadio = new String[] {"0","1"};
         objSave.setRadio("");
         for (int i = 0; i < rdogrpRadio.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpRadio.getChildAt(i);
            if (rb.isChecked()) objSave.setRadio(d_rdogrpRadio[i]);
         }

         String[] d_rdogrpDVD = new String[] {"0","1"};
         objSave.setDVD("");
         for (int i = 0; i < rdogrpDVD.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpDVD.getChildAt(i);
            if (rb.isChecked()) objSave.setDVD(d_rdogrpDVD[i]);
         }

         String[] d_rdogrpVideo = new String[] {"0","1"};
         objSave.setVideo("");
         for (int i = 0; i < rdogrpVideo.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpVideo.getChildAt(i);
            if (rb.isChecked()) objSave.setVideo(d_rdogrpVideo[i]);
         }

         String[] d_rdogrpComputer = new String[] {"0","1"};
         objSave.setComputer("");
         for (int i = 0; i < rdogrpComputer.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpComputer.getChildAt(i);
            if (rb.isChecked()) objSave.setComputer(d_rdogrpComputer[i]);
         }

         String[] d_rdogrpLaptop = new String[] {"0","1"};
         objSave.setLaptop("");
         for (int i = 0; i < rdogrpLaptop.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpLaptop.getChildAt(i);
            if (rb.isChecked()) objSave.setLaptop(d_rdogrpLaptop[i]);
         }

         String[] d_rdogrpCable = new String[] {"0","1"};
         objSave.setCable("");
         for (int i = 0; i < rdogrpCable.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpCable.getChildAt(i);
            if (rb.isChecked()) objSave.setCable(d_rdogrpCable[i]);
         }

         String[] d_rdogrpMicrowave = new String[] {"0","1"};
         objSave.setMicrowave("");
         for (int i = 0; i < rdogrpMicrowave.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpMicrowave.getChildAt(i);
            if (rb.isChecked()) objSave.setMicrowave(d_rdogrpMicrowave[i]);
         }

         String[] d_rdogrpGeyser = new String[] {"0","1"};
         objSave.setGeyser("");
         for (int i = 0; i < rdogrpGeyser.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpGeyser.getChildAt(i);
            if (rb.isChecked()) objSave.setGeyser(d_rdogrpGeyser[i]);
         }

         String[] d_rdogrpGrill = new String[] {"0","1"};
         objSave.setGrill("");
         for (int i = 0; i < rdogrpGrill.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpGrill.getChildAt(i);
            if (rb.isChecked()) objSave.setGrill(d_rdogrpGrill[i]);
         }

         String[] d_rdogrpGrain = new String[] {"0","1"};
         objSave.setGrain("");
         for (int i = 0; i < rdogrpGrain.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpGrain.getChildAt(i);
            if (rb.isChecked()) objSave.setGrain(d_rdogrpGrain[i]);
         }

         String[] d_rdogrpRefrigerator = new String[] {"0","1"};
         objSave.setRefrigerator("");
         for (int i = 0; i < rdogrpRefrigerator.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpRefrigerator.getChildAt(i);
            if (rb.isChecked()) objSave.setRefrigerator(d_rdogrpRefrigerator[i]);
         }

         String[] d_rdogrpDeepFreezer = new String[] {"0","1"};
         objSave.setDeepFreezer("");
         for (int i = 0; i < rdogrpDeepFreezer.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpDeepFreezer.getChildAt(i);
            if (rb.isChecked()) objSave.setDeepFreezer(d_rdogrpDeepFreezer[i]);
         }

         String[] d_rdogrpStove = new String[] {"0","1"};
         objSave.setStove("");
         for (int i = 0; i < rdogrpStove.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpStove.getChildAt(i);
            if (rb.isChecked()) objSave.setStove(d_rdogrpStove[i]);
         }

         String[] d_rdogrpGasHob = new String[] {"0","1"};
         objSave.setGasHob("");
         for (int i = 0; i < rdogrpGasHob.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpGasHob.getChildAt(i);
            if (rb.isChecked()) objSave.setGasHob(d_rdogrpGasHob[i]);
         }

         String[] d_rdogrpImpCooker = new String[] {"0","1"};
         objSave.setImpCooker("");
         for (int i = 0; i < rdogrpImpCooker.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpImpCooker.getChildAt(i);
            if (rb.isChecked()) objSave.setImpCooker(d_rdogrpImpCooker[i]);
         }

         String[] d_rdogrpBike = new String[] {"0","1"};
         objSave.setBike("");
         for (int i = 0; i < rdogrpBike.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpBike.getChildAt(i);
            if (rb.isChecked()) objSave.setBike(d_rdogrpBike[i]);
         }

         String[] d_rdogrpMotorcycle = new String[] {"0","1"};
         objSave.setMotorcycle("");
         for (int i = 0; i < rdogrpMotorcycle.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpMotorcycle.getChildAt(i);
            if (rb.isChecked()) objSave.setMotorcycle(d_rdogrpMotorcycle[i]);
         }

         String[] d_rdogrpCar = new String[] {"0","1"};
         objSave.setCar("");
         for (int i = 0; i < rdogrpCar.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpCar.getChildAt(i);
            if (rb.isChecked()) objSave.setCar(d_rdogrpCar[i]);
         }

         String[] d_rdogrpRickshaw = new String[] {"0","1"};
         objSave.setRickshaw("");
         for (int i = 0; i < rdogrpRickshaw.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpRickshaw.getChildAt(i);
            if (rb.isChecked()) objSave.setRickshaw(d_rdogrpRickshaw[i]);
         }

         String[] d_rdogrpCart = new String[] {"0","1"};
         objSave.setCart("");
         for (int i = 0; i < rdogrpCart.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpCart.getChildAt(i);
            if (rb.isChecked()) objSave.setCart(d_rdogrpCart[i]);
         }

         String[] d_rdogrpCanoe = new String[] {"0","1"};
         objSave.setCanoe("");
         for (int i = 0; i < rdogrpCanoe.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpCanoe.getChildAt(i);
            if (rb.isChecked()) objSave.setCanoe(d_rdogrpCanoe[i]);
         }

         String[] d_rdogrpBus = new String[] {"0","1"};
         objSave.setBus("");
         for (int i = 0; i < rdogrpBus.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpBus.getChildAt(i);
            if (rb.isChecked()) objSave.setBus(d_rdogrpBus[i]);
         }

         String[] d_rdogrpTricycle = new String[] {"0","1"};
         objSave.setTricycle("");
         for (int i = 0; i < rdogrpTricycle.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpTricycle.getChildAt(i);
            if (rb.isChecked()) objSave.setTricycle(d_rdogrpTricycle[i]);
         }

         String[] d_rdogrpBoatWithMot = new String[] {"0","1"};
         objSave.setBoatWithMot("");
         for (int i = 0; i < rdogrpBoatWithMot.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpBoatWithMot.getChildAt(i);
            if (rb.isChecked()) objSave.setBoatWithMot(d_rdogrpBoatWithMot[i]);
         }




         String[] d_rdogrpTractor = new String[] {"0","1"};
         objSave.setTractor("");
         for (int i = 0; i < rdogrpTractor.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpTractor.getChildAt(i);
            if (rb.isChecked()) objSave.setTractor(d_rdogrpTractor[i]);
         }

         String[] d_rdogrpPlow = new String[] {"0","1"};
         objSave.setPlow("");
         for (int i = 0; i < rdogrpPlow.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpPlow.getChildAt(i);
            if (rb.isChecked()) objSave.setPlow(d_rdogrpPlow[i]);
         }

         String[] d_rdogrpDuck = new String[] {"0","1"};
         objSave.setDuck("");
         for (int i = 0; i < rdogrpDuck.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpDuck.getChildAt(i);
            if (rb.isChecked()) objSave.setDuck(d_rdogrpDuck[i]);
         }

         String[] d_rdogrpCow = new String[] {"0","1"};
         objSave.setCow("");
         for (int i = 0; i < rdogrpCow.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpCow.getChildAt(i);
            if (rb.isChecked()) objSave.setCow(d_rdogrpCow[i]);
         }

         String[] d_rdogrpSheep = new String[] {"0","1"};
         objSave.setSheep("");
         for (int i = 0; i < rdogrpSheep.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpSheep.getChildAt(i);
            if (rb.isChecked()) objSave.setSheep(d_rdogrpSheep[i]);
         }

         String[] d_rdogrpGoat = new String[] {"0","1"};
         objSave.setGoat("");
         for (int i = 0; i < rdogrpGoat.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpGoat.getChildAt(i);
            if (rb.isChecked()) objSave.setGoat(d_rdogrpGoat[i]);
         }

         String[] d_rdogrpChicken = new String[] {"0","1"};
         objSave.setChicken("");
         for (int i = 0; i < rdogrpChicken.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpChicken.getChildAt(i);
            if (rb.isChecked()) objSave.setChicken(d_rdogrpChicken[i]);
         }

         String[] d_rdogrpDonkey = new String[] {"0","1"};
         objSave.setDonkey("");
         for (int i = 0; i < rdogrpDonkey.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpDonkey.getChildAt(i);
            if (rb.isChecked()) objSave.setDonkey(d_rdogrpDonkey[i]);
         }

         String[] d_rdogrpHorse = new String[] {"0","1"};
         objSave.setHorse("");
         for (int i = 0; i < rdogrpHorse.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpHorse.getChildAt(i);
            if (rb.isChecked()) objSave.setHorse(d_rdogrpHorse[i]);
         }

         String[] d_rdogrpPig = new String[] {"0","1"};
         objSave.setPig("");
         for (int i = 0; i < rdogrpPig.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpPig.getChildAt(i);
            if (rb.isChecked()) objSave.setPig(d_rdogrpPig[i]);
         }

         String[] d_rdogrpBirds = new String[] {"0","1"};
         objSave.setBirds("");
         for (int i = 0; i < rdogrpBirds.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpBirds.getChildAt(i);
            if (rb.isChecked()) objSave.setBirds(d_rdogrpBirds[i]);
         }

         String[] d_rdogrpDogs = new String[] {"0","1"};
         objSave.setDogs("");
         for (int i = 0; i < rdogrpDogs.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpDogs.getChildAt(i);
            if (rb.isChecked()) objSave.setDogs(d_rdogrpDogs[i]);
         }

         String[] d_rdogrpCats = new String[] {"0","1"};
         objSave.setCats("");
         for (int i = 0; i < rdogrpCats.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpCats.getChildAt(i);
            if (rb.isChecked()) objSave.setCats(d_rdogrpCats[i]);
         }

         String[] d_rdogrpFishNet = new String[] {"0","1"};
         objSave.setFishNet("");
         for (int i = 0; i < rdogrpFishNet.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpFishNet.getChildAt(i);
            if (rb.isChecked()) objSave.setFishNet(d_rdogrpFishNet[i]);
         }

         String[] d_rdogrpOtherAsset = new String[] {"0","1"};
         objSave.setOtherAsset("");
         for (int i = 0; i < rdogrpOtherAsset.getChildCount(); i++)
         {
            rb = (RadioButton) rdogrpOtherAsset.getChildAt(i);
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

            Toast.makeText(Surv_SES_CrossRiver.this,"Save Successfully...",Toast.LENGTH_SHORT).show();
            finish();
         }
         else{
            Connection.MessageBox(Surv_SES_CrossRiver.this, status);
            return;
         }
      }
      catch(Exception  e)
      {
         Connection.MessageBox(Surv_SES_CrossRiver.this, e.getMessage());
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
            ValidationMsg += "\n"+ lblHHID.getText().toString() + " Required field: "+ VlblHHID.getText().toString();
            secHHID.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtSESNo.getText().toString().length()==0 & secSESNo.isShown())
         {
            ValidationMsg += "\n"+ lblSESNo.getText().toString() + " Required field: "+ VlblSESNo.getText().toString();
            secSESNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         DV = Global.DateValidate(txtSESVDate.getText().toString());
         if(DV.length()!=0 & secSESVDate.isShown())
         {
            ValidationMsg += "\n"+ lblSESVDate.getText().toString() + " Required field/Not a valid date format: "+ VlblSESVDate.getText().toString();
            secSESVDate.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!rdoSESVStatus1.isChecked() & !rdoSESVStatus2.isChecked() & !rdoSESVStatus3.isChecked() & !rdoSESVStatus4.isChecked() & secSESVStatus.isShown())
         {
            ValidationMsg += "\n"+ lblSESVStatus.getText().toString() + " Required field: "+ VlblSESVStatus.getText().toString();
         }
         if(txtSESVStatusOth.getText().toString().length()==0 & secSESVStatusOth.isShown())
         {
            ValidationMsg += "\n"+ lblSESVStatusOth.getText().toString() + " Required field: "+ VlblSESVStatusOth.getText().toString();
            secSESVStatusOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
         {
            ValidationMsg += "\n"+ lblRnd.getText().toString() + " Required field: "+ VlblRnd.getText().toString();
            secRnd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnWSDrink.getSelectedItemPosition()==0  & secWSDrink.isShown())
         {
            ValidationMsg += "\n"+ lblWSDrink.getText().toString() + " Required field: "+ VlblWSDrink.getText().toString();
            secWSDrink.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtWSDrinkOth.getText().toString().length()==0 & secWSDrinkOth.isShown())
         {
            ValidationMsg += "\n"+ lblWSDrinkOth.getText().toString() + " Required field: "+ VlblWSDrinkOth.getText().toString();
            secWSDrinkOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnMainWater.getSelectedItemPosition()==0  & secMainWater.isShown())
         {
            ValidationMsg += "\n"+ lblMainWater.getText().toString() + " Required field: "+ VlblMainWater.getText().toString();
            secMainWater.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtMainWaterOth.getText().toString().length()==0 & secMainWaterOth.isShown())
         {
            ValidationMsg += "\n"+ lblMainWaterOth.getText().toString() + " Required field: "+ VlblMainWaterOth.getText().toString();
            secMainWaterOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnToilet.getSelectedItemPosition()==0  & secToilet.isShown())
         {
            ValidationMsg += "\n"+ lblToilet.getText().toString() + " Required field: "+ VlblToilet.getText().toString();
            secToilet.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtToiletOth.getText().toString().length()==0 & secToiletOth.isShown())
         {
            ValidationMsg += "\n"+ lblToiletOth.getText().toString() + " Required field: "+ VlblToiletOth.getText().toString();
            secToiletOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!rdoToiletShrd1.isChecked() & !rdoToiletShrd2.isChecked() & !rdoToiletShrd3.isChecked() & secToiletShrd.isShown())
         {
            ValidationMsg += "\n"+ lblToiletShrd.getText().toString() + " Required field: "+ VlblToiletShrd.getText().toString();
         }
         if(txtToiletUseNo.getText().toString().length()==0 & secToiletUseNo.isShown() && (!rdoToiletUseNoDk1.isChecked()&&!rdoToiletUseNoDk2.isChecked()&&!rdoToiletUseNoDk3.isChecked()))
         {
            ValidationMsg += "\n"+ lblToiletUseNo.getText().toString() + " Required field: "+ VlblToiletUseNo.getText().toString();
            secToiletUseNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(secToiletUseNo.isShown() & (Integer.parseInt(txtToiletUseNo.getText().toString().length()==0 ? "1" : txtToiletUseNo.getText().toString()) < 1 || Integer.parseInt(txtToiletUseNo.getText().toString().length()==0 ? "10" : txtToiletUseNo.getText().toString()) > 10) && (!rdoToiletUseNoDk1.isChecked()&&!rdoToiletUseNoDk2.isChecked()&&!rdoToiletUseNoDk3.isChecked()))
         {
            ValidationMsg += "\n"+ lblToiletUseNo.getText().toString() + " Value should be between 1 and 10 (" + VlblToiletUseNo.getText().toString() +")";
            secToiletUseNo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtToiletUseNo.getText().toString().length()==0 && !rdoToiletUseNoDk1.isChecked() & !rdoToiletUseNoDk2.isChecked() & !rdoToiletUseNoDk3.isChecked() & secToiletUseNoDk.isShown())
         {
            ValidationMsg += "\n"+ lblToiletUseNoDk.getText().toString() + " Required field: "+ VlblToiletUseNoDk.getText().toString();
         }
         if(!rdoToiletLoc1.isChecked() & !rdoToiletLoc2.isChecked() & !rdoToiletLoc3.isChecked() & !rdoToiletLoc4.isChecked() & secToiletLoc.isShown())
         {
            ValidationMsg += "\n"+ lblToiletLoc.getText().toString() + " Required field: "+ VlblToiletLoc.getText().toString();
         }
         if(!rdoHandWash1.isChecked() & !rdoHandWash2.isChecked() & !rdoHandWash3.isChecked() & secHandWash.isShown())
         {
            ValidationMsg += "\n"+ lblHandWash.getText().toString() + " Required field: "+ VlblHandWash.getText().toString();
         }
         if(!rdoShowWash1.isChecked() & !rdoShowWash2.isChecked() & !rdoShowWash3.isChecked() & !rdoShowWash4.isChecked() & !rdoShowWash5.isChecked() & !rdoShowWash6.isChecked() & secShowWash.isShown())
         {
            ValidationMsg += "\n"+ lblShowWash.getText().toString() + " Required field: "+ VlblShowWash.getText().toString();
         }
         if(!rdoAvailableWat1.isChecked() & !rdoAvailableWat2.isChecked() & secAvailableWat.isShown())
         {
            ValidationMsg += "\n"+ lblAvailableWat.getText().toString() + " Required field: "+ VlblAvailableWat.getText().toString();
         }
         if(!rdoAvailableSoap1.isChecked() & !rdoAvailableSoap2.isChecked() & secAvailableSoap.isShown())
         {
            ValidationMsg += "\n"+ lblAvailableSoap.getText().toString() + " Required field: "+ VlblAvailableSoap.getText().toString();
         }
         if(spnHandWashOthMem.getSelectedItemPosition()==0  & secHandWashOthMem.isShown())
         {
            ValidationMsg += "\n"+ lblHandWashOthMem.getText().toString() + " Required field: "+ VlblHandWashOthMem.getText().toString();
            secHandWashOthMem.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtHandWashOthMemO.getText().toString().length()==0 & secHandWashOthMemO.isShown())
         {
            ValidationMsg += "\n"+ lblHandWashOthMemO.getText().toString() + " Required field: "+ VlblHandWashOthMemO.getText().toString();
            secHandWashOthMemO.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!rdoSoapInHouse1.isChecked() & !rdoSoapInHouse2.isChecked() & secSoapInHouse.isShown())
         {
            ValidationMsg += "\n"+ lblSoapInHouse.getText().toString() + " Required field: "+ VlblSoapInHouse.getText().toString();
            secSoapInHouse.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(!rdoSoapInHouseShow1.isChecked() & !rdoSoapInHouseShow2.isChecked() & secSoapInHouseShow.isShown())
         {
            ValidationMsg += "\n"+ lblSoapInHouseShow.getText().toString() + " Required field: "+ VlblSoapInHouseShow.getText().toString();
            secSoapInHouseShow.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }

         if(secSoapInHouseObjA.isShown() && !chkSoapInHouseObjA.isChecked() && !chkSoapInHouseObjB.isChecked() && !chkSoapInHouseObjC.isChecked())
         {
            ValidationMsg += "\n"+ lblCookDvc.getText().toString() + " Please select at least one options.";
            secSoapInHouseObjA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            secSoapInHouseObjB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
            secSoapInHouseObjC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnCookDvc.getSelectedItemPosition()==0  & secCookDvc.isShown())
         {
            ValidationMsg += "\n"+ lblCookDvc.getText().toString() + " Required field: "+ VlblCookDvc.getText().toString();
            secCookDvc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtCookDvcOth.getText().toString().length()==0 & secCookDvcOth.isShown())
         {
            ValidationMsg += "\n"+ lblCookDvcOth.getText().toString() + " Required field: "+ VlblCookDvcOth.getText().toString();
            secCookDvcOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnCookFuel.getSelectedItemPosition()==0  & secCookFuel.isShown())
         {
            ValidationMsg += "\n"+ lblCookFuel.getText().toString() + " Required field: "+ VlblCookFuel.getText().toString();
            secCookFuel.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtCookFuelOth.getText().toString().length()==0 & secCookFuelOth.isShown())
         {
            ValidationMsg += "\n"+ lblCookFuelOth.getText().toString() + " Required field: "+ VlblCookFuelOth.getText().toString();
            secCookFuelOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnCookPlc.getSelectedItemPosition()==0  & secCookPlc.isShown())
         {
            ValidationMsg += "\n"+ lblCookPlc.getText().toString() + " Required field: "+ VlblCookPlc.getText().toString();
            secCookPlc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtCookPlcOth.getText().toString().length()==0 & secCookPlcOth.isShown())
         {
            ValidationMsg += "\n"+ lblCookPlcOth.getText().toString() + " Required field: "+ VlblCookPlcOth.getText().toString();
            secCookPlcOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnFloor.getSelectedItemPosition()==0  & secFloor.isShown())
         {
            ValidationMsg += "\n"+ lblFloor.getText().toString() + " Required field: "+ VlblFloor.getText().toString();
            secFloor.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtFloorOth.getText().toString().length()==0 & secFloorOth.isShown())
         {
            ValidationMsg += "\n"+ lblFloorOth.getText().toString() + " Required field: "+ VlblFloorOth.getText().toString();
            secFloorOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnRoof.getSelectedItemPosition()==0  & secRoof.isShown())
         {
            ValidationMsg += "\n"+ lblRoof.getText().toString() + " Required field: "+ VlblRoof.getText().toString();
            secRoof.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtRoofOth.getText().toString().length()==0 & secRoofOth.isShown())
         {
            ValidationMsg += "\n"+ lblRoofOth.getText().toString() + " Required field: "+ VlblRoofOth.getText().toString();
            secRoofOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(spnWall.getSelectedItemPosition()==0  & secWall.isShown())
         {
            ValidationMsg += "\n"+ lblWall.getText().toString() + " Required field: "+ VlblWall.getText().toString();
            secWall.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtWallOth.getText().toString().length()==0 & secWallOth.isShown())
         {
            ValidationMsg += "\n"+ lblWallOth.getText().toString() + " Required field: "+ VlblWallOth.getText().toString();
            secWallOth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtRoomSleep.getText().toString().length()==0 & secRoomSleep.isShown() && (!rdoRoomSleepDk1.isChecked() & !rdoRoomSleepDk2.isChecked()))
         {
            ValidationMsg += "\n"+ lblRoomSleep.getText().toString() + " Required field: "+ VlblRoomSleep.getText().toString();
            secRoomSleep.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(secRoomSleep.isShown() & (Integer.parseInt(txtRoomSleep.getText().toString().length()==0 ? "01" : txtRoomSleep.getText().toString()) < 01 || Integer.parseInt(txtRoomSleep.getText().toString().length()==0 ? "20" : txtRoomSleep.getText().toString()) > 20) && (!rdoRoomSleepDk1.isChecked() & !rdoRoomSleepDk2.isChecked()))
         {
            ValidationMsg += "\n"+ lblRoomSleep.getText().toString() + " Value should be between 01 and 20 (" + VlblRoomSleep.getText().toString() +")";
            secRoomSleep.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtRoomSleep.getText().toString().length()==0 && !rdoRoomSleepDk1.isChecked() & !rdoRoomSleepDk2.isChecked() & secRoomSleepDk.isShown())
         {
            ValidationMsg += "\n"+ lblRoomSleepDk.getText().toString() + " Required field: "+ VlblRoomSleepDk.getText().toString();
         }
         if(!rdoHomesteadAny1.isChecked() & !rdoHomesteadAny2.isChecked() & !rdoHomesteadAny3.isChecked() & !rdoHomesteadAny4.isChecked() & secHomesteadAny.isShown())
         {
            ValidationMsg += "\n"+ lblHomesteadAny.getText().toString() + " Required field: "+ VlblHomesteadAny.getText().toString();
         }
         if(!rdoOthLand1.isChecked() & !rdoOthLand2.isChecked() & !rdoOthLand3.isChecked() & !rdoOthLand4.isChecked() & secOthLand.isShown())
         {
            ValidationMsg += "\n"+ lblOthLand.getText().toString() + " Required field: "+ VlblOthLand.getText().toString();
         }
         if(!rdoElectricity1.isChecked() & !rdoElectricity2.isChecked() & secElectricity.isShown())
         {
            ValidationMsg += "\n"+ lblElectricity.getText().toString() + " Required field: "+ VlblElectricity.getText().toString();
         }
         if(!rdoHeater1.isChecked() & !rdoHeater2.isChecked() & secHeater.isShown())
         {
            ValidationMsg += "\n"+ lblHeater.getText().toString() + " Required field: "+ VlblHeater.getText().toString();
         }
         if(!rdoAC1.isChecked() & !rdoAC2.isChecked() & secAC.isShown())
         {
            ValidationMsg += "\n"+ lblAC.getText().toString() + " Required field: "+ VlblAC.getText().toString();
         }
         if(!rdoElecFan1.isChecked() & !rdoElecFan2.isChecked() & secElecFan.isShown())
         {
            ValidationMsg += "\n"+ lblElecFan.getText().toString() + " Required field: "+ VlblElecFan.getText().toString();
         }
         if(!rdoLantern1.isChecked() & !rdoLantern2.isChecked() & secLantern.isShown())
         {
            ValidationMsg += "\n"+ lblLantern.getText().toString() + " Required field: "+ VlblLantern.getText().toString();
         }
         if(!rdoLamp1.isChecked() & !rdoLamp2.isChecked() & secLamp.isShown())
         {
            ValidationMsg += "\n"+ lblLamp.getText().toString() + " Required field: "+ VlblLamp.getText().toString();
         }
         if(!rdoGasLamp1.isChecked() & !rdoGasLamp2.isChecked() & secGasLamp.isShown())
         {
            ValidationMsg += "\n"+ lblGasLamp.getText().toString() + " Required field: "+ VlblGasLamp.getText().toString();
         }
         if(!rdoPetroleum1.isChecked() & !rdoPetroleum2.isChecked() & secPetroleum.isShown())
         {
            ValidationMsg += "\n"+ lblPetroleum.getText().toString() + " Required field: "+ VlblPetroleum.getText().toString();
         }
         if(!rdoMatt1.isChecked() & !rdoMatt2.isChecked() & secMatt.isShown())
         {
            ValidationMsg += "\n"+ lblMatt.getText().toString() + " Required field: "+ VlblMatt.getText().toString();
         }
         if(!rdoMats1.isChecked() & !rdoMats2.isChecked() & secMats.isShown())
         {
            ValidationMsg += "\n"+ lblMats.getText().toString() + " Required field: "+ VlblMats.getText().toString();
         }
         if(!rdoCarpets1.isChecked() & !rdoCarpets2.isChecked() & secCarpets.isShown())
         {
            ValidationMsg += "\n"+ lblCarpets.getText().toString() + " Required field: "+ VlblCarpets.getText().toString();
         }
         if(!rdoBed1.isChecked() & !rdoBed2.isChecked() & secBed.isShown())
         {
            ValidationMsg += "\n"+ lblBed.getText().toString() + " Required field: "+ VlblBed.getText().toString();
         }
         if(!rdoChair1.isChecked() & !rdoChair2.isChecked() & secChair.isShown())
         {
            ValidationMsg += "\n"+ lblChair.getText().toString() + " Required field: "+ VlblChair.getText().toString();
         }
         if(!rdoSofa1.isChecked() & !rdoSofa2.isChecked() & secSofa.isShown())
         {
            ValidationMsg += "\n"+ lblSofa.getText().toString() + " Required field: "+ VlblSofa.getText().toString();
         }
         if(!rdoTables1.isChecked() & !rdoTables2.isChecked() & secTables.isShown())
         {
            ValidationMsg += "\n"+ lblTables.getText().toString() + " Required field: "+ VlblTables.getText().toString();
         }
         if(!rdoWatch1.isChecked() & !rdoWatch2.isChecked() & secWatch.isShown())
         {
            ValidationMsg += "\n"+ lblWatch.getText().toString() + " Required field: "+ VlblWatch.getText().toString();
         }
         if(!rdoWMachine1.isChecked() & !rdoWMachine2.isChecked() & secWMachine.isShown())
         {
            ValidationMsg += "\n"+ lblWMachine.getText().toString() + " Required field: "+ VlblWMachine.getText().toString();
         }
         if(!rdoIron1.isChecked() & !rdoIron2.isChecked() & secIron.isShown())
         {
            ValidationMsg += "\n"+ lblIron.getText().toString() + " Required field: "+ VlblIron.getText().toString();
         }
         if(!rdoBooth1.isChecked() & !rdoBooth2.isChecked() & secBooth.isShown())
         {
            ValidationMsg += "\n"+ lblBooth.getText().toString() + " Required field: "+ VlblBooth.getText().toString();
         }
         if(!rdoSMachine1.isChecked() & !rdoSMachine2.isChecked() & secSMachine.isShown())
         {
            ValidationMsg += "\n"+ lblSMachine.getText().toString() + " Required field: "+ VlblSMachine.getText().toString();
         }
         if(!rdoGenerator1.isChecked() & !rdoGenerator2.isChecked() & secGenerator.isShown())
         {
            ValidationMsg += "\n"+ lblGenerator.getText().toString() + " Required field: "+ VlblGenerator.getText().toString();
         }
         if(!rdoInternet1.isChecked() & !rdoInternet2.isChecked() & secInternet.isShown())
         {
            ValidationMsg += "\n"+ lblInternet.getText().toString() + " Required field: "+ VlblInternet.getText().toString();
         }
         if(!rdoSatellite1.isChecked() & !rdoSatellite2.isChecked() & secSatellite.isShown())
         {
            ValidationMsg += "\n"+ lblSatellite.getText().toString() + " Required field: "+ VlblSatellite.getText().toString();
         }
         if(!rdoLandline1.isChecked() & !rdoLandline2.isChecked() & secLandline.isShown())
         {
            ValidationMsg += "\n"+ lblLandline.getText().toString() + " Required field: "+ VlblLandline.getText().toString();
         }
         if(!rdoCellphone1.isChecked() & !rdoCellphone2.isChecked() & secCellphone.isShown())
         {
            ValidationMsg += "\n"+ lblCellphone.getText().toString() + " Required field: "+ VlblCellphone.getText().toString();
         }
         if(!rdoTV1.isChecked() & !rdoTV2.isChecked() & secTV.isShown())
         {
            ValidationMsg += "\n"+ lblTV.getText().toString() + " Required field: "+ VlblTV.getText().toString();
         }
         if(!rdoTV51.isChecked() & !rdoTV52.isChecked() & secTV5.isShown())
         {
            ValidationMsg += "\n"+ lblTV5.getText().toString() + " Required field: "+ VlblTV5.getText().toString();
         }
         if(!rdoChannel1.isChecked() & !rdoChannel2.isChecked() & secChannel.isShown())
         {
            ValidationMsg += "\n"+ lblChannel.getText().toString() + " Required field: "+ VlblChannel.getText().toString();
         }
         if(!rdoRadio1.isChecked() & !rdoRadio2.isChecked() & secRadio.isShown())
         {
            ValidationMsg += "\n"+ lblRadio.getText().toString() + " Required field: "+ VlblRadio.getText().toString();
         }
         if(!rdoDVD1.isChecked() & !rdoDVD2.isChecked() & secDVD.isShown())
         {
            ValidationMsg += "\n"+ lblDVD.getText().toString() + " Required field: "+ VlblDVD.getText().toString();
         }
         if(!rdoVideo1.isChecked() & !rdoVideo2.isChecked() & secVideo.isShown())
         {
            ValidationMsg += "\n"+ lblVideo.getText().toString() + " Required field: "+ VlblVideo.getText().toString();
         }
         if(!rdoComputer1.isChecked() & !rdoComputer2.isChecked() & secComputer.isShown())
         {
            ValidationMsg += "\n"+ lblComputer.getText().toString() + " Required field: "+ VlblComputer.getText().toString();
         }
         if(!rdoLaptop1.isChecked() & !rdoLaptop2.isChecked() & secLaptop.isShown())
         {
            ValidationMsg += "\n"+ lblLaptop.getText().toString() + " Required field: "+ VlblLaptop.getText().toString();
         }
         if(!rdoCable1.isChecked() & !rdoCable2.isChecked() & secCable.isShown())
         {
            ValidationMsg += "\n"+ lblCable.getText().toString() + " Required field: "+ VlblCable.getText().toString();
         }
         if(!rdoMicrowave1.isChecked() & !rdoMicrowave2.isChecked() & secMicrowave.isShown())
         {
            ValidationMsg += "\n"+ lblMicrowave.getText().toString() + " Required field: "+ VlblMicrowave.getText().toString();
         }
         if(!rdoGeyser1.isChecked() & !rdoGeyser2.isChecked() & secGeyser.isShown())
         {
            ValidationMsg += "\n"+ lblGeyser.getText().toString() + " Required field: "+ VlblGeyser.getText().toString();
         }
         if(!rdoGrill1.isChecked() & !rdoGrill2.isChecked() & secGrill.isShown())
         {
            ValidationMsg += "\n"+ lblGrill.getText().toString() + " Required field: "+ VlblGrill.getText().toString();
         }
         if(!rdoGrain1.isChecked() & !rdoGrain2.isChecked() & secGrain.isShown())
         {
            ValidationMsg += "\n"+ lblGrain.getText().toString() + " Required field: "+ VlblGrain.getText().toString();
         }
         if(!rdoRefrigerator1.isChecked() & !rdoRefrigerator2.isChecked() & secRefrigerator.isShown())
         {
            ValidationMsg += "\n"+ lblRefrigerator.getText().toString() + " Required field: "+ VlblRefrigerator.getText().toString();
         }
         if(!rdoDeepFreezer1.isChecked() & !rdoDeepFreezer2.isChecked() & secDeepFreezer.isShown())
         {
            ValidationMsg += "\n"+ lblDeepFreezer.getText().toString() + " Required field: "+ VlblDeepFreezer.getText().toString();
         }
         if(!rdoStove1.isChecked() & !rdoStove2.isChecked() & secStove.isShown())
         {
            ValidationMsg += "\n"+ lblStove.getText().toString() + " Required field: "+ VlblStove.getText().toString();
         }
         if(!rdoGasHob1.isChecked() & !rdoGasHob2.isChecked() & secGasHob.isShown())
         {
            ValidationMsg += "\n"+ lblGasHob.getText().toString() + " Required field: "+ VlblGasHob.getText().toString();
         }
         if(!rdoImpCooker1.isChecked() & !rdoImpCooker2.isChecked() & secImpCooker.isShown())
         {
            ValidationMsg += "\n"+ lblImpCooker.getText().toString() + " Required field: "+ VlblImpCooker.getText().toString();
         }
         if(!rdoBike1.isChecked() & !rdoBike2.isChecked() & secBike.isShown())
         {
            ValidationMsg += "\n"+ lblBike.getText().toString() + " Required field: "+ VlblBike.getText().toString();
         }
         if(!rdoMotorcycle1.isChecked() & !rdoMotorcycle2.isChecked() & secMotorcycle.isShown())
         {
            ValidationMsg += "\n"+ lblMotorcycle.getText().toString() + " Required field: "+ VlblMotorcycle.getText().toString();
         }
         if(!rdoCar1.isChecked() & !rdoCar2.isChecked() & secCar.isShown())
         {
            ValidationMsg += "\n"+ lblCar.getText().toString() + " Required field: "+ VlblCar.getText().toString();
         }
         if(!rdoRickshaw1.isChecked() & !rdoRickshaw2.isChecked() & secRickshaw.isShown())
         {
            ValidationMsg += "\n"+ lblRickshaw.getText().toString() + " Required field: "+ VlblRickshaw.getText().toString();
         }
         if(!rdoCart1.isChecked() & !rdoCart2.isChecked() & secCart.isShown())
         {
            ValidationMsg += "\n"+ lblCart.getText().toString() + " Required field: "+ VlblCart.getText().toString();
         }
         if(!rdoCanoe1.isChecked() & !rdoCanoe2.isChecked() & secCanoe.isShown())
         {
            ValidationMsg += "\n"+ lblCanoe.getText().toString() + " Required field: "+ VlblCanoe.getText().toString();
         }
         if(!rdoBus1.isChecked() & !rdoBus2.isChecked() & secBus.isShown())
         {
            ValidationMsg += "\n"+ lblBus.getText().toString() + " Required field: "+ VlblBus.getText().toString();
         }

         if(!rdoTricycle1.isChecked() & !rdoTricycle2.isChecked() & secTricycle.isShown())
         {
            ValidationMsg += "\n"+ lblTricycle.getText().toString() + " Required field: "+ VlblTricycle.getText().toString();
         }

         if(!rdoBoatWithMot1.isChecked() & !rdoBoatWithMot2.isChecked() & secBoatWithMot.isShown())
         {
            ValidationMsg += "\n"+ lblBoatWithMot.getText().toString() + " Required field: "+ VlblBoatWithMot.getText().toString();
         }


         if(!rdoTractor1.isChecked() & !rdoTractor2.isChecked() & secTractor.isShown())
         {
            ValidationMsg += "\n"+ lblTractor.getText().toString() + " Required field: "+ VlblTractor.getText().toString();
         }
         if(!rdoPlow1.isChecked() & !rdoPlow2.isChecked() & secPlow.isShown())
         {
            ValidationMsg += "\n"+ lblPlow.getText().toString() + " Required field: "+ VlblPlow.getText().toString();
         }
         if(!rdoDuck1.isChecked() & !rdoDuck2.isChecked() & secDuck.isShown())
         {
            ValidationMsg += "\n"+ lblDuck.getText().toString() + " Required field: "+ VlblDuck.getText().toString();
         }
         if(!rdoCow1.isChecked() & !rdoCow2.isChecked() & secCow.isShown())
         {
            ValidationMsg += "\n"+ lblCow.getText().toString() + " Required field: "+ VlblCow.getText().toString();
         }
         if(!rdoSheep1.isChecked() & !rdoSheep2.isChecked() & secSheep.isShown())
         {
            ValidationMsg += "\n"+ lblSheep.getText().toString() + " Required field: "+ VlblSheep.getText().toString();
         }
         if(!rdoGoat1.isChecked() & !rdoGoat2.isChecked() & secGoat.isShown())
         {
            ValidationMsg += "\n"+ lblGoat.getText().toString() + " Required field: "+ VlblGoat.getText().toString();
         }
         if(!rdoChicken1.isChecked() & !rdoChicken2.isChecked() & secChicken.isShown())
         {
            ValidationMsg += "\n"+ lblChicken.getText().toString() + " Required field: "+ VlblChicken.getText().toString();
         }
         if(!rdoDonkey1.isChecked() & !rdoDonkey2.isChecked() & secDonkey.isShown())
         {
            ValidationMsg += "\n"+ lblDonkey.getText().toString() + " Required field: "+ VlblDonkey.getText().toString();
         }
         if(!rdoHorse1.isChecked() & !rdoHorse2.isChecked() & secHorse.isShown())
         {
            ValidationMsg += "\n"+ lblHorse.getText().toString() + " Required field: "+ VlblHorse.getText().toString();
         }
         if(!rdoPig1.isChecked() & !rdoPig2.isChecked() & secPig.isShown())
         {
            ValidationMsg += "\n"+ lblPig.getText().toString() + " Required field: "+ VlblPig.getText().toString();
         }
         if(!rdoBirds1.isChecked() & !rdoBirds2.isChecked() & secBirds.isShown())
         {
            ValidationMsg += "\n"+ lblBirds.getText().toString() + " Required field: "+ VlblBirds.getText().toString();
         }
         if(!rdoDogs1.isChecked() & !rdoDogs2.isChecked() & secDogs.isShown())
         {
            ValidationMsg += "\n"+ lblDogs.getText().toString() + " Required field: "+ VlblDogs.getText().toString();
         }
         if(!rdoCats1.isChecked() & !rdoCats2.isChecked() & secCats.isShown())
         {
            ValidationMsg += "\n"+ lblCats.getText().toString() + " Required field: "+ VlblCats.getText().toString();
         }
         if(!rdoFishNet1.isChecked() & !rdoFishNet2.isChecked() & secFishNet.isShown())
         {
            ValidationMsg += "\n"+ lblFishNet.getText().toString() + " Required field: "+ VlblFishNet.getText().toString();
         }
         if(!rdoOtherAsset1.isChecked() & !rdoOtherAsset2.isChecked() & secOtherAsset.isShown())
         {
            ValidationMsg += "\n"+ lblOtherAsset.getText().toString() + " Required field: "+ VlblOtherAsset.getText().toString();
         }
         if(txtOtherAsset1.getText().toString().length()==0 & secOtherAsset1.isShown())
         {
            ValidationMsg += "\n"+ lblOtherAsset1.getText().toString() + " Required field: "+ VlblOtherAsset1.getText().toString();
            secOtherAsset1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         /*if(txtOtherAsset2.getText().toString().length()==0 & secOtherAsset2.isShown())
         {
            ValidationMsg += "\n"+ lblOtherAsset2.getText().toString() + " Required field: "+ VlblOtherAsset2.getText().toString();
            secOtherAsset2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtOtherAsset3.getText().toString().length()==0 & secOtherAsset3.isShown())
         {
            ValidationMsg += "\n"+ lblOtherAsset3.getText().toString() + " Required field: "+ VlblOtherAsset3.getText().toString();
            secOtherAsset3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }
         if(txtSESNote.getText().toString().length()==0 & secSESNote.isShown())
         {
            ValidationMsg += "\n"+ lblSESNote.getText().toString() + " Required field: "+ VlblSESNote.getText().toString();
            secSESNote.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.color_Section_Highlight));
         }*/
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
         secSESVStatusOth.setBackgroundColor(Color.WHITE);
         secRnd.setBackgroundColor(Color.WHITE);
         secWSDrink.setBackgroundColor(Color.WHITE);
         secWSDrinkOth.setBackgroundColor(Color.WHITE);
         secMainWater.setBackgroundColor(Color.WHITE);
         secMainWaterOth.setBackgroundColor(Color.WHITE);
         secToilet.setBackgroundColor(Color.WHITE);
         secToiletOth.setBackgroundColor(Color.WHITE);
         secToiletUseNo.setBackgroundColor(Color.WHITE);
         secToiletUseNo.setBackgroundColor(Color.WHITE);
         secHandWashOthMem.setBackgroundColor(Color.WHITE);
         secHandWashOthMemO.setBackgroundColor(Color.WHITE);
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
         tmpSES_CrossRiver_DataModel d = new tmpSES_CrossRiver_DataModel();
         String SQL = "Select * from "+ TableName +"  Where HHID='"+ HHID +"' and SESNo='"+ SESNo +"'";
         List<tmpSES_CrossRiver_DataModel> data = d.SelectAll(this, SQL);
         for(tmpSES_CrossRiver_DataModel item : data){
            txtHHID.setText(item.getHHID());
            txtSESNo.setText(item.getSESNo());
            txtSESVDate.setText(item.getSESVDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getSESVDate()));
            String[] d_rdogrpSESVStatus = new String[] {"1","2","3","9"};
            for (int i = 0; i < d_rdogrpSESVStatus.length; i++)
            {
               if (String.valueOf(item.getSESVStatus()).equals(String.valueOf(d_rdogrpSESVStatus[i])))
               {
                  rb = (RadioButton) rdogrpSESVStatus.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            txtSESVStatusOth.setText(item.getSESVStatusOth());
            txtRnd.setText(item.getRnd());
            spnWSDrink.setSelection(Global.SpinnerItemPositionAnyLength(spnWSDrink, String.valueOf(item.getWSDrink())));
            txtWSDrinkOth.setText(item.getWSDrinkOth());
            spnMainWater.setSelection(Global.SpinnerItemPositionAnyLength(spnMainWater, String.valueOf(item.getMainWater())));
            txtMainWaterOth.setText(item.getMainWaterOth());
            spnToilet.setSelection(Global.SpinnerItemPositionAnyLength(spnToilet, String.valueOf(item.getToilet())));
            txtToiletOth.setText(item.getToiletOth());
            String[] d_rdogrpToiletShrd = new String[] {"0","1","2"};
            for (int i = 0; i < d_rdogrpToiletShrd.length; i++)
            {
               if (String.valueOf(item.getToiletShrd()).equals(String.valueOf(d_rdogrpToiletShrd[i])))
               {
                  rb = (RadioButton) rdogrpToiletShrd.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            txtToiletUseNo.setText(String.valueOf(item.getToiletUseNo()));
            String[] d_rdogrpToiletUseNoDk = new String[] {"95","98","99"};
            for (int i = 0; i < d_rdogrpToiletUseNoDk.length; i++)
            {
               if (String.valueOf(item.getToiletUseNoDk()).equals(String.valueOf(d_rdogrpToiletUseNoDk[i])))
               {
                  rb = (RadioButton) rdogrpToiletUseNoDk.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpToiletLoc = new String[] {"1","2","4","3"};
            for (int i = 0; i < d_rdogrpToiletLoc.length; i++)
            {
               if (String.valueOf(item.getToiletLoc()).equals(String.valueOf(d_rdogrpToiletLoc[i])))
               {
                  rb = (RadioButton) rdogrpToiletLoc.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpHandWash = new String[] {"0","1","8"};
            for (int i = 0; i < d_rdogrpHandWash.length; i++)
            {
               if (String.valueOf(item.getHandWash()).equals(String.valueOf(d_rdogrpHandWash[i])))
               {
                  rb = (RadioButton) rdogrpHandWash.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpShowWash = new String[] {"1","2","3","4","5","6"};
            for (int i = 0; i < d_rdogrpShowWash.length; i++)
            {
               if (String.valueOf(item.getShowWash()).equals(String.valueOf(d_rdogrpShowWash[i])))
               {
                  rb = (RadioButton) rdogrpShowWash.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpAvailableWat = new String[] {"1","2"};
            for (int i = 0; i < d_rdogrpAvailableWat.length; i++)
            {
               if (String.valueOf(item.getAvailableWat()).equals(String.valueOf(d_rdogrpAvailableWat[i])))
               {
                  rb = (RadioButton) rdogrpAvailableWat.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpAvailableSoap = new String[] {"1","2"};
            for (int i = 0; i < d_rdogrpAvailableSoap.length; i++)
            {
               if (String.valueOf(item.getAvailableSoap()).equals(String.valueOf(d_rdogrpAvailableSoap[i])))
               {
                  rb = (RadioButton) rdogrpAvailableSoap.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            spnHandWashOthMem.setSelection(Global.SpinnerItemPositionAnyLength(spnHandWashOthMem, String.valueOf(item.getHandWashOthMem())));
            txtHandWashOthMemO.setText(item.getHandWashOthMemO());
            String[] d_rdogrpSoapInHouse = new String[] {"1","2"};
            for (int i = 0; i < d_rdogrpSoapInHouse.length; i++)
            {
               if (String.valueOf(item.getSoapInHouse()).equals(String.valueOf(d_rdogrpSoapInHouse[i])))
               {
                  rb = (RadioButton) rdogrpSoapInHouse.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpSoapInHouseShow = new String[] {"1","2"};
            for (int i = 0; i < d_rdogrpSoapInHouseShow.length; i++)
            {
               if (String.valueOf(item.getSoapInHouseShow()).equals(String.valueOf(d_rdogrpSoapInHouseShow[i])))
               {
                  rb = (RadioButton) rdogrpSoapInHouseShow.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            if(String.valueOf(item.getSoapInHouseObjA()).equals("1"))
            {
               chkSoapInHouseObjA.setChecked(true);
            }
            else if(String.valueOf(item.getSoapInHouseObjA()).equals("2"))
            {
               chkSoapInHouseObjA.setChecked(false);
            }
            if(String.valueOf(item.getSoapInHouseObjB()).equals("1"))
            {
               chkSoapInHouseObjB.setChecked(true);
            }
            else if(String.valueOf(item.getSoapInHouseObjB()).equals("2"))
            {
               chkSoapInHouseObjB.setChecked(false);
            }
            if(String.valueOf(item.getSoapInHouseObjC()).equals("1"))
            {
               chkSoapInHouseObjC.setChecked(true);
            }
            else if(String.valueOf(item.getSoapInHouseObjC()).equals("2"))
            {
               chkSoapInHouseObjC.setChecked(false);
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
            String[] d_rdogrpRoomSleepDk = new String[] {"8","9"};
            for (int i = 0; i < d_rdogrpRoomSleepDk.length; i++)
            {
               if (String.valueOf(item.getRoomSleepDk()).equals(String.valueOf(d_rdogrpRoomSleepDk[i])))
               {
                  rb = (RadioButton) rdogrpRoomSleepDk.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpHomesteadAny = new String[] {"0","1","8","9"};
            for (int i = 0; i < d_rdogrpHomesteadAny.length; i++)
            {
               if (String.valueOf(item.getHomesteadAny()).equals(String.valueOf(d_rdogrpHomesteadAny[i])))
               {
                  rb = (RadioButton) rdogrpHomesteadAny.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpOthLand = new String[] {"0","1","8","9"};
            for (int i = 0; i < d_rdogrpOthLand.length; i++)
            {
               if (String.valueOf(item.getOthLand()).equals(String.valueOf(d_rdogrpOthLand[i])))
               {
                  rb = (RadioButton) rdogrpOthLand.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpElectricity = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpElectricity.length; i++)
            {
               if (String.valueOf(item.getElectricity()).equals(String.valueOf(d_rdogrpElectricity[i])))
               {
                  rb = (RadioButton) rdogrpElectricity.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpHeater = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpHeater.length; i++)
            {
               if (String.valueOf(item.getHeater()).equals(String.valueOf(d_rdogrpHeater[i])))
               {
                  rb = (RadioButton) rdogrpHeater.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpAC = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpAC.length; i++)
            {
               if (String.valueOf(item.getAC()).equals(String.valueOf(d_rdogrpAC[i])))
               {
                  rb = (RadioButton) rdogrpAC.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpElecFan = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpElecFan.length; i++)
            {
               if (String.valueOf(item.getElecFan()).equals(String.valueOf(d_rdogrpElecFan[i])))
               {
                  rb = (RadioButton) rdogrpElecFan.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpLantern = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpLantern.length; i++)
            {
               if (String.valueOf(item.getLantern()).equals(String.valueOf(d_rdogrpLantern[i])))
               {
                  rb = (RadioButton) rdogrpLantern.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpLamp = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpLamp.length; i++)
            {
               if (String.valueOf(item.getLamp()).equals(String.valueOf(d_rdogrpLamp[i])))
               {
                  rb = (RadioButton) rdogrpLamp.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpGasLamp = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpGasLamp.length; i++)
            {
               if (String.valueOf(item.getGasLamp()).equals(String.valueOf(d_rdogrpGasLamp[i])))
               {
                  rb = (RadioButton) rdogrpGasLamp.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpPetroleum = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpPetroleum.length; i++)
            {
               if (String.valueOf(item.getPetroleum()).equals(String.valueOf(d_rdogrpPetroleum[i])))
               {
                  rb = (RadioButton) rdogrpPetroleum.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpMatt = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpMatt.length; i++)
            {
               if (String.valueOf(item.getMatt()).equals(String.valueOf(d_rdogrpMatt[i])))
               {
                  rb = (RadioButton) rdogrpMatt.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpMats = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpMats.length; i++)
            {
               if (String.valueOf(item.getMats()).equals(String.valueOf(d_rdogrpMats[i])))
               {
                  rb = (RadioButton) rdogrpMats.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpCarpets = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpCarpets.length; i++)
            {
               if (String.valueOf(item.getCarpets()).equals(String.valueOf(d_rdogrpCarpets[i])))
               {
                  rb = (RadioButton) rdogrpCarpets.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpBed = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpBed.length; i++)
            {
               if (String.valueOf(item.getBed()).equals(String.valueOf(d_rdogrpBed[i])))
               {
                  rb = (RadioButton) rdogrpBed.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpChair = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpChair.length; i++)
            {
               if (String.valueOf(item.getChair()).equals(String.valueOf(d_rdogrpChair[i])))
               {
                  rb = (RadioButton) rdogrpChair.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpSofa = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpSofa.length; i++)
            {
               if (String.valueOf(item.getSofa()).equals(String.valueOf(d_rdogrpSofa[i])))
               {
                  rb = (RadioButton) rdogrpSofa.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpTables = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpTables.length; i++)
            {
               if (String.valueOf(item.getTables()).equals(String.valueOf(d_rdogrpTables[i])))
               {
                  rb = (RadioButton) rdogrpTables.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpWatch = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpWatch.length; i++)
            {
               if (String.valueOf(item.getWatch()).equals(String.valueOf(d_rdogrpWatch[i])))
               {
                  rb = (RadioButton) rdogrpWatch.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpWMachine = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpWMachine.length; i++)
            {
               if (String.valueOf(item.getWMachine()).equals(String.valueOf(d_rdogrpWMachine[i])))
               {
                  rb = (RadioButton) rdogrpWMachine.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpIron = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpIron.length; i++)
            {
               if (String.valueOf(item.getIron()).equals(String.valueOf(d_rdogrpIron[i])))
               {
                  rb = (RadioButton) rdogrpIron.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpBooth = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpBooth.length; i++)
            {
               if (String.valueOf(item.getBooth()).equals(String.valueOf(d_rdogrpBooth[i])))
               {
                  rb = (RadioButton) rdogrpBooth.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpSMachine = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpSMachine.length; i++)
            {
               if (String.valueOf(item.getSMachine()).equals(String.valueOf(d_rdogrpSMachine[i])))
               {
                  rb = (RadioButton) rdogrpSMachine.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpGenerator = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpGenerator.length; i++)
            {
               if (String.valueOf(item.getGenerator()).equals(String.valueOf(d_rdogrpGenerator[i])))
               {
                  rb = (RadioButton) rdogrpGenerator.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpInternet = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpInternet.length; i++)
            {
               if (String.valueOf(item.getInternet()).equals(String.valueOf(d_rdogrpInternet[i])))
               {
                  rb = (RadioButton) rdogrpInternet.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpSatellite = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpSatellite.length; i++)
            {
               if (String.valueOf(item.getSatellite()).equals(String.valueOf(d_rdogrpSatellite[i])))
               {
                  rb = (RadioButton) rdogrpSatellite.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpLandline = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpLandline.length; i++)
            {
               if (String.valueOf(item.getLandline()).equals(String.valueOf(d_rdogrpLandline[i])))
               {
                  rb = (RadioButton) rdogrpLandline.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpCellphone = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpCellphone.length; i++)
            {
               if (String.valueOf(item.getCellphone()).equals(String.valueOf(d_rdogrpCellphone[i])))
               {
                  rb = (RadioButton) rdogrpCellphone.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpTV = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpTV.length; i++)
            {
               if (String.valueOf(item.getTV()).equals(String.valueOf(d_rdogrpTV[i])))
               {
                  rb = (RadioButton) rdogrpTV.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpTV5 = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpTV5.length; i++)
            {
               if (String.valueOf(item.getTV5()).equals(String.valueOf(d_rdogrpTV5[i])))
               {
                  rb = (RadioButton) rdogrpTV5.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpChannel = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpChannel.length; i++)
            {
               if (String.valueOf(item.getChannel()).equals(String.valueOf(d_rdogrpChannel[i])))
               {
                  rb = (RadioButton) rdogrpChannel.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpRadio = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpRadio.length; i++)
            {
               if (String.valueOf(item.getRadio()).equals(String.valueOf(d_rdogrpRadio[i])))
               {
                  rb = (RadioButton) rdogrpRadio.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpDVD = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpDVD.length; i++)
            {
               if (String.valueOf(item.getDVD()).equals(String.valueOf(d_rdogrpDVD[i])))
               {
                  rb = (RadioButton) rdogrpDVD.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpVideo = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpVideo.length; i++)
            {
               if (String.valueOf(item.getVideo()).equals(String.valueOf(d_rdogrpVideo[i])))
               {
                  rb = (RadioButton) rdogrpVideo.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpComputer = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpComputer.length; i++)
            {
               if (String.valueOf(item.getComputer()).equals(String.valueOf(d_rdogrpComputer[i])))
               {
                  rb = (RadioButton) rdogrpComputer.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpLaptop = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpLaptop.length; i++)
            {
               if (String.valueOf(item.getLaptop()).equals(String.valueOf(d_rdogrpLaptop[i])))
               {
                  rb = (RadioButton) rdogrpLaptop.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpCable = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpCable.length; i++)
            {
               if (String.valueOf(item.getCable()).equals(String.valueOf(d_rdogrpCable[i])))
               {
                  rb = (RadioButton) rdogrpCable.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpMicrowave = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpMicrowave.length; i++)
            {
               if (String.valueOf(item.getMicrowave()).equals(String.valueOf(d_rdogrpMicrowave[i])))
               {
                  rb = (RadioButton) rdogrpMicrowave.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpGeyser = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpGeyser.length; i++)
            {
               if (String.valueOf(item.getGeyser()).equals(String.valueOf(d_rdogrpGeyser[i])))
               {
                  rb = (RadioButton) rdogrpGeyser.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpGrill = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpGrill.length; i++)
            {
               if (String.valueOf(item.getGrill()).equals(String.valueOf(d_rdogrpGrill[i])))
               {
                  rb = (RadioButton) rdogrpGrill.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpGrain = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpGrain.length; i++)
            {
               if (String.valueOf(item.getGrain()).equals(String.valueOf(d_rdogrpGrain[i])))
               {
                  rb = (RadioButton) rdogrpGrain.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpRefrigerator = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpRefrigerator.length; i++)
            {
               if (String.valueOf(item.getRefrigerator()).equals(String.valueOf(d_rdogrpRefrigerator[i])))
               {
                  rb = (RadioButton) rdogrpRefrigerator.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpDeepFreezer = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpDeepFreezer.length; i++)
            {
               if (String.valueOf(item.getDeepFreezer()).equals(String.valueOf(d_rdogrpDeepFreezer[i])))
               {
                  rb = (RadioButton) rdogrpDeepFreezer.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpStove = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpStove.length; i++)
            {
               if (String.valueOf(item.getStove()).equals(String.valueOf(d_rdogrpStove[i])))
               {
                  rb = (RadioButton) rdogrpStove.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpGasHob = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpGasHob.length; i++)
            {
               if (String.valueOf(item.getGasHob()).equals(String.valueOf(d_rdogrpGasHob[i])))
               {
                  rb = (RadioButton) rdogrpGasHob.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpImpCooker = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpImpCooker.length; i++)
            {
               if (String.valueOf(item.getImpCooker()).equals(String.valueOf(d_rdogrpImpCooker[i])))
               {
                  rb = (RadioButton) rdogrpImpCooker.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpBike = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpBike.length; i++)
            {
               if (String.valueOf(item.getBike()).equals(String.valueOf(d_rdogrpBike[i])))
               {
                  rb = (RadioButton) rdogrpBike.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpMotorcycle = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpMotorcycle.length; i++)
            {
               if (String.valueOf(item.getMotorcycle()).equals(String.valueOf(d_rdogrpMotorcycle[i])))
               {
                  rb = (RadioButton) rdogrpMotorcycle.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpCar = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpCar.length; i++)
            {
               if (String.valueOf(item.getCar()).equals(String.valueOf(d_rdogrpCar[i])))
               {
                  rb = (RadioButton) rdogrpCar.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpRickshaw = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpRickshaw.length; i++)
            {
               if (String.valueOf(item.getRickshaw()).equals(String.valueOf(d_rdogrpRickshaw[i])))
               {
                  rb = (RadioButton) rdogrpRickshaw.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpCart = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpCart.length; i++)
            {
               if (String.valueOf(item.getCart()).equals(String.valueOf(d_rdogrpCart[i])))
               {
                  rb = (RadioButton) rdogrpCart.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpCanoe = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpCanoe.length; i++)
            {
               if (String.valueOf(item.getCanoe()).equals(String.valueOf(d_rdogrpCanoe[i])))
               {
                  rb = (RadioButton) rdogrpCanoe.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpBus = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpBus.length; i++)
            {
               if (String.valueOf(item.getBus()).equals(String.valueOf(d_rdogrpBus[i])))
               {
                  rb = (RadioButton) rdogrpBus.getChildAt(i);
                  rb.setChecked(true);
               }
            }



            String[] d_rdogrpTricycle = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpTricycle.length; i++)
            {
               if (String.valueOf(item.getTricycle()).equals(String.valueOf(d_rdogrpTricycle[i])))
               {
                  rb = (RadioButton) rdogrpTricycle.getChildAt(i);
                  rb.setChecked(true);
               }
            }

            String[] d_rdogrpBoatWithMot = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpBoatWithMot.length; i++)
            {
               if (String.valueOf(item.getBoatWithMot()).equals(String.valueOf(d_rdogrpBoatWithMot[i])))
               {
                  rb = (RadioButton) rdogrpBoatWithMot.getChildAt(i);
                  rb.setChecked(true);
               }
            }




            String[] d_rdogrpTractor = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpTractor.length; i++)
            {
               if (String.valueOf(item.getTractor()).equals(String.valueOf(d_rdogrpTractor[i])))
               {
                  rb = (RadioButton) rdogrpTractor.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpPlow = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpPlow.length; i++)
            {
               if (String.valueOf(item.getPlow()).equals(String.valueOf(d_rdogrpPlow[i])))
               {
                  rb = (RadioButton) rdogrpPlow.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpDuck = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpDuck.length; i++)
            {
               if (String.valueOf(item.getDuck()).equals(String.valueOf(d_rdogrpDuck[i])))
               {
                  rb = (RadioButton) rdogrpDuck.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpCow = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpCow.length; i++)
            {
               if (String.valueOf(item.getCow()).equals(String.valueOf(d_rdogrpCow[i])))
               {
                  rb = (RadioButton) rdogrpCow.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpSheep = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpSheep.length; i++)
            {
               if (String.valueOf(item.getSheep()).equals(String.valueOf(d_rdogrpSheep[i])))
               {
                  rb = (RadioButton) rdogrpSheep.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpGoat = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpGoat.length; i++)
            {
               if (String.valueOf(item.getGoat()).equals(String.valueOf(d_rdogrpGoat[i])))
               {
                  rb = (RadioButton) rdogrpGoat.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpChicken = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpChicken.length; i++)
            {
               if (String.valueOf(item.getChicken()).equals(String.valueOf(d_rdogrpChicken[i])))
               {
                  rb = (RadioButton) rdogrpChicken.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpDonkey = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpDonkey.length; i++)
            {
               if (String.valueOf(item.getDonkey()).equals(String.valueOf(d_rdogrpDonkey[i])))
               {
                  rb = (RadioButton) rdogrpDonkey.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpHorse = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpHorse.length; i++)
            {
               if (String.valueOf(item.getHorse()).equals(String.valueOf(d_rdogrpHorse[i])))
               {
                  rb = (RadioButton) rdogrpHorse.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpPig = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpPig.length; i++)
            {
               if (String.valueOf(item.getPig()).equals(String.valueOf(d_rdogrpPig[i])))
               {
                  rb = (RadioButton) rdogrpPig.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpBirds = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpBirds.length; i++)
            {
               if (String.valueOf(item.getBirds()).equals(String.valueOf(d_rdogrpBirds[i])))
               {
                  rb = (RadioButton) rdogrpBirds.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpDogs = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpDogs.length; i++)
            {
               if (String.valueOf(item.getDogs()).equals(String.valueOf(d_rdogrpDogs[i])))
               {
                  rb = (RadioButton) rdogrpDogs.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpCats = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpCats.length; i++)
            {
               if (String.valueOf(item.getCats()).equals(String.valueOf(d_rdogrpCats[i])))
               {
                  rb = (RadioButton) rdogrpCats.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpFishNet = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpFishNet.length; i++)
            {
               if (String.valueOf(item.getFishNet()).equals(String.valueOf(d_rdogrpFishNet[i])))
               {
                  rb = (RadioButton) rdogrpFishNet.getChildAt(i);
                  rb.setChecked(true);
               }
            }
            String[] d_rdogrpOtherAsset = new String[] {"0","1"};
            for (int i = 0; i < d_rdogrpOtherAsset.length; i++)
            {
               if (String.valueOf(item.getOtherAsset()).equals(String.valueOf(d_rdogrpOtherAsset[i])))
               {
                  rb = (RadioButton) rdogrpOtherAsset.getChildAt(i);
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
         Connection.MessageBox(Surv_SES_CrossRiver.this, e.getMessage());
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
         EditText txtDate;


         txtDate = findViewById(R.id.txtSESVDate);
         if (VariableID.equals("btnSESVDate"))
         {
            txtDate = findViewById(R.id.txtSESVDate);
         }
         txtDate.setText(new StringBuilder()
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