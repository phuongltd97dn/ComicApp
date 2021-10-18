package com.example.comicapp.screen.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.comicapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initToolbar()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgBackSetting.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()

            val bottomNav: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationMain)
            bottomNav?.visibility = View.VISIBLE
        }
    }

    private fun initToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbarSetting)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    companion object {
        fun newInstance() = SettingFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
        }
    }
}