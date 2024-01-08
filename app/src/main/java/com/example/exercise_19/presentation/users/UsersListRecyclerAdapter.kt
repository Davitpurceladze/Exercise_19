package com.example.exercise_19.presentation.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercise_19.databinding.UsersListItemBinding
import com.example.exercise_19.domain.users.UsersResponse

class UsersListRecyclerAdapter: ListAdapter<UsersResponse, UsersListRecyclerAdapter.UserViewHolder>(DiffUtilCallback()) {

    private var onItemClickListener: ((id: Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (id: Int) -> Unit)  {
        onItemClickListener = listener
    }
    inner class UserViewHolder(private val binding: UsersListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val item = currentList[adapterPosition]
            with(binding) {
                tvFirstName.text = item.firstName
                tvLastName.text = item.lastName
                tvEmail.text = item.email
                root.setOnClickListener {
                    onItemClickListener?.invoke(item.id)
                }
            }
            setAvatarImg()

        }
        private fun setAvatarImg() {
            val item = currentList[adapterPosition]
            Glide.with(this.itemView)
                .load(item.avatar)
                .into(binding.imgAvatar)
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