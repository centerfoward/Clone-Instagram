package com.example.clone_instagram.di

import com.example.clone_instagram.data.respository.AuthRepositoryImpl
import com.example.clone_instagram.data.source.AuthRemoteDataSource
import com.example.clone_instagram.domain.repository.AuthRepository
import com.example.clone_instagram.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        remoteDataSource: AuthRemoteDataSource
    ) : AuthRepository{
        return AuthRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginUsecase(
        repository: AuthRepository
    ) : LoginUseCase{
        return LoginUseCase(repository)
    }
}