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
public class ChildChar_DataModel{

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

    private String _ChRthHead = "";
    public String getChRthHead(){
        return _ChRthHead;
    }
    public void setChRthHead(String newValue){
        _ChRthHead = newValue;
    }
    private String _RthHeadOth = "";
    public String getRthHeadOth(){
        return _RthHeadOth;
    }
    public void setRthHeadOth(String newValue){
        _RthHeadOth = newValue;
    }
    private String _RthCaregiver = "";
    public String getRthCaregiver(){
        return _RthCaregiver;
    }
    public void setRthCaregiver(String newValue){
        _RthCaregiver = newValue;
    }
    private String _RthCaregiverOth = "";
    public String getRthCaregiverOth(){
        return _RthCaregiverOth;
    }
    public void setRthCaregiverOth(String newValue){
        _RthCaregiverOth = newValue;
    }
    private String _ChSize = "";
    public String getChSize(){
        return _ChSize;
    }
    public void setChSize(String newValue){
        _ChSize = newValue;
    }
    private String _ChStatus = "";
    public String getChStatus(){
        return _ChStatus;
    }
    public void setChStatus(String newValue){
        _ChStatus = newValue;
    }
    private String _ChDiarrhea = "";
    public String getChDiarrhea(){
        return _ChDiarrhea;
    }
    public void setChDiarrhea(String newValue){
        _ChDiarrhea = newValue;
    }
    private String _SeekCareDiarrhea = "";
    public String getSeekCareDiarrhea(){
        return _SeekCareDiarrhea;
    }
    public void setSeekCareDiarrhea(String newValue){
        _SeekCareDiarrhea = newValue;
    }
//    private String _DiarrheaCareLoc = "";
//    public String getDiarrheaCareLoc(){
//        return _DiarrheaCareLoc;
//    }
//    public void setDiarrheaCareLoc(String newValue){
//        _DiarrheaCareLoc = newValue;
//    }
//    private String _DiarrheaCareLocOth = "";
//    public String getDiarrheaCareLocOth(){
//        return _DiarrheaCareLocOth;
//    }
//    public void setDiarrheaCareLocOth(String newValue){
//        _DiarrheaCareLocOth = newValue;
//    }

