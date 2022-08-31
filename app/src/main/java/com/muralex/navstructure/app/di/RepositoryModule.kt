package com.muralex.navstructure.app.di

import android.content.Context
import com.muralex.navstructure.data.database.ArticlesDao
import com.muralex.navstructure.data.mappers.db_data.ArticleDBToDataMapper
import com.muralex.navstructure.data.mappers.db_data.DetailDBToDataMapper
import com.muralex.navstructure.data.mappers.json_data.ArticleJsonToDataMapper
import com.muralex.navstructure.data.mappers.json_data.DetailJsonToDataMapper
import com.muralex.navstructure.data.mappers.json_data.SectionJsonToDataMapper
import com.muralex.navstructure.data.mappers.json_data.SectionsArticlesJsonToDataMapper
import com.muralex.navstructure.data.repository.articles.ArticleRepositoryImpl
import com.muralex.navstructure.data.repository.articles.datasource.ArticleDataSourceImpl
import com.muralex.navstructure.data.repository.articles.datasource.ArticlesDataSource
import com.muralex.navstructure.data.repository.articles.datasource.ArticlesJsonUtil
import com.muralex.navstructure.data.repository.navstructure.NavRepositoryImpl
import com.muralex.navstructure.data.repository.navstructure.datasource.SectionsJsonUtil
import com.muralex.navstructure.data.repository.navstructure.datasource.NavDataSource
import com.muralex.navstructure.data.repository.navstructure.datasource.NavDataSourceImpl
import com.muralex.navstructure.domain.repositories.ArticlesRepository
import com.muralex.navstructure.domain.repositories.NavRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSectionsJsonUtil(
        @ApplicationContext appContext: Context,
    ): SectionsJsonUtil {
        return SectionsJsonUtil(appContext)
    }

    @Singleton
    @Provides
    fun provideNavDataSource(
        sectionsJsonUtil: SectionsJsonUtil,
        mapper: SectionJsonToDataMapper,
    ): NavDataSource {
        return NavDataSourceImpl(sectionsJsonUtil, mapper)
    }

    @Singleton
    @Provides
    fun provideNavRepository(
        navDataSource: NavDataSource,
    ): NavRepository {
        return NavRepositoryImpl(navDataSource)
    }

    @Singleton
    @Provides
    fun provideArticlesJsonUtil(
        @ApplicationContext appContext: Context,
    ): ArticlesJsonUtil {
        return ArticlesJsonUtil(appContext)
    }

    @Singleton
    @Provides
    fun provideArticlesDataSource(
        jsonUtil: ArticlesJsonUtil,
        mapper: ArticleJsonToDataMapper,
        collectionsMapper: SectionsArticlesJsonToDataMapper,
        detailMapper: DetailJsonToDataMapper,
        dao: ArticlesDao,
        articleDBToDataMapper: ArticleDBToDataMapper,
        detailDBToDataMapper: DetailDBToDataMapper
    ): ArticlesDataSource {
        return ArticleDataSourceImpl(jsonUtil,
            mapper,
            collectionsMapper,
            detailMapper,
            dao,
            articleDBToDataMapper,
            detailDBToDataMapper
        )
    }

    @Singleton
    @Provides
    fun provideArticleRepository(
        dataSource: ArticlesDataSource,
        navDataSource: NavDataSource,
    ): ArticlesRepository {
        return ArticleRepositoryImpl(dataSource, navDataSource)
    }


}