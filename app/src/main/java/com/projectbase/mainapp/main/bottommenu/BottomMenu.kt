package com.projectbase.mainapp.main.bottommenu

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.core.view.iterator
import com.projectbase.R
import kotlinx.android.synthetic.main.bottom_menu.view.*

@SuppressLint("ClickableViewAccessibility")
class BottomMenu(context: Context?, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private lateinit var onClickBottomMenuListener: OnClickBottomMenuListener

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

        // turn on button home first
        turnOnItem(0)
    }

    private fun turnOffAllItem() {
        for(position in 0 until root_bottom_menu.childCount)
            (root_bottom_menu[position] as ItemBottomMenu).off()
    }

    fun turnOnItem(position: Int) {
        turnOffAllItem()
        (root_bottom_menu[position] as ItemBottomMenu).on()
    }

    @JvmName("setOnClickBottomMenuListener1")
    fun setOnClickBottomMenuListener(onClickBottomMenuListener: OnClickBottomMenuListener) {
        this.onClickBottomMenuListener = onClickBottomMenuListener
    }
}