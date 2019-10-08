package com.hyojin.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.hyojin.project.dataSchema.dataTable;

public class dataStore {
    private SQLiteDatabase db;
    public dataStore(Context context)
    {
        this.db = new database(context.getApplicationContext()).getWritableDatabase();
    }

    public void addData(Data data)
    {
        ContentValues cv = new ContentValues();
        cv.put(dataTable.cols.mapInfo, data.getMaMap());
        cv.put(dataTable.cols.travelTime, data.getTime());
        cv.put(dataTable.cols.travelTime, data.getDistance());
        cv.put(dataTable.cols.caloriesBurned, data.getCalroie());
        db.insert(dataTable.NAME, null, cv);
    }

    public void updateData(Data data)
    {
        ContentValues cv = new ContentValues();

        String[] whereValue = { String.valueOf(data.getMaMap())};
        db.update(dataTable.NAME, cv, dataTable.cols.mapInfo + " = ?", whereValue);
    }

    public void removeData(Data data)
    {
        String[] whereValue = { String.valueOf(data.getMaMap())};
        db.delete(dataTable.NAME, dataTable.cols.mapInfo + " = ?", whereValue);
    }
}
