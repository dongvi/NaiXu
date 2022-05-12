package com.projectbase.mainapp.main.home.dailychanllenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectbase.R
import com.projectbase.base.ui.BaseFragment
import com.projectbase.mainapp.main.MainActivity
import com.projectbase.mainapp.main.home.dailyblog.DailyBlogFragment

class DailyChallengeFragment : BaseFragment() {

    companion object {
        val TAG: String = DailyChallengeFragment::class.java.name ?: "DailyChallengeFragment::class.java.name"
    }

    private var mainActivity: MainActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        return LayoutInflater.from(context).inflate(R.layout.fragment_daily_challenge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        handleObservable()
    }

    private fun initView() {}
    private fun handleObservable() {}

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden) {
            mainActivity?.setCurrentFragmentTag(DailyBlogFragment.TAG)
        }
    }
}
