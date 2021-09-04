/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.features.showsList.di

import com.apollographql.apollo.ApolloClient
import com.example.showmanager.features.addshow.domain.AddShowRepository
import com.example.showmanager.features.showsList.data.ShowsListRepositoryImpl
import com.example.showmanager.features.showsList.domain.ShowsListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Class that contributes to the application's object graph.
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    /**
     * Create a provider method binding for [AddShowRepository].
     *
     * @return Instance of add show repository.
     * @see Provides
     */
    @Provides
    fun provideShowsListRepository(apolloClient: ApolloClient): ShowsListRepository =
        ShowsListRepositoryImpl(apolloClient)
}
