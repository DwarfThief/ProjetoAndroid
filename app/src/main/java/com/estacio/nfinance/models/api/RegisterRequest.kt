package com.estacio.nfinance.models.api

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val passwordVerify: String
)