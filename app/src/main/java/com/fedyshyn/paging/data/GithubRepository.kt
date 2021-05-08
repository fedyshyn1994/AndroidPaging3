package com.fedyshyn.paging.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fedyshyn.paging.api.GithubService
import com.fedyshyn.paging.model.Repo
import kotlinx.coroutines.flow.Flow

class GithubRepository(private val service: GithubService) {

    fun getSearchResultStream(query: String): Flow<PagingData<Repo>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { GithubPagingSource(service, query) }
    ).flow

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}