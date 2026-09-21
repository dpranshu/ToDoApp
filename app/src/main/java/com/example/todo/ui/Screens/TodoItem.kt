package com.example.todo.ui.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.ModeEditOutline
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.data.room_database.TaskItem

@Composable
fun TodoItem(
    item: TaskItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onCheckChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(
            if (item.isDone) Color.LightGray.copy(alpha = 0.5f)
            else Color.White
        ),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onCheckChange(!item.isDone) },
                modifier = Modifier.size(24.dp),
            ) {
                Icon(
                    imageVector = if (item.isDone) Icons.Default.CheckCircle
                    else Icons.Default.RadioButtonUnchecked,
                    contentDescription = "Check Box",
                    tint = if (item.isDone) Color.DarkGray
                    else Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = item.taskName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f),
                color = if (item.isDone) Color.Gray else Color.DarkGray,
                textDecoration = if (item.isDone) TextDecoration.LineThrough else TextDecoration.None
            )
            Row {
                IconButton(
                    onClick = { onEditClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ModeEditOutline,
                        contentDescription = "Edit Task",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Gray
                    )
                }
                IconButton(
                    onClick = { onDeleteClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteOutline,
                        contentDescription = "Delete Task",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Gray
                    )
                }
            }

        }
    }
}