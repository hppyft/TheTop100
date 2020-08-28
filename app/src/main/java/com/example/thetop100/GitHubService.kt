package com.example.thetop100

import retrofit2.Call
import retrofit2.http.GET

interface GitHubService {
    @GET("repositories")
     fun listRepos() : Call<List<Repo>>


}