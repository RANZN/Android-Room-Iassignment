package com.ranzan.android_room_iassignment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getDataBase(): DatabaseDAO

    companion object {
        private var instance: LocalDatabase? = null

        fun getDataBase(context: Context): LocalDatabase {
            if (instance != null) {
                return instance!!
            } else {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "table"
                )
                builder.fallbackToDestructiveMigration()
                instance = builder.build()
            }
            return instance!!
        }
    }
}