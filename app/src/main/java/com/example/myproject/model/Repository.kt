package com.example.myproject.model

object Repository {
    private val notes: List<Note> = listOf(
            Note(
                    "Моя первая заметка",
                    "Котлин очень краткий, но при этом выразительный язык",
                    0xfff06292.toInt()
            ),
            Note(
                    "Моя вторая заметка",
                    "Котлин очень краткий, но при этом выразительный язык",
                    0xfff06292.toInt()
            ),
            Note(
                    "Моя третья заметка",
                    "Котлин очень краткий, но при этом выразительный язык",
                    0xfff06292.toInt()
            ),
            Note(
                    "Моя четвертая заметка",
                    "Котлин очень краткий, но при этом выразительный язык",
                    0xfff06292.toInt()
            ),
    )

    fun getNotes(): List<Note> = notes
}