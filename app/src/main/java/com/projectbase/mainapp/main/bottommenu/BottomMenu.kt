package com.projectbase.mainapp.main.bottommenu

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import com.projectbase.R
import kotlinx.android.synthetic.main.bottom_menu.view.*

class BottomMenu : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    private var onClickBottomMenuListener: OnClickBottomMenuListener? = null
    private var currentItemPosition = 0

    init {
        val inflater = LayoutInflater.from(context).inflate(R.layout.bottom_menu, this)
        inflater.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        for(position in 0 until root_bottom_menu.childCount) {
            val item = root_bottom_menu[position]
            item.setOnClickListener {
                if(currentItemPosition != position) {
                    turnOnItem(position)
                    onClickBottomMenuListener?.onItemClick(position)
                }
            }
        }

        // turn on item home first
        turnOnItem(currentItemPosition)
    }

    fun turnOnItem(position: Int) {
        (root_bottom_menu[currentItemPosition] as ItemBottomMenu).inactive()
        (root_bottom_menu[position] as ItemBottomMenu).active()
        currentItemPosition = position
    }

    fun setOnClickBottomMenuListener(onClickBottomMenuListener: OnClickBottomMenuListener) {
        this.onClickBottomMenuListener = onClickBottomMenuListener
    }
}
