package com.example.roomexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey
    @ColumnInfo(name = "dateAdded")
    val dateAdded: Date,
    @ColumnInfo(name = "noteText")
    var noteText: String
)