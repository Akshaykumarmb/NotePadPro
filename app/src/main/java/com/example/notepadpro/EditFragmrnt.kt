package com.example.notepadpro

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notepadpro.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.fragment_second.view.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class EditFragmrnt : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
//        viewModel.createDB()
        view.save.setOnClickListener {
             Log.d("EditFragment", "onClick")
            viewModel.insertValue()
        }
        view.content.addTextChangedListener {
            Log.d("EditFragment", "onViewCreated: ")
            viewModel.setNotesValue(it.toString())
        }

        viewModel.insertResult!!.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onViewCreated: inserted = $it")
            findNavController().navigate(R.id.back_to_view_event)
        })
        super.onViewCreated(view, savedInstanceState)
    }
}