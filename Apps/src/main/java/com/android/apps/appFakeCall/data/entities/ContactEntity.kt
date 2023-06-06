package com.android.apps.appFakeCall.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ContactsEntity")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var uid: Int = 0,

    @ColumnInfo("contact_name")
    @SerializedName("contactName")
    var contactName: String? = null,

    @ColumnInfo("contact_number")
    @SerializedName("contactNumber")
    var contactNumber: String? = null,

    @ColumnInfo("contact_icon")
    @SerializedName("contactIcon")
    var contactIcon: String? = null,

    @ColumnInfo("contact_video")
    @SerializedName("contactVideo")
    var contactVideo: String? = null,

    @ColumnInfo("isReward")
    @SerializedName("isReward")
    var isReward: Boolean = false,

    @Ignore
    @SerializedName("isPremium")
    var isPremium: Boolean = false,

    @ColumnInfo("is_database")
    var isDataBase: Boolean = false,
)