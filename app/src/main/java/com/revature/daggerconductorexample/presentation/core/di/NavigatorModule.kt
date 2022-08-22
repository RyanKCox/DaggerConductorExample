package com.revature.daggerconductorexample.presentation.core.di

import com.revature.daggerconductorexample.presentation.core.di.scope.PerActivity
import com.revature.daggerconductorexample.presentation.core.navigator.MainNavigator
import com.revature.daggerconductorexample.presentation.core.navigator.Navigator
import dagger.Binds
import dagger.Module

@Module
abstract class NavigatorModule {
    @PerActivity
    @Binds
    abstract fun bindNavigator(nav:MainNavigator):Navigator
}