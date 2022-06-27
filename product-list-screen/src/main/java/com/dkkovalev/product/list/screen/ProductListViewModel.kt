package com.dkkovalev.product.list.screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val useCase: ProductListUseCase
) : ViewModel() {

    val productsObservable: Observable<List<Product>>
        get() = productsSubject
    private val productsSubject: BehaviorSubject<List<Product>> = BehaviorSubject.create()

    private val compositeDisposable = CompositeDisposable()

    fun loadProducts() {
        compositeDisposable.add(
            useCase.loadProducts().subscribe(
                {
                    productsSubject.onNext(it)
                },
                Throwable::printStackTrace
            )
        )
    }

    fun cleanup() {
        compositeDisposable.clear()
    }
}