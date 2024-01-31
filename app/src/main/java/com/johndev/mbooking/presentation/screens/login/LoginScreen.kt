package com.johndev.mbooking.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.johndev.mbooking.presentation.viewmodels.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewmodel: LoginViewModel, navigation: () -> Unit) {
    val context = LocalContext.current
    val username: String by viewmodel.username.observeAsState(initial = "")
    val password: String by viewmodel.password.observeAsState(initial = "")
    val message: String? by viewmodel.message.observeAsState(initial = null)
    if (message != null) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Login Screen") })
        },
        content = { padding ->
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = username,
                    onValueChange = { value ->
                        viewmodel.updateForm(username = value, password = password)
                    })
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { value ->
                        viewmodel.updateForm(username = username, password = value)
                    })
                Button(onClick = {
                    viewmodel.createSessionWithLogin {
                        navigation()
                    }
                }) {
                    Text(text = "Obtener Token")
                }
            }
        }
    )
}