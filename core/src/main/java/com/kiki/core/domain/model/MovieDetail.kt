package com.kiki.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetail(
    val id: Int? = null,
    val backdropUrl: String? = null,
    val status: String? = null,
    val releaseDate: String? = null
) : Parcelable
