package forms_datamodel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Common.Connection;
import Common.Global;
import Utility.AuditTrial;

public class tmpMemberMove_DataModel {

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

    private String _Active = "";

    public String getActive() {
        return _Active;
    }

    public void setActive(String newValue) {
        _Active = newValue;
    }

    private String _DSSID = "";

    public String getDSSID() {
        return _DSSID;
    }

    public void setDSSID(String newValue) {
        _DSSID = newValue;
    }

    private String _MEnType = "";

    public String getMEnType() {
        return _MEnType;
    }

    public void setMEnType(String newValue) {
        _MEnType = newValue;
    }

    private String _MEnDate = "";

    public String getMEnDate() {
        return _MEnDate;
    }

    public void setMEnDate(String newValue) {
        _MEnDate = newValue;
    }

    private String _MExType = "";

    public String getMExType() {
        return _MExType;
    }

    public void setMExType(String newValue) {
        _MExType = newValue;
    }

    private String _MExDate = "";

    public String getMExDate() {
        return _MExDate;
    }

    public void setMExDate(String newValue) {
        _MExDate = newValue;
    }

    private String _Rnd = "";

    public String getRnd() {
        return _Rnd;
    }

    public void setRnd(String newValue) {
        _Rnd = newValue;
    }

    private String _MemNote = "";

    public String getMemNote() {
        return _MemNote;
    }

