package com.example.githubpractice.network

import com.example.githubpractice.model.Repo
import com.example.githubpractice.model.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @Headers("Authorization: Bearer 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")
    @GET("users/{username}/repos")
    fun listRepos(@Path("username") username: String): Call<List<Repo>>

    @Headers("Authorization: Bearer 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")
    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<UserDto>
}