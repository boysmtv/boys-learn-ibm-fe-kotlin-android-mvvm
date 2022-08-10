package com.boys.assets.ibm.activity.movies.model

import com.google.gson.annotations.SerializedName

data class MoviesReqModel (

    @SerializedName("category")
    val category: String? = null,

    @SerializedName("api_key")
    val api_key: String? = null,

)