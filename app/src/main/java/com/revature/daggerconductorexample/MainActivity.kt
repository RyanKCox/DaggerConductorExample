package com.revature.daggerconductorexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.revature.daggerconductorexample.databinding.ActivityMainBinding
import com.revature.daggerconductorexample.presentation.mainmenu.MainMenuController
import com.revature.daggerconductorexample.presentation.title.DisplayUsersController

class MainActivity : AppCompatActivity() {

    private lateinit var router:Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        router = Conductor.attachRouter(
            this,
            binding.controllerContainer,
            savedInstanceState)
        if(!router.hasRootController()){
            router.setRoot(RouterTransaction.with(MainMenuController()))
        }
    }

    override fun onBackPressed() {
        if(!router.handleBack())
            super.onBackPressed()
    }
}