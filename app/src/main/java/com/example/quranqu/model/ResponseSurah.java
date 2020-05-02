package com.example.quranqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maulana Ibrahim on 02/May/2020
 * Email maulibrahim19@gmail.com
 */
public abstract class ResponseSurah {

    @Expose
    @SerializedName("urut")
    private String urut;
    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    @SerializedName("rukuk")
    private String rukuk;
    @Expose
    @SerializedName("nomor")
    private String nomor;
    @Expose
    @SerializedName("nama")
    private String nama;
    @Expose
    @SerializedName("keterangan")
    private String keterangan;
    @Expose
    @SerializedName("ayat")
    private int ayat;
    @Expose
    @SerializedName("audio")
    private String audio;
    @Expose
    @SerializedName("asma")
    private String asma;
    @Expose
    @SerializedName("arti")
    private String arti;

    public String getUrut() {
        return urut;
    }

    public void setUrut(String urut) {
        this.urut = urut;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRukuk() {
        return rukuk;
    }

    public void setRukuk(String rukuk) {
        this.rukuk = rukuk;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getAyat() {
        return ayat;
    }

    public void setAyat(int ayat) {
        this.ayat = ayat;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getAsma() {
        return asma;
    }

    public void setAsma(String asma) {
        this.asma = asma;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }
}
