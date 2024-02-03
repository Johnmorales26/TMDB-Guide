package com.johndev.mbooking.di

import com.johndev.mbooking.data.datasource.MoviesService
import com.johndev.mbooking.data.remote.MovieServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class MovieModule {

    @RemoteData
    @Singleton
    @Binds
    abstract fun bindMovieService(impl: MovieServiceImpl): MoviesService

}