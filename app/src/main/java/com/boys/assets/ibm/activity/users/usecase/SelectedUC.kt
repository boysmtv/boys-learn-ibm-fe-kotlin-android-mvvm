package com.boys.assets.ibm.activity.users.usecase

import com.boys.assets.ibm.activity.users.model.SelectedReqModel
import com.boys.assets.ibm.activity.users.model.SelectedRespModel
import com.boys.assets.ibm.di.network.Repository
import com.boys.assets.ibm.domain.usecase.UseCase

class SelectedUC constructor(
    private val repository: Repository
) : UseCase<SelectedRespModel, Any?>() {

    private val TAG = this::class.java.simpleName
    override suspend fun run(params: Any?): SelectedRespModel {
        val model = params as SelectedReqModel
        return repository.getSelected(model.id, model.api_key)
    }


}