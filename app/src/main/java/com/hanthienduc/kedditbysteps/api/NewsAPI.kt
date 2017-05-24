package com.hanthienduc.kedditbysteps.api

import retrofit2.Call

/**
 * Created by hanth on 24/05/2017.
 */

interface NewsAPI{
    fun getNews(after: String, limit: String): Call<RedditNewsResponse>
}