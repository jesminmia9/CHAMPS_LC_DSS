package org.icddrb.champs_lc_dss;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import Common.Connection;
import Common.Global;
import Common.ProjectSetting;
import Utility.MySharedPreferences;

public class LoginActivity extends AppCompatActivity {
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    Connection C;
    boolean networkAvailable = false;
    int count = 0;
    TextView lblStaffType;
    TextView tv_subtitle1;
    String SystemUpdateDT = "";
    private ProgressDialog dialog;
    private String Password = "";
    MySharedPreferences sp;
    TextView Country;
    TextView Facility;
    TextView tv_subtitle;
    EditText pass;
    LinearLayout secDevelopmentVersion;
    TextView UniqueUserId;
    Spinner uid;
    //EditText pass;
    Spinner spnLanguage;
    TextView lblSystemDate;
    TextView lblSystemName;
    RelativeLayout ll_progressBar;

    @Override
    public boolean onKeyDown(int iKeyCode, KeyEvent event) {
        if (iKeyCode == KeyEvent.KEYCODE_BACK || iKeyCode == KeyEvent.KEYCODE_HOME) {
            AlertDialog.Builder adb = new AlertDialog.Builder(LoginActivity.this);
            adb.setTitle("Close");
            adb.setMessage("Do you want to exit from the system?");
            adb.setNegativeButton("No", null);
            adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();
                }
            });
            adb.show();
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.login_activity);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            //SQLiteDatabase.loadLibs(this);
            C = new Connection(this);
            sp = new MySharedPreferences();
            sp.save(this, "deviceid", "");
            sp.save(this, "userid", "");
            sp.save(this, "languageid", "0");
            sp.save(this, "lat", "");
            sp.save(this, "lon", "");
            sp.save(this, "bdbrequest", "");
            sp.save(this, "userrole", "1");

            UniqueUserId = (TextView) findViewById(R.id.UniqueUserId);
            uid = (Spinner) findViewById(R.id.userId);
            pass = (EditText) findViewById(R.id.pass);
            spnLanguage = (Spinner) findViewById(R.id.spnLanguage);
            lblSystemDate = (TextView) findViewById(R.id.lblSystemDate);
            lblSystemName = (TextView) findViewById(R.id.lblSystemName);
            ll_progressBar = findViewById(R.id.ll_progressBar);
            ll_progressBar.setVisibility(View.GONE);

            TextView tv_subtitle = findViewById(R.id.tv_subtitle);
            tv_subtitle.setText(ProjectSetting.SITE_NAME.length() > 0 ? ProjectSetting.COUNTRY_NAME + "-" + ProjectSetting.SITE_NAME : ProjectSetting.COUNTRY_NAME);
            TextView tv_subtitle1 = findViewById(R.id.tv_subtitle1);
            tv_subtitle1.setText(ProjectSetting.LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME);
            tv_subtitle1.setVisibility(View.VISIBLE);

            secDevelopmentVersion = findViewById(R.id.secDevelopmentVersion);
            if (ProjectSetting.DSS_SYSTEM_FOR.equals((ProjectSetting.DEVELOPMENT))) {
                secDevelopmentVersion.setVisibility(View.VISIBLE);
                lblSystemName.setText("Development");
            }
            else if (ProjectSetting.DSS_SYSTEM_FOR.equals((ProjectSetting.TRAINING))) {
                secDevelopmentVersion.setVisibility(View.VISIBLE);
                lblSystemName.setText("Training");
            }
            else if (ProjectSetting.DSS_SYSTEM_FOR.equals((ProjectSetting.MAIN_DATA_COLLECTION))) {
                secDevelopmentVersion.setVisibility(View.VISIBLE);
                lblSystemName.setText("Production");
                lblSystemName.setTextColor(Color.parseColor("#4A9C2D"));
            }
            else {
                secDevelopmentVersion.setVisibility(View.GONE);
            }

            Activity_Load();

        } catch (Exception ex) {
            Connection.MessageBox(LoginActivity.this, ex.getMessage());
        }
    }

    private void Activity_Load() {
        try {


            SystemUpdateDT = ProjectSetting.VersionDate;
            lblSystemDate.setText("Version: 1.0, Built on: " + SystemUpdateDT);

            //Check for Internet connectivity
            networkAvailable = Connection.haveNetworkConnection(LoginActivity.this);

            //Device Unique ID
            final String UniqueID = C.ReturnSingleValue("Select DeviceId from DeviceList");
            UniqueUserId.setText("Unique ID :" + UniqueID);
            sp.save(this, "deviceid", UniqueID);


            uid.setAdapter(C.getArrayAdapter("select UserId||'-'||UserName User from DataCollector order by UserName"));
            spnLanguage.setAdapter(C.getArrayAdapter("select language_id||'-'||language_name from Language order by language_id"));
            spnLanguage.setSelection(Global.SpinnerItemPositionAnyLength(spnLanguage,ProjectSetting.COUNTRY_CODE));
            /*try {
                C.CreateTable("process_tab", "Create table process_tab(process_id int)");
                String Resp = "";
                if (!C.Existence("Select * from process_tab where process_id=1")) {
                    C.SaveData("Drop Table MemberMove");
                    C.CreateTable("MemberMove", "Create table MemberMove(MemID varchar (40),HHID varchar (40),MSlNo varchar (2),Active varchar (50),DSSID numeric(20,0),MEnType varchar (2),MEnDate datetime,MExType varchar (2),MExDate datetime,Rnd varchar (2),MemNote varchar (300),isdelete int,StartTime varchar (5),EndTime varchar (5),DeviceID varchar (10),EntryUser varchar (10),Lat varchar (20),Lon varchar (20),EnDt datetime,Upload int,modifyDate datetime,Constraint pk_MemberMove Primary Key(MemID,HHID,MSlNo))");
                    if (Resp.length() == 0)
                        C.SaveData("Insert into process_tab(process_id)values(1)");
                }
            } catch (Exception ex) {

            }*/

            //stop: 04 jun 2024
            /*if (networkAvailable) {
                new DataLoadAsyncTask().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
            }*/

            //Login -----------------------------------------------------------------------
            Button loginButton = findViewById(R.id.btnLogin);
            loginButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!C.Existence("select Approval from DeviceList where DeviceID='" + UniqueID + "' and Approval='1'")) {
                        Connection.MessageBox(LoginActivity.this, "Device ID: " + UniqueID + " is not currently authorized to access the system.");
                        return;
                    } else if (!C.Existence("select Active from DataCollector where UserId='" + Connection.split(uid.getSelectedItem().toString(), '-')[0] + "' and Active='1'")) {
                        Connection.MessageBox(LoginActivity.this, "Data Collector " + Connection.split(uid.getSelectedItem().toString(), '-')[1] + " is currently inactive. \nPlease contact with supervisors.");
                        return;
                    }
                    try {
                        String[] U = Connection.split(uid.getSelectedItem().toString(), '-');
                        String[] L = Connection.split(spnLanguage.getSelectedItem().toString(), '-');
                        sp.save(LoginActivity.this, "userid", U[0]);
                        sp.save(LoginActivity.this, "username", U[1]);
                        sp.save(LoginActivity.this, "languageid", L[0]);

                        if (!C.Existence("Select * from DataCollector where UserId='" + U[0] + "' and Pass='" + pass.getText().toString() + "'")) {
                            Connection.MessageBox(LoginActivity.this, "This is not a valid user id or password");
                            return;
                        }else{
                            //User Role
                            sp.save(LoginActivity.this, "userrole", C.ReturnSingleValue("Select ifnull(UserRole,'3')UserRole from DataCollector where UserId='"+ U[0] +"'"));
                            //sp.save(LoginActivity.this, "userrole", "2");
                        }

                        String UserRole = C.ReturnSingleValue("Select UserRole from DataCollector where UserId='" + U[0] + "'");
                        if (Global.isNullOrEmpty(UserRole)) {
                            Connection.MessageBox(LoginActivity.this, "User role not defined for this user!!");
                            return;
                        }

                        //Data Update Process: 30-09-2024
                        //==========================================================================
                        if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1)||
                            ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1)||
                            ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2)) {

                            String start_date = "";
                            List<String> tableList_upload = Collections.emptyList();
                            if(ProjectSetting.SITE_CODE.equals(ProjectSetting.MALI_SITE1))  {
                                start_date = "2024-09-18";
                                tableList_upload = ProjectSetting.Surveillance_Upload();
                            }
                            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE1))  {
                                start_date = "2024-08-18";
                                tableList_upload = ProjectSetting.Surveillance_Upload();
                            }
                            else if(ProjectSetting.SITE_CODE.equals(ProjectSetting.NIGERIA_SITE2))  {
                                start_date = "2024-09-18";
                                tableList_upload = ProjectSetting.Baseline_Upload();
                            }

                            try {
                                if (!C.TableExists("ChangeUploadStatFlagCM")) {
                                    C.CreateTable("ChangeUploadStatFlagCM", "Create table ChangeUploadStatFlagCM(deviceid varchar(10) Primary Key, changeStat varchar(2))");
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("deviceid", UniqueID);
                                    contentValues.put("changeStat", "2");
                                    C.InsertData("ChangeUploadStatFlagCM", contentValues);
                                }

                                if (C.ReturnSingleValue("Select changeStat from ChangeUploadStatFlagCM").equalsIgnoreCase("2")) {

                                    for (int i = 0; i < tableList_upload.size(); i++) {
                                        if(tableList_upload.get(i).equalsIgnoreCase("cleaning_household")){
                                            try {
                                                C.SaveData("Update " + tableList_upload.get(i) + " set upload='2'");
                                            } catch (Exception ignored) {

                                            }
                                        }
                                        else {
                                            try {
                                                C.SaveData("Update " + tableList_upload.get(i) + " set upload='2' where DATE(modifyDate) >='"+ start_date + "'");
                                            } catch (Exception ignored) {

                                            }
                                        }

                                    }

                                    C.SaveData("Update ChangeUploadStatFlagCM set changeStat='1'");
                                }
                            } catch (Exception ignored) {

                            }
                        }
                        //==========================================================================


                        //Download Updated System
                        //...................................................................................
                        if (networkAvailable) {
                            //Retrieve data from server for checking local device
                            String[] ServerVal = Connection.split(C.ReturnResult("ReturnSingleValue", "sp_ServerCheck_v1 '" + UniqueID + "','"+ ProjectSetting.VersionDate +"'"), ',');
                            String ServerDate = ServerVal[0];
                            String UpdateDT = ServerVal[1];

                            //Check for New Version
                            if (!UpdateDT.equals(SystemUpdateDT)) {
                                SystemDownload d = new SystemDownload();
                                d.setContext(getApplicationContext());
                                d.execute(ProjectSetting.UpdatedSystem);
                            } else {
                                //check for system date
                                /*if (!ServerDate.equals(Global.TodaysDateforCheck())) {
                                    Connection.MessageBox(LoginActivity.this, "System date is incorrect [" + Global.DateNowDMY() + "]");
                                    startActivity(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS));
                                    return;
                                }*/

                                final ProgressDialog progDailog = ProgressDialog.show(LoginActivity.this, "", "Please Wait . . .", true);

                                new Thread() {
                                    public void run() {
                                        try {
                                            finishAffinity();
                                            Intent f1 = new Intent(getApplicationContext(), Fragment_Main.class);
                                            startActivity(f1);
                                        } catch (Exception e) {

                                        }
                                        progDailog.dismiss();
                                    }
                                }.start();
                            }
                        } else {
                            final ProgressDialog progDailog = ProgressDialog.show(LoginActivity.this, "", "Please Wait . . .", true);

                            new Thread() {
                                public void run() {
                                    try {
                                        finishAffinity();
                                        Intent f1 = new Intent(getApplicationContext(), Fragment_Main.class);
                                        startActivity(f1);
                                    } catch (Exception e) {

                                    }
                                    progDailog.dismiss();
                                }
                            }.start();
                        }
                    } catch (Exception ex) {
                        final ProgressDialog progDailog = ProgressDialog.show(LoginActivity.this, "", "Please Wait . . .", true);

                        new Thread() {
                            public void run() {
                                try {
                                    finishAffinity();
                                    Intent f1 = new Intent(getApplicationContext(), Fragment_Main.class);
                                    startActivity(f1);
                                } catch (Exception e) {

                                }
                                progDailog.dismiss();
                            }
                        }.start();
                    }
                }
            });
        } catch (Exception ex) {
            Connection.MessageBox(LoginActivity.this, ex.getMessage());
            ex.printStackTrace();
        }
    }


    //Install application
    private void InstallApplication() {
        File apkfile = new File(Environment.getExternalStorageDirectory() + File.separator + ProjectSetting.NewVersionName + ".apk");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            /*Uri apkuri = FileProvider.getUriForFile(LoginActivity.this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    apkfile);*/
            Uri apkuri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                    Objects.requireNonNull(getApplicationContext()).getPackageName() + ".provider", apkfile);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            intent.setDataAndType(apkuri, "application/vnd.android.package-archive");

            startActivity(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            intent.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");

            startActivity(intent);
        }
    }

    //Downloading updated system from the central server
    class SystemDownload extends AsyncTask<String, String, Void> {
        private Context context;

        public void setContext(Context contextf) {
            context = contextf;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(LoginActivity.this);
            dialog.setMessage("Downloading Updated System...");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setCancelable(false);
            dialog.show();
        }


        protected void onProgressUpdate(String... progress) {
            dialog.setProgress(Integer.parseInt(progress[0]));
            //publishProgress(progress);

        }

        //@Override
        protected void onPostExecute(String unused) {
            dialog.dismiss();
        }


        @Override
        protected Void doInBackground(String... arg0) {
            try {
                URL url = new URL(arg0[0]);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.connect();
                int lenghtOfFile = c.getContentLength();

                File file = Environment.getExternalStorageDirectory();

                file.mkdirs();
                File outputFile = new File(file.getAbsolutePath() + File.separator + ProjectSetting.NewVersionName + ".apk");

                if (outputFile.exists()) {
                    outputFile.delete();
                } else {
                    outputFile.createNewFile();
                }

                FileOutputStream fos = new FileOutputStream(outputFile);

                InputStream is = c.getInputStream();


                byte[] buffer = new byte[1024];
                int len1 = 0;
                long total = 0;
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                    count++;
                }
                fos.close();
                is.close();

                InstallApplication();

                dialog.dismiss();

            } catch (IOException e) {
                //Log.e("UpdateAPP", "Update error! " + e.getMessage());
            }
            return null;
        }
    }

    private class DataLoadAsyncTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        private Context context;
        String resp = "";

        public void setContext(Context contextf) {
            context = contextf;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*dialog = new ProgressDialog(LoginActivity.this);
            dialog.setTitle("Loading");
            //dialog.setMessage("Data download is in Progress, Please wait ...");
            //dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setCancelable(false);
            dialog.setIcon(R.drawable.data_sync);
            dialog.show();*/
            ll_progressBar.setVisibility(View.VISIBLE);

        }

        //@Override
        protected void onProgressUpdate(String... values) {
            // dialog.setProgress(Integer.parseInt(values[0].toString().split(",")[1]));
            //dialog.setProgress(Integer.parseInt(values[0].toString()));
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                new Thread() {
                    public void run() {
                        try {
                            int count = 50;
                            //Stop Sync Service if still running
                            //----------------------------------------------------------------------
                            /*if (isServiceRunning(Sync_Service.class)) {
                                LoginActivity.this.stopService(new Intent(LoginActivity.this, Sync_Service.class));
                            }*/

                            //Update database level change
                            //----------------------------------------------------------------------
                            try {
                                onProgressUpdate(String.valueOf(count));
                                C.Sync_Download("DataCollector", "DataCollector", "WorkGroup='" + ProjectSetting.Get_WorkGroup() + "' and UserId='" + uid.getSelectedItem().toString().split("-")[0] + "'");
                                count = 100;
                                onProgressUpdate(String.valueOf(count));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            try {
                                /*count = 100;
                                onProgressUpdate(String.valueOf(count));*/
                                C.Sync_Download("DataCollector_AssignArea", "DataCollector_AssignArea", "UserId='" + uid.getSelectedItem().toString().split("-")[0] + "'");
                            } catch (Exception ignored) {
                            }

                            //dialog.dismiss();
                            ll_progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {
                            resp = e.getMessage();
                            //dialog.dismiss();
                            ll_progressBar.setVisibility(View.GONE);
                        } finally {
                            //dialog.dismiss();
                            ll_progressBar.setVisibility(View.GONE);
                        }
                    }
                }.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
            // do stuff!
            return "done";
        }

        // -- called if the cancel button is pressed
        @Override
        protected void onCancelled() {
            super.onCancelled();
            ll_progressBar.setVisibility(View.GONE);
        }

        //@Override
        protected void onPostExecute(String result) {
            if (result.length() != 0) {
                //Connection.MessageBox(LoginActivity.this, "Data Sync successfully completed.");
                uid.setAdapter(C.getArrayAdapter("select UserId||'-'||UserName User from DataCollector order by UserName"));

                //dialog.dismiss();

            }
            ll_progressBar.setVisibility(View.GONE);
        }
    }
}