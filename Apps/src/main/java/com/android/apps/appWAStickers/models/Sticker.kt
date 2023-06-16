package com.android.apps.appWAStickers.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sticker(
    @Expose
    @SerializedName("name")
    var name: String? = null,

    @Expose
    @SerializedName("type")
    var type: String? = null,

    @Expose
    @SerializedName("id")
    var id: String? = null,

    @Expose
    @SerializedName("identifier")
    var identifier: String? = null,

    @Expose
    @SerializedName("image_file")
    var imageFileName: String? = null,

    @Expose
    @SerializedName("isRewardAds")
    var isRewardAds: Boolean? = null,

    @Expose
    @SerializedName("emojis")
    var emojis: List<String>? = null,

    var isDownload: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        name = parcel.readString(),
        type = parcel.readString(),
        id = parcel.readString(),
        identifier = parcel.readString(),
        imageFileName = parcel.readString(),
        isRewardAds = parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        emojis = parcel.createStringArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(id)
        parcel.writeString(identifier)
        parcel.writeString(imageFileName)
        parcel.writeValue(isRewardAds)
        parcel.writeStringList(emojis)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sticker> {
        override fun createFromParcel(parcel: Parcel): Sticker {
            return Sticker(parcel)
        }

        override fun newArray(size: Int): Array<Sticker?> {
            return arrayOfNulls(size)
        }
    }

}