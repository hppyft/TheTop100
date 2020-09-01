package com.example.thetop100

interface RepoDataSource {
    fun listAll(success: (List<Repo>) -> Unit, failure: () -> Unit)
}