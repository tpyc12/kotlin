package com.example.myproject.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myproject.R
import com.example.myproject.model.Note
import com.example.myproject.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override val layoutRes: Int = R.layout.activity_main
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        adapter = MainAdapter(object: OnItemClickListener {
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }
        })
        mainRecycler.adapter = adapter

        fab.setOnClickListener { openNoteScreen() }
    }

    override fun renderData(data: List<Note>?) {
        if (data == null) return
        adapter.notes = data
    }

    private fun openNoteScreen(note: Note? = null){
        startActivity(NoteActivity.getStartIntent(this, note?.id))
    }
}