package com.credijusto.test.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Comment.TABLE_NAME)
data class Comment(
    @PrimaryKey
    val id: Int? = 0,
    @ColumnInfo
    val body: String? = null,
    @ColumnInfo
    val email: String? = null,
    @ColumnInfo
    val name: String? = null,
    @ColumnInfo
    val postId: Int? = 0
){
    companion object {
        const val TABLE_NAME = "comments"
    }
}