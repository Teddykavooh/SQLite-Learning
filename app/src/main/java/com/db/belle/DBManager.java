package com.db.belle;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
//import java.sql.SQLException;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;


public class DBManager {
    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public void open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String age, String gender, String mail/*, Date dob*/) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.AGE, age);
        contentValue.put(DatabaseHelper.GENDER, gender);
        contentValue.put(DatabaseHelper.EMAIL, String.valueOf(mail));
        //contentValue.put(DatabaseHelper.DOB, String.valueOf(dob));
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.AGE, DatabaseHelper.GENDER, DatabaseHelper.EMAIL/*, DatabaseHelper.DOB*/};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void update(long _id, String name, Integer age, String gender, String mail/*, Date dob*/) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.AGE, age);
        contentValues.put(DatabaseHelper.GENDER, gender);
        contentValues.put(DatabaseHelper.EMAIL, String.valueOf(mail));
        //contentValues.put(DatabaseHelper.DOB, String.valueOf(dob));
        database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }
}
