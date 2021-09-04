/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.core.di

import com.apollographql.apollo.ApolloClient
import com.example.showmanager.BuildConfig
import com.example.showmanager.core.network.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * Class that contributes to the application's object graph.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val SERVER_URL =
            "https://tv-show-manager.combyne.com/graphql"
    }

    /**
     * Create a provider method binding for [OkHttpClient].
     *
     * @return Instance of okhttpClient.
     * @see Provides
     */
    @Provides
    @Singleton
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient =
        ApolloClient.builder()
            .serverUrl(SERVER_URL)
            .okHttpClient(okHttpClient)
            .build()

    /**
     * Create a provider method binding for [HttpLoggingInterceptor].
     *
     * @return Instance of http interceptor.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    /**
     * Create a provider method binding for [OkHttpClient].
     *
     * @return Instance of http client.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideHttpClient(
        interceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(interceptor)
        }
        clientBuilder.addInterceptor(authorizationInterceptor)
        return clientBuilder.build()
    }

    /**
     * Create a provider method binding for [AuthorizationInterceptor].
     *
     * @return Instance of authorization interceptor.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideAuthorizationInterceptor() =
        AuthorizationInterceptor()
}
