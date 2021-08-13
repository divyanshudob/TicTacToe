package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_friends.*

class FriendsActivity : AppCompatActivity() {
    var PLAYER_X = true
    var TURN_COUNT = 0
    var boardStatus = Array(3) { IntArray(3) }
    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        board = arrayOf(
            arrayOf(First, Second, Third),
            arrayOf(Fourth, Fifth, Sixth),
            arrayOf(Seventh, Eighth, Ninth)
        )

        for (i in board) {
            for (button in i) {
                button.setOnClickListener {

                    when (it.id) {
                        R.id.First -> {
                            updateValue(row = 0, col = 0, player = PLAYER_X)

                        }
                        R.id.Second -> {
                            updateValue(row = 0, col = 1, player = PLAYER_X)

                        }
                        R.id.Third -> {
                            updateValue(row = 0, col = 2, player = PLAYER_X)

                        }
                        R.id.Fourth -> {
                            updateValue(row = 1, col = 0, player = PLAYER_X)
                        }
                        R.id.Fifth -> {
                            updateValue(row = 1, col = 1, player = PLAYER_X)
                        }
                        R.id.Sixth -> {
                            updateValue(row = 1, col = 2, player = PLAYER_X)
                        }
                        R.id.Seventh -> {
                            updateValue(row = 2, col = 0, player = PLAYER_X)
                        }
                        R.id.Eighth -> {
                            updateValue(row = 2, col = 1, player = PLAYER_X)
                        }
                        R.id.Ninth -> {
                            updateValue(row = 2, col = 2, player = PLAYER_X)

                        }
                    }

                    TURN_COUNT++
                    PLAYER_X = !PLAYER_X
                    if (PLAYER_X) {
                        result("Player 1 turn");
                    } else {
                        result("Player 2 turn");
                    }
                    if (TURN_COUNT == 9) {
                        result("Game Draw");
                    }

                    checkWinner();
                }

            }
        }

        initializeBoardStatus()

        resetBtn.setOnClickListener {
            PLAYER_X = true
            TURN_COUNT = 0
            Status.text = "Player 1 turn"
            initializeBoardStatus()
        }

    }

    private fun checkWinner() {

        //Horizontal --- rows
        for (i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    result("Player 1 Won!!")
                    break
                } else if (boardStatus[i][0] == 0) {
                    result("Player 2 Won")
                    break
                }
            }
        }

        //Vertical --- columns
        for (i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    result("Player 1 Won!!")
                    break
                } else if (boardStatus[0][i] == 0) {
                    result("Player 2 Won!!")
                    break
                }
            }
        }



        //First diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                result("Player 1 Won!!")
            } else if (boardStatus[0][0] == 0) {
                result("Player 2 Won!!")
            }
        }

        //Second diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                result("Player 1 Won!!")
            } else if (boardStatus[0][2] == 0) {
                result("PPlayer 2 Won!!")
            }
        }
    }

    private fun result(result: String) {
        Status.text = result
        if (result.contains("Won")) {
            disableButton()
        } else {

        }

    }

    private fun disableButton() {
        for (i in board) {
            for (button in i) {
                button.isEnabled = false
            }
        }
    }


    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if (player) "X" else "0"
        val value = if (player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value
    }

    private fun initializeBoardStatus() {
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