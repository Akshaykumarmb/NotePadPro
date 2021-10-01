package com.example.notepadpro.Interfaces

import android.view.View
import com.example.notepadpro.Entity.Notes

interface RecyclerViewClickListener {

    fun recyclerViewListClicked(notes:Notes)

    fun recyclerViewListLongClicked(notes:Notes)

}