package forms_datamodel;

import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import Common.Global;
 import android.content.ContentValues;
 public class PregHis_DataModel{

        private String _ObsMatID = "";
        public String getObsMatID(){
              return _ObsMatID;
         }
        public void setObsMatID(String newValue){
              _ObsMatID = newValue;
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
        private String _MSlNo = "";
        public String getMSlNo(){
              return _MSlNo;
         }
        public void setMSlNo(String newValue){
              _MSlNo = newValue;
         }
        private String _ObsVDate = "";
        public String getObsVDate(){
              return _ObsVDate;
         }
        public void setObsVDate(String newValue){
              _ObsVDate = newValue;
         }
        private String _ObsVStatus = "";
        public String getObsVStatus(){
              return _ObsVStatus;
         }
        public void setObsVStatus(String newValue){
              _ObsVStatus = newValue;
         }
        private String _ObsVStatusOth = "";
        public String getObsVStatusOth(){
              return _ObsVStatusOth;
         }
        public void setObsVStatusOth(String newValue){
              _ObsVStatusOth = newValue;
         }
        private String _MarMon = "";
        public String getMarMon(){
              return _MarMon;
         }
        public void setMarMon(String newValue){
              _MarMon = newValue;
         }
        private String _MarYear = "";
        public String getMarYear(){
              return _MarYear;
         }
        public void setMarYear(String newValue){
              _MarYear = newValue;
         }
        private String _MarDK = "";
        public String getMarDK(){
              return _MarDK;
         }
        public void setMarDK(String newValue){
              _MarDK = newValue;
         }
        private String _EverPreg = "";
        public String getEverPreg(){
              return _EverPreg;
         }
        public void setEverPreg(String newValue){
              _EverPreg = newValue;
         }
        private String _TotPreg = "";
        public String getTotPreg(){
              return _TotPreg;
         }
        public void setTotPreg(String newValue){
              _TotPreg = newValue;
         }
        private String _GaveBirth = "";
        public String getGaveBirth(){
              return _GaveBirth;
         }
        public void setGaveBirth(String newValue){
              _GaveBirth = newValue;
         }
        private String _ChildLivWWo = "";
        public String getChildLivWWo(){
              return _ChildLivWWo;
         }
        public void setChildLivWWo(String newValue){
              _ChildLivWWo = newValue;
         }
        private String _SonLivWWo = "";
        public String getSonLivWWo(){
              return _SonLivWWo;
         }
        public void setSonLivWWo(String newValue){
              _SonLivWWo = newValue;
         }
        private String _DaugLivWWo = "";
        public String getDaugLivWWo(){
              return _DaugLivWWo;
         }
        public void setDaugLivWWo(String newValue){
              _DaugLivWWo = newValue;
         }
        private String _ChldLivOut = "";
        public String getChldLivOut(){
              return _ChldLivOut;
         }
        public void setChldLivOut(String newValue){
              _ChldLivOut = newValue;
         }
        private String _SonLivOut = "";
        public String getSonLivOut(){
              return _SonLivOut;
         }
        public void setSonLivOut(String newValue){
              _SonLivOut = newValue;
         }
        private String _DaugLivOut = "";
        public String getDaugLivOut(){
              return _DaugLivOut;
         }
        public void setDaugLivOut(String newValue){
              _DaugLivOut = newValue;
         }
        private String _EarlyAlive = "";
        public String getEarlyAlive(){
              return _EarlyAlive;
         }
        public void setEarlyAlive(String newValue){
              _EarlyAlive = newValue;
         }
        private String _EarlyAliveNo = "";
        public String getEarlyAliveNo(){
              return _EarlyAliveNo;
         }
        public void setEarlyAliveNo(String newValue){
              _EarlyAliveNo = newValue;
         }
        private String _ChldDie = "";
        public String getChldDie(){
              return _ChldDie;
         }
        public void setChldDie(String newValue){
              _ChldDie = newValue;
         }
        private String _BoyDied = "";
        public String getBoyDied(){
              return _BoyDied;
         }
        public void setBoyDied(String newValue){
              _BoyDied = newValue;
         }
        private String _GirlDied = "";
        public String getGirlDied(){
              return _GirlDied;
         }
        public void setGirlDied(String newValue){
              _GirlDied = newValue;
         }
        private String _ChDiedFsMon = "";
        public String getChDiedFsMon(){
              return _ChDiedFsMon;
         }
        public void setChDiedFsMon(String newValue){
              _ChDiedFsMon = newValue;
         }
        private String _NotLivBrth = "";
        public String getNotLivBrth(){
              return _NotLivBrth;
         }
        public void setNotLivBrth(String newValue){
              _NotLivBrth = newValue;
         }
        private String _TotNotLB = "";
        public String getTotNotLB(){
              return _TotNotLB;
         }
        public void setTotNotLB(String newValue){
              _TotNotLB = newValue;
         }

        private String _StillBirth = "";
        public String getStillBirth(){
              return _StillBirth;
         }
        public void setStillBirth(String newValue){
              _StillBirth = newValue;
         }
        private String _StillBirthDk = "";
        public String getStillBirthDk(){
              return _StillBirthDk;
         }
        public void setStillBirthDk(String newValue){
              _StillBirthDk = newValue;
         }
        private String _MiscAbor = "";
        public String getMiscAbor(){
              return _MiscAbor;
         }
        public void setMiscAbor(String newValue){
              _MiscAbor = newValue;
         }
        private String _MiscAborDk = "";
        public String getMiscAborDk(){
              return _MiscAborDk;
         }
        public void setMiscAborDk(String newValue){
              _MiscAborDk = newValue;
         }



         private String _Misc = "";
         public String getMisc(){
             return _Misc;
         }
         public void setMisc(String newValue){
             _Misc = newValue;
         }
         private String _MiscDk = "";
         public String getMiscDk(){
             return _MiscDk;
         }
         public void setMiscDk(String newValue){
             _MiscDk = newValue;
         }



        private String _LastPregRes = "";
        public String getLastPregRes(){
              return _LastPregRes;
         }
        public void setLastPregRes(String newValue){
              _LastPregRes = newValue;
         }
        private String _LastOutDate = "";
        public String getLastOutDate(){
              return _LastOutDate;
         }
        public void setLastOutDate(String newValue){
              _LastOutDate = newValue;
         }
        private String _LastOutDateDK = "";
        public String getLastOutDateDK(){
              return _LastOutDateDK;
         }
        public void setLastOutDateDK(String newValue){
              _LastOutDateDK = newValue;
         }
        private String _Cesarean = "";
        public String getCesarean(){
              return _Cesarean;
         }
        public void setCesarean(String newValue){
              _Cesarean = newValue;
         }
        private String _CesareanNo = "";
        public String getCesareanNo(){
              return _CesareanNo;
         }
        public void setCesareanNo(String newValue){
              _CesareanNo = newValue;
         }
        private String _TotPregOut = "";
        public String getTotPregOut(){
              return _TotPregOut;
         }
        public void setTotPregOut(String newValue){
              _TotPregOut = newValue;
         }
        private String _ObsNote = "";
        public String getObsNote(){
              return _ObsNote;
         }
        public void setObsNote(String newValue){
              _ObsNote = newValue;
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

        String TableName = "PregHis";

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
                 contentValues.put("ObsMatID", _ObsMatID);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("MSlNo", _MSlNo);
                 contentValues.put("ObsVDate", _ObsVDate);
                 contentValues.put("ObsVStatus", _ObsVStatus);
                 contentValues.put("ObsVStatusOth", _ObsVStatusOth);
                 contentValues.put("MarMon", _MarMon);
                 contentValues.put("MarYear", _MarYear);
                 contentValues.put("MarDK", _MarDK);
                 contentValues.put("EverPreg", _EverPreg);
                 contentValues.put("TotPreg", _TotPreg);
                 contentValues.put("GaveBirth", _GaveBirth);
                 contentValues.put("ChildLivWWo", _ChildLivWWo);
                 contentValues.put("SonLivWWo", _SonLivWWo);
                 contentValues.put("DaugLivWWo", _DaugLivWWo);
                 contentValues.put("ChldLivOut", _ChldLivOut);
                 contentValues.put("SonLivOut", _SonLivOut);
                 contentValues.put("DaugLivOut", _DaugLivOut);
                 contentValues.put("EarlyAlive", _EarlyAlive);
                 contentValues.put("EarlyAliveNo", _EarlyAliveNo);
                 contentValues.put("ChldDie", _ChldDie);
                 contentValues.put("BoyDied", _BoyDied);
                 contentValues.put("GirlDied", _GirlDied);
                 contentValues.put("ChDiedFsMon", _ChDiedFsMon);
                 contentValues.put("NotLivBrth", _NotLivBrth);
                 contentValues.put("TotNotLB", _TotNotLB);
                 contentValues.put("StillBirth", _StillBirth);
                 contentValues.put("StillBirthDk", _StillBirthDk);
                 contentValues.put("MiscAbor", _MiscAbor);
                 contentValues.put("MiscAborDk", _MiscAborDk);

                 contentValues.put("Misc", _Misc);
                 contentValues.put("MiscDk", _MiscDk);

                 contentValues.put("LastPregRes", _LastPregRes);
                 contentValues.put("LastOutDate", _LastOutDate);
                 contentValues.put("LastOutDateDK", _LastOutDateDK);
                 contentValues.put("Cesarean", _Cesarean);
                 contentValues.put("CesareanNo", _CesareanNo);
                 contentValues.put("TotPregOut", _TotPregOut);
                 contentValues.put("ObsNote", _ObsNote);
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
                 contentValues.put("ObsMatID", _ObsMatID);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("MSlNo", _MSlNo);
                 contentValues.put("ObsVDate", _ObsVDate);
                 contentValues.put("ObsVStatus", _ObsVStatus);
                 contentValues.put("ObsVStatusOth", _ObsVStatusOth);
                 contentValues.put("MarMon", _MarMon);
                 contentValues.put("MarYear", _MarYear);
                 contentValues.put("MarDK", _MarDK);
                 contentValues.put("EverPreg", _EverPreg);
                 contentValues.put("TotPreg", _TotPreg);
                 contentValues.put("GaveBirth", _GaveBirth);
                 contentValues.put("ChildLivWWo", _ChildLivWWo);
                 contentValues.put("SonLivWWo", _SonLivWWo);
                 contentValues.put("DaugLivWWo", _DaugLivWWo);
                 contentValues.put("ChldLivOut", _ChldLivOut);
                 contentValues.put("SonLivOut", _SonLivOut);
                 contentValues.put("DaugLivOut", _DaugLivOut);
                 contentValues.put("EarlyAlive", _EarlyAlive);
                 contentValues.put("EarlyAliveNo", _EarlyAliveNo);
                 contentValues.put("ChldDie", _ChldDie);
                 contentValues.put("BoyDied", _BoyDied);
                 contentValues.put("GirlDied", _GirlDied);
                 contentValues.put("ChDiedFsMon", _ChDiedFsMon);
                 contentValues.put("NotLivBrth", _NotLivBrth);
                 contentValues.put("TotNotLB", _TotNotLB);
                 contentValues.put("StillBirth", _StillBirth);
                 contentValues.put("StillBirthDk", _StillBirthDk);
                 contentValues.put("MiscAbor", _MiscAbor);
                 contentValues.put("MiscAborDk", _MiscAborDk);

                 contentValues.put("Misc", _Misc);
                 contentValues.put("MiscDk", _MiscDk);

                 contentValues.put("LastPregRes", _LastPregRes);
                 contentValues.put("LastOutDate", _LastOutDate);
                 contentValues.put("LastOutDateDK", _LastOutDateDK);
                 contentValues.put("Cesarean", _Cesarean);
                 contentValues.put("CesareanNo", _CesareanNo);
                 contentValues.put("TotPregOut", _TotPregOut);
                 contentValues.put("ObsNote", _ObsNote);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "MemID", (""+ _MemID +""), contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        public List<PregHis_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<PregHis_DataModel> data = new ArrayList<PregHis_DataModel>();
            PregHis_DataModel d = new PregHis_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new PregHis_DataModel();
                d._Count = Count;
                d._ObsMatID = cur.getString(cur.getColumnIndex("ObsMatID"));
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._MSlNo = cur.getString(cur.getColumnIndex("MSlNo"));
                d._ObsVDate = cur.getString(cur.getColumnIndex("ObsVDate"));
                d._ObsVStatus = cur.getString(cur.getColumnIndex("ObsVStatus"));
                d._ObsVStatusOth = cur.getString(cur.getColumnIndex("ObsVStatusOth"));
                d._MarMon = cur.getString(cur.getColumnIndex("MarMon"));
                d._MarYear = cur.getString(cur.getColumnIndex("MarYear"));
                d._MarDK = cur.getString(cur.getColumnIndex("MarDK"));
                d._EverPreg = cur.getString(cur.getColumnIndex("EverPreg"));
                d._TotPreg = cur.getString(cur.getColumnIndex("TotPreg"));
                d._GaveBirth = cur.getString(cur.getColumnIndex("GaveBirth"));
                d._ChildLivWWo = cur.getString(cur.getColumnIndex("ChildLivWWo"));
                d._SonLivWWo = cur.getString(cur.getColumnIndex("SonLivWWo"));
                d._DaugLivWWo = cur.getString(cur.getColumnIndex("DaugLivWWo"));
                d._ChldLivOut = cur.getString(cur.getColumnIndex("ChldLivOut"));
                d._SonLivOut = cur.getString(cur.getColumnIndex("SonLivOut"));
                d._DaugLivOut = cur.getString(cur.getColumnIndex("DaugLivOut"));
                d._EarlyAlive = cur.getString(cur.getColumnIndex("EarlyAlive"));
                d._EarlyAliveNo = cur.getString(cur.getColumnIndex("EarlyAliveNo"));
                d._ChldDie = cur.getString(cur.getColumnIndex("ChldDie"));
                d._BoyDied = cur.getString(cur.getColumnIndex("BoyDied"));
                d._GirlDied = cur.getString(cur.getColumnIndex("GirlDied"));
                d._ChDiedFsMon = cur.getString(cur.getColumnIndex("ChDiedFsMon"));
                d._NotLivBrth = cur.getString(cur.getColumnIndex("NotLivBrth"));
                d._TotNotLB = cur.getString(cur.getColumnIndex("TotNotLB"));
                d._StillBirth = cur.getString(cur.getColumnIndex("StillBirth"));
                d._StillBirthDk = cur.getString(cur.getColumnIndex("StillBirthDk"));
                d._MiscAbor = cur.getString(cur.getColumnIndex("MiscAbor"));
                d._MiscAborDk = cur.getString(cur.getColumnIndex("MiscAborDk"));

                d._Misc = cur.getString(cur.getColumnIndex("Misc"));
                d._MiscDk = cur.getString(cur.getColumnIndex("MiscDk"));

                d._LastPregRes = cur.getString(cur.getColumnIndex("LastPregRes"));
                d._LastOutDate = cur.getString(cur.getColumnIndex("LastOutDate"));
                d._LastOutDateDK = cur.getString(cur.getColumnIndex("LastOutDateDK"));
                d._Cesarean = cur.getString(cur.getColumnIndex("Cesarean"));
                d._CesareanNo = cur.getString(cur.getColumnIndex("CesareanNo"));
                d._TotPregOut = cur.getString(cur.getColumnIndex("TotPregOut"));
                d._ObsNote = cur.getString(cur.getColumnIndex("ObsNote"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }
 }