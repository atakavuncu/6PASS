package com.example.altipass.ui.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.altipass.R
import com.example.altipass.model.MatchModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MatchDetailViewModel : ViewModel() {

    private val GSON_KEY = "gson_key"

    fun addMatchToFavorites(
        match: MatchModel,
        sharedPreferences: SharedPreferences
    ) {
        val gson = Gson()
        val gsonString = sharedPreferences.getString(GSON_KEY, null)
        val type = object : TypeToken<List<MatchModel>>() {}.type

        val favoritesList: MutableList<MatchModel> = gson.fromJson(gsonString, type) ?: mutableListOf()
        favoritesList.add(match)

        val editor = sharedPreferences.edit()
        editor.putString(GSON_KEY, gson.toJson(favoritesList))
        editor.apply()
    }

    fun removeMatchFromFavorites(
        match: MatchModel,
        sharedPreferences: SharedPreferences
    ) {
        val gson = Gson()
        val gsonString = sharedPreferences.getString(GSON_KEY, null)
        val type = object : TypeToken<List<MatchModel>>() {}.type

        val favoritesList: MutableList<MatchModel> = gson.fromJson(gsonString, type) ?: mutableListOf()
        favoritesList.remove(match)

        val editor = sharedPreferences.edit()
        editor.putString(GSON_KEY, gson.toJson(favoritesList))
        editor.apply()
    }

}
