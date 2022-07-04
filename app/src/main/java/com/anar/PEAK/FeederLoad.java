package com.anar.PEAK;

import java.io.Serializable;
import java.util.Date;

public class FeederLoad implements Serializable{

    public FeederLoad(){

    }
    public FeederLoad( int r, int s, int t, int n){
        this.nutralN =n;
        this.phaseS=s;
        this.phaseR=r;
        this.phaseT=t;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public int getPhaseR() {
        return phaseR;
    }

    public void setPhaseR(int phaseR) {
        this.phaseR = phaseR;
    }
    public void setPhaseR(String phaseR) {
        this.phaseR = Integer.valueOf(phaseR);
    }

    public int getPhaseS() {
        return phaseS;
    }

    public void setPhaseS(int phaseS) {
        this.phaseS = phaseS;
    }
    public void setPhaseS(String phaseS) {
        this.phaseS = Integer.valueOf(phaseS);
    }

    public int getPhaseT() {
        return phaseT;
    }

    public void setPhaseT(int phaseT) {
        this.phaseT = phaseT;
    }
    public void setPhaseT(String phaseT)
    {
        this.phaseT = Integer.valueOf(phaseT);
    }

    public int getNutralN() {
        return nutralN;
    }

    public void setNutralN(int nutralN) {
        this.nutralN = nutralN;
    }
    public void setNutralN(String nutralN) {
        this.nutralN = Integer.valueOf(nutralN);
    }

    private int id;
    private Date recordDate;
    private int phaseR;
    private int phaseS;
    private int phaseT;
    private int nutralN;

    @Override
    public String toString() {
        return "FeederLoad{" +
                "phaseR=" + phaseR +
                ", phaseS=" + phaseS +
                ", phaseT=" + phaseT +
                ", nutralN=" + nutralN +
                '}';
    }
}
