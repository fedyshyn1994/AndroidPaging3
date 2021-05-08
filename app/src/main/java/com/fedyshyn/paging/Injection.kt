package com.fedyshyn.paging

import com.fedyshyn.paging.api.GithubService
import com.fedyshyn.paging.data.GithubRepository
import com.fedyshyn.paging.ui.ViewModelFactory

object Injection {

    private fun provideGithubRepository() = GithubRepository(GithubService.create())

    fun provideViewModelFactory() = ViewModelFactory(provideGithubRepository())
}