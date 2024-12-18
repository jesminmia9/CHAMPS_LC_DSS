package forms_datamodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import Common.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import Common.Global;

import android.content.ContentValues;

public class NBC_PNCNB_DataModel {

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

    private String _ChPNCCkup = "";

    public String getChPNCCkup() {
        return _ChPNCCkup;
    }

    public void setChPNCCkup(String newValue) {
        _ChPNCCkup = newValue;
    }

    private String _ChPNCCheckUnit = "";

    public String getChPNCCheckUnit() {
        return _ChPNCCheckUnit;
    }

    public void setChPNCCheckUnit(String newValue) {
        _ChPNCCheckUnit = newValue;
    }

    private String _ChPNCCheckDur = "";

    public String getChPNCCheckDur() {
        return _ChPNCCheckDur;
    }

    public void setChPNCCheckDur(String newValue) {
        _ChPNCCheckDur = newValue;
    }

    private String _ChPNCTotal = "";

    public String getChPNCTotal() {
        return _ChPNCTotal;
    }

    public void setChPNCTotal(String newValue) {
        _ChPNCTotal = newValue;
    }

    private String _ChPNCCard = "";

    public String getChPNCCard() {
        return _ChPNCCard;
    }

    public void setChPNCCard(String newValue) {
        _ChPNCCard = newValue;
    }

    private String _ChPNCPres = "";

    public String getChPNCPres() {
        return _ChPNCPres;
    }

    public void setChPNCPres(String newValue) {
        _ChPNCPres = newValue;
    }

    private String _ChPNC42dWA = "";

    public String getChPNC42dWA() {
        return _ChPNC42dWA;
    }

    public void setChPNC42dWA(String newValue) {
        _ChPNC42dWA = newValue;
    }

    private String _ChPNC42dTM = "";

    public String getChPNC42dTM() {
        return _ChPNC42dTM;
    }

    public void setChPNC42dTM(String newValue) {
        _ChPNC42dTM = newValue;
    }

    private String _ChPNC42dRRA = "";

    public String getChPNC42dRRA() {
        return _ChPNC42dRRA;
    }

    public void setChPNC42dRRA(String newValue) {
        _ChPNC42dRRA = newValue;
    }

    private String _ChPNC42IM = "";

    public String getChPNC42IM() {
        return _ChPNC42IM;
    }

    public void setChPNC42IM(String newValue) {
        _ChPNC42IM = newValue;
    }

    private String _ChPNC42CE = "";

    public String getChPNC42CE() {
        return _ChPNC42CE;
    }

    public void setChPNC42CE(String newValue) {
        _ChPNC42CE = newValue;
    }

    private String _ChPNC42CDS = "";

    public String getChPNC42CDS() {
        return _ChPNC42CDS;
    }

    public void setChPNC42CDS(String newValue) {
        _ChPNC42CDS = newValue;
    }

    private String _ChPNC42CB = "";

    public String getChPNC42CB() {
        return _ChPNC42CB;
    }

    public void setChPNC42CB(String newValue) {
        _ChPNC42CB = newValue;
    }

    private String _ChPNC42OB = "";

    public String getChPNC42OB() {
        return _ChPNC42OB;
    }

    public void setChPNC42OB(String newValue) {
        _ChPNC42OB = newValue;
    }

    private String _ChPNC42dOth = "";

    public String getChPNC42dOth() {
        return _ChPNC42dOth;
    }

    public void setChPNC42dOth(String newValue) {
        _ChPNC42dOth = newValue;
    }

    private String _ChPNC42dOthSp = "";

    public String getChPNC42dOthSp() {
        return _ChPNC42dOthSp;
    }

    public void setChPNC42dOthSp(String newValue) {
        _ChPNC42dOthSp = newValue;
    }

    private String _ChPNC42dDk = "";

    public String getChPNC42dDk() {
        return _ChPNC42dDk;
    }

    public void setChPNC42dDk(String newValue) {
        _ChPNC42dDk = newValue;
    }

    private String _ChPNC42dRR = "";

    public String getChPNC42dRR() {
        return _ChPNC42dRR;
    }

