package com.credijusto.test

import com.credijusto.test.data.api.PostService
import com.credijusto.test.data.local.dao.PostDao
import com.credijusto.test.data.repository.DefaultPostRepository
import com.credijusto.test.data.repository.PostRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class PostRepositoryTest {
    private var postRepository: PostRepository = TODO()
    @Mock
    lateinit var postDao: PostDao
    @Mock
    lateinit var postService: PostService

    @Before
    fun setUp(){
       TODO()
    }

    @Test
    suspend fun testGetPosts(){
        TODO()
    }
}