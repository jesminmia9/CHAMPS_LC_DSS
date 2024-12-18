package forms_activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.icddrb.champs_lc_dss.R;

import java.util.List;

import Common.Connection;
import Utility.MySharedPreferences;
import forms_datamodel.tmpAbortion_DataModel;
import forms_datamodel.tmpLiveBirth_DataModel;
import forms_datamodel.tmpStillBirth_DataModel;

public class Surv_delivery_module extends AppCompatActivity {

    Connection C;
    MySharedPreferences sp;
    Bundle IDbundle;
     String DELIVERYID = "";
     String MEM_ID = "";
     String HHID = "";
     String HHNO = "";
     String EV_TYPE = "";
     String ROUND = "";
     String DOD = "";
     String LB = "";
     String SB = "";
     String MISC = "";
     String AB = "";
     String MEMNAME = "";
     String TOTAL = "";
     String TOD = "";
     String EDIT = "";

    ColorStateList def;
    TextView memname, memdod, memtotaloutcome, lblMiscarriage, lblLiveBirth, lblStillBirth, lblAbortion;
    TextView select;
    LinearLayout tab1, tab2, tab3, tab4;
    Button lbc1, lbc2, lbc3, lbc4, lbc5, lbc6, lbc7, mc1, mc2, mc3, mc4, mc5, mc6, mc7, sbc1, sbc2, sbc3, sbc4, sbc5, sbc6, sbc7, abc1, abc2, abc3, abc4, abc5, abc6, abc7;

