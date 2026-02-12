package com.example.yourbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import io.github.muddz.styleabletoast.StyleableToast;

public class Addexpense extends AppCompatActivity {
    TextView date;
    Spinner s1;
    MyAdapter adapter;
    ArrayList<Categories> Liste=new ArrayList<Categories>();
    Button btn2;
    EditText ed1,ed2,ed3;
    DataBaseSqlite db;
    FloatingActionButton acrionbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense);

        date= findViewById(R.id.textView4);
        acrionbutton=findViewById(R.id.floatingActionButton);

        s1=findViewById(R.id.spinner);

//        String [] liste= new String[]{ "aliments","achats","médical","Ménage","Voyage","Véhicule","Autre"};
//
//        ArrayAdapter a1= new ArrayAdapter(Addexpense.this, android.R.layout.simple_list_item_1,liste);
//        s1.setAdapter(a1);
//        s1.setSelection(6);
        Liste.add(new Categories("aliments",R.drawable.food));
        Liste.add(new Categories("achats",R.drawable.purchases));
        Liste.add(new Categories("médical",R.drawable.medical));
        Liste.add(new Categories("Ménage",R.drawable.housework));
        Liste.add(new Categories("Voyage",R.drawable.travel));
        Liste.add(new Categories("Véhicule",R.drawable.transport));
        Liste.add(new Categories("Autre",R.drawable.other));

        db=new DataBaseSqlite(getApplicationContext(),"Gestion2",null,1);

        adapter=new MyAdapter(getApplicationContext(),0);
        s1.setAdapter(adapter);
        s1.setSelection(6);
//
        adapter.addAll(Liste);
        ed1=findViewById(R.id.editTextTextPersonName3);
        ed2=findViewById(R.id.editTextTextPersonName4);
        ed3=findViewById(R.id.editTextTextPersonName7);

        btn2=findViewById(R.id.button2);


        Calendar c= Calendar.getInstance();
        int day=c.get(Calendar.DAY_OF_MONTH);
        int mounth = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        date.setText(day+"/"+mounth+"/"+year);

        acrionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Addexpense.this,MenuActivity.class);
                startActivity(intent);
            }
        });





        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c= Calendar.getInstance();
                int day=c.get(Calendar.DAY_OF_MONTH);
                int mounth = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);


                DatePickerDialog.OnDateSetListener datepicker = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date.setText(i2+"/"+(i1+1)+"/"+i);

                    }
                };
                DatePickerDialog dt= new DatePickerDialog(Addexpense.this,datepicker,year,mounth,day);
                dt.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    if(ed1.getText().toString().matches("")){
                        ed1.setError("Ce champ est obligatoire");

                    }
                    else if(ed2.getText().toString().matches("")){
                        ed2.setError("Ce champ est obligatoire");
                    }
                    else if(date.getText().toString().matches("")){
                        date.setError("Ce champ est obligatoire");
                    }
                    else if(ed3.getText().toString().matches("")){
                        ed3.setError("Ce champ est obligatoire");
                    }
                    else{
                        String NomExpense=ed1.getText().toString();
                        String AmountExpense=ed2.getText().toString();
                        float AmountExpenseInteger=Float.valueOf(AmountExpense);
                        String DescriptionExpense=ed3.getText().toString();
                        String categotyExpense=s1.getSelectedItem().toString();
//              String categotyExpense = (String) s1.getItemAtPosition(s1.getSelectedItemPosition());
                        String dateExpense=date.getText().toString();
                        db.AddExpense(NomExpense,AmountExpenseInteger,categotyExpense,dateExpense,DescriptionExpense,Login.User.toString());
                        ed1.setText("");
                        ed2.setText("");
                        ed3.setText("");

                        StyleableToast.makeText(getApplicationContext(), "Ajouté avec succès", Toast.LENGTH_LONG, R.style.Added).show();




                    }



                }catch (Exception ex){
                    StyleableToast.makeText(getApplicationContext(), "le montant doit être un nombre !", Toast.LENGTH_LONG, R.style.OopsUpdate).show();
                }



            }
        });
    //        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String NomExpense=ed1.getText().toString();
//                int AmountExpense=Integer.valueOf(ed2.getText().toString());
//                String DescriptionExpense=ed3.getText().toString();
//                String categotyExpense=s1.getSelectedItem().toString();
//                String dateExpense=date.getText().toString();
//                if(NomExpense.matches("")|| DescriptionExpense.matches("")){
//                    ed1.setError("Remplir cette champ");
//                    ed2.setError("Remplir cette champ");
//                    ed3.setError("Remplir cette champ");
//                }
//                else{
//
//
//                    db.AddExpense(NomExpense,AmountExpense,categotyExpense,dateExpense,DescriptionExpense,Login.User.toString());
//                    Toast.makeText(getApplicationContext(), "Added successflly", Toast.LENGTH_SHORT).show();
//
//
//                    ed1.setText("");
//                    ed2.setText("");
//                    ed3.setText("");
//                }
//
//            }
//        });
    }



}