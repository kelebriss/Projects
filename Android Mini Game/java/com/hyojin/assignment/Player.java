package com.hyojin.assignment;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable {
    private int mRowLocation;
    private int mColLocation;
    private int mCash;
    private double mHealth;
    private double mEquipmentMass;
    private List<Equipment> mEquipment;


    public Player(int rowLocation, int colLocation, int cash, double health, List<Equipment> equipment) {
        mRowLocation = rowLocation;
        mColLocation = colLocation;
        mCash = cash;
        mHealth = health;
        mEquipment = equipment;
    }


    public int getRowLocation() {
        return mRowLocation;
    }

    public void setRowLocation(int rowLocation) {
        mRowLocation = rowLocation;
    }

    public int getColLocation() {
        return mColLocation;
    }

    public void setColLocation(int colLocation) {
        mColLocation = colLocation;
    }

    public int getCash() {
        return mCash;
    }

    public void setCash(int cash) {
        mCash = cash;
    }

    public double getHealth() {
        return mHealth;
    }

    public void setHealth(double health) {
        mHealth = health;
    }

    public double getEquipmentMass() {

        for (int i = 0; i < mEquipment.size(); i++) {
            mEquipmentMass += mEquipment.get(i).getMass();
        }
        return mEquipmentMass;
    }

    public List<Equipment> getEquipment() {
        return mEquipment;
    }

    public void setEquipment(Equipment item) {
        mEquipment.add(item);
    }


    public boolean haswon() {
        boolean jd, ic, rm;
        jd = false;
        ic = false;
        rm = false;
        Equipment item;

        for (int i = 0; i < getEquipment().size(); i++) {
            item = getEquipment().get(i);

            if (item.getDescription().equals("JadeMonkey")) {
                jd = true;
            }
            if (item.getDescription().equals("Ice Scrapper")) {
                ic = true;
            }
            if (item.getDescription().equals("Roadmap")) {
                rm = true;
            }

            if (jd == true && ic == true && rm == true) {
                return true;
            }
        }
        return false;

    }
}
