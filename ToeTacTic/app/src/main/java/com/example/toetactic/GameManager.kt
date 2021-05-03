package com.example.toetactic

import com.example.toetactic.api.GameService
import com.example.toetactic.api.data.Game
import com.example.toetactic.api.data.GameState

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
}