/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.features.addshow.data

import co.crop2cash.core.network.utils.Failure
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.toInput
import com.apollographql.apollo.coroutines.await
import com.example.showmanager.AddShowMutation
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.core.utils.Either
import com.example.showmanager.features.addshow.domain.AddShowRepository
import com.example.showmanager.type.CreateMovieFieldsInput
import com.example.showmanager.type.CreateMovieInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

/**
 * Repository module for handling add tv show data operations.
 */
class AddShowRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : AddShowRepository {

    /**
     * Add to shows list.
     *
     * @param show The new tv show to be added.
     * @return Flow of failure or status is successful.
     */
    override suspend fun addShow(show: Show):
        Flow<Either<Failure, Boolean>> = flow {
            try {
                val fieldsInput = CreateMovieFieldsInput(
                    title = show.title!!,
                    releaseDate = show.releaseDate.toInput(),
                    seasons = show.seasons?.toDouble().toInput()
                ).toInput()

                val input = CreateMovieInput(fieldsInput)

                val response = apolloClient.mutate(AddShowMutation(input)).await()

                if (!response.hasErrors()) {
                    emit(Either.Right(true))
                    return@flow
                }
                emit(Either.Left(Failure.DataError))
            } catch (e: Exception) {
                if (e is IOException) {
                    emit(Either.Left(Failure.NetworkError))
                    return@flow
                }
                emit(Either.Left(Failure.ServerError))
            }
        }
}
