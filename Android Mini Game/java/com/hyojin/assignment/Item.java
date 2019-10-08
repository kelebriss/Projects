package com.hyojin.assignment;

import java.io.Serializable;

class Item implements Serializable{
    private String mDescription;
    private int mValue;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }


}
