package forms_datamodel;

import android.content.Context;
import android.database.Cursor;
import Common.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import Common.Global;
import android.content.ContentValues;
public class Caregiver_DataModel{

    private String _CaregID = "";
    public String getCaregID(){
        return _CaregID;
    }
    public void setCaregID(String newValue){
        _CaregID = newValue;
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
    private String _RespName = "";
    public String getRespName(){
        return _RespName;
    }
    public void setRespName(String newValue){
        _RespName = newValue;
    }
    private String _RespID = "";
    public String getRespID(){
        return _RespID;
    }
    public void setRespID(String newValue){
        _RespID = newValue;
    }
    private String _IsMem = "";
    public String getIsMem(){
        return _IsMem;
    }
    public void setIsMem(String newValue){
        _IsMem = newValue;
    }
    private String _CMemID = "";
    public String getCMemID(){
        return _CMemID;
    }
    public void setCMemID(String newValue){
        _CMemID = newValue;
    }
    private String _PriCaregiver = "";
    public String getPriCaregiver(){
        return _PriCaregiver;
    }
    public void setPriCaregiver(String newValue){
        _PriCaregiver = newValue;
    }
    private String _WhoPriCaregiver = "";
    public String getWhoPriCaregiver(){
        return _WhoPriCaregiver;
    }
    public void setWhoPriCaregiver(String newValue){
        _WhoPriCaregiver = newValue;
    }
    private String _WhoPrCaregOth = "";
    public String getWhoPrCaregOth(){
        return _WhoPrCaregOth;
    }
    public void setWhoPrCaregOth(String newValue){
        _WhoPrCaregOth = newValue;
    }
    private String _PriCaregID = "";
    public String getPriCaregID(){
        return _PriCaregID;
    }
    public void setPriCaregID(String newValue){
        _PriCaregID = newValue;
    }
    private String _RthChild = "";
    public String getRthChild(){
        return _RthChild;
    }
    public void setRthChild(String newValue){
        _RthChild = newValue;
    }
    private String _RthChildOth = "";
    public String getRthChildOth(){
        return _RthChildOth;
    }
    public void setRthChildOth(String newValue){
        _RthChildOth = newValue;
    }
    private String _ReligGroup = "";
    public String getReligGroup(){
        return _ReligGroup;
    }
    public void setReligGroup(String newValue){
        _ReligGroup = newValue;
    }
    private String _ReligGroupOth = "";
    public String getReligGroupOth(){
        return _ReligGroupOth;
    }
    public void setReligGroupOth(String newValue){
        _ReligGroupOth = newValue;
    }
    private String _EthnicGroup = "";
    public String getEthnicGroup(){
        return _EthnicGroup;
    }
    public void setEthnicGroup(String newValue){
        _EthnicGroup = newValue;
    }
    private String _EthnicGroupOth = "";
    public String getEthnicGroupOth(){
        return _EthnicGroupOth;
    }
    public void setEthnicGroupOth(String newValue){
        _EthnicGroupOth = newValue;
    }
    private String _CrgvEduY = "";
    public String getCrgvEduY(){
        return _CrgvEduY;
    }
    public void setCrgvEduY(String newValue){
        _CrgvEduY = newValue;
    }
    private String _CrgvEduYDk = "";
    public String getCrgvEduYDk(){
        return _CrgvEduYDk;
    }
    public void setCrgvEduYDk(String newValue){
        _CrgvEduYDk = newValue;
    }
    private String _EmpStatus = "";
    public String getEmpStatus(){
        return _EmpStatus;
    }
    public void setEmpStatus(String newValue){
        _EmpStatus = newValue;
    }
    private String _EmployStsOth = "";
    public String getEmployStsOth(){
        return _EmployStsOth;
    }
    public void setEmployStsOth(String newValue){
        _EmployStsOth = newValue;
    }
    private String _MainOccu = "";
    public String getMainOccu(){
        return _MainOccu;
    }
    public void setMainOccu(String newValue){
        _MainOccu = newValue;
    }
    private String _MainOccuDk = "";
    public String getMainOccuDk(){
        return _MainOccuDk;
    }
    public void setMainOccuDk(String newValue){
        _MainOccuDk = newValue;
    }
    private String _MainOccuOth = "";
    public String getMainOccuOth(){
        return _MainOccuOth;
    }
    public void setMainOccuOth(String newValue){
        _MainOccuOth = newValue;
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

    String TableName = "Caregiver";

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
            contentValues.put("CaregID", _CaregID);
            contentValues.put("MemID", _MemID);
            contentValues.put("HHID", _HHID);
            contentValues.put("RespName", _RespName);
            contentValues.put("RespID", _RespID);
            contentValues.put("IsMem", _IsMem);
            contentValues.put("CMemID", _CMemID);
            contentValues.put("PriCaregiver", _PriCaregiver);
            contentValues.put("WhoPriCaregiver", _WhoPriCaregiver);
            contentValues.put("WhoPrCaregOth", _WhoPrCaregOth);
            contentValues.put("PriCaregID", _PriCaregID);
            contentValues.put("RthChild", _RthChild);
            contentValues.put("RthChildOth", _RthChildOth);
            contentValues.put("ReligGroup", _ReligGroup);
            contentValues.put("ReligGroupOth", _ReligGroupOth);
            contentValues.put("EthnicGroup", _EthnicGroup);
            contentValues.put("EthnicGroupOth", _EthnicGroupOth);
            contentValues.put("CrgvEduY", _CrgvEduY);
            contentValues.put("CrgvEduYDk", _CrgvEduYDk);
            contentValues.put("EmpStatus", _EmpStatus);
            contentValues.put("EmployStsOth", _EmployStsOth);
            contentValues.put("MainOccu", _MainOccu);
            contentValues.put("MainOccuDk", _MainOccuDk);
            contentValues.put("MainOccuOth", _MainOccuOth);
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
            contentValues.put("CaregID", _CaregID);
            contentValues.put("MemID", _MemID);
            contentValues.put("HHID", _HHID);
            contentValues.put("RespName", _RespName);
            contentValues.put("RespID", _RespID);
            contentValues.put("IsMem", _IsMem);
            contentValues.put("CMemID", _CMemID);
            contentValues.put("PriCaregiver", _PriCaregiver);
            contentValues.put("WhoPriCaregiver", _WhoPriCaregiver);
            contentValues.put("WhoPrCaregOth", _WhoPrCaregOth);
            contentValues.put("PriCaregID", _PriCaregID);
            contentValues.put("RthChild", _RthChild);
            contentValues.put("RthChildOth", _RthChildOth);
            contentValues.put("ReligGroup", _ReligGroup);
            contentValues.put("ReligGroupOth", _ReligGroupOth);
            contentValues.put("EthnicGroup", _EthnicGroup);
            contentValues.put("EthnicGroupOth", _EthnicGroupOth);
            contentValues.put("CrgvEduY", _CrgvEduY);
            contentValues.put("CrgvEduYDk", _CrgvEduYDk);
            contentValues.put("EmpStatus", _EmpStatus);
            contentValues.put("EmployStsOth", _EmployStsOth);
            contentValues.put("MainOccu", _MainOccu);
            contentValues.put("MainOccuDk", _MainOccuDk);
            contentValues.put("MainOccuOth", _MainOccuOth);
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
    public List<Caregiver_DataModel> SelectAll(Context context, String SQL)
    {
        Connection C = new Connection(context);
        List<Caregiver_DataModel> data = new ArrayList<Caregiver_DataModel>();
        Caregiver_DataModel d = new Caregiver_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while(!cur.isAfterLast())
        {
            Count += 1;
            d = new Caregiver_DataModel();
            d._Count = Count;
            d._CaregID = cur.getString(cur.getColumnIndex("CaregID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._RespName = cur.getString(cur.getColumnIndex("RespName"));
            d._RespID = cur.getString(cur.getColumnIndex("RespID"));
            d._IsMem = cur.getString(cur.getColumnIndex("IsMem"));
            d._CMemID = cur.getString(cur.getColumnIndex("CMemID"));
            d._PriCaregiver = cur.getString(cur.getColumnIndex("PriCaregiver"));
            d._WhoPriCaregiver = cur.getString(cur.getColumnIndex("WhoPriCaregiver"));
            d._WhoPrCaregOth = cur.getString(cur.getColumnIndex("WhoPrCaregOth"));
            d._PriCaregID = cur.getString(cur.getColumnIndex("PriCaregID"));
            d._RthChild = cur.getString(cur.getColumnIndex("RthChild"));
            d._RthChildOth = cur.getString(cur.getColumnIndex("RthChildOth"));
            d._ReligGroup = cur.getString(cur.getColumnIndex("ReligGroup"));
            d._ReligGroupOth = cur.getString(cur.getColumnIndex("ReligGroupOth"));
            d._EthnicGroup = cur.getString(cur.getColumnIndex("EthnicGroup"));
            d._EthnicGroupOth = cur.getString(cur.getColumnIndex("EthnicGroupOth"));
            d._CrgvEduY = cur.getString(cur.getColumnIndex("CrgvEduY"));
            d._CrgvEduYDk = cur.getString(cur.getColumnIndex("CrgvEduYDk"));
            d._EmpStatus = cur.getString(cur.getColumnIndex("EmpStatus"));
            d._EmployStsOth = cur.getString(cur.getColumnIndex("EmployStsOth"));
            d._MainOccu = cur.getString(cur.getColumnIndex("MainOccu"));
            d._MainOccuDk = cur.getString(cur.getColumnIndex("MainOccuDk"));
            d._MainOccuOth = cur.getString(cur.getColumnIndex("MainOccuOth"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }
}