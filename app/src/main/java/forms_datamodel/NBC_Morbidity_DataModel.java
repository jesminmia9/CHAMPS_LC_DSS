package forms_datamodel;

import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import Common.Global;
 import android.content.ContentValues;
 public class NBC_Morbidity_DataModel{

        private String _MorbiID = "";
        public String getMorbiID(){
              return _MorbiID;
         }
        public void setMorbiID(String newValue){
              _MorbiID = newValue;
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
        private String _ChSl = "";
        public String getChSl(){
              return _ChSl;
         }
        public void setChSl(String newValue){
              _ChSl = newValue;
         }
        private String _ChDiarrhea = "";
        public String getChDiarrhea(){
              return _ChDiarrhea;
         }
        public void setChDiarrhea(String newValue){
              _ChDiarrhea = newValue;
         }
        private String _DiarrheaTreat = "";
        public String getDiarrheaTreat(){
              return _DiarrheaTreat;
         }
        public void setDiarrheaTreat(String newValue){
              _DiarrheaTreat = newValue;
         }
        private String _DiarrheaTreatPlace = "";
        public String getDiarrheaTreatPlace(){
              return _DiarrheaTreatPlace;
         }
        public void setDiarrheaTreatPlace(String newValue){
              _DiarrheaTreatPlace = newValue;
         }
        private String _DiarrheaTreatPlaceOth = "";
        public String getDiarrheaTreatPlaceOth(){
              return _DiarrheaTreatPlaceOth;
         }
        public void setDiarrheaTreatPlaceOth(String newValue){
              _DiarrheaTreatPlaceOth = newValue;
         }
        private String _Cough2W = "";
        public String getCough2W(){
              return _Cough2W;
         }
        public void setCough2W(String newValue){
              _Cough2W = newValue;
         }
        private String _FastBrth = "";
        public String getFastBrth(){
              return _FastBrth;
         }
        public void setFastBrth(String newValue){
              _FastBrth = newValue;
         }
        private String _FastBrthCause = "";
        public String getFastBrthCause(){
              return _FastBrthCause;
         }
        public void setFastBrthCause(String newValue){
              _FastBrthCause = newValue;
         }
        private String _SeekHCare = "";
        public String getSeekHCare(){
              return _SeekHCare;
         }
        public void setSeekHCare(String newValue){
              _SeekHCare = newValue;
         }
        private String _SeekHCarePlace = "";
        public String getSeekHCarePlace(){
              return _SeekHCarePlace;
         }
        public void setSeekHCarePlace(String newValue){
              _SeekHCarePlace = newValue;
         }
        private String _SeekHCarePlaceOth = "";
        public String getSeekHCarePlaceOth(){
              return _SeekHCarePlaceOth;
         }
        public void setSeekHCarePlaceOth(String newValue){
              _SeekHCarePlaceOth = newValue;
         }
        private String _Fever2W = "";
        public String getFever2W(){
              return _Fever2W;
         }
        public void setFever2W(String newValue){
              _Fever2W = newValue;
         }
        private String _FeverTreat = "";
        public String getFeverTreat(){
              return _FeverTreat;
         }
        public void setFeverTreat(String newValue){
              _FeverTreat = newValue;
         }
        private String _FeverTreatPlace = "";
        public String getFeverTreatPlace(){
              return _FeverTreatPlace;
         }
        public void setFeverTreatPlace(String newValue){
              _FeverTreatPlace = newValue;
         }
        private String _FeverTreatPlaceOth = "";
        public String getFeverTreatPlaceOth(){
              return _FeverTreatPlaceOth;
         }
        public void setFeverTreatPlaceOth(String newValue){
              _FeverTreatPlaceOth = newValue;
         }
        private String _WeightLost = "";
        public String getWeightLost(){
              return _WeightLost;
         }
        public void setWeightLost(String newValue){
              _WeightLost = newValue;
         }
        private String _PoorWeight = "";
        public String getPoorWeight(){
              return _PoorWeight;
         }
        public void setPoorWeight(String newValue){
              _PoorWeight = newValue;
         }
        private String _FeedLP2Week = "";
        public String getFeedLP2Week(){
              return _FeedLP2Week;
         }
        public void setFeedLP2Week(String newValue){
              _FeedLP2Week = newValue;
         }
        private String _UnderweightAge = "";
        public String getUnderweightAge(){
              return _UnderweightAge;
         }
        public void setUnderweightAge(String newValue){
              _UnderweightAge = newValue;
         }
        private String _OverweightAge = "";
        public String getOverweightAge(){
              return _OverweightAge;
         }
        public void setOverweightAge(String newValue){
              _OverweightAge = newValue;
         }
        private String _ComMEBaby = "";
        public String getComMEBaby(){
              return _ComMEBaby;
         }
        public void setComMEBaby(String newValue){
              _ComMEBaby = newValue;
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

        String TableName = "NBC_Morbidity";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where MorbiID='"+ _MorbiID +"' and PGN='"+ _PGN +"' and ChSl='"+ _ChSl +"' "))
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
                 contentValues.put("MorbiID", _MorbiID);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("PGN", _PGN);
                 contentValues.put("ChSl", _ChSl);
                 contentValues.put("ChDiarrhea", _ChDiarrhea);
                 contentValues.put("DiarrheaTreat", _DiarrheaTreat);
                 contentValues.put("DiarrheaTreatPlace", _DiarrheaTreatPlace);
                 contentValues.put("DiarrheaTreatPlaceOth", _DiarrheaTreatPlaceOth);
                 contentValues.put("Cough2W", _Cough2W);
                 contentValues.put("FastBrth", _FastBrth);
                 contentValues.put("FastBrthCause", _FastBrthCause);
                 contentValues.put("SeekHCare", _SeekHCare);
                 contentValues.put("SeekHCarePlace", _SeekHCarePlace);
                 contentValues.put("SeekHCarePlaceOth", _SeekHCarePlaceOth);
                 contentValues.put("Fever2W", _Fever2W);
                 contentValues.put("FeverTreat", _FeverTreat);
                 contentValues.put("FeverTreatPlace", _FeverTreatPlace);
                 contentValues.put("FeverTreatPlaceOth", _FeverTreatPlaceOth);
                 contentValues.put("WeightLost", _WeightLost);
                 contentValues.put("PoorWeight", _PoorWeight);
                 contentValues.put("FeedLP2Week", _FeedLP2Week);
                 contentValues.put("UnderweightAge", _UnderweightAge);
                 contentValues.put("OverweightAge", _OverweightAge);
                 contentValues.put("ComMEBaby", _ComMEBaby);
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
                 contentValues.put("MorbiID", _MorbiID);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("PGN", _PGN);
                 contentValues.put("ChSl", _ChSl);
                 contentValues.put("ChDiarrhea", _ChDiarrhea);
                 contentValues.put("DiarrheaTreat", _DiarrheaTreat);
                 contentValues.put("DiarrheaTreatPlace", _DiarrheaTreatPlace);
                 contentValues.put("DiarrheaTreatPlaceOth", _DiarrheaTreatPlaceOth);
                 contentValues.put("Cough2W", _Cough2W);
                 contentValues.put("FastBrth", _FastBrth);
                 contentValues.put("FastBrthCause", _FastBrthCause);
                 contentValues.put("SeekHCare", _SeekHCare);
                 contentValues.put("SeekHCarePlace", _SeekHCarePlace);
                 contentValues.put("SeekHCarePlaceOth", _SeekHCarePlaceOth);
                 contentValues.put("Fever2W", _Fever2W);
                 contentValues.put("FeverTreat", _FeverTreat);
                 contentValues.put("FeverTreatPlace", _FeverTreatPlace);
                 contentValues.put("FeverTreatPlaceOth", _FeverTreatPlaceOth);
                 contentValues.put("WeightLost", _WeightLost);
                 contentValues.put("PoorWeight", _PoorWeight);
                 contentValues.put("FeedLP2Week", _FeedLP2Week);
                 contentValues.put("UnderweightAge", _UnderweightAge);
                 contentValues.put("OverweightAge", _OverweightAge);
                 contentValues.put("ComMEBaby", _ComMEBaby);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "MorbiID,PGN,ChSl", (""+ _MorbiID +", "+ _PGN +", "+ _ChSl +""), contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        public List<NBC_Morbidity_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<NBC_Morbidity_DataModel> data = new ArrayList<NBC_Morbidity_DataModel>();
            NBC_Morbidity_DataModel d = new NBC_Morbidity_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new NBC_Morbidity_DataModel();
                d._Count = Count;
                d._MorbiID = cur.getString(cur.getColumnIndex("MorbiID"));
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._PGN = cur.getString(cur.getColumnIndex("PGN"));
                d._ChSl = cur.getString(cur.getColumnIndex("ChSl"));
                d._ChDiarrhea = cur.getString(cur.getColumnIndex("ChDiarrhea"));
                d._DiarrheaTreat = cur.getString(cur.getColumnIndex("DiarrheaTreat"));
                d._DiarrheaTreatPlace = cur.getString(cur.getColumnIndex("DiarrheaTreatPlace"));
                d._DiarrheaTreatPlaceOth = cur.getString(cur.getColumnIndex("DiarrheaTreatPlaceOth"));
                d._Cough2W = cur.getString(cur.getColumnIndex("Cough2W"));
                d._FastBrth = cur.getString(cur.getColumnIndex("FastBrth"));
                d._FastBrthCause = cur.getString(cur.getColumnIndex("FastBrthCause"));
                d._SeekHCare = cur.getString(cur.getColumnIndex("SeekHCare"));
                d._SeekHCarePlace = cur.getString(cur.getColumnIndex("SeekHCarePlace"));
                d._SeekHCarePlaceOth = cur.getString(cur.getColumnIndex("SeekHCarePlaceOth"));
                d._Fever2W = cur.getString(cur.getColumnIndex("Fever2W"));
                d._FeverTreat = cur.getString(cur.getColumnIndex("FeverTreat"));
                d._FeverTreatPlace = cur.getString(cur.getColumnIndex("FeverTreatPlace"));
                d._FeverTreatPlaceOth = cur.getString(cur.getColumnIndex("FeverTreatPlaceOth"));
                d._WeightLost = cur.getString(cur.getColumnIndex("WeightLost"));
                d._PoorWeight = cur.getString(cur.getColumnIndex("PoorWeight"));
                d._FeedLP2Week = cur.getString(cur.getColumnIndex("FeedLP2Week"));
                d._UnderweightAge = cur.getString(cur.getColumnIndex("UnderweightAge"));
                d._OverweightAge = cur.getString(cur.getColumnIndex("OverweightAge"));
                d._ComMEBaby = cur.getString(cur.getColumnIndex("ComMEBaby"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }
 }