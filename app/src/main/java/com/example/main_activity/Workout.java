package com.example.main_activity;

public class Workout {
    private String  name;
    private int imageResourceID;
    private String description;

    public static final Workout[] workouts = {
            new Workout("Home workout", R.drawable.classic_1,"This is a description for classic 1"),
            new Workout("Challenge Workout", R.drawable.classic_1,"This is a description for classic 2")
    };


    public Workout(String name, int imageResourceID, String description) {
        this.name = name;
        this.imageResourceID = imageResourceID;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public String getDescription() {
        return description;
    }
}
