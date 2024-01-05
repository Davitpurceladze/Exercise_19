package com.example.exercise_19.presentation.users

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.exercise_19.databinding.FragmentUsersBinding
import com.example.exercise_19.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {

    private val viewModel: UsersViewModel by viewModels()

    override fun bind() {
        getUser()
        submitRecyclerData()
    }

    private fun submitRecyclerData() {
        val adapter = UsersListRecyclerAdapter()

        binding.usersListRecycler
    }

    private fun getUser() {
        binding.btnGetUsers.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.getUsers()
                    viewModel.successFlow.collect{
                        println("this is fragment file -> ${it.data}")
                        val adapter = UsersListRecyclerAdapter()
                        adapter.submitList(it.data)
                        binding.usersListRecycler.adapter = adapter
                    }

                }
            }
        }
    }
}