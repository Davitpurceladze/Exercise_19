package com.example.exercise_19.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise_19.data.common.Resource
import com.example.exercise_19.domain.users.UsersRepository
import com.example.exercise_19.domain.users.UsersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val usersRepository: UsersRepository) :
    ViewModel() {

    private val _successFlow =
        MutableStateFlow<Resource<List<UsersResponse>>>(Resource.Success(value = emptyList()))
    val successFlow: StateFlow<Resource<List<UsersResponse>>> = _successFlow.asStateFlow()

    init {
        viewModelScope.launch {
            usersRepository.getUsersList().collect {
                _successFlow.value = it
            }
        }
    }

    sealed class UsersFragmentNavigationEvents{
        object NavigateToUserDetails: UsersFragmentNavigationEvents()
    }

}