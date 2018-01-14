package com.example.admin.medication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/01/2018.
 */

class ItemArrayAdapter extends ArrayAdapter<String[]> {
    private List<String[]> scoreList = new ArrayList<String[]>();

    static class ItemViewHolder {
        TextView Hname ;
        TextView Add ;
        TextView Cat ;
        TextView Telephone  ;
        TextView CareType;
        TextView State;
        TextView Email ;
        TextView Mobile ;
        TextView District ;
        TextView pin ;
    }

    public ItemArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(String[] object) {
        scoreList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.scoreList.size();
    }

    @Override
    public String[] getItem(int position) {
        return this.scoreList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if(row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.record, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.Hname = row.findViewById(R.id.Hname);
            viewHolder.Add = row.findViewById(R.id.addr);
            viewHolder.Cat = row.findViewById(R.id.category);
            viewHolder.Telephone = row.findViewById(R.id.telephone);
            viewHolder.CareType = row.findViewById(R.id.caretype);
           // viewHolder.Email = row.findViewById(R.id.email);
            //viewHolder.State = row.findViewById(R.id.state);
            //viewHolder.District = row.findViewById(R.id.district);
            viewHolder.Mobile = row.findViewById(R.id.mobile);
           // viewHolder.pin = row.findViewById(R.id.pincode);

            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder) row.getTag();
        }

        String[] stat = getItem(position);
        viewHolder.Hname.setText(stat[1]);
        viewHolder.Cat.setText(stat[2]);
        viewHolder.CareType.setText(stat[3]);
        viewHolder.Telephone.setText(stat[9]);
        //viewHolder.pin.setText(stat[8]);
        viewHolder.Add.setText(stat[4]);
        viewHolder.Mobile.setText(stat[11]);
        //viewHolder.State.setText(stat[5]);
        //viewHolder.District.setText(stat[6]);
        return row;

    }
}