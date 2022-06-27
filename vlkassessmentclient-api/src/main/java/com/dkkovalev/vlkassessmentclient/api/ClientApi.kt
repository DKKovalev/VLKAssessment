package com.dkkovalev.vlkassessmentclient.api

interface ClientApi {

    fun getProducts(limit: Int): List<ProductDto>
}