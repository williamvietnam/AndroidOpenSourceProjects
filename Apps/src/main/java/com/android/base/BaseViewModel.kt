package com.android.base

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.lifecycle.ViewModel
import java.io.IOException
import java.nio.charset.StandardCharsets

abstract class BaseViewModel : ViewModel() {
    fun getImageFromAsset(fileName: String, context: Context): Drawable? {
        var result: Drawable? = null
        try {
            val stream = context.assets.open(fileName)
            result = Drawable.createFromStream(stream, null)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return result
    }

    fun getJsonFromAssets(filename: String?, context: Context): String? {
        val jsonString: String
        try {
            val inputStream = context.assets.open(filename!!)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonString = String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getRawUriFile(filename: String, context: Context): Uri? {
        val rawId: Int =
            context.resources.getIdentifier(
                filename,
                "raw",
                context.packageName
            )

        return Uri.parse("android.resource://" + context.packageName + "/" + rawId)
    }
}