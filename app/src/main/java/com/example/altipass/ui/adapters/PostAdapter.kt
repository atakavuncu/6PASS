package com.example.altipass.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.altipass.R
import com.example.altipass.model.DataModel
import com.example.altipass.model.EventModel
import com.example.altipass.model.MatchModel
import com.example.altipass.ui.viewmodels.PostViewHolder

class PostAdapter (val eventModel: EventModel) : RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return eventModel.EA.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.bindView(eventModel, position)
    }

}