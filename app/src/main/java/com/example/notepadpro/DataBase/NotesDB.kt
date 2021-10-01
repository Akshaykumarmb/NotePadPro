package com.example.notepadpro.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notepadpro.Dao.NotesDAO
import com.example.notepadpro.Entity.Notes

@Database(entities = arrayOf(Notes::class), version = 3)
abstract class NotesDB : RoomDatabase() {

    abstract fun notesDao(): NotesDAO

}