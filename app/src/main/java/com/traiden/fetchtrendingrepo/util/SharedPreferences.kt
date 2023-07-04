package com.traiden.fetchtrendingrepo.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.preference.PreferenceManager
import com.traiden.fetchtrendingrepo.util.ApplicationConstants.Companion.APP_THEME

class SharedPreferences {
    companion object {
        var mSharedPreferences: SharedPreferences? = null

        private fun initShardPreference(context: Context): SharedPreferences? {
            if (mSharedPreferences == null) {
                mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            }
            return mSharedPreferences
        }

        fun saveAppTheme(context: Context, appTheme: Int) {
            Log.d(SharedPreferences::class.simpleName, "saveAppTheme: ")
            val editor: SharedPreferences.Editor = initShardPreference(context)!!.edit()
            editor.putInt(APP_THEME, appTheme)
            editor.apply()
        }

        fun getAppTheme(context: Context): Int {
            val msharedPreferences: SharedPreferences? = initShardPreference(context)
            if (msharedPreferences != null) {
                return msharedPreferences.getInt(APP_THEME, -1)
            }
            return -1
        }
    }
}