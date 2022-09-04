package com.muralex.wildlife.data.model.structure

import com.muralex.wildlife.data.model.articles.ArticleData

data class SectionDataWithArticles(
    val section: SectionData,
    val articles: List<ArticleData>,
)
