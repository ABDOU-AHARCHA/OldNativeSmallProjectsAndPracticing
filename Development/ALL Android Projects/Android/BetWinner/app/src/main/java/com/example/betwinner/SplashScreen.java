package com.example.betwinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.se.omapi.Session;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashScreen extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        String url= "https://api.npoint.io/0c3d66accd0afae31463";
        new MyAsyncTask().execute(url);
    }

    public void AlertDialoge(){
        AlertDialog.Builder b = new AlertDialog.Builder(SplashScreen.this);
        View ld = LayoutInflater.from(SplashScreen.this).inflate(R.layout.customdialog, null);
        b.setView(ld);

        AppCompatButton btnRetry = ld.findViewById(R.id.btn_okay);

        //show dialog
        AlertDialog d = b.create();
        d.show();
        d.setCancelable(false);


        d.getWindow().setGravity(Gravity.CENTER);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               if(Common.IsConnectedTOInternet(SplashScreen.this)==false) {
                   d.dismiss();
                   AlertDialoge();


               }else{

                   d.dismiss();

                   new Handler().postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                           startActivity(intent);
                           finish();
                       }
                   },3000);


               }
            }
        });
    }



    public class MyAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
                urlConnect.setConnectTimeout(700);
                String dataJsonAsStr = convertStreamToString(urlConnect.getInputStream());
                publishProgress(dataJsonAsStr);

                JSONObject json = new JSONObject();
                JSONObject Obj = json.getJSONObject("coord");
                String Link = Obj.getString("link");
                Boolean isActive = Obj.getBoolean("is_active");


            } catch (Exception ex) {
                // handle exceptions
            }
            return "";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            try {
                JSONObject json = new JSONObject(values[0]);
                JSONObject Obj = json.getJSONObject("coord");
                String Link = Obj.getString("link");
                Boolean isActive = Obj.getBoolean("is_active");

                if(isActive==true){
//                    Toast.makeText(SplashScreen.this,"link is"+isActive,Toast.LENGTH_SHORT).show();
                    if (Common.IsConnectedTOInternet(SplashScreen.this)==false) {
                        AlertDialoge();
                    }
                    else{
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                                intent.putExtra("Link",Link);
                                startActivity(intent);
                                finish();
                            }
                        },3000);

                    }

                }


//                Toast.makeText(SplashScreen.this,"link is"+isActive,Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                // handle exceptions
            }
        }
        public String convertStreamToString(InputStream inputStream) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            String allString = "";
            try {
                do {
                    line = bufferedReader.readLine();
                    if (line != null) {
                        allString += line;
                    }
                } while (line != null);
                bufferedReader.close();
            } catch (Exception ex) {
                // handle exceptions
            }
            return allString;
        }

    }


}