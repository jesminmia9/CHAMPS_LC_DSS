package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class tmpFatherSerial_DataModel {

    private String _FathSerialID = "";

    public String getFathSerialID() {
        return _FathSerialID;
    }

    public void setFathSerialID(String newValue) {
        _FathSerialID = newValue;
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

    private String _FathSlEvDate = "";

    public String getFathSlEvDate() {
        return _FathSlEvDate;
    }

    public void setFathSlEvDate(String newValue) {
        _FathSlEvDate = newValue;
    }

    private String _NewFathSl = "";

    public String getNewFathSl() {
        return _NewFathSl;
    }

    public void setNewFathSl(String newValue) {
        _NewFathSl = newValue;
    }

    private String _OldFathSl = "";

    public String getOldFathSl() {
        return _OldFathSl;
    }

    public void setOldFathSl(String newValue) {
        _OldFathSl = newValue;
    }

    private String _FathVDate = "";

    public String getFathVDate() {
        return _FathVDate;
    }

    public void setFathVDate(String newValue) {
        _FathVDate = newValue;
    }

    private String _Rnd = "";

    public String getRnd() {
        return _Rnd;
    }

    public void setRnd(String newValue) {
        _Rnd = newValue;
    }

    private String _FathSlNote = "";

    public String getFathSlNote() {
        return _FathSlNote;
    }

    public void setFathSlNote(String newValue) {
        _FathSlNote = newValue;
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

    String TableName = "tmpFatherSerial";

    public String SaveUpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try {
            if (C.Existence("Select * from " + TableName + "  Where FathSerialID='" + _FathSerialID + "' "))
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
            contentValues.put("FathSerialID", _FathSerialID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("FathSlEvDate", _FathSlEvDate);
            contentValues.put("NewFathSl", _NewFathSl);
            contentValues.put("OldFathSl", _OldFathSl);
            contentValues.put("FathVDate", _FathVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("FathSlNote", _FathSlNote);
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
            contentValues.put("FathSerialID", _FathSerialID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("FathSlEvDate", _FathSlEvDate);
            contentValues.put("NewFathSl", _NewFathSl);
            contentValues.put("OldFathSl", _OldFathSl);
            contentValues.put("FathVDate", _FathVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("FathSlNote", _FathSlNote);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            C.UpdateData(TableName, "FathSerialID", ("" + _FathSerialID + ""), contentValues);
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

    public List<tmpFatherSerial_DataModel> SelectAll(Context context, String SQL) {
        Connection C = new Connection(context);
        List<tmpFatherSerial_DataModel> data = new ArrayList<tmpFatherSerial_DataModel>();
        tmpFatherSerial_DataModel d = new tmpFatherSerial_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Count += 1;
            d = new tmpFatherSerial_DataModel();
            d._Count = Count;
            d._FathSerialID = cur.getString(cur.getColumnIndex("FathSerialID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._FathSlEvDate = cur.getString(cur.getColumnIndex("FathSlEvDate"));
            d._NewFathSl = cur.getString(cur.getColumnIndex("NewFathSl"));
            d._OldFathSl = cur.getString(cur.getColumnIndex("OldFathSl"));
            d._FathVDate = cur.getString(cur.getColumnIndex("FathVDate"));
            d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
            d._FathSlNote = cur.getString(cur.getColumnIndex("FathSlNote"));
            d._DeviceID = cur.getString(cur.getColumnIndex("DeviceID"));
            d._EntryUser = cur.getString(cur.getColumnIndex("EntryUser"));
            d._isdelete = cur.getInt(cur.getColumnIndex("isdelete"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }

    public String getDataName(String code, String HHID, Context context) {
        Connection C = new Connection(context);
        String result = "";

        if ("00".equals(code)) {
            result = "00-Not a Member of this Household";
        } else if ("0".equals(code)) {
            result = "0-Not a Member of this Household";
        } else {
            String query = "SELECT MSlNo||'-'||Name FROM tmpMember WHERE MSlNo = '" + code + "' AND HHID = '" + HHID + "'";
            Cursor cursor = C.ReadData(query);

            if (cursor != null) {
                cursor.moveToFirst();
                if (!cursor.isAfterLast()) {
                    result = cursor.getString(0);
                }
                cursor.close();
            }
        }

        return result;
    }

}