package forms_datamodel;

import android.content.Context;
 import android.annotation.SuppressLint;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import java.util.HashMap;
 import Utility.AuditTrial;
 import Common.Global;
 import android.content.ContentValues;

 public class cleaning_household_DataModel{

        private String _uuid = "";
        public String getuuid(){
              return _uuid;
         }
        public void setuuid(String newValue){
              _uuid = newValue;
         }
        private String _HHID = "";
        public String getHHID(){
              return _HHID;
         }
        public void setHHID(String newValue){
              _HHID = newValue;
         }
        private String _error_type = "";
        public String geterror_type(){
              return _error_type;
         }
        public void seterror_type(String newValue){
              _error_type = newValue;
         }
        private String _errors = "";
        public String geterrors(){
              return _errors;
         }
        public void seterrors(String newValue){
              _errors = newValue;
         }
        private String _rnd = "";
        public String getrnd(){
              return _rnd;
         }
        public void setrnd(String newValue){
              _rnd = newValue;
         }
        private String _status = "";
        public String getstatus(){
              return _status;
         }
        public void setstatus(String newValue){
              _status = newValue;
         }
        private String _cleandt = "";
        public String getcleandt(){
              return _cleandt;
         }
        public void setcleandt(String newValue){
              _cleandt = newValue;
         }
        private String _deviceid = "";
        public void setDeviceID(String newValue){
            _deviceid = newValue;
         }
        private String _entryuser = "";
        public void setEntryUser(String newValue){
              _entryuser = newValue;
         }
        private int _upload = 2;

        String TableName = "cleaning_household";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where uuid='"+ _uuid +"' "))
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
                 contentValues.put("uuid", _uuid);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("error_type", _error_type);
                 contentValues.put("errors", _errors);
                 contentValues.put("rnd", _rnd);
                 contentValues.put("status", _status);
                 contentValues.put("cleandt", _cleandt);
                 contentValues.put("deviceid", _deviceid);
                 contentValues.put("entryuser", _entryuser);
                 contentValues.put("upload", _upload);
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
                 contentValues.put("uuid", _uuid);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("status", _status);
                 contentValues.put("cleandt", _cleandt);
                 contentValues.put("deviceid", _deviceid);
                 contentValues.put("entryuser", _entryuser);
                 contentValues.put("upload", _upload);
                 C.UpdateData(TableName, "uuid", (""+ _uuid +""), contentValues);

                 //manageAudit(context,this,AuditTrial.KEY_UPDATE);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }


        int Count = 0;
        private int _Count = 0;
        public int getCount(){ return _Count; }

        @SuppressLint("Range")
        public List<cleaning_household_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<cleaning_household_DataModel> data = new ArrayList<cleaning_household_DataModel>();
            cleaning_household_DataModel d = new cleaning_household_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new cleaning_household_DataModel();
                d._Count = Count;
                d._uuid = cur.getString(cur.getColumnIndex("uuid"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._error_type = cur.getString(cur.getColumnIndex("error_type"));
                d._errors = cur.getString(cur.getColumnIndex("errors"));
                d._rnd = cur.getString(cur.getColumnIndex("rnd"));
                d._status = cur.getString(cur.getColumnIndex("status"));
                d._cleandt = cur.getString(cur.getColumnIndex("cleandt"));
                data.add(d);

                manageAudit(context,d,AuditTrial.KEY_SELECT);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }



        static Map<String, Object> firstStateMap;
        public void manageAudit(Context context, cleaning_household_DataModel ob, String key) {
            if (key.equalsIgnoreCase(AuditTrial.KEY_SELECT)) {
                //store old state
                firstStateMap = getMapData(ob);
            } else if (key.equalsIgnoreCase(AuditTrial.KEY_UPDATE)) {
                //store new state
                Map<String, Object> secondStateMap = getMapData(ob);
                AuditTrial auditTrial = new AuditTrial(context,TableName);
                // run audit
                if (firstStateMap != null && !firstStateMap.isEmpty() && secondStateMap != null && !secondStateMap.isEmpty()) {
                    auditTrial.audit(firstStateMap, secondStateMap);
                }
            }
        }



        /**
        * get object state
        * @param ob
        * @return
        */
        public Map<String, Object> getMapData(cleaning_household_DataModel ob) {
            Map<String, Object> data = new HashMap<>();

            if (ob != null) {
                 data.put("uuid", ob._uuid);
                 data.put("HHID", ob._HHID);
                 data.put("error_type", ob._error_type);
                 data.put("errors", ob._errors);
                 data.put("rnd", ob._rnd);
                 data.put("status", ob._status);
                 data.put("cleandt", ob._cleandt);
            
            }
            return data;
        }
 }