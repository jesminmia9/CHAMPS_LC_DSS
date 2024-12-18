package Common;

import java.util.ArrayList;
import java.util.List;

public class Global_CodeList {

    //LGA List for Crossriver
    public static List<String> Get_LGA(){
        List<String> lst_lga = new ArrayList<String>();
        lst_lga.add("Abi");
        lst_lga.add("Akamkpa");
        lst_lga.add("Akpabuyo");
        lst_lga.add("Bakassi");
        lst_lga.add("Bekwarra");
        lst_lga.add("Biase");
        lst_lga.add("Boki");
        lst_lga.add("Calabar Municipal");
        lst_lga.add("Calabar South");
        lst_lga.add("Etung");
        lst_lga.add("Ikom");
        lst_lga.add("Obanliku");
        lst_lga.add("Obubra");
        lst_lga.add("Obudu");
        lst_lga.add("Odukpani");
        lst_lga.add("Ogoja");
        lst_lga.add("Yakurr");
        lst_lga.add("Yala");

        return lst_lga;
    }

    public static List<String> Get_State(){
        List<String> lst_state = new ArrayList<String>();
        lst_state.add("Abia");
        lst_state.add("Adamawa");
        lst_state.add("Akwa Ibom");
        lst_state.add("Anambra");
        lst_state.add("Bauchi");
        lst_state.add("Bayelsa");
        lst_state.add("Benue");
        lst_state.add("Borno");
        lst_state.add("Cross River");
        lst_state.add("Delta");
        lst_state.add("Ebonyi");
        lst_state.add("Edo");
        lst_state.add("Ekiti");
        lst_state.add("Enugu");
        lst_state.add("Gombe");
        lst_state.add("Imo");
        lst_state.add("Jigawa");
        lst_state.add("Kaduna");
        lst_state.add("Kano");
        lst_state.add("Katsina");
        lst_state.add("Kebbi");
        lst_state.add("Kogi");
        lst_state.add("Kwara");
        lst_state.add("Lagos");
        lst_state.add("Nasarawa");
        lst_state.add("Niger");
        lst_state.add("Ogun");
        lst_state.add("Ondo");
        lst_state.add("Osun");
        lst_state.add("Oyo");
        lst_state.add("Plateau");
        lst_state.add("Rivers");
        lst_state.add("Sokoto");
        lst_state.add("Taraba");
        lst_state.add("Yobe");
        lst_state.add("Zamfara");
        lst_state.add("Federal Capital Territory");

        return lst_state;
    }

