package com.android.apps.appWAStickers.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StickersResponse(
    @Expose
    @SerializedName("sticker_packs")
    var stickerCategories: ArrayList<StickerPack>? = null
)