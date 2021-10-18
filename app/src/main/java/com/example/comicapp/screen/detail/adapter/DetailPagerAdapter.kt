package com.example.comicapp.screen.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.comicapp.data.model.Comic
import com.example.comicapp.screen.chapter.ChapterFragment
import com.example.comicapp.screen.detail_info.DetailInfoFragment

class DetailPagerAdapter(fa: FragmentActivity) :
    FragmentStateAdapter(fa) {
    private var comic: Comic? = null

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DetailInfoFragment.newInstance(comic)
            else -> ChapterFragment.newInstance(comic)
        }
    }

    fun setComic(comic: Comic?) {
        this.comic = comic
    }

    companion object {
        const val NUM_PAGES = 2
    }
}
