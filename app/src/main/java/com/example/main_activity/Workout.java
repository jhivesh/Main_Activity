package com.example.main_activity;



public class Workout {
    private String  name;
    private String imageResourceURL;
    private String description;


    public static final Workout[] workouts = {
            new Workout("Home workout",
                    "https://upload.wikimedia.org/wikipedia/commons/6/6c/Man_lifting_a_heavy_barbell.jpg",
                    "This is a description for classic 1"),
            new Workout("Challenge Workout","https://upload.wikimedia.org/wikipedia/commons/6/6c/Man_lifting_a_heavy_barbell.jpg",
                    "This is a description for classic 2")
    };






    public Workout(String name, String imageResourceURL, String description) {
        this.name = name;
        this.imageResourceURL = imageResourceURL;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getImageResourceURL() {
        return imageResourceURL;
    }

    public String getDescription() {
        return description;
    }


}
