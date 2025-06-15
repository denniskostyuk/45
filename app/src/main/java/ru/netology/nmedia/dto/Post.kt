package ru.netology.nmedia.dto

data class Post(
    val id: Int,
    val author: String,
    val published: String,
    val content: String,
    var likes: Int = 1999999,
    var reposts: Int = 1,
    var likeByMe: Boolean = false


)
