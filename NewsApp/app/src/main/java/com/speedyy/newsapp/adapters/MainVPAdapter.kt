package com.speedyy.newsapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.speedyy.newsapp.view.ViewDbNews
import com.speedyy.newsapp.view.ViewOnlineNews

class MainVPAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if(position == 0) {
            return ViewOnlineNews()
        } else if(position == 1) {
            return ViewDbNews()
        }
        return ViewOnlineNews()
    }
}