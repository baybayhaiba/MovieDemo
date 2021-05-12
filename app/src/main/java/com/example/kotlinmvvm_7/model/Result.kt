package com.example.kotlinmvvm_7.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "moviesTable")
open class Result(
    @SerializedName("adult")
    @Expose
    @Ignore
    var adult: Boolean? = null,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,

    @SerializedName("genre_ids")
    @Expose
    @Ignore
    var genreIds: List<Int>? = null,

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("original_language")
    @Expose
    @Ignore
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null,

    @SerializedName("overview")
    @Expose
    var overview: String? = null,

    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null,

    @SerializedName("poster_path")
    @Expose
    @Ignore
    var posterPath: String? = null,

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("video")
    @Expose
    @Ignore
    var video: Boolean? = null,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null,

    var movieSaved: Long? = null
) : Parcelable