package com.example.notepadpro.Dagger

import androidx.room.Room
import com.example.notepadpro.DataBase.NotesDB
import com.example.notepadpro.NotePadProApplication
import com.example.notepadpro.NotesAdapter
import dagger.Module
import dagger.Provides

@Module
class DIModule {

    @Provides
    fun getNoteAdapter() : NotesAdapter {
        return NotesAdapter()
    }

    @Provides
    fun getDb():NotesDB{
        return Room.databaseBuilder(NotePadProApplication.applicationContext(), NotesDB::class.java,"notes_table").build()
    }

}

