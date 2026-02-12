package com.example.yourbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.github.muddz.styleabletoast.StyleableToast;

public class Login extends AppCompatActivity {
    Button Inscrie;
    Button forgot;
    Button connexion;
    EditText ed1,ed2;
    DataBaseSqlite db;
    public static String User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Inscrie = findViewById(R.id.button2);
        forgot = findViewById(R.id.button3);
        connexion = findViewById(R.id.button);
        ed1=findViewById(R.id.editTextTextPersonName);
        ed2=findViewById(R.id.editTextTextPersonName2);
        db= new DataBaseSqlite(getApplicationContext(),"Gestion2",null,1);

        Inscrie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);

            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Forgotpassword.class);
                startActivity(intent);

            }
        });
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameView=ed1.getText().toString();
                String passwordView=ed2.getText().toString();
                if(usernameView.matches("")){
                    ed1.setError("Ce champ est obligatoire");

                }
                else if(passwordView.matches("")){
                    ed2.setError("Ce champ est obligatoire");

                }
                else{
                    boolean checkusernamepassword=db.CheckUserNameAndPassword(usernameView,passwordView);
                    if(checkusernamepassword==true){
                        User = usernameView;

                        StyleableToast.makeText(getApplicationContext(), "connectez-vous avec succès", Toast.LENGTH_LONG, R.style.LoginSuccess).show();
                        Intent intent= new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{

                        StyleableToast.makeText(getApplicationContext(), "Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_LONG, R.style.PasswordError).show();
                    }
                }

            }
        });
    }
}