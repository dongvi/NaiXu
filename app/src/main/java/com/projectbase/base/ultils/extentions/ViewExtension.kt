package com.projectbase.base.ultils.extentions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

fun View.setAnim(context: Context?, anim: Int, duration: Long) {
    this.animation = AnimationUtils.loadAnimation(context, anim)
    this.animation.duration = duration
}

fun ImageView.setImageFromAttrRs(attrs: AttributeSet, attr: String) {
    this.setImageResource(attrs.getAttributeResourceValue(null, attr, 0))
}

fun TextView.setTextFromAttrRs(attrs: AttributeSet, attr: String) {
    this.text = resources.getString(attrs.getAttributeResourceValue(null, attr, 0))
}
