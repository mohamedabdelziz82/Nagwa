package com.mohamedabdelaziz.nagwatask.data.repositoryimp

import javax.inject.Inject
import com.mohamedabdelaziz.nagwatask.data.datasource.remote.ApiService
import com.mohamedabdelaziz.nagwatask.domain.repositories.ContentRepositoryRemote
import com.mohamedabdelaziz.nagwatask.domain.entities.Content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers.IO
 import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * This repository is responsible for
 * fetching data from server
 */
class ContentRepositoryRemoteImp @Inject constructor(private val apiService: ApiService) : ContentRepositoryRemote {
       override suspend fun getTrendingList():  Flow<List<Content>> = flow {
           emit(apiService.getContents())
       }.flowOn(IO)
}