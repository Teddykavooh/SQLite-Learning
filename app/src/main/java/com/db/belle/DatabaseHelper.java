package com.db.belle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Table Name
    public static final String TABLE_NAME = "DATA_TABLE";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String GENDER ="gender";
    public static final String EMAIL = "mail";
    public static final String DOB = "dob";

    // Database Info
    static final String DB_NAME = "BELLE.DB";
    // Database Version
    static  final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + " ("
            + _ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT NOT NULL, "
            + AGE + " INTEGER NOT NULL, "
            + GENDER + " TEXT NOT NULL, "
            + EMAIL + " TEXT, "
            + DOB + " DATE);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.e("Database Creation", "Tables created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
