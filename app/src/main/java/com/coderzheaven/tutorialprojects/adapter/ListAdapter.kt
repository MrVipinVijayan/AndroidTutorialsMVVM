package com.coderzheaven.tutorialprojects.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coderzheaven.tutorialprojects.R
import com.coderzheaven.tutorialprojects.models.User
import com.coderzheaven.tutorialprojects.view_holder.UserViewHolder

class ListAdapter(private val list: List<User>) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: User = list[position]
        holder.tvUserName.text = user.name
        holder.tvUserEmail.text = user.email
    }

    override fun getItemCount(): Int = list.size

}


