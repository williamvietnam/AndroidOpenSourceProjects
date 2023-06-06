package com.android.apps.appFakeCall.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.apps.appFakeCall.data.entities.ContactEntity

@Dao
interface ContactDAO {
    @Query("SELECT * FROM ContactsEntity")
    fun getAllContacts(): MutableList<ContactEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createContactsListToDatabase(contacts: MutableList<ContactEntity>)
}