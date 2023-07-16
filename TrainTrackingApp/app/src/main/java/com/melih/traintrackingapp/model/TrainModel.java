package com.melih.traintrackingapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TrainModel {

    @SerializedName("modifyTime")
    public String modifyTime;

    @SerializedName("_id")
    public String _id;


    @SerializedName("plc_params1_port")
    public PlcParams plcParams;


    public static class PlcParams {
        @SerializedName("PLC_MC_EMERGENCY")
        public String PLC_MC_EMERGENCY;

        @SerializedName("PLC_MC_BRAKE")
        public String PLC_MC_BRAKE;

        @SerializedName("PLC_MC_TRACTION")
        public String PLC_MC_TRACTION;


        // Diğer alanlar buraya eklenebilir

        // Getter ve setter metodları
    }

}



