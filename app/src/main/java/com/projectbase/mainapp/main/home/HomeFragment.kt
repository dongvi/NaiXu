package com.projectbase.mainapp.main.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.projectbase.R
import com.projectbase.base.api.model.ItemDailyBlog
import com.projectbase.base.ui.BaseFragment
import com.projectbase.base.ultils.extentions.gone
import com.projectbase.base.ultils.extentions.visible
import com.projectbase.mainapp.main.MainActivity
import com.projectbase.mainapp.main.MainViewModel
import com.projectbase.mainapp.main.home.bannerads.BannerAdsAdapter
import com.projectbase.mainapp.main.home.bannerads.BannerAdsAdapterListener
import com.projectbase.mainapp.main.home.dailyblog.DailyBlogAdapter
import com.projectbase.mainapp.main.home.dailyblog.DailyBlogFragment
import com.projectbase.mainapp.main.home.dailychanllenge.DailyChallengeFragment
import com.projectbase.mainapp.main.home.game.GameFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.scope.viewModel

class HomeFragment : BaseFragment() {

    companion object {
        val TAG: String = HomeFragment::class.java.name ?: "HomeFragment::class.java.name"
    }

    private val mainViewModel: MainViewModel by sharedViewModel()
    private val homeViewModel: HomeViewModel by currentScope.viewModel(this)
    private var mainActivity: MainActivity? = null
    private var bannerAdsAdapter: BannerAdsAdapter? = null
    private var dailyBlogAdapter: DailyBlogAdapter? = null

    private var bannerAdsAdapterListener = object : BannerAdsAdapterListener {
        override fun onClickBannerAds(position: Int) {
            Log.d("Check", "onClickBannerAds: $position")
        }
    }

    private val handler = Handler()
    private var currentItem = 0
    private val TIME_DELAY_RUN_BANNER_ADS = 3000L
    private val autoRunBanner: Runnable = object : Runnable {
        override fun run() {
            if (view_pager_banners.adapter?.count == currentItem + 1) {
                currentItem = 0
            }
            else {
                currentItem++
            }

            view_pager_banners.currentItem = currentItem
            handler.postDelayed(this, TIME_DELAY_RUN_BANNER_ADS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getAllData()
        handleObservable()
    }

    private fun initView() {
        // set background for main activity
        mainActivity?.setBackground(R.drawable.background_home)

        effectOfItems()

        // viewpager banner ads
        bannerAdsAdapter = context?.let { BannerAdsAdapter(it) }
        view_pager_banners.adapter = bannerAdsAdapter

        // catch event click on banner ads
        bannerAdsAdapter?.setBannerAdsAdapterListener(bannerAdsAdapterListener)
        view_pager_banners.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {}

            override fun onPageSelected(position: Int) {
                currentItem = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        // View daily blog
        dailyBlogAdapter = context?.let { DailyBlogAdapter(it) }
        container_item_daily_blog.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        container_item_daily_blog.adapter = dailyBlogAdapter

        button_view_more_daily_blog.setOnClickListener {
            mainActivity?.addFragment(DailyBlogFragment.TAG, DailyBlogFragment(), true)
        }

        // feature daily challenge
        view_daily_challenge.setOnClickListener {
            mainActivity?.addFragment(DailyChallengeFragment.TAG, DailyChallengeFragment(), true)
        }
        // feature game
        view_game.setOnClickListener {
            mainActivity?.addFragment(GameFragment.TAG, GameFragment(), true)
        }
    }

    private fun handleObservable() {
        homeViewModel.getListBannerAds().observe(viewLifecycleOwner) {
            bannerAdsAdapter?.setData(it)
            if(it.size > 0) {
                handler.postDelayed(autoRunBanner, TIME_DELAY_RUN_BANNER_ADS)
            }
        }

        homeViewModel.getListDailyBlog().observe(viewLifecycleOwner) {
            dailyBlogAdapter?.setData(if(it.size <= 3) it else it.subList(0, 3))
        }

        homeViewModel.error().observe(viewLifecycleOwner) {
            Log.d("Check", "error: $it")
        }
    }

    private fun effectOfItems() {
        for (item in root_home.children)
            item.gone()

        root_home.postDelayed({
            for (item in root_home.children)
                item.visible()

            view_pager_banners.animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
            view_daily_blog.animation = AnimationUtils.loadAnimation(context, R.anim.left_in)
            view_daily_challenge.animation = AnimationUtils.loadAnimation(context, R.anim.right_enter)
            view_game.animation = AnimationUtils.loadAnimation(context, R.anim.left_in)
        }, 500)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(autoRunBanner)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden) {
            mainActivity?.setCurrentFragmentTag(TAG)
        }
    }

    private fun getAllData() {
        homeViewModel.getBannerAdsApi()
        homeViewModel.getDailyBlogApi()
    }
}
