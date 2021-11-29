package com.mohamedabdelaziz.nagwatask.presentation.viewmodels

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mohamedabdelaziz.nagwatask.core.utils.constants.Constants
import com.mohamedabdelaziz.nagwatask.data.datasource.remote.ApiService
import com.mohamedabdelaziz.nagwatask.data.repositoryimp.ContentRepositoryRemoteImp
import com.mohamedabdelaziz.nagwatask.domain.entities.Content
import com.mohamedabdelaziz.nagwatask.domain.usecase.ContentRemoteUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4ClassRunner::class)
class ContentViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var viewModel: ContentViewModel

    private lateinit var apiService: ApiService

    lateinit var repositoryRemote: ContentRepositoryRemoteImp

     @Before
    fun init() {
        apiService = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL+Constants.CONTENTS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
         repositoryRemote= ContentRepositoryRemoteImp(apiService)
        var contentRemoteUseCase=ContentRemoteUseCase(repositoryRemote)
         viewModel = ContentViewModel(contentRemoteUseCase)
    }

    @Test
    fun testTrendingViewModel() {
        init()
        viewModel.contentsMutableLiveData.observeForever { trending: List<Content> ->
            Assert.assertEquals(
                trending.size.toLong(),
                4
            )
        }
    }
}