package com.example.crudwithroom.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.crudwithroom.viewmodel.NoteViewModel

@Composable
fun NoteListScreen(viewModel: NoteViewModel) {
    val notes by viewModel.notes.collectAsState()
    var tittle by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = tittle,
            onValueChange = { tittle = it },
            label = { Text("Tittle") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (tittle.isNotBlank() && content.isNotBlank()) {
                    viewModel.addNote(tittle, content)
                    tittle = ""
                    content = ""
                }
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Note")
        }
        Spacer(modifier = Modifier.height(16.dp))
        notes.forEach { note ->
            NoteItem(
                note = note, onDelete = {
                    viewModel.deleteNote(note)
                },
                onUpdate = { updatedTittle, updatedContent ->
                    viewModel.updateNote(note.copy(tittle = updatedTittle, content = updatedContent))
                }
            )
        }
    }
}