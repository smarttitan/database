package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {

    // DatabaseHelper Name
    private DatabaseHelper dbHelper;

    // Context Name
    private Context context;

    // SQLite Database Name
    private SQLiteDatabase database;

    // DBManager Method
    public DBManager(Context c) {
        context = c;
    }

    // Open Database Method
    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    // Close Database Method
    public void close(){
        dbHelper.close();
    }

    // Add Account Method
    public void insert(String add_account) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.ADD_ACCOUNT, add_account);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    // Read Database Data
    public Cursor fetch() {
        String[] columns = new String[] {
                DatabaseHelper._ID, DatabaseHelper.ADD_ACCOUNT
        };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);

        // Fetch DATA to Log
        if (cursor != null){
            Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));
            cursor.moveToFirst();
        }

        return cursor;
    }

}
