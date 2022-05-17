package com.projectbase.base.ultils.extentions

import android.view.View

fun View.setHidden(isHidden: Boolean) {
    this.visibility = if(isHidden) View.GONE else View.VISIBLE
}