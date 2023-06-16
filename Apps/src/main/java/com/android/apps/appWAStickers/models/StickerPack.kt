package com.android.apps.appWAStickers.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StickerPack(
    @Expose
    @SerializedName("stickerPackName")
    var stickerPackName: String? = null,

    @Expose
    @SerializedName("name")
    var name: String? = null,

    @Expose
    @SerializedName("publisher")
    var publisher: String? = null,

    @Expose
    @SerializedName("id")
    var id: Int? = null,

    @Expose
    @SerializedName("identifier")
    var identifier: String? = null,

    @Expose
    @SerializedName("tray_image_file")
    var trayImageFile: String? = null,

    @Expose
    @SerializedName("isPremium")
    var isPremium: Boolean = false,

    @Expose
    @SerializedName("isRewardAds")
    var isRewardAds: Boolean = false,

    @Expose
    @SerializedName("publisher_email")
    val publisherEmail: String? = null,

    @Expose
    @SerializedName("publisher_website")
    val publisherWebsite: String? = null,

    @Expose
    @SerializedName("privacy_policy_website")
    var privacyPolicyWebsite: String? = null,

    @Expose
    @SerializedName("license_agreement_website")
    var licenseAgreementWebsite: String? = null,

    @Expose
    @SerializedName("image_data_version")
    var imageDataVersion: String? = null,

    @Expose
    @SerializedName("avoid_cache")
    val avoidCache: Boolean = false,

    @Expose
    @SerializedName("animated_sticker_pack")
    var animatedStickerPack: Boolean = false,

    var isWhitelisted: Boolean = false,

    var iosAppStoreLink: String? = null,

    var androidPlayStoreLink: String? = null,

    @Expose
    @SerializedName("stickers")
    var stickers: List<Sticker>? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        stickerPackName = parcel.readString(),
        name = parcel.readString(),
        publisher = parcel.readString(),
        id = parcel.readValue(Int::class.java.classLoader) as? Int,
        identifier = parcel.readString(),
        trayImageFile = parcel.readString(),
        isPremium = parcel.readByte() != 0.toByte(),
        isRewardAds = parcel.readByte() != 0.toByte(),
        publisherEmail = parcel.readString(),
        publisherWebsite = parcel.readString(),
        privacyPolicyWebsite = parcel.readString(),
        licenseAgreementWebsite = parcel.readString(),
        imageDataVersion = parcel.readString(),
        avoidCache = parcel.readByte().toInt() != 0,
        animatedStickerPack = parcel.readByte() != 0.toByte(),
        isWhitelisted = parcel.readByte() != 0.toByte(),
        iosAppStoreLink = parcel.readString(),
        androidPlayStoreLink = parcel.readString(),
        stickers = parcel.createTypedArrayList(Sticker)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(stickerPackName)
        parcel.writeString(name)
        parcel.writeString(publisher)
        parcel.writeValue(id)
        parcel.writeString(identifier)
        parcel.writeString(trayImageFile)
        parcel.writeByte(if (isPremium) 1 else 0)
        parcel.writeByte(if (isRewardAds) 1 else 0)
        parcel.writeString(publisherEmail)
        parcel.writeString(publisherWebsite)
        parcel.writeString(privacyPolicyWebsite)
        parcel.writeString(licenseAgreementWebsite)
        parcel.writeString(imageDataVersion)
        parcel.writeByte((if (avoidCache) 1 else 0).toByte())
        parcel.writeByte(if (animatedStickerPack) 1 else 0)
        parcel.writeByte(if (isWhitelisted) 1 else 0)
        parcel.writeString(iosAppStoreLink)
        parcel.writeString(androidPlayStoreLink)
        parcel.writeTypedList(stickers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StickerPack> {
        override fun createFromParcel(parcel: Parcel): StickerPack {
            return StickerPack(parcel)
        }

        override fun newArray(size: Int): Array<StickerPack?> {
            return arrayOfNulls(size)
        }
    }

}