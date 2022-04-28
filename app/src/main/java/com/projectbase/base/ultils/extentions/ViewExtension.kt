package com.projectbase.base.ultils.extentions

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils

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
