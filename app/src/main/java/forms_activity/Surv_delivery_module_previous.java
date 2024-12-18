package forms_activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.icddrb.champs_lc_dss.R;

import Common.Connection;

public class Surv_delivery_module_previous extends AppCompatActivity implements View.OnClickListener {


    Connection C;
    Bundle IDbundle;
     String DELIVERYID = "";
     String MEM_ID = "";
     String HHID = "";
     String EV_TYPE = "";
     String ROUND = "";
     String DOD = "";
     String LB = "";
     String SB = "";
     String MISC = "";
     String AB = "";
     String MEMNAME = "";
     String TOTAL = "";

    ColorStateList def;
    TextView memname, memdod, memtotaloutcome;
    TextView item1, item2, item3, item4, select;
    LinearLayout tab1, tab2, tab3, tab4;
    Button lbc1,lbc2,lbc3,lbc4,lbc5,lbc6,lbc7,mc1,mc2,mc3,mc4,mc5,mc6,mc7,sbc1,sbc2,sbc3,sbc4,sbc5,sbc6,sbc7,abc1,abc2,abc3,abc4,abc5,abc6,abc7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surv_delivery_module);

        C = new Connection(this);
        IDbundle = getIntent().getExtras();
        DELIVERYID = IDbundle.getString("DeliveryID");
        MEM_ID = IDbundle.getString("MemID");
        HHID = IDbundle.getString("HHID");
        EV_TYPE = IDbundle.getString("evtype");
        ROUND = IDbundle.getString("round");
        DOD = IDbundle.getString("dod");
        LB = IDbundle.getString("lb");
        SB = IDbundle.getString("sb");
        MISC = IDbundle.getString("misc");
        AB = IDbundle.getString("ab");
        TOTAL = IDbundle.getString("total");


        ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
        cmdBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(Surv_delivery_module_previous.this);
                adb.setTitle("Close");
                adb.setIcon(R.drawable.favicon);
                adb.setMessage("Do you want to close this form[Yes/No]?");
                adb.setNegativeButton("No", null);
                adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }});
                adb.show();
            }});


        MEMNAME = C.ReturnSingleValue("Select Name from tmpMember where MemID = '"+MEM_ID+"'");

        memname = findViewById(R.id.memname);
        memdod = findViewById(R.id.memdod);
        memtotaloutcome = findViewById(R.id.memtotaloutcome);

        memname.setText(MEMNAME);
        memdod.setText("Delivery Date: "+ DOD);
        memtotaloutcome.setText("Total Outcome: "+ TOTAL);

//        Toolbar toolbar = findViewById(R.id.toolbarcustom);
//        setSupportActionBar(toolbar);
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        select = findViewById(R.id.select);
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
        sbc1 = findViewById(R.id.sbc1);
        sbc2 = findViewById(R.id.sbc2);
        sbc3 = findViewById(R.id.sbc3);
        sbc4 = findViewById(R.id.sbc4);
        sbc5 = findViewById(R.id.sbc5);
        sbc6 = findViewById(R.id.sbc6);
        sbc7 = findViewById(R.id.sbc7);
        abc1 = findViewById(R.id.abc1);
