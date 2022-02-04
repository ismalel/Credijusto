package com.credijusto.test.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.credijusto.test.data.model.Post.Companion.TABLE_NAME

/**
 * Data class for Database entity and Serialization.
 */
@Entity(tableName = TABLE_NAME)
data class Post(

    @PrimaryKey
    val id: Int? = 0,
    @ColumnInfo
    val body: String? = null,
    @ColumnInfo
    val title: String? = null,
    @ColumnInfo
    val userId: Int? = 0
){
    companion object {
        const val TABLE_NAME = "posts"
    }
}