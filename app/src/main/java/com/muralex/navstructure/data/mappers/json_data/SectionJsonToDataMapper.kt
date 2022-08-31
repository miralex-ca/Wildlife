package com.muralex.navstructure.data.mappers.json_data

import com.muralex.navstructure.app.data.EntityMapper
import com.muralex.navstructure.app.utils.Constants.ICONS_FOLDER
import com.muralex.navstructure.data.model.structure.SectionData
import com.muralex.navstructure.data.model.structure.SectionJsonData
import javax.inject.Inject

class SectionJsonToDataMapper @Inject constructor() : EntityMapper<SectionJsonData, SectionData> {

    override fun mapFromEntity(data: SectionJsonData): SectionData {
        return SectionData(
            id = data.id ?: "",
            title = data.title ?: "",
            desc = data.desc ?: "",
            image = data.image?.let { ICONS_FOLDER + data.image } ?: "",
            parent = data.parent ?: "",
            type = data.type ?: "items",
        )
    }

    fun mapFromEntityList(entitiesList: List<SectionJsonData>): List<SectionData> {
        return entitiesList.map { mapFromEntity(it) }
    }

}
