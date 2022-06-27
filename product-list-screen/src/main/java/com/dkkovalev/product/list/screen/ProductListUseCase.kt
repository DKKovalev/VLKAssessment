package com.dkkovalev.product.list.screen

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface ProductListUseCase {
    fun loadProducts(): Single<List<Product>>
}

class ProductListUseCaseImpl @Inject constructor(
    private val repo: ProductListRepo
) : ProductListUseCase {

    private var oldProductList = emptyList<Product>()

    companion object {
        private const val LIMIT = 5
    }

    override fun loadProducts(): Single<List<Product>> = repo.loadProducts(LIMIT)
        .observeOn(Schedulers.computation())
        .doOnSuccess {
            oldProductList.forEachIndexed { index, oldProduct ->
                it[index].checkPrice(oldProduct)
            }
            oldProductList = it
        }
}