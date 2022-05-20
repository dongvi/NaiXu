package com.projectbase.base.ultils.extentions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.setHidden(isHidden: Boolean) {
    this.visibility = if(isHidden) View.GONE else View.VISIBLE
}
