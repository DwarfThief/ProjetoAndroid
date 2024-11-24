package com.estacio.nfinance.models.api

data class TransactionRequest(
    val name: String,
    val amount: String,
    val category: String,
    val description: String
)
