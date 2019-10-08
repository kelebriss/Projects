package com.hyojin.assignment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Area implements Serializable {
    private boolean mTown;
    private List<Item> mItems;
    private String mDescription;
    private boolean mStarred;
    private int mPosition;
    private boolean mExplored;

    public Area()
    {

    }

    public Area(boolean town, List<Item> items, String description, boolean starred, int position, boolean explored) {
        mTown = town;
        mItems = items;
        mDescription = description;
        mStarred = starred;
        mPosition = position;
        mExplored = explored;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public boolean isTown() {
        return mTown;
    }

    public void setTown(boolean town) {
        mTown = town;
    }

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }
    public void setItems(Item item) {
        mItems.add(item);
    }
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isStarred() {
        return mStarred;
    }

    public void setStarred(boolean starred) {
        mStarred = starred;
    }

    public boolean isExplored() {
        return mExplored;
    }

    public void setExplored(boolean explored) {
        mExplored = explored;
    }



}

