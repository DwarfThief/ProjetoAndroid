package com.estacio.nfinance.models.api

import Transaction

data class ListTransactionResponse(
    val data: List<Transaction>,
    val message: String
)
