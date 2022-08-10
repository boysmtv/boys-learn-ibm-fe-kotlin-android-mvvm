package com.boys.assets.ibm.di

import com.boys.assets.ibm.activity.movies.vm.MoviesViewModel
import com.boys.assets.ibm.activity.users.vm.SelectedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureStag = module {
    // for search
    viewModel { MoviesViewModel(get()) }
    single { getMoviesUseCase(get()) }

    // for get users
    viewModel { SelectedViewModel(get()) }
    single { getSelectedUseCase(get()) }

    // create repository
    single { createRepository(get()) }
}
