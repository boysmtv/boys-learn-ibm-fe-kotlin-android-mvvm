package com.boys.assets.ibm.activity.movies.model

import com.google.gson.annotations.SerializedName

data class MoviesModel(

    @SerializedName("dates")
    val dates: MoviesDates? = null,

    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("results")
    val results: ArrayList<MoviesData>? = null,

)

data class MoviesDates(

    @SerializedName("maximum")
    val maximum: Boolean? = null,

    @SerializedName("minimum")
    val minimum: Boolean? = null,
)

data class MoviesData(

    @SerializedName("adult")
    val adult: Boolean? = null,

    @SerializedName("backdrop_path")
    val backdrop_path: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("original_language")
    val original_language: String? = null,

    @SerializedName("original_title")
    val original_title: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("popularity")
    val popularity: String? = null,

    @SerializedName("poster_path")
    val poster_path: String? = null,

    @SerializedName("release_date")
    val release_date: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("video")
    val video: String? = null,

    @SerializedName("vote_average")
    val vote_average: String? = null,

    @SerializedName("vote_count")
    val vote_count: String? = null,

)