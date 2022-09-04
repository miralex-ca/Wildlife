package com.muralex.wildlife.app.di

import com.muralex.wildlife.app.utils.Dispatchers
import com.muralex.wildlife.presentation.utils.DelayProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDelayProvide() : DelayProvider = DelayProvider.Base()

    @Singleton
    @Provides
    fun provideDispatchers() : Dispatchers = Dispatchers.Base()

}