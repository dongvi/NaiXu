package com.projectbase.mainapp.main.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectbase.R
import com.projectbase.base.ui.BaseFragment
import com.projectbase.mainapp.main.MainActivity
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment: BaseFragment() {

    companion object {
        val TAG = SplashFragment::class.java.name ?: "SplashFragment::class.java.name"

        private const val SPLASH_TIME = 3000L
    }

    private var mainActivity: MainActivity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity

        startHomeScreen()
    }

    private fun startHomeScreen() {
        root_splash.postDelayed({
            mainActivity?.openHomeScreen()
        }, SPLASH_TIME)
    }
}