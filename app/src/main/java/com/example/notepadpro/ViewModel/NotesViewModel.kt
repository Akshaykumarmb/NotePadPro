package com.example.notepadpro.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import androidx.room.Room
import com.example.notepadpro.Dao.NotesDAO
import com.example.notepadpro.DataBase.NotesDB
import com.example.notepadpro.Entity.Notes
import com.example.notepadpro.Interfaces.DaggerDIComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    var notesLiveData: MutableLiveData<String>? = MutableLiveData<String>()
    var insertResult: MutableLiveData<Long>? = MutableLiveData()
    var db:NotesDB
    var notesDAO:NotesDAO?=null
    var resultList:MutableLiveData<ArrayList<Notes>>?=MutableLiveData<ArrayList<Notes>>()

    init {
        db = Room.databaseBuilder(application.applicationContext, NotesDB::class.java,"notes_table").build()
        notesDAO = db!!.notesDao()
    }
    
    fun setNotesValue(data:String){
        notesLiveData!!.postValue(data)
    }

    fun insertValue(){
        val note= Notes(0,notesLiveData!!.value)
        viewModelScope.launch(Dispatchers.IO){
            val res = notesDAO!!.insertNotes(note)
            withContext(Dispatchers.Main)
            {
                insertResult!!.postValue(res)
            }
        }
    }

    fun updateValue(id:Int){
        viewModelScope.launch(Dispatchers.IO){
            val res = notesDAO!!.update(notesLiveData!!.value,id)
            withContext(Dispatchers.Main)
            {
                insertResult!!.postValue(res.toLong())
            }
        }
    }

    fun displayValues(){
        viewModelScope.launch(Dispatchers.IO) {
           val resultlist:ArrayList<Notes> = notesDAO!!.getAll() as ArrayList<Notes>
            withContext(Dispatchers.Main) {
                resultList!!.postValue(resultlist)
            }
        }
    }

    fun deleteValue(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesDAO!!.deleteNotes(notes)
            val resultlist:ArrayList<Notes> = notesDAO!!.getAll() as ArrayList<Notes>
            withContext(Dispatchers.Main) {
                resultList!!.postValue(resultlist)
            }
        }
    }

}