package com.app.assestmentloadingpager

import ItemPagingSource
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

class ItemViewModel(private val apiService: ApiService) : ViewModel() {

    fun getItems(): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = 10)
        ) {
            ItemPagingSource(apiService)
        }.flow.cachedIn(viewModelScope)
    }
}