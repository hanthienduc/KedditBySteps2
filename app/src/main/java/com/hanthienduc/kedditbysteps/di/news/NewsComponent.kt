package com.hanthienduc.kedditbysteps.di.news

import com.hanthienduc.kedditbysteps.di.AppModule
import com.hanthienduc.kedditbysteps.di.NetworkModule
import com.hanthienduc.kedditbysteps.feature.NewsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by hanth on 24/05/2017.
 */

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NewsModule::class,
        NetworkModule::class)
)
interface NewsComponent {

    fun inject(newsFragment: NewsFragment)

}