package com.boys.assets.ibm.remote

import com.boys.assets.ibm.activity.movies.model.MoviesModel
import com.boys.assets.ibm.activity.users.model.SelectedRespModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("{category}")
    suspend fun getMovies(
        @Path("category") category: String?,
        @Query("api_key") api_key: String?
    ) : MoviesModel

    @Headers("Content-Type: application/json")
    @GET("{id}")
    suspend fun getSelected(
        @Path("id") id: Int?,
        @Query("api_key") api_key: String?
    ) : SelectedRespModel

}