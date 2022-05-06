package com.projectbase.mainapp.main.bottommenu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import com.projectbase.R
import com.projectbase.base.ultils.extentions.*
import kotlinx.android.synthetic.main.item_bottom_menu.view.*

class ItemBottomMenu(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private var imageActive: Int = 0
    private var imageInactive: Int = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.item_bottom_menu, this)

        item_btm_tv.setTextFromAttrRs(attrs, "label")
        item_btm_tv.setTextColor(resources.getColor(R.color.item_btm))
        imageActive = attrs.getAttributeResourceValue(null, "image_active", 0)
        imageInactive = attrs.getAttributeResourceValue(null, "image_inactive", 0)

        inactive()
    }

    fun active() {
        item_btm_img.setImageResource(imageActive)
        item_btm_tv.visible()
    }

    fun inactive() {
        item_btm_img.setImageResource(imageInactive)
        item_btm_tv.gone()
    }
}
