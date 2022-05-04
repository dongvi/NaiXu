package com.projectbase.mainapp.main.bottommenu

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.core.view.get
import com.projectbase.R
import kotlinx.android.synthetic.main.bottom_menu.view.*

@SuppressLint("ClickableViewAccessibility")
class BottomMenu(context: Context?, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private lateinit var onClickBottomMenuListener: OnClickBottomMenuListener
    private var currentItemPosition = 0

    init {
        val inflater = LayoutInflater.from(context).inflate(R.layout.bottom_menu, this)
        inflater.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        for(position in 0 until root_bottom_menu.childCount) {
            val item = root_bottom_menu[position]
            item.setOnTouchListener { _, motionEvent ->
                when(motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        turnOnItem(position)
                        onClickBottomMenuListener.onItemClick(position)
                    }
                    MotionEvent.ACTION_UP -> {}
                }
                true
            }
        }

        // turn on item home first
        turnOnItem(currentItemPosition)
    }

    fun turnOnItem(position: Int) {
        if(currentItemPosition != position) {
            (root_bottom_menu[currentItemPosition] as ItemBottomMenu).inactive()
            currentItemPosition = position
        }

        (root_bottom_menu[position] as ItemBottomMenu).active()
    }

    @JvmName("setOnClickBottomMenuListener1")
    fun setOnClickBottomMenuListener(onClickBottomMenuListener: OnClickBottomMenuListener) {
        this.onClickBottomMenuListener = onClickBottomMenuListener
    }
}
