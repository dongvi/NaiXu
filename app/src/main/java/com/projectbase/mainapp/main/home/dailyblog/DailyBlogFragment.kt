package com.projectbase.mainapp.main.home.dailyblog

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projectbase.R
import com.projectbase.base.ui.BaseFragment
import com.projectbase.mainapp.main.MainActivity
import kotlinx.android.synthetic.main.bottom_func.view.*
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
            // think screen
        }
    }

    private fun handleObservable() {
        dailyBlogViewModel.getListDailyBlog().observe(viewLifecycleOwner) {
            dailyBlogAdapter?.setData(it)
        }

        dailyBlogViewModel.error().observe(viewLifecycleOwner) {
            Log.d("Check", "error: $it")
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden) {
            mainActivity?.setCurrentFragmentTag(TAG)
        }
    }

    private fun getAllData() {
        dailyBlogViewModel.getDailyBlogApi()
    }
}
