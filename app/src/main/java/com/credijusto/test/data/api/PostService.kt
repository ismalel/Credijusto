package com.credijusto.test.data.api

import com.credijusto.test.data.model.Comment
import com.credijusto.test.data.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("comments")
    suspend fun getCommentsByPostId(@Query("postId") postId: Int) : Response<List<Comment>>

    companion object {
        const val API_URL = "https://jsonplaceholder.typicode.com/"
    }
}