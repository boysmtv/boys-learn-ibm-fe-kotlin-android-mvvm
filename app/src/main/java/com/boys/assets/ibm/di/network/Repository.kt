package com.boys.assets.ibm.di.network

import com.boys.assets.ibm.activity.movies.model.MoviesModel
import com.boys.assets.ibm.activity.users.model.SelectedRespModel

interface Repository {

    suspend fun getMovies(category: String?, apiKey: String?): MoviesModel

    suspend fun getSelected(id: Int?, apiKey: String?): SelectedRespModel

}