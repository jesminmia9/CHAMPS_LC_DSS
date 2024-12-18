package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class Relation_DataModel {

    private String _RthID = "";

    public String getRthID() {
        return _RthID;
    }

    public void setRthID(String newValue) {
        _RthID = newValue;
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

    private String _RthEvDate = "";

    public String getRthEvDate() {
        return _RthEvDate;
    }

    public void setRthEvDate(String newValue) {
        _RthEvDate = newValue;
    }

    private String _HeadShipRth = "";

    public String getHeadShipRth() {
        return _HeadShipRth;
    }

    public void setHeadShipRth(String _HeadShipRth) {
        this._HeadShipRth = _HeadShipRth;
    }

    private String _NewRth = "";

    public String getNewRth() {
        return _NewRth;
    }

    public void setNewRth(String newValue) {
        _NewRth = newValue;
    }

    private String _OldRth = "";

    public String getOldRth() {
        return _OldRth;
    }

    public void setOldRth(String newValue) {
        _OldRth = newValue;
    }

    private String _RthVDate = "";

    public String getRthVDate() {
        return _RthVDate;
    }

    public void setRthVDate(String newValue) {
        _RthVDate = newValue;
    }

    private String _Rnd = "";

    public String getRnd() {
        return _Rnd;
    }

    public void setRnd(String newValue) {
        _Rnd = newValue;
    }

    private String _RthNote = "";

    public String getRthNote() {
        return _RthNote;
    }

    public void setRthNote(String newValue) {
        _RthNote = newValue;
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

    String TableName = "Relation";

    public String SaveUpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try {
            if (C.Existence("Select * from " + TableName + "  Where RthID='" + _RthID + "' "))
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
            contentValues.put("RthID", _RthID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("RthEvDate", _RthEvDate);
            contentValues.put("NewRth", _NewRth);
            contentValues.put("HeadShipRth", _HeadShipRth);
            contentValues.put("OldRth", _OldRth);
            contentValues.put("RthVDate", _RthVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("RthNote", _RthNote);
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
            contentValues.put("RthID", _RthID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("RthEvDate", _RthEvDate);
            contentValues.put("NewRth", _NewRth);
            contentValues.put("HeadShipRth", _HeadShipRth);
            contentValues.put("OldRth", _OldRth);
            contentValues.put("RthVDate", _RthVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("RthNote", _RthNote);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            C.UpdateData(TableName, "RthID", ("" + _RthID + ""), contentValues);
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

    public List<Relation_DataModel> SelectAll(Context context, String SQL) {
        Connection C = new Connection(context);
        List<Relation_DataModel> data = new ArrayList<Relation_DataModel>();
        Relation_DataModel d = new Relation_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Count += 1;
            d = new Relation_DataModel();
            d._Count = Count;
            d._RthID = cur.getString(cur.getColumnIndex("RthID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._RthEvDate = cur.getString(cur.getColumnIndex("RthEvDate"));
            d._NewRth = cur.getString(cur.getColumnIndex("NewRth"));
            d._OldRth = cur.getString(cur.getColumnIndex("OldRth"));
            d._RthVDate = cur.getString(cur.getColumnIndex("RthVDate"));
            d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
            d._RthNote = cur.getString(cur.getColumnIndex("RthNote"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }

    public String getDataName(String code, Context context) {
        Connection C = new Connection(context);
        String result = "";
        String query = "SELECT Code||'-'||Name FROM RTH WHERE Code = '" + code + "'";
        Cursor cursor = C.ReadData(query);

        if (cursor != null) {
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                result = cursor.getString(0);
            }
            cursor.close();
        }

        return result;
    }
}