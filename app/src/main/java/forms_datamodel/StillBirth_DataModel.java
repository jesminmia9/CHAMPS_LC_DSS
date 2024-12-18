package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class StillBirth_DataModel {

    private String _StillBirthID = "";

    public String getStillBirthID() {
        return _StillBirthID;
    }

    public void setStillBirthID(String newValue) {
        _StillBirthID = newValue;
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

    private String _SBMomID = "";

    public String getSBMomID() {
        return _SBMomID;
    }

    public void setSBMomID(String newValue) {
        _SBMomID = newValue;
    }
    private String _SBNAT = "";

    public String getSBNAT() {
        return _SBNAT;
    }

    public void setSBNAT(String _SBNAT) {
        this._SBNAT = _SBNAT;
    }
    private String _SBDate = "";

    public String getSBDate() {
        return _SBDate;
    }

    public void setSBDate(String newValue) {
        _SBDate = newValue;
    }

    private String _SBDateType = "";

    public String getSBDateType() {
        return _SBDateType;
    }

    public void setSBDateType(String newValue) {
        _SBDateType = newValue;
    }

    private String _SBTime = "";

    public String getSBTime() {
        return _SBTime;
    }

    public void setSBTime(String newValue) {
        _SBTime = newValue;
    }

    private String _SBTimeType = "";

    public String getSBTimeType() {
        return _SBTimeType;
    }

    public void setSBTimeType(String newValue) {
        _SBTimeType = newValue;
    }

    private String _SBType = "";

    public String getSBType() {
        return _SBType;
    }

    public void setSBType(String newValue) {
        _SBType = newValue;
    }

    private String _SBSex = "";

    public String getSBSex() {
        return _SBSex;
    }

    public void setSBSex(String newValue) {
        _SBSex = newValue;
    }

    private String _SBPlace = "";

    public String getSBPlace() {
        return _SBPlace;
    }

    public void setSBPlace(String newValue) {
        _SBPlace = newValue;
    }

    private String _SBPlaceOth = "";

    public String getSBPlaceOth() {
        return _SBPlaceOth;
    }

    public void setSBPlaceOth(String newValue) {
        _SBPlaceOth = newValue;
    }

    private String _SBDoctor = "";

    public String getSBDoctor() {
        return _SBDoctor;
    }

    public void setSBDoctor(String newValue) {
        _SBDoctor = newValue;
    }

    private String _SBNurse = "";

    public String getSBNurse() {
        return _SBNurse;
    }

    public void setSBNurse(String newValue) {
        _SBNurse = newValue;
    }

    private String _SBMidwife = "";

    public String getSBMidwife() {
        return _SBMidwife;
    }

    public void setSBMidwife(String newValue) {
        _SBMidwife = newValue;
    }

    private String _SBTBA = "";

    public String getSBTBA() {
        return _SBTBA;
    }

    public void setSBTBA(String newValue) {
        _SBTBA = newValue;
    }

    private String _SBCHW = "";

    public String getSBCHW() {
        return _SBCHW;
    }

    public void setSBCHW(String newValue) {
        _SBCHW = newValue;
    }

    private String _SBRltv = "";

    public String getSBRltv() {
        return _SBRltv;
    }

    public void setSBRltv(String newValue) {
        _SBRltv = newValue;
    }

    private String _SBOth = "";

    public String getSBOth() {
        return _SBOth;
    }

    public void setSBOth(String newValue) {
        _SBOth = newValue;
    }

    private String _SBOthSp = "";

    public String getSBOthSp() {
        return _SBOthSp;
    }

    public void setSBOthSp(String newValue) {
        _SBOthSp = newValue;
    }

    private String _SBDk = "";

    public String getSBDk() {
        return _SBDk;
    }

    public void setSBDk(String newValue) {
        _SBDk = newValue;
    }

    private String _SBRfs = "";

    public String getSBRfs() {
        return _SBRfs;
    }

    public void setSBRfs(String newValue) {
        _SBRfs = newValue;
    }

    private String _SBDelType = "";

    public String getSBDelType() {
        return _SBDelType;
    }

    public void setSBDelType(String newValue) {
        _SBDelType = newValue;
    }

    private String _SBVDate = "";

    public String getSBVDate() {
        return _SBVDate;
    }

    public void setSBVDate(String newValue) {
        _SBVDate = newValue;
    }

    private String _Rnd = "";

    public String getRnd() {
        return _Rnd;
    }

    public void setRnd(String newValue) {
        _Rnd = newValue;
    }

    private String _SBNote = "";

    public String getSBNote() {
        return _SBNote;
    }

    public void setSBNote(String newValue) {
        _SBNote = newValue;
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

    String TableName = "StillBirth";

    public String SaveUpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try {
            if (C.Existence("Select * from " + TableName + "  Where StillBirthID='" + _StillBirthID + "' "))
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
            contentValues.put("StillBirthID", _StillBirthID);
            contentValues.put("DeliveryID", _DeliveryID);
            contentValues.put("HHID", _HHID);
            contentValues.put("SBMomID", _SBMomID);
            contentValues.put("SBDate", _SBDate);
            contentValues.put("SBDateType", _SBDateType);
            contentValues.put("SBTime", _SBTime);
            contentValues.put("SBTimeType", _SBTimeType);
            contentValues.put("SBType", _SBType);
            contentValues.put("SBSex", _SBSex);
            contentValues.put("SBPlace", _SBPlace);
            contentValues.put("SBPlaceOth", _SBPlaceOth);
            contentValues.put("SBDoctor", _SBDoctor);
            contentValues.put("SBNurse", _SBNurse);
            contentValues.put("SBMidwife", _SBMidwife);
            contentValues.put("SBTBA", _SBTBA);
            contentValues.put("SBCHW", _SBCHW);
            contentValues.put("SBRltv", _SBRltv);
            contentValues.put("SBOth", _SBOth);
            contentValues.put("SBOthSp", _SBOthSp);
            contentValues.put("SBDk", _SBDk);
            contentValues.put("SBRfs", _SBRfs);
            contentValues.put("SBNAT", _SBNAT);
            contentValues.put("SBDelType", _SBDelType);
            contentValues.put("SBVDate", _SBVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("SBNote", _SBNote);
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
            contentValues.put("StillBirthID", _StillBirthID);
            contentValues.put("DeliveryID", _DeliveryID);
            contentValues.put("HHID", _HHID);
            contentValues.put("SBMomID", _SBMomID);
            contentValues.put("SBDate", _SBDate);
            contentValues.put("SBDateType", _SBDateType);
            contentValues.put("SBTime", _SBTime);
            contentValues.put("SBTimeType", _SBTimeType);
            contentValues.put("SBType", _SBType);
            contentValues.put("SBSex", _SBSex);
            contentValues.put("SBPlace", _SBPlace);
            contentValues.put("SBPlaceOth", _SBPlaceOth);
            contentValues.put("SBDoctor", _SBDoctor);
            contentValues.put("SBNurse", _SBNurse);
            contentValues.put("SBMidwife", _SBMidwife);
            contentValues.put("SBTBA", _SBTBA);
            contentValues.put("SBCHW", _SBCHW);
            contentValues.put("SBRltv", _SBRltv);
            contentValues.put("SBOth", _SBOth);
            contentValues.put("SBOthSp", _SBOthSp);
            contentValues.put("SBDk", _SBDk);
            contentValues.put("SBRfs", _SBRfs);
            contentValues.put("SBNAT", _SBNAT);
            contentValues.put("SBDelType", _SBDelType);
            contentValues.put("SBVDate", _SBVDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("SBNote", _SBNote);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            C.UpdateData(TableName, "StillBirthID", ("" + _StillBirthID + ""), contentValues);
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

    public List<StillBirth_DataModel> SelectAll(Context context, String SQL) {
        Connection C = new Connection(context);
        List<StillBirth_DataModel> data = new ArrayList<StillBirth_DataModel>();
        StillBirth_DataModel d = new StillBirth_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Count += 1;
            d = new StillBirth_DataModel();
            d._Count = Count;
            d._StillBirthID = cur.getString(cur.getColumnIndex("StillBirthID"));
            d._DeliveryID = cur.getString(cur.getColumnIndex("DeliveryID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._SBMomID = cur.getString(cur.getColumnIndex("SBMomID"));
            d._SBDate = cur.getString(cur.getColumnIndex("SBDate"));
            d._SBDateType = cur.getString(cur.getColumnIndex("SBDateType"));
            d._SBTime = cur.getString(cur.getColumnIndex("SBTime"));
            d._SBTimeType = cur.getString(cur.getColumnIndex("SBTimeType"));
            d._SBType = cur.getString(cur.getColumnIndex("SBType"));
            d._SBSex = cur.getString(cur.getColumnIndex("SBSex"));
            d._SBPlace = cur.getString(cur.getColumnIndex("SBPlace"));
            d._SBPlaceOth = cur.getString(cur.getColumnIndex("SBPlaceOth"));
            d._SBDoctor = cur.getString(cur.getColumnIndex("SBDoctor"));
            d._SBNurse = cur.getString(cur.getColumnIndex("SBNurse"));
            d._SBMidwife = cur.getString(cur.getColumnIndex("SBMidwife"));
            d._SBTBA = cur.getString(cur.getColumnIndex("SBTBA"));
            d._SBCHW = cur.getString(cur.getColumnIndex("SBCHW"));
            d._SBRltv = cur.getString(cur.getColumnIndex("SBRltv"));
            d._SBOth = cur.getString(cur.getColumnIndex("SBOth"));
            d._SBOthSp = cur.getString(cur.getColumnIndex("SBOthSp"));
            d._SBDk = cur.getString(cur.getColumnIndex("SBDk"));
            d._SBRfs = cur.getString(cur.getColumnIndex("SBRfs"));
            d._SBNAT = cur.getString(cur.getColumnIndex("SBNAT"));
            d._SBDelType = cur.getString(cur.getColumnIndex("SBDelType"));
            d._SBVDate = cur.getString(cur.getColumnIndex("SBVDate"));
            d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
            d._SBNote = cur.getString(cur.getColumnIndex("SBNote"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }
}