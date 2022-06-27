package com.dkkovalev.product.list.screen

import com.dkkovalev.vlkassessmentclient.api.ClientApi
import io.reactivex.Single
import javax.inject.Inject

interface ProductListRepo {
    fun loadProducts(limit: Int): Single<List<Product>>
}

class ProductListRepoImpl @Inject constructor(
    private val api: ClientApi
) : ProductListRepo {

    override fun loadProducts(limit: Int) = Single.just(api.getProducts(limit)
        .map {
            Product(
                id = it.id,
                price = it.price,
                name = it.name
            )
        })

}