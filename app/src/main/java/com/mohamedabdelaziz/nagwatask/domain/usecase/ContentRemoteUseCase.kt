package com.mohamedabdelaziz.nagwatask.domain.usecase

import com.mohamedabdelaziz.nagwatask.domain.entities.Content
import javax.inject.Inject
import com.mohamedabdelaziz.nagwatask.domain.repositories.ContentRepositoryRemote
import kotlinx.coroutines.flow.Flow


class ContentRemoteUseCase @Inject constructor(private var repository: ContentRepositoryRemote){
    suspend fun invokeTrendingList(): Flow<List<Content>> =repository.getTrendingList()

}