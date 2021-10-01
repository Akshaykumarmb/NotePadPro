package com.example.notepadpro.Interfaces

import android.app.Application
import com.example.notepadpro.DIModule
import com.example.notepadpro.ViewFragment
import com.example.notepadpro.ViewModel.NotesViewModel
import dagger.Component

@Component(modules = [DIModule::class])
interface DIComponent {

     fun inject(viewfragment: ViewFragment)


}