package com.harshitaapptech.randomquote.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshitaapptech.randomquote.model.QuotesModel
import com.harshitaapptech.randomquote.network.QuoteAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val quoteAPI: QuoteAPI
) : ViewModel() {

    private val quoteString: MutableLiveData<String> = MutableLiveData()
    fun quoteString(): LiveData<String> {
        return quoteString
    }

    private val authorString: MutableLiveData<String> = MutableLiveData()
    fun authorString(): LiveData<String> {
        return authorString
    }

    init {
        getQuoteData()
    }

    fun getQuoteData() {
        viewModelScope.launch {
            quoteAPI.getQuote().enqueue(object : Callback<QuotesModel> {
                override fun onResponse(call: Call<QuotesModel>, response: Response<QuotesModel>) {
                    if (response.isSuccessful) {
                        response.body()?.let { data ->
                            quoteString.value = data.content
                            authorString.value = data.author
                        }
                    }
                }

                override fun onFailure(call: Call<QuotesModel>, t: Throwable) {
                    quoteString.value = "Error with API = ${t.message}"
                }
            })
        }
    }

}