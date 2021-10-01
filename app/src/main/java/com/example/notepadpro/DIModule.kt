package com.example.notepadpro

import dagger.Module
import dagger.Provides

@Module
class DIModule {

    @Provides
    fun getNoteAdapter() : NotesAdapter{
        return NotesAdapter()
    }

}

