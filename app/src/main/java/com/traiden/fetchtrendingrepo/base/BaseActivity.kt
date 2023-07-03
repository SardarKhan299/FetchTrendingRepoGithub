package com.traiden.fetchtrendingrepo.base

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

abstract class BaseActivity : AppCompatActivity() {

    protected var mPrevConfig: Configuration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(BaseActivity::class.simpleName, "onCreate: ")
        mPrevConfig = Configuration(resources.configuration)
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mPrevConfig = Configuration(newConfig)
    }


    protected open fun isNightConfigChanged(newConfig: Configuration): Boolean {
        return newConfig.diff(mPrevConfig) and
                ActivityInfo.CONFIG_UI_MODE != 0 && isOnDarkMode(newConfig)!= isOnDarkMode(mPrevConfig!!)
    }

    open fun isOnDarkMode(configuration: Configuration): Boolean {
        return configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }



}