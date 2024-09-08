package com.imtiaz_acedamy.july2024app.Domain;

import java.io.Serializable;

public class Sohid_items implements Serializable {
    private String name;
    private String background;
    private String SchoolName;
    private String DeathDate;
    private String BirthPlace;
    private String lifeHistory;
    private String deathHistory;
    private int ImagePath;

    public Sohid_items(String name, String background, String schoolName, String deathDate, String birthPlace, String lifeHistory, String deathHistory, int imagePath) {
        this.name = name;
        this.background = background;
        SchoolName = schoolName;
        DeathDate = deathDate;
        BirthPlace = birthPlace;
        this.lifeHistory = lifeHistory;
        this.deathHistory = deathHistory;
        ImagePath = imagePath;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    public String getDeathDate() {
        return DeathDate;
    }

    public void setDeathDate(String deathDate) {
        DeathDate = deathDate;
    }

    public String getBirthPlace() {
        return BirthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        BirthPlace = birthPlace;
    }

    public String getLifeHistory() {
        return lifeHistory;
    }

    public void setLifeHistory(String lifeHistory) {
        this.lifeHistory = lifeHistory;
    }

    public String getDeathHistory() {
        return deathHistory;
    }

    public void setDeathHistory(String deathHistory) {
        this.deathHistory = deathHistory;
    }

    public int getImagePath() {
        return ImagePath;
    }

    public void setImagePath(int imagePath) {
        ImagePath = imagePath;
    }
}
