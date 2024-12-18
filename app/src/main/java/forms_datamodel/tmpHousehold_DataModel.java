package forms_datamodel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Common.Connection;
import Common.Global;
import Utility.AuditTrial;

public class tmpHousehold_DataModel {

       private String _HHID = "";
       public String getHHID(){
             return _HHID;
        }
       public void setHHID(String newValue){
             _HHID = newValue;
        }
       private String _HHNO = "";
       public String getHHNO(){
             return _HHNO;
        }
       public void setHHNO(String newValue){
             _HHNO = newValue;
        }
       private String _VillID = "";
       public String getVillID(){
             return _VillID;
        }
       public void setVillID(String newValue){
             _VillID = newValue;
        }
       private String _CompoundID = "";
       public String getCompoundID(){
             return _CompoundID;
        }
       public void setCompoundID(String newValue){
             _CompoundID = newValue;
        }
       private String _HHHead = "";
       public String getHHHead(){
             return _HHHead;
        }
       public void setHHHead(String newValue){
             _HHHead = newValue;
        }
       private String _MobileNo1 = "";
       public String getMobileNo1(){
             return _MobileNo1;
        }
       public void setMobileNo1(String newValue){
             _MobileNo1 = newValue;
        }
       private String _MobileNo2 = "";
       public String getMobileNo2(){
             return _MobileNo2;
        }
       public void setMobileNo2(String newValue){
             _MobileNo2 = newValue;
        }
       private String _TotMem = "";
       public String getTotMem(){
             return _TotMem;
        }
       public void setTotMem(String newValue){
             _TotMem = newValue;
        }
       private String _HHEnType = "";
       public String getHHEnType(){
             return _HHEnType;
        }
       public void setHHEnType(String newValue){
             _HHEnType = newValue;
        }
       private String _HHEnDate = "";
       public String getHHEnDate(){
             return _HHEnDate;
        }
       public void setHHEnDate(String newValue){
             _HHEnDate = newValue;
        }
       private String _HHExType = "";
       public String getHHExType(){
             return _HHExType;
        }
       public void setHHExType(String newValue){
             _HHExType = newValue;
        }
       private String _HHExDate = "";
       public String getHHExDate(){
             return _HHExDate;
        }
       public void setHHExDate(String newValue){
             _HHExDate = newValue;
        }
       private String _Rnd = "";
       public String getRnd(){
             return _Rnd;
        }
       public void setRnd(String newValue){
             _Rnd = newValue;
        }
       private String _Active = "";
       public String getActive(){
             return _Active;
        }
       public void setActive(String newValue){
             _Active = newValue;
        }
       private String _HHNote = "";
       public String getHHNote(){
             return _HHNote;
        }
       public void setHHNote(String newValue){
             _HHNote = newValue;
        }
       private String _StartTime = "";
       public void setStartTime(String newValue){
             _StartTime = newValue;
        }
        public String getStartTime(){
            return _StartTime;
        }
       private String _EndTime = "";
       public void setEndTime(String newValue){
             _EndTime = newValue;
        }
    public String getEndTime(){
        return _EndTime;
    }
       private String _DeviceID = "";
       public void setDeviceID(String newValue){
             _DeviceID = newValue;
        }
    public String getDeviceID(){
        return _DeviceID;
    }
       private String _EntryUser = "";
       public void setEntryUser(String newValue){
             _EntryUser = newValue;
        }
    public String getEntryUser(){
        return _EntryUser;
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

       String TableName = "tmpHousehold";

       public String SaveUpdateData(Context context)
       {
           String response = "";
           C = new Connection(context);
           String SQL = "";
           try
           {
                if(C.Existence("Select * from "+ TableName +"  Where HHID='"+ _HHID +"' "))
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
                contentValues.put("HHNO", _HHNO);
                contentValues.put("VillID", _VillID);
                contentValues.put("CompoundID", _CompoundID);
                contentValues.put("HHHead", _HHHead);
                contentValues.put("MobileNo1", _MobileNo1);
                contentValues.put("MobileNo2", _MobileNo2);
                contentValues.put("TotMem", _TotMem);
                contentValues.put("HHEnType", _HHEnType);
                contentValues.put("HHEnDate", _HHEnDate);
                contentValues.put("HHExType", _HHExType);
                contentValues.put("HHExDate", _HHExDate);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("Active", _Active);
                contentValues.put("HHNote", _HHNote);
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
                contentValues.put("HHNO", _HHNO);
                contentValues.put("VillID", _VillID);
                contentValues.put("CompoundID", _CompoundID);
                contentValues.put("HHHead", _HHHead);
                contentValues.put("MobileNo1", _MobileNo1);
                contentValues.put("MobileNo2", _MobileNo2);
                contentValues.put("TotMem", _TotMem);
                contentValues.put("HHEnType", _HHEnType);
                contentValues.put("HHEnDate", _HHEnDate);
                contentValues.put("HHExType", _HHExType);
                contentValues.put("HHExDate", _HHExDate);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("Active", _Active);
                contentValues.put("HHNote", _HHNote);
                contentValues.put("Upload", _Upload);
                contentValues.put("modifyDate", _modifyDate);
                C.UpdateData(TableName, "HHID", (""+ _HHID +""), contentValues);
                 manageAudit(context,this,AuditTrial.KEY_UPDATE);
             }
             catch(Exception  e)
             {
                response = e.getMessage();
             }
          return response;
       }

         int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }

