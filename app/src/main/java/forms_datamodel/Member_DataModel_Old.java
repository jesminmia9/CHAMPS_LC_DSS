package forms_datamodel;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class Member_DataModel_Old {

       private String _MemID = "";
       public String getMemID(){ return _MemID;}
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
       private String _DSSID = "";
       public String getDSSID(){
             return _DSSID;
        }
       public void setDSSID(String newValue){
             _DSSID = newValue;
        }
       private String _MSlNo = "";
       public String getMSlNo(){
             return _MSlNo;
        }
       public void setMSlNo(String newValue){
             _MSlNo = newValue;
        }
       private String _Name = "";
       public String getName(){
             return _Name;
        }
       public void setName(String newValue){
             _Name = newValue;
        }
       private String _Rth = "";
       public String getRth(){
             return _Rth;
        }
       public void setRth(String newValue){
             _Rth = newValue;
        }
       private String _RthOth = "";
       public String getRthOth(){
             return _RthOth;
        }
       public void setRthOth(String newValue){
             _RthOth = newValue;
        }
       private String _Sex = "";
       public String getSex(){
             return _Sex;
        }
       public void setSex(String newValue){
             _Sex = newValue;
        }
       private String _BDate = "";
       public String getBDate(){
             return _BDate;
        }
       public void setBDate(String newValue){
             _BDate = newValue;
        }
       private String _BDateType = "";
       public String getBDateType(){
             return _BDateType;
        }
       public void setBDateType(String newValue){
             _BDateType = newValue;
        }
       private String _AgeY = "";
       public String getAgeY(){
             return _AgeY;
        }
       public void setAgeY(String newValue){
             _AgeY = newValue;
        }
       private String _MoNo = "";
       public String getMoNo(){
             return _MoNo;
        }
       public void setMoNo(String newValue){
             _MoNo = newValue;
        }
       private String _MoName = "";
       public String getMoName(){
             return _MoName;
        }
       public void setMoName(String newValue){
             _MoName = newValue;
        }
       private String _FaNo = "";
       public String getFaNo(){
             return _FaNo;
        }
       public void setFaNo(String newValue){
             _FaNo = newValue;
        }
       private String _FaName = "";
       public String getFaName(){
             return _FaName;
        }
       public void setFaName(String newValue){
             _FaName = newValue;
        }
       private String _EduY = "";
       public String getEduY(){
             return _EduY;
        }
       public void setEduY(String newValue){
             _EduY = newValue;
        }
       private String _MS = "";
       public String getMS(){
             return _MS;
        }
       public void setMS(String newValue){
             _MS = newValue;
        }
       private String _MSOth = "";
       public String getMSOth(){
             return _MSOth;
        }
       public void setMSOth(String newValue){
             _MSOth = newValue;
        }
       private String _Employ = "";
       public String getEmploy(){
             return _Employ;
        }
       public void setEmploy(String newValue){
             _Employ = newValue;
        }
       private String _EmployOth = "";
       public String getEmployOth(){
             return _EmployOth;
        }
       public void setEmployOth(String newValue){
             _EmployOth = newValue;
        }
       private String _Ocp = "";
       public String getOcp(){
             return _Ocp;
        }
       public void setOcp(String newValue){
             _Ocp = newValue;
        }
       private String _OcpOth = "";
       public String getOcpOth(){
             return _OcpOth;
        }
       public void setOcpOth(String newValue){
             _OcpOth = newValue;
        }
       private String _Religion = "";
       public String getReligion(){
             return _Religion;
        }
       public void setReligion(String newValue){
             _Religion = newValue;
        }
       private String _ReligionOth = "";
       public String getReligionOth(){
             return _ReligionOth;
        }
       public void setReligionOth(String newValue){
             _ReligionOth = newValue;
        }
       private String _Ethnicity = "";
       public String getEthnicity(){
             return _Ethnicity;
        }
       public void setEthnicity(String newValue){
             _Ethnicity = newValue;
        }
       private String _MobileNo = "";
       public String getMobileNo(){
             return _MobileNo;
        }
       public void setMobileNo(String newValue){
             _MobileNo = newValue;
        }
       private String _Sp1 = "";
       public String getSp1(){
             return _Sp1;
        }
       public void setSp1(String newValue){
             _Sp1 = newValue;
        }
       private String _Sp1Name = "";
       public String getSp1Name(){
             return _Sp1Name;
        }
       public void setSp1Name(String newValue){
             _Sp1Name = newValue;
        }
       private String _Sp2 = "";
       public String getSp2(){
             return _Sp2;
        }
       public void setSp2(String newValue){
             _Sp2 = newValue;
        }
       private String _Sp2Name = "";
       public String getSp2Name(){
             return _Sp2Name;
        }
       public void setSp2Name(String newValue){
             _Sp2Name = newValue;
        }
       private String _Sp3 = "";
       public String getSp3(){
             return _Sp3;
        }
       public void setSp3(String newValue){
             _Sp3 = newValue;
        }
       private String _Sp3Name = "";
       public String getSp3Name(){
             return _Sp3Name;
        }
       public void setSp3Name(String newValue){
             _Sp3Name = newValue;
        }
       private String _Sp4 = "";
       public String getSp4(){
             return _Sp4;
        }
       public void setSp4(String newValue){
             _Sp4 = newValue;
        }
       private String _Sp4Name = "";
       public String getSp4Name(){
             return _Sp4Name;
        }
       public void setSp4Name(String newValue){
             _Sp4Name = newValue;
        }
       private String _Pstat = "";
       public String getPstat(){
             return _Pstat;
        }
       public void setPstat(String newValue){
             _Pstat = newValue;
        }
       private String _LmpDt = "";
       public String getLmpDt(){
             return _LmpDt;
        }
       public void setLmpDt(String newValue){
             _LmpDt = newValue;
        }
       private String _Rnd = "";
       public String getRnd(){
             return _Rnd;
        }
       public void setRnd(String newValue){
             _Rnd = newValue;
        }
       private String _Active = "";
       public String getActive(){
             return _Active;
        }
       public void setActive(String newValue){
             _Active = newValue;
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

       String TableName = "Member";

       public String SaveUpdateData(Context context)
       {
           String response = "";
           C = new Connection(context);
           String SQL = "";
           try
           {
                if(C.Existence("Select * from "+ TableName +"  Where MemID='"+ _MemID +"' "))
                   response = UpdateData(context);
                else
                   response = SaveData(context);
           }
           catch(Exception  e)
           {
                response = e.getMessage();
           }
          return response;
       }
       Connection C;

       private String SaveData(Context context)
       {
           String response = "";
           C = new Connection(context);
           try
             {
                ContentValues contentValues = new ContentValues();
                contentValues.put("MemID", _MemID);
                contentValues.put("HHID", _HHID);
                contentValues.put("DSSID", _DSSID);
                contentValues.put("MSlNo", _MSlNo);
                contentValues.put("Name", _Name);
                contentValues.put("Rth", _Rth);
                contentValues.put("RthOth", _RthOth);
                contentValues.put("Sex", _Sex);
                contentValues.put("BDate", _BDate);
                contentValues.put("BDateType", _BDateType);
                contentValues.put("AgeY", _AgeY);
                contentValues.put("MoNo", _MoNo);
                contentValues.put("MoName", _MoName);
                contentValues.put("FaNo", _FaNo);
                contentValues.put("FaName", _FaName);
                contentValues.put("EduY", _EduY);
                contentValues.put("MS", _MS);
                contentValues.put("MSOth", _MSOth);
                contentValues.put("Employ", _Employ);
                contentValues.put("EmployOth", _EmployOth);
                contentValues.put("Ocp", _Ocp);
                contentValues.put("OcpOth", _OcpOth);
                contentValues.put("Religion", _Religion);
                contentValues.put("ReligionOth", _ReligionOth);
                contentValues.put("Ethnicity", _Ethnicity);
                contentValues.put("MobileNo", _MobileNo);
                contentValues.put("Sp1", _Sp1);
                contentValues.put("Sp1Name", _Sp1Name);
                contentValues.put("Sp2", _Sp2);
                contentValues.put("Sp2Name", _Sp2Name);
                contentValues.put("Sp3", _Sp3);
                contentValues.put("Sp3Name", _Sp3Name);
                contentValues.put("Sp4", _Sp4);
                contentValues.put("Sp4Name", _Sp4Name);
                contentValues.put("Pstat", _Pstat);
                contentValues.put("LmpDt", _LmpDt);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("Active", _Active);
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
             }
             catch(Exception  e)
             {
                response = e.getMessage();
             }
          return response;
       }


       private String UpdateData(Context context)
       {
           String response = "";
           C = new Connection(context);
           try
             {
                ContentValues contentValues = new ContentValues();
                contentValues.put("MemID", _MemID);
                contentValues.put("HHID", _HHID);
                contentValues.put("DSSID", _DSSID);
                contentValues.put("MSlNo", _MSlNo);
                contentValues.put("Name", _Name);
                contentValues.put("Rth", _Rth);
                contentValues.put("RthOth", _RthOth);
                contentValues.put("Sex", _Sex);
                contentValues.put("BDate", _BDate);
                contentValues.put("BDateType", _BDateType);
                contentValues.put("AgeY", _AgeY);
                contentValues.put("MoNo", _MoNo);
                contentValues.put("MoName", _MoName);
                contentValues.put("FaNo", _FaNo);
                contentValues.put("FaName", _FaName);
                contentValues.put("EduY", _EduY);
                contentValues.put("MS", _MS);
                contentValues.put("MSOth", _MSOth);
                contentValues.put("Employ", _Employ);
                contentValues.put("EmployOth", _EmployOth);
                contentValues.put("Ocp", _Ocp);
                contentValues.put("OcpOth", _OcpOth);
                contentValues.put("Religion", _Religion);
                contentValues.put("ReligionOth", _ReligionOth);
                contentValues.put("Ethnicity", _Ethnicity);
                contentValues.put("MobileNo", _MobileNo);
                contentValues.put("Sp1", _Sp1);
                contentValues.put("Sp1Name", _Sp1Name);
                contentValues.put("Sp2", _Sp2);
                contentValues.put("Sp2Name", _Sp2Name);
                contentValues.put("Sp3", _Sp3);
                contentValues.put("Sp3Name", _Sp3Name);
                contentValues.put("Sp4", _Sp4);
                contentValues.put("Sp4Name", _Sp4Name);
                contentValues.put("Pstat", _Pstat);
                contentValues.put("LmpDt", _LmpDt);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("Active", _Active);
                contentValues.put("Upload", _Upload);
                contentValues.put("modifyDate", _modifyDate);
                C.UpdateData(TableName, "MemID", (""+ _MemID +""), contentValues);
             }
             catch(Exception  e)
             {
                response = e.getMessage();
             }
          return response;
       }

         int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
       @SuppressLint("Range")
       public List<Member_DataModel_Old> SelectAll(Context context, String SQL)
       {
           Connection C = new Connection(context);
           List<Member_DataModel_Old> data = new ArrayList<Member_DataModel_Old>();
           Member_DataModel_Old d = new Member_DataModel_Old();
           Cursor cur = C.ReadData(SQL);

           cur.moveToFirst();
           while(!cur.isAfterLast())
           {
               Count += 1;
               d = new Member_DataModel_Old();
               d._Count = Count;
               d._MemID = cur.getString(cur.getColumnIndex("MemID"));
               d._HHID = cur.getString(cur.getColumnIndex("HHID"));
               d._DSSID = cur.getString(cur.getColumnIndex("DSSID"));
               d._MSlNo = cur.getString(cur.getColumnIndex("MSlNo"));
               d._Name = cur.getString(cur.getColumnIndex("Name"));
               d._Rth = cur.getString(cur.getColumnIndex("Rth"));
               d._RthOth = cur.getString(cur.getColumnIndex("RthOth"));
               d._Sex = cur.getString(cur.getColumnIndex("Sex"));
               d._BDate = cur.getString(cur.getColumnIndex("BDate"));
               d._BDateType = cur.getString(cur.getColumnIndex("BDateType"));
               d._AgeY = cur.getString(cur.getColumnIndex("AgeY"));
               d._MoNo = cur.getString(cur.getColumnIndex("MoNo"));
               d._MoName = cur.getString(cur.getColumnIndex("MoName"));
               d._FaNo = cur.getString(cur.getColumnIndex("FaNo"));
               d._FaName = cur.getString(cur.getColumnIndex("FaName"));
               d._EduY = cur.getString(cur.getColumnIndex("EduY"));
               d._MS = cur.getString(cur.getColumnIndex("MS"));
               d._MSOth = cur.getString(cur.getColumnIndex("MSOth"));
               d._Employ = cur.getString(cur.getColumnIndex("Employ"));
               d._EmployOth = cur.getString(cur.getColumnIndex("EmployOth"));
               d._Ocp = cur.getString(cur.getColumnIndex("Ocp"));
               d._OcpOth = cur.getString(cur.getColumnIndex("OcpOth"));
               d._Religion = cur.getString(cur.getColumnIndex("Religion"));
               d._ReligionOth = cur.getString(cur.getColumnIndex("ReligionOth"));
               d._Ethnicity = cur.getString(cur.getColumnIndex("Ethnicity"));
               d._MobileNo = cur.getString(cur.getColumnIndex("MobileNo"));
               d._Sp1 = cur.getString(cur.getColumnIndex("Sp1"));
               d._Sp1Name = cur.getString(cur.getColumnIndex("Sp1Name"));
               d._Sp2 = cur.getString(cur.getColumnIndex("Sp2"));
               d._Sp2Name = cur.getString(cur.getColumnIndex("Sp2Name"));
               d._Sp3 = cur.getString(cur.getColumnIndex("Sp3"));
               d._Sp3Name = cur.getString(cur.getColumnIndex("Sp3Name"));
               d._Sp4 = cur.getString(cur.getColumnIndex("Sp4"));
               d._Sp4Name = cur.getString(cur.getColumnIndex("Sp4Name"));
               d._Pstat = cur.getString(cur.getColumnIndex("Pstat"));
               d._LmpDt = cur.getString(cur.getColumnIndex("LmpDt"));
               d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
               d._Active = cur.getString(cur.getColumnIndex("Active"));
               data.add(d);

               cur.moveToNext();
           }
           cur.close();
         return data;
       }

    private String _MName = "";
    public String getMName(){
        return _MName;
    }
    public void setMName(String newValue){
        _MName = newValue;
    }

    private String _FName = "";
    public String getFName(){
        return _FName;
    }
    public void setFName(String newValue){
        _FName = newValue;
    }

    private String _MEnDate = "";
    public String getMEnDate(){
        return _MEnDate;
    }
    public void setMEnDate(String newValue){
        _MEnDate = newValue;
    }

    @SuppressLint("Range")
    public List<Member_DataModel_Old> SelectMember(Context context, String SQL)
    {
        Connection C = new Connection(context);
        List<Member_DataModel_Old> data = new ArrayList<Member_DataModel_Old>();
        Member_DataModel_Old d = new Member_DataModel_Old();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while(!cur.isAfterLast())
        {
            Count += 1;
            d = new Member_DataModel_Old();
            d._Count = Count;
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
//             d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._DSSID = cur.getString(cur.getColumnIndex("DSSID"));
            d._MSlNo = cur.getString(cur.getColumnIndex("MSlNo"));
            d._Name = cur.getString(cur.getColumnIndex("Name"));
            d._Rth = cur.getString(cur.getColumnIndex("Rth"));
            d._Sex = cur.getString(cur.getColumnIndex("Sex"));
            d._AgeY = cur.getString(cur.getColumnIndex("AgeY"));
            d._BDate = cur.getString(cur.getColumnIndex("BDate"));
            d._MoNo = cur.getString(cur.getColumnIndex("MoNo"));
            d._FaNo = cur.getString(cur.getColumnIndex("FaNo"));
            d._EduY = cur.getString(cur.getColumnIndex("EduY"));
            d._MS = cur.getString(cur.getColumnIndex("MS"));
            d._Ocp = cur.getString(cur.getColumnIndex("Ocp"));
            d._Pstat = cur.getString(cur.getColumnIndex("Pstat"));
            d._LmpDt = cur.getString(cur.getColumnIndex("LmpDt"));
            d._Sp1 = cur.getString(cur.getColumnIndex("Sp1"));
            d._Sp2 = cur.getString(cur.getColumnIndex("Sp2"));
            d._FName = cur.getString(cur.getColumnIndex("FName"));
            d._MName = cur.getString(cur.getColumnIndex("MName"));
            d._Active = cur.getString(cur.getColumnIndex("Active"));
            d._MEnDate = cur.getString(cur.getColumnIndex("MEnDate"));

            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }

    @SuppressLint("Range")
    public List<Member_DataModel_Old> SelectAll_MemberEvent(Context context, String SQL)
    {
        C = new Connection(context);
        List<Member_DataModel_Old> data = new ArrayList<Member_DataModel_Old>();
        Member_DataModel_Old d = new Member_DataModel_Old();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while(!cur.isAfterLast())
        {
            d = new Member_DataModel_Old();
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._DSSID = cur.getString(cur.getColumnIndex("DSSID"));
            d._Name = cur.getString(cur.getColumnIndex("Name"));
            d._Sex = cur.getString(cur.getColumnIndex("Sex"));
            d._BDate = cur.getString(cur.getColumnIndex("BDate"));
            d._BDateType = cur.getString(cur.getColumnIndex("BDateType"));
            d._AgeY = cur.getString(cur.getColumnIndex("AgeY"));
            d._Religion = cur.getString(cur.getColumnIndex("Religion"));
            d._Ethnicity = cur.getString(cur.getColumnIndex("Ethnicity"));
            d._MoNo = cur.getString(cur.getColumnIndex("MoNo"));
            d._MoName = cur.getString(cur.getColumnIndex("MoName"));
            d._FaNo = cur.getString(cur.getColumnIndex("FaNo"));
            d._FaName = cur.getString(cur.getColumnIndex("FaName"));
            d._EduY = cur.getString(cur.getColumnIndex("EduY"));
            d._MS = cur.getString(cur.getColumnIndex("MS"));
            d._Ocp = cur.getString(cur.getColumnIndex("Ocp"));
            d._Pstat = cur.getString(cur.getColumnIndex("Pstat"));
            d._LmpDt = cur.getString(cur.getColumnIndex("LmpDt"));

            data.add(d);

            cur.moveToNext();
        }
        cur.close();
        C.close();
        return data;
    }
}