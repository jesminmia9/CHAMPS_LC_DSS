package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class tmpVisits_DataModel {

       private String _HHID = "";
       public String getHHID(){
             return _HHID;
        }
       public void setHHID(String newValue){
             _HHID = newValue;
        }
       private String _VisitNo = "";
       public String getVisitNo(){
             return _VisitNo;
        }
       public void setVisitNo(String newValue){
             _VisitNo = newValue;
        }
       private String _VisitDate = "";
       public String getVisitDate(){
             return _VisitDate;
        }
       public void setVisitDate(String newValue){
             _VisitDate = newValue;
        }
       private String _VisitStatus = "";
       public String getVisitStatus(){
             return _VisitStatus;
        }
       public void setVisitStatus(String newValue){
             _VisitStatus = newValue;
        }
       private String _VisitStatusOth = "";
       public String getVisitStatusOth(){
             return _VisitStatusOth;
        }
       public void setVisitStatusOth(String newValue){
             _VisitStatusOth = newValue;
        }
       private String _RespID = "";
       public String getRespID(){
             return _RespID;
        }
       public void setRespID(String newValue){
             _RespID = newValue;
        }
       private String _HaveDeath = "";
       public String getHaveDeath(){
             return _HaveDeath;
        }
       public void setHaveDeath(String newValue){
             _HaveDeath = newValue;
        }
       private String _TotalDeath = "";
       public String getTotalDeath(){
             return _TotalDeath;
        }
       public void setTotalDeath(String newValue){
             _TotalDeath = newValue;
        }
       private String _Rnd = "";
       public String getRnd(){
             return _Rnd;
        }
       public void setRnd(String newValue){
             _Rnd = newValue;
        }
       private String _VisitNote = "";
       public String getVisitNote(){
             return _VisitNote;
        }
       public void setVisitNote(String newValue){
             _VisitNote = newValue;
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

       String TableName = "tmpVisits";

       public String SaveUpdateData(Context context)
       {
           String response = "";
           C = new Connection(context);
           String SQL = "";
           try
           {
                if(C.Existence("Select * from "+ TableName +"  Where HHID='"+ _HHID +"' and VisitNo='"+ _VisitNo +"' "))
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
                contentValues.put("VisitNo", _VisitNo);
                contentValues.put("VisitDate", _VisitDate);
                contentValues.put("VisitStatus", _VisitStatus);
                contentValues.put("VisitStatusOth", _VisitStatusOth);
                contentValues.put("RespID", _RespID);
                contentValues.put("HaveDeath", _HaveDeath);
                contentValues.put("TotalDeath", _TotalDeath);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("VisitNote", _VisitNote);
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
                contentValues.put("VisitNo", _VisitNo);
                contentValues.put("VisitDate", _VisitDate);
                contentValues.put("VisitStatus", _VisitStatus);
                contentValues.put("VisitStatusOth", _VisitStatusOth);
                contentValues.put("RespID", _RespID);
                contentValues.put("HaveDeath", _HaveDeath);
                contentValues.put("TotalDeath", _TotalDeath);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("VisitNote", _VisitNote);
                contentValues.put("Upload", _Upload);
                contentValues.put("modifyDate", _modifyDate);
                C.UpdateData(TableName, "HHID,VisitNo", (""+ _HHID +", "+ _VisitNo +""), contentValues);
             }
             catch(Exception  e)
             {
                response = e.getMessage();
             }
          return response;
       }

         int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
       public List<tmpVisits_DataModel> SelectAll(Context context, String SQL)
       {
           Connection C = new Connection(context);
           List<tmpVisits_DataModel> data = new ArrayList<tmpVisits_DataModel>();
           tmpVisits_DataModel d = new tmpVisits_DataModel();
           Cursor cur = C.ReadData(SQL);

           cur.moveToFirst();
           while(!cur.isAfterLast())
           {
               Count += 1;
               d = new tmpVisits_DataModel();
               d._Count = Count;
               d._HHID = cur.getString(cur.getColumnIndex("HHID"));
               d._VisitNo = cur.getString(cur.getColumnIndex("VisitNo"));
               d._VisitDate = cur.getString(cur.getColumnIndex("VisitDate"));
               d._VisitStatus = cur.getString(cur.getColumnIndex("VisitStatus"));
               d._VisitStatusOth = cur.getString(cur.getColumnIndex("VisitStatusOth"));
               d._RespID = cur.getString(cur.getColumnIndex("RespID"));
               d._HaveDeath = cur.getString(cur.getColumnIndex("HaveDeath"));
               d._TotalDeath = cur.getString(cur.getColumnIndex("TotalDeath"));
               d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
               d._VisitNote = cur.getString(cur.getColumnIndex("VisitNote"));
               data.add(d);

               cur.moveToNext();
           }
           cur.close();
         return data;
       }
}