package forms_datamodel;

import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import Common.Global;
 import android.content.ContentValues;
 public class PregDetail_DataModel{

        private String _MemID = "";
        public String getMemID(){
              return _MemID;
         }
        public void setMemID(String newValue){
              _MemID = newValue;
         }
        private String _OutSl = "";
        public String getOutSl(){
              return _OutSl;
         }
        public void setOutSl(String newValue){
              _OutSl = newValue;
         }
        private String _HHID = "";
        public String getHHID(){
              return _HHID;
         }
        public void setHHID(String newValue){
              _HHID = newValue;
         }
        private String _MSlNo = "";
        public String getMSlNo(){
              return _MSlNo;
         }
        public void setMSlNo(String newValue){
              _MSlNo = newValue;
         }
        private String _PregNo = "";
        public String getPregNo(){
              return _PregNo;
         }
        public void setPregNo(String newValue){
              _PregNo = newValue;
         }
        private String _OutDate = "";
        public String getOutDate(){
              return _OutDate;
         }
        public void setOutDate(String newValue){
              _OutDate = newValue;
         }
        private String _LMP = "";
        public String getLMP(){
              return _LMP;
         }
        public void setLMP(String newValue){
              _LMP = newValue;
         }
        private String _LMPDk = "";
        public String getLMPDk(){
              return _LMPDk;
         }
        public void setLMPDk(String newValue){
              _LMPDk = newValue;
         }
        private String _Result = "";
        public String getResult(){
              return _Result;
         }
        public void setResult(String newValue){
              _Result = newValue;
         }
        private String _Cry = "";
        public String getCry(){
              return _Cry;
         }
        public void setCry(String newValue){
              _Cry = newValue;
         }
        private String _DelMode = "";
        public String getDelMode(){
              return _DelMode;
         }
        public void setDelMode(String newValue){
              _DelMode = newValue;
         }
        private String _DelModeOth = "";
        public String getDelModeOth(){
              return _DelModeOth;
         }
        public void setDelModeOth(String newValue){
              _DelModeOth = newValue;
         }
        private String _Name = "";
        public String getName(){
              return _Name;
         }
        public void setName(String newValue){
              _Name = newValue;
         }
        private String _Sex = "";
        public String getSex(){
              return _Sex;
         }
        public void setSex(String newValue){
              _Sex = newValue;
         }
        private String _Alive = "";
        public String getAlive(){
              return _Alive;
         }
        public void setAlive(String newValue){
              _Alive = newValue;
         }
        private String _AgeY = "";
        public String getAgeY(){
              return _AgeY;
         }
        public void setAgeY(String newValue){
              _AgeY = newValue;
         }
        private String _DAgeU = "";
        public String getDAgeU(){
              return _DAgeU;
         }
        public void setDAgeU(String newValue){
              _DAgeU = newValue;
         }
        private String _DAge = "";
        public String getDAge(){
              return _DAge;
         }
        public void setDAge(String newValue){
              _DAge = newValue;
         }
        private String _DAgeD = "";
        public String getDAgeD(){
              return _DAgeD;
         }
        public void setDAgeD(String newValue){
              _DAgeD = newValue;
         }
        private String _OthPreg = "";
        public String getOthPreg(){
              return _OthPreg;
         }
        public void setOthPreg(String newValue){
              _OthPreg = newValue;
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

        String TableName = "PregDetail";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where MemID='"+ _MemID +"' and OutSl='"+ _OutSl +"' "))
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
                 contentValues.put("MemID", _MemID);
                 contentValues.put("OutSl", _OutSl);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("MSlNo", _MSlNo);
                 contentValues.put("PregNo", _PregNo);
                 contentValues.put("OutDate", _OutDate);
                 contentValues.put("LMP", _LMP);
                 contentValues.put("LMPDk", _LMPDk);
                 contentValues.put("Result", _Result);
                 contentValues.put("Cry", _Cry);
                 contentValues.put("DelMode", _DelMode);
                 contentValues.put("DelModeOth", _DelModeOth);
                 contentValues.put("Name", _Name);
                 contentValues.put("Sex", _Sex);
                 contentValues.put("Alive", _Alive);
                 contentValues.put("AgeY", _AgeY);
                 contentValues.put("DAgeU", _DAgeU);
                 contentValues.put("DAge", _DAge);
                 contentValues.put("DAgeD", _DAgeD);
                 contentValues.put("OthPreg", _OthPreg);
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
                 contentValues.put("MemID", _MemID);
                 contentValues.put("OutSl", _OutSl);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("MSlNo", _MSlNo);
                 contentValues.put("PregNo", _PregNo);
                 contentValues.put("OutDate", _OutDate);
                 contentValues.put("LMP", _LMP);
                 contentValues.put("LMPDk", _LMPDk);
                 contentValues.put("Result", _Result);
                 contentValues.put("Cry", _Cry);
                 contentValues.put("DelMode", _DelMode);
                 contentValues.put("DelModeOth", _DelModeOth);
                 contentValues.put("Name", _Name);
                 contentValues.put("Sex", _Sex);
                 contentValues.put("Alive", _Alive);
                 contentValues.put("AgeY", _AgeY);
                 contentValues.put("DAgeU", _DAgeU);
                 contentValues.put("DAge", _DAge);
                 contentValues.put("DAgeD", _DAgeD);
                 contentValues.put("OthPreg", _OthPreg);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "MemID,OutSl", (""+ _MemID +", "+ _OutSl +""), contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        public List<PregDetail_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<PregDetail_DataModel> data = new ArrayList<PregDetail_DataModel>();
            PregDetail_DataModel d = new PregDetail_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new PregDetail_DataModel();
                d._Count = Count;
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._OutSl = cur.getString(cur.getColumnIndex("OutSl"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._MSlNo = cur.getString(cur.getColumnIndex("MSlNo"));
                d._PregNo = cur.getString(cur.getColumnIndex("PregNo"));
                d._OutDate = cur.getString(cur.getColumnIndex("OutDate"));
                d._LMP = cur.getString(cur.getColumnIndex("LMP"));
                d._LMPDk = cur.getString(cur.getColumnIndex("LMPDk"));
                d._Result = cur.getString(cur.getColumnIndex("Result"));
                d._Cry = cur.getString(cur.getColumnIndex("Cry"));
                d._DelMode = cur.getString(cur.getColumnIndex("DelMode"));
                d._DelModeOth = cur.getString(cur.getColumnIndex("DelModeOth"));
                d._Name = cur.getString(cur.getColumnIndex("Name"));
                d._Sex = cur.getString(cur.getColumnIndex("Sex"));
                d._Alive = cur.getString(cur.getColumnIndex("Alive"));
                d._AgeY = cur.getString(cur.getColumnIndex("AgeY"));
                d._DAgeU = cur.getString(cur.getColumnIndex("DAgeU"));
                d._DAge = cur.getString(cur.getColumnIndex("DAge"));
                d._DAgeD = cur.getString(cur.getColumnIndex("DAgeD"));
                d._OthPreg = cur.getString(cur.getColumnIndex("OthPreg"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }
 }