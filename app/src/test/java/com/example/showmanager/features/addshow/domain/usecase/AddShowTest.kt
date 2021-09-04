/**
 * Created by Sola-Aremu Oluwadara on 04/09/2021.
 */

package com.example.showmanager.features.addshow.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.crop2cash.core.network.utils.Failure
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.features.addshow.data.FakeAddShowRepository
import com.example.showmanager.features.showsList.domain.usecases.GetShows
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AddShowTest {

    lateinit var addShow: AddShow
    lateinit var repository: FakeAddShowRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    var added = false
    var failure: Failure? = null

    val show1 = Show(
        title = "Avatar",
        seasons = 11,
        id = null,
        releaseDate = null
    )
    val show2 = Show(
        title = "SpongeBob",
        seasons = null,
        id = null,
        releaseDate = null
    )

    @Before
    fun setUp() {
        repository = FakeAddShowRepository()
        addShow = AddShow(repository)
    }

    @Test
    fun run_ReturnsTrueIfSuccessfullyAdded() = runBlockingTest {
        val show = addShow.run(show1)
        show.collectLatest {
            it.fold(
                ::onFailure,
                ::onSuccess
            )
        }
        assertEquals(added, true)
    }

    @Test
    fun runAddInvalidShow_ReturnsFailure() = runBlockingTest {
        val show = addShow.run(show2)
        show.collectLatest {
            it.fold(
                ::onFailure,
                ::onSuccess
            )
        }
        assertThat(failure, instanceOf(Failure.DataError::class.java))
    }

    /**
     * Handler on success for [GetShows]
     */
    private fun onSuccess(status: Boolean) {
        this.added = status
    }

    /**
     * Handler on failure for [GetShows]
     */
    private fun onFailure(failure: Failure) {
        this.failure = failure
    }
}
