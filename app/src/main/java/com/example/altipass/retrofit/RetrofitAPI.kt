package com.example.altipass.retrofit

import com.example.altipass.model.MatchModel
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("api/bulten/getlivebultenv3")
    suspend fun fetchData(): Response<MatchModel>

}