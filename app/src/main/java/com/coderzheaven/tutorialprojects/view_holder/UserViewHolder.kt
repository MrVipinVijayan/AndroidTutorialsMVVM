package com.coderzheaven.tutorialprojects.view_holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coderzheaven.tutorialprojects.R

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tvUserName: TextView = view.findViewById(R.id.list_name)
    val tvUserEmail: TextView = view.findViewById(R.id.list_email)

}