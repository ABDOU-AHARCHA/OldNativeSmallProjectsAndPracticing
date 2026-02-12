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

public class Addincome extends AppCompatActivity {
    TextView date;
    Spinner s1;
    ArrayList<Categories> liste= new ArrayList<Categories>();
    MyAdapter adapter;
    EditText ed1,ed2,ed3;
    Button btn1;
    DataBaseSqlite db;
    FloatingActionButton acrionbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addincome);
        ed1=findViewById(R.id.editTextTextPersonName3);
        ed2=findViewById(R.id.editTextTextPersonName4);
        ed3=findViewById(R.id.editTextTextPersonName7);
        btn1=findViewById(R.id.button2);
        acrionbutton=findViewById(R.id.floatingActionButton);

        date=findViewById(R.id.lasttextview);
        s1=findViewById(R.id.spinner);
        db= new DataBaseSqlite(getApplicationContext(),"Gestion2",null,1);


//        String [] liste= new String[]{ "Salaire","Affaires","Assurance","Autre"};
//
//        ArrayAdapter a1= new ArrayAdapter(Addincome.this, android.R.layout.simple_list_item_1,liste);
//        s1.setAdapter(a1);
//        s1.setSelection(3);
        liste.add(new Categories("Salaire",R.drawable.salaire));
        liste.add(new Categories("Assurance",R.drawable.assurance));
        liste.add(new Categories("Affaires",R.drawable.business));
        liste.add(new Categories("Autre",R.drawable.other));

        adapter=new MyAdapter(getApplicationContext(),0);
        s1.setAdapter(adapter);
        s1.setSelection(3);

        Calendar c= Calendar.getInstance();
        int day=c.get(Calendar.DAY_OF_MONTH);
        int mounth = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        date.setText(day+"/"+mounth+"/"+year);
        acrionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Addincome.this,MenuActivity.class);
                startActivity(intent);
            }
        });


        adapter.addAll(liste);

        btn1.setOnClickListener(new View.OnClickListener() {
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
                        String NomIncome=ed1.getText().toString();
                        String AmountIncome=ed2.getText().toString();
                        float AmountIncomeInteger=Float.valueOf(AmountIncome);
                        String DescriptionIncome=ed3.getText().toString();
                        String categotyIncome=s1.getSelectedItem().toString();
//                String categotyIncom = (String) s1.getItemAtPosition(s1.getSelectedItemPosition());
                        String dateIncome=date.getText().toString();


                        boolean insertincome =db.AddIncome(NomIncome,AmountIncomeInteger,categotyIncome,dateIncome,DescriptionIncome,Login.User.toString());
                        if (insertincome == true) {
                            ed1.setText("");
                            ed2.setText("");
                            ed3.setText("");
                            StyleableToast.makeText(getApplicationContext(), "Ajouté avec succès", Toast.LENGTH_LONG, R.style.Added).show();

                        }
                        else{
                            StyleableToast.makeText(getApplicationContext(), "Quelque chose ne va pas", Toast.LENGTH_LONG, R.style.OopsUpdate).show();

                        }




                    }
                }catch (Exception ex){
                    StyleableToast.makeText(getApplicationContext(), "le montant doit être un nombre !", Toast.LENGTH_LONG, R.style.OopsUpdate).show();

                }



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
                DatePickerDialog dt= new DatePickerDialog(Addincome.this,datepicker,year,mounth,day);
                dt.show();
            }
        });
    }
}