package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "ACCOUNT";

    // Table Columns
    public static final String _ID = "_id";
    public static final String ADD_ACCOUNT = "add_account";

    // Database Information
    static final String DB_NAME = "TYC_ACCOUNT.DB";

    // Database Version
    static final int DB_VERSION = 1;

    // Creating Table Query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + ADD_ACCOUNT
            + " TEXT NOT NULL );";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
