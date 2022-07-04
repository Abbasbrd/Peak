package com.anar.PEAK;

public class LoadDto {
    public LoadDto() {
    }
    public LoadDto(int r, int s, int t, int n, int cap) {
        R=r;
        S=s;
        T=t;
        N=n;
        Cap =cap;
    }
    private static int R;
    private static int S;
    private static int T;
    private static int N;
    private static int Cap;

    public static int getCap() {
        return R;
    }
    public static int getR() {
        return R;
    }

    public static int getS() {
        return S;

    }


    public static int getT() {
        return T;

    }

    public static int getN() {
        return N;

    }

    public static void setCap(int cap) {
        LoadDto.Cap = cap;
    }

    public static void setR(int r) {
        LoadDto.R = r;
    }

    public static void setS(int s) {
        LoadDto.S = s;
    }

    public static void setN(int n) {
        LoadDto.N = n;
    }

    public static void setT(int t) {
        LoadDto.T = t;
    }


}
