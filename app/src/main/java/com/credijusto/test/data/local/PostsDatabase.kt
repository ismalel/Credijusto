package com.credijusto.test.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.credijusto.test.data.local.dao.PostDao
import com.credijusto.test.data.model.Post

/**
 * Abstract Posts database.
 * It provides DAO [PostsDao] by using method [getPostsDao].
 */
@Database(
    entities = [Post::class],
    version = 1
)
abstract class PostsDatabase : RoomDatabase() {
    /**
     * @return [PostsDao]  Posts Data Access Object.
     */
    abstract fun getPostsDao(): PostDao

    companion object {
        const val DB_NAME = "posts_database"

        @Volatile
        private var INSTANCE: PostsDatabase? = null

        fun getInstance(context: Context): PostsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostsDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}