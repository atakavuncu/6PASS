package com.example.altipass.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.altipass.databinding.CardPostBinding
import com.example.altipass.databinding.FragmentHomePageBinding
import com.example.altipass.model.DataModel
import com.example.altipass.retrofit.ApiService
import com.example.altipass.retrofit.ServiceGenerator
import com.example.altipass.ui.adapters.PostAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        val view = binding.root
        val recyclerView = binding.matchesRecyclerView

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getPosts()

        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(
                call: Call<DataModel>,
                response: Response<DataModel>
            ) {
                if (response.isSuccessful) {
                    val dataModel: DataModel? = response.body()
                    dataModel?.let { dataModel ->
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(requireContext())
                            adapter = PostAdapter(dataModel.sg)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                t.printStackTrace()
                Log.e("FailureError", t.message.toString())
            }
        })

        return view
    }
}