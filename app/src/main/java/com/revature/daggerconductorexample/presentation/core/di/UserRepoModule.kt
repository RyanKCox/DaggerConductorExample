package com.revature.daggerconductorexample.presentation.core.di

import com.revature.daggerconductorexample.domain.UserDataRepository
import com.revature.daggerconductorexample.domain.UserRepository
import com.revature.daggerconductorexample.presentation.core.di.scope.PerActivity
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UserRepoModule {

    @Singleton
    @Binds
    abstract fun bindUserRepo(userRepo:UserDataRepository):UserRepository
}