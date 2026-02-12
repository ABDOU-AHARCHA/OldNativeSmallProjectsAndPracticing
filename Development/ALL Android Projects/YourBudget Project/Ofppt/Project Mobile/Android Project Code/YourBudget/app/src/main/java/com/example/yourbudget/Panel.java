package com.example.yourbudget;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import io.github.muddz.styleabletoast.StyleableToast;

public class Panel extends AppCompatActivity {

    Button tester;
    Button effacer;
    TextView t11,t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    TextView t8;
    CardView cardView1;
    DataBaseSqlite db;
    CardView cv1,cv2,cv3,cv4,cv5,cv6,cv7,cv8;
    CustomListView clv;
    ArrayList<String > arraylist;
    AdapterListView adapter;;

    FloatingActionButton acrionbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);
        db = new DataBaseSqlite(getApplicationContext(),"Gestion2",null,1);




        t11=findViewById(R.id.t1);
        t1=findViewById(R.id.t11);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);
        t6=findViewById(R.id.t6);
        t7=findViewById(R.id.t7);
        t8=findViewById(R.id.t8);
        acrionbutton=findViewById(R.id.floatingActionButton);
        cardView1=findViewById(R.id.cardview1);
        Float maxincome,maxexpense,minincome,minexpense,sumincome,sumexpense;
        Integer incometransaction,expesnetransactions;
        maxexpense=db.MaxExpense(Login.User);
        minexpense=db.MINExpense(Login.User);
        maxincome=db.MaxIncomes(Login.User);
        minincome=db.MinIncomes(Login.User);
        sumexpense=db.SumExpense(Login.User);
        sumincome=db.SumIncomes(Login.User);
        incometransaction=db.TransactionsIncomes(Login.User);
        expesnetransactions=db.TransactionsExpense(Login.User);





        t1.setText(sumexpense.toString());
        t2.setText(sumincome.toString());
        t3.setText(maxexpense.toString());
        t4.setText(minexpense.toString());
        t5.setText(minincome.toString());
        t6.setText(maxincome.toString());
        t7.setText(expesnetransactions.toString());
        t8.setText(incometransaction.toString());
        cv1=findViewById(R.id.cardview1);
        cv2=findViewById(R.id.cardview10);
        cv3=findViewById(R.id.cardview3);
        cv4=findViewById(R.id.cardview4);
        cv5=findViewById(R.id.cardview5);
        cv6=findViewById(R.id.cardview6);
        cv7=findViewById(R.id.cardview7);
        cv8=findViewById(R.id.cardview8);
        arraylist= new ArrayList<String>();

        float maxInc=Float.parseFloat(sumincome.toString());
        float maxExp=Float.parseFloat(sumexpense.toString());
        float result=maxInc-maxExp;
        t11.setText(String.valueOf(result));


        acrionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Panel.this,MenuActivity.class);
                startActivity(intent);
            }
        });
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Panel.this);
                LayoutInflater inflater=LayoutInflater.from(getApplicationContext());
                View v= inflater.inflate(R.layout.alert_dialog_cardviews,null);
                builder1.setView(v);
                final AlertDialog dialog= builder1.create();
                final TextView tv1=v.findViewById(R.id.textView19);
                final TextView tv2=v.findViewById(R.id.textView21);
                final TextView tv3=v.findViewById(R.id.textView23);
                final TextView tv4=v.findViewById(R.id.textView25);
                final Button annuler=v.findViewById(R.id.button7);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Cursor c= db.MaxExpenseDetails(Login.User);
                if(c.getCount()==0){

                    StyleableToast.makeText(getApplicationContext(), "Pas de données", Toast.LENGTH_LONG, R.style.NoData).show();
                }
                else{
                    while (c.moveToNext()){
                        tv1.setText(c.getString(1));
                        tv2.setText(c.getString(2));
                        tv3.setText(c.getString(3));
                        tv4.setText(c.getString(4));


                    }
                }
                dialog.show();
                annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Panel.this);
                LayoutInflater inflater=LayoutInflater.from(getApplicationContext());
                View v= inflater.inflate(R.layout.alert_dialog_cardviews,null);
                builder1.setView(v);
                final AlertDialog dialog= builder1.create();
                final TextView tv1=v.findViewById(R.id.textView19);
                final TextView tv2=v.findViewById(R.id.textView21);
                final TextView tv3=v.findViewById(R.id.textView23);
                final TextView tv4=v.findViewById(R.id.textView25);
                final Button annuler=v.findViewById(R.id.button7);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Cursor c= db.MinExpenseDetails(Login.User);
                if(c.getCount()==0){
                    StyleableToast.makeText(getApplicationContext(), "Pas de données", Toast.LENGTH_LONG, R.style.NoData).show();
                }
                else{
                    while (c.moveToNext()){
                        tv1.setText(c.getString(1));
                        tv2.setText(c.getString(2));
                        tv3.setText(c.getString(3));
                        tv4.setText(c.getString(4));


                    }
                }
                dialog.show();
                annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Panel.this);
                LayoutInflater inflater=LayoutInflater.from(getApplicationContext());
                View v= inflater.inflate(R.layout.alertdialogcardviewsrevenu,null);
                builder1.setView(v);
                final AlertDialog dialog= builder1.create();
                final TextView tv1=v.findViewById(R.id.textView19);
                final TextView tv2=v.findViewById(R.id.textView21);
                final TextView tv3=v.findViewById(R.id.textView23);
                final TextView tv4=v.findViewById(R.id.textView25);
                final Button annuler=v.findViewById(R.id.button7);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Cursor c= db.MinIncomeDetails(Login.User);
                if(c.getCount()==0){
                    StyleableToast.makeText(getApplicationContext(), "Pas de données", Toast.LENGTH_LONG, R.style.NoData).show();
                }
                else{
                    while (c.moveToNext()){
                        tv1.setText(c.getString(1));
                        tv2.setText(c.getString(2));
                        tv3.setText(c.getString(3));
                        tv4.setText(c.getString(4));


                    }
                }
                dialog.show();
                annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


            }
        });
        cv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Panel.this);
                LayoutInflater inflater=LayoutInflater.from(getApplicationContext());
                View v= inflater.inflate(R.layout.alertdialogcardviewsrevenu,null);
                builder1.setView(v);
                final AlertDialog dialog= builder1.create();
                final TextView tv1=v.findViewById(R.id.textView19);
                final TextView tv2=v.findViewById(R.id.textView21);
                final TextView tv3=v.findViewById(R.id.textView23);
                final TextView tv4=v.findViewById(R.id.textView25);
                final Button annuler=v.findViewById(R.id.button7);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Cursor c= db.MaxIncomeDetails(Login.User);
                if(c.getCount()==0){
                    StyleableToast.makeText(getApplicationContext(), "Pas de données", Toast.LENGTH_LONG, R.style.NoData).show();
                }
                else{
                    while (c.moveToNext()){
                        tv1.setText(c.getString(1));
                        tv2.setText(c.getString(2));
                        tv3.setText(c.getString(3));
                        tv4.setText(c.getString(4));


                    }
                }
                dialog.show();
                annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Panel.this,ViewExpense.class);
                startActivity(intent);
            }
        });
        cv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Panel.this,ViewIncome.class);
                startActivity(intent);

            }
        });

//        cv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(Panel.this);
//                LayoutInflater inflater=LayoutInflater.from(getApplicationContext());
//                View v= inflater.inflate(R.layout.alert_dialog_cardviews,null);
//                builder1.setView(v);
//                final AlertDialog dialog= builder1.create();
//                final TextView tv1=v.findViewById(R.id.textView19);
//                final TextView tv2=v.findViewById(R.id.textView21);
//                final TextView tv3=v.findViewById(R.id.textView23);
//                final TextView tv4=v.findViewById(R.id.textView25);
//                arraylist=db.MaxExpenseDetails(Login.User);
//                ArrayAdapter ad = new ArrayAdapter(Panel.this, android.R.layout.simple_list_item_1,arraylist);
//
//
//
//                tv1.setText(ad.toString());
//                tv2.setText(ad.toString());
//                tv3.setText(ad.toString());




//
//            }
//        });





    }
//    public void GetData(){
//        arraylist=db.MaxExpenseDetails(Login.User);
//
//    }
}