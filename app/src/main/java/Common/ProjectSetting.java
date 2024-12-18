package Common;

import android.os.Environment;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanvirHossain on 19/07/2016.
 * Last Update on 28 Jul 2022
 */
public class ProjectSetting {

    /*//Project and Server link Information
    //==============================================================================================
    public static String ProjectName    = "champs_lc_dss";
    public static String ServerURL      = "http://mchdweb.icddrb.org/";

    //Database Location
    //==============================================================================================
    public static String DatabaseName   = ProjectName.toUpperCase() +"Database.db";
    public static String DatabaseFolder = ProjectName.toUpperCase() +"DB";
    public static final String Database_Folder_URL = Environment.getExternalStorageDirectory() + File.separator + ProjectSetting.DatabaseFolder;
    //Internal Memory
    //public static final String Database_Location = DatabaseName;

    //External Storage
    public static final String Database_Location = Database_Folder_URL + File.separator + DatabaseName;*/

    //Login User type Holder
    //==============================================================================================
    //public static String UserRoleHolder = "";
    //Global Variable
    //==============================================================================================
    public static String LISTING   = "1";
    public static String LISTING_NAME   = "LISTING";
    public static String BASELINE   = "2";
    public static String BASELINE_NAME   = "BASELINE";
    public static String SURVEILLANCE   = "3";
    public static String SURVEILLANCE_NAME   = "SURVEILLANCE";

    //Demo
    //----------------------------------------------------------------------------------------------
    public static String DEMO   = "1";
    public static String DEMO_NAME   = "Demo Country";
    public static String DEMO_SITE1   = "21";
    public static String DEMO_SITE1_NAME   = "Demo Site";
    public static String DEMO_SITE1_GEO_LAYER1   = "Location";
    public static String DEMO_SITE1_GEO_LAYER2   = "Sub-Location";
    public static String DEMO_SITE1_GEO_LAYER3   = "Enumeration Area";

    //Bangladesh
    //----------------------------------------------------------------------------------------------
    public static String BANGLADESH   = "1";
    public static String BANGLADESH_NAME   = "Bangladesh";
    public static String BANGLADESH_SITE1   = "11";
    public static String BANGLADESH_SITE1_NAME   = "Baliakandi";
    public static String BANGLADESH_SITE1_GEO_LAYER1   = "Upazila";
    public static String BANGLADESH_SITE1_GEO_LAYER2   = "Unions";
    public static String BANGLADESH_SITE1_GEO_LAYER3   = "Village";

    //Nigeria-Cross River
    //----------------------------------------------------------------------------------------------
    public static String NIGERIA   = "2";
    public static String NIGERIA_NAME   = "Nigeria";
    public static String NIGERIA_SITE1   = "21";
    public static String NIGERIA_SITE1_NAME   = "Cross River";
    public static String NIGERIA_SITE1_GEO_LAYER1   = "Local Govt.";
    public static String NIGERIA_SITE1_GEO_LAYER2   = "Ward";
    public static String NIGERIA_SITE1_GEO_LAYER3   = "Enumeration Area"; //Village Table
    public static String NIGERIA_SITE1_GEO_LAYER4   = "State";

    public static String NIGERIA_SITE1_MIG_LAYER1   = "Region/District";
    public static String NIGERIA_SITE1_MIG_LAYER2   = "Commune";
    public static String NIGERIA_SITE1_MIG_LAYER3   = "Quartier";

    //Nigeria-Bauchi
    //----------------------------------------------------------------------------------------------
    public static String NIGERIA_SITE2   = "22";
    public static String NIGERIA_SITE2_NAME   = "Bauchi";
    public static String NIGERIA_SITE2_GEO_LAYER1   = "Local Govt.";
    public static String NIGERIA_SITE2_GEO_LAYER2   = "Ward";
    public static String NIGERIA_SITE2_GEO_LAYER3   = "Enumeration Area";

    public static String NIGERIA_SITE2_MIG_LAYER1   = "Region/District";
    public static String NIGERIA_SITE2_MIG_LAYER2   = "Commune";
    public static String NIGERIA_SITE2_MIG_LAYER3   = "Quartier";

    //Sierra Leone-Makeni City
    //----------------------------------------------------------------------------------------------
    public static String SIERRA_LEONE   = "3";
    public static String SIERRA_LEONE_NAME   = "Sierra Leone";
    public static String SIERRA_LEONE_SITE1   = "31";
    public static String SIERRA_LEONE_SITE1_NAME   = "";
    public static String SIERRA_SITE1_GEO_LAYER1   = "Chiefdom";
    public static String SIERRA_SITE1_GEO_LAYER2   = "Section Name";
    public static String SIERRA_SITE1_GEO_LAYER3   = "Enumeration Area";

