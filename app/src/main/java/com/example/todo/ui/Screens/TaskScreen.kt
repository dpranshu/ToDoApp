package com.example.todo.ui.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.data.room_database.TaskItem
import com.example.todo.viewModel.TaskviewModel

@Composable
fun TodoListScreen(viewModel: TaskviewModel) {

    val tasks by viewModel.allTasks.collectAsState() //collectAsState() mean data change in database then ui automaticaly refresh
    var taskToEdit by remember { mutableStateOf<TaskItem?>(null) }
    var showEditDialog by remember { mutableStateOf(false) }


    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    taskToEdit = null
                    showEditDialog = true
                },
                shape = RoundedCornerShape(50.dp),
                containerColor = Color.Black,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(7.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task"
                )
                Text(
                    text = "Add Task",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "My Tasks",
                modifier = Modifier
                    .padding(top = 37.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${tasks.filter { !it.isDone }.size} tasks remaining",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (tasks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Tasks",
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 60.dp)
                ) {
                    items(
                        items = tasks,
                        key = { it.id }
                    ) { task ->
                        TodoItem(
                            item = task,
                            onEditClick = {
                                taskToEdit = task
                                showEditDialog = true
                            },
                            onDeleteClick = { viewModel.deleteTask(task) },
                            onCheckChange = { checked ->
                                viewModel.upadateTask(task.copy(isDone = checked))
                            }
                        )

                    }
                }

            }
        }

    }

    if (showEditDialog){
        TaskEditorDialog(
            task = taskToEdit,
            onSave = { newName ->
                if (taskToEdit == null){
                    viewModel.addTask(TaskItem(taskName = newName, isDone = false))
                } else {
                    taskToEdit?.let { currentTask ->
                        viewModel.upadateTask(currentTask.copy(taskName = newName))
                    }
                }
                showEditDialog = false
                taskToEdit = null
            },
            onCancel = {
                showEditDialog = false
                taskToEdit = null
            }
        )

    }


}