package com.example.thetop100

import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubDataSource(val mGitHubService: GitHubService) :RepoDataSource{
    override fun listAll(success: (List<Repo>) -> Unit, failure: () -> Unit) {
        val call = mGitHubService.listRepos()
        call.enqueue(object : Callback<List<Repo>?> {
            override fun onFailure(call: Call<List<Repo>?>, t: Throwable) {
                failure()
            }

            override fun onResponse(call: Call<List<Repo>?>, response: Response<List<Repo>?>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        success(it)
                    }
                }
            }

        })
    }
}