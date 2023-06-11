package com.android.apps.appPrankSound.data.dao

import androidx.room.*
import com.android.apps.appPrankSound.data.models.Sound

@Dao
interface SoundDAO {
    @Query("SELECT * FROM Sounds")
    suspend fun getFavouriteSounds(): MutableList<Sound>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavouriteSound(vararg sounds: Sound)

    @Delete
    suspend fun deleteFavouriteSound(vararg sounds: Sound)
}