package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class tmpEducation_DataModel {

    private String _EduID = "";

    public String getEduID() {
        return _EduID;
    }

    public void setEduID(String newValue) {
        _EduID = newValue;
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

    private String _EduEvDate = "";

    public String getEduEvDate() {
        return _EduEvDate;
    }

    public void setEduEvDate(String newValue) {
        _EduEvDate = newValue;
    }

    private String _NewEdu = "";

    public String getNewEdu() {
        return _NewEdu;
    }

    public void setNewEdu(String newValue) {
        _NewEdu = newValue;
    }

    private String _OldEdu = "";

    public String getOldEdu() {
        return _OldEdu;
    }

    public void setOldEdu(String newValue) {
        _OldEdu = newValue;
    }

    private String _EduVDate = "";

    public String getEduVDate() {
        return _EduVDate;
    }

    public void setEduVDate(String newValue) {
        _EduVDate = newValue;
    }

    private String _Rnd = "";

    public String getRnd() {
        return _Rnd;
    }

    public void setRnd(String newValue) {
        _Rnd = newValue;
    }

    private String _EduNote = "";

    public String getEduNote() {
        return _EduNote;
    }

    public void setEduNote(String newValue) {
        _EduNote = newValue;
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

    private String _Lat = "";

    public void setLat(String newValue) {
        _Lat = newValue;
    }

    private String _Lon = "";

    public void setLon(String newValue) {
        _Lon = newValue;
    }
    private int _isdelete;

    public int get_isdelete() {
        return _isdelete;
    }

    public void set_isdelete(int _isdelete) {
        this._isdelete = _isdelete;
    }
    private String _EnDt = Global.DateTimeNowYMDHMS();
    private int _Upload = 2;
    private String _modifyDate = Global.DateTimeNowYMDHMS();

    String TableName = "tmpEducation";

    public String SaveUpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try {
            if (C.Existence("Select * from " + TableName + "  Where EduID='" + _EduID + "' "))
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
            contentValues.put("EduID", _EduID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("EduEvDate", _EduEvDate);
            contentValues.put("NewEdu", _NewEdu);
            contentValues.put("OldEdu", _OldEdu);
            contentValues.put("EduVDate", _EduVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("EduNote", _EduNote);
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
            contentValues.put("EduID", _EduID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("EduEvDate", _EduEvDate);
            contentValues.put("NewEdu", _NewEdu);
            contentValues.put("OldEdu", _OldEdu);
            contentValues.put("EduVDate", _EduVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("EduNote", _EduNote);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            C.UpdateData(TableName, "EduID", ("" + _EduID + ""), contentValues);
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

    public List<tmpEducation_DataModel> SelectAll(Context context, String SQL) {
        Connection C = new Connection(context);
        List<tmpEducation_DataModel> data = new ArrayList<tmpEducation_DataModel>();
        tmpEducation_DataModel d = new tmpEducation_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Count += 1;
            d = new tmpEducation_DataModel();
            d._Count = Count;
            d._EduID = cur.getString(cur.getColumnIndex("EduID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._EduEvDate = cur.getString(cur.getColumnIndex("EduEvDate"));
            d._NewEdu = cur.getString(cur.getColumnIndex("NewEdu"));
            d._OldEdu = cur.getString(cur.getColumnIndex("OldEdu"));
            d._EduVDate = cur.getString(cur.getColumnIndex("EduVDate"));
            d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
            d._EduNote = cur.getString(cur.getColumnIndex("EduNote"));
            d._DeviceID = cur.getString(cur.getColumnIndex("DeviceID"));
            d._EntryUser = cur.getString(cur.getColumnIndex("EntryUser"));
            d._isdelete = cur.getInt(cur.getColumnIndex("isdelete"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }

    public String getDataName(String code, Context context) {
        Connection C = new Connection(context);
        String result = "";
        String query = "SELECT Code||'-'||Name FROM EDU WHERE Code = '"+ code +"'";
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