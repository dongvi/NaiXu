package com.projectbase.mainapp.main

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import com.projectbase.R
import com.projectbase.base.ui.BaseActivity
import com.projectbase.base.ultils.extentions.gone
import com.projectbase.base.ultils.extentions.setAnim
import com.projectbase.base.ultils.extentions.visible
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
        btn_hide_or_show_btm.gone()
        bottom_menu.gone()
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
        btn_hide_or_show_btm.visible()
        btn_hide_or_show_btm.setAnim(applicationContext, R.anim.up_fade_in, 1000)
        bottom_menu.visible()
        bottom_menu.setAnim(applicationContext, R.anim.up_fade_in, 1000)

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

    fun hideOrShowBottomMenu() {
        var valueRotate = 0f
        var isHidden = false
        var isAllow = true // allow interaction
        val dim = Runnable { btn_hide_or_show_btm.animate().alpha(0.3f).start() }
        val handler = Handler()

        btn_hide_or_show_btm.setOnClickListener {
            if(isAllow) {
                // temporality not interaction
                isAllow = false

                bottom_menu.setAnim(applicationContext,
                    if (!isHidden) {
                        bottom_menu.gone()
                        R.anim.down_exit
                    } else {
                        bottom_menu.visible()
                        R.anim.up_in
                    }, 1000
                )

                // set anim 1 for btn_hide_or_show_btm
                it.animate().translationY(if(!isHidden) bottom_menu.height.toFloat() else 0f)
                    .setDuration(2000).start()

                // set anim 2 for btn_hide_or_show_btm after time duration of anim 1
                it.postDelayed({
                    valueRotate += 180f
                    it.animate().rotation(valueRotate).setDuration(1000).start()

                    // change value off sttIc and sttInteract after anims are run all
                    it.postDelayed({
                        isHidden = !isHidden
                        isAllow = true
                    }, 1500)
                }, 1000)
            }

            // clarify btn_hide_or_show_btm
            it.animate().alpha(1.0f).setDuration(0).start()
            handler.removeCallbacks(dim)

            // if bottom menu is hidden and after 3s not interact the btn_hide_or_show_btm is dim
            if(!isHidden) {
                handler.postDelayed(dim, 3000)
            }
        }
    }
}
