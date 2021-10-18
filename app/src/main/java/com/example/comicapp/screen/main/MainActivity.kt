package com.example.comicapp.screen.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.comicapp.R
import com.example.comicapp.screen.main.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount <= 0) {
            bottomNavigationMain.visibility = View.VISIBLE
        }
    }

    private fun initView() {
        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        mainViewPagerAdapter = MainViewPagerAdapter(this)
        pagerMain.adapter = mainViewPagerAdapter

        bottomNavigationMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_home -> pagerMain.currentItem = 0
                R.id.bottom_nav_classify -> pagerMain.currentItem = 1
                R.id.bottom_nav_me -> pagerMain.currentItem = 2
            }

            true
        }

        pagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when (position) {
                    0 -> {
                        bottomNavigationMain.menu.findItem(R.id.bottom_nav_home).isChecked = true
                    }

                    1 -> {
                        bottomNavigationMain.menu.findItem(R.id.bottom_nav_classify).isChecked =
                            true
                    }

                    else -> {
                        bottomNavigationMain.menu.findItem(R.id.bottom_nav_me).isChecked = true
                    }
                }
            }
        })
    }

}
