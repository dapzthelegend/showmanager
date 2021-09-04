/**
 * Created by Sola-Aremu Oluwadara on 04/09/2021.
 */

package com.example.showmanager.features.showsList.domain

import co.crop2cash.core.network.utils.Failure
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.core.utils.Either
import kotlinx.coroutines.flow.Flow

interface ShowsListRepository {

    suspend fun getShows():
        Flow<Either<Failure, List<Show>>>
}
