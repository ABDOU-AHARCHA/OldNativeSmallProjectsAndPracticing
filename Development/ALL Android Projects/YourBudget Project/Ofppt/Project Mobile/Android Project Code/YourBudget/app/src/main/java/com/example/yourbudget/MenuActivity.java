package com.example.yourbudget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView Salut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu");

        Salut=findViewById(R.id.textView8);


        Salut.setText(Login.User.toString());



        drawerLayout=findViewById(R.id.drawerLayout);

        navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle(MenuActivity.this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.income:
                Intent intent = new Intent(getApplicationContext(),Addincome.class);
                startActivity(intent);
                break;
            case R.id.logout:
                Intent intent6 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent6);
                finish();
                break;
            case R.id.expense:
                Intent intent2 = new Intent(getApplicationContext(),Addexpense.class);
                startActivity(intent2);
                break;
            case R.id.Home:
                Intent intent1 = new Intent(getApplicationContext(),Panel.class);
                startActivity(intent1);
                break;
            case R.id.view:
                Intent intent3 = new Intent(getApplicationContext(),ViewIncome.class);
                startActivity(intent3);
                break;
            case R.id.view2:
                Intent intent4 = new Intent(getApplicationContext(),ViewExpense.class);
                startActivity(intent4);
                break;



        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }






}