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
import android.util.Log;

public class NBC_NBCareDetail_DataModel{

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

         private String _ChSl = "";
         public String getChSl(){
             return _ChSl;
         }
         public void setChSl(String newValue){
             _ChSl = newValue;
         }
        private String _PregType = "";
        public String getPregType(){
              return _PregType;
         }
        public void setPregType(String newValue){
              _PregType = newValue;
         }
        private String _OutcomeDate = "";
        public String getOutcomeDate(){
              return _OutcomeDate;
         }
        public void setOutcomeDate(String newValue){
              _OutcomeDate = newValue;
         }
        private String _OutcomeDateDk = "";
        public String getOutcomeDateDk(){
              return _OutcomeDateDk;
         }
        public void setOutcomeDateDk(String newValue){
              _OutcomeDateDk = newValue;
         }
        private String _OutcomeTime = "";
        public String getOutcomeTime(){
              return _OutcomeTime;
         }
        public void setOutcomeTime(String newValue){
              _OutcomeTime = newValue;
         }
        private String _OutcomeTimeDk = "";
        public String getOutcomeTimeDk(){
              return _OutcomeTimeDk;
         }
        public void setOutcomeTimeDk(String newValue){
              _OutcomeTimeDk = newValue;
         }
        private String _OutcomeType = "";
        public String getOutcomeType(){
              return _OutcomeType;
         }
        public void setOutcomeType(String newValue){
              _OutcomeType = newValue;
         }
        private String _CryMoveBreathe = "";
        public String getCryMoveBreathe(){
              return _CryMoveBreathe;
         }
        public void setCryMoveBreathe(String newValue){
              _CryMoveBreathe = newValue;
         }
        private String _ChildName = "";
        public String getChildName(){
              return _ChildName;
         }
        public void setChildName(String newValue){
              _ChildName = newValue;
         }
        private String _ChildNameDk = "";
        public String getChildNameDk(){
              return _ChildNameDk;
         }
        public void setChildNameDk(String newValue){
              _ChildNameDk = newValue;
         }
        private String _ChildID = "";
        public String getChildID(){
              return _ChildID;
         }
        public void setChildID(String newValue){
              _ChildID = newValue;
         }
        private String _ChildSex = "";
        public String getChildSex(){
              return _ChildSex;
         }
        public void setChildSex(String newValue){
              _ChildSex = newValue;
         }
        private String _BirthTiming = "";
        public String getBirthTiming(){
              return _BirthTiming;
         }
        public void setBirthTiming(String newValue){
              _BirthTiming = newValue;
         }
        private String _PregWeek = "";
        public String getPregWeek(){
              return _PregWeek;
         }
        public void setPregWeek(String newValue){
              _PregWeek = newValue;
         }
        private String _PregWeekDk = "";
        public String getPregWeekDk(){
              return _PregWeekDk;
         }
        public void setPregWeekDk(String newValue){
              _PregWeekDk = newValue;
         }
        private String _ExpDOB = "";
        public String getExpDOB(){
              return _ExpDOB;
         }
        public void setExpDOB(String newValue){
              _ExpDOB = newValue;
         }
        private String _ExpDOBDk = "";
        public String getExpDOBDk(){
              return _ExpDOBDk;
         }
        public void setExpDOBDk(String newValue){
              _ExpDOBDk = newValue;
         }
        private String _ChildDOB = "";
        public String getChildDOB(){
              return _ChildDOB;
         }
        public void setChildDOB(String newValue){
              _ChildDOB = newValue;
         }
        private String _ChildDOBDk = "";
        public String getChildDOBDk(){
              return _ChildDOBDk;
         }
        public void setChildDOBDk(String newValue){
              _ChildDOBDk = newValue;
         }
        private String _BPlace = "";
        public String getBPlace(){
              return _BPlace;
         }
        public void setBPlace(String newValue){
              _BPlace = newValue;
         }
        private String _BPlaceOth = "";
        public String getBPlaceOth(){
              return _BPlaceOth;
         }
        public void setBPlaceOth(String newValue){
              _BPlaceOth = newValue;
         }
        private String _FaciName = "";
        public String getFaciName(){
              return _FaciName;
         }
        public void setFaciName(String newValue){
              _FaciName = newValue;
         }
        private String _FaciNameDk = "";
        public String getFaciNameDk(){
              return _FaciNameDk;
         }
        public void setFaciNameDk(String newValue){
              _FaciNameDk = newValue;
         }
        private String _DelAtndDoc = "";
        public String getDelAtndDoc(){
              return _DelAtndDoc;
         }
        public void setDelAtndDoc(String newValue){
              _DelAtndDoc = newValue;
         }
        private String _DelAtndNurse = "";
        public String getDelAtndNurse(){
              return _DelAtndNurse;
         }
        public void setDelAtndNurse(String newValue){
              _DelAtndNurse = newValue;
         }
        private String _DelAtndMidwife = "";
        public String getDelAtndMidwife(){
              return _DelAtndMidwife;
         }
        public void setDelAtndMidwife(String newValue){
              _DelAtndMidwife = newValue;
         }
        private String _DelAtndTBA = "";
        public String getDelAtndTBA(){
              return _DelAtndTBA;
         }
        public void setDelAtndTBA(String newValue){
              _DelAtndTBA = newValue;
         }
        private String _DelAtndCHW = "";
        public String getDelAtndCHW(){
              return _DelAtndCHW;
         }
        public void setDelAtndCHW(String newValue){
              _DelAtndCHW = newValue;
         }
        private String _DelAtndRel = "";
        public String getDelAtndRel(){
              return _DelAtndRel;
         }
        public void setDelAtndRel(String newValue){
              _DelAtndRel = newValue;
         }
        private String _DelAtndNon = "";
        public String getDelAtndNon(){
              return _DelAtndNon;
         }
        public void setDelAtndNon(String newValue){
              _DelAtndNon = newValue;
         }
        private String _DelAtndOth = "";
        public String getDelAtndOth(){
              return _DelAtndOth;
         }
        public void setDelAtndOth(String newValue){
              _DelAtndOth = newValue;
         }
        private String _DelAtndOthSp = "";
        public String getDelAtndOthSp(){
              return _DelAtndOthSp;
         }
        public void setDelAtndOthSp(String newValue){
              _DelAtndOthSp = newValue;
         }
        private String _DelAtndDK = "";
        public String getDelAtndDK(){
              return _DelAtndDK;
         }
        public void setDelAtndDK(String newValue){
              _DelAtndDK = newValue;
         }
        private String _DelAtndRef = "";
        public String getDelAtndRef(){
              return _DelAtndRef;
         }
        public void setDelAtndRef(String newValue){
              _DelAtndRef = newValue;
         }
        private String _BabySize = "";
        public String getBabySize(){
              return _BabySize;
         }
        public void setBabySize(String newValue){
              _BabySize = newValue;
         }
        private String _WgtMsrd = "";
        public String getWgtMsrd(){
              return _WgtMsrd;
         }
        public void setWgtMsrd(String newValue){
              _WgtMsrd = newValue;
         }
        private String _WgtMsrdUnt = "";
        public String getWgtMsrdUnt(){
              return _WgtMsrdUnt;
         }
        public void setWgtMsrdUnt(String newValue){
              _WgtMsrdUnt = newValue;
         }
        private String _WgtPound = "";
        public String getWgtPound(){
              return _WgtPound;
         }
        public void setWgtPound(String newValue){
              _WgtPound = newValue;
         }
        private String _WgtGm = "";
        public String getWgtGm(){
              return _WgtGm;
         }
        public void setWgtGm(String newValue){
              _WgtGm = newValue;
         }
        private String _WgtDk = "";
        public String getWgtDk(){
              return _WgtDk;
         }
        public void setWgtDk(String newValue){
              _WgtDk = newValue;
         }
        private String _WgtDtSrc = "";
        public String getWgtDtSrc(){
              return _WgtDtSrc;
         }
        public void setWgtDtSrc(String newValue){
              _WgtDtSrc = newValue;
         }
        private String _BFStatus = "";
        public String getBFStatus(){
              return _BFStatus;
         }
        public void setBFStatus(String newValue){
              _BFStatus = newValue;
         }
        private String _BFTime = "";
        public String getBFTime(){
              return _BFTime;
         }
        public void setBFTime(String newValue){
              _BFTime = newValue;
         }
        private String _BFTimeDur = "";
        public String getBFTimeDur(){
              return _BFTimeDur;
         }
        public void setBFTimeDur(String newValue){
              _BFTimeDur = newValue;
         }
        private String _BFTimeOth = "";
        public String getBFTimeOth(){
              return _BFTimeOth;
         }
        public void setBFTimeOth(String newValue){
              _BFTimeOth = newValue;
         }
        private String _NBRthHead = "";
        public String getNBRthHead(){
              return _NBRthHead;
         }
        public void setNBRthHead(String newValue){
              _NBRthHead = newValue;
         }
        private String _NBRthHeadOth = "";
        public String getNBRthHeadOth(){
              return _NBRthHeadOth;
         }
        public void setNBRthHeadOth(String newValue){
              _NBRthHeadOth = newValue;
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

        String TableName = "NBC_NBCareDetail";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where NBID='"+ _NBID +"' and PGN='"+ _PGN +"' and ChSl='"+ _ChSl +"'  "))
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
                  contentValues.put("ChSl", _ChSl);
                 contentValues.put("PregType", _PregType);
                 contentValues.put("OutcomeDate", _OutcomeDate);
                 contentValues.put("OutcomeDateDk", _OutcomeDateDk);
                 contentValues.put("OutcomeTime", _OutcomeTime);
                 contentValues.put("OutcomeTimeDk", _OutcomeTimeDk);
                 contentValues.put("OutcomeType", _OutcomeType);
                 contentValues.put("CryMoveBreathe", _CryMoveBreathe);
                 contentValues.put("ChildName", _ChildName);
                 contentValues.put("ChildNameDk", _ChildNameDk);
                 contentValues.put("ChildID", _ChildID);
                 contentValues.put("ChildSex", _ChildSex);
                 contentValues.put("BirthTiming", _BirthTiming);
                 contentValues.put("PregWeek", _PregWeek);
                 contentValues.put("PregWeekDk", _PregWeekDk);
                 contentValues.put("ExpDOB", _ExpDOB);
                 contentValues.put("ExpDOBDk", _ExpDOBDk);
                 contentValues.put("ChildDOB", _ChildDOB);
                 contentValues.put("ChildDOBDk", _ChildDOBDk);
                 contentValues.put("BPlace", _BPlace);
                 contentValues.put("BPlaceOth", _BPlaceOth);
                 contentValues.put("FaciName", _FaciName);
                 contentValues.put("FaciNameDk", _FaciNameDk);
                 contentValues.put("DelAtndDoc", _DelAtndDoc);
                 contentValues.put("DelAtndNurse", _DelAtndNurse);
                 contentValues.put("DelAtndMidwife", _DelAtndMidwife);
                 contentValues.put("DelAtndTBA", _DelAtndTBA);
                 contentValues.put("DelAtndCHW", _DelAtndCHW);
                 contentValues.put("DelAtndRel", _DelAtndRel);
                 contentValues.put("DelAtndNon", _DelAtndNon);
                 contentValues.put("DelAtndOth", _DelAtndOth);
                 contentValues.put("DelAtndOthSp", _DelAtndOthSp);
                 contentValues.put("DelAtndDK", _DelAtndDK);
                 contentValues.put("DelAtndRef", _DelAtndRef);
                 contentValues.put("BabySize", _BabySize);
                 contentValues.put("WgtMsrd", _WgtMsrd);
                 contentValues.put("WgtMsrdUnt", _WgtMsrdUnt);
                 contentValues.put("WgtPound", _WgtPound);
                 contentValues.put("WgtGm", _WgtGm);
                 contentValues.put("WgtDk", _WgtDk);
                 contentValues.put("WgtDtSrc", _WgtDtSrc);
                 contentValues.put("BFStatus", _BFStatus);
                 contentValues.put("BFTime", _BFTime);
                 contentValues.put("BFTimeDur", _BFTimeDur);
                 contentValues.put("BFTimeOth", _BFTimeOth);
                 contentValues.put("NBRthHead", _NBRthHead);
                 contentValues.put("NBRthHeadOth", _NBRthHeadOth);
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
                  contentValues.put("ChSl", _ChSl);
                 contentValues.put("PregType", _PregType);
                 contentValues.put("OutcomeDate", _OutcomeDate);
                 contentValues.put("OutcomeDateDk", _OutcomeDateDk);
                 contentValues.put("OutcomeTime", _OutcomeTime);
                 contentValues.put("OutcomeTimeDk", _OutcomeTimeDk);
                 contentValues.put("OutcomeType", _OutcomeType);
                 contentValues.put("CryMoveBreathe", _CryMoveBreathe);
                 contentValues.put("ChildName", _ChildName);
                 contentValues.put("ChildNameDk", _ChildNameDk);
                 contentValues.put("ChildID", _ChildID);
                 contentValues.put("ChildSex", _ChildSex);
                 contentValues.put("BirthTiming", _BirthTiming);
                 contentValues.put("PregWeek", _PregWeek);
                 contentValues.put("PregWeekDk", _PregWeekDk);
                 contentValues.put("ExpDOB", _ExpDOB);
                 contentValues.put("ExpDOBDk", _ExpDOBDk);
                 contentValues.put("ChildDOB", _ChildDOB);
                 contentValues.put("ChildDOBDk", _ChildDOBDk);
                 contentValues.put("BPlace", _BPlace);
                 contentValues.put("BPlaceOth", _BPlaceOth);
                 contentValues.put("FaciName", _FaciName);
                 contentValues.put("FaciNameDk", _FaciNameDk);
                 contentValues.put("DelAtndDoc", _DelAtndDoc);
                 contentValues.put("DelAtndNurse", _DelAtndNurse);
                 contentValues.put("DelAtndMidwife", _DelAtndMidwife);
                 contentValues.put("DelAtndTBA", _DelAtndTBA);
                 contentValues.put("DelAtndCHW", _DelAtndCHW);
                 contentValues.put("DelAtndRel", _DelAtndRel);
                 contentValues.put("DelAtndNon", _DelAtndNon);
                 contentValues.put("DelAtndOth", _DelAtndOth);
                 contentValues.put("DelAtndOthSp", _DelAtndOthSp);
                 contentValues.put("DelAtndDK", _DelAtndDK);
                 contentValues.put("DelAtndRef", _DelAtndRef);
                 contentValues.put("BabySize", _BabySize);
                 contentValues.put("WgtMsrd", _WgtMsrd);
                 contentValues.put("WgtMsrdUnt", _WgtMsrdUnt);
                 contentValues.put("WgtPound", _WgtPound);
                 contentValues.put("WgtGm", _WgtGm);
                 contentValues.put("WgtDk", _WgtDk);
                 contentValues.put("WgtDtSrc", _WgtDtSrc);
                 contentValues.put("BFStatus", _BFStatus);
                 contentValues.put("BFTime", _BFTime);
                 contentValues.put("BFTimeDur", _BFTimeDur);
                 contentValues.put("BFTimeOth", _BFTimeOth);
                 contentValues.put("NBRthHead", _NBRthHead);
                 contentValues.put("NBRthHeadOth", _NBRthHeadOth);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "NBID,PGN,ChSl", (""+ _NBID +", "+ _PGN +", "+ _ChSl +""), contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        @SuppressLint("Range")
        public List<NBC_NBCareDetail_DataModel> SelectAll(Context context, String SQL)
        {
            Log.d("JM", SQL);
            Connection C = new Connection(context);
            List<NBC_NBCareDetail_DataModel> data = new ArrayList<NBC_NBCareDetail_DataModel>();
            NBC_NBCareDetail_DataModel d = new NBC_NBCareDetail_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new NBC_NBCareDetail_DataModel();
                d._Count = Count;
                d._NBID = cur.getString(cur.getColumnIndex("NBID"));
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._PGN = cur.getString(cur.getColumnIndex("PGN"));
                d._ChSl = cur.getString(cur.getColumnIndex("ChSl"));
                d._PregType = cur.getString(cur.getColumnIndex("PregType"));
                d._OutcomeDate = cur.getString(cur.getColumnIndex("OutcomeDate"));
                d._OutcomeDateDk = cur.getString(cur.getColumnIndex("OutcomeDateDk"));
                d._OutcomeTime = cur.getString(cur.getColumnIndex("OutcomeTime"));
                d._OutcomeTimeDk = cur.getString(cur.getColumnIndex("OutcomeTimeDk"));
                d._OutcomeType = cur.getString(cur.getColumnIndex("OutcomeType"));
                d._CryMoveBreathe = cur.getString(cur.getColumnIndex("CryMoveBreathe"));
                d._ChildName = cur.getString(cur.getColumnIndex("ChildName"));
                d._ChildNameDk = cur.getString(cur.getColumnIndex("ChildNameDk"));
                d._ChildID = cur.getString(cur.getColumnIndex("ChildID"));
                d._ChildSex = cur.getString(cur.getColumnIndex("ChildSex"));
                d._BirthTiming = cur.getString(cur.getColumnIndex("BirthTiming"));
                d._PregWeek = cur.getString(cur.getColumnIndex("PregWeek"));
                d._PregWeekDk = cur.getString(cur.getColumnIndex("PregWeekDk"));
                d._ExpDOB = cur.getString(cur.getColumnIndex("ExpDOB"));
                d._ExpDOBDk = cur.getString(cur.getColumnIndex("ExpDOBDk"));
                d._ChildDOB = cur.getString(cur.getColumnIndex("ChildDOB"));
                d._ChildDOBDk = cur.getString(cur.getColumnIndex("ChildDOBDk"));
                d._BPlace = cur.getString(cur.getColumnIndex("BPlace"));
                d._BPlaceOth = cur.getString(cur.getColumnIndex("BPlaceOth"));
                d._FaciName = cur.getString(cur.getColumnIndex("FaciName"));
                d._FaciNameDk = cur.getString(cur.getColumnIndex("FaciNameDk"));
                d._DelAtndDoc = cur.getString(cur.getColumnIndex("DelAtndDoc"));
                d._DelAtndNurse = cur.getString(cur.getColumnIndex("DelAtndNurse"));
                d._DelAtndMidwife = cur.getString(cur.getColumnIndex("DelAtndMidwife"));
                d._DelAtndTBA = cur.getString(cur.getColumnIndex("DelAtndTBA"));
                d._DelAtndCHW = cur.getString(cur.getColumnIndex("DelAtndCHW"));
                d._DelAtndRel = cur.getString(cur.getColumnIndex("DelAtndRel"));
                d._DelAtndNon = cur.getString(cur.getColumnIndex("DelAtndNon"));
                d._DelAtndOth = cur.getString(cur.getColumnIndex("DelAtndOth"));
                d._DelAtndOthSp = cur.getString(cur.getColumnIndex("DelAtndOthSp"));
                d._DelAtndDK = cur.getString(cur.getColumnIndex("DelAtndDK"));
                d._DelAtndRef = cur.getString(cur.getColumnIndex("DelAtndRef"));
                d._BabySize = cur.getString(cur.getColumnIndex("BabySize"));
                d._WgtMsrd = cur.getString(cur.getColumnIndex("WgtMsrd"));
                d._WgtMsrdUnt = cur.getString(cur.getColumnIndex("WgtMsrdUnt"));
                d._WgtPound = cur.getString(cur.getColumnIndex("WgtPound"));
                d._WgtGm = cur.getString(cur.getColumnIndex("WgtGm"));
                d._WgtDk = cur.getString(cur.getColumnIndex("WgtDk"));
                d._WgtDtSrc = cur.getString(cur.getColumnIndex("WgtDtSrc"));
                d._BFStatus = cur.getString(cur.getColumnIndex("BFStatus"));
                d._BFTime = cur.getString(cur.getColumnIndex("BFTime"));
                d._BFTimeDur = cur.getString(cur.getColumnIndex("BFTimeDur"));
                d._BFTimeOth = cur.getString(cur.getColumnIndex("BFTimeOth"));
                d._NBRthHead = cur.getString(cur.getColumnIndex("NBRthHead"));
                d._NBRthHeadOth = cur.getString(cur.getColumnIndex("NBRthHeadOth"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }
 }