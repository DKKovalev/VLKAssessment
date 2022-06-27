package com.dkkovalev.product.list.screen

data class Product(
    val id: Int,
    val name: String,
    val price: Int,
) {
    var priceState: PriceState = PriceState.SAME
        private set

    fun checkPrice(prevProduct: Product) {
        priceState = when {
            price > prevProduct.price -> PriceState.HIGHER
            price < prevProduct.price -> PriceState.LOWER
            else -> PriceState.SAME
        }
    }
}