package com.example.internetcalls

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF080D2B)
private val Purple = Color(0xFF6545FF)
private val White = Color(0xFFF5F5FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }

    val contacts = listOf(
        "Анна Смирнова",
        "Дмитрий Иванов",
        "Екатерина Петрова",
        "Иван Соколов",
        "Мария Никитина",
        "Алексей Кузнецов"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Звонки",
                color = White,
                fontSize = 30.sp
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Голосовые и видеозвонки через интернет",
                color = Color.LightGray,
                fontSize = 14.sp
            )

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                listOf("Контакты", "Звонки").forEachIndexed { index, label ->
                    Button(
                        onClick = { selectedTab = index },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTab == index)
                                Purple else Color(0xFF1A2048)
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(label, color = White)
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            if (selectedTab == 0) {
                Text(
                    text = "Контакты",
                    color = White,
                    fontSize = 22.sp
                )

                Spacer(Modifier.height(12.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(contacts) { name ->
                        ContactItem(name)
                    }
                }
            } else {
                Text(
                    text = "История звонков",
                    color = White,
                    fontSize = 22.sp
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Здесь будут отображаться ваши звонки",
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun ContactItem(name: String) {
    var showCallDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xFF151B40),
                RoundedCornerShape(18.dp)
            )
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Purple, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name.first().toString(),
                color = White,
                fontSize = 22.sp
            )
        }

        Spacer(Modifier.width(12.dp))

        Text(
            text = name,
            color = White,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp
        )

        TextButton(onClick = { showCallDialog = true }) {
            Text("Звонок", color = Color(0xFFB7A8FF))
        }
    }

    if (showCallDialog) {
        AlertDialog(
            onDismissRequest = { showCallDialog = false },
            title = { Text("Звонок") },
            text = {
                Text(
                    "Голосовые и видеозвонки подключим следующим этапом."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = { showCallDialog = false }
                ) {
                    Text("Понятно")
                }
            }
        )
    }
}
