package com.example.user.flightresults;

import java.io.Serializable;

/**
 * Created by USER on 2/8/2016.
 */
public class MoreArr implements Serializable{
    private String dep_date;
    private String flt_no;
    private String arr_date;
    private String time;
    private Boolean zone;
    private String stop;




    public MoreArr( String arr_date, String dep_date, String flt_no, String stop, String time, Boolean zone) {
        this.arr_date = arr_date;
        this.dep_date = dep_date;
        this.flt_no = flt_no;
        this.stop = stop;
        this.time = time;
        this.zone = zone;

    }

    public String getArr_date() {
        return arr_date;
    }

    public void setArr_date(String arr_date) {
        this.arr_date = arr_date;
    }

    public String getDep_date() {
        return dep_date;
    }

    public void setDep_date(String dep_date) {
        this.dep_date = dep_date;
    }

    public String getFlt_no() {
        return flt_no;
    }

    public void setFlt_no(String flt_no) {
        this.flt_no = flt_no;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getZone() {
        return zone;
    }

    public void setZone(Boolean zone) {
        this.zone = zone;
    }
}
