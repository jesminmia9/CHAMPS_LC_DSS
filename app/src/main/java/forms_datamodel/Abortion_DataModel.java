package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class Abortion_DataModel {

    private String _AbortionID = "";
    private String _NAT = "";
    public String getNAT() {
        return _NAT;
    }

    public void setNAT(String _NAT) {
        this._NAT = _NAT;
    }

    public String getAbortionID() {
        return _AbortionID;
    }

    public void setAbortionID(String newValue) {
        _AbortionID = newValue;
    }

    private String _DeliveryID = "";

    public String getDeliveryID() {
        return _DeliveryID;
    }

    public void setDeliveryID(String newValue) {
        _DeliveryID = newValue;
    }

    private String _HHID = "";

    public String getHHID() {
        return _HHID;
    }

    public void setHHID(String newValue) {
        _HHID = newValue;
    }

    private String _AbMomID = "";

    public String getAbMomID() {
        return _AbMomID;
    }

    public void setAbMomID(String newValue) {
        _AbMomID = newValue;
    }

    private String _AbDate = "";

    public String getAbDate() {
        return _AbDate;
    }

    public void setAbDate(String newValue) {
        _AbDate = newValue;
    }

    private String _AbDateType = "";

    public String getAbDateType() {
        return _AbDateType;
    }

    public void setAbDateType(String newValue) {
        _AbDateType = newValue;
    }

    private String _AbTime = "";

    public String getAbTime() {
        return _AbTime;
    }

    public void setAbTime(String newValue) {
        _AbTime = newValue;
    }

    private String _AbTimeType = "";

    public String getAbTimeType() {
        return _AbTimeType;
    }

    public void setAbTimeType(String newValue) {
        _AbTimeType = newValue;
    }

    private String _AbPlace = "";

    public String getAbPlace() {
        return _AbPlace;
    }

    public void setAbPlace(String newValue) {
        _AbPlace = newValue;
    }

    private String _AbPlaceOth = "";

    public String getAbPlaceOth() {
        return _AbPlaceOth;
    }

    public void setAbPlaceOth(String newValue) {
        _AbPlaceOth = newValue;
    }

    private String _AbType = "";

    public String getAbType() {
        return _AbType;
    }

    public void setAbType(String newValue) {
        _AbType = newValue;
    }

    private String _AbDoctor = "";

    public String getAbDoctor() {
        return _AbDoctor;
    }

    public void setAbDoctor(String newValue) {
        _AbDoctor = newValue;
    }

    private String _AbNurse = "";

    public String getAbNurse() {
        return _AbNurse;
    }

    public void setAbNurse(String newValue) {
        _AbNurse = newValue;
    }

    private String _AbMidwifeParamedic = "";

    public String getAbMidwifeParamedic() {
        return _AbMidwifeParamedic;
    }

    public void setAbMidwifeParamedic(String newValue) {
        _AbMidwifeParamedic = newValue;
    }

    private String _AbTBA = "";

    public String getAbTBA() {
        return _AbTBA;
    }

    public void setAbTBA(String newValue) {
        _AbTBA = newValue;
    }

    private String _AbCHW = "";

    public String getAbCHW() {
        return _AbCHW;
    }

    public void setAbCHW(String newValue) {
        _AbCHW = newValue;
    }

    private String _AbRelativeFriend = "";

    public String getAbRelativeFriend() {
        return _AbRelativeFriend;
    }

    public void setAbRelativeFriend(String newValue) {
        _AbRelativeFriend = newValue;
    }

    private String _AbOther = "";

    public String getAbOther() {
        return _AbOther;
    }

    public void setAbOther(String newValue) {
        _AbOther = newValue;
    }

    private String _AbOthSpec = "";

    public String getAbOthSpec() {
        return _AbOthSpec;
    }

    public void setAbOthSpec(String newValue) {
        _AbOthSpec = newValue;
    }

    private String _AbDK = "";

    public String getAbDK() {
        return _AbDK;
    }

    public void setAbDK(String newValue) {
        _AbDK = newValue;
    }

    private String _AbRR = "";

    public String getAbRR() {
        return _AbRR;
    }

    public void setAbRR(String newValue) {
        _AbRR = newValue;
    }

    private String _AbNote = "";

    public String getAbNote() {
        return _AbNote;
    }

    public void setAbNote(String newValue) {
        _AbNote = newValue;
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

    String TableName = "Abortion";

    public String SaveUpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try {
            if (C.Existence("Select * from " + TableName + "  Where AbortionID='" + _AbortionID + "' "))
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
            contentValues.put("AbortionID", _AbortionID);
            contentValues.put("DeliveryID", _DeliveryID);
            contentValues.put("HHID", _HHID);
            contentValues.put("AbMomID", _AbMomID);
            contentValues.put("AbDate", _AbDate);
            contentValues.put("AbDateType", _AbDateType);
            contentValues.put("AbTime", _AbTime);
            contentValues.put("AbTimeType", _AbTimeType);
            contentValues.put("AbPlace", _AbPlace);
            contentValues.put("AbPlaceOth", _AbPlaceOth);
            contentValues.put("AbType", _AbType);
            contentValues.put("AbDoctor", _AbDoctor);
            contentValues.put("AbNurse", _AbNurse);
            contentValues.put("AbMidwifeParamedic", _AbMidwifeParamedic);
            contentValues.put("AbTBA", _AbTBA);
            contentValues.put("AbCHW", _AbCHW);
            contentValues.put("AbRelativeFriend", _AbRelativeFriend);
            contentValues.put("ABNAT", _NAT);
            contentValues.put("AbOther", _AbOther);
            contentValues.put("AbOthSpec", _AbOthSpec);
            contentValues.put("AbDK", _AbDK);
            contentValues.put("AbRR", _AbRR);
            contentValues.put("AbNote", _AbNote);
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
            contentValues.put("AbortionID", _AbortionID);
            contentValues.put("DeliveryID", _DeliveryID);
            contentValues.put("HHID", _HHID);
            contentValues.put("AbMomID", _AbMomID);
            contentValues.put("AbDate", _AbDate);
            contentValues.put("AbDateType", _AbDateType);
            contentValues.put("AbTime", _AbTime);
            contentValues.put("AbTimeType", _AbTimeType);
            contentValues.put("AbPlace", _AbPlace);
            contentValues.put("AbPlaceOth", _AbPlaceOth);
            contentValues.put("AbType", _AbType);
            contentValues.put("AbDoctor", _AbDoctor);
            contentValues.put("AbNurse", _AbNurse);
            contentValues.put("AbMidwifeParamedic", _AbMidwifeParamedic);
            contentValues.put("AbTBA", _AbTBA);
            contentValues.put("AbCHW", _AbCHW);
            contentValues.put("AbRelativeFriend", _AbRelativeFriend);
            contentValues.put("ABNAT", _NAT);
            contentValues.put("AbOther", _AbOther);
            contentValues.put("AbOthSpec", _AbOthSpec);
            contentValues.put("AbDK", _AbDK);
            contentValues.put("AbRR", _AbRR);
            contentValues.put("AbNote", _AbNote);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            C.UpdateData(TableName, "AbortionID", ("" + _AbortionID + ""), contentValues);
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

    public List<Abortion_DataModel> SelectAll(Context context, String SQL) {
        Connection C = new Connection(context);
        List<Abortion_DataModel> data = new ArrayList<Abortion_DataModel>();
        Abortion_DataModel d = new Abortion_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Count += 1;
            d = new Abortion_DataModel();
            d._Count = Count;
            d._AbortionID = cur.getString(cur.getColumnIndex("AbortionID"));
            d._DeliveryID = cur.getString(cur.getColumnIndex("DeliveryID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._AbMomID = cur.getString(cur.getColumnIndex("AbMomID"));
            d._AbDate = cur.getString(cur.getColumnIndex("AbDate"));
            d._AbDateType = cur.getString(cur.getColumnIndex("AbDateType"));
            d._AbTime = cur.getString(cur.getColumnIndex("AbTime"));
            d._AbTimeType = cur.getString(cur.getColumnIndex("AbTimeType"));
            d._AbPlace = cur.getString(cur.getColumnIndex("AbPlace"));
            d._AbPlaceOth = cur.getString(cur.getColumnIndex("AbPlaceOth"));
            d._AbType = cur.getString(cur.getColumnIndex("AbType"));
            d._AbDoctor = cur.getString(cur.getColumnIndex("AbDoctor"));
            d._AbNurse = cur.getString(cur.getColumnIndex("AbNurse"));
            d._AbMidwifeParamedic = cur.getString(cur.getColumnIndex("AbMidwifeParamedic"));
            d._AbTBA = cur.getString(cur.getColumnIndex("AbTBA"));
            d._AbCHW = cur.getString(cur.getColumnIndex("AbCHW"));
            d._AbRelativeFriend = cur.getString(cur.getColumnIndex("AbRelativeFriend"));
            d._NAT = cur.getString(cur.getColumnIndex("ABNAT"));
            d._AbOther = cur.getString(cur.getColumnIndex("AbOther"));
            d._AbOthSpec = cur.getString(cur.getColumnIndex("AbOthSpec"));
            d._AbDK = cur.getString(cur.getColumnIndex("AbDK"));
            d._AbRR = cur.getString(cur.getColumnIndex("AbRR"));
            d._AbNote = cur.getString(cur.getColumnIndex("AbNote"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }
}