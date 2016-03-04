package com.example.user.flightresults;

/**
 * Created by USER on 2/8/2016.
 */
public class Arrival {
    private String DEPaircode;
    private String airDEP;
    private String ARRaircode;
    private String airARR;

    public Arrival(String airARR, String airDEP, String ARRaircode, String DEPaircode) {
        this.airARR = airARR;
        this.airDEP = airDEP;
        this.ARRaircode = ARRaircode;
        this.DEPaircode = DEPaircode;
    }

    public String getAirARR() {
        return airARR;
    }

    public void setAirARR(String airARR) {
        this.airARR = airARR;
    }

    public String getAirDEP() {
        return airDEP;
    }

    public void setAirDEP(String airDEP) {
        this.airDEP = airDEP;
    }

    public String getARRaircode() {
        return ARRaircode;
    }

    public void setARRaircode(String ARRaircode) {
        this.ARRaircode = ARRaircode;
    }

    public String getDEPaircode() {
        return DEPaircode;
    }

    public void setDEPaircode(String DEPaircode) {
        this.DEPaircode = DEPaircode;
    }
}
