package com.muralex.navstructure.data.mappers.json_data

import android.webkit.URLUtil
import com.muralex.navstructure.app.data.EntityMapper
import com.muralex.navstructure.app.utils.Constants.ICONS_FOLDER
import com.muralex.navstructure.data.model.articles.ArticleData
import com.muralex.navstructure.data.model.json.ArticleJsonData
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
