package com.example.khapharma.models

import com.example.khapharma.models.User

data class LoginUser(
    val token: String,
    val user: User
)