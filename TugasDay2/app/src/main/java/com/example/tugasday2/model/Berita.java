package com.example.tugasday2.model;


import java.io.Serializable;

public class Berita implements Serializable {
    private String title;
    private String category;
    private String image;

    public Berita () {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
