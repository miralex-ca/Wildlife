package com.muralex.wildlife.domain.data.article

data class DetailArticleUI(
    val article: Article,
    val sectionId: String,
    val previousId: String?,
    val nextId: String?,
    val displayNavigation: Boolean
)