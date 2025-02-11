package com.example.dialoguebox

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(3000)
        showDialog = true
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Learnperk",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 32.sp),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = { showDialog = true }) {
            Text("Show Dialog")
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("\uD83C\uDF55 Pizza Party Special at Crust & Sauce!")},
            text = { Text("\uD83C\uDF89 Buy 1, Get 1 Free on all Large Pizzas!")},
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        context.startActivity(Intent(context, SecondActivity::class.java))
                    }
                ) {
                    Text("Visit Page")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}