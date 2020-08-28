package com.example.thetop100

data class Repo(val name:String, val html_url:String, val owner: Owner)

data class Owner(val login:String)