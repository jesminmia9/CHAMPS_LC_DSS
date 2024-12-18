package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class tmpNotPregnant_DataModel {

       private String _NotPregID = "";
       public String getNotPregID(){
             return _NotPregID;
        }
       public void setNotPregID(String newValue){
             _NotPregID = newValue;
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
       private String _NotPregVDate = "";
       public String getNotPregVDate(){
             return _NotPregVDate;
        }
       public void setNotPregVDate(String newValue){
             _NotPregVDate = newValue;
        }
    private String _LMPDt = "";
    public String getLMPDt(){
        return _LMPDt;
    }
    public void setLMPDt(String newValue){
        _LMPDt = newValue;
    }
    private String _LMPDtType = "";
    public String getLMPDtType(){
        return _LMPDtType;
    }
    public void setLMPDtType(String newValue){
        _LMPDtType = newValue;
    }
       private String _PregStatus = "";
       public String getPregStatus(){
             return _PregStatus;
        }
       public void setPregStatus(String newValue){
             _PregStatus = newValue;
        }
       private String _NotPregNote = "";
       public String getNotPregNote(){
             return _NotPregNote;
        }
       public void setNotPregNote(String newValue){
             _NotPregNote = newValue;
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
    private String _Rnd = "";
    public String getRnd(){
        return _Rnd;
    }
    public void setRnd(String newValue){
        _Rnd = newValue;
    }

       String TableName = "tmpNotPregnant";

       public String SaveUpdateData(Context context)
       {
           String response = "";
           C = new Connection(context);
           String SQL = "";
           try
           {
                if(C.Existence("Select * from "+ TableName +"  Where NotPregID='"+ _NotPregID +"' "))
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
                contentValues.put("NotPregID", _NotPregID);
                contentValues.put("HHID", _HHID);
                contentValues.put("MemID", _MemID);
                contentValues.put("NotPregVDate", _NotPregVDate);
                 contentValues.put("LMPDt", _LMPDt);
                 contentValues.put("LMPDtType", _LMPDtType);
                contentValues.put("PregStatus", _PregStatus);
                contentValues.put("NotPregNote", _NotPregNote);
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
                 contentValues.put("Rnd", _Rnd);
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
                contentValues.put("NotPregID", _NotPregID);
                contentValues.put("HHID", _HHID);
                contentValues.put("MemID", _MemID);
                contentValues.put("NotPregVDate", _NotPregVDate);
                 contentValues.put("LMPDt", _LMPDt);
                 contentValues.put("LMPDtType", _LMPDtType);
                contentValues.put("PregStatus", _PregStatus);
                contentValues.put("NotPregNote", _NotPregNote);
                contentValues.put("Upload", _Upload);
                contentValues.put("modifyDate", _modifyDate);
                 contentValues.put("Rnd", _Rnd);
                C.UpdateData(TableName, "NotPregID", (""+ _NotPregID +""), contentValues);
             }
             catch(Exception  e)
             {
                response = e.getMessage();
             }
          return response;
       }

         int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
       public List<tmpNotPregnant_DataModel> SelectAll(Context context, String SQL)
       {
           Connection C = new Connection(context);
           List<tmpNotPregnant_DataModel> data = new ArrayList<tmpNotPregnant_DataModel>();
           tmpNotPregnant_DataModel d = new tmpNotPregnant_DataModel();
           Cursor cur = C.ReadData(SQL);

           cur.moveToFirst();
           while(!cur.isAfterLast())
           {
               Count += 1;
               d = new tmpNotPregnant_DataModel();
               d._Count = Count;
               d._NotPregID = cur.getString(cur.getColumnIndex("NotPregID"));
               d._HHID = cur.getString(cur.getColumnIndex("HHID"));
               d._MemID = cur.getString(cur.getColumnIndex("MemID"));
               d._NotPregVDate = cur.getString(cur.getColumnIndex("NotPregVDate"));
               d._LMPDt = cur.getString(cur.getColumnIndex("LMPDt"));
               d._LMPDtType = cur.getString(cur.getColumnIndex("LMPDtType"));
               d._PregStatus = cur.getString(cur.getColumnIndex("PregStatus"));
               d._NotPregNote = cur.getString(cur.getColumnIndex("NotPregNote"));
               d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
               data.add(d);

               cur.moveToNext();
           }
           cur.close();
         return data;
       }
}