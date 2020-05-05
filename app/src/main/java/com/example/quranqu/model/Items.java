package com.example.quranqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {
    @Expose
    @SerializedName("isha")
    private String isha;
    @Expose
    @SerializedName("maghrib")
    private String maghrib;
    @Expose
    @SerializedName("asr")
    private String asr;
    @Expose
    @SerializedName("dhuhr")
    private String dhuhr;
    @Expose
    @SerializedName("shurooq")
    private String shurooq;
    @Expose
    @SerializedName("fajr")
    private String fajr;
    @Expose
    @SerializedName("date_for")
    private String date_for;


    public String getIsha() {
        return isha;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public String getAsr() {
        return asr;
    }

    public void setAsr(String asr) {
        this.asr = asr;
    }

    public String getDhuhr() {
        return dhuhr;
    }

    public void setDhuhr(String dhuhr) {
        this.dhuhr = dhuhr;
    }

    public String getShurooq() {
        return shurooq;
    }

    public void setShurooq(String shurooq) {
        this.shurooq = shurooq;
    }

    public String getFajr() {
        return fajr;
    }

    public void setFajr(String fajr) {
        this.fajr = fajr;
    }

    public String getDate_for() {
        return date_for;
    }

    public void setDate_for(String date_for) {
        this.date_for = date_for;
    }
}
