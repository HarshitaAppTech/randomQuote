package com.harshitaapptech.randomquote.network

import com.harshitaapptech.randomquote.model.CommonResponse
import com.harshitaapptech.randomquote.utils.Constants
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {

    @GET(Constants.GET_QUOTE_API)
    fun getQuote(): Flowable<Response<CommonResponse>>

    @GET(Constants.GET_QUOTE_FROM_TAG)
    fun getQuoteFromTag(
        @Query("tag") tag: String
    ): Flowable<Response<CommonResponse>>

}