    List<tmpLiveBirth_DataModel> liveBirthDataModels;
    List<tmpStillBirth_DataModel> stillBirthDataModelList;
    List<tmpAbortion_DataModel> abortionDataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surv_delivery_module);

        C = new Connection(this);
        sp = new MySharedPreferences();
        IDbundle = getIntent().getExtras();
        DELIVERYID = IDbundle.getString("DeliveryID");
        MEM_ID = IDbundle.getString("MemID");
        HHID = IDbundle.getString("HHID");
        HHNO = IDbundle.getString("HHNO");
        EV_TYPE = IDbundle.getString("evtype");
        ROUND = IDbundle.getString("round");
        DOD = IDbundle.getString("dod");
        TOD = IDbundle.getString("tod");
        LB = IDbundle.getString("lb");
        SB = IDbundle.getString("sb");
        MISC = IDbundle.getString("misc");
        AB = IDbundle.getString("ab");
        TOTAL = IDbundle.getString("total");
        EDIT = IDbundle.getString("edit");

        String str[] = DOD.split("/");
        int day = Integer.parseInt(str[0]);
        String month = str[1];
        int year = Integer.parseInt(str[2]);

        ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
        cmdBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (!C.ReturnSingleValue("select count(*) from tmpMember where BDate_D='" + day + "' and BDate_M='" + month + "' and BDate_Y='" + year + "'").equals(LB)) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(Surv_delivery_module.this);
                    adb.setTitle("Close");
                    adb.setIcon(R.drawable.favicon);
                    adb.setMessage("Events are not completed yet!!!\nDo you still want to close this form[Yes/No]?");
                    adb.setNegativeButton("No", null);
                    adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    adb.show();
                    return;
                }
                AlertDialog.Builder adb = new AlertDialog.Builder(Surv_delivery_module.this);
                adb.setTitle("Close");
                adb.setIcon(R.drawable.favicon);
                adb.setMessage("Do you want to close this form[Yes/No]?");
                adb.setNegativeButton("No", null);
                adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sp.save(Surv_delivery_module.this, "ab1", "");
                        sp.save(Surv_delivery_module.this, "child1", "");
                        sp.save(Surv_delivery_module.this, "child2", "");
                        sp.save(Surv_delivery_module.this, "child3", "");
                        sp.save(Surv_delivery_module.this, "child4", "");
                        sp.save(Surv_delivery_module.this, "child5", "");
                        sp.save(Surv_delivery_module.this, "child6", "");
                        sp.save(Surv_delivery_module.this, "child7", "");

                        //tmpStillBirth
                        sp.save(Surv_delivery_module.this, "stl1", "");
                        sp.save(Surv_delivery_module.this, "stl2", "");
                        sp.save(Surv_delivery_module.this, "stl3", "");
                        sp.save(Surv_delivery_module.this, "stl4", "");
                        sp.save(Surv_delivery_module.this, "stl5", "");
                        sp.save(Surv_delivery_module.this, "stl6", "");
                        sp.save(Surv_delivery_module.this, "stl7", "");
                        finish();
                    }
                });
                adb.show();
            }
        });

        // Initialization
        memname = findViewById(R.id.memname);
        memdod = findViewById(R.id.memdod);
        memtotaloutcome = findViewById(R.id.memtotaloutcome);
        lblMiscarriage = findViewById(R.id.lblMiscarriage);
        lblLiveBirth = findViewById(R.id.lblLiveBirth);
        lblStillBirth = findViewById(R.id.lblStillBirth);
        lblAbortion = findViewById(R.id.lblAbortion);

        MEMNAME = C.ReturnSingleValue("Select Name from tmpMember where MemID = '" + MEM_ID + "'");
        memname.setText(MEMNAME);
        memdod.setText("Delivery Date: " + DOD);
        memtotaloutcome.setText("Total Outcome: " + TOTAL);

        //select = findViewById(R.id.select);
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        tab4 = findViewById(R.id.tab4);
        lbc1 = findViewById(R.id.lbc1);
        lbc2 = findViewById(R.id.lbc2);
        lbc3 = findViewById(R.id.lbc3);
        lbc4 = findViewById(R.id.lbc4);
        lbc5 = findViewById(R.id.lbc5);
        lbc6 = findViewById(R.id.lbc6);
        lbc7 = findViewById(R.id.lbc7);

        mc1 = findViewById(R.id.mc1);
        mc2 = findViewById(R.id.mc2);
        mc3 = findViewById(R.id.mc3);
        mc4 = findViewById(R.id.mc4);
        mc5 = findViewById(R.id.mc5);
        mc6 = findViewById(R.id.mc6);
        mc7 = findViewById(R.id.mc7);
        mc1.setEnabled(false);
        mc2.setEnabled(false);
        mc3.setEnabled(false);
        mc4.setEnabled(false);
        mc5.setEnabled(false);
        mc6.setEnabled(false);
        mc7.setEnabled(false);

        sbc1 = findViewById(R.id.sbc1);
        sbc2 = findViewById(R.id.sbc2);
        sbc3 = findViewById(R.id.sbc3);
        sbc4 = findViewById(R.id.sbc4);
        sbc5 = findViewById(R.id.sbc5);
        sbc6 = findViewById(R.id.sbc6);
        sbc7 = findViewById(R.id.sbc7);
        abc1 = findViewById(R.id.abc1);

        // Outcome Cards title
        lblLiveBirth.setText("Live Birth (" + LB + ")");
        lblStillBirth.setText("Still Birth (" + SB + ")");
        lblMiscarriage.setText("Miscarriage (" + MISC + ")");
        lblAbortion.setText("Abortion (" + AB + ")");

        // Call Outcome display functions
        ShowLiveBirthChildButton(LB);
        ShowMiscarriageChildButton(MISC);
        ShowStillBirthChildButton(SB);
        ShowAbortion(AB);

        // Live birth child buttons onclick events
        lbc1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (liveBirthDataModels.size() > 0) {
                    IDbundle.putString("LiveBirthID", liveBirthDataModels.get(0).getLiveBirthID());
                    IDbundle.putString("LiveBirthMemID", liveBirthDataModels.get(0).getMemID());
                } else {
                    IDbundle.putString("LiveBirthID", "");
                    IDbundle.putString("LiveBirthMemID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", "25");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "1");
                IDbundle.putString("dod", DOD);
                IDbundle.putString("tod", TOD);

                Intent intent = new Intent(getApplicationContext(), Surv_LiveBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        lbc2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (liveBirthDataModels.size() > 1) {
                    IDbundle.putString("LiveBirthID", liveBirthDataModels.get(1).getLiveBirthID());
                    IDbundle.putString("LiveBirthMemID", liveBirthDataModels.get(1).getMemID());
                } else {
                    IDbundle.putString("LiveBirthID", "");
                    IDbundle.putString("LiveBirthMemID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", "25");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "2");
                IDbundle.putString("dod", DOD);
                IDbundle.putString("tod", TOD);

                Intent intent = new Intent(getApplicationContext(), Surv_LiveBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        lbc2.setOnLongClickListener(view -> {
            memberPosition(1);
            return true;
        });
        lbc3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (liveBirthDataModels.size() > 2) {
                    IDbundle.putString("LiveBirthID", liveBirthDataModels.get(2).getLiveBirthID());
                    IDbundle.putString("LiveBirthMemID", liveBirthDataModels.get(2).getMemID());
                } else {
                    IDbundle.putString("LiveBirthID", "");
                    IDbundle.putString("LiveBirthMemID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", "25");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "3");
                IDbundle.putString("dod", DOD);
                IDbundle.putString("tod", TOD);

                Intent intent = new Intent(getApplicationContext(), Surv_LiveBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        lbc3.setOnLongClickListener(view -> {
            memberPosition(2);
            return true;
        });
        lbc4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (liveBirthDataModels.size() > 3) {
                    IDbundle.putString("LiveBirthID", liveBirthDataModels.get(3).getLiveBirthID());
                    IDbundle.putString("LiveBirthMemID", liveBirthDataModels.get(3).getMemID());
                } else {
                    IDbundle.putString("LiveBirthID", "");
                    IDbundle.putString("LiveBirthMemID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", "25");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "4");
                IDbundle.putString("dod", DOD);
                IDbundle.putString("tod", TOD);

                Intent intent = new Intent(getApplicationContext(), Surv_LiveBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        lbc4.setOnLongClickListener(view -> {
            memberPosition(3);
            return true;
        });
        lbc5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (liveBirthDataModels.size() > 4) {
                    IDbundle.putString("LiveBirthID", liveBirthDataModels.get(4).getLiveBirthID());
                    IDbundle.putString("LiveBirthMemID", liveBirthDataModels.get(4).getMemID());
                } else {
                    IDbundle.putString("LiveBirthID", "");
                    IDbundle.putString("LiveBirthMemID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", "25");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "5");
                IDbundle.putString("dod", DOD);
                IDbundle.putString("tod", TOD);

                Intent intent = new Intent(getApplicationContext(), Surv_LiveBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        lbc5.setOnLongClickListener(view -> {
            memberPosition(4);
            return true;
        });
        lbc6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (liveBirthDataModels.size() > 5) {
                    IDbundle.putString("LiveBirthID", liveBirthDataModels.get(5).getLiveBirthID());
                    IDbundle.putString("LiveBirthMemID", liveBirthDataModels.get(5).getMemID());
                } else {
                    IDbundle.putString("LiveBirthID", "");
                    IDbundle.putString("LiveBirthMemID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", "25");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "6");
                IDbundle.putString("dod", DOD);
                IDbundle.putString("tod", TOD);

                Intent intent = new Intent(getApplicationContext(), Surv_LiveBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        lbc6.setOnLongClickListener(view -> {
            memberPosition(5);
            return true;
        });
        lbc7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (liveBirthDataModels.size() > 6) {
                    IDbundle.putString("LiveBirthID", liveBirthDataModels.get(6).getLiveBirthID());
                    IDbundle.putString("LiveBirthMemID", liveBirthDataModels.get(6).getMemID());
                } else {
                    IDbundle.putString("LiveBirthID", "");
                    IDbundle.putString("LiveBirthMemID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("HHNO", HHNO);
                IDbundle.putString("evtype", "25");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "7");
                IDbundle.putString("dod", DOD);
                IDbundle.putString("tod", TOD);

                Intent intent = new Intent(getApplicationContext(), Surv_LiveBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        lbc7.setOnLongClickListener(view -> {
            memberPosition(6);
            return true;
        });

        // Still birth buttons onclick events
        sbc1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (stillBirthDataModelList.size() > 0) {
                    IDbundle.putString("StillBirthID", stillBirthDataModelList.get(0).getStillBirthID());
                } else {
                    IDbundle.putString("StillBirthID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "47");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "1");
                IDbundle.putString("dod", DOD);

                Intent intent = new Intent(getApplicationContext(), Surv_StillBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        sbc2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (stillBirthDataModelList.size() > 1) {
                    IDbundle.putString("StillBirthID", stillBirthDataModelList.get(1).getStillBirthID());
                } else {
                    IDbundle.putString("StillBirthID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "47");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "2");
                IDbundle.putString("dod", DOD);

                Intent intent = new Intent(getApplicationContext(), Surv_StillBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        sbc2.setOnLongClickListener(view -> {
            stillBirth(1);
            return true;
        });
        sbc3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (stillBirthDataModelList.size() > 2) {
                    IDbundle.putString("StillBirthID", stillBirthDataModelList.get(2).getStillBirthID());
                } else {
                    IDbundle.putString("StillBirthID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "47");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "3");
                IDbundle.putString("dod", DOD);

                Intent intent = new Intent(getApplicationContext(), Surv_StillBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        sbc3.setOnLongClickListener(view -> {
            stillBirth(2);
            return true;
        });
        sbc4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (stillBirthDataModelList.size() > 3) {
                    IDbundle.putString("StillBirthID", stillBirthDataModelList.get(3).getStillBirthID());
                } else {
                    IDbundle.putString("StillBirthID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "47");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "4");
                IDbundle.putString("dod", DOD);

                Intent intent = new Intent(getApplicationContext(), Surv_StillBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        sbc4.setOnLongClickListener(view -> {
            stillBirth(3);
            return true;
        });
        sbc5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (stillBirthDataModelList.size() > 4) {
                    IDbundle.putString("StillBirthID", stillBirthDataModelList.get(4).getStillBirthID());
                } else {
                    IDbundle.putString("StillBirthID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "47");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "5");
                IDbundle.putString("dod", DOD);

                Intent intent = new Intent(getApplicationContext(), Surv_StillBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        sbc5.setOnLongClickListener(view -> {
            stillBirth(4);
            return true;
        });
        sbc6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (stillBirthDataModelList.size() > 5) {
                    IDbundle.putString("StillBirthID", stillBirthDataModelList.get(5).getStillBirthID());
                } else {
                    IDbundle.putString("StillBirthID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "47");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "6");
                IDbundle.putString("dod", DOD);

                Intent intent = new Intent(getApplicationContext(), Surv_StillBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        sbc6.setOnLongClickListener(view -> {
            stillBirth(5);
            return true;
        });
        sbc7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (stillBirthDataModelList.size() > 6) {
                    IDbundle.putString("StillBirthID", stillBirthDataModelList.get(6).getStillBirthID());
                } else {
                    IDbundle.putString("StillBirthID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "47");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("child", "7");
                IDbundle.putString("dod", DOD);

                Intent intent = new Intent(getApplicationContext(), Surv_StillBirth.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        sbc7.setOnLongClickListener(view -> {
            stillBirth(6);
            return true;
        });


        //Abortion Button click
        abc1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Bundle IDbundle = new Bundle();
                if (abortionDataModels.size() > 0) {
                    IDbundle.putString("AbortionID", abortionDataModels.get(0).getAbortionID());
                } else {
                    IDbundle.putString("AbortionID", "");
                }
                IDbundle.putString("DeliveryID", DELIVERYID);
                IDbundle.putString("MemID", MEM_ID);
                IDbundle.putString("HHID", HHID);
                IDbundle.putString("evtype", "50");
                IDbundle.putString("round", ROUND);
                IDbundle.putString("dod", DOD);

                Intent intent = new Intent(getApplicationContext(), Surv_Abortion.class);
                intent.putExtras(IDbundle);
                startActivityForResult(intent, 1);
            }
        });
        abc1.setOnLongClickListener(view -> {
            if (abortionDataModels.size() > 0) {
                String AbortionID = abortionDataModels.get(0).getAbortionID();
                AlertDialog.Builder adb = new AlertDialog.Builder(Surv_delivery_module.this);
                adb.setTitle("Close");
                adb.setIcon(R.drawable.favicon);
                adb.setMessage("Do you want to delete this record[Yes/No]?");
                adb.setNegativeButton("No", null);
                adb.setPositiveButton("Yes", (dialog, which) -> {
                    C.SaveData("Delete from tmpAbortion where AbortionID='" + AbortionID + "'");
                    sp.save(this, "ab1", "");
                    loadUI();
                });
                adb.show();
            }
            return true;
        });

    }

    // Display Live birth section on condition
    public void ShowLiveBirthChildButton(String lb) {
        if (lb.equals("1")) {
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.GONE);
            lbc3.setVisibility(View.GONE);
            lbc4.setVisibility(View.GONE);
            lbc5.setVisibility(View.GONE);
            lbc6.setVisibility(View.GONE);
            lbc7.setVisibility(View.GONE);
        } else if (lb.equals("2")) {
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.VISIBLE);
            lbc3.setVisibility(View.GONE);
            lbc4.setVisibility(View.GONE);
            lbc5.setVisibility(View.GONE);
            lbc6.setVisibility(View.GONE);
            lbc7.setVisibility(View.GONE);
        } else if (lb.equals("3")) {
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.VISIBLE);
            lbc3.setVisibility(View.VISIBLE);
            lbc4.setVisibility(View.GONE);
            lbc5.setVisibility(View.GONE);
            lbc6.setVisibility(View.GONE);
            lbc7.setVisibility(View.GONE);
        } else if (lb.equals("4")) {
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.VISIBLE);
            lbc3.setVisibility(View.VISIBLE);
            lbc4.setVisibility(View.VISIBLE);
            lbc5.setVisibility(View.GONE);
            lbc6.setVisibility(View.GONE);
            lbc7.setVisibility(View.GONE);
        } else if (lb.equals("5")) {
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.VISIBLE);
            lbc3.setVisibility(View.VISIBLE);
            lbc4.setVisibility(View.VISIBLE);
            lbc5.setVisibility(View.VISIBLE);
            lbc6.setVisibility(View.GONE);
            lbc7.setVisibility(View.GONE);
        } else if (lb.equals("6")) {
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.VISIBLE);
            lbc3.setVisibility(View.VISIBLE);
            lbc4.setVisibility(View.VISIBLE);
            lbc5.setVisibility(View.VISIBLE);
            lbc6.setVisibility(View.VISIBLE);
            lbc7.setVisibility(View.GONE);
        } else if (lb.equals("7")) {
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.VISIBLE);
            lbc3.setVisibility(View.VISIBLE);
            lbc4.setVisibility(View.VISIBLE);
            lbc5.setVisibility(View.VISIBLE);
            lbc6.setVisibility(View.VISIBLE);
            lbc7.setVisibility(View.VISIBLE);
        } else {
            lbc1.setVisibility(View.GONE);
            lbc2.setVisibility(View.GONE);
            lbc3.setVisibility(View.GONE);
            lbc4.setVisibility(View.GONE);
            lbc5.setVisibility(View.GONE);
            lbc6.setVisibility(View.GONE);
            lbc7.setVisibility(View.GONE);
        }
    }

    // Display Miscarriage section on condition
    public void ShowMiscarriageChildButton(String m) {
        if (m.equals("1")) {
            mc1.setVisibility(View.VISIBLE);
            mc1.setEnabled(false);
            mc1.setBackgroundResource(R.drawable.button_style_disabled);
            mc2.setVisibility(View.GONE);
            mc3.setVisibility(View.GONE);
            mc4.setVisibility(View.GONE);
            mc5.setVisibility(View.GONE);
            mc6.setVisibility(View.GONE);
            mc7.setVisibility(View.GONE);
        } else if (m.equals("2")) {
            mc1.setVisibility(View.VISIBLE);
            mc2.setVisibility(View.VISIBLE);
            mc1.setEnabled(false);
            mc2.setEnabled(false);
            mc1.setBackgroundResource(R.drawable.button_style_disabled);
            mc2.setBackgroundResource(R.drawable.button_style_disabled);
            mc3.setVisibility(View.GONE);
            mc4.setVisibility(View.GONE);
            mc5.setVisibility(View.GONE);
            mc6.setVisibility(View.GONE);
            mc7.setVisibility(View.GONE);
        } else if (m.equals("3")) {
            mc1.setVisibility(View.VISIBLE);
            mc2.setVisibility(View.VISIBLE);
            mc3.setVisibility(View.VISIBLE);
            mc1.setEnabled(false);
            mc2.setEnabled(false);
            mc3.setEnabled(false);
            mc1.setBackgroundResource(R.drawable.button_style_disabled);
            mc2.setBackgroundResource(R.drawable.button_style_disabled);
            mc3.setBackgroundResource(R.drawable.button_style_disabled);
            mc4.setVisibility(View.GONE);
            mc5.setVisibility(View.GONE);
            mc6.setVisibility(View.GONE);
            mc7.setVisibility(View.GONE);
        } else if (m.equals("4")) {
            mc1.setVisibility(View.VISIBLE);
            mc2.setVisibility(View.VISIBLE);
            mc3.setVisibility(View.VISIBLE);
            mc4.setVisibility(View.VISIBLE);
            mc1.setEnabled(false);
            mc2.setEnabled(false);
            mc3.setEnabled(false);
            mc4.setEnabled(false);
            mc1.setBackgroundResource(R.drawable.button_style_disabled);
            mc2.setBackgroundResource(R.drawable.button_style_disabled);
            mc3.setBackgroundResource(R.drawable.button_style_disabled);
            mc4.setBackgroundResource(R.drawable.button_style_disabled);
            mc5.setVisibility(View.GONE);
            mc6.setVisibility(View.GONE);
            mc7.setVisibility(View.GONE);
        } else if (m.equals("5")) {
            mc1.setVisibility(View.VISIBLE);
            mc2.setVisibility(View.VISIBLE);
            mc3.setVisibility(View.VISIBLE);
            mc4.setVisibility(View.VISIBLE);
            mc5.setVisibility(View.VISIBLE);
            mc1.setEnabled(false);
            mc2.setEnabled(false);
            mc3.setEnabled(false);
            mc4.setEnabled(false);
            mc5.setEnabled(false);
            mc1.setBackgroundResource(R.drawable.button_style_disabled);
            mc2.setBackgroundResource(R.drawable.button_style_disabled);
            mc3.setBackgroundResource(R.drawable.button_style_disabled);
            mc4.setBackgroundResource(R.drawable.button_style_disabled);
            mc5.setBackgroundResource(R.drawable.button_style_disabled);
            mc6.setVisibility(View.GONE);
            mc7.setVisibility(View.GONE);
        } else if (m.equals("6")) {
            mc1.setVisibility(View.VISIBLE);
            mc2.setVisibility(View.VISIBLE);
            mc3.setVisibility(View.VISIBLE);
            mc4.setVisibility(View.VISIBLE);
            mc5.setVisibility(View.VISIBLE);
            mc6.setVisibility(View.VISIBLE);
            mc1.setEnabled(false);
            mc2.setEnabled(false);
            mc3.setEnabled(false);
            mc4.setEnabled(false);
            mc5.setEnabled(false);
            mc6.setEnabled(false);
            mc1.setBackgroundResource(R.drawable.button_style_disabled);
            mc2.setBackgroundResource(R.drawable.button_style_disabled);
            mc3.setBackgroundResource(R.drawable.button_style_disabled);
            mc4.setBackgroundResource(R.drawable.button_style_disabled);
            mc5.setBackgroundResource(R.drawable.button_style_disabled);
            mc6.setBackgroundResource(R.drawable.button_style_disabled);
            mc7.setVisibility(View.GONE);
        } else if (m.equals("7")) {
            mc1.setVisibility(View.VISIBLE);
            mc2.setVisibility(View.VISIBLE);
            mc3.setVisibility(View.VISIBLE);
            mc4.setVisibility(View.VISIBLE);
            mc5.setVisibility(View.VISIBLE);
            mc6.setVisibility(View.VISIBLE);
            mc7.setVisibility(View.VISIBLE);
            mc1.setEnabled(false);
            mc2.setEnabled(false);
            mc3.setEnabled(false);
            mc4.setEnabled(false);
            mc5.setEnabled(false);
            mc6.setEnabled(false);
            mc7.setEnabled(false);
            mc1.setBackgroundResource(R.drawable.button_style_disabled);
            mc2.setBackgroundResource(R.drawable.button_style_disabled);
            mc3.setBackgroundResource(R.drawable.button_style_disabled);
            mc4.setBackgroundResource(R.drawable.button_style_disabled);
            mc5.setBackgroundResource(R.drawable.button_style_disabled);
            mc6.setBackgroundResource(R.drawable.button_style_disabled);
            mc7.setBackgroundResource(R.drawable.button_style_disabled);
        } else {
            mc1.setVisibility(View.GONE);
            mc2.setVisibility(View.GONE);
            mc3.setVisibility(View.GONE);
            mc4.setVisibility(View.GONE);
            mc5.setVisibility(View.GONE);
            mc6.setVisibility(View.GONE);
            mc7.setVisibility(View.GONE);
        }
    }

    // Display Still birth section on condition
    public void ShowStillBirthChildButton(String sb) {
        if (sb.equals("1")) {
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.GONE);
            sbc3.setVisibility(View.GONE);
            sbc4.setVisibility(View.GONE);
            sbc5.setVisibility(View.GONE);
            sbc6.setVisibility(View.GONE);
            sbc7.setVisibility(View.GONE);
        } else if (sb.equals("2")) {
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.VISIBLE);
            sbc3.setVisibility(View.GONE);
            sbc4.setVisibility(View.GONE);
            sbc5.setVisibility(View.GONE);
            sbc6.setVisibility(View.GONE);
            sbc7.setVisibility(View.GONE);
        } else if (sb.equals("3")) {
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.VISIBLE);
            sbc3.setVisibility(View.VISIBLE);
            sbc4.setVisibility(View.GONE);
            sbc5.setVisibility(View.GONE);
            sbc6.setVisibility(View.GONE);
            sbc7.setVisibility(View.GONE);
        } else if (sb.equals("4")) {
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.VISIBLE);
            sbc3.setVisibility(View.VISIBLE);
            sbc4.setVisibility(View.VISIBLE);
            sbc5.setVisibility(View.GONE);
            sbc6.setVisibility(View.GONE);
            sbc7.setVisibility(View.GONE);
        } else if (sb.equals("5")) {
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.VISIBLE);
            sbc3.setVisibility(View.VISIBLE);
            sbc4.setVisibility(View.VISIBLE);
            sbc5.setVisibility(View.VISIBLE);
            sbc6.setVisibility(View.GONE);
            sbc7.setVisibility(View.GONE);
        } else if (sb.equals("6")) {
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.VISIBLE);
            sbc3.setVisibility(View.VISIBLE);
            sbc4.setVisibility(View.VISIBLE);
            sbc5.setVisibility(View.VISIBLE);
            sbc6.setVisibility(View.VISIBLE);
            sbc7.setVisibility(View.GONE);
        } else if (sb.equals("7")) {
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.VISIBLE);
            sbc3.setVisibility(View.VISIBLE);
            sbc4.setVisibility(View.VISIBLE);
            sbc5.setVisibility(View.VISIBLE);
            sbc6.setVisibility(View.VISIBLE);
            sbc7.setVisibility(View.VISIBLE);
        } else {
            sbc1.setVisibility(View.GONE);
            sbc2.setVisibility(View.GONE);
            sbc3.setVisibility(View.GONE);
            sbc4.setVisibility(View.GONE);
            sbc5.setVisibility(View.GONE);
            sbc6.setVisibility(View.GONE);
            sbc7.setVisibility(View.GONE);
        }
    }

    // Display Abortion section on condition
    public void ShowAbortion(String ab) {
        if (ab.equals("1")) {
            abc1.setVisibility(View.VISIBLE);
        } else {
            abc1.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUI();
    }

    private void stillBirth(int position) {
        if (stillBirthDataModelList.size() > position) {
            String StillBirthID = stillBirthDataModelList.get(position).getStillBirthID();
            int lastIndex = stillBirthDataModelList.size() - 1;
            String finalStillBirthID = stillBirthDataModelList.get(lastIndex).getStillBirthID();

            if (StillBirthID.equals(finalStillBirthID)) {
                AlertDialog.Builder adb = new AlertDialog.Builder(Surv_delivery_module.this);
                adb.setTitle("Close");
                adb.setIcon(R.drawable.favicon);
                adb.setMessage("Do you want to delete this record[Yes/No]?");
                adb.setNegativeButton("No", null);
                adb.setPositiveButton("Yes", (dialog, which) -> stillBirthDelete(StillBirthID));
                adb.show();
            }
        }
    }

    private void memberPosition(int position) {
        if (liveBirthDataModels.size() > position) {
            String MemID = liveBirthDataModels.get(position).getMemID();
            int lastIndex = liveBirthDataModels.size() - 1;
            String LiveBirthID = liveBirthDataModels.get(lastIndex).getLiveBirthID();
            String finalMemID = liveBirthDataModels.get(lastIndex).getMemID();

            if (MemID.equals(finalMemID)) {
                AlertDialog.Builder adb = new AlertDialog.Builder(Surv_delivery_module.this);
                adb.setTitle("Close");
                adb.setIcon(R.drawable.favicon);
                adb.setMessage("Do you want to delete this child[Yes/No]?");
                adb.setNegativeButton("No", null);
                adb.setPositiveButton("Yes", (dialog, which) -> DataDelete(MemID));
                adb.show();
            }
        }
    }

    private void DataDelete(String MemID) {
        C.SaveData("Delete from tmpMember where MemID='" + MemID + "'");
        C.SaveData("Delete from tmpDeath where MemID='" + MemID + "'");
        C.SaveData("Delete from tmpFatherSerial where MemID='" + MemID + "'");
        C.SaveData("Delete from tmpLiveBirth where MemID='" + MemID + "'");
        C.SaveData("Delete from tmpMemberMove where MemID='" + MemID + "'");
        C.SaveData("Delete from tmpMigration where MemID='" + MemID + "'");
        C.SaveData("Delete from tmpMotherSerial where MemID='" + MemID + "'");
        C.SaveData("Delete from tmpMovement where MemID='" + MemID + "'");
        C.SaveData("Delete from tmpRelation where MemID='" + MemID + "'");
        C.SaveData("Delete from Caregiver where MemID='" + MemID + "'");
        C.SaveData("Delete from ChildChar where MemID='" + MemID + "'");
        C.SaveData("Delete from Newborn where MemID='" + MemID + "'");
        loadUI();
    }

    private void stillBirthDelete(String StillBirthID) {
        C.SaveData("Delete from tmpStillBirth where StillBirthID ='" + StillBirthID + "'");
        loadUI();
    }

    private void loadUI() {
        tmpLiveBirth_DataModel model = new tmpLiveBirth_DataModel();
        String SQL = "Select * from tmpLiveBirth  Where HHID='" + HHID + "' AND DeliveryID ='" + DELIVERYID + "' AND LBMomID = '" + MEM_ID + "'";
        liveBirthDataModels = model.SelectAll(getApplicationContext(), SQL);

        tmpStillBirth_DataModel model1 = new tmpStillBirth_DataModel();
        String SQL1 = "Select * from tmpStillBirth  Where HHID='" + HHID + "' AND DeliveryID ='" + DELIVERYID + "' AND SBMomID = '" + MEM_ID + "'";
        stillBirthDataModelList = model1.SelectAll(getApplicationContext(), SQL1);

        tmpAbortion_DataModel model2 = new tmpAbortion_DataModel();
        String SQL2 = "Select * from tmpAbortion  Where HHID='" + HHID + "' AND DeliveryID ='" + DELIVERYID + "' AND AbMomID = '" + MEM_ID + "'";
        abortionDataModels = model2.SelectAll(getApplicationContext(), SQL2);

        if (abortionDataModels.size() == 1) {
            sp.save(this, "ab1", "complete");
        }

        //tmpLiveBirth
        if (liveBirthDataModels.size() == 1) {
            sp.save(this, "child1", "complete");
            sp.save(this, "child2", "");
            sp.save(this, "child3", "");
            sp.save(this, "child4", "");
            sp.save(this, "child5", "");
            sp.save(this, "child6", "");
            sp.save(this, "child7", "");
        } else if (liveBirthDataModels.size() == 2) {
            sp.save(this, "child1", "complete");
            sp.save(this, "child2", "complete");
            sp.save(this, "child3", "");
            sp.save(this, "child4", "");
            sp.save(this, "child5", "");
            sp.save(this, "child6", "");
            sp.save(this, "child7", "");
        } else if (liveBirthDataModels.size() == 3) {
            sp.save(this, "child1", "complete");
            sp.save(this, "child2", "complete");
            sp.save(this, "child3", "complete");
            sp.save(this, "child4", "");
            sp.save(this, "child5", "");
            sp.save(this, "child6", "");
            sp.save(this, "child7", "");
        } else if (liveBirthDataModels.size() == 4) {
            sp.save(this, "child1", "complete");
            sp.save(this, "child2", "complete");
            sp.save(this, "child3", "complete");
            sp.save(this, "child4", "complete");
            sp.save(this, "child5", "");
            sp.save(this, "child6", "");
            sp.save(this, "child7", "");
        } else if (liveBirthDataModels.size() == 5) {
            sp.save(this, "child1", "complete");
            sp.save(this, "child2", "complete");
            sp.save(this, "child3", "complete");
            sp.save(this, "child4", "complete");
            sp.save(this, "child5", "complete");
            sp.save(this, "child6", "");
            sp.save(this, "child7", "");
        } else if (liveBirthDataModels.size() == 6) {
            sp.save(this, "child1", "complete");
            sp.save(this, "child2", "complete");
            sp.save(this, "child3", "complete");
            sp.save(this, "child4", "complete");
            sp.save(this, "child5", "complete");
            sp.save(this, "child6", "complete");
            sp.save(this, "child7", "");
        } else if (liveBirthDataModels.size() == 7) {
            sp.save(this, "child1", "complete");
            sp.save(this, "child2", "complete");
            sp.save(this, "child3", "complete");
            sp.save(this, "child4", "complete");
            sp.save(this, "child5", "complete");
            sp.save(this, "child6", "complete");
            sp.save(this, "child7", "complete");
        }

        //tmpStillBirth
        if (stillBirthDataModelList.size() == 1) {
            sp.save(this, "stl1", "complete");
            sp.save(this, "stl2", "");
            sp.save(this, "stl3", "");
            sp.save(this, "stl4", "");
            sp.save(this, "stl5", "");
            sp.save(this, "stl6", "");
            sp.save(this, "stl7", "");
        } else if (stillBirthDataModelList.size() == 2) {
            sp.save(this, "stl1", "complete");
            sp.save(this, "stl2", "complete");
            sp.save(this, "stl3", "");
            sp.save(this, "stl4", "");
            sp.save(this, "stl5", "");
            sp.save(this, "stl6", "");
            sp.save(this, "stl7", "");
        } else if (stillBirthDataModelList.size() == 3) {
            sp.save(this, "stl1", "complete");
            sp.save(this, "stl2", "complete");
            sp.save(this, "stl3", "complete");
            sp.save(this, "stl4", "");
            sp.save(this, "stl5", "");
            sp.save(this, "stl6", "");
            sp.save(this, "stl7", "");
        } else if (stillBirthDataModelList.size() == 4) {
            sp.save(this, "stl1", "complete");
            sp.save(this, "stl2", "complete");
            sp.save(this, "stl3", "complete");
            sp.save(this, "stl4", "complete");
            sp.save(this, "stl5", "");
            sp.save(this, "stl6", "");
            sp.save(this, "stl7", "");
        } else if (stillBirthDataModelList.size() == 5) {
            sp.save(this, "stl1", "complete");
            sp.save(this, "stl2", "complete");
            sp.save(this, "stl3", "complete");
            sp.save(this, "stl4", "complete");
            sp.save(this, "stl5", "complete");
            sp.save(this, "stl6", "");
            sp.save(this, "stl7", "");
        } else if (stillBirthDataModelList.size() == 6) {
            sp.save(this, "stl1", "complete");
            sp.save(this, "stl2", "complete");
            sp.save(this, "stl3", "complete");
            sp.save(this, "stl4", "complete");
            sp.save(this, "stl5", "complete");
            sp.save(this, "stl6", "complete");
            sp.save(this, "stl7", "");
        } else if (stillBirthDataModelList.size() == 7) {
            sp.save(this, "stl1", "complete");
            sp.save(this, "stl2", "complete");
            sp.save(this, "stl3", "complete");
            sp.save(this, "stl4", "complete");
            sp.save(this, "stl5", "complete");
            sp.save(this, "stl6", "complete");
            sp.save(this, "stl7", "complete");
        }

        String str[] = DOD.split("/");
        int day = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        int year = Integer.parseInt(str[2]);
//        if(C.Existence("select * from tmpMember where BDate_D='"+day+"' and BDate_M='"+month+"' and BDate_Y='"+year+"' order by EnDt asc")){
//            lbc1.setBackgroundResource(R.drawable.button_style_circle_fill);
//        }

        if (MySharedPreferences.getValue(this, "ab1").equals("complete")) {
            abc1.setBackgroundResource(R.drawable.button_style_circle_green);
        } else {
            abc1.setBackgroundResource(R.drawable.not_complete);
        }

        //tmpLiveBirth
        if (MySharedPreferences.getValue(this, "child1").equals("complete")) {
            lbc1.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            lbc1.setBackgroundResource(R.drawable.not_complete);
        }
        if (MySharedPreferences.getValue(this, "child2").equals("complete")) {
            lbc2.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            lbc2.setBackgroundResource(R.drawable.not_complete);
        }
        if (MySharedPreferences.getValue(this, "child3").equals("complete")) {
            lbc3.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            lbc3.setBackgroundResource(R.drawable.not_complete);
        }
        if (MySharedPreferences.getValue(this, "child4").equals("complete")) {
            lbc4.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            lbc4.setBackgroundResource(R.drawable.not_complete);
        }
        if (MySharedPreferences.getValue(this, "child5").equals("complete")) {
            lbc5.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            lbc5.setBackgroundResource(R.drawable.not_complete);
        }
        if (MySharedPreferences.getValue(this, "child6").equals("complete")) {
            lbc6.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            lbc6.setBackgroundResource(R.drawable.not_complete);
        }
        if (MySharedPreferences.getValue(this, "child7").equals("complete")) {
            lbc7.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            lbc7.setBackgroundResource(R.drawable.not_complete);
        }

        //tmpStillBirth
        if (MySharedPreferences.getValue(this, "stl1").equals("complete")) {
            sbc1.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            sbc1.setBackgroundResource(R.drawable.not_complete);
        }
        if (MySharedPreferences.getValue(this, "stl2").equals("complete")) {
            sbc2.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            sbc2.setBackgroundResource(R.drawable.not_complete);
        }
        if (MySharedPreferences.getValue(this, "stl3").equals("complete")) {
            sbc3.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            sbc3.setBackgroundResource(R.drawable.not_complete);
        }
        if (MySharedPreferences.getValue(this, "stl4").equals("complete")) {
            sbc4.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            sbc4.setBackgroundResource(R.drawable.not_complete);
        }
        if (MySharedPreferences.getValue(this, "stl5").equals("complete")) {
            sbc5.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            sbc5.setBackgroundResource(R.drawable.not_complete);
        }
        if (MySharedPreferences.getValue(this, "stl6").equals("complete")) {
            sbc6.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            sbc6.setBackgroundResource(R.drawable.not_complete);
        }
        if (MySharedPreferences.getValue(this, "stl7").equals("complete")) {
            sbc7.setBackgroundResource(R.drawable.style_completed_square_shape);
        } else {
            sbc7.setBackgroundResource(R.drawable.not_complete);
        }

        //abortion
        if (C.Existence("select * from tmpAbortion where DeliveryID='" + DELIVERYID)) {
            abc1.setBackgroundResource(R.drawable.style_completed_square_shape);
        }
    }

}