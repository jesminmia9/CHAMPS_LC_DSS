package org.icddrb.champs_lc_dss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.icddrb.champs_lc_dss.R;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter {

    private int hidingItemIndex = -1; // Default to no hiding
    public CustomSpinnerAdapter(@NonNull Context context, ArrayList<String> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_custom_view,parent, false);
        }
        String name = (String) getItem(position);
        TextView tv = convertView.findViewById(R.id.weekofday);
        if (name != null) {
            tv.setText(name);
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_custom_dropdown_item,parent, false);
        }
        String name = (String) getItem(position);
        TextView tv = convertView.findViewById(R.id.tv_dropdown);
        if (name != null) {
            tv.setText(name);
        }

        return convertView;
    }

    public void setHidingItemIndex(int hidingItemIndex) {
        this.hidingItemIndex = hidingItemIndex;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    private View hideItem(int position) {
        if (position == hidingItemIndex) {
            TextView tv = new TextView(getContext());
            tv.setVisibility(View.GONE);
            return tv;
        }
        return null;
    }
}
