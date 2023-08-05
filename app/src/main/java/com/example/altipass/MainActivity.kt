package com.example.altipass

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.altipass.databinding.ActivityMainBinding
import com.example.altipass.model.DataModel
import com.example.altipass.retrofit.ApiService
import com.example.altipass.retrofit.ServiceGenerator
import com.example.altipass.ui.adapters.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.myRecyclerView

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
                            layoutManager = LinearLayoutManager(this@MainActivity)
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