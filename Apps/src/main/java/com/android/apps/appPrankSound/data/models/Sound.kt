package com.android.apps.appPrankSound.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Sounds")
data class Sound(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var uid: Int = 0,

    @SerializedName("name")
    @Expose
    @ColumnInfo("name")
    var name: String? = null,

    @SerializedName("category")
    @Expose
    @ColumnInfo("category")
    var category: Int? = null,

    @SerializedName("id")
    @Expose
    @ColumnInfo("id")
    var id: String? = null,

    @SerializedName("sound")
    @Expose
    @ColumnInfo("sound")
    var sound: String? = null,

    @SerializedName("icon")
    @Expose
    @ColumnInfo("icon")
    var icon: String? = null,

    @SerializedName("isFavorite")
    @Expose
    @ColumnInfo("isFavourite")
    var isFavorite: Boolean = false
) : Serializable