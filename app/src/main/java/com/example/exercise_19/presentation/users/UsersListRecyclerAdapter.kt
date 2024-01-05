package com.example.exercise_19.presentation.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_19.databinding.UsersListItemBinding
import com.example.exercise_19.domain.users.UsersResponse

class UsersListRecyclerAdapter: ListAdapter<UsersResponse, UsersListRecyclerAdapter.UserViewHolder>(DiffUtilCallback()) {

    inner class UserViewHolder(private val binding: UsersListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = currentList[adapterPosition]
            binding.tvFirstName.text = item.firstName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UsersListItemBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }

    private class DiffUtilCallback : DiffUtil.ItemCallback<UsersResponse>() {
        override fun areItemsTheSame(
            oldItem: UsersResponse,
            newItem: UsersResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UsersResponse,
            newItem: UsersResponse
        ): Boolean {
            return oldItem == newItem
        }
    }
}