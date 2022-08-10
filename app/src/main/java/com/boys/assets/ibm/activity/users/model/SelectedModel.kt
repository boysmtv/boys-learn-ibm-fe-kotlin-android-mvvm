package com.boys.assets.ibm.activity.users.model

import com.google.gson.annotations.SerializedName

data class SelectedReqModel (

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("api_key")
    var api_key: String? = null

)
data class SelectedRespModel (

    @SerializedName("adult")
    var adult: Boolean? = null,

    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,

    @SerializedName("belongs_to_collection")
    var belongs_to_collection: Beelong? = null,

    @SerializedName("budget")
    var budget: Long? = null,

    @SerializedName("genres")
    var genres: ArrayList<Genres>? = null,

    @SerializedName("homepage")
    var homepage: String? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("imdb_id")
    var imdb_id: String? = null,

    @SerializedName("original_language")
    var original_language: String? = null,

    @SerializedName("original_title")
    var original_title: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("poster_path")
    var poster_path: String? = null,

    @SerializedName("release_date")
    var release_date: String? = null,

    @SerializedName("revenue")
    var revenue: Long? = null,

    @SerializedName("runtime")
    var runtime: Int? = null,

    @SerializedName("tagline")
    var tagline: String? = null,

)

data class Beelong(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("poster_path")
    var poster_path: String? = null,

    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,

)

data class Genres(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

)