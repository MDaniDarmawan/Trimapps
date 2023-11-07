package com.example.trimapps.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        private const val dbName = "user"
        private var userDatabase: UserDatabase? = null

        @Synchronized
        fun getUserDatabase(context: Context): UserDatabase {
            if (userDatabase == null) {
                userDatabase = Room.databaseBuilder(context, UserDatabase::class.java, dbName)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return userDatabase!!
        }
    }
    abstract fun userDao(): UserDao
}
