package com.anar.PEAK;

import java.io.Serializable;
import java.util.Date;

public class PostLoad implements Serializable {


    //fields
    private int id;
    private Date recordDate;
    private int phaseR;
    private int phaseS;
    private int phaseT;
    private int voltRS;
    private int voltST;
    private int voltTR;
    private int voltRN;
    private int voltSN;
    private int voltTN;
    private int nutralN;
    private int postCapacity;
    private String postCode;
    private LocationPoint postLocation;


    //costructors
    public PostLoad() {
    }

    public PostLoad(int r, int s, int t, int n) {
        this.nutralN = n;
        this.phaseS = s;
        this.phaseR = r;
        this.phaseT = t;
    }

    public PostLoad(int id, int r, int s, int t, int n) {
        this.id = id;
        this.nutralN = n;
        this.phaseS = s;
        this.phaseR = r;
        this.phaseT = t;
    }

    public PostLoad(int id, int r, int s, int t, int n, int postCapacity, String postCode) {
        this.id = id;
        this.nutralN = n;
        this.phaseS = s;
        this.phaseR = r;
        this.phaseT = t;
        this.postCapacity = postCapacity;
        this.postCode = postCode;
    }

    public PostLoad(int id, int r, int s, int t, int n, int postCapacity, String postCode, LocationPoint postLocation) {
        this.id = id;
        this.nutralN = n;
        this.phaseS = s;
        this.phaseR = r;
        this.phaseT = t;
        this.postCapacity = postCapacity;
        this.postCode = postCode;
        this.postLocation = postLocation;
    }

    //properties
    public int getNutralN() {
        return nutralN;
    }

    public void setNutralN(int nutralN) {
        this.nutralN = nutralN;
    }

    public void setPhaseN(String nutralN) {
        this.nutralN = Integer.valueOf(nutralN);
    }

    public int getPhaseT() {
        return phaseT;
    }

    public void setPhaseT(int phaseT) {
        this.phaseT = phaseT;
    }

    public void setPhaseT(String phaseT) {
        this.phaseT = Integer.valueOf(phaseT);
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

    public int getPhaseR() {
        return phaseR;
    }

    public void setPhaseR(int phaseR) {
        this.phaseR = phaseR;
    }

    public void setPhaseR(String phaseR) {
        this.phaseR = Integer.valueOf(phaseR);
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostCapacity() {
        return postCapacity;
    }

    public void setPostCapacity(int postCapacity) {
        this.postCapacity = postCapacity;
    }

    public void setPostCapacity(String postCapacity) {
        this.postCapacity = Integer.valueOf(postCapacity);
    }


    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public LocationPoint getPostLocation() {
        return postLocation;
    }

    public void setPostLocation(LocationPoint postLocation) {
        this.postLocation = postLocation;
    }

    public int getVoltRN() {
        return voltRN;
    }

    public void setVoltRN(int voltRN) {
        this.voltRN = voltRN;
    }

    public int getVoltSN() {
        return voltSN;
    }

    public void setVoltSN(int voltSN) {
        this.voltSN = voltSN;
    }

    public int getVoltTN() {
        return voltTN;
    }

    public void setVoltTN(int voltTN) {
        this.voltTN = voltTN;
    }

    public int getVoltRS() {
        return voltRS;
    }

    public void setVoltRS(int voltRS) {
        this.voltRS = voltRS;
    }

    public int getVoltST() {
        return voltST;
    }

    public void setVoltST(int voltST) {
        this.voltST = voltST;
    }

    public int getVoltTR() {
        return voltTR;
    }

    public void setVoltTR(int voltTR) {
        this.voltTR = voltTR;
    }

    @Override
    public String toString() {
        return "PostLoad{" +
                "phaseR=" + phaseR +
                ", phaseS=" + phaseS +
                ", phaseT=" + phaseT +
                ", nutralN=" + nutralN +
                '}';
    }


}
