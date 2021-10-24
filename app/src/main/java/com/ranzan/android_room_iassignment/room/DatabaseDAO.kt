package com.ranzan.android_room_iassignment.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DatabaseDAO {
    @Insert
    fun insert(databaseEntity: DatabaseEntity)

    @Update
    fun update(databaseEntity: DatabaseEntity)

    @Delete
    fun delete(databaseEntity: DatabaseEntity)

    @Query("select * from `table`")
    fun getList(): LiveData<MutableList<DatabaseEntity>>

    @Query("select * from `table` where id=:id")
    fun getData(id: Int): DatabaseEntity
}