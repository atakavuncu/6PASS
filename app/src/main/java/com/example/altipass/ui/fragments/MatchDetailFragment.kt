package com.example.altipass.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.altipass.R
import com.example.altipass.databinding.FragmentMatchDetailBinding
import com.example.altipass.model.MatchModel
import com.example.altipass.ui.viewmodels.FavouritesViewModel
import com.example.altipass.ui.viewmodels.MatchDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

class MatchDetailFragment : Fragment() {

    private lateinit var binding: FragmentMatchDetailBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val matchDetailViewModel: MatchDetailViewModel by viewModels()
    private val favouritesViewModel: FavouritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMatchDetailBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val view = binding.root

        val timeInfo = binding.timeInfo
        val matchInfo = binding.matchInfo
        val dateInfo = binding.dateInfo
        val firstTeamOdd = binding.firstTeamOdd
        val drawOdd = binding.drawOdd
        val secondTeamOdd = binding.secondTeamOdd

        val star = binding.star
        val backIcon = binding.backIcon

        val matchList = favouritesViewModel.getFavoriteMatches(sharedPreferences)

        val matchModel: MatchModel? = arguments?.getParcelable("key")
        if (matchModel != null) {
            timeInfo.text = matchModel.T
            matchInfo.text = "${matchModel.HN} v ${matchModel.AN}"
            dateInfo.text = matchModel.D
            firstTeamOdd.text = matchModel.MA[0].OCA[0].O.toString()
            if(matchModel.MA[0].OCA.size == 2){
                drawOdd.text = "--"
                secondTeamOdd.text = matchModel.MA[0].OCA[1].O.toString()
            }
            else{
                drawOdd.text = matchModel.MA[0].OCA[1].O.toString()
                secondTeamOdd.text = matchModel.MA[0].OCA[2].O.toString()
            }
            if (matchList.contains(matchModel)) {
                star.setImageResource(R.drawable.star_selected)
                star.tag = "star_selected"
            } else {
                star.setImageResource(R.drawable.star_non_selected)
                star.tag = "star_non_selected"
            }
        }

        star.setOnClickListener {
            if (star.tag == "star_selected") {
                star.setImageResource(R.drawable.star_non_selected)
                star.tag = "star_non_selected"
                if (matchModel != null) {
                    matchDetailViewModel.removeMatchFromFavorites(matchModel, sharedPreferences)
                }
            } else {
                star.setImageResource(R.drawable.star_selected)
                star.tag = "star_selected"
                if (matchModel != null) {
                    matchDetailViewModel.addMatchToFavorites(matchModel, sharedPreferences)
                }
            }
        }

        backIcon.setOnClickListener {
            val homePageFragment: Fragment = HomePageFragment()

            val fragmentManager: FragmentManager = (this.context as AppCompatActivity).supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView, homePageFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }
}
