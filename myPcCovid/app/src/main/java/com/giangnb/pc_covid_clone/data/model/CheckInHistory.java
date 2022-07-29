package com.giangnb.pc_covid_clone.data.model;

public class CheckInHistory {
    private String date;
    private String time;
    private String place;
    private String address;

    public CheckInHistory(){

    }

    public CheckInHistory(String date, String time, String place, String address) {
        this.date = date;
        this.time = time;
        this.place = place;
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
