package com.credijusto.test.data.repository

import com.credijusto.test.data.api.PostService
import com.credijusto.test.data.local.dao.PostDao
import com.credijusto.test.data.model.Comment
import com.credijusto.test.data.model.Post
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

interface PostRepository {
   suspend fun getAllPosts(): Response<List<Post>>
   suspend fun getCommentsByPostId(id: Int) : Response<List<Comment>>
   suspend fun getPostById(id: Int) : Post
}
/**
 * Singleton repository for fetching data from remote (storing it in database)
 * and local
 */
@ExperimentalCoroutinesApi
class DefaultPostRepository @Inject constructor(
    private val postService: PostService,
    private val postDao: PostDao
) : PostRepository {

    override suspend fun getAllPosts(): Response<List<Post>> {
        val response = postService.getPosts()
        if(response.isSuccessful){
            postDao.addPosts(response.body()!!)
        }
        return response
    }

    override suspend fun getCommentsByPostId(id: Int): Response<List<Comment>> {
        return postService.getCommentsByPostId(id)
    }

    override suspend fun getPostById(id: Int): Post {
        return postDao.getPostById(id)
    }

}
