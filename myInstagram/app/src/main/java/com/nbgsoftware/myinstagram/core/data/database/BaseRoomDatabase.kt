package com.nbgsoftware.myinstagram.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nbgsoftware.myinstagram.core.data.database.dao.UserDAO
import com.nbgsoftware.myinstagram.core.data.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class BaseRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
}