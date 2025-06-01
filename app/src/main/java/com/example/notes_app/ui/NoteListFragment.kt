package com.example.notes_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes_app.databinding.FragmentNoteListBinding
import com.example.notes_app.viewmodel.NoteViewModel

class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding
    private val viewModel: NoteViewModel by viewModels()
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = NoteAdapter(
            onNoteClick = {  },
            onNoteDeleteClick = { note -> viewModel.delete(note) },
            onNoteEditClick = { note ->
                val action = NoteListFragmentDirections
                    .actionNoteListFragmentToAddEditNoteFragment(note.id, note.title, note.content)
                findNavController().navigate(action)
            }
        )



        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewNotes.adapter = adapter

        viewModel.allNotes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        // FAB click to add a new note
        binding.fabAddNote.setOnClickListener {
            val action = NoteListFragmentDirections
                .actionNoteListFragmentToAddEditNoteFragment(0, "", "")
            findNavController().navigate(action)
        }
    }
}
