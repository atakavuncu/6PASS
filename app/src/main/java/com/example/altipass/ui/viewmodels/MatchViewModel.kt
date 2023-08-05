/*
package com.example.altipass.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altipass.model.DataModel
import com.example.altipass.retrofit.ServiceGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class MatchViewModel : ViewModel() {
    private val _match = MutableLiveData<DataModel>()
    val post: LiveData<DataModel>
        get() = _match

    private val _hasError = MutableLiveData<Boolean>()
    val hasError: LiveData<Boolean>
        get() = _hasError

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            val call = RetrofitInstance.api.fetchData()

            call.enqueue(object : retrofit2.Callback<DataModel>{
                override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                    if (response.isSuccessful){
                        val dataModel = response.body()

                        dataModel?.let {
                            val matchModel = it.EA[0]
                            val eno = matchModel.ENO
                            val d = matchModel.D
                            val t = matchModel.T
                            val oca = matchModel.MA[0].OCA[0]
                            val n = oca.N
                            val o = oca.O

                            println("ENO: $eno, D: $d, T: $t, N: $n, O: $o")
                        }
                    }
                    else {
                        println("Response error: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                    println("Request failed: ${t.message}")
                }
            })
        }
    }
}*/
