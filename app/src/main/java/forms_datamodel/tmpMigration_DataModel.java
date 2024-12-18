package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class tmpMigration_DataModel implements Serializable {

       private String _MigrationID = "";
       public String getMigrationID(){
             return _MigrationID;
        }
       public void setMigrationID(String newValue){
             _MigrationID = newValue;
        }
       private String _HHID = "";
       public String getHHID(){
             return _HHID;
        }
       public void setHHID(String newValue){
             _HHID = newValue;
        }
       private String _MemID = "";
       public String getMemID(){
             return _MemID;
        }
       public void setMemID(String newValue){
             _MemID = newValue;
        }
       private String _MigEvType = "";
       public String getMigEvType(){
             return _MigEvType;
        }
       public void setMigEvType(String newValue){
             _MigEvType = newValue;
        }
       private String _MigDate = "";
       public String getMigDate(){
             return _MigDate;
        }
       public void setMigDate(String newValue){
             _MigDate = newValue;
        }
       private String _MigLocType = "";
       public String getMigLocType(){
             return _MigLocType;
        }
       public void setMigLocType(String newValue){
             _MigLocType = newValue;
        }
       private String _MigAdminL1 = "";
       public String getMigAdminL1(){
             return _MigAdminL1;
        }
       public void setMigAdminL1(String newValue){
             _MigAdminL1 = newValue;
        }
       private String _MigAdminL2 = "";
       public String getMigAdminL2(){
             return _MigAdminL2;
        }
       public void setMigAdminL2(String newValue){
             _MigAdminL2 = newValue;
        }
       private String _MigAdminL3 = "";
       public String getMigAdminL3(){
             return _MigAdminL3;
        }
       public void setMigAdminL3(String newValue){
             _MigAdminL3 = newValue;
        }
       private String _MigAdminL4 = "";
       public String getMigAdminL4(){
             return _MigAdminL4;
        }
       public void setMigAdminL4(String newValue){
             _MigAdminL4 = newValue;
        }
       private String _MigAdminLArea = "";
       public String getMigAdminLArea(){
             return _MigAdminLArea;
        }
       public void setMigAdminLArea(String newValue){
             _MigAdminLArea = newValue;
        }
       private String _MigCountry = "";
       public String getMigCountry(){
             return _MigCountry;
        }
       public void setMigCountry(String newValue){
             _MigCountry = newValue;
        }
    private String _MigBirthCountry = "";
    public String getMigBirthCountry(){
        return _MigBirthCountry;
    }
    public void setMigBirthCountry(String newValue){
        _MigBirthCountry = newValue;
    }
    private String _MigBirthCountryOth = "";
    public String getMigBirthCountryOth(){
        return _MigBirthCountryOth;
    }
    public void setMigBirthCountryOth(String newValue){
        _MigBirthCountryOth = newValue;
    }
    private String _MigOriginCountry = "";
    public String getMigOriginCountry(){
        return _MigOriginCountry;
    }
    public void setMigOriginCountry(String newValue){
        _MigOriginCountry = newValue;
    }
    private String _MigOriginCountryOth = "";
    public String getMigOriginCountryOth(){
        return _MigOriginCountryOth;
    }
    public void setMigOriginCountryOth(String newValue){
        _MigOriginCountryOth = newValue;
    }
    private String _MigWCRegionBurCity = "";
    public String getMigWCRegionBurCity(){
        return _MigWCRegionBurCity;
    }
    public void setMigWCRegionBurCity(String newValue){
        _MigWCRegionBurCity = newValue;
    }
    private String _MigBurCity = "";
    public String getMigBurCity(){
        return _MigBurCity;
    }
    public void setMigBurCity(String newValue){
        _MigBurCity = newValue;
    }
    private String _MigReason = "";
    public String getMigReason(){
        return _MigReason;
    }
    public void setMigReason(String newValue){
        _MigReason = newValue;
    }
    private String _MigReasonOth = "";
    public String getMigReasonOth(){
        return _MigReasonOth;
    }
    public void setMigReasonOth(String newValue){
        _MigReasonOth = newValue;
    }
    private String _MigMaritalChangeReason = "";
    public String getMigMaritalChangeReason(){
        return _MigMaritalChangeReason;
    }
    public void setMigMaritalChangeReason(String newValue){
        _MigMaritalChangeReason = newValue;
    }
    private String _MigMaritalChangeReasonOth = "";
    public String getMigMaritalChangeReasonOth(){
        return _MigMaritalChangeReasonOth;
    }
    public void setMigMaritalChangeReasonOth(String newValue){
        _MigMaritalChangeReasonOth = newValue;
    }
    private String _MigAbleToRW = "";
    public String getMigAbleToRW(){
        return _MigAbleToRW;
    }
    public void setMigAbleToRW(String newValue){
        _MigAbleToRW = newValue;
    }
    private String _chkMigLanguageA = "";
    public String getchkMigLanguageA(){
        return _chkMigLanguageA;
    }
    public void setchkMigLanguageA(String newValue){
        _chkMigLanguageA = newValue;
    }
    private String _chkMigLanguageB = "";
    public String getchkMigLanguageB(){
        return _chkMigLanguageB;
    }
    public void setchkMigLanguageB(String newValue){
        _chkMigLanguageB = newValue;
    }
    private String _chkMigLanguageC = "";
    public String getchkMigLanguageC(){
        return _chkMigLanguageC;
    }
    public void setchkMigLanguageC(String newValue){
        _chkMigLanguageC = newValue;
    }
    private String _chkMigLanguageD = "";
    public String getchkMigLanguageD(){
        return _chkMigLanguageD;
    }
    public void setchkMigLanguageD(String newValue){
        _chkMigLanguageD = newValue;
    }
    private String _MigLanguageOth = "";
    public String getMigLanguageOth(){
        return _MigLanguageOth;
    }
    public void setMigLanguageOth(String newValue){
        _MigLanguageOth = newValue;
    }
    private String _MigOccupation = "";
    public String getMigOccupation(){
        return _MigOccupation;
    }
    public void setMigOccupation(String newValue){
        _MigOccupation = newValue;
    }
    private String _MigTotalUnion = "";
    public String getMigTotalUnion(){
        return _MigTotalUnion;
    }
    public void setMigTotalUnion(String newValue){
        _MigTotalUnion = newValue;
    }
    private String _MigFirstUnionDate = "";
    public String getMigFirstUnionDate(){
        return _MigFirstUnionDate;
    }
    public void setMigFirstUnionDate(String newValue){
        _MigFirstUnionDate = newValue;
    }
    private String _MigUnionRupture = "";
    public String getMigUnionRupture(){
        return _MigUnionRupture;
    }
    public void setMigUnionRupture(String newValue){
        _MigUnionRupture = newValue;
    }
    private String _MigRuptureDate = "";
    public String getMigRuptureDate(){
        return _MigRuptureDate;
    }
    public void setMigRuptureDate(String newValue){
        _MigRuptureDate = newValue;
    }
    private String _MigNotDisUnionDate = "";
    public String getMigNotDisUnionDate(){
        return _MigNotDisUnionDate;
    }
    public void setMigNotDisUnionDate(String newValue){
        _MigNotDisUnionDate = newValue;
    }
       private String _MigVDate = "";
       public String getMigVDate(){
             return _MigVDate;
        }
       public void setMigVDate(String newValue){
             _MigVDate = newValue;
        }
       private String _Rnd = "";
       public String getRnd(){
             return _Rnd;
        }
       public void setRnd(String newValue){
             _Rnd = newValue;
        }
       private String _MigNote = "";
       public String getMigNote(){
             return _MigNote;
        }
       public void setMigNote(String newValue){
             _MigNote = newValue;
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

       String TableName = "tmpMigration";

       public String SaveUpdateData(Context context)
       {
           String response = "";
           C = new Connection(context);
           String SQL = "";
           try
           {
                if(C.Existence("Select * from "+ TableName +"  Where MigrationID='"+ _MigrationID +"' "))
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
                contentValues.put("MigrationID", _MigrationID);
                contentValues.put("HHID", _HHID);
                contentValues.put("MemID", _MemID);
                contentValues.put("MigEvType", _MigEvType);
                contentValues.put("MigDate", _MigDate);
                contentValues.put("MigLocType", _MigLocType);
                contentValues.put("MigAdminL1", _MigAdminL1);
                contentValues.put("MigAdminL2", _MigAdminL2);
                contentValues.put("MigAdminL3", _MigAdminL3);
                contentValues.put("MigAdminL4", _MigAdminL4);
                contentValues.put("MigAdminLArea", _MigAdminLArea);
                contentValues.put("MigCountry", _MigCountry);
                 contentValues.put("MigBirthCountry", _MigBirthCountry);
                 contentValues.put("MigBirthCountryOth", _MigBirthCountryOth);
                 contentValues.put("MigOriginCountry", _MigOriginCountry);
                 contentValues.put("MigOriginCountryOth", _MigOriginCountryOth);
                 contentValues.put("MigWCRegionBurCity", _MigWCRegionBurCity);
                 contentValues.put("MigBurCity", _MigBurCity);
                 contentValues.put("MigReason", _MigReason);
                 contentValues.put("MigReasonOth", _MigReasonOth);
                 contentValues.put("MigMaritalChangeReason", _MigMaritalChangeReason);
                 contentValues.put("MigMaritalChangeReasonOth", _MigMaritalChangeReasonOth);
                 contentValues.put("MigAbleToRW", _MigAbleToRW);
                 contentValues.put("chkMigLanguageA", _chkMigLanguageA);
                 contentValues.put("chkMigLanguageB", _chkMigLanguageB);
                 contentValues.put("chkMigLanguageC", _chkMigLanguageC);
                 contentValues.put("chkMigLanguageD", _chkMigLanguageD);
                 contentValues.put("MigLanguageOth", _MigLanguageOth);
                 contentValues.put("MigOccupation", _MigOccupation);
                 contentValues.put("MigTotalUnion", _MigTotalUnion);
                 contentValues.put("MigFirstUnionDate", _MigFirstUnionDate);
                 contentValues.put("MigUnionRupture", _MigUnionRupture);
                 contentValues.put("MigRuptureDate", _MigRuptureDate);
                 contentValues.put("MigNotDisUnionDate", _MigNotDisUnionDate);
                contentValues.put("MigVDate", _MigVDate);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("MigNote", _MigNote);
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
                contentValues.put("MigrationID", _MigrationID);
                contentValues.put("HHID", _HHID);
                contentValues.put("MemID", _MemID);
                contentValues.put("MigEvType", _MigEvType);
                contentValues.put("MigDate", _MigDate);
                contentValues.put("MigLocType", _MigLocType);
                contentValues.put("MigAdminL1", _MigAdminL1);
                contentValues.put("MigAdminL2", _MigAdminL2);
                contentValues.put("MigAdminL3", _MigAdminL3);
                contentValues.put("MigAdminL4", _MigAdminL4);
                contentValues.put("MigAdminLArea", _MigAdminLArea);
                contentValues.put("MigCountry", _MigCountry);
                 contentValues.put("MigBirthCountry", _MigBirthCountry);
                 contentValues.put("MigBirthCountryOth", _MigBirthCountryOth);
                 contentValues.put("MigOriginCountry", _MigOriginCountry);
                 contentValues.put("MigOriginCountryOth", _MigOriginCountryOth);
                 contentValues.put("MigWCRegionBurCity", _MigWCRegionBurCity);
                 contentValues.put("MigBurCity", _MigBurCity);
                 contentValues.put("MigReason", _MigReason);
                 contentValues.put("MigReasonOth", _MigReasonOth);
                 contentValues.put("MigMaritalChangeReason", _MigMaritalChangeReason);
                 contentValues.put("MigMaritalChangeReasonOth", _MigMaritalChangeReasonOth);
                 contentValues.put("MigAbleToRW", _MigAbleToRW);
                 contentValues.put("chkMigLanguageA", _chkMigLanguageA);
                 contentValues.put("chkMigLanguageB", _chkMigLanguageB);
                 contentValues.put("chkMigLanguageC", _chkMigLanguageC);
                 contentValues.put("chkMigLanguageD", _chkMigLanguageD);
                 contentValues.put("MigLanguageOth", _MigLanguageOth);
                 contentValues.put("MigOccupation", _MigOccupation);
                 contentValues.put("MigTotalUnion", _MigTotalUnion);
                 contentValues.put("MigFirstUnionDate", _MigFirstUnionDate);
                 contentValues.put("MigUnionRupture", _MigUnionRupture);
                 contentValues.put("MigRuptureDate", _MigRuptureDate);
                 contentValues.put("MigNotDisUnionDate", _MigNotDisUnionDate);
                contentValues.put("MigVDate", _MigVDate);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("MigNote", _MigNote);
                contentValues.put("Upload", _Upload);
                contentValues.put("modifyDate", _modifyDate);
                C.UpdateData(TableName, "MigrationID", (""+ _MigrationID +""), contentValues);
             }
             catch(Exception  e)
             {
                response = e.getMessage();
             }
          return response;
       }

         int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
       public List<tmpMigration_DataModel> SelectAll(Context context, String SQL)
       {
           Connection C = new Connection(context);
           List<tmpMigration_DataModel> data = new ArrayList<tmpMigration_DataModel>();
           tmpMigration_DataModel d = new tmpMigration_DataModel();
           Cursor cur = C.ReadData(SQL);

           cur.moveToFirst();
           while(!cur.isAfterLast())
           {
               Count += 1;
               d = new tmpMigration_DataModel();
               d._Count = Count;
               d._MigrationID = cur.getString(cur.getColumnIndex("MigrationID"));
               d._HHID = cur.getString(cur.getColumnIndex("HHID"));
               d._MemID = cur.getString(cur.getColumnIndex("MemID"));
               d._MigEvType = cur.getString(cur.getColumnIndex("MigEvType"));
               d._MigDate = cur.getString(cur.getColumnIndex("MigDate"));
               d._MigLocType = cur.getString(cur.getColumnIndex("MigLocType"));
               d._MigAdminL1 = cur.getString(cur.getColumnIndex("MigAdminL1"));
               d._MigAdminL2 = cur.getString(cur.getColumnIndex("MigAdminL2"));
               d._MigAdminL3 = cur.getString(cur.getColumnIndex("MigAdminL3"));
               d._MigAdminL4 = cur.getString(cur.getColumnIndex("MigAdminL4"));
               d._MigAdminLArea = cur.getString(cur.getColumnIndex("MigAdminLArea"));
               d._MigCountry = cur.getString(cur.getColumnIndex("MigCountry"));
               d._MigBirthCountry = cur.getString(cur.getColumnIndex("MigBirthCountry"));
               d._MigBirthCountryOth = cur.getString(cur.getColumnIndex("MigBirthCountryOth"));
               d._MigOriginCountry = cur.getString(cur.getColumnIndex("MigOriginCountry"));
               d._MigOriginCountryOth = cur.getString(cur.getColumnIndex("MigOriginCountryOth"));
               d._MigWCRegionBurCity = cur.getString(cur.getColumnIndex("MigWCRegionBurCity"));
               d._MigBurCity = cur.getString(cur.getColumnIndex("MigBurCity"));
               d._MigReason = cur.getString(cur.getColumnIndex("MigReason"));
               d._MigReasonOth = cur.getString(cur.getColumnIndex("MigReasonOth"));
               d._MigMaritalChangeReason = cur.getString(cur.getColumnIndex("MigMaritalChangeReason"));
               d._MigMaritalChangeReasonOth = cur.getString(cur.getColumnIndex("MigMaritalChangeReasonOth"));
               d._MigAbleToRW = cur.getString(cur.getColumnIndex("MigAbleToRW"));
               d._chkMigLanguageA = cur.getString(cur.getColumnIndex("chkMigLanguageA"));
               d._chkMigLanguageB = cur.getString(cur.getColumnIndex("chkMigLanguageB"));
               d._chkMigLanguageC = cur.getString(cur.getColumnIndex("chkMigLanguageC"));
               d._chkMigLanguageD = cur.getString(cur.getColumnIndex("chkMigLanguageD"));
               d._MigLanguageOth = cur.getString(cur.getColumnIndex("MigLanguageOth"));
               d._MigOccupation = cur.getString(cur.getColumnIndex("MigOccupation"));
               d._MigTotalUnion = cur.getString(cur.getColumnIndex("MigTotalUnion"));
               d._MigFirstUnionDate = cur.getString(cur.getColumnIndex("MigFirstUnionDate"));
               d._MigUnionRupture = cur.getString(cur.getColumnIndex("MigUnionRupture"));
               d._MigRuptureDate = cur.getString(cur.getColumnIndex("MigRuptureDate"));
               d._MigNotDisUnionDate = cur.getString(cur.getColumnIndex("MigNotDisUnionDate"));
               d._MigVDate = cur.getString(cur.getColumnIndex("MigVDate"));
               d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
               d._MigNote = cur.getString(cur.getColumnIndex("MigNote"));
               data.add(d);

               cur.moveToNext();
           }
           cur.close();
         return data;
       }
}