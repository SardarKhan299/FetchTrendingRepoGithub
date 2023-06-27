package com.traiden.fetchtrendingrepo.domain

data class Repository(
    val name: String,
    val owner: String,
    val description: String,
    val ownerAvatar:String,
    val language:String,
    val watchers_count:Int
)
