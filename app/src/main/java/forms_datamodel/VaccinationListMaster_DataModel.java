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
 public class VaccinationListMaster_DataModel{

        private String _MemID = "";
        public String getMemID(){
              return _MemID;
         }
        public void setMemID(String newValue){
              _MemID = newValue;
         }
        private String _CardAvailable = "";
        public String getCardAvailable(){
              return _CardAvailable;
         }
        public void setCardAvailable(String newValue){
              _CardAvailable = newValue;
         }
     private String _PrevCardAvailable = "";
     public String getPrevCardAvailable(){
         return _PrevCardAvailable;
     }
     public void setPrevCardAvailable(String newValue){
         _PrevCardAvailable = newValue;
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

        String TableName = "VaccinationListMaster";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where MemID='"+ _MemID +"' "))
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
                 contentValues.put("CardAvailable", _CardAvailable);
                  contentValues.put("PrevCardAvailable", _PrevCardAvailable);
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
                 contentValues.put("CardAvailable", _CardAvailable);
                  contentValues.put("PrevCardAvailable", _PrevCardAvailable);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "MemID", (""+ _MemID +""), contentValues);

//                 manageAudit(context,this,AuditTrial.KEY_UPDATE);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        @SuppressLint("Range")
        public List<VaccinationListMaster_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<VaccinationListMaster_DataModel> data = new ArrayList<VaccinationListMaster_DataModel>();
            VaccinationListMaster_DataModel d = new VaccinationListMaster_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new VaccinationListMaster_DataModel();
                d._Count = Count;
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._CardAvailable = cur.getString(cur.getColumnIndex("CardAvailable"));
                d._PrevCardAvailable = cur.getString(cur.getColumnIndex("PrevCardAvailable"));
                data.add(d);

//                manageAudit(context,d,AuditTrial.KEY_SELECT);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }

//
//
//        static Map<String, Object> firstStateMap;
//        public void manageAudit(Context context, VaccinationListMaster_DataModel ob, String key) {
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
//        public Map<String, Object> getMapData(VaccinationListMaster_DataModel ob) {
//            Map<String, Object> data = new HashMap<>();
//
//            if (ob != null) {
//                 data.put("MemID", ob._MemID);
//                 data.put("CardAvailable", ob._CardAvailable);
//
//            }
//            return data;
//        }
 }