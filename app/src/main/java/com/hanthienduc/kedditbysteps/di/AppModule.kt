package com.hanthienduc.kedditbysteps.di

import android.content.Context
import com.hanthienduc.kedditbysteps.KedditApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by hanth on 24/05/2017.
 */

@Module
class AppModule(val app: KedditApp) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app;
    }

    @Provides
    @Singleton
    fun provideApplication(): KedditApp {
        return app;
    }
}