       @SuppressLint("Range")
       public List<tmpHousehold_DataModel> SelectAll(Context context, String SQL)
       {
           Connection C = new Connection(context);
           List<tmpHousehold_DataModel> data = new ArrayList<tmpHousehold_DataModel>();
           tmpHousehold_DataModel d = new tmpHousehold_DataModel();
           Cursor cur = C.ReadData(SQL);

           cur.moveToFirst();
           while(!cur.isAfterLast())
           {
               Count += 1;
               d = new tmpHousehold_DataModel();
               d._Count = Count;
               d._HHID = cur.getString(cur.getColumnIndex("HHID"));
               d._HHNO = cur.getString(cur.getColumnIndex("HHNO"));
               d._VillID = cur.getString(cur.getColumnIndex("VillID"));
               d._CompoundID = cur.getString(cur.getColumnIndex("CompoundID"));
               d._HHHead = cur.getString(cur.getColumnIndex("HHHead"));
               d._MobileNo1 = cur.getString(cur.getColumnIndex("MobileNo1"));
               d._MobileNo2 = cur.getString(cur.getColumnIndex("MobileNo2"));
               d._TotMem = cur.getString(cur.getColumnIndex("TotMem"));
               d._HHEnType = cur.getString(cur.getColumnIndex("HHEnType"));
               d._HHEnDate = cur.getString(cur.getColumnIndex("HHEnDate"));
               d._HHExType = cur.getString(cur.getColumnIndex("HHExType"));
               d._HHExDate = cur.getString(cur.getColumnIndex("HHExDate"));
               d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
               d._Active = cur.getString(cur.getColumnIndex("Active"));
               d._HHNote = cur.getString(cur.getColumnIndex("HHNote"));
               data.add(d);
               manageAudit(context,d,AuditTrial.KEY_SELECT);
               cur.moveToNext();
           }
           cur.close();
         return data;
       }

    private String _VisitStatus = "";
    public String getVisitStatus(){return _VisitStatus; }
    private String _regis_mem = "";
    public String getregis_mem(){return _regis_mem; }

    private String _BaselineStatus = "";
    public String getBaselineStatus(){return _BaselineStatus; }



    public List<tmpHousehold_DataModel> SelectHHList(Context context, String SQL)
    {
        Connection C = new Connection(context);
        List<tmpHousehold_DataModel> data = new ArrayList<tmpHousehold_DataModel>();
        tmpHousehold_DataModel d = new tmpHousehold_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while(!cur.isAfterLast())
        {
            Count += 1;
            d = new tmpHousehold_DataModel();
            d._Count = Count;
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._HHNO = cur.getString(cur.getColumnIndex("HHNO"));
            d._VillID = cur.getString(cur.getColumnIndex("VillID"));
            d._CompoundID = cur.getString(cur.getColumnIndex("CompoundID"));
            d._HHHead = cur.getString(cur.getColumnIndex("HHHead"));
            d._MobileNo1 = cur.getString(cur.getColumnIndex("MobileNo1"));
            d._MobileNo2 = cur.getString(cur.getColumnIndex("MobileNo2"));
            d._TotMem = cur.getString(cur.getColumnIndex("TotMem"));
            d._regis_mem = cur.getString(cur.getColumnIndex("regis_mem"));
            d._VisitStatus = cur.getString(cur.getColumnIndex("VisitStatus"));
            d._HHNote = cur.getString(cur.getColumnIndex("HHNote"));
            d._BaselineStatus = cur.getString(cur.getColumnIndex("BaselineStatus"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }



    static Map<String, Object> firstStateMap;
    public void manageAudit(Context context, tmpHousehold_DataModel ob, String key) {

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
    public Map<String, Object> getMapData(tmpHousehold_DataModel ob) {
        Map<String, Object> data = new HashMap<>();

        if (ob != null) {
            data.put("HHID", ob._HHID);
            data.put("HHNO", ob._HHNO);
            data.put("VillID", ob._VillID);
            data.put("CompoundID", ob._CompoundID);
            data.put("HHHead", ob._HHHead);
            data.put("MobileNo1", ob._MobileNo1);
            data.put("MobileNo2", ob._MobileNo2);
            data.put("TotMem", ob._TotMem);
            data.put("HHEnType", ob._HHEnType);
            data.put("HHEnDate", ob._HHEnDate);
            data.put("HHExType", ob._HHExType);
            data.put("HHExDate", ob._HHExDate);
            data.put("Rnd", ob._Rnd);
            data.put("Active", ob._Active);
            data.put("HHNote", ob._HHNote);
        }

        return data;
    }
}