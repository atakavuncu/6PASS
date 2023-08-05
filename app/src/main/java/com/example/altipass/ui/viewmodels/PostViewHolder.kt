package com.example.altipass.ui.viewmodels

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.altipass.R
import com.example.altipass.model.DataModel
import com.example.altipass.model.EventModel

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val matchName: TextView = itemView.findViewById(R.id.matchName)
    private val date: TextView = itemView.findViewById(R.id.date)
    private val time: TextView = itemView.findViewById(R.id.time)

    fun bindView(eventModel: EventModel, position: Int) {
        matchName.text = eventModel.EA[position].ENO
        println("deneme")
        println(eventModel.EA[position].ENO)
        date.text = eventModel.EA[position].D
        time.text = eventModel.EA[position].T
    }

}