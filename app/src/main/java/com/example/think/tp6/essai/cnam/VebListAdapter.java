package com.example.think.tp6.essai.cnam;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.think.tp6.R;
import com.example.think.tp6.velib.model.StationVelib;

import java.util.ArrayList;


public class VebListAdapter extends ArrayAdapter<StationVelib> {
    Context mContext;
    public VebListAdapter( Context context,ArrayList<StationVelib> objects) {
        super(context, 0, objects);
        this.mContext=context;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        StationVelib velib = getItem(position);
        View view =convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.vebl_list_item, parent, false);
            ViewHolder viewHolder= new ViewHolder(view);
            view.setTag(viewHolder);

        }
        ViewHolder viewHolder= (ViewHolder) view.getTag();

        viewHolder.stationName.setText(velib.getName());
        Log.d("fulladd",velib.getFullAddress());
        viewHolder.stationDesc.setText(velib.getFullAddress());
        if(velib.getOpen()){
            viewHolder.statusImg.setImageResource(R.drawable.accepted);
        }
        else
            viewHolder.statusImg.setImageResource(R.drawable.cancel);
        return view;
    }

    public static class ViewHolder  {
        ImageView statusImg;
        TextView stationName, stationDesc;

        public ViewHolder(View itemView) {

            stationName = (TextView) itemView.findViewById(R.id.stationName);
            stationDesc = (TextView) itemView.findViewById(R.id.stationDesc);
            statusImg = (ImageView) itemView.findViewById(R.id.statusImg);

        }


    }

}
