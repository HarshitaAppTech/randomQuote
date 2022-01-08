package com.harshitaapptech.randomquote.network

import com.harshitaapptech.randomquote.model.QuotesModel
import com.harshitaapptech.randomquote.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {

    @GET(Constants.GET_QUOTE_API)
    fun getQuote(): Call<QuotesModel>

    @GET(Constants.GET_QUOTE_API)
    fun getQuoteFromTag(
        @Query("tags") tag: String
    ): Call<QuotesModel>

}