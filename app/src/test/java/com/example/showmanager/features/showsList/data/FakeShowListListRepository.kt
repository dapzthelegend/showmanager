package com.example.showmanager.features.showsList.data

import co.crop2cash.core.network.utils.Failure
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.core.utils.Either
import com.example.showmanager.features.showsList.domain.ShowsListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeShowListListRepository(val shows: List<Show>) : ShowsListRepository {

    override suspend fun getShows(): Flow<Either<Failure, List<Show>>> = flow {
        emit(Either.Right(shows))
    }
}
