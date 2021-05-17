package com.lucascabral.paging3app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lucascabral.paging3app.network.CharacterData
import com.lucascabral.paging3app.network.RetrofitInstance
import com.lucascabral.paging3app.network.RetrofitService
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel: ViewModel() {

    private val retrofitService: RetrofitService = RetrofitInstance.getRetrofitIntance().create(RetrofitService::class.java)

    fun getListData(): Flow<PagingData<CharacterData>> {
        return Pager (config = PagingConfig(pageSize = 34, maxSize = 200),
        pagingSourceFactory = {CharacterPagingSource(retrofitService)}).flow.cachedIn(viewModelScope)
    }
}