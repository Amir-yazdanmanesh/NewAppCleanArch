package com.example.yazdanmanesh.ui.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazdanmanesh.usecase.NotifyNewArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(useCase: NotifyNewArticlesUseCase) : ViewModel() {
    private val _newArticlesCount = MutableSharedFlow<Int>()
    val newArticlesCount: SharedFlow<Int> = _newArticlesCount

    init {
        notifyNewArticle()
    }

    private val sharedFlow = useCase.execute()
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

    private fun notifyNewArticle() {
        viewModelScope.launch {
            sharedFlow.collectLatest { count ->
                _newArticlesCount.emit(count)
            }
        }
    }
}
