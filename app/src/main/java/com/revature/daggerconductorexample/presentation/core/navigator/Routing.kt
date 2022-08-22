package com.revature.daggerconductorexample.presentation.core.navigator

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction


fun <C: Controller> routerTransaction(
    controllerClass:Class<C>,
    changeHandler: ControllerChangeHandler? = null
): RouterTransaction = routerTransaction(
    controllerClass.newInstance(),
    changeHandler)

fun routerTransaction(
    controller: Controller,
    changeHandler: ControllerChangeHandler? = null,
    tag: String? = null
): RouterTransaction =
    RouterTransaction.with(controller)
        .pushChangeHandler(changeHandler)
        .popChangeHandler(changeHandler)
        .tag(tag)

val Router.rootController get() = rootTransaction?.controller
val Router.rootTransaction get() = backstack.firstOrNull()