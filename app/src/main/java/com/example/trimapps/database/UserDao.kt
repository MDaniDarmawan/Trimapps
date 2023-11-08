package com.example.trimapps.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trimapps.home.Model.responseUserGithub

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: responseUserGithub.Item)

    @Query("SELECT * FROM User")
    fun loadAll(): LiveData<MutableList<responseUserGithub.Item>>

    @Query("SELECT * FROM User WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): responseUserGithub.Item

    @Delete
    fun delete(user: responseUserGithub.Item)
}