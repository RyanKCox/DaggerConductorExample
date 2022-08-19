package com.revature.daggerconductorexample.presentation.di

import com.bluelinelabs.conductor.Router
import com.revature.daggerconductorexample.presentation.di.scope.AppComponent
import com.revature.daggerconductorexample.presentation.di.scope.PerActivity
import com.revature.daggerconductorexample.presentation.mainmenu.MainMenuController
import com.revature.daggerconductorexample.presentation.displayusers.DisplayUsersController
import dagger.BindsInstance
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class],
    modules = [UserRepoModule::class])
interface ActivityComponent {

    fun inject(controller:DisplayUsersController)
    fun inject(controller:MainMenuController)

    @Component.Builder
    interface Builder{
        fun appComponent(appComponent: AppComponent):Builder

        @BindsInstance
        fun router(router:Router):Builder

        fun build():ActivityComponent
    }
}