package forms_activity;

import java.util.ArrayList;
 import java.util.List;
 import android.app.*;
 import android.app.AlertDialog;
 import android.content.Context;
 import android.content.DialogInterface;
 import android.content.Intent;
 import android.location.Location;
 import android.view.KeyEvent;
 import android.os.Bundle;
 import android.view.View;
 import android.view.MotionEvent;
 import android.view.ViewGroup;
 import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
 import android.widget.Button;
 import android.widget.ImageButton;
 import Common.*;
import forms_datamodel.VaccinationListMaster_DataModel;
import forms_datamodel.Vaccination_DataModel;
 import android.content.res.TypedArray;
 import android.graphics.Canvas;
 import android.graphics.Rect;
 import android.graphics.drawable.Drawable;
 import android.view.GestureDetector;
 import android.widget.EditText;
 import android.view.WindowManager;
 import Utility.*;
import forms_datamodel.tmpSES_DataModel;

import java.util.Calendar;
 import android.widget.DatePicker;
 import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;
 import androidx.constraintlayout.widget.ConstraintLayout;

import org.icddrb.champs_lc_dss.R;

public class Vaccination_list extends AppCompatActivity {
    boolean networkAvailable=false;
    Location currentLocation; 
    double currentLatitude,currentLongitude; 
    //Disabled Back/Home key
    //--------------------------------------------------------------------------------------------------
    @Override 
    public boolean onKeyDown(int iKeyCode, KeyEvent event)
    {
        if(iKeyCode == KeyEvent.KEYCODE_BACK || iKeyCode == KeyEvent.KEYCODE_HOME) 
             { return false; }
        else { return true;  }
    }
    String VariableID;
    private int mDay;
    private int mMonth;
    private int mYear;
     final int DATE_DIALOG = 1;
     final int TIME_DIALOG = 2;

    Connection C;
    Global g;
    private List<Vaccination_DataModel> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
     String TableName;

    TextView lblHeading;
    TextView lblTotal;
    Button btnAdd;
    ImageButton btnSearch;
    EditText txtSearch;
    EditText dtpFDate;
    EditText dtpTDate;
    LinearLayout secMemID;
    View lineMemID;
    TextView VlblMemID;
    EditText txtMemID;
    LinearLayout secCardAvailable;
    View lineCardAvailable;
    TextView VlblCardAvailable;
    RadioGroup rdogrpCardAvailable;
    RadioButton rdoCardAvailable1;
    RadioButton rdoCardAvailable2;
    LinearLayout secPrevCardAvailable;
    View linePrevCardAvailable;
    TextView lblPrevCardAvailable;
    TextView VlblPrevCardAvailable;
    RadioGroup rdogrpPrevCardAvailable;
    RadioButton rdoPrevCardAvailable1;
    RadioButton rdoPrevCardAvailable2;
    FrameLayout FrameVaccineList;

     String STARTTIME = "";
     String DEVICEID  = "";

     String ENTRYUSER = "";

    ConstraintLayout ll_no_data;

