package com.projectbase.mainapp.main.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.projectbase.R
import com.projectbase.base.ui.BaseFragment
import com.projectbase.base.ultils.extentions.setHidden
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
        mainActivity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        view_logo.postDelayed({
            view_logo.setHidden(false)

            view_logo.animation = AnimationUtils.loadAnimation(context, R.anim.up_fade_in)

            view_logo.postDelayed ({
                view_logo.animation = AnimationUtils.loadAnimation(context, R.anim.right_exit)
                view_logo.setHidden(true)
            }, 2300)
        }, 500)

        startHomeScreen()
    }

    private fun startHomeScreen() {
        root_splash.postDelayed({
            mainActivity?.openHomeScreen()
            mainActivity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }, SPLASH_TIME)
    }
}