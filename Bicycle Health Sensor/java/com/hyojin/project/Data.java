package com.hyojin.project;

public class Data {
    private double mDistance;
    private int mCalroie;
    private int mTime;
    private byte[] maMap;


    Data(byte[] map, int time, double distance, int calroie)
    {
        this.maMap = map;
        this.mTime = time;
        this.mDistance = distance;
        this.mCalroie = calroie;
    }

    public double getDistance() {
        return mDistance;
    }

    public void setDistance(double distance) {
        mDistance = distance;
    }

    public int getCalroie() {
        return mCalroie;
    }

    public void setCalroie(int calroie) {
        mCalroie = calroie;
    }

    public int getTime() {
        return mTime;
    }

    public void setTime(int time) {
        mTime = time;
    }

    public byte[] getMaMap() {
        return maMap;
    }

    public void setMaMap(byte[] maMap) {
        this.maMap = maMap;
    }
}
