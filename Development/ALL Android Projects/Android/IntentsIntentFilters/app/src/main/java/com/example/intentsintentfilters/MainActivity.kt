package com.example.intentsintentfilters

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var button:Button?= null;
    var textView:TextView?= null;
    var textView2:String?= null;

//    var button1=null
//    var textview1=null



    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button= findViewById<Button>(R.id.button);
        textView= findViewById<TextView>(R.id.textView);




        button!!.setOnClickListener(){

            val theApp = "com.google.android.youtube"
            textView!!.text="We're here for you and your loved ones!"
//            textView!!.

//            textView= textView!!.freezesText=""
//            textView= textView!!.textDirection=""
//            textView= textView!!.textLocale=""
//            textView= textView!!.textColors=""
//            textView= textView!!.textScaleX=""

                Intent(Intent.ACTION_MAIN).also {
                it.`package`=theApp

            try {
                startActivity(it)
            } catch (ex : ActivityNotFoundException){
                ex.printStackTrace()
            }



            }

        }

//        button!!.setOnClickListener()

    }
}