package com.example.exercise_19.di

import com.example.exercise_19.data.common.HandleResponse
import com.example.exercise_19.data.repository.userDetails.UserDetailsRepositoryImpl
import com.example.exercise_19.data.repository.users.UsersRepositoryImpl
import com.example.exercise_19.data.service.users.UsersService
import com.example.exercise_19.data.service.usersDetails.UserDetailsService
import com.example.exercise_19.domain.userDetails.UserDetailsRepository
import com.example.exercise_19.domain.users.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUsersRepository(usersService: UsersService, handleResponse: HandleResponse) : UsersRepository {
        return UsersRepositoryImpl(usersService = usersService, handleResponse = handleResponse)
    }

    @Singleton
    @Provides
    fun provideHandleResponse() : HandleResponse {
        return HandleResponse()
    }

    @Singleton
    @Provides
    fun provideUserDetailsRepository(userDetailsService: UserDetailsService, handleResponse: HandleResponse):UserDetailsRepository {
        return UserDetailsRepositoryImpl(userDetailsService, handleResponse)
    }



}