    public static String SIERRA_SITE1_MIG_LAYER1   = "Region/District";
    public static String SIERRA_SITE1_MIG_LAYER2   = "Commune";
    public static String SIERRA_SITE1_MIG_LAYER3   = "Quartier";

    //Mali-Bamako
    //----------------------------------------------------------------------------------------------
    public static String MALI   = "4";
    public static String MALI_NAME   = "Mali";
    public static String MALI_SITE1   = "41";
    public static String MALI_SITE1_NAME   = "Bamako";
    public static String MALI_SITE1_GEO_LAYER1   = "Quartier";
    public static String MALI_SITE1_GEO_LAYER2   = "Sous Quartier";
    public static String MALI_SITE1_GEO_LAYER3   = "SE";

    public static String MALI_SITE1_MIG_LAYER1   = "Region/District";
    public static String MALI_SITE1_MIG_LAYER2   = "Commune";
    public static String MALI_SITE1_MIG_LAYER3   = "Quartier";

    //----------------------------------------------------------------------------------------------
    public static String YES   = "1";
    public static String NO   = "2";

    public static String TRAINING   = "0";
    public static String MAIN_DATA_COLLECTION   = "1";
    public static String DEVELOPMENT   = "2";

    //==============================================================================================
    //Development
    //==============================================================================================
    /*public static String COUNTRY_CODE   = BANGLADESH;
    public static String COUNTRY_NAME   = BANGLADESH_NAME;
    public static String SITE_CODE      = BANGLADESH_SITE1;
    public static String SITE_NAME      = BANGLADESH_SITE1_NAME;
    public static String LANDMARK_COLLECT     = YES;
    public static boolean Tab_Database_Upload = true;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE = SURVEILLANCE;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME = SURVEILLANCE_NAME;
    public static String DATABASE_FOLDER_NAME = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME;
    public static String DSS_SYSTEM_FOR  = DEVELOPMENT;
    public static String COMPOUND_LABEL  = "Bari";
    public static String HOUSEHOLD_LABEL = "Household";
    public static String MEMBER_LABEL    = "Member";
    public static int REPRODUCTIVE_AGE_START   = 15;
    public static int REPRODUCTIVE_AGE_END     = 49;
    public static int SES_DURATION_MONTHS      = 36;
    public static int ELIGIBILITY_AGE_MS_YEAR  = 14;
    public static int ELIGIBILITY_AGE_EDU_YEAR = 3;
    public static int ELIGIBILITY_AGE_OCP_YEAR = 18;
    public static int ELIGIBILITY_AGE_EMP_YEAR = 18;
    public static int ELIGIBILITY_HOUSEHOLD_HEAD_AGE = 18;

    //Modules
    public static boolean PREGNANCY_HISTORY_MODULE = true;
    public static boolean DEATH_NOTIFICATION_MODULE = true;
    public static boolean CHILD_CHARACTERISTICS_MODULE = true;
    public static boolean ANTHROPOMETRIC_MODULE = true;
    public static boolean CAREGIVER_MODULE = true;
    public static boolean VACCINATION_MODULE = true;
    public static boolean NEW_BIRTH_MODULE = true;
    public static boolean MORBIDITY_MODULE = true;

    public static String ServerURL       = "https://mchdweb.icddrb.org/";
    public static String apiName         = "champs_hdss_" + SITE_CODE;
    public static String VersionDate     = "20092024"; //New Release, Format: DDMMYYYY*/

