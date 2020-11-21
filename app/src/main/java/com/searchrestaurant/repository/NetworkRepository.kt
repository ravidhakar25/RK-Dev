package com.searchrestaurant.repository

import androidx.lifecycle.MutableLiveData
import com.searchrestaurant.models.SearchResult


class NetworkRepository {
    companion object {
        private var networkRepository: NetworkRepository? = null
        fun instance(): NetworkRepository {
            synchronized(this) {
                if (networkRepository == null)
                    networkRepository = NetworkRepository()
                return networkRepository!!
            }
        }
    }

    init {
        if (networkRepository != null) {
            throw Exception("Use Single instance")
        }
    }


    suspend fun nearBySearch(
        mResult: MutableLiveData<SearchResult>
    ) {
        try {
            mResult.postValue(
                NetworkClient.instance().create(ApiServices::class.java)
                    .nearBySearch()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            mResult.postValue(null)
        }
    }
    suspend fun nearBySearch(keyword:String,
        mResult: MutableLiveData<SearchResult>
    ) {
        try {
            mResult.postValue(
                NetworkClient.instance().create(ApiServices::class.java)
                    .nearBySearch(keyword)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            mResult.postValue(null)
        }
    }

}