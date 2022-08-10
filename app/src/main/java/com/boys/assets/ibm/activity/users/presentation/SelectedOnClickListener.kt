package com.boys.assets.ibm.activity.users.presentation

import android.view.View
import com.boys.assets.ibm.activity.users.model.SelectedRespModel

interface SelectedOnClickListener<T> {
    fun onItemClick(v: View?, position: Int, model: SelectedRespModel)
}