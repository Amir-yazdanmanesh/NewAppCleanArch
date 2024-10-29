package com.example.yazdanmanesh

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yazdanmanesh.common_entity.Article

class FakePagingSource(
    private val loadResult: LoadResult<Int, Article>
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return loadResult
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = null
}
