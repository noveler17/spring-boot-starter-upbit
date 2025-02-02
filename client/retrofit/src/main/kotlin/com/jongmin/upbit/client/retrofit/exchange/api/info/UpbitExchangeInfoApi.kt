package com.jongmin.upbit.client.retrofit.exchange.api.info

import retrofit2.Call
import retrofit2.http.GET

interface UpbitExchangeInfoApi {

    @GET("v1/status/wallet")
    fun getWalletStatus(): Call<List<UpbitWalletStatusResponse>>

    @GET("v1/api_keys")
    fun getApiKeys(): Call<List<UpbitApiKeyResponse>>
}
