package com.dkkovalev.vlkassessmentclient

import com.dkkovalev.vlkassessmentclient.api.ProductDto
import javax.inject.Inject

interface ClientDataStore {
    fun getProducts(): List<ProductDto>
    fun updateProducts(newList: List<ProductDto>)
}

class ClientDataStoreImpl @Inject constructor() : ClientDataStore {

    private var products = listOf(
        ProductDto(1, 30, "Product one"),
        ProductDto(2, 35, "Product two"),
        ProductDto(3, 23, "Product three"),
        ProductDto(4, 18, "Product four"),
        ProductDto(5, 234, "Product five"),
        ProductDto(6, 12, "Product six"),
        ProductDto(7, 45, "Product seven"),
        ProductDto(8, 32, "Product eight"),
        ProductDto(9, 21, "Product nine"),
        ProductDto(10, 86, "Product ten")
    )

    override fun getProducts(): List<ProductDto> = products

    override fun updateProducts(newList: List<ProductDto>) {
        products = products.map { product ->
            newList.lastOrNull { it.id == product.id }
                ?.let { product.copy(price = it.price) }
                ?: product
        }
    }
}