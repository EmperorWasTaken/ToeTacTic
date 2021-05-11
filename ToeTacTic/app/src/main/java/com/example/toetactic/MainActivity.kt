package com.example.toetactic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.example.toetactic.api.GameService
import com.example.toetactic.api.data.Game
import com.example.toetactic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG:String = "MainActivity"

    lateinit var binding: ActivityMainBinding
    var pollTime = 5000L
    var timeTicks = 1000L
    lateinit var timer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(GameManager.thisGame != null){
            println("Any values?${GameManager.thisGame!!}")
            Log.d(TAG, "${GameManager.thisGame!!}")
        }

        binding.startGameButton.setOnClickListener {
            createNewGame()
        }

        binding.joinGameButton.setOnClickListener {
            joinGame()

        }
        pollTimer()
    }

    private fun createNewGame(){

        val username = binding.username.text.toString()



        if(username.isNotEmpty()){
            GameManager.player = username

            GameService.createGame(username, GameManager.PreGameState) { game: Game?, err: Int? ->
                if(err != null){
                    println(err)
                } else {
                    if (game != null) {
                        println("You created a game with token: ${game.gameId}")

                        GameManager.thisGame = game

                        val intent = Intent(this, GameActivity::class.java)
                        startActivity(intent)

                        Log.println(Log.ERROR, TAG, "${GameManager.thisGame}")

                    }
                }
            }




        } else {

            Toast.makeText(this, "Forgot your own name?", Toast.LENGTH_SHORT).show()

        }

    }

    private fun joinGame(){

        val username = binding.username.text.toString()

        val gameId = binding.joinToken.text.toString()



        if(gameId.isNotEmpty()){
            GameManager.player = username
            GameService.joinGame(username, gameId) { game: Game?, err: Int? ->
                if(err == 406){
                    println(err)
                } else {
                    if (game != null){
                        println("Joined game with token: ${game.gameId}")

                        GameManager.thisGame = game

                        val intent = Intent(this, GameActivity::class.java)
                        startActivity(intent)
                    }
                }
            }

        } else {

            Toast.makeText(this, "You forgot the Game Token!", Toast.LENGTH_SHORT).show()

        }


    }

    fun pollTimer(){
        timer = object : CountDownTimer(pollTime, timeTicks) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if(GameManager.thisGame != null){
                GameManager.pollGame(GameManager.thisGame!!.gameId)
                GameManager.updateThisGame()

                }
                pollTimer()

            }
        }
        timer.start()
    }

    override fun onResume(){
        super.onResume()
        Toast.makeText(this, "Welcome back Chief!", Toast.LENGTH_LONG).show()

    }

    override fun onPause(){
        super.onPause()


    }


}