    //==============================================================================================
    //Demo Site for Demonstration
    //==============================================================================================
//    public static String COUNTRY_CODE   = DEMO;
//    public static String COUNTRY_NAME   = DEMO_NAME;
//    public static String SITE_CODE      = DEMO_SITE1;
//    public static String SITE_NAME      = DEMO_SITE1_NAME;
//    public static String LANDMARK_COLLECT     = YES;
//    public static boolean Tab_Database_Upload = true;
//    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE = SURVEILLANCE;
//    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME = SURVEILLANCE_NAME;
//    public static String DATABASE_FOLDER_NAME = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME;
//    public static String DSS_SYSTEM_FOR  = DEVELOPMENT;
//    public static String COMPOUND_LABEL  = "Compound";
//    public static String HOUSEHOLD_LABEL = "Household";
//    public static String MEMBER_LABEL    = "Member";
//    public static int REPRODUCTIVE_AGE_START   = 15;
//    public static int REPRODUCTIVE_AGE_END     = 49;
//    public static int SES_DURATION_MONTHS      = 36;
//    public static int ELIGIBILITY_AGE_MS_YEAR  = 14;
//    public static int ELIGIBILITY_AGE_EDU_YEAR = 3;
//    public static int ELIGIBILITY_AGE_OCP_YEAR = 18;
//    public static int ELIGIBILITY_AGE_EMP_YEAR = 18;
//    public static int ELIGIBILITY_HOUSEHOLD_HEAD_AGE = 18;
//
//    //Modules
//    public static boolean PREGNANCY_HISTORY_MODULE = true;
//    public static boolean DEATH_NOTIFICATION_MODULE = true;
//    public static boolean CHILD_CHARACTERISTICS_MODULE = true;
//    public static boolean ANTHROPOMETRIC_MODULE = true;
//    public static boolean CAREGIVER_MODULE = true;
//    public static boolean VACCINATION_MODULE = true;
//    public static boolean NEW_BIRTH_MODULE = true;
//    public static boolean MORBIDITY_MODULE = true;
//
//    public static String ServerURL       = "https://champshdsslc.org/";
//    public static String apiName         = "champs_hdss_11_demo";
//    public static String VersionDate     = "12112024"; //New Release, Format: DDMMYYYY

    //==============================================================================================
    //Nigeria - Cross River
    //Listing Main Data Collection Start: 23 Oct 2023
    //==============================================================================================
    /*public static String COUNTRY_CODE   = NIGERIA;
    public static String COUNTRY_NAME   = NIGERIA_NAME;
    public static String SITE_CODE      = NIGERIA_SITE1;
    public static String SITE_NAME      = NIGERIA_SITE1_NAME;
    public static String LANDMARK_COLLECT     = YES;
    public static boolean Tab_Database_Upload = true;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE = LISTING;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME = LISTING_NAME;
    public static String DATABASE_FOLDER_NAME = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME;
    public static String DSS_SYSTEM_FOR  = MAIN_DATA_COLLECTION;
    public static String COMPOUND_LABEL  = "House";
    public static String HOUSEHOLD_LABEL = "Household";
    public static String MEMBER_LABEL    = "Individual";
    public static int REPRODUCTIVE_AGE_START   = 15;
    public static int REPRODUCTIVE_AGE_END     = 49;
    public static int SES_DURATION_MONTHS      = 36;
    public static int ELIGIBILITY_AGE_MS_YEAR  = 14;
    public static int ELIGIBILITY_AGE_EDU_YEAR = 3;
    public static int ELIGIBILITY_AGE_OCP_YEAR = 18;
    public static int ELIGIBILITY_AGE_EMP_YEAR = 18;
    public static int ELIGIBILITY_HOUSEHOLD_HEAD_AGE = 18;

    //Modules
    public static boolean PREGNANCY_HISTORY_MODULE = true;
    public static boolean DEATH_NOTIFICATION_MODULE = true;
    public static boolean CHILD_CHARACTERISTICS_MODULE = true;
    public static boolean ANTHROPOMETRIC_MODULE = true;
    public static boolean CAREGIVER_MODULE = true;
    public static boolean VACCINATION_MODULE = true;
    public static boolean NEW_BIRTH_MODULE = true;
    public static boolean MORBIDITY_MODULE = true;

    public static String ServerURL       = "https://champshdsslc.org/";
    public static String apiName         = "champs_hdss_" + SITE_CODE;
    public static String VersionDate     = "02112023"; //New Release, Format: DDMMYYYY*/

