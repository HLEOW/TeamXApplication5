package com.example.reedhamilton.teamxapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Reed Hamilton on 3/25/2017.
 */

public class TeamXDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Different.db";
    private static final int DATABASE_VERSION = 2;
    public static final String PERSON_TABLE_NAME = "users";
    public static final String PERSON_COLUMN_USERNAME = "username";
    public static final String PERSON_COLUMN_PASSWORD = "password";
    public static final String PERSON_COLUMN_NAME = "name";
    public static final String PERSON_COLUMN_GENDER = "gender";
    public static final String PERSON_COLUMN_AGE = "age";
    public static final String PERSON_COLUMN_LOCATION = "location";
    public static final String PERSON_COLUMN_SCHOOL = "school";
    public static final String PERSON_COLUMN_EMAIL = "email";


    public TeamXDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + PERSON_TABLE_NAME + "(" +
                PERSON_COLUMN_USERNAME + " VARCHAR(20) PRIMARY KEY, " +
                PERSON_COLUMN_PASSWORD + " TEXT, " +
                PERSON_COLUMN_NAME + " TEXT, " +
                PERSON_COLUMN_GENDER + " TEXT, " +
                PERSON_COLUMN_AGE + " INTEGER, " +
                PERSON_COLUMN_LOCATION + " TEXT, " +
                PERSON_COLUMN_SCHOOL + " TEXT, " +
                PERSON_COLUMN_EMAIL + " TEXT);"
        );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertPerson(String username, String pwd, String name, String gender, int age, String location, String schoolType, String email) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PERSON_COLUMN_USERNAME, username);
        contentValues.put(PERSON_COLUMN_PASSWORD, pwd);
        contentValues.put(PERSON_COLUMN_NAME, name);
        contentValues.put(PERSON_COLUMN_GENDER, gender);
        contentValues.put(PERSON_COLUMN_AGE, age);
        contentValues.put(PERSON_COLUMN_LOCATION, location);
        contentValues.put(PERSON_COLUMN_SCHOOL, schoolType);
        contentValues.put(PERSON_COLUMN_EMAIL, email);
        long result = db.insert(PERSON_TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updatePerson(String username, String pwd, String name, String gender, int age, String location, String schoolType, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PERSON_COLUMN_USERNAME, username);
        contentValues.put(PERSON_COLUMN_PASSWORD, pwd);
        contentValues.put(PERSON_COLUMN_NAME, name);
        contentValues.put(PERSON_COLUMN_GENDER, gender);
        contentValues.put(PERSON_COLUMN_AGE, age);
        contentValues.put(PERSON_COLUMN_LOCATION, location);
        contentValues.put(PERSON_COLUMN_SCHOOL, schoolType);
        contentValues.put(PERSON_COLUMN_EMAIL, email);
        long result = db.update(PERSON_TABLE_NAME, contentValues, PERSON_COLUMN_USERNAME + " = ? ", new String[]{username});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean getPerson(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + PERSON_TABLE_NAME + " WHERE " +
                PERSON_COLUMN_USERNAME + "=?", new String[]{id});
        if (res.getCount() <= 0) {
            res.close();
            return false;
        }
        res.close();
        return true;
    }

    public boolean getPassword(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + PERSON_TABLE_NAME + " WHERE " +
                PERSON_COLUMN_PASSWORD + "=?", new String[]{id});
        if (res.getCount() <= 0) {
            res.close();
            return false;
        }
        res.close();
        return true;
    }

    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + PERSON_TABLE_NAME, null);
        return res;
    }
}
