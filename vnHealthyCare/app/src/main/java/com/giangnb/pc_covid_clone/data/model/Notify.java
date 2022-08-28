package com.giangnb.pc_covid_clone.data.model;

public class Notify {
    private String dateNotify;
    private String content;

    public Notify(){}

    public Notify(String dateNotify, String content) {
        this.dateNotify = dateNotify;
        this.content = content;
    }

    public String getDateNotify() {
        return dateNotify;
    }

    public void setDateNotify(String dateNotify) {
        this.dateNotify = dateNotify;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
