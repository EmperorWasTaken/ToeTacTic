package com.example.toetactic.dialogs

interface GameDialogListener {
    fun onDialogCreateGame(player:String)
    fun onDialogJoinGame(player: String, gameId:String)
}