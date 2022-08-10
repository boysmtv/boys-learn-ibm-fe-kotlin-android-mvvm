package com.boys.assets.ibm.activity.movies.usecase

import com.boys.assets.ibm.activity.movies.model.MoviesModel
import com.boys.assets.ibm.activity.movies.model.MoviesReqModel
import com.boys.assets.ibm.di.network.Repository
import com.boys.assets.ibm.domain.usecase.UseCase

class MoviesUC constructor(
    private val repository: Repository
) : UseCase<MoviesModel, Any?>() {

    private val TAG = this::class.java.simpleName

    override suspend fun run(params: Any?): MoviesModel {
        val model = params as MoviesReqModel
        return repository.getMovies(model.category, model.api_key)
    }

}