package com.example.toetactic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toetactic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GameManager.player = "Petter"
        GameManager.createGame(GameManager.player!!, GameManager.StartingGameState)
    }
}