package com.boys.assets.ibm.activity.users.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boys.assets.ibm.activity.users.model.SelectedReqModel
import com.boys.assets.ibm.activity.users.model.SelectedRespModel
import com.boys.assets.ibm.activity.users.usecase.SelectedUC
import com.boys.assets.ibm.domain.model.ApiError
import com.boys.assets.ibm.domain.usecase.UseCaseResponse

class SelectedViewModel constructor(
    private val selectedUC: SelectedUC
) : ViewModel()  {

    private val TAG = this::class.java.simpleName

    val onSuccess = MutableLiveData<SelectedRespModel>()
    val onError = MutableLiveData<String>()

    fun doIt(model: SelectedReqModel) {
        selectedUC.invoke(
            viewModelScope, model,
            object : UseCaseResponse<SelectedRespModel> {
                override fun onSuccess(result: SelectedRespModel) {
                    onSuccess.value = result
                }

                override fun onError(apiError: ApiError) {
                    onError.value = apiError.getErrorMessage()
                }
            },
        )
    }
}