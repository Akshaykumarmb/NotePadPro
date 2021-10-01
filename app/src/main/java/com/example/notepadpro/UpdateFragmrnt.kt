package com.example.notepadpro

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notepadpro.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.fragment_second.view.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */



class UpdateFragmrnt : Fragment() {

    private var nid: Int? = null
    private var note:String? = null

    val ID = "id"
    val NOTE = "note"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        nid = arguments?.getInt(ID)
        note = arguments?.getString(NOTE)
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        view.save.text = "Update"

        view.content.setText(note)
        viewModel.setNotesValue(note!!)
        Log.d(TAG, "onViewCreated: "+nid)

        view.save.setOnClickListener {
             Log.d("EditFragment", "onClick")
            viewModel.updateValue(nid!!)
        }
        view.content.addTextChangedListener {
            Log.d("EditFragment", "onViewCreated: ")
            viewModel.setNotesValue(it.toString())
        }

        viewModel.insertResult!!.observe(viewLifecycleOwner, {
            Log.d(TAG, "onViewCreated: Updated = $it")
            findNavController().navigate(R.id.back_to_view_event)
        })
        super.onViewCreated(view, savedInstanceState)
    }


}