package com.ppenelon.passordr

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ppenelon.passordr.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Passordr")
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Greeting(
                        name = "Pepene",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

class Username(
    initialUsername: String
) {
    var name by mutableStateOf(initialUsername)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val usernames = remember { mutableStateListOf<Username>().apply { add(Username(name)); add(Username(name)); } }
    val namesStr = usernames
        .filter { it.name.isNotBlank() }
        .joinToString(" and ") { it.name.trim() }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(4.dp),
    ) {
        Column {
            Text(
                text = "Hello $namesStr!",
                fontSize = 30.sp,
                modifier = Modifier.padding(4.dp)
            )
            LazyColumn {
                items(usernames) { username ->
                    TextField(
                        value = username.name,
                        onValueChange = { username.name = it },
                        label = { Text("Your name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Button(
                onClick = { usernames.add(Username("")) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "+ Ajouter")
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting("Pepene")
    }
}