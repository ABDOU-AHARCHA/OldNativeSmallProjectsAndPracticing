package com.example.readjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    TextView tv1, tv2;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1= this.findViewById(R.id.textView);
        tv2= this.findViewById(R.id.textView2);
        btn1= this.findViewById(R.id.button);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url= "https://api.npoint.io/0c3d66accd0afae31463";
                new MyAsyncTask().execute(url);
            }
        });
    }

    class MyAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
                urlConnect.setConnectTimeout(700);
                String dataJsonAsStr = convertStreamToString(urlConnect.getInputStream());
                publishProgress(dataJsonAsStr);
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
                JSONObject link = json.getJSONObject("coord");
                String SpeedWind = link.getString("link");
                Boolean isactive = link.getBoolean("is_active");
                tv1.setText("active is: " + isactive );
                tv2.setText("link is: " + SpeedWind );

                if(isactive==true){
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);

                }
            } catch (Exception ex) {
                // handle exceptions
            }
        }

        // method to convert input stream to string
//        private String convertStreamToString(InputStream inputStream) throws IOException {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            StringBuilder stringBuilder = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//            reader.close();
//            return stringBuilder.toString();
//        }
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