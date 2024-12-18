package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class tmpClimate_Change_DataModel {

       private String _HHID = "";
       public String getHHID(){
             return _HHID;
        }
       public void setHHID(String newValue){
             _HHID = newValue;
        }
       private String _ClimateSL = "";
       public String getClimateSL(){
             return _ClimateSL;
        }
       public void setClimateSL(String newValue){
             _ClimateSL = newValue;
        }
       private String _ClimateVDate = "";
       public String getClimateVDate(){
             return _ClimateVDate;
        }
       public void setClimateVDate(String newValue){
             _ClimateVDate = newValue;
        }
       private String _ClimateVStatus = "";
       public String getClimateVStatus(){
             return _ClimateVStatus;
        }
       public void setClimateVStatus(String newValue){
             _ClimateVStatus = newValue;
        }
       private String _ClimateVStatusOth = "";
       public String getClimateVStatusOth(){
             return _ClimateVStatusOth;
        }
       public void setClimateVStatusOth(String newValue){
             _ClimateVStatusOth = newValue;
        }
       private String _Rnd = "";
       public String getRnd(){
             return _Rnd;
        }
       public void setRnd(String newValue){
             _Rnd = newValue;
        }
       private String _ClimateChangesNoticed = "";
       public String getClimateChangesNoticed(){
             return _ClimateChangesNoticed;
        }
       public void setClimateChangesNoticed(String newValue){
             _ClimateChangesNoticed = newValue;
        }
       private String _ClimateChangesTemp = "";
       public String getClimateChangesTemp(){
             return _ClimateChangesTemp;
        }
       public void setClimateChangesTemp(String newValue){
             _ClimateChangesTemp = newValue;
        }
       private String _ClimateChangesSeason = "";
       public String getClimateChangesSeason(){
             return _ClimateChangesSeason;
        }
       public void setClimateChangesSeason(String newValue){
             _ClimateChangesSeason = newValue;
        }
       private String _ClimateChangesWeather = "";
       public String getClimateChangesWeather(){
             return _ClimateChangesWeather;
        }
       public void setClimateChangesWeather(String newValue){
             _ClimateChangesWeather = newValue;
        }
       private String _ClimateChangesFlora = "";
       public String getClimateChangesFlora(){
             return _ClimateChangesFlora;
        }
       public void setClimateChangesFlora(String newValue){
             _ClimateChangesFlora = newValue;
        }
       private String _ClimateChangesOther = "";
       public String getClimateChangesOther(){
             return _ClimateChangesOther;
        }
       public void setClimateChangesOther(String newValue){
             _ClimateChangesOther = newValue;
        }
       private String _ClimateChangesOtherSp = "";
       public String getClimateChangesOtherSp(){
             return _ClimateChangesOtherSp;
        }
       public void setClimateChangesOtherSp(String newValue){
             _ClimateChangesOtherSp = newValue;
        }
       private String _ClimateAffectDailyLife = "";
       public String getClimateAffectDailyLife(){
             return _ClimateAffectDailyLife;
        }
       public void setClimateAffectDailyLife(String newValue){
             _ClimateAffectDailyLife = newValue;
        }
       private String _ClimateFlood = "";
       public String getClimateFlood(){
             return _ClimateFlood;
        }
       public void setClimateFlood(String newValue){
             _ClimateFlood = newValue;
        }
       private String _ClimateFloodHouse = "";
       public String getClimateFloodHouse(){
             return _ClimateFloodHouse;
        }
       public void setClimateFloodHouse(String newValue){
             _ClimateFloodHouse = newValue;
        }
       private String _ClimateFloodWall = "";
       public String getClimateFloodWall(){
             return _ClimateFloodWall;
        }
       public void setClimateFloodWall(String newValue){
             _ClimateFloodWall = newValue;
        }
       private String _ClimateFloodMember = "";
       public String getClimateFloodMember(){
             return _ClimateFloodMember;
        }
       public void setClimateFloodMember(String newValue){
             _ClimateFloodMember = newValue;
        }
       private String _ClimateFloodLivestock = "";
       public String getClimateFloodLivestock(){
             return _ClimateFloodLivestock;
        }
       public void setClimateFloodLivestock(String newValue){
             _ClimateFloodLivestock = newValue;
        }
       private String _ClimateFloodJobLess = "";
       public String getClimateFloodJobLess(){
             return _ClimateFloodJobLess;
        }
       public void setClimateFloodJobLess(String newValue){
             _ClimateFloodJobLess = newValue;
        }
       private String _ClimateFloodEnergy = "";
       public String getClimateFloodEnergy(){
             return _ClimateFloodEnergy;
        }
       public void setClimateFloodEnergy(String newValue){
             _ClimateFloodEnergy = newValue;
        }
       private String _ClimateFloodToilets = "";
       public String getClimateFloodToilets(){
             return _ClimateFloodToilets;
        }
       public void setClimateFloodToilets(String newValue){
             _ClimateFloodToilets = newValue;
        }
       private String _ClimateFloodHealth = "";
       public String getClimateFloodHealth(){
             return _ClimateFloodHealth;
        }
       public void setClimateFloodHealth(String newValue){
             _ClimateFloodHealth = newValue;
        }
       private String _ClimateFloodMigration = "";
       public String getClimateFloodMigration(){
             return _ClimateFloodMigration;
        }
       public void setClimateFloodMigration(String newValue){
             _ClimateFloodMigration = newValue;
        }
       private String _ClimateFloodOther = "";
       public String getClimateFloodOther(){
             return _ClimateFloodOther;
        }
       public void setClimateFloodOther(String newValue){
             _ClimateFloodOther = newValue;
        }
       private String _ClimateFloodOtherSp = "";
       public String getClimateFloodOtherSp(){
             return _ClimateFloodOtherSp;
        }
       public void setClimateFloodOtherSp(String newValue){
             _ClimateFloodOtherSp = newValue;
        }
       private String _ClimateHighHeat = "";
       public String getClimateHighHeat(){
             return _ClimateHighHeat;
        }
       public void setClimateHighHeat(String newValue){
             _ClimateHighHeat = newValue;
        }
       private String _ClimateHighHeatMemberDth = "";
       public String getClimateHighHeatMemberDth(){
             return _ClimateHighHeatMemberDth;
        }
       public void setClimateHighHeatMemberDth(String newValue){
             _ClimateHighHeatMemberDth = newValue;
        }
       private String _ClimateHighHeatHealth = "";
       public String getClimateHighHeatHealth(){
             return _ClimateHighHeatHealth;
        }
       public void setClimateHighHeatHealth(String newValue){
             _ClimateHighHeatHealth = newValue;
        }
       private String _ClimateHighHeatMemberIll = "";
       public String getClimateHighHeatMemberIll(){
             return _ClimateHighHeatMemberIll;
        }
       public void setClimateHighHeatMemberIll(String newValue){
             _ClimateHighHeatMemberIll = newValue;
        }
       private String _ClimateHighHeatPetDth = "";
       public String getClimateHighHeatPetDth(){
             return _ClimateHighHeatPetDth;
        }
       public void setClimateHighHeatPetDth(String newValue){
             _ClimateHighHeatPetDth = newValue;
        }
       private String _ClimateHighHeatOther = "";
       public String getClimateHighHeatOther(){
             return _ClimateHighHeatOther;
        }
       public void setClimateHighHeatOther(String newValue){
             _ClimateHighHeatOther = newValue;
        }
       private String _ClimateHighHeatOtherSp = "";
       public String getClimateHighHeatOtherSp(){
             return _ClimateHighHeatOtherSp;
        }
       public void setClimateHighHeatOtherSp(String newValue){
             _ClimateHighHeatOtherSp = newValue;
        }
       private String _ClimateHealthProblemSleep = "";
       public String getClimateHealthProblemSleep(){
             return _ClimateHealthProblemSleep;
        }
       public void setClimateHealthProblemSleep(String newValue){
             _ClimateHealthProblemSleep = newValue;
        }
       private String _ClimateHealthProblemDizzy = "";
       public String getClimateHealthProblemDizzy(){
             return _ClimateHealthProblemDizzy;
        }
       public void setClimateHealthProblemDizzy(String newValue){
             _ClimateHealthProblemDizzy = newValue;
        }
       private String _ClimateHealthProblemLowBlood = "";
       public String getClimateHealthProblemLowBlood(){
             return _ClimateHealthProblemLowBlood;
        }
       public void setClimateHealthProblemLowBlood(String newValue){
             _ClimateHealthProblemLowBlood = newValue;
        }
       private String _ClimateHealthProblemHighBlood = "";
       public String getClimateHealthProblemHighBlood(){
             return _ClimateHealthProblemHighBlood;
        }
       public void setClimateHealthProblemHighBlood(String newValue){
             _ClimateHealthProblemHighBlood = newValue;
        }
       private String _ClimateHealthProblemStroke = "";
       public String getClimateHealthProblemStroke(){
             return _ClimateHealthProblemStroke;
        }
       public void setClimateHealthProblemStroke(String newValue){
             _ClimateHealthProblemStroke = newValue;
        }
       private String _ClimateHealthProblemFever = "";
       public String getClimateHealthProblemFever(){
             return _ClimateHealthProblemFever;
        }
       public void setClimateHealthProblemFever(String newValue){
             _ClimateHealthProblemFever = newValue;
        }
       private String _ClimateHealthProblemOther = "";
       public String getClimateHealthProblemOther(){
             return _ClimateHealthProblemOther;
        }
       public void setClimateHealthProblemOther(String newValue){
             _ClimateHealthProblemOther = newValue;
        }
       private String _ClimateHealthProblemOtherSp = "";
       public String getClimateHealthProblemOtherSp(){
             return _ClimateHealthProblemOtherSp;
        }
       public void setClimateHealthProblemOtherSp(String newValue){
             _ClimateHealthProblemOtherSp = newValue;
        }
       private String _ClimateHealthProblemPeriod = "";
       public String getClimateHealthProblemPeriod(){
             return _ClimateHealthProblemPeriod;
        }
       public void setClimateHealthProblemPeriod(String newValue){
             _ClimateHealthProblemPeriod = newValue;
        }
       private String _ClimateIllnessMalaria = "";
       public String getClimateIllnessMalaria(){
             return _ClimateIllnessMalaria;
        }
       public void setClimateIllnessMalaria(String newValue){
             _ClimateIllnessMalaria = newValue;
        }
       private String _ClimateIllnessPneumonia = "";
       public String getClimateIllnessPneumonia(){
             return _ClimateIllnessPneumonia;
        }
       public void setClimateIllnessPneumonia(String newValue){
             _ClimateIllnessPneumonia = newValue;
        }
       private String _ClimateIllnessMeasles = "";
       public String getClimateIllnessMeasles(){
             return _ClimateIllnessMeasles;
        }
       public void setClimateIllnessMeasles(String newValue){
             _ClimateIllnessMeasles = newValue;
        }
       private String _ClimateIllnessPertussis = "";
       public String getClimateIllnessPertussis(){
             return _ClimateIllnessPertussis;
        }
       public void setClimateIllnessPertussis(String newValue){
             _ClimateIllnessPertussis = newValue;
        }
       private String _ClimateIllnessDiarrhea = "";
       public String getClimateIllnessDiarrhea(){
             return _ClimateIllnessDiarrhea;
        }
       public void setClimateIllnessDiarrhea(String newValue){
             _ClimateIllnessDiarrhea = newValue;
        }
       private String _ClimateIllnessFood = "";
       public String getClimateIllnessFood(){
             return _ClimateIllnessFood;
        }
       public void setClimateIllnessFood(String newValue){
             _ClimateIllnessFood = newValue;
        }
       private String _ClimateIllnessTyphoid = "";
       public String getClimateIllnessTyphoid(){
             return _ClimateIllnessTyphoid;
        }
       public void setClimateIllnessTyphoid(String newValue){
             _ClimateIllnessTyphoid = newValue;
        }
       private String _ClimateIllnessCough = "";
       public String getClimateIllnessCough(){
             return _ClimateIllnessCough;
        }
       public void setClimateIllnessCough(String newValue){
             _ClimateIllnessCough = newValue;
        }
       private String _ClimateIllnessCholera = "";
       public String getClimateIllnessCholera(){
             return _ClimateIllnessCholera;
        }
       public void setClimateIllnessCholera(String newValue){
             _ClimateIllnessCholera = newValue;
        }
       private String _ClimateIllnessOther = "";
       public String getClimateIllnessOther(){
             return _ClimateIllnessOther;
        }
       public void setClimateIllnessOther(String newValue){
             _ClimateIllnessOther = newValue;
        }
       private String _ClimateIllnessOtherSp = "";
       public String getClimateIllnessOtherSp(){
             return _ClimateIllnessOtherSp;
        }
       public void setClimateIllnessOtherSp(String newValue){
             _ClimateIllnessOtherSp = newValue;
        }
       private String _ClimateIllnessPeriod = "";
       public String getClimateIllnessPeriod(){
             return _ClimateIllnessPeriod;
        }
       public void setClimateIllnessPeriod(String newValue){
             _ClimateIllnessPeriod = newValue;
        }
       private String _ClimateHeard = "";
       public String getClimateHeard(){
             return _ClimateHeard;
        }
       public void setClimateHeard(String newValue){
             _ClimateHeard = newValue;
        }
       private String _ClimateInfoMedia = "";
       public String getClimateInfoMedia(){
             return _ClimateInfoMedia;
        }
       public void setClimateInfoMedia(String newValue){
             _ClimateInfoMedia = newValue;
        }
       private String _ClimateInfoInternet = "";
       public String getClimateInfoInternet(){
             return _ClimateInfoInternet;
        }
       public void setClimateInfoInternet(String newValue){
             _ClimateInfoInternet = newValue;
        }
       private String _ClimateInfoEducation = "";
       public String getClimateInfoEducation(){
             return _ClimateInfoEducation;
        }
       public void setClimateInfoEducation(String newValue){
             _ClimateInfoEducation = newValue;
        }
       private String _ClimateInfoFnF = "";
       public String getClimateInfoFnF(){
             return _ClimateInfoFnF;
        }
       public void setClimateInfoFnF(String newValue){
             _ClimateInfoFnF = newValue;
        }
       private String _ClimateInfoOther = "";
       public String getClimateInfoOther(){
             return _ClimateInfoOther;
        }
       public void setClimateInfoOther(String newValue){
             _ClimateInfoOther = newValue;
        }
       private String _ClimateInfoOtherSp = "";
       public String getClimateInfoOtherSp(){
             return _ClimateInfoOtherSp;
        }
       public void setClimateInfoOtherSp(String newValue){
             _ClimateInfoOtherSp = newValue;
        }
       private String _ClimateKnRate = "";
       public String getClimateKnRate(){
             return _ClimateKnRate;
        }
       public void setClimateKnRate(String newValue){
             _ClimateKnRate = newValue;
        }
       private String _Climatethreat = "";
       public String getClimatethreat(){
             return _Climatethreat;
        }
       public void setClimatethreat(String newValue){
             _Climatethreat = newValue;
        }
       private String _ClimateWorried = "";
       public String getClimateWorried(){
             return _ClimateWorried;
        }
       public void setClimateWorried(String newValue){
             _ClimateWorried = newValue;
        }
       private String _ClimateConcernTemp = "";
       public String getClimateConcernTemp(){
             return _ClimateConcernTemp;
        }
       public void setClimateConcernTemp(String newValue){
             _ClimateConcernTemp = newValue;
        }
       private String _ClimateConcernEvent = "";
       public String getClimateConcernEvent(){
             return _ClimateConcernEvent;
        }
       public void setClimateConcernEvent(String newValue){
             _ClimateConcernEvent = newValue;
        }
       private String _ClimateConcernPolarIce = "";
       public String getClimateConcernPolarIce(){
             return _ClimateConcernPolarIce;
        }
       public void setClimateConcernPolarIce(String newValue){
             _ClimateConcernPolarIce = newValue;
        }
       private String _ClimateConcernSeaLevel = "";
       public String getClimateConcernSeaLevel(){
             return _ClimateConcernSeaLevel;
        }
       public void setClimateConcernSeaLevel(String newValue){
             _ClimateConcernSeaLevel = newValue;
        }
       private String _ClimateConcernBiodiversity = "";
       public String getClimateConcernBiodiversity(){
             return _ClimateConcernBiodiversity;
        }
       public void setClimateConcernBiodiversity(String newValue){
             _ClimateConcernBiodiversity = newValue;
        }
       private String _ClimateConcernDrying = "";
       public String getClimateConcernDrying(){
             return _ClimateConcernDrying;
        }
       public void setClimateConcernDrying(String newValue){
             _ClimateConcernDrying = newValue;
        }
       private String _ClimateConcernRising = "";
       public String getClimateConcernRising(){
             return _ClimateConcernRising;
        }
       public void setClimateConcernRising(String newValue){
             _ClimateConcernRising = newValue;
        }
       private String _ClimateConcernDrought = "";
       public String getClimateConcernDrought(){
             return _ClimateConcernDrought;
        }
       public void setClimateConcernDrought(String newValue){
             _ClimateConcernDrought = newValue;
        }
       private String _ClimateConcernDesert = "";
       public String getClimateConcernDesert(){
             return _ClimateConcernDesert;
        }
       public void setClimateConcernDesert(String newValue){
             _ClimateConcernDesert = newValue;
        }
       private String _ClimateConcernErosin = "";
       public String getClimateConcernErosin(){
             return _ClimateConcernErosin;
        }
       public void setClimateConcernErosin(String newValue){
             _ClimateConcernErosin = newValue;
        }
       private String _ClimateConcernOther = "";
       public String getClimateConcernOther(){
             return _ClimateConcernOther;
        }
       public void setClimateConcernOther(String newValue){
             _ClimateConcernOther = newValue;
        }
       private String _ClimateConcernOtherSp = "";
       public String getClimateConcernOtherSp(){
             return _ClimateConcernOtherSp;
        }
       public void setClimateConcernOtherSp(String newValue){
             _ClimateConcernOtherSp = newValue;
        }
       private String _ClimateComDoing = "";
       public String getClimateComDoing(){
             return _ClimateComDoing;
        }
       public void setClimateComDoing(String newValue){
             _ClimateComDoing = newValue;
        }
       private String _ClimateReduceImpact = "";
       public String getClimateReduceImpact(){
             return _ClimateReduceImpact;
        }
       public void setClimateReduceImpact(String newValue){
             _ClimateReduceImpact = newValue;
        }
       private String _ClimateActionsPlastics = "";
       public String getClimateActionsPlastics(){
             return _ClimateActionsPlastics;
        }
       public void setClimateActionsPlastics(String newValue){
             _ClimateActionsPlastics = newValue;
        }
       private String _ClimateActionsTransport = "";
       public String getClimateActionsTransport(){
             return _ClimateActionsTransport;
        }
       public void setClimateActionsTransport(String newValue){
             _ClimateActionsTransport = newValue;
        }
       private String _ClimateActionsFoods = "";
       public String getClimateActionsFoods(){
             return _ClimateActionsFoods;
        }
       public void setClimateActionsFoods(String newValue){
             _ClimateActionsFoods = newValue;
        }
       private String _ClimateActionsHome = "";
       public String getClimateActionsHome(){
             return _ClimateActionsHome;
        }
       public void setClimateActionsHome(String newValue){
             _ClimateActionsHome = newValue;
        }
       private String _ClimateActionsOther = "";
       public String getClimateActionsOther(){
             return _ClimateActionsOther;
        }
       public void setClimateActionsOther(String newValue){
             _ClimateActionsOther = newValue;
        }
       private String _ClimateActionsOtherSp = "";
       public String getClimateActionsOtherSp(){
             return _ClimateActionsOtherSp;
        }
       public void setClimateActionsOtherSp(String newValue){
             _ClimateActionsOtherSp = newValue;
        }
       private String _ClimateRenewableEnergy = "";
       public String getClimateRenewableEnergy(){
             return _ClimateRenewableEnergy;
        }
       public void setClimateRenewableEnergy(String newValue){
             _ClimateRenewableEnergy = newValue;
        }
       private String _ClimateObstaclesCost = "";
       public String getClimateObstaclesCost(){
             return _ClimateObstaclesCost;
        }
       public void setClimateObstaclesCost(String newValue){
             _ClimateObstaclesCost = newValue;
        }
        private String _ClimateObstaclesLackInfo = "";
        public String getClimateObstaclesLackInfo(){
            return _ClimateObstaclesLackInfo;
        }
        public void setClimateObstaclesLackInfo(String newValue){
            _ClimateObstaclesLackInfo = newValue;
        }
       private String _ClimateObstaclesAccess = "";
       public String getClimateObstaclesAccess(){
             return _ClimateObstaclesAccess;
        }
       public void setClimateObstaclesAccess(String newValue){
             _ClimateObstaclesAccess = newValue;
        }
       private String _ClimateObstaclesHabit = "";
       public String getClimateObstaclesHabit(){
             return _ClimateObstaclesHabit;
        }
       public void setClimateObstaclesHabit(String newValue){
             _ClimateObstaclesHabit = newValue;
        }
       private String _ClimateObstaclesOther = "";
       public String getClimateObstaclesOther(){
             return _ClimateObstaclesOther;
        }
       public void setClimateObstaclesOther(String newValue){
             _ClimateObstaclesOther = newValue;
        }
       private String _ClimateObstaclesOtherSp = "";
       public String getClimateObstaclesOtherSp(){
             return _ClimateObstaclesOtherSp;
        }
       public void setClimateObstaclesOtherSp(String newValue){
             _ClimateObstaclesOtherSp = newValue;
        }
       private String _ClimateNote = "";
       public String getClimateNote(){
             return _ClimateNote;
        }
       public void setClimateNote(String newValue){
             _ClimateNote = newValue;
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

       String TableName = "tmpClimate_Change";

       public String SaveUpdateData(Context context)
       {
           String response = "";
           C = new Connection(context);
           String SQL = "";
           try
           {
                if(C.Existence("Select * from "+ TableName +"  Where HHID='"+ _HHID +"' and ClimateSL='"+ _ClimateSL +"' "))
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
                contentValues.put("ClimateSL", _ClimateSL);
                contentValues.put("ClimateVDate", _ClimateVDate);
                contentValues.put("ClimateVStatus", _ClimateVStatus);
                contentValues.put("ClimateVStatusOth", _ClimateVStatusOth);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("ClimateChangesNoticed", _ClimateChangesNoticed);
                contentValues.put("ClimateChangesTemp", _ClimateChangesTemp);
                contentValues.put("ClimateChangesSeason", _ClimateChangesSeason);
                contentValues.put("ClimateChangesWeather", _ClimateChangesWeather);
                contentValues.put("ClimateChangesFlora", _ClimateChangesFlora);
                contentValues.put("ClimateChangesOther", _ClimateChangesOther);
                contentValues.put("ClimateChangesOtherSp", _ClimateChangesOtherSp);
                contentValues.put("ClimateAffectDailyLife", _ClimateAffectDailyLife);
                contentValues.put("ClimateFlood", _ClimateFlood);
                contentValues.put("ClimateFloodHouse", _ClimateFloodHouse);
                contentValues.put("ClimateFloodWall", _ClimateFloodWall);
                contentValues.put("ClimateFloodMember", _ClimateFloodMember);
                contentValues.put("ClimateFloodLivestock", _ClimateFloodLivestock);
                contentValues.put("ClimateFloodJobLess", _ClimateFloodJobLess);
                contentValues.put("ClimateFloodEnergy", _ClimateFloodEnergy);
                contentValues.put("ClimateFloodToilets", _ClimateFloodToilets);
                contentValues.put("ClimateFloodHealth", _ClimateFloodHealth);
                contentValues.put("ClimateFloodMigration", _ClimateFloodMigration);
                contentValues.put("ClimateFloodOther", _ClimateFloodOther);
                contentValues.put("ClimateFloodOtherSp", _ClimateFloodOtherSp);
                contentValues.put("ClimateHighHeat", _ClimateHighHeat);
                contentValues.put("ClimateHighHeatMemberDth", _ClimateHighHeatMemberDth);
                contentValues.put("ClimateHighHeatHealth", _ClimateHighHeatHealth);
                contentValues.put("ClimateHighHeatMemberIll", _ClimateHighHeatMemberIll);
                contentValues.put("ClimateHighHeatPetDth", _ClimateHighHeatPetDth);
                contentValues.put("ClimateHighHeatOther", _ClimateHighHeatOther);
                contentValues.put("ClimateHighHeatOtherSp", _ClimateHighHeatOtherSp);
                contentValues.put("ClimateHealthProblemSleep", _ClimateHealthProblemSleep);
                contentValues.put("ClimateHealthProblemDizzy", _ClimateHealthProblemDizzy);
                contentValues.put("ClimateHealthProblemLowBlood", _ClimateHealthProblemLowBlood);
                contentValues.put("ClimateHealthProblemHighBlood", _ClimateHealthProblemHighBlood);
                contentValues.put("ClimateHealthProblemStroke", _ClimateHealthProblemStroke);
                contentValues.put("ClimateHealthProblemFever", _ClimateHealthProblemFever);
                contentValues.put("ClimateHealthProblemOther", _ClimateHealthProblemOther);
                contentValues.put("ClimateHealthProblemOtherSp", _ClimateHealthProblemOtherSp);
                contentValues.put("ClimateHealthProblemPeriod", _ClimateHealthProblemPeriod);
                contentValues.put("ClimateIllnessMalaria", _ClimateIllnessMalaria);
                contentValues.put("ClimateIllnessPneumonia", _ClimateIllnessPneumonia);
                contentValues.put("ClimateIllnessMeasles", _ClimateIllnessMeasles);
                contentValues.put("ClimateIllnessPertussis", _ClimateIllnessPertussis);
                contentValues.put("ClimateIllnessDiarrhea", _ClimateIllnessDiarrhea);
                contentValues.put("ClimateIllnessFood", _ClimateIllnessFood);
                contentValues.put("ClimateIllnessTyphoid", _ClimateIllnessTyphoid);
                contentValues.put("ClimateIllnessCough", _ClimateIllnessCough);
                contentValues.put("ClimateIllnessCholera", _ClimateIllnessCholera);
                contentValues.put("ClimateIllnessOther", _ClimateIllnessOther);
                contentValues.put("ClimateIllnessOtherSp", _ClimateIllnessOtherSp);
                contentValues.put("ClimateIllnessPeriod", _ClimateIllnessPeriod);
                contentValues.put("ClimateHeard", _ClimateHeard);
                contentValues.put("ClimateInfoMedia", _ClimateInfoMedia);
                contentValues.put("ClimateInfoInternet", _ClimateInfoInternet);
                contentValues.put("ClimateInfoEducation", _ClimateInfoEducation);
                contentValues.put("ClimateInfoFnF", _ClimateInfoFnF);
                contentValues.put("ClimateInfoOther", _ClimateInfoOther);
                contentValues.put("ClimateInfoOtherSp", _ClimateInfoOtherSp);
                contentValues.put("ClimateKnRate", _ClimateKnRate);
                contentValues.put("Climatethreat", _Climatethreat);
                contentValues.put("ClimateWorried", _ClimateWorried);
                contentValues.put("ClimateConcernTemp", _ClimateConcernTemp);
                contentValues.put("ClimateConcernEvent", _ClimateConcernEvent);
                contentValues.put("ClimateConcernPolarIce", _ClimateConcernPolarIce);
                contentValues.put("ClimateConcernSeaLevel", _ClimateConcernSeaLevel);
                contentValues.put("ClimateConcernBiodiversity", _ClimateConcernBiodiversity);
                contentValues.put("ClimateConcernDrying", _ClimateConcernDrying);
                contentValues.put("ClimateConcernRising", _ClimateConcernRising);
                contentValues.put("ClimateConcernDrought", _ClimateConcernDrought);
                contentValues.put("ClimateConcernDesert", _ClimateConcernDesert);
                contentValues.put("ClimateConcernErosin", _ClimateConcernErosin);
                contentValues.put("ClimateConcernOther", _ClimateConcernOther);
                contentValues.put("ClimateConcernOtherSp", _ClimateConcernOtherSp);
                contentValues.put("ClimateComDoing", _ClimateComDoing);
                contentValues.put("ClimateReduceImpact", _ClimateReduceImpact);
                contentValues.put("ClimateActionsPlastics", _ClimateActionsPlastics);
                contentValues.put("ClimateActionsTransport", _ClimateActionsTransport);
                contentValues.put("ClimateActionsFoods", _ClimateActionsFoods);
                contentValues.put("ClimateActionsHome", _ClimateActionsHome);
                contentValues.put("ClimateActionsOther", _ClimateActionsOther);
                contentValues.put("ClimateActionsOtherSp", _ClimateActionsOtherSp);
                contentValues.put("ClimateRenewableEnergy", _ClimateRenewableEnergy);
                contentValues.put("ClimateObstaclesCost", _ClimateObstaclesCost);
                contentValues.put("ClimateObstaclesLackInfo", _ClimateObstaclesLackInfo);
                contentValues.put("ClimateObstaclesAccess", _ClimateObstaclesAccess);
                contentValues.put("ClimateObstaclesHabit", _ClimateObstaclesHabit);
                contentValues.put("ClimateObstaclesOther", _ClimateObstaclesOther);
                contentValues.put("ClimateObstaclesOtherSp", _ClimateObstaclesOtherSp);
                contentValues.put("ClimateNote", _ClimateNote);
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
                contentValues.put("ClimateSL", _ClimateSL);
                contentValues.put("ClimateVDate", _ClimateVDate);
                contentValues.put("ClimateVStatus", _ClimateVStatus);
                contentValues.put("ClimateVStatusOth", _ClimateVStatusOth);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("ClimateChangesNoticed", _ClimateChangesNoticed);
                contentValues.put("ClimateChangesTemp", _ClimateChangesTemp);
                contentValues.put("ClimateChangesSeason", _ClimateChangesSeason);
                contentValues.put("ClimateChangesWeather", _ClimateChangesWeather);
                contentValues.put("ClimateChangesFlora", _ClimateChangesFlora);
                contentValues.put("ClimateChangesOther", _ClimateChangesOther);
                contentValues.put("ClimateChangesOtherSp", _ClimateChangesOtherSp);
                contentValues.put("ClimateAffectDailyLife", _ClimateAffectDailyLife);
                contentValues.put("ClimateFlood", _ClimateFlood);
                contentValues.put("ClimateFloodHouse", _ClimateFloodHouse);
                contentValues.put("ClimateFloodWall", _ClimateFloodWall);
                contentValues.put("ClimateFloodMember", _ClimateFloodMember);
                contentValues.put("ClimateFloodLivestock", _ClimateFloodLivestock);
                contentValues.put("ClimateFloodJobLess", _ClimateFloodJobLess);
                contentValues.put("ClimateFloodEnergy", _ClimateFloodEnergy);
                contentValues.put("ClimateFloodToilets", _ClimateFloodToilets);
                contentValues.put("ClimateFloodHealth", _ClimateFloodHealth);
                contentValues.put("ClimateFloodMigration", _ClimateFloodMigration);
                contentValues.put("ClimateFloodOther", _ClimateFloodOther);
                contentValues.put("ClimateFloodOtherSp", _ClimateFloodOtherSp);
                contentValues.put("ClimateHighHeat", _ClimateHighHeat);
                contentValues.put("ClimateHighHeatMemberDth", _ClimateHighHeatMemberDth);
                contentValues.put("ClimateHighHeatHealth", _ClimateHighHeatHealth);
                contentValues.put("ClimateHighHeatMemberIll", _ClimateHighHeatMemberIll);
                contentValues.put("ClimateHighHeatPetDth", _ClimateHighHeatPetDth);
                contentValues.put("ClimateHighHeatOther", _ClimateHighHeatOther);
                contentValues.put("ClimateHighHeatOtherSp", _ClimateHighHeatOtherSp);
                contentValues.put("ClimateHealthProblemSleep", _ClimateHealthProblemSleep);
                contentValues.put("ClimateHealthProblemDizzy", _ClimateHealthProblemDizzy);
                contentValues.put("ClimateHealthProblemLowBlood", _ClimateHealthProblemLowBlood);
                contentValues.put("ClimateHealthProblemHighBlood", _ClimateHealthProblemHighBlood);
                contentValues.put("ClimateHealthProblemStroke", _ClimateHealthProblemStroke);
                contentValues.put("ClimateHealthProblemFever", _ClimateHealthProblemFever);
                contentValues.put("ClimateHealthProblemOther", _ClimateHealthProblemOther);
                contentValues.put("ClimateHealthProblemOtherSp", _ClimateHealthProblemOtherSp);
                contentValues.put("ClimateHealthProblemPeriod", _ClimateHealthProblemPeriod);
                contentValues.put("ClimateIllnessMalaria", _ClimateIllnessMalaria);
                contentValues.put("ClimateIllnessPneumonia", _ClimateIllnessPneumonia);
                contentValues.put("ClimateIllnessMeasles", _ClimateIllnessMeasles);
                contentValues.put("ClimateIllnessPertussis", _ClimateIllnessPertussis);
                contentValues.put("ClimateIllnessDiarrhea", _ClimateIllnessDiarrhea);
                contentValues.put("ClimateIllnessFood", _ClimateIllnessFood);
                contentValues.put("ClimateIllnessTyphoid", _ClimateIllnessTyphoid);
                contentValues.put("ClimateIllnessCough", _ClimateIllnessCough);
                contentValues.put("ClimateIllnessCholera", _ClimateIllnessCholera);
                contentValues.put("ClimateIllnessOther", _ClimateIllnessOther);
                contentValues.put("ClimateIllnessOtherSp", _ClimateIllnessOtherSp);
                contentValues.put("ClimateIllnessPeriod", _ClimateIllnessPeriod);
                contentValues.put("ClimateHeard", _ClimateHeard);
                contentValues.put("ClimateInfoMedia", _ClimateInfoMedia);
                contentValues.put("ClimateInfoInternet", _ClimateInfoInternet);
                contentValues.put("ClimateInfoEducation", _ClimateInfoEducation);
                contentValues.put("ClimateInfoFnF", _ClimateInfoFnF);
                contentValues.put("ClimateInfoOther", _ClimateInfoOther);
                contentValues.put("ClimateInfoOtherSp", _ClimateInfoOtherSp);
                contentValues.put("ClimateKnRate", _ClimateKnRate);
                contentValues.put("Climatethreat", _Climatethreat);
                contentValues.put("ClimateWorried", _ClimateWorried);
                contentValues.put("ClimateConcernTemp", _ClimateConcernTemp);
                contentValues.put("ClimateConcernEvent", _ClimateConcernEvent);
                contentValues.put("ClimateConcernPolarIce", _ClimateConcernPolarIce);
                contentValues.put("ClimateConcernSeaLevel", _ClimateConcernSeaLevel);
                contentValues.put("ClimateConcernBiodiversity", _ClimateConcernBiodiversity);
                contentValues.put("ClimateConcernDrying", _ClimateConcernDrying);
                contentValues.put("ClimateConcernRising", _ClimateConcernRising);
                contentValues.put("ClimateConcernDrought", _ClimateConcernDrought);
                contentValues.put("ClimateConcernDesert", _ClimateConcernDesert);
                contentValues.put("ClimateConcernErosin", _ClimateConcernErosin);
                contentValues.put("ClimateConcernOther", _ClimateConcernOther);
                contentValues.put("ClimateConcernOtherSp", _ClimateConcernOtherSp);
                contentValues.put("ClimateComDoing", _ClimateComDoing);
                contentValues.put("ClimateReduceImpact", _ClimateReduceImpact);
                contentValues.put("ClimateActionsPlastics", _ClimateActionsPlastics);
                contentValues.put("ClimateActionsTransport", _ClimateActionsTransport);
                contentValues.put("ClimateActionsFoods", _ClimateActionsFoods);
                contentValues.put("ClimateActionsHome", _ClimateActionsHome);
                contentValues.put("ClimateActionsOther", _ClimateActionsOther);
                contentValues.put("ClimateActionsOtherSp", _ClimateActionsOtherSp);
                contentValues.put("ClimateRenewableEnergy", _ClimateRenewableEnergy);
                contentValues.put("ClimateObstaclesCost", _ClimateObstaclesCost);
                contentValues.put("ClimateObstaclesLackInfo", _ClimateObstaclesLackInfo);
                contentValues.put("ClimateObstaclesAccess", _ClimateObstaclesAccess);
                contentValues.put("ClimateObstaclesHabit", _ClimateObstaclesHabit);
                contentValues.put("ClimateObstaclesOther", _ClimateObstaclesOther);
                contentValues.put("ClimateObstaclesOtherSp", _ClimateObstaclesOtherSp);
                contentValues.put("ClimateNote", _ClimateNote);
                contentValues.put("Upload", _Upload);
                contentValues.put("modifyDate", _modifyDate);
                C.UpdateData(TableName, "HHID,ClimateSL", (""+ _HHID +", "+ _ClimateSL +""), contentValues);

//                 manageAudit(context,this,AuditTrial.KEY_UPDATE);
             }
             catch(Exception  e)
             {
                response = e.getMessage();
             }
          return response;
       }

         int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
       public List<tmpClimate_Change_DataModel> SelectAll(Context context, String SQL)
       {
           Connection C = new Connection(context);
           List<tmpClimate_Change_DataModel> data = new ArrayList<tmpClimate_Change_DataModel>();
           tmpClimate_Change_DataModel d = new tmpClimate_Change_DataModel();
           Cursor cur = C.ReadData(SQL);

           cur.moveToFirst();
           while(!cur.isAfterLast())
           {
               Count += 1;
               d = new tmpClimate_Change_DataModel();
               d._Count = Count;
               d._HHID = cur.getString(cur.getColumnIndex("HHID"));
               d._ClimateSL = cur.getString(cur.getColumnIndex("ClimateSL"));
               d._ClimateVDate = cur.getString(cur.getColumnIndex("ClimateVDate"));
               d._ClimateVStatus = cur.getString(cur.getColumnIndex("ClimateVStatus"));
               d._ClimateVStatusOth = cur.getString(cur.getColumnIndex("ClimateVStatusOth"));
               d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
               d._ClimateChangesNoticed = cur.getString(cur.getColumnIndex("ClimateChangesNoticed"));
               d._ClimateChangesTemp = cur.getString(cur.getColumnIndex("ClimateChangesTemp"));
               d._ClimateChangesSeason = cur.getString(cur.getColumnIndex("ClimateChangesSeason"));
               d._ClimateChangesWeather = cur.getString(cur.getColumnIndex("ClimateChangesWeather"));
               d._ClimateChangesFlora = cur.getString(cur.getColumnIndex("ClimateChangesFlora"));
               d._ClimateChangesOther = cur.getString(cur.getColumnIndex("ClimateChangesOther"));
               d._ClimateChangesOtherSp = cur.getString(cur.getColumnIndex("ClimateChangesOtherSp"));
               d._ClimateAffectDailyLife = cur.getString(cur.getColumnIndex("ClimateAffectDailyLife"));
               d._ClimateFlood = cur.getString(cur.getColumnIndex("ClimateFlood"));
               d._ClimateFloodHouse = cur.getString(cur.getColumnIndex("ClimateFloodHouse"));
               d._ClimateFloodWall = cur.getString(cur.getColumnIndex("ClimateFloodWall"));
               d._ClimateFloodMember = cur.getString(cur.getColumnIndex("ClimateFloodMember"));
               d._ClimateFloodLivestock = cur.getString(cur.getColumnIndex("ClimateFloodLivestock"));
               d._ClimateFloodJobLess = cur.getString(cur.getColumnIndex("ClimateFloodJobLess"));
               d._ClimateFloodEnergy = cur.getString(cur.getColumnIndex("ClimateFloodEnergy"));
               d._ClimateFloodToilets = cur.getString(cur.getColumnIndex("ClimateFloodToilets"));
               d._ClimateFloodHealth = cur.getString(cur.getColumnIndex("ClimateFloodHealth"));
               d._ClimateFloodMigration = cur.getString(cur.getColumnIndex("ClimateFloodMigration"));
               d._ClimateFloodOther = cur.getString(cur.getColumnIndex("ClimateFloodOther"));
               d._ClimateFloodOtherSp = cur.getString(cur.getColumnIndex("ClimateFloodOtherSp"));
               d._ClimateHighHeat = cur.getString(cur.getColumnIndex("ClimateHighHeat"));
               d._ClimateHighHeatMemberDth = cur.getString(cur.getColumnIndex("ClimateHighHeatMemberDth"));
               d._ClimateHighHeatHealth = cur.getString(cur.getColumnIndex("ClimateHighHeatHealth"));
               d._ClimateHighHeatMemberIll = cur.getString(cur.getColumnIndex("ClimateHighHeatMemberIll"));
               d._ClimateHighHeatPetDth = cur.getString(cur.getColumnIndex("ClimateHighHeatPetDth"));
               d._ClimateHighHeatOther = cur.getString(cur.getColumnIndex("ClimateHighHeatOther"));
               d._ClimateHighHeatOtherSp = cur.getString(cur.getColumnIndex("ClimateHighHeatOtherSp"));
               d._ClimateHealthProblemSleep = cur.getString(cur.getColumnIndex("ClimateHealthProblemSleep"));
               d._ClimateHealthProblemDizzy = cur.getString(cur.getColumnIndex("ClimateHealthProblemDizzy"));
               d._ClimateHealthProblemLowBlood = cur.getString(cur.getColumnIndex("ClimateHealthProblemLowBlood"));
               d._ClimateHealthProblemHighBlood = cur.getString(cur.getColumnIndex("ClimateHealthProblemHighBlood"));
               d._ClimateHealthProblemStroke = cur.getString(cur.getColumnIndex("ClimateHealthProblemStroke"));
               d._ClimateHealthProblemFever = cur.getString(cur.getColumnIndex("ClimateHealthProblemFever"));
               d._ClimateHealthProblemOther = cur.getString(cur.getColumnIndex("ClimateHealthProblemOther"));
               d._ClimateHealthProblemOtherSp = cur.getString(cur.getColumnIndex("ClimateHealthProblemOtherSp"));
               d._ClimateHealthProblemPeriod = cur.getString(cur.getColumnIndex("ClimateHealthProblemPeriod"));
               d._ClimateIllnessMalaria = cur.getString(cur.getColumnIndex("ClimateIllnessMalaria"));
               d._ClimateIllnessPneumonia = cur.getString(cur.getColumnIndex("ClimateIllnessPneumonia"));
               d._ClimateIllnessMeasles = cur.getString(cur.getColumnIndex("ClimateIllnessMeasles"));
               d._ClimateIllnessPertussis = cur.getString(cur.getColumnIndex("ClimateIllnessPertussis"));
               d._ClimateIllnessDiarrhea = cur.getString(cur.getColumnIndex("ClimateIllnessDiarrhea"));
               d._ClimateIllnessFood = cur.getString(cur.getColumnIndex("ClimateIllnessFood"));
               d._ClimateIllnessTyphoid = cur.getString(cur.getColumnIndex("ClimateIllnessTyphoid"));
               d._ClimateIllnessCough = cur.getString(cur.getColumnIndex("ClimateIllnessCough"));
               d._ClimateIllnessCholera = cur.getString(cur.getColumnIndex("ClimateIllnessCholera"));
               d._ClimateIllnessOther = cur.getString(cur.getColumnIndex("ClimateIllnessOther"));
               d._ClimateIllnessOtherSp = cur.getString(cur.getColumnIndex("ClimateIllnessOtherSp"));
               d._ClimateIllnessPeriod = cur.getString(cur.getColumnIndex("ClimateIllnessPeriod"));
               d._ClimateHeard = cur.getString(cur.getColumnIndex("ClimateHeard"));
               d._ClimateInfoMedia = cur.getString(cur.getColumnIndex("ClimateInfoMedia"));
               d._ClimateInfoInternet = cur.getString(cur.getColumnIndex("ClimateInfoInternet"));
               d._ClimateInfoEducation = cur.getString(cur.getColumnIndex("ClimateInfoEducation"));
               d._ClimateInfoFnF = cur.getString(cur.getColumnIndex("ClimateInfoFnF"));
               d._ClimateInfoOther = cur.getString(cur.getColumnIndex("ClimateInfoOther"));
               d._ClimateInfoOtherSp = cur.getString(cur.getColumnIndex("ClimateInfoOtherSp"));
               d._ClimateKnRate = cur.getString(cur.getColumnIndex("ClimateKnRate"));
               d._Climatethreat = cur.getString(cur.getColumnIndex("Climatethreat"));
               d._ClimateWorried = cur.getString(cur.getColumnIndex("ClimateWorried"));
               d._ClimateConcernTemp = cur.getString(cur.getColumnIndex("ClimateConcernTemp"));
               d._ClimateConcernEvent = cur.getString(cur.getColumnIndex("ClimateConcernEvent"));
               d._ClimateConcernPolarIce = cur.getString(cur.getColumnIndex("ClimateConcernPolarIce"));
               d._ClimateConcernSeaLevel = cur.getString(cur.getColumnIndex("ClimateConcernSeaLevel"));
               d._ClimateConcernBiodiversity = cur.getString(cur.getColumnIndex("ClimateConcernBiodiversity"));
               d._ClimateConcernDrying = cur.getString(cur.getColumnIndex("ClimateConcernDrying"));
               d._ClimateConcernRising = cur.getString(cur.getColumnIndex("ClimateConcernRising"));
               d._ClimateConcernDrought = cur.getString(cur.getColumnIndex("ClimateConcernDrought"));
               d._ClimateConcernDesert = cur.getString(cur.getColumnIndex("ClimateConcernDesert"));
               d._ClimateConcernErosin = cur.getString(cur.getColumnIndex("ClimateConcernErosin"));
               d._ClimateConcernOther = cur.getString(cur.getColumnIndex("ClimateConcernOther"));
               d._ClimateConcernOtherSp = cur.getString(cur.getColumnIndex("ClimateConcernOtherSp"));
               d._ClimateComDoing = cur.getString(cur.getColumnIndex("ClimateComDoing"));
               d._ClimateReduceImpact = cur.getString(cur.getColumnIndex("ClimateReduceImpact"));
               d._ClimateActionsPlastics = cur.getString(cur.getColumnIndex("ClimateActionsPlastics"));
               d._ClimateActionsTransport = cur.getString(cur.getColumnIndex("ClimateActionsTransport"));
               d._ClimateActionsFoods = cur.getString(cur.getColumnIndex("ClimateActionsFoods"));
               d._ClimateActionsHome = cur.getString(cur.getColumnIndex("ClimateActionsHome"));
               d._ClimateActionsOther = cur.getString(cur.getColumnIndex("ClimateActionsOther"));
               d._ClimateActionsOtherSp = cur.getString(cur.getColumnIndex("ClimateActionsOtherSp"));
               d._ClimateRenewableEnergy = cur.getString(cur.getColumnIndex("ClimateRenewableEnergy"));
               d._ClimateObstaclesCost = cur.getString(cur.getColumnIndex("ClimateObstaclesCost"));
               d._ClimateObstaclesLackInfo = cur.getString(cur.getColumnIndex("ClimateObstaclesLackInfo"));
               d._ClimateObstaclesAccess = cur.getString(cur.getColumnIndex("ClimateObstaclesAccess"));
               d._ClimateObstaclesHabit = cur.getString(cur.getColumnIndex("ClimateObstaclesHabit"));
               d._ClimateObstaclesOther = cur.getString(cur.getColumnIndex("ClimateObstaclesOther"));
               d._ClimateObstaclesOtherSp = cur.getString(cur.getColumnIndex("ClimateObstaclesOtherSp"));
               d._ClimateNote = cur.getString(cur.getColumnIndex("ClimateNote"));
               data.add(d);

//                manageAudit(context,d,AuditTrial.KEY_SELECT);

               cur.moveToNext();
           }
           cur.close();
         return data;
       }


//
//        static Map<String, Object> firstStateMap;
//        public void manageAudit(Context context, Climate_Change_DataModel ob, String key) {
//            if (key.equalsIgnoreCase(AuditTrial.KEY_SELECT)) {
//                //store old state
//                firstStateMap = getMapData(ob);
//            } else if (key.equalsIgnoreCase(AuditTrial.KEY_UPDATE)) {
//                //store new state
//                Map<String, Object> secondStateMap = getMapData(ob);
//                AuditTrial auditTrial = new AuditTrial(context,TableName);
//                // run audit
//                if (firstStateMap != null && !firstStateMap.isEmpty() && secondStateMap != null && !secondStateMap.isEmpty()) {
//                    auditTrial.audit(firstStateMap, secondStateMap);
//                }
//            }
//        }
//
//
//
//        /**
//        * get object state
//        * @param ob
//        * @return
//        */
//        public Map<String, Object> getMapData(Climate_Change_DataModel ob) {
//            Map<String, Object> data = new HashMap<>();
//
//            if (ob != null) {
//                 data.put("HHID", ob._HHID);
//                 data.put("ClimateSL", ob._ClimateSL);
//                 data.put("ClimateVDate", ob._ClimateVDate);
//                 data.put("ClimateVStatus", ob._ClimateVStatus);
//                 data.put("ClimateVStatusOth", ob._ClimateVStatusOth);
//                 data.put("Rnd", ob._Rnd);
//                 data.put("ClimateChangesNoticed", ob._ClimateChangesNoticed);
//                 data.put("ClimateChangesTemp", ob._ClimateChangesTemp);
//                 data.put("ClimateChangesSeason", ob._ClimateChangesSeason);
//                 data.put("ClimateChangesWeather", ob._ClimateChangesWeather);
//                 data.put("ClimateChangesFlora", ob._ClimateChangesFlora);
//                 data.put("ClimateChangesOther", ob._ClimateChangesOther);
//                 data.put("ClimateChangesOtherSp", ob._ClimateChangesOtherSp);
//                 data.put("ClimateAffectDailyLife", ob._ClimateAffectDailyLife);
//                 data.put("ClimateFlood", ob._ClimateFlood);
//                 data.put("ClimateFloodHouse", ob._ClimateFloodHouse);
//                 data.put("ClimateFloodWall", ob._ClimateFloodWall);
//                 data.put("ClimateFloodMember", ob._ClimateFloodMember);
//                 data.put("ClimateFloodLivestock", ob._ClimateFloodLivestock);
//                 data.put("ClimateFloodJobLess", ob._ClimateFloodJobLess);
//                 data.put("ClimateFloodEnergy", ob._ClimateFloodEnergy);
//                 data.put("ClimateFloodToilets", ob._ClimateFloodToilets);
//                 data.put("ClimateFloodHealth", ob._ClimateFloodHealth);
//                 data.put("ClimateFloodMigration", ob._ClimateFloodMigration);
//                 data.put("ClimateFloodOther", ob._ClimateFloodOther);
//                 data.put("ClimateFloodOtherSp", ob._ClimateFloodOtherSp);
//                 data.put("ClimateHighHeat", ob._ClimateHighHeat);
//                 data.put("ClimateHighHeatMemberDth", ob._ClimateHighHeatMemberDth);
//                 data.put("ClimateHighHeatHealth", ob._ClimateHighHeatHealth);
//                 data.put("ClimateHighHeatMemberIll", ob._ClimateHighHeatMemberIll);
//                 data.put("ClimateHighHeatPetDth", ob._ClimateHighHeatPetDth);
//                 data.put("ClimateHighHeatOther", ob._ClimateHighHeatOther);
//                 data.put("ClimateHighHeatOtherSp", ob._ClimateHighHeatOtherSp);
//                 data.put("ClimateHealthProblemSleep", ob._ClimateHealthProblemSleep);
//                 data.put("ClimateHealthProblemDizzy", ob._ClimateHealthProblemDizzy);
//                 data.put("ClimateHealthProblemLowBlood", ob._ClimateHealthProblemLowBlood);
//                 data.put("ClimateHealthProblemHighBlood", ob._ClimateHealthProblemHighBlood);
//                 data.put("ClimateHealthProblemStroke", ob._ClimateHealthProblemStroke);
//                 data.put("ClimateHealthProblemFever", ob._ClimateHealthProblemFever);
//                 data.put("ClimateHealthProblemOther", ob._ClimateHealthProblemOther);
//                 data.put("ClimateHealthProblemOtherSp", ob._ClimateHealthProblemOtherSp);
//                 data.put("ClimateHealthProblemPeriod", ob._ClimateHealthProblemPeriod);
//                 data.put("ClimateIllnessMalaria", ob._ClimateIllnessMalaria);
//                 data.put("ClimateIllnessPneumonia", ob._ClimateIllnessPneumonia);
//                 data.put("ClimateIllnessMeasles", ob._ClimateIllnessMeasles);
//                 data.put("ClimateIllnessPertussis", ob._ClimateIllnessPertussis);
//                 data.put("ClimateIllnessDiarrhea", ob._ClimateIllnessDiarrhea);
//                 data.put("ClimateIllnessFood", ob._ClimateIllnessFood);
//                 data.put("ClimateIllnessTyphoid", ob._ClimateIllnessTyphoid);
//                 data.put("ClimateIllnessCough", ob._ClimateIllnessCough);
//                 data.put("ClimateIllnessCholera", ob._ClimateIllnessCholera);
//                 data.put("ClimateIllnessOther", ob._ClimateIllnessOther);
//                 data.put("ClimateIllnessOtherSp", ob._ClimateIllnessOtherSp);
//                 data.put("ClimateIllnessPeriod", ob._ClimateIllnessPeriod);
//                 data.put("ClimateHeard", ob._ClimateHeard);
//                 data.put("ClimateInfoMedia", ob._ClimateInfoMedia);
//                 data.put("ClimateInfoInternet", ob._ClimateInfoInternet);
//                 data.put("ClimateInfoEducation", ob._ClimateInfoEducation);
//                 data.put("ClimateInfoFnF", ob._ClimateInfoFnF);
//                 data.put("ClimateInfoOther", ob._ClimateInfoOther);
//                 data.put("ClimateInfoOtherSp", ob._ClimateInfoOtherSp);
//                 data.put("ClimateKnRate", ob._ClimateKnRate);
//                 data.put("Climatethreat", ob._Climatethreat);
//                 data.put("ClimateWorried", ob._ClimateWorried);
//                 data.put("ClimateConcernTemp", ob._ClimateConcernTemp);
//                 data.put("ClimateConcernEvent", ob._ClimateConcernEvent);
//                 data.put("ClimateConcernPolarIce", ob._ClimateConcernPolarIce);
//                 data.put("ClimateConcernSeaLevel", ob._ClimateConcernSeaLevel);
//                 data.put("ClimateConcernBiodiversity", ob._ClimateConcernBiodiversity);
//                 data.put("ClimateConcernDrying", ob._ClimateConcernDrying);
//                 data.put("ClimateConcernRising", ob._ClimateConcernRising);
//                 data.put("ClimateConcernDrought", ob._ClimateConcernDrought);
//                 data.put("ClimateConcernDesert", ob._ClimateConcernDesert);
//                 data.put("ClimateConcernErosin", ob._ClimateConcernErosin);
//                 data.put("ClimateConcernOther", ob._ClimateConcernOther);
//                 data.put("ClimateConcernOtherSp", ob._ClimateConcernOtherSp);
//                 data.put("ClimateComDoing", ob._ClimateComDoing);
//                 data.put("ClimateReduceImpact", ob._ClimateReduceImpact);
//                 data.put("ClimateActionsPlastics", ob._ClimateActionsPlastics);
//                 data.put("ClimateActionsTransport", ob._ClimateActionsTransport);
//                 data.put("ClimateActionsFoods", ob._ClimateActionsFoods);
//                 data.put("ClimateActionsHome", ob._ClimateActionsHome);
//                 data.put("ClimateActionsOther", ob._ClimateActionsOther);
//                 data.put("ClimateActionsOtherSp", ob._ClimateActionsOtherSp);
//                 data.put("ClimateRenewableEnergy", ob._ClimateRenewableEnergy);
//                 data.put("ClimateObstaclesCost", ob._ClimateObstaclesCost);
//                 data.put("ClimateObstaclesAccess", ob._ClimateObstaclesAccess);
//                 data.put("ClimateObstaclesHabit", ob._ClimateObstaclesHabit);
//                 data.put("ClimateObstaclesOther", ob._ClimateObstaclesOther);
//                 data.put("ClimateObstaclesOtherSp", ob._ClimateObstaclesOtherSp);
//                 data.put("ClimateNote", ob._ClimateNote);
//
//            }
//            return data;
//        }
}