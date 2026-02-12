package com.example.yourbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import io.github.muddz.styleabletoast.StyleableToast;

public class Register extends AppCompatActivity {
    Button Ins1;
    TextView datenaissanse;
    TextView ins2;
    EditText ed1,ed2,ed3,ed4;
    DataBaseSqlite db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Ins1 = findViewById(R.id.button2);
        datenaissanse = findViewById(R.id.textView3);
        ins2 = findViewById(R.id.textView2);
        
        ed1=findViewById(R.id.editTextTextPersonName3);
        ed2=findViewById(R.id.editTextTextPersonName4);
        ed3=findViewById(R.id.editTextTextPersonName6);
        ed4=findViewById(R.id.editTextTextPersonName7);
        db =new DataBaseSqlite(getApplicationContext(),"Gestion2",null,1);

        Calendar c= Calendar.getInstance();
        int day=c.get(Calendar.DAY_OF_MONTH);
        int mounth = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        datenaissanse.setText(day+"/"+mounth+"/"+year);
        
        Ins1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameView= ed1.getText().toString();
                String emailView= ed2.getText().toString();
                String dateView= datenaissanse.getText().toString();
                String passwordView= ed3.getText().toString();
                String adressView= ed4.getText().toString();
                
                if(usernameView.matches("")){
                    ed1.setError("Entrer Nom d'utilusateur");
                }
                else if(emailView.matches("")){
                    ed2.setError("Entrer l'email");

                }
                else if(dateView.matches("")){
                    datenaissanse.setError("Entrer la date de naissance");

                }
                else if(passwordView.matches("")){
                    ed3.setError("Entrer le mot de passe");

                }
                else if(adressView.matches("")){
                    ed4.setError("Entrer votre adresse");

                }
                else{
                    boolean checkuser=db.CheckUserIfExist(usernameView);
                    boolean checkemail=db.CheckEmailIfExist(emailView);
                    if(checkemail==false){
                        if(checkuser==false){
                            boolean insertRegister=db.RegisterUser(usernameView,emailView,dateView,passwordView,adressView);
                            if(insertRegister==true){

                                StyleableToast.makeText(getApplicationContext(), "enregistrez-vous avec succès", Toast.LENGTH_LONG, R.style.LoginSuccess).show();
                                Intent intent= new Intent(Register.this,Login.class);
                                startActivity(intent);
                                finish();

                            }
                            else {

                                StyleableToast.makeText(getApplicationContext(), "Quelque chose ne va pas", Toast.LENGTH_LONG, R.style.OopsUpdate).show();
                            }
                        }
                        if(checkuser==true){

                            StyleableToast.makeText(getApplicationContext(), "Ce nom d'utilisateur existe déjà", Toast.LENGTH_LONG, R.style.OopsUpdate).show();

                        }
                    }
                    if(checkemail==true){

                        StyleableToast.makeText(getApplicationContext(), "cette Email déjà existé", Toast.LENGTH_LONG, R.style.OopsUpdate).show();
                    }

                }
                
            }
        });
        
        
        
        
        
        
        ins2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }

        });
        datenaissanse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c= Calendar.getInstance();
                int day=c.get(Calendar.DAY_OF_MONTH);
                int mounth = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);


                DatePickerDialog.OnDateSetListener datepicker = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        datenaissanse.setText(i2+"/"+(i1+1)+"/"+i);

                    }
                };
                DatePickerDialog dt= new DatePickerDialog(Register.this,datepicker,year,mounth,day);
                dt.show();
            }
        });
        ins2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);

            }
        });
    }
}