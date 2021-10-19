package com.example.comicapp.screen.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.comicapp.R
import com.example.comicapp.data.model.User
import com.example.comicapp.screen.setting.SettingFragment
import com.example.comicapp.screen.sign_in.SignInFragment
import com.example.comicapp.screen.sign_in.SignInFragment.Companion.KEY_PASSWORD
import com.example.comicapp.screen.sign_in.SignInFragment.Companion.KEY_USERNAME
import com.example.comicapp.screen.sign_in.SignInFragment.Companion.REQUEST_KEY
import com.example.comicapp.utils.extensions.hideBottomNavigationView
import com.example.comicapp.utils.extensions.replaceFragment
import com.example.comicapp.utils.extensions.showBottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_me.*

class MeFragment : Fragment() {
    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(REQUEST_KEY) { requestKey, bundle ->
            val username = bundle.getString(KEY_USERNAME)
            val password = bundle.getString(KEY_PASSWORD)

            Toast.makeText(context, "username: $username, password: $password", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_me, container, false)

        initView(view)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initListener()
    }

    private fun initView(view: View?) {

    }

    private fun initData() {
        currentUser.apply {
            tvUserName.text = this?.username
            tvUserFullName.text = this?.fullName
            tvUserEmail.text = this?.email
            tvUserPhone.text = this?.phone
        }
    }

    private fun initListener() {
        onButtonSignInClick()
        onButtonSignUpClick()
        onButtonSettingClick()
    }

    private fun onButtonSignInClick() {
        btnUserSignIn.setOnClickListener {
            replaceFragment(R.id.layoutContainer, SignInFragment.newInstance())
            hideBottomNavigationView(activity)
        }
    }

    private fun onButtonSignUpClick() {
        btnUserSignUp.setOnClickListener {
            Toast.makeText(context, "Sign Up!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onButtonSettingClick() {
        btnSetting.setOnClickListener {
            replaceFragment(R.id.layoutContainer, SettingFragment.newInstance())

            val bottomNav: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationMain)
            bottomNav?.visibility = View.GONE
        }
    }

    companion object {
        const val ARGUMENT_USERNAME = "ARGUMENT_USERNAME"
        const val ARGUMENT_PASSWORD = "ARGUMENT_PASSWORD"

        fun newInstance() = MeFragment().apply {
//            arguments = Bundle().apply {
//                putString(ARGUMENT_USERNAME, userName)
//                putString(ARGUMENT_PASSWORD, password)
//            }
        }
    }
}
