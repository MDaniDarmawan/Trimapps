package com.example.trimapps.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    // Kolom 'usernameApp' yang akan menyimpan nama pengguna aplikasi.
    @ColumnInfo(name = "usernameApp")
    var usernameApp: String? = null

    // Kolom 'usernameGitHub' yang akan menyimpan nama pengguna GitHub.
    @ColumnInfo(name = "usernameGitHub")
    var usernameGitHub: String? = null

    // Kolom 'email' yang akan menyimpan alamat email pengguna.
    @ColumnInfo(name = "email")
    var email: String? = null

    // Kolom 'password' yang akan menyimpan kata sandi pengguna.
    @ColumnInfo(name = "password")
    var password: String? = null



}