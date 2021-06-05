package com.coderzheaven.tutorialprojects.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coderzheaven.tutorialprojects.R
import com.coderzheaven.tutorialprojects.models.User

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mListRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnLoad: Button
    var mAdapter: ListAdapter? = null
    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mListRecyclerView = findViewById(R.id.list_recycler_view)
        btnLoad = findViewById(R.id.btnLoad)
        progressBar = findViewById(R.id.progress)

        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        viewModel.users.observe(this, Observer(onUsersListReceived()))
        viewModel.loading.observe(this, Observer(onLoading()))
        viewModel.getUsers()

        btnLoad.setOnClickListener(this)
    }

    private fun onUsersListReceived() = { users: List<User> ->
        setList(users = users)
    }

    private fun onLoading() = { loading: Boolean ->
        progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun setList(users: List<User>) {

        if (null != mAdapter) {
            mAdapter?.notifyDataSetChanged()
            return
        }

        mListRecyclerView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            mAdapter = ListAdapter(users)
        }
    }

    override fun onClick(v: View?) {
        if (v!!.id == R.id.btnLoad) {
            viewModel.getUsers()
        }
    }
}