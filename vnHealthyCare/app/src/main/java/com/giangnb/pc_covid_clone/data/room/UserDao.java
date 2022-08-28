package com.giangnb.pc_covid_clone.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * FROM users where userId=(:userId) and password=(:password)")
    UserEntity login(String userId, String password);
}
