package com.example.altipass.retrofit

import com.example.altipass.model.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/api/bulten/getlivebultenv3")
    fun getPosts(): Call<DataModel>
}