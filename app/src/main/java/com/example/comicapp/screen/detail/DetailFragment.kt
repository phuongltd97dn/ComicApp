package com.example.comicapp.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.comicapp.R
import com.example.comicapp.data.model.Comic
import com.example.comicapp.screen.detail.adapter.DetailPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private var comic: Comic? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        initView(view)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initListener()
    }

    private fun initView(view: View) {
        view.imgBackDetail.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()

            val bottomNav: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationMain)
            bottomNav?.visibility = View.VISIBLE
        }
    }

    private fun initData() {
        val pagerAdapter = DetailPagerAdapter(context as FragmentActivity)
        pagerDetail.adapter = pagerAdapter
        setUpTabLayout()

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        arguments?.run {
            detailViewModel.setComic(getParcelable(ARGUMENT_COMIC_DETAIL))
        }

        detailViewModel.comic.observe(requireActivity(), Observer {
            comic = it
            pagerAdapter.setComic(it)
        })

        Glide.with(requireContext()).load(comic?.image).into(imgImageDetail)
    }


    private fun initListener() {
        llDetailContainer.setOnClickListener {
            // Set onClick to avoid old fragment listen click event from new fragment
        }
    }


    private fun setUpTabLayout() {
        TabLayoutMediator(tab_layout, pagerDetail) { tab, position ->
            when (position) {
                0 -> tab.text = context?.getString(R.string.text_view_tab_0_text)
                1 -> tab.text = context?.getString(R.string.text_view_tab_1_text)
            }
        }.attach()
    }

    companion object {
        const val ARGUMENT_COMIC_DETAIL = "ARGUMENT_COMIC_DETAIL"

        fun newInstance(comic: Comic?) = DetailFragment().apply {
            arguments = bundleOf(ARGUMENT_COMIC_DETAIL to comic)
        }
    }
}
