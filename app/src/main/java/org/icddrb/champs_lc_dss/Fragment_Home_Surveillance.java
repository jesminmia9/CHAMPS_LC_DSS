package org.icddrb.champs_lc_dss;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import forms_activity.Surv_Compound_list;

public class Fragment_Home_Surveillance extends Fragment {
    public static Fragment_Home_Surveillance newInstance() {
        Fragment_Home_Surveillance fragment = new Fragment_Home_Surveillance();
        return fragment;
    }

    Context thiscontext;
    Bundle IBundle;
    Connection C;
    private static String DEVICEID = "";
    private static String ENTRYUSER = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        thiscontext=container.getContext();
        IBundle = new Bundle();
        C = new Connection(thiscontext);

        DEVICEID  = MySharedPreferences.getValue(thiscontext, "deviceid");
        ENTRYUSER = MySharedPreferences.getValue(thiscontext, "userid");

        GridView gv = rootView.findViewById(R.id.gridview);
        gv.setAdapter(new menuAdapter(thiscontext));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                try
                {
                    if(position==0)
                    {
                        Intent I = new Intent(thiscontext, Surv_Compound_list.class);
                        IBundle.putString("locationid", "");
                        IBundle.putString("locationname", "Village/Area Name");
                        IBundle.putString("SurvType", "2");
                        I.putExtras(IBundle);
                        startActivity(I);
                    }
                    else if(position==1)
                    {

                    }
                    else if(position==2) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(thiscontext);
                        builder
                                .setTitle("Data Sync")
                                .setMessage("Do you want to synchronize data to server[Y/N]?")
                                .setIcon(R.drawable.favicon)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which){
                                            case DialogInterface.BUTTON_POSITIVE:

                                                if (Connection.haveNetworkConnection(thiscontext)) {
                                                } else {
                                                    Connection.MessageBox(thiscontext,"Internet connection is not available for Data Sync.");
                                                    return;
                                                }

                                                new DataSyncTask().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);

                                                break;

