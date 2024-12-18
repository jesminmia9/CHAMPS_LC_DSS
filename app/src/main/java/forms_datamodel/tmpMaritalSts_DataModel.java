package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class tmpMaritalSts_DataModel {

    private String _MaritStsID = "";

    public String getMaritStsID() {
        return _MaritStsID;
    }

    public void setMaritStsID(String newValue) {
        _MaritStsID = newValue;
    }

    private String _HHID = "";

    public String getHHID() {
        return _HHID;
    }

    public void setHHID(String newValue) {
        _HHID = newValue;
    }

    private String _MemID = "";

    public String getMemID() {
        return _MemID;
    }

    public void setMemID(String newValue) {
        _MemID = newValue;
    }

    private String _MSEvType = "";

    public String getMSEvType() {
        return _MSEvType;
    }

    public void setMSEvType(String newValue) {
        _MSEvType = newValue;
    }

    private String _MSEvDate = "";

    public String getMSEvDate() {
        return _MSEvDate;
    }

    public void setMSEvDate(String newValue) {
        _MSEvDate = newValue;
    }

    private String _PrevMS = "";

    public String getPrevMS() {
        return _PrevMS;
    }

    public void setPrevMS(String newValue) {
        _PrevMS = newValue;
    }

    private String _MSVDate = "";

    public String getMSVDate() {
        return _MSVDate;
    }

    public void setMSVDate(String newValue) {
        _MSVDate = newValue;
    }

    private String _Rnd = "";

    public String getRnd() {
        return _Rnd;
    }

    public void setRnd(String newValue) {
        _Rnd = newValue;
    }

    private String _MSNote = "";

    public String getMSNote() {
        return _MSNote;
    }

    public void setMSNote(String newValue) {
        _MSNote = newValue;
    }

    private String _StartTime = "";

    public void setStartTime(String newValue) {
        _StartTime = newValue;
    }

    private String _EndTime = "";

    public void setEndTime(String newValue) {
        _EndTime = newValue;
    }

    private String _DeviceID = "";

    public void setDeviceID(String newValue) {
        _DeviceID = newValue;
    }

    private String _EntryUser = "";

    public void setEntryUser(String newValue) {
        _EntryUser = newValue;
    }

    public String getDeviceID() {
        return _DeviceID;
    }

    public String getEntryUser() {
        return _EntryUser;
    }

    private int _isdelete;

    public int get_isdelete() {
        return _isdelete;
    }

    public void set_isdelete(int _isdelete) {
        this._isdelete = _isdelete;
    }

    private String _Lat = "";

    public void setLat(String newValue) {
        _Lat = newValue;
    }

    private String _Lon = "";

    public void setLon(String newValue) {
        _Lon = newValue;
    }

    private String _EnDt = Global.DateTimeNowYMDHMS();
    private int _Upload = 2;
    private String _modifyDate = Global.DateTimeNowYMDHMS();

    String TableName = "tmpMaritalSts";

    public String SaveUpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try {
            if (C.Existence("Select * from " + TableName + "  Where MaritStsID='" + _MaritStsID + "' "))
                response = UpdateData(context);
            else
                response = SaveData(context);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return response;
    }

    Connection C;

    private String SaveData(Context context) {
        String response = "";
        C = new Connection(context);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("MaritStsID", _MaritStsID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("MSEvType", _MSEvType);
            contentValues.put("MSEvDate", _MSEvDate);
            contentValues.put("PrevMS", _PrevMS);
            contentValues.put("MSVDate", _MSVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("MSNote", _MSNote);
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
        } catch (Exception e) {
            response = e.getMessage();
        }
        return response;
    }

    private String UpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("MaritStsID", _MaritStsID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("MSEvType", _MSEvType);
            contentValues.put("MSEvDate", _MSEvDate);
            contentValues.put("PrevMS", _PrevMS);
            contentValues.put("MSVDate", _MSVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("MSNote", _MSNote);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            C.UpdateData(TableName, "MaritStsID", ("" + _MaritStsID + ""), contentValues);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return response;
    }

    int Count = 0;
    private int _Count = 0;

    public int getCount() {
        return _Count;
    }

    public List<tmpMaritalSts_DataModel> SelectAll(Context context, String SQL) {
        Connection C = new Connection(context);
        List<tmpMaritalSts_DataModel> data = new ArrayList<tmpMaritalSts_DataModel>();
        tmpMaritalSts_DataModel d = new tmpMaritalSts_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Count += 1;
            d = new tmpMaritalSts_DataModel();
            d._Count = Count;
            d._MaritStsID = cur.getString(cur.getColumnIndex("MaritStsID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._MSEvType = cur.getString(cur.getColumnIndex("MSEvType"));
            d._MSEvDate = cur.getString(cur.getColumnIndex("MSEvDate"));
            d._PrevMS = cur.getString(cur.getColumnIndex("PrevMS"));
            d._MSVDate = cur.getString(cur.getColumnIndex("MSVDate"));
            d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
            d._MSNote = cur.getString(cur.getColumnIndex("MSNote"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }

    public List<tmpMaritalSts_DataModel> SelectAllByMember(Context context, String SQL) {
        Log.d("JM", SQL);
        Connection C = new Connection(context);
        List<tmpMaritalSts_DataModel> data = new ArrayList<tmpMaritalSts_DataModel>();
        tmpMaritalSts_DataModel d = new tmpMaritalSts_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {

            d = new tmpMaritalSts_DataModel();
            d._MaritStsID = cur.getString(cur.getColumnIndex("MaritStsID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._MSEvType = cur.getString(cur.getColumnIndex("MSEvType"));
            d._MSEvDate = cur.getString(cur.getColumnIndex("MSEvDate"));
            d._PrevMS = cur.getString(cur.getColumnIndex("PrevMS"));
            d._MSVDate = cur.getString(cur.getColumnIndex("MSVDate"));
            d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
            d._MSNote = cur.getString(cur.getColumnIndex("MSNote"));
            d._modifyDate = cur.getString(cur.getColumnIndex("modifyDate"));
            d._DeviceID = cur.getString(cur.getColumnIndex("DeviceID"));
            d._EntryUser = cur.getString(cur.getColumnIndex("EntryUser"));
            d._isdelete = cur.getInt(cur.getColumnIndex("isdelete"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }
}