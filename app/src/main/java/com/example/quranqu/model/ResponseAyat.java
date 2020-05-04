package com.example.quranqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maulana Ibrahim on 03/May/2020
 * Email maulibrahim19@gmail.com
 */
public class ResponseAyat {

    @Expose
    @SerializedName("tr")
    private String tr;

    @Expose
    @SerializedName("nomor")
    private String nomor;

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("ar")
    private String ar;

    public ResponseAyat(String tr, String nomor, String id, String ar) {
        this.tr = tr;
        this.nomor = nomor;
        this.id = id;
        this.ar = ar;
    }

    public String getTr() {
        return tr;
    }

    public void setTr(String tr) {
        this.tr = tr;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAr() {
        return ar;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }
}
