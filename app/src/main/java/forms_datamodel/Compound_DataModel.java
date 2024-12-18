package forms_datamodel;

import android.content.Context;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Date;
 import Common.Global;
 import android.content.ContentValues;
 public class Compound_DataModel{

        private String _CompoundID = "";
        public String getCompoundID(){
              return _CompoundID;
         }
        public void setCompoundID(String newValue){
              _CompoundID = newValue;
         }
        private String _CompoundCode = "";
        public String getCompoundCode(){
              return _CompoundCode;
         }
        public void setCompoundCode(String newValue){
              _CompoundCode = newValue;
         }
        private String _CompoundName = "";
        public String getCompoundName(){
              return _CompoundName;
         }
        public void setCompoundName(String newValue){
              _CompoundName = newValue;
         }
        private String _CompoundAdrs = "";
        public String getCompoundAdrs(){
              return _CompoundAdrs;
         }
        public void setCompoundAdrs(String newValue){
              _CompoundAdrs = newValue;
         }
        private String _VillID = "";
        public String getVillID(){
              return _VillID;
         }
        public void setVillID(String newValue){
              _VillID = newValue;
         }


         private String _CompoundType = "";
         public String getCompoundType(){
             return _CompoundType;
         }
         public void setCompoundType(String newValue){
             _CompoundType = newValue;
         }
         private String _CompoundTypeOth = "";
         public String getCompoundTypeOth(){
             return _CompoundTypeOth;
         }
         public void setCompoundTypeOth(String newValue){
             _CompoundTypeOth = newValue;
         }
         private String _CompoundStatus = "";
         public String getCompoundStatus(){
             return _CompoundStatus;
         }
         public void setCompoundStatus(String newValue){
             _CompoundStatus = newValue;
         }
         private String _CompoundPurpose = "";
         public String getCompoundPurpose(){
             return _CompoundPurpose;
         }
         public void setCompoundPurpose(String newValue){
             _CompoundPurpose = newValue;
         }
         private String _CompoundPurposeOth = "";
         public String getCompoundPurposeOth(){
             return _CompoundPurposeOth;
         }
         public void setCompoundPurposeOth(String newValue){
             _CompoundPurposeOth = newValue;
         }


        private String _TotalHH = "";
        public String getTotalHH(){
              return _TotalHH;
         }
        public void setTotalHH(String newValue){
              _TotalHH = newValue;
         }

     private String _TotalHH_Bari = "";
     public String getTotalHH_Bari(){
         return _TotalHH_Bari;
     }
     public void setTotalHH_Bari(String newValue){
         _TotalHH_Bari = newValue;
     }

        private String _Active = "";
        public String getActive(){
              return _Active;
         }
        public void setActive(String newValue){
              _Active = newValue;
         }
        private String _ComEnDate = "";
        public String getComEnDate(){
              return _ComEnDate;
         }
        public void setComEnDate(String newValue){
              _ComEnDate = newValue;
         }
        private String _ComExDate = "";
        public String getComExDate(){
              return _ComExDate;
         }
        public void setComExDate(String newValue){
              _ComExDate = newValue;
         }
        private String _ComExReason = "";
        public String getComExReason(){
              return _ComExReason;
         }
        public void setComExReason(String newValue){
              _ComExReason = newValue;
         }
        private String _Cluster = "";
        public String getCluster(){
              return _Cluster;
         }
        public void setCluster(String newValue){
              _Cluster = newValue;
         }
        private String _Block = "";
        public String getBlock(){
              return _Block;
         }
        public void setBlock(String newValue){
              _Block = newValue;
         }
        private String _Rnd = "";
        public String getRnd(){
              return _Rnd;
         }
        public void setRnd(String newValue){
              _Rnd = newValue;
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

        String TableName = "Compound";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where CompoundID='"+ _CompoundID +"' "))
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
                 contentValues.put("CompoundID", _CompoundID);
                 contentValues.put("CompoundCode", _CompoundCode);
                 contentValues.put("CompoundName", _CompoundName);
                 contentValues.put("CompoundAdrs", _CompoundAdrs);
                 contentValues.put("VillID", _VillID);

                  contentValues.put("CompoundType", _CompoundType);
                  contentValues.put("CompoundTypeOth", _CompoundTypeOth);
                  contentValues.put("CompoundStatus", _CompoundStatus);
                  contentValues.put("CompoundPurpose", _CompoundPurpose);
                  contentValues.put("CompoundPurposeOth", _CompoundPurposeOth);

                 contentValues.put("TotalHH", _TotalHH);
                 contentValues.put("Active", _Active);
                 contentValues.put("ComEnDate", _ComEnDate);
                 contentValues.put("ComExDate", _ComExDate);
                 contentValues.put("ComExReason", _ComExReason);
                 contentValues.put("Cluster", _Cluster);
                 contentValues.put("Block", _Block);
                 contentValues.put("Rnd", _Rnd);
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
                 contentValues.put("CompoundID", _CompoundID);
                 contentValues.put("CompoundCode", _CompoundCode);
                 contentValues.put("CompoundName", _CompoundName);
                 contentValues.put("CompoundAdrs", _CompoundAdrs);
                 contentValues.put("VillID", _VillID);

                  contentValues.put("CompoundType", _CompoundType);
                  contentValues.put("CompoundTypeOth", _CompoundTypeOth);
                  contentValues.put("CompoundStatus", _CompoundStatus);
                  contentValues.put("CompoundPurpose", _CompoundPurpose);
                  contentValues.put("CompoundPurposeOth", _CompoundPurposeOth);

                 contentValues.put("TotalHH", _TotalHH);
                 contentValues.put("Active", _Active);
                 contentValues.put("ComEnDate", _ComEnDate);
                 contentValues.put("ComExDate", _ComExDate);
                 contentValues.put("ComExReason", _ComExReason);
                 contentValues.put("Cluster", _Cluster);
                 contentValues.put("Block", _Block);
                 //contentValues.put("Rnd", _Rnd);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "CompoundID", (""+ _CompoundID +""), contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }



          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }

         private String _Visited = "";
         public String getVisited(){
             return _Visited;
         }
         public void setVisited(String newValue){
             _Visited = newValue;
         }
        public List<Compound_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<Compound_DataModel> data = new ArrayList<Compound_DataModel>();
            Compound_DataModel d = new Compound_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new Compound_DataModel();
                d._Count = Count;
                d._CompoundID = cur.getString(cur.getColumnIndex("CompoundID"));
                d._CompoundCode = cur.getString(cur.getColumnIndex("CompoundCode"));
                d._CompoundName = cur.getString(cur.getColumnIndex("CompoundName"));
                d._CompoundAdrs = cur.getString(cur.getColumnIndex("CompoundAdrs"));
                d._VillID = cur.getString(cur.getColumnIndex("VillID"));

                d._CompoundType = cur.getString(cur.getColumnIndex("CompoundType"));
                d._CompoundTypeOth = cur.getString(cur.getColumnIndex("CompoundTypeOth"));
                d._CompoundStatus = cur.getString(cur.getColumnIndex("CompoundStatus"));
                d._CompoundPurpose = cur.getString(cur.getColumnIndex("CompoundPurpose"));
                d._CompoundPurposeOth = cur.getString(cur.getColumnIndex("CompoundPurposeOth"));

                d._TotalHH = cur.getString(cur.getColumnIndex("TotalHH"));
                d._Active = cur.getString(cur.getColumnIndex("Active"));
                d._ComEnDate = cur.getString(cur.getColumnIndex("ComEnDate"));
                d._ComExDate = cur.getString(cur.getColumnIndex("ComExDate"));
                d._ComExReason = cur.getString(cur.getColumnIndex("ComExReason"));
                d._Cluster = cur.getString(cur.getColumnIndex("Cluster"));
                d._Block = cur.getString(cur.getColumnIndex("Block"));
                d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
                //d._Visited = cur.getString(cur.getColumnIndex("Visited"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }

     public List<Compound_DataModel> SelectAll_Baseline(Context context, String SQL)
     {
         Connection C = new Connection(context);
         List<Compound_DataModel> data = new ArrayList<Compound_DataModel>();
         Compound_DataModel d = new Compound_DataModel();
         Cursor cur = C.ReadData(SQL);

         cur.moveToFirst();
         while(!cur.isAfterLast())
         {
             Count += 1;
             d = new Compound_DataModel();
             d._Count = Count;
             d._CompoundID = cur.getString(cur.getColumnIndex("CompoundID"));
             d._CompoundCode = cur.getString(cur.getColumnIndex("CompoundCode"));
             d._CompoundName = cur.getString(cur.getColumnIndex("CompoundName"));
             d._CompoundAdrs = cur.getString(cur.getColumnIndex("CompoundAdrs"));
             d._VillID = cur.getString(cur.getColumnIndex("VillID"));
             d._TotalHH = cur.getString(cur.getColumnIndex("TotalHH"));

             d._CompoundType = cur.getString(cur.getColumnIndex("CompoundType"));
             d._CompoundTypeOth = cur.getString(cur.getColumnIndex("CompoundTypeOth"));
             d._CompoundStatus = cur.getString(cur.getColumnIndex("CompoundStatus"));
             d._CompoundPurpose = cur.getString(cur.getColumnIndex("CompoundPurpose"));
             d._CompoundPurposeOth = cur.getString(cur.getColumnIndex("CompoundPurposeOth"));

             /*d._Cluster = cur.getString(cur.getColumnIndex("Cluster"));
             d._Block = cur.getString(cur.getColumnIndex("Block"));
             d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));*/
             d._Visited = cur.getString(cur.getColumnIndex("Visited"));
             data.add(d);


             cur.moveToNext();
         }
         cur.close();
         return data;
     }

     private String _compound_totalhh = "";
     public String getcompound_totalhh(){
         return _compound_totalhh;
     }
     private String _gps = "";
     public String getgps(){
         return _gps;
     }
     private String _gpsstatus = "";
     public String getgpsstatus(){
         return _gpsstatus;
     }

     private String _ListingStatus = "";
     public String getListingStatus(){
         return _ListingStatus;
     }

     public List<Compound_DataModel> SelectAll_ListingBaseline(Context context, String SQL)
     {
         Connection C = new Connection(context);
         List<Compound_DataModel> data = new ArrayList<Compound_DataModel>();
         Compound_DataModel d = new Compound_DataModel();
         Cursor cur = C.ReadData(SQL);

         cur.moveToFirst();
         while(!cur.isAfterLast())
         {
             Count += 1;
             d = new Compound_DataModel();
             d._Count = Count;
             d._CompoundID = cur.getString(cur.getColumnIndex("CompoundID"));
             d._CompoundCode = cur.getString(cur.getColumnIndex("CompoundCode"));
             d._CompoundName = cur.getString(cur.getColumnIndex("CompoundName"));
             d._CompoundAdrs = cur.getString(cur.getColumnIndex("CompoundAdrs"));
             d._VillID = cur.getString(cur.getColumnIndex("VillID"));
             d._TotalHH = cur.getString(cur.getColumnIndex("TotalHH"));

             d._Cluster = cur.getString(cur.getColumnIndex("Cluster"));
             d._Block = cur.getString(cur.getColumnIndex("Block"));
             d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
             d._Visited = cur.getString(cur.getColumnIndex("Visited"));
             d._compound_totalhh = cur.getString(cur.getColumnIndex("compound_totalhh"));
             d._gps = cur.getString(cur.getColumnIndex("gps"));
             d._gpsstatus = cur.getString(cur.getColumnIndex("gpsstatus"));
             d._ListingStatus = cur.getString(cur.getColumnIndex("ListingStatus"));
             data.add(d);


             cur.moveToNext();
         }
         cur.close();
         return data;
     }

     public List<Compound_DataModel> SelectAll_Surv(Context context, String SQL)
     {
         Connection C = new Connection(context);
         List<Compound_DataModel> data = new ArrayList<Compound_DataModel>();
         Compound_DataModel d = new Compound_DataModel();
         Cursor cur = C.ReadData(SQL);

         cur.moveToFirst();
         while(!cur.isAfterLast())
         {
             Count += 1;
             d = new Compound_DataModel();
             d._Count = Count;
             d._CompoundID = cur.getString(cur.getColumnIndex("CompoundID"));
             d._CompoundCode = cur.getString(cur.getColumnIndex("CompoundCode"));
             d._CompoundName = cur.getString(cur.getColumnIndex("CompoundName"));
             d._CompoundAdrs = cur.getString(cur.getColumnIndex("CompoundAdrs"));
             d._VillID = cur.getString(cur.getColumnIndex("VillID"));
             d._TotalHH = cur.getString(cur.getColumnIndex("TotalHH"));

             d._CompoundType = cur.getString(cur.getColumnIndex("CompoundType"));
             d._CompoundTypeOth = cur.getString(cur.getColumnIndex("CompoundTypeOth"));
             d._CompoundStatus = cur.getString(cur.getColumnIndex("CompoundStatus"));
             d._CompoundPurpose = cur.getString(cur.getColumnIndex("CompoundPurpose"));
             d._CompoundPurposeOth = cur.getString(cur.getColumnIndex("CompoundPurposeOth"));

             d._Cluster = cur.getString(cur.getColumnIndex("Cluster"));
             d._Block = cur.getString(cur.getColumnIndex("Block"));
             d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
             d._Visited = cur.getString(cur.getColumnIndex("Visited"));
             data.add(d);


             cur.moveToNext();
         }
         cur.close();
         return data;
     }
 }