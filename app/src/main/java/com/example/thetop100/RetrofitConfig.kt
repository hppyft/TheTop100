package com.example.thetop100

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun gitHubService() = retrofit.create(GitHubService::class.java)

}