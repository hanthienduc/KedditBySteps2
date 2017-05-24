package com.hanthienduc.kedditbysteps.api

import retrofit2.Call
import javax.inject.Inject;
/**
 * Created by hanth on 24/05/2017.
 */

class NewsRestAPI @Inject constructor(private val redditApi: RedditApi) : NewsAPI{


    override fun getNews(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }

}