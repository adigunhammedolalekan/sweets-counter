package com.deividasstr.ui.features.sweetsearchlist

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.deividasstr.ui.base.framework.BaseViewModel
import com.deividasstr.ui.base.models.SweetUi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SweetsSearchListViewModel
@Inject constructor(private val dataSourceFactory: SweetSearchDataSourceFactory) : BaseViewModel() {

    var query: String = ""

    companion object {
        private const val PAGE_SIZE = 10
        private const val PREFETCH_DISTANCE = 20
    }

    val sweets: LiveData<PagedList<SweetUi>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .setEnablePlaceholders(true)
            .build()

        sweets = LivePagedListBuilder(dataSourceFactory, config).build()
    }

    fun searchSweets(text: String) {
        query = text
        dataSourceFactory.search(text)
        sweets.value?.dataSource?.invalidate()
    }
}