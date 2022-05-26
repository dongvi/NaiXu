package com.projectbase.mainapp.main.home.dailyblog.bottomfunc

import android.view.View

interface ItemBottomFuncListener {
    fun onClickButtonLike(buttonLike: ItemBottomFunc, userId: String?, blogId: String?)
    fun onClickButtonComment()
    fun onClickButtonShare()
}
