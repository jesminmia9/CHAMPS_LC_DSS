package forms_datamodel;

import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Common.Global;
import Utility.AuditTrial;

import android.content.ContentValues;
 public class SES_Mali_DataModel{

        private String _HHID = "";
        public String getHHID(){
              return _HHID;
         }
        public void setHHID(String newValue){
              _HHID = newValue;
         }
        private String _SESNo = "";
        public String getSESNo(){
              return _SESNo;
         }
        public void setSESNo(String newValue){
              _SESNo = newValue;
         }
        private String _SESVDate = "";
        public String getSESVDate(){
              return _SESVDate;
         }
        public void setSESVDate(String newValue){
              _SESVDate = newValue;
         }
        private String _SESVStatus = "";
        public String getSESVStatus(){
              return _SESVStatus;
         }
        public void setSESVStatus(String newValue){
              _SESVStatus = newValue;
         }
        private String _SESVStatusOth = "";
        public String getSESVStatusOth(){
              return _SESVStatusOth;
         }
        public void setSESVStatusOth(String newValue){
              _SESVStatusOth = newValue;
         }
        private String _Rnd = "";
        public String getRnd(){
              return _Rnd;
         }
        public void setRnd(String newValue){
              _Rnd = newValue;
         }
        private String _WSDrink = "";
        public String getWSDrink(){
              return _WSDrink;
         }
        public void setWSDrink(String newValue){
              _WSDrink = newValue;
         }
        private String _WSDrinkOth = "";
        public String getWSDrinkOth(){
              return _WSDrinkOth;
         }
        public void setWSDrinkOth(String newValue){
              _WSDrinkOth = newValue;
         }
        private String _WaterSource = "";
        public String getWaterSource(){
              return _WaterSource;
         }
        public void setWaterSource(String newValue){
              _WaterSource = newValue;
         }
        private String _FetchWaterM = "";
        public String getFetchWaterM(){
              return _FetchWaterM;
         }
        public void setFetchWaterM(String newValue){
              _FetchWaterM = newValue;
         }
        private String _FetchWaterMDk = "";
        public String getFetchWaterMDk(){
              return _FetchWaterMDk;
         }
        public void setFetchWaterMDk(String newValue){
              _FetchWaterMDk = newValue;
         }
        private String _GetWater = "";
        public String getGetWater(){
              return _GetWater;
         }
        public void setGetWater(String newValue){
              _GetWater = newValue;
         }
        private String _GetWaterOth = "";
        public String getGetWaterOth(){
              return _GetWaterOth;
         }
        public void setGetWaterOth(String newValue){
              _GetWaterOth = newValue;
         }
        private String _MemberID = "";
        public String getMemberID(){
              return _MemberID;
         }
        public void setMemberID(String newValue){
              _MemberID = newValue;
         }
        private String _BringWater = "";
        public String getBringWater(){
              return _BringWater;
         }
        public void setBringWater(String newValue){
              _BringWater = newValue;
         }
        private String _BringWaterDk = "";
        public String getBringWaterDk(){
              return _BringWaterDk;
         }
        public void setBringWaterDk(String newValue){
              _BringWaterDk = newValue;
         }
        private String _Someone = "";
        public String getSomeone(){
              return _Someone;
         }
        public void setSomeone(String newValue){
              _Someone = newValue;
         }
        private String _SecondPers = "";
        public String getSecondPers(){
              return _SecondPers;
         }
        public void setSecondPers(String newValue){
              _SecondPers = newValue;
         }
        private String _SecondPersOth = "";
        public String getSecondPersOth(){
              return _SecondPersOth;
         }
        public void setSecondPersOth(String newValue){
              _SecondPersOth = newValue;
         }
        private String _MemberID2nd = "";
        public String getMemberID2nd(){
              return _MemberID2nd;
         }
        public void setMemberID2nd(String newValue){
              _MemberID2nd = newValue;
         }
        private String _EnoughWater = "";
        public String getEnoughWater(){
              return _EnoughWater;
         }
        public void setEnoughWater(String newValue){
              _EnoughWater = newValue;
         }
        private String _MainWater = "";
        public String getMainWater(){
              return _MainWater;
         }
        public void setMainWater(String newValue){
              _MainWater = newValue;
         }
        private String _MainWaterOth = "";
        public String getMainWaterOth(){
              return _MainWaterOth;
         }
        public void setMainWaterOth(String newValue){
              _MainWaterOth = newValue;
         }
        private String _SmallTank = "";
        public String getSmallTank(){
              return _SmallTank;
         }
        public void setSmallTank(String newValue){
              _SmallTank = newValue;
         }
        private String _MediunTank = "";
        public String getMediunTank(){
              return _MediunTank;
         }
        public void setMediunTank(String newValue){
              _MediunTank = newValue;
         }
        private String _LargeTank = "";
        public String getLargeTank(){
              return _LargeTank;
         }
        public void setLargeTank(String newValue){
              _LargeTank = newValue;
         }
        private String _StoreDrink = "";
        public String getStoreDrink(){
              return _StoreDrink;
         }
        public void setStoreDrink(String newValue){
              _StoreDrink = newValue;
         }
        private String _ContainOpenCov = "";
        public String getContainOpenCov(){
              return _ContainOpenCov;
         }
        public void setContainOpenCov(String newValue){
              _ContainOpenCov = newValue;
         }
        private String _ContainOpenNotCov = "";
        public String getContainOpenNotCov(){
              return _ContainOpenNotCov;
         }
        public void setContainOpenNotCov(String newValue){
              _ContainOpenNotCov = newValue;
         }
        private String _ContainOpenDK = "";
        public String getContainOpenDK(){
              return _ContainOpenDK;
         }
        public void setContainOpenDK(String newValue){
              _ContainOpenDK = newValue;
         }
        private String _RecoverWater = "";
        public String getRecoverWater(){
              return _RecoverWater;
         }
        public void setRecoverWater(String newValue){
              _RecoverWater = newValue;
         }
        private String _RecoverWaterOth = "";
        public String getRecoverWaterOth(){
              return _RecoverWaterOth;
         }
        public void setRecoverWaterOth(String newValue){
              _RecoverWaterOth = newValue;
         }
        private String _LessDanger = "";
        public String getLessDanger(){
              return _LessDanger;
         }
        public void setLessDanger(String newValue){
              _LessDanger = newValue;
         }
        private String _MakeSafer = "";
        public String getMakeSafer(){
              return _MakeSafer;
         }
        public void setMakeSafer(String newValue){
              _MakeSafer = newValue;
         }
        private String _MakeSaferOth = "";
        public String getMakeSaferOth(){
              return _MakeSaferOth;
         }
        public void setMakeSaferOth(String newValue){
              _MakeSaferOth = newValue;
         }
        private String _Toilet = "";
        public String getToilet(){
              return _Toilet;
         }
        public void setToilet(String newValue){
              _Toilet = newValue;
         }
        private String _ToiletOth = "";
        public String getToiletOth(){
              return _ToiletOth;
         }
        public void setToiletOth(String newValue){
              _ToiletOth = newValue;
         }
        private String _ToiletShrd = "";
        public String getToiletShrd(){
              return _ToiletShrd;
         }
        public void setToiletShrd(String newValue){
              _ToiletShrd = newValue;
         }
        private String _ToiletUseNo = "";
        public String getToiletUseNo(){
              return _ToiletUseNo;
         }
        public void setToiletUseNo(String newValue){
              _ToiletUseNo = newValue;
         }
        private String _ToiletUseNoDk = "";
        public String getToiletUseNoDk(){
              return _ToiletUseNoDk;
         }
        public void setToiletUseNoDk(String newValue){
              _ToiletUseNoDk = newValue;
         }
        private String _ToiletLoc = "";
        public String getToiletLoc(){
              return _ToiletLoc;
         }
        public void setToiletLoc(String newValue){
              _ToiletLoc = newValue;
         }
        private String _ContentEmp = "";
        public String getContentEmp(){
              return _ContentEmp;
         }
        public void setContentEmp(String newValue){
              _ContentEmp = newValue;
         }
        private String _ContentEmpOth = "";
        public String getContentEmpOth(){
              return _ContentEmpOth;
         }
        public void setContentEmpOth(String newValue){
              _ContentEmpOth = newValue;
         }
        private String _BowelMov = "";
        public String getBowelMov(){
              return _BowelMov;
         }
        public void setBowelMov(String newValue){
              _BowelMov = newValue;
         }
        private String _BowelMovOth = "";
        public String getBowelMovOth(){
              return _BowelMovOth;
         }
        public void setBowelMovOth(String newValue){
              _BowelMovOth = newValue;
         }
        private String _LiquidWaste = "";
        public String getLiquidWaste(){
              return _LiquidWaste;
         }
        public void setLiquidWaste(String newValue){
              _LiquidWaste = newValue;
         }
        private String _LiquidWasteOth = "";
        public String getLiquidWasteOth(){
              return _LiquidWasteOth;
         }
        public void setLiquidWasteOth(String newValue){
              _LiquidWasteOth = newValue;
         }
        private String _SolidWasteMethod = "";
        public String getSolidWasteMethod(){
              return _SolidWasteMethod;
         }
        public void setSolidWasteMethod(String newValue){
              _SolidWasteMethod = newValue;
         }
        private String _SolidWasteMethodOth = "";
        public String getSolidWasteMethodOth(){
              return _SolidWasteMethodOth;
         }
        public void setSolidWasteMethodOth(String newValue){
              _SolidWasteMethodOth = newValue;
         }
        private String _HandWash = "";
        public String getHandWash(){
              return _HandWash;
         }
        public void setHandWash(String newValue){
              _HandWash = newValue;
         }
        private String _ShowWash = "";
        public String getShowWash(){
              return _ShowWash;
         }
        public void setShowWash(String newValue){
              _ShowWash = newValue;
         }
        private String _AvailableWat = "";
        public String getAvailableWat(){
              return _AvailableWat;
         }
        public void setAvailableWat(String newValue){
              _AvailableWat = newValue;
         }
        private String _AvailableSoap = "";
        public String getAvailableSoap(){
              return _AvailableSoap;
         }
        public void setAvailableSoap(String newValue){
              _AvailableSoap = newValue;
         }
        private String _CookDvc = "";
        public String getCookDvc(){
              return _CookDvc;
         }
        public void setCookDvc(String newValue){
              _CookDvc = newValue;
         }
        private String _CookDvcOth = "";
        public String getCookDvcOth(){
              return _CookDvcOth;
         }
        public void setCookDvcOth(String newValue){
              _CookDvcOth = newValue;
         }
        private String _CookFuel = "";
        public String getCookFuel(){
              return _CookFuel;
         }
        public void setCookFuel(String newValue){
              _CookFuel = newValue;
         }
        private String _CookFuelOth = "";
        public String getCookFuelOth(){
              return _CookFuelOth;
         }
        public void setCookFuelOth(String newValue){
              _CookFuelOth = newValue;
         }
        private String _CookPlc = "";
        public String getCookPlc(){
              return _CookPlc;
         }
        public void setCookPlc(String newValue){
              _CookPlc = newValue;
         }
        private String _CookPlcOth = "";
        public String getCookPlcOth(){
              return _CookPlcOth;
         }
        public void setCookPlcOth(String newValue){
              _CookPlcOth = newValue;
         }
        private String _Floor = "";
        public String getFloor(){
              return _Floor;
         }
        public void setFloor(String newValue){
              _Floor = newValue;
         }
        private String _FloorOth = "";
        public String getFloorOth(){
              return _FloorOth;
         }
        public void setFloorOth(String newValue){
              _FloorOth = newValue;
         }
        private String _GroundMat = "";
        public String getGroundMat(){
              return _GroundMat;
         }
        public void setGroundMat(String newValue){
              _GroundMat = newValue;
         }
        private String _GroundMatOth = "";
        public String getGroundMatOth(){
              return _GroundMatOth;
         }
        public void setGroundMatOth(String newValue){
              _GroundMatOth = newValue;
         }
        private String _Roof = "";
        public String getRoof(){
              return _Roof;
         }
        public void setRoof(String newValue){
              _Roof = newValue;
         }
        private String _RoofOth = "";
        public String getRoofOth(){
              return _RoofOth;
         }
        public void setRoofOth(String newValue){
              _RoofOth = newValue;
         }
        private String _SmokeInside = "";
        public String getSmokeInside(){
              return _SmokeInside;
         }
        public void setSmokeInside(String newValue){
              _SmokeInside = newValue;
         }
        private String _FreqSmoke = "";
        public String getFreqSmoke(){
              return _FreqSmoke;
         }
        public void setFreqSmoke(String newValue){
              _FreqSmoke = newValue;
         }
        private String _Wall = "";
        public String getWall(){
              return _Wall;
         }
        public void setWall(String newValue){
              _Wall = newValue;
         }
        private String _WallOth = "";
        public String getWallOth(){
              return _WallOth;
         }
        public void setWallOth(String newValue){
              _WallOth = newValue;
         }
        private String _RoomSleep = "";
        public String getRoomSleep(){
              return _RoomSleep;
         }
        public void setRoomSleep(String newValue){
              _RoomSleep = newValue;
         }
        private String _RoomSleepDk = "";
        public String getRoomSleepDk(){
              return _RoomSleepDk;
         }
        public void setRoomSleepDk(String newValue){
              _RoomSleepDk = newValue;
         }
        private String _ElecNight = "";
        public String getElecNight(){
              return _ElecNight;
         }
        public void setElecNight(String newValue){
              _ElecNight = newValue;
         }
        private String _ElecNightOth = "";
        public String getElecNightOth(){
              return _ElecNightOth;
         }
        public void setElecNightOth(String newValue){
              _ElecNightOth = newValue;
         }
        private String _HomesteadAny = "";
        public String getHomesteadAny(){
              return _HomesteadAny;
         }
        public void setHomesteadAny(String newValue){
              _HomesteadAny = newValue;
         }
        private String _OthLand = "";
        public String getOthLand(){
              return _OthLand;
         }
        public void setOthLand(String newValue){
              _OthLand = newValue;
         }
        private String _Area = "";
        public String getArea(){
              return _Area;
         }
        public void setArea(String newValue){
              _Area = newValue;
         }
        private String _IncomeMo = "";
        public String getIncomeMo(){
              return _IncomeMo;
         }
        public void setIncomeMo(String newValue){
              _IncomeMo = newValue;
         }
        private String _Expenses = "";
        public String getExpenses(){
              return _Expenses;
         }
        public void setExpenses(String newValue){
              _Expenses = newValue;
         }
        private String _BankAcc = "";
        public String getBankAcc(){
              return _BankAcc;
         }
        public void setBankAcc(String newValue){
              _BankAcc = newValue;
         }
        private String _SprayInt = "";
        public String getSprayInt(){
              return _SprayInt;
         }
        public void setSprayInt(String newValue){
              _SprayInt = newValue;
         }
        private String _MosqNet = "";
        public String getMosqNet(){
              return _MosqNet;
         }
        public void setMosqNet(String newValue){
              _MosqNet = newValue;
         }
        private String _NetOwned = "";
        public String getNetOwned(){
              return _NetOwned;
         }
        public void setNetOwned(String newValue){
              _NetOwned = newValue;
         }
        private String _MedHome = "";
        public String getMedHome(){
              return _MedHome;
         }
        public void setMedHome(String newValue){
              _MedHome = newValue;
         }
        private String _MedTypeAM = "";
        public String getMedTypeAM(){
              return _MedTypeAM;
         }
        public void setMedTypeAM(String newValue){
              _MedTypeAM = newValue;
         }
        private String _MedTypeAB = "";
        public String getMedTypeAB(){
              return _MedTypeAB;
         }
        public void setMedTypeAB(String newValue){
              _MedTypeAB = newValue;
         }
        private String _MedTypeDK = "";
        public String getMedTypeDK(){
              return _MedTypeDK;
         }
        public void setMedTypeDK(String newValue){
              _MedTypeDK = newValue;
         }
        private String _AntimalarAL = "";
        public String getAntimalarAL(){
              return _AntimalarAL;
         }
        public void setAntimalarAL(String newValue){
              _AntimalarAL = newValue;
         }
        private String _AntimalarASAQ = "";
        public String getAntimalarASAQ(){
              return _AntimalarASAQ;
         }
        public void setAntimalarASAQ(String newValue){
              _AntimalarASAQ = newValue;
         }
        private String _AntimalarSP = "";
        public String getAntimalarSP(){
              return _AntimalarSP;
         }
        public void setAntimalarSP(String newValue){
              _AntimalarSP = newValue;
         }
        private String _AntimalarOth = "";
        public String getAntimalarOth(){
              return _AntimalarOth;
         }
        public void setAntimalarOth(String newValue){
              _AntimalarOth = newValue;
         }
        private String _AntimalarSpecifyOth = "";
        public String getAntimalarSpecifyOth(){
              return _AntimalarSpecifyOth;
         }
        public void setAntimalarSpecifyOth(String newValue){
              _AntimalarSpecifyOth = newValue;
         }
        private String _GetMedHosp = "";
        public String getGetMedHosp(){
              return _GetMedHosp;
         }
        public void setGetMedHosp(String newValue){
              _GetMedHosp = newValue;
         }
        private String _GetMedCSCom = "";
        public String getGetMedCSCom(){
              return _GetMedCSCom;
         }
        public void setGetMedCSCom(String newValue){
              _GetMedCSCom = newValue;
         }
        private String _GetMedPrvCl = "";
        public String getGetMedPrvCl(){
              return _GetMedPrvCl;
         }
        public void setGetMedPrvCl(String newValue){
              _GetMedPrvCl = newValue;
         }
        private String _GetMedPhar = "";
        public String getGetMedPhar(){
              return _GetMedPhar;
         }
        public void setGetMedPhar(String newValue){
              _GetMedPhar = newValue;
         }
        private String _GetMedPD = "";
        public String getGetMedPD(){
              return _GetMedPD;
         }
        public void setGetMedPD(String newValue){
              _GetMedPD = newValue;
         }
        private String _GetMedCHW = "";
        public String getGetMedCHW(){
              return _GetMedCHW;
         }
        public void setGetMedCHW(String newValue){
              _GetMedCHW = newValue;
         }
        private String _GetMedSS = "";
        public String getGetMedSS(){
              return _GetMedSS;
         }
        public void setGetMedSS(String newValue){
              _GetMedSS = newValue;
         }
        private String _GetMedOth = "";
        public String getGetMedOth(){
              return _GetMedOth;
         }
        public void setGetMedOth(String newValue){
              _GetMedOth = newValue;
         }
        private String _GetMedSpecifyOth = "";
        public String getGetMedSpecifyOth(){
              return _GetMedSpecifyOth;
         }
        public void setGetMedSpecifyOth(String newValue){
              _GetMedSpecifyOth = newValue;
         }
        private String _AComment = "";
        public String getAComment(){
              return _AComment;
         }
        public void setAComment(String newValue){
              _AComment = newValue;
         }
        private String _Comment = "";
        public String getComment(){
              return _Comment;
         }
        public void setComment(String newValue){
              _Comment = newValue;
         }
        private String _Electricity = "";
        public String getElectricity(){
              return _Electricity;
         }
        public void setElectricity(String newValue){
              _Electricity = newValue;
         }
        private String _SolarPlate = "";
        public String getSolarPlate(){
              return _SolarPlate;
         }
        public void setSolarPlate(String newValue){
              _SolarPlate = newValue;
         }
        private String _SolarPlateN = "";
        public String getSolarPlateN(){
              return _SolarPlateN;
         }
        public void setSolarPlateN(String newValue){
              _SolarPlateN = newValue;
         }
        private String _Heater = "";
        public String getHeater(){
              return _Heater;
         }
        public void setHeater(String newValue){
              _Heater = newValue;
         }
        private String _HeaterN = "";
        public String getHeaterN(){
              return _HeaterN;
         }
        public void setHeaterN(String newValue){
              _HeaterN = newValue;
         }
        private String _AC = "";
        public String getAC(){
              return _AC;
         }
        public void setAC(String newValue){
              _AC = newValue;
         }
        private String _ACN = "";
        public String getACN(){
              return _ACN;
         }
        public void setACN(String newValue){
              _ACN = newValue;
         }
        private String _ElecFan = "";
        public String getElecFan(){
              return _ElecFan;
         }
        public void setElecFan(String newValue){
              _ElecFan = newValue;
         }
        private String _ElecFanN = "";
        public String getElecFanN(){
              return _ElecFanN;
         }
        public void setElecFanN(String newValue){
              _ElecFanN = newValue;
         }
        private String _Lantern = "";
        public String getLantern(){
              return _Lantern;
         }
        public void setLantern(String newValue){
              _Lantern = newValue;
         }
        private String _LanternN = "";
        public String getLanternN(){
              return _LanternN;
         }
        public void setLanternN(String newValue){
              _LanternN = newValue;
         }
        private String _Lamp = "";
        public String getLamp(){
              return _Lamp;
         }
        public void setLamp(String newValue){
              _Lamp = newValue;
         }
        private String _LampN = "";
        public String getLampN(){
              return _LampN;
         }
        public void setLampN(String newValue){
              _LampN = newValue;
         }
        private String _GasLamp = "";
        public String getGasLamp(){
              return _GasLamp;
         }
        public void setGasLamp(String newValue){
              _GasLamp = newValue;
         }
        private String _GasLampN = "";
        public String getGasLampN(){
              return _GasLampN;
         }
        public void setGasLampN(String newValue){
              _GasLampN = newValue;
         }
        private String _Petroleum = "";
        public String getPetroleum(){
              return _Petroleum;
         }
        public void setPetroleum(String newValue){
              _Petroleum = newValue;
         }
        private String _PetroleumN = "";
        public String getPetroleumN(){
              return _PetroleumN;
         }
        public void setPetroleumN(String newValue){
              _PetroleumN = newValue;
         }
        private String _Matt = "";
        public String getMatt(){
              return _Matt;
         }
        public void setMatt(String newValue){
              _Matt = newValue;
         }
        private String _MattN = "";
        public String getMattN(){
              return _MattN;
         }
        public void setMattN(String newValue){
              _MattN = newValue;
         }
        private String _Mats = "";
        public String getMats(){
              return _Mats;
         }
        public void setMats(String newValue){
              _Mats = newValue;
         }
        private String _MatsN = "";
        public String getMatsN(){
              return _MatsN;
         }
        public void setMatsN(String newValue){
              _MatsN = newValue;
         }
        private String _Carpets = "";
        public String getCarpets(){
              return _Carpets;
         }
        public void setCarpets(String newValue){
              _Carpets = newValue;
         }
        private String _CarpetsN = "";
        public String getCarpetsN(){
              return _CarpetsN;
         }
        public void setCarpetsN(String newValue){
              _CarpetsN = newValue;
         }
        private String _Bed = "";
        public String getBed(){
              return _Bed;
         }
        public void setBed(String newValue){
              _Bed = newValue;
         }
        private String _BedN = "";
        public String getBedN(){
              return _BedN;
         }
        public void setBedN(String newValue){
              _BedN = newValue;
         }
        private String _Chair = "";
        public String getChair(){
              return _Chair;
         }
        public void setChair(String newValue){
              _Chair = newValue;
         }
        private String _ChairN = "";
        public String getChairN(){
              return _ChairN;
         }
        public void setChairN(String newValue){
              _ChairN = newValue;
         }
        private String _Sofa = "";
        public String getSofa(){
              return _Sofa;
         }
        public void setSofa(String newValue){
              _Sofa = newValue;
         }
        private String _SofaN = "";
        public String getSofaN(){
              return _SofaN;
         }
        public void setSofaN(String newValue){
              _SofaN = newValue;
         }
        private String _Tables = "";
        public String getTables(){
              return _Tables;
         }
        public void setTables(String newValue){
              _Tables = newValue;
         }
        private String _TablesN = "";
        public String getTablesN(){
              return _TablesN;
         }
        public void setTablesN(String newValue){
              _TablesN = newValue;
         }
        private String _Watch = "";
        public String getWatch(){
              return _Watch;
         }
        public void setWatch(String newValue){
              _Watch = newValue;
         }
        private String _WatchN = "";
        public String getWatchN(){
              return _WatchN;
         }
        public void setWatchN(String newValue){
              _WatchN = newValue;
         }
        private String _WMachine = "";
        public String getWMachine(){
              return _WMachine;
         }
        public void setWMachine(String newValue){
              _WMachine = newValue;
         }
        private String _WMachineN = "";
        public String getWMachineN(){
              return _WMachineN;
         }
        public void setWMachineN(String newValue){
              _WMachineN = newValue;
         }
        private String _Iron = "";
        public String getIron(){
              return _Iron;
         }
        public void setIron(String newValue){
              _Iron = newValue;
         }
        private String _IronN = "";
        public String getIronN(){
              return _IronN;
         }
        public void setIronN(String newValue){
              _IronN = newValue;
         }
        private String _Booth = "";
        public String getBooth(){
              return _Booth;
         }
        public void setBooth(String newValue){
              _Booth = newValue;
         }
        private String _BoothN = "";
        public String getBoothN(){
              return _BoothN;
         }
        public void setBoothN(String newValue){
              _BoothN = newValue;
         }
        private String _SMachine = "";
        public String getSMachine(){
              return _SMachine;
         }
        public void setSMachine(String newValue){
              _SMachine = newValue;
         }
        private String _SMachineN = "";
        public String getSMachineN(){
              return _SMachineN;
         }
        public void setSMachineN(String newValue){
              _SMachineN = newValue;
         }
        private String _Generator = "";
        public String getGenerator(){
              return _Generator;
         }
        public void setGenerator(String newValue){
              _Generator = newValue;
         }
        private String _GeneratorN = "";
        public String getGeneratorN(){
              return _GeneratorN;
         }
        public void setGeneratorN(String newValue){
              _GeneratorN = newValue;
         }
        private String _Internet = "";
        public String getInternet(){
              return _Internet;
         }
        public void setInternet(String newValue){
              _Internet = newValue;
         }
        private String _InternetN = "";
        public String getInternetN(){
              return _InternetN;
         }
        public void setInternetN(String newValue){
              _InternetN = newValue;
         }
        private String _Satellite = "";
        public String getSatellite(){
              return _Satellite;
         }
        public void setSatellite(String newValue){
              _Satellite = newValue;
         }
        private String _SatelliteN = "";
        public String getSatelliteN(){
              return _SatelliteN;
         }
        public void setSatelliteN(String newValue){
              _SatelliteN = newValue;
         }
        private String _Landline = "";
        public String getLandline(){
              return _Landline;
         }
        public void setLandline(String newValue){
              _Landline = newValue;
         }
        private String _LandlineN = "";
        public String getLandlineN(){
              return _LandlineN;
         }
        public void setLandlineN(String newValue){
              _LandlineN = newValue;
         }
        private String _Cellphone = "";
        public String getCellphone(){
              return _Cellphone;
         }
        public void setCellphone(String newValue){
              _Cellphone = newValue;
         }
        private String _CellphoneN = "";
        public String getCellphoneN(){
              return _CellphoneN;
         }
        public void setCellphoneN(String newValue){
              _CellphoneN = newValue;
         }
        private String _TV = "";
        public String getTV(){
              return _TV;
         }
        public void setTV(String newValue){
              _TV = newValue;
         }
        private String _TVN = "";
        public String getTVN(){
              return _TVN;
         }
        public void setTVN(String newValue){
              _TVN = newValue;
         }
        private String _TV5 = "";
        public String getTV5(){
              return _TV5;
         }
        public void setTV5(String newValue){
              _TV5 = newValue;
         }
        private String _TV5N = "";
        public String getTV5N(){
              return _TV5N;
         }
        public void setTV5N(String newValue){
              _TV5N = newValue;
         }
        private String _Channel = "";
        public String getChannel(){
              return _Channel;
         }
        public void setChannel(String newValue){
              _Channel = newValue;
         }
        private String _ChannelN = "";
        public String getChannelN(){
              return _ChannelN;
         }
        public void setChannelN(String newValue){
              _ChannelN = newValue;
         }
        private String _Radio = "";
        public String getRadio(){
              return _Radio;
         }
        public void setRadio(String newValue){
              _Radio = newValue;
         }
        private String _RadioN = "";
        public String getRadioN(){
              return _RadioN;
         }
        public void setRadioN(String newValue){
              _RadioN = newValue;
         }
        private String _DVD = "";
        public String getDVD(){
              return _DVD;
         }
        public void setDVD(String newValue){
              _DVD = newValue;
         }
        private String _DVDN = "";
        public String getDVDN(){
              return _DVDN;
         }
        public void setDVDN(String newValue){
              _DVDN = newValue;
         }
        private String _Video = "";
        public String getVideo(){
              return _Video;
         }
        public void setVideo(String newValue){
              _Video = newValue;
         }
        private String _VideoN = "";
        public String getVideoN(){
              return _VideoN;
         }
        public void setVideoN(String newValue){
              _VideoN = newValue;
         }
        private String _Computer = "";
        public String getComputer(){
              return _Computer;
         }
        public void setComputer(String newValue){
              _Computer = newValue;
         }
        private String _ComputerN = "";
        public String getComputerN(){
              return _ComputerN;
         }
        public void setComputerN(String newValue){
              _ComputerN = newValue;
         }
        private String _Laptop = "";
        public String getLaptop(){
              return _Laptop;
         }
        public void setLaptop(String newValue){
              _Laptop = newValue;
         }
        private String _LaptopN = "";
        public String getLaptopN(){
              return _LaptopN;
         }
        public void setLaptopN(String newValue){
              _LaptopN = newValue;
         }
        private String _Cable = "";
        public String getCable(){
              return _Cable;
         }
        public void setCable(String newValue){
              _Cable = newValue;
         }
        private String _CableN = "";
        public String getCableN(){
              return _CableN;
         }
        public void setCableN(String newValue){
              _CableN = newValue;
         }
        private String _Microwave = "";
        public String getMicrowave(){
              return _Microwave;
         }
        public void setMicrowave(String newValue){
              _Microwave = newValue;
         }
        private String _MicrowaveN = "";
        public String getMicrowaveN(){
              return _MicrowaveN;
         }
        public void setMicrowaveN(String newValue){
              _MicrowaveN = newValue;
         }
        private String _Geyser = "";
        public String getGeyser(){
              return _Geyser;
         }
        public void setGeyser(String newValue){
              _Geyser = newValue;
         }
        private String _GeyserN = "";
        public String getGeyserN(){
              return _GeyserN;
         }
        public void setGeyserN(String newValue){
              _GeyserN = newValue;
         }
        private String _Grill = "";
        public String getGrill(){
              return _Grill;
         }
        public void setGrill(String newValue){
              _Grill = newValue;
         }
        private String _GrillN = "";
        public String getGrillN(){
              return _GrillN;
         }
        public void setGrillN(String newValue){
              _GrillN = newValue;
         }
        private String _Grain = "";
        public String getGrain(){
              return _Grain;
         }
        public void setGrain(String newValue){
              _Grain = newValue;
         }
        private String _GrainN = "";
        public String getGrainN(){
              return _GrainN;
         }
        public void setGrainN(String newValue){
              _GrainN = newValue;
         }
        private String _Refrigerator = "";
        public String getRefrigerator(){
              return _Refrigerator;
         }
        public void setRefrigerator(String newValue){
              _Refrigerator = newValue;
         }
        private String _RefrigeratorN = "";
        public String getRefrigeratorN(){
              return _RefrigeratorN;
         }
        public void setRefrigeratorN(String newValue){
              _RefrigeratorN = newValue;
         }
        private String _DeepFreezer = "";
        public String getDeepFreezer(){
              return _DeepFreezer;
         }
        public void setDeepFreezer(String newValue){
              _DeepFreezer = newValue;
         }
        private String _DeepFreezerN = "";
        public String getDeepFreezerN(){
              return _DeepFreezerN;
         }
        public void setDeepFreezerN(String newValue){
              _DeepFreezerN = newValue;
         }
        private String _Stove = "";
        public String getStove(){
              return _Stove;
         }
        public void setStove(String newValue){
              _Stove = newValue;
         }
        private String _StoveN = "";
        public String getStoveN(){
              return _StoveN;
         }
        public void setStoveN(String newValue){
              _StoveN = newValue;
         }
        private String _GasHob = "";
        public String getGasHob(){
              return _GasHob;
         }
        public void setGasHob(String newValue){
              _GasHob = newValue;
         }
        private String _GasHobN = "";
        public String getGasHobN(){
              return _GasHobN;
         }
        public void setGasHobN(String newValue){
              _GasHobN = newValue;
         }
        private String _ImpCooker = "";
        public String getImpCooker(){
              return _ImpCooker;
         }
        public void setImpCooker(String newValue){
              _ImpCooker = newValue;
         }
        private String _ImpCookerN = "";
        public String getImpCookerN(){
              return _ImpCookerN;
         }
        public void setImpCookerN(String newValue){
              _ImpCookerN = newValue;
         }
        private String _Bike = "";
        public String getBike(){
              return _Bike;
         }
        public void setBike(String newValue){
              _Bike = newValue;
         }
        private String _BikeN = "";
        public String getBikeN(){
              return _BikeN;
         }
        public void setBikeN(String newValue){
              _BikeN = newValue;
         }
        private String _Motorcycle = "";
        public String getMotorcycle(){
              return _Motorcycle;
         }
        public void setMotorcycle(String newValue){
              _Motorcycle = newValue;
         }
        private String _MotorcycleN = "";
        public String getMotorcycleN(){
              return _MotorcycleN;
         }
        public void setMotorcycleN(String newValue){
              _MotorcycleN = newValue;
         }
        private String _Car = "";
        public String getCar(){
              return _Car;
         }
        public void setCar(String newValue){
              _Car = newValue;
         }
        private String _CarN = "";
        public String getCarN(){
              return _CarN;
         }
        public void setCarN(String newValue){
              _CarN = newValue;
         }
        private String _Rickshaw = "";
        public String getRickshaw(){
              return _Rickshaw;
         }
        public void setRickshaw(String newValue){
              _Rickshaw = newValue;
         }
        private String _RickshawN = "";
        public String getRickshawN(){
              return _RickshawN;
         }
        public void setRickshawN(String newValue){
              _RickshawN = newValue;
         }
        private String _Cart = "";
        public String getCart(){
              return _Cart;
         }
        public void setCart(String newValue){
              _Cart = newValue;
         }
        private String _CartN = "";
        public String getCartN(){
              return _CartN;
         }
        public void setCartN(String newValue){
              _CartN = newValue;
         }
        private String _Canoe = "";
        public String getCanoe(){
              return _Canoe;
         }
        public void setCanoe(String newValue){
              _Canoe = newValue;
         }
        private String _CanoeN = "";
        public String getCanoeN(){
              return _CanoeN;
         }
        public void setCanoeN(String newValue){
              _CanoeN = newValue;
         }
        private String _Bus = "";
        public String getBus(){
              return _Bus;
         }
        public void setBus(String newValue){
              _Bus = newValue;
         }
        private String _BusN = "";
        public String getBusN(){
              return _BusN;
         }
        public void setBusN(String newValue){
              _BusN = newValue;
         }
        private String _Tractor = "";
        public String getTractor(){
              return _Tractor;
         }
        public void setTractor(String newValue){
              _Tractor = newValue;
         }
        private String _TractorN = "";
        public String getTractorN(){
              return _TractorN;
         }
        public void setTractorN(String newValue){
              _TractorN = newValue;
         }
        private String _Plow = "";
        public String getPlow(){
              return _Plow;
         }
        public void setPlow(String newValue){
              _Plow = newValue;
         }
        private String _PlowN = "";
        public String getPlowN(){
              return _PlowN;
         }
        public void setPlowN(String newValue){
              _PlowN = newValue;
         }
        private String _Duck = "";
        public String getDuck(){
              return _Duck;
         }
        public void setDuck(String newValue){
              _Duck = newValue;
         }
        private String _DuckN = "";
        public String getDuckN(){
              return _DuckN;
         }
        public void setDuckN(String newValue){
              _DuckN = newValue;
         }
        private String _Cow = "";
        public String getCow(){
              return _Cow;
         }
        public void setCow(String newValue){
              _Cow = newValue;
         }
        private String _CowN = "";
        public String getCowN(){
              return _CowN;
         }
        public void setCowN(String newValue){
              _CowN = newValue;
         }
        private String _Sheep = "";
        public String getSheep(){
              return _Sheep;
         }
        public void setSheep(String newValue){
              _Sheep = newValue;
         }
        private String _SheepN = "";
        public String getSheepN(){
              return _SheepN;
         }
        public void setSheepN(String newValue){
              _SheepN = newValue;
         }
        private String _Goat = "";
        public String getGoat(){
              return _Goat;
         }
        public void setGoat(String newValue){
              _Goat = newValue;
         }
        private String _GoatN = "";
        public String getGoatN(){
              return _GoatN;
         }
        public void setGoatN(String newValue){
              _GoatN = newValue;
         }
        private String _Chicken = "";
        public String getChicken(){
              return _Chicken;
         }
        public void setChicken(String newValue){
              _Chicken = newValue;
         }
        private String _ChickenN = "";
        public String getChickenN(){
              return _ChickenN;
         }
        public void setChickenN(String newValue){
              _ChickenN = newValue;
         }
        private String _Donkey = "";
        public String getDonkey(){
              return _Donkey;
         }
        public void setDonkey(String newValue){
              _Donkey = newValue;
         }
        private String _DunkeyN = "";
        public String getDunkeyN(){
              return _DunkeyN;
         }
        public void setDunkeyN(String newValue){
              _DunkeyN = newValue;
         }
        private String _Horse = "";
        public String getHorse(){
              return _Horse;
         }
        public void setHorse(String newValue){
              _Horse = newValue;
         }
        private String _HorseN = "";
        public String getHorseN(){
              return _HorseN;
         }
        public void setHorseN(String newValue){
              _HorseN = newValue;
         }
        private String _Pig = "";
        public String getPig(){
              return _Pig;
         }
        public void setPig(String newValue){
              _Pig = newValue;
         }
        private String _PigN = "";
        public String getPigN(){
              return _PigN;
         }
        public void setPigN(String newValue){
              _PigN = newValue;
         }
        private String _Birds = "";
        public String getBirds(){
              return _Birds;
         }
        public void setBirds(String newValue){
              _Birds = newValue;
         }
        private String _BirdsN = "";
        public String getBirdsN(){
              return _BirdsN;
         }
        public void setBirdsN(String newValue){
              _BirdsN = newValue;
         }
        private String _Dogs = "";
        public String getDogs(){
              return _Dogs;
         }
        public void setDogs(String newValue){
              _Dogs = newValue;
         }
        private String _DogsN = "";
        public String getDogsN(){
              return _DogsN;
         }
        public void setDogsN(String newValue){
              _DogsN = newValue;
         }
        private String _Cats = "";
        public String getCats(){
              return _Cats;
         }
        public void setCats(String newValue){
              _Cats = newValue;
         }
        private String _CatsN = "";
        public String getCatsN(){
              return _CatsN;
         }
        public void setCatsN(String newValue){
              _CatsN = newValue;
         }
        private String _FishNet = "";
        public String getFishNet(){
              return _FishNet;
         }
        public void setFishNet(String newValue){
              _FishNet = newValue;
         }
        private String _FishNetN = "";
        public String getFishNetN(){
              return _FishNetN;
         }
        public void setFishNetN(String newValue){
              _FishNetN = newValue;
         }
        private String _OtherAsset = "";
        public String getOtherAsset(){
              return _OtherAsset;
         }
        public void setOtherAsset(String newValue){
              _OtherAsset = newValue;
         }
        private String _OtherAsset1 = "";
        public String getOtherAsset1(){
              return _OtherAsset1;
         }
        public void setOtherAsset1(String newValue){
              _OtherAsset1 = newValue;
         }
        private String _OtherAsset1N = "";
        public String getOtherAsset1N(){
              return _OtherAsset1N;
         }
        public void setOtherAsset1N(String newValue){
              _OtherAsset1N = newValue;
         }
        private String _OtherAsset2 = "";
        public String getOtherAsset2(){
              return _OtherAsset2;
         }
        public void setOtherAsset2(String newValue){
              _OtherAsset2 = newValue;
         }
        private String _OtherAsset2N = "";
        public String getOtherAsset2N(){
              return _OtherAsset2N;
         }
        public void setOtherAsset2N(String newValue){
              _OtherAsset2N = newValue;
         }
        private String _OtherAsset3 = "";
        public String getOtherAsset3(){
              return _OtherAsset3;
         }
        public void setOtherAsset3(String newValue){
              _OtherAsset3 = newValue;
         }
        private String _OtherAsset3N = "";
        public String getOtherAsset3N(){
              return _OtherAsset3N;
         }
        public void setOtherAsset3N(String newValue){
              _OtherAsset3N = newValue;
         }
        private String _SESNote = "";
        public String getSESNote(){
              return _SESNote;
         }
        public void setSESNote(String newValue){
              _SESNote = newValue;
         }
        private String _StartTime = "";
        public void setStartTime(String newValue){
              _StartTime = newValue;
         }
        private String _EndTime = "";
        public void setEndTime(String newValue){
              _EndTime = newValue;
         }
        private String _DeviceID = "";
        public void setDeviceID(String newValue){
              _DeviceID = newValue;
         }
        private String _EntryUser = "";
        public void setEntryUser(String newValue){
              _EntryUser = newValue;
         }
        private String _Lat = "";
        public void setLat(String newValue){
              _Lat = newValue;
         }
        private String _Lon = "";
        public void setLon(String newValue){
              _Lon = newValue;
         }
        private String _EnDt = Global.DateTimeNowYMDHMS();
        private int _Upload = 2;
        private String _modifyDate = Global.DateTimeNowYMDHMS();

        String TableName = "SES_Mali";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where HHID='"+ _HHID +"' and SESNo='"+ _SESNo +"' "))
                    response = UpdateData(context);
                 else
                    response = SaveData(context);
            }
            catch(Exception  e)
            {
                 response = e.getMessage();
            }
           return response;
        }
        Connection C;

        private String SaveData(Context context)
        {
            String response = "";
            C = new Connection(context);
            try
              {
                 ContentValues contentValues = new ContentValues();
                 contentValues.put("HHID", _HHID);
                 contentValues.put("SESNo", _SESNo);
                 contentValues.put("SESVDate", _SESVDate);
                 contentValues.put("SESVStatus", _SESVStatus);
                 contentValues.put("SESVStatusOth", _SESVStatusOth);
                 contentValues.put("Rnd", _Rnd);
                 contentValues.put("WSDrink", _WSDrink);
                 contentValues.put("WSDrinkOth", _WSDrinkOth);
                 contentValues.put("WaterSource", _WaterSource);
                 contentValues.put("FetchWaterM", _FetchWaterM);
                 contentValues.put("FetchWaterMDk", _FetchWaterMDk);
                 contentValues.put("GetWater", _GetWater);
                 contentValues.put("GetWaterOth", _GetWaterOth);
                 contentValues.put("MemberID", _MemberID);
                 contentValues.put("BringWater", _BringWater);
                 contentValues.put("BringWaterDk", _BringWaterDk);
                 contentValues.put("Someone", _Someone);
                 contentValues.put("SecondPers", _SecondPers);
                 contentValues.put("SecondPersOth", _SecondPersOth);
                 contentValues.put("MemberID2nd", _MemberID2nd);
                 contentValues.put("EnoughWater", _EnoughWater);
                 contentValues.put("MainWater", _MainWater);
                 contentValues.put("MainWaterOth", _MainWaterOth);
                 contentValues.put("SmallTank", _SmallTank);
                 contentValues.put("MediunTank", _MediunTank);
                 contentValues.put("LargeTank", _LargeTank);
                 contentValues.put("StoreDrink", _StoreDrink);
                 contentValues.put("ContainOpenCov", _ContainOpenCov);
                 contentValues.put("ContainOpenNotCov", _ContainOpenNotCov);
                 contentValues.put("ContainOpenDK", _ContainOpenDK);
                 contentValues.put("RecoverWater", _RecoverWater);
                 contentValues.put("RecoverWaterOth", _RecoverWaterOth);
                 contentValues.put("LessDanger", _LessDanger);
                 contentValues.put("MakeSafer", _MakeSafer);
                 contentValues.put("MakeSaferOth", _MakeSaferOth);
                 contentValues.put("Toilet", _Toilet);
                 contentValues.put("ToiletOth", _ToiletOth);
                 contentValues.put("ToiletShrd", _ToiletShrd);
                 contentValues.put("ToiletUseNo", _ToiletUseNo);
                 contentValues.put("ToiletUseNoDk", _ToiletUseNoDk);
                 contentValues.put("ToiletLoc", _ToiletLoc);
                 contentValues.put("ContentEmp", _ContentEmp);
                 contentValues.put("ContentEmpOth", _ContentEmpOth);
                 contentValues.put("BowelMov", _BowelMov);
                 contentValues.put("BowelMovOth", _BowelMovOth);
                 contentValues.put("LiquidWaste", _LiquidWaste);
                 contentValues.put("LiquidWasteOth", _LiquidWasteOth);
                 contentValues.put("SolidWasteMethod", _SolidWasteMethod);
                 contentValues.put("SolidWasteMethodOth", _SolidWasteMethodOth);
                 contentValues.put("HandWash", _HandWash);
                 contentValues.put("ShowWash", _ShowWash);
                 contentValues.put("AvailableWat", _AvailableWat);
                 contentValues.put("AvailableSoap", _AvailableSoap);
                 contentValues.put("CookDvc", _CookDvc);
                 contentValues.put("CookDvcOth", _CookDvcOth);
                 contentValues.put("CookFuel", _CookFuel);
                 contentValues.put("CookFuelOth", _CookFuelOth);
                 contentValues.put("CookPlc", _CookPlc);
                 contentValues.put("CookPlcOth", _CookPlcOth);
                 contentValues.put("Floor", _Floor);
                 contentValues.put("FloorOth", _FloorOth);
                 contentValues.put("GroundMat", _GroundMat);
                 contentValues.put("GroundMatOth", _GroundMatOth);
                 contentValues.put("Roof", _Roof);
                 contentValues.put("RoofOth", _RoofOth);
                 contentValues.put("SmokeInside", _SmokeInside);
                 contentValues.put("FreqSmoke", _FreqSmoke);
                 contentValues.put("Wall", _Wall);
                 contentValues.put("WallOth", _WallOth);
                 contentValues.put("RoomSleep", _RoomSleep);
                 contentValues.put("RoomSleepDk", _RoomSleepDk);
                 contentValues.put("ElecNight", _ElecNight);
                 contentValues.put("ElecNightOth", _ElecNightOth);
                 contentValues.put("HomesteadAny", _HomesteadAny);
                 contentValues.put("OthLand", _OthLand);
                 contentValues.put("Area", _Area);
                 contentValues.put("IncomeMo", _IncomeMo);
                 contentValues.put("Expenses", _Expenses);
                 contentValues.put("BankAcc", _BankAcc);
                 contentValues.put("SprayInt", _SprayInt);
                 contentValues.put("MosqNet", _MosqNet);
                 contentValues.put("NetOwned", _NetOwned);
                 contentValues.put("MedHome", _MedHome);
                 contentValues.put("MedTypeAM", _MedTypeAM);
                 contentValues.put("MedTypeAB", _MedTypeAB);
                 contentValues.put("MedTypeDK", _MedTypeDK);
                 contentValues.put("AntimalarAL", _AntimalarAL);
                 contentValues.put("AntimalarASAQ", _AntimalarASAQ);
                 contentValues.put("AntimalarSP", _AntimalarSP);
                 contentValues.put("AntimalarOth", _AntimalarOth);
                 contentValues.put("AntimalarSpecifyOth", _AntimalarSpecifyOth);
                 contentValues.put("GetMedHosp", _GetMedHosp);
                 contentValues.put("GetMedCSCom", _GetMedCSCom);
                 contentValues.put("GetMedPrvCl", _GetMedPrvCl);
                 contentValues.put("GetMedPhar", _GetMedPhar);
                 contentValues.put("GetMedPD", _GetMedPD);
                 contentValues.put("GetMedCHW", _GetMedCHW);
                 contentValues.put("GetMedSS", _GetMedSS);
                 contentValues.put("GetMedOth", _GetMedOth);
                 contentValues.put("GetMedSpecifyOth", _GetMedSpecifyOth);
                 contentValues.put("AComment", _AComment);
                 contentValues.put("Comment", _Comment);
                 contentValues.put("Electricity", _Electricity);
                 contentValues.put("SolarPlate", _SolarPlate);
                 contentValues.put("SolarPlateN", _SolarPlateN);
                 contentValues.put("Heater", _Heater);
                 contentValues.put("HeaterN", _HeaterN);
                 contentValues.put("AC", _AC);
                 contentValues.put("ACN", _ACN);
                 contentValues.put("ElecFan", _ElecFan);
                 contentValues.put("ElecFanN", _ElecFanN);
                 contentValues.put("Lantern", _Lantern);
                 contentValues.put("LanternN", _LanternN);
                 contentValues.put("Lamp", _Lamp);
                 contentValues.put("LampN", _LampN);
                 contentValues.put("GasLamp", _GasLamp);
                 contentValues.put("GasLampN", _GasLampN);
                 contentValues.put("Petroleum", _Petroleum);
                 contentValues.put("PetroleumN", _PetroleumN);
                 contentValues.put("Matt", _Matt);
                 contentValues.put("MattN", _MattN);
                 contentValues.put("Mats", _Mats);
                 contentValues.put("MatsN", _MatsN);
                 contentValues.put("Carpets", _Carpets);
                 contentValues.put("CarpetsN", _CarpetsN);
                 contentValues.put("Bed", _Bed);
                 contentValues.put("BedN", _BedN);
                 contentValues.put("Chair", _Chair);
                 contentValues.put("ChairN", _ChairN);
                 contentValues.put("Sofa", _Sofa);
                 contentValues.put("SofaN", _SofaN);
                 contentValues.put("Tables", _Tables);
                 contentValues.put("TablesN", _TablesN);
                 contentValues.put("Watch", _Watch);
                 contentValues.put("WatchN", _WatchN);
                 contentValues.put("WMachine", _WMachine);
                 contentValues.put("WMachineN", _WMachineN);
                 contentValues.put("Iron", _Iron);
                 contentValues.put("IronN", _IronN);
                 contentValues.put("Booth", _Booth);
                 contentValues.put("BoothN", _BoothN);
                 contentValues.put("SMachine", _SMachine);
                 contentValues.put("SMachineN", _SMachineN);
                 contentValues.put("Generator", _Generator);
                 contentValues.put("GeneratorN", _GeneratorN);
                 contentValues.put("Internet", _Internet);
                 contentValues.put("InternetN", _InternetN);
                 contentValues.put("Satellite", _Satellite);
                 contentValues.put("SatelliteN", _SatelliteN);
                 contentValues.put("Landline", _Landline);
                 contentValues.put("LandlineN", _LandlineN);
                 contentValues.put("Cellphone", _Cellphone);
                 contentValues.put("CellphoneN", _CellphoneN);
                 contentValues.put("TV", _TV);
                 contentValues.put("TVN", _TVN);
                 contentValues.put("TV5", _TV5);
                 contentValues.put("TV5N", _TV5N);
                 contentValues.put("Channel", _Channel);
                 contentValues.put("ChannelN", _ChannelN);
                 contentValues.put("Radio", _Radio);
                 contentValues.put("RadioN", _RadioN);
                 contentValues.put("DVD", _DVD);
                 contentValues.put("DVDN", _DVDN);
                 contentValues.put("Video", _Video);
                 contentValues.put("VideoN", _VideoN);
                 contentValues.put("Computer", _Computer);
                 contentValues.put("ComputerN", _ComputerN);
                 contentValues.put("Laptop", _Laptop);
                 contentValues.put("LaptopN", _LaptopN);
                 contentValues.put("Cable", _Cable);
                 contentValues.put("CableN", _CableN);
                 contentValues.put("Microwave", _Microwave);
                 contentValues.put("MicrowaveN", _MicrowaveN);
                 contentValues.put("Geyser", _Geyser);
                 contentValues.put("GeyserN", _GeyserN);
                 contentValues.put("Grill", _Grill);
                 contentValues.put("GrillN", _GrillN);
                 contentValues.put("Grain", _Grain);
                 contentValues.put("GrainN", _GrainN);
                 contentValues.put("Refrigerator", _Refrigerator);
                 contentValues.put("RefrigeratorN", _RefrigeratorN);
                 contentValues.put("DeepFreezer", _DeepFreezer);
                 contentValues.put("DeepFreezerN", _DeepFreezerN);
                 contentValues.put("Stove", _Stove);
                 contentValues.put("StoveN", _StoveN);
                 contentValues.put("GasHob", _GasHob);
                 contentValues.put("GasHobN", _GasHobN);
                 contentValues.put("ImpCooker", _ImpCooker);
                 contentValues.put("ImpCookerN", _ImpCookerN);
                 contentValues.put("Bike", _Bike);
                 contentValues.put("BikeN", _BikeN);
                 contentValues.put("Motorcycle", _Motorcycle);
                 contentValues.put("MotorcycleN", _MotorcycleN);
                 contentValues.put("Car", _Car);
                 contentValues.put("CarN", _CarN);
                 contentValues.put("Rickshaw", _Rickshaw);
                 contentValues.put("RickshawN", _RickshawN);
                 contentValues.put("Cart", _Cart);
                 contentValues.put("CartN", _CartN);
                 contentValues.put("Canoe", _Canoe);
                 contentValues.put("CanoeN", _CanoeN);
                 contentValues.put("Bus", _Bus);
                 contentValues.put("BusN", _BusN);
                 contentValues.put("Tractor", _Tractor);
                 contentValues.put("TractorN", _TractorN);
                 contentValues.put("Plow", _Plow);
                 contentValues.put("PlowN", _PlowN);
                 contentValues.put("Duck", _Duck);
                 contentValues.put("DuckN", _DuckN);
                 contentValues.put("Cow", _Cow);
                 contentValues.put("CowN", _CowN);
                 contentValues.put("Sheep", _Sheep);
                 contentValues.put("SheepN", _SheepN);
                 contentValues.put("Goat", _Goat);
                 contentValues.put("GoatN", _GoatN);
                 contentValues.put("Chicken", _Chicken);
                 contentValues.put("ChickenN", _ChickenN);
                 contentValues.put("Donkey", _Donkey);
                 contentValues.put("DunkeyN", _DunkeyN);
                 contentValues.put("Horse", _Horse);
                 contentValues.put("HorseN", _HorseN);
                 contentValues.put("Pig", _Pig);
                 contentValues.put("PigN", _PigN);
                 contentValues.put("Birds", _Birds);
                 contentValues.put("BirdsN", _BirdsN);
                 contentValues.put("Dogs", _Dogs);
                 contentValues.put("DogsN", _DogsN);
                 contentValues.put("Cats", _Cats);
                 contentValues.put("CatsN", _CatsN);
                 contentValues.put("FishNet", _FishNet);
                 contentValues.put("FishNetN", _FishNetN);
                 contentValues.put("OtherAsset", _OtherAsset);
                 contentValues.put("OtherAsset1", _OtherAsset1);
                 contentValues.put("OtherAsset1N", _OtherAsset1N);
                 contentValues.put("OtherAsset2", _OtherAsset2);
                 contentValues.put("OtherAsset2N", _OtherAsset2N);
                 contentValues.put("OtherAsset3", _OtherAsset3);
                 contentValues.put("OtherAsset3N", _OtherAsset3N);
                 contentValues.put("SESNote", _SESNote);
                 contentValues.put("isdelete", 2);
                 contentValues.put("StartTime", _StartTime);
                 contentValues.put("EndTime", _EndTime);
                 contentValues.put("DeviceID", _DeviceID);
                 contentValues.put("EntryUser", _EntryUser);
                 contentValues.put("Lat", _Lat);
                 contentValues.put("Lon", _Lon);
                 contentValues.put("EnDt", _EnDt);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.InsertData(TableName, contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

        private String UpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            try
              {
                 ContentValues contentValues = new ContentValues();
                 contentValues.put("HHID", _HHID);
                 contentValues.put("SESNo", _SESNo);
                 contentValues.put("SESVDate", _SESVDate);
                 contentValues.put("SESVStatus", _SESVStatus);
                 contentValues.put("SESVStatusOth", _SESVStatusOth);
                 contentValues.put("Rnd", _Rnd);
                 contentValues.put("WSDrink", _WSDrink);
                 contentValues.put("WSDrinkOth", _WSDrinkOth);
                 contentValues.put("WaterSource", _WaterSource);
                 contentValues.put("FetchWaterM", _FetchWaterM);
                 contentValues.put("FetchWaterMDk", _FetchWaterMDk);
                 contentValues.put("GetWater", _GetWater);
                 contentValues.put("GetWaterOth", _GetWaterOth);
                 contentValues.put("MemberID", _MemberID);
                 contentValues.put("BringWater", _BringWater);
                 contentValues.put("BringWaterDk", _BringWaterDk);
                 contentValues.put("Someone", _Someone);
                 contentValues.put("SecondPers", _SecondPers);
                 contentValues.put("SecondPersOth", _SecondPersOth);
                 contentValues.put("MemberID2nd", _MemberID2nd);
                 contentValues.put("EnoughWater", _EnoughWater);
                 contentValues.put("MainWater", _MainWater);
                 contentValues.put("MainWaterOth", _MainWaterOth);
                 contentValues.put("SmallTank", _SmallTank);
                 contentValues.put("MediunTank", _MediunTank);
                 contentValues.put("LargeTank", _LargeTank);
                 contentValues.put("StoreDrink", _StoreDrink);
                 contentValues.put("ContainOpenCov", _ContainOpenCov);
                 contentValues.put("ContainOpenNotCov", _ContainOpenNotCov);
                 contentValues.put("ContainOpenDK", _ContainOpenDK);
                 contentValues.put("RecoverWater", _RecoverWater);
                 contentValues.put("RecoverWaterOth", _RecoverWaterOth);
                 contentValues.put("LessDanger", _LessDanger);
                 contentValues.put("MakeSafer", _MakeSafer);
                 contentValues.put("MakeSaferOth", _MakeSaferOth);
                 contentValues.put("Toilet", _Toilet);
                 contentValues.put("ToiletOth", _ToiletOth);
                 contentValues.put("ToiletShrd", _ToiletShrd);
                 contentValues.put("ToiletUseNo", _ToiletUseNo);
                 contentValues.put("ToiletUseNoDk", _ToiletUseNoDk);
                 contentValues.put("ToiletLoc", _ToiletLoc);
                 contentValues.put("ContentEmp", _ContentEmp);
                 contentValues.put("ContentEmpOth", _ContentEmpOth);
                 contentValues.put("BowelMov", _BowelMov);
                 contentValues.put("BowelMovOth", _BowelMovOth);
                 contentValues.put("LiquidWaste", _LiquidWaste);
                 contentValues.put("LiquidWasteOth", _LiquidWasteOth);
                 contentValues.put("SolidWasteMethod", _SolidWasteMethod);
                 contentValues.put("SolidWasteMethodOth", _SolidWasteMethodOth);
                 contentValues.put("HandWash", _HandWash);
                 contentValues.put("ShowWash", _ShowWash);
                 contentValues.put("AvailableWat", _AvailableWat);
                 contentValues.put("AvailableSoap", _AvailableSoap);
                 contentValues.put("CookDvc", _CookDvc);
                 contentValues.put("CookDvcOth", _CookDvcOth);
                 contentValues.put("CookFuel", _CookFuel);
                 contentValues.put("CookFuelOth", _CookFuelOth);
                 contentValues.put("CookPlc", _CookPlc);
                 contentValues.put("CookPlcOth", _CookPlcOth);
                 contentValues.put("Floor", _Floor);
                 contentValues.put("FloorOth", _FloorOth);
                 contentValues.put("GroundMat", _GroundMat);
                 contentValues.put("GroundMatOth", _GroundMatOth);
                 contentValues.put("Roof", _Roof);
                 contentValues.put("RoofOth", _RoofOth);
                 contentValues.put("SmokeInside", _SmokeInside);
                 contentValues.put("FreqSmoke", _FreqSmoke);
                 contentValues.put("Wall", _Wall);
                 contentValues.put("WallOth", _WallOth);
                 contentValues.put("RoomSleep", _RoomSleep);
                 contentValues.put("RoomSleepDk", _RoomSleepDk);
                 contentValues.put("ElecNight", _ElecNight);
                 contentValues.put("ElecNightOth", _ElecNightOth);
                 contentValues.put("HomesteadAny", _HomesteadAny);
                 contentValues.put("OthLand", _OthLand);
                 contentValues.put("Area", _Area);
                 contentValues.put("IncomeMo", _IncomeMo);
                 contentValues.put("Expenses", _Expenses);
                 contentValues.put("BankAcc", _BankAcc);
                 contentValues.put("SprayInt", _SprayInt);
                 contentValues.put("MosqNet", _MosqNet);
                 contentValues.put("NetOwned", _NetOwned);
                 contentValues.put("MedHome", _MedHome);
                 contentValues.put("MedTypeAM", _MedTypeAM);
                 contentValues.put("MedTypeAB", _MedTypeAB);
                 contentValues.put("MedTypeDK", _MedTypeDK);
                 contentValues.put("AntimalarAL", _AntimalarAL);
                 contentValues.put("AntimalarASAQ", _AntimalarASAQ);
                 contentValues.put("AntimalarSP", _AntimalarSP);
                 contentValues.put("AntimalarOth", _AntimalarOth);
                 contentValues.put("AntimalarSpecifyOth", _AntimalarSpecifyOth);
                 contentValues.put("GetMedHosp", _GetMedHosp);
                 contentValues.put("GetMedCSCom", _GetMedCSCom);
                 contentValues.put("GetMedPrvCl", _GetMedPrvCl);
                 contentValues.put("GetMedPhar", _GetMedPhar);
                 contentValues.put("GetMedPD", _GetMedPD);
                 contentValues.put("GetMedCHW", _GetMedCHW);
                 contentValues.put("GetMedSS", _GetMedSS);
                 contentValues.put("GetMedOth", _GetMedOth);
                 contentValues.put("GetMedSpecifyOth", _GetMedSpecifyOth);
                 contentValues.put("AComment", _AComment);
                 contentValues.put("Comment", _Comment);
                 contentValues.put("Electricity", _Electricity);
                 contentValues.put("SolarPlate", _SolarPlate);
                 contentValues.put("SolarPlateN", _SolarPlateN);
                 contentValues.put("Heater", _Heater);
                 contentValues.put("HeaterN", _HeaterN);
                 contentValues.put("AC", _AC);
                 contentValues.put("ACN", _ACN);
                 contentValues.put("ElecFan", _ElecFan);
                 contentValues.put("ElecFanN", _ElecFanN);
                 contentValues.put("Lantern", _Lantern);
                 contentValues.put("LanternN", _LanternN);
                 contentValues.put("Lamp", _Lamp);
                 contentValues.put("LampN", _LampN);
                 contentValues.put("GasLamp", _GasLamp);
                 contentValues.put("GasLampN", _GasLampN);
                 contentValues.put("Petroleum", _Petroleum);
                 contentValues.put("PetroleumN", _PetroleumN);
                 contentValues.put("Matt", _Matt);
                 contentValues.put("MattN", _MattN);
                 contentValues.put("Mats", _Mats);
                 contentValues.put("MatsN", _MatsN);
                 contentValues.put("Carpets", _Carpets);
                 contentValues.put("CarpetsN", _CarpetsN);
                 contentValues.put("Bed", _Bed);
                 contentValues.put("BedN", _BedN);
                 contentValues.put("Chair", _Chair);
                 contentValues.put("ChairN", _ChairN);
                 contentValues.put("Sofa", _Sofa);
                 contentValues.put("SofaN", _SofaN);
                 contentValues.put("Tables", _Tables);
                 contentValues.put("TablesN", _TablesN);
                 contentValues.put("Watch", _Watch);
                 contentValues.put("WatchN", _WatchN);
                 contentValues.put("WMachine", _WMachine);
                 contentValues.put("WMachineN", _WMachineN);
                 contentValues.put("Iron", _Iron);
                 contentValues.put("IronN", _IronN);
                 contentValues.put("Booth", _Booth);
                 contentValues.put("BoothN", _BoothN);
                 contentValues.put("SMachine", _SMachine);
                 contentValues.put("SMachineN", _SMachineN);
                 contentValues.put("Generator", _Generator);
                 contentValues.put("GeneratorN", _GeneratorN);
                 contentValues.put("Internet", _Internet);
                 contentValues.put("InternetN", _InternetN);
                 contentValues.put("Satellite", _Satellite);
                 contentValues.put("SatelliteN", _SatelliteN);
                 contentValues.put("Landline", _Landline);
                 contentValues.put("LandlineN", _LandlineN);
                 contentValues.put("Cellphone", _Cellphone);
                 contentValues.put("CellphoneN", _CellphoneN);
                 contentValues.put("TV", _TV);
                 contentValues.put("TVN", _TVN);
                 contentValues.put("TV5", _TV5);
                 contentValues.put("TV5N", _TV5N);
                 contentValues.put("Channel", _Channel);
                 contentValues.put("ChannelN", _ChannelN);
                 contentValues.put("Radio", _Radio);
                 contentValues.put("RadioN", _RadioN);
                 contentValues.put("DVD", _DVD);
                 contentValues.put("DVDN", _DVDN);
                 contentValues.put("Video", _Video);
                 contentValues.put("VideoN", _VideoN);
                 contentValues.put("Computer", _Computer);
                 contentValues.put("ComputerN", _ComputerN);
                 contentValues.put("Laptop", _Laptop);
                 contentValues.put("LaptopN", _LaptopN);
                 contentValues.put("Cable", _Cable);
                 contentValues.put("CableN", _CableN);
                 contentValues.put("Microwave", _Microwave);
                 contentValues.put("MicrowaveN", _MicrowaveN);
                 contentValues.put("Geyser", _Geyser);
                 contentValues.put("GeyserN", _GeyserN);
                 contentValues.put("Grill", _Grill);
                 contentValues.put("GrillN", _GrillN);
                 contentValues.put("Grain", _Grain);
                 contentValues.put("GrainN", _GrainN);
                 contentValues.put("Refrigerator", _Refrigerator);
                 contentValues.put("RefrigeratorN", _RefrigeratorN);
                 contentValues.put("DeepFreezer", _DeepFreezer);
                 contentValues.put("DeepFreezerN", _DeepFreezerN);
                 contentValues.put("Stove", _Stove);
                 contentValues.put("StoveN", _StoveN);
                 contentValues.put("GasHob", _GasHob);
                 contentValues.put("GasHobN", _GasHobN);
                 contentValues.put("ImpCooker", _ImpCooker);
                 contentValues.put("ImpCookerN", _ImpCookerN);
                 contentValues.put("Bike", _Bike);
                 contentValues.put("BikeN", _BikeN);
                 contentValues.put("Motorcycle", _Motorcycle);
                 contentValues.put("MotorcycleN", _MotorcycleN);
                 contentValues.put("Car", _Car);
                 contentValues.put("CarN", _CarN);
                 contentValues.put("Rickshaw", _Rickshaw);
                 contentValues.put("RickshawN", _RickshawN);
                 contentValues.put("Cart", _Cart);
                 contentValues.put("CartN", _CartN);
                 contentValues.put("Canoe", _Canoe);
                 contentValues.put("CanoeN", _CanoeN);
                 contentValues.put("Bus", _Bus);
                 contentValues.put("BusN", _BusN);
                 contentValues.put("Tractor", _Tractor);
                 contentValues.put("TractorN", _TractorN);
                 contentValues.put("Plow", _Plow);
                 contentValues.put("PlowN", _PlowN);
                 contentValues.put("Duck", _Duck);
                 contentValues.put("DuckN", _DuckN);
                 contentValues.put("Cow", _Cow);
                 contentValues.put("CowN", _CowN);
                 contentValues.put("Sheep", _Sheep);
                 contentValues.put("SheepN", _SheepN);
                 contentValues.put("Goat", _Goat);
                 contentValues.put("GoatN", _GoatN);
                 contentValues.put("Chicken", _Chicken);
                 contentValues.put("ChickenN", _ChickenN);
                 contentValues.put("Donkey", _Donkey);
                 contentValues.put("DunkeyN", _DunkeyN);
                 contentValues.put("Horse", _Horse);
                 contentValues.put("HorseN", _HorseN);
                 contentValues.put("Pig", _Pig);
                 contentValues.put("PigN", _PigN);
                 contentValues.put("Birds", _Birds);
                 contentValues.put("BirdsN", _BirdsN);
                 contentValues.put("Dogs", _Dogs);
                 contentValues.put("DogsN", _DogsN);
                 contentValues.put("Cats", _Cats);
                 contentValues.put("CatsN", _CatsN);
                 contentValues.put("FishNet", _FishNet);
                 contentValues.put("FishNetN", _FishNetN);
                 contentValues.put("OtherAsset", _OtherAsset);
                 contentValues.put("OtherAsset1", _OtherAsset1);
                 contentValues.put("OtherAsset1N", _OtherAsset1N);
                 contentValues.put("OtherAsset2", _OtherAsset2);
                 contentValues.put("OtherAsset2N", _OtherAsset2N);
                 contentValues.put("OtherAsset3", _OtherAsset3);
                 contentValues.put("OtherAsset3N", _OtherAsset3N);
                 contentValues.put("SESNote", _SESNote);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "HHID,SESNo", (""+ _HHID +", "+ _SESNo +""), contentValues);

                 manageAudit(context,this,AuditTrial.KEY_UPDATE);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        public List<SES_Mali_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<SES_Mali_DataModel> data = new ArrayList<SES_Mali_DataModel>();
            SES_Mali_DataModel d = new SES_Mali_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new SES_Mali_DataModel();
                d._Count = Count;
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._SESNo = cur.getString(cur.getColumnIndex("SESNo"));
                d._SESVDate = cur.getString(cur.getColumnIndex("SESVDate"));
                d._SESVStatus = cur.getString(cur.getColumnIndex("SESVStatus"));
                d._SESVStatusOth = cur.getString(cur.getColumnIndex("SESVStatusOth"));
                d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
                d._WSDrink = cur.getString(cur.getColumnIndex("WSDrink"));
                d._WSDrinkOth = cur.getString(cur.getColumnIndex("WSDrinkOth"));
                d._WaterSource = cur.getString(cur.getColumnIndex("WaterSource"));
                d._FetchWaterM = cur.getString(cur.getColumnIndex("FetchWaterM"));
                d._FetchWaterMDk = cur.getString(cur.getColumnIndex("FetchWaterMDk"));
                d._GetWater = cur.getString(cur.getColumnIndex("GetWater"));
                d._GetWaterOth = cur.getString(cur.getColumnIndex("GetWaterOth"));
                d._MemberID = cur.getString(cur.getColumnIndex("MemberID"));
                d._BringWater = cur.getString(cur.getColumnIndex("BringWater"));
                d._BringWaterDk = cur.getString(cur.getColumnIndex("BringWaterDk"));
                d._Someone = cur.getString(cur.getColumnIndex("Someone"));
                d._SecondPers = cur.getString(cur.getColumnIndex("SecondPers"));
                d._SecondPersOth = cur.getString(cur.getColumnIndex("SecondPersOth"));
                d._MemberID2nd = cur.getString(cur.getColumnIndex("MemberID2nd"));
                d._EnoughWater = cur.getString(cur.getColumnIndex("EnoughWater"));
                d._MainWater = cur.getString(cur.getColumnIndex("MainWater"));
                d._MainWaterOth = cur.getString(cur.getColumnIndex("MainWaterOth"));
                d._SmallTank = cur.getString(cur.getColumnIndex("SmallTank"));
                d._MediunTank = cur.getString(cur.getColumnIndex("MediunTank"));
                d._LargeTank = cur.getString(cur.getColumnIndex("LargeTank"));
                d._StoreDrink = cur.getString(cur.getColumnIndex("StoreDrink"));
                d._ContainOpenCov = cur.getString(cur.getColumnIndex("ContainOpenCov"));
                d._ContainOpenNotCov = cur.getString(cur.getColumnIndex("ContainOpenNotCov"));
                d._ContainOpenDK = cur.getString(cur.getColumnIndex("ContainOpenDK"));
                d._RecoverWater = cur.getString(cur.getColumnIndex("RecoverWater"));
                d._RecoverWaterOth = cur.getString(cur.getColumnIndex("RecoverWaterOth"));
                d._LessDanger = cur.getString(cur.getColumnIndex("LessDanger"));
                d._MakeSafer = cur.getString(cur.getColumnIndex("MakeSafer"));
                d._MakeSaferOth = cur.getString(cur.getColumnIndex("MakeSaferOth"));
                d._Toilet = cur.getString(cur.getColumnIndex("Toilet"));
                d._ToiletOth = cur.getString(cur.getColumnIndex("ToiletOth"));
                d._ToiletShrd = cur.getString(cur.getColumnIndex("ToiletShrd"));
                d._ToiletUseNo = cur.getString(cur.getColumnIndex("ToiletUseNo"));
                d._ToiletUseNoDk = cur.getString(cur.getColumnIndex("ToiletUseNoDk"));
                d._ToiletLoc = cur.getString(cur.getColumnIndex("ToiletLoc"));
                d._ContentEmp = cur.getString(cur.getColumnIndex("ContentEmp"));
                d._ContentEmpOth = cur.getString(cur.getColumnIndex("ContentEmpOth"));
                d._BowelMov = cur.getString(cur.getColumnIndex("BowelMov"));
                d._BowelMovOth = cur.getString(cur.getColumnIndex("BowelMovOth"));
                d._LiquidWaste = cur.getString(cur.getColumnIndex("LiquidWaste"));
                d._LiquidWasteOth = cur.getString(cur.getColumnIndex("LiquidWasteOth"));
                d._SolidWasteMethod = cur.getString(cur.getColumnIndex("SolidWasteMethod"));
                d._SolidWasteMethodOth = cur.getString(cur.getColumnIndex("SolidWasteMethodOth"));
                d._HandWash = cur.getString(cur.getColumnIndex("HandWash"));
                d._ShowWash = cur.getString(cur.getColumnIndex("ShowWash"));
                d._AvailableWat = cur.getString(cur.getColumnIndex("AvailableWat"));
                d._AvailableSoap = cur.getString(cur.getColumnIndex("AvailableSoap"));
                d._CookDvc = cur.getString(cur.getColumnIndex("CookDvc"));
                d._CookDvcOth = cur.getString(cur.getColumnIndex("CookDvcOth"));
                d._CookFuel = cur.getString(cur.getColumnIndex("CookFuel"));
                d._CookFuelOth = cur.getString(cur.getColumnIndex("CookFuelOth"));
                d._CookPlc = cur.getString(cur.getColumnIndex("CookPlc"));
                d._CookPlcOth = cur.getString(cur.getColumnIndex("CookPlcOth"));
                d._Floor = cur.getString(cur.getColumnIndex("Floor"));
                d._FloorOth = cur.getString(cur.getColumnIndex("FloorOth"));
                d._GroundMat = cur.getString(cur.getColumnIndex("GroundMat"));
                d._GroundMatOth = cur.getString(cur.getColumnIndex("GroundMatOth"));
                d._Roof = cur.getString(cur.getColumnIndex("Roof"));
                d._RoofOth = cur.getString(cur.getColumnIndex("RoofOth"));
                d._SmokeInside = cur.getString(cur.getColumnIndex("SmokeInside"));
                d._FreqSmoke = cur.getString(cur.getColumnIndex("FreqSmoke"));
                d._Wall = cur.getString(cur.getColumnIndex("Wall"));
                d._WallOth = cur.getString(cur.getColumnIndex("WallOth"));
                d._RoomSleep = cur.getString(cur.getColumnIndex("RoomSleep"));
                d._RoomSleepDk = cur.getString(cur.getColumnIndex("RoomSleepDk"));
                d._ElecNight = cur.getString(cur.getColumnIndex("ElecNight"));
                d._ElecNightOth = cur.getString(cur.getColumnIndex("ElecNightOth"));
                d._HomesteadAny = cur.getString(cur.getColumnIndex("HomesteadAny"));
                d._OthLand = cur.getString(cur.getColumnIndex("OthLand"));
                d._Area = cur.getString(cur.getColumnIndex("Area"));
                d._IncomeMo = cur.getString(cur.getColumnIndex("IncomeMo"));
                d._Expenses = cur.getString(cur.getColumnIndex("Expenses"));
                d._BankAcc = cur.getString(cur.getColumnIndex("BankAcc"));
                d._SprayInt = cur.getString(cur.getColumnIndex("SprayInt"));
                d._MosqNet = cur.getString(cur.getColumnIndex("MosqNet"));
                d._NetOwned = cur.getString(cur.getColumnIndex("NetOwned"));
                d._MedHome = cur.getString(cur.getColumnIndex("MedHome"));
                d._MedTypeAM = cur.getString(cur.getColumnIndex("MedTypeAM"));
                d._MedTypeAB = cur.getString(cur.getColumnIndex("MedTypeAB"));
                d._MedTypeDK = cur.getString(cur.getColumnIndex("MedTypeDK"));
                d._AntimalarAL = cur.getString(cur.getColumnIndex("AntimalarAL"));
                d._AntimalarASAQ = cur.getString(cur.getColumnIndex("AntimalarASAQ"));
                d._AntimalarSP = cur.getString(cur.getColumnIndex("AntimalarSP"));
                d._AntimalarOth = cur.getString(cur.getColumnIndex("AntimalarOth"));
                d._AntimalarSpecifyOth = cur.getString(cur.getColumnIndex("AntimalarSpecifyOth"));
                d._GetMedHosp = cur.getString(cur.getColumnIndex("GetMedHosp"));
                d._GetMedCSCom = cur.getString(cur.getColumnIndex("GetMedCSCom"));
                d._GetMedPrvCl = cur.getString(cur.getColumnIndex("GetMedPrvCl"));
                d._GetMedPhar = cur.getString(cur.getColumnIndex("GetMedPhar"));
                d._GetMedPD = cur.getString(cur.getColumnIndex("GetMedPD"));
                d._GetMedCHW = cur.getString(cur.getColumnIndex("GetMedCHW"));
                d._GetMedSS = cur.getString(cur.getColumnIndex("GetMedSS"));
                d._GetMedOth = cur.getString(cur.getColumnIndex("GetMedOth"));
                d._GetMedSpecifyOth = cur.getString(cur.getColumnIndex("GetMedSpecifyOth"));
                d._AComment = cur.getString(cur.getColumnIndex("AComment"));
                d._Comment = cur.getString(cur.getColumnIndex("Comment"));
                d._Electricity = cur.getString(cur.getColumnIndex("Electricity"));
                d._SolarPlate = cur.getString(cur.getColumnIndex("SolarPlate"));
                d._SolarPlateN = cur.getString(cur.getColumnIndex("SolarPlateN"));
                d._Heater = cur.getString(cur.getColumnIndex("Heater"));
                d._HeaterN = cur.getString(cur.getColumnIndex("HeaterN"));
                d._AC = cur.getString(cur.getColumnIndex("AC"));
                d._ACN = cur.getString(cur.getColumnIndex("ACN"));
                d._ElecFan = cur.getString(cur.getColumnIndex("ElecFan"));
                d._ElecFanN = cur.getString(cur.getColumnIndex("ElecFanN"));
                d._Lantern = cur.getString(cur.getColumnIndex("Lantern"));
                d._LanternN = cur.getString(cur.getColumnIndex("LanternN"));
                d._Lamp = cur.getString(cur.getColumnIndex("Lamp"));
                d._LampN = cur.getString(cur.getColumnIndex("LampN"));
                d._GasLamp = cur.getString(cur.getColumnIndex("GasLamp"));
                d._GasLampN = cur.getString(cur.getColumnIndex("GasLampN"));
                d._Petroleum = cur.getString(cur.getColumnIndex("Petroleum"));
                d._PetroleumN = cur.getString(cur.getColumnIndex("PetroleumN"));
                d._Matt = cur.getString(cur.getColumnIndex("Matt"));
                d._MattN = cur.getString(cur.getColumnIndex("MattN"));
                d._Mats = cur.getString(cur.getColumnIndex("Mats"));
                d._MatsN = cur.getString(cur.getColumnIndex("MatsN"));
                d._Carpets = cur.getString(cur.getColumnIndex("Carpets"));
                d._CarpetsN = cur.getString(cur.getColumnIndex("CarpetsN"));
                d._Bed = cur.getString(cur.getColumnIndex("Bed"));
                d._BedN = cur.getString(cur.getColumnIndex("BedN"));
                d._Chair = cur.getString(cur.getColumnIndex("Chair"));
                d._ChairN = cur.getString(cur.getColumnIndex("ChairN"));
                d._Sofa = cur.getString(cur.getColumnIndex("Sofa"));
                d._SofaN = cur.getString(cur.getColumnIndex("SofaN"));
                d._Tables = cur.getString(cur.getColumnIndex("Tables"));
                d._TablesN = cur.getString(cur.getColumnIndex("TablesN"));
                d._Watch = cur.getString(cur.getColumnIndex("Watch"));
                d._WatchN = cur.getString(cur.getColumnIndex("WatchN"));
                d._WMachine = cur.getString(cur.getColumnIndex("WMachine"));
                d._WMachineN = cur.getString(cur.getColumnIndex("WMachineN"));
                d._Iron = cur.getString(cur.getColumnIndex("Iron"));
                d._IronN = cur.getString(cur.getColumnIndex("IronN"));
                d._Booth = cur.getString(cur.getColumnIndex("Booth"));
                d._BoothN = cur.getString(cur.getColumnIndex("BoothN"));
                d._SMachine = cur.getString(cur.getColumnIndex("SMachine"));
                d._SMachineN = cur.getString(cur.getColumnIndex("SMachineN"));
                d._Generator = cur.getString(cur.getColumnIndex("Generator"));
                d._GeneratorN = cur.getString(cur.getColumnIndex("GeneratorN"));
                d._Internet = cur.getString(cur.getColumnIndex("Internet"));
                d._InternetN = cur.getString(cur.getColumnIndex("InternetN"));
                d._Satellite = cur.getString(cur.getColumnIndex("Satellite"));
                d._SatelliteN = cur.getString(cur.getColumnIndex("SatelliteN"));
                d._Landline = cur.getString(cur.getColumnIndex("Landline"));
                d._LandlineN = cur.getString(cur.getColumnIndex("LandlineN"));
                d._Cellphone = cur.getString(cur.getColumnIndex("Cellphone"));
                d._CellphoneN = cur.getString(cur.getColumnIndex("CellphoneN"));
                d._TV = cur.getString(cur.getColumnIndex("TV"));
                d._TVN = cur.getString(cur.getColumnIndex("TVN"));
                d._TV5 = cur.getString(cur.getColumnIndex("TV5"));
                d._TV5N = cur.getString(cur.getColumnIndex("TV5N"));
                d._Channel = cur.getString(cur.getColumnIndex("Channel"));
                d._ChannelN = cur.getString(cur.getColumnIndex("ChannelN"));
                d._Radio = cur.getString(cur.getColumnIndex("Radio"));
                d._RadioN = cur.getString(cur.getColumnIndex("RadioN"));
                d._DVD = cur.getString(cur.getColumnIndex("DVD"));
                d._DVDN = cur.getString(cur.getColumnIndex("DVDN"));
                d._Video = cur.getString(cur.getColumnIndex("Video"));
                d._VideoN = cur.getString(cur.getColumnIndex("VideoN"));
                d._Computer = cur.getString(cur.getColumnIndex("Computer"));
                d._ComputerN = cur.getString(cur.getColumnIndex("ComputerN"));
                d._Laptop = cur.getString(cur.getColumnIndex("Laptop"));
                d._LaptopN = cur.getString(cur.getColumnIndex("LaptopN"));
                d._Cable = cur.getString(cur.getColumnIndex("Cable"));
                d._CableN = cur.getString(cur.getColumnIndex("CableN"));
                d._Microwave = cur.getString(cur.getColumnIndex("Microwave"));
                d._MicrowaveN = cur.getString(cur.getColumnIndex("MicrowaveN"));
                d._Geyser = cur.getString(cur.getColumnIndex("Geyser"));
                d._GeyserN = cur.getString(cur.getColumnIndex("GeyserN"));
                d._Grill = cur.getString(cur.getColumnIndex("Grill"));
                d._GrillN = cur.getString(cur.getColumnIndex("GrillN"));
                d._Grain = cur.getString(cur.getColumnIndex("Grain"));
                d._GrainN = cur.getString(cur.getColumnIndex("GrainN"));
                d._Refrigerator = cur.getString(cur.getColumnIndex("Refrigerator"));
                d._RefrigeratorN = cur.getString(cur.getColumnIndex("RefrigeratorN"));
                d._DeepFreezer = cur.getString(cur.getColumnIndex("DeepFreezer"));
                d._DeepFreezerN = cur.getString(cur.getColumnIndex("DeepFreezerN"));
                d._Stove = cur.getString(cur.getColumnIndex("Stove"));
                d._StoveN = cur.getString(cur.getColumnIndex("StoveN"));
                d._GasHob = cur.getString(cur.getColumnIndex("GasHob"));
                d._GasHobN = cur.getString(cur.getColumnIndex("GasHobN"));
                d._ImpCooker = cur.getString(cur.getColumnIndex("ImpCooker"));
                d._ImpCookerN = cur.getString(cur.getColumnIndex("ImpCookerN"));
                d._Bike = cur.getString(cur.getColumnIndex("Bike"));
                d._BikeN = cur.getString(cur.getColumnIndex("BikeN"));
                d._Motorcycle = cur.getString(cur.getColumnIndex("Motorcycle"));
                d._MotorcycleN = cur.getString(cur.getColumnIndex("MotorcycleN"));
                d._Car = cur.getString(cur.getColumnIndex("Car"));
                d._CarN = cur.getString(cur.getColumnIndex("CarN"));
                d._Rickshaw = cur.getString(cur.getColumnIndex("Rickshaw"));
                d._RickshawN = cur.getString(cur.getColumnIndex("RickshawN"));
                d._Cart = cur.getString(cur.getColumnIndex("Cart"));
                d._CartN = cur.getString(cur.getColumnIndex("CartN"));
                d._Canoe = cur.getString(cur.getColumnIndex("Canoe"));
                d._CanoeN = cur.getString(cur.getColumnIndex("CanoeN"));
                d._Bus = cur.getString(cur.getColumnIndex("Bus"));
                d._BusN = cur.getString(cur.getColumnIndex("BusN"));
                d._Tractor = cur.getString(cur.getColumnIndex("Tractor"));
                d._TractorN = cur.getString(cur.getColumnIndex("TractorN"));
                d._Plow = cur.getString(cur.getColumnIndex("Plow"));
                d._PlowN = cur.getString(cur.getColumnIndex("PlowN"));
                d._Duck = cur.getString(cur.getColumnIndex("Duck"));
                d._DuckN = cur.getString(cur.getColumnIndex("DuckN"));
                d._Cow = cur.getString(cur.getColumnIndex("Cow"));
                d._CowN = cur.getString(cur.getColumnIndex("CowN"));
                d._Sheep = cur.getString(cur.getColumnIndex("Sheep"));
                d._SheepN = cur.getString(cur.getColumnIndex("SheepN"));
                d._Goat = cur.getString(cur.getColumnIndex("Goat"));
                d._GoatN = cur.getString(cur.getColumnIndex("GoatN"));
                d._Chicken = cur.getString(cur.getColumnIndex("Chicken"));
                d._ChickenN = cur.getString(cur.getColumnIndex("ChickenN"));
                d._Donkey = cur.getString(cur.getColumnIndex("Donkey"));
                d._DunkeyN = cur.getString(cur.getColumnIndex("DunkeyN"));
                d._Horse = cur.getString(cur.getColumnIndex("Horse"));
                d._HorseN = cur.getString(cur.getColumnIndex("HorseN"));
                d._Pig = cur.getString(cur.getColumnIndex("Pig"));
                d._PigN = cur.getString(cur.getColumnIndex("PigN"));
                d._Birds = cur.getString(cur.getColumnIndex("Birds"));
                d._BirdsN = cur.getString(cur.getColumnIndex("BirdsN"));
                d._Dogs = cur.getString(cur.getColumnIndex("Dogs"));
                d._DogsN = cur.getString(cur.getColumnIndex("DogsN"));
                d._Cats = cur.getString(cur.getColumnIndex("Cats"));
                d._CatsN = cur.getString(cur.getColumnIndex("CatsN"));
                d._FishNet = cur.getString(cur.getColumnIndex("FishNet"));
                d._FishNetN = cur.getString(cur.getColumnIndex("FishNetN"));
                d._OtherAsset = cur.getString(cur.getColumnIndex("OtherAsset"));
                d._OtherAsset1 = cur.getString(cur.getColumnIndex("OtherAsset1"));
                d._OtherAsset1N = cur.getString(cur.getColumnIndex("OtherAsset1N"));
                d._OtherAsset2 = cur.getString(cur.getColumnIndex("OtherAsset2"));
                d._OtherAsset2N = cur.getString(cur.getColumnIndex("OtherAsset2N"));
                d._OtherAsset3 = cur.getString(cur.getColumnIndex("OtherAsset3"));
                d._OtherAsset3N = cur.getString(cur.getColumnIndex("OtherAsset3N"));
                d._SESNote = cur.getString(cur.getColumnIndex("SESNote"));
                data.add(d);

                manageAudit(context,d,AuditTrial.KEY_SELECT);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }

     private String _ses_dur = "";
     public String getses_dur(){
         return _ses_dur;
     }
     public List<SES_Mali_DataModel> SelectAll_List(Context context, String SQL)
     {
         Connection C = new Connection(context);
         List<SES_Mali_DataModel> data = new ArrayList<SES_Mali_DataModel>();
         SES_Mali_DataModel d = new SES_Mali_DataModel();
         Cursor cur = C.ReadData(SQL);

         cur.moveToFirst();
         while(!cur.isAfterLast())
         {
             Count += 1;
             d = new SES_Mali_DataModel();
             d._Count = Count;
             d._HHID = cur.getString(cur.getColumnIndex("HHID"));
             d._SESNo = cur.getString(cur.getColumnIndex("SESNo"));
             d._SESVDate = cur.getString(cur.getColumnIndex("SESVDate"));
             if(cur.getString(cur.getColumnIndex("SESVStatus")).equals("1")){
                 d._SESVStatus = "Completed";
             }else if(cur.getString(cur.getColumnIndex("SESVStatus")).equals("2")){
                 d._SESVStatus = "Partially Completed";
             }else if(cur.getString(cur.getColumnIndex("SESVStatus")).equals("3")){
                 d._SESVStatus = "Refused";
             }else{
                 d._SESVStatus = cur.getString(cur.getColumnIndex("SESVStatusOth"));
             }

             d._ses_dur = cur.getString(cur.getColumnIndex("ses_dur"));

             data.add(d);

             cur.moveToNext();
         }
         cur.close();
         return data;
     }



        static Map<String, Object> firstStateMap;
        public void manageAudit(Context context, SES_Mali_DataModel ob, String key) {
            if (key.equalsIgnoreCase(AuditTrial.KEY_SELECT)) {
                //store old state
                firstStateMap = getMapData(ob);
            } else if (key.equalsIgnoreCase(AuditTrial.KEY_UPDATE)) {
                //store new state
                Map<String, Object> secondStateMap = getMapData(ob);
                AuditTrial auditTrial = new AuditTrial(context,TableName);
                // run audit
                if (firstStateMap != null && !firstStateMap.isEmpty() && secondStateMap != null && !secondStateMap.isEmpty()) {
                    auditTrial.audit(firstStateMap, secondStateMap);
                }
            }
        }



        /**
        * get object state
        * @param ob
        * @return
        */
        public Map<String, Object> getMapData(SES_Mali_DataModel ob) {
            Map<String, Object> data = new HashMap<>();

            if (ob != null) {
                 data.put("HHID", ob._HHID);
                 data.put("SESNo", ob._SESNo);
                 data.put("SESVDate", ob._SESVDate);
                 data.put("SESVStatus", ob._SESVStatus);
                 data.put("SESVStatusOth", ob._SESVStatusOth);
                 data.put("Rnd", ob._Rnd);
                 data.put("WSDrink", ob._WSDrink);
                 data.put("WSDrinkOth", ob._WSDrinkOth);
                 data.put("WaterSource", ob._WaterSource);
                 data.put("FetchWaterM", ob._FetchWaterM);
                 data.put("FetchWaterMDk", ob._FetchWaterMDk);
                 data.put("GetWater", ob._GetWater);
                 data.put("GetWaterOth", ob._GetWaterOth);
                 data.put("MemberID", ob._MemberID);
                 data.put("BringWater", ob._BringWater);
                 data.put("BringWaterDk", ob._BringWaterDk);
                 data.put("Someone", ob._Someone);
                 data.put("SecondPers", ob._SecondPers);
                 data.put("SecondPersOth", ob._SecondPersOth);
                 data.put("MemberID2nd", ob._MemberID2nd);
                 data.put("EnoughWater", ob._EnoughWater);
                 data.put("MainWater", ob._MainWater);
                 data.put("MainWaterOth", ob._MainWaterOth);
                 data.put("SmallTank", ob._SmallTank);
                 data.put("MediunTank", ob._MediunTank);
                 data.put("LargeTank", ob._LargeTank);
                 data.put("StoreDrink", ob._StoreDrink);
                 data.put("ContainOpenCov", ob._ContainOpenCov);
                 data.put("ContainOpenNotCov", ob._ContainOpenNotCov);
                 data.put("ContainOpenDK", ob._ContainOpenDK);
                 data.put("RecoverWater", ob._RecoverWater);
                 data.put("RecoverWaterOth", ob._RecoverWaterOth);
                 data.put("LessDanger", ob._LessDanger);
                 data.put("MakeSafer", ob._MakeSafer);
                 data.put("MakeSaferOth", ob._MakeSaferOth);
                 data.put("Toilet", ob._Toilet);
                 data.put("ToiletOth", ob._ToiletOth);
                 data.put("ToiletShrd", ob._ToiletShrd);
                 data.put("ToiletUseNo", ob._ToiletUseNo);
                 data.put("ToiletUseNoDk", ob._ToiletUseNoDk);
                 data.put("ToiletLoc", ob._ToiletLoc);
                 data.put("ContentEmp", ob._ContentEmp);
                 data.put("ContentEmpOth", ob._ContentEmpOth);
                 data.put("BowelMov", ob._BowelMov);
                 data.put("BowelMovOth", ob._BowelMovOth);
                 data.put("LiquidWaste", ob._LiquidWaste);
                 data.put("LiquidWasteOth", ob._LiquidWasteOth);
                 data.put("SolidWasteMethod", ob._SolidWasteMethod);
                 data.put("SolidWasteMethodOth", ob._SolidWasteMethodOth);
                 data.put("HandWash", ob._HandWash);
                 data.put("ShowWash", ob._ShowWash);
                 data.put("AvailableWat", ob._AvailableWat);
                 data.put("AvailableSoap", ob._AvailableSoap);
                 data.put("CookDvc", ob._CookDvc);
                 data.put("CookDvcOth", ob._CookDvcOth);
                 data.put("CookFuel", ob._CookFuel);
                 data.put("CookFuelOth", ob._CookFuelOth);
                 data.put("CookPlc", ob._CookPlc);
                 data.put("CookPlcOth", ob._CookPlcOth);
                 data.put("Floor", ob._Floor);
                 data.put("FloorOth", ob._FloorOth);
                 data.put("GroundMat", ob._GroundMat);
                 data.put("GroundMatOth", ob._GroundMatOth);
                 data.put("Roof", ob._Roof);
                 data.put("RoofOth", ob._RoofOth);
                 data.put("SmokeInside", ob._SmokeInside);
                 data.put("FreqSmoke", ob._FreqSmoke);
                 data.put("Wall", ob._Wall);
                 data.put("WallOth", ob._WallOth);
                 data.put("RoomSleep", ob._RoomSleep);
                 data.put("RoomSleepDk", ob._RoomSleepDk);
                 data.put("ElecNight", ob._ElecNight);
                 data.put("ElecNightOth", ob._ElecNightOth);
                 data.put("HomesteadAny", ob._HomesteadAny);
                 data.put("OthLand", ob._OthLand);
                 data.put("Area", ob._Area);
                 data.put("IncomeMo", ob._IncomeMo);
                 data.put("Expenses", ob._Expenses);
                 data.put("BankAcc", ob._BankAcc);
                 data.put("SprayInt", ob._SprayInt);
                 data.put("MosqNet", ob._MosqNet);
                 data.put("NetOwned", ob._NetOwned);
                 data.put("MedHome", ob._MedHome);
                 data.put("MedTypeAM", ob._MedTypeAM);
                 data.put("MedTypeAB", ob._MedTypeAB);
                 data.put("MedTypeDK", ob._MedTypeDK);
                 data.put("AntimalarAL", ob._AntimalarAL);
                 data.put("AntimalarASAQ", ob._AntimalarASAQ);
                 data.put("AntimalarSP", ob._AntimalarSP);
                 data.put("AntimalarOth", ob._AntimalarOth);
                 data.put("AntimalarSpecifyOth", ob._AntimalarSpecifyOth);
                 data.put("GetMedHosp", ob._GetMedHosp);
                 data.put("GetMedCSCom", ob._GetMedCSCom);
                 data.put("GetMedPrvCl", ob._GetMedPrvCl);
                 data.put("GetMedPhar", ob._GetMedPhar);
                 data.put("GetMedPD", ob._GetMedPD);
                 data.put("GetMedCHW", ob._GetMedCHW);
                 data.put("GetMedSS", ob._GetMedSS);
                 data.put("GetMedOth", ob._GetMedOth);
                 data.put("GetMedSpecifyOth", ob._GetMedSpecifyOth);
                 data.put("AComment", ob._AComment);
                 data.put("Comment", ob._Comment);
                 data.put("Electricity", ob._Electricity);
                 data.put("SolarPlate", ob._SolarPlate);
                 data.put("SolarPlateN", ob._SolarPlateN);
                 data.put("Heater", ob._Heater);
                 data.put("HeaterN", ob._HeaterN);
                 data.put("AC", ob._AC);
                 data.put("ACN", ob._ACN);
                 data.put("ElecFan", ob._ElecFan);
                 data.put("ElecFanN", ob._ElecFanN);
                 data.put("Lantern", ob._Lantern);
                 data.put("LanternN", ob._LanternN);
                 data.put("Lamp", ob._Lamp);
                 data.put("LampN", ob._LampN);
                 data.put("GasLamp", ob._GasLamp);
                 data.put("GasLampN", ob._GasLampN);
                 data.put("Petroleum", ob._Petroleum);
                 data.put("PetroleumN", ob._PetroleumN);
                 data.put("Matt", ob._Matt);
                 data.put("MattN", ob._MattN);
                 data.put("Mats", ob._Mats);
                 data.put("MatsN", ob._MatsN);
                 data.put("Carpets", ob._Carpets);
                 data.put("CarpetsN", ob._CarpetsN);
                 data.put("Bed", ob._Bed);
                 data.put("BedN", ob._BedN);
                 data.put("Chair", ob._Chair);
                 data.put("ChairN", ob._ChairN);
                 data.put("Sofa", ob._Sofa);
                 data.put("SofaN", ob._SofaN);
                 data.put("Tables", ob._Tables);
                 data.put("TablesN", ob._TablesN);
                 data.put("Watch", ob._Watch);
                 data.put("WatchN", ob._WatchN);
                 data.put("WMachine", ob._WMachine);
                 data.put("WMachineN", ob._WMachineN);
                 data.put("Iron", ob._Iron);
                 data.put("IronN", ob._IronN);
                 data.put("Booth", ob._Booth);
                 data.put("BoothN", ob._BoothN);
                 data.put("SMachine", ob._SMachine);
                 data.put("SMachineN", ob._SMachineN);
                 data.put("Generator", ob._Generator);
                 data.put("GeneratorN", ob._GeneratorN);
                 data.put("Internet", ob._Internet);
                 data.put("InternetN", ob._InternetN);
                 data.put("Satellite", ob._Satellite);
                 data.put("SatelliteN", ob._SatelliteN);
                 data.put("Landline", ob._Landline);
                 data.put("LandlineN", ob._LandlineN);
                 data.put("Cellphone", ob._Cellphone);
                 data.put("CellphoneN", ob._CellphoneN);
                 data.put("TV", ob._TV);
                 data.put("TVN", ob._TVN);
                 data.put("TV5", ob._TV5);
                 data.put("TV5N", ob._TV5N);
                 data.put("Channel", ob._Channel);
                 data.put("ChannelN", ob._ChannelN);
                 data.put("Radio", ob._Radio);
                 data.put("RadioN", ob._RadioN);
                 data.put("DVD", ob._DVD);
                 data.put("DVDN", ob._DVDN);
                 data.put("Video", ob._Video);
                 data.put("VideoN", ob._VideoN);
                 data.put("Computer", ob._Computer);
                 data.put("ComputerN", ob._ComputerN);
                 data.put("Laptop", ob._Laptop);
                 data.put("LaptopN", ob._LaptopN);
                 data.put("Cable", ob._Cable);
                 data.put("CableN", ob._CableN);
                 data.put("Microwave", ob._Microwave);
                 data.put("MicrowaveN", ob._MicrowaveN);
                 data.put("Geyser", ob._Geyser);
                 data.put("GeyserN", ob._GeyserN);
                 data.put("Grill", ob._Grill);
                 data.put("GrillN", ob._GrillN);
                 data.put("Grain", ob._Grain);
                 data.put("GrainN", ob._GrainN);
                 data.put("Refrigerator", ob._Refrigerator);
                 data.put("RefrigeratorN", ob._RefrigeratorN);
                 data.put("DeepFreezer", ob._DeepFreezer);
                 data.put("DeepFreezerN", ob._DeepFreezerN);
                 data.put("Stove", ob._Stove);
                 data.put("StoveN", ob._StoveN);
                 data.put("GasHob", ob._GasHob);
                 data.put("GasHobN", ob._GasHobN);
                 data.put("ImpCooker", ob._ImpCooker);
                 data.put("ImpCookerN", ob._ImpCookerN);
                 data.put("Bike", ob._Bike);
                 data.put("BikeN", ob._BikeN);
                 data.put("Motorcycle", ob._Motorcycle);
                 data.put("MotorcycleN", ob._MotorcycleN);
                 data.put("Car", ob._Car);
                 data.put("CarN", ob._CarN);
                 data.put("Rickshaw", ob._Rickshaw);
                 data.put("RickshawN", ob._RickshawN);
                 data.put("Cart", ob._Cart);
                 data.put("CartN", ob._CartN);
                 data.put("Canoe", ob._Canoe);
                 data.put("CanoeN", ob._CanoeN);
                 data.put("Bus", ob._Bus);
                 data.put("BusN", ob._BusN);
                 data.put("Tractor", ob._Tractor);
                 data.put("TractorN", ob._TractorN);
                 data.put("Plow", ob._Plow);
                 data.put("PlowN", ob._PlowN);
                 data.put("Duck", ob._Duck);
                 data.put("DuckN", ob._DuckN);
                 data.put("Cow", ob._Cow);
                 data.put("CowN", ob._CowN);
                 data.put("Sheep", ob._Sheep);
                 data.put("SheepN", ob._SheepN);
                 data.put("Goat", ob._Goat);
                 data.put("GoatN", ob._GoatN);
                 data.put("Chicken", ob._Chicken);
                 data.put("ChickenN", ob._ChickenN);
                 data.put("Donkey", ob._Donkey);
                 data.put("DunkeyN", ob._DunkeyN);
                 data.put("Horse", ob._Horse);
                 data.put("HorseN", ob._HorseN);
                 data.put("Pig", ob._Pig);
                 data.put("PigN", ob._PigN);
                 data.put("Birds", ob._Birds);
                 data.put("BirdsN", ob._BirdsN);
                 data.put("Dogs", ob._Dogs);
                 data.put("DogsN", ob._DogsN);
                 data.put("Cats", ob._Cats);
                 data.put("CatsN", ob._CatsN);
                 data.put("FishNet", ob._FishNet);
                 data.put("FishNetN", ob._FishNetN);
                 data.put("OtherAsset", ob._OtherAsset);
                 data.put("OtherAsset1", ob._OtherAsset1);
                 data.put("OtherAsset1N", ob._OtherAsset1N);
                 data.put("OtherAsset2", ob._OtherAsset2);
                 data.put("OtherAsset2N", ob._OtherAsset2N);
                 data.put("OtherAsset3", ob._OtherAsset3);
                 data.put("OtherAsset3N", ob._OtherAsset3N);
                 data.put("SESNote", ob._SESNote);
            
            }
            return data;
        }
 }