package com.example.crudwithroom.repository

import com.example.crudwithroom.data.Note
import com.example.crudwithroom.data.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val dao: NoteDao) {
    val allNotes: Flow<List<Note>> = dao.getAllNotes()
    suspend fun insert(note: Note) =
        dao.insert(note)

    suspend fun update(note: Note) = dao.update(note)
    suspend fun delete(note: Note) = dao.delete(note)
}