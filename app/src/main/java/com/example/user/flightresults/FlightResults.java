package com.example.user.flightresults;

import java.util.ArrayList;

/**
 * Created by USER on 2/1/2016.
 */
public class FlightResults {
    private String arl_code;
    private String airline;
    private String fare_before;
    private String fare_total;
    private Integer more;
    private ArrayList<MoreDep> departure;
    private ArrayList<MoreArr> arrival;
    private Integer i;


    public FlightResults(Integer i,String airline, String arl_code, String fare_before, String fare_total,Integer more,ArrayList<MoreDep> departure,ArrayList<MoreArr> arrival) {
        this.i=i;
        this.airline = airline;
        this.arl_code = arl_code;
        this.fare_before = fare_before;
        this.fare_total = fare_total;
        this.departure=departure;
        this.arrival=arrival;
        this.more=more;
    }

    public Integer getI() {
        return i;
    }

    public ArrayList<MoreArr> getArrival() {
        return arrival;
    }


    public ArrayList<MoreDep> getDeparture() {
        return departure;
    }

    public Integer getMore() {
        return more;
    }

    public String getAirline() {
        return airline;
    }

    public String getArl_code() {
        return arl_code;
    }


    public String getFare_before() {
        return fare_before;
    }


    public String getFare_total() {
        return fare_total;
    }


}
