package com.example.myproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.model.Note

class MainAdapter : RecyclerView.Adapter<MainAdapter.NoteViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return MainAdapter.NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    class NoteViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val body = itemView.findViewById<TextView>(R.id.body)

        fun bind(note: Note) {
            title.text = note.title
            body.text = note.note
            itemView.setBackgroundColor(note.color)
        }
    }
}