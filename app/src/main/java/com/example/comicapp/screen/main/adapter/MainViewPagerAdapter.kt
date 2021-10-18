package com.example.comicapp.screen.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.comicapp.screen.classify.ClassifyFragment
import com.example.comicapp.screen.me.MeFragment
import com.example.comicapp.screen.home.HomeFragment

class MainViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment.newInstance()
            1 -> ClassifyFragment.newInstance()
            else -> MeFragment.newInstance()
        }
    }

    companion object {
        const val NUM_PAGES = 3
    }
}