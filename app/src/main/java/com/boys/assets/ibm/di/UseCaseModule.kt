package com.boys.assets.ibm.di

import com.boys.assets.ibm.activity.movies.usecase.MoviesUC
import com.boys.assets.ibm.activity.users.usecase.SelectedUC
import com.boys.assets.ibm.di.network.Repository

fun getMoviesUseCase(repository: Repository): MoviesUC {
    return MoviesUC(repository)
}

fun getSelectedUseCase(repository: Repository): SelectedUC {
    return SelectedUC(repository)
}