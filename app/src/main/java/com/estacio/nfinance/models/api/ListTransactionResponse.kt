package com.estacio.nfinance.models.api

import com.estacio.nfinance.models.Transaction

data class ListTransactionResponse(
    val data: List<Transaction>,
    val message: String
)