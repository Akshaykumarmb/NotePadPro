package com.example.notepadpro.Dao

import androidx.room.*
import com.example.notepadpro.Entity.Notes

@Dao
interface NotesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes):Long

    @Query("SELECT * FROM notes_table")
    suspend fun getAll(): List<Notes>

    @Query("UPDATE notes_table SET Note=:note WHERE Nid = :id")
    fun update(note: String?, id: Int):Int

    @Delete
    fun deleteNotes(notes:Notes)
}