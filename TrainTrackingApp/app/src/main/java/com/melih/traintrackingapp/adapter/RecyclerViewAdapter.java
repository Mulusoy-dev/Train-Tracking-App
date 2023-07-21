package com.melih.traintrackingapp.adapter;


import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.melih.traintrackingapp.R;
import com.melih.traintrackingapp.model.TrainModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    private ArrayList<TrainModel> trainList;

    private String[] colors = {"#81d8d0","#ff0000","#800080","#f08080","#66cdaa","#008000","#00ff7f", "#a0db8e"};

    public RecyclerViewAdapter(ArrayList<TrainModel> trainList) {
        this.trainList = trainList;
    }





    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(trainList.get(position),colors,position);

    }

    @Override
    public int getItemCount() {
        return trainList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView text_id;
        TextView text_date;

        TextView text_emergency;

        TextView text_pan_upp;

        TextView text_pan_dwn;

        TextView text_total_km;

        TextView text_pf12_pan_narr;

        TextView text_pf40_ext_temp;

        TextView text_brake;


        public RowHolder(@NonNull View itemView) {
            super(itemView);


        }

        public void bind(TrainModel trainModel, String[] colors, Integer position) {
            //itemView.setBackgroundColor(Color.parseColor(colors[position % 8]));
            text_id = itemView.findViewById(R.id.text_id);
            text_date = itemView.findViewById(R.id.text_date);
            text_brake = itemView.findViewById(R.id.text_brake);
            text_emergency = itemView.findViewById(R.id.text_emergency);
            text_pan_upp = itemView.findViewById(R.id.text_pan_upp);
            text_total_km = itemView.findViewById(R.id.text_total_km);
            text_pf12_pan_narr = itemView.findViewById(R.id.text_pf12_pan_narr);
            text_pf40_ext_temp = itemView.findViewById(R.id.text_pf40_ext_temp);
            text_pan_dwn = itemView.findViewById(R.id.text_pan_dwn);

            text_id.setText(trainModel._id);
            text_date.setText(trainModel.modifyTime);
            text_brake.setText(trainModel.plcParams.PLC_MC_BRAKE);
            text_emergency.setText(trainModel.plcParams.PLC_MC_EMERGENCY);
            text_pan_upp.setText(trainModel.plcProcess.PF12_PAN_UP);
            text_total_km.setText(trainModel.recC1Misc.V1_TotalKM);
            text_pf12_pan_narr.setText(trainModel.plcProcess.PF12_PAN_NARR);
            text_pf40_ext_temp.setText(trainModel.plcProcess.PF40_EXT_TEMP);
            text_pan_dwn.setText(trainModel.plcProcess.PF12_PAN_DWN);

        }
    }
}
