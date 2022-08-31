package com.muralex.navstructure.data.model.structure

import com.muralex.navstructure.data.model.articles.ArticleData

data class SectionDataWithArticles(
    val section: SectionData,
    val articles: List<ArticleData>,
)
