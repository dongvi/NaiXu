package com.projectbase.mainapp.main.home.dailyblog.bottomfunc

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.projectbase.R
import com.projectbase.base.ultils.extentions.setHidden
import kotlinx.android.synthetic.main.bottom_func.view.*

class BottomFunc : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        LayoutInflater.from(context).inflate(R.layout.bottom_func, this)
    }

    fun setHiddenButtonLikeAndButtonComment(isHidden: Boolean) {
        val setView = setOf<View>(button_like, button_comment, line_bottom_func_1, line_bottom_func_2)
        setView.forEach {it.setHidden(isHidden)}
    }
}
