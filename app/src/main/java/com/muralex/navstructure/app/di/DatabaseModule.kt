package com.muralex.navstructure.app.di

import android.content.Context
import androidx.room.Room
import com.muralex.navstructure.app.utils.Constants.DATABASE_NAME
import com.muralex.navstructure.data.database.AppDatabase
import com.muralex.navstructure.data.database.ArticlesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext appContext: Context) : AppDatabase {
        return AppDatabase.getInstance(appContext)
    }

    @Singleton
    @Provides
    fun providesDao(database: AppDatabase) : ArticlesDao {
        return database.articlesDAO
    }

}