package com.example.notepadpro.ViewModel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notepadpro.DataBase.NotesDB
import junit.framework.JUnit4TestAdapter
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotesViewModelTest : TestCase()
{
    lateinit var viewMode:NotesViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()

        viewMode = NotesViewModel(ApplicationProvider.getApplicationContext())
        viewMode.notesLiveData?.postValue("hellow")

    }

    @Test
    fun insertData() {

        val value = viewMode.notesLiveData?.getOrAwaitTheValue()

        Log.d("NotesViewModel", "insertData: "+value)
    }

}