    //Cross River: Surveillance for Training
    //----------------------------------------------------------------------------------------------
    /*public static String COUNTRY_CODE   = NIGERIA;
    public static String COUNTRY_NAME   = NIGERIA_NAME;
    public static String SITE_CODE      = NIGERIA_SITE1;
    public static String SITE_NAME      = NIGERIA_SITE1_NAME;
    public static String LANDMARK_COLLECT     = YES;
    public static boolean Tab_Database_Upload = false;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE = SURVEILLANCE;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME = SURVEILLANCE_NAME;
    public static String DATABASE_FOLDER_NAME = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME;
    public static String DSS_SYSTEM_FOR  = DEVELOPMENT;
    public static String COMPOUND_LABEL  = "House";
    public static String HOUSEHOLD_LABEL = "Household";
    public static String MEMBER_LABEL    = "Individual";
    public static int REPRODUCTIVE_AGE_START   = 15;
    public static int REPRODUCTIVE_AGE_END     = 49;
    public static int SES_DURATION_MONTHS      = 36;
    public static int ELIGIBILITY_AGE_MS_YEAR  = 14;
    public static int ELIGIBILITY_AGE_EDU_YEAR = 3;
    public static int ELIGIBILITY_AGE_OCP_YEAR = 18;
    public static int ELIGIBILITY_AGE_EMP_YEAR = 18;
    public static int ELIGIBILITY_HOUSEHOLD_HEAD_AGE = 18;

    //Modules
    public static boolean PREGNANCY_HISTORY_MODULE = true;
    public static boolean DEATH_NOTIFICATION_MODULE = true;
    public static boolean CHILD_CHARACTERISTICS_MODULE = true;
    public static boolean ANTHROPOMETRIC_MODULE = true;
    public static boolean CAREGIVER_MODULE = true;
    public static boolean VACCINATION_MODULE = true;
    public static boolean NEW_BIRTH_MODULE = true;
    public static boolean MORBIDITY_MODULE = true;

    public static String ServerURL       = "https://champshdsslc.org/";
    public static String apiName         = "champs_hdss_" + SITE_CODE +"_surveillance_training";
    public static String VersionDate     = "30092024"; //New Release, Format: DDMMYYYY*/

    //Surveillance for Production
    //----------------------------------------------------------------------------------------------
    /*public static String COUNTRY_CODE   = NIGERIA;
    public static String COUNTRY_NAME   = NIGERIA_NAME;
    public static String SITE_CODE      = NIGERIA_SITE1;
    public static String SITE_NAME      = NIGERIA_SITE1_NAME;
    public static String LANDMARK_COLLECT     = YES;
    public static boolean Tab_Database_Upload = true;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE = SURVEILLANCE;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME = SURVEILLANCE_NAME;
    public static String DATABASE_FOLDER_NAME = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME;
    public static String DSS_SYSTEM_FOR  = MAIN_DATA_COLLECTION;
    public static String COMPOUND_LABEL  = "House";
    public static String HOUSEHOLD_LABEL = "Household";
    public static String MEMBER_LABEL    = "Individual";
    public static int REPRODUCTIVE_AGE_START   = 15;
    public static int REPRODUCTIVE_AGE_END     = 49;
    public static int SES_DURATION_MONTHS      = 36;
    public static int ELIGIBILITY_AGE_MS_YEAR  = 14;
    public static int ELIGIBILITY_AGE_EDU_YEAR = 3;
    public static int ELIGIBILITY_AGE_OCP_YEAR = 18;
    public static int ELIGIBILITY_AGE_EMP_YEAR = 18;
    public static int ELIGIBILITY_HOUSEHOLD_HEAD_AGE = 18;

    //Modules
    public static boolean PREGNANCY_HISTORY_MODULE = true;
    public static boolean DEATH_NOTIFICATION_MODULE = true;
    public static boolean CHILD_CHARACTERISTICS_MODULE = true;

    public static boolean ANTHROPOMETRIC_MODULE = true;
    public static boolean CAREGIVER_MODULE = true;
    public static boolean VACCINATION_MODULE = true;
    public static boolean NEW_BIRTH_MODULE = true;
    public static boolean MORBIDITY_MODULE = true;

    public static String ServerURL       = "https://champshdsslc.org/";
    public static String apiName         = "champs_hdss_" + SITE_CODE +"_surveillance_production";

    public static String VersionDate     = "14112024"; //New Release, Format: DDMMYYYY*/



