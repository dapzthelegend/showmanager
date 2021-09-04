/**
 * Created by Sola-Aremu Oluwadara on 10/08/2021.
 * Copyright (c) 2021 Crop2Cash. All rights reserved.
 */

package com.dapzthelegend.spacexodyssey.core.flow

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * A lifecycle aware flow that only emits events when an explicit call to emit is made.
 * This eliminates the problem of emitting events when there is no active collector.
 *
 * The collector is only going to be notified of changes.
 */
class SingleFlow<T> {
    private val channel = Channel<T>()
    private val flow = channel.receiveAsFlow()

    /**
     * Called to send events to the channel.
     */
    suspend fun emit(t: T) {
        channel.send(t)
    }

    /**
     * Collect events stream.
     *
     * @param lifecycle The lifecycle on which the event is collector
     * @param collector The event consumer
     */
    suspend fun collect(lifecycle: Lifecycle, collector: (T) -> Unit) {
        flow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .collect {
                if (it != null) collector(it)
            }
    }
}
