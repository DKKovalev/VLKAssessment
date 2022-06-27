package com.dkkovalev.vlkassessmentclient

import com.dkkovalev.vlkassessmentclient.api.ClientApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ClientModule {

    @Binds
    @Singleton
    fun bindsDataStore(impl: ClientDataStoreImpl): ClientDataStore

    @Binds
    @Singleton
    fun bindsClientApi(impl: ClientApiImpl): ClientApi
}