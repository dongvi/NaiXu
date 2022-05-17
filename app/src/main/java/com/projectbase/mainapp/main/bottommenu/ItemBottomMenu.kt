package com.projectbase.mainapp.main.bottommenu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.projectbase.R
import com.projectbase.base.ultils.extentions.*
import kotlinx.android.synthetic.main.item_bottom_menu.view.*

class ItemBottomMenu(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private var imageActive: Int = 0
    private var imageInactive: Int = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.item_bottom_menu, this)

        item_btm_tv.text = resources.getString(attrs.getAttributeResourceValue(null, "label", 0))
        item_btm_tv.setTextColor(resources.getColor(R.color.item_btm))
        imageActive = attrs.getAttributeResourceValue(null, "image_active", 0)
        imageInactive = attrs.getAttributeResourceValue(null, "image_inactive", 0)

        inactive()
    }

    fun active() {
        item_btm_img.setImageResource(imageActive)
        item_btm_tv.setHidden(false)
    }

    fun inactive() {
        item_btm_img.setImageResource(imageInactive)
        item_btm_tv.setHidden(true)
    }
}
