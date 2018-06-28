package com.deividasstr.data.store.datasource

import androidx.paging.PositionalDataSource
import io.objectbox.query.LazyList

open class ObjectBoxDataSource<T>(
    private val lazyList: LazyList<*>,
    private val converter: (Any) -> T
) : PositionalDataSource<T>() {

    private val size: Int = lazyList.size

    override fun loadInitial(
        params: PositionalDataSource.LoadInitialParams,
        callback: PositionalDataSource.LoadInitialCallback<T>
    ) {
        callback.onResult(
            lazyList.subList(
                params.requestedStartPosition,
                Math.min(params.requestedLoadSize, size)
            ).map(converter),
            params.requestedStartPosition,
            size
        )
    }

    override fun loadRange(
        params: PositionalDataSource.LoadRangeParams,
        callback: PositionalDataSource.LoadRangeCallback<T>
    ) {
        callback.onResult(
            lazyList.subList(
                params.startPosition,
                Math.min(params.startPosition + params.loadSize, size)
            ).map(converter)
        )
    }
}