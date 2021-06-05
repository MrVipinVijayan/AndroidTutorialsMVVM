package com.coderzheaven.tutorialprojects.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coderzheaven.tutorialprojects.R

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val textView: TextView = view.findViewById(R.id.list_title)

}