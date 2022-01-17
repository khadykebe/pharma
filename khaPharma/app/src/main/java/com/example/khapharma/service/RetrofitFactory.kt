package com.example.khapharma.service

import android.content.Context
import com.example.khapharma.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

import com.google.gson.Gson




object RetrofitFactory {
    const val  BASE_URL = "http://192.168.98.212:8000/"

    fun makeRetrofitService(context: Context): RetrofitService {

    return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient(context))
            .build().create(RetrofitService::class.java)
    }
    private fun okhttpClient(context: Context): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }



}