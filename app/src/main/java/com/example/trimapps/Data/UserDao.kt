package com.example.trimapps.Data

import androidx.room.Dao
import androidx.room.Query


@Dao
public interface UserDao {

   //@Insert
   //void registerUser(userEntity: UserEntity?)


    // Metode ini digunakan untuk melakukan pencarian pengguna berdasarkan nama pengguna (userId) dan kata sandi (password)
    // saat proses login dan autentikasi.
    @Query("SELECT * from user where usernameApp = :userId and password = :password")
    fun login(userId: String?, password: String?): UserEntity?

    // Metode ini digunakan untuk mencari pengguna berdasarkan nama pengguna (username).
    @Query("SELECT * from user where usernameApp = :username")
    fun getUserByUsername(username: String?): UserEntity?


}