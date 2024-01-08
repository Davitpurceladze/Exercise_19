package com.example.exercise_19.presentation.userDetails

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.exercise_19.data.common.Resource
import com.example.exercise_19.databinding.FragmentUserDetailsBinding
import com.example.exercise_19.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetails : BaseFragment<FragmentUserDetailsBinding>(FragmentUserDetailsBinding::inflate) {

    private val viewModel: UserDetailsViewModel by viewModels()
    private val args: UserDetailsArgs by navArgs()

    override fun bind() {
        println(" this is userDetail fragment -> ${args.id}")
        getUserDetails(args.id)
    }

    private fun getUserDetails(id: Int) {
        viewModel.getUserDetails(id)
    }

    override fun bindObserved() {
        collectData()
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.successFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            println("this is fragment -> $it")
                            with(binding) {
                                tvFirstName.text = it.value?.data?.firstName
                                tvLastName.text = it.value?.data?.lastName
                                tvEmail.text = it.value?.data?.email


                            }
                            Glide.with(requireView())
                                .load(it.value?.data?.avatar)
                                .into(binding.imgAvatar)

                        }

                        is Resource.Error -> {}
                        is Resource.Loading -> {}
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }
    }
}