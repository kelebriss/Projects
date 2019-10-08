package com.hyojin.project;

import com.hyojin.project.dataSchema.dataTable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper{


    private static final int VERSION = 1;
    private static final String dataBase_Name = "bicycleData.db";

    public database (Context context)
    {
        super(context, dataBase_Name, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ dataTable.NAME+ "(" +
                "_id integer primary key autoincrement, "+
                dataTable.cols.mapInfo +", "+
                dataTable.cols.travelTime+", "+
                dataTable.cols.Disatnce+", "+
                dataTable.cols.caloriesBurned + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int a, int b) {
        // to be added additional function
    }
}
