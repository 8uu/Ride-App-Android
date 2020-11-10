package com.example.rideapp.Classes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseRideApp extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "RideApp.db";
    public static final String TABLE_NAME1 = "rides_table";
    public static final String TABLE_NAME2 = "users_table";


    public DatabaseRideApp(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @SuppressLint("SQLiteString")
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME2 + "(EMAIL TEXT PRIMARY KEY, " +
                "NUME TEXT, TELEFON TEXT, PAROLA TEXT);");
        db.execSQL("CREATE TABLE " + TABLE_NAME1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "LOC_PLECARE TEXT, DESTINATIE TEXT, NUMAR_LOCURI TEXT, PRET REAL, DATA TEXT," +
                " LOC_BAGAJ INTEGER, EMAIL TEXT, CONSTRAINT fk_users " +
                "FOREIGN KEY (EMAIL) " +
                "REFERENCES " + TABLE_NAME2 + "(EMAIL));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        // db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        // onCreate(db);
    }

    public boolean insertData(String loc_plecare, String destinatie, String nrLocuri, float pret, String data, int locBagaj, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("LOC_PLECARE", loc_plecare);
        contentValues.put("DESTINATIE", destinatie);
        contentValues.put("NUMAR_LOCURI", nrLocuri);
        contentValues.put("PRET", pret);
        contentValues.put("DATA", data);
        contentValues.put("LOC_BAGAJ", locBagaj);
        contentValues.put("EMAIL", email);
        long result = db.insert(TABLE_NAME1, null, contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor response = db.rawQuery("SELECT * FROM " + TABLE_NAME1 + " WHERE EMAIL = \'" + email + "\';", null );
        return response;
    }

    public Cursor getLocations(String adresaPlecare, String adresaSosire) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor response = db.rawQuery("SELECT * FROM " + TABLE_NAME1 +
                " WHERE LOC_PLECARE LIKE \'%" + adresaPlecare + "%\' AND " +
                "DESTINATIE LIKE \'%" + adresaSosire + "%\';", null);
        return response;
    }

    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", user.getEmail());
        contentValues.put("NUME", user.getNume());
        contentValues.put("TELEFON", user.getTelefon());
        contentValues.put("PAROLA", user.getParola());
        long result = db.insert(TABLE_NAME2, null, contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor checkUser(String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor response = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE EMAIL = \'" + email + "\' AND PAROLA = \'" + pass + "\';", null);
        return  response;
    }
}
