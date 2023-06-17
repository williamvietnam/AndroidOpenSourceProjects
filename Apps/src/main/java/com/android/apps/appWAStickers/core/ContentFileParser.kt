package com.android.apps.appWAStickers.core

import android.text.TextUtils
import android.util.JsonReader
import com.android.apps.appWAStickers.models.Sticker
import com.android.apps.appWAStickers.models.StickerPack
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class ContentFileParser {
    companion object {
        @Throws(IOException::class, IllegalStateException::class)
        fun parseStickerPacks(contentsInputStream: InputStream): List<StickerPack> {
            JsonReader(InputStreamReader(contentsInputStream)).use { reader ->
                return readStickerPacks(reader)
            }
        }

        @Throws(IOException::class, IllegalStateException::class)
        private fun readStickerPacks(reader: JsonReader): List<StickerPack> {
            val stickerPackList: MutableList<StickerPack> = ArrayList()
            var androidPlayStoreLink: String? = null
            var iosAppStoreLink: String? = null
            reader.beginObject()
            while (reader.hasNext()) {
                when (val key = reader.nextName()) {
                    "android_play_store_link" -> {
                        androidPlayStoreLink = reader.nextString()
                    }

                    "ios_app_store_link" -> {
                        iosAppStoreLink = reader.nextString()
                    }

                    "sticker_packs" -> {
                        reader.beginArray()
                        while (reader.hasNext()) {
                            val stickerPack = readStickerPack(reader)
                            stickerPackList.add(stickerPack)
                        }
                        reader.endArray()
                    }

                    else -> {
                        throw IllegalStateException("unknown field in json: $key")
                    }
                }
            }
            reader.endObject()
            check(stickerPackList.size != 0) { "sticker pack list cannot be empty" }
            for (stickerPack in stickerPackList) {
                stickerPack.androidPlayStoreLink = (androidPlayStoreLink)
                stickerPack.iosAppStoreLink = (iosAppStoreLink)
            }
            return stickerPackList
        }

        @Throws(IOException::class, IllegalStateException::class)
        private fun readStickerPack(reader: JsonReader): StickerPack {
            reader.beginObject()
            var identifier: String? = null
            var name: String? = null
            var publisher: String? = null
            var trayImageFile: String? = null
            var publisherEmail: String? = null
            var publisherWebsite: String? = null
            var privacyPolicyWebsite: String? = null
            var licenseAgreementWebsite: String? = null
            var imageDataVersion: String? = ""
            var avoidCache = false
            var animatedStickerPack = false
            var stickerList: List<Sticker?>? = null
            while (reader.hasNext()) {
                when (reader.nextName()) {
                    "identifier" -> identifier = reader.nextString()
                    "name" -> name = reader.nextString()
                    "publisher" -> publisher = reader.nextString()
                    "tray_image_file" -> trayImageFile = reader.nextString()
                    "publisher_email" -> publisherEmail = reader.nextString()
                    "publisher_website" -> publisherWebsite = reader.nextString()
                    "privacy_policy_website" -> privacyPolicyWebsite = reader.nextString()
                    "license_agreement_website" -> licenseAgreementWebsite = reader.nextString()
                    "stickers" -> stickerList = readStickers(reader)
                    "image_data_version" -> imageDataVersion = reader.nextString()
                    "avoid_cache" -> avoidCache = reader.nextBoolean()
                    "animated_sticker_pack" -> animatedStickerPack = reader.nextBoolean()
                    else -> reader.skipValue()
                }
            }
            check(!TextUtils.isEmpty(identifier)) { "identifier cannot be empty" }
            check(!TextUtils.isEmpty(name)) { "name cannot be empty" }
            check(!TextUtils.isEmpty(publisher)) { "publisher cannot be empty" }
            check(!TextUtils.isEmpty(trayImageFile)) { "tray_image_file cannot be empty" }
            check(!stickerList.isNullOrEmpty()) { "sticker list is empty" }
            check(!(identifier!!.contains("..") || identifier.contains("/"))) { "identifier should not contain .. or / to prevent directory traversal" }
            check(!TextUtils.isEmpty(imageDataVersion)) { "image_data_version should not be empty" }
            reader.endObject()
            val stickerPack = StickerPack(
                identifier = identifier,
                name = name,
                publisher = publisher,
                trayImageFile = trayImageFile,
                publisherEmail = publisherEmail,
                publisherWebsite = publisherWebsite,
                privacyPolicyWebsite = privacyPolicyWebsite,
                licenseAgreementWebsite = licenseAgreementWebsite,
                imageDataVersion = imageDataVersion,
                avoidCache = avoidCache,
                animatedStickerPack = animatedStickerPack
            )
            stickerPack.stickers = stickerList as List<Sticker>?
            return stickerPack
        }

        @Throws(IOException::class, IllegalStateException::class)
        private fun readStickers(reader: JsonReader): List<Sticker?> {
            reader.beginArray()
            val stickerList: MutableList<Sticker?> = ArrayList()
            while (reader.hasNext()) {
                reader.beginObject()
                var name: String? = null
                var type: String? = null
                var id: String? = null
                var identifier: String? = null
                var imageFile: String? = null
                var isRewardAds = false
                val emojis: MutableList<String> = ArrayList(StickerPackValidator.EMOJI_MAX_LIMIT)
                while (reader.hasNext()) {
                    val key = reader.nextName()
                    if ("name" == key) {
                        name = reader.nextString()
                    } else if ("type" == key) {
                        type = reader.nextString()
                    } else if ("id" == key) {
                        id = reader.nextString()
                    } else if ("identifier" == key) {
                        identifier = reader.nextString()
                    } else if ("image_file" == key) {
                        imageFile = reader.nextString()
                    } else if ("isRewardAds" == key) {
                        isRewardAds = reader.nextBoolean()
                    } else if ("emojis" == key) {
                        reader.beginArray()
                        while (reader.hasNext()) {
                            val emoji = reader.nextString()
                            if (!TextUtils.isEmpty(emoji)) {
                                emojis.add(emoji)
                            }
                        }
                        reader.endArray()
                    } else {
                        throw IllegalStateException("unknown field in json: $key")
                    }
                }
                reader.endObject()
                check(!TextUtils.isEmpty(imageFile)) { "sticker image_file cannot be empty" }
                check(imageFile!!.endsWith(".webp")) { "image file for stickers should be webp files, image file is: $imageFile" }
                check(!(imageFile.contains("..") || imageFile.contains("/"))) { "the file name should not contain .. or / to prevent directory traversal, image file is:$imageFile" }
                stickerList.add(
                    Sticker(
                        name = name,
                        type = type,
                        id = id,
                        identifier = identifier,
                        imageFileName = imageFile,
                        isRewardAds = isRewardAds,
                        emojis = emojis
                    )
                )
            }
            reader.endArray()
            return stickerList
        }
    }
}