    //==============================================================================================
    //Nigeria - Bauchi
    //==============================================================================================
    /*public static String COUNTRY_CODE   = NIGERIA;
    public static String COUNTRY_NAME   = NIGERIA_NAME;
    public static String SITE_CODE      = NIGERIA_SITE2;
    public static String SITE_NAME      = NIGERIA_SITE2_NAME;
    public static String LANDMARK_COLLECT     = YES;
    public static boolean Tab_Database_Upload = true;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE = BASELINE;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME = BASELINE_NAME;
    public static String DATABASE_FOLDER_NAME = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME;
    public static String DSS_SYSTEM_FOR  = MAIN_DATA_COLLECTION;
    public static String COMPOUND_LABEL  = "Structure";
    public static String HOUSEHOLD_LABEL = "Household";
    public static String MEMBER_LABEL    = "Member";
    public static int REPRODUCTIVE_AGE_START   = 13;
    public static int REPRODUCTIVE_AGE_END     = 49;
    public static int SES_DURATION_MONTHS      = 36;
    public static int ELIGIBILITY_AGE_MS_YEAR  = 14;
    public static int ELIGIBILITY_AGE_EDU_YEAR = 3;
    public static int ELIGIBILITY_AGE_OCP_YEAR = 18;
    public static int ELIGIBILITY_AGE_EMP_YEAR = 18;
    public static int ELIGIBILITY_HOUSEHOLD_HEAD_AGE = 18;

    //Modules
    public static boolean PREGNANCY_HISTORY_MODULE = true;
    public static boolean DEATH_NOTIFICATION_MODULE = true;
    public static boolean CHILD_CHARACTERISTICS_MODULE = true;
    public static boolean ANTHROPOMETRIC_MODULE = true;
    public static boolean CAREGIVER_MODULE = true;

    public static boolean VACCINATION_MODULE = true;
    public static boolean NEW_BIRTH_MODULE = true;
    public static boolean MORBIDITY_MODULE = true;

    public static String ServerURL       = "https://champshdsslc.org/";
    public static String apiName         = "champs_hdss_" + SITE_CODE;
    public static String VersionDate     = "30092024"; //New Release, Format: DDMMYYYY*/

    //===========================
    //Training: Listing/Baseline
    //===========================
   /* public static String COUNTRY_CODE   = NIGERIA;
    public static String COUNTRY_NAME   = NIGERIA_NAME;
    public static String SITE_CODE      = NIGERIA_SITE2;
    public static String SITE_NAME      = NIGERIA_SITE2_NAME;
    public static String LANDMARK_COLLECT     = YES;
    public static boolean Tab_Database_Upload = true;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE = SURVEILLANCE;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME = SURVEILLANCE_NAME;
    public static String DATABASE_FOLDER_NAME = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME;
    public static String DSS_SYSTEM_FOR  = TRAINING;
    public static String COMPOUND_LABEL  = "Structure";
    public static String HOUSEHOLD_LABEL = "Household";
    public static String MEMBER_LABEL    = "Member";
    public static int REPRODUCTIVE_AGE_START   = 15;
    public static int REPRODUCTIVE_AGE_END     = 49;
    public static int SES_DURATION_MONTHS      = 36;
    public static int ELIGIBILITY_AGE_MS_YEAR  = 14;
    public static int ELIGIBILITY_AGE_EDU_YEAR = 3;
    public static int ELIGIBILITY_AGE_OCP_YEAR = 18;
    public static int ELIGIBILITY_AGE_EMP_YEAR = 18;
    public static int ELIGIBILITY_HOUSEHOLD_HEAD_AGE = 18;

    //Modules
    public static boolean PREGNANCY_HISTORY_MODULE = true;
    public static boolean DEATH_NOTIFICATION_MODULE = true;
    public static boolean CHILD_CHARACTERISTICS_MODULE = true;
    public static boolean ANTHROPOMETRIC_MODULE = true;
    public static boolean CAREGIVER_MODULE = true;
    public static boolean VACCINATION_MODULE = true;
    public static boolean NEW_BIRTH_MODULE = true;
    public static boolean MORBIDITY_MODULE = true;

    public static String ServerURL       = "https://champshdsslc.org/";
    public static String apiName         = "champs_hdss_" + SITE_CODE + "_Training";
    public static String VersionDate     = "27122023"; //New Release, Format: DDMMYYYY*/

    //==============================================================================================
    //Sierra Leone
    //==============================================================================================
    //Development
    //----------------------------------------------------------------------------------------------
   /* public static String COUNTRY_CODE   = SIERRA_LEONE;
    public static String COUNTRY_NAME   = SIERRA_LEONE_NAME;
    public static String SITE_CODE      = SIERRA_LEONE_SITE1;
    public static String SITE_NAME      = SIERRA_LEONE_SITE1_NAME;
    public static String LANDMARK_COLLECT     = YES;
    public static boolean Tab_Database_Upload = false;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE = SURVEILLANCE;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME = SURVEILLANCE_NAME;
    public static String DATABASE_FOLDER_NAME = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME;
    public static String DSS_SYSTEM_FOR  = DEVELOPMENT;  //Production
    public static String COMPOUND_LABEL  = "Structure";
    public static String HOUSEHOLD_LABEL = "Household";
    public static String MEMBER_LABEL    = "Member";
    public static int REPRODUCTIVE_AGE_START   = 13;
    public static int REPRODUCTIVE_AGE_END     = 49;
    public static int SES_DURATION_MONTHS      = 36;
    public static int ELIGIBILITY_AGE_MS_YEAR  = 12;
    public static int ELIGIBILITY_AGE_EDU_YEAR = 3;
    public static int ELIGIBILITY_AGE_OCP_YEAR = 18;
    public static int ELIGIBILITY_AGE_EMP_YEAR = 18;
    public static int ELIGIBILITY_HOUSEHOLD_HEAD_AGE = 15;

    //Modules
    public static boolean PREGNANCY_HISTORY_MODULE = true;
    public static boolean DEATH_NOTIFICATION_MODULE = true;
    public static boolean CHILD_CHARACTERISTICS_MODULE = true;
    public static boolean ANTHROPOMETRIC_MODULE = true;
    public static boolean CAREGIVER_MODULE = true;
    public static boolean VACCINATION_MODULE = true;
    public static boolean NEW_BIRTH_MODULE = true;
    public static boolean MORBIDITY_MODULE = true;
    public static String ServerURL       = "https://champshdsslc.org/"; //CHAMPS LC Cloud Server
    public static String apiName         = "champs_hdss_" + SITE_CODE;
    public static String VersionDate     = "04082024"; //New Release, Format: DDMMYYYY*/

