package com.example.quranqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maulana Ibrahim on 04/May/2020
 * Email maulibrahim19@gmail.com
 */
public class ResponseJadwalSholat {

    @Expose
    @SerializedName("status_description")
    private String status_description;
    @Expose
    @SerializedName("status_code")
    private int status_code;
    @Expose
    @SerializedName("status_valid")
    private int status_valid;
    @Expose
    @SerializedName("items")
    private List<Items> items;
    @Expose
    @SerializedName("country_code")
    private String country_code;
    @Expose
    @SerializedName("country")
    private String country;
    @Expose
    @SerializedName("postal_code")
    private String postal_code;
    @Expose
    @SerializedName("state")
    private String state;
    @Expose
    @SerializedName("city")
    private String city;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("longitude")
    private String longitude;
    @Expose
    @SerializedName("latitude")
    private String latitude;
    @Expose
    @SerializedName("qibla_direction")
    private String qibla_direction;
    @Expose
    @SerializedName("link")
    private String link;
    @Expose
    @SerializedName("today_weather")
    private Today_weather today_weather;
    @Expose
    @SerializedName("sealevel")
    private String sealevel;
    @Expose
    @SerializedName("map_image")
    private String map_image;
    @Expose
    @SerializedName("timezone")
    private String timezone;
    @Expose
    @SerializedName("daylight")
    private String daylight;
    @Expose
    @SerializedName("prayer_method_name")
    private String prayer_method_name;
    @Expose
    @SerializedName("method")
    private int method;
    @Expose
    @SerializedName("for")
    private String THIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE;
    @Expose
    @SerializedName("query")
    private String query;
    @Expose
    @SerializedName("title")
    private String title;

    public String getStatus_description() {
        return status_description;
    }

    public void setStatus_description(String status_description) {
        this.status_description = status_description;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public int getStatus_valid() {
        return status_valid;
    }

    public void setStatus_valid(int status_valid) {
        this.status_valid = status_valid;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getQibla_direction() {
        return qibla_direction;
    }

    public void setQibla_direction(String qibla_direction) {
        this.qibla_direction = qibla_direction;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Today_weather getToday_weather() {
        return today_weather;
    }

    public void setToday_weather(Today_weather today_weather) {
        this.today_weather = today_weather;
    }

    public String getSealevel() {
        return sealevel;
    }

    public void setSealevel(String sealevel) {
        this.sealevel = sealevel;
    }

    public String getMap_image() {
        return map_image;
    }

    public void setMap_image(String map_image) {
        this.map_image = map_image;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getDaylight() {
        return daylight;
    }

    public void setDaylight(String daylight) {
        this.daylight = daylight;
    }

    public String getPrayer_method_name() {
        return prayer_method_name;
    }

    public void setPrayer_method_name(String prayer_method_name) {
        this.prayer_method_name = prayer_method_name;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getTHIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE() {
        return THIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE;
    }

    public void setTHIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE(String THIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE) {
        this.THIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE = THIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
