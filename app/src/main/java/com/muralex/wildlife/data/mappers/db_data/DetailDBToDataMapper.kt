package com.muralex.wildlife.data.mappers.db_data

import com.muralex.wildlife.app.data.EntityMapper
import com.muralex.wildlife.app.utils.checkArticleImageUrl
import com.muralex.wildlife.data.model.articles.DetailData
import com.muralex.wildlife.data.model.db.DetailDataDB
import javax.inject.Inject

class DetailDBToDataMapper @Inject constructor() : EntityMapper<DetailDataDB, DetailData> {

    override fun mapFromEntity(data: DetailDataDB): DetailData {
        return DetailData(
            id = data.id,
            title = data.title,
            desc = data.desc,
            text = data.text,
            image = data.image.checkArticleImageUrl()
        )
    }

    fun mapFromEntityList(entitiesList: List<DetailDataDB>): List<DetailData> {
        return entitiesList.map { mapFromEntity(it) }
    }

}
