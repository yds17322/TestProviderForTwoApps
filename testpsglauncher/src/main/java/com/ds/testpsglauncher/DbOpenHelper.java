package com.ds.testpsglauncher;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by deshui on 2018/11/30
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "for_systemui.db";
    public static final String EVENT_TABLE_NAME = "event";
    private static final int DB_VERSION = 1;

    public static final String ID = "id";
    public static final String LAUNCHER_STATUS = "launcher_status";
    public static final String NAME = "name";

    private String CREATE_EVENT_TABLE = "CREATE TABLE IF NOT EXISTS " + EVENT_TABLE_NAME +
            "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LAUNCHER_STATUS + " INTEGER, "
            + NAME + " TEXT" +
            ")";

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
