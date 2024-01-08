package com.example.exercise_19.presentation.userDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise_19.data.common.Resource
import com.example.exercise_19.domain.userDetails.UserDetailsRepository
import com.example.exercise_19.domain.userDetails.UserDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(val userDetailsRepository: UserDetailsRepository): ViewModel() {
    private val _successFlow = MutableStateFlow<Resource<UserDetailsResponse>>(Resource.Success(null))
      val successFlow: StateFlow<Resource<UserDetailsResponse>> = _successFlow.asStateFlow()

    fun getUserDetails(id: Int) {
        viewModelScope.launch {
            userDetailsRepository.getUserDetails(id).collect{
                _successFlow.value = it
            }
        }
    }

}