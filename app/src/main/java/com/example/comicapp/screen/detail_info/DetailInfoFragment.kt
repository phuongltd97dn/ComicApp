package com.example.comicapp.screen.detail_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.comicapp.R
import com.example.comicapp.data.model.Comic
import kotlinx.android.synthetic.main.fragment_detail_info.*

class DetailInfoFragment : Fragment() {
    private lateinit var detailInfoViewModel: DetailInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_info, container, false)

        initView(view)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initView(view: View?) {

    }

    private fun initData() {
        detailInfoViewModel = ViewModelProvider(this).get(DetailInfoViewModel::class.java)

        arguments?.run {
            detailInfoViewModel.setComic(getParcelable(ARGUMENT_COMIC_DETAIL_INFO))
        }

        detailInfoViewModel.comic.observe(requireActivity(), Observer {
            tvAuthor.text = it.author
            tvStatus.text = it.status
            tvDescription.text = it.description

            if (it.listCategories.size == 1) {
                tvCategories.text = it.listCategories[0].name
            } else {
                var categories = ""

                for (i in it.listCategories.indices) {
                    categories += it.listCategories[i].name
                    if (i != it.listCategories.size - 1) {
                        categories += context?.getString(R.string.comma)
                    }
                }

                tvCategories.text = categories
            }
        })
    }

    companion object {
        const val ARGUMENT_COMIC_DETAIL_INFO = "ARGUMENT_COMIC_DETAIL_INFO"

        fun newInstance(comic: Comic?) = DetailInfoFragment().apply {
            arguments = bundleOf(ARGUMENT_COMIC_DETAIL_INFO to comic)
        }
    }
}
