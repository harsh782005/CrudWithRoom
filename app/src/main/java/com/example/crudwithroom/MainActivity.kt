package com.example.crudwithroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crudwithroom.data.NoteDatabase
import com.example.crudwithroom.repository.NoteRepository
import com.example.crudwithroom.ui.theme.CrudWithRoomTheme
import com.example.crudwithroom.ui.theme.NoteListScreen
import com.example.crudwithroom.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = NoteDatabase.getDatabase(this)
        val repository = NoteRepository(database.noteDao())

        class NoteViewModelFactory : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return NoteViewModel(repository) as T
            }
        }
        enableEdgeToEdge()
        setContent {
            CrudWithRoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize().systemBarsPadding()) { innerPadding ->
                    val viewModel: NoteViewModel = viewModel(factory = NoteViewModelFactory())
                    NoteListScreen(viewModel)

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CrudWithRoomTheme {
        NoteListScreen(viewModel = viewModel())
    }
}