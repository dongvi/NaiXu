package com.projectbase.mainapp.main.home.dailyblog.bottomfunc

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.projectbase.R
import kotlinx.android.synthetic.main.bottom_func.view.*

@SuppressLint("ClickableViewAccessibility")
class BottomFunc : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var itemBottomFuncListener: ItemBottomFuncListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.bottom_func, this)

        var isLike = false
        button_like.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    button_like.setBackgroundColor(resources.getColor(R.color.dark_5))
                }

                MotionEvent.ACTION_CANCEL -> {
                    button_like.setBackgroundColor(resources.getColor(R.color.no_color))
                }

                MotionEvent.ACTION_UP -> {
                    isLike = !isLike
                    if(isLike) button_like.active()
                    else button_like.inactive()
                    button_like.setBackgroundColor(resources.getColor(R.color.no_color))
                    // feature like ...
                }
            }
            true
        }

        button_comment.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    button_comment.active()
                    button_comment.setBackgroundColor(resources.getColor(R.color.dark_5))
                }

                MotionEvent.ACTION_CANCEL -> {
                    button_comment.inactive()
                    button_comment.setBackgroundColor(resources.getColor(R.color.no_color))
                }

                MotionEvent.ACTION_UP -> {
                    button_comment.inactive()
                    button_comment.setBackgroundColor(resources.getColor(R.color.no_color))
                    itemBottomFuncListener?.onClickButtonComment()
                }
            }
            true
        }

        button_share.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    button_share.active()
                    button_share.setBackgroundColor(resources.getColor(R.color.dark_5))
                }

                MotionEvent.ACTION_CANCEL -> {
                    button_share.inactive()
                    button_share.setBackgroundColor(resources.getColor(R.color.no_color))
                }

                MotionEvent.ACTION_UP -> {
                    button_share.inactive()
                    button_share.setBackgroundColor(resources.getColor(R.color.no_color))
                    // feature share ...
                }
            }
            true
        }
    }

    fun setItemBottomFuncListener(itemBottomFuncListener: ItemBottomFuncListener?) {
        this.itemBottomFuncListener = itemBottomFuncListener
    }
}
