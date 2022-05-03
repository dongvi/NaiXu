package com.projectbase.mainapp.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.projectbase.R
import com.projectbase.base.ui.BaseActivity
import com.projectbase.mainapp.main.home.HomeFragment
import com.projectbase.mainapp.main.splash.SplashFragment
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception

class MainActivity : BaseActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.name ?: "MainActivity::class.java.name"
    }

    private val mainViewModel: MainViewModel by viewModel()
    private var currentFragmentTag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        initViews()
        initScreenFlow()
        handleObservable()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun initViews() {

    }

    private fun handleObservable() {

    }

    /*
    * Handle screen flow
    * */
    private fun initScreenFlow() {
        cleanBackStackIfNeed()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                SplashFragment(),
                SplashFragment.TAG
            ).commitAllowingStateLoss()
        currentFragmentTag = SplashFragment.TAG
    }

    private fun cleanBackStackIfNeed() {
        try {
            if (supportFragmentManager.backStackEntryCount > 0)
                supportFragmentManager.popBackStackImmediate()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun openHomeScreen() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.right_enter, R.anim.left_exit)
            .replace(
                R.id.container,
                HomeFragment(),
                HomeFragment.TAG
            ).commitAllowingStateLoss()
        currentFragmentTag = HomeFragment.TAG
    }

    fun addFragment(fragmentTag: String, fragment: Fragment? = null, isEnableAnimation: Boolean = false) {
        if (currentFragmentTag == fragmentTag) return

        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        if (isEnableAnimation) {
            transaction.setCustomAnimations(R.anim.right_enter, R.anim.fade_out, R.anim.fade_in, R.anim.right_exit)
        }
        supportFragmentManager.findFragmentByTag(currentFragmentTag)?.let {
            transaction.hide(it)
        }
        currentFragmentTag = fragmentTag
        // if input fragment by tag is exist, show or re-add it
        supportFragmentManager.findFragmentByTag(fragmentTag)?.let {
            if (it.isAdded) transaction.show(it).commit()
            else transaction.add(R.id.container, it, fragmentTag).commit()
            return
        }

        // if you want show specific fragment
        fragment?.let {
            transaction.add(R.id.container, it, fragmentTag).commit()
            return
        }

        // if you want show fragment only by tag
        transaction.add(
            R.id.container,
            createNewFragmentByTag(fragmentTag),
            fragmentTag
        ).commit()
    }

    private fun createNewFragmentByTag(fragmentTag: String): Fragment {
        return when (fragmentTag) {
            HomeFragment.TAG -> HomeFragment()
            else -> HomeFragment()
        }
    }
}
