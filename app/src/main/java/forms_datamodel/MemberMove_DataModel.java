package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class MemberMove_DataModel {

        private String _MemID = "";
        public String getMemID(){
              return _MemID;
         }
        public void setMemID(String newValue){
              _MemID = newValue;
         }
        private String _HHID = "";
        public String getHHID(){
              return _HHID;
         }
        public void setHHID(String newValue){
              _HHID = newValue;
         }
         private String _MSlNo = "";
         public String getMSlNo(){
             return _MSlNo;
         }
         public void setMSlNo(String newValue){
             _MSlNo = newValue;
         }

        private String _Active = "";
        public String getActive(){
              return _Active;
         }
        public void setActive(String newValue){
              _Active = newValue;
         }
        private String _DSSID = "";
        public String getDSSID(){
              return _DSSID;
         }
        public void setDSSID(String newValue){
              _DSSID = newValue;
         }
        private String _MEnType = "";
        public String getMEnType(){
              return _MEnType;
         }
        public void setMEnType(String newValue){
              _MEnType = newValue;
         }
        private String _MEnDate = "";
        public String getMEnDate(){
              return _MEnDate;
         }
        public void setMEnDate(String newValue){
              _MEnDate = newValue;
         }
        private String _MExType = "";
        public String getMExType(){
              return _MExType;
         }
        public void setMExType(String newValue){
              _MExType = newValue;
         }
        private String _MExDate = "";
        public String getMExDate(){
              return _MExDate;
         }
        public void setMExDate(String newValue){
              _MExDate = newValue;
         }
        private String _Rnd = "";
        public String getRnd(){
              return _Rnd;
         }
        public void setRnd(String newValue){
              _Rnd = newValue;
         }
        private String _MemNote = "";
        public String getMemNote(){
              return _MemNote;
         }
        public void setMemNote(String newValue){
              _MemNote = newValue;
         }
        private String _StartTime = "";
        public void setStartTime(String newValue){
              _StartTime = newValue;
         }
        private String _EndTime = "";
        public void setEndTime(String newValue){
              _EndTime = newValue;
         }
        private String _DeviceID = "";
        public void setDeviceID(String newValue){
              _DeviceID = newValue;
         }
        private String _EntryUser = "";
        public void setEntryUser(String newValue){
              _EntryUser = newValue;
         }
        private String _Lat = "";
        public void setLat(String newValue){
              _Lat = newValue;
         }
        private String _Lon = "";
        public void setLon(String newValue){
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

    String TableName = "MemberMove";

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
            contentValues.put("MSlNo", _MSlNo);
            contentValues.put("Active", _Active);
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
            contentValues.put("MSlNo", _MSlNo);
            contentValues.put("Active", _Active);
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

    int Count = 0;
    private int _Count = 0;

    public int getCount() {
        return _Count;
    }

    public List<MemberMove_DataModel> SelectAll(Context context, String SQL) {
        Connection C = new Connection(context);
        List<MemberMove_DataModel> data = new ArrayList<MemberMove_DataModel>();
        MemberMove_DataModel d = new MemberMove_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Count += 1;
            d = new MemberMove_DataModel();
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

            cur.moveToNext();
        }
        cur.close();
        return data;
    }
}