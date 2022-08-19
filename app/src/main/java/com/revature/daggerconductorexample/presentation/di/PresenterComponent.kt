package com.revature.daggerconductorexample.presentation.di

import com.revature.daggerconductorexample.presentation.title.TitleController
import dagger.Component

@Component(modules = [UserRepoModule::class])
interface PresenterComponent {

    fun inject(controller:TitleController)
}