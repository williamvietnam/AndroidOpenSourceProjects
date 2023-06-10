package com.android.apps.appPrankSound.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Sound(
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("category")
    @Expose
    var category: Int? = null,

    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("sound")
    @Expose
    var sound: String? = null,

    @SerializedName("icon")
    @Expose
    var icon: String? = null,

    @SerializedName("isReward")
    @Expose
    var isReward: Boolean? = null,

    @SerializedName("isFavorite")
    @Expose
    var isFavorite: Boolean = false
) : Serializable