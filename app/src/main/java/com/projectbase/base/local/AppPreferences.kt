package com.projectbase.base.local

import android.content.Context

class AppPreferences(private val context: Context) {

    companion object {
        const val APP_SHARED_PREFS_NAME = "app_preference"

        const val KEY_EXAMPLE = "key_example"
    }

    private val sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun getExample(): String? {
        return sharedPreferences.getString(KEY_EXAMPLE, "")
    }

    fun setExample(example: String) {
        sharedPreferences.edit().putString(KEY_EXAMPLE, example).apply()
    }
}
