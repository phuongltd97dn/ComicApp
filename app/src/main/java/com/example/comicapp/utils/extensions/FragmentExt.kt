package com.example.comicapp.utils.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.comicapp.R

fun Fragment.replaceFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false,
    tag: String = fragment::class.java.simpleName
) {
    activity?.supportFragmentManager?.apply {
        beginTransaction()
            .addToBackStack(tag)
            .setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_left,
                R.anim.slide_out_right,
                R.anim.slide_in_right
            )
            .replace(containerId, fragment, tag)
            .commit()
    }
}
