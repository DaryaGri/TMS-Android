package com.example.cryptocurrenciesapp

interface CryptocurrencyRepository {
    fun getCryptocurrency () : List <Cryptocurrency>
}