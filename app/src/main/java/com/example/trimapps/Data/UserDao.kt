package com.example.trimapps.Data

import androidx.room.Dao
import androidx.room.Query


@Dao
public interface UserDao {

   //@Insert
   //void registerUser(userEntity: UserEntity?)

    @Query("SELECT * from user where usernameApp = :userId and password = :password")
    fun login(userId: String?, password: String?): UserEntity?



}