package com.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLiteDatabase.db";
    public static final String TABLE_NAME = "PEOPLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    private SQLiteDatabase database;

    public DataBaseHandler( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        // create table  in onCreate method
        db.execSQL("create table " + TABLE_NAME +
                " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FIRST_NAME + " VARCHAR, "
                + COLUMN_LAST_NAME + " VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertRecord(Bean b) {

        database=this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME,b.getFirstName());
        contentValues.put(COLUMN_LAST_NAME,b.getLastName());
        database.insert(TABLE_NAME,null,contentValues);
        database.close();
    }

    /*Alternate method of insert */
    public void insertRecordAlternate(Bean b) {
        database = this.getReadableDatabase();
        database.execSQL("INSERT INTO " + TABLE_NAME +
                "(" + COLUMN_FIRST_NAME + ","
                + COLUMN_LAST_NAME + ") VALUES('" + b.getFirstName()
                + "','" + b.getLastName() + "')");
        database.close();
    }

    public ArrayList<Bean>getAllRecords()
    {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME,
                null, null, null, null,
                null, null);
        ArrayList<Bean>beanArrayList = new ArrayList<Bean>();
        if (cursor.getCount() > 0) {

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                Bean b = new Bean();
                b.setId(cursor.getString(0));
                b.setFirstName(cursor.getString(1));
                b.setLastName(cursor.getString(2));
                beanArrayList.add(b);
            }
        }
        cursor.close();
        database.close();
        return beanArrayList;
    }

    /*Select data*/
    public ArrayList<Bean> getAllRecordsAlternate() {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Bean>beanArrayList = new ArrayList<Bean>();
        if (cursor.getCount() > 0) {

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                Bean b = new Bean();
                b.setId(cursor.getString(0));
                b.setFirstName(cursor.getString(1));
                b.setLastName(cursor.getString(2));
                beanArrayList.add(b);
            }
        }
        cursor.close();
        database.close();
        return beanArrayList;
    }


}
