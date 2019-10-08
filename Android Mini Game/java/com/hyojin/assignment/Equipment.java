package com.hyojin.assignment;

import java.io.Serializable;

public class Equipment extends Item implements Serializable{
    private double mMass;

    public double getMass() {
        return mMass;
    }

    public void setMass(double mass) {
        mMass = mass;
    }


}
