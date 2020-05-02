package com.example.quranqu.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maulana Ibrahim on 02/May/2020
 * Email maulibrahim19@gmail.com
 */
public class ResponseSurah {

    @SerializedName("urut")
    private String urut;
    @SerializedName("type")
    private String type;
    @SerializedName("rukuk")
    private String rukuk;
    @SerializedName("nomor")
    private String nomor;
    @SerializedName("nama")
    private String nama;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("ayat")
    private int ayat;
    @SerializedName("audio")
    private String audio;
    @SerializedName("asma")
    private String asma;
    @SerializedName("arti")
    private String arti;


    public ResponseSurah() {
    }

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
