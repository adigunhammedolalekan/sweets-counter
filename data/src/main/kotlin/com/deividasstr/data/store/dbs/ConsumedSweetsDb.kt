package com.deividasstr.data.store.dbs

import com.deividasstr.data.store.daos.ConsumedSweetsDao
import com.deividasstr.data.store.models.ConsumedSweetDb
import com.deividasstr.data.store.models.ConsumedSweetDb_
import com.deividasstr.data.store.utils.RxObjectBoxQuery
import com.deividasstr.domain.entities.DateRange
import io.objectbox.Box
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
class ConsumedSweetsDb(val db: Box<ConsumedSweetDb>) : ConsumedSweetsDao {

    override fun getAllConsumedSweets(): Single<List<ConsumedSweetDb>> {
        return RxObjectBoxQuery.singleList(db.query().orderDesc(ConsumedSweetDb_.date).build())
    }

    override fun addSweet(sweet: ConsumedSweetDb): Completable {
        return Completable.fromAction {
            db.put(sweet)
        }
    }

    override fun getConsumedSweetsByPeriod(dateRange: DateRange): Single<List<ConsumedSweetDb>> {
        val query = db.query().between(
            ConsumedSweetDb_.date,
            dateRange.start,
            dateRange.endInclusive
        ).build()
        return RxObjectBoxQuery.singleList(query)
    }
}