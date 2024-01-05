package com.example.exercise_19.di

import com.example.exercise_19.data.repository.UsersRepositoryImpl
import com.example.exercise_19.data.service.UsersService
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
    fun provideUsersRepository(usersService: UsersService) : UsersRepository {
        return UsersRepositoryImpl(usersService = usersService)
    }
}