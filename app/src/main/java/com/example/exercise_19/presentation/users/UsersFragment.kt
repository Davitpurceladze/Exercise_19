package com.example.exercise_19.presentation.users

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.exercise_19.data.common.Resource
import com.example.exercise_19.databinding.FragmentUsersBinding
import com.example.exercise_19.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {

    private val viewModel: UsersViewModel by viewModels()
    private val usersListRecyclerAdapter: UsersListRecyclerAdapter by lazy { UsersListRecyclerAdapter() }

    override fun bind() {
        bindRecycler()
    }

    override fun bindObserved() {
        collectData()
        itemClickListener()

    }

    private fun itemClickListener() {
        usersListRecyclerAdapter.setOnItemClickListener {
            println("this is items ID $it")
            navigateToUserDetailFragment(it)
        }
    }

    private fun navigateToUserDetailFragment(id: Int) {

        findNavController().navigate(UsersFragmentDirections.actionUsersFragmentToUserDetails(id = id))

    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.successFlow.collect{
                    when (it) {
                        is Resource.Success -> usersListRecyclerAdapter.submitList(it.data)
                        is Resource.Error -> {}
                        is Resource.Loading -> {}
                    }
                }
            }
        }
    }

//    private fun showProgressBarAndHideRecyclerView() {
//        with(binding){
//            progressBar.visibility = View.VISIBLE
//            usersListRecyclerView.visibility = View.GONE
//        }
//    }
//
//    private fun hideProgressBarAndShowRecyclerView() {
//        with(binding){
//            progressBar.visibility = View.GONE
//            usersListRecyclerView.visibility = View.VISIBLE
//        }
//    }

//    private fun loading(isLoading:Boolean){
//        lifecycleScope.launch {
//            binding.apply {
//                if(isLoading){
//                    progressBar.visibility = View.VISIBLE
//                }else{
//                    progressBar.visibility = View.GONE
//                }
//            }
//        }
//    }

    private fun bindRecycler() {
        binding.usersListRecycler.apply {
              adapter = usersListRecyclerAdapter

        }
    }


//    private fun getUser() {
//        binding.btnGetUsers.setOnClickListener {
//            viewLifecycleOwner.lifecycleScope.launch {
//                repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    viewModel.getUsers()
//
//                }
//            }
//        }
//        loadData()
//    }

//    private fun loadData() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.successFlow.collect {
//                println("this is fragment file -> ${it.data}")
//                val adapter = UsersListRecyclerAdapter()
//                adapter.submitList(it.data)
//                binding.usersListRecycler.adapter = adapter
//            }
//        }
//    }
}