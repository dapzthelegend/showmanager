/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.core.di

import com.apollographql.apollo.ApolloClient
import com.example.showmanager.features.addshow.data.AddShowRepositoryImpl
import com.example.showmanager.features.addshow.domain.AddShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
    @Singleton
    @Provides
    fun provideAddShowRepository(apolloClient: ApolloClient): AddShowRepository =
        AddShowRepositoryImpl(apolloClient)
}
