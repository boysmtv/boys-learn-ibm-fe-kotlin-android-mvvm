package com.boys.assets.ibm.activity.movies.presentation

import android.view.View
import com.boys.assets.ibm.activity.movies.model.MoviesData
import com.boys.assets.ibm.activity.movies.model.MoviesModel

interface MoviesOnClickListener<T> {
    fun onItemClick(v: View?, position: Int, model: MoviesData)
}