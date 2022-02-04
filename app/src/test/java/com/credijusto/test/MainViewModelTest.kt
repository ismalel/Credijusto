package com.credijusto.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.credijusto.test.data.model.Post
import com.credijusto.test.data.repository.PostRepository
import com.credijusto.test.data.repository.Resource
import com.credijusto.test.ui.main.MainViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import org.mockito.Mockito.`when` as whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    private lateinit var viewModel: MainViewModel
    @Mock
    private lateinit var postsRepository: PostRepository
    @Mock
    private lateinit var postResponseObserver: Observer<Resource<List<Post>>>

    @Before
    fun setUp() {
        viewModel = MainViewModel(postsRepository)
    }

    @Test
    fun `when fetching results ok then return a list successfully`() {
        val emptyList = arrayListOf<Post>()
        testCoroutineRule.runBlockingTest {
            viewModel.postsLiveData.observeForever(postResponseObserver)
            whenever(postsRepository.getAllPosts()).thenAnswer{
                Resource.SUCCESS(emptyList)
            }
            viewModel.getPosts()

            assertNotNull(viewModel.postsLiveData.value)
            assertEquals(Resource.SUCCESS(emptyList), viewModel.postsLiveData.value)

        }
    }
}