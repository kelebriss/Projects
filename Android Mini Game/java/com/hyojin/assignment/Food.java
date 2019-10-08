package com.hyojin.assignment;

import java.io.Serializable;
import java.util.Date;

public class Food extends Item implements Serializable{

    private double mHealth;

    public double getHealth() {
        return mHealth;
    }

    public void setHealth(double health) {
        mHealth = health;
    }

}
