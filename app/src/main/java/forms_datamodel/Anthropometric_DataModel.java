package forms_datamodel;

import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import Common.Global;
 import android.content.ContentValues;
 public class Anthropometric_DataModel{

        private String _CID = "";
        public String getCID(){
              return _CID;
         }
        public void setCID(String newValue){
              _CID = newValue;
         }
        private String _RespID = "";
        public String getRespID(){
              return _RespID;
         }
        public void setRespID(String newValue){
              _RespID = newValue;
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
        private String _AnthMeasurementDate = "";
        public String getAnthMeasurementDate(){
              return _AnthMeasurementDate;
         }
        public void setAnthMeasurementDate(String newValue){
              _AnthMeasurementDate = newValue;
         }
        private String _AnthWeightCol = "";
        public String getAnthWeightCol(){
              return _AnthWeightCol;
         }
        public void setAnthWeightCol(String newValue){
              _AnthWeightCol = newValue;
         }
        private String _AnthWeight = "";
        public String getAnthWeight(){
              return _AnthWeight;
         }
        public void setAnthWeight(String newValue){
              _AnthWeight = newValue;
         }
        private String _AnthWeightDK = "";
        public String getAnthWeightDK(){
              return _AnthWeightDK;
         }
        public void setAnthWeightDK(String newValue){
              _AnthWeightDK = newValue;
         }
        private String _AnthHeightCol = "";
        public String getAnthHeightCol(){
              return _AnthHeightCol;
         }
        public void setAnthHeightCol(String newValue){
              _AnthHeightCol = newValue;
         }
        private String _AnthHeight = "";
        public String getAnthHeight(){
              return _AnthHeight;
         }
        public void setAnthHeight(String newValue){
              _AnthHeight = newValue;
         }
        private String _AnthHeightDK = "";
        public String getAnthHeightDK(){
              return _AnthHeightDK;
         }
        public void setAnthHeightDK(String newValue){
              _AnthHeightDK = newValue;
         }
        private String _AnthMUACCol = "";
        public String getAnthMUACCol(){
              return _AnthMUACCol;
         }
        public void setAnthMUACCol(String newValue){
              _AnthMUACCol = newValue;
         }
        private String _AnthMUAC = "";
        public String getAnthMUAC(){
              return _AnthMUAC;
         }
        public void setAnthMUAC(String newValue){
              _AnthMUAC = newValue;
         }
        private String _AnthMUACDK = "";
        public String getAnthMUACDK(){
              return _AnthMUACDK;
         }
        public void setAnthMUACDK(String newValue){
              _AnthMUACDK = newValue;
         }
        private String _AnthHeadCircumCol = "";
        public String getAnthHeadCircumCol(){
              return _AnthHeadCircumCol;
         }
        public void setAnthHeadCircumCol(String newValue){
              _AnthHeadCircumCol = newValue;
         }
        private String _AnthHeadCircum = "";
        public String getAnthHeadCircum(){
              return _AnthHeadCircum;
         }
        public void setAnthHeadCircum(String newValue){
              _AnthHeadCircum = newValue;
         }
        private String _AnthHeadCircumDK = "";
        public String getAnthHeadCircumDK(){
              return _AnthHeadCircumDK;
         }
        public void setAnthHeadCircumDK(String newValue){
              _AnthHeadCircumDK = newValue;
         }
        private String _AnthNote = "";
        public String getAnthNote(){
              return _AnthNote;
         }
        public void setAnthNote(String newValue){
              _AnthNote = newValue;
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

        String TableName = "Anthropometric";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where CID='"+ _CID +"' "))
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
                 contentValues.put("CID", _CID);
                 contentValues.put("RespID", _RespID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("AnthMeasurementDate", _AnthMeasurementDate);
                 contentValues.put("AnthWeightCol", _AnthWeightCol);
                 contentValues.put("AnthWeight", _AnthWeight);
                 contentValues.put("AnthWeightDK", _AnthWeightDK);
                 contentValues.put("AnthHeightCol", _AnthHeightCol);
                 contentValues.put("AnthHeight", _AnthHeight);
                 contentValues.put("AnthHeightDK", _AnthHeightDK);
                 contentValues.put("AnthMUACCol", _AnthMUACCol);
                 contentValues.put("AnthMUAC", _AnthMUAC);
                 contentValues.put("AnthMUACDK", _AnthMUACDK);
                 contentValues.put("AnthHeadCircumCol", _AnthHeadCircumCol);
                 contentValues.put("AnthHeadCircum", _AnthHeadCircum);
                 contentValues.put("AnthHeadCircumDK", _AnthHeadCircumDK);
                 contentValues.put("AnthNote", _AnthNote);
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
                 contentValues.put("CID", _CID);
                 contentValues.put("RespID", _RespID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("AnthMeasurementDate", _AnthMeasurementDate);
                 contentValues.put("AnthWeightCol", _AnthWeightCol);
                 contentValues.put("AnthWeight", _AnthWeight);
                 contentValues.put("AnthWeightDK", _AnthWeightDK);
                 contentValues.put("AnthHeightCol", _AnthHeightCol);
                 contentValues.put("AnthHeight", _AnthHeight);
                 contentValues.put("AnthHeightDK", _AnthHeightDK);
                 contentValues.put("AnthMUACCol", _AnthMUACCol);
                 contentValues.put("AnthMUAC", _AnthMUAC);
                 contentValues.put("AnthMUACDK", _AnthMUACDK);
                 contentValues.put("AnthHeadCircumCol", _AnthHeadCircumCol);
                 contentValues.put("AnthHeadCircum", _AnthHeadCircum);
                 contentValues.put("AnthHeadCircumDK", _AnthHeadCircumDK);
                 contentValues.put("AnthNote", _AnthNote);
                 contentValues.put("Rnd", _Rnd);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "CID", (""+ _CID +""), contentValues);

                 //manageAudit(context,this,AuditTrial.KEY_UPDATE);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        public List<Anthropometric_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<Anthropometric_DataModel> data = new ArrayList<Anthropometric_DataModel>();
            Anthropometric_DataModel d = new Anthropometric_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new Anthropometric_DataModel();
                d._Count = Count;
                d._CID = cur.getString(cur.getColumnIndex("CID"));
                d._RespID = cur.getString(cur.getColumnIndex("RespID"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._AnthMeasurementDate = cur.getString(cur.getColumnIndex("AnthMeasurementDate"));
                d._AnthWeightCol = cur.getString(cur.getColumnIndex("AnthWeightCol"));
                d._AnthWeight = cur.getString(cur.getColumnIndex("AnthWeight"));
                d._AnthWeightDK = cur.getString(cur.getColumnIndex("AnthWeightDK"));
                d._AnthHeightCol = cur.getString(cur.getColumnIndex("AnthHeightCol"));
                d._AnthHeight = cur.getString(cur.getColumnIndex("AnthHeight"));
                d._AnthHeightDK = cur.getString(cur.getColumnIndex("AnthHeightDK"));
                d._AnthMUACCol = cur.getString(cur.getColumnIndex("AnthMUACCol"));
                d._AnthMUAC = cur.getString(cur.getColumnIndex("AnthMUAC"));
                d._AnthMUACDK = cur.getString(cur.getColumnIndex("AnthMUACDK"));
                d._AnthHeadCircumCol = cur.getString(cur.getColumnIndex("AnthHeadCircumCol"));
                d._AnthHeadCircum = cur.getString(cur.getColumnIndex("AnthHeadCircum"));
                d._AnthHeadCircumDK = cur.getString(cur.getColumnIndex("AnthHeadCircumDK"));
                d._AnthNote = cur.getString(cur.getColumnIndex("AnthNote"));
                d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
                data.add(d);

               // manageAudit(context,d,AuditTrial.KEY_SELECT);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }



//        static Map<String, Object> firstStateMap;
//        public void manageAudit(Context context, Anthropometric_DataModel ob, String key) {
//            if (key.equalsIgnoreCase(AuditTrial.KEY_SELECT)) {
//                //store old state
//                firstStateMap = getMapData(ob);
//            } else if (key.equalsIgnoreCase(AuditTrial.KEY_UPDATE)) {
//                //store new state
//                Map<String, Object> secondStateMap = getMapData(ob);
//                AuditTrial auditTrial = new AuditTrial(context,TableName);
//                // run audit
//                if (firstStateMap != null && !firstStateMap.isEmpty() && secondStateMap != null && !secondStateMap.isEmpty()) {
//                    auditTrial.audit(firstStateMap, secondStateMap);
//                }
//            }
//        }
//
//
//
//        /**
//        * get object state
//        * @param ob
//        * @return
//        */
//        public Map<String, Object> getMapData(Anthropometric_DataModel ob) {
//            Map<String, Object> data = new HashMap<>();
//
//            if (ob != null) {
//                 data.put("CID", ob._CID);
//                 data.put("RespID", ob._RespID);
//                 data.put("HHID", ob._HHID);
//                 data.put("MemID", ob._MemID);
//                 data.put("AnthMeasurementDate", ob._AnthMeasurementDate);
//                 data.put("AnthWeightCol", ob._AnthWeightCol);
//                 data.put("AnthWeight", ob._AnthWeight);
//                 data.put("AnthWeightDK", ob._AnthWeightDK);
//                 data.put("AnthHeightCol", ob._AnthHeightCol);
//                 data.put("AnthHeight", ob._AnthHeight);
//                 data.put("AnthHeightDK", ob._AnthHeightDK);
//                 data.put("AnthMUACCol", ob._AnthMUACCol);
//                 data.put("AnthMUAC", ob._AnthMUAC);
//                 data.put("AnthMUACDK", ob._AnthMUACDK);
//                 data.put("AnthHeadCircumCol", ob._AnthHeadCircumCol);
//                 data.put("AnthHeadCircum", ob._AnthHeadCircum);
//                 data.put("AnthHeadCircumDK", ob._AnthHeadCircumDK);
//                 data.put("AnthNote", ob._AnthNote);
//                 data.put("Rnd", ob._Rnd);
//
//            }
//            return data;
//        }
 }