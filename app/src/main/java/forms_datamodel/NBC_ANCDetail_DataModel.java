package forms_datamodel;

import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import Common.Global;
 import android.content.ContentValues;
 public class NBC_ANCDetail_DataModel{

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
        private String _ANCSl = "";
        public String getANCSl(){
              return _ANCSl;
         }
        public void setANCSl(String newValue){
              _ANCSl = newValue;
         }
        private String _ANCDate = "";
        public String getANCDate(){
              return _ANCDate;
         }
        public void setANCDate(String newValue){
              _ANCDate = newValue;
         }
        private String _ANCDateDk = "";
        public String getANCDateDk(){
              return _ANCDateDk;
         }
        public void setANCDateDk(String newValue){
              _ANCDateDk = newValue;
         }
        private String _ANCGAge = "";
        public String getANCGAge(){
              return _ANCGAge;
         }
        public void setANCGAge(String newValue){
              _ANCGAge = newValue;
         }
        private String _ANCProv = "";
        public String getANCProv(){
              return _ANCProv;
         }
        public void setANCProv(String newValue){
              _ANCProv = newValue;
         }
        private String _ANCProvOth = "";
        public String getANCProvOth(){
              return _ANCProvOth;
         }
        public void setANCProvOth(String newValue){
              _ANCProvOth = newValue;
         }
        private String _ANCPlace = "";
        public String getANCPlace(){
              return _ANCPlace;
         }
        public void setANCPlace(String newValue){
              _ANCPlace = newValue;
         }
        private String _ANCPlaceOth = "";
        public String getANCPlaceOth(){
              return _ANCPlaceOth;
         }
        public void setANCPlaceOth(String newValue){
              _ANCPlaceOth = newValue;
         }
        private String _ANCRes = "";
        public String getANCRes(){
              return _ANCRes;
         }
        public void setANCRes(String newValue){
              _ANCRes = newValue;
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

        String TableName = "NBC_ANCDetail";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where NBID='"+ _NBID +"' and PGN='"+ _PGN +"' and ANCSl='"+ _ANCSl +"' "))
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
                 contentValues.put("ANCSl", _ANCSl);
                 contentValues.put("ANCDate", _ANCDate);
                 contentValues.put("ANCDateDk", _ANCDateDk);
                 contentValues.put("ANCGAge", _ANCGAge);
                 contentValues.put("ANCProv", _ANCProv);
                 contentValues.put("ANCProvOth", _ANCProvOth);
                 contentValues.put("ANCPlace", _ANCPlace);
                 contentValues.put("ANCPlaceOth", _ANCPlaceOth);
                 contentValues.put("ANCRes", _ANCRes);
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
                 contentValues.put("ANCSl", _ANCSl);
                 contentValues.put("ANCDate", _ANCDate);
                 contentValues.put("ANCDateDk", _ANCDateDk);
                 contentValues.put("ANCGAge", _ANCGAge);
                 contentValues.put("ANCProv", _ANCProv);
                 contentValues.put("ANCProvOth", _ANCProvOth);
                 contentValues.put("ANCPlace", _ANCPlace);
                 contentValues.put("ANCPlaceOth", _ANCPlaceOth);
                 contentValues.put("ANCRes", _ANCRes);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "NBID,PGN,ANCSl", (""+ _NBID +", "+ _PGN +", "+ _ANCSl +""), contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        public List<NBC_ANCDetail_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<NBC_ANCDetail_DataModel> data = new ArrayList<NBC_ANCDetail_DataModel>();
            NBC_ANCDetail_DataModel d = new NBC_ANCDetail_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new NBC_ANCDetail_DataModel();
                d._Count = Count;
                d._NBID = cur.getString(cur.getColumnIndex("NBID"));
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._PGN = cur.getString(cur.getColumnIndex("PGN"));
                d._ANCSl = cur.getString(cur.getColumnIndex("ANCSl"));
                d._ANCDate = cur.getString(cur.getColumnIndex("ANCDate"));
                d._ANCDateDk = cur.getString(cur.getColumnIndex("ANCDateDk"));
                d._ANCGAge = cur.getString(cur.getColumnIndex("ANCGAge"));
                d._ANCProv = cur.getString(cur.getColumnIndex("ANCProv"));
                d._ANCProvOth = cur.getString(cur.getColumnIndex("ANCProvOth"));
                d._ANCPlace = cur.getString(cur.getColumnIndex("ANCPlace"));
                d._ANCPlaceOth = cur.getString(cur.getColumnIndex("ANCPlaceOth"));
                d._ANCRes = cur.getString(cur.getColumnIndex("ANCRes"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }
 }