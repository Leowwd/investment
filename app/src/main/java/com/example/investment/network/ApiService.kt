package com.example.investment.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://cpx.cbc.gov.tw/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface RateApiService {
    @GET("api/OpenData/DataSet?set_id=9464&index=0")
    suspend fun getRate(): List<Info>
}

object RateApi {
    val retrofitService: RateApiService by lazy { retrofit.create(RateApiService::class.java) }
}