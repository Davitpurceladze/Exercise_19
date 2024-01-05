package com.example.exercise_19.di

import com.example.exercise_19.data.service.UsersService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModel {

    private const val USERS_LIST_URL = "https://run.mocky.io/"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private const val DETAILED_USER_URL = "https://reqres.in/api/"

    @Singleton
    @Provides
    @UsersListRetrofitClient
    fun provideUsersListRetrofitClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(USERS_LIST_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    @DetailedUserRetrofitClient
    fun provideDetailedUserRetrofitClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(DETAILED_USER_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideGetUsersService(@UsersListRetrofitClient retrofit: Retrofit) : UsersService {
        return retrofit.create(UsersService::class.java)
    }


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UsersListRetrofitClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DetailedUserRetrofitClient
}