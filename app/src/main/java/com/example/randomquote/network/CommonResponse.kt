package com.example.randomquote.network

class CommonResponseResource<T>(
    val status: CommonApiStatus, val data: T, val message: String?
) {
    enum class CommonApiStatus {
        SUCCESS, ERROR, LOADING, UNAUTH
    }

    companion object {
        fun <T> success(msg: String?, data: T?): CommonResponseResource<T?> {
            return CommonResponseResource(CommonApiStatus.SUCCESS, data, msg)
        }

        fun <T> error(msg: String?): CommonResponseResource<T?> {
            return CommonResponseResource(CommonApiStatus.ERROR, null, msg)
        }

        fun <T> loading(): CommonResponseResource<T?> {
            return CommonResponseResource(CommonApiStatus.LOADING, null, "Loading")
        }

        fun <T> unAuth(): CommonResponseResource<T?> {
            return CommonResponseResource(CommonApiStatus.UNAUTH, null, null)
        }
    }
}