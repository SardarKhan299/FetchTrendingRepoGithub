package com.traiden.fetchtrendingrepo.base

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.traiden.fetchtrendingrepo.R
import com.traiden.fetchtrendingrepo.util.SharedPreferences

abstract class BaseActivity(@LayoutRes private val layoutResId : Int) : AppCompatActivity() {

    protected var mPrevConfig: Configuration? = null
    // Binding object
    private lateinit var binding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(BaseActivity::class.simpleName, "onCreate: ")
        mPrevConfig = Configuration(resources.configuration)
        // Inflate the layout using data binding
        binding = DataBindingUtil.setContentView(this, layoutResId)

        // Perform additional setup or initialization
        setupViews()
    }

    // Abstract method to be implemented by subclasses
    abstract fun setupViews()

    // Get the binding object
    fun getBinding(): ViewDataBinding {
        return binding
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        configurationChanged(newConfig)
        mPrevConfig = Configuration(newConfig)
    }

    protected open fun configurationChanged(newConfig: Configuration) {
        when (SharedPreferences.getAppTheme(this)) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> {
                if(isNightConfigChanged(newConfig)){
                    Log.d(BaseActivity::class.simpleName, "configurationChanged: Mode Changed")
                    if(isOnDarkMode(newConfig)){
                        Log.d(BaseActivity::class.simpleName, "configurationChanged: Dark Mode")
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }else{
                        Log.d(BaseActivity::class.simpleName, "configurationChanged: Light Mode")
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }else{
                    Log.d(BaseActivity::class.simpleName, "configurationChanged: Mode Not Changed")
                    if(isOnDarkMode(newConfig)){
                        Log.d(BaseActivity::class.simpleName, "configurationChanged: Dark Mode")
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }else{
                        Log.d(BaseActivity::class.simpleName, "configurationChanged: Light Mode")
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
            }
        }
        Log.d(BaseActivity::class.simpleName, "configurationChanged: Recreate")
        recreate()

    }


    protected open fun isNightConfigChanged(newConfig: Configuration): Boolean {
        return newConfig.diff(mPrevConfig) and
                ActivityInfo.CONFIG_UI_MODE != 0 && isOnDarkMode(newConfig)!= isOnDarkMode(mPrevConfig!!)
    }

    open fun isOnDarkMode(configuration: Configuration): Boolean {
        return configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }



}