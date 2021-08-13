package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_computer.*
import java.util.*


class ComputerActivity : AppCompatActivity() {
    var player = true
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
        disableButton()
        Toast.makeText(this,"Press 'Start' to start the game",Toast.LENGTH_SHORT).show()
        start.setOnClickListener{
            enableButton()
            playGame()
        }
        resetBtn.setOnClickListener{
            player = true;
            turnCount = 0
            changeBoard()
        }
    }

    private fun playGame(){
        Status.text = "Player's Turn"
        for (i in 0..2) {
            var flag: Boolean = false
            for (j in 0..2) {
                if (player) {
                    Status.text = "Player's Turn"
                    update_player(player)
                    player = false
                    turnCount++
                    checkWinner()
                    if (turnCount == 9) {
                        Status.text = "Game Draw"
                        flag = true;
                    }

                } else {
                    Status.text = "Computer's Turn"
                    update_computer(player)
                    player = true
                    turnCount++
                    checkWinner()
                    if (turnCount == 9){
                        Status.text = "Game Draw"
                        flag = true
                    }

                }

            }
            if(flag)
                break
        }
        changeBoard()

    }

    private fun update_player(player:Boolean){

        for(i in board){
            for(button in i){
                button.setOnClickListener{
                    when(it.id){
                        R.id.First->{
                            updateBoardStatus(row = 0, column = 0,player)
                        }
                        R.id.Second->{
                            updateBoardStatus(row = 0, column = 1,player)
                        }
                        R.id.Third->{
                            updateBoardStatus(row = 0, column = 2,player)
                        }
                        R.id.Fourth->{
                            updateBoardStatus(row = 1, column = 0,player)
                        }
                        R.id.Fifth->{
                            updateBoardStatus(row = 1, column = 1,player)
                        }
                        R.id.Sixth->{
                            updateBoardStatus(row = 1, column = 2,player)
                        }
                        R.id.Seventh->{
                            updateBoardStatus(row = 2, column = 0,player)
                        }
                        R.id.Eighth->{
                            updateBoardStatus(row = 2, column = 1,player)
                        }
                        R.id.Ninth->{
                            updateBoardStatus(row = 2, column = 2,player)
                        }
                    }
                }
            }
        }

    }

    private fun update_computer(player:Boolean){

           var row:Int = 0
           var column:Int = 0

            row = (0..2).random()
            column = (0..2).random()


            if(boardStatus[row][column]==1)
               update_computer(player)
            else
                updateBoardStatus(row, column, player)



    }

    private fun updateBoardStatus(row:Int, column:Int, player:Boolean){
        val text = if (player) "X" else "0"
        val value = if (player) 1 else 0
        board[row][column].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][column] = value
    }

    private fun checkWinner(){
        //Horizontal --- rows
        for (i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    result("Player Won!!")
                    break
                } else if (boardStatus[i][0] == 0) {
                    result("Computer Won")
                    break
                }
            }
        }

        //Vertical --- columns
        for (i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    result("Player Won!!")
                    break
                } else if (boardStatus[0][i] == 0) {
                    result("Computer Won!!")
                    break
                }
            }
        }



        //First diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                result("Player Won!!")
            } else if (boardStatus[0][0] == 0) {
                result("Computer won!!")
            }
        }

        //Second diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                result("Player Won!!")
            } else if (boardStatus[0][2] == 0) {
                result("Computer Won!!")
            }
        }
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

    private fun enableButton(){
        for(i in board){
            for(button in i){
                button.isEnabled = true
            }
        }
    }
}



