package forms_datamodel;

import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import Common.Global;
 import android.content.ContentValues;
 public class DeathNotifi_DataModel{

        private String _DthID = "";
        public String getDthID(){
              return _DthID;
         }
        public void setDthID(String newValue){
              _DthID = newValue;
         }
        private String _HHID = "";
        public String getHHID(){
              return _HHID;
         }
        public void setHHID(String newValue){
              _HHID = newValue;
         }

     private String _DSSID = "";
     public String getDSSID(){
         return _DSSID;
     }
     public void setDSSID(String newValue){
         _DSSID = newValue;
     }


        private String _MortalityAgeG = "";
        public String getMortalityAgeG(){
              return _MortalityAgeG;
         }
        public void setMortalityAgeG(String newValue){
              _MortalityAgeG = newValue;
         }
        private String _DeceasedName = "";
        public String getDeceasedName(){
              return _DeceasedName;
         }
        public void setDeceasedName(String newValue){
              _DeceasedName = newValue;
         }
        private String _MemID = "";
        public String getMemID(){
              return _MemID;
         }
        public void setMemID(String newValue){
              _MemID = newValue;
         }
        private String _DOD = "";
        public String getDOD(){
              return _DOD;
         }
        public void setDOD(String newValue){
              _DOD = newValue;
         }
        private String _DODDk = "";
        public String getDODDk(){
              return _DODDk;
         }
        public void setDODDk(String newValue){
              _DODDk = newValue;
         }
        private String _DOB = "";
        public String getDOB(){
              return _DOB;
         }
        public void setDOB(String newValue){
              _DOB = newValue;
         }
        private String _DOBDk = "";
        public String getDOBDk(){
              return _DOBDk;
         }
        public void setDOBDk(String newValue){
              _DOBDk = newValue;
         }

        private String _DAge = "";
        public String getDAge(){
              return _DAge;
         }
        public void setDAge(String newValue){
              _DAge = newValue;
         }

        private String _DAgeUnit = "";
        public String getDAgeUnit(){
         return _DAgeUnit;
     }
        public void setDAgeUnit(String newValue){
         _DAgeUnit = newValue;
     }

        private String _DAgeDk = "";
        public String getDAgeDk(){
              return _DAgeDk;
         }
        public void setDAgeDk(String newValue){
              _DAgeDk = newValue;
         }

        private String _POD = "";
        public String getPOD(){
              return _POD;
         }
        public void setPOD(String newValue){
              _POD = newValue;
         }
        private String _PODOth = "";
        public String getPODOth(){
              return _PODOth;
         }
        public void setPODOth(String newValue){
              _PODOth = newValue;
         }
        private String _PODNm = "";
        public String getPODNm(){
              return _PODNm;
         }
        public void setPODNm(String newValue){
              _PODNm = newValue;
         }
        private String _PODNmDk = "";
        public String getPODNmDk(){
              return _PODNmDk;
         }
        public void setPODNmDk(String newValue){
              _PODNmDk = newValue;
         }
        private String _CODToldHCP = "";
        public String getCODToldHCP(){
              return _CODToldHCP;
         }
        public void setCODToldHCP(String newValue){
              _CODToldHCP = newValue;
         }
        private String _MainCOD = "";
        public String getMainCOD(){
              return _MainCOD;
         }
        public void setMainCOD(String newValue){
              _MainCOD = newValue;
         }
        private String _MainCODDk = "";
        public String getMainCODDk(){
              return _MainCODDk;
         }
        public void setMainCODDk(String newValue){
              _MainCODDk = newValue;
         }
        private String _MainCODThink = "";
        public String getMainCODThink(){
              return _MainCODThink;
         }
        public void setMainCODThink(String newValue){
              _MainCODThink = newValue;
         }
        private String _MainCODThinkDk = "";
        public String getMainCODThinkDk(){
              return _MainCODThinkDk;
         }
        public void setMainCODThinkDk(String newValue){
              _MainCODThinkDk = newValue;
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

        String TableName = "DeathNotifi";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where DthID='"+ _DthID +"' "))
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
                 contentValues.put("DthID", _DthID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("DSSID", _DSSID);
                 contentValues.put("MortalityAgeG", _MortalityAgeG);
                 contentValues.put("DeceasedName", _DeceasedName);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("DOD", _DOD);
                 contentValues.put("DODDk", _DODDk);
                 contentValues.put("DOB", _DOB);
                 contentValues.put("DOBDk", _DOBDk);
                 contentValues.put("DAge", _DAge);
                 contentValues.put("DAgeUnit", _DAgeUnit);
                 contentValues.put("DAgeDk", _DAgeDk);
                 contentValues.put("POD", _POD);
                 contentValues.put("PODOth", _PODOth);
                 contentValues.put("PODNm", _PODNm);
                 contentValues.put("PODNmDk", _PODNmDk);
                 contentValues.put("CODToldHCP", _CODToldHCP);
                 contentValues.put("MainCOD", _MainCOD);
                 contentValues.put("MainCODDk", _MainCODDk);
                 contentValues.put("MainCODThink", _MainCODThink);
                 contentValues.put("MainCODThinkDk", _MainCODThinkDk);
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
                 contentValues.put("DthID", _DthID);
                 contentValues.put("HHID", _HHID);
                  contentValues.put("DSSID", _DSSID);
                 contentValues.put("MortalityAgeG", _MortalityAgeG);
                 contentValues.put("DeceasedName", _DeceasedName);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("DOD", _DOD);
                 contentValues.put("DODDk", _DODDk);
                 contentValues.put("DOB", _DOB);
                 contentValues.put("DOBDk", _DOBDk);
                  contentValues.put("DAge", _DAge);
                  contentValues.put("DAgeUnit", _DAgeUnit);
                  contentValues.put("DAgeDk", _DAgeDk);
                 contentValues.put("POD", _POD);
                 contentValues.put("PODOth", _PODOth);
                 contentValues.put("PODNm", _PODNm);
                 contentValues.put("PODNmDk", _PODNmDk);
                 contentValues.put("CODToldHCP", _CODToldHCP);
                 contentValues.put("MainCOD", _MainCOD);
                 contentValues.put("MainCODDk", _MainCODDk);
                 contentValues.put("MainCODThink", _MainCODThink);
                 contentValues.put("MainCODThinkDk", _MainCODThinkDk);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "DthID", (""+ _DthID +""), contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        public List<DeathNotifi_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<DeathNotifi_DataModel> data = new ArrayList<DeathNotifi_DataModel>();
            DeathNotifi_DataModel d = new DeathNotifi_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new DeathNotifi_DataModel();
                d._Count = Count;
                d._DthID = cur.getString(cur.getColumnIndex("DthID"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._DSSID = cur.getString(cur.getColumnIndex("DSSID"));
                d._MortalityAgeG = cur.getString(cur.getColumnIndex("MortalityAgeG"));
                d._DeceasedName = cur.getString(cur.getColumnIndex("DeceasedName"));
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._DOD = cur.getString(cur.getColumnIndex("DOD"));
                d._DODDk = cur.getString(cur.getColumnIndex("DODDk"));
                d._DOB = cur.getString(cur.getColumnIndex("DOB"));
                d._DOBDk = cur.getString(cur.getColumnIndex("DOBDk"));
                d._DAge = cur.getString(cur.getColumnIndex("DAge"));
                d._DAgeUnit = cur.getString(cur.getColumnIndex("DAgeUnit"));
                d._DAgeDk = cur.getString(cur.getColumnIndex("DAgeDk"));
                d._POD = cur.getString(cur.getColumnIndex("POD"));
                d._PODOth = cur.getString(cur.getColumnIndex("PODOth"));
                d._PODNm = cur.getString(cur.getColumnIndex("PODNm"));
                d._PODNmDk = cur.getString(cur.getColumnIndex("PODNmDk"));
                d._CODToldHCP = cur.getString(cur.getColumnIndex("CODToldHCP"));
                d._MainCOD = cur.getString(cur.getColumnIndex("MainCOD"));
                d._MainCODDk = cur.getString(cur.getColumnIndex("MainCODDk"));
                d._MainCODThink = cur.getString(cur.getColumnIndex("MainCODThink"));
                d._MainCODThinkDk = cur.getString(cur.getColumnIndex("MainCODThinkDk"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }
 }