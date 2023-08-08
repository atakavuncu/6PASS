package com.example.altipass.ui.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.altipass.model.MatchModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavouritesViewModel : ViewModel() {

    private val GSON_KEY = "gson_key"

    fun getFavoriteMatches(sharedPreferences: SharedPreferences): List<MatchModel> {
        val gsonString = sharedPreferences.getString(GSON_KEY, null)
        if (gsonString != null) {
            val gson = Gson()
            val type = object : TypeToken<List<MatchModel>>() {}.type
            val favoriteMatches = gson.fromJson<List<MatchModel>>(gsonString, type)
            return favoriteMatches ?: emptyList()
        }
        return emptyList()
    }
}
