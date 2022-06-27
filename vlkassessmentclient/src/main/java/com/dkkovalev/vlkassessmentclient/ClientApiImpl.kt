package com.dkkovalev.vlkassessmentclient

import com.dkkovalev.vlkassessmentclient.api.ClientApi
import com.dkkovalev.vlkassessmentclient.api.ProductDto
import javax.inject.Inject

class ClientApiImpl @Inject constructor(
   private val dataStore: ClientDataStore
) : ClientApi {

    override fun getProducts(limit: Int): List<ProductDto> =
        dataStore.getProducts().sortedByDescending { it.price }
            .map { it.copy(price = it.price + (-5..5).random()) }
            .take(limit)
            .also(dataStore::updateProducts)
}