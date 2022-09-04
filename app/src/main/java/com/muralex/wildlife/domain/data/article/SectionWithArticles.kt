package com.muralex.wildlife.domain.data.article

import com.muralex.wildlife.domain.data.navstructure.Section

data class SectionWithArticles (
    val section: Section,
    val articles: List<Article>,
)

