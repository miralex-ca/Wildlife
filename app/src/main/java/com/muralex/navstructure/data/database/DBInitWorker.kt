package com.muralex.navstructure.data.database

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.muralex.navstructure.app.utils.SettingsManager
import com.muralex.navstructure.data.mappers.json_db.ArticleJsonToDataDBMapper
import com.muralex.navstructure.data.mappers.json_db.DetailJsonToDataDBMapper
import com.muralex.navstructure.data.mappers.json_db.SectionsArticlesJsonToDBMapper
import com.muralex.navstructure.data.model.json.ArticleJsonData
import com.muralex.navstructure.data.model.json.DetailJsonData
import com.muralex.navstructure.data.model.json.SectionArticlesJsonData
import com.muralex.navstructure.domain.usecases.articles.GetSectionWithArticlesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltWorker
class DBInitWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val dao: ArticlesDao,
    private val settingsManager: SettingsManager
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val size = dao.getAllArticles().size
            settingsManager.setFirstLaunch(true)
            Timber.d("DB init: Articles count: $size")
            Result.success()
        } catch (ex: Exception) {
            Result.failure()
        }
    }
}
