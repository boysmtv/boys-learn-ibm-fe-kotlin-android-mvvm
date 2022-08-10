package com.boys.assets.ibm.activity.movies.presentation

/**
 * Data validation state of the form.
 */
data class MoviesFormState(
    val idNameError: Int? = null,
    val isDataValid: Boolean = false
)