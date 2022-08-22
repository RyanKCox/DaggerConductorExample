package com.revature.daggerconductorexample.presentation.core.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import io.reactivex.exceptions.Exceptions

open class BaseActivity: AppCompatActivity() {

    protected var router: Router? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        onCreating(savedInstanceState)
//    }
//    private fun onCreating(savedInstanceState:Bundle?){
//
//    }
    protected fun setupRouter(container:ViewGroup,savedInstanceState: Bundle?){
        router = Conductor.attachRouter(this,container,savedInstanceState)
    }

    protected open fun setupRootController(
        rootControllerClazz: Class<out Controller>) {
        router?.let {
            if(!router!!.hasRootController()){
                try{
                    router!!.setRoot(RouterTransaction.with(rootControllerClazz.newInstance()))
                } catch (e:Exception){
                    throw Exceptions.propagate(e)
                }
            }
        }
    }
    protected fun setupRootController(controller:Controller){
        router?.let {
            if(!router!!.hasRootController())
                router!!.setRoot(RouterTransaction.with(controller))
        }
    }

    override fun onBackPressed() {
        val handleBack:Boolean = router!!.handleBack()
        if(handleBack)
            return
        super.onBackPressed()
    }
}