package forms_activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.icddrb.champs_lc_dss.R;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;
import Common.Global_CodeList;
import Common.ProjectSetting;
import Utility.MySharedPreferences;
import forms_datamodel.Death_DataModel;
import forms_datamodel.Delivery_DataModel;
import forms_datamodel.Education_DataModel;
import forms_datamodel.EventData;
import forms_datamodel.FatherSerial_DataModel;
import forms_datamodel.MaritalSts_DataModel;
import forms_datamodel.MemberMove_DataModel;
import forms_datamodel.Member_DataModel;
import forms_datamodel.Migration_DataModel;
import forms_datamodel.MotherSerial_DataModel;
import forms_datamodel.Movement_DataModel;
import forms_datamodel.NotPregnant_DataModel;
import forms_datamodel.Occupation_DataModel;
import forms_datamodel.Pregnancy_DataModel;
import forms_datamodel.Relation_DataModel;
import forms_datamodel.SpouseSerial_DataModel;
import forms_datamodel.tmpDeath_DataModel;
import forms_datamodel.tmpDelivery_DataModel;
import forms_datamodel.tmpEducation_DataModel;
import forms_datamodel.tmpFatherSerial_DataModel;
import forms_datamodel.tmpMaritalSts_DataModel;
import forms_datamodel.tmpMemberMove_DataModel;
import forms_datamodel.tmpMember_DataModel;
import forms_datamodel.tmpMigration_DataModel;
import forms_datamodel.tmpMotherSerial_DataModel;
import forms_datamodel.tmpMovement_DataModel;
import forms_datamodel.tmpNotPregnant_DataModel;
import forms_datamodel.tmpOccupation_DataModel;
import forms_datamodel.tmpPregnancy_DataModel;
import forms_datamodel.tmpRelation_DataModel;
import forms_datamodel.tmpSpouseSerial_DataModel;

