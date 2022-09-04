package com.muralex.wildlife.data.model.structure

data class SectionDataWithChildren (
    val section: SectionData,
    val subSections: List<SectionData>,
)

