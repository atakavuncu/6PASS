package com.example.altipass.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class MatchModel(
    val HN: String,
    val AN: String,
    val D: String,
    val T: String,
    val MA: @RawValue List<OddsModel>
) : Parcelable
