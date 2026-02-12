package com.example.yourbudget;

public class CustomListView {

    public String Name;
    public  String Amount;
    public String Date;
    public String id;
    public String User;
    public int image;
    public String Description;

    public String getId() {
        return id;
    }

    public String getUser() {
        return User;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(String user) {
        User = user;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public CustomListView(){

    }

    public String getName() {
        return Name;
    }

    public String getAmount() {
        return Amount;
    }

    public String getDate() {
        return Date;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public void setDate(String date) {
        Date = date;
    }

    public CustomListView(String name, String amount, String date) {
        Name = name;
        Amount = amount;
        Date = date;

    }
    public CustomListView(String name, String amount, String date,String Id) {
        Name = name;
        Amount = amount;
        Date = date;

        id=Id;


    }
    public CustomListView(String name, String amount, String date,String description,String Id) {
        Name = name;
        Amount = amount;
        Date = date;
        Description=description;

        id=Id;


    }

}
