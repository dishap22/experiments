package com.example.dialoguebox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecondScreen(onBack = { finish() })
        }
    }
}

@Composable
fun SecondScreen(onBack: () -> Unit) { Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Crust & Sauce",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 32.sp),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = { onBack() }) {
            Text("Back to LearnPerk")
        }
    }
}