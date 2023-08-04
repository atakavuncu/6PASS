package com.example.altipass.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MatchModel(
    @SerializedName("ENO")
    @Expose
    val matchName: String,

    @SerializedName("D")
    @Expose
    val matchDate: String,

    @SerializedName("T")
    @Expose
    val matchTime: String,

    val homeWin: Double,
    val draw: Double,
    val awayWin: Double,
)
