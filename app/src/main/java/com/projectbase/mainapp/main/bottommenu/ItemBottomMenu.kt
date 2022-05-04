package com.projectbase.mainapp.main.bottommenu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.setPadding
import com.projectbase.R
import com.projectbase.base.ultils.extentions.*
import kotlinx.android.synthetic.main.item_bottom_menu.view.*

class ItemBottomMenu(context: Context?, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_bottom_menu, this)

        item_btm_img_on.setImageFromAttrRs(attrs, "img_on")
        item_btm_img_off.setImageFromAttrRs(attrs, "img_off")
        item_btm_tv.setTextFromAttrRs(attrs, "tv")
        item_btm_tv.setColor(R.color.item_btm)
    }

    fun on() {
        item_btm_img_on.visible()
        item_btm_img_off.invisible()
        item_btm_tv.visible()
        root_item_btm.setPadding(0)
    }

    fun off() {
        item_btm_img_on.invisible()
        item_btm_img_off.visible()
        item_btm_tv.gone()
        root_item_btm.setPadding(10)
    }
}