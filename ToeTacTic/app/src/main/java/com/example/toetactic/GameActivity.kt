package com.example.toetactic

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.toetactic.GameManager.gameId
import com.example.toetactic.GameManager.makeMove
import com.example.toetactic.databinding.ActivityGameBinding
import kotlinx.android.synthetic.main.activity_game.*
import java.util.stream.DoubleStream.builder
import java.util.stream.IntStream.builder
import java.util.stream.LongStream.builder

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    private var TAG = "GameActivity"


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "${GameManager.thisGame}")

        Log.println(Log.DEBUG,TAG,"${GameManager.thisGame}")

        val gameGrid = listOf(
            b00, b01, b02,
            b10, b11, b12,
            b20, b21, b22
        )

        GameManager.onGame = {

            makeGrid(gameGrid)
            resultChecker()

        }



        if(GameManager.thisGame != null){
            binding.player1.text = "${GameManager.thisGame!!.players[0]}"


            if(GameManager.thisGame!!.players.size > 1){
                binding.player2.text = "${GameManager.thisGame!!.players[1]}"
            }



            binding.gameId.text = "Game ID: ${GameManager.thisGame!!.gameId}"
        }

        b00.setOnClickListener {

            if(binding.b00.text == ""){
                binding.b00.text = checkSpot()
                moveSpot(0,0)
            } else {
                Toast.makeText(this, "Spot Taken", Toast.LENGTH_SHORT).show()
            }



        }
        b01.setOnClickListener {

            if(binding.b01.text == ""){
                binding.b01.text = checkSpot()
                moveSpot(0,1)
            } else {
                Toast.makeText(this, "Spot Taken", Toast.LENGTH_SHORT).show()
            }

        }
        b02.setOnClickListener {

            if(binding.b02.text == ""){
                binding.b02.text = checkSpot()
                moveSpot(0,2)
            } else {
                Toast.makeText(this, "Spot Taken", Toast.LENGTH_SHORT).show()
            }

        }
        b10.setOnClickListener {

            if(binding.b10.text == ""){
                binding.b10.text = checkSpot()
                moveSpot(1,0)
            } else {
                Toast.makeText(this, "Spot Taken", Toast.LENGTH_SHORT).show()
            }

        }
        b11.setOnClickListener {

            if(binding.b11.text == ""){
                binding.b11.text = checkSpot()
                moveSpot(1,1)
            } else {
                Toast.makeText(this, "Spot Taken", Toast.LENGTH_SHORT).show()
            }

        }
        b12.setOnClickListener {

            if(binding.b12.text == ""){
                binding.b12.text = checkSpot()
                moveSpot(1,2)
            } else {
                Toast.makeText(this, "Spot Taken", Toast.LENGTH_SHORT).show()
            }

        }
        b20.setOnClickListener {

            if(binding.b20.text == ""){
                binding.b20.text = checkSpot()
                moveSpot(2,0)
            } else {
                Toast.makeText(this, "Spot Taken", Toast.LENGTH_SHORT).show()
            }

        }
        b21.setOnClickListener {

            if(binding.b21.text == ""){
                binding.b21.text = checkSpot()
                moveSpot(2,1)
            } else {
                Toast.makeText(this, "Spot Taken", Toast.LENGTH_SHORT).show()
            }

        }
        b22.setOnClickListener {

            if(binding.b22.text == ""){
                binding.b22.text = checkSpot()
                moveSpot(2,2)
                GameManager.updateGame(GameManager.thisGame!!.gameId, GameManager.thisGame!!.state)
            } else {
                Toast.makeText(this, "Spot Taken", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun checkSpot(): String {

        val sign: String

        if(GameManager.thisGame!!.players[0] == GameManager.player){
            sign = "X"
        } else {
            sign = "O"
        }
        return sign

    }

    private fun moveSpot(row: Int, button: Int){

        val player: Int

        if(GameManager.thisGame!!.players[0] == GameManager.player){
            player = 1
        } else {
            player = 2
        }
        GameManager.makeMove(player, row, button)

    }

    private fun makeGrid(gameGrid: List<Button>){
        val ints = listOf(0,1,2,3,4,5,6,7,8)
        val states: List<Int> = listOf(
            GameManager.thisGame!!.state[0][0],
            GameManager.thisGame!!.state[0][1],
            GameManager.thisGame!!.state[0][2],
            GameManager.thisGame!!.state[1][0],
            GameManager.thisGame!!.state[1][1],
            GameManager.thisGame!!.state[1][2],
            GameManager.thisGame!!.state[2][0],
            GameManager.thisGame!!.state[2][1],
            GameManager.thisGame!!.state[2][2]
        )

        for ( i in ints){


            updateGameGrid(states[i], gameGrid[i])

        }
    }

    private fun updateGameGrid(state : Int, gridButton: Button){
        when (state){
            0 -> gridButton.text = ""
            1 -> gridButton.text = "X"
            2 -> gridButton.text = "O"

            else -> {
                Log.d(TAG, "Something is wrong")
            }
        }
    }

    fun resultChecker(){

        if(GameManager.thisGame!!.players.size < 2){

            return

        }


        if(GameManager.thisGame!!.state [0][0] == 1 && GameManager.thisGame!!.state [0][1] == 1 && GameManager.thisGame!!.state [0][2] == 1){

            playEndingSequence(this, "${GameManager.thisGame!!.players[0]} wins!")

        }
        if(GameManager.thisGame!!.state [0][0] == 2 && GameManager.thisGame!!.state [0][1] == 2 && GameManager.thisGame!!.state [0][2] == 2){

            playEndingSequence(this, "${GameManager.thisGame!!.players[1]} wins!")
        }
        if (GameManager.thisGame!!.state [1][0] == 1 && GameManager.thisGame!!.state [1][1] == 1 && GameManager.thisGame!!.state [1][2] == 1) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[0]} wins!")

        }
        if (GameManager.thisGame!!.state [1][0] == 2 && GameManager.thisGame!!.state [1][1] == 2 && GameManager.thisGame!!.state [1][2] == 2) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[1]} wins!")

        }
        if (GameManager.thisGame!!.state [2][0] == 1 && GameManager.thisGame!!.state [2][1] == 1 && GameManager.thisGame!!.state [2][2] == 1) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[0]} wins!")

        }
        if (GameManager.thisGame!!.state [2][0] == 2 && GameManager.thisGame!!.state [2][1] == 2 && GameManager.thisGame!!.state [2][2] == 2) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[1]} wins!")

        }
        if (GameManager.thisGame!!.state [0][0] == 1 && GameManager.thisGame!!.state [1][0] == 1 && GameManager.thisGame!!.state [2][0] == 1) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[0]} wins!")

        }
        if (GameManager.thisGame!!.state [0][0] == 2 && GameManager.thisGame!!.state [1][0] == 2 && GameManager.thisGame!!.state [2][0] == 2) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[1]} wins!")

        }
        if (GameManager.thisGame!!.state [0][1] == 1 && GameManager.thisGame!!.state [1][1] == 1 && GameManager.thisGame!!.state [2][1] == 1) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[0]} wins!")

        }
        if (GameManager.thisGame!!.state [0][1] == 2 && GameManager.thisGame!!.state [1][1] == 2 && GameManager.thisGame!!.state [2][1] == 2) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[1]} wins!")

        }
        if (GameManager.thisGame!!.state [0][2] == 1 && GameManager.thisGame!!.state [1][2] == 1 && GameManager.thisGame!!.state [2][2] == 1) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[0]} wins!")

        }
        if (GameManager.thisGame!!.state [0][2] == 2 && GameManager.thisGame!!.state [1][2] == 2 && GameManager.thisGame!!.state [2][2] == 2) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[1]} wins!")

        }
        if (GameManager.thisGame!!.state [0][0] == 1 && GameManager.thisGame!!.state [1][1] == 1 && GameManager.thisGame!!.state [2][2] == 1) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[0]} wins!")

        }
        if (GameManager.thisGame!!.state [0][0] == 2 && GameManager.thisGame!!.state [1][1] == 2 && GameManager.thisGame!!.state [2][2] == 2) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[1]} wins!")

        }
        if (GameManager.thisGame!!.state [2][0] == 1 && GameManager.thisGame!!.state [1][1] == 1 && GameManager.thisGame!!.state [0][2] == 1) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[0]} wins!")

        }
        if (GameManager.thisGame!!.state [2][0] == 2 && GameManager.thisGame!!.state [1][1] == 2 && GameManager.thisGame!!.state [0][2] == 2) {

            playEndingSequence(this, "${GameManager.thisGame!!.players[1]} wins!")

        }

    }

    override fun onResume(){
        super.onResume()
        Toast.makeText(this, "Welcome back Chief!", Toast.LENGTH_SHORT).show()

    }

    override fun onPause(){
        super.onPause()

    }

    private fun playEndingSequence(parentActivity: AppCompatActivity, message: String): Unit {
        val endGameTextView = parentActivity.findViewById<TextView>(R.id.result)
        endGameTextView.text = message

        val translateAnimation = TranslateAnimation(0f, 0f, 0f, -350f)
        translateAnimation.duration = 1500

        endGameTextView.startAnimation(translateAnimation)

        endGameTextView.postOnAnimationDelayed( {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 8000)
    }


}