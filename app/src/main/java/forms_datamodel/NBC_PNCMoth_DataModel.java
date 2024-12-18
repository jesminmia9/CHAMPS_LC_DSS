package forms_datamodel;

import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import Common.Global;
 import android.content.ContentValues;
 public class NBC_PNCMoth_DataModel{

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
        private String _HealthCheck = "";
        public String getHealthCheck(){
              return _HealthCheck;
         }
        public void setHealthCheck(String newValue){
              _HealthCheck = newValue;
         }
        private String _DeliCheckUnit = "";
        public String getDeliCheckUnit(){
              return _DeliCheckUnit;
         }
        public void setDeliCheckUnit(String newValue){
              _DeliCheckUnit = newValue;
         }
        private String _DeliCheckDur = "";
        public String getDeliCheckDur(){
              return _DeliCheckDur;
         }
        public void setDeliCheckDur(String newValue){
              _DeliCheckDur = newValue;
         }
        private String _PNCTotal = "";
        public String getPNCTotal(){
              return _PNCTotal;
         }
        public void setPNCTotal(String newValue){
              _PNCTotal = newValue;
         }
        private String _PNCCard = "";
        public String getPNCCard(){
              return _PNCCard;
         }
        public void setPNCCard(String newValue){
              _PNCCard = newValue;
         }
        private String _PNCPres = "";
        public String getPNCPres(){
              return _PNCPres;
         }
        public void setPNCPres(String newValue){
              _PNCPres = newValue;
         }
        private String _PNC42dBPM = "";
        public String getPNC42dBPM(){
              return _PNC42dBPM;
         }
        public void setPNC42dBPM(String newValue){
              _PNC42dBPM = newValue;
         }
        private String _PNC42dTM = "";
        public String getPNC42dTM(){
              return _PNC42dTM;
         }
        public void setPNC42dTM(String newValue){
              _PNC42dTM = newValue;
         }
        private String _PNC42dAnm = "";
        public String getPNC42dAnm(){
              return _PNC42dAnm;
         }
        public void setPNC42dAnm(String newValue){
              _PNC42dAnm = newValue;
         }
        private String _PNC42dBrst = "";
        public String getPNC42dBrst(){
              return _PNC42dBrst;
         }
        public void setPNC42dBrst(String newValue){
              _PNC42dBrst = newValue;
         }
        private String _PNC42dPeri = "";
        public String getPNC42dPeri(){
              return _PNC42dPeri;
         }
        public void setPNC42dPeri(String newValue){
              _PNC42dPeri = newValue;
         }
        private String _PNC42dOth = "";
        public String getPNC42dOth(){
              return _PNC42dOth;
         }
        public void setPNC42dOth(String newValue){
              _PNC42dOth = newValue;
         }
        private String _PNC42dOthSp = "";
        public String getPNC42dOthSp(){
              return _PNC42dOthSp;
         }
        public void setPNC42dOthSp(String newValue){
              _PNC42dOthSp = newValue;
         }
        private String _PNC42dDk = "";
        public String getPNC42dDk(){
              return _PNC42dDk;
         }
        public void setPNC42dDk(String newValue){
              _PNC42dDk = newValue;
         }
        private String _PNC42dRR = "";
        public String getPNC42dRR(){
              return _PNC42dRR;
         }
        public void setPNC42dRR(String newValue){
              _PNC42dRR = newValue;
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

        String TableName = "NBC_PNCMoth";

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
                 contentValues.put("HealthCheck", _HealthCheck);
                 contentValues.put("DeliCheckUnit", _DeliCheckUnit);
                 contentValues.put("DeliCheckDur", _DeliCheckDur);
                 contentValues.put("PNCTotal", _PNCTotal);
                 contentValues.put("PNCCard", _PNCCard);
                 contentValues.put("PNCPres", _PNCPres);
                 contentValues.put("PNC42dBPM", _PNC42dBPM);
                 contentValues.put("PNC42dTM", _PNC42dTM);
                 contentValues.put("PNC42dAnm", _PNC42dAnm);
                 contentValues.put("PNC42dBrst", _PNC42dBrst);
                 contentValues.put("PNC42dPeri", _PNC42dPeri);
                 contentValues.put("PNC42dOth", _PNC42dOth);
                 contentValues.put("PNC42dOthSp", _PNC42dOthSp);
                 contentValues.put("PNC42dDk", _PNC42dDk);
                 contentValues.put("PNC42dRR", _PNC42dRR);
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
                 contentValues.put("HealthCheck", _HealthCheck);
                 contentValues.put("DeliCheckUnit", _DeliCheckUnit);
                 contentValues.put("DeliCheckDur", _DeliCheckDur);
                 contentValues.put("PNCTotal", _PNCTotal);
                 contentValues.put("PNCCard", _PNCCard);
                 contentValues.put("PNCPres", _PNCPres);
                 contentValues.put("PNC42dBPM", _PNC42dBPM);
                 contentValues.put("PNC42dTM", _PNC42dTM);
                 contentValues.put("PNC42dAnm", _PNC42dAnm);
                 contentValues.put("PNC42dBrst", _PNC42dBrst);
                 contentValues.put("PNC42dPeri", _PNC42dPeri);
                 contentValues.put("PNC42dOth", _PNC42dOth);
                 contentValues.put("PNC42dOthSp", _PNC42dOthSp);
                 contentValues.put("PNC42dDk", _PNC42dDk);
                 contentValues.put("PNC42dRR", _PNC42dRR);
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
        public List<NBC_PNCMoth_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<NBC_PNCMoth_DataModel> data = new ArrayList<NBC_PNCMoth_DataModel>();
            NBC_PNCMoth_DataModel d = new NBC_PNCMoth_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new NBC_PNCMoth_DataModel();
                d._Count = Count;
                d._NBID = cur.getString(cur.getColumnIndex("NBID"));
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._PGN = cur.getString(cur.getColumnIndex("PGN"));
                d._HealthCheck = cur.getString(cur.getColumnIndex("HealthCheck"));
                d._DeliCheckUnit = cur.getString(cur.getColumnIndex("DeliCheckUnit"));
                d._DeliCheckDur = cur.getString(cur.getColumnIndex("DeliCheckDur"));
                d._PNCTotal = cur.getString(cur.getColumnIndex("PNCTotal"));
                d._PNCCard = cur.getString(cur.getColumnIndex("PNCCard"));
                d._PNCPres = cur.getString(cur.getColumnIndex("PNCPres"));
                d._PNC42dBPM = cur.getString(cur.getColumnIndex("PNC42dBPM"));
                d._PNC42dTM = cur.getString(cur.getColumnIndex("PNC42dTM"));
                d._PNC42dAnm = cur.getString(cur.getColumnIndex("PNC42dAnm"));
                d._PNC42dBrst = cur.getString(cur.getColumnIndex("PNC42dBrst"));
                d._PNC42dPeri = cur.getString(cur.getColumnIndex("PNC42dPeri"));
                d._PNC42dOth = cur.getString(cur.getColumnIndex("PNC42dOth"));
                d._PNC42dOthSp = cur.getString(cur.getColumnIndex("PNC42dOthSp"));
                d._PNC42dDk = cur.getString(cur.getColumnIndex("PNC42dDk"));
                d._PNC42dRR = cur.getString(cur.getColumnIndex("PNC42dRR"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }
 }