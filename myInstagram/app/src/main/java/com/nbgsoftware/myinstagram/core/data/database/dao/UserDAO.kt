package com.nbgsoftware.myinstagram.core.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.base.mvvm.core.data.database.dao.BaseDAO
import com.nbgsoftware.myinstagram.core.data.database.entities.UserEntity

/**
 * Author: William Giang Nguyen | 14/8/2022
 * */
@Dao
interface UserDAO: BaseDAO<UserEntity> {

    @Query("SELECT * FROM userEntity")
    fun getAll(): List<UserEntity>

    @Insert
    fun insertAll(vararg userEntities: UserEntity)

    @Delete
    override fun delete(obj: UserEntity)

}