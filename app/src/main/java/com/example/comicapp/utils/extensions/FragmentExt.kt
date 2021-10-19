package com.example.comicapp.utils.extensions

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.comicapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

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

fun Fragment.showBottomNavigationView(activity: Activity?) {
    val bottomNav: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationMain)
    bottomNav?.visibility = View.VISIBLE
}

fun Fragment.hideBottomNavigationView(activity: Activity?) {
    val bottomNav: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationMain)
    bottomNav?.visibility = View.GONE
}
