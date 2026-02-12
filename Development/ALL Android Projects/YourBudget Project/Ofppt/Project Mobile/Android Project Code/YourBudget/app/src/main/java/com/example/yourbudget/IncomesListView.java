package com.example.yourbudget;

public class IncomesListView {
    public String Name;
    public String  Date;
    public String Amount;

    public IncomesListView() {
    }

    public IncomesListView(String name, String date,String amount) {
        Name = name;
        Date=date;
        Amount=amount;
    }

    public String getName() {
        return Name;
    }

    public String getDate() {
        return Date;
    }

    public String getAmount() {
        return Amount;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
