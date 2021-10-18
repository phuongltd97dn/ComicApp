package com.example.comicapp.screen.chapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comicapp.R
import com.example.comicapp.data.model.Chapter
import com.example.comicapp.data.model.Comic
import com.example.comicapp.screen.chapter.adapter.ChapterAdapter
import com.example.comicapp.screen.content.ContentFragment
import com.example.comicapp.utils.OnItemChapterClickListener
import com.example.comicapp.utils.extensions.replaceFragment
import kotlinx.android.synthetic.main.fragment_chapter.view.*

class ChapterFragment : Fragment(), OnItemChapterClickListener {
    private lateinit var chapterViewModel: ChapterViewModel
    private lateinit var chapterAdapter: ChapterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chapter, container, false)

        initView(view)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initView(view: View) {
        view.rcvListChapter.apply {
            this.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            context.getDrawable(R.drawable.bg_divider)?.let { divider.setDrawable(it) }
            this.addItemDecoration(divider)

            chapterAdapter = ChapterAdapter(requireContext(), this@ChapterFragment)
            this.adapter = chapterAdapter
        }
    }

    private fun initData() {
        chapterViewModel = ViewModelProvider(this).get(ChapterViewModel::class.java)

        arguments?.run {
            chapterViewModel.setComic(getParcelable(ARGUMENT_COMIC_CHAPTER))
        }

        chapterViewModel.comic.observe(requireActivity(), Observer {
            chapterAdapter.setData(it.listChapters)
        })
    }

    override fun onItemChapterClick(chapter: Chapter?) {
        replaceFragment(R.id.layoutContainer, ContentFragment.newInstance(chapter))
    }

    companion object {
        const val ARGUMENT_COMIC_CHAPTER = "ARGUMENT_COMIC_CHAPTER"

        fun newInstance(comic: Comic?) = ChapterFragment().apply {
            arguments = bundleOf(ARGUMENT_COMIC_CHAPTER to comic)
        }
    }

}
