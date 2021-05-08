package com.fedyshyn.paging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fedyshyn.paging.data.GithubRepository
import com.fedyshyn.paging.model.Repo
import kotlinx.coroutines.flow.Flow

class SearchRepositoriesViewModel(private val repository: GithubRepository) : ViewModel() {

    private var currentSearchResult: Flow<PagingData<Repo>>? = null
    private var currentQueryValue: String? = null

    fun searchRepo(queryString: String): Flow<PagingData<Repo>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }

        val newResult: Flow<PagingData<Repo>> = repository.getSearchResultStream(queryString).cachedIn(viewModelScope)

        currentQueryValue = queryString
        currentSearchResult = newResult

        return newResult
    }
}