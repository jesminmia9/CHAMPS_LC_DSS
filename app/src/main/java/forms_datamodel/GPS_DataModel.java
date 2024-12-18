package forms_datamodel;

import android.annotation.SuppressLint;
import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import Common.Global;
 import android.content.ContentValues;
 public class GPS_DataModel{

        private String _IDNo = "";
        public String getIDNo(){
              return _IDNo;
         }
        public void setIDNo(String newValue){
              _IDNo = newValue;
         }
        private String _VillID = "";
        public String getVillID(){
              return _VillID;
         }
        public void setVillID(String newValue){
              _VillID = newValue;
         }
        private String _GPSType = "";
        public String getGPSType(){
              return _GPSType;
         }
        public void setGPSType(String newValue){
              _GPSType = newValue;
         }
        private String _CompoundID = "";
        public String getCompoundID(){
              return _CompoundID;
         }
        public void setCompoundID(String newValue){
              _CompoundID = newValue;
         }
        private String _LMName = "";
        public String getLMName(){
              return _LMName;
         }
        public void setLMName(String newValue){
              _LMName = newValue;
         }
        private String _LMType = "";
        public String getLMType(){
              return _LMType;
         }
        public void setLMType(String newValue){
              _LMType = newValue;
         }

         private String _LMTypeOth = "";
         public String getLMTypeOth(){
             return _LMTypeOth;
         }
         public void setLMTypeOth(String newValue){
             _LMTypeOth = newValue;
         }

        private String _LMSl = "";
        public String getLMSl(){
              return _LMSl;
         }
        public void setLMSl(String newValue){
              _LMSl = newValue;
         }
        private String _Latitude = "";
        public String getLatitude(){
              return _Latitude;
         }
        public void setLatitude(String newValue){
              _Latitude = newValue;
         }
        private String _Longitude = "";
        public String getLongitude(){
              return _Longitude;
         }
        public void setLongitude(String newValue){
              _Longitude = newValue;
         }
        private String _Altitude = "";
        public String getAltitude(){
              return _Altitude;
         }
        public void setAltitude(String newValue){
              _Altitude = newValue;
         }
        private String _Accuracy = "";
        public String getAccuracy(){
              return _Accuracy;
         }
        public void setAccuracy(String newValue){
              _Accuracy = newValue;
         }
        private String _Satelites = "";
        public String getSatelites(){
              return _Satelites;
         }
        public void setSatelites(String newValue){
              _Satelites = newValue;
         }
        private String _GPSStatus = "";
        public String getGPSStatus(){
              return _GPSStatus;
         }
        public void setGPSStatus(String newValue){
              _GPSStatus = newValue;
         }
     private String _GPSStatusReason = "";
     public String getGPSStatusReason(){
         return _GPSStatusReason;
     }
     public void setGPSStatusReason(String newValue){
         _GPSStatusReason = newValue;
     }
        private String _GPSNote = "";
        public String getGPSNote(){
              return _GPSNote;
         }
        public void setGPSNote(String newValue){
              _GPSNote = newValue;
         }
        private String _Rnd = "";
        public String getRnd(){
              return _Rnd;
         }
        public void setRnd(String newValue){
              _Rnd = newValue;
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

        String TableName = "GPS";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where IDNo='"+ _IDNo +"' and VillID='"+ _VillID +"' "))
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
                 contentValues.put("IDNo", _IDNo);
                 contentValues.put("VillID", _VillID);
                 contentValues.put("GPSType", _GPSType);
                 contentValues.put("CompoundID", _CompoundID);
                 contentValues.put("LMName", _LMName);
                 contentValues.put("LMType", _LMType);
                 contentValues.put("LMTypeOth", _LMTypeOth);
                 contentValues.put("LMSl", _LMSl);
                 contentValues.put("Latitude", _Latitude);
                 contentValues.put("Longitude", _Longitude);
                 contentValues.put("Altitude", _Altitude);
                 contentValues.put("Accuracy", _Accuracy);
                 contentValues.put("Satelites", _Satelites);
                 contentValues.put("GPSStatus", _GPSStatus);
                  contentValues.put("GPSStatusReason", _GPSStatusReason);
                 contentValues.put("GPSNote", _GPSNote);
                 contentValues.put("Rnd", _Rnd);
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
                 contentValues.put("IDNo", _IDNo);
                 contentValues.put("VillID", _VillID);
                 contentValues.put("GPSType", _GPSType);
                 contentValues.put("CompoundID", _CompoundID);
                 contentValues.put("LMName", _LMName);
                 contentValues.put("LMType", _LMType);
                 contentValues.put("LMTypeOth", _LMTypeOth);
                 contentValues.put("LMSl", _LMSl);
                 contentValues.put("Latitude", _Latitude);
                 contentValues.put("Longitude", _Longitude);
                 contentValues.put("Altitude", _Altitude);
                 contentValues.put("Accuracy", _Accuracy);
                 contentValues.put("Satelites", _Satelites);
                 contentValues.put("GPSStatus", _GPSStatus);
                  contentValues.put("GPSStatusReason", _GPSStatusReason);
                 contentValues.put("GPSNote", _GPSNote);
                 contentValues.put("Rnd", _Rnd);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "IDNo,VillID", (""+ _IDNo +", "+ _VillID +""), contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        @SuppressLint("Range")
        public List<GPS_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<GPS_DataModel> data = new ArrayList<GPS_DataModel>();
            GPS_DataModel d = new GPS_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new GPS_DataModel();
                d._Count = Count;
                d._IDNo = cur.getString(cur.getColumnIndex("IDNo"));
                d._VillID = cur.getString(cur.getColumnIndex("VillID"));
                d._GPSType = cur.getString(cur.getColumnIndex("GPSType"));
                d._CompoundID = cur.getString(cur.getColumnIndex("CompoundID"));
                d._LMName = cur.getString(cur.getColumnIndex("LMName"));
                d._LMType = cur.getString(cur.getColumnIndex("LMType"));
                d._LMTypeOth = cur.getString(cur.getColumnIndex("LMTypeOth"));
                d._LMSl = cur.getString(cur.getColumnIndex("LMSl"));
                d._Latitude = cur.getString(cur.getColumnIndex("Latitude"));
                d._Longitude = cur.getString(cur.getColumnIndex("Longitude"));
                d._Altitude = cur.getString(cur.getColumnIndex("Altitude"));
                d._Accuracy = cur.getString(cur.getColumnIndex("Accuracy"));
                d._Satelites = cur.getString(cur.getColumnIndex("Satelites"));
                d._GPSStatus = cur.getString(cur.getColumnIndex("GPSStatus"));
                d._GPSStatusReason = cur.getString(cur.getColumnIndex("GPSStatusReason"));
                d._GPSNote = cur.getString(cur.getColumnIndex("GPSNote"));
                d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }

     private String _TypeName = "";
     public String getTypeName(){
         return _TypeName;
     }
     public List<GPS_DataModel> SelectAllList(Context context, String SQL)
     {
         Connection C = new Connection(context);
         List<GPS_DataModel> data = new ArrayList<GPS_DataModel>();
         GPS_DataModel d = new GPS_DataModel();
         Cursor cur = C.ReadData(SQL);

         cur.moveToFirst();
         while(!cur.isAfterLast())
         {
             Count += 1;
             d = new GPS_DataModel();
             d._Count = Count;
             d._IDNo = cur.getString(cur.getColumnIndex("IDNo"));
             d._VillID = cur.getString(cur.getColumnIndex("VillID"));
             d._GPSType = cur.getString(cur.getColumnIndex("GPSType"));
             d._CompoundID = cur.getString(cur.getColumnIndex("CompoundID"));
             d._LMName = cur.getString(cur.getColumnIndex("LMName"));
             d._LMType = cur.getString(cur.getColumnIndex("LMType"));
             d._LMSl = cur.getString(cur.getColumnIndex("LMSl"));
             d._Latitude = cur.getString(cur.getColumnIndex("Latitude"));
             d._Longitude = cur.getString(cur.getColumnIndex("Longitude"));
             d._Altitude = cur.getString(cur.getColumnIndex("Altitude"));
             d._Accuracy = cur.getString(cur.getColumnIndex("Accuracy"));
             d._Satelites = cur.getString(cur.getColumnIndex("Satelites"));
             d._GPSStatus = cur.getString(cur.getColumnIndex("GPSStatus"));
             d._GPSNote = cur.getString(cur.getColumnIndex("GPSNote"));
             d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
             d._TypeName = cur.getString(cur.getColumnIndex("TypeName"));
             data.add(d);

             cur.moveToNext();
         }
         cur.close();
         return data;
     }
 }