    //Production
    //----------------------------------------------------------------------------------------------
    public static String COUNTRY_CODE   = SIERRA_LEONE;
    public static String COUNTRY_NAME   = SIERRA_LEONE_NAME;
    public static String SITE_CODE      = SIERRA_LEONE_SITE1;
    public static String SITE_NAME      = SIERRA_LEONE_SITE1_NAME;
    public static String LANDMARK_COLLECT     = YES;
    public static boolean Tab_Database_Upload = false;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE = SURVEILLANCE;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME = SURVEILLANCE_NAME;
    public static String DATABASE_FOLDER_NAME = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME;
    public static String DSS_SYSTEM_FOR  = MAIN_DATA_COLLECTION;  //Production
    public static String COMPOUND_LABEL  = "Structure";
    public static String HOUSEHOLD_LABEL = "Household";
    public static String MEMBER_LABEL    = "Member";
    public static int REPRODUCTIVE_AGE_START   = 13;
    public static int REPRODUCTIVE_AGE_END     = 49;
    public static int SES_DURATION_MONTHS      = 36;
    public static int ELIGIBILITY_AGE_MS_YEAR  = 12;
    public static int ELIGIBILITY_AGE_EDU_YEAR = 3;
    public static int ELIGIBILITY_AGE_OCP_YEAR = 18;
    public static int ELIGIBILITY_AGE_EMP_YEAR = 18;
    public static int ELIGIBILITY_HOUSEHOLD_HEAD_AGE = 15;

    //Modules
    public static boolean PREGNANCY_HISTORY_MODULE = true;
    public static boolean DEATH_NOTIFICATION_MODULE = true;
    public static boolean CHILD_CHARACTERISTICS_MODULE = true;
    public static boolean ANTHROPOMETRIC_MODULE = true;
    public static boolean CAREGIVER_MODULE = true;
    public static boolean VACCINATION_MODULE = true;
    public static boolean NEW_BIRTH_MODULE = true;
    public static boolean MORBIDITY_MODULE = true;
    public static String ServerURL       = "http://13.43.170.74//"; //Sierra Leone Cloud Server
    public static String apiName         = "champs_hdss_" + SITE_CODE;

    public static String VersionDate     = "10122024"; //New Release, Format: DDMMYYYY


    //==============================================================================================
    //Mali
    //==============================================================================================

    //Development
    //----------------------------------------------------------------------------------------------
    /*public static String COUNTRY_CODE   = MALI;
    public static String COUNTRY_NAME   = MALI_NAME;
    public static String SITE_CODE      = MALI_SITE1;
    public static String SITE_NAME      = MALI_SITE1_NAME;
    public static String LANDMARK_COLLECT     = YES;
    public static boolean Tab_Database_Upload = false;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE = SURVEILLANCE;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME = SURVEILLANCE_NAME;
    public static String DATABASE_FOLDER_NAME = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME;
    public static String DSS_SYSTEM_FOR  = DEVELOPMENT;
    public static String COMPOUND_LABEL  = "Concession";
    public static String HOUSEHOLD_LABEL = "Household";
    public static String MEMBER_LABEL    = "Individual";
    public static int REPRODUCTIVE_AGE_START   = 11;
    public static int REPRODUCTIVE_AGE_END     = 49;
    public static int SES_DURATION_MONTHS      = 60;
    public static int ELIGIBILITY_AGE_MS_YEAR  = 14;
    public static int ELIGIBILITY_AGE_EDU_YEAR = 5;
    public static int ELIGIBILITY_AGE_OCP_YEAR = 18;
    public static int ELIGIBILITY_AGE_EMP_YEAR = 18;
    public static int ELIGIBILITY_HOUSEHOLD_HEAD_AGE = 18;

    //Modules
    public static boolean PREGNANCY_HISTORY_MODULE = true;
    public static boolean DEATH_NOTIFICATION_MODULE = false;
    public static boolean CHILD_CHARACTERISTICS_MODULE = false;
    public static boolean ANTHROPOMETRIC_MODULE = true;
    public static boolean CAREGIVER_MODULE = false;
    public static boolean VACCINATION_MODULE = true;
    public static boolean NEW_BIRTH_MODULE = false;
    public static boolean MORBIDITY_MODULE = false;

    public static String ServerURL       = "https://champshdsslc.org/";
    public static String apiName         = "champs_hdss_" + SITE_CODE + "_development";
    public static String VersionDate     = "14052024"; //New Release, Format: DDMMYYYY*/

