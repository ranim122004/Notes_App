package com.example.notes_app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_app.R
import com.example.notes_app.data.Note

class NoteAdapter(
    private val onNoteClick: (Note) -> Unit,
    private val onNoteDeleteClick: (Note) -> Unit,
    private val onNoteEditClick: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var notes = listOf<Note>()

    fun submitList(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            val titleText = itemView.findViewById<TextView>(R.id.textViewTitle)
            val contentText = itemView.findViewById<TextView>(R.id.textViewContent)
            val deleteButton = itemView.findViewById<ImageButton>(R.id.buttonDelete)
            val editButton = itemView.findViewById<ImageButton>(R.id.buttonEdit)

            titleText.text = note.title
            contentText.text = note.content

            itemView.setOnClickListener {
                onNoteClick(note)
            }

            deleteButton.setOnClickListener {
                onNoteDeleteClick(note)
            }

            editButton.setOnClickListener {
                onNoteEditClick(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size
}
