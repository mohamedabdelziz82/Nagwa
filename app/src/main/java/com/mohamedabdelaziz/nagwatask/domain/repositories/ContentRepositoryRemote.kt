package com.mohamedabdelaziz.nagwatask.domain.repositories

import com.mohamedabdelaziz.nagwatask.domain.entities.Content
import kotlinx.coroutines.flow.Flow

interface ContentRepositoryRemote {
    suspend fun getTrendingList(): Flow<List<Content>>
}