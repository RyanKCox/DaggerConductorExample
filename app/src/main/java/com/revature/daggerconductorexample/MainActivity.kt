package com.revature.daggerconductorexample

import android.os.Bundle
import android.view.ViewGroup
import com.bluelinelabs.conductor.Router
import com.revature.daggerconductorexample.databinding.ActivityMainBinding
import com.revature.daggerconductorexample.presentation.core.activity.BaseActivity
import com.revature.daggerconductorexample.presentation.core.di.ActivityComponent
import com.revature.daggerconductorexample.presentation.mainmenu.MainMenuController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {


    private lateinit var activityComponent:ActivityComponent
    private lateinit var container:ViewGroup
    private val coroutineScope = CoroutineScope(Main+ Job())

    val mainControllerChildRouter: Router?
        get() {
            return if(router != null)
                router
            else
                null
//            val mainController = router!!
//                .getControllerWithTag(
//                MainMenuController::class.java.simpleName)
//            return if(mainController != null
//                && mainController.childRouters.size >0
//                && mainController.childRouters[0] != null){
//                mainController.childRouters[0]
//            } else null
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent = (application as ExampleApp)
            .getAppComponent()
            .getActivityComponentFactory().create(this,this)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        container = binding.controllerContainer

        setContentView(view)

        setupRouter(container,savedInstanceState)
        coroutineScope.launch {
            setupRootController(MainMenuController::class.java)
        }



    }
    fun getActivityComponent() = activityComponent
}