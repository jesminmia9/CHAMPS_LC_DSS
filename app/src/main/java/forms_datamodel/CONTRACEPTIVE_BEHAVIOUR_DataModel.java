package forms_datamodel;

import android.content.Context;
 import android.annotation.SuppressLint;
 import android.database.Cursor;
 import Common.Connection;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import java.util.HashMap;
 import Utility.AuditTrial;
 import Common.Global;
 import android.content.ContentValues;

 public class CONTRACEPTIVE_BEHAVIOUR_DataModel{

        private String _uuid = "";
        public String getuuid(){
              return _uuid;
         }
        public void setuuid(String newValue){
              _uuid = newValue;
         }
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
        private String _S1Q1 = "";
        public String getS1Q1(){
              return _S1Q1;
         }
        public void setS1Q1(String newValue){
              _S1Q1 = newValue;
         }
        private String _S1Q11 = "";
        public String getS1Q11(){
              return _S1Q11;
         }
        public void setS1Q11(String newValue){
              _S1Q11 = newValue;
         }
        private String _S1Q12 = "";
        public String getS1Q12(){
              return _S1Q12;
         }
        public void setS1Q12(String newValue){
              _S1Q12 = newValue;
         }
        private String _S1Q13 = "";
        public String getS1Q13(){
              return _S1Q13;
         }
        public void setS1Q13(String newValue){
              _S1Q13 = newValue;
         }
        private String _S1Q14 = "";
        public String getS1Q14(){
              return _S1Q14;
         }
        public void setS1Q14(String newValue){
              _S1Q14 = newValue;
         }
        private String _S1Q15 = "";
        public String getS1Q15(){
              return _S1Q15;
         }
        public void setS1Q15(String newValue){
              _S1Q15 = newValue;
         }
        private String _S1Q16 = "";
        public String getS1Q16(){
              return _S1Q16;
         }
        public void setS1Q16(String newValue){
              _S1Q16 = newValue;
         }
        private String _S1Q17 = "";
        public String getS1Q17(){
              return _S1Q17;
         }
        public void setS1Q17(String newValue){
              _S1Q17 = newValue;
         }
        private String _S1Q17_1 = "";
        public String getS1Q17_1(){
              return _S1Q17_1;
         }
        public void setS1Q17_1(String newValue){
              _S1Q17_1 = newValue;
         }
        private String _S1Q21 = "";
        public String getS1Q21(){
              return _S1Q21;
         }
        public void setS1Q21(String newValue){
              _S1Q21 = newValue;
         }
        private String _S1Q22 = "";
        public String getS1Q22(){
              return _S1Q22;
         }
        public void setS1Q22(String newValue){
              _S1Q22 = newValue;
         }
        private String _S1Q23 = "";
        public String getS1Q23(){
              return _S1Q23;
         }
        public void setS1Q23(String newValue){
              _S1Q23 = newValue;
         }
        private String _S1Q24 = "";
        public String getS1Q24(){
              return _S1Q24;
         }
        public void setS1Q24(String newValue){
              _S1Q24 = newValue;
         }
        private String _S1Q25 = "";
        public String getS1Q25(){
              return _S1Q25;
         }
        public void setS1Q25(String newValue){
              _S1Q25 = newValue;
         }
        private String _S1Q26 = "";
        public String getS1Q26(){
              return _S1Q26;
         }
        public void setS1Q26(String newValue){
              _S1Q26 = newValue;
         }
        private String _S1Q27 = "";
        public String getS1Q27(){
              return _S1Q27;
         }
        public void setS1Q27(String newValue){
              _S1Q27 = newValue;
         }
        private String _S1Q28 = "";
        public String getS1Q28(){
              return _S1Q28;
         }
        public void setS1Q28(String newValue){
              _S1Q28 = newValue;
         }
        private String _S1Q28_1 = "";
        public String getS1Q28_1(){
              return _S1Q28_1;
         }
        public void setS1Q28_1(String newValue){
              _S1Q28_1 = newValue;
         }
        private String _S1Q3 = "";
        public String getS1Q3(){
              return _S1Q3;
         }
        public void setS1Q3(String newValue){
              _S1Q3 = newValue;
         }
        private String _S1Q31 = "";
        public String getS1Q31(){
              return _S1Q31;
         }
        public void setS1Q31(String newValue){
              _S1Q31 = newValue;
         }
        private String _S1Q32 = "";
        public String getS1Q32(){
              return _S1Q32;
         }
        public void setS1Q32(String newValue){
              _S1Q32 = newValue;
         }
        private String _S1Q33 = "";
        public String getS1Q33(){
              return _S1Q33;
         }
        public void setS1Q33(String newValue){
              _S1Q33 = newValue;
         }
        private String _S1Q34 = "";
        public String getS1Q34(){
              return _S1Q34;
         }
        public void setS1Q34(String newValue){
              _S1Q34 = newValue;
         }
        private String _S1Q35 = "";
        public String getS1Q35(){
              return _S1Q35;
         }
        public void setS1Q35(String newValue){
              _S1Q35 = newValue;
         }
        private String _S1Q36 = "";
        public String getS1Q36(){
              return _S1Q36;
         }
        public void setS1Q36(String newValue){
              _S1Q36 = newValue;
         }
        private String _S1Q37 = "";
        public String getS1Q37(){
              return _S1Q37;
         }
        public void setS1Q37(String newValue){
              _S1Q37 = newValue;
         }
        private String _S1Q38 = "";
        public String getS1Q38(){
              return _S1Q38;
         }
        public void setS1Q38(String newValue){
              _S1Q38 = newValue;
         }
        private String _S1Q39 = "";
        public String getS1Q39(){
              return _S1Q39;
         }
        public void setS1Q39(String newValue){
              _S1Q39 = newValue;
         }
        private String _S1Q310 = "";
        public String getS1Q310(){
              return _S1Q310;
         }
        public void setS1Q310(String newValue){
              _S1Q310 = newValue;
         }
        private String _S1Q311 = "";
        public String getS1Q311(){
              return _S1Q311;
         }
        public void setS1Q311(String newValue){
              _S1Q311 = newValue;
         }
        private String _S1Q311_1 = "";
        public String getS1Q311_1(){
              return _S1Q311_1;
         }
        public void setS1Q311_1(String newValue){
              _S1Q311_1 = newValue;
         }
        private String _S2Q4 = "";
        public String getS2Q4(){
              return _S2Q4;
         }
        public void setS2Q4(String newValue){
              _S2Q4 = newValue;
         }
        private String _S2Q51 = "";
        public String getS2Q51(){
              return _S2Q51;
         }
        public void setS2Q51(String newValue){
              _S2Q51 = newValue;
         }
        private String _S2Q52 = "";
        public String getS2Q52(){
              return _S2Q52;
         }
        public void setS2Q52(String newValue){
              _S2Q52 = newValue;
         }
        private String _S2Q53 = "";
        public String getS2Q53(){
              return _S2Q53;
         }
        public void setS2Q53(String newValue){
              _S2Q53 = newValue;
         }
        private String _S2Q54 = "";
        public String getS2Q54(){
              return _S2Q54;
         }
        public void setS2Q54(String newValue){
              _S2Q54 = newValue;
         }
        private String _S2Q55 = "";
        public String getS2Q55(){
              return _S2Q55;
         }
        public void setS2Q55(String newValue){
              _S2Q55 = newValue;
         }
        private String _S2Q6 = "";
        public String getS2Q6(){
              return _S2Q6;
         }
        public void setS2Q6(String newValue){
              _S2Q6 = newValue;
         }
        private String _S2Q7 = "";
        public String getS2Q7(){
              return _S2Q7;
         }
        public void setS2Q7(String newValue){
              _S2Q7 = newValue;
         }
        private String _S2Q81 = "";
        public String getS2Q81(){
              return _S2Q81;
         }
        public void setS2Q81(String newValue){
              _S2Q81 = newValue;
         }
        private String _S2Q82 = "";
        public String getS2Q82(){
              return _S2Q82;
         }
        public void setS2Q82(String newValue){
              _S2Q82 = newValue;
         }
        private String _S2Q83 = "";
        public String getS2Q83(){
              return _S2Q83;
         }
        public void setS2Q83(String newValue){
              _S2Q83 = newValue;
         }
        private String _S2Q84 = "";
        public String getS2Q84(){
              return _S2Q84;
         }
        public void setS2Q84(String newValue){
              _S2Q84 = newValue;
         }
        private String _S2Q84_1 = "";
        public String getS2Q84_1(){
              return _S2Q84_1;
         }
        public void setS2Q84_1(String newValue){
              _S2Q84_1 = newValue;
         }
        private String _S3Q9 = "";
        public String getS3Q9(){
              return _S3Q9;
         }
        public void setS3Q9(String newValue){
              _S3Q9 = newValue;
         }
        private String _S3Q91 = "";
        public String getS3Q91(){
              return _S3Q91;
         }
        public void setS3Q91(String newValue){
              _S3Q91 = newValue;
         }
        private String _S3Q92 = "";
        public String getS3Q92(){
              return _S3Q92;
         }
        public void setS3Q92(String newValue){
              _S3Q92 = newValue;
         }
        private String _S3Q93 = "";
        public String getS3Q93(){
              return _S3Q93;
         }
        public void setS3Q93(String newValue){
              _S3Q93 = newValue;
         }
        private String _S3Q94 = "";
        public String getS3Q94(){
              return _S3Q94;
         }
        public void setS3Q94(String newValue){
              _S3Q94 = newValue;
         }
        private String _S3Q95 = "";
        public String getS3Q95(){
              return _S3Q95;
         }
        public void setS3Q95(String newValue){
              _S3Q95 = newValue;
         }
        private String _S3Q96 = "";
        public String getS3Q96(){
              return _S3Q96;
         }
        public void setS3Q96(String newValue){
              _S3Q96 = newValue;
         }
        private String _S3Q97 = "";
        public String getS3Q97(){
              return _S3Q97;
         }
        public void setS3Q97(String newValue){
              _S3Q97 = newValue;
         }
        private String _S3Q98 = "";
        public String getS3Q98(){
              return _S3Q98;
         }
        public void setS3Q98(String newValue){
              _S3Q98 = newValue;
         }
        private String _S3Q99 = "";
        public String getS3Q99(){
              return _S3Q99;
         }
        public void setS3Q99(String newValue){
              _S3Q99 = newValue;
         }
        private String _S3Q910 = "";
        public String getS3Q910(){
              return _S3Q910;
         }
        public void setS3Q910(String newValue){
              _S3Q910 = newValue;
         }
        private String _S3Q911 = "";
        public String getS3Q911(){
              return _S3Q911;
         }
        public void setS3Q911(String newValue){
              _S3Q911 = newValue;
         }
        private String _S3Q912 = "";
        public String getS3Q912(){
              return _S3Q912;
         }
        public void setS3Q912(String newValue){
              _S3Q912 = newValue;
         }
        private String _S3Q913 = "";
        public String getS3Q913(){
              return _S3Q913;
         }
        public void setS3Q913(String newValue){
              _S3Q913 = newValue;
         }
        private String _S3Q914 = "";
        public String getS3Q914(){
              return _S3Q914;
         }
        public void setS3Q914(String newValue){
              _S3Q914 = newValue;
         }
        private String _S3Q914_1 = "";
        public String getS3Q914_1(){
              return _S3Q914_1;
         }
        public void setS3Q914_1(String newValue){
              _S3Q914_1 = newValue;
         }
        private String _S3Q10 = "";
        public String getS3Q10(){
              return _S3Q10;
         }
        public void setS3Q10(String newValue){
              _S3Q10 = newValue;
         }
        private String _S3Q10_1 = "";
        public String getS3Q10_1(){
              return _S3Q10_1;
         }
        public void setS3Q10_1(String newValue){
              _S3Q10_1 = newValue;
         }
        private String _S3Q11 = "";
        public String getS3Q11(){
              return _S3Q11;
         }
        public void setS3Q11(String newValue){
              _S3Q11 = newValue;
         }
        private String _S3Q111 = "";
        public String getS3Q111(){
              return _S3Q111;
         }
        public void setS3Q111(String newValue){
              _S3Q111 = newValue;
         }
        private String _S3Q112 = "";
        public String getS3Q112(){
              return _S3Q112;
         }
        public void setS3Q112(String newValue){
              _S3Q112 = newValue;
         }
        private String _S3Q113 = "";
        public String getS3Q113(){
              return _S3Q113;
         }
        public void setS3Q113(String newValue){
              _S3Q113 = newValue;
         }
        private String _S3Q114 = "";
        public String getS3Q114(){
              return _S3Q114;
         }
        public void setS3Q114(String newValue){
              _S3Q114 = newValue;
         }
        private String _S3Q115 = "";
        public String getS3Q115(){
              return _S3Q115;
         }
        public void setS3Q115(String newValue){
              _S3Q115 = newValue;
         }
        private String _S3Q116 = "";
        public String getS3Q116(){
              return _S3Q116;
         }
        public void setS3Q116(String newValue){
              _S3Q116 = newValue;
         }
        private String _S3Q117 = "";
        public String getS3Q117(){
              return _S3Q117;
         }
        public void setS3Q117(String newValue){
              _S3Q117 = newValue;
         }
        private String _S3Q117_1 = "";
        public String getS3Q117_1(){
              return _S3Q117_1;
         }
        public void setS3Q117_1(String newValue){
              _S3Q117_1 = newValue;
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

        String TableName = "CONTRACEPTIVE_BEHAVIOUR";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where uuid='"+ _uuid +"' "))
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
                 contentValues.put("uuid", _uuid);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("S1Q1", _S1Q1);
                 contentValues.put("S1Q11", _S1Q11);
                 contentValues.put("S1Q12", _S1Q12);
                 contentValues.put("S1Q13", _S1Q13);
                 contentValues.put("S1Q14", _S1Q14);
                 contentValues.put("S1Q15", _S1Q15);
                 contentValues.put("S1Q16", _S1Q16);
                 contentValues.put("S1Q17", _S1Q17);
                 contentValues.put("S1Q17_1", _S1Q17_1);
                 contentValues.put("S1Q21", _S1Q21);
                 contentValues.put("S1Q22", _S1Q22);
                 contentValues.put("S1Q23", _S1Q23);
                 contentValues.put("S1Q24", _S1Q24);
                 contentValues.put("S1Q25", _S1Q25);
                 contentValues.put("S1Q26", _S1Q26);
                 contentValues.put("S1Q27", _S1Q27);
                 contentValues.put("S1Q28", _S1Q28);
                 contentValues.put("S1Q28_1", _S1Q28_1);
                 contentValues.put("S1Q3", _S1Q3);
                 contentValues.put("S1Q31", _S1Q31);
                 contentValues.put("S1Q32", _S1Q32);
                 contentValues.put("S1Q33", _S1Q33);
                 contentValues.put("S1Q34", _S1Q34);
                 contentValues.put("S1Q35", _S1Q35);
                 contentValues.put("S1Q36", _S1Q36);
                 contentValues.put("S1Q37", _S1Q37);
                 contentValues.put("S1Q38", _S1Q38);
                 contentValues.put("S1Q39", _S1Q39);
                 contentValues.put("S1Q310", _S1Q310);
                 contentValues.put("S1Q311", _S1Q311);
                 contentValues.put("S1Q311_1", _S1Q311_1);
                 contentValues.put("S2Q4", _S2Q4);
                 contentValues.put("S2Q51", _S2Q51);
                 contentValues.put("S2Q52", _S2Q52);
                 contentValues.put("S2Q53", _S2Q53);
                 contentValues.put("S2Q54", _S2Q54);
                 contentValues.put("S2Q55", _S2Q55);
                 contentValues.put("S2Q6", _S2Q6);
                 contentValues.put("S2Q7", _S2Q7);
                 contentValues.put("S2Q81", _S2Q81);
                 contentValues.put("S2Q82", _S2Q82);
                 contentValues.put("S2Q83", _S2Q83);
                 contentValues.put("S2Q84", _S2Q84);
                 contentValues.put("S2Q84_1", _S2Q84_1);
                 contentValues.put("S3Q9", _S3Q9);
                 contentValues.put("S3Q91", _S3Q91);
                 contentValues.put("S3Q92", _S3Q92);
                 contentValues.put("S3Q93", _S3Q93);
                 contentValues.put("S3Q94", _S3Q94);
                 contentValues.put("S3Q95", _S3Q95);
                 contentValues.put("S3Q96", _S3Q96);
                 contentValues.put("S3Q97", _S3Q97);
                 contentValues.put("S3Q98", _S3Q98);
                 contentValues.put("S3Q99", _S3Q99);
                 contentValues.put("S3Q910", _S3Q910);
                 contentValues.put("S3Q911", _S3Q911);
                 contentValues.put("S3Q912", _S3Q912);
                 contentValues.put("S3Q913", _S3Q913);
                 contentValues.put("S3Q914", _S3Q914);
                 contentValues.put("S3Q914_1", _S3Q914_1);
                 contentValues.put("S3Q10", _S3Q10);
                 contentValues.put("S3Q10_1", _S3Q10_1);
                 contentValues.put("S3Q11", _S3Q11);
                 contentValues.put("S3Q111", _S3Q111);
                 contentValues.put("S3Q112", _S3Q112);
                 contentValues.put("S3Q113", _S3Q113);
                 contentValues.put("S3Q114", _S3Q114);
                 contentValues.put("S3Q115", _S3Q115);
                 contentValues.put("S3Q116", _S3Q116);
                 contentValues.put("S3Q117", _S3Q117);
                 contentValues.put("S3Q117_1", _S3Q117_1);
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
                 contentValues.put("uuid", _uuid);
                 contentValues.put("MemID", _MemID);
                 contentValues.put("HHID", _HHID);
                 contentValues.put("S1Q1", _S1Q1);
                 contentValues.put("S1Q11", _S1Q11);
                 contentValues.put("S1Q12", _S1Q12);
                 contentValues.put("S1Q13", _S1Q13);
                 contentValues.put("S1Q14", _S1Q14);
                 contentValues.put("S1Q15", _S1Q15);
                 contentValues.put("S1Q16", _S1Q16);
                 contentValues.put("S1Q17", _S1Q17);
                 contentValues.put("S1Q17_1", _S1Q17_1);
                 contentValues.put("S1Q21", _S1Q21);
                 contentValues.put("S1Q22", _S1Q22);
                 contentValues.put("S1Q23", _S1Q23);
                 contentValues.put("S1Q24", _S1Q24);
                 contentValues.put("S1Q25", _S1Q25);
                 contentValues.put("S1Q26", _S1Q26);
                 contentValues.put("S1Q27", _S1Q27);
                 contentValues.put("S1Q28", _S1Q28);
                 contentValues.put("S1Q28_1", _S1Q28_1);
                 contentValues.put("S1Q3", _S1Q3);
                 contentValues.put("S1Q31", _S1Q31);
                 contentValues.put("S1Q32", _S1Q32);
                 contentValues.put("S1Q33", _S1Q33);
                 contentValues.put("S1Q34", _S1Q34);
                 contentValues.put("S1Q35", _S1Q35);
                 contentValues.put("S1Q36", _S1Q36);
                 contentValues.put("S1Q37", _S1Q37);
                 contentValues.put("S1Q38", _S1Q38);
                 contentValues.put("S1Q39", _S1Q39);
                 contentValues.put("S1Q310", _S1Q310);
                 contentValues.put("S1Q311", _S1Q311);
                 contentValues.put("S1Q311_1", _S1Q311_1);
                 contentValues.put("S2Q4", _S2Q4);
                 contentValues.put("S2Q51", _S2Q51);
                 contentValues.put("S2Q52", _S2Q52);
                 contentValues.put("S2Q53", _S2Q53);
                 contentValues.put("S2Q54", _S2Q54);
                 contentValues.put("S2Q55", _S2Q55);
                 contentValues.put("S2Q6", _S2Q6);
                 contentValues.put("S2Q7", _S2Q7);
                 contentValues.put("S2Q81", _S2Q81);
                 contentValues.put("S2Q82", _S2Q82);
                 contentValues.put("S2Q83", _S2Q83);
                 contentValues.put("S2Q84", _S2Q84);
                 contentValues.put("S2Q84_1", _S2Q84_1);
                 contentValues.put("S3Q9", _S3Q9);
                 contentValues.put("S3Q91", _S3Q91);
                 contentValues.put("S3Q92", _S3Q92);
                 contentValues.put("S3Q93", _S3Q93);
                 contentValues.put("S3Q94", _S3Q94);
                 contentValues.put("S3Q95", _S3Q95);
                 contentValues.put("S3Q96", _S3Q96);
                 contentValues.put("S3Q97", _S3Q97);
                 contentValues.put("S3Q98", _S3Q98);
                 contentValues.put("S3Q99", _S3Q99);
                 contentValues.put("S3Q910", _S3Q910);
                 contentValues.put("S3Q911", _S3Q911);
                 contentValues.put("S3Q912", _S3Q912);
                 contentValues.put("S3Q913", _S3Q913);
                 contentValues.put("S3Q914", _S3Q914);
                 contentValues.put("S3Q914_1", _S3Q914_1);
                 contentValues.put("S3Q10", _S3Q10);
                 contentValues.put("S3Q10_1", _S3Q10_1);
                 contentValues.put("S3Q11", _S3Q11);
                 contentValues.put("S3Q111", _S3Q111);
                 contentValues.put("S3Q112", _S3Q112);
                 contentValues.put("S3Q113", _S3Q113);
                 contentValues.put("S3Q114", _S3Q114);
                 contentValues.put("S3Q115", _S3Q115);
                 contentValues.put("S3Q116", _S3Q116);
                 contentValues.put("S3Q117", _S3Q117);
                 contentValues.put("S3Q117_1", _S3Q117_1);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "uuid", (""+ _uuid +""), contentValues);

                 manageAudit(context,this,AuditTrial.KEY_UPDATE);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }


        int Count = 0;
        private int _Count = 0;
        public int getCount(){ return _Count; }

        @SuppressLint("Range")
        public List<CONTRACEPTIVE_BEHAVIOUR_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<CONTRACEPTIVE_BEHAVIOUR_DataModel> data = new ArrayList<CONTRACEPTIVE_BEHAVIOUR_DataModel>();
            CONTRACEPTIVE_BEHAVIOUR_DataModel d = new CONTRACEPTIVE_BEHAVIOUR_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new CONTRACEPTIVE_BEHAVIOUR_DataModel();
                d._Count = Count;
                d._uuid = cur.getString(cur.getColumnIndex("uuid"));
                d._MemID = cur.getString(cur.getColumnIndex("MemID"));
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._S1Q1 = cur.getString(cur.getColumnIndex("S1Q1"));
                d._S1Q11 = cur.getString(cur.getColumnIndex("S1Q11"));
                d._S1Q12 = cur.getString(cur.getColumnIndex("S1Q12"));
                d._S1Q13 = cur.getString(cur.getColumnIndex("S1Q13"));
                d._S1Q14 = cur.getString(cur.getColumnIndex("S1Q14"));
                d._S1Q15 = cur.getString(cur.getColumnIndex("S1Q15"));
                d._S1Q16 = cur.getString(cur.getColumnIndex("S1Q16"));
                d._S1Q17 = cur.getString(cur.getColumnIndex("S1Q17"));
                d._S1Q17_1 = cur.getString(cur.getColumnIndex("S1Q17_1"));
                d._S1Q21 = cur.getString(cur.getColumnIndex("S1Q21"));
                d._S1Q22 = cur.getString(cur.getColumnIndex("S1Q22"));
                d._S1Q23 = cur.getString(cur.getColumnIndex("S1Q23"));
                d._S1Q24 = cur.getString(cur.getColumnIndex("S1Q24"));
                d._S1Q25 = cur.getString(cur.getColumnIndex("S1Q25"));
                d._S1Q26 = cur.getString(cur.getColumnIndex("S1Q26"));
                d._S1Q27 = cur.getString(cur.getColumnIndex("S1Q27"));
                d._S1Q28 = cur.getString(cur.getColumnIndex("S1Q28"));
                d._S1Q28_1 = cur.getString(cur.getColumnIndex("S1Q28_1"));
                d._S1Q3 = cur.getString(cur.getColumnIndex("S1Q3"));
                d._S1Q31 = cur.getString(cur.getColumnIndex("S1Q31"));
                d._S1Q32 = cur.getString(cur.getColumnIndex("S1Q32"));
                d._S1Q33 = cur.getString(cur.getColumnIndex("S1Q33"));
                d._S1Q34 = cur.getString(cur.getColumnIndex("S1Q34"));
                d._S1Q35 = cur.getString(cur.getColumnIndex("S1Q35"));
                d._S1Q36 = cur.getString(cur.getColumnIndex("S1Q36"));
                d._S1Q37 = cur.getString(cur.getColumnIndex("S1Q37"));
                d._S1Q38 = cur.getString(cur.getColumnIndex("S1Q38"));
                d._S1Q39 = cur.getString(cur.getColumnIndex("S1Q39"));
                d._S1Q310 = cur.getString(cur.getColumnIndex("S1Q310"));
                d._S1Q311 = cur.getString(cur.getColumnIndex("S1Q311"));
                d._S1Q311_1 = cur.getString(cur.getColumnIndex("S1Q311_1"));
                d._S2Q4 = cur.getString(cur.getColumnIndex("S2Q4"));
                d._S2Q51 = cur.getString(cur.getColumnIndex("S2Q51"));
                d._S2Q52 = cur.getString(cur.getColumnIndex("S2Q52"));
                d._S2Q53 = cur.getString(cur.getColumnIndex("S2Q53"));
                d._S2Q54 = cur.getString(cur.getColumnIndex("S2Q54"));
                d._S2Q55 = cur.getString(cur.getColumnIndex("S2Q55"));
                d._S2Q6 = cur.getString(cur.getColumnIndex("S2Q6"));
                d._S2Q7 = cur.getString(cur.getColumnIndex("S2Q7"));
                d._S2Q81 = cur.getString(cur.getColumnIndex("S2Q81"));
                d._S2Q82 = cur.getString(cur.getColumnIndex("S2Q82"));
                d._S2Q83 = cur.getString(cur.getColumnIndex("S2Q83"));
                d._S2Q84 = cur.getString(cur.getColumnIndex("S2Q84"));
                d._S2Q84_1 = cur.getString(cur.getColumnIndex("S2Q84_1"));
                d._S3Q9 = cur.getString(cur.getColumnIndex("S3Q9"));
                d._S3Q91 = cur.getString(cur.getColumnIndex("S3Q91"));
                d._S3Q92 = cur.getString(cur.getColumnIndex("S3Q92"));
                d._S3Q93 = cur.getString(cur.getColumnIndex("S3Q93"));
                d._S3Q94 = cur.getString(cur.getColumnIndex("S3Q94"));
                d._S3Q95 = cur.getString(cur.getColumnIndex("S3Q95"));
                d._S3Q96 = cur.getString(cur.getColumnIndex("S3Q96"));
                d._S3Q97 = cur.getString(cur.getColumnIndex("S3Q97"));
                d._S3Q98 = cur.getString(cur.getColumnIndex("S3Q98"));
                d._S3Q99 = cur.getString(cur.getColumnIndex("S3Q99"));
                d._S3Q910 = cur.getString(cur.getColumnIndex("S3Q910"));
                d._S3Q911 = cur.getString(cur.getColumnIndex("S3Q911"));
                d._S3Q912 = cur.getString(cur.getColumnIndex("S3Q912"));
                d._S3Q913 = cur.getString(cur.getColumnIndex("S3Q913"));
                d._S3Q914 = cur.getString(cur.getColumnIndex("S3Q914"));
                d._S3Q914_1 = cur.getString(cur.getColumnIndex("S3Q914_1"));
                d._S3Q10 = cur.getString(cur.getColumnIndex("S3Q10"));
                d._S3Q10_1 = cur.getString(cur.getColumnIndex("S3Q10_1"));
                d._S3Q11 = cur.getString(cur.getColumnIndex("S3Q11"));
                d._S3Q111 = cur.getString(cur.getColumnIndex("S3Q111"));
                d._S3Q112 = cur.getString(cur.getColumnIndex("S3Q112"));
                d._S3Q113 = cur.getString(cur.getColumnIndex("S3Q113"));
                d._S3Q114 = cur.getString(cur.getColumnIndex("S3Q114"));
                d._S3Q115 = cur.getString(cur.getColumnIndex("S3Q115"));
                d._S3Q116 = cur.getString(cur.getColumnIndex("S3Q116"));
                d._S3Q117 = cur.getString(cur.getColumnIndex("S3Q117"));
                d._S3Q117_1 = cur.getString(cur.getColumnIndex("S3Q117_1"));
                data.add(d);

                manageAudit(context,d,AuditTrial.KEY_SELECT);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }



        static Map<String, Object> firstStateMap;
        public void manageAudit(Context context, CONTRACEPTIVE_BEHAVIOUR_DataModel ob, String key) {
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
        public Map<String, Object> getMapData(CONTRACEPTIVE_BEHAVIOUR_DataModel ob) {
            Map<String, Object> data = new HashMap<>();

            if (ob != null) {
                 data.put("uuid", ob._uuid);
                 data.put("MemID", ob._MemID);
                 data.put("HHID", ob._HHID);
                 data.put("S1Q1", ob._S1Q1);
                 data.put("S1Q11", ob._S1Q11);
                 data.put("S1Q12", ob._S1Q12);
                 data.put("S1Q13", ob._S1Q13);
                 data.put("S1Q14", ob._S1Q14);
                 data.put("S1Q15", ob._S1Q15);
                 data.put("S1Q16", ob._S1Q16);
                 data.put("S1Q17", ob._S1Q17);
                 data.put("S1Q17_1", ob._S1Q17_1);
                 data.put("S1Q21", ob._S1Q21);
                 data.put("S1Q22", ob._S1Q22);
                 data.put("S1Q23", ob._S1Q23);
                 data.put("S1Q24", ob._S1Q24);
                 data.put("S1Q25", ob._S1Q25);
                 data.put("S1Q26", ob._S1Q26);
                 data.put("S1Q27", ob._S1Q27);
                 data.put("S1Q28", ob._S1Q28);
                 data.put("S1Q28_1", ob._S1Q28_1);
                 data.put("S1Q3", ob._S1Q3);
                 data.put("S1Q31", ob._S1Q31);
                 data.put("S1Q32", ob._S1Q32);
                 data.put("S1Q33", ob._S1Q33);
                 data.put("S1Q34", ob._S1Q34);
                 data.put("S1Q35", ob._S1Q35);
                 data.put("S1Q36", ob._S1Q36);
                 data.put("S1Q37", ob._S1Q37);
                 data.put("S1Q38", ob._S1Q38);
                 data.put("S1Q39", ob._S1Q39);
                 data.put("S1Q310", ob._S1Q310);
                 data.put("S1Q311", ob._S1Q311);
                 data.put("S1Q311_1", ob._S1Q311_1);
                 data.put("S2Q4", ob._S2Q4);
                 data.put("S2Q51", ob._S2Q51);
                 data.put("S2Q52", ob._S2Q52);
                 data.put("S2Q53", ob._S2Q53);
                 data.put("S2Q54", ob._S2Q54);
                 data.put("S2Q55", ob._S2Q55);
                 data.put("S2Q6", ob._S2Q6);
                 data.put("S2Q7", ob._S2Q7);
                 data.put("S2Q81", ob._S2Q81);
                 data.put("S2Q82", ob._S2Q82);
                 data.put("S2Q83", ob._S2Q83);
                 data.put("S2Q84", ob._S2Q84);
                 data.put("S2Q84_1", ob._S2Q84_1);
                 data.put("S3Q9", ob._S3Q9);
                 data.put("S3Q91", ob._S3Q91);
                 data.put("S3Q92", ob._S3Q92);
                 data.put("S3Q93", ob._S3Q93);
                 data.put("S3Q94", ob._S3Q94);
                 data.put("S3Q95", ob._S3Q95);
                 data.put("S3Q96", ob._S3Q96);
                 data.put("S3Q97", ob._S3Q97);
                 data.put("S3Q98", ob._S3Q98);
                 data.put("S3Q99", ob._S3Q99);
                 data.put("S3Q910", ob._S3Q910);
                 data.put("S3Q911", ob._S3Q911);
                 data.put("S3Q912", ob._S3Q912);
                 data.put("S3Q913", ob._S3Q913);
                 data.put("S3Q914", ob._S3Q914);
                 data.put("S3Q914_1", ob._S3Q914_1);
                 data.put("S3Q10", ob._S3Q10);
                 data.put("S3Q10_1", ob._S3Q10_1);
                 data.put("S3Q11", ob._S3Q11);
                 data.put("S3Q111", ob._S3Q111);
                 data.put("S3Q112", ob._S3Q112);
                 data.put("S3Q113", ob._S3Q113);
                 data.put("S3Q114", ob._S3Q114);
                 data.put("S3Q115", ob._S3Q115);
                 data.put("S3Q116", ob._S3Q116);
                 data.put("S3Q117", ob._S3Q117);
                 data.put("S3Q117_1", ob._S3Q117_1);
            
            }
            return data;
        }
 }