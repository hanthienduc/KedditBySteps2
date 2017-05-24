package com.hanthienduc.kedditbysteps.di.news

import com.hanthienduc.kedditbysteps.api.NewsAPI
import com.hanthienduc.kedditbysteps.api.NewsRestAPI
import com.hanthienduc.kedditbysteps.api.RedditApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by hanth on 24/05/2017.
 */
@Module
class NewsModule {

    @Provides
    @Singleton
    fun provideNewsAPI(redditApi: RedditApi): NewsAPI {
        return NewsRestAPI(redditApi)
    }

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditApi {
        return retrofit.create(RedditApi::class.java)
    }

    /**
     * NewsManager is automatically provided by Dagger as we set the @Inject annotation in the
     * constructor, so we can avoid adding a 'provider method' here.
     */
}