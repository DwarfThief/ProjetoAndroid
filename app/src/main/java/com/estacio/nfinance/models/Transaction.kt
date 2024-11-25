package com.estacio.nfinance.models

data class Transaction(
    val _id: String,
    val name: String,
    val amount: Int,
    val description: String?,
    val category: String,
    val date: String,
    val userID: String,
    val type: Int,
    val __v: Int
)
