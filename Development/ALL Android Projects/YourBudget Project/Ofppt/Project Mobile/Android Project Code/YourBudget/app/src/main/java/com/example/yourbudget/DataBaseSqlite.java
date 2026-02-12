package com.example.yourbudget;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseSqlite extends SQLiteOpenHelper {
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table Users(UserName TEXT Primary key NOT NULL,Email TEXT NOT NULL,UserDateNaissance TEXT NOT NULL, UserPassword TEXT NOT NULL,Adress TEXT NOT NULL)");
        sqLiteDatabase.execSQL("Create table Incomes(IncomeId INTEGER PRIMARY KEY AUTOINCREMENT ,IncomeName TEXT,IncomeAmount real,IncomeCategory TEXT,IncomeDate TEXT,IncomeDescription TEXT,IncomeUser TEXT , foreign key (IncomeUser) references Users(UserName))");
        sqLiteDatabase.execSQL("create table Expenses(ExpenseId Integer primary key autoincrement,ExpenseName TEXT,ExpenseAmount real,ExpenseCategory Text,ExpenseDate Text,ExpenseDescription Text,ExpenseUser Text,Foreign key (ExpenseUser) references Users(UserName))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public DataBaseSqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public boolean RegisterUser(String un,String mail,String date ,String psw,String adress ){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("UserName",un);
        cv.put("Email",mail);
        cv.put("UserDateNaissance",date);
        cv.put("UserPassword",psw);
        cv.put("Adress",adress);
        long check=db.insert("Users",null,cv);
        if(check==-1){
            return false;

        }
        else{
            return true;
        }
    }
    public boolean UpdateUserPassword(String pass){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("UserPassword",pass);
        long check=db.update("Users",cv,"UserPassword=?",new String[]{pass});
        if(check==-1){
            return false;

        }
        else{
            return true;
        }
    }


    public boolean CheckEmailIfExist(String Email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c= db.rawQuery("select * from Users where Email=?",new String[]{Email});
        if(c.getCount()>0){
            return true;
        }
        else{
            return false;
        }


    }

    public  boolean CheckUserNameAndPassword(String un,String ps){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor c= db.rawQuery("Select * from Users where UserName=? and UserPassword=?",new String[]{un,ps});
        if(c.getCount()>0){
            return true;

        }
        else{
            return false;

        }

    }
    public  boolean AddIncome(String nom,float Iamount,String IncomeCat,String Date,String IncomeDesc,String UserIncome){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("IncomeName",nom);
        cv.put("IncomeAmount",Iamount);
        cv.put("IncomeCategory",IncomeCat);
        cv.put("IncomeDate",Date);
        cv.put("IncomeDescription ",IncomeDesc);
        cv.put("IncomeUser",UserIncome);

        long check=db.insert("Incomes",null,cv);
        if(check==-1){
            return false;

        }
        else{
            return true;
        }


    }

    public void AddExpense(String nom,float Examount,String ExpenseCat,String Date,String ExpenseDesc,String UserIncome){

            SQLiteDatabase db =this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put("ExpenseName",nom);
            cv.put("ExpenseAmount",Examount);
            cv.put("ExpenseCategory",ExpenseCat);
            cv.put("ExpenseDate",Date);
            cv.put("ExpenseDescription ",ExpenseDesc);
            cv.put("ExpenseUser",UserIncome);
            db.insert("Expenses",null,cv);

    }
    public float SumIncomes(String User){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor c= db.rawQuery("select Sum(IncomeAmount) from Incomes where IncomeUser=?",new String[]{User});
        c.moveToLast();
        float sum=c.getFloat(0);
        return sum;
    }
    public float SumExpense(String User){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor c= db.rawQuery("select Sum(ExpenseAmount) from Expenses where ExpenseUser=?",new String[]{User});
        c.moveToLast();
        float sum=c.getFloat(0);
        return sum;
    }
    public float MaxIncomes(String User){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c= db.rawQuery("select MAX(IncomeAmount)  from Incomes where IncomeUser=? ",new String[]{User});
        c.moveToLast();
        float max=c.getFloat(0);
        return max;

    }
    public float MaxExpense(String User){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor c= db.rawQuery("select Max(ExpenseAmount) from Expenses where ExpenseUser=?",new String[]{User});
        c.moveToLast();
        float max=c.getFloat(0);
        return max;
    }
//    public String[] MaxExpenseDetails(String User){
//        SQLiteDatabase db =this.getReadableDatabase();
//        String T;
//        Cursor c= db.rawQuery("select Max(ExpenseAmount),ExpenseName,ExpenseAmount,ExpenseDate,ExpenseDescription from Expenses where ExpenseUser=?",new String[]{User});
//        while (c.moveToNext()){
//            String max=c.getString(0);
//            String name=c.getString(1);
//            String amount=c.getString(2);
//            String date=c.getString(3);
//            String description=c.getString(4);
//            T=new String{max,name,amount,date,description};
//            T.add(T);
//        }
//
//        return T;
//    }
    public Cursor MaxExpenseDetails(String User){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor c=db.rawQuery("select Max(ExpenseAmount),ExpenseName,ExpenseAmount,ExpenseDate,ExpenseDescription from Expenses where ExpenseUser=?",new String[]{User});
        return c;
    }
    public Cursor MinExpenseDetails(String User){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor c=db.rawQuery("select Min(ExpenseAmount),ExpenseName,ExpenseAmount,ExpenseDate,ExpenseDescription from Expenses where ExpenseUser=?",new String[]{User});
        return c;
    }
    public Cursor MinIncomeDetails(String User){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor c=db.rawQuery("select Min(IncomeAmount),IncomeName,IncomeAmount,IncomeDate,IncomeDescription from Incomes where IncomeUser=?",new String[]{User});
        return c;
    }
    public Cursor MaxIncomeDetails(String User){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor c=db.rawQuery("select Max(IncomeAmount),IncomeName,IncomeAmount,IncomeDate,IncomeDescription from Incomes where IncomeUser=?",new String[]{User});
        return c;
    }
    public float MINExpense(String User){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor c= db.rawQuery("select Min(ExpenseAmount) from Expenses where ExpenseUser=?",new String[]{User});
        c.moveToLast();
        float min=c.getFloat(0);
        return min;
    }
    public float MinIncomes(String User){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c= db.rawQuery("select Min(IncomeAmount) from Incomes where IncomeUser=? ",new String[]{User});
        c.moveToLast();
        float min=c.getFloat(0);
        return min;

    }
    public int TransactionsIncomes(String User){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c= db.rawQuery("select count(*) from Incomes where IncomeUser=? ",new String[]{User});
        c.moveToLast();
        int nombreTra=c.getInt(0);
        return nombreTra;

    }
    public int TransactionsExpense(String User){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor c= db.rawQuery("select Count(*) from Expenses where ExpenseUser=?",new String[]{User});
        c.moveToLast();
        int NombreTra=c.getInt(0);
        return NombreTra;
    }
    @SuppressLint("Range")
    public ArrayList<String> ViewIncomes(String us){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c= db.rawQuery("select * from Incomes where IncomeUser=?",new String[]{us});

        ArrayList<String> liste = new ArrayList<String>();
        c.moveToFirst();
        while(c.isAfterLast()==false){
            String IncomeName;
//            String IncomeCategoty;
//            String IncomeAmount;
            String IncomeDate;
            String Incomedescription;
            IncomeName=c.getString(c.getColumnIndex("IncomeName"));
//            IncomeCategoty=c.getString(c.getColumnIndex("IncomeCategory "));
//            IncomeAmount=c.getString(c.getColumnIndex("IncomeAmount"));
            IncomeDate=c.getString(c.getColumnIndex("IncomeDate"));
            Incomedescription=c.getString(c.getColumnIndex("IncomeDescription"));
            liste.add(IncomeName+"||"+"||"+IncomeDate+"||"+Incomedescription);
            c.moveToNext();

        }

        return liste;


    }
    public boolean CheckUserIfExist(String un){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c= db.rawQuery("select * from Users where UserName=?",new String[]{un});
        if(c.getCount()>0){
            return true;
        }
        else{
            return false;
        }


    }
    @SuppressLint("Range")
    public ArrayList<String> ViewExpenses(String us){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c= db.rawQuery("select * from Expenses where ExpenseUser=?",new String[]{us});

        ArrayList<String> liste = new ArrayList<String>();
        c.moveToFirst();
        while(c.isAfterLast()==false){
            String ExpenseName;
//            String IncomeCategoty;
//            String IncomeAmount;
            String ExpenseDate;
            String Expensedescription;
            ExpenseName=c.getString(c.getColumnIndex("ExpenseName"));
//            IncomeCategoty=c.getString(c.getColumnIndex("IncomeCategory "));
//            IncomeAmount=c.getString(c.getColumnIndex("IncomeAmount"));
            ExpenseDate=c.getString(c.getColumnIndex("ExpenseDate"));
            Expensedescription=c.getString(c.getColumnIndex("ExpenseDescription"));
            liste.add(ExpenseName+"||"+"||"+ExpenseDate+"||"+Expensedescription);
            c.moveToNext();

        }

        return liste;


    }
    public ArrayList<CustomListView> CustomListView(String us){
        ArrayList<CustomListView> liste= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c= db.rawQuery("select * from Incomes where IncomeUser=?",new String[]{us});
        while (c.moveToNext()){
            String id=c.getString(0);
            String name=c.getString(1);
            String amount=c.getString(2);
            String date=c.getString(4);
            CustomListView customListView=new CustomListView(name,date,amount,id);
            liste.add(customListView);

        }
        return liste;
    }
    public ArrayList<CustomListView> CustomListViewExpenses(String us){
        ArrayList<CustomListView> liste= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c= db.rawQuery("select * from Expenses where ExpenseUser=?",new String[]{us});
        while (c.moveToNext()){
            String id=c.getString(0);
            String name=c.getString(1);
            String amount=c.getString(2);
            String date=c.getString(4);
            CustomListView customListView=new CustomListView(name,date,amount,id);
            liste.add(customListView);

        }
        return liste;
    }
    public boolean UpdateIncome(String n,float m,String d,String id){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("IncomeName",n);
        cv.put("IncomeAmount",m);
        cv.put("IncomeDate",d);
        cv.put("IncomeId",id);
        long check=db.update("Incomes",cv,"IncomeId=?",new String[]{id});
        if(check==-1){
            return false;

        }
        else{
            return true;
        }

    }
    public boolean UpdateExpense(String n,float m,String d,String id){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ExpenseName",n);
        cv.put("ExpenseAmount",m);
        cv.put("ExpenseDate",d);
        cv.put("ExpenseId",id);
        long check=db.update("Expenses",cv,"ExpenseId=?",new String[]{id});
        if(check==-1){
            return false;

        }
        else{
            return true;
        }

    }
    public boolean DeleteIncome(String id){
        SQLiteDatabase db =this.getWritableDatabase();
        long check = db.delete("Incomes","IncomeId=?",new String[]{id});
        if(check==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean DeleteExpense(String id){
        SQLiteDatabase db =this.getWritableDatabase();
        long check = db.delete("Expenses","ExpenseId=?",new String[]{id});
        if(check==-1){
            return false;
        }
        else{
            return true;
        }
    }


}
