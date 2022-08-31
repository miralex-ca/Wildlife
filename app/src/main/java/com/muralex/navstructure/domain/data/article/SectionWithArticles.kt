package com.muralex.navstructure.domain.data.article

import com.muralex.navstructure.domain.data.navstructure.Section

data class SectionWithArticles (
    val section: Section,
    val articles: List<Article>,
)