    public void setChPNC42dRR(String newValue) {
        _ChPNC42dRR = newValue;
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

    String TableName = "NBC_PNCNB";

    public String SaveUpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try {
            if (C.Existence("Select * from " + TableName + "  Where NBID='" + _NBID + "' and PGN='" + _PGN + "' and ChSl='" + _ChSl + "' "))
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
            contentValues.put("ChPNCCkup", _ChPNCCkup);
            contentValues.put("ChPNCCheckUnit", _ChPNCCheckUnit);
            contentValues.put("ChPNCCheckDur", _ChPNCCheckDur);
            contentValues.put("ChPNCTotal", _ChPNCTotal);
            contentValues.put("ChPNCCard", _ChPNCCard);
            contentValues.put("ChPNCPres", _ChPNCPres);
            contentValues.put("ChPNC42dWA", _ChPNC42dWA);
            contentValues.put("ChPNC42dTM", _ChPNC42dTM);
            contentValues.put("ChPNC42dRRA", _ChPNC42dRRA);
            contentValues.put("ChPNC42IM", _ChPNC42IM);
            contentValues.put("ChPNC42CE", _ChPNC42CE);
            contentValues.put("ChPNC42CDS", _ChPNC42CDS);
            contentValues.put("ChPNC42CB", _ChPNC42CB);
            contentValues.put("ChPNC42OB", _ChPNC42OB);
            contentValues.put("ChPNC42dOth", _ChPNC42dOth);
            contentValues.put("ChPNC42dOthSp", _ChPNC42dOthSp);
            contentValues.put("ChPNC42dDk", _ChPNC42dDk);
            contentValues.put("ChPNC42dRR", _ChPNC42dRR);
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
            contentValues.put("ChPNCCkup", _ChPNCCkup);
            contentValues.put("ChPNCCheckUnit", _ChPNCCheckUnit);
            contentValues.put("ChPNCCheckDur", _ChPNCCheckDur);
            contentValues.put("ChPNCTotal", _ChPNCTotal);
            contentValues.put("ChPNCCard", _ChPNCCard);
            contentValues.put("ChPNCPres", _ChPNCPres);
            contentValues.put("ChPNC42dWA", _ChPNC42dWA);
            contentValues.put("ChPNC42dTM", _ChPNC42dTM);
            contentValues.put("ChPNC42dRRA", _ChPNC42dRRA);
            contentValues.put("ChPNC42IM", _ChPNC42IM);
            contentValues.put("ChPNC42CE", _ChPNC42CE);
            contentValues.put("ChPNC42CDS", _ChPNC42CDS);
            contentValues.put("ChPNC42CB", _ChPNC42CB);
            contentValues.put("ChPNC42OB", _ChPNC42OB);
            contentValues.put("ChPNC42dOth", _ChPNC42dOth);
            contentValues.put("ChPNC42dOthSp", _ChPNC42dOthSp);
            contentValues.put("ChPNC42dDk", _ChPNC42dDk);
            contentValues.put("ChPNC42dRR", _ChPNC42dRR);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            C.UpdateData(TableName, "NBID,PGN,ChSl", ("" + _NBID + ", " + _PGN + ", " + _ChSl + ""), contentValues);

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
    public List<NBC_PNCNB_DataModel> SelectAll(Context context, String SQL) {
        Connection C = new Connection(context);
        List<NBC_PNCNB_DataModel> data = new ArrayList<NBC_PNCNB_DataModel>();
        NBC_PNCNB_DataModel d = new NBC_PNCNB_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Count += 1;
            d = new NBC_PNCNB_DataModel();
            d._Count = Count;
            d._NBID = cur.getString(cur.getColumnIndex("NBID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._PGN = cur.getString(cur.getColumnIndex("PGN"));
            d._ChSl = cur.getString(cur.getColumnIndex("ChSl"));
            d._ChPNCCkup = cur.getString(cur.getColumnIndex("ChPNCCkup"));
            d._ChPNCCheckUnit = cur.getString(cur.getColumnIndex("ChPNCCheckUnit"));
            d._ChPNCCheckDur = cur.getString(cur.getColumnIndex("ChPNCCheckDur"));
            d._ChPNCTotal = cur.getString(cur.getColumnIndex("ChPNCTotal"));
            d._ChPNCCard = cur.getString(cur.getColumnIndex("ChPNCCard"));
            d._ChPNCPres = cur.getString(cur.getColumnIndex("ChPNCPres"));
            d._ChPNC42dWA = cur.getString(cur.getColumnIndex("ChPNC42dWA"));
            d._ChPNC42dTM = cur.getString(cur.getColumnIndex("ChPNC42dTM"));
            d._ChPNC42dRRA = cur.getString(cur.getColumnIndex("ChPNC42dRRA"));
            d._ChPNC42IM = cur.getString(cur.getColumnIndex("ChPNC42IM"));
            d._ChPNC42CE = cur.getString(cur.getColumnIndex("ChPNC42CE"));
            d._ChPNC42CDS = cur.getString(cur.getColumnIndex("ChPNC42CDS"));
            d._ChPNC42CB = cur.getString(cur.getColumnIndex("ChPNC42CB"));
            d._ChPNC42OB = cur.getString(cur.getColumnIndex("ChPNC42OB"));
            d._ChPNC42dOth = cur.getString(cur.getColumnIndex("ChPNC42dOth"));
            d._ChPNC42dOthSp = cur.getString(cur.getColumnIndex("ChPNC42dOthSp"));
            d._ChPNC42dDk = cur.getString(cur.getColumnIndex("ChPNC42dDk"));
            d._ChPNC42dRR = cur.getString(cur.getColumnIndex("ChPNC42dRR"));
            data.add(d);


            cur.moveToNext();
        }
        cur.close();
        return data;
    }


}