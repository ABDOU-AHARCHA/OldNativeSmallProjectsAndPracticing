package com.example.yourbudget;

public class Categories {

    public String Name;
    public int Image;

    public Categories() {
    }

    public Categories(String name, int image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public int getImage() {
        return Image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImage(int image) {
        Image = image;
    }
}
