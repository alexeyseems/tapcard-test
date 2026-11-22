package com.tapcard.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TapCardScreen(onShare = { textToShare ->
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, textToShare)
                        type = "text/plain"
                    }
                    startActivity(Intent.createChooser(sendIntent, "Поделиться визиткой"))
                })
            }
        }
    }
}

@Composable
fun TapCardScreen(onShare: (String) -> Unit) {
    var name by remember { mutableStateOf("Алексей") }
    var phone by remember { mutableStateOf("+7 (900) 000-00-00") }
    var telegram by remember { mutableStateOf("@alexeyseems") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "TapCard", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Имя") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Телефон") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telegram,
            onValueChange = { telegram = it },
            label = { Text("Telegram") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val cardText = "Визитка TapCard\nИмя: $name\nТел: $phone\nTelegram: $telegram"
                onShare(cardText)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Поделиться контактом")
        }
    }
}
