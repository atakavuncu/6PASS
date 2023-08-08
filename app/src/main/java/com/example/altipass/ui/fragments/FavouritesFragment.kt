package com.example.altipass.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.altipass.R
import com.example.altipass.databinding.FragmentFavouritesBinding
import com.example.altipass.model.MatchModel
import com.example.altipass.ui.adapters.FavouriteAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding

    private lateinit var sharedPreferences: SharedPreferences
    private val GSON_KEY = "gson_key"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val view = binding.root
        val recyclerView = binding.matchesRecyclerView

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val matchList = getFavoriteMatches()
        val adapter = FavouriteAdapter(matchList)
        recyclerView.adapter = adapter

        return view
    }

    private fun getFavoriteMatches(): List<MatchModel> {
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