package com.muralex.wildlife.data.mappers.json_data

import android.webkit.URLUtil
import com.muralex.wildlife.app.data.EntityMapper
import com.muralex.wildlife.app.utils.Constants.ICONS_FOLDER
import com.muralex.wildlife.data.model.articles.ArticleData
import com.muralex.wildlife.data.model.json.ArticleJsonData
import javax.inject.Inject

class ArticleJsonToDataMapper @Inject constructor() : EntityMapper<ArticleJsonData, ArticleData> {

    override fun mapFromEntity(data: ArticleJsonData): ArticleData {
        return ArticleData(
            id = data.id ?: "",
            title = data.title ?: "",
            desc = data.desc ?: "",
            text = data.text ?: "",
            image = data.image?.let {
                if (URLUtil.isValidUrl(data.image)) data.image
                else ICONS_FOLDER + data.image
            } ?: ""
        )
    }

    fun mapFromEntityList(entitiesList: List<ArticleJsonData>): List<ArticleData> {
        return entitiesList.map { mapFromEntity(it) }
    }

}
