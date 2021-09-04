package com.example.showmanager.features.showsList.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.crop2cash.core.network.utils.Failure
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.core.network.utils.BaseUseCase
import com.example.showmanager.features.showsList.data.FakeShowListListRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Sola-Aremu Oluwadara on 04/09/2021.
 */
@ExperimentalCoroutinesApi
class GetShowsTest {
    lateinit var getShows: GetShows
    lateinit var repository: FakeShowListListRepository
    lateinit var repositoryShows: List<Show>

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    var shows = emptyList<Show>()

    @Before
    fun setUp() {
        val show1 = Show(
            title = "Avatar",
            seasons = 11,
            id = null,
            releaseDate = null
        )
        val show2 = Show(
            title = "SpongeBob",
            seasons = 1,
            id = null,
            releaseDate = null
        )
        val show3 = Show(
            title = "Victorious",
            seasons = 10,
            id = null,
            releaseDate = null
        )
        repositoryShows = listOf(show1, show2, show3)
        repository = FakeShowListListRepository(repositoryShows)
        getShows = GetShows(repository)
    }

    @Test
    fun run_ReturnsShowList() = runBlockingTest {
        val show = getShows.run(BaseUseCase.None)
        show.collectLatest {
            it.fold(
                ::onFailure,
                ::onSuccess
            )
        }
        assertEquals(repositoryShows, shows)
        assertEquals(repositoryShows.size, shows.size)
    }

    /**
     * Handler on success for [GetShows]
     */
    private fun onSuccess(shows: List<Show>) {
        this.shows = shows
    }

    /**
     * Handler on failure for [GetShows]
     */
    private fun onFailure(failure: Failure) {
    }
}
