package forms_datamodel;

import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import Common.Global;
 import android.content.ContentValues;
 public class NBC_PNCMothDetail_DataModel{

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
        private String _PNCSl = "";
        public String getPNCSl(){
              return _PNCSl;
         }
        public void setPNCSl(String newValue){
              _PNCSl = newValue;
         }
        private String _PNCDate = "";
        public String getPNCDate(){
              return _PNCDate;
         }
        public void setPNCDate(String newValue){
              _PNCDate = newValue;
         }
        private String _PNCDateDk = "";
        public String getPNCDateDk(){
              return _PNCDateDk;
         }
        public void setPNCDateDk(String newValue){
              _PNCDateDk = newValue;
         }
        private String _PNCProv = "";
        public String getPNCProv(){
              return _PNCProv;
         }
        public void setPNCProv(String newValue){
              _PNCProv = newValue;
         }
        private String _PNCProvOth = "";
        public String getPNCProvOth(){
              return _PNCProvOth;
         }
        public void setPNCProvOth(String newValue){
              _PNCProvOth = newValue;
         }
        private String _PNCPlace = "";
        public String getPNCPlace(){
              return _PNCPlace;
         }
        public void setPNCPlace(String newValue){
              _PNCPlace = newValue;
         }
        private String _PNCPlaceOth = "";
        public String getPNCPlaceOth(){
              return _PNCPlaceOth;
         }
        public void setPNCPlaceOth(String newValue){
              _PNCPlaceOth = newValue;
         }
        private String _PNCRes = "";
        public String getPNCRes(){
              return _PNCRes;
         }
        public void setPNCRes(String newValue){
              _PNCRes = newValue;
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

        String TableName = "NBC_PNCMothDetail";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where NBID='"+ _NBID +"' and PGN='"+ _PGN +"' and PNCSl='"+ _PNCSl +"' "))
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
                 contentValues.put("PNCSl", _PNCSl);
                 contentValues.put("PNCDate", _PNCDate);
                 contentValues.put("PNCDateDk", _PNCDateDk);
                 contentValues.put("PNCProv", _PNCProv);
                 contentValues.put("PNCProvOth", _PNCProvOth);
                 contentValues.put("PNCPlace", _PNCPlace);
                 contentValues.put("PNCPlaceOth", _PNCPlaceOth);
                 contentValues.put("PNCRes", _PNCRes);
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
                 contentValues.put("PNCSl", _PNCSl);
                 contentValues.put("PNCDate", _PNCDate);
                 contentValues.put("PNCDateDk", _PNCDateDk);
                 contentValues.put("PNCProv", _PNCProv);
                 contentValues.put("PNCProvOth", _PNCProvOth);
                 contentValues.put("PNCPlace", _PNCPlace);
                 contentValues.put("PNCPlaceOth", _PNCPlaceOth);
                 contentValues.put("PNCRes", _PNCRes);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "NBID,PGN,PNCSl", (""+ _NBID +", "+ _PGN +", "+ _PNCSl +""), contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        public List<NBC_PNCMothDetail_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<NBC_PNCMothDetail_DataModel> data = new ArrayList<NBC_PNCMothDetail_DataModel>();
            NBC_PNCMothDetail_DataModel d = new NBC_PNCMothDetail_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new NBC_PNCMothDetail_DataModel();
                d._Count = Count;
                d._NBID = cur.getString(cur.getColumnIndex("NBID"));
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._PGN = cur.getString(cur.getColumnIndex("PGN"));
                d._PNCSl = cur.getString(cur.getColumnIndex("PNCSl"));
                d._PNCDate = cur.getString(cur.getColumnIndex("PNCDate"));
                d._PNCDateDk = cur.getString(cur.getColumnIndex("PNCDateDk"));
                d._PNCProv = cur.getString(cur.getColumnIndex("PNCProv"));
                d._PNCProvOth = cur.getString(cur.getColumnIndex("PNCProvOth"));
                d._PNCPlace = cur.getString(cur.getColumnIndex("PNCPlace"));
                d._PNCPlaceOth = cur.getString(cur.getColumnIndex("PNCPlaceOth"));
                d._PNCRes = cur.getString(cur.getColumnIndex("PNCRes"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }
 }