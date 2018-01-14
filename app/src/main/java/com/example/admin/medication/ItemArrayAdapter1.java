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
 * Created by Admin on 11/01/2018.
 */

class ItemArrayAdapter1 extends ArrayAdapter<String[]> {
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
        TextView Ambulance;
        TextView BloodBank;
        TextView Emergency;
    }

    public ItemArrayAdapter1(Context context, int resource) {
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
            row = inflater.inflate(R.layout.record1, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.Hname = row.findViewById(R.id.Hname);
            viewHolder.Add = row.findViewById(R.id.addr);
            viewHolder.Emergency = row.findViewById(R.id.Emergency);
            viewHolder.Telephone = row.findViewById(R.id.telephone);
            viewHolder.Ambulance = row.findViewById(R.id.Ambulance);
            viewHolder.BloodBank = row.findViewById(R.id.BloodBank);
            //viewHolder.State = row.findViewById(R.id.state);
            //viewHolder.District = row.findViewById(R.id.district);
            viewHolder.Mobile = row.findViewById(R.id.mobile);
            // viewHolder.pin = row.findViewById(R.id.pincode);

            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder) row.getTag();
        }

        String[] stat = getItem(position);
        viewHolder.Hname.setText(stat[2]);
        //viewHolder.Cat.setText(stat[4]);
        //viewHolder.CareType.setText(stat[3]);
        viewHolder.Telephone.setText(stat[10]);
        //viewHolder.pin.setText(stat[10]);
        viewHolder.Add.setText(stat[6]);
        viewHolder.Mobile.setText(stat[11]);
        //viewHolder.State.setText(stat[8]);
        //viewHolder.District.setText(stat[9]);
        viewHolder.Ambulance.setText(stat[13]);
        viewHolder.BloodBank.setText(stat[14]);
        viewHolder.Emergency.setText(stat[12]);
        return row;

    }
}