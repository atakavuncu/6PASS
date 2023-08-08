package com.example.altipass.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.altipass.MainActivity
import com.example.altipass.R
import com.example.altipass.model.DataModel
import com.example.altipass.model.EventModel
import com.example.altipass.model.MatchModel
import com.example.altipass.ui.fragments.HomePageFragment
import com.example.altipass.ui.fragments.MatchDetailFragment
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