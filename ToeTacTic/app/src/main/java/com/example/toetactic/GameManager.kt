package com.example.toetactic

import com.example.toetactic.api.GameService
import com.example.toetactic.api.data.Game
import com.example.toetactic.api.data.GameState
import java.lang.System.err

object GameManager {

    var player:String? = null
    var state:GameState? = null

    val StartingGameState:GameState = listOf(listOf(0,0,0),listOf(0,0,0),listOf(0,0,0))

    fun createGame(player:String) =
        GameService.createGame(player,StartingGameState) { game: Game?, err: Int? ->
            if(err != null){
                 println(err)
            } else {
                if (game != null) {
                    println("Players ${game.gameId}")
                }
            }
        }

    fun joinGame(player:String, gameId:String){
        GameService.joinGame(player, gameId) { game: Game?, err: Int? ->
            if(err == 406){
                println(err)
            } else {
                if (game != null){
                    println("Joined game with ID: ${game.gameId}")
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
                    println("Updated game with ID: ${game.gameId}")
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
                    println("Polled game with ID: ${game.gameId}")
                }
            }
        }
    }
}