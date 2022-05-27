package com.projectbase.mainapp.main.home.dailyblog.bottomfunc

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.projectbase.R
import kotlinx.android.synthetic.main.item_bottom_func.view.*

class ItemBottomFunc(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    var isActive = false

    init {
        LayoutInflater.from(context).inflate(R.layout.item_bottom_func, this)

        label_button_func.text = resources.getString(attrs.getAttributeResourceValue(null, "label", 0))
        image_button_func.setImageResource(attrs.getAttributeResourceValue(null, "image", 0))

        setActive(isActive)
    }

    @JvmName("setActive1")
    fun setActive(isActive: Boolean) {
        this.isActive = isActive

        if(isActive) {
            label_button_func.setTextColor(resources.getColor(R.color.blue, null))
            image_button_func.imageTintList = resources.getColorStateList(R.color.blue, null)
        } else {
            label_button_func.setTextColor(resources.getColor(R.color.dark_20, null))
            image_button_func.imageTintList = resources.getColorStateList(R.color.dark_20, null)
        }
    }
}
