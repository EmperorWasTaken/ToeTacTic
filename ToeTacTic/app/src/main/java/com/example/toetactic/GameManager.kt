package com.example.toetactic

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.example.toetactic.api.GameService
import com.example.toetactic.api.data.Game
import com.example.toetactic.api.data.GameState

object GameManager {

    var TAG = "GameManager"

    //lateinit var thisGame: Game
    var thisGame: Game? = null
    var player:String? = null
    var state:GameState? = null
    var gameId:String? = null

    var onGame:((Game) -> Unit)? = null

    val PreGameState = mutableListOf(mutableListOf(0,0,0),mutableListOf(0,0,0),mutableListOf(0,0,0))

    fun createGame(player:String) =
        GameService.createGame(player,PreGameState) { game: Game?, err: Int? ->
            if(err != null){
                 println(err)
            } else {
                if (game != null) {
                    println("You created a game with token: ${game.gameId}")

                    thisGame = game

                    Log.println(Log.ERROR, TAG, "$thisGame")

                }
            }
        }

    fun joinGame(player:String, gameId:String){
        GameService.joinGame(player, gameId) { game: Game?, err: Int? ->
            if(err == 406){
                println(err)
            } else {
                if (game != null){
                    println("Joined game with token: ${game.gameId}")

                    thisGame = game

                }
            }
        }
    }

    fun updateGame(gameId:String, gameState: GameState){
        GameService.updateGame(gameId, gameState) { game: Game?, err: Int? ->
            if(err == 406){
                println(err)
            } else {
                if (game != null){
                    println("Updated game with token: ${game.gameId}")
                    thisGame = game
                }
            }

        }
    }

    fun pollGame(gameId:String){
        GameService.pollGame(gameId){ game: Game?, err: Int? ->
            if(err == 406){
                println(err)
            } else {
                if (game != null){
                    println("Polled game with token: ${game.gameId}")
                    thisGame = game
                    updateThisGame()
                }
            }
        }
    }

    fun makeMove(sign: Int, row: Int, button: Int){
        thisGame?.state?.get(row)?.set(button, sign)
        thisGame?.let { updateGame(it.gameId, thisGame!!.state) }
    }

    fun updateThisGame(){

        thisGame?.let { onGame?.invoke(it) }

    }

}