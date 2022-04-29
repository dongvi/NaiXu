package com.projectbase.mainapp.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectbase.R
import com.projectbase.base.ui.BaseFragment
import com.projectbase.mainapp.main.MainViewModel
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.scope.viewModel

class HomeFragment : BaseFragment() {

    companion object {
        val TAG: String = HomeFragment::class.java.name ?: "HomeFragment::class.java.name"
    }

    private val mainViewModel: MainViewModel by sharedViewModel()
    private val homeViewModel: HomeViewModel by currentScope.viewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        handleObservable()
    }

    private fun initView() {
        // init view
    }

    private fun handleObservable() {
        // handle observable
    }

}