public class Surv_Event_list extends AppCompatActivity {
     String HHID = "";
     String HHNO = "";
     String ROUND = "";
     String SelectedROUND = "";
    Connection C;
    Global g;
     String TableName;
    TextView lblHeading;
    TextView lblTotal;
    private List<tmpMember_DataModel> dataList = new ArrayList<>();
    private List<Member_DataModel> dataListPrev = new ArrayList<>();
     String STARTTIME = "";
     String DEVICEID = "";
     String ENTRYUSER = "";
    TextView currentEvents;
    TextView previousEvents;
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
    private DataAdapterPrev mAdapterPrev;
    Bundle IDbundle;
    Spinner spnRound;
    LinearLayout secRound;
    private ProgressDialog progDailog;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_surv_event_list);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            C = new Connection(this);
            g = Global.getInstance();
            STARTTIME = g.CurrentTime24();

            DEVICEID = MySharedPreferences.getValue(this, "deviceid");
            ENTRYUSER = MySharedPreferences.getValue(this, "userid");

            IDbundle = getIntent().getExtras();
            HHID = IDbundle.getString("HHID");
            HHNO = IDbundle.getString("HHNO");
            ROUND = IDbundle.getString("round");

            TableName = "tmpMember";
            lblHeading = findViewById(R.id.lblHeading);
            lblTotal = findViewById(R.id.lblTotal);
            currentEvents = findViewById(R.id.currentEvents);
            previousEvents = findViewById(R.id.previousEvents);
            recyclerView = findViewById(R.id.recycler_view);

            ImageButton cmdBack = findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(v -> finish());

            secRound = findViewById(R.id.secRound);
            secRound.setVisibility(View.GONE);
            spnRound = findViewById(R.id.spnRound);

            List<String> dataListSp = new ArrayList<>();
            dataListSp.addAll(C.getDataList("Select RoundNo from DSSRound where RoundNo <> '"+ROUND+"' order by RoundNo desc"));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataListSp);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnRound.setAdapter(adapter);
            spnRound.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if (!dataListSp.isEmpty()) {
                        SelectedROUND = dataListSp.get(position).toString();
                        DataSearchPrevious(HHID, SelectedROUND);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

            currentEvents.setOnClickListener(view -> {
                progDailog = ProgressDialog.show(Surv_Event_list.this, "", "Please Wait . . .", false);
                secRound.setVisibility(View.GONE);
                dataListPrev.clear();
                DataSearch(HHID);
                currentEvents.setTextColor(getResources().getColor(R.color.white));
                currentEvents.setBackground(getResources().getDrawable(R.drawable.shadow_background_button));
                previousEvents.setTextColor(getResources().getColor(R.color.blackMedium));
                previousEvents.setBackground(getResources().getDrawable(R.drawable.button_background));
                handler.postDelayed(() -> progDailog.dismiss(), 1500);

            });
            previousEvents.setOnClickListener(v -> {
                progDailog = ProgressDialog.show(Surv_Event_list.this, "", "Please Wait . . .", false);
                secRound.setVisibility(View.VISIBLE);
                dataList.clear();
                DataSearchPrevious(HHID, SelectedROUND);
                currentEvents.setTextColor(getResources().getColor(R.color.blackMedium));
                currentEvents.setBackground(getResources().getDrawable(R.drawable.button_background));
                previousEvents.setTextColor(getResources().getColor(R.color.white));
                previousEvents.setBackground(getResources().getDrawable(R.drawable.shadow_background_button));
                handler.postDelayed(() -> progDailog.dismiss(), 1500);
            });

            DataSearch(HHID);


            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        } catch (Exception e) {
            Connection.MessageBox(Surv_Event_list.this, e.getMessage());
            return;
        }

    }

    private void DataSearch(String HHID) {
        try {
            tmpMember_DataModel d = new tmpMember_DataModel("");

            String SQL = "SELECT tm.MemID, tmm.MSlNo, tm.Name, tm.FaNo, tm.MoNo, tm.DSSID, tm.Sex, tm.Age, tm.AgeU," +
                    " IFNULL(CAST((julianday('now') - julianday(tm.BDate)) AS INT), 0) AS AgeD," +
                    " IFNULL(CAST((julianday('now') - julianday(tm.BDate)) / 365.25 AS INT), 0) AS AgeY," +
                    " tm.Rth, tm.MS, tm.EduY, tm.Ocp,tm.OcpOth," +
                    " IFNULL(tm.BDate, '') AS BDate,IFNULL(tm.BDate_D, '') AS BDate_D," +
                    " IFNULL(tm.BDate_M, '') AS BDate_M,IFNULL(tm.BDate_Y, '') AS BDate_Y," +
                    " IFNULL(tm.Pstat, '') AS Pstat,IFNULL(tm.LmpDt, '') AS LmpDt," +
                    " IFNULL(tm.Sp1, '') AS Sp1,IFNULL(tm.Sp2, '') AS Sp2," +
                    " (CASE WHEN tm.MoNo = '00' THEN tm.MoName ELSE IFNULL(mo.Name, '') END) AS MName," +
                    " (CASE WHEN tm.FaNo = '00' THEN tm.FaName ELSE IFNULL(fa.Name, '') END) AS FName," +
                    " IFNULL(tmm.Active, '') AS Active,IFNULL(tmm.MEnDate, '') AS MEnDate,IFNULL(tmm.MEnType, '') AS MEnType," +
                    " IFNULL(ph.MemID, '') AS phis,IFNULL(cc.MemID, '') AS cchar, IFNULL(tmm.MExDate, '') AS MExDate,IFNULL(tmm.MExType, '') AS MExType," +
                    " IFNULL(cg.MemID, '') AS care,IFNULL(va.MemID, '') AS vacc," +
                    " IFNULL(ap.MemID, '') AS anthro," +
                    " '' con_beh, " +
                    " IFNULL(tmm.isdelete, '2') AS isdelete," +
                    " (Case when length(ifnull(tmm.MExType,''))=0 then 1 else 0 end)active_member" +
                    " FROM tmpMemberMove tmm " +
                    "INNER JOIN tmpMember tm ON tmm.MemID = tm.MemID " +
                    "LEFT JOIN tmpMember mo ON tmm.HHID = mo.HHID AND tm.MoNo = mo.MSlNo " +
                    "LEFT JOIN tmpMember fa ON tmm.HHID = fa.HHID AND tm.FaNo = fa.MSlNo " +
                    "LEFT JOIN tmpPregHis ph ON tmm.MemID = ph.MemID " +
                    "LEFT JOIN ChildChar cc ON tmm.MemID = cc.MemID " +
                    "LEFT JOIN Caregiver cg ON tmm.MemID = cg.MemID " +
                    "LEFT JOIN Anthropometric ap ON tmm.MemID = ap.MemID " +
                    "LEFT JOIN ( " +
                    "    SELECT MemID, COUNT(*) AS total " +
                    "    FROM Vaccination " +
                    "    WHERE HHID = '" + HHID + "'" +
                    "    GROUP BY MemID" +
                    ") va ON tmm.MemID = va.MemID " +
                    "WHERE tmm.HHID = '" + HHID + "'" +
                    " ORDER BY tmm.MSlNo";
            List<tmpMember_DataModel> data = d.SelectMember(this, SQL);
            dataList.clear();

            dataList.addAll(data);
            if (dataList != null && !dataList.isEmpty()) {
                mAdapter = new DataAdapter(dataList);
                recyclerView.setAdapter(mAdapter);
            }
            try {
                lblTotal.setText(" (Total: " + dataList.size() + ")");
            } catch (Exception ex) {
                Connection.MessageBox(Surv_Event_list.this, ex.getMessage());
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_Event_list.this, e.getMessage());
            return;
        }
    }

    private void DataSearchPrevious(String HHID, String SelectedROUND) {
        try {
            Member_DataModel d = new Member_DataModel();

            String SQL = "SELECT tm.MemID, tmm.MSlNo, tm.Name, tm.FaNo, tm.MoNo, tm.DSSID, tm.Sex," +
                    " IFNULL(CAST((julianday('now') - julianday(tm.BDate)) AS INT), 0) AS AgeD," +
                    " IFNULL(CAST((julianday('now') - julianday(tm.BDate)) / 365.25 AS INT), 0) AS AgeY," +
                    " tm.Rth, tm.MS, tm.EduY, tm.Ocp, tm.OcpOth," +
                    " IFNULL(tm.BDate, '') AS BDate,IFNULL(tm.BDate_D, '') AS BDate_D," +
                    " IFNULL(tm.BDate_M, '') AS BDate_M,IFNULL(tm.BDate_Y, '') AS BDate_Y," +
                    " IFNULL(tm.Pstat, '') AS Pstat,IFNULL(tm.LmpDt, '') AS LmpDt," +
                    " IFNULL(tm.Sp1, '') AS Sp1,IFNULL(tm.Sp2, '') AS Sp2," +
                    " (CASE WHEN tm.MoNo = '00' THEN tm.MoName ELSE IFNULL(mo.Name, '') END) AS MName," +
                    " (CASE WHEN tm.FaNo = '00' THEN tm.FaName ELSE IFNULL(fa.Name, '') END) AS FName," +
                    " IFNULL(tmm.Active, '') AS Active,IFNULL(tmm.MEnDate, '') AS MEnDate," +
                    " IFNULL(ph.MemID, '') AS phis,IFNULL(cc.MemID, '') AS cchar," +
                    " IFNULL(cg.MemID, '') AS care,IFNULL(va.MemID, '') AS vacc," +
                    " IFNULL(ap.MemID, '') AS anthro," +
                    " '' con_beh, " +
                    " IFNULL(tmm.isdelete, '2') AS isdelete," +
                    " (Case when length(ifnull(tmm.MExType,''))=0 then 1 else 0 end)active_member" +
                    " FROM MemberMove tmm " +
                    "INNER JOIN Member tm ON tmm.MemID = tm.MemID " +
                    "LEFT JOIN Member mo ON tmm.HHID = mo.HHID AND tm.MoNo = mo.MSlNo " +
                    "LEFT JOIN Member fa ON tmm.HHID = fa.HHID AND tm.FaNo = fa.MSlNo " +
                    "LEFT JOIN PregHis ph ON tmm.MemID = ph.MemID " +
                    "LEFT JOIN ChildChar cc ON tmm.MemID = cc.MemID " +
                    "LEFT JOIN Caregiver cg ON tmm.MemID = cg.MemID " +
                    "LEFT JOIN Anthropometric ap ON tmm.MemID = ap.MemID " +
                    "LEFT JOIN ( " +
                    "    SELECT MemID, COUNT(*) AS total " +
                    "    FROM Vaccination " +
                    "    WHERE HHID = '" + HHID + "'" +
                    "    GROUP BY MemID" +
                    ") va ON tmm.MemID = va.MemID " +
                    "WHERE tmm.HHID = '" + HHID + "'" +
                    " ORDER BY tmm.MSlNo";
            List<Member_DataModel> data = d.SelectMember(this, SQL);
            dataListPrev.clear();

            dataListPrev.addAll(data);
            if (dataListPrev != null && !dataListPrev.isEmpty()) {
                mAdapterPrev = new DataAdapterPrev(dataListPrev, SelectedROUND);
                recyclerView.setAdapter(mAdapterPrev);
            }
            try {
                lblTotal.setText(" (Total: " + dataListPrev.size() + ")");
            } catch (Exception ex) {
                Connection.MessageBox(Surv_Event_list.this, ex.getMessage());
            }
        } catch (Exception e) {
            Connection.MessageBox(Surv_Event_list.this, e.getMessage());
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    public class DataAdapter extends RecyclerView.Adapter<Surv_Event_list.DataAdapter.MyViewHolder> {
        private List<tmpMember_DataModel> dataList;
        private EventAdapter eventAdapter;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout secListRow;
            CardView card_view;
            TextView Name;
            TextView MemID;
            RecyclerView innerEventRecycler;

            public MyViewHolder(View convertView) {
                super(convertView);
                secListRow = convertView.findViewById(R.id.secListRow);
                card_view = convertView.findViewById(R.id.card_view);
                Name = convertView.findViewById(R.id.Name);
                MemID = convertView.findViewById(R.id.MemID);
                innerEventRecycler = convertView.findViewById(R.id.innerEventRecycler);
            }
        }

        public DataAdapter(List<tmpMember_DataModel> datalist) {
            this.dataList = datalist;
        }

        @Override
        public Surv_Event_list.DataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_member_name, parent, false);
            return new Surv_Event_list.DataAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(Surv_Event_list.DataAdapter.MyViewHolder holder, int position) {
            final tmpMember_DataModel memberDataModel = dataList.get(position);
            if (memberDataModel.getActive().equals("2")){
                holder.card_view.setBackgroundResource(R.drawable.style_circle_line_incomplete);
            }

            tmpMaritalSts_DataModel model1 = new tmpMaritalSts_DataModel();
            String SQL1 = "Select * from tmpMaritalSts  Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpMaritalSts_DataModel> dataMaritalSts = model1.SelectAllByMember(getApplicationContext(), SQL1);

            tmpEducation_DataModel model2 = new tmpEducation_DataModel();
            String SQL2 = "Select * from tmpEducation  Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpEducation_DataModel> educationDataModels = model2.SelectAll(getApplicationContext(), SQL2);

            tmpRelation_DataModel model3 = new tmpRelation_DataModel();
            String SQL3 = "Select * from tmpRelation Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpRelation_DataModel> tmpRelationDataModels = model3.SelectAll(getApplicationContext(), SQL3);

            tmpFatherSerial_DataModel model4 = new tmpFatherSerial_DataModel();
            String SQL4 = "Select * from tmpFatherSerial Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpFatherSerial_DataModel> tmpFatherSerialDataModels = model4.SelectAll(getApplicationContext(), SQL4);

            tmpMotherSerial_DataModel model5 = new tmpMotherSerial_DataModel();
            String SQL5 = "Select * from tmpMotherSerial Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpMotherSerial_DataModel> tmpMotherSerialDataModels = model5.SelectAll(getApplicationContext(), SQL5);

            tmpSpouseSerial_DataModel model6 = new tmpSpouseSerial_DataModel();
            String SQL6 = "Select * from tmpSpouseSerial Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpSpouseSerial_DataModel> tmpSpouseSerialDataModels = model6.SelectAll(getApplicationContext(), SQL6);

            tmpDeath_DataModel model7 = new tmpDeath_DataModel();
            String SQL7 = "Select * from tmpDeath Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpDeath_DataModel> tmpDeathDataModels = model7.SelectAll(getApplicationContext(), SQL7);

            tmpMemberMove_DataModel objSave1 = new tmpMemberMove_DataModel();
            String SQL8 = "Select * from tmpMemberMove Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpMemberMove_DataModel> tmpMemberMoveDataModels = objSave1.SelectAll(getApplicationContext(), SQL8);

            tmpMigration_DataModel model9 = new tmpMigration_DataModel();
            String SQL9 = "Select * from tmpMigration Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpMigration_DataModel> tmpMigrationDataModels = model9.SelectAll(getApplicationContext(), SQL9);

            tmpMovement_DataModel model10 = new tmpMovement_DataModel();
            String SQL10 = "Select * from tmpMovement Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpMovement_DataModel> tmpMovementDataModels = model10.SelectAll(getApplicationContext(), SQL10);

            tmpNotPregnant_DataModel model11 = new tmpNotPregnant_DataModel();
            String SQL11 = "Select * from tmpNotPregnant Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpNotPregnant_DataModel> tmpNotPregnantDataModels = model11.SelectAll(getApplicationContext(), SQL11);

            tmpPregnancy_DataModel model12 = new tmpPregnancy_DataModel();
            String SQL12 = "Select * from tmpPregnancy Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpPregnancy_DataModel> tmpPregnancyDataModels = model12.SelectAll(getApplicationContext(), SQL12);

            tmpOccupation_DataModel model13 = new tmpOccupation_DataModel();
            String SQL13 = "Select * from tmpOccupation Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpOccupation_DataModel> occupationDataModels = model13.SelectAll(getApplicationContext(), SQL13);

            tmpDelivery_DataModel model14 = new tmpDelivery_DataModel();
            String SQL14 = "Select * from tmpDelivery Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd = '"+ROUND+ "'";
            List<tmpDelivery_DataModel> tmpDeliveryDataModels = model14.SelectAll(getApplicationContext(), SQL14);


            List<EventData> eventList = new ArrayList<>();
            for (tmpMaritalSts_DataModel item : dataMaritalSts) {
                EventData eventData = new EventData();
                eventData.eventID = item.getMaritStsID();
                eventData.eventName = "Marital Status";
                eventData.eventTable = "tmpMaritalSts";
                eventData.eventCode = "31";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getMSEvType();
                eventData.preEventType = item.getPrevMS();
                eventData.eventDate = item.getMSEvDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.isDeleted = item.get_isdelete();
                eventData.modifyDate = item.getMSVDate();
                eventData.note = item.getMSNote();
                eventData.Device = item.getDeviceID();
                eventData.enterUser = item.getEntryUser();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (tmpEducation_DataModel item : educationDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getEduID();
                eventData.eventName = "Education";
                eventData.eventTable = "tmpEducation";
                eventData.eventCode = "71";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getNewEdu();
                eventData.preEventType = item.getOldEdu();
                eventData.eventDate = item.getEduEvDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.isDeleted = item.get_isdelete();
                eventData.modifyDate = item.getEduVDate();
                eventData.note = item.getEduNote();
                eventData.Device = item.getDeviceID();
                eventData.enterUser = item.getEntryUser();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (tmpMotherSerial_DataModel item : tmpMotherSerialDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getMothSerialID();
                eventData.eventName = "Mother Serial";
                eventData.eventTable = "tmpMotherSerial";
                eventData.eventCode = "61";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getNewMothSl();
                eventData.preEventType = item.getOldMothSl();
                eventData.eventDate = item.getMothSlEvDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.isDeleted = item.get_isdelete();
                eventData.modifyDate = item.getMothVDate();
                eventData.note = item.getMothSlNote();
                eventData.Device = item.getDeviceID();
                eventData.enterUser = item.getEntryUser();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (tmpFatherSerial_DataModel item : tmpFatherSerialDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getFathSerialID();
                eventData.eventName = "Father Serial";
                eventData.eventTable = "tmpFatherSerial";
                eventData.eventCode = "62";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getNewFathSl();
                eventData.preEventType = item.getOldFathSl();
                eventData.eventDate = item.getFathSlEvDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.isDeleted = item.get_isdelete();
                eventData.modifyDate = item.getFathVDate();
                eventData.note = item.getFathSlNote();
                eventData.Device = item.getDeviceID();
                eventData.enterUser = item.getEntryUser();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (tmpSpouseSerial_DataModel item : tmpSpouseSerialDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getSpSerialID();
                eventData.eventName = "Spouse's Serial";
                eventData.eventTable = "tmpSpouseSerial";
                eventData.eventCode = "63";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getNewSpSl();
                eventData.preEventType = item.getOldSpSl();
                eventData.eventDate = item.getSpSlEvDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.isDeleted = item.get_isdelete();
                eventData.modifyDate = item.getSpVDate();
                eventData.note = item.getSpSlNote();
                eventData.Device = item.getDeviceID();
                eventData.enterUser = item.getEntryUser();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (tmpRelation_DataModel item : tmpRelationDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getRthID();
                eventData.eventName = "Relation to Head";
                eventData.eventTable = "tmpRelation";
                eventData.eventCode = "64";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getNewRth();
                eventData.preEventType = item.getOldRth();
                eventData.eventDate = item.getRthEvDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.isDeleted = item.get_isdelete();
                eventData.modifyDate = item.getRthVDate();
                eventData.note = item.getRthNote();
                eventData.Device = item.getDeviceID();
                eventData.enterUser = item.getEntryUser();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (tmpDeath_DataModel item : tmpDeathDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getDeathID();
                eventData.eventName = "Death";
                eventData.eventTable = "tmpDeath";
                eventData.eventCode = "55";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getDthPlace();
                eventData.preEventType = item.getDthTime();
                eventData.eventDate = item.getDthDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.isDeleted = item.get_isdelete();
                eventData.modifyDate = item.getDthVDate();
                eventData.note = item.getDthNote();
                eventData.Device = item.getDeviceID();
                eventData.enterUser = item.getEntryUser();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (tmpMigration_DataModel item : tmpMigrationDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getMigrationID();
                eventData.eventName = "Migration";
                eventData.eventTable = "tmpMigration";
                eventData.eventCode = "00";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getMigEvType();
                eventData.preEventType = item.getMigReason();
                eventData.eventDate = item.getMigDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.modifyDate = item.getMigVDate();
                eventData.note = item.getMigNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (tmpMovement_DataModel item : tmpMovementDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getMovementID();
                eventData.eventName = "Movement";
                eventData.eventTable = "tmpMovement";
                eventData.eventCode = "00";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getMoveEvType();
                eventData.preEventType = item.getMoveReason();
                eventData.eventDate = item.getMoveDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.modifyDate = item.getMoveVDate();
                eventData.note = item.getMoveNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (tmpPregnancy_DataModel item : tmpPregnancyDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getPregOccurID();
                eventData.eventName = "Pregnancy";
                eventData.eventTable = "tmpPregnancy";
                eventData.eventCode = "41";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getLMPDtType();
                eventData.eventDate = item.getLMPDt();
                eventData.modifyDate = item.getPregVdate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.note = item.getPregNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (tmpNotPregnant_DataModel item : tmpNotPregnantDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getNotPregID();
                eventData.eventName = "Not Pregnant";
                eventData.eventTable = "tmpNotPregnant";
                eventData.eventCode = "40";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getPregStatus();
                eventData.eventDate = item.getNotPregVDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.note = item.getNotPregNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }

            for (tmpOccupation_DataModel item : occupationDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getOcpID();
                eventData.eventName = "Occupation";
                eventData.eventTable = "tmpOccupation";
                eventData.eventCode = "72";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getNewOcp();
                eventData.preEventType = item.getOldOcp();
                eventData.eventDate = item.getOcpEvDate();
                eventData.modifyDate = item.getOcpVDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.note = item.getOcpNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }

            for (tmpDelivery_DataModel item : tmpDeliveryDataModels) {
                EventData eventData = new EventData();
                eventData.PregID = item.getPregOccurID();
                eventData.eventID = item.getDeliveryID();
                eventData.eventName = "Outcome";
                eventData.eventTable = "tmpDelivery";
                eventData.eventCode = "42";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getTotOut();
                eventData.eventDate = item.getDelDate();
                eventData.modifyDate = item.getDelVDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.note = item.getDelNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }

//            for(tmpMemberMove_DataModel item : tmpMemberMoveDataModels){
//                EventData eventData = new EventData();
//                eventData.eventID = "00";
//                eventData.eventName = "Member Move";
//                eventData.eventTable = "tmpMemberMove";
//                eventData.eventCode = "00";
//                eventData.memberID = item.getMemID();
//                eventData.HHID = item.getHHID();
//                eventData.memberName = memberDataModel.getName();
//                eventData.active = memberDataModel.getActive();
//                eventData.eventType = item.getMEnType();
//                eventData.preEventType = item.getMExType();
//                eventData.eventDate = item.getMEnDate();
//                eventData.DSSID = memberDataModel.getDSSID();
//                eventData.isDeleted = item.get_isdelete();
//                eventData.modifyDate = item.getMExDate();
//                eventData.note = item.getMemNote();
//                eventData.Device = item.getDeviceID();
//                eventData.enterUser = item.getEntryUser();
//                eventData.MSlNo = memberDataModel.getMSlNo();
//                eventData.round = item.getRnd();
//                eventList.add(eventData);
//            }

            holder.Name.setText(memberDataModel.getMSlNo() + ": " + memberDataModel.getName());
            //holder.MemID.setText(memberDataModel.getMemID());

            if (memberDataModel.getActive().equalsIgnoreCase("2")) {
                holder.Name.setTextColor(Color.RED);
            } else {
                holder.Name.setTextColor(getResources().getColor(R.color.blue));
            }

            eventAdapter = new EventAdapter(eventList, true);
            holder.innerEventRecycler.setItemViewCacheSize(20);
            holder.innerEventRecycler.setDrawingCacheEnabled(true);
            holder.innerEventRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            holder.innerEventRecycler.setHasFixedSize(true);
            holder.innerEventRecycler.setLayoutManager(new LinearLayoutManager(holder.innerEventRecycler.getContext()) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            holder.innerEventRecycler.setItemAnimator(new DefaultItemAnimator());
            holder.innerEventRecycler.setAdapter(eventAdapter);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    public class DataAdapterPrev extends RecyclerView.Adapter<Surv_Event_list.DataAdapterPrev.MyViewHolder> {
        private List<Member_DataModel> dataList;
        private EventAdapter eventAdapter;
        String SelectedROUND;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout secListRow;
            CardView card_view;
            TextView Name;
            TextView MemID;
            RecyclerView innerEventRecycler;

            public MyViewHolder(View convertView) {
                super(convertView);
                secListRow = convertView.findViewById(R.id.secListRow);
                card_view = convertView.findViewById(R.id.card_view);
                Name = convertView.findViewById(R.id.Name);
                MemID = convertView.findViewById(R.id.MemID);
                innerEventRecycler = convertView.findViewById(R.id.innerEventRecycler);
            }
        }

        public DataAdapterPrev(List<Member_DataModel> datalist, String SelectedROUND) {
            this.dataList = datalist;
            this.SelectedROUND = SelectedROUND;
        }

        @Override
        public Surv_Event_list.DataAdapterPrev.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_member_name, parent, false);
            return new Surv_Event_list.DataAdapterPrev.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(Surv_Event_list.DataAdapterPrev.MyViewHolder holder, int position) {
            final Member_DataModel memberDataModel = dataList.get(position);
            if (memberDataModel.getActive().equals("2")){
                holder.card_view.setBackgroundResource(R.drawable.style_circle_line_incomplete);
            }

            MaritalSts_DataModel model1 = new MaritalSts_DataModel();
            String SQL1 = "Select * from MaritalSts  Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<MaritalSts_DataModel> dataMaritalSts = model1.SelectAllByMember(getApplicationContext(), SQL1);

            Education_DataModel model2 = new Education_DataModel();
            String SQL2 = "Select * from Education  Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<Education_DataModel> educationDataModels = model2.SelectAll(getApplicationContext(), SQL2);

            Relation_DataModel model3 = new Relation_DataModel();
            String SQL3 = "Select * from Relation Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<Relation_DataModel> RelationDataModels = model3.SelectAll(getApplicationContext(), SQL3);

            FatherSerial_DataModel model4 = new FatherSerial_DataModel();
            String SQL4 = "Select * from FatherSerial Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<FatherSerial_DataModel> FatherSerialDataModels = model4.SelectAll(getApplicationContext(), SQL4);

            MotherSerial_DataModel model5 = new MotherSerial_DataModel();
            String SQL5 = "Select * from MotherSerial Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<MotherSerial_DataModel> MotherSerialDataModels = model5.SelectAll(getApplicationContext(), SQL5);

            SpouseSerial_DataModel model6 = new SpouseSerial_DataModel();
            String SQL6 = "Select * from SpouseSerial Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<SpouseSerial_DataModel> SpouseSerialDataModels = model6.SelectAll(getApplicationContext(), SQL6);

            Death_DataModel model7 = new Death_DataModel();
            String SQL7 = "Select * from Death Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<Death_DataModel> DeathDataModels = model7.SelectAll(getApplicationContext(), SQL7);

            MemberMove_DataModel objSave1 = new MemberMove_DataModel();
            String SQL8 = "Select * from MemberMove Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<MemberMove_DataModel> MemberMoveDataModels = objSave1.SelectAll(getApplicationContext(), SQL8);

            Migration_DataModel model9 = new Migration_DataModel();
            String SQL9 = "Select * from Migration Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<Migration_DataModel> MigrationDataModels = model9.SelectAll(getApplicationContext(), SQL9);

            Movement_DataModel model10 = new Movement_DataModel();
            String SQL10 = "Select * from Movement Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<Movement_DataModel> MovementDataModels = model10.SelectAll(getApplicationContext(), SQL10);

            NotPregnant_DataModel model11 = new NotPregnant_DataModel();
            String SQL11 = "Select * from tmpNotPregnant Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<NotPregnant_DataModel> NotPregnantDataModels = model11.SelectAll(getApplicationContext(), SQL11);

            Pregnancy_DataModel model12 = new Pregnancy_DataModel();
            String SQL12 = "Select * from Pregnancy Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<Pregnancy_DataModel> PregnancyDataModels = model12.SelectAll(getApplicationContext(), SQL12);

            Occupation_DataModel model13 = new Occupation_DataModel();
            String SQL13 = "Select * from Occupation Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<Occupation_DataModel> occupationDataModels = model13.SelectAll(getApplicationContext(), SQL13);

            Delivery_DataModel model14 = new Delivery_DataModel();
            String SQL14 = "Select * from Delivery Where HHID='" + HHID + "' AND MemID ='" + memberDataModel.getMemID() + "' AND Rnd <> '"+ROUND+ "' AND Rnd = '"+SelectedROUND+ "'";
            List<Delivery_DataModel> deliveryDataModels = model14.SelectAll(getApplicationContext(), SQL14);

            List<EventData> eventList = new ArrayList<>();
            for (MaritalSts_DataModel item : dataMaritalSts) {
                EventData eventData = new EventData();
                eventData.eventID = item.getMaritStsID();
                eventData.eventName = "Marital Status";
                eventData.eventTable = "MaritalSts";
                eventData.eventCode = "31";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getMSEvType();
                eventData.preEventType = item.getPrevMS();
                eventData.eventDate = item.getMSEvDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.modifyDate = item.getMSVDate();
                eventData.note = item.getMSNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (Education_DataModel item : educationDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getEduID();
                eventData.eventName = "Education";
                eventData.eventTable = "Education";
                eventData.eventCode = "71";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getNewEdu();
                eventData.preEventType = item.getOldEdu();
                eventData.eventDate = item.getEduEvDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.modifyDate = item.getEduVDate();
                eventData.note = item.getEduNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (MotherSerial_DataModel item : MotherSerialDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getMothSerialID();
                eventData.eventName = "Mother Serial";
                eventData.eventTable = "MotherSerial";
                eventData.eventCode = "61";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getNewMothSl();
                eventData.preEventType = item.getOldMothSl();
                eventData.eventDate = item.getMothSlEvDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.modifyDate = item.getMothVDate();
                eventData.note = item.getMothSlNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (FatherSerial_DataModel item : FatherSerialDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getFathSerialID();
                eventData.eventName = "Father Serial";
                eventData.eventTable = "FatherSerial";
                eventData.eventCode = "62";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getNewFathSl();
                eventData.preEventType = item.getOldFathSl();
                eventData.eventDate = item.getFathSlEvDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.modifyDate = item.getFathVDate();
                eventData.note = item.getFathSlNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (SpouseSerial_DataModel item : SpouseSerialDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getSpSerialID();
                eventData.eventName = "Spouse's Serial";
                eventData.eventTable = "SpouseSerial";
                eventData.eventCode = "63";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getNewSpSl();
                eventData.preEventType = item.getOldSpSl();
                eventData.eventDate = item.getSpSlEvDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.modifyDate = item.getSpVDate();
                eventData.note = item.getSpSlNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (Relation_DataModel item : RelationDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getRthID();
                eventData.eventName = "Relation to Head";
                eventData.eventTable = "Relation";
                eventData.eventCode = "64";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getNewRth();
                eventData.preEventType = item.getOldRth();
                eventData.eventDate = item.getRthEvDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.modifyDate = item.getRthVDate();
                eventData.note = item.getRthNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (Death_DataModel item : DeathDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getDeathID();
                eventData.eventName = "Death";
                eventData.eventTable = "Death";
                eventData.eventCode = "55";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getDthPlace();
                eventData.preEventType = item.getDthTime();
                eventData.eventDate = item.getDthDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.modifyDate = item.getDthVDate();
                eventData.note = item.getDthNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (Migration_DataModel item : MigrationDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getMigrationID();
                eventData.eventName = "Migration";
                eventData.eventTable = "Migration";
                eventData.eventCode = "00";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getMigEvType();
                eventData.preEventType = item.getMigReason();
                eventData.eventDate = item.getMigDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.modifyDate = item.getMigVDate();
                eventData.note = item.getMigNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (Movement_DataModel item : MovementDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getMovementID();
                eventData.eventName = "Movement";
                eventData.eventTable = "Movement";
                eventData.eventCode = "00";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getMoveEvType();
                eventData.preEventType = item.getMoveReason();
                eventData.eventDate = item.getMoveDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.modifyDate = item.getMoveVDate();
                eventData.note = item.getMoveNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (Pregnancy_DataModel item : PregnancyDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getPregOccurID();
                eventData.eventName = "Pregnancy";
                eventData.eventTable = "Pregnancy";
                eventData.eventCode = "41";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getLMPDtType();
                eventData.eventDate = item.getLMPDt();
                eventData.modifyDate = item.getPregVdate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.note = item.getPregNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }
            for (NotPregnant_DataModel item : NotPregnantDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getNotPregID();
                eventData.eventName = "Not Pregnant";
                eventData.eventTable = "NotPregnant";
                eventData.eventCode = "40";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getPregStatus();
                eventData.eventDate = item.getNotPregVDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.note = item.getNotPregNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }

            for (Occupation_DataModel item : occupationDataModels) {
                EventData eventData = new EventData();
                eventData.eventID = item.getOcpID();
                eventData.eventName = "Occupation";
                eventData.eventTable = "Occupation";
                eventData.eventCode = "72";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getNewOcp();
                eventData.preEventType = item.getOldOcp();
                eventData.eventDate = item.getOcpEvDate();
                eventData.modifyDate = item.getOcpVDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.note = item.getOcpNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }

            for (Delivery_DataModel item : deliveryDataModels) {
                EventData eventData = new EventData();
                eventData.PregID = item.getPregOccurID();
                eventData.eventID = item.getDeliveryID();
                eventData.eventName = "Outcome";
                eventData.eventTable = "Delivery";
                eventData.eventCode = "42";
                eventData.memberID = item.getMemID();
                eventData.HHID = item.getHHID();
                eventData.memberName = memberDataModel.getName();
                eventData.active = memberDataModel.getActive();
                eventData.eventType = item.getTotOut();
                eventData.eventDate = item.getDelDate();
                eventData.modifyDate = item.getDelVDate();
                eventData.DSSID = memberDataModel.getDSSID();
                eventData.note = item.getDelNote();
                eventData.MSlNo = memberDataModel.getMSlNo();
                eventData.round = item.getRnd();
                eventList.add(eventData);
            }


            holder.Name.setText(memberDataModel.getMSlNo() + ": " + memberDataModel.getName());
            //holder.MemID.setText(memberDataModel.getMemID());

            if (memberDataModel.getActive().equalsIgnoreCase("2")) {
                holder.Name.setTextColor(Color.RED);
            } else {
                holder.Name.setTextColor(getResources().getColor(R.color.blue));
            }

            eventAdapter = new EventAdapter(eventList, false);
            holder.innerEventRecycler.setItemViewCacheSize(20);
            holder.innerEventRecycler.setDrawingCacheEnabled(true);
            holder.innerEventRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            holder.innerEventRecycler.setHasFixedSize(true);
            holder.innerEventRecycler.setLayoutManager(new LinearLayoutManager(holder.innerEventRecycler.getContext()) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            holder.innerEventRecycler.setItemAnimator(new DefaultItemAnimator());
            holder.innerEventRecycler.setAdapter(eventAdapter);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    public class EventAdapter extends RecyclerView.Adapter<Surv_Event_list.EventAdapter.MyViewHolder> {
        private List<EventData> dataList;
        boolean isClickable;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout secListRow;
            TextView Name;
            TextView eventType;
            TextView preEventType;
            TextView eventDate;
            TextView modifyDate;
            TextView eventNote;
            ImageView img_arrow;

            public MyViewHolder(View convertView) {
                super(convertView);
                secListRow = convertView.findViewById(R.id.secListRow);
                Name = convertView.findViewById(R.id.Name);
                img_arrow = convertView.findViewById(R.id.img_arrow);
                eventType = convertView.findViewById(R.id.eventType);
                preEventType = convertView.findViewById(R.id.preEventType);
                eventDate = convertView.findViewById(R.id.eventDate);
                modifyDate = convertView.findViewById(R.id.modifyDate);
                eventNote = convertView.findViewById(R.id.eventNote);
            }
        }

        public EventAdapter(List<EventData> datalist, boolean isClickable) {
            this.dataList = datalist;
            this.isClickable = isClickable;
        }

        @Override
        public Surv_Event_list.EventAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_items, parent, false);
            return new Surv_Event_list.EventAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(Surv_Event_list.EventAdapter.MyViewHolder holder, int position) {
            final EventData data = dataList.get(position);
            holder.Name.setText("Event: " + data.eventName);
            if (isClickable && data.active.equals("1")){
                holder.img_arrow.setVisibility(View.VISIBLE);
            } else {
                holder.img_arrow.setVisibility(View.GONE);
            }
            List<String> listEDU = Global_CodeList.Get_EDUCATION();
            if (data.eventCode.equals("31")) {
                if (data.eventType.equals("01")) {
                    holder.eventType.setText("New Event: Married");
                } else if (data.eventType.equals("02")) {
                    holder.eventType.setText("New Event: Married (Polygamous)");
                } else if (data.eventType.equals("03")) {
                    holder.eventType.setText("New Event: Regular partner(Cohabited)");
                } else if (data.eventType.equals("04")) {
                    holder.eventType.setText("New Event: Divorced");
                } else if (data.eventType.equals("05")) {
                    holder.eventType.setText("New Event: Separated");
                } else if (data.eventType.equals("06")) {
                    holder.eventType.setText("New Event: Widow/widower");
                } else if (data.eventType.equals("00")) {
                    holder.eventType.setText("New Event: Single/Never married");
                }

                if (data.preEventType.equals("01")) {
                    holder.preEventType.setText("Pre Event: Married");
                } else if (data.preEventType.equals("02")) {
                    holder.preEventType.setText("Pre Event: Married (Polygamous)");
                } else if (data.preEventType.equals("03")) {
                    holder.preEventType.setText("Pre Event: Regular partner(Cohabited)");
                } else if (data.preEventType.equals("04")) {
                    holder.preEventType.setText("Pre Event: Divorced");
                } else if (data.preEventType.equals("05")) {
                    holder.preEventType.setText("Pre Event: Separated");
                } else if (data.preEventType.equals("06")) {
                    holder.preEventType.setText("Pre Event: Widow/widower");
                } else if (data.preEventType.equals("00")) {
                    holder.preEventType.setText("Pre Event: Single/Never married");
                }
                holder.eventDate.setText("Event Date: " + Global.DateConvertDMY(data.eventDate));
                holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));
            } else if (data.eventCode.equals("71")) {
                if (isClickable) {
                    holder.eventType.setText("New Event: " + getTextFromCode(data.eventType, listEDU));
                    holder.preEventType.setText("Pre Event: " +  getTextFromCode(data.preEventType, listEDU));
                    holder.eventDate.setText("Event Date: " + Global.DateConvertDMY(data.eventDate));
                    holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));
                } else {
                    holder.eventType.setText("New Event: " + getTextFromCode(data.eventType, listEDU));
                    holder.preEventType.setText("Pre Event: " +  getTextFromCode(data.preEventType, listEDU));
                    holder.eventDate.setText("Event Date: " + Global.DateConvertDMY(data.eventDate));
                    holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));
                }

            } else if (data.eventCode.equals("61")) {
                if (isClickable) {
                    tmpMotherSerial_DataModel model5 = new tmpMotherSerial_DataModel();
                    holder.eventType.setText("Mother: " + model5.getDataName(data.eventType, data.HHID, getApplicationContext()));
                    holder.preEventType.setText("Pre Event: " + model5.getDataName(data.preEventType, data.HHID, getApplicationContext()));
                    holder.eventDate.setText("Event Date: " + Global.DateConvertDMY(data.eventDate));
                    holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));
                } else {
                    MotherSerial_DataModel model5 = new MotherSerial_DataModel();
                    holder.eventType.setText("Mother: " + model5.getDataName(data.eventType, data.HHID, getApplicationContext()));
                    holder.preEventType.setText("Pre Event: " + model5.getDataName(data.preEventType, data.HHID, getApplicationContext()));
                    holder.eventDate.setText("Event Date: " + Global.DateConvertDMY(data.eventDate));
                    holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));
                }

            } else if (data.eventCode.equals("62")) {
                if (isClickable) {
                    tmpFatherSerial_DataModel model4 = new tmpFatherSerial_DataModel();
                    holder.eventType.setText("Father: " + model4.getDataName(data.eventType, data.HHID, getApplicationContext()));
                    holder.preEventType.setText("Pre E: " + model4.getDataName(data.preEventType, data.HHID, getApplicationContext()));
                    holder.eventDate.setText("Event Date: " + Global.DateConvertDMY(data.eventDate));
                    holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));
                } else {
                    FatherSerial_DataModel model4 = new FatherSerial_DataModel();
                    holder.eventType.setText("Father: " + model4.getDataName(data.eventType, data.HHID, getApplicationContext()));
                    holder.preEventType.setText("Pre Event: " + model4.getDataName(data.preEventType, data.HHID, getApplicationContext()));
                    holder.eventDate.setText("Event Date: " + Global.DateConvertDMY(data.eventDate));
                    holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));
                }

            } else if (data.eventCode.equals("63")) {
                if (isClickable) {
                    tmpSpouseSerial_DataModel model6 = new tmpSpouseSerial_DataModel();
                    holder.eventType.setText("Spouse: " + model6.getDataName(data.eventType, data.HHID, getApplicationContext()));
                    holder.preEventType.setText("Pre Event: " + model6.getDataName(data.preEventType, data.HHID, getApplicationContext()));
                    holder.eventDate.setText("Event Date: " + Global.DateConvertDMY(data.eventDate));
                    holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));
                } else {
                    SpouseSerial_DataModel model6 = new SpouseSerial_DataModel();
                    holder.eventType.setText("Spouse: " + model6.getDataName(data.eventType, data.HHID, getApplicationContext()));
                    holder.preEventType.setText("Pre Event: " + model6.getDataName(data.preEventType, data.HHID, getApplicationContext()));
                    holder.eventDate.setText("Event Date: " + Global.DateConvertDMY(data.eventDate));
                    holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));
                }

            } else if (data.eventCode.equals("64")) {
                if (isClickable) {
                    tmpRelation_DataModel model3 = new tmpRelation_DataModel();
                    holder.eventType.setText("New Relation: " + model3.getDataName(data.eventType, getApplicationContext()));
                    holder.preEventType.setText("Pre Relation: " + model3.getDataName(data.preEventType, getApplicationContext()));
                    holder.eventDate.setText("Event Date: " + Global.DateConvertDMY(data.eventDate));
                    holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));
                } else {
                    Relation_DataModel model3 = new Relation_DataModel();
                    holder.eventType.setText("New Relation: " + model3.getDataName(data.eventType, getApplicationContext()));
                    holder.preEventType.setText("Pre Relation: " + model3.getDataName(data.preEventType, getApplicationContext()));
                    holder.eventDate.setText("Event Date: " + Global.DateConvertDMY(data.eventDate));
                    holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));
                }

            } else if (data.eventCode.equals("55")) {
                holder.eventType.setText("Died on: " + Global.DateConvertDMY(data.eventDate) + " " + data.preEventType);
                holder.eventDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));
                holder.modifyDate.setVisibility(View.GONE);

                if (data.eventType.equals("01")) {
                    holder.preEventType.setText("Death Place: 01-At deceaseds home");
                } else if (data.eventType.equals("02")) {
                    holder.preEventType.setText("Death Place: 02-At a relatives home");
                } else if (data.eventType.equals("03")) {
                    holder.preEventType.setText("Death Place: 03-At a healers home");
                } else if (data.eventType.equals("04")) {
                    holder.preEventType.setText("Death Place: 04-At other home");
                } else if (data.eventType.equals("05")) {
                    holder.preEventType.setText("Death Place: 05-At a public health facility");
                } else if (data.eventType.equals("06")) {
                    holder.preEventType.setText("Death Place: 06-At a private health facility");
                } else if (data.eventType.equals("07")) {
                    holder.preEventType.setText("Death Place: 07-At an NGO health facility");
                } else if (data.eventType.equals("08")) {
                    holder.preEventType.setText("Death Place: 08-On route to a health facility");
                } else if (data.eventType.equals("09")) {
                    holder.preEventType.setText("Death Place: 09-At church/mosque");
                } else if (data.eventType.equals("97")) {
                    holder.preEventType.setText("Death Place: 97-Other");
                } else if (data.eventType.equals("98")) {
                    holder.preEventType.setText("Death Place: 98-Dont know");
                } else if (data.eventType.equals("99")) {
                    holder.preEventType.setText("Death Place: 99-Refused to respond");
                }

            } else if (data.eventCode.equals("40")) {
                if (data.eventType.equals("49")) {
                    holder.eventType.setText("Absent");
                } else if (data.eventType.equals("40")) {
                    holder.eventType.setText("Not pregnant");
                } else if (data.eventType.equals("3")) {
                    holder.eventType.setText("Refused to provide pregnancy information");
                }
                holder.eventDate.setText("Visit Date: " + Global.DateConvertDMY(data.eventDate));

            } else if (data.eventCode.equals("41")) {
                holder.eventType.setText("Pregnancy");
                holder.eventDate.setText("LMP Date: " + Global.DateConvertDMY(data.eventDate));
                holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));

            } else if (data.eventCode.equals("42")) {
                holder.eventType.setText("Delivery");
                holder.eventDate.setText("Delivery Date: " + Global.DateConvertDMY(data.eventDate));
                holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));

            } else if (data.eventCode.equals("72")) {
                switch (data.eventType) {
                    case "01":
                        holder.eventType.setText("None");
                        break;
                    case "11":
                        holder.eventType.setText("Farmer");
                        break;
                    case "02":
                        holder.eventType.setText("Student");
                        break;
                    case "33":
                        holder.eventType.setText("Business man/woman");
                        break;
                    case "21":
                        holder.eventType.setText("Artisan");
                        break;
                    case "35":
                        holder.eventType.setText("Private sector employee");
                        break;
                    case "36":
                        holder.eventType.setText("Civil/Public servant");
                        break;
                    case "06":
                        holder.eventType.setText("Retired");
                        break;
                    case "97":
                        holder.eventType.setText("Other specify");
                        break;
                    case "98":
                        holder.eventType.setText("Don’t know");
                        break;
                    case "99":
                        holder.eventType.setText("Refused to respond");
                        break;
                    default:
                        break;
                }
                switch (data.preEventType) {
                    case "01":
                        holder.preEventType.setText("None");
                        break;
                    case "11":
                        holder.preEventType.setText("Farmer");
                        break;
                    case "02":
                        holder.preEventType.setText("Student");
                        break;
                    case "33":
                        holder.preEventType.setText("Business man/woman");
                        break;
                    case "21":
                        holder.preEventType.setText("Artisan");
                        break;
                    case "35":
                        holder.preEventType.setText("Private sector employee");
                        break;
                    case "36":
                        holder.preEventType.setText("Civil/Public servant");
                        break;
                    case "06":
                        holder.preEventType.setText("Retired");
                        break;
                    case "97":
                        holder.preEventType.setText("Other specify");
                        break;
                    case "98":
                        holder.preEventType.setText("Don’t know");
                        break;
                    case "99":
                        holder.preEventType.setText("Refused to respond");
                        break;
                    default:
                        break;
                }

                holder.eventDate.setText("Event Date: " + Global.DateConvertDMY(data.eventDate));
                holder.modifyDate.setText("Visit Date: " + Global.DateConvertDMY(data.modifyDate));

            } else if (data.eventCode.equals("00")) {
                if (data.eventType.equals("20")) {
                    holder.eventType.setText("Registration");
                    holder.eventDate.setText("Reg Date: " + Global.DateConvertDMY(data.eventDate));
                } else if (data.eventType.equals("21")) {
                    holder.eventType.setText("Migration-in from outside Demographic Surveillance System (DSS) Area");
                    holder.eventDate.setText("Mig Date: " + Global.DateConvertDMY(data.eventDate));
                } else if (data.eventType.equals("22")) {
                    holder.eventType.setText("Internal Migration in");
                    holder.eventDate.setText("Mig Date: " + Global.DateConvertDMY(data.eventDate));
                } else if (data.eventType.equals("12")) {
                    holder.eventType.setText("Member Correction");
                    holder.eventDate.setText("Event Date: " + Global.DateConvertDMY(data.eventDate));
                } else if (data.eventType.equals("51")) {
                    holder.eventType.setText("Migration-out to outside Demographic Surveillance System (DSS) Area");
                    holder.eventDate.setText("Mig Date: " + Global.DateConvertDMY(data.eventDate));
                } else if (data.eventType.equals("52")) {
                    holder.eventType.setText("Internal Migration out");
                    holder.eventDate.setText("Mig Date: " + Global.DateConvertDMY(data.eventDate));
                }

                if (data.preEventType.equals("51")) {
                    holder.modifyDate.setText("Exit Date: " + Global.DateConvertDMY(data.modifyDate));
                    holder.preEventType.setText("Migration-out to outside Demographic Surveillance System (DSS) Area");
                } else if (data.preEventType.equals("52")) {
                    holder.modifyDate.setText("Exit Date: " + Global.DateConvertDMY(data.modifyDate));
                    holder.preEventType.setText("Internal Migration out");
                }
            }

            if (data.note.equals("")) {
                holder.eventNote.setVisibility(View.GONE);
            }
            holder.eventNote.setText(data.note);
            holder.secListRow.setOnClickListener(view -> {
                if (isClickable) {
                    if (data.active.equals("1")) {

                        if (data.eventCode.equals("31")) {
                            Bundle IDbundle = new Bundle();
                            IDbundle.putString("MaritStsID", data.eventID);
                            IDbundle.putString("MemID", data.memberID);
                            IDbundle.putString("HHID", data.HHID);
                            IDbundle.putString("evtype", data.eventCode);
                            IDbundle.putString("ms", data.preEventType);
                            IDbundle.putString("round", data.round);
                            Intent intent = new Intent(getApplicationContext(), Surv_MaritalSts.class);
                            intent.putExtras(IDbundle);
                            startActivityForResult(intent, 1);

                        } else if (data.eventCode.equals("61")) {
                            Bundle IDbundle = new Bundle();
                            IDbundle.putString("MothSerialID", data.eventID);
                            IDbundle.putString("MemID", data.memberID);
                            IDbundle.putString("HHID", data.HHID);
                            IDbundle.putString("evtype", data.eventCode);
                            IDbundle.putString("round", data.round);
                            IDbundle.putString("age", data.agedays);
                            Intent intent = new Intent(getApplicationContext(), Surv_MotherSerial.class);
                            intent.putExtras(IDbundle);
                            startActivityForResult(intent, 1);

                        } else if (data.eventCode.equals("62")) {
                            Bundle IDbundle = new Bundle();
                            IDbundle.putString("FathSerialID", data.eventID);
                            IDbundle.putString("MemID", data.memberID);
                            IDbundle.putString("HHID", data.HHID);
                            IDbundle.putString("evtype", data.eventCode);
                            IDbundle.putString("round", data.round);
                            Intent intent = new Intent(getApplicationContext(), Surv_FatherSerial.class);
                            intent.putExtras(IDbundle);
                            startActivityForResult(intent, 1);

                        } else if (data.eventCode.equals("63")) {
                            Bundle IDbundle = new Bundle();
                            IDbundle.putString("SpSerialID", data.eventID);
                            IDbundle.putString("MemID", data.memberID);
                            IDbundle.putString("HHID", data.HHID);
                            IDbundle.putString("evtype", data.eventCode);
                            IDbundle.putString("round", data.round);
                            Intent intent = new Intent(getApplicationContext(), Surv_SpouseSerial.class);
                            intent.putExtras(IDbundle);
                            startActivityForResult(intent, 1);

                        } else if (data.eventCode.equals("64")) {
                            Bundle IDbundle = new Bundle();
                            IDbundle.putString("RthID", data.eventID);
                            IDbundle.putString("MemID", data.memberID);
                            IDbundle.putString("HHID", data.HHID);
                            IDbundle.putString("evtype", data.eventCode);
                            IDbundle.putString("round", data.round);
                            Intent intent = new Intent(getApplicationContext(), Surv_Relation.class);
                            intent.putExtras(IDbundle);
                            startActivityForResult(intent, 1);

                        } else if (data.eventCode.equals("55")) {
                            Bundle IDbundle = new Bundle();
                            IDbundle.putString("DeathID", data.eventID);
                            IDbundle.putString("MemID", data.memberID);
                            IDbundle.putString("HHID", data.HHID);
                            IDbundle.putString("evtype", data.eventCode);
                            IDbundle.putString("round", data.round);
                            Intent intent = new Intent(getApplicationContext(), Surv_Death.class);
                            intent.putExtras(IDbundle);
                            startActivityForResult(intent, 1);

                        } else if (data.eventCode.equals("71")) {
                            Bundle IDbundle = new Bundle();
                            IDbundle.putString("EduID", data.eventID);
                            IDbundle.putString("MemID", data.memberID);
                            IDbundle.putString("HHID", data.HHID);
                            IDbundle.putString("evtype", data.eventCode);
                            IDbundle.putString("round", data.round);
                            IDbundle.putString("isEdit", "2");
                            Intent intent = new Intent(getApplicationContext(), Surv_Education.class);
                            intent.putExtras(IDbundle);
                            startActivityForResult(intent, 1);

                        } else if (data.eventCode.equals("40")) {
                            Bundle IDbundle = new Bundle();
                            IDbundle.putString("NotPregID", data.eventID);
                            IDbundle.putString("MemID", data.memberID);
                            IDbundle.putString("HHID", data.HHID);
                            IDbundle.putString("evtype", data.eventCode);
                            IDbundle.putString("round", data.round);
                            Intent intent = new Intent(getApplicationContext(), Surv_NotPregnant.class);
                            intent.putExtras(IDbundle);
                            startActivityForResult(intent, 1);

                        } else if (data.eventCode.equals("41")) {
                            Bundle IDbundle = new Bundle();
                            IDbundle.putString("PregOccurID", data.eventID);
                            IDbundle.putString("MemID", data.memberID);
                            IDbundle.putString("HHID", data.HHID);
                            IDbundle.putString("evtype", data.eventCode);
                            IDbundle.putString("round", data.round);
                            IDbundle.putString("isEdit", "1");
                            Intent intent = new Intent(getApplicationContext(), Surv_Pregnancy.class);
                            intent.putExtras(IDbundle);
                            startActivityForResult(intent, 1);

                        } else if (data.eventCode.equals("42")) {
                            Bundle IDbundle = new Bundle();
                            IDbundle.putString("DeliveryID", data.eventID);
                            IDbundle.putString("MemID", data.memberID);
                            IDbundle.putString("HHID", data.HHID);
                            IDbundle.putString("HHNO", HHNO);
                            IDbundle.putString("evtype", data.eventCode);
                            IDbundle.putString("round", data.round);
                            IDbundle.putString("pregid", data.PregID);
                            IDbundle.putString("edit", "2");

                            Intent intent = new Intent(getApplicationContext(), Surv_Delivery.class);
                            intent.putExtras(IDbundle);
                            startActivityForResult(intent, 1);

                        } else if (data.eventCode.equals("72")) {
                            Bundle IDbundle = new Bundle();
                            IDbundle.putString("OcpID", data.eventID);
                            IDbundle.putString("MemID", data.memberID);
                            IDbundle.putString("HHID", data.HHID);
                            IDbundle.putString("evtype", data.eventCode);
                            IDbundle.putString("round", data.round);
                            Intent intent = new Intent(getApplicationContext(), Surv_Occupation.class);
                            intent.putExtras(IDbundle);
                            startActivityForResult(intent, 1);

                        } else if (data.eventCode.equals("00")) {
                            if (data.eventType.equals("20") || data.eventType.equals("12")) {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("MigrationID", data.eventID);
                                IDbundle.putString("MemID", data.memberID);
                                IDbundle.putString("HHID", data.HHID);
                                IDbundle.putString("evtype", "12");
                                IDbundle.putString("round", data.round);
                                IDbundle.putString("vdate", Global.DateNowYMD());
                                IDbundle.putString("dod", "");
                                IDbundle.putString("MoID", "");
                                IDbundle.putString("LiveBirthID", "");
                                IDbundle.putString("TmpMigrationID", "");
                                Intent intent = new Intent(getApplicationContext(), Surv_Member.class);
                                intent.putExtras(IDbundle);
                                startActivityForResult(intent, 1);

                            } else if (data.eventType.equals("22") || data.eventType.equals("52")) {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("MovementID", data.eventID);
                                IDbundle.putString("MemID", data.memberID);
                                IDbundle.putString("HHID", data.HHID);
                                IDbundle.putString("evtype", data.eventType);
                                IDbundle.putString("round", data.round);
                                IDbundle.putString("edit", "2");
                                Intent intent = new Intent(getApplicationContext(), Surv_Movement.class);
                                intent.putExtras(IDbundle);
                                startActivityForResult(intent, 1);

                            } else if (data.eventType.equals("21") || data.eventType.equals("51")) {
                                Bundle IDbundle = new Bundle();
                                IDbundle.putString("MigrationID", data.eventID);
                                IDbundle.putString("MemID", data.memberID);
                                IDbundle.putString("HHID", data.HHID);
                                IDbundle.putString("evtype", data.eventType);
                                IDbundle.putString("round", data.round);
                                Intent intent = new Intent(getApplicationContext(), Surv_Migration.class);
                                intent.putExtras(IDbundle);
                                startActivityForResult(intent, 1);
                            }
                        }

                    } else {
                        Connection.MessageBox(Surv_Event_list.this, "Member is not active in this Household");
                        return;
                    }
                } else {
                    Connection.MessageBox(Surv_Event_list.this, "Previous event is not editable");
                    return;
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        DataSearch(HHID);
    }

    public  String getTextFromCode(String code, List<String> listEDU) {
        // Iterate over the listEDU array list
        for (String item : listEDU) {
            // Split the item into code and text using "-"
            String[] parts = item.split("-");
            // If the code matches the code portion of the item, return the text
            if (parts[0].trim().equals(code)) {
                // Return the text portion of the item
                return parts[1].trim();
            }
        }
        // If no match is found, return null or an appropriate default value
        return null;
    }

}