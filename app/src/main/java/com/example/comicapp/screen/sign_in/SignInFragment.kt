package com.example.comicapp.screen.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.comicapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener() {
        btnSignIn.setOnClickListener {
            validateAccount()
        }

        llSignInContainer.setOnClickListener {
            // Set onClick to avoid old fragment listen event click from new fragment
        }
    }

    private fun validateAccount() {
        val username = edtSignInUsername.text.toString()
        val password = edtSignInPassword.text.toString()

        if (username != "admin" || password != "admin") {
            Toast.makeText(context, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show()
        } else {
            setFragmentResult(
                REQUEST_KEY,
                bundleOf(KEY_USERNAME to username, KEY_PASSWORD to password)
            )

            activity?.supportFragmentManager?.popBackStack()
            val bottomNav: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationMain)
            bottomNav?.visibility = View.VISIBLE
        }
    }

    companion object {
        const val REQUEST_KEY = "REQUEST_KEY"
        const val KEY_USERNAME = "KEY_USERNAME"
        const val KEY_PASSWORD = "KEY_PASSWORD"

        fun newInstance() = SignInFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
        }
    }
}