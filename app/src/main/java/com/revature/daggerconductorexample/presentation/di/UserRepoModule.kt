package com.revature.daggerconductorexample.presentation.di

import com.revature.daggerconductorexample.domain.UserDataRepository
import com.revature.daggerconductorexample.domain.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class UserRepoModule {

    @Binds
    abstract fun bindUserRepo(userRepo:UserDataRepository):UserRepository
}