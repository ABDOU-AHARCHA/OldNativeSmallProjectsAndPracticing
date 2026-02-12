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

public class Forgotpassword extends AppCompatActivity {
    TextView ins,date;
    EditText ed1,ed2,ed3,ed4;
    Button btn1;
    DataBaseSqlite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        ins=findViewById(R.id.textView2);
//        date=findViewById(R.id.textView3);
        btn1=findViewById(R.id.button2);
        ed1=findViewById(R.id.editTextTextPersonName3);
        ed2=findViewById(R.id.editTextTextPersonName4);
        ed3=findViewById(R.id.editTextTextPersonName5);
        ed4=findViewById(R.id.editTextTextPersonName6);
        db=new DataBaseSqlite(getApplicationContext(),"Gestion2",null,1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameView= ed1.getText().toString();
                String emailView= ed2.getText().toString();
//                String dateView= date.getText().toString();
                String passwordView= ed3.getText().toString();
                String ConfirmPassword= ed4.getText().toString();

                if(usernameView.matches("")){
                    ed1.setError("Ce champ est obligatoire");
                }
                else if(emailView.matches("")){
                    ed2.setError("Ce champ est obligatoire");

                }
                else if(passwordView.matches("")){
                    ed3.setError("Ce champ est obligatoire");

                }
                else if(ConfirmPassword.matches("")){
                    ed4.setError("Ce champ est obligatoire");

                }


                else{
                    boolean checkuser=db.CheckUserIfExist(usernameView);
                    boolean checkemail=db.CheckEmailIfExist(emailView);
                    if(passwordView.equals(ConfirmPassword)){
                        if(checkemail==true){
                            if(checkuser==true){
                                boolean UpdateUser=db.UpdateUserPassword(passwordView);
                                if(UpdateUser==true){

                                    StyleableToast.makeText(getApplicationContext(), "mot de passe réinitialisé avec succès", Toast.LENGTH_LONG, R.style.PasswordUpdate).show();
                                    Intent intent= new Intent(getApplicationContext(),MenuActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                                else {
                                    StyleableToast.makeText(getApplicationContext(), "échec de la réinitialisation", Toast.LENGTH_LONG, R.style.OopsUpdate).show();

                                }
                            }
                            if(checkuser==false){
                                StyleableToast.makeText(getApplicationContext(), "Ce nom d'utilisateur n'existe pas !", Toast.LENGTH_LONG, R.style.NotFound).show();


                            }
                        }
                        if(checkemail==false){
                            StyleableToast.makeText(getApplicationContext(), "cette Email n'existe pas !", Toast.LENGTH_LONG, R.style.NotFound).show();

                        }
                    }
                    else{
                        ed4.setError("le mot de passe ne correspond pas");
                    }


                }

            }
        });
        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);

            }
        });

    }
}