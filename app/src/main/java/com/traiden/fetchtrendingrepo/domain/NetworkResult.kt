package com.traiden.fetchtrendingrepo.domain

sealed class NetworkResult<T>(open val data: T? = null) {

    data class Success<T>(override val data: T) : NetworkResult<T>(data)

    class Error<T>(data: T? = null) : NetworkResult<T>(data)

    class Loading<T> : NetworkResult<T>()

}