package com.boys.assets.ibm.activity.movies.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boys.assets.ibm.activity.movies.model.MoviesModel
import com.boys.assets.ibm.activity.movies.model.MoviesReqModel
import com.boys.assets.ibm.activity.movies.usecase.MoviesUC
import com.boys.assets.ibm.domain.model.ApiError
import com.boys.assets.ibm.domain.usecase.UseCaseResponse

class MoviesViewModel constructor(private val useCase: MoviesUC) : ViewModel()  {

    private val TAG = this::class.java.simpleName

    val onSuccess = MutableLiveData<Pair<MoviesModel, MoviesReqModel>>()
    val onError = MutableLiveData<String>()

    fun doIt(model: MoviesReqModel) {
        useCase.invoke(
            viewModelScope, model,
            object : UseCaseResponse<MoviesModel> {
                override fun onSuccess(result: MoviesModel) {
                    onSuccess.value = Pair(result, model)
                }

                override fun onError(apiError: ApiError) {
                    onError.value = apiError.getErrorMessage()
                }
            },
        )
    }
}