    Bundle IDbundle;
     String VACCID = "";
     String MEMID = "";
     String HHID = "";
     String VaccCode = "";
     String VISIT_DATE = "";
     String BIRTH_DATE = "";
     int MODULEID   = 0;
     int LANGUAGEID = 0;

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.vaccination_list);
         getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         C = new Connection(this);
         g = Global.getInstance();
         STARTTIME = g.CurrentTime24();

         DEVICEID = MySharedPreferences.getValue(this, "deviceid");
         ENTRYUSER = MySharedPreferences.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         HHID = IDbundle.getString("HHID");
         MEMID = IDbundle.getString("MemID");
         VISIT_DATE = IDbundle.getString("visitdate"); //dd/mm/yyyy
         BIRTH_DATE = IDbundle.getString("bdate"); //yyyy-mm-dd

         ((TextView)findViewById(R.id.tv_member_name)).setText(IDbundle.getString("MemName","[Name]"));



         TableName = "Vaccination";
         MODULEID = 50;
         LANGUAGEID = Integer.parseInt(MySharedPreferences.getValue(this, "languageid"));

         lblHeading = (TextView)findViewById(R.id.lblHeading);
         lblTotal = (TextView)findViewById(R.id.lblTotal);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 finish();
             }});
         FrameVaccineList = (FrameLayout) findViewById(R.id.FrameVaccineList);
         FrameVaccineList.setVisibility(View.GONE);


         Initialization();


         //Hide all skip variables
         secPrevCardAvailable.setVisibility(View.GONE);
         linePrevCardAvailable.setVisibility(View.GONE);
         FrameVaccineList.setVisibility(View.GONE);


         btnAdd = (Button) findViewById(R.id.btnAdd);
         btnAdd.setOnClickListener(new View.OnClickListener() {

             public void onClick(View view) {
                         Bundle IDbundle = new Bundle();
                         IDbundle.putString("VaccID", "");
                         Intent intent = new Intent(getApplicationContext(), Vaccination.class);
                         intent.putExtras(IDbundle);
                         startActivityForResult(intent, 1);

             }});

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        ll_no_data = findViewById(R.id.ll_no_data);
        mAdapter = new DataAdapter(dataList);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        Connection.LocalizeLanguage(Vaccination_list.this, MODULEID, LANGUAGEID);

        DataSearch();


     }
     catch(Exception  e)
     {
         Connection.MessageBox(Vaccination_list.this, e.getMessage());
         return;
     }
 }
    private void Initialization()
    {
        try
        {
            secMemID=(LinearLayout)findViewById(R.id.secMemID);
            lineMemID=(View)findViewById(R.id.lineMemID);
            VlblMemID=(TextView) findViewById(R.id.VlblMemID);
            txtMemID=(EditText) findViewById(R.id.txtMemID);
            txtMemID.setText(MEMID);
            txtMemID.setEnabled(false);
            secCardAvailable=(LinearLayout)findViewById(R.id.secCardAvailable);
            lineCardAvailable=(View)findViewById(R.id.lineCardAvailable);
            VlblCardAvailable = (TextView) findViewById(R.id.VlblCardAvailable);
            rdogrpCardAvailable = (RadioGroup) findViewById(R.id.rdogrpCardAvailable);
            rdoCardAvailable1 = (RadioButton) findViewById(R.id.rdoCardAvailable1);
            rdoCardAvailable2 = (RadioButton) findViewById(R.id.rdoCardAvailable2);




            secPrevCardAvailable = findViewById(R.id.secPrevCardAvailable);
            linePrevCardAvailable = findViewById(R.id.linePrevCardAvailable);
//            lblPrevCardAvailable =  findViewById(R.id.lblPrevCardAvailable);
            VlblPrevCardAvailable =  findViewById(R.id.VlblPrevCardAvailable);
            rdogrpPrevCardAvailable =  findViewById(R.id.rdogrpPrevCardAvailable);
            rdoPrevCardAvailable1 =  findViewById(R.id.rdoPrevCardAvailable1);
            rdoPrevCardAvailable2 =  findViewById(R.id.rdoPrevCardAvailable2);


            rdogrpCardAvailable.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {

                    DataSave();

                    String rbData = "";
                    RadioButton rb;
                    String[] d_rdogrpCardAvailable = new String[]{"1", "2"};
                    for (int i = 0; i < rdogrpCardAvailable.getChildCount(); i++) {
                        rb = (RadioButton) rdogrpCardAvailable.getChildAt(i);
                        if (rb.isChecked()) rbData = d_rdogrpCardAvailable[i];
                    }

                    if (rbData.equalsIgnoreCase("1")) {
                        secPrevCardAvailable.setVisibility(View.GONE);
                        linePrevCardAvailable.setVisibility(View.GONE);
                        rdogrpPrevCardAvailable.clearCheck();
                        FrameVaccineList.setVisibility(View.VISIBLE);
                    } else {
                        if(rdoPrevCardAvailable1.isChecked()){
                            FrameVaccineList.setVisibility(View.VISIBLE);
                        }
                        else {
                            FrameVaccineList.setVisibility(View.GONE);
                        }
                        secPrevCardAvailable.setVisibility(View.VISIBLE);
                        linePrevCardAvailable.setVisibility(View.VISIBLE);
                    }
                }
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

            rdogrpPrevCardAvailable.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    DataSave();

                    RadioButton rb;
                    String rbData = "";

                    String[] d_rdogrpPrevCardAvailable = new String[]{"1","2"};

                    for(int i=0; i < rdogrpPrevCardAvailable.getChildCount(); i++){
                        rb = (RadioButton) rdogrpPrevCardAvailable.getChildAt(i);
                        if(rb.isChecked()) rbData = d_rdogrpPrevCardAvailable[i];
                    }

                    if(rbData.equalsIgnoreCase("1")){
                        FrameVaccineList.setVisibility(View.VISIBLE);
                    }
                    else{
                        FrameVaccineList.setVisibility(View.GONE);
                    }
                }
            });

        }
        catch(Exception  e)
        {
            Connection.MessageBox(Vaccination_list.this, e.getMessage());
            return;
        }
    }

    private void DataSave()
    {
        try
        {
            String SQL = "";
            RadioButton rb;

            VaccinationListMaster_DataModel objSave = new VaccinationListMaster_DataModel();
            objSave.setMemID(txtMemID.getText().toString());
            String[] d_rdogrpCardAvailable = new String[] {"1","2"};
            objSave.setCardAvailable("");
            for (int i = 0; i < rdogrpCardAvailable.getChildCount(); i++)
            {
                rb = (RadioButton)rdogrpCardAvailable.getChildAt(i);
                if (rb.isChecked()) objSave.setCardAvailable(d_rdogrpCardAvailable[i]);
            }


            String[] d_rdogrpPrevCardAvailable = new String[] {"1","2"};
            objSave.setPrevCardAvailable("");
            for (int i = 0; i < rdogrpPrevCardAvailable.getChildCount(); i++)
            {
                rb = (RadioButton) rdogrpPrevCardAvailable.getChildAt(i);
                if (rb.isChecked()) objSave.setPrevCardAvailable(d_rdogrpPrevCardAvailable[i]);
            }


            objSave.setStartTime(STARTTIME);
            objSave.setEndTime(g.CurrentTime24());
            objSave.setDeviceID(DEVICEID);
            objSave.setEntryUser(ENTRYUSER); //from data entry user list
            objSave.setLat(MySharedPreferences.getValue(this, "lat"));
            objSave.setLon(MySharedPreferences.getValue(this, "lon"));

            String status = objSave.SaveUpdateData(this);

        }
        catch(Exception  e)
        {
            Connection.MessageBox(Vaccination_list.this, e.getMessage());
            return;
        }
    }
 
 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if (resultCode == Activity.RESULT_CANCELED) {
         //Write your code if there's no result
     } else {
         DataSearch();
     }
 }

 private void DataSearch()
     {
       try
        {
     
           Vaccination_DataModel d = new Vaccination_DataModel();
           VaccinationListMaster_DataModel dmaster = new VaccinationListMaster_DataModel();
//             String SQL = "Select * from "+ TableName +"  Where VaccID like('"+ SearchText +"%') and isdelete=2";
            String SQL = "Select VaccCode,VaccName from VaccinationList where isdelete = 2 order by cast (VaccSequence as int) asc";
            String SQLmaster = "Select * from VaccinationListMaster where MemID='"+MEMID+"'";
             List<Vaccination_DataModel> data = d.SelectVaccAll(this, SQL);
             List<VaccinationListMaster_DataModel> datamaster = dmaster.SelectAll(this, SQLmaster);
             dataList.clear();

             dataList.addAll(data);
             if (dataList != null && !dataList.isEmpty())
             {
                 recyclerView.setVisibility(View.VISIBLE);
                 ll_no_data.setVisibility(View.GONE);
             }
             else
             {
                 recyclerView.setVisibility(View.GONE);
                 ll_no_data.setVisibility(View.VISIBLE);
             }

            for(VaccinationListMaster_DataModel item : datamaster){
                if(item.getMemID() != null){

                    RadioButton rb;
                    txtMemID.setText(item.getMemID());
                    String[] d_rdogrpCardAvailable = new String[] {"1","2"};
                    for (int i = 0; i < d_rdogrpCardAvailable.length; i++)
                    {
                        if (String.valueOf(item.getCardAvailable()).equals(String.valueOf(d_rdogrpCardAvailable[i])))
                        {
                            rb = (RadioButton)rdogrpCardAvailable.getChildAt(i);
                            rb.setChecked(true);
//
//                            if (item.getCardAvailable().equalsIgnoreCase("1")) {
//                                FrameVaccineList.setVisibility(View.VISIBLE);
//                            } else {
//                                FrameVaccineList.setVisibility(View.GONE);
//                            }
                        }
                    }


                    String[] d_rdogrpPrevCardAvailable = new String[] {"1","2"};
                    for (int i = 0; i < d_rdogrpPrevCardAvailable.length; i++)
                    {
                        if (String.valueOf(item.getPrevCardAvailable()).equals(String.valueOf(d_rdogrpPrevCardAvailable[i])))
                        {
                            rb = (RadioButton) rdogrpPrevCardAvailable.getChildAt(i);
                            rb.setChecked(true);


                            if (item.getPrevCardAvailable().equalsIgnoreCase("1")) {
                                FrameVaccineList.setVisibility(View.VISIBLE);
                            } else {
                                FrameVaccineList.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            }


             try {
                 mAdapter.notifyDataSetChanged();
                 lblTotal.setText(" (Total: "+ dataList.size() +")");
             }catch ( Exception ex){
                 Connection.MessageBox(Vaccination_list.this,ex.getMessage());
             }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Vaccination_list.this, e.getMessage());
            return;
        }
     }



     public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
         private List<Vaccination_DataModel> dataList;
         public class MyViewHolder extends RecyclerView.ViewHolder {
         LinearLayout secListRow;
         TextView VaccID;
         TextView HHID;
         TextView MoSlNo;
         TextView MemID;
         TextView VaccCode;
         TextView VaccName;
         TextView VaccCodeOth2;
         TextView Response;
         TextView ResponseOth;
         TextView VaccDate;
         TextView VaccDateDK;
         CardView card_view;
         public MyViewHolder(View convertView) {
             super(convertView);
             secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);
//             VaccID = (TextView)convertView.findViewById(R.id.VaccID);
//             HHID = (TextView)convertView.findViewById(R.id.HHID);
//             MoSlNo = (TextView)convertView.findViewById(R.id.MoSlNo);
//             MemID = (TextView)convertView.findViewById(R.id.MemID);
             VaccName = (TextView)convertView.findViewById(R.id.VaccName);
//             VaccName = (TextView)convertView.findViewById(R.id.VaccName);
//             VaccCodeOth2 = (TextView)convertView.findViewById(R.id.VaccCodeOth2);
//             Response = (TextView)convertView.findViewById(R.id.Response);
//             ResponseOth = (TextView)convertView.findViewById(R.id.ResponseOth);
//             VaccDate = (TextView)convertView.findViewById(R.id.VaccDate);
//             VaccDateDK = (TextView)convertView.findViewById(R.id.VaccDateDK);
             card_view = (CardView) convertView.findViewById(R.id.card_view);
             }
         }
         public DataAdapter(List<Vaccination_DataModel> datalist) {
             this.dataList = datalist;
         }
         @Override
         public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.vaccination_row, parent, false);
             return new MyViewHolder(itemView);
         }
         @Override
         public void onBindViewHolder(MyViewHolder holder, int position) {
             final Vaccination_DataModel data = dataList.get(position);
//             holder.VaccID.setText(data.getVaccID());
//             holder.HHID.setText(data.getHHID());
//             holder.MoSlNo.setText(data.getMoSlNo());
//             holder.MemID.setText(data.getMemID());
//             holder.VaccCode.setText(data.getVaccCode());
//             holder.VaccName.setText(data.getVaccName());
             holder.VaccName.setText(data.getVaccCode() +"- " +data.getVaccName());
//             holder.VaccCodeOth2.setText(data.getVaccCodeOth2());
//             holder.Response.setText(String.valueOf(data.getResponse()));
//             holder.ResponseOth.setText(data.getResponseOth());
//             holder.VaccDate.setText(data.getVaccDate());
//             holder.VaccDateDK.setText(String.valueOf(data.getVaccDateDK()));


             if(C.Existence("Select * from Vaccination where MemID='"+MEMID+"' and VaccCode='"+ data.getVaccCode() +"'"))
             {
                 holder.card_view.setBackgroundResource(R.drawable.style_completed_square_shape);
             }

             holder.secListRow.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     final ProgressDialog progDailog = ProgressDialog.show(Vaccination_list.this, "", "Please Wait . . .", true);
                     new Thread() {
                         public void run() {
                             try {
                                 Bundle IDbundle = new Bundle();
                                 IDbundle.putString("VaccID", data.getVaccID());
                                 IDbundle.putString("HHID", HHID);
                                 IDbundle.putString("MemID", MEMID);
                                 IDbundle.putString("VaccCode", data.getVaccCode());
                                 IDbundle.putString("VaccName", data.getVaccName());
                                 IDbundle.putString("visitdate", VISIT_DATE);
                                 IDbundle.putString("bdate", BIRTH_DATE); //yyyy-mm-dd
                                 Intent f1 = new Intent(getApplicationContext(), Vaccination.class);
                                 f1.putExtras(IDbundle);
                                 startActivityForResult(f1,1);
                             } catch (Exception e) {
                             }
                             progDailog.dismiss();
                         }
                     }.start();
                 }
             });
         }
         @Override
         public int getItemCount() {
             return dataList.size();
         }
     }


     protected Dialog onCreateDialog(int id) {
        final Calendar c = Calendar.getInstance();
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mDateSetListener,g.mYear,g.mMonth-1,g.mDay);
        }
        return null;
     }


     private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year; mMonth = monthOfYear+1; mDay = dayOfMonth;
            EditText dtpDate;
            dtpDate = (EditText)findViewById(R.id.dtpFDate);
            if (VariableID.equals("dtpFDate"))
            {
                dtpDate = (EditText)findViewById(R.id.dtpFDate);
            }
            else if (VariableID.equals("dtpTDate"))
            {
                dtpDate = (EditText)findViewById(R.id.dtpTDate);
            }
            dtpDate.setText(new StringBuilder()
                .append(Global.Right("00"+mDay,2)).append("/")
                .append(Global.Right("00"+mMonth,2)).append("/")
                .append(mYear));
        }
     };


}