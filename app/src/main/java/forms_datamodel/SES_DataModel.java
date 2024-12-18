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
 public class SES_DataModel{

        private String _HHID = "";
        public String getHHID(){
              return _HHID;
         }
        public void setHHID(String newValue){
              _HHID = newValue;
         }
        private String _SESNo = "";
        public String getSESNo(){
              return _SESNo;
         }
        public void setSESNo(String newValue){
              _SESNo = newValue;
         }
        private String _SESVDate = "";
        public String getSESVDate(){
              return _SESVDate;
         }
        public void setSESVDate(String newValue){
              _SESVDate = newValue;
         }
        private String _SESVStatus = "";
        public String getSESVStatus(){
              return _SESVStatus;
         }
        public void setSESVStatus(String newValue){
              _SESVStatus = newValue;
         }
        private String _SESVStatusOth = "";
        public String getSESVStatusOth(){
              return _SESVStatusOth;
         }
        public void setSESVStatusOth(String newValue){
              _SESVStatusOth = newValue;
         }
        private String _Rnd = "";
        public String getRnd(){
              return _Rnd;
         }
        public void setRnd(String newValue){
              _Rnd = newValue;
         }
        private String _WSDrink = "";
        public String getWSDrink(){
              return _WSDrink;
         }
        public void setWSDrink(String newValue){
              _WSDrink = newValue;
         }
        private String _WSDrinkOth = "";
        public String getWSDrinkOth(){
              return _WSDrinkOth;
         }
        public void setWSDrinkOth(String newValue){
              _WSDrinkOth = newValue;
         }
        private String _Toilet = "";
        public String getToilet(){
              return _Toilet;
         }
        public void setToilet(String newValue){
              _Toilet = newValue;
         }
        private String _ToiletOth = "";
        public String getToiletOth(){
              return _ToiletOth;
         }
        public void setToiletOth(String newValue){
              _ToiletOth = newValue;
         }
        private String _ToiletShrd = "";
        public String getToiletShrd(){
              return _ToiletShrd;
         }
        public void setToiletShrd(String newValue){
              _ToiletShrd = newValue;
         }
        private String _ToiletUseNo = "";
        public String getToiletUseNo(){
              return _ToiletUseNo;
         }
        public void setToiletUseNo(String newValue){
              _ToiletUseNo = newValue;
         }
        private String _ToiletUseNoDk = "";
        public String getToiletUseNoDk(){
              return _ToiletUseNoDk;
         }
        public void setToiletUseNoDk(String newValue){
              _ToiletUseNoDk = newValue;
         }
        private String _ToiletLoc = "";
        public String getToiletLoc(){
              return _ToiletLoc;
         }
        public void setToiletLoc(String newValue){
              _ToiletLoc = newValue;
         }
        private String _CookDvc = "";
        public String getCookDvc(){
              return _CookDvc;
         }
        public void setCookDvc(String newValue){
              _CookDvc = newValue;
         }
        private String _CookDvcOth = "";
        public String getCookDvcOth(){
              return _CookDvcOth;
         }
        public void setCookDvcOth(String newValue){
              _CookDvcOth = newValue;
         }
        private String _CookFuel = "";
        public String getCookFuel(){
              return _CookFuel;
         }
        public void setCookFuel(String newValue){
              _CookFuel = newValue;
         }
        private String _CookFuelOth = "";
        public String getCookFuelOth(){
              return _CookFuelOth;
         }
        public void setCookFuelOth(String newValue){
              _CookFuelOth = newValue;
         }
        private String _CookPlc = "";
        public String getCookPlc(){
              return _CookPlc;
         }
        public void setCookPlc(String newValue){
              _CookPlc = newValue;
         }
        private String _CookPlcOth = "";
        public String getCookPlcOth(){
              return _CookPlcOth;
         }
        public void setCookPlcOth(String newValue){
              _CookPlcOth = newValue;
         }
        private String _Floor = "";
        public String getFloor(){
              return _Floor;
         }
        public void setFloor(String newValue){
              _Floor = newValue;
         }
        private String _FloorOth = "";
        public String getFloorOth(){
              return _FloorOth;
         }
        public void setFloorOth(String newValue){
              _FloorOth = newValue;
         }
        private String _Roof = "";
        public String getRoof(){
              return _Roof;
         }
        public void setRoof(String newValue){
              _Roof = newValue;
         }
        private String _RoofOth = "";
        public String getRoofOth(){
              return _RoofOth;
         }
        public void setRoofOth(String newValue){
              _RoofOth = newValue;
         }
        private String _Wall = "";
        public String getWall(){
              return _Wall;
         }
        public void setWall(String newValue){
              _Wall = newValue;
         }
        private String _WallOth = "";
        public String getWallOth(){
              return _WallOth;
         }
        public void setWallOth(String newValue){
              _WallOth = newValue;
         }
        private String _RoomSleep = "";
        public String getRoomSleep(){
              return _RoomSleep;
         }
        public void setRoomSleep(String newValue){
              _RoomSleep = newValue;
         }
        private String _RoomSleepDk = "";
        public String getRoomSleepDk(){
              return _RoomSleepDk;
         }
        public void setRoomSleepDk(String newValue){
              _RoomSleepDk = newValue;
         }
        private String _HomesteadAny = "";
        public String getHomesteadAny(){
              return _HomesteadAny;
         }
        public void setHomesteadAny(String newValue){
              _HomesteadAny = newValue;
         }
        private String _OthLand = "";
        public String getOthLand(){
              return _OthLand;
         }
        public void setOthLand(String newValue){
              _OthLand = newValue;
         }
        private String _Electricity = "";
        public String getElectricity(){
              return _Electricity;
         }
        public void setElectricity(String newValue){
              _Electricity = newValue;
         }
        private String _Heater = "";
        public String getHeater(){
              return _Heater;
         }
        public void setHeater(String newValue){
              _Heater = newValue;
         }
        private String _AC = "";
        public String getAC(){
              return _AC;
         }
        public void setAC(String newValue){
              _AC = newValue;
         }
        private String _ElecFan = "";
        public String getElecFan(){
              return _ElecFan;
         }
        public void setElecFan(String newValue){
              _ElecFan = newValue;
         }
        private String _Lantern = "";
        public String getLantern(){
              return _Lantern;
         }
        public void setLantern(String newValue){
              _Lantern = newValue;
         }
        private String _Lamp = "";
        public String getLamp(){
              return _Lamp;
         }
        public void setLamp(String newValue){
              _Lamp = newValue;
         }
        private String _Matt = "";
        public String getMatt(){
              return _Matt;
         }
        public void setMatt(String newValue){
              _Matt = newValue;
         }
        private String _Bed = "";
        public String getBed(){
              return _Bed;
         }
        public void setBed(String newValue){
              _Bed = newValue;
         }
        private String _Chair = "";
        public String getChair(){
              return _Chair;
         }
        public void setChair(String newValue){
              _Chair = newValue;
         }
        private String _Sofa = "";
        public String getSofa(){
              return _Sofa;
         }
        public void setSofa(String newValue){
              _Sofa = newValue;
         }
        private String _Tables = "";
        public String getTables(){
              return _Tables;
         }
        public void setTables(String newValue){
              _Tables = newValue;
         }
        private String _Watch = "";
        public String getWatch(){
              return _Watch;
         }
        public void setWatch(String newValue){
              _Watch = newValue;
         }
        private String _WMachine = "";
        public String getWMachine(){
              return _WMachine;
         }
        public void setWMachine(String newValue){
              _WMachine = newValue;
         }
        private String _Iron = "";
        public String getIron(){
              return _Iron;
         }
        public void setIron(String newValue){
              _Iron = newValue;
         }
        private String _Booth = "";
        public String getBooth(){
              return _Booth;
         }
        public void setBooth(String newValue){
              _Booth = newValue;
         }
        private String _SMachine = "";
        public String getSMachine(){
              return _SMachine;
         }
        public void setSMachine(String newValue){
              _SMachine = newValue;
         }
        private String _Generator = "";
        public String getGenerator(){
              return _Generator;
         }
        public void setGenerator(String newValue){
              _Generator = newValue;
         }
     private String _Mosquito = "";
     public String getMosquito(){
         return _Mosquito;
     }
     public void setMosquito(String newValue){
         _Mosquito = newValue;
     }
        private String _Internet = "";
        public String getInternet(){
              return _Internet;
         }
        public void setInternet(String newValue){
              _Internet = newValue;
         }
        private String _Satellite = "";
        public String getSatellite(){
              return _Satellite;
         }
        public void setSatellite(String newValue){
              _Satellite = newValue;
         }
        private String _Landline = "";
        public String getLandline(){
              return _Landline;
         }
        public void setLandline(String newValue){
              _Landline = newValue;
         }
        private String _Cellphone = "";
        public String getCellphone(){
              return _Cellphone;
         }
        public void setCellphone(String newValue){
              _Cellphone = newValue;
         }
        private String _TV = "";
        public String getTV(){
              return _TV;
         }
        public void setTV(String newValue){
              _TV = newValue;
         }
        private String _Radio = "";
        public String getRadio(){
              return _Radio;
         }
        public void setRadio(String newValue){
              _Radio = newValue;
         }
        private String _DVD = "";
        public String getDVD(){
              return _DVD;
         }
        public void setDVD(String newValue){
              _DVD = newValue;
         }
        private String _Video = "";
        public String getVideo(){
              return _Video;
         }
        public void setVideo(String newValue){
              _Video = newValue;
         }
        private String _Computer = "";
        public String getComputer(){
              return _Computer;
         }
        public void setComputer(String newValue){
              _Computer = newValue;
         }
        private String _Cable = "";
        public String getCable(){
              return _Cable;
         }
        public void setCable(String newValue){
              _Cable = newValue;
         }
        private String _Microwave = "";
        public String getMicrowave(){
              return _Microwave;
         }
        public void setMicrowave(String newValue){
              _Microwave = newValue;
         }
        private String _Geyser = "";
        public String getGeyser(){
              return _Geyser;
         }
        public void setGeyser(String newValue){
              _Geyser = newValue;
         }
        private String _Grill = "";
        public String getGrill(){
              return _Grill;
         }
        public void setGrill(String newValue){
              _Grill = newValue;
         }
        private String _Fridge = "";
        public String getFridge(){
              return _Fridge;
         }
        public void setFridge(String newValue){
              _Fridge = newValue;
         }
        private String _DeepFreezer = "";
        public String getDeepFreezer(){
              return _DeepFreezer;
         }
        public void setDeepFreezer(String newValue){
              _DeepFreezer = newValue;
         }
        private String _Stove = "";
        public String getStove(){
              return _Stove;
         }
        public void setStove(String newValue){
              _Stove = newValue;
         }
     private String _Blender = "";
     public String getBlender(){
         return _Blender;
     }
     public void setBlender(String newValue){
         _Blender = newValue;
     }
        private String _Bike = "";
        public String getBike(){
              return _Bike;
         }
        public void setBike(String newValue){
              _Bike = newValue;
         }
        private String _Motorcycle = "";
        public String getMotorcycle(){
              return _Motorcycle;
         }
        public void setMotorcycle(String newValue){
              _Motorcycle = newValue;
         }
        private String _Car = "";
        public String getCar(){
              return _Car;
         }
        public void setCar(String newValue){
              _Car = newValue;
         }
        private String _Rickshaw = "";
        public String getRickshaw(){
              return _Rickshaw;
         }
        public void setRickshaw(String newValue){
              _Rickshaw = newValue;
         }
        private String _Cart = "";
        public String getCart(){
              return _Cart;
         }
        public void setCart(String newValue){
              _Cart = newValue;
         }
        private String _Canoe = "";
        public String getCanoe(){
              return _Canoe;
         }
        public void setCanoe(String newValue){
              _Canoe = newValue;
         }
        private String _Bus = "";
        public String getBus(){
              return _Bus;
         }
        public void setBus(String newValue){
              _Bus = newValue;
         }
        private String _Tractor = "";
        public String getTractor(){
              return _Tractor;
         }
        public void setTractor(String newValue){
              _Tractor = newValue;
         }
        private String _Plow = "";
        public String getPlow(){
              return _Plow;
         }
        public void setPlow(String newValue){
              _Plow = newValue;
         }
        private String _Duck = "";
        public String getDuck(){
              return _Duck;
         }
        public void setDuck(String newValue){
              _Duck = newValue;
         }
        private String _Cow = "";
        public String getCow(){
              return _Cow;
         }
        public void setCow(String newValue){
              _Cow = newValue;
         }
        private String _Sheep = "";
        public String getSheep(){
              return _Sheep;
         }
        public void setSheep(String newValue){
              _Sheep = newValue;
         }
        private String _Goat = "";
        public String getGoat(){
              return _Goat;
         }
        public void setGoat(String newValue){
              _Goat = newValue;
         }
        private String _Chicken = "";
        public String getChicken(){
              return _Chicken;
         }
        public void setChicken(String newValue){
              _Chicken = newValue;
         }
        private String _Donkey = "";
        public String getDonkey(){
              return _Donkey;
         }
        public void setDonkey(String newValue){
              _Donkey = newValue;
         }
        private String _Horse = "";
        public String getHorse(){
              return _Horse;
         }
        public void setHorse(String newValue){
              _Horse = newValue;
         }
        private String _Pig = "";
        public String getPig(){
              return _Pig;
         }
        public void setPig(String newValue){
              _Pig = newValue;
         }
        private String _OtherAsset = "";
        public String getOtherAsset(){
              return _OtherAsset;
         }
        public void setOtherAsset(String newValue){
              _OtherAsset = newValue;
         }
        private String _OtherAsset1 = "";
        public String getOtherAsset1(){
              return _OtherAsset1;
         }
        public void setOtherAsset1(String newValue){
              _OtherAsset1 = newValue;
         }
        private String _OtherAsset2 = "";
        public String getOtherAsset2(){
              return _OtherAsset2;
         }
        public void setOtherAsset2(String newValue){
              _OtherAsset2 = newValue;
         }
        private String _OtherAsset3 = "";
        public String getOtherAsset3(){
              return _OtherAsset3;
         }
        public void setOtherAsset3(String newValue){
              _OtherAsset3 = newValue;
         }
        private String _SESNote = "";
        public String getSESNote(){
              return _SESNote;
         }
        public void setSESNote(String newValue){
              _SESNote = newValue;
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

        String TableName = "SES";

        public String SaveUpdateData(Context context)
        {
            String response = "";
            C = new Connection(context);
            String SQL = "";
            try
            {
                 if(C.Existence("Select * from "+ TableName +"  Where HHID='"+ _HHID +"' and SESNo='"+ _SESNo +"' "))
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
                 contentValues.put("HHID", _HHID);
                 contentValues.put("SESNo", _SESNo);
                 contentValues.put("SESVDate", _SESVDate);
                 contentValues.put("SESVStatus", _SESVStatus);
                 contentValues.put("SESVStatusOth", _SESVStatusOth);
                 contentValues.put("Rnd", _Rnd);
                 contentValues.put("WSDrink", _WSDrink);
                 contentValues.put("WSDrinkOth", _WSDrinkOth);
                 contentValues.put("Toilet", _Toilet);
                 contentValues.put("ToiletOth", _ToiletOth);
                 contentValues.put("ToiletShrd", _ToiletShrd);
                 contentValues.put("ToiletUseNo", _ToiletUseNo);
                 contentValues.put("ToiletUseNoDk", _ToiletUseNoDk);
                 contentValues.put("ToiletLoc", _ToiletLoc);
                 contentValues.put("CookDvc", _CookDvc);
                 contentValues.put("CookDvcOth", _CookDvcOth);
                 contentValues.put("CookFuel", _CookFuel);
                 contentValues.put("CookFuelOth", _CookFuelOth);
                 contentValues.put("CookPlc", _CookPlc);
                 contentValues.put("CookPlcOth", _CookPlcOth);
                 contentValues.put("Floor", _Floor);
                 contentValues.put("FloorOth", _FloorOth);
                 contentValues.put("Roof", _Roof);
                 contentValues.put("RoofOth", _RoofOth);
                 contentValues.put("Wall", _Wall);
                 contentValues.put("WallOth", _WallOth);
                 contentValues.put("RoomSleep", _RoomSleep);
                 contentValues.put("RoomSleepDk", _RoomSleepDk);
                 contentValues.put("HomesteadAny", _HomesteadAny);
                 contentValues.put("OthLand", _OthLand);
                 contentValues.put("Electricity", _Electricity);
                 contentValues.put("Heater", _Heater);
                 contentValues.put("AC", _AC);
                 contentValues.put("ElecFan", _ElecFan);
                 contentValues.put("Lantern", _Lantern);
                 contentValues.put("Lamp", _Lamp);
                 contentValues.put("Matt", _Matt);
                 contentValues.put("Bed", _Bed);
                 contentValues.put("Chair", _Chair);
                 contentValues.put("Sofa", _Sofa);
                 contentValues.put("Tables", _Tables);
                 contentValues.put("Watch", _Watch);
                 contentValues.put("WMachine", _WMachine);
                 contentValues.put("Iron", _Iron);
                 contentValues.put("Booth", _Booth);
                 contentValues.put("SMachine", _SMachine);
                 contentValues.put("Generator", _Generator);
                  contentValues.put("Mosquito", _Mosquito);
                 contentValues.put("Internet", _Internet);
                 contentValues.put("Satellite", _Satellite);
                 contentValues.put("Landline", _Landline);
                 contentValues.put("Cellphone", _Cellphone);
                 contentValues.put("TV", _TV);
                 contentValues.put("Radio", _Radio);
                 contentValues.put("DVD", _DVD);
                 contentValues.put("Video", _Video);
                 contentValues.put("Computer", _Computer);
                 contentValues.put("Cable", _Cable);
                 contentValues.put("Microwave", _Microwave);
                 contentValues.put("Geyser", _Geyser);
                 contentValues.put("Grill", _Grill);
                 contentValues.put("Fridge", _Fridge);
                 contentValues.put("DeepFreezer", _DeepFreezer);
                 contentValues.put("Stove", _Stove);
                  contentValues.put("Blender", _Blender);
                 contentValues.put("Bike", _Bike);
                 contentValues.put("Motorcycle", _Motorcycle);
                 contentValues.put("Car", _Car);
                 contentValues.put("Rickshaw", _Rickshaw);
                 contentValues.put("Cart", _Cart);
                 contentValues.put("Canoe", _Canoe);
                 contentValues.put("Bus", _Bus);
                 contentValues.put("Tractor", _Tractor);
                 contentValues.put("Plow", _Plow);
                 contentValues.put("Duck", _Duck);
                 contentValues.put("Cow", _Cow);
                 contentValues.put("Sheep", _Sheep);
                 contentValues.put("Goat", _Goat);
                 contentValues.put("Chicken", _Chicken);
                 contentValues.put("Donkey", _Donkey);
                 contentValues.put("Horse", _Horse);
                 contentValues.put("Pig", _Pig);
                 contentValues.put("OtherAsset", _OtherAsset);
                 contentValues.put("OtherAsset1", _OtherAsset1);
                 contentValues.put("OtherAsset2", _OtherAsset2);
                 contentValues.put("OtherAsset3", _OtherAsset3);
                 contentValues.put("SESNote", _SESNote);
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
                 contentValues.put("HHID", _HHID);
                 contentValues.put("SESNo", _SESNo);
                 contentValues.put("SESVDate", _SESVDate);
                 contentValues.put("SESVStatus", _SESVStatus);
                 contentValues.put("SESVStatusOth", _SESVStatusOth);
                 contentValues.put("Rnd", _Rnd);
                 contentValues.put("WSDrink", _WSDrink);
                 contentValues.put("WSDrinkOth", _WSDrinkOth);
                 contentValues.put("Toilet", _Toilet);
                 contentValues.put("ToiletOth", _ToiletOth);
                 contentValues.put("ToiletShrd", _ToiletShrd);
                 contentValues.put("ToiletUseNo", _ToiletUseNo);
                 contentValues.put("ToiletUseNoDk", _ToiletUseNoDk);
                 contentValues.put("ToiletLoc", _ToiletLoc);
                 contentValues.put("CookDvc", _CookDvc);
                 contentValues.put("CookDvcOth", _CookDvcOth);
                 contentValues.put("CookFuel", _CookFuel);
                 contentValues.put("CookFuelOth", _CookFuelOth);
                 contentValues.put("CookPlc", _CookPlc);
                 contentValues.put("CookPlcOth", _CookPlcOth);
                 contentValues.put("Floor", _Floor);
                 contentValues.put("FloorOth", _FloorOth);
                 contentValues.put("Roof", _Roof);
                 contentValues.put("RoofOth", _RoofOth);
                 contentValues.put("Wall", _Wall);
                 contentValues.put("WallOth", _WallOth);
                 contentValues.put("RoomSleep", _RoomSleep);
                 contentValues.put("RoomSleepDk", _RoomSleepDk);
                 contentValues.put("HomesteadAny", _HomesteadAny);
                 contentValues.put("OthLand", _OthLand);
                 contentValues.put("Electricity", _Electricity);
                 contentValues.put("Heater", _Heater);
                 contentValues.put("AC", _AC);
                 contentValues.put("ElecFan", _ElecFan);
                 contentValues.put("Lantern", _Lantern);
                 contentValues.put("Lamp", _Lamp);
                 contentValues.put("Matt", _Matt);
                 contentValues.put("Bed", _Bed);
                 contentValues.put("Chair", _Chair);
                 contentValues.put("Sofa", _Sofa);
                 contentValues.put("Tables", _Tables);
                 contentValues.put("Watch", _Watch);
                 contentValues.put("WMachine", _WMachine);
                 contentValues.put("Iron", _Iron);
                 contentValues.put("Booth", _Booth);
                 contentValues.put("SMachine", _SMachine);
                 contentValues.put("Generator", _Generator);
                  contentValues.put("Mosquito", _Mosquito);
                 contentValues.put("Internet", _Internet);
                 contentValues.put("Satellite", _Satellite);
                 contentValues.put("Landline", _Landline);
                 contentValues.put("Cellphone", _Cellphone);
                 contentValues.put("TV", _TV);
                 contentValues.put("Radio", _Radio);
                 contentValues.put("DVD", _DVD);
                 contentValues.put("Video", _Video);
                 contentValues.put("Computer", _Computer);
                 contentValues.put("Cable", _Cable);
                 contentValues.put("Microwave", _Microwave);
                 contentValues.put("Geyser", _Geyser);
                 contentValues.put("Grill", _Grill);
                 contentValues.put("Fridge", _Fridge);
                 contentValues.put("DeepFreezer", _DeepFreezer);
                 contentValues.put("Stove", _Stove);
                  contentValues.put("Blender", _Blender);
                 contentValues.put("Bike", _Bike);
                 contentValues.put("Motorcycle", _Motorcycle);
                 contentValues.put("Car", _Car);
                 contentValues.put("Rickshaw", _Rickshaw);
                 contentValues.put("Cart", _Cart);
                 contentValues.put("Canoe", _Canoe);
                 contentValues.put("Bus", _Bus);
                 contentValues.put("Tractor", _Tractor);
                 contentValues.put("Plow", _Plow);
                 contentValues.put("Duck", _Duck);
                 contentValues.put("Cow", _Cow);
                 contentValues.put("Sheep", _Sheep);
                 contentValues.put("Goat", _Goat);
                 contentValues.put("Chicken", _Chicken);
                 contentValues.put("Donkey", _Donkey);
                 contentValues.put("Horse", _Horse);
                 contentValues.put("Pig", _Pig);
                 contentValues.put("OtherAsset", _OtherAsset);
                 contentValues.put("OtherAsset1", _OtherAsset1);
                 contentValues.put("OtherAsset2", _OtherAsset2);
                 contentValues.put("OtherAsset3", _OtherAsset3);
                 contentValues.put("SESNote", _SESNote);
                 contentValues.put("Upload", _Upload);
                 contentValues.put("modifyDate", _modifyDate);
                 C.UpdateData(TableName, "HHID,SESNo", (""+ _HHID +", "+ _SESNo +""), contentValues);
              }
              catch(Exception  e)
              {
                 response = e.getMessage();
              }
           return response;
        }

          int Count = 0;          private int _Count = 0;          public int getCount(){ return _Count; }
        @SuppressLint("Range")
        public List<SES_DataModel> SelectAll(Context context, String SQL)
        {
            Connection C = new Connection(context);
            List<SES_DataModel> data = new ArrayList<SES_DataModel>();
            SES_DataModel d = new SES_DataModel();
            Cursor cur = C.ReadData(SQL);

            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                Count += 1;
                d = new SES_DataModel();
                d._Count = Count;
                d._HHID = cur.getString(cur.getColumnIndex("HHID"));
                d._SESNo = cur.getString(cur.getColumnIndex("SESNo"));
                d._SESVDate = cur.getString(cur.getColumnIndex("SESVDate"));
                d._SESVStatus = cur.getString(cur.getColumnIndex("SESVStatus"));
                d._SESVStatusOth = cur.getString(cur.getColumnIndex("SESVStatusOth"));
                d._Rnd = cur.getString(cur.getColumnIndex("Rnd"));
                d._WSDrink = cur.getString(cur.getColumnIndex("WSDrink"));
                d._WSDrinkOth = cur.getString(cur.getColumnIndex("WSDrinkOth"));
                d._Toilet = cur.getString(cur.getColumnIndex("Toilet"));
                d._ToiletOth = cur.getString(cur.getColumnIndex("ToiletOth"));
                d._ToiletShrd = cur.getString(cur.getColumnIndex("ToiletShrd"));
                d._ToiletUseNo = cur.getString(cur.getColumnIndex("ToiletUseNo"));
                d._ToiletUseNoDk = cur.getString(cur.getColumnIndex("ToiletUseNoDk"));
                d._ToiletLoc = cur.getString(cur.getColumnIndex("ToiletLoc"));
                d._CookDvc = cur.getString(cur.getColumnIndex("CookDvc"));
                d._CookDvcOth = cur.getString(cur.getColumnIndex("CookDvcOth"));
                d._CookFuel = cur.getString(cur.getColumnIndex("CookFuel"));
                d._CookFuelOth = cur.getString(cur.getColumnIndex("CookFuelOth"));
                d._CookPlc = cur.getString(cur.getColumnIndex("CookPlc"));
                d._CookPlcOth = cur.getString(cur.getColumnIndex("CookPlcOth"));
                d._Floor = cur.getString(cur.getColumnIndex("Floor"));
                d._FloorOth = cur.getString(cur.getColumnIndex("FloorOth"));
                d._Roof = cur.getString(cur.getColumnIndex("Roof"));
                d._RoofOth = cur.getString(cur.getColumnIndex("RoofOth"));
                d._Wall = cur.getString(cur.getColumnIndex("Wall"));
                d._WallOth = cur.getString(cur.getColumnIndex("WallOth"));
                d._RoomSleep = cur.getString(cur.getColumnIndex("RoomSleep"));
                d._RoomSleepDk = cur.getString(cur.getColumnIndex("RoomSleepDk"));
                d._HomesteadAny = cur.getString(cur.getColumnIndex("HomesteadAny"));
                d._OthLand = cur.getString(cur.getColumnIndex("OthLand"));
                d._Electricity = cur.getString(cur.getColumnIndex("Electricity"));
                d._Heater = cur.getString(cur.getColumnIndex("Heater"));
                d._AC = cur.getString(cur.getColumnIndex("AC"));
                d._ElecFan = cur.getString(cur.getColumnIndex("ElecFan"));
                d._Lantern = cur.getString(cur.getColumnIndex("Lantern"));
                d._Lamp = cur.getString(cur.getColumnIndex("Lamp"));
                d._Matt = cur.getString(cur.getColumnIndex("Matt"));
                d._Bed = cur.getString(cur.getColumnIndex("Bed"));
                d._Chair = cur.getString(cur.getColumnIndex("Chair"));
                d._Sofa = cur.getString(cur.getColumnIndex("Sofa"));
                d._Tables = cur.getString(cur.getColumnIndex("Tables"));
                d._Watch = cur.getString(cur.getColumnIndex("Watch"));
                d._WMachine = cur.getString(cur.getColumnIndex("WMachine"));
                d._Iron = cur.getString(cur.getColumnIndex("Iron"));
                d._Booth = cur.getString(cur.getColumnIndex("Booth"));
                d._SMachine = cur.getString(cur.getColumnIndex("SMachine"));
                d._Generator = cur.getString(cur.getColumnIndex("Generator"));
                d._Mosquito = cur.getString(cur.getColumnIndex("Mosquito"));
                d._Internet = cur.getString(cur.getColumnIndex("Internet"));
                d._Satellite = cur.getString(cur.getColumnIndex("Satellite"));
                d._Landline = cur.getString(cur.getColumnIndex("Landline"));
                d._Cellphone = cur.getString(cur.getColumnIndex("Cellphone"));
                d._TV = cur.getString(cur.getColumnIndex("TV"));
                d._Radio = cur.getString(cur.getColumnIndex("Radio"));
                d._DVD = cur.getString(cur.getColumnIndex("DVD"));
                d._Video = cur.getString(cur.getColumnIndex("Video"));
                d._Computer = cur.getString(cur.getColumnIndex("Computer"));
                d._Cable = cur.getString(cur.getColumnIndex("Cable"));
                d._Microwave = cur.getString(cur.getColumnIndex("Microwave"));
                d._Geyser = cur.getString(cur.getColumnIndex("Geyser"));
                d._Grill = cur.getString(cur.getColumnIndex("Grill"));
                d._Fridge = cur.getString(cur.getColumnIndex("Fridge"));
                d._DeepFreezer = cur.getString(cur.getColumnIndex("DeepFreezer"));
                d._Stove = cur.getString(cur.getColumnIndex("Stove"));
                d._Blender = cur.getString(cur.getColumnIndex("Blender"));
                d._Bike = cur.getString(cur.getColumnIndex("Bike"));
                d._Motorcycle = cur.getString(cur.getColumnIndex("Motorcycle"));
                d._Car = cur.getString(cur.getColumnIndex("Car"));
                d._Rickshaw = cur.getString(cur.getColumnIndex("Rickshaw"));
                d._Cart = cur.getString(cur.getColumnIndex("Cart"));
                d._Canoe = cur.getString(cur.getColumnIndex("Canoe"));
                d._Bus = cur.getString(cur.getColumnIndex("Bus"));
                d._Tractor = cur.getString(cur.getColumnIndex("Tractor"));
                d._Plow = cur.getString(cur.getColumnIndex("Plow"));
                d._Duck = cur.getString(cur.getColumnIndex("Duck"));
                d._Cow = cur.getString(cur.getColumnIndex("Cow"));
                d._Sheep = cur.getString(cur.getColumnIndex("Sheep"));
                d._Goat = cur.getString(cur.getColumnIndex("Goat"));
                d._Chicken = cur.getString(cur.getColumnIndex("Chicken"));
                d._Donkey = cur.getString(cur.getColumnIndex("Donkey"));
                d._Horse = cur.getString(cur.getColumnIndex("Horse"));
                d._Pig = cur.getString(cur.getColumnIndex("Pig"));
                d._OtherAsset = cur.getString(cur.getColumnIndex("OtherAsset"));
                d._OtherAsset1 = cur.getString(cur.getColumnIndex("OtherAsset1"));
                d._OtherAsset2 = cur.getString(cur.getColumnIndex("OtherAsset2"));
                d._OtherAsset3 = cur.getString(cur.getColumnIndex("OtherAsset3"));
                d._SESNote = cur.getString(cur.getColumnIndex("SESNote"));
                data.add(d);

                cur.moveToNext();
            }
            cur.close();
          return data;
        }

     private String _ses_dur = "";
     public String getses_dur(){
         return _ses_dur;
     }
     @SuppressLint("Range")
     public List<SES_DataModel> SelectAll_List(Context context, String SQL)
     {
         Connection C = new Connection(context);
         List<SES_DataModel> data = new ArrayList<SES_DataModel>();
         SES_DataModel d = new SES_DataModel();
         Cursor cur = C.ReadData(SQL);

         cur.moveToFirst();
         while(!cur.isAfterLast())
         {
             Count += 1;
             d = new SES_DataModel();
             d._Count = Count;
             d._HHID = cur.getString(cur.getColumnIndex("HHID"));
             d._SESNo = cur.getString(cur.getColumnIndex("SESNo"));
             d._SESVDate = cur.getString(cur.getColumnIndex("SESVDate"));
             if(cur.getString(cur.getColumnIndex("SESVStatus")).equals("1")){
                 d._SESVStatus = "Completed";
             }else if(cur.getString(cur.getColumnIndex("SESVStatus")).equals("2")){
                 d._SESVStatus = "Partially Completed";
             }else if(cur.getString(cur.getColumnIndex("SESVStatus")).equals("3")){
                 d._SESVStatus = "Refused";
             }else{
                 d._SESVStatus = cur.getString(cur.getColumnIndex("SESVStatusOth"));
             }

             d._ses_dur = cur.getString(cur.getColumnIndex("ses_dur"));

             data.add(d);

             cur.moveToNext();
         }
         cur.close();
         return data;
     }
 }