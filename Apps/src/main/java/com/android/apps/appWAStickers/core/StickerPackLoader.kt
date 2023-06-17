package com.android.apps.appWAStickers.core

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.text.TextUtils
import com.android.BuildConfig
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.ANDROID_APP_DOWNLOAD_LINK_IN_QUERY
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.ANIMATED_STICKER_PACK
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.AVOID_CACHE
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.IMAGE_DATA_VERSION
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.IOS_APP_DOWNLOAD_LINK_IN_QUERY
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.LICENSE_AGREENMENT_WEBSITE
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.PRIVACY_POLICY_WEBSITE
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.PUBLISHER_EMAIL
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.PUBLISHER_WEBSITE
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.STICKER_FILE_EMOJI_IN_QUERY
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.STICKER_FILE_NAME_IN_QUERY
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.STICKER_PACK_ICON_IN_QUERY
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.STICKER_PACK_IDENTIFIER_IN_QUERY
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.STICKER_PACK_NAME_IN_QUERY
import com.android.apps.appWAStickers.core.StickerContentProvider.Companion.STICKER_PACK_PUBLISHER_IN_QUERY
import com.android.apps.appWAStickers.models.Sticker
import com.android.apps.appWAStickers.models.StickerPack
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.Arrays

class StickerPackLoader {
    companion object {
        /**
         * Get the list of sticker packs for the sticker content provider
         */
        @Throws(IllegalStateException::class)
        fun fetchStickerPacks(context: Context): ArrayList<StickerPack> {
            val cursor =
                context.contentResolver.query(
                    StickerContentProvider.AUTHORITY_URI,
                    null,
                    null,
                    null,
                    null
                )
                    ?: throw IllegalStateException("could not fetch from content provider, " + BuildConfig.CONTENT_PROVIDER_AUTHORITY)
            val identifierSet = HashSet<String>()
            val stickerPackList: ArrayList<StickerPack> = fetchFromContentProvider(cursor)
            for (stickerPack in stickerPackList) {
                check(!identifierSet.contains(stickerPack.identifier)) { "sticker pack identifiers should be unique, there are more than one pack with identifier:" + stickerPack.identifier }
                stickerPack.identifier?.let { identifierSet.add(it) }
            }
            check(stickerPackList.isNotEmpty()) { "There should be at least one sticker pack in the app" }
            for (stickerPack in stickerPackList) {
                val stickers: List<Sticker> = getStickersForPack(context, stickerPack)
                stickerPack.stickers = stickers
                StickerPackValidator.verifyStickerPackValidity(context, stickerPack)
            }
            return stickerPackList
        }

        private fun getStickersForPack(context: Context, stickerPack: StickerPack): List<Sticker> {
            val stickers: List<Sticker> = fetchFromContentProviderForStickers(
                stickerPack.identifier!!,
                context.contentResolver
            )
            for (sticker in stickers) {
                val bytes: ByteArray
                try {
                    bytes = fetchStickerAsset(
                        stickerPack.identifier!!,
                        sticker.imageFileName!!,
                        context.contentResolver
                    )
                    check(bytes.isNotEmpty()) { "Asset file is empty, pack: " + stickerPack.name + ", sticker: " + sticker.imageFileName }
                } catch (e: IOException) {
                    throw IllegalStateException(
                        "Asset file doesn't exist. pack: " + stickerPack.name + ", sticker: " + sticker.imageFileName,
                        e
                    )
                } catch (e: IllegalArgumentException) {
                    throw IllegalStateException(
                        "Asset file doesn't exist. pack: " + stickerPack.name + ", sticker: " + sticker.imageFileName,
                        e
                    )
                }
            }
            return stickers
        }


        private fun fetchFromContentProvider(cursor: Cursor): ArrayList<StickerPack> {
            val stickerPackList: ArrayList<StickerPack> = ArrayList()
            cursor.moveToFirst()
            do {
                val identifier =
                    cursor.getString(cursor.getColumnIndexOrThrow(STICKER_PACK_IDENTIFIER_IN_QUERY))
                val name =
                    cursor.getString(cursor.getColumnIndexOrThrow(STICKER_PACK_NAME_IN_QUERY))
                val publisher =
                    cursor.getString(cursor.getColumnIndexOrThrow(STICKER_PACK_PUBLISHER_IN_QUERY))
                val trayImage =
                    cursor.getString(cursor.getColumnIndexOrThrow(STICKER_PACK_ICON_IN_QUERY))
                val androidPlayStoreLink =
                    cursor.getString(cursor.getColumnIndexOrThrow(ANDROID_APP_DOWNLOAD_LINK_IN_QUERY))
                val iosAppLink =
                    cursor.getString(cursor.getColumnIndexOrThrow(IOS_APP_DOWNLOAD_LINK_IN_QUERY))
                val publisherEmail = cursor.getString(cursor.getColumnIndexOrThrow(PUBLISHER_EMAIL))
                val publisherWebsite =
                    cursor.getString(cursor.getColumnIndexOrThrow(PUBLISHER_WEBSITE))
                val privacyPolicyWebsite =
                    cursor.getString(cursor.getColumnIndexOrThrow(PRIVACY_POLICY_WEBSITE))
                val licenseAgreementWebsite =
                    cursor.getString(cursor.getColumnIndexOrThrow(LICENSE_AGREENMENT_WEBSITE))
                val imageDataVersion =
                    cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_DATA_VERSION))
                val avoidCache = cursor.getShort(cursor.getColumnIndexOrThrow(AVOID_CACHE)) > 0
                val animatedStickerPack =
                    cursor.getShort(cursor.getColumnIndexOrThrow(ANIMATED_STICKER_PACK)) > 0
                val stickerPack = StickerPack(
                    identifier = identifier,
                    name = name,
                    publisher = publisher,
                    trayImageFile = trayImage,
                    publisherEmail = publisherEmail,
                    publisherWebsite = publisherWebsite,
                    privacyPolicyWebsite = privacyPolicyWebsite,
                    licenseAgreementWebsite = licenseAgreementWebsite,
                    imageDataVersion = imageDataVersion,
                    avoidCache = avoidCache,
                    animatedStickerPack = animatedStickerPack
                )
                stickerPack.androidPlayStoreLink = (androidPlayStoreLink)
                stickerPack.iosAppStoreLink = (iosAppLink)
                stickerPackList.add(stickerPack)
            } while (cursor.moveToNext())
            return stickerPackList
        }

        private fun fetchFromContentProviderForStickers(
            identifier: String,
            contentResolver: ContentResolver
        ): List<Sticker> {
            val uri = getStickerListUri(identifier)
            val projection = arrayOf(STICKER_FILE_NAME_IN_QUERY, STICKER_FILE_EMOJI_IN_QUERY)
            val cursor = contentResolver.query(uri, projection, null, null, null)
            val stickers: MutableList<Sticker> = ArrayList<Sticker>()
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val name =
                        cursor.getString(cursor.getColumnIndexOrThrow(STICKER_FILE_NAME_IN_QUERY))
                    val emojisConcatenated =
                        cursor.getString(cursor.getColumnIndexOrThrow(STICKER_FILE_EMOJI_IN_QUERY))
                    var emojis: List<String> = ArrayList(StickerPackValidator.EMOJI_MAX_LIMIT)
                    if (!TextUtils.isEmpty(emojisConcatenated)) {
                        emojis = Arrays.asList(
                            *emojisConcatenated.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                                .toTypedArray())
                    }
                    stickers.add(Sticker(name = name, emojis = emojis))
                } while (cursor.moveToNext())
            }
            cursor?.close()
            return stickers
        }

        @Throws(IOException::class)
        fun fetchStickerAsset(
            identifier: String,
            name: String,
            contentResolver: ContentResolver
        ): ByteArray {
            contentResolver.openInputStream(getStickerAssetUri(identifier, name)!!)
                .use { inputStream ->
                    ByteArrayOutputStream().use { buffer ->
                        if (inputStream == null) {
                            throw IOException("cannot read sticker asset:$identifier/$name")
                        }
                        var read: Int
                        val data = ByteArray(16384)
                        while (inputStream.read(data, 0, data.size).also { read = it } != -1) {
                            buffer.write(data, 0, read)
                        }
                        return buffer.toByteArray()
                    }
                }
        }

        private fun getStickerListUri(identifier: String): Uri {
            return Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
                .authority(BuildConfig.CONTENT_PROVIDER_AUTHORITY)
                .appendPath(StickerContentProvider.STICKERS)
                .appendPath(identifier)
                .build()
        }

         fun getStickerCategoryAssetUri(identifier: String?, stickerName: String?): Uri {
            return Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
                .authority(BuildConfig.CONTENT_PROVIDER_AUTHORITY)
                .appendPath(StickerContentProvider.STICKERS)
                .appendPath(identifier)
                .appendPath(stickerName)
                .build()
        }

        fun getStickerAssetUri(identifier: String?, stickerName: String?): Uri? {
            return Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
                .authority(BuildConfig.CONTENT_PROVIDER_AUTHORITY)
                .appendPath(StickerContentProvider.STICKERS_ASSET)
                .appendPath(identifier)
                .appendPath(stickerName)
                .build()
        }
    }
}