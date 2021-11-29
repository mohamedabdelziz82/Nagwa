package com.mohamedabdelaziz.nagwatask.domain.entities

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("type")
    val type: ContentType?,
    @SerializedName("url")
    var url: String
)