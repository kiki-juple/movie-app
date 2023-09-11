package com.kiki.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kiki.core.util.movieDetailTable

@Entity(tableName = movieDetailTable)
data class MovieDetailEntity(
    @PrimaryKey
    val id: Int? = null,
    val backdropUrl: String? = null,
    val status: String? = null,
    val releaseDate: String? = null
)
