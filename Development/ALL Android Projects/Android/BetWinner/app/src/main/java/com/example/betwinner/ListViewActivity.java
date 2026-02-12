package com.example.betwinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.betwinner.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

        ActivityMainBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;

    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_list_view);
        listView1 = findViewById(R.id.listview1);



                int[] imageList = {R.drawable.advise, R.drawable.common, R.drawable.what, R.drawable.guide, R.drawable.true2, R.drawable.win2,R.drawable.wrong,R.drawable.advise, R.drawable.what, R.drawable.common, R.drawable.guide, R.drawable.true2, R.drawable.win2, R.drawable.wrong};
//        int[] ingredientList = {R.string.pastaIngredients,R.string.pastaIngredients, R.string.pastaIngredients, R.string.pastaIngredients, R.string.pastaIngredients, R.string.pastaIngredients,R.string.pastaIngredients,R.string.pastaIngredients, R.string.maggiIngredients,R.string.cakeIngredients,R.string.pancakeIngredients,R.string.pizzaIngredients, R.string.burgerIngredients, R.string.friesIngredients};
        int[] descList = {R.string.pastaDesc, R.string.burgerDesc, R.string.cakeDesc, R.string.pancakeDesc, R.string.pastaDesc, R.string.pastaDesc,  R.string.pastaDesc,R.string.pastaDesc, R.string.maggieDesc, R.string.cakeDesc,R.string.pancakeDesc,R.string.pizzaDesc, R.string.burgerDesc, R.string.friesDesc};
        String[] nameList = {"TYPES OF BETS", "LIVE BETS", "SPORTS", "DEPOSIT/WITHDRAWAL", "Sports betting", "App Bonuses", "Social networks", "registration", "Site Services", "1xbet English", "Football Betting", "Moneyline","Live bets", "Slots"};
//        String[] timeList = {"30 mins", "30 mins", "30 mins", "30 mins", "30 mins", "30 mins", "30 mins", "30 mins","2 mins", "45 mins","10 mins", "60 mins", "45 mins", "30 mins"};
        for (int i = 0; i < imageList.length; i++){
            listData = new ListData(nameList[i],  descList[i], imageList[i]);
            dataArrayList.add(listData);
        }
        listAdapter = new ListAdapter(ListViewActivity.this, dataArrayList);
        listView1.setAdapter(listAdapter);
        listView1.setClickable(true);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListViewActivity.this, DetailedActivity.class);
                intent.putExtra("name", nameList[i]);
//                intent.putExtra("time", timeList[i]);
//                intent.putExtra("ingredients", ingredientList[i]);
                intent.putExtra("desc", descList[i]);
                intent.putExtra("image", imageList[i]);
                startActivity(intent);
            }
        });
    }


    NetworkchangeListener networkchangeListener =new NetworkchangeListener();


    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkchangeListener,filter);
        super.onStart();


    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkchangeListener);

        super.onStop();
    }
}