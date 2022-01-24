package com.shiomara.appnewsdata.network

import com.shiomara.appnews.domain.common.Failure
import com.shiomara.appnews.domain.common.HttpError
import com.shiomara.appnews.domain.common.Result
import com.shiomara.appnews.domain.common.Success
import com.shiomara.appnewsdata.common.DB_ENTRY_ERROR
import com.shiomara.appnewsdata.common.GENERAL_NETWORK_ERROR
import com.shiomara.appnewsdata.noticias.ApiResponse
import retrofit2.Response
import java.io.IOException


interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (HttpError) -> Unit) {
    if (!isSuccessful) errorBody()?.run { action(HttpError(Throwable(message()), code())) }
}

/**
 * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
 */
inline fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<T>.getData(
    cacheAction: (R) -> Unit,
    fetchFromCacheAction: () -> R
): Result<U> {
    try {
        onSuccess {
            val databaseEntity = it.mapToRoomEntity()
            cacheAction(databaseEntity)
            return Success(databaseEntity.mapToDomainModel())
        }
        onFailure {
            val cachedModel = fetchFromCacheAction()
            if (cachedModel != null) Success(cachedModel.mapToDomainModel()) else Failure(
                HttpError(
                    Throwable(DB_ENTRY_ERROR)
                )
            )
        }
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}

/**
 * Use this when communicating only with the api service
 */
fun <T : DomainMapper<R>, R : Any> Response<T>.getData(): Result<R> {
    try {
        onSuccess { return Success(it.mapToDomainModel()) }
        onFailure { return Failure(it) }
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}

/**
 * Use this function when you need to fetch list of data from the API
 */
fun <T : DomainMapper<R>, R : Any> Response<List<T>>.getDataAsList(): Result<List<R>> {
    try {
        onSuccess { responseList -> return Success(responseList.map { it.mapToDomainModel() }) }
        onFailure { return Failure(it) }
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}

fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<ApiResponse<T>>.getDataAsListApi(): Result<List<U>> {
    try {
        onSuccess { responseList ->
            val listOfEntities = responseList.hits.map { it.mapToRoomEntity() }
            return Success(listOfEntities.map { it.mapToDomainModel() })
        }
        onFailure {
            return Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
        }
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}

fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<ApiResponse<T>>.getDataAsListRoom(): Result<List<R>> {
    try {
        onSuccess { responseList ->
            val listOfEntities = responseList.hits.map { it.mapToRoomEntity() }
            return Success(listOfEntities)
        }
        onFailure {
            return Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
        }
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}

/**
 * Use this function when you need to fetch list of data from the API and cache it locally
 */
inline fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<List<T>>.getDataAsList(
    cacheAction: (List<R>) -> Unit,
    fetchFromCacheAction: () -> List<R>
): Result<List<U>> {
    try {
        onSuccess { responseList ->
            val listOfEntities = responseList.map { it.mapToRoomEntity() }
            cacheAction(listOfEntities)
            return Success(listOfEntities.map { it.mapToDomainModel() })
        }
        onFailure {
            val cachedList = fetchFromCacheAction()
            if (cachedList.isNotEmpty()) Success(cachedList.map { it.mapToDomainModel() })
            else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
        }
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}