package com.example.myproject.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.myproject.R
import com.example.myproject.model.Color
import com.example.myproject.model.Note
import com.example.myproject.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_note.*
import java.util.*

private const val SAVE_DELAY = 2000L

class NoteActivity : BaseActivity<Note?, NoteViewState>() {

    companion object {
        const val EXTRA_NOTE = "NoteActivity.extra.NOTE"

        fun getStartIntent(context: Context, noteId: String?): Intent {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, noteId)
            return intent
        }
    }

    private var note: Note? = null

    override val viewModel: NoteViewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    override val layoutRes: Int = R.layout.activity_note
    private val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            triggerSaveNote()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // do nothing
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // do nothing
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteId = intent.getStringExtra(EXTRA_NOTE)
        noteId?.let {
            viewModel.loadNote(it)
        }
        if (noteId != null) supportActionBar?.title = getString(R.string.new_note_title)

        initView()
    }

    private fun initView() {
        titleEt.setText(note?.title ?: "")
        bodyEt.setText(note?.note ?: "")

        val color = when (note?.color) {
            Color.WHITE -> R.color.color_white
            Color.VIOLET -> R.color.color_violet
            Color.YELLOW -> R.color.color_yello
            Color.RED -> R.color.color_red
            Color.PINK -> R.color.color_pink
            Color.GREEN -> R.color.color_green
            Color.BLUE -> R.color.color_blue
            else -> R.color.white
        }

        toolbar.setBackgroundColor(resources.getColor(color))
        titleEt.addTextChangedListener(textChangeListener)
        bodyEt.addTextChangedListener(textChangeListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun createNewNote(): Note = Note(
            UUID.randomUUID().toString(),
            titleEt.text.toString(),
            bodyEt.text.toString())

    private fun triggerSaveNote() {
        if (titleEt.text == null || titleEt.text!!.length < 3) return

        Handler(Looper.getMainLooper()).postDelayed({
            note = note?.copy(
                    title = titleEt.text.toString(),
                    note = bodyEt.text.toString(),
                    lastChanged = Date()
            ) ?: createNewNote()

            if (note != null) viewModel.saveChanges(note!!)
        }, SAVE_DELAY)
    }

    override fun renderData(data: Note?) {
        this.note = data
        initView()
    }
}

