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
 public class Vaccination_DataModel{

        private String _VaccID = "";
        public String getVaccID(){
              return _VaccID;
         }
        public void setVaccID(String newValue){
              _VaccID = newValue;
         }
        private String _HHID = "";
        public String getHHID(){
              return _HHID;
         }
        public void setHHID(String newValue){
              _HHID = newValue;
         }
        private String _MoSlNo = "";
        public String getMoSlNo(){
              return _MoSlNo;
         }
        public void setMoSlNo(String newValue){
              _MoSlNo = newValue;
         }
        private String _MemID = "";
        public String getMemID(){
              return _MemID;
         }
        public void setMemID(String newValue){
              _MemID = newValue;
         }
        private String _VaccCode = "";
        public String getVaccCode(){
              return _VaccCode;
         }
        public void setVaccCode(String newValue){
              _VaccCode = newValue;
         }
        private String _VaccCodeOth1 = "";
        public String getVaccCodeOth1(){
              return _VaccCodeOth1;
         }
        public void setVaccCodeOth1(String newValue){
              _VaccCodeOth1 = newValue;
         }
        private String _VaccCodeOth2 = "";
        public String getVaccCodeOth2(){
              return _VaccCodeOth2;
         }
        public void setVaccCodeOth2(String newValue){
              _VaccCodeOth2 = newValue;
         }
        private String _Response = "";
        public String getResponse(){
              return _Response;
         }
        public void setResponse(String newValue){
              _Response = newValue;
         }
        private String _ResponseOth = "";
        public String getResponseOth(){
              return _ResponseOth;
         }
        public void setResponseOth(String newValue){
              _ResponseOth = newValue;
         }
        private String _VaccDate = "";
        public String getVaccDate(){
              return _VaccDate;
         }
        public void setVaccDate(String newValue){
              _VaccDate = newValue;
         }
        private String _VaccDateDK = "";
        public String getVaccDateDK(){
              return _VaccDateDK;
         }
        public void setVaccDateDK(String newValue){
              _VaccDateDK = newValue;
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

        String TableName = "Vaccination";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where MemID='"+ _MemID +"' and VaccCode='"+ _VaccCode +"' "))
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
                 contentValues.put("VaccID", _VaccID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("MoSlNo", _MoSlNo);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("VaccCode", _VaccCode);
                 contentValues.put("VaccCodeOth1", _VaccCodeOth1);
                 contentValues.put("VaccCodeOth2", _VaccCodeOth2);
                 contentValues.put("Response", _Response);
                 contentValues.put("ResponseOth", _ResponseOth);
                 contentValues.put("VaccDate", _VaccDate);
                 contentValues.put("VaccDateDK", _VaccDateDK);
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
                 contentValues.put("VaccID", _VaccID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("MoSlNo", _MoSlNo);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("VaccCode", _VaccCode);
                 contentValues.put("VaccCodeOth1", _VaccCodeOth1);
                 contentValues.put("VaccCodeOth2", _VaccCodeOth2);
                 contentValues.put("Response", _Response);
                 contentValues.put("ResponseOth", _ResponseOth);
                 contentValues.put("VaccDate", _VaccDate);
                 contentValues.put("VaccDateDK", _VaccDateDK);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "MemID,VaccCode", (""+ _MemID +","+ _VaccCode +""), contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        @SuppressLint("Range")
        public List<Vaccination_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<Vaccination_DataModel> data = new ArrayList<Vaccination_DataModel>();
            Vaccination_DataModel d = new Vaccination_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new Vaccination_DataModel();
                d._Count = Count;
                d._VaccID = cur.getString(cur.getColumnIndex("VaccID"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._MoSlNo = cur.getString(cur.getColumnIndex("MoSlNo"));
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._VaccCode = cur.getString(cur.getColumnIndex("VaccCode"));
                d._VaccCodeOth1 = cur.getString(cur.getColumnIndex("VaccCodeOth1"));
                d._VaccCodeOth2 = cur.getString(cur.getColumnIndex("VaccCodeOth2"));
                d._Response = cur.getString(cur.getColumnIndex("Response"));
                d._ResponseOth = cur.getString(cur.getColumnIndex("ResponseOth"));
                d._VaccDate = cur.getString(cur.getColumnIndex("VaccDate"));
                d._VaccDateDK = cur.getString(cur.getColumnIndex("VaccDateDK"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }

     @SuppressLint("Range")
     public List<Vaccination_DataModel> SelectVaccAll(Context context, String SQL)
     {
         Connection C = new Connection(context);
         List<Vaccination_DataModel> data = new ArrayList<Vaccination_DataModel>();
         Vaccination_DataModel d = new Vaccination_DataModel();
         Cursor cur = C.ReadData(SQL);

         cur.moveToFirst();
         while(!cur.isAfterLast())
         {
             Count += 1;
             d = new Vaccination_DataModel();
             d._Count = Count;
//             d._VaccID = cur.getString(cur.getColumnIndex("VaccID"));
//             d._HHID = cur.getString(cur.getColumnIndex("HHID"));
//             d._MoSlNo = cur.getString(cur.getColumnIndex("MoSlNo"));
//             d._MemID = cur.getString(cur.getColumnIndex("MemID"));
             d._VaccCode = cur.getString(cur.getColumnIndex("VaccCode"));
             d._VaccName = cur.getString(cur.getColumnIndex("VaccName"));
//             d._VaccCodeOth2 = cur.getString(cur.getColumnIndex("VaccCodeOth2"));
//             d._Response = cur.getString(cur.getColumnIndex("Response"));
//             d._ResponseOth = cur.getString(cur.getColumnIndex("ResponseOth"));
//             d._VaccDate = cur.getString(cur.getColumnIndex("VaccDate"));
//             d._VaccDateDK = cur.getString(cur.getColumnIndex("VaccDateDK"));
             data.add(d);

             cur.moveToNext();
         }
         cur.close();
         return data;
     }


     private String _VaccName = "";
     public String getVaccName(){
         return _VaccName;
     }
     public void setVaccName(String newValue){
         _VaccName = newValue;
     }

     @SuppressLint("Range")
     public List<Vaccination_DataModel> SelectAll1(Context context, String SQL)
     {
         Connection C = new Connection(context);
         List<Vaccination_DataModel> data = new ArrayList<Vaccination_DataModel>();
         Vaccination_DataModel d = new Vaccination_DataModel();
         Cursor cur = C.ReadData(SQL);

         cur.moveToFirst();
         while(!cur.isAfterLast())
         {
             Count += 1;
             d = new Vaccination_DataModel();
             d._Count = Count;
//
             d._VaccCode = cur.getString(cur.getColumnIndex("VaccCode"));
             d._VaccName = cur.getString(cur.getColumnIndex("VaccName"));
//
             data.add(d);

             cur.moveToNext();
         }
         cur.close();
         return data;
     }

 }