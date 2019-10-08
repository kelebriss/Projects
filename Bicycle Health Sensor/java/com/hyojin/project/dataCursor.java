package com.hyojin.project;

import android.database.Cursor;
import android.database.CursorWrapper;

public class dataCursor extends CursorWrapper {
    public dataCursor (Cursor cursor) {super(cursor);}

    public Data getData()
    {
        byte[] mapInfo = getBlob(getColumnIndex(dataSchema.dataTable.cols.mapInfo));
        int travelTime = getInt(getColumnIndex(dataSchema.dataTable.cols.travelTime));
        double distance = getDouble(getColumnIndex(dataSchema.dataTable.cols.Disatnce));
        int calories = getInt(getColumnIndex(dataSchema.dataTable.cols.caloriesBurned));

        return new Data(mapInfo, travelTime, distance ,calories);
    }

}
