package com.muralex.navstructure.data.model.structure

data class SectionDataWithChildren (
    val section: SectionData,
    val subSections: List<SectionData>,
)

