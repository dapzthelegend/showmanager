package com.example.showmanager.features.showsList.data

import co.crop2cash.core.network.utils.Failure
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.example.showmanager.ShowsListQuery
import com.example.showmanager.commons.utils.formatDateReverse
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.core.utils.Either
import com.example.showmanager.features.addshow.data.ShowsError
import com.example.showmanager.features.showsList.domain.ShowsListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

/**
 * Repository module for handling marvel API network operations [MarvelService]
 */
class ShowsListRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : ShowsListRepository {
    var cursor: String? = null

    override suspend fun getShows(): Flow<Either<Failure, List<Show>>> = flow {
        try {
            val response =
                apolloClient.query(ShowsListQuery(cursor = Input.fromNullable(cursor))).await()
            val newShows = response.data?.movies?.edges?.map {
                val show = it?.node
                Show(
                    id = show?.id,
                    title = show?.title,
                    seasons = show?.seasons?.toInt(),
                    releaseDate = formatDateReverse(show?.releaseDate?.toString() ?: "")
                )
            }

            if (newShows != null) {
                emit(Either.Right(newShows))
            }
            if (response.data?.movies?.pageInfo?.hasNextPage != true) {
                emit(Either.Left(ShowsError.NoMoreShows))
            } else {
                cursor = response.data?.movies?.pageInfo?.endCursor
            }
        } catch (e: Exception) {
            if (e is IOException) {
                emit(Either.Left(Failure.NetworkError))
                return@flow
            }
            emit(Either.Left(Failure.ServerError))
        }
    }
}
