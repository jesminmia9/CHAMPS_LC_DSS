package forms_datamodel;

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

public class tmpTemporaryMigrationIn_DataModel {
    private String _TmpMigrationID = "";
    public String getTmpMigrationID(){
        return _TmpMigrationID;
    }
    public void setTmpMigrationID(String newValue){
        _TmpMigrationID = newValue;
    }
    private String _HHID = "";
    public String getHHID(){
        return _HHID;
    }
    public void setHHID(String newValue){
        _HHID = newValue;
    }
    private String _MemID = "";
    public String getMemID(){
        return _MemID;
    }
    public void setMemID(String newValue){
        _MemID = newValue;
    }
    private String _TmpMigVDate = "";
    public String getTmpMigVDate(){
        return _TmpMigVDate;
    }
    public void setTmpMigVDate(String newValue){
        _TmpMigVDate = newValue;
    }
    private String _TmpMigVisitorArrivalDate = "";
    public String getTmpMigVisitorArrivalDate(){
        return _TmpMigVisitorArrivalDate;
    }
    public void setTmpMigVisitorArrivalDate(String newValue){
        _TmpMigVisitorArrivalDate = newValue;
    }
    private String _TmpMigStayMonth = "";
    public String getTmpMigStayMonth(){
        return _TmpMigStayMonth;
    }
    public void setTmpMigStayMonth(String newValue){
        _TmpMigStayMonth = newValue;
    }
    private String _TmpMigReside = "";
    public String getTmpMigReside(){
        return _TmpMigReside;
    }
    public void setTmpMigReside(String newValue){
        _TmpMigReside = newValue;
    }

    private String _MigReason = "";
    public String getMigReason(){
        return _MigReason;
    }
    public void setMigReason(String newValue){
        _MigReason = newValue;
    }
    private String _MigReasonOth = "";
    public String getMigReasonOth(){
        return _MigReasonOth;
    }
    public void setMigReasonOth(String newValue){
        _MigReasonOth = newValue;
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

    String TableName = "tmpTemporaryMigrationIn";

    public String SaveUpdateData(Context context)
    {
        String response = "";
        C = new Connection(context);
        String SQL = "";
        try
        {
            if(C.Existence("Select * from "+ TableName +"  Where TmpMigrationID='"+ _TmpMigrationID +"' "))
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
            contentValues.put("TmpMigrationID", _TmpMigrationID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("TmpMigVDate", _TmpMigVDate);
            contentValues.put("TmpMigVisitorArrivalDate", _TmpMigVisitorArrivalDate);
            contentValues.put("TmpMigStayMonth", _TmpMigStayMonth);
            contentValues.put("TmpMigReside", _TmpMigReside);
            contentValues.put("MigReason", _MigReason);
            contentValues.put("MigReasonOth", _MigReasonOth);
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
            contentValues.put("TmpMigrationID", _TmpMigrationID);
            contentValues.put("HHID", _HHID);
            contentValues.put("MemID", _MemID);
            contentValues.put("TmpMigVDate", _TmpMigVDate);
            contentValues.put("TmpMigVisitorArrivalDate", _TmpMigVisitorArrivalDate);
            contentValues.put("TmpMigStayMonth", _TmpMigStayMonth);
            contentValues.put("TmpMigReside", _TmpMigReside);
            contentValues.put("MigReason", _MigReason);
            contentValues.put("MigReasonOth", _MigReasonOth);
            contentValues.put("Upload", _Upload);
            contentValues.put("modifyDate", _modifyDate);
            C.UpdateData(TableName, "TmpMigrationID", (""+ _TmpMigrationID +""), contentValues);

            manageAudit(context,this, AuditTrial.KEY_UPDATE);
        }
        catch(Exception  e)
        {
            response = e.getMessage();
        }
        return response;
    }

    int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
    public List<tmpTemporaryMigrationIn_DataModel> SelectAll(Context context, String SQL)
    {
        Connection C = new Connection(context);
        List<tmpTemporaryMigrationIn_DataModel> data = new ArrayList<tmpTemporaryMigrationIn_DataModel>();
        tmpTemporaryMigrationIn_DataModel d = new tmpTemporaryMigrationIn_DataModel();
        Cursor cur = C.ReadData(SQL);

        cur.moveToFirst();
        while(!cur.isAfterLast())
        {
            Count += 1;
            d = new tmpTemporaryMigrationIn_DataModel();
            d._Count = Count;
            d._TmpMigrationID = cur.getString(cur.getColumnIndex("TmpMigrationID"));
            d._HHID = cur.getString(cur.getColumnIndex("HHID"));
            d._MemID = cur.getString(cur.getColumnIndex("MemID"));
            d._TmpMigVDate = cur.getString(cur.getColumnIndex("TmpMigVDate"));
            d._TmpMigVisitorArrivalDate = cur.getString(cur.getColumnIndex("TmpMigVisitorArrivalDate"));
            d._TmpMigStayMonth = cur.getString(cur.getColumnIndex("TmpMigStayMonth"));
            d._TmpMigReside = cur.getString(cur.getColumnIndex("TmpMigReside"));
            d._MigReason = cur.getString(cur.getColumnIndex("MigReason"));
            d._MigReasonOth = cur.getString(cur.getColumnIndex("MigReasonOth"));
            data.add(d);

            manageAudit(context,d,AuditTrial.KEY_SELECT);

            cur.moveToNext();
        }
        cur.close();
        return data;
    }



    static Map<String, Object> firstStateMap;
    public void manageAudit(Context context, tmpTemporaryMigrationIn_DataModel ob, String key) {
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
    public Map<String, Object> getMapData(tmpTemporaryMigrationIn_DataModel ob) {
        Map<String, Object> data = new HashMap<>();

        if (ob != null) {
            data.put("TmpMigrationID", ob._TmpMigrationID);
            data.put("HHID", ob._HHID);
            data.put("MemID", ob._MemID);
            data.put("TmpMigVDate", ob._TmpMigVDate);
            data.put("TmpMigVisitorArrivalDate", ob._TmpMigVisitorArrivalDate);
            data.put("TmpMigStayMonth", ob._TmpMigStayMonth);
            data.put("TmpMigReside", ob._TmpMigReside);
            data.put("MigReason", ob._MigReason);
            data.put("MigReasonOth", ob._MigReasonOth);

        }
        return data;
    }

}
