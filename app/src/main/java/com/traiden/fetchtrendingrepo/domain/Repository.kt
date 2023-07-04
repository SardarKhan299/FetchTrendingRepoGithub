package com.traiden.fetchtrendingrepo.domain

data class Repository(
    val name: String,
    val owner: Owner,
    val description: String,
    val language:String,
    val watchers_count:Int
)

data class Owner(val avatar_url: String,val login:String)

data class Items(val items: List<Repository>)
