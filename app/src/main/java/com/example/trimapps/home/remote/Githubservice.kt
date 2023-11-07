package com.example.trimapps.Home.remote

import com.example.trimapps.Home.Model.responseUserGithub
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.Response

interface Githubservice {

    @GET("users")
    suspend fun getUserGithub(@QueryMap map: Map<String, Any>): Response<responseUserGithub>
}