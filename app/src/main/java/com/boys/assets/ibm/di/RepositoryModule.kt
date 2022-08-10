package com.boys.assets.ibm.di

import com.boys.assets.ibm.remote.ApiService
import com.boys.assets.ibm.di.network.Repository
import com.boys.assets.ibm.di.network.RepositoryImpl

fun createRepository(apiService: ApiService): Repository {
    return RepositoryImpl(apiService)
}