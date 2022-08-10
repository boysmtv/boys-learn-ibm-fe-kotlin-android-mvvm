package com.boys.assets.ibm.domain.usecase

import com.boys.assets.ibm.domain.model.ApiError

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError)

}

