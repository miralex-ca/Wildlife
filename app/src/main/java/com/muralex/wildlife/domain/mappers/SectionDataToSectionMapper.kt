package com.muralex.wildlife.domain.mappers

import com.muralex.wildlife.app.data.EntityMapper
import com.muralex.wildlife.data.model.structure.SectionData
import com.muralex.wildlife.domain.data.navstructure.Section
import javax.inject.Inject

class SectionDataToSectionMapper @Inject constructor() : EntityMapper<SectionData, Section> {

    override fun mapFromEntity(data: SectionData): Section {
        return Section(
            id = data.id,
            title = data.title,
            desc = data.desc,
            image = data.image,
            parent = data.parent,
            type = data.type
        )
    }

    fun mapFromEntityList(entitiesList: List<SectionData>): List<Section> {
        return entitiesList.map { mapFromEntity(it) }
    }

}
