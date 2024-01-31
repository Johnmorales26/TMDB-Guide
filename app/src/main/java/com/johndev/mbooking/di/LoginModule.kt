package com.johndev.mbooking.di

import com.johndev.mbooking.data.datasource.LoginService
import com.johndev.mbooking.data.remote.LoginServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class RemoteData

@InstallIn(SingletonComponent::class)
@Module
abstract class LoginModule {

    @RemoteData
    @Singleton
    @Binds
    abstract fun bindLoginService(impl: LoginServiceImpl): LoginService

}