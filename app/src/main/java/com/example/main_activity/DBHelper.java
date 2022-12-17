package com.example.main_activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


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
                + "imageURL Text,"
                + "date_created Text);"
                );

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        insertWorkout(myDB, date, "Classic Home Workout", "Workout designed for weight loss",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Exercise_Treadmill_Convey_Motion.jpg/1280px-Exercise_Treadmill_Convey_Motion.jpg");

        myDB.execSQL("CREATE TABLE exercise("
                + "exercise_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name Text,"
                + "instruction Text,"
                + "date_created Text,"
                + "workout_id INTEGER,"
                + "FOREIGN KEY (workout_id) REFERENCES workout(workout_id) ON UPDATE SET NULL);"
        );

        myDB.execSQL("CREATE TABLE workout_duration("
                + "workout_id INTEGER,"
                + "duration TEXT,"
                + "workout_status TEXT,"
                + "date_completed TEXT,"
                + "FOREIGN KEY (workout_id) REFERENCES workout(workout_id) ON UPDATE SET NULL);"
                );


        String date2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        insertWorkout(myDB, date2, "Classic workout","This is a home workout, description...",
                "https://upload.wikimedia.org/wikipedia/commons/6/6c/Man_lifting_a_heavy_barbell.jpg");

        insertExercise(myDB,date2,
                "Jumping Jack",
                "Stand upright with your legs together, arms at your sides.\n" +
                "2. Bend your knees slightly, and jump into the air.\n",
                1 );

        insertExercise(myDB,date2,  "Push-up", "instruction a",1 );
        insertExercise(myDB,date2,"Squats", "instruction a",1 );
        insertExercise(myDB,date2,"Lunges", "instruction a",1 );
        insertExercise(myDB,date2,"Plank", "instruction a", 1 );

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
            Log.i("Message:","The user was successfully created");
            return false;
        }
        else{
            Log.e("Error on: ","User could not be created");
            return true;
        }

    }

    private static void insertWorkout(SQLiteDatabase db,String date, String name, String description,String imageURL){
        ContentValues Classic_workout = new ContentValues();
        Classic_workout.put("name", name);
        Classic_workout.put("date_created", date);
        Classic_workout.put("description", description);
        Classic_workout.put("imageURL", imageURL);
        db.insert("workout", null, Classic_workout);
    }

    private static void insertExercise(SQLiteDatabase db, String date_created, String name, String instruction, Integer workout_ID){
        ContentValues content_exercise = new ContentValues();
        content_exercise.put("name", name);
        content_exercise.put("instruction", instruction);
        content_exercise.put("date_created", date_created);
        content_exercise.put("workout_id", workout_ID);
        db.insert("exercise",null,content_exercise);
    }

    public Cursor readWorkout(){
        SQLiteDatabase db = this.getReadableDatabase();
        String qry = "select * from workout";
        Cursor cursor = db.rawQuery(qry,null);
        return cursor;
    }

    public List<Workout> getEveryone(){
        List <Workout> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM workout";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        try{
            if(cursor.moveToFirst()){

                do {

                    String workoutName = cursor.getString(1);
                    String workoutDescription = cursor.getString(2);
                    String imageURL = cursor.getString(3);
                    //int workoutID = cursor.getInt(0);

                    Workout newWorkout = new Workout(workoutName,imageURL,workoutDescription);
                    returnList.add(newWorkout);
                }while (cursor.moveToNext());

            }else{
                //failure do not add anything to the list .
            }
        }catch (Exception e){
            Log.d("ERROR", "Error while trying to get post from database");
        }finally {
            if (cursor != null && cursor.isClosed()){
                cursor.close();
            }
        }

        //close bth cursor

        db.close();
        return  returnList;
    }

    public String[] getWorkouts(){
        String[] LikeWorkout = null;
        SQLiteDatabase myDB = this.getReadableDatabase();
        String queryString = "SELECT * FROM workout";
        Cursor cursor = myDB.rawQuery(queryString,null);

        if(cursor.getCount()> 0){
            int i = 0;
            while(cursor.moveToNext()){
                LikeWorkout[i] = cursor.getString(0);
            }
        }

        return LikeWorkout;
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
