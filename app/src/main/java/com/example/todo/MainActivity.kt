package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo.ui.Screens.TodoListScreen
import com.example.todo.ui.theme.ToDoTheme
import com.example.todo.viewModel.TaskViewModelFactory
import com.example.todo.viewModel.TaskviewModel

class MainActivity : ComponentActivity() {

    private val viewModel: TaskviewModel by viewModels {
        TaskViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoTheme {
                TodoListScreen(viewModel)
            }
        }
    }
}

