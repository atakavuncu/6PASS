package com.example.altipass.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.altipass.databinding.FragmentFavouritesBinding
import com.example.altipass.model.MatchModel
import com.example.altipass.ui.adapters.FavouriteAdapter
import com.example.altipass.ui.viewmodels.FavouritesViewModel
import dagger.hilt.android.AndroidEntryPoint

class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val favouritesViewModel: FavouritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val view = binding.root
        val recyclerView = binding.matchesRecyclerView

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val matchList = favouritesViewModel.getFavoriteMatches(sharedPreferences)
        val adapter = FavouriteAdapter(matchList)
        recyclerView.adapter = adapter

        return view
    }
}
