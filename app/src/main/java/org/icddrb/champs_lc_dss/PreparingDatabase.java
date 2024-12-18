package org.icddrb.champs_lc_dss;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.icddrb.champs_lc_dss.adapter.CustomSpinnerAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;
import Common.ProjectSetting;
import Utility.CompressZip;

public class PreparingDatabase extends AppCompatActivity {
    Connection C;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preparing_database);
        Activity_Load();
    }

    public static final String SECURITY_TAG = "Security Permission";
    private static final int REQUEST_Code = 0;
    private static final String[] PERMISSIONS_LIST = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA};

    boolean networkAvailable = false;
    TextView lblMessage;
    TextView tv_subtitle;
    TextView tv_subtitle1;
    Spinner userId;
    Button btnTryAgain;
    private void Activity_Load()
    {
        C = new Connection(this);
        lblMessage = findViewById(R.id.lblMessage);
        tv_subtitle = findViewById(R.id.tv_subtitle);
        tv_subtitle1 = findViewById(R.id.tv_subtitle1);
        tv_subtitle.setText(ProjectSetting.SITE_NAME.length()>0?ProjectSetting.COUNTRY_NAME+"-"+ProjectSetting.SITE_NAME:ProjectSetting.COUNTRY_NAME);
        tv_subtitle1.setText(ProjectSetting.LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME);
        tv_subtitle1.setVisibility(View.VISIBLE);
        btnTryAgain = findViewById(R.id.btnTryAgain);
        userId = findViewById(R.id.userId);

        networkAvailable = Connection.haveNetworkConnection(PreparingDatabase.this);
        if(!networkAvailable){
            lblMessage.setText("Internet connection is not available for initial database creation.\nPlease try again with internet connection.");
            lblMessage.setVisibility(View.VISIBLE);
            btnTryAgain.setText("Try Again");
        }else{
            SpinnerItem(userId);
            btnTryAgain.setText("Complete Device Setting");
        }


        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(btnTryAgain.getText().equals("Try Again")){
                    networkAvailable = Connection.haveNetworkConnection(PreparingDatabase.this);
                    if(!networkAvailable){
                        lblMessage.setText("Internet connection is not available for initial database creation.\nPlease try again with internet connection.");
                        lblMessage.setVisibility(View.VISIBLE);
                        btnTryAgain.setText("Try Again");
                    }else{
                        SpinnerItem(userId);

                        btnTryAgain.setText("Complete Device Setting");
                        lblMessage.setText("");
                        lblMessage.setVisibility(View.GONE);
                        userId.requestFocus();
                    }

                }else {
                    ProcessDatabase(userId.getSelectedItem().toString().split("-")[0]);
                }
            }});

        //ProcessDatabase();
    }

    private void ProcessDatabase(String UserId)
    {
        String zipFile_URL = ProjectSetting.Database_Folder_URL + File.separator + ProjectSetting.zipDatabaseName;
        String dbFile_URL = ProjectSetting.Database_Folder_URL + File.separator + ProjectSetting.DatabaseName;
        networkAvailable = Connection.haveNetworkConnection(PreparingDatabase.this);
        if (new File(zipFile_URL).exists()) {
            File dbfile = new File(dbFile_URL);
            if (dbfile.exists()) {
                Boolean.valueOf(dbfile.delete());
            }
            CompressZip decompressZip = new CompressZip();
            StringBuilder sb = new StringBuilder();
            sb.append(Environment.getExternalStorageDirectory());
            sb.append(File.separator);
            sb.append(ProjectSetting.DatabaseFolder);
            decompressZip.unzipDB(zipFile_URL, sb.toString());
        } else {
            lblMessage.setVisibility(View.GONE);
            btnTryAgain.setVisibility(View.GONE);
            if (this.networkAvailable) {
                String DeviceID = C.ReturnResult("ReturnSingleValue","sp_Request_DeviceID '"+ ProjectSetting.LISTING_OR_BASELINE_OR_SURVEILLANCE_NAME + "-" + C.getDeviceUniqueID(this) +"',''");
                String Setting = C.ReturnResult("Existence", "Select DeviceId from DeviceList where DeviceId='"+ DeviceID +"' and Setting='3' and Active='1'");
                if (Setting.equals("2")) {
                    lblMessage.setText("Device ID :"+ DeviceID +" is not allowed to configure in mobile device, please contact with administrator.");
                    lblMessage.setVisibility(View.VISIBLE);
                    btnTryAgain.setVisibility(View.VISIBLE);
                    return;
                }

                RebuildDatabase(DeviceID, UserId);

            }else {
                lblMessage.setText("Internet connection is not available for initial database creation.");
                lblMessage.setVisibility(View.VISIBLE);
                btnTryAgain.setText("Try Again");
            }
        }
    }

    private void SpinnerItem(Spinner SpinnerName)
    {
        String SQL = "";
        if(ProjectSetting.LISTING_OR_BASELINE_OR_SURVEILLANCE.equals(ProjectSetting.LISTING)){
            SQL = "Select UserId+'-'+UserName from DataCollector where WorkGroup=1 and Active='1'";
        }else if(ProjectSetting.LISTING_OR_BASELINE_OR_SURVEILLANCE.equals(ProjectSetting.BASELINE)){
            SQL = "Select UserId+'-'+UserName from DataCollector where WorkGroup=2 and Active='1'";
        }else{
            SQL = "Select UserId+'-'+UserName from DataCollector where WorkGroup=3 and Active='1'";
        }

        List<String> listItem = new ArrayList<String>();
        listItem = C.DataListJSON(SQL);
        SpinnerName.setAdapter(new CustomSpinnerAdapter(this,new ArrayList<>(listItem)));
    }

    ProgressDialog progDailog;
    private void RebuildDatabase(String DeviceID, String UserId)
    {
        lblMessage.setVisibility(View.GONE);
        btnTryAgain.setVisibility(View.GONE);
        progDailog = new ProgressDialog(PreparingDatabase.this);
        progDailog.setTitle("Preparing database");
        progDailog.setMessage("Please Wait . . .");
        progDailog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progDailog.setIndeterminate(false);
        progDailog.setCancelable(false);
        progDailog.setIcon(R.drawable.favicon);
        progDailog.setProgress(0);
        progDailog.show();

        new Thread() {
            public void run() {
                try {
                    if(C.RebuildDatabase(progDailog, progressHandler, DeviceID, UserId)){
                        progDailog.dismiss();
                        finish();
                        Intent mainIntent = new Intent(PreparingDatabase.this, LoginActivity.class);
                        startActivity(mainIntent);
                    }else{
                        progDailog.dismiss();
                        lblMessage.setText("Process execution failed. Please try again with internet connection.");
                        lblMessage.setVisibility(View.VISIBLE);
                        btnTryAgain.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {

                }
            }
        }.start();


    }

    @SuppressLint("HandlerLeak")
    Handler progressHandler = new Handler() {
        public void handleMessage(Message msg) {
            progDailog.setMessage(Global.getInstance().getProgressMessage());
            progDailog.incrementProgressBy(0);
        }
    };


}