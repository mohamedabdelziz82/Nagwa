package com.mohamedabdelaziz.nagwatask.data.datasource.remote

import com.mohamedabdelaziz.nagwatask.core.utils.constants.Constants.Companion.CONTENTS
import retrofit2.http.GET
import com.mohamedabdelaziz.nagwatask.domain.entities.Content
interface ApiService {
    @GET(CONTENTS)
    suspend fun getContents(): List<Content>
 }