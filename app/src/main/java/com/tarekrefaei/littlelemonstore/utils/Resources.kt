package com.tarekrefaei.littlelemonstore.utils

sealed class Resources<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resources<T>(data = data)
    class Error<T>(data: T? = null, message: String) : Resources<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true) : Resources<T>(null)
}
