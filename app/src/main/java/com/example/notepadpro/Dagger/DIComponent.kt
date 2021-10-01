package com.example.notepadpro.Dagger

import com.example.notepadpro.Dagger.DIModule
import com.example.notepadpro.NotePadProApplication
import com.example.notepadpro.ViewFragment
import dagger.Component

@Component(modules = [DIModule::class])
interface DIComponent {

     fun inject(viewfragment: ViewFragment)

     fun inject(notePadProApplication: NotePadProApplication)

}