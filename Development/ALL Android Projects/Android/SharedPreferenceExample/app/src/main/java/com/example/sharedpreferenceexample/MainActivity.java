package com.example.sharedpreferenceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button ApplyTextButton;
    private Button SaveButton;
    private EditText ed1;
    private TextView tv1;
    private Switch Switch1;

    public static final String SHARED_PREFERENCE="SharedPrefs"; // The Name or The Key of SharedPrefences
    public static final String TEXT="text"; // The Key of SharedPreference (of the text)
    public static final String SWITCH="Switch1";// The Key of SharedPreference (of the boolean (Switch))

    private String text;
    private boolean switchOnOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = this.findViewById(R.id.textView);
        SaveButton = this.findViewById(R.id.btn2);
        ApplyTextButton = this.findViewById(R.id.Applybtn);
        ed1 = this.findViewById(R.id.ed1);
        Switch1 = this.findViewById(R.id.switch1);

        ApplyTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv1.setText(ed1.getText().toString());
            }
        });
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveData();
            }
        });
        loadData();
        updateViews();
    }

    public void SaveData() {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCE,MODE_PRIVATE);//Getting or Creation of "Shared Prefarences (File)"
        SharedPreferences.Editor editor =sharedPreferences.edit();// Modification de "sharedPreferences" qui nous creons (like we put this SharedPreference in this variable "editor" on which we can edit our preference)

        editor.putString(TEXT,tv1.getText().toString()); //the key that we gonna put in this editor (to save this text in the preference)
        editor.putBoolean(SWITCH,Switch1.isChecked()); //the key of the Boolean varaible and the value of this Boolean
        editor.apply();
        Toast.makeText(MainActivity.this,"Data is saved !",Toast.LENGTH_LONG);

    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE,MODE_PRIVATE);//getting the content of "Shared Prefarences"
        text = sharedPreferences.getString(TEXT,"No data is set"); //Retrieve a String value from the preferences.
        switchOnOff = sharedPreferences.getBoolean(SWITCH,false);//Retrieve a boolean value from the preferences.
    }
    public void updateViews(){
        tv1.setText(text);
        Switch1.setChecked(switchOnOff);

    }
}