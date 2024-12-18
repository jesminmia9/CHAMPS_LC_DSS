package forms_datamodel;

import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import Common.Global;
 import android.content.ContentValues;
 public class NBC_ANC_DataModel{

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
        private String _Anc = "";
        public String getAnc(){
              return _Anc;
         }
        public void setAnc(String newValue){
              _Anc = newValue;
         }
        private String _AncNo = "";
        public String getAncNo(){
              return _AncNo;
         }
        public void setAncNo(String newValue){
              _AncNo = newValue;
         }
        private String _AncNoDk = "";
        public String getAncNoDk(){
              return _AncNoDk;
         }
        public void setAncNoDk(String newValue){
              _AncNoDk = newValue;
         }
        private String _AncCard = "";
        public String getAncCard(){
              return _AncCard;
         }
        public void setAncCard(String newValue){
              _AncCard = newValue;
         }
     private String _AncLackMoney = "";
     public String getAncLackMoney(){
         return _AncLackMoney;
     }
     public void setAncLackMoney(String newValue){
         _AncLackMoney = newValue;
     }
     private String _AncLackTransport = "";
     public String getAncLackTransport(){
         return _AncLackTransport;
     }
     public void setAncLackTransport(String newValue){
         _AncLackTransport = newValue;
     }
     private String _AncDistance = "";
     public String getAncDistance(){
         return _AncDistance;
     }
     public void setAncDistance(String newValue){
         _AncDistance = newValue;
     }
     private String _AncAttitude = "";
     public String getAncAttitude(){
         return _AncAttitude;
     }
     public void setAncAttitude(String newValue){
         _AncAttitude = newValue;
     }
     private String _AncPreference = "";
     public String getAncPreference(){
         return _AncPreference;
     }
     public void setAncPreference(String newValue){
         _AncPreference = newValue;
     }
     private String _AncRsnOth = "";
     public String getAncRsnOth(){
         return _AncRsnOth;
     }
     public void setAncRsnOth(String newValue){
         _AncRsnOth = newValue;
     }
     private String _AncRsnOthSp = "";
     public String getAncRsnOthSp(){
         return _AncRsnOthSp;
     }
     public void setAncRsnOthSp(String newValue){
         _AncRsnOthSp = newValue;
     }
     private String _AncRsnDk = "";
     public String getAncRsnDk(){
         return _AncRsnDk;
     }
     public void setAncRsnDk(String newValue){
         _AncRsnDk = newValue;
     }
     private String _AncRsnRefu = "";
     public String getAncRsnRefu(){
         return _AncRsnRefu;
     }
     public void setAncRsnRefu(String newValue){
         _AncRsnRefu = newValue;
     }
        private String _ANCPres = "";
        public String getANCPres(){
              return _ANCPres;
         }
        public void setANCPres(String newValue){
              _ANCPres = newValue;
         }
        private String _AncWeight = "";
        public String getAncWeight(){
              return _AncWeight;
         }
        public void setAncWeight(String newValue){
              _AncWeight = newValue;
         }
        private String _AncBp = "";
        public String getAncBp(){
              return _AncBp;
         }
        public void setAncBp(String newValue){
              _AncBp = newValue;
         }
        private String _AncUrine = "";
        public String getAncUrine(){
              return _AncUrine;
         }
        public void setAncUrine(String newValue){
              _AncUrine = newValue;
         }
        private String _AncBlood = "";
        public String getAncBlood(){
              return _AncBlood;
         }
        public void setAncBlood(String newValue){
              _AncBlood = newValue;
         }
        private String _AncHeartbeat = "";
        public String getAncHeartbeat(){
              return _AncHeartbeat;
         }
        public void setAncHeartbeat(String newValue){
              _AncHeartbeat = newValue;
         }
        private String _AncUSG = "";
        public String getAncUSG(){
              return _AncUSG;
         }
        public void setAncUSG(String newValue){
              _AncUSG = newValue;
         }
        private String _AncFood = "";
        public String getAncFood(){
              return _AncFood;
         }
        public void setAncFood(String newValue){
              _AncFood = newValue;
         }
        private String _ANCBF = "";
        public String getANCBF(){
              return _ANCBF;
         }
        public void setANCBF(String newValue){
              _ANCBF = newValue;
         }
        private String _ANCVBleed = "";
        public String getANCVBleed(){
              return _ANCVBleed;
         }
        public void setANCVBleed(String newValue){
              _ANCVBleed = newValue;
         }
        private String _ANCDSign = "";
        public String getANCDSign(){
              return _ANCDSign;
         }
        public void setANCDSign(String newValue){
              _ANCDSign = newValue;
         }
        private String _ANCFPMtd = "";
        public String getANCFPMtd(){
              return _ANCFPMtd;
         }
        public void setANCFPMtd(String newValue){
              _ANCFPMtd = newValue;
         }
        private String _ANCHCOth = "";
        public String getANCHCOth(){
              return _ANCHCOth;
         }
        public void setANCHCOth(String newValue){
              _ANCHCOth = newValue;
         }
        private String _ANCHCOthSp = "";
        public String getANCHCOthSp(){
              return _ANCHCOthSp;
         }
        public void setANCHCOthSp(String newValue){
              _ANCHCOthSp = newValue;
         }
        private String _AncHCDk = "";
        public String getAncHCDk(){
              return _AncHCDk;
         }
        public void setAncHCDk(String newValue){
              _AncHCDk = newValue;
         }
        private String _ANCRefu = "";
        public String getANCRefu(){
              return _ANCRefu;
         }
        public void setANCRefu(String newValue){
              _ANCRefu = newValue;
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

        String TableName = "NBC_ANC";

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
                 contentValues.put("Anc", _Anc);
                 contentValues.put("AncNo", _AncNo);
                 contentValues.put("AncNoDk", _AncNoDk);
                 contentValues.put("AncCard", _AncCard);
                  contentValues.put("AncLackMoney", _AncLackMoney);
                  contentValues.put("AncLackTransport", _AncLackTransport);
                  contentValues.put("AncDistance", _AncDistance);
                  contentValues.put("AncAttitude", _AncAttitude);
                  contentValues.put("AncPreference", _AncPreference);
                  contentValues.put("AncRsnOth", _AncRsnOth);
                  contentValues.put("AncRsnOthSp", _AncRsnOthSp);
                  contentValues.put("AncRsnDk", _AncRsnDk);
                  contentValues.put("AncRsnRefu", _AncRsnRefu);
                 contentValues.put("ANCPres", _ANCPres);
                 contentValues.put("AncWeight", _AncWeight);
                 contentValues.put("AncBp", _AncBp);
                 contentValues.put("AncUrine", _AncUrine);
                 contentValues.put("AncBlood", _AncBlood);
                 contentValues.put("AncHeartbeat", _AncHeartbeat);
                 contentValues.put("AncUSG", _AncUSG);
                 contentValues.put("AncFood", _AncFood);
                 contentValues.put("ANCBF", _ANCBF);
                 contentValues.put("ANCVBleed", _ANCVBleed);
                 contentValues.put("ANCDSign", _ANCDSign);
                 contentValues.put("ANCFPMtd", _ANCFPMtd);
                 contentValues.put("ANCHCOth", _ANCHCOth);
                 contentValues.put("ANCHCOthSp", _ANCHCOthSp);
                 contentValues.put("AncHCDk", _AncHCDk);
                 contentValues.put("ANCRefu", _ANCRefu);
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
                 contentValues.put("Anc", _Anc);
                 contentValues.put("AncNo", _AncNo);
                 contentValues.put("AncNoDk", _AncNoDk);
                 contentValues.put("AncCard", _AncCard);
                  contentValues.put("AncLackMoney", _AncLackMoney);
                  contentValues.put("AncLackTransport", _AncLackTransport);
                  contentValues.put("AncDistance", _AncDistance);
                  contentValues.put("AncAttitude", _AncAttitude);
                  contentValues.put("AncPreference", _AncPreference);
                  contentValues.put("AncRsnOth", _AncRsnOth);
                  contentValues.put("AncRsnOthSp", _AncRsnOthSp);
                  contentValues.put("AncRsnDk", _AncRsnDk);
                  contentValues.put("AncRsnRefu", _AncRsnRefu);
                 contentValues.put("ANCPres", _ANCPres);
                 contentValues.put("AncWeight", _AncWeight);
                 contentValues.put("AncBp", _AncBp);
                 contentValues.put("AncUrine", _AncUrine);
                 contentValues.put("AncBlood", _AncBlood);
                 contentValues.put("AncHeartbeat", _AncHeartbeat);
                 contentValues.put("AncUSG", _AncUSG);
                 contentValues.put("AncFood", _AncFood);
                 contentValues.put("ANCBF", _ANCBF);
                 contentValues.put("ANCVBleed", _ANCVBleed);
                 contentValues.put("ANCDSign", _ANCDSign);
                 contentValues.put("ANCFPMtd", _ANCFPMtd);
                 contentValues.put("ANCHCOth", _ANCHCOth);
                 contentValues.put("ANCHCOthSp", _ANCHCOthSp);
                 contentValues.put("AncHCDk", _AncHCDk);
                 contentValues.put("ANCRefu", _ANCRefu);
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
        public List<NBC_ANC_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<NBC_ANC_DataModel> data = new ArrayList<NBC_ANC_DataModel>();
            NBC_ANC_DataModel d = new NBC_ANC_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new NBC_ANC_DataModel();
                d._Count = Count;
                d._NBID = cur.getString(cur.getColumnIndex("NBID"));
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._PGN = cur.getString(cur.getColumnIndex("PGN"));
                d._Anc = cur.getString(cur.getColumnIndex("Anc"));
                d._AncNo = cur.getString(cur.getColumnIndex("AncNo"));
                d._AncNoDk = cur.getString(cur.getColumnIndex("AncNoDk"));
                d._AncCard = cur.getString(cur.getColumnIndex("AncCard"));
                d._AncLackMoney = cur.getString(cur.getColumnIndex("AncLackMoney"));
                d._AncLackTransport = cur.getString(cur.getColumnIndex("AncLackTransport"));
                d._AncDistance = cur.getString(cur.getColumnIndex("AncDistance"));
                d._AncAttitude = cur.getString(cur.getColumnIndex("AncAttitude"));
                d._AncPreference = cur.getString(cur.getColumnIndex("AncPreference"));
                d._AncRsnOth = cur.getString(cur.getColumnIndex("AncRsnOth"));
                d._AncRsnOthSp = cur.getString(cur.getColumnIndex("AncRsnOthSp"));
                d._AncRsnDk = cur.getString(cur.getColumnIndex("AncRsnDk"));
                d._AncRsnRefu = cur.getString(cur.getColumnIndex("AncRsnRefu"));
                d._ANCPres = cur.getString(cur.getColumnIndex("ANCPres"));
                d._AncWeight = cur.getString(cur.getColumnIndex("AncWeight"));
                d._AncBp = cur.getString(cur.getColumnIndex("AncBp"));
                d._AncUrine = cur.getString(cur.getColumnIndex("AncUrine"));
                d._AncBlood = cur.getString(cur.getColumnIndex("AncBlood"));
                d._AncHeartbeat = cur.getString(cur.getColumnIndex("AncHeartbeat"));
                d._AncUSG = cur.getString(cur.getColumnIndex("AncUSG"));
                d._AncFood = cur.getString(cur.getColumnIndex("AncFood"));
                d._ANCBF = cur.getString(cur.getColumnIndex("ANCBF"));
                d._ANCVBleed = cur.getString(cur.getColumnIndex("ANCVBleed"));
                d._ANCDSign = cur.getString(cur.getColumnIndex("ANCDSign"));
                d._ANCFPMtd = cur.getString(cur.getColumnIndex("ANCFPMtd"));
                d._ANCHCOth = cur.getString(cur.getColumnIndex("ANCHCOth"));
                d._ANCHCOthSp = cur.getString(cur.getColumnIndex("ANCHCOthSp"));
                d._AncHCDk = cur.getString(cur.getColumnIndex("AncHCDk"));
                d._ANCRefu = cur.getString(cur.getColumnIndex("ANCRefu"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }
 }