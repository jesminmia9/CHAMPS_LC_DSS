package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class tmpSpouseSerial_DataModel {

    private String _SpSerialID = "";

    public String getSpSerialID() {
        return _SpSerialID;
    }

    public void setSpSerialID(String newValue) {
        _SpSerialID = newValue;
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

    private String _SpSlEvDate = "";

    public String getSpSlEvDate() {
        return _SpSlEvDate;
    }

    public void setSpSlEvDate(String newValue) {
        _SpSlEvDate = newValue;
    }

    private String _NewSpSl = "";

    public String getNewSpSl() {
        return _NewSpSl;
    }

    public void setNewSpSl(String newValue) {
        _NewSpSl = newValue;
    }

    private String _OldSpSl = "";

    public String getOldSpSl() {
        return _OldSpSl;
    }

    public void setOldSpSl(String newValue) {
        _OldSpSl = newValue;
    }

    private String _SpVDate = "";

    public String getSpVDate() {
        return _SpVDate;
    }

    public void setSpVDate(String newValue) {
        _SpVDate = newValue;
    }

    private String _Rnd = "";

    public String getRnd() {
        return _Rnd;
    }

    public void setRnd(String newValue) {
        _Rnd = newValue;
    }

    private String _SpSlNote = "";

    public String getSpSlNote() {
        return _SpSlNote;
    }

    public void setSpSlNote(String newValue) {
        _SpSlNote = newValue;
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

    String TableName = "tmpSpouseSerial";

    public String SaveUpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try {
            if (C.Existence("Select * from " + TableName + "  Where SpSerialID='" + _SpSerialID + "' "))
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
            contentValues.put("SpSerialID", _SpSerialID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("SpSlEvDate", _SpSlEvDate);
            contentValues.put("NewSpSl", _NewSpSl);
            contentValues.put("OldSpSl", _OldSpSl);
            contentValues.put("SpVDate", _SpVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("SpSlNote", _SpSlNote);
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
            contentValues.put("SpSerialID", _SpSerialID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("SpSlEvDate", _SpSlEvDate);
            contentValues.put("NewSpSl", _NewSpSl);
            contentValues.put("OldSpSl", _OldSpSl);
            contentValues.put("SpVDate", _SpVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("SpSlNote", _SpSlNote);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            C.UpdateData(TableName, "SpSerialID", ("" + _SpSerialID + ""), contentValues);
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

    public List<tmpSpouseSerial_DataModel> SelectAll(Context context, String SQL) {
        Connection C = new Connection(context);
        List<tmpSpouseSerial_DataModel> data = new ArrayList<tmpSpouseSerial_DataModel>();
        tmpSpouseSerial_DataModel d = new tmpSpouseSerial_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Count += 1;
            d = new tmpSpouseSerial_DataModel();
            d._Count = Count;
            d._SpSerialID = cur.getString(cur.getColumnIndex("SpSerialID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._SpSlEvDate = cur.getString(cur.getColumnIndex("SpSlEvDate"));
            d._NewSpSl = cur.getString(cur.getColumnIndex("NewSpSl"));
            d._OldSpSl = cur.getString(cur.getColumnIndex("OldSpSl"));
            d._SpVDate = cur.getString(cur.getColumnIndex("SpVDate"));
            d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
            d._SpSlNote = cur.getString(cur.getColumnIndex("SpSlNote"));
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