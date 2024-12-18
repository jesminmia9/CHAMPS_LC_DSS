package forms_datamodel;

import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import Common.Global;
 import android.content.ContentValues;
 public class Delivery_DataModel{

     private String _PregOccurID = "";
     public String getPregOccurID(){
         return _PregOccurID;
     }
     public void setPregOccurID(String newValue){
         _PregOccurID = newValue;
     }
        private String _DeliveryID = "";
        public String getDeliveryID(){
              return _DeliveryID;
         }
        public void setDeliveryID(String newValue){
              _DeliveryID = newValue;
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
        private String _DelDate = "";
        public String getDelDate(){
              return _DelDate;
         }
        public void setDelDate(String newValue){
              _DelDate = newValue;
         }
        private String _DelDateType = "";
        public String getDelDateType(){
              return _DelDateType;
         }
        public void setDelDateType(String newValue){
              _DelDateType = newValue;
         }
        private String _DelTime = "";
        public String getDelTime(){
              return _DelTime;
         }
        public void setDelTime(String newValue){
              _DelTime = newValue;
         }
        private String _DelTimeType = "";
        public String getDelTimeType(){
              return _DelTimeType;
         }
        public void setDelTimeType(String newValue){
              _DelTimeType = newValue;
         }
        private String _TotOut = "";
        public String getTotOut(){
              return _TotOut;
         }
        public void setTotOut(String newValue){
              _TotOut = newValue;
         }
        private String _TotLB = "";
        public String getTotLB(){
              return _TotLB;
         }
        public void setTotLB(String newValue){
              _TotLB = newValue;
         }
        private String _TotMis = "";
        public String getTotMis(){
              return _TotMis;
         }
        public void setTotMis(String newValue){
              _TotMis = newValue;
         }
        private String _TotSB = "";
        public String getTotSB(){
              return _TotSB;
         }
        public void setTotSB(String newValue){
              _TotSB = newValue;
         }
        private String _TotAB = "";
        public String getTotAB(){
              return _TotAB;
         }
        public void setTotAB(String newValue){
              _TotAB = newValue;
         }
        private String _DelVDate = "";
        public String getDelVDate(){
              return _DelVDate;
         }
        public void setDelVDate(String newValue){
              _DelVDate = newValue;
         }
        private String _Rnd = "";
        public String getRnd(){
              return _Rnd;
         }
        public void setRnd(String newValue){
              _Rnd = newValue;
         }
        private String _DelNote = "";
        public String getDelNote(){
              return _DelNote;
         }
        public void setDelNote(String newValue){
              _DelNote = newValue;
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

        String TableName = "Delivery";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where DeliveryID='"+ _DeliveryID +"' "))
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
                  contentValues.put("PregOccurID", _PregOccurID);
                 contentValues.put("DeliveryID", _DeliveryID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("DelDate", _DelDate);
                 contentValues.put("DelDateType", _DelDateType);
                 contentValues.put("DelTime", _DelTime);
                 contentValues.put("DelTimeType", _DelTimeType);
                 contentValues.put("TotOut", _TotOut);
                 contentValues.put("TotLB", _TotLB);
                 contentValues.put("TotMis", _TotMis);
                 contentValues.put("TotSB", _TotSB);
                 contentValues.put("TotAB", _TotAB);
                 contentValues.put("DelVDate", _DelVDate);
                 contentValues.put("Rnd", _Rnd);
                 contentValues.put("DelNote", _DelNote);
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
                  contentValues.put("PregOccurID", _PregOccurID);
                 contentValues.put("DeliveryID", _DeliveryID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("DelDate", _DelDate);
                 contentValues.put("DelDateType", _DelDateType);
                 contentValues.put("DelTime", _DelTime);
                 contentValues.put("DelTimeType", _DelTimeType);
                 contentValues.put("TotOut", _TotOut);
                 contentValues.put("TotLB", _TotLB);
                 contentValues.put("TotMis", _TotMis);
                 contentValues.put("TotSB", _TotSB);
                 contentValues.put("TotAB", _TotAB);
                 contentValues.put("DelVDate", _DelVDate);
                 contentValues.put("Rnd", _Rnd);
                 contentValues.put("DelNote", _DelNote);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "DeliveryID", (""+ _DeliveryID +""), contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        public List<Delivery_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<Delivery_DataModel> data = new ArrayList<Delivery_DataModel>();
            Delivery_DataModel d = new Delivery_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new Delivery_DataModel();
                d._Count = Count;
                d._PregOccurID = cur.getString(cur.getColumnIndex("PregOccurID"));
                d._DeliveryID = cur.getString(cur.getColumnIndex("DeliveryID"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._DelDate = cur.getString(cur.getColumnIndex("DelDate"));
                d._DelDateType = cur.getString(cur.getColumnIndex("DelDateType"));
                d._DelTime = cur.getString(cur.getColumnIndex("DelTime"));
                d._DelTimeType = cur.getString(cur.getColumnIndex("DelTimeType"));
                d._TotOut = cur.getString(cur.getColumnIndex("TotOut"));
                d._TotLB = cur.getString(cur.getColumnIndex("TotLB"));
                d._TotMis = cur.getString(cur.getColumnIndex("TotMis"));
                d._TotSB = cur.getString(cur.getColumnIndex("TotSB"));
                d._TotAB = cur.getString(cur.getColumnIndex("TotAB"));
                d._DelVDate = cur.getString(cur.getColumnIndex("DelVDate"));
                d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
                d._DelNote = cur.getString(cur.getColumnIndex("DelNote"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }
 }