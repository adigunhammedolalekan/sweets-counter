package com.deividasstr.domain.usecases

import com.deividasstr.domain.framework.CompletableUseCase
import com.deividasstr.domain.repositories.PrefsRepo
import io.reactivex.Completable

class SaveDownloadSweetDateUseCase(private val repo: PrefsRepo) : CompletableUseCase {

    override fun execute(): Completable {
        return repo.saveSweetsDownloadTime()
    }
}