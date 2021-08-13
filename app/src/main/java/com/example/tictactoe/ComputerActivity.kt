package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_computer.*
import kotlinx.coroutines.delay
import java.util.*
import java.util.concurrent.TimeUnit


class ComputerActivity : AppCompatActivity() {
    var player:Boolean = true
    var turnCount = 0
    var boardStatus = Array(3) { IntArray(3) }
    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_computer)
        board = arrayOf(
            arrayOf(First, Second, Third),
            arrayOf(Fourth, Fifth, Sixth),
            arrayOf(Seventh, Eighth, Ninth)
        )

        initializeButtons()
        changeBoard()
        resetBtn.setOnClickListener{

            turnCount = 0
            player = true
            Status.text = "Player's Turn"
            changeBoard()
        }
    }



    private fun initializeButtons(){

                        First.setOnClickListener{
                            updateBoardStatus(row = 0, column = 0)
                        }
                        Second.setOnClickListener(){
                            updateBoardStatus(row = 0, column = 1)
                        }
                        Third.setOnClickListener(){
                            updateBoardStatus(row = 0, column = 2)
                        }
                        Fourth.setOnClickListener{
                            updateBoardStatus(row = 1, column = 0)
                        }
                        Fifth.setOnClickListener{
                            updateBoardStatus(row = 1, column = 1)
                        }
                        Sixth.setOnClickListener{
                            updateBoardStatus(row = 1, column = 2)
                        }
                        Seventh.setOnClickListener{
                            updateBoardStatus(row = 2, column = 0)
                        }
                        Eighth.setOnClickListener(){
                            updateBoardStatus(row = 2, column = 1)
                        }
                       Ninth.setOnClickListener(){
                            updateBoardStatus(row = 2, column = 2)
                        }


    }

    private fun update_computer(){

           var row:Int = 0
           var column:Int = 0

            row = (0..2).random()
            column = (0..2).random()


            if(boardStatus[row][column]==0 || boardStatus[row][column]==1)
               update_computer()
            else
                updateBoardStatus(row, column)



    }

    private fun updateBoardStatus(row:Int, column:Int){
        val text = if (player) "X" else "0"
        val value = if (player) 1 else 0

        board[row][column].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][column] = value
        turnCount++
        player = !player
        if(turnCount == 9){
            turnCount = 0
            player = true
            Toast.makeText(this, "Game Draw!!", Toast.LENGTH_LONG).show()
            Status.text = "Player's Turn"
            changeBoard()
        }

        var win: Boolean = checkWinner()
        if(!win){
               if(player)
                   initializeButtons()
               else
                   update_computer()
        }
    }

    private fun checkWinner():Boolean{
        //Horizontal --- rows
        for (i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    result("Player Won!!")
                    return true
                    break
                } else if (boardStatus[i][0] == 0) {
                    result("Computer Won")
                    return true
                    break
                }
            }
        }

        //Vertical --- columns
        for (i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    result("Player Won!!")
                    return true
                    break
                } else if (boardStatus[0][i] == 0) {
                    result("Computer Won!!")
                    return true
                    break
                }
            }
        }



        //First diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                result("Player Won!!")
                return true
            } else if (boardStatus[0][0] == 0) {
                result("Computer Won!!")
                return true
            }
        }

        //Second diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                result("Player Won!!")
                return true
            } else if (boardStatus[0][2] == 0) {
                result("Computer Won!!")
                return true
            }
        }
        return false
    }

    private fun result(res:String){
        Status.text = res
        if(res.contains("Won")){
            disableButton()
        }
        else{

        }
    }

    private fun disableButton(){
        for(i in board){
            for(button in i){
                button.isEnabled = false
            }
        }
    }

    private fun changeBoard(){
        for (i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] = -1
            }
        }

        for (i in board) {
            for (button in i) {
                button.isEnabled = true
                button.text = ""

            }
        }
    }


}



