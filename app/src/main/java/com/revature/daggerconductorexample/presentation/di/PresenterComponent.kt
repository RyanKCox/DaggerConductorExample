package com.revature.daggerconductorexample.presentation.di

import com.bluelinelabs.conductor.Router
import com.revature.daggerconductorexample.presentation.mainmenu.MainMenuController
import com.revature.daggerconductorexample.presentation.title.DisplayUsersController
import dagger.BindsInstance
import dagger.Component

@Component(modules = [UserRepoModule::class])
interface PresenterComponent {

    fun inject(controller:DisplayUsersController)
    fun inject(controller:MainMenuController)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun provideRouter(router:Router):Builder
        fun build():PresenterComponent
    }
}