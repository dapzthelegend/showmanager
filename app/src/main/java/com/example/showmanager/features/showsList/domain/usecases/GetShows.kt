/**
 * Created by Sola-Aremu Oluwadara on 04/09/2021.
 */

package com.example.showmanager.features.showsList.domain.usecases

import co.crop2cash.core.network.utils.Failure
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.core.network.utils.BaseUseCase
import com.example.showmanager.core.utils.Either
import com.example.showmanager.features.showsList.domain.ShowsListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShows @Inject constructor(
    private val showsListRepository: ShowsListRepository
) : BaseUseCase<BaseUseCase.None, List<Show>>() {

    override suspend fun run(params: None): Flow<Either<Failure, List<Show>>> =
        showsListRepository.getShows()
}
