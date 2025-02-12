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
    var showSnackbar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    // Auto-show snackbar after 3 seconds when screen opens
    LaunchedEffect(Unit) {
        delay(3000)
        showSnackbar = true
    }

    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            try {
                val result = snackbarHostState.showSnackbar(
                    message = "\uD83C\uDF55 Pizza Party Special at Crust & Sauce! \n\uD83C\uDF89 Buy 1, Get 1 Free on all Large Pizzas!",
                    actionLabel = "Visit Page",
                    duration = SnackbarDuration.Long,
                    withDismissAction = true
                )

                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        context.startActivity(Intent(context, SecondActivity::class.java))
                    }
                    SnackbarResult.Dismissed -> {
                        // Handle dismiss case if needed
                    }
                }
            } finally {
                showSnackbar = false
            }
        }
    }

    // Reset showSnackbar when returning from SecondActivity
    DisposableEffect(Unit) {
        onDispose {
            showSnackbar = false
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Learnperk",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 32.sp),
                modifier = Modifier.padding(16.dp)
            )
            Button(
                onClick = {
                    if (!showSnackbar) {
                        showSnackbar = true
                    }
                }
            ) {
                Text("Show Snackbar")
            }
        }
    }
}