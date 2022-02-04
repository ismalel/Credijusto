package com.credijusto.test.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.credijusto.test.data.model.Post
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Data Access Object (DAO) for [com.data.local.PostsDatabasee]
 */
@Dao
interface PostDao {

    /**
     * Inserts [posts] into the [Post.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param posts Posts
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPosts(posts: List<Post>)

    /**
     * Deletes all the posts from the [Post.TABLE_NAME] table.
     */
    @Query("DELETE FROM ${Post.TABLE_NAME}")
    suspend fun deleteAllPosts()

    /**
     * Fetches all the posts from the [Post.TABLE_NAME] table.
     * @return posts Posts
     */
    @Query("SELECT * FROM ${Post.TABLE_NAME}")
    suspend fun getAllPosts(): List<Post>

    /**
     * Fetches a specific post searched for by its id from the [Post.TABLE_NAME] table .
     * @return post
     */
    @Query("SELECT * FROM ${Post.TABLE_NAME} WHERE id = :postId")
    suspend fun getPostById(postId: Int): Post

}
