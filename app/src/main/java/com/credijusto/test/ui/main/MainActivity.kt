package com.credijusto.test.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.credijusto.test.data.repository.Resource
import com.credijusto.test.databinding.ActivityMainBinding
import com.credijusto.test.ui.base.BaseActivity
import com.credijusto.test.ui.detail.DetailActivity
import com.credijusto.test.ui.main.adapter.PostListAdapter
import com.credijusto.test.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.credijusto.test.utils.*

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private val mAdapter = PostListAdapter()

    override val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        initView()
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private fun initView() {

        mViewBinding.postsRecyclerView.apply {
            adapter = mAdapter

        }
        mAdapter.onPostClicked {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("postId", it.id)
            startActivity(intent)
        }
        getPosts()


    }

    override fun onStart() {
        super.onStart()
        observePosts()
    }

    private fun observePosts() {
        mViewModel.postsLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.LOADING -> showLoading(true)
                is Resource.SUCCESS -> {
                    if (resource.data!!.isNotEmpty()) {
                        mAdapter.submitList(resource.data.toMutableList())
                        showLoading(false)
                    }
                }
                is Resource.ERROR -> {
                    resource.message?.let { showToast(it) }
                    showLoading(false)
                }
            }
        }
    }

    private fun getPosts() = mViewModel.getPosts()

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            mViewBinding.pb.show()
        } else {
            mViewBinding.pb.hide()
        }
    }

}