package com.example.movies;

public class Movie_modal {
    int img;
    String name, releaseDate;
    boolean isSelected;

    public Movie_modal(int img, String name, String releaseDate) {
        this.img = img;
        this.name = name;
        this.releaseDate = releaseDate;

    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }
}
