package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class tmpPregnancy_DataModel {

       private String _PregOccurID = "";
       public String getPregOccurID(){
             return _PregOccurID;
        }
       public void setPregOccurID(String newValue){
             _PregOccurID = newValue;
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
       private String _LMPDt = "";
       public String getLMPDt(){
             return _LMPDt;
        }
       public void setLMPDt(String newValue){
             _LMPDt = newValue;
        }
       private String _LMPDtType = "";
       public String getLMPDtType(){
             return _LMPDtType;
        }
       public void setLMPDtType(String newValue){
             _LMPDtType = newValue;
        }
       private String _LMPDtObt = "";
       public String getLMPDtObt(){
             return _LMPDtObt;
        }
       public void setLMPDtObt(String newValue){
             _LMPDtObt = newValue;
        }
       private String _PrConLMP = "";
       public String getPrConLMP(){
             return _PrConLMP;
        }
       public void setPrConLMP(String newValue){
             _PrConLMP = newValue;
        }
       private String _PrConBelly = "";
       public String getPrConBelly(){
             return _PrConBelly;
        }
       public void setPrConBelly(String newValue){
             _PrConBelly = newValue;
        }
       private String _PrConFeMove = "";
       public String getPrConFeMove(){
             return _PrConFeMove;
        }
       public void setPrConFeMove(String newValue){
             _PrConFeMove = newValue;
        }
       private String _PrConTestHome = "";
       public String getPrConTestHome(){
             return _PrConTestHome;
        }
       public void setPrConTestHome(String newValue){
             _PrConTestHome = newValue;
        }
       private String _PrConTestDoc = "";
       public String getPrConTestDoc(){
             return _PrConTestDoc;
        }
       public void setPrConTestDoc(String newValue){
             _PrConTestDoc = newValue;
        }
       private String _PrConHeartSnd = "";
       public String getPrConHeartSnd(){
             return _PrConHeartSnd;
        }
       public void setPrConHeartSnd(String newValue){
             _PrConHeartSnd = newValue;
        }
       private String _PrConUtrasound = "";
       public String getPrConUtrasound(){
             return _PrConUtrasound;
        }
       public void setPrConUtrasound(String newValue){
             _PrConUtrasound = newValue;
        }
       private String _PrConTestFaci = "";
       public String getPrConTestFaci(){
             return _PrConTestFaci;
        }
       public void setPrConTestFaci(String newValue){
             _PrConTestFaci = newValue;
        }
       private String _PrConUrineTest = "";
       public String getPrConUrineTest(){
             return _PrConUrineTest;
        }
       public void setPrConUrineTest(String newValue){
             _PrConUrineTest = newValue;
        }
       private String _PrConOth = "";
       public String getPrConOth(){
             return _PrConOth;
        }
       public void setPrConOth(String newValue){
             _PrConOth = newValue;
        }
       private String _PrConOthSpecify = "";
       public String getPrConOthSpecify(){
             return _PrConOthSpecify;
        }
       public void setPrConOthSpecify(String newValue){
             _PrConOthSpecify = newValue;
        }
    private String _PregPrenatalCons = "";
    public String getPregPrenatalCons(){
        return _PregPrenatalCons;
    }
    public void setPregPrenatalCons(String newValue){
        _PregPrenatalCons = newValue;
    }
    private String _chkPregPrenatalConsA = "";
    public String getchkPregPrenatalConsA(){
        return _chkPregPrenatalConsA;
    }
    public void setchkPregPrenatalConsA(String newValue){
        _chkPregPrenatalConsA = newValue;
    }
    private String _chkPregPrenatalConsB = "";
    public String getchkPregPrenatalConsB(){
        return _chkPregPrenatalConsB;
    }
    public void setchkPregPrenatalConsB(String newValue){
        _chkPregPrenatalConsB = newValue;
    }
    private String _chkPregPrenatalConsC = "";
    public String getchkPregPrenatalConsC(){
        return _chkPregPrenatalConsC;
    }
    public void setchkPregPrenatalConsC(String newValue){
        _chkPregPrenatalConsC = newValue;
    }
    private String _chkPregPrenatalConsD = "";
    public String getchkPregPrenatalConsD(){
        return _chkPregPrenatalConsD;
    }
    public void setchkPregPrenatalConsD(String newValue){
        _chkPregPrenatalConsD = newValue;
    }
    private String _chkPregPrenatalConsE = "";
    public String getchkPregPrenatalConsE(){
        return _chkPregPrenatalConsE;
    }
    public void setchkPregPrenatalConsE(String newValue){
        _chkPregPrenatalConsE = newValue;
    }
    private String _chkPregPrenatalConsF = "";
    public String getchkPregPrenatalConsF(){
        return _chkPregPrenatalConsF;
    }
    public void setchkPregPrenatalConsF(String newValue){
        _chkPregPrenatalConsF = newValue;
    }
    private String _chkPregPrenatalConsG = "";
    public String getchkPregPrenatalConsG(){
        return _chkPregPrenatalConsG;
    }
    public void setchkPregPrenatalConsG(String newValue){
        _chkPregPrenatalConsG = newValue;
    }
    private String _chkPregPrenatalConsH = "";
    public String getchkPregPrenatalConsH(){
        return _chkPregPrenatalConsH;
    }
    public void setchkPregPrenatalConsH(String newValue){
        _chkPregPrenatalConsH = newValue;
    }
    private String _chkPregPrenatalConsI = "";
    public String getchkPregPrenatalConsI(){
        return _chkPregPrenatalConsI;
    }
    public void setchkPregPrenatalConsI(String newValue){
        _chkPregPrenatalConsI = newValue;
    }
    private String _chkPregPrenatalConsPlaceA = "";
    public String getchkPregPrenatalConsPlaceA(){
        return _chkPregPrenatalConsPlaceA;
    }
    public void setchkPregPrenatalConsPlaceA(String newValue){
        _chkPregPrenatalConsPlaceA = newValue;
    }
    private String _chkPregPrenatalConsPlaceB = "";
    public String getchkPregPrenatalConsPlaceB(){
        return _chkPregPrenatalConsPlaceB;
    }
    public void setchkPregPrenatalConsPlaceB(String newValue){
        _chkPregPrenatalConsPlaceB = newValue;
    }
    private String _chkPregPrenatalConsPlaceC = "";
    public String getchkPregPrenatalConsPlaceC(){
        return _chkPregPrenatalConsPlaceC;
    }
    public void setchkPregPrenatalConsPlaceC(String newValue){
        _chkPregPrenatalConsPlaceC = newValue;
    }
    private String _chkPregPrenatalConsPlaceD = "";
    public String getchkPregPrenatalConsPlaceD(){
        return _chkPregPrenatalConsPlaceD;
    }
    public void setchkPregPrenatalConsPlaceD(String newValue){
        _chkPregPrenatalConsPlaceD = newValue;
    }
    private String _chkPregPrenatalConsPlaceE = "";
    public String getchkPregPrenatalConsPlaceE(){
        return _chkPregPrenatalConsPlaceE;
    }
    public void setchkPregPrenatalConsPlaceE(String newValue){
        _chkPregPrenatalConsPlaceE = newValue;
    }
    private String _chkPregPrenatalConsPlaceF = "";
    public String getchkPregPrenatalConsPlaceF(){
        return _chkPregPrenatalConsPlaceF;
    }
    public void setchkPregPrenatalConsPlaceF(String newValue){
        _chkPregPrenatalConsPlaceF = newValue;
    }
    private String _chkPregPrenatalConsPlaceG = "";
    public String getchkPregPrenatalConsPlaceG(){
        return _chkPregPrenatalConsPlaceG;
    }
    public void setchkPregPrenatalConsPlaceG(String newValue){
        _chkPregPrenatalConsPlaceG = newValue;
    }
    private String _chkPregPrenatalConsPlaceH = "";
    public String getchkPregPrenatalConsPlaceH(){
        return _chkPregPrenatalConsPlaceH;
    }
    public void setchkPregPrenatalConsPlaceH(String newValue){
        _chkPregPrenatalConsPlaceH = newValue;
    }
    private String _chkPregPrenatalConsPlaceI = "";
    public String getchkPregPrenatalConsPlaceI(){
        return _chkPregPrenatalConsPlaceI;
    }
    public void setchkPregPrenatalConsPlaceI(String newValue){
        _chkPregPrenatalConsPlaceI = newValue;
    }
    private String _chkPregPrenatalConsPlaceJ = "";
    public String getchkPregPrenatalConsPlaceJ(){
        return _chkPregPrenatalConsPlaceJ;
    }
    public void setchkPregPrenatalConsPlaceJ(String newValue){
        _chkPregPrenatalConsPlaceJ = newValue;
    }
    private String _chkPregPrenatalConsPlaceX = "";
    public String getchkPregPrenatalConsPlaceX(){
        return _chkPregPrenatalConsPlaceX;
    }
    public void setchkPregPrenatalConsPlaceX(String newValue){
        _chkPregPrenatalConsPlaceX = newValue;
    }
    private String _chkPregPrenatalConsPlaceY = "";
    public String getchkPregPrenatalConsPlaceY(){
        return _chkPregPrenatalConsPlaceY;
    }
    public void setchkPregPrenatalConsPlaceY(String newValue){
        _chkPregPrenatalConsPlaceY = newValue;
    }
    private String _chkPregPrenatalConsPlaceZ = "";
    public String getchkPregPrenatalConsPlaceZ(){
        return _chkPregPrenatalConsPlaceZ;
    }
    public void setchkPregPrenatalConsPlaceZ(String newValue){
        _chkPregPrenatalConsPlaceZ = newValue;
    }
    private String _PregPrenatalConsPlaceOth = "";
    public String getPregPrenatalConsPlaceOth(){
        return _PregPrenatalConsPlaceOth;
    }
    public void setPregPrenatalConsPlaceOth(String newValue){
        _PregPrenatalConsPlaceOth = newValue;
    }
    private String _PregRank = "";
    public String getPregRank(){
        return _PregRank;
    }
    public void setPregRank(String newValue){
        _PregRank = newValue;
    }
    private String _PregEnrolled = "";
    public String getPregEnrolled(){
        return _PregEnrolled;
    }
    public void setPregEnrolled(String newValue){
        _PregEnrolled = newValue;
    }
    private String _PregPSIDKnown = "";
    public String getPregPSIDKnown(){
        return _PregPSIDKnown;
    }
    public void setPregPSIDKnown(String newValue){
        _PregPSIDKnown = newValue;
    }
    private String _PregPSID = "";
    public String getPregPSID(){
        return _PregPSID;
    }
    public void setPregPSID(String newValue){
        _PregPSID = newValue;
    }
    private String _PregWishEnroll = "";
    public String getPregWishEnroll(){
        return _PregWishEnroll;
    }
    public void setPregWishEnroll(String newValue){
        _PregWishEnroll = newValue;
    }
       private String _PregVdate = "";
       public String getPregVdate(){
             return _PregVdate;
        }
       public void setPregVdate(String newValue){
             _PregVdate = newValue;
        }
       private String _Rnd = "";
       public String getRnd(){
             return _Rnd;
        }
       public void setRnd(String newValue){
             _Rnd = newValue;
        }
       private String _PregNote = "";
       public String getPregNote(){
             return _PregNote;
        }
       public void setPregNote(String newValue){
             _PregNote = newValue;
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

       String TableName = "tmpPregnancy";

       public String SaveUpdateData(Context context)
       {
           String response = "";
           C = new Connection(context);
           String SQL = "";
           try
           {
                if(C.Existence("Select * from "+ TableName +"  Where PregOccurID='"+ _PregOccurID +"' "))
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
                contentValues.put("PregOccurID", _PregOccurID);
                contentValues.put("HHID", _HHID);
                contentValues.put("MemID", _MemID);
                contentValues.put("LMPDt", _LMPDt);
                contentValues.put("LMPDtType", _LMPDtType);
                contentValues.put("LMPDtObt", _LMPDtObt);
                contentValues.put("PrConLMP", _PrConLMP);
                contentValues.put("PrConBelly", _PrConBelly);
                contentValues.put("PrConFeMove", _PrConFeMove);
                contentValues.put("PrConTestHome", _PrConTestHome);
                contentValues.put("PrConTestDoc", _PrConTestDoc);
                contentValues.put("PrConHeartSnd", _PrConHeartSnd);
                contentValues.put("PrConUtrasound", _PrConUtrasound);
                contentValues.put("PrConTestFaci", _PrConTestFaci);
                contentValues.put("PrConUrineTest", _PrConUrineTest);
                contentValues.put("PrConOth", _PrConOth);
                contentValues.put("PrConOthSpecify", _PrConOthSpecify);
                 contentValues.put("PregPrenatalCons", _PregPrenatalCons);
                 contentValues.put("chkPregPrenatalConsA", _chkPregPrenatalConsA);
                 contentValues.put("chkPregPrenatalConsB", _chkPregPrenatalConsB);
                 contentValues.put("chkPregPrenatalConsC", _chkPregPrenatalConsC);
                 contentValues.put("chkPregPrenatalConsD", _chkPregPrenatalConsD);
                 contentValues.put("chkPregPrenatalConsE", _chkPregPrenatalConsE);
                 contentValues.put("chkPregPrenatalConsF", _chkPregPrenatalConsF);
                 contentValues.put("chkPregPrenatalConsG", _chkPregPrenatalConsG);
                 contentValues.put("chkPregPrenatalConsH", _chkPregPrenatalConsH);
                 contentValues.put("chkPregPrenatalConsI", _chkPregPrenatalConsI);
                 contentValues.put("chkPregPrenatalConsPlaceA", _chkPregPrenatalConsPlaceA);
                 contentValues.put("chkPregPrenatalConsPlaceB", _chkPregPrenatalConsPlaceB);
                 contentValues.put("chkPregPrenatalConsPlaceC", _chkPregPrenatalConsPlaceC);
                 contentValues.put("chkPregPrenatalConsPlaceD", _chkPregPrenatalConsPlaceD);
                 contentValues.put("chkPregPrenatalConsPlaceE", _chkPregPrenatalConsPlaceE);
                 contentValues.put("chkPregPrenatalConsPlaceF", _chkPregPrenatalConsPlaceF);
                 contentValues.put("chkPregPrenatalConsPlaceG", _chkPregPrenatalConsPlaceG);
                 contentValues.put("chkPregPrenatalConsPlaceH", _chkPregPrenatalConsPlaceH);
                 contentValues.put("chkPregPrenatalConsPlaceI", _chkPregPrenatalConsPlaceI);
                 contentValues.put("chkPregPrenatalConsPlaceJ", _chkPregPrenatalConsPlaceJ);
                 contentValues.put("chkPregPrenatalConsPlaceX", _chkPregPrenatalConsPlaceX);
                 contentValues.put("chkPregPrenatalConsPlaceY", _chkPregPrenatalConsPlaceY);
                 contentValues.put("chkPregPrenatalConsPlaceZ", _chkPregPrenatalConsPlaceZ);
                 contentValues.put("PregPrenatalConsPlaceOth", _PregPrenatalConsPlaceOth);
                 contentValues.put("PregRank", _PregRank);
                 contentValues.put("PregEnrolled", _PregEnrolled);
                 contentValues.put("PregPSIDKnown", _PregPSIDKnown);
                 contentValues.put("PregPSID", _PregPSID);
                 contentValues.put("PregWishEnroll", _PregWishEnroll);
                contentValues.put("PregVdate", _PregVdate);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("PregNote", _PregNote);
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
                contentValues.put("PregOccurID", _PregOccurID);
                contentValues.put("HHID", _HHID);
                contentValues.put("MemID", _MemID);
                contentValues.put("LMPDt", _LMPDt);
                contentValues.put("LMPDtType", _LMPDtType);
                contentValues.put("LMPDtObt", _LMPDtObt);
                contentValues.put("PrConLMP", _PrConLMP);
                contentValues.put("PrConBelly", _PrConBelly);
                contentValues.put("PrConFeMove", _PrConFeMove);
                contentValues.put("PrConTestHome", _PrConTestHome);
                contentValues.put("PrConTestDoc", _PrConTestDoc);
                contentValues.put("PrConHeartSnd", _PrConHeartSnd);
                contentValues.put("PrConUtrasound", _PrConUtrasound);
                contentValues.put("PrConTestFaci", _PrConTestFaci);
                contentValues.put("PrConUrineTest", _PrConUrineTest);
                contentValues.put("PrConOth", _PrConOth);
                contentValues.put("PrConOthSpecify", _PrConOthSpecify);
                 contentValues.put("PregPrenatalCons", _PregPrenatalCons);
                 contentValues.put("chkPregPrenatalConsA", _chkPregPrenatalConsA);
                 contentValues.put("chkPregPrenatalConsB", _chkPregPrenatalConsB);
                 contentValues.put("chkPregPrenatalConsC", _chkPregPrenatalConsC);
                 contentValues.put("chkPregPrenatalConsD", _chkPregPrenatalConsD);
                 contentValues.put("chkPregPrenatalConsE", _chkPregPrenatalConsE);
                 contentValues.put("chkPregPrenatalConsF", _chkPregPrenatalConsF);
                 contentValues.put("chkPregPrenatalConsG", _chkPregPrenatalConsG);
                 contentValues.put("chkPregPrenatalConsH", _chkPregPrenatalConsH);
                 contentValues.put("chkPregPrenatalConsI", _chkPregPrenatalConsI);
                 contentValues.put("chkPregPrenatalConsPlaceA", _chkPregPrenatalConsPlaceA);
                 contentValues.put("chkPregPrenatalConsPlaceB", _chkPregPrenatalConsPlaceB);
                 contentValues.put("chkPregPrenatalConsPlaceC", _chkPregPrenatalConsPlaceC);
                 contentValues.put("chkPregPrenatalConsPlaceD", _chkPregPrenatalConsPlaceD);
                 contentValues.put("chkPregPrenatalConsPlaceE", _chkPregPrenatalConsPlaceE);
                 contentValues.put("chkPregPrenatalConsPlaceF", _chkPregPrenatalConsPlaceF);
                 contentValues.put("chkPregPrenatalConsPlaceG", _chkPregPrenatalConsPlaceG);
                 contentValues.put("chkPregPrenatalConsPlaceH", _chkPregPrenatalConsPlaceH);
                 contentValues.put("chkPregPrenatalConsPlaceI", _chkPregPrenatalConsPlaceI);
                 contentValues.put("chkPregPrenatalConsPlaceJ", _chkPregPrenatalConsPlaceJ);
                 contentValues.put("chkPregPrenatalConsPlaceX", _chkPregPrenatalConsPlaceX);
                 contentValues.put("chkPregPrenatalConsPlaceY", _chkPregPrenatalConsPlaceY);
                 contentValues.put("chkPregPrenatalConsPlaceZ", _chkPregPrenatalConsPlaceZ);
                 contentValues.put("PregPrenatalConsPlaceOth", _PregPrenatalConsPlaceOth);
                 contentValues.put("PregRank", _PregRank);
                 contentValues.put("PregEnrolled", _PregEnrolled);
                 contentValues.put("PregPSIDKnown", _PregPSIDKnown);
                 contentValues.put("PregPSID", _PregPSID);
                 contentValues.put("PregWishEnroll", _PregWishEnroll);
                contentValues.put("PregVdate", _PregVdate);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("PregNote", _PregNote);
                contentValues.put("Upload", _Upload);
                contentValues.put("modifyDate", _modifyDate);
                C.UpdateData(TableName, "PregOccurID", (""+ _PregOccurID +""), contentValues);
             }
             catch(Exception  e)
             {
                response = e.getMessage();
             }
          return response;
       }

         int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
       public List<tmpPregnancy_DataModel> SelectAll(Context context, String SQL)
       {
           Connection C = new Connection(context);
           List<tmpPregnancy_DataModel> data = new ArrayList<tmpPregnancy_DataModel>();
           tmpPregnancy_DataModel d = new tmpPregnancy_DataModel();
           Cursor cur = C.ReadData(SQL);

           cur.moveToFirst();
           while(!cur.isAfterLast())
           {
               Count += 1;
               d = new tmpPregnancy_DataModel();
               d._Count = Count;
               d._PregOccurID = cur.getString(cur.getColumnIndex("PregOccurID"));
               d._HHID = cur.getString(cur.getColumnIndex("HHID"));
               d._MemID = cur.getString(cur.getColumnIndex("MemID"));
               d._LMPDt = cur.getString(cur.getColumnIndex("LMPDt"));
               d._LMPDtType = cur.getString(cur.getColumnIndex("LMPDtType"));
               d._LMPDtObt = cur.getString(cur.getColumnIndex("LMPDtObt"));
               d._PrConLMP = cur.getString(cur.getColumnIndex("PrConLMP"));
               d._PrConBelly = cur.getString(cur.getColumnIndex("PrConBelly"));
               d._PrConFeMove = cur.getString(cur.getColumnIndex("PrConFeMove"));
               d._PrConTestHome = cur.getString(cur.getColumnIndex("PrConTestHome"));
               d._PrConTestDoc = cur.getString(cur.getColumnIndex("PrConTestDoc"));
               d._PrConHeartSnd = cur.getString(cur.getColumnIndex("PrConHeartSnd"));
               d._PrConUtrasound = cur.getString(cur.getColumnIndex("PrConUtrasound"));
               d._PrConTestFaci = cur.getString(cur.getColumnIndex("PrConTestFaci"));
               d._PrConUrineTest = cur.getString(cur.getColumnIndex("PrConUrineTest"));
               d._PrConOth = cur.getString(cur.getColumnIndex("PrConOth"));
               d._PrConOthSpecify = cur.getString(cur.getColumnIndex("PrConOthSpecify"));
               d._PregPrenatalCons = cur.getString(cur.getColumnIndex("PregPrenatalCons"));
               d._chkPregPrenatalConsA = cur.getString(cur.getColumnIndex("chkPregPrenatalConsA"));
               d._chkPregPrenatalConsB = cur.getString(cur.getColumnIndex("chkPregPrenatalConsB"));
               d._chkPregPrenatalConsC = cur.getString(cur.getColumnIndex("chkPregPrenatalConsC"));
               d._chkPregPrenatalConsD = cur.getString(cur.getColumnIndex("chkPregPrenatalConsD"));
               d._chkPregPrenatalConsE = cur.getString(cur.getColumnIndex("chkPregPrenatalConsE"));
               d._chkPregPrenatalConsF = cur.getString(cur.getColumnIndex("chkPregPrenatalConsF"));
               d._chkPregPrenatalConsG = cur.getString(cur.getColumnIndex("chkPregPrenatalConsG"));
               d._chkPregPrenatalConsH = cur.getString(cur.getColumnIndex("chkPregPrenatalConsH"));
               d._chkPregPrenatalConsI = cur.getString(cur.getColumnIndex("chkPregPrenatalConsI"));
               d._chkPregPrenatalConsPlaceA = cur.getString(cur.getColumnIndex("chkPregPrenatalConsPlaceA"));
               d._chkPregPrenatalConsPlaceB = cur.getString(cur.getColumnIndex("chkPregPrenatalConsPlaceB"));
               d._chkPregPrenatalConsPlaceC = cur.getString(cur.getColumnIndex("chkPregPrenatalConsPlaceC"));
               d._chkPregPrenatalConsPlaceD = cur.getString(cur.getColumnIndex("chkPregPrenatalConsPlaceD"));
               d._chkPregPrenatalConsPlaceE = cur.getString(cur.getColumnIndex("chkPregPrenatalConsPlaceE"));
               d._chkPregPrenatalConsPlaceF = cur.getString(cur.getColumnIndex("chkPregPrenatalConsPlaceF"));
               d._chkPregPrenatalConsPlaceG = cur.getString(cur.getColumnIndex("chkPregPrenatalConsPlaceG"));
               d._chkPregPrenatalConsPlaceH = cur.getString(cur.getColumnIndex("chkPregPrenatalConsPlaceH"));
               d._chkPregPrenatalConsPlaceI = cur.getString(cur.getColumnIndex("chkPregPrenatalConsPlaceI"));
               d._chkPregPrenatalConsPlaceJ = cur.getString(cur.getColumnIndex("chkPregPrenatalConsPlaceJ"));
               d._chkPregPrenatalConsPlaceX = cur.getString(cur.getColumnIndex("chkPregPrenatalConsPlaceX"));
               d._chkPregPrenatalConsPlaceY = cur.getString(cur.getColumnIndex("chkPregPrenatalConsPlaceY"));
               d._chkPregPrenatalConsPlaceZ = cur.getString(cur.getColumnIndex("chkPregPrenatalConsPlaceZ"));
               d._PregPrenatalConsPlaceOth = cur.getString(cur.getColumnIndex("PregPrenatalConsPlaceOth"));
               d._PregRank = cur.getString(cur.getColumnIndex("PregRank"));
               d._PregEnrolled = cur.getString(cur.getColumnIndex("PregEnrolled"));
               d._PregPSIDKnown = cur.getString(cur.getColumnIndex("PregPSIDKnown"));
               d._PregPSID = cur.getString(cur.getColumnIndex("PregPSID"));
               d._PregWishEnroll = cur.getString(cur.getColumnIndex("PregWishEnroll"));
               d._PregVdate = cur.getString(cur.getColumnIndex("PregVdate"));
               d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
               d._PregNote = cur.getString(cur.getColumnIndex("PregNote"));
               data.add(d);

               cur.moveToNext();
           }
           cur.close();
         return data;
       }
}