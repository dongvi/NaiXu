package com.projectbase.mainapp.main

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.projectbase.R
import com.projectbase.base.api.model.User
import com.projectbase.base.ui.BaseActivity
import com.projectbase.base.ultils.extentions.*
import com.projectbase.mainapp.main.bottommenu.OnClickBottomMenuListener
import com.projectbase.mainapp.main.home.HomeFragment
import com.projectbase.mainapp.main.splash.SplashFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception

class MainActivity : BaseActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.name ?: "MainActivity::class.java.name"
    }

    private val mainViewModel: MainViewModel by viewModel()
    private var currentFragmentTag: String? = null
    private val onClickBottomMenuListener = object : OnClickBottomMenuListener {
        override fun onItemClick(position: Int) {
            when(position) {
                0 -> {}
                1 -> {}
                2 -> {}
                3 -> {}
            }
        }
    }
    private val handler = Handler()
    private val dim = Runnable { btn_hide_or_show_btm.animate().alpha(0.3f).start() }

    // user login demo
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        initViews()
        initScreenFlow()
        handleObservable()
    }

    private fun initViews() {
        // catch click event on bottom menu
        bottom_menu.setOnClickBottomMenuListener(onClickBottomMenuListener)

        hideOrShowBottomMenu()
    }

    private fun handleObservable() {

    }

    /*
    * Handle screen flow
    * */
    private fun initScreenFlow() {
        btn_hide_or_show_btm.setHidden(true)
        bottom_menu.setHidden(true)
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
        // if don't interact the btn_hide_or_show_btm is dim
        handler.postDelayed(dim, 1500)

        root_activity.postDelayed({
            btn_hide_or_show_btm.setHidden(false)
            bottom_menu.setHidden(false)

            btn_hide_or_show_btm.animation = AnimationUtils.loadAnimation(this, R.anim.up_fade_in)
            bottom_menu.animation = AnimationUtils.loadAnimation(this, R.anim.up_fade_in)
        }, 500)


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

    private fun hideOrShowBottomMenu() {
        var valueRotate = 0

        btn_hide_or_show_btm.setOnClickListener {
            btn_hide_or_show_btm.isEnabled = false
            val isHideBottomMenu = bottom_menu.visibility == View.GONE

            bottom_menu.setHidden(!isHideBottomMenu)

            bottom_menu.animation = AnimationUtils
                .loadAnimation(this,
                    if(!isHideBottomMenu) R.anim.move_down_exit else  R.anim.move_up_in )

            // set anim 1 for btn_hide_or_show_btm
            it.animate()
                .translationY(if(!isHideBottomMenu) bottom_menu.height.toFloat() else 0f)
                .setDuration(400)
                .withEndAction {
                    // after anim 1 finish, anim 2 is run
                    it.animate().rotation(++valueRotate * 180f).setDuration(200)
                        .withEndAction { btn_hide_or_show_btm.isEnabled = true }.start()
                }.start()

            // clarify btn_hide_or_show_btm
            it.animate().alpha(1.0f).setDuration(0).start()
            handler.removeCallbacks(dim)

            // if don't interact the btn_hide_or_show_btm is dim
            handler.postDelayed(dim, 1500)
        }
    }

    fun setHiddenBottomMenu(isHiddenButton: Boolean, isHiddenBottomMenu: Boolean) {
        btn_hide_or_show_btm.setHidden(isHiddenButton)
        bottom_menu.setHidden(isHiddenBottomMenu)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(dim)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {}
        else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {}
    }

    fun setCurrentFragmentTag(tag: String?) {
        currentFragmentTag = tag
    }

    fun setBackground(resId: Int) {
        root_activity.background = resources.getDrawable(resId)
    }
}
