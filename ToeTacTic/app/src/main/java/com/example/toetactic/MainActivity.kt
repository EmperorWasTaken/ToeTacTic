package com.example.toetactic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.toetactic.GameManager.player
import com.example.toetactic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG:String = "MainActivity"

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startGameButton.setOnClickListener {
            createNewGame()
        }

        binding.joinGameButton.setOnClickListener {
            joinGame()
        }
    }

    private fun createNewGame(){

        val username = binding.username.text.toString()

        val intent = Intent(this, GameActivity::class.java)

        if(username.isNotEmpty()){
            GameManager.player = username
            GameManager.createGame(GameManager.player!!)
            startActivity(intent)


        } else {

            Toast.makeText(this, "Forgot your own name?", Toast.LENGTH_SHORT).show()

        }

        //GameManager.createGame(GameManager.player!!)
    }

    private fun joinGame(){

        val gameId = binding.joinToken.text.toString()

        if(gameId.isNotEmpty()){
            GameManager.joinGame(GameManager.player.toString(), gameId)
        } else {

            Toast.makeText(this, "You forgot the Game Token!", Toast.LENGTH_SHORT).show()

        }

        finish()


    }


}