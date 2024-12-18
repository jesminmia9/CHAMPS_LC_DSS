package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class Occupation_DataModel {

    private String _OcpID = "";
    public String getOcpID(){
        return _OcpID;
    }
    public void setOcpID(String newValue){
        _OcpID = newValue;
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
    private String _OcpEvDate = "";
    public String getOcpEvDate(){
        return _OcpEvDate;
    }
    public void setOcpEvDate(String newValue){
        _OcpEvDate = newValue;
    }
    private String _NewOcp = "";
    public String getNewOcp(){
        return _NewOcp;
    }
    public void setNewOcp(String newValue){
        _NewOcp = newValue;
    }
    private String _NewEmpStat = "";
    public String getNewEmpStat(){
        return _NewEmpStat;
    }
    public void setNewEmpStat(String newValue){
        _NewEmpStat = newValue;
    }
    private String _OldOcp = "";
    public String getOldOcp(){
        return _OldOcp;
    }
    public void setOldOcp(String newValue){
        _OldOcp = newValue;
    }
    private String _OldEmpStat = "";
    public String getOldEmpStat(){
        return _OldEmpStat;
    }
    public void setOldEmpStat(String newValue){
        _OldEmpStat = newValue;
    }
    private String _OcpVDate = "";
    public String getOcpVDate(){
        return _OcpVDate;
    }
    public void setOcpVDate(String newValue){
        _OcpVDate = newValue;
    }
    private String _Rnd = "";
    public String getRnd(){
        return _Rnd;
    }
    public void setRnd(String newValue){
        _Rnd = newValue;
    }
    private String _OcpNote = "";
    public String getOcpNote(){
        return _OcpNote;
    }
    public void setOcpNote(String newValue){
        _OcpNote = newValue;
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

    String TableName = "Occupation";

    public String SaveUpdateData(Context context)
    {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try
        {
            if(C.Existence("Select * from "+ TableName +"  Where OcpID='"+ _OcpID +"' "))
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
            contentValues.put("OcpID", _OcpID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("OcpEvDate", _OcpEvDate);
            contentValues.put("NewOcp", _NewOcp);
            contentValues.put("NewEmploymentStatus", _NewEmpStat);
            contentValues.put("OldOcp", _OldOcp);
            contentValues.put("OldEmploymentStatus", _OldEmpStat);
            contentValues.put("OcpVDate", _OcpVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("OcpNote", _OcpNote);
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
            contentValues.put("OcpID", _OcpID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("OcpEvDate", _OcpEvDate);
            contentValues.put("NewOcp", _NewOcp);
            contentValues.put("NewEmploymentStatus", _NewEmpStat);
            contentValues.put("OldOcp", _OldOcp);
            contentValues.put("OldEmploymentStatus", _OldEmpStat);
            contentValues.put("OcpVDate", _OcpVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("OcpNote", _OcpNote);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            C.UpdateData(TableName, "OcpID", (""+ _OcpID +""), contentValues);
        }
        catch(Exception  e)
        {
            response = e.getMessage();
        }
        return response;
    }

    int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
    public List<Occupation_DataModel> SelectAll(Context context, String SQL)
    {
        Connection C = new Connection(context);
        List<Occupation_DataModel> data = new ArrayList<Occupation_DataModel>();
        Occupation_DataModel d = new Occupation_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while(!cur.isAfterLast())
        {
            Count += 1;
            d = new Occupation_DataModel();
            d._Count = Count;
            d._OcpID = cur.getString(cur.getColumnIndex("OcpID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._OcpEvDate = cur.getString(cur.getColumnIndex("OcpEvDate"));
            d._NewOcp = cur.getString(cur.getColumnIndex("NewOcp"));
            d._NewEmpStat = cur.getString(cur.getColumnIndex("NewEmploymentStatus"));
            d._OldOcp = cur.getString(cur.getColumnIndex("OldOcp"));
            d._OldEmpStat = cur.getString(cur.getColumnIndex("OldEmploymentStatus"));
            d._OcpVDate = cur.getString(cur.getColumnIndex("OcpVDate"));
            d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
            d._OcpNote = cur.getString(cur.getColumnIndex("OcpNote"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }
}