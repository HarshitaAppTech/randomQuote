package com.example.randomquote.utils

import com.example.randomquote.network.QuoteAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Networking {

    @Singleton
    @Provides
    fun provideQuoteAPI(retrofit: Retrofit): QuoteAPI = retrofit.create(QuoteAPI::class.java)

}