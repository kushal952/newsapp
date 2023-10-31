package com.speedyy.newsapp.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.speedyy.newsapp.R
import com.speedyy.newsapp.adapters.MainVPAdapter
import com.speedyy.newsapp.databinding.LandingScreenBinding

class LandingScreen: Fragment(R.layout.landing_screen) {

    private lateinit var landingScreenBinding: LandingScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainVPAdapter = MainVPAdapter(this)
        landingScreenBinding = LandingScreenBinding.bind(view)
        landingScreenBinding.viewPagerNews.adapter = mainVPAdapter

        val tabLayout = TabLayoutMediator(landingScreenBinding.tabLayout, landingScreenBinding.viewPagerNews) { tab, position ->
            if (position == 0) {
                tab.text = "API"
            } else if(position == 1){
                tab.text = "Database"
            }
        }
        tabLayout.attach()
    }
}














