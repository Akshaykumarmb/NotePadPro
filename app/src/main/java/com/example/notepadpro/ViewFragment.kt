package com.example.notepadpro

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notepadpro.Dagger.DaggerDIComponent
import com.example.notepadpro.Entity.Notes
import com.example.notepadpro.FireBase.RemoteConfigUtils
import com.example.notepadpro.Interfaces.RecyclerViewClickListener
import com.example.notepadpro.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.android.synthetic.main.option_layout.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ViewFragment : Fragment(),RecyclerViewClickListener {

    var viewModelView:NotesViewModel?=null
    var showMaintainance:Boolean = false

    @Inject
    lateinit var notesAdapter:NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showMaintainance = RemoteConfigUtils.getMaintainanceCheck()

        Log.d("ViewFragment", "onCreate: boolean - "+showMaintainance)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(showMaintainance) {
            textview_first.text = "Site Under Maintainance"
            view.fab.visibility = View.GONE
        }else{
            DaggerDIComponent.create().inject(this)

            val viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
            viewModelView=viewModel
            recyclerview.layoutManager = LinearLayoutManager(activity)



            viewModel.displayValues()
            viewModel.resultList!!.observe(viewLifecycleOwner, {
                Log.d(ContentValues.TAG, "onViewCreated: inserted = $it")
                if (!it.isEmpty()) {
                    textview_first.visibility = View.GONE
                    notesAdapter.setDataToAdapter(it,this)
                    recyclerview.adapter = notesAdapter
                } else {
                    notesAdapter.setDataToAdapter(it,this)
                    recyclerview.adapter = notesAdapter
                    textview_first.visibility = View.VISIBLE
                    textview_first.text = "No Notes Are Present"
                }
            }
            )


            view.fab.setOnClickListener {
                findNavController().navigate(R.id.fab_event)
            }
        }



    }

    override fun recyclerViewListClicked( notes: Notes) {
        Log.d("TAG", "recyclerViewListClicked: $notes")
        val notesBundle:Bundle = bundleOf("id" to notes.Nid,"note" to notes.notes)
        this.findNavController().navigate(R.id.update_event,notesBundle)
    }

    override fun recyclerViewListLongClicked(notes: Notes) {
        Log.d("TAG", "recyclerViewListLongClicked: $notes")
        showDialog(notes)
    }

    private fun showDialog(notes: Notes) {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.option_layout)
        val editBtn = dialog.edit_notes
        val deleteBtn = dialog.delete_notes
        editBtn.setOnClickListener {
            val notesBundle:Bundle = bundleOf("id" to notes.Nid,"note" to notes.notes)
            this.findNavController().navigate(R.id.update_event,notesBundle)
            dialog.dismiss()
        }
        deleteBtn.setOnClickListener {
            viewModelView!!.deleteValue(notes)
            dialog.dismiss()
        }
        dialog.show()

    }

}