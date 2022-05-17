package com.projectbase.mainapp.main.home.dailyblog.bottomfunc

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.projectbase.R
import kotlinx.android.synthetic.main.item_bottom_func.view.*

class ItemBottomFunc(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_bottom_func, this)

        label_button_func.text = resources.getString(attrs.getAttributeResourceValue(null, "label", 0))
        image_button_func.setImageResource(attrs.getAttributeResourceValue(null, "image", 0))

        inactive()
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    fun active() {
        label_button_func.setTextColor(resources.getColor(R.color.blue))
        image_button_func.imageTintList = resources.getColorStateList(R.color.blue)
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    fun inactive() {
        label_button_func.setTextColor(resources.getColor(R.color.dark_20))
        image_button_func.imageTintList = resources.getColorStateList(R.color.dark_20)
    }
}