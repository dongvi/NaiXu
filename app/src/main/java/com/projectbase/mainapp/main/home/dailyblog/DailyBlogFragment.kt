package com.projectbase.mainapp.main.home.dailyblog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projectbase.R
import com.projectbase.base.ui.BaseFragment
import com.projectbase.mainapp.main.MainActivity
import com.projectbase.mainapp.main.home.dailyblog.postblog.PostBlogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_daily_blog.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.scope.viewModel

class DailyBlogFragment : BaseFragment() {

    companion object {
        val TAG: String = DailyBlogFragment::class.java.name ?: "DailyBlogFragment::class.java.name"
    }

    private var mainActivity: MainActivity? = null
    private var dailyBlogAdapter: DailyBlogAdapter? = null
    private val dailyBlogViewModel: DailyBlogViewModel by currentScope.viewModel(this)
    private var isHiddenBottomMenu = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        return LayoutInflater.from(context).inflate(R.layout.fragment_daily_blog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getAllData()
        handleObservable()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initView() {
        // blogs
        dailyBlogAdapter = context?.let { DailyBlogAdapter(it) }
        container_daily_blog.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        container_daily_blog.adapter = dailyBlogAdapter

        // feature post blog
        view_user_think.setOnClickListener {
            isHiddenBottomMenu = mainActivity?.bottom_menu?.visibility == View.GONE
            mainActivity?.addFragment(PostBlogFragment.TAG, PostBlogFragment(), true)
            mainActivity?.setHiddenBottomMenu(true, true)
        }

        // refresh
        swipe_refresh_container_daily_Blog.setOnRefreshListener {
            dailyBlogAdapter?.removeAllDataBlog()
            getAllData()
        }
    }

    private fun handleObservable() {
        dailyBlogViewModel.getListDailyBlogApi().observe(viewLifecycleOwner) {
            dailyBlogAdapter?.setDataBlog(it)
            swipe_refresh_container_daily_Blog.isRefreshing = false
        }

        dailyBlogViewModel.getListUser().observe(viewLifecycleOwner) {
            dailyBlogAdapter?.setDataUser(it)
            swipe_refresh_container_daily_Blog.isRefreshing = false
        }

        dailyBlogViewModel.getListDailyBlogLocal().observe(viewLifecycleOwner) {
            dailyBlogAdapter?.setDataBlogLocal(it)
            swipe_refresh_container_daily_Blog.isRefreshing = false
        }

        dailyBlogViewModel.error().observe(viewLifecycleOwner) {
            swipe_refresh_container_daily_Blog.isRefreshing = false
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden) {
            mainActivity?.setCurrentFragmentTag(TAG)
            mainActivity?.setHiddenBottomMenu(false, isHiddenBottomMenu)
        }
    }

    private fun getAllData() {
        dailyBlogViewModel.getDailyBlogApi()
        dailyBlogViewModel.getAllUserApi()
        dailyBlogViewModel.getDailyBlogLocal()
    }
}
