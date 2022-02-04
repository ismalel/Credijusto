package com.credijusto.test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.credijusto.test.data.model.Post
import com.credijusto.test.data.repository.PostRepository
import com.credijusto.test.data.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

/**
 * ViewModel for [MainActivity]
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val postRepository: PostRepository) :
    ViewModel() {

    private val _postsLiveData = MutableLiveData<Resource<List<Post>>>()

    val postsLiveData: LiveData<Resource<List<Post>>> = _postsLiveData

    fun getPosts() {
        viewModelScope.launch {
            val response = postRepository.getAllPosts()
            _postsLiveData.postValue(handleResponse(response))
        }
    }

    private fun handleResponse(response: Response<List<Post>>) : Resource<List<Post>> {
        if(response.isSuccessful){
            response.body()?.let {
                    it -> return Resource.SUCCESS(it)
            }
        }
        return Resource.ERROR(message = response.errorBody().toString())

    }

}