    public void setMemNote(String newValue) {
        _MemNote = newValue;
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

    private String _MSlNo;
    public String get_MSlNo() {
        return _MSlNo;
    }

    public void set_MSlNo(String _MSlNo) {
        this._MSlNo = _MSlNo;
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
    private String Rth = "";
    private String MoNo = "";
    private String FaNo = "";
    private String EduY = "";
    private String Ocp = "";
    private String MS = "";
    private String Employ = "";
    private String EmployOth = "";
    private String OcpOth = "";
    private String OcpDk = "";
    private String MSOth = "";
    private String Sp1 = "";
    private String Sp1Name = "";
    private String Sp2 = "";
    private String Sp2Name = "";
    private String Sp3 = "";
    private String Sp3Name = "";
    private String Sp4 = "";
    private String Sp4Name = "";

    public String getEmploy() {
        return Employ;
    }

    public void setEmploy(String employ) {
        Employ = employ;
    }

    public String getEmployOth() {
        return EmployOth;
    }

    public void setEmployOth(String employOth) {
        EmployOth = employOth;
    }

    public String getOcpOth() {
        return OcpOth;
    }

    public void setOcpOth(String ocpOth) {
        OcpOth = ocpOth;
    }

    public String getOcpDk() {
        return OcpDk;
    }

    public void setOcpDk(String ocpDk) {
        OcpDk = ocpDk;
    }

    public String getMSOth() {
        return MSOth;
    }

    public void setMSOth(String MSOth) {
        this.MSOth = MSOth;
    }

    public String getSp1() {
        return Sp1;
    }

    public void setSp1(String sp1) {
        Sp1 = sp1;
    }

    public String getSp1Name() {
        return Sp1Name;
    }

    public void setSp1Name(String sp1Name) {
        Sp1Name = sp1Name;
    }

    public String getSp2() {
        return Sp2;
    }

    public void setSp2(String sp2) {
        Sp2 = sp2;
    }

    public String getSp2Name() {
        return Sp2Name;
    }

    public void setSp2Name(String sp2Name) {
        Sp2Name = sp2Name;
    }

    public String getSp3() {
        return Sp3;
    }

    public void setSp3(String sp3) {
        Sp3 = sp3;
    }

    public String getSp3Name() {
        return Sp3Name;
    }

    public void setSp3Name(String sp3Name) {
        Sp3Name = sp3Name;
    }

    public String getSp4() {
        return Sp4;
    }

    public void setSp4(String sp4) {
        Sp4 = sp4;
    }

    public String getSp4Name() {
        return Sp4Name;
    }

    public void setSp4Name(String sp4Name) {
        Sp4Name = sp4Name;
    }

    public String getRth() {
        return Rth;
    }

    public void setRth(String rth) {
        Rth = rth;
    }

    public String getMoNo() {
        return MoNo;
    }

    public void setMoNo(String moNo) {
        MoNo = moNo;
    }

    public String getFaNo() {
        return FaNo;
    }

    public void setFaNo(String faNo) {
        FaNo = faNo;
    }

    public String getEduY() {
        return EduY;
    }

    public void setEduY(String eduY) {
        EduY = eduY;
    }

    public String getOcp() {
        return Ocp;
    }

    public void setOcp(String ocp) {
        Ocp = ocp;
    }

    public String getMS() {
        return MS;
    }

    public void setMS(String MS) {
        this.MS = MS;
    }

    String TableName = "tmpMemberMove";

    public String SaveUpdateData(Context context) {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try {
            if (C.Existence("Select * from " + TableName + "  Where MemID='" + _MemID + "' and HHID='" + _HHID + "' and MSlNo='" + _MSlNo + "' "))
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
            contentValues.put("MemID", _MemID);
            contentValues.put("HHID", _HHID);
            contentValues.put("Active", _Active);
            contentValues.put("MSlNo", _MSlNo);
            contentValues.put("DSSID", _DSSID);
            contentValues.put("MEnType", _MEnType);
            contentValues.put("MEnDate", _MEnDate);
            contentValues.put("MExType", _MExType);
            contentValues.put("MExDate", _MExDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("MemNote", _MemNote);
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
            contentValues.put("Rth",Rth);
            contentValues.put("MoNo",MoNo);
            contentValues.put("FaNo",FaNo);
            contentValues.put("EduY",EduY);
            contentValues.put("Ocp",Ocp);
            contentValues.put("MS",MS);
            contentValues.put("Employ", Employ);
            contentValues.put("EmployOth", EmployOth);
            contentValues.put("OcpOth", OcpOth);
            contentValues.put("OcpDk", OcpDk);
            contentValues.put("MSOth", MSOth);
            contentValues.put("Sp1", Sp1);
            contentValues.put("Sp1Name", Sp1Name);
            contentValues.put("Sp2", Sp2);
            contentValues.put("Sp2Name", Sp2Name);
            contentValues.put("Sp3", Sp3);
            contentValues.put("Sp3Name", Sp3Name);
            contentValues.put("Sp4", Sp4);
            contentValues.put("Sp4Name", Sp4Name);
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
            contentValues.put("MemID", _MemID);
            contentValues.put("HHID", _HHID);
            contentValues.put("Active", _Active);
            contentValues.put("MSlNo", _MSlNo);
            contentValues.put("DSSID", _DSSID);
            contentValues.put("MEnType", _MEnType);
            contentValues.put("MEnDate", _MEnDate);
            contentValues.put("MExType", _MExType);
            contentValues.put("MExDate", _MExDate);
            contentValues.put("Rnd", _Rnd);
            contentValues.put("MemNote", _MemNote);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            contentValues.put("Rth",Rth);
            contentValues.put("MoNo",MoNo);
            contentValues.put("FaNo",FaNo);
            contentValues.put("EduY",EduY);
            contentValues.put("Ocp",Ocp);
            contentValues.put("MS",MS);
            contentValues.put("Employ", Employ);
            contentValues.put("EmployOth", EmployOth);
            contentValues.put("OcpOth", OcpOth);
            contentValues.put("OcpDk", OcpDk);
            contentValues.put("MSOth", MSOth);
            contentValues.put("Sp1", Sp1);
            contentValues.put("Sp1Name", Sp1Name);
            contentValues.put("Sp2", Sp2);
            contentValues.put("Sp2Name", Sp2Name);
            contentValues.put("Sp3", Sp3);
            contentValues.put("Sp3Name", Sp3Name);
            contentValues.put("Sp4", Sp4);
            contentValues.put("Sp4Name", Sp4Name);
            C.UpdateData(TableName, "MemID,HHID,MSlNo", ("" + _MemID + ", " + _HHID + ", " + _MSlNo + ""), contentValues);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return response;
    }

    public String DataCorrection(Context context) {
        String response = "";
        C = new Connection(context);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("MemID", _MemID);
            contentValues.put("HHID", _HHID);
            contentValues.put("modifyDate", _modifyDate);
            contentValues.put("Rth",Rth);
            contentValues.put("MoNo",MoNo);
            contentValues.put("FaNo",FaNo);
            contentValues.put("EduY",EduY);
            contentValues.put("Ocp",Ocp);
            contentValues.put("MS",MS);
            contentValues.put("Employ", Employ);
            contentValues.put("EmployOth", EmployOth);
            contentValues.put("OcpOth", OcpOth);
            contentValues.put("OcpDk", OcpDk);
            contentValues.put("MSOth", MSOth);
            contentValues.put("Sp1", Sp1);
            contentValues.put("Sp1Name", Sp1Name);
            contentValues.put("Sp2", Sp2);
            contentValues.put("Sp2Name", Sp2Name);
            contentValues.put("Sp3", Sp3);
            contentValues.put("Sp3Name", Sp3Name);
            contentValues.put("Sp4", Sp4);
            contentValues.put("Sp4Name", Sp4Name);
            C.UpdateData(TableName, "MemID,HHID,MSlNo", ("" + _MemID + ", " + _HHID + ", " + _MSlNo + ""), contentValues);
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
    public List<tmpMemberMove_DataModel> SelectAll(Context context, String SQL) {
        Connection C = new Connection(context);
        List<tmpMemberMove_DataModel> data = new ArrayList<tmpMemberMove_DataModel>();
        tmpMemberMove_DataModel d = new tmpMemberMove_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Count += 1;
            d = new tmpMemberMove_DataModel();
            d._Count = Count;
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._MSlNo = cur.getString(cur.getColumnIndex("MSlNo"));
            d._Active = cur.getString(cur.getColumnIndex("Active"));
            d._DSSID = cur.getString(cur.getColumnIndex("DSSID"));
            d._MEnType = cur.getString(cur.getColumnIndex("MEnType"));
            d._MEnDate = cur.getString(cur.getColumnIndex("MEnDate"));
            d._MExType = cur.getString(cur.getColumnIndex("MExType"));
            d._MExDate = cur.getString(cur.getColumnIndex("MExDate"));
            d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
            d._MemNote = cur.getString(cur.getColumnIndex("MemNote"));
            d._DeviceID = cur.getString(cur.getColumnIndex("DeviceID"));
            d._EntryUser = cur.getString(cur.getColumnIndex("EntryUser"));
            d._isdelete = cur.getInt(cur.getColumnIndex("isdelete"));
            d.Rth =cur.getString(cur.getColumnIndex("Rth"));
            d.MoNo =cur.getString(cur.getColumnIndex("MoNo"));
            d.FaNo =cur.getString(cur.getColumnIndex("FaNo"));
            d.EduY =cur.getString(cur.getColumnIndex("EduY"));
            d.Ocp =cur.getString(cur.getColumnIndex("Ocp"));
            d.MS =cur.getString(cur.getColumnIndex("MS"));
            d.Employ = cur.getString(cur.getColumnIndex("Employ"));
            d.EmployOth = cur.getString(cur.getColumnIndex("EmployOth"));
            d.OcpOth = cur.getString(cur.getColumnIndex("OcpOth"));
            d.OcpDk = cur.getString(cur.getColumnIndex("OcpDk"));
            d.MSOth = cur.getString(cur.getColumnIndex("MSOth"));
            d.Sp1 = cur.getString(cur.getColumnIndex("Sp1"));
            d.Sp1Name = cur.getString(cur.getColumnIndex("Sp1Name"));
            d.Sp2 = cur.getString(cur.getColumnIndex("Sp2"));
            d.Sp2Name = cur.getString(cur.getColumnIndex("Sp2Name"));
            d.Sp3 = cur.getString(cur.getColumnIndex("Sp3"));
            d.Sp3Name = cur.getString(cur.getColumnIndex("Sp3Name"));
            d.Sp4 = cur.getString(cur.getColumnIndex("Sp4"));
            d.Sp4Name = cur.getString(cur.getColumnIndex("Sp4Name"));
            data.add(d);

            manageAudit(context,d,AuditTrial.KEY_SELECT);
            cur.moveToNext();
        }
        cur.close();
        return data;
    }

    static Map<String, Object> firstStateMap;
    public void manageAudit(Context context, tmpMemberMove_DataModel ob, String key) {

        if (key.equalsIgnoreCase(AuditTrial.KEY_SELECT)) {
            //store old state
            firstStateMap = getMapData(ob);
        } else if (key.equalsIgnoreCase(AuditTrial.KEY_UPDATE)) {
            //store new state
            Map<String, Object> secondStateMap = getMapData(ob);
            AuditTrial auditTrial = new AuditTrial(context,TableName);
            // run audit
            if (firstStateMap != null && !firstStateMap.isEmpty() && secondStateMap != null && !secondStateMap.isEmpty()) {
                auditTrial.audit(firstStateMap, secondStateMap);
            }
        }
    }

    /**
     * get object state
     * @param ob
     * @return
     */
    public Map<String, Object> getMapData(tmpMemberMove_DataModel ob) {
        Map<String, Object> data = new HashMap<>();

        if (ob != null) {
            data.put("MemID", ob._MemID);
            data.put("HHID", ob._HHID);
            data.put("DSSID", ob._DSSID);
            data.put("MSlNo", ob._MSlNo);
            data.put("Rth", ob.Rth);
            data.put("MoNo", ob.MoNo);
            data.put("FaNo", ob.FaNo);
            data.put("EduY", ob.EduY);
            data.put("Employ", ob.Employ);
            data.put("EmployOth", ob.EmployOth);
            data.put("Ocp", ob.Ocp);
            data.put("OcpOth", ob.OcpOth);
            data.put("OcpDk", ob.OcpDk);
            data.put("MS", ob.MS);
            data.put("MSOth", ob.MSOth);
            data.put("Sp1", ob.Sp1);
            data.put("Sp1Name", ob.Sp1Name);
            data.put("Sp2", ob.Sp2);
            data.put("Sp2Name", ob.Sp2Name);
            data.put("Sp3", ob.Sp3);
            data.put("Sp3Name", ob.Sp3Name);
            data.put("Sp4", ob.Sp4);
            data.put("Sp4Name", ob.Sp4Name);
        }

        return data;
    }
}