package com.example.trimapps.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trimapps.home.Model.responseUserGithub

@Database(entities = [responseUserGithub.Item::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun userDao(): UserDao
}