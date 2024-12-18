package forms_datamodel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class NBC_PNCNBDetail_DataModel {

    private String _NBID = "";

    public String getNBID() {
        return _NBID;
    }

    public void setNBID(String newValue) {
        _NBID = newValue;
    }

    private String _MemID = "";

    public String getMemID() {
        return _MemID;
    }

    public void setMemID(String newValue) {
        _MemID = newValue;
    }

    private String _HHID = "";

    public String getHHID() {
        return _HHID;
    }

    public void setHHID(String newValue) {
        _HHID = newValue;
    }

    private String _PGN = "";

    public String getPGN() {
        return _PGN;
    }

    public void setPGN(String newValue) {
        _PGN = newValue;
    }

    private String _ChSl = "";

    public String getChSl() {
        return _ChSl;
    }

    public void setChSl(String newValue) {
        _ChSl = newValue;
    }

    private String _ChPNCSl = "";

    public String getChPNCSl() {
        return _ChPNCSl;
    }

    public void setChPNCSl(String newValue) {
        _ChPNCSl = newValue;
    }

    private String _ChPNCDate = "";

    public String getChPNCDate() {
        return _ChPNCDate;
    }

    public void setChPNCDate(String newValue) {
        _ChPNCDate = newValue;
    }

    private String _ChPNCDateDk = "";

    public String getChPNCDateDk() {
        return _ChPNCDateDk;
    }

    public void setChPNCDateDk(String newValue) {
        _ChPNCDateDk = newValue;
    }

    private String _ChPNCProv = "";

    public String getChPNCProv() {
        return _ChPNCProv;
    }

    public void setChPNCProv(String newValue) {
        _ChPNCProv = newValue;
    }

    private String _ChPNCProvOth = "";

    public String getChPNCProvOth() {
        return _ChPNCProvOth;
    }

    public void setChPNCProvOth(String newValue) {
        _ChPNCProvOth = newValue;
    }

    private String _ChPNCPlace = "";

    public String getChPNCPlace() {
        return _ChPNCPlace;
    }

    public void setChPNCPlace(String newValue) {
        _ChPNCPlace = newValue;
    }

    private String _ChPNCPlaceOth = "";

    public String getChPNCPlaceOth() {
        return _ChPNCPlaceOth;
    }

    public void setChPNCPlaceOth(String newValue) {
        _ChPNCPlaceOth = newValue;
    }

    private String _ChPNCRes = "";

    public String getChPNCRes() {
        return _ChPNCRes;
    }

    public void setChPNCRes(String newValue) {
        _ChPNCRes = newValue;
    }

    private String _ChPNCCkWA = "";

    public String getChPNCCkWA() {
        return _ChPNCCkWA;
    }

    public void setChPNCCkWA(String newValue) {
        _ChPNCCkWA = newValue;
    }

    private String _ChPNCCkTM = "";

    public String getChPNCCkTM() {
        return _ChPNCCkTM;
    }

    public void setChPNCCkTM(String newValue) {
        _ChPNCCkTM = newValue;
    }

    private String _ChPNCCkRR = "";

    public String getChPNCCkRR() {
        return _ChPNCCkRR;
    }

    public void setChPNCCkRR(String newValue) {
        _ChPNCCkRR = newValue;
    }

    private String _ChPNCCkCE = "";

    public String getChPNCCkCE() {
        return _ChPNCCkCE;
    }

    public void setChPNCCkCE(String newValue) {
        _ChPNCCkCE = newValue;
    }

    private String _ChPNCCkCDS = "";

    public String getChPNCCkCDS() {
        return _ChPNCCkCDS;
    }

    public void setChPNCCkCDS(String newValue) {
        _ChPNCCkCDS = newValue;
    }

    private String _ChPNCCkCB = "";

    public String getChPNCCkCB() {
        return _ChPNCCkCB;
    }

    public void setChPNCCkCB(String newValue) {
        _ChPNCCkCB = newValue;
    }

    private String _ChPNCCkOB = "";

    public String getChPNCCkOB() {
        return _ChPNCCkOB;
    }

    public void setChPNCCkOB(String newValue) {
        _ChPNCCkOB = newValue;
    }

    private String _ChPNCCkOth = "";

    public String getChPNCCkOth() {
        return _ChPNCCkOth;
    }

    public void setChPNCCkOth(String newValue) {
        _ChPNCCkOth = newValue;
    }

    private String _ChPNCCkOthSp = "";

    public String getChPNCCkOthSp() {
        return _ChPNCCkOthSp;
    }

    public void setChPNCCkOthSp(String newValue) {
        _ChPNCCkOthSp = newValue;
    }

    private String _ChPNCCkDk = "";

    public String getChPNCCkDk() {
        return _ChPNCCkDk;
    }

    public void setChPNCCkDk(String newValue) {
        _ChPNCCkDk = newValue;
    }

    private String _ChPNCCkRef = "";

    public String getChPNCCkRef() {
        return _ChPNCCkRef;
    }

    public void setChPNCCkRef(String newValue) {
        _ChPNCCkRef = newValue;
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

    String TableName = "NBC_PNCNBDetail";

    public String SaveUpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try {
            if (C.Existence("Select * from " + TableName + "  Where NBID='" + _NBID + "' and PGN='" + _PGN + "' and ChSl='" + _ChSl + "'  and ChPNCSl='" + _ChPNCSl + "' "))
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
            contentValues.put("NBID", _NBID);
            contentValues.put("MemID", _MemID);
            contentValues.put("HHID", _HHID);
            contentValues.put("PGN", _PGN);
            contentValues.put("ChSl", _ChSl);
            contentValues.put("ChPNCSl", _ChPNCSl);
            contentValues.put("ChPNCDate", _ChPNCDate);
            contentValues.put("ChPNCDateDk", _ChPNCDateDk);
            contentValues.put("ChPNCProv", _ChPNCProv);
            contentValues.put("ChPNCProvOth", _ChPNCProvOth);
            contentValues.put("ChPNCPlace", _ChPNCPlace);
            contentValues.put("ChPNCPlaceOth", _ChPNCPlaceOth);
            contentValues.put("ChPNCRes", _ChPNCRes);
            contentValues.put("ChPNCCkWA", _ChPNCCkWA);
            contentValues.put("ChPNCCkTM", _ChPNCCkTM);
            contentValues.put("ChPNCCkRR", _ChPNCCkRR);
            contentValues.put("ChPNCCkCE", _ChPNCCkCE);
            contentValues.put("ChPNCCkCDS", _ChPNCCkCDS);
            contentValues.put("ChPNCCkCB", _ChPNCCkCB);
            contentValues.put("ChPNCCkOB", _ChPNCCkOB);
            contentValues.put("ChPNCCkOth", _ChPNCCkOth);
            contentValues.put("ChPNCCkOthSp", _ChPNCCkOthSp);
            contentValues.put("ChPNCCkDk", _ChPNCCkDk);
            contentValues.put("ChPNCCkRef", _ChPNCCkRef);
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
            contentValues.put("NBID", _NBID);
            contentValues.put("MemID", _MemID);
            contentValues.put("HHID", _HHID);
            contentValues.put("PGN", _PGN);
            contentValues.put("ChSl", _ChSl);
            contentValues.put("ChPNCSl", _ChPNCSl);
            contentValues.put("ChPNCDate", _ChPNCDate);
            contentValues.put("ChPNCDateDk", _ChPNCDateDk);
            contentValues.put("ChPNCProv", _ChPNCProv);
            contentValues.put("ChPNCProvOth", _ChPNCProvOth);
            contentValues.put("ChPNCPlace", _ChPNCPlace);
            contentValues.put("ChPNCPlaceOth", _ChPNCPlaceOth);
            contentValues.put("ChPNCRes", _ChPNCRes);
            contentValues.put("ChPNCCkWA", _ChPNCCkWA);
            contentValues.put("ChPNCCkTM", _ChPNCCkTM);
            contentValues.put("ChPNCCkRR", _ChPNCCkRR);
            contentValues.put("ChPNCCkCE", _ChPNCCkCE);
            contentValues.put("ChPNCCkCDS", _ChPNCCkCDS);
            contentValues.put("ChPNCCkCB", _ChPNCCkCB);
            contentValues.put("ChPNCCkOB", _ChPNCCkOB);
            contentValues.put("ChPNCCkOth", _ChPNCCkOth);
            contentValues.put("ChPNCCkOthSp", _ChPNCCkOthSp);
            contentValues.put("ChPNCCkDk", _ChPNCCkDk);
            contentValues.put("ChPNCCkRef", _ChPNCCkRef);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            C.UpdateData(TableName, "NBID,PGN,ChSl,ChPNCSl", ("" + _NBID + ", " + _PGN + ", " + _ChSl + ", " + _ChPNCSl + ""), contentValues);
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
    public List<NBC_PNCNBDetail_DataModel> SelectAll(Context context, String SQL) {
        Log.d("JM", SQL);
        Connection C = new Connection(context);
        List<NBC_PNCNBDetail_DataModel> data = new ArrayList<NBC_PNCNBDetail_DataModel>();
        NBC_PNCNBDetail_DataModel d = new NBC_PNCNBDetail_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Count += 1;
            d = new NBC_PNCNBDetail_DataModel();
            d._Count = Count;
            d._NBID = cur.getString(cur.getColumnIndex("NBID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._PGN = cur.getString(cur.getColumnIndex("PGN"));
            d._ChSl = cur.getString(cur.getColumnIndex("ChSl"));
            d._ChPNCSl = cur.getString(cur.getColumnIndex("ChPNCSl"));
            d._ChPNCDate = cur.getString(cur.getColumnIndex("ChPNCDate"));
            d._ChPNCDateDk = cur.getString(cur.getColumnIndex("ChPNCDateDk"));
            d._ChPNCProv = cur.getString(cur.getColumnIndex("ChPNCProv"));
            d._ChPNCProvOth = cur.getString(cur.getColumnIndex("ChPNCProvOth"));
            d._ChPNCPlace = cur.getString(cur.getColumnIndex("ChPNCPlace"));
            d._ChPNCPlaceOth = cur.getString(cur.getColumnIndex("ChPNCPlaceOth"));
            d._ChPNCRes = cur.getString(cur.getColumnIndex("ChPNCRes"));
            d._ChPNCCkWA = cur.getString(cur.getColumnIndex("ChPNCCkWA"));
            d._ChPNCCkTM = cur.getString(cur.getColumnIndex("ChPNCCkTM"));
            d._ChPNCCkRR = cur.getString(cur.getColumnIndex("ChPNCCkRR"));
            d._ChPNCCkCE = cur.getString(cur.getColumnIndex("ChPNCCkCE"));
            d._ChPNCCkCDS = cur.getString(cur.getColumnIndex("ChPNCCkCDS"));
            d._ChPNCCkCB = cur.getString(cur.getColumnIndex("ChPNCCkCB"));
            d._ChPNCCkOB = cur.getString(cur.getColumnIndex("ChPNCCkOB"));
            d._ChPNCCkOth = cur.getString(cur.getColumnIndex("ChPNCCkOth"));
            d._ChPNCCkOthSp = cur.getString(cur.getColumnIndex("ChPNCCkOthSp"));
            d._ChPNCCkDk = cur.getString(cur.getColumnIndex("ChPNCCkDk"));
            d._ChPNCCkRef = cur.getString(cur.getColumnIndex("ChPNCCkRef"));
            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }
}