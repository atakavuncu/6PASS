package com.example.altipass.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.altipass.databinding.CardPostBinding
import com.example.altipass.databinding.FragmentHomePageBinding
import com.example.altipass.model.DataModel
import com.example.altipass.retrofit.ApiService
import com.example.altipass.retrofit.ServiceGenerator
import com.example.altipass.ui.adapters.PostAdapter
import com.example.altipass.ui.viewmodels.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private val homePageViewModel: HomePageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        val view = binding.root
        val recyclerView = binding.matchesRecyclerView

        homePageViewModel.fetchData(recyclerView, requireContext())

        return view
    }
}