package com.example.checksunset

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    var tv1:TextView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn1:Button = this.findViewById<Button>(R.id.button)
        tv1= this.findViewById<TextView>(R.id.textView)
        var ed1:EditText = this.findViewById<EditText>(R.id.editTextTextPersonName)


        btn1.setOnClickListener(){
            var city = ed1.text.toString()
            val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=cb96f0d033deb03547e4eff4a7e7d88f"
            MyAsncTask().execute(url)
        }
    }


// class for implement the AsyncTask Class's Methodes
    inner class MyAsncTask:AsyncTask<String,String,String>(){
        override fun doInBackground(vararg params: String?): String {
            try{
                var url =URL(params[0]) //Creates a URL object from the String representation.
                var urlConnect =url.openConnection() as HttpURLConnection
                urlConnect.connectTimeout = 700
                val dataJsonAsStr =ConvertStreamToString(urlConnect.inputStream)
                publishProgress(dataJsonAsStr)
            }catch (ex:Exception){}
            return ""
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            try{
                var json= JSONObject(values[0])
                val query=json.getJSONObject("wind")
                val SpeedWind=query.getString("speed")
                tv1!!.text = "speed wind is: "+ SpeedWind+"m/s"
            }catch (ex:Exception){}
        }
    }



    //Methode to convert InputStream to StringStream
    fun ConvertStreamToString(inputStream: InputStream):String{
        val bufferReader= BufferedReader(InputStreamReader(inputStream))
        var line:String
        var AllString:String=""
        try {
            do{
                line=bufferReader.readLine()
                if(line!=null){
                    AllString+=line
                }
            }while (line!=null)
            bufferReader.close()
        }catch (ex:Exception){}
        return AllString
    }


}