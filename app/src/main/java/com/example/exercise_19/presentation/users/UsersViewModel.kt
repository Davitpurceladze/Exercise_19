package com.example.exercise_19.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise_19.data.common.Resource
import com.example.exercise_19.domain.users.UsersRepository
import com.example.exercise_19.domain.users.UsersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(val usersRepository: UsersRepository) : ViewModel() {

    private val _successFlow = MutableStateFlow<Resource<List<UsersResponse>>>(Resource.Success(value = null))
    val successFlow = _successFlow.asStateFlow()

    fun getUsers() {
        viewModelScope.launch {
            usersRepository.getUsersList().collect {

                println("this is UsersViewModel file -> ${it.data}")
                _successFlow.value = Resource.Success(it.data)
            }
        }
    }
}