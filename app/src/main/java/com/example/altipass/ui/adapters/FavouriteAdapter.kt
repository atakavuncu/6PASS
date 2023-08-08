package com.example.altipass.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.altipass.R
import com.example.altipass.model.MatchModel

class FavouriteAdapter (val favourites: List<MatchModel>) : RecyclerView.Adapter<FavouriteViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_post, parent, false)
        return FavouriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favourites.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        return holder.bindView(favourites, position)
    }
}