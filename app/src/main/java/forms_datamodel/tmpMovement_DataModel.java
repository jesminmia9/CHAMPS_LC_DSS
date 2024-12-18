package forms_datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;

public class tmpMovement_DataModel {

       private String _MovementID = "";
       public String getMovementID(){
             return _MovementID;
        }
       public void setMovementID(String newValue){
             _MovementID = newValue;
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
       private String _MoveEvType = "";
       public String getMoveEvType(){
             return _MoveEvType;
        }
       public void setMoveEvType(String newValue){
             _MoveEvType = newValue;
        }
       private String _MoveDate = "";
       public String getMoveDate(){
             return _MoveDate;
        }
       public void setMoveDate(String newValue){
             _MoveDate = newValue;
        }
    private String _MovePhoneAvail = "";
    public String getMovePhoneAvail(){
        return _MovePhoneAvail;
    }
    public void setMovePhoneAvail(String newValue){
        _MovePhoneAvail = newValue;
    }
    private String _MovePhone = "";
    public String getMovePhone(){
        return _MovePhone;
    }
    public void setMovePhone(String newValue){
        _MovePhone = newValue;
    }
    private String _MoveAddressAvail = "";
    public String getMoveAddressAvail(){
        return _MoveAddressAvail;
    }
    public void setMoveAddressAvail(String newValue){
        _MoveAddressAvail = newValue;
    }
    private String _MoveAddress = "";
    public String getMoveAddress(){
        return _MoveAddress;
    }
    public void setMoveAddress(String newValue){
        _MoveAddress = newValue;
    }
       private String _MoveVill = "";
       public String getMoveVill(){
             return _MoveVill;
        }
       public void setMoveVill(String newValue){
             _MoveVill = newValue;
        }
       private String _MoveCompound = "";
       public String getMoveCompound(){
             return _MoveCompound;
        }
       public void setMoveCompound(String newValue){
             _MoveCompound = newValue;
        }
       private String _MoveHH = "";
       public String getMoveHH(){
             return _MoveHH;
        }
       public void setMoveHH(String newValue){
             _MoveHH = newValue;
        }
       private String _MoveReason = "";
       public String getMoveReason(){
             return _MoveReason;
        }
       public void setMoveReason(String newValue){
             _MoveReason = newValue;
        }
       private String _MoveReasonOth = "";
       public String getMoveReasonOth(){
             return _MoveReasonOth;
        }
       public void setMoveReasonOth(String newValue){
             _MoveReasonOth = newValue;
        }
       private String _MoveVDate = "";
       public String getMoveVDate(){
             return _MoveVDate;
        }
       public void setMoveVDate(String newValue){
             _MoveVDate = newValue;
        }
       private String _Rnd = "";
       public String getRnd(){
             return _Rnd;
        }
       public void setRnd(String newValue){
             _Rnd = newValue;
        }
       private String _MoveNote = "";
       public String getMoveNote(){
             return _MoveNote;
        }
       public void setMoveNote(String newValue){
             _MoveNote = newValue;
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

       String TableName = "tmpMovement";

       public String SaveUpdateData(Context context)
       {
           String response = "";
           C = new Connection(context);
           String SQL = "";
           try
           {
                if(C.Existence("Select * from "+ TableName +"  Where MovementID='"+ _MovementID +"' "))
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
                contentValues.put("MovementID", _MovementID);
                contentValues.put("HHID", _HHID);
                contentValues.put("MemID", _MemID);
                contentValues.put("MoveEvType", _MoveEvType);
                contentValues.put("MoveDate", _MoveDate);
                 contentValues.put("MovePhoneAvail", _MovePhoneAvail);
                 contentValues.put("MovePhone", _MovePhone);
                 contentValues.put("MoveAddressAvail", _MoveAddressAvail);
                 contentValues.put("MoveAddress", _MoveAddress);
                contentValues.put("MoveVill", _MoveVill);
                contentValues.put("MoveCompound", _MoveCompound);
                contentValues.put("MoveHH", _MoveHH);
                contentValues.put("MoveReason", _MoveReason);
                contentValues.put("MoveReasonOth", _MoveReasonOth);
                contentValues.put("MoveVDate", _MoveVDate);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("MoveNote", _MoveNote);
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
                contentValues.put("MovementID", _MovementID);
                contentValues.put("HHID", _HHID);
                contentValues.put("MemID", _MemID);
                contentValues.put("MoveEvType", _MoveEvType);
                contentValues.put("MoveDate", _MoveDate);
                 contentValues.put("MovePhoneAvail", _MovePhoneAvail);
                 contentValues.put("MovePhone", _MovePhone);
                 contentValues.put("MoveAddressAvail", _MoveAddressAvail);
                 contentValues.put("MoveAddress", _MoveAddress);
                contentValues.put("MoveVill", _MoveVill);
                contentValues.put("MoveCompound", _MoveCompound);
                contentValues.put("MoveHH", _MoveHH);
                contentValues.put("MoveReason", _MoveReason);
                contentValues.put("MoveReasonOth", _MoveReasonOth);
                contentValues.put("MoveVDate", _MoveVDate);
                contentValues.put("Rnd", _Rnd);
                contentValues.put("MoveNote", _MoveNote);
                contentValues.put("Upload", _Upload);
                contentValues.put("modifyDate", _modifyDate);
                C.UpdateData(TableName, "MovementID", (""+ _MovementID +""), contentValues);
             }
             catch(Exception  e)
             {
                response = e.getMessage();
             }
          return response;
       }

         int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
       public List<tmpMovement_DataModel> SelectAll(Context context, String SQL)
       {
           Connection C = new Connection(context);
           List<tmpMovement_DataModel> data = new ArrayList<tmpMovement_DataModel>();
           tmpMovement_DataModel d = new tmpMovement_DataModel();
           Cursor cur = C.ReadData(SQL);

           cur.moveToFirst();
           while(!cur.isAfterLast())
           {
               Count += 1;
               d = new tmpMovement_DataModel();
               d._Count = Count;
               d._MovementID = cur.getString(cur.getColumnIndex("MovementID"));
               d._HHID = cur.getString(cur.getColumnIndex("HHID"));
               d._MemID = cur.getString(cur.getColumnIndex("MemID"));
               d._MoveEvType = cur.getString(cur.getColumnIndex("MoveEvType"));
               d._MoveDate = cur.getString(cur.getColumnIndex("MoveDate"));
               d._MovePhoneAvail = cur.getString(cur.getColumnIndex("MovePhoneAvail"));
               d._MovePhone = cur.getString(cur.getColumnIndex("MovePhone"));
               d._MoveAddressAvail = cur.getString(cur.getColumnIndex("MoveAddressAvail"));
               d._MoveAddress = cur.getString(cur.getColumnIndex("MoveAddress"));
               d._MoveVill = cur.getString(cur.getColumnIndex("MoveVill"));
               d._MoveCompound = cur.getString(cur.getColumnIndex("MoveCompound"));
               d._MoveHH = cur.getString(cur.getColumnIndex("MoveHH"));
               d._MoveReason = cur.getString(cur.getColumnIndex("MoveReason"));
               d._MoveReasonOth = cur.getString(cur.getColumnIndex("MoveReasonOth"));
               d._MoveVDate = cur.getString(cur.getColumnIndex("MoveVDate"));
               d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
               d._MoveNote = cur.getString(cur.getColumnIndex("MoveNote"));
               data.add(d);

               cur.moveToNext();
           }
           cur.close();
         return data;
       }
}