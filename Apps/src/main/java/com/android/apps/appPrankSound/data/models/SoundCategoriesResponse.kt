package com.android.apps.appPrankSound.data.models

import com.google.gson.annotations.SerializedName

data class SoundCategoriesResponse(
    @SerializedName("soundCategories")
    var soundCategories: ArrayList<SoundCategory>? = null
)