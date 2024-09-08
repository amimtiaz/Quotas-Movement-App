package com.imtiaz_acedamy.july2024app.Domain;

import java.io.Serializable;

public class NewsItems implements Serializable {
    private String title;
    private String time;
    private String description;
    private int ImagePath;
    private String place;


    public NewsItems(String title, String time, String description, int imagePath, String place) {
        this.title = title;
        this.time = time;
        this.description = description;
        ImagePath = imagePath;
        this.place = place;
    }

    public NewsItems() {
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }



    public int getImagePath() {
        return ImagePath;
    }

    public void setImagePath(int imageResId) {
        this.ImagePath = imageResId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
