package com.example.altipass.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.altipass.model.DataModel
import com.example.altipass.retrofit.ApiService
import com.example.altipass.retrofit.ServiceGenerator
import com.example.altipass.ui.adapters.PostAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageViewModel : ViewModel() {

    fun fetchData(recyclerView : RecyclerView, context: Context) {
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
                            layoutManager = LinearLayoutManager(context)
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
    }
}