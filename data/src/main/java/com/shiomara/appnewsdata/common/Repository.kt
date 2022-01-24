package com.shiomara.appnewsdata.common

import com.shiomara.appnews.domain.common.Failure
import com.shiomara.appnews.domain.common.HttpError
import com.shiomara.appnews.domain.common.Success
import com.shiomara.appnews.domain.common.Result
import com.shiomara.appnewsdata.network.DomainMapper
import com.shiomara.appnewsdata.utils.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject


abstract class Repository<T : Any, R : DomainMapper<T>> : KoinComponent {
    private val networkUtils: NetworkUtil by inject()

    /**
     * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun fetchData(
        apiDataProvider: suspend () -> Result<T>,
        dbDataProvider: suspend () -> R
    ): Result<T> {
        return if (networkUtils.hasNetworkAccess()) {
            withContext(Dispatchers.IO) {
                apiDataProvider()
            }
        } else {
            withContext(Dispatchers.IO) {
                val dbResult = dbDataProvider()
                if (dbResult != null) Success(dbResult.mapToDomainModel()) else Failure(
                    HttpError(
                        Throwable(DB_ENTRY_ERROR)
                    )
                )
            }
        }
    }

    /**
     * Use this when communicating only with the api service
     */
    protected suspend fun fetchData(dataProvider: suspend () -> Result<T>): Result<T> {
        return if (networkUtils.hasNetworkAccess()) {
            withContext(Dispatchers.IO) {
                dataProvider()
            }
        } else {
            Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
        }
    }

    /**
     * Use this if you need to cache list of data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun fetchDataList(
        apiDataProvider: suspend () -> Result<List<T>>,
        dbDataProvider: suspend () -> List<R>
    ): Result<List<T>> {
        return if (networkUtils.hasNetworkAccess()) {
            withContext(Dispatchers.IO) {
                apiDataProvider()
            }
        } else {
            withContext(Dispatchers.IO) {
                val dbResult = dbDataProvider()
                if (dbResult.isNotEmpty()) Success(dbResult.map { it.mapToDomainModel() })
                else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
            }
        }
    }

    /**
     * Use this when communicating only with the api service
     */
    protected suspend fun fetchDataList(dataProvider: suspend () -> Result<List<T>>): Result<List<T>> {
        return if (networkUtils.hasNetworkAccess()) {
            withContext(Dispatchers.IO) {
                dataProvider()
            }
        } else {
            Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
        }
    }
}