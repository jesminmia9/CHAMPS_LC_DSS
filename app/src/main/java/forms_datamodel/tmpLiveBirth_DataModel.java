package forms_datamodel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class tmpLiveBirth_DataModel {

    private String _LiveBirthID = "";
    private String _NAT = "";
    public String getNAT() {
        return _NAT;
    }

    public void setNAT(String _NAT) {
        this._NAT = _NAT;
    }

    public String getLiveBirthID() {
        return _LiveBirthID;
    }

    public void setLiveBirthID(String newValue) {
        _LiveBirthID = newValue;
    }

    private String _DeliveryID = "";

    public String getDeliveryID() {
        return _DeliveryID;
    }

    public void setDeliveryID(String _DeliveryID) {
        this._DeliveryID = _DeliveryID;
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

    private String _LBMomID = "";

    public String getLBMomID() {
        return _LBMomID;
    }

    public void setLBMomID(String newValue) {
        _LBMomID = newValue;
    }

    private String _LBDOB = "";

    public String getLBDOB() {
        return _LBDOB;
    }

    public void setLBDOB(String newValue) {
        _LBDOB = newValue;
    }

    private String _LBTime = "";

    public String getLBTime() {
        return _LBTime;
    }

    public void setLBTime(String newValue) {
        _LBTime = newValue;
    }

    private String _LBTimeType = "";

    public String getLBTimeType() {
        return _LBTimeType;
    }

    public void setLBTimeType(String newValue) {
        _LBTimeType = newValue;
    }

    private String _LBDPlace = "";

    public String getLBDPlace() {
        return _LBDPlace;
    }

    public void setLBDPlace(String newValue) {
        _LBDPlace = newValue;
    }

    private String _LBPlaceOth = "";

    public String getLBPlaceOth() {
        return _LBPlaceOth;
    }

    public void setLBPlaceOth(String newValue) {
        _LBPlaceOth = newValue;
    }

    private String _LBDoctor = "";

    public String getLBDoctor() {
        return _LBDoctor;
    }

    public void setLBDoctor(String newValue) {
        _LBDoctor = newValue;
    }

    private String _LBNurse = "";

    public String getLBNurse() {
        return _LBNurse;
    }

    public void setLBNurse(String newValue) {
        _LBNurse = newValue;
    }

    private String _LBMidwife = "";

    public String getLBMidwife() {
        return _LBMidwife;
    }

    public void setLBMidwife(String newValue) {
        _LBMidwife = newValue;
    }

    private String _LBTBA = "";

    public String getLBTBA() {
        return _LBTBA;
    }

    public void setLBTBA(String newValue) {
        _LBTBA = newValue;
    }

    private String _LBCHW = "";

    public String getLBCHW() {
        return _LBCHW;
    }

    public void setLBCHW(String newValue) {
        _LBCHW = newValue;
    }

    private String _LBRF = "";

    public String getLBRF() {
        return _LBRF;
    }

    public void setLBRF(String newValue) {
        _LBRF = newValue;
    }

    private String _LBOth = "";

    public String getLBOth() {
        return _LBOth;
    }

    public void setLBOth(String newValue) {
        _LBOth = newValue;
    }

    private String _LBOthSpecify = "";

    public String getLBOthSpecify() {
        return _LBOthSpecify;
    }

    public void setLBOthSpecify(String newValue) {
        _LBOthSpecify = newValue;
    }

    private String _LBDk = "";

    public String getLBDk() {
        return _LBDk;
    }

    public void setLBDk(String newValue) {
        _LBDk = newValue;
    }

    private String _LBRfs = "";

    public String getLBRfs() {
        return _LBRfs;
    }

    public void setLBRfs(String newValue) {
        _LBRfs = newValue;
    }

    private String _LBType = "";

    public String getLBType() {
        return _LBType;
    }

    public void setLBType(String newValue) {
        _LBType = newValue;
    }

    private String _LBVDate = "";

    public String getLBVDate() {
        return _LBVDate;
    }

    public void setLBVDate(String newValue) {
        _LBVDate = newValue;
    }

    private String _Rnd = "";

    public String getRnd() {
        return _Rnd;
    }

    public void setRnd(String newValue) {
        _Rnd = newValue;
    }

    private String _LBNote = "";

    public String getLBNote() {
        return _LBNote;
    }

    public void setLBNote(String newValue) {
        _LBNote = newValue;
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

    String TableName = "tmpLiveBirth";

    public String SaveUpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try {
            if (C.Existence("Select * from " + TableName + "  Where LiveBirthID='" + _LiveBirthID + "' "))
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
            contentValues.put("LiveBirthID", _LiveBirthID);
            contentValues.put("DeliveryID", _DeliveryID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("LBMomID", _LBMomID);
            contentValues.put("LBDOB", _LBDOB);
            contentValues.put("LBTime", _LBTime);
            contentValues.put("LBTimeType", _LBTimeType);
            contentValues.put("LBDPlace", _LBDPlace);
            contentValues.put("LBPlaceOth", _LBPlaceOth);
            contentValues.put("LBDoctor", _LBDoctor);
            contentValues.put("LBNurse", _LBNurse);
            contentValues.put("LBMidwife", _LBMidwife);
            contentValues.put("LBTBA", _LBTBA);
            contentValues.put("LBCHW", _LBCHW);
            contentValues.put("LBRF", _LBRF);
            contentValues.put("LBOth", _LBOth);
            contentValues.put("LBOthSpecify", _LBOthSpecify);
            contentValues.put("LBDk", _LBDk);
            contentValues.put("LBRfs", _LBRfs);
            contentValues.put("LBNAT", _NAT);
            contentValues.put("LBType", _LBType);
            contentValues.put("LBVDate", _LBVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("LBNote", _LBNote);
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
            contentValues.put("LiveBirthID", _LiveBirthID);
            contentValues.put("DeliveryID", _DeliveryID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("LBMomID", _LBMomID);
            contentValues.put("LBDOB", _LBDOB);
            contentValues.put("LBTime", _LBTime);
            contentValues.put("LBTimeType", _LBTimeType);
            contentValues.put("LBDPlace", _LBDPlace);
            contentValues.put("LBPlaceOth", _LBPlaceOth);
            contentValues.put("LBDoctor", _LBDoctor);
            contentValues.put("LBNurse", _LBNurse);
            contentValues.put("LBMidwife", _LBMidwife);
            contentValues.put("LBTBA", _LBTBA);
            contentValues.put("LBCHW", _LBCHW);
            contentValues.put("LBRF", _LBRF);
            contentValues.put("LBNAT", _NAT);
            contentValues.put("LBOth", _LBOth);
            contentValues.put("LBOthSpecify", _LBOthSpecify);
            contentValues.put("LBDk", _LBDk);
            contentValues.put("LBRfs", _LBRfs);
            contentValues.put("LBType", _LBType);
            contentValues.put("LBVDate", _LBVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("LBNote", _LBNote);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            C.UpdateData(TableName, "LiveBirthID", ("" + _LiveBirthID + ""), contentValues);
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

    @SuppressLint("Range")
    public List<tmpLiveBirth_DataModel> SelectAll(Context context, String SQL) {
        Connection C = new Connection(context);
        List<tmpLiveBirth_DataModel> data = new ArrayList<tmpLiveBirth_DataModel>();
        tmpLiveBirth_DataModel d = new tmpLiveBirth_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Count += 1;
            d = new tmpLiveBirth_DataModel();
            d._Count = Count;
            d._LiveBirthID = cur.getString(cur.getColumnIndex("LiveBirthID"));
            d._DeliveryID = cur.getString(cur.getColumnIndex("DeliveryID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._LBMomID = cur.getString(cur.getColumnIndex("LBMomID"));
            d._LBDOB = cur.getString(cur.getColumnIndex("LBDOB"));
            d._LBTime = cur.getString(cur.getColumnIndex("LBTime"));
            d._LBTimeType = cur.getString(cur.getColumnIndex("LBTimeType"));
            d._LBDPlace = cur.getString(cur.getColumnIndex("LBDPlace"));
            d._LBPlaceOth = cur.getString(cur.getColumnIndex("LBPlaceOth"));
            d._LBDoctor = cur.getString(cur.getColumnIndex("LBDoctor"));
            d._LBNurse = cur.getString(cur.getColumnIndex("LBNurse"));
            d._LBMidwife = cur.getString(cur.getColumnIndex("LBMidwife"));
            d._LBTBA = cur.getString(cur.getColumnIndex("LBTBA"));
            d._LBCHW = cur.getString(cur.getColumnIndex("LBCHW"));
            d._LBRF = cur.getString(cur.getColumnIndex("LBRF"));
            d._LBOth = cur.getString(cur.getColumnIndex("LBOth"));
            d._LBOthSpecify = cur.getString(cur.getColumnIndex("LBOthSpecify"));
            d._LBDk = cur.getString(cur.getColumnIndex("LBDk"));
            d._LBRfs = cur.getString(cur.getColumnIndex("LBRfs"));
            d._NAT = cur.getString(cur.getColumnIndex("LBNAT"));
            d._LBType = cur.getString(cur.getColumnIndex("LBType"));
            d._LBVDate = cur.getString(cur.getColumnIndex("LBVDate"));
            d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
            d._LBNote = cur.getString(cur.getColumnIndex("LBNote"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }

    @SuppressLint("Range")
    public tmpLiveBirth_DataModel SelectSingle(Context context, String SQL) {
        Connection C = new Connection(context);
        tmpLiveBirth_DataModel d = new tmpLiveBirth_DataModel();
        Cursor cur = C.ReadData(SQL);

        if (cur.moveToFirst()) {
            d._LiveBirthID = cur.getString(cur.getColumnIndex("LiveBirthID"));
            d._DeliveryID = cur.getString(cur.getColumnIndex("DeliveryID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._LBMomID = cur.getString(cur.getColumnIndex("LBMomID"));
            d._LBDOB = cur.getString(cur.getColumnIndex("LBDOB"));
            d._LBTime = cur.getString(cur.getColumnIndex("LBTime"));
            d._LBTimeType = cur.getString(cur.getColumnIndex("LBTimeType"));
            d._LBDPlace = cur.getString(cur.getColumnIndex("LBDPlace"));
            d._LBPlaceOth = cur.getString(cur.getColumnIndex("LBPlaceOth"));
            d._LBDoctor = cur.getString(cur.getColumnIndex("LBDoctor"));
            d._LBNurse = cur.getString(cur.getColumnIndex("LBNurse"));
            d._LBMidwife = cur.getString(cur.getColumnIndex("LBMidwife"));
            d._LBTBA = cur.getString(cur.getColumnIndex("LBTBA"));
            d._LBCHW = cur.getString(cur.getColumnIndex("LBCHW"));
            d._LBRF = cur.getString(cur.getColumnIndex("LBRF"));
            d._LBOth = cur.getString(cur.getColumnIndex("LBOth"));
            d._LBOthSpecify = cur.getString(cur.getColumnIndex("LBOthSpecify"));
            d._LBDk = cur.getString(cur.getColumnIndex("LBDk"));
            d._LBRfs = cur.getString(cur.getColumnIndex("LBRfs"));
            d._NAT = cur.getString(cur.getColumnIndex("LBNAT"));
            d._LBType = cur.getString(cur.getColumnIndex("LBType"));
            d._LBVDate = cur.getString(cur.getColumnIndex("LBVDate"));
            d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
            d._LBNote = cur.getString(cur.getColumnIndex("LBNote"));
            cur.close();
            return d;
        } else {
            // Handle the case when no data is found
            cur.close();
            return null;
        }
    }
}