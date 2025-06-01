package com.example.notes_app.ui

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes_app.ui.AddEditNoteFragmentArgs
import com.example.notes_app.R
import com.example.notes_app.data.Note
import com.example.notes_app.viewmodel.NoteViewModel

class AddEditNoteFragment : Fragment() {

    private val args by navArgs<AddEditNoteFragmentArgs>()
    private val viewModel: NoteViewModel by viewModels()

    private lateinit var editTitle: EditText
    private lateinit var editContent: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_add_edit_note, container, false)
        editTitle = view.findViewById(R.id.editTextTitle)
        editContent = view.findViewById(R.id.editTextContent)
        view.findViewById<View>(R.id.buttonSave).setOnClickListener {
            saveNote()
        }

        if (args.noteId != 0) {
            editTitle.setText(args.noteTitle)
            editContent.setText(args.noteContent)
        }

        return view
    }

    private fun saveNote() {
        val title = editTitle.text.toString().trim()
        val content = editContent.text.toString().trim()

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(requireContext(), "Title and content required", Toast.LENGTH_SHORT).show()
            return
        }

        val note = Note(id = args.noteId, title = title, content = content)
        if (args.noteId == 0) viewModel.insert(note) else viewModel.update(note)

        findNavController().navigateUp()
    }
}
