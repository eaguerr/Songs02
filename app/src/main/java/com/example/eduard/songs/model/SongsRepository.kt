package com.example.eduard.songs.model

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


object SongsRepository {

    private lateinit var songs: List<Song>

    private val TAG = "SongRepository"

    fun loadSongs(context: Context) {
        val gson = Gson()
        val json = loadJSONFromAsset("songs.json", context)
        val listType = object : TypeToken<List<Song>>() {}.type
        songs = gson.fromJson(json, listType)
        songs
                .filter { FavoriteScreen.isMarkedFavorite(it) }
                .forEach { it.isMarkedFavorite = true }
        songs
    }

    fun getSongs() = songs.sortedBy { it.name }

    fun getSongsForGenre(genre: String) = songs.filter { it.category == genre }.sortedBy { it.name }

    fun getSongById(id: Int) = songs.firstOrNull { it.id == id }

    fun getGenres() = songs.map { it.category }.distinctBy { it }.sortedBy { it }

    private fun loadJSONFromAsset(filename: String, context: Context): String? {
        var json: String? = null
        try {
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
        } catch (ex: IOException) {
            Log.e(TAG, "Error reading from $filename", ex)
        }
        return json
    }
}