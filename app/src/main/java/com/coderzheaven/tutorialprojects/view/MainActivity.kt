package com.coderzheaven.tutorialprojects.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coderzheaven.tutorialprojects.R
import com.coderzheaven.tutorialprojects.adapter.ListAdapter
import com.coderzheaven.tutorialprojects.models.User
import com.coderzheaven.tutorialprojects.repo.RetrofitUtil
import com.coderzheaven.tutorialprojects.repo.user_repo.UserRepoImplementation
import com.coderzheaven.tutorialprojects.models.AppServiceResponse
import com.coderzheaven.tutorialprojects.models.Status
import com.coderzheaven.tutorialprojects.view_model.UserViewModelFactory
import com.coderzheaven.tutorialprojects.view_model.UsersViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mListRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnLoad: Button
    private lateinit var tvErrorMessage: TextView

    private lateinit var viewModel: UsersViewModel
    private var mAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "MVVM"
        findViews()
        showErrorMessage(null)
        setListeners()
    }

    private fun findViews() {
        mListRecyclerView = findViewById(R.id.list_recycler_view)
        btnLoad = findViewById(R.id.btnLoad)
        progressBar = findViewById(R.id.progress)
        tvErrorMessage = findViewById(R.id.tvErrorMessage)
        btnLoad.setOnClickListener(this)
    }

    private fun setListeners() {
        val userViewModelFactory = UserViewModelFactory(
            application,
            UserRepoImplementation(RetrofitUtil.usersInterface())
        )
        viewModel = ViewModelProvider(this, userViewModelFactory).get(UsersViewModel::class.java)
        viewModel.apiServiceResponse.observe(this, Observer(onResponseReceived()))
    }

    private fun onResponseReceived() = { users: AppServiceResponse<List<User>> ->
        when (users.status) {
            Status.LOADING -> {
                showLoading(true)
            }
            Status.ERROR -> {
                showErrorMessage(users.message)
                showLoading(false)
            }
            Status.SUCCESS -> {
                setList(users.data!!)
                showLoading(false)
            }
        }
    }

    private fun showErrorMessage(message: String?) {
        tvErrorMessage.visibility = if (null != message) View.VISIBLE else View.GONE
        tvErrorMessage.text = message
    }

    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setList(users: List<User>) {
        if (null != mAdapter) {
            mAdapter?.notifyDataSetChanged()
            return
        }
        mAdapter = ListAdapter(users)
        mListRecyclerView.adapter = mAdapter
        mListRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(v: View?) {
        if (v!!.id == R.id.btnLoad) {
            showErrorMessage(null)
            viewModel.fetchUsers()
        }
    }
}