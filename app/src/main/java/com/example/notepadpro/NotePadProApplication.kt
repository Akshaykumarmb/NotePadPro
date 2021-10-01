package com.example.notepadpro

import android.app.Application
import android.util.Log
import com.example.notepadpro.Dagger.DaggerDIComponent
import com.example.notepadpro.DataBase.NotesDB
import javax.inject.Inject

class NotePadProApplication : Application() {

    @Inject
    lateinit var notesdb:NotesDB

    init {
        instance = this
    }


    companion object {
        private var instance: NotePadProApplication? = null
        var TAG:String = "ShowOffApplication"

        fun applicationContext() : NotePadProApplication {
            Log.d(TAG, "applicationContext: ")
            return instance as NotePadProApplication
        }

    }

    fun InjectAllData()
    {
        var daggerNetworkComponent = DaggerDIComponent.create()
        daggerNetworkComponent.inject(this)
    }

}