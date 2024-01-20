package com.htrackk.myapplication.data.local

interface UserRepository {
    suspend fun getUserId(): Long?
    suspend fun insert(userEntity: UserEntity)
    suspend fun delete()
}

class OfflineUserRepository(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun delete() {
        return userDao.delete()
    }

    override suspend fun getUserId(): Long? {
        return userDao.getUserId()
    }

    override suspend fun insert(userEntity: UserEntity) {
        return userDao.insert(userEntity)
    }
}