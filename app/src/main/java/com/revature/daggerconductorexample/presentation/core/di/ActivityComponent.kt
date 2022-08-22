package com.revature.daggerconductorexample.presentation.core.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.revature.daggerconductorexample.presentation.core.di.scope.PerActivity
import com.revature.daggerconductorexample.presentation.mainmenu.MainMenuController
import com.revature.daggerconductorexample.presentation.displayusers.DisplayUsersController
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [
        NavigatorModule::class
    ])
interface ActivityComponent {

    fun inject(controller:DisplayUsersController)
    fun inject(controller:MainMenuController)

//    @Subcomponent.Builder
//    interface Builder{
//
//        @BindsInstance
//        fun activity(activity:AppCompatActivity):Builder
//        @BindsInstance
//        fun context(context: Context):Builder
//
//        fun build():ActivityComponent
//    }

    //Provides compile time safety
    @Subcomponent.Factory
    interface Factory{
        fun create(
            @BindsInstance  activity: AppCompatActivity,
            @BindsInstance  context:Context
        ):ActivityComponent
    }
}