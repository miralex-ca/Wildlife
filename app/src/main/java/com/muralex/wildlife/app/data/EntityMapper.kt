package com.muralex.wildlife.app.data

interface EntityMapper<SRC, DST> {
    fun mapFromEntity(data: SRC): DST
}