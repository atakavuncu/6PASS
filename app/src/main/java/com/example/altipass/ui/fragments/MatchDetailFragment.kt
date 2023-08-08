package com.example.altipass.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.altipass.R
import com.example.altipass.databinding.FragmentHomePageBinding
import com.example.altipass.databinding.FragmentMatchDetailBinding
import com.example.altipass.model.EventModel
import com.example.altipass.model.MatchModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MatchDetailFragment : Fragment() {

    private lateinit var binding: FragmentMatchDetailBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val GSON_KEY = "gson_key"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMatchDetailBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val view = binding.root

        val timeInfo = binding.timeInfo
        val matchInfo = binding.matchInfo
        val dateInfo = binding.dateInfo
        val firstTeamOdd = binding.firstTeamOdd
        val drawOdd = binding.drawOdd
        val secondTeamOdd = binding.secondTeamOdd

        val star = binding.star

        val matchList = getFavoriteMatches()

        val matchModel: MatchModel? = arguments?.getParcelable("key")
        if (matchModel != null){
            timeInfo.text = matchModel.T
            matchInfo.text = "${matchModel.HN} v ${matchModel.AN}"
            dateInfo.text = matchModel.D
            firstTeamOdd.text = matchModel.MA[0].OCA[0].O.toString()
            drawOdd.text = matchModel.MA[0].OCA[1].O.toString()
            secondTeamOdd.text = matchModel.MA[0].OCA[2].O.toString()
            if (matchList.contains(matchModel)) {
                star.setImageResource(R.drawable.star_non_selected)
                star.tag = "star_non_selected"
            }
            else {
                star.setImageResource(R.drawable.star_selected)
                star.tag = "star_selected"
            }

        }

        star.setOnClickListener{
            if (star.tag == "star_selected") {
                star.setImageResource(R.drawable.star_non_selected)
                star.tag = "star_non_selected"
                if (matchModel != null) {
                    removeMatchFromFavorites(matchModel)
                }
            }
            else {
                star.setImageResource(R.drawable.star_selected)
                star.tag = "star_selected"
                if (matchModel != null) {
                    addMatchToFavorites(matchModel)
                }
            }
        }

        return view
    }

    private fun addMatchToFavorites(match: MatchModel) {
        val gson = Gson()
        val gsonString = sharedPreferences.getString(GSON_KEY, null)
        val type = object : TypeToken<List<MatchModel>>() {}.type

        val favoritesList: MutableList<MatchModel> = gson.fromJson(gsonString, type) ?: mutableListOf()
        favoritesList.add(match)

        val editor = sharedPreferences.edit()
        editor.putString(GSON_KEY, gson.toJson(favoritesList))
        editor.apply()
    }

    private fun removeMatchFromFavorites(match: MatchModel) {
        val gson = Gson()
        val gsonString = sharedPreferences.getString(GSON_KEY, null)
        val type = object : TypeToken<List<MatchModel>>() {}.type

        val favoritesList: MutableList<MatchModel> = gson.fromJson(gsonString, type) ?: mutableListOf()
        favoritesList.remove(match)

        val editor = sharedPreferences.edit()
        editor.putString(GSON_KEY, gson.toJson(favoritesList))
        editor.apply()
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