//        abc2 = findViewById(R.id.abc2);
//        abc3 = findViewById(R.id.abc3);
//        abc4 = findViewById(R.id.abc4);
//        abc5 = findViewById(R.id.abc5);
//        abc6 = findViewById(R.id.abc6);
//        abc7 = findViewById(R.id.abc7);

        ShowLiveBirthChildButton(LB);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);

        def = item2.getTextColors();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.item1){
            select.animate().x(0).setDuration(100);
            item1.setTextColor(Color.WHITE);
            item2.setTextColor(def);
            item3.setTextColor(def);
            item4.setTextColor(def);

            tab1.setVisibility(View.VISIBLE);
            ShowLiveBirthChildButton(LB);

            tab2.setVisibility(View.GONE);
            tab3.setVisibility(View.GONE);
            tab4.setVisibility(View.GONE);
        }
        else if(view.getId() == R.id.item2){
            select.animate().x(0).setDuration(100);
            item1.setTextColor(def);
            item3.setTextColor(def);
            item4.setTextColor(def);
            item2.setTextColor(Color.WHITE);
            int size = item2.getWidth();
            select.animate().x(size).setDuration(100);

            tab1.setVisibility(View.GONE);
            tab2.setVisibility(View.VISIBLE);
            ShowMiscarriageChildButton(MISC);
            tab3.setVisibility(View.GONE);
            tab4.setVisibility(View.GONE);
        }
        else if(view.getId() == R.id.item3){
            select.animate().x(0).setDuration(100);
            item3.setTextColor(Color.WHITE);
            item1.setTextColor(def);
            item2.setTextColor(def);
            item4.setTextColor(def);
            item3.setTextColor(Color.WHITE);
            int size = item3.getWidth() * 2;
            select.animate().x(size).setDuration(100);

            tab1.setVisibility(View.GONE);
            tab2.setVisibility(View.GONE);
            tab3.setVisibility(View.VISIBLE);
            ShowStillBirthChildButton(SB);
            tab4.setVisibility(View.GONE);
        }
        else if(view.getId() == R.id.item4){
            select.animate().x(0).setDuration(100);
            item2.setTextColor(def);
            item1.setTextColor(def);
            item3.setTextColor(def);
            item4.setTextColor(Color.WHITE);
            int size = item4.getWidth() * 3;
            select.animate().x(size).setDuration(100);

            tab1.setVisibility(View.GONE);
            tab2.setVisibility(View.GONE);
            tab3.setVisibility(View.GONE);
            tab4.setVisibility(View.VISIBLE);
        }
    }

    public void ShowLiveBirthChildButton(String lb){
        if(lb.equals("1")){
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.GONE);
            lbc3.setVisibility(View.GONE);
            lbc4.setVisibility(View.GONE);
            lbc5.setVisibility(View.GONE);
            lbc6.setVisibility(View.GONE);
            lbc7.setVisibility(View.GONE);
        }
        else  if(lb.equals("2")){
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.VISIBLE);
            lbc3.setVisibility(View.GONE);
            lbc4.setVisibility(View.GONE);
            lbc5.setVisibility(View.GONE);
            lbc6.setVisibility(View.GONE);
            lbc7.setVisibility(View.GONE);
        }
        else  if(lb.equals("3")){
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.VISIBLE);
            lbc3.setVisibility(View.VISIBLE);
            lbc4.setVisibility(View.GONE);
            lbc5.setVisibility(View.GONE);
            lbc6.setVisibility(View.GONE);
            lbc7.setVisibility(View.GONE);
        }
        else  if(lb.equals("4")){
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.VISIBLE);
            lbc3.setVisibility(View.VISIBLE);
            lbc4.setVisibility(View.VISIBLE);
            lbc5.setVisibility(View.GONE);
            lbc6.setVisibility(View.GONE);
            lbc7.setVisibility(View.GONE);
        }
        else  if(lb.equals("5")){
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.VISIBLE);
            lbc3.setVisibility(View.VISIBLE);
            lbc4.setVisibility(View.VISIBLE);
            lbc5.setVisibility(View.VISIBLE);
            lbc6.setVisibility(View.GONE);
            lbc7.setVisibility(View.GONE);
        }
        else  if(lb.equals("6")){
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.VISIBLE);
            lbc3.setVisibility(View.VISIBLE);
            lbc4.setVisibility(View.VISIBLE);
            lbc5.setVisibility(View.VISIBLE);
            lbc6.setVisibility(View.VISIBLE);
            lbc7.setVisibility(View.GONE);
        }
        else  if(lb.equals("7")){
            lbc1.setVisibility(View.VISIBLE);
            lbc2.setVisibility(View.VISIBLE);
            lbc3.setVisibility(View.VISIBLE);
            lbc4.setVisibility(View.VISIBLE);
            lbc5.setVisibility(View.VISIBLE);
            lbc6.setVisibility(View.VISIBLE);
            lbc7.setVisibility(View.VISIBLE);
        }
        else{
            lbc1.setVisibility(View.GONE);
            lbc2.setVisibility(View.GONE);
            lbc3.setVisibility(View.GONE);
            lbc4.setVisibility(View.GONE);
            lbc5.setVisibility(View.GONE);
            lbc6.setVisibility(View.GONE);
            lbc7.setVisibility(View.GONE);
        }
    }

    public void ShowMiscarriageChildButton(String m){
        if(m.equals("1")){
            mc1.setVisibility(View.VISIBLE);
            mc2.setVisibility(View.GONE);
            mc3.setVisibility(View.GONE);
            mc4.setVisibility(View.GONE);
            mc5.setVisibility(View.GONE);
            mc6.setVisibility(View.GONE);
            mc7.setVisibility(View.GONE);
        }
        else  if(m.equals("2")){
            mc1.setVisibility(View.VISIBLE);
            mc2.setVisibility(View.VISIBLE);
            mc3.setVisibility(View.GONE);
            mc4.setVisibility(View.GONE);
            mc5.setVisibility(View.GONE);
            mc6.setVisibility(View.GONE);
            mc7.setVisibility(View.GONE);
        }
        else  if(m.equals("3")){
            mc1.setVisibility(View.VISIBLE);
            mc2.setVisibility(View.VISIBLE);
            mc3.setVisibility(View.VISIBLE);
            mc4.setVisibility(View.GONE);
            mc5.setVisibility(View.GONE);
            mc6.setVisibility(View.GONE);
            mc7.setVisibility(View.GONE);
        }
        else  if(m.equals("4")){
            mc1.setVisibility(View.VISIBLE);
            mc2.setVisibility(View.VISIBLE);
            mc3.setVisibility(View.VISIBLE);
            mc4.setVisibility(View.VISIBLE);
            mc5.setVisibility(View.GONE);
            mc6.setVisibility(View.GONE);
            mc7.setVisibility(View.GONE);
        }
        else  if(m.equals("5")){
            mc1.setVisibility(View.VISIBLE);
            mc2.setVisibility(View.VISIBLE);
            mc3.setVisibility(View.VISIBLE);
            mc4.setVisibility(View.VISIBLE);
            mc5.setVisibility(View.VISIBLE);
            mc6.setVisibility(View.GONE);
            mc7.setVisibility(View.GONE);
        }
        else  if(m.equals("6")){
            mc1.setVisibility(View.VISIBLE);
            mc2.setVisibility(View.VISIBLE);
            mc3.setVisibility(View.VISIBLE);
            mc4.setVisibility(View.VISIBLE);
            mc5.setVisibility(View.VISIBLE);
            mc6.setVisibility(View.VISIBLE);
            mc7.setVisibility(View.GONE);
        }
        else  if(m.equals("7")){
            mc1.setVisibility(View.VISIBLE);
            mc2.setVisibility(View.VISIBLE);
            mc3.setVisibility(View.VISIBLE);
            mc4.setVisibility(View.VISIBLE);
            mc5.setVisibility(View.VISIBLE);
            mc6.setVisibility(View.VISIBLE);
            mc7.setVisibility(View.VISIBLE);
        }
        else{
            mc1.setVisibility(View.GONE);
            mc2.setVisibility(View.GONE);
            mc3.setVisibility(View.GONE);
            mc4.setVisibility(View.GONE);
            mc5.setVisibility(View.GONE);
            mc6.setVisibility(View.GONE);
            mc7.setVisibility(View.GONE);
        }
    }

    public void ShowStillBirthChildButton(String sb){
        if(sb.equals("1")){
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.GONE);
            sbc3.setVisibility(View.GONE);
            sbc4.setVisibility(View.GONE);
            sbc5.setVisibility(View.GONE);
            sbc6.setVisibility(View.GONE);
            sbc7.setVisibility(View.GONE);
        }
        else  if(sb.equals("2")){
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.VISIBLE);
            sbc3.setVisibility(View.GONE);
            sbc4.setVisibility(View.GONE);
            sbc5.setVisibility(View.GONE);
            sbc6.setVisibility(View.GONE);
            sbc7.setVisibility(View.GONE);
        }
        else  if(sb.equals("3")){
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.VISIBLE);
            sbc3.setVisibility(View.VISIBLE);
            sbc4.setVisibility(View.GONE);
            sbc5.setVisibility(View.GONE);
            sbc6.setVisibility(View.GONE);
            sbc7.setVisibility(View.GONE);
        }
        else  if(sb.equals("4")){
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.VISIBLE);
            sbc3.setVisibility(View.VISIBLE);
            sbc4.setVisibility(View.VISIBLE);
            sbc5.setVisibility(View.GONE);
            sbc6.setVisibility(View.GONE);
            sbc7.setVisibility(View.GONE);
        }
        else  if(sb.equals("5")){
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.VISIBLE);
            sbc3.setVisibility(View.VISIBLE);
            sbc4.setVisibility(View.VISIBLE);
            sbc5.setVisibility(View.VISIBLE);
            sbc6.setVisibility(View.GONE);
            sbc7.setVisibility(View.GONE);
        }
        else  if(sb.equals("6")){
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.VISIBLE);
            sbc3.setVisibility(View.VISIBLE);
            sbc4.setVisibility(View.VISIBLE);
            sbc5.setVisibility(View.VISIBLE);
            sbc6.setVisibility(View.VISIBLE);
            sbc7.setVisibility(View.GONE);
        }
        else  if(sb.equals("7")){
            sbc1.setVisibility(View.VISIBLE);
            sbc2.setVisibility(View.VISIBLE);
            sbc3.setVisibility(View.VISIBLE);
            sbc4.setVisibility(View.VISIBLE);
            sbc5.setVisibility(View.VISIBLE);
            sbc6.setVisibility(View.VISIBLE);
            sbc7.setVisibility(View.VISIBLE);
        }
        else{
            sbc1.setVisibility(View.GONE);
            sbc2.setVisibility(View.GONE);
            sbc3.setVisibility(View.GONE);
            sbc4.setVisibility(View.GONE);
            sbc5.setVisibility(View.GONE);
            sbc6.setVisibility(View.GONE);
            sbc7.setVisibility(View.GONE);
        }
    }



}