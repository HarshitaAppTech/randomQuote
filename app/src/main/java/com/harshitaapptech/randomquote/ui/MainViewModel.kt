package com.harshitaapptech.randomquote.ui

import androidx.lifecycle.*
import com.harshitaapptech.randomquote.model.CommonResponse
import com.harshitaapptech.randomquote.network.CommonResponseResource
import com.harshitaapptech.randomquote.network.QuoteAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val quoteAPI: QuoteAPI
) : ViewModel() {

    private val homeQuoteApiResponse: MediatorLiveData<CommonResponseResource<out Response<CommonResponse>?>> =
        MediatorLiveData()

    fun getHomeQuoteApiResponse(): LiveData<CommonResponseResource<out Response<CommonResponse>?>> {
        return homeQuoteApiResponse
    }

    fun getQuoteData() {
        viewModelScope.launch {
            homeQuoteApiResponse.value = CommonResponseResource.loading()
            val callUploadSource: LiveData<CommonResponseResource<Response<CommonResponse>?>> =
                LiveDataReactiveStreams.fromPublisher(
                    quoteAPI.getQuote().onErrorReturn {
                        return@onErrorReturn Response.success(
                            CommonResponse(
                                it.message + "",
                                false
                            )
                        )
                    }.map {
                        when {
                            it.isSuccessful -> return@map CommonResponseResource.success(
                                it.body()?.message,
                                it
                            )
                            it.code() == 401 -> return@map CommonResponseResource.unAuth()
                            else -> return@map CommonResponseResource.error(it.body()?.message)
                        }
                    }.subscribeOn(Schedulers.io())
                )

            homeQuoteApiResponse.addSource(callUploadSource) {
                homeQuoteApiResponse.value = it
                homeQuoteApiResponse.removeSource(callUploadSource)
            }
        }
    }

}