                                            case DialogInterface.BUTTON_NEGATIVE:
                                                //No button clicked
                                                break;
                                        }
                                    }
                                })
                                .setNegativeButton("No", null)	//Do nothing on no
                                .show();
                    }
                    else if(position==3)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(thiscontext);
                        builder
                                .setTitle("Exit")
                                .setIcon(R.drawable.favicon)
                                .setMessage("Do you want to exit from the system[Y/N]?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which){
                                            case DialogInterface.BUTTON_POSITIVE:
                                                //getActivity().finish();
                                                getActivity().finishAffinity();
                                                System.exit(0);
                                                break;

                                            case DialogInterface.BUTTON_NEGATIVE:
                                                //No button clicked
                                                break;
                                        }
                                    }
                                })
                                .setNegativeButton("No", null)	//Do nothing on no
                                .show();
                    }


                }
                catch(Exception ex)
                {
                    Connection.MessageBox(thiscontext, ex.getMessage());
                }
            }
        });

        //Synchronize data if database in empty
        if (Connection.haveNetworkConnection(thiscontext)) {
            if(!C.Existence("Select * from Compound limit 1")) {
                if (Connection.haveNetworkConnection(thiscontext)) {
                    new Fragment_Home_Surveillance.DataSyncTask().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
                }
            }
        }

        return rootView; //inflater.inflate(R.layout.fragment_home, container, false);
    }

    private boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) thiscontext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static class menuAdapter extends BaseAdapter {
        private Context mContext;

        public menuAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return menu_list_image.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        //create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            View MyView = convertView;
            if (convertView == null) {
                LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                MyView = li.inflate(R.layout.mainmenugriditem_v1, null);

                try {
                    // Add The Image!!!
                    ImageView iv = (ImageView) MyView.findViewById(R.id.itemImage);
                    iv.setImageResource(menu_list_image[position]);

                    // Add The Text!!!
                    TextView tv = (TextView) MyView.findViewById(R.id.itemDesc);
                    tv.setTextSize(18);
                    tv.setText(menu_list[position]);

                }catch (Exception ex){
                    //String a = ex.getMessage();
                }
                //MyView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 190));
            }
            return MyView;
        }

        private final String[] menu_list={
                "SURVEILLANCE",
                "DASHBOARD",
                "DATA SYNC",
                "EXIT"
        };

        //references to our images
        private final Integer[] menu_list_image = {
                R.drawable.img_surveillance,
                R.drawable.img_dashboard,
                R.drawable.img_datasync1,
                R.drawable.img_exit,
        };
    }

    private class DataSyncTask extends AsyncTask<String, Void, Void> {
        ProgressDialog dialog;
        private Context context;
        String resp = "";
        public void setContext(Context contextf){
            context = contextf;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(thiscontext);
            dialog.setTitle("Data Sync");
            dialog.setMessage("Data Sync in Progress, Please wait ...");
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setCancelable(false);
            dialog.setIcon(R.drawable.data_sync);
            dialog.show();

        }

        //@Override
        protected void onProgressUpdate(String... values) {
            dialog.setProgress(Integer.parseInt(values[0].toString().split(",")[1]));
        }

        @Override
        protected Void doInBackground(String... params) {

            try {

                new Thread() {
                    public void run() {
                        try {
                            int count = 0;
                            int progressCount = 0;

                            List<String> tableList = new ArrayList<String>();

                            //Stop Sync Service if still running
                            //----------------------------------------------------------------------
                            if (isServiceRunning(Sync_Service.class)) {
                                thiscontext.stopService(new Intent(thiscontext, Sync_Service.class));
                            }

                            //Update database level change
                            //----------------------------------------------------------------------
                            C.Sync_DatabaseStructure();

                            Connection.Populate_Index_Table();

                            //Update Catchment Area
                            //----------------------------------------------------------------------
                            //C.Sync_Download("DataCollector_AssignArea","DataCollector_AssignArea", "UserId='"+ ENTRYUSER +"' and WorkGroup='3' and Active='1'");
                            C.Sync_Download("DataCollector_AssignArea","DataCollector_AssignArea", "UserId='"+ ENTRYUSER +"' and WorkGroup='3'");
                            String AREA_LIST = C.GetCatchmentArea(ENTRYUSER,"3");

                            //Upload data to server
                            //----------------------------------------------------------------------
                            List<String> tableList_upload = ProjectSetting.Surveillance_Upload();

                            if(tableList_upload.size()!=0)
                                progressCount = 50/tableList_upload.size();
                            for (int i = 0; i < tableList_upload.size(); i++) {
                                try {
                                    C.Sync_Upload_Process(tableList_upload.get(i));
                                    count +=progressCount;
                                    onProgressUpdate(tableList_upload.get(i)+","+ String.valueOf(count));
                                }catch(Exception ex){
                                    Log.e("Data Upload Error",ex.getMessage());
                                }
                            }
                            count = 50;

                            //Download data from server
                            //----------------------------------------------------------------------

                            progressCount = 1;

                            try {
                                C.Sync_Download("DSSRound", "DSSRound", "");
                                count +=progressCount;
                                onProgressUpdate("DSSRound,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("module_variable_list", "module_variable_list", "");
                                count +=progressCount;
                                onProgressUpdate("module_variable_list,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("module_variable_language", "module_variable_language", "");
                                count +=progressCount;
                                onProgressUpdate("module_variable_language,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("Compound", "Compound", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Compound,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("GPS", "GPS", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("GPS,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("Household", "Household", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Household,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("SES", "vw_sync_SES", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("SES,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("SES_Mali", "vw_sync_SES_Mali", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("SES_Mali,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("SES_CrossRiver", "vw_sync_SES_CrossRiver", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("SES_CrossRiver,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("Visits", "vw_sync_Visits", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Visits,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("Member", "vw_sync_Member", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Member,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("MemberMove", "vw_sync_MemberMove", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("MemberMove,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("PregHis", "vw_sync_PregHis", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("PregHis,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("DeathNotifi", "vw_sync_DeathNotifi", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("DeathNotifi,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("VaccinationList", "VaccinationList", "");
                                count +=progressCount;
                                onProgressUpdate("VaccinationList,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("Vaccination", "vw_sync_Vaccination", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Vaccination,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("VaccinationListMaster", "VaccinationListMaster", "");
                                count +=progressCount;
                                onProgressUpdate("VaccinationListMaster,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("Caregiver", "vw_sync_Caregiver", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Caregiver,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("ChildChar", "vw_sync_ChildChar", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("ChildChar,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("NBC_NBCare", "vw_sync_NBC_NBCare", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("NBC_NBCare,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("NBC_NBCareDetail", "vw_sync_NBC_NBCareDetail", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("NBC_NBCareDetail,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("NBC_ANC", "vw_sync_NBC_ANC", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("NBC_ANC,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("NBC_ANCDetail", "vw_sync_NBC_ANCDetail", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("NBC_ANCDetail,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("NBC_PNCMoth", "vw_sync_NBC_PNCMoth", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("NBC_PNCMoth,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("NBC_PNCMothDetail", "vw_sync_NBC_PNCMothDetail", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("NBC_PNCMothDetail,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("NBC_PNCNB", "vw_sync_NBC_NBC_PNCNB", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("NBC_PNCNB,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("NBC_PNCNBDetail", "vw_sync_NBC_PNCNBDetail", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("NBC_PNCNBDetail,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }


                            try {
                                C.Sync_Download("NBC_Morbidity", "vw_sync_NBC_Morbidity", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("NBC_Morbidity,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.SaveData("delete from migMember");
                                C.SaveData("update local_index_datasync set timestamp='' where table_name='migMember'");
                                C.Sync_Download("migMember", "migMember", "");
                                count +=progressCount;
                                onProgressUpdate("migMember,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }



                            //MaritalSts
                            try {
                                C.Sync_Download("MaritalSts", "vw_sync_MaritalSts", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("MaritalSts,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //NotPregnant
                            try {
                                C.Sync_Download("NotPregnant", "vw_sync_NotPregnant", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("NotPregnant,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //Pregnancy
                            try {
                                C.Sync_Download("Pregnancy", "vw_sync_Pregnancy", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Pregnancy,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //Delivery
                            try {
                                C.Sync_Download("Delivery", "vw_sync_Delivery", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Delivery,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //LiveBirth
                            try {
                                C.Sync_Download("LiveBirth", "vw_sync_LiveBirth", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("LiveBirth,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //StillBirth
                            try {
                                C.Sync_Download("StillBirth", "vw_sync_StillBirth", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("StillBirth,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //Abortion
                            try {
                                C.Sync_Download("Abortion", "vw_sync_Abortion", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Abortion,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            //MotherSerial
                            try {
                                C.Sync_Download("MotherSerial", "vw_sync_MotherSerial", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("MotherSerial,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //FatherSerial
                            try {
                                C.Sync_Download("FatherSerial", "vw_sync_FatherSerial", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("FatherSerial,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //SpouseSerial
                            try {
                                C.Sync_Download("SpouseSerial", "vw_sync_SpouseSerial", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("SpouseSerial,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            //Migration
                            try {
                                C.Sync_Download("Migration", "vw_sync_Migration", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Migration,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //Movement
                            try {
                                C.Sync_Download("Movement", "vw_sync_Movement", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Movement,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //TemporaryMigrationIn
                            try {
                                C.Sync_Download("TemporaryMigrationIn", "vw_sync_TemporaryMigrationIn", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("TemporaryMigrationIn,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //TemporaryMigrationOut
                            try {
                                C.Sync_Download("TemporaryMigrationOut", "vw_sync_TemporaryMigrationOut", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("TemporaryMigrationOut,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            //Education
                            try {
                                C.Sync_Download("Education", "vw_sync_Education", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Education,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //Occupation
                            try {
                                C.Sync_Download("Occupation", "vw_sync_Occupation", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Occupation,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //Relation
                            try {
                                C.Sync_Download("Relation", "vw_sync_Relation", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Relation,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            //Death
                            try {
                                C.Sync_Download("Death", "vw_sync_Death", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Death,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //Newborn
                            try {
                                C.Sync_Download("Newborn", "vw_sync_Newborn", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Newborn,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            //PregDetail
                            try {
                                C.Sync_Download("PregDetail", "vw_sync_PregDetail", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("PregDetail,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            //Data Cleaning
                            try {
                                C.Sync_Download("cleaning_household", "vw_sync_cleaning_household", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("cleaning_household,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            try {
                                C.Sync_Download("cleaning_member", "vw_sync_cleaning_member", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("cleaning_member,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            try {
                                C.Sync_Download("Anthropometric", "vw_sync_Anthropometric", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Anthropometric,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }
                            try {
                                C.Sync_Download("Climate_Change", "vw_sync_Climate_Change", "VillID in("+ AREA_LIST +")");
                                count +=progressCount;
                                onProgressUpdate("Climate_Change,"+ String.valueOf(count));
                            }catch (Exception ignored){
                            }

                            onProgressUpdate(","+ "100");

                            //Database File Upload
                            if (ProjectSetting.Tab_Database_Upload){
                                requireActivity().startService(new Intent(getActivity(),DatabaseFileSync_Service.class));
                            }


                            dialog.dismiss();

                        } catch (Exception e) {
                            resp = e.getMessage();
                            dialog.dismiss();
                        }
                        finally {
                            dialog.dismiss();
                        }
                    }
                }.start();

            } catch (Exception e) {

            }
            // do stuff!
            return null;
        }

        //@Override
        protected void onPostExecute(String result) {
            if(result.length()!=0) {
                Connection.MessageBox(thiscontext, "Data Sync successfully completed.");
                dialog.dismiss();
            }
        }
    }

    private void SelectVillageForm()
    {
        final Dialog dialog = new Dialog(thiscontext);
        dialog.setContentView(R.layout.select_village);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.TOP;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        final Spinner spnDCode;
        final Spinner spnUPCode;
        final Spinner spnUNCode;
        final Spinner spnCluster;
        final Spinner spnVCode;

        spnDCode=(Spinner)dialog.findViewById(R.id.spnDCode);
        spnUPCode=(Spinner)dialog.findViewById(R.id.spnUPCode) ;
        spnUNCode=(Spinner)dialog.findViewById(R.id.spnUNCode) ;
        spnCluster=(Spinner)dialog.findViewById(R.id.spnCluster) ;
        spnVCode=(Spinner)dialog.findViewById(R.id.spnVCode) ;
        spnDCode.setAdapter(C.getArrayAdapter("Select '' union Select DCode||'-'||DName from zilla"));
        spnUPCode.setAdapter(C.getArrayAdapter("Select '' union Select UPCode||'-'||UPName from Upazila where DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"'"));
        spnUNCode.setAdapter(C.getArrayAdapter("Select '' union Select UNCode||'-'||UNName from Unions where DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"' and UPCode='"+ spnUPCode.getSelectedItem().toString().split("-")[0] +"'"));
        spnCluster.setAdapter(C.getArrayAdapter("Select '' union Select '000' union Select Cluster from Cluster where DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"' and UPCode='"+ spnUPCode.getSelectedItem().toString().split("-")[0] +"' and UNCode='"+ spnUNCode.getSelectedItem().toString().split("-")[0] +"'"));
        spnVCode.setAdapter(C.getArrayAdapter("Select '' union select v.vcode||'-'||v.VName from Village v inner join Cluster c on v.dcode=c.dcode and v.upcode=c.upcode and v.uncode=c.uncode and v.vcode=c.vcode and c.cluster='"+ spnCluster.getSelectedItem().toString().split("-")[0] +"'" +
                " where v.DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"' and v.UPCode='"+ spnUPCode.getSelectedItem().toString().split("-")[0] +"' and v.UNCode='"+ spnUNCode.getSelectedItem().toString().split("-")[0] +"'"));

        spnDCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spnUPCode.setAdapter(C.getArrayAdapter("Select '' union Select UPCode||'-'||UPName from Upazila where DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"'"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        spnUPCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spnUNCode.setAdapter(C.getArrayAdapter("Select '' union Select UNCode||'-'||UNName from Unions where DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"' and UPCode='"+ spnUPCode.getSelectedItem().toString().split("-")[0] +"'"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        spnUNCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spnCluster.setAdapter(C.getArrayAdapter("Select '' union Select '000' union Select Cluster from Cluster where DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"' and UPCode='"+ spnUPCode.getSelectedItem().toString().split("-")[0] +"' and UNCode='"+ spnUNCode.getSelectedItem().toString().split("-")[0] +"'"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        spnCluster.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spnVCode.setAdapter(C.getArrayAdapter("Select '' union select v.vcode||'-'||v.VName from Village v inner join Cluster c on v.dcode=c.dcode and v.upcode=c.upcode and v.uncode=c.uncode and v.vcode=c.vcode and c.cluster='"+ spnCluster.getSelectedItem().toString().split("-")[0] +"'" +
                        " where v.DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"' and v.UPCode='"+ spnUPCode.getSelectedItem().toString().split("-")[0] +"' and v.UNCode='"+ spnUNCode.getSelectedItem().toString().split("-")[0] +"'"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        ImageButton cmdBack = (ImageButton) dialog.findViewById(R.id.cmdBack);
        cmdBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }});

        Button cmdHHListing = (Button)dialog.findViewById(R.id.cmdHHListing);
        cmdHHListing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(spnDCode.getSelectedItemPosition()==0){
                    Connection.MessageBox(thiscontext,"তালিকা থেকে সঠিক জেলার নাম সিলেক্ট করুন");
                    return;
                }else if(spnUPCode.getSelectedItemPosition()==0){
                    Connection.MessageBox(thiscontext,"তালিকা থেকে সঠিক উপজেলার নাম সিলেক্ট করুন");
                    return;
                }else if(spnUNCode.getSelectedItemPosition()==0){
                    Connection.MessageBox(thiscontext,"তালিকা থেকে সঠিক ইউনিয়ন এর  নাম সিলেক্ট করুন");
                    return;
                }else if(spnCluster.getSelectedItemPosition()==0){
                    Connection.MessageBox(thiscontext,"তালিকা থেকে সঠিক ক্লাস্টার এর নম্বর সিলেক্ট করুন");
                    return;
                }
                else if(spnVCode.getSelectedItemPosition()==0){
                    Connection.MessageBox(thiscontext,"তালিকা থেকে সঠিক গ্রামের নাম সিলেক্ট করুন");
                    return;
                }

                AlertDialog.Builder adb = new AlertDialog.Builder(thiscontext);
                adb.setTitle("নিশ্চিত করুন");
                adb.setMessage("আপনি যেই গ্রাম সিলেক্ট করেছেন সেটা সঠিক কিনা পুনরায় নিশ্চিত হন এবং হ্যাঁ বাটন সিলেক্ট করুন অন্যথায় না সিলেক্ট করুন। ");
                adb.setNegativeButton("না", null);
                adb.setPositiveButton("হ্যাঁ", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Bundle Ibundle = new Bundle();

                        Ibundle.putString("DCode", spnDCode.getSelectedItem().toString().split("-")[0]);
                        Ibundle.putString("DName", spnDCode.getSelectedItem().toString().split("-")[1]);
                        Ibundle.putString("UPCode", spnUPCode.getSelectedItem().toString().split("-")[0]);
                        Ibundle.putString("UPName", spnUPCode.getSelectedItem().toString().split("-")[1]);
                        Ibundle.putString("UNCode", spnUNCode.getSelectedItem().toString().split("-")[0]);
                        Ibundle.putString("UNName", spnUNCode.getSelectedItem().toString().split("-")[1]);
                        Ibundle.putString("Cluster", spnCluster.getSelectedItem().toString());
                        Ibundle.putString("VCode", spnVCode.getSelectedItem().toString().split("-")[0]);
                        Ibundle.putString("VName", spnVCode.getSelectedItem().toString().split("-")[1]);

                        //Intent intent = new Intent(thiscontext, Household_list.class);
                        //intent.putExtras(Ibundle);
                        //startActivity(intent);

                        dialog.dismiss();
                    }});
                adb.show();
            }
        });

        dialog.show();
    }

    private void SelectVillageForm_Mapping()
    {
        final Dialog dialog = new Dialog(thiscontext);
        dialog.setContentView(R.layout.select_village);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.TOP;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        final Spinner spnDCode;
        final Spinner spnUPCode;
        final Spinner spnUNCode;
        final Spinner spnCluster;
        final Spinner spnVCode;
        spnDCode=(Spinner)dialog.findViewById(R.id.spnDCode);
        spnUPCode=(Spinner)dialog.findViewById(R.id.spnUPCode) ;
        spnUNCode=(Spinner)dialog.findViewById(R.id.spnUNCode) ;
        spnCluster=(Spinner)dialog.findViewById(R.id.spnCluster) ;
        spnVCode=(Spinner)dialog.findViewById(R.id.spnVCode) ;
        spnDCode.setAdapter(C.getArrayAdapter("Select '' union Select DCode||'-'||DName from zilla"));
        spnUPCode.setAdapter(C.getArrayAdapter("Select '' union Select UPCode||'-'||UPName from Upazila where DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"'"));
        spnUNCode.setAdapter(C.getArrayAdapter("Select '' union Select UNCode||'-'||UNName from Unions where DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"' and UPCode='"+ spnUPCode.getSelectedItem().toString().split("-")[0] +"'"));
        spnCluster.setAdapter(C.getArrayAdapter("Select '' union Select '000' union Select Cluster from Cluster where DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"' and UPCode='"+ spnUPCode.getSelectedItem().toString().split("-")[0] +"' and UNCode='"+ spnUNCode.getSelectedItem().toString().split("-")[0] +"'"));
        spnVCode.setAdapter(C.getArrayAdapter("Select '' union select v.vcode||'-'||v.VName from Village v inner join Cluster c on v.dcode=c.dcode and v.upcode=c.upcode and v.uncode=c.uncode and v.vcode=c.vcode and c.cluster='"+ spnCluster.getSelectedItem().toString().split("-")[0] +"'" +
                " where v.DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"' and v.UPCode='"+ spnUPCode.getSelectedItem().toString().split("-")[0] +"' and v.UNCode='"+ spnUNCode.getSelectedItem().toString().split("-")[0] +"'"));

        spnDCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spnUPCode.setAdapter(C.getArrayAdapter("Select '' union Select UPCode||'-'||UPName from Upazila where DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"'"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spnUPCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spnUNCode.setAdapter(C.getArrayAdapter("Select '' union Select UNCode||'-'||UNName from Unions where DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"' and UPCode='"+ spnUPCode.getSelectedItem().toString().split("-")[0] +"'"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        spnUNCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spnCluster.setAdapter(C.getArrayAdapter("Select '' union Select '000' union Select Cluster from Cluster where DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"' and UPCode='"+ spnUPCode.getSelectedItem().toString().split("-")[0] +"' and UNCode='"+ spnUNCode.getSelectedItem().toString().split("-")[0] +"'"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spnCluster.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spnVCode.setAdapter(C.getArrayAdapter("Select '' union select v.vcode||'-'||v.VName from Village v inner join Cluster c on v.dcode=c.dcode and v.upcode=c.upcode and v.uncode=c.uncode and v.vcode=c.vcode and c.cluster='"+ spnCluster.getSelectedItem().toString().split("-")[0] +"'" +
                        " where v.DCode='"+ spnDCode.getSelectedItem().toString().split("-")[0] +"' and v.UPCode='"+ spnUPCode.getSelectedItem().toString().split("-")[0] +"' and v.UNCode='"+ spnUNCode.getSelectedItem().toString().split("-")[0] +"'"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        ImageButton cmdBack = (ImageButton) dialog.findViewById(R.id.cmdBack);
        cmdBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }});

        Button cmdHHListing = (Button)dialog.findViewById(R.id.cmdHHListing);
        cmdHHListing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(spnDCode.getSelectedItemPosition()==0){
                    Connection.MessageBox(thiscontext,"তালিকা থেকে সঠিক জেলার নাম সিলেক্ট করুন");
                    return;
                }else if(spnUPCode.getSelectedItemPosition()==0){
                    Connection.MessageBox(thiscontext,"তালিকা থেকে সঠিক উপজেলার নাম সিলেক্ট করুন");
                    return;
                }else if(spnUNCode.getSelectedItemPosition()==0){
                    Connection.MessageBox(thiscontext,"তালিকা থেকে সঠিক ইউনিয়ন এর  নাম সিলেক্ট করুন");
                    return;
                }else if(spnCluster.getSelectedItemPosition()==0){
                    Connection.MessageBox(thiscontext,"তালিকা থেকে সঠিক ক্লাস্টার এর নম্বর সিলেক্ট করুন");
                    return;
                }
                else if(spnVCode.getSelectedItemPosition()==0){
                    Connection.MessageBox(thiscontext,"তালিকা থেকে সঠিক গ্রামের নাম সিলেক্ট করুন");
                    return;
                }

                AlertDialog.Builder adb = new AlertDialog.Builder(thiscontext);
                adb.setTitle("নিশ্চিত করুন");
                adb.setMessage("আপনি যেই গ্রাম সিলেক্ট করেছেন সেটা সঠিক কিনা পুনরায় নিশ্চিত হন এবং হ্যাঁ বাটন সিলেক্ট করুন অন্যথায় না সিলেক্ট করুন। ");
                adb.setNegativeButton("না", null);
                adb.setPositiveButton("হ্যাঁ", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Bundle Ibundle = new Bundle();

                        Ibundle.putString("DCode", spnDCode.getSelectedItem().toString().split("-")[0]);
                        Ibundle.putString("DName", spnDCode.getSelectedItem().toString().split("-")[1]);
                        Ibundle.putString("UPCode", spnUPCode.getSelectedItem().toString().split("-")[0]);
                        Ibundle.putString("UPName", spnUPCode.getSelectedItem().toString().split("-")[1]);
                        Ibundle.putString("UNCode", spnUNCode.getSelectedItem().toString().split("-")[0]);
                        Ibundle.putString("UNName", spnUNCode.getSelectedItem().toString().split("-")[1]);
                        Ibundle.putString("Cluster", spnCluster.getSelectedItem().toString());
                        Ibundle.putString("VCode", spnVCode.getSelectedItem().toString().split("-")[0]);
                        Ibundle.putString("VName", spnVCode.getSelectedItem().toString().split("-")[1]);

                        //Intent intent = new Intent(thiscontext, Mapping_Household_list.class);
                        //intent.putExtras(Ibundle);
                        //startActivity(intent);

                        dialog.dismiss();
                    }});
                adb.show();
            }
        });

        dialog.show();
    }
}
