package com.example.altipass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.altipass.databinding.ActivityMainBinding
import com.example.altipass.ui.fragments.FavouritesFragment
import com.example.altipass.ui.fragments.MatchDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favourites = binding.favourites

        favourites.setOnClickListener {
            val favouritesFragment: Fragment = FavouritesFragment()

            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView, favouritesFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }


    }
}