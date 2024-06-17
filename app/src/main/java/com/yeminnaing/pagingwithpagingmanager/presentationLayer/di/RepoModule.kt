package com.yeminnaing.pagingwithpagingmanager.presentationLayer.di

import com.yeminnaing.pagingwithpagingmanager.dataLayer.repositories.GetMovieRepoImpl
import com.yeminnaing.pagingwithpagingmanager.domainLayer.repositories.GetMovieRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindGetMovieRepo(
      getMovieRepoImpl: GetMovieRepoImpl
    ):GetMovieRepo
}