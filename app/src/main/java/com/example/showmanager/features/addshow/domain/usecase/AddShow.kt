/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.features.addshow.domain.usecase

import co.crop2cash.core.network.utils.Failure
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.core.network.utils.BaseUseCase
import com.example.showmanager.core.utils.Either
import com.example.showmanager.features.addshow.domain.AddShowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case to add new tv show.
 */
class AddShow @Inject constructor(
    private val repository: AddShowRepository
) : BaseUseCase<Show, Boolean>() {

    /**
     * Called to run use case.
     *
     * @param params The tv show to be added.
     * @return Flow of Either failure if an error occurs or completed status.
     */
    override suspend fun run(params: Show): Flow<Either<Failure, Boolean>> =
        repository.addShow(params)
}
