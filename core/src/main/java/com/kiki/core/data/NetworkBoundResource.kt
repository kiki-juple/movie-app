package com.kiki.core.data

import com.kiki.core.data.source.remote.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDb().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val responseState = createRequest().first()) {
                is ResponseState.Success -> {
                    saveRequestResult(responseState.data)
                    emitAll(loadFromDb().map { Resource.Success(it) })
                }

                is ResponseState.Empty -> {
                    emitAll(loadFromDb().map { Resource.Success(it) })
                }

                is ResponseState.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(message = responseState.error))
                }
            }
        } else {
            emitAll(loadFromDb().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() = Unit

    protected abstract suspend fun saveRequestResult(data: RequestType)

    protected abstract suspend fun createRequest(): Flow<ResponseState<RequestType>>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDb(): Flow<ResultType>

    fun asFlow() = result
}