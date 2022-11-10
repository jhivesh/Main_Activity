package com.example.main_activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Logic.db",null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE users("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username Text,"
                + "firstName Text,"
                + "lastName Text,"
                + "password Text)"
        );

        myDB.execSQL("CREATE TABLE login("
                        + "_id INTEGER,"
                        + "password TEXT,"
                        + "FOREIGN KEY(_id) REFERENCES users(_id) ON UPDATE SET NULL)"
                );


        myDB.execSQL("CREATE TABLE workout("
                + "workout_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name Text,"
                + "description Text,"
                + "date_created Text);"
                );

        myDB.execSQL("CREATE TABLE workout_duration("
                + "workout_id INTEGER,"
                + "duration TEXT,"
                + "workout_status TEXT,"
                + "date_completed TEXT,"
                + "FOREIGN KEY (workout_id) REFERENCES workout(workout_id) ON UPDATE SET NULL);"
                );



    }



    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("DROP TABLE IF EXISTS users");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("username", username);
        contentvalues.put("password", password);
        long result = myDB.insert("users",null,contentvalues);

        if (result == -1) {
            return false;
        }
        else{
            return true;
        }

    }


    public boolean checkusername(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ?", new String [] {username});
        if (cursor.getCount() >0) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean checkusernamePassword(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password = ?", new String [] {username, password});
        if (cursor.getCount() >0) {
            return true;
        }
        else
        {
            return false;
        }
    }



}
