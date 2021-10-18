package com.example.comicapp.screen.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comicapp.R
import com.example.comicapp.data.model.Chapter
import com.example.comicapp.screen.content.adapter.ContentAdapter
import kotlinx.android.synthetic.main.fragment_content.view.*

class ContentFragment : Fragment() {
    private lateinit var contentViewModel: ContentViewModel
    private lateinit var contentAdapter: ContentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_content, container, false)

        initView(view)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initView(view: View) {
        view.rcvContent.apply {
            this.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            contentAdapter = ContentAdapter(requireContext())
            this.adapter = contentAdapter
        }

        view.imgBackContent.setOnClickListener { activity?.supportFragmentManager?.popBackStack() }
    }

    private fun initData() {
        contentViewModel = ViewModelProvider(this).get(ContentViewModel::class.java)

        arguments?.run {
            contentViewModel.setChapter(getParcelable(ARGUMENT_CHAPTER))
        }

        contentViewModel.chapter.observe(requireActivity(), Observer {
            contentAdapter.setData(it.listImage)
        })
    }

    companion object {
        const val ARGUMENT_CHAPTER = "ARGUMENT_CHAPTER"

        fun newInstance(chapter: Chapter?) = ContentFragment().apply {
            arguments = bundleOf(ARGUMENT_CHAPTER to chapter)
        }
    }
}
