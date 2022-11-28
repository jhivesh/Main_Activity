package com.example.main_activity;

public class WorkoutModel {
    private String Title;
    private String Description;
    private int image;
    private String date_created;


    public WorkoutModel(String title, String description, int image,
                        String date_created) {
        Title = title;
        Description = description;
        this.image = image;
        this.date_created = date_created;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getImage() {
        return image;
    }

    public String getDate_created() {
        return date_created;
    }
}
