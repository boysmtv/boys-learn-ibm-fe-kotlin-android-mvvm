package com.boys.assets.ibm.di

import com.boys.assets.ibm.remote.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single { createService(get()) }
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}