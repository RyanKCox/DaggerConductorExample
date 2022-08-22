package com.revature.daggerconductorexample.presentation.core.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        UserRepoModule::class
    ]
)
interface AppComponent {

    fun getActivityComponentFactory():ActivityComponent.Factory
}