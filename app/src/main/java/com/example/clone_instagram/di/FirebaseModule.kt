package com.example.clone_instagram.di

import com.example.clone_instagram.data.respository.UserRepositoryImpl
import com.example.clone_instagram.data.source.FirebaseRemoteDataSource
import com.example.clone_instagram.domain.repository.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun proviceFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance() // FirebaseAuth 의존성 주입

    @Provides
    @Singleton
    fun provideFirebaseStore() : FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideUserRepositoryImpl(remote : FirebaseRemoteDataSource) : UserRepository{
        return UserRepositoryImpl(remote)
    }

}