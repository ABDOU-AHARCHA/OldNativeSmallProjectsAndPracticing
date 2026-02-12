package com.example.tictactoy

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var ButtonReplay:Button =this.findViewById(R.id.buttonReplay)

        ButtonReplay.setOnClickListener(){




        }

    }




    fun ButtonClick(view: View){
        val ButtonChoise=view as Button
        var CellID= 0
        when(ButtonChoise.id){
            R.id.button1 -> CellID=1
            R.id.button2 -> CellID=2
            R.id.button3 -> CellID=3
            R.id.button4 -> CellID=4
            R.id.button5 -> CellID=5
            R.id.button6 -> CellID=6
            R.id.button7 -> CellID=7
            R.id.button8 -> CellID=8
            R.id.button9 -> CellID=9


        }
        playGame(CellID,ButtonChoise)
    }


    var activeplayer = 1
    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()

    fun playGame(CellId:Int,ButtonNumber:Button){

        if(activeplayer == 1){
            ButtonNumber.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.MULTIPLY);
            ButtonNumber.text="X"
            player1.add(CellId)
            activeplayer =2




        }
        else{
            ButtonNumber.getBackground().setColorFilter(Color.MAGENTA, PorterDuff.Mode.MULTIPLY);
            ButtonNumber.text="O"
            player2.add(CellId)
            activeplayer = 1

        }

        ButtonNumber.isEnabled=false
        WinnerPlayer()
    }



    fun WinnerPlayer (){
         var winner = -1

        //line1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner =1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner =2
        }

        //line2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner =1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner =2
        }

        //line3

        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner =1
        }
        if(player2.contains(9) && player2.contains(8) && player2.contains(9)){
            winner =2
        }

        //line4
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner =1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner =2
        }

        //line5
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner =1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner =2
        }

        //line6
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner =1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner =2
        }


        //line7
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner =1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner =2
        }


        //line8
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner =1
        }
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner =2
        }


        if(winner!=-1){
            if(winner==1){
                Toast.makeText(this,"The Player 1 is the winner",Toast.LENGTH_LONG).show()


            }
            if (winner==2){
                Toast.makeText(this,"The Player 2 is the winner",Toast.LENGTH_LONG).show()

            }
        }
    }


    fun Autoplay(){
        var emptyCells = ArrayList<Int>()
        for(cellid in 0..9){
            if(!(player1.contains(cellid) || player2.contains(cellid))){
                emptyCells.add(cellid)
            }
        }

        val r= Random
        val RndIndex=r.nextInt(emptyCells.size)
        val CellId = emptyCells[RndIndex]
        var BuSelect:Button?
        when(CellId){
            1 -> BuSelect= R.id.button1 as Button

            2 -> BuSelect= R.id.button2 as Button
            3 -> BuSelect= R.id.button3 as Button
            4 -> BuSelect= R.id.button4 as Button
            5 -> BuSelect= R.id.button5 as Button
            6 -> BuSelect= R.id.button6 as Button
            7 -> BuSelect= R.id.button7 as Button
            8 -> BuSelect= R.id.button8 as Button
            9 -> BuSelect= R.id.button9 as Button
            else->{
                BuSelect= R.id.button1 as Button

            }

        }
        playGame(CellId,BuSelect)



    }




}