package com.example.thetop100

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val mGitHubService = retrofit.create(GitHubService::class.java)

    fun gitHubService(): GitHubService = mGitHubService

}