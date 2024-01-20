package com.raul.myapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT id FROM user_info")
    suspend fun getUserId(): Long?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userEntity: UserEntity)

    @Query("DELETE FROM user_info")
    suspend fun delete()



}