package com.revature.daggerconductorexample.presentation.core.navigator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.revature.daggerconductorexample.MainActivity
import com.revature.daggerconductorexample.presentation.displayusers.DisplayUsersController
import com.revature.daggerconductorexample.presentation.mainmenu.MainMenuController
import javax.inject.Inject

enum class NavigationAction{
    MainMenu,
    DisplayUsers
}

interface Navigator{

    fun navigateTo(
        navigationAction: NavigationAction?,
        action:String="",
        options:String="")
    fun backFromChild()
}

class MainNavigator @Inject constructor(
    private val context: Context,
    private val activity:AppCompatActivity
    ):Navigator{

    private fun getRouter():Router?{
       val router = (activity as MainActivity).mainControllerChildRouter
        return router
    }

    private fun push(
        newController: Controller,
        changeHandler: ControllerChangeHandler = HorizontalChangeHandler(),
        tag: String = newController::class.java.simpleName
    ){
        val (controllerToBePushed, tagToBeUsed) =
            Pair(newController,tag)

        val backStack = getRouter()?.backstack
        val newBackStack = backStack?.filterIndexed{ index, transaction->
            ((index>=1 && transaction.tag() == tagToBeUsed)
                    || (backStack.size == 1 && transaction.tag() == tagToBeUsed))
                .not()
        }?.toMutableList()
        newBackStack?.let {
            if(it.size > 1 && it[0].tag() == it[1].tag()){
                it.removeAt(1)
            }
        }
        newBackStack?.add(routerTransaction(controllerToBePushed,changeHandler,tagToBeUsed))
        newBackStack?.let { getRouter()?.setBackstack(it,changeHandler) }
    }

    override fun navigateTo(navigationAction: NavigationAction?, action: String, options: String) {
        when(navigationAction){
            NavigationAction.MainMenu -> mainMenu()
            NavigationAction.DisplayUsers -> displayUsers()
            else -> TODO()
        }
    }
    private fun mainMenu() = push(MainMenuController())
    private fun displayUsers() = push(DisplayUsersController())

    override fun backFromChild() {
        TODO("Not yet implemented")
    }
}