    //Education Code List
    public static List<String> Get_EDUCATION(){
        List<String> lst_edu = new ArrayList<String>();
        lst_edu.add("");

        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)) {
            lst_edu.add("00-No Education");

            //add for surveillance
//            lst_edu.add("11-Never attended school");
            lst_edu.add("12-Finished Primary 1");
            lst_edu.add("13-Finished Primary 2");
            lst_edu.add("14-Finished Primary 3");
            lst_edu.add("15-Finished Primary 4");
            lst_edu.add("16-Finished Primary 5");
            lst_edu.add("17-Stopped at Primary 6 but did not write FSCL/Common Entrance");

            lst_edu.add("01-First School Leaving Certificate/Primary Certificate");
            lst_edu.add("02-JSCE/Junior Secondary School Certificate");
            lst_edu.add("03-SSCE/Secondary School Certificate");
            lst_edu.add("04-Pre-ND/Certificate (A-Level)");
            lst_edu.add("05-National Diploma (ND)/NCE/Nursing");
            lst_edu.add("06-HND");
            lst_edu.add("07-BSc/B. Tech/MBSS/BL");
            lst_edu.add("08-PGD");
            lst_edu.add("09-MSc/M. Tech/MPhil");
            lst_edu.add("10-PhD");

            lst_edu.add("66-Not Applicable");
            lst_edu.add("97-Others");
            lst_edu.add("98-Don't know");
            lst_edu.add("99-Refused to Respond");
        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2)) {
            lst_edu.add("00-No Education");
            lst_edu.add("01-First School Leaving Certificate/Primary Certificate");
            lst_edu.add("02-SSCE/Secondary School Certificate");
            lst_edu.add("03-Madarasa");
            lst_edu.add("04-Pre-ND/Certificate");
            lst_edu.add("05-National Diploma (ND)/NCE/Nursing");
            lst_edu.add("06-HND");
            lst_edu.add("07-BSc/B. Tech/MBSS/BL");
            lst_edu.add("08-PGD");
            lst_edu.add("09-MSc/M. Tech/MPhil");
            lst_edu.add("10-PhD");
            lst_edu.add("66-Not Applicable");
            lst_edu.add("97-Others");
            lst_edu.add("98-Don't know");
            lst_edu.add("99-Refused to Respond");
        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1)) {
            lst_edu.add("00-No Education");
            lst_edu.add("01-Pre School");
            lst_edu.add("02-Primary");
            lst_edu.add("03-Secondary");
            lst_edu.add("04-Vocational");
            lst_edu.add("05-Tertiary");
            lst_edu.add("06-Non-Formal Education");
            lst_edu.add("66-Not Applicable");
            lst_edu.add("97-Others");
            lst_edu.add("98-Don't know");
            lst_edu.add("99-Refused to Answer");
        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)) {
             lst_edu.add("00-No Education");
             lst_edu.add("01-Koranic Education");
             lst_edu.add("02-Primary Education");
             lst_edu.add("03-Secondary Education");
             lst_edu.add("04-Post Secondary");
             lst_edu.add("66-Not Applicable");
             lst_edu.add("97-Others");
             lst_edu.add("98-Don't know");
             lst_edu.add("99-Refused to Respond");
        }

        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.BANGLADESH_SITE1)) {
            lst_edu.add("00-No Education");
            lst_edu.add("01-Class 1 Passed");
            lst_edu.add("02-Class 2 Passed");
            lst_edu.add("03-Class 3 Passed");
            lst_edu.add("04-Class 4 Passed");
            lst_edu.add("05-Class 5 Passed");
            lst_edu.add("06-Class 6 Passed");
            lst_edu.add("07-Class 7 Passed");
            lst_edu.add("08-Class 8 Passed");
            lst_edu.add("09-Class 9 Passed");
            lst_edu.add("10-S.S.C Passed");
            lst_edu.add("12-H.S.C Passed");
            lst_edu.add("14-BA/BCOM/BSC Passed/Diploma passed");
            lst_edu.add("16-BA/BCOM/BSC (Hons.) or BSC-Engineering Passed");
            lst_edu.add("17-MA/MSC/MCOM/MSS/MBBS  Passed/Higher Degree");
            lst_edu.add("66-Not Applicable");
            lst_edu.add("97-Other");
            lst_edu.add("98-Don’t know");
            lst_edu.add("99-Refused to respond");
        }

        return lst_edu;
    }

    //Employment Code List
    public static List<String> Get_Employment(){
        List<String> listEmploy   = new ArrayList<String>();
        listEmploy.add("");
        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)) {

            listEmploy.add("00-Unemployed/Not working");
            listEmploy.add("01-Full-time employed");
            listEmploy.add("02-Part-time employed");
            listEmploy.add("03-Self-employed");
            listEmploy.add("04-Volunteer");
            listEmploy.add("66-Not Applicable");
            listEmploy.add("97-Other");
            listEmploy.add("98-Don't know");
            listEmploy.add("99-Refused to respond");
        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1)){
            listEmploy.add("00-Unemployed/Not working");
            listEmploy.add("01-Full-time employed");
            listEmploy.add("02-Part-time employed");
            listEmploy.add("03-Self-employed");
            listEmploy.add("04-Volunteer");
            listEmploy.add("66-Not Applicable");
            listEmploy.add("97-Other");
            listEmploy.add("98-Don't know");
            listEmploy.add("99-Refused to respond");
        }
        else{

            listEmploy.add("00-Unemployed/Not working");
            listEmploy.add("01-Full-time employed");
            listEmploy.add("02-Part-time employed");
            listEmploy.add("03-Self-employed");
            listEmploy.add("04-Volunteer");
            listEmploy.add("66-Not Applicable");
            listEmploy.add("97-Other");
            listEmploy.add("98-Don't know");
            listEmploy.add("99-Refused to respond");
        }

        return listEmploy;
    }

    //Occupation Code List
    public static List<String> Get_OCCUPATION(){
        /*
        //Baseline: using cross river and bauchi
        listOcp.add("01-None");
        listOcp.add("11-Farmer");
        listOcp.add("02-Student");
        listOcp.add("33-Business man/woman");
        listOcp.add("21-Artisan");
        listOcp.add("35-Private sector employee");
        listOcp.add("36-Civil/Public servant");
        listOcp.add("06-Retired");
        listOcp.add("37-Breeding");
        listOcp.add("38-Househelp/Maid");
        listOcp.add("97-Other specify");
        listOcp.add("98-Don’t know");
        listOcp.add("99-Refused to respond");
        */

        List<String> listOcp   = new ArrayList<String>();
        listOcp.add("");
        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1) ||
                ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2))
        {
            listOcp.add("01-None");
            listOcp.add("02-Farming");
            listOcp.add("03-Student");
            listOcp.add("04-Business man/woman(Trade)");
            listOcp.add("05-Craftmanship(handworker)/Artisan");
            listOcp.add("06-Private sector employee");
            listOcp.add("07-Civil/Public servant");
            listOcp.add("08-Retired");
            listOcp.add("09-Breeding");
            listOcp.add("10-Househelp/Maid");
            listOcp.add("11-Housewife");
            listOcp.add("66-Not Applicable");
            listOcp.add("97-Other specify");
            listOcp.add("98-Don’t know");
            listOcp.add("99-Refused to respond");
        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1))
        {
            listOcp.add("01-None");
            listOcp.add("02-Farming");
            listOcp.add("03-Student");
            listOcp.add("04-Business man/woman(Trade)");
            listOcp.add("05-Craftmanship(handworker)/Artisan");
            listOcp.add("06-Private sector employee");
            listOcp.add("07-Civil/Public servant");
            listOcp.add("08-Retired");
            listOcp.add("09-Breeding");
            listOcp.add("10-Househelp/Maid");
            listOcp.add("11-Housewife");
            listOcp.add("66-Not Applicable");
            listOcp.add("97-Other specify");
            listOcp.add("98-Don’t know");
            listOcp.add("99-Refused to respond");
        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1))
        {
            listOcp.add("01-None");
            listOcp.add("02-agriculteur");
            listOcp.add("03-Student");
            listOcp.add("04-Business man/woman(Trade)");
            listOcp.add("05-Craftmanship(handworker)/Artisan");
            listOcp.add("06-Private sector employee");
            listOcp.add("07-Civil/Public servant");
            listOcp.add("08-Retired");
            listOcp.add("09-Breeding");
            listOcp.add("10-Househelp/Maid");
            listOcp.add("11-Housewife");
            listOcp.add("12-Imam");
            listOcp.add("13-Traditional Healer");
            listOcp.add("14-Clergy/Pastor/Priest");
            listOcp.add("66-Not Applicable");
            listOcp.add("97-Other specify");
            listOcp.add("98-Don’t know");
            listOcp.add("99-Refused to respond");
        }
        else {
            listOcp.add("01-None");
            listOcp.add("02-Farming");
            listOcp.add("03-Student");
            listOcp.add("04-Business man/woman(Trade)");
            listOcp.add("05-Craftmanship(handworker)/Artisan");
            listOcp.add("06-Private sector employee");
            listOcp.add("07-Civil/Public servant");
            listOcp.add("08-Retired");
            listOcp.add("09-Breeding");
            listOcp.add("10-Househelp/Maid");
            listOcp.add("11-Housewife");
            listOcp.add("66-Not Applicable");
            listOcp.add("97-Other specify");
            listOcp.add("98-Don’t know");
            listOcp.add("99-Refused to respond");
        }
        return listOcp;
    }

    //Marital Status Code List
    public static List<String> Get_MARITAL_STATUS(){
        List<String> listMS   = new ArrayList<String>();
        listMS.add("");
        listMS.add("00-Single/never married");
        listMS.add("01-Married");
        listMS.add("02-Married (polygamous)");
        listMS.add("03-Regular partner(cohabiting)");
        listMS.add("04-Divorced");
        listMS.add("05-Separated");
        listMS.add("06-Widow/Widower");
        listMS.add("66-Not Applicable");
        listMS.add("97-Other");
        listMS.add("98-Don't know");
        listMS.add("99-Refused to respond");
        return listMS;
    }

    public static List<String> Get_MARITAL_STATUS_SURVEILLANCE(){
        List<String> listMS   = new ArrayList<String>();
        listMS.add("");
        listMS.add("00-Single/never married");
        listMS.add("01-Married");
        listMS.add("02-Married (polygamous)");
        listMS.add("03-Regular partner(cohabiting)");
        listMS.add("04-Divorced");
        listMS.add("05-Separated");
        listMS.add("06-Widow/widower");

        return listMS;
    }

    //Ethnicity Code List
    public static List<String> Get_ETHNICITY(String STUDY_SITE){
        List<String> listEthnicity   = new ArrayList<String>();
        listEthnicity.add("");
        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1))
        {
            listEthnicity.add("45-Efik");
            listEthnicity.add("47-Ejagham");
            listEthnicity.add("10-Igbo");
            listEthnicity.add("09-Hausa");
            listEthnicity.add("42-Yoruba");
            listEthnicity.add("49-Ibibio");
            listEthnicity.add("51-Dusauga Iyong Iyong");
            listEthnicity.add("60-Anang");
            listEthnicity.add("61-Oron");
            listEthnicity.add("86-Ekoi");
            listEthnicity.add("97-Other specify");
            listEthnicity.add("98-Don’t know");
            listEthnicity.add("99-Refused to respond");
        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2))
        {
            listEthnicity.add("7-Fulani (Fulbe)");
            listEthnicity.add("8-Fulani (Peuhl)");
            listEthnicity.add("9-Hausa");
            listEthnicity.add("10-Igbo");
            listEthnicity.add("62-Miyawa");
            listEthnicity.add("63-Warjawa");
            listEthnicity.add("64-Jarawa");
            listEthnicity.add("65-Seyawa");
            listEthnicity.add("66-Angas");
            listEthnicity.add("67-Gerawa");
            listEthnicity.add("68-Benewa");
            listEthnicity.add("69-Sirawa");
            listEthnicity.add("70-Banbadawa");
            listEthnicity.add("97-Other specify");
            listEthnicity.add("98-Don't know");
            listEthnicity.add("99-Refused to respond");

        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1))
        {
            listEthnicity.add("15-Kuranko");
            listEthnicity.add("16-Limba");
            listEthnicity.add("22-Mende");
            listEthnicity.add("36-Temne");
            listEthnicity.add("46-Creole");
            listEthnicity.add("48-Fullah");
            listEthnicity.add("50-Kissi");
            listEthnicity.add("52-Kono");
            listEthnicity.add("53-Loko");
            listEthnicity.add("54-Mandingo");
            listEthnicity.add("55-Sherbro");
            listEthnicity.add("56-Susu");
            listEthnicity.add("57-Themne");
            listEthnicity.add("58-Vai");
            listEthnicity.add("59-Yalunkaa");
            listEthnicity.add("97-Other specify");
            listEthnicity.add("98-Don't know");
            listEthnicity.add("99-Refused to respond");
        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1))
        {
            listEthnicity.add("4-Bambara");
            listEthnicity.add("6-Dogon");
            listEthnicity.add("7-Fulani (Fulbe)");
            listEthnicity.add("8-Fulani (Peuhl)");
            listEthnicity.add("39-Tuareg");
            listEthnicity.add("71-Malinké");
            listEthnicity.add("72-Sarakolé");
            //listEthnicity.add("73-Peuhl");
            listEthnicity.add("74-Bobo");
            listEthnicity.add("75-Sénoufo");
            listEthnicity.add("76-Minianka");
            listEthnicity.add("77-Bozo");
            listEthnicity.add("78-Somono");
            listEthnicity.add("79-Sonrhai");
            listEthnicity.add("80-Maure");
            //listEthnicity.add("81-Tamachek");
            listEthnicity.add("82-Samoko");
            listEthnicity.add("83-Dafing");
            listEthnicity.add("84-Autre");
            listEthnicity.add("85-Ne sais pas");
            listEthnicity.add("97-Autre à preciser");
            listEthnicity.add("98-Je ne sais pas");
            listEthnicity.add("99-Refus de répondre");
        }
        else{
            listEthnicity.add("1-Afrikaner");
            listEthnicity.add("2-Amhara");
            listEthnicity.add("3-Baloch");
            listEthnicity.add("4-Bambara");
            listEthnicity.add("5-Chakma");
            listEthnicity.add("6-Dogon");
            listEthnicity.add("7-Fulani (Fulbe)");
            listEthnicity.add("8-Fulani (Peuhl)");
            listEthnicity.add("9-Hausa");
            listEthnicity.add("10-Igbo");
            listEthnicity.add("11-Kamba");
            listEthnicity.add("12-Kanuri");
            listEthnicity.add("13-Kikuyu");
            listEthnicity.add("14-Krio");
            listEthnicity.add("15-Kuranko");
            listEthnicity.add("16-Limba");
            listEthnicity.add("17-Luo");
            listEthnicity.add("18-Luhya");
            listEthnicity.add("19-Maasai");
            listEthnicity.add("20-Makhuwa");
            listEthnicity.add("21-Marma");
            listEthnicity.add("22-Mende");
            listEthnicity.add("23-Mro");
            listEthnicity.add("24-Oromo");
            listEthnicity.add("25-Pashtun (Pakhtun)");
            listEthnicity.add("26-Punjabi");
            listEthnicity.add("27-Rohingya");
            listEthnicity.add("28-Saraiki");
            listEthnicity.add("29-Sena");
            listEthnicity.add("30-Shona");
            listEthnicity.add("31-Sidama");
            listEthnicity.add("32-Sindhi");
            listEthnicity.add("33-Somali");
            listEthnicity.add("34-Songhai");
            listEthnicity.add("35-Sotho");
            listEthnicity.add("36-Temne");
            listEthnicity.add("37-Tigray");
            listEthnicity.add("38-Tsonga");
            listEthnicity.add("39-Tuareg");
            listEthnicity.add("40-Xhosa");
            listEthnicity.add("41-Yao");
            listEthnicity.add("42-Yoruba");
            listEthnicity.add("43-Zulu");
            listEthnicity.add("44-Bengali");
            listEthnicity.add("45-Efik");
            listEthnicity.add("46-Creole");
            listEthnicity.add("47-Ejagham");
            listEthnicity.add("48-Fullah");
            listEthnicity.add("49-Ibibio");
            listEthnicity.add("50-Kissi");
            listEthnicity.add("51-Dusauga Iyong Iyong");
            listEthnicity.add("52-Kono");
            listEthnicity.add("53-Loko");
            listEthnicity.add("54-Mandingo");
            listEthnicity.add("55-Sherbro");
            listEthnicity.add("56-Susu");
            listEthnicity.add("57-Themne");
            listEthnicity.add("58-Vai");
            listEthnicity.add("59-Yalunkaa");
            listEthnicity.add("60-Anang");
            listEthnicity.add("61-Oron");
            listEthnicity.add("62-Miyawa");
            listEthnicity.add("63-Warjawa");
            listEthnicity.add("64-Jarawa");
            listEthnicity.add("65-Seyawa");
            listEthnicity.add("66-Angas");
            listEthnicity.add("67-Gerawa");
            listEthnicity.add("68-Benewa");
            listEthnicity.add("69-Sirawa");
            listEthnicity.add("70-Banbadawa");
            listEthnicity.add("97-Other specify");
            listEthnicity.add("98-Don't know");
            listEthnicity.add("99-Refused to respond");
        }

        return listEthnicity;
    }

    public static List<String> Get_RTH() {
        List<String> listRth = new ArrayList<String>();

        listRth.add("");
        listRth.add("01-Household head");
        listRth.add("02-1st Spouse");
        listRth.add("03-2nd Spouse");
        listRth.add("04-3rd Spouse");
        listRth.add("05-4th Spouse");
        listRth.add("06-Son");
        listRth.add("07-Daughter");
        listRth.add("08-Adopted son");
        listRth.add("09-Adopted daughter");
        listRth.add("10-Stepson");
        listRth.add("11-Stepdaughter");
        listRth.add("12-Nephew");
        listRth.add("13-Niece");
        listRth.add("14-Grandson");
        listRth.add("15-Granddaughter");
        listRth.add("16-Father");
        listRth.add("17-Mother");
        listRth.add("18-Stepfather");
        listRth.add("19-Stepmother");
        listRth.add("20-Brother");
        listRth.add("21-Sister");
        listRth.add("22-Stepbrother");
        listRth.add("23-Stepsister");
        listRth.add("24-Cousin");
        listRth.add("25-Uncle");
        listRth.add("26-Aunt");
        listRth.add("27-Grandfather");
        listRth.add("28-Grandmother");
        listRth.add("29-Father/mother-in-law");
        listRth.add("30-Son/daughter-in-law");
        listRth.add("31-Brother/sister-in-law");
        listRth.add("32-Unrelated");
        listRth.add("97-Other");
        listRth.add("98-Don't know");
        listRth.add("99-Refused to respond");

        return  listRth;
    }

    public static List<String> Get_CAREGIVER_RELATION() {
        List<String> listRth = new ArrayList<String>();

        listRth.add("");
        /*listRth.add("01-Household head");
        listRth.add("02-1st Spouse");
        listRth.add("03-2nd Spouse");
        listRth.add("04-3rd Spouse");
        listRth.add("05-4th Spouse");
        listRth.add("06-Son");
        listRth.add("07-Daughter");
        listRth.add("08-Adopted son");
        listRth.add("09-Adopted daughter");
        listRth.add("10-Stepson");
        listRth.add("11-Stepdaughter");
        listRth.add("12-Nephew");
        listRth.add("13-Niece");
        listRth.add("14-Grandson");
        listRth.add("15-Granddaughter");*/
        listRth.add("16-Father");
        listRth.add("17-Mother");
        listRth.add("18-Stepfather");
        listRth.add("19-Stepmother");
        listRth.add("20-Brother");
        listRth.add("21-Sister");
        listRth.add("22-Stepbrother");
        listRth.add("23-Stepsister");
        listRth.add("24-Cousin");
        listRth.add("25-Uncle");
        listRth.add("26-Aunt");
        listRth.add("27-Grandfather");
        listRth.add("28-Grandmother");
        listRth.add("29-Father/mother-in-law");
        //listRth.add("30-Son/daughter-in-law");
        //listRth.add("31-Brother/sister-in-law");
        listRth.add("32-Unrelated");
        listRth.add("97-Other");
        listRth.add("98-Don't know");
        listRth.add("99-Refused to respond");

        return  listRth;
    }

    public static List<String> Get_RELIGION() {
        List<String> listReligion = new ArrayList<String>();

        listReligion.add("");

        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1))
        {
            listReligion.add("15-Muslim");
            listReligion.add("23-Traditional");
            listReligion.add("9-Christian: Catholic");
            listReligion.add("20-Christian: protestant/Pentecostal");
            listReligion.add("25-Christian: Jehovah Witness");
            listReligion.add("10-Christian: unspecified");
            listReligion.add("0-No Religion");
            listReligion.add("97-Other specify");
            listReligion.add("98-Don’t know");
            listReligion.add("99-Refused to respond");
        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2))
        {
            listReligion.add("15-Islam/Muslim");
            listReligion.add("23-Traditional");
            listReligion.add("9-Christian: Catholic");
            listReligion.add("20-Christian: protestant/Pentecostal");
            listReligion.add("25-Christian: Jehovah Witness");
            listReligion.add("10-Christian: unspecified");
            listReligion.add("0-No Religion");
            listReligion.add("97-Other specify");
            listReligion.add("98-Don’t know");
            listReligion.add("99-Refused to respond");
        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.SIERRA_LEONE_SITE1))
        {
            listReligion.add("9-Christian: Catholic");
            listReligion.add("10-Christian unspecified");
            listReligion.add("15-Islam/Muslim");
            listReligion.add("20-Christian: protestant/Pentecostal");
            listReligion.add("23-Traditional/Traditionalist");
            listReligion.add("25-Christian: Jehovah Witness");
            listReligion.add("0-No Religion");
            listReligion.add("97-Other specify");
            listReligion.add("98-Don't know");
            listReligion.add("99-Refused to respond");
        }
        else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1))
        {

            listReligion.add("4-Animiste");
            listReligion.add("9-Chrétienne");
            listReligion.add("10-cristiano no especificado");
            listReligion.add("15-Islam");
            listReligion.add("20-cristiano: protestante/pentecostal");
            listReligion.add("0-Sans religion");
            listReligion.add("97-Autre");
            listReligion.add("98-No lo sé");
            listReligion.add("99-Se negó a responder");
        }
        else{
            listReligion.add("0-No Religion");
            listReligion.add("1-African independent church");
            listReligion.add("2-African tribal tradition");
            listReligion.add("3-Anglican");
            listReligion.add("4-Animist");
            listReligion.add("5-Agnostic");
            listReligion.add("6-Atheist");
            listReligion.add("7-Baptist");
            listReligion.add("8-Buddhist");
            listReligion.add("9-Christian: Catholic");
            listReligion.add("10-Christian unspecified");
            listReligion.add("11-Evangelical");
            listReligion.add("12-Hindu");
            listReligion.add("13-Legio Maria");
            listReligion.add("14-Methodist");
            listReligion.add("15-Islam/Muslim");
            listReligion.add("16-Nomia");
            //listReligion.add("17-No Religion");
            listReligion.add("18-Orthodox Christian");
            listReligion.add("19-Presbyterian");
            listReligion.add("20-Christian: protestant/Pentecostal");
            listReligion.add("21-Roho");
            listReligion.add("22-SDA");
            listReligion.add("23-Traditional/Traditionalist");
            listReligion.add("24-Zion");
            listReligion.add("25-Christian: Jehovah Witness");
            listReligion.add("97-Other specify");
            listReligion.add("98-Don't know");
            listReligion.add("99-Refused to respond");
        }
        return listReligion;
    }
  
    /*public static List<String> Get_Emploment() {
        List<String> listEmploy = new ArrayList<String>();
        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
            listEmploy.add("");
            listEmploy.add("0-Chômeur/Ne travaille pas");
            listEmploy.add("1-Employé à temps plein");
            listEmploy.add("2-Employé à temps partiel");
            listEmploy.add("3-Indépendant");
            listEmploy.add("7-Autre");
            listEmploy.add("8-Je ne sais pas");
            listEmploy.add("9-Refusé de répondre");
        }
        else{
            listEmploy.add("");
            listEmploy.add("0-Unemployed/Not working");
            listEmploy.add("1-Full-time employed");
            listEmploy.add("2-Part-time employed");
            listEmploy.add("3-Self-employed");
            listEmploy.add("7-Other");
            listEmploy.add("8-Don't know");
            listEmploy.add("9-Refused to respond");
        }


        return  listEmploy;
    }*/

    public static List<String> Get_GPSType() {
        List<String> listGPSType = new ArrayList<String>();
        listGPSType.add("");
        listGPSType.add("1-Medical college hospital");
        listGPSType.add("2-UH&FWC");
        listGPSType.add("3-Union Sub Center");
        listGPSType.add("5-Community Clinic(CC)");
        listGPSType.add("6-Private clinic/Hospital");
        listGPSType.add("7-NGO Clinic");
        listGPSType.add("8-Charitable/Trust Run Hospital");
        listGPSType.add("9-School");
        listGPSType.add("10-College");
        listGPSType.add("11-Madrasa");
        listGPSType.add("12-Mosque");
        listGPSType.add("13-Grave yard");
        listGPSType.add("14-Temple");
        listGPSType.add("15-Bridge/culvert");
        listGPSType.add("16-Bazar");
        listGPSType.add("17-Pharmacy");
        listGPSType.add("18-Shop");
        listGPSType.add("19-Filling station");
        listGPSType.add("20-Bus stand");
        listGPSType.add("21-Tempo stand");
        listGPSType.add("22-Rail station");
        listGPSType.add("24-Townhall");
        listGPSType.add("25-Village Square");
        listGPSType.add("26-Borehole/Overhead tank");
        listGPSType.add("27-Roundabout");
        listGPSType.add("28-Market");
        listGPSType.add("29-Church");
        listGPSType.add("30-Mechanic workshop");
        listGPSType.add("31-Hotel/guest house");
        listGPSType.add("32-Company");
        listGPSType.add("33-Football Field/playground");
        listGPSType.add("34-Car wash");
        listGPSType.add("35-Court berray");
        listGPSType.add("36-Chief house");
        listGPSType.add("37-Borehole");
        listGPSType.add("38-Bondo-bush/shrine");
        listGPSType.add("77-Other");

        return  listGPSType;
    }

    public static  String Get_Process_Transaction_Message(int position, int language){

        List<String> get_message = new ArrayList<String>();
        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)){
            if(language == 4){
                get_message.add(0, "Il doit y avoir un chef de ménage dans le ménage.");
                get_message.add(1, "Le ménage ne peut pas avoir plus d'un chef de ménage.");
                get_message.add(2, "Au moins un membre doit entrer dans le foyer.");
                get_message.add(3, "Les informations sociodémographiques ne peuvent pas rester vides.");
                get_message.add(4, "Les antécédents de grossesse n'ont pas été collectés.");
                get_message.add(5, "L'état civil ne sera pas applicable/autres/Ne sait pas/refuse de répondre si l'âge est inférieur à "+ ProjectSetting.ELIGIBILITY_AGE_MS_YEAR +" ans.");
                get_message.add(6, "Le numéro de série du père/mère ne sera pas 00 si le lien avec le chef de ménage est fils/fille.");
                get_message.add(7, "Le sexe de la mère n'est pas cohérent. Devrait être une femme.");
                get_message.add(8, "Le sexe du père n'est pas cohérent. Devrait être un homme.");
            }
            else{
                get_message.add(0, "There must be a household head in the household.");
                get_message.add(1, "Household can't have more than one household head.");
                get_message.add(2, "At least one member must enter.");
                get_message.add(3, "Socio-demographic information can't be left blank.");
                get_message.add(4, "Pregnancy history was not collected.");
                get_message.add(5, "Marital status will be not applicable/others/Don't know/refuse to response if age is below "+ ProjectSetting.ELIGIBILITY_AGE_MS_YEAR +" years.");
                get_message.add(6, "Serial No. of father/mother will not be 00 if the relationship with the household head is son/daughter.");
                get_message.add(7, "Sex of the mother is not consistent. Should be female.");
                get_message.add(8, "Sex of the father is not consistent. Should be male.");
            }

        }else {
            get_message.add(0, "There must be a household head in the household.");
            get_message.add(1, "Household can't have more than one household head.");
            get_message.add(2, "At least one member must enter.");
            get_message.add(3, "Socio-demographic information can't be left blank.");
            get_message.add(4, "Pregnancy history was not collected.");
            get_message.add(5, "Marital status will be not applicable/others/Don't know/refuse to response if age is below "+ ProjectSetting.ELIGIBILITY_AGE_MS_YEAR +" years.");
            get_message.add(6, "Serial No. of father/mother will not be 00 if the relationship with the household head is son/daughter.");
            get_message.add(7, "Sex of the mother is not consistent. Should be female.");
            get_message.add(8, "Sex of the father is not consistent. Should be male.");
        }

        /*get_message.add(6,"Mother's serial number is not correct");
        get_message.add(7,"Father's serial number is not correct");
        get_message.add(9,"Education cannot be 00 for profession meritorious.");
        get_message.add(10,"A live birth occurred but event 25 did not occur");
        get_message.add(11,"This member's spouse is of the same sex");
        get_message.add(12,"(Spouse Serial1) Number 00 of the member's spouse");
        get_message.add(13,"(Serial1 of Spouse) number of member whose spouse is blank");
        get_message.add(14,"Member has spouse serial number but no spouse listed");
        get_message.add(15,"husband/wife of household head but his (husband's serial1) number is 00");
        get_message.add(16,"Member is currently married but his (husband/wife serial) number is blank");
        get_message.add(17,"Check the member's relationship code and marital status code");
        get_message.add(18,"Check the relationship code with household head and the serial number of the member's father");
        get_message.add(19,"Check the relationship code and serial number of the member's mother with the household head");
        get_message.add(20,"brother/sister of household head but father has no serial");
        get_message.add(21,"brother/sister of household head but mother has no serial");
        get_message.add(22,"father of household head is present in household but father's serial is not there");
        get_message.add(23,"mother of household head is present in household but mother's serial is not there");
        get_message.add(24,"son/daughter of household head but no father's serial");
        get_message.add(25,"There is a mother but there is no serial of the mother, son/daughter of the head of the relationship");
        get_message.add(26,"Stillbirth occurred but event 47 did not occur");*/


        return get_message.get(position);
    }
}
