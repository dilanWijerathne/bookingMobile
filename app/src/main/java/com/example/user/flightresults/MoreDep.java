package com.example.user.flightresults;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by USER on 2/8/2016.
 */
public class MoreDep implements Serializable {
   private String dep_date;
   private String flt_no;
   private String arr_date;
   private String time;
   private Boolean zone;
   private String stop;


    public MoreDep( String arr_date, String dep_date, String flt_no, String stop, String time, Boolean zone) {
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



    public String getDep_date() {
        return dep_date;
    }


    public String getFlt_no() {
        return flt_no;
    }


    public String getStop() {
        return stop;
    }


    public String getTime() {
        return time;
    }



    public Boolean getZone() {
        return zone;
    }



}
