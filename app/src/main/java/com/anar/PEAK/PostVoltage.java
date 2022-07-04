package com.anar.PEAK;

public class PostVoltage {

    private int id;
    private int TR;
    private int ST;
    private int RS;
    private int RN;
    private int TN;
    private int SN;

    public PostVoltage(){}

    public PostVoltage(int TR, int ST, int RS, int RN, int SN, int TN) {
        this.TR = TR;
        this.ST = ST;
        this.RS = RS;
        this.RN = RN;
        this.SN = SN;
        this.TN = TN;
    }

    public int getTN() {
        return TN;
    }

    public void setTN(int TN) {
        this.TN = TN;
    }

    public int getSN() {
        return SN;
    }

    public void setSN(int SN) {
        this.SN = SN;
    }



    public int getTR() {
        return TR;
    }

    public void setTR(int TR) {
        this.TR = TR;
    }

    public int getST() {
        return ST;
    }

    public void setST(int ST) {
        this.ST = ST;
    }

    public int getRS() {
        return RS;
    }

    public void setRS(int RS) {
        this.RS = RS;
    }

    public int getRN() {
        return RN;
    }

    public void setRN(int RN) {
        this.RN = RN;
    }


}
