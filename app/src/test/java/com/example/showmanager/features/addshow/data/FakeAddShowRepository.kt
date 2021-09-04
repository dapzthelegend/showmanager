package com.example.showmanager.features.addshow.data

import co.crop2cash.core.network.utils.Failure
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.core.utils.Either
import com.example.showmanager.features.addshow.domain.AddShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAddShowRepository : AddShowRepository {

    override suspend fun addShow(show: Show): Flow<Either<Failure, Boolean>> = flow {
        if (show.title != null && show.seasons != null) {
            emit(Either.Right(true))
        } else {
            emit(Either.Left(Failure.DataError))
        }
    }
}
