package com.hanthienduc.kedditbysteps

import android.app.Application
import com.hanthienduc.kedditbysteps.di.AppModule
import com.hanthienduc.kedditbysteps.di.news.NewsComponent

/**
 * Created by hanth on 24/05/2017.
 */

class KedditApp : Application() {

    companion object {
        lateinit var newsComponent: NewsComponent
    }

    override fun onCreate() {
        super.onCreate()
        newsComponent = DaggerNewsComponent.builder()
                .appModule(AppModule(this))
                .build();
    }
}