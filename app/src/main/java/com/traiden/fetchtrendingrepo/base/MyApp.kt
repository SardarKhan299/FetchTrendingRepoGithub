package com.traiden.fetchtrendingrepo.base

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.traiden.fetchtrendingrepo.util.SharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(MyApp::class.simpleName, "onCreate: ")
        getAppThemeFromSharedPref()
    }


    private fun getAppThemeFromSharedPref() {
        Log.d(MyApp::class.simpleName, "getAppThemeFromSharedPref: ")
        when (SharedPreferences.getAppTheme(this)) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }
}