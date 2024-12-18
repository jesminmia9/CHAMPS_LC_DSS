package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class tmpDeath_DataModel {

    private String _DeathID = "";

    public String getDeathID() {
        return _DeathID;
    }

    public void setDeathID(String newValue) {
        _DeathID = newValue;
    }

    private String _HHID = "";

    public String getHHID() {
        return _HHID;
    }

    public void setHHID(String newValue) {
        _HHID = newValue;
    }

    private String _MemID = "";

    public String getMemID() {
        return _MemID;
    }

    public void setMemID(String newValue) {
        _MemID = newValue;
    }

    private String _DthDate = "";

    public String getDthDate() {
        return _DthDate;
    }

    public void setDthDate(String newValue) {
        _DthDate = newValue;
    }

    private String _DthDateType = "";

    public String getDthDateType() {
        return _DthDateType;
    }

    public void setDthDateType(String newValue) {
        _DthDateType = newValue;
    }

    private String _DthTime = "";

    public String getDthTime() {
        return _DthTime;
    }

    public void setDthTime(String newValue) {
        _DthTime = newValue;
    }

    private String _DthTimeType = "";

    public String getDthTimeType() {
        return _DthTimeType;
    }

    public void setDthTimeType(String newValue) {
        _DthTimeType = newValue;
    }

    private String _DthPlace = "";

    public String getDthPlace() {
        return _DthPlace;
    }

    public void setDthPlace(String newValue) {
        _DthPlace = newValue;
    }

    private String _DthPlaceOth = "";

    public String getDthPlaceOth() {
        return _DthPlaceOth;
    }

    public void setDthPlaceOth(String newValue) {
        _DthPlaceOth = newValue;
    }


    private String _DthPlaceFacility = "";
    public String getDthPlaceFacility(){
        return _DthPlaceFacility;
    }
    public void setDthPlaceFacility(String newValue){
        _DthPlaceFacility = newValue;
    }

    private String _DthAdrsState = "";
    public String getDthAdrsState(){
        return _DthAdrsState;
    }
    public void setDthAdrsState(String newValue){
        _DthAdrsState = newValue;
    }
    private String _DthAdrsAL1 = "";
    public String getDthAdrsAL1(){
        return _DthAdrsAL1;
    }
    public void setDthAdrsAL1(String newValue){
        _DthAdrsAL1 = newValue;
    }
    private String _DthAdrsAL2 = "";
    public String getDthAdrsAL2(){
        return _DthAdrsAL2;
    }
    public void setDthAdrsAL2(String newValue){
        _DthAdrsAL2 = newValue;
    }
    private String _DthAdrsAL3 = "";
    public String getDthAdrsAL3(){
        return _DthAdrsAL3;
    }
    public void setDthAdrsAL3(String newValue){
        _DthAdrsAL3 = newValue;
    }
    private String _DthAdrsAL4 = "";
    public String getDthAdrsAL4(){
        return _DthAdrsAL4;
    }
    public void setDthAdrsAL4(String newValue){
        _DthAdrsAL4 = newValue;
    }
    private String _DthAdrsAL5 = "";
    public String getDthAdrsAL5(){
        return _DthAdrsAL5;
    }
    public void setDthAdrsAL5(String newValue){
        _DthAdrsAL5 = newValue;
    }
    private String _DthSick = "";
    public String getDthSick(){
        return _DthSick;
    }
    public void setDthSick(String newValue){
        _DthSick = newValue;
    }
    private String _DthCertificate = "";
    public String getDthCertificate(){
        return _DthCertificate;
    }
    public void setDthCertificate(String newValue){
        _DthCertificate = newValue;
    }
    private String _chkDthCauseA = "";
    public String getchkDthCauseA(){
        return _chkDthCauseA;
    }
    public void setchkDthCauseA(String newValue){
        _chkDthCauseA = newValue;
    }
    private String _chkDthCauseB = "";
    public String getchkDthCauseB(){
        return _chkDthCauseB;
    }
    public void setchkDthCauseB(String newValue){
        _chkDthCauseB = newValue;
    }
    private String _chkDthCauseC = "";
    public String getchkDthCauseC(){
        return _chkDthCauseC;
    }
    public void setchkDthCauseC(String newValue){
        _chkDthCauseC = newValue;
    }
    private String _chkDthCauseD = "";
    public String getchkDthCauseD(){
        return _chkDthCauseD;
    }
    public void setchkDthCauseD(String newValue){
        _chkDthCauseD = newValue;
    }
    private String _chkDthCauseE = "";
    public String getchkDthCauseE(){
        return _chkDthCauseE;
    }
    public void setchkDthCauseE(String newValue){
        _chkDthCauseE = newValue;
    }
    private String _chkDthCauseF = "";
    public String getchkDthCauseF(){
        return _chkDthCauseF;
    }
    public void setchkDthCauseF(String newValue){
        _chkDthCauseF = newValue;
    }
    private String _chkDthCauseG = "";
    public String getchkDthCauseG(){
        return _chkDthCauseG;
    }
    public void setchkDthCauseG(String newValue){
        _chkDthCauseG = newValue;
    }
    private String _chkDthCauseH = "";
    public String getchkDthCauseH(){
        return _chkDthCauseH;
    }
    public void setchkDthCauseH(String newValue){
        _chkDthCauseH = newValue;
    }
    private String _chkDthCauseI = "";
    public String getchkDthCauseI(){
        return _chkDthCauseI;
    }
    public void setchkDthCauseI(String newValue){
        _chkDthCauseI = newValue;
    }
    private String _chkDthCauseJ = "";
    public String getchkDthCauseJ(){
        return _chkDthCauseJ;
    }
    public void setchkDthCauseJ(String newValue){
        _chkDthCauseJ = newValue;
    }
    private String _chkDthCauseK = "";
    public String getchkDthCauseK(){
        return _chkDthCauseK;
    }
    public void setchkDthCauseK(String newValue){
        _chkDthCauseK = newValue;
    }
    private String _chkDthCauseL = "";
    public String getchkDthCauseL(){
        return _chkDthCauseL;
    }
    public void setchkDthCauseL(String newValue){
        _chkDthCauseL = newValue;
    }
    private String _chkDthCauseM = "";
    public String getchkDthCauseM(){
        return _chkDthCauseM;
    }
    public void setchkDthCauseM(String newValue){
        _chkDthCauseM = newValue;
    }
    private String _chkDthCauseN = "";
    public String getchkDthCauseN(){
        return _chkDthCauseN;
    }
    public void setchkDthCauseN(String newValue){
        _chkDthCauseN = newValue;
    }
    private String _chkDthCauseO = "";
    public String getchkDthCauseO(){
        return _chkDthCauseO;
    }
    public void setchkDthCauseO(String newValue){
        _chkDthCauseO = newValue;
    }
    private String _chkDthCauseP = "";
    public String getchkDthCauseP(){
        return _chkDthCauseP;
    }
    public void setchkDthCauseP(String newValue){
        _chkDthCauseP = newValue;
    }
    private String _chkDthCauseQ = "";
    public String getchkDthCauseQ(){
        return _chkDthCauseQ;
    }
    public void setchkDthCauseQ(String newValue){
        _chkDthCauseQ = newValue;
    }
    private String _chkDthCauseR = "";
    public String getchkDthCauseR(){
        return _chkDthCauseR;
    }
    public void setchkDthCauseR(String newValue){
        _chkDthCauseR = newValue;
    }
    private String _chkDthCauseS = "";
    public String getchkDthCauseS(){
        return _chkDthCauseS;
    }
    public void setchkDthCauseS(String newValue){
        _chkDthCauseS = newValue;
    }
    private String _chkDthCauseT = "";
    public String getchkDthCauseT(){
        return _chkDthCauseT;
    }
    public void setchkDthCauseT(String newValue){
        _chkDthCauseT = newValue;
    }
    private String _chkDthCauseX = "";
    public String getchkDthCauseX(){
        return _chkDthCauseX;
    }
    public void setchkDthCauseX(String newValue){
        _chkDthCauseX = newValue;
    }
    private String _chkDthCauseY = "";
    public String getchkDthCauseY(){
        return _chkDthCauseY;
    }
    public void setchkDthCauseY(String newValue){
        _chkDthCauseY = newValue;
    }
    private String _chkDthCauseZ = "";
    public String getchkDthCauseZ(){
        return _chkDthCauseZ;
    }
    public void setchkDthCauseZ(String newValue){
        _chkDthCauseZ = newValue;
    }
    private String _DthCauseOth = "";
    public String getDthCauseOth(){
        return _DthCauseOth;
    }
    public void setDthCauseOth(String newValue){
        _DthCauseOth = newValue;
    }
    private String _DthHCProf = "";
    public String getDthHCProf(){
        return _DthHCProf;
    }
    public void setDthHCProf(String newValue){
        _DthHCProf = newValue;
    }
    private String _chkDthCauseProfA = "";
    public String getchkDthCauseProfA(){
        return _chkDthCauseProfA;
    }
    public void setchkDthCauseProfA(String newValue){
        _chkDthCauseProfA = newValue;
    }
    private String _chkDthCauseProfB = "";
    public String getchkDthCauseProfB(){
        return _chkDthCauseProfB;
    }
    public void setchkDthCauseProfB(String newValue){
        _chkDthCauseProfB = newValue;
    }
    private String _chkDthCauseProfC = "";
    public String getchkDthCauseProfC(){
        return _chkDthCauseProfC;
    }
    public void setchkDthCauseProfC(String newValue){
        _chkDthCauseProfC = newValue;
    }
    private String _chkDthCauseProfD = "";
    public String getchkDthCauseProfD(){
        return _chkDthCauseProfD;
    }
    public void setchkDthCauseProfD(String newValue){
        _chkDthCauseProfD = newValue;
    }
    private String _chkDthCauseProfE = "";
    public String getchkDthCauseProfE(){
        return _chkDthCauseProfE;
    }
    public void setchkDthCauseProfE(String newValue){
        _chkDthCauseProfE = newValue;
    }
    private String _chkDthCauseProfF = "";
    public String getchkDthCauseProfF(){
        return _chkDthCauseProfF;
    }
    public void setchkDthCauseProfF(String newValue){
        _chkDthCauseProfF = newValue;
    }
    private String _chkDthCauseProfG = "";
    public String getchkDthCauseProfG(){
        return _chkDthCauseProfG;
    }
    public void setchkDthCauseProfG(String newValue){
        _chkDthCauseProfG = newValue;
    }
    private String _chkDthCauseProfH = "";
    public String getchkDthCauseProfH(){
        return _chkDthCauseProfH;
    }
    public void setchkDthCauseProfH(String newValue){
        _chkDthCauseProfH = newValue;
    }
    private String _chkDthCauseProfI = "";
    public String getchkDthCauseProfI(){
        return _chkDthCauseProfI;
    }
    public void setchkDthCauseProfI(String newValue){
        _chkDthCauseProfI = newValue;
    }
    private String _chkDthCauseProfJ = "";
    public String getchkDthCauseProfJ(){
        return _chkDthCauseProfJ;
    }
    public void setchkDthCauseProfJ(String newValue){
        _chkDthCauseProfJ = newValue;
    }
    private String _chkDthCauseProfK = "";
    public String getchkDthCauseProfK(){
        return _chkDthCauseProfK;
    }
    public void setchkDthCauseProfK(String newValue){
        _chkDthCauseProfK = newValue;
    }
    private String _chkDthCauseProfL = "";
    public String getchkDthCauseProfL(){
        return _chkDthCauseProfL;
    }
    public void setchkDthCauseProfL(String newValue){
        _chkDthCauseProfL = newValue;
    }
    private String _chkDthCauseProfM = "";
    public String getchkDthCauseProfM(){
        return _chkDthCauseProfM;
    }
    public void setchkDthCauseProfM(String newValue){
        _chkDthCauseProfM = newValue;
    }
    private String _chkDthCauseProfN = "";
    public String getchkDthCauseProfN(){
        return _chkDthCauseProfN;
    }
    public void setchkDthCauseProfN(String newValue){
        _chkDthCauseProfN = newValue;
    }
    private String _chkDthCauseProfO = "";
    public String getchkDthCauseProfO(){
        return _chkDthCauseProfO;
    }
    public void setchkDthCauseProfO(String newValue){
        _chkDthCauseProfO = newValue;
    }
    private String _chkDthCauseProfP = "";
    public String getchkDthCauseProfP(){
        return _chkDthCauseProfP;
    }
    public void setchkDthCauseProfP(String newValue){
        _chkDthCauseProfP = newValue;
    }
    private String _chkDthCauseProfQ = "";
    public String getchkDthCauseProfQ(){
        return _chkDthCauseProfQ;
    }
    public void setchkDthCauseProfQ(String newValue){
        _chkDthCauseProfQ = newValue;
    }
    private String _chkDthCauseProfR = "";
    public String getchkDthCauseProfR(){
        return _chkDthCauseProfR;
    }
    public void setchkDthCauseProfR(String newValue){
        _chkDthCauseProfR = newValue;
    }
    private String _chkDthCauseProfS = "";
    public String getchkDthCauseProfS(){
        return _chkDthCauseProfS;
    }
    public void setchkDthCauseProfS(String newValue){
        _chkDthCauseProfS = newValue;
    }
    private String _chkDthCauseProfT = "";
    public String getchkDthCauseProfT(){
        return _chkDthCauseProfT;
    }
    public void setchkDthCauseProfT(String newValue){
        _chkDthCauseProfT = newValue;
    }
    private String _chkDthCauseProfX = "";
    public String getchkDthCauseProfX(){
        return _chkDthCauseProfX;
    }
    public void setchkDthCauseProfX(String newValue){
        _chkDthCauseProfX = newValue;
    }
    private String _chkDthCauseProfY = "";
    public String getchkDthCauseProfY(){
        return _chkDthCauseProfY;
    }
    public void setchkDthCauseProfY(String newValue){
        _chkDthCauseProfY = newValue;
    }
    private String _chkDthCauseProfZ = "";
    public String getchkDthCauseProfZ(){
        return _chkDthCauseProfZ;
    }
    public void setchkDthCauseProfZ(String newValue){
        _chkDthCauseProfZ = newValue;
    }
    private String _DthCauseOthProf = "";
    public String getDthCauseOthProf(){
        return _DthCauseOthProf;
    }
    public void setDthCauseOthProf(String newValue){
        _DthCauseOthProf = newValue;
    }
    private String _DthCategory = "";
    public String getDthCategory(){
        return _DthCategory;
    }
    public void setDthCategory(String newValue){
        _DthCategory = newValue;
    }
    private String _DthEnrolChamps = "";
    public String getDthEnrolChamps(){
        return _DthEnrolChamps;
    }
    public void setDthEnrolChamps(String newValue){
        _DthEnrolChamps = newValue;
    }
    private String _DthChampsIdKnown = "";
    public String getDthChampsIdKnown(){
        return _DthChampsIdKnown;
    }
    public void setDthChampsIdKnown(String newValue){
        _DthChampsIdKnown = newValue;
    }
    private String _DthChampsId = "";
    public String getDthChampsId(){
        return _DthChampsId;
    }
    public void setDthChampsId(String newValue){
        _DthChampsId = newValue;
    }



    private String _DthVDate = "";

    public String getDthVDate() {
        return _DthVDate;
    }

    public void setDthVDate(String newValue) {
        _DthVDate = newValue;
    }

    private String _Rnd = "";

    public String getRnd() {
        return _Rnd;
    }

    public void setRnd(String newValue) {
        _Rnd = newValue;
    }

    private String _DthNote = "";

    public String getDthNote() {
        return _DthNote;
    }

    public void setDthNote(String newValue) {
        _DthNote = newValue;
    }

    private String _StartTime = "";

    public void setStartTime(String newValue) {
        _StartTime = newValue;
    }

    private String _EndTime = "";

    public void setEndTime(String newValue) {
        _EndTime = newValue;
    }

    private String _DeviceID = "";

    public void setDeviceID(String newValue) {
        _DeviceID = newValue;
    }

    private String _EntryUser = "";

    public void setEntryUser(String newValue) {
        _EntryUser = newValue;
    }

    public String getDeviceID() {
        return _DeviceID;
    }

    public String getEntryUser() {
        return _EntryUser;
    }

    private int _isdelete;

    public int get_isdelete() {
        return _isdelete;
    }

    public void set_isdelete(int _isdelete) {
        this._isdelete = _isdelete;
    }

    private String _Lat = "";

    public void setLat(String newValue) {
        _Lat = newValue;
    }

    private String _Lon = "";

    public void setLon(String newValue) {
        _Lon = newValue;
    }

    private String _EnDt = Global.DateTimeNowYMDHMS();
    private int _Upload = 2;
    private String _modifyDate = Global.DateTimeNowYMDHMS();

    String TableName = "tmpDeath";

    public String SaveUpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try {
            if (C.Existence("Select * from " + TableName + "  Where DeathID='" + _DeathID + "' "))
                response = UpdateData(context);
            else
                response = SaveData(context);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return response;
    }

    Connection C;

    private String SaveData(Context context) {
        String response = "";
        C = new Connection(context);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("DeathID", _DeathID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("DthDate", _DthDate);
            contentValues.put("DthDateType", _DthDateType);
            contentValues.put("DthTime", _DthTime);
            contentValues.put("DthTimeType", _DthTimeType);
            contentValues.put("DthPlace", _DthPlace);
            contentValues.put("DthPlaceOth", _DthPlaceOth);
            contentValues.put("DthPlaceFacility", _DthPlaceFacility);
            contentValues.put("DthAdrsState", _DthAdrsState);
            contentValues.put("DthAdrsAL1", _DthAdrsAL1);
            contentValues.put("DthAdrsAL2", _DthAdrsAL2);
            contentValues.put("DthAdrsAL3", _DthAdrsAL3);
            contentValues.put("DthAdrsAL4", _DthAdrsAL4);
            contentValues.put("DthAdrsAL5", _DthAdrsAL5);
            contentValues.put("DthSick", _DthSick);
            contentValues.put("DthCertificate", _DthCertificate);
            contentValues.put("chkDthCauseA", _chkDthCauseA);
            contentValues.put("chkDthCauseB", _chkDthCauseB);
            contentValues.put("chkDthCauseC", _chkDthCauseC);
            contentValues.put("chkDthCauseD", _chkDthCauseD);
            contentValues.put("chkDthCauseE", _chkDthCauseE);
            contentValues.put("chkDthCauseF", _chkDthCauseF);
            contentValues.put("chkDthCauseG", _chkDthCauseG);
            contentValues.put("chkDthCauseH", _chkDthCauseH);
            contentValues.put("chkDthCauseI", _chkDthCauseI);
            contentValues.put("chkDthCauseJ", _chkDthCauseJ);
            contentValues.put("chkDthCauseK", _chkDthCauseK);
            contentValues.put("chkDthCauseL", _chkDthCauseL);
            contentValues.put("chkDthCauseM", _chkDthCauseM);
            contentValues.put("chkDthCauseN", _chkDthCauseN);
            contentValues.put("chkDthCauseO", _chkDthCauseO);
            contentValues.put("chkDthCauseP", _chkDthCauseP);
            contentValues.put("chkDthCauseQ", _chkDthCauseQ);
            contentValues.put("chkDthCauseR", _chkDthCauseR);
            contentValues.put("chkDthCauseS", _chkDthCauseS);
            contentValues.put("chkDthCauseT", _chkDthCauseT);
            contentValues.put("chkDthCauseX", _chkDthCauseX);
            contentValues.put("chkDthCauseY", _chkDthCauseY);
            contentValues.put("chkDthCauseZ", _chkDthCauseZ);
            contentValues.put("DthCauseOth", _DthCauseOth);
            contentValues.put("DthHCProf", _DthHCProf);
            contentValues.put("chkDthCauseProfA", _chkDthCauseProfA);
            contentValues.put("chkDthCauseProfB", _chkDthCauseProfB);
            contentValues.put("chkDthCauseProfC", _chkDthCauseProfC);
            contentValues.put("chkDthCauseProfD", _chkDthCauseProfD);
            contentValues.put("chkDthCauseProfE", _chkDthCauseProfE);
            contentValues.put("chkDthCauseProfF", _chkDthCauseProfF);
            contentValues.put("chkDthCauseProfG", _chkDthCauseProfG);
            contentValues.put("chkDthCauseProfH", _chkDthCauseProfH);
            contentValues.put("chkDthCauseProfI", _chkDthCauseProfI);
            contentValues.put("chkDthCauseProfJ", _chkDthCauseProfJ);
            contentValues.put("chkDthCauseProfK", _chkDthCauseProfK);
            contentValues.put("chkDthCauseProfL", _chkDthCauseProfL);
            contentValues.put("chkDthCauseProfM", _chkDthCauseProfM);
            contentValues.put("chkDthCauseProfN", _chkDthCauseProfN);
            contentValues.put("chkDthCauseProfO", _chkDthCauseProfO);
            contentValues.put("chkDthCauseProfP", _chkDthCauseProfP);
            contentValues.put("chkDthCauseProfQ", _chkDthCauseProfQ);
            contentValues.put("chkDthCauseProfR", _chkDthCauseProfR);
            contentValues.put("chkDthCauseProfS", _chkDthCauseProfS);
            contentValues.put("chkDthCauseProfT", _chkDthCauseProfT);
            contentValues.put("chkDthCauseProfX", _chkDthCauseProfX);
            contentValues.put("chkDthCauseProfY", _chkDthCauseProfY);
            contentValues.put("chkDthCauseProfZ", _chkDthCauseProfZ);
            contentValues.put("DthCauseOthProf", _DthCauseOthProf);
            contentValues.put("DthCategory", _DthCategory);
            contentValues.put("DthEnrolChamps", _DthEnrolChamps);
            contentValues.put("DthChampsIdKnown", _DthChampsIdKnown);
            contentValues.put("DthChampsId", _DthChampsId);
            contentValues.put("DthVDate", _DthVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("DthNote", _DthNote);
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
        } catch (Exception e) {
            response = e.getMessage();
        }
        return response;
    }

    private String UpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("DeathID", _DeathID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("DthDate", _DthDate);
            contentValues.put("DthDateType", _DthDateType);
            contentValues.put("DthTime", _DthTime);
            contentValues.put("DthTimeType", _DthTimeType);
            contentValues.put("DthPlace", _DthPlace);
            contentValues.put("DthPlaceOth", _DthPlaceOth);
            contentValues.put("DthPlaceFacility", _DthPlaceFacility);
            contentValues.put("DthAdrsState", _DthAdrsState);
            contentValues.put("DthAdrsAL1", _DthAdrsAL1);
            contentValues.put("DthAdrsAL2", _DthAdrsAL2);
            contentValues.put("DthAdrsAL3", _DthAdrsAL3);
            contentValues.put("DthAdrsAL4", _DthAdrsAL4);
            contentValues.put("DthAdrsAL5", _DthAdrsAL5);
            contentValues.put("DthSick", _DthSick);
            contentValues.put("DthCertificate", _DthCertificate);
            contentValues.put("chkDthCauseA", _chkDthCauseA);
            contentValues.put("chkDthCauseB", _chkDthCauseB);
            contentValues.put("chkDthCauseC", _chkDthCauseC);
            contentValues.put("chkDthCauseD", _chkDthCauseD);
            contentValues.put("chkDthCauseE", _chkDthCauseE);
            contentValues.put("chkDthCauseF", _chkDthCauseF);
            contentValues.put("chkDthCauseG", _chkDthCauseG);
            contentValues.put("chkDthCauseH", _chkDthCauseH);
            contentValues.put("chkDthCauseI", _chkDthCauseI);
            contentValues.put("chkDthCauseJ", _chkDthCauseJ);
            contentValues.put("chkDthCauseK", _chkDthCauseK);
            contentValues.put("chkDthCauseL", _chkDthCauseL);
            contentValues.put("chkDthCauseM", _chkDthCauseM);
            contentValues.put("chkDthCauseN", _chkDthCauseN);
            contentValues.put("chkDthCauseO", _chkDthCauseO);
            contentValues.put("chkDthCauseP", _chkDthCauseP);
            contentValues.put("chkDthCauseQ", _chkDthCauseQ);
            contentValues.put("chkDthCauseR", _chkDthCauseR);
            contentValues.put("chkDthCauseS", _chkDthCauseS);
            contentValues.put("chkDthCauseT", _chkDthCauseT);
            contentValues.put("chkDthCauseX", _chkDthCauseX);
            contentValues.put("chkDthCauseY", _chkDthCauseY);
            contentValues.put("chkDthCauseZ", _chkDthCauseZ);
            contentValues.put("DthCauseOth", _DthCauseOth);
            contentValues.put("DthHCProf", _DthHCProf);
            contentValues.put("chkDthCauseProfA", _chkDthCauseProfA);
            contentValues.put("chkDthCauseProfB", _chkDthCauseProfB);
            contentValues.put("chkDthCauseProfC", _chkDthCauseProfC);
            contentValues.put("chkDthCauseProfD", _chkDthCauseProfD);
            contentValues.put("chkDthCauseProfE", _chkDthCauseProfE);
            contentValues.put("chkDthCauseProfF", _chkDthCauseProfF);
            contentValues.put("chkDthCauseProfG", _chkDthCauseProfG);
            contentValues.put("chkDthCauseProfH", _chkDthCauseProfH);
            contentValues.put("chkDthCauseProfI", _chkDthCauseProfI);
            contentValues.put("chkDthCauseProfJ", _chkDthCauseProfJ);
            contentValues.put("chkDthCauseProfK", _chkDthCauseProfK);
            contentValues.put("chkDthCauseProfL", _chkDthCauseProfL);
            contentValues.put("chkDthCauseProfM", _chkDthCauseProfM);
            contentValues.put("chkDthCauseProfN", _chkDthCauseProfN);
            contentValues.put("chkDthCauseProfO", _chkDthCauseProfO);
            contentValues.put("chkDthCauseProfP", _chkDthCauseProfP);
            contentValues.put("chkDthCauseProfQ", _chkDthCauseProfQ);
            contentValues.put("chkDthCauseProfR", _chkDthCauseProfR);
            contentValues.put("chkDthCauseProfS", _chkDthCauseProfS);
            contentValues.put("chkDthCauseProfT", _chkDthCauseProfT);
            contentValues.put("chkDthCauseProfX", _chkDthCauseProfX);
            contentValues.put("chkDthCauseProfY", _chkDthCauseProfY);
            contentValues.put("chkDthCauseProfZ", _chkDthCauseProfZ);
            contentValues.put("DthCauseOthProf", _DthCauseOthProf);
            contentValues.put("DthCategory", _DthCategory);
            contentValues.put("DthEnrolChamps", _DthEnrolChamps);
            contentValues.put("DthChampsIdKnown", _DthChampsIdKnown);
            contentValues.put("DthChampsId", _DthChampsId);
            contentValues.put("DthVDate", _DthVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("DthNote", _DthNote);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            C.UpdateData(TableName, "DeathID", ("" + _DeathID + ""), contentValues);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return response;
    }

    int Count = 0;
    private int _Count = 0;

    public int getCount() {
        return _Count;
    }

    public List<tmpDeath_DataModel> SelectAll(Context context, String SQL) {
        Connection C = new Connection(context);
        List<tmpDeath_DataModel> data = new ArrayList<tmpDeath_DataModel>();
        tmpDeath_DataModel d = new tmpDeath_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Count += 1;
            d = new tmpDeath_DataModel();
            d._Count = Count;
            d._DeathID = cur.getString(cur.getColumnIndex("DeathID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._DthDate = cur.getString(cur.getColumnIndex("DthDate"));
            d._DthDateType = cur.getString(cur.getColumnIndex("DthDateType"));
            d._DthTime = cur.getString(cur.getColumnIndex("DthTime"));
            d._DthTimeType = cur.getString(cur.getColumnIndex("DthTimeType"));
            d._DthPlace = cur.getString(cur.getColumnIndex("DthPlace"));
            d._DthPlaceOth = cur.getString(cur.getColumnIndex("DthPlaceOth"));
            d._DthPlaceFacility = cur.getString(cur.getColumnIndex("DthPlaceFacility"));
            d._DthAdrsState = cur.getString(cur.getColumnIndex("DthAdrsState"));
            d._DthAdrsAL1 = cur.getString(cur.getColumnIndex("DthAdrsAL1"));
            d._DthAdrsAL2 = cur.getString(cur.getColumnIndex("DthAdrsAL2"));
            d._DthAdrsAL3 = cur.getString(cur.getColumnIndex("DthAdrsAL3"));
            d._DthAdrsAL4 = cur.getString(cur.getColumnIndex("DthAdrsAL4"));
            d._DthAdrsAL5 = cur.getString(cur.getColumnIndex("DthAdrsAL5"));
            d._DthSick = cur.getString(cur.getColumnIndex("DthSick"));
            d._DthCertificate = cur.getString(cur.getColumnIndex("DthCertificate"));
            d._chkDthCauseA = cur.getString(cur.getColumnIndex("chkDthCauseA"));
            d._chkDthCauseB = cur.getString(cur.getColumnIndex("chkDthCauseB"));
            d._chkDthCauseC = cur.getString(cur.getColumnIndex("chkDthCauseC"));
            d._chkDthCauseD = cur.getString(cur.getColumnIndex("chkDthCauseD"));
            d._chkDthCauseE = cur.getString(cur.getColumnIndex("chkDthCauseE"));
            d._chkDthCauseF = cur.getString(cur.getColumnIndex("chkDthCauseF"));
            d._chkDthCauseG = cur.getString(cur.getColumnIndex("chkDthCauseG"));
            d._chkDthCauseH = cur.getString(cur.getColumnIndex("chkDthCauseH"));
            d._chkDthCauseI = cur.getString(cur.getColumnIndex("chkDthCauseI"));
            d._chkDthCauseJ = cur.getString(cur.getColumnIndex("chkDthCauseJ"));
            d._chkDthCauseK = cur.getString(cur.getColumnIndex("chkDthCauseK"));
            d._chkDthCauseL = cur.getString(cur.getColumnIndex("chkDthCauseL"));
            d._chkDthCauseM = cur.getString(cur.getColumnIndex("chkDthCauseM"));
            d._chkDthCauseN = cur.getString(cur.getColumnIndex("chkDthCauseN"));
            d._chkDthCauseO = cur.getString(cur.getColumnIndex("chkDthCauseO"));
            d._chkDthCauseP = cur.getString(cur.getColumnIndex("chkDthCauseP"));
            d._chkDthCauseQ = cur.getString(cur.getColumnIndex("chkDthCauseQ"));
            d._chkDthCauseR = cur.getString(cur.getColumnIndex("chkDthCauseR"));
            d._chkDthCauseS = cur.getString(cur.getColumnIndex("chkDthCauseS"));
            d._chkDthCauseT = cur.getString(cur.getColumnIndex("chkDthCauseT"));
            d._chkDthCauseX = cur.getString(cur.getColumnIndex("chkDthCauseX"));
            d._chkDthCauseY = cur.getString(cur.getColumnIndex("chkDthCauseY"));
            d._chkDthCauseZ = cur.getString(cur.getColumnIndex("chkDthCauseZ"));
            d._DthCauseOth = cur.getString(cur.getColumnIndex("DthCauseOth"));
            d._DthHCProf = cur.getString(cur.getColumnIndex("DthHCProf"));
            d._chkDthCauseProfA = cur.getString(cur.getColumnIndex("chkDthCauseProfA"));
            d._chkDthCauseProfB = cur.getString(cur.getColumnIndex("chkDthCauseProfB"));
            d._chkDthCauseProfC = cur.getString(cur.getColumnIndex("chkDthCauseProfC"));
            d._chkDthCauseProfD = cur.getString(cur.getColumnIndex("chkDthCauseProfD"));
            d._chkDthCauseProfE = cur.getString(cur.getColumnIndex("chkDthCauseProfE"));
            d._chkDthCauseProfF = cur.getString(cur.getColumnIndex("chkDthCauseProfF"));
            d._chkDthCauseProfG = cur.getString(cur.getColumnIndex("chkDthCauseProfG"));
            d._chkDthCauseProfH = cur.getString(cur.getColumnIndex("chkDthCauseProfH"));
            d._chkDthCauseProfI = cur.getString(cur.getColumnIndex("chkDthCauseProfI"));
            d._chkDthCauseProfJ = cur.getString(cur.getColumnIndex("chkDthCauseProfJ"));
            d._chkDthCauseProfK = cur.getString(cur.getColumnIndex("chkDthCauseProfK"));
            d._chkDthCauseProfL = cur.getString(cur.getColumnIndex("chkDthCauseProfL"));
            d._chkDthCauseProfM = cur.getString(cur.getColumnIndex("chkDthCauseProfM"));
            d._chkDthCauseProfN = cur.getString(cur.getColumnIndex("chkDthCauseProfN"));
            d._chkDthCauseProfO = cur.getString(cur.getColumnIndex("chkDthCauseProfO"));
            d._chkDthCauseProfP = cur.getString(cur.getColumnIndex("chkDthCauseProfP"));
            d._chkDthCauseProfQ = cur.getString(cur.getColumnIndex("chkDthCauseProfQ"));
            d._chkDthCauseProfR = cur.getString(cur.getColumnIndex("chkDthCauseProfR"));
            d._chkDthCauseProfS = cur.getString(cur.getColumnIndex("chkDthCauseProfS"));
            d._chkDthCauseProfT = cur.getString(cur.getColumnIndex("chkDthCauseProfT"));
            d._chkDthCauseProfX = cur.getString(cur.getColumnIndex("chkDthCauseProfX"));
            d._chkDthCauseProfY = cur.getString(cur.getColumnIndex("chkDthCauseProfY"));
            d._chkDthCauseProfZ = cur.getString(cur.getColumnIndex("chkDthCauseProfZ"));
            d._DthCauseOthProf = cur.getString(cur.getColumnIndex("DthCauseOthProf"));
            d._DthCategory = cur.getString(cur.getColumnIndex("DthCategory"));
            d._DthEnrolChamps = cur.getString(cur.getColumnIndex("DthEnrolChamps"));
            d._DthChampsIdKnown = cur.getString(cur.getColumnIndex("DthChampsIdKnown"));
            d._DthChampsId = cur.getString(cur.getColumnIndex("DthChampsId"));
            d._DthVDate = cur.getString(cur.getColumnIndex("DthVDate"));
            d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
            d._DthNote = cur.getString(cur.getColumnIndex("DthNote"));
            d._DeviceID = cur.getString(cur.getColumnIndex("DeviceID"));
            d._EntryUser = cur.getString(cur.getColumnIndex("EntryUser"));
            d._isdelete = cur.getInt(cur.getColumnIndex("isdelete"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }
}