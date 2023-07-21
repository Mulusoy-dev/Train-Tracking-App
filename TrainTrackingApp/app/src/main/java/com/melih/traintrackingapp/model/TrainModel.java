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


    @SerializedName("rec_c1_misc_port")
    public RecC1Misc recC1Misc;

    @SerializedName("plc_process_port")
    public PlcProcess plcProcess;


    public static class PlcParams {
        @SerializedName("PLC_MC_EMERGENCY")
        public String PLC_MC_EMERGENCY;

        @SerializedName("PLC_MC_BRAKE")
        public String PLC_MC_BRAKE;




        // Diğer alanlar buraya eklenebilir
        // Getter ve setter metodları
    }

    public static class RecC1Misc{
        @SerializedName("V1_TotalKM")
        public String V1_TotalKM;
    }

    public static class PlcProcess{
        @SerializedName("PF12_PAN_NARR")
        public String PF12_PAN_NARR;

        @SerializedName("PF40_EXT_TEMP")
        public String PF40_EXT_TEMP;

        @SerializedName("PF12_PAN_UP")
        public String PF12_PAN_UP;

        @SerializedName("PF12_PAN_DWN")
        public String PF12_PAN_DWN;
    }

}



