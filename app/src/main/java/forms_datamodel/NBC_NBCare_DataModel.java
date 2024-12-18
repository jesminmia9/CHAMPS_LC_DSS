package forms_datamodel;

import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import Common.Global;
 import android.content.ContentValues;
import android.util.Log;

public class NBC_NBCare_DataModel{

        private String _NBID = "";
        public String getNBID(){
              return _NBID;
         }
        public void setNBID(String newValue){
              _NBID = newValue;
         }
        private String _MemID = "";
        public String getMemID(){
              return _MemID;
         }
        public void setMemID(String newValue){
              _MemID = newValue;
         }
        private String _HHID = "";
        public String getHHID(){
              return _HHID;
         }
        public void setHHID(String newValue){
              _HHID = newValue;
         }
        private String _PGN = "";
        public String getPGN(){
              return _PGN;
         }
        public void setPGN(String newValue){
              _PGN = newValue;
         }
        private String _PregLVisit = "";
        public String getPregLVisit(){
              return _PregLVisit;
         }
        public void setPregLVisit(String newValue){
              _PregLVisit = newValue;
         }
        private String _OutcomeType = "";
        public String getOutcomeType(){
              return _OutcomeType;
         }
        public void setOutcomeType(String newValue){
              _OutcomeType = newValue;
         }
        private String _BirthNo = "";
        public String getBirthNo(){
              return _BirthNo;
         }
        public void setBirthNo(String newValue){
              _BirthNo = newValue;
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

        String TableName = "NBC_NBCare";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where NBID='"+ _NBID +"' and PGN='"+ _PGN +"' "))
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
                 contentValues.put("NBID", _NBID);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("PGN", _PGN);
                 contentValues.put("PregLVisit", _PregLVisit);
                 contentValues.put("OutcomeType", _OutcomeType);
                 contentValues.put("BirthNo", _BirthNo);
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
                 contentValues.put("NBID", _NBID);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("PGN", _PGN);
                 contentValues.put("PregLVisit", _PregLVisit);
                 contentValues.put("OutcomeType", _OutcomeType);
                 contentValues.put("BirthNo", _BirthNo);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "NBID,PGN", (""+ _NBID +", "+ _PGN +""), contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
     private String _PregType = "";
     public String getPregType(){
         return _PregType;
     }
     public void setPregType(String newValue){
         _PregType = newValue;
     }

        public List<NBC_NBCare_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<NBC_NBCare_DataModel> data = new ArrayList<NBC_NBCare_DataModel>();
            NBC_NBCare_DataModel d = new NBC_NBCare_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new NBC_NBCare_DataModel();
                d._Count = Count;
                d._NBID = cur.getString(cur.getColumnIndex("NBID"));
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._PGN = cur.getString(cur.getColumnIndex("PGN"));
                d._PregLVisit = cur.getString(cur.getColumnIndex("PregLVisit"));
                d._OutcomeType = cur.getString(cur.getColumnIndex("OutcomeType"));
                d._BirthNo = cur.getString(cur.getColumnIndex("BirthNo"));
//                d._PregType = cur.getString(cur.getColumnIndex("PregType"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }

     public List<NBC_NBCare_DataModel> SelectAllwithPregType(Context context, String SQL)
     {
         Log.d("BB", SQL);
         Connection C = new Connection(context);
         List<NBC_NBCare_DataModel> data = new ArrayList<NBC_NBCare_DataModel>();
         NBC_NBCare_DataModel d = new NBC_NBCare_DataModel();
         Cursor cur = C.ReadData(SQL);

         cur.moveToFirst();
         while(!cur.isAfterLast())
         {
             Count += 1;
             d = new NBC_NBCare_DataModel();
             d._Count = Count;
             d._NBID = cur.getString(cur.getColumnIndex("NBID"));
             d._MemID = cur.getString(cur.getColumnIndex("MemID"));
             d._HHID = cur.getString(cur.getColumnIndex("HHID"));
             d._PGN = cur.getString(cur.getColumnIndex("PGN"));
             d._PregLVisit = cur.getString(cur.getColumnIndex("PregLVisit"));
             d._OutcomeType = cur.getString(cur.getColumnIndex("OutcomeType"));
             d._BirthNo = cur.getString(cur.getColumnIndex("BirthNo"));
                d._PregType = cur.getString(cur.getColumnIndex("PregType"));
             data.add(d);

             cur.moveToNext();
         }
         cur.close();
         return data;
     }
 }