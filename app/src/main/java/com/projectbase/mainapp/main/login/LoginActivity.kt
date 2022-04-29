package com.projectbase.mainapp.main.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.projectbase.R
import kotlinx.android.synthetic.main.activity_login.*
import com.projectbase.base.ui.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        handleObservable()
    }

    private fun initView() {
        button_test_api.setOnClickListener {
            loginViewModel.getExampleApi(0)
        }
    }

    private fun handleObservable() {
        loginViewModel.getResponseData().observe(this, {
            Log.d("Check", "getResponseData: $it")
        })

        loginViewModel.error().observe(this, {
            Log.d("Check", "error: $it")
        })
    }
}
