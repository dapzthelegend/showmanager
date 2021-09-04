/**
 * Created by Sola-Aremu Oluwadara on 17/08/2021.
 * Copyright (c) 2021 Crop2Cash. All rights reserved.
 */

package com.example.showmanager.core.network.utils

import co.crop2cash.core.network.utils.Failure
import com.example.showmanager.core.utils.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Base class for data layer use cases.
 */
abstract class BaseUseCase<in Params, out Type> where Type : Any {

    object None

    /**
     * Run use case.
     *
     * @param params request parameters.
     */
    abstract suspend fun run(params: Params): Flow<Either<Failure, Type>>

    /**
     * Called to invoke operation.
     *
     * @param job The couroutine job
     * @param params Request parameters.
     * @param onResult Block of code to be executed on result.
     */
    open operator fun invoke(
        job: Job,
        params: Params,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        val backgroundJob = CoroutineScope(job + Dispatchers.IO).async { run(params) }
        CoroutineScope(job + Dispatchers.Main).launch {
            val await = backgroundJob.await()
            await.catch {
                onResult(Either.Left(Failure.ServerError))
            }.collect {
                onResult(it)
            }
        }
    }
}
