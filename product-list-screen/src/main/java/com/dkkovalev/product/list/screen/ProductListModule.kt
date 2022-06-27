package com.dkkovalev.product.list.screen

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface ProductListModule {

    @Binds
    @ViewModelScoped
    fun bindsProductListUseCase(impl: ProductListUseCaseImpl): ProductListUseCase

    @Binds
    @ViewModelScoped
    fun bindsProductListRepo(impl: ProductListRepoImpl): ProductListRepo
}
