package com.ranzan.android_room_iassignment.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Table")
data class DatabaseEntity(
    @ColumnInfo(name = "Title") var title: String,
    @ColumnInfo(name = "Description") var desc: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Int = 0
}