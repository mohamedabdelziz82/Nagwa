package com.mohamedabdelaziz.nagwatask.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.mohamedabdelaziz.nagwatask.domain.entities.Content
import androidx.lifecycle.viewModelScope
import com.mohamedabdelaziz.nagwatask.domain.usecase.ContentRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
 class ContentViewModel @Inject constructor(
    private var contentsRemoteUseCase: ContentRemoteUseCase
) : ViewModel() {
    var contentsMutableLiveData = MutableLiveData<List<Content>>()
    init {
        getTrendingListRemote()
    }

    private fun getTrendingListRemote() {
        viewModelScope.launch {
            contentsRemoteUseCase.invokeTrendingList().catch {
                Log.e(TAG, it.message!!)
            }.collect {
                contentsMutableLiveData.value = it
            }
        }

    }


    companion object {
        private const val TAG = "ContentViewModel"
    }
}