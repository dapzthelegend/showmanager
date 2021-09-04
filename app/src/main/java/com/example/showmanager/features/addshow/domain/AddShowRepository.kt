/**
 * Created by Sola-Aremu Oluwadara on 04/09/2021.
 */

package com.example.showmanager.features.addshow.domain

import co.crop2cash.core.network.utils.Failure
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.core.utils.Either
import kotlinx.coroutines.flow.Flow

/**
 * Repository module for handling add tv show data operations.
 */
interface AddShowRepository {

    /**
     * Add to shows list.
     *
     * @param show The new tv show to be added.
     * @return Flow of failure or status is successful.
     */
    suspend fun addShow(show: Show):
        Flow<Either<Failure, Boolean>>
}
