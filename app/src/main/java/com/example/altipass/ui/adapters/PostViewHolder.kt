package com.example.altipass.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.altipass.R
import com.example.altipass.model.EventModel
import com.example.altipass.ui.fragments.MatchDetailFragment

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val matchName: TextView = itemView.findViewById(R.id.matchName)
    private val date: TextView = itemView.findViewById(R.id.date)
    private val time: TextView = itemView.findViewById(R.id.time)
    private val button: Button = itemView.findViewById(R.id.button)

    @SuppressLint("SetTextI18n")
    fun bindView(eventModel: EventModel, position: Int) {
        matchName.text = "${eventModel.EA[position].HN} v ${eventModel.EA[position].AN}"
        date.text = eventModel.EA[position].D
        time.text = eventModel.EA[position].T
        if (position % 2 == 0)
            button.background = ColorDrawable(Color.argb(0, 255, 255, 255))
        else
            button.background = ColorDrawable(Color.argb(100, 255, 255, 255))

        button.setOnClickListener {
            val matchDetailFragment: Fragment = MatchDetailFragment()

            val bundle = Bundle()
            bundle.putParcelable("key", eventModel.EA[position])
            matchDetailFragment.arguments = bundle

            val fragmentManager: FragmentManager = (itemView.context as AppCompatActivity).supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView, matchDetailFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

}