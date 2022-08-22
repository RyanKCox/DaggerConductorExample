package com.revature.daggerconductorexample

import android.app.Application
import com.revature.daggerconductorexample.presentation.core.di.AppComponent
import com.revature.daggerconductorexample.presentation.core.di.DaggerAppComponent

class ExampleApp:Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
    fun getAppComponent(): AppComponent = appComponent
}