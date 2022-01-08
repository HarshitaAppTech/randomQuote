package com.harshitaapptech.randomquote.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class QuotesModel(
    @SerializedName("author")
    val author: String? = null, // Ella Fitzgerald
    @SerializedName("authorSlug")
    val authorSlug: String? = null, // ella-fitzgerald
    @SerializedName("content")
    val content: String? = null, // It isn't where you come from, it's where you're going that counts.
    @SerializedName("dateAdded")
    val dateAdded: String? = null, // 2020-09-05
    @SerializedName("dateModified")
    val dateModified: String? = null, // 2020-09-05
    @SerializedName("_id")
    val id: String? = null, // WzhPe07XA6rt
    @SerializedName("length")
    val length: Int? = null, // 66
    @SerializedName("tags")
    val tags: List<String>? = null
)