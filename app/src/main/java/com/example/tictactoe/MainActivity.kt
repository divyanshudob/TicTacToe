package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       Computer.setOnClickListener{
           Intent(this,ComputerActivity::class.java).also{
                startActivity(it)
           }
       }
        Friend.setOnClickListener{
            Intent(this,FriendsActivity::class.java).also{
                startActivity(it)
            }
        }
        Room.setOnClickListener{
            Intent(this,RoomActivity::class.java).also{
                startActivity(it)
            }
        }
    }
}