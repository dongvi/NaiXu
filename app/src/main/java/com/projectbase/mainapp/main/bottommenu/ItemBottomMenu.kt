package com.projectbase.mainapp.main.bottommenu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.setPadding
import com.projectbase.R
import com.projectbase.base.ultils.extentions.*
import kotlinx.android.synthetic.main.item_bottom_menu.view.*

class ItemBottomMenu(context: Context?, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var imgOn: Int = 0
    private var imgOff: Int = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.item_bottom_menu, this)

        item_btm_tv.setTextFromAttrRs(attrs, "label")
        item_btm_tv.setTextColor(resources.getColor(R.color.item_btm))
        imgOn = attrs.getAttributeResourceValue(null, "img_on", 0)
        imgOff = attrs.getAttributeResourceValue(null, "img_off", 0)

        inactive()
    }

    fun active() {
        item_btm_img.setImageResource(imgOn)
        item_btm_tv.visible()
        root_item_btm.setPadding(0)
    }

    fun inactive() {
        item_btm_img.setImageResource(imgOff)
        item_btm_tv.gone()
        root_item_btm.setPadding(20)
    }
}
