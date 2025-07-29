package com.example.crudwithroom.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.crudwithroom.data.Note

@Composable
fun NoteItem(
    note: Note, onDelete: () -> Unit,
    onUpdate: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isEditing by remember { mutableStateOf(false) }
    var updatedTittle by remember { mutableStateOf(note.tittle) }
    var updatedContent by remember { mutableStateOf(note.content) }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (isEditing) {
                OutlinedTextField(
                    value = updatedTittle,
                    onValueChange = { updatedTittle = it },
                    label =
                    { Text("Tittle") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = updatedContent,
                    onValueChange = { updatedContent = it },
                    label =
                    { Text("Content") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Button(
                        onClick = {
                            onUpdate(updatedTittle, updatedContent)
                            isEditing = false
                        }
                    ) {
                        Text("Update")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedButton(
                        onClick = {
                            isEditing = false
                            updatedTittle = note.tittle
                            updatedContent = note.content
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            } else {
                Text(text = note.tittle, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = note.content, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Button(
                        onClick = onDelete
                    ) {
                        Text("Delete")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedButton(
                        onClick = { isEditing = true }
                    ) {
                        Text("Edit")
                    }

                }
            }

        }
    }
}