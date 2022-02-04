package com.credijusto.test.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.credijusto.test.data.repository.Resource
import com.credijusto.test.databinding.ActivityDetailBinding
import com.credijusto.test.ui.base.BaseActivity
import com.credijusto.test.utils.hide
import com.credijusto.test.utils.show
import com.credijusto.test.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailActivity : BaseActivity<DetailViewModel, ActivityDetailBinding>() {


    override val mViewModel: DetailViewModel by viewModels()

    var postId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        initView()
    }

    fun initView(){

        postId = intent.extras!!.getInt("postId")
        mViewModel.getCommentByPostId(postId!!)

    }
    override fun onStart() {
        super.onStart()
        observePosts()
    }
    private fun observePosts() {
        mViewModel.commentsLiveData.observe(this){ resource ->
            when (resource) {
                is Resource.SUCCESS -> {
                    if (resource.data!!.isNotEmpty()) {
                        mViewModel.numberComments = resource.data.size
                        mViewModel.getPostById(postId!!)
                    }
                }
                is Resource.ERROR -> {
                    resource.message?.let { showToast(it) }
                }
            }
        }

        mViewModel.postLiveData.observe(this){
            resource ->
            when (resource){
                is Resource.SUCCESS -> {

                    mViewBinding.tvName.text = resource.data!!.title
                    mViewBinding.tvEmail.text = mViewModel.numberComments.toString()
                    mViewBinding.tvComment.text = resource.data.body

                }
                is Resource.ERROR -> {
                    resource.message?.let { showToast(it) }
                }
            }
        }
    }

    override fun getViewBinding(): ActivityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)

}