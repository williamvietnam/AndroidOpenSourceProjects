package com.android.apps.appPrankSound.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SoundCategory(
    @SerializedName("name_category")
    @Expose
    var nameCategory: String? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("icon_category")
    @Expose
    var iconCategory: String? = null,

    @SerializedName("isReward")
    @Expose
    var isReward: Boolean? = null,

    @SerializedName("isPremium")
    @Expose
    var isPremium: Boolean? = null,

    @SerializedName("list_sound")
    @Expose
    var listSound: MutableList<Sound> = ArrayList()

) : Serializable