    private String _GovHos = "";
    public String getGovHos(){
        return _GovHos;
    }
    public void setGovHos(String newValue){
        _GovHos = newValue;
    }
    private String _GovHelCenter = "";
    public String getGovHelCenter(){
        return _GovHelCenter;
    }
    public void setGovHelCenter(String newValue){
        _GovHelCenter = newValue;
    }
    private String _GovHelPost = "";
    public String getGovHelPost(){
        return _GovHelPost;
    }
    public void setGovHelPost(String newValue){
        _GovHelPost = newValue;
    }
    private String _GovMobClinic = "";
    public String getGovMobClinic(){
        return _GovMobClinic;
    }
    public void setGovMobClinic(String newValue){
        _GovMobClinic = newValue;
    }
    private String _PrvtHos = "";
    public String getPrvtHos(){
        return _PrvtHos;
    }
    public void setPrvtHos(String newValue){
        _PrvtHos = newValue;
    }
    private String _Pharma = "";
    public String getPharma(){
        return _Pharma;
    }
    public void setPharma(String newValue){
        _Pharma = newValue;
    }
    private String _PrvtDoctor = "";
    public String getPrvtDoctor(){
        return _PrvtDoctor;
    }
    public void setPrvtDoctor(String newValue){
        _PrvtDoctor = newValue;
    }
    private String _PrvtMobClinic = "";
    public String getPrvtMobClinic(){
        return _PrvtMobClinic;
    }
    public void setPrvtMobClinic(String newValue){
        _PrvtMobClinic = newValue;
    }
    private String _TrdiPract = "";
    public String getTrdiPract(){
        return _TrdiPract;
    }
    public void setTrdiPract(String newValue){
        _TrdiPract = newValue;
    }
    private String _DrugSeller = "";
    public String getDrugSeller(){
        return _DrugSeller;
    }
    public void setDrugSeller(String newValue){
        _DrugSeller = newValue;
    }
    private String _Other = "";
    public String getOther(){
        return _Other;
    }
    public void setOther(String newValue){
        _Other = newValue;
    }
    private String _OtherSp = "";
    public String getOtherSp(){
        return _OtherSp;
    }
    public void setOtherSp(String newValue){
        _OtherSp = newValue;
    }
    private String _FaciDk = "";
    public String getFaciDk(){
        return _FaciDk;
    }
    public void setFaciDk(String newValue){
        _FaciDk = newValue;
    }
    private String _Refused = "";
    public String getRefused(){
        return _Refused;
    }
    public void setRefused(String newValue){
        _Refused = newValue;
    }
    private String _Cough = "";
    public String getCough(){
        return _Cough;
    }
    public void setCough(String newValue){
        _Cough = newValue;
    }
    private String _Breath = "";
    public String getBreath(){
        return _Breath;
    }
    public void setBreath(String newValue){
        _Breath = newValue;
    }
    private String _BreathDiffC = "";
    public String getBreathDiffC(){
        return _BreathDiffC;
    }
    public void setBreathDiffC(String newValue){
        _BreathDiffC = newValue;
    }
    private String _SeekCareCough = "";
    public String getSeekCareCough(){
        return _SeekCareCough;
    }
    public void setSeekCareCough(String newValue){
        _SeekCareCough = newValue;
    }
    private String _CoughCareLoc = "";
    public String getCoughCareLoc(){
        return _CoughCareLoc;
    }
    public void setCoughCareLoc(String newValue){
        _CoughCareLoc = newValue;
    }
    private String _CoughCareLocOth = "";
    public String getCoughCareLocOth(){
        return _CoughCareLocOth;
    }
    public void setCoughCareLocOth(String newValue){
        _CoughCareLocOth = newValue;
    }
    private String _FeverPst2W = "";
    public String getFeverPst2W(){
        return _FeverPst2W;
    }
    public void setFeverPst2W(String newValue){
        _FeverPst2W = newValue;
    }
    private String _SeekCareFever = "";
    public String getSeekCareFever(){
        return _SeekCareFever;
    }
    public void setSeekCareFever(String newValue){
        _SeekCareFever = newValue;
    }
    private String _FeverCareLoc = "";
    public String getFeverCareLoc(){
        return _FeverCareLoc;
    }
    public void setFeverCareLoc(String newValue){
        _FeverCareLoc = newValue;
    }
    private String _FeverCareLocOth = "";
    public String getFeverCareLocOth(){
        return _FeverCareLocOth;
    }
    public void setFeverCareLocOth(String newValue){
        _FeverCareLocOth = newValue;
    }
    private String _WgtLost = "";
    public String getWgtLost(){
        return _WgtLost;
    }
    public void setWgtLost(String newValue){
        _WgtLost = newValue;
    }
    private String _WgtGain = "";
    public String getWgtGain(){
        return _WgtGain;
    }
    public void setWgtGain(String newValue){
        _WgtGain = newValue;
    }
    private String _ChLessFeed = "";
    public String getChLessFeed(){
        return _ChLessFeed;
    }
    public void setChLessFeed(String newValue){
        _ChLessFeed = newValue;
    }
    private String _UnderWeight = "";
    public String getUnderWeight(){
        return _UnderWeight;
    }
    public void setUnderWeight(String newValue){
        _UnderWeight = newValue;
    }
    private String _OverWeight = "";
    public String getOverWeight(){
        return _OverWeight;
    }
    public void setOverWeight(String newValue){
        _OverWeight = newValue;
    }
    private String _EvrVacc = "";
    public String getEvrVacc(){
        return _EvrVacc;
    }
    public void setEvrVacc(String newValue){
        _EvrVacc = newValue;
    }
    private String _VaccCard = "";
    public String getVaccCard(){
        return _VaccCard;
    }
    public void setVaccCard(String newValue){
        _VaccCard = newValue;
    }
    private String _VaccCardPhoto = "";
    public String getVaccCardPhoto(){
        return _VaccCardPhoto;
    }
    public void setVaccCardPhoto(String newValue){
        _VaccCardPhoto = newValue;
    }
    private String _EvrVaccCard = "";
    public String getEvrVaccCard(){
        return _EvrVaccCard;
    }
    public void setEvrVaccCard(String newValue){
        _EvrVaccCard = newValue;
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

    String TableName = "ChildChar";

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
            contentValues.put("CID", _CID);
            contentValues.put("RespID", _RespID);
            contentValues.put("MemID", _MemID);
            contentValues.put("HHID", _HHID);
            contentValues.put("ChRthHead", _ChRthHead);
            contentValues.put("RthHeadOth", _RthHeadOth);
            contentValues.put("RthCaregiver", _RthCaregiver);
            contentValues.put("RthCaregiverOth", _RthCaregiverOth);
            contentValues.put("ChSize", _ChSize);
            contentValues.put("ChStatus", _ChStatus);
            contentValues.put("ChDiarrhea", _ChDiarrhea);
            contentValues.put("SeekCareDiarrhea", _SeekCareDiarrhea);
//            contentValues.put("DiarrheaCareLoc", _DiarrheaCareLoc);
//            contentValues.put("DiarrheaCareLocOth", _DiarrheaCareLocOth);

            contentValues.put("GovHos", _GovHos);
            contentValues.put("GovHelCenter", _GovHelCenter);
            contentValues.put("GovHelPost", _GovHelPost);
            contentValues.put("GovMobClinic", _GovMobClinic);
            contentValues.put("PrvtHos", _PrvtHos);
            contentValues.put("Pharma", _Pharma);
            contentValues.put("PrvtDoctor", _PrvtDoctor);
            contentValues.put("PrvtMobClinic", _PrvtMobClinic);
            contentValues.put("TrdiPract", _TrdiPract);
            contentValues.put("DrugSeller", _DrugSeller);
            contentValues.put("Other", _Other);
            contentValues.put("OtherSp", _OtherSp);
            contentValues.put("FaciDk", _FaciDk);
            contentValues.put("Refused", _Refused);

            contentValues.put("Cough", _Cough);
            contentValues.put("Breath", _Breath);
            contentValues.put("BreathDiffC", _BreathDiffC);
            contentValues.put("SeekCareCough", _SeekCareCough);
            contentValues.put("CoughCareLoc", _CoughCareLoc);
            contentValues.put("CoughCareLocOth", _CoughCareLocOth);
            contentValues.put("FeverPst2W", _FeverPst2W);
            contentValues.put("SeekCareFever", _SeekCareFever);
            contentValues.put("FeverCareLoc", _FeverCareLoc);
            contentValues.put("FeverCareLocOth", _FeverCareLocOth);
            contentValues.put("WgtLost", _WgtLost);
            contentValues.put("WgtGain", _WgtGain);
            contentValues.put("ChLessFeed", _ChLessFeed);
            contentValues.put("UnderWeight", _UnderWeight);
            contentValues.put("OverWeight", _OverWeight);
            contentValues.put("EvrVacc", _EvrVacc);
            contentValues.put("VaccCard", _VaccCard);
            contentValues.put("VaccCardPhoto", _VaccCardPhoto);
            contentValues.put("EvrVaccCard", _EvrVaccCard);
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
            contentValues.put("MemID", _MemID);
            contentValues.put("HHID", _HHID);

            contentValues.put("ChRthHead", _ChRthHead);
            contentValues.put("RthHeadOth", _RthHeadOth);
            contentValues.put("RthCaregiver", _RthCaregiver);
            contentValues.put("RthCaregiverOth", _RthCaregiverOth);
            contentValues.put("ChSize", _ChSize);
            contentValues.put("ChStatus", _ChStatus);
            contentValues.put("ChDiarrhea", _ChDiarrhea);
            contentValues.put("SeekCareDiarrhea", _SeekCareDiarrhea);
//            contentValues.put("DiarrheaCareLoc", _DiarrheaCareLoc);
//            contentValues.put("DiarrheaCareLocOth", _DiarrheaCareLocOth);

            contentValues.put("GovHos", _GovHos);
            contentValues.put("GovHelCenter", _GovHelCenter);
            contentValues.put("GovHelPost", _GovHelPost);
            contentValues.put("GovMobClinic", _GovMobClinic);
            contentValues.put("PrvtHos", _PrvtHos);
            contentValues.put("Pharma", _Pharma);
            contentValues.put("PrvtDoctor", _PrvtDoctor);
            contentValues.put("PrvtMobClinic", _PrvtMobClinic);
            contentValues.put("TrdiPract", _TrdiPract);
            contentValues.put("DrugSeller", _DrugSeller);
            contentValues.put("Other", _Other);
            contentValues.put("OtherSp", _OtherSp);
            contentValues.put("FaciDk", _FaciDk);
            contentValues.put("Refused", _Refused);

            contentValues.put("Cough", _Cough);
            contentValues.put("Breath", _Breath);
            contentValues.put("BreathDiffC", _BreathDiffC);
            contentValues.put("SeekCareCough", _SeekCareCough);
            contentValues.put("CoughCareLoc", _CoughCareLoc);
            contentValues.put("CoughCareLocOth", _CoughCareLocOth);
            contentValues.put("FeverPst2W", _FeverPst2W);
            contentValues.put("SeekCareFever", _SeekCareFever);
            contentValues.put("FeverCareLoc", _FeverCareLoc);
            contentValues.put("FeverCareLocOth", _FeverCareLocOth);
            contentValues.put("WgtLost", _WgtLost);
            contentValues.put("WgtGain", _WgtGain);
            contentValues.put("ChLessFeed", _ChLessFeed);
            contentValues.put("UnderWeight", _UnderWeight);
            contentValues.put("OverWeight", _OverWeight);
            contentValues.put("EvrVacc", _EvrVacc);
            contentValues.put("VaccCard", _VaccCard);
            contentValues.put("VaccCardPhoto", _VaccCardPhoto);
            contentValues.put("EvrVaccCard", _EvrVaccCard);
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
    @SuppressLint("Range")
    public List<ChildChar_DataModel> SelectAll(Context context, String SQL)
    {
        Connection C = new Connection(context);
        List<ChildChar_DataModel> data = new ArrayList<ChildChar_DataModel>();
        ChildChar_DataModel d = new ChildChar_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while(!cur.isAfterLast())
        {
            Count += 1;
            d = new ChildChar_DataModel();
            d._Count = Count;
            d._CID = cur.getString(cur.getColumnIndex("CID"));
            d._RespID = cur.getString(cur.getColumnIndex("RespID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._ChRthHead = cur.getString(cur.getColumnIndex("ChRthHead"));
            d._RthHeadOth = cur.getString(cur.getColumnIndex("RthHeadOth"));
            d._RthCaregiver = cur.getString(cur.getColumnIndex("RthCaregiver"));
            d._RthCaregiverOth = cur.getString(cur.getColumnIndex("RthCaregiverOth"));
            d._ChSize = cur.getString(cur.getColumnIndex("ChSize"));
            d._ChStatus = cur.getString(cur.getColumnIndex("ChStatus"));
            d._ChDiarrhea = cur.getString(cur.getColumnIndex("ChDiarrhea"));
            d._SeekCareDiarrhea = cur.getString(cur.getColumnIndex("SeekCareDiarrhea"));
//            d._DiarrheaCareLoc = cur.getString(cur.getColumnIndex("DiarrheaCareLoc"));
//            d._DiarrheaCareLocOth = cur.getString(cur.getColumnIndex("DiarrheaCareLocOth"));

            d._GovHos = cur.getString(cur.getColumnIndex("GovHos"));
            d._GovHelCenter = cur.getString(cur.getColumnIndex("GovHelCenter"));
            d._GovHelPost = cur.getString(cur.getColumnIndex("GovHelPost"));
            d._GovMobClinic = cur.getString(cur.getColumnIndex("GovMobClinic"));
            d._PrvtHos = cur.getString(cur.getColumnIndex("PrvtHos"));
            d._Pharma = cur.getString(cur.getColumnIndex("Pharma"));
            d._PrvtDoctor = cur.getString(cur.getColumnIndex("PrvtDoctor"));
            d._PrvtMobClinic = cur.getString(cur.getColumnIndex("PrvtMobClinic"));
            d._TrdiPract = cur.getString(cur.getColumnIndex("TrdiPract"));
            d._DrugSeller = cur.getString(cur.getColumnIndex("DrugSeller"));
            d._Other = cur.getString(cur.getColumnIndex("Other"));
            d._OtherSp = cur.getString(cur.getColumnIndex("OtherSp"));
            d._FaciDk = cur.getString(cur.getColumnIndex("FaciDk"));
            d._Refused = cur.getString(cur.getColumnIndex("Refused"));

            d._Cough = cur.getString(cur.getColumnIndex("Cough"));
            d._Breath = cur.getString(cur.getColumnIndex("Breath"));
            d._BreathDiffC = cur.getString(cur.getColumnIndex("BreathDiffC"));
            d._SeekCareCough = cur.getString(cur.getColumnIndex("SeekCareCough"));
            d._CoughCareLoc = cur.getString(cur.getColumnIndex("CoughCareLoc"));
            d._CoughCareLocOth = cur.getString(cur.getColumnIndex("CoughCareLocOth"));
            d._FeverPst2W = cur.getString(cur.getColumnIndex("FeverPst2W"));
            d._SeekCareFever = cur.getString(cur.getColumnIndex("SeekCareFever"));
            d._FeverCareLoc = cur.getString(cur.getColumnIndex("FeverCareLoc"));
            d._FeverCareLocOth = cur.getString(cur.getColumnIndex("FeverCareLocOth"));
            d._WgtLost = cur.getString(cur.getColumnIndex("WgtLost"));
            d._WgtGain = cur.getString(cur.getColumnIndex("WgtGain"));
            d._ChLessFeed = cur.getString(cur.getColumnIndex("ChLessFeed"));
            d._UnderWeight = cur.getString(cur.getColumnIndex("UnderWeight"));
            d._OverWeight = cur.getString(cur.getColumnIndex("OverWeight"));
            d._EvrVacc = cur.getString(cur.getColumnIndex("EvrVacc"));
            d._VaccCard = cur.getString(cur.getColumnIndex("VaccCard"));
            d._VaccCardPhoto = cur.getString(cur.getColumnIndex("VaccCardPhoto"));
            d._EvrVaccCard = cur.getString(cur.getColumnIndex("EvrVaccCard"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }
}