    //Production
    //----------------------------------------------------------------------------------------------
    /*public static String COUNTRY_CODE   = MALI;
    public static String COUNTRY_NAME   = MALI_NAME;
    public static String SITE_CODE      = MALI_SITE1;
    public static String SITE_NAME      = MALI_SITE1_NAME;
    public static String LANDMARK_COLLECT     = YES;
    public static boolean Tab_Database_Upload = true;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE = SURVEILLANCE;
    public static String LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME = SURVEILLANCE_NAME;
    public static String DATABASE_FOLDER_NAME = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME;
    public static String DSS_SYSTEM_FOR  = MAIN_DATA_COLLECTION; //Production
    public static String COMPOUND_LABEL  = "Concession";
    public static String HOUSEHOLD_LABEL = "Household";
    public static String MEMBER_LABEL    = "Individual";
    public static int REPRODUCTIVE_AGE_START   = 11;
    public static int REPRODUCTIVE_AGE_END     = 49;
    public static int SES_DURATION_MONTHS      = 60;
    public static int ELIGIBILITY_AGE_MS_YEAR  = 14;
    public static int ELIGIBILITY_AGE_EDU_YEAR = 5;
    public static int ELIGIBILITY_AGE_OCP_YEAR = 18;
    public static int ELIGIBILITY_AGE_EMP_YEAR = 18;
    public static int ELIGIBILITY_HOUSEHOLD_HEAD_AGE = 18;

    //Modules
    public static boolean PREGNANCY_HISTORY_MODULE = true;
    public static boolean DEATH_NOTIFICATION_MODULE = false;
    public static boolean CHILD_CHARACTERISTICS_MODULE = false;
    public static boolean ANTHROPOMETRIC_MODULE = true;
    public static boolean CAREGIVER_MODULE = false;
    public static boolean VACCINATION_MODULE = true;
    public static boolean NEW_BIRTH_MODULE = false;
    public static boolean MORBIDITY_MODULE = false;

    public static String ServerURL       = "https://champshdsslc.org/";
    public static String apiName         = "champs_hdss_" + SITE_CODE + "_production";
    public static String VersionDate     = "20102024"; //New Release, Format: DDMMYYYY*/


    //Project and Server link Information
    //==============================================================================================
    public static String ProjectName    = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE;
    public static String DatabaseName   = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME +"Database.db";
    //public static String DatabaseFolder = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME;
    public static String DatabaseFolder = DATABASE_FOLDER_NAME;
    public static final String Database_Folder_URL = Environment.getExternalStorageDirectory() + File.separator + DATABASE_FOLDER_NAME;


    //External Storage
    public static final String Database_Location = Database_Folder_URL + File.separator + DatabaseName;


    public static String Get_WorkGroup(){
        String WorkGroup = "";
        if(ProjectSetting.LISTING_OR_BASELINE_OR_SURVEILLANCE.equals(ProjectSetting.LISTING)){
            WorkGroup = "1";
        }else if(ProjectSetting.LISTING_OR_BASELINE_OR_SURVEILLANCE.equals(ProjectSetting.BASELINE)){
            WorkGroup = "2";
        }else if(ProjectSetting.LISTING_OR_BASELINE_OR_SURVEILLANCE.equals(ProjectSetting.SURVEILLANCE)){
            WorkGroup = "3";
        }
        return  WorkGroup;
    }

    //==============================================================================================
    // Data Sync
    //==============================================================================================


