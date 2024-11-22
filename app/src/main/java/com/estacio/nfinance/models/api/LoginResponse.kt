package com.estacio.nfinance.models.api

data class LoginResponse(
    val id : String,
    val name: String,
    val email: String,
    val token: String,
    val message: String
)
