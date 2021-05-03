package com.example.toetactic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GameManager.player = "Petter"
        GameManager.createGame(GameManager.player!!, GameManager.StartingGameState)
    }
}