    //Listing
    //----------------------------------------------------------------------------------------------
    public static List<String> Listing_Upload(){
        List<String> tableList   = new ArrayList<String>();
        tableList.add("Compound");
        tableList.add("Household");
        tableList.add("GPS");
        tableList.add("Visits");
        tableList.add("Member");
        tableList.add("MemberMove");

        return tableList;
    }


    //Baseline
    //----------------------------------------------------------------------------------------------
    public static List<String> Baseline_Upload(){
        List<String> tableList   = new ArrayList<String>();
        tableList.add("Compound");
        tableList.add("Household");
        tableList.add("SES");
        tableList.add("tmpSES");
        tableList.add("Visits");
        tableList.add("Member");
        tableList.add("MemberMove");
        tableList.add("GPS");
        tableList.add("PregHis");
        tableList.add("DeathNotifi");
        tableList.add("Vaccination");
        tableList.add("VaccinationListMaster");
        tableList.add("Climate_Change");
        tableList.add("Caregiver");
        tableList.add("ChildChar");

        tableList.add("NBC_ANC");
        tableList.add("NBC_ANCDetail");
        tableList.add("NBC_Morbidity");
        tableList.add("NBC_NBCare");
        tableList.add("NBC_NBCareDetail");
        tableList.add("NBC_PNCMoth");
        tableList.add("NBC_PNCMothDetail");
        tableList.add("NBC_PNCNB");
        tableList.add("NBC_PNCNBDetail");

        tableList.add("CONTRACEPTIVE_BEHAVIOUR");
        tableList.add("Anthropometric");
        return tableList;
    }

    //Surveillance
    //----------------------------------------------------------------------------------------------
    public static List<String> Surveillance_Upload(){
        List<String> tableList   = new ArrayList<String>();
        tableList.add("Compound");
        tableList.add("Household");
        tableList.add("GPS");
        tableList.add("SES");
        tableList.add("SES_Mali");
        tableList.add("SES_CrossRiver");
        tableList.add("DeathNotifi");
        tableList.add("Death");
        tableList.add("Visits");
        tableList.add("Member");
        tableList.add("MemberMove");
        tableList.add("PregHis");
        tableList.add("Caregiver");
        tableList.add("ChildChar");

        tableList.add("MaritalSts");
        tableList.add("NotPregnant");
        tableList.add("PregDetail");
        tableList.add("Pregnancy");
        tableList.add("Delivery");
        tableList.add("LiveBirth");
        tableList.add("StillBirth");
        tableList.add("Abortion");
        tableList.add("Migration");
        tableList.add("Movement");

        tableList.add("TemporaryMigrationIn");
        tableList.add("TemporaryMigrationOut");

        tableList.add("SpouseSerial");
        tableList.add("FatherSerial");
        tableList.add("MotherSerial");
        tableList.add("Education");
        tableList.add("Occupation");
        tableList.add("Relation");
        tableList.add("Death");
        tableList.add("Relation");

        tableList.add("NBC_ANC");
        tableList.add("NBC_ANCDetail");
        tableList.add("NBC_Morbidity");
        tableList.add("NBC_NBCare");
        tableList.add("NBC_NBCareDetail");
        tableList.add("NBC_PNCMoth");
        tableList.add("NBC_PNCMothDetail");
        tableList.add("NBC_PNCNB");
        tableList.add("NBC_PNCNBDetail");

        tableList.add("Vaccination");
        tableList.add("VaccinationListMaster");
        tableList.add("Climate_Change");

        tableList.add("cleaning_household");
        tableList.add("cleaning_member");
        tableList.add("audit_trial");
        tableList.add("Anthropometric");
        return tableList;
    }

    //UI Design Attributes
    //==============================================================================================
    public static boolean Show_Bottom_Navigation_Bar = false;
    public static boolean Show_Floating_Button_Navigation_Bar = false;

    //System Variables: Don't need to change
    //==============================================================================================
    public static String NewVersionName  = apiName +"_update";
    public static String UpdatedSystem   = ProjectSetting.ServerURL + "/"+ ProjectSetting.apiName +"/Update/"+ ProjectSetting.NewVersionName +".txt";
    public static String Namespace       = "http://chu.icddrb.org/";
    public static String zipDatabaseName = "champs_hdss"+"_"+LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME +"Database.zip";
    public static String DBSecurityPass  = "a";
    public static String Organization    = "ICDDR,B";
    public static String Soap_Address    = ProjectSetting.ServerURL + "/"+ ProjectSetting.apiName +"/datasync_v4.asmx";
    public static int Sync_Request_Timeout     = 60*1500; // 1.5 minutes
    //==============================================================================================
}
