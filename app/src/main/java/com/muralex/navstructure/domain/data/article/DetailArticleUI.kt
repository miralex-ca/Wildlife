package com.muralex.navstructure.domain.data.article

data class DetailArticleUI(
    val article: Article,
    val sectionId: String,
    val previousId: String?,
    val nextId: String?
)