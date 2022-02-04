package com.credijusto.test.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.credijusto.test.data.model.Comment
import com.credijusto.test.data.model.Post
import com.credijusto.test.data.repository.PostRepository
import com.credijusto.test.data.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


/**
 * ViewModel for [DetailActivity]
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class DetailViewModel @Inject constructor(private val postRepository: PostRepository) :
    ViewModel() {

    private val _commentsLiveData = MutableLiveData<Resource<List<Comment>>>()

    val commentsLiveData: LiveData<Resource<List<Comment>>> = _commentsLiveData

    private val _postLiveData = MutableLiveData<Resource<Post>>()
    val postLiveData: LiveData<Resource<Post>> = _postLiveData

    var numberComments: Int = 0



    fun getCommentByPostId(id: Int){
        viewModelScope.launch {
            val response = postRepository.getCommentsByPostId(id)
            if (response.isSuccessful){
                _commentsLiveData.postValue(Resource.SUCCESS(response.body()!!))

            }else{
                _commentsLiveData.postValue(Resource.ERROR(message = "error"))

            }
        }
    }

    fun getPostById(id: Int){
        viewModelScope.launch {
            val postResponse = postRepository.getPostById(id)
            _postLiveData.postValue(Resource.SUCCESS(postResponse))
        }
    }
}