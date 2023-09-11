package com.kiki.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
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
