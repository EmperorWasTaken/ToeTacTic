package com.example.toetactic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toetactic.databinding.ActivityGameBinding
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    private lateinit var binding:ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.player1.text = GameManager.player

        //binding.player2.text = GameManager.player

        b00.setOnClickListener{

        }
    }
}