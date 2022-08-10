package com.boys.assets.ibm.di.network

import com.boys.assets.ibm.activity.movies.model.MoviesModel
import com.boys.assets.ibm.activity.users.model.SelectedRespModel
import com.boys.assets.ibm.remote.ApiService

class RepositoryImpl (private val apiService: ApiService) : Repository {

    override suspend fun getMovies(category: String?, apiKey: String?): MoviesModel {
        return apiService.getMovies(category, apiKey)
    }

    override suspend fun getSelected(id: Int?, apiKey: String?): SelectedRespModel {
        return apiService.getSelected(id, apiKey)
    }

}