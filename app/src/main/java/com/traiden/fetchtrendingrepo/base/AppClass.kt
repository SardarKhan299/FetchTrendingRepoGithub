package com.traiden.fetchtrendingrepo.base

import android.app.Application

class AppClass : Application() {

    companion object {
        var isLoading: Boolean = false
    }
}