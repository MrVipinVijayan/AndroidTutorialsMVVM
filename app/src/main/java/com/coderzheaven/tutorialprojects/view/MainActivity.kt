package com.coderzheaven.tutorialprojects.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coderzheaven.tutorialprojects.R
import com.coderzheaven.tutorialprojects.adapter.ListAdapter
import com.coderzheaven.tutorialprojects.databinding.ActivityMainBinding
import com.coderzheaven.tutorialprojects.models.AppServiceResponse
import com.coderzheaven.tutorialprojects.models.Status
import com.coderzheaven.tutorialprojects.models.User
import com.coderzheaven.tutorialprojects.repo.retrofit_util.RetrofitUtil
import com.coderzheaven.tutorialprojects.repo.user_repo.UserRepoImplementation
import com.coderzheaven.tutorialprojects.view_model.UserViewModelFactory
import com.coderzheaven.tutorialprojects.view_model.UsersViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UsersViewModel
    private var mAdapter: ListAdapter? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        showErrorMessage(null)
        setViewModel()
        setDataBinding()
    }

    private fun setDataBinding(){
        binding.lifecycleOwner = this
        binding.usersViewModel = viewModel
    }

    private fun setViewModel() {
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
        binding.tvErrorMessage.visibility = if (null != message) View.VISIBLE else View.GONE
        binding.tvErrorMessage.text = message;
    }

    private fun showLoading(show: Boolean) {
        binding.progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setList(users: List<User>) {
        if (null != mAdapter) {
            mAdapter?.notifyDataSetChanged()
            return
        }
        mAdapter = ListAdapter(users)
        binding.userListRecyclerView.adapter = mAdapter
        binding.userListRecyclerView.layoutManager = LinearLayoutManager(this)
    }

}