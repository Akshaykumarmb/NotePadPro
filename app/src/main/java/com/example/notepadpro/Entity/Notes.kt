package com.example.notepadpro.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject


@Entity(tableName = "notes_table")
data class Notes (
    @PrimaryKey(autoGenerate = true)
    val Nid: Int,
    @ColumnInfo(name = "Note") var notes: String?=null
)