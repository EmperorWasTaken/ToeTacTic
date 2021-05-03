package com.example.toetactic.api

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.toetactic.App
import com.example.toetactic.R
import com.example.toetactic.api.data.Game
import com.example.toetactic.api.data.GameState
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

typealias GameServiceCallback = (state: Game?, errorCode:Int? ) -> Unit

object GameService {

    private val context = App.context

    private val requestQue: RequestQueue = Volley.newRequestQueue(context)

    private enum class APIEndpoints(val url:String) {
        CREATE_GAME("%1s%2s%3s".format(context.getString(R.string.protocol), context.getString(R.string.domain),context.getString(R.string.base_path))),
        JOIN_GAME("%1s%2s%3s".format(context.getString(R.string.protocol), context.getString(R.string.domain),context.getString(R.string.base_path), context.getString(R.string.join_game_path))),
        UPDATE_GAME("%1s%2s%3s".format(context.getString(R.string.protocol), context.getString(R.string.domain),context.getString(R.string.base_path), context.getString(R.string.update_game_path))),
        POLL_GAME("%1s%2s%3s".format(context.getString(R.string.protocol), context.getString(R.string.domain),context.getString(R.string.base_path), context.getString(R.string.poll_game_path))),
    }


    fun createGame(playerId:String, state:GameState, callback:GameServiceCallback) {

        val url = APIEndpoints.CREATE_GAME.url

        val requestData = JSONObject()
        requestData.put("player", playerId)
        requestData.put("state",JSONArray(state))

        val request = object : JsonObjectRequest(
            Request.Method.POST,url, requestData,
            {
                val game = Gson().fromJson(it.toString(0), Game::class.java)
                callback(game,null)
            }, {
                callback(null, it.networkResponse.statusCode)
            } ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Game-Service-Key"] = context.getString(R.string.game_service_key)
                return headers
            }
        }

        requestQue.add(request)
    }

    fun joinGame(playerId:String, gameId:String, callback: GameServiceCallback){


        val url = APIEndpoints.JOIN_GAME.url

        val requestData = JSONObject()
        requestData.put("player", playerId)
        requestData.put("gameId", gameId)

        val request = object : JsonObjectRequest(
            Request.Method.POST,url, requestData,
            {
                val game = Gson().fromJson(it.toString(0), Game::class.java)
                callback(game,null)
            }, {
                callback(null, it.networkResponse.statusCode)
            } ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Game-Service-Key"] = context.getString(R.string.game_service_key)
                return headers
            }
        }

        requestQue.add(request)
    }

    fun updateGame(gameId: String, gameState:GameState, callback: GameServiceCallback){



        val url = APIEndpoints.UPDATE_GAME.url

        val requestData = JSONObject()
        requestData.put("gameId", gameId)
        requestData.put("state", gameState)

        val request = object : JsonObjectRequest(
            Request.Method.POST,url, requestData,
            {
                val game = Gson().fromJson(it.toString(0), Game::class.java)
                callback(game,null)
            }, {
                callback(null, it.networkResponse.statusCode)
            } ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Game-Service-Key"] = context.getString(R.string.game_service_key)
                return headers
            }
        }

        requestQue.add(request)
    }

    fun pollGame(gameId: String,callback:GameServiceCallback){

        val url = APIEndpoints.POLL_GAME.url

        val requestData = JSONObject()
        requestData.put("gameId", gameId)

        val request = object : JsonObjectRequest(
            Request.Method.POST,url, requestData,
            {
                val game = Gson().fromJson(it.toString(0), Game::class.java)
                callback(game,null)
            }, {
                callback(null, it.networkResponse.statusCode)
            } ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Game-Service-Key"] = context.getString(R.string.game_service_key)
                return headers
            }
        }

        requestQue.add(request)

    }

}