package org.icddrb.champs_lc_dss.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.icddrb.champs_lc_dss.R;

import java.util.List;

import Common.Global;
import Interfaces.DeliveryClick;
import forms_datamodel.tmpDelivery_DataModel;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder> {

    private List<String> deliveryDataModels;
    private Context context;
    private DeliveryClick deliveryClick;
    String NBID;
    String PGN;
    String HHID;
    String MemID;
    String Title;
    Dialog dialog;

    public DeliveryAdapter(Context context,Dialog dialog,
                           List<String> deliveryDataModels,
                           String NBID, String PGN, String HHID, String MemID, String Title,
                           DeliveryClick deliveryClick
    ) {
        this.context = context;
        this.dialog = dialog;
        this.deliveryDataModels = deliveryDataModels;
        this.NBID = NBID;
        this.PGN = PGN;
        this.HHID = HHID;
        this.MemID = MemID;
        this.Title = Title;
        this.deliveryClick = deliveryClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delivery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String data = deliveryDataModels.get(position);

        // Bind data to the ViewHolder here
        holder.eventType.setText("Pregnancy# "+data);

        holder.secListRow.setOnClickListener(view -> {
            deliveryClick.onClickDelivery(dialog, data, NBID, PGN, HHID, MemID, Title);
        });
    }

    @Override
    public int getItemCount() {
        return deliveryDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout secListRow;
        TextView eventType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            secListRow = itemView.findViewById(R.id.secListRow);
            eventType = itemView.findViewById(R.id.eventType);
        }
    }
}

