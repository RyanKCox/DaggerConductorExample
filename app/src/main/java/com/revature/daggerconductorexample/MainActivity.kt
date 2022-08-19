package com.revature.daggerconductorexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.revature.daggerconductorexample.databinding.ActivityMainBinding
import com.revature.daggerconductorexample.presentation.di.ActivityComponent
import com.revature.daggerconductorexample.presentation.di.DaggerActivityComponent
import com.revature.daggerconductorexample.presentation.mainmenu.MainMenuController

class MainActivity : AppCompatActivity() {

    private lateinit var router:Router

    private lateinit var activityComponent:ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        router = Conductor.attachRouter(
            this,
            binding.controllerContainer,
            savedInstanceState)

        activityComponent = DaggerActivityComponent
            .builder()
            .appComponent((application as ExampleApp).getAppComponent())
            .router(router)
            .build()

        if(!router.hasRootController()){
            router.setRoot(RouterTransaction.with(MainMenuController()))
        }

    }
    fun getActivityComponent() = activityComponent

    override fun onBackPressed() {
        if(!router.handleBack())
            super.onBackPressed()
    }
}