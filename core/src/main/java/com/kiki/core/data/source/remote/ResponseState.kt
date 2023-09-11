package com.kiki.core.data.source.remote

sealed class ResponseState<out T> {

    data class Success<out T>(val data: T) : ResponseState<T>()

    data class Error(val error: String) : ResponseState<Nothing>()

    data object Empty : ResponseState<Nothing>()
}