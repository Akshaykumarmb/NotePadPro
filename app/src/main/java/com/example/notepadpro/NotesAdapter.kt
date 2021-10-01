package com.example.notepadpro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notepadpro.Entity.Notes
import com.example.notepadpro.Interfaces.RecyclerViewClickListener
import kotlinx.android.synthetic.main.notes_list_row.view.*


class NotesAdapter() : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var notesList: List<Notes>? = null
    var itemListener: RecyclerViewClickListener? = null
    fun setDataToAdapter(notesList: List<Notes>, itemListener:RecyclerViewClickListener)
    {
        this.notesList = notesList
        this.itemListener = itemListener
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notes_list_row, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = notesList?.get(position)
        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel?.notes
        holder.itemView.setOnClickListener {
            itemListener?.recyclerViewListClicked(notesList!!.get(position))
        }
        holder.itemView.setOnLongClickListener {
            itemListener?.recyclerViewListLongClicked(notesList!!.get(position))
            true
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return notesList!!.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.title
    }


}