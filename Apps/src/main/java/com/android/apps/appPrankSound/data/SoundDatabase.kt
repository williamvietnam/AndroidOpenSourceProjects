package com.android.apps.appPrankSound.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.apps.appPrankSound.data.dao.SoundDAO
import com.android.apps.appPrankSound.data.models.Sound

@Database(entities = [Sound::class], version = 1, exportSchema = false)
abstract class SoundDatabase : RoomDatabase() {
    abstract fun soundDAO(): SoundDAO

    companion object {
        @Volatile
        private var INSTANCE: SoundDatabase? = null

        fun getDatabase(context: Context): SoundDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SoundDatabase::class.java,
                    "sound_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}