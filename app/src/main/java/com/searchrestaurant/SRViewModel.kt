package com.searchrestaurant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.searchrestaurant.models.SearchResult
import com.searchrestaurant.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SRViewModel() : ViewModel() {
    val mSearchResult = MutableLiveData<SearchResult>()
    private var mLastSearchRequest: Job? = null
    fun nearBySearch() {
        cancelLastSearch()
        mLastSearchRequest = viewModelScope.launch(Dispatchers.IO) {
            NetworkRepository.instance().nearBySearch(mSearchResult)

        }
    }

    private fun cancelLastSearch() {
        mLastSearchRequest?.let {
            if (it.isActive)
                it.cancel()
        }
    }

    fun nearBySearch(keyword: String) {
        cancelLastSearch()
        mLastSearchRequest = viewModelScope.launch(Dispatchers.IO) {
            NetworkRepository.instance().nearBySearch(keyword, mSearchResult)
        }
    }

}