package com.projectbase.mainapp.main.bottommenu

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import com.projectbase.R
import kotlinx.android.synthetic.main.bottom_menu.view.*

class BottomMenu : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var onClickBottomMenuListener: OnClickBottomMenuListener? = null
    private var currentItem: ItemBottomMenu? = null

    init {
        val inflater = LayoutInflater.from(context).inflate(R.layout.bottom_menu, this)
        inflater.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        for(position in 0 until root_bottom_menu.childCount) {
            val item = root_bottom_menu[position]
            item.setOnClickListener {
                item as ItemBottomMenu
                if(currentItem != item) {
                    activeItem(item)
                    onClickBottomMenuListener?.onItemClick(position)
                }
            }
        }

        // turn on item home first
        activeItem(root_bottom_menu[0] as ItemBottomMenu)
    }

    fun activeItem(item: ItemBottomMenu) {
        currentItem?.inactive()
        item.active()
        currentItem = item
    }

    fun setOnClickBottomMenuListener(onClickBottomMenuListener: OnClickBottomMenuListener) {
        this.onClickBottomMenuListener = onClickBottomMenuListener
    }
}
