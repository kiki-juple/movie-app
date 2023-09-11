package com.kiki.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kiki.core.util.movieTable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = movieTable)
data class MovieEntity(
    @PrimaryKey
    val id: Int? = null,
    val title: String? = null,
    val overView: String? = null,
    val posterUrl: String? = null,
    val backdropUrl: String? = null,
    val rating: Double? = null,
    val ratingCount: Int? = null,
    val adult: Boolean? = false,
    val releaseDate: String? = null,
    val isPopular: Boolean? = false,
    val isUpcoming: Boolean? = false,
    val isTopRated: Boolean? = false,
    val isNowPlaying: Boolean? = false,
    var isFavorite: Boolean? = false
) : Parcelable
